package biz.belcorp.ssicc.web.scsicc.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteINCProyeccionPremiosForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;


/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteINCProyeccionPremiosAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = -6225513016492715144L;
	private String formatoReporte;
	private String tipoReporte;
	private List siccConcursoList;
	private List siccSubGerenciaList;
	private LabelValue[] siccNivelesConcursoList;
	private LabelValue[] siccRegionList;
	private LabelValue[] siccZonaList;
	private List tipoReporteList;
	private List totalesList;
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteINCProyeccionPremiosForm form = new ReporteINCProyeccionPremiosForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte)){
			if(tipoReporte.equals("1"))
				return "INCProyeccionPremiosXLS";
			else
				return "ReporteINCProyeccionPremiosConsultorasXLS";
		}
		else
			return "reporteMaestroHorizontal";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		if(tipoReporte.equals("1"))
			return "ReporteINCProyeccionPremios";
		else
			return "ReporteINCProyeccionPremiosConsultorasPDF";
	}
	
	

	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reporteINCProyeccionPremiosService";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteINCPremiosEntregadosAction.prepareParameterMap' method");
		}
		
		ReporteINCProyeccionPremiosForm form = (ReporteINCProyeccionPremiosForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		formatoReporte = form.getFormatoExportacion();
		tipoReporte = form.getTipoReporte().trim();
		
		//-- Validar el Reporte a ejecutar --------------------------
		
		if(tipoReporte.equals("1")){
			
			
				ClassPathResource resource = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteINCProyeccionPremiosZonas" + JASPER_EXTENSION);
				ClassPathResource resource1 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteINCProyeccionPremiosRegion" + JASPER_EXTENSION);
				ClassPathResource resource2 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteINCProyeccionPremiosSubGerencia" + JASPER_EXTENSION);
				ClassPathResource resource3 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteINCProyeccionPremios" + JASPER_EXTENSION);
				
				if ("XLS".equals(formatoReporte)) {
					resource = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteINCProyeccionPremiosZonasXLS" + JASPER_EXTENSION);
					resource1 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteINCProyeccionPremiosRegionXLS" + JASPER_EXTENSION);
					resource2 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteINCProyeccionPremiosSubGerenciaXLS" + JASPER_EXTENSION);
					resource3 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteINCProyeccionPremiosXLS" + JASPER_EXTENSION);
				}
				
				params.put("SUBREPORT_DIR1", JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource.getPath() )));
				params.put("SUBREPORT_DIR2", JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource1.getPath() )));
				params.put("SUBREPORT_DIR3", JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource2.getPath() )));
				params.put("SUBREPORT_DIR4", JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource3.getPath() )));
			

		}

		/* Variables utilizadas para mostrar o no un subreprote */
		String muestraZonas = "0";
		String muestraRegion = "0";
		String muestraSubGerencia = "0";
		String muestraConcurso = "0";
		
		/*
		 * Las variables se inicializan con la seleccion del usuario la misma
		 * que viene en totales del formulario
		 */
		for (int i = 0; i < form.getTotales().length; i++) {
			if (StringUtils.equals(form.getTotales()[i], "Z"))
				muestraZonas = "1";
			if (StringUtils.equals(form.getTotales()[i], "C"))
				muestraConcurso = "1";
			if (StringUtils.equals(form.getTotales()[i], "R"))
				muestraRegion = "1";
			if (StringUtils.equals(form.getTotales()[i], "S"))
				muestraSubGerencia = "1";
		}

		//-- Parametro Reportes -------------------------------------
		params.put("muestraZonas", muestraZonas);
		params.put("muestraRegion", muestraRegion);
		params.put("muestraSubGerencia", muestraSubGerencia);
		params.put("muestraConcurso", muestraConcurso);
		params.put("nivelConcursoList", (form.getNivelConcursoList() == null) ? new ArrayList(): Arrays.asList(form.getNivelConcursoList()));
		params.put("subGerenciaList", (form.getSubgerenciaList() == null) ? new ArrayList() : Arrays.asList(form.getSubgerenciaList()));
		params.put("zonaList", (form.getZonaList() == null) ? new ArrayList() : Arrays.asList(form.getZonaList()));
		params.put("regionList", (form.getRegionList() == null) ? new ArrayList() : Arrays.asList(form.getRegionList()));
		params.put("NroReporte", "");
		
		Map criteria = new HashMap();
		criteria.put("numeroConcurso", form.getNumeroConcurso());
		
		Map concurso = reporteService.getDatosConcursosByNumeroConcurso(criteria);
		String descripcionConcurso = concurso.get("descripcion") != null ? concurso.get("descripcion").toString(): "";
		String oidParametroGeneral = concurso.get("oidParametroGeneral") != null ? concurso.get("oidParametroGeneral").toString(): "";
				
		params.put("oidParametroGeneral", new Integer(oidParametroGeneral));	
		
		String campanhaDespacho="";
		try{
			campanhaDespacho = reporteService.getOidString("getPeriodoPremiacionByConcurso", params);
		}
		catch (Exception e) {
			campanhaDespacho="";
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(this.mPantallaPrincipalBean.getReportResourceMessage("reporteINCProyeccionPremiosForm.titulo"));
		sb.append("-");
		sb.append(form.getNumeroConcurso());
		sb.append(" ");
		sb.append(descripcionConcurso);
		sb.append(this.mPantallaPrincipalBean.getReportResourceMessage("reporteINCProyeccionPremiosForm.tituloCampana"));
		sb.append("-");
		sb.append(campanhaDespacho);
		
		String titu = sb.toString();
		form.setTitulo(titu);
		params.put("titulo", form.getTitulo());

		String condicion = "";
		// if (StringUtils.equals(recalculo, "NO")) {
		if(tipoReporte.equals("1")){	
			String condicionZonas = obtieneCondicion(form.getZonaList(),"x.COD_ZONA", "'");
			String condicionRegion = obtieneCondicion(form.getRegionList(), "x.COD_REGI", "'");
			String condicionNiveles = obtieneCondicion(form.getNivelConcursoList(), "x.NUM_NIVE", "'");
			condicion = condicionZonas + condicionRegion + condicionNiveles;
		}else{
			String condicionZonas = obtieneCondicion(form.getZonaList(),"cl.COD_ZONA", "'");
			String condicionRegion = obtieneCondicion(form.getRegionList(), "cl.COD_REGI", "'");
			String condicionNiveles = obtieneCondicion(form.getNivelConcursoList(), "cl.NUM_NIVE", "'");
			condicion = condicionZonas + condicionRegion + condicionNiveles;
		}
		
		// }
		params.put("condicion", condicion);
	
		form.setBeforeExecuteReporte(true);
			
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("ReporteINCProyeccionPremiosAction - setViewAtributes()");
		
		this.mostrarReporteXLS = true;
		this.mostrarReporteXLSX = true;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ReporteINCProyeccionPremiosForm form = (ReporteINCProyeccionPremiosForm) this.formReporte;
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		criteriaOperacion.put("indicadorActivo", null);
		criteriaOperacion.put("indicadorBorrado", "0");
		criteriaOperacion.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteriaOperacion.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		
		
		setSiccConcursoList(reporteService.getListaGenerico("getConcursosByPaisMarcaCanalDetalle",	criteriaOperacion));
		Base baseCL = (Base) this.siccConcursoList.get(0);
		form.setNumeroConcurso(baseCL.getCodigo());
		setSiccNivelesConcursoList(aSvc.getNivelesByConcurso(form.getNumeroConcurso(), ""));
		
		setSiccSubGerenciaList(reporteService.getListaGenerico("getSubGerenciasByPaisMarcaCanal", criteriaOperacion));
		
		//Seteo de tipo de reportes
		ArrayList<Base> reporteTipo = new ArrayList<Base>();
		Base[] baseTipo = new Base[2];		
		String rt1 = this.mPantallaPrincipalBean.getResourceMessage("reporteINCProyeccionPremiosForm.nivelPremio");
		String rt2 = this.mPantallaPrincipalBean.getResourceMessage("reporteINCProyeccionPremiosForm.nivelConsultora");
		String[] elementos = { rt1, rt2 };
		for (int i = 0; i < 2; i++) {
			baseTipo[i] = new Base();
			baseTipo[i].setCodigo("" + (i + 1));
			baseTipo[i].setDescripcion(elementos[i]);
			reporteTipo.add(baseTipo[i]);
		}
		tipoReporteList = reporteTipo;		
		
		//Seteo de lista Totales
		ArrayList<Base> totales = new ArrayList<Base>();
		Base[] baseTotal = new Base[3];
		baseTotal[0] = new Base();
		baseTotal[0].setCodigo("R");
		baseTotal[0].setDescripcion(this.mPantallaPrincipalBean.getResourceMessage("select.porRegion"));
		baseTotal[1] = new Base();
		baseTotal[1].setCodigo("Z");
		baseTotal[1].setDescripcion(this.mPantallaPrincipalBean.getResourceMessage("select.porZona"));
		baseTotal[2] = new Base();
		baseTotal[2].setCodigo("C");
		baseTotal[2].setDescripcion(this.mPantallaPrincipalBean.getResourceMessage("select.porConcurso"));
		for(int j =0; j < 3; j++){
			totales.add(baseTotal[j]);
		}
		totalesList = totales;
	
		
		/* Inicialiamos las listas */
		
		setSiccRegionList(aSvc.getRegionesByPaisMarcaCanal(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT));

		form.setCodigoPais(pais.getCodigo());
		form.setDescPais(pais.getDescripcion());
		
		
	}
	
	
	/**
	 * Obtiene Lista de Niveles x Concurso
	 * @param val
	 */
	public void showNivelesxConcurso(ValueChangeEvent val){
		log.debug(">>showNivelesxConcurso ");
		log.debug("val: "+val.getNewValue().toString());
		String codConcurso = val.getNewValue().toString();
		
		ReporteINCProyeccionPremiosForm form = (ReporteINCProyeccionPremiosForm) this.formReporte;
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		setSiccNivelesConcursoList(aSvc.getNivelesByConcurso(codConcurso, ""));	
		form.setNivelConcursoList(null);
	}
	
	/**
	 * Obtiene Lista de Zonas x Region
	 * @param val
	 */
	public void showZonasxRegion(ValueChangeEvent val){
		log.debug(">>showZonasxRegion ");
		log.debug("val: "+val.getNewValue().toString());
		
		String[] regiones = (String [])val.getNewValue();
		ReporteINCProyeccionPremiosForm form = (ReporteINCProyeccionPremiosForm) this.formReporte;
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		this.setSiccZonaList(aSvc.getZonasMultipleByPaisMarcaCanalRegion(form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, 
				Constants.CODIGO_CANAL_DEFAULT, regiones, Constants.FORMATO_TOTAL));	
		form.setZonaList(null);
		
	}

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	public String getTipoReporte() {
		return tipoReporte;
	}

	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	public List getSiccConcursoList() {
		return siccConcursoList;
	}

	public void setSiccConcursoList(List siccConcursoList) {
		this.siccConcursoList = siccConcursoList;
	}

	public List getSiccSubGerenciaList() {
		return siccSubGerenciaList;
	}

	public void setSiccSubGerenciaList(List siccSubGerenciaList) {
		this.siccSubGerenciaList = siccSubGerenciaList;
	}

	public LabelValue[] getSiccNivelesConcursoList() {
		return siccNivelesConcursoList;
	}

	public void setSiccNivelesConcursoList(LabelValue[] siccNivelesConcursoList) {
		this.siccNivelesConcursoList = siccNivelesConcursoList;
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

	public List getTipoReporteList() {
		return tipoReporteList;
	}

	public void setTipoReporteList(List tipoReporteList) {
		this.tipoReporteList = tipoReporteList;
	}

	public List getTotalesList() {
		return totalesList;
	}

	public void setTotalesList(List totalesList) {
		this.totalesList = totalesList;
	}
	
		
}