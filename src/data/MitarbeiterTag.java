package data;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MitarbeiterTag {
	private int mitarbeiter_id;
	private Date monatsdatum;
	private Date tagesdatum;
	private int taetigkeits_id;
	private Date von;
	private Date bis;
	private int pause;
	private int ueberstunden;
	private int sollstunden;
	
	public MitarbeiterTag(int m_id, Date mondate, Date tDate, int tid, Time von, Time bis, int pause, int sollstd) {
		this.mitarbeiter_id = m_id;
		this.monatsdatum = mondate;
		this.tagesdatum = tDate;
		this.taetigkeits_id = tid;
		this.von = new Date(von.getTime());
		this.bis = new Date(von.getTime());
		this.pause = pause;
		this.sollstunden = sollstd;
		this.ueberstunden = this.calculateUeberstunden();
	}

	public int getMitarbeiter_id() {
		return mitarbeiter_id;
	}

	public void setMitarbeiter_id(int mitarbeiter_id) {
		this.mitarbeiter_id = mitarbeiter_id;
	}

	public Date getMonatsdatum() {
		return monatsdatum;
	}

	public void setMonatsdatum(Date monatsdatum) {
		this.monatsdatum = monatsdatum;
	}

	public Date getTagesdatum() {
		return tagesdatum;
	}

	public void setTagesdatum(Date tagesdatum) {
		this.tagesdatum = tagesdatum;
	}

	public int getTaetigkeits_id() {
		return taetigkeits_id;
	}

	public void setTaetigkeits_id(int taetigkeits_id) {
		this.taetigkeits_id = taetigkeits_id;
	}

	public Date getVon() {
		return von;
	}

	public void setVon(Time von) {
		this.von = von;
	}

	public Date getBis() {
		return bis;
	}

	public void setBis(Time bis) {
		this.bis = bis;
	}

	public int getPause() {
		return pause;
	}

	public void setPause(int pause) {
		this.pause = pause;
	}

	public int getUeberstunden() {
		return ueberstunden;
	}

	public void setUeberstunden(int ueberstunden) {
		this.ueberstunden = ueberstunden;
	}

	public int getSollstunden() {
		return sollstunden;
	}

	public void setSollstunden(int sollstunden) {
		this.sollstunden = sollstunden;
	}
	
	private int calculateUeberstunden() {
		Calendar c = GregorianCalendar.getInstance();
		c.setTime(bis);
		int minbis = c.get(Calendar.MINUTE);
		c.setTime(von);
		int minvon = c.get(Calendar.MINUTE);
		int retmin = minbis-minvon-pause;
		c.set(Calendar.MINUTE, retmin);
		return c.get(Calendar.HOUR_OF_DAY);
	}

}
