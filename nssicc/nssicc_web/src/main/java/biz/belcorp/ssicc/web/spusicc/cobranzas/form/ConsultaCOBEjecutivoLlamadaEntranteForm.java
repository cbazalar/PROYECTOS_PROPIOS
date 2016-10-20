package biz.belcorp.ssicc.web.spusicc.cobranzas.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class ConsultaCOBEjecutivoLlamadaEntranteForm extends BaseSearchForm implements
		Serializable {

	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoPeriodo;
	private String codigoSociedad;	
	private String codigoEtapa;
	private String codigoCobrador;
	private String departamento;
	private String provincia;
	private String distrito;
	private String codigoRegion;
	private String codigoZona;
	private String codigoCartera;
	private String codigoEtapaConsultora;
	private String codigoConsultora;
	private String digitoControl;
	private String nombreConsultora;
	private String numeroDocumentoIdentidad;
	private String tiempoMora;
	private String campanhaAsignacion;
	private String fechaAsignacion;
	private String fechaCierre;
	private String deudaTotal;
	private String deudaCartera;	
	private String tipoAccion;
	private String accionCobranza;
    private String observaciones;
	private Date fechaPago;
	private String importePago;
	private String regionTab;
	private String zonaTab;
	private String seccionTab;
    private String gerenteZonaTab;
	private String liderSeccionTab;
	private String territorioTab;
	private String campanaIngresoTab;
	private String direccionTab;
	private String referenciaTab;
	private String fechaNacimientoTab;
	private String edadTab;
	private String ocupacionTab;
	private String fechaIngresoTab;
	private String statusTab;
	private String ultimoPedidoTab;
	private String[] telefonosTab;	
	private String referencias;
	private String nuevaDireccionTab;
	private String nuevoTelefonoFijoTab;
	private String nuevoTelefonoTrabajoTab;
	private String nuevoTelefonoMovilTab;
	
	private String telefonoFijo;
	private String telefonoMovil;
	private String telefonoTrabajo;
	private String nuevoTelefono;		
	private String tipoTelefono;
	
	private String  tabSeleccionCurso;
	
	private String codigoConsultoraSearch;
	private String numeroDocumentoSearch;	


	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the codigoSociedad
	 */
	public String getCodigoSociedad() {
		return codigoSociedad;
	}

	/**
	 * @param codigoSociedad the codigoSociedad to set
	 */
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}

	/**
	 * @return the codigoEtapa
	 */
	public String getCodigoEtapa() {
		return codigoEtapa;
	}

	/**
	 * @param codigoEtapa the codigoEtapa to set
	 */
	public void setCodigoEtapa(String codigoEtapa) {
		this.codigoEtapa = codigoEtapa;
	}

	/**
	 * @return the codigoCobrador
	 */
	public String getCodigoCobrador() {
		return codigoCobrador;
	}

	/**
	 * @param codigoCobrador the codigoCobrador to set
	 */
	public void setCodigoCobrador(String codigoCobrador) {
		this.codigoCobrador = codigoCobrador;
	}

	/**
	 * @return the departamento
	 */
	public String getDepartamento() {
		return departamento;
	}

	/**
	 * @param departamento the departamento to set
	 */
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	/**
	 * @return the provincia
	 */
	public String getProvincia() {
		return provincia;
	}

	/**
	 * @param provincia the provincia to set
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	/**
	 * @return the distrito
	 */
	public String getDistrito() {
		return distrito;
	}

	/**
	 * @param distrito the distrito to set
	 */
	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	/**
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	/**
	 * @return the codigoCartera
	 */
	public String getCodigoCartera() {
		return codigoCartera;
	}

	/**
	 * @param codigoCartera the codigoCartera to set
	 */
	public void setCodigoCartera(String codigoCartera) {
		this.codigoCartera = codigoCartera;
	}

	/**
	 * @return the codigoEtapaConsultora
	 */
	public String getCodigoEtapaConsultora() {
		return codigoEtapaConsultora;
	}

	/**
	 * @param codigoEtapaConsultora the codigoEtapaConsultora to set
	 */
	public void setCodigoEtapaConsultora(String codigoEtapaConsultora) {
		this.codigoEtapaConsultora = codigoEtapaConsultora;
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
	 * @return the digitoControl
	 */
	public String getDigitoControl() {
		return digitoControl;
	}

	/**
	 * @param digitoControl the digitoControl to set
	 */
	public void setDigitoControl(String digitoControl) {
		this.digitoControl = digitoControl;
	}

	/**
	 * @return the nombreConsultora
	 */
	public String getNombreConsultora() {
		return nombreConsultora;
	}

	/**
	 * @param nombreConsultora the nombreConsultora to set
	 */
	public void setNombreConsultora(String nombreConsultora) {
		this.nombreConsultora = nombreConsultora;
	}

	/**
	 * @return the numeroDocumentoIdentidad
	 */
	public String getNumeroDocumentoIdentidad() {
		return numeroDocumentoIdentidad;
	}

	/**
	 * @param numeroDocumentoIdentidad the numeroDocumentoIdentidad to set
	 */
	public void setNumeroDocumentoIdentidad(String numeroDocumentoIdentidad) {
		this.numeroDocumentoIdentidad = numeroDocumentoIdentidad;
	}

	/**
	 * @return the tiempoMora
	 */
	public String getTiempoMora() {
		return tiempoMora;
	}

	/**
	 * @param tiempoMora the tiempoMora to set
	 */
	public void setTiempoMora(String tiempoMora) {
		this.tiempoMora = tiempoMora;
	}

	/**
	 * @return the campanhaAsignacion
	 */
	public String getCampanhaAsignacion() {
		return campanhaAsignacion;
	}

	/**
	 * @param campanhaAsignacion the campanhaAsignacion to set
	 */
	public void setCampanhaAsignacion(String campanhaAsignacion) {
		this.campanhaAsignacion = campanhaAsignacion;
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
	 * @return the fechaCierre
	 */
	public String getFechaCierre() {
		return fechaCierre;
	}

	/**
	 * @param fechaCierre the fechaCierre to set
	 */
	public void setFechaCierre(String fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	/**
	 * @return the deudaTotal
	 */
	public String getDeudaTotal() {
		return deudaTotal;
	}

	/**
	 * @param deudaTotal the deudaTotal to set
	 */
	public void setDeudaTotal(String deudaTotal) {
		this.deudaTotal = deudaTotal;
	}

	/**
	 * @return the deudaCartera
	 */
	public String getDeudaCartera() {
		return deudaCartera;
	}

	/**
	 * @param deudaCartera the deudaCartera to set
	 */
	public void setDeudaCartera(String deudaCartera) {
		this.deudaCartera = deudaCartera;
	}

	/**
	 * @return the tipoAccion
	 */
	public String getTipoAccion() {
		return tipoAccion;
	}

	/**
	 * @param tipoAccion the tipoAccion to set
	 */
	public void setTipoAccion(String tipoAccion) {
		this.tipoAccion = tipoAccion;
	}

	/**
	 * @return the accionCobranza
	 */
	public String getAccionCobranza() {
		return accionCobranza;
	}

	/**
	 * @param accionCobranza the accionCobranza to set
	 */
	public void setAccionCobranza(String accionCobranza) {
		this.accionCobranza = accionCobranza;
	}

	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return the fechaPago
	 */
	public Date getFechaPago() {
		return fechaPago;
	}

	/**
	 * @param fechaPago the fechaPago to set
	 */
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	/**
	 * @return the importePago
	 */
	public String getImportePago() {
		return importePago;
	}

	/**
	 * @param importePago the importePago to set
	 */
	public void setImportePago(String importePago) {
		this.importePago = importePago;
	}

	/**
	 * @return the regionTab
	 */
	public String getRegionTab() {
		return regionTab;
	}

	/**
	 * @param regionTab the regionTab to set
	 */
	public void setRegionTab(String regionTab) {
		this.regionTab = regionTab;
	}

	/**
	 * @return the zonaTab
	 */
	public String getZonaTab() {
		return zonaTab;
	}

	/**
	 * @param zonaTab the zonaTab to set
	 */
	public void setZonaTab(String zonaTab) {
		this.zonaTab = zonaTab;
	}

	/**
	 * @return the seccionTab
	 */
	public String getSeccionTab() {
		return seccionTab;
	}

	/**
	 * @param seccionTab the seccionTab to set
	 */
	public void setSeccionTab(String seccionTab) {
		this.seccionTab = seccionTab;
	}

	/**
	 * @return the gerenteZonaTab
	 */
	public String getGerenteZonaTab() {
		return gerenteZonaTab;
	}

	/**
	 * @param gerenteZonaTab the gerenteZonaTab to set
	 */
	public void setGerenteZonaTab(String gerenteZonaTab) {
		this.gerenteZonaTab = gerenteZonaTab;
	}

	/**
	 * @return the liderSeccionTab
	 */
	public String getLiderSeccionTab() {
		return liderSeccionTab;
	}

	/**
	 * @param liderSeccionTab the liderSeccionTab to set
	 */
	public void setLiderSeccionTab(String liderSeccionTab) {
		this.liderSeccionTab = liderSeccionTab;
	}

	/**
	 * @return the territorioTab
	 */
	public String getTerritorioTab() {
		return territorioTab;
	}

	/**
	 * @param territorioTab the territorioTab to set
	 */
	public void setTerritorioTab(String territorioTab) {
		this.territorioTab = territorioTab;
	}

	/**
	 * @return the campanaIngresoTab
	 */
	public String getCampanaIngresoTab() {
		return campanaIngresoTab;
	}

	/**
	 * @param campanaIngresoTab the campanaIngresoTab to set
	 */
	public void setCampanaIngresoTab(String campanaIngresoTab) {
		this.campanaIngresoTab = campanaIngresoTab;
	}

	/**
	 * @return the direccionTab
	 */
	public String getDireccionTab() {
		return direccionTab;
	}

	/**
	 * @param direccionTab the direccionTab to set
	 */
	public void setDireccionTab(String direccionTab) {
		this.direccionTab = direccionTab;
	}

	/**
	 * @return the referenciaTab
	 */
	public String getReferenciaTab() {
		return referenciaTab;
	}

	/**
	 * @param referenciaTab the referenciaTab to set
	 */
	public void setReferenciaTab(String referenciaTab) {
		this.referenciaTab = referenciaTab;
	}

	/**
	 * @return the fechaNacimientoTab
	 */
	public String getFechaNacimientoTab() {
		return fechaNacimientoTab;
	}

	/**
	 * @param fechaNacimientoTab the fechaNacimientoTab to set
	 */
	public void setFechaNacimientoTab(String fechaNacimientoTab) {
		this.fechaNacimientoTab = fechaNacimientoTab;
	}

	/**
	 * @return the edadTab
	 */
	public String getEdadTab() {
		return edadTab;
	}

	/**
	 * @param edadTab the edadTab to set
	 */
	public void setEdadTab(String edadTab) {
		this.edadTab = edadTab;
	}

	/**
	 * @return the ocupacionTab
	 */
	public String getOcupacionTab() {
		return ocupacionTab;
	}

	/**
	 * @param ocupacionTab the ocupacionTab to set
	 */
	public void setOcupacionTab(String ocupacionTab) {
		this.ocupacionTab = ocupacionTab;
	}

	/**
	 * @return the fechaIngresoTab
	 */
	public String getFechaIngresoTab() {
		return fechaIngresoTab;
	}

	/**
	 * @param fechaIngresoTab the fechaIngresoTab to set
	 */
	public void setFechaIngresoTab(String fechaIngresoTab) {
		this.fechaIngresoTab = fechaIngresoTab;
	}

	/**
	 * @return the statusTab
	 */
	public String getStatusTab() {
		return statusTab;
	}

	/**
	 * @param statusTab the statusTab to set
	 */
	public void setStatusTab(String statusTab) {
		this.statusTab = statusTab;
	}

	/**
	 * @return the ultimoPedidoTab
	 */
	public String getUltimoPedidoTab() {
		return ultimoPedidoTab;
	}

	/**
	 * @param ultimoPedidoTab the ultimoPedidoTab to set
	 */
	public void setUltimoPedidoTab(String ultimoPedidoTab) {
		this.ultimoPedidoTab = ultimoPedidoTab;
	}

	/**
	 * @return the telefonosTab
	 */
	public String[] getTelefonosTab() {
		return telefonosTab;
	}

	/**
	 * @param telefonosTab the telefonosTab to set
	 */
	public void setTelefonosTab(String[] telefonosTab) {
		this.telefonosTab = telefonosTab;
	}

	/**
	 * @return the referencias
	 */
	public String getReferencias() {
		return referencias;
	}

	/**
	 * @param referencias the referencias to set
	 */
	public void setReferencias(String referencias) {
		this.referencias = referencias;
	}

	/**
	 * @return the nuevaDireccionTab
	 */
	public String getNuevaDireccionTab() {
		return nuevaDireccionTab;
	}

	/**
	 * @param nuevaDireccionTab the nuevaDireccionTab to set
	 */
	public void setNuevaDireccionTab(String nuevaDireccionTab) {
		this.nuevaDireccionTab = nuevaDireccionTab;
	}

	/**
	 * @return the telefonoFijo
	 */
	public String getTelefonoFijo() {
		return telefonoFijo;
	}

	/**
	 * @param telefonoFijo the telefonoFijo to set
	 */
	public void setTelefonoFijo(String telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}

	/**
	 * @return the telefonoMovil
	 */
	public String getTelefonoMovil() {
		return telefonoMovil;
	}

	/**
	 * @param telefonoMovil the telefonoMovil to set
	 */
	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}

	/**
	 * @return the telefonoTrabajo
	 */
	public String getTelefonoTrabajo() {
		return telefonoTrabajo;
	}

	/**
	 * @param telefonoTrabajo the telefonoTrabajo to set
	 */
	public void setTelefonoTrabajo(String telefonoTrabajo) {
		this.telefonoTrabajo = telefonoTrabajo;
	}

	/**
	 * @return the nuevoTelefono
	 */
	public String getNuevoTelefono() {
		return nuevoTelefono;
	}

	/**
	 * @param nuevoTelefono the nuevoTelefono to set
	 */
	public void setNuevoTelefono(String nuevoTelefono) {
		this.nuevoTelefono = nuevoTelefono;
	}

	/**
	 * @return the tipoTelefono
	 */
	public String getTipoTelefono() {
		return tipoTelefono;
	}

	/**
	 * @param tipoTelefono the tipoTelefono to set
	 */
	public void setTipoTelefono(String tipoTelefono) {
		this.tipoTelefono = tipoTelefono;
	}

	/**
	 * @return the tabSeleccionCurso
	 */
	public String getTabSeleccionCurso() {
		return tabSeleccionCurso;
	}

	/**
	 * @param tabSeleccionCurso the tabSeleccionCurso to set
	 */
	public void setTabSeleccionCurso(String tabSeleccionCurso) {
		this.tabSeleccionCurso = tabSeleccionCurso;
	}

	/**
	 * @return the codigoConsultoraSearch
	 */
	public String getCodigoConsultoraSearch() {
		return codigoConsultoraSearch;
	}

	/**
	 * @param codigoConsultoraSearch the codigoConsultoraSearch to set
	 */
	public void setCodigoConsultoraSearch(String codigoConsultoraSearch) {
		this.codigoConsultoraSearch = codigoConsultoraSearch;
	}

	/**
	 * @return the numeroDocumentoSearch
	 */
	public String getNumeroDocumentoSearch() {
		return numeroDocumentoSearch;
	}

	/**
	 * @param numeroDocumentoSearch the numeroDocumentoSearch to set
	 */
	public void setNumeroDocumentoSearch(String numeroDocumentoSearch) {
		this.numeroDocumentoSearch = numeroDocumentoSearch;
	}

	/**
	 * @return the nuevoTelefonoFijoTab
	 */
	public String getNuevoTelefonoFijoTab() {
		return nuevoTelefonoFijoTab;
	}

	/**
	 * @param nuevoTelefonoFijoTab the nuevoTelefonoFijoTab to set
	 */
	public void setNuevoTelefonoFijoTab(String nuevoTelefonoFijoTab) {
		this.nuevoTelefonoFijoTab = nuevoTelefonoFijoTab;
	}

	/**
	 * @return the nuevoTelefonoTrabajoTab
	 */
	public String getNuevoTelefonoTrabajoTab() {
		return nuevoTelefonoTrabajoTab;
	}

	/**
	 * @param nuevoTelefonoTrabajoTab the nuevoTelefonoTrabajoTab to set
	 */
	public void setNuevoTelefonoTrabajoTab(String nuevoTelefonoTrabajoTab) {
		this.nuevoTelefonoTrabajoTab = nuevoTelefonoTrabajoTab;
	}

	/**
	 * @return the nuevoTelefonoMovilTab
	 */
	public String getNuevoTelefonoMovilTab() {
		return nuevoTelefonoMovilTab;
	}

	/**
	 * @param nuevoTelefonoMovilTab the nuevoTelefonoMovilTab to set
	 */
	public void setNuevoTelefonoMovilTab(String nuevoTelefonoMovilTab) {
		this.nuevoTelefonoMovilTab = nuevoTelefonoMovilTab;
	}


}
