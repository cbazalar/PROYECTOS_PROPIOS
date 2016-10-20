package biz.belcorp.ssicc.web.spusicc.zon.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoZONCargosForm extends BaseSearchForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoPais;
	private String codigoCargo;
	private String codigoCargoBase;
	private String descripcionCargo;
	private String codigoTipoUniAdmi;
	private String cantUniAdmi;
	private String codigoTitular;
	private String posicion ;
	private String oidIdioma;
	private String codigoCargoEditar;
	private String indicadorAdmin;
	private String rolDesc;
	private String perfilDesc;
		
	//Asignar Cargo
	private String codigoClienteBuscar;
	private String codigoCargoAsignarCambiar;
	private String codigoRegionUnico;
	private String codigoRegion[];
	private String codigoZonaUnico;
	private String codigoZona[];
	private String fechaIngreso;
	private Date fechaIngresoDate;
	private String fechaIngresoHasta;
	private Date fechaIngresoHastaDate;
	private String campanyaProceso;
	private String nombresCompletosConsultora;
	private String fechaAsignacion;
	private Date fechaAsignacionDate;
	private String fechaCambioCargo;
	private Date fechaCambioCargoDate;
	private String fechaVenta;
	private Date fechaVentaDate;
	private String fechaFacturacion;
	private String fechaRefacturacion;
	private String campanyaFacturacion;
	private String campanyaVenta;
	private String campanyaFacturacion2;
	private String campanyaVenta2;	
	
	//Cambiar Cargo
	private String codigoCargoActual;
	private String fechaRegistro;
	private Date fechaRegistroDate;
	private String codigoNuevoCargo;
	private String fechaNombramiento;
	private Date fechaNombramientoDate;
	private String cargoActualCambiar;
	private String codigoCargoNuevo;
	private String codigoConsultora;
	private String codigoCargoAnterior;
	private String codigoRegionAnterior;
	private String codigoZonaAnterior;
	
	//Mantener Licencias
	private String codigoUnidadAdministrativa;
	private String codigoRegionLic;
	private String codigoZonaLic;
	private String fechaSalida;
	private Date fechaSalidaDate;
	private String fechaRegreso;
	private Date fechaRegresoDate;
	private String nroDocumentoIdentidadReemplazo;
	private String codigoCargoReemplazo;
	private String unidadesAdministrativasaCargo;
	private String codigoTipoLicencia;
	private String codigoCargoLicencia;
	private String listaUnidadesAdministrativas;
	private String nombresCompletosConsultoraReemplazo;
	private String descripcionCargoConsultoraReemplazo;
	////////////////////////////////////////////////
	
	private String nombreCliente;
	private String docCliente;
	
	private String fechaRotacion;
//	private Date fechaRotacionDate;
	private String fechaRotacionActual;
		
	//Retiro personal
	private String fechaRetiro;
	private Date fechaRetiroDate;
	private String fechaRetiroActual;
	
	private String codigoRegionNueva;
	private String codigoZonaNueva;
	
	private String codigoOperacion;
	private String oidDirecVentDetal;
	private String subGerencia;
	
	private String codigoSubGerencia;
	
	private String numeroDocIdentidadBuscar;
	
	private String indicadorTitularNoTitular;
	private String indicadorRegistrar;
	private String indicadorEditar;
	private String indicadorEliminar;
	
	private String correlativoCabecera;	
	private String codigoCorrrelativoCabeceraReemplazo;
	
	private String[] codigoCorrrelativoCabeceraReemplazos;
	
