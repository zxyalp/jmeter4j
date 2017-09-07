package com.jmter4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 将内容追加到文件
 * Created by yang.zhou on 2017/9/6.
 */
public class FileHelper {

//    public static void main(String[] args) {
//        randomWrite("D:\\tmp\\1.json", "test context");
//
//        randomRed("D:\\tmp\\1.json",10);
//
//    }


    public static void randomRed(String path, int pointer) {


        try {
            RandomAccessFile randomFile = new RandomAccessFile(new File(path), "r");

            System.out.println("文件的初始化位置：" + randomFile.getFilePointer());

            // 移动指针位置
            randomFile.seek(pointer);

            System.out.println("文件的移动后指针位置：" + randomFile.getFilePointer());

            byte[] buffer = new byte[1024];

            int hasRead;
            hasRead = 0;

            while ((hasRead = randomFile.read(buffer)) > 0) {

                System.out.print(new String(buffer, 0, hasRead));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /*
    * 向文件中追加数据
    * */
    public static void randomWrite(String fileParent, String fileName, String text) throws IOException {

        RandomAccessFile randomFile = null;
        File file = null;

        try {
            file = new File(fileParent);
            if (!file.exists()) {
                System.out.println("路径不存在，开始创建目录.");
                if(file.mkdirs())
                    System.out.println("目录创建成功.");
            }
            if (file.isDirectory()) {
                file = new File(file, fileName);
            }

            randomFile = new RandomAccessFile(file, "rw");

            System.out.println("开始保存内容：\r\n" + text);

            // 将指针移到文件最后
            randomFile.seek(randomFile.length());
            randomFile.write(text.getBytes());
            randomFile.write("\r\n".getBytes());
            System.out.println("保存成功");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {

            if (randomFile != null)
                randomFile.close();

        }

    }
}


