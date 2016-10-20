package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.collections.MapUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSACIndicadoresForm;

/**
 * The Class ReporteSACIndicadoresAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 22/05/2014
 */
@ManagedBean
@SessionScoped
public class ReporteSACIndicadoresAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 1L;
	private LabelValue[] sacIndicadoresList = {};
	private LabelValue[] siccPeriodoList = {};	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSACIndicadoresForm form = new ReporteSACIndicadoresForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoExportacion)){
			return "reporteSACIndicadoresXLS";
		}
		return "";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("prepareParameterMap");
		}
		ReporteSACIndicadoresForm form = (ReporteSACIndicadoresForm) this.formReporte;
		params.put("NroReporte", " ");
		params.put("titulo",getResourceMessage("reporteSACIndicadoresForm.titulo"));
		
		if(form.getIndicadores() != null && form.getIndicadores().length > 0){
			params.put("indicadores", obtieneCondicion(form.getIndicadores(), "ind.COD_INDI", "'"));
		}
		
		form.setBeforeExecuteReporte(true);
		
		return params;
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		this.mostrarReportePDF = false;
		this.mostrarReporteXLS = true;		
		ReporteSACIndicadoresForm reporteSICForm = (ReporteSACIndicadoresForm) this.formReporte;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteria = new HashMap();
		List listaIndicadores = reporteService.getIndicadoresSAC(criteria);
		this.sacIndicadoresList = new LabelValue[listaIndicadores.size()];
		int i = 0;
		for (Object object : listaIndicadores) {
			LabelValue labelValue = new LabelValue();
			labelValue.setLabel(((Base)object).getDescripcion());
			labelValue.setValue(((Base)object).getCodigo());
			this.getSacIndicadoresList()[i] = labelValue;
			i++;
		}
		
		criteria.put("codigoPais", this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		List listaPeriodo = service.getPeriodosByPMC(criteria);
		this.siccPeriodoList = new LabelValue[listaPeriodo.size()];
		int j = 0;
		for (Object object : listaPeriodo) {
			LabelValue labelValue = new LabelValue();
			labelValue.setLabel(((Base)object).getDescripcion());
			labelValue.setValue(((Base)object).getCodigo());
			this.getSiccPeriodoList()[j] = labelValue;
			j++;
		}
		reporteSICForm.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(this.mPantallaPrincipalBean.getCurrentCountry().getCodigo(), Constants.CODIGO_CANAL_DEFAULT));		
		reporteSICForm.setIndicadores(null);
		
		log.debug("Todo Ok: Redireccionando");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanConstructorService()
	 */
	protected String devuelveBeanReporteService(){
		return "reportes.reporteSACIndicadoresService";
	}
	
	public LabelValue[] getSacIndicadoresList() {
		return sacIndicadoresList;
	}

	public void setSacIndicadoresList(LabelValue[] sacIndicadoresList) {
		this.sacIndicadoresList = sacIndicadoresList;
	}

	public LabelValue[] getSiccPeriodoList() {
		return siccPeriodoList;
	}

	public void setSiccPeriodoList(LabelValue[] siccPeriodoList) {
		this.siccPeriodoList = siccPeriodoList;
	}

	
	
	
}
