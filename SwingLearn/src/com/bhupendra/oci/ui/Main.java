package com.bhupendra.oci.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileFilter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import com.bhupendra.oci.ui.titlebar.Titlebar;
import com.bhupendra.oci.ui.titlebar.WindowResizer;

import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class Main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void registerFont() {
		File fontDir = new File("resources/fonts");
		File[] fonts = fontDir.listFiles(new FileFilter() {
			@Override
			public boolean accept(File font) {
				return font.isFile() && font.getName().toLowerCase().endsWith(".ttf");
			}
		});

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		for (File font : fonts) {
			try {
				ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, font));
			} catch (Exception e) {
				System.out.println("Fail to register font: " + font.getName());
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public static void setUIFont() {
		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, new File("resources/fonts/Roboto-Regular.ttf")).deriveFont(Font.BOLD, 12f);
			FontUIResource fur = new javax.swing.plaf.FontUIResource(font);
			java.util.Enumeration keys = UIManager.getDefaults().keys();
			while (keys.hasMoreElements()) {
				Object key = keys.nextElement();
				Object value = UIManager.get(key);
				if (value != null && value instanceof javax.swing.plaf.FontUIResource)
					UIManager.put(key, fur);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		registerFont();
		System.setProperty("awt.useSystemAAFontSettings", "on");
		System.setProperty("swing.aatext", "true");
		setUIFont();
		setForeground(Color.WHITE);
		setBackground(Color.BLUE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 717, 387);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 128)));
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setTitle("One click installer");

		InstallationSteps jp = new InstallationSteps();
		WindowResizer.install(contentPane, SwingConstants.SOUTH_EAST);
		contentPane.add(new Titlebar(this), BorderLayout.NORTH);
		contentPane.add(jp, BorderLayout.CENTER);

		setUndecorated(true);
	}

}
