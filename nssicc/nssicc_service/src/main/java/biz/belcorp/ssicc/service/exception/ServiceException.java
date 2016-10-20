/*
 * Created on 08/03/2005 02:57:40 PM
 *
 * biz.belcorp.ssicc.service.ServiceException
 */
package biz.belcorp.ssicc.service.exception;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ServiceException.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public class ServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5988415880555998626L;

	/**
	 *  
	 */
	public ServiceException() {
		super();
	}

	/**
	 * @param message
	 */
	public ServiceException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}