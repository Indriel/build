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
	private Time von;
	private Time bis;
	private Time pause;
	private Time ueberstunden;
	private Time arbeitsstunden;
	private int sollstunden;
	private float floatUeberstunden;
	//------------------------------------------------------
	public MitarbeiterTag(int m_id, Date mondate, Date tDate, int tid, Time von, Time bis, int pause, int sollstd, float ueber) {
		this.mitarbeiter_id = m_id;
		this.monatsdatum = mondate;
		this.tagesdatum = tDate;
		this.taetigkeits_id = tid;
		this.von = this.calculateVon(von);
		this.bis = this.calculateBis(bis);
		this.pause = this.calculatePause(pause);
		this.sollstunden = sollstd;
		this.arbeitsstunden = this.calculateArbeitsstunden();
		this.ueberstunden = this.calculateUeberstunden();
		this.setFloatUeberstunden(ueber);
	}

	private Time calculateBis(Time bis2) {
		Calendar c = new GregorianCalendar();
		//c.set(Calendar.MINUTE, von2.getMinutes());
		c.setTime(this.tagesdatum);
		c.set(Calendar.HOUR_OF_DAY, bis2.getHours());
		c.set(Calendar.MINUTE, bis2.getMinutes());
		c.set(Calendar.SECOND, bis2.getSeconds());
		return new Time(c.getTimeInMillis());
	}

	private Time calculateVon(Time von2) {
		Calendar c = new GregorianCalendar();
		//c.set(Calendar.MINUTE, von2.getMinutes());
		c.setTime(this.tagesdatum);
		c.set(Calendar.HOUR_OF_DAY, von2.getHours());
		c.set(Calendar.MINUTE, von2.getMinutes());
		c.set(Calendar.SECOND, von2.getSeconds());
		return new Time(c.getTimeInMillis());
	}

	private Time calculatePause(int pause2) {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.MINUTE, pause2);
		c.setTime(this.tagesdatum);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, pause2);
		c.set(Calendar.SECOND, 0);
		return new Time(c.getTimeInMillis());
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

	public Time getPause() {
		return pause;
	}

	public void setPause(Time pause) {
		this.pause = pause;
	}

	public Date getUeberstunden() {
		return ueberstunden;
	}

	public void setUeberstunden(Time ueberstunden) {
		this.ueberstunden = ueberstunden;
	}

	public int getSollstunden() {
		return sollstunden;
	}

	public void setSollstunden(int sollstunden) {
		this.sollstunden = sollstunden;
	}
	
	private Time calculateUeberstunden() {
		Calendar c = GregorianCalendar.getInstance();
		Calendar c2 = GregorianCalendar.getInstance();
		c2.setTime(this.tagesdatum);
		c2.set(Calendar.HOUR_OF_DAY, this.sollstunden);
		c2.set(Calendar.MINUTE, 0);
		c2.set(Calendar.SECOND, 0);
		c.setTime(this.tagesdatum);
		c.set(Calendar.HOUR_OF_DAY, -c2.get(Calendar.HOUR_OF_DAY) + this.arbeitsstunden.getHours());
		c.set(Calendar.MINUTE, -c2.get(Calendar.MINUTE) + this.arbeitsstunden.getMinutes());
		c.set(Calendar.SECOND, -c2.get(Calendar.SECOND) + this.arbeitsstunden.getSeconds());
		return new Time(c.getTimeInMillis());
	}
	
	private Time calculateArbeitsstunden() {
		Calendar c = GregorianCalendar.getInstance();
		c.setTime(this.tagesdatum);
		c.set(Calendar.HOUR_OF_DAY, this.bis.getHours()-this.von.getHours()-this.pause.getHours());
		c.set(Calendar.MINUTE, this.bis.getMinutes()-this.von.getMinutes()-this.pause.getMinutes());
		c.set(Calendar.SECOND, this.bis.getSeconds()-this.von.getSeconds()-this.pause.getSeconds());
		return new Time(c.getTimeInMillis());
	}

	public Date getArbeitsstunden() {
		return arbeitsstunden;
	}

	public void setArbeitsstunden(Time arbeitsstunden) {
		this.arbeitsstunden = arbeitsstunden;
	}

	public float getFloatUeberstunden() {
		return floatUeberstunden;
	}

	public void setFloatUeberstunden(float floatUeberstunden) {
		this.floatUeberstunden = floatUeberstunden;
	}
}
