package biz.belcorp.ssicc.web.scsicc.hip.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;


/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan Altamirano</a>
 *
 */
public class ConsultaHIPActualizacionDireccionClienteForm extends BaseSearchForm {
	private static final long serialVersionUID = 1L;
	
	private String codCliente;
	private String nomCliente;
	private String desRegZonTerri;
	private String desRegZonSecTerri;
	
	private String telefonoFijo;
	private String telefonoCelular;
	private String email;
	private String fechaNacimiento;
	
	private String primerApellido;
	private String segundoApellido;
	private String primerNombre;
	private String segundoNombre;
	private String documento;
	
	private String validarCaracteres1;
	private String validarCaracteres2;
	private String validarCaracteres3;
	
	private String cadenaCaracteresV1;
	private String cadenaCaracteresNV1;
	private String cadenaCaracteresV2;
	private String cadenaCaracteresNV2;
	private String cadenaCaracteresV3;
	private String cadenaCaracteresNV3;
	
	private String zona;
	private String territorio;
	private String oidTerritorioAdministrativo;
	private String oidTerritorio;

	private String tipoVia;
	private String numeroPrincipal;
	private String nombreVia;
	private String observacionDireccion;
	private String barrio;
	private String nivel1;
	private String nivel2;
	private String nivel3;
	private String nivel4;
	private String nivel5;
	private String nivel6;
	
	private String codigoCiudad;
	private String villaPoblacion;
	
	private boolean mostrarCiudad;
	private boolean mostrarVillaPoblacion;

	private boolean mostrarTipoVia;
	private boolean mostrarNumeroPrincipal;
	private boolean mostrarBarrio;
	
	private boolean permitirModificarUbigeo;
	private boolean editable;
	
	private String oidPais;
	private String codigoPais;
	
	private String codigoZonaInicial;
	private String codigoTerritorioInicial;
	
	private String descripcionNivel1;
	private String descripcionNivel2;
	private String descripcionNivel3;
	private String descripcionNivel4;
	private String descripcionNivel5;
	private String descripcionNivel6;
	private String totalNiveles;
	
	//si se ha confirmado [Aceptar] cuando el territorio es diferente al ubigeo del distrito
	private String confirmacionTerritorio;

	//En casos que se necesita mostrar mensajes de confirmacion para poder proceder a grabar los datos del cliente
	private String mensajeConfirmacion;

	private String codNivel1;
	private String codNivel2;
	private String codNivel3;
	private String codNivel4;
	private String codNivel5;
	private String codNivel6;
	
	private boolean cambioZonaTerritorio;
	private boolean consultoraPasoPedido;
	private boolean mostrarMensajePeriodoFinCerrado;
	
	private String longitudCodigoZona;
	private String codigoEstatus;
	private String saldoDeuda;
	
	private String mensajeSaldoDeuda;
	private String mensajeBloqueoActivo;
	
	private boolean graboOK;
	
	private boolean permitirGrabar;
	private boolean esEjecutiva;
	
	private String codigoRegion;
	private String codigoSeccion;
	
	private String oidPeriodo;
	private boolean mostrarMensajePedidoExtemporaneo;
	private boolean mostrarMensajeCambioPeriodoVigente;
	private boolean esMayorPeriodoVigente;
	private boolean requiereGenerarEstatus;
	private String codigoPeriodoIniUA;
	
	private boolean deshabilitarZonaTerritorio;
	
	/**
	 * @return the barrio
	 */
	public String getBarrio() {
		return barrio;
	}
	
	/**
	 * @return the cadenaCaracteresNV1
	 */
	public String getCadenaCaracteresNV1() {
		return cadenaCaracteresNV1;
	}
	
	/**
	 * @return the cadenaCaracteresNV2
	 */
	public String getCadenaCaracteresNV2() {
		return cadenaCaracteresNV2;
	}
	
	/**
	 * @return the cadenaCaracteresNV3
	 */
	public String getCadenaCaracteresNV3() {
		return cadenaCaracteresNV3;
	}
	
	/**
	 * @return the cadenaCaracteresV1
	 */
	public String getCadenaCaracteresV1() {
		return cadenaCaracteresV1;
	}
	
	/**
	 * @return the cadenaCaracteresV2
	 */
	public String getCadenaCaracteresV2() {
		return cadenaCaracteresV2;
	}	
	
