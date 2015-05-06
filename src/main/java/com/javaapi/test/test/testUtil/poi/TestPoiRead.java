package com.javaapi.test.test.testUtil.poi;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;

public class TestPoiRead {

	private  static Map<String,String> map= new LinkedHashMap<>();

	@Test
	public void testPoiRead() throws Exception {
		String filePath = "C:\\Users\\hncw\\Desktop\\东北大区22.xls";
//		String filePath = this.getClass().getResource(file).getPath();
		HSSFWorkbook hss = new HSSFWorkbook(new FileInputStream(new File(
				filePath)));
		HSSFSheet sheetAt = hss.getSheetAt(4);
		iterateRow(sheetAt);
		dealAddressMap();
	}

	private void iterateRow(HSSFSheet sheetAt) {
		for (Row row : sheetAt) {
			iterateCell(row);
			System.out.println("--");
		}
	}

	private void iterateCell(Row row)  {
		try {
			Cell cell2 = row.getCell(1);
			String cellStringValue = getCellStringValue(cell2);
			 map.put(cellStringValue, cellStringValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void dealAddressMap() throws Exception {
		Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> entry = iterator.next();
			String key = entry.getKey();
			dealAddress(key);
		}
	}

	private void dealAddress(String cellStringValue) throws Exception {
		String url = getBaiKeUrl(cellStringValue);
		String address = JsoupUtil.getAddress(url);
		String shi = AddressUtil.getShi(address);
		if(shi == null) {
		shi = JsoupUtil.parseFromMap(cellStringValue);
		}
		if("北京市".equals(shi)) {
			shi=null;
		}
		System.out.println(cellStringValue+"&&=>"+shi);
	}

	private String getBaiKeUrl(String cellStringValue) {
		String baike = "http://baike.baidu.com/search?word="+cellStringValue;
		return baike;
	}

	private String getCellStringValue(Cell cell) {
		String cellVal = "";
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_BLANK:
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			break;
		case Cell.CELL_TYPE_ERROR:
			break;
		case Cell.CELL_TYPE_FORMULA:
			break;
		case Cell.CELL_TYPE_NUMERIC:
			cellVal = String.valueOf(cell.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_STRING:
			cellVal = cell.getStringCellValue();
			break;
		default:
			break;
		}
		return cellVal;
	}


}
