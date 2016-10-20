package biz.belcorp.ssicc.web.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoBASParametroPaisForm extends BaseEditForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoPais;
    private String codigoSistema;
    private String codigoParametro;
    private String nombreParametro;
    private String valorParametro;
    private String obsParametro;
    private String estado;
    
    
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		//this.listaZonas = null;
		this.codigoSistema = Constants.FORMATEAR_TODOS;
	}
	

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}


	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}


	public String getCodigoSistema() {
		return codigoSistema;
	}


	public void setCodigoSistema(String codigoSistema) {
		this.codigoSistema = codigoSistema;
	}

	
	public String getCodigoParametro() {
		return codigoParametro;
	}


	public void setCodigoParametro(String codigoParametro) {
		this.codigoParametro = codigoParametro;
	}


	public String getNombreParametro() {
		return nombreParametro;
	}


	public void setNombreParametro(String nombreParametro) {
		this.nombreParametro = nombreParametro;
	}


	public String getValorParametro() {
		return valorParametro;
	}


	public void setValorParametro(String valorParametro) {
		this.valorParametro = valorParametro;
	}


	public String getObsParametro() {
		return obsParametro;
	}


	public void setObsParametro(String obsParametro) {
		this.obsParametro = obsParametro;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}

}
