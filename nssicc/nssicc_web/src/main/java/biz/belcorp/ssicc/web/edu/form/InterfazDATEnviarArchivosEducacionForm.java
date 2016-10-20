package biz.belcorp.ssicc.web.edu.form;

import java.io.Serializable;
import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**           
 */
public class InterfazDATEnviarArchivosEducacionForm extends BaseInterfazForm
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3836684553352251001L;

	private String codigoPais;
	private String codigoEmpresa;
	private String codigoPeriodo;
	private String codigoRegion;
	private String tipoEnvio;
	private String indicadorSistema;
	private String indicadorCierre;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.
	 * ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public InterfazDATEnviarArchivosEducacionForm() {
		this.codigoPais = "";
		this.codigoEmpresa = "";
		this.codigoPeriodo = null;
		this.codigoRegion = null;
		this.tipoEnvio = Constants.EDU_TIPO_ENVIO_NORMAL;
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
	 * @return the codigoEmpresa
	 */
	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}

	/**
	 * @param codigoEmpresa the codigoEmpresa to set
	 */
	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
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
	 * @return the tipoEnvio
	 */
	public String getTipoEnvio() {
		return tipoEnvio;
	}

	/**
	 * @param tipoEnvio the tipoEnvio to set
	 */
	public void setTipoEnvio(String tipoEnvio) {
		this.tipoEnvio = tipoEnvio;
	}

	/**
	 * @return the indicadorSistema
	 */
	public String getIndicadorSistema() {
		return indicadorSistema;
	}

	/**
	 * @param indicadorSistema the indicadorSistema to set
	 */
	public void setIndicadorSistema(String indicadorSistema) {
		this.indicadorSistema = indicadorSistema;
	}

	/**
	 * @return the indicadorCierre
	 */
	public String getIndicadorCierre() {
		return indicadorCierre;
	}

	/**
	 * @param indicadorCierre the indicadorCierre to set
	 */
	public void setIndicadorCierre(String indicadorCierre) {
		this.indicadorCierre = indicadorCierre;
	}
	
	

}
