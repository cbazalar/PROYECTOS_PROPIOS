package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSACControlFacturacionEntregaPedidosForm;

@ManagedBean
@SessionScoped
public class ReporteSACControlFacturacionEntregaPedidosAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 1L;	

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSACControlFacturacionEntregaPedidosForm form = new ReporteSACControlFacturacionEntregaPedidosForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "";
		
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("prepareParameterMap...");
		}
		
		ReporteSACControlFacturacionEntregaPedidosForm form = (ReporteSACControlFacturacionEntregaPedidosForm)this.formReporte;
		
		params.put("codigoPais", form.getCodigoPais());
		params.put("codigoPeriodoInicial", form.getCodigoPeriodoInicial());
		params.put("codigoPeriodoFinal", form.getCodigoPeriodoFinal());
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {		
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		ReporteSACControlFacturacionEntregaPedidosForm form = (ReporteSACControlFacturacionEntregaPedidosForm)this.formReporte;
		
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		
		this.mostrarReporteOCSV = true;
		this.mostrarReportePDF = false;
		
		Map criteriaOperacion = new HashMap();
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		String periodoActual = new String();
		
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		
		//-- Logica 
		periodoActual = service.getPeriodoDefaultByPaisCanal(pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT);
		
		form.setCodigoPeriodoInicial(periodoActual);
		form.setCodigoPeriodoFinal(periodoActual);
		form.setCodigoPais(pais.getCodigo());
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanReporteService()
	 */
	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reporteSACControlFacturacionEntregaPedidosService";
	}
}