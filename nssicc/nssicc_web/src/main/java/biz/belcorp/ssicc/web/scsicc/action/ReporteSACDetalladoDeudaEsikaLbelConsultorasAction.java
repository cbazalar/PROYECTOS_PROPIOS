package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSACDetalladoDeudaEsikaLbelConsultorasForm;


/**
 * The Class ReporteSACFacturacionDetalleAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 28/08/2014
 */
@ManagedBean
@SessionScoped
public class ReporteSACDetalladoDeudaEsikaLbelConsultorasAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	private LabelValue[] siccRegionList = {};
	
	private LabelValue[] siccZonaList = {};

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSACDetalladoDeudaEsikaLbelConsultorasForm form = new ReporteSACDetalladoDeudaEsikaLbelConsultorasForm();
		return form;
	}
	
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteSACDetalladoDeudaEsikaLbelConsultorasForm form = (ReporteSACDetalladoDeudaEsikaLbelConsultorasForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		
		if ("XLS".equals(form.getFormatoExportacion())) {
			return "reporteSACDetalladoDeudaEsikaLbelConsultorasDXLS";
		}else{
			return "reporteMaestroHorizontal";
		}
	}
	
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteSACDetalladoDeudaEsikaLbelConsultorasForm form = (ReporteSACDetalladoDeudaEsikaLbelConsultorasForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		
		return "";
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");			
		}
		
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		
		ReporteSACDetalladoDeudaEsikaLbelConsultorasForm reporteForm = (ReporteSACDetalladoDeudaEsikaLbelConsultorasForm) formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		reporteForm.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), Constants.CODIGO_CANAL_DEFAULT));
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());

		List listaRegiones = reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);
		
		if(listaRegiones.size()>0){
			this.siccRegionList = new LabelValue[listaRegiones.size()]; 		
			int i = 0;
			
			for(Object object : listaRegiones){
				LabelValue labelValue = new LabelValue();
				labelValue.setLabel(((Base)object).getDescripcion());
				labelValue.setValue(((Base)object).getCodigo());
				this.getSiccRegionList()[i] = labelValue;
				i++;
			}
		}
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("prepareParameterMap");
		}
		
		ReporteSACDetalladoDeudaEsikaLbelConsultorasForm form = (ReporteSACDetalladoDeudaEsikaLbelConsultorasForm) formReporte;
		
		String condicionRegion = this.obtieneCondicion(form.getRegionList(), "COD_REGI", "'");
		String condicionZonas = this.obtieneCondicion(form.getZonaList(), "COD_ZONA", "'");		
		String condicion = condicionRegion + condicionZonas;
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		Map criteria = params;
		String oidPais = reporteService.getOidString("getOidPaisByCodigoPais",criteria);
		criteria.put("oidPais", oidPais);
		criteria.put("condicion", condicion);
					
		params.put("titulo", getResourceMessage("reporteSACDetalladoDeudaEsikaLbelConsultorasForm.titulo"));
		return params;
	}
	
	/**
	 * Cambia zonas by region.
	 *
	 * @param val the val
	 */
	public void cambiaZonasByRegion(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("cambiaZonas...");
		}
		String[] valores = (String[]) val.getNewValue();
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		ReporteSACDetalladoDeudaEsikaLbelConsultorasForm form = (ReporteSACDetalladoDeudaEsikaLbelConsultorasForm) this.formReporte;
		this.setSiccZonaList(ajaxService.getZonasMultipleByPaisMarcaCanalRegion(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),  Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, valores, Constants.OPCION_TODOS));
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
}
