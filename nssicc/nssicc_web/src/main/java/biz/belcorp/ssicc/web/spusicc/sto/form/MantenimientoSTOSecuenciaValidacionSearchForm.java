package biz.belcorp.ssicc.web.spusicc.sto.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoSTOSecuenciaValidacionSearchForm extends BaseSearchForm implements Serializable{	
	
	private static final long serialVersionUID = 2797144302052545879L;
	
	private String codigoPais;
    private String codigoDocumento;
    private String[] listaSecuenciaValidacionSecuenciaNuevo= {};
    private String[] listaSecuenciaValidacionSecuenciaOriginal= {};
    private String[] listaSecuenciaValidacionEstado= {};
    
	
	
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	
	/**
	 * @param codigoPais The codigoPais to set.
	 * @struts.validator type = "required" 
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	/**
	 * @return Returns the codigoDocumento.
	 */
	public String getCodigoDocumento() {
		return codigoDocumento;
	}

	/**
	 * @param codigoDocumento The codigoDocumento to set. 
	 */
	public void setCodigoDocumento(String codigoDocumento) {
		this.codigoDocumento = codigoDocumento;
	}



	/**
	 * @return the listaSecuenciaValidacionSecuenciaNuevo
	 */
	public String[] getListaSecuenciaValidacionSecuenciaNuevo() {
		return listaSecuenciaValidacionSecuenciaNuevo;
	}

	/**
	 * @param listaSecuenciaValidacionSecuenciaNuevo the listaSecuenciaValidacionSecuenciaNuevo to set
	 */
	public void setListaSecuenciaValidacionSecuenciaNuevo(
			String[] listaSecuenciaValidacionSecuenciaNuevo) {
		this.listaSecuenciaValidacionSecuenciaNuevo = listaSecuenciaValidacionSecuenciaNuevo;
	}

	/**
	 * @return the listaSecuenciaValidacionSecuenciaOriginal
	 */
	public String[] getListaSecuenciaValidacionSecuenciaOriginal() {
		return listaSecuenciaValidacionSecuenciaOriginal;
	}

	/**
	 * @param listaSecuenciaValidacionSecuenciaOriginal the listaSecuenciaValidacionSecuenciaOriginal to set
	 */
	public void setListaSecuenciaValidacionSecuenciaOriginal(
			String[] listaSecuenciaValidacionSecuenciaOriginal) {
		this.listaSecuenciaValidacionSecuenciaOriginal = listaSecuenciaValidacionSecuenciaOriginal;
	}

	/**
	 * @return Returns the listaSecuenciaValidacionEstado.
	 */
	public String[] getListaSecuenciaValidacionEstado() {
		return listaSecuenciaValidacionEstado;
	}

	/**
	 * @param listaSecuenciaValidacionEstado The listaSecuenciaValidacionEstado to set. 
	 */
	public void setListaSecuenciaValidacionEstado(
			String[] listaSecuenciaValidacionEstado) {
		this.listaSecuenciaValidacionEstado = listaSecuenciaValidacionEstado;
	}


}