	/**
	 * @return the cadenaCaracteresV3
	 */
	public String getCadenaCaracteresV3() {
		return cadenaCaracteresV3;
	}

	/**
	 * @return the codCliente
	 */
	public String getCodCliente() {
		return codCliente;
	}

	/**
	 * @return the codigoCiudad
	 */
	public String getCodigoCiudad() {
		return codigoCiudad;
	}

	/**
	 * @return the codigoEstatus
	 */
	public String getCodigoEstatus() {
		return codigoEstatus;
	}
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	
	/**
	 * @return the codigoTerritorioInicial
	 */
	public String getCodigoTerritorioInicial() {
		return codigoTerritorioInicial;
	}
	
	/**
	 * @return the codigoZonaInicial
	 */
	public String getCodigoZonaInicial() {
		return codigoZonaInicial;
	}
	
	/**
	 * @return the codNivel1
	 */
	public String getCodNivel1() {
		return codNivel1;
	}

	/**
	 * @return the codNivel2
	 */
	public String getCodNivel2() {
		return codNivel2;
	}

	/**
	 * @return the codNivel3
	 */
	public String getCodNivel3() {
		return codNivel3;
	}

	/**
	 * @return the codNivel4
	 */
	public String getCodNivel4() {
		return codNivel4;
	}

	/**
	 * @return the codNivel5
	 */
	public String getCodNivel5() {
		return codNivel5;
	}

	/**
	 * @return the codNivel6
	 */
	public String getCodNivel6() {
		return codNivel6;
	}

	/**
	 * @return the confirmacionTerritorio
	 */
	public String getConfirmacionTerritorio() {
		return confirmacionTerritorio;
	}

	/**
	 * @return the descripcionNivel1
	 */
	public String getDescripcionNivel1() {
		return descripcionNivel1;
	}

	/**
	 * @return the descripcionNivel2
	 */
	public String getDescripcionNivel2() {
		return descripcionNivel2;
	}

	/**
	 * @return the descripcionNivel3
	 */
	public String getDescripcionNivel3() {
		return descripcionNivel3;
	}

	/**
	 * @return the descripcionNivel4
	 */
	public String getDescripcionNivel4() {
		return descripcionNivel4;
	}

	/**
	 * @return the descripcionNivel5
	 */
	public String getDescripcionNivel5() {
		return descripcionNivel5;
	}

	/**
	 * @return the descripcionNivel6
	 */
	public String getDescripcionNivel6() {
		return descripcionNivel6;
	}

	/**
	 * @return the desRegZonSecTerri
	 */
	public String getDesRegZonSecTerri() {
		return desRegZonSecTerri;
	}

	/**
	 * @return the desRegZonTerri
	 */
	public String getDesRegZonTerri() {
		return desRegZonTerri;
	}

