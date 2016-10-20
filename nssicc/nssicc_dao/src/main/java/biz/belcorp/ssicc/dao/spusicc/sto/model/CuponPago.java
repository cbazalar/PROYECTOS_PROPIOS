package biz.belcorp.ssicc.dao.spusicc.sto.model;

import java.io.Serializable;

/* @author <a href="mailto:croman@belcorp.biz">Cristhian Roman</a>
 * 
 */

public class CuponPago implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codPais;
	private String codCompania;
	private String codCliente;
	private String numDocumento;
	private String fechaProceso;
	private String codPeriodo;
	private String codRegion;
	private String codZona;
	private String indEstaProceso;
	private String indMotivoRechazo;
	private String impValor;
	private String valorDeuda;
	private String estadoCupon;
	private String indRechazoSello;
	private String codTipoDocumento;
	private String numSecuencia;
	private String numLote;
	private String detalle;
	private String codigoVerificador;
	private String codigoZonaArribo;
	
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
	 * @return Returns the estadoCupon.
	 */
	public String getEstadoCupon() {
		return estadoCupon;
	}
	/**
	 * @param estadoCupon The estadoCupon to set.
	 */
	public void setEstadoCupon(String estadoCupon) {
		this.estadoCupon = estadoCupon;
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
	 * @return Returns the impValor.
	 */
	public String getImpValor() {
		return impValor;
	}
	/**
	 * @param impValor The impValor to set.
	 */
	public void setImpValor(String impValor) {
		this.impValor = impValor;
	}
	/**
	 * @return Returns the indEstaProceso.
	 */
	public String getIndEstaProceso() {
		return indEstaProceso;
	}
	/**
	 * @param indEstaProceso The indEstaProceso to set.
	 */
	public void setIndEstaProceso(String indEstaProceso) {
		this.indEstaProceso = indEstaProceso;
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
	 * @return Returns the indRechazoSello.
	 */
	public String getIndRechazoSello() {
		return indRechazoSello;
	}
	/**
	 * @param indRechazoSello The indRechazoSello to set.
	 */
	public void setIndRechazoSello(String indRechazoSello) {
		this.indRechazoSello = indRechazoSello;
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
	 * @return Returns the valorDeuda.
	 */
	public String getValorDeuda() {
		return valorDeuda;
	}
	/**
	 * @param valorDeuda The valorDeuda to set.
	 */
	public void setValorDeuda(String valorDeuda) {
		this.valorDeuda = valorDeuda;
	}
	public String getCodigoVerificador() {
		return codigoVerificador;
	}
	public void setCodigoVerificador(String codigoVerificador) {
		this.codigoVerificador = codigoVerificador;
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
	
	
}
