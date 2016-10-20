package org.sistema.framework.web.base.form;



/**
 * TODO Include class description here.
 * <p>
 * <a href="BaseSearchForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 */
public class BaseEditForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 37844711792889788L;
	
	protected boolean editable = true;
	protected boolean newRecord = true;
	
	

	/**
	 * @return the editable
	 */
	public boolean isEditable() {
		return editable;
	}

	/**
	 * @param editable the editable to set
	 */
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	/**
	 * @return the newRecord
	 */
	public boolean isNewRecord() {
		return newRecord;
	}

	/**
	 * @param newRecord the newRecord to set
	 */
	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}


	
}