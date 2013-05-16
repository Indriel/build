package database;

public class DistrictInUseException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DistrictInUseException(String meldung) {
		super(meldung);
	}
}
