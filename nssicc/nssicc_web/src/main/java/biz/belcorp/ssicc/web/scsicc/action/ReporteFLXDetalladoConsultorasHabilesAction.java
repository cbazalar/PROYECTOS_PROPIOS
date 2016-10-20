package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import net.sf.jasperreports.engine.JRParameter;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteFLXDetalladoConsultorasHabilesForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 *
 */
@ManagedBean
@SessionScoped
public class ReporteFLXDetalladoConsultorasHabilesAction extends
		BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = 6467992741175535675L;
	private String formatoReporte;
	private String codigoIdiomaIso;
	private	List siccRegionList;
	private LabelValue[] siccZonaList = {};
	private LabelValue[] siccSeccionList = {};
	

	


	public String getCodigoIdiomaIso() {
		return codigoIdiomaIso;
	}

	public void setCodigoIdiomaIso(String codigoIdiomaIso) {
		this.codigoIdiomaIso = codigoIdiomaIso;
	}

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	public LabelValue[] getSiccSeccionList() {
		return siccSeccionList;
	}

	public void setSiccSeccionList(LabelValue[] siccSeccionList) {
		this.siccSeccionList = siccSeccionList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		this.mostrarReportePDF = true;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteria = new HashMap();
		criteria.put("codigoPais", mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		siccRegionList=reporteService.getListaGenerico("getRegionesByPais", criteria);
		
		ReporteFLXDetalladoConsultorasHabilesForm f1 = (ReporteFLXDetalladoConsultorasHabilesForm) this.formReporte;
		f1.getIdioma().setCodigoISO( mPantallaPrincipalBean.getCurrentIdioma().getCodigoISO() );
		f1.setPais(pais);
		f1.setCodigoPais(pais.getCodigo());
		GenericoService genericoService = (GenericoService) getBean("genericoService");
		
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
		f1.setIdiomaReporte(idiomaReporte);
		
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
		f1.setPaisReporte(paisReporte);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));
		f1.setCodigoCampana(periodo);

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteFLXDetalladoConsultorasHabilesForm f = (ReporteFLXDetalladoConsultorasHabilesForm) this.formReporte;
		formatoReporte = f.getFormatoExportacion();
		if ("PDF".equals(formatoReporte))
			return "reporteMaestroVertical";
		else{
			return "reporteFLXDetalladoConsultorasHabilesXLS";
		}
			
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	protected Map prepareParameterMap(Map params) throws Exception {

		ReporteFLXDetalladoConsultorasHabilesForm f = (ReporteFLXDetalladoConsultorasHabilesForm) this.formReporte;
		formatoReporte = f.getFormatoExportacion();
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteria =  new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoSistema", Constants.FLX_CODIGO_SISTEMA);
		criteria.put("codigoParam", Constants.FLX_PARAMETRO_INDICADOR_CLIENTE_CEDULA);
		params.put("indClienteCedula", reporteService.getIndicadorClienteCedula(criteria));
		
		params.put("idiomaReporte", f.getIdiomaReporte());
		params.put("paisReporte", f.getPaisReporte());
		
		//this.setVirtualizador(true);
		
		String condicionRegion = this.obtieneCondicion(f.getRegionList(), "ZR.COD_REGI", "'");
		params.put("condicionRegion", condicionRegion);
		log.debug("condicionRegion"+condicionRegion.toString());
		
		String condicionZona = this.obtieneCondicion(f.getZonaList(), "ZZ.COD_ZONA", "'");
		params.put("condicionZona", condicionZona);
		log.debug("condicionZona"+condicionZona.toString());
		
		String condicionSeccion = this.obtieneCondicion(f.getSeccionList(), "ZS.COD_SECC", "'");
		params.put("condicionSeccion", condicionSeccion);
		log.debug("condicionSeccion"+condicionSeccion.toString());
		
		params.put("NroReporte", "");				
		
		String titulo = getResourceMessage("reporteFLXDetalladoConsultorasHabiles.titulo");
		params.put("titulo", titulo );
			

		return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		// TODO Auto-generated method stub
		ReporteFLXDetalladoConsultorasHabilesForm reporteForm = new ReporteFLXDetalladoConsultorasHabilesForm();
		return reporteForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteFLXDetalladoConsultorasHabilesForm f = (ReporteFLXDetalladoConsultorasHabilesForm) this.formReporte;
		formatoReporte = f.getFormatoExportacion();
		if ("PDF".equals(formatoReporte))
			return "reporteFLXDetalladoConsultorasHabilesPDF";
		else
			return "";
		
	}

	/**
	 * Obtener Lista de Zonas
	 * 
	 * @param val
	 */
	public void showZonasxRegion(ValueChangeEvent val) {	
		ReporteFLXDetalladoConsultorasHabilesForm form = (ReporteFLXDetalladoConsultorasHabilesForm) this.formReporte;
		String[] regiones = (String[]) val.getNewValue();
		if (!val.equals(null) && regiones.length > 0) {
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			siccZonaList=aSvc.getZonasMultipleByPaisMarcaCanalRegion(form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT,
					Constants.CODIGO_CANAL_DEFAULT, regiones,Constants.FORMATO_TOTAL);
			form.setZonaList(null);
			form.setSeccionList(null);
			this.siccSeccionList=null;
		} else {
			this.siccZonaList = null;
			this.siccSeccionList=null;
			form.setZonaList(null);
			form.setSeccionList(null);
		}
	}
	public void showSeccionxZona(ValueChangeEvent val) {	
		ReporteFLXDetalladoConsultorasHabilesForm form = (ReporteFLXDetalladoConsultorasHabilesForm) this.formReporte;
		String[] zonas = (String[]) val.getNewValue();
		if (!val.equals(null) && zonas.length > 0) {
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			this.siccSeccionList=aSvc.getSeccionMultipleByPaisMarcaCanalRegionZona(form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT,
					Constants.CODIGO_CANAL_DEFAULT, form.getRegionList(),zonas,Constants.FORMATO_TOTAL);		
			form.setSeccionList(null);
		} else {
			siccSeccionList = null;
			form.setSeccionList(null);
		}
	}


	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @param siccZonaList
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}
	
	@Override
	protected void configReporteParams(ReporteParams reporteParams) {
		Map params = reporteParams.getQueryParams();
		Map parameterMap = (Map)params.get("parameterMap");
		String idiomaReporte = (String)parameterMap.get("idiomaReporte");
		String paisReporte = (String)parameterMap.get("paisReporte");
		
		Locale locale = new Locale(idiomaReporte,paisReporte);
		params.put(JRParameter.REPORT_LOCALE, locale);
	}

}