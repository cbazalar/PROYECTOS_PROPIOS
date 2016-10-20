/*
 * Created on 07/05/2007 11:18:28 AM
 * biz.belcorp.ssicc.sisicc.web.ws.impl.InterfazResultado
 */
package biz.belcorp.ssicc.service.spusicc.mae.ws.beans;

import java.io.Serializable;

/**
 * TODO Include class description here.
 * <p>
 * <a href="PrefComMAEWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva</a>
 * 
 * 
 */
public class PrefComMAEWebService implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codigoTipoPrefe;
	private String desTipoPreferen;
	private String ordenTipoPref;
	
	public PrefComMAEWebService(){
		codigoTipoPrefe=desTipoPreferen=ordenTipoPref="";
	}
	
	/**
	 * @return the codigoTipoPrefe
	 */
	public String getCodigoTipoPrefe() {
		return codigoTipoPrefe;
	}
	/**
	 * @param codigoTipoPrefe the codigoTipoPrefe to set
	 */
	public void setCodigoTipoPrefe(String codigoTipoPrefe) {
		this.codigoTipoPrefe = codigoTipoPrefe;
	}
	/**
	 * @return the desTipoPreferen
	 */
	public String getDesTipoPreferen() {
		return desTipoPreferen;
	}
	/**
	 * @param desTipoPreferen the desTipoPreferen to set
	 */
	public void setDesTipoPreferen(String desTipoPreferen) {
		this.desTipoPreferen = desTipoPreferen;
	}
	/**
	 * @return the ordenTipoPref
	 */
	public String getOrdenTipoPref() {
		return ordenTipoPref;
	}
	/**
	 * @param ordenTipoPref the ordenTipoPref to set
	 */
	public void setOrdenTipoPref(String ordenTipoPref) {
		this.ordenTipoPref = ordenTipoPref;
	}
	
	
}
