package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReportePEDPedidosFacturadosCampanaForm;


/**
 * The Class ReportePEDPedidosFacturadosCampanaAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 30/05/2014
 */
@ManagedBean
@SessionScoped
public class ReportePEDPedidosFacturadosCampanaAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The sicc region list. */
	private LabelValue[] siccRegionList = {};
	
	/** The sicc zona list. */
	private LabelValue[] siccZonaList = {};
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReportePEDPedidosFacturadosCampanaForm form = new ReportePEDPedidosFacturadosCampanaForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {		
		return "reportePEDPedidosFacturadosCampanaXLS";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {;
		if(log.isDebugEnabled()){
			log.debug("prepareParameterMap");
		}
		ReportePEDPedidosFacturadosCampanaForm f = (ReportePEDPedidosFacturadosCampanaForm)this.formReporte;
		log.debug(f.getFormatoExportacion());
		
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		// Condicional que virtualiza el reporte si es PDF
		
		Map criteria = params;
				
		String codigoRegion = (String) criteria.get("codigoRegion");
		String codigoZona = (String) criteria.get("codigoZona");
		String fechaInicial =(String)criteria.get("fechaInicial");
		String fechaFinal = (String)criteria.get("fechaFinal");
		
		String condicionZonas = "";
		String condicionRegion = "";
		String condicionFechaInicial = "";
		String condicionFechaFinal = "";
		
		if(StringUtils.isNotEmpty(fechaInicial))
//			condicionFechaInicial = " AND a.FEC_FACT >= '"+DateUtil.convertDateToString("dd/MM/yyyy", f.getFechaInicial())+"' ";
			condicionFechaInicial = " AND a.FEC_FACT >= to_date('"+DateUtil.convertDateToString("dd/MM/yyyy", f.getFechaInicial())+"','dd/MM/yyyy') ";
		
		if(StringUtils.isNotEmpty(fechaFinal))
//			condicionFechaFinal = " AND a.FEC_FACT <= '"+DateUtil.convertDateToString("dd/MM/yyyy", f.getFechaFinal())+"' ";
			condicionFechaFinal = " AND a.FEC_FACT <= to_date('"+DateUtil.convertDateToString("dd/MM/yyyy", f.getFechaFinal())+"','dd/MM/yyyy') ";
		
		if ("Todos".equals(codigoRegion) || StringUtils.isEmpty(codigoRegion)) {
			codigoRegion = null;
			params.put("codigoRegion", codigoRegion);
		} else {
			condicionRegion = obtieneCondicion(f.getCodigoRegion(), "e.COD_REGI", "'");
		}

		if ("Todos".equals(codigoZona) || StringUtils.isEmpty(codigoZona)) {
			codigoZona = null;
			params.put("codigoZona", codigoZona);
		} else {
			condicionZonas = obtieneCondicion(f.getCodigoZona(),"d.COD_ZONA", "'");
		}
		
		String condicion = condicionFechaInicial+ condicionFechaFinal + condicionRegion + condicionZonas;
		params.put("condicion", condicion);
				
		return params;
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("ReporteSICFacturacionAction - setViewAtributes");
		}
		this.mostrarReportePDF = false;
		this.mostrarReporteXLS = true;
		/*InterfazSiCCService service = (InterfazSiCCService) this.getBean("sisicc.interfazSiCCService");
		ReporteService reportService = (ReporteService) this.getBean("scsicc.reporteService");*/
		
		AjaxService ajaxService = (AjaxService)getBean("ajaxService");
		
		ReportePEDPedidosFacturadosCampanaForm f = ( ReportePEDPedidosFacturadosCampanaForm) formReporte;
		
		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
	     
		MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService) this.getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceFact.getControlFacturacionById(criteriaPeriodo);
		
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
				
		f.setFechaInicial(DateUtil.convertStringToDate("dd/MM/yyyy", ajaxService.getFechaInicioPeriodoByPaisMarcaCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, f.getCodigoPeriodo())));
		f.setFechaFinal(DateUtil.convertStringToDate("dd/MM/yyyy", ajaxService.getFechaFinalPeriodoByPaisMarcaCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, f.getCodigoPeriodo())));
		
		this.siccRegionList = ajaxService.getRegionesByPaisMarcaCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),Constants.CODIGO_MARCA_DEFAULT,Constants.CODIGO_CANAL_DEFAULT);
		
	}
	
	/**
	 * Load zonas.
	 *
	 * @param val the val
	 */
	public void loadZonas(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("loadZonas");
		}
		String[] valor = (String[]) val.getNewValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		ReportePEDPedidosFacturadosCampanaForm reporteSICForm = (ReportePEDPedidosFacturadosCampanaForm) this.formReporte;
		
		this.siccZonaList = ajax.getZonasMultipleByPaisMarcaCanalRegion(this.mPantallaPrincipalBean.getCurrentCountry().getCodigo(), 
				Constants.CODIGO_MARCA_DEFAULT, 
				Constants.CODIGO_CANAL_DEFAULT, 
				valor, 
				Constants.FORMATEAR_TODOS);
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
	
	public void loadFechasPeriodo(String valor) {
		if (log.isDebugEnabled()) {
			log.debug("loadFechasPeriodo");
		}
		ReportePEDPedidosFacturadosCampanaForm reporteForm = (ReportePEDPedidosFacturadosCampanaForm) this.formReporte;
		try {
			if (StringUtils.isNotBlank(valor) && valor.length() == 6) {
				AjaxService ajaxService = (AjaxService) getBean("ajaxService");
				String fechaDesde = ajaxService
						.getFechaInicioPeriodoByPaisMarcaCanal(this
								.getmPantallaPrincipalBean().getCurrentCountry()
								.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
								Constants.CODIGO_CANAL_DEFAULT, valor);
				
				String fechaHasta = ajaxService.getFechaFinalPeriodoByPaisMarcaCanal(this
								.getmPantallaPrincipalBean().getCurrentCountry()
								.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
								Constants.CODIGO_CANAL_DEFAULT, valor);
				
				reporteForm.setFechaInicial(DateUtil.convertStringToDate(fechaDesde));
				reporteForm.setFechaFinal(DateUtil.convertStringToDate(fechaHasta));
			}
		}
		catch (Exception e) {
			
		}
		
	}
}
