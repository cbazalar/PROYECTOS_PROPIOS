package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteINCSolicitudesPremiosRechazadasForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano
 *         Huamán</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteINCSolicitudesPremiosRechazadasAction extends
		BaseReporteAbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2738796949590860015L;
	private String formatoReporte;
	private List siccConcursosList;
	public List getSiccConcursosList() {
		return siccConcursosList;
	}

	public void setSiccConcursosList(List siccConcursosList) {
		this.siccConcursosList = siccConcursosList;
	}

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteINCSolicitudesPremiosRechazadasForm reporteForm = new ReporteINCSolicitudesPremiosRechazadasForm();
		return reporteForm;
	}

	protected String devuelveNombreReporte() throws Exception {

		if ("XLS".equals(formatoReporte))
			return "reporteINCSolicitudesPremiosRechazadasXLS";
		else
		   return "reporteMaestroHorizontal";
	}

	protected String devuelveNombreSubReporte() throws Exception {
	
		if(formatoReporte.equals("PDF") || formatoReporte.equals("VPDF"))
			 return "reporteINCSolicitudesPremiosRechazadasPDF";
		 
		return "reporteINCSolicitudesPremiosRechazadasPDF";
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'view' method");
		}
		this.mostrarReportePDF = true;
		this.mostrarReporteXLS = true;
		// servicios y formulario
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ReporteINCSolicitudesPremiosRechazadasForm f = (ReporteINCSolicitudesPremiosRechazadasForm) this.formReporte;	
		// parametros generales
		Pais pais = getmPantallaPrincipalBean().getCurrentCountry();
		Usuario usuario = getmPantallaPrincipalBean().getCurrentUser();
		// reset
		// Map para almacenar los parametros
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais",pais.getCodigo());
		// seteando
		siccConcursosList = 	reporteService.getListaGenerico("getListaConcursosPremiosRechazadas",	criteriaOperacion);
		log.debug("Todo Ok: Redireccionando");

	}

	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'reporteINCSolicitudesPremiosRechazadasService.prepareParameterMap' method");
		}

		
		ReporteINCSolicitudesPremiosRechazadasForm reporteINCForm = (ReporteINCSolicitudesPremiosRechazadasForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		formatoReporte = reporteINCForm.getFormatoExportacion();
		Map criteria = params;

		String codigoConcurso = (String) params.get("codigoConcurso");
		//String codigoConcurso = reporteINCForm.getCodigoConcurso();
		String condicionConcurso = "";
		String condicionPeriodo = "";
		String codPeriodo = StringUtils.EMPTY;
		
		//se obtiene el codigo de periodo
		codPeriodo = reporteINCForm.getCodigoPeriodo();
		//String oidConcurso = reporteService.getOidConcursoByNumConc(codigoConcurso);
	
		params.put("codPeriodo", codPeriodo);
		criteria.put("numeroConcurso", codigoConcurso);
		Map concurso = (Map) reporteService.getDatosConcursosByNumeroConcurso(criteria);
		String descripcionConcurso = concurso != null && concurso.get("descripcion") != null ? concurso.get("descripcion").toString(): "";
        params.put("descripcionConcurso", descripcionConcurso);
		params.put("titulo",getResourceMessage("reporteINCSolicitudesPremiosRechazadasForm.titleReport")+ " "+reporteINCForm.getCodigoConcurso()+"\n"+(String)params.get("descripcionConcurso"));
		reporteINCForm.setFormatoExportacion(formatoReporte);
		return params;

	}
	protected String devuelveBeanReporteService(){
		return "reportes.reporteINCSolicitudesPremiosRechazadasService";
	}


}