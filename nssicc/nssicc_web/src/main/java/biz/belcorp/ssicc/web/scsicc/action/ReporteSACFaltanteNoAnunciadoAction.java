package biz.belcorp.ssicc.web.scsicc.action;

import java.text.ParseException;
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
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.util.StringUtil;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReportePEDPedidosFacturadosYobelForm;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSACFaltanteNoAnunciadoForm;


// TODO: Auto-generated Javadoc
/**
 * The Class ReporteSACFaltanteNoAnunciadoAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 21/05/2014
 */
@ManagedBean
@SessionScoped
public class ReporteSACFaltanteNoAnunciadoAction extends BaseReporteAbstractAction{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The sicc region list. */
	private LabelValue[] siccRegionList = {};
	
	/** The sicc zona list. */
	private LabelValue[] siccZonaList = {};
	
	/** The sac unidad negocio list. */
	private LabelValue[] sacUnidadNegocioList = {};
	
	/** The tipo reporte. */
	private String tipoReporte = "";
	
	private String fechaInicialPeriodo;
	private String fechaFinalPeriodo;
	private boolean muestraZonas;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSACFaltanteNoAnunciadoForm reporteForm = new ReporteSACFaltanteNoAnunciadoForm();
		return reporteForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {		
		ReporteSACFaltanteNoAnunciadoForm reporteSICForm = (ReporteSACFaltanteNoAnunciadoForm) formReporte;		
		if ("XLS".equals(this.formReporte.getFormatoExportacion())) {
			log.debug("nombre del reporte: " + "reporteSACFaltanteNoAnunciado" + this.getTipoReporte() + "XLS");
			return "reporteSACFaltanteNoAnunciado" + this.getTipoReporte() + "XLS";
		} else {
			log.debug("nombre del reporte: " + "reporteMaestroVerticalSAC");
			return "reporteMaestroVerticalSAC";
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		log.debug("nombre del reporte: " + "reporteSACFaltanteNoAnunciado" + tipoReporte+ "PDF");
		return "reporteSACFaltanteNoAnunciado" + tipoReporte+ "PDF";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteSACFaltanteNoAnunciadoForm reporteSACForm = (ReporteSACFaltanteNoAnunciadoForm) this.formReporte;
		String tipoConsulta = "";
		String incluyeMav = "";
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteria = params;
				
		criteria.put("codigoPeriodo", reporteSACForm.getCodigoPeriodo());
		String oidPeriodo=reporteService.getOidString("getDesPerioByOidPerio", criteria);
		params.put("codigoPeriodo",oidPeriodo);
		
		if (reporteSACForm.getTipoConsulta().equals("R")) {
			this.tipoReporte="Zona";
			tipoConsulta = "Resumen";
		}
		if (reporteSACForm.getTipoConsulta().equals("D")){
			this.tipoReporte="Region";
			tipoConsulta = "Detalle";
		}
		if (reporteSACForm.getTipoConsulta().equals("T")){
			this.tipoReporte="Total";
			tipoConsulta = "Total";
		}
		
		params.put("unidadNegocio",this.obtieneCondicion(reporteSACForm.getUnidadNegocio(), "h.cod_unid_nego", "'"));
		params.put("region",this.obtieneCondicion(reporteSACForm.getCodigoRegion(), "d.cod_REGI", "'"));
		params.put("zona",this.obtieneCondicion(reporteSACForm.getCodigoZona(), "c.cod_ZONA", "'"));
		
		if (reporteSACForm.getIncluyeMAV().equals("N")){
			this.tipoReporte=this.tipoReporte+"NoMav";
			incluyeMav = "No";
		}
		else{
			this.tipoReporte=this.tipoReporte+"SiMav";
			incluyeMav = "Si";
		}
		
		String fechaInicio = "";
		String fechaFin = "";
		
	    if(reporteSACForm.getFecFactIni() != null){
	    	fechaInicio = DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, reporteSACForm.getFecFactIni());
	    	params.put("fecFactIni", DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, reporteSACForm.getFecFactIni()));
	    }else{
	    	fechaInicio = "";
	    	params.put("fecFactIni", "");
	    }
		
