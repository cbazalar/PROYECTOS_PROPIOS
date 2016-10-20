package biz.belcorp.ssicc.service.spusicc.fdv.exception;

import biz.belcorp.ssicc.service.exception.ServiceException;

/**
 * <p>
 * <a href="InvalidUploadException.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:fayala@belcorp.biz">Frank Ayala</a>
 */
public class InvalidUploadException extends ServiceException{

	private String description;
	private String indicatorProblem;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getIndicatorProblem() {
		return indicatorProblem;
	}

	public void setIndicatorProblem(String indicatorProblem) {
		this.indicatorProblem = indicatorProblem;
	}

	/**
	 * Create a new InvalidDescriptionException for the given object, with
	 * the default message.
	 * @param description
	 *            the description of the object that should have been retrieved
	 * @param indicatorProblem
	 *            the indicatorProblem of the object that should have been retrieved
	 */
	public InvalidUploadException(
			String description, String indicatorProblem) {
		this(description, indicatorProblem, "Ocurrio un error al adjuntar el archivo " +
		"["+ description +"]", null);
	}

	/**
	 * Create a new InvalidUploadException for the given object, with
	 * the given explicit message and exception.
	 * 
	 * @param description
	 *            the description of the object that should have been retrieved
	 * @param indicatorProblem
	 *            the indicatorProblem of the object that should have been retrieved
	 * @param msg
	 *            exception message
	 * @param ex
	 *            source exception
	 */
	public InvalidUploadException(String description, String indicatorProblem, String msg, Throwable ex) {
		super(msg, ex);
		this.description = description;
		this.indicatorProblem = indicatorProblem;
	}
}
