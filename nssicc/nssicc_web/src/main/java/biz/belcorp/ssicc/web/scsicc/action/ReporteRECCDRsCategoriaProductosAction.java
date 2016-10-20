package biz.belcorp.ssicc.web.scsicc.action;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteRECCDRsCategoriaProductosForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaProductoSearchAction;


/**
 * 
 * @author <a href="">Jorge Velasquez</a>
 */
@ManagedBean
@SessionScoped
public class ReporteRECCDRsCategoriaProductosAction extends BaseReporteAbstractAction implements Serializable {
			
	private static final long serialVersionUID = 7496906172990857522L;

	private String secuencial = "0";
	private List siccMarcaList;
	private List siccRegionList;
	private List siccOperacionesList;
	private LabelValue[] siccTipoOperacionList;
	private LabelValue[] siccZonaList;
	private LabelValue[] siccTerritorioList;
    
 	/*Agregar estos atributos para el popup Producto*/
	private static final String POPUP_SACPRODUCTO = "SACPRODUCTO";
	@ManagedProperty(value="#{busquedaProductoSearchAction}")
	private BusquedaProductoSearchAction busquedaProductoSearchAction;
	private boolean mostrarPopupProducto;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setInvocarPopup(java.lang.String)
	 */
	@Override
	protected void setInvocarPopup(String accion) {

		this.mostrarProcesoBatch = false;
		this.mostrarPopupProducto = true;
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setAceptarPopup(javax.faces.event.ActionEvent, java.lang.String)
	 */
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopup' method");
		}
		this.mostrarProcesoBatch = true;
		ReporteRECCDRsCategoriaProductosForm busquedaForm = (ReporteRECCDRsCategoriaProductosForm)this.formReporte;
		
		if (accion.equals(POPUP_SACPRODUCTO)) {
			this.mostrarPopupProducto = false;
			
			this.busquedaProductoSearchAction.verificarRegistro(event);
			if (this.busquedaProductoSearchAction.isSeleccionoRegistro()) {
				
				Map prodMap = (Map) this.busquedaProductoSearchAction.getBeanRegistroSeleccionado(); 

				busquedaForm.setCodigoSap(MapUtils.getString(prodMap, "codigoSap"));
				this.busquedaProductoSearchAction.setBeanRegistroSeleccionado(null);
			}
		}	
		
