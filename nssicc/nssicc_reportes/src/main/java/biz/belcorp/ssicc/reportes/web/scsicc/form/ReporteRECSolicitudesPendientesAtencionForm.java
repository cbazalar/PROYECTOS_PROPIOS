package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


public class ReporteRECSolicitudesPendientesAtencionForm extends
		BaseReporteForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String codigoPeriodoInicio;

	private String codigoPeriodoFin;
	
	private String fechaFacturacionInicio;
	
	private Date fechaFacturacionInicioD;
	
	private String fechaFacturacionFin;
	
	private Date fechaFacturacionFinD;
	
	private String tipoReporte;
	
	private String[] regionList;

	private String[] zonaList;

	private String[] territorioList;
	
	private String[] grupoProcesoList;
	
	private String[] operacionList;
	
	public Date getFechaFacturacionInicioD() {
		return fechaFacturacionInicioD;
	}

	public void setFechaFacturacionInicioD(Date fechaFacturacionInicioD) {
		this.fechaFacturacionInicioD = fechaFacturacionInicioD;
	}

	public Date getFechaFacturacionFinD() {
		return fechaFacturacionFinD;
	}

	public void setFechaFacturacionFinD(Date fechaFacturacionFinD) {
		this.fechaFacturacionFinD = fechaFacturacionFinD;
	}

	
	public void reset() {
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));
		this.codigoPeriodoInicio = (String) request.getSession().getAttribute(
				"codigoPeriodoInicio");
		if (StringUtils.isEmpty(this.codigoPeriodoInicio))
			this.codigoPeriodoInicio = periodo;
		this.codigoPeriodoFin = (String) request.getSession().getAttribute(
				"codigoPeriodoFin");
		if (StringUtils.isEmpty(this.codigoPeriodoFin))
			this.codigoPeriodoFin = periodo;*/

	}

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the codigoPeriodoFin.
	 */
	public String getCodigoPeriodoFin() {
		return codigoPeriodoFin;
	}

 
	public void setCodigoPeriodoFin(String codigoPeriodoFin) {
		this.codigoPeriodoFin = codigoPeriodoFin;
	}

	/**
	 * @return Returns the codigoPeriodoInicio.
	 */
	public String getCodigoPeriodoInicio() {
		return codigoPeriodoInicio;
	}


	public void setCodigoPeriodoInicio(String codigoPeriodoInicio) {
		this.codigoPeriodoInicio = codigoPeriodoInicio;
	}

	/**
	 * @return Returns the fechaFacturacionFin.
	 */
	public String getFechaFacturacionFin() {
		return fechaFacturacionFin;
	}


	public void setFechaFacturacionFin(String fechaFacturacionFin) {
		this.fechaFacturacionFin = fechaFacturacionFin;
	}

	/**
	 * @return Returns the fechaFacturacionInicio.
	 */
	public String getFechaFacturacionInicio() {
		return fechaFacturacionInicio;
	}


	public void setFechaFacturacionInicio(String fechaFacturacionInicio) {
		this.fechaFacturacionInicio = fechaFacturacionInicio;
	}

	/**
	 * @return Returns the regionList.
	 */
	public String[] getRegionList() {
		return regionList;
	}

	/**
	 * @param regionList
	 *            The regionList to set.
	 */
	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}

	/**
	 * @return Returns the territorioList.
	 */
	public String[] getTerritorioList() {
		return territorioList;
	}

	/**
	 * @param territorioList
	 *            The territorioList to set.
	 */
	public void setTerritorioList(String[] territorioList) {
		this.territorioList = territorioList;
	}

	/**
	 * @return Returns the tipoReporte.
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte
	 *            The tipoReporte to set.
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	/**
	 * @return Returns the zonaList.
	 */
	public String[] getZonaList() {
		return zonaList;
	}

	/**
	 * @param zonaList
	 *            The zonaList to set.
	 */
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}

	/**
	 * @return Returns the grupoProcesoList.
	 */
	public String[] getGrupoProcesoList() {
		return grupoProcesoList;
	}

	/**
	 * @param grupoProcesoList
	 *            The grupoProcesoList to set.
	 */
	public void setGrupoProcesoList(String[] grupoProcesoList) {
		this.grupoProcesoList = grupoProcesoList;
	}
	
	/**
	 * @return Returns the operacionList.
	 */
	public String[] getOperacionList() {
		return operacionList;
	}

	/**
	 * @param operacionList The operacionList to set.
	 * 
	 */
	public void setOperacionList(String[] operacionList) {
		this.operacionList = operacionList;
	}
	
}
