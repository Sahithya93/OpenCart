package com.wipro.opencart.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelData {
	
	XSSFSheet sheet;
	XSSFWorkbook workbook;
	
	public ExcelData(String filePath, int sheetIndex) throws Exception{
		
		File file = new File(filePath);
		FileInputStream fis = new FileInputStream(file);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheetAt(sheetIndex);
	}
	
	public int numOfRows(){
		
		int rows_sheet = sheet.getLastRowNum()-sheet.getFirstRowNum();
		return rows_sheet;
	}
	
	
	public String getData(int rowNum,int cellNum){
		
		
		String data = sheet.getRow(rowNum).getCell(cellNum).getStringCellValue();
		return data;
	}
	/*@SuppressWarnings("deprecation")
	public String getData(int rowNum,int cellNum){
		Row row=sheet.getRow(rowNum);
		Cell cell=row.getCell(cellNum);
		
		switch(cell.getCellType()){
		case cell.CELL_TYPE_STRING:
			String data = row.getCell(cellNum).getStringCellValue();
			return data;	
			
		case cell.CELL_TYPE_NUMERIC:
			String numericData = String.valueOf(row.getCell(cellNum).getNumericCellValue());
			return numericData;
		}
		
		}*/
		
	}


