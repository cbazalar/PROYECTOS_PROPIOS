package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazSAPFIPagoLetForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:henrypb6@gmail.com">Henry Paredes</a>
 * 
 * @struts.form name = "interfazSAPFIPagoLetForm" extends = "baseInterfazForm"
 */
public class InterfazSAPFIPagoLetForm extends BaseInterfazForm implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -913422789663615933L;

	private String codigoPais;

	private String codigoPrograma;

	private String programa;

	private String codigoPeriodo;

	private String fechaProceso;
	
	private Date fechaProcesoD;

	private String codigoTipoPago;

	public String getCodigoPais() {
		return codigoPais;
	}

	public String getCodigoPrograma() {
		return codigoPrograma;
	}

	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	public String getFechaProceso() {
		return fechaProceso;
	}

	public String getCodigoTipoPago() {
		return codigoTipoPago;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}

	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	public void setCodigoTipoPago(String codigoTipoPago) {
		this.codigoTipoPago = codigoTipoPago;
	}

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	/**
	 * @return the fechaProcesoD
	 */
	public Date getFechaProcesoD() {
		return fechaProcesoD;
	}

	/**
	 * @param fechaProcesoD the fechaProcesoD to set
	 */
	public void setFechaProcesoD(Date fechaProcesoD) {
		this.fechaProcesoD = fechaProcesoD;
	}
	
	

}
