package biz.belcorp.ssicc.dao.spusicc.sto.model;

import java.io.Serializable;
import java.math.BigDecimal;



public class SolicitudCredito implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codPais;
	private String codCompania;
	private String codCliente;
	private String numDocumento;
	private String fechaProceso;
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
	private String codSexoCliente;
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
	private String detalle;
	
	private String indicadorTelefonoOK;
	private String indicadorSituacionCrediticia;
	private String indicadorSinSaldoAmbasMarcas;	
	
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

	//sb PER-SiCC-2012-0460 ini
	private String codigoCiudadDomicilio;
	private String codigoCiudadDomicilioUbigeo;
	private String villaPoblacionDomicilio;
	private String codigoCiudadEntrega;
	private String codigoCiudadEntregaUbigeo;
	private String villaPoblacionEntrega;	
	//sb PER-SiCC-2012-0460 fin
	//sb ini boletin comercial	
	private BigDecimal montoInfoComercial;
	private String estadoComercial;
	//sb fin boletin comercial
	
	private String nombresInfocorp;	
	private String explicacionInfocorp;
	
	private String montoMeta;
	private String tipoMeta;
	private String descripcionMeta;
	
	private String oidMotiGes;
	private String valObseGestion;
	
	private String codigoLiderRecomendante;
	private String indicadorFacturacionElectronica;
	
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
	 * @return Returns the codClienteRetenido.
	 */
	public String getCodClienteRetenido() {
		return codClienteRetenido;
	}
	/**
	 * @param codClienteRetenido The codClienteRetenido to set.
	 */
	public void setCodClienteRetenido(String codClienteRetenido) {
		this.codClienteRetenido = codClienteRetenido;
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
	 * @return Returns the codDepaFiador.
	 */
	public String getCodDepaFiador() {
		return codDepaFiador;
	}
	/**
	 * @param codDepaFiador The codDepaFiador to set.
	 */
	public void setCodDepaFiador(String codDepaFiador) {
		this.codDepaFiador = codDepaFiador;
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
	 * @return Returns the codDistFiador.
	 */
	public String getCodDistFiador() {
		return codDistFiador;
	}
	/**
	 * @param codDistFiador The codDistFiador to set.
	 */
	public void setCodDistFiador(String codDistFiador) {
		this.codDistFiador = codDistFiador;
	}
	/**
	 * @return Returns the codDocuFiador.
	 */
	public String getCodDocuFiador() {
		return codDocuFiador;
	}
	/**
	 * @param codDocuFiador The codDocuFiador to set.
	 */
	public void setCodDocuFiador(String codDocuFiador) {
		this.codDocuFiador = codDocuFiador;
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
	 * @return Returns the codPremio.
	 */
	public String getCodPremio() {
		return codPremio;
	}
	/**
	 * @param codPremio The codPremio to set.
	 */
	public void setCodPremio(String codPremio) {
		this.codPremio = codPremio;
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
	 * @return Returns the codProvFiador.
	 */
	public String getCodProvFiador() {
		return codProvFiador;
	}
	/**
	 * @param codProvFiador The codProvFiador to set.
	 */
	public void setCodProvFiador(String codProvFiador) {
		this.codProvFiador = codProvFiador;
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
	
	public String getCodSexoCliente() {
		return codSexoCliente;
	}
	public void setCodSexoCliente(String codSexoCliente) {
		this.codSexoCliente = codSexoCliente;
	}
	/**
	 * @return Returns the codSectFiador.
	 */
	public String getCodSectFiador() {
		return codSectFiador;
	}
	/**
	 * @param codSectFiador The codSectFiador to set.
	 */
	public void setCodSectFiador(String codSectFiador) {
		this.codSectFiador = codSectFiador;
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
	 * @return Returns the indEstaCivil.
	 */
	public String getIndEstaCivil() {
		return indEstaCivil;
	}
	/**
	 * @param indEstaCivil The indEstaCivil to set.
	 */
	public void setIndEstaCivil(String indEstaCivil) {
		this.indEstaCivil = indEstaCivil;
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
	 * @return Returns the indNivelEducativo.
	 */
	public String getIndNivelEducativo() {
		return indNivelEducativo;
	}
	/**
	 * @param indNivelEducativo The indNivelEducativo to set.
	 */
	public void setIndNivelEducativo(String indNivelEducativo) {
		this.indNivelEducativo = indNivelEducativo;
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
	 * @return Returns the numDireFiador.
	 */
	public String getNumDireFiador() {
		return numDireFiador;
	}
	/**
	 * @param numDireFiador The numDireFiador to set.
	 */
	public void setNumDireFiador(String numDireFiador) {
		this.numDireFiador = numDireFiador;
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
	 * @return Returns the tipoDocuFiador.
	 */
	public String getTipoDocuFiador() {
		return tipoDocuFiador;
	}
	/**
	 * @param tipoDocuFiador The tipoDocuFiador to set.
	 */
	public void setTipoDocuFiador(String tipoDocuFiador) {
		this.tipoDocuFiador = tipoDocuFiador;
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
	 * @return Returns the fechaNacimiento.
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	/**
	 * @param fechaNacimiento The fechaNacimiento to set.
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
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
	 * @return the montoInfoComercial
	 */
	public BigDecimal getMontoInfoComercial() {
		return montoInfoComercial;
	}
	/**
	 * @param montoInfoComercial the montoInfoComercial to set
	 */
	public void setMontoInfoComercial(BigDecimal montoInfoComercial) {
		this.montoInfoComercial = montoInfoComercial;
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
	/**
	 * @return the descripcionMeta
	 */
	public String getDescripcionMeta() {
		return descripcionMeta;
	}
	/**
	 * @param descripcionMeta the descripcionMeta to set
	 */
	public void setDescripcionMeta(String descripcionMeta) {
		this.descripcionMeta = descripcionMeta;
	}
	/**
	 * @return the oidMotiGes
	 */
	public String getOidMotiGes() {
		return oidMotiGes;
	}
	/**
	 * @return the valObseGestion
	 */
	public String getValObseGestion() {
		return valObseGestion;
	}
	/**
	 * @param oidMotiGes the oidMotiGes to set
	 */
	public void setOidMotiGes(String oidMotiGes) {
		this.oidMotiGes = oidMotiGes;
	}
	/**
	 * @param valObseGestion the valObseGestion to set
	 */
	public void setValObseGestion(String valObseGestion) {
		this.valObseGestion = valObseGestion;
	}
	/**
	 * @return the codigoLiderRecomendante
	 */
	public String getCodigoLiderRecomendante() {
		return codigoLiderRecomendante;
	}
	/**
	 * @param codigoLiderRecomendante the codigoLiderRecomendante to set
	 */
	public void setCodigoLiderRecomendante(String codigoLiderRecomendante) {
		this.codigoLiderRecomendante = codigoLiderRecomendante;
	}
	/**
	 * @return the indicadorFacturacionElectronica
	 */
	public String getIndicadorFacturacionElectronica() {
		return indicadorFacturacionElectronica;
	}
	/**
	 * @param indicadorFacturacionElectronica the indicadorFacturacionElectronica to set
	 */
	public void setIndicadorFacturacionElectronica(
			String indicadorFacturacionElectronica) {
		this.indicadorFacturacionElectronica = indicadorFacturacionElectronica;
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
}
