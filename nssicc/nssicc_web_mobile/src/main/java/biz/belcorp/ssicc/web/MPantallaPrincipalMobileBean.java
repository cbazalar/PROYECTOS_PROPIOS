package biz.belcorp.ssicc.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scsicc.model.ConsultaHIPDatosCliente;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.IdiomaService;
import biz.belcorp.ssicc.service.PaisService;
import biz.belcorp.ssicc.service.UsuarioService;
import biz.belcorp.ssicc.service.scsicc.ConsultaHIPDatosClienteService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ConsultaCOBTelecobroService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.util.StringUtil;

@ManagedBean
@SessionScoped
public class MPantallaPrincipalMobileBean extends MPantallaPrincipalBean {
	
	public static final int HIP_FILAS_ULTIMOS_MOVIMIENTOS = 4; //Filas a Mostrarse
	private List hipTipoClienteList = new ArrayList();
	private List hipSubTipoClienteList = new ArrayList();
	private List hipTipoClasificacionList = new ArrayList();
	private List hipClasificacionList = new ArrayList();
	private List hipMediosContactosList = new ArrayList();
	
	private List hipUnidadesAdministrativasList = new ArrayList();
	private List hipOpcionesList = new ArrayList();
	
	private List hipReclamosCabeceraList = new ArrayList();
	private List hipCuentaCorrientesMovimientosList = new ArrayList();
	private List hipPedidosConsultoraList = new ArrayList();
	private List hipConcursosBolsaFaltantesList = new ArrayList();
	private List hipVinculosReferenciasList = new ArrayList();
	
	private List hipSolicitudesPolizaList = new ArrayList();
	private List hipHistoricoCargosPolizaList = new ArrayList();
	
	private List lstParametros = new ArrayList();
	
	private String mostrarDigitacionSimplificada;
	private String indicadorTipo;
	private String nroColumnas;
	
	private boolean valPara;	
	private boolean viewResults = false;	
	
	private String pageOpcionConsulta;
	private String manageOpcionConsulta;
	
	protected String codigoUsuario;
    protected String password; 
	protected String countryCode;
	
	//Datos generales de Hiperconsulta
	//private ConsultaHIPDatosCliente hipDtoDatosCliente;	
	private String opcionConsulta;
	
	protected MPantallaPrincipalMobileFormBean formBusqueda;
	
	protected Boolean viewDetalleColumnnasDatatable = true;
	protected String viewStringDetalleColumnnasDatatable = "true";
	
	@PostConstruct
	public void init() {
		//super.init();
		//this.ingresarSistemaMobile();
		formBusqueda = new MPantallaPrincipalMobileFormBean();
		limpiar(formBusqueda);
    }  
	
	
	public String ingresarSistemaMobile() {
		String paginaInicio = "index";
    	this.pantallaBodyXhtml = "test"; 
        
        /* Construyendo Menu del Sistema */
        String language = "";
        Locale locale = Locale.ENGLISH;
        
        UsuarioService usuarioService = (UsuarioService)this.getBeanService("usuarioService");
        PaisService paisService = (PaisService) this.getBeanService("paisService");
        Usuario usuario=null;
        
        if(StringUtils.isBlank(codigoUsuario)){
        	addInfo("Debe ingresar el nombre de usuario!", "Login");
        	return paginaInicio;
        }else if(StringUtils.isBlank(password)){
        	addInfo("Debe ingresar un password!", "Login");
        	return paginaInicio;
        }else if(countryCode.equals("0")){
        	addInfo("Debe seleccionar un país!", "Login");
        	return paginaInicio;
        }else{
        	addInfo("OK!", "Login");
        }
        
        
		try {
			usuario = usuarioService.getUsuarioByUsername(this.codigoUsuario);
		}
		catch(Exception e) {
			usuario=null;	
			return this.errorIngresoLogin();
		}
		
		try {
			if(usuario != null){
				Pais pais = paisService.getPais(this.countryCode);
				if (!StringUtils.equals(StringUtil.encodePassword(this.password,
						Constants.ENC_ALGORITHM), usuario.getClave())) {
					log.debug("usuario Clave: " + usuario.getClave());
					return this.errorIngresoLogin();
				}
				
				//Iniciamos una nueva session de usuario
				HttpSession sesion = this.getSession(true);
				
				// Seteamos el valor del lenguaje
				if (StringUtils.isNotBlank(language)) {
					IdiomaService idiomaService = (IdiomaService)this.getBeanService("idiomaService");
					Idioma idioma = idiomaService.getIdioma(language);
					locale = new Locale(idioma.getCodigoISO());
					usuario.setIdioma(idioma);					
				} else {
					locale = new Locale(usuario.getIdioma().getCodigoISO());
				}
				
				if (log.isDebugEnabled()) {
					log.debug("INI Cargando las opciones de menú del usuario.");
				}
				
				//obteniendo ip remota
				String ip= this.getRequest().getRemoteAddr();
				log.debug("maquina remota ip "+ip);
				usuario.setIpMaquinaRemota(ip);
				
				// Guardamos el objeto usuario en la sesion
				this.currentUser = usuario;
				// Guardamos el objeto del pais en la sesion
				this.currentCountry = pais;

	            // Para la aplicación
	           	this.localeKey = locale;
	    		
			}
		}
		catch (Exception e) {
			return this.errorIngresoLogin();
		}
		
		return this.pantallaBodyXhtml;
    }
    
