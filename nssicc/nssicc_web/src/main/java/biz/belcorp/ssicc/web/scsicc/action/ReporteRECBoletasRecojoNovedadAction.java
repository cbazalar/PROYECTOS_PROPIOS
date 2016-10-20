/**
 * 
 */
package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteRECBoletasRecojoNovedadForm;


/**
 * The Class ReporteRECBoletasRecojoNovedadAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 18/03/2014
 */
@SuppressWarnings("rawtypes")
@ManagedBean
@SessionScoped
public class ReporteRECBoletasRecojoNovedadAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 7313115511530013121L;
	private String formatoReporte;
	private List siccRegionList;
	private List siccZonaList;
	private List siccResultadoList;
	private List siccMotivoList;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteRECBoletasRecojoNovedadForm form = new ReporteRECBoletasRecojoNovedadForm();
		return form;
	}
	/**
	 * Método que configura los atributos iniciales en el FormReporte
	 *
	 */
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@SuppressWarnings("unchecked")
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteRECBoletasRecojoNovedadAction.setViewAtributes' method");
		}		
		this.mostrarReporteOCSV = false;
		this.mostrarReporteXLSX = false;
		this.mostrarReporteCSV = false;
		this.mostrarReportePDF = false;
		this.mostrarReporteXLS = true;
		this.mostrarReporteOJXLSX = false;
		this.mostrarReporteOOXLSX = false;		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		setSiccRegionList(reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion));
		setSiccZonaList(new ArrayList());
		setSiccResultadoList(reporteService.getResultadoList());
		setSiccMotivoList(reporteService.getMotivoList());
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		this.setFormatoReporte(((ReporteRECBoletasRecojoNovedadForm)this.formReporte).getFormatoExportacion()); 
		if ("XLS".equals(getFormatoReporte())){
			return "reporteRECBoletasRecojoNovedadXLS";
		} else {
			return "reporteMaestroHorizontal";
		}
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		//return "reporteRECBoletasRecojoNovedadPDF";
		return "";
	}
	
	/**
	 * Método que devuelve 
	 *
	 */
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	protected Map prepareParameterMap(Map params) throws Exception {	
		ReporteRECBoletasRecojoNovedadForm reporteRECForm = (ReporteRECBoletasRecojoNovedadForm) this.formReporte;
		this.setFormatoExportacion(reporteRECForm.getFormatoExportacion());
		params.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		params.put("codigoPeriodo", reporteRECForm.getCodigoPeriodo());
		params.put("region", this.obtieneCondicion(reporteRECForm.getRegionList(), "c.cod_regi", "'"));
		params.put("zona", this.obtieneCondicion(reporteRECForm.getZonaList(), "c.cod_ZONA", "'"));
		params.put("fechaProceso", reporteRECForm.getFechaProceso());
		if (!reporteRECForm.getCodigoResultado().equals("")){
			params.put("resultado", StringUtils.split(reporteRECForm.getCodigoResultado(),"/")[0]);
			params.put("descripcionResultado", StringUtils.split(reporteRECForm.getCodigoResultado(),"/")[1]);
			if (reporteRECForm.getCodigoMotivo() != null) {
				if ((!reporteRECForm.getCodigoMotivo().equals("")) && (StringUtils.split(reporteRECForm.getCodigoResultado(),"/")[1].equals("NO EXITOSA"))){
					params.put("motivo", StringUtils.split(reporteRECForm.getCodigoMotivo(), "/")[0]);		
					params.put("descripcionMotivo", StringUtils.split(reporteRECForm.getCodigoMotivo(), "/")[1]);
				} else {
					params.put("motivo",null);		
					params.put("descripcionMotivo", null);
				}
			} else {
				params.put("motivo",null);		
				params.put("descripcionMotivo", null);
			}
		} else{
			params.put("resultado",null);
		    params.put("descripcionResultado", null);
		}		
		params.put("titulo", this.getReportResourceMessage("reporteRECBoletasRecojoNovedadForm.title"));
		log.debug("Imprimiendo parámetros");
		log.debug(params);
		log.debug("Fin parámetros");
		return params;
	}	
	
	/**
	 * Show zonasx region.
	 *
	 * @param val the val
	 */
	/**
	 * Método que carga muestra zonas por región
	 * @param val
	 */
	public void showZonasxRegion(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		log.debug(val.getNewValue().toString());
		if(StringUtils.isNotEmpty(val.getNewValue().toString()) 
				|| StringUtils.isNotBlank(val.getNewValue().toString())){
			String[] regionListado = (String[])val.getNewValue();
			log.debug(regionListado.length);
			
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			if(regionListado.length>0){
				setSiccZonaList(Arrays.asList(ajax.getZonasMultipleByPaisMarcaCanalRegion(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), "T", "VD", regionListado,"T")));			
			}else{
				setSiccZonaList(new ArrayList());
			}
		}
	}
	/**
	 * @return the formatoReporte
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}
	/**
	 * @param formatoReporte the formatoReporte to set
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
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
	public List getSiccZonaList() {
		return siccZonaList;
	}
	/**
	 * @param siccZonaList the siccZonaList to set
	 */
	public void setSiccZonaList(List siccZonaList) {
		this.siccZonaList = siccZonaList;
	}
	/**
	 * @return the siccResultadoList
	 */
	public List getSiccResultadoList() {
		return siccResultadoList;
	}
	/**
	 * @param siccResultadoList the siccResultadoList to set
	 */
	public void setSiccResultadoList(List siccResultadoList) {
		this.siccResultadoList = siccResultadoList;
	}
	/**
	 * @return the siccMotivoList
	 */
	public List getSiccMotivoList() {
		return siccMotivoList;
	}
	/**
	 * @param siccMotivoList the siccMotivoList to set
	 */
	public void setSiccMotivoList(List siccMotivoList) {
		this.siccMotivoList = siccMotivoList;
	}
	



}
