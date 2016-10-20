/*
 * Created on 11-jul-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * <p>
 * <a href="InterfazRECEnviarTransferenciaBoletasRecojoForm.java.html"> <i>View Source</i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jos� A. Cairampoma</a>
 * 
 * @struts.form name = "interfazRECEnviarUnidadesAlmacenVirtualForm" extends =
 *              "baseInterfazForm"
 */
public class ReporteRECEnviarTransferenciaBoletasRecojoForm extends BaseReporteForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPais;
	
	protected String formatoExportacion; 
	
	protected String tipoReporte;	
	

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


	public String getFormatoExportacion() {
		return formatoExportacion;
	}

	public void setFormatoExportacion(String formatoExportacion) {
		this.formatoExportacion = formatoExportacion;
	}

	public String getTipoReporte() {
		return tipoReporte;
	}

	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	


	

	

}