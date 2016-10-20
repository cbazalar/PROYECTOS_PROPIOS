/**
 * 
 */
package biz.belcorp.ssicc.dao.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * TODO Include class description here.
 * <p>
 * <a href="UsuarioBloqueo.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto Jaimes </a>
 */
public class UsuarioBloqueo extends AuditableBaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7157460177180347701L;

	private String codigoPais;
	
    private String codigoUsuarioBloqueo;

    private String codigoTipoBloqueo;

    private String codigoAccion;

    private String estado;
    
    private String nombreTipoBloqueo;
    
    private String nombreAccion;
    
    private long correlativo;
    
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		UsuarioBloqueo other = (UsuarioBloqueo) obj;
		if (this.codigoAccion == null) {
			if (other.codigoAccion != null) {
				return false;
			}
		} else if (!this.codigoAccion.equals(other.codigoAccion)) {
			return false;
		}
		if (this.codigoPais == null) {
			if (other.codigoPais != null) {
				return false;
			}
		} else if (!this.codigoPais.equals(other.codigoPais)) {
			return false;
		}
		if (this.codigoTipoBloqueo == null) {
			if (other.codigoTipoBloqueo != null) {
				return false;
			}
		} else if (!this.codigoTipoBloqueo.equals(other.codigoTipoBloqueo)) {
			return false;
		}
		if (this.codigoUsuarioBloqueo == null) {
			if (other.codigoUsuarioBloqueo != null) {
				return false;
			}
		} else if (!this.codigoUsuarioBloqueo.equals(other.codigoUsuarioBloqueo)) {
			return false;
		}
		if (this.correlativo != other.correlativo) {
			return false;
		}
		if (this.estado == null) {
			if (other.estado != null) {
				return false;
			}
		} else if (!this.estado.equals(other.estado)) {
			return false;
		}
		if (this.nombreAccion == null) {
			if (other.nombreAccion != null) {
				return false;
			}
		} else if (!this.nombreAccion.equals(other.nombreAccion)) {
			return false;
		}
		if (this.nombreTipoBloqueo == null) {
			if (other.nombreTipoBloqueo != null) {
				return false;
			}
		} else if (!this.nombreTipoBloqueo.equals(other.nombreTipoBloqueo)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the codigoAccion
	 */
	public String getCodigoAccion() {
		return this.codigoAccion;
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return this.codigoPais;
	}

	/**
	 * @return the codigoTipoBloqueo
	 */
	public String getCodigoTipoBloqueo() {
		return this.codigoTipoBloqueo;
	}

	/**
	 * @return the codigoUsuarioBloqueo
	 */
	public String getCodigoUsuarioBloqueo() {
		return this.codigoUsuarioBloqueo;
	}

	/**
	 * @return the correlativo
	 */
	public long getCorrelativo() {
		return this.correlativo;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return this.estado;
	}

	/**
	 * @return the nombreAccion
	 */
	public String getNombreAccion() {
		return this.nombreAccion;
	}

	/**
	 * @return the nombreTipoBloqueo
	 */
	public String getNombreTipoBloqueo() {
		return this.nombreTipoBloqueo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 13;
		result = prime * result
				+ ((this.codigoAccion == null) ? 0 : this.codigoAccion.hashCode());
		result = prime * result
				+ ((this.codigoPais == null) ? 0 : this.codigoPais.hashCode());
		result = prime
				* result
				+ ((this.codigoTipoBloqueo == null) ? 0 : this.codigoTipoBloqueo
						.hashCode());
		result = prime * result
				+ ((this.codigoUsuarioBloqueo == null) ? 0 : this.codigoUsuarioBloqueo.hashCode());
		result = prime * result + (int) (this.correlativo ^ (this.correlativo >>> 32));
		result = prime * result + ((this.estado == null) ? 0 : this.estado.hashCode());
		result = prime * result
				+ ((this.nombreAccion == null) ? 0 : this.nombreAccion.hashCode());
		result = prime
				* result
				+ ((this.nombreTipoBloqueo == null) ? 0 : this.nombreTipoBloqueo
						.hashCode());
		return result;
	}

	/**
	 * @param codigoAccion the codigoAccion to set
	 */
	public void setCodigoAccion(String codigoAccion) {
		this.codigoAccion = codigoAccion;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @param codigoTipoBloqueo the codigoTipoBloqueo to set
	 */
	public void setCodigoTipoBloqueo(String codigoTipoBloqueo) {
		this.codigoTipoBloqueo = codigoTipoBloqueo;
	}

	/**
	 * @param codigoUsuario the codigoUsuario to set
	 */
	public void setCodigoUsuarioBloqueo(String codigoUsuarioBloqueo) {
		this.codigoUsuarioBloqueo = codigoUsuarioBloqueo;
	}

	/**
	 * @param correlativo the correlativo to set
	 */
	public void setCorrelativo(long correlativo) {
		this.correlativo = correlativo;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @param nombreAccion the nombreAccion to set
	 */
	public void setNombreAccion(String nombreAccion) {
		this.nombreAccion = nombreAccion;
	}

	/**
	 * @param nombreTipoBloqueo the nombreTipoBloqueo to set
	 */
	public void setNombreTipoBloqueo(String nombreTipoBloqueo) {
		this.nombreTipoBloqueo = nombreTipoBloqueo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UsuarioBloqueo [codigoAccion=" + this.codigoAccion + ", codigoPais="
				+ this.codigoPais + ", codigoTipoBloqueo=" + this.codigoTipoBloqueo
				+ ", codigoUsuarioBloqueo=" + this.codigoUsuarioBloqueo + ", correlativo="
				+ this.correlativo + ", estado=" + this.estado + ", nombreAccion="
				+ this.nombreAccion + ", nombreTipoBloqueo=" + this.nombreTipoBloqueo
				+ "]";
	}

}
