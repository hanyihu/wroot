package com.vic.ck.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

 

/**
 * @author 根据数据库表 自动生成实体类
 * @Description
 * @date 2018-01-09 下午4:34
 */
 
 
public class CodeProduct {
    private String packageOutPath = "com.vic.ck.entity";//指定实体生成所在包的路径
    private static String authorName = "oyml";//作者名字
    //数据库连接
//    private static final String URL ="jdbc:mysql://192.168.16.84:3306/nagios?useUnicode=true&characterEncoding=UTF8";
    private static final String URL = "jdbc:mysql://47.92.246.51:3306/ttltest?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
    private static final String NAME = "ttltest";
    private static final String PASS = "AY2PeN4SehMaNKGj";
    private String tablename = "platform_activity_discountCoupon";//表名
    private static final String DRIVER ="com.mysql.jdbc.Driver";
 
 
 
    /*
     * 构造函数
     */
    public CodeProduct(){
        //创建连接
        Connection con;
        //查要生成实体类的表
        String sql = "show full columns from " + tablename;
        PreparedStatement pStemt = null;
        try {
            try {
                Class.forName(DRIVER);
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            con = DriverManager.getConnection(URL,NAME,PASS);
            pStemt = con.prepareStatement(sql);
            ResultSet rs = pStemt.executeQuery();
            List<ColumnEntity> list=new ArrayList<>();
            while (rs.next()){
                ColumnEntity ce=new ColumnEntity();
                ce.setName(rs.getString("Field"));
                String type = rs.getString("Type");
                ce.setDataType(parseType(type));
                list.add(ce);
            }
            String content = parse(list);
 
            try {
                File directory = new File("");
                String path=this.getClass().getResource("").getPath();
                //System.out.println(path);
//              String outputPath = directory.getAbsolutePath()+ "/src/"+path.substring(path.lastIndexOf("/com/", path.length()), path.length()) + initcap(tablename) + ".java";
                String outputPath = directory.getAbsolutePath()+ "/src/main/java/"+this.packageOutPath.replace(".", "/")+"/"+replaceUnderlineAndfirstToUpper(tablename.toLowerCase(),"_","")+".java";
                File file=new File(outputPath);
                System.out.println("----------->"+outputPath);
                if(!file.exists()){
                    file.createNewFile();
                }
                FileWriter fw = new FileWriter(outputPath);
                PrintWriter pw = new PrintWriter(fw);
                pw.println(content);
                pw.flush();
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
 
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
//          try {
//              con.close();
//          } catch (SQLException e) {
//              // TODO Auto-generated catch block
//              e.printStackTrace();
//          }
        }
    }
 
 
    private String parse(List<ColumnEntity> list) {
        StringBuffer sb = new StringBuffer();
        sb.append("package " + this.packageOutPath + ";\r\n");
        //tablename=replaceUnderlineAndfirstToUpper(tablename.toLowerCase(),"_","");
        importPackage(sb,this.packageOutPath+"."+initcap(tablename));
        //注释部分
        infoMsg(sb,tablename+"实体类");
        //实体部分
//        sb.append("@Entity\r\n");
//        sb.append("@Table(name = \""+tablename+"\")\r\n");
        sb.append("public class " + replaceUnderlineAndfirstToUpper(tablename.toLowerCase(),"_","")
                +" implements Serializable"+"{\r\n");
        sb.append("\tprivate static final long serialVersionUID = 1L;\r\n");
        sb.append("\r\n");
        addElement(sb,list);
        sb.append("}\r\n");
 
        return sb.toString();
    }
    /**
     * 导入包
     *@title
     *@date 2014-10-17
     *@author ZhengYunfei
     * @param sb
     */
    public static void importPackage(StringBuffer sb,String tablename){
        sb.append("\r\n");
//        sb.append("import javax.persistence.Column;\r\n");
//        sb.append("import javax.persistence.Entity;\r\n");
//        sb.append("import javax.persistence.Id;\r\n");
//        sb.append("import javax.persistence.Table;\r\n");
        sb.append("import java.io.Serializable;\r\n");
        sb.append("import java.util.Date;\r\n");
        sb.append("\r\n");
    }
 
 
    public static void addElement(StringBuffer sb,List<ColumnEntity> list){
        for (ColumnEntity ce:list) {
//            sb.append("\t@Column(name = \""+ce.getName()+"\")\r\n");
            sb.append("\t\r\n");
            sb.append("\tprivate "+ce.getDataType()+" "+ce.getName()+";\r\n");
            sb.append("\r\n");
        }
    }
    /**
     * 自动生成注释
     *@title
     *@date 2014-10-17
     *@author ZhengYunfei
     */
    public static void infoMsg(StringBuffer sb,String msg){
        sb.append("/**\r\n");
        sb.append("*@author "+authorName+"\r\n");
        sb.append("*@Description "+msg+"\r\n");
        sb.append("*@date "+ "2222-2-22"+"\r\n");
        sb.append("*/\r\n");
        sb.append("\r\n");
    }
    /**
     * 首字母大写
     *
     * @param srcStr
     * @return
     */
    public static String firstCharacterToUpper(String srcStr) {
        return srcStr.substring(0, 1).toUpperCase() + srcStr.substring(1);
    }
    /**
     * 替换字符串并让它的下一个字母为大写
     * @param srcStr
     * @param org
     * @param ob
     * @return
     */
    public static String replaceUnderlineAndfirstToUpper(String srcStr,String org,String ob)
    {
        String newString = "";
        int first=0;
        while(srcStr.indexOf(org)!=-1)
        {
            first=srcStr.indexOf(org);
            if(first!=srcStr.length())
            {
                newString=newString+srcStr.substring(0,first)+ob;
                srcStr=srcStr.substring(first+org.length(),srcStr.length());
                srcStr=firstCharacterToUpper(srcStr);
            }
        }
        newString=firstCharacterToUpper(newString+srcStr);
        return newString;
    }
 
 
    /**
     * 功能：将输入字符串的首字母改成大写
     * @param str
     * @return
     */
    private static String initcap(String str) {
 
        char[] ch = str.toCharArray();
        if(ch[0] >= 'a' && ch[0] <= 'z'){
            ch[0] = (char)(ch[0] - 32);
        }
 
        return new String(ch);
    }
 
    private static String parseType(String type) {
        if(type.toLowerCase().contains("int")){
            return "Integer";
        }
        if(type.toLowerCase().contains("varchar")){
            return "String";
        }
        if(type.toLowerCase().contains("datetime")){
            return "Date";
        }
        if(type.toLowerCase().contains("text")){
            return "String";
        }
        if(type.toLowerCase().contains("double")){
            return "Double";
        }
        if(type.toLowerCase().contains("decimal")){
            return "BigDecimal";
        }
        if(type.toLowerCase().contains("tinyint")){
            return "Integer";
        }
        return null;
    }
 
    private class ColumnEntity{
        private String name;
        private String dataType;
 
        public String getName() {
            return name;
        }
 
        public void setName(String name) {
            this.name = name;
        }
 
        public String getDataType() {
            return dataType;
        }
 
        public void setDataType(String dataType) {
            this.dataType = dataType;
        }
    }
 
    /**
     * 出口
     * TODO
     * @param args
     */
    public static void main(String[] args) {
 
        new CodeProduct();
 
    }
}