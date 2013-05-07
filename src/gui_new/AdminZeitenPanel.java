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

import com.toedter.calendar.JDateChooser;

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
public class AdminZeitenPanel extends javax.swing.JPanel implements ActionListener{
	private JPanel panelZeitenStandard;
	private JPanel panelSSollstunden;
	private JPanel panelSpezSollstunden;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel lblSpezSollstundenNeu;
	private JComboBox cmbSpezSollstundenNeu;
	private JLabel jLabel9;
	private JComboBox cmbSpezSollstundenUser;
	private JLabel jLabel11;
	private JLabel jLabel10;
	private JButton btnSpezSollstundenFestlegen;
	private JLabel jLabel8;
	private JLabel lblSSollstunden;
	private JButton btnSSollstundenFestlegen;
	private JButton btnStandardzeitFestlegen;
	private JComboBox cmbSSollstundenNeu;
	private JLabel jLabel7;
	private JComboBox cmbStandardzeitPause;
	private JComboBox cmbStandardzeitBisMIN;
	private JComboBox cmbStandardzeitBisH;
	private JComboBox cmbStandardzeitVonMIN;
	private JComboBox cmbStandardzeitVonH;
	private JLabel lblStandardzeitInfo;
	private JLabel jLabel6;
	private JLabel jLabel1;
	private JDateChooser choseAenderungszeit;
	private JDateChooser choseAenderungszeitNonSpez;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
		
