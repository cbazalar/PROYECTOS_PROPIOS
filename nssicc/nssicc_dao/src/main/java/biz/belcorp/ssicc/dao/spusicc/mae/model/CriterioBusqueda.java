package biz.belcorp.ssicc.dao.spusicc.mae.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;


/**
 * @author <a href="kgomez@sigcomt.com">Karina Gomez</a>
 *
 */
public class CriterioBusqueda extends AuditableBaseObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String oidCriterioBus;
	private String atributo1;
	private String atributo2;
	private String estado;
	private String codigoPais;
	
	
	
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
	 * @return the oidCriterioBus
	 */
	public String getOidCriterioBus() {
		return oidCriterioBus;
	}

	/**
	 * @param oidCriterioBus the oidCriterioBus to set
	 */
	public void setOidCriterioBus(String oidCriterioBus) {
		this.oidCriterioBus = oidCriterioBus;
	}

	/**
	 * @return the atributo1
	 */
	public String getAtributo1() {
		return atributo1;
	}

	/**
	 * @param atributo1 the atributo1 to set
	 */
	public void setAtributo1(String atributo1) {
		this.atributo1 = atributo1;
	}

	/**
	 * @return the atributo2
	 */
	public String getAtributo2() {
		return atributo2;
	}

	/**
	 * @param atributo2 the atributo2 to set
	 */
	public void setAtributo2(String atributo2) {
		this.atributo2 = atributo2;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "CriterioBusqueda [codigoPais=" + codigoPais + ", oidCriterioBus=" + oidCriterioBus
				+ ", atributo1=" + atributo1
				+ ", atributo2=" + atributo2  
				+ ", estado=" + estado  + "]";
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CriterioBusqueda other = (CriterioBusqueda) obj;
		if (oidCriterioBus  == null) {
			if (other.oidCriterioBus != null)
				return false;
		} else if (!oidCriterioBus.equals(other.oidCriterioBus))
			return false;
		if (codigoPais == null) {
			if (other.codigoPais != null)
				return false;
		} else if (!codigoPais.equals(other.codigoPais))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (atributo1 == null) {
			if (other.atributo1 != null)
				return false;
		} else if (!atributo1.equals(other.atributo1))
			return false;
		if (atributo2 == null) {
			if (other.atributo2 != null)
				return false;
		} else if (!atributo2.equals(other.atributo2))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		final int prime = 31;
		int result = 1;
		result = prime * result + ((oidCriterioBus == null) ? 0 : oidCriterioBus.hashCode());
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime
				* result
				+ ((atributo1 == null) ? 0
						: atributo1.hashCode());
		result = prime * result
				+ ((atributo2 == null) ? 0 : atributo2.hashCode());
		result = prime * result
				+ ((estado  == null) ? 0 : estado.hashCode());
		return result;

	}

}
