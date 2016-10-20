/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.mae.model;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author <a href="dtorres@sigcomt.com">Diego Torres</a>
 *
 */ 
public class ExcencionSobreFlete extends AuditableBaseObject {

	private String codExenSfle;
	private String codPais;
	private String codClasificacion;
	private String indicadorExcepcionFlete;
	private String codSubTipoCliente;
	private String codTipoCliente;
	private String codTipoClasificacion;
	

	
	
	public String getCodExenSfle() {
		return codExenSfle;
	}

	public void setCodExenSfle(String codExenSfle) {
		this.codExenSfle = codExenSfle;
	}

	/**
	 * @return the indicadorExcepcionFlete
	 */
	public String getIndicadorExcepcionFlete() {
		return indicadorExcepcionFlete;
	}

	/**
	 * @param indicadorExcepcionFlete the indicadorExcepcionFlete to set
	 */
	public void setIndicadorExcepcionFlete(String indicadorExcepcionFlete) {
		this.indicadorExcepcionFlete = indicadorExcepcionFlete;
	}
	
	

	public String getCodPais() {
		return codPais;
	}

	public void setCodPais(String codPais) {
		this.codPais = codPais;
	}

	public String getCodClasificacion() {
		return codClasificacion;
	}

	public void setCodClasificacion(String codClasificacion) {
		this.codClasificacion = codClasificacion;
	}

	public String getCodSubTipoCliente() {
		return codSubTipoCliente;
	}

	public void setCodSubTipoCliente(String codSubTipoCliente) {
		this.codSubTipoCliente = codSubTipoCliente;
	}

	public String getCodTipoCliente() {
		return codTipoCliente;
	}

	public void setCodTipoCliente(String codTipoCliente) {
		this.codTipoCliente = codTipoCliente;
	}

	public String getCodTipoClasificacion() {
		return codTipoClasificacion;
	}

	public void setCodTipoClasificacion(String codTipoClasificacion) {
		this.codTipoClasificacion = codTipoClasificacion;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ExcencionSobreFlete [codPais=" + codPais + ", codTipoCliente=" + codTipoCliente
				+ ", codSubTipoCliente=" + codSubTipoCliente
				+ ", codTipoClasificacion=" + codTipoClasificacion
				+ ", codClasificacion=" + codClasificacion
				+ ", indicadorExcepcionFlete=" + indicadorExcepcionFlete + "]";
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
		ExcencionSobreFlete other = (ExcencionSobreFlete) obj;
		if (indicadorExcepcionFlete == null) {
			if (other.indicadorExcepcionFlete != null)
				return false;
		} else if (!indicadorExcepcionFlete
				.equals(other.indicadorExcepcionFlete))
			return false;
		if (codClasificacion == null) {
			if (other.codClasificacion != null)
				return false;
		} else if (!codClasificacion.equals(other.codClasificacion))
			return false;
		if (codPais == null) {
			if (other.codPais != null)
				return false;
		} else if (!codPais.equals(other.codPais))
			return false;
		if (codSubTipoCliente == null) {
			if (other.codSubTipoCliente != null)
				return false;
		} else if (!codSubTipoCliente.equals(other.codSubTipoCliente))
			return false;
		if (codTipoClasificacion == null) {
			if (other.codTipoClasificacion != null)
				return false;
		} else if (!codTipoClasificacion.equals(other.codTipoClasificacion))
			return false;
		if (codTipoCliente == null) {
			if (other.codTipoCliente != null)
				return false;
		} else if (!codTipoCliente.equals(other.codTipoCliente))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((indicadorExcepcionFlete == null) ? 0
						: indicadorExcepcionFlete.hashCode());
		result = prime
				* result
				+ ((codClasificacion == null) ? 0 : codClasificacion.hashCode());
		result = prime * result + ((codPais == null) ? 0 : codPais.hashCode());
		result = prime
				* result
				+ ((codSubTipoCliente == null) ? 0 : codSubTipoCliente
						.hashCode());
		result = prime
				* result
				+ ((codTipoClasificacion == null) ? 0 : codTipoClasificacion
						.hashCode());
		result = prime * result
				+ ((codTipoCliente == null) ? 0 : codTipoCliente.hashCode());
		return result;
	}

}
