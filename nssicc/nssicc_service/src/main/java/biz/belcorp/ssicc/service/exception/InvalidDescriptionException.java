/*
 * Created on 14/03/2005 06:06:09 PM
 *
 * biz.belcorp.ssicc.service.InvalidDescriptionException
 */
package biz.belcorp.ssicc.service.exception;

/**
 * TODO Include class description here. 
 * <p>
 * <a href="InvalidDescriptionException.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public class InvalidDescriptionException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 594999228685110498L;

	private Object persistentClass;

	private String description;

	/**
	 * Create a new InvalidDescriptionException for the given object, with
	 * the default "already exists" message.
	 * 
	 * @param persistentClass
	 *            the persistent class
	 * @param description
	 *            the description of the object that should have been retrieved
	 */
	public InvalidDescriptionException(Class persistentClass,
			String description) {
		this(persistentClass, description, "Object of class ["
				+ persistentClass.getName() + "] with description ["
				+ description + "]: already exists", null);
	}

	/**
	 * Create a new InvalidDescriptionException for the given object, with
	 * the given explicit message and exception.
	 * 
	 * @param persistentClass
	 *            the persistent class
	 * @param description
	 *            the description of the object that should have been retrieved
	 * @param msg
	 *            exception message
	 * @param ex
	 *            source exception
	 */
	public InvalidDescriptionException(Class persistentClass,
			String description, String msg, Throwable ex) {
		super(msg, ex);
		this.persistentClass = persistentClass;
		this.description = description;
	}

	/**
	 * Create a new InvalidDescriptionException for the given object, with
	 * the default "already exists" message.
	 * 
	 * @param persistentClassName
	 *            the name of the persistent class
	 * @param description
	 *            the description of the object that should have been retrieved
	 */
	public InvalidDescriptionException(String persistentClassName,
			String description) {
		this(persistentClassName, description, "Object of class ["
				+ persistentClassName + "] with description [" + description
				+ "]: already exists", null);
	}

	/**
	 * Create a new InvalidDescriptionException for the given object, with
	 * the given explicit message and exception.
	 * 
	 * @param persistentClassName
	 *            the name of the persistent class
	 * @param description
	 *            the description of the object that should have been retrieved
	 * @param msg
	 *            exception message
	 * @param ex
	 *            source exception
	 */
	public InvalidDescriptionException(String persistentClassName,
			String description, String msg, Throwable ex) {
		super(msg, ex);
		this.persistentClass = persistentClassName;
		this.description = description;
	}

	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return Returns the persistentClass.
	 */
	public Object getPersistentClass() {
		return persistentClass;
	}

	/**
	 * @param persistentClass
	 *            The persistentClass to set.
	 */
	public void setPersistentClass(Object persistentClass) {
		this.persistentClass = persistentClass;
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
