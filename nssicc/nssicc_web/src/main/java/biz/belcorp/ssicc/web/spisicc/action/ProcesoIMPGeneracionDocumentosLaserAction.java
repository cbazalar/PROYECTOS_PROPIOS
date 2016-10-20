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
import biz.belcorp.ssicc.web.spisicc.form.ProcesoIMPGeneracionDocumentosLaserForm;

@ManagedBean
@SessionScoped
public class ProcesoIMPGeneracionDocumentosLaserAction extends BaseProcesoAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -75532117021596643L;

	private List siccMarcaList;
	private List siccCanalList;
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoIMPGeneracionDocumentosLaserForm form=new ProcesoIMPGeneracionDocumentosLaserForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		log.debug("Parameters List :  " + params.toString());
		ProcesoIMPGeneracionDocumentosLaserForm form1=(ProcesoIMPGeneracionDocumentosLaserForm)this.formProceso;
		form1.setFechaFacturacion(DateUtil.convertDateToString(form1.getFechaFacturacionD()));
		ProcesoIMPGeneracionDocumentosLaserService procesoService = (ProcesoIMPGeneracionDocumentosLaserService)getBean("spisicc.procesoIMPGeneracionDocumentosLaserService");

		procesoService.executeGeneracionDocumentosLaser(params);
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("Executing :  setViewAttributes. ");
		ProcesoIMPGeneracionDocumentosLaserForm form=(ProcesoIMPGeneracionDocumentosLaserForm) this.formProceso;
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		form.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		form.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha",Constants.NUMERO_CERO);  
		criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO);
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		siccMarcaList=service.getMarcas();
		siccCanalList=service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		
		MantenimientoOCRPedidoControlFacturacionService serviceFaturacion = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceFaturacion.getControlFacturacionById(criteria);
		form.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		form.setFechaFacturacion(controlFacturacion.getFechaProceso());
		form.setFechaFacturacionD(DateUtil.convertStringToDate(controlFacturacion.getFechaProceso()));
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,BaseForm form) throws Exception{
		
		ProcesoIMPGeneracionDocumentosLaserForm form1=(ProcesoIMPGeneracionDocumentosLaserForm)this.formProceso;
		form1.setFechaFacturacion(DateUtil.convertDateToString(form1.getFechaFacturacionD()));
		params=super.prepareParamsBeforeExecute(params,form);
		
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
	









