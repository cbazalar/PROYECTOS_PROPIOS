package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCOMSeguimientoCalificacionCampanaForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.comision.ProcesoCOMCalculoCalificacionTramoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
 

/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteCOMSeguimientoCalificacionCampanaAction extends
	BaseReporteAbstractAction {

	private static final long serialVersionUID = -5298972932156502655L;
	private List siccMarcaList;
	private List siccCanalList;
	private List comTipoComisionistaList;
	private List comTramoList;
	private String formatoReporte;
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOMSeguimientoCalificacionCampanaForm reporteForm = new ReporteCOMSeguimientoCalificacionCampanaForm();
		return reporteForm;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		this.formatoReporte = ((ReporteCOMSeguimientoCalificacionCampanaForm)this.formReporte).getFormatoExportacion();
		if ("XLS".equals(formatoReporte))
			return "reporteCOMSeguimientoCalificacionCampanaXLS";
		else
			return "reporteMaestroHorizontal";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		this.formatoReporte = ((ReporteCOMSeguimientoCalificacionCampanaForm)this.formReporte).getFormatoExportacion();
		if (("PDF".equals(formatoReporte))||("VPDF".equals(formatoReporte)))
			return "reporteCOMSeguimientoCalificacionCampana";
		return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCOMSeguimientoCalificacionCampanaAction.prepareParameterMap' method");
		}
		ReporteCOMSeguimientoCalificacionCampanaForm form = (ReporteCOMSeguimientoCalificacionCampanaForm) this.formReporte;
		formatoReporte = form.getFormatoExportacion();
		
		form.setBeforeExecuteReporte(true);
		params.put("NroReporte", " ");
		params.put("titulo", this.getReportResourceMessage("reporteCOMSeguimientoCalificacionCampanaForm.titulo"));
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		Map mapCampanas = reporteService.getPeriodosSeguimientoCalificacionCampana(params);
		String tituloCampana = this.getReportResourceMessage("reporteCOMSeguimientoCalificacionCampanaForm.campana");
		
		if (mapCampanas.get("CampanaAnt")!=null) 
			params.put("CampanaAnt", tituloCampana + " " +  mapCampanas.get("CampanaAnt"));

		if (mapCampanas.get("Campana1")!=null) 
			params.put("Campana1", tituloCampana + " " +  mapCampanas.get("Campana1"));

		if (mapCampanas.get("Campana2")!=null) 
			params.put("Campana2", tituloCampana + " " +  mapCampanas.get("Campana2"));

		if (mapCampanas.get("Campana3")!=null) 
			params.put("Campana3", tituloCampana + " " +  mapCampanas.get("Campana3"));

		if (mapCampanas.get("Campana4")!=null) 
			params.put("Campana4", tituloCampana + " " +  mapCampanas.get("Campana4"));
		
		if (mapCampanas.get("Campana5")!=null) 
			params.put("Campana5", tituloCampana + " " +  mapCampanas.get("Campana5"));

		if (mapCampanas.get("Campana6")!=null) 
			params.put("Campana6", tituloCampana + " " +  mapCampanas.get("Campana6"));		
		
		return params;
	}
	

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCOMSeguimientoCalificacionCampanaAction.setViewAtributes' method");
		}
		
		this.mostrarReporteXLS = true;

		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ProcesoCOMCalculoCalificacionTramoService tramoService = (ProcesoCOMCalculoCalificacionTramoService) 
																getBean("spusicc.procesoCOMCalculoCalificacionTramoService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codigoPais = pais.getCodigo();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		String codigoProceso = this.mPantallaPrincipalBean.getCodigoProcesoBatch();
		String codigoSistema = this.mPantallaPrincipalBean.getCodigoSistema();
		
		setSiccCanalList(service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO()));
		setSiccMarcaList(service.getMarcas());
		setComTipoComisionistaList(tramoService.getTiposComisionistas(codigoPais));
		setComTramoList(tramoService.getTramos(codigoPais));
		
		ReporteCOMSeguimientoCalificacionCampanaForm f = (ReporteCOMSeguimientoCalificacionCampanaForm) this.formReporte;
		f.setBeforeExecuteReporte(true);		
		
		//Asignamos al codigo del periodo el valor por defecto
        Map criteria = new HashMap();
        criteria.put("codigoPais", codigoPais);
        criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
        criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
        List periodos = service.getPeriodosDefaultByPMC(criteria);
        
        if(periodos != null && periodos.size() > 0) {
            Base base = (Base)periodos.get(0);
            f.setAnioInicial(base.getCodigo().substring(0,4));
        }
		
	}

	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	public List getSiccCanalList() {
		return siccCanalList;
	}

	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	public List getComTipoComisionistaList() {
		return comTipoComisionistaList;
	}

	public void setComTipoComisionistaList(List comTipoComisionistaList) {
		this.comTipoComisionistaList = comTipoComisionistaList;
	}

	public List getComTramoList() {
		return comTramoList;
	}

	public void setComTramoList(List comTramoList) {
		this.comTramoList = comTramoList;
	}

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}
	
	
}
