package biz.belcorp.ssicc.web.scsicc.action;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Cliente;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteRECListadoReclamosDetalleForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaConsultoraPOPUPSearchAction;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaProductoSearchAction;



@ManagedBean
@SessionScoped
public class ReporteRECListadoReclamosDetalleAction extends BaseReporteAbstractAction implements Serializable {
			
	private static final long serialVersionUID = 7496906172990857522L;

     private List siccMotivoDevolucionList;
     private List siccOperacionesList;
     private LabelValue[] siccTipoOperacionList;
     
    /*Agregar estos atributos para el popup cliente*/
 	private boolean mostrarPopupCliente = false;
 	private static final String POPUP_CLIENTE = "POPUP_CLIENTE";
	private Boolean habilitarOperacion = false;
 	@ManagedProperty(value="#{busquedaConsultoraPOPUPSearchAction}")
 	private BusquedaConsultoraPOPUPSearchAction busquedaConsultoraPOPUPSearchAction;
 	
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
		
		ReporteRECListadoReclamosDetalleForm busquedaForm = (ReporteRECListadoReclamosDetalleForm)this.formReporte;
		busquedaForm.setAccion(accion);
		this.mostrarProcesoBatch = false;
		
		if (accion.equals(POPUP_CLIENTE)){ 
			this.mostrarPopupCliente = true;
			this.mostrarPopupProducto = false;
		}else if(accion.equals(POPUP_SACPRODUCTO)){
			this.mostrarPopupProducto = true;
			this.mostrarPopupCliente = false;
		}
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
		ReporteRECListadoReclamosDetalleForm busquedaForm = (ReporteRECListadoReclamosDetalleForm)this.formReporte;
		
		if (accion.equals(POPUP_CLIENTE)) {
			this.mostrarPopupCliente = false;
			
			this.busquedaConsultoraPOPUPSearchAction.verificarRegistro(event);
			if (this.busquedaConsultoraPOPUPSearchAction.isSeleccionoRegistro()) {	
				
				Cliente cliente= (Cliente)this.busquedaConsultoraPOPUPSearchAction.getBeanRegistroSeleccionado(); 
								
				busquedaForm.setCodigoConsultora(cliente.getCodigo());
				this.busquedaConsultoraPOPUPSearchAction.setBeanRegistroSeleccionado(null);

			}
		}if (accion.equals(POPUP_SACPRODUCTO)) {
			this.mostrarPopupProducto = false;
			
			this.busquedaProductoSearchAction.verificarRegistro(event);
			if (this.busquedaProductoSearchAction.isSeleccionoRegistro()) {
				
				Map clienteHipMap = (Map) this.busquedaProductoSearchAction.getBeanRegistroSeleccionado(); 

				busquedaForm.setCodigoSap(((String)clienteHipMap.get("codigoSap")));
				busquedaForm.setDescripcionCorta(MapUtils.getString(clienteHipMap, "descripcionCorta"));
				this.busquedaProductoSearchAction.setBeanRegistroSeleccionado(null);
			}
		}	
		
		this.formReporte =  busquedaForm;
		
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
		ReporteRECListadoReclamosDetalleForm busquedaForm = (ReporteRECListadoReclamosDetalleForm)this.formReporte;
		String accion = busquedaForm.getAccion();
		
		//if (accion.equals(POPUP_CLIENTE)) {
			
			this.mostrarPopupCliente = false;
			this.busquedaConsultoraPOPUPSearchAction.setBeanRegistroSeleccionado(null);
		
		// }else if(accion.equals(POPUP_SACPRODUCTO)){
				
			 this.mostrarPopupProducto = false;
			 this.busquedaProductoSearchAction.setBeanRegistroSeleccionado(null);