	/**
     * Salir del Sistema
     * @return
     */
    public String salirSistema() {
		this.getSession(true).invalidate();
		return this.LOGIN_FAILURE_PAGE;
	}
	
    
	private void limpiar(MPantallaPrincipalMobileFormBean f) {
		
		f.setCodigoCliente("");
		f.setCodigoDigitoControl("");
		f.setOidCliente("");
		f.setTipoDocIdentidad("");
		f.setNumeroDocIdentidad("");
		f.setNombreCompleto("");
		
		f.setEstatus("");
		f.setTelefono("");
		f.setEmail("");
		f.setFechaNacimiento("");
		f.setPromedioVentas("");
		f.setIndicadorActivo("");
		
		f.setFechaIngreso("");
		f.setPeriodoIngreso("");
		f.setBloqueo("");
		
		f.setDireccionDespacho("");
		f.setBarrioDespacho("");
		f.setUbigeoDespacho("");
		
		f.setDireccionDomicilio("");
		f.setBarrioDomicilio("");
		f.setUbigeoDomicilio("");
		
		f.setPeriodoUltimoPedido("");
		f.setBoletaUltimoPedido("");
		f.setImporteUltimoPedido("");
		f.setSaldoPeriodoUltimoPedido("");
		f.setSaldoUnicoUltimoPedido("");		
		f.setPagosPosterioresUltimoPedido("");
		f.setMostrarPantallaBusqueda(false);
		f.setFechaCastigada("");

		f.setEstadoSolicitudPoliza("");
		f.setFechaInicioCobertura("");
		f.setFechaFinCobertura("");
		f.setMostrarReporteImagenesSC(false);
	
		/* INI SA PER-SiCC-2012-0545 */
		f.setCiudadDespacho("");
		f.setVillaPoblacionDespacho("");
		f.setCiudadDomicilio("");
		f.setVillaPoblacionDomicilio("");
		/* FIN SA PER-SiCC-2012-0545 */
		
	
		
	}
	
	
	
	
	
	private String reemplazarNulo(Object obj) {
		if(obj == null)
			return "";
		else
			return (String)obj;
	}
	
	
	public String activarDesativarVistaColumna() {
		this.viewDetalleColumnnasDatatable = !this.viewDetalleColumnnasDatatable;
		if (this.viewDetalleColumnnasDatatable)
		this.viewStringDetalleColumnnasDatatable = "true";
		else
			this.viewStringDetalleColumnnasDatatable = "false";
		return "pm:navbar";
	}
	
	public String find(){
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService) this.getBeanService("scsicc.consultaHIPDatosClienteService");
		limpiar(formBusqueda);
		
		if(StringUtils.isBlank(formBusqueda.getCodigoClienteBuscar()) && 
				StringUtils.isBlank(formBusqueda.getNumeroDocIdentidadBuscar())){
			addInfo("Debe ingresar al menos un criterio de búsqueda!", "PM Messages");
			return "pm:navbar";
		}
		
		Map criterios = new HashMap();
		criterios.put("codigoPais", this.currentUser.getPais().getCodigo());
		criterios.put("codigoCliente", formBusqueda.getCodigoClienteBuscar());
		criterios.put("oidIdioma", this.currentUser.getIdioma().getCodigo());
		criterios.put("numeroDocIdentidad", formBusqueda.getNumeroDocIdentidadBuscar());
		
		//obtenemos la cantidad de clientes con el codigo de cliente y numero de documento como filtro
		List resultado = service.getClientesByCriteria(criterios);
		
		/* Obteniendo los datos generales del Cliente */
		Map mapDatosGenerales = service.getDatosGenerales(criterios);
		
	
		
		if(mapDatosGenerales != null) {
		
			//Recuperamos el pais logueado
			formBusqueda.setCodigoPais(this.currentUser.getCodigoPais());
			
			//Recuperamos el idioma		
			formBusqueda.setOidIdioma(this.currentUser.getCodigoIdioma());
			
			//Si el pais es Colombia, se muestra el campo Barrio
			if(formBusqueda.getCodigoPais().substring(0,2).equals(Constants.HIP_PAIS_COLOMBIA)) {
				formBusqueda.setMostrarBarrio(true);
			} else {
				formBusqueda.setMostrarBarrio(false);
			}		
			
			this.setViewResults(true);
			formBusqueda.setOidCliente(reemplazarNulo(mapDatosGenerales.get("oidCliente")));
			formBusqueda.setCodigoCliente(reemplazarNulo(mapDatosGenerales.get("codigoCliente")));
			
			//concatenando nombre completo
			StringBuilder fullName = new StringBuilder();
			fullName.append(reemplazarNulo(mapDatosGenerales.get("nombre1")).concat(" "));
			fullName.append(reemplazarNulo(mapDatosGenerales.get("nombre2")).concat(" "));
			fullName.append(reemplazarNulo(mapDatosGenerales.get("apellido1")).concat(" "));
			fullName.append(reemplazarNulo(mapDatosGenerales.get("apellido2")));
			
			formBusqueda.setNombreCompleto(fullName.toString());
			formBusqueda.setNumeroDocIdentidad(reemplazarNulo(mapDatosGenerales.get("numeroDocIdentidad")));
			formBusqueda.setTipoDocIdentidad(reemplazarNulo(mapDatosGenerales.get("tipoDocIdentidad")));
			formBusqueda.setEstatus(reemplazarNulo(mapDatosGenerales.get("estatus")));
			formBusqueda.setTelefono(reemplazarNulo(mapDatosGenerales.get("telefono")));
			formBusqueda.setEmail(reemplazarNulo(mapDatosGenerales.get("email")));
			formBusqueda.setFechaIngreso(reemplazarNulo(mapDatosGenerales.get("fechaIngreso")));
			formBusqueda.setPeriodoIngreso(reemplazarNulo(mapDatosGenerales.get("periodoIngreso")));
			formBusqueda.setFechaNacimiento(reemplazarNulo(mapDatosGenerales.get("fechaNacimiento")));
			formBusqueda.setCodigoDigitoControl(reemplazarNulo(mapDatosGenerales.get("codigoDigitoControl")));
			//this.setNroColumnas((formBusqueda.getCodigoDigitoControl()!=null)?"8":"7");			
			formBusqueda.setIndicadorActivo(reemplazarNulo(mapDatosGenerales.get("indicadorActivo")));
			formBusqueda.setNivelRiesgo(reemplazarNulo(mapDatosGenerales.get("nivelRiesgo")));
			//ini sb numero contaro
			formBusqueda.setNumeroContrato(reemplazarNulo(mapDatosGenerales.get("numeroContrato")));
			//fin sb numero de contrato
			criterios.put("oidCliente", formBusqueda.getOidCliente());
			criterios.put("oidNivelRiesgo", mapDatosGenerales.get("oidNivelRiesgo"));
			criterios.put("codigoCliente", mapDatosGenerales.get("codigoCliente"));
			
			//construimos el objeto que estara en sesion, disponible para las demas pantallas
			ConsultaHIPDatosCliente dtoDatosCliente = new ConsultaHIPDatosCliente();
			dtoDatosCliente.setCodigoPais(formBusqueda.getCodigoPais());
			dtoDatosCliente.setOidIdioma(formBusqueda.getOidIdioma());
			dtoDatosCliente.setOidCliente(formBusqueda.getOidCliente());
			dtoDatosCliente.setCodigoCliente(formBusqueda.getCodigoCliente());
			dtoDatosCliente.setNombreCompleto(formBusqueda.getNombreCompleto());
			dtoDatosCliente.setTipoDocIdentidad(formBusqueda.getTipoDocIdentidad());
			dtoDatosCliente.setNumeroDocIdentidad(formBusqueda.getNumeroDocIdentidad());
			dtoDatosCliente.setStatus(formBusqueda.getEstatus());
			dtoDatosCliente.setCampanaIngreso(formBusqueda.getPeriodoIngreso());
			dtoDatosCliente.setTelefonoCasa(formBusqueda.getTelefono());
			dtoDatosCliente.setTelefonoCelular(reemplazarNulo(mapDatosGenerales.get("celular")));
			dtoDatosCliente.setMail(formBusqueda.getEmail());
			dtoDatosCliente.setFechaNacimiento(formBusqueda.getFechaNacimiento());
			dtoDatosCliente.setApellido1(reemplazarNulo(mapDatosGenerales.get("apellido1")));
			dtoDatosCliente.setApellido2(reemplazarNulo(mapDatosGenerales.get("apellido2")));
			dtoDatosCliente.setNombre1(reemplazarNulo(mapDatosGenerales.get("nombre1")));
			dtoDatosCliente.setNombre2(reemplazarNulo(mapDatosGenerales.get("nombre2")));
			dtoDatosCliente.setOidTipoDocIdentidad(reemplazarNulo(mapDatosGenerales.get("oidTipoDocIdentidad")));
			dtoDatosCliente.setIndicadorActivo(formBusqueda.getIndicadorActivo());
			
			//obtenemos el oidPais
			MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService)this.getBeanService("spusicc.mantenimientoMAEClienteService");
    		//criterios.put("codigoPais", this.mPantallaPrincipalBean.getCountryCode());
			criterios.put("codigoPais", this.currentUser.getPais().getCodigo());
    		dtoDatosCliente.setOidPais(clienteService.getOidPais(criterios));
    		
