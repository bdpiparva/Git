package com.bhupendra.oci.ui.titlebar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WindowResizer extends MouseAdapter implements SwingConstants {
	private boolean resizing = false;
	private int prevX = -1;
	private int prevY = -1;

	private int resizeSide = 0;

	public static void install(Component component, int resizeSide) {
		WindowResizer wra = new WindowResizer(resizeSide);
		component.addMouseListener(wra);
		component.addMouseMotionListener(wra);
	}

	public WindowResizer(int resizeSide) {
		super();
		this.resizeSide = resizeSide;
	}

	public void mousePressed(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) {
			resizing = true;
		}
		prevX = e.getXOnScreen();
		prevY = e.getYOnScreen();
	}

	public void mouseDragged(MouseEvent e) {
		if (prevX != -1 && prevY != -1 && resizing) {
			Window w = SwingUtilities.getWindowAncestor(e.getComponent());
			Rectangle rect = w.getBounds();

			Dimension dim;
			boolean undecorated;
			if (w instanceof JDialog) {
				dim = ((JDialog) w).getContentPane().getPreferredSize();
				undecorated = ((JDialog) w).isUndecorated();
			} else if (w instanceof JFrame) {
				dim = ((JFrame) w).getContentPane().getPreferredSize();
				undecorated = ((JFrame) w).isUndecorated();
			} else {
				dim = w.getPreferredSize();
				undecorated = true;
			}

			// Checking for minimal width and height
			int xInc = e.getXOnScreen() - prevX;
			int yInc = e.getYOnScreen() - prevY;
			if (undecorated) {
				if (resizeSide == SwingConstants.NORTH_WEST || resizeSide == SwingConstants.WEST || resizeSide == SwingConstants.SOUTH_WEST) {
					if (rect.width - xInc < dim.width) {
						xInc = 0;
					}
				} else if (resizeSide == SwingConstants.NORTH_EAST || resizeSide == SwingConstants.EAST || resizeSide == SwingConstants.SOUTH_EAST) {
					if (rect.width + xInc < dim.width) {
						xInc = 0;
					}
				}
				if (resizeSide == SwingConstants.NORTH_WEST || resizeSide == SwingConstants.NORTH || resizeSide == SwingConstants.NORTH_EAST) {
					if (rect.height - yInc < dim.height) {
						yInc = 0;
					}
				} else if (resizeSide == SwingConstants.SOUTH_WEST || resizeSide == SwingConstants.SOUTH || resizeSide == SwingConstants.SOUTH_EAST) {
					if (rect.height + yInc < dim.height) {
						yInc = 0;
					}
				}
			}

			// Resizing window if any changes are done
			if (xInc != 0 || yInc != 0) {
				if (resizeSide == SwingConstants.NORTH_WEST) {
					w.setBounds(rect.x + xInc, rect.y + yInc, rect.width - xInc, rect.height - yInc);
				} else if (resizeSide == SwingConstants.NORTH) {
					w.setBounds(rect.x, rect.y + yInc, rect.width, rect.height - yInc);
				} else if (resizeSide == SwingConstants.NORTH_EAST) {
					w.setBounds(rect.x, rect.y + yInc, rect.width + xInc, rect.height - yInc);
				} else if (resizeSide == SwingConstants.WEST) {
					w.setBounds(rect.x + xInc, rect.y, rect.width - xInc, rect.height);
				} else if (resizeSide == SwingConstants.EAST) {
					w.setBounds(rect.x, rect.y, rect.width + xInc, rect.height);
				} else if (resizeSide == SwingConstants.SOUTH_WEST) {
					w.setBounds(rect.x + xInc, rect.y, rect.width - xInc, rect.height + yInc);
				} else if (resizeSide == SwingConstants.SOUTH) {
					w.setBounds(rect.x, rect.y, rect.width, rect.height + yInc);
				} else if (resizeSide == SwingConstants.SOUTH_EAST) {
					w.setBounds(rect.x, rect.y, rect.width + xInc, rect.height + yInc);
				}
				prevX = e.getXOnScreen();
				prevY = e.getYOnScreen();
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
		resizing = false;
	}
}