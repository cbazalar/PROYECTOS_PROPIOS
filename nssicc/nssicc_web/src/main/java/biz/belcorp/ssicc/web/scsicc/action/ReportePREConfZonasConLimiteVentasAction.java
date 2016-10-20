package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReportePREConfZonasConLimiteVentasForm;


/**
 * The Class ReportePREConfZonasConLimiteVentasAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 21/07/2014
 */
@ManagedBean
@SessionScoped
public class ReportePREConfZonasConLimiteVentasAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The sufijo reporte. */
	private String sufijoReporte;
	
	private List siccTipoOfertaList = new ArrayList();
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReportePREConfZonasConLimiteVentasForm form = new ReportePREConfZonasConLimiteVentasForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {		
		ReportePREConfZonasConLimiteVentasForm form = (ReportePREConfZonasConLimiteVentasForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(form.getFormatoExportacion())){
			return "reportePREConfZonasConLimiteVentasXLS";
		}else{
			return "reporteMaestroHorizontal";
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReportePREConfZonasConLimiteVentasForm form = (ReportePREConfZonasConLimiteVentasForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		return "reportePREConfZonasConLimiteVentasPDF";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("prepareParameterMap");
		}
		ReportePREConfZonasConLimiteVentasForm reporte = (ReportePREConfZonasConLimiteVentasForm)this.formReporte;
		
		//Parametros estandar que se envían a los reportes
		params.put("NroReporte", " ");
		
		params.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		
		//filtros del reporte
		
		params.put("periodoIni", reporte.getPeriodoIni());
		if ( reporte.getPeriodoFin().length() == 0 ){
			params.put("periodoFin", reporte.getPeriodoIni());
		}else{
			params.put("periodoFin", reporte.getPeriodoFin());	
		}
		
		params.put("tipoOferta", reporte.getTipoOferta());	
		
		if (reporte.getCodigoVenta().length() == 0){
			params.put("codigoVenta", "TODOS");
		}else{
			params.put("codigoVenta", reporte.getCodigoVenta());	
		}
		if ( reporte.getCodigoSAP().length() == 0){
			params.put("codigoSAP", "TODOS");
		}else{
			params.put("codigoSAP", reporte.getCodigoSAP());
		}
		
		params.put("titulo",this.getResourceMessage("reportePREConfZonasConLimiteVentasForm.title"));
				
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
		ReportePREConfZonasConLimiteVentasForm reporteSICForm = (ReportePREConfZonasConLimiteVentasForm)this.formReporte;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		//Carga la lista de tipo ofertas
		siccTipoOfertaList = reporteService.getTipoOfertas();
		
		//Seteo de las variables por defecto que se deben mostrar por pantalla
		reporteSICForm.setCodigoPais(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		reporteSICForm.setPeriodoIni(service.getPeriodoDefaultByPaisCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), Constants.CODIGO_CANAL_DEFAULT));
	}
	
	public String getSufijoReporte() {
		return sufijoReporte;
	}

	public void setSufijoReporte(String sufijoReporte) {
		this.sufijoReporte = sufijoReporte;
	}

	public List getSiccTipoOfertaList() {
		return siccTipoOfertaList;
	}

	public void setSiccTipoOfertaList(List siccTipoOfertaList) {
		this.siccTipoOfertaList = siccTipoOfertaList;
	}

}
