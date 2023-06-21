package com.zj.common.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.security.SecureRandom;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/1/11 10:36
 */

public class VerifyImgUtil {
    /** 26个英文字母表 */
    private final static String LETTER="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    /** 验证码长度 */
    private final static int VERIFY_CODE_NUM=5;
    /** 验证码图片的宽度 */
    private final static int IMG_WIDTH=300;
    /** 验证码图片的长度 */
    private final static int IMG_HEIGHT=100;
    /** 验证码图片类型 8 代表jpg */
    private final static String[] FT = { "Arial", "Verdana", "Comic Sans MS", "Impact", "Haettenschweiler", "Lucida Sans Unicode", "Garamond", "Courier New", "Book Antiqua", "Arial Narrow" };

    private final static int LINE_SIZE=3;
    /**
     * @description: 获取图片的code
     * @return java.lang.String
     * @param randomCode 随机字符
     * @author 赵健
     * @date 2023/1/11 10:37
     */
    public static String getImgCode(String randomCode){
        // 获取img的随机验证码字符串
        SecureRandom random = new SecureRandom();
        StringBuilder imgCode = new StringBuilder();
        for(int i=0;i<VERIFY_CODE_NUM;i++){
            imgCode.append(LETTER.charAt(random.nextInt(62)));
        }
        return imgCode.toString();
    }

    public static BufferedImage getTextImage(String imgCode){
        BufferedImage bufferedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_INT_RGB);
        SecureRandom random = new SecureRandom(imgCode.getBytes());
        Graphics graphics = bufferedImage.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0,0,IMG_WIDTH,IMG_HEIGHT);
        // rgb颜色变量
        int r=0;
        int g=0;
        int b=0;
        int x=IMG_WIDTH/6;
        for(char c : imgCode.toCharArray()){
            // 获取随机RGB值
            r=random.nextInt(255);
            g=random.nextInt(255);
            b=random.nextInt(255);
            // 设置色彩
            int charHeight=60+random.nextInt(IMG_WIDTH/6);
            graphics.setFont(new Font(FT[random.nextInt(10)],Font.PLAIN,charHeight));
            graphics.setColor(new Color(r,g,b));
            // 高度
            graphics.drawString(String.valueOf(c),x,charHeight);
            x+=charHeight/2;

        }
        Graphics2D graphics2D = bufferedImage.createGraphics();
        int lineNum=5+ random.nextInt(10);
        int x1, y1, x2, y2;
        for(int i=0;i<lineNum;i++){
            r=random.nextInt(255);
            g=random.nextInt(255);
            b=random.nextInt(255);
            graphics2D.setStroke(new BasicStroke(LINE_SIZE));
            graphics2D.setColor(new Color(r,g,b));
            x1 = random.nextInt(IMG_WIDTH);
            y1 = random.nextInt(IMG_HEIGHT);
            x2 = random.nextInt(IMG_WIDTH);
            y2 = random.nextInt(IMG_HEIGHT);
            graphics2D.drawLine(x1,y1,x2,y2);
        }
        graphics2D.dispose();
        graphics.dispose();
        return bufferedImage;
    }
}
