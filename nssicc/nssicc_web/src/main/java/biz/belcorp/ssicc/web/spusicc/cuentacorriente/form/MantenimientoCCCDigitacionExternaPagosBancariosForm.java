package biz.belcorp.ssicc.web.spusicc.cuentacorriente.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoCCCDigitacionExternaPagosBancariosForm extends BaseSearchForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPais;	
	private String codigoCuentaCorrienteBancaria;  
	private String fechaPago;
	private Date fechaPagoD;	
	
	private String[] listaCodigoConsultora;	
	private String[] listaNombreConsultora;
	private String[] listaFechaPago;
	private String[] listaImportePago;
	
	private String codigoConsultora;	
	private String nombreConsultora;
	private String importePago;
	
	private String[] selectedItemsDelete;
	private String longitudCampoClientes;
	private String indicadorHayRegistros;
	
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
	 * @return the codigoCuentaCorrienteBancaria
	 */
	public String getCodigoCuentaCorrienteBancaria() {
		return codigoCuentaCorrienteBancaria;
	}

	/**
	 * @param codigoCuentaCorrienteBancaria the codigoCuentaCorrienteBancaria to set
	 */
	public void setCodigoCuentaCorrienteBancaria(String codigoCuentaCorrienteBancaria) {
		this.codigoCuentaCorrienteBancaria = codigoCuentaCorrienteBancaria;
	}

	/**
	 * @return the fechaPago
	 */
	public String getFechaPago() {
		return fechaPago;
	}

	/**
	 * @param fechaPago the fechaPago to set
	 */
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	/**
	 * @return the listaCodigoConsultora
	 */
	public String[] getListaCodigoConsultora() {
		return listaCodigoConsultora;
	}

	/**
	 * @param listaCodigoConsultora the listaCodigoConsultora to set
	 */
	public void setListaCodigoConsultora(String[] listaCodigoConsultora) {
		this.listaCodigoConsultora = listaCodigoConsultora;
	}

	/**
	 * @return the listaNombreConsultora
	 */
	public String[] getListaNombreConsultora() {
		return listaNombreConsultora;
	}

	/**
	 * @param listaNombreConsultora the listaNombreConsultora to set
	 */
	public void setListaNombreConsultora(String[] listaNombreConsultora) {
		this.listaNombreConsultora = listaNombreConsultora;
	}
	
	/**
	 * @return the listaImportePago
	 */
	public String[] getListaImportePago() {
		return listaImportePago;
	}

	/**
	 * @param listaImportePago the listaImportePago to set
	 */
	public void setListaImportePago(String[] listaImportePago) {
		this.listaImportePago = listaImportePago;
	}
	
	/**
	 * @return the listaFechaPago
	 */
	public String[] getListaFechaPago() {
		return listaFechaPago;
	}

	/**
	 * @param listaImportePago the listaFechaPago to set
	 */
	public void setListaFechaPago(String[] listaFechaPago) {
		this.listaFechaPago = listaFechaPago;
	}

	/**
	 * @return the selectedItemsDelete
	 */
	public String[] getSelectedItemsDelete() {
		return selectedItemsDelete;
	}

	/**
	 * @param selectedItemsDelete the selectedItemsDelete to set
	 */
	public void setSelectedItemsDelete(String[] selectedItemsDelete) {
		this.selectedItemsDelete = selectedItemsDelete;
	}

	/**
	 * @return the longitudCampoClientes
	 */
	public String getLongitudCampoClientes() {
		return longitudCampoClientes;
	}

	/**
	 * @param longitudCampoClientes the longitudCampoClientes to set
	 */
	public void setLongitudCampoClientes(String longitudCampoClientes) {
		this.longitudCampoClientes = longitudCampoClientes;
	}

	/**
	 * @return the indicadorHayRegistros
	 */
	public String getIndicadorHayRegistros() {
		return indicadorHayRegistros;
	}

	/**
	 * @param indicadorHayRegistros the indicadorHayRegistros to set
	 */
	public void setIndicadorHayRegistros(String indicadorHayRegistros) {
		this.indicadorHayRegistros = indicadorHayRegistros;
	}

	/**
	 * @return the fechaPagoD
	 */
	public Date getFechaPagoD() {
		return fechaPagoD;
	}

	/**
	 * @param fechaPagoD the fechaPagoD to set
	 */
	public void setFechaPagoD(Date fechaPagoD) {
		this.fechaPagoD = fechaPagoD;
	}

	/**
	 * @return the codigoConsultora
	 */
	public String getCodigoConsultora() {
		return codigoConsultora;
	}

	/**
	 * @param codigoConsultora the codigoConsultora to set
	 */
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}

	/**
	 * @return the nombreConsultora
	 */
	public String getNombreConsultora() {
		return nombreConsultora;
	}

	/**
	 * @param nombreConsultora the nombreConsultora to set
	 */
	public void setNombreConsultora(String nombreConsultora) {
		this.nombreConsultora = nombreConsultora;
	}

	/**
	 * @return the importePago
	 */
	public String getImportePago() {
		return importePago;
	}

	/**
	 * @param importePago the importePago to set
	 */
	public void setImportePago(String importePago) {
		this.importePago = importePago;
	}
	
	
}