package biz.belcorp.ssicc.web.spusicc.reclamos.form;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoRECAtencionesMasivasForm extends BaseSearchForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6998605929557643455L;
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
	private UploadedFile archivo;

	private String codTipoProducto;
	private String codTipoAtencion;
	private String sFlagTipoOper;
	private String codTipoOperDefault;
	
	private String numfila;
	private String cantExis;
	private String cantNoExis;
	private String numeroLote;
	private String mostrarEjecutar;
	
	private String activarMatrizConPedido;
	private String extensionArchivo;	//extension del archivo	
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type="required"
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
	 * @struts.validator type="required"
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
	 * @struts.validator type="required"
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
	 * @return the archivo
	 */
	public UploadedFile getArchivo() {
		return archivo;
	}

	/**
	 * @param archivo the archivo to set
	 */
	public void setArchivo(UploadedFile archivo) {
		this.archivo = archivo;
	}
	
	/**
	 * @return tipoOperacion
	 */
	public String getTipoOperacion() {
		return tipoOperacion;
	}

	/**
	 * @param tipoOperacion
	 */
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	/**
	 * @return codTipoProducto
	 */
	public String getCodTipoProducto() {
		return codTipoProducto;
	}

	/**
	 * @param codTipoProducto
	 */
	public void setCodTipoProducto(String codTipoProducto) {
		this.codTipoProducto = codTipoProducto;
	}

	/**
	 * @return codTipoAtencion
	 */
	public String getCodTipoAtencion() {
		return codTipoAtencion;
	}

	/**
	 * @param codTipoAtencion
	 */
	public void setCodTipoAtencion(String codTipoAtencion) {
		this.codTipoAtencion = codTipoAtencion;
	}

	/**
	 * @return sFlagTipoOper
	 */
	public String getsFlagTipoOper() {
		return sFlagTipoOper;
	}

	/**
	 * @param sFlagTipoOper
	 */
	public void setsFlagTipoOper(String sFlagTipoOper) {
		this.sFlagTipoOper = sFlagTipoOper;
	}

	/**
	 * @return codTipoOperDefault
	 */
	public String getCodTipoOperDefault() {
		return codTipoOperDefault;
	}

	/**
	 * @param codTipoOperDefault
	 */
	public void setCodTipoOperDefault(String codTipoOperDefault) {
		this.codTipoOperDefault = codTipoOperDefault;
	}

	/**
	 * @return the numfila
	 */
	public String getNumfila() {
		return numfila;
	}

	/**
	 * @param numfila the numfila to set
	 */
	public void setNumfila(String numfila) {
		this.numfila = numfila;
	}

	/**
	 * @return the cantExis
	 */
	public String getCantExis() {
		return cantExis;
	}

	/**
	 * @param cantExis the cantExis to set
	 */
	public void setCantExis(String cantExis) {
		this.cantExis = cantExis;
	}

	/**
	 * @return the cantNoExis
	 */
	public String getCantNoExis() {
		return cantNoExis;
	}

	/**
	 * @param cantNoExis the cantNoExis to set
	 */
	public void setCantNoExis(String cantNoExis) {
		this.cantNoExis = cantNoExis;
	}

	/**
	 * @return the numeroLote
	 */
	public String getNumeroLote() {
		return numeroLote;
	}

	/**
	 * @param numeroLote the numeroLote to set
	 */
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	/**
	 * @return the mostrarEjecutar
	 */
	public String getMostrarEjecutar() {
		return mostrarEjecutar;
	}

	/**
	 * @param mostrarEjecutar the mostrarEjecutar to set
	 */
	public void setMostrarEjecutar(String mostrarEjecutar) {
		this.mostrarEjecutar = mostrarEjecutar;
	}

	/**
	 * @return the activarMatrizConPedido
	 */
	public String getActivarMatrizConPedido() {
		return activarMatrizConPedido;
	}

	/**
	 * @param activarMatrizConPedido the activarMatrizConPedido to set
	 */
	public void setActivarMatrizConPedido(String activarMatrizConPedido) {
		this.activarMatrizConPedido = activarMatrizConPedido;
	}
	public String getExtensionArchivo() {
		return extensionArchivo;
	}
	public void setExtensionArchivo(String extensionArchivo) {
		this.extensionArchivo = extensionArchivo;
	}
	
}
