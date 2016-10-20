package biz.belcorp.ssicc.dao.spusicc.sessionexperte.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author <a href="mailto:ivega@belcorp.biz">Isabel Vega Palomino</a>
 *
 */
public class ProgramaSessionExperte extends AuditableBaseObject implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String codigoPais;
	private String codigoPrograma;
	private String campanhaInicial;
	private String campanhaFinal;
	private String estadoRegistro;
	private String codigoMarca;
	private String codigoCanal;
	private int codigoAnho;
	
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof ProgramaSessionExperte)) {
			return false;
		}

		ProgramaSessionExperte prog = (ProgramaSessionExperte) object;

		return new EqualsBuilder()	.append(this.codigoPais,		prog.codigoPais)
									.append(this.codigoPrograma,	prog.codigoPrograma)
									.append(this.campanhaInicial,	prog.campanhaInicial)
									.append(this.campanhaFinal,		prog.campanhaFinal)
									.append(this.estadoRegistro,	prog.estadoRegistro)
									.append(this.codigoMarca,		prog.codigoMarca)
									.append(this.codigoCanal,		prog.codigoCanal)
									.append(this.codigoAnho,		prog.codigoAnho)	
									.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(2022562655, 96184653)	
				.append(this.codigoPais)
				.append(this.codigoPrograma)
				.append(this.campanhaInicial)
				.append(this.campanhaFinal)
				.append(this.estadoRegistro)
				.append(this.codigoMarca)
				.append(this.codigoCanal)
				.append(this.codigoAnho)
				.toHashCode();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("codigoPais", codigoPais)
		.append("codigoPrograma", codigoPrograma)
		.append("campanhaInicial",campanhaInicial)
		.append("campanhaFinal",campanhaFinal)
		.append("estadoRegistro",estadoRegistro)
		.append("codigoMarca",codigoMarca)
		.append("codigoCanal",codigoCanal)
		.append("codigoAnho",codigoAnho)
		.toString();
	}

	public String getCampanhaFinal() {
		return campanhaFinal;
	}

	public void setCampanhaFinal(String campanhaFinal) {
		this.campanhaFinal = campanhaFinal;
	}

	public String getCampanhaInicial() {
		return campanhaInicial;
	}

	public void setCampanhaInicial(String campanhaInicial) {
		this.campanhaInicial = campanhaInicial;
	}

	public String getCodigoCanal() {
		return codigoCanal;
	}

	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	public String getCodigoMarca() {
		return codigoMarca;
	}

	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getCodigoPrograma() {
		return codigoPrograma;
	}

	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}

	public String getEstadoRegistro() {
		return estadoRegistro;
	}

	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

}
