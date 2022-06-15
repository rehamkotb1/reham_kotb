package utilities;

import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
public class ExcelUtils {
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;
	
	
	// Set up excel input sheet path
		public static String getExcelInputPath(String filename) {
			String path = ExcelConfg.Path_ExcelSheets + filename;
			return path;
		}
		
		// Set up excel output sheet path
		public static String getExcelOutputPath(String filename) {
			String path = ExcelConfg.Path_ExcelSheets + filename;
			return path;
		}
		
		

		// set up excel sheet variables for input sheet
		public static XSSFSheet setUpExcelInputFile(String filename) throws Exception {
			// Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(getExcelInputPath(filename));
			// Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(ExcelConfg.SheetName_TestData);
			return ExcelWSheet;
		}
		
		
		// set up excel sheet variables for output sheet
		public static XSSFSheet setUpExcelOutputFile(String filename) throws Exception {
			// Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(getExcelOutputPath(filename));
			// Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(ExcelConfg.SheetName_Output);
			return ExcelWSheet;

		}
		
		
		// This method is to read the test data from the Excel cell, in this we are
		// passing parameters as Row num and Col num

		public static String getCellData(String filename, int RowNum, int ColNum) throws Exception {

			try {
				setUpExcelInputFile(filename);
				Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

				String CellData = Cell.getStringCellValue();

				return CellData;

			} catch (Exception e) {

				return "";

			}

		}
		
		
		// This method is to write in the Excel cell, Row num and Col num are the
		// parameters

		public static void setCellData(String filename, String CellValue, int RowNum, int ColNum) throws Exception {
			try {
				try {
					FileInputStream file = new FileInputStream(getExcelOutputPath(filename));
					ExcelWBook = new XSSFWorkbook(file);
					file.close();
				} catch (Exception e) {
					ExcelWBook = new XSSFWorkbook();
				}
				if (ExcelWBook.getSheet(ExcelConfg.SheetName_Output) == null) {
					ExcelWSheet = ExcelWBook.createSheet(ExcelConfg.SheetName_Output);
				} else {
					ExcelWSheet = ExcelWBook.getSheet(ExcelConfg.SheetName_Output);
				}
				// Row = ExcelWSheet.createRow(RowNum);

				Row = ExcelWSheet.getRow(RowNum);
				if (Row == null) {
					Row = ExcelWSheet.createRow(RowNum);
				}

				Cell = Row.getCell(ColNum, MissingCellPolicy.RETURN_BLANK_AS_NULL);

				if (Cell == null) {

					Cell = Row.createCell(ColNum);

					Cell.setCellValue(CellValue);

				} else {

					Cell.setCellValue(CellValue);

				}
				// Cell = Row.createCell(ColNum);
				// Cell.setCellValue(CellValue);
				FileOutputStream fileOut = new FileOutputStream(getExcelOutputPath(filename));
				ExcelWBook.write(fileOut);
				fileOut.flush();
				fileOut.close();
				ExcelWBook.close();
			} catch (Exception e) {

				throw (e);

			}
		}
		
		// delete out put sheet before start execution
		public static void deleteOutPutFile(String filename) {
			File outPutFile = new File(ExcelUtils.getExcelOutputPath(filename));
			if (outPutFile.delete()) {
				System.out.println("Output file deleted successfully");
			} else {
				System.out.println("Output file already deleted");
			}
		}

		// this method to print list into output sheet bassed on row num and column num 
		public static void updateOutputSheetWithListElements(String filename, ArrayList<String> listName, int rowNum, int colNum) throws Exception {
			for (int j = 0; j < listName.size(); j++) {
				ExcelUtils.setCellData(filename, listName.get(j), rowNum, j + colNum);
			}
		}
	
}
