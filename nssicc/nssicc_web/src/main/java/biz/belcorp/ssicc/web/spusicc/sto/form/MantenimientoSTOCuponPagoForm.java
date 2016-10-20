package biz.belcorp.ssicc.web.spusicc.sto.form;

import java.io.Serializable;
import java.util.Date;

public class MantenimientoSTOCuponPagoForm extends BaseMantenimientoSTOGestionForm
implements Serializable {
	
	private static final long serialVersionUID = 1927371721098329227L;
	
	
	private String codigoPais;
	private String codCompania;
	private String codCliente;
	private String numDocumento;
	private String impValor;
	private String fechaProceso;
	private Date fechaProcesoDate;
	private String codRegion;
	private String codZona;
	private String indEstaProceso;
	private String indMotivoRechazo;
	private String codPeriodo;
	private String valorDeuda;
	private String estadoCupon;
	private String indRechazoSello;
	private String codTipoDocumento;
	private String numSecuencia;
	private String numLote;
	private String fecProceso;
	private String detalle;
	private String oidPais;
	private String codigoVerificador;
	private String nombreConsultora;
	private String salirPantalla = "N";
	
	private String codigoZonaArribo;
	
	
	public String getCodCliente() {
		return codCliente;
	}
	
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}

	public String getCodCompania() {
		return codCompania;
	}
	
	public void setCodCompania(String codCompania) {
		this.codCompania = codCompania;
	}
	
	public String getCodigoPais() {
		return codigoPais;
	}
	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	public String getCodPeriodo() {
		return codPeriodo;
	}
	
	public void setCodPeriodo(String codPeriodo) {
		this.codPeriodo = codPeriodo;
	}

	public String getCodTipoDocumento() {
		return codTipoDocumento;
	}
	
	public void setCodTipoDocumento(String codTipoDocumento) {
		this.codTipoDocumento = codTipoDocumento;
	}
	
	public String getFechaProceso() {
		return fechaProceso;
	}
	
	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}
	
	public String getIndEstaProceso() {
		return indEstaProceso;
	}
	
	public void setIndEstaProceso(String indEstaProceso) {
		this.indEstaProceso = indEstaProceso;
	}
	
	public String getIndMotivoRechazo() {
		return indMotivoRechazo;
	}
	
	public void setIndMotivoRechazo(String indMotivoRechazo) {
		this.indMotivoRechazo = indMotivoRechazo;
	}

	public String getNumDocumento() {
		return numDocumento;
	}
	
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

	public String getNumSecuencia() {
		return numSecuencia;
	}
	
	public void setNumSecuencia(String numSecuencia) {
		this.numSecuencia = numSecuencia;
	}
	/**
	 * @return Returns the numLote.
	 */
	public String getNumLote() {
		return numLote;
	}
	
	public void setNumLote(String numLote) {
		this.numLote = numLote;
	}
	
	public String getFecProceso() {
		return fecProceso;
	}
	
	public void setFecProceso(String fecProceso) {
		this.fecProceso = fecProceso;
	}
	
	public String getDetalle() {
		return detalle;
	}
	
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
	public String getOidPais() {
		return oidPais;
	}
	
	public void setOidPais(String oidPais) {
		this.oidPais = oidPais;
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
	public String getNombreConsultora() {
		return nombreConsultora;
	}
	public void setNombreConsultora(String nombreConsultora) {
		this.nombreConsultora = nombreConsultora;
	}
	/**
	 * @return the salirPantalla
	 */
	public String getSalirPantalla() {
		return salirPantalla;
	}
	/**
	 * @param salirPantalla the salirPantalla to set
	 */
	public void setSalirPantalla(String salirPantalla) {
		this.salirPantalla = salirPantalla;
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

	public Date getFechaProcesoDate() {
		return fechaProcesoDate;
	}

	public void setFechaProcesoDate(Date fechaProcesoDate) {
		this.fechaProcesoDate = fechaProcesoDate;
	}
	
	

}
