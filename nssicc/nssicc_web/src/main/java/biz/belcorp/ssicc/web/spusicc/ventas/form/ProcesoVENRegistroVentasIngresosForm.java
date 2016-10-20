package biz.belcorp.ssicc.web.spusicc.ventas.form;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

/**
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 * 
 */
public class ProcesoVENRegistroVentasIngresosForm extends BaseProcesoForm {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1411921958889035901L;
	private String codigoPais;
	private String codigoPeriodoInformar;
	private String codigoPeriodoEnviar;
	private String accion;
	private String formatoExportacion;
	private String tipoReporteAMostrar;
	
	

	/**
	 * @return the formatoExportacion
	 */
	public String getFormatoExportacion() {
		return formatoExportacion;
	}


	/**
	 * @param formatoExportacion the formatoExportacion to set
	 */
	public void setFormatoExportacion(String formatoExportacion) {
		this.formatoExportacion = formatoExportacion;
	}


	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type="required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigoPeriodoInformar
	 */
	public String getCodigoPeriodoInformar() {
		return codigoPeriodoInformar;
	}

	/**
	 * @param codigoPeriodoInformar the codigoPeriodoInformar to set
	 * @struts.validator type="required"
	 */
	public void setCodigoPeriodoInformar(String codigoPeriodoInformar) {
		this.codigoPeriodoInformar = codigoPeriodoInformar;
	}

	/**
	 * @return the codigoPeriodoEnviar
	 */
	public String getCodigoPeriodoEnviar() {
		return codigoPeriodoEnviar;
	}

	/**
	 * @param codigoPeriodoEnviar the codigoPeriodoEnviar to set
	 * @struts.validator type="required"
	 */
	public void setCodigoPeriodoEnviar(String codigoPeriodoEnviar) {
		this.codigoPeriodoEnviar = codigoPeriodoEnviar;
	}

	/**
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}

	/**
	 * @param accion the accion to set
	 * @struts.validator type="required"
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}

	/**
	 * @return the tipoReporteAMostrar
	 */
	public String getTipoReporteAMostrar() {
		return tipoReporteAMostrar;
	}

	/**
	 * tipoReporteAMostrar the tipoReporteAMostrar to set
	 * @param tipoReporteAMostrar
	 */
	public void setTipoReporteAMostrar(String tipoReporteAMostrar) {
		this.tipoReporteAMostrar = tipoReporteAMostrar;
	}
	
	

}