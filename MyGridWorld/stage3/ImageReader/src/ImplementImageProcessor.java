import java.awt.*;
import javax.imageio.*;

import imagereader.*;

import java.io.*;
import javax.swing.*;
import java.awt.image.*;

// 实现IImageProcessor接口
public class ImplementImageProcessor implements IImageProcessor
{
    // 颜色通道R
    public Image showChanelR(Image sourceImage)
    {
        return showChanel(sourceImage, 0);
    }
    // 颜色通道G
    public Image showChanelG(Image sourceImage)
    {
        return showChanel(sourceImage, 1);
    }
    // 颜色通道B
    public Image showChanelB(Image sourceImage)
    {
        return showChanel(sourceImage, 2);
    }
    // 灰度图像
    public Image showGray(Image sourceImage)
    {
        return showChanel(sourceImage, 3);
    }
    // 实现showChanel方法
    public Image showChanel(Image sourceImage, int chanel) {
        BufferedImage image;
        image = new BufferedImage(sourceImage.getWidth(null), sourceImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.drawImage(sourceImage, 0, 0, sourceImage.getWidth(null), sourceImage.getHeight(null), null);
        g.dispose();
        int[] rgb = new int[3];
        // 如果chanel为0，返回红色通道图像
        if (chanel == 0) {
            for (int i = 0; i < sourceImage.getWidth(null); i++) {
                for (int j = 0; j < sourceImage.getHeight(null); j++) {
                    int pixel = image.getRGB(i, j);
                    rgb[0] = (pixel & 0xff0000) >> 16;
                    rgb[1] = (pixel & 0xff00) >> 8;
                    rgb[2] = (pixel & 0xff);
                    pixel = (rgb[0] << 16) | (0 << 8) | 0;
                    image.setRGB(i, j, pixel);
                }
            }
        } else if (chanel == 1) // 如果chanel为1，返回绿色通道图像
        {
            for (int i = 0; i < sourceImage.getWidth(null); i++) {
                for (int j = 0; j < sourceImage.getHeight(null); j++) {
                    int pixel = image.getRGB(i, j);
                    rgb[0] = (pixel & 0xff0000) >> 16;
                    rgb[1] = (pixel & 0xff00) >> 8;
                    rgb[2] = (pixel & 0xff);
                    pixel = (0 << 16) | (rgb[1] << 8) | 0;
                    image.setRGB(i, j, pixel);
                }
            }
        } else if (chanel == 2) // 如果chanel为2，返回蓝色通道图像
        {
            for (int i = 0; i < sourceImage.getWidth(null); i++) {
                for (int j = 0; j < sourceImage.getHeight(null); j++) {
                    int pixel = image.getRGB(i, j);
                    rgb[0] = (pixel & 0xff0000) >> 16;
                    rgb[1] = (pixel & 0xff00) >> 8;
                    rgb[2] = (pixel & 0xff);
                    pixel = (0 << 16) | (0 << 8) | rgb[2];
                    image.setRGB(i, j, pixel);
                }
            }
        }
        else // 返回灰度图像
        {
            for (int i = 0; i < sourceImage.getWidth(null); i++)
            {
                for (int j = 0; j < sourceImage.getHeight(null); j++)
                {
                    int pixel = image.getRGB(i, j);
                    rgb[0] = (pixel & 0xff0000) >> 16;
                    rgb[1] = (pixel & 0xff00) >> 8;
                    rgb[2] = (pixel & 0xff);
                    int gray = (int)(0.299 * rgb[0] + 0.587 * rgb[1] + 0.114 * rgb[2]);
                    pixel = (gray << 16) | (gray << 8) | gray;
                    image.setRGB(i, j, pixel);
                }
            }
        }
        return image;
    }
}