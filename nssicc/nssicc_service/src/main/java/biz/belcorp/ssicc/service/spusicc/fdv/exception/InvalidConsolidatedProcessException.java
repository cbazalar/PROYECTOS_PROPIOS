package biz.belcorp.ssicc.service.spusicc.fdv.exception;

import biz.belcorp.ssicc.service.exception.ServiceException;

/**
 * <p>
 * <a href="InvalidConsolidatedProcessException.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:fayala@belcorp.biz">Frank Ayala</a>
 */

public class InvalidConsolidatedProcessException extends ServiceException{

	private String description;
	
	/**
	 * Create a new InvalidConsolidatedProcessException for the given object, with
	 * the default message.
	 * 
	 * @param description
	 *            the description of the object that should have been retrieved
	 */
	public InvalidConsolidatedProcessException(String description) {
		this(description, "Ocurrio un error al intentar invocar al procedure de consolidado de la informacion," +
		"el detalle es el siguiente: "+ description, null);
	}

	/**
	 * Create a new InvalidConsolidatedProcessException for the given object, with
	 * the given explicit message and exception.
	 * 
	 * @param description
	 *            the description of the object that should have been retrieved
	 * @param msg
	 *            exception message
	 * @param ex
	 *            source exception
	 */
	public InvalidConsolidatedProcessException(String description, 
		String msg, Throwable ex) {
		super(msg, ex);
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
