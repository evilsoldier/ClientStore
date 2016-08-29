package exceptions;

public class NotExistingAdressEx extends RuntimeException {

	
	private static final long serialVersionUID = 7277815132858533700L;
	public NotExistingAdressEx(String message) {
		super(message);
	}
}