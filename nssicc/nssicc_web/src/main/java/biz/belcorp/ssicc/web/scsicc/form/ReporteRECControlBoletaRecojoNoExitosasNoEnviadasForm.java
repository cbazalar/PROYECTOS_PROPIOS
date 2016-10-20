package biz.belcorp.ssicc.web.scsicc.form;


import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


public class ReporteRECControlBoletaRecojoNoExitosasNoEnviadasForm extends BaseReporteForm implements Serializable{
	
	private static final long serialVersionUID = -7201869075494112764L;
	

	private String codigoPais;

	private String codigoPeriodoInicial;

	private String[] regionList;
	
	private String[] zonaList;

	private String[] resultadoList;

	private String codigoPeriodoFinal;
	
	private String[] recojoList;	
	
	
	
	public String getCodigoPais() {
		return codigoPais;
	}
	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	public String[] getRegionList() {
		return regionList;
	}
	
	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}	
	
	public String getCodigoPeriodoFinal() {
		return codigoPeriodoFinal;
	}
	
	public void setCodigoPeriodoFinal(String codigoPeriodoFinal) {
		this.codigoPeriodoFinal = codigoPeriodoFinal;
	}
	
	public String getCodigoPeriodoInicial() {
		return codigoPeriodoInicial;
	}
	
	public void setCodigoPeriodoInicial(String codigoPeriodoInicial) {
		this.codigoPeriodoInicial = codigoPeriodoInicial;
	}
	
	public String[] getRecojoList() {
		return recojoList;
	}
	
	public void setRecojoList(String[] recojoList) {
		this.recojoList = recojoList;
	}
	
	public String[] getResultadoList() {
		return resultadoList;
	}
	
	public void setResultadoList(String[] resultadoList) {
		this.resultadoList = resultadoList;
	}
	
	public String[] getZonaList() {
		return zonaList;
	}
	
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}		
	
}
