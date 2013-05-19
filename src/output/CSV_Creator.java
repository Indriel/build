package output;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import data.District;
import data.MitarbeiterMonat;
import data.MitarbeiterTag;
import data.Mitarbeitertaetigkeit;
import data.User;
import data.ZusammenfassungDaten;
import database.MySQL;

public class CSV_Creator {
	
	public static final String DETAIL = "detail";
	public static final String ZEITAUFZEICHNUNG = "zeitaufzeichnung";
	public static final String ZUSAMMENFASSUNG = "zusammenfassung";
	private String type;
	SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
	SimpleDateFormat mona = new SimpleDateFormat("MMMM yyyy");
	SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");
	
	private MySQL database;
		
	public CSV_Creator(String t) {
		type = t;
		database = MySQL.getInstance();
	}
	
	public void create(boolean all, User usr, Date von, Date bis, String keyword) throws SQLException, IOException {
		if(type.equals(CSV_Creator.DETAIL)) {
			this.createDetail(all,usr,von,bis,keyword);
		}
		else if(type.equals(CSV_Creator.ZEITAUFZEICHNUNG)) {
			this.createZeitaufzeichnung(all,usr,von,bis,keyword);
		}
		else if(type.equals(CSV_Creator.ZUSAMMENFASSUNG)) {
			this.createZusammenfassung(all,usr,von,bis,keyword);
		}
	}

	private void createZeitaufzeichnung(boolean all, User usr, Date von,
			Date bis, String keyword) throws SQLException, IOException {
		Vector<MitarbeiterTag> tage;
		Vector<MitarbeiterMonat> monate;
		Vector<User> mitarbeiter;
		
		if(all) {
			mitarbeiter = this.database.getUsers();
		}
		else {
			mitarbeiter = new Vector<User>();
			mitarbeiter.add(usr);
		}
		
		File output = new File("auswetung.csv");
		FileWriter outFile = new FileWriter(output);
		PrintWriter out = new PrintWriter(outFile);			
		
		
		
		for(User u : mitarbeiter) {
			out.print(u.getName()+";"+sdf.format(von)+"-"+sdf.format(bis)+";"+"Keywort: " + keyword);
			out.println("");
			out.println("");
			monate = this.database.getMitarbeiterMonatForAuswertung(u, von, bis);
			for(MitarbeiterMonat m : monate) {
				out.println(this.mona.format(m.getDatum()));
				out.println("");
				out.println("");
				out.print("Datum;Arbeitsbeginn;Arbeitsende;Mittagspause;Arbeitsstunden;Überstunden;Anmerkungen");
				out.println("");
				tage = this.database.getMitarbeiterTagForAuswertung(u, m.getDatum());
				float sum = 0;
				for(MitarbeiterTag t : tage) {
					sum += t.getFloatUeberstunden();
					out.print(sdf.format(t.getTagesdatum())+";"+formatTime.format(t.getVon())+";"+formatTime.format(t.getBis())+";"+formatTime.format(t.getPause())+";"+t.getFloatUeberstunden()+";"+database.getActivity(t.getTaetigkeits_id()));
					out.println("");
				}
				out.print(";;;;;SUMME;"+sum);
				out.println("");
				float monueber = this.database.getUebertragsSumme(m.getDatum());
				out.print(";;;;;Übertrag;" + monueber);
				out.println("");
				out.print(";;;;;GESAMT;"+(sum+monueber));
			}
			out.println("");
			out.println("");
		}
		out.close();
		System.out.println("CSV GENERATION COMPLETED!!!!!");
		Desktop.getDesktop().open(output);
	}

	private void createZusammenfassung(boolean all, User usr, Date von,
			Date bis, String keyword) throws SQLException, IOException {
		Vector<ZusammenfassungDaten> data;
		
		String name;
		
		if(all) {
			data = this.database.getHoursOfAllMitarbeiterForZfs(von, bis, keyword);
			name = "Gesamt";
		}
		else {
			data = this.database.getHoursOfSpecialMitarbeiterForZfs(von, bis, usr, keyword);
			name = usr.getName();
		}
		
		
		File output = new File("auswetung.csv");
		FileWriter outFile = new FileWriter(output);
		PrintWriter out = new PrintWriter(outFile);			
		
		out.print(name+";"+sdf.format(von)+"-"+sdf.format(bis)+";"+"Keywort: " + keyword);
		out.println("");
		out.println("");
		for(ZusammenfassungDaten z : data) {
			out.print(z.getD_name()+";");
		}
		out.println("");
		for(ZusammenfassungDaten z : data) {
			out.print(z.getHours()+" h;");
		}
		out.println("");
		for(ZusammenfassungDaten z : data) {
			out.print(z.getPercentage() + "%;");
		}
		out.close();
		System.out.println("CSV GENERATION COMPLETED!!!!!");
		Desktop.getDesktop().open(output);
	}

	private void createDetail(boolean all, User usr, Date von, Date bis, String keyword) throws SQLException, IOException {
		Vector<User> users;
		Vector<District> districts = this.database.getDistricts();
		Vector<Mitarbeitertaetigkeit> taets;
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		if(all) {
			users = this.database.getUsers();
		}
		else {
			users = new Vector<User>();
			users.add(usr);
		}
			File output = new File("auswetung.csv");
			FileWriter outFile = new FileWriter(output);
			PrintWriter out = new PrintWriter(outFile);			
			
			for(User u : users) {
				out.print(u.getName()+";"+sdf.format(von)+"-"+sdf.format(bis)+";"+"Keyword: "+keyword);
				out.println("");
				out.println("");
				for(District d : districts) {
					taets = this.database.getMitarbeiterTaetigkeitenforAuswertung(u.getId(), d.getId(), von, bis, keyword);
					out.print("Bereich - " + d.getName());
					out.println("");
					out.print("Datum;Beschreibung;Dauer");
					out.println("");
					int sum = 0;
					for(Mitarbeitertaetigkeit mt : taets) {
						int d1 = mt.getDauer();
						out.print(sdf.format(mt.getDatum())+";"+mt.getBeschreibung()+";"+d1);
						sum += d1;
						out.println("");
					}
					out.print(";SUMME;" + sum);
					out.println("");
					out.println("");
				}
			}
			/*for (Iterator i = myData.iterator(); i.hasNext();)
			{
				Vector<String> tmp = (Vector<String>)i.next();
				for(Iterator it = tmp.iterator(); it.hasNext();)
				{
					String str = (String)it.next();
					out.print(str + ";");
				}
				out.println("");
			}*/
			out.close();
			System.out.println("CSV GENERATION COMPLETED!!!!!");
			Desktop.getDesktop().open(output);
	}
	
	
}
