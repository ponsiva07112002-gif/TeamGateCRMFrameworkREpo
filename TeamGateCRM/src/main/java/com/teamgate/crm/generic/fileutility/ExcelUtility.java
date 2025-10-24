package com.teamgate.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class is used to perform Excel related actions
 * 
 * @author Ponselvi
 */
public class ExcelUtility {

	/**
	 * This method is used to read test-script related data from excel
	 * 
	 * @param sheetname
	 * @param rownum
	 * @param cellnum
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public String getDataFromExcel(String sheetname, int rownum, int cellnum)
			throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream(
				"./TestData/TeamgateCRM_TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(sheetname).getRow(rownum).getCell(cellnum).toString();
		wb.close();
		return data;

	}

	/**
	 * This method is used to count the Row Number
	 * 
	 * @param sheetname
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public int getRowCount(String sheetname) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\Ponsiva\\eclipse-workspace\\TeamGateCRM\\TestData\\TeamgateCRM_TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int rowcount = wb.getSheet(sheetname).getLastRowNum();
		;
		wb.close();
		return rowcount;
	}

	/**
	 * This method is used to give data back to Excel
	 * 
	 * @param sheetname
	 * @param rownum
	 * @param cellnum
	 * @param data
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public void setDataIntoExcel(String sheetname, int rownum, int cellnum, String data) throws Throwable {
		try (FileInputStream fis = new FileInputStream(
				"C:\\Users\\Ponsiva\\eclipse-workspace\\TeamGateCRM\\TestData\\TeamgateCRM_TestScriptData.xlsx");
				Workbook wb = WorkbookFactory.create(fis)) {
			Sheet sheet = wb.getSheet(sheetname);
			if (sheet == null) {
				sheet = wb.createSheet(sheetname);
			}
			Row row = sheet.getRow(rownum);
			if (row == null) {
				row = sheet.createRow(rownum);
			}
			Cell cell = row.getCell(cellnum);
			if (cell == null) {
				cell = row.createCell(cellnum);
			}
			cell.setCellValue(data);

			try (FileOutputStream fos = new FileOutputStream(
					"C:\\Users\\Ponsiva\\eclipse-workspace\\TeamGateCRM\\TestData\\TeamgateCRM_TestScriptData.xlsx")) {
				wb.write(fos);
			}
			wb.close();
		} catch (Exception e) {
			System.out.println("Error writing data to excel: " + e.getMessage());
		}
	}
}
