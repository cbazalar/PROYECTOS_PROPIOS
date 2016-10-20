package biz.belcorp.ssicc.web.sisicc.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazLARService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazLARGenerarLARFacturacionElectronicaForm;

@ManagedBean
@SessionScoped
public class InterfazLARGenerarLARFacturacionElectronicaAction extends
		BaseInterfazAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2972093377070376881L;
	private List siccRegionList;
	private LabelValue[] siccZonaList = {};

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazLARGenerarLARFacturacionElectronicaForm form = new InterfazLARGenerarLARFacturacionElectronicaForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		criteriaOperacion.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteriaOperacion.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);

		criteriaOperacion.put("estadoCampanha", Constants.NUMERO_CERO);
		criteriaOperacion.put("indicadorActiva", Constants.ESTADO_ACTIVO);

		MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");

		PedidoControlFacturacion controlFacturacion = serviceFact
				.getControlFacturacionById(criteriaOperacion);

		InterfazLARGenerarLARFacturacionElectronicaForm viewForm = (InterfazLARGenerarLARFacturacionElectronicaForm) this.formInterfaz;

		viewForm.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());

		viewForm.setFechaD(DateUtil.convertStringToDate(controlFacturacion
				.getFechaProceso()));
		/*
		 * String todos[] = {"Todos"}; viewForm.setRegionList(todos);
		 * viewForm.setZonaList(todos); viewForm.setTipoDocumento(todos);
		 */
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		Map criteria = new HashMap();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoUsuario", usuario.getLogin());

		this.siccRegionList = reporteService.getListaGenerico(
				"getRegionesByPais", criteria);

	}

	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		InterfazLARGenerarLARFacturacionElectronicaForm f = (InterfazLARGenerarLARFacturacionElectronicaForm) this.formInterfaz;
		f.setFecha(null);
		if (f.getFechaD() != null) {
			f.setFecha(DateUtil.convertDateToString(f.getFechaD()));
		}

		params = super.prepareParamsBeforeExecute(params, form);

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		params.put(
				"tipoDocumento",
				f.getTipoDocumento() == null ? new ArrayList() : Arrays
						.asList(f.getTipoDocumento()));

		params.put("zonaList", f.getZonaList() == null ? new ArrayList()
				: Arrays.asList(f.getZonaList()));

		params.put("regionList", f.getRegionList() == null ? new ArrayList()
				: Arrays.asList(f.getRegionList()));

		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		criteriaOperacion.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteriaOperacion.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		criteriaOperacion.put("estadoCampanha", Constants.NUMERO_CERO);
		criteriaOperacion.put("indicadorActiva", Constants.ESTADO_ACTIVO);

		MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");

		PedidoControlFacturacion controlFacturacion = serviceFact
				.getControlFacturacionById(criteriaOperacion);

		params.put(
				"codigoPeriodo",
				f.getCodigoPeriodo() == null ? controlFacturacion
						.getCodigoPeriodo() : f.getCodigoPeriodo());
		params.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		params.put(
				"fecha",
				f.getFecha() == null ? controlFacturacion.getFechaProceso() : f
						.getFecha());

		return params;

	}
	
	/* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseInterfazAbstractAction#beforeExecuteInterfaz(java.util.Map)
     */
    protected void beforeExecuteInterfaz(Map params) {
    	
    	super.beforeExecuteInterfaz(params);    	
    	InterfazLARService interfazLARService = (InterfazLARService) getBean("sisicc.interfazLARService");
            	
    	List tipoDocList = (List)params.get("tipoDocumento");
    	List zonaList = (List)params.get("zonaList");
    	List regionList = (List)params.get("regionList");
    	 
    	interfazLARService.deleteInterfazLAR8Parametros();
    	
    	if(tipoDocList!=null && tipoDocList.size()>0){
    		for(int i=0; i<tipoDocList.size(); i++){
    			String tipoDocumento = (String)tipoDocList.get(i);
    			Map map = new HashMap();
    			map.put("tipoParam", "TIPODOC");
    			map.put("tipoDocumento", tipoDocumento);
    			interfazLARService.insertInterfazLAR8Parametros(map);
    		}    		
    	}
    	
    	if(zonaList!=null && zonaList.size()>0){
    		for(int i=0; i<zonaList.size(); i++){
    			String zona = (String)zonaList.get(i);
    			Map map = new HashMap();
    			map.put("tipoParam", "ZONA");
    			map.put("codigoZona", zona);
    			interfazLARService.insertInterfazLAR8Parametros(map);
    		}    		
    	}
    	
    	if(regionList!=null && regionList.size()>0){
    		for(int i=0; i<regionList.size(); i++){
    			String region = (String)regionList.get(i);
    			Map map = new HashMap();
    			map.put("tipoParam", "REGION");
    			map.put("codigoRegion", region);
    			interfazLARService.insertInterfazLAR8Parametros(map);
    		}    		
    	}
    	
    }
	
    
    /* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseInterfazAbstractAction#afterExecuteInterfaz(java.util.Map, biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazExecutionResult)
	 */
	protected void afterExecuteInterfaz(Map params, InterfazExecutionResult interfazExecutionResult)  throws Exception{

		super.afterExecuteInterfaz(params, interfazExecutionResult);
		InterfazLARService interfazLARService = (InterfazLARService) getBean("sisicc.interfazLARService");
		interfazLARService.deleteInterfazLAR8Parametros();
		
	}

	/**
	 * @param val
	 */
	public void loadzonas(ValueChangeEvent val) {
		log.debug("loadzonas");

		String[] regiones = (String[]) val.getNewValue();
		if (regiones.length > 0) {

			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			this.siccZonaList = aSvc
					.getZonasMultipleByPaisMarcaCanalRegion(
							this.mPantallaPrincipalBean.getCurrentCountry()
									.getCodigo(),
							Constants.CODIGO_MARCA_DEFAULT,
							Constants.CODIGO_CANAL_DEFAULT, regiones,
							Constants.FORMATO_TOTAL);

		} else {
			setSiccZonaList(null);

		}

	}

	@Override
	public String setValidarInterfaz() {
		InterfazLARGenerarLARFacturacionElectronicaForm f = (InterfazLARGenerarLARFacturacionElectronicaForm) this.formInterfaz;

		if (!StringUtils.isBlank(f.getDesde())
				&& !StringUtils.isBlank(f.getHasta())) {
			int desde = Integer.parseInt(f.getDesde());
			int hasta = Integer.parseInt(f.getHasta());
			if(hasta<desde){
				String mensaje =  this.getResourceMessage("interfazLARGenerarLARFacturacionElectronicaForm.error.numero.interno");
				return mensaje;
			}
		}

		return null;

	}

	/**
	 * @return the siccRegionList
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 *            the siccRegionList to set
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccZonaList
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 *            the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

}
