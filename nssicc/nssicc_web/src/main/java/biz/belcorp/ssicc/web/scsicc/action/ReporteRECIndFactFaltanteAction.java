package biz.belcorp.ssicc.web.scsicc.action;


import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteRECIndFactFaltanteForm;


/**
 * 
 * @author RRG
 * 
 */

@ManagedBean
@SessionScoped
public class ReporteRECIndFactFaltanteAction extends BaseReporteAbstractAction implements Serializable {
			
	private static final long serialVersionUID = 7496906172990857522L;

    private List siccRegionList;
    private LabelValue[] siccZonaList = {};

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
	    return new ReporteRECIndFactFaltanteForm();
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
	    return "ReporteRECIndFactFaltanteXLS";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
	    return null;
	}
	
	public void showZonasxRegion(ValueChangeEvent val){
		log.debug(">>showZonasxRegion ");
		log.debug("val: "+ ArrayUtils.toString(val.getNewValue()));
		ReporteRECIndFactFaltanteForm form = (ReporteRECIndFactFaltanteForm) this.formReporte;
		String region = (String) val.getNewValue();		
	      
		if(StringUtils.isNotBlank(region)){
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			setSiccZonaList(aSvc.getZonasMultipleByPaisMarcaCanalRegion(form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, 
					Constants.CODIGO_CANAL_DEFAULT, new String[]{region}, StringUtils.EMPTY));
		}else{
			
			setSiccZonaList(new LabelValue[]{new LabelValue("Todos", StringUtils.EMPTY)});
		}
		
		form.setZonaList(null);
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {

		ReporteRECIndFactFaltanteForm f = (ReporteRECIndFactFaltanteForm) this.formReporte;	
	
		params.put("CodigoPeriodoInicio",f.getCodigoPeriodoInicio());
		params.put("CodigoPeriodoFin",f.getCodigoPeriodoFin());
		params.put("ZonaList",f.getZonaList());
		params.put("titulo", getResourceMessage("reporteRECIndFactFaltanteForm.title"));																		

		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		log.info("Entro a setViewAttributes");
		
		ReporteRECIndFactFaltanteForm f = (ReporteRECIndFactFaltanteForm)this.formReporte;
		
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		
		f.setCodigoPeriodoInicio(null);
		f.setCodigoPeriodoFin(null);
		
		// parametros generales
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
        f.setCodigoPais(pais.getCodigo());
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais",pais.getCodigo());
		
		siccRegionList =  reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);
		
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
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	

	
}