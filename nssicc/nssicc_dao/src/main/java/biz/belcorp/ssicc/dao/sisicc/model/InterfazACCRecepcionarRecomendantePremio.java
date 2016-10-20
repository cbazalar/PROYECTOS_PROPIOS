package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class InterfazACCRecepcionarRecomendantePremio extends AuditableBaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3816384086699064574L;
	private String codigoPais;
	private String codigoCompania;
	private String codigoRecomendante;
	private String tipoDocIdentidad;
	private String numeroDocIdentidad;
	private String numeroConcurso;
	private Integer versionConcurso;
	private Integer numeroPremio;
	private Integer nivel;
	private String campanaSolicitud;
	private String indicadorAsignacionPremio;
	private String codigoRecomendada;
	private String fecha;
	private String hora;
	private String usuario;
	private String indicadorProcesado;
	
	/**
	 * @return Returns the campanaSolicitud.
	 */
	public String getCampanaSolicitud() {
		return campanaSolicitud;
	}
	/**
	 * @param campanaSolicitud The campanaSolicitud to set.
	 */
	public void setCampanaSolicitud(String campanaSolicitud) {
		this.campanaSolicitud = campanaSolicitud;
	}
	/**
	 * @return Returns the codigoCompania.
	 */
	public String getCodigoCompania() {
		return codigoCompania;
	}
	/**
	 * @param codigoCompania The codigoCompania to set.
	 */
	public void setCodigoCompania(String codigoCompania) {
		this.codigoCompania = codigoCompania;
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
	 * @return Returns the codigoRecomendada.
	 */
	public String getCodigoRecomendada() {
		return codigoRecomendada;
	}
	/**
	 * @param codigoRecomendada The codigoRecomendada to set.
	 */
	public void setCodigoRecomendada(String codigoRecomendada) {
		this.codigoRecomendada = codigoRecomendada;
	}
	/**
	 * @return Returns the codigoRecomendante.
	 */
	public String getCodigoRecomendante() {
		return codigoRecomendante;
	}
	/**
	 * @param codigoRecomendante The codigoRecomendante to set.
	 */
	public void setCodigoRecomendante(String codigoRecomendante) {
		this.codigoRecomendante = codigoRecomendante;
	}
	/**
	 * @return Returns the fecha.
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * @param fecha The fecha to set.
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return Returns the hora.
	 */
	public String getHora() {
		return hora;
	}
	/**
	 * @param hora The hora to set.
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}
	/**
	 * @return Returns the indicadorAsignacionPremio.
	 */
	public String getIndicadorAsignacionPremio() {
		return indicadorAsignacionPremio;
	}
	/**
	 * @param indicadorAsignacionPremio The indicadorAsignacionPremio to set.
	 */
	public void setIndicadorAsignacionPremio(String indicadorAsignacionPremio) {
		this.indicadorAsignacionPremio = indicadorAsignacionPremio;
	}
	/**
	 * @return Returns the nivel.
	 */
	public Integer getNivel() {
		return nivel;
	}
	/**
	 * @param nivel The nivel to set.
	 */
	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}
	/**
	 * @return Returns the numeroConcurso.
	 */
	public String getNumeroConcurso() {
		return numeroConcurso;
	}
	/**
	 * @param numeroConcurso The numeroConcurso to set.
	 */
	public void setNumeroConcurso(String numeroConcurso) {
		this.numeroConcurso = numeroConcurso;
	}
	/**
	 * @return Returns the numeroDocIdentidad.
	 */
	public String getNumeroDocIdentidad() {
		return numeroDocIdentidad;
	}
	/**
	 * @param numeroDocIdentidad The numeroDocIdentidad to set.
	 */
	public void setNumeroDocIdentidad(String numeroDocIdentidad) {
		this.numeroDocIdentidad = numeroDocIdentidad;
	}
	/**
	 * @return Returns the numeroPremio.
	 */
	public Integer getNumeroPremio() {
		return numeroPremio;
	}
	/**
	 * @param numeroPremio The numeroPremio to set.
	 */
	public void setNumeroPremio(Integer numeroPremio) {
		this.numeroPremio = numeroPremio;
	}
	/**
	 * @return Returns the tipoDocIdentidad.
	 */
	public String getTipoDocIdentidad() {
		return tipoDocIdentidad;
	}
	/**
	 * @param tipoDocIdentidad The tipoDocIdentidad to set.
	 */
	public void setTipoDocIdentidad(String tipoDocIdentidad) {
		this.tipoDocIdentidad = tipoDocIdentidad;
	}
	/**
	 * @return Returns the usuario.
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario The usuario to set.
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return Returns the versionConcurso.
	 */
	public Integer getVersionConcurso() {
		return versionConcurso;
	}
	/**
	 * @param versionConcurso The versionConcurso to set.
	 */
	public void setVersionConcurso(Integer versionConcurso) {
		this.versionConcurso = versionConcurso;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof MovimientosBancariosDetalle)) {
			return false;
		}
		InterfazACCRecepcionarRecomendantePremio rhs = (InterfazACCRecepcionarRecomendantePremio) object;
		return new EqualsBuilder()
				.append(this.tipoDocIdentidad, rhs.tipoDocIdentidad).append(
						this.numeroDocIdentidad, rhs.numeroDocIdentidad).append(
						this.campanaSolicitud, rhs.campanaSolicitud).append(
						this.codigoRecomendante, rhs.codigoRecomendante).isEquals();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(1732059285, 362616899).append(
				this.tipoDocIdentidad).append(this.campanaSolicitud).append(
				this.campanaSolicitud).append(this.codigoRecomendante).toHashCode();
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("codigoPais", this.codigoPais).append("codigoCompania",
						this.codigoCompania).append("codigoRecomendante",
						this.codigoRecomendante).append("tipoDocIdentidad",
						this.tipoDocIdentidad).append("numeroDocIdentidad",
						this.numeroDocIdentidad)
				.append("numeroConcurso", this.numeroConcurso).append("versionConcurso",
						this.versionConcurso).append("numeroPremio",
						this.numeroPremio)
				.append("nivel", this.nivel).append("campanaSolicitud",
						this.campanaSolicitud).append("indicadorAsignacionPremio",
						this.indicadorAsignacionPremio).append("codigoRecomendada",
						this.codigoRecomendada).append("fecha",
						this.fecha).append("hora",
						this.hora).append("usuario", this.usuario)
				.append("indicadorProcesado", this.indicadorProcesado).toString();
	}
	/**
	 * @return Returns the indicadorProcesado.
	 */
	public String getIndicadorProcesado() {
		return indicadorProcesado;
	}
	/**
	 * @param indicadorProcesado The indicadorProcesado to set.
	 */
	public void setIndicadorProcesado(String indicadorProcesado) {
		this.indicadorProcesado = indicadorProcesado;
	}
	
}
