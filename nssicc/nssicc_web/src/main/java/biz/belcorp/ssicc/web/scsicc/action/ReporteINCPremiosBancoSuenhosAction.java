package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteINCPremiosBancoSuenhosForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;




@ManagedBean
@SessionScoped
public class ReporteINCPremiosBancoSuenhosAction extends BaseReporteAbstractAction {
	
	
	private static final long serialVersionUID = -6508920766384628076L;
	
	
	private String formatoReporte;
	private List siccRegionList;
	private LabelValue[] siccZonaList = {};
	private List siccConcursoList;
	
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteINCPremiosBancoSuenhosForm reporteForm = new ReporteINCPremiosBancoSuenhosForm();
		return reporteForm;
	}

	
	@Override
	protected void setViewAtributes() throws Exception {
		
		this.mostrarReporteXLS = true;
		
		ReporteINCPremiosBancoSuenhosForm f = (ReporteINCPremiosBancoSuenhosForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		String codpais = pais.getCodigo();
		f.setCodigoPais(codpais);
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", codpais);
		
				
		this.siccConcursoList=reporteService.getListaGenerico("getListaConcursosBancoSuenhos",	criteriaOperacion);
		//this.siccRegionList=reporteService.getListaGenerico("getRegionesByPaisActivasNoActivas",criteriaOperacion);
		this.siccRegionList=reporteService.getListaGenerico("getRegionesByPais", criteriaOperacion);	
		
			
		
		
		log.debug("Todo Ok: Redireccionando");
	}
		
	
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		if(formatoReporte.equals("PDF"))
			 return "reporteINCPremiosBancoSuenhosPDF";
		 
		return "";
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))
			return "reporteINCPremiosBancoSuenhosXLS";
		else
		   return "reporteMaestroHorizontal";
	}
	
	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reporteINCPremiosBancoSuenhosService";
	}
	
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		
		ReporteINCPremiosBancoSuenhosForm f = (ReporteINCPremiosBancoSuenhosForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		formatoReporte =f.getFormatoExportacion();
		Map criteria = params;

		String codigoRegion = (String) params.get("codigoRegion");
		String codigoZona = (String) params.get("codigoZona");
		String codigoConcurso = (String) params.get("codigoConcurso");
		String condicionZonas = "";
		String condicionRegion = "";
		String condicionConcurso = "";
		
		
		String oidConcurso = reporteService.getOidConcursoByNumConc(codigoConcurso);
		
		if ("Todos".equals(codigoRegion) || StringUtils.isEmpty(codigoRegion)) {
			codigoRegion = null;
			params.put("codigoRegion", codigoRegion);
		} else {
			condicionRegion = obtieneCondicion(f.getCodigoRegion(), "r.COD_REGI", "'");
		}

		if ("Todos".equals(codigoZona) || StringUtils.isEmpty(codigoZona)) {
			codigoZona = null;
			params.put("codigoZona", codigoZona);
		} else {
			condicionZonas = obtieneCondicion(f.getCodigoZona(),"z.COD_ZONA", "'");
		}
		
		String condicion = condicionZonas + condicionRegion;
		
			
		params.put("condicion", condicion);
		params.put("oidConcurso", oidConcurso);
		criteria.put("numeroConcurso", codigoConcurso);
		Map concurso = (Map) reporteService.getDatosConcursosByNumeroConcurso(criteria);
		String descripcionConcurso = concurso != null && concurso.get("descripcion") != null ? concurso.get("descripcion").toString(): "";
        params.put("descripcionConcurso", descripcionConcurso);
		params.put("titulo",getResourceMessage("reporteINCPremiosBancoSuenhosForm.titleReport")+ " "+f.getCodigoConcurso()+"\n"+(String)params.get("descripcionConcurso"));

		f.setFormatoExportacion(formatoReporte);
		return params;	
	}
	
	public void showZonasxRegion(ValueChangeEvent val) {
		log.debug(">>showZonasxRegion ");
		log.debug("val: " + val.getNewValue().toString());
		
		
		ReporteINCPremiosBancoSuenhosForm f = (ReporteINCPremiosBancoSuenhosForm) this.formReporte;
		String[] regiones = (String[]) val.getNewValue();
		if (!val.equals(null) && regiones.length > 0) {
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			this.setSiccZonaList(aSvc.getZonasMultipleByPaisMarcaCanalRegion(
					f.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT,Constants.CODIGO_CANAL_DEFAULT, regiones,
					Constants.FORMATO_TOTAL));			
			f.setCodigoZona(null);
		} else {
			this.siccZonaList = null;
			f.setCodigoZona(null);
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


	public List getSiccConcursoList() {
		return siccConcursoList;
	}


	public void setSiccConcursoList(List siccConcursoList) {
		this.siccConcursoList = siccConcursoList;
	}
	
	
}
