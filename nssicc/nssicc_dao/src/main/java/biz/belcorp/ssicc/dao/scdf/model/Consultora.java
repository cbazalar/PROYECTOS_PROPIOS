/*
 * Created on 03/11/2005 03:55:37 PM biz.belcorp.ssicc.model.Consultora
 */
package biz.belcorp.ssicc.dao.scdf.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * TODO Include class descripcion here.
 * <p>
 * <a href="Consultora.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 * @struts.form include-all="false" extends="BaseEditForm"
 * @hibernate.class table="INT_CONSU" dynamic-update="true"
 */

public class Consultora extends AuditableBaseObject implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 4927679470456766103L;

	private String codigoPais;
    
    /*private String codigoCanal;
    
    private String codigoSubcanal;*/
    
    private String codigo;
    
    private String codigoSubgerencia;
    
    private String codigoRegion;
    
    private String codigoZona;
    
    private String nombre;
    
    private String documentoIdentidad;
    
    private String telefono;
    
    private String condicion;
    
    private String statusTransferencia;
    
	
	/**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof Consultora)) {
            return false;
        }
        Consultora rhs = (Consultora) object;
        return new EqualsBuilder().append(this.codigoPais, rhs.codigoPais).append(this.statusTransferencia, rhs.statusTransferencia)
        		.append(this.codigo, rhs.codigo).append(this.codigoRegion, rhs.codigoRegion).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-2026197927, -998135449)
        		.append(this.codigoPais).append(this.statusTransferencia).append(this.codigo)
        		.append(this.codigoRegion).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("codigoPais", this.codigoPais)
                        .append("statusTransferencia",this.statusTransferencia).append("codigo", this.codigo)
						.append("codigoRegion", this.codigoRegion).toString();
    }

	/**
	 * @return Returns the codigo.
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo The codigo to set.
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the codigoRegion.
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion The codigoRegion to set.
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return Returns the codigoSubgerencia.
	 */
	public String getCodigoSubgerencia() {
		return codigoSubgerencia;
	}

	/**
	 * @param codigoSubgerencia The codigoSubgerencia to set.
	 */
	public void setCodigoSubgerencia(String codigoSubgerencia) {
		this.codigoSubgerencia = codigoSubgerencia;
	}

	/**
	 * @return Returns the codigoZona.
	 */
	public String getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona The codigoZona to set.
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	/**
	 * @return Returns the condicion.
	 */
	public String getCondicion() {
		return condicion;
	}

	/**
	 * @param condicion The condicion to set.
	 */
	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

	/**
	 * @return Returns the documentoIdentidad.
	 */
	public String getDocumentoIdentidad() {
		return documentoIdentidad;
	}

	/**
	 * @param documentoIdentidad The documentoIdentidad to set.
	 */
	public void setDocumentoIdentidad(String documentoIdentidad) {
		this.documentoIdentidad = documentoIdentidad;
	}

	/**
	 * @return Returns the statusTransferencia.
	 */
	public String getStatusTransferencia() {
		return statusTransferencia;
	}

	/**
	 * @param statusTransferencia The statusTransferencia to set.
	 */
	public void setStatusTransferencia(String estado) {
		this.statusTransferencia = estado;
	}

	/**
	 * @return Returns the nombre.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre The nombre to set.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return Returns the telefono.
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono The telefono to set.
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}