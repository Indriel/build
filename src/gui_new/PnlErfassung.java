package gui_new;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.util.Iterator;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.InputTableModel;
import model.MyTableModel;


import com.toedter.calendar.JDateChooser;

import data.User;
import data.WorkType;
import database.MySQL;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
public class PnlErfassung extends javax.swing.JPanel implements ActionListener{
	private JLabel lblErfassung;
	private JLabel lblUser;
	private JComboBox cbUserAdmin;
	private JLabel lblMessage;
	private JLabel lblDatum;
	private JScrollPane spTable;
	private JButton btnErfassen;
	private JTable tErfassung;
	private JLabel lblDate;
	private JComboBox cbPause;
	private JLabel lblPaus;
	private JComboBox cbMinutesBis;
	private JComboBox cbHoursBis;
	private JPanel pnlBis;
	private JLabel lblBis;
	private JComboBox cbMinutesVon;
	private JComboBox cbHoursVon;
	private JPanel pnlVon;
	private JLabel lblVon;
	private JPanel pnlErfassung;
	private JComboBox cbArt;
	private JLabel lblArt;
	private JDateChooser mainDateChooser=null;
	private MyTableModel mtm;
	private InputTableModel itm;
	private User user;
	private MySQL sql;
	private DefaultComboBoxModel<WorkType> modelWorkmode;
	private DefaultComboBoxModel<Integer> modelFromHour;
	private DefaultComboBoxModel<Integer> modelFromMinute;
	private DefaultComboBoxModel<Integer> modelToHour;
	private DefaultComboBoxModel<Integer> modelToMinute;
	private DefaultComboBoxModel<String> modelPause;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
		
	public PnlErfassung() {
		super();
		initGUI();
	}
	
