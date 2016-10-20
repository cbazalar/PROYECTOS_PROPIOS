package biz.belcorp.ssicc.web.scsicc.hip.action;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scsicc.model.ConsultaHIPDatosCliente;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteHistoricoDatos;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.ConsultaHIPDatosClienteService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.form.ConsultaHIPActualizacionDatosClienteForm;


/**
 * TODO Include class description here.
 * <p>
 * <a href="ConsultaHIPActualizacionDatosClienteAction"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan Altamirano </a>
 * 
 */

@ManagedBean  
@SessionScoped
public class ConsultaHIPActualizacionDatosClienteAction extends BasePopupAbstractAction {	

	private static final long serialVersionUID = 1299469669769195350L;	
		
	@ManagedProperty(value="#{consultaHIPDatosClienteAction}")
	private ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction;
	
	private ClienteHistoricoDatos clienteHistoricoDatos;
	private boolean indicadorMostrarNomApe;
	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaHIPActualizacionDatosClienteForm form = new ConsultaHIPActualizacionDatosClienteForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Metodo que se ejecuta luego que se ejecuta el Constructor de la clase
	 */
	@PostConstruct
	public void view() {
		if (log.isDebugEnabled()) {
            log.debug("Entering 'ConsultaHIPCuentaCorrientesAction - view' method");
        }
		Map<String, String> parametros = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();;
		this.parametrosPantalla = new HashMap<String, String>();
		this.parametrosPantalla.putAll(parametros);
		try {
			this.formBusqueda = this.devuelveFormBusqueda();
		}
		catch (Exception e) {
			
		}
	}

