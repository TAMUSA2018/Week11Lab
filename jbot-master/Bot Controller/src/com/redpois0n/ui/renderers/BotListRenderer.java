package com.redpois0n.ui.renderers;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.redpois0n.bot.Bot;
import com.redpois0n.ui.Flags;


@SuppressWarnings({ "rawtypes", "serial" })
public class BotListRenderer extends DefaultListCellRenderer {

	/**
	 * Sets foreground as red if not up to date and the correct flag
	 */
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		
		Bot bot = (Bot) value;
		try {
			setIcon(Flags.getFlag(Flags.getCountry(bot)));
		} catch (Exception e) {
			e.printStackTrace();
			setIcon(null);
		}
		
		setText(bot.toString());
		
		if (bot.isUpToDate()) {
			setForeground(Color.black);
		} else {
			setForeground(Color.red);
		}
		
		return this;
	}

}