    		//Obtenemos la direccion de domicilio de la consultora
			Map mapDireccionDomicilio = service.getDireccionDomicilio(criterios);
			if(mapDireccionDomicilio != null) {
				String direccion = reemplazarNulo(mapDireccionDomicilio.get("tipoVia")) + " " +
								   reemplazarNulo(mapDireccionDomicilio.get("nombreVia")) + " " +
								   reemplazarNulo(mapDireccionDomicilio.get("numero")) + " " + 
								   reemplazarNulo(mapDireccionDomicilio.get("observaciones")) + " Territorio: " +
								   reemplazarNulo(mapDireccionDomicilio.get("codigoTerritorio"));
				
				formBusqueda.setDireccionDomicilio(direccion);
				formBusqueda.setUbigeoDomicilio(reemplazarNulo(mapDireccionDomicilio.get("ubigeo")));
				formBusqueda.setBarrioDomicilio(reemplazarNulo(mapDireccionDomicilio.get("barrio")));
				
				/* INI SA PER-SiCC-2012-0545 */
				String ciudadDomicilio = reemplazarNulo(mapDireccionDomicilio.get("ciudad"));
				if(formBusqueda.isMostrarCiudad() &&  ciudadDomicilio.length() > 0)
					formBusqueda.setUbigeoDomicilio(formBusqueda.getUbigeoDomicilio() + "/" + ciudadDomicilio);
				
				String villaPoblacionDomicilio = reemplazarNulo(mapDireccionDomicilio.get("villaPoblacion"));
				if(formBusqueda.isMostrarVillaPoblacion() &&  villaPoblacionDomicilio.length() > 0)
					formBusqueda.setUbigeoDomicilio(formBusqueda.getUbigeoDomicilio() + "/" + villaPoblacionDomicilio);
				/* FIN SA PER-SiCC-2012-0545 */
			}	

			//Obtenemos la direccion de despacho de la consultora
			Map mapDireccionDespacho = service.getDireccionDespacho(criterios);
			if(mapDireccionDespacho != null) {
				String direccion = reemplazarNulo(mapDireccionDespacho.get("tipoVia")) + " " +
								   reemplazarNulo(mapDireccionDespacho.get("nombreVia")) + " " +
								   reemplazarNulo(mapDireccionDespacho.get("numero")) + " " + 
								   reemplazarNulo(mapDireccionDespacho.get("observaciones")) + " Territorio: " +
								   reemplazarNulo(mapDireccionDespacho.get("codigoTerritorio"));
				
				formBusqueda.setDireccionDespacho(direccion);
				formBusqueda.setUbigeoDespacho(reemplazarNulo(mapDireccionDespacho.get("ubigeo")));
				formBusqueda.setBarrioDespacho(reemplazarNulo(mapDireccionDespacho.get("barrio")));
				
				/* INI SA PER-SiCC-2012-0545 */
				String ciudadDespacho = reemplazarNulo(mapDireccionDespacho.get("ciudad"));
				if(formBusqueda.isMostrarCiudad() &&  ciudadDespacho.length() > 0)
					formBusqueda.setUbigeoDespacho(formBusqueda.getUbigeoDespacho() + "/" + ciudadDespacho);
				
				String villaPoblacionDespacho = reemplazarNulo(mapDireccionDespacho.get("villaPoblacion"));
				if(formBusqueda.isMostrarVillaPoblacion() &&  villaPoblacionDespacho.length() > 0)
					formBusqueda.setUbigeoDespacho(formBusqueda.getUbigeoDespacho() + "/" + villaPoblacionDespacho);
				/* FIN SA PER-SiCC-2012-0545 */
			}
			
			//obtenemos el motivo del bloqueo de la consultora
			String bloqueo = reemplazarNulo(service.getMotivoBloqueo(criterios));
			if(!bloqueo.equals("")) {
				StringTokenizer st = new StringTokenizer(bloqueo, "__");
				dtoDatosCliente.setCodigoBloqueo(st.nextToken());
				dtoDatosCliente.setBloqueo(st.nextToken());
				formBusqueda.setBloqueo(dtoDatosCliente.getBloqueo());
			} else
				formBusqueda.setBloqueo("");
			
