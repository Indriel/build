package data;

import java.util.Date;

public class Mitarbeitertaetigkeit {
	private Date datum;
	private String beschreibung;
	private int dauer;
	
	public Mitarbeitertaetigkeit(Date datum, String beschreibung, int dauer) {
		this.datum = datum;
		this.beschreibung = beschreibung;
		this.dauer = dauer;
	}

	public Date getDatum() {
		return datum;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public int getDauer() {
		return dauer;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public void setDauer(int dauer) {
		this.dauer = dauer;
	}
	
}
