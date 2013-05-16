package gui_new;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import model.MitBerTaetCBModel;
import data.User;
import data.WorkType;
import database.DistrictInUseException;
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
public class AdminTaetigkeitenPanel extends javax.swing.JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelTaetigkeitAnlegen;
	private JPanel panelTaetigkeitAendern;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JButton btnTaetigkeitErstellen;
	private JLabel jLabel5;
	private JButton btnTaetigkeitBearbeiten;
	private JLabel lblTaetigkeitLoeschenInfo;
	private JButton btnTaetigkeitLoeschen;
	private JComboBox<WorkType> cmbTaetigkeitLoeschen;
	private JLabel jLabel7;
	private JLabel lblTaetigkeitBearbeitenInfo;
	private JTextField txtTaetigkeitBearbeiten;
	private JLabel jLabel6;
	private JComboBox<WorkType> cmbTaetigkeitBearbeiten;
	private JLabel lblTaetigkeitErstellenInfo;
	private JTextField txtTaetigkeitErstellen;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private JPanel panelTaetigkeitLoeschen;

	
	private User loggedInUser;
	private MySQL database;
	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
		
	public AdminTaetigkeitenPanel(User loggedInUser) {
		super();
		this.loggedInUser = loggedInUser;
		this.database = MySQL.getInstance();
		initGUI();
	}
	
	private void initGUI() {
		try {
			BoxLayout thisLayout = new BoxLayout(this, javax.swing.BoxLayout.Y_AXIS);
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(695, 501));
			{
				panelTaetigkeitAnlegen = new JPanel();
				this.add(panelTaetigkeitAnlegen);
				panelTaetigkeitAnlegen.setLayout(null);
				panelTaetigkeitAnlegen.setPreferredSize(new java.awt.Dimension(695, 155));
				{
					jLabel1 = new JLabel();
					panelTaetigkeitAnlegen.add(jLabel1);
					jLabel1.setText("Taetigkeit erstellen");
					jLabel1.setBounds(12, 12, 671, 26);
					jLabel1.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				}
				{
					jLabel4 = new JLabel();
					panelTaetigkeitAnlegen.add(jLabel4);
					jLabel4.setText("Tätigkeit:");
					jLabel4.setBounds(12, 50, 69, 21);
				}
				{
					txtTaetigkeitErstellen = new JTextField();
					panelTaetigkeitAnlegen.add(txtTaetigkeitErstellen);
					txtTaetigkeitErstellen.setBounds(123, 47, 197, 28);
				}
				{
					lblTaetigkeitErstellenInfo = new JLabel();
					panelTaetigkeitAnlegen.add(lblTaetigkeitErstellenInfo);
					lblTaetigkeitErstellenInfo.setBounds(12, 104, 671, 22);
					lblTaetigkeitErstellenInfo.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				}
				{
					btnTaetigkeitErstellen = new JButton();
					panelTaetigkeitAnlegen.add(btnTaetigkeitErstellen);
					btnTaetigkeitErstellen.setText("Erstellen");
					btnTaetigkeitErstellen.setBounds(350, 46, 105, 28);
					btnTaetigkeitErstellen.addActionListener(this);
				}
			}
			{
				panelTaetigkeitAendern = new JPanel();
				this.add(panelTaetigkeitAendern);
				panelTaetigkeitAendern.setLayout(null);
				panelTaetigkeitAendern.setBounds(197, 217, 10, 10);
				panelTaetigkeitAendern.setPreferredSize(new java.awt.Dimension(695, 172));
				{
					jLabel2 = new JLabel();
					panelTaetigkeitAendern.add(jLabel2);
					jLabel2.setText("Taetigkeit bearbeiten");
					jLabel2.setBounds(12, 12, 671, 23);
					jLabel2.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				}
				{
					jLabel5 = new JLabel();
					panelTaetigkeitAendern.add(jLabel5);
					jLabel5.setText("Tätigkeit:");
					jLabel5.setBounds(12, 45, 67, 21);
				}
				{
					ComboBoxModel<WorkType> cmbTaetigkeitBearbeitenModel = new MitBerTaetCBModel("Taetigkeit");
					cmbTaetigkeitBearbeiten = new JComboBox<WorkType>();
					panelTaetigkeitAendern.add(cmbTaetigkeitBearbeiten);
					cmbTaetigkeitBearbeiten.setModel(cmbTaetigkeitBearbeitenModel);
					cmbTaetigkeitBearbeiten.setBounds(126, 41, 189, 28);
				}
				{
					jLabel6 = new JLabel();
					panelTaetigkeitAendern.add(jLabel6);
					jLabel6.setText("Neue Tätigkeit");
					jLabel6.setBounds(12, 88, 97, 21);
				}
				{
					txtTaetigkeitBearbeiten = new JTextField();
					panelTaetigkeitAendern.add(txtTaetigkeitBearbeiten);
					txtTaetigkeitBearbeiten.setBounds(127, 85, 188, 28);
				}
				{
					lblTaetigkeitBearbeitenInfo = new JLabel();
					panelTaetigkeitAendern.add(lblTaetigkeitBearbeitenInfo);
					lblTaetigkeitBearbeitenInfo.setBounds(12, 119, 671, 23);
					lblTaetigkeitBearbeitenInfo.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				}
				{
					btnTaetigkeitBearbeiten = new JButton();
					panelTaetigkeitAendern.add(btnTaetigkeitBearbeiten);
					btnTaetigkeitBearbeiten.setText("Ändern");
					btnTaetigkeitBearbeiten.setBounds(349, 60, 106, 28);
					btnTaetigkeitBearbeiten.addActionListener(this);
				}
			}
			{
				panelTaetigkeitLoeschen = new JPanel();
				this.add(panelTaetigkeitLoeschen);
				panelTaetigkeitLoeschen.setLayout(null);
				panelTaetigkeitLoeschen.setBounds(0, -330, 695, 169);
				panelTaetigkeitLoeschen.setPreferredSize(new java.awt.Dimension(695, 196));
				{
					jLabel3 = new JLabel();
					panelTaetigkeitLoeschen.add(jLabel3);
					jLabel3.setText("Taetigkeit löschen");
					jLabel3.setBounds(12, 12, 671, 24);
					jLabel3.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				}
				{
					jLabel7 = new JLabel();
					panelTaetigkeitLoeschen.add(jLabel7);
					jLabel7.setText("Taetigkeit");
					jLabel7.setBounds(12, 58, 66, 21);
				}
				{
					ComboBoxModel<WorkType> cmbTaetigkeitLoeschenModel = new MitBerTaetCBModel("Taetigkeit");
					cmbTaetigkeitLoeschen = new JComboBox<WorkType>();
					panelTaetigkeitLoeschen.add(cmbTaetigkeitLoeschen);
					cmbTaetigkeitLoeschen.setModel(cmbTaetigkeitLoeschenModel);
					cmbTaetigkeitLoeschen.setBounds(135, 54, 178, 28);
				}
				{
					btnTaetigkeitLoeschen = new JButton();
					panelTaetigkeitLoeschen.add(btnTaetigkeitLoeschen);
					btnTaetigkeitLoeschen.setText("Löschen");
					btnTaetigkeitLoeschen.setBounds(351, 55, 97, 28);
					btnTaetigkeitLoeschen.addActionListener(this);
				}
				{
					lblTaetigkeitLoeschenInfo = new JLabel();
					panelTaetigkeitLoeschen.add(lblTaetigkeitLoeschenInfo);
					lblTaetigkeitLoeschenInfo.setBounds(12, 139, 671, 26);
					lblTaetigkeitLoeschenInfo.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btnTaetigkeitErstellen) {
			String newTaetigkeit = this.txtTaetigkeitErstellen.getText();
			try {
				this.database.newTaetigkeit(newTaetigkeit);
				this.lblTaetigkeitErstellenInfo.setText("Tätigkeit erstellt");
			} catch (SQLException e1) {
				this.lblTaetigkeitErstellenInfo.setText("Fehler beim Erstellen der Tätigkeit: " + e1.getMessage());
			}
		}
		
		else if(e.getSource() == this.btnTaetigkeitLoeschen) {
			WorkType toDel = (WorkType) this.cmbTaetigkeitLoeschen.getSelectedItem();
			try {
				this.database.deleteTaetigkeit(toDel.getId());
				this.lblTaetigkeitLoeschenInfo.setText("Löschen der Tätigkeit erfolgreich");
			} catch (SQLException e1) {
				this.lblTaetigkeitLoeschenInfo.setText("Fehler beim Löschen der Tätigkeit: " + e1.getMessage());
			} catch (DistrictInUseException e1) {
				this.lblTaetigkeitLoeschenInfo.setText(e1.getMessage());
			}
		}
		
		else if(e.getSource() == this.btnTaetigkeitBearbeiten) {
			WorkType wt = (WorkType) this.cmbTaetigkeitBearbeiten.getSelectedItem();
			String newName = this.txtTaetigkeitBearbeiten.getText();
			try {
				this.database.updateTaetigkeit(wt.getId(), newName);
				this.lblTaetigkeitBearbeitenInfo.setText("Bearbeiten der Tätigkeit erfolgreich");
			} catch (SQLException e1) {
				this.lblTaetigkeitBearbeitenInfo.setText("Bearbeiten der Tätigkeit fehlgeschlagen: " + e1.getMessage());
			}
		}
	}

}