	public PnlErfassung(User user){
		super();
		this.user=user;
		sql=MySQL.getInstance();
		initialize();
		initGUI();
	}
	
	
	private void initialize() {
		// TODO Auto-generated method stub
		
	}

	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(660, 505));
			this.setLayout(null);
			{
				lblErfassung = new JLabel();
				this.add(lblErfassung);
				lblErfassung.setText("Erfassung:");
				lblErfassung.setBounds(7, 5, 139, 40);
				lblErfassung.setFont(new java.awt.Font("Segoe UI",0,28));
			}
			{
				pnlErfassung = new JPanel();
				GridLayout pnlErfassungLayout = new GridLayout(3, 4);
				pnlErfassungLayout.setColumns(4);
				pnlErfassungLayout.setHgap(5);
				pnlErfassungLayout.setVgap(5);
				pnlErfassungLayout.setRows(3);
				pnlErfassung.setLayout(pnlErfassungLayout);
				this.add(pnlErfassung);
				this.add(getSpTable());
				this.add(getBtnErfassen());
				this.add(getLblMessage());
				this.add(getLblDatum());
				this.add(getLblDate());
				pnlErfassung.setBounds(7, 64, 617, 108);
				{
					lblUser = new JLabel();
					pnlErfassung.add(lblUser);
					lblUser.setText("User:");
					lblUser.setBounds(7, 33, 41, 19);
				}
				{
					ComboBoxModel cbUserAdminModel = 
							new DefaultComboBoxModel(
									new String[] { "Franz Fischer", "Item Two" });
					cbUserAdmin = new JComboBox();
					pnlErfassung.add(cbUserAdmin);
					cbUserAdmin.setModel(cbUserAdminModel);
					cbUserAdmin.setBounds(60, 31, 101, 23);
					pnlErfassung.add(lblDate);
					pnlErfassung.add(this.getMainDateChooser());
					pnlErfassung.add(this.getLblVon());
					pnlErfassung.add(this.getPnlVon());
					pnlErfassung.add(this.getLblBis());
					pnlErfassung.add(this.getPnlBis());
					pnlErfassung.add(this.getLblArt());
					pnlErfassung.add(this.getCbArt());
					pnlErfassung.add(this.getLblPaus());
					pnlErfassung.add(this.getCbPause());

				}

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
			//mainDateChooser.add(this.getLblDatum(), BorderLayout.CENTER);
		}
		return mainDateChooser;
	}

	private JLabel getLblArt() {
		if(lblArt == null) {
			lblArt = new JLabel();
			lblArt.setText("Art:");
		}
		return lblArt;
	}
	
	
	private JComboBox getCbArt() {
		if (cbArt == null) {
			modelWorkmode = new DefaultComboBoxModel<WorkType>();
			cbArt = new JComboBox(modelWorkmode);
			cbArt.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					//workModeChanged();
				}
			});
			cbArt.setPreferredSize(new Dimension(150, 20));
			
			for(Iterator<WorkType> i = sql.getWorkType().iterator(); i.hasNext(); )
			{
				WorkType wt = (WorkType)i.next();
				modelWorkmode.addElement(wt);
				if(wt.getWork_type().compareTo("Arbeitstag") == 0)
					cbArt.setSelectedItem(wt);
			}
		}
		return cbArt;
	}
	
	private JLabel getLblVon() {
		if(lblVon == null) {
			lblVon = new JLabel();
			lblVon.setText("Von:");
		}
		return lblVon;
	}
	
	private JPanel getPnlVon() {
		if(pnlVon == null) {
			pnlVon = new JPanel();
			GridLayout pnlVonLayout = new GridLayout(1, 2);
			pnlVonLayout.setColumns(2);
			pnlVonLayout.setHgap(5);
			pnlVonLayout.setVgap(5);
			pnlVon.setLayout(pnlVonLayout);
			pnlVon.add(getCbHoursVon());
			pnlVon.add(getCbMinutesVon());
		}
		return pnlVon;
	}
	
	private JComboBox getCbHoursVon() {
		if(cbHoursVon == null) {
			modelFromHour = new DefaultComboBoxModel<Integer>();
			cbHoursVon = new JComboBox();
			cbHoursVon.setModel(modelFromHour);
			for(int i=1;i<25;i++)
				modelFromHour.addElement(i);
		}
		return cbHoursVon;
	}
	
	private JComboBox getCbMinutesVon() {
		if(cbMinutesVon == null) {
		    modelFromMinute = new DefaultComboBoxModel<Integer>();
			cbMinutesVon = new JComboBox();
			cbMinutesVon.setModel(modelFromMinute);
			modelFromMinute.addElement(00);
			for(int i=15;i<60;i+=15)
				modelFromMinute.addElement(i);
		}
		return cbMinutesVon;
	}
	
	private JLabel getLblBis() {
		if(lblBis == null) {
			lblBis = new JLabel();
			lblBis.setText("Bis:");
		}
		return lblBis;
	}
	
	private JPanel getPnlBis() {
		if(pnlBis == null) {
			pnlBis = new JPanel();
			GridLayout pnlBisLayout = new GridLayout(1, 2);
			pnlBisLayout.setColumns(2);
			pnlBisLayout.setHgap(5);
			pnlBisLayout.setVgap(5);
			pnlBis.setLayout(pnlBisLayout);
			pnlBis.add(getCbHoursBis());
			pnlBis.add(getCbMinutesBis());
		}
		return pnlBis;
	}
	
	private JComboBox getCbHoursBis() {
		if(cbHoursBis == null) {
			modelToHour = new DefaultComboBoxModel<Integer>();
			cbHoursBis = new JComboBox();
			cbHoursBis.setModel(modelToHour);
			for(int i=1;i<25;i++)
				modelToHour.addElement(i);
		}
		return cbHoursBis;
	}
	
	private JComboBox getCbMinutesBis() {
		if(cbMinutesBis == null) {
			modelToMinute = new DefaultComboBoxModel<Integer>();
			cbMinutesBis = new JComboBox();
			cbMinutesBis.setModel(modelToMinute);
			for(int i=0;i<60;i+=15)
				modelToMinute.addElement(i);
		}
		return cbMinutesBis;
	}
	
	private JLabel getLblPaus() {
		if(lblPaus == null) {
			lblPaus = new JLabel();
			lblPaus.setText("Pause:");
		}
		return lblPaus;
	}
	
	private JComboBox getCbPause() {
		if(cbPause == null) {
			modelPause=new DefaultComboBoxModel<String>();
			cbPause = new JComboBox();
			cbPause.setModel(modelPause);
			for(int i=0;i<75;i+=15)
				modelPause.addElement(i+" Minuten");
		}
		return cbPause;
	}
	
	private JLabel getLblDate() {
		if(lblDate == null) {
			lblDate = new JLabel();
			lblDate.setText("Datum:");
			lblDate.setBounds(499, 348, 39, 16);
		}
		return lblDate;
	}
	private JTable getTErfassung() {
		if (tErfassung == null) {
			 itm = new InputTableModel();
			tErfassung = new JTable(itm);
			tErfassung.setCellSelectionEnabled(true);
			tErfassung.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent arg0) {
					itm.fireTableDataChanged();
				}
			});
			itm.setColumnModel(tErfassung.getColumnModel());
			tErfassung.setRowHeight(20);
			
			tErfassung.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		}
		return tErfassung;
	}
	
	private JScrollPane getSpTable() {
		if(spTable == null) {
			spTable = new JScrollPane();
			spTable.setBounds(12, 224, 617, 161);
			spTable.setViewportView(getTErfassung());
		}
		return spTable;
	}
	
	private JButton getBtnErfassen() {
		if(btnErfassen == null) {
			btnErfassen = new JButton();
			btnErfassen.setText("Erfassen");
			btnErfassen.setBounds(250, 407, 105, 42);
		}
		return btnErfassen;
	}
	
	private JLabel getLblMessage() {
		if(lblMessage == null) {
			lblMessage = new JLabel();
			lblMessage.setText("...");
			lblMessage.setBounds(7, 425, 617, 24);
			lblMessage.setBackground(new java.awt.Color(255,255,128));
		}
		return lblMessage;
	}
	
	private JLabel getJLabel1() {
		if(lblDatum == null) {
			lblDatum = new JLabel();
		}
		return lblDatum;
	}
	
	private JLabel getLblDatum() {
		if(lblDatum == null) {
			lblDatum = new JLabel();
			lblDatum.setBounds(389, 24, 10, 10);
			lblDatum.setOpaque(true);
		}
		return lblDatum;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btnErfassen) {
			
		}
	}

}
