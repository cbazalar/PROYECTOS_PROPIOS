package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.ParameterContructorService;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteINCPuntObtenidosPuntFaltantesForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCHabilitacionConcursoCargaPuntajeService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;



/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteINCPuntObtenidosPuntFaltantesAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 5079104276299059652L;
	private String formatoReporte;
	private String tipoReporte;
	private List siccMarcaList;
	private List incestadosList;
	private LabelValue[] siccConcursoList;
	private List siccCanalList;
	
	private LabelValue[] siccRegionList;
	private LabelValue[] siccZonaList;
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteINCPuntObtenidosPuntFaltantesForm form = new ReporteINCPuntObtenidosPuntFaltantesForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))
			if(tipoReporte.equals("1"))
				return "reporteINCPuntObtenidosPuntFaltantesXLS";
			else
				return "reporteINCPuntObtenidosPuntFaltantesExigidosXLS";
		else
			return "reporteMaestroHorizontal";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		if (("PDF".equals(formatoReporte))||("VPDF".equals(formatoReporte)))
			if(tipoReporte.equals("1"))
				return "reporteINCPuntObtenidosPuntFaltantes";
			else
				return "reporteINCPuntObtenidosPuntFaltantesExigidos";
		return "";
	}
	
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteINCPuntObtenidosPuntFaltantesAction.prepareParameterMap' method");
		}
		
		ReporteINCPuntObtenidosPuntFaltantesForm form = (ReporteINCPuntObtenidosPuntFaltantesForm) this.formReporte;
		formatoReporte = form.getFormatoExportacion();
		ReporteService reporteService = (ReporteService)getBean("scsicc.reporteService");
		MantenimientoINCHabilitacionConcursoCargaPuntajeService service = (MantenimientoINCHabilitacionConcursoCargaPuntajeService)getBean("spusicc.mantenimientoINCHabilitacionConcursoCargaPuntajeService");
		
		String oidConcurso = reporteService.getOidConcursoByNumConc(form.getCodigoConcurso());
		params.put("oidConcurso", oidConcurso);
		int indProdExig = service.getIndicadorProdExig(Integer.valueOf(oidConcurso));
		
		if(indProdExig == 0)
			this.tipoReporte = "1";
		else
			this.tipoReporte = "2";
		
		String codigoRegion = (String) params.get("codigoRegion");
		String codigoZona = (String) params.get("codigoZona");
		String codigoEstado = (String) params.get("codigoEstado");
		String condicionZonas = "";
		String condicionRegion = "";
		String condicionEstado = "";
		
		if ("Todos".equals(codigoRegion) || StringUtils.isEmpty(codigoRegion) ) {
			codigoRegion = null;
			params.put("codigoRegion", codigoRegion);
		} else 
			condicionRegion = obtieneCondicion(form.getCodigoRegion(), "COD_REGI", "'");
		

		if ("Todos".equals(codigoZona) || StringUtils.isEmpty(codigoZona)) {
			codigoZona = null;
			params.put("codigoZona", codigoZona);
		} else 
			condicionZonas = obtieneCondicion(form.getCodigoZona(),"COD_ZONA", "'");
		
		if ("Todos".equals(codigoEstado) || StringUtils.isEmpty(codigoEstado)){
			ArrayList listaCodigosEstados = new ArrayList(incestadosList.size());
			for(int i=0;i<incestadosList.size();i++){
				listaCodigosEstados.add(i,((Base)incestadosList.get(i)).getCodigo());
			}
			String[] arrayEstados=new String[listaCodigosEstados.size()];  
			listaCodigosEstados.toArray(arrayEstados);
			condicionEstado = obtieneCondicion(arrayEstados,"mcd.esta_oid_esta_clie", "'");
			
		} else {
			condicionEstado= obtieneCondicion(form.getCodigoEstado(),"mcd.esta_oid_esta_clie", "'");
		}

		String condicion = condicionZonas + condicionRegion;
		
		params.put("condicion", condicion);
		params.put("condicionEstado", condicionEstado);
		
		
		if(indProdExig == 0){
			form.setSubReporteDir1("subReporteINCPuntObtenidosPuntFaltantes");
			ClassPathResource resource = new ClassPathResource(((ParameterContructorService)this.getBeanService("reportes.parameterContructorService")).getJasperDirectorio() + form.getSubReporteDir1() + JASPER_EXTENSION);
			
			params.put("SUBREPORT_DIR1", JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource.getPath() )));
		}else{
			form.setSubReporteDir1("subReporteINCPuntObtenidosPuntFaltantesExigidos");
			ClassPathResource resource = new ClassPathResource(((ParameterContructorService)this.getBeanService("reportes.parameterContructorService")).getJasperDirectorio() + form.getSubReporteDir2() + JASPER_EXTENSION);
			params.put("SUBREPORT_DIR2", JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource.getPath() )));
		}
		form.setTitulo(this.getmPantallaPrincipalBean().getResourceMessage("reporteINCPuntObtenidosPuntFaltantesForm.titulo"));
		
		params.put("numeroConcurso", form.getCodigoConcurso());		
		params.put("NroReporte", " ");
		params.put("titulo",form.getTitulo());
		params.put("tipoReporte", this.tipoReporte);
			
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		
		log.debug("ReporteINCPuntObtenidosPuntFaltantesAction - setViewAtributes");
		
		this.mostrarReporteXLS = true;
		this.mostrarReporteOCSV = true;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ReporteINCPuntObtenidosPuntFaltantesForm form = (ReporteINCPuntObtenidosPuntFaltantesForm) this.formReporte;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		form.setCodigoPais(pais.getCodigo());
		form.setDescPais(pais.getDescripcion());
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		
		this.setSiccConcursoList(aSvc.getConcursosByPaisMarcaCanal(form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, ""));
		setSiccMarcaList(service.getMarcas());
		setSiccCanalList(service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO()));
		String periodo = service.getPeriodoDefaultByPaisCanal(pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT);
		
		form.setCodigoPeriodo(periodo);
		
		this.setSiccRegionList(aSvc.getRegionesByPaisMarcaCanal(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT));
		this.setIncestadosList(service.getListaEstadosIncentivos());
	}
	
	/**
	 * Carga Combo Multiple de Regiones por Marca
	 * @param val
	 */
	public void loadRegionesMarca(ValueChangeEvent val){
		
		log.debug(">>loadRegionesMarca...");
		String valor = val.getNewValue().toString();
		
		ReporteINCPuntObtenidosPuntFaltantesForm form = (ReporteINCPuntObtenidosPuntFaltantesForm) this.formReporte;
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		this.setSiccRegionList(aSvc.getRegionesByPaisMarcaCanalDetalle(form.getCodigoPais(), valor, 
				form.getCodigoCanal(), Constants.FORMATO_TOTAL));
		form.setCodigoRegion(null);
		setSiccZonaList(null);
		form.setCodigoZona(null);
		
		this.setSiccConcursoList(aSvc.getConcursosByPaisMarcaCanal(form.getCodigoPais(), valor, form.getCodigoCanal(), ""));
	}
	
	/**
	 * Carga Combo Multiple de Regiones por Marca
	 * @param val
	 */
	public void loadRegionesCanal(ValueChangeEvent val){
		
		log.debug(">>loadRegionesMarca...");
		String valor = val.getNewValue().toString();
		
		ReporteINCPuntObtenidosPuntFaltantesForm form = (ReporteINCPuntObtenidosPuntFaltantesForm) this.formReporte;
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		this.setSiccRegionList(aSvc.getRegionesByPaisMarcaCanalDetalle(form.getCodigoPais(), form.getCodigoMarca(), 
				valor, Constants.FORMATO_TOTAL));
		form.setCodigoRegion(null);
		setSiccZonaList(null);
		form.setCodigoZona(null);
		
		this.setSiccConcursoList(aSvc.getConcursosByPaisMarcaCanal(form.getCodigoPais(), form.getCodigoMarca(), valor, ""));
	}
	
	/**
	 * Obtiene lista de Zonas por Region
	 * @param val
	 */
	public void showZonasxRegion(ValueChangeEvent val){
		log.debug(">>showZonasxRegion ");
		log.debug("val: "+val.getNewValue().toString());
		
		String[] regiones = (String []) val.getNewValue();
		ReporteINCPuntObtenidosPuntFaltantesForm form = (ReporteINCPuntObtenidosPuntFaltantesForm) this.formReporte;
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		setSiccZonaList(aSvc.getZonasMultipleByPaisMarcaCanalRegion(form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, 
				Constants.CODIGO_CANAL_DEFAULT, regiones, Constants.FORMATO_TOTAL));	
		form.setCodigoZona(null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanReporteService()
	 */
	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reporteINCPuntObtenidosPuntFaltantesService";
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

	public LabelValue[] getSiccConcursoList() {
		return siccConcursoList;
	}

	public void setSiccConcursoList(LabelValue[] siccConcursoList) {
		this.siccConcursoList = siccConcursoList;
	}

	public List getIncestadosList() {
		return incestadosList;
	}

	public void setIncestadosList(List incestadosList) {
		this.incestadosList = incestadosList;
	}
}