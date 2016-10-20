package biz.belcorp.ssicc.web.spncd.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spncd.model.DespachoProducto;
import biz.belcorp.ssicc.service.spncd.MantenimientoCUPProgDsctoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.action.BusquedaProductoMatrizSearchAction;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spncd.form.MantenimientoCUPProductoPrimerPedidoForm;
import biz.belcorp.ssicc.web.spncd.form.MantenimientoCUPProductoPrimerPedidoSearchForm;

@SessionScoped
@ManagedBean
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoCUPProductoPrimerPedidoSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 8558444547382483825L;
	private List cupProgramasList;
	private List cupProductoPrimerPedidoList;
	private static final String POPUP_CODIGO_VENTA = "VENTA";
	private boolean mostrarPopupMatrizProducto = false;
	private String codigoPeriodo;
	private boolean mostrarValores;

	@ManagedProperty(value = "#{busquedaProductoMatrizSearchAction}")
	private BusquedaProductoMatrizSearchAction busquedaProductoMatrizSearchAction;

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoCUPProductoPrimerPedidoList";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setInvocarPopup(java.lang.String)
	 */
	@Override
	protected void setInvocarPopup(String accion) {
		this.mostrarProcesoBatch = false;
		MantenimientoCUPProductoPrimerPedidoForm mante = (MantenimientoCUPProductoPrimerPedidoForm) this.formMantenimiento;
		this.codigoPeriodo = mante.getCodigoPeriodo();

		if (accion.equals(this.POPUP_CODIGO_VENTA))
			this.mostrarPopupMatrizProducto = true;
		this.busquedaProductoMatrizSearchAction.setCodigoPeriodo(codigoPeriodo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setAceptarPopup(javax.faces.event.ActionEvent, java.lang.String)
	 */
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopup' method");
		}
		this.mostrarProcesoBatch = true;
		if (accion.equals(this.POPUP_CODIGO_VENTA)) {
			this.mostrarPopupMatrizProducto = false;
			this.busquedaProductoMatrizSearchAction.verificarRegistro(event);
			if (this.busquedaProductoMatrizSearchAction.isSeleccionoRegistro()) {
				HashMap<String, Object> sistemabusqueda = (HashMap<String, Object>) this.busquedaProductoMatrizSearchAction
						.getBeanRegistroSeleccionado();
				MantenimientoCUPProductoPrimerPedidoForm mante = (MantenimientoCUPProductoPrimerPedidoForm) this.formMantenimiento;
				String codigoVenta = sistemabusqueda.get("codigoVenta")
						.toString();
				mante.setCodigoVenta(codigoVenta);
				// CodigoVentaMatriz codigoVenta =
				// (CodigoVentaMatriz)this.busquedaProductoMatrizSearchAction.getBeanRegistroSeleccionado();
				// MantenimientoCUPDespachoProductoForm mante =
				// (MantenimientoCUPDespachoProductoForm)this.formMantenimiento;
				// mante.setCodigoVenta(codigoVenta.getCodigoVenta());
				this.formMantenimiento = mante;
				this.busquedaProductoMatrizSearchAction
						.setBeanRegistroSeleccionado(null);
			}
		}
		if (log.isDebugEnabled()) {
			log.debug("Finish 'retornarPopupZona' method");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setSalirPopup()
	 */
	@Override
	protected void setSalirPopup() {
		this.mostrarPopupMatrizProducto = false;
		this.busquedaProductoMatrizSearchAction
				.setBeanRegistroSeleccionado(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoCUPProductoPrimerPedidoForm";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoCUPProductoPrimerPedidoSearchForm form = new MantenimientoCUPProductoPrimerPedidoSearchForm();
		return form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		log.debug("Setting find Attributes");

		MantenimientoCUPProductoPrimerPedidoSearchForm f = (MantenimientoCUPProductoPrimerPedidoSearchForm) this.formBusqueda;
		MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");

		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoPrograma", f.getCodigoPrograma());
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		List resul = new ArrayList();
		resul = service.getProdutoPrimerPedidoList(criteria);
		return resul;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		log.debug("Setting Delete Attributes");
		MantenimientoCUPProductoPrimerPedidoSearchForm f = (MantenimientoCUPProductoPrimerPedidoSearchForm) this.formBusqueda;
		MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
		// service.deleteProdutosPrimerPedido(, user.getLogin());
		// String mensaje = this
		// .getResourceMessage("mantenimientoCUPProductoPrimerPedidoSearchForm.detalle.deleted");
		// addInfo("Info :", mensaje);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		Usuario user = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoCUPProductoPrimerPedidoForm f = (MantenimientoCUPProductoPrimerPedidoForm) this.formMantenimiento;
		MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
		MantenimientoOCRPedidoControlFacturacionService facturacionService = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");

		String codigoPais = f.getCodigoPais();
		String codigoUsuario = user.getLogin();
		String codigoPeriodo = f.getCodigoPeriodo();
		String codigoPrograma = f.getCodigoPrograma();

		Map map = BeanUtils.describe(f);
		map.put("codigoPais", codigoPais);
		map.put("codigoUsuario", codigoUsuario);
		map.put("codigoPeriodo", codigoPeriodo);
		map.put("codigoPrograma", codigoPrograma);

		// Validar si el periódo se encuentra en la BD.
		String periodo = service.getPeriodo(map);
		if (StringUtils.isBlank(periodo)) {
			service.insertPeriodo(map);
			log.debug("Se insertó un nuevo periodo.");
		}

		if (f.getCodigoValida().equals(Constants.NUMERO_CERO)) {
			// String mensaje = null;
			// En caso sea una actualizacion
			service.updateProdutoPrimerPedido(map);

			// mensaje = this
			// .getResourceMessage("mantenimientoCUPProductoPrimerPedidoForm.actualizacion.exito");
			// addError("Info :", mensaje);

		} else {
			// String mensaje = null;
			BigDecimal oidOferta = facturacionService
					.getOfertaDetalleByPeriodoCodigoVenta(map);
			map.put("idDetalleOferta", oidOferta);
			DespachoProducto despachoProducto = service
					.getOfertaDetalleById(map);
			BeanUtils.copyProperties(map, despachoProducto);

			service.insertProductoPrimerPedido(map);
			//
			// mensaje = this
			// .getResourceMessage("mantenimientoCUPProductoPrimerPedidoForm.registro.exito");
			// addError("Info :", mensaje);

		}

		return true;
	}

	/**
	 * 
	 */
	public void verificar() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'verificar' method");
		}

		try {
			MantenimientoCUPProductoPrimerPedidoForm f = (MantenimientoCUPProductoPrimerPedidoForm) this.formMantenimiento;
			MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
			MantenimientoOCRPedidoControlFacturacionService facturacionService = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");

			Map criteria = new HashMap();
			criteria.put("codigoPais", f.getCodigoPais());
			criteria.put("codigoPrograma", f.getCodigoPrograma());
			criteria.put("codigoPeriodo", f.getCodigoPeriodo());
			criteria.put("codigoVenta", f.getCodigoVenta());

			List l = new ArrayList();
			l = service.getIndicadorDigitable(criteria);

			try {
				if (l.get(0).equals(Constants.NUMERO_CERO)) {
					// Indicador en 0, entonces es una oferta no digitable
					String mensaje = this
							.getResourceMessage("mantenimientoCUPProductoPrimerPedidoForm.oferta.noDigitable");
					addError("Error :", mensaje);
					return;
				} else {
					// Valido que no exista un registro igual que se encuentre
					// con
					// estado habilitado
					List list = new ArrayList();
					criteria.put("estado", Constants.NUMERO_UNO);
					list = service.getProdutoPrimerPedidoList(criteria);
					if (list.size() != 0) {
						String mensaje = this
								.getResourceMessage("mantenimientoCUPProductoPrimerPedidoForm.registro.existe");
						addError("Error :", mensaje);
						return;
					} else {
						this.mostrarBotonSave = true;
						this.mostrarValores = true;
						f.setCodigoValida(Constants.NUMERO_DOS);
						Map map = BeanUtils.describe(f);
						BigDecimal oidOferta = facturacionService
								.getOfertaDetalleByPeriodoCodigoVenta(map);
						map.put("idDetalleOferta", oidOferta);
						DespachoProducto despachoProducto = service
								.getOfertaDetalleById(map);
						BeanUtils.copyProperties(f, despachoProducto);

					}
				}
			} catch (Exception e) {
				this.addError(
						"Error : ",
						this.getResourceMessage("mantenimientoCUPProductoPrimerPedidoForm.noExiste.matriz"));
				return;
			}

		} catch (Exception e) {
			this.addError("Error :", this.obtieneMensajeErrorException(e));
			return;
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		log.debug("Entering 'edit' method");
		MantenimientoCUPProductoPrimerPedidoForm f = new MantenimientoCUPProductoPrimerPedidoForm();
		MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		this.mostrarValores = false;
		f.setCodigoPais(pais.getCodigo());
		f.setNewRecord(false);

		// String id = f.getSelectedItems()[0];

		// log.debug("_________id = " + id);
		if (!this.accion.equals(this.ACCION_NUEVO)) {
			f.setNewRecord(true);
			this.mostrarValores = true;
			this.mostrarBotonSave = true;
			HashMap<String, Object> sistemabusqueda = (HashMap<String, Object>) this.beanRegistroSeleccionado;

			String codigoPais = (String) sistemabusqueda.get("codigoPais");
			String codigoPrograma = (String) sistemabusqueda
					.get("codigoPrograma");
			String codigoPeriodo = (String) sistemabusqueda
					.get("codigoPeriodo");
			String codigoVenta = (String) sistemabusqueda.get("codigoVenta");

			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoPrograma", codigoPrograma);
			criteria.put("codigoPeriodo", codigoPeriodo);
			criteria.put("codigoVenta", codigoVenta);

			Map map = new HashMap();
			map = (Map) service.getProdutoPrimerPedidoList(criteria).get(0);
			f.setCodigoPeriodo(map.get("codigoPeriodo").toString());
			f.setCodigoPrograma(map.get("codigoPrograma").toString());
			f.setCodigoVenta(map.get("codigoVenta").toString());
			f.setCodigoProducto(map.get("codigoProducto").toString());
			f.setDescripcionProducto(map.get("descripcionProducto").toString());
			f.setValorUnitario(map.get("precioProducto").toString());
			f.setEstado(map.get("estado").toString());
			f.setCodigoValida(Constants.NUMERO_CERO);
			return f;
		}
		return f;
	}

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo
	 *            the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the mostrarValores
	 */
	public boolean isMostrarValores() {
		return mostrarValores;
	}

	/**
	 * @param mostrarValores
	 *            the mostrarValores to set
	 */
	public void setMostrarValores(boolean mostrarValores) {
		this.mostrarValores = mostrarValores;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("Setting Attributes.");

		this.mostrarBotonSave = false;
		this.mostrarBotonConsultar = false;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoCUPProductoPrimerPedidoSearchForm f = (MantenimientoCUPProductoPrimerPedidoSearchForm) this.formBusqueda;
		MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
		f.setCodigoPais(pais.getCodigo());
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());

		// Cargar combo de Programas
		this.cupProgramasList = service.getProgramasDescuentosbyPais(criteria);

	}

	/**
	 * @return the cupProgramasList
	 */
	public List getCupProgramasList() {
		return cupProgramasList;
	}

	/**
	 * @param cupProgramasList
	 *            the cupProgramasList to set
	 */
	public void setCupProgramasList(List cupProgramasList) {
		this.cupProgramasList = cupProgramasList;
	}

	/**
	 * @return the cupProductoPrimerPedidoList
	 */
	public List getCupProductoPrimerPedidoList() {
		return cupProductoPrimerPedidoList;
	}

	/**
	 * @param cupProductoPrimerPedidoList
	 *            the cupProductoPrimerPedidoList to set
	 */
	public void setCupProductoPrimerPedidoList(List cupProductoPrimerPedidoList) {
		this.cupProductoPrimerPedidoList = cupProductoPrimerPedidoList;
	}

	/**
	 * @return the busquedaProductoMatrizSearchAction
	 */
	public BusquedaProductoMatrizSearchAction getBusquedaProductoMatrizSearchAction() {
		return busquedaProductoMatrizSearchAction;
	}

	/**
	 * @param busquedaProductoMatrizSearchAction
	 *            the busquedaProductoMatrizSearchAction to set
	 */
	public void setBusquedaProductoMatrizSearchAction(
			BusquedaProductoMatrizSearchAction busquedaProductoMatrizSearchAction) {
		this.busquedaProductoMatrizSearchAction = busquedaProductoMatrizSearchAction;
	}

	/**
	 * @return the mostrarPopupMatrizProducto
	 */
	public boolean isMostrarPopupMatrizProducto() {
		return mostrarPopupMatrizProducto;
	}

	/**
	 * @param mostrarPopupMatrizProducto
	 *            the mostrarPopupMatrizProducto to set
	 */
	public void setMostrarPopupMatrizProducto(boolean mostrarPopupMatrizProducto) {
		this.mostrarPopupMatrizProducto = mostrarPopupMatrizProducto;
	}
}