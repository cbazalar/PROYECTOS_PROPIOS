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
import org.apache.commons.lang3.time.DateFormatUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteAPEDistribucionCdrsRecojosForm;


/**
 * 
 * @author <a href="">Gonzalo Javier Huertas Agurto</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteAPEDistribucionCdrsRecojosAction extends BaseReporteAbstractAction implements Serializable {
			
	private static final long serialVersionUID = 7496906172990857522L;

	private List siccRegionList;
    private LabelValue[] siccZonaList = {};

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
	    return new ReporteAPEDistribucionCdrsRecojosForm();
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		
	   return "reporteAPERecojosXLS";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {

	   return null;
	}
	
	public void showZonasxRegion(ValueChangeEvent val){
		log.debug(">>showZonasxRegion ");
		log.debug("val: "+ ArrayUtils.toString(val.getNewValue()));
		ReporteAPEDistribucionCdrsRecojosForm form = (ReporteAPEDistribucionCdrsRecojosForm) this.formReporte;
		String[] regiones = (String []) val.getNewValue();		
	      
		if(!ArrayUtils.isEmpty(regiones)){
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			setSiccZonaList(aSvc.getZonasMultipleByPaisMarcaCanalRegion(form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, 
					Constants.CODIGO_CANAL_DEFAULT, regiones, Constants.FORMATO_TOTAL));
		}else{
			setSiccZonaList(null);
		}
		
		form.setCodigoZona(null);
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		
		ReporteAPEDistribucionCdrsRecojosForm reporteAPEForm = (ReporteAPEDistribucionCdrsRecojosForm) this.formReporte;
		
		Map criteria = params;
		String condicion = "";
		String condCampania = "";

		String condicionRegion = this.obtieneCondicion(reporteAPEForm.getCodigoRegion(), "R.COD_REGI", "'");
		String condicionZona = this.obtieneCondicion(reporteAPEForm.getCodigoZona(),"Z.COD_ZONA", "'");
		
		if(StringUtils.isNotBlank(reporteAPEForm.getPeriodoGeneracion())){
			condCampania = " AND  C.COD_PERI_PROC = " + reporteAPEForm.getPeriodoGeneracion();  
		}else{
			condCampania = "";
		}
		
		if(StringUtils.isNotBlank(reporteAPEForm.getPeriodoDespacho())){
		        condicion = " AND (" + reporteAPEForm.getPeriodoDespacho() + " = C.COD_PERI_DESP1 OR " + reporteAPEForm.getPeriodoDespacho() +  " = C.COD_PERI_DESP2 ) ";
		}else{
			condicion = "";
		}

		params.put("NroReporte", "");
		params.put("region", condicionRegion + condicionZona);
		params.put("zona", condicionZona);
		params.put("numeroRecojo", StringUtils.isBlank(reporteAPEForm.getNumeroRecojo())?null:reporteAPEForm.getNumeroRecojo());
		params.put("fechaInicioFacturacion", reporteAPEForm.getFechaFacturacionInicio() == null ? null: DateFormatUtils.format(reporteAPEForm.getFechaFacturacionInicio(),"dd/MM/yyyy"));
		params.put("fechaFinFacturacion", reporteAPEForm.getFechaFacturacionFin() == null? null: DateFormatUtils.format(reporteAPEForm.getFechaFacturacionFin(),"dd/MM/yyyy"));
		params.put("titulo", this.getResourceMessage("reporteAPEDistribucionCdrsRecojosForm.title"));
		criteria.put("campania", condCampania);
		params.put("condicion", condicion);		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		
		log.info("ReporteAPEDistribucionCdrsRecojosAction - setViewAttributes");
		
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		
		ReporteAPEDistribucionCdrsRecojosForm form = (ReporteAPEDistribucionCdrsRecojosForm) this.formReporte;
		
		// parametros generales
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();

		String codigoPeriodoActual = this.getmPantallaPrincipalBean().getCodigoPeriodoActual();
		form.setCodigoPais(pais.getCodigo());
		
		if (StringUtils.isBlank(codigoPeriodoActual)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			String periodo = sdf.format(new Date(System.currentTimeMillis()));
			codigoPeriodoActual = periodo;
		}
		form.setPeriodoDespacho(codigoPeriodoActual);
		form.setPeriodoGeneracion(codigoPeriodoActual);
		

		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
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

	

	
}