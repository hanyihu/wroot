//package com.vic;
//
//import java.awt.Graphics;
//import java.awt.image.BufferedImage;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.util.UUID;
//
//import javax.imageio.ImageIO;
//
//import org.apache.commons.codec.binary.Base64;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;
//
//@SuppressWarnings("restriction")
//public class TestOther {
//
//	public static void main(String[] args) throws Exception {
//		//bubble();
//		
//		
//		String a = "1";
//		String b = new String(Base64.encodeBase64(a.getBytes(), true));
//		String c = new String( Base64.decodeBase64(b.getBytes()));
//		System.out.println(b);
//		System.out.println(c);		
//		System.out.println(UUID.randomUUID().toString().replace("-", ""));
//	}
//	
//	public static void t1() throws Exception{
//		ObjectMapper o = new ObjectMapper();
//		Test t = new Test(11, "zhangsan", 24);
//		String str =o.writeValueAsString(t);
//		System.out.println(str);
//		
//		String str2=  "{\"a\":11,\"name\":\"zhangsan\",\"age\":24}";
//		Test b = o.readValue(str2, Test.class);
//		System.out.println(b);
//	}
//	
//	public static void t2(){
////		String a = "<p style=\"text-align: center;\"><img style=\"width: 100%;\" src=\"http://39.106.13.51:10081/attachment/ajax/visit/62\" title=\"pingguoX_kantuwang.jpg\" alt=\"pingguoX_kantuwang.jpg\" /></p>";
//		String c = "adsa <img xxx /> asddasdas <img title='aaa'/>";
//		String replacement="$0 width=\"100%;\" ";
//		String b = c.replaceAll("(<img)", replacement);
//		System.out.println(b);
//	}
//	
//	public static void t3() throws Exception{
//		 int i=1;
//	        int j=2;
//	        InputStream imagein = new FileInputStream("E:\\pic\\"+ i + ".png");
//	        InputStream imagein2 = new FileInputStream("E:\\pic\\"+j + ".png");
//
//	        BufferedImage image = ImageIO.read(imagein);
//	        BufferedImage image2 = ImageIO.read(imagein2);
//	        Graphics g = image.getGraphics();
//	        g.drawImage(image2, image.getWidth() - image2.getWidth() - 35, image.getHeight() - image2.getHeight() - 45,
//	                image2.getWidth() + 10, image2.getHeight() + 5, null);
//	        OutputStream outImage = new FileOutputStream("E:\\pic\\custom" + j + "-" + i + ".jpg");
//	        JPEGImageEncoder enc = JPEGCodec.createJPEGEncoder(outImage);
//	        enc.encode(image);
//	        imagein.close();
//	        imagein2.close();
//	        outImage.close();
//	}
//	
//	public static void bubble(){
//		int[] arr = {9, 4,2,6,7,1,3};
//		int len = arr.length;
//		for(int i=0; i<len; i++) {
//			for(int j=i+1; j<len; j++) {
//				if(arr[i] > arr[j]) {
//					int tem = arr[i];
//					arr[i] = arr[j];
//					arr[j] = tem;
//				}
//			}
//		}
//		for(int i :arr) {
//			System.out.print(i + "\t");
//		}
//	}
//}
//
//
//
//class Test{
//	private int a;
//	
//	@JsonIgnore
//	private String name;
//	
//	private int age;
//	
//	
//
//	public Test() {
//		super();
//	}
//
//	@Override
//	public String toString() {
//		return "Test [a=" + a + ", name=" + name + ", age=" + age + "]";
//	}
//
//	public Test(int a, String name, int age) {
//		super();
//		this.a = a;
//		this.name = name;
//		this.age = age;
//	}
//
//	public int getA() {
//		return a;
//	}
//
//	public void setA(int a) {
//		this.a = a;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public int getAge() {
//		return age;
//	}
//
//	public void setAge(int age) {
//		this.age = age;
//	}
//	
//	
//}