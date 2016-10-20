package biz.belcorp.ssicc.web.spusicc.mae.form;

import java.io.Serializable;

import org.apache.xmlbeans.impl.jam.mutable.MPackage;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.web.MPantallaPrincipalBean;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class BusquedaMAEPremiosRecomendacionForm extends
		BaseSearchForm implements Serializable {

	private static final long serialVersionUID = 1L;
	private String oidCliente;
	private String oidClienteRecomendante;
	private String oidPeriodo;
	private String oidPais;
	private String id;

	private String codigoClienteRecomendado;
	private String codigoClienteRecomendante;

	// indica si grabo correctamente el premio
	private boolean graboOK;

	private String foco;

	private boolean grabar;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.
	 * ActionMapping, javax.servlet.HttpServletRequest)
	 */
	public void reset() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'reset' method");
		}
	}

	public void inicializar() {

	}

	/**
	 * @return
	 */
	public String getOidCliente() {
		return oidCliente;
	}

	/**
	 * @param oidCliente
	 */
	public void setOidCliente(String oidCliente) {
		this.oidCliente = oidCliente;
	}

	/**
	 * @return
	 */
	public String getOidClienteRecomendante() {
		return oidClienteRecomendante;
	}

	/**
	 * @param oidClienteRecomendante
	 */
	public void setOidClienteRecomendante(String oidClienteRecomendante) {
		this.oidClienteRecomendante = oidClienteRecomendante;
	}

	/**
	 * @return
	 */
	public String getOidPeriodo() {
		return oidPeriodo;
	}

	/**
	 * @param oidPeriodo
	 */
	public void setOidPeriodo(String oidPeriodo) {
		this.oidPeriodo = oidPeriodo;
	}

	/**
	 * @return
	 */
	public String getOidPais() {
		return oidPais;
	}

	/**
	 * @param oidPais
	 */
	public void setOidPais(String oidPais) {
		this.oidPais = oidPais;
	}

	/**
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return
	 */
	public String getCodigoClienteRecomendado() {
		return codigoClienteRecomendado;
	}

	/**
	 * @param codigoClienteRecomendado
	 */
	public void setCodigoClienteRecomendado(String codigoClienteRecomendado) {
		this.codigoClienteRecomendado = codigoClienteRecomendado;
	}

	/**
	 * @return
	 */
	public String getCodigoClienteRecomendante() {
		return codigoClienteRecomendante;
	}

	/**
	 * @param codigoClienteRecomendante
	 */
	public void setCodigoClienteRecomendante(String codigoClienteRecomendante) {
		this.codigoClienteRecomendante = codigoClienteRecomendante;
	}

	/**
	 * @return Returns the graboOK.
	 */
	public boolean isGraboOK() {
		return graboOK;
	}

	/**
	 * @param graboOK
	 *            The graboOK to set.
	 */
	public void setGraboOK(boolean graboOK) {
		this.graboOK = graboOK;
	}

	/**
	 * @return Returns the foco.
	 */
	public String getFoco() {
		return foco;
	}

	/**
	 * @param foco
	 *            The foco to set.
	 */
	public void setFoco(String foco) {
		this.foco = foco;
	}

	/**
	 * @return the grabar
	 */
	public boolean isGrabar() {
		return grabar;
	}

	/**
	 * @param grabar
	 *            the grabar to set
	 */
	public void setGrabar(boolean grabar) {
		this.grabar = grabar;
	}

}