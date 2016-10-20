package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * 
 * @author RRG
 * 
 */

public class ReporteEDUCronogramaDictadoForm extends BaseReporteForm implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -884191433602231487L;
	private String codigoPais;
	private String codigoEmpresa;
	private String codigoRegion;
	private String campanhaProceso;
	private String codigoEjecutiva;
	private String nombreEjecutiva;
	private String codigoZona;
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
	 * @return the codigoEmpresa
	 */
	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}
	/**
	 * @param codigoEmpresa the codigoEmpresa to set
	 */
	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
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
	 * @return the campanhaProceso
	 */
	public String getCampanhaProceso() {
		return campanhaProceso;
	}
	/**
	 * @param campanhaProceso the campanhaProceso to set
	 */
	public void setCampanhaProceso(String campanhaProceso) {
		this.campanhaProceso = campanhaProceso;
	}
	/**
	 * @return the codigoEjecutiva
	 */
	public String getCodigoEjecutiva() {
		return codigoEjecutiva;
	}
	/**
	 * @param codigoEjecutiva the codigoEjecutiva to set
	 */
	public void setCodigoEjecutiva(String codigoEjecutiva) {
		this.codigoEjecutiva = codigoEjecutiva;
	}
	/**
	 * @return the nombreEjecutiva
	 */
	public String getNombreEjecutiva() {
		return nombreEjecutiva;
	}
	/**
	 * @param nombreEjecutiva the nombreEjecutiva to set
	 */
	public void setNombreEjecutiva(String nombreEjecutiva) {
		this.nombreEjecutiva = nombreEjecutiva;
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
	
	

}
