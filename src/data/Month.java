package data;

public class Month {
	String name;
	
	public Month(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toStringForDatabase() {
		return name;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String part[] = name.split("-");
		return part[0] + "-" + part[1];
	}
	
	
}
