package it.lastminute.exceptions;

public class NoInputGoodException extends Exception {

	private static final long serialVersionUID = 1L;
	
    public NoInputGoodException() {
        super("No good found");
    }
}
