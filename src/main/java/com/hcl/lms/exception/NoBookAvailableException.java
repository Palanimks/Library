package com.hcl.lms.exception;

/**
 * 
 * @author User1
 *
 */
public class NoBookAvailableException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NoBookAvailableException(String message)
	{
		super(message);
	}

}
