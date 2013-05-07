package output;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;


@SuppressWarnings("restriction")
public class HTML_Creator {
	
	private Vector<Vector<String>> myData = new Vector<Vector<String>>();
	
	public HTML_Creator(Vector<Vector<String>> vk)
	{
		myData = vk;
		XMLCreator x = new XMLCreator(myData);
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
