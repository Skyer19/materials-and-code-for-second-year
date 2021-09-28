package org.dapeng.usicms.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.dapeng.usicms.model.Role;

public class Register extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField userNameTextField;
	private JTextField userIDTextField;
	private JComboBox userTypeComboBox;
	private JTextField userPasswordtextField;
	private JLabel lblNewLabel_4;

	/**
	 * Create the dialog.
	 */
	public Register() {
		setResizable(false);
		setBounds(100, 100, 555, 341);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		{
			JPanel panelTop = new JPanel();
			contentPanel.add(panelTop, BorderLayout.NORTH);
			panelTop.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		}

		{
			JPanel panelBottom = new JPanel();
			contentPanel.add(panelBottom, BorderLayout.SOUTH);
			panelBottom.setLayout(new GridLayout(2, 4, 0, 0));

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
				JLabel lblNewLabel = new JLabel("User Name:");
				lblNewLabel.setBounds(46, 76, 93, 15);
				panel.add(lblNewLabel);
			}
			{
				userNameTextField = new JTextField();
				userNameTextField.setBounds(128, 72, 159, 21);
				panel.add(userNameTextField);
				userNameTextField.setColumns(15);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("ID:");
				lblNewLabel_1.setBounds(46, 110, 19, 15);
				panel.add(lblNewLabel_1);
			}
			{
				userIDTextField = new JTextField();
				userIDTextField.setBounds(128, 105, 159, 21);
				panel.add(userIDTextField);
				userIDTextField.setColumns(6);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Type:");
				lblNewLabel_2.setBounds(46, 148, 70, 15);
				panel.add(lblNewLabel_2);
			}
			{
				userTypeComboBox = new JComboBox();
				userTypeComboBox.setBounds(128, 146, 159, 21);
				panel.add(userTypeComboBox);
				userTypeComboBox.setModel(new DefaultComboBoxModel(Role.values()));
			}

			JLabel lblNewLabel_3 = new JLabel("REGISTER");
			lblNewLabel_3.setBounds(226, 30, 110, 15);
			panel.add(lblNewLabel_3);
			{
				JLabel lblNewLabel_1 = new JLabel("PASSWORD");
				lblNewLabel_1.setBounds(46, 189, 69, 15);
				panel.add(lblNewLabel_1);
			}
			{
				userPasswordtextField = new JPasswordField();
				userPasswordtextField.setColumns(15);
				userPasswordtextField.setBounds(128, 185, 159, 21);
				panel.add(userPasswordtextField);
			}

			lblNewLabel_4 = new JLabel("ID HAS BEEN CREATED!!!!!");
			lblNewLabel_4.setFont(new Font("����", Font.BOLD, 22));
			lblNewLabel_4.setForeground(Color.RED);
			lblNewLabel_4.setBounds(140, 218, 327, 32);
			lblNewLabel_4.setVisible(false);
			panel.add(lblNewLabel_4);

			JRadioButton rdbtnNewRadioButton = new JRadioButton("male");
			rdbtnNewRadioButton.setBounds(341, 86, 141, 23);
			panel.add(rdbtnNewRadioButton);

			JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("female");
			rdbtnNewRadioButton_1.setBounds(341, 120, 141, 23);
			panel.add(rdbtnNewRadioButton_1);

			JLabel lblNewLabel_5 = new JLabel("gender");
			lblNewLabel_5.setBounds(346, 58, 61, 16);
			panel.add(lblNewLabel_5);
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
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						/*
						 * connect to the database
						 */
						Connection c = null;
						Statement stmt = null;
						try {
							Class.forName("org.sqlite.JDBC");
							c = DriverManager.getConnection("jdbc:sqlite:Store_Users.sesp");
							c.setAutoCommit(false);
							System.out.println("Opened database successfully");

							stmt = c.createStatement();
							int temp_Id = Integer.parseInt(userIDTextField.getText());
							String temp_Name = userNameTextField.getText();
							String temp_Role = userTypeComboBox.getSelectedItem().toString();
							int temp_Password = Integer.parseInt(userPasswordtextField.getText());

							String sql = "INSERT INTO USERS (ID,NAME,ROLE,PASSWORD) " + "VALUES ('" + temp_Id + "', '"
									+ temp_Name + "', '" + temp_Role + "', '" + temp_Password + "' );";
							stmt.executeUpdate(sql);

							stmt.close();
							c.commit();
							c.close();
							dispose();
						} catch (SQLException E) {
							System.err.println(E.getClass().getName() + ": " + E.getMessage());

							dispose();
							idRepeatError dialog = new idRepeatError();
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.setVisible(true);

						} catch (Exception E1) {
							System.err.println(E1.getClass().getName() + ": " + E1.getMessage());
							System.exit(0);
							errorPage dialog = new errorPage();
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.setVisible(true);
						}
						System.out.println("Records created successfully");

//						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
