package com.javaapi.test.testMMM;

import java.math.RoundingMode;
import java.text.ChoiceFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;

import org.junit.Test;

public class TestMathFormat {
    @Test
    public void NumberFormat() {
        NumberFormat nf = NumberFormat.getNumberInstance();
        Integer integer = 1234567890;
        float fl = 223123.4560000f;
        double doub = 223123.4560000;
        System.out.println(nf.format(integer));
        System.out.println(nf.format(fl));
        System.out.println(nf.format(doub));
    }

    /**
     * setMinimumFractionDigits 用于format 四舍五入取整数
     * 
     * @throws ParseException
     */
    @Test
    public void Scale() throws ParseException {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMinimumFractionDigits(2);
        nf.setMaximumIntegerDigits(2);
        Integer integer = 1234567800;
        float fl = 223823.4562000099f;
        double doub = 223123.456000099;
        System.out.println(nf.format(integer));
        System.out.println(nf.format(fl));
        System.out.println(nf.format(doub));

        nf.setMaximumFractionDigits(0);
        nf.setRoundingMode(RoundingMode.FLOOR);
        System.out.println(nf.format(12.6655));
        System.out.println(nf.parse("12.665"));

    }

    /**
     * 四舍五入,roundingmode 跟BigDecimal有关
     */
    @Test
    public void RoundingMode() {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setRoundingMode(java.math.RoundingMode.CEILING);
        float fl = 3823.4562f;
        double doub = 223123.456000099;
        System.out.println(nf.format(fl));
        System.out.println(nf.format(doub));

        // 取2位小数,对第三位四舍五入
        nf.setRoundingMode(RoundingMode.HALF_UP);
        nf.setMaximumFractionDigits(2);
        System.out.println(nf.format(32.005));
    }

    /**
     * NumberFormat中很对设置对parse都不起作用,使用前需要额外测试下
     */
    @Test
    public void parse() {
        NumberFormat nf = NumberFormat.getNumberInstance();
        try {
            System.out.println(nf.parse("123.35611"));
            System.out.println(nf.parse("..456789", new ParsePosition(2)));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void CurrencyFormat() {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        Integer integer = 1234567890;
        float fl = 223123.4560000f;
        double doub = 223123.4560000;
        System.out.println(nf.format(integer));
        System.out.println(nf.format(fl));
        System.out.println(nf.format(doub));
    }

    @Test
    public void PercentFormat() {
        NumberFormat nf = NumberFormat.getPercentInstance();
        Integer integer = 1234567890;
        float fl = 223123.4560000f;
        double doub = 223123.4560000;

        float xiaoshu = 1.5000f;
        float zhengshu = 2.000f;
        System.out.println(nf.format(integer));
        System.out.println(nf.format(fl));
        System.out.println(nf.format(doub));
        System.out.println(nf.format(xiaoshu));
        System.out.println(nf.format(zhengshu));
    }

    @Test
    public void ChoiceFormat() {
        double[] limits = { 1, 2, 3, 4, 5, 6, 7 };
        String[] dayOfWeekNames = { "Sun", "Mon", "Tue", "Wed", "Thur", "Fri",
                "Sat" };
        ChoiceFormat form = new ChoiceFormat(limits, dayOfWeekNames);
        ParsePosition status = new ParsePosition(0);
        for (double i = 0.0; i <= 8.0; ++i) {
            status.setIndex(0);
            System.out.println(i + " -> " + form.format(i) + " -> "
                    + form.parse(form.format(i), status));
        }

    }

}