	public AdminZeitenPanel() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			BoxLayout thisLayout = new BoxLayout(this, javax.swing.BoxLayout.Y_AXIS);
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(645, 589));
			{
				panelZeitenStandard = new JPanel();
				this.add(panelZeitenStandard);
				panelZeitenStandard.setLayout(null);
				{
					jLabel1 = new JLabel();
					panelZeitenStandard.add(jLabel1);
					jLabel1.setText("Standardzeit festlegen");
					jLabel1.setBounds(12, 12, 621, 21);
					jLabel1.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				}
				{
					jLabel4 = new JLabel();
					panelZeitenStandard.add(jLabel4);
					jLabel4.setText("Von:");
					jLabel4.setBounds(12, 45, 29, 21);
				}
				{
					jLabel5 = new JLabel();
					panelZeitenStandard.add(jLabel5);
					jLabel5.setText("Bis:");
					jLabel5.setBounds(12, 76, 29, 21);
				}
				{
					jLabel6 = new JLabel();
					panelZeitenStandard.add(jLabel6);
					jLabel6.setText("Pause:");
					jLabel6.setBounds(12, 107, 41, 21);
				}
				{
					lblStandardzeitInfo = new JLabel();
					panelZeitenStandard.add(lblStandardzeitInfo);
					lblStandardzeitInfo.setBounds(12, 151, 621, 10);
					lblStandardzeitInfo.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				}
				{
					ComboBoxModel cmbStandardzeitVonHModel = 
							new DefaultComboBoxModel(
									new String[] { "10", "Item Two" });
					cmbStandardzeitVonH = new JComboBox();
					panelZeitenStandard.add(cmbStandardzeitVonH);
					cmbStandardzeitVonH.setModel(cmbStandardzeitVonHModel);
					cmbStandardzeitVonH.setBounds(59, 41, 60, 28);
				}
				{
					ComboBoxModel cmbStandardzeitVonMINModel = 
							new DefaultComboBoxModel(
									new String[] { "00", "Item Two" });
					cmbStandardzeitVonMIN = new JComboBox();
					panelZeitenStandard.add(cmbStandardzeitVonMIN);
					cmbStandardzeitVonMIN.setModel(cmbStandardzeitVonMINModel);
					cmbStandardzeitVonMIN.setBounds(131, 41, 57, 28);
				}
				{
					ComboBoxModel cmbStandardzeitBisHModel = 
							new DefaultComboBoxModel(
									new String[] { "18", "Item Two" });
					cmbStandardzeitBisH = new JComboBox();
					panelZeitenStandard.add(cmbStandardzeitBisH);
					cmbStandardzeitBisH.setModel(cmbStandardzeitBisHModel);
					cmbStandardzeitBisH.setBounds(59, 75, 60, 28);
				}
				{
					ComboBoxModel cmbStandardzeitBisMINModel = 
							new DefaultComboBoxModel(
									new String[] { "30", "Item Two" });
					cmbStandardzeitBisMIN = new JComboBox();
					panelZeitenStandard.add(cmbStandardzeitBisMIN);
					cmbStandardzeitBisMIN.setModel(cmbStandardzeitBisMINModel);
					cmbStandardzeitBisMIN.setBounds(131, 75, 57, 28);
				}
				{
					ComboBoxModel cmbStandardzeitPauseModel = 
							new DefaultComboBoxModel(
									new String[] { "30", "Item Two" });
					cmbStandardzeitPause = new JComboBox();
					panelZeitenStandard.add(cmbStandardzeitPause);
					cmbStandardzeitPause.setModel(cmbStandardzeitPauseModel);
					cmbStandardzeitPause.setBounds(59, 109, 60, 28);
				}
				{
					btnStandardzeitFestlegen = new JButton();
					panelZeitenStandard.add(btnStandardzeitFestlegen);
					btnStandardzeitFestlegen.setText("Festlegen");
					btnStandardzeitFestlegen.setBounds(275, 76, 96, 28);
				}
			}
			{
				panelSSollstunden = new JPanel();
				this.add(panelSSollstunden);
				panelSSollstunden.setLayout(null);
				panelSSollstunden.setBounds(166, 295, 10, 10);
				{
					jLabel2 = new JLabel();
					panelSSollstunden.add(jLabel2);
					jLabel2.setText("Standard Sollstunden");
					jLabel2.setBounds(12, 12, 621, 21);
					jLabel2.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				}
				{
					jLabel7 = new JLabel();
					panelSSollstunden.add(jLabel7);
					jLabel7.setText("Sollstunden Neu");
					jLabel7.setBounds(12, 57, 119, 21);
				}
				{
					ComboBoxModel cmbSSollstundenNeuModel = 
							new DefaultComboBoxModel(
									new String[] { "09", "Item Two" });
					cmbSSollstundenNeu = new JComboBox();
					panelSSollstunden.add(cmbSSollstundenNeu);
					cmbSSollstundenNeu.setModel(cmbSSollstundenNeuModel);
					cmbSSollstundenNeu.setBounds(149, 57, 69, 28);
				}
				{
					btnSSollstundenFestlegen = new JButton();
					panelSSollstunden.add(btnSSollstundenFestlegen);
					btnSSollstundenFestlegen.setText("Festlegen");
					btnSSollstundenFestlegen.setBounds(275, 58, 99, 28);
				}
				{
					lblSSollstunden = new JLabel();
					panelSSollstunden.add(lblSSollstunden);
					lblSSollstunden.setBounds(12, 164, 621, 10);
					lblSSollstunden.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				}
				{
					this.choseAenderungszeitNonSpez = new JDateChooser();
					this.panelSSollstunden.add(choseAenderungszeitNonSpez);
					choseAenderungszeitNonSpez.setBounds(149, 100, 126, 24);
				}
				{
					jLabel11 = new JLabel();
					panelSSollstunden.add(jLabel11);
					jLabel11.setText("Änderungsdatum");
					jLabel11.setBounds(12, 103, 114, 21);
				}
			}
			{
				panelSpezSollstunden = new JPanel();
				this.add(panelSpezSollstunden);
				panelSpezSollstunden.setLayout(null);
				panelSpezSollstunden.setBounds(208, 92, 10, 10);
				{
					jLabel3 = new JLabel();
					panelSpezSollstunden.add(jLabel3);
					jLabel3.setText("Spezifische Sollstunden");
					jLabel3.setBounds(12, 12, 621, 21);
					jLabel3.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				}
				{
					jLabel8 = new JLabel();
					panelSpezSollstunden.add(jLabel8);
					jLabel8.setText("Benutzer:");
					jLabel8.setBounds(12, 45, 70, 21);
				}
				{
					ComboBoxModel cmbSpezSollstundenUserModel = 
							new DefaultComboBoxModel(
									new String[] { "Angestellter 1", "Item Two" });
					cmbSpezSollstundenUser = new JComboBox();
					panelSpezSollstunden.add(cmbSpezSollstundenUser);
					cmbSpezSollstundenUser.setModel(cmbSpezSollstundenUserModel);
					cmbSpezSollstundenUser.setBounds(145, 41, 128, 28);
				}
				{
					jLabel9 = new JLabel();
					panelSpezSollstunden.add(jLabel9);
					jLabel9.setText("Sollstunden Neu");
					jLabel9.setBounds(12, 84, 108, 21);
				}
				{
					ComboBoxModel cmbSpezSollstundenNeuModel = 
							new DefaultComboBoxModel(
									new String[] { "09", "Item Two" });
					cmbSpezSollstundenNeu = new JComboBox();
					panelSpezSollstunden.add(cmbSpezSollstundenNeu);
					cmbSpezSollstundenNeu.setModel(cmbSpezSollstundenNeuModel);
					cmbSpezSollstundenNeu.setBounds(145, 80, 69, 28);
				}
				{
					lblSpezSollstundenNeu = new JLabel();
					panelSpezSollstunden.add(lblSpezSollstundenNeu);
					lblSpezSollstundenNeu.setBounds(12, 164, 621, 10);
					lblSpezSollstundenNeu.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				}
				{
					btnSpezSollstundenFestlegen = new JButton();
					panelSpezSollstunden.add(btnSpezSollstundenFestlegen);
					btnSpezSollstundenFestlegen.setText("Festlegen");
					btnSpezSollstundenFestlegen.setBounds(273, 77, 96, 28);
				}
				{
					choseAenderungszeit = new JDateChooser();
					panelSpezSollstunden.add(choseAenderungszeit);
					choseAenderungszeit.setBounds(145, 116, 128, 22);
				}
				{
					jLabel10 = new JLabel();
					panelSpezSollstunden.add(jLabel10);
					jLabel10.setText("Änderungsdatum");
					jLabel10.setBounds(12, 117, 121, 21);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btnSpezSollstundenFestlegen) {
			
		}
		
		else if(e.getSource() == this.btnSSollstundenFestlegen) {
			
		}
		
		else if(e.getSource() == this.btnStandardzeitFestlegen) {
			
		}
	}

}
