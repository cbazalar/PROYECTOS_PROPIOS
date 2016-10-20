package biz.belcorp.ssicc.web.sisicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazCOBEnviarArchivosInfocorpForm;

@ManagedBean
@SessionScoped
public class InterfazCOBEnviarArchivosInfocorpAction extends BaseInterfazAbstractAction
{
	private static final long serialVersionUID = -1970793498134282670L;
	private LabelValue[] siccPeriodoList;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazCOBEnviarArchivosInfocorpForm f = new InterfazCOBEnviarArchivosInfocorpForm(); 
		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		InterfazCOBEnviarArchivosInfocorpForm f = (InterfazCOBEnviarArchivosInfocorpForm)this.formInterfaz;
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		
		this.siccPeriodoList = ajax.getPeriodosByPaisMarcaCanal(f.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT);
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
		
        MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
        PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);
        
        f.setCampanya(controlFacturacion.getCodigoPeriodo());
		
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception 
	{
		params = super.prepareParamsBeforeExecute(params, form);
		InterfazCOBEnviarArchivosInfocorpForm f = (InterfazCOBEnviarArchivosInfocorpForm)this.formInterfaz;
		params.put("campanya", f.getCampanya());
		
		return params;		
	}
	
	public LabelValue[] getSiccPeriodoList() {
		return siccPeriodoList;
	}

	public void setSiccPeriodoList(LabelValue[] siccPeriodoList) {
		this.siccPeriodoList = siccPeriodoList;
	}

}
