package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 */

public class InterfazLETEnviarPagosForm extends BaseInterfazForm implements Serializable{

	private static final long serialVersionUID = 1L;
	
    protected String codigoPeriodo;
    protected String codigoRegion;
    protected String tipoEnvio;
    protected Date fechaReenvioDate;
    protected String fechaReenvio;
    
	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	/**
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}
	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	/**
	 * @return the tipoEnvio
	 */
	public String getTipoEnvio() {
		return tipoEnvio;
	}
	/**
	 * @param tipoEnvio the tipoEnvio to set
	 */
	public void setTipoEnvio(String tipoEnvio) {
		this.tipoEnvio = tipoEnvio;
	}
	/**
	 * @return the fechaReenvioDate
	 */
	public Date getFechaReenvioDate() {
		return fechaReenvioDate;
	}
	/**
	 * @param fechaReenvioDate the fechaReenvioDate to set
	 */
	public void setFechaReenvioDate(Date fechaReenvioDate) {
		this.fechaReenvioDate = fechaReenvioDate;
	}
	/**
	 * @return the fechaReenvio
	 */
	public String getFechaReenvio() {
		return fechaReenvio;
	}
	/**
	 * @param fechaReenvio the fechaReenvio to set
	 */
	public void setFechaReenvio(String fechaReenvio) {
		this.fechaReenvio = fechaReenvio;
	}
	
}