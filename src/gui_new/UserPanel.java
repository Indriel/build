package gui_new;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Time;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.BevelBorder;

import model.TimeCBModel;
import data.User;
import database.MySQL;
import database.WrongPasswordException;

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
public class UserPanel extends javax.swing.JPanel implements ActionListener{
	private JPanel panelUserPasswortAendern;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JButton btnStandZeitFestlegen;
	private JLabel lblStandZeitInfo;
	private JComboBox<String> cmbStandZeitPause;
	private JComboBox<String> cmbStandZeitBisMIN;
	private JComboBox<String> cmbStandZeitBisH;
	private JComboBox<String> cmbStandZeitVonMIN;
	private JComboBox<String> cmbStandZeitVonH;
	private JLabel jLabel8;
	private JLabel jLabel7;
	private JLabel jLabel6;
	private JButton btnPasswortAendern;
	private JLabel lblPasswortAendernInfo;
	private JPasswordField txtPasswortAendernNeuWdh;
	private JPasswordField txtPasswortAendernNeu;
	private JPasswordField txtPasswortAendernAlt;
	private JLabel jLabel1;
	private JPanel panelUserArbeitszeitFestlegen;
	
	private MySQL database;
	private User loggedInUser;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	 * @param loggedInUser 
	*/
		
	public UserPanel(User loggedInUser) {
		super();
		this.loggedInUser = loggedInUser;
		this.database = MySQL.getInstance();
		initGUI();
	}
	
