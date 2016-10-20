package biz.belcorp.ssicc.web.sisicc.form;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * The Class InterfazLAREnviarVisibilidadCronogramaFacturacionForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 02/12/2014
 */
public class InterfazLAREnviarVisibilidadCronogramaFacturacionForm extends BaseInterfazForm {

	private static final long serialVersionUID = 1L;
	private String codigoMarca;
    private String codigoCanal;
    private String codigoPeriodo;
    

    /**
     * Default constructor
     */
    public InterfazLAREnviarVisibilidadCronogramaFacturacionForm() {
        super();
        this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
        this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
    }

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

    public String getCodigoPeriodo() {
        return codigoPeriodo;
    }

    /**
     * @struts.validator type = "required"
     * @struts.validator type="mask"
     * @struts.validator-var name="mask" value="${campaign}"
     */
    public void setCodigoPeriodo(String codigoPeriodo) {
        this.codigoPeriodo = codigoPeriodo;
    }

}

