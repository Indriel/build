package output;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.jws.soap.SOAPBinding.Use;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

import data.District;
import data.MitarbeiterMonat;
import data.MitarbeiterTag;
import data.Mitarbeitertaetigkeit;
import data.User;
import data.ZusammenfassungDaten;
import database.MySQL;


@SuppressWarnings("restriction")
public class HTML_Creator {
	
	public static final String DETAIL = "Detail";
	public static final String ZEITAUFZEICHUNG = "Zeitaufzeichung";
	public static final String ZUSAMMENFASSUNG = "Zusammenfassung";
	private String type;
	private MySQL database;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
	SimpleDateFormat mona = new SimpleDateFormat("MMMM yyyy");
	SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");
	
	public HTML_Creator(String type)
	{
		this.type = type;
		this.database = MySQL.getInstance();
	}
	
	
	public void create(boolean all, User usr, Date von, Date bis, String keyword) throws SQLException, IOException {
		if(type.equals(HTML_Creator.DETAIL)) {
			this.createDetail(all,usr,von,bis,keyword);
		}
		else if(type.equals(HTML_Creator.ZEITAUFZEICHUNG)) {
			this.createZeitaufzeichung(all, usr, von, bis);
		}
		else if(type.equals(HTML_Creator.ZUSAMMENFASSUNG)) {
			this.createZusammenfassung(all,usr,von,bis,keyword);
		}
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
		
		
		File output = new File("auswetung.html");
		FileWriter outFile = new FileWriter(output);
		PrintWriter out = new PrintWriter(outFile);			
		
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"format.css\">");
		out.println("</head>");
		out.println("<body>");
		
