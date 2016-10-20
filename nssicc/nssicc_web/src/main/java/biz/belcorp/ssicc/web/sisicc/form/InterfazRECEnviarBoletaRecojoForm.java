/*
 * Created on 26/09/2006 11:32:52 AM
 * biz.belcorp.ssicc.sisicc.web.form.InterfazMYEEnviarMovimientosCuentaCorrienteForm
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * 
 * @author <a href="">Leonardo Lizana</a>
 */
public class InterfazRECEnviarBoletaRecojoForm extends
        BaseInterfazForm implements Serializable {

	private static final long serialVersionUID = -7990664002399349745L;

	private String codigoMarca;
	
	private String codigoCanal;
	
	private String codigoPeriodo;
    /**
     * Default Constructor
     */
    public InterfazRECEnviarBoletaRecojoForm() {
		this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
	    this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;	
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
	

    
    
}
