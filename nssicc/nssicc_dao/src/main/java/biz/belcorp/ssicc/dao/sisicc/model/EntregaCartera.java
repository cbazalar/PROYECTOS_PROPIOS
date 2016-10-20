package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author Aurelio Oviedo
 *
 */
public class EntregaCartera extends AuditableBaseObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2689339432724015438L;
	private String cedula;           			   
	private String referencia;         
	private String apellidoPaterno;              
	private String apellidoMaterno;               
	private String primerNombre;                
	private String segundoNombre;
	private String campanya;              		   
	private String direccion;             		 
	private String codigoRegion;           	
	private String codigoZona;              		   
	private String codigoSeccion;              	 
	private String codigoTerritorio;           	 
	private String barrio;              			  
	private String ciudad;             			 
	private String departamento;           		
	private String telefono1;              		   
	private String telefono2;              		
	private String telefonoMovil;           		  
	private String campanyaDeuda;              	    
	private String fechaFactura;              	  
	private String fechaVencimiento;           	  
	private String diasMora;              		  
	private String valorFactura;                 
	private String saldoFactura;           	     
	private String etapa;              			  
	private String tipoDocumento;              	  
	private String numeroDocumento;           	  
	private String cuotaDocumento;                  
	private String tipoDocumentoReferencia;        
	private String numeroDocumentoReferencia;      
	private String cuotaDocumentoReferencia;         
	private String campanyaDocumentoReferencia;    
	private String codigousuaricobrador;           
	private String codigocart;              	     
	private String codigoCliente;                 
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
	 * @return the apellidoPaterno
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	/**
	 * @param apellidoPaterno the apellidoPaterno to set
	 */
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	/**
	 * @return the apellidoMaterno
	 */
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	/**
	 * @param apellidoMaterno the apellidoMaterno to set
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	/**
	 * @return the primerNombre
	 */
	public String getPrimerNombre() {
		return primerNombre;
	}
	/**
	 * @param primerNombre the primerNombre to set
	 */
	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}
	/**
	 * @return the segundoNombre
	 */
	public String getSegundoNombre() {
		return segundoNombre;
	}
	/**
	 * @param segundoNombre the segundoNombre to set
	 */
	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
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
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
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
	 * @return the codigoSeccion
	 */
	public String getCodigoSeccion() {
		return codigoSeccion;
	}
	/**
	 * @param codigoSeccion the codigoSeccion to set
	 */
	public void setCodigoSeccion(String codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
	}
	/**
	 * @return the codigoTerritorio
	 */
	public String getCodigoTerritorio() {
		return codigoTerritorio;
	}
	/**
	 * @param codigoTerritorio the codigoTerritorio to set
	 */
	public void setCodigoTerritorio(String codigoTerritorio) {
		this.codigoTerritorio = codigoTerritorio;
	}
	/**
	 * @return the barrio
	 */
	public String getBarrio() {
		return barrio;
	}
	/**
	 * @param barrio the barrio to set
	 */
	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}
	/**
	 * @return the ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}
	/**
	 * @param ciudad the ciudad to set
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	/**
	 * @return the departamento
	 */
	public String getDepartamento() {
		return departamento;
	}
	/**
	 * @param departamento the departamento to set
	 */
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	/**
	 * @return the telefono1
	 */
	public String getTelefono1() {
		return telefono1;
	}
	/**
	 * @param telefono1 the telefono1 to set
	 */
	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}
	/**
	 * @return the telefono2
	 */
	public String getTelefono2() {
		return telefono2;
	}
	/**
	 * @param telefono2 the telefono2 to set
	 */
	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}
	/**
	 * @return the telefonoMovil
	 */
	public String getTelefonoMovil() {
		return telefonoMovil;
	}
	/**
	 * @param telefonoMovil the telefonoMovil to set
	 */
	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}
	/**
	 * @return the campanyaDeuda
	 */
	public String getCampanyaDeuda() {
		return campanyaDeuda;
	}
	/**
	 * @param campanyaDeuda the campanyaDeuda to set
	 */
	public void setCampanyaDeuda(String campanyaDeuda) {
		this.campanyaDeuda = campanyaDeuda;
	}
	/**
	 * @return the fechaFactura
	 */
	public String getFechaFactura() {
		return fechaFactura;
	}
	/**
	 * @param fechaFactura the fechaFactura to set
	 */
	public void setFechaFactura(String fechaFactura) {
		this.fechaFactura = fechaFactura;
	}
	/**
	 * @return the fechaVencimiento
	 */
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}
	/**
	 * @param fechaVencimiento the fechaVencimiento to set
	 */
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	/**
	 * @return the diasMora
	 */
	public String getDiasMora() {
		return diasMora;
	}
	/**
	 * @param diasMora the diasMora to set
	 */
	public void setDiasMora(String diasMora) {
		this.diasMora = diasMora;
	}
	/**
	 * @return the valorFactura
	 */
	public String getValorFactura() {
		return valorFactura;
	}
	/**
	 * @param valorFactura the valorFactura to set
	 */
	public void setValorFactura(String valorFactura) {
		this.valorFactura = valorFactura;
	}
	/**
	 * @return the saldoFactura
	 */
	public String getSaldoFactura() {
		return saldoFactura;
	}
	/**
	 * @param saldoFactura the saldoFactura to set
	 */
	public void setSaldoFactura(String saldoFactura) {
		this.saldoFactura = saldoFactura;
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
	 * @return the cuotaDocumento
	 */
	public String getCuotaDocumento() {
		return cuotaDocumento;
	}
	/**
	 * @param cuotaDocumento the cuotaDocumento to set
	 */
	public void setCuotaDocumento(String cuotaDocumento) {
		this.cuotaDocumento = cuotaDocumento;
	}
	/**
	 * @return the tipoDocumentoReferencia
	 */
	public String getTipoDocumentoReferencia() {
		return tipoDocumentoReferencia;
	}
	/**
	 * @param tipoDocumentoReferencia the tipoDocumentoReferencia to set
	 */
	public void setTipoDocumentoReferencia(String tipoDocumentoReferencia) {
		this.tipoDocumentoReferencia = tipoDocumentoReferencia;
	}
	/**
	 * @return the numeroDocumentoReferencia
	 */
	public String getNumeroDocumentoReferencia() {
		return numeroDocumentoReferencia;
	}
	/**
	 * @param numeroDocumentoReferencia the numeroDocumentoReferencia to set
	 */
	public void setNumeroDocumentoReferencia(String numeroDocumentoReferencia) {
		this.numeroDocumentoReferencia = numeroDocumentoReferencia;
	}
	/**
	 * @return the cuotaDocumentoReferencia
	 */
	public String getCuotaDocumentoReferencia() {
		return cuotaDocumentoReferencia;
	}
	/**
	 * @param cuotaDocumentoReferencia the cuotaDocumentoReferencia to set
	 */
	public void setCuotaDocumentoReferencia(String cuotaDocumentoReferencia) {
		this.cuotaDocumentoReferencia = cuotaDocumentoReferencia;
	}
	/**
	 * @return the campanyaDocumentoReferencia
	 */
	public String getCampanyaDocumentoReferencia() {
		return campanyaDocumentoReferencia;
	}
	/**
	 * @param campanyaDocumentoReferencia the campanyaDocumentoReferencia to set
	 */
	public void setCampanyaDocumentoReferencia(String campanyaDocumentoReferencia) {
		this.campanyaDocumentoReferencia = campanyaDocumentoReferencia;
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
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}
	/**
	 * @param codigoCliente the codigoCliente to set
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
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
