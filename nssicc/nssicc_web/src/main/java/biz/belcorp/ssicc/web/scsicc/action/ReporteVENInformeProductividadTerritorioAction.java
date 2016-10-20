// TODO Migrar al framework de reportes
package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteVENInformeProductividadTerritorioForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteVENInformeProductividadTerritorioAction extends
		BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = -846141383563622222L;

	private String formatoReporte;
	private List siccMarcaList = new ArrayList();
	private List siccCanalList = new ArrayList();
	private String siccPeriodoInicial;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	private String periodoActual = null;
	private String codigoMarca = null;
	private String codigoCanal = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteVENInformeProductividadTerritorioForm form = new ReporteVENInformeProductividadTerritorioForm();
		return form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		// TODO Auto-generated method stub
		ReporteVENInformeProductividadTerritorioForm reporteVENForm = (ReporteVENInformeProductividadTerritorioForm) this.formReporte;
		formatoReporte = reporteVENForm.getFormatoExportacion();
		ClassPathResource resource = new ClassPathResource(
				Constants.JASPER_DIRECTORIO + "subreporteVENInformeProductividadTerritorio"
						+ JASPER_EXTENSION);

		params.put("SUBREPORT_DIR1",
				(JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource.getPath() )));

		String codigoRegion = (String) params.get("codigoRegion");
		String codigoZona = (String) params.get("codigoZona");
		if ("Todos".equals(codigoRegion)) {
			codigoRegion = null;
			params.put("codigoRegion", codigoRegion);
		}
		if ("Todos".equals(codigoZona)) {
			codigoZona = null;
			params.put("codigoZona", codigoZona);
		}
		params.put("NroReporte", " ");
		params.put(
				"titulo",
				getReportResourceMessage("reporteVENInformeProductividadTerritorioForm.titulo"));
		return params;
	}

	/**
	 * @param Muestra lista de zonas segun la region escodiga.
	 */
	public void showZonasxRegion(ValueChangeEvent val) {
		log.debug(">>showZonasxRegion ");
		log.debug("val: " + val.getNewValue().toString());
		ReporteVENInformeProductividadTerritorioForm form = (ReporteVENInformeProductividadTerritorioForm) this.formReporte;
		String regiones = (String) val.getNewValue();
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		this.setSiccZonaList(aSvc.getZonasByPaisMarcaCanalRegion(
				form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT, regiones));
		form.setCodigoZona(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		this.mostrarReporteXLS = true;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		siccMarcaList = service.getMarcas();
		siccCanalList = service.getCanalesByCodigoISO(usuario.getIdioma()
				.getCodigoISO());
		this.siccRegionList = aSvc.getRegionesByPaisMarcaCanal(
				pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT);
		siccPeriodoInicial = service.getPeriodoDefaultByPaisCanal(
				pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT);
		ReporteVENInformeProductividadTerritorioForm reporteForm = (ReporteVENInformeProductividadTerritorioForm) this.formReporte;
		reporteForm.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(
				pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT));
		reporteForm.setCodigoPais(pais.getCodigo());
		codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
		codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
		reporteForm.setCodigoCanal(codigoCanal);
		reporteForm.setCodigoMarca(codigoMarca);

		reporteForm.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(pais.getCodigo(),Constants.CODIGO_CANAL_DEFAULT));
		
		log.debug("Todo Ok: Redireccionando");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveBeanReporteService()
	 */
	protected String devuelveBeanReporteService() {
		return "reportes.reporteVENInformeProductividadTerritorioService";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))
			return "reporteVENInformeProductividadTerritorioXLS";
		else
			return "reporteMaestroVertical";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		if ("PDF".equals(formatoReporte))
			return "reporteVENInformeProductividadTerritorioPDF";
		return "";
	}

	/**
	 * @return the formatoReporte
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte
	 *            the formatoReporte to set
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	/**
	 * @return the siccMarcaList
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList
	 *            the siccMarcaList to set
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * @return the siccCanalList
	 */
	public List getSiccCanalList() {
		return siccCanalList;
	}

	/**
	 * @param siccCanalList
	 *            the siccCanalList to set
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	/**
	 * @return the siccPeriodoInicial
	 */
	public String getSiccPeriodoInicial() {
		return siccPeriodoInicial;
	}

	/**
	 * @param siccPeriodoInicial
	 *            the siccPeriodoInicial to set
	 */
	public void setSiccPeriodoInicial(String siccPeriodoInicial) {
		this.siccPeriodoInicial = siccPeriodoInicial;
	}

	/**
	 * @return the siccRegionList
	 */
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 *            the siccRegionList to set
	 */
	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccZonaList
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 *            the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}
}