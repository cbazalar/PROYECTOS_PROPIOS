package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;
import org.primefaces.context.RequestContext;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.HistoricoAuditoria;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOIngresoCuponForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoSTOIngresoCuponAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -7766588187031148487L;

	private Integer longitudCampoClientes;
	private String oidPais;
	private String mensaje;
	private boolean indicadorRechazo;
	private String parametroAccion;

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoSTOIngresoCuponForm";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		Map criteria = new HashMap();
		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		MantenimientoSTOIngresoCuponForm f = (MantenimientoSTOIngresoCuponForm) this.formMantenimiento;

		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		boolean bGrabar = true;
		f.setFechaRegistro(DateUtil.convertDateToString(f.getFechaRegistroD()));
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codCliente", f.getCodCliente());
		criteria.put("valorPagado", f.getValorPagado());
		criteria.put("fechaRegistro", f.getFechaRegistro());
		criteria.put("codigoUsuario", usuario.getLogin());
		criteria.put("codigoDocumento", Constants.STO_TIPO_DOCUMENTO_CUP);
		criteria.put("caracterDocumento",
				Constants.STO_CODIGO_NUMERO_DOCUMENTO_CUPON);
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		String codigoCompania = procesoSTOEjecucionValidacionesService
				.getCodigoCompania(criteria);
		String numLoteSTO = interfazSiCCService.getNumLoteSTO(criteria);
		String secuenciaCupon = procesoSTOEjecucionValidacionesService
				.getSecuenciaCuponPagoSTONextValue();
		criteria.put("secuenciaCupon", secuenciaCupon);
		String secuenciaSTO = procesoSTOEjecucionValidacionesService
				.getSecuenciaSTONextValue();
		criteria.put("secuenciaSTO", secuenciaSTO);
		criteria.put("numLoteSTO", numLoteSTO);
		criteria.put("codigoCompania", codigoCompania);



		if (f.getIndicadorRechazo().compareToIgnoreCase("S") == 0)
			criteria.put("indicadorRechazo", "1");
		else
			criteria.put("indicadorRechazo", "0");

		procesoSTOEjecucionValidacionesService.insertCuponPago(criteria);
		f.setCodCliente("");
		f.setValorPagado("");
		this.indicadorRechazo = false;
		f.setIndicadorRechazo("N");

		return bGrabar;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarBotonEliminar = false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonSalir = false;
		this.mostrarBotonSave = false;
		this.parametroAccion = "";
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codigoPais = pais.getCodigo();
		MantenimientoSTOIngresoCuponForm f = new MantenimientoSTOIngresoCuponForm();

		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", codigoPais);

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		String oidPais = reporteService.getOidString("getOidPaisByCodigoPais",
				criteriaOperacion);

		setOidPais(String.valueOf(oidPais));

		// Seteo la longitud del codigo de consultora de acuerdo al pais
		ClienteUAGenerarService clienteService = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");

		this.longitudCampoClientes = clienteService
				.getTamanhoNumeroCliente(codigoPais);
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", codigoPais);
		// indica campania activa
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO);
		// indica campania activa que se procesa actualmente
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO);

		PedidoControlFacturacion controlFacturacion = service
				.getControlFacturacionById(criteriaPeriodo);
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		formMantenimiento = f;

	}
	
	public void guardarCondicional(ActionEvent event){
		
			try {
				if(StringUtils.equals(parametroAccion, "GRABAR")){
					String ventana = "mensajeValor2";
					String ventanaConfirmar = "PF('" + ventana
							+ "_confirmationDialogConfirmar').show()";
					this.getRequestContext().execute(ventanaConfirmar);
					return;
				}
			} catch (Exception e) {
				this.addError("Error : ", this.obtieneMensajeErrorException(e));
				return;
			}
		
		
	}
	
	

	/**
	 * @param event
	 */
	public void valorOnEnter(ActionEvent event) {
		


		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		this.parametroAccion= externalContext.getRequestParameterMap().get("parametroAccion");
		
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoSTOIngresoCuponForm f = (MantenimientoSTOIngresoCuponForm) this.formMantenimiento;
		
		if(StringUtils.equals(parametroAccion, "GRABAR")){
			if(f.getCodCliente().length()==0){
				 this.addError("Mensaje", "Cliente es un campo requerido");
				 return;
			}
			
			if(f.getValorPagado().length()==0){
				 this.addError("Mensaje", "Valor pagado es un campo requerido");
				 return;
			}
			
			if(f.getFechaRegistroD()==null ){
				 this.addError("Mensaje", "Fecha es un campo requerido");
				 return;
			}
			
			if (indicadorRechazo){
				f.setIndicadorRechazo("S");
			}
			else{
				this.indicadorRechazo=true;
				f.setIndicadorRechazo("N");			
			}
		}
		
		
		
		f.setFechaRegistro(DateUtil.convertDateToString(f.getFechaRegistroD()));
		String valor = ajax.getPedidoCuponExistenteByCriteria(pais.getCodigo(),
				f.getCodigoPeriodo(), f.getCodCliente(), f.getFechaRegistro(),
				f.getValorPagado());
		if (StringUtils.isNotBlank(valor)) {
			StringTokenizer st = new StringTokenizer(valor, "|");
			String user = st.nextToken();
			String fecha = st.nextToken();
			mensaje = "El Cupón ya existe \n Usuario Digitador : " + user
					+ " Fecha de digitación : " + fecha + "\n¿Desea continuar?";
			this.getRequestContext()
					.execute(
							"PF('mensajeValor_confirmationDialogConfirmarSalir').show()");
		
		}else{
			if(StringUtils.equals(parametroAccion, "GRABAR")){
				
				if(f.getCodCliente().length()>0){
					
					Map criteriaOperacion = new HashMap();
					criteriaOperacion.put("codigoPais", pais.getCodigo());
					ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
					String oidPais = reporteService.getOidString("getOidPaisByCodigoPais",criteriaOperacion);
					
					
					String validacionCodigoCliente = ajax.validarCodigoCliente(oidPais, f.getCodCliente());	
					
					if(StringUtils.equals(validacionCodigoCliente, Constants.NO)){
						this.addError("Mensaje", "Código de Cliente no existe");
						return;
					}
				}		
				
				if(!StringUtils.equals(parametroAccion, "GRABAR")){					
					if (indicadorRechazo){
						f.setIndicadorRechazo("S");
					}
					else{
						f.setIndicadorRechazo("N");			
					}
				}
				
				guardarCondicional(event);
			}
		}

	}
	
	public void savePadInsert(ActionEvent event){
		this.parametroAccion = "GRABAR";
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoSTOIngresoCuponForm f = (MantenimientoSTOIngresoCuponForm) this.formMantenimiento;
		
		if(f.getCodCliente().length()==0){
			 this.addError("Mensaje", "Cliente es un campo requerido");
			 return;
		}
		
		if(f.getValorPagado().length()==0){
			 this.addError("Mensaje", "Valor pagado es un campo requerido");
			 return;
		}
		
		if(f.getFechaRegistroD()==null ){
			 this.addError("Mensaje", "Fecha es un campo requerido");
			 return;
		}
		
		
		f.setFechaRegistro(DateUtil.convertDateToString(f.getFechaRegistroD()));
		String valor = ajax.getPedidoCuponExistenteByCriteria(pais.getCodigo(),
				f.getCodigoPeriodo(), f.getCodCliente(), f.getFechaRegistro(),
				f.getValorPagado());
		
		
		if (indicadorRechazo){
			f.setIndicadorRechazo("S");
		}
		else{
			this.indicadorRechazo=true;
			f.setIndicadorRechazo("N");
		}
		
		if (StringUtils.isNotBlank(valor)) {
			StringTokenizer st = new StringTokenizer(valor, "|");
			String user = st.nextToken();
			String fecha = st.nextToken();
			mensaje = "El Cupón ya existe \n Usuario Digitador : " + user
					+ " Fecha de digitación : " + fecha + "\n¿Desea continuar?";
			this.getRequestContext()
					.execute(
							"PF('mensajeValor_confirmationDialogConfirmarSalir').show()");
		}else{
			if(f.getCodCliente().length()>0){
				
				Map criteriaOperacion = new HashMap();
				criteriaOperacion.put("codigoPais", pais.getCodigo());
				ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
				String oidPais = reporteService.getOidString("getOidPaisByCodigoPais",criteriaOperacion);
				
				
				String validacionCodigoCliente = ajax.validarCodigoCliente(oidPais, f.getCodCliente());	
				
				if(StringUtils.equals(validacionCodigoCliente, Constants.NO)){
					this.addError("Mensaje", "Código de Cliente no existe");
					return;
				}
			}		
			
			
			guardarCondicional(event);
		}
	}

	public void save(ActionEvent actionEvent)  {
		if (log.isWarnEnabled()) {
			log.warn("Entering 'save' method");
		}
		String mensajeGrabarKey = new String();
		HistoricoAuditoria historicoAuditoria = new HistoricoAuditoria();
		historicoAuditoria.setCodigoAccion((this.accionFuncional != null)?this.accionFuncional:this.ACCION_GRABAR);
		boolean error=true;
		
		try{			
			this.grabarAuditoriaUsuarioIni(historicoAuditoria, this.codigoMenu);			
			
			if (this.setSaveAttributes()) {
				mensajeGrabarKey = this.devuelveMensajeKeySaveOK(); 
				String mensaje = this.getResourceMessage(mensajeGrabarKey);	
				error=false;
				
				
				if (this.salirGrabarPantallaPadre) {
					if (this.invocarFindLuegoGrabar) {
						this.setSaveFindBeforeAttributes();
						this.setSaveFindAfterAttributes();
					}
					this.accion = this.ACCION_BUSCAR;
					if (this.mostrarMantenimientoEnPopup) {
						String ventana = "PF('dialogMantenimientoForm').hide()";
						this.getRequestContext().execute(ventana);
						RequestContext.getCurrentInstance().update("listaBusquedaForm");
						RequestContext.getCurrentInstance().update("idBody_final_busqueda");						
						this.addInfo("Info: ", mensaje);
					}
					else {			
						String mensajeAlert = this.devuelveMensajeAlertaDefaultAction();
						if (StringUtils.isBlank(mensajeAlert)) {
							mensajeAlert = mensaje;	
						}
						this.setMensajeAlertaDefaultAction(mensajeAlert);
						RequestContext.getCurrentInstance().update("principalFormAlertAction:textoMensajeAlerta");
						String ventana = "PF('principalFormAlertAction_alertDialogAction').show()";
						this.getRequestContext().execute(ventana);
					}
				}
				else {
					this.addInfo("Info: ", mensaje);
				}
				
				historicoAuditoria.setIndicadorEstado(Constants.MENSAJE_PROCESO_EXITO);
				this.grabarAuditoriaUsuarioFin(historicoAuditoria);
			}	
		}
		catch (Exception e) {
 			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			historicoAuditoria.setIndicadorEstado(Constants.MENSAJE_PROCESO_ERROR);
			this.grabarAuditoriaUsuarioFin(historicoAuditoria);
			this.getRequestContext().addCallbackParam("activarCerrar", false);
			return;
		}
		if (log.isWarnEnabled()) {
			log.warn("Finish 'save' method");
		}
		if(this.activarGrabarWindowClose && error)
			this.getRequestContext().addCallbackParam("activarCerrar", false);
		return;
	}

	/**
	 * @param event
	 */
	public void codigoClienteOnEnter(ActionEvent event) {
		try {
			MantenimientoSTOIngresoCuponForm f = (MantenimientoSTOIngresoCuponForm) this.formMantenimiento;
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			String valor = ajax.getCodigoClienteByDocumentoIdentidad(f
					.getNumeroDocumentoIdentidad());
			f.setCodCliente(valor);
			if (StringUtils.isBlank(valor)){
				f.setCodCliente("");
				this.addError("Error : ",this.getResourceMessage("mantenimientoSTOCupon.documento.no.existe"));
				return;
				
			}
				
			
			this.getRequestContext().execute("PrimeFaces.focus('valorPagado:input')");
		} catch (Exception e) {
			this.addError("Error", obtieneMensajeErrorException(e));
		}

	}

	public void cancelar(ActionEvent event) {
		MantenimientoSTOIngresoCuponForm f = (MantenimientoSTOIngresoCuponForm) this.formMantenimiento;
		f.setCodCliente(null);
		f.setValorPagado(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoSTOIngresoCuponForm form = (MantenimientoSTOIngresoCuponForm) this.formMantenimiento;
		boolean isNew = form.isNewRecord();
		if (isNew) {
			return "mantenimientoSTOCuponForm.insert";
		} else {
			return "mantenimientoSTOCuponForm.insert";
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setValidarMantenimiento()
	 */
	public String setValidarMantenimiento() {
		MantenimientoSTOIngresoCuponForm f = (MantenimientoSTOIngresoCuponForm) this.formMantenimiento;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		String valor = ajax.validarCodigoCliente(oidPais, f.getCodCliente());
		if (StringUtils.equals(valor, "N"))
			return "Código de Cliente no existe";
		if (!this.indicadorRechazo) {
			
			f.setIndicadorRechazo("N");
		} else{
			f.setIndicadorRechazo("S");
			this.indicadorRechazo = true;
		}
		return "";

	}
	
	public void limpiar2(ActionEvent event){
		MantenimientoSTOIngresoCuponForm f = (MantenimientoSTOIngresoCuponForm) this.formMantenimiento;
		f.setCodCliente("");
		f.setNumeroDocumentoIdentidad("");
		f.setValorPagado("");
		this.parametroAccion="";
	}

	/**
	 * @return the longitudCampoClientes
	 */
	public Integer getLongitudCampoClientes() {
		return longitudCampoClientes;
	}

	/**
	 * @param longitudCampoClientes
	 *            the longitudCampoClientes to set
	 */
	public void setLongitudCampoClientes(Integer longitudCampoClientes) {
		this.longitudCampoClientes = longitudCampoClientes;
	}

	/**
	 * @return the oidPais
	 */
	public String getOidPais() {
		return oidPais;
	}

	/**
	 * @param oidPais
	 *            the oidPais to set
	 */
	public void setOidPais(String oidPais) {
		this.oidPais = oidPais;
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje
	 *            the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return the indicadorRechazo
	 */
	public boolean isIndicadorRechazo() {
		return indicadorRechazo;
	}

	/**
	 * @param indicadorRechazo
	 *            the indicadorRechazo to set
	 */
	public void setIndicadorRechazo(boolean indicadorRechazo) {
		this.indicadorRechazo = indicadorRechazo;
	}

}
