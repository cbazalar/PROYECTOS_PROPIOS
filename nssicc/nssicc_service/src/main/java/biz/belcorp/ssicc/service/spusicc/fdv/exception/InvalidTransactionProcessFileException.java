package biz.belcorp.ssicc.service.spusicc.fdv.exception;

import biz.belcorp.ssicc.service.exception.ServiceException;

/**
 * <p>
 * <a href="InvalidTransactionProcessFileException.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:fayala@belcorp.biz">Frank Ayala</a>
 */

public class InvalidTransactionProcessFileException extends ServiceException{

	private String fileName;
	
	private String description;
	
	/**
	 * Create a new InvalidDescriptionException for the given object, with
	 * the default message.
	 * 
	 * @param description
	 *            the description of the object that should have been retrieved
	 * @param fileName
	 *            the name of file upload
	 */
	public InvalidTransactionProcessFileException(String description, String fileName) {
		this(description, fileName, "Ocurrio un error al intentar guardar los datos del archivo ["
				+ fileName + "], el detalle es el siguiente: "+ description, null);
	}

	/**
	 * Create a new InvalidTransactionProcessFileException for the given object, with
	 * the given explicit message and exception.
	 * 
	 * @param description
	 *            the description of the object that should have been retrieved
	 * @param fileName
	 *            the name of file upload
	 * @param msg
	 *            exception message
	 * @param ex
	 *            source exception
	 */
	public InvalidTransactionProcessFileException(String description, 
			String fileName, String msg, Throwable ex) {
		super(msg, ex);
		this.description = description;
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	
}
