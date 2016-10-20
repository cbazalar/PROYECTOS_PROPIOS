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
 * <a href="InterfazGEOReporteClientesErroneos.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public class InterfazGEOReporteClientesErroneos implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 9101231360960953529L;

	private String codigoPais;

    private String codigoCliente;
	
    private String documentoIdentidad;
	
    private String nombre;
	
    private String apellidoPaterno;
	
    private String nombreVia;
	
    private String numeroPrincipal;
	
    private String codigoUbigeo;
	
    private String descripcionUbigeo;
	
    private String observacionesDireccion;

    private String errorZona;
	
    private String errorTerritorio;
	
    private String errorZonaTerritorio;
	
    private String fechaActualizacion;
	
    private String usuario;

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    public String getCodigoUbigeo() {
        return codigoUbigeo;
    }

    public void setCodigoUbigeo(String codigoUbigeo) {
        this.codigoUbigeo = codigoUbigeo;
    }

    public String getDescripcionUbigeo() {
        return descripcionUbigeo;
    }

    public void setDescripcionUbigeo(String descripcionUbigeo) {
        this.descripcionUbigeo = descripcionUbigeo;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public String getErrorTerritorio() {
        return errorTerritorio;
    }

    public void setErrorTerritorio(String errorTerritorio) {
        this.errorTerritorio = errorTerritorio;
    }

    public String getErrorZona() {
        return errorZona;
    }

    public void setErrorZona(String errorZona) {
        this.errorZona = errorZona;
    }

    public String getErrorZonaTerritorio() {
        return errorZonaTerritorio;
    }

    public void setErrorZonaTerritorio(String errorZonaTerritorio) {
        this.errorZonaTerritorio = errorZonaTerritorio;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreVia() {
        return nombreVia;
    }

    public void setNombreVia(String nombreVia) {
        this.nombreVia = nombreVia;
    }

    public String getNumeroPrincipal() {
        return numeroPrincipal;
    }

    public void setNumeroPrincipal(String numeroPrincipal) {
        this.numeroPrincipal = numeroPrincipal;
    }

    public String getObservacionesDireccion() {
        return observacionesDireccion;
    }

    public void setObservacionesDireccion(String observacionesDireccion) {
        this.observacionesDireccion = observacionesDireccion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }


    
}