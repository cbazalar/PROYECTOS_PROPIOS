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

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Cliente;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteRECNoAtendidosBajoStockForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaConsultoraPOPUPSearchAction;

/**
 * @author Sigcomt
 *
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteRECNoAtendidosBajoStockAction extends BaseReporteAbstractAction implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String formatoReporte;
	private List siccOperacionesList;
	private LabelValue[] siccTipoOperacionList;
	private LabelValue[] siccZonaList;
	private LabelValue[] siccTerritorioList;
	private String codigoIdiomaISO;
	private List siccRegionList;
	private List siccMarcaList;
	private List siccUnidadNegocioList;
	private List siccEstadoOperacionList;
	private List siccReclamoList;
	private List siccNegocioList;
	
	private boolean mostrarPopupCliente = false;
	private static final String POPUP_CLIENTE = "POPUP_CLIENTE";
	
	@ManagedProperty(value="#{busquedaConsultoraPOPUPSearchAction}")
	private BusquedaConsultoraPOPUPSearchAction busquedaConsultoraPOPUPSearchAction;
	
	

	/**
	 * @return
	 */
	public LabelValue[] getSiccTipoOperacionList() {
		return siccTipoOperacionList;
	}

	/**
	 * @param siccTipoOperacionList
	 */
	public void setSiccTipoOperacionList(LabelValue[] siccTipoOperacionList) {
		this.siccTipoOperacionList = siccTipoOperacionList;
	}

	/**
	 * @return
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return
	 */
	public List getSiccUnidadNegocioList() {
		return siccUnidadNegocioList;
	}

	/**
	 * @param siccUnidadNegocioList
	 */
	public void setSiccUnidadNegocioList(List siccUnidadNegocioList) {
		this.siccUnidadNegocioList = siccUnidadNegocioList;
	}

	/**
	 * @return
	 */
	public List getSiccEstadoOperacionList() {
		return siccEstadoOperacionList;
	}

	/**
	 * @param siccEstadoOperacionList
	 */
	public void setSiccEstadoOperacionList(List siccEstadoOperacionList) {
		this.siccEstadoOperacionList = siccEstadoOperacionList;
	}

	/**
	 * @return
	 */
	public List getSiccReclamoList() {
		return siccReclamoList;
	}

	/**
	 * @param siccReclamoList
	 */
	public void setSiccReclamoList(List siccReclamoList) {
		this.siccReclamoList = siccReclamoList;
	}

	/**
	 * @return
	 */
	public List getSiccNegocioList() {
		return siccNegocioList;
	}

	/**
	 * @param siccNegocioList
	 */
	public void setSiccNegocioList(List siccNegocioList) {
		this.siccNegocioList = siccNegocioList;
	}

	/**
	 * @return
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}


	private Boolean habilitarOperacion = false;
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteRECNoAtendidosBajoStockForm form = new ReporteRECNoAtendidosBajoStockForm();
		return form;
	}
	
	/**
	 * @param form
	 * @return
	 */
	public String setValidarReporte(ReporteRECNoAtendidosBajoStockForm form) {		
	    if (form.getCodigoPeriodoInicial().compareTo(form.getCodigoPeriodoFinal()) < 0) 
				return "Usted debe de ingresar una fecha diferente";			
	    return null;
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteRECNoAtendidosBajoStockForm form = (ReporteRECNoAtendidosBajoStockForm)this.formReporte;
		
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();	
		form.setCodigoPais(pais.getCodigo());
		this.mostrarReporteXLS = true;
		Map criteriaOperacion = new HashMap();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));
		form.setCodigoPeriodoInicial(periodo);
		form.setCodigoPeriodoFinal(periodo);
		
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		this.siccMarcaList = reporteService.getListaGenerico("getMarcaProdu",criteriaOperacion);
		this.siccUnidadNegocioList=reporteService.getListaGenerico("getListaUnidadNegocio",criteriaOperacion);
		this.siccEstadoOperacionList=reporteService.getListaGenerico("getListaEstadoOperacion",criteriaOperacion);
		this.siccReclamoList=reporteService.getListaGenerico("getListaEstadoReclamo",
				criteriaOperacion);
		this.siccNegocioList=reporteService.getListaGenerico("getListaNegocio",
				criteriaOperacion);
		this.siccRegionList=reporteService.getListaGenerico("getRegionesByPais",
				criteriaOperacion);
		this.siccOperacionesList=interfazSiCCService
				.getOperacionesByCodigoPais(criteriaOperacion);
		
		this.siccTipoOperacionList= null;
		this.siccZonaList=null;
		this.siccTerritorioList=null;
		this.setCodigoIdiomaISO( this.mPantallaPrincipalBean.getOidIdiomaIso());


	}
	
	/**
	 * Desabilita la opcion Operacion 
	 */
	public void desahabilitarOperacion(ValueChangeEvent val) {
		try {
			log.info("el valor es ");
			log.info("val " +val.getNewValue().toString());
			String obj =  val.getNewValue().toString();
		    if (obj.equals("1")) {	       
		        this.setHabilitarOperacion(true); 
		    } else {	
		    	 this.setHabilitarOperacion(false); 
		    }
		    log.info(this.getHabilitarOperacion().toString());
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		this.formatoReporte= this.getFormatoExportacion();
		if ("XLS".equals(formatoReporte))
			return "reporteRECNoAtendidosBajoStockXLS";				
		else
			return "reporteMaestroHorizontal";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteRECNoAtendidosBajoStockPDF";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteRECNoAtendidosBajoStockForm form = (ReporteRECNoAtendidosBajoStockForm)this.formReporte;
		Integer fecha1,fecha2;
		fecha1 = Integer.parseInt(form.getCodigoPeriodoInicial());
		fecha2 = Integer.parseInt(form.getCodigoPeriodoFinal());

		
		if (fecha1 > fecha2){
			String mensaje = "El periodo inicial no puede ser mayor al final";
			return mensaje;
		}
	    	    					
	    return null;
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		log.debug(">>ENTRO A prepareParameterMap ");
		
		
		ReporteRECNoAtendidosBajoStockForm reporteRECForm = (ReporteRECNoAtendidosBajoStockForm) this.formReporte;
		this.formatoReporte = reporteRECForm.getFormatoExportacion();
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteria = params;
		if (StringUtils
				.isNotEmpty(reporteRECForm.getCodigoConsultora())) {
			criteria.put("codigoCliente", reporteRECForm.getCodigoConsultora());
			params.put("oidConsultora", reporteService.getOidString(
					"getOidClienteByCodigoCliente", criteria));
		} else
			params.put("oidConsultora", null);
		if (StringUtils.equals(reporteRECForm.getTipoPeriodo(), "0")) {
			criteria.put("codigoPeriodo", reporteRECForm
					.getCodigoPeriodoInicial());
			params.put("oidPeriodoReferenciaInicial", reporteService
					.getOidString("getOidPeriodoByCodigoPeriodo", criteria));

			criteria.put("codigoPeriodo", reporteRECForm
					.getCodigoPeriodoFinal());
			params.put("oidPeriodoReferenciaFinal", reporteService
					.getOidString("getOidPeriodoByCodigoPeriodo", criteria));

			params.put("oidPeriodoRegistroInicial", null);
			params.put("oidPeriodoRegistroFinal", null);
		} else {
			criteria.put("codigoPeriodo", reporteRECForm
					.getCodigoPeriodoInicial());
			params.put("oidPeriodoRegistroInicial", reporteService
					.getOidString("getOidPeriodoByCodigoPeriodo", criteria));

			criteria.put("codigoPeriodo", reporteRECForm
					.getCodigoPeriodoFinal());
			params.put("oidPeriodoRegistroFinal", reporteService.getOidString(
					"getOidPeriodoByCodigoPeriodo", criteria));

			params.put("oidPeriodoReferenciaInicial", null);
			params.put("oidPeriodoReferenciaFinal", null);
		}	
		params.put("negocioList",
				(reporteRECForm.getNegocioList() == null) ? new ArrayList()
						: Arrays.asList(reporteRECForm.getNegocioList()));
		params
				.put("unidadNegocioList", (reporteRECForm
						.getUnidadNegocioList() == null) ? new ArrayList()
						: Arrays.asList(reporteRECForm.getUnidadNegocioList()));
		params.put("territorioList",
				(reporteRECForm.getTerritorioList() == null) ? new ArrayList()
						: Arrays.asList(reporteRECForm.getTerritorioList()));

		/* Para calcular el tipo de Operacion */
		params
		.put("tipoOperacionList", (reporteRECForm
				.getTipoOperacionList() == null) ? new ArrayList() : Arrays
				.asList(reporteRECForm.getTipoOperacionList()));		

		params.put("operacionList",
				(reporteRECForm.getOperacionList() == null) ? new ArrayList()
						: Arrays.asList(reporteRECForm.getOperacionList()));

		params.put("marcaList",
				(reporteRECForm.getMarcaList() == null) ? new ArrayList()
						: Arrays.asList(reporteRECForm.getMarcaList()));
		params.put("zonaList",
				(reporteRECForm.getZonaList() == null) ? new ArrayList()
						: Arrays.asList(reporteRECForm.getZonaList()));
		params.put("regionList",
				(reporteRECForm.getRegionList() == null) ? new ArrayList()
						: Arrays.asList(reporteRECForm.getRegionList()));
		params.put("NroReporte", getResourceMessage(
				"reporteRECNoAtendidosBajoStockForm.numero.reporte"));
		params.put("titulo", getResourceMessage(
				"reporteRECNoAtendidosBajoStockForm.titulo"));	
		return params;
	}
	
	/**
	 * Obtener Lista de Zonas
	 * @param val
	 */
	public void showZonasxRegion(ValueChangeEvent val){
		log.debug(">>showZonasxRegions ");
		try {
			ReporteRECNoAtendidosBajoStockForm form = (ReporteRECNoAtendidosBajoStockForm) this.formReporte;
			String[] regiones = (String [])val.getNewValue();	
			//Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();	
			if(!val.equals(null) && regiones.length > 0 ){		
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			this.setSiccZonaList(aSvc.getZonasMultipleByPaisMarcaCanalRegion(form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, 
					Constants.CODIGO_CANAL_DEFAULT, regiones, Constants.FORMATO_TOTAL));
			form.setZonaList(null);
			}else {
				this.siccZonaList= null;
				form.setZonaList(null);
				this.siccTerritorioList = null;
				form.setTerritorioList(null);
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
		
	}
	
	/**
	 * @param val
	 */
	public void showTiposxOperacion(ValueChangeEvent val){
		log.debug(">>showTiposxOperacion ");
		try {
			ReporteRECNoAtendidosBajoStockForm form = (ReporteRECNoAtendidosBajoStockForm) this.formReporte;
			String[] valores = (String [])val.getNewValue();	
			//Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();	
			ArrayList<String> listaValores = 
				     new ArrayList<String>(Arrays.asList(valores));
			if(!val.equals(null) && valores.length > 0 ){		
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			this.setSiccTipoOperacionList(aSvc.getTiposOperaMultipleByOpera(form.getCodigoPais(), listaValores, ""));
			form.setTipoOperacionList(null);
			}else {
				this.siccTipoOperacionList= null;
				form.setTipoOperacionList(null);
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
		
	}
	
	/**
	 * @param val
	 */
	public void showTerritorioxZona(ValueChangeEvent val){
		log.debug(">>showTerritorioxZona ");
		try {
			ReporteRECNoAtendidosBajoStockForm form = (ReporteRECNoAtendidosBajoStockForm) this.formReporte;
			
			String[] regiones = (String [])form.getRegionList();
			
			String[] zonas = (String [])val.getNewValue();
			if(!val.equals(null) && zonas.length > 0 ){
			
			List<String> listaRegiones = 
				     new ArrayList<String>(Arrays.asList(regiones));
			
			List<String> listaZonas = 
				     new ArrayList<String>(Arrays.asList(zonas));	
			
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			this.setSiccTerritorioList(aSvc.getTerritoriosMultipleByPaisMarcaCanalRegionZona(form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT,
					Constants.CODIGO_CANAL_DEFAULT, listaRegiones, listaZonas, Constants.FORMATO_TOTAL));

			form.setTerritorioList(null);
			}else {
				this.siccTerritorioList = null;
				form.setTerritorioList(null);
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setInvocarPopup(java.lang.String)
	 */
	@Override
	protected void setInvocarPopup(String accion) {
		if (accion.equals(this.POPUP_CLIENTE)){ 
			this.mostrarPopupCliente = true;
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setAceptarPopup(javax.faces.event.ActionEvent, java.lang.String)
	 */
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopupHipCliente' method");
		}
		this.mostrarProcesoBatch = true;
		this.mostrarPopupCliente = false;
		if (accion.equals(this.POPUP_CLIENTE)) {
			this.busquedaConsultoraPOPUPSearchAction.verificarRegistro(event);
			if (this.busquedaConsultoraPOPUPSearchAction.isSeleccionoRegistro()) {	
				
				Cliente cliente= (Cliente)this.busquedaConsultoraPOPUPSearchAction.getBeanRegistroSeleccionado(); 
				
				
				ReporteRECNoAtendidosBajoStockForm busquedaForm = (ReporteRECNoAtendidosBajoStockForm)this.formReporte;				
				busquedaForm.setCodigoConsultora(cliente.getCodigo());
				this.busquedaConsultoraPOPUPSearchAction.setBeanRegistroSeleccionado(null);
				this.formReporte =  busquedaForm;
				
				
			}
		}	
		if (log.isDebugEnabled()) {
			log.debug("Finish 'PopupHipCliente' method");
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setSalirPopup()
	 */
	@Override
	protected void setSalirPopup() {
		this.mostrarProcesoBatch = true;
		this.mostrarPopupCliente = false;
		this.busquedaConsultoraPOPUPSearchAction.setBeanRegistroSeleccionado(null);
	}
		
	/**
	 * @return
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}
	
	/**
	 * @return
	 */
	public List getSiccOperacionesList() {
		return siccOperacionesList;
	}

	/**
	 * @param siccOperacionesList
	 */
	public void setSiccOperacionesList(List siccOperacionesList) {
		this.siccOperacionesList = siccOperacionesList;
	}


	/**
	 * @return
	 */
	public String getCodigoIdiomaISO() {
		return codigoIdiomaISO;
	}

	/**
	 * @param codigoIdiomaISO
	 */
	public void setCodigoIdiomaISO(String codigoIdiomaISO) {
		this.codigoIdiomaISO = codigoIdiomaISO;
	}

	/**
	 * @return
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return
	 */
	public LabelValue[] getSiccTerritorioList() {
		return siccTerritorioList;
	}

	/**
	 * @param siccTerritorioList
	 */
	public void setSiccTerritorioList(LabelValue[] siccTerritorioList) {
		this.siccTerritorioList = siccTerritorioList;
	}

	/**
	 * @return the habilitarOperacion
	 */
	public Boolean getHabilitarOperacion() {
		return habilitarOperacion;
	}

	/**
	 * @param habilitarOperacion the habilitarOperacion to set
	 */
	public void setHabilitarOperacion(Boolean habilitarOperacion) {
		this.habilitarOperacion = habilitarOperacion;
	}
		
	protected String devuelveBeanReporteService(){
		return "reportes.reporteRECNoAtendidosBajoStockService";
	}

	/**
	 * @return the busquedaConsultoraPOPUPSearchAction
	 */
	public BusquedaConsultoraPOPUPSearchAction getBusquedaConsultoraPOPUPSearchAction() {
		return busquedaConsultoraPOPUPSearchAction;
	}

	/**
	 * @param busquedaConsultoraPOPUPSearchAction the busquedaConsultoraPOPUPSearchAction to set
	 */
	public void setBusquedaConsultoraPOPUPSearchAction(
			BusquedaConsultoraPOPUPSearchAction busquedaConsultoraPOPUPSearchAction) {
		this.busquedaConsultoraPOPUPSearchAction = busquedaConsultoraPOPUPSearchAction;
	}

	/**
	 * @return the mostrarPopupCliente
	 */
	public boolean isMostrarPopupCliente() {
		return mostrarPopupCliente;
	}

	/**
	 * @param mostrarPopupCliente the mostrarPopupCliente to set
	 */
	public void setMostrarPopupCliente(boolean mostrarPopupCliente) {
		this.mostrarPopupCliente = mostrarPopupCliente;
	}
}