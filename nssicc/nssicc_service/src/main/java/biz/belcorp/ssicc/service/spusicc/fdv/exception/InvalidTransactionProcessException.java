package biz.belcorp.ssicc.service.spusicc.fdv.exception;

import biz.belcorp.ssicc.service.exception.ServiceException;

/**
 * <p>
 * <a href="InvalidTransactionProcessException.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:fayala@belcorp.biz">Frank Ayala</a>
 */
public class InvalidTransactionProcessException extends ServiceException{

	private Object persistentClass;
	
	private String typeProcess;
	
	private String description;
	
	/**
	 * Create a new InvalidDescriptionException for the given object, with
	 * the default message.
	 * 
	 * @param persistentClass
	 *            the persistent class
	 * @param description
	 *            the description of the object that should have been retrieved
	 * @param typeProcess
	 *            the type process of the object that should have been retrieved
	 */
	public InvalidTransactionProcessException(Class persistentClass,
			String description, String typeProcess) {
		this(persistentClass, description, typeProcess, "En la operacin ["
				+ typeProcess + "] de la clase [" + persistentClass.getName() + "]" +
				" hubo una excepcin, el detalle es el siguiente: "+ description, null);
	}

	/**
	 * Create a new InvalidTransactionProcessException for the given object, with
	 * the given explicit message and exception.
	 * 
	 * @param persistentClass
	 *            the persistent class
	 * @param description
	 *            the description of the object that should have been retrieved
	 * @param typeProcess
	 *            the type process of the object that should have been retrieved
	 * @param msg
	 *            exception message
	 * @param ex
	 *            source exception
	 */
	public InvalidTransactionProcessException(Class persistentClass,
			String description, String typeProcess, String msg, Throwable ex) {
		super(msg, ex);
		this.persistentClass = persistentClass;
		this.description = description;
		this.typeProcess = typeProcess;
	}

	/**
	 * Create a new InvalidTransactionProcessException for the given object, with
	 * the default message.
	 * 
	 * @param persistentClassName
	 *            the name of the persistent class
	 * @param description
	 *            the description of the object that should have been retrieved
	 * @param typeProcess
	 *            the type process of the object that should have been retrieved           
	 */
	public InvalidTransactionProcessException(String persistentClassName,
			String description, String typeProcess) {
		this(persistentClassName, description, typeProcess, "En la operacin ["
				+ typeProcess + "] de la clase [" + persistentClassName + "]" +
				" hubo una excepcin, el detalle es el siguiente: "+ description, null);
	}

	/**
	 * Create a new InvalidTransactionProcessException for the given object, with
	 * the given explicit message and exception.
	 * 
	 * @param persistentClassName
	 *            the name of the persistent class
	 * @param description
	 *            the description of the object that should have been retrieved
	 * @param typeProcess
	 *            the type process of the object that should have been retrieved            
	 * @param msg
	 *            exception message
	 * @param ex
	 *            source exception
	 */
	public InvalidTransactionProcessException(String persistentClassName,
			String description, String typeProcess, String msg, Throwable ex) {
		super(msg, ex);
		this.persistentClass = persistentClassName;
		this.description = description;
		this.typeProcess = typeProcess;
	}
	
	public Object getPersistentClass() {
		return persistentClass;
	}

	public void setPersistentClass(Object persistentClass) {
		this.persistentClass = persistentClass;
	}

	public String getTypeProcess() {
		return typeProcess;
	}

	public void setTypeProcess(String typeProcess) {
		this.typeProcess = typeProcess;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Return the name of the persistent class of the object that was not found.
	 * Will work for both Class objects and String names.
	 */
	public String getPersistentClassName() {
		return (this.persistentClass instanceof Class ? ((Class) this.persistentClass)
				.getName()
				: this.persistentClass.toString());
	}
}
