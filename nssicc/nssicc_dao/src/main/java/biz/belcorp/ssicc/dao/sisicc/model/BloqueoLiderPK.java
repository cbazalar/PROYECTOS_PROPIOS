/*
 * Created on 18-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.BaseObject;



/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="BloqueoLiderPK.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public class BloqueoLiderPK extends BaseObject implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1842580348769111746L;
	private String codigo;
	private String codigoPais;
		
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

	/**
	 * Constructor por defecto
	 */
	public BloqueoLiderPK(){
		super();
	}
	
	/**
	 * @param codigoPais
	 * @param codigo
	 */
	public BloqueoLiderPK(String codigoPais, String codigo){
		super();
		this.codigoPais = codigoPais;
		this.codigo = codigo;
	}
	/* 
	 * @see biz.belcorp.ssicc.model.BaseObject#toString()
	 */
	public String toString() {
        return new ToStringBuilder(this)
        	.append("codigoPais", this.codigoPais)
        	.append("codigo", this.codigo).toString();
	}

	/* 
	 * @see biz.belcorp.ssicc.model.BaseObject#equals(java.lang.Object)
	 */
	public boolean equals(Object object) {
		if(!(object instanceof BloqueoLiderPK)){
			return false;
		}
		BloqueoLiderPK rhs = (BloqueoLiderPK)object;
		return new EqualsBuilder()
			.append(this.codigoPais, rhs.codigoPais)
			.append(this.codigo, rhs.codigo).isEquals();
	}

	/* 
	 * @see biz.belcorp.ssicc.model.BaseObject#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(527242603, -124015047)
			.append(this.codigoPais)
			.append(this.codigo).toHashCode();
	}

}
