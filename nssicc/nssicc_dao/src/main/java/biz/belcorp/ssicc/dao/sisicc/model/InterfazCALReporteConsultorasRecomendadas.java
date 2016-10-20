/*
 * Created on 27-dic-2005
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
 * <a href="InterfazGISReporteDireccionConsultoras.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public class InterfazCALReporteConsultorasRecomendadas implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -1506711664275315289L;

	private String codfi;
   
    private String region;
    
    private String zona;
    
    private String codigoCliente;
    
    private String tipoDocumento;
    
    private String numero;
    
    private String primerNombre;
    
    private String segundoNombre;
    
    private String apellidoPaterno;
    
    private String apellidoMaterno;
    
    private String direccion;
    
    private String telefono;
    
    private String codigo;
    
    private String primerNombreRecomendada;
    
    private String segundoNombreRecomendada;
    
    private String apellidoPaternoRecomendada;
    
    private String apellidoMaternoRecomendada;
    
    private String zonaRecomendada;
    
    private String territorioRecomendada;
    
    private String fechaRegistro;
    
    private String estado;
    
    private String observacion;
    
    private String usuario;
    
    private String fechaProceso;
    
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    public String getApellidoMaternoRecomendada() {
        return apellidoMaternoRecomendada;
    }
    public void setApellidoMaternoRecomendada(String apellidoMaternoRecomendada) {
        this.apellidoMaternoRecomendada = apellidoMaternoRecomendada;
    }
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }
    public String getApellidoPaternoRecomendada() {
        return apellidoPaternoRecomendada;
    }
    public void setApellidoPaternoRecomendada(String apellidoPaternoRecomendada) {
        this.apellidoPaternoRecomendada = apellidoPaternoRecomendada;
    }
    public String getCodfi() {
        return codfi;
    }
    public void setCodfi(String codfi) {
        this.codfi = codfi;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getCodigoCliente() {
        return codigoCliente;
    }
    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getFechaProceso() {
        return fechaProceso;
    }
    public void setFechaProceso(String fechaProceso) {
        this.fechaProceso = fechaProceso;
    }
    public String getFechaRegistro() {
        return fechaRegistro;
    }
    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getObservacion() {
        return observacion;
    }
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    public String getPrimerNombre() {
        return primerNombre;
    }
    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }
    public String getPrimerNombreRecomendada() {
        return primerNombreRecomendada;
    }
    public void setPrimerNombreRecomendada(String primerNombreRecomendada) {
        this.primerNombreRecomendada = primerNombreRecomendada;
    }
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public String getSegundoNombre() {
        return segundoNombre;
    }
    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }
    public String getSegundoNombreRecomendada() {
        return segundoNombreRecomendada;
    }
    public void setSegundoNombreRecomendada(String segundoNombreRecomendada) {
        this.segundoNombreRecomendada = segundoNombreRecomendada;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getTerritorioRecomendada() {
        return territorioRecomendada;
    }
    public void setTerritorioRecomendada(String territorioRecomendada) {
        this.territorioRecomendada = territorioRecomendada;
    }
    public String getTipoDocumento() {
        return tipoDocumento;
    }
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getZona() {
        return zona;
    }
    public void setZona(String zona) {
        this.zona = zona;
    }
    public String getZonaRecomendada() {
        return zonaRecomendada;
    }
    public void setZonaRecomendada(String zonaRecomendada) {
        this.zonaRecomendada = zonaRecomendada;
    }
    
      
    }
