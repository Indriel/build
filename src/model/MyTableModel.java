package model;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class MyTableModel extends DefaultTableModel{
	String arr[]=new String[] { "0", "1" };
	 public MyTableModel(int rowData, Object columnNames[], TableColumnModel tableColumnModel) {  
         super(columnNames,rowData );  
         JComboBox cb=new JComboBox(arr);
         cb.setEditable(true);
         DefaultCellEditor dce=new DefaultCellEditor(cb);
         tableColumnModel.getColumn(0).setCellEditor(dce);
      }  
     
    @Override  
      public Class getColumnClass(int col) { 
    	Class c=String.class;
        if (col == 0)       //second column accepts only Integer values  
              c=JComboBox.class;
        
        return c;//other columns accept String values  
    }  
  
    @Override  
      public boolean isCellEditable(int row, int col) {  
        if (col == 0)       //first column will be uneditable  
            return false;  
        else return true;  
      }  
}
