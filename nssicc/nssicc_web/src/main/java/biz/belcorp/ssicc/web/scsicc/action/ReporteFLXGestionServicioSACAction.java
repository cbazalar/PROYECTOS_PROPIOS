package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteFLXGestionServicioSACForm;

@ManagedBean
@SessionScoped
public class ReporteFLXGestionServicioSACAction extends BaseReporteAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6519251242446462487L;
	private String formatoReporte;
	private List siccRegionList;
	private LabelValue[] siccZonaList={};
	
	

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		// TODO Auto-generated method stub
		ReporteFLXGestionServicioSACForm form=new ReporteFLXGestionServicioSACForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		// TODO Auto-generated method stub
		return "reporteFLXGestionServicioSAC2XLS";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		// TODO Auto-generated method stub
		ReporteFLXGestionServicioSACForm reporteForm = (ReporteFLXGestionServicioSACForm) this.formReporte;
		formatoReporte = reporteForm.getFormatoExportacion();
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		String condicionRegion = this.obtieneCondicion(reporteForm.getRegionList(), "COD_REGI", "'");
		params.put("condicionRegion", condicionRegion);
		log.debug("condicionRegion"+ condicionRegion.toString());
		
		String condicionZona = this.obtieneCondicion(reporteForm.getZonaList(), "COD_ZONA", "'");
		params.put("condicionZona", condicionZona);
		log.debug("condicionZona"+condicionZona.toString());				
		
		String condicion = condicionRegion + condicionZona;
		
		params.put("condicion", condicion);
		
		log.debug("La condicion");
		log.debug(condicion);
																	
		params.put("NroReporte", "");				
		
		String titulo = getResourceMessage("reporteFLXGestionServicioSACForm.title");
		params.put("titulo", titulo );	
		
		return params;

	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		this.mostrarReporteXLS=true;
		this.mostrarReportePDF=false;
		if(log.isDebugEnabled()){
			log.debug("ReporteFLXGestionServicioSACAction - setViewAtributes");
		}
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ReporteFLXGestionServicioSACForm form=(ReporteFLXGestionServicioSACForm) this.formReporte;
		Map criteriaOperacion = new HashMap();
		AjaxService ajaxService=(AjaxService) getBean("ajaxService");
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		form.setCodigoPais(pais.getCodigo());
		
		criteriaOperacion.put("codigoPais", form.getCodigoPais());
		this.siccRegionList=reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);
		
		String[]reg={""};
		this.siccZonaList=ajaxService.getZonasMultipleByPaisMarcaCanalRegion( form.getCodigoPais(), "T", "VD",reg,"T");					
	}
	
	public void loadZonas(ValueChangeEvent valor)
	{
		if (log.isDebugEnabled()) {
			log.debug("loadZonas");
		}
		
		ReporteFLXGestionServicioSACForm form=(ReporteFLXGestionServicioSACForm) this.formReporte;
		String [] reg=(String[]) valor.getNewValue();
		log.debug("valorrrrrrrrrrrrrrrrrrrrrr::::::"+reg);
		AjaxService ajaxService=(AjaxService) getBean("ajaxService");
		
		if (!valor.equals(null) && reg.length > 0) {			
			this.siccZonaList=ajaxService.getZonasMultipleByPaisMarcaCanalRegion(form.getCodigoPais(), "T", "VD", reg, "");
			form.setZonaList(null);
		} else {
			this.siccZonaList=null;
			form.setZonaList(null);
		}
	}

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}
	
	
	
}
