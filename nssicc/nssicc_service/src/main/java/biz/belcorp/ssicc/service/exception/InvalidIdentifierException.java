/*
 * Created on 08/03/2005 03:11:28 PM
 *
 * biz.belcorp.ssicc.service.InvalidIdentifierException
 */
package biz.belcorp.ssicc.service.exception;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InvalidIdentifierException.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public class InvalidIdentifierException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6371466749109342203L;

	private Object persistentClass;

	private Object identifier;

	/**
	 * Create a new InvalidIdentifierException for the given object, with
	 * the default "already exists" message.
	 * 
	 * @param persistentClass
	 *            the persistent class
	 * @param identifier
	 *            the ID of the object that should have been retrieved
	 */
	public InvalidIdentifierException(Class persistentClass,
			Object identifier) {
		this(persistentClass, identifier, "Object of class ["
				+ persistentClass.getName() + "] with identifier ["
				+ identifier + "]: already exists", null);
	}

	/**
	 * Create a new InvalidIdentifierException for the given object, with
	 * the given explicit message and exception.
	 * 
	 * @param persistentClass
	 *            the persistent class
	 * @param identifier
	 *            the ID of the object that should have been retrieved
	 * @param msg
	 *            exception message
	 * @param ex
	 *            source exception
	 */
	public InvalidIdentifierException(Class persistentClass,
			Object identifier, String msg, Throwable ex) {
		super(msg, ex);
		this.persistentClass = persistentClass;
		this.identifier = identifier;
	}

	/**
	 * Create a new InvalidIdentifierException for the given object, with
	 * the default "already exists" message.
	 * 
	 * @param persistentClassName
	 *            the name of the persistent class
	 * @param identifier
	 *            the ID of the object that should have been retrieved
	 */
	public InvalidIdentifierException(String persistentClassName,
			Object identifier) {
		this(persistentClassName, identifier, "Object of class ["
				+ persistentClassName + "] with identifier [" + identifier
				+ "]: already exists", null);
	}

	/**
	 * Create a new InvalidIdentifierException for the given object, with
	 * the given explicit message and exception.
	 * 
	 * @param persistentClassName
	 *            the name of the persistent class
	 * @param identifier
	 *            the ID of the object that should have been retrieved
	 * @param msg
	 *            exception message
	 * @param ex
	 *            source exception
	 */
	public InvalidIdentifierException(String persistentClassName,
			Object identifier, String msg, Throwable ex) {
		super(msg, ex);
		this.persistentClass = persistentClassName;
		this.identifier = identifier;
	}

	/**
	 * @return Returns the identifier.
	 */
	public Object getIdentifier() {
		return identifier;
	}

	/**
	 * @param identifier
	 *            The identifier to set.
	 */
	public void setIdentifier(Object identifier) {
		this.identifier = identifier;
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