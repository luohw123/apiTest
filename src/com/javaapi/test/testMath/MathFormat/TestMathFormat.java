package com.javaapi.test.testMath.MathFormat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ChoiceFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Locale;

import org.junit.Test;

public class TestMathFormat {
	/**
	 * http://blog.csdn.net/alanzyy/article/details/8465098
	 */
	/**
	 * 数字格式化方式1
	 */
	@Test
	public void DecimalFormat(){
		DecimalFormat  df =new DecimalFormat() ;
		// 可以不输出逗号
		df.setGroupingSize(0);
		df.setMinimumFractionDigits(2);
		df.setMaximumFractionDigits(2);
		// 这是通常意义上的四舍五入
		df.setRoundingMode(RoundingMode.HALF_UP);
		System.out.println(df.format(123456789.00400));
		System.out.println(df.format(123456789.00500));
	}
	/**
	 * 数字格式化方式2,比较难懂,有学习成本..</br>
	 * http://blog.csdn.net/neal1225/article/details/9332957
	 * 
	 */
	@Test
	public void DecimalFormat2(){
		DecimalFormat format=new DecimalFormat("###.00");
		DecimalFormat format2=new DecimalFormat(",##0.00");
		double number = 123123456789.000000000;
		System.out.println(format.format(number));
		System.out.println(format2.format(number));
	}
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
    	//TODO 不带逗号得输出
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.CHINA);
        nf.setMinimumFractionDigits(2);
//        nf.setMaximumIntegerDigits(2);
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
    public void testNumberFormat(){
    	NumberFormat finalSpFomat= NumberFormat.getNumberInstance();
		finalSpFomat.setMaximumFractionDigits(2);
		finalSpFomat.setRoundingMode(RoundingMode.FLOOR);
		
		System.out.println(finalSpFomat.format(new BigDecimal("123456.66666666")));
		System.out.println(finalSpFomat.format(Double.parseDouble("123456.66666666")));
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

	@Test
	public void otherTest() {
		BigDecimal bi = new BigDecimal(112222);
		System.out.println(bi.toString());
	}
}