	@Override
	protected void setViewAtributes() throws Exception {
		ConsultaHIPActualizacionDatosClienteForm f = (ConsultaHIPActualizacionDatosClienteForm) this.formBusqueda;
		//HttpSession session = request.getSession(true);
		
		//ConsultaHIPDatosCliente dtoDatosCliente = (ConsultaHIPDatosCliente)session.getAttribute(Constants.HIP_DTO_DATOS_CLIENTE);
		ConsultaHIPDatosCliente dtoDatosCliente = this.consultaHIPDatosClienteAction.getHipDtoDatosCliente();
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		MantenimientoMAEClienteService serviceMae = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		
		f.setCodCliente(dtoDatosCliente.getCodigoCliente());
		f.setNomCliente(dtoDatosCliente.getNombreCompleto());
		f.setDesRegZonTerri(dtoDatosCliente.getDescripcionRegion() + " / " + dtoDatosCliente.getDescripcionZona() 
							+ " / " + dtoDatosCliente.getCodigoTerritorio());
		
		Map criterios = new HashMap();
		criterios.put("codigoPais", dtoDatosCliente.getCodigoPais());
		criterios.put("codigoCliente", dtoDatosCliente.getCodigoCliente());
		criterios.put("oidIdioma", dtoDatosCliente.getOidIdioma());
		
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");
		Map mapDatosGenerales = service.getDatosGenerales(criterios);

		f.setPrimerApellido(MapUtils.getString(mapDatosGenerales, "apellido1", "")); 
		f.setSegundoApellido(MapUtils.getString(mapDatosGenerales, "apellido2", "")); 
		f.setPrimerNombre(MapUtils.getString(mapDatosGenerales, "nombre1", "")); 
		f.setSegundoNombre(MapUtils.getString(mapDatosGenerales, "nombre2", ""));
			
		f.setTelefono(MapUtils.getString(mapDatosGenerales, "telefono", ""));  
		f.setCelular(MapUtils.getString(mapDatosGenerales, "celular", ""));
		f.setEmail(MapUtils.getString(mapDatosGenerales, "email", ""));
		String fechaNac = MapUtils.getString(mapDatosGenerales, "fechaNacimiento", "");
		f.setFechaNacimiento(null);
		if (StringUtils.isNotBlank(fechaNac)) {
			f.setFechaNacimiento(DateUtil.convertStringToDate(fechaNac));
		}
		f.setDocumento(dtoDatosCliente.getNumeroDocIdentidad());
		
		//Validarores
		String validarCaractererNV1 = clienteService.getValorModuloxPaisTipoValidacion(dtoDatosCliente.getCodigoPais(), 
									Constants.MAE_VALID_CARACTER_NOVALIDO1);
		String validarCaractererNV2 = clienteService.getValorModuloxPaisTipoValidacion(dtoDatosCliente.getCodigoPais(), 
									Constants.MAE_VALID_CARACTER_NOVALIDO2);
		String validarCaractererNV3 = clienteService.getValorModuloxPaisTipoValidacion(dtoDatosCliente.getCodigoPais(), 
									Constants.MAE_VALID_CARACTER_NOVALIDO3);
		
		if(validarCaractererNV1 != null) {
			f.setValidarCaracteres1(validarCaractererNV1);
			f.setCadenaCaracteresV1(clienteService.getCaracteresxModuloValidacion(Constants.MAE_VALID_CARACTER_NOVALIDO1, "S"));
			f.setCadenaCaracteresNV1(clienteService.getCaracteresxModuloValidacion(Constants.MAE_VALID_CARACTER_NOVALIDO1, "N"));
		}
		if(validarCaractererNV2 != null) {
			f.setValidarCaracteres2(validarCaractererNV2);
			f.setCadenaCaracteresV2(clienteService.getCaracteresxModuloValidacion(Constants.MAE_VALID_CARACTER_NOVALIDO2, "S"));
			f.setCadenaCaracteresNV2(clienteService.getCaracteresxModuloValidacion(Constants.MAE_VALID_CARACTER_NOVALIDO2, "N"));
		}
		if(validarCaractererNV3 != null) {
			f.setValidarCaracteres3(validarCaractererNV3);
			f.setCadenaCaracteresV3(clienteService.getCaracteresxModuloValidacion(Constants.MAE_VALID_CARACTER_NOVALIDO3, "S"));
			f.setCadenaCaracteresNV3(clienteService.getCaracteresxModuloValidacion(Constants.MAE_VALID_CARACTER_NOVALIDO3, "N"));
		}
		
		criterios.put("oidSubTipoCliente", service.getOidSubtipoCliente(criterios));
		criterios.put("oidPais", clienteService.getOidPais(criterios));
		
		//seteamos la edad minima y maxima 
        f.setEdadMinima(clienteService.getEdadMinima(criterios));
        f.setEdadMaxima(clienteService.getEdadMaxima(criterios));
		
		f.setGraboOK(false);
		
		//Obtenemos los datos iniciales del Cliente
		clienteHistoricoDatos = new ClienteHistoricoDatos();
		clienteHistoricoDatos.setCodigoCliente(f.getCodCliente());
		clienteHistoricoDatos.setCodigoPais(dtoDatosCliente.getCodigoPais());
		clienteHistoricoDatos.setPrimerApellidoAnterior(f.getPrimerApellido());
		clienteHistoricoDatos.setSegundoApellidoAnterior(f.getSegundoApellido());
		clienteHistoricoDatos.setPrimerNombreAnterior(f.getPrimerNombre());
		clienteHistoricoDatos.setSegundoNombreAnterior(f.getSegundoNombre());
		clienteHistoricoDatos.setFechaNacimientoAnterior(DateUtil.convertDateToString(f.getFechaNacimiento()));
		clienteHistoricoDatos.setNumeroDocumentoAnterior(dtoDatosCliente.getNumeroDocIdentidad());

		clienteHistoricoDatos.setTelefonoCelularAnterior(f.getCelular());
		clienteHistoricoDatos.setTelefonoFijoAnterior(f.getTelefono());
		clienteHistoricoDatos.setEmailAnterior(f.getEmail());
		
		clienteHistoricoDatos.setIndicadorOrigen("CH");
		//session.setAttribute(HIP_HISTORICO_DATOS_CLIENTE, clienteHistoricoDatos);
		
		//Mostrar Nombres y Apellidos - PER-SiCC-2015-0187
		GenericoService genericoService = (GenericoService) getBean("genericoService");
		
		ParametroPais parametroPaisNomApe = new ParametroPais();
		parametroPaisNomApe.setCodigoPais(dtoDatosCliente.getCodigoPais());
		parametroPaisNomApe.setCodigoSistema(Constants.HIP_CODIGO_SISTEMA);
		parametroPaisNomApe.setNombreParametro(Constants.HIP_NOMBRE_PARAMETRO_MOSTRAR_NOMAPE_CLIENTE);
		parametroPaisNomApe.setIndicadorActivo(Constants.ESTADO_ACTIVO);
		
		List parametrosNomApe = genericoService.getParametrosPais(parametroPaisNomApe);
		
		String indicadorMostrarNomApe = Constants.NUMERO_CERO;
		
		if(parametrosNomApe != null && parametrosNomApe.size() > 0){
			ParametroPais p = (ParametroPais)parametrosNomApe.get(0);
			indicadorMostrarNomApe = p.getValorParametro();
		}
		//session.setAttribute("indicadorMostrarNomApe", indicadorMostrarNomApe);
		this.setIndicadorMostrarNomApe(StringUtils.equals(indicadorMostrarNomApe, Constants.NUMERO_UNO) ? false : true);
		//Mostrar Nombres y Apellidos - PER-SiCC-2015-0187
		
	}
	
