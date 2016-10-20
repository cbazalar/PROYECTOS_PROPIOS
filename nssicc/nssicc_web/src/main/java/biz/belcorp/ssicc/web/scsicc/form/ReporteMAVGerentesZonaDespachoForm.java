package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;



/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="reporteMAVGerentesZonaDespachoForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:avillavicencio@csigcomt.com">AV</a>
 * 
 * @struts.form name = "reporteMAVGerentesZonaDespachoForm"
 */

public class ReporteMAVGerentesZonaDespachoForm extends BaseReporteForm  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	
	private String codigoMarca;

	private String codigoCanal;
	
	
	
	public void reset() {
		this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
		this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
	}
	
	
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * 
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * 
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}
	
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

	public void setFormatoExportacion(String formatoExportacion) {
		// TODO Auto-generated method stub
		
	}	
	
}
