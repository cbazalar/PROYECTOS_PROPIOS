package biz.belcorp.ssicc.dao.spusicc.sto.model;

import java.io.Serializable;
import java.util.Date;



public class SolicitudPostVentaCabecera implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codPeriodo;
	private String codCliente;
	private String numLote;
	private String codTipoDocumento;
	private String numDocumento;
	private String numSecuencia;
	private String codCia;
	private String numDocuRUC;
	private String tipoSolicitud;
	private String codSubAcceso;
	private String accFisi;
	private Date fechaProcesoDoc;
	private String codRegion;
	private String codZona;
	private String estadoProceso;
	private String codMotivoRechazo;
	private String observaciones;
	private String nombre;
	private String periodoReferencia;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return Returns the accFisi.
	 */
	public String getAccFisi() {
		return accFisi;
	}
	/**
	 * @param accFisi The accFisi to set.
	 */
	public void setAccFisi(String accFisi) {
		this.accFisi = accFisi;
	}
	/**
	 * @return Returns the codCia.
	 */
	public String getCodCia() {
		return codCia;
	}
	/**
	 * @param codCia The codCia to set.
	 */
	public void setCodCia(String codCia) {
		this.codCia = codCia;
	}
	/**
	 * @return Returns the codCliente.
	 */
	public String getCodCliente() {
		return codCliente;
	}
	/**
	 * @param codCliente The codCliente to set.
	 */
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
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
	 * @return Returns the codMotivoRechazo.
	 */
	public String getCodMotivoRechazo() {
		return codMotivoRechazo;
	}
	/**
	 * @param codMotivoRechazo The codMotivoRechazo to set.
	 */
	public void setCodMotivoRechazo(String codMotivoRechazo) {
		this.codMotivoRechazo = codMotivoRechazo;
	}
	/**
	 * @return Returns the codPeriodo.
	 */
	public String getCodPeriodo() {
		return codPeriodo;
	}
	/**
	 * @param codPeriodo The codPeriodo to set.
	 */
	public void setCodPeriodo(String codPeriodo) {
		this.codPeriodo = codPeriodo;
	}
	/**
	 * @return Returns the codRegion.
	 */
	public String getCodRegion() {
		return codRegion;
	}
	/**
	 * @param codRegion The codRegion to set.
	 */
	public void setCodRegion(String codRegion) {
		this.codRegion = codRegion;
	}
	/**
	 * @return Returns the codSubAcceso.
	 */
	public String getCodSubAcceso() {
		return codSubAcceso;
	}
	/**
	 * @param codSubAcceso The codSubAcceso to set.
	 */
	public void setCodSubAcceso(String codSubAcceso) {
		this.codSubAcceso = codSubAcceso;
	}
	/**
	 * @return Returns the codTipoDocumento.
	 */
	public String getCodTipoDocumento() {
		return codTipoDocumento;
	}
	/**
	 * @param codTipoDocumento The codTipoDocumento to set.
	 */
	public void setCodTipoDocumento(String codTipoDocumento) {
		this.codTipoDocumento = codTipoDocumento;
	}
	/**
	 * @return Returns the codZona.
	 */
	public String getCodZona() {
		return codZona;
	}
	/**
	 * @param codZona The codZona to set.
	 */
	public void setCodZona(String codZona) {
		this.codZona = codZona;
	}
	/**
	 * @return Returns the estadoProceso.
	 */
	public String getEstadoProceso() {
		return estadoProceso;
	}
	/**
	 * @param estadoProceso The estadoProceso to set.
	 */
	public void setEstadoProceso(String estadoProceso) {
		this.estadoProceso = estadoProceso;
	}
	/**
	 * @return Returns the fechaProcesoDoc.
	 */
	public Date getFechaProcesoDoc() {
		return fechaProcesoDoc;
	}
	/**
	 * @param fechaProcesoDoc The fechaProcesoDoc to set.
	 */
	public void setFechaProcesoDoc(Date fechaProcesoDoc) {
		this.fechaProcesoDoc = fechaProcesoDoc;
	}
	/**
	 * @return Returns the numDocumento.
	 */
	public String getNumDocumento() {
		return numDocumento;
	}
	/**
	 * @param numDocumento The numDocumento to set.
	 */
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}
	/**
	 * @return Returns the numDocuRUC.
	 */
	public String getNumDocuRUC() {
		return numDocuRUC;
	}
	/**
	 * @param numDocuRUC The numDocuRUC to set.
	 */
	public void setNumDocuRUC(String numDocuRUC) {
		this.numDocuRUC = numDocuRUC;
	}
	/**
	 * @return Returns the numLote.
	 */
	public String getNumLote() {
		return numLote;
	}
	/**
	 * @param numLote The numLote to set.
	 */
	public void setNumLote(String numLote) {
		this.numLote = numLote;
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
	/**
	 * @return Returns the tipoSolicitud.
	 */
	public String getTipoSolicitud() {
		return tipoSolicitud;
	}
	/**
	 * @param tipoSolicitud The tipoSolicitud to set.
	 */
	public void setTipoSolicitud(String tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}
	/**
	 * @return Returns the observaciones.
	 */
	public String getObservaciones() {
		return observaciones;
	}
	/**
	 * @param observaciones The observaciones to set.
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getPeriodoReferencia() {
		return periodoReferencia;
	}
	public void setPeriodoReferencia(String periodoReferencia) {
		this.periodoReferencia = periodoReferencia;
	}
	
}
