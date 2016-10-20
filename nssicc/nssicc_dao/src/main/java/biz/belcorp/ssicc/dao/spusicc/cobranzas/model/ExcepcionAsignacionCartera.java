package biz.belcorp.ssicc.dao.spusicc.cobranzas.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author Jorge Florencio Arias
 *
 */
public class ExcepcionAsignacionCartera extends AuditableBaseObject implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String codigoPais;
	private String codigoEtapaDeuda;	
	private String codigoTipoExcepcion;
	private String numeroOrdenExcepcion;	
	private String valorNombrePrograma;
	private String valorMensajeExcepcion;
	private String indicadorActivo;
	private String observaciones;
	
	public ExcepcionAsignacionCartera() {
	
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
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the codigoEtapaDeuda
	 */
	public String getCodigoEtapaDeuda() {
		return codigoEtapaDeuda;
	}

	/**
	 * @param codigoEtapaDeuda the codigoEtapaDeuda to set
	 */
	public void setCodigoEtapaDeuda(String codigoEtapaDeuda) {
		this.codigoEtapaDeuda = codigoEtapaDeuda;
	}

	/**
	 * @return the codigoTipoExcepcion
	 */
	public String getCodigoTipoExcepcion() {
		return codigoTipoExcepcion;
	}

	/**
	 * @param codigoTipoExcepcion the codigoTipoExcepcion to set
	 */
	public void setCodigoTipoExcepcion(String codigoTipoExcepcion) {
		this.codigoTipoExcepcion = codigoTipoExcepcion;
	}

	/**
	 * @return the numeroOrdenExcepcion
	 */
	public String getNumeroOrdenExcepcion() {
		return numeroOrdenExcepcion;
	}

	/**
	 * @param numeroOrdenExcepcion the numeroOrdenExcepcion to set
	 */
	public void setNumeroOrdenExcepcion(String numeroOrdenExcepcion) {
		this.numeroOrdenExcepcion = numeroOrdenExcepcion;
	}

	/**
	 * @return the valorNombrePrograma
	 */
	public String getValorNombrePrograma() {
		return valorNombrePrograma;
	}

	/**
	 * @param valorNombrePrograma the valorNombrePrograma to set
	 */
	public void setValorNombrePrograma(String valorNombrePrograma) {
		this.valorNombrePrograma = valorNombrePrograma;
	}

	/**
	 * @return the valorMensajeExcepcion
	 */
	public String getValorMensajeExcepcion() {
		return valorMensajeExcepcion;
	}

	/**
	 * @param valorMensajeExcepcion the valorMensajeExcepcion to set
	 */
	public void setValorMensajeExcepcion(String valorMensajeExcepcion) {
		this.valorMensajeExcepcion = valorMensajeExcepcion;
	}

	/**
	 * @return the indicadorActivo
	 */
	public String getIndicadorActivo() {
		return indicadorActivo;
	}

	/**
	 * @param indicadorActivo the indicadorActivo to set
	 */
	public void setIndicadorActivo(String indicadorActivo) {
		this.indicadorActivo = indicadorActivo;
	}

	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	
}
