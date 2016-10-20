package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazPEJBancoCierreEtapaForm.java.html"><i>View Source </i></a>
 * </p>
 * 
 * @author <a href="mailto:jjrios@csigcomt.com">Jesse James Rios Franco</a>
 * 
 * @struts.form name = "interfazPEJBancoCierreEtapaForm" extends = "baseInterfazForm"
 */

public class InterfazPEJBancoCierreEtapaForm extends BaseInterfazForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String codigoMarca;
    private String codigoCanal;
    private String anioInicial;
	private String nroEtapa;    
    
    public InterfazPEJBancoCierreEtapaForm(){
    	super();
        this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
        this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
    }
    
	/**
	 * @return
	 */
	public String getAnioInicial() {
		return anioInicial;
	}

	/**
	 * @param anioInicial
	 * @struts.validator type = "required"
	 */
	public void setAnioInicial(String anioInicial) {
		this.anioInicial = anioInicial;
	}

	/**
	 * @return
	 */
	public String getNroEtapa() {
		return nroEtapa;
	}

	/**
	 * @param nroEtapa
	 * @struts.validator type = "required"
	 */
	public void setNroEtapa(String nroEtapa) {
		this.nroEtapa = nroEtapa;
	}

	/**
	 * @return
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca
	 * @struts.validator type = "required"
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	/**
	 * @return
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal
	 * @struts.validator type = "required"
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}
}