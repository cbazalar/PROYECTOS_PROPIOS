package biz.belcorp.ssicc.web.spusicc.cuentacorriente.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoCCCDigitacionPagosChequesForm extends BaseSearchForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPais;
	
	private String[] listaTipoDocumento;
	private String[] listaDocumentoIdentidadConsultora;
	private String[] listaCodigoConsultora;	
	private String[] listaNombreConsultora;	
	private String[] listaFechaCobro;
	private Date[] listaFechaCobroD;
	private String[] listaBanco;	
	private String[] listaImportePago;
	
	private String[] selectedItemsDelete;
	private String longitudCampoClientes;
	private String indicadorHayRegistros;
	private String strComboTipoDocumento;	
	private String strComboBanco;
	private String fechaHoy;
	
	
	private String tipoDocumento;
	private String documentoIdentidadConsultora;
	private String codigoConsultora;	
	private String nombreConsultora;	
	private String fechaCobro;
	private Date  fechaCobroD;
	private String banco;	
	private String importePago;
	
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
	 * @return the listaTipoDocumento
	 */
	public String[] getListaTipoDocumento() {
		return listaTipoDocumento;
	}

	/**
	 * @param listaTipoDocumento the listaTipoDocumento to set
	 */
	public void setListaTipoDocumento(String[] listaTipoDocumento) {
		this.listaTipoDocumento = listaTipoDocumento;
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
	 * @return the listaFechaCobro
	 */
	public String[] getListaFechaCobro() {
		return listaFechaCobro;
	}

	/**
	 * @param listaFechaCobro the listaFechaCobro to set
	 */
	public void setListaFechaCobro(String[] listaFechaCobro) {
		this.listaFechaCobro = listaFechaCobro;
	}

	/**
	 * @return the listaBanco
	 */
	public String[] getListaBanco() {
		return listaBanco;
	}

	/**
	 * @param listaBanco the listaBanco to set
	 */
	public void setListaBanco(String[] listaBanco) {
		this.listaBanco = listaBanco;
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
	 * @return the listaDocumentoIdentidadConsultora
	 */
	public String[] getListaDocumentoIdentidadConsultora() {
		return listaDocumentoIdentidadConsultora;
	}

	/**
	 * @param listaDocumentoIdentidadConsultora the listaDocumentoIdentidadConsultora to set
	 */
	public void setListaDocumentoIdentidadConsultora(
			String[] listaDocumentoIdentidadConsultora) {
		this.listaDocumentoIdentidadConsultora = listaDocumentoIdentidadConsultora;
	}
	
	/**
	 * @return the strComboTipoDocumento
	 */
	public String getStrComboTipoDocumento() {
		return strComboTipoDocumento;
	}

	/**
	 * @param strComboTipoDocumento the strComboTipoDocumento to set
	 */
	public void setStrComboTipoDocumento(String strComboTipoDocumento) {
		this.strComboTipoDocumento = strComboTipoDocumento;
	}

	/**
	 * @return the strComboBanco
	 */
	public String getStrComboBanco() {
		return strComboBanco;
	}

	/**
	 * @param strComboBanco the strComboBanco to set
	 */
	public void setStrComboBanco(String strComboBanco) {
		this.strComboBanco = strComboBanco;
	}

	/**
	 * @return the fechaHoy
	 */
	public String getFechaHoy() {
		return fechaHoy;
	}

	/**
	 * @param fechaHoy the fechaHoy to set
	 */
	public void setFechaHoy(String fechaHoy) {
		this.fechaHoy = fechaHoy;
	}

	/**
	 * @return the listaFechaCobroD
	 */
	public Date[] getListaFechaCobroD() {
		return listaFechaCobroD;
	}

	/**
	 * @param listaFechaCobroD the listaFechaCobroD to set
	 */
	public void setListaFechaCobroD(Date[] listaFechaCobroD) {
		this.listaFechaCobroD = listaFechaCobroD;
	}

	/**
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * @return the documentoIdentidadConsultora
	 */
	public String getDocumentoIdentidadConsultora() {
		return documentoIdentidadConsultora;
	}

	/**
	 * @param documentoIdentidadConsultora the documentoIdentidadConsultora to set
	 */
	public void setDocumentoIdentidadConsultora(String documentoIdentidadConsultora) {
		this.documentoIdentidadConsultora = documentoIdentidadConsultora;
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
	 * @return the fechaCobro
	 */
	public String getFechaCobro() {
		return fechaCobro;
	}

	/**
	 * @param fechaCobro the fechaCobro to set
	 */
	public void setFechaCobro(String fechaCobro) {
		this.fechaCobro = fechaCobro;
	}

	/**
	 * @return the fechaCobroD
	 */
	public Date getFechaCobroD() {
		return fechaCobroD;
	}

	/**
	 * @param fechaCobroD the fechaCobroD to set
	 */
	public void setFechaCobroD(Date fechaCobroD) {
		this.fechaCobroD = fechaCobroD;
	}

	/**
	 * @return the banco
	 */
	public String getBanco() {
		return banco;
	}

	/**
	 * @param banco the banco to set
	 */
	public void setBanco(String banco) {
		this.banco = banco;
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