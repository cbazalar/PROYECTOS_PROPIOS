package biz.belcorp.ssicc.dao.spusicc.pej.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author Jesse James Rios Franco
 *
 */
public class ConsultoraNivel1 extends AuditableBaseObject implements Serializable{

	private static final long serialVersionUID = 1L;

	private int fila;
	
	private String descripcionError;
		
	private String codigoPais;
	
	private String codigoEtapa;
	
	private String codigoCliente;
	
	private String usuarioDigitacion;
	
	private Date fechaDigitacion;
	
	private String usuarioModificacion;
	
	private Date fechaModificacion;
	
	
	/**
	 * @return
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return
	 */
	public String getCodigoEtapa() {
		return codigoEtapa;
	}

	/**
	 * @param codigoEtapa
	 */
	public void setCodigoEtapa(String codigoEtapa) {
		this.codigoEtapa = codigoEtapa;
	}

	/**
	 * @return
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * @return
	 */
	public String getUsuarioDigitacion() {
		return usuarioDigitacion;
	}

	/**
	 * @param usuarioDigitacion
	 */
	public void setUsuarioDigitacion(String usuarioDigitacion) {
		this.usuarioDigitacion = usuarioDigitacion;
	}

	/**
	 * @return
	 */
	public Date getFechaDigitacion() {
		return fechaDigitacion;
	}

	/**
	 * @param fechaDigitacion
	 */
	public void setFechaDigitacion(Date fechaDigitacion) {
		this.fechaDigitacion = fechaDigitacion;
	}

	/**
	 * @return
	 */
	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}

	/**
	 * @param usuarioModificacion
	 */
	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	/**
	 * @return
	 */
	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	/**
	 * @param fechaModificacion
	 */
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	/**
	 * @return
	 */
	public int getFila() {
		return fila;
	}

	/**
	 * @param fila
	 */
	public void setFila(int fila) {
		this.fila = fila;
	}

	/**
	 * @return
	 */
	public String getDescripcionError() {
		return descripcionError;
	}

	/**
	 * @param descripcionError
	 */
	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#toString()
	 */
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#hashCode()
	 */
	public int hashCode() {
		return 0;
	}
}