/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author efernandezo
 * 
 */
public class InterfazCOMRecepcionarActualizaCodigoProveedor extends
		AuditableBaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8379357311540347007L;

	private String codProveedor;
	
	private String ruc;

	private String CodigoPais;

	/**
	 * @return
	 */
	public String getCodigoPais() {
		return CodigoPais;
	}

	/**
	 * @param codigoPais
	 */
	public void setCodigoPais(String codigoPais) {
		CodigoPais = codigoPais;
	}

	/**
	 * @return
	 */
	public String getCodProveedor() {
		return codProveedor;
	}

	/**
	 * @param codProveedor
	 */
	public void setCodProveedor(String codProveedor) {
		this.codProveedor = codProveedor;
	}

	/**
	 * @return
	 */
	public String getRuc() {
		return ruc;
	}

	/**
	 * @param ruc
	 */
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.model.BaseObject#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.model.BaseObject#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.model.BaseObject#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