		if(reporteSACForm.getFecFactFin() != null){
			fechaFin = DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, reporteSACForm.getFecFactFin());
			params.put("fecFactFin", DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, reporteSACForm.getFecFactFin()));
		}else{
			fechaFin = "";
			params.put("fecFactFin", "");
		}
		
		Map paramsCab = new HashMap();
		paramsCab.put("codigoPeriodo",oidPeriodo);
		paramsCab.put("fecFactIni",reporteSACForm.getFecFactIni());
		paramsCab.put("fecFactFin",reporteSACForm.getFecFactFin());
		paramsCab.put("region",reporteSACForm.getCodigoRegion());		
		paramsCab.put("zona",reporteSACForm.getCodigoZona());
		
		//Map criteriaCab = reporteService.getCabeceraSACFaltante(paramsCab);			
		
		params.put("titulo", getResourceMessage(
				"reporteSACFaltanteNoAnunciadoForm.title")
			//	+ "\n" + "Pedidos Totales: "+criteriaCab.get("pedidosTotales")
			//	+ "\n" + "Unidades Totales: "+criteriaCab.get("unidadesTotales")
			//	+ "\n" + "Monto: "+  criteriaCab.get("monto").toString()
				+ "\n" + "Campaña: "+reporteSACForm.getCodigoPeriodo()
				+ "\n" + "Fecha desde: "+fechaInicio+"   Fecha hasta:"+fechaFin
				+ "\n" + "Tipo Consulta: "+ tipoConsulta
				+ "\n" + "Incluye MAV: "+incluyeMav
				+ "\n" + this.getSubTitulo(reporteSACForm));
		return criteria;
	}
	
	/**
	 * Gets the sub titulo.
	 *
	 * @param reporteSACForm the reporte sac form
	 * @return the sub titulo
	 */
	private String getSubTitulo(ReporteSACFaltanteNoAnunciadoForm reporteSACForm){
		
		String subTitulo = "";
		if (reporteSACForm.getUnidadNegocio()!=null && reporteSACForm.getUnidadNegocio().length > 0)
			subTitulo += "Unidad Negocio: "+ this.obtieneLista(reporteSACForm.getUnidadNegocio())+"\n";
		if (reporteSACForm.getCodigoRegion()!=null && reporteSACForm.getCodigoRegion().length > 0)			
			subTitulo += "Region: "+this.obtieneLista(reporteSACForm.getCodigoRegion())+"\n";
		if (reporteSACForm.getCodigoZona()!=null && reporteSACForm.getCodigoZona().length > 0)			
			subTitulo+= "Zona: "+this.obtieneLista(reporteSACForm.getCodigoZona());	
		return subTitulo;
	}
	
	/**
	 * Obtiene lista.
	 *
	 * @param lista the lista
	 * @return the string
	 */
	private String obtieneLista(String[] lista) {
		if (lista == null || lista.length == 0){
			return "";
		}else{
			String resultado = " ";
			for (int i = 0; i < lista.length; i++) {
				String dato = lista[i];
				resultado += dato + ",";
			}
			resultado = resultado.substring(0,resultado.length()-1);
			return resultado;
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Finish 'setViewAtributes' method");
		}
		
		this.mostrarReporteXLS = true;
		
		ReporteSACFaltanteNoAnunciadoForm f = (ReporteSACFaltanteNoAnunciadoForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		List listaRegiones = reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);
		this.siccRegionList = new LabelValue[listaRegiones.size()]; 		
		
		int i = 0;
		for(Object object : listaRegiones){
			LabelValue labelValue = new LabelValue();
			labelValue.setLabel(((Base)object).getDescripcion());
			labelValue.setValue(((Base)object).getCodigo());
			this.getSiccRegionList()[i] = labelValue;
			i++;
		}
		
		List listaUnidadNegocio = reporteService.getUnidadNegocio();
		int j = 0;
		this.sacUnidadNegocioList = new LabelValue[listaUnidadNegocio.size()];
		for(Object object : listaUnidadNegocio){
			LabelValue labelValue = new LabelValue();
			labelValue.setLabel(((Base)object).getDescripcion());
			labelValue.setValue(((Base)object).getCodigo());
			this.getSacUnidadNegocioList()[j] = labelValue;
			j++;
		}
		// Carga Fecha y Periodo
        InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
        f.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),Constants.CODIGO_CANAL_DEFAULT));
        f.setFecFactIni(DateUtil.convertStringToDate(Constants.DEFAULT_DATE_FORMAT, aSvc.getFechaInicioPeriodoByPaisMarcaCanal(f.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, f.getCodigoPeriodo())));
        f.setFecFactFin(DateUtil.convertStringToDate(Constants.DEFAULT_DATE_FORMAT, aSvc.getFechaFinalPeriodoByPaisMarcaCanal(f.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, f.getCodigoPeriodo())));
        
        if(StringUtils.equals(f.getTipoConsulta(), "D")){
        	this.muestraZonas= true;
        }else{
        	this.muestraZonas= false;
        }
	}
	
	/**
	 * Load fechas periodos.
	 */
	public void loadFechasPeriodos(String val){
		log.debug("loadFechasPeriodos...");
		
		ReporteSACFaltanteNoAnunciadoForm f = (ReporteSACFaltanteNoAnunciadoForm) this.formReporte;
		AjaxService ajaxService = (AjaxService)this.getBean("ajaxService");
		
		f.setCodigoPeriodo(val);
		
		try {
			f.setFecFactIni(DateUtil.convertStringToDate(Constants.DEFAULT_DATE_FORMAT, ajaxService.getFechaInicioPeriodoByPaisMarcaCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, f.getCodigoPeriodo())));
			f.setFecFactFin(DateUtil.convertStringToDate(Constants.DEFAULT_DATE_FORMAT, ajaxService.getFechaFinalPeriodoByPaisMarcaCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, f.getCodigoPeriodo())));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Load Zonas by Tipo Consulta
	 */
	public void loadZonasByTipoConsulta(ValueChangeEvent val){
		log.debug("loadZonasByTipoConsulta...");
		
		ReporteSACFaltanteNoAnunciadoForm f = (ReporteSACFaltanteNoAnunciadoForm) this.formReporte;
		AjaxService ajaxService = (AjaxService)this.getBean("ajaxService");
		
		String tipoConsulta = val.getNewValue().toString();
		f.setTipoConsulta(tipoConsulta);
		
		if(StringUtils.equals(f.getTipoConsulta(), "D")){
			this.muestraZonas = true;
		}else{
			this.muestraZonas = false;
		}
	}
	
	/**
	 * Load Zonas by Region
	 */
	public void loadZonasByRegion(ValueChangeEvent val){
		log.debug("loadZonasByRegion...");
		
		ReporteSACFaltanteNoAnunciadoForm f = (ReporteSACFaltanteNoAnunciadoForm) this.formReporte;
		AjaxService ajaxService = (AjaxService)this.getBean("ajaxService");
		
		String[] regiones = (String[]) val.getNewValue();
		
		this.siccZonaList = ajaxService.getZonasMultipleByPaisMarcaCanalRegion(f.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, regiones, "");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	@Override
	public String setValidarReporte() {
		if(log.isDebugEnabled()){
			log.debug("setValidarReporte...");
		}
			
		ReporteSACFaltanteNoAnunciadoForm form = (ReporteSACFaltanteNoAnunciadoForm) this.formReporte;
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		
		int resultado = 0;
		int resultado1 = 0;
		String mensaje = "";
		
		if(form.getFecFactIni()!=null && form.getFecFactFin()!=null){
			if(form.getFecFactIni().compareTo(form.getFecFactFin())>0){
				this.setMensajeAlertaDefault(this.getResourceMessage("errors.compare.dates"));
				return this.getMensajeAlertaDefault();
			}
		}
		
		if(StringUtils.isBlank(form.getTipoConsulta())){
			this.setMensajeAlertaDefault("'Tipo de Consulta' es un campo requerido");
			return this.getMensajeAlertaDefault();
		}
		
		if(StringUtils.isBlank(form.getIncluyeMAV())){
			this.setMensajeAlertaDefault("'Incluye MAV' es un campo requerido");
			return this.getMensajeAlertaDefault();
		}
		
		fechaInicialPeriodo = ajaxService.getFechaInicioPeriodoByPaisMarcaCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, form.getCodigoPeriodo());
		fechaFinalPeriodo = ajaxService.getFechaFinalPeriodoByPaisMarcaCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, form.getCodigoPeriodo());
		
		try {
			if (form.getFecFactIni().toString() != ""){
				resultado = form.getFecFactIni().compareTo(DateUtil.convertStringToDate(DateUtil.getDatePattern(), fechaFinalPeriodo));
				resultado1 = DateUtil.convertStringToDate(DateUtil.getDatePattern(), fechaInicialPeriodo).compareTo(form.getFecFactIni());
				
				/*resultado  = compareDates(fechaDesde ,'dd/MM/yyyy',fechaFinal,'dd/MM/yyyy');    
		    	resultado1 = compareDates(fechaInicio ,'dd/MM/yyyy',fechaDesde,'dd/MM/yyyy');*/
				
				if (resultado == 1 || resultado1 == 1){
		    		mensaje = this.getResourceMessage("errors.compare.campaigns.periodo.fechaInicio") + fechaInicialPeriodo + " - " + fechaFinalPeriodo;
					
					//alert("<fmt:message key="errors.compare.campaigns.periodo.fechaInicio"/>"+ fechaInicio+' - '+fechaFinal) ; 
					return mensaje;
		        }
			}
			
			if (form.getFecFactFin().toString() != "") {
				resultado = form.getFecFactFin().compareTo(DateUtil.convertStringToDate(DateUtil.getDatePattern(), fechaFinalPeriodo));
				resultado1 = DateUtil.convertStringToDate(DateUtil.getDatePattern(), fechaInicialPeriodo).compareTo(form.getFecFactFin());
				
				/*resultado = compareDates(fechaHasta ,'dd/MM/yyyy',fechaFinal,'dd/MM/yyyy');
		    	resultado1 = compareDates(fechaInicio ,'dd/MM/yyyy',fechaHasta,'dd/MM/yyyy');*/
		    	
		    	if (resultado == 1 || resultado1 == 1){
		    		mensaje = this.getResourceMessage("errors.compare.campaigns.periodo.fechaFin") + fechaInicialPeriodo + " - " + fechaFinalPeriodo;
		    		
		    		//alert("<fmt:message key="errors.compare.campaigns.periodo.fechaFin"/>"+ fechaInicio+' - '+fechaFinal) ; 
		        	return mensaje;
		        }   
	      	}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}	
	
	/**
	 * Metodos Get y Set
	 * @return
	 */

	public LabelValue[] getSacUnidadNegocioList() {
		return sacUnidadNegocioList;
	}

	public void setSacUnidadNegocioList(LabelValue[] sacUnidadNegocioList) {
		this.sacUnidadNegocioList = sacUnidadNegocioList;
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

	public String getTipoReporte() {
		return tipoReporte;
	}

	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	/**
	 * @return the muestraZonas
	 */
	public boolean isMuestraZonas() {
		return muestraZonas;
	}

	/**
	 * @param muestraZonas the muestraZonas to set
	 */
	public void setMuestraZonas(boolean muestraZonas) {
		this.muestraZonas = muestraZonas;
	}	
}
