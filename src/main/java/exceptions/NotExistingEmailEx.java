package exceptions;

public class NotExistingEmailEx extends RuntimeException {

	
	private static final long serialVersionUID = 7277815132858533700L;
	public NotExistingEmailEx(String message) {
		super(message);
	}
}