			//obtenemos los impedimentis
			formBusqueda.setImpedidaPasarCdr(StringUtils.isNotEmpty(service.getImpedidaPasarCdr(criterios))?Constants.NUMERO_UNO:Constants.NUMERO_CERO);
			formBusqueda.setImpedidaPasarPedido(StringUtils.isNotEmpty(service.getImpedidaPasarPedido(criterios))?Constants.NUMERO_UNO:Constants.NUMERO_CERO);
			
			//obtenemos la fecha castigada
			formBusqueda.setFechaCastigada(reemplazarNulo(service.getFechaCastigada(criterios)));
			
			//Obtenemos los datos del ultimo pedido de la Consultora
			Map mapUltimoPedido = service.getUltimoPedido(criterios);
			if(mapUltimoPedido != null) {
				formBusqueda.setPeriodoUltimoPedido(reemplazarNulo(mapUltimoPedido.get("periodoUltimoPedido")));
				formBusqueda.setBoletaUltimoPedido(reemplazarNulo(mapUltimoPedido.get("boletaUltimoPedido")));
				formBusqueda.setImporteUltimoPedido(reemplazarNulo(mapUltimoPedido.get("importeUltimoPedido")));
				
				dtoDatosCliente.setPeriodoUltimoPedido(reemplazarNulo(mapUltimoPedido.get("periodoUltimoPedido")));
			}
			
			//obtenemos el saldo unico de la consultora
			formBusqueda.setSaldoUnicoUltimoPedido(reemplazarNulo(service.getSaldoUnico(criterios)));
			dtoDatosCliente.setSaldoUnico(formBusqueda.getSaldoUnicoUltimoPedido());
			
			//obtenemos las unidades administrativas de la consultora
			List listUnidadesAdministrativas = service.getUnidadesAdministrativas(criterios);
			this.setHipUnidadesAdministrativasList(listUnidadesAdministrativas);
			
			if(listUnidadesAdministrativas!=null && listUnidadesAdministrativas.size()>0) {
				
				for(int i=0; i<listUnidadesAdministrativas.size(); i++) {
					Map mapUnidadAdministrativa = (Map)listUnidadesAdministrativas.get(i);
					
					String indicadorActividad = reemplazarNulo(mapUnidadAdministrativa.get("indicadorActividad"));

					if(indicadorActividad.equals("1")) {
						dtoDatosCliente.setCodigoMarca(reemplazarNulo(mapUnidadAdministrativa.get("codigoMarca")));
						dtoDatosCliente.setCodigoCanal(reemplazarNulo(mapUnidadAdministrativa.get("codigoCanal")));
						dtoDatosCliente.setCodigoSubGerencia(reemplazarNulo(mapUnidadAdministrativa.get("codigoSubGerencia")));
						dtoDatosCliente.setCodigoRegion(reemplazarNulo(mapUnidadAdministrativa.get("codigoRegion")));
						dtoDatosCliente.setCodigoZona(reemplazarNulo(mapUnidadAdministrativa.get("codigoZona")));
						dtoDatosCliente.setCodigoSeccion(reemplazarNulo(mapUnidadAdministrativa.get("codigoSeccion")));
						dtoDatosCliente.setCodigoTerritorio(reemplazarNulo(mapUnidadAdministrativa.get("codigoTerritorio")));
						
						dtoDatosCliente.setDescripcionRegion(reemplazarNulo(mapUnidadAdministrativa.get("descripcionRegion")));
						dtoDatosCliente.setDescripcionZona(reemplazarNulo(mapUnidadAdministrativa.get("descripcionZona")));
						dtoDatosCliente.setDescripcionSeccion(reemplazarNulo(mapUnidadAdministrativa.get("descripcionSeccion")));
						
						//////////////////////////////////////////////////////////
						GenericoService genericoService0 = (GenericoService) getBean("genericoService");
						ParametroPais parametroPais0 = new ParametroPais();
						
						parametroPais0.setCodigoPais(formBusqueda.getCodigoPais());
						parametroPais0.setCodigoSistema(Constants.HIP_CODIGO_SISTEMA);
						parametroPais0.setCodigoParametro(Constants.HIP_CODIGO_PARAMETRO_DATOS_BUZON);
						parametroPais0.setIndicadorActivo(Constants.ESTADO_ACTIVO);
						
						List lstParametros = genericoService0.getParametrosPais(parametroPais0);
						
						if(lstParametros != null && lstParametros.size() > 0){
							ParametroPais param = (ParametroPais)lstParametros.get(0);
							
							if(StringUtils.equals(param.getValorParametro(), Constants.ESTADO_ACTIVO)){
								
								this.setLstParametros(lstParametros);
								
								Map map = new HashMap();
								map.put("codigoRegion", MapUtils.getString(mapUnidadAdministrativa, "codigoRegion"));
								map.put("codigoZona", MapUtils.getInteger(mapUnidadAdministrativa, "codigoZona"));
								map.put("codigoTerritorio", MapUtils.getInteger(mapUnidadAdministrativa, "codigoTerritorio"));
								map.put("codigoSeccion", MapUtils.getString(mapUnidadAdministrativa, "codigoSeccion"));
								List lstCoberturaCentroAcopio = service.getListCoberturaCentroAcopio(map);
								
								if(lstCoberturaCentroAcopio != null && lstCoberturaCentroAcopio.size() > 0)
								{
									Map result =(Map)lstCoberturaCentroAcopio.get(0);

									formBusqueda.setCodigoBuzon(MapUtils.getString(result, "codigoBuzon", ""));
									formBusqueda.setDireccionBuzon(MapUtils.getString(result, "direccionBuzon", ""));
									formBusqueda.setTelefonoBuzon(MapUtils.getString(result, "telefonoBuzon", ""));
									formBusqueda.setCelularBuzon(MapUtils.getString(result, "celularBuzon", ""));
									formBusqueda.setDescripcionBuzon(MapUtils.getString(result, "descripcionBuzon", ""));
								}
							}
						}
						
						////////////////////////////////////////////////////////
						
						
						//OBTENER GERENTE ZONA
						Map criteria = new HashMap();
						criteria.put("codZona", reemplazarNulo(mapUnidadAdministrativa.get("codigoZona")));
						criteria.put("codSeccion", reemplazarNulo(mapUnidadAdministrativa.get("codigoSeccion")));
						
						List listGerenteZona = service.getGerenteZonaList(criteria);		
						
						if(listGerenteZona != null && listGerenteZona.size() > 0) {
							Map mapGerenteZona = (Map)listGerenteZona.get(0);
							
							String nombreCompletoGerenteZona =
									reemplazarNulo(mapGerenteZona.get("apellido1"))+" "+reemplazarNulo(mapGerenteZona.get("apellido2"))+", "+
									reemplazarNulo(mapGerenteZona.get("nombre1"))+" "+reemplazarNulo(mapGerenteZona.get("nombre2")); 
							
							dtoDatosCliente.setNombreCompletoGerenteZona(nombreCompletoGerenteZona);
							dtoDatosCliente.setCelularGerenteZona(reemplazarNulo(mapGerenteZona.get("celular")));
							
							formBusqueda.setGerenteZona(dtoDatosCliente.getNombreCompletoGerenteZona());
							formBusqueda.setCelularGerenteZona(dtoDatosCliente.getCelularGerenteZona());
						}
						//--------------------
						
						//OBTENER LIDER SECCION
						List listLiderSeccion = service.getLiderSeccionList(criteria);
						
						if(listLiderSeccion != null && listLiderSeccion.size() > 0) {
							Map mapLiderSeccion = (Map)listLiderSeccion.get(0);
							
							String nombreCompletoLiderSeccion = 
									reemplazarNulo(mapLiderSeccion.get("apellido1"))+" "+reemplazarNulo(mapLiderSeccion.get("apellido2"))+", "+
									reemplazarNulo(mapLiderSeccion.get("nombre1"))+" "+reemplazarNulo(mapLiderSeccion.get("nombre2"));
							
							dtoDatosCliente.setNombreCompletoLiderSeccion(nombreCompletoLiderSeccion);
							dtoDatosCliente.setCelularLiderSeccion(reemplazarNulo(mapLiderSeccion.get("celular")));
							
							formBusqueda.setLiderSeccion(dtoDatosCliente.getNombreCompletoLiderSeccion());
							formBusqueda.setCelularLiderSeccion(dtoDatosCliente.getCelularLiderSeccion());
						}
						//--------------------
						
						break;
					}
				}
			}

//			this.setHipDtoDatosCliente(dtoDatosCliente);
						
