package biz.belcorp.ssicc.web.spisicc.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
import biz.belcorp.ssicc.web.spisicc.form.ProcesoIMPGeneracionProcesoNoDependeFacturacionForm;

@ManagedBean
@SessionScoped
public class ProcesoIMPGeneracionProcesoNoDependeFacturacionAction extends BaseInterfazAbstractAction{

	private static final long serialVersionUID = -836349064808569450L;
	
	private List siccMarcaList;
	private List siccCanalList;
	private String alturaFija;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		ProcesoIMPGeneracionProcesoNoDependeFacturacionForm f =new ProcesoIMPGeneracionProcesoNoDependeFacturacionForm();
		return f;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("Executing :  setViewAttributes. ");
		ProcesoIMPGeneracionProcesoNoDependeFacturacionForm f = (ProcesoIMPGeneracionProcesoNoDependeFacturacionForm) this.formInterfaz;
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario =this.mPantallaPrincipalBean.getCurrentUser();
		
		Map criteria = new HashMap();
		criteria.put("codigoPais",pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO);  
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO);
        InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
        this.siccMarcaList= service.getMarcas();
        this.siccCanalList=service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
       
		MantenimientoOCRPedidoControlFacturacionService serviceFaturacion = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceFaturacion.getControlFacturacionById(criteria);
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		String fecha=controlFacturacion.getFechaProceso();
		f.setFechaFacturacionD(DateUtil.convertStringToDate(fecha));
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);		
		setAlturaFija("S");
	}

//	@Override
//	protected Map<String, Object> executeProcess(Map<String, Object> params)
//			throws Exception {
//		
////		log.debug("Parameters List :  " + params.toString());		
////		Usuario usuario =this.mPantallaPrincipalBean.getCurrentUser();
////		ProcesoIMPGeneracionProcesoNoDependeFacturacionForm f = (ProcesoIMPGeneracionProcesoNoDependeFacturacionForm) this.formProceso;
////		f.setFechaFacturacion(DateUtil.convertDateToString(f.getFechaFacturacionD()));
////		ProcesoIMPGeneracionDocumentosLaserService procesoService = (ProcesoIMPGeneracionDocumentosLaserService)
////																				getBean("spisicc.procesoIMPGeneracionDocumentosLaserService");
////		params.put("codigoProcesoImpresion",Constants.proceso);
////		params.put("usuarioImp", usuario);
////		procesoService.executeGeneracionDocumentosLaserColor(params);
////		return params;
//	}


	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form)throws Exception{
		params = super.prepareParamsBeforeExecute(params, form);
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ProcesoIMPGeneracionProcesoNoDependeFacturacionForm f = (ProcesoIMPGeneracionProcesoNoDependeFacturacionForm)this.formInterfaz;
		
		String fechaFacturacion = DateUtil.convertDateToString(f.getFechaFacturacionD());
		params.put("fechaFacturacion", fechaFacturacion);
		params.put("codigoPeriodo", f.getCodigoPeriodo());
		String oidPeriodo=reporteService.getOidString("getDesPerioByOidPerio", params);
		params.put("oidPeriodo", oidPeriodo);
		params.put("fechaProcesoArchivo", DateUtil.formatoString(f.getFechaFacturacionD(), "yyyyMMdd"));
		params.put("fechaProceso", DateUtil.formatoString(f.getFechaFacturacionD(), "yyyyMMdd"));
		String hora =  new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());
		params.put("fechaProcesoHora", hora);	
		String indicadorNovedad = "0";
		params.put("indicadorNovedad", indicadorNovedad);	
		params.put("rutaPath","/");
		
		
		MantenimientoSTOBloqueoControlService serviceSTOBC = (MantenimientoSTOBloqueoControlService) getBean("spusicc.mantenimientoSTOBloqueoControlService");
		Map criteria = new HashMap();
		criteria.put("codigoPais", params.get("codigoPais"));
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
	
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	public List getSiccCanalList() {
		return siccCanalList;
	}

	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	/**
	 * @return the alturaFija
	 */
	public String getAlturaFija() {
		return alturaFija;
	}

	/**
	 * @param alturaFija the alturaFija to set
	 */
	public void setAlturaFija(String alturaFija) {
		this.alturaFija = alturaFija;
	}
	
}
