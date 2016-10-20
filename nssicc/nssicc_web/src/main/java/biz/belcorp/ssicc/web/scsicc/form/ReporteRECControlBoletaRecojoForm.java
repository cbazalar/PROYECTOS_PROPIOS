package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteRECControlBoletaRecojoForm extends BaseReporteForm implements Serializable {
	

	private static final long serialVersionUID = 2507531683235403471L;	

	private String codigoPais;

	private String codigoPeriodoInicial;

	private String[] regionList;

	private String resultadoList;

	private String descripcionRegionList;

	private String descripconResultadoList;
	
	private String codigoPeriodoFinal;
	
	private String recojoList;	
	
	private String descripconRecojoList;
	

	/**
	 * 
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the regionList.
	 */
	public String[] getRegionList() {
		return regionList;
	}

	/**
	 * @param regionList
	 *            The regionList to set.
	 */
	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}
	
	/**
	 * @return Returns the descripcionRegionList.
	 */
	public String getDescripcionRegionList() {
		return descripcionRegionList;
	}
	/**
	 * @param descripcionRegionList The descripcionRegionList to set.
	 */
	public void setDescripcionRegionList(String descripcionRegionList) {
		String temp = StringUtils.replace(descripcionRegionList, "&&","\n" );
		this.descripcionRegionList = temp;
	}
	/**
	 * @return Returns the codigoPeriodoFinal.
	 */
	public String getCodigoPeriodoFinal() {
		return codigoPeriodoFinal;
	}
	/**
	 * @param codigoPeriodoFinal The codigoPeriodoFinal to set.
	 */
	public void setCodigoPeriodoFinal(String codigoPeriodoFinal) {
		this.codigoPeriodoFinal = codigoPeriodoFinal;
	}
	/**
	 * @return Returns the codigoPeriodoInicial.
	 */
	public String getCodigoPeriodoInicial() {
		return codigoPeriodoInicial;
	}
	/**
	 * @param codigoPeriodoInicial The codigoPeriodoInicial to set.
	 */
	public void setCodigoPeriodoInicial(String codigoPeriodoInicial) {
		this.codigoPeriodoInicial = codigoPeriodoInicial;
	}
	/**
	 * @return Returns the descripconRecojoList.
	 */
	public String getDescripconRecojoList() {
		return descripconRecojoList;
	}
	/**
	 * @param descripconRecojoList The descripconRecojoList to set.
	 */
	public void setDescripconRecojoList(String descripconRecojoList) {
		String temp = StringUtils.replace(descripconRecojoList, "&&","\n" );
		this.descripconRecojoList = temp;
	}
	/**
	 * @return Returns the descripconResultadoList.
	 */
	public String getDescripconResultadoList() {
		return descripconResultadoList;
	}
	/**
	 * @param descripconResultadoList The descripconResultadoList to set.
	 */
	public void setDescripconResultadoList(String descripconResultadoList) {
		String temp = StringUtils.replace(descripconResultadoList, "&&","\n" );
		this.descripconResultadoList = temp;
	}

	public String getResultadoList() {
		return resultadoList;
	}

	public void setResultadoList(String resultadoList) {
		this.resultadoList = resultadoList;
	}

	public String getRecojoList() {
		return recojoList;
	}

	public void setRecojoList(String recojoList) {
		this.recojoList = recojoList;
	}
	
	
	
}
