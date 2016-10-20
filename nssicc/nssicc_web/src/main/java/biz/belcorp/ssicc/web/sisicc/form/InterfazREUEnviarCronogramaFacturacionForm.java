/*
 * Created on 26/09/2006 09:42:55 AM
 * biz.belcorp.ssicc.sisicc.web.form.InterfazREUEnviarCronogramaFacturacionForm
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazREUEnviarCronogramaFacturacionForm.java.html"> <i>View Source </i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ram�rez </a>�
 * 
 * @struts.form name = "interfazREUEnviarCronogramaFacturacionForm" extends =
 *              "baseInterfazForm"
 */

public class InterfazREUEnviarCronogramaFacturacionForm extends BaseInterfazForm
        implements Serializable {

  
	private static final long serialVersionUID = 558574597355913007L;

	private String codigoMarca;

    private String codigoCanal;

    private String periodoDesde;

    private String periodoHasta;

    private String codigoActividad;

    public String getCodigoActividad() {
        return codigoActividad;
    }

    public void setCodigoActividad(String codigoActividad) {
        this.codigoActividad = codigoActividad;
    }

    public String getCodigoCanal() {
        return codigoCanal;
    }
   
    public void setCodigoCanal(String codigoCanal) {
        this.codigoCanal = codigoCanal;
    }

    public String getCodigoMarca() {
        return codigoMarca;
    }
   
    public void setCodigoMarca(String codigoMarca) {
        this.codigoMarca = codigoMarca;
    }

    public String getPeriodoDesde() {
        return periodoDesde;
    }
   
    public void setPeriodoDesde(String periodoDesde) {
        this.periodoDesde = periodoDesde;
    }
   
    public String getPeriodoHasta() {
        return periodoHasta;
    }
   
    public void setPeriodoHasta(String periodoHasta) {
        this.periodoHasta = periodoHasta;
    }
   
}
