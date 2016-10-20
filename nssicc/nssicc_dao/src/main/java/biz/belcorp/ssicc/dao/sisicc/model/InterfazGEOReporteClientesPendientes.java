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
 * <a href="InterfazGEOReporteClientesPendientes.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public class InterfazGEOReporteClientesPendientes implements Serializable {
   
    /**
	 * 
	 */
	private static final long serialVersionUID = 6481443023737197941L;

	private String codigoCliente;
	
    private String nombre;
	
    private String tipo;
	
    private String subTipo;
	
    private String documentoIdentidad;
	
    private String tipoDireccion;
	
    private String direccion;
	
    private String unidadGeografica;

    private String zona;
	
    private String territorio;

    private String fechaIngreso;
    
    private String status;

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

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSubTipo() {
        return subTipo;
    }

    public void setSubTipo(String subTipo) {
        this.subTipo = subTipo;
    }

    public String getTerritorio() {
        return territorio;
    }

    public void setTerritorio(String territorio) {
        this.territorio = territorio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipoDireccion() {
        return tipoDireccion;
    }

    public void setTipoDireccion(String tipoDireccion) {
        this.tipoDireccion = tipoDireccion;
    }

    public String getUnidadGeografica() {
        return unidadGeografica;
    }

    public void setUnidadGeografica(String unidadGeografica) {
        this.unidadGeografica = unidadGeografica;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
    
    
    
}
