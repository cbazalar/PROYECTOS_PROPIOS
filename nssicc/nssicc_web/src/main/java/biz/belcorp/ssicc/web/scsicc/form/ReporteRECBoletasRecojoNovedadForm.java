package biz.belcorp.ssicc.web.scsicc.form;

import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * The Class ReporteRECBoletasRecojoNovedadForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 07/11/2014
 */
public class ReporteRECBoletasRecojoNovedadForm extends BaseReporteForm {
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	
	private String codigoPeriodo;
	
	private Date fechaProceso;
	
	private String[] regionList;

	private String[] zonaList;
	
	private String codigoMotivo;
	
	private String codigoResultado;	


	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	public String[] getRegionList() {
		return regionList;
	}

	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}

	public String[] getZonaList() {
		return zonaList;
	}

	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}

	public Date getFechaProceso() {
		return fechaProceso;
	}

	public void setFechaProceso(Date fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	/**
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo The codigoPeriodo to set.
	 * @struts.validator type = "required"
	 * @struts.validator type="mask"
	 * @struts.validator-var name="mask" value="${campaign}"
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	public String getCodigoMotivo() {
		return codigoMotivo;
	}

	public void setCodigoMotivo(String codigoMotivo) {
		this.codigoMotivo = codigoMotivo;
	}

	public String getCodigoResultado() {
		return codigoResultado;
	}

	public void setCodigoResultado(String codigoResultado) {
		this.codigoResultado = codigoResultado;
	}

}
