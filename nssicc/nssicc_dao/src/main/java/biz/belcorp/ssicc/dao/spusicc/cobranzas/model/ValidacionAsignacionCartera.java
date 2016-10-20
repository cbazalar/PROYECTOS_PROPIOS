package biz.belcorp.ssicc.dao.spusicc.cobranzas.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author Jorge Florencio Arias
 *
 */
public class ValidacionAsignacionCartera extends AuditableBaseObject implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String codigoPais;
	private String codigoEtapaDeuda;	
	private String codigoTipoValidacion;
	private String numeroOrdenValidacion;	
	private String valorNombrePrograma;
	private String valorMensajeValidacion;
	private String indicadorActivo;	
	
	public ValidacionAsignacionCartera() {
	
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
	 * @return the codigoTipoValidacion
	 */
	public String getCodigoTipoValidacion() {
		return codigoTipoValidacion;
	}

	/**
	 * @param codigoTipoValidacion the codigoTipoValidacion to set
	 */
	public void setCodigoTipoValidacion(String codigoTipoValidacion) {
		this.codigoTipoValidacion = codigoTipoValidacion;
	}

	/**
	 * @return the numeroOrdenValidacion
	 */
	public String getNumeroOrdenValidacion() {
		return numeroOrdenValidacion;
	}

	/**
	 * @param numeroOrdenValidacion the numeroOrdenValidacion to set
	 */
	public void setNumeroOrdenValidacion(String numeroOrdenValidacion) {
		this.numeroOrdenValidacion = numeroOrdenValidacion;
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
	 * @return the valorMensajeValidacion
	 */
	public String getValorMensajeValidacion() {
		return valorMensajeValidacion;
	}

	/**
	 * @param valorMensajeValidacion the valorMensajeValidacion to set
	 */
	public void setValorMensajeValidacion(String valorMensajeValidacion) {
		this.valorMensajeValidacion = valorMensajeValidacion;
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


}
