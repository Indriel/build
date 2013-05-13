package output;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.text.Document;
import javax.swing.text.html.HTML;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

import data.MitarbeiterMonat;
import data.MitarbeiterTag;
import data.User;
import database.MySQL;

public class OutputGenerator {
	private String format;
	private User user;
	private static MySQL database = MySQL.getInstance();
	
	public OutputGenerator() {
		
	}
	
	public static void generateOutputMitarbeiter(User u, Date von, Date bis) throws SQLException, ParserConfigurationException {
		Vector<MitarbeiterMonat> vmon = database.getMitarbeiterMonatForAuswertung(u, von, bis);
		Vector<Vector<MitarbeiterTag>> vtage = new Vector<Vector<MitarbeiterTag>>();
		for(Iterator<MitarbeiterMonat> i = vmon.iterator(); i.hasNext();) {
			MitarbeiterMonat m = i.next();
			Vector<MitarbeiterTag> vtforAdd = database.getMitarbeiterTagForAuswertung(u, m.getDatum());
			vtage.add(vtforAdd);
		}
		org.w3c.dom.Document doc = OutputGenerator.createHTMDocForMitarbeiterAusgabe();
		OutputGenerator.generatedocTree(doc, u, von, bis, vmon, vtage);
		OutputGenerator.printToFile(doc);
	}

	private static void generatedocTree(org.w3c.dom.Document doc, User u, Date von, Date bis, Vector<MitarbeiterMonat> months, Vector<Vector<MitarbeiterTag>> days) throws DOMException, SQLException {
		Element rootEle = doc.createElement("html");
		doc.appendChild(rootEle);
		
		//			creates <head> and <title> tag
		Element headEle = doc.createElement("head");
		Element titleEle = doc.createElement("title");
		
		//			set value to <title> tag
		Text kuerzelText = doc.createTextNode("Auswertung: " + u.getName());
		titleEle.appendChild(kuerzelText);
		headEle.appendChild(titleEle);
		
		Element linkEle = doc.createElement("link");
		linkEle.setAttribute("rel", "stylesheet");
		linkEle.setAttribute("type", "text/css");
		linkEle.setAttribute("href", "./format.css");
		headEle.appendChild(linkEle);
		rootEle.appendChild(headEle);
		
		Element bodyEle = doc.createElement("body");
		
		Element h1Ueberschrift = doc.createElement("h1");
		h1Ueberschrift.appendChild(doc.createTextNode("Auswertung: " + u.getName()));
		bodyEle.appendChild(h1Ueberschrift);
		
		Element h3ZeitraumEle = doc.createElement("h2");
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM YYYY");
		h3ZeitraumEle.appendChild(doc.createTextNode(sdf.format(von) + " bis " + sdf.format(bis)));
		bodyEle.appendChild(h3ZeitraumEle);
		
		Element contentDivElement = doc.createElement("div");
		bodyEle.appendChild(contentDivElement);
		
		int x = 0;
		for(Iterator<MitarbeiterMonat> i = months.iterator(); i.hasNext(); x++) {
			MitarbeiterMonat m = i.next();
			Element derMonat = doc.createElement("h4");
//			derMonat.setAttribute("title", );
			derMonat.appendChild(doc.createTextNode(sdf.format(m.getDatum())));
			contentDivElement.appendChild(derMonat);
			//float ueberstundenuebertrag = m.getUebertrag();
			Vector<MitarbeiterTag> d = days.get(x);
			Element tabledaysElemennt = doc.createElement("table");
			Element tabledaysHeadRowElement = createHeadRow(doc);
			tabledaysElemennt.appendChild(tabledaysHeadRowElement);
			for(Iterator<MitarbeiterTag> iday = d.iterator(); iday.hasNext();) {
				Element tabledaysRowElement = createTableDaysRow(doc, iday.next());
				tabledaysElemennt.appendChild(tabledaysRowElement);
			}
			appendCalculationAtTheEndOfTable(m, tabledaysElemennt, doc);
			//Still need to Add Stuff here.
			contentDivElement.appendChild(tabledaysElemennt);
		}
		rootEle.appendChild(bodyEle);
	}

