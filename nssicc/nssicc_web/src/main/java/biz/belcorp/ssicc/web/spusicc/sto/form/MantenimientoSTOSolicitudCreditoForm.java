package biz.belcorp.ssicc.web.spusicc.sto.form;

import java.io.Serializable;
import java.util.Date;

public class MantenimientoSTOSolicitudCreditoForm extends BaseMantenimientoSTOGestionForm
	implements Serializable{

	private static final long serialVersionUID = -1442489732143013199L;
	
	private String codigoPais;
	private String codCompania;
	private String codCliente;
	private String numDocumento;
	private String fechaProceso;
	private Date fechaProcesoDate;
	private String  unidadAdministrativa;
	private String tipoIngreso;
	private String codPeriodo;
	private String codClienteRetenido;
	private String codPremio;
	private String valApellido1;
	private String valApellido2;
	private String valNombre1;
	private String valNombre2;
	private String fechaNacimiento;
	private Date fechaNacimientoDate;
	private String tipoDocumento;
	private String numDocuIdentidad;
	private String numRUC;
	private String indEstaCivil;
	private String indNivelEducativo;
	private String valDirecCliente;
	private String valBarrCliente;
	private String valCiudCliente;
	private String valDepaCliente;
	private String valTelfCliente;
	private String valCeluCliente;
	private String valTelfTrabajo;
	private String indVentaDirecta;
	private String valMailCliente;
	private String tipoDocuFiador;
	private String codDocuFiador;
	private String valApellido1Fiador;
	private String valApellido2Fiador;
	private String valNombre1Fiador;
	private String valNombre2Fiador;
	private String valDireFiador;
	private String valBarrFiador;
	private String valCiudFiador;
	private String valDepaFiador;
	private String valTelfFiador;
	private String valCeluFiador;
	private String valTelfTrabaFiador;
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
	private String tipoViaFiador;
	private String valNomViaFiador;
	private String numDireFiador;
	private String codDepaFiador;
	private String codProvFiador;
	private String codDistFiador;
	private String codSectFiador;
	private String codTipoDocumento;
	private String numSecuencia;
	private String numLote;
	private String fecProceso;
	private String fecNacimiento;
	private Date fecProcesoDate;
	private Date fecNacimientoDate;
	private String detalle;
	private String oidPais;
		
	private String indicadorTelefonoOK;
	private String indicadorSituacionCrediticia;
	private String indicadorSinSaldoAmbasMarcas;	
	
	private String indTelefonoOK;
	private String indSituacionCrediticia;
	private String indSinSaldoAmbasMarcas;	
	
	private String delegacionCliente       ;
	private String codigoPostalCliente     ;
	private String direccionEntrega        ;
	private String barrioEntrega           ;
	private String delegacionEntrega       ;
	private String departamentoEntrega     ;
	private String telefonoEntrega         ;
	private String celularEntrega          ;
	private String apellido1RefFamiliar    ;
	private String nombre1RefFamiliar      ;
	private String direccionRefFamiliar    ;
	private String barrioRefFamiliar       ;
	private String delegacionRefFamiliar   ;
	private String ciudadRefFamiliar       ;
	private String departamentoRefFamiliar ;
	private String telefonoRefFamiliar     ;
	private String celularRefFamiliar      ;
	private String tipoVinculoRefFamiliar  ;
	private String apellido1RefNoFamiliar  ;
	private String nombre1RefNoFamiliar    ;
	private String telefonoRefNoFamiliar   ;
	private String celularRefNoFamiliar    ;
	private String tipoVinculoRefNoFamiliar;
	private String nombreEmpresaFiador     ;
	private String direccionEmpresaFiador  ;
	private String cargoFiador             ;
	private String tipoVinculoFiador       ;
	private String requiereFactura         ;
	private String direccionContribuyente  ;
	private String barrioContribuyente     ;
	private String delegacionContribuyente ;
	private String ciudadContribuyente     ;
	private String departamentoContribuyente;
	private String codigoPostalContribuyente;
	private String numeroContribuyente      ;
	private String direccionRefNoFamiliar   ;
	private String salirPantalla;
	private String indicadorGuardar;
	
	private boolean validarCaracteres1;
	private boolean validarCaracteres2;
	private boolean validarCaracteres3;
	
	private String cadenaCaracteresV1;
	private String cadenaCaracteresNV1;
	private String cadenaCaracteresV2;
	private String cadenaCaracteresNV2;
	private String cadenaCaracteresV3;
	private String cadenaCaracteresNV3;
	
	private boolean editableCampana;
	
	private String codSexoCliente;
	
	private String codigoConsultora;
	private String nombreConsultora;
	private String statusConsultora;
	private String campaniaUltConsultora;
	private String direccionConsultora;
	private String unidadGeoConsultora;
	private String bloqueoConsultora;
	private String regionConsultora;
	private String zonaConsultora;
	private String seccionConsultora;
	private String territorioConsultora;
	private String activaConsultora;
	
	private String indMostrarDatos;
	
	//sb PER-SiCC-2012-0460 ini
	private boolean mostrarCiudad;
	private boolean mostrarVillaPoblacion;
	private String codigoCiudadDomicilio;
	private String codigoCiudadDomicilioUbigeo;
	private String villaPoblacionDomicilio;
	private String codigoCiudadEntrega;
	private String codigoCiudadEntregaUbigeo;
	private String villaPoblacionEntrega;		
	//sb PER-SiCC-2012-0460 fin
	//ini sb boletin comercial
	private String montoInfoComercial;
	private String estadoComercial;
	private String limInfVerde;
	private String limSupVerde;
	private String limInfAmarillo;
	private String limSupAmarillo;
	private String limInfRojo;
	private String limSupRojo; 
	
	private String indActivaWS;
	//fin boletin comercial
	
	private String nombresInfocorp;	
	private String explicacionInfocorp;
	
	//PER-SiCC-2013-0778
	private String oidMotiGes;
	private String valObseGestion;
	private boolean mostrarMotivoObservacion;	
	//Metas
	private String muestraMetas;
	private String montoMeta;
	private String tipoMeta;
	private String descripcionMeta;
	//Indicador Buzon
	private String muestraIndicadorBuzon;
	private String indicadorBuzon;
	
	//Indicador informacion OK
	private String indInformacionOk;
	//Indicador Situacion Crediticia
	private String indSitCrediticia;
	//Indicador Sin saldo ambas marcas
	private String indSinSalAmbas;
	
	private String indViewMotiGest;
	
	private String valorId;
	
	private boolean indicadorLiderRecomendante = false;
	private String codigoLiderRecomendante;
	
	private boolean indicadorMostrarFacturacionElectronica = false;
	private String indicadorFacturacionElectronica;
	private String indicadorFacturacionElectronicaTempo;
	
	private String nacionalidad;
	private String tipoPersona;
	private String origenIngreso;
	private String territorioCorresponde;
	private String direccionDomicilioIgualDireccionEntrega;
	private String dirDomManzana;
	private String dirDomEtapa;
	private String dirDomCallePrincipal;
	private String dirDomCalleSecundaria;
	private String dirDomNumero;
	private String dirDomReferencia;
	private String dirEntManzana;
	private String dirEntEtapa;
	private String dirEntNumero;
	private String dirEntCallePrincipal;
	private String dirEntCalleSecundaria;
	private String dirEntReferencia;
	private String muestraIndicadorCamposAdicionales;
	
	private boolean indValidaDatosNumericos;
	
	public String getCodCliente() {
		return codCliente;
	}
	
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	
	public String getCodClienteRetenido() {
		return codClienteRetenido;
	}
	
	public void setCodClienteRetenido(String codClienteRetenido) {
		this.codClienteRetenido = codClienteRetenido;
	}
	
	public String getCodCompania() {
		return codCompania;
	}
	
	public void setCodCompania(String codCompania) {
		this.codCompania = codCompania;
	}
	
	public String getCodDepaCliente() {
		return codDepaCliente;
	}
	
	public void setCodDepaCliente(String codDepaCliente) {
		this.codDepaCliente = codDepaCliente;
	}
	
	public String getCodDepaFiador() {
		return codDepaFiador;
	}
	
	public void setCodDepaFiador(String codDepaFiador) {
		this.codDepaFiador = codDepaFiador;
	}
	
	public String getCodDistCliente() {
		return codDistCliente;
	}
	
	public void setCodDistCliente(String codDistCliente) {
		this.codDistCliente = codDistCliente;
	}
	
	public String getCodDistFiador() {
		return codDistFiador;
	}
	
	public void setCodDistFiador(String codDistFiador) {
		this.codDistFiador = codDistFiador;
	}
	
	public String getCodDocuFiador() {
		return codDocuFiador;
	}
	
	public void setCodDocuFiador(String codDocuFiador) {
		this.codDocuFiador = codDocuFiador;
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
	
	public String getCodPremio() {
		return codPremio;
	}
	
	public void setCodPremio(String codPremio) {
		this.codPremio = codPremio;
	}
	
	public String getCodProvCliente() {
		return codProvCliente;
	}
	
	public void setCodProvCliente(String codProvCliente) {
		this.codProvCliente = codProvCliente;
	}
	
	public String getCodProvFiador() {
		return codProvFiador;
	}
	
	public void setCodProvFiador(String codProvFiador) {
		this.codProvFiador = codProvFiador;
	}
	
	public String getCodSectCliente() {
		return codSectCliente;
	}
	
	public void setCodSectCliente(String codSectCliente) {
		this.codSectCliente = codSectCliente;
	}
	
	public String getCodSectFiador() {
		return codSectFiador;
	}
	
	public void setCodSectFiador(String codSectFiador) {
		this.codSectFiador = codSectFiador;
	}
	
	public String getCodTipoDocumento() {
		return codTipoDocumento;
	}

	public void setCodTipoDocumento(String codTipoDocumento) {
		this.codTipoDocumento = codTipoDocumento;
	}
	
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public String getFechaProceso() {
		return fechaProceso;
	}

	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}
	
	public String getIndEstaCivil() {
		return indEstaCivil;
	}
	
	public void setIndEstaCivil(String indEstaCivil) {
		this.indEstaCivil = indEstaCivil;
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
	
	public String getIndNivelEducativo() {
		return indNivelEducativo;
	}
	
	public void setIndNivelEducativo(String indNivelEducativo) {
		this.indNivelEducativo = indNivelEducativo;
	}
	
	public String getIndVentaDirecta() {
		return indVentaDirecta;
	}
	
	public void setIndVentaDirecta(String indVentaDirecta) {
		this.indVentaDirecta = indVentaDirecta;
	}
	
	public String getNumDireCliente() {
		return numDireCliente;
	}
	
	public void setNumDireCliente(String numDireCliente) {
		this.numDireCliente = numDireCliente;
	}
	
	public String getNumDireFiador() {
		return numDireFiador;
	}
	
	public void setNumDireFiador(String numDireFiador) {
		this.numDireFiador = numDireFiador;
	}
	
	public String getNumDocuIdentidad() {
		return numDocuIdentidad;
	}
	
	public void setNumDocuIdentidad(String numDocuIdentidad) {
		this.numDocuIdentidad = numDocuIdentidad;
	}
	
	public String getNumDocumento() {
		return numDocumento;
	}
	
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}
	
	public String getNumRUC() {
		return numRUC;
	}
	
	public void setNumRUC(String numRUC) {
		this.numRUC = numRUC;
	}
	
	public String getTipoDocuFiador() {
		return tipoDocuFiador;
	}
	
	public void setTipoDocuFiador(String tipoDocuFiador) {
		this.tipoDocuFiador = tipoDocuFiador;
	}
	
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	/**
	 * @return Returns the tipoIngreso.
	 */
	public String getTipoIngreso() {
		return tipoIngreso;
	}
	/**
	 * @param tipoIngreso The tipoIngreso to set.
	 */
	public void setTipoIngreso(String tipoIngreso) {
		this.tipoIngreso = tipoIngreso;
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
	 * @return Returns the tipoViaFiador.
	 */
	public String getTipoViaFiador() {
		return tipoViaFiador;
	}
	/**
	 * @param tipoViaFiador The tipoViaFiador to set.
	 */
	public void setTipoViaFiador(String tipoViaFiador) {
		this.tipoViaFiador = tipoViaFiador;
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
	 * @return Returns the valApellido1Fiador.
	 */
	public String getValApellido1Fiador() {
		return valApellido1Fiador;
	}
	/**
	 * @param valApellido1Fiador The valApellido1Fiador to set.
	 */
	public void setValApellido1Fiador(String valApellido1Fiador) {
		this.valApellido1Fiador = valApellido1Fiador;
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
	 * @return Returns the valApellido2Fiador.
	 */
	public String getValApellido2Fiador() {
		return valApellido2Fiador;
	}
	/**
	 * @param valApellido2Fiador The valApellido2Fiador to set.
	 */
	public void setValApellido2Fiador(String valApellido2Fiador) {
		this.valApellido2Fiador = valApellido2Fiador;
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
	 * @return Returns the valBarrFiador.
	 */
	public String getValBarrFiador() {
		return valBarrFiador;
	}
	/**
	 * @param valBarrFiador The valBarrFiador to set.
	 */
	public void setValBarrFiador(String valBarrFiador) {
		this.valBarrFiador = valBarrFiador;
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
	 * @return Returns the valCeluFiador.
	 */
	public String getValCeluFiador() {
		return valCeluFiador;
	}
	/**
	 * @param valCeluFiador The valCeluFiador to set.
	 */
	public void setValCeluFiador(String valCeluFiador) {
		this.valCeluFiador = valCeluFiador;
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
	 * @return Returns the valCiudFiador.
	 */
	public String getValCiudFiador() {
		return valCiudFiador;
	}
	/**
	 * @param valCiudFiador The valCiudFiador to set.
	 */
	public void setValCiudFiador(String valCiudFiador) {
		this.valCiudFiador = valCiudFiador;
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
	 * @return Returns the valDepaFiador.
	 */
	public String getValDepaFiador() {
		return valDepaFiador;
	}
	/**
	 * @param valDepaFiador The valDepaFiador to set.
	 */
	public void setValDepaFiador(String valDepaFiador) {
		this.valDepaFiador = valDepaFiador;
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
	 * @return Returns the valDireFiador.
	 */
	public String getValDireFiador() {
		return valDireFiador;
	}
	/**
	 * @param valDireFiador The valDireFiador to set.
	 */
	public void setValDireFiador(String valDireFiador) {
		this.valDireFiador = valDireFiador;
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
	 * @return Returns the valNombre1Fiador.
	 */
	public String getValNombre1Fiador() {
		return valNombre1Fiador;
	}
	/**
	 * @param valNombre1Fiador The valNombre1Fiador to set.
	 */
	public void setValNombre1Fiador(String valNombre1Fiador) {
		this.valNombre1Fiador = valNombre1Fiador;
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
	 * @return Returns the valNombre2Fiador.
	 */
	public String getValNombre2Fiador() {
		return valNombre2Fiador;
	}
	/**
	 * @param valNombre2Fiador The valNombre2Fiador to set.
	 */
	public void setValNombre2Fiador(String valNombre2Fiador) {
		this.valNombre2Fiador = valNombre2Fiador;
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
	 * @return Returns the valNomViaFiador.
	 */
	public String getValNomViaFiador() {
		return valNomViaFiador;
	}
	/**
	 * @param valNomViaFiador The valNomViaFiador to set.
	 */
	public void setValNomViaFiador(String valNomViaFiador) {
		this.valNomViaFiador = valNomViaFiador;
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
	 * @return Returns the valTelfFiador.
	 */
	public String getValTelfFiador() {
		return valTelfFiador;
	}
	/**
	 * @param valTelfFiador The valTelfFiador to set.
	 */
	public void setValTelfFiador(String valTelfFiador) {
		this.valTelfFiador = valTelfFiador;
	}
	/**
	 * @return Returns the valTelfTrabaFiador.
	 */
	public String getValTelfTrabaFiador() {
		return valTelfTrabaFiador;
	}
	/**
	 * @param valTelfTrabaFiador The valTelfTrabaFiador to set.
	 */
	public void setValTelfTrabaFiador(String valTelfTrabaFiador) {
		this.valTelfTrabaFiador = valTelfTrabaFiador;
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
	 * @return Returns the fecNacimiento.
	 */
	public String getFecNacimiento() {
		return fecNacimiento;
	}
	/**
	 * @param fecNacimiento The fecNacimiento to set.
	 */
	public void setFecNacimiento(String fecNacimiento) {
		this.fecNacimiento = fecNacimiento;
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
	 * @return the indicadorTelefonoOK
	 */
	public String getIndicadorTelefonoOK() {
		return indicadorTelefonoOK;
	}
	/**
	 * @param indicadorTelefonoOK the indicadorTelefonoOK to set
	 */
	public void setIndicadorTelefonoOK(String indicadorTelefonoOK) {
		this.indicadorTelefonoOK = indicadorTelefonoOK;
	}
	/**
	 * @return the indicadorSituacionCrediticia
	 */
	public String getIndicadorSituacionCrediticia() {
		return indicadorSituacionCrediticia;
	}
	/**
	 * @param indicadorSituacionCrediticia the indicadorSituacionCrediticia to set
	 */
	public void setIndicadorSituacionCrediticia(String indicadorSituacionCrediticia) {
		this.indicadorSituacionCrediticia = indicadorSituacionCrediticia;
	}
	/**
	 * @return the indicadorSinSaldoAmbasMarcas
	 */
	public String getIndicadorSinSaldoAmbasMarcas() {
		return indicadorSinSaldoAmbasMarcas;
	}
	/**
	 * @param indicadorSinSaldoAmbasMarcas the indicadorSinSaldoAmbasMarcas to set
	 */
	public void setIndicadorSinSaldoAmbasMarcas(String indicadorSinSaldoAmbasMarcas) {
		this.indicadorSinSaldoAmbasMarcas = indicadorSinSaldoAmbasMarcas;
	}
	/**
	 * @return the indTelefonoOK
	 */
	public String getIndTelefonoOK() {
		return indTelefonoOK;
	}
	/**
	 * @param indTelefonoOK the indTelefonoOK to set
	 */
	public void setIndTelefonoOK(String indTelefonoOK) {
		this.indTelefonoOK = indTelefonoOK;
	}
	/**
	 * @return the indSituacionCrediticia
	 */
	public String getIndSituacionCrediticia() {
		return indSituacionCrediticia;
	}
	/**
	 * @param indSituacionCrediticia the indSituacionCrediticia to set
	 */
	public void setIndSituacionCrediticia(String indSituacionCrediticia) {
		this.indSituacionCrediticia = indSituacionCrediticia;
	}
	/**
	 * @return the indSinSaldoAmbasMarcas
	 */
	public String getIndSinSaldoAmbasMarcas() {
		return indSinSaldoAmbasMarcas;
	}
	/**
	 * @param indSinSaldoAmbasMarcas the indSinSaldoAmbasMarcas to set
	 */
	public void setIndSinSaldoAmbasMarcas(String indSinSaldoAmbasMarcas) {
		this.indSinSaldoAmbasMarcas = indSinSaldoAmbasMarcas;
	}
	/**
	 * @return the delegacionCliente
	 */
	public String getDelegacionCliente() {
		return delegacionCliente;
	}
	/**
	 * @param delegacionCliente the delegacionCliente to set
	 */
	public void setDelegacionCliente(String delegacionCliente) {
		this.delegacionCliente = delegacionCliente;
	}
	/**
	 * @return the codigoPostalCliente
	 */
	public String getCodigoPostalCliente() {
		return codigoPostalCliente;
	}
	/**
	 * @param codigoPostalCliente the codigoPostalCliente to set
	 */
	public void setCodigoPostalCliente(String codigoPostalCliente) {
		this.codigoPostalCliente = codigoPostalCliente;
	}
	/**
	 * @return the direccionEntrega
	 */
	public String getDireccionEntrega() {
		return direccionEntrega;
	}
	/**
	 * @param direccionEntrega the direccionEntrega to set
	 */
	public void setDireccionEntrega(String direccionEntrega) {
		this.direccionEntrega = direccionEntrega;
	}
	/**
	 * @return the barrioEntrega
	 */
	public String getBarrioEntrega() {
		return barrioEntrega;
	}
	/**
	 * @param barrioEntrega the barrioEntrega to set
	 */
	public void setBarrioEntrega(String barrioEntrega) {
		this.barrioEntrega = barrioEntrega;
	}
	/**
	 * @return the delegacionEntrega
	 */
	public String getDelegacionEntrega() {
		return delegacionEntrega;
	}
	/**
	 * @param delegacionEntrega the delegacionEntrega to set
	 */
	public void setDelegacionEntrega(String delegacionEntrega) {
		this.delegacionEntrega = delegacionEntrega;
	}
	/**
	 * @return the departamentoEntrega
	 */
	public String getDepartamentoEntrega() {
		return departamentoEntrega;
	}
	/**
	 * @param departamentoEntrega the departamentoEntrega to set
	 */
	public void setDepartamentoEntrega(String departamentoEntrega) {
		this.departamentoEntrega = departamentoEntrega;
	}
	/**
	 * @return the telefonoEntrega
	 */
	public String getTelefonoEntrega() {
		return telefonoEntrega;
	}
	/**
	 * @param telefonoEntrega the telefonoEntrega to set
	 */
	public void setTelefonoEntrega(String telefonoEntrega) {
		this.telefonoEntrega = telefonoEntrega;
	}
	/**
	 * @return the celularEntrega
	 */
	public String getCelularEntrega() {
		return celularEntrega;
	}
	/**
	 * @param celularEntrega the celularEntrega to set
	 */
	public void setCelularEntrega(String celularEntrega) {
		this.celularEntrega = celularEntrega;
	}
	/**
	 * @return the apellido1RefFamiliar
	 */
	public String getApellido1RefFamiliar() {
		return apellido1RefFamiliar;
	}
	/**
	 * @param apellido1RefFamiliar the apellido1RefFamiliar to set
	 */
	public void setApellido1RefFamiliar(String apellido1RefFamiliar) {
		this.apellido1RefFamiliar = apellido1RefFamiliar;
	}
	/**
	 * @return the nombre1RefFamiliar
	 */
	public String getNombre1RefFamiliar() {
		return nombre1RefFamiliar;
	}
	/**
	 * @param nombre1RefFamiliar the nombre1RefFamiliar to set
	 */
	public void setNombre1RefFamiliar(String nombre1RefFamiliar) {
		this.nombre1RefFamiliar = nombre1RefFamiliar;
	}
	/**
	 * @return the direccionRefFamiliar
	 */
	public String getDireccionRefFamiliar() {
		return direccionRefFamiliar;
	}
	/**
	 * @param direccionRefFamiliar the direccionRefFamiliar to set
	 */
	public void setDireccionRefFamiliar(String direccionRefFamiliar) {
		this.direccionRefFamiliar = direccionRefFamiliar;
	}
	/**
	 * @return the barrioRefFamiliar
	 */
	public String getBarrioRefFamiliar() {
		return barrioRefFamiliar;
	}
	/**
	 * @param barrioRefFamiliar the barrioRefFamiliar to set
	 */
	public void setBarrioRefFamiliar(String barrioRefFamiliar) {
		this.barrioRefFamiliar = barrioRefFamiliar;
	}
	/**
	 * @return the delegacionRefFamiliar
	 */
	public String getDelegacionRefFamiliar() {
		return delegacionRefFamiliar;
	}
	/**
	 * @param delegacionRefFamiliar the delegacionRefFamiliar to set
	 */
	public void setDelegacionRefFamiliar(String delegacionRefFamiliar) {
		this.delegacionRefFamiliar = delegacionRefFamiliar;
	}
	/**
	 * @return the ciudadRefFamiliar
	 */
	public String getCiudadRefFamiliar() {
		return ciudadRefFamiliar;
	}
	/**
	 * @param ciudadRefFamiliar the ciudadRefFamiliar to set
	 */
	public void setCiudadRefFamiliar(String ciudadRefFamiliar) {
		this.ciudadRefFamiliar = ciudadRefFamiliar;
	}
	/**
	 * @return the departamentoRefFamiliar
	 */
	public String getDepartamentoRefFamiliar() {
		return departamentoRefFamiliar;
	}
	/**
	 * @param departamentoRefFamiliar the departamentoRefFamiliar to set
	 */
	public void setDepartamentoRefFamiliar(String departamentoRefFamiliar) {
		this.departamentoRefFamiliar = departamentoRefFamiliar;
	}
	/**
	 * @return the telefonoRefFamiliar
	 */
	public String getTelefonoRefFamiliar() {
		return telefonoRefFamiliar;
	}
	/**
	 * @param telefonoRefFamiliar the telefonoRefFamiliar to set
	 */
	public void setTelefonoRefFamiliar(String telefonoRefFamiliar) {
		this.telefonoRefFamiliar = telefonoRefFamiliar;
	}
	/**
	 * @return the celularRefFamiliar
	 */
	public String getCelularRefFamiliar() {
		return celularRefFamiliar;
	}
	/**
	 * @param celularRefFamiliar the celularRefFamiliar to set
	 */
	public void setCelularRefFamiliar(String celularRefFamiliar) {
		this.celularRefFamiliar = celularRefFamiliar;
	}
	/**
	 * @return the tipoVinculoRefFamiliar
	 */
	public String getTipoVinculoRefFamiliar() {
		return tipoVinculoRefFamiliar;
	}
	/**
	 * @param tipoVinculoRefFamiliar the tipoVinculoRefFamiliar to set
	 */
	public void setTipoVinculoRefFamiliar(String tipoVinculoRefFamiliar) {
		this.tipoVinculoRefFamiliar = tipoVinculoRefFamiliar;
	}
	/**
	 * @return the apellido1RefNoFamiliar
	 */
	public String getApellido1RefNoFamiliar() {
		return apellido1RefNoFamiliar;
	}
	/**
	 * @param apellido1RefNoFamiliar the apellido1RefNoFamiliar to set
	 */
	public void setApellido1RefNoFamiliar(String apellido1RefNoFamiliar) {
		this.apellido1RefNoFamiliar = apellido1RefNoFamiliar;
	}
	/**
	 * @return the nombre1RefNoFamiliar
	 */
	public String getNombre1RefNoFamiliar() {
		return nombre1RefNoFamiliar;
	}
	/**
	 * @param nombre1RefNoFamiliar the nombre1RefNoFamiliar to set
	 */
	public void setNombre1RefNoFamiliar(String nombre1RefNoFamiliar) {
		this.nombre1RefNoFamiliar = nombre1RefNoFamiliar;
	}
	/**
	 * @return the telefonoRefNoFamiliar
	 */
	public String getTelefonoRefNoFamiliar() {
		return telefonoRefNoFamiliar;
	}
	/**
	 * @param telefonoRefNoFamiliar the telefonoRefNoFamiliar to set
	 */
	public void setTelefonoRefNoFamiliar(String telefonoRefNoFamiliar) {
		this.telefonoRefNoFamiliar = telefonoRefNoFamiliar;
	}
	/**
	 * @return the celularRefNoFamiliar
	 */
	public String getCelularRefNoFamiliar() {
		return celularRefNoFamiliar;
	}
	/**
	 * @param celularRefNoFamiliar the celularRefNoFamiliar to set
	 */
	public void setCelularRefNoFamiliar(String celularRefNoFamiliar) {
		this.celularRefNoFamiliar = celularRefNoFamiliar;
	}
	/**
	 * @return the tipoVinculoRefNoFamiliar
	 */
	public String getTipoVinculoRefNoFamiliar() {
		return tipoVinculoRefNoFamiliar;
	}
	/**
	 * @param tipoVinculoRefNoFamiliar the tipoVinculoRefNoFamiliar to set
	 */
	public void setTipoVinculoRefNoFamiliar(String tipoVinculoRefNoFamiliar) {
		this.tipoVinculoRefNoFamiliar = tipoVinculoRefNoFamiliar;
	}
	/**
	 * @return the nombreEmpresaFiador
	 */
	public String getNombreEmpresaFiador() {
		return nombreEmpresaFiador;
	}
	/**
	 * @param nombreEmpresaFiador the nombreEmpresaFiador to set
	 */
	public void setNombreEmpresaFiador(String nombreEmpresaFiador) {
		this.nombreEmpresaFiador = nombreEmpresaFiador;
	}
	/**
	 * @return the direccionEmpresaFiador
	 */
	public String getDireccionEmpresaFiador() {
		return direccionEmpresaFiador;
	}
	/**
	 * @param direccionEmpresaFiador the direccionEmpresaFiador to set
	 */
	public void setDireccionEmpresaFiador(String direccionEmpresaFiador) {
		this.direccionEmpresaFiador = direccionEmpresaFiador;
	}
	/**
	 * @return the cargoFiador
	 */
	public String getCargoFiador() {
		return cargoFiador;
	}
	/**
	 * @param cargoFiador the cargoFiador to set
	 */
	public void setCargoFiador(String cargoFiador) {
		this.cargoFiador = cargoFiador;
	}
	/**
	 * @return the tipoVinculoFiador
	 */
	public String getTipoVinculoFiador() {
		return tipoVinculoFiador;
	}
	/**
	 * @param tipoVinculoFiador the tipoVinculoFiador to set
	 */
	public void setTipoVinculoFiador(String tipoVinculoFiador) {
		this.tipoVinculoFiador = tipoVinculoFiador;
	}
	/**
	 * @return the requiereFactura
	 */
	public String getRequiereFactura() {
		return requiereFactura;
	}
	/**
	 * @param requiereFactura the requiereFactura to set
	 */
	public void setRequiereFactura(String requiereFactura) {
		this.requiereFactura = requiereFactura;
	}
	/**
	 * @return the direccionContribuyente
	 */
	public String getDireccionContribuyente() {
		return direccionContribuyente;
	}
	/**
	 * @param direccionContribuyente the direccionContribuyente to set
	 */
	public void setDireccionContribuyente(String direccionContribuyente) {
		this.direccionContribuyente = direccionContribuyente;
	}
	/**
	 * @return the barrioContribuyente
	 */
	public String getBarrioContribuyente() {
		return barrioContribuyente;
	}
	/**
	 * @param barrioContribuyente the barrioContribuyente to set
	 */
	public void setBarrioContribuyente(String barrioContribuyente) {
		this.barrioContribuyente = barrioContribuyente;
	}
	/**
	 * @return the delegacionContribuyente
	 */
	public String getDelegacionContribuyente() {
		return delegacionContribuyente;
	}
	/**
	 * @param delegacionContribuyente the delegacionContribuyente to set
	 */
	public void setDelegacionContribuyente(String delegacionContribuyente) {
		this.delegacionContribuyente = delegacionContribuyente;
	}
	/**
	 * @return the ciudadContribuyente
	 */
	public String getCiudadContribuyente() {
		return ciudadContribuyente;
	}
	/**
	 * @param ciudadContribuyente the ciudadContribuyente to set
	 */
	public void setCiudadContribuyente(String ciudadContribuyente) {
		this.ciudadContribuyente = ciudadContribuyente;
	}
	/**
	 * @return the departamentoContribuyente
	 */
	public String getDepartamentoContribuyente() {
		return departamentoContribuyente;
	}
	/**
	 * @param departamentoContribuyente the departamentoContribuyente to set
	 */
	public void setDepartamentoContribuyente(String departamentoContribuyente) {
		this.departamentoContribuyente = departamentoContribuyente;
	}
	/**
	 * @return the codigoPostalContribuyente
	 */
	public String getCodigoPostalContribuyente() {
		return codigoPostalContribuyente;
	}
	/**
	 * @param codigoPostalContribuyente the codigoPostalContribuyente to set
	 */
	public void setCodigoPostalContribuyente(String codigoPostalContribuyente) {
		this.codigoPostalContribuyente = codigoPostalContribuyente;
	}
	/**
	 * @return the numeroContribuyente
	 */
	public String getNumeroContribuyente() {
		return numeroContribuyente;
	}
	/**
	 * @param numeroContribuyente the numeroContribuyente to set
	 */
	public void setNumeroContribuyente(String numeroContribuyente) {
		this.numeroContribuyente = numeroContribuyente;
	}
	/**
	 * @return the direccionRefNoFamiliar
	 */
	public String getDireccionRefNoFamiliar() {
		return direccionRefNoFamiliar;
	}
	/**
	 * @param direccionRefNoFamiliar the direccionRefNoFamiliar to set
	 */
	public void setDireccionRefNoFamiliar(String direccionRefNoFamiliar) {
		this.direccionRefNoFamiliar = direccionRefNoFamiliar;
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
	 * @return the indicadorGuardar
	 */
	public String getIndicadorGuardar() {
		return indicadorGuardar;
	}
	/**
	 * @param indicadorGuardar the indicadorGuardar to set
	 */
	public void setIndicadorGuardar(String indicadorGuardar) {
		this.indicadorGuardar = indicadorGuardar;
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
	 * @return editableCampana
	 */
	public boolean isEditableCampana() {
		return editableCampana;
	}
	
	/**
	 * @param editableCampana
	 */
	public void setEditableCampana(boolean editableCampana) {
		this.editableCampana = editableCampana;
	}
	
	public String getCodSexoCliente() {
		return codSexoCliente;
	}
	public void setCodSexoCliente(String codSexoCliente) {
		this.codSexoCliente = codSexoCliente;
	}
	public String getNombreConsultora() {
		return nombreConsultora;
	}
	public void setNombreConsultora(String nombreConsultora) {
		this.nombreConsultora = nombreConsultora;
	}
	public String getStatusConsultora() {
		return statusConsultora;
	}
	public void setStatusConsultora(String statusConsultora) {
		this.statusConsultora = statusConsultora;
	}
	public String getCampaniaUltConsultora() {
		return campaniaUltConsultora;
	}
	public void setCampaniaUltConsultora(String campaniaUltConsultora) {
		this.campaniaUltConsultora = campaniaUltConsultora;
	}
	public String getDireccionConsultora() {
		return direccionConsultora;
	}
	public void setDireccionConsultora(String direccionConsultora) {
		this.direccionConsultora = direccionConsultora;
	}
	public String getUnidadGeoConsultora() {
		return unidadGeoConsultora;
	}
	public void setUnidadGeoConsultora(String unidadGeoConsultora) {
		this.unidadGeoConsultora = unidadGeoConsultora;
	}
	public String getBloqueoConsultora() {
		return bloqueoConsultora;
	}
	public void setBloqueoConsultora(String bloqueoConsultora) {
		this.bloqueoConsultora = bloqueoConsultora;
	}
	public String getRegionConsultora() {
		return regionConsultora;
	}
	public void setRegionConsultora(String regionConsultora) {
		this.regionConsultora = regionConsultora;
	}
	public String getZonaConsultora() {
		return zonaConsultora;
	}
	public void setZonaConsultora(String zonaConsultora) {
		this.zonaConsultora = zonaConsultora;
	}
	public String getSeccionConsultora() {
		return seccionConsultora;
	}
	public void setSeccionConsultora(String seccionConsultora) {
		this.seccionConsultora = seccionConsultora;
	}
	public String getTerritorioConsultora() {
		return territorioConsultora;
	}
	public void setTerritorioConsultora(String territorioConsultora) {
		this.territorioConsultora = territorioConsultora;
	}
	public String getActivaConsultora() {
		return activaConsultora;
	}
	public void setActivaConsultora(String activaConsultora) {
		this.activaConsultora = activaConsultora;
	}
	/**
	 * @return the indMostrarDatos
	 */
	public String getIndMostrarDatos() {
		return indMostrarDatos;
	}
	/**
	 * @param indMostrarDatos the indMostrarDatos to set
	 */
	public void setIndMostrarDatos(String indMostrarDatos) {
		this.indMostrarDatos = indMostrarDatos;
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
	 * @return the codigoCiudadDomicilioUbigeo
	 */
	public String getCodigoCiudadDomicilioUbigeo() {
		return codigoCiudadDomicilioUbigeo;
	}
	/**
	 * @param codigoCiudadDomicilioUbigeo the codigoCiudadDomicilioUbigeo to set
	 */
	public void setCodigoCiudadDomicilioUbigeo(String codigoCiudadDomicilioUbigeo) {
		this.codigoCiudadDomicilioUbigeo = codigoCiudadDomicilioUbigeo;
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
	 * @return the codigoCiudadEntregaUbigeo
	 */
	public String getCodigoCiudadEntregaUbigeo() {
		return codigoCiudadEntregaUbigeo;
	}
	/**
	 * @param codigoCiudadEntregaUbigeo the codigoCiudadEntregaUbigeo to set
	 */
	public void setCodigoCiudadEntregaUbigeo(String codigoCiudadEntregaUbigeo) {
		this.codigoCiudadEntregaUbigeo = codigoCiudadEntregaUbigeo;
	}
	/**
	 * @return the villaPoblacionEntrega
	 */
	public String getVillaPoblacionEntrega() {
		return villaPoblacionEntrega;
	}
	/**
	 * @param villaPoblacionEntrega the villaPoblacionEntrega to set
	 */
	public void setVillaPoblacionEntrega(String villaPoblacionEntrega) {
		this.villaPoblacionEntrega = villaPoblacionEntrega;
	}
	/**
	 * @return the estadoComercial
	 */
	public String getEstadoComercial() {
		return estadoComercial;
	}
	/**
	 * @param estadoComercial the estadoComercial to set
	 */
	public void setEstadoComercial(String estadoComercial) {
		this.estadoComercial = estadoComercial;
	}
	/**
	 * @return the limInfVerde
	 */
	public String getLimInfVerde() {
		return limInfVerde;
	}
	/**
	 * @param limInfVerde the limInfVerde to set
	 */
	public void setLimInfVerde(String limInfVerde) {
		this.limInfVerde = limInfVerde;
	}
	/**
	 * @return the limSupVerde
	 */
	public String getLimSupVerde() {
		return limSupVerde;
	}
	/**
	 * @param limSupVerde the limSupVerde to set
	 */
	public void setLimSupVerde(String limSupVerde) {
		this.limSupVerde = limSupVerde;
	}
	/**
	 * @return the limInfAmarillo
	 */
	public String getLimInfAmarillo() {
		return limInfAmarillo;
	}
	/**
	 * @param limInfAmarillo the limInfAmarillo to set
	 */
	public void setLimInfAmarillo(String limInfAmarillo) {
		this.limInfAmarillo = limInfAmarillo;
	}
	/**
	 * @return the limSupAmarillo
	 */
	public String getLimSupAmarillo() {
		return limSupAmarillo;
	}
	/**
	 * @param limSupAmarillo the limSupAmarillo to set
	 */
	public void setLimSupAmarillo(String limSupAmarillo) {
		this.limSupAmarillo = limSupAmarillo;
	}
	/**
	 * @return the limInfRojo
	 */
	public String getLimInfRojo() {
		return limInfRojo;
	}
	/**
	 * @param limInfRojo the limInfRojo to set
	 */
	public void setLimInfRojo(String limInfRojo) {
		this.limInfRojo = limInfRojo;
	}
	/**
	 * @return the limSupRojo
	 */
	public String getLimSupRojo() {
		return limSupRojo;
	}
	/**
	 * @param limSupRojo the limSupRojo to set
	 */
	public void setLimSupRojo(String limSupRojo) {
		this.limSupRojo = limSupRojo;
	}
	/**
	 * @return the montoInfoComercial
	 */
	public String getMontoInfoComercial() {
		return montoInfoComercial;
	}
	/**
	 * @param montoInfoComercial the montoInfoComercial to set
	 */
	public void setMontoInfoComercial(String montoInfoComercial) {
		this.montoInfoComercial = montoInfoComercial;
	}
	/**
	 * @return the indActivaWS
	 */
	public String getIndActivaWS() {
		return indActivaWS;
	}
	/**
	 * @param indActivaWS the indActivaWS to set
	 */
	public void setIndActivaWS(String indActivaWS) {
		this.indActivaWS = indActivaWS;
	}
	/**
	 * @return the nombresInfocorp
	 */
	public String getNombresInfocorp() {
		return nombresInfocorp;
	}
	/**
	 * @param nombresInfocorp the nombresInfocorp to set
	 */
	public void setNombresInfocorp(String nombresInfocorp) {
		this.nombresInfocorp = nombresInfocorp;
	}
	/**
	 * @return the explicacionInfocorp
	 */
	public String getExplicacionInfocorp() {
		return explicacionInfocorp;
	}
	/**
	 * @param explicacionInfocorp the explicacionInfocorp to set
	 */
	public void setExplicacionInfocorp(String explicacionInfocorp) {
		this.explicacionInfocorp = explicacionInfocorp;
	}
	/**
	 * @return the codigoConsultora
	 */
	public String getCodigoConsultora() {
		return codigoConsultora;
	}
	/**
	 * @param codigoConsultora the codigoConsultora to set
	 */
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}
	

	
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
	/**
	 * @return the mostrarObservacion
	 */
	public boolean isMostrarMotivoObservacion() {
		return mostrarMotivoObservacion;
	}
	/**
	 * @param mostrarObservacion the mostrarObservacion to set
	 */
	public void setMostrarMotivoObservacion(boolean mostrarMotivoObservacion) {
		this.mostrarMotivoObservacion = mostrarMotivoObservacion;
	}
	
	/**
	 * @return the montoMeta
	 */
	public String getMontoMeta() {
		return montoMeta;
	}
	/**
	 * @param montoMeta the montoMeta to set
	 */
	public void setMontoMeta(String montoMeta) {
		this.montoMeta = montoMeta;
	}
	/**
	 * @return the tipoMeta
	 */
	public String getTipoMeta() {
		return tipoMeta;
	}
	/**
	 * @param tipoMeta the tipoMeta to set
	 */
	public void setTipoMeta(String tipoMeta) {
		this.tipoMeta = tipoMeta;
	}
	
	public String getDescripcionMeta() {
		return descripcionMeta;
	}
	
	public void setDescripcionMeta(String descripcionMeta) {
		this.descripcionMeta = descripcionMeta;
	}
	
	public String getMuestraMetas() {
		return muestraMetas;
	}
	
	public void setMuestraMetas(String muestraMetas) {
		this.muestraMetas = muestraMetas;
	}
	
	public String getMuestraIndicadorBuzon() {
		return muestraIndicadorBuzon;
	}
	
	public void setMuestraIndicadorBuzon(String muestraIndicadorBuzon) {
		this.muestraIndicadorBuzon = muestraIndicadorBuzon;
	}
	
	public String getIndicadorBuzon() {
		return indicadorBuzon;
	}
	
	public void setIndicadorBuzon(String indicadorBuzon) {
		this.indicadorBuzon = indicadorBuzon;
	}
	
	public String getIndInformacionOk() {
		return indInformacionOk;
	}
	
	public void setIndInformacionOk(String indInformacionOk) {
		this.indInformacionOk = indInformacionOk;
	}
	 
	public String getIndSitCrediticia() {
		return indSitCrediticia;
	}
	
	public void setIndSitCrediticia(String indSitCrediticia) {
		this.indSitCrediticia = indSitCrediticia;
	}
	
	public String getIndSinSalAmbas() {
		return indSinSalAmbas;
	}
	
	public void setIndSinSalAmbas(String indSinSalAmbas) {
		this.indSinSalAmbas = indSinSalAmbas;
	}
	
	public String getIndViewMotiGest() {
		return indViewMotiGest;
	}
	
	public void setIndViewMotiGest(String indViewMotiGest) {
		this.indViewMotiGest = indViewMotiGest;
	}
	
	public String getValorId() {
		return valorId;
	}
	
	public void setValorId(String valorId) {
		this.valorId = valorId;
	}
	
	public boolean isIndicadorLiderRecomendante() {
		return indicadorLiderRecomendante;
	}
	
	public void setIndicadorLiderRecomendante(boolean indicadorLiderRecomendante) {
		this.indicadorLiderRecomendante = indicadorLiderRecomendante;
	}
	
	public String getCodigoLiderRecomendante() {
		return codigoLiderRecomendante;
	}
	
	public void setCodigoLiderRecomendante(String codigoLiderRecomendante) {
		this.codigoLiderRecomendante = codigoLiderRecomendante;
	}
	
	public boolean isIndicadorMostrarFacturacionElectronica() {
		return indicadorMostrarFacturacionElectronica;
	}
	
	public void setIndicadorMostrarFacturacionElectronica(
			boolean indicadorMostrarFacturacionElectronica) {
		this.indicadorMostrarFacturacionElectronica = indicadorMostrarFacturacionElectronica;
	}
	
	public String getIndicadorFacturacionElectronica() {
		return indicadorFacturacionElectronica;
	}
	
	public void setIndicadorFacturacionElectronica(
			String indicadorFacturacionElectronica) {
		this.indicadorFacturacionElectronica = indicadorFacturacionElectronica;
	}
	
	public String getIndicadorFacturacionElectronicaTempo() {
		return indicadorFacturacionElectronicaTempo;
	}
	
	public void setIndicadorFacturacionElectronicaTempo(
			String indicadorFacturacionElectronicaTempo) {
		this.indicadorFacturacionElectronicaTempo = indicadorFacturacionElectronicaTempo;
	}

	public Date getFechaProcesoDate() {
		return fechaProcesoDate;
	}

	public void setFechaProcesoDate(Date fechaProcesoDate) {
		this.fechaProcesoDate = fechaProcesoDate;
	}

	public Date getFechaNacimientoDate() {
		return fechaNacimientoDate;
	}

	public void setFechaNacimientoDate(Date fechaNacimientoDate) {
		this.fechaNacimientoDate = fechaNacimientoDate;
	}

	public Date getFecProcesoDate() {
		return fecProcesoDate;
	}

	public void setFecProcesoDate(Date fecProcesoDate) {
		this.fecProcesoDate = fecProcesoDate;
	}

	public Date getFecNacimientoDate() {
		return fecNacimientoDate;
	}

	public void setFecNacimientoDate(Date fecNacimientoDate) {
		this.fecNacimientoDate = fecNacimientoDate;
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
	 * @return the tipoPersona
	 */
	public String getTipoPersona() {
		return tipoPersona;
	}

	/**
	 * @param tipoPersona the tipoPersona to set
	 */
	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	/**
	 * @return the origenIngreso
	 */
	public String getOrigenIngreso() {
		return origenIngreso;
	}

	/**
	 * @param origenIngreso the origenIngreso to set
	 */
	public void setOrigenIngreso(String origenIngreso) {
		this.origenIngreso = origenIngreso;
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
