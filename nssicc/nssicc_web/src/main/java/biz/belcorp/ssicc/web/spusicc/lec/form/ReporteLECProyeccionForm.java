package biz.belcorp.ssicc.web.spusicc.lec.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteLECProyeccionForm extends BaseReporteForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String campanyaProceso;
	private String[] codigoRegion;
	private String tramo;
	private String codigoGrupoPago;
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 * 
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the campanyaProceso
	 */
	public String getCampanyaProceso() {
		return campanyaProceso;
	}
	/**
	 * @param campanyaProceso the campanyaProceso to set
	 * 
	 */
	public void setCampanyaProceso(String campanyaProceso) {
		this.campanyaProceso = campanyaProceso;
	}
	/**
	 * @return the codigoRegion
	 */
	public String[] getCodigoRegion() {
		return codigoRegion;
	}
	/**
	 * @param codigoRegion the codigoRegion to set
	 * 
	 */
	public void setCodigoRegion(String[] codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	/**
	 * @return the tramo
	 */
	public String getTramo() {
		return tramo;
	}
	/**
	 * @param tramo the tramo to set
	 * 
	 */
	public void setTramo(String tramo) {
		this.tramo = tramo;
	}
	/**
	 * @return the codigoGrupoPago
	 */
	public String getCodigoGrupoPago() {
		return codigoGrupoPago;
	}
	/**
	 * @param codigoGrupoPago the codigoGrupoPago to set
	 */
	public void setCodigoGrupoPago(String codigoGrupoPago) {
		this.codigoGrupoPago = codigoGrupoPago;
	}
}