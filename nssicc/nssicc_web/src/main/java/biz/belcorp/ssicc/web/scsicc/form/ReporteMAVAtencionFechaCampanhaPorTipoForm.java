package biz.belcorp.ssicc.web.scsicc.form;

import java.util.Date;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;



/**
 * Form base para los Reportes MAV de ScSiCC.
 * 
 * @author <a href="">Eduardo Sánchez</a>
 * 
 * @struts.form name="reporteMAVAtencionFechaCampanhaPorTipoForm" extends="baseReporteForm"
 */
public class ReporteMAVAtencionFechaCampanhaPorTipoForm extends BaseReporteForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoPeriodo;

	private String codigoPais;

	private String codigoTipoMav;

	private String codigoActividad;
	
	private String codigoTipoOferta;
	
	private String codigoProducto;
	
	private String codigoTipoReporte;
	
	private String fechaInicio;
	
	private Date fechaInicioD;

	private String fechaFin;
	
	private Date fechaFinD;
	
	private String flagRangoFechas;
	
	private String nombreTipoMav;
	
	private String nombreTipoOferta;
	
	private String codigoRadio;
	
	private String indicadorCapacitadora;
	private String[] regionListMultiple;
	private String[] zonaListMultiple;
	private String codigoTipoCargo;
	private String codigoIndicadorEnvio;
	
	private String indicadorTipoCargo;
	
	private String[] arrayCodigosSAP;
	
	private UploadedFile sapsFile;
	private String directorioTemporal;
	
	
	public Date getFechaInicioD() {
		return fechaInicioD;
	}

	public void setFechaInicioD(Date fechaInicioD) {
		this.fechaInicioD = fechaInicioD;
	}

	public Date getFechaFinD() {
		return fechaFinD;
	}

	public void setFechaFinD(Date fechaFinD) {
		this.fechaFinD = fechaFinD;
	}

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @struts.validator type="required"
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigoTipoMav
	 */
	public String getCodigoTipoMav() {
		return codigoTipoMav;
	}


	public void setCodigoTipoMav(String codigoTipoMav) {
		this.codigoTipoMav = codigoTipoMav;
	}

	/**
	 * @return the codigoActividad
	 */
	public String getCodigoActividad() {
		return codigoActividad;
	}

	/**
	 * @param codigoActividad the codigoActividad to set
	 */
	public void setCodigoActividad(String codigoActividad) {
		this.codigoActividad = codigoActividad;
	}

	/**
	 * @return the codigoTipoOferta
	 */
	public String getCodigoTipoOferta() {
		return codigoTipoOferta;
	}

	/**
	 * @param codigoTipoOferta the codigoTipoOferta to set
	 */
	public void setCodigoTipoOferta(String codigoTipoOferta) {
		this.codigoTipoOferta = codigoTipoOferta;
	}

	/**
	 * @return the codigoProducto
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * @param codigoProducto the codigoProducto to set
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	/**
	 * @return the codigoTipoReporte
	 */
	public String getCodigoTipoReporte() {
		return codigoTipoReporte;
	}


	public void setCodigoTipoReporte(String codigoTipoReporte) {
		this.codigoTipoReporte = codigoTipoReporte;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	 * @return the flagRangoFechas
	 */
	public String getFlagRangoFechas() {
		return flagRangoFechas;
	}

	/**
	 * @param flagRangoFechas the flagRangoFechas to set
	 */
	public void setFlagRangoFechas(String flagRangoFechas) {
		this.flagRangoFechas = flagRangoFechas;
	}

	/**
	 * @return the nombreTipoMav
	 */
	public String getNombreTipoMav() {
		return nombreTipoMav;
	}

	/**
	 * @param nombreTipoMav the nombreTipoMav to set
	 */
	public void setNombreTipoMav(String nombreTipoMav) {
		this.nombreTipoMav = nombreTipoMav;
	}

	/**
	 * @return the nombreTipoOferta
	 */
	public String getNombreTipoOferta() {
		return nombreTipoOferta;
	}

	/**
	 * @param nombreTipoOferta the nombreTipoOferta to set
	 */
	public void setNombreTipoOferta(String nombreTipoOferta) {
		this.nombreTipoOferta = nombreTipoOferta;
	}

	/**
	 * @return the codigoRadio
	 */
	public String getCodigoRadio() {
		return codigoRadio;
	}

	/**
	 * @param codigoRadio the codigoRadio to set
	 */
	public void setCodigoRadio(String codigoRadio) {
		this.codigoRadio = codigoRadio;
	}

	/**
	 * @return the indicadorCapacitadora
	 */
	public String getIndicadorCapacitadora() {
		return indicadorCapacitadora;
	}

	/**
	 * @param indicadorCapacitadora the indicadorCapacitadora to set
	 */
	public void setIndicadorCapacitadora(String indicadorCapacitadora) {
		this.indicadorCapacitadora = indicadorCapacitadora;
	}

	/**
	 * @return the regionListMultiple
	 */
	public String[] getRegionListMultiple() {
		return regionListMultiple;
	}

	/**
	 * @param regionListMultiple the regionListMultiple to set
	 */
	public void setRegionListMultiple(String[] regionListMultiple) {
		this.regionListMultiple = regionListMultiple;
	}

	/**
	 * @return the zonaListMultiple
	 */
	public String[] getZonaListMultiple() {
		return zonaListMultiple;
	}

	/**
	 * @param zonaListMultiple the zonaListMultiple to set
	 */
	public void setZonaListMultiple(String[] zonaListMultiple) {
		this.zonaListMultiple = zonaListMultiple;
	}

	/**
	 * @return the codigoTipoCargo
	 */
	public String getCodigoTipoCargo() {
		return codigoTipoCargo;
	}

	/**
	 * @param codigoTipoCargo the codigoTipoCargo to set
	 */
	public void setCodigoTipoCargo(String codigoTipoCargo) {
		this.codigoTipoCargo = codigoTipoCargo;
	}

	/**
	 * @return the codigoIndicadorEnvio
	 */
	public String getCodigoIndicadorEnvio() {
		return codigoIndicadorEnvio;
	}

	/**
	 * @param codigoIndicadorEnvio the codigoIndicadorEnvio to set
	 */
	public void setCodigoIndicadorEnvio(String codigoIndicadorEnvio) {
		this.codigoIndicadorEnvio = codigoIndicadorEnvio;
	}

	/**
	 * @return the indicadorTipoCargo
	 */
	public String getIndicadorTipoCargo() {
		return indicadorTipoCargo;
	}

	/**
	 * @param indicadorTipoCargo the indicadorTipoCargo to set
	 */
	public void setIndicadorTipoCargo(String indicadorTipoCargo) {
		this.indicadorTipoCargo = indicadorTipoCargo;
	}

	/**
	 * @return the arrayCodigosSAP
	 */
	public String[] getArrayCodigosSAP() {
		return arrayCodigosSAP;
	}

	/**
	 * @param arrayCodigosSAP the arrayCodigosSAP to set
	 */
	public void setArrayCodigosSAP(String[] arrayCodigosSAP) {
		this.arrayCodigosSAP = arrayCodigosSAP;
	}

	/**
	 * @return the sapsFile
	 */
	public UploadedFile getSapsFile() {
		return sapsFile;
	}

	/**
	 * @param sapsFile the sapsFile to set
	 */
	public void setSapsFile(UploadedFile sapsFile) {
		this.sapsFile = sapsFile;
	}

	/**
	 * @return the directorioTemporal
	 */
	public String getDirectorioTemporal() {
		return directorioTemporal;
	}

	/**
	 * @param directorioTemporal the directorioTemporal to set
	 */
	public void setDirectorioTemporal(String directorioTemporal) {
		this.directorioTemporal = directorioTemporal;
	}	
}
