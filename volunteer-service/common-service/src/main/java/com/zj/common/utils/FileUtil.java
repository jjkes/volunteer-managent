package com.zj.common.utils;

import com.zj.common.exception.MyException;
import com.zj.common.exception.MyFileException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/3/10 16:19
 */

public class FileUtil {

    public static byte[] getFileByteByAbsolutePath(String absolutePath) throws MyException {
        File file = new File(absolutePath);
        if (!file.exists()) {
            throw new MyFileException("文件未找到！");
        }
        try (InputStream ips = new FileInputStream(file)) {
            byte[] b = new byte[ips.available()];
            ips.read(b);
            return b;
        } catch (Exception e) {
            throw new MyFileException(e.getMessage());
        }
    }

    public static String saveFile(byte[] bytes, String fileName, String basePath) throws MyException {
        LocalDateTime localDateTime = LocalDateTime.now();
        String path = File.separator + localDateTime.getYear()
                + File.separator + localDateTime.getMonthValue()
                + File.separator + localDateTime.getDayOfMonth()
                + File.separator + localDateTime.getHour()
                + File.separator + localDateTime.getMinute()
                + File.separator + localDateTime.getSecond()
                + UUID.randomUUID() +File.separator+ fileName;

        OutputStream ops = null;
        try {
            File pathFile= new File(basePath +File.separator+ path.substring(0,path.lastIndexOf(File.separator)));
            if(!pathFile.exists()){
                pathFile.mkdirs();
            }
            File file = new File(basePath +File.separator+ path);
            if (!file.exists()) {
                file.createNewFile();
            }
            ops = new FileOutputStream(file);
            ops.write(bytes);
            ops.flush();
            return path;
        } catch (Exception e) {
            throw new MyFileException(e.getMessage());
        } finally {
            if (ops != null) {
                try {
                    ops.close();
                } catch (IOException e) {
                    throw new MyFileException(e.getMessage());
                }
            }
        }
    }
}
