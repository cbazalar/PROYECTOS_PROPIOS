package biz.belcorp.ssicc.web.spusicc.sto.form;

import java.io.Serializable;
import java.util.Date;

public class MantenimientoSTOActualizacionDatosForm extends BaseMantenimientoSTOGestionForm
implements Serializable{
	
	private static final long serialVersionUID = 8883658910479485883L;
	
	
	private String codigoPais;
	private String codCompania;
	private String codCliente;
	private String numDocumento;
	private String fechaProceso;
	private Date fechaProcesoDate;
	private String unidadAdministrativa;
	private String codPeriodo;
	private String valApellido1;
	private String valApellido2;
	private String valNombre1;
	private String valNombre2;
	private String tipoDocumento;
	private String numDocuIdentidad;
	private String numRUC;
	private String valDirecCliente;
	private String valBarrCliente;
	private String valCiudCliente;
	private String valDepaCliente;
	private String valTelfCliente;
	private String valCeluCliente;
	private String valTelfTrabajo;
	private String indVentaDirecta;
	private String valMailCliente;
	private String valRegionArribo;
	private String valZonaArribo;
	private String indEstaProceso;
	private String indMotivoRechazo;
	private String tipoViaCliente;
	private String valNombreVia;
	private String numDireCliente;
	private String codDepaCliente;
	private String codProvCliente;
	private String codDistCliente;
	private String codSectCliente;
	private String codTipoDocumento;
	private String numSecuencia;
	private String numLote;
	private String fecProceso;
	private String detalle;
	private String oidPais;
	private String direccionEntrega;
	private String telefonoEntrega;
	private String celularEntrega;
	private String salirPantalla = "N";
	private String indicadorValidacionOK;
	private String indicadorValidacionOKHidden;
	
	private boolean validarCaracteres1;
	private boolean validarCaracteres2;
	private boolean validarCaracteres3;
	
	private String cadenaCaracteresV1;
	private String cadenaCaracteresNV1;
	private String cadenaCaracteresV2;
	private String cadenaCaracteresNV2;
	private String cadenaCaracteresV3;
	private String cadenaCaracteresNV3;
	
	private String nombreActual;
	private String status;
	private String campanaUltimoPedido;
	private String direccion;
	private String unidadGeografica;
	private String region;
	private String zona;
	private String seccion;
	private String territorio;
	private String indicadorDesmarcaCampos;
	
	//sb PER-SiCC-2012-0460 ini
	private boolean mostrarCiudad;
	private boolean mostrarVillaPoblacion;
	private String codigoCiudadDomicilio;
	private String codigoCiudadDomicilioUbigeo;
	private String villaPoblacionDomicilio;
	private String codigoCiudadEntrega;
	private String codigoCiudadEntregaUbigeo;
	private String villaPoblacionEntrega;
	
	private String nacionalidad;
	private String territorioCorresponde;
	private String direccionDomicilioIgualDireccionEntrega;
	private String dirDomManzana;
	private String dirDomEtapa;
	private String dirDomCallePrincipal;
	private String dirDomCalleSecundaria;
	private String dirDomNumero;
	private String dirDomReferencia;
	private String dirEntBarrio;
	private String dirEntManzana;
	private String dirEntEtapa;
	private String dirEntCallePrincipal;
	private String dirEntCalleSecundaria;
	private String dirEntNumero;
	private String dirEntReferencia;
	private String muestraIndicadorCamposAdicionales;	
	//sb PER-SiCC-2012-0460 fin
	
	private boolean indValidaDatosNumericos;
	
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
	 * @return Returns the codDepaCliente.
	 */
	public String getCodDepaCliente() {
		return codDepaCliente;
	}
	/**
	 * @param codDepaCliente The codDepaCliente to set.
	 */
	public void setCodDepaCliente(String codDepaCliente) {
		this.codDepaCliente = codDepaCliente;
	}
	
	/**
	 * @return Returns the codDistCliente.
	 */
	public String getCodDistCliente() {
		return codDistCliente;
	}
	/**
	 * @param codDistCliente The codDistCliente to set.
	 */
	public void setCodDistCliente(String codDistCliente) {
		this.codDistCliente = codDistCliente;
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
	 * @return Returns the codProvCliente.
	 */
	public String getCodProvCliente() {
		return codProvCliente;
	}
	/**
	 * @param codProvCliente The codProvCliente to set.
	 */
	public void setCodProvCliente(String codProvCliente) {
		this.codProvCliente = codProvCliente;
	}
	
	/**
	 * @return Returns the codSectCliente.
	 */
	public String getCodSectCliente() {
		return codSectCliente;
	}
	/**
	 * @param codSectCliente The codSectCliente to set.
	 */
	public void setCodSectCliente(String codSectCliente) {
		this.codSectCliente = codSectCliente;
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
	 * @return Returns the indVentaDirecta.
	 */
	public String getIndVentaDirecta() {
		return indVentaDirecta;
	}
	/**
	 * @param indVentaDirecta The indVentaDirecta to set.
	 */
	public void setIndVentaDirecta(String indVentaDirecta) {
		this.indVentaDirecta = indVentaDirecta;
	}
	/**
	 * @return Returns the numDireCliente.
	 */
	public String getNumDireCliente() {
		return numDireCliente;
	}
	/**
	 * @param numDireCliente The numDireCliente to set.
	 */
	public void setNumDireCliente(String numDireCliente) {
		this.numDireCliente = numDireCliente;
	}

	/**
	 * @return Returns the numDocuIdentidad.
	 */
	public String getNumDocuIdentidad() {
		return numDocuIdentidad;
	}
	/**
	 * @param numDocuIdentidad The numDocuIdentidad to set.
	 */
	public void setNumDocuIdentidad(String numDocuIdentidad) {
		this.numDocuIdentidad = numDocuIdentidad;
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
	 * @return Returns the numRUC.
	 */
	public String getNumRUC() {
		return numRUC;
	}
	/**
	 * @param numRUC The numRUC to set.
	 */
	public void setNumRUC(String numRUC) {
		this.numRUC = numRUC;
	}

	/**
	 * @return Returns the tipoDocumento.
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	/**
	 * @param tipoDocumento The tipoDocumento to set.
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * @return Returns the tipoViaCliente.
	 */
	public String getTipoViaCliente() {
		return tipoViaCliente;
	}
	/**
	 * @param tipoViaCliente The tipoViaCliente to set.
	 */
	public void setTipoViaCliente(String tipoViaCliente) {
		this.tipoViaCliente = tipoViaCliente;
	}
	
	/**
	 * @return Returns the unidadAdministrativa.
	 */
	public String getUnidadAdministrativa() {
		return unidadAdministrativa;
	}
	/**
	 * @param unidadAdministrativa The unidadAdministrativa to set.
	 */
	public void setUnidadAdministrativa(String unidadAdministrativa) {
		this.unidadAdministrativa = unidadAdministrativa;
	}
	/**
	 * @return Returns the valApellido1.
	 */
	public String getValApellido1() {
		return valApellido1;
	}
	/**
	 * @param valApellido1 The valApellido1 to set.
	 */
	public void setValApellido1(String valApellido1) {
		this.valApellido1 = valApellido1;
	}
	
	/**
	 * @return Returns the valApellido2.
	 */
	public String getValApellido2() {
		return valApellido2;
	}
	/**
	 * @param valApellido2 The valApellido2 to set.
	 */
	public void setValApellido2(String valApellido2) {
		this.valApellido2 = valApellido2;
	}
	
	/**
	 * @return Returns the valBarrCliente.
	 */
	public String getValBarrCliente() {
		return valBarrCliente;
	}
	/**
	 * @param valBarrCliente The valBarrCliente to set.
	 */
	public void setValBarrCliente(String valBarrCliente) {
		this.valBarrCliente = valBarrCliente;
	}

	/**
	 * @return Returns the valCeluCliente.
	 */
	public String getValCeluCliente() {
		return valCeluCliente;
	}
	/**
	 * @param valCeluCliente The valCeluCliente to set.
	 */
	public void setValCeluCliente(String valCeluCliente) {
		this.valCeluCliente = valCeluCliente;
	}
	
	/**
	 * @return Returns the valCiudCliente.
	 */
	public String getValCiudCliente() {
		return valCiudCliente;
	}
	/**
	 * @param valCiudCliente The valCiudCliente to set.
	 */
	public void setValCiudCliente(String valCiudCliente) {
		this.valCiudCliente = valCiudCliente;
	}
	
	/**
	 * @return Returns the valDepaCliente.
	 */
	public String getValDepaCliente() {
		return valDepaCliente;
	}
	/**
	 * @param valDepaCliente The valDepaCliente to set.
	 */
	public void setValDepaCliente(String valDepaCliente) {
		this.valDepaCliente = valDepaCliente;
	}
	
	/**
	 * @return Returns the valDirecCliente.
	 */
	public String getValDirecCliente() {
		return valDirecCliente;
	}
	/**
	 * @param valDirecCliente The valDirecCliente to set.
	 */
	public void setValDirecCliente(String valDirecCliente) {
		this.valDirecCliente = valDirecCliente;
	}
	
	/**
	 * @return Returns the valMailCliente.
	 */
	public String getValMailCliente() {
		return valMailCliente;
	}
	/**
	 * @param valMailCliente The valMailCliente to set.
	 */
	public void setValMailCliente(String valMailCliente) {
		this.valMailCliente = valMailCliente;
	}
	/**
	 * @return Returns the valNombre1.
	 */
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
	 * @return Returns the valNombreVia.
	 */
	public String getValNombreVia() {
		return valNombreVia;
	}
	/**
	 * @param valNombreVia The valNombreVia to set.
	 */
	public void setValNombreVia(String valNombreVia) {
		this.valNombreVia = valNombreVia;
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
	 * @return Returns the valTelfTrabajo.
	 */
	public String getValTelfTrabajo() {
		return valTelfTrabajo;
	}
	/**
	 * @param valTelfTrabajo The valTelfTrabajo to set.
	 */
	public void setValTelfTrabajo(String valTelfTrabajo) {
		this.valTelfTrabajo = valTelfTrabajo;
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
	public String getDireccionEntrega() {
		return direccionEntrega;
	}
	public void setDireccionEntrega(String direccionEntrega) {
		this.direccionEntrega = direccionEntrega;
	}
	public String getTelefonoEntrega() {
		return telefonoEntrega;
	}
	public void setTelefonoEntrega(String telefonoEntrega) {
		this.telefonoEntrega = telefonoEntrega;
	}
	public String getCelularEntrega() {
		return celularEntrega;
	}
	public void setCelularEntrega(String celularEntrega) {
		this.celularEntrega = celularEntrega;
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
	 * @return the indicadorValidacionOK
	 */
	public String getIndicadorValidacionOK() {
		return indicadorValidacionOK;
	}
	/**
	 * @param indicadorValidacionOK the indicadorValidacionOK to set
	 */
	public void setIndicadorValidacionOK(String indicadorValidacionOK) {
		this.indicadorValidacionOK = indicadorValidacionOK;
	}
	/**
	 * @return the indicadorValidacionOKHidden
	 */
	public String getIndicadorValidacionOKHidden() {
		return indicadorValidacionOKHidden;
	}
	/**
	 * @param indicadorValidacionOKHidden the indicadorValidacionOKHidden to set
	 */
	public void setIndicadorValidacionOKHidden(String indicadorValidacionOKHidden) {
		this.indicadorValidacionOKHidden = indicadorValidacionOKHidden;
	}
	/**
	 * @return the validarCaracteres1
	 */
	public boolean isValidarCaracteres1() {
		return validarCaracteres1;
	}
	/**
	 * @param validarCaracteres1 the validarCaracteres1 to set
	 */
	public void setValidarCaracteres1(boolean validarCaracteres1) {
		this.validarCaracteres1 = validarCaracteres1;
	}
	/**
	 * @return the validarCaracteres2
	 */
	public boolean isValidarCaracteres2() {
		return validarCaracteres2;
	}
	/**
	 * @param validarCaracteres2 the validarCaracteres2 to set
	 */
	public void setValidarCaracteres2(boolean validarCaracteres2) {
		this.validarCaracteres2 = validarCaracteres2;
	}
	/**
	 * @return the validarCaracteres3
	 */
	public boolean isValidarCaracteres3() {
		return validarCaracteres3;
	}
	/**
	 * @param validarCaracteres3 the validarCaracteres3 to set
	 */
	public void setValidarCaracteres3(boolean validarCaracteres3) {
		this.validarCaracteres3 = validarCaracteres3;
	}
	/**
	 * @return the cadenaCaracteresV1
	 */
	public String getCadenaCaracteresV1() {
		return cadenaCaracteresV1;
	}
	/**
	 * @param cadenaCaracteresV1 the cadenaCaracteresV1 to set
	 */
	public void setCadenaCaracteresV1(String cadenaCaracteresV1) {
		this.cadenaCaracteresV1 = cadenaCaracteresV1;
	}
	/**
	 * @return the cadenaCaracteresNV1
	 */
	public String getCadenaCaracteresNV1() {
		return cadenaCaracteresNV1;
	}
	/**
	 * @param cadenaCaracteresNV1 the cadenaCaracteresNV1 to set
	 */
	public void setCadenaCaracteresNV1(String cadenaCaracteresNV1) {
		this.cadenaCaracteresNV1 = cadenaCaracteresNV1;
	}
	/**
	 * @return the cadenaCaracteresV2
	 */
	public String getCadenaCaracteresV2() {
		return cadenaCaracteresV2;
	}
	/**
	 * @param cadenaCaracteresV2 the cadenaCaracteresV2 to set
	 */
	public void setCadenaCaracteresV2(String cadenaCaracteresV2) {
		this.cadenaCaracteresV2 = cadenaCaracteresV2;
	}
	/**
	 * @return the cadenaCaracteresNV2
	 */
	public String getCadenaCaracteresNV2() {
		return cadenaCaracteresNV2;
	}
	/**
	 * @param cadenaCaracteresNV2 the cadenaCaracteresNV2 to set
	 */
	public void setCadenaCaracteresNV2(String cadenaCaracteresNV2) {
		this.cadenaCaracteresNV2 = cadenaCaracteresNV2;
	}
	/**
	 * @return the cadenaCaracteresV3
	 */
	public String getCadenaCaracteresV3() {
		return cadenaCaracteresV3;
	}
	/**
	 * @param cadenaCaracteresV3 the cadenaCaracteresV3 to set
	 */
	public void setCadenaCaracteresV3(String cadenaCaracteresV3) {
		this.cadenaCaracteresV3 = cadenaCaracteresV3;
	}
	/**
	 * @return the cadenaCaracteresNV3
	 */
	public String getCadenaCaracteresNV3() {
		return cadenaCaracteresNV3;
	}
	/**
	 * @param cadenaCaracteresNV3 the cadenaCaracteresNV3 to set
	 */
	public void setCadenaCaracteresNV3(String cadenaCaracteresNV3) {
		this.cadenaCaracteresNV3 = cadenaCaracteresNV3;
	}
	/**
	 * @return the nombreActual
	 */
	public String getNombreActual() {
		return nombreActual;
	}
	/**
	 * @param nombreActual the nombreActual to set
	 */
	public void setNombreActual(String nombreActual) {
		this.nombreActual = nombreActual;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the campanaUltimoPedido
	 */
	public String getCampanaUltimoPedido() {
		return campanaUltimoPedido;
	}
	/**
	 * @param campanaUltimoPedido the campanaUltimoPedido to set
	 */
	public void setCampanaUltimoPedido(String campanaUltimoPedido) {
		this.campanaUltimoPedido = campanaUltimoPedido;
	}
	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * @return the unidadGeografica
	 */
	public String getUnidadGeografica() {
		return unidadGeografica;
	}
	/**
	 * @param unidadGeografica the unidadGeografica to set
	 */
	public void setUnidadGeografica(String unidadGeografica) {
		this.unidadGeografica = unidadGeografica;
	}
	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}
	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}
	/**
	 * @return the zona
	 */
	public String getZona() {
		return zona;
	}
	/**
	 * @param zona the zona to set
	 */
	public void setZona(String zona) {
		this.zona = zona;
	}
	/**
	 * @return the seccion
	 */
	public String getSeccion() {
		return seccion;
	}
	/**
	 * @param seccion the seccion to set
	 */
	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}
	/**
	 * @return the territorio
	 */
	public String getTerritorio() {
		return territorio;
	}
	/**
	 * @param territorio the territorio to set
	 */
	public void setTerritorio(String territorio) {
		this.territorio = territorio;
	}
	/**
	 * @return the indicadorDesmarcaCampos
	 */
	public String getIndicadorDesmarcaCampos() {
		return indicadorDesmarcaCampos;
	}
	/**
	 * @param indicadorDesmarcaCampos the indicadorDesmarcaCampos to set
	 */
	public void setIndicadorDesmarcaCampos(String indicadorDesmarcaCampos) {
		this.indicadorDesmarcaCampos = indicadorDesmarcaCampos;
	}
	/**
	 * @return the mostrarCiudad
	 */
	public boolean isMostrarCiudad() {
		return mostrarCiudad;
	}
	/**
	 * @param mostrarCiudad the mostrarCiudad to set
	 */
	public void setMostrarCiudad(boolean mostrarCiudad) {
		this.mostrarCiudad = mostrarCiudad;
	}
	/**
	 * @return the mostrarVillaPoblacion
	 */
	public boolean isMostrarVillaPoblacion() {
		return mostrarVillaPoblacion;
	}
	/**
	 * @param mostrarVillaPoblacion the mostrarVillaPoblacion to set
	 */
	public void setMostrarVillaPoblacion(boolean mostrarVillaPoblacion) {
		this.mostrarVillaPoblacion = mostrarVillaPoblacion;
	}
	/**
	 * @return the codigoCiudadDomicilio
	 */
	public String getCodigoCiudadDomicilio() {
		return codigoCiudadDomicilio;
	}
	/**
	 * @param codigoCiudadDomicilio the codigoCiudadDomicilio to set
	 */
	public void setCodigoCiudadDomicilio(String codigoCiudadDomicilio) {
		this.codigoCiudadDomicilio = codigoCiudadDomicilio;
	}
	/**
	 * @return the villaPoblacionDomicilio
	 */
	public String getVillaPoblacionDomicilio() {
		return villaPoblacionDomicilio;
	}
	/**
	 * @param villaPoblacionDomicilio the villaPoblacionDomicilio to set
	 */
	public void setVillaPoblacionDomicilio(String villaPoblacionDomicilio) {
		this.villaPoblacionDomicilio = villaPoblacionDomicilio;
	}
	/**
	 * @return the codigoCiudadEntrega
	 */
	public String getCodigoCiudadEntrega() {
		return codigoCiudadEntrega;
	}
	/**
	 * @param codigoCiudadEntrega the codigoCiudadEntrega to set
	 */
	public void setCodigoCiudadEntrega(String codigoCiudadEntrega) {
		this.codigoCiudadEntrega = codigoCiudadEntrega;
	}
	/**
	 * @return the villaPoblacionEntrega
	 */
	public String getVillaPoblacionEntrega() {
		return villaPoblacionEntrega;
	}
	
	public void setVillaPoblacionEntrega(String villaPoblacionEntrega) {
		this.villaPoblacionEntrega = villaPoblacionEntrega;
	}
	
	public String getCodigoCiudadDomicilioUbigeo() {
		return codigoCiudadDomicilioUbigeo;
	}
	
	public void setCodigoCiudadDomicilioUbigeo(String codigoCiudadDomicilioUbigeo) {
		this.codigoCiudadDomicilioUbigeo = codigoCiudadDomicilioUbigeo;
	}
	
	public String getCodigoCiudadEntregaUbigeo() {
		return codigoCiudadEntregaUbigeo;
	}
	
	public void setCodigoCiudadEntregaUbigeo(String codigoCiudadEntregaUbigeo) {
		this.codigoCiudadEntregaUbigeo = codigoCiudadEntregaUbigeo;
	}
	public Date getFechaProcesoDate() {
		return fechaProcesoDate;
	}
	public void setFechaProcesoDate(Date fechaProcesoDate) {
		this.fechaProcesoDate = fechaProcesoDate;
	}
	/**
	 * @return the nacionalidad
	 */
	public String getNacionalidad() {
		return nacionalidad;
	}
	/**
	 * @param nacionalidad the nacionalidad to set
	 */
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	/**
	 * @return the territorioCorresponde
	 */
	public String getTerritorioCorresponde() {
		return territorioCorresponde;
	}
	/**
	 * @param territorioCorresponde the territorioCorresponde to set
	 */
	public void setTerritorioCorresponde(String territorioCorresponde) {
		this.territorioCorresponde = territorioCorresponde;
	}
	/**
	 * @return the direccionDomicilioIgualDireccionEntrega
	 */
	public String getDireccionDomicilioIgualDireccionEntrega() {
		return direccionDomicilioIgualDireccionEntrega;
	}
	/**
	 * @param direccionDomicilioIgualDireccionEntrega the direccionDomicilioIgualDireccionEntrega to set
	 */
	public void setDireccionDomicilioIgualDireccionEntrega(
			String direccionDomicilioIgualDireccionEntrega) {
		this.direccionDomicilioIgualDireccionEntrega = direccionDomicilioIgualDireccionEntrega;
	}
	/**
	 * @return the dirDomManzana
	 */
	public String getDirDomManzana() {
		return dirDomManzana;
	}
	/**
	 * @param dirDomManzana the dirDomManzana to set
	 */
	public void setDirDomManzana(String dirDomManzana) {
		this.dirDomManzana = dirDomManzana;
	}
	/**
	 * @return the dirDomEtapa
	 */
	public String getDirDomEtapa() {
		return dirDomEtapa;
	}
	/**
	 * @param dirDomEtapa the dirDomEtapa to set
	 */
	public void setDirDomEtapa(String dirDomEtapa) {
		this.dirDomEtapa = dirDomEtapa;
	}
	/**
	 * @return the dirDomCallePrincipal
	 */
	public String getDirDomCallePrincipal() {
		return dirDomCallePrincipal;
	}
	/**
	 * @param dirDomCallePrincipal the dirDomCallePrincipal to set
	 */
	public void setDirDomCallePrincipal(String dirDomCallePrincipal) {
		this.dirDomCallePrincipal = dirDomCallePrincipal;
	}
	/**
	 * @return the dirDomCalleSecundaria
	 */
	public String getDirDomCalleSecundaria() {
		return dirDomCalleSecundaria;
	}
	/**
	 * @param dirDomCalleSecundaria the dirDomCalleSecundaria to set
	 */
	public void setDirDomCalleSecundaria(String dirDomCalleSecundaria) {
		this.dirDomCalleSecundaria = dirDomCalleSecundaria;
	}
	/**
	 * @return the dirDomNumero
	 */
	public String getDirDomNumero() {
		return dirDomNumero;
	}
	/**
	 * @param dirDomNumero the dirDomNumero to set
	 */
	public void setDirDomNumero(String dirDomNumero) {
		this.dirDomNumero = dirDomNumero;
	}
	/**
	 * @return the dirDomReferencia
	 */
	public String getDirDomReferencia() {
		return dirDomReferencia;
	}
	/**
	 * @param dirDomReferencia the dirDomReferencia to set
	 */
	public void setDirDomReferencia(String dirDomReferencia) {
		this.dirDomReferencia = dirDomReferencia;
	}
	/**
	 * @return the dirEntBarrio
	 */
	public String getDirEntBarrio() {
		return dirEntBarrio;
	}
	/**
	 * @param dirEntBarrio the dirEntBarrio to set
	 */
	public void setDirEntBarrio(String dirEntBarrio) {
		this.dirEntBarrio = dirEntBarrio;
	}
	/**
	 * @return the dirEntManzana
	 */
	public String getDirEntManzana() {
		return dirEntManzana;
	}
	/**
	 * @param dirEntManzana the dirEntManzana to set
	 */
	public void setDirEntManzana(String dirEntManzana) {
		this.dirEntManzana = dirEntManzana;
	}
	/**
	 * @return the dirEntEtapa
	 */
	public String getDirEntEtapa() {
		return dirEntEtapa;
	}
	/**
	 * @param dirEntEtapa the dirEntEtapa to set
	 */
	public void setDirEntEtapa(String dirEntEtapa) {
		this.dirEntEtapa = dirEntEtapa;
	}
	/**
	 * @return the dirEntCallePrincipal
	 */
	public String getDirEntCallePrincipal() {
		return dirEntCallePrincipal;
	}
	/**
	 * @param dirEntCallePrincipal the dirEntCallePrincipal to set
	 */
	public void setDirEntCallePrincipal(String dirEntCallePrincipal) {
		this.dirEntCallePrincipal = dirEntCallePrincipal;
	}
	/**
	 * @return the dirEntCalleSecundaria
	 */
	public String getDirEntCalleSecundaria() {
		return dirEntCalleSecundaria;
	}
	/**
	 * @param dirEntCalleSecundaria the dirEntCalleSecundaria to set
	 */
	public void setDirEntCalleSecundaria(String dirEntCalleSecundaria) {
		this.dirEntCalleSecundaria = dirEntCalleSecundaria;
	}
	/**
	 * @return the dirEntNumero
	 */
	public String getDirEntNumero() {
		return dirEntNumero;
	}
	/**
	 * @param dirEntNumero the dirEntNumero to set
	 */
	public void setDirEntNumero(String dirEntNumero) {
		this.dirEntNumero = dirEntNumero;
	}
	/**
	 * @return the dirEntReferencia
	 */
	public String getDirEntReferencia() {
		return dirEntReferencia;
	}
	/**
	 * @param dirEntReferencia the dirEntReferencia to set
	 */
	public void setDirEntReferencia(String dirEntReferencia) {
		this.dirEntReferencia = dirEntReferencia;
	}
	/**
	 * @return the muestraIndicadorCamposAdicionales
	 */
	public String getMuestraIndicadorCamposAdicionales() {
		return muestraIndicadorCamposAdicionales;
	}
	/**
	 * @param muestraIndicadorCamposAdicionales the muestraIndicadorCamposAdicionales to set
	 */
	public void setMuestraIndicadorCamposAdicionales(String muestraIndicadorCamposAdicionales) {
		this.muestraIndicadorCamposAdicionales = muestraIndicadorCamposAdicionales;
	}
	/**
	 * @return the indValidaDatosNumericos
	 */
	public boolean isIndValidaDatosNumericos() {
		return indValidaDatosNumericos;
	}
	/**
	 * @param indValidaDatosNumericos the indValidaDatosNumericos to set
	 */
	public void setIndValidaDatosNumericos(boolean indValidaDatosNumericos) {
		this.indValidaDatosNumericos = indValidaDatosNumericos;
	}
	
	
}
