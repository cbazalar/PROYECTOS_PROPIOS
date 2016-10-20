/**
 * 
 */
package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * @author fochoa
 *
 */
public class ReporteFLXGestionarConsultoraForm extends BaseReporteForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4782578398365878164L;
	
	private String codigoPais;
	private String campanyaComunicacion;
	private String campanyaFacturacion;
	private String codigoCliente;
	private String codigoCalificacionComportamiento;
	private String codigoCalificacionExperiencia;
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
	 * @return the campanyaComunicacion
	 */
	public String getCampanyaComunicacion() {
		return campanyaComunicacion;
	}
	/**
	 * @param campanyaComunicacion the campanyaComunicacion to set
	 */
	public void setCampanyaComunicacion(String campanyaComunicacion) {
		this.campanyaComunicacion = campanyaComunicacion;
	}
	/**
	 * @return the campanyaFacturacion
	 */
	public String getCampanyaFacturacion() {
		return campanyaFacturacion;
	}
	/**
	 * @param campanyaFacturacion the campanyaFacturacion to set
	 */
	public void setCampanyaFacturacion(String campanyaFacturacion) {
		this.campanyaFacturacion = campanyaFacturacion;
	}
	/**
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}
	/**
	 * @param codigoCliente the codigoCliente to set
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	/**
	 * @return the codigoCalificacionComportamiento
	 */
	public String getCodigoCalificacionComportamiento() {
		return codigoCalificacionComportamiento;
	}
	/**
	 * @param codigoCalificacionComportamiento the codigoCalificacionComportamiento to set
	 */
	public void setCodigoCalificacionComportamiento(
			String codigoCalificacionComportamiento) {
		this.codigoCalificacionComportamiento = codigoCalificacionComportamiento;
	}
	/**
	 * @return the codigoCalificacionExperiencia
	 */
	public String getCodigoCalificacionExperiencia() {
		return codigoCalificacionExperiencia;
	}
	/**
	 * @param codigoCalificacionExperiencia the codigoCalificacionExperiencia to set
	 */
	public void setCodigoCalificacionExperiencia(
			String codigoCalificacionExperiencia) {
		this.codigoCalificacionExperiencia = codigoCalificacionExperiencia;
	}
}
