/*
 * Created on 07/05/2007 11:18:28 AM
 * biz.belcorp.ssicc.sisicc.web.ws.impl.InterfazResultado
 */
package biz.belcorp.ssicc.service.spusicc.mae.ws.beans;

import java.io.Serializable;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoMAEWebServiceResultado.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva</a>
 * 
 * 
 */
public class TipLogroLoveMAEWebService implements Serializable {

 
	private static final long serialVersionUID = 1L;
	private String codTipoLogro;    
    private String desTipoLogro;
    
    public TipLogroLoveMAEWebService(){
    	codTipoLogro=desTipoLogro="";
    }
    
	/**
	 * @return the codTipoLogro
	 */
	public String getCodTipoLogro() {
		return codTipoLogro;
	}
	/**
	 * @param codTipoLogro the codTipoLogro to set
	 */
	public void setCodTipoLogro(String codTipoLogro) {
		this.codTipoLogro = codTipoLogro;
	}
	/**
	 * @return the desTipoLogro
	 */
	public String getDesTipoLogro() {
		return desTipoLogro;
	}
	/**
	 * @param desTipoLogro the desTipoLogro to set
	 */
	public void setDesTipoLogro(String desTipoLogro) {
		this.desTipoLogro = desTipoLogro;
	}
    
    
	
}
