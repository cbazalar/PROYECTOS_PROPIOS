package biz.belcorp.ssicc.web.scsicc.action;

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
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteOCRSeguimientoDeudaForm;

@ManagedBean
@SessionScoped
public class ReporteOCRSeguimientoDeudaAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 1L;	
	
	private List siccPeriodoList;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	
	private String reporteExcel;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteOCRSeguimientoDeudaForm form = new ReporteOCRSeguimientoDeudaForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("devuelveNombreReporte");
		}
		
		return reporteExcel;		
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {		
		return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteOCRSeguimientoDeudaForm form = (ReporteOCRSeguimientoDeudaForm) this.formReporte;
		//StringBuilder reporteExcel = new StringBuilder("reporteOCRSeguimientoDeuda");
		reporteExcel = "reporteOCRSeguimientoDeuda" + form.getAgrupamientoReporte() + "XLS";	
		 
		form.setReporteExcel(reporteExcel.toString());
		String titulo = this.getReportResourceMessage("reporteOCRDeudaForm.titulo")	+ " ";
		titulo = titulo	+ StringUtils.substring(form.getCodigoPeriodo(), 0, 4)
				+ "/"
				+ StringUtils.substring(form.getCodigoPeriodo(), 4, 6);
		if (StringUtils.equals((form.getTipoReporte()), "1"))
			titulo = titulo
					+ "\n "
					+ this.getReportResourceMessage("reporteOCRDeudaForm.historicos");
		else
			titulo = titulo + "\n "	+ this.getReportResourceMessage("reporteOCRDeudaForm.actual");

		String condicionEstadoPedido = "";
		String condicionEstadoDeuda = "";
		
		if(StringUtils.isNotBlank(form.getEstadoPedido())){
			condicionEstadoPedido = form.getEstadoPedido();
		}
		
		if(StringUtils.isNotBlank(form.getEstadoDeuda())){
			condicionEstadoDeuda = form.getEstadoDeuda();
		}
		
		
		
		params.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		params.put("condicion", condicionEstadoPedido + condicionEstadoDeuda);
		params.put("NroReporte", this.getReportResourceMessage("reporteOCRSeguimientoDeudaForm.numero.reporte"));
		params.put("titulo", titulo);
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {		
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}		
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;	
		
		valoresByDefault();		
		loadRegiones();
	}
	
	private void valoresByDefault(){	
		ReporteOCRSeguimientoDeudaForm form = (ReporteOCRSeguimientoDeudaForm) this.formReporte;
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		form.setCodigoPais(pais.getCodigo());
		form.setTipoReporte("1");
		form.setCodigoRegion("");
	}
	
	private void loadRegiones() {		
		ReporteOCRSeguimientoDeudaForm form = (ReporteOCRSeguimientoDeudaForm) this.formReporte;
		AjaxService ajaxService = (AjaxService)this.getBean("ajaxService");		
		if (StringUtils.equals(form.getTipoReporte(), "1"))			
			this.siccRegionList=ajaxService.getRegionesByPeriodoBasCtrlFact(form.getCodigoPais(), form.getCodigoPeriodo() ,"");
	    else
	    	this.siccRegionList=ajaxService.getRegionesByPeriodoIntEviPerioRegio(form.getCodigoPais(),form.getCodigoPeriodo(),"");
		LabelValue[] result = null;
		result = new LabelValue[1];
		result[0] = new LabelValue("Todos", "");
		this.siccZonaList=result;		
		
	}
	
	
	
	private void loadZonas(){		
		ReporteOCRSeguimientoDeudaForm f= (ReporteOCRSeguimientoDeudaForm) this.formReporte;
		AjaxService ajaxService = (AjaxService)this.getBean("ajaxService");
		if(StringUtils.equals(f.getTipoReporte(), "1")){
			this.siccZonaList=ajaxService.getZonasByPeriodoBasCtrlFact(f.getCodigoPais(), f.getCodigoPeriodo(), "", "T");
		}else{
			this.siccZonaList=ajaxService.getZonasByPeriodoIntEviPerioRegioZona(f.getCodigoPais(), f.getCodigoPeriodo(), "", "T");
		}
	}
	
	//Cambia las zonas por la region mostrada
	public void cambiaZonasByRegion(ValueChangeEvent val){	
		
		String valor = (String) val.getNewValue();
		if(StringUtils.isNotBlank(valor) || StringUtils.isNotEmpty(valor)){
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			ReporteOCRSeguimientoDeudaForm f= (ReporteOCRSeguimientoDeudaForm) this.formReporte;			
			if(StringUtils.equals(f.getTipoReporte(), "1")){
				this.siccZonaList=ajaxService.getZonasByPeriodoBasCtrlFact(f.getCodigoPais(), f.getCodigoPeriodo(), valor, "T");
			}else{
				this.siccZonaList=ajaxService.getZonasByPeriodoIntEviPerioRegioZona(f.getCodigoPais(), f.getCodigoPeriodo(), valor, "T");
			}
		}
	}
	
	//cambia las regiones por el tipo de Doc
	public void loadPeriodosyRegion(ValueChangeEvent val){		
		String valor = (String) val.getNewValue();
			if(StringUtils.isNotBlank(valor)){
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			ReporteOCRSeguimientoDeudaForm f= (ReporteOCRSeguimientoDeudaForm) this.formReporte;
			f.setTipoReporte(valor);
			if (StringUtils.equals(f.getTipoReporte(), "1")){
				this.siccRegionList=ajaxService.getRegionesByPeriodoBasCtrlFact(f.getCodigoPais(), f.getCodigoPeriodo() ,"T");
			}else{
				this.siccRegionList=ajaxService.getRegionesByPeriodoIntEviPerioRegio(f.getCodigoPais(),f.getCodigoPeriodo(),"T");
			}
		}
	}
	
	//Cambia las regiones por periodo
	public void cambiaRegionByPeriodo(String val) {	
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		ReporteOCRSeguimientoDeudaForm form = (ReporteOCRSeguimientoDeudaForm) this.formReporte;
		
		String valor = val;	
		if(StringUtils.isNotBlank(valor)){
			form.setCodigoPeriodo(valor);			
			if (StringUtils.equals(form.getTipoReporte(), "1")){
				this.siccRegionList=ajaxService.getRegionesByPeriodoBasCtrlFact(form.getCodigoPais(), valor, "");
			}else{
		    	this.siccRegionList=ajaxService.getRegionesByPeriodoIntEviPerioRegio(form.getCodigoPais(), valor, "");
			}
			
			if(StringUtils.equals(form.getTipoReporte(), "1")){
				this.siccZonaList=ajaxService.getZonasByPeriodoBasCtrlFact(form.getCodigoPais(), form.getCodigoPeriodo(), "", "T");
			}else{
				this.siccZonaList=ajaxService.getZonasByPeriodoIntEviPerioRegioZona(form.getCodigoPais(), form.getCodigoPeriodo(), "", "T");
			}
			
			form.setCodigoRegion(" ");
			form.setCodigoZona("");
		}
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

	public List getSiccPeriodoList() {
		return siccPeriodoList;
	}

	public void setSiccPeriodoList(List siccPeriodoList) {
		this.siccPeriodoList = siccPeriodoList;
	}
}
