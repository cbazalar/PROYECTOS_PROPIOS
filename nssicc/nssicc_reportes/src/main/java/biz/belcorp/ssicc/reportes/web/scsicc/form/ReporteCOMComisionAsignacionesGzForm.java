package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
public class ReporteCOMComisionAsignacionesGzForm extends BaseReporteForm implements Serializable {

	private static final long serialVersionUID = -6868615769033366870L;

	private String codigoPais;
	
	private String descPais;

	private String codigoPeriodo;

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getDescPais() {
		return descPais;
	}

	public void setDescPais(String descPais) {
		this.descPais = descPais;
	}

	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	
	
}
