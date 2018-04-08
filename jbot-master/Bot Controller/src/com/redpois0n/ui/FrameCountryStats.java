package com.redpois0n.ui;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.redpois0n.ui.stats.CountryStats;


@SuppressWarnings("serial")
public class FrameCountryStats extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	
	/**
	 * Returns the model
	 * @return
	 */
	public DefaultTableModel getModel() {
		return (DefaultTableModel) table.getModel();
	}

	
	public FrameCountryStats() {
		setTitle("Country Statistics");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPanel.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setRowHeight(25);
		table.getTableHeader().setReorderingAllowed(false);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Country", "Unique Connects" }) {
			public boolean isCellEditable(int i, int i1) {
				return false;
			}
		});
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				JLabel lbl = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if (column == 0) {
					String country = value.toString().substring(value.toString().indexOf("(") + 1, value.toString().length() - 1);
					setIcon(Flags.getFlag(country));
				} else {
					setIcon(null);
				}
				return lbl;
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(64);
		table.getColumnModel().getColumn(1).setPreferredWidth(99);
		scrollPane.setViewportView(table);

		load();
	}

	/**
	 * Clears and loads from country statistics
	 */
	public void load() {
		while (getModel().getRowCount() > 0) {
			getModel().removeRow(0);
		}
		
		for (String country : CountryStats.map.keySet().toArray(new String[0])) {
			CountryStats stats = CountryStats.map.get(country);
			
			getModel().addRow(new Object[] { stats.getCode(), stats.getIPs().size() });
		}
	}
}
