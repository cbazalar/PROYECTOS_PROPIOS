package biz.belcorp.ssicc.dao.model;

import java.io.File;
import java.io.Serializable;

public class ProcesoRECAtencionesMasivas implements Serializable{

	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoPeriodoProceso;
	private String tipoProducto;
	private String tipoAtencion;
	private String codigoPeriodoReferencia;
	private String codigoVenta;
	private String tipoOperacion;
	
	private String[] listaNumeroUnidades;
	private String[] selectedItems2;	
	private String mostrarPanel;	
	private String flagVacio;

	private String codTipoProducto;
	private String codTipoAtencion;
	private String sFlagTipoOper;
	private String codTipoOperDefault;
	private File Archivo;	

	public ProcesoRECAtencionesMasivas() {}

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
	 * @return the codigoPeriodoProceso
	 */
	public String getCodigoPeriodoProceso() {
		return codigoPeriodoProceso;
	}

	/**
	 * @param codigoPeriodoProceso the codigoPeriodoProceso to set
	 */
	public void setCodigoPeriodoProceso(String codigoPeriodoProceso) {
		this.codigoPeriodoProceso = codigoPeriodoProceso;
	}

	/**
	 * @return the tipoProducto
	 */
	public String getTipoProducto() {
		return tipoProducto;
	}

	/**
	 * @param tipoProducto the tipoProducto to set
	 */
	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	/**
	 * @return the tipoAtencion
	 */
	public String getTipoAtencion() {
		return tipoAtencion;
	}

	/**
	 * @param tipoAtencion the tipoAtencion to set
	 */
	public void setTipoAtencion(String tipoAtencion) {
		this.tipoAtencion = tipoAtencion;
	}

	/**
	 * @return the codigoPeriodoReferencia
	 */
	public String getCodigoPeriodoReferencia() {
		return codigoPeriodoReferencia;
	}

	/**
	 * @param codigoPeriodoReferencia the codigoPeriodoReferencia to set
	 */
	public void setCodigoPeriodoReferencia(String codigoPeriodoReferencia) {
		this.codigoPeriodoReferencia = codigoPeriodoReferencia;
	}

	/**
	 * @return the codigoVenta
	 */
	public String getCodigoVenta() {
		return codigoVenta;
	}

	/**
	 * @param codigoVenta the codigoVenta to set
	 */
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}

	/**
	 * @return the tipoOperacion
	 */
	public String getTipoOperacion() {
		return tipoOperacion;
	}

	/**
	 * @param tipoOperacion the tipoOperacion to set
	 */
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	/**
	 * @return the listaNumeroUnidades
	 */
	public String[] getListaNumeroUnidades() {
		return listaNumeroUnidades;
	}

	/**
	 * @param listaNumeroUnidades the listaNumeroUnidades to set
	 */
	public void setListaNumeroUnidades(String[] listaNumeroUnidades) {
		this.listaNumeroUnidades = listaNumeroUnidades;
	}

	/**
	 * @return the selectedItems2
	 */
	public String[] getSelectedItems2() {
		return selectedItems2;
	}

	/**
	 * @param selectedItems2 the selectedItems2 to set
	 */
	public void setSelectedItems2(String[] selectedItems2) {
		this.selectedItems2 = selectedItems2;
	}

	/**
	 * @return the mostrarPanel
	 */
	public String getMostrarPanel() {
		return mostrarPanel;
	}

	/**
	 * @param mostrarPanel the mostrarPanel to set
	 */
	public void setMostrarPanel(String mostrarPanel) {
		this.mostrarPanel = mostrarPanel;
	}

	/**
	 * @return the flagVacio
	 */
	public String getFlagVacio() {
		return flagVacio;
	}

	/**
	 * @param flagVacio the flagVacio to set
	 */
	public void setFlagVacio(String flagVacio) {
		this.flagVacio = flagVacio;
	}

	/**
	 * @return the codTipoProducto
	 */
	public String getCodTipoProducto() {
		return codTipoProducto;
	}

	/**
	 * @param codTipoProducto the codTipoProducto to set
	 */
	public void setCodTipoProducto(String codTipoProducto) {
		this.codTipoProducto = codTipoProducto;
	}

	/**
	 * @return the codTipoAtencion
	 */
	public String getCodTipoAtencion() {
		return codTipoAtencion;
	}

	/**
	 * @param codTipoAtencion the codTipoAtencion to set
	 */
	public void setCodTipoAtencion(String codTipoAtencion) {
		this.codTipoAtencion = codTipoAtencion;
	}

	/**
	 * @return the sFlagTipoOper
	 */
	public String getsFlagTipoOper() {
		return sFlagTipoOper;
	}

	/**
	 * @param sFlagTipoOper the sFlagTipoOper to set
	 */
	public void setsFlagTipoOper(String sFlagTipoOper) {
		this.sFlagTipoOper = sFlagTipoOper;
	}

	/**
	 * @return the codTipoOperDefault
	 */
	public String getCodTipoOperDefault() {
		return codTipoOperDefault;
	}

	/**
	 * @param codTipoOperDefault the codTipoOperDefault to set
	 */
	public void setCodTipoOperDefault(String codTipoOperDefault) {
		this.codTipoOperDefault = codTipoOperDefault;
	}

	public File getArchivo() {
		return Archivo;
	}

	public void setArchivo(File archivo) {
		Archivo = archivo;
	}

}
