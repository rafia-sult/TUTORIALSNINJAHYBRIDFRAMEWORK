package com.tutorials.ninja.qa.testdata;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class SupplyTestDataFromExcel {

	public static FileInputStream ip;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;

	@DataProvider(name = "TutorialsNinjaDataProviderSupply")
	public static Object[][] dataSupplyFrom2DimensionalObjectArray() {

		Object[][] data = { { "rafselenium1@gmail.com", "Selenium@123" },
							{ "rafselenium2@gmail.com", "Selenium@123" },
							{ "rafselenium3@yahoo.com", "Selenium@123" },
							{ "rafselenium4@yahoo.com", "Selenium@123"}};

		return data;
	}

	@DataProvider(name = "TutorialsExcelDataWithDataProvider")
	public static Object[][] excelSheetDataSupply() throws Exception {
		Object[][] data = SupplyTestDataFromExcel.getTutorialsNinjaDataFromExcelSheet("Login");
		return data;
	}

	public static Object[][] getTutorialsNinjaDataFromExcelSheet(String sheetName) throws Exception {
		ip = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\test\\java\\com\\tutorials\\ninja\\qa\\testdata\\tutorialsNinjaValidCredentials.xlsx");
		workbook = new XSSFWorkbook(ip);
		sheet = workbook.getSheet(sheetName);
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[rows][cols];
		for (int i = 0; i < rows; i++) {
			XSSFRow row = sheet.getRow(i + 1);

			for (int j = 0; j < cols; j++) {
				XSSFCell cell = row.getCell(j);
				CellType celltype = cell.getCellType();

				switch (celltype) {
				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;

				case NUMERIC:
					data[i][j] = Integer.toString((int) cell.getNumericCellValue());
					break;

				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;
				}

			}

		}
		return data;

	}
}
