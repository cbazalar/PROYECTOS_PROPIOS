package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCOBRecuperacionCobranzaFFVVForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;


@SuppressWarnings({"unchecked","rawtypes"})
@SessionScoped
@ManagedBean
public class ReporteCOBRecuperacionCobranzaFFVVAction extends BaseReporteAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1798779804810880387L;
	
	private LabelValue[] siccRegionList;
	
	@Override
	protected String devuelveBeanReporteService() {
		
		return "reportes.reporteCOBRecuperacionCobranzaFFVVServiceImpl";
	}
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		
		ReporteCOBRecuperacionCobranzaFFVVForm r = new ReporteCOBRecuperacionCobranzaFFVVForm();
		return r;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		// TODO Auto-generated method stub
		ReporteCOBRecuperacionCobranzaFFVVForm f =(ReporteCOBRecuperacionCobranzaFFVVForm)this.formReporte;
		params.put("usuario", mPantallaPrincipalBean.getCurrentUser().getLogin());
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		this.mostrarReportePDF = false;
		this.mostrarReporteOTXT = true;
		
		ReporteCOBRecuperacionCobranzaFFVVForm f = (ReporteCOBRecuperacionCobranzaFFVVForm)this.formReporte;
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		
		Map criteria = new HashMap();
		// Asignamos al codigo del periodo el valor por defecto
        criteria.put("codigoPais", pais.getCodigo());
        criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
		
		PedidoControlFacturacion controlFacturacion = (PedidoControlFacturacion) service.getControlFacturacionById(criteria);
		
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		
//		AjaxService ajax = (AjaxService) getBean("ajaxService");
		this.setSiccRegionList(ajaxService.getRegionesByPaisMarcaCanal(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT));
	
	}

	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}
	
}
