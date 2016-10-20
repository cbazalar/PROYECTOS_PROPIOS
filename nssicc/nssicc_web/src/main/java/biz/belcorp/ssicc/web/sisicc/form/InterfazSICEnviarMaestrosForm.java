/*
 * Created on 11-jul-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * <p>
 * <a href="InterfazSICEnviarMaestrosForm.java.html"> <i>View Source</i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 * 
 * @struts.form name = "interfazSICEnviarMaestrosForm" extends =
 *              "baseInterfazForm"
 */
public class InterfazSICEnviarMaestrosForm extends BaseInterfazForm
		implements Serializable {

	private String codigoMarca;

	private String codigoCanal;

	private String codigoPeriodo;

	private String codigoPais;
	
	private String codigoTipoCliente;
	
	private String []codigoSubTipoCliente;
	
	private String []codigoTipoClasificacion;
	
	private String []codigoClasificacion;
	
	private String codigoTipoVinculo;
	
	private String codigoConcurso;
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
		this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
		this.codigoTipoCliente = Constants.CODIGO_TIPO_CLIENTE_DEFAULT;
		this.codigoTipoVinculo = Constants.CODIGO_TIPO_VINCULO_DEFAULT;

	}


	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @struts.validator type = "required"
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @struts.validator type = "required"
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @struts.validator type = "required"
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

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
	 * @return Returns the codigoClasificacion.
	 */
	public String[] getCodigoClasificacion() {
		return codigoClasificacion;
	}


	/**
	 * @param codigoClasificacion The codigoClasificacion to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoClasificacion(String[] codigoClasificacion) {
		this.codigoClasificacion = codigoClasificacion;
	}


	/**
	 * @return Returns the codigoSubTipoCliente.
	 * 
	 */
	public String[] getCodigoSubTipoCliente() {
		return codigoSubTipoCliente;
	}


	/**
	 * @param codigoSubTipoCliente The codigoSubTipoCliente to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoSubTipoCliente(String[] codigoSubTipoCliente) {
		this.codigoSubTipoCliente = codigoSubTipoCliente;
	}


	/**
	 * @return Returns the codigoTipoClasificacion.
	 */
	public String[] getCodigoTipoClasificacion() {
		return codigoTipoClasificacion;
	}


	/**
	 * @param codigoTipoClasificacion The codigoTipoClasificacion to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoTipoClasificacion(String[] codigoTipoClasificacion) {
		this.codigoTipoClasificacion = codigoTipoClasificacion;
	}


	/**
	 * @return Returns the codigoTipoCliente.
	 */
	public String getCodigoTipoCliente() {
		return codigoTipoCliente;
	}


	/**
	 * @param codigoTipoCliente The codigoTipoCliente to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoTipoCliente(String codigoTipoCliente) {
		this.codigoTipoCliente = codigoTipoCliente;
	}


	/**
	 * @return Returns the codigoTipoVinculo.
	 */
	public String getCodigoTipoVinculo() {
		return codigoTipoVinculo;
	}


	/**
	 * @param codigoTipoVinculo The codigoTipoVinculo to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoTipoVinculo(String codigoTipoVinculo) {
		this.codigoTipoVinculo = codigoTipoVinculo;
	}
	
	/**
	 * @return Returns the codigoConcurso.
	 */
	public String getCodigoConcurso() {
		return codigoConcurso;
	}


	/**
	 * @param codigoConcurso The codigoConcurso to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoConcurso(String codigoConcurso) {
		this.codigoConcurso = codigoConcurso;
	}
}