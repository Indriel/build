package data;

import java.util.Date;

public class MitarbeiterMonat {
	private Date datum;
	private int id;
	private float uebertrag;
	
	public MitarbeiterMonat(int id, Date datum, float uebertrag) {
		this.datum = datum;
		this.id = id;
		this.uebertrag = uebertrag;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getUebertrag() {
		return uebertrag;
	}

	public void setUebertrag(float uebertrag) {
		this.uebertrag = uebertrag;
	}
}
