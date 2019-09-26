package com.vic.ck.demo;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import org.springframework.util.ClassUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hanyihu
 * @title  二维码合成图片
 * @date 2019/9/25 15:21
 */
public class CodeImage {

    //二维码颜色
    private static final int BLACK = 0xFF000000;
    //二维码颜色
    private static final int WHITE = 0xFFFFFFFF;


    /**
     * <span style="font-size:18px;font-weight:blod;">ZXing 方式生成二维码</span>
     *
     * @param text       <a href="javascript:void();">二维码内容</a>
     * @param width      二维码宽
     * @param height     二维码高
     * @param outPutPath 二维码生成保存路径
     * @param imageType  二维码生成格式
     */
    public static void zxingCodeCreate(String text, int width, int height, String outPutPath, String fileName, String imageType) {
        Map<EncodeHintType, String> his = new HashMap<EncodeHintType, String>();
        //设置编码字符集
        his.put(EncodeHintType.CHARACTER_SET, "utf-8");
        try {
            //1、生成二维码
            BitMatrix encode = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, his);

            //2、获取二维码宽高
            int codeWidth = encode.getWidth();
            int codeHeight = encode.getHeight();

            //3、将二维码放入缓冲流
            BufferedImage image = new BufferedImage(codeWidth, codeHeight, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < codeWidth; i++) {
                for (int j = 0; j < codeHeight; j++) {
                    //4、循环将二维码内容定入图片
                    image.setRGB(i, j, encode.get(i, j) ? BLACK : WHITE);
                }
            }
            File outPut = new File(outPutPath);
            //如果文件夹不存在创建图片
            if (!outPut.isDirectory()) {
                outPut.mkdirs();
            }
            File outPutImage = new File(outPutPath + "/" + fileName);
            //如果图片不存在创建图片
            if (!outPutImage.exists()) {
                outPutImage.createNewFile();
            }
            //5、将二维码写入图片
            ImageIO.write(image, imageType, outPutImage);
            System.out.println("生成成功");
        } catch (WriterException e) {
            e.printStackTrace();
            System.out.println("二维码生成失败");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("生成二维码图片失败");
        }
    }


    /**
     * 生成带背景二维码图片
     *
     * @param text    二维码内容 例：https://www.baidu.com
     * @param width   二维码宽度
     * @param height  二维码高度
     * @param bigPath 背景图路径
     * @param x       二维码距离左边像素
     * @param y       二维码距离顶部像素
     * @return 2018年11月23日
     */
    public static void zxingCodeCreateImage(String text, int width, int height, String bigPath, String x, String y) {
        Map<EncodeHintType, String> his = new HashMap<EncodeHintType, String>();
        //设置编码字符集
        his.put(EncodeHintType.CHARACTER_SET, "utf-8");
        try {
            //1、生成二维码
            BitMatrix encode = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, his);

            //2、获取二维码宽高
            int codeWidth = encode.getWidth();
            int codeHeight = encode.getHeight();

            //3、将二维码放入缓冲流
            BufferedImage image = new BufferedImage(codeWidth, codeHeight, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < codeWidth; i++) {
                for (int j = 0; j < codeHeight; j++) {
                    //4、循环将二维码内容定入图片
                    image.setRGB(i, j, encode.get(i, j) ? BLACK : WHITE);
                }
            }
            //设置圆角
            image = setClip(image, 10);
            mergeImage(bigPath, image, x, y);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("二维码图片生成失败");
        }
    }

    /**
     * 图片切圆角
     *
     * @param srcImage
     * @param radius
     * @return
     */
    public static BufferedImage setClip(BufferedImage srcImage, int radius) {
        int width = srcImage.getWidth();
        int height = srcImage.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gs = image.createGraphics();

        gs.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gs.setClip(new RoundRectangle2D.Double(0, 0, width, height, radius, radius));
        gs.drawImage(srcImage, 0, 0, null);
        gs.dispose();
        return image;
    }

    /**
     * 将二维码绘图到图片中
     *
     * @param bigPath
     * @param small
     * @param x
     * @param y
     * @throws IOException 2018年11月23日
     */
    public static void mergeImage(String bigPath, BufferedImage small, String x, String y) throws IOException {

        try {
            BufferedImage big = ImageIO.read(new File(bigPath));
            Graphics2D g = big.createGraphics();
            float fx = Float.parseFloat(x);
            float fy = Float.parseFloat(y);
            int x_i = (int) fx;
            int y_i = (int) fy;
            g.drawImage(small, x_i, y_i, small.getWidth(), small.getHeight(), null);
            g.dispose();
            ImageIO.write(big, "png", new File(bigPath));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {
       /* String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        String t = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        path = path.replace('/', '\\');
        System.out.println("图片路径"+path);*/
        zxingCodeCreateImage("https://www.baidu.com", 222, 222, "E://11.jpg", "1", "20");
      // zxingCodeCreateImage("https://www.baidu.com", 348, 348, path + "/pyq.jpg", "824", "1790");

    }
}