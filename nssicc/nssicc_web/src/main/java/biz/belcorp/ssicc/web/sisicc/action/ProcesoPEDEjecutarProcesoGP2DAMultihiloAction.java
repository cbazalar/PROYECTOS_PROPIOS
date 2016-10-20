package biz.belcorp.ssicc.web.sisicc.action;

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
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.ProcesoPEDEjecutarProcesoGP2DAMultihiloForm;


@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProcesoPEDEjecutarProcesoGP2DAMultihiloAction extends BaseInterfazAbstractAction{

	private static final long serialVersionUID = -4030435816719944025L;
	
	private List siccMarcaList;
	private List siccCanalList;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		ProcesoPEDEjecutarProcesoGP2DAMultihiloForm interfazForm = new ProcesoPEDEjecutarProcesoGP2DAMultihiloForm();
		return interfazForm;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("Executing :  setViewAttributes. ");
		
		ProcesoPEDEjecutarProcesoGP2DAMultihiloForm f = (ProcesoPEDEjecutarProcesoGP2DAMultihiloForm) this.formInterfaz;
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
		ProcesoPEDEjecutarProcesoGP2DAMultihiloForm f = (ProcesoPEDEjecutarProcesoGP2DAMultihiloForm) this.formInterfaz;
		
		params.put("fechaProcesoArchivo", DateUtil.convertDateToString("yyyyMMdd", f.getFechaFacturacionDate()));
		params.put("fechaProceso", DateUtil.convertDateToString("yyyyMMdd", f.getFechaFacturacionDate()));
		params.put("fechaFacturacion", DateUtil.convertDateToString("dd/MM/yyyy", f.getFechaFacturacionDate()));
		String hora =  new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());
		params.put("fechaProcesoHora", hora);
		
		String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
		params.put("rutaPath", path);
		
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
