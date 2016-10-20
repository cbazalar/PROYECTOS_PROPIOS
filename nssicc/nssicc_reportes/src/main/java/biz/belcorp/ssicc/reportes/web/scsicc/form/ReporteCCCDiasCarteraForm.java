package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteCCCDiasCarteraForm extends BaseReporteForm implements Serializable {

	
	private static final long serialVersionUID = 7425705113499586436L;
	
	
	private String codigoPais;
	private String codigoSociedad;
	private String codigoOpcion;
	private String anyoInicial;
	private String mesInicial;
	private String anyoFinal;
	private String mesFinal;	
	private String codigoGrupoMeses;
	
		
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
	
	public String getCodigoOpcion() {
		return codigoOpcion;
	}
	
	public void setCodigoOpcion(String codigoOpcion) {
		this.codigoOpcion = codigoOpcion;
	}
	
	public String getCodigoGrupoMeses() {
		return codigoGrupoMeses;
	}
	
	public void setCodigoGrupoMeses(String codigoGrupoMeses) {
		this.codigoGrupoMeses = codigoGrupoMeses;
	}
	
	public String getAnyoInicial() {
		return anyoInicial;
	}
	
	public void setAnyoInicial(String anyoInicial) {
		this.anyoInicial = anyoInicial;
	}
	
	public String getMesInicial() {
		return mesInicial;
	}
	
	public void setMesInicial(String mesInicial) {
		this.mesInicial = mesInicial;
	}
	
	public String getAnyoFinal() {
		return anyoFinal;
	}
	
	public void setAnyoFinal(String anyoFinal) {
		this.anyoFinal = anyoFinal;
	}
	
	public String getMesFinal() {
		return mesFinal;
	}
	
	public void setMesFinal(String mesFinal) {
		this.mesFinal = mesFinal;
	}		
}
