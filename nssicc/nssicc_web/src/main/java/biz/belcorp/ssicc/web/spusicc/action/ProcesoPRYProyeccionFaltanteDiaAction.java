package biz.belcorp.ssicc.web.spusicc.action;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.ProcesoBatchActu;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ProcesoBatchService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.ProcesoPRYProyeccionFaltanteDiaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.form.ProcesoPRYProyeccionFaltanteDiaDetalleForm;
import biz.belcorp.ssicc.web.spusicc.form.ProcesoPRYProyeccionFaltanteDiaForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.action.MantenimientoOCRCargaPedidosAction;

@ManagedBean  
@SessionScoped
public class ProcesoPRYProyeccionFaltanteDiaAction extends BaseProcesoAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3540058092117976177L;
	
	private List resultadoProyeccionFaltanteList= new ArrayList();
	private List detallePRYProyeccionFaltanteList = new ArrayList();
	private DataTableModel dtProcesoPRYProyeccionFaltanteList; 
	private DataTableModel dtDetallePRYProyeccionFaltanteList; 
	private ProcesoPRYProyeccionFaltanteDiaDetalleForm detalleForm;
	private Object registroSeleccionadoObject;
	private String mensajeProceso;
	private String mostrarPrimerMensaje;
	private Boolean mostrarPrimerMensajeBoolean;
	private Boolean mostrarSegundoMensajeBoolean;
	
	@ManagedProperty(value="#{procesoPRYProyeccionFaltanteDiaPopupAction}")	
	private ProcesoPRYProyeccionFaltanteDiaPopupAction  procesoPRYProyeccionFaltanteDiaPopupAction ;

	
	
	
	/**
	 * @return the mostrarSegundoMensajeBoolean
	 */
	public Boolean getMostrarSegundoMensajeBoolean() {
		return mostrarSegundoMensajeBoolean;
	}

	/**
	 * @param mostrarSegundoMensajeBoolean the mostrarSegundoMensajeBoolean to set
	 */
	public void setMostrarSegundoMensajeBoolean(Boolean mostrarSegundoMensajeBoolean) {
		this.mostrarSegundoMensajeBoolean = mostrarSegundoMensajeBoolean;
	}

	/**
	 * @return the mostrarPrimerMensaje
	 */
	public String getMostrarPrimerMensaje() {
		return mostrarPrimerMensaje;
	}

	/**
	 * @param mostrarPrimerMensaje the mostrarPrimerMensaje to set
	 */
	public void setMostrarPrimerMensaje(String mostrarPrimerMensaje) {
		this.mostrarPrimerMensaje = mostrarPrimerMensaje;
	}

	/**
	 * @return the mostrarPrimerMensajeBoolean
	 */
	public Boolean getMostrarPrimerMensajeBoolean() {
		return mostrarPrimerMensajeBoolean;
	}

	/**
	 * @param mostrarPrimerMensajeBoolean the mostrarPrimerMensajeBoolean to set
	 */
	public void setMostrarPrimerMensajeBoolean(Boolean mostrarPrimerMensajeBoolean) {
		this.mostrarPrimerMensajeBoolean = mostrarPrimerMensajeBoolean;
	}

	/**
	 * @return the mensajeProceso
	 */
	public String getMensajeProceso() {
		return mensajeProceso;
	}

	/**
	 * @param mensajeProceso the mensajeProceso to set
	 */
	public void setMensajeProceso(String mensajeProceso) {
		this.mensajeProceso = mensajeProceso;
	}

	/**
	 * @return the registroSeleccionadoObject
	 */
	public Object getRegistroSeleccionadoObject() {
		return registroSeleccionadoObject;
	}

	/**
	 * @param registroSeleccionadoObject the registroSeleccionadoObject to set
	 */
	public void setRegistroSeleccionadoObject(Object registroSeleccionadoObject) {
		this.registroSeleccionadoObject = registroSeleccionadoObject;
	}

	/**
	 * @return the procesoPRYProyeccionFaltanteDiaPopupAction
	 */
	public ProcesoPRYProyeccionFaltanteDiaPopupAction getProcesoPRYProyeccionFaltanteDiaPopupAction() {
		return procesoPRYProyeccionFaltanteDiaPopupAction;
	}

	/**
	 * @param procesoPRYProyeccionFaltanteDiaPopupAction the procesoPRYProyeccionFaltanteDiaPopupAction to set
	 */
	public void setProcesoPRYProyeccionFaltanteDiaPopupAction(
			ProcesoPRYProyeccionFaltanteDiaPopupAction procesoPRYProyeccionFaltanteDiaPopupAction) {
		this.procesoPRYProyeccionFaltanteDiaPopupAction = procesoPRYProyeccionFaltanteDiaPopupAction;
	}

	/**
	 * @return the detalleForm
	 */
	public ProcesoPRYProyeccionFaltanteDiaDetalleForm getDetalleForm() {
		return detalleForm;
	}

	/**
	 * @param detalleForm the detalleForm to set
	 */
	public void setDetalleForm(
			ProcesoPRYProyeccionFaltanteDiaDetalleForm detalleForm) {
		this.detalleForm = detalleForm;
	}

	/**
	 * @return the detallePRYProyeccionFaltanteList
	 */
	public List getDetallePRYProyeccionFaltanteList() {
		return detallePRYProyeccionFaltanteList;
	}

	/**
	 * @param detallePRYProyeccionFaltanteList the detallePRYProyeccionFaltanteList to set
	 */
	public void setDetallePRYProyeccionFaltanteList(
			List detallePRYProyeccionFaltanteList) {
		this.detallePRYProyeccionFaltanteList = detallePRYProyeccionFaltanteList;
	}

	/**
	 * @return the dtDetallePRYProyeccionFaltanteList
	 */
	public DataTableModel getDtDetallePRYProyeccionFaltanteList() {
		return dtDetallePRYProyeccionFaltanteList;
	}

	/**
	 * @param dtDetallePRYProyeccionFaltanteList the dtDetallePRYProyeccionFaltanteList to set
	 */
	public void setDtDetallePRYProyeccionFaltanteList(
			DataTableModel dtDetallePRYProyeccionFaltanteList) {
		this.dtDetallePRYProyeccionFaltanteList = dtDetallePRYProyeccionFaltanteList;
	}

	/**
	 * @return the dtProcesoPRYProyeccionFaltanteList
	 */
	public DataTableModel getDtProcesoPRYProyeccionFaltanteList() {
		return dtProcesoPRYProyeccionFaltanteList;
	}

	/**
	 * @param dtProcesoPRYProyeccionFaltanteList the dtProcesoPRYProyeccionFaltanteList to set
	 */
	public void setDtProcesoPRYProyeccionFaltanteList(
			DataTableModel dtProcesoPRYProyeccionFaltanteList) {
		this.dtProcesoPRYProyeccionFaltanteList = dtProcesoPRYProyeccionFaltanteList;
	}

	/**
	 * @return the resultadoProyeccionFaltanteList
	 */
	public List getResultadoProyeccionFaltanteList() {
		return resultadoProyeccionFaltanteList;
	}

	/**
	 * @param resultadoProyeccionFaltanteList the resultadoProyeccionFaltanteList to set
	 */
	public void setResultadoProyeccionFaltanteList(
			List resultadoProyeccionFaltanteList) {
		this.resultadoProyeccionFaltanteList = resultadoProyeccionFaltanteList;
	}

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoPRYProyeccionFaltanteDiaForm form = new ProcesoPRYProyeccionFaltanteDiaForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		
		/* Invocando al metodo del proceso de cargo y abono */
		ProcesoPRYProyeccionFaltanteDiaService service = (ProcesoPRYProyeccionFaltanteDiaService) getBean("spusicc.procesoPRYProyeccionFaltanteDiaService");
		service.executeProyeccionFaltante(params);
		log.debug("Mostrando el numero de Lote " + params.get("numeroLote"));
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		this.mostrarPrimerMensajeBoolean=false;
		this.mostrarPrimerMensaje="";
		this.mostrarSegundoMensajeBoolean=false;
		this.resultadoProyeccionFaltanteList.clear();
		this.mostrarBotonExecute=false;
		ProcesoPRYProyeccionFaltanteDiaForm f = (ProcesoPRYProyeccionFaltanteDiaForm) this.formProceso;
		String codigoSistema   = "PRY";
		f.setCodigoSistema(codigoSistema);
		f.setNumeroVersion("1");
		Map criteria = BeanUtils.describe(f);
		ProcesoBatchService service = (ProcesoBatchService) getBean("scsicc.procesoBatchService");
		List resultado = service.getProcesoBatchActuByCriteria(criteria);
		if (resultado.size() > 0) {
			ProcesoBatchActu procesoBatchActu = (ProcesoBatchActu) resultado.get(0);
			
			/* En caso siga ejecutandose el proceso */
			if (Constants.INDICADOR_EJECUCION_PROCESO_BATCH_SI.equals(procesoBatchActu.getIndicadorEjecucion())) {

				setMostrarPrimerMensaje(this.getResourceMessage("interfazSiCC.archivo.ejecutandosePresioneAqui"));
				setMostrarSegundoMensajeBoolean(true);
				this.redireccionarPagina("procesoPRYProyeccionFaltanteDiaResultado");
			}
		}	
		
		/* Seteando Fecha de Facturacion */
		f.setFechaFacturacionD(new Date());
		
	}
	

	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(
			Map<String, Object> params, BaseForm form) throws Exception {
		
		ProcesoPRYProyeccionFaltanteDiaForm f = (ProcesoPRYProyeccionFaltanteDiaForm) this.formProceso;
		f.setFechaFacturacion(DateUtil.convertDateToString(f.getFechaFacturacionD()));	
		params= super.prepareParamsBeforeExecute(params, form);
		
		
		/* obteniendo codigo del periodo */
		Map criteria = new HashMap();			
		criteria.put("fechaFacturacion",  f.getFechaFacturacion());		
		ProcesoPRYProyeccionFaltanteDiaService service = (ProcesoPRYProyeccionFaltanteDiaService) getBean("spusicc.procesoPRYProyeccionFaltanteDiaService");
		String codigoPeriodo = service.getPeriodoActual(criteria);
		String descripcionPeriodo = service.getDevuelveDescripcionPeriodo(criteria);
		
		params.put("codigoPeriodo", codigoPeriodo);	
		params.put("descripcionPeriodo", descripcionPeriodo);
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		params.put("codIso", usuario.getIdioma().getCodigoISO());
		
		return params;
	}
	
	@Override
	protected void setVerificarValidacionParametriaMenu() {
		String codigoSistema   = Constants.PRY_CODIGO_SISTEMA;
		ProcesoPRYProyeccionFaltanteDiaForm f = (ProcesoPRYProyeccionFaltanteDiaForm) this.formProceso;
		f.setCodigoSistema(codigoSistema);
	}
	
	@Override
	protected String getExecuteForward() throws Exception {
		resultado();
		return super.getExecuteForward();
	}
	
	public void resultado(){
		
		try {
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

			/* Seteando valores */
			ProcesoPRYProyeccionFaltanteDiaForm f = (ProcesoPRYProyeccionFaltanteDiaForm) this.formProceso;
			this.detalleForm = new ProcesoPRYProyeccionFaltanteDiaDetalleForm();
			this.resultadoProyeccionFaltanteList.clear();
			this.dtProcesoPRYProyeccionFaltanteList= new DataTableModel(this.resultadoProyeccionFaltanteList);
			
			f.setCodigoPais(pais.getCodigo());
		
			
			/* Seteando codigo de Periodo */ 
			Map criteriaPeriodo = new HashMap();
			criteriaPeriodo.put("fechaFacturacion",  f.getFechaFacturacion());
			ProcesoPRYProyeccionFaltanteDiaService servicePeriodo = (ProcesoPRYProyeccionFaltanteDiaService) 
						getBean("spusicc.procesoPRYProyeccionFaltanteDiaService");
			String codigoPeriodo = servicePeriodo.getPeriodoActual(criteriaPeriodo);
			String descripcionPeriodo = servicePeriodo.getDevuelveDescripcionPeriodo(criteriaPeriodo);
			f.setCodigoPeriodo(codigoPeriodo);
			f.setDescripcionPeriodo(descripcionPeriodo);
			
			/* Pasando valores del Form al Map */
 			Map criteria = BeanUtils.describe(f);
			
			/* Buscando proceso Batch */
 			ProcesoBatchService service = (ProcesoBatchService) getBean("scsicc.procesoBatchService");
			List resultado = service.getProcesoBatchActuByCriteria(criteria);
			if (resultado.size() > 0) {
				ProcesoBatchActu procesoBatchActu = (ProcesoBatchActu) resultado.get(0);
				
				/* En caso siga ejecutandose el proceso */
				if (Constants.INDICADOR_EJECUCION_PROCESO_BATCH_SI.equals(procesoBatchActu.getIndicadorEjecucion())) {
					setMostrarPrimerMensaje(this.getResourceMessage("interfazSiCC.archivo.ejecutandosePresioneAqui"));
					setMostrarSegundoMensajeBoolean(true);
				}
				
				/* En caso ya se terminó la ejecucion del proceso */
				else {
					/* Buscando ultima version del dia */ 
					ProcesoPRYProyeccionFaltanteDiaService serviceProy = (ProcesoPRYProyeccionFaltanteDiaService) 
					      getBean("spusicc.procesoPRYProyeccionFaltanteDiaService");
					String numeroVersion = serviceProy.getMaximoVersionProyeccionFaltanteDia(criteria);
					f.setNumeroVersion(numeroVersion);
					criteria.put("numeroVersion", numeroVersion);
					
					/* Verificando si tiene Indicadores críticos */
					Integer valor = serviceProy.existeIndicadorValorCritico(criteria);
					
					if (valor.intValue() > 0 ) {
						setMostrarPrimerMensaje(this.getResourceMessage("interfazSiCC.archivo.proyeccionFaltanteIndicadorCritico"));
						setMostrarPrimerMensajeBoolean(true);
						setMostrarSegundoMensajeBoolean(false);
					}
					else {
						setMostrarPrimerMensaje(this.getResourceMessage("interfazSiCC.archivo.proyeccionFaltanteIndicadorOK"));
						setMostrarPrimerMensajeBoolean(true);
						setMostrarSegundoMensajeBoolean(false);
					}
					
					/* obteniendo lista de la ejecucion del proceso */
					List lista = serviceProy.getProyeccionFaltanteGrupo(criteria);
					this.dtProcesoPRYProyeccionFaltanteList = new DataTableModel(lista);
					Integer totalOC = serviceProy.getTotalOC(criteria);
					f.setTotalSolicitud(totalOC);
					
				}
			}
			
			this.redireccionarPagina("procesoPRYProyeccionFaltanteDiaResultado");
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	

	
	//Metodo que abre el pop
	public void openPopupProducto(ActionEvent event){
		try {
				
			this.procesoPRYProyeccionFaltanteDiaPopupAction.detalle();
				
				
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}	
	}
	
	//Metodo que abre el pop
		public void openPopupTodos(ActionEvent event){
			try {
					
				this.procesoPRYProyeccionFaltanteDiaPopupAction.generaldetalle();
					
					
			} catch (Exception e) {
				this.addError("Error: ", this.obtieneMensajeErrorException(e));
			}	
		}
	

	/**
	 * @param evt
	 */
	public void confirmarProceso(ActionEvent evt) {
		try {
			String ventana = "confirmDialogCerrar";
			String ventanaConfirmar = "PF('" + ventana
					+ "_confirmationDialogConfirmar').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
			return;
		}
	}
	
	/**
	 * @param evt
	 */
	public void confirmarProcesoPosterior(ActionEvent evt) {
		try {
			ProcesoPRYProyeccionFaltanteDiaForm f = (ProcesoPRYProyeccionFaltanteDiaForm) this.formProceso;
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			f.setFechaFacturacion(DateUtil.convertDateToString(f.getFechaFacturacionD()));	
			String validaCodigo = aSvc.getExisteProyeccionFaltanteDia(f.getCodigoPais(), f.getFechaFacturacion());
			
			Integer intValidaCodigo = Integer.parseInt(validaCodigo);
			String mensaje =  this.getResourceMessage("confirm.execute.ProyeccionFaltante");

			this.mensajeProceso = null;
			if (intValidaCodigo.intValue()!=0) {
				this.mensajeProceso = mensaje;
				String ventana = "confirmarProcesoDialog";
				String ventanaConfirmar = "PF('" + ventana
						+ "_confirmationDialogConfirmarSalir').show()";
				this.getRequestContext().execute(ventanaConfirmar);
				return;
			} else {
				String ventana = "confirmDialogCerrar";
				String ventanaConfirmar = "PF('" + ventana
						+ "_confirmationDialogConfirmarSalir').show()";
				this.getRequestContext().execute(ventanaConfirmar);
				return;
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
			return;
		}

	}
	
	public void resultado(ActionEvent event){
		if (log.isDebugEnabled()) {
			log.debug("resultado");
		}
		try {
			this.resultado();
			this.mostrarBotonExecute = false;
			this.mostrarBotonLimpiar = false;
		}catch (Exception e1) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e1));
		}	
	}
	
	public void ejecucionProcesoProyeccion(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("ejecucionProcesoProyeccion");
		}
		try {
			executeProceso(event);
			this.mostrarBotonExecute = false;
			this.mostrarBotonLimpiar = false;

		} catch (Exception e1) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e1));
		}
	}

}
