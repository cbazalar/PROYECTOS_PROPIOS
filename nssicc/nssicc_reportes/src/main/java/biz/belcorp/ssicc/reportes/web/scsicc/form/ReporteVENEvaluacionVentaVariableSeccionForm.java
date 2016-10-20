package biz.belcorp.ssicc.reportes.web.scsicc.form;
import java.io.Serializable;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * 
 * @author RRG
 * 
 */
public class ReporteVENEvaluacionVentaVariableSeccionForm extends BaseReporteForm	implements Serializable{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 2922581860413594722L;

	private String codigoPais;

	private String codigoMarca;

	private String descripcionMarca;

	private String codigoCanal;

	private String descripcionCanal;

	private String codigoPeriodo;

	private String codigoRegion;

	private String descripcionRegion;

	private String codigoZona;

	private String descripcionZona;

	
	
	/**
	 * Valores por default
	 */
	public ReporteVENEvaluacionVentaVariableSeccionForm(){
	this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
	this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
	this.codigoPeriodo = null;
	this.codigoRegion  = null;
	this.codigoZona    = null;
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
	 * @return the codigoMarca
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}



	/**
	 * @param codigoMarca the codigoMarca to set
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}



	/**
	 * @return the descripcionMarca
	 */
	public String getDescripcionMarca() {
		return descripcionMarca;
	}



	/**
	 * @param descripcionMarca the descripcionMarca to set
	 */
	public void setDescripcionMarca(String descripcionMarca) {
		this.descripcionMarca = descripcionMarca;
	}



	/**
	 * @return the codigoCanal
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}



	/**
	 * @param codigoCanal the codigoCanal to set
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}



	/**
	 * @return the descripcionCanal
	 */
	public String getDescripcionCanal() {
		return descripcionCanal;
	}



	/**
	 * @param descripcionCanal the descripcionCanal to set
	 */
	public void setDescripcionCanal(String descripcionCanal) {
		this.descripcionCanal = descripcionCanal;
	}



	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}



	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
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
	 * @return the descripcionRegion
	 */
	public String getDescripcionRegion() {
		return descripcionRegion;
	}



	/**
	 * @param descripcionRegion the descripcionRegion to set
	 */
	public void setDescripcionRegion(String descripcionRegion) {
		this.descripcionRegion = descripcionRegion;
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
	 * @return the descripcionZona
	 */
	public String getDescripcionZona() {
		return descripcionZona;
	}



	/**
	 * @param descripcionZona the descripcionZona to set
	 */
	public void setDescripcionZona(String descripcionZona) {
		this.descripcionZona = descripcionZona;
	}
	
	


	
}


