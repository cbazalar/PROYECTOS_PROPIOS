package biz.belcorp.ssicc.web.spusicc.sto.form;

import java.io.Serializable;
import java.util.Date;

public class MantenimientoSTOSolicitudPostVentaCabeceraForm  extends BaseMantenimientoSTOGestionForm
implements Serializable{	
	
	private static final long serialVersionUID = 7131157947133241437L;	
	
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
	//private String codZona;
	private String estadoProceso;
	private String codMotivoRechazo;
	private String observaciones;
	private String detalle;
	private String nombre;
	private String [] clasConsultora;	
	private String oidPais;
	private String periodoReferencia;
	
	private String codZonaArribo;
	private String indicadorGuardar;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return Returns the codCliente.
	 */
	public String getCodCliente() {
		return codCliente;
	}
	/**
	 * 
	 * @param codCliente The codCliente to set.
	 */
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
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
	 * @return Returns the estadoProceso.
	 */
	public String getEstadoProceso() {
		return estadoProceso;
	}
	
	public void setEstadoProceso(String estadoProceso) {
		this.estadoProceso = estadoProceso;
	}
	
	public String getNumDocumento() {
		return numDocumento;
	}
	
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}
	
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
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
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
	 * @return Returns the observaciones.
	 */
	public String getObservaciones() {
		return observaciones;
	}
	
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	/**
	 * @return Returns the detalle.
	 */
	public String getDetalle() {
		return detalle;
	}
	/**
	 * @param detalle The detalle to set.
	 */
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public String[] getClasConsultora() {
		return clasConsultora;
	}
	public void setClasConsultora(String[] clasConsultora) {
		this.clasConsultora = clasConsultora;
	}
	public String getOidPais() {
		return oidPais;
	}
	public void setOidPais(String oidPais) {
		this.oidPais = oidPais;
	}
	public String getPeriodoReferencia() {
		return periodoReferencia;
	}
	public void setPeriodoReferencia(String periodoReferencia) {
		this.periodoReferencia = periodoReferencia;
	}
	
	public String getCodZonaArribo() {
		return codZonaArribo;
	}
	
	public void setCodZonaArribo(String codZonaArribo) {
		this.codZonaArribo = codZonaArribo;
	}
	
	public String getIndicadorGuardar() {
		return indicadorGuardar;
	}
	
	public void setIndicadorGuardar(String indicadorGuardar) {
		this.indicadorGuardar = indicadorGuardar;
	}

}
