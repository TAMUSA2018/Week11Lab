package com.redpois0n.ui;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.redpois0n.bot.Util;
//import com.redpois0n.oslib.OperatingSystem;
import oslib.OperatingSystem;
import oslib.*;
import com.redpois0n.ui.stats.OsStats;


@SuppressWarnings("serial")
public class FrameOsStats extends JDialog {

	/**
	 * OS icons
	 */
	public static final ImageIcon OS_WIN = Util.getIcon("os_win");
	public static final ImageIcon OS_MAC = Util.getIcon("os_mac");
	public static final ImageIcon OS_LINUX = Util.getIcon("os_linux");
	public static final ImageIcon OS_OTHERS = Util.getIcon("os_other");
	
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JScrollPane scrollPane;
	
	/**
	 * Returns the model
	 * @return
	 */
	public DefaultTableModel getModel() {
		return (DefaultTableModel) table.getModel();
	}

	public FrameOsStats() {
		setTitle("Operating System Statistics");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPanel.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setRowHeight(25);
		table.getTableHeader().setReorderingAllowed(false);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Operating System", "Unique bots" }) {
			public boolean isCellEditable(int i, int i1) {
				return false;
			}
		});
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				JLabel lbl = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if (column == 0) {
					OperatingSystem os = OperatingSystem.getOperatingSystem(value.toString());
					if (os == OperatingSystem.WINDOWS) {
						lbl.setIcon(OS_WIN);
					} else if (os == OperatingSystem.MACOS) {
						lbl.setIcon(OS_MAC);
					} else if (os == OperatingSystem.LINUX) {
						lbl.setIcon(OS_LINUX);
					} else {
						lbl.setIcon(OS_OTHERS);
					}
				} else {
					setIcon(null);
				}
				return lbl;
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(239);
		scrollPane.setViewportView(table);

		load();
	}
	
	/**
	 * Clears and loads from operating system statistics
	 */
	public void load() {
		while (getModel().getRowCount() > 0) {
			getModel().removeRow(0);
		}
		
		for (String os : OsStats.map.keySet().toArray(new String[0])) {
			OsStats stats = OsStats.map.get(os);
			
			getModel().addRow(new Object[] { os, stats.getIPs().size() });
		}
	}

}
