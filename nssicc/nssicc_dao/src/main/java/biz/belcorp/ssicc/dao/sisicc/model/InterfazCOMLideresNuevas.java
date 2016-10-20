/**
 * Created on 17-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazCOMLideresNuevas.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
public class InterfazCOMLideresNuevas implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -7447342564408773890L;

	private String nombres;
    
    private String apellidoPaterno;
    
    private String apellidoMaterno;
    
    private Timestamp fechaIngreso;
    
    private String dni;
    
    private String direccion;
    
    private String telefono;
    
    private Timestamp fechaNacimiento;
        
    private String ciudad;
    
    private String estadoCivil;
    
    private String limaProvincia;
    
    private Timestamp fechaIngresoPlanilla;
    
    private String centroCosto;
    
    private String codigoZona;
                 
    private String descripcionDistrito;
    
    private String codigoLider;
    
    private String descripcionDepartamento;
    
    private String descripcionProvincia;

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getCentroCosto() {
        return centroCosto;
    }

    public void setCentroCosto(String centroCosto) {
        this.centroCosto = centroCosto;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCodigoLider() {
        return codigoLider;
    }

    public void setCodigoLider(String codigoLider) {
        this.codigoLider = codigoLider;
    }

    public String getCodigoZona() {
        return codigoZona;
    }

    public void setCodigoZona(String codigoZona) {
        this.codigoZona = codigoZona;
    }

    public String getDescripcionDepartamento() {
        return descripcionDepartamento;
    }

    public void setDescripcionDepartamento(String descripcionDepartamento) {
        this.descripcionDepartamento = descripcionDepartamento;
    }

    public String getDescripcionDistrito() {
        return descripcionDistrito;
    }

    public void setDescripcionDistrito(String descripcionDistrito) {
        this.descripcionDistrito = descripcionDistrito;
    }

    public String getDescripcionProvincia() {
        return descripcionProvincia;
    }

    public void setDescripcionProvincia(String descripcionProvincia) {
        this.descripcionProvincia = descripcionProvincia;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Timestamp getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Timestamp fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Timestamp getFechaIngresoPlanilla() {
        return fechaIngresoPlanilla;
    }

    public void setFechaIngresoPlanilla(Timestamp fechaIngresoPlanilla) {
        this.fechaIngresoPlanilla = fechaIngresoPlanilla;
    }

    public Timestamp getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Timestamp fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getLimaProvincia() {
        return limaProvincia;
    }

    public void setLimaProvincia(String limaProvincia) {
        this.limaProvincia = limaProvincia;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
    
}
