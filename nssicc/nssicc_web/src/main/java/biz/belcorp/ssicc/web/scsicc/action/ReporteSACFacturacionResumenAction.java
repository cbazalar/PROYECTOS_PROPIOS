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
import biz.belcorp.ssicc.web.scsicc.form.ReporteSACFacturacionResumenForm;


/**
 * The Class ReporteSACFacturacionDetalleAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 28/08/2014
 */
@ManagedBean
@SessionScoped
public class ReporteSACFacturacionResumenAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	private LabelValue[] siccRegionList = {};
	
	private LabelValue[] siccAlmacenList = {};
	
	private LabelValue[] siccTipoOperacionList = {};
	
	private LabelValue[] siccZonaList = {};
	
	private LabelValue[] stoOrigenesByDocumento = {};

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSACFacturacionResumenForm form = new ReporteSACFacturacionResumenForm();
		return form;
	}
	
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteSACFacturacionResumenForm form = (ReporteSACFacturacionResumenForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		
		if ("XLS".equals(form.getFormatoExportacion())) {
			if(StringUtils.equalsIgnoreCase(form.getVistaReporte(),"Facturacion")){
				return "reporteSACFacturacionResumenHojaUnoXLS";
			}else{
				if(StringUtils.equalsIgnoreCase(form.getVistaReporte(),"Premios")){
					return "reporteSACFacturacionResumenPremiosXLS";
				}else{
					return "reporteSACFacturacionResumenFueraCajaXLS";
				}
			}
		}else{
			return "reporteMaestroHorizontal";
		}
	}
	
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteSACFacturacionResumenForm form = (ReporteSACFacturacionResumenForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		
		return "reporteSACFacturacionResumen" + form.getReporte();
		
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");			
		}
		
		this.mostrarReporteXLS = true;
		
		ReporteSACFacturacionResumenForm form = (ReporteSACFacturacionResumenForm)this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");

		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("tipoDocumento", Constants.STO_TIPO_DOCUMENTO_OCC);		 
		criteriaOperacion.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		criteriaOperacion.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteriaOperacion.put("indicadorActiva", Constants.ESTADO_ACTIVO);
		
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteriaOperacion);
			
		form.setFechaProceso(controlFacturacion.getFechaProceso());
		//form.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		
		if(StringUtils.isNotBlank(form.getFechaProceso()) || StringUtils.isNotEmpty(form.getFechaProceso())){
			form.setFechaProcesoDate(DateUtil.convertStringToDate(Constants.DEFAULT_DATE_FORMAT, form.getFechaProceso()));
		}

		List listaRegiones = reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);
		
		List listaAlmacen = reporteService.getListaAlmacen(criteriaOperacion);
		
		List listaOrigenes = reporteService.getOrigenSTOByTipoDocumento(criteriaOperacion);
		
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
		
		if(listaAlmacen.size()>0){
			this.siccAlmacenList = new LabelValue[listaAlmacen.size()];
			int i = 0;
			for(Object object : listaAlmacen){
				LabelValue labelValue = new LabelValue();
				labelValue.setLabel(((Base)object).getDescripcion());
				labelValue.setValue(((Base)object).getCodigo());
				this.getSiccAlmacenList()[i] = labelValue;
				i++;
			}
		}
		
		if(listaOrigenes.size()>0){
			this.stoOrigenesByDocumento = new LabelValue[listaOrigenes.size()];
			int i = 0;
			for(Object object : listaOrigenes){
				LabelValue labelValue = new LabelValue();
				labelValue.setLabel(((Base)object).getDescripcion());
				labelValue.setValue(((Base)object).getCodigo());
				this.getStoOrigenesByDocumento()[i] = labelValue;
				i++;
			}
		}
	}


	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("prepareParameterMap");
		}
		ReporteSACFacturacionResumenForm form = (ReporteSACFacturacionResumenForm) this.formReporte;			
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");		
		
		String titulo= new String();
		titulo="\n";
		
		String codigoPeriodo=form.getCodigoPeriodo();
		
		String fechaProceso = form.getFechaProceso();
		String condicionRegion = this.obtieneCondicion(form.getRegionList(), "REG.COD_REGI", "'");
		
		if(StringUtils.equalsIgnoreCase(form.getVistaReporte(), "Facturacion")){			
			form.setReporte("HojaUnoPDF");
			titulo+="Facturacion";
		}else{
			if(StringUtils.equalsIgnoreCase(form.getVistaReporte(),"Premios")){
				form.setReporte("PremiosPDF");
				titulo+="Premios";
			}else{
				form.setReporte("FueraCajaPDF");
				titulo+="Fuera de Caja";

			}
		}
		
		if(form.getFechaProcesoDate() != null){
			form.setFechaProceso(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT,form.getFechaProcesoDate()));
		}
				
		params.put("codigoPeriodo", codigoPeriodo);
		params.put("fechaProceso", form.getFechaProceso());
		params.put("condicionRegion", condicionRegion);
		params.put("NroReporte", "");
		params.put("titulo", getReportResourceMessage("reporteSACFacturacionResumenForm.title")+titulo);
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
		String[] valores = (String[]) val.getNewValue();
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		ReporteSACFacturacionResumenForm form = (ReporteSACFacturacionResumenForm) this.formReporte;
		this.setSiccZonaList(ajaxService.getZonasMultipleByPaisMarcaCanalRegion(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),  Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, valores, Constants.OPCION_TODOS));
	}

	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public LabelValue[] getSiccAlmacenList() {
		return siccAlmacenList;
	}

	public void setSiccAlmacenList(LabelValue[] siccAlmacenList) {
		this.siccAlmacenList = siccAlmacenList;
	}

	public LabelValue[] getSiccTipoOperacionList() {
		return siccTipoOperacionList;
	}

	public void setSiccTipoOperacionList(LabelValue[] siccTipoOperacionList) {
		this.siccTipoOperacionList = siccTipoOperacionList;
	}

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	public LabelValue[] getStoOrigenesByDocumento() {
		return stoOrigenesByDocumento;
	}

	public void setStoOrigenesByDocumento(LabelValue[] stoOrigenesByDocumento) {
		this.stoOrigenesByDocumento = stoOrigenesByDocumento;
	}		
}
