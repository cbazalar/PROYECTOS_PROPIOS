package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.scsicc.model.ConsultaHIPDatosCliente;
import biz.belcorp.ssicc.dao.sisicc.model.Cliente;
import biz.belcorp.ssicc.dao.spusicc.sto.model.PedidoSeguidoSTO;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOService;
import biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaConsultoraPOPUPSearchAction;
import biz.belcorp.ssicc.web.scsicc.hip.action.ConsultaHIPDatosClienteAction;
import biz.belcorp.ssicc.web.spusicc.sto.form.ConsultaSTOSeguimientoPedidosForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ConsultaSTOSeguimientoPedidosAction extends
		BaseConsultaAbstractAction implements Serializable {

	private static final long serialVersionUID = -423133921424137509L;
	private List stoSeguPediList;
	private List stoSeguPedidList2;
	private List stoSeguPedidList3;
	private Boolean mostrarPrimeraLista;
	private Boolean mostrarSegundaLista;
	private Boolean mostrarTerceraLista;
	private static final String POPUP_CONSULTORA = "CONSULTORA";
	private boolean mostrarPopupConsultora;
	
	private boolean seleccionable = false;
	private Object beanRegistroSeleccionado = null;
	
	
	@ManagedProperty(value="#{consultaHIPDatosClienteAction}")
	private ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction;

	@ManagedProperty(value = "#{busquedaConsultoraPOPUPSearchAction}")
	private BusquedaConsultoraPOPUPSearchAction busquedaConsultoraPOPUPSearchAction;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaSTOSeguimientoPedidosForm form = new ConsultaSTOSeguimientoPedidosForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setAceptarPopup(javax.faces.event.ActionEvent, java.lang.String)
	 */
	@SuppressWarnings("static-access")
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopupHipCliente' method");
		}
		if (accion.equals(this.POPUP_CONSULTORA)) {
			this.busquedaConsultoraPOPUPSearchAction.verificarRegistro(event);
			if (this.busquedaConsultoraPOPUPSearchAction.isSeleccionoRegistro()) {
				Cliente cliente = (Cliente) this.busquedaConsultoraPOPUPSearchAction
						.getBeanRegistroSeleccionado();
				ConsultaSTOSeguimientoPedidosForm f = (ConsultaSTOSeguimientoPedidosForm) this.formBusqueda;
				f.setCodigoConsultora(cliente.getCodigo());
				this.busquedaConsultoraPOPUPSearchAction
						.setBeanRegistroSeleccionado(null);
			}

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setSalirPopup()
	 */
	protected void setSalirPopup() {
		this.mostrarPopupConsultora = false;
		this.busquedaConsultoraPOPUPSearchAction
				.setBeanRegistroSeleccionado(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setInvocarPopup(java.lang.String)
	 */
	@SuppressWarnings("static-access")
	protected void setInvocarPopup(String accion) {
		this.mostrarProcesoBatch = false;
		if (accion.equals(this.POPUP_CONSULTORA)) {
			this.mostrarPopupConsultora = true;
		}
	}

	/**
	 * Metodo que se ejecuta para cargar data inicial del Manage a traves de AJAX
	 * @param actionEvent
	 */
	public void viewAjax(ActionEvent actionEvent) {
		log.debug("Entering view (Ajax)' - method");
		this.view();
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ConsultaSTOSeguimientoPedidosAction - search' method");
		}

		ConsultaSTOSeguimientoPedidosForm searchForm = (ConsultaSTOSeguimientoPedidosForm) this.formBusqueda;

		Map criteriaSearch = new HashMap();

		this.mostrarListaBusqueda = false;
    	ConsultaHIPDatosCliente dtoDatosCliente = this.consultaHIPDatosClienteAction.getHipDtoDatosCliente();
    	if (dtoDatosCliente != null) {
    		searchForm.setCodigoConsultora(dtoDatosCliente.getCodigoCliente());
		   this.mostrarListaBusqueda = true;
    	}
		
		if (StringUtils.isNotBlank(searchForm.getCodigoPeriodo())
				&& StringUtils.isNotBlank(searchForm.getCodigoConsultora())) {

			criteriaSearch.put("codigoCliente",
					searchForm.getCodigoConsultora());
			criteriaSearch.put("codigoPeriodo", searchForm.getCodigoPeriodo());

			ProcesoSTOService procesoSTOService = (ProcesoSTOService) getBean("spusicc.procesoSTOService");

			// Carga de Tabla de Seguimiento de Pedidos
			// procesoSTOService.executeCargaSeguimientoPedidos(criteriaSearch);
			this.stoSeguPediList = new ArrayList();

			List listResult = procesoSTOService
					.getPedidoSeguidoSTOList(criteriaSearch);

			List lista1 = new ArrayList();
			List lista2 = new ArrayList();
			List lista3 = new ArrayList();

			if (listResult != null && listResult.size() > 0) {

				// Recorro toda la lista
				for (int i = 0; i < listResult.size(); i++) {
					PedidoSeguidoSTO m = new PedidoSeguidoSTO();
					m = (PedidoSeguidoSTO) listResult.get(i);

					if (m.getGrupo().equals("G1")) {
						log.debug(".--------------------------- > "
								+ m.getOrden());
						if (m.getOrden().equals("0")) {
							// Setea la fecha del primer pedido
							searchForm.setFechaEntrega(m.getEstado());
						} else {
							lista1.add(m);
						}
					}

					if (m.getGrupo().equals("G2")) {
						if (m.getOrden().equals("0")) {
							// Setea la fecha del segundo pedido
							searchForm.setFechaEntrega2(m.getEstado());
						} else {
							lista2.add(m);
						}
					}

					if (m.getGrupo().equals("G3")) {
						if (m.getOrden().equals("0")) {
							// Setea la fecha del tercer pedido
							searchForm.setFechaEntrega3(m.getEstado());
						} else {
							lista3.add(m);
						}
					}
				}
				
				this.inicializandoValores();
				this.stoSeguPediList = lista1;
				int tamanioLista1 = lista1.size();
				if (tamanioLista1 > 0)
					this.mostrarPrimeraLista = true;
				this.stoSeguPedidList2 = lista2;
				int tamanioLista2 = lista2.size();
				if (tamanioLista2 > 0)
					this.mostrarSegundaLista = true;
				this.stoSeguPedidList3 = lista3;
				int tamanioLista3 = lista3.size();
				if (tamanioLista3 > 0)
					this.mostrarTerceraLista = true;
			}
		}
		return stoSeguPediList;
	}

	/* inicializando valores */
	public void inicializandoValores() {
		this.mostrarSegundaLista = false;
		this.mostrarTerceraLista = false;
		this.mostrarPrimeraLista = false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'view' method");
		}
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ConsultaSTOSeguimientoPedidosForm f = (ConsultaSTOSeguimientoPedidosForm) this.formBusqueda;
    	inicializandoValores();
    	
    	this.mostrarListaBusqueda = false;
    	ConsultaHIPDatosCliente dtoDatosCliente = this.consultaHIPDatosClienteAction.getHipDtoDatosCliente();
    	if (dtoDatosCliente != null) {
		   f.setCodigoConsultora(dtoDatosCliente.getCodigoCliente());
		   this.mostrarListaBusqueda = true;
    	}
    	
		String codigoConsultora = f.getCodigoConsultora();
		if (!StringUtils.isBlank(codigoConsultora)) {
			f.setShowCodigoConsultora("S");
			f.setCodigoConsultora(codigoConsultora);
			f.setCodigoPeriodo("");
		} else {
			f.setShowCodigoConsultora("N");
			f.setCodigoConsultora("");
			f.setCodigoPeriodo("");
		}
		
		

		f.setCodigoPais(pais.getCodigo());
		this.stoSeguPedidList2 = new ArrayList();
		this.stoSeguPedidList3 = new ArrayList();
		this.stoSeguPediList = new ArrayList();
	}

	
	
	/* GET - SEY */
	/**
	 * @return the stoSeguPediList
	 */
	public List getStoSeguPediList() {
		return stoSeguPediList;
	}

	/**
	 * @param stoSeguPediList
	 *            the stoSeguPediList to set
	 */
	public void setStoSeguPediList(List stoSeguPediList) {
		this.stoSeguPediList = stoSeguPediList;
	}

	/**
	 * @return the stoSeguPedidList2
	 */
	public List getStoSeguPedidList2() {
		return stoSeguPedidList2;
	}

	/**
	 * @param stoSeguPedidList2
	 *            the stoSeguPedidList2 to set
	 */
	public void setStoSeguPedidList2(List stoSeguPedidList2) {
		this.stoSeguPedidList2 = stoSeguPedidList2;
	}

	/**
	 * @return the stoSeguPedidList3
	 */
	public List getStoSeguPedidList3() {
		return stoSeguPedidList3;
	}

	/**
	 * @param stoSeguPedidList3
	 *            the stoSeguPedidList3 to set
	 */
	public void setStoSeguPedidList3(List stoSeguPedidList3) {
		this.stoSeguPedidList3 = stoSeguPedidList3;
	}

	/**
	 * @return the mostrarSegundaLista
	 */
	public Boolean getMostrarSegundaLista() {
		return mostrarSegundaLista;
	}

	/**
	 * @param mostrarSegundaLista
	 *            the mostrarSegundaLista to set
	 */
	public void setMostrarSegundaLista(Boolean mostrarSegundaLista) {
		this.mostrarSegundaLista = mostrarSegundaLista;
	}

	/**
	 * @return the mostrarTerceraLista
	 */
	public Boolean getMostrarTerceraLista() {
		return mostrarTerceraLista;
	}

	/**
	 * @param mostrarTerceraLista
	 *            the mostrarTerceraLista to set
	 */
	public void setMostrarTerceraLista(Boolean mostrarTerceraLista) {
		this.mostrarTerceraLista = mostrarTerceraLista;
	}

	/**
	 * @return the mostrarPrimeraLista
	 */
	public Boolean getMostrarPrimeraLista() {
		return mostrarPrimeraLista;
	}

	/**
	 * @param mostrarPrimeraLista
	 *            the mostrarPrimeraLista to set
	 */
	public void setMostrarPrimeraLista(Boolean mostrarPrimeraLista) {
		this.mostrarPrimeraLista = mostrarPrimeraLista;
	}

	/**
	 * @return the mostrarPopupConsultora
	 */
	public boolean isMostrarPopupConsultora() {
		return mostrarPopupConsultora;
	}

	/**
	 * @param mostrarPopupConsultora
	 *            the mostrarPopupConsultora to set
	 */
	public void setMostrarPopupConsultora(boolean mostrarPopupConsultora) {
		this.mostrarPopupConsultora = mostrarPopupConsultora;
	}

	/**
	 * @return the busquedaConsultoraPOPUPSearchAction
	 */
	public BusquedaConsultoraPOPUPSearchAction getBusquedaConsultoraPOPUPSearchAction() {
		return busquedaConsultoraPOPUPSearchAction;
	}

	/**
	 * @param busquedaConsultoraPOPUPSearchAction
	 *            the busquedaConsultoraPOPUPSearchAction to set
	 */
	public void setBusquedaConsultoraPOPUPSearchAction(
			BusquedaConsultoraPOPUPSearchAction busquedaConsultoraPOPUPSearchAction) {
		this.busquedaConsultoraPOPUPSearchAction = busquedaConsultoraPOPUPSearchAction;
	}

	/**
	 * @return the popupConsultora
	 */
	public static String getPopupConsultora() {
		return POPUP_CONSULTORA;
	}

	/**
	 * @return the seleccionable
	 */
	public boolean isSeleccionable() {
		return seleccionable;
	}

	/**
	 * @param seleccionable the seleccionable to set
	 */
	public void setSeleccionable(boolean seleccionable) {
		this.seleccionable = seleccionable;
	}

	/**
	 * @return the beanRegistroSeleccionado
	 */
	public Object getBeanRegistroSeleccionado() {
		return beanRegistroSeleccionado;
	}

	/**
	 * @param beanRegistroSeleccionado the beanRegistroSeleccionado to set
	 */
	public void setBeanRegistroSeleccionado(Object beanRegistroSeleccionado) {
		this.beanRegistroSeleccionado = beanRegistroSeleccionado;
	}

	/**
	 * @return the consultaHIPDatosClienteAction
	 */
	public ConsultaHIPDatosClienteAction getConsultaHIPDatosClienteAction() {
		return consultaHIPDatosClienteAction;
	}

	/**
	 * @param consultaHIPDatosClienteAction the consultaHIPDatosClienteAction to set
	 */
	public void setConsultaHIPDatosClienteAction(
			ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction) {
		this.consultaHIPDatosClienteAction = consultaHIPDatosClienteAction;
	}
	
	
}