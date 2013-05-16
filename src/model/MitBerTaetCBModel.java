package model;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;

import data.District;
import data.User;
import data.WorkType;
import database.MySQL;

public class MitBerTaetCBModel extends DefaultComboBoxModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final List<String> types = Arrays.asList("Bereich","Mitarbeiter","Taetigkeit");
	private String actualType;
	private MySQL database;
	
	public MitBerTaetCBModel(String type) throws InvalidMitBerTaetTypeException, SQLException {
		super();
		this.actualType = type;
		if(!types.contains(actualType))
			throw new InvalidMitBerTaetTypeException("Invalid Type for MitBerTaetCBModel");
		database = MySQL.getInstance();
		this.createItems();
	}

	private void createItems() throws SQLException {
		if(actualType.equals("Bereich")) {
			this.initBereich();
		}
		else if(actualType.equals("Mitarbeiter")) {
			this.initMitarbeiter();
		}
		else if(actualType.equals("Taetigkeit")) {
			this.initTaetigkeit();
		}
	}

	private void initTaetigkeit() {
		Vector<WorkType> vw = this.database.getWorkType();
		for(WorkType d : vw)
			this.addElement(d);
	}

	private void initMitarbeiter() throws SQLException {
		Vector<User> vm = this.database.getUsers();
		for(User d : vm) {
			this.addElement(d);
		}
	}

	private void initBereich() {
		Vector<District> vd = this.database.getDistricts();
		for(District d : vd) {
			this.addElement(d);
		}
	}
	
	public void refresh() throws SQLException {
		if(actualType.equals("Bereich")) {
			this.initBereich();
		}
		else if(actualType.equals("Mitarbeiter")) {
			this.initMitarbeiter();
		}
		else if(actualType.equals("Taetigkeit")) {
			this.initTaetigkeit();
		}
	}
}
