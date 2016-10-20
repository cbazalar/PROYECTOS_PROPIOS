package biz.belcorp.ssicc.web.spusicc.mav.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.mav.MantenimientoMAVConfiguracionService;
import biz.belcorp.ssicc.service.spusicc.mav.ProcesoMAVGenerarEnviosService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.mav.form.ProcesoMAVGenerarEnviosForm;

@ManagedBean
@SessionScoped
public class ProcesoMAVGenerarEnviosAction extends BaseProcesoAbstractAction {

	private static final long serialVersionUID = -3817444075780394300L;
	private static final String CODIGO_SISTEMA = "MAV";
	private static final String CODIGO_PROCESO_BATCH = "03";
	
	private List mavActividadesList;
	private List siccTipoMavList;
	
	private String paginaPadre;

	private boolean codigoActividadDisabled;
	
	
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoMAVGenerarEnviosForm form=new ProcesoMAVGenerarEnviosForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		ProcesoMAVGenerarEnviosService service = (ProcesoMAVGenerarEnviosService) getBean("spusicc.procesoMAVGenerarEnviosService");
		service.executeGenerarEnvios(params);
		return params;
	}
	
	@Override
    protected void setVerificarValidacionParametriaMenu() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("codigoSistema", this.CODIGO_SISTEMA);
		params.put("codigoProcesoBatch", this.CODIGO_PROCESO_BATCH);
		this.parametrosPantalla.putAll(params);
		this.codigoProcesoBatch = this.CODIGO_PROCESO_BATCH;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();

		ProcesoMAVGenerarEnviosForm f = (ProcesoMAVGenerarEnviosForm) this.formProceso;
		
		MantenimientoMAVConfiguracionService service = (MantenimientoMAVConfiguracionService) 
				this.getBean("spusicc.mantenimientoMAVConfiguracionService");

		//combo actividades
		this.mavActividadesList = service.getActividades();
		
		Map criteria= new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
		MantenimientoOCRPedidoControlFacturacionService serviceOCR = 
				(MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceOCR.getControlFacturacionById(criteria);

		// Carga el periodo proceso
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		this.siccTipoMavList = reporteService.getTipoMav();
	}

	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,BaseForm form) throws Exception {
		params=super.prepareParamsBeforeExecute(params,form);
		
		ProcesoMAVGenerarEnviosService service = (ProcesoMAVGenerarEnviosService)  
				getBean("spusicc.procesoMAVGenerarEnviosService");
		
		boolean existeMatriz =  service.getExisteMatrizFacturacion(params);
		
		if(!existeMatriz) {
			String mensaje = this.getResourceMessage("procesoMAVGenerarEnviosForm.msg.NoExisteMatrizFacturacion");
			throw new Exception(mensaje);
		}
		
		boolean existeMAVEnvios =  service.existeMAVEnvios(params);
		
		if(!existeMAVEnvios) {
			String mensaje = this.getResourceMessage("procesoMAVGenerarEnviosForm.msg.NoExisteRegistros");
			throw new Exception(mensaje);
		}
		
		return params;
	}
	


	// metodo que sale del popup	
	public void salirAPantallaPadre(ActionEvent actionEvent) {
		try {			
			this.redireccionarPagina(this.paginaPadre);			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}	
			
	}

	/**
	 * @param val
	 */
	public void deshabilitar(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("deshabilitar");
		}
		ProcesoMAVGenerarEnviosForm f = (ProcesoMAVGenerarEnviosForm) this.formProceso;
		String actividad = (String) val.getNewValue();
		if (StringUtils.isBlank(actividad)) {
			this.codigoActividadDisabled = false;
		} else {
			f.setCodigoActividad("");
			this.codigoActividadDisabled = true;
		}
		
		f.setTipoCliente("");
	}
	
	/**
	 * @param val
	 */
	public void loadTipoCliente(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadTipoCliente");
		}
		ProcesoMAVGenerarEnviosForm f = (ProcesoMAVGenerarEnviosForm) this.formProceso;
		String actividad = (String) val.getNewValue();
		if (StringUtils.isNotBlank(actividad)) {
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			String datosActividad = ajax.getDatosActividad(actividad);
			f.setTipoCliente(datosActividad.split("__")[3]);	 	
		} else {
			f.setTipoCliente("");
		}
	}
	
	
	
	
	
	
	/**
	 * @return the mavActividadesList
	 */
	public List getMavActividadesList() {
		return mavActividadesList;
	}

	/**
	 * @param mavActividadesList the mavActividadesList to set
	 */
	public void setMavActividadesList(List mavActividadesList) {
		this.mavActividadesList = mavActividadesList;
	}

	/**
	 * @return the siccTipoMavList
	 */
	public List getSiccTipoMavList() {
		return siccTipoMavList;
	}

	/**
	 * @param siccTipoMavList the siccTipoMavList to set
	 */
	public void setSiccTipoMavList(List siccTipoMavList) {
		this.siccTipoMavList = siccTipoMavList;
	}

	/**
	 * @return the paginaPadre
	 */
	public String getPaginaPadre() {
		return paginaPadre;
	}

	/**
	 * @param paginaPadre the paginaPadre to set
	 */
	public void setPaginaPadre(String paginaPadre) {
		this.paginaPadre = paginaPadre;
	}

	/**
	 * @return the codigoActividadDisabled
	 */
	public boolean isCodigoActividadDisabled() {
		return codigoActividadDisabled;
	}

	/**
	 * @param codigoActividadDisabled the codigoActividadDisabled to set
	 */
	public void setCodigoActividadDisabled(boolean codigoActividadDisabled) {
		this.codigoActividadDisabled = codigoActividadDisabled;
	}
	
}