	private void initGUI() {
		try {
			BoxLayout thisLayout = new BoxLayout(this, javax.swing.BoxLayout.Y_AXIS);
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(723, 497));
			{
				panelUserPasswortAendern = new JPanel();
				this.add(panelUserPasswortAendern);
				panelUserPasswortAendern.setLayout(null);
				{
					jLabel1 = new JLabel();
					panelUserPasswortAendern.add(jLabel1);
					jLabel1.setText("Passwort ändern");
					jLabel1.setBounds(12, 12, 699, 21);
					jLabel1.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				}
				{
					jLabel3 = new JLabel();
					panelUserPasswortAendern.add(jLabel3);
					jLabel3.setText("Altes Passwort:");
					jLabel3.setBounds(12, 52, 107, 21);
				}
				{
					jLabel4 = new JLabel();
					panelUserPasswortAendern.add(jLabel4);
					jLabel4.setText("Neues Passwort:");
					jLabel4.setBounds(12, 95, 107, 21);
				}
				{
					jLabel5 = new JLabel();
					panelUserPasswortAendern.add(jLabel5);
					jLabel5.setText("Neues Passwort wiederholen:");
					jLabel5.setBounds(12, 143, 199, 21);
				}
				{
					txtPasswortAendernAlt = new JPasswordField();
					panelUserPasswortAendern.add(txtPasswortAendernAlt);
					txtPasswortAendernAlt.setBounds(228, 49, 218, 28);
				}
				{
					txtPasswortAendernNeu = new JPasswordField();
					panelUserPasswortAendern.add(txtPasswortAendernNeu);
					txtPasswortAendernNeu.setBounds(228, 92, 218, 28);
				}
				{
					txtPasswortAendernNeuWdh = new JPasswordField();
					panelUserPasswortAendern.add(txtPasswortAendernNeuWdh);
					txtPasswortAendernNeuWdh.setBounds(229, 140, 217, 28);
				}
				{
					lblPasswortAendernInfo = new JLabel();
					panelUserPasswortAendern.add(lblPasswortAendernInfo);
					lblPasswortAendernInfo.setBounds(12, 204, 699, 22);
					lblPasswortAendernInfo.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				}
				{
					btnPasswortAendern = new JButton();
					panelUserPasswortAendern.add(btnPasswortAendern);
					btnPasswortAendern.setText("Ändern");
					btnPasswortAendern.setBounds(501, 92, 81, 28);
					btnPasswortAendern.addActionListener(this);
				}
			}
			{
				panelUserArbeitszeitFestlegen = new JPanel();
				this.add(panelUserArbeitszeitFestlegen);
				panelUserArbeitszeitFestlegen.setLayout(null);
				panelUserArbeitszeitFestlegen.setBounds(231, 157, 10, 10);
				{
					jLabel2 = new JLabel();
					panelUserArbeitszeitFestlegen.add(jLabel2);
					jLabel2.setText("Standardarbeitszeit festlegen");
					jLabel2.setBounds(12, 12, 699, 21);
					jLabel2.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				}
				{
					jLabel6 = new JLabel();
					panelUserArbeitszeitFestlegen.add(jLabel6);
					jLabel6.setText("Von");
					jLabel6.setBounds(12, 52, 26, 21);
				}
				{
					jLabel7 = new JLabel();
					panelUserArbeitszeitFestlegen.add(jLabel7);
					jLabel7.setText("Bis");
					jLabel7.setBounds(12, 98, 26, 21);
				}
				{
					jLabel8 = new JLabel();
					panelUserArbeitszeitFestlegen.add(jLabel8);
					jLabel8.setText("Pause");
					jLabel8.setBounds(12, 146, 43, 21);
				}
				{
					ComboBoxModel<String> cmbStandZeitVonHModel = new TimeCBModel("hour");
					cmbStandZeitVonH = new JComboBox<String>();
					panelUserArbeitszeitFestlegen.add(cmbStandZeitVonH);
					cmbStandZeitVonH.setModel(cmbStandZeitVonHModel);
					cmbStandZeitVonH.setBounds(74, 48, 62, 28);
				}
				{
					ComboBoxModel<String> cmbStandZeitVonMINModel = new TimeCBModel("min");
					cmbStandZeitVonMIN = new JComboBox<String>();
					panelUserArbeitszeitFestlegen.add(cmbStandZeitVonMIN);
					cmbStandZeitVonMIN.setModel(cmbStandZeitVonMINModel);
					cmbStandZeitVonMIN.setBounds(156, 48, 61, 28);
				}
				{
					ComboBoxModel<String> cmbStandZeitBisHModel = new TimeCBModel("hour");
					cmbStandZeitBisH = new JComboBox<String>();
					panelUserArbeitszeitFestlegen.add(cmbStandZeitBisH);
					cmbStandZeitBisH.setModel(cmbStandZeitBisHModel);
					cmbStandZeitBisH.setBounds(74, 94, 62, 28);
				}
				{
					ComboBoxModel<String> cmbStandZeitBisMINModel = new TimeCBModel("min");
					cmbStandZeitBisMIN = new JComboBox<String>();
					panelUserArbeitszeitFestlegen.add(cmbStandZeitBisMIN);
					cmbStandZeitBisMIN.setModel(cmbStandZeitBisMINModel);
					cmbStandZeitBisMIN.setBounds(156, 94, 61, 28);
				}
				{
					ComboBoxModel<String> cmbStandZeitPauseModel = new TimeCBModel("pause");
					cmbStandZeitPause = new JComboBox<String>();
					panelUserArbeitszeitFestlegen.add(cmbStandZeitPause);
					cmbStandZeitPause.setModel(cmbStandZeitPauseModel);
					cmbStandZeitPause.setBounds(73, 142, 63, 28);
				}
				{
					lblStandZeitInfo = new JLabel();
					panelUserArbeitszeitFestlegen.add(lblStandZeitInfo);
					lblStandZeitInfo.setBounds(12, 203, 699, 23);
					lblStandZeitInfo.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				}
				{
					btnStandZeitFestlegen = new JButton();
					panelUserArbeitszeitFestlegen.add(btnStandZeitFestlegen);
					btnStandZeitFestlegen.setText("Festlegen");
					btnStandZeitFestlegen.setBounds(303, 95, 109, 28);
					btnStandZeitFestlegen.addActionListener(this);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btnPasswortAendern) {
			checkPasswords();
		}
		if(e.getSource() == this.btnStandZeitFestlegen) {
			this.setNewStandardWorkingTime();
		}
	}

	private void setNewStandardWorkingTime() {
		Time vont = Time.valueOf(this.cmbStandZeitVonH.getSelectedItem()+":"+this.cmbStandZeitVonMIN.getSelectedItem()+":00");
		Time bist = Time.valueOf(this.cmbStandZeitBisH.getSelectedItem()+":"+this.cmbStandZeitBisMIN.getSelectedItem()+":00");
		int pause = Integer.parseInt((String) this.cmbStandZeitPause.getSelectedItem());
		try {
			if(this.database.setNewStandardWorkingTime(this.loggedInUser, vont, bist, pause) == 0)
				this.lblStandZeitInfo.setText("Update der Standardarbeitszeit fehlgeschlagen");
			else {
				this.lblStandZeitInfo.setText("Update der Standardarbeitszeit erfolgreich");
			}
		} catch (SQLException e) {
			this.lblStandZeitInfo.setText("Verbindung zur Datenbank Fehlgeschlagen");
		}
	}

	private void checkPasswords() {
		try {
			this.database.checkPasswordCorrect(new String(this.txtPasswortAendernAlt.getPassword()));
			String passwd = new String(this.txtPasswortAendernNeu.getPassword());
			String passwdwdh = new String(this.txtPasswortAendernNeuWdh.getPassword());
			if(passwd.equals(passwdwdh)) {
				this.database.resetPassword(this.loggedInUser.getId(), new String(this.txtPasswortAendernNeu.getPassword()));
				this.lblPasswortAendernInfo.setText("Ändern des Passworts erfolgreich");
				this.txtPasswortAendernNeu.setText("");
				this.txtPasswortAendernNeuWdh.setText("");
			}
			else {
				this.lblPasswortAendernInfo.setText("Neues Passwort stimmt nich überein");
				this.txtPasswortAendernNeu.setText("");
				this.txtPasswortAendernNeuWdh.setText("");
				this.txtPasswortAendernAlt.setText("");
			}
		} catch (SQLException e) {
			this.lblPasswortAendernInfo.setText("Error Connecting to Database");
		} catch (WrongPasswordException e) {
			this.lblPasswortAendernInfo.setText("Altes Passwort falsch");
			this.txtPasswortAendernAlt.setText("");
		}
	}

}
