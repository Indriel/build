package data;

import java.util.GregorianCalendar;

public class DayOfWeek {
	
	private GregorianCalendar gc = null;
	
	public DayOfWeek(String date) {
		String part[] = date.split("-");
		try{
			gc = new GregorianCalendar(Integer.parseInt(part[0]), Integer.parseInt(part[1]) - 1, Integer.parseInt(part[2]));
		}catch(Exception e){;}
	}
	
	public String getDayOfWeek(){
		int iWDay = gc.get(GregorianCalendar.DAY_OF_WEEK);
        String sWDay = "";
        switch (iWDay) {
            case 1:
                sWDay = "So";
                break;
            case 2:
                sWDay = "Mo";
                break;
            case 3:
                sWDay = "Di";
                break;
            case 4:
                sWDay = "Mi";
                break;     
            case 5:
                sWDay = "Do";
                break;
            case 6:
                sWDay = "Fr";
                break;
            case 7:
                sWDay = "Sa";
                break; 
        }
        return sWDay;
	}
}
