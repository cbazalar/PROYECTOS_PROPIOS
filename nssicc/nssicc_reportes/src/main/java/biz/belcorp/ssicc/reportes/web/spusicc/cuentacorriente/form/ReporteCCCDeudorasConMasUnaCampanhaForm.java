package biz.belcorp.ssicc.reportes.web.spusicc.cuentacorriente.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteCCCDeudorasConMasUnaCampanhaForm extends BaseReporteForm implements Serializable {

	private static final long serialVersionUID = -7739099406766238731L;
	
	

	private String codigoPais;
	
	private String codigoSociedad;

    private String codigoPeriodo;
    
    private String formatoExportacion;
	    
	
	public String getCodigoPais() {
		return codigoPais;
	}

	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	
	public String getCodigoSociedad() {
		return codigoSociedad;
	}

	
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}
	
		
	
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	
	public String getFormatoExportacion() {
		return formatoExportacion;
	}

	
	public void setFormatoExportacion(String formatoExportacion) {
		this.formatoExportacion = formatoExportacion;
	}
	
}
