package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * Form del Repcionar Cabecera y Detalle por Venta de la Interfaz Retail.
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 * 
 * @struts.form name = "interfazRETRecepcionarVentasRetailCabDetForm" extends =
 *              "baseInterfazPaqueteForm"
 */
public class InterfazRETRecepcionarVentasRetailCabDetForm extends
		BaseInterfazForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String indicadorReproceso;
	private String fechaInicio;
	private String fechaFinal;
	private Date fechaInicioD;
	private Date fechaFinalD;

	/**
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechaFinal
	 */
	public String getFechaFinal() {
		return fechaFinal;
	}

	/**
	 * @param fechaFinal the fechaFinal to set
	 */
	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	/**
	 * @return the indicadorReproceso
	 */
	public String getIndicadorReproceso() {
		return indicadorReproceso;
	}

	/**
	 * @param indicadorReproceso the indicadorReproceso to set
	 */
	public void setIndicadorReproceso(String indicadorReproceso) {
		this.indicadorReproceso = indicadorReproceso;
	}
	
	
    /**
	 * @return the fechaInicioD
	 */
	public Date getFechaInicioD() {
		return fechaInicioD;
	}

	/**
	 * @param fechaInicioD the fechaInicioD to set
	 */
	public void setFechaInicioD(Date fechaInicioD) {
		this.fechaInicioD = fechaInicioD;
	}

	/**
	 * @return the fechaFinalD
	 */
	public Date getFechaFinalD() {
		return fechaFinalD;
	}

	/**
	 * @param fechaFinalD the fechaFinalD to set
	 */
	public void setFechaFinalD(Date fechaFinalD) {
		this.fechaFinalD = fechaFinalD;
	}

	/* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.web.framework.form.BaseInterfazForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    public InterfazRETRecepcionarVentasRetailCabDetForm() {		
    	  this.indicadorReproceso = Constants.NUMERO_CERO;
          this.fechaInicio = this.fechaFinal = "";		
	}
    
}