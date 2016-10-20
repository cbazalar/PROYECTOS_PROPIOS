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

/**
 * @author <a href="">ghuertas@sigcomt.com</a>
 *
 */
@ManagedBean
@SessionScoped
public class ReporteRECControlRecojosAction  extends BaseReporteAbstractAction {
	
	private String tipo;
	
	private List siccRegionList;
	private List siccZonaList;
	
	
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteRECControlRecojosForm reporteForm = new ReporteRECControlRecojosForm();
		return reporteForm;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteRECControlRecojosXLS";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteRECControlRecojosForm f = (ReporteRECControlRecojosForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		

		tipo = f.getTipo();
		
		if(StringUtils.equalsIgnoreCase(tipo, Constants.REC_TIPO_FECHA_GENERACION)){
			params.put("fechaIngrReco", "ircb.fec_ingr");
			params.put("fechaIngrReco2", "ircb.fec_ing2");
		}else{
			if(StringUtils.equalsIgnoreCase(tipo, Constants.REC_TIPO_FECHA_RECOJO)){
				params.put("fechaIngrReco", "ircb.FEC_RECO");
				params.put("fechaIngrReco2", "ircb.FEC_REC2");
			}
		}
		
		if(f.getDfechaInicio() != null ){
			f.setFechaInicio(DateUtil.convertDateToString(f.getDfechaInicio()));
		}
		if(f.getDfechaFin() != null ){
			f.setFechaFin(DateUtil.convertDateToString(f.getDfechaFin()));
		}
		
		params.put("fechaInicio", f.getFechaInicio());
		params.put("fechaFin", f.getFechaFin());
		
		String condicionRegion = this.obtieneCondicion(f.getRegionList(), "ircb.cod_regi", "'");
		params.put("condicionRegion", condicionRegion);
		log.debug("condicionRegion"+condicionRegion.toString());
		
		String condicionZona = this.obtieneCondicion(f.getZonaList(), "ircb.cod_zona", "'");
		params.put("condicionZona", condicionZona);
		log.debug("condicionZona"+condicionZona.toString());
		return params;
	}

	
	@Override
	protected void setViewAtributes() throws Exception {
		ReporteRECControlRecojosForm reporteSICForm = (ReporteRECControlRecojosForm) this.formReporte;
		Map criteria = new HashMap();
		criteria.put("codigoPais", this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais", criteria);
		this.siccZonaList = new ArrayList();
		
		reporteSICForm.setFechaInicio("");
		reporteSICForm.setFechaFin("");
		
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		
		LabelValue valorIni =  new LabelValue("Todos", "");
		this.siccZonaList.add(valorIni);
		
	}

	
	/**
	 * Show zonasx region.
	 *
	 * @param val the val
	 */

	public void showZonasxRegion(ValueChangeEvent val)
	{
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
    	ReporteRECControlRecojosForm f = (ReporteRECControlRecojosForm) this.formReporte;
	    if (f.getDfechaFin().compareTo(f.getDfechaInicio()) < 0) 
				return this.getResourceMessage("reporteRECControlRecojosForm.validar.fechas");
			
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
