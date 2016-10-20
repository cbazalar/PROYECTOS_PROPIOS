package org.sistema.framework.web.base.form;



/**
 * TODO Include class description here.
 * <p>
 * <a href="BaseSearchForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 */
public class BaseSearchForm extends BaseForm {

	private static final long serialVersionUID = 4614846556425574028L;
	protected String[] selectedItems = {};
  

	public String[] getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(String[] selectedItems) {
		this.selectedItems = selectedItems;
	}
	
	
	
}