package biz.belcorp.ssicc.web.spusicc.flx.action;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.flx.model.ConsultoraFlexipago;
import biz.belcorp.ssicc.dao.spusicc.flx.model.ConsultoraFlexipagoAuditoria;
import biz.belcorp.ssicc.dao.spusicc.flx.model.ConsultoraFlexipagoPK;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.spusicc.flx.MantenimientoFLXConsultoraService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.util.ExcelUtil;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.scsicc.action.ReporteFLXGestionarConsultoraAction;
import biz.belcorp.ssicc.web.spusicc.flx.form.MantenimientoFLXConsultoraForm;
import biz.belcorp.ssicc.web.spusicc.flx.form.MantenimientoFLXConsultoraProcesoMasivoForm;
import biz.belcorp.ssicc.web.spusicc.flx.form.MantenimientoFLXConsultoraSearchForm;

@ManagedBean
@SessionScoped
public class MantenimientoFLXConsultoraSearchAction extends BaseMantenimientoSearchAbstractAction{

	private static final long serialVersionUID = 1L;
	
	private DataTableModel dataTableResultado = new DataTableModel();
	private DataTableModel cargaMasivaModel = new DataTableModel();
	private List flxCalificacionComportamientoList = new ArrayList();
	private List flxCalificacionExperienciaList = new ArrayList();
	private List flxConsultoraCargaMasivaList = new ArrayList();
	private List flxMotivoList = new ArrayList();
	private MantenimientoFLXConsultoraForm objectSeleccionado = new MantenimientoFLXConsultoraForm();
	private String flxConsultoraCargaMasivaErroneos = "";
	private String attachment;
	
