package com.redpois0n.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class FrameBuild extends JFrame {

	private JPanel contentPane;
	private JTextField txtHost;
	private JSpinner spPort;

	/**
	 * 
	 *	TODO Finish this
	 *
	 */
	public FrameBuild() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblHost = new JLabel("Host");
		lblHost.setBounds(23, 19, 22, 14);
		
		txtHost = new JTextField();
		txtHost.setBounds(49, 16, 173, 20);
		txtHost.setColumns(10);
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setBounds(25, 47, 20, 14);
		
		spPort = new JSpinner();
		spPort.setBounds(49, 44, 63, 20);
		spPort.setModel(new SpinnerNumberModel(1444, 1, 65535, 1));
		contentPane.setLayout(null);
		contentPane.add(lblHost);
		contentPane.add(lblPort);
		contentPane.add(txtHost);
		contentPane.add(spPort);
	}
}
