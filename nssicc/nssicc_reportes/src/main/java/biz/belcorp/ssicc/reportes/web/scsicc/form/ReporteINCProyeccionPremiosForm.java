package biz.belcorp.ssicc.reportes.web.scsicc.form;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
public class ReporteINCProyeccionPremiosForm extends BaseReporteForm {

	private static final long serialVersionUID = 6971480803332091785L;

	private String codigoPais;
	
	private String descPais;
	
	private String recalculo;

	private String[] regionList;

	private String[] zonaList;

	private String numeroConcurso;

	private String[] nivelConcursoList;

	private String[] subgerenciaList;

	private String[] totales;
	
	private String tipoReporte;

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

	public String getRecalculo() {
		return recalculo;
	}

	public void setRecalculo(String recalculo) {
		this.recalculo = recalculo;
	}

	public String[] getRegionList() {
		return regionList;
	}

	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}

	public String[] getZonaList() {
		return zonaList;
	}

	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}

	public String getNumeroConcurso() {
		return numeroConcurso;
	}

	public void setNumeroConcurso(String numeroConcurso) {
		this.numeroConcurso = numeroConcurso;
	}

	public String[] getNivelConcursoList() {
		return nivelConcursoList;
	}

	public void setNivelConcursoList(String[] nivelConcursoList) {
		this.nivelConcursoList = nivelConcursoList;
	}

	public String[] getSubgerenciaList() {
		return subgerenciaList;
	}

	public void setSubgerenciaList(String[] subgerenciaList) {
		this.subgerenciaList = subgerenciaList;
	}

	public String[] getTotales() {
		return totales;
	}

	public void setTotales(String[] totales) {
		this.totales = totales;
	}

	public String getTipoReporte() {
		return tipoReporte;
	}

	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}
	
	
}
