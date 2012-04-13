/**
 * 
 */
package com.dongbu.farm.common.exception;

/**
 * @author dhcho
 *
 */
public class CommonException extends RuntimeException {

	private static final long serialVersionUID = 4886068018029391783L;
	
	public CommonException() {
		super();
	}

	public CommonException(String message, Throwable cause) {
		super(message, cause);
	}

	public CommonException(String message) {
		super(message);
	}

	public CommonException(Throwable cause) {
		super(cause);
	}

}
