package biz.belcorp.ssicc.web.spisicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spisicc.ProcesoIMPGeneracionDocumentosLaserService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spisicc.form.ProcesoCompaginacionForm;
import biz.belcorp.ssicc.web.spisicc.form.ProcesoIMPGeneracionDocumentosLaserColorForm;

@ManagedBean
@SessionScoped
public class ProcesoIMPGeneracionDocumentosLaserColorAction extends BaseProcesoAbstractAction{

	private static final long serialVersionUID = -836349064808569450L;
	
	private List siccMarcaList;
	private List siccCanalList;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoIMPGeneracionDocumentosLaserColorForm procesoForm =new ProcesoIMPGeneracionDocumentosLaserColorForm();		
		return procesoForm;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("Executing :  setViewAttributes. ");
		ProcesoIMPGeneracionDocumentosLaserColorForm f = (ProcesoIMPGeneracionDocumentosLaserColorForm) this.formProceso;
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
		f.setFechaFacturacionDate(DateUtil.convertStringToDate(fecha));
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);		
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		
		log.debug("Parameters List :  " + params.toString());		
		Usuario usuario =this.mPantallaPrincipalBean.getCurrentUser();
		ProcesoIMPGeneracionDocumentosLaserColorForm f = (ProcesoIMPGeneracionDocumentosLaserColorForm) this.formProceso;
		f.setFechaFacturacion(DateUtil.convertDateToString(f.getFechaFacturacionDate()));
		ProcesoIMPGeneracionDocumentosLaserService procesoService = (ProcesoIMPGeneracionDocumentosLaserService)
																				getBean("spisicc.procesoIMPGeneracionDocumentosLaserService");
		params.put("codigoProcesoImpresion",Constants.PROCESO_IMPRESION_LASER_COLOR);
		params.put("usuarioImp", usuario);
		procesoService.executeGeneracionDocumentosLaserColor(params);
		return params;
	}


	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form)throws Exception{
		ProcesoCompaginacionForm form1=(ProcesoCompaginacionForm) this.formProceso;
		form1.setFechaFacturacion(DateUtil.convertDateToString(form1.getFechaFacturacionD()));
		params=super.prepareParamsBeforeExecute(params, form);
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
	
	
	
}
