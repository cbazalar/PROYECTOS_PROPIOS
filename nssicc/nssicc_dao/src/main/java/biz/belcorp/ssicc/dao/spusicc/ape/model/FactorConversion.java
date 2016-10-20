package biz.belcorp.ssicc.dao.spusicc.ape.model;

import java.io.Serializable;

/**
 * @author Nicolas Lopez
 */

public class FactorConversion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8981809223942916650L;

	private String codigoMagn;
	
	private String codigoUnidMediOrig;
	
	private String codigoUnidMediDest;
	
	private String factor;
	
	private String oidFactorConv;

	/**
	 * @return codigoMagn
	 */
	public String getCodigoMagn() {
		return codigoMagn;
	}

	/**
	 * @param codigoMagn
	 */
	public void setCodigoMagn(String codigoMagn) {
		this.codigoMagn = codigoMagn;
	}

	/**
	 * @return codigoUnidMediOrig
	 */
	public String getCodigoUnidMediOrig() {
		return codigoUnidMediOrig;
	}

	/**
	 * @param codigoUnidMediOrig
	 */
	public void setCodigoUnidMediOrig(String codigoUnidMediOrig) {
		this.codigoUnidMediOrig = codigoUnidMediOrig;
	}

	/**
	 * @return codigoUnidMediDest
	 */
	public String getCodigoUnidMediDest() {
		return codigoUnidMediDest;
	}

	/**
	 * @param codigoUnidMediDest
	 */
	public void setCodigoUnidMediDest(String codigoUnidMediDest) {
		this.codigoUnidMediDest = codigoUnidMediDest;
	}

	/**
	 * @return factor
	 */
	public String getFactor() {
		return factor;
	}

	/**
	 * @param factor
	 */
	public void setFactor(String factor) {
		this.factor = factor;
	}

	/**
	 * @return oidFactorConv
	 */
	public String getOidFactorConv() {
		return oidFactorConv;
	}

	/**
	 * @param oidFactorConv
	 */
	public void setOidFactorConv(String oidFactorConv) {
		this.oidFactorConv = oidFactorConv;
	}
	
}