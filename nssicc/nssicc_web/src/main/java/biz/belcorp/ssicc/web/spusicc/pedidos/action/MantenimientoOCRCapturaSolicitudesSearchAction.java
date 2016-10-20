package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelSolicitudesCreditoValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRCapturaSolicitudesService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoOCRCapturaSolicitudesForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoOCRCapturaSolicitudesSearchForm;

@SessionScoped
@ManagedBean
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoOCRCapturaSolicitudesSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -3592045800381562802L;
	private List pedidosxZonaList;

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoOCRCapturaSolicitudesCreditoList";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoOCRCapturaSolicitudesForm";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoOCRCapturaSolicitudesSearchForm form = new MantenimientoOCRCapturaSolicitudesSearchForm();
		return form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}
		MantenimientoOCRCapturaSolicitudesSearchForm f = (MantenimientoOCRCapturaSolicitudesSearchForm) this.formBusqueda;
		f.setFechaFacturacionSearch(DateUtil.convertDateToString(f.getFechaFacturacionSearchD()));
		MantenimientoOCRCapturaSolicitudesService serviceSolicCredito = (MantenimientoOCRCapturaSolicitudesService) getBean("spusicc.pedidos.mantenimientoOCRCapturaSolicitudesService");
		List list = serviceSolicCredito.getSearchPedidosZonaByCriteria(getCriteria(f));
