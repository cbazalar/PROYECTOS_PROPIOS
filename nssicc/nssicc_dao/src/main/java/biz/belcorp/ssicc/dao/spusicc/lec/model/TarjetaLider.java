/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.lec.model;

import java.util.Date;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author <a href="dtorres@sigcomt.com">Diego Torres</a>
 *
 */ 
public class TarjetaLider extends AuditableBaseObject {
	
	private String codigoLider;
	private String codigoTarjeta;
	private String codigoEstado;
	
	private String campanaCreacion;
	private String campanaEnvio;
	private String campanaBloqueo;
	private String usuarioEnvio;
	private Date fechaEnvio;
	private String usuarioBloqueo;
	
	private Date fechaBloqueo;
	private String usuarioCreacion;
	private Date fechaCreacion;
	
	private String usuarioModificacion;
	private Date fechaModificacion;
	private Integer indicarActivo;
	
	
	/**
	 * @return the codigoTarjeta
	 */
	public String getCodigoTarjeta() {
		return codigoTarjeta;
	}
	/**
	 * @param codigoTarjeta the codigoTarjeta to set
	 */
	public void setCodigoTarjeta(String codigoTarjeta) {
		this.codigoTarjeta = codigoTarjeta;
	}
	/**
	 * @return the codigoLider
	 */
	public String getCodigoLider() {
		return codigoLider;
	}
	/**
	 * @param codigoLider the codigoLider to set
	 */
	public void setCodigoLider(String codigoLider) {
		this.codigoLider = codigoLider;
	}
	/**
	 * @return the codigoEstado
	 */
	public String getCodigoEstado() {
		return codigoEstado;
	}
	/**
	 * @param codigoEstado the codigoEstado to set
	 */
	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	/**
	 * @return the campanaCreacion
	 */
	public String getCampanaCreacion() {
		return campanaCreacion;
	}
	/**
	 * @param campanaCreacion the campanaCreacion to set
	 */
	public void setCampanaCreacion(String campanaCreacion) {
		this.campanaCreacion = campanaCreacion;
	}
	/**
	 * @return the campanaEnvio
	 */
	public String getCampanaEnvio() {
		return campanaEnvio;
	}
	/**
	 * @param campanaEnvio the campanaEnvio to set
	 */
	public void setCampanaEnvio(String campanaEnvio) {
		this.campanaEnvio = campanaEnvio;
	}
	/**
	 * @return the campanaBloqueo
	 */
	public String getCampanaBloqueo() {
		return campanaBloqueo;
	}
	/**
	 * @param campanaBloqueo the campanaBloqueo to set
	 */
	public void setCampanaBloqueo(String campanaBloqueo) {
		this.campanaBloqueo = campanaBloqueo;
	}
	/**
	 * @return the usarioEnvio
	 */
	public String getUsuarioEnvio() {
		return usuarioEnvio;
	}
	/**
	 * @param usarioEnvio the usarioEnvio to set
	 */
	public void setUsuarioEnvio(String usuarioEnvio) {
		this.usuarioEnvio = usuarioEnvio;
	}
	/**
	 * @return the fechaEnvio
	 */
	public Date getFechaEnvio() {
		return fechaEnvio;
	}
	/**
	 * @param fechaEnvio the fechaEnvio to set
	 */
	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}
	/**
	 * @return the usuarioBloqueo
	 */
	public String getUsuarioBloqueo() {
		return usuarioBloqueo;
	}
	/**
	 * @param usuarioBloqueo the usuarioBloqueo to set
	 */
	public void setUsuarioBloqueo(String usuarioBloqueo) {
		this.usuarioBloqueo = usuarioBloqueo;
	}
	/**
	 * @return the fechaBloqueo
	 */
	public Date getFechaBloqueo() {
		return fechaBloqueo;
	}
	/**
	 * @param fechaBloqueo the fechaBloqueo to set
	 */
	public void setFechaBloqueo(Date fechaBloqueo) {
		this.fechaBloqueo = fechaBloqueo;
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
	 * @return the fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
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
	 * @return the fechaModificacion
	 */
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	/**
	 * @param fechaModificacion the fechaModificacion to set
	 */
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	/**
	 * @return the indicarActivo
	 */
	public Integer getIndicarActivo() {
		return indicarActivo;
	}
	/**
	 * @param indicarActivo the indicarActivo to set
	 */
	public void setIndicarActivo(Integer indicarActivo) {
		this.indicarActivo = indicarActivo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TarjetaLider [codigoTarjeta=" + codigoTarjeta + ", codigoLider="
				+ codigoLider + ", codigoEstado="
				+ codigoEstado + ", campanaCreacion="
				+ campanaCreacion + ", campanaEnvio="
				+ campanaEnvio + ", =campanaBloqueo"
				+ campanaBloqueo + ", =usuarioEnvio"
				+ usuarioEnvio + ", =fechaEnvio"
				+ fechaEnvio + ", =usuarioBloqueo"
				+ usuarioBloqueo + ", =fechaBloqueo"
				+ fechaBloqueo + ", =usuarioCreacion"
				+ usuarioCreacion + ", =fechaCreacion"
				+ fechaCreacion + ", =usuarioModificacion"
				+ usuarioModificacion + ", =fechaModificacion"
				+ fechaModificacion + ", =usuarioModificacion"
				+ usuarioModificacion + ", =fechaModificacion"
				+ fechaModificacion + ", =indicarActivo"
				+ indicarActivo + "]";
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
		TarjetaLider other = (TarjetaLider) obj;
		if (codigoTarjeta == null) {
			if (other.codigoTarjeta != null)
				return false;
		}
		if (codigoLider == null) {
			if (other.codigoLider != null)
				return false;
		}
		if (codigoEstado == null) {
			if (other.codigoEstado != null)
				return false;
		}
		
		if (campanaCreacion == null) {
			if (other.campanaCreacion != null)
				return false;
		}
		if (campanaEnvio == null) {
			if (other.campanaEnvio != null)
				return false;
		}
		if (campanaBloqueo == null) {
			if (other.campanaBloqueo != null)
				return false;
		}
		if (usuarioEnvio == null) {
			if (other.usuarioEnvio != null)
				return false;
		}
		if (fechaEnvio == null) {
			if (other.fechaEnvio != null)
				return false;
		}
		if (usuarioBloqueo == null) {
			if (other.usuarioBloqueo != null)
				return false;
		}
		if (fechaBloqueo == null) {
			if (other.fechaBloqueo != null)
				return false;
		}
		if (usuarioCreacion == null) {
			if (other.usuarioCreacion != null)
				return false;
		}
		if (fechaCreacion == null) {
			if (other.fechaCreacion != null)
				return false;
		}
		if (usuarioModificacion == null) {
			if (other.usuarioModificacion != null)
				return false;
		}
		if (fechaModificacion == null) {
			if (other.fechaModificacion != null)
				return false;
		}
		if (indicarActivo == null) {
			if (other.indicarActivo != null)
				return false;
		}
		
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
				+ ((codigoTarjeta == null) ? 0
						: codigoTarjeta.hashCode());
		result = prime * result
				+ ((codigoLider == null) ? 0 : codigoLider.hashCode());	
		result = prime * result
				+ ((codigoEstado == null) ? 0 : codigoEstado.hashCode());	
		result = prime * result
				+ ((campanaCreacion == null) ? 0 : campanaCreacion.hashCode());		
		result = prime * result
				+ ((campanaEnvio == null) ? 0 : campanaEnvio.hashCode());
		result = prime * result
				+ ((campanaBloqueo  == null) ? 0 : campanaBloqueo.hashCode());
		result = prime * result
				+ ((usuarioEnvio == null) ? 0 : usuarioEnvio.hashCode());
		result = prime * result
				+ ((fechaEnvio == null) ? 0 : fechaEnvio.hashCode());
		result = prime * result
				+ ((usuarioBloqueo == null) ? 0 : usuarioBloqueo.hashCode());
		result = prime * result
				+ ((fechaBloqueo == null) ? 0 : fechaBloqueo.hashCode());
		result = prime * result
				+ ((usuarioBloqueo == null) ? 0 : usuarioBloqueo.hashCode());
		result = prime * result
				+ ((fechaBloqueo == null) ? 0 : fechaBloqueo.hashCode());
		result = prime * result
				+ ((usuarioCreacion == null) ? 0 : usuarioCreacion.hashCode());
		result = prime * result
				+ ((fechaCreacion == null) ? 0 : fechaCreacion.hashCode());
		result = prime * result
				+ ((usuarioModificacion == null) ? 0 : usuarioModificacion.hashCode());
		result = prime * result
				+ ((fechaModificacion == null) ? 0 : fechaModificacion.hashCode());
		result = prime * result
				+ ((indicarActivo == null) ? 0 : indicarActivo.hashCode());
		
		return result;
	}

}
