/*
 * Created on 06-dic-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.BaseObject;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="NormaInterfazPK.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public class NormaInterfazPK extends BaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5604320483877546567L;
	private String codigoPais;
	private String codigo;

	public NormaInterfazPK(){
		super();
	}
	
	public NormaInterfazPK(String codigoPais, String codigo){
		super();
		this.codigoPais = codigoPais; 
		this.codigo = codigo;
	}
	
	
	/**
	 * @return Returns the codigo.
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo The codigo to set.
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/* 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	/* 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	/* 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

}
