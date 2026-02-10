package SpotifyPom;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class SpotipyExcel {

	public String togetDataFromExcelFile(String sheet,int row,int cell) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream("C:\\Users\\Basamma\\OneDrive\\Desktop\\DDT\\SpotifuData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(sheet).getRow(row).getCell(cell).getStringCellValue();
		wb.close();
		return data;
	}
	
	public int togetLastRowNum(String sheet) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream("C:\\Users\\Basamma\\OneDrive\\Desktop\\DDT\\SpotifuData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int lastRowNum = wb.getSheet(sheet).getLastRowNum();
		wb.close();
		return lastRowNum;
	}
	public void setDatBackToExcel(String sheet,int row,int cell,String value) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream("C:\\Users\\Basamma\\OneDrive\\Desktop\\DDT\\SpotifuData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		 Sheet sh = wb.getSheet(sheet);
				Row rw = sh.getRow(row);
		if (rw == null) {
		    rw = sh.createRow(row);
		}

		Cell cl = rw.getCell(cell);
		if (cl == null) {
		    cl = rw.createCell(cell);
		}
	    cl.setCellType(CellType.STRING);
	    cl.setCellValue(value);
	    FileOutputStream fos=new FileOutputStream("C:\\Users\\Basamma\\OneDrive\\Desktop\\DDT\\SpotifuData.xlsx");
	    wb.write(fos);
	    wb.close();
}
}
