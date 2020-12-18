package com.zzc.curriumdesign.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @ClassName: CreateVerificationImage
 * @Author: zzc
 * @CreateTime: 2020/12/15 9:25
 * @Description: 创建验证码图片
 */

public class CreateVerificationImage {
    /**
     * 设置验证码图片宽度
     */
    private static final int WIDTH = 100;
    /**
     * 设置验证码图片高度
     */
    private static final int HEIGHT = 30;
    /**
     * 设置验证码长度
     */
    private static final int LENGTH = 4;
    /**
     * 干扰线的数目
     */
    public static final int LINECOUNT = 6;

    /**
     * 验证码的字符库，去掉不便识别的o01Il等字符
     */
    private static final String STR = "23456789"
            + "ABCDEFGHJKLMNPQRSTUVWXYZ" + "abcdefghijkmnpqrstuvwxyz";

    private static final Random RANDOM = new Random();

    /**
    * @Param:[]
    * @Return:java.lang.String
    * @Author:zzc
    * @DateTime:2020/12/15 9:26
    * @Description:通过随机数取字符库中的字符组合成4位验证码
    */
    public String createCode() {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < LENGTH; i++) {
            // nextInt(n)生成一个随机的int值，该值介于[0,n)的区间，也就是0到n之间的随机int值，包含0而不包含n
            char c = STR.charAt(RANDOM.nextInt(STR.length()));

            code.append(c);
        }
        return code.toString();
    }

    /**
    * @Param: []
    * @Return: java.awt.Color
    * @Author: zzc
    * @DateTime: 2020/12/15 9:28
    * @Description: 获取不同颜色
    */
    public Color getColor() {
        return new Color(RANDOM.nextInt(250), RANDOM.nextInt(250),
                RANDOM.nextInt(250));
    }

    /**
    * @Param: []
    * @Return: java.awt.Font
    * @Author: zzc
    * @DateTime: 2020/12/15 9:29
    * @Description:获取字体样式
    */
    public Font getFont() {
        return new Font("黑体", Font.BOLD, 23);
    }

    /**
    * @Param: [g, code]
    * @Return: void
    * @Author: zzc
    * @DateTime: 2020/12/15 9:29
    * @Description: 绘制字符
    */
    public void drawChar(Graphics g, String code) {
        g.setFont(getFont());
        for (int i = 0; i < LENGTH; i++) {
            char c = code.charAt(i);
            g.setColor(getColor());
            g.drawString(c + "", 20 * i + 10, 25);
        }
    }

    /**
    * @Param: [g]
    * @Return: void
    * @Author: zzc
    * @DateTime: 2020/12/15 9:29
    * @Description: 绘制随机线
    */
    public void drawLine(Graphics g) {
        int x = RANDOM.nextInt(WIDTH);
        int y = RANDOM.nextInt(HEIGHT);
        int xl = RANDOM.nextInt(13);
        int yl = RANDOM.nextInt(15);
        g.setColor(getColor());
        g.drawLine(x, y, x + xl, y + yl);
    }

    /**
    * @Param: [code]
    * @Return: java.awt.image.BufferedImage
    * @Author: zzc
    * @DateTime: 2020/12/15 9:29
    * @Description: 绘制验证码图片
    */
    public BufferedImage CreateImage(String code) {
        // 获取画笔
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_3BYTE_BGR);
        Graphics g = image.getGraphics();
        // 设置背景颜色并绘制矩形背景
        g.setColor(new Color(255,255,255));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        // 验证码的绘制
        drawChar(g, code);
        // 随机线的绘制
        for (int i = 0; i < LINECOUNT; i++) {
            drawLine(g);
        }
        // 绘制图片
        g.dispose();
        //返回生成的图片
        return image;
    }
}
