package biz.belcorp.ssicc.dao.spusicc.sto.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;



public class ValidacionErrorDocumento implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoPais;
    private String codigoTipo;
    private String codValidacion;
    private String codValidacionAnterior; 
    private String indicadorRequisito;
    private String indicadorGestion; 
    private String executeProcedure;
    private String numSecuencia;
  
    

   
    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

   
   
    /**
	 * @return Returns the codigoTipo.
	 */
	public String getCodigoTipo() {
		return codigoTipo;
	}

	/**
	 * @param codigoTipo The codigoTipo to set.
	 */
	public void setCodigoTipo(String codigoTipo) {
		this.codigoTipo = codigoTipo;
	}

	/**
	 * @return Returns the codValidacion.
	 */
	public String getCodValidacion() {
		return codValidacion;
	}

	/**
	 * @param codValidacion The codValidacion to set.
	 */
	public void setCodValidacion(String codValidacion) {
		this.codValidacion = codValidacion;
	}

	/**
	 * @return Returns the codValidacionAnterior.
	 */
	public String getCodValidacionAnterior() {
		return codValidacionAnterior;
	}

	/**
	 * @param codValidacionAnterior The codValidacionAnterior to set.
	 */
	public void setCodValidacionAnterior(String codValidacionAnterior) {
		this.codValidacionAnterior = codValidacionAnterior;
	}

	/**
	 * @return Returns the indicadorGestion.
	 */
	public String getIndicadorGestion() {
		return indicadorGestion;
	}

	/**
	 * @param indicadorGestion The indicadorGestion to set.
	 */
	public void setIndicadorGestion(String indicadorGestion) {
		this.indicadorGestion = indicadorGestion;
	}

	/**
	 * @return Returns the indicadorRequisito.
	 */
	public String getIndicadorRequisito() {
		return indicadorRequisito;
	}

	/**
	 * @param indicadorRequisito The indicadorRequisito to set.
	 */
	public void setIndicadorRequisito(String indicadorRequisito) {
		this.indicadorRequisito = indicadorRequisito;
	}

	/**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("codigoPais", this.codigoPais)
                                        .append("codigoTipo", this.codigoTipo)
                                        .append("codValidacion", this.codValidacion)
                                        .append("codValidacionAnterior", this.codValidacionAnterior)
                                        .append("indicadorRequisito", this.indicadorRequisito)
                                        .append("indicadorGestion",
            this.indicadorGestion)
                                        .toString();
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof ValidacionErrorDocumento)) {
            return false;
        }

        ValidacionErrorDocumento rhs = (ValidacionErrorDocumento) object;

        return new EqualsBuilder().append(this.codigoTipo,
            rhs.codigoTipo)
                                  .append(this.codValidacion,
            rhs.codValidacion).append(this.codigoPais, rhs.codigoPais)
                                  .append(this.codValidacionAnterior, rhs.codValidacionAnterior)
                                  .append(this.indicadorRequisito, rhs.indicadorRequisito)
                                  .append(this.indicadorGestion, rhs.indicadorGestion)
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
                                                          .append(this.indicadorRequisito)
                                                          .append(this.indicadorGestion)
                                                         
                                                          //.append(this.auditInfo)
                                                         
                                                          .toHashCode();
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
	 * @return Returns the numSecuencia.
	 */
	public String getNumSecuencia() {
		return numSecuencia;
	}

	/**
	 * @param numSecuencia The numSecuencia to set.
	 */
	public void setNumSecuencia(String numSecuencia) {
		this.numSecuencia = numSecuencia;
	}
	
	
}
