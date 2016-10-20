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

import org.apache.commons.collections.MapUtils;
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
import biz.belcorp.ssicc.web.scsicc.form.ReporteCCCLevantamientosDeDeudaForm;


/**
 * 
 * @author <a href="">JFA</a>
 *
 */
@ManagedBean
@SessionScoped
public class ReporteCCCLevantamientosDeDeudaAction extends BaseReporteAbstractAction implements Serializable {
			
	private static final long serialVersionUID = 7496906172990857522L;
	
	private String tipoVista;	
    private List siccSociedadList;
    private List siccRegionList;
    private LabelValue[] siccZonaList = {};


	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCCCLevantamientosDeDeudaForm form = new ReporteCCCLevantamientosDeDeudaForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		
		return "reporteCCCLevantamientosDeDeuda" +   tipoVista  + "XLS";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}
	
	public void showZonasxRegion(ValueChangeEvent val){
		log.debug(">>showZonasxRegion ");
		log.debug("val: "+ ArrayUtils.toString(val.getNewValue()));
		ReporteCCCLevantamientosDeDeudaForm form = (ReporteCCCLevantamientosDeDeudaForm) this.formReporte;
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

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteCCCLevantamientosDeDeudaForm reporteCCCForm = (ReporteCCCLevantamientosDeDeudaForm) this.formReporte;
		
		this.tipoVista = reporteCCCForm.getTipoVista();						
		
		String condicionZonas = obtieneCondicion(reporteCCCForm.getZonaList(),
				"COD_ZONA", "'");
		String condicionRegion = obtieneCondicion(reporteCCCForm
				.getRegionList(), "COD_REGI", "'");
		reporteCCCForm.setBeforeExecuteReporte(true);	
		
		String condicion = condicionZonas + condicionRegion;		
		params.put("condicion", condicion);
		        				                      				
		log.debug(" Imprimiendo parámetros");
		log.debug(params);
		log.debug("Fin parámetros");
		 
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		log.info("Entro a ReporteCCCLevantamientosDeDeudaAction - setViewAttributes");

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		
		ReporteCCCLevantamientosDeDeudaForm reporteCCCForm = (ReporteCCCLevantamientosDeDeudaForm) this.formReporte;
		
		// parametros generales
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		String periodoActual =  this.getmPantallaPrincipalBean().getCodigoPeriodoActual();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));
		
		reporteCCCForm.setCodigoPeriodoInicial(periodoActual);
		if (StringUtils.isEmpty(reporteCCCForm.getCodigoPeriodoInicial()))
			reporteCCCForm.setCodigoPeriodoInicial(periodo);
		
		reporteCCCForm.setCodigoPeriodoFinal(periodo);
		if (StringUtils.isEmpty(reporteCCCForm.getCodigoPeriodoFinal()))
			reporteCCCForm.setCodigoPeriodoFinal(periodo);
		
		reporteCCCForm.setCodigoPais(pais.getCodigo());
		
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		
		siccSociedadList = service.getSociedadesByCodigoPais(pais.getCodigo());
		siccRegionList = reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);
		
	}

	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}

	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public String getTipoVista() {
		return tipoVista;
	}

	public void setTipoVista(String tipoVista) {
		this.tipoVista = tipoVista;
	}

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	
}