package com.wipro.opencart.utilities;

public class SampleWriteData {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		ExcelWriteData excelWriteData = new ExcelWriteData("Sample");
		
		for(int i=0;i<2;i++){
			excelWriteData.createRows(i);
			for(int j=0;j<1;j++){
				
				excelWriteData.createCellsAndWriteData(j, "Selenium");
				
			}
		}
		excelWriteData.writeTo("D:/Test Data/OpenCart.xlsx");
	}

}
