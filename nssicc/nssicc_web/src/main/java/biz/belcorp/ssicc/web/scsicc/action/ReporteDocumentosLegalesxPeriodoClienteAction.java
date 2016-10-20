package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteDocumentosLegalesxPeriodoClienteForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaHIPClientePOPUPSearchAction;

@ManagedBean
@SessionScoped
public class ReporteDocumentosLegalesxPeriodoClienteAction  extends BaseReporteAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5937385610096159219L;
	
	private boolean mostrarPopupCliente;
	private static final String POPUP_CLIENTE = "CLIENTE";
	
	@ManagedProperty(value="#{busquedaHIPClientePOPUPSearchAction}")
	private BusquedaHIPClientePOPUPSearchAction busquedaHIPClientePOPUPSearchAction;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteDocumentosLegalesxPeriodoClienteForm form = new ReporteDocumentosLegalesxPeriodoClienteForm();		
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(this.formatoExportacion)) {
			return "reporteDocumentosLegalesxPeriodoClienteXLS";
		} else
			return "reporteMaestroVertical";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteDocumentosLegalesxPeriodoClienteForm f=(ReporteDocumentosLegalesxPeriodoClienteForm)this.formReporte;		
		String titulo=this.getResourceMessage("reporteDocumentosLegalesxPeriodoClienteForm.titulo", new Object[]{f.getCodigoPeriodo()});
		params.put("codigoPeriodo", f.getCodigoPeriodo());
		params.put("codigoConsultora", f.getCodConsultora());	
		params.put("titulo", titulo);
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReportePDF=false;
		this.mostrarReporteXLS=true;
		ReporteDocumentosLegalesxPeriodoClienteForm f=(ReporteDocumentosLegalesxPeriodoClienteForm)this.formReporte;		
		Pais pais= this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());			
	}

	@Override
	protected void setInvocarPopup(String accion) {
		if (accion.equals(this.POPUP_CLIENTE)){ 
			this.mostrarPopupCliente = true;
		}
	}	
	
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopupHipCliente' method");
		}
		this.mostrarProcesoBatch = true;
		this.mostrarPopupCliente = false;
		if (accion.equals(this.POPUP_CLIENTE)) {
			this.busquedaHIPClientePOPUPSearchAction.verificarRegistro(event);
			if (this.busquedaHIPClientePOPUPSearchAction.isSeleccionoRegistro()) {				
				Map clienteHipMap = (Map)this.busquedaHIPClientePOPUPSearchAction.getBeanRegistroSeleccionado(); 
				ReporteDocumentosLegalesxPeriodoClienteForm busquedaForm = (ReporteDocumentosLegalesxPeriodoClienteForm)this.formReporte;	
				String codCliente=(String)clienteHipMap.get("codigoCliente");	
				busquedaForm.setCodConsultora(codCliente);
				this.busquedaHIPClientePOPUPSearchAction.setBeanRegistroSeleccionado(null);
				this.formReporte=  busquedaForm;        
			}
		}		
	}
	
	@Override
	protected void setSalirPopup() {		
		this.mostrarPopupCliente = false;
		this.busquedaHIPClientePOPUPSearchAction.setBeanRegistroSeleccionado(null);
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
	 * @return the busquedaHIPClientePOPUPSearchAction
	 */
	public BusquedaHIPClientePOPUPSearchAction getBusquedaHIPClientePOPUPSearchAction() {
		return busquedaHIPClientePOPUPSearchAction;
	}

	/**
	 * @param busquedaHIPClientePOPUPSearchAction the busquedaHIPClientePOPUPSearchAction to set
	 */
	public void setBusquedaHIPClientePOPUPSearchAction(
			BusquedaHIPClientePOPUPSearchAction busquedaHIPClientePOPUPSearchAction) {
		this.busquedaHIPClientePOPUPSearchAction = busquedaHIPClientePOPUPSearchAction;
	}

	/**
	 * @return the popupCliente
	 */
	public static String getPopupCliente() {
		return POPUP_CLIENTE;
	}
	
	

}
