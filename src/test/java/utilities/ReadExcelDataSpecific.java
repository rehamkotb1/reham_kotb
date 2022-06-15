/**
 * 
 */
package utilities;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadExcelDataSpecific {
	
	 // Read specific data from excel sheet
	public ReadExcelDataSpecific() {
		// TODO Auto-generated constructor stub
	}
	
	public String ReadCellData(int vRow, int vColumn , String fileName) {  
		String value=null;          //variable for storing the cell value  
		Workbook wb=null;           //initialize Workbook null  
		try  
		{  
		//reading data from a file in the form of bytes  

			FileInputStream files =new FileInputStream(ExcelUtils.getExcelInputPath(fileName));
		//constructs an XSSFWorkbook object, by buffering the whole stream into the memory  
		wb=new XSSFWorkbook(files);  
		}  
		catch(FileNotFoundException e)  
		{  
		e.printStackTrace();  
		}  
		catch(IOException e1)  
		{  
		e1.printStackTrace();  
		}  
		Sheet sheet=wb.getSheetAt(0);   //getting the XSSFSheet object at given index  
		Row row=sheet.getRow(vRow); //returns the logical row  
		Cell cell=row.getCell(vColumn); //getting the cell representing the given column  
		value=cell.getStringCellValue();    //getting cell value  
		return value;               //returns the cell value  
		} 
	
	

}
