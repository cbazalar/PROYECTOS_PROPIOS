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
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spisicc.ProcesoImpresionService;
import biz.belcorp.ssicc.service.spisicc.framework.ProcesoImpresionExecutionService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spisicc.form.ProcesoCompaginacionForm;

@ManagedBean
@SessionScoped
public class ProcesoIMPCompaginacionBatchAction extends BaseProcesoAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5413272302597096601L;
	
	private List procesoImpresionList;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoCompaginacionForm form=new ProcesoCompaginacionForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		//super.executeProcess(form, request, params);
		ProcesoCompaginacionForm form=(ProcesoCompaginacionForm) this.formProceso;
		form.setFechaFacturacion(DateUtil.convertDateToString(form.getFechaFacturacionD()));
		ProcesoImpresionExecutionService executionService = (ProcesoImpresionExecutionService) getBean("spisicc.procesoImpresionExecutionService");
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		executionService.executeProcesoImpresion(params, usuario);

		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
		criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
		
		ProcesoCompaginacionForm form=(ProcesoCompaginacionForm) this.formProceso;
		MantenimientoOCRPedidoControlFacturacionService serviceOCR = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");        
		PedidoControlFacturacion controlFacturacion = serviceOCR.getControlFacturacionById(criteria);
		
		form.setFechaFacturacion(controlFacturacion.getFechaProceso());
		form.setFechaFacturacionD(DateUtil.convertStringToDate(controlFacturacion.getFechaProceso()));
		form.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		
		// Obtenemos la lista de procesos de impresion
		ProcesoImpresionService service = (ProcesoImpresionService) getBean("spisicc.procesoImpresionService");
		List procesos = service.getProcesosImpresion(null);
		procesoImpresionList=procesos;
	}

	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form)throws Exception{
		ProcesoCompaginacionForm form1=(ProcesoCompaginacionForm) this.formProceso;
		form1.setFechaFacturacion(DateUtil.convertDateToString(form1.getFechaFacturacionD()));
		params=super.prepareParamsBeforeExecute(params, form);
		return params;
	}
	
	public List getProcesoImpresionList() {
		return procesoImpresionList;
	}

	public void setProcesoImpresionList(List procesoImpresionList) {
		this.procesoImpresionList = procesoImpresionList;
	}

	
}





