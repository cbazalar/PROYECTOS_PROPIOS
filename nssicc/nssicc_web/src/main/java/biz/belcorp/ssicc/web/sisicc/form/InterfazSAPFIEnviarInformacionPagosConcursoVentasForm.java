package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;


public class InterfazSAPFIEnviarInformacionPagosConcursoVentasForm extends BaseInterfazForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoPeriodo;
	
	private String fechaFacturacion;
	private Date fechaFacturacionDate;
	
	private String habilitadorHidden;
	
	private String tipoEnvio;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.framework.form.BaseInterfazForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset() {
		setNumeroLote("");
        setHabilitadorHidden("");
        setTipoEnvio("");
	}

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @return the fechaFacturacion
	 */
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}

	/**
	 * @return the habilitadorHidden
	 */
	public String getHabilitadorHidden() {
		return habilitadorHidden;
	}

	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @param fechaFacturacion the fechaFacturacion to set
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

	/**
	 * @param habilitadorHidden the habilitadorHidden to set
	 */
	public void setHabilitadorHidden(String habilitadorHidden) {
		this.habilitadorHidden = habilitadorHidden;
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
	 * @return the fechaFacturacionDate
	 */
	public Date getFechaFacturacionDate() {
		return fechaFacturacionDate;
	}

	/**
	 * @param fechaFacturacionDate the fechaFacturacionDate to set
	 */
	public void setFechaFacturacionDate(Date fechaFacturacionDate) {
		this.fechaFacturacionDate = fechaFacturacionDate;
	}
	
}

