package biz.belcorp.ssicc.dao.spusicc.sto.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;



public class DatosCliente implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoCliente;
    private String indActivo;
    private String tipoCliente;
    private String subTipoCliente; 
    private String primerApellido;
    private String segundoApellido;
    private String primerNombre;
    private String segundoNombre;
    private String status;
    private String bloqueo;
    private String zona;
    private String territorio;
    private String direccion;
    private String telefono;
    
   
    
    
    
    
	/**
	 * @return Returns the codigoCliente.
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente The codigoCliente to set.
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	

	/**
	 * @return Returns the indActivo.
	 */
	public String getIndActivo() {
		return indActivo;
	}

	/**
	 * @param indActivo The indActivo to set.
	 */
	public void setIndActivo(String indActivo) {
		this.indActivo = indActivo;
	}

	/**
	 * @return Returns the primerApellido.
	 */
	public String getPrimerApellido() {
		return primerApellido;
	}

	/**
	 * @param primerApellido The primerApellido to set.
	 */
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	/**
	 * @return Returns the primerNombre.
	 */
	public String getPrimerNombre() {
		return primerNombre;
	}

	/**
	 * @param primerNombre The primerNombre to set.
	 */
	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	/**
	 * @return Returns the segundoApellido.
	 */
	public String getSegundoApellido() {
		return segundoApellido;
	}

	/**
	 * @param segundoApellido The segundoApellido to set.
	 */
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	/**
	 * @return Returns the segundoNombre.
	 */
	public String getSegundoNombre() {
		return segundoNombre;
	}

	/**
	 * @param segundoNombre The segundoNombre to set.
	 */
	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	/**
	 * @return Returns the subTipoCliente.
	 */
	public String getSubTipoCliente() {
		return subTipoCliente;
	}

	/**
	 * @param subTipoCliente The subTipoCliente to set.
	 */
	public void setSubTipoCliente(String subTipoCliente) {
		this.subTipoCliente = subTipoCliente;
	}

	/**
	 * @return Returns the tipoCliente.
	 */
	public String getTipoCliente() {
		return tipoCliente;
	}

	/**
	 * @param tipoCliente The tipoCliente to set.
	 */
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	/**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("codigoCliente", this.codigoCliente)
                                        .append("indActivo", this.indActivo)
                                        .append("tipoCliente", this.tipoCliente)
                                        .append("subTipoCliente", this.subTipoCliente)
                                        .append("primerApellido", this.primerApellido)
                                        .append("segundoApellido",this.segundoApellido)
                                        .append("primerNombre", this.primerNombre)
                                        .append("segundoNombre",this.segundoNombre)
                                        .toString();
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof DatosCliente)) {
            return false;
        }

        DatosCliente rhs = (DatosCliente) object;

        return new EqualsBuilder().append(this.codigoCliente,
            rhs.codigoCliente)
                                  .append(this.indActivo,
            rhs.indActivo).append(this.tipoCliente, rhs.tipoCliente)
                                  .append(this.subTipoCliente, rhs.subTipoCliente)
                                  .append(this.primerApellido, rhs.primerApellido)
                                  .append(this.segundoApellido, rhs.segundoApellido)
                                  .append(this.primerNombre, rhs.primerNombre)
                                  .append(this.segundoNombre, rhs.segundoNombre)

           .isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
  public int hashCode() {
        return new HashCodeBuilder(1054922849, -863815175).append(this.codigoCliente)
                                                          .append(this.indActivo)
                                                          .append(this.tipoCliente)
                                                          .append(this.subTipoCliente)
                                                          .append(this.primerApellido)
                                                          .append(this.segundoApellido)
                                                         
                                                          .append(this.primerNombre)
                                                         .append(this.segundoNombre)
                                                          .toHashCode();
    }

	/**
	 * @return Returns the bloqueo.
	 */
	public String getBloqueo() {
		return bloqueo;
	}

	/**
	 * @param bloqueo The bloqueo to set.
	 */
	public void setBloqueo(String bloqueo) {
		this.bloqueo = bloqueo;
	}

	/**
	 * @return Returns the direccion.
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion The direccion to set.
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
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

	/**
	 * @return Returns the territorio.
	 */
	public String getTerritorio() {
		return territorio;
	}

	/**
	 * @param territorio The territorio to set.
	 */
	public void setTerritorio(String territorio) {
		this.territorio = territorio;
	}

	/**
	 * @return Returns the zona.
	 */
	public String getZona() {
		return zona;
	}

	/**
	 * @param zona The zona to set.
	 */
	public void setZona(String zona) {
		this.zona = zona;
	}

}
