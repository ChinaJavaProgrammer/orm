package util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 生成图形验证码的缓存流需要首先生成验证码getValidateCode()才能拿到验证码的具体内容getCode()
 */
public class ValidateCode {
    private String code = "";
    private static final String[] FONT_TYPE = {"宋体", "微软雅黑", "仿宋", "华文行楷", "楷体", "隶书", "黑体"};
    private static final String[] WORD_TYPE = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C",
            "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private static final Color[] COLOR_TYPE = {Color.black, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.red};
    private static final Integer WIDTH = 150;
    private static final Integer HEIGHT = 20;

    public ValidateCode() {
    }

    /**
     * 生成一个图形验证码的缓存流
     *
     * @return
     */
    public BufferedImage getValidateCode() {
        BufferedImage bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bi.createGraphics();
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);
        writeCode(graphics);
        writeInterference(graphics);
        return bi;
    }

    /**
     * 画验证码
     * @param graphics
     */
    private void writeCode(Graphics2D graphics) {
        final int COLOR_TYPE_LENGTH = COLOR_TYPE.length;
        final int FONT_TYPE_LENGTH = FONT_TYPE.length;
        final int WORD_TYPE_LENGTH = WORD_TYPE.length;
        String temp = "";

        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            temp = WORD_TYPE[random.nextInt(WORD_TYPE_LENGTH)];
            graphics.setColor(COLOR_TYPE[random.nextInt(COLOR_TYPE_LENGTH)]);
            graphics.setFont(new Font(FONT_TYPE[random.nextInt(FONT_TYPE_LENGTH)], i, 20));
            graphics.drawString(temp, WIDTH / 8 + i * WIDTH / 4, HEIGHT / 2 + 5);
            code += temp;
        }
        code  = code.toLowerCase();
    }

    /**
     * 画干扰线
     * @param graphics
     */
    private void writeInterference(Graphics2D graphics) {
        final int COLOR_TYPE_LENGTH = COLOR_TYPE.length;
        final int FONT_TYPE_LENGTH = FONT_TYPE.length;
        final int WORD_TYPE_LENGTH = WORD_TYPE.length;
        Random random = new Random();
        for (int i = 0; i < 2; i++) {
            graphics.setColor(COLOR_TYPE[random.nextInt(COLOR_TYPE_LENGTH)]);
            graphics.setFont(new Font(FONT_TYPE[random.nextInt(FONT_TYPE_LENGTH)], i, 22));
            graphics.drawString("/", random.nextInt(WIDTH), HEIGHT / 2 + 5);
        }

    }

    /**
     * 拿到验证码生成的字符串
     * @return
     */
    public String getCode() {
        return code;
    }
}
