package utilities;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ExcelReader {

	public Object[][] getExcelData(String filePath, int sheetindex, boolean titleRowExists) throws IOException {
		try (InputStream inp = new FileInputStream(filePath)) {
			Workbook wb = WorkbookFactory.create(inp);
			Sheet sheet = wb.getSheetAt(sheetindex);

			int numRows = sheet.getLastRowNum() + 1;
			int numCols = sheet.getRow(0).getLastCellNum();

			int rowIndex = 0;
			if (titleRowExists) {
				numRows--;
				rowIndex = 1;
			}

			String[][] arrayExcelData = new String[numRows][numCols];
			for (int i = 0; i < numRows; i++) {
				for (int j = 0; j < numCols; j++) {
					Row row = sheet.getRow(rowIndex);
					if (row.getCell(j) != null)
						arrayExcelData[i][j] = row.getCell(j).toString();
					else
						arrayExcelData[i][j] = "";
				}
				rowIndex++;
			}

			return arrayExcelData;
		} catch (FileNotFoundException e) {
			System.out.println("Test Data file not found.treminating process!!: check file path: " + filePath);
			System.exit(0);
		}
		return null;

	}
}
