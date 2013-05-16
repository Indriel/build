package gui_new;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.MitBerTaetCBModel;

import com.toedter.calendar.JDateChooser;

import data.User;
import database.MySQL;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class AdminUserPanel extends javax.swing.JPanel implements ActionListener{

	private JPanel panelContent = null;
	private JPanel panelCreateUser = null;
	private JPanel panelChangePassword = null;
	private JPanel panelDisableUser = null;
	private JButton btnCreateUserCreate;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JButton btnDisableUserDisable;
	private JLabel lblDisableUserInfo;
	private JDateChooser jDateChooser1;
	private JLabel jLabel6;
	private JComboBox jComboBox1;
	private JLabel lblDisableUser;
	private JButton btnChangePasswordChange;
	private JLabel lblChangePasswordInfo;
	private JTextField txtChangePasswordNewPassword;
	private JComboBox cmbChangePasswordUsers;
	private JLabel lblChangePassword;
	private JLabel lblCreateUserInfo;
	private JTextField txtCreateUserPasswort;
	private JTextField txtUserNameCreateUser;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private JLabel lblName1;
	private JLabel lblCreateUser;
	private JDateChooser dchCreateUserED;

	
	private User loggedInUser;
	private MySQL database;
	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
		
	public AdminUserPanel(User loggedInUser) {
		super();
		this.loggedInUser = loggedInUser;
		this.database = MySQL.getInstance();
		initGUI();
	}
	
	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(640, 602));
			{
				panelContent = new JPanel();
				this.add(panelContent, BorderLayout.CENTER);
				panelContent.setLayout(new BoxLayout(panelContent, BoxLayout.Y_AXIS));
				{
					panelCreateUser = new JPanel();
					panelContent.add(panelCreateUser);
					panelCreateUser.setLayout(null);
					{
						{
							lblCreateUser = new JLabel();
							panelCreateUser.add(lblCreateUser);
							lblCreateUser.setText("Benutzer erstellen");
							lblCreateUser.setBounds(12, 12, 616, 23);
							lblCreateUser.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
						}
						{
							lblName1 = new JLabel();
							panelCreateUser.add(lblName1);
							lblName1.setText("Name:");
							lblName1.setBounds(12, 53, 97, 21);
						}
						{
							jLabel1 = new JLabel();
							panelCreateUser.add(jLabel1);
							jLabel1.setText("Passwort:");
							jLabel1.setBounds(12, 84, 97, 21);
						}
						{
							jLabel2 = new JLabel();
							panelCreateUser.add(jLabel2);
							jLabel2.setText("Einstelldatum:");
							jLabel2.setBounds(12, 118, 97, 21);
						}
						{
							txtUserNameCreateUser = new JTextField();
							panelCreateUser.add(txtUserNameCreateUser);
							txtUserNameCreateUser.setBounds(127, 50, 192, 28);
						}
						{
							txtCreateUserPasswort = new JTextField();
							panelCreateUser.add(txtCreateUserPasswort);
							txtCreateUserPasswort.setBounds(127, 84, 192, 28);
						}
						{
							btnCreateUserCreate = new JButton();
							panelCreateUser.add(btnCreateUserCreate);
							btnCreateUserCreate.setText("Erstellen");
							btnCreateUserCreate.setBounds(369, 84, 85, 28);
							btnCreateUserCreate.addActionListener(this);
						}
						{
							dchCreateUserED = new JDateChooser();
							panelCreateUser.add(dchCreateUserED);
							dchCreateUserED.setBounds(127, 122, 192, 24);
						}
						{
							lblCreateUserInfo = new JLabel();
							panelCreateUser.add(lblCreateUserInfo);
							lblCreateUserInfo.setBounds(12, 158, 616, 22);
							lblCreateUserInfo.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
						}
					}
					
					panelChangePassword = new JPanel();
					panelContent.add(panelChangePassword);
					panelChangePassword.setLayout(null);
					{
						lblChangePassword = new JLabel();
						panelChangePassword.add(lblChangePassword);
						lblChangePassword.setText("Passwort zurücksetzen");
						lblChangePassword.setBounds(12, 12, 616, 21);
						lblChangePassword.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
					}
					{
						jLabel3 = new JLabel();
						panelChangePassword.add(jLabel3);
						jLabel3.setText("Benutzer:");
						jLabel3.setBounds(12, 55, 115, 21);
					}
					{
						jLabel4 = new JLabel();
						panelChangePassword.add(jLabel4);
						jLabel4.setText("Neues Passwort:");
						jLabel4.setBounds(12, 82, 115, 21);
					}
					{
						ComboBoxModel cmbChangePasswordUsersModel = new MitBerTaetCBModel("Mitarbeiter");
						cmbChangePasswordUsers = new JComboBox();
						panelChangePassword.add(cmbChangePasswordUsers);
						cmbChangePasswordUsers.setModel(cmbChangePasswordUsersModel);
						cmbChangePasswordUsers.setBounds(127, 51, 185, 28);
					}
					{
						txtChangePasswordNewPassword = new JTextField();
						panelChangePassword.add(txtChangePasswordNewPassword);
						txtChangePasswordNewPassword.setBounds(127, 85, 185, 28);
					}
					{
						lblChangePasswordInfo = new JLabel();
						panelChangePassword.add(lblChangePasswordInfo);
						lblChangePasswordInfo.setBounds(12, 152, 616, 23);
						lblChangePasswordInfo.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
					}
					{
						btnChangePasswordChange = new JButton();
						panelChangePassword.add(btnChangePasswordChange);
						btnChangePasswordChange.setText("Ändern");
						btnChangePasswordChange.setBounds(380, 85, 76, 28);
						btnChangePasswordChange.addActionListener(this);
					}

					panelDisableUser = new JPanel();
					panelContent.add(panelDisableUser);
					panelDisableUser.setLayout(null);
					{
						lblDisableUser = new JLabel();
						panelDisableUser.add(lblDisableUser);
						lblDisableUser.setText("Benutzer abmelden");
						lblDisableUser.setBounds(12, 12, 616, 21);
						lblDisableUser.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
					}
					{
						ComboBoxModel<User> jComboBox1Model = new MitBerTaetCBModel("Mitarbeiter");
						jComboBox1 = new JComboBox<User>();
						panelDisableUser.add(jComboBox1);
						jComboBox1.setModel(jComboBox1Model);
						jComboBox1.setBounds(128, 61, 185, 25);
					}
					{
						jLabel5 = new JLabel();
						panelDisableUser.add(jLabel5);
						jLabel5.setText("Benutzer:");
						jLabel5.setBounds(12, 65, 116, 21);
					}
					{
						jLabel6 = new JLabel();
						panelDisableUser.add(jLabel6);
						jLabel6.setText("Abmeldedatum");
						jLabel6.setBounds(11, 124, 104, 21);
					}
					{
						jDateChooser1 = new JDateChooser();
						panelDisableUser.add(jDateChooser1);
						jDateChooser1.setBounds(127, 122, 192, 24);
					}
					{
						btnDisableUserDisable = new JButton();
						panelDisableUser.add(btnDisableUserDisable);
						btnDisableUserDisable.setText("Abmelden");
						btnDisableUserDisable.setBounds(381, 95, 93, 28);
						btnDisableUserDisable.addActionListener(this);
					}
					{
						lblDisableUserInfo = new JLabel();
						panelDisableUser.add(lblDisableUserInfo);
						lblDisableUserInfo.setBounds(12, 168, 616, 20);
						lblDisableUserInfo.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btnChangePasswordChange) {
			String newPasswd = this.txtChangePasswordNewPassword.getText();
			User u = (User) this.cmbChangePasswordUsers.getSelectedItem();
			this.database.resetPassword(u.getId(), newPasswd);
			this.lblChangePasswordInfo.setText("Aktion erfolgreich");
		}
		
		else if(e.getSource() == this.btnCreateUserCreate) {
			String userName = this.txtUserNameCreateUser.getText();
			String passwd = this.txtCreateUserPasswort.getText();
			Date d = this.dchCreateUserED.getDate();
			this.lblCreateUserInfo.setText("Mitarbeiter erstellt");
			try {
				this.database.newEmployee(userName, passwd, d);
			} catch (SQLException e1) {
				this.lblCreateUserInfo.setText("Fehler bei der Benutzererstellung: " + e1.getMessage());
			}
		}
		
		else if(e.getSource() == this.btnDisableUserDisable) {
			Date disableDate = jDateChooser1.getDate();
			User disableUser = (User) jComboBox1.getSelectedItem();
			this.lblDisableUserInfo.setText("Mitarbeiter gesperrt");
			try {
				this.database.disableUser(disableUser, disableDate);
				this.lblDisableUserInfo.setText("Aktion erfolgreich");
			} catch (SQLException e1) {
				this.lblDisableUserInfo.setText("Fehler bei der Verbindung zur Datenbank: " + e1.getMessage());
			}
		}
	}

}
