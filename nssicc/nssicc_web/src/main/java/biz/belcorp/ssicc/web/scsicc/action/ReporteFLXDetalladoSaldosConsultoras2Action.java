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
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteFLXDetalladoSaldosConsultoras2Form;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.flx.MantenimientoFLXConsultoraService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
//import biz.belcorp.ssicc.web.scsicc.form.ReporteFLXDetalladoSaldosConsultoras2Form;

@ManagedBean
@SessionScoped
public class ReporteFLXDetalladoSaldosConsultoras2Action extends BaseReporteAbstractAction {
	
	private static final long serialVersionUID = 1L;
	private String formatoReporte;
	private List siccRegionList;
	private LabelValue[] siccZonaList={};


	protected String  devuelveBeanReporteService() {
		return "reportes.reporteFLXDetalladoSaldosConsultoras2Service";
		
	}
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteFLXDetalladoSaldosConsultoras2Form f = new ReporteFLXDetalladoSaldosConsultoras2Form();
		return f;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteMaestroVertical";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		if ("PDF".equals(formatoReporte))
			return "reporteFLXDetalladoSaldosConsultoras2PDF";
		else
			return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteFLXDetalladoSaldosConsultoras2Form f = (ReporteFLXDetalladoSaldosConsultoras2Form) this.formReporte;
		formatoReporte = f.getFormatoExportacion();		
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		String condicionRegion = this.obtieneCondicion(f.getRegionList(), "GTT.COD_REGI", "'");
		params.put("condicionRegion", condicionRegion);
		log.debug("condicionRegion"+ condicionRegion.toString());
		
		String condicionZona = this.obtieneCondicion(f.getZonaList(), "GTT.COD_ZONA", "'");
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

		if(log.isDebugEnabled()){
			log.debug("ReporteFLXDetalladoSaldosConsultoras2Action - setViewAtributes");
		}
		AjaxService ajaxService= (AjaxService) getBean("ajaxService");
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ReporteFLXDetalladoSaldosConsultoras2Form form=(ReporteFLXDetalladoSaldosConsultoras2Form) this.formReporte;
		form.setCodigoPais(pais.getCodigo());
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		this.siccRegionList=reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);
		String [] reg={""};
		this.siccZonaList=ajaxService.getZonasMultipleByPaisMarcaCanalRegion(form.getCodigoPais(), "T", "VD", reg, "T");
	}
	
	public void beforeExecuteReporte() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("ReporteFLXDetalladoSaldosConsultoras2Action - beforeExecuteReporte");
		}
		Map params = ((ReporteFLXDetalladoSaldosConsultoras2Form)this.formReporte).getParametrosReporteGeneral();
		
		log.debug("Los parametros del Reporte en el before son: "	+ params.toString());
		
		params.put("codigoCampana", ((ReporteFLXDetalladoSaldosConsultoras2Form)this.formReporte).getCodigoCampana());
		params.put("codigoPais", this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		params.put("codigoCliente", ((ReporteFLXDetalladoSaldosConsultoras2Form)this.formReporte).getCodigoCliente());
		this.getFormReporte().setParametrosReporteGeneral(params);
		MantenimientoFLXConsultoraService service = (MantenimientoFLXConsultoraService) getBean("spusicc.mantenimientoFLXConsultoraService");
		 service.executeReporteDetalleSaldo(params);
		this.setViewReporteMedia(true);
		this.setVisualizarReporte(true);
		return;
	}
	
	public void loadZonas(ValueChangeEvent valor)
	{
		
		if (log.isDebugEnabled()) {
			log.debug("loadZonas");
		}
		
		ReporteFLXDetalladoSaldosConsultoras2Form form=(ReporteFLXDetalladoSaldosConsultoras2Form) this.formReporte;		
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