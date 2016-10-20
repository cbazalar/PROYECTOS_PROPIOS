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
public class ReclamoDigitadoCabecera extends AuditableBaseObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoEmpresa;
	private String numeroDocumento;
	private String codigoPeriodo;
	private String codigoCliente;
	private String numeroDocumentoCruce;
	private String tipoSolicitud;
	private String codigoSubAcceso;
	private String accesoFisico;
	private String fechaProcesoDocumento;
	private String codigoRegionArrivo;
	private String codigoZonaArrivo;
	private String estadoProceso;
	private String codigoMotivoRechazo;
	private String codigoTipoDocumento;
	
	
	private String indicadorExpress;
	private String indicadorOrigen;
	
	private String numeroLote;

	/* INI JR PER-SiCC-2012-0304 */
	private String indicadorCDRRechazo;
	private String observacionCDR;
	
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
	 * @return the indicadorCDRRechazo
	 */
	public String getIndicadorCDRRechazo() {
		return indicadorCDRRechazo;
	}

	/**
	 * @param indicadorCDRRechazo the indicadorCDRRechazo to set
	 */
	public void setIndicadorCDRRechazo(String indicadorCDRRechazo) {
		this.indicadorCDRRechazo = indicadorCDRRechazo;
	}

	/**
	 * @return the observacionCDR
	 */
	public String getObservacionCDR() {
		return observacionCDR;
	}
	/* FIN JR PER-SiCC-2012-0304 */

	/**
	 * @param observacionCDR the observacionCDR to set
	 */
	public void setObservacionCDR(String observacionCDR) {
		this.observacionCDR = observacionCDR;
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
	 * @return the numeroDocumentoCruce
	 */
	public String getNumeroDocumentoCruce() {
		return numeroDocumentoCruce;
	}

	/**
	 * @param numeroDocumentoCruce the numeroDocumentoCruce to set
	 */
	public void setNumeroDocumentoCruce(String numeroDocumentoCruce) {
		this.numeroDocumentoCruce = numeroDocumentoCruce;
	}

	/**
	 * @return the tipoSolicitud
	 */
	public String getTipoSolicitud() {
		return tipoSolicitud;
	}

	/**
	 * @param tipoSolicitud the tipoSolicitud to set
	 */
	public void setTipoSolicitud(String tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}

	/**
	 * @return the codigoSubAcceso
	 */
	public String getCodigoSubAcceso() {
		return codigoSubAcceso;
	}

	/**
	 * @param codigoSubAcceso the codigoSubAcceso to set
	 */
	public void setCodigoSubAcceso(String codigoSubAcceso) {
		this.codigoSubAcceso = codigoSubAcceso;
	}

	/**
	 * @return the accesoFisico
	 */
	public String getAccesoFisico() {
		return accesoFisico;
	}

	/**
	 * @param accesoFisico the accesoFisico to set
	 */
	public void setAccesoFisico(String accesoFisico) {
		this.accesoFisico = accesoFisico;
	}

	/**
	 * @return the fechaProcesoDocumento
	 */
	public String getFechaProcesoDocumento() {
		return fechaProcesoDocumento;
	}

	/**
	 * @param fechaProcesoDocumento the fechaProcesoDocumento to set
	 */
	public void setFechaProcesoDocumento(String fechaProcesoDocumento) {
		this.fechaProcesoDocumento = fechaProcesoDocumento;
	}

	/**
	 * @return the codigoRegionArrivo
	 */
	public String getCodigoRegionArrivo() {
		return codigoRegionArrivo;
	}

	/**
	 * @param codigoRegionArrivo the codigoRegionArrivo to set
	 */
	public void setCodigoRegionArrivo(String codigoRegionArrivo) {
		this.codigoRegionArrivo = codigoRegionArrivo;
	}

	/**
	 * @return the codigoZonaArrivo
	 */
	public String getCodigoZonaArrivo() {
		return codigoZonaArrivo;
	}

	/**
	 * @param codigoZonaArrivo the codigoZonaArrivo to set
	 */
	public void setCodigoZonaArrivo(String codigoZonaArrivo) {
		this.codigoZonaArrivo = codigoZonaArrivo;
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
	 * @return the indicadorExpress
	 */
	public String getIndicadorExpress() {
		return indicadorExpress;
	}

	/**
	 * @param indicadorExpress the indicadorExpress to set
	 */
	public void setIndicadorExpress(String indicadorExpress) {
		this.indicadorExpress = indicadorExpress;
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
	 * @return the indicadorOrigen
	 */
	public String getIndicadorOrigen() {
		return indicadorOrigen;
	}

	/**
	 * @param indicadorOrigen the indicadorOrigen to set
	 */
	public void setIndicadorOrigen(String indicadorOrigen) {
		this.indicadorOrigen = indicadorOrigen;
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
