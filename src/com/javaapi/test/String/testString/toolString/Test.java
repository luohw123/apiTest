package com.javaapi.test.String.testString.toolString;



//public class Test {
//	public static void main(String[] args) throws Exception {
//		String aa = FileTools.getFileContent(new File(
//				"C:\\Users\\Administrator\\Desktop\\fudongkuang.txt"), "UTF-8");
//		BufferedReader is=new BufferedReader (new InputStreamReader(new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\fudongkuang.txt"))));
//		String temp ;
//		JSONObject joJsonObject=new JSONObject();
//		Map<String, String> map=new HashMap<String, String>();
//		String [] strArray =new String[200];
//		String aString="";
//		int length=0;
//		while ((temp = is.readLine())  != null) {
//			if(StringUtils.isNotBlank(temp)){
////				String type= temp.split("=>")[0];
////				String number =	temp.split("=>")[1];
////				map.put(number, type);
////				type = type.substring(type.indexOf("\'")+1,type.lastIndexOf("\'"));
////				number = number.substring(number.indexOf("\'")+1,number.lastIndexOf("\'"));
//////				System.out.println(type+"----"+number);
//////				map.put(number, type);
//////				joJsonObject.put(number, type);
////				joJsonObject.put(type, number);
////				strArray[length++]="\'"+type+"\'";
//				aString+=temp.trim();
//			}
//		}
//		System.out.println(aString);
////		System.out.println(Arrays.toString(strArray));
////		System.out.println(JSON.toJSON(aa).toString());
////		JSON.toJSON(map).toString();
////		JSON.toJSONString(map, SerializerFeature.UseSingleQuotes);
////		System.out.println(JSON.toJSONString(map));
////		System.out.println(JSON.toJSONString(joJsonObject, SerializerFeature.UseSingleQuotes));
//	}
//}