	/**
	 * @return the documento
	 */
	public String getDocumento() {
		return documento;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the fechaNacimiento
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * @return the longitudCodigoZona
	 */
	public String getLongitudCodigoZona() {
		return longitudCodigoZona;
	}

	/**
	 * @return the mensajeBloqueoActivo
	 */
	public String getMensajeBloqueoActivo() {
		return mensajeBloqueoActivo;
	}

	/**
	 * @return the mensajeConfirmacion
	 */
	public String getMensajeConfirmacion() {
		return mensajeConfirmacion;
	}

	/**
	 * @return the mensajeSaldoDeuda
	 */
	public String getMensajeSaldoDeuda() {
		return mensajeSaldoDeuda;
	}

	/**
	 * @return the nivel1
	 */
	public String getNivel1() {
		return nivel1;
	}

	/**
	 * @return the nivel2
	 */
	public String getNivel2() {
		return nivel2;
	}

	/**
	 * @return the nivel3
	 */
	public String getNivel3() {
		return nivel3;
	}

	/**
	 * @return the nivel4
	 */
	public String getNivel4() {
		return nivel4;
	}

	/**
	 * @return the nivel5
	 */
	public String getNivel5() {
		return nivel5;
	}

	/**
	 * @return the nivel6
	 */
	public String getNivel6() {
		return nivel6;
	}

	/**
	 * @return the nombreVia
	 */
	public String getNombreVia() {
		return nombreVia;
	}

	/**
	 * @return the nomCliente
	 */
	public String getNomCliente() {
		return nomCliente;
	}

	/**
	 * @return the numeroPrincipal
	 */
	public String getNumeroPrincipal() {
		return numeroPrincipal;
	}

	/**
	 * @return the observacionDireccion
	 */
	public String getObservacionDireccion() {
		return observacionDireccion;
	}

	/**
	 * @return the oidPais
	 */
	public String getOidPais() {
		return oidPais;
	}

	/**
	 * @return the oidTerritorio
	 */
	public String getOidTerritorio() {
		return oidTerritorio;
	}

	/**
	 * @return the oidTerritorioAdministrativo
	 */
	public String getOidTerritorioAdministrativo() {
		return oidTerritorioAdministrativo;
	}

	/**
	 * @return the primerApellido
	 */
	public String getPrimerApellido() {
		return primerApellido;
	}

	/**
	 * @return the primerNombre
	 */
	public String getPrimerNombre() {
		return primerNombre;
	}

	/**
	 * @return the saldoDeuda
	 */
	public String getSaldoDeuda() {
		return saldoDeuda;
	}

	/**
	 * @return the segundoApellido
	 */
	public String getSegundoApellido() {
		return segundoApellido;
	}

	/**
	 * @return the segundoNombre
	 */
	public String getSegundoNombre() {
		return segundoNombre;
	}

	/**
	 * @return the telefonoCelular
	 */
	public String getTelefonoCelular() {
		return telefonoCelular;
	}

	/**
	 * @return the telefonoFijo
	 */
	public String getTelefonoFijo() {
		return telefonoFijo;
	}

	/**
	 * @return the tipoVia
	 */
	public String getTipoVia() {
		return tipoVia;
	}

	/**
	 * @return the totalNiveles
	 */
	public String getTotalNiveles() {
		return totalNiveles;
	}

	/**
	 * @return the validarCaracteres1
	 */
	public String getValidarCaracteres1() {
		return validarCaracteres1;
	}

	/**
	 * @return the validarCaracteres2
	 */
	public String getValidarCaracteres2() {
		return validarCaracteres2;
	}

	/**
	 * @return the validarCaracteres3
	 */
	public String getValidarCaracteres3() {
		return validarCaracteres3;
	}

	/**
	 * @return the villaPoblacion
	 */
	public String getVillaPoblacion() {
		return villaPoblacion;
	}

	/**
	 * @return the cambioZonaTerritorio
	 */
	public boolean isCambioZonaTerritorio() {
		return cambioZonaTerritorio;
	}

	/**
	 * @return the consultoraPasoPedido
	 */
	public boolean isConsultoraPasoPedido() {
		return consultoraPasoPedido;
	}

	/**
	 * @return the editable
	 */
	public boolean isEditable() {
		return editable;
	}

	/**
	 * @return the mostrarBarrio
	 */
	public boolean isMostrarBarrio() {
		return mostrarBarrio;
	}

	/**
	 * @return the mostrarCiudad
	 */
	public boolean isMostrarCiudad() {
		return mostrarCiudad;
	}

	/**
	 * @return the mostrarMensajePeriodoFinCerrado
	 */
	public boolean isMostrarMensajePeriodoFinCerrado() {
		return mostrarMensajePeriodoFinCerrado;
	}

	/**
	 * @return the mostrarNumeroPrincipal
	 */
	public boolean isMostrarNumeroPrincipal() {
		return mostrarNumeroPrincipal;
	}

	/**
	 * @return the mostrarTipoVia
	 */
	public boolean isMostrarTipoVia() {
		return mostrarTipoVia;
	}

	/**
	 * @return the mostrarVillaPoblacion
	 */
	public boolean isMostrarVillaPoblacion() {
		return mostrarVillaPoblacion;
	}

	/**
	 * @return the permitirModificarUbigeo
	 */
	public boolean isPermitirModificarUbigeo() {
		return permitirModificarUbigeo;
	}

	/**
	 * @param barrio the barrio to set
	 */
	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	/**
	 * @param cadenaCaracteresNV1 the cadenaCaracteresNV1 to set
	 */
	public void setCadenaCaracteresNV1(String cadenaCaracteresNV1) {
		this.cadenaCaracteresNV1 = cadenaCaracteresNV1;
	}

	/**
	 * @param cadenaCaracteresNV2 the cadenaCaracteresNV2 to set
	 */
	public void setCadenaCaracteresNV2(String cadenaCaracteresNV2) {
		this.cadenaCaracteresNV2 = cadenaCaracteresNV2;
	}

	/**
	 * @param cadenaCaracteresNV3 the cadenaCaracteresNV3 to set
	 */
	public void setCadenaCaracteresNV3(String cadenaCaracteresNV3) {
		this.cadenaCaracteresNV3 = cadenaCaracteresNV3;
	}

	/**
	 * @param cadenaCaracteresV1 the cadenaCaracteresV1 to set
	 */
	public void setCadenaCaracteresV1(String cadenaCaracteresV1) {
		this.cadenaCaracteresV1 = cadenaCaracteresV1;
	}

	/**
	 * @param cadenaCaracteresV2 the cadenaCaracteresV2 to set
	 */
	public void setCadenaCaracteresV2(String cadenaCaracteresV2) {
		this.cadenaCaracteresV2 = cadenaCaracteresV2;
	}

	/**
	 * @param cadenaCaracteresV3 the cadenaCaracteresV3 to set
	 */
	public void setCadenaCaracteresV3(String cadenaCaracteresV3) {
		this.cadenaCaracteresV3 = cadenaCaracteresV3;
	}

	/**
	 * @param cambioZonaTerritorio the cambioZonaTerritorio to set
	 */
	public void setCambioZonaTerritorio(boolean cambioZonaTerritorio) {
		this.cambioZonaTerritorio = cambioZonaTerritorio;
	}

	/**
	 * @param codCliente the codCliente to set
	 */
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}

