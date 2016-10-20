package biz.belcorp.ssicc.web.scsicc.hip.action;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scsicc.model.ConsultaHIPDatosCliente;
import biz.belcorp.ssicc.dao.spusicc.flx.model.ConsultoraFlexipago;
import biz.belcorp.ssicc.dao.spusicc.flx.model.ConsultoraFlexipagoPK;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteComunicacion;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteFLXDetalladoSaldosConsultoras2Form;
import biz.belcorp.ssicc.service.scsicc.ConsultaHIPDatosClienteService;
import biz.belcorp.ssicc.service.spusicc.emprendedoras.MantenimientoEMPEmprendedoraService;
import biz.belcorp.ssicc.service.spusicc.flx.MantenimientoFLXConsultoraService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.action.ReporteFLXDetalladoSaldosConsultoras2Action;
import biz.belcorp.ssicc.web.scsicc.hip.form.ConsultaFLXConsultoraForm;

/**
 * The Class ConsultaFLXConsultoraAction.
 *
 * @autor: Belcorp
 * @version: 1.0 19/12/2013
 */
@ManagedBean
@SessionScoped
public class ConsultaFLXConsultoraAction extends BasePopupAbstractAction {

	private static final long serialVersionUID = 1L;

	private List flxConsuDetalleList;
	private List flxUsoDetalleList;
	private List flxMotivoList;
	private String oidCliente;
	private String estadoGrabado;

	@ManagedProperty(value = "#{consultaHIPDatosClienteAction}")
	private ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction;

