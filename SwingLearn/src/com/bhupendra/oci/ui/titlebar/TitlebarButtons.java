package com.bhupendra.oci.ui.titlebar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.bhupendra.oci.IconUtil;
import com.bhupendra.oci.ui.Main;

@SuppressWarnings("serial")
public class TitlebarButtons extends JPanel implements ActionListener {

	private Main main;
	private JButton btnClose;
	private JButton btnRestore;
	private JButton btnMinimize;
	private Color BLUE = new Color(0, 0, 128);
	private Color RED = new Color(244, 67, 54);
	private Color LIGHT_RED = new Color(240, 128, 128);
	private Color LIGHT_GRAY = new Color(189, 189, 189);

	public TitlebarButtons(Main main) {
		this.main = main;
		setBackground(new Color(0, 0, 128));
		setSize(new Dimension(140, 34));
		setMaximumSize(new Dimension(32767, 30));
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnMinimize = new JButton(IconUtil.getIcon("minus.png", 13, 13));
		add(btnMinimize);

		btnRestore = new JButton(IconUtil.getIcon("expand.png", 13, 13));
		add(btnRestore);

		btnClose = new JButton(IconUtil.getIcon("cross.png", 13, 13));
		addButtonUI(btnRestore, LIGHT_GRAY, BLUE, Color.GRAY);
		addButtonUI(btnMinimize, LIGHT_GRAY, BLUE, Color.GRAY);
		addButtonUI(btnClose, LIGHT_RED, Color.WHITE, Color.RED);
		addHoverEffect(btnMinimize, btnMinimize.getBackground(), Color.gray);
		addHoverEffect(btnRestore, btnRestore.getBackground(), Color.gray);
		addHoverEffect(btnClose, btnClose.getBackground(), RED);
		btnMinimize.addActionListener(this);
		btnRestore.addActionListener(this);
		btnClose.addActionListener(this);
		btnClose.setFocusPainted(false);
		btnRestore.setFocusPainted(false);
		btnMinimize.setFocusPainted(false);
		add(btnClose);

	}

	private void addHoverEffect(final JButton button, final Color defaultColor, final Color hoverColor) {
		button.getModel().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				ButtonModel model = (ButtonModel) e.getSource();
				button.setBackground(model.isRollover() ? hoverColor : defaultColor);
			}
		});

	}

	public void addButtonUI(JButton button, Color bgColor, Color textColor, Color borderColor) {
		button.setOpaque(true);
		button.setForeground(textColor);
		button.setBackground(bgColor);
		button.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(borderColor, 1), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == btnClose) {
			main.dispose();
		} else if (event.getSource() == btnMinimize) {
			main.setState(JFrame.ICONIFIED);
		} else if (event.getSource() == btnRestore) {
			main.setExtendedState(main.getExtendedState() == JFrame.NORMAL ? JFrame.MAXIMIZED_BOTH : JFrame.NORMAL);
		}
	}

}
