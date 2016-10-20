package biz.belcorp.ssicc.web.spusicc.mae.form;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * 
 * <p>
 * <a href="MantenimientoMAEInformacionClienteForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 *                      
 */
public class MantenimientoMAEInformacionClienteForm extends BaseSearchForm  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoClienteBuscar;
	
	private String codigoCliente;
	private String nombreCliente;
	private String oidCliente;

	private String oidNivelRiesgoActual;
	private String codigoNivelRiesgoActual;
	private String descripcionNivelRiesgoActual;

	private String fechaUltimaActualizacion;
	private Date fechaUltimaActualizacionDate;
	
	private String nuevoNivelRiesgo;
	private String oidNuevoNivelRiesgo;
	private String codigoNuevoNivelRiesgo;
	
	private String oidPeriodoNivelRiezgo;
	private String oidEstatus;

	private boolean clienteEncontrado;
	
	private String indicadorActividadActual;
	private String indicadorActividadNuevo;
	private String tabSeleccion;
	
	private List listaSubTipoClienteActual;
	private String indicadorPrincipalSubTipo;
	private String subTipoCliente;
	
	private boolean cambioSubTipoCliente;
	private boolean tieneSubTipoClienteGerente;
	private boolean tieneSubTipoClienteConsultora;
	private String oidPais;
	
	/* INI SA PER-SiCC-2012-0367 */
	private String numeroDocumentoIdentidad;
	private boolean validarEstatusComercial;
	private String mensajeConsultoraRoja;
	/* FIN SA PER-SiCC-2012-0367 */

	private String indicadorSolicitudCredito;
	private boolean graboOK;
	private String valorId;
	
	private Integer numeroPeriodosSinPedido;
	
	/**
	 * @return Returns the codigoCliente.
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}
	
	/**
	 * @param codigoCliente The codigoCliente to set.
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	
	/**
	 * @return Returns the codigoNivelRiesgoActual.
	 */
	public String getCodigoNivelRiesgoActual() {
		return codigoNivelRiesgoActual;
	}
	
	/**
	 * @param codigoNivelRiesgoActual The codigoNivelRiesgoActual to set.
	 */
	public void setCodigoNivelRiesgoActual(String codigoNivelRiesgoActual) {
		this.codigoNivelRiesgoActual = codigoNivelRiesgoActual;
	}
	
	/**
	 * @return Returns the codigoNuevoNivelRiesgo.
	 */
	public String getCodigoNuevoNivelRiesgo() {
		return codigoNuevoNivelRiesgo;
	}
	
	/**
	 * @param codigoNuevoNivelRiesgo The codigoNuevoNivelRiesgo to set.
	 */
	public void setCodigoNuevoNivelRiesgo(String codigoNuevoNivelRiesgo) {
		this.codigoNuevoNivelRiesgo = codigoNuevoNivelRiesgo;
	}
	
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	
	/**
	 * @param codigoPais The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	/**
	 * @return Returns the descripcionNivelRiesgoActual.
	 */
	public String getDescripcionNivelRiesgoActual() {
		return descripcionNivelRiesgoActual;
	}
	
	/**
	 * @param descripcionNivelRiesgoActual The descripcionNivelRiesgoActual to set.
	 */
	public void setDescripcionNivelRiesgoActual(String descripcionNivelRiesgoActual) {
		this.descripcionNivelRiesgoActual = descripcionNivelRiesgoActual;
	}
	
	/**
	 * @return Returns the fechaUltimaActualizacion.
	 */
	public String getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}
	
	/**
	 * @param fechaUltimaActualizacion The fechaUltimaActualizacion to set.
	 */
	public void setFechaUltimaActualizacion(String fechaUltimaActualizacion) {
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
	}
	
	/**
	 * @return Returns the nuevoNivelRiesgo.
	 */
	public String getNuevoNivelRiesgo() {
		return nuevoNivelRiesgo;
	}
	
	/**
	 * @param nuevoNivelRiesgo The nuevoNivelRiesgo to set.
	 */
	public void setNuevoNivelRiesgo(String nuevoNivelRiesgo) {
		this.nuevoNivelRiesgo = nuevoNivelRiesgo;
	}
	
	/**
	 * @return Returns the oidCliente.
	 */
	public String getOidCliente() {
		return oidCliente;
	}
	
	/**
	 * @param oidCliente The oidCliente to set.
	 */
	public void setOidCliente(String oidCliente) {
		this.oidCliente = oidCliente;
	}
	
	/**
	 * @return Returns the oidNivelRiesgoActual.
	 */
	public String getOidNivelRiesgoActual() {
		return oidNivelRiesgoActual;
	}
	/**
	 * @param oidNivelRiesgoActual The oidNivelRiesgoActual to set.
	 */
	public void setOidNivelRiesgoActual(String oidNivelRiesgoActual) {
		this.oidNivelRiesgoActual = oidNivelRiesgoActual;
	}
	
	/**
	 * @return Returns the oidNuevoNivelRiesgo.
	 */
	public String getOidNuevoNivelRiesgo() {
		return oidNuevoNivelRiesgo;
	}
	
	/**
	 * @param oidNuevoNivelRiesgo The oidNuevoNivelRiesgo to set.
	 */
	public void setOidNuevoNivelRiesgo(String oidNuevoNivelRiesgo) {
		this.oidNuevoNivelRiesgo = oidNuevoNivelRiesgo;
	}

	/**
	 * @return Returns the clienteEncontrado.
	 */
	public boolean isClienteEncontrado() {
		return clienteEncontrado;
	}

	/**
	 * @param clienteEncontrado The clienteEncontrado to set.
	 */
	public void setClienteEncontrado(boolean clienteEncontrado) {
		this.clienteEncontrado = clienteEncontrado;
	}

	/**
	 * @return Returns the codigoClienteBuscar.
	 */
	public String getCodigoClienteBuscar() {
		return codigoClienteBuscar;
	}

	/**
	 * @param codigoClienteBuscar The codigoClienteBuscar to set.
	 */
	public void setCodigoClienteBuscar(String codigoClienteBuscar) {
		this.codigoClienteBuscar = codigoClienteBuscar;
	}

	/**
	 * @return Returns the nombreCliente.
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}

	/**
	 * @param nombreCliente The nombreCliente to set.
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	
	/**
	 * @return the oidPeriodoNivelRiezgo
	 */
	public String getOidPeriodoNivelRiezgo() {
		return oidPeriodoNivelRiezgo;
	}

	/**
	 * @param oidPeriodoNivelRiezgo the oidPeriodoNivelRiezgo to set
	 */
	public void setOidPeriodoNivelRiezgo(String oidPeriodoNivelRiezgo) {
		this.oidPeriodoNivelRiezgo = oidPeriodoNivelRiezgo;
	}

	/**
	 * @return the indicadorActividadActual
	 */
	public String getIndicadorActividadActual() {
		return indicadorActividadActual;
	}

	/**
	 * @param indicadorActividadActual the indicadorActividadActual to set
	 */
	public void setIndicadorActividadActual(String indicadorActividadActual) {
		this.indicadorActividadActual = indicadorActividadActual;
	}

	/**
	 * @return the indicadorActividadNuevo
	 */
	public String getIndicadorActividadNuevo() {
		return indicadorActividadNuevo;
	}

	/**
	 * @param indicadorActividadNuevo the indicadorActividadNuevo to set
	 */
	public void setIndicadorActividadNuevo(String indicadorActividadNuevo) {
		this.indicadorActividadNuevo = indicadorActividadNuevo;
	}

	/**
	 * @return the tabSeleccion
	 */
	public String getTabSeleccion() {
		return tabSeleccion;
	}

	/**
	 * @param tabSeleccion the tabSeleccion to set
	 */
	public void setTabSeleccion(String tabSeleccion) {
		this.tabSeleccion = tabSeleccion;
	}

	/**
	 * @return the listaSubTipoClienteActual
	 */
	public List getListaSubTipoClienteActual() {
		return listaSubTipoClienteActual;
	}

	/**
	 * @param listaSubTipoClienteActual the listaSubTipoClienteActual to set
	 */
	public void setListaSubTipoClienteActual(List listaSubTipoClienteActual) {
		this.listaSubTipoClienteActual = listaSubTipoClienteActual;
	}

	/**
	 * @return the indicadorPrincipalSubTipo
	 */
	public String getIndicadorPrincipalSubTipo() {
		return indicadorPrincipalSubTipo;
	}

	/**
	 * @param indicadorPrincipalSubTipo the indicadorPrincipalSubTipo to set
	 */
	public void setIndicadorPrincipalSubTipo(String indicadorPrincipalSubTipo) {
		this.indicadorPrincipalSubTipo = indicadorPrincipalSubTipo;
	}

	/**
	 * @return the subTipoCliente
	 */
	public String getSubTipoCliente() {
		return subTipoCliente;
	}

	/**
	 * @param subTipoCliente the subTipoCliente to set
	 */
	public void setSubTipoCliente(String subTipoCliente) {
		this.subTipoCliente = subTipoCliente;
	}

	/**
	 * @return the cambioSubTipoCliente
	 */
	public boolean isCambioSubTipoCliente() {
		return cambioSubTipoCliente;
	}

	/**
	 * @param cambioSubTipoCliente the cambioSubTipoCliente to set
	 */
	public void setCambioSubTipoCliente(boolean cambioSubTipoCliente) {
		this.cambioSubTipoCliente = cambioSubTipoCliente;
	}

	/**
	 * @return the tieneSubTipoClienteGerente
	 */
	public boolean isTieneSubTipoClienteGerente() {
		return tieneSubTipoClienteGerente;
	}

	/**
	 * @param tieneSubTipoClienteGerente the tieneSubTipoClienteGerente to set
	 */
	public void setTieneSubTipoClienteGerente(boolean tieneSubTipoClienteGerente) {
		this.tieneSubTipoClienteGerente = tieneSubTipoClienteGerente;
	}

	/**
	 * @return the oidPais
	 */
	public String getOidPais() {
		return oidPais;
	}

	/**
	 * @param oidPais the oidPais to set
	 */
	public void setOidPais(String oidPais) {
		this.oidPais = oidPais;
	}

	/**
	 * @return the tieneSubTipoClienteConsultora
	 */
	public boolean isTieneSubTipoClienteConsultora() {
		return tieneSubTipoClienteConsultora;
	}

	/**
	 * @param tieneSubTipoClienteConsultora the tieneSubTipoClienteConsultora to set
	 */
	public void setTieneSubTipoClienteConsultora(
			boolean tieneSubTipoClienteConsultora) {
		this.tieneSubTipoClienteConsultora = tieneSubTipoClienteConsultora;
	}

	/**
	 * @return the oidEstatus
	 */
	public String getOidEstatus() {
		return oidEstatus;
	}

	/**
	 * @param oidEstatus the oidEstatus to set
	 */
	public void setOidEstatus(String oidEstatus) {
		this.oidEstatus = oidEstatus;
	}

	/**
	 * @return the validarEstatusComercial
	 */
	public boolean isValidarEstatusComercial() {
		return validarEstatusComercial;
	}

	/**
	 * @param validarEstatusComercial the validarEstatusComercial to set
	 */
	public void setValidarEstatusComercial(boolean validarEstatusComercial) {
		this.validarEstatusComercial = validarEstatusComercial;
	}

	/**
	 * @return the mensajeConsultoraRoja
	 */
	public String getMensajeConsultoraRoja() {
		return mensajeConsultoraRoja;
	}

	/**
	 * @param mensajeConsultoraRoja the mensajeConsultoraRoja to set
	 */
	public void setMensajeConsultoraRoja(String mensajeConsultoraRoja) {
		this.mensajeConsultoraRoja = mensajeConsultoraRoja;
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
	 * @return the indicadorSolicitudCredito
	 */
	public String getIndicadorSolicitudCredito() {
		return indicadorSolicitudCredito;
	}

	/**
	 * @param indicadorSolicitudCredito the indicadorSolicitudCredito to set
	 */
	public void setIndicadorSolicitudCredito(String indicadorSolicitudCredito) {
		this.indicadorSolicitudCredito = indicadorSolicitudCredito;
	}

	/**
	 * @return the graboOK
	 */
	public boolean isGraboOK() {
		return graboOK;
	}

	/**
	 * @param graboOK the graboOK to set
	 */
	public void setGraboOK(boolean graboOK) {
		this.graboOK = graboOK;
	}

	/**
	 * @return the valorId
	 */
	public String getValorId() {
		return valorId;
	}

	/**
	 * @param valorId the valorId to set
	 */
	public void setValorId(String valorId) {
		this.valorId = valorId;
	}

	/**
	 * @return the numeroPeriodosSinPedido
	 */
	public Integer getNumeroPeriodosSinPedido() {
		return numeroPeriodosSinPedido;
	}

	/**
	 * @param numeroPeriodosSinPedido the numeroPeriodosSinPedido to set
	 */
	public void setNumeroPeriodosSinPedido(Integer numeroPeriodosSinPedido) {
		this.numeroPeriodosSinPedido = numeroPeriodosSinPedido;
	}

	public Date getFechaUltimaActualizacionDate() {
		return fechaUltimaActualizacionDate;
	}

	public void setFechaUltimaActualizacionDate(Date fechaUltimaActualizacionDate) {
		this.fechaUltimaActualizacionDate = fechaUltimaActualizacionDate;
	}


}
