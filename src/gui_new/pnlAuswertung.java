package gui_new;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import data.User;
import database.MySQL;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class pnlAuswertung extends javax.swing.JPanel implements ActionListener {
	private JLabel lblAuswertung;
	private JPanel pnlAuswertung;
	private JLabel lblMessage;
	private JTextField txtSuchtext;
	private JLabel lblSuchwort;
	private JPanel pnlBis;
	private JButton btnGenerate;
	private JComboBox<String> cbFormat;
	private JLabel lblFormat;
	private JComboBox<String> cbOption;
	private JLabel lblOption;
	private JLabel lblBis;
	private JLabel lblVon;
	private JPanel pnlVon;
	private JComboBox<User> cbMitarbeiter;
	private JLabel lblMitarbeiter;
	private JDateChooser mainDateChooser = null;
	private JDateChooser minorDateChooser = null;
	private MySQL sql;

	private User loggedUser;

	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 * 
	 * @param loggedInUser
	 */

	public pnlAuswertung(User loggedInUser) {
		super();
		this.loggedUser = loggedInUser;
		this.sql = MySQL.getInstance();
		initGUI();
	}

	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(660, 300));
			this.setLayout(null);
			this.setSize(660, 505);
			this.setOpaque(false);
			{
				lblAuswertung = new JLabel();
				this.add(lblAuswertung);
				lblAuswertung.setText("Auswertung:");
				lblAuswertung.setBounds(12, 12, 172, 37);
				lblAuswertung.setFont(new java.awt.Font("Segoe UI", 0, 28));
			}
			{
				pnlAuswertung = new JPanel();
				GridLayout pnlAuswertungLayout = new GridLayout(5, 2);
				pnlAuswertungLayout.setColumns(2);
				pnlAuswertungLayout.setHgap(5);
				pnlAuswertungLayout.setVgap(5);
				pnlAuswertungLayout.setRows(5);
				pnlAuswertung.setLayout(pnlAuswertungLayout);
				this.add(pnlAuswertung);
				pnlAuswertung.setBounds(12, 61, 623, 143);
				{
					lblMitarbeiter = new JLabel();
					pnlAuswertung.add(lblMitarbeiter);
					lblMitarbeiter.setText("Mitarbeiter:");
				}
				{
					ComboBoxModel<User> cbMitarbeiterModel = null;
					cbMitarbeiter = new JComboBox<User>();
					if(this.loggedUser.getName().equals("admin")) {
					cbMitarbeiterModel = new DefaultComboBoxModel<User>(
							this.sql.getUsers());
					}
					else {
						cbMitarbeiterModel = new DefaultComboBoxModel<User>(new User[]{this.loggedUser});
						cbMitarbeiter.setEnabled(false);
					}
					pnlAuswertung.add(cbMitarbeiter);
					cbMitarbeiter.setModel(cbMitarbeiterModel);
				}
				{
					pnlVon = new JPanel();
					GridLayout pnlTimeLayout = new GridLayout(1, 2);
					pnlTimeLayout.setColumns(2);
					pnlTimeLayout.setHgap(5);
					pnlTimeLayout.setVgap(5);
					pnlVon.setLayout(pnlTimeLayout);
					pnlAuswertung.add(pnlVon);
					pnlAuswertung.add(getPnlBis());
					pnlAuswertung.add(getLblOption());
					pnlAuswertung.add(getCbOption());
					pnlAuswertung.add(getLblFormat());
					pnlAuswertung.add(getCbFormat());
					pnlAuswertung.add(getLblSuchwort());
					pnlAuswertung.add(getTxtSuchtext());
					{
						lblVon = new JLabel();
						pnlVon.add(lblVon);
						lblVon.setText("Von:");
						pnlVon.add(this.getMainDateChooser());
					}
				}
			}
			{
				lblMessage = new JLabel();
				this.add(lblMessage);
				this.add(getBtnGenerate());
				lblMessage.setText("...");
				lblMessage.setBounds(7, 269, 641, 19);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JDateChooser getMainDateChooser() {
		if (mainDateChooser == null) {
			mainDateChooser = new JDateChooser(new Date());
			mainDateChooser
					.addPropertyChangeListener(new PropertyChangeListener() {
						public void propertyChange(PropertyChangeEvent arg0) {
							// checkIfEntryAlreadyExists();
						}
					});
			mainDateChooser.setPreferredSize(new Dimension(150, 20));
			// mainDateChooser.add(getLblArt(), BorderLayout.CENTER);
		}
		return mainDateChooser;
	}

	private JDateChooser getMinorDateChooser() {
		if (minorDateChooser == null) {
			minorDateChooser = new JDateChooser(new Date());
			minorDateChooser
					.addPropertyChangeListener(new PropertyChangeListener() {
						public void propertyChange(PropertyChangeEvent arg0) {
							// checkIfEntryAlreadyExists();
						}
					});
			minorDateChooser.setPreferredSize(new Dimension(150, 20));
			// MinorDateChooser.add(getLblArt(), BorderLayout.CENTER);
		}
		return minorDateChooser;
	}

	private JLabel getLblBis() {
		if (lblBis == null) {
			lblBis = new JLabel();
			lblBis.setText("Bis:");
		}
		return lblBis;
	}

	private JLabel getLblOption() {
		if (lblOption == null) {
			lblOption = new JLabel();
			lblOption.setText("Auswertungsmöglichkeit:");
			if(!this.loggedUser.getName().equals("admin"))
				lblOption.setVisible(false);
		}
		return lblOption;
	}

	private JComboBox<String> getCbOption() {
		if (cbOption == null) {
			ComboBoxModel<String> cbOptionModel = new DefaultComboBoxModel<String>(
					new String[] { "Zusammenfassung", "Detail", "Zeitaufzeichung"});
			cbOption = new JComboBox<String>();
			cbOption.setModel(cbOptionModel);
			if(!this.loggedUser.getName().equals("admin"))
				cbOption.setVisible(false);
		}
		return cbOption;
	}

	private JLabel getLblFormat() {
		if (lblFormat == null) {
			lblFormat = new JLabel();
			lblFormat.setText("Format:");
			if(!this.loggedUser.getName().equals("admin"))
				lblFormat.setVisible(false);
		}
		return lblFormat;
	}

	private JComboBox getCbFormat() {
		if (cbFormat == null) {
			ComboBoxModel cbFormatModel = new DefaultComboBoxModel(
					new String[] { "csv", "Item Two" });
			cbFormat = new JComboBox();
			cbFormat.setModel(cbFormatModel);
			if(!this.loggedUser.getName().equals("admin"))
				cbFormat.setVisible(false);
		}
		return cbFormat;
	}

	private JButton getBtnGenerate() {
		if (btnGenerate == null) {
			btnGenerate = new JButton();
			btnGenerate.setText("Generate");
			btnGenerate.setBounds(250, 216, 155, 42);
		}
		return btnGenerate;
	}

	private JPanel getPnlBis() {
		if (pnlBis == null) {
			pnlBis = new JPanel();
			GridLayout pnlBisLayout = new GridLayout(1, 2);
			pnlBisLayout.setColumns(2);
			pnlBisLayout.setHgap(5);
			pnlBisLayout.setVgap(5);
			pnlBis.setLayout(pnlBisLayout);
			pnlBis.add(getLblBis());
			pnlBis.add(getMinorDateChooser());
		}
		return pnlBis;
	}

	private JLabel getLblSuchwort() {
		if (lblSuchwort == null) {
			lblSuchwort = new JLabel();
			lblSuchwort.setText("Suchtext:");
			if(!this.loggedUser.getName().equals("admin"))
				lblSuchwort.setVisible(false);
		}
		return lblSuchwort;
	}

	private JTextField getTxtSuchtext() {
		if (txtSuchtext == null) {
			txtSuchtext = new JTextField();
			if(!this.loggedUser.getName().equals("admin"))
				txtSuchtext.setVisible(false);
		}
		return txtSuchtext;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnGenerate) {
			if(this.loggedUser.getName().equals("admin")) {
				User usr = (User) this.cbMitarbeiter.getSelectedItem();
				String format = (String) this.cbFormat.getSelectedItem();
				String auswertung = (String) this.cbOption.getSelectedItem();
				Date von = this.mainDateChooser.getDate();
				Date bis = this.minorDateChooser.getDate();
				this.createResultAdmin(usr,format,auswertung,von,bis);
			}
			else {
				Date von = this.mainDateChooser.getDate();
				Date bis = this.minorDateChooser.getDate();
				this.createResult(von,bis);
			}
		}
	}

	private void createResultAdmin(User usr, String format, String auswertung, Date von, Date bis) {
		// TODO Auto-generated method stub
		
	}

	private void createResult(Date von, Date bis) {
		// TODO Auto-generated method stub
		
	}

}
