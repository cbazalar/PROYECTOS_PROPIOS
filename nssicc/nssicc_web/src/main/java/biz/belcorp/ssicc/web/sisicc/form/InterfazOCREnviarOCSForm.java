package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * Form del Enviar OCS de archivos de texto de OCS de la
 * Interfaz OCR.
 * 
 * @author <a href="mailto:msilva@belcorp.biz">Marco Silva</a>
 * 
 * @struts.form name = "interfazOCREnviarOCSForm" extends =
 *              "baseInterfazPaqueteForm"
 */
public class InterfazOCREnviarOCSForm extends
		BaseInterfazForm implements Serializable {
	
	private String[] regiones ;
	private String periodo ;
	private String fechaFact ;
	
	
    /*
     *  (non-Javadoc)
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        // TODO Auto-generated method stub
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        this.fechaFact = sdf.format(new Date(System.currentTimeMillis()));
    }


	public String getFechaFact() {
		return fechaFact;
	}

    /**
     * @param fechaFact The fechaInicio to set.
     * @param fechaFact
     *            New value of property fechaFact.
     * @struts.validator type="required"
     * @struts.validator type="date"
     * @struts.validator-var name="datePatternStrict"
     *                       value="${defaultDatePattern}"
     */

	public void setFechaFact(String fechaFact) {
		this.fechaFact = fechaFact;
	}


	public String getPeriodo() {
		return periodo;
	}

	/**
	 * @struts.validator type = "required"
	 * @struts.validator type="mask"
	 * @struts.validator-var name="mask" value="${campaign}"
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}


	public String[] getRegiones() {
		return regiones;
	}


	public void setRegiones(String[] regiones) {
		this.regiones = regiones;
	}
}