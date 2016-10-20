package biz.belcorp.ssicc.web.spusicc.reclamos.form;

import java.io.Serializable;

import org.apache.commons.fileupload.FileUpload;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoRECIngresoAtencionesForm extends
		BaseSearchForm implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoPeriodoProceso;
	private String tipoProducto;
	private String tipoAtencion;
	private String codigoPeriodoReferencia;
	private String codigoVenta;
	private String codigoSAP;
	private String descripcionProducto;
	private String[] listaNumeroUnidades;
	private String[] selectedItems2;
	private String mostrarPanel;
	private String flagVacio;
	private FileUpload archivo;
	private String tipoOperacion;
	private String codTipoProducto;
	private String codTipoAtencion;
	private String sFlagTipoOper;
	private String codTipoOperDefault;
    private boolean flagFilaCodigos;
    
    
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts.validator.ValidatorForm#reset(org.apache.struts.action
	 * .ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	
	public boolean isFlagFilaCodigos() {
		return flagFilaCodigos;
	}

	public void setFlagFilaCodigos(boolean flagFilaCodigos) {
		this.flagFilaCodigos = flagFilaCodigos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            the codigoPais to set
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
	 * @param codigoPeriodoProceso
	 *            the codigoPeriodoProceso to set
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
	 * @param tipoProducto
	 *            the tipoProducto to set
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
	 * @param tipoAtencion
	 *            the tipoAtencion to set
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
	 * @param codigoPeriodoReferencia
	 *            the codigoPeriodoReferencia to set
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
	 * @param codigoVenta
	 *            the codigoVenta to set
	 */
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}

	/**
	 * @return the codigoSAP
	 */
	public String getCodigoSAP() {
		return codigoSAP;
	}

	/**
	 * @param codigoSAP
	 *            the codigoSAP to set
	 */
	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}

	/**
	 * @return the descripcionProducto
	 */
	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	/**
	 * @param descripcionProducto
	 *            the descripcionProducto to set
	 */
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	/**
	 * @return the listaNumeroUnidades
	 */
	public String[] getListaNumeroUnidades() {
		return listaNumeroUnidades;
	}

	/**
	 * @param listaNumeroUnidades
	 *            the listaNumeroUnidades to set
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
	 * @param selectedItems2
	 *            the selectedItems2 to set
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
	 * @param mostrarPanel
	 *            the mostrarPanel to set
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
	 * @param flagVacio
	 *            the flagVacio to set
	 */
	public void setFlagVacio(String flagVacio) {
		this.flagVacio = flagVacio;
	}

	/**
	 * @return the archivo
	 */


	/**
	 * @return tipoOperacion
	 */
	public String getTipoOperacion() {
		return tipoOperacion;
	}

	public FileUpload getArchivo() {
		return archivo;
	}

	public void setArchivo(FileUpload archivo) {
		this.archivo = archivo;
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

}
