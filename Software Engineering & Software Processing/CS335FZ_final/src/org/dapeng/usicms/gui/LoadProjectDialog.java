package org.dapeng.usicms.gui;

import java.awt.FileDialog;
import java.awt.Frame;

import javax.swing.JDialog;

import org.dapeng.usicms.handler.ProjectLevelConfigs;
import org.dapeng.usicms.model.UserStory;

public class LoadProjectDialog extends JDialog {

	public LoadProjectDialog() {

		Frame LP = null;
		FileDialog fdopen = new FileDialog(LP, "Load Project", FileDialog.LOAD);
		fdopen.setVisible(true);
		String filename = fdopen.getDirectory() + fdopen.getFile();
		ProjectLevelConfigs.getProjectLevelConfigs().projectName = fdopen.getFile();
		UserStory us = new UserStory();
		us.readUserStory();

	}
}
