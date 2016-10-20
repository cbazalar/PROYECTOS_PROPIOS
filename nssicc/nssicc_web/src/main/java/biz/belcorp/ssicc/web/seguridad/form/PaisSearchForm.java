/*
 * Created on 03/05/2005 04:00:51 PM
 *
 * biz.belcorp.ssicc.web.form.PaisSearchForm
 */
package biz.belcorp.ssicc.web.seguridad.form;

import javax.validation.constraints.Size;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="PaisSearchForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a> 
 */
public class PaisSearchForm extends BaseSearchForm {
    
	private static final long serialVersionUID = 4750938785868109391L;

    protected String codigoPais;
   
    protected String descripcionPais;

    protected String estadoPais;

    public PaisSearchForm() {
    }

    @Size(max = 3)
    public String getCodigoPais() {
        return codigoPais;
    }
   
    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }
   
    @Size(max = 100)
    public String getDescripcionPais() {
        return descripcionPais;
    }
    
    public void setDescripcionPais(String descripcionPais) {
        this.descripcionPais = descripcionPais;
    }
   
    public String getEstadoPais() {
        return estadoPais;
    }
    
    public void setEstadoPais(String estadoPais) {
        this.estadoPais = estadoPais;
    }
}
