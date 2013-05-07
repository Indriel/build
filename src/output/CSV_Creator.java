package output;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Vector;

public class CSV_Creator {
	
//	private Vector<Vector<String>> myData = new Vector<Vector<String>>();

	public CSV_Creator(Vector<Vector<String>> myData){
		
		try {
			FileWriter outFile = new FileWriter("auswertung.csv");
			PrintWriter out = new PrintWriter(outFile);
//			
			for (Iterator i = myData.iterator(); i.hasNext();)
			{
				Vector<String> tmp = (Vector<String>)i.next();
				for(Iterator it = tmp.iterator(); it.hasNext();)
				{
					String str = (String)it.next();
					out.print(str + ";");
				}
				out.println("");
			}
			out.close();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
}
