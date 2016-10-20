/**
 * 
 */
package biz.belcorp.ssicc.dao.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextdoliva
 *
 */
public class ReclamoDigitadoDetalle extends AuditableBaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoEmpresa;
	private String numeroDocumento;	
	private String codigoCliente;
	private String tipoReferencia;	
	
	private String descripcionTipoReferencia;
	
	private String codigoVentaDevuelve;
	private String codigoVentaDesea;
	private String cantidadProductosDevuelve;
	private String cantidadProductosDesea;
	private String estadoProceso;
	private String codigoMotivoRechazo;
	private String motivoSPV;
	
	private String descripcionMotivoSPV;
	
	private String codigoOperador;
	private String codigoTipoOperacion;
	private String codigoTipoDocumento;
	private String codigoZona;
	private String codigoRegion;	
	
	private String codigoPeriodo;
	
	private int numeroLinea;
	
	private String numeroLote;

	private String codigoMotivoRechazoDef;
	
	/**
	 * @return the codigoMotivoRechazoDef
	 */
	public String getCodigoMotivoRechazoDef() {
		return codigoMotivoRechazoDef;
	}

	/**
	 * @param codigoMotivoRechazoDef the codigoMotivoRechazoDef to set
	 */
	public void setCodigoMotivoRechazoDef(String codigoMotivoRechazoDef) {
		this.codigoMotivoRechazoDef = codigoMotivoRechazoDef;
	}

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
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
	 * @return the codigoEmpresa
	 */
	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}

	/**
	 * @param codigoEmpresa the codigoEmpresa to set
	 */
	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	/**
	 * @return the numeroDocumento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * @param numeroDocumento the numeroDocumento to set
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente the codigoCliente to set
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * @return the tipoReferencia
	 */
	public String getTipoReferencia() {
		return tipoReferencia;
	}

	/**
	 * @param tipoReferencia the tipoReferencia to set
	 */
	public void setTipoReferencia(String tipoReferencia) {
		this.tipoReferencia = tipoReferencia;
	}

	/**
	 * @return the codigoVentaDevuelve
	 */
	public String getCodigoVentaDevuelve() {
		return codigoVentaDevuelve;
	}

	/**
	 * @param codigoVentaDevuelve the codigoVentaDevuelve to set
	 */
	public void setCodigoVentaDevuelve(String codigoVentaDevuelve) {
		this.codigoVentaDevuelve = codigoVentaDevuelve;
	}

	/**
	 * @return the codigoVentaDesea
	 */
	public String getCodigoVentaDesea() {
		return codigoVentaDesea;
	}

	/**
	 * @param codigoVentaDesea the codigoVentaDesea to set
	 */
	public void setCodigoVentaDesea(String codigoVentaDesea) {
		this.codigoVentaDesea = codigoVentaDesea;
	}

	/**
	 * @return the cantidadProductosDevuelve
	 */
	public String getCantidadProductosDevuelve() {
		return cantidadProductosDevuelve;
	}

	/**
	 * @param cantidadProductosDevuelve the cantidadProductosDevuelve to set
	 */
	public void setCantidadProductosDevuelve(String cantidadProductosDevuelve) {
		this.cantidadProductosDevuelve = cantidadProductosDevuelve;
	}

	/**
	 * @return the cantidadProductosDesea
	 */
	public String getCantidadProductosDesea() {
		return cantidadProductosDesea;
	}

	/**
	 * @param cantidadProductosDesea the cantidadProductosDesea to set
	 */
	public void setCantidadProductosDesea(String cantidadProductosDesea) {
		this.cantidadProductosDesea = cantidadProductosDesea;
	}

	/**
	 * @return the estadoProceso
	 */
	public String getEstadoProceso() {
		return estadoProceso;
	}

	/**
	 * @param estadoProceso the estadoProceso to set
	 */
	public void setEstadoProceso(String estadoProceso) {
		this.estadoProceso = estadoProceso;
	}

	/**
	 * @return the codigoMotivoRechazo
	 */
	public String getCodigoMotivoRechazo() {
		return codigoMotivoRechazo;
	}

	/**
	 * @param codigoMotivoRechazo the codigoMotivoRechazo to set
	 */
	public void setCodigoMotivoRechazo(String codigoMotivoRechazo) {
		this.codigoMotivoRechazo = codigoMotivoRechazo;
	}

	/**
	 * @return the motivoSPV
	 */
	public String getMotivoSPV() {
		return motivoSPV;
	}

	/**
	 * @param motivoSPV the motivoSPV to set
	 */
	public void setMotivoSPV(String motivoSPV) {
		this.motivoSPV = motivoSPV;
	}

	/**
	 * @return the codigoOperador
	 */
	public String getCodigoOperador() {
		return codigoOperador;
	}

	/**
	 * @param codigoOperador the codigoOperador to set
	 */
	public void setCodigoOperador(String codigoOperador) {
		this.codigoOperador = codigoOperador;
	}

	/**
	 * @return the codigoTipoOperacion
	 */
	public String getCodigoTipoOperacion() {
		return codigoTipoOperacion;
	}

	/**
	 * @param codigoTipoOperacion the codigoTipoOperacion to set
	 */
	public void setCodigoTipoOperacion(String codigoTipoOperacion) {
		this.codigoTipoOperacion = codigoTipoOperacion;
	}

	/**
	 * @return the codigoTipoDocumento
	 */
	public String getCodigoTipoDocumento() {
		return codigoTipoDocumento;
	}

	/**
	 * @param codigoTipoDocumento the codigoTipoDocumento to set
	 */
	public void setCodigoTipoDocumento(String codigoTipoDocumento) {
		this.codigoTipoDocumento = codigoTipoDocumento;
	}

	/**
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	/**
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return the numeroLinea
	 */
	public int getNumeroLinea() {
		return numeroLinea;
	}

	/**
	 * @param numeroLinea the numeroLinea to set
	 */
	public void setNumeroLinea(int numeroLinea) {
		this.numeroLinea = numeroLinea;
	}

	/**
	 * @return the descripcionTipoReferencia
	 */
	public String getDescripcionTipoReferencia() {
		return descripcionTipoReferencia;
	}

	/**
	 * @param descripcionTipoReferencia the descripcionTipoReferencia to set
	 */
	public void setDescripcionTipoReferencia(String descripcionTipoReferencia) {
		this.descripcionTipoReferencia = descripcionTipoReferencia;
	}

	/**
	 * @return the descripcionMotivoSPV
	 */
	public String getDescripcionMotivoSPV() {
		return descripcionMotivoSPV;
	}

	/**
	 * @param descripcionMotivoSPV the descripcionMotivoSPV to set
	 */
	public void setDescripcionMotivoSPV(String descripcionMotivoSPV) {
		this.descripcionMotivoSPV = descripcionMotivoSPV;
	}

	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

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
	
	

}
