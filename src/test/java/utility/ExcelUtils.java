package utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;
	private static XSSFRow row;
	private static XSSFCell cell;
	
	public static void setExcelPath(String sheetName, String path) throws Exception {
		
		FileInputStream fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
	}
	
	
	public static String getCellData(int rownum, int column) {
		
		return sheet.getRow(rownum).getCell(column).getStringCellValue();
		
	}
	
	public static String [][] getAllExcelData(){
		int rowNum = sheet.getLastRowNum();
		int colNum = 2;
		String[][] Table = new String[rowNum+1][colNum];
		System.out.println(rowNum);
		for (int i=0; i<rowNum+1; i++)
		{
			for (int j=0; j<colNum; j++)
			{
				Table[i][j] = ExcelUtils.getCellData(i, j);
				System.out.println(Table[i][j]);
			}
		}
		return Table;
	}
	
	public static void setExcelData(int rowNum, int colNum, String cellData) throws Exception {
		row = sheet.getRow(rowNum);
		
		cell = row.getCell(colNum, MissingCellPolicy.RETURN_BLANK_AS_NULL);
		if (cell == null) {
			cell = row.createCell(colNum);
			cell.setCellValue(cellData);
		}
		else {
			cell.setCellValue(cellData);
		}
		
		
		FileOutputStream fos = new FileOutputStream("C:\\Users\\A06438_p5.Training\\Desktop\\SeleniumAdvance\\TestMeApp\\LoginData.xlsx");
		workbook.write(fos);
		
		fos.flush();
		fos.close();
	}
	
}