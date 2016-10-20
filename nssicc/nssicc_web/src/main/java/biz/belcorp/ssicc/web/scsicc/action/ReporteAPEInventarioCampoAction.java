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
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.men.ProcesoMENCargaMasivaInformacionMensajesService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteAPEDistribucionCdrsRecojosForm;
import biz.belcorp.ssicc.web.scsicc.form.ReporteAPEInventarioCampoForm;


/**
 * @author <a href="">Gonzalo Javier Huertas Agurto</a>
 */
@ManagedBean
@SessionScoped
public class ReporteAPEInventarioCampoAction extends BaseReporteAbstractAction implements Serializable {
			
	private static final long serialVersionUID = 7496906172990857522L;

	private List siccRegionList;
    private LabelValue[] siccZonaList = {};
    private String tipoReporte;
    private List apeCentroAcopioList;
    private List apeCiaTransporteList;
    
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
	    return new ReporteAPEInventarioCampoForm();
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteAPEInventarioCampo"+tipoReporte+"XLS";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
	   return null;
	}
	
	protected String devuelveBeanReporteService() {
		return "reportes.reporteAPEInventarioCampoService";
	}
	
	public void showZonasxRegion(ValueChangeEvent val){
		log.debug(">>showZonasxRegion ");
		log.debug("val: "+ ArrayUtils.toString(val.getNewValue()));
		ReporteAPEInventarioCampoForm form = (ReporteAPEInventarioCampoForm) this.formReporte;
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
	
		Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
		
		ReporteAPEInventarioCampoForm reporteAPEForm = (ReporteAPEInventarioCampoForm) this.formReporte;

		ProcesoMENCargaMasivaInformacionMensajesService service = (ProcesoMENCargaMasivaInformacionMensajesService) 
		getBean("spusicc.procesoMENCargaMasivaInformacionMensajesService");
		
		
		Map criteria = params;

		String condicionRegion = this.obtieneCondicion(reporteAPEForm.getCodigoRegion(), "REG.COD_REGI", "'");
		String condicionZona = this.obtieneCondicion(reporteAPEForm.getCodigoZona(),"ZON.COD_ZONA", "'");
		
		criteria.put("codigoPais", reporteAPEForm.getCodigoPais());
		
		//this.setGenerateTabsXLS(true);
		
		tipoReporte = reporteAPEForm.getTipoReporte();

		params.put("serviceCMIMS", service);
		params.put("NroReporte", "");
		params.put("tipoReporte", tipoReporte);
		params.put("region",condicionRegion);
		params.put("zona",condicionZona);
		params.put("fechaInicioFacturacion", (reporteAPEForm.getFechaFacturacionInicio() == null) ? StringUtils.EMPTY : DateFormatUtils.format(reporteAPEForm.getFechaFacturacionInicio(), "dd/MM/yyyy"));
		params.put("fechaFinFacturacion", (reporteAPEForm.getFechaFacturacionFin() == null) ? StringUtils.EMPTY : DateFormatUtils.format(reporteAPEForm.getFechaFacturacionFin(), "dd/MM/yyyy"));
		params.put("centroAcopio", reporteAPEForm.getCentroAcopio());
		params.put("companiaTransporte", reporteAPEForm.getCompanhiaTransporte());
		params.put("titulo", this.getResourceMessage("reporteAPEInventarioCampoForm.title"));
		params.put("usuario", usuario.getLogin());
		
		params.put("regionListMultiple", reporteAPEForm.getCodigoRegion());
		int tamanio = reporteAPEForm.getCodigoZona().length;
		if(tamanio==0){
			reporteAPEForm.setCodigoZona(null);
		}
		params.put("zonaListMultiple", reporteAPEForm.getCodigoZona());
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		
       log.info("ReporteAPEInventarioCampoAction - setViewAttributes");
		

		this.mostrarReporteXLS = true;
		this.mostrarReporteOCSV = true;
		this.mostrarReportePDF = false;
		
		ReporteAPEInventarioCampoForm f = (ReporteAPEInventarioCampoForm) this.formReporte;
		
		// parametros generales
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
	

		f.setCodigoPais(pais.getCodigo());
		
		

		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		siccRegionList = reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);

	    
	    apeCentroAcopioList = reporteService.getListaGenerico("getListaCentrosDeAcopio",criteriaOperacion);
	    apeCiaTransporteList = reporteService.getListaGenerico("getListaCiaDeTransportes",criteriaOperacion);
	    
	    criteriaOperacion.put("codigoPais", pais.getCodigo());
	    criteriaOperacion.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
	    criteriaOperacion.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente 
	
    	MantenimientoOCRPedidoControlFacturacionService service =(MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteriaOperacion);

		f.setFechaFacturacionInicio(DateUtil.convertStringToDate(controlFacturacion.getFechaProceso()));
		f.setFechaFacturacionFin(DateUtil.convertStringToDate(controlFacturacion.getFechaProceso()));

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
	 * @return the apeCentroAcopioList
	 */
	public List getApeCentroAcopioList() {
		return apeCentroAcopioList;
	}

	/**
	 * @param apeCentroAcopioList the apeCentroAcopioList to set
	 */
	public void setApeCentroAcopioList(List apeCentroAcopioList) {
		this.apeCentroAcopioList = apeCentroAcopioList;
	}

	/**
	 * @return the apeCiaTransporteList
	 */
	public List getApeCiaTransporteList() {
		return apeCiaTransporteList;
	}

	/**
	 * @param apeCiaTransporteList the apeCiaTransporteList to set
	 */
	public void setApeCiaTransporteList(List apeCiaTransporteList) {
		this.apeCiaTransporteList = apeCiaTransporteList;
	}	

    
}