/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * @author Sigcomt
 *
 */
public class ProcesoOCRDepuraBolsaPedidosSearchForm extends BaseSearchForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6631846191042517849L;
	
	protected String[] selectedItems = {};

	private String id;

	private String tipoIngreso;

	private String fechaInicio;

	private String fechaFin;

	private String operacion; // indicador Deuda o Rechazado o ...

	private String codigoRegion;

	private String codigoZona;

	private String[] regiones;

	private String[] zonas;

	private String opcion;

	private String subAcceso;

	private String codigoPeriodo;

	private String codigoCliente;

	private String[] subAccesos;

	private String codigoMarca;

	private String codigoCanal;

	private String estadoDeuda;

	private String fechaInicialSolicitud;
	
	private Date fechaInicialSolicitudDate;

	private String fechaFinSolicitud;
	
	private Date fechaFinSolicitudDate;

	private String fechaSolicitud;
		
	private String codigoPais;

	private String estadoProceso;

	private String nombreCliente;

	private String descripcionRegion;

	private String descripcionZona;

	private String codigoTerritorio;

	private String valorSaldoDeudor;

	/**
	 * Holds value of property indicadorAdmCartera.
	 */
	protected boolean indOCSBloqueada;

	private String observaciones;

	private String indErrorOCSBloqueada;

	/**
	 * @return the selectedItems
	 */
	public String[] getSelectedItems() {
		return selectedItems;
	}

	/**
	 * @param selectedItems the selectedItems to set
	 */
	public void setSelectedItems(String[] selectedItems) {
		this.selectedItems = selectedItems;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the tipoIngreso
	 */
	public String getTipoIngreso() {
		return tipoIngreso;
	}

	/**
	 * @param tipoIngreso the tipoIngreso to set
	 */
	public void setTipoIngreso(String tipoIngreso) {
		this.tipoIngreso = tipoIngreso;
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
	 * @return the operacion
	 */
	public String getOperacion() {
		return operacion;
	}

	/**
	 * @param operacion the operacion to set
	 */
	public void setOperacion(String operacion) {
		this.operacion = operacion;
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
	 * @return the regiones
	 */
	public String[] getRegiones() {
		return regiones;
	}

	/**
	 * @param regiones the regiones to set
	 */
	public void setRegiones(String[] regiones) {
		this.regiones = regiones;
	}

	/**
	 * @return the zonas
	 */
	public String[] getZonas() {
		return zonas;
	}

	/**
	 * @param zonas the zonas to set
	 */
	public void setZonas(String[] zonas) {
		this.zonas = zonas;
	}

	/**
	 * @return the opcion
	 */
	public String getOpcion() {
		return opcion;
	}

	/**
	 * @param opcion the opcion to set
	 */
	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	/**
	 * @return the subAcceso
	 */
	public String getSubAcceso() {
		return subAcceso;
	}

	/**
	 * @param subAcceso the subAcceso to set
	 */
	public void setSubAcceso(String subAcceso) {
		this.subAcceso = subAcceso;
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
	 * @return the subAccesos
	 */
	public String[] getSubAccesos() {
		return subAccesos;
	}

	/**
	 * @param subAccesos the subAccesos to set
	 */
	public void setSubAccesos(String[] subAccesos) {
		this.subAccesos = subAccesos;
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
	 * @return the estadoDeuda
	 */
	public String getEstadoDeuda() {
		return estadoDeuda;
	}

	/**
	 * @param estadoDeuda the estadoDeuda to set
	 */
	public void setEstadoDeuda(String estadoDeuda) {
		this.estadoDeuda = estadoDeuda;
	}

	/**
	 * @return the fechaInicialSolicitud
	 */
	public String getFechaInicialSolicitud() {
		return fechaInicialSolicitud;
	}

	/**
	 * @param fechaInicialSolicitud the fechaInicialSolicitud to set
	 */
	public void setFechaInicialSolicitud(String fechaInicialSolicitud) {
		this.fechaInicialSolicitud = fechaInicialSolicitud;
	}

	/**
	 * @return the fechaFinSolicitud
	 */
	public String getFechaFinSolicitud() {
		return fechaFinSolicitud;
	}

	/**
	 * @param fechaFinSolicitud the fechaFinSolicitud to set
	 */
	public void setFechaFinSolicitud(String fechaFinSolicitud) {
		this.fechaFinSolicitud = fechaFinSolicitud;
	}

	/**
	 * @return the fechaSolicitud
	 */
	public String getFechaSolicitud() {
		return fechaSolicitud;
	}

	/**
	 * @param fechaSolicitud the fechaSolicitud to set
	 */
	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
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
	 * @return the estadoProceso
	 */
	public String getEstadoProceso() {
		return estadoProceso;
	}

	/**
	 * @param estadoProceso the estadoProceso to set
	 */
	public void setEstadoProceso(String estadoProceso) {
		this.estadoProceso = estadoProceso;
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

	/**
	 * @return the codigoTerritorio
	 */
	public String getCodigoTerritorio() {
		return codigoTerritorio;
	}

	/**
	 * @param codigoTerritorio the codigoTerritorio to set
	 */
	public void setCodigoTerritorio(String codigoTerritorio) {
		this.codigoTerritorio = codigoTerritorio;
	}

	/**
	 * @return the valorSaldoDeudor
	 */
	public String getValorSaldoDeudor() {
		return valorSaldoDeudor;
	}

	/**
	 * @param valorSaldoDeudor the valorSaldoDeudor to set
	 */
	public void setValorSaldoDeudor(String valorSaldoDeudor) {
		this.valorSaldoDeudor = valorSaldoDeudor;
	}

	/**
	 * @return the indOCSBloqueada
	 */
	public boolean isIndOCSBloqueada() {
		return indOCSBloqueada;
	}

	/**
	 * @param indOCSBloqueada the indOCSBloqueada to set
	 */
	public void setIndOCSBloqueada(boolean indOCSBloqueada) {
		this.indOCSBloqueada = indOCSBloqueada;
	}

	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return the indErrorOCSBloqueada
	 */
	public String getIndErrorOCSBloqueada() {
		return indErrorOCSBloqueada;
	}

	/**
	 * @param indErrorOCSBloqueada the indErrorOCSBloqueada to set
	 */
	public void setIndErrorOCSBloqueada(String indErrorOCSBloqueada) {
		this.indErrorOCSBloqueada = indErrorOCSBloqueada;
	}

	/**
	 * @return the fechaInicialSolicitudDate
	 */
	public Date getFechaInicialSolicitudDate() {
		return fechaInicialSolicitudDate;
	}

	/**
	 * @param fechaInicialSolicitudDate the fechaInicialSolicitudDate to set
	 */
	public void setFechaInicialSolicitudDate(Date fechaInicialSolicitudDate) {
		this.fechaInicialSolicitudDate = fechaInicialSolicitudDate;
	}

	/**
	 * @return the fechaFinSolicitudDate
	 */
	public Date getFechaFinSolicitudDate() {
		return fechaFinSolicitudDate;
	}

	/**
	 * @param fechaFinSolicitudDate the fechaFinSolicitudDate to set
	 */
	public void setFechaFinSolicitudDate(Date fechaFinSolicitudDate) {
		this.fechaFinSolicitudDate = fechaFinSolicitudDate;
	}

	
}
