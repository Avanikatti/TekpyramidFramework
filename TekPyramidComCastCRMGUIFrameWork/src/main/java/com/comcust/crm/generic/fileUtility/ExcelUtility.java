package com.comcust.crm.generic.fileUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {

	public String togetDataFromExcelFile(String sheet,int row,int cell) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream("./Testdata\\TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(sheet).getRow(row).getCell(cell).getStringCellValue();
		wb.close();
		return data;
	}
	
	public int togetLastRowNum(String sheet) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream("./Testdata\\TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int lastRowNum = wb.getSheet(sheet).getLastRowNum();
		wb.close();
		return lastRowNum;
	}
	public void setDatBackToExcel(String sheet,int row,int cell,String value) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream("./Testdata\\TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Row rw = wb.getSheet(sheet).getRow(row);
		Cell cl = rw.createCell(cell);
	    cl.setCellType(CellType.STRING);
	    cl.setCellValue(value);
	    FileOutputStream fos=new FileOutputStream("./Testdata\\TestScriptData.xlsx");
	    wb.write(fos);
	    wb.close();
	}
}
