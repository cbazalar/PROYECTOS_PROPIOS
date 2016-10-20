package biz.belcorp.ssicc.web.scsicc.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCOBConsultoraIncobrableForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaHIPClientePOPUPSearchAction;

@ManagedBean
@SessionScoped
public class ReporteCOBConsultoraIncobrableAction extends BaseReporteAbstractAction{

	private static final long serialVersionUID = -6799295236891094296L;
	
	private List siccSociedadList;
	private boolean mostrarPopupCliente;
	private static final String POPUP_CLIENTE = "CLIENTE";
	
	@ManagedProperty(value="#{busquedaHIPClientePOPUPSearchAction}")
	private BusquedaHIPClientePOPUPSearchAction busquedaHIPClientePOPUPSearchAction;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOBConsultoraIncobrableForm form = new ReporteCOBConsultoraIncobrableForm();		
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(this.formatoExportacion)) {
			return "reporteCOBConsultoraIncobrableXLS";
		} else
			return "reporteMaestroVertical";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteCOBConsultoraIncobrableForm f=(ReporteCOBConsultoraIncobrableForm)this.formReporte;
		String codCliente="";
		String nroDocumento="";
		
		if(StringUtils.isNotBlank(f.getCodigoConsultora()))
			codCliente="AND COD_CLIE = '"+f.getCodigoConsultora()+"'";
		if(StringUtils.isNotBlank(f.getNumDocumento()))
			nroDocumento="AND NUM_DOCU_IDEN = '"+f.getNumDocumento()+"'";
		
		String condicion=codCliente+nroDocumento;
		params.put("codigoSociedad", f.getCodigoSociedad());
		params.put("condicion", condicion);		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReportePDF=false;
		this.mostrarReporteXLS=true;
		ReporteCOBConsultoraIncobrableForm f=(ReporteCOBConsultoraIncobrableForm)this.formReporte;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Pais pais= this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		this.siccSociedadList=service.getSociedadesByCodigoPais(pais.getCodigo());
		Base base = (Base) this.siccSociedadList.get(0);
		f.setCodigoSociedad(base.getCodigo());
		
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
				ReporteCOBConsultoraIncobrableForm busquedaForm = (ReporteCOBConsultoraIncobrableForm)this.formReporte;	
				String codCliente=(String)clienteHipMap.get("codigoCliente");	
				busquedaForm.setCodigoConsultora(codCliente);
				this.busquedaHIPClientePOPUPSearchAction.setBeanRegistroSeleccionado(null);
				this.formReporte=  busquedaForm;        
			}
		}		
	}
	
	@Override
	protected void setSalirPopup() {
		this.mostrarProcesoBatch = true;
		this.mostrarPopupCliente = false;
		this.busquedaHIPClientePOPUPSearchAction.setBeanRegistroSeleccionado(null);
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
	
	

}
