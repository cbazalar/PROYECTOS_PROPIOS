/**
 * 
 */
package biz.belcorp.ssicc.dao.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextdoliva
 *
 */
public class ParametrosOperacionesReclamos extends AuditableBaseObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoOperacion;
	private String descripcionOperacion;
	private String indicadorCUVCambiaObligatorio;
	private String indicadorCUVDeseaObligatorio;
	private String indicadorValidacionCUVCambia;
	private String indicadorValidacionCUVDesea;
	private String popupCambia;
	private String popupDesea;
	private String indicadorValidarCentroServicio;
		
	/**
	 * @return the codigoOperacion
	 */
	public String getCodigoOperacion() {
		return codigoOperacion;
	}

	/**
	 * @param codigoOperacion the codigoOperacion to set
	 */
	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}

	/**
	 * @return the descripcionOperacion
	 */
	public String getDescripcionOperacion() {
		return descripcionOperacion;
	}

	/**
	 * @param descripcionOperacion the descripcionOperacion to set
	 */
	public void setDescripcionOperacion(String descripcionOperacion) {
		this.descripcionOperacion = descripcionOperacion;
	}

	/**
	 * @return the indicadorCUVCambiaObligatorio
	 */
	public String getIndicadorCUVCambiaObligatorio() {
		return indicadorCUVCambiaObligatorio;
	}

	/**
	 * @param indicadorCUVCambiaObligatorio the indicadorCUVCambiaObligatorio to set
	 */
	public void setIndicadorCUVCambiaObligatorio(
			String indicadorCUVCambiaObligatorio) {
		this.indicadorCUVCambiaObligatorio = indicadorCUVCambiaObligatorio;
	}

	/**
	 * @return the indicadorCUVDeseaObligatorio
	 */
	public String getIndicadorCUVDeseaObligatorio() {
		return indicadorCUVDeseaObligatorio;
	}

	/**
	 * @param indicadorCUVDeseaObligatorio the indicadorCUVDeseaObligatorio to set
	 */
	public void setIndicadorCUVDeseaObligatorio(String indicadorCUVDeseaObligatorio) {
		this.indicadorCUVDeseaObligatorio = indicadorCUVDeseaObligatorio;
	}

	/**
	 * @return the indicadorValidacionCUVCambia
	 */
	public String getIndicadorValidacionCUVCambia() {
		return indicadorValidacionCUVCambia;
	}

	/**
	 * @param indicadorValidacionCUVCambia the indicadorValidacionCUVCambia to set
	 */
	public void setIndicadorValidacionCUVCambia(String indicadorValidacionCUVCambia) {
		this.indicadorValidacionCUVCambia = indicadorValidacionCUVCambia;
	}

	/**
	 * @return the indicadorValidacionCUVDesea
	 */
	public String getIndicadorValidacionCUVDesea() {
		return indicadorValidacionCUVDesea;
	}

	/**
	 * @param indicadorValidacionCUVDesea the indicadorValidacionCUVDesea to set
	 */
	public void setIndicadorValidacionCUVDesea(String indicadorValidacionCUVDesea) {
		this.indicadorValidacionCUVDesea = indicadorValidacionCUVDesea;
	}

	/**
	 * @return the popupCambia
	 */
	public String getPopupCambia() {
		return popupCambia;
	}

	/**
	 * @param popupCambia the popupCambia to set
	 */
	public void setPopupCambia(String popupCambia) {
		this.popupCambia = popupCambia;
	}

	/**
	 * @return the popupDesea
	 */
	public String getPopupDesea() {
		return popupDesea;
	}

	/**
	 * @param popupDesea the popupDesea to set
	 */
	public void setPopupDesea(String popupDesea) {
		this.popupDesea = popupDesea;
	}

	/**
	 * @return the indicadorValidarCentroServicio
	 */
	public String getIndicadorValidarCentroServicio() {
		return indicadorValidarCentroServicio;
	}

	/**
	 * @param indicadorValidarCentroServicio the indicadorValidarCentroServicio to set
	 */
	public void setIndicadorValidarCentroServicio(
			String indicadorValidarCentroServicio) {
		this.indicadorValidarCentroServicio = indicadorValidarCentroServicio;
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
}