	public void save() throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entering 'save' method");
		//HttpSession session = request.getSession(true);
		
		try{
		ConsultaHIPActualizacionDatosClienteForm f = (ConsultaHIPActualizacionDatosClienteForm) this.formBusqueda;
		ConsultaHIPDatosClienteService consultaHIPDatosClienteService = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");
		ConsultaHIPDatosCliente dtoDatosCliente = this.consultaHIPDatosClienteAction.getHipDtoDatosCliente();
		
		//Obtenemos los datos del usuario Logueado
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
				
		Map params = new HashMap();
		params.put("oidCliente", dtoDatosCliente.getOidCliente());
		params.put("primerApellido", f.getPrimerApellido());
		params.put("segundoApellido", f.getSegundoApellido()); 
		params.put("primerNombre", f.getPrimerNombre());
		params.put("segundoNombre", f.getSegundoNombre());
		params.put("telefonoFijo", f.getTelefono());
		params.put("telefonoCelular", f.getCelular());
		params.put("email", f.getEmail());
		params.put("fechaNacimiento", DateUtil.convertDateToString(f.getFechaNacimiento()));
		params.put("codigoUsuario", usuario.getLogin());

		//guardamos historial de cambio de datos del cliente
		//ClienteHistoricoDatos clienteHistoricoDatos = new ClienteHistoricoDatos();
		clienteHistoricoDatos.setPrimerApellido(f.getPrimerApellido());
		clienteHistoricoDatos.setSegundoApellido(f.getSegundoApellido());
		clienteHistoricoDatos.setPrimerNombre(f.getPrimerNombre());
		clienteHistoricoDatos.setSegundoNombre(f.getSegundoNombre());
		clienteHistoricoDatos.setFechaNacimiento(DateUtil.convertDateToString(f.getFechaNacimiento()));
		clienteHistoricoDatos.setNumeroDocumento(dtoDatosCliente.getNumeroDocIdentidad());

		clienteHistoricoDatos.setTelefonoCelular(f.getCelular());
		clienteHistoricoDatos.setTelefonoFijo(f.getTelefono());
		clienteHistoricoDatos.setEmail(f.getEmail());
		
		clienteHistoricoDatos.setCodigoUsuario(usuario.getLogin());
		params.put("clienteHistoricoDatos", clienteHistoricoDatos);
		
		consultaHIPDatosClienteService.executeActualizarDatosCliente(params);
		
		/*ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("consultaHIPActualizacionDatosClienteForm.msg.graboOk"));
		saveMessages(request.getSession(), messages);
		f.setGraboOK(true);*/
		
		String msgConfirm = this.getResourceMessage("consultaHIPActualizacionDatosClienteForm.msg.graboOk");
		this.addInfo("Mensaje.", msgConfirm);
		
		}catch(Exception e){
			log.debug("Error al grabar. "+ e.getMessage());
			e.printStackTrace();
			this.addError("Error al grabar.", e.getMessage());
		}
	}
	
	public void save2(ActionEvent event) throws Exception{
		if (log.isDebugEnabled()){
			log.debug("Entering 'save2' method");
		}
		boolean activarCerrar = false;
		try {
			ConsultaHIPActualizacionDatosClienteForm f = (ConsultaHIPActualizacionDatosClienteForm) this.formBusqueda;
			ConsultaHIPDatosClienteService consultaHIPDatosClienteService = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");
			ConsultaHIPDatosCliente dtoDatosCliente = this.consultaHIPDatosClienteAction.getHipDtoDatosCliente();
			
			//Obtenemos los datos del usuario Logueado
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			
			Map params = new HashMap();
			params.put("oidCliente", dtoDatosCliente.getOidCliente());
			params.put("primerApellido", f.getPrimerApellido());
			params.put("segundoApellido", f.getSegundoApellido()); 
			params.put("primerNombre", f.getPrimerNombre());
			params.put("segundoNombre", f.getSegundoNombre());
			params.put("telefonoFijo", f.getTelefono());
			params.put("telefonoCelular", f.getCelular());		
			params.put("email", f.getEmail());
			params.put("fechaNacimiento", DateUtil.convertDateToString(f.getFechaNacimiento()));
			params.put("codigoUsuario", usuario.getLogin());
	
			//guardamos historial de cambio de datos del cliente
			//ClienteHistoricoDatos clienteHistoricoDatos = new ClienteHistoricoDatos();
			clienteHistoricoDatos.setPrimerApellido(f.getPrimerApellido());
			clienteHistoricoDatos.setSegundoApellido(f.getSegundoApellido());
			clienteHistoricoDatos.setPrimerNombre(f.getPrimerNombre());
			clienteHistoricoDatos.setSegundoNombre(f.getSegundoNombre());
			clienteHistoricoDatos.setFechaNacimiento(DateUtil.convertDateToString(f.getFechaNacimiento()));
			clienteHistoricoDatos.setNumeroDocumento(dtoDatosCliente.getNumeroDocIdentidad());
	
			clienteHistoricoDatos.setTelefonoCelular(f.getCelular());
			clienteHistoricoDatos.setTelefonoFijo(f.getTelefono());
			clienteHistoricoDatos.setEmail(f.getEmail());
			
			clienteHistoricoDatos.setCodigoUsuario(usuario.getLogin());
			params.put("clienteHistoricoDatos", clienteHistoricoDatos);
			
			consultaHIPDatosClienteService.executeActualizarDatosCliente(params);
			
			String msgConfirm = this.getResourceMessage("consultaHIPActualizacionDatosClienteForm.msg.graboOk");
			this.addInfo("Mensaje.", msgConfirm);
			//this.consultaHIPDatosClienteAction.find();
			activarCerrar = true;
		}
		catch(Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			
		}
		this.getRequestContext().addCallbackParam("activarCerrar", activarCerrar);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {
		
		String mensaje = null;
		ConsultaHIPActualizacionDatosClienteForm f = (ConsultaHIPActualizacionDatosClienteForm) this.formBusqueda;
		
		if(StringUtils.equals(accion, "validarEdad"))
		{						
			int edadCliente = getEdadCliente(f); 
			
			if(edadCliente <= 0)
			{
				return this.getResourceMessage("consultaHIPActualizacionDatosClienteForm.msg.error.edad.menorigual.0");
			}
			else if(edadCliente > 99)
			{
				return this.getResourceMessage("consultaHIPActualizacionDatosClienteForm.msg.error.edad.mayor.99");
			}
		}
		
		return mensaje;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setValidarReconfirmar(java.lang.String)
	 */
	@Override
	public String setValidarReconfirmar(String accion) {
		String mensaje = null;
		ConsultaHIPActualizacionDatosClienteForm f = (ConsultaHIPActualizacionDatosClienteForm) this.formBusqueda;
		
		int edadCliente = getEdadCliente(f); 
		
		if(StringUtils.equals(accion, "validarEdad"))
		{
			if(StringUtils.isNotBlank(f.getEdadMinima()) && StringUtils.isNotBlank(f.getEdadMaxima()))
			{					
	  			if(!(edadCliente >= Integer.parseInt(f.getEdadMinima()) && edadCliente <= Integer.parseInt(f.getEdadMaxima()))) 
	  			{
	  				return this.getResourceMessage("consultaHIPActualizacionDatosClienteForm.msg.error.edad.rango");	  				
		  	  	}
				
			}
		}
		
		return mensaje;
	}

	/**
	 * 
	 * @param f
	 * @return
	 */
	private int getEdadCliente(ConsultaHIPActualizacionDatosClienteForm f) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(f.getFechaNacimiento());
		cal.get(cal.YEAR);
		int anioNac = cal.get(cal.YEAR);
		cal.setTime(new Date());
		int anioAct = cal.get(cal.YEAR);
		int edadCliente = anioAct - anioNac;
		return edadCliente;
	}
	
	public ConsultaHIPDatosClienteAction getConsultaHIPDatosClienteAction() {
		return consultaHIPDatosClienteAction;
	}

	public void setConsultaHIPDatosClienteAction(
			ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction) {
		this.consultaHIPDatosClienteAction = consultaHIPDatosClienteAction;
	}

	public ClienteHistoricoDatos getClienteHistoricoDatos() {
		return clienteHistoricoDatos;
	}

	public void setClienteHistoricoDatos(ClienteHistoricoDatos clienteHistoricoDatos) {
		this.clienteHistoricoDatos = clienteHistoricoDatos;
	}

	/**
	 * @return the indicadorMostrarNomApe
	 */
	public boolean isIndicadorMostrarNomApe() {
		return indicadorMostrarNomApe;
	}

	/**
	 * @param indicadorMostrarNomApe the indicadorMostrarNomApe to set
	 */
	public void setIndicadorMostrarNomApe(boolean indicadorMostrarNomApe) {
		this.indicadorMostrarNomApe = indicadorMostrarNomApe;
	}

}
