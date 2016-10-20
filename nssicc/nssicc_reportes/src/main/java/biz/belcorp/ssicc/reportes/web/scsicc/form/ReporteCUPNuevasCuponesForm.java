package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteCUPNuevasCuponesForm extends BaseReporteForm implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private  String codigoPais;
	private String codigoPeriodoInicial;	
	private String codigoPeriodoFinal;
	
	
	public String getCodigoPais() {
		return codigoPais;
	}
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	public String getCodigoPeriodoInicial() {
		return codigoPeriodoInicial;
	}
	public void setCodigoPeriodoInicial(String codigoPeriodoInicial) {
		this.codigoPeriodoInicial = codigoPeriodoInicial;
	}
	public String getCodigoPeriodoFinal() {
		return codigoPeriodoFinal;
	}
	public void setCodigoPeriodoFinal(String codigoPeriodoFinal) {
		this.codigoPeriodoFinal = codigoPeriodoFinal;
	}
}
