package biz.belcorp.ssicc.dao.spusicc.cobranzas.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author Jorge Florencio Arias
 *
 */
public class ExcepcionClasificacionCliente extends AuditableBaseObject implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String codigoPais;
	private String codigoEtapaDeuda;		
	private String oidTipoCliente;
	private String oidSubtipoCliente;
	private String oidTipoClasificacionCliente;
	private String oidClasificacionCliente;	
	private String indicadorActivo;
	private String observaciones;
	
	public ExcepcionClasificacionCliente() {
	
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
	 * @return the oidTipoCliente
	 */
	public String getOidTipoCliente() {
		return oidTipoCliente;
	}

	/**
	 * @param oidTipoCliente the oidTipoCliente to set
	 */
	public void setOidTipoCliente(String oidTipoCliente) {
		this.oidTipoCliente = oidTipoCliente;
	}

	/**
	 * @return the oidSubtipoCliente
	 */
	public String getOidSubtipoCliente() {
		return oidSubtipoCliente;
	}

	/**
	 * @param oidSubtipoCliente the oidSubtipoCliente to set
	 */
	public void setOidSubtipoCliente(String oidSubtipoCliente) {
		this.oidSubtipoCliente = oidSubtipoCliente;
	}

	/**
	 * @return the oidTipoClasificacionCliente
	 */
	public String getOidTipoClasificacionCliente() {
		return oidTipoClasificacionCliente;
	}

	/**
	 * @param oidTipoClasificacionCliente the oidTipoClasificacionCliente to set
	 */
	public void setOidTipoClasificacionCliente(String oidTipoClasificacionCliente) {
		this.oidTipoClasificacionCliente = oidTipoClasificacionCliente;
	}

	/**
	 * @return the oidClasificacionCliente
	 */
	public String getOidClasificacionCliente() {
		return oidClasificacionCliente;
	}

	/**
	 * @param oidClasificacionCliente the oidClasificacionCliente to set
	 */
	public void setOidClasificacionCliente(String oidClasificacionCliente) {
		this.oidClasificacionCliente = oidClasificacionCliente;
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

