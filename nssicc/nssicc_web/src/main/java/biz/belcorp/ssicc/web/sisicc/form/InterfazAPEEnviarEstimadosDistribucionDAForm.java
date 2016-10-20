package biz.belcorp.ssicc.web.sisicc.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;


/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazAPEEnviarEstimadosDistribucionDAForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto</a>
 * 
 * @struts.form name = "interfazAPEEnviarEstimadosDistribucionDAForm" extends =
 *              "baseInterfazForm"
 */
public class InterfazAPEEnviarEstimadosDistribucionDAForm extends
        BaseInterfazForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3691265417277432298L;

	/**
     * Holds value of property codigoPeriodo.
     */
    protected String codigoPeriodo;

    private String pedidosProyectados;
    
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

	/**
	 * @return the pedidosProyectados
	 */
	public String getPedidosProyectados() {
		return pedidosProyectados;
	}

	/**
	 * @param pedidosProyectados the pedidosProyectados to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setPedidosProyectados(String pedidosProyectados) {
		this.pedidosProyectados = pedidosProyectados;
	}
    
}
