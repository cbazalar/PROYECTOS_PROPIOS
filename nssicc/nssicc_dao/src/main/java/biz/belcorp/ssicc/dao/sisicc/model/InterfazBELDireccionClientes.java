/**
 * Created on 17-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;


/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazBELDireccionClientes.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
public class InterfazBELDireccionClientes implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -298081040196817741L;
	private String codigoCliente;
    private String tipoVia;
    private String nombreVia;
    private String statusIndicadorDescripcionAdicional;
    private String numeroVia;
    private String numeroBlock="   ";  
    private String numeroManzana;
    private String numeroLote;
    private String numeroKilometro;
    private String numeroInteriorDomicilio;
    private String codigoPostal;
    private String codigoUbigeoINEI;
    private String codigoTipoCentroPoblado="  ";
    private String numeroEtapa="   ";
    private String numeroSector="    ";
    private String statusIndicadorDescripcionReferencia=" ";
    private String numeroTelefono1;
    private String numeroTelefono2;
    private String statusIndicadorDireccionTrabajo;
    private String numeroTelefono3;
    private String numeroTelefono4;
    private String statusIndicadorAdicional=" ";
    
    public String getCodigoCliente() {
        return codigoCliente;
    }
    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }
    public String getCodigoPostal() {
        return codigoPostal;
    }
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
    public String getCodigoTipoCentroPoblado() {
        return codigoTipoCentroPoblado;
    }
    public void setCodigoTipoCentroPoblado(String codigoTipoCentroPoblado) {
        this.codigoTipoCentroPoblado = codigoTipoCentroPoblado;
    }
    public String getCodigoUbigeoINEI() {
        return codigoUbigeoINEI;
    }
    public void setCodigoUbigeoINEI(String codigoUbigeoINEI) {
        this.codigoUbigeoINEI = codigoUbigeoINEI;
    }
    public String getNombreVia() {
        return nombreVia;
    }
    public void setNombreVia(String nombreVia) {
        this.nombreVia = nombreVia;
    }
    public String getNumeroBlock() {
        return numeroBlock;
    }
    public void setNumeroBlock(String numeroBlock) {
        this.numeroBlock = numeroBlock;
    }
    public String getNumeroEtapa() {
        return numeroEtapa;
    }
    public void setNumeroEtapa(String numeroEtapa) {
        this.numeroEtapa = numeroEtapa;
    }
    public String getNumeroInteriorDomicilio() {
        return numeroInteriorDomicilio;
    }
    public void setNumeroInteriorDomicilio(String numeroInteriorDomicilio) {
        this.numeroInteriorDomicilio = numeroInteriorDomicilio;
    }
    public String getNumeroKilometro() {
        return numeroKilometro;
    }
    public void setNumeroKilometro(String numeroKilometro) {
        this.numeroKilometro = numeroKilometro;
    }
    public String getNumeroLote() {
        return numeroLote;
    }
    public void setNumeroLote(String numeroLote) {
        this.numeroLote = numeroLote;
    }
    public String getNumeroManzana() {
        return numeroManzana;
    }
    public void setNumeroManzana(String numeroManzana) {
        this.numeroManzana = numeroManzana;
    }
    public String getNumeroSector() {
        return numeroSector;
    }
    public void setNumeroSector(String numeroSector) {
        this.numeroSector = numeroSector;
    }
    public String getNumeroTelefono1() {
        return numeroTelefono1;
    }
    public void setNumeroTelefono1(String numeroTelefono1) {
        this.numeroTelefono1 = numeroTelefono1;
    }
    public String getNumeroTelefono2() {
        return numeroTelefono2;
    }
    public void setNumeroTelefono2(String numeroTelefono2) {
        this.numeroTelefono2 = numeroTelefono2;
    }
    public String getNumeroTelefono3() {
        return numeroTelefono3;
    }
    public void setNumeroTelefono3(String numeroTelefono3) {
        this.numeroTelefono3 = numeroTelefono3;
    }
    public String getNumeroTelefono4() {
        return numeroTelefono4;
    }
    public void setNumeroTelefono4(String numeroTelefono4) {
        this.numeroTelefono4 = numeroTelefono4;
    }
    public String getNumeroVia() {
        return numeroVia;
    }
    public void setNumeroVia(String numeroVia) {
        this.numeroVia = numeroVia;
    }
    public String getStatusIndicadorAdicional() {
        return statusIndicadorAdicional;
    }
    public void setStatusIndicadorAdicional(String statusIndicadorAdicional) {
        this.statusIndicadorAdicional = statusIndicadorAdicional;
    }
    public String getStatusIndicadorDescripcionAdicional() {
        return statusIndicadorDescripcionAdicional;
    }
    public void setStatusIndicadorDescripcionAdicional(
            String statusIndicadorDescripcionAdicional) {
        this.statusIndicadorDescripcionAdicional = statusIndicadorDescripcionAdicional;
    }
    public String getStatusIndicadorDescripcionReferencia() {
        return statusIndicadorDescripcionReferencia;
    }
    public void setStatusIndicadorDescripcionReferencia(
            String statusIndicadorDescripcionReferencia) {
        this.statusIndicadorDescripcionReferencia = statusIndicadorDescripcionReferencia;
    }
    public String getStatusIndicadorDireccionTrabajo() {
        return statusIndicadorDireccionTrabajo;
    }
    public void setStatusIndicadorDireccionTrabajo(
            String statusIndicadorDireccionTrabajo) {
        this.statusIndicadorDireccionTrabajo = statusIndicadorDireccionTrabajo;
    }
    public String getTipoVia() {
        return tipoVia;
    }
    public void setTipoVia(String tipoVia) {
        this.tipoVia = tipoVia;
    }
    
    
    
}
