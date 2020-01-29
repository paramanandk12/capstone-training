package com.mindtree.migrationaccelerator.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Component;

import com.mindtree.migrationaccelerator.dto.ProcessedFileDTO;

@Component
public class ExcelReader {

	final static Logger logger = Logger.getLogger(ExcelReader.class);
	static Properties prop;

	public ProcessedFileDTO processExcel(String SAMPLE_XLSX_FILE_PATH) throws IOException, InvalidFormatException {

		ProcessedFileDTO processedFileDTO = new ProcessedFileDTO();

		logger.info("Processing excel started !!!");
		prop = PropertiesReader.getPropertiesReaderInstance();
		Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));
		Sheet sheet = workbook.getSheetAt(0);
		Map<String, Integer> map = getColumnNameAndIndexMap(sheet, 2);

		String[][] dataRecords2d = getExcelValues(sheet);
		processedFileDTO.setDataRecords2D(dataRecords2d);
		processedFileDTO.setMapColumnAndIndex(map);
		workbook.close();
		return processedFileDTO;

	}

	private static Map<String, Integer> getColumnNameAndIndexMap(Sheet sheet, int colNameIndex) {
		// Create map to store column name and index.
		Map<String, Integer> map = new HashMap<String, Integer>();

		Row secondRow = sheet.getRow(colNameIndex);
		short indexOfSecondRowFirstCell = secondRow.getFirstCellNum();
		short indexOfSecondRowLastCell = secondRow.getLastCellNum();
		for (short colIx = indexOfSecondRowFirstCell; colIx < indexOfSecondRowLastCell; colIx++) {
			Cell cell = secondRow.getCell(colIx);
			map.put(getCellValue(cell), cell.getColumnIndex());
		}
		return map;
	}

	// creating 2D Array of records
	public static String[][] getExcelValues(Sheet sheet) throws FileNotFoundException, IOException {

		String[][] excelValues = new String[sheet.getPhysicalNumberOfRows() - 3][];
		int rowCount = 0;
		Row headerRow = sheet.getRow(2);
		for (int i = 3; i < sheet.getPhysicalNumberOfRows(); i++) {
			Row row = sheet.getRow(i);
			if (row != null) {
				excelValues[rowCount] = new String[headerRow.getLastCellNum()];
				int columnCount = 0;
				for (int j = 0; j < headerRow.getLastCellNum(); j++) {
					Cell cell = row.getCell(j);
					if (cell != null) {
						excelValues[rowCount][columnCount] = getCellValue(cell);
					}
					else {
						excelValues[rowCount][columnCount] = "";
					}
					columnCount++;
				}
				rowCount++;
			}
		}
		return excelValues;
	}

	// using cell value get string
	private static String getCellValue(Cell cell) {
		String retVal = null;

		switch (cell.getCellTypeEnum()) {
		case STRING:
			retVal = cell.getStringCellValue().trim();
			break;
		case NUMERIC:
			double val = cell.getNumericCellValue();
			retVal = Double.toString(val);
			break;
		case BOOLEAN:
			boolean bval = cell.getBooleanCellValue();
			retVal = Boolean.toString(bval);
			break;
		case BLANK:
			retVal = "";
			break;
		case FORMULA:
			retVal = cell.getCellFormula().toString();
			break;
		default:
		}
		return retVal;
	}
}