		this.formReporte =  busquedaForm;

	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setSalirPopup()
	 */
	@Override
	protected void setSalirPopup() {
		this.mostrarProcesoBatch = true;
		this.mostrarPopupProducto = false;
		this.busquedaProductoSearchAction.setBeanRegistroSeleccionado(null);
	}
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
	    return new ReporteRECCDRsCategoriaProductosForm();
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
	   return "reporteRECCDRsCategoriaProductosXLS";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
	   return null;
	}
	
	public void showTipoOperaXOpera(ValueChangeEvent val){
		log.debug(">>showTipoOperaXOpera ");
		log.debug("val: "+ ArrayUtils.toString(val.getNewValue()));
		ReporteRECCDRsCategoriaProductosForm form = (ReporteRECCDRsCategoriaProductosForm) this.formReporte;
		String[] operaciones = (String []) val.getNewValue();		
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		
		if(!ArrayUtils.isEmpty(operaciones)){
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			ArrayList tmp = new ArrayList();
			
			for (String ope : operaciones) {
				tmp.add(ope);
			}
			
			siccTipoOperacionList = aSvc.getTiposOperaMultipleByOperaxCodigoOid( pais.getCodigo(),tmp, "");      
      		
		  }else{
			setSiccTipoOperacionList(null);
		}
		form.setTipoOperacionList(null);
	}
	
	public void showZonasxRegion(ValueChangeEvent val){
		log.debug(">>showZonasxRegion ");
		log.debug("val: "+ ArrayUtils.toString(val.getNewValue()));
		ReporteRECCDRsCategoriaProductosForm form = (ReporteRECCDRsCategoriaProductosForm) this.formReporte;
		String[] regiones = (String []) val.getNewValue();		
	      
		if(!ArrayUtils.isEmpty(regiones)){
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			setSiccZonaList(aSvc.getZonasMultipleByPaisMarcaCanalRegion(form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, 
					Constants.CODIGO_CANAL_DEFAULT, regiones, Constants.FORMATO_TOTAL));
		}else{
			setSiccZonaList(null);
		}
		
		setSiccTerritorioList(null);
		form.setZonaList(null);
	}

	
	public void showTerritorioXZonas(ValueChangeEvent val){
		log.debug(">>showTerritorioXZonas ");
		log.debug("val: "+ ArrayUtils.toString(val.getNewValue()));
		ReporteRECCDRsCategoriaProductosForm form = (ReporteRECCDRsCategoriaProductosForm) this.formReporte;
		String[] zonas = (String []) val.getNewValue();		
	      
		if(!ArrayUtils.isEmpty(zonas)){
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			List cRegionList = Arrays.asList(form.getRegionList());
			List cZonaList = Arrays.asList(zonas);
			setSiccTerritorioList(aSvc.getTerritoriosMultipleByPaisMarcaCanalRegionZona( form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, 
							Constants.CODIGO_CANAL_DEFAULT, cRegionList,cZonaList,Constants.FORMATO_TOTAL));
							
		}else{
			setSiccTerritorioList(null);
		}
		
		form.setTerritorioList(null);
	}
	
	
	@Override
	public String setValidarReporte() {
		ReporteRECCDRsCategoriaProductosForm form = (ReporteRECCDRsCategoriaProductosForm)this.formReporte;
		AjaxService ajaxService  =  (AjaxService) getBean("ajaxService");
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		
		Integer periodoIncial = Integer.parseInt(form.getCodigoPeriodoInicial());
		Integer periodoFinal = Integer.parseInt(form.getCodigoPeriodoFinal());

		Date fechaDesde=form.getFechaDesde();
		Date fechaHasta=form.getFechaHasta();
		
		if (StringUtils.isNotBlank(form.getCodigoPeriodoInicial()) && StringUtils.isNotBlank(form.getCodigoPeriodoFinal())) {
			if (periodoIncial > periodoFinal){
				String mensaje = getResourceMessage("reporteRECVentasMensualesForm.validacionPeriodo");
				return mensaje;
			}
		}
		if(fechaDesde != null && fechaHasta != null){
			if(fechaDesde.compareTo(fechaHasta) > 0){
				String mensaje = getResourceMessage("reporteRECVentasMensualesForm.validacionFechas");
				return mensaje;
			}
			
		}
/*
		String codigoPeriodo = service.getPeriodoDefaultByPaisCanal(form.getCodigoPais(),Constants.CODIGO_CANAL_DEFAULT);
	  
		try {
			String strFechaInicial = ajaxService.getFechaInicioPeriodoByPaisMarcaCanal(form.getCodigoPais(),Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, codigoPeriodo); 		
			Date fechaInicioPeriodoInicial = DateUtils.parseDate(strFechaInicial, "dd/MM/yyyy"); 
			String strFechaFinal = ajaxService.getFechaFinalPeriodoByPaisMarcaCanal(form.getCodigoPais(),Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, codigoPeriodo);
			Date fechaFinalPeriodoInicial = DateUtils.parseDate(strFechaFinal, "dd/MM/yyyy");
			
		   if (fechaDesde != null){
	    	
	    	int resultado  = fechaDesde.compareTo(fechaInicioPeriodoInicial);    

	    	if ( resultado == -1) 
	        {	
	            return "La fecha inicial debe de encontrarse en los siguientes intervalos ".concat(strFechaInicial)
	            		.concat(" - ").concat(strFechaFinal) ; 
	        }	    	
	    }
		
		    if (fechaHasta != null){

			    	int resultado  = fechaFinalPeriodoInicial.compareTo(fechaHasta);    

			    	if ( resultado == -1) 
			        {		
			        	return "La fecha final debe de encontrarse en los siguientes intervalos ".concat(strFechaInicial).concat(" - ").concat(strFechaFinal) ; 
			        }  
			 }
		} catch (ParseException e) {}
	*/
      return null;
	}
	
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {

		ReporteRECCDRsCategoriaProductosForm reporteRECForm = (ReporteRECCDRsCategoriaProductosForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		params.put("secuencial", "");

		Map criteria = new HashMap();
		criteria.put("codigoPeriodo",reporteRECForm.getCodigoPeriodoInicial());
		String oidPeriodoInicial = reporteService.getOidString("getOidPeriodoByCodigoPeriodo",criteria);
		
		criteria.put("codigoPeriodo",reporteRECForm.getCodigoPeriodoFinal());
		String oidPeriodoFinal = reporteService.getOidString("getOidPeriodoByCodigoPeriodo", criteria);
		
		params.put("periodoRegistroInicial", oidPeriodoInicial);
		params.put("periodoRegistroFinal", oidPeriodoFinal);
		
		String condicionTerritorioList = obtieneCondicion(
				reporteRECForm.getTerritorioList(), "zt.cod_terr", "'");

		String condicionTipoOperacionList = obtieneCondicion(
				reporteRECForm.getTipoOperacionList(), "top.oid_tipo_oper", "'");

		String condicionZonaList = obtieneCondicion(
				reporteRECForm.getZonaList(), "zz.cod_zona", "'");

		String condicionRegionList = obtieneCondicion(
				reporteRECForm.getRegionList(), "zr.cod_regi", "'");
		
		String condicionMarcaList = obtieneCondicionIN(reporteRECForm.getMarcaList(), "", "'");
		String condicionWhere = "";

		if (!StringUtils.isBlank(condicionMarcaList)) {
			condicionMarcaList = " Where a.cod_marc_prod " + condicionMarcaList;
		}
		
		String codigoProducto ="";
		if(!reporteRECForm.getCodigoSap().equals("")){
			codigoProducto ="and prod.cod_sap = '"+ reporteRECForm.getCodigoSap()+"' ";
		}
		
		String fechaDesde = "";
		if (reporteRECForm.getFechaDesde() != null) {
			String dFechaDesde = DateFormatUtils.format(reporteRECForm.getFechaDesde(),"dd/MM/yyyy");
			fechaDesde = "and r.fec_ingr >= to_date('"+dFechaDesde+"','dd/MM/yyyy') ";
		}
		
		String fechaHasta = "";
		if (reporteRECForm.getFechaHasta()!=null) {
			String dFechaHasta = DateFormatUtils.format(reporteRECForm.getFechaHasta(),"dd/MM/yyyy");
			fechaHasta="and r.fec_ingr <= to_date('"+dFechaHasta+"','dd/MM/yyyy') ";
		}
		
		params.put("secuencial", secuencial);
		params.put("codigoSap", reporteRECForm.getCodigoSap());
		params.put("territorioList", condicionTerritorioList);
		params.put("tipoOperacionList", condicionTipoOperacionList);
		params.put("zonaList",condicionZonaList);
		params.put("regionList", condicionRegionList);
		params.put("codigoSap", codigoProducto);
		params.put("fechaDesde", fechaDesde);
		params.put("fechaHasta", fechaHasta);
		params.put("condicionWhere", condicionWhere);
		params.put("marcaList", condicionMarcaList);
		params.put(
				"titulo",
				getReportResourceMessage(
						"reporteRECCDRsCategoriaProductosForm.titulo"));

		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		log.info("Entro a setViewAttributes");

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		
		ReporteRECCDRsCategoriaProductosForm f = (ReporteRECCDRsCategoriaProductosForm) this.formReporte;
		
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
		f.setCodigoPais(pais.getCodigo());
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		siccMarcaList =  reporteService.getListaGenerico("getMarcaProdu", criteriaOperacion);
		siccRegionList = reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);
		siccOperacionesList = interfazSiCCService.getOperacionesByCodigoPais(criteriaOperacion);
		
		
	}

	/**
	 * @return the secuencial
	 */
	public String getSecuencial() {
		return secuencial;
	}

	/**
	 * @param secuencial the secuencial to set
	 */
	public void setSecuencial(String secuencial) {
		this.secuencial = secuencial;
	}

	/**
	 * @return the siccMarcaList
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList the siccMarcaList to set
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
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
	 * @return the siccOperacionesList
	 */
	public List getSiccOperacionesList() {
		return siccOperacionesList;
	}

	/**
	 * @param siccOperacionesList the siccOperacionesList to set
	 */
	public void setSiccOperacionesList(List siccOperacionesList) {
		this.siccOperacionesList = siccOperacionesList;
	}

	/**
	 * @return the siccTipoOperacionList
	 */
	public LabelValue[] getSiccTipoOperacionList() {
		return siccTipoOperacionList;
	}

	/**
	 * @param siccTipoOperacionList the siccTipoOperacionList to set
	 */
	public void setSiccTipoOperacionList(LabelValue[] siccTipoOperacionList) {
		this.siccTipoOperacionList = siccTipoOperacionList;
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
	 * @return the siccTerritorioList
	 */
	public LabelValue[] getSiccTerritorioList() {
		return siccTerritorioList;
	}

	/**
	 * @param siccTerritorioList the siccTerritorioList to set
	 */
	public void setSiccTerritorioList(LabelValue[] siccTerritorioList) {
		this.siccTerritorioList = siccTerritorioList;
	}

	/**
	 * @return the busquedaProductoSearchAction
	 */
	public BusquedaProductoSearchAction getBusquedaProductoSearchAction() {
		return busquedaProductoSearchAction;
	}

	/**
	 * @param busquedaProductoSearchAction the busquedaProductoSearchAction to set
	 */
	public void setBusquedaProductoSearchAction(
			BusquedaProductoSearchAction busquedaProductoSearchAction) {
		this.busquedaProductoSearchAction = busquedaProductoSearchAction;
	}

	/**
	 * @return the mostrarPopupProducto
	 */
	public boolean isMostrarPopupProducto() {
		return mostrarPopupProducto;
	}

	/**
	 * @param mostrarPopupProducto the mostrarPopupProducto to set
	 */
	public void setMostrarPopupProducto(boolean mostrarPopupProducto) {
		this.mostrarPopupProducto = mostrarPopupProducto;
	}

	

	
}