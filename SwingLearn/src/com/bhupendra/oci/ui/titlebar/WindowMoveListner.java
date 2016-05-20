package com.bhupendra.oci.ui.titlebar;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import com.bhupendra.oci.ui.Main;

public class WindowMoveListner extends MouseAdapter {

	private Point initialClick;
	private Main main;

	public WindowMoveListner(Main main) {
		this.main = main;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		initialClick = e.getPoint();
		main.getComponentAt(initialClick);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			main.setExtendedState(main.getExtendedState() == JFrame.NORMAL ? JFrame.MAXIMIZED_BOTH : JFrame.NORMAL);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// get location of Window
		int thisX = main.getLocation().x;
		int thisY = main.getLocation().y;

		// Determine how much the mouse moved since the initial click
		int xMoved = (thisX + e.getX()) - (thisX + initialClick.x);
		int yMoved = (thisY + e.getY()) - (thisY + initialClick.y);

		// Move window to this position
		int X = thisX + xMoved;
		int Y = thisY + yMoved;
		main.setLocation(X, Y);
	}

}