//	public void reset(ActionMapping mapping, HttpServletRequest request) {
//		this.indicadorAdmin = Constants.NUMERO_CERO;
//	}
	
	/**
	 * @return the subGerencia
	 */
	public String getSubGerencia() {
		return subGerencia;
	}
	/**
	 * @param subGerencia the subGerencia to set
	 */
	public void setSubGerencia(String subGerencia) {
		this.subGerencia = subGerencia;
	}
	/**
	 * @return the oidDirecVentDetal
	 */
	public String getOidDirecVentDetal() {
		return oidDirecVentDetal;
	}
	/**
	 * @param oidDirecVentDetal the oidDirecVentDetal to set
	 */
	public void setOidDirecVentDetal(String oidDirecVentDetal) {
		this.oidDirecVentDetal = oidDirecVentDetal;
	}
	
	/**
	 * @return the codigoOperacion
	 */
	public String getCodigoOperacion() {
		return codigoOperacion;
	}
	/**
	 * @param codigoOperacion the codigoOperacion to set
	 */
	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}
	
	/**
	 * @return the codigoZonaNueva
	 */
	public String getCodigoZonaNueva() {
		return codigoZonaNueva;
	}
	/**
	 * @param codigoZonaNueva the codigoZonaNueva to set
	 */
	public void setCodigoZonaNueva(String codigoZonaNueva) {
		this.codigoZonaNueva = codigoZonaNueva;
	}
	
	/**
	 * @return the codigoRegionNueva
	 */
	public String getCodigoRegionNueva() {
		return codigoRegionNueva;
	}
	/**
	 * @param codigoRegionNueva the codigoRegionNueva to set
	 */
	public void setCodigoRegionNueva(String codigoRegionNueva) {
		this.codigoRegionNueva = codigoRegionNueva;
	}
	
	/**
	 * @return the fechaRetiro
	 */
	public String getFechaRetiro() {
		return fechaRetiro;
	}
	/**
	 * @param fechaRetiro the fechaRetiro to set
	 */
	public void setFechaRetiro(String fechaRetiro) {
		this.fechaRetiro = fechaRetiro;
	}
	/**
	 * @return the fechaRotacion
	 */
	public String getFechaRotacion() {
		return fechaRotacion;
	}
	/**
	 * @param fechaRotacion the fechaRotacion to set
	 * @struts.validator type = "required"
	 */
	public void setFechaRotacion(String fechaRotacion) {
		this.fechaRotacion = fechaRotacion;
	}
	/**
	 * @return the docCliente
	 */
	public String getDocCliente() {
		return docCliente;
	}
	/**
	 * @param docCliente the docCliente to set
	 */
	public void setDocCliente(String docCliente) {
		this.docCliente = docCliente;
	}
	
	/**
	 * @return the nombreCliente
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}
	/**
	 * @param nombreCliente the nombreCliente to set
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the codigoCargo
	 */
	public String getCodigoCargo() {
		return codigoCargo;
	}
	/**
	 * @param codigoCargo the codigoCargo to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoCargo(String codigoCargo) {
		this.codigoCargo = codigoCargo;
	}
	/**
	 * @return the codigoCargoBase
	 */
	public String getCodigoCargoBase() {
		return codigoCargoBase;
	}
	/**
	 * @param codigoCargoBase the codigoCargoBase to set
	 */
	public void setCodigoCargoBase(String codigoCargoBase) {
		this.codigoCargoBase = codigoCargoBase;
	}
	/**
	 * @return the descripcionCargo
	 */
	public String getDescripcionCargo() {
		return descripcionCargo;
	}
	/**
	 * @param descripcionCargo the descripcionCargo to set
	 * @struts.validator type = "required"
	 */
	public void setDescripcionCargo(String descripcionCargo) {
		this.descripcionCargo = descripcionCargo;
	}
	/**
	 * @return the codigoTipoUniAdmi
	 */
	public String getCodigoTipoUniAdmi() {
		return codigoTipoUniAdmi;
	}
	/**
	 * @param codigoTipoUniAdmi the codigoTipoUniAdmi to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoTipoUniAdmi(String codigoTipoUniAdmi) {
		this.codigoTipoUniAdmi = codigoTipoUniAdmi;
	}
	/**
	 * @return the cantUniAdmi
	 */
	public String getCantUniAdmi() {
		return cantUniAdmi;
	}
	/**
	 * @param cantUniAdmi the cantUniAdmi to set
	 * @struts.validator type = "required"
	 */
	public void setCantUniAdmi(String cantUniAdmi) {
		this.cantUniAdmi = cantUniAdmi;
	}
	/**
	 * @return the codigoTitular
	 */
	public String getCodigoTitular() {
		return codigoTitular;
	}
	/**
	 * @param codigoTitular the codigoTitular to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoTitular(String codigoTitular) {
		this.codigoTitular = codigoTitular;
	}

	/**
	 * @return the oidIdioma
	 */
	public String getOidIdioma() {
		return oidIdioma;
	}
	/**
	 * @param oidIdioma the oidIdioma to set
	 */
	public void setOidIdioma(String oidIdioma) {
		this.oidIdioma = oidIdioma;
	}
	// ***** Asignar Cargo
	/**
	 * @return the codigoClienteBuscar
	 */
	public String getCodigoClienteBuscar() {
		return codigoClienteBuscar;
	}
	/**
	 * @param codigoClienteBuscar the codigoClienteBuscar to set
	 */
	public void setCodigoClienteBuscar(String codigoClienteBuscar) {
		this.codigoClienteBuscar = codigoClienteBuscar;
	}
	
	/**
	 * @return the codigoRegion
	 */
	public String[] getCodigoRegion() {
		return codigoRegion;
	}
	/**
	 * @param codigoRegion the codigoRegion to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoRegion(String[] codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	/**
	 * @return the codigoZona
	 */
	public String[] getCodigoZona() {
		return codigoZona;
	}
	/**
	 * @param codigoZona the codigoZona to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoZona(String[] codigoZona) {
		this.codigoZona = codigoZona;
	}
	/**
	 * @return the fechaIngreso
	 */
	public String getFechaIngreso() {
		return fechaIngreso;
	}
	/**
	 * @param fechaIngreso the fechaIngreso to set
	 * @struts.validator type = "required"
	 */
	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	/**
	 * @return the fechaIngresoHasta
	 */
	public String getFechaIngresoHasta() {
		return fechaIngresoHasta;
	}
	/**
	 * @param fechaIngresoHasta the fechaIngresoHasta to set
	 */
	public void setFechaIngresoHasta(String fechaIngresoHasta) {
		this.fechaIngresoHasta = fechaIngresoHasta;
	}
	/**
	 * @return the codigoCargoAsignarCambiar
	 */
	public String getCodigoCargoAsignarCambiar() {
		return codigoCargoAsignarCambiar;
	}
	/**
	 * @param codigoCargoAsignarCambiar the codigoCargoAsignarCambiar to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoCargoAsignarCambiar(String codigoCargoAsignarCambiar) {
		this.codigoCargoAsignarCambiar = codigoCargoAsignarCambiar;
	}
	/**
	 * @return the campanyaProceso
	 */
	public String getCampanyaProceso() {
		return campanyaProceso;
	}
	/**
	 * @param campanyaProceso the campanyaProceso to set
	 */
	public void setCampanyaProceso(String campanyaProceso) {
		this.campanyaProceso = campanyaProceso;
	}
	/**
	 * @return the posicion
	 */
	public String getPosicion() {
		return posicion;
	}
	/**
	 * @param posicion the posicion to set
	 */
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	/**
	 * @return the nombresCompletosConsultora
	 */
	public String getNombresCompletosConsultora() {
		return nombresCompletosConsultora;
	}
	/**
	 * @param nombresCompletosConsultora the nombresCompletosConsultora to set
	 */
	public void setNombresCompletosConsultora(String nombresCompletosConsultora) {
		this.nombresCompletosConsultora = nombresCompletosConsultora;
	}
	//Cambiar Cargo
	/**
	 * @return the fechaRegistro
	 */
	public String getFechaRegistro() {
		return fechaRegistro;
	}
	/**
	 * @param fechaRegistro the fechaRegistro to set
	 * @struts.validator type = "required"
	 */
	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	/**
	 * @return the codigoNuevoCargo
	 */
	public String getCodigoNuevoCargo() {
		return codigoNuevoCargo;
	}
	/**
	 * @param codigoNuevoCargo the codigoNuevoCargo to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoNuevoCargo(String codigoNuevoCargo) {
		this.codigoNuevoCargo = codigoNuevoCargo;
	}
	/**
	 * @return the fechaNombramiento
	 */
	public String getFechaNombramiento() {
		return fechaNombramiento;
	}
	/**
	 * @param fechaNombramiento the fechaNombramiento to set
	 * @struts.validator type = "required"
	 */
	public void setFechaNombramiento(String fechaNombramiento) {
		this.fechaNombramiento = fechaNombramiento;
	}
	/**
	 * @return the cargoActualCambiar
	 */
	public String getCargoActualCambiar() {
		return cargoActualCambiar;
	}
	/**
	 * @param cargoActualCambiar the cargoActualCambiar to set
	 * @struts.validator type = "required"
	 */
	public void setCargoActualCambiar(String cargoActualCambiar) {
		this.cargoActualCambiar = cargoActualCambiar;
	}
	/**
	 * @return the codigoCargoNuevo
	 */
	public String getCodigoCargoNuevo() {
		return codigoCargoNuevo;
	}
	/**
	 * @param codigoCargoNuevo the codigoCargoNuevo to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoCargoNuevo(String codigoCargoNuevo) {
		this.codigoCargoNuevo = codigoCargoNuevo;
	}
	/**
	 * @return the codigoConsultora
	 */
	public String getCodigoConsultora() {
		return codigoConsultora;
	}
	/**
	 * @param codigoConsultora the codigoConsultora to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}
	/**
	 * @return the codigoCargoActual
	 */
	public String getCodigoCargoActual() {
		return codigoCargoActual;
	}
	/**
	 * @param codigoCargoActual the codigoCargoActual to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoCargoActual(String codigoCargoActual) {
		this.codigoCargoActual = codigoCargoActual;
	}
	
	//Mantener Licencias
	/**
	 * @return the codigoUnidadAdministrativa
	 */
	public String getCodigoUnidadAdministrativa() {
		return codigoUnidadAdministrativa;
	}
	/**
	 * @param codigoUnidadAdministrativa the codigoUnidadAdministrativa to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoUnidadAdministrativa(String codigoUnidadAdministrativa) {
		this.codigoUnidadAdministrativa = codigoUnidadAdministrativa;
	}
	/**
	 * @return the fechaSalida
	 */
	public String getFechaSalida() {
		return fechaSalida;
	}
	/**
	 * @param fechaSalida the fechaSalida to set
	 * @struts.validator type = "required"
	 */
	public void setFechaSalida(String fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	/**
	 * @return the fechaRegreso
	 */
	public String getFechaRegreso() {
		return fechaRegreso;
	}
	/**
	 * @param fechaRegreso the fechaRegreso to set
	 * @struts.validator type = "required"
	 */
	public void setFechaRegreso(String fechaRegreso) {
		this.fechaRegreso = fechaRegreso;
	}
	/**
	 * @return the nroDocumentoIdentidadReemplazo
	 */
	public String getNroDocumentoIdentidadReemplazo() {
		return nroDocumentoIdentidadReemplazo;
	}
	/**
	 * @param nroDocumentoIdentidadReemplazo the nroDocumentoIdentidadReemplazo to set
	 */
	public void setNroDocumentoIdentidadReemplazo(
			String nroDocumentoIdentidadReemplazo) {
		this.nroDocumentoIdentidadReemplazo = nroDocumentoIdentidadReemplazo;
	}
	/**
	 * @return the codigoCargoReemplazo
	 */
	public String getCodigoCargoReemplazo() {
		return codigoCargoReemplazo;
	}
	/**
	 * @param codigoCargoReemplazo the codigoCargoReemplazo to set
	 */
	public void setCodigoCargoReemplazo(String codigoCargoReemplazo) {
		this.codigoCargoReemplazo = codigoCargoReemplazo;
	}
	/**
	 * @return the unidadesAdministrativasaCargo
	 */
	public String getUnidadesAdministrativasaCargo() {
		return unidadesAdministrativasaCargo;
	}
	/**
	 * @param unidadesAdministrativasaCargo the unidadesAdministrativasaCargo to set
	 */
	public void setUnidadesAdministrativasaCargo(
			String unidadesAdministrativasaCargo) {
		this.unidadesAdministrativasaCargo = unidadesAdministrativasaCargo;
	}
	/**
	 * @return the codigoTipoLicencia
	 */
	public String getCodigoTipoLicencia() {
		return codigoTipoLicencia;
	}
	/**
	 * @param codigoTipoLicencia the codigoTipoLicencia to set
     * @struts.validator type = "required"
	 */
	public void setCodigoTipoLicencia(String codigoTipoLicencia) {
		this.codigoTipoLicencia = codigoTipoLicencia;
	}
	/**
	 * @return the codigoCargoLicencia
	 */
	public String getCodigoCargoLicencia() {
		return codigoCargoLicencia;
	}
	/**
	 * @param codigoCargoLicencia the codigoCargoLicencia to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoCargoLicencia(String codigoCargoLicencia) {
		this.codigoCargoLicencia = codigoCargoLicencia;
	}
	/**
	 * @return the listaUnidadesAdministrativas
	 */
	public String getListaUnidadesAdministrativas() {
		return listaUnidadesAdministrativas;
	}
	/**
	 * @param listaUnidadesAdministrativas the listaUnidadesAdministrativas to set
	 */
	public void setListaUnidadesAdministrativas(String listaUnidadesAdministrativas) {
		this.listaUnidadesAdministrativas = listaUnidadesAdministrativas;
	}
	/**
	 * @return the codigoSubGerencia
	 */
	public String getCodigoSubGerencia() {
		return codigoSubGerencia;
	}
	/**
	 * @param codigoSubGerencia the codigoSubGerencia to set
	 */
	public void setCodigoSubGerencia(String codigoSubGerencia) {
		this.codigoSubGerencia = codigoSubGerencia;
	}
	/**
	 * @return the nombresCompletosConsultoraReemplazo
	 */
	public String getNombresCompletosConsultoraReemplazo() {
		return nombresCompletosConsultoraReemplazo;
	}
	/**
	 * @param nombresCompletosConsultoraReemplazo the nombresCompletosConsultoraReemplazo to set
	 */
	public void setNombresCompletosConsultoraReemplazo(
			String nombresCompletosConsultoraReemplazo) {
		this.nombresCompletosConsultoraReemplazo = nombresCompletosConsultoraReemplazo;
	}
	/**
	 * @return the numeroDocIdentidadBuscar
	 */
	public String getNumeroDocIdentidadBuscar() {
		return numeroDocIdentidadBuscar;
	}
	/**
	 * @param numeroDocIdentidadBuscar the numeroDocIdentidadBuscar to set
	 */
	public void setNumeroDocIdentidadBuscar(String numeroDocIdentidadBuscar) {
		this.numeroDocIdentidadBuscar = numeroDocIdentidadBuscar;
	}
	/**
	 * @return the indicadorTitularNoTitular
	 */
	public String getIndicadorTitularNoTitular() {
		return indicadorTitularNoTitular;
	}
	/**
	 * @param indicadorTitularNoTitular the indicadorTitularNoTitular to set
	 */
	public void setIndicadorTitularNoTitular(String indicadorTitularNoTitular) {
		this.indicadorTitularNoTitular = indicadorTitularNoTitular;
	}
	/**
	 * @return the indicadorEditar
	 */
	public String getIndicadorEditar() {
		return indicadorEditar;
	}
	/**
	 * @param indicadorEditar the indicadorEditar to set
	 */
	public void setIndicadorEditar(String indicadorEditar) {
		this.indicadorEditar = indicadorEditar;
	}
	/**
	 * @return the fechaAsignacion
	 */
	public String getFechaAsignacion() {
		return fechaAsignacion;
	}
	/**
	 * @param fechaAsignacion the fechaAsignacion to set
	 */
	public void setFechaAsignacion(String fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}
	/**
	 * @return the descripcionCargoConsultoraReemplazo
	 */
	public String getDescripcionCargoConsultoraReemplazo() {
		return descripcionCargoConsultoraReemplazo;
	}
	/**
	 * @param descripcionCargoConsultoraReemplazo the descripcionCargoConsultoraReemplazo to set
	 */
	public void setDescripcionCargoConsultoraReemplazo(String descripcionCargoConsultoraReemplazo) {
		this.descripcionCargoConsultoraReemplazo = descripcionCargoConsultoraReemplazo;
	}
	/**
	 * @return the fechaCambioCargo
	 */
	public String getFechaCambioCargo() {
		return fechaCambioCargo;
	}
	/**
	 * @param fechaCambioCargo the fechaCambioCargo to set
	 */
	public void setFechaCambioCargo(String fechaCambioCargo) {
		this.fechaCambioCargo = fechaCambioCargo;
	}
	/**
	 * @return the codigoCargoAnterior
	 */
	public String getCodigoCargoAnterior() {
		return codigoCargoAnterior;
	}
	/**
	 * @param codigoCargoAnterior the codigoCargoAnterior to set
	 */
	public void setCodigoCargoAnterior(String codigoCargoAnterior) {
		this.codigoCargoAnterior = codigoCargoAnterior;
	}
	/**
	 * @return the codigoRegionAnterior
	 */
	public String getCodigoRegionAnterior() {
		return codigoRegionAnterior;
	}
	/**
	 * @param codigoRegionAnterior the codigoRegionAnterior to set
	 */
	public void setCodigoRegionAnterior(String codigoRegionAnterior) {
		this.codigoRegionAnterior = codigoRegionAnterior;
	}
	/**
	 * @return the codigoZonaAnterior
	 */
	public String getCodigoZonaAnterior() {
		return codigoZonaAnterior;
	}
	/**
	 * @param codigoZonaAnterior the codigoZonaAnterior to set
	 */
	public void setCodigoZonaAnterior(String codigoZonaAnterior) {
		this.codigoZonaAnterior = codigoZonaAnterior;
	}
	/**
	 * @return the fechaRotacionActual
	 */
	public String getFechaRotacionActual() {
		return fechaRotacionActual;
	}
	/**
	 * @param fechaRotacionActual the fechaRotacionActual to set
	 */
	public void setFechaRotacionActual(String fechaRotacionActual) {
		this.fechaRotacionActual = fechaRotacionActual;
	}
	/**
	 * @return the fechaRetiroActual
	 */
	public String getFechaRetiroActual() {
		return fechaRetiroActual;
	}
	/**
	 * @param fechaRetiroActual the fechaRetiroActual to set
	 */
	public void setFechaRetiroActual(String fechaRetiroActual) {
		this.fechaRetiroActual = fechaRetiroActual;
	}
	/**
	 * @return the codigoCargoEditar
	 */
	public String getCodigoCargoEditar() {
		return codigoCargoEditar;
	}
	/**
	 * @param codigoCargoEditar the codigoCargoEditar to set
	 */
	public void setCodigoCargoEditar(String codigoCargoEditar) {
		this.codigoCargoEditar = codigoCargoEditar;
	}
	/**
	 * @return the indicadorEliminar
	 */
	public String getIndicadorEliminar() {
		return indicadorEliminar;
	}
	/**
	 * @param indicadorEliminar the indicadorEliminar to set
	 */
	public void setIndicadorEliminar(String indicadorEliminar) {
		this.indicadorEliminar = indicadorEliminar;
	}
	/**
	 * @return the correlativoCabecera
	 */
	public String getCorrelativoCabecera() {
		return correlativoCabecera;
	}
	/**
	 * @param correlativoCabecera the correlativoCabecera to set
	 */
	public void setCorrelativoCabecera(String correlativoCabecera) {
		this.correlativoCabecera = correlativoCabecera;
	}
	/**
	 * @return the codigoCorrrelativoCabeceraReemplazo
	 */
	public String getCodigoCorrrelativoCabeceraReemplazo() {
		return codigoCorrrelativoCabeceraReemplazo;
	}
	/**
	 * @param codigoCorrrelativoCabeceraReemplazo the codigoCorrrelativoCabeceraReemplazo to set
	 */
	public void setCodigoCorrrelativoCabeceraReemplazo(
			String codigoCorrrelativoCabeceraReemplazo) {
		this.codigoCorrrelativoCabeceraReemplazo = codigoCorrrelativoCabeceraReemplazo;
	}
	
	/**
	 * @return the fechaVenta
	 */
	public String getFechaVenta() {
		return fechaVenta;
	}
	
	/**
	 * @param fechaVenta the fechaVenta to set
	 */
	public void setFechaVenta(String fechaVenta) {
		this.fechaVenta = fechaVenta;
	}
	
	/**
	 * @return the fechaFacturacion
	 */
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}
	
	/**
	 * @param fechaFacturacion the fechaFacturacion to set
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}
	
	/**
	 * @return the indicadorAdmin
	 */
	public String getIndicadorAdmin() {
		return indicadorAdmin;
	}
	
	/**
	 * @param indicadorAdmin the indicadorAdmin to set
	 */
	public void setIndicadorAdmin(String indicadorAdmin) {
		this.indicadorAdmin = indicadorAdmin;
	}
	
	/**
	 * @return the indicadorRegistrar
	 */
	public String getIndicadorRegistrar() {
		return indicadorRegistrar;
	}
	
	/**
	 * @param indicadorRegistrar the indicadorRegistrar to set
	 */
	public void setIndicadorRegistrar(String indicadorRegistrar) {
		this.indicadorRegistrar = indicadorRegistrar;
	}
	
	/**
	 * @return the campanyaFacturacion
	 */
	public String getCampanyaFacturacion() {
		return campanyaFacturacion;
	}
	
	/**
	 * @param campanyaFacturacion the campanyaFacturacion to set
	 */
	public void setCampanyaFacturacion(String campanyaFacturacion) {
		this.campanyaFacturacion = campanyaFacturacion;
	}

	/**
	 * @return the campanyaVenta
	 */
	public String getCampanyaVenta() {
		return campanyaVenta;
	}
	
	/**
	 * @param campanyaVenta the campanyaVenta to set
	 */
	public void setCampanyaVenta(String campanyaVenta) {
		this.campanyaVenta = campanyaVenta;
	}

	/**
	 * @return the fechaRefacturacion
	 */
	public String getFechaRefacturacion() {
		return fechaRefacturacion;
	}

	/**
	 * @param fechaRefacturacion the fechaRefacturacion to set
	 */
	public void setFechaRefacturacion(String fechaRefacturacion) {
		this.fechaRefacturacion = fechaRefacturacion;
	}
	
	/**
	 * @return the campanyaFacturacion2
	 */
	public String getCampanyaFacturacion2() {
		return campanyaFacturacion2;
	}
	
	/**
	 * @param campanyaFacturacion2 the campanyaFacturacion2 to set
	 */
	public void setCampanyaFacturacion2(String campanyaFacturacion2) {
		this.campanyaFacturacion2 = campanyaFacturacion2;
	}
	
	/**
	 * @return the campanyaVenta2
	 */
	public String getCampanyaVenta2() {
		return campanyaVenta2;
	}
	
	/**
	 * @param campanyaVenta2 the campanyaVenta2 to set
	 */
	public void setCampanyaVenta2(String campanyaVenta2) {
		this.campanyaVenta2 = campanyaVenta2;
	}

	/**
	 * @return the codigoCorrrelativoCabeceraReemplazos
	 */
	public String[] getCodigoCorrrelativoCabeceraReemplazos() {
		return codigoCorrrelativoCabeceraReemplazos;
	}

	/**
	 * @param codigoCorrrelativoCabeceraReemplazos the codigoCorrrelativoCabeceraReemplazos to set
	 */
	public void setCodigoCorrrelativoCabeceraReemplazos(
			String[] codigoCorrrelativoCabeceraReemplazos) {
		this.codigoCorrrelativoCabeceraReemplazos = codigoCorrrelativoCabeceraReemplazos;
	}
	
	/**
	 * @return the rolDesc
	 */
	public String getRolDesc() {
		return rolDesc;
	}
	
	/**
	 * @param rolDesc the rolDesc to set
	 */
	public void setRolDesc(String rolDesc) {
		this.rolDesc = rolDesc;
	}
	
	/**
	 * @return the perfilDesc
	 */
	public String getPerfilDesc() {
		return perfilDesc;
	}
	
	/**
	 * @param perfilDesc the perfilDesc to set
	 */
	public void setPerfilDesc(String perfilDesc) {
		this.perfilDesc = perfilDesc;
	}
	
	/**
	 * @return the codigoRegionLic
	 */
	public String getCodigoRegionLic() {
		return codigoRegionLic;
	}
	
	/**
	 * @param codigoRegionLic the codigoRegionLic to set
	 */
	public void setCodigoRegionLic(String codigoRegionLic) {
		this.codigoRegionLic = codigoRegionLic;
	}
	
	/**
	 * @return the codigoZonaLic
	 */
	public String getCodigoZonaLic() {
		return codigoZonaLic;
	}
	
	/**
	 * @param codigoZonaLic the codigoZonaLic to set
	 */
	public void setCodigoZonaLic(String codigoZonaLic) {
		this.codigoZonaLic = codigoZonaLic;
	}
	public Date getFechaIngresoDate() {
		return fechaIngresoDate;
	}
	public void setFechaIngresoDate(Date fechaIngresoDate) {
		this.fechaIngresoDate = fechaIngresoDate;
	}
	public Date getFechaIngresoHastaDate() {
		return fechaIngresoHastaDate;
	}
	public void setFechaIngresoHastaDate(Date fechaIngresoHastaDate) {
		this.fechaIngresoHastaDate = fechaIngresoHastaDate;
	}
	public Date getFechaNombramientoDate() {
		return fechaNombramientoDate;
	}
	public void setFechaNombramientoDate(Date fechaNombramientoDate) {
		this.fechaNombramientoDate = fechaNombramientoDate;
	}
