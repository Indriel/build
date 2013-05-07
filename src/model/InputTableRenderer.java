package model;

import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class InputTableRenderer extends JComboBox<String> implements TableCellRenderer {

	public InputTableRenderer(String[] items) {
		super(items);
	}
	
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		// TODO Auto-generated method stub
		
		
		System.out.println("hier");
		
//		if (isSelected) {
//			setForeground(table.getSelectionForeground());
//		    super.setBackground(table.getSelectionBackground());
//		}
//		
//		else {
//			setForeground(table.getForeground());
//		    setBackground(table.getBackground());
//		}
		    
		setSelectedItem(value);
		return this;
	}

	
}
