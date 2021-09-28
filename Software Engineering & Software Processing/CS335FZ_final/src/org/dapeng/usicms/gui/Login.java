package org.dapeng.usicms.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Login extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField loginIdTextField;
	private JTextField loginPasswordtextField;

	/**
	 * Create the dialog.
	 */
	public Login() {
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
				loginIdTextField = new JTextField();
				loginIdTextField.setBounds(235, 83, 125, 21);
				panel.add(loginIdTextField);
				loginIdTextField.setColumns(15);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("ID:");
				lblNewLabel_1.setBounds(171, 86, 19, 15);
				panel.add(lblNewLabel_1);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("LOGIN");
				lblNewLabel_2.setBounds(245, 27, 77, 15);
				panel.add(lblNewLabel_2);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("PASSWORD");
				lblNewLabel_1.setBounds(141, 124, 77, 15);
				panel.add(lblNewLabel_1);
			}
			{
				loginPasswordtextField = new JPasswordField();
				loginPasswordtextField.setColumns(15);
				loginPasswordtextField.setBounds(235, 121, 125, 21);
				panel.add(loginPasswordtextField);
			}
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
							int temp_Id = Integer.parseInt(loginIdTextField.getText());
							int temp_Password = Integer.parseInt(loginPasswordtextField.getText());

							ResultSet rs = stmt.executeQuery("SELECT ID,PASSWORD FROM USERS where ID='" + temp_Id
									+ "' and PASSWORD='" + temp_Password + "' ;");
//							if (!loginIdTextField.getText().isEmpty()) {
//							if (true) {
							if (!rs.next()) {
								errorPage dialog = new errorPage();
								dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								dialog.setVisible(true);
							} else {
//								if()
								USICMS.getInstance().user_id = temp_Id;
								USICMS.getInstance().mnNew.setVisible(true);
								USICMS.getInstance().mnUserStory.setVisible(true);
								System.out.println(USICMS.getInstance().user_id);
							}
//							}
							rs.close();
							stmt.close();
							c.close();
						} catch (Exception E) {
							System.err.println(E.getClass().getName() + ": " + E.getMessage());
//							System.exit(0);
							errorPage dialog = new errorPage();
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.setVisible(true);
						}
						System.out.println("Operation done successfully");
						/*
						 */
						dispose();
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
