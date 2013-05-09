package gui;


import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Robot;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import com.toedter.calendar.JDateChooser;

import data.User;
import data.WorkType;
import database.MySQL;

import java.awt.FlowLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.InputTableModel;

import javax.swing.JButton;

import output.Last_Done_Property;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.TimeZone;
import java.util.Vector;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.Color;


public class InputPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel jPanelNorth;
	private JPanel jPanelNorthWest;
	private JLabel jLabelEmployee;
	private JComboBox<User> jComboBoxEmployee;
	private JLabel jLabelDate;
	private JTextField jTextFieldDate;
	private JPanel jPanelNorthEast;
	private JLabel jLabelFrom;
	private JComboBox jComboBoxFromHour;
	private JComboBox jComboBoxFromMinute;
	private DefaultComboBoxModel<Integer> modelFromHour;
	private DefaultComboBoxModel<Integer> modelFromMinute;
	private DefaultComboBoxModel<Integer> modelToHour;
	private DefaultComboBoxModel<Integer> modelToMinute;
	private DefaultComboBoxModel<WorkType> modelWorkmode;
	private DefaultComboBoxModel<User> modelEmployee;
	private DefaultComboBoxModel<Integer> modelBreak;
	private JLabel jLabelTo;
	private JComboBox jComboBoxToHour;
	private JComboBox jComboBoxToMinute;
	
	private JDateChooser mainDateChooser = null;
	private JPanel jPanelNorthWestNorth;
	private JPanel jPanelNorthWestSouth;
	private JComboBox jComboBoxWorkmode;
	private JLabel jLabelWorkmode;
	private JLabel jLabelBreak;
	private JLabel jLabelMin;
	private JPanel jPanelSouth;
	private InputTableModel inputTableModel;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel jPanelSouthSouth;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel label;
	private JPanel jPanelSouthSouthNorth;
	private JButton jButtonSubmit;
	private JPanel jPanelSouthSouthCenter;
	private JLabel jLabelMessageLine;
	private Last_Done_Property ldp = null;
	
	private User user;
	private MySQL sql;
	private JComboBox jComboBoxBreak;
	
	
	
	
	public InputPanel(User user) {
		super();
		this.user = user;
		sql = MySQL.getInstance();
		initialize();
		ldp = new Last_Done_Property(
				jComboBoxFromHour,
				jComboBoxFromMinute,
				jComboBoxToHour,
				jComboBoxToMinute,
				jComboBoxBreak);
	}

	
	private void initialize() {
		this.setLayout(new BorderLayout());
		add(getJPanelNorth(), BorderLayout.NORTH);
		this.setPreferredSize(new Dimension(550, 400));
		add(getJPanelSouth(), BorderLayout.SOUTH);
		jPanelNorthEast.setEnabled(false);
	}

	private JPanel getJPanelNorth() {
		if (jPanelNorth == null) {
			jPanelNorth = new JPanel();
			jPanelNorth.setPreferredSize(new Dimension(550, 160));
			jPanelNorth.setLayout(new BorderLayout(0, 0));
			jPanelNorth.add(getJPanelNorthWest(), BorderLayout.WEST);
			jPanelNorth.add(getJPanelNorthEast(), BorderLayout.EAST);
		}
		return jPanelNorth;
	}
	private JPanel getJPanelNorthWest() {
		if (jPanelNorthWest == null) {
			jPanelNorthWest = new JPanel();
			jPanelNorthWest.setPreferredSize(new Dimension(280, 110));
			jPanelNorthWest.setLayout(new BorderLayout(0, 0));
			jPanelNorthWest.add(getJPanelNorthWestNorth(), BorderLayout.NORTH);
			jPanelNorthWest.add(getJPanelNorthWestSouth(), BorderLayout.SOUTH);
			//jPanelNorthWest.add(getJTextFieldDate());
		}
		return jPanelNorthWest;
	}
	private JLabel getJLabelEmployee() {
		if (jLabelEmployee == null) {
			jLabelEmployee = new JLabel("Mitarbeiter:");
			jLabelEmployee.setPreferredSize(new Dimension(90, 14));
		}
		return jLabelEmployee;
	}
	private JComboBox<User> getJComboBoxEmployee() {
		if (jComboBoxEmployee == null) {
			modelEmployee = new DefaultComboBoxModel<User>();
			jComboBoxEmployee = new JComboBox(modelEmployee);
			jComboBoxEmployee.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					if(mainDateChooser != null)
					checkIfEntryAlreadyExists();
				}
			});
			jComboBoxEmployee.setPreferredSize(new Dimension(150, 20));
			
			
			if(user.getName().compareTo("admin") == 0)
			{
				//sql.updateComboBoxUser(modelEmployee);
			} else {
				modelEmployee.addElement(user);
			}
		}
		return jComboBoxEmployee;
	}
	private JLabel getJLabelDate() {
		if (jLabelDate == null) {
			jLabelDate = new JLabel("Datum:");
			jLabelDate.setPreferredSize(new Dimension(90, 14));
		}
		return jLabelDate;
	}
	private JTextField getJTextFieldDate() {
		if (jTextFieldDate == null) {
			jTextFieldDate = new JTextField();
			jTextFieldDate.setPreferredSize(new Dimension(150, 20));
		}
		return jTextFieldDate;
	}
	private JPanel getJPanelNorthEast() {
		if (jPanelNorthEast == null) {
			jPanelNorthEast = new JPanel();
			jPanelNorthEast.setPreferredSize(new Dimension(270, 10));
			jPanelNorthEast.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
			jPanelNorthEast.add(getJLabelFrom());
			jPanelNorthEast.add(getJComboBoxFromHour());
			jPanelNorthEast.add(getJComboBoxFromMinute());
			jPanelNorthEast.add(getLblNewLabel());
			jPanelNorthEast.add(getJLabelTo());
			jPanelNorthEast.add(getJComboBoxToHour());
			jPanelNorthEast.add(getJComboBoxToMinute());
			jPanelNorthEast.add(getLblNewLabel_1());
			jPanelNorthEast.add(getJLabelBreak());
			jPanelNorthEast.add(getJComboBoxBreak());
			jPanelNorthEast.add(getJLabelMin());
			jPanelNorthEast.add(getLabel());
		}
		return jPanelNorthEast;
	}
	private JLabel getJLabelFrom() {
		if (jLabelFrom == null) {
			jLabelFrom = new JLabel("Von:");
			jLabelFrom.setPreferredSize(new Dimension(40, 14));
		}
		return jLabelFrom;
	}
	private JComboBox getJComboBoxFromHour() {
		if (jComboBoxFromHour == null) {
			modelFromHour = new DefaultComboBoxModel<Integer>();
			jComboBoxFromHour = new JComboBox(modelFromHour);
			jComboBoxFromHour.setPreferredSize(new Dimension(50, 20));
			modelFromHour.addElement(6);
			modelFromHour.addElement(7);
			modelFromHour.addElement(8);
			modelFromHour.addElement(9);
			modelFromHour.addElement(10);
			modelFromHour.addElement(11);
			modelFromHour.addElement(12);
			modelFromHour.addElement(13);
			modelFromHour.addElement(14);
			modelFromHour.addElement(15);
			modelFromHour.addElement(16);
			modelFromHour.addElement(17);
			modelFromHour.addElement(18);
			modelFromHour.addElement(19);
			modelFromHour.addElement(20);
		}
		return jComboBoxFromHour;
	}
	private JComboBox getJComboBoxFromMinute() {
		if (jComboBoxFromMinute == null) {
			modelFromMinute = new DefaultComboBoxModel<Integer>();
			jComboBoxFromMinute = new JComboBox(modelFromMinute);
			jComboBoxFromMinute.setPreferredSize(new Dimension(50, 20));
			modelFromMinute.addElement(00);
			modelFromMinute.addElement(15);
			modelFromMinute.addElement(30);
			modelFromMinute.addElement(45);
		}
		return jComboBoxFromMinute;
	}
	private JLabel getJLabelTo() {
		if (jLabelTo == null) {
			jLabelTo = new JLabel("Bis:");
			jLabelTo.setPreferredSize(new Dimension(40, 14));
		}
		return jLabelTo;
	}
	private JComboBox getJComboBoxToHour() {
		if (jComboBoxToHour == null) {
			modelToHour = new DefaultComboBoxModel<Integer>();
			jComboBoxToHour = new JComboBox(modelToHour);
			jComboBoxToHour.setPreferredSize(new Dimension(50, 20));
			modelToHour.addElement(6);
			modelToHour.addElement(7);
			modelToHour.addElement(8);
			modelToHour.addElement(9);
			modelToHour.addElement(10);
			modelToHour.addElement(11);
			modelToHour.addElement(12);
			modelToHour.addElement(13);
			modelToHour.addElement(14);
			modelToHour.addElement(15);
			modelToHour.addElement(16);
			modelToHour.addElement(17);
			modelToHour.addElement(18);
			modelToHour.addElement(19);
			modelToHour.addElement(20);
			modelToHour.addElement(21);
			modelToHour.addElement(22);
		}
		return jComboBoxToHour;
	}
	private JComboBox getJComboBoxToMinute() {
		if (jComboBoxToMinute == null) {
			modelToMinute = new DefaultComboBoxModel<Integer>();
			jComboBoxToMinute = new JComboBox(modelToMinute);
			jComboBoxToMinute.setPreferredSize(new Dimension(50, 20));
			modelToMinute.addElement(00);
			modelToMinute.addElement(15);
			modelToMinute.addElement(30);
			modelToMinute.addElement(45);
		}
		return jComboBoxToMinute;
	}
	
	private JDateChooser getMainDateChooser() {
		if(mainDateChooser == null) {
			mainDateChooser = new JDateChooser(new Date());
			mainDateChooser.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent arg0) {
					checkIfEntryAlreadyExists();
				}
			});
			mainDateChooser.setPreferredSize(new Dimension(150, 20));
		}
		return mainDateChooser;
	}
	
	private JPanel getJPanelNorthWestNorth() {
		if (jPanelNorthWestNorth == null) {
			jPanelNorthWestNorth = new JPanel();
			jPanelNorthWestNorth.setPreferredSize(new Dimension(80, 100));
			jPanelNorthWestNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
			jPanelNorthWestNorth.add(getJLabelEmployee());
			jPanelNorthWestNorth.add(getJComboBoxEmployee());
			jPanelNorthWestNorth.add(getJLabelDate());
			jPanelNorthWestNorth.add(getMainDateChooser());
			jPanelNorthWestNorth.add(getJLabelWorkmode());
			jPanelNorthWestNorth.add(getComboBoxWorkmode());
		}
		return jPanelNorthWestNorth;
	}
	private JPanel getJPanelNorthWestSouth() {
		if (jPanelNorthWestSouth == null) {
			jPanelNorthWestSouth = new JPanel();
			jPanelNorthWestSouth.setPreferredSize(new Dimension(10, 60));
			jPanelNorthWestSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));
		}
		return jPanelNorthWestSouth;
	}
	private JComboBox getComboBoxWorkmode() {
		if (jComboBoxWorkmode == null) {
			modelWorkmode = new DefaultComboBoxModel<WorkType>();
			jComboBoxWorkmode = new JComboBox(modelWorkmode);
			jComboBoxWorkmode.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					workModeChanged();
				}
			});
			jComboBoxWorkmode.setPreferredSize(new Dimension(150, 20));
			
			for(Iterator<WorkType> i = sql.getWorkType().iterator(); i.hasNext(); )
			{
				WorkType wt = (WorkType)i.next();
				modelWorkmode.addElement(wt);
				if(wt.getWork_type().compareTo("Arbeitstag") == 0)
					jComboBoxWorkmode.setSelectedItem(wt);
			}
		}
		return jComboBoxWorkmode;
	}
	private JLabel getJLabelWorkmode() {
		if (jLabelWorkmode == null) {
			jLabelWorkmode = new JLabel("Art:");
			jLabelWorkmode.setPreferredSize(new Dimension(90, 14));
		}
		return jLabelWorkmode;
	}
	private JLabel getJLabelBreak() {
		if (jLabelBreak == null) {
			jLabelBreak = new JLabel("Pause:");
			jLabelBreak.setPreferredSize(new Dimension(40, 14));
		}
		return jLabelBreak;
	}
	private JLabel getJLabelMin() {
		if (jLabelMin == null) {
			jLabelMin = new JLabel("Minuten");
			jLabelMin.setPreferredSize(new Dimension(50, 20));
		}
		return jLabelMin;
	}
	
	
	private void workModeChanged() {
		if(jLabelFrom != null) {
			if(modelWorkmode.getSelectedItem().toString().compareTo("Urlaub") == 0 ||
		       modelWorkmode.getSelectedItem().toString().compareTo("Krankenstand") == 0  ||
		       modelWorkmode.getSelectedItem().toString().compareTo("Zeitausgleich") == 0 ||
		       modelWorkmode.getSelectedItem().toString().compareTo("Feiertag") == 0) {
				jLabelFrom.setEnabled(false);
				jLabelTo.setEnabled(false);
				jLabelBreak.setEnabled(false);
				jLabelMin.setEnabled(false);
				jComboBoxBreak.setEnabled(false);
				jComboBoxFromHour.setEnabled(false);
				jComboBoxFromMinute.setEnabled(false);
				jComboBoxToHour.setEnabled(false);
				jComboBoxToMinute.setEnabled(false);
				inputTableModel.setEditable(false);
				table.setBackground(Color.LIGHT_GRAY);
			}
			else {
				jLabelFrom.setEnabled(true);
				jLabelTo.setEnabled(true);
				jLabelBreak.setEnabled(true);
				jLabelMin.setEnabled(true);
				jComboBoxBreak.setEnabled(true);
				jComboBoxFromHour.setEnabled(true);
				jComboBoxFromMinute.setEnabled(true);
				jComboBoxToHour.setEnabled(true);
				jComboBoxToMinute.setEnabled(true);
				inputTableModel.setEditable(true);
				table.setBackground(Color.WHITE);
			}
		}
	}
	private JPanel getJPanelSouth() {
		if (jPanelSouth == null) {
			jPanelSouth = new JPanel();
			jPanelSouth.setPreferredSize(new Dimension(520, 240));
			jPanelSouth.setLayout(new BorderLayout(0, 0));
			jPanelSouth.add(getScrollPane(), BorderLayout.NORTH);
			jPanelSouth.add(getJPanelSouthSouth(), BorderLayout.SOUTH);
		}
		return jPanelSouth;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setPreferredSize(new Dimension(2, 150));
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}
	private JTable getTable() {
		if (table == null) {
			inputTableModel = new InputTableModel();
			table = new JTable(inputTableModel);
			table.setCellSelectionEnabled(true);
			table.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent arg0) {
					inputTableModel.fireTableDataChanged();
				}
			});
			inputTableModel.setColumnModel(table.getColumnModel());
			table.setRowHeight(20);
			
			table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		}
		return table;
	}
	private JComboBox getJComboBoxBreak() {
		if (jComboBoxBreak == null) {
			modelBreak = new DefaultComboBoxModel<Integer>();
			jComboBoxBreak = new JComboBox(modelBreak);
			jComboBoxBreak.setPreferredSize(new Dimension(50, 20));
			modelBreak.addElement(0);
			modelBreak.addElement(15);
			modelBreak.addElement(30);
			modelBreak.addElement(45);
			modelBreak.addElement(60);
		}
		return jComboBoxBreak;
	}
	private JPanel getJPanelSouthSouth() {
		if (jPanelSouthSouth == null) {
			jPanelSouthSouth = new JPanel();
			jPanelSouthSouth.setPreferredSize(new Dimension(10, 90));
			jPanelSouthSouth.setLayout(new BorderLayout(0, 0));
			jPanelSouthSouth.add(getJPanelSouthSouthNorth(), BorderLayout.NORTH);
			jPanelSouthSouth.add(getJPanelSouthSouthCenter(), BorderLayout.CENTER);
		}
		return jPanelSouthSouth;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("                    ");
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("                    ");
		}
		return lblNewLabel_1;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("                    ");
		}
		return label;
	}
	
	private JPanel getJPanelSouthSouthNorth() {
		if (jPanelSouthSouthNorth == null) {
			jPanelSouthSouthNorth = new JPanel();
			jPanelSouthSouthNorth.setPreferredSize(new Dimension(10, 50));
			jPanelSouthSouthNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));
			jPanelSouthSouthNorth.add(getJButtonSubmit());
		}
		return jPanelSouthSouthNorth;
	}
	private JButton getJButtonSubmit() {
		if (jButtonSubmit == null) {
			jButtonSubmit = new JButton("Erfassen");
			jButtonSubmit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					checkInput();
//					ldp.printToFile();
				}
			});
		}
		return jButtonSubmit;
	}
	
	private JPanel getJPanelSouthSouthCenter() {
		if (jPanelSouthSouthCenter == null) {
			jPanelSouthSouthCenter = new JPanel();
			jPanelSouthSouthCenter.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 15));
			jPanelSouthSouthCenter.add(getJLabelMessageLine());
		}
		return jPanelSouthSouthCenter;
	}
	private JLabel getJLabelMessageLine() {
		if (jLabelMessageLine == null) {
			jLabelMessageLine = new JLabel("");
		}
		return jLabelMessageLine;
	}
	
	public DefaultComboBoxModel<User> getUserComboBoxModel() {
		return modelEmployee;
	}
	
	private void checkInput() {
		
		boolean correctInput = true;
		Date date;
		int userID = ( (User)jComboBoxEmployee.getSelectedItem()).getId();
		SimpleDateFormat df;
		
		if(mainDateChooser.getDate() != null) {
			date = mainDateChooser.getDate();
			df = new SimpleDateFormat( "yyyy-MM-dd" );
			df.setTimeZone( TimeZone.getDefault() );
			jLabelMessageLine.setText("");
			//System.out.println(df.format( dt ));
		}		
		
		else {
			jLabelMessageLine.setText("Bitte überprüfen Sie das Datumsformat! (tt.mm.yyyy)");
			correctInput = false;
			return;
		}
		
		int fromHour = new Integer(jComboBoxFromHour.getSelectedItem().toString());
		int fromMinute = new Integer(jComboBoxFromMinute.getSelectedItem().toString());
		int toHour = new Integer(jComboBoxToHour.getSelectedItem().toString());
		int toMinute = new Integer(jComboBoxToMinute.getSelectedItem().toString());
		
		if( (fromHour > toHour) || (fromHour == toHour && fromMinute >= toMinute) &&
				modelWorkmode.getSelectedItem().toString().compareTo("Arbeitstag") == 0) {
			jLabelMessageLine.setText("Bitte überprüfen Sie die Angabe der Tageszeiten!");
			correctInput = false;
			return;
		}
		
		else
			jLabelMessageLine.setText("");
		
		int pause;
		
		try {
			pause = new Integer(jComboBoxBreak.getSelectedItem().toString());
			jLabelMessageLine.setText("");
		}
		catch(NumberFormatException nfe) {
			jLabelMessageLine.setText("Bitte geben Sie für die Dauer der Pause eine ganze Zahl ein!");
			correctInput = false;
			return;
		}
		
		if(correctInput) {
			
			int rows = inputTableModel.getRowCount() - 1;
			
			if(rows == 0  && modelWorkmode.getSelectedItem().toString().compareTo("Arbeitstag") == 0 ) {
				jLabelMessageLine.setText("Es muss mindestens eine Aktivität eingetragen werden!");
				return;
			}
			else
				jLabelMessageLine.setText("");
			
			int categoryId[] = new int[rows];
			int duration[] = new int[rows];
			String text[] = new String[rows];
			
			if(modelWorkmode.getSelectedItem().toString().compareTo("Arbeitstag") == 0 ) {
				try {
				
					for(int i = 0; i < rows; i++) {
						String category[] = inputTableModel.getValueAt(i, 1).toString().split(" ");
						categoryId[i] = Integer.parseInt(category[0]);
						duration[i] = Integer.parseInt(inputTableModel.getValueAt(i, 3).toString());
						text[i] = inputTableModel.getValueAt(i, 2).toString();
					}
				}
				catch(NumberFormatException nfe) {
					System.out.println(nfe.getMessage());
					return;
				}
			}
			
			boolean success = true;
			/*sql.writeEmployeeDay(
				userID,
				date,
				fromMinute,
				fromHour,
				toMinute,
				toHour,
				pause,
				((WorkType)modelWorkmode.getSelectedItem()).getId()
			);	*/
			
			if(!success) {
				jLabelMessageLine.setText("Eintrag für " + df.format( date ) + " erfolgreich überschrieben!");
				
				sql.resetWorkDay(userID, date);
				sql.deleteWorkDay(userID, date);
				
				/*sql.writeEmployeeDay(
						userID,
						date,
						fromMinute,
						fromHour,
						toMinute,
						toHour,
						pause,
						((WorkType)modelWorkmode.getSelectedItem()).getId()
					);	*/
			}
			
			else 
				jLabelMessageLine.setText("Eintrag für " + df.format( date ) + " erfolgreich!");
			
			sql.deleteActivities(userID, date);
			
			if(modelWorkmode.getSelectedItem().toString().compareTo("Arbeitstag") == 0 ) {
				sql.writeEmployeeActivity(
					userID,
					date,
					categoryId,
					duration,
					text);
			}
			else
				inputTableModel.reset();
				
		}
	}
	
	public void updateTime() {
		ldp = new Last_Done_Property(
				jComboBoxFromHour,
				jComboBoxFromMinute,
				jComboBoxToHour,
				jComboBoxToMinute,
				jComboBoxBreak);
	}
	
	private void checkIfEntryAlreadyExists() {
		Vector<Vector<String>> values;
		
		int userID = 0;
		Date date;
		if(jComboBoxEmployee != null) {
			try {
			userID = ( (User)jComboBoxEmployee.getSelectedItem()).getId();
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
			if(inputTableModel != null) 
				inputTableModel.setLines(values);
		}
		
		else {
			if(inputTableModel != null)
				inputTableModel.reset();
		}
	}
}
