package biz.belcorp.ssicc.web.spusicc.flexipago.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.spusicc.flexipago.form.ProcesoFLXRecepcionArchivosWebManualForm;

@ManagedBean
@SessionScoped
public class ProcesoFLXRecepcionArchivosWebManualAction extends BaseInterfazAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3206279320348110612L;

	private List flxInterfacesPaqueteList;
	
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception
	{
		ProcesoFLXRecepcionArchivosWebManualForm formInterfaz = new ProcesoFLXRecepcionArchivosWebManualForm();
		return formInterfaz;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		ProcesoFLXRecepcionArchivosWebManualForm f = (ProcesoFLXRecepcionArchivosWebManualForm)this.formInterfaz;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
        
		Map criteria = new HashMap();
		criteria.put("codigoPais",pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
        
        MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);
		
		// Carga de PeriodoProceso y Fecha Facturacion
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
			
		criteria.put("codigoSistema", Constants.FLX_CODIGO_SISTEMA);
		criteria.put("codigoInterfaz", f.getCodigoInterfaz());
		
		List lista = interfazSiCCService.getListaProcesosLet(criteria);
		this.flxInterfacesPaqueteList = lista;
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception 
	{
		params = super.prepareParamsBeforeExecute(params, form);
		return params;
	}

	public List getFlxInterfacesPaqueteList() {
		return flxInterfacesPaqueteList;
	}

	public void setFlxInterfacesPaqueteList(List flxInterfacesPaqueteList) {
		this.flxInterfacesPaqueteList = flxInterfacesPaqueteList;
	}

}