	@ManagedProperty(value = "#{reporteFLXGestionarConsultoraAction}")
	ReporteFLXGestionarConsultoraAction reporteFLXGestionarConsultoraAction;
	
	
	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoFLXConsultoraSearchForm f = new MantenimientoFLXConsultoraSearchForm();
		return f;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setFindAttributes");
		}
		MantenimientoFLXConsultoraSearchForm f = (MantenimientoFLXConsultoraSearchForm)this.formBusqueda;
		f.setCodigoPais(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		MantenimientoFLXConsultoraService service = (MantenimientoFLXConsultoraService)getBean("spusicc.mantenimientoFLXConsultoraService");
		Map criteria = BeanUtils.describe(f);
		List lista = service.getConsultorasByCriteria(criteria);
		return lista;
	}
	
	

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		
		MantenimientoFLXConsultoraService service = (MantenimientoFLXConsultoraService)getBean("spusicc.mantenimientoFLXConsultoraService");
		
		List listaCalificacionComportamiento = service.getCalificacionesComportamiento();		
		List listaCalificacionExperiencia = service.getCalificacionesExperiencia();
		
		this.setFlxCalificacionComportamientoList(listaCalificacionComportamiento);
		this.setFlxCalificacionExperienciaList(listaCalificacionExperiencia);
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonSalir = false;
		this.mostrarBotonSave = false;
		this.mostrarListaBusqueda=false;
		
	}
	
	public void openPopup(String codigoPais, String codigoCliente, String campanya){
		if(log.isDebugEnabled()){
			log.debug("openPopup");
		}		
		if(!StringUtils.isBlank(codigoPais) && !StringUtils.isBlank(codigoCliente) && !StringUtils.isBlank(campanya)){
			MantenimientoFLXConsultoraService service = (MantenimientoFLXConsultoraService)getBean("spusicc.mantenimientoFLXConsultoraService");			
			
			ConsultoraFlexipagoPK pk = new ConsultoraFlexipagoPK(codigoPais, codigoCliente, campanya);
			ConsultoraFlexipago consultora = service.getConsultora(pk);
									
			try {
				BeanUtils.copyProperties(objectSeleccionado, consultora);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Evaluo el estatus
			if (objectSeleccionado.getFlagActivo().equals("0") && objectSeleccionado.getFlagCancelado().equals("0")){
				// Es Inactiva
				objectSeleccionado.setFlagEstatus("0");
			}
			if (objectSeleccionado.getFlagActivo().equals("1")){
				// Es Activa
				objectSeleccionado.setFlagEstatus("1");
			}
			if (objectSeleccionado.getFlagActivo().equals("0") && objectSeleccionado.getFlagCancelado().equals("1")){
				// Es Cancelada
				objectSeleccionado.setFlagEstatus("2");
			}
			
			// Obtiene la lista de motivos de desactivacion
			List listaMotivos = service.getMotivos();			
			this.flxMotivoList = listaMotivos;
						
			//Obtenemos los detalles de modificacion
			ConsultoraFlexipagoAuditoria auditActivar = service.getConsultoraAuditoriaByCodigosAccion(codigoPais, codigoCliente, Constants.FLX_CODIGO_ACCION_ACTIVAR, Constants.FLX_CODIGO_ACCION_DESACTIVAR);
			ConsultoraFlexipagoAuditoria auditRechazar = service.getConsultoraAuditoriaByCodigosAccion(codigoPais, codigoCliente, Constants.FLX_CODIGO_ACCION_REGISTRAR_OBJECION, Constants.FLX_CODIGO_ACCION_ANULAR_OBJECION);
			
			objectSeleccionado.setUltimaModificacionActivar("");
			if(!StringUtils.isBlank(auditActivar.getUsuarioAccion())){
				objectSeleccionado.setUltimaModificacionActivar(
						String.format("%s %s %s", 
								auditActivar.getCampanyaFacturacion(), 
								auditActivar.getUsuarioAccion(), 
								DateUtil.convertDateToString("dd/MM/yyyy HH:mm:ss", 
										auditActivar.getFechaAccion())));
			}
			this.getRequestContext().execute("PF('popupFlexMantConsul').show()");
		}
	}
	
	public void activar(ActionEvent event){
		if(log.isDebugEnabled()){
			log.debug("activar");
		}
		
		Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
		
		MantenimientoFLXConsultoraService service = (MantenimientoFLXConsultoraService)getBean("spusicc.mantenimientoFLXConsultoraService");
		MantenimientoOCRPedidoControlFacturacionService servicePeriodo = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		
		ConsultoraFlexipago consultora = new ConsultoraFlexipago();
		
		try {
			BeanUtils.copyProperties(consultora, objectSeleccionado);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		consultora.setFlagActivo("1");
		consultora.setFlagCancelado("0");
		
		if(log.isDebugEnabled())
			log.debug(consultora);
		
		Map criteria = new HashMap();
		
		criteria.put("codigoCampanyaFacturacion", objectSeleccionado.getCodigoCampanyaFacturacion());
		criteria.put("codigoCliente", objectSeleccionado.getCodigoCliente());								
		
		service.updateConsultora(consultora, usuario);
		find();
		this.getRequestContext().execute("PF('popupFlexMantConsul').hide()");		
		this.addInfo("Info:", this.getResourceMessage("mantenimientoFLXConsultoraForm.updated"));		
	}
	
	public void desactivar(ActionEvent event){
		if(log.isDebugEnabled()){
			log.debug("desactivar");
		}
		
		Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
		
		MantenimientoFLXConsultoraService service = (MantenimientoFLXConsultoraService)getBean("spusicc.mantenimientoFLXConsultoraService");
		MantenimientoOCRPedidoControlFacturacionService servicePeriodo = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		
		ConsultoraFlexipago consultora = new ConsultoraFlexipago();
		
		try {
			BeanUtils.copyProperties(consultora, objectSeleccionado);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		consultora.setFlagActivo("0");
		consultora.setFlagCancelado("1");
		
		if(log.isDebugEnabled())
			log.debug(consultora);
		
		Map criteria = new HashMap();
		
		criteria.put("codigoCampanyaFacturacion", objectSeleccionado.getCodigoCampanyaFacturacion());
		criteria.put("codigoCliente", objectSeleccionado.getCodigoCliente());
				
		// Si se desactiva cuando ya paso pedido y/o cerro su region, se desactiva a partir de la campaña siguiente
		if(objectSeleccionado.getFlagEstatus().equals("1") 
				&& (service.getPasoPedido(criteria).equals("1") 
						|| service.getCerroRegion(criteria).equals("1"))){
			Map params = new HashMap();
			params.put("codigoPeriodo", objectSeleccionado.getCodigoCampanyaFacturacion());
			params.put("numCampanhas",new BigDecimal(1)); // La siguiente campaña
			
			consultora.setCodigoCampanyaFacturacion(servicePeriodo.getPedidosNSiguienteCampanha(params));
		}
						
		consultora.setCodigoMotivo(objectSeleccionado.getCodigoMotivo());
		
		service.updateConsultora(consultora, usuario);
		find();
		this.getRequestContext().execute("PF('popupFlexMantConsul').hide()");	
		this.addInfo("Info:", this.getResourceMessage("mantenimientoFLXConsultoraForm.updated"));
	}
	
	/**
	 * Ejecuta reporte
	 * 
	 * @param event
	 */
	public void ejecutarReporteCSV(ActionEvent event){

		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'ejecutarReporte' method");
		}			
		
		MantenimientoFLXConsultoraSearchForm f = (MantenimientoFLXConsultoraSearchForm)this.formBusqueda;
		f.setFormatoExportacion("OCSV");
        log.debug("formato exportacion " + f.getFormatoExportacion());		
        
        this.reporteFLXGestionarConsultoraAction.setCampanyaComunicacion(f.getCampanyaComunicacion());
        this.reporteFLXGestionarConsultoraAction.setCampanyaFacturacion(f.getCampanyaFacturacion());
        this.reporteFLXGestionarConsultoraAction.setCodigoCliente(f.getCodigoCliente());
        this.reporteFLXGestionarConsultoraAction.setCodigoCalificacionComportamiento(f.getCodigoCalificacionComportamiento());
        this.reporteFLXGestionarConsultoraAction.setCodigoCalificacionExperiencia(f.getCodigoCalificacionExperiencia());
        this.reporteFLXGestionarConsultoraAction.inicializarValores();        
        this.reporteFLXGestionarConsultoraAction.setFormatoExportacion(f.getFormatoExportacion());
        this.reporteFLXGestionarConsultoraAction.getFormReporte().setFormatoExportacion(f.getFormatoExportacion());
        this.reporteFLXGestionarConsultoraAction.executeReporteNoJASPER();
        
	}

	public List getFlxCalificacionComportamientoList() {
		return flxCalificacionComportamientoList;
	}

	public void setFlxCalificacionComportamientoList(
			List flxCalificacionComportamientoList) {
		this.flxCalificacionComportamientoList = flxCalificacionComportamientoList;
	}

	public List getFlxCalificacionExperienciaList() {
		return flxCalificacionExperienciaList;
	}

	public void setFlxCalificacionExperienciaList(
			List flxCalificacionExperienciaList) {
		this.flxCalificacionExperienciaList = flxCalificacionExperienciaList;
	}

	public DataTableModel getDataTableResultado() {
		return dataTableResultado;
	}

	public void setDataTableResultado(DataTableModel dataTableResultado) {
		this.dataTableResultado = dataTableResultado;
	}

	public List getFlxMotivoList() {
		return flxMotivoList;
	}

	public void setFlxMotivoList(List flxMotivoList) {
		this.flxMotivoList = flxMotivoList;
	}

	public MantenimientoFLXConsultoraForm getObjectSeleccionado() {
		return objectSeleccionado;
	}

	public void setObjectSeleccionado(
			MantenimientoFLXConsultoraForm objectSeleccionado) {
		this.objectSeleccionado = objectSeleccionado;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public List getFlxConsultoraCargaMasivaList() {
		return flxConsultoraCargaMasivaList;
	}

	public void setFlxConsultoraCargaMasivaList(List flxConsultoraCargaMasivaList) {
		this.flxConsultoraCargaMasivaList = flxConsultoraCargaMasivaList;
	}

	public String getFlxConsultoraCargaMasivaErroneos() {
		return flxConsultoraCargaMasivaErroneos;
	}

	public void setFlxConsultoraCargaMasivaErroneos(
			String flxConsultoraCargaMasivaErroneos) {
		this.flxConsultoraCargaMasivaErroneos = flxConsultoraCargaMasivaErroneos;
	}

	public DataTableModel getCargaMasivaModel() {
		return cargaMasivaModel;
	}

	public void setCargaMasivaModel(DataTableModel cargaMasivaModel) {
		this.cargaMasivaModel = cargaMasivaModel;
	}

	/**
	 * @return the reporteFLXGestionarConsultoraAction
	 */
	public ReporteFLXGestionarConsultoraAction getReporteFLXGestionarConsultoraAction() {
		return reporteFLXGestionarConsultoraAction;
	}

	/**
	 * @param reporteFLXGestionarConsultoraAction the reporteFLXGestionarConsultoraAction to set
	 */
	public void setReporteFLXGestionarConsultoraAction(
			ReporteFLXGestionarConsultoraAction reporteFLXGestionarConsultoraAction) {
		this.reporteFLXGestionarConsultoraAction = reporteFLXGestionarConsultoraAction;
	}
}
