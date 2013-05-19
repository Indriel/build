package gui_new;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.TimeZone;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.InputTableModel;
import model.MyTableModel;

import com.toedter.calendar.JDateChooser;

import data.User;
import data.WorkType;
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
public class PnlErfassung extends javax.swing.JPanel {
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
	private JDateChooser mainDateChooser = null;
	private MyTableModel mtm;
	private InputTableModel itm;
	private User user = new User(2, "Pate");
	private MySQL sql;
	private DefaultComboBoxModel<WorkType> modelWorkmode;
	private DefaultComboBoxModel<String> modelFromHour;
	private DefaultComboBoxModel<String> modelFromMinute;
	private DefaultComboBoxModel<String> modelToHour;
	private DefaultComboBoxModel<String> modelToMinute;
	private DefaultComboBoxModel<String> modelPause;
	private DefaultComboBoxModel<User> modelUser = new DefaultComboBoxModel<User>();
	private Time[] standard = new Time[2];
	private int pause = 0;
	private Vector<User> vecUsers = new Vector<User>();

	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 * 
	 * @throws SQLException
	 */

	public PnlErfassung() throws SQLException {
		super();
		this.user = new User(2, "Pate");
		sql = MySQL.getInstance();
		initialize();
		initGUI();
	}

	public PnlErfassung(User user) throws SQLException {
		super();
		this.user = user;
		sql = MySQL.getInstance();
		initialize();
		initGUI();
	}

	private void initialize() throws SQLException {
		// TODO Auto-generated method stub
		standard = sql.getStandardTimes(user.getId());
		pause = sql.getPause(user.getId());
		setUsers();

	}

