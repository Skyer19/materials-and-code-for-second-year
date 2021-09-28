/*******************************************************************************
 * Copyright (C) 2021 Dapeng Dong
 * 
 * This program is free software; you can redistribute it and/or 
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

import java.awt.Component;
import java.awt.EventQueue;
//hjt
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
//hjt
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.ListSelectionModel;

import org.dapeng.usicms.handler.ColumnTransferHandler;
import org.dapeng.usicms.model.UserStory;
import org.dapeng.usicms.model.UserStoryStatus;

public class USICMS {

	private JFrame frame;
	private final JPanel contentPanel = new JPanel();

	private final Action actionCreateProject = new SwingActionCreateProject();
	private final Action actionCreateUserStory = new SwingActionCreateUserStory();
	private final Action actionLoadProject = new SwingActionLoadProject();

	private DefaultListModel listInProgressModel = new DefaultListModel();
	private DefaultListModel listDoneModel = new DefaultListModel();
	private DefaultListModel listToDoModel = new DefaultListModel();
	private static final USICMS SINGLE_USICMS = new USICMS();

	public int user_id = Integer.MAX_VALUE;

	public JMenu mnNew;
	public JMenu mnUserStory;

	public static USICMS getInstance() {
		return SINGLE_USICMS;
	}

	public void addListModelElement(UserStory us, UserStoryStatus uss) {
		switch (uss) {
		case TODO:
			listToDoModel.addElement(us.getId() + "--" + us.getName());
			break;
		case INPROGRESS:
			listInProgressModel.addElement(us.getId() + "--" + us.getName());
			break;
		case DONE:
			listDoneModel.addElement(us.getId() + "--" + us.getName());
			break;
		}
	}

	public void removeListModelElement(int index, UserStoryStatus uss) {
		switch (uss) {
		case TODO:
			listToDoModel.removeElementAt(index);
			break;
		case INPROGRESS:
			listInProgressModel.removeElementAt(index);
			break;
		case DONE:
			listDoneModel.removeElementAt(index);
			break;
		}
	}

	public void removeListModelElement() {
		listToDoModel.clear();
		listInProgressModel.clear();
		listDoneModel.clear();
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					USICMS.getInstance().frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public USICMS() {
//		while (true) {
		initialize();
//		}
	}

	private void maybeShowPopup(JPopupMenu popupMenu, JList list, MouseEvent e) {

		if (e.isPopupTrigger() && list.getSelectedIndex() != -1) {

			Object selected = list.getModel().getElementAt(list.getSelectedIndex());
			popupMenu.show(e.getComponent(), e.getX(), e.getY());
		}
	}

	void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 530, 295);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		mnNew = new JMenu("Project");
		menuBar.add(mnNew);

		JMenuItem mntmNewProject = new JMenuItem("New Project");
		mntmNewProject.setAction(actionCreateProject);
		mnNew.add(mntmNewProject);

		JMenuItem mntmLoadProject = new JMenuItem("Load Project");
		mntmLoadProject.setAction(actionLoadProject);
		mnNew.add(mntmLoadProject);

		mnUserStory = new JMenu("User Story");
		menuBar.add(mnUserStory);

		JMenuItem mntmCreateUserStory = new JMenuItem("Create User Story");
		mntmCreateUserStory.setAction(actionCreateUserStory);
		mnUserStory.add(mntmCreateUserStory);

		{

			JButton btnNewButton = new JButton("REGISTER");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Register dialog = new Register();
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			});
			menuBar.add(btnNewButton);

			JButton btnNewButton_1 = new JButton("LOGIN");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Login dialog = new Login();
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			});
			menuBar.add(btnNewButton_1);
		}
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panelToDo = new JPanel();
		frame.getContentPane().add(panelToDo);
		panelToDo.setLayout(null);

		JMenuItem mntmDeleteStory = new JMenuItem("delete");
		mntmDeleteStory.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				JPanel components1 = (JPanel) frame.getContentPane().getComponentAt(evt.getPoint());
				Component[] components = components1.getComponents();
				for (Component c : components) {
					if (c instanceof JList) {
						JList list = (JList) c;
						int index = list.locationToIndex(evt.getPoint());
						USICMS.getInstance().removeListModelElement(index,
								UserStoryStatus.valueOf(list.getName().toUpperCase()));
					}
				}
			}
		});

		JPopupMenu popupMenu = new JPopupMenu();
		popupMenu.add(mntmDeleteStory);

		JLabel lblTodo = new JLabel("ToDo");
		lblTodo.setBounds(58, 0, 57, 24);
		panelToDo.add(lblTodo);

		JList listToDo = new JList(listToDoModel);
		listToDo.setName("ToDo");
		listToDo.setBounds(0, 24, 164, 226);
		listToDo.setVisibleRowCount(12);
		listToDo.setDragEnabled(true);
		listToDo.setTransferHandler(new ColumnTransferHandler());
		listToDo.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();
				if (evt.getClickCount() == 2) {
					int index = list.locationToIndex(evt.getPoint());
					DisplayUserStory dialog = new DisplayUserStory((String) list.getModel().getElementAt(index));
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}
				int mods = evt.getModifiers();

				if ((mods & InputEvent.BUTTON3_MASK) != 0) {
					list.add(popupMenu);
					popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
				}

			}

			public void mouseReleased(MouseEvent evt) {
				JList list = (JList) evt.getSource();
				maybeShowPopup(popupMenu, list, evt);
			}
		});

		listToDo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panelToDo.add(listToDo);

		JPanel panelInProgress = new JPanel();
		frame.getContentPane().add(panelInProgress);
		panelInProgress.setLayout(null);

		JLabel lblInProgress = new JLabel("In Progress");
		lblInProgress.setBounds(47, 5, 81, 15);
		panelInProgress.add(lblInProgress);

		JList listInProgress = new JList(listInProgressModel);
		listInProgress.setName("InProgress");
		listInProgress.setDragEnabled(true);
		listInProgress.setTransferHandler(new ColumnTransferHandler());
		listInProgress.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();
				if (evt.getClickCount() == 2) {
					int index = list.locationToIndex(evt.getPoint());
					DisplayUserStory dialog = new DisplayUserStory((String) list.getModel().getElementAt(index));
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}
			}
		});

		listInProgress.setBounds(0, 25, 176, 226);
		panelInProgress.add(listInProgress);

		JPanel panelDone = new JPanel();
		frame.getContentPane().add(panelDone);
		panelDone.setLayout(null);

		JLabel lblDone = new JLabel("Done");
		lblDone.setBounds(69, 5, 37, 15);
		panelDone.add(lblDone);

		JList listDone = new JList(listDoneModel);
		listDone.setName("Done");
		listDone.setDragEnabled(true);
		listDone.setTransferHandler(new ColumnTransferHandler());
		listDone.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();
				if (evt.getClickCount() == 2) {
					int index = list.locationToIndex(evt.getPoint());
					DisplayUserStory dialog = new DisplayUserStory((String) list.getModel().getElementAt(index));
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}
			}
		});
		listDone.setBounds(12, 25, 164, 226);
		panelDone.add(listDone);

		if (user_id != Integer.MAX_VALUE) {

		} else {
			mnNew.setVisible(false);
			mnUserStory.setVisible(false);
			System.out.println("set visible to false");

		}
	}

	// create a new project
	private class SwingActionCreateProject extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public SwingActionCreateProject() {
			putValue(NAME, "New Project");
		}

		public void actionPerformed(ActionEvent e) {
			try {
				CreateProjectDialog dialog = new CreateProjectDialog();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	// create a new user story
	private class SwingActionCreateUserStory extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public SwingActionCreateUserStory() {
			putValue(NAME, "Create User Story");
		}

		public void actionPerformed(ActionEvent e) {

			CreateUserStory dialog = new CreateUserStory();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}
	}

	private class SwingActionDeleteUserStory extends AbstractAction {
		private static final long serialVersionUID = 3L;

		public SwingActionDeleteUserStory() {
			putValue(NAME, "Delete Story");
		}

		public void actionPerformed(ActionEvent e) {
			try {

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	// load a project from local
	private class SwingActionLoadProject extends AbstractAction {
		private static final long serialVersionUID = 2L;

		public SwingActionLoadProject() {
			putValue(NAME, "Load Project");
		}

		public void actionPerformed(ActionEvent e) {
			try {
				LoadProjectDialog dialog = new LoadProjectDialog();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
