package biz.belcorp.ssicc.dao.spusicc.sessionexperte.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextrramirez 
 *
 */
public class CicloDePrograma extends AuditableBaseObject 
    implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String codigoPais;
	private String codigoPrograma;
	private int codigoAnho;
	private String tipoCiclo;
	private String codigoCiclo;
	private String campanhaInicial;
	private String campanhaFinal;
	private String estadoRegistro;
	private String descTipoCiclo;
	/**
	 * @return Returns the descTipoCiclo.
	 */
	public String getDescTipoCiclo() {
		return descTipoCiclo;
	}

	/**
	 * @param descTipoCiclo The descTipoCiclo to set.
	 */
	public void setDescTipoCiclo(String descTipoCiclo) {
		this.descTipoCiclo = descTipoCiclo;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof CicloDePrograma)) {
			return false;
		}

		CicloDePrograma ciclo = (CicloDePrograma) object;

		return new EqualsBuilder()	.append(this.codigoPais,	ciclo.codigoPais)
									.append(this.codigoPrograma,	ciclo.codigoPrograma)
									.append(this.codigoAnho,	ciclo.codigoAnho)
									.append(this.tipoCiclo,	ciclo.tipoCiclo)
									.append(this.codigoCiclo,	ciclo.codigoCiclo)
									.append(this.campanhaInicial,	ciclo.campanhaInicial)
									.append(this.campanhaFinal,	ciclo.campanhaFinal)
									.append(this.estadoRegistro,	ciclo.estadoRegistro)
									.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(2022562655, 96184653)	
				.append(this.codigoPais)
				.append(this.codigoPrograma)
				.append(this.codigoAnho)
				.append(this.tipoCiclo)
				.append(this.codigoCiclo)
				.append(this.campanhaInicial)
				.append(this.campanhaFinal)
				.append(this.estadoRegistro)
				.toHashCode();
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append(this.codigoPais)
				.append(this.codigoPrograma)
				.append(this.codigoAnho)
				.append(this.tipoCiclo)
				.append(this.codigoCiclo)
				.append(this.campanhaInicial)
				.append(this.campanhaFinal)
				.append(this.estadoRegistro)
				.toString();
	}

	/**
	 * @return Returns the serialVersionUID.
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
	 * @return Returns the campanhaFinal.
	 */
	public String getCampanhaFinal() {
		return campanhaFinal;
	}

	/**
	 * @param campanhaFinal The campanhaFinal to set.
	 */
	public void setCampanhaFinal(String campanhaFinal) {
		this.campanhaFinal = campanhaFinal;
	}

	/**
	 * @return Returns the campanhaInicial.
	 */
	public String getCampanhaInicial() {
		return campanhaInicial;
	}

	/**
	 * @param campanhaInicial The campanhaInicial to set.
	 */
	public void setCampanhaInicial(String campanhaInicial) {
		this.campanhaInicial = campanhaInicial;
	}

	/**
	 * @return Returns the codigoAnho.
	 */
	public int getCodigoAnho() {
		return codigoAnho;
	}

	/**
	 * @param codigoAnho The codigoAnho to set.
	 */
	public void setCodigoAnho(int codigoAnho) {
		this.codigoAnho = codigoAnho;
	}

	/**
	 * @return Returns the codigoCiclo.
	 */
	public String getCodigoCiclo() {
		return codigoCiclo;
	}

	/**
	 * @param codigoCiclo The codigoCiclo to set.
	 */
	public void setCodigoCiclo(String codigoCiclo) {
		this.codigoCiclo = codigoCiclo;
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
	 * @return Returns the codigoPrograma.
	 */
	public String getCodigoPrograma() {
		return codigoPrograma;
	}

	/**
	 * @param codigoPrograma The codigoPrograma to set.
	 */
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}

	/**
	 * @return Returns the estadoRegistro.
	 */
	public String getEstadoRegistro() {
		return estadoRegistro;
	}

	/**
	 * @param estadoRegistro The estadoRegistro to set.
	 */
	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}

	/**
	 * @return Returns the tipoCiclo.
	 */
	public String getTipoCiclo() {
		return tipoCiclo;
	}

	/**
	 * @param tipoCiclo The tipoCiclo to set.
	 */
	public void setTipoCiclo(String tipoCiclo) {
		this.tipoCiclo = tipoCiclo;
	}

}