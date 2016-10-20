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
import biz.belcorp.ssicc.service.spusicc.men.ProcesoMENCargaMasivaInformacionMensajesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteAPEArmadoMgpedxdiaForm;


/**
 * 
 * @author <a href="">Gonzalo Javier Huertas Agurto</a>
 */
@ManagedBean
@SessionScoped
public class ReporteAPEArmadoMgpedxdiaAction extends BaseReporteAbstractAction implements Serializable {
			
	private static final long serialVersionUID = 7496906172990857522L;

	private List siccRegionList;
    private LabelValue[] siccZonaList = {};

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
	    return new ReporteAPEArmadoMgpedxdiaForm();
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteAPEArmadoMgpedxdiaForm form =  (ReporteAPEArmadoMgpedxdiaForm) this.formReporte;
		
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(form.getFormatoExportacion()))
			return "reporteAPEArmadoMgpedxdiaXLS";
		else
		   return "reporteMaestroHorizontal";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {

	   return null;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanConstructorService()
	 */
	protected String devuelveBeanReporteService(){
		return "reportes.reporteAPEArmadoMgpedxdiaService";
	}
	
	public void showZonasxRegion(ValueChangeEvent val){
		log.debug(">>showZonasxRegion ");
		log.debug("val: "+ ArrayUtils.toString(val.getNewValue()));
		ReporteAPEArmadoMgpedxdiaForm form = (ReporteAPEArmadoMgpedxdiaForm) this.formReporte;
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
	
		ReporteAPEArmadoMgpedxdiaForm reporteAPEForm = (ReporteAPEArmadoMgpedxdiaForm) this.formReporte;
		
		ProcesoMENCargaMasivaInformacionMensajesService service = (ProcesoMENCargaMasivaInformacionMensajesService) 
		getBean("spusicc.procesoMENCargaMasivaInformacionMensajesService");
		Map criteria = params;

		String condicionRegion = this.obtieneCondicion(reporteAPEForm.getCodigoRegion(), "ZRE.COD_REGI", "'");
		String condicionZona = this.obtieneCondicion(reporteAPEForm.getCodigoZona(),"ZON_ZONA.COD_ZONA", "'");
	
		criteria.put("codigoPeriodo", reporteAPEForm.getCodigoPeriodo());
		criteria.put("codigoRegion", reporteAPEForm.getCodigoRegion());
		criteria.put("codigoZona", reporteAPEForm.getCodigoZona());
		criteria.put("codigoPais",reporteAPEForm.getCodigoPais());
		
		
		params.put("serviceCMIMS", service);
		params.put("NroReporte", "");
		
		params.put("condicion", condicionRegion + condicionZona);
		params.put("titulo", getResourceMessage("reporteAPEArmadoMgpedxdiaForm.title"));
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		log.info("ReporteAPEArmadoMgpedxdiaAction - setViewAttributes");

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		this.mostrarReporteOCSV = true;
		
		ReporteAPEArmadoMgpedxdiaForm form =  (ReporteAPEArmadoMgpedxdiaForm) this.formReporte;
		
		// parametros generales
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		//	Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
		
		
			String codigoPeriodoActual = this.getmPantallaPrincipalBean().getCodigoPeriodoActual();
			form.setCodigoPais(pais.getCodigo());
		
		if (StringUtils.isBlank(codigoPeriodoActual)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			String periodo = sdf.format(new Date(System.currentTimeMillis()));
			codigoPeriodoActual = periodo;
		}
		form.setCodigoPeriodo(codigoPeriodoActual);

		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
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