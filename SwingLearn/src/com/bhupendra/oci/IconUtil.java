package com.bhupendra.oci;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;

public abstract class IconUtil {

	private static String BASE_FOLDER = "resources" + File.separator + "icons" + File.separator;

	public static ImageIcon getIcon(String iconName) {
		return new ImageIcon(BASE_FOLDER + iconName);
	}

	public static ImageIcon getIcon(String iconName, int width, int height) {
		ImageIcon imageIcon = getIcon(iconName);
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(newimg);
	}
}
