/*
 * Created on 08-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazRETEnviarMatrizCampanyaForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 *  
 * @struts.form
 *   name = "interfazRETEnviarMatrizCampanyaForm"
 * 	 extends = "baseInterfazForm"	
 */
public class InterfazRETEnviarMatrizCampanyaForm extends BaseInterfazForm implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7494357401841123205L;
	private String codigoMarca;
	private String codigoCanal;
	private String codigoAcceso;
	private String codigoSubacceso;	
	private String periodoDesde;
	private String periodoHasta;
		
    /**
	 * @return Returns the codigoAcceso.
	 */
	public String getCodigoAcceso() {
		return codigoAcceso;
	}

	/**
	 * @param codigoAcceso The codigoAcceso to set.
	 */
	public void setCodigoAcceso(String codigoAcceso) {
		this.codigoAcceso = codigoAcceso;
	}

	/**
	 * @return Returns the codigoCanal.
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal The codigoCanal to set.
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @return Returns the codigoMarca.
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca The codigoMarca to set.
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	/**
	 * @return Returns the codigoSubacceso.
	 */
	public String getCodigoSubacceso() {
		return codigoSubacceso;
	}

	/**
	 * @param codigoSubacceso The codigoSubacceso to set.
	 */
	public void setCodigoSubacceso(String codigoSubacceso) {
		this.codigoSubacceso = codigoSubacceso;
	}

	/**
	 * @return Returns the periodoDesde.
	 */
	public String getPeriodoDesde() {
		return periodoDesde;
	}

	/**
	 * @param periodoDesde The periodoDesde to set.
	 * @struts.validator type = "required"
	 * @struts.validator type="mask"
	 * @struts.validator-var name="mask" value="${campaign}"
	 */
	public void setPeriodoDesde(String periodoDesde) {
		this.periodoDesde = periodoDesde;
	}

	/**
	 * @return Returns the periodoHasta.
	 */
	public String getPeriodoHasta() {
		return periodoHasta;
	}

	/**
	 * @param periodoHasta The periodoHasta to set.
	 * @struts.validator type = "required"
	 * @struts.validator type="mask"
	 * @struts.validator-var name="mask" value="${campaign}"
	 */
	public void setPeriodoHasta(String periodoHasta) {
		this.periodoHasta = periodoHasta;
	}

	public InterfazRETEnviarMatrizCampanyaForm() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		
    	this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
    	this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
    	this.periodoDesde = sdf.format(new Date(System.currentTimeMillis()));;
    	this.periodoHasta = sdf.format(new Date(System.currentTimeMillis()));;
    	this.codigoAcceso = Constants.CODIGO_ACCESO_DEFAULT;
    	this.codigoSubacceso = Constants.CODIGO_SUBACCESO_DEFAULT;
    	
    }

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		
		ActionErrors errors = new ActionErrors();

		if((getPeriodoDesde() != null && getPeriodoDesde().length() > 1) && (getPeriodoHasta() != null && getPeriodoHasta().length() > 1)){
			Long desde = new Long(getPeriodoDesde());
			Long hasta = new Long(getPeriodoHasta());
			
			if( desde.longValue() > hasta.longValue()){
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("interfazSiCC.error.periodo.Desde.Mayor"));
			}
		}
		return errors;
	}
	
}
