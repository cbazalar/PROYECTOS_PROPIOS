package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;



/**
 * @author Aurelio Oviedo
 *
 */
public class ActualizacionSaldos extends AuditableBaseObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 768726212471396423L;
	private String cedula;
	private String referencia;  
	private String saldoActualCargo;          
	private String valorCargo;                
	private String fechaCargo;              	  
	private String campanya;              	  	
	private String etapa;              			    
	private String tipoDocumento;              	 
	private String numeroDocumento;           	  
	private String cuota;                 		  
	private String codigousuaricobrador;           
	private String codigocart;              	      
	private String numeroLoteAsig;                 
	private String indicadorEnviCart;              
	private String indicadorGeneLiqui;             
	private String indicadorEnviLiqui;
	/**
	 * @return the cedula
	 */
	public String getCedula() {
		return cedula;
	}
	/**
	 * @param cedula the cedula to set
	 */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	/**
	 * @return the referencia
	 */
	public String getReferencia() {
		return referencia;
	}
	/**
	 * @param referencia the referencia to set
	 */
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	/**
	 * @return the saldoActualCargo
	 */
	public String getSaldoActualCargo() {
		return saldoActualCargo;
	}
	/**
	 * @param saldoActualCargo the saldoActualCargo to set
	 */
	public void setSaldoActualCargo(String saldoActualCargo) {
		this.saldoActualCargo = saldoActualCargo;
	}
	/**
	 * @return the valorCargo
	 */
	public String getValorCargo() {
		return valorCargo;
	}
	/**
	 * @param valorCargo the valorCargo to set
	 */
	public void setValorCargo(String valorCargo) {
		this.valorCargo = valorCargo;
	}
	/**
	 * @return the fechaCargo
	 */
	public String getFechaCargo() {
		return fechaCargo;
	}
	/**
	 * @param fechaCargo the fechaCargo to set
	 */
	public void setFechaCargo(String fechaCargo) {
		this.fechaCargo = fechaCargo;
	}
	/**
	 * @return the campanya
	 */
	public String getCampanya() {
		return campanya;
	}
	/**
	 * @param campanya the campanya to set
	 */
	public void setCampanya(String campanya) {
		this.campanya = campanya;
	}
	/**
	 * @return the etapa
	 */
	public String getEtapa() {
		return etapa;
	}
	/**
	 * @param etapa the etapa to set
	 */
	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}
	/**
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	/**
	 * @return the numeroDocumento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	/**
	 * @param numeroDocumento the numeroDocumento to set
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	/**
	 * @return the cuota
	 */
	public String getCuota() {
		return cuota;
	}
	/**
	 * @param cuota the cuota to set
	 */
	public void setCuota(String cuota) {
		this.cuota = cuota;
	}
	/**
	 * @return the codigousuaricobrador
	 */
	public String getCodigousuaricobrador() {
		return codigousuaricobrador;
	}
	/**
	 * @param codigousuaricobrador the codigousuaricobrador to set
	 */
	public void setCodigousuaricobrador(String codigousuaricobrador) {
		this.codigousuaricobrador = codigousuaricobrador;
	}
	/**
	 * @return the codigocart
	 */
	public String getCodigocart() {
		return codigocart;
	}
	/**
	 * @param codigocart the codigocart to set
	 */
	public void setCodigocart(String codigocart) {
		this.codigocart = codigocart;
	}
	/**
	 * @return the numeroLoteAsig
	 */
	public String getNumeroLoteAsig() {
		return numeroLoteAsig;
	}
	/**
	 * @param numeroLoteAsig the numeroLoteAsig to set
	 */
	public void setNumeroLoteAsig(String numeroLoteAsig) {
		this.numeroLoteAsig = numeroLoteAsig;
	}
	/**
	 * @return the indicadorEnviCart
	 */
	public String getIndicadorEnviCart() {
		return indicadorEnviCart;
	}
	/**
	 * @param indicadorEnviCart the indicadorEnviCart to set
	 */
	public void setIndicadorEnviCart(String indicadorEnviCart) {
		this.indicadorEnviCart = indicadorEnviCart;
	}
	/**
	 * @return the indicadorGeneLiqui
	 */
	public String getIndicadorGeneLiqui() {
		return indicadorGeneLiqui;
	}
	/**
	 * @param indicadorGeneLiqui the indicadorGeneLiqui to set
	 */
	public void setIndicadorGeneLiqui(String indicadorGeneLiqui) {
		this.indicadorGeneLiqui = indicadorGeneLiqui;
	}
	/**
	 * @return the indicadorEnviLiqui
	 */
	public String getIndicadorEnviLiqui() {
		return indicadorEnviLiqui;
	}
	/**
	 * @param indicadorEnviLiqui the indicadorEnviLiqui to set
	 */
	public void setIndicadorEnviLiqui(String indicadorEnviLiqui) {
		this.indicadorEnviLiqui = indicadorEnviLiqui;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}    
	
}