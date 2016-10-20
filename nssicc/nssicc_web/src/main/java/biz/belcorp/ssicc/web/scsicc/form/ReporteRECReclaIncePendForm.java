package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 *
 */
public class ReporteRECReclaIncePendForm extends BaseReporteForm implements Serializable{

	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String codigoPeriodoReclamoInicio;

	private String codigoPeriodoReclamoFin;

	private String codigoPeriodoReferenciaInicio;

	private String codigoPeriodoReferenciaFin;

	private String codigoPeriodoInicial;

	private String codigoPeriodoFinal;

	private String tipoPeriodo;

	private String fechaFacturacion;

	private String fechaInicioIngreso;

	private String fechaFinIngreso;

	private String tipoReporte;

	private String[] regionList;

	private String[] zonaList;

	private String[] territorioList;

	private String[] codigoOperacion;

	private Date fechaFacturacionD;

	private Date fechaInicioIngresoD;

	private Date fechaFinIngresoD;
	
	/**
	 * @return Returns the codigoOperacion.
	 */
	public String[] getCodigoOperacion() {
		return codigoOperacion;
	}

	/**
	 * @param codigoOperacion
	 *            The codigoOperacion to set.
	 */
	public void setCodigoOperacion(String[] codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}

	/**
	 * @return Returns the codigoPeriodoReclamoFin.
	 */
	public String getCodigoPeriodoReclamoFin() {
		return codigoPeriodoReclamoFin;
	}

	/**
	 * @param codigoPeriodoReclamoFin
	 *            The codigoPeriodoReclamoFin to set.
	 */
	public void setCodigoPeriodoReclamoFin(String codigoPeriodoReclamoFin) {
		this.codigoPeriodoReclamoFin = codigoPeriodoReclamoFin;
	}

	/**
	 * @return Returns the codigoPeriodoReclamoInicio.
	 */
	public String getCodigoPeriodoReclamoInicio() {
		return codigoPeriodoReclamoInicio;
	}

	/**
	 * @param codigoPeriodoReclamoInicio
	 *            The codigoPeriodoReclamoInicio to set.
	 */
	public void setCodigoPeriodoReclamoInicio(String codigoPeriodoReclamoInicio) {
		this.codigoPeriodoReclamoInicio = codigoPeriodoReclamoInicio;
	}

	/**
	 * @return Returns the codigoPeriodoReferenciaFin.
	 */
	public String getCodigoPeriodoReferenciaFin() {
		return codigoPeriodoReferenciaFin;
	}

	/**
	 * @param codigoPeriodoReferenciaFin
	 *            The codigoPeriodoReferenciaFin to set.
	 */
	public void setCodigoPeriodoReferenciaFin(String codigoPeriodoReferenciaFin) {
		this.codigoPeriodoReferenciaFin = codigoPeriodoReferenciaFin;
	}

	/**
	 * @return Returns the codigoPeriodoReferenciaInicio.
	 */
	public String getCodigoPeriodoReferenciaInicio() {
		return codigoPeriodoReferenciaInicio;
	}

	/**
	 * @param codigoPeriodoReferenciaInicio
	 *            The codigoPeriodoReferenciaInicio to set.
	 */
	public void setCodigoPeriodoReferenciaInicio(
			String codigoPeriodoReferenciaInicio) {
		this.codigoPeriodoReferenciaInicio = codigoPeriodoReferenciaInicio;
	}

	/**
	 * @return Returns the fechaFacturacion.
	 */
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}

	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
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
	 * @return Returns the codigoPeriodoFinal.
	 */
	public String getCodigoPeriodoFinal() {
		return codigoPeriodoFinal;
	}

	/**
	 * @param codigoPeriodoFinal
	 */
	public void setCodigoPeriodoFinal(String codigoPeriodoFinal) {
		this.codigoPeriodoFinal = codigoPeriodoFinal;
	}

	/**
	 * @return Returns the codigoPeriodoInicial.
	 */
	public String getCodigoPeriodoInicial() {
		return codigoPeriodoInicial;
	}

	/**
	 * @param codigoPeriodoInicial
	 */
	public void setCodigoPeriodoInicial(String codigoPeriodoInicial) {
		this.codigoPeriodoInicial = codigoPeriodoInicial;
	}

	/**
	 * @return Returns the tipoPeriodo.
	 */
	public String getTipoPeriodo() {
		return tipoPeriodo;
	}

	/**
	 * @param tipoPeriodo
	 *            The tipoPeriodo to set.
	 */
	public void setTipoPeriodo(String tipoPeriodo) {
		this.tipoPeriodo = tipoPeriodo;
	}

	/**
	 * @return Returns the fechaFinIngreso.
	 */
	public String getFechaFinIngreso() {
		return fechaFinIngreso;
	}

	/**
	 * @param fechaFinIngreso
	 */
	public void setFechaFinIngreso(String fechaFinIngreso) {
		this.fechaFinIngreso = fechaFinIngreso;
	}

	/**
	 * @return Returns the fechaInicioIngreso.
	 */
	public String getFechaInicioIngreso() {
		return fechaInicioIngreso;
	}

	/**
	 * @param fechaInicioIngreso
	 */
	public void setFechaInicioIngreso(String fechaInicioIngreso) {
		this.fechaInicioIngreso = fechaInicioIngreso;
	}

	/**
	 * @return the fechaFacturacionD
	 */
	public Date getFechaFacturacionD() {
		return fechaFacturacionD;
	}

	/**
	 * @param fechaFacturacionD the fechaFacturacionD to set
	 */
	public void setFechaFacturacionD(Date fechaFacturacionD) {
		this.fechaFacturacionD = fechaFacturacionD;
	}

	/**
	 * @return the fechaInicioIngresoD
	 */
	public Date getFechaInicioIngresoD() {
		return fechaInicioIngresoD;
	}

	/**
	 * @param fechaInicioIngresoD the fechaInicioIngresoD to set
	 */
	public void setFechaInicioIngresoD(Date fechaInicioIngresoD) {
		this.fechaInicioIngresoD = fechaInicioIngresoD;
	}

	/**
	 * @return the fechaFinIngresoD
	 */
	public Date getFechaFinIngresoD() {
		return fechaFinIngresoD;
	}

	/**
	 * @param fechaFinIngresoD the fechaFinIngresoD to set
	 */
	public void setFechaFinIngresoD(Date fechaFinIngresoD) {
		this.fechaFinIngresoD = fechaFinIngresoD;
	}	
}