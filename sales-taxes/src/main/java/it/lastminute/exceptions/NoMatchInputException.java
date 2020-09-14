package it.lastminute.exceptions;

public class NoMatchInputException extends Exception {

	private static final long serialVersionUID = 1L;
	
    public NoMatchInputException() {
        super("Format your input record properly");
    }
}
