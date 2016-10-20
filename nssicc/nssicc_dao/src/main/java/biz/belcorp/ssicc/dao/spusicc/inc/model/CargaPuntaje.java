package biz.belcorp.ssicc.dao.spusicc.inc.model;

import java.io.Serializable;

public class CargaPuntaje implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1595366501896355714L;
	private String oid;
	private String fechaHabilitacion;
	private String codigoUsuario;
	/**
	 * @return the oid
	 */
	public String getOid() {
		return oid;
	}
	/**
	 * @param oid the oid to set
	 */
	public void setOid(String oid) {
		this.oid = oid;
	}
	/**
	 * @return the fechaHabilitacion
	 */
	public String getFechaHabilitacion() {
		return fechaHabilitacion;
	}
	/**
	 * @param fechaHabilitacion the fechaHabilitacion to set
	 */
	public void setFechaHabilitacion(String fechaHabilitacion) {
		this.fechaHabilitacion = fechaHabilitacion;
	}
	/**
	 * @return the codigoUsuario
	 */
	public String getCodigoUsuario() {
		return codigoUsuario;
	}
	/**
	 * @param codigoUsuario the codigoUsuario to set
	 */
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

}
