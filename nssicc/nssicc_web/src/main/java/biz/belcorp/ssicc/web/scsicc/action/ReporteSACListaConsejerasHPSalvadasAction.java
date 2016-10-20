package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSACListaConsejerasHPSalvadasForm;

/**
 * The Class ReporteSACPedidoZonaAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 10/09/2014
 */
@ManagedBean
@SessionScoped
public class ReporteSACListaConsejerasHPSalvadasAction extends BaseReporteAbstractAction {

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
		ReporteSACListaConsejerasHPSalvadasForm form = new ReporteSACListaConsejerasHPSalvadasForm();
		return form;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteSACListaConsejerasHPSalvadasForm form = (ReporteSACListaConsejerasHPSalvadasForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		
		if ("XLS".equals(form.getFormatoExportacion())) {
			return "reporteSACListaConsejerasHPSalvadasXLS";
		}else{
			return "reporteMaestroHorizontal";
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteSACListaConsejerasHPSalvadasForm form = (ReporteSACListaConsejerasHPSalvadasForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		return "";
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
		this.mostrarReportePDF = false;
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		ReporteSACListaConsejerasHPSalvadasForm form = (ReporteSACListaConsejerasHPSalvadasForm)this.formReporte;
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());		
		
		List listaRegiones = reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);		
		
		if(listaRegiones.size()>0){
			this.siccRegionList = new LabelValue[listaRegiones.size()];
			int i = 0;
		
			for(Object object : listaRegiones){
				LabelValue labelValue = new LabelValue();
				labelValue.setLabel(((Base)object).getDescripcion());
				labelValue.setValue(((Base)object).getCodigo());
				this.getSiccRegionList()[i] = labelValue;
				i++;
			}
		}
		
		criteriaOperacion.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteriaOperacion.put("indicadorActiva", Constants.ESTADO_ACTIVO);
		
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteriaOperacion);
		form.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("prepareParameterMap...");
		}
		ReporteSACListaConsejerasHPSalvadasForm form = (ReporteSACListaConsejerasHPSalvadasForm) this.formReporte;
		//StringBuilder condicionRegion = new StringBuilder(obtieneCondicion(form.getRegionList(), "RE.COD_REGI", "'"));
		
		
		StringBuilder codigoPais= new StringBuilder(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		//StringBuilder condicionZona = new StringBuilder(obtieneCondicion(form.getZonaList(), "ZZ.COD_ZONA", "'"));
		
		String codigoPeriodo=form.getCodigoPeriodo();
		if(codigoPeriodo.compareToIgnoreCase("")==0)
			codigoPeriodo=null;

		if(form.getFechaProcesoDate() != null){
			form.setFechaProceso(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT,form.getFechaProcesoDate()));
		}
		
		params.put("codigoPeriodo", codigoPeriodo);
		params.put("codigoPais", codigoPais);
		
		if(!StringUtils.equals(form.getRegionList(), "Todos")){
			params.put("condicionRegion", " AND RE.COD_REGI = '" + form.getRegionList() + "'");
		}else{
			params.put("condicionRegion", "");
		}
		
		if(!StringUtils.equals(form.getZonaList(), null)){
			params.put("condicionZona", " AND ZZ.COD_ZONA = '" + form.getZonaList() + "'");
		}else{
			params.put("condicionZona", "");
		}
		
		params.put("fechaProceso", form.getFechaProceso());
		params.put("NroReporte", "");
		params.put("titulo", getReportResourceMessage("reporteSACListaConsejerasHPSalvadasForm.title"));
		return params;
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
		String[] valores = new String[1];
		valores[0] = (String) val.getNewValue();
				
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		ReporteSACListaConsejerasHPSalvadasForm form = (ReporteSACListaConsejerasHPSalvadasForm) this.formReporte;
		this.setSiccZonaList(ajaxService.getZonasMultipleByPaisMarcaCanalRegion(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),  Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, valores, Constants.FORMATEAR_TODOS));
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
}
