package gui;

//import html.CSS_Creator;
//import html.CSV_Creator;
//import html.HTML_Creator;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import output.Last_Done_Property;

import data.User;
import database.MySQL;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginFrame extends JFrame {

	private MySQL mysql = MySQL.getInstance();
	
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel jPanelNorth = null;
	private JLabel jLabelUser = null;
	private JTextField jTextFieldUser = null;
	private JLabel jLabelPassword = null;
	private JPasswordField jTextFieldPassword = null;
	private JPanel jPanelSouth = null;
	private JPanel jPanelCenter = null;
	private JButton jButtonSubmit = null;
	private JLabel jLabelMsgLine = null;
	
	private User user = null;

	
	public LoginFrame() {
		super();
		initialize();
//		CSS_Creator cs = new CSS_Creator();
//		HTML_Creator hc = new HTML_Creator();
//		CSV_Creator cc = new CSV_Creator();
	}


	private void initialize() {
		this.setSize(300, 180);
		this.setTitle("Login");
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		getRootPane().setDefaultButton(jButtonSubmit);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds( (dim.width/2) - (this.getWidth()/2), (dim.height/2) - (this.getHeight()/2), this.getWidth(), this.getHeight());
		Last_Done_Property ldp = new Last_Done_Property(jTextFieldUser);
	}

	
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJPanelNorth(), BorderLayout.NORTH);
			jContentPane.add(getJPanelSouth(), BorderLayout.SOUTH);
			jContentPane.add(getJPanelCenter(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	
	private JPanel getJPanelNorth() {
		if (jPanelNorth == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setVgap(10);
			jLabelPassword = new JLabel();
			jLabelPassword.setText("Passwort: ");
			jLabelPassword.setPreferredSize(new Dimension(70, 15));
			jLabelUser = new JLabel();
			jLabelUser.setText("Benutzer: ");
			jLabelUser.setPreferredSize(new Dimension(70, 16));
			jPanelNorth = new JPanel();
			jPanelNorth.setLayout(flowLayout);
			jPanelNorth.setPreferredSize(new Dimension(10, 70));
			jPanelNorth.add(jLabelUser, null);
			jPanelNorth.add(getJTextFieldUser(), null);
			jPanelNorth.add(jLabelPassword, null);
			jPanelNorth.add(getJTextFieldPassword(), null);
		}
		return jPanelNorth;
	}

	
	private JTextField getJTextFieldUser() {
		if (jTextFieldUser == null) {
			jTextFieldUser = new JTextField();
			jTextFieldUser.setPreferredSize(new Dimension(150, 20));
		}
		return jTextFieldUser;
	}

	
	private JPasswordField getJTextFieldPassword() {
		if (jTextFieldPassword == null) {
			jTextFieldPassword = new JPasswordField();
			jTextFieldPassword.setPreferredSize(new Dimension(150, 20));
			this.addComponentListener(new java.awt.event.ComponentAdapter() {
			     public void componentShown(java.awt.event.ComponentEvent e) {
			    	 jTextFieldPassword.requestFocus();
			     }
			});
		}
		return jTextFieldPassword;
	}

	
	private JPanel getJPanelSouth() {
		if (jPanelSouth == null) {
			FlowLayout flowLayout1 = new FlowLayout();
			flowLayout1.setVgap(11);
			jLabelMsgLine = new JLabel();
			jLabelMsgLine.setText("");
			jLabelMsgLine.setFont(new Font("Arial monospaced for SAP", Font.BOLD, 12));
			jPanelSouth = new JPanel();
			jPanelSouth.setLayout(flowLayout1);
			jPanelSouth.setPreferredSize(new Dimension(10, 40));
			jPanelSouth.add(jLabelMsgLine, null);
		}
		return jPanelSouth;
	}

	
	private JPanel getJPanelCenter() {
		if (jPanelCenter == null) {
			jPanelCenter = new JPanel();
			jPanelCenter.setLayout(new FlowLayout());
			jPanelCenter.add(getJButtonSubmit(), null);
		}
		return jPanelCenter;
	}

	
	private JButton getJButtonSubmit() {
		if (jButtonSubmit == null) {
			jButtonSubmit = new JButton(" Login ");
			jButtonSubmit.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					checkLogin();
				}
			});
		}
		return jButtonSubmit;
	}
	
	
	private void checkLogin() {
		String strUser     = jTextFieldUser.getText();
		char[] password = jTextFieldPassword.getPassword();
		
		if(strUser == "") {
			jLabelMsgLine.setText("Bitte Benutzername eingeben!");
			return;
		}
	
		this.user = mysql.login(strUser, password);
		
		if(user != null) {
			this.login();
		}
		else
		    jLabelMsgLine.setText("Benutzername oder Passwort falsch!");
	}

	
	private void login() {
		Main.toMenu(user);
	}
}
