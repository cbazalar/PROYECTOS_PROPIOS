package biz.belcorp.ssicc.web.scsicc.hip.action;

import java.util.ArrayList;
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

import org.apache.commons.lang3.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scsicc.model.ConsultaHIPDatosCliente;
import biz.belcorp.ssicc.dao.spusicc.sto.model.AccionTipoDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.DocumentoDigitadoPK;
import biz.belcorp.ssicc.dao.spusicc.sto.model.GestionDocumento;
import biz.belcorp.ssicc.service.scsicc.ConsultaHIPDatosClienteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOLevantamientoErroresValidacionService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.ProcesoSTOExecutionService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.form.ConsultaHIPActualizacionDatosConsultoraForm;
import biz.belcorp.ssicc.web.util.StringUtil;



/**
 * TODO Include class description here.
 * <p>
 * <a href="ConsultaHIPActualizacionDatosConsultoraAction"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */

@ManagedBean
@SessionScoped
public class ConsultaHIPActualizacionDatosConsultoraAction extends BasePopupAbstractAction {

	private static final long serialVersionUID = -5011718210988038432L;
	private List siccTipoDocumentoList;
	
	@ManagedProperty(value="#{consultaHIPDatosClienteAction}")
	private ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction;
	
	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaHIPActualizacionDatosConsultoraForm f = new ConsultaHIPActualizacionDatosConsultoraForm();
		return f;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ConsultaHIPActualizacionDatosConsultoraAction - setFindAttributes' method");
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
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ConsultaHIPActualizacionDatosConsultoraAction - setViewAtributes' method");
		}
		
		ConsultaHIPActualizacionDatosConsultoraForm f = (ConsultaHIPActualizacionDatosConsultoraForm) this.formBusqueda;
		
		ConsultaHIPDatosCliente dtoDatosCliente = consultaHIPDatosClienteAction.getHipDtoDatosCliente();
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		
		f.setCodConsultora(dtoDatosCliente.getCodigoCliente());
		f.setNomConsultora(dtoDatosCliente.getNombreCompleto());
		f.setDesRegZonTerri(dtoDatosCliente.getDescripcionRegion() + " / " + dtoDatosCliente.getDescripcionZona() + " / " + dtoDatosCliente.getCodigoTerritorio());
		
		f.setTelefonoCasa(dtoDatosCliente.getTelefonoCasa());  
		f.setTelefonoCelular(dtoDatosCliente.getTelefonoCelular());
		f.setMail(dtoDatosCliente.getMail());
		f.setFechaNacimiento(dtoDatosCliente.getFechaNacimiento());
		
		f.setApellido1(dtoDatosCliente.getApellido1());
		f.setApellido2(dtoDatosCliente.getApellido2());
		f.setNombre1(dtoDatosCliente.getNombre1());
		f.setNombre2(dtoDatosCliente.getNombre2());
		f.setTipoDocumento(dtoDatosCliente.getOidTipoDocIdentidad());
		f.setNumeroDocumento(dtoDatosCliente.getNumeroDocIdentidad());
		
		//verificamos si se permite actualizar datos de la consultora
		f.setPermitirActualizacion(true);
		if(Constants.HIP_MOTIVO_BLOQUEO_ACTUALIZACION_DATOS.equals(dtoDatosCliente.getCodigoBloqueo())) {
			f.setPermitirActualizacion(false);
		}
		
		//cargamos la lista de tipos de documentos de identidad
		Map criteria = new HashMap();
		criteria.put("oidPais", dtoDatosCliente.getOidPais());
		
		List tipoDocumentoList = clienteService.getTiposDocumentoIdentidad(criteria);
		setSiccTipoDocumentoList(tipoDocumentoList);
		
		
		//Validadores
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
		
		String validaciones = validarCamposEntradaCaracteres1();
		
		if(StringUtils.isBlank(validaciones))
		{
			validaciones = validarCamposEntradaCaracteres2();
			
			if(StringUtils.isBlank(validaciones))
				validaciones = validarCamposEntradaCaracteres3();			
		}
		
		return validaciones;
	}

	/**
	 * 
	 * @return
	 */
	private String validarCamposEntradaCaracteres1()
	{
		ConsultaHIPActualizacionDatosConsultoraForm f = (ConsultaHIPActualizacionDatosConsultoraForm) this.formBusqueda;
		String mensaje = "";
		
		String msgValidarCaracteresInicio = this.getResourceMessage("mantenimientoMAEClienteForm.msg.validarCaracteresInicio");
		String msgValidarCaracteresFinal1 = this.getResourceMessage("mantenimientoMAEClienteForm.msg.validarCaracteresFinal1");
		
		if(f.isValidarCaracteres1())
		{			
    		String d_valApellido1             = this.getResourceMessage("consultaHIPActualizacionDatosConsultoraForm.apellido1");
    		String d_valApellido2             = this.getResourceMessage("consultaHIPActualizacionDatosConsultoraForm.apellido2");
    		String d_valNombre1               = this.getResourceMessage("consultaHIPActualizacionDatosConsultoraForm.nombre1");
    		String d_valNombre2               = this.getResourceMessage("consultaHIPActualizacionDatosConsultoraForm.nombre2");
    		String d_numDocuIdentidad         = this.getResourceMessage("consultaHIPActualizacionDatosConsultoraForm.numeroDocumento");

    		String v_valApellido1             =   f.getApellido1();             
    		String v_valApellido2             =   f.getApellido2();             
    		String v_valNombre1               =   f.getNombre1();               
    		String v_valNombre2               =   f.getNombre2();               
    		String v_numDocuIdentidad         =   f.getNumeroDocumento();         
            
    		boolean b_valApellido1             = StringUtil.validarTextoCaracteresNoValidos(f.getCadenaCaracteresNV1(), v_valApellido1);             
    		boolean b_valApellido2             = StringUtil.validarTextoCaracteresNoValidos(f.getCadenaCaracteresNV1(), v_valApellido2);             
    		boolean b_valNombre1               = StringUtil.validarTextoCaracteresNoValidos(f.getCadenaCaracteresNV1(), v_valNombre1);               
    		boolean b_valNombre2               = StringUtil.validarTextoCaracteresNoValidos(f.getCadenaCaracteresNV1(), v_valNombre2);               
    		boolean b_numDocuIdentidad         = StringUtil.validarTextoCaracteresNoValidos(f.getCadenaCaracteresNV1(), v_numDocuIdentidad);         
    		
    		if(b_valApellido1              == false) mensaje = mensaje + "," + d_valApellido1             ;
    		if(b_valApellido2              == false) mensaje = mensaje + "," + d_valApellido2             ;
    		if(b_valNombre1                == false) mensaje = mensaje + "," + d_valNombre1               ;
    		if(b_valNombre2                == false) mensaje = mensaje + "," + d_valNombre2               ;
    		if(b_numDocuIdentidad          == false) mensaje = mensaje + "," + d_numDocuIdentidad         ;
    		
    		if(mensaje.length() > 1) mensaje = mensaje.substring(1);
    		
		}
		
    	if (mensaje.length() > 0) {
    		mensaje = msgValidarCaracteresInicio + mensaje + msgValidarCaracteresFinal1 + " ";
    		mensaje = mensaje + StringUtil.obtenerListaCaracteres(f.getCadenaCaracteresNV1());    		
    	}
		
		return mensaje;
	}

	/**
	 * 
	 * @return
	 */
	private String validarCamposEntradaCaracteres2()
	{
		ConsultaHIPActualizacionDatosConsultoraForm f = (ConsultaHIPActualizacionDatosConsultoraForm) this.formBusqueda;
		String mensaje = "";
		
		String msgValidarCaracteresInicio = this.getResourceMessage("mantenimientoMAEClienteForm.msg.validarCaracteresInicio");
		String msgValidarCaracteresFinal2 = this.getResourceMessage("mantenimientoMAEClienteForm.msg.validarCaracteresFinal2");
				
    	if(!f.isValidarCaracteres2()) {
    		return null;
    	}
    	else {
    		String d_valTelfCliente           = this.getResourceMessage("consultaHIPActualizacionDatosConsultoraForm.telefonoCasa");
    		String d_valCeluCliente           = this.getResourceMessage("consultaHIPActualizacionDatosConsultoraForm.telefonoCelular");

    		String v_valTelfCliente           =   f.getTelefonoCasa();           
    		String v_valCeluCliente           =   f.getTelefonoCelular();           

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
	 * @return
	 */
	private String validarCamposEntradaCaracteres3()
	{
		ConsultaHIPActualizacionDatosConsultoraForm f = (ConsultaHIPActualizacionDatosConsultoraForm) this.formBusqueda;
		String mensaje = "";

		String msgValidarCaracteresInicio = this.getResourceMessage("mantenimientoMAEClienteForm.msg.validarCaracteresInicio");
		String msgValidarCaracteresFinal1 = this.getResourceMessage("mantenimientoMAEClienteForm.msg.validarCaracteresFinal1");
		
    	if(!f.isValidarCaracteres3()) {
    		return null;
    	}
    	else {
    		String d_valMailCliente           = this.getResourceMessage("consultaHIPActualizacionDatosConsultoraForm.mail");
    		String v_valMailCliente           = f.getMail();  
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
		
		try{
			ConsultaHIPActualizacionDatosConsultoraForm f = (ConsultaHIPActualizacionDatosConsultoraForm) this.formBusqueda;
			ConsultaHIPDatosClienteService consultaHIPDatosClienteService = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");
			InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
			ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
			
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			String codigoPais = pais.getCodigo();
			String codigoPeriodo = interfazSiCCService.getPeriodoDefaultByPaisCanal(codigoPais, Constants.CODIGO_CANAL_DEFAULT);
			ConsultaHIPDatosCliente dtoDatosCliente = consultaHIPDatosClienteAction.getHipDtoDatosCliente();
			
			//Obtenemos los datos del usuario Logueado
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			
			Map params = new HashMap();
			params.put("codigoPais", dtoDatosCliente.getCodigoPais());
			params.put("codigoTipoDocumento", Constants.STO_TIPO_DOCUMENTO_SAD);
			
			//obtenemos el numero de lote, y lo actualizamos
			Map criteriaInterfaz = new HashMap();
			criteriaInterfaz.put("codigoPais", dtoDatosCliente.getCodigoPais());
			criteriaInterfaz.put("codigoDocumento", Constants.STO_TIPO_DOCUMENTO_SAD);
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
			params.put("codigoPeriodo", codigoPeriodo);
			
			//enviamos la informacion de los datos de la consultora a actualizar
			params.put("codigoCompania", "01");
			params.put("numeroDocumento", numeroSecuencia);
			//params.put("fechaNacimiento", sdf.parse(f.getFechaNacimiento()));
			params.put("mail", f.getMail());
			params.put("telefonoCasa", f.getTelefonoCasa());
			params.put("telefonoCelular", f.getTelefonoCelular());
	
			params.put("apellido1", f.getApellido1());
			params.put("apellido2", f.getApellido2()); 
			params.put("nombre1", f.getNombre1());
			params.put("nombre2", f.getNombre2());
			params.put("tipoDocumento", f.getTipoDocumento());
			params.put("numeroDocumentoCliente", f.getNumeroDocumento());
			
			consultaHIPDatosClienteService.execGenerarSolicitudActualizacionDatos(params);
			//ActionMessages messages = new ActionMessages();
			
			//Ejecutando Validaciones STO
			
			
			String codTipoDocu = Constants.STO_TIPO_DOCUMENTO_SAD;
			params.put("codPais", codigoPais);
			params.put("codTipoDocu", codTipoDocu);			
			params.put("numLote", numeroLote);
			
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
			
			ProcesoSTOExecutionService execService = (ProcesoSTOExecutionService) getBean("spusicc.procesoSTOExecutionService");
			AccionTipoDocumento accionTipoDocumento = new AccionTipoDocumento(codigoPais,codTipoDocu,Constants.STO_ACCI_VALI_ON_LINE);
			
			
			execService.execute(accionTipoDocumento,params,listaSTO);
			
			ProcesoSTOLevantamientoErroresValidacionService serviceGestion = (ProcesoSTOLevantamientoErroresValidacionService) 
													getBean("spusicc.procesoSTOLevantamientoErroresValidacionService");
			
			//Verificamos si  Existen Errores STO En caso existan los mostramos
			
			params.put("tipoDocumento",Constants.STO_TIPO_DOCUMENTO_SAD);
			params.remove("numeroDocumento");
			
			List listaErroresValidacion =  serviceGestion.getGestionDocumentoList(params);
			if (listaErroresValidacion.size()>0){
				String msgError1 = this.getResourceMessage("consultaHIPActualizacionDatosConsultoraForm.errors.gestion");
				String msgError2 = this.getResourceMessage("errors.token");
				this.addError(msgError1, msgError2);
				
				for (Iterator iterator = listaErroresValidacion.iterator(); iterator.hasNext();) {
		    		 GestionDocumento gestionDocumento = (GestionDocumento) iterator.next();
		    		 this.addError("errors.detail", gestionDocumento.getDesValidacionLarga());
		    		 
		  		}
			}
			else{
				String msgConfirm = this.getResourceMessage("consultaHIPActualizacionDatosConsultoraForm.msg.graboOk");
				this.addInfo("Mensaje.", msgConfirm);
			}
		
		}catch(Exception ex){
			this.addError("Error: ", ex.getMessage());
			log.debug("Error al grabar. "+ ex.getMessage());
		}
	
	}
	
	//getters && setters
	
	public List getSiccTipoDocumentoList() {
		return siccTipoDocumentoList;
	}

	public void setSiccTipoDocumentoList(List siccTipoDocumentoList) {
		this.siccTipoDocumentoList = siccTipoDocumentoList;
	}

	public ConsultaHIPDatosClienteAction getConsultaHIPDatosClienteAction() {
		return consultaHIPDatosClienteAction;
	}

	public void setConsultaHIPDatosClienteAction(
			ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction) {
		this.consultaHIPDatosClienteAction = consultaHIPDatosClienteAction;
	}

}
