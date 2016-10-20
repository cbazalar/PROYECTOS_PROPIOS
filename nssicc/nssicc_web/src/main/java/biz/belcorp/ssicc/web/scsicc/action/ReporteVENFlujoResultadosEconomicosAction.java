package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteVENFlujoResultadosEconomicosForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteVENFlujoResultadosEconomicosAction extends BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = 1L;
	private String formatoReporte;
	private List siccMarcaList = new ArrayList();
	private List siccCanalList = new ArrayList();;
	private String siccPeriodoInicial;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	private String periodoActual = null;
	private String codigoMarca = null;
	private String codigoCanal = null;
	private List siccAnioList = new ArrayList();
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteVENFlujoResultadosEconomicosForm form = new ReporteVENFlujoResultadosEconomicosForm();
		return form;
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
		if ("XLS".equals(this.formatoReporte))
			return "reporteVENFlujoResultadosEconomicosZonaXLS";
		else
			return "reporteMaestroHorizontal";
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
		if ("PDF".equals(this.formatoReporte))
			return "reporteVENFlujoResultadosEconomicosZonaPDF";
		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes...");
		}
		
		this.mostrarReporteXLS = true;
		
		ReporteVENFlujoResultadosEconomicosForm reporteForm = (ReporteVENFlujoResultadosEconomicosForm) this.formReporte;
		
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		this.siccMarcaList = service.getMarcas();
		this.siccCanalList = service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		this.siccRegionList = aSvc.getRegionesByPaisMarcaCanal(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT);
		this.siccPeriodoInicial = service.getPeriodoDefaultByPaisCanal(pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT);

		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());

		reporteForm.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT));
		reporteForm.setCodigoPais(pais.getCodigo());
		
		this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
		this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
		reporteForm.setCodigoCanal(this.codigoCanal);
		reporteForm.setCodigoMarca(this.codigoMarca);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String anio = sdf.format(new Date(System.currentTimeMillis()));
		reporteForm.setAnio(anio);
		this.siccAnioList = reporteService.getListaGenerico("getAniosCraPerio", criteriaOperacion);

		log.debug("Todo Ok: Redireccionando");
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
		ReporteVENFlujoResultadosEconomicosForm reporteVENForm = (ReporteVENFlujoResultadosEconomicosForm) this.formReporte;
		this.formatoReporte = reporteVENForm.getFormatoExportacion();

		params.put("NroReporte", getReportResourceMessage("reporteVENFlujoResultadosEconomicosForm.numero.reporte"));
		params.put("titulo", getReportResourceMessage("reporteVENFlujoResultadosEconomicosForm.titulo"));
		
		return params;
	}
	
	/**
	 * Obtener Lista de Regiones Por Marca
	 * 
	 * @param val
	 */
	public void showRegionesxMarca(ValueChangeEvent val) {
		log.debug(">>showRegionesxMarca ");
		
		try {
			ReporteVENFlujoResultadosEconomicosForm form = (ReporteVENFlujoResultadosEconomicosForm) this.formReporte;
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			
			String marca = (String) val.getNewValue();
			form.setCodigoMarca(marca);
			
			this.siccRegionList = aSvc.getRegionesByPaisMarcaCanal(form.getCodigoPais(), form.getCodigoMarca(), form.getCodigoCanal());
			
			form.setCodigoZona(null);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}	
	}
	
	/**
	 * Obtener Lista de Regiones Por Canal
	 * 
	 * @param val
	 */
	public void showRegionesxCanal(ValueChangeEvent val) {
		log.debug(">>showRegionesxCanal ");
		
		try {
			ReporteVENFlujoResultadosEconomicosForm form = (ReporteVENFlujoResultadosEconomicosForm) this.formReporte;
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			
			String canal = (String) val.getNewValue();
			form.setCodigoCanal(canal);
			
			this.siccRegionList = aSvc.getRegionesByPaisMarcaCanal(form.getCodigoPais(), form.getCodigoMarca(), form.getCodigoCanal());
			
			form.setCodigoZona(null);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}	
	}

	/**
	 * Obtener Lista de Zonas
	 * 
	 * @param val
	 */
	public void showZonasxRegion(ValueChangeEvent val) {
		log.debug(">>showZonasxRegion ");
		
		try {
			ReporteVENFlujoResultadosEconomicosForm form = (ReporteVENFlujoResultadosEconomicosForm) this.formReporte;
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			
			String regiones = (String) val.getNewValue();
			form.setCodigoRegion(regiones);
			
			this.siccZonaList = aSvc.getZonasByPaisMarcaCanalRegion(form.getCodigoPais(), form.getCodigoMarca(), form.getCodigoCanal(), form.getCodigoRegion());
			
			form.setCodigoZona(null);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveBeanReporteService()
	 */
	protected String devuelveBeanReporteService() {
		return "reportes.reporteVENFlujoResultadosEconomicosService";
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

	/**
	 * @return the periodoActual
	 */
	public String getPeriodoActual() {
		return periodoActual;
	}

	/**
	 * @param periodoActual
	 *            the periodoActual to set
	 */
	public void setPeriodoActual(String periodoActual) {
		this.periodoActual = periodoActual;
	}

	/**
	 * @return the codigoMarca
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca
	 *            the codigoMarca to set
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	/**
	 * @return the codigoCanal
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal
	 *            the codigoCanal to set
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @return the siccAnioList
	 */
	public List getSiccAnioList() {
		return siccAnioList;
	}

	/**
	 * @param siccAnioList
	 *            the siccAnioList to set
	 */
	public void setSiccAnioList(List siccAnioList) {
		this.siccAnioList = siccAnioList;
	}
}