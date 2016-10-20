package biz.belcorp.ssicc.web.scsicc.hip.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang3.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scsicc.model.ConsultaHIPDatosCliente;
import biz.belcorp.ssicc.dao.spusicc.sto.model.AccionTipoDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.DocumentoDigitadoPK;
import biz.belcorp.ssicc.dao.spusicc.sto.model.GestionDocumento;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.scsicc.ConsultaHIPDatosClienteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOLevantamientoErroresValidacionService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.ProcesoSTOExecutionService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.form.ConsultaHIPActualizacionDuplaCyzoneForm;
import biz.belcorp.ssicc.web.util.StringUtil;


/**
 * TODO Include class description here.
 * <p>
 * <a href="ConsultaHIPCronogramaActividadesAction"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ConsultaHIPActualizacionDuplaCyzoneAction extends BasePopupAbstractAction {

	private static final long serialVersionUID = -7439685740681999227L;
	@ManagedProperty(value="#{consultaHIPDatosClienteAction}")
	private ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction;

	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaHIPActualizacionDuplaCyzoneForm f = new ConsultaHIPActualizacionDuplaCyzoneForm();
		return f;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()){
			log.debug("Entering ConsultaHIPActualizacionDuplaCyzoneAction - 'setFindAttributes' - method");
		}
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
		
		if (log.isDebugEnabled()){
			log.debug("Entering ConsultaHIPActualizacionDuplaCyzoneAction - 'setViewAtributes' - method");
		}
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");
		ConsultaHIPActualizacionDuplaCyzoneForm f = (ConsultaHIPActualizacionDuplaCyzoneForm) this.formBusqueda;
		ConsultaHIPDatosCliente dtoDatosCliente = consultaHIPDatosClienteAction.getHipDtoDatosCliente();
		
		f.setCodConsultora(dtoDatosCliente.getCodigoCliente());
		f.setNomConsultora(dtoDatosCliente.getNombreCompleto());
		f.setDesRegZonTerri(dtoDatosCliente.getDescripcionRegion() + " / " + dtoDatosCliente.getDescripcionZona() + " / " + dtoDatosCliente.getCodigoTerritorio());
		
		limpiar(f);
		
		//obtenemos los Concursos de la consultora
		Map criteria = new HashMap();
		criteria.put("oidCliente", dtoDatosCliente.getOidCliente());
		criteria.put("oidIdioma", dtoDatosCliente.getOidIdioma());
		
		Map mapDuplaActual = service.getDuplaActual(criteria);
		
		if(mapDuplaActual != null) {
			f.setCodigoDupla((String)mapDuplaActual.get("codigoCliente"));
			f.setNombre1Modificar((String)mapDuplaActual.get("nombre1"));
			f.setNombre2Modificar((String)mapDuplaActual.get("nombre2"));
			f.setApellido1Modificar((String)mapDuplaActual.get("apellidoPaterno"));
			f.setApellido2Modificar((String)mapDuplaActual.get("apellidoMaterno"));
			f.setFechaNacimientoModificar(DateUtil.convertStringToDate((String)mapDuplaActual.get("fechaNacimiento")));
			f.setMailModificar((String)mapDuplaActual.get("email"));
			f.setTelefonoModificar((String)mapDuplaActual.get("telefono"));
			f.setCelularModificar((String)mapDuplaActual.get("celular"));
			
			f.setIndicadorModificar("S"); //Actualizar
			f.setHabilitarActualizacion(true);
		} else {
			f.setIndicadorModificar("N"); //Insertar
			f.setHabilitarActualizacion(false);
		}
		
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService)getBean("spusicc.mantenimientoMAEClienteService");	
		//Validarores
		String validarCaractererNV1 = clienteService.getValorModuloxPaisTipoValidacion(dtoDatosCliente.getCodigoPais(), 
									Constants.MAE_VALID_CARACTER_NOVALIDO1);
		String validarCaractererNV2 = clienteService.getValorModuloxPaisTipoValidacion(dtoDatosCliente.getCodigoPais(), 
									Constants.MAE_VALID_CARACTER_NOVALIDO2);
		String validarCaractererNV3 = clienteService.getValorModuloxPaisTipoValidacion(dtoDatosCliente.getCodigoPais(), 
									Constants.MAE_VALID_CARACTER_NOVALIDO3);
		
		if(validarCaractererNV1 != null) {
			f.setValidarCaracteres1(true);
			f.setCadenaCaracteresV1(clienteService.getCaracteresxModuloValidacion(Constants.MAE_VALID_CARACTER_NOVALIDO1, "S"));
			f.setCadenaCaracteresNV1(clienteService.getCaracteresxModuloValidacion(Constants.MAE_VALID_CARACTER_NOVALIDO1, "N"));
		}
		if(validarCaractererNV2 != null) {
			f.setValidarCaracteres2(true);
			f.setCadenaCaracteresV2(clienteService.getCaracteresxModuloValidacion(Constants.MAE_VALID_CARACTER_NOVALIDO2, "S"));
			f.setCadenaCaracteresNV2(clienteService.getCaracteresxModuloValidacion(Constants.MAE_VALID_CARACTER_NOVALIDO2, "N"));
		}
		if(validarCaractererNV3 != null) {
			f.setValidarCaracteres3(true);
			f.setCadenaCaracteresV3(clienteService.getCaracteresxModuloValidacion(Constants.MAE_VALID_CARACTER_NOVALIDO3, "S"));
			f.setCadenaCaracteresNV3(clienteService.getCaracteresxModuloValidacion(Constants.MAE_VALID_CARACTER_NOVALIDO3, "N"));
		}
		
		if(f.isHabilitarActualizacion())
		{
			f.setFlagModificar(true);
			f.setFlagIngresar(false);
		}
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {

		if (log.isDebugEnabled()){
			log.debug("Entering 'setValidarConfirmar' method");
		}
		
		log.debug(accion);
		
		ConsultaHIPActualizacionDuplaCyzoneForm f = (ConsultaHIPActualizacionDuplaCyzoneForm) this.formBusqueda;		
		
		if(f.isHabilitarActualizacion())
		{
			if(!f.isFlagIngresar() && !f.isFlagModificar())
				return this.getResourceMessage("consultaHIPActualizacionDuplaCyzoneForm.msg.validacionesSeleccionar");
			
			if(f.isFlagModificar())
			{
				if(StringUtils.isBlank(f.getApellido1Modificar()) || StringUtils.isBlank(f.getNombre1Modificar()) || f.getFechaNacimientoModificar() == null)
				{
					return this.getResourceMessage("consultaHIPActualizacionDuplaCyzoneForm.msg.validacionesModificar");
				}
				
				f.setIndicadorModificar(Constants.SI);
			}
			
			if(f.isFlagIngresar())
			{
				if(StringUtils.isBlank(f.getApellido1Ingresar()) || StringUtils.isBlank(f.getNombre1Ingresar()) || f.getFechaNacimientoIngresar() == null)
				{
					return this.getResourceMessage("consultaHIPActualizacionDuplaCyzoneForm.msg.validacionesIngresar");
				}
				
				f.setIndicadorModificar(Constants.NO);
			}
		}
		else
		{
			if(StringUtils.isBlank(f.getApellido1Ingresar()) || StringUtils.isBlank(f.getNombre1Ingresar()) || f.getFechaNacimientoIngresar() == null)
			{
				return this.getResourceMessage("consultaHIPActualizacionDuplaCyzoneForm.msg.validacionesIngresar");
			}
			
			f.setIndicadorModificar(Constants.NO);			
		}
		
		boolean insercion = true;
		
		if(f.isHabilitarActualizacion() && f.isFlagModificar())
			insercion = false;
		
		
		String validaciones = validarCamposEntradaCaracteres1(insercion);
		
		if(StringUtils.isBlank(validaciones))
		{
			validaciones = validarCamposEntradaCaracteres2(insercion);
			
			if(StringUtils.isBlank(validaciones))
				validaciones = validarCamposEntradaCaracteres3(insercion);			
		}
		
		return validaciones;
		
	}
	
	
	/**
	 * 
	 * @param insercion
	 * @return
	 */
	private String validarCamposEntradaCaracteres1(boolean insercion) {
		ConsultaHIPActualizacionDuplaCyzoneForm f = (ConsultaHIPActualizacionDuplaCyzoneForm) this.formBusqueda;
		String mensaje = "";
		
		String msgValidarCaracteresInicio = this.getResourceMessage("mantenimientoMAEClienteForm.msg.validarCaracteresInicio");
		String msgValidarCaracteresFinal1 = this.getResourceMessage("mantenimientoMAEClienteForm.msg.validarCaracteresFinal1");
		
		if(!f.isValidarCaracteres1())
			return null;
		
		if(f.isValidarCaracteres1()) {
    		String d_valApellido1             = this.getResourceMessage("consultaHIPActualizacionDuplaCyzoneForm.apellido1");
    		String d_valApellido2             = this.getResourceMessage("consultaHIPActualizacionDuplaCyzoneForm.apellido2");
    		String d_valNombre1               = this.getResourceMessage("consultaHIPActualizacionDuplaCyzoneForm.nombre1");
    		String d_valNombre2               = this.getResourceMessage("consultaHIPActualizacionDuplaCyzoneForm.nombre2");

    		String v_valApellido1             =   f.getApellido1Ingresar();             
    		String v_valApellido2             =   f.getApellido2Ingresar();             
    		String v_valNombre1               =   f.getNombre1Ingresar();               
    		String v_valNombre2               =   f.getNombre2Ingresar();               

    		if(!insercion) {
    			v_valApellido1             =   f.getApellido1Modificar();             
        		v_valApellido2             =   f.getApellido2Modificar();             
        		v_valNombre1               =   f.getNombre1Modificar();               
        		v_valNombre2               =   f.getNombre2Modificar();               
        	}         
            
    		boolean b_valApellido1             = StringUtil.validarTextoCaracteresNoValidos(f.getCadenaCaracteresNV1(), v_valApellido1);             
    		boolean b_valApellido2             = StringUtil.validarTextoCaracteresNoValidos(f.getCadenaCaracteresNV1(), v_valApellido2);             
    		boolean b_valNombre1               = StringUtil.validarTextoCaracteresNoValidos(f.getCadenaCaracteresNV1(), v_valNombre1);               
    		boolean b_valNombre2               = StringUtil.validarTextoCaracteresNoValidos(f.getCadenaCaracteresNV1(), v_valNombre2);               
    		
    		if(b_valApellido1              == false) mensaje = mensaje + "," + d_valApellido1             ;
    		if(b_valApellido2              == false) mensaje = mensaje + "," + d_valApellido2             ;
    		if(b_valNombre1                == false) mensaje = mensaje + "," + d_valNombre1               ;
    		if(b_valNombre2                == false) mensaje = mensaje + "," + d_valNombre2               ;
    		
    		if(mensaje.length()>1) mensaje = mensaje.substring(1);		
    	}	
    	    	
    	if (mensaje.length() > 0) {
    		mensaje = msgValidarCaracteresInicio + mensaje + msgValidarCaracteresFinal1 + " ";
    		mensaje = mensaje + StringUtil.obtenerListaCaracteres(f.getCadenaCaracteresNV1());
    	}
    	
		return mensaje;
	}

	/**
	 * 
	 * @param insercion
	 * @return
	 */
	private String validarCamposEntradaCaracteres2(boolean insercion) {
		ConsultaHIPActualizacionDuplaCyzoneForm f = (ConsultaHIPActualizacionDuplaCyzoneForm) this.formBusqueda;
		String mensaje = "";

		String msgValidarCaracteresInicio = this.getResourceMessage("mantenimientoMAEClienteForm.msg.validarCaracteresInicio");
		String msgValidarCaracteresFinal2 = this.getResourceMessage("mantenimientoMAEClienteForm.msg.validarCaracteresFinal2");
		
    	if(!f.isValidarCaracteres2())
    		return null;

    	if(f.isValidarCaracteres2()) {
    		String d_valTelfCliente           = this.getResourceMessage("consultaHIPActualizacionDuplaCyzoneForm.telefono");
    		String d_valCeluCliente           = this.getResourceMessage("consultaHIPActualizacionDuplaCyzoneForm.celular");

    		String v_valTelfCliente           =   f.getTelefonoIngresar();           
    		String v_valCeluCliente           =   f.getCelularIngresar();           

    		if(!insercion) {
        		v_valTelfCliente           =   f.getTelefonoModificar();           
        		v_valCeluCliente           =   f.getCelularModificar();           
    		}
    		
    		boolean b_valTelfCliente           =  StringUtil.validarTextoCaracteresValidos(f.getCadenaCaracteresV2(), v_valTelfCliente);       
    		boolean b_valCeluCliente           =  StringUtil.validarTextoCaracteresValidos(f.getCadenaCaracteresV2(), v_valCeluCliente);     

    		if(b_valTelfCliente           == false) mensaje = mensaje + "," +  d_valTelfCliente       ;
    		if(b_valCeluCliente           == false) mensaje = mensaje + "," +  d_valCeluCliente       ;
    	
    		if(mensaje.length()>1) mensaje = mensaje.substring(1);		
    	}	    	

    	if (mensaje.length() > 0) {
    		mensaje = msgValidarCaracteresInicio + mensaje + msgValidarCaracteresFinal2 + " ";
    		mensaje = mensaje + StringUtil.obtenerListaCaracteres(f.getCadenaCaracteresV2());
    	}
    	
		return mensaje;
	}

	/**
	 * 
	 * @param insercion
	 * @return
	 */
	private String validarCamposEntradaCaracteres3(boolean insercion) {
		ConsultaHIPActualizacionDuplaCyzoneForm f = (ConsultaHIPActualizacionDuplaCyzoneForm) this.formBusqueda;
		String mensaje = "";
		
		String msgValidarCaracteresInicio = this.getResourceMessage("mantenimientoMAEClienteForm.msg.validarCaracteresInicio");
		String msgValidarCaracteresFinal1 = this.getResourceMessage("mantenimientoMAEClienteForm.msg.validarCaracteresFinal1");
		
    	if(!f.isValidarCaracteres3())
    		return null;
    	
    	if(f.isValidarCaracteres3()) {
    		String d_valMailCliente           = this.getResourceMessage("consultaHIPActualizacionDuplaCyzoneForm.mail");
    		String v_valMailCliente           = f.getMailIngresar();

    		if(!insercion) {
    			v_valMailCliente           =   f.getMailModificar();           
    		}
    		  
    		boolean b_valMailCliente           = StringUtil.validarTextoCaracteresNoValidos(f.getCadenaCaracteresNV3(), v_valMailCliente);
    		
    		if(b_valMailCliente==false) mensaje = d_valMailCliente;
    	}	

    	if (mensaje.length() > 0) {
    		mensaje = msgValidarCaracteresInicio + mensaje + msgValidarCaracteresFinal1 + " ";
    		mensaje = mensaje + StringUtil.obtenerListaCaracteres(f.getCadenaCaracteresNV3());
    	}
    	
    	return mensaje;	
		
	}
	
	/**
	 * 
	 * @param event
	 * @throws Exception
	 */
	public void save(ActionEvent event) throws Exception {
		if (log.isDebugEnabled()){
			log.debug("Entering 'save' method");
		}
		
		try
		{
		
			ConsultaHIPActualizacionDuplaCyzoneForm f = (ConsultaHIPActualizacionDuplaCyzoneForm) this.formBusqueda;
			ConsultaHIPDatosClienteService consultaHIPDatosClienteService = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");
			InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
			ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = 
								(ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
			
			ConsultaHIPDatosCliente dtoDatosCliente = consultaHIPDatosClienteAction.getHipDtoDatosCliente();
			
			
			//Obtenemos los datos del usuario Logueado
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			String codigoPais = pais.getCodigo();
			
			Map params = new HashMap();
			params.put("codigoPais", dtoDatosCliente.getCodigoPais());
			params.put("codigoTipoDocumento", Constants.STO_TIPO_DOCUMENTO_DCYZ);
			
			//obtenemos el numero de lote, y lo actualizamos
			Map criteriaInterfaz = new HashMap();
			criteriaInterfaz.put("codigoPais", dtoDatosCliente.getCodigoPais());
			criteriaInterfaz.put("codigoDocumento", Constants.STO_TIPO_DOCUMENTO_DCYZ);
	
			String numeroLote = interfazSiCCService.getNumLoteSTO(criteriaInterfaz);
			params.put("numeroLote", numeroLote);
			params.put("usuario",usuario);
	
			
			String numeroSecuencia = procesoSTOEjecucionValidacionesService.getSecuenciaSTONextValue();
			
			params.put("numeroSecuencia", numeroSecuencia);
			params.put("indicadorEnvio", "0");
			params.put("indicadorRechazo", "0");
			params.put("codigoZona", dtoDatosCliente.getCodigoZona());
			params.put("codigoCliente", dtoDatosCliente.getCodigoCliente());
			params.put("codigoRegion", dtoDatosCliente.getCodigoRegion());
			params.put("login", usuario.getLogin());
			
			//enviamoas la informacion de la hida dupla
			params.put("codigoCompania", "01");
			params.put("codigoMadreDupla", dtoDatosCliente.getCodigoCliente());
			params.put("numeroDocumento", numeroSecuencia);
			
			String codigoPeriodo =interfazSiCCService.getPeriodoDefaultByPaisCanal(pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT);
			params.put("codigoPeriodo", codigoPeriodo);
			
			if(f.getIndicadorModificar().equals("S"))	{ //Actualizamos los datos de la hija Dupla
				params.put("apellido1", f.getApellido1Modificar());
				params.put("apellido2", f.getApellido2Modificar());
				params.put("nombre1", f.getNombre1Modificar());
				params.put("nombre2", f.getNombre2Modificar());
				params.put("fechaNacimiento", DateUtil.convertDateToString(f.getFechaNacimientoModificar()));
				params.put("email", f.getMailModificar());
				params.put("telefono", f.getTelefonoModificar());
				params.put("celular", f.getCelularModificar());
				params.put("indicadorDuplaNueva", "N");
				params.put("indicadorActualizacion", "S");
				params.put("codigoClienteDupla", f.getCodigoDupla());
				
			} else { //Insertamos los datos de la hija Dupla
				
				params.put("apellido1", f.getApellido1Ingresar());
				params.put("apellido2", f.getApellido2Ingresar());
				params.put("nombre1", f.getNombre1Ingresar());
				params.put("nombre2", f.getNombre2Ingresar());
				params.put("fechaNacimiento", DateUtil.convertDateToString(f.getFechaNacimientoIngresar()));
				params.put("email", f.getMailIngresar());
				params.put("telefono", f.getTelefonoIngresar());
				params.put("celular", f.getCelularIngresar());
				params.put("indicadorDuplaNueva", "S");
				params.put("indicadorActualizacion", "N");
			}
			
			params.put("oidMadreDupla", dtoDatosCliente.getOidCliente());
			consultaHIPDatosClienteService.execGenerarSolicitudDuplaCyzone(params);
			
			//Ejecutando Validaciones STO
			params.put("codPais", codigoPais);
			params.put("codTipoDocu", Constants.STO_TIPO_DOCUMENTO_DCYZ);			
			params.put("numLote", numeroLote);
	
			params.put("codigoPeriodo", "");
			
			
			ProcesoSTOService procesoSTOService = (ProcesoSTOService) getBean("spusicc.procesoSTOService");
			
			List lista = procesoSTOService.getDocumentoDigitadoPKByLote(params);
			
			List listaSTO = new ArrayList();
			for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
	    		 DocumentoDigitadoPK documentoDigitadoPK = (DocumentoDigitadoPK) iterator.next();
	    		 GestionDocumento gestionDocumento = new GestionDocumento();
	    		 gestionDocumento.setCodigoPais(documentoDigitadoPK.getCodPais());
	    		 gestionDocumento.setNumeroDocumento(documentoDigitadoPK.getSecNumeDocu());
	    		 gestionDocumento.setLote(documentoDigitadoPK.getNumLote());
	    		 gestionDocumento.setDocumento(documentoDigitadoPK.getCodTipoDocu());
	    		 listaSTO.add(gestionDocumento);
	    		 
	  		}		
			
			String codTipoDocu = Constants.STO_TIPO_DOCUMENTO_DCYZ;
			ProcesoSTOExecutionService execService = (ProcesoSTOExecutionService) getBean("spusicc.procesoSTOExecutionService");
			AccionTipoDocumento accionTipoDocumento = new AccionTipoDocumento(codigoPais,codTipoDocu,Constants.STO_ACCI_VALI_ON_LINE);
			
			
			execService.execute(accionTipoDocumento,params,listaSTO);
			
			ProcesoSTOLevantamientoErroresValidacionService serviceGestion = (ProcesoSTOLevantamientoErroresValidacionService) 
													getBean("spusicc.procesoSTOLevantamientoErroresValidacionService");
			
			//Verificamos si  Existen Errores STO En caso existan los mostramos
			
			params.put("tipoDocumento",Constants.STO_TIPO_DOCUMENTO_DCYZ);
			params.remove("numeroDocumento");
			List listaErroresValidacion =  serviceGestion.getGestionDocumentoList(params);
	
			if (listaErroresValidacion.size()>0){
				String msgError1 = this.getResourceMessage("consultaHIPActualizacionDuplaCyzoneForm.errors.gestion");
				String msgError2 = this.getResourceMessage("errors.token");
				this.addError(msgError1, msgError2);
				
				for (Iterator iterator = listaErroresValidacion.iterator(); iterator.hasNext();) {
		    		 GestionDocumento gestionDocumento = (GestionDocumento) iterator.next();
		    		 this.addError("errors.detail", gestionDocumento.getDesValidacionLarga());
		    		 
		  		}
			}
			else{
	
				String msgConfirm = "";
				if(f.getIndicadorModificar().equals("S"))
					msgConfirm = this.getResourceMessage("consultaHIPActualizacionDuplaCyzoneForm.msg.actualizoOk");
				else
					msgConfirm = this.getResourceMessage("consultaHIPActualizacionDuplaCyzoneForm.msg.graboOk");
				
				this.addInfo("Mensaje.", msgConfirm);
			}
		
		}
		catch(Exception ex)
		{
			this.addError("Error: ", ex.getMessage());
			log.debug("Error al grabar. "+ ex.getMessage());
		}
		
	}

	/**
	 * 
	 * @param val
	 */
	public void flagModificarChange(ValueChangeEvent val) {

		String valor = val.getNewValue().toString();

		log.debug("flagModificarChange: valor = " + valor);
	
		ConsultaHIPActualizacionDuplaCyzoneForm f = (ConsultaHIPActualizacionDuplaCyzoneForm) this.formBusqueda;
		
		if(StringUtils.equals(valor, "true"))
		{
			f.setFlagModificar(true);
			f.setFlagIngresar(false);
		}
	}
	
	/**
	 * 
	 * @param val
	 */
	public void flagIngresarChange(ValueChangeEvent val) {

		String valor = val.getNewValue().toString();

		log.debug("flagIngresarChange: valor = " + valor);

		ConsultaHIPActualizacionDuplaCyzoneForm f = (ConsultaHIPActualizacionDuplaCyzoneForm) this.formBusqueda;
		
		if(StringUtils.equals(valor, "true"))
		{
			f.setFlagIngresar(true);
			f.setFlagModificar(false);			
		}
		
	}
	
	/**
	 * Limpia los campos del formulario de la pantalla
	 * 
	 * @param f
	 * @param dtoDatosCliente
	 */
	private void limpiar(ConsultaHIPActualizacionDuplaCyzoneForm f) {
		f.setCodigoDupla("");
		f.setNombre1Modificar("");
		f.setNombre2Modificar("");
		f.setApellido1Modificar("");
		f.setApellido2Modificar("");
		f.setFechaNacimientoModificar(new Date());
		f.setMailModificar("");
		f.setTelefonoModificar("");
		f.setCelularModificar("");
		
		f.setNombre1Ingresar("");
		f.setNombre2Ingresar("");
		f.setApellido1Ingresar("");
		f.setApellido2Ingresar("");
		f.setFechaNacimientoIngresar(new Date());
		f.setMailIngresar("");
		f.setTelefonoIngresar("");
		f.setCelularIngresar("");
		
		f.setFlagIngresar(false);
		f.setFlagModificar(false);
	}
	
	//getters && setters

	public ConsultaHIPDatosClienteAction getConsultaHIPDatosClienteAction() {
		return consultaHIPDatosClienteAction;
	}

	public void setConsultaHIPDatosClienteAction(
			ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction) {
		this.consultaHIPDatosClienteAction = consultaHIPDatosClienteAction;
	}

}
