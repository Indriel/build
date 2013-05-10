package output;

import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Text;

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
		OutputGenerator.generatedocTree(doc, u);
	}

	private static void generatedocTree(org.w3c.dom.Document doc, User u) {
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
		/*linkEle.setAttribute("rel", "stylesheet");
		linkEle.setAttribute("type", "text/css");
		linkEle.setAttribute("href", "./format.css");*/
		headEle.appendChild(linkEle);
		rootEle.appendChild(headEle);
		
		
	}

	private static org.w3c.dom.Document createHTMDocForMitarbeiterAusgabe() throws ParserConfigurationException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		return db.newDocument();
	}
}
