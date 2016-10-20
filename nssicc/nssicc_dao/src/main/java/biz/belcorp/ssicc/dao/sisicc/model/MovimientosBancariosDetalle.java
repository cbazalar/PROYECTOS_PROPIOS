package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class MovimientosBancariosDetalle extends AuditableBaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4899759293110439089L;
	private String codigoPais;
	private long consecutivo;
	private long numeroFacturaBoleta;
	private Date   fechaPago;
	private String importePago;
	private String observacion;
	private String numeroCupon;
	private String codigoConsultora;
	private String digitoChequeo;
	private String oficinaRecaudadora;
	private String nombreOficina;
	private String tipoTransaccion;
	private String numeroDocumento;
	private String horario;
	private String usuarioProceso;
	private Date   fechaProceso;
	private String horaProceso;
	private String estado;
	private double importePagoAplicado;
	private double importePagoPendiente;
	private double importeRecaudoGenerado;
	private double importePercepcion;
	private String statusMovimiento;
	private String codigoPlanilla;
	private String numeroLoteInterno;
	private String numeroLoteExterno;
	private String codigoTipoOrigenDatos;
	
	/**
	 * @return Returns the observacion.
	 */
	public String getObservacion() {
		return observacion;
	}
	/**
	 * @param observacion The observacion to set.
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	/**
	 * @return Returns the codigoConsultora.
	 */
	public String getCodigoConsultora() {
		return codigoConsultora;
	}
	/**
	 * @param codigoConsultora The codigoConsultora to set.
	 */
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
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
	 * @return Returns the consecutivo.
	 */
	public long getConsecutivo() {
		return consecutivo;
	}
	/**
	 * @param consecutivo The consecutivo to set.
	 */
	public void setConsecutivo(long consecutivo) {
		this.consecutivo = consecutivo;
	}
	/**
	 * @return Returns the digitoChequeo.
	 */
	public String getDigitoChequeo() {
		return digitoChequeo;
	}
	/**
	 * @param digitoChequeo The digitoChequeo to set.
	 */
	public void setDigitoChequeo(String digitoChequeo) {
		this.digitoChequeo = digitoChequeo;
	}
	/**
	 * @return Returns the estado.
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado The estado to set.
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * @return Returns the fechaPago.
	 */
	public Date getFechaPago() {
		return fechaPago;
	}
	/**
	 * @param fechaPago The fechaPago to set.
	 */
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	/**
	 * @return Returns the fechaProceso.
	 */
	public Date getFechaProceso() {
		return fechaProceso;
	}
	/**
	 * @param fechaProceso The fechaProceso to set.
	 */
	public void setFechaProceso(Date fechaProceso) {
		this.fechaProceso = fechaProceso;
	}
	/**
	 * @return Returns the horaProceso.
	 */
	public String getHoraProceso() {
		return horaProceso;
	}
	/**
	 * @param horaProceso The horaProceso to set.
	 */
	public void setHoraProceso(String horaProceso) {
		this.horaProceso = horaProceso;
	}
	/**
	 * @return Returns the horario.
	 */
	public String getHorario() {
		return horario;
	}
	/**
	 * @param horario The horario to set.
	 */
	public void setHorario(String horario) {
		this.horario = horario;
	}
	/**
	 * @return Returns the nombreOficina.
	 */
	public String getNombreOficina() {
		return nombreOficina;
	}
	/**
	 * @param nombreOficina The nombreOficina to set.
	 */
	public void setNombreOficina(String nombreOficina) {
		this.nombreOficina = nombreOficina;
	}
	/**
	 * @return Returns the numeroCupon.
	 */
	public String getNumeroCupon() {
		return numeroCupon;
	}
	/**
	 * @param numeroCupon The numeroCupon to set.
	 */
	public void setNumeroCupon(String numeroCupon) {
		this.numeroCupon = numeroCupon;
	}
	/**
	 * @return Returns the numeroDocumento.
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	/**
	 * @param numeroDocumento The numeroDocumento to set.
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	/**
	 * @return Returns the numeroFacturaBoleta.
	 */
	public long getNumeroFacturaBoleta() {
		return numeroFacturaBoleta;
	}
	/**
	 * @param numeroFacturaBoleta The numeroFacturaBoleta to set.
	 */
	public void setNumeroFacturaBoleta(long numeroFacturaBoleta) {
		this.numeroFacturaBoleta = numeroFacturaBoleta;
	}
	/**
	 * @return Returns the oficinaRecaudadora.
	 */
	public String getOficinaRecaudadora() {
		return oficinaRecaudadora;
	}
	/**
	 * @param oficinaRecaudadora The oficinaRecaudadora to set.
	 */
	public void setOficinaRecaudadora(String oficinaRecaudadora) {
		this.oficinaRecaudadora = oficinaRecaudadora;
	}
	/**
	 * @return Returns the tipoTransaccion.
	 */
	public String getTipoTransaccion() {
		return tipoTransaccion;
	}
	/**
	 * @param tipoTransaccion The tipoTransaccion to set.
	 */
	public void setTipoTransaccion(String tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}
	/**
	 * @return Returns the usuarioProceso.
	 */
	public String getUsuarioProceso() {
		return usuarioProceso;
	}
	/**
	 * @param usuarioProceso The usuarioProceso to set.
	 */
	public void setUsuarioProceso(String usuarioProceso) {
		this.usuarioProceso = usuarioProceso;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof MovimientosBancariosDetalle)) {
			return false;
		}
		MovimientosBancariosDetalle rhs = (MovimientosBancariosDetalle) object;
		return new EqualsBuilder()
				.append(this.digitoChequeo, rhs.digitoChequeo).append(
						this.horario, rhs.horario).append(
						this.codigoConsultora, rhs.codigoConsultora).append(
						this.oficinaRecaudadora, rhs.oficinaRecaudadora)
				.append(this.horaProceso, rhs.horaProceso).append(
						this.auditInfo, rhs.auditInfo).append(
						this.tipoTransaccion, rhs.tipoTransaccion).append(
						this.consecutivo, rhs.consecutivo).append(
						this.fechaPago, rhs.fechaPago).append(this.importePago,
						rhs.importePago)
				.append(this.codigoPais, rhs.codigoPais).append(
						this.observacion, rhs.observacion).append(
						this.numeroLoteInterno, rhs.numeroLoteInterno).append(
						this.nombreOficina, rhs.nombreOficina).append(
						this.estado, rhs.estado).append(this.fechaProceso,
						rhs.fechaProceso).append(this.numeroFacturaBoleta,
						rhs.numeroFacturaBoleta).append(this.usuarioProceso,
						rhs.usuarioProceso).append(this.numeroDocumento,
						rhs.numeroDocumento).append(this.numeroCupon,
						rhs.numeroCupon).isEquals();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(1732059285, 362616899).append(
				this.digitoChequeo).append(this.horario).append(
				this.codigoConsultora).append(this.oficinaRecaudadora).append(
				this.horaProceso).append(this.auditInfo).append(
				this.tipoTransaccion).append(this.consecutivo).append(
				this.fechaPago).append(this.importePago)
				.append(this.codigoPais).append(this.observacion).append(
						this.numeroLoteInterno).append(this.nombreOficina).append(
						this.estado).append(this.fechaProceso).append(
						this.numeroFacturaBoleta).append(this.usuarioProceso)
				.append(this.numeroDocumento).append(this.numeroCupon)
				.toHashCode();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("numeroCupon", this.numeroCupon).append("codigoPais",
						this.codigoPais).append("digitoChequeo",
						this.digitoChequeo).append("nombreOficina",
						this.nombreOficina).append("usuarioProceso",
						this.usuarioProceso)
				.append("auditInfo", this.auditInfo).append("horaProceso",
						this.horaProceso).append("oficinaRecaudadora",
						this.oficinaRecaudadora)
				.append("horario", this.horario).append("numeroFacturaBoleta",
						this.numeroFacturaBoleta).append("codigoConsultora",
						this.codigoConsultora).append("consecutivo",
						this.consecutivo).append("numeroDocumento",
						this.numeroDocumento).append("fechaPago",
						this.fechaPago).append("observacion", this.observacion)
				.append("estado", this.estado).append("numeroLote",
						this.numeroLoteInterno)
				.append("importePago", this.importePago).append("fechaProceso",
						this.fechaProceso).append("tipoTransaccion",
						this.tipoTransaccion).toString();
	}
	/**
	 * @return Returns the importePago.
	 */
	public String getImportePago() {
		return importePago;
	}
	/**
	 * @param importePago The importePago to set.
	 */
	public void setImportePago(String importePago) {
		this.importePago = importePago;
	}
	public String getStatusMovimiento() {
		return statusMovimiento;
	}
	public void setStatusMovimiento(String statusMovimiento) {
		this.statusMovimiento = statusMovimiento;
	}
	/**
	 * @return Returns the importePagoAplicado.
	 */
	public double getImportePagoAplicado() {
		return importePagoAplicado;
	}
	/**
	 * @param importePagoAplicado The importePagoAplicado to set.
	 */
	public void setImportePagoAplicado(double importePagoAplicado) {
		this.importePagoAplicado = importePagoAplicado;
	}
	/**
	 * @return Returns the importePagoPendiente.
	 */
	public double getImportePagoPendiente() {
		return importePagoPendiente;
	}
	/**
	 * @param importePagoPendiente The importePagoPendiente to set.
	 */
	public void setImportePagoPendiente(double importePagoPendiente) {
		this.importePagoPendiente = importePagoPendiente;
	}
	/**
	 * @return Returns the importePercepcion.
	 */
	public double getImportePercepcion() {
		return importePercepcion;
	}
	/**
	 * @param importePercepcion The importePercepcion to set.
	 */
	public void setImportePercepcion(double importePercepcion) {
		this.importePercepcion = importePercepcion;
	}
	/**
	 * @return Returns the importeRecaudoGenerado.
	 */
	public double getImporteRecaudoGenerado() {
		return importeRecaudoGenerado;
	}
	/**
	 * @param importeRecaudoGenerado The importeRecaudoGenerado to set.
	 */
	public void setImporteRecaudoGenerado(double importeRecaudoGenerado) {
		this.importeRecaudoGenerado = importeRecaudoGenerado;
	}
	/**
	 * @return Returns the codigoPlanilla.
	 */
	public String getCodigoPlanilla() {
		return codigoPlanilla;
	}
	/**
	 * @param codigoPlanilla The codigoPlanilla to set.
	 */
	public void setCodigoPlanilla(String codigoPlanilla) {
		this.codigoPlanilla = codigoPlanilla;
	}
	/**
	 * @return Returns the numeroLoteInterno.
	 */
	public String getNumeroLoteInterno() {
		return numeroLoteInterno;
	}
	/**
	 * @param numeroLoteInterno The numeroLoteInterno to set.
	 */
	public void setNumeroLoteInterno(String numeroLoteInterno) {
		this.numeroLoteInterno = numeroLoteInterno;
	}
	
	/**
	 * @return Returns the codigoTipoOrigenDatos.
	 */
	public String getCodigoTipoOrigenDatos() {
		return codigoTipoOrigenDatos;
	}
	/**
	 * @param codigoTipoOrigenDatos The codigoTipoOrigenDatos to set.
	 */
	public void setCodigoTipoOrigenDatos(String codigoTipoOrigenDatos) {
		this.codigoTipoOrigenDatos = codigoTipoOrigenDatos;
	}
	/**
	 * @return Returns the numeroLoteExterno.
	 */
	public String getNumeroLoteExterno() {
		return numeroLoteExterno;
	}
	/**
	 * @param numeroLoteExterno The numeroLoteExterno to set.
	 */
	public void setNumeroLoteExterno(String numeroLoteExterno) {
		this.numeroLoteExterno = numeroLoteExterno;
	}
	
	

}
