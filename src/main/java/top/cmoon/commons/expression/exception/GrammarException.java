package top.cmoon.commons.expression.exception;

public class GrammarException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GrammarException(String msg) {
		super(msg);
	}

	public GrammarException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
