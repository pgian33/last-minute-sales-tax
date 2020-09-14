package it.lastminute.exceptions;

public class NoInputItemException extends Exception {

	private static final long serialVersionUID = 1L;
	
    public NoInputItemException() {
        super("No item found");
    }
}
