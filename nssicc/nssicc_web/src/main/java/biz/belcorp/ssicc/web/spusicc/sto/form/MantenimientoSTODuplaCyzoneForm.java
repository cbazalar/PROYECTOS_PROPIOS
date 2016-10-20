package biz.belcorp.ssicc.web.spusicc.sto.form;

import java.io.Serializable;
import java.util.Date;

public class MantenimientoSTODuplaCyzoneForm extends BaseMantenimientoSTOGestionForm
implements Serializable {
	
	private static final long serialVersionUID = -3717895112890820309L;	
	
	private String codigoPais;
	private String codCompania;
	private String codCliente;
	private String numDocumento;
	private String codPeriodo;
	private String valApellido1;
	private String valApellido2;
	private String valNombre1;
	private String valNombre2;
	private String fechaNacimiento;
	private Date fechaNacimientoDate;
	private String valTelfCliente;
	private String valCeluCliente;
	private String valMailCliente;
	private String indDuplaNueva;
	private String indActuDatos;
	private String indEnvio;
	private String valRegionArribo;
	private String valZonaArribo;
	private String fechaProceso;
	private Date fechaProcesoDate;
	private String indEstaProceso;
	private String indMotivoRechazo;
	private String detalle;
	private String oidPais;
	private String codTipoDocumento;
	private String numSecuencia;
	private String numLote;
	private String fecProceso;	
	private String salirPantalla = "N";
	
	
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
	
	public String getCodTipoDocumento() {
		return codTipoDocumento;
	}
	/**
	 * @param codTipoDocumento The codTipoDocumento to set.
	 */
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

	public String getNumDocumento() {
		return numDocumento;
	}
	
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

	public String getValApellido1() {
		return valApellido1;
	}
	
	public void setValApellido1(String valApellido1) {
		this.valApellido1 = valApellido1;
	}
	
	public String getValApellido2() {
		return valApellido2;
	}
	
	public void setValApellido2(String valApellido2) {
		this.valApellido2 = valApellido2;
	}
	
	public String getValCeluCliente() {
		return valCeluCliente;
	}
	
	public void setValCeluCliente(String valCeluCliente) {
		this.valCeluCliente = valCeluCliente;
	}

	public String getValMailCliente() {
		return valMailCliente;
	}
	
	public void setValMailCliente(String valMailCliente) {
		this.valMailCliente = valMailCliente;
	}
	
	public String getValNombre1() {
		return valNombre1;
	}
	/**
	 * @param valNombre1 The valNombre1 to set.
	 */
	public void setValNombre1(String valNombre1) {
		this.valNombre1 = valNombre1;
	}
	
	/**
	 * @return Returns the valNombre2.
	 */
	public String getValNombre2() {
		return valNombre2;
	}
	/**
	 * @param valNombre2 The valNombre2 to set.
	 */
	public void setValNombre2(String valNombre2) {
		this.valNombre2 = valNombre2;
	}

	/**
	 * @return Returns the valRegionArribo.
	 */
	public String getValRegionArribo() {
		return valRegionArribo;
	}
	/**
	 * @param valRegionArribo The valRegionArribo to set.
	 */
	public void setValRegionArribo(String valRegionArribo) {
		this.valRegionArribo = valRegionArribo;
	}
	/**
	 * @return Returns the valTelfCliente.
	 */
	public String getValTelfCliente() {
		return valTelfCliente;
	}
	/**
	 * @param valTelfCliente The valTelfCliente to set.
	 */
	public void setValTelfCliente(String valTelfCliente) {
		this.valTelfCliente = valTelfCliente;
	}

	/**
	 * @return Returns the valZonaArribo.
	 */
	public String getValZonaArribo() {
		return valZonaArribo;
	}
	/**
	 * @param valZonaArribo The valZonaArribo to set.
	 */
	public void setValZonaArribo(String valZonaArribo) {
		this.valZonaArribo = valZonaArribo;
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
	
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
	public String getOidPais() {
		return oidPais;
	}
	
	public void setOidPais(String oidPais) {
		this.oidPais = oidPais;
	}
	
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public String getIndActuDatos() {
		return indActuDatos;
	}
	
	public void setIndActuDatos(String indActuDatos) {
		this.indActuDatos = indActuDatos;
	}

	public String getIndDuplaNueva() {
		return indDuplaNueva;
	}
	
	public void setIndDuplaNueva(String indDuplaNueva) {
		this.indDuplaNueva = indDuplaNueva;
	}
	
	public String getIndEnvio() {
		return indEnvio;
	}
	
	public void setIndEnvio(String indEnvio) {
		this.indEnvio = indEnvio;
	}
	
	public String getSalirPantalla() {
		return salirPantalla;
	}
	
	public void setSalirPantalla(String salirPantalla) {
		this.salirPantalla = salirPantalla;
	}

	public Date getFechaNacimientoDate() {
		return fechaNacimientoDate;
	}

	public void setFechaNacimientoDate(Date fechaNacimientoDate) {
		this.fechaNacimientoDate = fechaNacimientoDate;
	}

	public Date getFechaProcesoDate() {
		return fechaProcesoDate;
	}

	public void setFechaProcesoDate(Date fechaProcesoDate) {
		this.fechaProcesoDate = fechaProcesoDate;
	}

}