			//obtenemos el saldo a pagar y Pagos posteriores
			criterios.put("codigoMarca", dtoDatosCliente.getCodigoMarca());
			criterios.put("codigoCanal", dtoDatosCliente.getCodigoCanal());
			criterios.put("codigoCliente", dtoDatosCliente.getCodigoCliente());
			criterios.put("codigoRegion", dtoDatosCliente.getCodigoRegion());
			criterios.put("codigoZona", dtoDatosCliente.getCodigoZona());
			criterios.put("codigoPeriodo", formBusqueda.getPeriodoUltimoPedido());
			
			try {
				formBusqueda.setSaldoPagarUltimoPedido(service.getSaldoPagar(criterios));
				
				try {
					formBusqueda.setPagosPosterioresUltimoPedido(String.valueOf(Double.parseDouble(formBusqueda.getSaldoUnicoUltimoPedido()) - 
														 Double.parseDouble(formBusqueda.getSaldoPagarUltimoPedido())));
				} catch (Exception ex) {
					formBusqueda.setPagosPosterioresUltimoPedido("");
				}
			} catch (Exception ex) {
				formBusqueda.setSaldoPagarUltimoPedido("");
			}
			
			//obtenemos el promedio de ventas de las ultimas 5 campañas de la consultora
			if(criterios.get("codigoRegion")!=null)
				formBusqueda.setPromedioVentas(service.getPromedioVentasxCampanhas(criterios));
			
			//obtenemos los medios de comunicacion del cliente
			List listMediosComunicacion = service.getMediosComunicacion(criterios);
			this.setHipMediosContactosList(listMediosComunicacion);
						
			//obtenemos la tipificacion de la consultora (tipoCliente, Subtipocliente, tipoClasificacion, Clasificacion)
			List listTipificacionCliente = service.getTipificacionCliente(criterios);
			agruparTipificaciones(listTipificacionCliente);
			
			//recuperamos los ultimos datos de cuenta corriente, pedidos, reclamos
			obtenerResumenDatosCliente(service, dtoDatosCliente);
			
			//verificamos si el cliente tiene asignado la clasificacion de LOVE
			String clasificacionLove = service.getClasificacionLove(criterios);
			dtoDatosCliente.setClasificacionLove(reemplazarNulo(clasificacionLove));
			
			//Obtenemos el limite de credito asociado al Nivel de Riesgo de la Consejera			
			formBusqueda.setLimiteCredito(service.getLimiteCredito(criterios));

			//Obtenemos los datos del usuario Logueado
//			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser(); 
								
			//recuperamos los datos de Familia Protegida
			Map mapDatosFamilia = service.getDatosFamiliaProtegida(criterios);
			String estadoSolicitudPoliza = reemplazarNulo(mapDatosFamilia.get("estadoSolicitudPoliza"));
			if(!estadoSolicitudPoliza.equalsIgnoreCase("")) {
				String mensaje = this.getResourceMessage(estadoSolicitudPoliza);						
				formBusqueda.setEstadoSolicitudPoliza(mensaje);
			}	
			formBusqueda.setFechaInicioCobertura(reemplazarNulo(mapDatosFamilia.get("fechaInicioCobertua")));
			formBusqueda.setFechaFinCobertura(reemplazarNulo(mapDatosFamilia.get("fechaFinCobertura")));
			
			//validamos si se muestra el icono de reporte de imagenes escaneadas de S/C
			formBusqueda.setMostrarReporteImagenesSC(service.validarImagenesEscaneoSC(dtoDatosCliente.getCodigoCliente()));
			
			//obtenemos las opciones de consulta que podra realizar el usuario logueado			
			String mostrarDigiSimp = this.getMostrarDigitacionSimplificada();					
			
