package biz.belcorp.ssicc.web.scsicc.action;


import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.core.io.ClassPathResource;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCOBReporteRecuperacion31DiasForm;


/**
 * 
 * @author <a href="">Sergio Apaza</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteCOBReporteRecuperacion31DiasAction extends BaseReporteAbstractAction implements Serializable {
			
	private static final long serialVersionUID = 7496906172990857522L;
	
	private String tipoVista;	
    private List siccSociedadList;
    private List siccRegionList;
    private LabelValue[] siccZonaList = {};
    private String codigoIdiomaISO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOBReporteRecuperacion31DiasForm form = new ReporteCOBReporteRecuperacion31DiasForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteCOBReporteRecuperacion31DiasForm form = (ReporteCOBReporteRecuperacion31DiasForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(form.getFormatoExportacion()))
			return "reporteCOBReporteRecuperacion31DiasXLS";
		else
		   return "reporteMaestroHorizontal";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteCOBReporteRecuperacion31DiasForm form = (ReporteCOBReporteRecuperacion31DiasForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		if("PDF".equals(form.getFormatoExportacion()))
			 return "reporteCOBReporteRecuperacion31DiasPDF";
		 
		return null;
	}
	
	/**
	 * Carga zonas dependiendo las regiones seleccionadas
	 * @param val
	 */
	public void showZonasxRegion(ValueChangeEvent val){
		try {
			log.debug(">>showZonasxRegion ");
			ReporteCOBReporteRecuperacion31DiasForm form = (ReporteCOBReporteRecuperacion31DiasForm) this.formReporte;
			String[] regiones = (String []) val.getNewValue();		
			int contador = 0;
			this.siccZonaList = new LabelValue[]{};
			
			if(!ArrayUtils.isEmpty(regiones)){
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				LabelValue[] listaAux = aSvc.getZonasMultipleByPaisMarcaCanalRegion(form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, 
						Constants.CODIGO_CANAL_DEFAULT, regiones, Constants.FORMATO_TOTAL);
				this.siccZonaList = new LabelValue[listaAux.length-1];
				for (int j = 0; j < listaAux.length; j++) {
					LabelValue labelValue = listaAux[j];
					if(!StringUtils.equalsIgnoreCase(labelValue.getLabel(), "Todos")){
						this.siccZonaList[contador] = listaAux[j];
//						this.siccZonaList[contador].setValue(labelValue.getValue());
//						this.siccZonaList[contador].setLabel(labelValue.getLabel());
						contador++;
					}
				
				}
				
			}else{
				setSiccZonaList(null);
			}
			
			form.setCodigoZona(null);
		} catch (Exception e) {
			this.addError("Error : " , this.obtieneMensajeErrorException(e));
		}
		
	}
	
	/* Se sobreescribe este metodo para forzar a pasar el locale de MX
	 * para que puedan salir correctamente el formato a numero (###,###,##0.00)
	 * (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#configReporteParams(biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams)
	 */
	@Override
	protected void configReporteParams(ReporteParams reporteParams) {
		Map params = reporteParams.getQueryParams();
		Map parameterMap = (Map)params.get("parameterMap");
		String idiomaReporte = (String)parameterMap.get("idiomaReporte");
		String paisReporte = (String)parameterMap.get("paisReporte");
		
		Locale locale = new Locale(idiomaReporte,paisReporte);
		params.put(JRParameter.REPORT_LOCALE, locale);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteCOBReporteRecuperacion31DiasForm reporteCOBForm = (ReporteCOBReporteRecuperacion31DiasForm) this.formReporte;
	
		ClassPathResource resource = new ClassPathResource(
				Constants.JASPER_DIRECTORIO + "subReporteCOBReporteRecuperacionZona" + JASPER_EXTENSION);
		ClassPathResource resource2 = new ClassPathResource(
				Constants.JASPER_DIRECTORIO + "subReporteCOBReporteRecuperacionPorcentajeZona" + JASPER_EXTENSION);
		ClassPathResource resource3 = new ClassPathResource(
				Constants.JASPER_DIRECTORIO + "subReporteCOBReporteRecuperacionRegion" + JASPER_EXTENSION);
		
		String condicionRegion = "";
		String condicionZona = "";
		
		condicionRegion = this.obtieneCondicion(reporteCOBForm.getCodigoRegion(), "COD_REGI", "'");
		condicionZona = this.obtieneCondicion(reporteCOBForm.getCodigoZona(), "COD_ZONA", "'");
		 
		String titulo = getReportResourceMessage("reporteCOBReporteRecuperacion31DiasForm.titulo");
		params.put("titulo",titulo);			
		params.put("codigoPais",reporteCOBForm.getCodigoPais());
		params.put("codigoPeriodo",reporteCOBForm.getCodigoPeriodo());
		params.put("codigoSociedad",reporteCOBForm.getCodigoSociedad());
		params.put("condicionRegion", condicionRegion!=null?condicionRegion:"");
		params.put("condicionZona", condicionZona!=null?condicionZona:"");
		params.put("idiomaReporte",reporteCOBForm.getIdiomaReporte());
		params.put("paisReporte",reporteCOBForm.getPaisReporte());
		
		
		params.put("SUBREPORT_DIR1", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource.getPath() )));
		params.put("SUBREPORT_DIR2", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource2.getPath() )));
		params.put("SUBREPORT_DIR3", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource3.getPath() )));
		

		return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {

		if(log.isDebugEnabled()){
			log.debug("Entro a ReporteCOBReporteRecuperacion31DiasAction - setViewAttributes");	
		}
		
		ReporteCOBReporteRecuperacion31DiasForm reporteCOBForm = (ReporteCOBReporteRecuperacion31DiasForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		GenericoService genericoService = (GenericoService) getBean("genericoService");
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		this.codigoIdiomaISO = usuario.getIdioma().getCodigoISO();
		reporteCOBForm.setCodigoPais(pais.getCodigo());
			
	
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		

		this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);
		this.siccSociedadList = service.getSociedadesByCodigoPais(pais.getCodigo());

		ParametroPais parametroPais = new ParametroPais();
		parametroPais.setCodigoPais(pais.getCodigo());
		parametroPais.setCodigoSistema("GEN");
		parametroPais.setNombreParametro("reporteLocaleIdiomaNumero");
		parametroPais.setIndicadorActivo(Constants.ESTADO_ACTIVO);
		List lstParametros = genericoService.getParametrosPais(parametroPais);
		
		ParametroPais parametro = null;
		String idiomaReporte = pais.getCodigoIdiomaIso();
		if(CollectionUtils.size(lstParametros)==1){
			parametro = (ParametroPais) lstParametros.get(0);
			idiomaReporte = parametro.getValorParametro();
		}
		reporteCOBForm.setIdiomaReporte(idiomaReporte);
		
		parametroPais = new ParametroPais();
		parametroPais.setCodigoPais(pais.getCodigo());
		parametroPais.setCodigoSistema("GEN");
		parametroPais.setNombreParametro("reporteLocatePaisNumero");
		parametroPais.setIndicadorActivo(Constants.ESTADO_ACTIVO);
		lstParametros = genericoService.getParametrosPais(parametroPais);
		
		String paisReporte = pais.getCodigoPaisIso();
		if(CollectionUtils.size(lstParametros)==1){
			parametro = (ParametroPais) lstParametros.get(0);
			paisReporte = parametro.getValorParametro();
		}
		reporteCOBForm.setPaisReporte(paisReporte);

		log.info("Salio a ReporteCOBReporteRecuperacion31DiasAction - setViewAttributes");
	
	}

	/**
	 * @return the tipoVista
	 */
	public String getTipoVista() {
		return tipoVista;
	}

	/**
	 * @param tipoVista the tipoVista to set
	 */
	public void setTipoVista(String tipoVista) {
		this.tipoVista = tipoVista;
	}

	/**
	 * @return the siccSociedadList
	 */
	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	/**
	 * @param siccSociedadList the siccSociedadList to set
	 */
	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}

	/**
	 * @return the siccRegionList
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList the siccRegionList to set
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccZonaList
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the codigoIdiomaISO
	 */
	public String getCodigoIdiomaISO() {
		return codigoIdiomaISO;
	}

	/**
	 * @param codigoIdiomaISO the codigoIdiomaISO to set
	 */
	public void setCodigoIdiomaISO(String codigoIdiomaISO) {
		this.codigoIdiomaISO = codigoIdiomaISO;
	}

		
}