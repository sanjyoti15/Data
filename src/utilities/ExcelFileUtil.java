package utilities;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtil {
	//create global object for excel
	XSSFWorkbook wb;
	//create constructor for reading excel path
	public ExcelFileUtil(String excelpath) throws Throwable
	{
		FileInputStream fi = new FileInputStream(excelpath);
		wb= new XSSFWorkbook(fi);
	}
	//count no of rows in a sheet
	public int rowCount(String sheetName)
	{
		return wb.getSheet(sheetName).getLastRowNum();
	}
	//get celltype data
	public String getCellData(String sheetName,int row,int column)
	{
		String data="";
		if(wb.getSheet(sheetName).getRow(row).getCell(column).getCellType()== Cell.CELL_TYPE_NUMERIC)
		{
			int celldata = (int) wb.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();
			data = String.valueOf(celldata);
		}
		else
		{
			data = wb.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
		}
		return data;
	}
	//method for set cell data
	public void setCellData(String sheetName,int row,int column, String status,String writeexcel)throws Throwable
	{
		//getsheet from workbook
		XSSFSheet ws = wb.getSheet(sheetName);
		//get row from sheet
		XSSFRow rownum = ws.getRow(row);
		//get column from row
		XSSFCell cell = rownum.createCell(column);
		//write status
		cell.setCellValue(status);
		//set formating for statustext
		if(status.equalsIgnoreCase("pass"))
		{
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont  font = wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rownum.getCell(column).setCellStyle(style);
		}

		else if(status.equalsIgnoreCase("fail"))
		{
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont  font = wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rownum.getCell(column).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("blocked"))
		{
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont  font = wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rownum.getCell(column).setCellStyle(style);
		}

		FileOutputStream fo = new FileOutputStream(writeexcel);
		wb.write(fo);
	}

	public static void main(String[] args) throws Throwable {
		// actual use of above written method create an object of class
		ExcelFileUtil xl = new ExcelFileUtil("e://sample1.xlsx");
		//count no. of rows
		int rc = xl.rowCount("empdata");
		System.out.println("no of rows" +rc);
		//read all cells
		for(int i =1; i<=rc;i++)
		{
			String eid = xl.getCellData("empdata",i,0);
			String fname = xl.getCellData("empdata",i,1);
			String mname = xl.getCellData("empdata",i,2);
			String lname = xl.getCellData("empdata",i,3);
			System.out.println(eid+"  "+fname+"     "+mname+"   "+lname);
			//write status into file
			xl.setCellData("empdata",i,4,"pass","e://results.xlsx");
			//xl.setCellData("empdata",i,4,"fail","e://results.xlsx");
			//xl.setCellData("empdata",i,4,"blocked","e://results.xlsx");


		}


	}

}
