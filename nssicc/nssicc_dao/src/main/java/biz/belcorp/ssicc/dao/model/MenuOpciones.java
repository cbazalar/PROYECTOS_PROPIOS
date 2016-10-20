package biz.belcorp.ssicc.dao.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class MenuOpciones extends AuditableBaseObject implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2289526331115826857L;
	String codigoOpciones;
	String codigoMenu;
	String desMenuOpciones;
	
	
	
	public String getCodigoMenu() {
		return codigoMenu;
	}

	public void setCodigoMenu(String codigoMenu) {
		this.codigoMenu = codigoMenu;
	}

	public String getCodigoOpciones() {
		return codigoOpciones;
	}

	public void setCodigoOpciones(String codigoOpciones) {
		this.codigoOpciones = codigoOpciones;
	}

	public boolean equals(Object object) {
        return true;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return 0;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "";
    }

	public String getDesMenuOpciones() {
		return desMenuOpciones;
	}

	public void setDesMenuOpciones(String desMenuOpciones) {
		this.desMenuOpciones = desMenuOpciones;
	}


}
