package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteINCProyeccionGanadorasForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 *
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteINCProyeccionGanadorasAction extends BaseReporteAbstractAction implements Serializable{

	private static final long serialVersionUID = -1873004602537028217L;
	private String formatoReporte;
	private List siccConcursoList = new ArrayList() ;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReporteXLS = true;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		this.siccConcursoList = reporteService.getListaGenerico("getListaConcursosProyecionGanadoras",	criteriaOperacion);
		log.debug("Todo Ok: Redireccionando");	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		if(this.formatoReporte.equals("PDF"))
			 return "reporteINCProyeccionGanadorasPDF";
		 
		return "";
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(this.formatoReporte))
			return "reporteINCProyeccionGanadorasXLS";
		else
		   return "reporteMaestroHorizontal";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteINCProyeccionGanadorasForm form =  new ReporteINCProyeccionGanadorasForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteINCProyeccionGanadorasForm reporteINCForm = (ReporteINCProyeccionGanadorasForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		this.formatoReporte = reporteINCForm.getFormatoExportacion();
		Map criteria = params;

		String codigoConcurso = (String) params.get("codigoConcurso");
		
		String oidConcurso = reporteService.getOidConcursoByNumConc(codigoConcurso);
	
		params.put("oidConcurso", oidConcurso);
		criteria.put("numeroConcurso", codigoConcurso);
		Map concurso = (Map) reporteService.getDatosConcursosByNumeroConcurso(criteria);
		String descripcionConcurso = concurso != null && concurso.get("descripcion") != null ? concurso.get("descripcion").toString(): "";
        params.put("descripcionConcurso", descripcionConcurso);
        String tituloreporte= this.getResourceMessage("reporteINCProyeccionGanadorasForm.titleReport");
        String titulo = tituloreporte+ " "+reporteINCForm.getCodigoConcurso()+"\n"+(String)params.get("descripcionConcurso");
		params.put("titulo",titulo);
		reporteINCForm.setFormatoExportacion(formatoReporte);
		return params;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanReporteService()
	 */
	protected String devuelveBeanReporteService(){
		return "reportes.reporteINCProyeccionGanadorasService";
	}

	/**
	 * @return the formatoReporte
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte the formatoReporte to set
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	/**
	 * @return the siccConcursoList
	 */
	public List getSiccConcursoList() {
		return siccConcursoList;
	}

	/**
	 * @param siccConcursoList the siccConcursoList to set
	 */
	public void setSiccConcursoList(List siccConcursoList) {
		this.siccConcursoList = siccConcursoList;
	}
}