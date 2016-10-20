/*
 * Created on 17-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="LibretaAhorro.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public class LibretaAhorro extends AuditableBaseObject implements Comparable, Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 9117883895481283135L;

	private String codigoLider;	
    
    private String codigoPlanilla;
	
    private String nombreApellidos;
    
    private String centroCosto;
	
    private String documentoIdentidad;
    
    private String numeroLibretaAhorro;
    
    private String periodoIngreso;
    
    private String codigoPais;
    
    private String paterno;
    
    private String materno;
    
    private String nombre;
    
    private String ruc;
    
    private String razonSocial;
    
    private String codCcci;
    
    private String tipoRegimen;
    
    /* INI SA PER-SiCC-2012-0357 */
    private String cuentaDetraccion;
    /* FIN SA PER-SiCC-2012-0357 */
    
	/**
	 * @return Returns the tipoRegimen.
	 */
	public String getTipoRegimen() {
		return tipoRegimen;
	}
	/**
	 * @param tipoRegimen The tipoRegimen to set.
	 */
	public void setTipoRegimen(String tipoRegimen) {
		this.tipoRegimen = tipoRegimen;
	}
	public String getCodCcci() {
		return codCcci;
	}
	public void setCodCcci(String codCcci) {
		this.codCcci = codCcci;
	}
	public String getCentroCosto() {
		return centroCosto;
	}
	public void setCentroCosto(String centroCosto) {
		this.centroCosto = centroCosto;
	}
	public String getCodigoLider() {
		return codigoLider;
	}
	public void setCodigoLider(String codigoLider) {
		this.codigoLider = codigoLider;
	}
	public String getCodigoPais() {
		return codigoPais;
	}
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	public String getCodigoPlanilla() {
		return codigoPlanilla;
	}
	public void setCodigoPlanilla(String codigoPlanilla) {
		this.codigoPlanilla = codigoPlanilla;
	}
	public String getDocumentoIdentidad() {
		return documentoIdentidad;
	}
	public void setDocumentoIdentidad(String documentoIdentidad) {
		this.documentoIdentidad = documentoIdentidad;
	}
	public String getNumeroLibretaAhorro() {
		return numeroLibretaAhorro;
	}
	public void setNumeroLibretaAhorro(String numeroLibretaAhorro) {
		this.numeroLibretaAhorro = numeroLibretaAhorro;
	}
	public String getPeriodoIngreso() {
		return periodoIngreso;
	}
	public void setPeriodoIngreso(String periodoIngreso) {
		this.periodoIngreso = periodoIngreso;
	}
	public String getMaterno() {
		return materno;
	}
	public void setMaterno(String materno) {
		this.materno = materno;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPaterno() {
		return paterno;
	}
	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}
    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    public int compareTo(Object object) {
        LibretaAhorro myClass = (LibretaAhorro) object;
        return new CompareToBuilder().append(this.materno, myClass.materno)
                .append(this.paterno, myClass.paterno).append(
                        this.numeroLibretaAhorro, myClass.numeroLibretaAhorro)
                .append(this.nombre, myClass.nombre).append(this.codigoPais,
                        myClass.codigoPais).append(this.periodoIngreso,
                        myClass.periodoIngreso).append(this.auditInfo,
                        myClass.auditInfo).append(this.documentoIdentidad,
                        myClass.documentoIdentidad).append(this.centroCosto,
                        myClass.centroCosto).append(this.codigoPlanilla,
                        myClass.codigoPlanilla).append(this.codigoLider,
                        myClass.codigoLider).toComparison();
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof LibretaAhorro)) {
            return false;
        }
        LibretaAhorro rhs = (LibretaAhorro) object;
        return new EqualsBuilder().append(this.materno, rhs.materno).append(
                this.paterno, rhs.paterno).append(this.numeroLibretaAhorro,
                rhs.numeroLibretaAhorro).append(this.nombre, rhs.nombre)
                .append(this.codigoPais, rhs.codigoPais).append(
                        this.periodoIngreso, rhs.periodoIngreso).append(
                        this.auditInfo, rhs.auditInfo).append(
                        this.documentoIdentidad, rhs.documentoIdentidad)
                .append(this.centroCosto, rhs.centroCosto).append(
                        this.codigoPlanilla, rhs.codigoPlanilla).append(
                        this.codigoLider, rhs.codigoLider).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(110662855, -1072826743).append(this.materno)
                .append(this.paterno).append(this.numeroLibretaAhorro).append(
                        this.nombre).append(this.codigoPais).append(
                        this.periodoIngreso).append(this.auditInfo).append(
                        this.documentoIdentidad).append(this.centroCosto)
                .append(this.codigoPlanilla).append(this.codigoLider)
                .toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("documentoIdentidad",
                this.documentoIdentidad).append("codigoPais", this.codigoPais)
                .append("periodoIngreso", this.periodoIngreso).append(
                        "auditInfo", this.auditInfo).append("nombre",
                        this.nombre).append("codigoLider", this.codigoLider)
                .append("codigoPlanilla", this.codigoPlanilla).append(
                        "numeroLibretaAhorro", this.numeroLibretaAhorro)
                .append("paterno", this.paterno)
                .append("materno", this.materno).append("centroCosto",
                        this.centroCosto).toString();
    }
    public String getNombreApellidos() {
        return nombreApellidos;
    }
    public void setNombreApellidos(String nombreApellidos) {
        this.nombreApellidos = nombreApellidos;
    }
	/**
	 * @return Returns the razonSocial.
	 */
	public String getRazonSocial() {
		return razonSocial;
	}
	/**
	 * @param razonSocial The razonSocial to set.
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	/**
	 * @return Returns the ruc.
	 */
	public String getRuc() {
		return ruc;
	}
	/**
	 * @param ruc The ruc to set.
	 */
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
 
    /**
	 * @return the cuentaDetraccion
	 */
	public String getCuentaDetraccion() {
		return cuentaDetraccion;
	}
	/**
	 * @param cuentaDetraccion the cuentaDetraccion to set
	 */
	public void setCuentaDetraccion(String cuentaDetraccion) {
		this.cuentaDetraccion = cuentaDetraccion;
	}
	
}