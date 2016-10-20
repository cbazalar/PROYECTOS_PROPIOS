package biz.belcorp.ssicc.dao.spusicc.sto.model;

import java.io.Serializable;

/**
 * @author <a href="mailto:nlopez@csigcomt.com">Nicols Lpez</a>
 *
 */
public class IngresoMetas implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codPais;
	private String codCompania;
	private String codCliente;
	private String numDocumento;
	private String numLine;
	private String fechaProceso;
	private String codCampanaProc;
	private String tipMeta;
	private String montoMeta;
	private String direccion;
	private String indMotivoRechazo;
	private String numSecuencia;
	private String numLote;
	private String codCampanaInicio;
	private String codigoRegionArribo;
	private String codigoZonaArribo;
	private String codigoEstado;
	private String detalle;
	private String descripcionEstado;
	
	/**
	 * @return descripcionEstado
	 */
	public String getDescripcionEstado() {
		return descripcionEstado;
	}
	/**
	 * @param descripcionEstado
	 */
	public void setDescripcionEstado(String descripcionEstado) {
		this.descripcionEstado = descripcionEstado;
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
	 * @return Returns the codCompania.
	 */
	public String getCodCompania() {
		return codCompania;
	}
	/**
	 * @param codCompania The codCompania to set.
	 */
	public void setCodCompania(String codCompania) {
		this.codCompania = codCompania;
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
	 * @return Returns the fechaProceso.
	 */
	public String getFechaProceso() {
		return fechaProceso;
	}
	/**
	 * @param fechaProceso The fechaProceso to set.
	 */
	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}
	/**
	 * @return Returns the indMotivoRechazo.
	 */
	public String getIndMotivoRechazo() {
		return indMotivoRechazo;
	}
	/**
	 * @param indMotivoRechazo The indMotivoRechazo to set.
	 */
	public void setIndMotivoRechazo(String indMotivoRechazo) {
		this.indMotivoRechazo = indMotivoRechazo;
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
	/**
	 * @return numLine
	 */
	public String getNumLine() {
		return numLine;
	}
	/**
	 * @param numLine
	 */
	public void setNumLine(String numLine) {
		this.numLine = numLine;
	}
	/**
	 * @return codCampanaProc
	 */
	public String getCodCampanaProc() {
		return codCampanaProc;
	}
	/**
	 * @param codCampanaProc
	 */
	public void setCodCampanaProc(String codCampanaProc) {
		this.codCampanaProc = codCampanaProc;
	}
	/**
	 * @return tipMeta
	 */
	public String getTipMeta() {
		return tipMeta;
	}
	/**
	 * @param tipMeta
	 */
	public void setTipMeta(String tipMeta) {
		this.tipMeta = tipMeta;
	}
	/**
	 * @return montoMeta
	 */
	public String getMontoMeta() {
		return montoMeta;
	}
	/**
	 * @param montoMeta
	 */
	public void setMontoMeta(String montoMeta) {
		this.montoMeta = montoMeta;
	}
	/**
	 * @return direccion
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * @param direccion
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * @return codCampanaInicio
	 */
	public String getCodCampanaInicio() {
		return codCampanaInicio;
	}
	/**
	 * @param codCampanaInicio
	 */
	public void setCodCampanaInicio(String codCampanaInicio) {
		this.codCampanaInicio = codCampanaInicio;
	}
	/**
	 * @return codigoRegionArribo
	 */
	public String getCodigoRegionArribo() {
		return codigoRegionArribo;
	}
	/**
	 * @param codigoRegionArribo
	 */
	public void setCodigoRegionArribo(String codigoRegionArribo) {
		this.codigoRegionArribo = codigoRegionArribo;
	}
	/**
	 * @return codigoEstado
	 */
	public String getCodigoEstado() {
		return codigoEstado;
	}
	/**
	 * @param codigoEstado
	 */
	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}
	
}
