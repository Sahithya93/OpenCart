package com.wipro.opencart.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriteData {
	
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	Row row;
	FileOutputStream fos;
	FileInputStream fis;

	public ExcelWriteData(String createSheetName) throws Exception{
		//File file = new File(filePath);
		//fis = new FileInputStream(file);
		
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet(createSheetName);
		
	}
	
	public ExcelWriteData(int getSheet) throws Exception{
		//File file = new File(filePath);
		//fis = new FileInputStream(file);
		
		workbook = new XSSFWorkbook();
		sheet = workbook.getSheetAt(getSheet);
	
		
	}
	
	public void createRows(int rows){
		
		row=sheet.getRow(rows);
		if(row==null){
		row = sheet.createRow(rows);
		}
	}
	
	public void createCellsAndWriteData(int cells, String writeText) throws Exception{
		
		Cell cell = row.createCell(cells);
		cell.setCellValue(writeText);
		
	
	}
	
	public void writeTo(String filePath) throws Exception{
		//fis.close();
		fos = new FileOutputStream(filePath);
		workbook.write(fos);
	}
}
