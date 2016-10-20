package biz.belcorp.ssicc.web.spusicc.reclamos.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;


/**
 * The Class BusquedaRECCodigoVentaPedidoForm.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 06/12/2013
 */
public class MantenimientoRECPedidosExpressPremiosBloqueadosForm extends BaseSearchForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoPeriodo;
	private String codigoPeriodoSel;
	private String concurso;
	private String premio;
	private String region;
	private String numeroFilas;
	private String checkAll;
	private String valFlag="1";

		
	/* (non-Javadoc)
	 * @see org.apache.struts.validator.ValidatorForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset() {		
		this.concurso="";
		this.premio="";
		this.region="";
		this.numeroFilas=null;
	}


	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}


	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}


	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	public String getCodigoPeriodoSel() {
		return codigoPeriodoSel;
	}


	public void setCodigoPeriodoSel(String codigoPeriodoSel) {
		this.codigoPeriodoSel = codigoPeriodoSel;
	}


	public String getConcurso() {
		return concurso;
	}


	public void setConcurso(String concurso) {
		this.concurso = concurso;
	}


	public String getPremio() {
		return premio;
	}


	public void setPremio(String premio) {
		this.premio = premio;
	}


	public String getRegion() {
		return region;
	}


	public void setRegion(String region) {
		this.region = region;
	}


	public String getNumeroFilas() {
		return numeroFilas;
	}


	public void setNumeroFilas(String numeroFilas) {
		this.numeroFilas = numeroFilas;
	}


	public String getCheckAll() {
		return checkAll;
	}


	public void setCheckAll(String checkAll) {
		this.checkAll = checkAll;
	}


	/**
	 * @return the valFlag
	 */
	public String getValFlag() {
		return valFlag;
	}


	/**
	 * @param valFlag the valFlag to set
	 */
	public void setValFlag(String valFlag) {
		this.valFlag = valFlag;
	}
	
}
