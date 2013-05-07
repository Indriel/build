package gui_new;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

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
public class AdminBereichePanel extends javax.swing.JPanel implements
		ActionListener {
	private JPanel panelBereichanlegen;
	private JLabel jLabel5;
	private JButton btnBereichLoeschenLoeschen;
	private JComboBox cmbBereichLoeschenBereich;
	private JLabel lblBereichLoeschenBereich;
	private JLabel lblBereichLoeschenInfo;
	private JButton btnBereichAendernAendern;
	private JTextField txtBereichAendernNeuerBereichname;
	private JLabel lblBereichname;
	private JLabel lblBereichAendernInfo;
	private JComboBox cmbBereichAendernBereich;
	private JButton btnBereichAnlegenAnlegen;
	private JLabel lblBereichAnlegenInfo;
	private JTextField txtBereichanlegenName;
	private JLabel jLabel4;
	private JLabel jLabel3;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private JPanel panelBereichloeschen;
	private JPanel panelBereichaendern;

	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */

	public AdminBereichePanel() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			BoxLayout thisLayout = new BoxLayout(this,
					javax.swing.BoxLayout.Y_AXIS);
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(703, 558));
			{
				panelBereichanlegen = new JPanel();
				this.add(panelBereichanlegen);
				panelBereichanlegen.setLayout(null);
				{
					jLabel1 = new JLabel();
					panelBereichanlegen.add(jLabel1);
					jLabel1.setText("Bereich anlegen");
					jLabel1.setBounds(12, 12, 679, 21);
					jLabel1.setBorder(BorderFactory
							.createBevelBorder(BevelBorder.LOWERED));
				}
				{
					jLabel4 = new JLabel();
					panelBereichanlegen.add(jLabel4);
					jLabel4.setText("Bereich:");
					jLabel4.setBounds(12, 55, 72, 21);
				}
				{
					txtBereichanlegenName = new JTextField();
					panelBereichanlegen.add(txtBereichanlegenName);
					txtBereichanlegenName.setBounds(166, 52, 222, 28);
				}
				{
					lblBereichAnlegenInfo = new JLabel();
					panelBereichanlegen.add(lblBereichAnlegenInfo);
					lblBereichAnlegenInfo.setBounds(12, 140, 677, 10);
					lblBereichAnlegenInfo.setBorder(BorderFactory
							.createBevelBorder(BevelBorder.LOWERED));
				}
				{
					btnBereichAnlegenAnlegen = new JButton();
					panelBereichanlegen.add(btnBereichAnlegenAnlegen);
					btnBereichAnlegenAnlegen.setText("Anlegen");
					btnBereichAnlegenAnlegen.setBounds(438, 52, 93, 28);
					btnBereichAnlegenAnlegen.addActionListener(this);
				}
			}
			{
				panelBereichaendern = new JPanel();
				this.add(panelBereichaendern);
				panelBereichaendern.setLayout(null);
				panelBereichaendern.setBounds(175, 322, 10, 10);
				{
					jLabel2 = new JLabel();
					panelBereichaendern.add(jLabel2);
					jLabel2.setText("Bereich ändern");
					jLabel2.setBounds(12, 12, 679, 21);
					jLabel2.setBorder(BorderFactory
							.createBevelBorder(BevelBorder.LOWERED));
				}
				{
					jLabel5 = new JLabel();
					panelBereichaendern.add(jLabel5);
					jLabel5.setText("Bereich:");
					jLabel5.setBounds(12, 53, 60, 21);
				}
				{
					ComboBoxModel cmbBereichAendernBereichModel = new DefaultComboBoxModel(
							new String[] { "Zentrum", "Item Two" });
					cmbBereichAendernBereich = new JComboBox();
					panelBereichaendern.add(cmbBereichAendernBereich);
					cmbBereichAendernBereich
							.setModel(cmbBereichAendernBereichModel);
					cmbBereichAendernBereich.setBounds(167, 49, 212, 28);
				}
				{
					lblBereichAendernInfo = new JLabel();
					panelBereichaendern.add(lblBereichAendernInfo);
					lblBereichAendernInfo.setBounds(12, 157, 677, 10);
					lblBereichAendernInfo.setBorder(BorderFactory
							.createBevelBorder(BevelBorder.LOWERED));
				}
				{
					lblBereichname = new JLabel();
					panelBereichaendern.add(lblBereichname);
					lblBereichname.setText("Neuer Bereichname:");
					lblBereichname.setBounds(12, 86, 142, 21);
				}
				{
					txtBereichAendernNeuerBereichname = new JTextField();
					panelBereichaendern.add(txtBereichAendernNeuerBereichname);
					txtBereichAendernNeuerBereichname.setBounds(166, 83, 213,
							28);
				}
				{
					btnBereichAendernAendern = new JButton();
					panelBereichaendern.add(btnBereichAendernAendern);
					btnBereichAendernAendern.setText("Ändern");
					btnBereichAendernAendern.setBounds(433, 83, 87, 28);
				}
			}
			{
				panelBereichloeschen = new JPanel();
				this.add(panelBereichloeschen);
				panelBereichloeschen.setLayout(null);
				panelBereichloeschen.setBounds(407, 223, 10, 10);
				{
					jLabel3 = new JLabel();
					panelBereichloeschen.add(jLabel3);
					jLabel3.setText("Bereich löschen");
					jLabel3.setBounds(12, 12, 679, 21);
					jLabel3.setBorder(BorderFactory
							.createBevelBorder(BevelBorder.LOWERED));
				}
				{
					lblBereichLoeschenInfo = new JLabel();
					panelBereichloeschen.add(lblBereichLoeschenInfo);
					lblBereichLoeschenInfo.setBounds(12, 156, 679, 10);
					lblBereichLoeschenInfo.setBorder(BorderFactory
							.createBevelBorder(BevelBorder.LOWERED));
				}
				{
					lblBereichLoeschenBereich = new JLabel();
					panelBereichloeschen.add(lblBereichLoeschenBereich);
					lblBereichLoeschenBereich.setText("Bereich:");
					lblBereichLoeschenBereich.setBounds(12, 55, 66, 21);
				}
				{
					ComboBoxModel cmbBereichLoeschenBereichModel = new DefaultComboBoxModel(
							new String[] { "Zentrum", "Item Two" });
					cmbBereichLoeschenBereich = new JComboBox();
					panelBereichloeschen.add(cmbBereichLoeschenBereich);
					cmbBereichLoeschenBereich
							.setModel(cmbBereichLoeschenBereichModel);
					cmbBereichLoeschenBereich.setBounds(170, 51, 205, 28);
				}
				{
					btnBereichLoeschenLoeschen = new JButton();
					panelBereichloeschen.add(btnBereichLoeschenLoeschen);
					btnBereichLoeschenLoeschen.setText("Löschen");
					btnBereichLoeschenLoeschen.setBounds(435, 52, 96, 28);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btnBereichAendernAendern) {
			
		}
		
		else if(e.getSource() == this.btnBereichAnlegenAnlegen) {
			
		}
		
		else if(e.getSource() == this.btnBereichLoeschenLoeschen) {
			
		}
	}

}
