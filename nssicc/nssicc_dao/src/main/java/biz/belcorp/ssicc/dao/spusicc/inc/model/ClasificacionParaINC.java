/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.inc.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author Sigcomt
 *
 */
public class ClasificacionParaINC extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = -2898556074038430672L;
	private Integer secuencial;
	private Integer valorGrupoCliente;
	private String  descripcion;
	private Integer oidDestinatario;
	private long oidPais;
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result
				+ ((oidDestinatario == null) ? 0 : oidDestinatario.hashCode());
		result = prime * result + (int) (oidPais ^ (oidPais >>> 32));
		result = prime * result
				+ ((secuencial == null) ? 0 : secuencial.hashCode());
		result = prime
				* result
				+ ((valorGrupoCliente == null) ? 0 : valorGrupoCliente
						.hashCode());
		return result;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClasificacionParaINC other = (ClasificacionParaINC) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (oidDestinatario == null) {
			if (other.oidDestinatario != null)
				return false;
		} else if (!oidDestinatario.equals(other.oidDestinatario))
			return false;
		if (oidPais != other.oidPais)
			return false;
		if (secuencial == null) {
			if (other.secuencial != null)
				return false;
		} else if (!secuencial.equals(other.secuencial))
			return false;
		if (valorGrupoCliente == null) {
			if (other.valorGrupoCliente != null)
				return false;
		} else if (!valorGrupoCliente.equals(other.valorGrupoCliente))
			return false;
		return true;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ClasificacionParaINC [secuencial=" + secuencial
				+ ", valorGrupoCliente=" + valorGrupoCliente + ", descripcion="
				+ descripcion + ", oidDestinatario=" + oidDestinatario
				+ ", oidPais=" + oidPais + "]";
	}
	/**
	 * @return the secuencial
	 */
	public Integer getSecuencial() {
		return secuencial;
	}
	/**
	 * @param secuencial the secuencial to set
	 */
	public void setSecuencial(Integer secuencial) {
		this.secuencial = secuencial;
	}
	/**
	 * @return the valorGrupoCliente
	 */
	public Integer getValorGrupoCliente() {
		return valorGrupoCliente;
	}
	/**
	 * @param valorGrupoCliente the valorGrupoCliente to set
	 */
	public void setValorGrupoCliente(Integer valorGrupoCliente) {
		this.valorGrupoCliente = valorGrupoCliente;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the oidDestinatario
	 */
	public Integer getOidDestinatario() {
		return oidDestinatario;
	}
	/**
	 * @param oidDestinatario the oidDestinatario to set
	 */
	public void setOidDestinatario(Integer oidDestinatario) {
		this.oidDestinatario = oidDestinatario;
	}

	/**
	 * @return the oidPais
	 */
	public long getOidPais() {
		return oidPais;
	}

	/**
	 * @param oidPais the oidPais to set
	 */
	public void setOidPais(long oidPais) {
		this.oidPais = oidPais;
	}
	
	
	
	

}
