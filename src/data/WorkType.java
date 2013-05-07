package data;

public class WorkType {
	
	private int id;
	private String work_type;
	
	
	public WorkType(int id, String work_type) {
		super();
		this.id = id;
		this.work_type = work_type;
	}
	
	
	public WorkType() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public int getId() {
		return id;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getWork_type() {
		return work_type;
	}
	
	
	public void setWork_type(String work_type) {
		this.work_type = work_type;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return work_type;
	}
	
}
