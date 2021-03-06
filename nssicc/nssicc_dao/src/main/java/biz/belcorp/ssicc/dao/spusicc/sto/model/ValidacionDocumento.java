package biz.belcorp.ssicc.dao.spusicc.sto.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;



public class ValidacionDocumento implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codigoPais;
    private String codigoTipo;
    private String codValidacion;
    private String codValidacionAnterior;    
    private String executeProcedure;
   
    
	public ValidacionDocumento() {
		super();
		this.codigoPais="";
		this.codigoTipo="";
		this.codValidacion="";
		this.codValidacionAnterior=""; 
		
		this.executeProcedure="";
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigoTipo
	 */
	public String getCodigoTipo() {
		return codigoTipo;
	}

	/**
	 * @param codigoTipo the codigoTipo to set
	 */
	public void setCodigoTipo(String codigoTipo) {
		this.codigoTipo = codigoTipo;
	}

	/**
	 * @return the codValidacion
	 */
	public String getCodValidacion() {
		return codValidacion;
	}

	/**
	 * @param codValidacion the codValidacion to set
	 */
	public void setCodValidacion(String codValidacion) {
		this.codValidacion = codValidacion;
	}

	/**
	 * @return the codValidacionAnterior
	 */
	public String getCodValidacionAnterior() {
		return codValidacionAnterior;
	}

	/**
	 * @param codValidacionAnterior the codValidacionAnterior to set
	 */
	public void setCodValidacionAnterior(String codValidacionAnterior) {
		if  (codValidacionAnterior == null) this.codValidacionAnterior="";
		else this.codValidacionAnterior = codValidacionAnterior;
	}


	/**
	 * @return the executeProcedure
	 */
	public String getExecuteProcedure() {
		return executeProcedure;
	}

	/**
	 * @param executeProcedure the executeProcedure to set
	 */
	public void setExecuteProcedure(String executeProcedure) {
		this.executeProcedure = executeProcedure;
	}

	/**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("codigoPais", this.codigoPais)
                                        .append("codigoTipo", this.codigoTipo)
                                        .append("codValidacion", this.codValidacion)
                                        .append("codValidacionAnterior", this.codValidacionAnterior)                                       
                                        .toString();
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof ValidacionDocumento)) {
            return false;
        }

        ValidacionDocumento rhs = (ValidacionDocumento) object;

        return new EqualsBuilder().append(this.codigoTipo,
            rhs.codigoTipo)
                                  .append(this.codValidacion,
            rhs.codValidacion).append(this.codigoPais, rhs.codigoPais)
                                  .append(this.codValidacionAnterior, rhs.codValidacionAnterior)                                  
           .isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
  public int hashCode() {
        return new HashCodeBuilder(1054922849, -863815175).append(this.codigoTipo)
                                                          .append(this.codValidacion)
                                                          .append(this.codigoPais)
                                                          .append(this.codValidacionAnterior)                                                         
                                                          .toHashCode();
    }

	
}
