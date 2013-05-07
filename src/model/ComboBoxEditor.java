package model;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;

public class ComboBoxEditor extends DefaultCellEditor {

	public ComboBoxEditor(String[] items) {
	    super(new JComboBox(items));
	}
}
