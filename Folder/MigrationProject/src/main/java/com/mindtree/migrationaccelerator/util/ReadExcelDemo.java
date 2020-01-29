package com.mindtree.migrationaccelerator.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.mindtree.migrationaccelerator.entity.UserAccountDetails;
import com.mindtree.migrationaccelerator.entity.UserEmailDetails;
import com.mindtree.migrationaccelerator.entity.UserLocationDetails;
import com.mindtree.migrationaccelerator.entity.UserOtherDetails;
import com.mindtree.migrationaccelerator.entity.UserSkypeDetails;
import com.mindtree.migrationaccelerator.entity.UserWorkstationDetails;

public class ReadExcelDemo {

	//public static final String SAMPLE_XLSX_FILE_PATH = "D:/MigrationMwatch/Migration 1.0/FinalTemplateWithDummyData.XLSX";

	static Properties prop;

	static {
		try {
			prop = PropertiesReader.getPropertiesReaderInstance();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static List<UserOtherDetails> userOtherDetails = null;

	public List<UserOtherDetails> getList() {
		return userOtherDetails;
	}

	public Boolean processExcel(String SAMPLE_XLSX_FILE_PATH) throws IOException, InvalidFormatException {

		Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));

		Sheet sheet = workbook.getSheetAt(0);

		Map<String, Integer> map = getColumnNameAndIndexMap(sheet, 2);

		String[][] dataRecords = getExcelValues(sheet);

		List<UserLocationDetails> userLocDetails = new ArrayList<UserLocationDetails>();
		List<UserAccountDetails> userAccountDetails = new ArrayList<UserAccountDetails>();
		List<UserEmailDetails> userEmailDetails = new ArrayList<UserEmailDetails>();
		List<UserSkypeDetails> userSkypelDetails = new ArrayList<UserSkypeDetails>();
		List<UserWorkstationDetails> userWorkstationDetails = new ArrayList<UserWorkstationDetails>();
		// List<UserOtherDetails> userOtherDetails = new
		// ArrayList<UserOtherDetails>();
		userOtherDetails = new ArrayList<UserOtherDetails>();

		//DataRecordsToEntityConverter.getPropertyInstance();
		
		for (String[] rowData : dataRecords) {

			UserLocationDetails locDetails = DataRecordsToEntityConverter.createUserLocationDetails(rowData, map);
			UserAccountDetails accountDetails = DataRecordsToEntityConverter.createUserAccountDetails(rowData, map);
			UserEmailDetails emailDetails = DataRecordsToEntityConverter.createUserEmailDetails(rowData, map);
			UserSkypeDetails skypeDetails = DataRecordsToEntityConverter.createUserSkypeDetails(rowData, map);
			UserWorkstationDetails workstationDetails = DataRecordsToEntityConverter
					.createUserWorkstationDetails(rowData, map);
			UserOtherDetails otherDetails = DataRecordsToEntityConverter.createUserOtherDetails(rowData, map);

			userLocDetails.add(locDetails);
			userAccountDetails.add(accountDetails);
			userEmailDetails.add(emailDetails);
			userSkypelDetails.add(skypeDetails);
			userWorkstationDetails.add(workstationDetails);
			userOtherDetails.add(otherDetails);
		}
		workbook.close();
		return true;
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
		for (int i = 3; i < sheet.getPhysicalNumberOfRows(); i++) {
			Row row = sheet.getRow(i);
			if (row != null) {
				excelValues[rowCount] = new String[row.getPhysicalNumberOfCells()];
				int columnCount = 0;
				for (Cell cell : row) {
					if (cell != null)
						excelValues[rowCount][columnCount] = getCellValue(cell);
					else
						excelValues[rowCount][columnCount] = "";
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