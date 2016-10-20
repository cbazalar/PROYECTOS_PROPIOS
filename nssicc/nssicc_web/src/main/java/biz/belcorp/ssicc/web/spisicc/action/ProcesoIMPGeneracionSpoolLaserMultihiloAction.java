package biz.belcorp.ssicc.web.spisicc.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections.MapUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOBloqueoControlService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.spisicc.form.ProcesoIMPGeneracionSpoolLaserMultihiloForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProcesoIMPGeneracionSpoolLaserMultihiloAction extends BaseInterfazAbstractAction{

	private static final long serialVersionUID = -4030435816719944025L;
	
	private List siccMarcaList;
	private List siccCanalList;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		ProcesoIMPGeneracionSpoolLaserMultihiloForm interfazForm = new ProcesoIMPGeneracionSpoolLaserMultihiloForm();
		return interfazForm;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("Executing :  setViewAttributes. ");
		
		ProcesoIMPGeneracionSpoolLaserMultihiloForm f = (ProcesoIMPGeneracionSpoolLaserMultihiloForm) this.formInterfaz;
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
								
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO);  
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO);
        InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
        this.siccMarcaList=service.getMarcas();
        this.siccCanalList=service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
       
		MantenimientoOCRPedidoControlFacturacionService serviceFaturacion = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceFaturacion.getControlFacturacionById(criteria);
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		String fecha=controlFacturacion.getFechaProceso();
		f.setFechaFacturacionDate(DateUtil.convertStringToDate(fecha));
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception{
		
		params = super.prepareParamsBeforeExecute(params, form);		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ProcesoIMPGeneracionSpoolLaserMultihiloForm f = (ProcesoIMPGeneracionSpoolLaserMultihiloForm) this.formInterfaz;
		String oidPeriodo=reporteService.getOidString("getDesPerioByOidPerio", params);
		params.put("oidPeriodo", oidPeriodo);
		
		params.put("fechaProcesoArchivo", DateUtil.convertDateToString("yyyyMMdd", f.getFechaFacturacionDate()));
		params.put("fechaProceso", DateUtil.convertDateToString("yyyyMMdd", f.getFechaFacturacionDate()));
		params.put("fechaFacturacion", DateUtil.convertDateToString("dd/MM/yyyy", f.getFechaFacturacionDate()));
		String hora =  new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());
		params.put("fechaProcesoHora", hora);
		
		String indicadorNovedad = "0";
		params.put("indicadorNovedad", indicadorNovedad);
		
		String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
		params.put("rutaPath", path);
		
		MantenimientoSTOBloqueoControlService serviceSTOBC = (MantenimientoSTOBloqueoControlService) getBean("spusicc.mantenimientoSTOBloqueoControlService");
		Map criteria = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		criteria.put("pais", pais);
		criteria.put("codigoPais",pais.getCodigo());
		criteria.put("codigoSistema",Constants.SISTEMA_IMP);
		criteria.put("nombreParametro","documentosSTOReporteErrores");
		
		String valorParametro = serviceSTOBC.getParametroGenericoSistema(criteria);
		params.put("documentosSTOReporteErrores",valorParametro);
		
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		String codigoPais = MapUtils.getString(params, "codigoPais");
		LabelValue[] result = ajaxService.getRegionesACerrar( codigoPais);
		
		if (result != null) {
			String[] codigoRegionList = new String[result.length];
			for(int i=0;i<result.length;i++){
				codigoRegionList[i] = result[i].getValue();
			}
			params.put("codigoRegionList",codigoRegionList);
		}
		return params;        
	}

	/**
	 * @return
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * @return
	 */
	public List getSiccCanalList() {
		return siccCanalList;
	}

	/**
	 * @param siccCanalList
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}	
	
}
