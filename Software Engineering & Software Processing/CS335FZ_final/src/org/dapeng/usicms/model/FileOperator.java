package org.dapeng.usicms.model;

import java.io.File;

import org.dapeng.usicms.handler.ProjectLevelConfigs;

public class FileOperator {

	public static void createProjFile(String fileName) {
		try {
			File projFile = new File(fileName);
			projFile.createNewFile();
			ProjectLevelConfigs.getProjectLevelConfigs().projectName = fileName;

		} catch (Exception e) {
			ProjectLevelConfigs.getProjectLevelConfigs().projectName = "";
		}
	}

}