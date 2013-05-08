package gui_new;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
public class UserPanel extends javax.swing.JPanel implements ActionListener{
	private JPanel panelUserPasswortAendern;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JButton btnStandZeitFestlegen;
	private JLabel lblStandZeitInfo;
	private JComboBox cmbStandZeitPause;
	private JComboBox cmbStandZeitBisMIN;
	private JComboBox cmbStandZeitBisH;
	private JComboBox cmbStandZeitVonMIN;
	private JComboBox cmbStandZeitVonH;
	private JLabel jLabel8;
	private JLabel jLabel7;
	private JLabel jLabel6;
	private JButton btnPasswortAendern;
	private JLabel lblPasswortAendernInfo;
	private JTextField txtPasswortAendernNeuWdh;
	private JTextField txtPasswortAendernNeu;
	private JTextField txtPasswortAendernAlt;
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
					txtPasswortAendernAlt = new JTextField();
					panelUserPasswortAendern.add(txtPasswortAendernAlt);
					txtPasswortAendernAlt.setBounds(228, 49, 218, 28);
				}
				{
					txtPasswortAendernNeu = new JTextField();
					panelUserPasswortAendern.add(txtPasswortAendernNeu);
					txtPasswortAendernNeu.setBounds(228, 92, 218, 28);
				}
				{
					txtPasswortAendernNeuWdh = new JTextField();
					panelUserPasswortAendern.add(txtPasswortAendernNeuWdh);
					txtPasswortAendernNeuWdh.setBounds(229, 140, 217, 28);
				}
				{
					lblPasswortAendernInfo = new JLabel();
					panelUserPasswortAendern.add(lblPasswortAendernInfo);
					lblPasswortAendernInfo.setBounds(12, 216, 699, 10);
					lblPasswortAendernInfo.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				}
				{
					btnPasswortAendern = new JButton();
					panelUserPasswortAendern.add(btnPasswortAendern);
					btnPasswortAendern.setText("Ändern");
					btnPasswortAendern.setBounds(501, 92, 68, 28);
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
					ComboBoxModel cmbStandZeitVonHModel = 
							new DefaultComboBoxModel(
									new String[] { "09", "Item Two" });
					cmbStandZeitVonH = new JComboBox();
					panelUserArbeitszeitFestlegen.add(cmbStandZeitVonH);
					cmbStandZeitVonH.setModel(cmbStandZeitVonHModel);
					cmbStandZeitVonH.setBounds(74, 48, 62, 28);
				}
				{
					ComboBoxModel cmbStandZeitVonMINModel = 
							new DefaultComboBoxModel(
									new String[] { "30", "Item Two" });
					cmbStandZeitVonMIN = new JComboBox();
					panelUserArbeitszeitFestlegen.add(cmbStandZeitVonMIN);
					cmbStandZeitVonMIN.setModel(cmbStandZeitVonMINModel);
					cmbStandZeitVonMIN.setBounds(156, 48, 61, 28);
				}
				{
					ComboBoxModel cmbStandZeitBisHModel = 
							new DefaultComboBoxModel(
									new String[] { "18", "Item Two" });
					cmbStandZeitBisH = new JComboBox();
					panelUserArbeitszeitFestlegen.add(cmbStandZeitBisH);
					cmbStandZeitBisH.setModel(cmbStandZeitBisHModel);
					cmbStandZeitBisH.setBounds(74, 94, 62, 28);
				}
				{
					ComboBoxModel cmbStandZeitBisMINModel = 
							new DefaultComboBoxModel(
									new String[] { "00", "Item Two" });
					cmbStandZeitBisMIN = new JComboBox();
					panelUserArbeitszeitFestlegen.add(cmbStandZeitBisMIN);
					cmbStandZeitBisMIN.setModel(cmbStandZeitBisMINModel);
					cmbStandZeitBisMIN.setBounds(156, 94, 61, 28);
				}
				{
					ComboBoxModel cmbStandZeitPauseModel = 
							new DefaultComboBoxModel(
									new String[] { "30", "Item Two" });
					cmbStandZeitPause = new JComboBox();
					panelUserArbeitszeitFestlegen.add(cmbStandZeitPause);
					cmbStandZeitPause.setModel(cmbStandZeitPauseModel);
					cmbStandZeitPause.setBounds(73, 142, 63, 28);
				}
				{
					lblStandZeitInfo = new JLabel();
					panelUserArbeitszeitFestlegen.add(lblStandZeitInfo);
					lblStandZeitInfo.setBounds(12, 216, 699, 10);
					lblStandZeitInfo.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				}
				{
					btnStandZeitFestlegen = new JButton();
					panelUserArbeitszeitFestlegen.add(btnStandZeitFestlegen);
					btnStandZeitFestlegen.setText("Festlegen");
					btnStandZeitFestlegen.setBounds(303, 95, 95, 28);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btnPasswortAendern) {
			
		}
		if(e.getSource() == this.btnStandZeitFestlegen) {
			
		}
	}

}
