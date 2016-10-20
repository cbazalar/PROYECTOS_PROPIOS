package biz.belcorp.ssicc.dao.spusicc.pedidos.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class SolicitudConsolidadoCabecera extends AuditableBaseObject implements Serializable{
	
	private static final long serialVersionUID = 4278268215530510344L;
	private String id															;
	private String codigoPais									;	
	private String codigoPeriodo									;
	private String codigoCliente									;
	private String fechaSolicitud									;
	private String numeroClientes									;
	private String tipoSolicitud 									;
	private String subAcceso											;
	private String acceso													;
	private String tipoDespacho										;
	private String estadoProceso									;
	private String nombreCliente									;
	private String codigoRegion										;
	private String descripcionRegion							;
	private String codigoZona 										;
	private String descripcionZona 								;
	private String codigoTerritorio 							;
	private String tipoOrden 											;
	private String valorMontoPedido								;
	private String valorSaldoDeudor								;
	private String indErrorRechazada							;
	private String indErrorDeuda									;
	private String indErrorMontoMinimo						;
	private String indErrorMontoMaximo 						;
	private String indErrorUnidadMaximo						;
	private String indErrorSegundoPedido					;
	private String indErrorCabecSinDetalle				;
	private String indErrorOCSProcesada						;
	private String indErrorOCSBloqueoAdmin				;
	private String indErrorOCSBloqueada						;
	private String indErrorAdminCartera						;
	private String indErrorOCSBloqueoFinanciero 	;
	private String indErrorCompMontoMin 					;
	private String codigoMarca										;
	private String descripcionMarca 							;
	private String codigoCanal 										;
	private String descripcionCanal 							;
	private String numeroLote;
	
	/**
	 * @return the numeroLote
	 */
	public String getNumeroLote() {
		return numeroLote;
	}

	/**
	 * @param numeroLote the numeroLote to set
	 */
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	private List codigos 							;	
	
    /**
     * Holds value of property indicadorAdmCartera.
     */
    protected boolean indicadorAdmCartera;
    
    protected boolean indOCSBloqueada;    
	
	private String observaciones 							;	
	
	private String estadoDeuda	 							;

	public String getAcceso() {
		return acceso;
	}

	public void setAcceso(String acceso) {
		this.acceso = acceso;
	}

	public String getCodigoCanal() {
		return codigoCanal;
	}

	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getCodigoMarca() {
		return codigoMarca;
	}

	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	public String getCodigoRegion() {
		return codigoRegion;
	}

	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	public String getCodigoTerritorio() {
		return codigoTerritorio;
	}

	public void setCodigoTerritorio(String codigoTerritorio) {
		this.codigoTerritorio = codigoTerritorio;
	}

	public String getCodigoZona() {
		return codigoZona;
	}

	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	public String getDescripcionCanal() {
		return descripcionCanal;
	}

	public void setDescripcionCanal(String descripcionCanal) {
		this.descripcionCanal = descripcionCanal;
	}

	public String getDescripcionMarca() {
		return descripcionMarca;
	}

	public void setDescripcionMarca(String descripcionMarca) {
		this.descripcionMarca = descripcionMarca;
	}

	public String getDescripcionRegion() {
		return descripcionRegion;
	}

	public void setDescripcionRegion(String descripcionRegion) {
		this.descripcionRegion = descripcionRegion;
	}

	public String getDescripcionZona() {
		return descripcionZona;
	}

	public void setDescripcionZona(String descripcionZona) {
		this.descripcionZona = descripcionZona;
	}

	public String getEstadoDeuda() {
		return estadoDeuda;
	}

	public void setEstadoDeuda(String estadoDeuda) {
		this.estadoDeuda = estadoDeuda;
	}

	public String getEstadoProceso() {
		return estadoProceso;
	}

	public void setEstadoProceso(String estadoProceso) {
		this.estadoProceso = estadoProceso;
	}

	public String getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIndErrorAdminCartera() {
		return indErrorAdminCartera;
	}

	public void setIndErrorAdminCartera(String indErrorAdminCartera) {
		this.indErrorAdminCartera = indErrorAdminCartera;
	}

	public String getIndErrorCabecSinDetalle() {
		return indErrorCabecSinDetalle;
	}

	public void setIndErrorCabecSinDetalle(String indErrorCabecSinDetalle) {
		this.indErrorCabecSinDetalle = indErrorCabecSinDetalle;
	}

	public String getIndErrorCompMontoMin() {
		return indErrorCompMontoMin;
	}

	public void setIndErrorCompMontoMin(String indErrorCompMontoMin) {
		this.indErrorCompMontoMin = indErrorCompMontoMin;
	}

	public String getIndErrorDeuda() {
		return indErrorDeuda;
	}

	public void setIndErrorDeuda(String indErrorDeuda) {
		this.indErrorDeuda = indErrorDeuda;
	}

	public String getIndErrorMontoMaximo() {
		return indErrorMontoMaximo;
	}

	public void setIndErrorMontoMaximo(String indErrorMontoMaximo) {
		this.indErrorMontoMaximo = indErrorMontoMaximo;
	}

	public String getIndErrorMontoMinimo() {
		return indErrorMontoMinimo;
	}

	public void setIndErrorMontoMinimo(String indErrorMontoMinimo) {
		this.indErrorMontoMinimo = indErrorMontoMinimo;
	}

	public String getIndErrorOCSBloqueada() {
		return indErrorOCSBloqueada;
	}

	public void setIndErrorOCSBloqueada(String indErrorOCSBloqueada) {
		this.indErrorOCSBloqueada = indErrorOCSBloqueada;
	}

	public String getIndErrorOCSBloqueoAdmin() {
		return indErrorOCSBloqueoAdmin;
	}

	public void setIndErrorOCSBloqueoAdmin(String indErrorOCSBloqueoAdmin) {
		this.indErrorOCSBloqueoAdmin = indErrorOCSBloqueoAdmin;
	}

	public String getIndErrorOCSBloqueoFinanciero() {
		return indErrorOCSBloqueoFinanciero;
	}

	public void setIndErrorOCSBloqueoFinanciero(String indErrorOCSBloqueoFinanciero) {
		this.indErrorOCSBloqueoFinanciero = indErrorOCSBloqueoFinanciero;
	}

	public String getIndErrorOCSProcesada() {
		return indErrorOCSProcesada;
	}

	public void setIndErrorOCSProcesada(String indErrorOCSProcesada) {
		this.indErrorOCSProcesada = indErrorOCSProcesada;
	}

	public String getIndErrorRechazada() {
		return indErrorRechazada;
	}

	public void setIndErrorRechazada(String indErrorRechazada) {
		this.indErrorRechazada = indErrorRechazada;
	}

	public String getIndErrorSegundoPedido() {
		return indErrorSegundoPedido;
	}

	public void setIndErrorSegundoPedido(String indErrorSegundoPedido) {
		this.indErrorSegundoPedido = indErrorSegundoPedido;
	}

	public String getIndErrorUnidadMaximo() {
		return indErrorUnidadMaximo;
	}

	public void setIndErrorUnidadMaximo(String indErrorUnidadMaximo) {
		this.indErrorUnidadMaximo = indErrorUnidadMaximo;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getNumeroClientes() {
		return numeroClientes;
	}

	public void setNumeroClientes(String numeroClientes) {
		this.numeroClientes = numeroClientes;
	}

	public String getSubAcceso() {
		return subAcceso;
	}

	public void setSubAcceso(String subAcceso) {
		this.subAcceso = subAcceso;
	}

	public String getTipoDespacho() {
		return tipoDespacho;
	}

	public void setTipoDespacho(String tipoDespacho) {
		this.tipoDespacho = tipoDespacho;
	}

	public String getTipoOrden() {
		return tipoOrden;
	}

	public void setTipoOrden(String tipoOrden) {
		this.tipoOrden = tipoOrden;
	}

	public String getTipoSolicitud() {
		return tipoSolicitud;
	}

	public void setTipoSolicitud(String tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}

	public String getValorMontoPedido() {
		return valorMontoPedido;
	}

	public void setValorMontoPedido(String valorMontoPedido) {
		this.valorMontoPedido = valorMontoPedido;
	}

	public String getValorSaldoDeudor() {
		return valorSaldoDeudor;
	}

	public void setValorSaldoDeudor(String valorSaldoDeudor) {
		this.valorSaldoDeudor = valorSaldoDeudor;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof SolicitudConsolidadoCabecera)) {
			return false;
		}
		SolicitudConsolidadoCabecera rhs = (SolicitudConsolidadoCabecera) object;
		return new EqualsBuilder().append(
				this.indErrorOCSBloqueoAdmin, rhs.indErrorOCSBloqueoAdmin)
				.append(this.indErrorOCSProcesada, rhs.indErrorOCSProcesada)
				.append(this.acceso, rhs.acceso).append(
						this.indErrorOCSBloqueoFinanciero,
						rhs.indErrorOCSBloqueoFinanciero).append(
						this.tipoDespacho, rhs.tipoDespacho).append(
						this.auditInfo, rhs.auditInfo).append(
						this.descripcionZona, rhs.descripcionZona).append(
						this.tipoOrden, rhs.tipoOrden).append(this.id, rhs.id)
				.append(this.indErrorMontoMinimo, rhs.indErrorMontoMinimo)
				.append(this.codigoCliente, rhs.codigoCliente).append(
						this.descripcionMarca, rhs.descripcionMarca).append(
						this.indErrorAdminCartera, rhs.indErrorAdminCartera)
				.append(this.indErrorCabecSinDetalle,
						rhs.indErrorCabecSinDetalle).append(this.codigoRegion,
						rhs.codigoRegion).append(this.estadoProceso,
						rhs.estadoProceso).append(this.estadoDeuda,
						rhs.estadoDeuda).append(this.descripcionRegion,
						rhs.descripcionRegion).append(this.valorMontoPedido,
						rhs.valorMontoPedido).append(this.indErrorDeuda,
						rhs.indErrorDeuda).append(this.nombreCliente,
						rhs.nombreCliente).append(this.indErrorRechazada,
						rhs.indErrorRechazada).append(
						this.indErrorOCSBloqueada, rhs.indErrorOCSBloqueada)
				.append(this.codigoTerritorio, rhs.codigoTerritorio).append(
						this.descripcionCanal, rhs.descripcionCanal).append(
						this.indErrorSegundoPedido, rhs.indErrorSegundoPedido)
				.append(this.tipoSolicitud, rhs.tipoSolicitud).append(
						this.indErrorCompMontoMin, rhs.indErrorCompMontoMin)
				.append(this.codigoMarca, rhs.codigoMarca).append(
						this.fechaSolicitud, rhs.fechaSolicitud).append(
						this.indErrorMontoMaximo, rhs.indErrorMontoMaximo)
				.append(this.valorSaldoDeudor, rhs.valorSaldoDeudor).append(
						this.subAcceso, rhs.subAcceso).append(
						this.numeroClientes, rhs.numeroClientes).append(
						this.codigoZona, rhs.codigoZona).append(
						this.codigoCanal, rhs.codigoCanal).append(
						this.codigoPeriodo, rhs.codigoPeriodo).append(
						this.indErrorUnidadMaximo, rhs.indErrorUnidadMaximo)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-246808451, 1740566931).append(this.indErrorOCSBloqueoAdmin).append(
				this.indErrorOCSProcesada).append(this.acceso).append(
				this.indErrorOCSBloqueoFinanciero).append(this.tipoDespacho)
				.append(this.auditInfo).append(this.descripcionZona).append(
						this.tipoOrden).append(this.id).append(
						this.indErrorMontoMinimo).append(this.codigoCliente)
				.append(this.descripcionMarca)
				.append(this.indErrorAdminCartera).append(
						this.indErrorCabecSinDetalle).append(this.codigoRegion)
				.append(this.estadoProceso).append(this.estadoDeuda).append(
						this.descripcionRegion).append(this.valorMontoPedido)
				.append(this.indErrorDeuda).append(this.nombreCliente).append(
						this.indErrorRechazada).append(
						this.indErrorOCSBloqueada)
				.append(this.codigoTerritorio).append(this.descripcionCanal)
				.append(this.indErrorSegundoPedido).append(this.tipoSolicitud)
				.append(this.indErrorCompMontoMin).append(this.codigoMarca)
				.append(this.fechaSolicitud).append(this.indErrorMontoMaximo)
				.append(this.valorSaldoDeudor).append(this.subAcceso).append(
						this.numeroClientes).append(this.codigoZona).append(
						this.codigoCanal).append(this.codigoPeriodo).append(
						this.indErrorUnidadMaximo).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("codigoRegion",
				this.codigoRegion).append("indErrorAdminCartera",
				this.indErrorAdminCartera).append("indErrorSegundoPedido",
				this.indErrorSegundoPedido).append("id", this.id).append(
				"indErrorUnidadMaximo", this.indErrorUnidadMaximo).append(
				"indErrorOCSBloqueoFinanciero",
				this.indErrorOCSBloqueoFinanciero).append("codigoCliente",
				this.codigoCliente).append("indErrorCompMontoMin",
				this.indErrorCompMontoMin).append("indErrorMontoMinimo",
				this.indErrorMontoMinimo).append("codigoMarca",
				this.codigoMarca).append("indErrorOCSBloqueoAdmin",
				this.indErrorOCSBloqueoAdmin).append("auditInfo",
				this.auditInfo).append("estadoDeuda", this.estadoDeuda).append(
				"indErrorOCSBloqueada", this.indErrorOCSBloqueada).append(
				"indErrorOCSProcesada", this.indErrorOCSProcesada).append(
				"descripcionRegion", this.descripcionRegion).append(
				"codigoPeriodo", this.codigoPeriodo).append("estadoProceso",
				this.estadoProceso).append("subAcceso", this.subAcceso).append(
				"valorSaldoDeudor", this.valorSaldoDeudor).append(
				"descripcionZona", this.descripcionZona).append(
				"indErrorCabecSinDetalle", this.indErrorCabecSinDetalle)
				.append("numeroClientes", this.numeroClientes).append(
						"codigoZona", this.codigoZona).append("tipoDespacho",
						this.tipoDespacho).append("nombreCliente",
						this.nombreCliente).append("descripcionCanal",
						this.descripcionCanal).append("indErrorRechazada",
						this.indErrorRechazada).append("codigoTerritorio",
						this.codigoTerritorio).append("valorMontoPedido",
						this.valorMontoPedido).append("fechaSolicitud",
						this.fechaSolicitud)
				.append("tipoOrden", this.tipoOrden).append(
						"indErrorMontoMaximo", this.indErrorMontoMaximo)
				.append("acceso", this.acceso).append("codigoCanal",
						this.codigoCanal).append("descripcionMarca",
						this.descripcionMarca).append("indErrorDeuda",
						this.indErrorDeuda).append("tipoSolicitud",
						this.tipoSolicitud).toString();
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public boolean isIndicadorAdmCartera() {
		return indicadorAdmCartera;
	}

	public void setIndicadorAdmCartera(boolean indicadorAdmCartera) {
		this.indicadorAdmCartera = indicadorAdmCartera;
	}

	public List getCodigos() {
		return codigos;
	}

	public void setCodigos(List codigos) {
		this.codigos = codigos;
	}

	public boolean isIndOCSBloqueada() {
		return indOCSBloqueada;
	}

	public void setIndOCSBloqueada(boolean indOCSBloqueada) {
		this.indOCSBloqueada = indOCSBloqueada;
	}
	
	
}
