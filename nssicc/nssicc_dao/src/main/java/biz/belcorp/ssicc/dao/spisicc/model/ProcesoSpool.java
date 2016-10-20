/**
 * 
 */
package biz.belcorp.ssicc.dao.spisicc.model;

/**
 * @author PEJCAIRAMPOMA
 *
 */
public class ProcesoSpool {
	private String codigoPais;
	private String codigoPeriodo;
	private String oidZona;
	private boolean isSuccess;
	private String mensajeError;
	private String fechaFacturacion;
	
	
	/**
	 * @return the mensajeError
	 */
	public String getMensajeError() {
		return mensajeError;
	}
	/**
	 * @param mensajeError the mensajeError to set
	 */
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}
	public ProcesoSpool() {
		super();
		this.isSuccess=true;
		this.mensajeError="";
		
	}
	public ProcesoSpool(String codigoPais, String codigoPeriodo, String oidZona,String fechaFacturacion) {
		
		this.isSuccess=true;
		this.mensajeError="";
		this.codigoPais = codigoPais;
		this.codigoPeriodo = codigoPeriodo;
		this.oidZona = oidZona;
		this.fechaFacturacion =fechaFacturacion;
	}
	/**
	 * @return the isSuccess
	 */
	public boolean isSuccess() {
		return isSuccess;
	}
	/**
	 * @param isSuccess the isSuccess to set
	 */
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
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
	 * @return the fechaFacturacion
	 */
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}
	/**
	 * @param fechaFacturacion the fechaFacturacion to set
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}
	/**
	 * @return the oidZona
	 */
	public String getOidZona() {
		return oidZona;
	}
	/**
	 * @param oidZona the oidZona to set
	 */
	public void setOidZona(String oidZona) {
		this.oidZona = oidZona;
	}
	
	

}
