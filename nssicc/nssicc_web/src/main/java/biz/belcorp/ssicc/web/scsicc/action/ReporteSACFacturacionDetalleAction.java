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
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.men.ProcesoMENCargaMasivaInformacionMensajesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSACFacturacionDetalleForm;

/**
 * @author jpulido
 *
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteSACFacturacionDetalleAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	private LabelValue[] siccRegionList = {};
	
	private LabelValue[] siccAlmacenList = {};
	
	private LabelValue[] siccTipoOperacionList = {};
	
	private LabelValue[] siccZonaList = {};

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSACFacturacionDetalleForm form = new ReporteSACFacturacionDetalleForm();
		return form;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteSACFacturacionDetalleForm form = (ReporteSACFacturacionDetalleForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("PDF".equals(form.getFormatoExportacion())) {
			return "reporteSACFacturacionDetallePDF";
		}else{
			return "";
		}
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
		this.mostrarReporteOCSV = true;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		Map criteriaOperacion = new HashMap();
		ReporteSACFacturacionDetalleForm reporteForm = (ReporteSACFacturacionDetalleForm) formReporte;

		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		reporteForm.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), Constants.CODIGO_CANAL_DEFAULT));
		criteriaOperacion.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());

		List listaRegiones = reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);
		
		List listaAlmacen = service.getListaAlmacen(criteriaOperacion);
		
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
		
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteSACFacturacionDetalleForm form = (ReporteSACFacturacionDetalleForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(form.getFormatoExportacion())) {
			return "reporteSACFacturacionDetalleXLS";
		}else{
			return "reporteMaestroHorizontal";
		}
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteSACFacturacionDetalleForm form = (ReporteSACFacturacionDetalleForm) formReporte;
		ProcesoMENCargaMasivaInformacionMensajesService service = (ProcesoMENCargaMasivaInformacionMensajesService)getBean("spusicc.procesoMENCargaMasivaInformacionMensajesService");		
		
		String condicionRegion = this.obtieneCondicion(form.getRegionList(), "g.COD_REGI", "'");
		String condicionRegion1 = this.obtieneCondicion(form.getRegionList(), "z.COD_REGI", "'");
		String condicionZonas = this.obtieneCondicion(form.getZonaList(), "f.COD_ZONA", "'");
		String condicionAdicional = "";
		
		//-- Validar Tipo Pedido
		String tipoPedido = form.getTipoPedido();
		String cadenaTipoPedido = 
			" AND     decode( " + 
			"            (select sum(ind_oc) from ped_solic_cabec where soca_oid_soli_cabe=a.oid_soli_cabe), " +
            "			 0,decode( " +
            "			   (select count(ind_oc) from ped_solic_cabec where soca_oid_soli_cabe=a.oid_soli_cabe and modu_oid_modu=15), " +
            "        	   0,'RECLAMO', " +
            "              'INCENTIVO'), " +
            "      	  'NORMAL' )";
		
		if(StringUtils.equals(tipoPedido, "1"))
			condicionAdicional = cadenaTipoPedido + " = 'NORMAL'";
		else if(StringUtils.equals(tipoPedido, "2"))
			condicionAdicional = cadenaTipoPedido + " <> 'NORMAL'";
		else
			condicionAdicional = " ";
		
		String condicion = condicionRegion + condicionZonas + condicionAdicional;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		String almacen = form.getAlmacen();
		
		Map criteria = params;
		criteria.put("codigoPeriodo", form.getCodigoPeriodo());
		String oidPais = reporteService.getOidString("getOidPaisByCodigoPais",criteria);
		criteria.put("oidPais", oidPais);
		String oidPeriodo = reporteService.getOidString("getOidPeriodoByCodigoPeriodo", criteria);
		criteria.put("oidPeriodo", oidPeriodo);
		String fechaFencimiento = reporteService.getOidString("getFechaVencimientoByOidPeriodo", criteria);
		
		criteria.put("codigoRegion", form.getRegionList());
		criteria.put("codigoZona", form.getZonaList());

		params.put("serviceCMIMS", service);
		params.put("almacen", almacen);
		params.put("oidPeriodo", oidPeriodo);
		params.put("tipoPedido", StringUtils.trim(tipoPedido));
		params.put("condicion", condicion);
		params.put("codigoRegion", form.getRegionList());
		params.put("codigoZona", form.getZonaList());
		params.put("tipoPedido", StringUtils.trim(tipoPedido));
		params.put("condicionRegion", condicionRegion1);
		params.put("formatoExportacion", form.getFormatoExportacion());
		
		
		form.setFechaFacturacion(DateUtil.convertDateToString(form.getFechaFacturacionDate()));
		String fechaFacturacion = form.getFechaFacturacion();
		if (StringUtils.isEmpty(form.getFechaFacturacion())){
			fechaFacturacion = null;
		}
		params.put("fechaFacturacion", fechaFacturacion);
		params.put("NroReporte", "");
		params.put("titulo", this.getReportResourceMessage("reporteSACFacturacionDetalleForm.titulo"));
		
		form.setBeforeExecuteReporte(true);
		
		return params;
	}
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanConstructorService()
	 */
	protected String devuelveBeanReporteService(){
		return "reportes.reporteSACFacturacionDetalleService";
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
		try {
			
			String[] valores = (String[]) val.getNewValue();
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			this.setSiccZonaList(ajaxService.getZonasMultipleByPaisMarcaCanalRegion(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),  Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, valores, Constants.OPCION_TODOS));

		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	
	}

	/**
	 * @return
	 */
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 */
	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return
	 */
	public LabelValue[] getSiccAlmacenList() {
		return siccAlmacenList;
	}

	/**
	 * @param siccAlmacenList
	 */
	public void setSiccAlmacenList(LabelValue[] siccAlmacenList) {
		this.siccAlmacenList = siccAlmacenList;
	}

	/**
	 * @return
	 */
	public LabelValue[] getSiccTipoOperacionList() {
		return siccTipoOperacionList;
	}

	/**
	 * @param siccTipoOperacionList
	 */
	public void setSiccTipoOperacionList(LabelValue[] siccTipoOperacionList) {
		this.siccTipoOperacionList = siccTipoOperacionList;
	}

	/**
	 * @return
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}		
}
