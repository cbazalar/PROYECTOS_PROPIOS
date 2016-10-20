package biz.belcorp.ssicc.dao.spusicc.sto.model;

import java.io.Serializable;

/**
 * @author <a href="mailto:nlopez@csigcomt.com">Nicols Lpez</a>
 *
 */
public class DetalleOferta implements Serializable {

	private static final long serialVersionUID = 1L;

	private String oidSoliCabe;
	private String oidProd;
	private String oidOfer;
	
	/**
	 * @return oidSoliCabe
	 */
	public String getOidSoliCabe() {
		return oidSoliCabe;
	}
	
	/**
	 * @param oidSoliCabe
	 */
	public void setOidSoliCabe(String oidSoliCabe) {
		this.oidSoliCabe = oidSoliCabe;
	}
	
	/**
	 * @return oidProd
	 */ 
	public String getOidProd() {
		return oidProd;
	}
	
	/**
	 * @param oidProd
	 */
	public void setOidProd(String oidProd) {
		this.oidProd = oidProd;
	}
	
	/**
	 * @return oidOfer
	 */
	public String getOidOfer() {
		return oidOfer;
	}
	
	/**
	 * @param oidOfer
	 */
	public void setOidOfer(String oidOfer) {
		this.oidOfer = oidOfer;
	}
	
}
