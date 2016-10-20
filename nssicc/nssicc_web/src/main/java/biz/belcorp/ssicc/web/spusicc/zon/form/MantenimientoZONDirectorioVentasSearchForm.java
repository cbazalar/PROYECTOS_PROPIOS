package biz.belcorp.ssicc.web.spusicc.zon.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoZONDirectorioVentasSearchForm extends BaseSearchForm implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoClienteBuscar;
	private String codigoCargo;
	private String codigoRegion;
	private String codigoZona;
	private String codigoPeriodo;
	private String oidIdioma;
	private String numeroDocIdentidadBuscar;
	private String nombreCliente;
	private String codigoEstadoRegistro;
	private String cub;
	private String codigoRol;
	private String codigoPerfil;
	private String codigoNovedad;
	private String fechaIngresoDesde;
	private String fechaIngresoHasta;
	private Date fechaIngresoDesdeDate;
	private Date fechaIngresoHastaDate;
	
	private String indicadorFutura;
	
	
	/**
	 * @return the oidIdioma
	 */
	public String getOidIdioma() {
		return oidIdioma;
	}
	/**
	 * @param oidIdioma the oidIdioma to set
	 */
	public void setOidIdioma(String oidIdioma) {
		this.oidIdioma = oidIdioma;
	}
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}


	/**
	 * @return the codigoClienteBuscar
	 */
	public String getCodigoClienteBuscar() {
		return codigoClienteBuscar;
	}
	/**
	 * @param codigoClienteBuscar the codigoClienteBuscar to set
	 */
	public void setCodigoClienteBuscar(String codigoClienteBuscar) {
		this.codigoClienteBuscar = codigoClienteBuscar;
	}
	/**
	 * @return the codigoCargo
	 */
	public String getCodigoCargo() {
		return codigoCargo;
	}
	/**
	 * @param codigoCargo the codigoCargo to set
	 */
	public void setCodigoCargo(String codigoCargo) {
		this.codigoCargo = codigoCargo;
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
	 * @return the numeroDocIdentidadBuscar
	 */
	public String getNumeroDocIdentidadBuscar() {
		return numeroDocIdentidadBuscar;
	}
	/**
	 * @param numeroDocIdentidadBuscar the numeroDocIdentidadBuscar to set
	 */
	public void setNumeroDocIdentidadBuscar(String numeroDocIdentidadBuscar) {
		this.numeroDocIdentidadBuscar = numeroDocIdentidadBuscar;
	}
	/**
	 * @return the codigoEstadoRegistro
	 */
	public String getCodigoEstadoRegistro() {
		return codigoEstadoRegistro;
	}
	/**
	 * @param codigoEstadoRegistro the codigoEstadoRegistro to set
	 */
	public void setCodigoEstadoRegistro(String codigoEstadoRegistro) {
		this.codigoEstadoRegistro = codigoEstadoRegistro;
	}
	
	/**
	 * @return the nombreCliente
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}
	
	/**
	 * @param nombreCliente the nombreCliente to set
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	
	/**
	 * @return the cub
	 */
	public String getCub() {
		return cub;
	}
	
	/**
	 * @param cub the cub to set
	 */
	public void setCub(String cub) {
		this.cub = cub;
	}
	
	/**
	 * @return the codigoRol
	 */
	public String getCodigoRol() {
		return codigoRol;
	}
	
	/**
	 * @param codigoRol the codigoRol to set
	 */
	public void setCodigoRol(String codigoRol) {
		this.codigoRol = codigoRol;
	}
	
	/**
	 * @return the codigoPerfil
	 */
	public String getCodigoPerfil() {
		return codigoPerfil;
	}
	
	/**
	 * @param codigoPerfil the codigoPerfil to set
	 */
	public void setCodigoPerfil(String codigoPerfil) {
		this.codigoPerfil = codigoPerfil;
	}
	
	/**
	 * @return the fechaIngresoDesde
	 */
	public String getFechaIngresoDesde() {
		return fechaIngresoDesde;
	}
	
	/**
	 * @param fechaIngresoDesde the fechaIngresoDesde to set
	 */
	public void setFechaIngresoDesde(String fechaIngresoDesde) {
		this.fechaIngresoDesde = fechaIngresoDesde;
	}
	
	/**
	 * @return the fechaIngresoHasta
	 */
	public String getFechaIngresoHasta() {
		return fechaIngresoHasta;
	}
	
	/**
	 * @param fechaIngresoHasta the fechaIngresoHasta to set
	 */
	public void setFechaIngresoHasta(String fechaIngresoHasta) {
		this.fechaIngresoHasta = fechaIngresoHasta;
	}
	
	/**
	 * @return the codigoNovedad
	 */
	public String getCodigoNovedad() {
		return codigoNovedad;
	}
	
	/**
	 * @param codigoNovedad the codigoNovedad to set
	 */
	public void setCodigoNovedad(String codigoNovedad) {
		this.codigoNovedad = codigoNovedad;
	}
	/**
	 * @return the indicadorFutura
	 */
	public String getIndicadorFutura() {
		return indicadorFutura;
	}
	/**
	 * @param indicadorFutura the indicadorFutura to set
	 */
	public void setIndicadorFutura(String indicadorFutura) {
		this.indicadorFutura = indicadorFutura;
	}
	public Date getFechaIngresoDesdeDate() {
		return fechaIngresoDesdeDate;
	}
	public void setFechaIngresoDesdeDate(Date fechaIngresoDesdeDate) {
		this.fechaIngresoDesdeDate = fechaIngresoDesdeDate;
	}
	public Date getFechaIngresoHastaDate() {
		return fechaIngresoHastaDate;
	}
	public void setFechaIngresoHastaDate(Date fechaIngresoHastaDate) {
		this.fechaIngresoHastaDate = fechaIngresoHastaDate;
	}
}