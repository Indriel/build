package model;

import java.awt.im.InputContext;
import java.sql.Time;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import data.Category;
import data.District;
import database.MySQL;

public class InputTableModel extends AbstractTableModel {

	private Vector<String> columns;
	private Vector<Vector<String>> data;
	private TableColumnModel columnModel = null;
	private Boolean isGained = false;
	private Boolean editable = true;
	
	//private InputTableRenderer rendererDistrict;
	private ComboBoxEditor editorDistrict;
	private ComboBoxEditor editorCategory;
	private MySQL sql;
	
	public InputTableModel() {
		this.columns = new Vector<String>();
		this.data = new Vector<Vector<String>>();
		
		sql = MySQL.getInstance();
		
		this.setDistrict();
		
		String[] values = new String[] { "1", "2", "3" };
		//this.rendererDistrict = new InputTableRenderer(values);
		//this.editorDistrict = new ComboBoxEditorDistrict(values);
		//this.editorCategory = new InputComboBoxEditor(values);
		
		columns.add("Bereich");
		columns.add("Text");
		columns.add("Dauer (HH:mm)");
		Vector<String> temp = new Vector<String>();
		temp.add("");
		temp.add("");
		temp.add("");
		data.add(temp);		
	}
	
	public void setColumnModel(TableColumnModel columnModel) {
		this.columnModel = columnModel;
		columnModel.getColumn(0).setCellEditor(editorDistrict);
		columnModel.getColumn(1).setCellEditor(editorCategory);
		
		//columnModel.getColumn(0).setCellRenderer(renderer);
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		try { return columns.size(); }
		catch(Exception e) { return 0; }
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		try { return data.size(); }
		catch(Exception e) { return 0; }
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		try { return data.elementAt(arg0).elementAt(arg1); }
		catch(Exception e) { return null; }
	}

	@Override
	public String getColumnName(int arg0) {
		// TODO Auto-generated method stub
		try { return columns.elementAt(arg0).toString(); }
		catch (Exception e){ return null; }
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		if(editable)
			return true;
		else return false;
	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		try {
			String strArg0 = (String) arg0;
			data.elementAt(arg1).setElementAt(strArg0 ,arg2); }
		catch(Exception e) { }
		
		
		if(arg2 == 0) {
			this.setValueAt("", arg1, arg2+1);
			String district = this.getValueAt(arg1, arg2).toString();
			String[] parts = district.split(" ");
			int id;
			try {
				id = Integer.parseInt(parts[0]);
				this.setCategories(id);
			}
			catch(NumberFormatException nfe) { }
		}
		
		boolean isComplete = true;
		
		String duration = data.elementAt(arg1).elementAt(2);
		
		if(duration.compareTo("") != 0) {
			try {
				/*System.out.println(duration.split(" - ")[0]);
				Time temp = Time.valueOf(duration.split(" - ")[0]);
				temp = Time.valueOf(duration.split(" - ")[1]);*/
			}
			catch(NumberFormatException nfe) { setValueAt("", arg1, arg2); }
		}
		
		int size = data.size();
		for(int i = 0; i < 3; i++) {
			if(data.elementAt(size-1).elementAt(i).compareTo("") == 0) {
				if(i != 2) {
					isComplete = false;
					break;
				}
			}
		}
		
		if(isComplete) {
			Vector<String> temp = new Vector<String>();
			for(int i = 0; i < 4; i++) 
				temp.add("");
			data.add(temp);
		}
		
		//columnModel.getColumn(0).setPreferredWidth(50);
		fireTableStructureChanged();
		fireTableDataChanged();
		columnModel.getColumn(0).setCellEditor(editorDistrict);
		columnModel.getColumn(1).setCellEditor(editorCategory);
		//columnModel.getColumn(0).setCellRenderer(renderer);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		// TODO Auto-generated method stub
		Class<?> c=super.getColumnClass(columnIndex);
		/*if(columnIndex==3)
			c=JFormattedTextField.class;*/
		return c;
	}
	
	
	private void setDistrict() {
		
		Vector<District> districts = sql.getDistricts();
		int size = districts.size();
		int j = 0;
		String[] districtArray = new String[size];
		
		for(Iterator<District> i = districts.iterator(); i.hasNext();) {
			districtArray[j] = i.next().toString();
			j++;
		}
		
		this.editorDistrict = new ComboBoxEditor(districtArray);
	}
	
	private void setCategories(int id) {
		
	/*	Vector<Category> categories = sql.getCategories(id);
		int size = categories.size();
		int j = 0;
		String[] categoryArray = new String[size];
		
		for(Iterator<Category> i = categories.iterator(); i.hasNext();) {
			categoryArray[j] = i.next().toString();
			j++;
		}
		
		this.editorCategory = new ComboBoxEditor(categoryArray);*/
	}
	
	public void reset() {
		this.data = new Vector<Vector<String>>();
		
		Vector<String> temp = new Vector<String>();
		temp.add("");
		temp.add("");
		temp.add("");
		temp.add("");
		data.add(temp);	
		fireTableDataChanged();
	}
	
	public void setLines(Vector<Vector<String>> values) {
		data = new Vector<Vector<String>>();
		for(Iterator i = values.iterator(); i.hasNext(); ) {
			Vector<String> temp = (Vector<String>) i.next();
			data.add(temp);
		}
		Vector<String> temp = new Vector<String>();
		temp.add("");
		temp.add("");
		temp.add("");
		data.add(temp);	
		fireTableDataChanged();
	}
	
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
}
