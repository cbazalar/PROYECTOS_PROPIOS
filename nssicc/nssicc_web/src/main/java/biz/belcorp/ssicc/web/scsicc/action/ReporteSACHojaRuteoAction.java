package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSACHojaRuteoForm;


/**
 * The Class ReporteSACFacturacionDetalleAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 28/08/2014
 */
@ManagedBean
@SessionScoped
public class ReporteSACHojaRuteoAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	private LabelValue[] siccRegionList = {};
	
	private LabelValue[] siccZonaList = {};

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSACHojaRuteoForm form = new ReporteSACHojaRuteoForm();
		return form;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteSACHojaRuteoForm form = (ReporteSACHojaRuteoForm)this.formReporte;
		
		if(log.isDebugEnabled()){
			log.debug(form.getFormatoExportacion());
		}
		
		if ("XLS".equals(form.getFormatoExportacion())) {
			return "";
		}else{
			return "reporteMaestroVertical";
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteSACHojaRuteoForm form = (ReporteSACHojaRuteoForm)this.formReporte;
		
		if(log.isDebugEnabled()){
			log.debug(form.getFormatoExportacion());
		}
		
		if ("PDF".equals(form.getFormatoExportacion())) {
			return "reporteSACHojaRuteo";
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
		ReporteSACHojaRuteoForm form = (ReporteSACHojaRuteoForm)this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		
		List listaRegiones = new ArrayList();

		listaRegiones = reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);
		
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
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("prepareParameterMap");			
		}
		
		ReporteSACHojaRuteoForm form = (ReporteSACHojaRuteoForm)this.formReporte;
		
		if(form.getFechaFacturacionDate() != null){
			form.setFechaFacturacion(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT , form.getFechaFacturacionDate()));
		}
		
		Map criteria = params;		
		
		String condicionZona = this.obtieneCondicion(form.getCodigoZona(), "ZON_ZONA.COD_ZONA", "'");
		//efenandezo 21/07/2009
		String condicionRegion = this.obtieneCondicion(form.getCodigoRegion(), "ZON_REGIO.COD_REGI", "'");
		
		criteria.put("periodo", form.getCodigoPeriodo());
		criteria.put("fechaFacturacion", form.getFechaFacturacion());
		
		//criteria.put("codigoRegion", reporteSACForm.getCodigoRegion()[0]);
		//efenandezo 21/07/2009
		criteria.put("codigoRegion", condicionRegion);
		criteria.put("codigoZona", condicionZona);
	
		params.put("titulo", this.getResourceMessage("reporteSACHojaRuteoForm.titulo") + form.getFechaFacturacion());
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
		ReporteSACHojaRuteoForm form = (ReporteSACHojaRuteoForm) this.formReporte;
		this.setSiccZonaList(ajaxService.getZonasMultipleByPaisMarcaCanalRegion(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, valores,Constants.TODAS));
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
