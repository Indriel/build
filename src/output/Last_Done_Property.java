package output;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class Last_Done_Property {
	
	private String username = "";
	private Properties prop = null;
	private JComboBox jComboBoxFromHour;
	private JComboBox jComboBoxFromMinute;
	private JComboBox jComboBoxToHour;
	private JComboBox jComboBoxToMinute;
	private JComboBox jComboBoxBreak;
	private JTextField pause;
	
	public Last_Done_Property(JTextField tf) {
		prop = new Properties();
		try{
			prop.load(new FileInputStream("default.prop"));
			tf.setText(prop.getProperty("username"));
		}catch(Exception e){
			printToFileErrorName();
		}
	}
	
	public Last_Done_Property(
			String un,
			JComboBox cfh,
			JComboBox cfm,
			JComboBox cth,
			JComboBox ctm,
			JComboBox paus){
		
		username = un;
		jComboBoxFromHour = cfh;
		jComboBoxFromMinute = cfm;
		jComboBoxToHour = cth;
		jComboBoxToMinute = ctm;
		jComboBoxBreak = paus;
		
		prop = new Properties();
		try{
			prop.load(new FileInputStream("default.prop"));
		
//			jComboBoxFromHour.setSelectedIndex(
//					((DefaultComboBoxModel)jComboBoxFromHour.getModel()).getIndexOf(Integer.parseInt(prop.getProperty("fromHour"))));
//			jComboBoxFromMinute.setSelectedIndex(
//					((DefaultComboBoxModel)jComboBoxFromMinute.getModel()).getIndexOf(Integer.parseInt(prop.getProperty("fromMinute"))));
//			jComboBoxToHour.setSelectedIndex(
//					((DefaultComboBoxModel)jComboBoxToHour.getModel()).getIndexOf(Integer.parseInt(prop.getProperty("toHour"))));
//			jComboBoxToMinute.setSelectedIndex(
//					((DefaultComboBoxModel)jComboBoxToMinute.getModel()).getIndexOf(Integer.parseInt(prop.getProperty("toMinute"))));
//			jComboBoxBreak.setSelectedIndex(
//					((DefaultComboBoxModel)jComboBoxBreak.getModel()).getIndexOf(Integer.parseInt(prop.getProperty("pause"))));
//		
		}catch(Exception e){
			printToFile();
		}
		printToFile();
	}
	
	public Last_Done_Property(
			JComboBox cfh,
			JComboBox cfm,
			JComboBox cth,
			JComboBox ctm,
			JComboBox paus){

		jComboBoxFromHour = cfh;
		jComboBoxFromMinute = cfm;
		jComboBoxToHour = cth;
		jComboBoxToMinute = ctm;
		jComboBoxBreak = paus;
		
		prop = new Properties();
		try{
			prop.load(new FileInputStream("default.prop"));
		
			jComboBoxFromHour.setSelectedIndex(
					((DefaultComboBoxModel)jComboBoxFromHour.getModel()).getIndexOf(Integer.parseInt(prop.getProperty("fromHour"))));
			jComboBoxFromMinute.setSelectedIndex(
					((DefaultComboBoxModel)jComboBoxFromMinute.getModel()).getIndexOf(Integer.parseInt(prop.getProperty("fromMinute"))));
			jComboBoxToHour.setSelectedIndex(
					((DefaultComboBoxModel)jComboBoxToHour.getModel()).getIndexOf(Integer.parseInt(prop.getProperty("toHour"))));
			jComboBoxToMinute.setSelectedIndex(
					((DefaultComboBoxModel)jComboBoxToMinute.getModel()).getIndexOf(Integer.parseInt(prop.getProperty("toMinute"))));
			jComboBoxBreak.setSelectedIndex(
					((DefaultComboBoxModel)jComboBoxBreak.getModel()).getIndexOf(Integer.parseInt(prop.getProperty("pause"))));
		
		}catch(Exception e){
			printToFile();
		}
	}
	
	
	public void printToFile()
	{
		prop.setProperty("username", username);
		prop.setProperty("fromHour", ""+((DefaultComboBoxModel)jComboBoxFromHour.getModel()).getSelectedItem());
		prop.setProperty("fromMinute", ""+((DefaultComboBoxModel)jComboBoxFromMinute.getModel()).getSelectedItem());
		prop.setProperty("toHour", ""+((DefaultComboBoxModel)jComboBoxToHour.getModel()).getSelectedItem());
		prop.setProperty("toMinute", ""+((DefaultComboBoxModel)jComboBoxToMinute.getModel()).getSelectedItem());
		prop.setProperty("pause", "" + ((DefaultComboBoxModel)jComboBoxBreak.getModel()).getSelectedItem());
		try{
			prop.store(new FileOutputStream("default.prop"), null);
		}catch(Exception e){System.out.println("printToFile()");}
	}
	public void printToFileErrorName() {
		prop.setProperty("username", username);
		try{
			prop.store(new FileOutputStream("default.prop"), null);
		}catch(Exception e){System.out.println("printToFile()");}
	}
	
}
