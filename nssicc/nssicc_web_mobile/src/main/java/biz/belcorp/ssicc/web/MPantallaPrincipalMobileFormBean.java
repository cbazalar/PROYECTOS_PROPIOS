package biz.belcorp.ssicc.web;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MPantallaPrincipalMobileFormBean extends BaseSearchForm{
	
private static final long serialVersionUID = 2691735620962468783L;
	
	private String codigoClienteBuscar;
	private String numeroDocIdentidadBuscar;
	
	private String oidPais;
	private String codigoPais;
	private String codigoMarca;
	private String codigoCanal;
	private String oidIdioma;

	private String codigoCliente;
	private String oidCliente;
	private String tipoDocIdentidad;
	private String numeroDocIdentidad;
	private String nombreCompleto;
	
	private String estatus;
	private String telefono;
	private String email;
	private String fechaNacimiento;
	
	private String fechaIngreso;
	private String periodoIngreso;
	private String bloqueo;
	
	private String direccionDespacho;
	private String barrioDespacho;
	private String ubigeoDespacho;
	
	private String direccionDomicilio;
	private String barrioDomicilio;
	private String ubigeoDomicilio;
	
	private String periodoUltimoPedido;
	private String boletaUltimoPedido;
	private String importeUltimoPedido;
	private String saldoPeriodoUltimoPedido;
	private String saldoUnicoUltimoPedido;
	
	private String saldoPagarUltimoPedido;
	private String pagosPosterioresUltimoPedido;
	
	private String propiedadAux;
	private String opcionConsulta;
	
	private boolean mostrarBarrio;
	
	private String impedidaPasarCdr;
	
	private String impedidaPasarPedido;
	
	private String codigoDigitoControl;
	
	private String promedioVentas;
	private String indicadorActivo;
	private String longitudCodigoCliente;
	
	private boolean mostrarPantallaBusqueda;
	
	private String fechaCastigada; 
	private String nivelRiesgo;
	
	private String limiteCredito;
	
	private String estadoSolicitudPoliza;
	private String fechaInicioCobertura;
	private String fechaFinCobertura;
	
	private boolean mostrarReporteImagenesSC;
	
	/* INI SA PER-SiCC-2012-0545 */
	private String ciudadDespacho;
	private String villaPoblacionDespacho;
	private String ciudadDomicilio;
	private String villaPoblacionDomicilio;
	
	private boolean mostrarCiudad;
	private boolean mostrarVillaPoblacion;
	/* FIN SA PER-SiCC-2012-0545 */
	
	private String gerenteZona;
	private String celularGerenteZona;
	private String liderSeccion;
	private String celularLiderSeccion;
	
	private String tipoConsultora;
	private String numeroContrato;
	
	
	private String codigoBuzon;
	private String direccionBuzon;			      
	private String telefonoBuzon;		      
	private String celularBuzon;
	private String descripcionBuzon;
	
	/**
	 * @return the codigoDigitoControl
	 */
	public String getCodigoDigitoControl() {
		return codigoDigitoControl;
	}

	/**
	 * @param codigoDigitoControl the codigoDigitoControl to set
	 */
	public void setCodigoDigitoControl(String codigoDigitoControl) {
		this.codigoDigitoControl = codigoDigitoControl;
	}
	
	public String getImpedidaPasarCdr() {
		return impedidaPasarCdr;
	}
	public void setImpedidaPasarCdr(String impedidaPasarCdr) {
		this.impedidaPasarCdr = impedidaPasarCdr;
	}
	public String getImpedidaPasarPedido() {
		return impedidaPasarPedido;
	}
	public void setImpedidaPasarPedido(String impedidaPasarPedido) {
		this.impedidaPasarPedido = impedidaPasarPedido;
	}
	/**
	 * @return Returns the barrioDespacho.
	 */
	public String getBarrioDespacho() {
		return barrioDespacho;
	}
	/**
	 * @param barrioDespacho The barrioDespacho to set.
	 */
	public void setBarrioDespacho(String barrioDespacho) {
		this.barrioDespacho = barrioDespacho;
	}
	/**
	 * @return Returns the barrioDomicilio.
	 */
	public String getBarrioDomicilio() {
		return barrioDomicilio;
	}
	/**
	 * @param barrioDomicilio The barrioDomicilio to set.
	 */
	public void setBarrioDomicilio(String barrioDomicilio) {
		this.barrioDomicilio = barrioDomicilio;
	}
	/**
	 * @return Returns the bloqueo.
	 */
	public String getBloqueo() {
		return bloqueo;
	}
	/**
	 * @param bloqueo The bloqueo to set.
	 */
	public void setBloqueo(String bloqueo) {
		this.bloqueo = bloqueo;
	}
	/**
	 * @return Returns the boletaUltimoPedido.
	 */
	public String getBoletaUltimoPedido() {
		return boletaUltimoPedido;
	}
	/**
	 * @param boletaUltimoPedido The boletaUltimoPedido to set.
	 */
	public void setBoletaUltimoPedido(String boletaUltimoPedido) {
		this.boletaUltimoPedido = boletaUltimoPedido;
	}
	/**
	 * @return Returns the codigoCanal.
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}
	/**
	 * @param codigoCanal The codigoCanal to set.
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}
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
	 * @return Returns the codigoMarca.
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}
	/**
	 * @param codigoMarca The codigoMarca to set.
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
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
	 * @return Returns the direccionDespacho.
	 */
	public String getDireccionDespacho() {
		return direccionDespacho;
	}
	/**
	 * @param direccionDespacho The direccionDespacho to set.
	 */
	public void setDireccionDespacho(String direccionDespacho) {
		this.direccionDespacho = direccionDespacho;
	}
	/**
	 * @return Returns the direccionDomicilio.
	 */
	public String getDireccionDomicilio() {
		return direccionDomicilio;
	}
	/**
	 * @param direccionDomicilio The direccionDomicilio to set.
	 */
	public void setDireccionDomicilio(String direccionDomicilio) {
		this.direccionDomicilio = direccionDomicilio;
	}
	/**
	 * @return Returns the email.
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email The email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return Returns the estatus.
	 */
	public String getEstatus() {
		return estatus;
	}
	/**
	 * @param estatus The estatus to set.
	 */
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	/**
	 * @return Returns the fechaIngreso.
	 */
	public String getFechaIngreso() {
		return fechaIngreso;
	}
	/**
	 * @param fechaIngreso The fechaIngreso to set.
	 */
	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
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
	 * @return Returns the importeUltimoPedido.
	 */
	public String getImporteUltimoPedido() {
		return importeUltimoPedido;
	}
	/**
	 * @param importeUltimoPedido The importeUltimoPedido to set.
	 */
	public void setImporteUltimoPedido(String importeUltimoPedido) {
		this.importeUltimoPedido = importeUltimoPedido;
	}
	/**
	 * @return Returns the nombreCompleto.
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	/**
	 * @param nombreCompleto The nombreCompleto to set.
	 */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	/**
	 * @return Returns the numeroDocIdentidad.
	 */
	public String getNumeroDocIdentidad() {
		return numeroDocIdentidad;
	}
	/**
	 * @param numeroDocIdentidad The numeroDocIdentidad to set.
	 */
	public void setNumeroDocIdentidad(String numeroDocIdentidad) {
		this.numeroDocIdentidad = numeroDocIdentidad;
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
	 * @return Returns the oidIdioma.
	 */
	public String getOidIdioma() {
		return oidIdioma;
	}
	/**
	 * @param oidIdioma The oidIdioma to set.
	 */
	public void setOidIdioma(String oidIdioma) {
		this.oidIdioma = oidIdioma;
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
	 * @return Returns the periodoIngreso.
	 */
	public String getPeriodoIngreso() {
		return periodoIngreso;
	}
	/**
	 * @param periodoIngreso The periodoIngreso to set.
	 */
	public void setPeriodoIngreso(String periodoIngreso) {
		this.periodoIngreso = periodoIngreso;
	}
	/**
	 * @return Returns the periodoUltimoPedido.
	 */
	public String getPeriodoUltimoPedido() {
		return periodoUltimoPedido;
	}
	/**
	 * @param periodoUltimoPedido The periodoUltimoPedido to set.
	 */
	public void setPeriodoUltimoPedido(String periodoUltimoPedido) {
		this.periodoUltimoPedido = periodoUltimoPedido;
	}
	/**
	 * @return Returns the saldoPeriodoUltimoPedido.
	 */
	public String getSaldoPeriodoUltimoPedido() {
		return saldoPeriodoUltimoPedido;
	}
	/**
	 * @param saldoPeriodoUltimoPedido The saldoPeriodoUltimoPedido to set.
	 */
	public void setSaldoPeriodoUltimoPedido(String saldoPeriodoUltimoPedido) {
		this.saldoPeriodoUltimoPedido = saldoPeriodoUltimoPedido;
	}
	/**
	 * @return Returns the saldoUnicoUltimoPedido.
	 */
	public String getSaldoUnicoUltimoPedido() {
		return saldoUnicoUltimoPedido;
	}
	/**
	 * @param saldoUnicoUltimoPedido The saldoUnicoUltimoPedido to set.
	 */
	public void setSaldoUnicoUltimoPedido(String saldoUnicoUltimoPedido) {
		this.saldoUnicoUltimoPedido = saldoUnicoUltimoPedido;
	}
	/**
	 * @return Returns the telefono.
	 */
	public String getTelefono() {
		return telefono;
	}
	/**
	 * @param telefono The telefono to set.
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	/**
	 * @return Returns the tipoDocIdentidad.
	 */
	public String getTipoDocIdentidad() {
		return tipoDocIdentidad;
	}
	/**
	 * @param tipoDocIdentidad The tipoDocIdentidad to set.
	 */
	public void setTipoDocIdentidad(String tipoDocIdentidad) {
		this.tipoDocIdentidad = tipoDocIdentidad;
	}
	/**
	 * @return Returns the ubigeoDespacho.
	 */
	public String getUbigeoDespacho() {
		return ubigeoDespacho;
	}
	/**
	 * @param ubigeoDespacho The ubigeoDespacho to set.
	 */
	public void setUbigeoDespacho(String ubigeoDespacho) {
		this.ubigeoDespacho = ubigeoDespacho;
	}
	/**
	 * @return Returns the ubigeoDomicilio.
	 */
	public String getUbigeoDomicilio() {
		return ubigeoDomicilio;
	}
	/**
	 * @param ubigeoDomicilio The ubigeoDomicilio to set.
	 */
	public void setUbigeoDomicilio(String ubigeoDomicilio) {
		this.ubigeoDomicilio = ubigeoDomicilio;
	}
	/**
	 * @return Returns the propiedadAux.
	 */
	public String getPropiedadAux() {
		return propiedadAux;
	}
	/**
	 * @param propiedadAux The propiedadAux to set.
	 */
	public void setPropiedadAux(String propiedadAux) {
		this.propiedadAux = propiedadAux;
	}
	/**
	 * @return Returns the opcionConsulta.
	 */
	public String getOpcionConsulta() {
		return opcionConsulta;
	}
	/**
	 * @param opcionConsulta The opcionConsulta to set.
	 */
	public void setOpcionConsulta(String opcionConsulta) {
		this.opcionConsulta = opcionConsulta;
	}
	/**
	 * @return Returns the mostrarBarrio.
	 */
	public boolean isMostrarBarrio() {
		return mostrarBarrio;
	}
	/**
	 * @param mostrarBarrio The mostrarBarrio to set.
	 */
	public void setMostrarBarrio(boolean mostrarBarrio) {
		this.mostrarBarrio = mostrarBarrio;
	}
	/**
	 * @return Returns the pagosPosterioresUltimoPedido.
	 */
	public String getPagosPosterioresUltimoPedido() {
		return pagosPosterioresUltimoPedido;
	}
	/**
	 * @param pagosPosterioresUltimoPedido The pagosPosterioresUltimoPedido to set.
	 */
	public void setPagosPosterioresUltimoPedido(String pagosPosterioresUltimoPedido) {
		this.pagosPosterioresUltimoPedido = pagosPosterioresUltimoPedido;
	}
	/**
	 * @return Returns the saldoPagarUltimoPedido.
	 */
	public String getSaldoPagarUltimoPedido() {
		return saldoPagarUltimoPedido;
	}
	/**
	 * @param saldoPagarUltimoPedido The saldoPagarUltimoPedido to set.
	 */
	public void setSaldoPagarUltimoPedido(String saldoPagarUltimoPedido) {
		this.saldoPagarUltimoPedido = saldoPagarUltimoPedido;
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
	 * @return the promedioVentas
	 */
	public String getPromedioVentas() {
		return promedioVentas;
	}

	/**
	 * @param promedioVentas the promedioVentas to set
	 */
	public void setPromedioVentas(String promedioVentas) {
		this.promedioVentas = promedioVentas;
	}

	/**
	 * @return the indicadorActivo
	 */
	public String getIndicadorActivo() {
		return indicadorActivo;
	}

	/**
	 * @param indicadorActivo the indicadorActivo to set
	 */
	public void setIndicadorActivo(String indicadorActivo) {
		this.indicadorActivo = indicadorActivo;
	}

	/**
	 * @return the longitudCodigoCliente
	 */
	public String getLongitudCodigoCliente() {
		return longitudCodigoCliente;
	}

	/**
	 * @param longitudCodigoCliente the longitudCodigoCliente to set
	 */
	public void setLongitudCodigoCliente(String longitudCodigoCliente) {
		this.longitudCodigoCliente = longitudCodigoCliente;
	}

	/**
	 * @return the mostrarPantallaBusqueda
	 */
	public boolean isMostrarPantallaBusqueda() {
		return mostrarPantallaBusqueda;
	}

	/**
	 * @param mostrarPantallaBusqueda the mostrarPantallaBusqueda to set
	 */
	public void setMostrarPantallaBusqueda(boolean mostrarPantallaBusqueda) {
		this.mostrarPantallaBusqueda = mostrarPantallaBusqueda;
	}

	/**
	 * @return the fechaCastigada
	 */
	public String getFechaCastigada() {
		return fechaCastigada;
	}

	/**
	 * @param fechaCastigada the fechaCastigada to set
	 */
	public void setFechaCastigada(String fechaCastigada) {
		this.fechaCastigada = fechaCastigada;
	}

	/**
	 * @return the nivelRiesgo
	 */
	public String getNivelRiesgo() {
		return nivelRiesgo;
	}

	/**
	 * @param nivelRiesgo the nivelRiesgo to set
	 */
	public void setNivelRiesgo(String nivelRiesgo) {
		this.nivelRiesgo = nivelRiesgo;
	}

	/**
	 * @return the limiteCredito
	 */
	public String getLimiteCredito() {
		return limiteCredito;
	}

	/**
	 * @param limiteCredito the limiteCredito to set
	 */
	public void setLimiteCredito(String limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	/**
	 * @return the estadoSolicitudPoliza
	 */
	public String getEstadoSolicitudPoliza() {
		return estadoSolicitudPoliza;
	}

	/**
	 * @param estadoSolicitudPoliza the estadoSolicitudPoliza to set
	 */
	public void setEstadoSolicitudPoliza(String estadoSolicitudPoliza) {
		this.estadoSolicitudPoliza = estadoSolicitudPoliza;
	}

	/**
	 * @return the fechaInicioCobertura
	 */
	public String getFechaInicioCobertura() {
		return fechaInicioCobertura;
	}

	/**
	 * @param fechaInicioCobertura the fechaInicioCobertura to set
	 */
	public void setFechaInicioCobertura(String fechaInicioCobertura) {
		this.fechaInicioCobertura = fechaInicioCobertura;
	}

	/**
	 * @return the fechaFinCobertura
	 */
	public String getFechaFinCobertura() {
		return fechaFinCobertura;
	}

	/**
	 * @param fechaFinCobertura the fechaFinCobertura to set
	 */
	public void setFechaFinCobertura(String fechaFinCobertura) {
		this.fechaFinCobertura = fechaFinCobertura;
	}

	/**
	 * @return the mostrarReporteImagenesSC
	 */
	public boolean isMostrarReporteImagenesSC() {
		return mostrarReporteImagenesSC;
	}

	/**
	 * @param mostrarReporteImagenesSC the mostrarReporteImagenesSC to set
	 */
	public void setMostrarReporteImagenesSC(boolean mostrarReporteImagenesSC) {
		this.mostrarReporteImagenesSC = mostrarReporteImagenesSC;
	}

	/**
	 * @return the ciudadDespacho
	 */
	public String getCiudadDespacho() {
		return ciudadDespacho;
	}

	/**
	 * @param ciudadDespacho the ciudadDespacho to set
	 */
	public void setCiudadDespacho(String ciudadDespacho) {
		this.ciudadDespacho = ciudadDespacho;
	}

	/**
	 * @return the villaPoblacionDespacho
	 */
	public String getVillaPoblacionDespacho() {
		return villaPoblacionDespacho;
	}

	/**
	 * @param villaPoblacionDespacho the villaPoblacionDespacho to set
	 */
	public void setVillaPoblacionDespacho(String villaPoblacionDespacho) {
		this.villaPoblacionDespacho = villaPoblacionDespacho;
	}

	/**
	 * @return the ciudadDomicilio
	 */
	public String getCiudadDomicilio() {
		return ciudadDomicilio;
	}

	/**
	 * @param ciudadDomicilio the ciudadDomicilio to set
	 */
	public void setCiudadDomicilio(String ciudadDomicilio) {
		this.ciudadDomicilio = ciudadDomicilio;
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
	 * @return the gerenteZona
	 */
	public String getGerenteZona() {
		return gerenteZona;
	}

	/**
	 * @param gerenteZona the gerenteZona to set
	 */
	public void setGerenteZona(String gerenteZona) {
		this.gerenteZona = gerenteZona;
	}

	/**
	 * @return the celularGerenteZona
	 */
	public String getCelularGerenteZona() {
		return celularGerenteZona;
	}

	/**
	 * @param celularGerenteZona the celularGerenteZona to set
	 */
	public void setCelularGerenteZona(String celularGerenteZona) {
		this.celularGerenteZona = celularGerenteZona;
	}

	/**
	 * @return the liderSeccion
	 */
	public String getLiderSeccion() {
		return liderSeccion;
	}

	/**
	 * @param liderSeccion the liderSeccion to set
	 */
	public void setLiderSeccion(String liderSeccion) {
		this.liderSeccion = liderSeccion;
	}

	/**
	 * @return the celularLiderSeccion
	 */
	public String getCelularLiderSeccion() {
		return celularLiderSeccion;
	}

	/**
	 * @param celularLiderSeccion the celularLiderSeccion to set
	 */
	public void setCelularLiderSeccion(String celularLiderSeccion) {
		this.celularLiderSeccion = celularLiderSeccion;
	}

	/**
	 * @return the tipoConsultora
	 */
	public String getTipoConsultora() {
		return tipoConsultora;
	}

	/**
	 * @param tipoConsultora the tipoConsultora to set
	 */
	public void setTipoConsultora(String tipoConsultora) {
		this.tipoConsultora = tipoConsultora;
	}

	/**
	 * @return the numeroContrato
	 */
	public String getNumeroContrato() {
		return numeroContrato;
	}

	/**
	 * @param numeroContrato the numeroContrato to set
	 */
	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	/**
	 * @return the codigoBuzon
	 */
	public String getCodigoBuzon() {
		return codigoBuzon;
	}

	/**
	 * @param codigoBuzon the codigoBuzon to set
	 */
	public void setCodigoBuzon(String codigoBuzon) {
		this.codigoBuzon = codigoBuzon;
	}

	/**
	 * @return the direccionBuzon
	 */
	public String getDireccionBuzon() {
		return direccionBuzon;
	}

	/**
	 * @param direccionBuzon the direccionBuzon to set
	 */
	public void setDireccionBuzon(String direccionBuzon) {
		this.direccionBuzon = direccionBuzon;
	}

	/**
	 * @return the telefonoBuzon
	 */
	public String getTelefonoBuzon() {
		return telefonoBuzon;
	}

	/**
	 * @param telefonoBuzon the telefonoBuzon to set
	 */
	public void setTelefonoBuzon(String telefonoBuzon) {
		this.telefonoBuzon = telefonoBuzon;
	}

	/**
	 * @return the celularBuzon
	 */
	public String getCelularBuzon() {
		return celularBuzon;
	}

	/**
	 * @param celularBuzon the celularBuzon to set
	 */
	public void setCelularBuzon(String celularBuzon) {
		this.celularBuzon = celularBuzon;
	}

	/**
	 * @return the descripcionBuzon
	 */
	public String getDescripcionBuzon() {
		return descripcionBuzon;
	}

	/**
	 * @param descripcionBuzon the descripcionBuzon to set
	 */
	public void setDescripcionBuzon(String descripcionBuzon) {
		this.descripcionBuzon = descripcionBuzon;
	}

}
