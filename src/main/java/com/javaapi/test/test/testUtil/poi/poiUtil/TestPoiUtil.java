package com.javaapi.test.test.testUtil.poi.poiUtil;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import com.javaapi.test.test.testUtil.poi.TestPoi;

public class TestPoiUtil {
	@Test
	public void testRead() throws Exception {
		String file = "testXls.xls";
		String filePath = TestPoi.class.getResource(file).getPath();
		HSSFWorkbook readDocument = PoiUtil.readDocument(filePath);
		List<List<List<String>>> allInfo = PoiUtil.getAllSheetInfo(readDocument);
	}
}
