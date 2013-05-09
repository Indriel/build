package model;

import javax.swing.DefaultComboBoxModel;

public class TimeCBModel extends DefaultComboBoxModel<String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TimeCBModel(String type) {
		if(type.equals("hour")) {
			this.initHour();
		}
		else if(type.equals("min")) {
			this.initMinute();
		}
		else if(type.equals("pause")) {
			this.initPause();
		}
	}

	private void initPause() {
		String[] s = new String[]{"15","30","45"};
		for(int i = 0; i<s.length; i++)
			this.addElement(s[i]);
	}

	private void initMinute() {
		String[] s = new String[]{"00","10","20","30","40","50"};
		for(int i = 0; i<s.length; i++)
			this.addElement(s[i]);
	}

	private void initHour() {
		String[] s = new String[]{"07","08","09","10","11","12","13","14","15","16","17","18"};
		for(int i = 0; i<s.length; i++)
			this.addElement(s[i]);
	}

}
