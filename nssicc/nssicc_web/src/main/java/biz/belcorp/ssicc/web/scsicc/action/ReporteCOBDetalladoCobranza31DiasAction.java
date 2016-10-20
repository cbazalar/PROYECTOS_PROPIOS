package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCOBDetalladoCobranza31DiasForm;


/**
 * The Class ReporteCOBDetalladoCobranza31DiasAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 18/03/2014
 */
@ManagedBean
@SessionScoped
public class ReporteCOBDetalladoCobranza31DiasAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 1L;
	List siccSociedadList = new ArrayList();
	List siccRegionList = new ArrayList();
	List siccZonaList = new ArrayList();

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOBDetalladoCobranza31DiasForm form = new ReporteCOBDetalladoCobranza31DiasForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteCOBDetalladoCobranza31DiasDXLS";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteCOBDetalladoCobranza31DiasForm reporteCOBForm = (ReporteCOBDetalladoCobranza31DiasForm) this.formReporte;
		this.setFormatoExportacion(reporteCOBForm.getFormatoExportacion());
		
		String condicionZonas = obtieneCondicion(reporteCOBForm.getZonaList(),"COD_ZONA", "'");
		String condicionRegion = obtieneCondicion(reporteCOBForm.getRegionList(), "COD_REGI", "'");
		String condicion = condicionZonas + condicionRegion;
		
		params.put("condicion", condicion);
		
		log.debug("Imprimiendo parámetros");
		log.debug(params);
		log.debug("Fin parámetros");
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCOBDetalladoCobranza31DiasAction.setViewAtributes' method");
		}
		
		this.mostrarReporteOCSV = true;
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		
		ReporteCOBDetalladoCobranza31DiasForm f = (ReporteCOBDetalladoCobranza31DiasForm) this.formReporte;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		
		f.setCodigoPeriodoInicial(DateUtil.convertDateToString("yyyyMM", new Date()));
		f.setCodigoPeriodoFinal(DateUtil.convertDateToString("yyyyMM", new Date()));
		
		siccSociedadList = service.getSociedadesByCodigoPais(pais.getCodigo());
		siccRegionList = reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);
		siccZonaList = new ArrayList();
	}
	
	
	/**
	 * Show zonasx region.
	 *
	 * @param val the val
	 */
	public void showZonasxRegion(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		log.debug(val.getNewValue().toString());
		if(StringUtils.isNotEmpty(val.getNewValue().toString()) 
				|| StringUtils.isNotBlank(val.getNewValue().toString())){
			String[] regionListado = (String[])val.getNewValue();
			log.debug(regionListado.length);
			
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			if(regionListado.length>0){
				siccZonaList = Arrays.asList(ajax.getZonasMultipleByPaisMarcaCanalRegion(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), "T", "VD", regionListado,"T"));			
			}else{
				siccZonaList = new ArrayList();
			}
		}
	}
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanConstructorService()
	 */
	protected String devuelveBeanReporteService(){
		return "reportes.reporteCOBDetalladoCobranza31DiasService";
	}

	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}

	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public List getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(List siccZonaList) {
		this.siccZonaList = siccZonaList;
	}
	
}