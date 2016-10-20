/*
 * Created on 07/05/2007 11:18:28 AM
 * biz.belcorp.ssicc.sisicc.web.ws.impl.InterfazResultado
 */
package biz.belcorp.ssicc.service.spusicc.mae.ws.beans;

import java.io.Serializable;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ComConsMAEWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva</a>
 * 
 * 
 */
public class ComConsMAEWebService implements Serializable {

 
	private static final long serialVersionUID = 1L;
	private String codigoTipoComun;    
    private String valTextComun;
    private String codTipoOperador;
    private String desTipoOperador;
    
    public ComConsMAEWebService(){
    	codigoTipoComun=valTextComun=codTipoOperador=desTipoOperador="";
    }
    
	/**
	 * @return the codigoTipoComun
	 */
	public String getCodigoTipoComun() {
		return codigoTipoComun;
	}
	/**
	 * @param codigoTipoComun the codigoTipoComun to set
	 */
	public void setCodigoTipoComun(String codigoTipoComun) {
		this.codigoTipoComun = codigoTipoComun;
	}
	/**
	 * @return the valTextComun
	 */
	public String getValTextComun() {
		return valTextComun;
	}
	/**
	 * @param valTextComun the valTextComun to set
	 */
	public void setValTextComun(String valTextComun) {
		this.valTextComun = valTextComun;
	}
	/**
	 * @return the codTipoOperador
	 */
	public String getCodTipoOperador() {
		return codTipoOperador;
	}
	/**
	 * @param codTipoOperador the codTipoOperador to set
	 */
	public void setCodTipoOperador(String codTipoOperador) {
		this.codTipoOperador = codTipoOperador;
	}
	/**
	 * @return the desTipoOperador
	 */
	public String getDesTipoOperador() {
		return desTipoOperador;
	}
	/**
	 * @param desTipoOperador the desTipoOperador to set
	 */
	public void setDesTipoOperador(String desTipoOperador) {
		this.desTipoOperador = desTipoOperador;
	}
 
}
