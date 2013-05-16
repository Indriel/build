package gui;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTabbedPane;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.InsetsUIResource;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JComboBox;

import com.toedter.calendar.JDateChooser;

import data.District;
import data.User;
import database.MySQL;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.TimeZone;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTabbedPane jTabbedPane;
	private JPanel jPanelEmployeeAdmin;
	private JPanel jPanelDistrictAdmin;
	private JPanel jPanelDistrict;
	private JLabel jLabelNewDistrictHeader;
	private JLabel jLabelDistrictID;
	private JTextField jTextFieldDistrictID;
	private JLabel lblNewLabel;
	private JLabel jLabelDistrictName;
	private JTextField jTextFieldDistrictName;
	private JLabel lblNewLabel_1;
	private JButton jButtonWriteDistrict;
	private JLabel label_1;
	private JPanel jPanelCategory;
	private JLabel jLabelNewCategoryHeader;
	private JLabel jLabelCategoryID;
	private JTextField jTextFiedCategoryID;
	private JLabel label_3;
	private JLabel jLabelCategoryName;
	private JTextField jTextFieldCategoryName;
	private JLabel label_5;
	private JButton jButtonWriteCategory;
	private JLabel label_6;
	private JLabel jLabelDistrictOfCategory;
	private JComboBox jComboBoxDistrictOfCategory;
	private JLabel label;
	private JPanel jPanelNorth;
	private JLabel jLabelNewEmployee;
	private JLabel jLabelEmployeeName;
	private JTextField jTextFieldEmployeeName;
	private JLabel lblNewLabel_2;
	private JLabel jLabelPassword;
	private JTextField jTextFieldPassword;
	private JLabel label_2;
	private JLabel lblEinstellungsdatum;
	private JDateChooser hireDateChooser;
	private JLabel label_4;
	private JButton jButtonSaveNewEmployee;
	private JLabel label_7;
	private JPanel jPanelSouth;
	private JPanel jPanelSouthNorth;
	private JLabel lblMitarbeiterAbmelden;
	private JLabel jLabelRemoveEmployee;
	private JComboBox<User> jComboBoxRemoveEmployee;
	private JLabel label_10;
	private JLabel lblAbmeldungsdatum;
	private JDateChooser removeDateChooser;
	private JLabel label_14;
	private JButton jButtonRemoveEmployee;
	private JLabel label_15;
	private JPanel jPanelResetPassword;
	private JLabel lblPasswortZurcksetzen;
	private JLabel label_9;
	private JComboBox<User> jComboBoxResetPasswordName;
	private JLabel label_11;
	private JButton jButtonResetPassword;
	private JLabel label_16;
	private JLabel lblNeuesPasswort;
	private JTextField jTextFieldNewPassword;
	private JLabel label_12;
	private DefaultComboBoxModel<User> dcbmRemove = null;
	private DefaultComboBoxModel<User> dcbmReset = null;
	private DefaultComboBoxModel<District> dcbmDistrict = null;
	private MySQL sql = null;
	private JLabel jLabelMessageLineBenutzer;
	private JLabel jLabelMessageLineBereich;

	/**
	 * This is the default constructor
	 */
	public AdminPanel() {
		super();
		setPreferredSize(new Dimension(550, 500));
		sql = sql.getInstance();
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
		add(getJTabbedPane(), BorderLayout.CENTER);
	}

	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			UIManager.put("TabbedPane.selected", Color.WHITE); 
			//UIManager.put("TabbedPane.darkShadow", Color.WHITE); 
			UIManager.put("TabbedPane.contentBorderInsets", new InsetsUIResource(1,1,1,1));

			jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
			jTabbedPane.setPreferredSize(new Dimension(550, 500));
			jTabbedPane.addTab("Benutzerverwaltung", null, getJPanelEmployeeAdmin(), null);
			jTabbedPane.addTab("Bereichsverwaltung", null, getJPanelDistrictAdmin(), null);
		}
		return jTabbedPane;
	}
	private JPanel getJPanelEmployeeAdmin() {
		if (jPanelEmployeeAdmin == null) {
			jPanelEmployeeAdmin = new JPanel();
			jPanelEmployeeAdmin.setLayout(new BorderLayout(0, 0));
			jPanelEmployeeAdmin.add(getJPanelNorth(), BorderLayout.NORTH);
			jPanelEmployeeAdmin.add(getJPanelSouth(), BorderLayout.CENTER);
		}
		return jPanelEmployeeAdmin;
	}
	private JPanel getJPanelDistrictAdmin() {
		if (jPanelDistrictAdmin == null) {
			jPanelDistrictAdmin = new JPanel();
			jPanelDistrictAdmin.setLayout(new BorderLayout(0, 0));
			jPanelDistrictAdmin.add(getJPanelDistrict(), BorderLayout.NORTH);
			jPanelDistrictAdmin.add(getJPanelCategory(), BorderLayout.SOUTH);
		}
		return jPanelDistrictAdmin;
	}
	private JPanel getJPanelDistrict() {
		if (jPanelDistrict == null) {
			jPanelDistrict = new JPanel();
			jPanelDistrict.setPreferredSize(new Dimension(10, 140));
			jPanelDistrict.setMinimumSize(new Dimension(100, 100));
			jPanelDistrict.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
			jPanelDistrict.add(getJLabelNewDistrictHeader());
			jPanelDistrict.add(getJLabelDistrictID());
			jPanelDistrict.add(getJTextFieldDistrictID());
			jPanelDistrict.add(getLblNewLabel());
			jPanelDistrict.add(getJLabelDistrictName());
			jPanelDistrict.add(getJTextFieldDistrictName());
			jPanelDistrict.add(getLblNewLabel_1());
			jPanelDistrict.add(getJButtonWriteDistrict());
			jPanelDistrict.add(getLabel_1());
		}
		return jPanelDistrict;
	}
	private JLabel getJLabelNewDistrictHeader() {
		if (jLabelNewDistrictHeader == null) {
			jLabelNewDistrictHeader = new JLabel(" Neuer Bereich");
			jLabelNewDistrictHeader.setFont(new Font("Tahoma", Font.PLAIN, 15));
			jLabelNewDistrictHeader.setPreferredSize(new Dimension(530, 20));
			jLabelNewDistrictHeader.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		}
		return jLabelNewDistrictHeader;
	}
	private JLabel getJLabelDistrictID() {
		if (jLabelDistrictID == null) {
			jLabelDistrictID = new JLabel("ID:");
			jLabelDistrictID.setPreferredSize(new Dimension(110, 14));
		}
		return jLabelDistrictID;
	}
	private JTextField getJTextFieldDistrictID() {
		if (jTextFieldDistrictID == null) {
			jTextFieldDistrictID = new JTextField();
			jTextFieldDistrictID.setPreferredSize(new Dimension(120, 20));
		}
		return jTextFieldDistrictID;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setPreferredSize(new Dimension(290, 14));
		}
		return lblNewLabel;
	}
	private JLabel getJLabelDistrictName() {
		if (jLabelDistrictName == null) {
			jLabelDistrictName = new JLabel("Bezeichnung:");
			jLabelDistrictName.setPreferredSize(new Dimension(110, 14));
		}
		return jLabelDistrictName;
	}
	private JTextField getJTextFieldDistrictName() {
		if (jTextFieldDistrictName == null) {
			jTextFieldDistrictName = new JTextField();
			jTextFieldDistrictName.setPreferredSize(new Dimension(120, 20));
		}
		return jTextFieldDistrictName;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setPreferredSize(new Dimension(290, 14));
		}
		return lblNewLabel_1;
	}
	private JButton getJButtonWriteDistrict() {
		if (jButtonWriteDistrict == null) {
			jButtonWriteDistrict = new JButton("Speichern");
			jButtonWriteDistrict.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					writeDistrict();
				}
			});
			jButtonWriteDistrict.setPreferredSize(new Dimension(110, 23));
		}
		return jButtonWriteDistrict;
	}
	
	private void writeDistrict() {
		if(jTextFieldDistrictID.getText().length() == 0 ||
				jTextFieldDistrictName.getText().length() == 0) {
			jLabelMessageLineBereich.setText("ID und Bereich eingeben!");
		} else {
			try {
				if(!sql.newDistrict(Integer.parseInt(jTextFieldDistrictID.getText()), jTextFieldDistrictName.getText())) {
					jLabelMessageLineBereich.setText("ID schon vorhanden!");
				} else {
					this.updateComboBoxDistrict(dcbmDistrict);
					jLabelMessageLineBereich.setText("Bereich wurde erfasst!");
					jTextFieldDistrictID.setText("");
					jTextFieldDistrictName.setText("");
				}
			} catch (Exception e){
				jLabelMessageLineBereich.setText("ID muss eine Zahl sein!");
			}
		}
	}
	
	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("");
			label_1.setPreferredSize(new Dimension(415, 14));
		}
		return label_1;
	}
	private JPanel getJPanelCategory() {
		if (jPanelCategory == null) {
			jPanelCategory = new JPanel();
			jPanelCategory.setPreferredSize(new Dimension(10, 220));
			jPanelCategory.setMinimumSize(new Dimension(100, 100));
			jPanelCategory.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
			jPanelCategory.add(getJLabelNewCategoryHeader());
			jPanelCategory.add(getJLabelCategoryID());
			jPanelCategory.add(getJTextFiedCategoryID());
			jPanelCategory.add(getLabel_3());
			jPanelCategory.add(getJLabelCategoryName());
			jPanelCategory.add(getJTextFieldCategoryName());
			jPanelCategory.add(getLabel_5());
			jPanelCategory.add(getJLabelDistrictOfCategory());
			jPanelCategory.add(getJComboBoxDistrictOfCategory());
			jPanelCategory.add(getLabel());
			jPanelCategory.add(getJButtonWriteCategory());
			jPanelCategory.add(getLabel_6());
			jPanelCategory.add(getJLabelMessageLineBereich());
		}
		return jPanelCategory;
	}
	private JLabel getJLabelNewCategoryHeader() {
		if (jLabelNewCategoryHeader == null) {
			jLabelNewCategoryHeader = new JLabel(" Neue Kategorie");
			jLabelNewCategoryHeader.setFont(new Font("Tahoma", Font.PLAIN, 15));
			jLabelNewCategoryHeader.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			jLabelNewCategoryHeader.setPreferredSize(new Dimension(530, 20));
		}
		return jLabelNewCategoryHeader;
	}
	private JLabel getJLabelCategoryID() {
		if (jLabelCategoryID == null) {
			jLabelCategoryID = new JLabel("ID:");
			jLabelCategoryID.setPreferredSize(new Dimension(110, 14));
		}
		return jLabelCategoryID;
	}
	private JTextField getJTextFiedCategoryID() {
		if (jTextFiedCategoryID == null) {
			jTextFiedCategoryID = new JTextField();
			jTextFiedCategoryID.setPreferredSize(new Dimension(120, 20));
		}
		return jTextFiedCategoryID;
	}
	private JLabel getLabel_3() {
		if (label_3 == null) {
			label_3 = new JLabel("");
			label_3.setPreferredSize(new Dimension(290, 14));
		}
		return label_3;
	}
	private JLabel getJLabelCategoryName() {
		if (jLabelCategoryName == null) {
			jLabelCategoryName = new JLabel("Bezeichnung:");
			jLabelCategoryName.setPreferredSize(new Dimension(110, 14));
		}
		return jLabelCategoryName;
	}
	private JTextField getJTextFieldCategoryName() {
		if (jTextFieldCategoryName == null) {
			jTextFieldCategoryName = new JTextField();
			jTextFieldCategoryName.setPreferredSize(new Dimension(120, 20));
			
		}
		return jTextFieldCategoryName;
	}
	private JLabel getLabel_5() {
		if (label_5 == null) {
			label_5 = new JLabel("");
			label_5.setPreferredSize(new Dimension(290, 14));
		}
		return label_5;
	}
	private JButton getJButtonWriteCategory() {
		if (jButtonWriteCategory == null) {
			jButtonWriteCategory = new JButton("Speichern");
			jButtonWriteCategory.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					writeCategory();
				}
			});
			jButtonWriteCategory.setPreferredSize(new Dimension(110, 23));
		}
		return jButtonWriteCategory;
	}
	
	private void writeCategory() {
		if ( jTextFiedCategoryID.getText().length() != 0 && jTextFieldCategoryName.getText().length() != 0 && jComboBoxDistrictOfCategory.getSelectedItem() != null) {
			try {
				if(/*!sql.newCategory(Integer.parseInt(jTextFiedCategoryID.getText()), jTextFieldCategoryName.getText(), ((District)jComboBoxDistrictOfCategory.getSelectedItem()).getId())*/ true) {
					jLabelMessageLineBereich.setText("IDs schon vergeben");
				}
				else {
					jLabelMessageLineBereich.setText("Kategorie wurde erfasst!");
					jTextFieldCategoryName.setText("");
					jTextFiedCategoryID.setText("");
				}
			} catch (Exception e) {
				jLabelMessageLineBereich.setText("ID muss eine Zahl sein!");
			}
		} else {
			jLabelMessageLineBereich.setText("ID, Bezeichnung eingeben und Bereich wählen!");
		}
	}
	
	private JLabel getLabel_6() {
		if (label_6 == null) {
			label_6 = new JLabel("");
			label_6.setPreferredSize(new Dimension(415, 14));
		}
		return label_6;
	}
	private JLabel getJLabelDistrictOfCategory() {
		if (jLabelDistrictOfCategory == null) {
			jLabelDistrictOfCategory = new JLabel("Bereich:");
			jLabelDistrictOfCategory.setPreferredSize(new Dimension(110, 14));
		}
		return jLabelDistrictOfCategory;
	}
	private JComboBox getJComboBoxDistrictOfCategory() {
		if (jComboBoxDistrictOfCategory == null) {
			dcbmDistrict = new DefaultComboBoxModel<District>();
			jComboBoxDistrictOfCategory = new JComboBox(dcbmDistrict);
			jComboBoxDistrictOfCategory.setPreferredSize(new Dimension(120, 20));
			this.updateComboBoxDistrict(dcbmDistrict);
		}
		return jComboBoxDistrictOfCategory;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("");
			label.setPreferredSize(new Dimension(290, 14));
		}
		return label;
	}
	private JPanel getJPanelNorth() {
		if (jPanelNorth == null) {
			jPanelNorth = new JPanel();
			FlowLayout flowLayout = (FlowLayout) jPanelNorth.getLayout();
			flowLayout.setVgap(10);
			jPanelNorth.setPreferredSize(new Dimension(10, 180));
			jPanelNorth.add(getJLabelNewEmployee());
			jPanelNorth.add(getJLabelEmployeeName());
			jPanelNorth.add(getJTextFieldEmployeeName());
			jPanelNorth.add(getLblNewLabel_2());
			jPanelNorth.add(getJLabelPassword());
			jPanelNorth.add(getJTextFieldPassword());
			jPanelNorth.add(getLabel_2());
			jPanelNorth.add(getLblEinstellungsdatum());
			jPanelNorth.add(getHireDateChooser());
			jPanelNorth.add(getLabel_4());
			jPanelNorth.add(getJButtonSaveNewEmployee());
			jPanelNorth.add(getLabel_7());
		}
		return jPanelNorth;
	}
	private JDateChooser getHireDateChooser() {
		if(hireDateChooser == null) {
			hireDateChooser = new JDateChooser(new Date());
			hireDateChooser.setPreferredSize(new Dimension(120, 20));
		}
		return hireDateChooser;
	}
	private JLabel getJLabelNewEmployee() {
		if (jLabelNewEmployee == null) {
			jLabelNewEmployee = new JLabel(" Mitarbeiter anmelden");
			jLabelNewEmployee.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			jLabelNewEmployee.setPreferredSize(new Dimension(530, 20));
			jLabelNewEmployee.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return jLabelNewEmployee;
	}
	private JLabel getJLabelEmployeeName() {
		if (jLabelEmployeeName == null) {
			jLabelEmployeeName = new JLabel("Name:");
			jLabelEmployeeName.setPreferredSize(new Dimension(110, 14));
		}
		return jLabelEmployeeName;
	}
	private JTextField getJTextFieldEmployeeName() {
		if (jTextFieldEmployeeName == null) {
			jTextFieldEmployeeName = new JTextField();
			jTextFieldEmployeeName.setPreferredSize(new Dimension(120, 20));
		}
		return jTextFieldEmployeeName;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("");
			lblNewLabel_2.setPreferredSize(new Dimension(290, 14));
		}
		return lblNewLabel_2;
	}
	private JLabel getJLabelPassword() {
		if (jLabelPassword == null) {
			jLabelPassword = new JLabel("Passwort:");
			jLabelPassword.setPreferredSize(new Dimension(110, 14));
		}
		return jLabelPassword;
	}
	private JTextField getJTextFieldPassword() {
		if (jTextFieldPassword == null) {
			jTextFieldPassword = new JTextField();
			jTextFieldPassword.setPreferredSize(new Dimension(120, 20));
		}
		return jTextFieldPassword;
	}
	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("");
			label_2.setPreferredSize(new Dimension(290, 14));
		}
		return label_2;
	}
	private JLabel getLblEinstellungsdatum() {
		if (lblEinstellungsdatum == null) {
			lblEinstellungsdatum = new JLabel("Anmeldungsdatum:");
			lblEinstellungsdatum.setPreferredSize(new Dimension(110, 14));
		}
		return lblEinstellungsdatum;
	}
	private JLabel getLabel_4() {
		if (label_4 == null) {
			label_4 = new JLabel("");
			label_4.setPreferredSize(new Dimension(290, 14));
		}
		return label_4;
	}
	private JButton getJButtonSaveNewEmployee() {
		if (jButtonSaveNewEmployee == null) {
			jButtonSaveNewEmployee = new JButton("Anmelden");
			jButtonSaveNewEmployee.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					checkNewEmp();
				}
			});
			jButtonSaveNewEmployee.setPreferredSize(new Dimension(110, 23));
		}
		return jButtonSaveNewEmployee;
	}
	private void checkNewEmp() {
		Date date;
		SimpleDateFormat df;
		
		if(hireDateChooser.getDate() != null) {
			date = hireDateChooser.getDate();
			df = new SimpleDateFormat( "yyyy-MM-dd" );
			df.setTimeZone( TimeZone.getDefault() );
			String begin = df.format(date);
			
			if(jTextFieldEmployeeName.getText().length() != 0)
			{
				if(jTextFieldPassword.getText().length() != 0)
				{
					//sql.newEmployee(jTextFieldEmployeeName.getText(), jTextFieldPassword.getText(), begin);
					//sql.updateComboBoxUser(dcbmRemove);
					//sql.updateComboBoxUser(dcbmReset);
					jTextFieldEmployeeName.setText("");
					jTextFieldPassword.setText("");
					jLabelMessageLineBenutzer.setText("Benutzer wurde angelegt!");
				} else {
					jLabelMessageLineBenutzer.setText("Passwort eingeben!");
				}
			} else {
				jLabelMessageLineBenutzer.setText("Benutzernamen eingeben!");
			}
			
		}		
		
		else {
			jLabelMessageLineBenutzer.setText("Bitte überprüfen Sie das Datumsformat! (tt.mm.yyyy)");
		}
		
	}
	private JLabel getLabel_7() {
		if (label_7 == null) {
			label_7 = new JLabel("");
			label_7.setPreferredSize(new Dimension(415, 14));
		}
		return label_7;
	}
	private JPanel getJPanelSouth() {
		if (jPanelSouth == null) {
			jPanelSouth = new JPanel();
			jPanelSouth.setLayout(new BorderLayout(0, 0));
			jPanelSouth.add(getJPanelSouthNorth(), BorderLayout.NORTH);
			jPanelSouth.add(getJPanelResetPassword(), BorderLayout.CENTER);
		}
		return jPanelSouth;
	}
	private JPanel getJPanelSouthNorth() {
		if (jPanelSouthNorth == null) {
			jPanelSouthNorth = new JPanel();
			FlowLayout flowLayout = (FlowLayout) jPanelSouthNorth.getLayout();
			flowLayout.setVgap(10);
			jPanelSouthNorth.setPreferredSize(new Dimension(0, 150));
			jPanelSouthNorth.add(getLblMitarbeiterAbmelden());
			jPanelSouthNorth.add(getJLabelRemoveEmployee());
			jPanelSouthNorth.add(getJComboBoxRemoveEmployee());
			jPanelSouthNorth.add(getLabel_10());
			jPanelSouthNorth.add(getLblAbmeldungsdatum());
			jPanelSouthNorth.add(getRemoveDateChooser());
			jPanelSouthNorth.add(getLabel_14());
			jPanelSouthNorth.add(getJButtonRemoveEmployee());
			jPanelSouthNorth.add(getLabel_15());
		}
		return jPanelSouthNorth;
	}
	private JLabel getLblMitarbeiterAbmelden() {
		if (lblMitarbeiterAbmelden == null) {
			lblMitarbeiterAbmelden = new JLabel(" Mitarbeiter abmelden");
			lblMitarbeiterAbmelden.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			lblMitarbeiterAbmelden.setPreferredSize(new Dimension(530, 20));
			lblMitarbeiterAbmelden.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return lblMitarbeiterAbmelden;
	}
	private JLabel getJLabelRemoveEmployee() {
		if (jLabelRemoveEmployee == null) {
			jLabelRemoveEmployee = new JLabel("Name:");
			jLabelRemoveEmployee.setPreferredSize(new Dimension(110, 14));
		}
		return jLabelRemoveEmployee;
	}
	private JComboBox<User> getJComboBoxRemoveEmployee() {
		if (jComboBoxRemoveEmployee == null) {
			dcbmRemove = new DefaultComboBoxModel<User>();
			jComboBoxRemoveEmployee = new JComboBox<User>(dcbmRemove);
			jComboBoxRemoveEmployee.setPreferredSize(new Dimension(120, 20));
			
			//sql.updateComboBoxUser(dcbmRemove);
		}
		return jComboBoxRemoveEmployee;
	}
	private JLabel getLabel_10() {
		if (label_10 == null) {
			label_10 = new JLabel("");
			label_10.setPreferredSize(new Dimension(290, 14));
		}
		return label_10;
	}
	private JLabel getLblAbmeldungsdatum() {
		if (lblAbmeldungsdatum == null) {
			lblAbmeldungsdatum = new JLabel("Abmeldungsdatum:");
			lblAbmeldungsdatum.setPreferredSize(new Dimension(110, 14));
		}
		return lblAbmeldungsdatum;
	}
	private JDateChooser getRemoveDateChooser() {
		if (removeDateChooser == null) {
			removeDateChooser = new JDateChooser((Date) null);
			removeDateChooser.setPreferredSize(new Dimension(120, 20));
		}
		return removeDateChooser;
	}
	private JLabel getLabel_14() {
		if (label_14 == null) {
			label_14 = new JLabel("");
			label_14.setPreferredSize(new Dimension(290, 14));
		}
		return label_14;
	}
	private JButton getJButtonRemoveEmployee() {
		if (jButtonRemoveEmployee == null) {
			jButtonRemoveEmployee = new JButton("Abmelden");
			jButtonRemoveEmployee.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					abmelden();
				}
			});
			jButtonRemoveEmployee.setPreferredSize(new Dimension(110, 23));
		}
		return jButtonRemoveEmployee;
	}
	
	private void abmelden() {
		Date date;
		SimpleDateFormat df;
		
		if(removeDateChooser.getDate() != null) {
			date = removeDateChooser.getDate();
			df = new SimpleDateFormat( "yyyy-MM-dd" );
			df.setTimeZone( TimeZone.getDefault() );
			String end = df.format(date);
			
			if(jComboBoxRemoveEmployee.getSelectedItem() != null)
			{
				sql.setTermination(((User)jComboBoxRemoveEmployee.getSelectedItem()).getId(), end);
				jLabelMessageLineBenutzer.setText("Benutzer wurde abgemeldet!");
			} else {
				jLabelMessageLineBenutzer.setText("Benutzer wählen!");
			}
		}		
		
		else {
			jLabelMessageLineBenutzer.setText("Bitte überprüfen Sie das Datumsformat! (tt.mm.yyyy)");
		}
	}
	
	private JLabel getLabel_15() {
		if (label_15 == null) {
			label_15 = new JLabel("");
			label_15.setPreferredSize(new Dimension(415, 14));
		}
		return label_15;
	}
	private JPanel getJPanelResetPassword() {
		if (jPanelResetPassword == null) {
			jPanelResetPassword = new JPanel();
			jPanelResetPassword.add(getLblPasswortZurcksetzen());
			jPanelResetPassword.add(getLabel_9());
			jPanelResetPassword.add(getComboBoxResetPwd());
			jPanelResetPassword.add(getLabel_11_1());
			jPanelResetPassword.add(getLblNeuesPasswort());
			jPanelResetPassword.add(getTextField_3());
			jPanelResetPassword.add(getLabel_12_1());
			jPanelResetPassword.add(getJButtonResetPassword());
			jPanelResetPassword.add(getLabel_16());
			jPanelResetPassword.add(getJLabelMessageLineBenutzer());
		}
		return jPanelResetPassword;
	}
	private JLabel getLblPasswortZurcksetzen() {
		if (lblPasswortZurcksetzen == null) {
			lblPasswortZurcksetzen = new JLabel(" Passwort zur\u00FCcksetzen");
			lblPasswortZurcksetzen.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			lblPasswortZurcksetzen.setPreferredSize(new Dimension(530, 20));
			lblPasswortZurcksetzen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return lblPasswortZurcksetzen;
	}
	private JLabel getLabel_9() {
		if (label_9 == null) {
			label_9 = new JLabel("Name:");
			label_9.setPreferredSize(new Dimension(110, 14));
		}
		return label_9;
	}
	private JComboBox getComboBoxResetPwd() {
		if (jComboBoxResetPasswordName == null) {
			dcbmReset = new DefaultComboBoxModel<User>();
			jComboBoxResetPasswordName = new JComboBox<User>(dcbmReset);
			jComboBoxResetPasswordName.setPreferredSize(new Dimension(120, 20));
			//sql.updateComboBoxUser(dcbmReset);
		}
		return jComboBoxResetPasswordName;
	}
	private JLabel getLabel_11_1() {
		if (label_11 == null) {
			label_11 = new JLabel("");
			label_11.setPreferredSize(new Dimension(290, 14));
		}
		return label_11;
	}
	private JButton getJButtonResetPassword() {
		if (jButtonResetPassword == null) {
			jButtonResetPassword = new JButton("Reset");
			jButtonResetPassword.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					reset();
				}
			});
			jButtonResetPassword.setPreferredSize(new Dimension(110, 23));
		}
		return jButtonResetPassword;
	}
	public void reset(){
		if(jComboBoxResetPasswordName.getSelectedItem() != null)
		{
			if(jTextFieldNewPassword.getText().length() != 0) {
				User tmp = (User)jComboBoxResetPasswordName.getSelectedItem();
				if(!sql.resetPassword(tmp.getId(), jTextFieldNewPassword.getText())){
					jLabelMessageLineBenutzer.setText("Fehler bei Reset!");
				}
				else {
					jTextFieldNewPassword.setText("");
					jLabelMessageLineBenutzer.setText("Passwort wurde zurückgesetzt!");
				}
			} else {
				jLabelMessageLineBenutzer.setText("Passwort eingeben!");
			}
		} else {
			jLabelMessageLineBenutzer.setText("Wähle Mitarbeiter!");
		}
	}
	private JLabel getLabel_16() {
		if (label_16 == null) {
			label_16 = new JLabel("");
			label_16.setPreferredSize(new Dimension(415, 14));
		}
		return label_16;
	}
	private JLabel getLblNeuesPasswort() {
		if (lblNeuesPasswort == null) {
			lblNeuesPasswort = new JLabel("Neues Passwort:");
			lblNeuesPasswort.setPreferredSize(new Dimension(110, 14));
		}
		return lblNeuesPasswort;
	}
	private JTextField getTextField_3() {
		if (jTextFieldNewPassword == null) {
			jTextFieldNewPassword = new JTextField();
			jTextFieldNewPassword.setPreferredSize(new Dimension(120, 20));
		}
		return jTextFieldNewPassword;
	}
	private JLabel getLabel_12_1() {
		if (label_12 == null) {
			label_12 = new JLabel("");
			label_12.setPreferredSize(new Dimension(290, 14));
		}
		return label_12;
	}
	private void updateComboBoxDistrict(DefaultComboBoxModel<District> dcbm) {
		dcbm.removeAllElements();
		for(Iterator i = sql.getDistricts().iterator(); i.hasNext();)
			dcbm.addElement((District)i.next());
	}
	private JLabel getJLabelMessageLineBenutzer() {
		if (jLabelMessageLineBenutzer == null) {
			jLabelMessageLineBenutzer = new JLabel("");
			jLabelMessageLineBenutzer.setBorder(new EmptyBorder(10, 0, 0, 0));
			jLabelMessageLineBenutzer.setPreferredSize(new Dimension(530, 24));
		}
		return jLabelMessageLineBenutzer;
	}
	private JLabel getJLabelMessageLineBereich() {
		if (jLabelMessageLineBereich == null) {
			jLabelMessageLineBereich = new JLabel("");
			jLabelMessageLineBereich.setBorder(new EmptyBorder(20, 0, 0, 0));
			jLabelMessageLineBereich.setPreferredSize(new Dimension(530, 34));
		}
		return jLabelMessageLineBereich;
	}
}
