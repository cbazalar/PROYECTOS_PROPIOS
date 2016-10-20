package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


public class ReporteRECConsolidadoCDRAprobadoForm extends BaseReporteForm implements Serializable{

	
	private static final long serialVersionUID = -9028709368801303134L;
	
	private String   codigoPais;
	private String[] regionList;
	private String[] zonaList;
	private String   descripcionRegionList;
	private String   descripcionZonaList;
	private String[] tipoCDRList;
	private String   descripcionTipoCDRList;	
	private String   codigoPeriodoInicial;
	private String   codigoPeriodoFinal;
	private String   fechaProcesoInicial;
	private String   fechaProcesoFinal;
	private Date fechaProcesoInicialD;
	private Date fechaProcesoFinalD;
	private String   tipoPeriodo;
	
	
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
	
	public String[] getZonaList() {
		return zonaList;
	}
	
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}
	
	public String getDescripcionRegionList() {		
		return descripcionRegionList;
	}
	
	public void setDescripcionRegionList(String descripcionRegionList) {
		String temp = StringUtils.replace(descripcionRegionList, "&&", "\n");
		this.descripcionRegionList = temp;
	}
	
	public String getDescripcionZonaList() {
		return descripcionZonaList;
	}
	
	public void setDescripcionZonaList(String descripcionZonaList) {		
		String temp = StringUtils.replace(descripcionZonaList, "&&", "\n");
		this.descripcionZonaList = temp;
	}
	
	public String[] getTipoCDRList() {
		return tipoCDRList;
	}
	
	public void setTipoCDRList(String[] tipoCDRList) {
		this.tipoCDRList = tipoCDRList;
	}
	
	public String getDescripcionTipoCDRList() {
		return descripcionTipoCDRList;
	}
	
	public void setDescripcionTipoCDRList(String descripcionTipoCDRList) {
		String temp = StringUtils.replace(descripcionTipoCDRList, "&&", "\n");
		this.descripcionTipoCDRList = temp;
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
	
	public String getFechaProcesoInicial() {
		return fechaProcesoInicial;
	}
	
	public void setFechaProcesoInicial(String fechaProcesoInicial) {
		this.fechaProcesoInicial = fechaProcesoInicial;
	}
	
	public String getFechaProcesoFinal() {
		return fechaProcesoFinal;
	}
	
	public void setFechaProcesoFinal(String fechaProcesoFinal) {
		this.fechaProcesoFinal = fechaProcesoFinal;
	}
	
	public String getTipoPeriodo() {
		return tipoPeriodo;
	}

	public void setTipoPeriodo(String tipoPeriodo) {
		this.tipoPeriodo = tipoPeriodo;
	}

	public Date getFechaProcesoInicialD() {
		return fechaProcesoInicialD;
	}

	public void setFechaProcesoInicialD(Date fechaProcesoInicialD) {
		this.fechaProcesoInicialD = fechaProcesoInicialD;
	}

	public Date getFechaProcesoFinalD() {
		return fechaProcesoFinalD;
	}

	public void setFechaProcesoFinalD(Date fechaProcesoFinalD) {
		this.fechaProcesoFinalD = fechaProcesoFinalD;
	}
	
	
}
