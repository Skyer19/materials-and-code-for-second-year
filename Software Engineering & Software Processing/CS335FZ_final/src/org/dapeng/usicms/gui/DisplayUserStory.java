/*******************************************************************************
 * Copyright (C) 2021 Dapeng Dong
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 ******************************************************************************/
package org.dapeng.usicms.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import org.dapeng.usicms.handler.ProjectLevelConfigs;
import org.dapeng.usicms.model.UserStory;
import org.dapeng.usicms.model.UserStoryType;

public class DisplayUserStory extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField userStoryNameTextField;
	private JTextField userStoryIDTextField;
	private JTextField estDuratoinTextField;
	private JTextField estEffortTextField;
	private JTextField userStoryCreatorTextField;
	private JTextField storyPointTextField;
	private JTextPane userStoryDescTextPane;
	private JTextField createdTextField;
	private JTextField lastModifiedTextField;
	private JTextField viwStatusTextField;
	private JTextField projectTextField;
	private JComboBox userStoryTypeComboBox;

	/**
	 * Create the dialog.
	 */
	public DisplayUserStory(String idName) {
		String id = idName.split("--")[0];
		UserStory us = new UserStory();

		for (UserStory singleUs : ProjectLevelConfigs.getProjectLevelConfigs().userStories) {
			if (singleUs.getId().equalsIgnoreCase(id))
				us = singleUs;
		}

		setResizable(false);
		setBounds(100, 100, 555, 369);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panelTop = new JPanel();
			contentPanel.add(panelTop, BorderLayout.NORTH);
			panelTop.setLayout(new GridLayout(0, 6, 0, 0));
			{
				JLabel lblNewLabel = new JLabel("Story Name:");
				panelTop.add(lblNewLabel);
			}
			{
				userStoryNameTextField = new JTextField(us.getName());
				userStoryNameTextField.setEditable(false);
				panelTop.add(userStoryNameTextField);
				userStoryNameTextField.setColumns(15);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("ID:");
				panelTop.add(lblNewLabel_1);
			}
			{
				userStoryIDTextField = new JTextField(us.getId());
				userStoryIDTextField.setEditable(false);
				panelTop.add(userStoryIDTextField);
				userStoryIDTextField.setColumns(6);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Type:");
				panelTop.add(lblNewLabel_2);
			}
			{
				JComboBox userStoryTypeComboBox = new JComboBox();
//				userStoryTypeComboBox.setSelectedIndex(1);
				userStoryTypeComboBox.setModel(new DefaultComboBoxModel(UserStoryType.values()));
				userStoryTypeComboBox.setSelectedIndex(0);
				panelTop.add(userStoryTypeComboBox);

			}
			{
				JLabel label = new JLabel("");
				panelTop.add(label);
			}
			{
				JLabel label = new JLabel("");
				panelTop.add(label);
			}
			{
				JLabel label = new JLabel("");
				panelTop.add(label);
			}
			{
				JLabel label = new JLabel("");
				panelTop.add(label);
			}
		}
		{
			JPanel panelBottom = new JPanel();
			contentPanel.add(panelBottom, BorderLayout.SOUTH);
			panelBottom.setLayout(new GridLayout(3, 4, 0, 0));

			JLabel lblNewLabel_7 = new JLabel("Est. Duration:");
			panelBottom.add(lblNewLabel_7);

			estDuratoinTextField = new JTextField(String.valueOf(us.getDuration()));
			estDuratoinTextField.setEditable(true);
			panelBottom.add(estDuratoinTextField);
			estDuratoinTextField.setColumns(10);

			JLabel lblNewLabel_8 = new JLabel("Est. Effort:");
			panelBottom.add(lblNewLabel_8);

			estEffortTextField = new JTextField(String.valueOf(us.getEstimate()));
			estEffortTextField.setEditable(true);
			panelBottom.add(estEffortTextField);
			estEffortTextField.setColumns(10);

			JLabel lblNewLabel_3 = new JLabel("Reporter:");
			panelBottom.add(lblNewLabel_3);
			userStoryCreatorTextField = new JTextField(us.getCreator());
			userStoryCreatorTextField.setEditable(true);
			panelBottom.add(userStoryCreatorTextField);
			userStoryCreatorTextField.setColumns(10);

			//
			JLabel lblNewLabel_12 = new JLabel("Project:");
			panelBottom.add(lblNewLabel_12);
			projectTextField = new JTextField(us.getProject());
			projectTextField.setEditable(true);
			panelBottom.add(projectTextField);
			projectTextField.setColumns(10);
			//
			JLabel lblNewLabel_4 = new JLabel("Story Point:");
			panelBottom.add(lblNewLabel_4);

			storyPointTextField = new JTextField(String.valueOf(us.getStoryPoint()));
			storyPointTextField.setEditable(true);
			panelBottom.add(storyPointTextField);
			storyPointTextField.setColumns(10);
			{
				JLabel lblNewLabel_5 = new JLabel("Status:");
				panelBottom.add(lblNewLabel_5);
			}

			viwStatusTextField = new JTextField(us.getStatus());
			viwStatusTextField.setEditable(false);
			panelBottom.add(viwStatusTextField);
			viwStatusTextField.setColumns(10);

			//

			SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm:ss");
			// lab3: set format that we want to show on the tablet
			long time = System.currentTimeMillis();
			Date date = new Date(time);

			// the time that we last modified the story was come from us.getLastModified
			JLabel lblNewLabel_10 = new JLabel("Last Modified:");
			panelBottom.add(lblNewLabel_10);
			lastModifiedTextField = new JTextField(
					format.format(us.getLastModified() == null ? date : us.getLastModified()));

			lastModifiedTextField.setEditable(false);
			panelBottom.add(lastModifiedTextField);
			lastModifiedTextField.setColumns(10);

			// the time that we created the story was come from us.getTimeStamp

			JLabel lblNewLabel_11 = new JLabel("created:");
			panelBottom.add(lblNewLabel_11);
			createdTextField = new JTextField(format.format(us.getTimeStamp() == null ? date : us.getTimeStamp()));
			createdTextField.setEditable(false);
			panelBottom.add(createdTextField);
			createdTextField.setColumns(10);
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.WEST);
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				JLabel lblDescription = new JLabel("Description:");
				lblDescription.setBounds(0, 0, 106, 15);
				panel.add(lblDescription);
			}

			userStoryDescTextPane = new JTextPane();
			userStoryDescTextPane.setText(us.getDescription());
			userStoryDescTextPane.setEditable(true);
			userStoryDescTextPane.setBounds(0, 23, 523, 166);
			panel.add(userStoryDescTextPane);
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.EAST);
			panel.setLayout(new GridLayout(4, 2, 0, 0));
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Close");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// modify the content in now user story

						UserStory us = new UserStory();
						for (UserStory singleUs : ProjectLevelConfigs.getProjectLevelConfigs().userStories) {
							if (singleUs.getId().equalsIgnoreCase(id))
								us = singleUs;
						}
						us.setCreator(userStoryCreatorTextField.getText());
//						us.setDuration(Integer.parseInt(estDuratoinTextField.getText()));
//						us.setEstimate(Integer.parseInt(estEffortTextField.getText()));
						us.setId(userStoryIDTextField.getText());
						us.setName(userStoryNameTextField.getText());
						us.setStoryPoint(Integer.parseInt(storyPointTextField.getText()));

//						us.setType(userStoryTypeComboBox.getSelectedItem().toString());
//						System.out.println(userStoryTypeComboBox.getSelectedItem().toString());
						us.setDescription(userStoryDescTextPane.getText());
						us.setProject(ProjectLevelConfigs.getProjectLevelConfigs().projectName);

						long time = System.currentTimeMillis();
						Date date = new Date(time);
						for (UserStory singleUs : ProjectLevelConfigs.getProjectLevelConfigs().userStories) {
							if (singleUs.getId().equalsIgnoreCase(id))
								singleUs.setLastModified(date);
						}
						// set the last modified time, the time was when you clicked "close" button
						dispose();
						// close the dialog.
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
