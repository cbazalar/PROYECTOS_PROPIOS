/*
 * 
 */
package biz.belcorp.ssicc.web.scsicc.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.ParameterContructorService;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteOCRPrimerosPedidosForm;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteOCRPrimerosPedidosAction.
 *
 * @author Belcorp
 * @version 1.0
 * 12/11/2014
 */
@ManagedBean
@SessionScoped
public class ReporteOCRPrimerosPedidosAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;	
	
	/** The sicc periodo list. */
	private List siccPeriodoList;
	
	/** The sicc region list. */
	private LabelValue[] siccRegionList = {};
	
	/** The sicc zona list. */
	private LabelValue[] siccZonaList = {};
	
	/** The mostrar agrupacion. */
	boolean mostrarAgrupacion;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteOCRPrimerosPedidosForm form = new ReporteOCRPrimerosPedidosForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteOCRPrimerosPedidosForm form = (ReporteOCRPrimerosPedidosForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(form.getFormatoExportacion())){
			return "OCRPrimerosPedidosXLS";
		}else{
			return "reporteMaestroHorizontal";
		}		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteOCRPrimerosPedidos";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteOCRPrimerosPedidosForm form = (ReporteOCRPrimerosPedidosForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		form.setFechaReporte(DateUtil.convertDateToString(DateUtil.getDatePattern(), form.getFechaReporteDate()));
		form.setFechaFin(DateUtil.convertDateToString(DateUtil.getDatePattern(), form.getFechaFinDate()));
		
		Map criteria = params;
		criteria.put("codigoPeriodo", form.getCodigoPeriodo());
		String oidPeriodo = reporteService.getOidString("getOidPeriodoByCodigoPeriodo", criteria);
		params.put("oidPeriodo", oidPeriodo);
		ClassPathResource resource = new ClassPathResource(((ParameterContructorService)this.getBeanService("reportes.parameterContructorService")).getJasperDirectorio() + "subReporteOCRPrimerosPedidos" + JASPER_EXTENSION);
		ClassPathResource resource1 = new ClassPathResource(((ParameterContructorService)this.getBeanService("reportes.parameterContructorService")).getJasperDirectorio() + "subReporteOCRPrimerosPedidosZonas" + JASPER_EXTENSION);
			
		params.put("SUBREPORT_DIR1", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource.getPath() )));
		params.put("SUBREPORT_DIR2", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource1.getPath() )));
		
		
		Map filter = new HashMap();
		filter.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		filter.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
		filter.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
		MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");        
        PedidoControlFacturacion controlFacturacion = serviceFact.getControlFacturacionById(filter);
        //Obtiene la campaña activa
        String campanhaActiva = controlFacturacion.getCodigoPeriodo();
        if(campanhaActiva!=null && campanhaActiva.equals(form.getCodigoPeriodo()))
        	params.put("tabla", Constants.SAC_TABLA_CONSOLIDADO);
        else
        	params.put("tabla", Constants.SAC_TABLA_HISTORICO);
		//-----------------------------------------
		
		String condicionZonas = obtieneCondicion(form.getCodigoZona(),"xxx.COD_ZONA", "'");
		String condicionRegion = obtieneCondicion(form.getCodigoRegion(), "xxx.COD_REGI", "'");
		String condicion = condicionRegion + condicionZonas;

		String condicionZonas1 = obtieneCondicion(form.getCodigoZona(), "ZON_ZONA.COD_ZONA", "'");
		String condicionRegion1 = obtieneCondicion(form.getCodigoRegion(), "ZON_REGIO.COD_REGI", "'");
		String condicion1 = condicionRegion1 + condicionZonas1;

		params.put("NroReporte", this.getReportResourceMessage("reporteOCRPrimerosPedidosForm.numero.reporte"));
		params.put("condicion", condicion);
		params.put("condicion1", condicion1);
		params.put("usuario.login", this.getmPantallaPrincipalBean().getCurrentUser().getLogin());
		params.put("titulo", this.getReportResourceMessage("reporteOCRPrimerosPedidosForm.titulo")	+ " " + StringUtils.substring(form.getCodigoPeriodo(), 0, 4)
				+ "/" + StringUtils.substring(form.getCodigoPeriodo(), 4, 6));
		params.put("fechaReporte", form.getFechaReporte());
		params.put("fechaFin", form.getFechaFin());
		return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {		
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}		
		this.mostrarReporteXLS = true;
		
		// Carga de los Periodos
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		List lista = reporteService.getListaPeriodosByBasCtrlFact(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), null);
		
		if(lista.size() > 0){			
			setSiccPeriodoList(lista);			
		}
		valoresByDefault();
		loadRegiones();
	}
	
	/**
	 * Load regiones.
	 */
	private void loadRegiones() {
		log.debug("loadRegiones...");
		ReporteOCRPrimerosPedidosForm form = (ReporteOCRPrimerosPedidosForm) this.formReporte;
		AjaxService ajaxService = (AjaxService)this.getBean("ajaxService");
		//setSiccRegionList(ajaxService.getListaPeriodosByBasCtrlFact(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), form.getCodigoPeriodo() ,Constants.FORMATEAR_TODOS));
		setSiccRegionList(ajaxService.getRegionesByPeriodoBasCtrlFact(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), form.getCodigoPeriodo() ,Constants.FORMATEAR_TODOS));
		//this.loadZonas();
	}
	
	/**
	 * Valores by default.
	 */
	private void valoresByDefault(){
		log.debug("valoresByDefault...");
		ReporteOCRPrimerosPedidosForm form = (ReporteOCRPrimerosPedidosForm) this.formReporte;
		Date valorFecha = new Date();
		form.setFechaReporteDate(valorFecha);
		form.setFechaFinDate(valorFecha);
		if(this.getSiccPeriodoList().size()>0){			
			form.setCodigoPeriodo(((Base)this.getSiccPeriodoList().get(0)).getCodigo());
		}
	}
	
	/**
	 * Load zonas.
	 */
	private void loadZonas(){
		log.debug("loadZonas...");
		ReporteOCRPrimerosPedidosForm form = (ReporteOCRPrimerosPedidosForm) this.formReporte;
		AjaxService ajaxService = (AjaxService)this.getBean("ajaxService");
		this.setSiccZonaList(ajaxService.getZonasByMultiplePeriodoBasCtrlFact(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), form.getCodigoPeriodo(), form.getCodigoRegion(), Constants.OPCION_TODOS));
	}	
	
	/**
	 * Cambia zonas by region.
	 *
	 * @param val the val
	 */
	public void cambiaZonasByRegion(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("cambiaZonas...");
		}
		log.debug(val.getNewValue().toString());
		ReporteOCRPrimerosPedidosForm form = (ReporteOCRPrimerosPedidosForm) this.formReporte;
		if(StringUtils.isNotEmpty(val.getNewValue().toString()) || StringUtils.isNotBlank(val.getNewValue().toString())){
			String[] valores = (String[]) val.getNewValue();
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			setSiccZonaList(ajaxService.getZonasByMultiplePeriodoBasCtrlFact(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),form.getCodigoPeriodo(),valores,Constants.FORMATEAR_TODOS));
		}
	}
	
	/**
	 * Cambia region by tipo reporte.
	 *
	 * @param val the val
	 */
	public void cambiaRegionByTipoReporte(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("cambiaRegionByTipoReporte...");
		}
		String valor = (String) val.getNewValue();
			if(StringUtils.isNotBlank(valor) || StringUtils.isNotEmpty(valor)){
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			ReporteOCRPrimerosPedidosForm form = (ReporteOCRPrimerosPedidosForm) this.formReporte;
			form.setTipoReporte(valor);
			if (StringUtils.equals(form.getTipoReporte(), "1")){
				this.setSiccRegionList(ajaxService.getRegionesByPeriodoBasCtrlFact(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), form.getCodigoPeriodo() ,Constants.FORMATEAR_TODOS));
			}else{
		    	this.setSiccRegionList(ajaxService.getRegionesByPeriodoIntEviPerioRegio(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),form.getCodigoPeriodo(),Constants.FORMATEAR_TODOS));
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	@Override
	public String setValidarReporte() {
		if(log.isDebugEnabled()){
			log.debug("setValidarReporte");
		}
		ReporteOCRPrimerosPedidosForm form = (ReporteOCRPrimerosPedidosForm) this.formReporte;
		if(form.getFechaReporteDate()!=null && form.getFechaFinDate()!=null){
			if(form.getFechaReporteDate().compareTo(form.getFechaFinDate())>0){
				this.setMensajeAlertaDefault(this.getResourceMessage("errors.compare.dates"));
				return this.getMensajeAlertaDefault();
			}
		}
		return null;
	}

	/**
	 * Gets the sicc region list.
	 *
	 * @return the sicc region list
	 */
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * Sets the sicc region list.
	 *
	 * @param siccRegionList the new sicc region list
	 */
	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * Gets the sicc zona list.
	 *
	 * @return the sicc zona list
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * Sets the sicc zona list.
	 *
	 * @param siccZonaList the new sicc zona list
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * Gets the sicc periodo list.
	 *
	 * @return the sicc periodo list
	 */
	public List getSiccPeriodoList() {
		return siccPeriodoList;
	}

	/**
	 * Sets the sicc periodo list.
	 *
	 * @param siccPeriodoList the new sicc periodo list
	 */
	public void setSiccPeriodoList(List siccPeriodoList) {
		this.siccPeriodoList = siccPeriodoList;
	}

	/**
	 * Checks if is mostrar agrupacion.
	 *
	 * @return true, if is mostrar agrupacion
	 */
	public boolean isMostrarAgrupacion() {
		return mostrarAgrupacion;
	}

	/**
	 * Sets the mostrar agrupacion.
	 *
	 * @param mostrarAgrupacion the new mostrar agrupacion
	 */
	public void setMostrarAgrupacion(boolean mostrarAgrupacion) {
		this.mostrarAgrupacion = mostrarAgrupacion;
	}
}