	@ManagedProperty(value = "#{reporteFLXDetalladoSaldosConsultoras2Action}")
	private ReporteFLXDetalladoSaldosConsultoras2Action reporteFLXDetalladoSaldosConsultoras2Action;

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaFLXConsultoraForm consultaFLXConsultoraForm = new ConsultaFLXConsultoraForm();
		return consultaFLXConsultoraForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ConsultaFLXConsultoraAction - setFindAttributes()");

		}

		return getFlxConsuDetalleList();
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
			log.debug("ConsultaFLXConsultoraAction - setViewAtributes()");
		}

		MantenimientoEMPEmprendedoraService serviceEmp = (MantenimientoEMPEmprendedoraService) getBean("spusicc.mantenimientoEMPEmprendedoraService");

		MantenimientoFLXConsultoraService service = (MantenimientoFLXConsultoraService) getBean("spusicc.mantenimientoFLXConsultoraService");
		ConsultaHIPDatosClienteService service2 = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");
		ConsultaFLXConsultoraForm f = (ConsultaFLXConsultoraForm) this.formBusqueda;
		ConsultaHIPDatosCliente dtoDatosCliente = consultaHIPDatosClienteAction
				.getHipDtoDatosCliente();

		Map criteria = new HashMap();
		// Obtiene la campaña de proceso actual
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																// Campanha
																// Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																	// Campanha
																	// activa q
																	// se
																	// procesa
																	// actualmente
		MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceFact
				.getControlFacturacionById(criteria);
		String periodoActual = controlFacturacion.getCodigoPeriodo();

		f.setPeriodoActual(periodoActual);
		f.setCodigoPais(this.mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo());
		f.setCodConsultora(dtoDatosCliente.getCodigoCliente());

		Map params = new HashMap();
		params.put("codigoCliente", f.getCodConsultora());
		params.put("codigoPeriodo", periodoActual);
		params.put("codigoPais", this.mPantallaPrincipalBean
				.getCurrentCountry().getCodigo());
		String codigoPais = this.mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo();
		String codigoCliente = f.getCodConsultora();
		// CAMPAÑA DE FACTURACION
		String campanya = periodoActual;

		ConsultoraFlexipagoPK pk = new ConsultoraFlexipagoPK(codigoPais,
				codigoCliente, campanya);
		ConsultoraFlexipago consultora = service.getConsultora(pk);

		// Obtiene la lista de motivos de desactivacion
		List listaMotivos = service.getMotivos();
		this.setFlxMotivoList(listaMotivos);

		List listaDatosConsultora = service.getDatosConsultora(params);
		List listaDatosConsultoraDetalle = service
				.getDatosConsultoraDetalle(params);
		List listaDatosUsoFlexipago = service.getDatosUsosFlexipago(params);
		List lista = serviceEmp.getDatosConsultora(params);

		if (listaDatosConsultora.size() > 0) {

			Map map = new HashMap();
			Map mapDatosConsultora = (Map) listaDatosConsultora.get(0);

			f.setNomConsultora((String) mapDatosConsultora.get("nombreCliente"));
			f.setDesRegZonTerri((String) mapDatosConsultora.get("desRegion")
					+ '/' + (String) mapDatosConsultora.get("desZona") + '/'
					+ (String) mapDatosConsultora.get("desSeccion"));
			f.setPrimerPeriodo((String) mapDatosConsultora.get("periodoMin"));
			f.setUltimoPeriodo((String) mapDatosConsultora.get("periodoMax"));
			// f.setLineaCredito((String)mapDatosConsultora.get("lineaCredito"));
			f.setFechaActivacion((String) mapDatosConsultora
					.get("fechaActivacion"));
			f.setPeriodoActivacion((String) mapDatosConsultora
					.get("periodoActivacion"));
			// f.setFechaComunicacion((String)mapDatosConsultora.get("fechaComunicacion"));
			// f.setPeriodoComunicacion((String)mapDatosConsultora.get("periodoComunicacion"));
			f.setCalificacion((String) mapDatosConsultora.get("calificacion"));
			f.setEstadoContrato((String) mapDatosConsultora
					.get("estadoContrato"));
			f.setEstadoConsultora((String) mapDatosConsultora
					.get("estadoConsultora"));
			f.setMotivoRechazo((String) mapDatosConsultora.get("motivoRechazo"));
			f.setFechaRechazo((String) mapDatosConsultora.get("fechaRechazo"));
			f.setNumeroContrato((String) mapDatosConsultora
					.get("numeroContrato"));
			f.setFlagEstatus((String) mapDatosConsultora
					.get("estadoConsultora"));
			f.setCodigoCampanyaFacturacion(campanya);
			if (consultora != null) {
				f.setCampanyaComunicacion(reemplazarNulo(consultora
						.getCampanyaComunicacion()));
				// Evaluo el estatus
				if (consultora.getFlagActivo().equals("0")
						&& consultora.getFlagCancelado().equals("0")) {
					// Es Inactiva
					f.setFlagEstatus("0");
				}
				if (consultora.getFlagActivo().equals("1")) {
					// Es Activa
					f.setFlagEstatus("1");
				}
				if (consultora.getFlagActivo().equals("0")
						&& consultora.getFlagCancelado().equals("1")) {
					// Es Cancelada
					f.setFlagEstatus("2");
				}
				f.setFlagHabilitado(consultora.getFlagHabilitado());
				f.setFlagActivo(consultora.getFlagActivo());
				f.setFlagCancelado(consultora.getFlagCancelado());

			}

			/* para llenar datos adicionales RCR PER-SiCC-2013-1073 */
			Map criteria1 = new HashMap();
			oidCliente = service2.getOidCliente(params).toString();
			criteria1.put("oidCliente", service2.getOidCliente(params));
			criteria1.put("oidPeriodo", service2.getOidPeriodo(params));
			Map aplicaMontoMinimo = service2.getAplicaMontoMinimo(criteria1);
			Double montoMinimo = service2.getMontoMinimo(criteria1);

			Map criteria9 = new HashMap();
			criteria9.put("campanias", 1);
			criteria9.put("codigoPeriodo", periodoActual);
			String periodoSiguiente = service2
					.getDesPeriodoByCodigoPeriodoX(criteria9);

			Map criteria10 = new HashMap();
			criteria10.put("codigoPeriodo", periodoSiguiente);
			Integer oidPeriodoSiguiente = service2.getOidPeriodo(criteria10);

			Map criteria11 = new HashMap();
			criteria11.put("oidCliente", service2.getOidCliente(params));
			criteria11.put("oidPeriodo", oidPeriodoSiguiente);
			Map aplicaMontoMinimo1 = service2.getAplicaMontoMinimo(criteria11);
			Double montoMinimoSiguiente = service2.getMontoMinimo(criteria11);

			f.setPeriodo(periodoActual);
			f.setPeriodoSiguiente(periodoSiguiente);

			f.setPagoTotalSiguientePedido(service2.getSaldoPagar(criteria1));
			if (aplicaMontoMinimo == null || aplicaMontoMinimo.size() == 0) {
				f.setMontoMinimo("NO APLICA");
			} else {
				if (montoMinimo < 0) {
					f.setMontoMinimo("PAGADO");
				} else {
					f.setMontoMinimo(montoMinimo.toString());
				}
			}

			if (aplicaMontoMinimo1 == null || aplicaMontoMinimo1.size() == 0) {
				f.setMontoMinimoSiguiente("NO APLICA");
			} else {
				if (montoMinimo < 0) {
					f.setMontoMinimoSiguiente("PAGADO");
				} else {
					f.setMontoMinimoSiguiente(montoMinimoSiguiente.toString());
				}
			}
			/* Fin RCR PER-SiCC-2013-1073 */
		}

		if (listaDatosConsultoraDetalle.size() > 0) {
			Map map = new HashMap();
			Map mapDatosCliente = (Map) listaDatosConsultoraDetalle.get(0);
			if (mapDatosCliente != null) {
				f.setLineaCred((String) mapDatosCliente.get("lineaCredito"));
				f.setPedidoBase((String) mapDatosCliente.get("pedidoBase"));
			}
		}
		if ((lista != null) && (lista.size() > 0)) {

			Map map = new HashMap();
			Map mapDatosCliente = (Map) lista.get(0);
			if (mapDatosCliente != null) {

				f.setNombreConsultora((String) mapDatosCliente
						.get("nombreCliente"));

				f.setCodigoCliente((String) mapDatosCliente
						.get("codigoCliente"));

				f.setFechaNacimiento((String) mapDatosCliente
						.get("fechaNacimiento"));
				f.setMail((String) mapDatosCliente.get("email"));
				f.setDireccion((String) mapDatosCliente.get("direccion"));
				f.setCelular((String) mapDatosCliente.get("telefonoCelular"));

				if (mapDatosCliente.get("existeEmpresaria") != null) { // Existe
																		// como
																		// empresaria
																		// debe
																		// actualizar

					f.setNewRecord(false);

				}
			}
		}

		this.setFlxConsuDetalleList(listaDatosConsultoraDetalle);
		this.setFlxUsoDetalleList(listaDatosUsoFlexipago);

	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	private String reemplazarNulo(Object obj) {
		if (obj == null)
			return "";
		else
			return String.valueOf(obj);
	}

	/**
	 * 
	 * @param e
	 * @throws Exception
	 */
	public void ejecutarReporte(ActionEvent e) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ConsultaFLXConsultoraAction - ejecutarReporte()");
		}

		if (validaAntesReporte() > 0) {
			log.debug("Error en las validaciones.");
		} else {
			
			ReporteFLXDetalladoSaldosConsultoras2Form reporteForm = (ReporteFLXDetalladoSaldosConsultoras2Form)this.getReporteFLXDetalladoSaldosConsultoras2Action().getFormReporte();
			reporteForm.setFormatoExportacion("PDF");
			reporteForm.setCodigoCliente(((ConsultaFLXConsultoraForm) this.formBusqueda).getCodConsultora());
			reporteForm.setCodigoCampana(((ConsultaFLXConsultoraForm) this.formBusqueda).getPeriodoActual());			
			this.getReporteFLXDetalladoSaldosConsultoras2Action().setFormReporte(reporteForm);
			this.getReporteFLXDetalladoSaldosConsultoras2Action().setFormatoExportacion("PDF");
			
			this.getReporteFLXDetalladoSaldosConsultoras2Action().beforeExecuteReporte();
			this.getReporteFLXDetalladoSaldosConsultoras2Action().executeReporte(e);
		}
	}

	/**
	 * 
	 * @param e
	 */
	public void activar (ActionEvent e) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("activar");
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		ConsultaFLXConsultoraForm f = (ConsultaFLXConsultoraForm)this.formBusqueda;
		
		MantenimientoFLXConsultoraService service = (MantenimientoFLXConsultoraService)getBean("spusicc.mantenimientoFLXConsultoraService");
		MantenimientoOCRPedidoControlFacturacionService servicePeriodo = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		
		ConsultoraFlexipago consultora = new ConsultoraFlexipago();
		
		BeanUtils.copyProperties(consultora, f);
		consultora.setFlagActivo("1");
		consultora.setFlagCancelado("0");
		
		if(log.isDebugEnabled())
			log.debug(consultora);
		
		Map criteria = new HashMap();
		
		criteria.put("codigoCampanyaFacturacion", f.getCodigoCampanyaFacturacion());
		criteria.put("codigoCliente", f.getCodigoCliente());								
		
		service.updateConsultora(consultora, usuario);  
		
		this.addInfo("Info: ", this.getResourceMessage("mantenimientoFLXConsultoraForm.updated"));
        
        this.setEstadoGrabado(Constants.NUMERO_UNO);
		
	}

	/**
	 * 
	 * @param e
	 */
	public void desactivar (ActionEvent e) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("desactivar");
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		ConsultaFLXConsultoraForm f = (ConsultaFLXConsultoraForm)this.formBusqueda;
		
		MantenimientoFLXConsultoraService service = (MantenimientoFLXConsultoraService)getBean("spusicc.mantenimientoFLXConsultoraService");
		MantenimientoOCRPedidoControlFacturacionService servicePeriodo = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		
		ConsultoraFlexipago consultora = new ConsultoraFlexipago();
		
		BeanUtils.copyProperties(consultora, f);
		consultora.setFlagActivo("0");
		consultora.setFlagCancelado("1");
		
		if(log.isDebugEnabled())
			log.debug(consultora);
		
		Map criteria = new HashMap();
		
		criteria.put("codigoCampanyaFacturacion", f.getCodigoCampanyaFacturacion());
		criteria.put("codigoCliente", f.getCodigoCliente());
				
		// Si se desactiva cuando ya paso pedido y/o cerro su region, se desactiva a partir de la campaña siguiente
		if(f.getFlagEstatus().equals("1") && (service.getPasoPedido(criteria).equals("1") || service.getCerroRegion(criteria).equals("1"))){
			Map params = new HashMap();
			params.put("codigoPeriodo", f.getCodigoCampanyaFacturacion());
			params.put("numCampanhas",new BigDecimal(1)); // La siguiente campaña
			
			consultora.setCodigoCampanyaFacturacion(servicePeriodo.getPedidosNSiguienteCampanha(params));
		}
						
		consultora.setCodigoMotivo(f.getCodigoMotivo());
		
		service.updateConsultora(consultora, usuario);  
		
		this.addInfo("Info: ", this.getResourceMessage("mantenimientoFLXConsultoraForm.updated"));
		
        this.setEstadoGrabado(Constants.NUMERO_UNO);		
	}
	
	/**
	 * 
	 * @param e
	 */
	public void guardar (ActionEvent e){
		
		if(log.isDebugEnabled())
			log.debug("guardar");
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		ConsultaFLXConsultoraForm f = (ConsultaFLXConsultoraForm)this.formBusqueda;
		
		/*MantenimientoFLXConsultoraService service = (MantenimientoFLXConsultoraService)getBean("spusicc.mantenimientoFLXConsultoraService");
		MantenimientoOCRPedidoControlFacturacionService servicePeriodo = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		
		ConsultoraFlexipago consultora = new ConsultoraFlexipago();
		
		BeanUtils.copyProperties(consultora, f);
		
		if(log.isDebugEnabled())
			log.debug(consultora);
		
		Map criteria = new HashMap();
		
		
		// Si se desactiva cuando ya paso pedido y/o cerro su region, se desactiva a partir de la campaña siguiente
		if(f.getFlagEstatus().equals("1") && (service.getPasoPedido(criteria).equals("1") || service.getCerroRegion(criteria).equals("1"))){
			Map params = new HashMap();
			params.put("codigoPeriodo", f.getCodigoCampanyaFacturacion());
			params.put("numCampanhas",new BigDecimal(1)); // La siguiente campaña
			
		    f.setCodigoCampanyaFacturacion(servicePeriodo.getPedidosNSiguienteCampanha(params));
		}
		
		/**/
		MantenimientoEMPEmprendedoraService serviceEmp = (MantenimientoEMPEmprendedoraService) getBean("spusicc.mantenimientoEMPEmprendedoraService");
		Map criteria = new HashMap();
		criteria.put("oidCliente", oidCliente);
		criteria.put("origen","CHF");
		criteria.put("codigoUsuario", usuario.getLogin());
	    //Actualiza O Ingresa datos cliente mail, telefono casa, telefono celular
		
		//revisamos MAIL
		criteria.put("campo","ML");
		
		if(StringUtils.equals(serviceEmp.getTextoComunicacion(criteria), Constants.NUMERO_CERO)){
			ClienteComunicacion clienteComunicacion=new ClienteComunicacion();
			
			clienteComunicacion.setOidCliente(Long.parseLong(oidCliente));
			clienteComunicacion.setTextoComunicacion(f.getMail());
			clienteComunicacion.setOidTipoComunicacion(Long.parseLong(Constants.NUMERO_TRES));
			clienteComunicacion.setIndicadorPrincipal(Integer.parseInt(Constants.NUMERO_CERO));
			clienteComunicacion.setCodigoUsuario(usuario.getLogin());
			
			serviceEmp.insertClienteComunicacion(clienteComunicacion);
			
		}else{
			criteria.put("dato", f.getMail());
			serviceEmp.updateDatosCliente(criteria);
		}
		//revisamos CELULAR
		criteria.put("campo","TM");
		
		if(StringUtils.equals(serviceEmp.getTextoComunicacion(criteria), Constants.NUMERO_CERO)){
			ClienteComunicacion clienteComunicacion=new ClienteComunicacion();
			
			clienteComunicacion.setOidCliente(Long.parseLong(oidCliente));
			clienteComunicacion.setTextoComunicacion(f.getCelular());
			clienteComunicacion.setOidTipoComunicacion(Long.parseLong(Constants.NUMERO_SEIS));
			clienteComunicacion.setIndicadorPrincipal(Integer.parseInt(Constants.NUMERO_UNO));
			clienteComunicacion.setCodigoUsuario(usuario.getLogin());
			
			serviceEmp.insertClienteComunicacion(clienteComunicacion);
		}else{
			criteria.put("dato", f.getCelular());
			serviceEmp.updateDatosCliente(criteria);
		}
		/**/
				
		//service.updateConsultora(consultora, usuario);  
		
		this.addInfo("Info: ", this.getResourceMessage("mantenimientoFLXConsultoraForm.updated"));
		
        this.setEstadoGrabado(Constants.NUMERO_UNO);		
		
	}
	
	public int validaAntesReporte() {
		int i = 0;
		if (StringUtils.isBlank(((ConsultaFLXConsultoraForm) this.formBusqueda)
				.getCodConsultora())
				&& StringUtils
						.isEmpty(((ConsultaFLXConsultoraForm) this.formBusqueda)
								.getCodConsultora())) {
			i++;
		}
		if (StringUtils.isBlank(((ConsultaFLXConsultoraForm) this.formBusqueda)
				.getPeriodoActual())
				&& StringUtils
						.isEmpty(((ConsultaFLXConsultoraForm) this.formBusqueda)
								.getPeriodoActual())) {
			i++;
		}
		return i;
	}

	public ConsultaHIPDatosClienteAction getConsultaHIPDatosClienteAction() {
		return consultaHIPDatosClienteAction;
	}

	public void setConsultaHIPDatosClienteAction(
			ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction) {
		this.consultaHIPDatosClienteAction = consultaHIPDatosClienteAction;
	}

	public List getFlxConsuDetalleList() {
		return flxConsuDetalleList;
	}

	public void setFlxConsuDetalleList(List flxConsuDetalleList) {
		this.flxConsuDetalleList = flxConsuDetalleList;
	}

	public ReporteFLXDetalladoSaldosConsultoras2Action getReporteFLXDetalladoSaldosConsultoras2Action() {
		return reporteFLXDetalladoSaldosConsultoras2Action;
	}

	public void setReporteFLXDetalladoSaldosConsultoras2Action(
			ReporteFLXDetalladoSaldosConsultoras2Action reporteFLXDetalladoSaldosConsultoras2Action) {
		this.reporteFLXDetalladoSaldosConsultoras2Action = reporteFLXDetalladoSaldosConsultoras2Action;
	}

	/**
	 * @return the flxMotivoList
	 */
	public List getFlxMotivoList() {
		return flxMotivoList;
	}

	/**
	 * @param flxMotivoList
	 *            the flxMotivoList to set
	 */
	public void setFlxMotivoList(List flxMotivoList) {
		this.flxMotivoList = flxMotivoList;
	}

	/**
	 * @return the oidCliente
	 */
	public String getOidCliente() {
		return oidCliente;
	}

	/**
	 * @param oidCliente
	 *            the oidCliente to set
	 */
	public void setOidCliente(String oidCliente) {
		this.oidCliente = oidCliente;
	}

	/**
	 * @return the flxUsoDetalleList
	 */
	public List getFlxUsoDetalleList() {
		return flxUsoDetalleList;
	}

	/**
	 * @param flxUsoDetalleList
	 *            the flxUsoDetalleList to set
	 */
	public void setFlxUsoDetalleList(List flxUsoDetalleList) {
		this.flxUsoDetalleList = flxUsoDetalleList;
	}

	/**
	 * @return the estadoGrabado
	 */
	public String getEstadoGrabado() {
		return estadoGrabado;
	}

	/**
	 * @param estadoGrabado the estadoGrabado to set
	 */
	public void setEstadoGrabado(String estadoGrabado) {
		this.estadoGrabado = estadoGrabado;
	}

}