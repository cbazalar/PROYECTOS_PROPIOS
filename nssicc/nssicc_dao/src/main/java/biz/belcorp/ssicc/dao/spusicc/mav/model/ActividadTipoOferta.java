package biz.belcorp.ssicc.dao.spusicc.mav.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author <a href="kgomez@sigcomt.com">Karina Gomez</a>
 *
 */
public class ActividadTipoOferta  extends AuditableBaseObject implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String oidTipoOfer;
	private String codigoPais;
	private String tipoActividad;
	private String actividad;
	private String tipoOferta;
	private String estado;
	private String descripcionTipoOferta;
	private String descripcionTipoActividad;
	private String descripcionActividad;
	private String  codigoTOferta;
	
	private String usuario;
	
	
	
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the descripcionTipoOferta
	 */
	public String getDescripcionTipoOferta() {
		return descripcionTipoOferta;
	}

	/**
	 * @param descripcionTipoOferta the descripcionTipoOferta to set
	 */
	public void setDescripcionTipoOferta(String descripcionTipoOferta) {
		this.descripcionTipoOferta = descripcionTipoOferta;
	}

	/**
	 * @return the descripcionTipoActividad
	 */
	public String getDescripcionTipoActividad() {
		return descripcionTipoActividad;
	}

	/**
	 * @param descripcionTipoActividad the descripcionTipoActividad to set
	 */
	public void setDescripcionTipoActividad(String descripcionTipoActividad) {
		this.descripcionTipoActividad = descripcionTipoActividad;
	}

	/**
	 * @return the oidTipoOfer
	 */
	public String getOidTipoOfer() {
		return oidTipoOfer;
	}

	/**
	 * @param oidTipoOfer the oidTipoOfer to set
	 */
	public void setOidTipoOfer(String oidTipoOfer) {
		this.oidTipoOfer = oidTipoOfer;
	}

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
	 * @return the tipoActividad
	 */
	public String getTipoActividad() {
		return tipoActividad;
	}

	/**
	 * @param tipoActividad the tipoActividad to set
	 */
	public void setTipoActividad(String tipoActividad) {
		this.tipoActividad = tipoActividad;
	}

	/**
	 * @return the actividad
	 */
	public String getActividad() {
		return actividad;
	}

	/**
	 * @param actividad the actividad to set
	 */
	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	/**
	 * @return the tipoOferta
	 */
	public String getTipoOferta() {
		return tipoOferta;
	}

	/**
	 * @param tipoOferta the tipoOferta to set
	 */
	public void setTipoOferta(String tipoOferta) {
		this.tipoOferta = tipoOferta;
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

	/**
	 * @return the descripcionActividad
	 */
	public String getDescripcionActividad() {
		return descripcionActividad;
	}

	/**
	 * @param descripcionActividad the descripcionActividad to set
	 */
	public void setDescripcionActividad(String descripcionActividad) {
		this.descripcionActividad = descripcionActividad;
	}

	/**
	 * @return the codigoTOferta
	 */
	public String getCodigoTOferta() {
		return codigoTOferta;
	}

	/**
	 * @param codigoTOferta the codigoTOferta to set
	 */
	public void setCodigoTOferta(String codigoTOferta) {
		this.codigoTOferta = codigoTOferta;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ActividadTipoOferta [codigoPais=" + codigoPais + ", oidTipoOfer=" + oidTipoOfer
				+ ", codigoPais=" + codigoPais
				+ ", tipoActividad=" + tipoActividad
				+ ", actividad=" + actividad
				+ ", tipoOferta=" + tipoOferta
				+ ", estado=" + estado
				+ ", descripcionTipoActividad=" + descripcionTipoActividad
				+ ", descripcionTipoOferta=" + descripcionTipoOferta
				+ ", descripcionActividad=" + descripcionActividad
				+ ", usuario=" + usuario
				+ ", codigoTOferta=" + codigoTOferta  + "]";
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
		ActividadTipoOferta other = (ActividadTipoOferta) obj;
		if (oidTipoOfer == null) {
			if (other.oidTipoOfer != null)
				return false;
		} else if (!oidTipoOfer.equals(other.oidTipoOfer))
			return false;
		if (codigoPais == null) {
			if (other.codigoPais != null)
				return false;
		} else if (!codigoPais.equals(other.codigoPais))
			return false;
		if (tipoActividad == null) {
			if (other.tipoActividad != null)
				return false;
		} else if (!tipoActividad.equals(other.tipoActividad))
			return false;
		if (actividad == null) {
			if (other.actividad != null)
				return false;
		} else if (!actividad.equals(other.actividad))
			return false;
		if (tipoOferta == null) {
			if (other.tipoOferta != null)
				return false;
		} else if (!tipoOferta.equals(other.tipoOferta))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (descripcionActividad == null) {
			if (other.descripcionActividad != null)
				return false;
		} else if (!descripcionActividad
				.equals(other.descripcionActividad))
			return false;
		if (codigoTOferta == null) {
			if (other.codigoTOferta != null)
				return false;
		} else if (!codigoTOferta
				.equals(other.codigoTOferta))
			return false;
		if (descripcionTipoActividad == null) {
			if (other.descripcionTipoActividad != null)
				return false;
		} else if (!descripcionTipoActividad
				.equals(other.descripcionTipoActividad))
			return false;
		if (descripcionTipoOferta == null) {
			if (other.descripcionTipoOferta != null)
				return false;
		} else if (!descripcionTipoOferta
				.equals(other.descripcionTipoOferta))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario
				.equals(other.usuario))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		final int prime = 31;
		int result = 1;
		result = prime * result + ((oidTipoOfer == null) ? 0 : oidTipoOfer.hashCode());
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime
				* result
				+ ((tipoActividad == null) ? 0
						: tipoActividad.hashCode());
		result = prime * result
				+ ((actividad == null) ? 0 : actividad.hashCode());
		result = prime * result
				+ ((tipoOferta == null) ? 0 : tipoOferta.hashCode());
		result = prime * result
				+ ((estado == null) ? 0 : estado.hashCode());
		result = prime * result
				+ ((descripcionActividad == null) ? 0 : descripcionActividad.hashCode());
		result = prime * result
				+ ((codigoTOferta == null) ? 0 : codigoTOferta.hashCode());
		result = prime * result
				+ ((descripcionTipoActividad == null) ? 0 : descripcionTipoActividad.hashCode());
		result = prime * result
				+ ((descripcionTipoOferta == null) ? 0 : descripcionTipoOferta.hashCode());
		result = prime * result
				+ ((usuario == null) ? 0 : usuario.hashCode());
		
		return result;
	}

}