	/**
	 * @param codigoCiudad the codigoCiudad to set
	 */
	public void setCodigoCiudad(String codigoCiudad) {
		this.codigoCiudad = codigoCiudad;
	}

	/**
	 * @param codigoEstatus the codigoEstatus to set
	 */
	public void setCodigoEstatus(String codigoEstatus) {
		this.codigoEstatus = codigoEstatus;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	/**
	 * @param codigoTerritorioInicial the codigoTerritorioInicial to set
	 */
	public void setCodigoTerritorioInicial(String codigoTerritorioInicial) {
		this.codigoTerritorioInicial = codigoTerritorioInicial;
	}
	
	/**
	 * @param codigoZonaInicial the codigoZonaInicial to set
	 */
	public void setCodigoZonaInicial(String codigoZonaInicial) {
		this.codigoZonaInicial = codigoZonaInicial;
	}

	/**
	 * @param codNivel1 the codNivel1 to set
	 */
	public void setCodNivel1(String codNivel1) {
		this.codNivel1 = codNivel1;
	}

	/**
	 * @param codNivel2 the codNivel2 to set
	 */
	public void setCodNivel2(String codNivel2) {
		this.codNivel2 = codNivel2;
	}

	/**
	 * @param codNivel3 the codNivel3 to set
	 */
	public void setCodNivel3(String codNivel3) {
		this.codNivel3 = codNivel3;
	}

	/**
	 * @param codNivel4 the codNivel4 to set
	 */
	public void setCodNivel4(String codNivel4) {
		this.codNivel4 = codNivel4;
	}

	/**
	 * @param codNivel5 the codNivel5 to set
	 */
	public void setCodNivel5(String codNivel5) {
		this.codNivel5 = codNivel5;
	}

	/**
	 * @param codNivel6 the codNivel6 to set
	 */
	public void setCodNivel6(String codNivel6) {
		this.codNivel6 = codNivel6;
	}

	/**
	 * @param confirmacionTerritorio the confirmacionTerritorio to set
	 */
	public void setConfirmacionTerritorio(String confirmacionTerritorio) {
		this.confirmacionTerritorio = confirmacionTerritorio;
	}

	/**
	 * @param consultoraPasoPedido the consultoraPasoPedido to set
	 */
	public void setConsultoraPasoPedido(boolean consultoraPasoPedido) {
		this.consultoraPasoPedido = consultoraPasoPedido;
	}

	/**
	 * @param descripcionNivel1 the descripcionNivel1 to set
	 */
	public void setDescripcionNivel1(String descripcionNivel1) {
		this.descripcionNivel1 = descripcionNivel1;
	}

	/**
	 * @param descripcionNivel2 the descripcionNivel2 to set
	 */
	public void setDescripcionNivel2(String descripcionNivel2) {
		this.descripcionNivel2 = descripcionNivel2;
	}

	/**
	 * @param descripcionNivel3 the descripcionNivel3 to set
	 */
	public void setDescripcionNivel3(String descripcionNivel3) {
		this.descripcionNivel3 = descripcionNivel3;
	}

	/**
	 * @param descripcionNivel4 the descripcionNivel4 to set
	 */
	public void setDescripcionNivel4(String descripcionNivel4) {
		this.descripcionNivel4 = descripcionNivel4;
	}

	/**
	 * @param descripcionNivel5 the descripcionNivel5 to set
	 */
	public void setDescripcionNivel5(String descripcionNivel5) {
		this.descripcionNivel5 = descripcionNivel5;
	}

	/**
	 * @param descripcionNivel6 the descripcionNivel6 to set
	 */
	public void setDescripcionNivel6(String descripcionNivel6) {
		this.descripcionNivel6 = descripcionNivel6;
	}

	/**
	 * @param desRegZonSecTerri the desRegZonSecTerri to set
	 */
	public void setDesRegZonSecTerri(String desRegZonSecTerri) {
		this.desRegZonSecTerri = desRegZonSecTerri;
	}

	/**
	 * @param desRegZonTerri the desRegZonTerri to set
	 */
	public void setDesRegZonTerri(String desRegZonTerri) {
		this.desRegZonTerri = desRegZonTerri;
	}

	/**
	 * @param documento the documento to set
	 */
	public void setDocumento(String documento) {
		this.documento = documento;
	}

	/**
	 * @param editable the editable to set
	 */
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * @param longitudCodigoZona the longitudCodigoZona to set
	 */
	public void setLongitudCodigoZona(String longitudCodigoZona) {
		this.longitudCodigoZona = longitudCodigoZona;
	}

	/**
	 * @param mensajeBloqueoActivo the mensajeBloqueoActivo to set
	 */
	public void setMensajeBloqueoActivo(String mensajeBloqueoActivo) {
		this.mensajeBloqueoActivo = mensajeBloqueoActivo;
	}

	/**
	 * @param mensajeConfirmacion the mensajeConfirmacion to set
	 */
	public void setMensajeConfirmacion(String mensajeConfirmacion) {
		this.mensajeConfirmacion = mensajeConfirmacion;
	}

	/**
	 * @param mensajeSaldoDeuda the mensajeSaldoDeuda to set
	 */
	public void setMensajeSaldoDeuda(String mensajeSaldoDeuda) {
		this.mensajeSaldoDeuda = mensajeSaldoDeuda;
	}

	/**
	 * @param mostrarBarrio the mostrarBarrio to set
	 */
	public void setMostrarBarrio(boolean mostrarBarrio) {
		this.mostrarBarrio = mostrarBarrio;
	}

	/**
	 * @param mostrarCiudad the mostrarCiudad to set
	 */
	public void setMostrarCiudad(boolean mostrarCiudad) {
		this.mostrarCiudad = mostrarCiudad;
	}

	/**
	 * @param mostrarMensajePeriodoFinCerrado the mostrarMensajePeriodoFinCerrado to set
	 */
	public void setMostrarMensajePeriodoFinCerrado(
			boolean mostrarMensajePeriodoFinCerrado) {
		this.mostrarMensajePeriodoFinCerrado = mostrarMensajePeriodoFinCerrado;
	}

	/**
	 * @param mostrarNumeroPrincipal the mostrarNumeroPrincipal to set
	 */
	public void setMostrarNumeroPrincipal(boolean mostrarNumeroPrincipal) {
		this.mostrarNumeroPrincipal = mostrarNumeroPrincipal;
	}

	/**
	 * @param mostrarTipoVia the mostrarTipoVia to set
	 */
	public void setMostrarTipoVia(boolean mostrarTipoVia) {
		this.mostrarTipoVia = mostrarTipoVia;
	}

	/**
	 * @param mostrarVillaPoblacion the mostrarVillaPoblacion to set
	 */
	public void setMostrarVillaPoblacion(boolean mostrarVillaPoblacion) {
		this.mostrarVillaPoblacion = mostrarVillaPoblacion;
	}

	/**
	 * @param nivel1 the nivel1 to set
	 */
	public void setNivel1(String nivel1) {
		this.nivel1 = nivel1;
	}

	/**
	 * @param nivel2 the nivel2 to set
	 */
	public void setNivel2(String nivel2) {
		this.nivel2 = nivel2;
	}

	/**
	 * @param nivel3 the nivel3 to set
	 */
	public void setNivel3(String nivel3) {
		this.nivel3 = nivel3;
	}

	/**
	 * @param nivel4 the nivel4 to set
	 */
	public void setNivel4(String nivel4) {
		this.nivel4 = nivel4;
	}

	/**
	 * @param nivel5 the nivel5 to set
	 */
	public void setNivel5(String nivel5) {
		this.nivel5 = nivel5;
	}

	/**
	 * @param nivel6 the nivel6 to set
	 */
	public void setNivel6(String nivel6) {
		this.nivel6 = nivel6;
	}

	/**
	 * @param nombreVia the nombreVia to set
	 */
	public void setNombreVia(String nombreVia) {
		this.nombreVia = nombreVia;
	}

	/**
	 * @param nomCliente the nomCliente to set
	 */
	public void setNomCliente(String nomCliente) {
		this.nomCliente = nomCliente;
	}

	/**
	 * @param numeroPrincipal the numeroPrincipal to set
	 */
	public void setNumeroPrincipal(String numeroPrincipal) {
		this.numeroPrincipal = numeroPrincipal;
	}

	/**
	 * @param observacionDireccion the observacionDireccion to set
	 */
	public void setObservacionDireccion(String observacionDireccion) {
		this.observacionDireccion = observacionDireccion;
	}

	/**
	 * @param oidPais the oidPais to set
	 */
	public void setOidPais(String oidPais) {
		this.oidPais = oidPais;
	}

	/**
	 * @param oidTerritorio the oidTerritorio to set
	 */
	public void setOidTerritorio(String oidTerritorio) {
		this.oidTerritorio = oidTerritorio;
	}

	/**
	 * @param oidTerritorioAdministrativo the oidTerritorioAdministrativo to set
	 */
	public void setOidTerritorioAdministrativo(String oidTerritorioAdministrativo) {
		this.oidTerritorioAdministrativo = oidTerritorioAdministrativo;
	}

	/**
	 * @param permitirModificarUbigeo the permitirModificarUbigeo to set
	 */
	public void setPermitirModificarUbigeo(boolean permitirModificarUbigeo) {
		this.permitirModificarUbigeo = permitirModificarUbigeo;
	}

	/**
	 * @param primerApellido the primerApellido to set
	 */
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	/**
	 * @param primerNombre the primerNombre to set
	 */
	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	/**
	 * @param saldoDeuda the saldoDeuda to set
	 */
	public void setSaldoDeuda(String saldoDeuda) {
		this.saldoDeuda = saldoDeuda;
	}

	/**
	 * @param segundoApellido the segundoApellido to set
	 */
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	/**
	 * @param segundoNombre the segundoNombre to set
	 */
	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	/**
	 * @param telefonoCelular the telefonoCelular to set
	 */
	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}