	private void setUsers() throws SQLException {
		// TODO Auto-generated method stub
		if (user.getName().compareTo("admin") == 0) {
			vecUsers = sql.getUsers();
		} else {
			vecUsers.add(user);

		}
		Iterator<User> i = vecUsers.iterator();
		while (i.hasNext()) {
			modelUser.addElement(i.next());
		}
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
				lblErfassung.setFont(new java.awt.Font("Segoe UI", 0, 28));
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

					cbUserAdmin = new JComboBox();
					pnlErfassung.add(cbUserAdmin);
					cbUserAdmin.setModel(modelUser);
					cbUserAdmin.setBounds(60, 31, 101, 23);
					cbUserAdmin.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
							cbUserChanged();
						}

					});
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

	private void cbUserChanged() {
		// TODO Auto-generated method stub
		standard = sql
				.getStandardTimes(((User) (cbUserAdmin.getSelectedItem()))
						.getId());
		pause = sql.getPause(((User) (cbUserAdmin.getSelectedItem())).getId());
		cbHoursVon.setSelectedIndex(standard[0].getHours() - 6);
		cbMinutesVon.setSelectedIndex((standard[0].getMinutes() / 15));
		cbHoursBis.setSelectedIndex(standard[1].getHours() - 6);
		cbMinutesBis.setSelectedIndex((standard[1].getMinutes() / 15));
		cbPause.setSelectedItem(pause + " Minuten");
	}

	private JDateChooser getMainDateChooser() {
		if (mainDateChooser == null) {
			mainDateChooser = new JDateChooser(new Date());
			mainDateChooser
					.addPropertyChangeListener(new PropertyChangeListener() {
						public void propertyChange(PropertyChangeEvent arg0) {
							 checkIfEntryAlreadyExists();
						}
					});
			mainDateChooser.setPreferredSize(new Dimension(150, 20));
			// mainDateChooser.add(this.getLblDatum(), BorderLayout.CENTER);
		}
		return mainDateChooser;
	}
	private void checkIfEntryAlreadyExists() {
		Vector<Vector<String>> values;
		int userID = 0;
		Date date;
		if(cbUserAdmin != null) {
			try {
			userID = ( (User)cbUserAdmin .getSelectedItem()).getId();
			}
			catch(NullPointerException npe) { return; }
		}
		else return;
		SimpleDateFormat df;
		
		if(mainDateChooser.getDate() != null) {
			date = mainDateChooser.getDate();
		}	
		
		else return;
		if(sql.entryAlreadyExists(userID, date)) {
			values = sql.getActivities(userID, date);
			if(itm != null) 
				itm.setLines(values);
		}
		
		else {
			if(itm != null)
				itm.reset();
		}
	}
	private JLabel getLblArt() {
		if (lblArt == null) {
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
					workModeChanged();
				}

				private void workModeChanged() {
					// TODO Auto-generated method stub
					if (!(cbArt.getSelectedItem().toString()
							.equals(("Arbeitstag")))) {
						tErfassung.setEnabled(false);
						cbHoursVon.setEnabled(false);
						cbHoursBis.setEnabled(false);
						cbMinutesVon.setEnabled(false);
						cbMinutesBis.setEnabled(false);
						cbPause.setEnabled(false);
					} else {
						if (cbPause != null) {
							tErfassung.setEnabled(true);
							cbHoursVon.setEnabled(true);
							cbHoursBis.setEnabled(true);
							cbMinutesVon.setEnabled(true);
							cbMinutesBis.setEnabled(true);
							cbPause.setEnabled(true);
						}
					}

				}
			});
			cbArt.setPreferredSize(new Dimension(150, 20));

			for (Iterator<WorkType> i = sql.getWorkType().iterator(); i
					.hasNext();) {
				WorkType wt = (WorkType) i.next();
				modelWorkmode.addElement(wt);
				if (wt.getWork_type().compareTo("Arbeitstag") == 0)
					cbArt.setSelectedItem(wt);
			}
		}
		return cbArt;
	}

	private JLabel getLblVon() {
		if (lblVon == null) {
			lblVon = new JLabel();
			lblVon.setText("Von:");
		}
		return lblVon;
	}

	private JPanel getPnlVon() {
		if (pnlVon == null) {
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
		if (cbHoursVon == null) {
			modelFromHour = new DefaultComboBoxModel<String>();
			cbHoursVon = new JComboBox();
			cbHoursVon.setModel(modelFromHour);
			for (int i = 6; i < 25; i++) {
				if (i < 10)
					modelFromHour.addElement("0" + i);
				else
					modelFromHour.addElement("" + i);
				if (i == standard[0].getHours()) {
					cbHoursVon.setSelectedIndex(i - 6);
				}
			}

		}
		return cbHoursVon;
	}

	private JComboBox getCbMinutesVon() {
		if (cbMinutesVon == null) {
			modelFromMinute = new DefaultComboBoxModel<String>();
			cbMinutesVon = new JComboBox();
			cbMinutesVon.setModel(modelFromMinute);
			for (int i = 0; i < 60; i += 15) {
				if (i < 10)
					modelFromMinute.addElement("0" + i);
				else
					modelFromMinute.addElement("" + i);
				if (i == standard[0].getMinutes()) {
					cbMinutesVon.setSelectedIndex(i / 15);
				}
			}
		}
		return cbMinutesVon;
	}

	private JLabel getLblBis() {
		if (lblBis == null) {
			lblBis = new JLabel();
			lblBis.setText("Bis:");
		}
		return lblBis;
	}

	private JPanel getPnlBis() {
		if (pnlBis == null) {
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
		if (cbHoursBis == null) {
			modelToHour = new DefaultComboBoxModel<String>();
			cbHoursBis = new JComboBox();
			cbHoursBis.setModel(modelToHour);
			for (int i = 6; i < 25; i++) {
				if (i < 10)
					modelToHour.addElement("0" + i);
				else
					modelToHour.addElement("" + i);
				if (i == standard[1].getHours()) {
					cbHoursBis.setSelectedIndex(i - 6);
				}
			}
		}
		return cbHoursBis;
	}

	private JComboBox getCbMinutesBis() {
		if (cbMinutesBis == null) {
			modelToMinute = new DefaultComboBoxModel<String>();
			cbMinutesBis = new JComboBox();
			cbMinutesBis.setModel(modelToMinute);
			for (int i = 0; i < 60; i += 15) {
				if (i < 10)
					modelToMinute.addElement("0" + i);
				else
					modelToMinute.addElement("" + i);
				if (i == standard[1].getMinutes()) {
					cbMinutesBis.setSelectedIndex(i / 15);
				}
			}
		}
		return cbMinutesBis;
	}

	private JLabel getLblPaus() {
		if (lblPaus == null) {
			lblPaus = new JLabel();
			lblPaus.setText("Pause:");
		}
		return lblPaus;
	}

	private JComboBox getCbPause() {
		if (cbPause == null) {
			modelPause = new DefaultComboBoxModel<String>();
			cbPause = new JComboBox();
			cbPause.setModel(modelPause);
			for (int i = 0; i < 75; i += 15) {
				modelPause.addElement(i + " Minuten");
				if (i == pause)
					cbPause.setSelectedIndex(i / 15);
			}
		}
		return cbPause;
	}

	private JLabel getLblDate() {
		if (lblDate == null) {
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

			tErfassung.putClientProperty("terminateEditOnFocusLost",
					Boolean.TRUE);
		}
		return tErfassung;
	}

	private JScrollPane getSpTable() {
		if (spTable == null) {
			spTable = new JScrollPane();
			spTable.setBounds(12, 224, 617, 161);
			spTable.setViewportView(getTErfassung());
		}
		return spTable;
	}

	private JButton getBtnErfassen() {
		if (btnErfassen == null) {
			btnErfassen = new JButton();
			btnErfassen.setText("Erfassen");
			btnErfassen.setBounds(250, 407, 105, 42);
			btnErfassen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					ClickErfassen();
				}

			});
		}
		return btnErfassen;
	}

	private void ClickErfassen() {
		// TODO Auto-generated method stub
		boolean correctInput = true;
		Date date;
		 int userID = ( (User)cbUserAdmin.getSelectedItem()).getId();
		SimpleDateFormat df;

		if (mainDateChooser.getDate() != null) {
			date = mainDateChooser.getDate();
			df = new SimpleDateFormat("yyyy-MM-dd");
			df.setTimeZone(TimeZone.getDefault());
			lblMessage.setText("");
			// lblMessage.setText(df.format( dt ));
		}
		else {
			lblMessage
					.setText("Bitte überprüfen Sie das Datumsformat! (tt.mm.yyyy)");
			correctInput = false;
			return;
		}

		int fromHour = new Integer(cbHoursVon.getSelectedItem().toString());
		int fromMinute = new Integer(cbMinutesVon.getSelectedItem().toString());
		int toHour = new Integer(cbHoursBis.getSelectedItem().toString());
		int toMinute = new Integer(cbMinutesBis.getSelectedItem().toString());

		if ((fromHour > toHour)
				|| (fromHour == toHour && fromMinute >= toMinute)
				&& modelWorkmode.getSelectedItem().toString()
						.compareTo("Arbeitstag") == 0) {
			lblMessage
					.setText("Bitte überprüfen Sie die Angabe der Tageszeiten!");
			correctInput = false;
			return;
		}

		else
			lblMessage.setText("");

		int pause;

		try {
			pause = new Integer(
					cbPause.getSelectedItem().toString().split(" ")[0]);
			lblMessage.setText("");
		} catch (NumberFormatException nfe) {
			lblMessage
					.setText("Bitte geben Sie für die Dauer der Pause eine ganze Zahl ein!");
			correctInput = false;
			return;
		}

		if (correctInput) {

			int rows = itm.getRowCount() - 1;

			if (rows == 0
					&& modelWorkmode.getSelectedItem().toString()
							.compareTo("Arbeitstag") == 0) {
				lblMessage
						.setText("Es muss mindestens eine Aktivität eingetragen werden!");
				return;
			} else
				lblMessage.setText("");

			int categoryId[] = new int[rows];
			Time durationVon[] = new Time[rows];
			String text[] = new String[rows];

			if (modelWorkmode.getSelectedItem().toString()
					.compareTo("Arbeitstag") == 0) {
				try {

					for (int i = 0; i < rows; i++) {
						String category = itm.getValueAt(i, 0).toString()
								.split(" ")[0];
						categoryId[i] = Integer.parseInt(category);
						durationVon[i] = new Time(Integer.parseInt(itm
								.getValueAt(i, 2).toString().split(":")[0]),
								Integer.parseInt(itm.getValueAt(i, 2)
										.toString().split(":")[1]), 0);
						text[i] = itm.getValueAt(i, 1).toString();
					}
				} catch (NumberFormatException nfe) {
					lblMessage.setText(nfe.getMessage());
					return;
				}
			}
			boolean checktime=checkEnteredTimes();
			boolean success=false;
			if(checktime){
				success = sql.writeEmployeeDay(userID, date, fromMinute,
						fromHour, toMinute, toHour, pause,
						((WorkType) modelWorkmode.getSelectedItem()).getId());
			}else{
				this.lblMessage.setText("Die Minuten der eingetragenen Tätigkeiten übersteigen die Arbeitszeit");
			}
			

			if (!success) {
				if(checkEnteredTimes()){
				lblMessage.setText("Eintrag für " + df.format(date)
						+ " erfolgreich überschrieben!");
				sql.deleteActivities(userID, date);
				sql.resetWorkDay(userID, date);
				sql.deleteWorkDay(userID, date);

				sql.writeEmployeeDay(userID, date, fromMinute, fromHour,
						toMinute, toHour, pause,
						((WorkType) modelWorkmode.getSelectedItem()).getId());
				}else{
					this.lblMessage.setText("Die Minuten der eingetragenen Tätigkeiten übersteigen die Arbeitszeit");
				}
			}

			else
				lblMessage.setText("Eintrag für " + df.format(date)
						+ " erfolgreich!");

			

			if (modelWorkmode.getSelectedItem().toString()
					.compareTo("Arbeitstag") == 0) {
				checktime=checkEnteredTimes();
				if(checktime){
				sql.writeEmployeeActivity(userID, date, categoryId,
						durationVon, text);
				}else{
					this.lblMessage.setText("Die Minuten der eingetragenen Tätigkeiten übersteigen die Arbeitszeit");
				}
			} else
				itm.reset();

		}

	}

	private boolean checkEnteredTimes() {
		// TODO Auto-generated method stub
		boolean ret=true;
		int minute=0;
		for(int i=0;i<itm.getRowCount();i++){
			if(itm.getValueAt(i, 2).toString().compareTo("")!=0){
			Time t1=new Time(Integer.parseInt(itm.getValueAt(i,2 ).toString().split(":")[0]),Integer.parseInt(itm.getValueAt(i, 2).toString().split(":")[1]),0);
			minute=minute+(t1.getHours()*60)+t1.getMinutes();
			}
		}
		Time t2=new Time(Integer.parseInt(cbHoursVon.getSelectedItem().toString()),Integer.parseInt(cbMinutesVon.getSelectedItem().toString()),0);
		Time t3=new Time(Integer.parseInt(cbHoursBis.getSelectedItem().toString()),Integer.parseInt(cbMinutesBis.getSelectedItem().toString()),0);
		int minutesdiff=((t3.getHours()*60)+(t3.getMinutes()))-((t2.getHours()*60)+(t3.getMinutes()));
		minutesdiff -=Integer.parseInt(cbPause.getSelectedItem().toString().split(" ")[0]);
		if(minutesdiff<minute)
			ret=false;
		return ret;
	}

	private JLabel getLblMessage() {
		if (lblMessage == null) {
			lblMessage = new JLabel();
			lblMessage.setText("...");
			lblMessage.setBounds(7, 454, 616, 27);
			lblMessage.setBackground(new java.awt.Color(255, 255, 128));
		}
		return lblMessage;
	}

	private JLabel getJLabel1() {
		if (lblDatum == null) {
			lblDatum = new JLabel();
		}
		return lblDatum;
	}

	private JLabel getLblDatum() {
		if (lblDatum == null) {
			lblDatum = new JLabel();
			lblDatum.setBounds(389, 24, 10, 10);
			lblDatum.setOpaque(true);
		}
		return lblDatum;
	}

}