//	public Date getFechaRotacionDate() {
//		return fechaRotacionDate;
//	}
//	public void setFechaRotacionDate(Date fechaRotacionDate) {
//		this.fechaRotacionDate = fechaRotacionDate;
//	}
	/**
	 * @return the codigoRegionUnico
	 */
	public String getCodigoRegionUnico() {
		return codigoRegionUnico;
	}
	/**
	 * @param codigoRegionUnico the codigoRegionUnico to set
	 */
	public void setCodigoRegionUnico(String codigoRegionUnico) {
		this.codigoRegionUnico = codigoRegionUnico;
	}
	public String getCodigoZonaUnico() {
		return codigoZonaUnico;
	}
	public void setCodigoZonaUnico(String codigoZonaUnico) {
		this.codigoZonaUnico = codigoZonaUnico;
	}
	public Date getFechaRegistroDate() {
		return fechaRegistroDate;
	}
	public void setFechaRegistroDate(Date fechaRegistroDate) {
		this.fechaRegistroDate = fechaRegistroDate;
	}
	public Date getFechaSalidaDate() {
		return fechaSalidaDate;
	}
	public void setFechaSalidaDate(Date fechaSalidaDate) {
		this.fechaSalidaDate = fechaSalidaDate;
	}
	public Date getFechaRegresoDate() {
		return fechaRegresoDate;
	}
	public void setFechaRegresoDate(Date fechaRegresoDate) {
		this.fechaRegresoDate = fechaRegresoDate;
	}
	public Date getFechaRetiroDate() {
		return fechaRetiroDate;
	}
	public void setFechaRetiroDate(Date fechaRetiroDate) {
		this.fechaRetiroDate = fechaRetiroDate;
	}
	
	
}