		 //}
	}
 	
     
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
	    return new ReporteRECListadoReclamosDetalleForm();
	}

	@Override
	public String setValidarReporte() {
		ReporteRECListadoReclamosDetalleForm form = (ReporteRECListadoReclamosDetalleForm)this.formReporte;
		Integer fecha1,fecha2;
		fecha1 = Integer.parseInt(form.getCodigoPeriodoInicial());
		fecha2 = Integer.parseInt(form.getCodigoPeriodoFinal());

		
		if (fecha1 > fecha2){
			String mensaje = "El Periodo Final debe ser mayor o igual al Periodo Inicial";
			return mensaje;
		}
	    	    					
	    return null;
	}
	
	
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteRECListadoReclamosDetalleForm form = (ReporteRECListadoReclamosDetalleForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(form.getFormatoExportacion()))
			return "reporteRECListadoReclamosDetalleXLS";
		else
		   return "reporteMaestroVertical";
	   
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteRECListadoReclamosDetalleForm form = (ReporteRECListadoReclamosDetalleForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(form.getFormatoExportacion()))
			return null;
		else
		   return "reporteRECListadoReclamosDetallePDF";
	}
	
	public void showTipoOperaXOpera(ValueChangeEvent val){
		log.debug(">>showTipoOperaXOpera ");
		log.debug("val: "+ ArrayUtils.toString(val.getNewValue()));
		ReporteRECListadoReclamosDetalleForm form = (ReporteRECListadoReclamosDetalleForm) this.formReporte;
		String[] operaciones = (String []) val.getNewValue();		
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		
		if(!ArrayUtils.isEmpty(operaciones)){
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			ArrayList tmp = new ArrayList();
			
			for (String ope : operaciones) {
				tmp.add(ope);
			}
			
			siccTipoOperacionList = aSvc.getTiposOperaMultipleByOpera( pais.getCodigo(),tmp, "");      
      		
		  }else{
			setSiccTipoOperacionList(null);
		}
		form.setTipoOperacionList(null);
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {

		ReporteRECListadoReclamosDetalleForm reporteRECForm = (ReporteRECListadoReclamosDetalleForm) this.formReporte;

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		params.put("codigoConsultora", reporteRECForm.getCodigoConsultora());
		Map criteria2 = new HashMap();
		criteria2.put("codigoCliente", reporteRECForm.getCodigoConsultora());
		String oidConsultora = null;
		if (StringUtils.isNotEmpty(reporteRECForm.getCodigoConsultora())) {
				oidConsultora = reporteService.getOidString(
						"getOidClienteByCodigoCliente", criteria2);
		}
		String oidProducto = null;
		criteria2.put("codigoSap", reporteRECForm.getCodigoSap());
		if (StringUtils.isNotEmpty(reporteRECForm.getCodigoSap())) {
				oidProducto = reporteService.getOidString(
						"getOidProductoByCodigoSap", criteria2);
		}
		criteria2
				.put("codigoPeriodo", reporteRECForm.getCodigoPeriodoInicial());
		String oidPeriodoInicial = reporteService.getOidString(
				"getOidPeriodoByCodigoPeriodo", criteria2);
		criteria2.put("codigoPeriodo", reporteRECForm.getCodigoPeriodoFinal());
		String oidPeriodoFinal = reporteService.getOidString(
				"getOidPeriodoByCodigoPeriodo", criteria2);
		String condicion = obtieneCondicion(reporteRECForm
				.getTipoOperacionList(), "g.COD_OPER ||'-'|| f.VAL_TIPO_OPER",
				"'");
		String codigoPais =  reporteRECForm.getCodigoPais();
		
        	

		params.put("codigoPais", codigoPais);
		params.put("oidPeriodoFinal", oidPeriodoFinal);
		params.put("oidPeriodoInicial", oidPeriodoInicial);
		params.put("oidConsultora", oidConsultora);
		params.put("oidProducto", oidProducto);
		params.put("condicion", condicion);
		params.put("NroReporte", "");
		params.put("titulo", getReportResourceMessage("reporteRECListadoReclamosDetalleForm.titulo"));

		
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		log.info("Entro a setViewAttributes");

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = true;
		
		ReporteRECListadoReclamosDetalleForm f = (ReporteRECListadoReclamosDetalleForm) this.formReporte;
		
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
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais",  pais.getCodigo());
		siccMotivoDevolucionList = reporteService.getListaGenerico("getMotivoDevolucionByPais",criteriaOperacion);
		siccOperacionesList = interfazSiCCService.getOperacionesByCodigoPais(criteriaOperacion);		
	}

	/**
	 * @return the siccMotivoDevolucionList
	 */
	public List getSiccMotivoDevolucionList() {
		return siccMotivoDevolucionList;
	}

	/**
	 * @param siccMotivoDevolucionList the siccMotivoDevolucionList to set
	 */
	public void setSiccMotivoDevolucionList(List siccMotivoDevolucionList) {
		this.siccMotivoDevolucionList = siccMotivoDevolucionList;
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