/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.reclamos.model;

import java.io.Serializable;
import java.sql.Timestamp;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author Danny Amaro
 *
 */
public class ExcepcionesValidaciones extends AuditableBaseObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6879093960808288462L;
	
	private Long codigo;
	
	private String codigoPais;
	private String tipoDocumento;
	private String validaciones;
	private String codigoOrigen;
	private String codigoRegion;
	private String codigoZona;
	private String codigoPeriodo;
	private String codigoCliente;
	
	private Timestamp fecha;
	private String indicador;
		
	private Timestamp fechaCreacion;
	private String usuarioCreacion;
	
	private Timestamp fechaModificacion;
	private String usuarioModificacion;

	private String descValid;

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
	 * @return the codigoOrigen
	 */
	public String getCodigoOrigen() {
		return codigoOrigen;
	}

	/**
	 * @param codigoOrigen the codigoOrigen to set
	 */
	public void setCodigoOrigen(String codigoOrigen) {
		this.codigoOrigen = codigoOrigen;
	}

	/**
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente the codigoCliente to set
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * @return the indicador
	 */
	public String getIndicador() {
		return indicador;
	}

	/**
	 * @param indicador the indicador to set
	 */
	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}

	/**
	 * @return the usuarioCreacion
	 */
	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	/**
	 * @param usuarioCreacion the usuarioCreacion to set
	 */
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	/**
	 * @return the usuarioModificacion
	 */
	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}

	/**
	 * @param usuarioModificacion the usuarioModificacion to set
	 */
	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	/**
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * @return the validaciones
	 */
	public String getValidaciones() {
		return validaciones;
	}

	/**
	 * @param validaciones the validaciones to set
	 */
	public void setValidaciones(String validaciones) {
		this.validaciones = validaciones;
	}

	
	
	/**
	 * @return the codigo
	 */
	public Long getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the fecha
	 */
	public Timestamp getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the fechaCreacion
	 */
	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * @return the fechaModificacion
	 */
	public Timestamp getFechaModificacion() {
		return fechaModificacion;
	}

	/**
	 * @param fechaModificacion the fechaModificacion to set
	 */
	public void setFechaModificacion(Timestamp fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	/**
	 * @return the descValid
	 */
	public String getDescValid() {
		return descValid;
	}
	
	/**
	 * @param descValid the descValid to set
	 */
	public void setDescValid(String descValid) {
		this.descValid = descValid;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 0;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result
				+ ((codigoCliente == null) ? 0 : codigoCliente.hashCode());
		result = prime * result
				+ ((codigoOrigen == null) ? 0 : codigoOrigen.hashCode());
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime * result
				+ ((codigoPeriodo == null) ? 0 : codigoPeriodo.hashCode());
		result = prime * result
				+ ((codigoRegion == null) ? 0 : codigoRegion.hashCode());
		result = prime * result
				+ ((codigoZona == null) ? 0 : codigoZona.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result
				+ ((fechaCreacion == null) ? 0 : fechaCreacion.hashCode());
		result = prime
				* result
				+ ((fechaModificacion == null) ? 0 : fechaModificacion
						.hashCode());
		result = prime * result
				+ ((indicador == null) ? 0 : indicador.hashCode());
		result = prime * result
				+ ((tipoDocumento == null) ? 0 : tipoDocumento.hashCode());		
		result = prime * result
				+ ((usuarioCreacion == null) ? 0 : usuarioCreacion.hashCode());
		result = prime
				* result
				+ ((usuarioModificacion == null) ? 0 : usuarioModificacion
						.hashCode());
		result = prime * result
				+ ((validaciones == null) ? 0 : validaciones.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}	
		if (!(obj instanceof ExcepcionesValidaciones)) {
			return false;
		}
		ExcepcionesValidaciones other = (ExcepcionesValidaciones) obj;
		if (codigo == null) {
			if (other.codigo != null) {
				return false;
			}
		} else if (!codigo.equals(other.codigo)) {
			return false;
		}
		if (codigoCliente == null) {
			if (other.codigoCliente != null) {
				return false;
			}
		} else if (!codigoCliente.equals(other.codigoCliente)) {
			return false;
		}
		if (codigoOrigen == null) {
			if (other.codigoOrigen != null) {
				return false;
			}
		} else if (!codigoOrigen.equals(other.codigoOrigen)) {
			return false;
		}
		if (codigoPais == null) {
			if (other.codigoPais != null) {
				return false;
			}
		} else if (!codigoPais.equals(other.codigoPais)) {
			return false;
		}
		if (codigoPeriodo == null) {
			if (other.codigoPeriodo != null) {
				return false;
			}
		} else if (!codigoPeriodo.equals(other.codigoPeriodo)) {
			return false;
		}
		if (codigoRegion == null) {
			if (other.codigoRegion != null) {
				return false;
			}
		} else if (!codigoRegion.equals(other.codigoRegion)) {
			return false;
		}
		if (codigoZona == null) {
			if (other.codigoZona != null) {
				return false;
			}
		} else if (!codigoZona.equals(other.codigoZona)) {
			return false;
		}
		if (fecha == null) {
			if (other.fecha != null) {
				return false;
			}
		} else if (!fecha.equals(other.fecha)) {
			return false;
		}
		if (fechaCreacion == null) {
			if (other.fechaCreacion != null) {
				return false;
			}
		} else if (!fechaCreacion.equals(other.fechaCreacion)) {
			return false;
		}
		if (fechaModificacion == null) {
			if (other.fechaModificacion != null) {
				return false;
			}
		} else if (!fechaModificacion.equals(other.fechaModificacion)) {
			return false;
		}
		if (indicador == null) {
			if (other.indicador != null) {
				return false;
			}
		} else if (!indicador.equals(other.indicador)) {
			return false;
		}
		if (tipoDocumento == null) {
			if (other.tipoDocumento != null) {
				return false;
			}
		} else if (!tipoDocumento.equals(other.tipoDocumento)) {
			return false;
		}	
		if (usuarioCreacion == null) {
			if (other.usuarioCreacion != null) {
				return false;
			}
		} else if (!usuarioCreacion.equals(other.usuarioCreacion)) {
			return false;
		}
		if (usuarioModificacion == null) {
			if (other.usuarioModificacion != null) {
				return false;
			}
		} else if (!usuarioModificacion.equals(other.usuarioModificacion)) {
			return false;
		}
		if (validaciones == null) {
			if (other.validaciones != null) {
				return false;
			}
		} else if (!validaciones.equals(other.validaciones)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ExcepcionesValidaciones [codigo=" + codigo + ", codigoCliente="
				+ codigoCliente + ", codigoOrigen=" + codigoOrigen
				+ ", codigoPais=" + codigoPais + ", codigoPeriodo="
				+ codigoPeriodo + ", codigoRegion=" + codigoRegion
				+ ", codigoZona=" + codigoZona + ", fecha=" + fecha
				+ ", fechaCreacion=" + fechaCreacion + ", fechaModificacion="
				+ fechaModificacion + ", indicador=" + indicador
				+ ", tipoDocumento=" + tipoDocumento
				+ ", usuarioCreacion=" + usuarioCreacion
				+ ", usuarioModificacion=" + usuarioModificacion
				+ ", validaciones=" + validaciones + "]";
	}
	
}
