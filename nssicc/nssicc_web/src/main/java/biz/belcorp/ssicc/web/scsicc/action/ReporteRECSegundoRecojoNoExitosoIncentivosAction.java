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
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteRECControlRecojosForm;
import biz.belcorp.ssicc.web.scsicc.form.ReporteRECSegundoRecojoNoExitosoIncentivosForm;

/**
 * @author <a href="">ghuertas@sigcomt.com</a>
 *
 */
@ManagedBean
@SessionScoped
public class ReporteRECSegundoRecojoNoExitosoIncentivosAction  extends BaseReporteAbstractAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4083817456599096814L;
	private String tipo;
	
	private List siccRegionList;
	private List siccZonaList;
	
	
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteRECSegundoRecojoNoExitosoIncentivosForm reporteForm = new ReporteRECSegundoRecojoNoExitosoIncentivosForm();
		return reporteForm;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteRECSegundoRecojoNoExitosoIncentivosXLS";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteRECSegundoRecojoNoExitosoIncentivosForm f = (ReporteRECSegundoRecojoNoExitosoIncentivosForm) formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		if(f.getDfechaInicio() != null ){
			f.setFechaInicio(DateUtil.convertDateToString(f.getDfechaInicio()));
		}
		if(f.getDfechaFin() != null ){
			f.setFechaFin(DateUtil.convertDateToString(f.getDfechaFin()));
		}
		//this.setVirtualizador(true);
		
		params.put("fechaInicio", f.getFechaInicio());
		params.put("fechaFin", f.getFechaFin());
		
		String condicionRegion = this.obtieneCondicion(f.getRegionList(), "ircb.cod_regi", "'");
		params.put("condicionRegion", condicionRegion);
		log.debug("condicionRegion"+condicionRegion.toString());
		
		String condicionZona = this.obtieneCondicion(f.getZonaList(), "ircb.cod_zona", "'");
		params.put("condicionZona", condicionZona);
		log.debug("condicionZona"+condicionZona.toString());
		
	//	super.prepareParameterMap(params, form, request);
		return params;
	}

	
	@Override
	protected void setViewAtributes() throws Exception {
		ReporteRECSegundoRecojoNoExitosoIncentivosForm f = (ReporteRECSegundoRecojoNoExitosoIncentivosForm) formReporte;		
		Map criteria = new HashMap();
		criteria.put("codigoPais", mPantallaPrincipalBean.getCurrentCountry().getCodigo());
	     
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		siccRegionList= reporteService.getListaGenerico("getRegionesByPais", criteria);
		siccZonaList=new ArrayList();
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		
	}

	
	/**
	 * Show zonasx region.
	 *
	 * @param val the val
	 */
	public void showZonasxRegion(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		log.debug(val.getNewValue().toString());
		if(StringUtils.isNotEmpty(val.getNewValue().toString()) 
				|| StringUtils.isNotBlank(val.getNewValue().toString()))
		{
			String[] regionListado = (String[])val.getNewValue();
			log.debug(regionListado.length);
			
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			if(regionListado.length>0){
				siccZonaList = Arrays.asList(ajax.getZonasMultipleByPaisMarcaCanalRegion(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), "T", "VD", regionListado,"T"));				                             
			}else{
				siccZonaList = new ArrayList();
			}
		}
	}
	
	
     /* (non-Javadoc)
     * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
     */
    public String setValidarReporte() {
    	ReporteRECSegundoRecojoNoExitosoIncentivosForm f = (ReporteRECSegundoRecojoNoExitosoIncentivosForm) this.formReporte;
	    if (f.getDfechaFin().compareTo(f.getDfechaInicio()) < 0) 
				return this.getResourceMessage("reporteRECSegundoRecojoNoExitosoIncentivosForm.validar.fechas");
			
	    return null;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	
}
