package com.zj.common.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/3/27 9:24
 */

public class ImgUtils {

    public static boolean convertTiffToJpg(String path, String newPath) {
        try {
            final BufferedImage tiff = ImageIO.read(new File(path));
            if(tiff!=null) {
                BufferedImage newBufferedImage = new BufferedImage(tiff.getWidth(), tiff.getHeight(),BufferedImage.TYPE_INT_RGB);
                newBufferedImage.createGraphics().drawImage(tiff, 0, 0, Color.WHITE, null);
                ImageIO.write(newBufferedImage, "jpg", new File(newPath));
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("第一种方法处理失败");

        }
        return false;
    }

    public static void main(String[] args) {
        String olFilePath = "C:\\Users\\a1204\\Desktop\\work\\bug测试包\\说明书底色问题\\新建文件夹 (3)\\ft_1.tif";
        String neFilePath = "C:\\Users\\a1204\\Desktop\\work\\bug测试包\\说明书底色问题\\新建文件夹 (3)\\ft_1_1.tif";
        System.err.println(convertTiffToJpg(olFilePath, neFilePath));
    }

}
