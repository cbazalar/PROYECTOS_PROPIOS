package biz.belcorp.ssicc.web.sisicc.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;


/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazAPEEnviarEstimadosDistribucionForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:doliva@csigcomt.com">Dennys Oliva Iriarte</a>
 * 
 * @struts.form name = "interfazAPEEnviarEstimadosDistribucionForm" extends =
 *              "baseInterfazForm"
 */
public class InterfazAPEEnviarEstimadosDistribucionForm extends
        BaseInterfazForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * Holds value of property codigoPeriodo.
     */
    protected String codigoPeriodo;

    /**
     * @return the codigoPeriodo
     */
    public String getCodigoPeriodo() {
        return codigoPeriodo;
    }

    /**
     * @param codigoPeriodo the codigoPeriodo to set
     * @struts.validator type="required"
     */
    public void setCodigoPeriodo(String codigoPeriodo) {
        this.codigoPeriodo = codigoPeriodo;
    }
    
}
