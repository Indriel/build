package output;

import java.io.FileWriter;
import java.io.PrintWriter;

public class CSS_Creator {

	public CSS_Creator(){
		try {
			FileWriter outFile = new FileWriter("format.css");
			PrintWriter out = new PrintWriter(outFile);
//			
			out.println(" body{ top: 0; left: 0; font-family: Arial, Helvetica, sans-serif; }");
			out.println(" #cont{ position:absolute; top: 5%; left: 5%; }");
			out.println(" td, th{ text-align:center; padding-left:15px; padding-right:15px; }");
			out.println(" table, td, th{ border-collapse:collapse; border: 2px solid #000; }");
			out.close();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
