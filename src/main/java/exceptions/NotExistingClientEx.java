package exceptions;

public class NotExistingClientEx extends RuntimeException {

	
	private static final long serialVersionUID = 7277815132858533700L;
	public NotExistingClientEx(String message) {
		super(message);
	}
}
