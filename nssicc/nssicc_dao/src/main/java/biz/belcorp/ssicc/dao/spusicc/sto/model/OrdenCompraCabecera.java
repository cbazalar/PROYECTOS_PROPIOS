package biz.belcorp.ssicc.dao.spusicc.sto.model;

import java.io.Serializable;
import java.util.Date;



public class OrdenCompraCabecera implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codPais;
	private String codPeriodo;
	private String codCliente;
	private Date fechaSolicitud;
	private Integer numCliente;
	private String tipoSolicitud;
	private String codSubAcceso;
	private String codAcceso;
	private String tipoDespacho;
	private String estadoProceso;
	private String numLote;
	private String numDocumento;
	private String codMotivoRechazo;
	private String codRegion;
	private String codZona;
	private String numSecuencia;
	private String detalle;
	private String montoPedido;
	private String indValiExisCronograma;
	private String indValiVencCronograma;
	private String oidPeriodo;
	private String oidZona;
	private String codigoZonaArribo;
	
	/* INI JR PER-SiCC-2012-0444 */
	private String montoFlexipago;
	private String indUtilizaFlex;
	private String indAceptaFlex;
	/* FIN JR PER-SiCC-2012-0444 */
	
	private String oidMotiGes;
	private String valObseGestion;
	
	/**
	 * @return Returns the codAcceso.
	 */
	public String getCodAcceso() {
		return codAcceso;
	}
	/**
	 * @param codAcceso The codAcceso to set.
	 */
	public void setCodAcceso(String codAcceso) {
		this.codAcceso = codAcceso;
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
	 * @return Returns the codPais.
	 */
	public String getCodPais() {
		return codPais;
	}
	/**
	 * @param codPais The codPais to set.
	 */
	public void setCodPais(String codPais) {
		this.codPais = codPais;
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
	 * @return Returns the fechaSolicitud.
	 */
	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}
	/**
	 * @param fechaSolicitud The fechaSolicitud to set.
	 */
	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	/**
	 * @return Returns the numCliente.
	 */
	public Integer getNumCliente() {
		return numCliente;
	}
	/**
	 * @param numCliente The numCliente to set.
	 */
	public void setNumCliente(Integer numCliente) {
		this.numCliente = numCliente;
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
	 * @return Returns the tipoDespacho.
	 */
	public String getTipoDespacho() {
		return tipoDespacho;
	}
	/**
	 * @param tipoDespacho The tipoDespacho to set.
	 */
	public void setTipoDespacho(String tipoDespacho) {
		this.tipoDespacho = tipoDespacho;
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
	/**
	 * @return Returns the montoPedido.
	 */
	public String getMontoPedido() {
		return montoPedido;
	}
	/**
	 * @param montoPedido The montoPedido to set.
	 */
	public void setMontoPedido(String montoPedido) {
		this.montoPedido = montoPedido;
	}
	public String getIndValiExisCronograma() {
		return indValiExisCronograma;
	}
	public void setIndValiExisCronograma(String indValiExisCronograma) {
		this.indValiExisCronograma = indValiExisCronograma;
	}
	public String getIndValiVencCronograma() {
		return indValiVencCronograma;
	}
	public void setIndValiVencCronograma(String indValiVencCronograma) {
		this.indValiVencCronograma = indValiVencCronograma;
	}
	public String getOidPeriodo() {
		return oidPeriodo;
	}
	public void setOidPeriodo(String oidPeriodo) {
		this.oidPeriodo = oidPeriodo;
	}
	public String getOidZona() {
		return oidZona;
	}
	public void setOidZona(String oidZona) {
		this.oidZona = oidZona;
	}
	/**
	 * @return the codigoZonaArribo
	 */
	public String getCodigoZonaArribo() {
		return codigoZonaArribo;
	}
	/**
	 * @param codigoZonaArribo the codigoZonaArribo to set
	 */
	public void setCodigoZonaArribo(String codigoZonaArribo) {
		this.codigoZonaArribo = codigoZonaArribo;
	}
	
	/* INI JR PER-SiCC-2012-0444 */
	/**
	 * @return the montoFlexipago
	 */
	public String getMontoFlexipago() {
		return montoFlexipago;
	}
	/**
	 * @param montoFlexipago the montoFlexipago to set
	 */
	public void setMontoFlexipago(String montoFlexipago) {
		this.montoFlexipago = montoFlexipago;
	}
	/**
	 * @return the indUtilizaFlex
	 */
	public String getIndUtilizaFlex() {
		return indUtilizaFlex;
	}
	/**
	 * @param indUtilizaFlex the indUtilizaFlex to set
	 */
	public void setIndUtilizaFlex(String indUtilizaFlex) {
		this.indUtilizaFlex = indUtilizaFlex;
	}
	/**
	 * @return the indAceptaFlex
	 */
	public String getIndAceptaFlex() {
		return indAceptaFlex;
	}
	/**
	 * @param indAceptaFlex the indAceptaFlex to set
	 */
	public void setIndAceptaFlex(String indAceptaFlex) {
		this.indAceptaFlex = indAceptaFlex;
	}
	/* FIN JR PER-SiCC-2012-0444 */
	/**
	 * @return the oidMotiGes
	 */
	public String getOidMotiGes() {
		return oidMotiGes;
	}
	/**
	 * @param oidMotiGes the oidMotiGes to set
	 */
	public void setOidMotiGes(String oidMotiGes) {
		this.oidMotiGes = oidMotiGes;
	}
	/**
	 * @return the valObseGestion
	 */
	public String getValObseGestion() {
		return valObseGestion;
	}
	/**
	 * @param valObseGestion the valObseGestion to set
	 */
	public void setValObseGestion(String valObseGestion) {
		this.valObseGestion = valObseGestion;
	}
	
	
}
