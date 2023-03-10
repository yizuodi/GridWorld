import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.util.logging.*;

import java.awt.image.BufferedImage;

import imagereader.*;

// 用于测试的类
public class ImageReaderTest {
    IImageIO imageioer;
    IImageProcessor processor;
    BufferedImage image1;
    BufferedImage image2;
    String filePath = "./bmptest/";

    /**
     * 构造函数
     */
    public ImageReaderTest() {
        Logger logger = Logger.getLogger("ImageReaderTest");
        imageioer = new ImplementImageIO();
        processor = new ImplementImageProcessor();
        try {
            image1 = (BufferedImage) ImageIO.read(new File(filePath + "1.bmp"));
            image2 = (BufferedImage) ImageIO.read(new File(filePath + "2.bmp"));
        } catch (Exception e) {
            System.out.println("Can't find the files!");
            logger.log(Level.SEVERE, "Can't find the files!", e);
        }
    }

    /**
     * 用于比较两幅图片是否一致
     **/
    public boolean isEqual(BufferedImage image1, BufferedImage image2) {
        if (image1.getHeight() != image2.getHeight() || image1.getWidth() != image2.getWidth()) {
            return false;
        }
        for (int row = 0; row < image1.getHeight(); row++) {
            for (int col = 0; col < image1.getWidth(); col++) {
                if (image1.getRGB(col, row) != image2.getRGB(col, row)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 测试图片读取
     **/
    @Test
    public void testMyRead() {
        Logger logger = Logger.getLogger("testMyRead");
        try {
            assertEquals(true, isEqual(image1, (BufferedImage) imageioer.myRead(filePath + "1.bmp")));
            assertEquals(true, isEqual(image2, (BufferedImage) imageioer.myRead(filePath + "2.bmp")));
            System.out.println("testMyRead passed!");
        } catch (Exception e) {
            System.out.println("testMyRead error...");
            logger.log(Level.SEVERE, "testMyRead error...", e);
        }
    }

    /**
     * 测试图片写入
     **/
    @Test
    public void testMyWrite() {
        Logger logger = Logger.getLogger("testMyWrite");
        try {
            imageioer.myWrite(image1, filePath + "myresult/1.bmp");
            imageioer.myWrite(image2, filePath + "myresult/2.bmp");
            assertEquals(true, isEqual(image1, (BufferedImage) ImageIO.read(new File(filePath + "myresult/1.bmp"))));
            assertEquals(true, isEqual(image2, (BufferedImage) ImageIO.read(new File(filePath + "myresult/2.bmp"))));
            System.out.println("testMyWrite passed!");
        } catch (Exception e) {
            System.out.println("testMyWrite error...");
            logger.log(Level.SEVERE, "testMyWrite error...", e);
        }
    }

    /**
     * 测试红色通道
     **/
    @Test
    public void testShowChanelR() {
        Logger logger = Logger.getLogger("testShowChanelR");
        try {
            imageioer.myWrite(processor.showChanelR(image1), filePath + "myresult/1_red.bmp");
            imageioer.myWrite(processor.showChanelR(image2), filePath + "myresult/2_red.bmp");
            assertEquals(true, isEqual((BufferedImage) ImageIO.read(new File(filePath + "goal/1_red_goal.bmp")), (BufferedImage) ImageIO.read(new File(filePath + "myresult/1_red.bmp"))));
            assertEquals(true, isEqual((BufferedImage) ImageIO.read(new File(filePath + "goal/2_red_goal.bmp")), (BufferedImage) ImageIO.read(new File(filePath + "myresult/2_red.bmp"))));
            System.out.println("testShowChanelR passed!");
        } catch (Exception e) {
            System.out.println("testShowChanelR error...");
            logger.log(Level.SEVERE, "testShowChanelR error...", e);
        }
    }

    /**
     * 测试绿色通道
     **/
    @Test
    public void testShowChanelG() {
        Logger logger = Logger.getLogger("testShowChanelG");
        try {
            imageioer.myWrite(processor.showChanelG(image1), filePath + "myresult/1_green.bmp");
            imageioer.myWrite(processor.showChanelG(image2), filePath + "myresult/2_green.bmp");
            assertEquals(true, isEqual((BufferedImage) ImageIO.read(new File(filePath + "goal/1_green_goal.bmp")), (BufferedImage) ImageIO.read(new File(filePath + "myresult/1_green.bmp"))));
            assertEquals(true, isEqual((BufferedImage) ImageIO.read(new File(filePath + "goal/2_green_goal.bmp")), (BufferedImage) ImageIO.read(new File(filePath + "myresult/2_green.bmp"))));
            System.out.println("testShowChanelG passed!");
        } catch (Exception e) {
            System.out.println("testShowChanelG error...");
            logger.log(Level.SEVERE, "testShowChanelG error...", e);
        }
    }

    /**
     * 测试蓝色通道
     **/
    @Test
    public void testShowChanelB() {
        Logger logger = Logger.getLogger("testShowChanelB");
        try {
            imageioer.myWrite(processor.showChanelB(image1), filePath + "myresult/1_blue.bmp");
            imageioer.myWrite(processor.showChanelB(image2), filePath + "myresult/2_blue.bmp");
            assertEquals(true, isEqual((BufferedImage) ImageIO.read(new File(filePath + "goal/1_blue_goal.bmp")), (BufferedImage) ImageIO.read(new File(filePath + "myresult/1_blue.bmp"))));
            assertEquals(true, isEqual((BufferedImage) ImageIO.read(new File(filePath + "goal/2_blue_goal.bmp")), (BufferedImage) ImageIO.read(new File(filePath + "myresult/2_blue.bmp"))));
            System.out.println("testShowChanelB passed!");
        } catch (Exception e) {
            System.out.println("testShowChanelB error...");
            logger.log(Level.SEVERE, "testShowChanelB error...", e);
        }
    }

    /**
     * 测试灰度图
     **/
    @Test
    public void testshowGray() {
        Logger logger = Logger.getLogger("testshowGray");
        try {
            imageioer.myWrite(processor.showGray(image1), filePath + "myresult/1_gray.bmp");
            imageioer.myWrite(processor.showGray(image2), filePath + "myresult/2_gray.bmp");
            assertEquals(true, isEqual((BufferedImage) ImageIO.read(new File(filePath + "goal/1_gray_goal.bmp")), (BufferedImage) ImageIO.read(new File(filePath + "myresult/1_gray.bmp"))));
            assertEquals(true, isEqual((BufferedImage) ImageIO.read(new File(filePath + "goal/2_gray_goal.bmp")), (BufferedImage) ImageIO.read(new File(filePath + "myresult/2_gray.bmp"))));
            System.out.println("testshowGray passed!");
        } catch (Exception e) {
            System.out.println("testshowGray error...");
            logger.log(Level.SEVERE, "testshowGray error...", e);
        }
    }
}



