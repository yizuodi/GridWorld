import imagereader.*;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.awt.Image;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.color.*;
import java.util.logging.*;

// 实现了对于图片读写的操作
public class ImplementImageIO implements IImageIO {
    private int bitcount = 0;

    // 利用二进制流读取Bitmap位图文件
    public Image myRead(String filePath) {
        Logger logger = Logger.getLogger("myRead");
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));
            // 读取位图头
            byte[] b = new byte[14];
            bis.read(b, 0, 14);
            int bfType = ((b[1] << 8) & 0xff00) | (b[0] & 0xff);  // 位图类型
            int bfSize = ((b[5] << 24) & 0xff000000) | ((b[4] << 16) & 0xff0000) | ((b[3] << 8) & 0xff00) | (b[2] & 0xff);  // 位图大小
            int bfReserved1 = ((b[7] << 8) & 0xff00) | (b[6] & 0xff);  // 位图保留字
            int bfReserved2 = ((b[9] << 8) & 0xff00) | (b[8] & 0xff);  // 位图保留字
            int bfOffBits = ((b[13] << 24) & 0xff000000) | ((b[12] << 16) & 0xff0000) | ((b[11] << 8) & 0xff00) | (b[10] & 0xff);  // 位图数据偏移量
            // 读取位图信息
            b = new byte[40];
            bis.read(b, 0, 40);
            int biSize = ((b[3] << 24) & 0xff000000) | ((b[2] << 16) & 0xff0000) | ((b[1] << 8) & 0xff00) | (b[0] & 0xff);  // 位图信息头大小
            int biWidth = ((b[7] << 24) & 0xff000000) | ((b[6] << 16) & 0xff0000) | ((b[5] << 8) & 0xff00) | (b[4] & 0xff);  // 位图宽度
            int biHeight = ((b[11] << 24) & 0xff000000) | ((b[10] << 16) & 0xff0000) | ((b[9] << 8) & 0xff00) | (b[8] & 0xff);  // 位图高度
            int biPlanes = ((b[13] << 8) & 0xff00) | (b[12] & 0xff);  // 位图位面数
            int biBitCount = ((b[15] << 8) & 0xff00) | (b[14] & 0xff);  // 位图每像素位数
            int biCompression = ((b[19] << 24) & 0xff000000) | ((b[18] << 16) & 0xff0000) | ((b[17] << 8) & 0xff00) | (b[16] & 0xff);  // 位图压缩算法
            int biSizeImage = ((b[23] << 24) & 0xff000000) | ((b[22] << 16) & 0xff0000) | ((b[21] << 8) & 0xff00) | (b[20] & 0xff);  // 位图压缩图像大小
            int biXPelsPerMeter = ((b[27] << 24) & 0xff000000) | ((b[26] << 16) & 0xff0000) | ((b[25] << 8) & 0xff00) | (b[24] & 0xff);  // 位图水平分辨率
            int biYPelsPerMeter = ((b[31] << 24) & 0xff000000) | ((b[30] << 16) & 0xff0000) | ((b[29] << 8) & 0xff00) | (b[28] & 0xff);  // 位图垂直分辨率
            int biClrUsed = ((b[35] << 24) & 0xff000000) | ((b[34] << 16) & 0xff0000) | ((b[33] << 8) & 0xff00) | (b[32] & 0xff);  // 位图实际使用的颜色表中的颜色数
            int biClrImportant = ((b[39] << 24) & 0xff000000) | ((b[38] << 16) & 0xff0000) | ((b[37] << 8) & 0xff00) | (b[36] & 0xff);  // 位图显示过程中重要的颜色数
            // 读取调色板
            int[] rgb = new int[biWidth * biHeight];  // 位图像素数组
            int n = 0;  // 位图像素数组索引
            int padding = 0;  // 位图每行字节数不是4的倍数时要补齐的字节数
            if (biWidth % 4 != 0) {
                padding = 4 - (biWidth * biBitCount / 8) % 4;
            }
            int type = BufferedImage.TYPE_INT_RGB;  // 位图类型，默认为24位位图
            if (biBitCount == 24) {
                type = BufferedImage.TYPE_INT_RGB;  // 24位位图
                for (int i = 0; i < biHeight; i++) {
                    for (int j = 0; j < biWidth; j++) {
                        b = new byte[3];
                        bis.read(b, 0, 3);
                        int blue = b[0] & 0xff;
                        int green = b[1] & 0xff;
                        int red = b[2] & 0xff;
                        rgb[n++] = (0xff << 24) | (red << 16) | (green << 8) | blue;
                    }
                    if (padding != 0)  // 位图每行字节数不是4的倍数时要补齐的字节数
                    {
                        bis.read(new byte[padding], 0, padding);
                    }
                }
            } else if (biBitCount == 8) {
                type = BufferedImage.TYPE_BYTE_INDEXED;  // 8位位图
                b = new byte[1024];
                bis.read(b, 0, 1024);
                int[] temp = new int[256];
                for (int i = 0; i < 256; i++) {
                    rgbdata(b, temp, i);
                }
                for (int i = 0; i < biHeight; i++) {
                    for (int j = 0; j < biWidth; j++) {
                        int index = bis.read() & 0xff;
                        rgb[n++] = temp[index];
                    }
                    if (padding != 0)  // 位图每行字节数不是4的倍数时要补齐的字节数
                    {
                        bis.read(new byte[padding], 0, padding);
                    }
                }
            } else if (biBitCount == 4) {
                int[] temp = new int[16];
                type = BufferedImage.TYPE_BYTE_INDEXED;  // 4位位图
                b = new byte[1024];
                bis.read(b, 0, 1024);
                for (int i = 0; i < 16; i++) {
                    rgbdata(b, temp, i);
                }
                for (int i = 0; i < biHeight; i++) {
                    for (int j = 0; j < biWidth / 2; j++) {
                        int index = bis.read() & 0xff;
                        rgb[n++] = temp[index >> 4];
                        rgb[n++] = temp[index & 0xf];
                    }
                    if (biWidth % 2 != 0)  // 位图宽度为奇数时，最后一位要补齐
                    {
                        int index = bis.read() & 0xff;
                        rgb[n++] = temp[index >> 4];
                    }
                    if (padding != 0)  // 位图每行字节数不是4的倍数时要补齐的字节数
                    {
                        bis.read(new byte[padding], 0, padding);
                    }
                }
            } else if (biBitCount == 1) {
                type = BufferedImage.TYPE_BYTE_BINARY;  // 1位位图
                b = new byte[1024];
                bis.read(b, 0, 1024);
                int[] temp = new int[2];
                for (int i = 0; i < 2; i++) {
                    rgbdata(b, temp, i);
                }
                for (int i = 0; i < biHeight; i++) {
                    for (int j = 0; j < biWidth / 8; j++) {
                        int index = bis.read() & 0xff;
                        rgb[n++] = temp[index >> 7];
                        rgb[n++] = temp[(index >> 6) & 0x1];
                        rgb[n++] = temp[(index >> 5) & 0x1];
                        rgb[n++] = temp[(index >> 4) & 0x1];
                        rgb[n++] = temp[(index >> 3) & 0x1];
                        rgb[n++] = temp[(index >> 2) & 0x1];
                        rgb[n++] = temp[(index >> 1) & 0x1];
                        rgb[n++] = temp[index & 0x1];
                    }
                    if (biWidth % 8 != 0) {
                        int index = bis.read() & 0xff;
                        for (int k = 0; k < biWidth % 8; k++) {
                            rgb[n++] = temp[(index >> (7 - k)) & 0x1];
                        }
                    }
                    if (padding != 0)  // 位图每行字节数不是4的倍数时要补齐的字节数
                    {
                        bis.read(new byte[padding], 0, padding);
                    }
                }
            }
            bis.close();
            // 输出biBitCount
            System.out.println("biBitCount: " + biBitCount);
            bitcount = biBitCount;
            // 将rgb上下翻转
            int[] rgb2 = new int[biWidth * biHeight];
            for (int i = 0; i < biHeight; i++) {
                for (int j = 0; j < biWidth; j++) {
                    rgb2[(biHeight - i - 1) * biWidth + j] = rgb[i * biWidth + j];
                }
            }
            // 返回读取的位图
            BufferedImage image = new BufferedImage(biWidth, biHeight, type);
            image.setRGB(0, 0, biWidth, biHeight, rgb2, 0, biWidth);
            return image;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "myRead error...", e);
        }
        return null;
    }

    private void rgbdata(byte[] b, int[] temp, int i) {
        int blue = b[i * 4] & 0xff;
        int green = b[i * 4 + 1] & 0xff;
        int red = b[i * 4 + 2] & 0xff;
        temp[i] = (0xff << 24) | (red << 16) | (green << 8) | blue;
    }

    // 保存位图
    public Image myWrite(Image image, String filePath) {
        Logger logger = Logger.getLogger("myWrite");
        try {
            BufferedImage bi;
            if (bitcount == 8) {
                bi = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_BYTE_INDEXED);
            } else if (bitcount == 4) {
                bi = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_BYTE_INDEXED);
            } else if (bitcount == 1) {
                bi = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_BYTE_BINARY);
            } else {
                bi = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
            }
            Graphics2D g = bi.createGraphics();
            g.drawImage(image, null, null);
            ImageIO.write(bi, "bmp", new File(filePath));
        } catch (Exception e) {
            logger.log(Level.SEVERE, "myWrite error...", e);
        }
        return null;
    }
}
