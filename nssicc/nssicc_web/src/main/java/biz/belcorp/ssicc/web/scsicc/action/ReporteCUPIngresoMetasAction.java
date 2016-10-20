package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCUPIngresoMetasForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;


@ManagedBean
@SessionScoped
public class ReporteCUPIngresoMetasAction  extends BaseReporteAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7857747175137529715L;
	private String formatoReporte;
	private LabelValue[]siccRegionList={};
	private LabelValue[]siccZonaList={};
	
	protected String  devuelveBeanReporteService() {
		return "reportes.reporteCUPIngresoMetasService";
		
	}

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		// TODO Auto-generated method stub
		ReporteCUPIngresoMetasForm reporteForm= new ReporteCUPIngresoMetasForm();
		return reporteForm;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		// TODO Auto-generated method stub
		if ("XLS".equals(formatoReporte))
			return "reporteCUPIngresoMetasXLS";
		else
			return "reporteMaestroHorizontal";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'prepareParameterMap' method");
		}
		
		ReporteCUPIngresoMetasForm reporteForm= (ReporteCUPIngresoMetasForm) this.formReporte;
		this.formatoReporte=reporteForm.getFormatoExportacion();

		params.put("codigoUsuario", reporteForm.getUsuario().getLogin());
//		params.put("usuario", reporteForm.getUsuario().getLogin());
		
		return params;
		
		
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		this.mostrarReportePDF=false;
		this.mostrarReporteXLS=true;
		
		AjaxService ajaxService=(AjaxService) getBean("ajaxService");
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		ReporteCUPIngresoMetasForm f=(ReporteCUPIngresoMetasForm) this.formReporte;
		f.setCodigoPais(pais.getCodigo());		
		this.siccRegionList=ajaxService.getRegionesByPaisMarcaCanal(f.getCodigoPais(), "T", "VD");
		String region0="Todos";
		String[] reg = {region0};
		this.siccZonaList=ajaxService.getZonasMultipleByPaisMarcaCanalRegion(f.getCodigoPais(),"T","VD",reg,"T");
		
		
	}
	
	public void loadZonas(ValueChangeEvent valor) { 
		if (log.isDebugEnabled()) {
			log.debug("loadZonas");
		}
						
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		ReporteCUPIngresoMetasForm f=(ReporteCUPIngresoMetasForm) this.formReporte;
		f.setCodigoPais(pais.getCodigo());
		String regiones=(String) valor.getNewValue();
		String [] reg={regiones};
		
		if(valor!=null && reg.length>0)
		{
			AjaxService ajaxService=(AjaxService) getBean("ajaxService");
			siccZonaList = ajaxService.getZonasMultipleByPaisMarcaCanalRegion(f.getCodigoPais(),"T","VD",reg,"T");
			f.setCodigoZona(null);
		}
		else
		{
			this.siccZonaList=null;
		}
		
	}
	
	public String setValidarReporte() {
		ReporteCUPIngresoMetasForm form = (ReporteCUPIngresoMetasForm)this.formReporte;
		int codperini = Integer.parseInt(form.getCodigoPeriodoInicial());
		int codperfin = Integer.parseInt(form.getCodigoPeriodoFinal());
		log.debug("codigoinicial"+codperini);
		log.debug("codigofinal"+codperfin);
		if(codperfin<codperini){
			String mensaje =  this.getResourceMessage("reporteCUPIngresoMetasForm.errorInicialFinal");
			return mensaje;
		}

	    					
	    return null;
	}

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
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
