package biz.belcorp.ssicc.web.scsicc.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import com.ctc.wstx.util.DataUtil;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSACFaltanteNoAnunciadoForm;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSACFaltanteNoAnunciadoNegocioForm;

/**
 * The Class ReporteSACFaltanteNoAnunciadoNegocioAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 22/05/2014
 */
@ManagedBean
@SessionScoped
public class ReporteSACFaltanteNoAnunciadoNegocioAction extends BaseReporteAbstractAction{

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
	
	private boolean mostrarZona;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSACFaltanteNoAnunciadoNegocioForm reporteForm = new ReporteSACFaltanteNoAnunciadoNegocioForm();
		return reporteForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {		
		ReporteSACFaltanteNoAnunciadoNegocioForm reporteSICForm = (ReporteSACFaltanteNoAnunciadoNegocioForm) formReporte;		
		if ("XLS".equals(this.formReporte.getFormatoExportacion())) {
			log.debug("nombre del reporte: " + "reporteSACFaltanteNoAnunciadoNegocio" + this.getTipoReporte() + "XLS");
			return "reporteSACFaltanteNoAnunciadoNegocio" + this.getTipoReporte() + "XLS";
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
		log.debug("nombre del reporte: " + "reporteSACFaltanteNoAnunciadoNegocio" + tipoReporte+ "PDF");
		return "reporteSACFaltanteNoAnunciadoNegocio" + tipoReporte+ "PDF";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteSACFaltanteNoAnunciadoNegocioForm reporteSACForm = (ReporteSACFaltanteNoAnunciadoNegocioForm) this.formReporte;
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
		
		params.put("unidadNegocio",this.obtieneCondicion(reporteSACForm.getNegocio(), "h.cod_nego", "'"));
		if (!reporteSACForm.getTipoConsulta().equals("T")){
			params.put("region",this.obtieneCondicion(reporteSACForm.getCodigoRegion(), "d.cod_REGI", "'"));
			params.put("zona",this.obtieneCondicion(reporteSACForm.getCodigoZona(), "c.cod_ZONA", "'"));
		}
						
		if (reporteSACForm.getIncluyeMAV().equals("N")){			
			this.tipoReporte=this.tipoReporte+"NoMav";
			incluyeMav = "No";
		}
		else{
			this.tipoReporte=this.tipoReporte+"SiMav";
			incluyeMav = "Si";
		}
		
	    params.put("fecFactIni",DateUtil.convertDateToString(reporteSACForm.getFecFactIni()));
		params.put("fecFactFin",DateUtil.convertDateToString(reporteSACForm.getFecFactFin()));
		
		Map paramsCab = new HashMap();
		paramsCab.put("codigoPeriodo",oidPeriodo);
		
		
		paramsCab.put("fecFactIni",DateUtil.convertDateToString(reporteSACForm.getFecFactIni()));
		paramsCab.put("fecFactFin",DateUtil.convertDateToString(reporteSACForm.getFecFactFin()));
		paramsCab.put("region",reporteSACForm.getCodigoRegion());		
		paramsCab.put("zona",reporteSACForm.getCodigoZona());
		
		//Map criteriaCab = reporteService.getCabeceraSACFaltante(paramsCab);			
		
		params.put("titulo", this.getResourceMessage("reporteSACFaltanteNoAnunciadoNegocioForm.title")
				+ "\n" + "Campaña: "+reporteSACForm.getCodigoPeriodo()
				+ "\n" + "Fecha desde: "+ DateUtil.convertDateToString("dd/MM/yyyy", reporteSACForm.getFecFactIni()) +"   Fecha hasta:"+ DateUtil.convertDateToString("dd/MM/yyyy", reporteSACForm.getFecFactFin())
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
	private String getSubTitulo(ReporteSACFaltanteNoAnunciadoNegocioForm reporteSACForm){
		
		String subTitulo = "";
		if (reporteSACForm.getNegocio()!= null && reporteSACForm.getNegocio().length>0)			
			subTitulo += "Negocio: "+ this.obtieneLista(reporteSACForm.getNegocio())+"\n";
		if (reporteSACForm.getCodigoRegion()!=null && reporteSACForm.getCodigoRegion().length>0)			
			subTitulo += "Region: "+this.obtieneLista(reporteSACForm.getCodigoRegion())+"\n";
		if (reporteSACForm.getCodigoZona()!=null && reporteSACForm.getCodigoZona().length>0)			
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
		
		ReporteSACFaltanteNoAnunciadoNegocioForm f = (ReporteSACFaltanteNoAnunciadoNegocioForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteriaOperacion = new HashMap();
		String codigoPais=this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo();
		criteriaOperacion.put("codigoPais", codigoPais);
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
		
		List listaUnidadNegocio = reporteService.getListaGenerico("getListaNegocio",criteriaOperacion);
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
        AjaxService aSvc = (AjaxService) getBean("ajaxService");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fechaInicio =aSvc.getFechaInicioPeriodoByPaisMarcaCanal(codigoPais, "T", "VD", f.getCodigoPeriodo()); //sdf.format(new Date(System.currentTimeMillis()));
		String fechaFin=aSvc.getFechaFinalPeriodoByPaisMarcaCanal(codigoPais, "T","VD", f.getCodigoPeriodo());
		f.setFecFactIniS(fechaInicio);
		f.setFecFactFinS(fechaFin);
		f.setFecFactIni(sdf.parse(f.getFecFactIniS()));
		f.setFecFactFin(sdf.parse(f.getFecFactFinS()));
		
		setMostrarZona(false);
		f.setTipoConsulta(null);

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
	 * Load zonas.
	 * 
	 * @param val
	 *            the val
	 */
	public void loadZonas(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadZonas");
		}
		try {
			String[] valor = (String[]) val.getNewValue();
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			this.siccZonaList = ajax
					.getZonasMultipleByPaisMarcaCanalRegion(
							this.mPantallaPrincipalBean.getCurrentCountry()
									.getCodigo(),
							Constants.CODIGO_MARCA_DEFAULT,
							Constants.CODIGO_CANAL_DEFAULT, valor,
							Constants.FORMATEAR_TODOS);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}
	
	/**
	 * Mostrar zonas.
	 * 
	 * @param val
	 *            the val
	 */
	public void mostrarZonas(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("mostrarZonas");
		}
		try {
			String valor = (String) val.getNewValue();
			if(StringUtils.equalsIgnoreCase("D", valor))
				this.setMostrarZona(true);
			else
				this.setMostrarZona(false);
			
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}


	/**
	 * Load fechas periodos.
	 */
	public void loadFechasPeriodos(String val){
		log.debug("loadFechasPeriodos...");
		
		ReporteSACFaltanteNoAnunciadoNegocioForm f = (ReporteSACFaltanteNoAnunciadoNegocioForm) this.formReporte;
		AjaxService ajaxService = (AjaxService)this.getBean("ajaxService");
		
		f.setCodigoPeriodo(val);
		
		try {
			f.setFecFactIni(DateUtil.convertStringToDate(Constants.DEFAULT_DATE_FORMAT, ajaxService.getFechaInicioPeriodoByPaisMarcaCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, f.getCodigoPeriodo())));
			f.setFecFactFin(DateUtil.convertStringToDate(Constants.DEFAULT_DATE_FORMAT, ajaxService.getFechaFinalPeriodoByPaisMarcaCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, f.getCodigoPeriodo())));
		} catch (ParseException e) {
			f.setFecFactIni(null);
			f.setFecFactFin(null);
			e.printStackTrace();
		}
	}
	
	@Override
	public String setValidarReporte() {
		if(log.isDebugEnabled()){
			log.debug("setValidarReporte...");
		}
			
		ReporteSACFaltanteNoAnunciadoNegocioForm form = (ReporteSACFaltanteNoAnunciadoNegocioForm) this.formReporte;
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
		
		if(form.getFecFactIni()==null){
			this.setMensajeAlertaDefault("'Incluye Fecha Desde' es un campo requerido");
			return this.getMensajeAlertaDefault();
		}
		
		if(form.getFecFactFin()==null){
			this.setMensajeAlertaDefault("'Incluye Fecha Hasta' es un campo requerido");
			return this.getMensajeAlertaDefault();
		}
		
		String fechaInicialPeriodo = ajaxService.getFechaInicioPeriodoByPaisMarcaCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, form.getCodigoPeriodo());
		String fechaFinalPeriodo = ajaxService.getFechaFinalPeriodoByPaisMarcaCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, form.getCodigoPeriodo());
		
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
	 * @return the mostrarZona
	 */
	public boolean isMostrarZona() {
		return mostrarZona;
	}

	/**
	 * @param mostrarZona the mostrarZona to set
	 */
	public void setMostrarZona(boolean mostrarZona) {
		this.mostrarZona = mostrarZona;
	}
}
