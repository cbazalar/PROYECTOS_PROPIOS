package biz.belcorp.ssicc.web.framework.base.form;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;


/**
 * TODO Include class description here.
 * <p>
 * <a href="BaseSearchForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramírez </a>
 */
public class BaseEditForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 37844711792889788L;
	protected String codigoPais;
	
	protected boolean editable = true;

	protected boolean newRecord = true;
	    
	protected String pageSelected = "";
  
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

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

	/**
	 * @return the pageSelected
	 */
	public String getPageSelected() {
		return pageSelected;
	}

	/**
	 * @param pageSelected the pageSelected to set
	 */
	public void setPageSelected(String pageSelected) {
		this.pageSelected = pageSelected;
	}
	
	
	
}