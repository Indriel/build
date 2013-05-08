package gui;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import output.CSV_Creator;
import output.HTML_Creator;
import data.Month;
import data.User;
import database.MySQL;

public class OutputPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox jComboBoxEmployee;
	private JComboBox jComboBoxMonth;
	private JLabel lbl_1;
	
	private DefaultComboBoxModel<User> modelUser;
	private DefaultComboBoxModel<Month> modelMonth;
	
	private User user;
	private MySQL sql;
	private JPanel jPanelSouth;
	private JPanel jPanelSouthNorth;
	private JButton btnHtmlGenerieren;
	private JButton btnCsvGenerieren;
	private JLabel lblNewLabel;
	private JLabel label;
	private JPanel jPanelSouthSouth;
	private JLabel jLabelMessageLine;

	/**
	 * This is the default constructor
	 */
	public OutputPanel(User user) {
		super();
		this.user = user;
		sql = MySQL.getInstance();
		setPreferredSize(new Dimension(550, 400));
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(400, 500);
		this.setLayout(new BorderLayout());
		
		JPanel jPanelNorth = new JPanel();
		FlowLayout flowLayout = (FlowLayout) jPanelNorth.getLayout();
		flowLayout.setHgap(20);
		flowLayout.setVgap(30);
		jPanelNorth.setPreferredSize(new Dimension(550, 70));
		add(jPanelNorth, BorderLayout.NORTH);
		jPanelNorth.add(getJComboBoxEmployee());
		jPanelNorth.add(getJComboBoxMonth());
		jPanelNorth.add(getLbl_1());
		add(getJPanelSouth(), BorderLayout.CENTER);
		
		
	}

	private JComboBox getJComboBoxEmployee() {
		if (jComboBoxEmployee == null) {
			modelUser = new DefaultComboBoxModel<User>();
			jComboBoxEmployee = new JComboBox(modelUser);
			jComboBoxEmployee.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					setMonths();
				}
			});
			jComboBoxEmployee.setPreferredSize(new Dimension(150, 20));
			
			if(user.getName().compareTo("admin") == 0)
			{
				Vector<User> tmp = null;
				try {
					tmp = sql.getUsers();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for(Iterator<User> i = tmp.iterator(); i.hasNext();)
				{
					User usr = (User)i.next();
					modelUser.addElement(usr);
					if(usr.getName().compareTo("admin") == 0)
						jComboBoxEmployee.setSelectedItem(usr);
				}
				
			} else {
				modelUser.addElement(user);
			}
		}
		return jComboBoxEmployee;
	}
	private JComboBox getJComboBoxMonth() {
		if (jComboBoxMonth == null) {
			modelMonth = new DefaultComboBoxModel<Month>();
			jComboBoxMonth = new JComboBox(modelMonth);
			jComboBoxMonth.setPreferredSize(new Dimension(150, 20));
			
			setMonths();
		}
		return jComboBoxMonth;
	}
	private JLabel getLbl_1() {
		if (lbl_1 == null) {
			lbl_1 = new JLabel("");
			lbl_1.setPreferredSize(new Dimension(150, 20));
		}
		return lbl_1;
	}
	
	private void setMonths() {
		try {
			Vector<Month> tmp;
			if(((User)modelUser.getSelectedItem()).getName().compareTo("admin") != 0) 
				tmp = sql.getMonthByUser(((User)jComboBoxEmployee.getSelectedItem()).getId());
			else
				tmp = sql.getMonthByAllUsers();
			
			modelMonth.removeAllElements();
			for(Iterator<Month> i = tmp.iterator(); i.hasNext();)
			{
				Month month = (Month) i.next();
				modelMonth.addElement(month);
			}
		}
		catch(NullPointerException npe) { }
	}
	private JPanel getJPanelSouth() {
		if (jPanelSouth == null) {
			jPanelSouth = new JPanel();
			jPanelSouth.setLayout(new BorderLayout(0, 0));
			jPanelSouth.add(getJPanelSouthNorth(), BorderLayout.NORTH);
			jPanelSouth.add(getJPanelSouthSouth(), BorderLayout.SOUTH);
		}
		return jPanelSouth;
	}
	private JPanel getJPanelSouthNorth() {
		if (jPanelSouthNorth == null) {
			jPanelSouthNorth = new JPanel();
			jPanelSouthNorth.setPreferredSize(new Dimension(550, 100));
			jPanelSouthNorth.add(getBtnHtmlGenerieren());
			jPanelSouthNorth.add(getLblNewLabel());
			jPanelSouthNorth.add(getBtnCsvGenerieren());
			jPanelSouthNorth.add(getLabel());
		}
		return jPanelSouthNorth;
	}
	private JButton getBtnHtmlGenerieren() {
		if (btnHtmlGenerieren == null) {
			btnHtmlGenerieren = new JButton("HTML generieren");
			btnHtmlGenerieren.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					createHTML();
				}
			});
			btnHtmlGenerieren.setPreferredSize(new Dimension(140, 23));
		}
		return btnHtmlGenerieren;
	}
	private JButton getBtnCsvGenerieren() {
		if (btnCsvGenerieren == null) {
			btnCsvGenerieren = new JButton("CSV generieren");
			btnCsvGenerieren.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					createCSV();
				}
			});
			btnCsvGenerieren.setPreferredSize(new Dimension(140, 23));
		}
		return btnCsvGenerieren;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setPreferredSize(new Dimension(340, 20));
		}
		return lblNewLabel;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("");
			label.setPreferredSize(new Dimension(340, 20));
		}
		return label;
	}
	private JPanel getJPanelSouthSouth() {
		if (jPanelSouthSouth == null) {
			jPanelSouthSouth = new JPanel();
			jPanelSouthSouth.setPreferredSize(new Dimension(550, 200));
			jPanelSouthSouth.add(getJLabelMessageLine());
		}
		return jPanelSouthSouth;
	}
	private JLabel getJLabelMessageLine() {
		if (jLabelMessageLine == null) {
			jLabelMessageLine = new JLabel("");
			jLabelMessageLine.setPreferredSize(new Dimension(450, 14));
		}
		return jLabelMessageLine;
	}
	
	
	private void createHTML() {
		try {
			String month = ((Month) modelMonth.getSelectedItem()).toStringForDatabase();
			jLabelMessageLine.setText("");
			
			HTML_Creator hc = null;
			if(((User)modelUser.getSelectedItem()).getName().compareTo("admin") == 0)
				hc = new HTML_Creator(sql.getAllMitarb_tag(month));
			else
				hc = new HTML_Creator(sql.getMitarb_tag((User) modelUser.getSelectedItem(), month));
			
			try{ 
				Desktop.getDesktop().open(new File("auswertung.html"));
			}catch(Exception e){;}
		}
		catch(NullPointerException npe) {
			jLabelMessageLine.setText("Für " + modelUser.getSelectedItem().toString() + " sind keine Einträge vorhanden");
		}
		
	}
	
	
	private void createCSV() {
		try {
			int m_id = ((User) modelUser.getSelectedItem()).getId();
			String month = ((Month) modelMonth.getSelectedItem()).toStringForDatabase();
			jLabelMessageLine.setText("");
			CSV_Creator csv = new CSV_Creator(sql.getMitarb_tag((User) modelUser.getSelectedItem(), month));
			try{ 
				Desktop.getDesktop().open(new File("auswertung.csv"));
			}catch(Exception e){;}
		}
		catch(NullPointerException npe) {
			jLabelMessageLine.setText("Für " + modelUser.getSelectedItem().toString() + " sind keine Einträge vorhanden");
		}
	}
	
	public DefaultComboBoxModel<User> getUserComboBoxModel() {
		return modelUser;
	}
	
}
