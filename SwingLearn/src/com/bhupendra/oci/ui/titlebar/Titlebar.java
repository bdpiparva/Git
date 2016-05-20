package com.bhupendra.oci.ui.titlebar;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.bhupendra.oci.ui.Main;

@SuppressWarnings("serial")
public class Titlebar extends JPanel {

	private Main main;

	public Titlebar(Main main) {
		this.main = main;
		setBorder(new LineBorder(Color.WHITE));
		setBackground(new Color(0, 0, 128));
		setLayout(new BorderLayout(0, 0));
		WindowMoveListner wm = new WindowMoveListner(this.main);
		TitlebarButtons tbs = new TitlebarButtons(this.main);
		TitlebarName tbn = new TitlebarName(this.main);
		TitlebarIcon tbi = new TitlebarIcon(this.main);
		tbn.addMouseListener(wm);
		tbs.addMouseListener(wm);
		tbn.addMouseMotionListener(wm);
		tbs.addMouseMotionListener(wm);
		add(tbs, BorderLayout.EAST);
		add(tbn, BorderLayout.CENTER);
		add(tbi, BorderLayout.WEST);
		addMouseListener(wm);

	}

}
