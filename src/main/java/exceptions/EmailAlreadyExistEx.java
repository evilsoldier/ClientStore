package exceptions;

public class EmailAlreadyExistEx extends RuntimeException {

	private static final long serialVersionUID = 7277815132858533700L;
	public EmailAlreadyExistEx(String message) {
		super(message);
	}
}