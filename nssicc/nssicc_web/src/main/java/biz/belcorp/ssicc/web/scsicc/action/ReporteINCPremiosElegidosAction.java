package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteINCPremiosElegidosForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;


/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteINCPremiosElegidosAction extends BaseReporteAbstractAction{

	private static final long serialVersionUID = 4817465786976388445L;
	private String tipoReporte;
	private String formatoReporte;
	private boolean flag;
	private List siccMarcaList;
	private List siccCanalList;
	private LabelValue[] siccRegionList;
	private LabelValue[] siccZonaList;
	private LabelValue[] concursoList;
	private List tipoReporteList;
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteINCPremiosElegidosForm form = new ReporteINCPremiosElegidosForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if(formatoReporte.equals("XLS")){
			if(!flag)
				return "reporteINCPremiosElegidos"+tipoReporte+"XLS";
			else
				return "reporteINCPremiosElegidos"+tipoReporte+"RegionZona"+"XLS";
		}
		
		return "reporteMaestroHorizontal";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		if(formatoReporte.equals("PDF") || formatoReporte.equals("VPDF")){
			if(!flag)
				return "reporteINCPremiosElegidos"+tipoReporte+"PDF";
			else
				return "reporteINCPremiosElegidos"+tipoReporte+"RegionZona"+"PDF";
		}
		return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug(">>ReporteINCPremiosElegidosAction - prepareParameterMap...");
		}
		ReporteINCPremiosElegidosForm reporteINCForm = (ReporteINCPremiosElegidosForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		formatoReporte = reporteINCForm.getFormatoExportacion();
		Map criteria = params;

		String codigoRegion = (String) params.get("codigoRegion");
		String codigoZona = (String) params.get("codigoZona");
		String codigoConcurso = (String) params.get("codigoConcurso");
		String condicionZonas = "";
		String condicionRegion = "";
		String condicionConcurso = "";
		
		if (codigoConcurso.equals("Todos") || StringUtils.isEmpty(codigoConcurso)) {
			params.put("codigoConcurso", null);
		}else{
			String idConcurso = reporteService.getOidConcursoByNumConc(codigoConcurso);
			condicionConcurso = "and pe.copa_oid_para_gral = '"+idConcurso+"' ";
		}
		if ("Todos".equals(codigoRegion) || StringUtils.isEmpty(codigoRegion)) {
			codigoRegion = null;
			params.put("codigoRegion", codigoRegion);
		} else {
			condicionRegion = obtieneCondicion(reporteINCForm.getCodigoRegion(), "r.COD_REGI", "'");
		}

		if ("Todos".equals(codigoZona) || StringUtils.isEmpty(codigoZona)) {
			codigoZona = null;
			params.put("codigoZona", codigoZona);
		} else {
			condicionZonas = obtieneCondicion(reporteINCForm.getCodigoZona(),"z.COD_ZONA", "'");
		}

		tipoReporte = reporteINCForm.getDetalleTipoReporte();
		
			
		ClassPathResource resource = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteINCPremiosElegidosPremiosReemplazo" + JASPER_EXTENSION);
		
		params.put("SUBREPORT_DIR1_PREM_REEMPLAZO", (JasperReport)JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource.getPath() )));
		
		
		String condicion = condicionConcurso + condicionZonas + condicionRegion;
		
		if((condicionZonas != null && StringUtils.isNotBlank(condicionZonas)) || (condicionRegion != null && StringUtils.isNotBlank(condicionRegion)))
			flag = true;
		else
			flag = false;
		
		
		params.put("condicion", condicion);
		//params.put("oidIdioma", reporteService.getOidString("getOidIdiomaByCodigoIdiomaIso", (Map)new HashMap().put("codigoIdiomaIso", getPais(request.getSession(true)).getCodigoIdiomaIso())));
		criteria.put("numeroConcurso", codigoConcurso);
		Map concurso = (Map) reporteService.getDatosConcursosByNumeroConcurso(criteria);
		String descripcionConcurso = concurso != null && concurso.get("descripcion") != null ? concurso.get("descripcion").toString(): "";
        params.put("descripcionConcurso", descripcionConcurso);
        
        //concatenacion del titulo.
        StringBuilder sb = new StringBuilder();
        sb.append(this.mPantallaPrincipalBean.getResourceMessage("reporteINCPremiosElegidosForm.titleReport"));
        sb.append(" ");
        sb.append(reporteINCForm.getCodigoConcurso().concat("-"));
        sb.append(descripcionConcurso);
        
        params.put("titulo", sb.toString());
		//params.put("titulo",getMessageReporte("", request)+ " "+reporteINCForm.getCodigoConcurso()+"-"+(String)params.get("descripcionConcurso"));
		//this.setVirtualizador(true);
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug(">>ReporteINCPremiosElegidosAction - setViewAtributes...");
		}
		
		this.mostrarReporteXLS = true;
		ReporteINCPremiosElegidosForm form = (ReporteINCPremiosElegidosForm) this.formReporte;
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");	
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		form.setCodigoPais(pais.getCodigo());
		form.setDescPais(pais.getDescripcion());
		
		
		setSiccMarcaList(service.getMarcas());
		setSiccCanalList(service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO()));
		String periodo = service.getPeriodoDefaultByPaisCanal(pais.getCodigo(),Constants.CODIGO_CANAL_DEFAULT);
		
		form.setCodigoPeriodo(periodo);
		formatoReporte = form.getFormatoExportacion();
		
		//Carga de Consursos
		setConcursoList(aSvc.getConcursosByPaisMarcaCanalDetallePremiosElec(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, "", ""));
		
		//Carga de tipoReporteList
		ArrayList<Base> tipoReporte = new ArrayList<Base>();
		Base[] baseTipos = new Base[2]; 
		baseTipos[0] = new Base();
		baseTipos[0].setCodigo(this.mPantallaPrincipalBean.getResourceMessage("select.consultora"));
		baseTipos[0].setDescripcion(this.mPantallaPrincipalBean.getResourceMessage("select.consultora"));
		baseTipos[1] = new Base();
		baseTipos[1].setCodigo(this.mPantallaPrincipalBean.getResourceMessage("select.premio"));
		baseTipos[1].setDescripcion(this.mPantallaPrincipalBean.getResourceMessage("select.premio"));
		for(int i=0; i<baseTipos.length;i++){
			tipoReporte.add(baseTipos[i]);
		}		
		setTipoReporteList(tipoReporte);
		setSiccRegionList(aSvc.getRegionesByPais(pais.getCodigo()));
		
	}
	
		
	/**
	 * Carga Combo Multiple de Regiones por Marca
	 * @param val
	 */
	public void loadRegionesMarca(ValueChangeEvent val){
		
		log.debug(">>loadRegionesMarca...");
		String valor = val.getNewValue().toString();
		
		ReporteINCPremiosElegidosForm form = (ReporteINCPremiosElegidosForm) this.formReporte;
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		this.setSiccRegionList(aSvc.getRegionesByPaisMarcaCanalDetalle(form.getCodigoPais(), valor, 
				form.getCodigoCanal(), Constants.FORMATO_TOTAL));
		form.setCodigoRegion(null);
		setSiccZonaList(null);
		form.setCodigoZona(null);
		
		this.setConcursoList(aSvc.getConcursosByPaisMarcaCanalDetallePremiosElec(form.getCodigoPais(), valor, 
				form.getCodigoCanal(), "", ""));
	}
	
	/**
	 * Carga Combo Multiple de Regiones por Marca
	 * @param val
	 */
	public void loadRegionesCanal(ValueChangeEvent val){
		
		log.debug(">>loadRegionesMarca...");
		String valor = val.getNewValue().toString();
		
		ReporteINCPremiosElegidosForm form = (ReporteINCPremiosElegidosForm) this.formReporte;
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		this.setSiccRegionList(aSvc.getRegionesByPaisMarcaCanalDetalle(form.getCodigoPais(), form.getCodigoMarca(), 
				valor, Constants.FORMATO_TOTAL));
		form.setCodigoRegion(null);
		setSiccZonaList(null);
		form.setCodigoZona(null);
		
		this.setConcursoList(aSvc.getConcursosByPaisMarcaCanalDetallePremiosElec(form.getCodigoPais(), form.getCodigoMarca(), 
				valor, "", ""));
	}
	
	/**
	 * Carga Combo Multiple de Zonas
	 * @param val
	 */
	public void loadZonas(ValueChangeEvent val) {

		String[] regiones = (String[]) val.getNewValue();

		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		ReporteINCPremiosElegidosForm form = (ReporteINCPremiosElegidosForm) this.formReporte;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		setSiccZonaList(aSvc.getZonasMultipleByPaisMarcaCanalRegion(pais.getCodigo(), form.getCodigoMarca(), form.getCodigoCanal(), regiones, Constants.FORMATO_TOTAL));
		form.setCodigoZona(null);
	}

	
	public String getTipoReporte() {
		return tipoReporte;
	}

	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
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

	public LabelValue[] getConcursoList() {
		return concursoList;
	}

	public void setConcursoList(LabelValue[] concursoList) {
		this.concursoList = concursoList;
	}

	public List getTipoReporteList() {
		return tipoReporteList;
	}

	public void setTipoReporteList(List tipoReporteList) {
		this.tipoReporteList = tipoReporteList;
	}
	
	
	
	
	
}