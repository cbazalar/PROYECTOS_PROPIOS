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
import biz.belcorp.ssicc.web.scsicc.form.ReporteFLXDetalladoSaldosConsultorasForm;

@ManagedBean
@SessionScoped
public class ReporteFLXDetalladoSaldosConsultorasAction extends BaseReporteAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3557040530281374094L;

	private String formatoReporte;	
	private List siccRegionList;
	private LabelValue[] siccZonaList={}; 
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		// TODO Auto-generated method stub
		ReporteFLXDetalladoSaldosConsultorasForm reporteForm=new ReporteFLXDetalladoSaldosConsultorasForm();
		return reporteForm;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		// TODO Auto-generated method stub
		if ("PDF".equals(formatoReporte))
			return "reporteMaestroHorizontal";
		else{
			return "reporteFLXDetalladoSaldosConsultorasXLS";
		}
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		// TODO Auto-generated method stub
		if ("PDF".equals(formatoReporte))
			return "reporteFLXDetalladoSaldosConsultorasPDF";
		else
			return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		// TODO Auto-generated method stub
		ReporteFLXDetalladoSaldosConsultorasForm reporteForm = (ReporteFLXDetalladoSaldosConsultorasForm) this.formReporte;
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
		
		String titulo = getResourceMessage("reporteFLXDetalladoSaldosConsultorasForm.title");
		params.put("titulo", titulo );	
		
		
		return params;	
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		AjaxService ajaxService= (AjaxService) getBean("ajaxService");
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ReporteFLXDetalladoSaldosConsultorasForm form=(ReporteFLXDetalladoSaldosConsultorasForm) this.formReporte;
		form.setCodigoPais(pais.getCodigo());
		Map criteriaOperacion = new HashMap();		
		criteriaOperacion.put("codigoPais", form.getCodigoPais());
		this.siccRegionList=reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);
		String [] reg={""};
		this.siccZonaList=ajaxService.getZonasMultipleByPaisMarcaCanalRegion( form.getCodigoPais(), "T", "VD",reg ,"T");
		
	}
	
	public void loadZonas(ValueChangeEvent valor)
	{
		
		if (log.isDebugEnabled()) {
			log.debug("loadZonas");
		}
		
		ReporteFLXDetalladoSaldosConsultorasForm form=(ReporteFLXDetalladoSaldosConsultorasForm) this.formReporte;		
		String [] reg=(String[]) valor.getNewValue();
		log.debug("valorrrrrrrrrrrrrrrrrrrrrr::::::"+reg);
		AjaxService ajaxService=(AjaxService) getBean("ajaxService");
		
		if (!valor.equals(null) && reg.length > 0) {
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			this.siccZonaList=ajaxService.getZonasMultipleByPaisMarcaCanalRegion(form.getCodigoPais(), "T", "VD", reg, "");
			form.setZonaList(null);
		} else {
			this.siccZonaList = null;
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
