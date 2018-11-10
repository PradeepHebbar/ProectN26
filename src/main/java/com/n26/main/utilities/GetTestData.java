package com.n26.main.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @description : 
 * @author pradeep.jp
 *
 */
public class GetTestData {
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private String path = "testData\\";
	

	public Object[][] getExcelData(String fileName) throws FileNotFoundException,IOException {
		Object[][] arrayExcelData = null;
		Hashtable<String, String> hashtable = null;
		try {
			FileInputStream ExcelFile = new FileInputStream(path+fileName+".xlsx");
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheetAt(0);
			int cj, ci;
			int totalNoOfCols = ExcelWSheet.getRow(0).getPhysicalNumberOfCells();
			int totalNoOfRows = ExcelWSheet.getLastRowNum();
			arrayExcelData = new Object[totalNoOfRows][1];
			ci = 0;
			for (int i = 1; i <= totalNoOfRows; i++, ci++) {
				cj = 0;
				hashtable = new Hashtable<String, String>();
				for (int j = 1; j < totalNoOfCols; j++, cj++) {
					hashtable.put(getCellData(0, j), getCellData(i, j));
					}
				arrayExcelData[ci][0] = hashtable;
			}
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException(e.getMessage());
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		
		return arrayExcelData;
	}

	public static String getCellData(int RowNum, int ColNum) throws Exception {
		try {
			String CellData;
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			int dataType = Cell.getCellType();
			switch (dataType) {
			case 0:
				CellData = Double.toString(Cell.getNumericCellValue());
				return CellData;
			case 3:
				return "";
			default:
				CellData = Cell.getStringCellValue();
				return CellData;

			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
