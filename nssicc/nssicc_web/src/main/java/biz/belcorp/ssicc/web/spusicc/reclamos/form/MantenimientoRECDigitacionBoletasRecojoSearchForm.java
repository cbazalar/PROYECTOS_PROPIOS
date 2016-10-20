package biz.belcorp.ssicc.web.spusicc.reclamos.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoRECDigitacionBoletasRecojoSearchForm extends BaseSearchForm{

	private static final long serialVersionUID = -398366034650868184L;
	
	
	private String codigoPais;

	private String codigoMarca;
	
	private String codigoCanal;

	private String numeroBoleta;

	private String codigoCliente;

	private String [] regionList;
	
	private String []zonaList;

	private String []resultadoList;

	private String []estadoList;

	private String codigoPeriodo;

	
	public String getCodigoPais() {
		return codigoPais;
	}
	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	public String getCodigoMarca() {
		return codigoMarca;
	}
	
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	public String getCodigoCanal() {
		return codigoCanal;
	}
	
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	public String getNumeroBoleta() {
		return numeroBoleta;
	}

	public void setNumeroBoleta(String numeroBoleta) {
		this.numeroBoleta = numeroBoleta;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
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

	public String[] getResultadoList() {
		return resultadoList;
	}

	public void setResultadoList(String[] resultadoList) {
		this.resultadoList = resultadoList;
	}

	public String[] getEstadoList() {
		return estadoList;
	}

	public void setEstadoList(String[] estadoList) {
		this.estadoList = estadoList;
	}

	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}
