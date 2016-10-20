package biz.belcorp.ssicc.dao.spusicc.cobranzas.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author JFA
 *
 */
public class CronogramaCartera extends AuditableBaseObject implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private String codigoPais;
	private String codigoSociedad;
	private String codigoEtapaDeuda;
	private String descriEtapaDeuda;
	private String codigoPeriodo;
	private String codigoRegion;
	private String descriRegion;
	private String codigoZona;
	private String fechaGeneracionCartera;
	private String fechaInicioGestion;
	private String fechaCompromisoPago;
	private String fechaCierreCartera;				
    
	public CronogramaCartera() {
	
	}
	
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}		
	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
    
	public String getCodigoPais() {
		return codigoPais;
	}
	
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}
    
	public String getCodigoSociedad() {
		return codigoSociedad;
	}

	/**
	 * @return the codigoEtapaDeuda
	 */
	public String getCodigoEtapaDeuda() {
		return codigoEtapaDeuda;
	}

	/**
	 * @param codigoEtapaDeuda the codigoEtapaDeuda to set
	 */
	public void setCodigoEtapaDeuda(String codigoEtapaDeuda) {
		this.codigoEtapaDeuda = codigoEtapaDeuda;
	}
	
	/**
	 * @return the descriEtapaDeuda
	 */
	public String getDescriEtapaDeuda() {
		return descriEtapaDeuda;
	}

	/**
	 * @param descriEtapaDeuda the descriEtapaDeuda to set
	 */
	public void setDescriEtapaDeuda(String descriEtapaDeuda) {
		this.descriEtapaDeuda = descriEtapaDeuda;
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
	 * @return the descriRegion
	 */
	public String getDescriRegion() {
		return descriRegion;
	}

	/**
	 * @param descriRegion the descriRegion to set
	 */
	public void setDescriRegion(String descriRegion) {
		this.descriRegion = descriRegion;
	}
	
	
	/**
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	/**
	 * @return the fechaGeneracionCartera
	 */
	public String getFechaGeneracionCartera() {
		return fechaGeneracionCartera;
	}

	/**
	 * @param fechaGeneracionCartera the fechaGeneracionCartera to set
	 */
	public void setFechaGeneracionCartera(String fechaGeneracionCartera) {
		this.fechaGeneracionCartera = fechaGeneracionCartera;
	}

	/**
	 * @return the fechaInicioGestion
	 */
	public String getFechaInicioGestion() {
		return fechaInicioGestion;
	}

	/**
	 * @param fechaInicioGestion the fechaInicioGestion to set
	 */
	public void setFechaInicioGestion(String fechaInicioGestion) {
		this.fechaInicioGestion = fechaInicioGestion;
	}

	/**
	 * @return the fechaCompromisoPago
	 */
	public String getFechaCompromisoPago() {
		return fechaCompromisoPago;
	}

	/**
	 * @param fechaCompromisoPago the fechaCompromisoPago to set
	 */
	public void setFechaCompromisoPago(String fechaCompromisoPago) {
		this.fechaCompromisoPago = fechaCompromisoPago;
	}

	/**
	 * @return the fechaCierreCartera
	 */
	public String getFechaCierreCartera() {
		return fechaCierreCartera;
	}

	/**
	 * @param fechaCierreCartera the fechaCierreCartera to set
	 */
	public void setFechaCierreCartera(String fechaCierreCartera) {
		this.fechaCierreCartera = fechaCierreCartera;
	}

}