			Map criteria = new HashMap();
			criteria.put("codigoPais", formBusqueda.getCodigoPais());
			criteria.put("codigoUsuario", this.currentUser.getCodigo());
			List listOpciones = service.getOpcionesPermitidas(criteria);
			//validarOpciones(listOpciones, formBusqueda.getCodigoCliente(), mostrarDigiSimp);
			this.setHipOpcionesList(listOpciones);
			
			if(listOpciones!=null && listOpciones.size()>0){
				Base base = (Base)listOpciones.get(0);
				this.opcionConsulta = base.getCodigo();
				this.validarOpcionConsulta(this.opcionConsulta);				
			}
			
			//TIPO CONSULTORA
			GenericoService genericoService = (GenericoService)this.getBeanService("genericoService");					
			ParametroPais parametroPais = new ParametroPais();
			
			parametroPais.setCodigoPais(formBusqueda.getCodigoPais());
			parametroPais.setCodigoSistema(Constants.HIP_CODIGO_SISTEMA);
			parametroPais.setNombreParametro(Constants.HIP_NOMBRE_PARAMETRO_TIPO_CONSULTORA);
			parametroPais.setIndicadorActivo(Constants.ESTADO_ACTIVO);
			
			List parametros = genericoService.getParametrosPais(parametroPais);
			
			String indicadorTipo = Constants.NUMERO_CERO;
			
