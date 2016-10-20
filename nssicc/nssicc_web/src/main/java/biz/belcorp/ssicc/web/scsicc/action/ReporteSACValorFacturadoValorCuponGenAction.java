/*
 * 
 */
package biz.belcorp.ssicc.web.scsicc.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.spusicc.pej.MantenimientoPEJProgramaEjecutivasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSACValorFacturadoValorCuponGenForm;


// TODO: Auto-generated Javadoc
/**
 * The Class ReporteSACFacturacionDetalleAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 28/08/2014
 */
@ManagedBean
@SessionScoped
public class ReporteSACValorFacturadoValorCuponGenAction extends BaseReporteAbstractAction {

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
		ReporteSACValorFacturadoValorCuponGenForm form = new ReporteSACValorFacturadoValorCuponGenForm();
		return form;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteSACValorFacturadoValorCuponGenForm form = (ReporteSACValorFacturadoValorCuponGenForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		
		if ("XLS".equals(form.getFormatoExportacion())) {
			return "reporteSACValorFacturadoValorCuponGenXLS";
		}
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
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
		
		ReporteSACValorFacturadoValorCuponGenForm form = (ReporteSACValorFacturadoValorCuponGenForm)this.formReporte;
		MantenimientoPEJProgramaEjecutivasService service = (MantenimientoPEJProgramaEjecutivasService)getBean("spusicc.mantenimientoPEJProgramaEjecutivasService");
		GenericoService genericoService1 = (GenericoService) getBean("genericoService");
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		
		Map result = service.getPeriodoDefault();
		
		String codigoPeriodo = (String) result.get("codigoPeriodo"); 
		String fecha = (String) result.get("fechaProceso");
		
		form.setCodigoPeriodo(codigoPeriodo);
		form.setFechaDate(DateUtil.convertStringToDate(Constants.DEFAULT_DATE_FORMAT, fecha));
		form.setMontoDiferencia("0");
	
		ParametroPais parametroPais1 = new ParametroPais();
		parametroPais1.setCodigoPais(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		parametroPais1.setCodigoSistema(Constants.SAC_CODIGO_SISTEMA);
		parametroPais1.setCodigoParametro(Constants.SAC_CODIGO_PARAMETRO_MD);
		parametroPais1.setNombreParametro(Constants.SAC_NOMBRE_PARAMETRO_MD);
		parametroPais1.setIndicadorActivo(Constants.ESTADO_ACTIVO);
		
		List lstParametros1 = genericoService1.getParametrosPais(parametroPais1);
		
		if(lstParametros1 != null && lstParametros1.size() > 0){			
			ParametroPais ps = (ParametroPais)lstParametros1.get(0);
			form.setMontoDiferencia(ps.getValorParametro());
		}
		
		this.siccRegionList = ajaxService.getRegionesByPeriodoBasCtrlFact(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),
																				form.getCodigoPeriodo(),
																				Constants.TODAS);

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("prepareParameterMap");
		}
		
		ReporteSACValorFacturadoValorCuponGenForm form = (ReporteSACValorFacturadoValorCuponGenForm) this.formReporte;
		
		String codigoRegionG = obtieneCondicion(form.getCodigoRegion(), "e.cod_regi", "'");
		String codigoZonaG = obtieneCondicion(form.getCodigoZona(), "d.cod_zona", "'");
		
		if(form.getFechaDate() != null){
			form.setFecha(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, form.getFechaDate()));
		}
		
		params.put("codigoRegion", codigoRegionG);
		params.put("codigoZona", codigoZonaG);
		params.put("codigoPeriodo", form.getCodigoPeriodo());
		params.put("fecha", form.getFecha());
		params.put("montoDif", form.getMontoDiferencia());
		params.put("titulo", "");
		
		return params;
	}
	
	/**
	 * Cambia zonas by region.
	 *
	 * @param val the val
	 */
	public void cambiaZonasByRegion(ValueChangeEvent val){
		ReporteSACValorFacturadoValorCuponGenForm form = (ReporteSACValorFacturadoValorCuponGenForm) this.formReporte;
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");		
		String[] valores = (String[]) val.getNewValue();			
		this.siccZonaList=ajaxService.getZonasByMultiplePeriodoBasCtrlFact(form.getCodigoPais(),form.getCodigoPeriodo(), valores, "T");
		
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
}
