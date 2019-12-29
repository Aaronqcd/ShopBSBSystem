package org.jeecg.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;

public class ToolsUtil {
    /**
     * 生成length位数字
     * @param length
     * @return
     */
    public static String getRandomNo(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            val += String.valueOf(random.nextInt(10));
        }
        return val;
    }

    /**
     * 随机生成带两位小数的价格
     * @return
     */
    public static BigDecimal getRandomPrice() {
        double price = new Random().nextDouble()*10;
        DecimalFormat df = new DecimalFormat("0.00");
        String str = df.format(price);
        price = Double.parseDouble(str);
        return BigDecimal.valueOf(price);
    }

    /**
     * 价格保留2位小数
     * @param price
     * @return
     */
    public static BigDecimal getFormatPrice(BigDecimal price) {
        DecimalFormat df = new DecimalFormat("0.00");
        String str = df.format(price);
        return new BigDecimal(str);
    }
}