			if(parametros != null && parametros.size() > 0)
			{
				Map criteria2 = new HashMap(); 
				criteria2.put("codigoPais", reemplazarNulo(formBusqueda.getCodigoPais()));
				criteria2.put("oidCliente", reemplazarNulo(mapDatosGenerales.get("oidCliente")));
				
				String tipoConsultora = service.getTipoConsultora(criteria2); 
				
				if(StringUtils.isNotBlank(tipoConsultora)){
					formBusqueda.setTipoConsultora(tipoConsultora);
				}
				
				ParametroPais p = (ParametroPais)parametros.get(0);
				indicadorTipo = p.getValorParametro();
			}
			this.setIndicadorTipo(indicadorTipo);
					
		}else {
			
			this.setViewResults(false);
			
			//limpiando los datos de la pantalla
			limpiar(formBusqueda);
			/* TODO*/
			/*
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("consultaHIPDatosClienteForm.msg.clienteNoEncontrado"));
			saveErrors(request, messages);
			*/
		}	
		log.debug("Valor de Service: " + service.toString());
		return "pm:navbar";
	}
	
	/**
	 * Valida la opcion de consulta y setea dinamicamente el url de la pagina y el manage de control
	 * @param opcionConsultaValid
	 */
	private void validarOpcionConsulta(String opcionConsultaValid){
		
		if (opcionConsultaValid.equals(Constants.HIP_OPCION_CUENTA_CORRIENTE)){ 
			this.pageOpcionConsulta = this.getResourceMessage("opcionConsulta.page.hip3");
			this.manageOpcionConsulta = "consultaHIPCuentaCorrientesAction";
		}
		
	}
	
	
	/**
	 * Agrupa el tipo/subtipo de clientes y sus clasificaciones de la consultora
	 * 
	 * @param session
	 * @param listTipificacionCliente
	 */
	private void agruparTipificaciones(List listTipificacionCliente) {
		
		List listTipoCliente = new ArrayList();
		List listSubTipoCliente = new ArrayList();
		List listTipoClasificacion = new ArrayList();
		List listClasificacion = new ArrayList();
		
		Base baseVacio = new Base();
		baseVacio.setCodigo("");
		baseVacio.setDescripcion("");
		listTipoCliente.add(baseVacio);
		listSubTipoCliente.add(baseVacio);
		listTipoClasificacion.add(baseVacio);
		listClasificacion.add(baseVacio);
		
		if(listTipificacionCliente!= null && listTipificacionCliente.size() > 0) {
			for(int i=0; i<listTipificacionCliente.size(); i++) {
				Map mapTipificacion = (Map)listTipificacionCliente.get(i);
				
				//formamos la lista de tipos de clientes de la consultora
				Base baseTipoCliente = new Base();
				baseTipoCliente.setCodigo(reemplazarNulo(mapTipificacion.get("tipoCliente")));
				baseTipoCliente.setDescripcion(reemplazarNulo(mapTipificacion.get("tipoCliente")));
				if(!existeElementoLista(listTipoCliente, baseTipoCliente)) {
					listTipoCliente.add(baseTipoCliente);	
				}
								
				//formamos la lista de subtipos de clientes de la consultora
				Base baseSubTipoCliente = new Base();
				baseSubTipoCliente.setCodigo(reemplazarNulo(mapTipificacion.get("tipoCliente")));
				baseSubTipoCliente.setDescripcion(reemplazarNulo(mapTipificacion.get("subtipoCliente")));
				if(!existeElementoLista(listSubTipoCliente, baseSubTipoCliente)) {
					listSubTipoCliente.add(baseSubTipoCliente);	
				}

				//formamos la lista de tipos de clasificaciones de la consultora
				Base baseTipoClasificacion = new Base();
				baseTipoClasificacion.setCodigo(reemplazarNulo(mapTipificacion.get("subtipoCliente")));
				baseTipoClasificacion.setDescripcion(reemplazarNulo(mapTipificacion.get("tipoClasificacion")));
				if(!baseTipoClasificacion.getDescripcion().equals("")) {
					listTipoClasificacion.add(baseTipoClasificacion);
					
					//formamos la lista de clasificaciones de la consultora
					Base baseClasificacion = new Base();
					baseClasificacion.setCodigo(reemplazarNulo(mapTipificacion.get("tipoClasificacion")));
					baseClasificacion.setDescripcion(reemplazarNulo(mapTipificacion.get("clasificacion")));
					listClasificacion.add(baseClasificacion);
				}	
			}
		}
		
		this.setHipTipoClienteList(listTipoCliente);
		this.setHipSubTipoClienteList(listSubTipoCliente);
		this.setHipTipoClasificacionList(listTipoClasificacion);
		this.setHipClasificacionList(listClasificacion);
		
	}
	
	/**
	 * metodo auxiliar que permite verificar si un elemento ya fue ingresado a la lista
	 * 
	 * @param listElementos
	 * @param baseElemento
	 * @return
	 */
	private boolean existeElementoLista(List listElementos, Base baseElemento) {
		boolean existe = false;
		
		for(int i=0; i<listElementos.size(); i++) {
			Base baseAux = (Base)listElementos.get(i);
			
			if((baseAux.getCodigo().equalsIgnoreCase(baseElemento.getCodigo())) &&
			   (baseAux.getDescripcion().equalsIgnoreCase(baseElemento.getDescripcion()))) {
				existe = true;
				break;
			}
		}
		
		return existe;
	}
	
	
	/**
	 * Recuperamos los ultimos movimientos de Cuenta Corriente, Ultimos Pedidos, Ultimos Reclamos,
	 * premios pendientes de despacho, Vinculos y referencias
	 * 
	 * @param service
	 * @param session
	 * @param dtoDatosCliente
	 */
	private void obtenerResumenDatosCliente(ConsultaHIPDatosClienteService service, 
											ConsultaHIPDatosCliente dtoDatosCliente) {
		//obtenemos los reclamos de la consultora
		Map criteria = new HashMap();
		criteria.put("oidCliente", dtoDatosCliente.getOidCliente());
		criteria.put("oidIdioma", dtoDatosCliente.getOidIdioma());
		criteria.put("filas", String.valueOf(HIP_FILAS_ULTIMOS_MOVIMIENTOS+1));
		
		//RECLAMOS
		List listCabeceraReclamos = service.getCabeceraReclamos(criteria);
		this.setHipReclamosCabeceraList(listCabeceraReclamos);
				
		//recorremos los reclamos pa determinar el estado de cada uno de ellos en base a los campos EnviaReclamado y EnviaAtendido
		List listReclamos = this.getHipReclamosCabeceraList(); 
				
		for(int i=0; i<listReclamos.size(); i++) {
			Map mapReclamo = (Map)listReclamos.get(i);
			String enviaReclamado = reemplazarNulo(mapReclamo.get("enviaReclamado"));
			String enviaAtendido = reemplazarNulo(mapReclamo.get("enviaAtendido"));
			
			if(enviaReclamado.equals("") || enviaReclamado.equals("0")) {
				//Queda como esta el campo [estado Reclamo]
			} else {
				if(!enviaAtendido.equals(""))
					mapReclamo.put("estadoReclamo", Constants.HIP_RECLAMOS_ESTADO_FACTURADO);
				else
					mapReclamo.put("estadoReclamo", Constants.HIP_RECLAMOS_ESTADO_PENDIENTE);
			}
		}

		//CUENTA CORRIENTES
		ConsultaCOBTelecobroService serviceCobranza = (ConsultaCOBTelecobroService)this.getBeanService("spusicc.consultaCOBTelecobroService");				
		criteria.put("codigoConsultora", dtoDatosCliente.getCodigoCliente());
		
		List listMovimientosCuentaCorriente = serviceCobranza.getDetalleConsultora(criteria);
		Iterator itCuentaCorriente = listMovimientosCuentaCorriente.iterator();
		List listMovimientosCuentaCorrienteAux = new ArrayList();
				
		int contador = 1;
		while(contador < (HIP_FILAS_ULTIMOS_MOVIMIENTOS + 1) && itCuentaCorriente.hasNext()) {
			listMovimientosCuentaCorrienteAux.add(itCuentaCorriente.next());
			contador = contador + 1;
		}
		this.setHipCuentaCorrientesMovimientosList(listMovimientosCuentaCorrienteAux);
				
		//PEDIDOS
		//Seteo los parametros de la consulta
		criteria.put("codigoCliente", dtoDatosCliente.getCodigoCliente());
		
		Pais pais = this.currentUser.getPais();
				
		ParametroPais parametroPais = new ParametroPais();
		parametroPais.setCodigoPais(pais.getCodigo());
		parametroPais.setCodigoSistema(Constants.CODIGO_SISTEMA_PED);
		parametroPais.setCodigoParametro(Constants.CODIGO_PARAMETRO_CONSULTA_HIP);				
		GenericoService genericoService = (GenericoService) getBean("genericoService");
		List lstParametroPais = genericoService.getParametrosPais(parametroPais);
		if(lstParametroPais.size()!=0){
			for (int i = 0; i < lstParametroPais.size(); i++) {
				ParametroPais pPais = (ParametroPais)lstParametroPais.get(i);
				if (pPais.getNombreParametro().equals("indMostGastoAdminTot")) {
					if(pPais.getValorParametro().equals("1")){
						this.setValPara(true);						
					}else{
						this.setValPara(false);						
					}
					break;
				}
			}
		}else{
			this.setValPara(false);
								
		}
		
		//Obtengo las listas a mostrar
		List pedidosConsultora = service.getPedidosConsultora(criteria);		
		for(int i=0; i<pedidosConsultora.size(); i++){
			Map pedidoConsultora = (Map)pedidosConsultora.get(i);
			Object indicador = pedidoConsultora.get("indicadorChequeo");
			if(indicador!=null){
				String chequeo = (String)indicador;
				if(chequeo.equals("2009")){
					pedidoConsultora.put("indicadorChequeo", this.getResourceMessage("consultaHIPPedidosForm.indicadorSI"));
				}else{
					pedidoConsultora.put("indicadorChequeo", this.getResourceMessage("consultaHIPPedidosForm.indicadorNO"));
				}
			}else{
				pedidoConsultora.put("indicadorChequeo", this.getResourceMessage("consultaHIPPedidosForm.indicadorNO"));
			}
		}
		this.setHipPedidosConsultoraList(pedidosConsultora);
				
		//PREMIOS PENDIENTES DE DESPACHO
		List listBolsaFaltantes = service.getBolsaFaltantes(criteria);
		this.setHipConcursosBolsaFaltantesList(listBolsaFaltantes);
				
		//VINCULOS Y REFERENCIAS
		List listVinculosReferencias = service.getVinculosReferencias(criteria);
		this.setHipVinculosReferenciasList(listVinculosReferencias);
		
	}


	/*GETTERS SETTERS*/
	public List getHipTipoClienteList() {
		return hipTipoClienteList;
	}


	public void setHipTipoClienteList(List hipTipoClienteList) {
		this.hipTipoClienteList = hipTipoClienteList;
	}


	public List getHipSubTipoClienteList() {
		return hipSubTipoClienteList;
	}


	public void setHipSubTipoClienteList(List hipSubTipoClienteList) {
		this.hipSubTipoClienteList = hipSubTipoClienteList;
	}


	public List getHipTipoClasificacionList() {
		return hipTipoClasificacionList;
	}


	public void setHipTipoClasificacionList(List hipTipoClasificacionList) {
		this.hipTipoClasificacionList = hipTipoClasificacionList;
	}


	public List getHipClasificacionList() {
		return hipClasificacionList;
	}


	public void setHipClasificacionList(List hipClasificacionList) {
		this.hipClasificacionList = hipClasificacionList;
	}


	public List getHipMediosContactosList() {
		return hipMediosContactosList;
	}


	public void setHipMediosContactosList(List hipMediosContactosList) {
		this.hipMediosContactosList = hipMediosContactosList;
	}


	public List getHipUnidadesAdministrativasList() {
		return hipUnidadesAdministrativasList;
	}


	public void setHipUnidadesAdministrativasList(
			List hipUnidadesAdministrativasList) {
		this.hipUnidadesAdministrativasList = hipUnidadesAdministrativasList;
	}


	public List getHipOpcionesList() {
		return hipOpcionesList;
	}


	public void setHipOpcionesList(List hipOpcionesList) {
		this.hipOpcionesList = hipOpcionesList;
	}


	public List getHipReclamosCabeceraList() {
		return hipReclamosCabeceraList;
	}


	public void setHipReclamosCabeceraList(List hipReclamosCabeceraList) {
		this.hipReclamosCabeceraList = hipReclamosCabeceraList;
	}


	public List getHipCuentaCorrientesMovimientosList() {
		return hipCuentaCorrientesMovimientosList;
	}


	public void setHipCuentaCorrientesMovimientosList(
			List hipCuentaCorrientesMovimientosList) {
		this.hipCuentaCorrientesMovimientosList = hipCuentaCorrientesMovimientosList;
	}


	public List getHipPedidosConsultoraList() {
		return hipPedidosConsultoraList;
	}


	public void setHipPedidosConsultoraList(List hipPedidosConsultoraList) {
		this.hipPedidosConsultoraList = hipPedidosConsultoraList;
	}


	public List getHipConcursosBolsaFaltantesList() {
		return hipConcursosBolsaFaltantesList;
	}


	public void setHipConcursosBolsaFaltantesList(
			List hipConcursosBolsaFaltantesList) {
		this.hipConcursosBolsaFaltantesList = hipConcursosBolsaFaltantesList;
	}


	public List getHipVinculosReferenciasList() {
		return hipVinculosReferenciasList;
	}


	public void setHipVinculosReferenciasList(List hipVinculosReferenciasList) {
		this.hipVinculosReferenciasList = hipVinculosReferenciasList;
	}


	public List getHipSolicitudesPolizaList() {
		return hipSolicitudesPolizaList;
	}


	public void setHipSolicitudesPolizaList(List hipSolicitudesPolizaList) {
		this.hipSolicitudesPolizaList = hipSolicitudesPolizaList;
	}


	public List getHipHistoricoCargosPolizaList() {
		return hipHistoricoCargosPolizaList;
	}


	public void setHipHistoricoCargosPolizaList(List hipHistoricoCargosPolizaList) {
		this.hipHistoricoCargosPolizaList = hipHistoricoCargosPolizaList;
	}


	public List getLstParametros() {
		return lstParametros;
	}


	public void setLstParametros(List lstParametros) {
		this.lstParametros = lstParametros;
	}


	public MPantallaPrincipalMobileFormBean getFormBusqueda() {
		return formBusqueda;
	}


	public void setFormBusqueda(MPantallaPrincipalMobileFormBean formBusqueda) {
		this.formBusqueda = formBusqueda;
	}


	public String getMostrarDigitacionSimplificada() {
		return mostrarDigitacionSimplificada;
	}


	public void setMostrarDigitacionSimplificada(
			String mostrarDigitacionSimplificada) {
		this.mostrarDigitacionSimplificada = mostrarDigitacionSimplificada;
	}


	public String getIndicadorTipo() {
		return indicadorTipo;
	}


	public void setIndicadorTipo(String indicadorTipo) {
		this.indicadorTipo = indicadorTipo;
	}


	public String getNroColumnas() {
		return nroColumnas;
	}


	public void setNroColumnas(String nroColumnas) {
		this.nroColumnas = nroColumnas;
	}


	public boolean isValPara() {
		return valPara;
	}


	public void setValPara(boolean valPara) {
		this.valPara = valPara;
	}


	public boolean isViewResults() {
		return viewResults;
	}


	public void setViewResults(boolean viewResults) {
		this.viewResults = viewResults;
	}

	public String getOpcionConsulta() {
		return opcionConsulta;
	}


	public void setOpcionConsulta(String opcionConsulta) {
		this.opcionConsulta = opcionConsulta;
	}


	public String getPageOpcionConsulta() {
		return pageOpcionConsulta;
	}


	public void setPageOpcionConsulta(String pageOpcionConsulta) {
		this.pageOpcionConsulta = pageOpcionConsulta;
	}


	public String getManageOpcionConsulta() {
		return manageOpcionConsulta;
	}


	public void setManageOpcionConsulta(String manageOpcionConsulta) {
		this.manageOpcionConsulta = manageOpcionConsulta;
	}


	public String getCountryCode() {
		return countryCode;
	}


	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}


	public String getCodigoUsuario() {
		return codigoUsuario;
	}


	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Boolean getViewDetalleColumnnasDatatable() {
		return viewDetalleColumnnasDatatable;
	}


	public void setViewDetalleColumnnasDatatable(
			Boolean viewDetalleColumnnasDatatable) {
		this.viewDetalleColumnnasDatatable = viewDetalleColumnnasDatatable;
	}


	public String getViewStringDetalleColumnnasDatatable() {
		return viewStringDetalleColumnnasDatatable;
	}


	public void setViewStringDetalleColumnnasDatatable(
			String viewStringDetalleColumnnasDatatable) {
		this.viewStringDetalleColumnnasDatatable = viewStringDetalleColumnnasDatatable;
	}



	

	
	
	
}
