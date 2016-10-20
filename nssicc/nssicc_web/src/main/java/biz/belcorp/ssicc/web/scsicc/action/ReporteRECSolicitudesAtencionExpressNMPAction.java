package biz.belcorp.ssicc.web.scsicc.action;


import java.io.Serializable;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteRECSolicitudesAtencionExpressNMPForm;


/**
 * 
 * @author <a href="">Eduardo Sánchez</a>
 */
@ManagedBean
@SessionScoped
public class ReporteRECSolicitudesAtencionExpressNMPAction extends BaseReporteAbstractAction implements Serializable {
			
	private static final long serialVersionUID = 7496906172990857522L;

	private List siccRegionList;
	private LabelValue[] siccZonaList;
	private LabelValue[] siccTerritorioList;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
	    return new ReporteRECSolicitudesAtencionExpressNMPForm();
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteRECSolicitudesAtencionExpressNMPForm form = (ReporteRECSolicitudesAtencionExpressNMPForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(form.getFormatoExportacion()))
			return "reporteRECSolicitudesAtencionExpressNMPXLS";
		else
		   return "reporteMaestroHorizontal";
	   
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteRECSolicitudesAtencionExpressNMPForm form = (ReporteRECSolicitudesAtencionExpressNMPForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(form.getFormatoExportacion()))
			return null;
		else
		   return "reporteRECSolicitudesAtencionExpressNMPPDF";

	}
	
	public void showZonasxRegion(ValueChangeEvent val){
		log.debug(">>showZonasxRegion ");
		log.debug("val: "+ ArrayUtils.toString(val.getNewValue()));
		ReporteRECSolicitudesAtencionExpressNMPForm form = (ReporteRECSolicitudesAtencionExpressNMPForm) this.formReporte;
		String[] regiones = (String []) val.getNewValue();		
	      
		if(!ArrayUtils.isEmpty(regiones)){
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			setSiccZonaList(aSvc.getZonasMultipleByPaisMarcaCanalRegion(form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, 
					Constants.CODIGO_CANAL_DEFAULT, regiones, StringUtils.EMPTY));
		}else{
			setSiccZonaList(null);
		}
		
		setSiccTerritorioList(null);
		form.setZonaList(null);
	}

	
	public void showTerritorioXZonas(ValueChangeEvent val){
		log.debug(">>showTerritorioXZonas ");
		log.debug("val: "+ ArrayUtils.toString(val.getNewValue()));
		ReporteRECSolicitudesAtencionExpressNMPForm form = (ReporteRECSolicitudesAtencionExpressNMPForm) this.formReporte;
		String[] zonas = (String []) val.getNewValue();		
	      
		if(!ArrayUtils.isEmpty(zonas)){
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			List cRegionList = Arrays.asList(form.getRegionList());
			List cZonaList = Arrays.asList(zonas);
			setSiccTerritorioList(aSvc.getTerritoriosMultipleByPaisMarcaCanalRegionZona( form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, 
							Constants.CODIGO_CANAL_DEFAULT, cRegionList,cZonaList,StringUtils.EMPTY));
							
		}else{
			setSiccTerritorioList(null);
		}
		
		form.setTerritorioList(null);
	}

	
	/**
	 * Metodo para Cambiar el Rango de Fechas
	 * @param val
	 * @throws ParseException 
	 */
	public void loadFechaPeriodoInicio(String valor) {
		if (log.isDebugEnabled()) {
			log.debug("loadFechaPeriodoInicio");
		}
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		ReporteRECSolicitudesAtencionExpressNMPForm form = (ReporteRECSolicitudesAtencionExpressNMPForm) this.formReporte;
		
		String fechaInicio = aSvc.getFechaInicioPeriodoByPaisMarcaCanal(form.getCodigoPais(),  Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, valor);
		try {
			form.setFechaFacturacionInicio(DateUtils.parseDate(fechaInicio, "dd/MM/yyyy"));
		} catch (ParseException e) {
		}
		
	}
	
	public void loadFechaPeriodoFin(String valor) {
		if (log.isDebugEnabled()) {
			log.debug("loadFechaPeriodoFin");
		}
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		ReporteRECSolicitudesAtencionExpressNMPForm form = (ReporteRECSolicitudesAtencionExpressNMPForm) this.formReporte;
		
		String fechaFinal = aSvc.getFechaFinalPeriodoByPaisMarcaCanal(form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, valor);
		try {
			form.setFechaFacturacionFin(DateUtils.parseDate(fechaFinal, "dd/MM/yyyy"));
		} catch (ParseException e) {
		}
	}
	
	
	
	@Override
	public String setValidarReporte() 
	{
		ReporteRECSolicitudesAtencionExpressNMPForm form = (ReporteRECSolicitudesAtencionExpressNMPForm) this.formReporte;
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		String fechaInicio = DateUtil.convertDateToString(form.getFechaFacturacionInicio());
		String fechaFin = DateUtil.convertDateToString(form.getFechaFacturacionFin());

		if (StringUtils.isBlank(fechaInicio) && StringUtils.isBlank(fechaFin)) {
			String mensaje = "Debe ingresar las fechas de Facturación";
			return mensaje;
		}

		int resultadoFechasPantalla = DateUtil.compareDates(fechaInicio, fechaFin, "dd/MM/yyyy");
		if (resultadoFechasPantalla == 1) {
			String mensaje = this.getResourceMessage("reporteRECSolicitudesAtencionExpressNMPForm.info.fechaFac");
			return mensaje;
		}

		String strFechaInicial = ajaxService.getFechaInicioPeriodoByPaisMarcaCanal(form.getCodigoPais(),
						Constants.CODIGO_MARCA_DEFAULT,
						Constants.CODIGO_CANAL_DEFAULT, form.getCodigoPeriodoInicio());

		String strFechaFinal = ajaxService.getFechaFinalPeriodoByPaisMarcaCanal(form.getCodigoPais(),
						Constants.CODIGO_MARCA_DEFAULT,
						Constants.CODIGO_CANAL_DEFAULT, form.getCodigoPeriodoFin());

		if (StringUtils.isNotBlank(fechaInicio)) 
		{
			int resultado = DateUtil.compareDates(strFechaInicial, fechaInicio, "dd/MM/yyyy");
			int resultado1 = DateUtil.compareDates(fechaInicio, strFechaFinal, "dd/MM/yyyy");

			if (resultado == 1 || resultado1 == 1) {
				return "La Fecha Facturación Inicio debe de encontrarse en los siguientes intervalos "
						.concat(strFechaInicial).concat(" - ")
						.concat(strFechaFinal);
			}
		}

		if (StringUtils.isNotBlank(fechaFin)) 
		{
			int resultado2 = DateUtil.compareDates(strFechaInicial, fechaFin, "dd/MM/yyyy");
			int resultado3 = DateUtil.compareDates(fechaFin, strFechaFinal, "dd/MM/yyyy");

			if (resultado2 == 1 || resultado3 == 1) 
			{
				return "La Fecha Facturación Fin debe de encontrarse en los siguientes intervalos "
						.concat(strFechaInicial).concat(" - ").concat(strFechaFinal);
			}
		}
		return null;
	}
	
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		
		ReporteRECSolicitudesAtencionExpressNMPForm reporteRECForm = (ReporteRECSolicitudesAtencionExpressNMPForm) this.formReporte;


		String condicionRegion = obtieneCondicion(reporteRECForm.getRegionList(), "gen_pkg_gener.GEN_FN_CLIEN_DATOS(gen_pkg_gener.GEN_FN_DEVUELVE_COD_CLIE(PSC.CLIE_OID_CLIE), 'COD_REGI')", "'");
		String condicionZonas = obtieneCondicion(reporteRECForm.getZonaList(), "gen_pkg_gener.GEN_FN_CLIEN_DATOS(gen_pkg_gener.GEN_FN_DEVUELVE_COD_CLIE(PSC.CLIE_OID_CLIE), 'COD_ZONA')", "'");
		String condicionTerritorio = obtieneCondicion(reporteRECForm.getTerritorioList(), "gen_pkg_gener.GEN_FN_CLIEN_DATOS(gen_pkg_gener.GEN_FN_DEVUELVE_COD_CLIE(PSC.CLIE_OID_CLIE), 'COD_TERR')", "'");
		String condicion = condicionRegion + condicionZonas + condicionTerritorio;

		String titulo = getReportResourceMessage("reporteRECSolicitudesAtencionExpressNMPForm.titulo");
		params.put("condicion", condicion);
		params.put("titulo", titulo);

		params.put("fechaFacturacionInicio", DateFormatUtils.format(reporteRECForm.getFechaFacturacionInicio(), "dd/MM/yyyy"));
		params.put("fechaFacturacionFin", DateFormatUtils.format(reporteRECForm.getFechaFacturacionFin(), "dd/MM/yyyy"));

		// Se agregan como parametros del reporte de Reclamos el periodo de
		// inicio y de fin
		Map criteria = params;
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");

		criteria.put("codigoPeriodo", reporteRECForm.getCodigoPeriodoInicio());
		String oidPeriodoInicio = reporteService.getOidString("getOidPeriodoByCodigoPeriodo", criteria);
		params.put("oidPeriodoInicio", oidPeriodoInicio);
		criteria.put("codigoPeriodo", reporteRECForm.getCodigoPeriodoFin());
		String oidPeriodoFin = reporteService.getOidString("getOidPeriodoByCodigoPeriodo", criteria);
		params.put("oidPeriodoFin", oidPeriodoFin);
		
		params.put("codigoPeriodoInicio", oidPeriodoInicio);
		params.put("codigoPeriodoFin", oidPeriodoFin);
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		log.info("Entro a setViewAttributes");

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = true;
		
		ReporteRECSolicitudesAtencionExpressNMPForm formRec = (ReporteRECSolicitudesAtencionExpressNMPForm)this.formReporte;
		
		// parametros generales
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		
		formRec.setCodigoPais(pais.getCodigo());
		
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");

		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		siccRegionList = reporteService.getListaGenerico("getRegionesByPais", criteriaOperacion);
		

		/* colocando periodos */
		String codigoPeriodo = service.getPeriodoDefaultByPaisCanal(pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT);
		formRec.setCodigoPeriodoInicio(codigoPeriodo);
		formRec.setCodigoPeriodoFin(codigoPeriodo);

		/* colocando fechas */
		String date = ajaxService.getFechaInicioPeriodoByPaisMarcaCanal(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, codigoPeriodo);
		
		formRec.setFechaFacturacionInicio(DateUtils.parseDate(date, "dd/MM/yyyy"));
		date = ajaxService.getFechaFinalPeriodoByPaisMarcaCanal(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, codigoPeriodo);
		formRec.setFechaFacturacionFin(DateUtils.parseDate(date, "dd/MM/yyyy"));
		
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
	 * @return the siccTerritorioList
	 */
	public LabelValue[] getSiccTerritorioList() {
		return siccTerritorioList;
	}

	/**
	 * @param siccTerritorioList the siccTerritorioList to set
	 */
	public void setSiccTerritorioList(LabelValue[] siccTerritorioList) {
		this.siccTerritorioList = siccTerritorioList;
	}

	

	
}