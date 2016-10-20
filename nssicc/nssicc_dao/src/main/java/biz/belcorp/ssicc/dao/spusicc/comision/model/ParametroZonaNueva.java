package biz.belcorp.ssicc.dao.spusicc.comision.model;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author <a href="mailto:ttataje@csigcomt.com">Telly Tataje Tirado</a>
 *
 */
public class ParametroZonaNueva extends AuditableBaseObject {
		
	private String codigoPais;
	private Double importeNeto;
	private Double porcentajeRecuperacion;
	private Double porcentajeActividad;
	private Double importeBono;
	
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
	 * @return the importeNeto
	 */
	public Double getImporteNeto() {
		return importeNeto;
	}

	/**
	 * @param importeNeto the importeNeto to set
	 */
	public void setImporteNeto(Double importeNeto) {
		this.importeNeto = importeNeto;
	}

	/**
	 * @return the porcentajeRecuperacion
	 */
	public Double getPorcentajeRecuperacion() {
		return porcentajeRecuperacion;
	}

	/**
	 * @param porcentajeRecuperacion the porcentajeRecuperacion to set
	 */
	public void setPorcentajeRecuperacion(Double porcentajeRecuperacion) {
		this.porcentajeRecuperacion = porcentajeRecuperacion;
	}

	/**
	 * @return the porcentajeActividad
	 */
	public Double getPorcentajeActividad() {
		return porcentajeActividad;
	}

	/**
	 * @param porcentajeActividad the porcentajeActividad to set
	 */
	public void setPorcentajeActividad(Double porcentajeActividad) {
		this.porcentajeActividad = porcentajeActividad;
	}

	/**
	 * @return the importeBono
	 */
	public Double getImporteBono() {
		return importeBono;
	}

	/**
	 * @param importeBono the importeBono to set
	 */
	public void setImporteBono(Double importeBono) {
		this.importeBono = importeBono;
	}

}