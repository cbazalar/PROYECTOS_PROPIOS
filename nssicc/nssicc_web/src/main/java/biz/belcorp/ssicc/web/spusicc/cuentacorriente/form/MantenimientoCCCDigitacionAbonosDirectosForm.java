package biz.belcorp.ssicc.web.spusicc.cuentacorriente.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoCCCDigitacionAbonosDirectosForm extends BaseSearchForm implements Serializable
{

	private static final long serialVersionUID = 1L;

	private String codigoPais;	
	private String tipoAbonoDirecto;		
    
	private String[] listaCodigoConsultora;	
	private String[] listaDocumentoIdentidadConsultora;
	private String[] listaNombreConsultora;
	private String[] listaObservaciones;	
	private String[] listaImporteCAD;
	
	private String[] selectedItemsDelete;
	private String longitudCampoClientes;
	private String tipoDocumentoIdentidadConsultora;
	private String longitudDocumentoIdentidadConsultora;	
	private String indicadorHayRegistros;
	
	private String codigoConsultora;
	private String documentoIdentidadConsultora;
	private String importeCAD;
	private String observaciones;
	private String nombreConsultora;
	private Boolean soloLectura;
		
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

						
	/**
	 * @return the tipoAbonoDirecto  	
	 */
	public String getTipoAbonoDirecto() {
		return tipoAbonoDirecto;
	}

	/**
	 * @param cccTiposCargoAbonosDirectosList the tipoAbonoDirecto to set
	 * 				The tipoAbonoDirecto to set.	 
	 * @struts.validator type = "required"
	 */
	public void setTipoAbonoDirecto(String tipoAbonoDirecto) {
		this.tipoAbonoDirecto = tipoAbonoDirecto;
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
	 * @return the listaObservaciones
	 */
	public String[] getListaObservaciones() {
		return listaObservaciones;
	}

	/**
	 * @param listaObservaciones the listaObservaciones to set
	 */
	public void setListaObservaciones(String[] listaObservaciones) {
		this.listaObservaciones = listaObservaciones;
	}
	
	/**
	 * @return the listaImporteCAD
	 */
	public String[] getListaImporteCAD() {
		return listaImporteCAD;
	}

	/**
	 * @param listaImporteCAD the listaImporteCAD to set
	 */
	public void setListaImporteCAD(String[] listaImporteCAD) {
		this.listaImporteCAD = listaImporteCAD;
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
	 * @return the tipoDocumentoIdentidadConsultora
	 */
	public String getTipoDocumentoIdentidadConsultora() {
		return tipoDocumentoIdentidadConsultora;
	}

	/**
	 * @param tipoDocumentoIdentidadConsultora the tipoDocumentoIdentidadConsultora to set
	 */
	public void setTipoDocumentoIdentidadConsultora(String tipoDocumentoIdentidadConsultora) {
		this.tipoDocumentoIdentidadConsultora = tipoDocumentoIdentidadConsultora;
	}

	/**
	 * @return the longitudDocumentoIdentidadConsultora
	 */
	public String getLongitudDocumentoIdentidadConsultora() {
		return longitudDocumentoIdentidadConsultora;
	}

	/**
	 * @param longitudDocumentoIdentidadConsultora the longitudDocumentoIdentidadConsultora to set
	 */
	public void setLongitudDocumentoIdentidadConsultora(String longitudDocumentoIdentidadConsultora) {
		this.longitudDocumentoIdentidadConsultora = longitudDocumentoIdentidadConsultora;
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

	public String getCodigoConsultora() {
		return codigoConsultora;
	}

	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}

	public String getDocumentoIdentidadConsultora() {
		return documentoIdentidadConsultora;
	}

	public void setDocumentoIdentidadConsultora(String documentoIdentidadConsultora) {
		this.documentoIdentidadConsultora = documentoIdentidadConsultora;
	}

	public String getImporteCAD() {
		return importeCAD;
	}

	public void setImporteCAD(String importeCAD) {
		this.importeCAD = importeCAD;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getNombreConsultora() {
		return nombreConsultora;
	}

	public void setNombreConsultora(String nombreConsultora) {
		this.nombreConsultora = nombreConsultora;
	}

	public Boolean getSoloLectura() {
		return soloLectura;
	}

	public void setSoloLectura(Boolean soloLectura) {
		this.soloLectura = soloLectura;
	}
}