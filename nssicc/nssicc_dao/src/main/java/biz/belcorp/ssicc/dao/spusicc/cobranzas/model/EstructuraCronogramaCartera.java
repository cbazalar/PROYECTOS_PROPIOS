package biz.belcorp.ssicc.dao.spusicc.cobranzas.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author JFA
 *
 */
public class EstructuraCronogramaCartera extends AuditableBaseObject implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String numeroLote;
	private String codigoEtapaDeuda;	
	private String codigoPeriodo;
	private String codigoRegion;
	private String codigoZona;
	private String fechaInicialGeneracionCartera;
	private String fechaFinalGeneracionCartera;		
	private String fechaCierreCartera;	
	private String codigoUsuario;
	
	private int fila;
    
	public EstructuraCronogramaCartera() {
	
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
	
	/**
	 * @return the numeroLote
	 */
	public String getNumeroLote() {
		return numeroLote;
	}

	/**
	 * @param codigoEtapaDeuda the codigoEtapaDeuda to set
	 */
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
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
	 * @return the fechaInicialGeneracionCartera
	 */
	public String getFechaInicialGeneracionCartera() {
		return fechaInicialGeneracionCartera;
	}

	/**
	 * @param fechaInicialGeneracionCartera the fechaInicialGeneracionCartera to set
	 */
	public void setFechaInicialGeneracionCartera(String fechaInicialGeneracionCartera) {
		this.fechaInicialGeneracionCartera = fechaInicialGeneracionCartera;
	}
	
	/**
	 * @return the fechaFinalGeneracionCartera
	 */
	public String getFechaFinalGeneracionCartera() {
		return fechaFinalGeneracionCartera;
	}

	/**
	 * @param fechaFinalGeneracionCartera the fechaFinallGeneracionCartera to set
	 */
	public void setFechaFinalGeneracionCartera(String fechaFinalGeneracionCartera) {
		this.fechaFinalGeneracionCartera = fechaFinalGeneracionCartera;
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
	
	/**
	 * @return the codigoUsuario
	 */
	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	/**
	 * @param codigoUsuario the codigoUsuario to set
	 */
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	/**
	 * @return the fila
	 */
	public int getFila() {
		return fila;
	}

	/**
	 * @param fila the fila to set
	 */
	public void setFila(int fila) {
		this.fila = fila;
	}
}
