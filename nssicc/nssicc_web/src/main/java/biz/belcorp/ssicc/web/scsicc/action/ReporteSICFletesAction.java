package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSICFletesForm;

/**
 * The Class ReporteSICFletesAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 14/05/2014
 */
@ManagedBean
@SessionScoped
public class ReporteSICFletesAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = -7372175913822687524L;
	
	private String sufijoReporte;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSICFletesForm form = new ReporteSICFletesForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {		
		ReporteSICFletesForm form = (ReporteSICFletesForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		log.debug(StringUtils.substring(sufijoReporte, 0, 7));
		log.debug(StringUtils.substring(sufijoReporte, 0, 6));
		
		if ("XLS".equals(form.getFormatoExportacion())){
			return "reporteSICFletes" + sufijoReporte;
		}else{ 
			if ("Resumen".equals(StringUtils.substring(sufijoReporte, 0, 7))){
				return "reporteMaestroVertical";
			}else{
				return "reporteMaestroHorizontal";
			}
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteSICFletesForm form = (ReporteSICFletesForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		
		if ("PDF".equals(form.getFormatoExportacion())){
			return "reporteSICFletes" + sufijoReporte;
		}else{
			return "";
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {;
		if(log.isDebugEnabled()){
			log.debug("prepareParameterMap");
		}
		
		ReporteSICFletesForm reporteSICForm = (ReporteSICFletesForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		log.debug(reporteSICForm.getFormatoExportacion());
		
		sufijoReporte = reporteSICForm.getTipoReporte() + reporteSICForm.getFormatoExportacion();
		log.debug(sufijoReporte);
		params.put("NroReporte", "");
		
		String condicionZonas = obtieneCondicion(reporteSICForm.getZonaList(),"f.COD_ZONA", "'");
		String condicionRegion = obtieneCondicion(reporteSICForm.getRegionList(), "g.COD_REGI", "'");
		String condicion = condicionZonas + condicionRegion;
		
		Map criteria = params;		
		
		String oidPais =reporteService.getOidString("getOidPaisByCodigoPais",criteria);
		criteria.put("codigoPeriodo",reporteSICForm.getCodigoPeriodo());
		String oidPeriodo =reporteService.getOidString("getOidPeriodoByCodigoPeriodo",criteria);
		
		String titulo = this.getResourceMessage("reporteSICFletesForm.titulo");
		criteria.put("codigoPais", reporteSICForm.getCodigoPais());
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String indSolPedFactAn = pais.getIndicadorExcluirPedidosAnulados();
		
		if ("Resumen".equals(reporteSICForm.getTipoReporte())){
			titulo+= this.getResourceMessage("reporteSICFletesForm.titulo.resumen");
		}else{
			if ("ResumenAnulaciones".equals(reporteSICForm.getTipoReporte())){
				titulo+= this.getResourceMessage("reporteSICFletesForm.titulo.resumenAnulaciones");
			}
		}
		if ("Resumen2".equals(reporteSICForm.getTipoReporte())){
			titulo+= this.getResourceMessage("reporteSICFletesForm.titulo.resumen2");
		}else{
			titulo+= this.getResourceMessage("reporteSICFletesForm.titulo.detallado");
		}
		
		params.put("titulo", titulo);
		params.put("condicion", condicion);
		params.put("oidPais", oidPais);
		params.put("oidPeriodo", oidPeriodo);
		
		if(reporteSICForm.getFechaFacturacionDate() != null){
			reporteSICForm.setFechaFacturacion(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, reporteSICForm.getFechaFacturacionDate()));
		}
		
		params.put("fechaFacturacion", reporteSICForm.getFechaFacturacion());
		params.put("indSolPedFactAn", indSolPedFactAn);
				
		return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("ReporteSICFacturacionAction - setViewAtributes");
		}
		
		this.mostrarReporteXLS = true;
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		
		List listaRegiones = reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);
		this.siccRegionList = new LabelValue[listaRegiones.size()];
		
		int i = 0;
		
		for(Object object : listaRegiones){
			LabelValue labelValue = new LabelValue();
			labelValue.setLabel(((Base)object).getDescripcion());
			labelValue.setValue(((Base)object).getCodigo().toString());
			this.getSiccRegionList()[i] = labelValue;
			i++;
		}
	}
	
	/**
	 * Load zonas.
	 *
	 * @param val the val
	 */
	public void loadZonas(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("loadZonas");
		}
		String[] valor = (String[]) val.getNewValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		ReporteSICFletesForm reporteSICForm = (ReporteSICFletesForm) this.formReporte;
		
		this.siccZonaList = ajax.getZonasMultipleByPaisMarcaCanalRegion(this.mPantallaPrincipalBean.getCurrentCountry().getCodigo(), 
				Constants.CODIGO_MARCA_DEFAULT, 
				Constants.CODIGO_CANAL_DEFAULT, 
				valor, 
				"");
	}
	
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	public String getSufijoReporte() {
		return sufijoReporte;
	}

	public void setSufijoReporte(String sufijoReporte) {
		this.sufijoReporte = sufijoReporte;
	}
	
	
}
