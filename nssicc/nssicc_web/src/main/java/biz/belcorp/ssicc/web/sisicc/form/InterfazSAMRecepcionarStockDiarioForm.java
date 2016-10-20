package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * <p>
 * <a href="InterfazSAMRecepcionarStockDiarioForm.java.html"> <i>View Source</i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 * @struts.form name = "interfazSAMRecepcionarStockDiarioForm" extends = "baseInterfazForm"
 */
public class InterfazSAMRecepcionarStockDiarioForm extends BaseInterfazForm	implements Serializable {

	private static final long serialVersionUID = 1L;

	private String numLoteSTO;	
	private String tipoCarga;
	private String indicadorPROL;
	private String codigoPeriodo;
	private String codigoBatch;


	/**
	 * @return Returns the numLoteSTO.
	 */
	public String getNumLoteSTO() {
		return numLoteSTO;
	}

	/**
	 * @param numLoteSTO The numLoteSTO to set.
	 */
	public void setNumLoteSTO(String numLoteSTO) {
		this.numLoteSTO = numLoteSTO;
	}

	/**
	 * @return the tipoCarga
	 */
	public String getTipoCarga() {
		return tipoCarga;
	}

	/**
	 * @param tipoCarga the tipoCarga to set
	 */
	public void setTipoCarga(String tipoCarga) {
		this.tipoCarga = tipoCarga;
	}

	/**
	 * @return the indicadorPROL
	 */
	public String getIndicadorPROL() {
		return indicadorPROL;
	}

	/**
	 * @param indicadorPROL the indicadorPROL to set
	 */
	public void setIndicadorPROL(String indicadorPROL) {
		this.indicadorPROL = indicadorPROL;
	}

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the codigoBatch
	 */
	public String getCodigoBatch() {
		return codigoBatch;
	}

	/**
	 * @param codigoBatch the codigoBatch to set
	 */
	public void setCodigoBatch(String codigoBatch) {
		this.codigoBatch = codigoBatch;
	}

}