	private static void appendCalculationAtTheEndOfTable(MitarbeiterMonat m, Element tableElement, org.w3c.dom.Document doc) throws DOMException, SQLException {
		Element trSum = doc.createElement("tr");
		for(int i = 0; i<5; i++)
			trSum.appendChild(doc.createElement("td"));
		Element tdSumText = doc.createElement("td");
		tdSumText.appendChild(doc.createTextNode("Summe"));
		trSum.appendChild(tdSumText);
		Element tdSumErg = doc.createElement("td");
		//System.out.println("Der Übertrag des Monats = " + String.valueOf(m.getUebertrag()));
		tdSumErg.appendChild(doc.createTextNode(String.valueOf(m.getUebertrag())));
		trSum.appendChild(tdSumErg);
		tableElement.appendChild(trSum);
		//-------------------------------------------
		Element trUeber = doc.createElement("tr");
		for(int i = 0; i<5; i++)
			trUeber.appendChild(doc.createElement("td"));
		Element tdUeberText = doc.createElement("td");
		tdUeberText.appendChild(doc.createTextNode("Uebertrag"));
		trUeber.appendChild(tdUeberText);
		Element tdUeberErg = doc.createElement("td");
		float ueber = database.getUebertragsSumme(m.getDatum());
		//System.out.println("Der Uebertrag des letzten Monats = " + String.valueOf(ueber));
		tdUeberErg.appendChild(doc.createTextNode(String.valueOf(ueber)));
		trUeber.appendChild(tdUeberErg);
		tableElement.appendChild(trUeber);
		//----------------------------------------------
		Element trGesamt = doc.createElement("tr");
		for(int i = 0; i<5; i++)
			trGesamt.appendChild(doc.createElement("td"));
		Element tdGesamtText = doc.createElement("td");
		tdGesamtText.appendChild(doc.createTextNode("GESAMT"));
		trGesamt.appendChild(tdGesamtText);
		Element tdGesamtErg = doc.createElement("td");
		//float ueber = database.getUebertragsSumme(m.getDatum());
		//System.out.println("Des Geamte: " + String.valueOf(ueber + m.getUebertrag()));
		tdGesamtErg.appendChild(doc.createTextNode(String.valueOf(ueber + m.getUebertrag())));
		trGesamt.appendChild(tdGesamtErg);
		tableElement.appendChild(trGesamt);
	}

	private static Element createTableDaysRow(org.w3c.dom.Document doc,
			MitarbeiterTag mitarbeiterTag) throws DOMException, SQLException {
		Element trElement = doc.createElement("tr");
		SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.YYYY");
		SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");
		//------Tagesdatum
		Element tdDatum = doc.createElement("td");
		tdDatum.setAttribute("datum", formatDate.format(mitarbeiterTag.getTagesdatum()));
		tdDatum.appendChild(doc.createTextNode(formatDate.format(mitarbeiterTag.getTagesdatum())));
		trElement.appendChild(tdDatum);
		//------Arbeitsbeginn
		Element tdAbeginn = doc.createElement("td");
		tdAbeginn.setAttribute("beginn", formatTime.format(mitarbeiterTag.getVon()));
		tdAbeginn.appendChild(doc.createTextNode(formatTime.format(mitarbeiterTag.getVon())));
		trElement.appendChild(tdAbeginn);
		//-------Arbeitsende
		Element tdEnde = doc.createElement("td");
		tdEnde.setAttribute("ende", formatTime.format(mitarbeiterTag.getBis()));
		tdEnde.appendChild(doc.createTextNode(formatTime.format(mitarbeiterTag.getBis())));
		trElement.appendChild(tdEnde);
		//-------Mittagspause
		Element tdPause = doc.createElement("td");
		tdPause.setAttribute("pause", formatTime.format(mitarbeiterTag.getPause()));
		tdPause.appendChild(doc.createTextNode( formatTime.format(mitarbeiterTag.getPause())));
		trElement.appendChild(tdPause);
		//-------Arbeitsstunden
		Element tdArbeitsstunde = doc.createElement("td");
		tdArbeitsstunde.setAttribute("arbeitsstunden", formatTime.format(mitarbeiterTag.getArbeitsstunden()));
		tdArbeitsstunde.appendChild(doc.createTextNode(formatTime.format(mitarbeiterTag.getArbeitsstunden())));
		trElement.appendChild(tdArbeitsstunde);
		//-------Überstunden
		Element tdUeberstunde = doc.createElement("td");
		tdUeberstunde.appendChild(doc.createTextNode(String.valueOf(mitarbeiterTag.getFloatUeberstunden())));
		trElement.appendChild(tdUeberstunde);
		//-------Anmerkung
		Element tdAnmerkung = doc.createElement("td");
		tdAnmerkung.appendChild(doc.createTextNode(database.getActivity(mitarbeiterTag.getTaetigkeits_id())));
		trElement.appendChild(tdAnmerkung);
		
		return trElement;
	}

	private static Element createHeadRow(org.w3c.dom.Document doc) {
		Element headRow = doc.createElement("tr");
		String[] array = new String[]{"Datum","Arbeitsbeginn","Arbeitsende","Mitagspause","Arbeitsstunden", "Ueberstunden", "Anmerkung"};
		for(int i = 0; i<array.length; i++) {
			Element headColumn = doc.createElement("td");
			//headColumn.setAttribute("name", array[i]);
			Text atext = doc.createTextNode(array[i]);
			headColumn.setAttribute("font-weight", "bold");
			headColumn.appendChild(atext);
			headRow.appendChild(headColumn);
		}
		return headRow;
	}

	private static org.w3c.dom.Document createHTMDocForMitarbeiterAusgabe() throws ParserConfigurationException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		return db.newDocument();
	}
	
	private static void printToFile(org.w3c.dom.Document doc) {

		try
		{
			//print
			OutputFormat format = new OutputFormat(doc);
			format.setIndenting(true);
			//to generate output to console use this serializer
			//XMLSerializer serializer = new XMLSerializer(System.out, format);
			//to generate a file output use fileoutputstream instead of system.out
			XMLSerializer serializer = new XMLSerializer(
			new FileOutputStream(new File("auswertung.html")), format);
			serializer.serialize(doc);

		} catch(IOException ie) {
		    ie.printStackTrace();
		}
	}
}
