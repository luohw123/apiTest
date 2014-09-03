package com.javaapi.test.test.testBit;

public class Test {
	public static int feature1 =1<<1;
	public static int feature2 =1<<2;
	public static int feature3 =1<<3;
	public static int feature4 =1<<4;
	public static int feature5 =1<<5;
	public static int feature6 =1<<6;
	public static int feature7 =1<<7;
	public static int features =0;
	static{
		int feature =0;
		feature|=feature1;
		feature|=feature2;
		feature|=feature3;
		feature|=feature4;
		feature|=feature5;
		feature|=feature6;
		feature|=feature7;
		features =feature;
	}
	/**一种形式
	 * @param feature
	 * @return
	 */
	public Boolean hasFeature(int feature){
		return (Test.features & feature)!=0; 
	}
	/**另一种形式
	 * @param feature
	 * @return
	 */
	public Boolean hasFeatureOther(int feature){
		return (Test.features & feature)==feature; 
	}
	
	public void addFeature(int feature){
		features |=feature;
	}
	public void removeFeature(int feature) {
        features &= ~feature;
    
	}
public static void main(String[] args) {
	Test test=new Test();
	System.out.println("原始特性==>"+Integer.toBinaryString(features));
	System.out.println("有特性1么==>"+test.hasFeature(feature1));
	test.removeFeature(feature1);
	System.out.println("移除特性1==>"+Integer.toBinaryString(features));
	test.addFeature(feature1);
	System.out.println("添加特性1==>"+Integer.toBinaryString(features));
	System.out.println(test.hasFeature(feature1));
	System.out.println(test.hasFeature(feature2));
	System.out.println("最终==>"+Integer.toBinaryString(features));
}
}
