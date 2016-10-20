package biz.belcorp.ssicc.web.spusicc.sto.form;

import java.io.Serializable;

public class MantenimientoSTOIngresoMetasForm extends BaseMantenimientoSTOGestionForm implements Serializable{
	
	private static final long serialVersionUID = 2844604996338858619L;
	
	
	private String codigoPais;
	private String codCompania;
	private String codCliente;
	private String numDocumento;
	private String fechaProceso;
	private String numSecuencia;
	private String numLote;
	private String fecProceso;
	private String detalle;
	private String oidPais;
	private String nombreConsultora;
	private String salirPantalla = "N";
	private String numLine;
	private String codCampanaProc;
	private String codigoZonaArribo;
	private String codigoRegionArribo;
	private String codCampanaInicio;
	private String montoMeta;
	private String tipMeta;
	private String indMotivoRechazo;
	private String codigoEstado;
	private String direccion;
	private String descripcionEstado;
	
	
	public String getDescripcionEstado() {
		return descripcionEstado;
	}

	public void setDescripcionEstado(String descripcionEstado) {
		this.descripcionEstado = descripcionEstado;
	}

	public String getCodigoEstado() {
		return codigoEstado;
	}

	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
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
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	
	/**
	 * @param codigoPais The codigoPais to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
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
	 * @return Returns the fecProceso.
	 */
	public String getFecProceso() {
		return fecProceso;
	}
	
	/**
	 * @param fecProceso The fecProceso to set.
	 */
	public void setFecProceso(String fecProceso) {
		this.fecProceso = fecProceso;
	}
	
	/**
	 * @return Returns the detalle.
	 */
	public String getDetalle() {
		return detalle;
	}
	
	/**
	 * @param detalle The detalle to set.
	 *
	 */
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
	/**
	 * @return Returns the oidPais.
	 */
	public String getOidPais() {
		return oidPais;
	}
	
	/**
	 * @param oidPais The oidPais to set.
	 */
	public void setOidPais(String oidPais) {
		this.oidPais = oidPais;
	}

	/**
	 * @return nombreConsultora
	 */
	public String getNombreConsultora() {
		return nombreConsultora;
	}
	
	/**
	 * @param nombreConsultora
	 */
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
	 * @return indMotivoRechazo
	 */
	public String getIndMotivoRechazo() {
		return indMotivoRechazo;
	}

	/**
	 * @param indMotivoRechazo
	 */
	public void setIndMotivoRechazo(String indMotivoRechazo) {
		this.indMotivoRechazo = indMotivoRechazo;
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
	
}
