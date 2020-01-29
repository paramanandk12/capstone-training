package com.mindtree.migrationaccelerator.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.mindtree.migrationaccelerator.service.IMasterSheetContentOperation;
import com.mindtree.migrationaccelerator.utilinterface.IMasterSheetReader;

public class MasterSheetContentOperation implements IMasterSheetContentOperation {

	final static Logger logger = Logger.getLogger(ProjectManagementServiceImpl.class);

	@Qualifier("")
	@Autowired
	private IMasterSheetReader excelReader;

	@Qualifier("")
	@Autowired
	private IMasterSheetReader csvReader;

	public IMasterSheetReader getMasterSheetInsatnce(String fileName) {
		IMasterSheetReader reader = null;
		if (fileName.contains(".xlsx") || fileName.contains("xls"))
			reader = excelReader;
		else if (fileName.contains(".csv"))
			reader = csvReader;
		return reader;

	}

}
