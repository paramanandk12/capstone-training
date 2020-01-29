package com.mindtree.migrationaccelerator.util;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.mindtree.migrationaccelerator.utilinterface.IMasterSheetReader;

@Component
public class CSVReader implements IMasterSheetReader {

	@Override
	public Map<String, Integer> getColumnNameAndIndex() {

		return null;
	}

}
