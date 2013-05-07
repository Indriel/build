package gui_new;

import java.awt.BorderLayout;
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

import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

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
public class pnlAuswertung extends javax.swing.JPanel implements ActionListener{
	private JLabel lblAuswertung;
	private JPanel pnlAuswertung;
	private JLabel lblMessage;
	private JTextField txtSuchtext;
	private JLabel lblSuchwort;
	private JPanel pnlBis;
	private JButton btnGenerate;
	private JComboBox cbFormat;
	private JLabel lblFormat;
	private JComboBox cbOption;
	private JLabel lblOption;
	private JLabel lblBis;
	private JLabel lblVon;
	private JPanel pnlVon;
	private JComboBox cbMitarbeiter;
	private JLabel lblMitarbeiter;
	private JDateChooser mainDateChooser=null;
	private JDateChooser minorDateChooser=null;
	private MySQL sql;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
		
	public pnlAuswertung() {
		super();
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
				lblAuswertung.setFont(new java.awt.Font("Segoe UI",0,28));
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
					ComboBoxModel cbMitarbeiterModel = 
							new DefaultComboBoxModel(
									new String[] { "Franz Fischer", "Item Two" });
					cbMitarbeiter = new JComboBox();
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
			if(mainDateChooser == null) {
				mainDateChooser = new JDateChooser(new Date());
				mainDateChooser.addPropertyChangeListener(new PropertyChangeListener() {
					public void propertyChange(PropertyChangeEvent arg0) {
						//checkIfEntryAlreadyExists();
					}
				});
				mainDateChooser.setPreferredSize(new Dimension(150, 20));
				//mainDateChooser.add(getLblArt(), BorderLayout.CENTER);
			}
			return mainDateChooser;
		}
		private JDateChooser getMinorDateChooser() {
			if(minorDateChooser == null) {
				minorDateChooser = new JDateChooser(new Date());
				minorDateChooser.addPropertyChangeListener(new PropertyChangeListener() {
					public void propertyChange(PropertyChangeEvent arg0) {
						//checkIfEntryAlreadyExists();
					}
				});
				minorDateChooser.setPreferredSize(new Dimension(150, 20));
				//MinorDateChooser.add(getLblArt(), BorderLayout.CENTER);
			}
			return minorDateChooser;
		}
		
		private JLabel getLblBis() {
			if(lblBis == null) {
				lblBis = new JLabel();
				lblBis.setText("Bis:");
			}
			return lblBis;
		}
		
		private JLabel getLblOption() {
			if(lblOption == null) {
				lblOption = new JLabel();
				lblOption.setText("Auswertungsmöglichkeit:");
			}
			return lblOption;
		}
		
		private JComboBox getCbOption() {
			if(cbOption == null) {
				ComboBoxModel cbOptionModel = 
						new DefaultComboBoxModel(
								new String[] { "Zusammenfassung", "Item Two" });
				cbOption = new JComboBox();
				cbOption.setModel(cbOptionModel);
			}
			return cbOption;
		}
		
		private JLabel getLblFormat() {
			if(lblFormat == null) {
				lblFormat = new JLabel();
				lblFormat.setText("Format:");
			}
			return lblFormat;
		}
		
		private JComboBox getCbFormat() {
			if(cbFormat == null) {
				ComboBoxModel cbFormatModel = 
						new DefaultComboBoxModel(
								new String[] { "csv", "Item Two" });
				cbFormat = new JComboBox();
				cbFormat.setModel(cbFormatModel);
			}
			return cbFormat;
		}
		
		private JButton getBtnGenerate() {
			if(btnGenerate == null) {
				btnGenerate = new JButton();
				btnGenerate.setText("Generate");
				btnGenerate.setBounds(250, 216, 155, 42);
			}
			return btnGenerate;
		}
		
		private JPanel getPnlBis() {
			if(pnlBis == null) {
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
			if(lblSuchwort == null) {
				lblSuchwort = new JLabel();
				lblSuchwort.setText("Suchtext:");
			}
			return lblSuchwort;
		}
		
		private JTextField getTxtSuchtext() {
			if(txtSuchtext == null) {
				txtSuchtext = new JTextField();
			}
			return txtSuchtext;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == this.btnGenerate) {
				
			}
		}

}
