/**
 * 
 */
package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Cliente;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.form.ConsultaPEDResultadoChequeoConsultoraForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaConsultoraPOPUPSearchAction;

/**
 * @author Jose Luis Rodriguez
 * 
 *
 */
@ManagedBean
@SessionScoped
public class ConsultaPEDResultadoChequeoConsultoraAction extends BaseConsultaAbstractAction{
	
	private boolean mostrarPopupCliente;
	private static final String POPUP_CLIENTE = "POPUP_CLIENTE";
	
	@ManagedProperty(value="#{busquedaConsultoraPOPUPSearchAction}")
	private BusquedaConsultoraPOPUPSearchAction busquedaConsultoraPOPUPSearchAction;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 798449179321130490L;
	private List pedResultadoChequeoConsultoraList;
	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaPEDResultadoChequeoConsultoraForm form = new ConsultaPEDResultadoChequeoConsultoraForm();
		return form;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.action.BaseAbstractAction#setViewAttributes(javax.servlet.http.HttpServletRequest, org.apache.struts.action.ActionForm)
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		
		this.mostrarPopupCliente = false;
		this.activarHotkeyEstandar=false;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ConsultaPEDResultadoChequeoConsultoraForm f = (ConsultaPEDResultadoChequeoConsultoraForm) this.formBusqueda;
		f.setCodigoPais(pais.getCodigo());
		
		String codigoPeriodo = service.getPeriodoDefaultByPaisCanal(pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT);
		f.setCodigoPeriodoInicial(codigoPeriodo);
		f.setCodigoPeriodoFinal(codigoPeriodo);
		
		this.pedResultadoChequeoConsultoraList = new ArrayList();
	}
	
	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}
		ConsultaPEDResultadoChequeoConsultoraForm f = (ConsultaPEDResultadoChequeoConsultoraForm) this.formBusqueda;
		ReporteService service = (ReporteService) getBean("scsicc.reporteService");
		Map criteria = new HashMap();
		criteria.put("codigoPais",f.getCodigoPais());
		criteria.put("codigoConsultora", f.getCodigoConsultora());
		criteria.put("codigoPeriodoInicial",f.getCodigoPeriodoInicial());
		criteria.put("codigoPeriodoFinal",f.getCodigoPeriodoFinal());
		
		List resultado = service.getResultadoChequeoConsultora(criteria);	
		return resultado;
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
				ConsultaPEDResultadoChequeoConsultoraForm f = (ConsultaPEDResultadoChequeoConsultoraForm) this.formBusqueda;
				f.setCodigoConsultora(cliente.getCodigo());
				this.busquedaConsultoraPOPUPSearchAction.setBeanRegistroSeleccionado(null);
				this.formBusqueda =  f;
				
				
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
	 * @return the pedResultadoChequeoConsultoraList
	 */
	public List getPedResultadoChequeoConsultoraList() {
		return pedResultadoChequeoConsultoraList;
	}

	/**
	 * @param pedResultadoChequeoConsultoraList the pedResultadoChequeoConsultoraList to set
	 */
	public void setPedResultadoChequeoConsultoraList(
			List pedResultadoChequeoConsultoraList) {
		this.pedResultadoChequeoConsultoraList = pedResultadoChequeoConsultoraList;
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
	
	


}
