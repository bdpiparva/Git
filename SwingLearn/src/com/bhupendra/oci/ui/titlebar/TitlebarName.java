package com.bhupendra.oci.ui.titlebar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.bhupendra.oci.ui.Main;

@SuppressWarnings("serial")
public class TitlebarName extends JPanel {

	private Main main;
	private JLabel title;

	public TitlebarName(Main main) {
		this.main = main;
		setBackground(new Color(0, 0, 128));
		setSize(new Dimension(131, 27));
		setMaximumSize(new Dimension(32767, 30));

		title = new JLabel(this.main.getTitle());
		title.setForeground(Color.WHITE);
		title.setFont(title.getFont().deriveFont(Font.BOLD, 16f));
		setLayout(new BorderLayout(0, 0));
		add(title, BorderLayout.CENTER);
	}
}
