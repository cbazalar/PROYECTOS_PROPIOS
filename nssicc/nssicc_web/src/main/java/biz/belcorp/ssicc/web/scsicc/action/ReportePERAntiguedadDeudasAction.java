package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Calendar;
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
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReportePERAntiguedadDeudasForm;

/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano
 *         Huamán</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ReportePERAntiguedadDeudasAction extends BaseReporteAbstractAction {

	private String tipoReporte;
	private String tipoVistaMes;
	private String tipoVistaPeriodo;
	private List tipoReporteList;
	private List siccMarcaList;
	private List siccSociedaddList;
	private LabelValue[] siccRegionList;
	private List siccCanalList;
	private LabelValue[] siccZonaList;

	
	public String getTipoVistaMes() {
		return tipoVistaMes;
	}

	public void setTipoVistaMes(String tipoVistaMes) {
		this.tipoVistaMes = tipoVistaMes;
	}

	public String getTipoVistaPeriodo() {
		return tipoVistaPeriodo;
	}

	public void setTipoVistaPeriodo(String tipoVistaPeriodo) {
		this.tipoVistaPeriodo = tipoVistaPeriodo;
	}

	public String getTipoReporte() {
		return tipoReporte;
	}

	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	public List getTipoReporteList() {
		return tipoReporteList;
	}

	public void setTipoReporteList(List tipoReporteList) {
		this.tipoReporteList = tipoReporteList;
	}

	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	public List getSiccSociedaddList() {
		return siccSociedaddList;
	}

	public void setSiccSociedaddList(List siccSociedaddList) {
		this.siccSociedaddList = siccSociedaddList;
	}

	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public List getSiccCanalList() {
		return siccCanalList;
	}

	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	



	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}





	private static final long serialVersionUID = 5452137025798023586L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReportePERAntiguedadDeudasForm reporteForm = new ReportePERAntiguedadDeudasForm();
		return reporteForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreReporte()
	 */
	protected String devuelveNombreReporte() throws Exception {

		// TODO Auto-generated method stub
		return "reporteMaestroHorizontal";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreSubReporte()
	 */
	protected String devuelveNombreSubReporte() throws Exception {
		if (StringUtils.equals("mes", tipoReporte))
			return "antiguedadDeudasMeses";
		else
			return "antiguedadDeudasPeriodos";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()ReporteRECMercaderiaSiniestradaAction
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'ReportePERAntiguedadDeudasForm.setViewAtributes' method");
		}

		this.mostrarReportePDF = true;
		ReportePERAntiguedadDeudasForm form = (ReportePERAntiguedadDeudasForm) this.formReporte;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
//		form.setCodigoSociedad(Constants.CODIGO_SOCIEDAD_DEFAULT);
//		form.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
//		form.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		form.setCodigoPais(pais.getCodigo());
		this.tipoVistaMes="1";
		this.tipoVistaPeriodo="2";
		form.setTipoVista("1");
		siccSociedaddList = service.getSociedadesByCodigoPais(pais.getCodigo());
		siccMarcaList = service.getMarcas();
		siccCanalList = service.getCanalesByCodigoISO(usuario.getIdioma()
				.getCodigoISO());
		
		ReportePERAntiguedadDeudasForm formAjax = (ReportePERAntiguedadDeudasForm) this.formReporte;
		AjaxService aSvc = (AjaxService) getBean("ajaxService");


	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap()
	 */
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReportePERAntiguedadDeudasAction.prepareParameterMap' method");
		}

		ReportePERAntiguedadDeudasForm f = (ReportePERAntiguedadDeudasForm) this.formReporte;
		Calendar fecha = Calendar.getInstance();
		tipoReporte="periodo";
		if (f.getTipoVista().equalsIgnoreCase(Constants.TIPO_VISTA_MES))
			tipoReporte= "mes";
		params.put("titulo", getResourceMessage("reportePERAntiguedadDeudasForm.title"));
		params.put("NroReporte","");
		params.put("anhoActual", "" + fecha.get(Calendar.YEAR));
		params.put("anhoActualAnterior", "" + (fecha.get(Calendar.YEAR)-1));
		params.put("anhoActualDosAnterior", "" + (fecha.get(Calendar.YEAR)-2));
		if (StringUtils.isEmpty(f.getCodigoSeccion()))
			params.put("codigoSeccion", "Todos");
		if (StringUtils.isEmpty(f.getCodigoZona()))
			params.put("codigoZona", "Todos");
		
		if (f.getTipoVista().equalsIgnoreCase(Constants.TIPO_VISTA_MES)) {
			
				ClassPathResource meses2Resource = new ClassPathResource(
						Constants.JASPER_DIRECTORIO + "deudasMeses.jasper", getClass());
						
				params.put("SUBREPORT_DIR1", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(meses2Resource.getPath() )));

		} else {
			
				ClassPathResource periodos2Resource = new ClassPathResource(
						Constants.JASPER_DIRECTORIO + "deudasPeriodosActual.jasper", getClass());
				params.put("SUBREPORT_DIR1", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(periodos2Resource.getPath() )));
			
		}
		
		
		f.setDescripcionSociedad(getDescripcionSociedad(f.getCodigoSociedad()));
		
		return params;

	}
	
	/**
	 * 
	 * @return
	 */
	private String getDescripcionSociedad(String codigoSociedad) {
		if(siccSociedaddList != null && siccSociedaddList.size() > 0 && StringUtils.isNotBlank(codigoSociedad))
		{
			for(int i=0; i<this.siccSociedaddList.size(); i++)
			{
				Base s = (Base)this.siccSociedaddList.get(i);
				
				if(StringUtils.equals(s.getCodigo(), codigoSociedad))
				{
					return s.getDescripcion();
				}
			}
		}
		
		return "";
	}

	/**
	 * @param val
	 */

	
	public void loadRegionMarca(ValueChangeEvent val) {

		log.debug(">>loadRegionMarca...");
		String valor = val.getNewValue().toString();

		ReportePERAntiguedadDeudasForm form = (ReportePERAntiguedadDeudasForm) this.formReporte;
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		this.setSiccZonaList(null);
		this.setSiccRegionList(aSvc.getRegionesByPaisMarcaCanal(
				this.mPantallaPrincipalBean.getCurrentCountry().getCodigo(),
				valor, form.getCodigoCanal()));
		
		return;
	}
	
	
	public void loadZonaRegion(ValueChangeEvent val) {

		log.debug(">>loadZonaRegion...");
		String regionSelect = val.getNewValue().toString();

		ReportePERAntiguedadDeudasForm form = (ReportePERAntiguedadDeudasForm) this.formReporte;
		AjaxService aSvc = (AjaxService) getBean("ajaxService");

		this.setSiccZonaList(aSvc.getZonasByPaisCanalRegion(
				this.mPantallaPrincipalBean.getCurrentCountry().getCodigo(),
				form.getCodigoCanal(),regionSelect));
		return;
	}

	/**
	 * Carga Combo Concursos por Canal
	 * 
	 * @param val
	 */
	public void loadRegionCanal(ValueChangeEvent val) {

		log.debug(">>loadConcursoCanal...");
		String valor = val.getNewValue().toString();

		ReportePERAntiguedadDeudasForm form = (ReportePERAntiguedadDeudasForm) this.formReporte;
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		this.setSiccZonaList(null);
		this.setSiccRegionList(aSvc.getRegionesByPaisMarcaCanal(
				this.mPantallaPrincipalBean.getCurrentCountry().getCodigo(),
				form.getCodigoMarca(), valor));

		return;
	}

}