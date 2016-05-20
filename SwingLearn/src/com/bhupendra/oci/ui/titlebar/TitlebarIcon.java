package com.bhupendra.oci.ui.titlebar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.bhupendra.oci.ui.Main;

@SuppressWarnings("serial")
public class TitlebarIcon extends JPanel implements ActionListener {

	private Main main;
	private JLabel icon;

	public TitlebarIcon(Main main) {
		this.main = main;
		setBackground(new Color(0, 0, 128));
		setSize(new Dimension(140, 34));
		setMaximumSize(new Dimension(32767, 30));
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		icon = new JLabel();
		if (main.getIconImage() != null) {
			icon.setIcon(new ImageIcon(main.getIconImage()));
		}
		icon.setOpaque(true);
		icon.setBackground(new Color(0, 0, 128));
		icon.setMinimumSize(new Dimension(30, 30));
	}

	public void addButtonUI(JButton button, Color bgColor, Color textColor, Color borderColor) {
		button.setOpaque(true);
		button.setForeground(textColor);
		button.setBackground(bgColor);
		button.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(borderColor, 1), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == icon) {
			main.dispose();
		}
	}
}
