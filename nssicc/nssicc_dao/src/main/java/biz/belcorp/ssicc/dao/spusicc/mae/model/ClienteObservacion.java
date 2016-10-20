package biz.belcorp.ssicc.dao.spusicc.mae.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextsapaza
 *
 */
public class ClienteObservacion extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long oid;
	private Long oidMarca;
	private Long oidCliente;
	private Integer numeroObservacion;
	private String texto;
	
	/* INI SA PER-SiCC-2013-0586 */
	private String codigoUsuario;
	/* FIN SA PER-SiCC-2013-0586 */

	public ClienteObservacion() {
		
	}
	
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return Returns the numeroObservacion.
	 */
	public Integer getNumeroObservacion() {
		return numeroObservacion;
	}

	/**
	 * @param numeroObservacion The numeroObservacion to set.
	 */
	public void setNumeroObservacion(Integer numeroObservacion) {
		this.numeroObservacion = numeroObservacion;
	}

	/**
	 * @return Returns the oid.
	 */
	public Long getOid() {
		return oid;
	}

	/**
	 * @param oid The oid to set.
	 */
	public void setOid(Long oid) {
		this.oid = oid;
	}

	/**
	 * @return Returns the oidCliente.
	 */
	public Long getOidCliente() {
		return oidCliente;
	}

	/**
	 * @param oidCliente The oidCliente to set.
	 */
	public void setOidCliente(Long oidCliente) {
		this.oidCliente = oidCliente;
	}

	/**
	 * @return Returns the oidMarca.
	 */
	public Long getOidMarca() {
		return oidMarca;
	}

	/**
	 * @param oidMarca The oidMarca to set.
	 */
	public void setOidMarca(Long oidMarca) {
		this.oidMarca = oidMarca;
	}

	/**
	 * @return Returns the texto.
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * @param texto The texto to set.
	 */
	public void setTexto(String texto) {
		this.texto = texto;
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
