package biz.belcorp.ssicc.dao.spusicc.cobranzas.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author Jorge Florencio Arias
 *
 */
public class CobradorUnidadAdministrativa extends AuditableBaseObject implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String codigoPais;
	private String codigoEtapaDeuda;	
	private String codigoRegion;
	private String codigoZona;
	private String codigoCobrador;
	private String importePorcentajeAsignacion;
	private String indicadorActivo;
	private String observaciones;
	
	public CobradorUnidadAdministrativa() {
	
	}
	
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
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
	 * @return the codigoCobrador
	 */
	public String getCodigoCobrador() {
		return codigoCobrador;
	}

	/**
	 * @param codigoCobrador the codigoCobrador to set
	 */
	public void setCodigoCobrador(String codigoCobrador) {
		this.codigoCobrador = codigoCobrador;
	}

	/**
	 * @return the importePorcentajeAsignacion
	 */
	public String getImportePorcentajeAsignacion() {
		return importePorcentajeAsignacion;
	}

	/**
	 * @param importePorcentajeAsignacion the importePorcentajeAsignacion to set
	 */
	public void setImportePorcentajeAsignacion(String importePorcentajeAsignacion) {
		this.importePorcentajeAsignacion = importePorcentajeAsignacion;
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

	@Override
	public String toString() {
		return "CobradorUnidadAdministrativa [id=" + id + ", codigoPais="
				+ codigoPais + ", codigoEtapaDeuda=" + codigoEtapaDeuda
				+ ", codigoRegion=" + codigoRegion + ", codigoZona="
				+ codigoZona + ", codigoCobrador=" + codigoCobrador
				+ ", importePorcentajeAsignacion="
				+ importePorcentajeAsignacion + ", indicadorActivo="
				+ indicadorActivo + ", observaciones=" + observaciones + "]";
	}
}
