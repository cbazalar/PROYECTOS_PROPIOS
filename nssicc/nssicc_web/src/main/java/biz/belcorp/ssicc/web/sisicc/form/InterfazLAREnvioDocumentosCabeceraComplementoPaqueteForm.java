/*
 * Created on 26/09/2006 11:32:52 AM
 * biz.belcorp.ssicc.sisicc.web.form.InterfazMYEEnviarMovimientosCuentaCorrienteForm
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazLAREnvioDocumentosCabeceraComplementoPaqueteForm.java.html"> <i>View Source
 * </i> </a>
 * </p>
 * 
 * @author <a href="mailto:doliva@csigcomt.com">Dennys Oliva Iriarte</a>
 */
public class InterfazLAREnvioDocumentosCabeceraComplementoPaqueteForm extends
        BaseInterfazForm implements Serializable {

	private static final long serialVersionUID = -7990664002399349745L;

	 private String codigoPeriodo;
		private String codigoMarca;	
		private String codigoCanal;
		
		
    /**
     * Default Constructor
     */
    public InterfazLAREnvioDocumentosCabeceraComplementoPaqueteForm() {
        super();
        this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
        this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
    }

    
    public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

    
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the codigoMarca
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca the codigoMarca to set
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	/**
	 * @return the codigoCanal
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal the codigoCanal to set
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

    
    
    
}
