package biz.belcorp.ssicc.web.scsicc.action;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCOBDetalladoRebajasCarteraForm;



@ManagedBean
@SessionScoped
public class ReporteCOBDetalladoRebajasCarteraAction extends BaseReporteAbstractAction implements Serializable {
			
	private static final long serialVersionUID = 7496906172990857522L;

	private List siccRegionList;
    private LabelValue[] siccZonaList = {};
    private List siccSociedadList;
	private LabelValue[] siccEtapaDeudaList = {};
    
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
	    return new ReporteCOBDetalladoRebajasCarteraForm();
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
	   return "reporteCOBDetalladoRebajasCarteraDXLS";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
	   return null;
	}
	
	public void showZonasxRegion(ValueChangeEvent val){
		log.debug(">>showZonasxRegion ");
		log.debug("val: "+ ArrayUtils.toString(val.getNewValue()));
		ReporteCOBDetalladoRebajasCarteraForm form = (ReporteCOBDetalladoRebajasCarteraForm) this.formReporte;
		String[] regiones = (String []) val.getNewValue();		
	      
		if(!ArrayUtils.isEmpty(regiones)){
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			setSiccZonaList(aSvc.getZonasMultipleByPaisMarcaCanalRegion(form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, 
					Constants.CODIGO_CANAL_DEFAULT, regiones, Constants.FORMATO_TOTAL));
		}else{
			setSiccZonaList(null);
		}
		
		form.setZonaList(null);
	}
	
	

	public void showEtapasXSociedad(ValueChangeEvent val){
		log.debug(">>showEtapasXSociedad ");
		log.debug("val: "+ ArrayUtils.toString(val.getNewValue()));
		ReporteCOBDetalladoRebajasCarteraForm form = (ReporteCOBDetalladoRebajasCarteraForm) this.formReporte;
		String codSociedad = (String) val.getNewValue();		
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		if(StringUtils.isNotBlank(codSociedad)){
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			setSiccEtapaDeudaList(aSvc.getEtapasDeudaByPaisSociedad( pais.getCodigo(), codSociedad));
			
		 }else{
			 setSiccEtapaDeudaList(null);
		}
		
		form.setCodigoEtapaDeuda(null);
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		
		ReporteCOBDetalladoRebajasCarteraForm reporteCOBForm = (ReporteCOBDetalladoRebajasCarteraForm) this.formReporte;
		
		String condicionZonas = obtieneCondicion(reporteCOBForm.getZonaList(),
				"COD_ZONA", "'");
		String condicionRegion = obtieneCondicion(reporteCOBForm
				.getRegionList(), "COD_REGI", "'");
			
		String condicion = condicionZonas + condicionRegion;
								  		        						
		params.put("condicion", condicion);

				        				                      				
		log.debug(" Imprimiendo parámetros");
		log.debug(params);
		log.debug("Fin parámetros");
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		log.info("Entro a setViewAttributes");

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		
		ReporteCOBDetalladoRebajasCarteraForm f = (ReporteCOBDetalladoRebajasCarteraForm) this.formReporte;
		// parametros generales
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		String codigoPeriodoActual = this.getmPantallaPrincipalBean().getCodigoPeriodoActual();
		f.setCodigoPais(pais.getCodigo());
		
		if (StringUtils.isBlank(codigoPeriodoActual)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			String periodo = sdf.format(new Date(System.currentTimeMillis()));
			codigoPeriodoActual = periodo;
		}
		f.setCodigoPeriodoInicial(codigoPeriodoActual);
		f.setCodigoPeriodoFinal(codigoPeriodoActual);

		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		Map criteriaOperacion = new HashMap();
		
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		
		siccSociedadList = service.getSociedadesByCodigoPais(pais.getCodigo());
		
		siccRegionList = reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);
					
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

	/**
	 * @return the siccSociedadList
	 */
	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	/**
	 * @param siccSociedadList the siccSociedadList to set
	 */
	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}

	/**
	 * @return the siccEtapaDeudaList
	 */
	public LabelValue[] getSiccEtapaDeudaList() {
		return siccEtapaDeudaList;
	}

	/**
	 * @param siccEtapaDeudaList the siccEtapaDeudaList to set
	 */
	public void setSiccEtapaDeudaList(LabelValue[] siccEtapaDeudaList) {
		this.siccEtapaDeudaList = siccEtapaDeudaList;
	}

	

	

	
}