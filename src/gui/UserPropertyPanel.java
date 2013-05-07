package gui;

import javax.swing.JPanel;

import data.User;
import database.MySQL;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;

import output.CSS_Creator;
import output.Last_Done_Property;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class UserPropertyPanel extends JPanel {

	private User user;
	private JPanel jPanelNorth;
	private JLabel jLabelChangePassword;
	private JLabel lblAltesPasswort;
	private JLabel jLabelNeusPasswort;
	private JLabel jLabelNeuesPasswortWiederholen;
	private JPasswordField passwordFieldOld;
	private JPasswordField passwordFieldNew;
	private JPasswordField passwordFieldRepeatNew;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JPanel jPanelCenter;
	private JPanel jPanelCenterNorth;
	private JLabel label;
	private JComboBox jComboBoxFromHour;
	private JComboBox jComboBoxFromMinute;
	private JLabel label_1;
	private JLabel label_2;
	private JComboBox jComboBoxToHour;
	private JComboBox jComboBoxToMinute;
	private JLabel label_3;
	private JLabel label_4;
	private JComboBox jComboBoxBreak;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel jLabelStandardWorkTime;

	private DefaultComboBoxModel<Integer> modelFromHour;
	private DefaultComboBoxModel<Integer> modelFromMinute;
	private DefaultComboBoxModel<Integer> modelToHour;
	private DefaultComboBoxModel<Integer> modelToMinute;
	private DefaultComboBoxModel<Integer> modelBreak;
	private JPanel jPanelCenterCenter;
	private JPanel jPanelCenterCenterNorth;
	private JButton btnCssGenerieren;
	private JPanel jPanelCenterCenterSouth;
	private JLabel jLabelMessageLine;
	private JButton btnPasswortAendern;
	private JLabel lblNewLabel_3;
	private JButton btnStandardarbeitszeitUebernehmen;
	private JLabel lblNewLabel_4;
	private MySQL sql = null;
	private JLabel lblNewLabel_5;

	/**
	 * Create the panel.
	 */
	public UserPropertyPanel(User user) {
		setPreferredSize(new Dimension(550, 400));
		sql = MySQL.getInstance();
		this.user = user;
		setLayout(new BorderLayout(0, 0));
		add(getJPanelNorth(), BorderLayout.NORTH);
		add(getJPanelCenter(), BorderLayout.CENTER);
		Last_Done_Property ldp = new Last_Done_Property(jComboBoxFromHour,
				jComboBoxFromMinute, jComboBoxToHour, jComboBoxToMinute,
				jComboBoxBreak);

	}

	private JPanel getJPanelNorth() {
		if (jPanelNorth == null) {
			jPanelNorth = new JPanel();
			jPanelNorth.setPreferredSize(new Dimension(550, 150));
			jPanelNorth.add(getJLabelChangePassword());
			jPanelNorth.add(getLblAltesPasswort());
			jPanelNorth.add(getPasswordFieldOld());
			jPanelNorth.add(getLblNewLabel());
			jPanelNorth.add(getJLabelNeusPasswort());
			jPanelNorth.add(getPasswordFieldNew());
			jPanelNorth.add(getLblNewLabel_1());
			jPanelNorth.add(getJLabelNeuesPasswortWiederholen());
			jPanelNorth.add(getPasswordFieldRepeatNew());
			jPanelNorth.add(getLblNewLabel_2());
			jPanelNorth.add(getBtnPasswortAendern());
			jPanelNorth.add(getLblNewLabel_3());
		}
		return jPanelNorth;
	}

	private JLabel getJLabelChangePassword() {
		if (jLabelChangePassword == null) {
			jLabelChangePassword = new JLabel(" Passwort \u00E4ndern");
			jLabelChangePassword.setPreferredSize(new Dimension(530, 20));
			jLabelChangePassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
			jLabelChangePassword.setBorder(new MatteBorder(1, 1, 1, 1,
					(Color) new Color(0, 0, 0)));
		}
		return jLabelChangePassword;
	}

	private JLabel getLblAltesPasswort() {
		if (lblAltesPasswort == null) {
			lblAltesPasswort = new JLabel("Altes Passwort:");
			lblAltesPasswort.setPreferredSize(new Dimension(180, 14));
		}
		return lblAltesPasswort;
	}

	private JLabel getJLabelNeusPasswort() {
		if (jLabelNeusPasswort == null) {
			jLabelNeusPasswort = new JLabel("Neus Passwort:");
			jLabelNeusPasswort.setPreferredSize(new Dimension(180, 14));
		}
		return jLabelNeusPasswort;
	}

	private JLabel getJLabelNeuesPasswortWiederholen() {
		if (jLabelNeuesPasswortWiederholen == null) {
			jLabelNeuesPasswortWiederholen = new JLabel(
					"Neues Passwort wiederholen:");
			jLabelNeuesPasswortWiederholen.setPreferredSize(new Dimension(180,
					14));
		}
		return jLabelNeuesPasswortWiederholen;
	}

	private JPasswordField getPasswordFieldOld() {
		if (passwordFieldOld == null) {
			passwordFieldOld = new JPasswordField();
			passwordFieldOld.setPreferredSize(new Dimension(150, 20));
		}
		return passwordFieldOld;
	}

	private JPasswordField getPasswordFieldNew() {
		if (passwordFieldNew == null) {
			passwordFieldNew = new JPasswordField();
			passwordFieldNew.setPreferredSize(new Dimension(150, 20));
		}
		return passwordFieldNew;
	}

	private JPasswordField getPasswordFieldRepeatNew() {
		if (passwordFieldRepeatNew == null) {
			passwordFieldRepeatNew = new JPasswordField();
			passwordFieldRepeatNew.setPreferredSize(new Dimension(150, 20));
		}
		return passwordFieldRepeatNew;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setPreferredSize(new Dimension(150, 14));
		}
		return lblNewLabel;
	}

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setPreferredSize(new Dimension(150, 14));
		}
		return lblNewLabel_1;
	}

	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("");
			lblNewLabel_2.setPreferredSize(new Dimension(150, 14));
		}
		return lblNewLabel_2;
	}

	private JPanel getJPanelCenter() {
		if (jPanelCenter == null) {
			jPanelCenter = new JPanel();
			jPanelCenter.setPreferredSize(new Dimension(550, 280));
			jPanelCenter.setLayout(new BorderLayout(0, 0));
			jPanelCenter.add(getJPanelCenterNorth(), BorderLayout.NORTH);
			jPanelCenter.add(getJPanelCenterCenter(), BorderLayout.CENTER);
		}
		return jPanelCenter;
	}

	private JPanel getJPanelCenterNorth() {
		if (jPanelCenterNorth == null) {
			jPanelCenterNorth = new JPanel();
			jPanelCenterNorth.setPreferredSize(new Dimension(550, 170));
			jPanelCenterNorth.setEnabled(false);
			jPanelCenterNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 15,
					10));
			jPanelCenterNorth.add(getJLabelStandardWorkTime());
			jPanelCenterNorth.add(getLabel());
			jPanelCenterNorth.add(getJComboBoxFromHour());
			jPanelCenterNorth.add(getJComboBoxFromMinute());
			jPanelCenterNorth.add(getLabel_1());
			jPanelCenterNorth.add(getLabel_2());
			jPanelCenterNorth.add(getJComboBoxToHour());
			jPanelCenterNorth.add(getJComboBoxToMinute());
			jPanelCenterNorth.add(getLabel_3());
			jPanelCenterNorth.add(getLabel_4());
			jPanelCenterNorth.add(getJComboBoxBreak());
			jPanelCenterNorth.add(getLabel_5());
			jPanelCenterNorth.add(getLabel_6());
			jPanelCenterNorth.add(getBtnStandardarbeitszeitUebernehmen());
			jPanelCenterNorth.add(getLblNewLabel_4());
		}
		return jPanelCenterNorth;
	}

	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("Von:");
			label.setPreferredSize(new Dimension(40, 14));
		}
		return label;
	}

	private JComboBox getJComboBoxFromHour() {
		if (jComboBoxFromHour == null) {
			modelFromHour = new DefaultComboBoxModel<Integer>();
			jComboBoxFromHour = new JComboBox(modelFromHour);
			jComboBoxFromHour.setPreferredSize(new Dimension(50, 20));
			modelFromHour.addElement(6);
			modelFromHour.addElement(7);
			modelFromHour.addElement(8);
			modelFromHour.addElement(9);
			modelFromHour.addElement(10);
			modelFromHour.addElement(11);
			modelFromHour.addElement(12);
			modelFromHour.addElement(13);
			modelFromHour.addElement(14);
			modelFromHour.addElement(15);
			modelFromHour.addElement(16);
			modelFromHour.addElement(17);
			modelFromHour.addElement(18);
			modelFromHour.addElement(19);
			modelFromHour.addElement(20);
		}
		return jComboBoxFromHour;
	}

	private JComboBox getJComboBoxFromMinute() {
		if (jComboBoxFromMinute == null) {
			modelFromMinute = new DefaultComboBoxModel<Integer>();
			jComboBoxFromMinute = new JComboBox(modelFromMinute);
			jComboBoxFromMinute.setPreferredSize(new Dimension(50, 20));
			modelFromMinute.addElement(00);
			modelFromMinute.addElement(15);
			modelFromMinute.addElement(30);
			modelFromMinute.addElement(45);
		}
		return jComboBoxFromMinute;
	}

	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("                    ");
			label_1.setPreferredSize(new Dimension(300, 14));
		}
		return label_1;
	}

	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("Bis:");
			label_2.setPreferredSize(new Dimension(40, 14));
		}
		return label_2;
	}

	private JComboBox getJComboBoxToHour() {
		if (jComboBoxToHour == null) {
			modelToHour = new DefaultComboBoxModel<Integer>();
			jComboBoxToHour = new JComboBox(modelToHour);
			jComboBoxToHour.setPreferredSize(new Dimension(50, 20));
			modelToHour.addElement(6);
			modelToHour.addElement(7);
			modelToHour.addElement(8);
			modelToHour.addElement(9);
			modelToHour.addElement(10);
			modelToHour.addElement(11);
			modelToHour.addElement(12);
			modelToHour.addElement(13);
			modelToHour.addElement(14);
			modelToHour.addElement(15);
			modelToHour.addElement(16);
			modelToHour.addElement(17);
			modelToHour.addElement(18);
			modelToHour.addElement(19);
			modelToHour.addElement(20);
			modelToHour.addElement(21);
			modelToHour.addElement(22);
		}
		return jComboBoxToHour;
	}

	private JComboBox getJComboBoxToMinute() {
		if (jComboBoxToMinute == null) {
			modelToMinute = new DefaultComboBoxModel<Integer>();
			jComboBoxToMinute = new JComboBox(modelToMinute);
			jComboBoxToMinute.setPreferredSize(new Dimension(50, 20));
			modelToMinute.addElement(00);
			modelToMinute.addElement(15);
			modelToMinute.addElement(30);
			modelToMinute.addElement(45);
		}
		return jComboBoxToMinute;
	}

	private JLabel getLabel_3() {
		if (label_3 == null) {
			label_3 = new JLabel("                    ");
			label_3.setPreferredSize(new Dimension(300, 14));
		}
		return label_3;
	}

	private JLabel getLabel_4() {
		if (label_4 == null) {
			label_4 = new JLabel("Pause:");
			label_4.setPreferredSize(new Dimension(40, 14));
		}
		return label_4;
	}

	private JComboBox getJComboBoxBreak() {
		if (jComboBoxBreak == null) {
			modelBreak = new DefaultComboBoxModel<Integer>();
			jComboBoxBreak = new JComboBox(modelBreak);
			jComboBoxBreak.setPreferredSize(new Dimension(50, 20));
			modelBreak.addElement(0);
			modelBreak.addElement(15);
			modelBreak.addElement(30);
			modelBreak.addElement(45);
			modelBreak.addElement(60);
		}
		return jComboBoxBreak;
	}

	private JLabel getLabel_5() {
		if (label_5 == null) {
			label_5 = new JLabel("Minuten");
			label_5.setPreferredSize(new Dimension(50, 20));
		}
		return label_5;
	}

	private JLabel getLabel_6() {
		if (label_6 == null) {
			label_6 = new JLabel("                    ");
			label_6.setPreferredSize(new Dimension(300, 14));
		}
		return label_6;
	}

	private JLabel getJLabelStandardWorkTime() {
		if (jLabelStandardWorkTime == null) {
			jLabelStandardWorkTime = new JLabel(" Standard Arbeitszeit");
			jLabelStandardWorkTime.setPreferredSize(new Dimension(530, 20));
			jLabelStandardWorkTime.setFont(new Font("Tahoma", Font.PLAIN, 15));
			jLabelStandardWorkTime.setBorder(new MatteBorder(1, 1, 1, 1,
					(Color) new Color(0, 0, 0)));
		}
		return jLabelStandardWorkTime;
	}

	private JPanel getJPanelCenterCenter() {
		if (jPanelCenterCenter == null) {
			jPanelCenterCenter = new JPanel();
			jPanelCenterCenter.setPreferredSize(new Dimension(550, 130));
			jPanelCenterCenter.setLayout(new BorderLayout(0, 0));
			jPanelCenterCenter.add(getJPanelCenterCenterNorth(),
					BorderLayout.NORTH);
			jPanelCenterCenter.add(getJPanelCenterCenterSouth(),
					BorderLayout.SOUTH);
		}
		return jPanelCenterCenter;
	}

	private JPanel getJPanelCenterCenterNorth() {
		if (jPanelCenterCenterNorth == null) {
			jPanelCenterCenterNorth = new JPanel();
			jPanelCenterCenterNorth.setPreferredSize(new Dimension(550, 40));
			jPanelCenterCenterNorth.add(getBtnCssGenerieren());
			jPanelCenterCenterNorth.add(getLblNewLabel_5());
		}
		return jPanelCenterCenterNorth;
	}

	private JButton getBtnCssGenerieren() {
		if (btnCssGenerieren == null) {
			btnCssGenerieren = new JButton("CSS generieren");
			btnCssGenerieren.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					generateCss();
				}
			});
		}
		return btnCssGenerieren;
	}

	private JPanel getJPanelCenterCenterSouth() {
		if (jPanelCenterCenterSouth == null) {
			jPanelCenterCenterSouth = new JPanel();
			jPanelCenterCenterSouth.setPreferredSize(new Dimension(550, 40));
			jPanelCenterCenterSouth.add(getJLabelMessageLine());
		}
		return jPanelCenterCenterSouth;
	}

	private JLabel getJLabelMessageLine() {
		if (jLabelMessageLine == null) {
			jLabelMessageLine = new JLabel("");
			jLabelMessageLine.setPreferredSize(new Dimension(480, 14));
		}
		return jLabelMessageLine;
	}

	private JButton getBtnPasswortAendern() {
		if (btnPasswortAendern == null) {
			btnPasswortAendern = new JButton("Passwort \u00E4ndern");
			btnPasswortAendern.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					changePassword();
				}
			});
		}
		return btnPasswortAendern;
	}

	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("");
			lblNewLabel_3.setPreferredSize(new Dimension(355, 14));
		}
		return lblNewLabel_3;
	}

	private JButton getBtnStandardarbeitszeitUebernehmen() {
		if (btnStandardarbeitszeitUebernehmen == null) {
			btnStandardarbeitszeitUebernehmen = new JButton(
					"Standardarbeitszeit \u00FCbernehmen");
			btnStandardarbeitszeitUebernehmen
					.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							changeStandardWorkTime();
						}
					});
		}
		return btnStandardarbeitszeitUebernehmen;
	}

	private JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("");
			lblNewLabel_4.setPreferredSize(new Dimension(250, 14));
		}
		return lblNewLabel_4;
	}

	private void generateCss() {
		CSS_Creator css = new CSS_Creator();
		jLabelMessageLine.setText("CSS wurde erstellt!");
	}

	private void changePassword() {
		String pwd1 = new String(passwordFieldNew.getPassword());
		String pwd2 = new String(passwordFieldRepeatNew.getPassword());
		try {
			if (sql.login(user.getName(),
					new String(passwordFieldOld.getPassword())) != null) {
				if (pwd1.compareTo(pwd2) == 0) {
					sql.setPassword(user.getId(),
							passwordFieldNew.getPassword());
					jLabelMessageLine.setText("Passwort geändert");
				} else {
					jLabelMessageLine
							.setText("Neue Passwörter stimmen nicht überein!");
				}
			} else
				jLabelMessageLine.setText("Alter Passwort ist falsch!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void changeStandardWorkTime() {
		Last_Done_Property ldp = new Last_Done_Property(user.getName(),
				jComboBoxFromHour, jComboBoxFromMinute, jComboBoxToHour,
				jComboBoxToMinute, jComboBoxBreak);
		jLabelMessageLine.setText("Standardeinstellungen geändert!");
	}

	private JLabel getLblNewLabel_5() {
		if (lblNewLabel_5 == null) {
			lblNewLabel_5 = new JLabel("");
			lblNewLabel_5.setPreferredSize(new Dimension(360, 14));
		}
		return lblNewLabel_5;
	}
}
