package com.bhupendra.oci.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class InstallationSteps extends JPanel {

	private static final String DELETE_LAST_ROW = "Delete last row";
	private static final String DELETE_CURRENT_ROW = "Delete current row";
	private static final String ADD_NEW_ROW = "Add new row";

	/**
	 * Create the panel.
	 */

	public InstallationSteps() {

		setLayout(new BorderLayout(0, 0));
		final JPanel help = new JPanel();
		add(help, BorderLayout.NORTH);

		JLabel lblAddRowaltent = new JLabel("Add Row: (Alt+Ent)   Delete Last Row: (Alt+Del)");
		help.add(lblAddRowaltent);
		final JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		final DefaultTableModel model = new DefaultTableModel();
		model.addColumn("#");
		model.addColumn("Key");
		model.addColumn("Value");

		JTable table = new JTable(model);
		table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 25));
		table.getColumnModel().getColumn(0).setMaxWidth(30);

		table.setAutoCreateRowSorter(true);
		table.setRowHeight(25);
		table.setRowMargin(4);

		KeyStroke addRow = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.ALT_MASK);
		KeyStroke delLastRow = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, InputEvent.ALT_MASK);
		KeyStroke delRow = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, InputEvent.CTRL_DOWN_MASK);
		table.getInputMap(JTable.WHEN_IN_FOCUSED_WINDOW).put(addRow, ADD_NEW_ROW);
		table.getInputMap(JTable.WHEN_IN_FOCUSED_WINDOW).put(delLastRow, DELETE_LAST_ROW);
		table.getInputMap(JTable.WHEN_FOCUSED).put(delRow, DELETE_CURRENT_ROW);
		table.getActionMap().put(ADD_NEW_ROW, new AddRowAction(model));
		table.getActionMap().put(DELETE_LAST_ROW, new DeleteLastRowAction(model));
		table.getActionMap().put(DELETE_CURRENT_ROW, new DeleteRowAction(model));

		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane, BorderLayout.CENTER);
	}

	private class AddRowAction extends AbstractAction {

		private DefaultTableModel model;

		public AddRowAction(DefaultTableModel model) {
			this.model = model;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			model.addRow(new String[] { "" + (model.getRowCount() + 1), "", "" });
		}
	}

	private class DeleteLastRowAction extends AbstractAction {

		private DefaultTableModel model;

		public DeleteLastRowAction(DefaultTableModel model) {
			this.model = model;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (model.getRowCount() - 1 > -1)
				model.removeRow(model.getRowCount() - 1);
		}
	}

	private class DeleteRowAction extends AbstractAction {

		private DefaultTableModel model;

		public DeleteRowAction(DefaultTableModel model) {
			this.model = model;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JTable table = (JTable) e.getSource();
			int row = table.getSelectedRow();
			if (row > -1) {
				model.removeRow(row);
			}
		}
	}
}