		out.print(name+";"+sdf.format(von)+"-"+sdf.format(bis)+";"+"Keywort: " + keyword);
		out.println("<h1>" + name + " " + mona.format(von) + "-" + mona.format(bis) + " Keywort: " + keyword + "</h1>");
		out.println("<br >");
		out.println("<br >");
		out.println("<table>");
		out.println("<tr>");
		for(ZusammenfassungDaten z : data) {
			out.println("<td>" + z.getD_name() + "</td>");
		}
		out.println("</tr>");
		out.println("<tr>");
		for(ZusammenfassungDaten z : data) {
			out.println("<td>" + z.getHours() + " h</td>");
		}
		out.println("</tr>");
		out.println("<tr>");
		for(ZusammenfassungDaten z : data) {
			out.println("<td>"+ z.getPercentage() +" %</td>");
		}
		out.println("</tr>");
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
		out.close();
		System.out.println("HTML GENERATION COMPLETED!!!!!");
		Desktop.getDesktop().open(output);
	}


	private void createZeitaufzeichung(boolean all, User usr, Date von, Date bis) throws IOException, SQLException {
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
		
		File output = new File("auswetung.html");
		FileWriter outFile = new FileWriter(output);
		PrintWriter out = new PrintWriter(outFile);			
		
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"format.css\">");
		out.println("</head>");
		out.println("<body>");
		
		for(User u : mitarbeiter) {
			out.println("<h1>" + u.getName() + " " + mona.format(von) + "-" + mona.format(bis) + "</h1>");
			out.println("<br >");
			out.println("<br >");
			monate = this.database.getMitarbeiterMonatForAuswertung(u, von, bis);
			for(MitarbeiterMonat m : monate) {
				out.println("<h2>" + mona.format(m.getDatum()) + "</h2>");
				out.println("<br >");
				out.println("<br >");
				out.println("<table>");
				out.println("<tr>");
				out.println("<td>Datum</td> <td>Arbeitsbeginn</td> <td>Arbeitsende</td> <td>Mittagspause</td> <td>Arbeitsstunden</td> <td>&Uuml;berstunden</td> <td>Anmerkung</td>");
				out.println("</tr>");
				tage = this.database.getMitarbeiterTagForAuswertung(u, m.getDatum());
				float sum = 0;
				for(MitarbeiterTag t : tage) {
					sum += t.getFloatUeberstunden();
					out.println("<tr>");
					out.println("<td>"+sdf.format(t.getTagesdatum())+"</td><td>"+formatTime.format(t.getVon())+"</td><td>"+formatTime.format(t.getBis())+"</td><td>"+formatTime.format(t.getPause())+"</td><td>"+t.getFloatUeberstunden()+"</td><td>"+database.getActivity(t.getTaetigkeits_id())+"</td>");
					out.println("</tr>");
				}
				out.println("<tr>");
				out.println("<td></td><td></td><td></td><td></td><td></td><td>Summe</td><td>" + sum + "</td>");
				out.println("</tr>");
				float monueber = this.database.getUebertragsSumme(m.getDatum());
				out.println("<tr>");
				out.println("<td></td><td></td><td></td><td></td><td></td><td>&Uuml;bertrag</td><td>" + monueber + "</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td></td><td></td><td></td><td></td><td></td><td>GESAMT</td><td>" + (sum+monueber) + "</td>");
				out.println("</tr>");
				out.println("<br >");
				out.println("<br >");
			}
			out.println("<br >");
			out.println("<br >");
		}
		out.close();
		out.println("</body>");
		out.println("</html>");
		System.out.println("HTML GENERATION COMPLETED!!!!!");
		Desktop.getDesktop().open(output);
	}


	private void createDetail(boolean all, User usr, Date von, Date bis,
			String keyword) throws SQLException, IOException {
		
		
		Vector<User> mitarbeiter;
		Vector<District> bereiche = this.database.getDistricts();
		Vector<Mitarbeitertaetigkeit> taets;
		
		if(all) {
			mitarbeiter = this.database.getUsers();
		}
		else {
			mitarbeiter = new Vector<User>();
			mitarbeiter.add(usr);
		}
		
		File output = new File("auswertung.html");
		FileWriter fileout = new FileWriter(output);
		PrintWriter out = new PrintWriter(fileout);
		
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"format.css\">");
		out.println("</head>");
		out.println("<body>");
		for(User u : mitarbeiter) {
			out.println("<h1>" + u.getName() + " " + mona.format(von) + "-" + mona.format(bis) + " Keyword: " + keyword + "</h1>");
			for(District d : bereiche) {
				out.println("<h2>Bereich-" + d.getName() + "</h2>");
				out.println("<table>");
				out.println("<tr>");
				out.println("<td>Datum</td> <td>Beschreibung</td> <td>Dauer</td>");
				out.println("</tr>");
				taets = this.database.getMitarbeiterTaetigkeitenforAuswertung(u.getId(), d.getId(), von, bis, keyword);
				int sum = 0;
				for(Mitarbeitertaetigkeit mt : taets) {
					out.println("<tr>");
					out.println("<td>" + sdf.format(mt.getDatum()) + "</td><td>" + mt.getBeschreibung() + "</td><td>" + mt.getDauer() + "</td>");
					out.println("</tr>");
					sum += mt.getDauer();
				}
				out.println("<tr>");
				out.println("<td></td><td>SUMME</td><td>"+sum+"</td>");
				//out.println("");
				out.println("</tr>");
				out.println("</table>");
			}
			out.println("<br >");
			out.println("<br >");
		}
		out.println("</body>");
		out.println("</html>");
		
		out.close();
		System.out.println("HTML_Generation completed");
		Desktop.getDesktop().open(output);
	}

	class XMLCreator{
		//No generics
		Vector<Vector<String>> myData = null;
		Document dom;

		public XMLCreator(Vector<Vector<String>> col) {
			//create a list to hold the data
			myData = col;
			//Get a DOM object
			createDocument();
			createDOMTree();
			printToFile();
		}
		
		
		private void createDocument() {

			//get an instance of factory
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			try {
			//get an instance of builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			//create an instance of DOM
			dom = db.newDocument();

			}catch(ParserConfigurationException pce) {
				//dump it
				System.out.println("Error while trying to instantiate DocumentBuilder " + pce);
				System.exit(1);
			}

		}
		
		
		private void createDOMTree(){

			//create the root element <Books>
			
			//			creates <html> tag
			Element rootEle = dom.createElement("html");
			dom.appendChild(rootEle);
			
			//			creates <head> and <title> tag
			Element headEle = dom.createElement("head");
			Element titleEle = dom.createElement("title");
			
			//			set value to <title> tag
			Text kuerzelText = dom.createTextNode("Auswertung");
			titleEle.appendChild(kuerzelText);
			headEle.appendChild(titleEle);
			
			Element linkEle = dom.createElement("link");
			linkEle.setAttribute("rel", "stylesheet");
			linkEle.setAttribute("type", "text/css");
			linkEle.setAttribute("href", "./format.css");
			headEle.appendChild(linkEle);
			
			rootEle.appendChild(headEle);
			
			
			Element bodyEle = dom.createElement("body");
			Element divEle = dom.createElement("div");
			divEle.setAttribute("id", "cont");
			
			Vector<String> tmp = myData.get(0);
			Element h2Ele = dom.createElement("h2");
			String h1Str = "";
			for(Iterator i = tmp.iterator(); i.hasNext(); )
			{
				h1Str = h1Str + (String)i.next() + " ";
			}
			Text aText = dom.createTextNode(h1Str);
			h2Ele.appendChild(aText);
			divEle.appendChild(h2Ele);
			myData.remove(0);
			
			Element tableEle = dom.createElement("table");
//			tableEle.setAttribute("border", "1");
			
			tmp = myData.get(0);
			Element trHeadEle = createTrHeadElement(tmp);
			tableEle.appendChild(trHeadEle);
			myData.remove(0);
			
			Iterator it  = myData.iterator();
			while(it.hasNext()) {
				tmp = (Vector<String>)it.next();
				Element trEle = createTrElement(tmp);
				tableEle.appendChild(trEle);
			}
			
			divEle.appendChild(tableEle);
			bodyEle.appendChild(divEle);
			rootEle.appendChild(bodyEle);
			
		}
		
		private Element createTrElement(Vector<String> d) {

			Element trEle = dom.createElement("tr");
//			trEle.setAttribute("BezeichnungG", b.getBezeichnung());

			for(Iterator i = d.iterator(); i.hasNext();){
				//create author element and author text node and attach it to bookElement
					String str = (String)i.next();
					Element tdEle = dom.createElement("td");
					Text aText = dom.createTextNode(str);
					tdEle.appendChild(aText);
					trEle.appendChild(tdEle);
				}

			return trEle;

		}
		
		private Element createTrHeadElement(Vector<String> d) {

			Element trEle = dom.createElement("tr");
//			trEle.setAttribute("BezeichnungG", b.getBezeichnung());
			
			for(Iterator i = d.iterator(); i.hasNext();){
			//create author element and author text node and attach it to bookElement
				String str = (String)i.next();
				Element tdEle = dom.createElement("th");
				Text aText = dom.createTextNode(str);
				tdEle.appendChild(aText);
				trEle.appendChild(tdEle);
			}

			return trEle;

		}
		
		
		private void printToFile() {

			try
			{
				//print
				OutputFormat format = new OutputFormat(dom);
				format.setIndenting(true);
				//to generate output to console use this serializer
				//XMLSerializer serializer = new XMLSerializer(System.out, format);
				//to generate a file output use fileoutputstream instead of system.out
				XMLSerializer serializer = new XMLSerializer(
				new FileOutputStream(new File("auswertung.html")), format);
				serializer.serialize(dom);

			} catch(IOException ie) {
			    ie.printStackTrace();
			}
		}
    }
}
