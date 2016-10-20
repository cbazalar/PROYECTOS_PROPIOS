package biz.belcorp.ssicc.web.spusicc.inc.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.inc.ProcesoINCCargaDespachoConcursoRxPService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.inc.form.ProcesoINCCargaDespachoConcursoRxPForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"unchecked","rawtypes"})
public class ProcesoINCCargaDespachoConcursoRxPAction extends BaseProcesoAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3059401231343492788L;
	
	private String viewValida;
	private List incArchivoList;
	private List incConcuHabilitadosList;
	
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		
		ProcesoINCCargaDespachoConcursoRxPForm p = new ProcesoINCCargaDespachoConcursoRxPForm();
		return p;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		
		ProcesoINCCargaDespachoConcursoRxPService service = (ProcesoINCCargaDespachoConcursoRxPService) 
						(ProcesoINCCargaDespachoConcursoRxPService) getBean("spusicc.procesoINCCargaDespachoConcursoRxPService");;
		
		service.executeCargaDespachoConcursoRxP(params);

		return params;
	
	
	}

	@Override
	protected void setViewAtributes() throws Exception {

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		ProcesoINCCargaDespachoConcursoRxPForm f = (ProcesoINCCargaDespachoConcursoRxPForm) this.formProceso;
		
		this.mostrarBotonExecute = false;
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
		MantenimientoOCRPedidoControlFacturacionService serviceAux = (MantenimientoOCRPedidoControlFacturacionService)
									getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceAux.getControlFacturacionById(criteria);

		// Carga el periodo actual
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		f.setOidConcurso("");
		
		this.limpiara();
		
		//seteamos la ruta temporal dodne guardar el excel
		ProcesoINCCargaDespachoConcursoRxPService service = (ProcesoINCCargaDespachoConcursoRxPService) 
									getBean("spusicc.procesoINCCargaDespachoConcursoRxPService");
		
		//cargando en session la lista de concursos habilitados
		this.incConcuHabilitadosList = service.getListConcursoVigentesRxP();
		
	}

	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form)
			throws Exception {

		params = super.prepareParamsBeforeExecute(params, form);
		ProcesoINCCargaDespachoConcursoRxPForm f = (ProcesoINCCargaDespachoConcursoRxPForm) this.formProceso;
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		//recuperamos el oidConcurso seleccionado por el usuario
    	params.put("oidConcurso", f.getOidConcursoSeleccionado());
    	
    	//recuperamos el oidPeriodo seleccionado por el usuario
    	Map criteriaAux = new HashMap();
    	criteriaAux.put("codigoPeriodo", f.getCodigoPeriodoSeleccionado());
    	String oidPeriodo = String.valueOf(reporteService.getOidPeriodo(criteriaAux));
    	params.put("oidPeriodo", oidPeriodo); 
    	
    	//recuperamos la lista de Codigos de Venta
    	ArrayList listaCodigosVenta = new ArrayList();
    	String[]objlistaCodigos   = f.getLabel();
    	for(int i=0; i<objlistaCodigos.length; i++) {
    		if(!objlistaCodigos[i].equals(""))
    			listaCodigosVenta.add(objlistaCodigos[i]);
    	}
    	params.put("listaCodigosVenta", listaCodigosVenta);
    	
    	//recuperamos el oidPais
    	criteriaAux.put("codigoPais", f.getCodigoPais());
    	MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) 
    								getBean("spusicc.mantenimientoMAEClienteService");
    	String oidPais= clienteService.getOidPais(criteriaAux);
    	params.put("oidPais", oidPais);
    	
    	return params;
	}
	
	@Override
	public void afterExecuteProcess(BaseProcesoForm form) throws Exception {
		
		this.limpiara();
		ProcesoINCCargaDespachoConcursoRxPForm f = (ProcesoINCCargaDespachoConcursoRxPForm) this.formProceso;
		
		String error = getResourceMessage("procesoINCCargaDespachoConcursoRxPForm.proceso.ok");
		addInfo("Mensaje: ", error);
	
	}
	
	
	/**
	 * Validamos el periodo y concurso RxP ingresado
     *
	 * 
	 */
	public void validar()
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'validar' method");
		}

		Usuario usuario= this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		ProcesoINCCargaDespachoConcursoRxPForm f = (ProcesoINCCargaDespachoConcursoRxPForm) this.formProceso;
		
		try
		{
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		criteria.put("oidConcurso", f.getOidConcurso());
		
		// obtenemos el servicio 
		ProcesoINCCargaDespachoConcursoRxPService service = (ProcesoINCCargaDespachoConcursoRxPService) getBean("spusicc.procesoINCCargaDespachoConcursoRxPService");
		
		//validamos el archivo excel y en criteria mandamos que estructura es sin period o con periodo
		String mensajeError = service.validarDespachoConcursoRxP(criteria);
		f.setEncontrado(Constants.NUMERO_CERO);
		
		if(mensajeError == null) {
			f.setIndicadorValido(Constants.NUMERO_UNO);
			this.viewValida = Constants.NUMERO_UNO;//flag para mostrar el resultado de la validacion

			
			f.setOidConcursoSeleccionado(f.getOidConcurso());
			f.setCodigoPeriodoSeleccionado(f.getCodigoPeriodo());
			
	    	//recuperamos el oidPeriodo seleccionado por el usuario
			ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
	    	String oidPeriodo = String.valueOf(reporteService.getOidPeriodo(criteria));
	    	criteria.put("oidPeriodo", oidPeriodo); 
			
	    	//recuperamos el oidPais
	    	MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) 
	    								getBean("spusicc.mantenimientoMAEClienteService");
	    	String oidPais= clienteService.getOidPais(criteria);
	    	criteria.put("oidPais", oidPais);
			
	    	//Verificamos si ya existe el concurso y periodo ingresado en BD
	    	List listaCabecera = service.getDespachoConcursoRxP(criteria);
	    	if(listaCabecera.size() > 0 ) {
	    		f.setEncontrado(Constants.NUMERO_UNO);
	    		
	    		List listaDetalle = service.getDespachoConcursoRxPDetalle(criteria);
	    		this.incArchivoList = listaDetalle;
	
	    	}
			
		} else {
				f.setIndicadorValido(Constants.NUMERO_CERO);
				addError("Error :", mensajeError);
			}

		}
		catch (Exception e)
		{
			 this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		
		return; 
	}
	
	/**
	 * 
	 */
	public void regresar()
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'regresar' method");
		}

		//limpiando el flag de validacion de archivo
		this.limpiara();
	
	}
	
	private void limpiara()
	{
		this.viewValida = "";
	}
	
//	public void searchCodigoVenta()
//	{
//		ProcesoINCCargaDespachoConcursoRxPForm f = (ProcesoINCCargaDespachoConcursoRxPForm)this.formProceso;
//		
//		String codigoPeriodoSel = f.getCodigoPeriodoSeleccionado();
//		String y = this.incArchivoList.get();
//		AjaxService ajax = (AjaxService) getBean("ajaxService");
//		ajax.validarCodigoVentaRxP( codigoPeriodoSel, y ); 
//	}
	
	public String getViewValida() {
		return viewValida;
	}

	public void setViewValida(String viewValida) {
		this.viewValida = viewValida;
	}

	public List getIncArchivoList() {
		return incArchivoList;
	}

	public void setIncArchivoList(List incArchivoList) {
		this.incArchivoList = incArchivoList;
	}

	public List getIncConcuHabilitadosList() {
		return incConcuHabilitadosList;
	}

	public void setIncConcuHabilitadosList(List incConcuHabilitadosList) {
		this.incConcuHabilitadosList = incConcuHabilitadosList;
	}
	
}