	/**
	 * @param telefonoFijo the telefonoFijo to set
	 */
	public void setTelefonoFijo(String telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}

	/**
	 * @param tipoVia the tipoVia to set
	 */
	public void setTipoVia(String tipoVia) {
		this.tipoVia = tipoVia;
	}

	/**
	 * @param totalNiveles the totalNiveles to set
	 */
	public void setTotalNiveles(String totalNiveles) {
		this.totalNiveles = totalNiveles;
	}

	/**
	 * @param validarCaracteres1 the validarCaracteres1 to set
	 */
	public void setValidarCaracteres1(String validarCaracteres1) {
		this.validarCaracteres1 = validarCaracteres1;
	}

	/**
	 * @param validarCaracteres2 the validarCaracteres2 to set
	 */
	public void setValidarCaracteres2(String validarCaracteres2) {
		this.validarCaracteres2 = validarCaracteres2;
	}

	/**
	 * @param validarCaracteres3 the validarCaracteres3 to set
	 */
	public void setValidarCaracteres3(String validarCaracteres3) {
		this.validarCaracteres3 = validarCaracteres3;
	}

	/**
	 * @param villaPoblacion the villaPoblacion to set
	 */
	public void setVillaPoblacion(String villaPoblacion) {
		this.villaPoblacion = villaPoblacion;
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
	 * @return the permitirGrabar
	 */
	public boolean isPermitirGrabar() {
		return permitirGrabar;
	}

	/**
	 * @param permitirGrabar the permitirGrabar to set
	 */
	public void setPermitirGrabar(boolean permitirGrabar) {
		this.permitirGrabar = permitirGrabar;
	}

	/**
	 * @return the esEjecutiva
	 */
	public boolean isEsEjecutiva() {
		return esEjecutiva;
	}

	/**
	 * @param esEjecutiva the esEjecutiva to set
	 */
	public void setEsEjecutiva(boolean esEjecutiva) {
		this.esEjecutiva = esEjecutiva;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getTerritorio() {
		return territorio;
	}

	public void setTerritorio(String territorio) {
		this.territorio = territorio;
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
	 * @return the codigoSeccion
	 */
	public String getCodigoSeccion() {
		return codigoSeccion;
	}

	/**
	 * @param codigoSeccion the codigoSeccion to set
	 */
	public void setCodigoSeccion(String codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
	}

	/**
	 * @return the oidPeriodo
	 */
	public String getOidPeriodo() {
		return oidPeriodo;
	}

	/**
	 * @param oidPeriodo the oidPeriodo to set
	 */
	public void setOidPeriodo(String oidPeriodo) {
		this.oidPeriodo = oidPeriodo;
	}

	/**
	 * @return the mostrarMensajePedidoExtemporaneo
	 */
	public boolean isMostrarMensajePedidoExtemporaneo() {
		return mostrarMensajePedidoExtemporaneo;
	}

	/**
	 * @param mostrarMensajePedidoExtemporaneo the mostrarMensajePedidoExtemporaneo to set
	 */
	public void setMostrarMensajePedidoExtemporaneo(
			boolean mostrarMensajePedidoExtemporaneo) {
		this.mostrarMensajePedidoExtemporaneo = mostrarMensajePedidoExtemporaneo;
	}

	/**
	 * @return the mostrarMensajeCambioPeriodoVigente
	 */
	public boolean isMostrarMensajeCambioPeriodoVigente() {
		return mostrarMensajeCambioPeriodoVigente;
	}

	/**
	 * @param mostrarMensajeCambioPeriodoVigente the mostrarMensajeCambioPeriodoVigente to set
	 */
	public void setMostrarMensajeCambioPeriodoVigente(
			boolean mostrarMensajeCambioPeriodoVigente) {
		this.mostrarMensajeCambioPeriodoVigente = mostrarMensajeCambioPeriodoVigente;
	}

	/**
	 * @return the esMayorPeriodoVigente
	 */
	public boolean isEsMayorPeriodoVigente() {
		return esMayorPeriodoVigente;
	}

	/**
	 * @param esMayorPeriodoVigente the esMayorPeriodoVigente to set
	 */
	public void setEsMayorPeriodoVigente(boolean esMayorPeriodoVigente) {
		this.esMayorPeriodoVigente = esMayorPeriodoVigente;
	}

	/**
	 * @return the requiereGenerarEstatus
	 */
	public boolean isRequiereGenerarEstatus() {
		return requiereGenerarEstatus;
	}

	/**
	 * @param requiereGenerarEstatus the requiereGenerarEstatus to set
	 */
	public void setRequiereGenerarEstatus(boolean requiereGenerarEstatus) {
		this.requiereGenerarEstatus = requiereGenerarEstatus;
	}

	/**
	 * @return the codigoPeriodoIniUA
	 */
	public String getCodigoPeriodoIniUA() {
		return codigoPeriodoIniUA;
	}

	/**
	 * @param codigoPeriodoIniUA the codigoPeriodoIniUA to set
	 */
	public void setCodigoPeriodoIniUA(String codigoPeriodoIniUA) {
		this.codigoPeriodoIniUA = codigoPeriodoIniUA;
	}

	/**
	 * @return the deshabilitarZonaTerritorio
	 */
	public boolean isDeshabilitarZonaTerritorio() {
		return deshabilitarZonaTerritorio;
	}

	/**
	 * @param deshabilitarZonaTerritorio the deshabilitarZonaTerritorio to set
	 */
	public void setDeshabilitarZonaTerritorio(boolean deshabilitarZonaTerritorio) {
		this.deshabilitarZonaTerritorio = deshabilitarZonaTerritorio;
	}

	
}