//		this.listaBusqueda = this.pedidosxZonaList;
		return list;
	}

	/**
	 * @param form
	 * @return
	 * @throws Exception
	 */
	private Map getCriteria(MantenimientoOCRCapturaSolicitudesSearchForm form)
			throws Exception {
		Map criteria = BeanUtils.describe(form);
		criteria.put("estado", Constants.ESTADO_ACTIVO);
		criteria.put("codigoPaisSearch", form.getCodigoPais());

		if (StringUtils.isNotBlank(form.getCodigoPaisSearch())) {
			criteria.put("periodoSearch", form.getCodigoPeriodoSearch());
		}
		if (StringUtils.isNotBlank(form.getFechaFacturacionSearch())) {
			criteria.put("fechaFacturacionSearch",
					form.getFechaFacturacionSearch());
		}
		if (StringUtils.isNotBlank(form.getCodigoZonaSearch())) {
			criteria.put("zonaSearch", form.getCodigoZonaSearch());
		}
		return criteria;
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
		if (log.isDebugEnabled()) {
            log.debug("Entering 'save' method");
        }
        Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
        MantenimientoOCRCapturaSolicitudesForm capturaSolicitudesForm = (MantenimientoOCRCapturaSolicitudesForm)this.formMantenimiento;
	    MantenimientoOCRCapturaSolicitudesService serviceSolicCredito = (MantenimientoOCRCapturaSolicitudesService)getBean("spusicc.pedidos.mantenimientoOCRCapturaSolicitudesService");
	    
	    Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

        if (!this.accion.equals(this.ACCION_NUEVO)) {
        	LabelSolicitudesCreditoValue solicitudPedidoCredito = new LabelSolicitudesCreditoValue();
        	
        	solicitudPedidoCredito.setCodigoZona(capturaSolicitudesForm.getCodigoZonaSearch());
        	solicitudPedidoCredito.setFechaFacturacion(capturaSolicitudesForm.getFechaFacturacion());
        	solicitudPedidoCredito.setCodigoPeriodo(capturaSolicitudesForm.getCodigoPeriodo());
        	solicitudPedidoCredito.setNumLote(capturaSolicitudesForm.getNumeroLote());
        	solicitudPedidoCredito.setNumSolicitudes(capturaSolicitudesForm.getTotalSolicitudes());
        	
            log.debug("update bean " + capturaSolicitudesForm);
            serviceSolicCredito.updateCapturaSolicitudCredito(solicitudPedidoCredito, usuario );   
        } else {
        	
    	    String codigoPeriodo= capturaSolicitudesForm.getCodigoPeriodo();
    		String numeroLote  = capturaSolicitudesForm.getNumeroLote();
    		//String codigoPais  = capturaSolicitudesForm.getCodigoPais();
    		
    		String[]objlistaZonas   = capturaSolicitudesForm.getListaZonas();
    		String[]objlistaFechasFact   = capturaSolicitudesForm.getListaFechasFacturacion();
    		String[]objlistaSolicitudes  = capturaSolicitudesForm.getListaSolicitudes();
    			
    		int tamRegiones= objlistaZonas.length;
    		ArrayList objListaSolicitudes = new ArrayList();
    		String zona 				   = "";
	    	String fechaFact     		   = "";
	        String numeroSolicitudes       = "";
	        int cont=0;
	        
	        // validamos que no haya repidos en el ingreso de datos
    		for(int k=0;k<tamRegiones-1; k++){
    			zona 				   = objlistaZonas[k+1];
    	    	fechaFact     		   = objlistaFechasFact[k+1];
    	    	cont=0;
    	    	for (int t=0; t<tamRegiones-1;t++){
    	    		if (zona.equals(objlistaZonas[t+1]) && fechaFact.equals(objlistaFechasFact[t+1])){
    	    			cont++;
    	    		}
    	    	}
    	    	if (cont>1){
    	    		capturaSolicitudesForm.setTotalSolicitudes("");
    	    	}
    		}
    		
    	    for (int i=0; i<tamRegiones-1; i++){
    	    	//String region                  = objlistaRegiones[i+1];
    	    	zona 				   = objlistaZonas[i+1];
    	    	fechaFact     		   = objlistaFechasFact[i+1];
    	        numeroSolicitudes       = objlistaSolicitudes[i+1];
    	        
    	        if (zona.equals("")){
    	        	
    	        }else{
    	        	if (validationSuccessful(zona, fechaFact, codigoPeriodo, numeroLote)){
    	        		
    		        	LabelSolicitudesCreditoValue objSolicitudes= new LabelSolicitudesCreditoValue();
    		        	//objSolicitudes.setCodigoPais(codigoPais);
    		        	objSolicitudes.setCodigoZona(zona);
    		        	objSolicitudes.setFechaFacturacion(fechaFact);
    		        	objSolicitudes.setCodigoPeriodo(codigoPeriodo);
    		        	objSolicitudes.setNumSolicitudes(numeroSolicitudes);
    		        	objSolicitudes.setNumLote(numeroLote);
    		        	
    		        	log.debug("insert bean "+objSolicitudes);
    		        	objListaSolicitudes.add(objSolicitudes);
    		        	
    		        	
    	        	}else{
    	        		 capturaSolicitudesForm.setTotalSolicitudes("");
    	        	}
    	        }   	
    	    }
    	    
    	    serviceSolicCredito.insertSolicitudesCreditoxZona(objListaSolicitudes, usuario);
    	    Map criteria = new HashMap();
    		
    		criteria.put("codigoPais", pais.getCodigo()); 
    	    serviceSolicCredito.executeActualizaNumeroLoteMica(criteria);
        }
        this.listaBusqueda = new ArrayList();
        this.listaBusqueda = this.setFindAttributes();
        this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
        return true;
	}
	
	private boolean validationSuccessful(String zona,String fechaFact,String codigoPeriodo,String numeroLote) {
        try {
		boolean isOk = true;
        MantenimientoOCRCapturaSolicitudesService serviceSolicCredito2 = (MantenimientoOCRCapturaSolicitudesService)getBean("spusicc.pedidos.mantenimientoOCRCapturaSolicitudesService");
        String mensaje = "";
    	Map criteriaPedidoxZona = new HashMap();
    	criteriaPedidoxZona.put("codigoZona", zona);
    	criteriaPedidoxZona.put("fechaFacturacion", fechaFact);
    	criteriaPedidoxZona.put("codigoPeriodo", codigoPeriodo);
    	criteriaPedidoxZona.put("numLote", numeroLote);

    	LabelSolicitudesCreditoValue objPedidoxZona= serviceSolicCredito2.getPedidoxZonaExistente(criteriaPedidoxZona);
         
    	if (objPedidoxZona!= null){
    		mensaje = this.getResourceMessage("errors.codigo.venta.tabla");
    		  isOk = false;
    		throw new Exception (mensaje);
        }
    	return isOk;
        } catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
			return false;
		}
        
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		MantenimientoOCRCapturaSolicitudesForm form = new MantenimientoOCRCapturaSolicitudesForm();
		MantenimientoOCRCapturaSolicitudesService serviceSolicCredito = (MantenimientoOCRCapturaSolicitudesService) getBean("spusicc.pedidos.mantenimientoOCRCapturaSolicitudesService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		form.setCodigoPais(pais.getCodigo());
		form.setNewRecord(true);
		if (!this.accion.equals(this.ACCION_NUEVO)) {
			form.setNewRecord(false);
			LabelSolicitudesCreditoValue objSeleccionado = (LabelSolicitudesCreditoValue) this.beanRegistroSeleccionado;
			LabelSolicitudesCreditoValue objPedidoxZona = serviceSolicCredito
					.getPedidoxZonaExistente(getCriteria(objSeleccionado));
			// BeanUtils.copyProperties(capturaSolicitudesForm, objPedidoxZona);
			form.setCodigoPeriodo(objPedidoxZona.getCodigoPeriodo());
			form.setFechaFacturacion(objPedidoxZona.getFechaFacturacion());

			form.setNumeroLote(objPedidoxZona.getNumLote());

			form.setCodigoZonaSearch(objPedidoxZona.getCodigoZona());
			form.setTotalSolicitudes(objPedidoxZona.getNumSolicitudes());

		} else {

			Map criteria = new HashMap();
			Map criteriaLote = new HashMap();

			criteria.put("codigoPais", form.getCodigoPais());
			criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																	// Campanha
																	// Activa
			criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																		// Campanha
																		// activa
																		// q se
																		// procesa
																		// actualmente

			MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
			PedidoControlFacturacion controlFacturacion = serviceFact
					.getControlFacturacionById(criteria);

			// Carga Fecha y Periodo
			// capturaSolicitudesForm.setFechaFacturacion(controlFacturacion.getFechaProceso());
			// traia la fecha del archivo de control
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			form.setFechaFacturacion(sdf.format(new Date(System
					.currentTimeMillis())));
			form.setFechaFacturacionD(DateUtil.convertStringToDate(form
					.getFechaFacturacion()));
			form.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
			criteriaLote.put("codigoPais", form.getCodigoPais());

			// Carga Numero de lotes
			Map map = new HashMap();
			map.put("periodo", controlFacturacion.getCodigoPeriodo());
			map.put("pais", form.getCodigoPais());
			form.setNumeroLote(serviceFact.getNumLotes(map));

			// return mapping.findForward("edit");
		}
		return form;
	}

	/**
	 * @param f
	 * @return
	 */
	private boolean isUpdate(MantenimientoOCRCapturaSolicitudesForm f) {
		boolean updateFlag = true;

		String id = f.getId();
		if (StringUtils.isBlank(id)) {
			updateFlag = false;
		}
		return updateFlag;
	}

	/**
	 * @param id
	 * @return
	 */
	private Map getCriteria(LabelSolicitudesCreditoValue objSeleccionado) {
		Map criteria = new HashMap();
		// String[] aux = StringUtils.split(id, ";");
		String codZona = objSeleccionado.getCodigoZona();
		String fechaFacturacion = objSeleccionado.getFechaFacturacion();
		String codPeriodo = objSeleccionado.getCodigoPeriodo();
		String numeroLote = objSeleccionado.getNumLote();
		criteria.put("codigoZona", codZona);
		criteria.put("fechaFacturacion", fechaFacturacion);
		criteria.put("codigoPeriodo", codPeriodo);
		criteria.put("numLote", numeroLote);

		return criteria;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'view' method de cargaPedidosx Zona");
		}
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoOCRCapturaSolicitudesSearchForm f = (MantenimientoOCRCapturaSolicitudesSearchForm) this.formBusqueda;
		f.setCodigoPais(pais.getCodigo());
		f.setCodigoPaisSearch(pais.getCodigo());
		this.mostrarBotonConsultar = false;
		this.pedidosxZonaList = new ArrayList();
		this.mostrarBotonEliminar = false;
		this.invocarFindLuegoGrabar = false;
	}

	/**
	 * @return the pedidosxZonaList
	 */
	public List getPedidosxZonaList() {
		return pedidosxZonaList;
	}

	/**
	 * @param pedidosxZonaList
	 *            the pedidosxZonaList to set
	 */
	public void setPedidosxZonaList(List pedidosxZonaList) {
		this.pedidosxZonaList = pedidosxZonaList;
	}

}
