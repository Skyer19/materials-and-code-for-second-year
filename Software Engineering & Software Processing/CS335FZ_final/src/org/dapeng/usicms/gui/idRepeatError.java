package org.dapeng.usicms.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class idRepeatError extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public idRepeatError() {
		setResizable(false);
		setBounds(100, 100, 555, 341);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		{
			JPanel panelBottom = new JPanel();
			contentPanel.add(panelBottom, BorderLayout.SOUTH);
			panelBottom.setLayout(new GridLayout(2, 4, 0, 0));

		}
		{
			JPanel panel = new JPanel();
			panel.setMaximumSize(new Dimension(31000, 32767));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("TRY AGAIN NOW");
				lblNewLabel.setForeground(Color.RED);
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel.setFont(new Font("����", Font.BOLD, 28));
				lblNewLabel.setBounds(112, 73, 317, 91);
				panel.add(lblNewLabel);
			}

			JLabel lblNewLabel_1 = new JLabel("ID HAS BEEN CREATED!!!!!");
			lblNewLabel_1.setBounds(190, 161, 192, 16);
			panel.add(lblNewLabel_1);
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
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
