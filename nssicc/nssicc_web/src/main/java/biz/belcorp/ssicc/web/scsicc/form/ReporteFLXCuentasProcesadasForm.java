package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * 
 * @author RRG
 * 
 */
public class ReporteFLXCuentasProcesadasForm extends BaseReporteForm implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2975857939130942363L;
	private String codigoPais;
	private String codigoCampana;
	private String[] regionList;
	private String[] zonaList;
	private String[] seccionList;
	private String descripcionRegionList;
	private String descripcionZonaList;
	private String descripcionSeccionList;
	private String fechaInicio;
	private String fechaFin;
	private Date fechaInicioD;
	private Date fechaFinD;
	private String descripcionTipoCuentaFlexipagoList;
	private String tipoCuentaFlexipagoList;
	private String indicadorContrato;

	public ReporteFLXCuentasProcesadasForm() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));

		if (StringUtils.isEmpty(this.codigoCampana))
			this.codigoCampana = periodo;

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
	 * @return the codigoCampana
	 */
	public String getCodigoCampana() {
		return codigoCampana;
	}

	/**
	 * @param codigoCampana the codigoCampana to set
	 */
	public void setCodigoCampana(String codigoCampana) {
		this.codigoCampana = codigoCampana;
	}

	/**
	 * @return the regionList
	 */
	public String[] getRegionList() {
		return regionList;
	}

	/**
	 * @param regionList the regionList to set
	 */
	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}

	/**
	 * @return the zonaList
	 */
	public String[] getZonaList() {
		return zonaList;
	}

	/**
	 * @param zonaList the zonaList to set
	 */
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}

	/**
	 * @return the seccionList
	 */
	public String[] getSeccionList() {
		return seccionList;
	}

	/**
	 * @param seccionList the seccionList to set
	 */
	public void setSeccionList(String[] seccionList) {
		this.seccionList = seccionList;
	}

	/**
	 * @return the descripcionRegionList
	 */
	public String getDescripcionRegionList() {
		return descripcionRegionList;
	}

	/**
	 * @param descripcionRegionList the descripcionRegionList to set
	 */
	public void setDescripcionRegionList(String descripcionRegionList) {
		this.descripcionRegionList = descripcionRegionList;
	}

	/**
	 * @return the descripcionZonaList
	 */
	public String getDescripcionZonaList() {
		return descripcionZonaList;
	}

	/**
	 * @param descripcionZonaList the descripcionZonaList to set
	 */
	public void setDescripcionZonaList(String descripcionZonaList) {
		this.descripcionZonaList = descripcionZonaList;
	}

	/**
	 * @return the descripcionSeccionList
	 */
	public String getDescripcionSeccionList() {
		return descripcionSeccionList;
	}

	/**
	 * @param descripcionSeccionList the descripcionSeccionList to set
	 */
	public void setDescripcionSeccionList(String descripcionSeccionList) {
		this.descripcionSeccionList = descripcionSeccionList;
	}

	/**
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechaFin
	 */
	public String getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * @return the descripcionTipoCuentaFlexipagoList
	 */
	public String getDescripcionTipoCuentaFlexipagoList() {
		return descripcionTipoCuentaFlexipagoList;
	}

	/**
	 * @param descripcionTipoCuentaFlexipagoList the descripcionTipoCuentaFlexipagoList to set
	 */
	public void setDescripcionTipoCuentaFlexipagoList(
			String descripcionTipoCuentaFlexipagoList) {
		this.descripcionTipoCuentaFlexipagoList = descripcionTipoCuentaFlexipagoList;
	}

	/**
	 * @return the tipoCuentaFlexipagoList
	 */
	public String getTipoCuentaFlexipagoList() {
		return tipoCuentaFlexipagoList;
	}

	/**
	 * @param tipoCuentaFlexipagoList the tipoCuentaFlexipagoList to set
	 */
	public void setTipoCuentaFlexipagoList(String tipoCuentaFlexipagoList) {
		this.tipoCuentaFlexipagoList = tipoCuentaFlexipagoList;
	}

	/**
	 * @return the indicadorContrato
	 */
	public String getIndicadorContrato() {
		return indicadorContrato;
	}

	/**
	 * @param indicadorContrato the indicadorContrato to set
	 */
	public void setIndicadorContrato(String indicadorContrato) {
		this.indicadorContrato = indicadorContrato;
	}

	/**
	 * @return the fechaInicioD
	 */
	public Date getFechaInicioD() {
		return fechaInicioD;
	}

	/**
	 * @param fechaInicioD the fechaInicioD to set
	 */
	public void setFechaInicioD(Date fechaInicioD) {
		this.fechaInicioD = fechaInicioD;
	}

	/**
	 * @return the fechaFinD
	 */
	public Date getFechaFinD() {
		return fechaFinD;
	}

	/**
	 * @param fechaFinD the fechaFinD to set
	 */
	public void setFechaFinD(Date fechaFinD) {
		this.fechaFinD = fechaFinD;
	}
	
	
	
	

}
