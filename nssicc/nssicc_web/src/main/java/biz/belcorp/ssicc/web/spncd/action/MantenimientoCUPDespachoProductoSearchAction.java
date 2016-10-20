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
import biz.belcorp.ssicc.dao.sisicc.model.HistoricoAuditoria;
import biz.belcorp.ssicc.dao.spncd.model.DespachoProducto;
import biz.belcorp.ssicc.dao.spncd.model.ProgramaCupon;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spncd.MantenimientoCUPProgDsctoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.action.BusquedaProductoMatrizSearchAction;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spncd.form.MantenimientoCUPDespachoProductoForm;
import biz.belcorp.ssicc.web.spncd.form.MantenimientoCUPDespachoProductoSearchForm;

@SessionScoped
@ManagedBean
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoCUPDespachoProductoSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -4601866438423378358L;
	private List cupDespachoProductosList;
	private List cupProgramasList;
	private List cupNivelList;
	private boolean mostrarPopupMatrizProducto = false;
	private static final String POPUP_CODIGO_VENTA = "VENTA";
	private String codigoPeriodo;
	private String renderedCodigoVenta = "false";
	private String variableTitulo = null;
	private Boolean mostrarLuegoCodigoVenta;
	private Boolean kit;
	private Boolean web;
	private Boolean mostrarMatriz;
	private Boolean mostrarCuv;
	private Boolean mostrarBotonDespachoAutomatico;

	@ManagedProperty(value = "#{busquedaProductoMatrizSearchAction}")
	private BusquedaProductoMatrizSearchAction busquedaProductoMatrizSearchAction;

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setInvocarPopup(java.lang.String)
	 */
	@Override
	protected void setInvocarPopup(String accion) {
		this.mostrarProcesoBatch = false;
		MantenimientoCUPDespachoProductoForm mante = (MantenimientoCUPDespachoProductoForm) this.formMantenimiento;
		this.codigoPeriodo = mante.getCodigoPeriodo();
		if (accion.equals(this.POPUP_CODIGO_VENTA))
			this.mostrarPopupMatrizProducto = true;
		this.busquedaProductoMatrizSearchAction
				.setCodigoPeriodo(this.codigoPeriodo);
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
				MantenimientoCUPDespachoProductoForm mante = (MantenimientoCUPDespachoProductoForm) this.formMantenimiento;
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

	/**
	 * @param form
	 * @return
	 */
	private Map getCriteria(MantenimientoCUPDespachoProductoSearchForm form) {
		try {
			MantenimientoCUPDespachoProductoSearchForm despachoProductoForm = (MantenimientoCUPDespachoProductoSearchForm) this.formBusqueda;
			Map criteria = BeanUtils.describe(form);
			criteria.put("estado", Constants.ESTADO_ACTIVO);
			criteria.put("codigoPais", getCodigoPais());

			if (StringUtils.isNotBlank(despachoProductoForm.getCodigoPeriodo())) {
				criteria.put("codigoPeriodo",
						despachoProductoForm.getCodigoPeriodo());
			}
			return criteria;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
			return null;
		}

	}

	/**
	 * @param id
	 * @return
	 */
	private Map getCriteria(String id) {
		Map criteria = new HashMap();
		criteria.put("codigoPais", StringUtils.split(id, "-")[0]);
		criteria.put("codigoPeriodo", StringUtils.split(id, "-")[1]);
		criteria.put("codigoPrograma", StringUtils.split(id, "-")[2]);
		criteria.put("codigoNivel", StringUtils.split(id, "-")[3]);
		criteria.put("codigoVenta", StringUtils.split(id, "-")[4]);

		return criteria;
	}

	/**
	 * @return
	 */
	private String getCodigoPais() {
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		return pais.getCodigo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoCUPDespachoProductoList";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoCUPDespachoProductoForm";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoCUPDespachoProductoSearchForm form = new MantenimientoCUPDespachoProductoSearchForm();
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
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}
		MantenimientoCUPDespachoProductoSearchForm form = (MantenimientoCUPDespachoProductoSearchForm) this.formBusqueda;
		MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
		List list = service.getDespachoProductosByCriteria(getCriteria(form));
		cupDespachoProductosList = list;

		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
		MantenimientoCUPDespachoProductoSearchForm form = (MantenimientoCUPDespachoProductoSearchForm) this.formBusqueda;
		DespachoProducto objSeleccionado = (DespachoProducto) this.beanRegistroSeleccionado;

		// String id = objSeleccionado.getId();
		DespachoProducto despachoProducto = new DespachoProducto();
		despachoProducto.setCodigoPais(objSeleccionado.getCodigoPais());
		despachoProducto.setCodigoPeriodo(objSeleccionado.getCodigoPeriodo());
		despachoProducto.setCodigoPrograma(objSeleccionado.getCodigoPrograma());
		despachoProducto.setCodigoNivel(objSeleccionado.getCodigoNivel());
		despachoProducto.setCodigoVenta(objSeleccionado.getCodigoVenta());
		despachoProducto.setEstadoRegistro(Constants.NUMERO_CERO);
		service.eliminarDespachoProducto(despachoProducto);
		//BeanUtils.copyProperties(form, despachoProducto);
		this.listaBusqueda = this.setFindAttributes();
		this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
		return true;
	}

	/**
	 * @param evt
	 */
	public void validaMatriz(ActionEvent evt) {
		MantenimientoCUPDespachoProductoForm f = (MantenimientoCUPDespachoProductoForm) this.formMantenimiento;
		MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteria = new HashMap();
		this.renderedCodigoVenta = "false";

		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoPrograma", f.getCodigoPrograma());

		// Obteniedo las campañas siguientes a la campaña incio y fin del
		// programa
		String periodo = service.getPeriodosPrograma(criteria);
		String[] periodos = periodo.split("__");
		String periodoInicio = periodos[0];
		String periodoFin = periodos[1];

		Map criteriaX = new HashMap();
		criteriaX.put("codigoPais", f.getCodigoPais());
		criteriaX.put("codigoPeriodo", periodoInicio);
		criteriaX.put("nperiodo", 1);
		periodoInicio = service.getPeriodoSiguiente(criteriaX);
		criteriaX.put("codigoPeriodo", periodoFin);
		periodoFin = service.getPeriodoSiguiente(criteriaX);

		String periodoSeleccionado = f.getCodigoPeriodo();

		try {
			criteria.put("codigoPeriodo", periodoInicio);
			int oidPeriodoInicio = reporteService.getOidPeriodo(criteria);
			criteria.put("codigoPeriodo", periodoSeleccionado);
			int oidPeriodoSeleccionado = reporteService.getOidPeriodo(criteria);

			// Valida campaña Despacho Final
			String parametroPrograma = service
					.getParametroPrograma(Constants.PARAMETRO_PROGRAMA);
			criteria.put("parametrosistema",
					Integer.parseInt(parametroPrograma) - 1);
			criteria.put("codigoPeriodo", f.getCodigoPeriodo());

			List cupones = (List) service.getProgramasDsctoByCriteria(criteria);
			ProgramaCupon cupon = (ProgramaCupon) cupones.get(0);
			criteria.put("campanhafinal", cupon.getCampanhaFinal());
			int validarPeriodoFin = service.validarPeriodoCampahnaFin(criteria);

			if (StringUtils.equals(cupon.getIndicadorRegaloPedido(),
					Constants.ESTADO_ACTIVO)) {
				if (oidPeriodoSeleccionado >= oidPeriodoInicio
						&& validarPeriodoFin != 0) {

					criteria.put("oidPeriodo", oidPeriodoSeleccionado);
					String validaMatriz = service
							.validarMatrizFacturacion(criteria);
					if (!Constants.NUMERO_CERO.equals(validaMatriz)) {
						f.setCodigoValida(Constants.IND_VALIDA_COD_VENTA);
						this.mostrarCuv = true;
						this.renderedCodigoVenta = "true";
						this.mostrarMatriz = false;

						return;
					} else {
						this.addError(
								"Error : ",
								this.getResourceMessage("mantenimientoCUPDespachoRegaloPedidoForm.error.matriz"));
						this.mostrarCuv = false;
						return;
					}
				} else {
					this.addError(
							"Error : ",
							this.getResourceMessage("mantenimientoCUPDespachoRegaloPedidoForm.error.periodos"));
					this.mostrarCuv = false;
					return;
				}
			} else {
				this.addError(
						"Error : ",
						this.getResourceMessage("mantenimientoCUPDespachoRegaloPedidoForm.regaloPorPedido.noActivado"));
				this.mostrarCuv = false;
				return;
			}

			//

		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * @param evt
	 */
	public void validaCuv(ActionEvent evt) {
		try {
			MantenimientoCUPDespachoProductoForm f = (MantenimientoCUPDespachoProductoForm) this.formMantenimiento;
			MantenimientoOCRPedidoControlFacturacionService facturacionService = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
			MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
			MantenimientoCUPProgDsctoService dsctoService = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
			Map map = new HashMap();

			// se valida que exista el cuv para el perido seleccionado
			map.put("codigoPeriodo", f.getCodigoPeriodo());
			map.put("codigoVenta", f.getCodigoVenta());
			map.put("codigoPais", f.getCodigoPais());
			map.put("codigoPrograma", f.getCodigoPrograma());
			map.put("codigoNivel", f.getCodigoNivel());

			BigDecimal oidOferta = facturacionService
					.getOfertaDetalleByPeriodoCodigoVenta(map);
			map.put("oidOferta", oidOferta);
			// Validar Tipo de Oferta
			int duplicado = service.validaTipoOfertaDuplicado(map);
			if (duplicado != 0) {
				int tipoOferta = service.validaTipoOferta(map);

				if (tipoOferta != 0) {
					if (oidOferta.intValue() <= 0) {
						this.addError(
								"Error : ",
								this.getResourceMessage("mantenimientoCUPDespachoRegaloPedidoForm.error.cuv"));
						return;
					} else {
						// se verifica que el codigo de venta sea digitable
						map.put("idDetalleOferta", oidOferta);
						BigDecimal indicaDigit = facturacionService
								.getDetOfertaIndicaDigitableById(map);

						if (indicaDigit.intValue() <= 0) {
							this.addError(
									"Error : ",
									this.getResourceMessage("errors.indica.digitable.matriz"));
							return;
						} else {
							map.put("oidDetalleOferta", oidOferta);
							List detalleProducto = dsctoService
									.getDetalleProductoByIdOferta(map);

							Map detalle = (Map) detalleProducto.get(0);
							String codigoProducto = (String) detalle
									.get("codigoProducto");
							String descripcionProducto = (String) detalle
									.get("descriProducto");
							BigDecimal valorUnitario = (BigDecimal) detalle
									.get("valPrecio");

							f.setCodigoProducto(codigoProducto);
							f.setDescripcionProducto(descripcionProducto);
							f.setValorUnitario(valorUnitario.toString());
							f.setCodigoValida(Constants.IND_NO_VALIDA);
							this.mostrarBotonSave = true;
							this.mostrarCuv = false;
						}
					}
				} else {
					this.addError(
							"Error : ",
							this.getResourceMessage("mantenimientoCUPDespachoRegaloPedidoForm.error.tipoOferta"));
					return;
				}
			} else {
				this.addError(
						"Error : ",
						this.getResourceMessage("mantenimientoCUPDespachoRegaloPedidoForm.error.tipoOfertaDuplicado"));
				return;
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));

		}
	}

	/**
	 * @param evt
	 */
	public void valida(ActionEvent evt) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}
		try {

			this.mostrarLuegoCodigoVenta = false;
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			MantenimientoCUPProgDsctoService dsctoService = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
			MantenimientoOCRPedidoControlFacturacionService facturacionService = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
			MantenimientoCUPDespachoProductoForm despachoProductoForm = (MantenimientoCUPDespachoProductoForm) this.formMantenimiento;
			MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
			Map criteria = new HashMap();
			this.codigoPeriodo = despachoProductoForm.getCodigoPeriodo();

			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("codigoPrograma",
					despachoProductoForm.getCodigoPrograma());

			/*
			 * Campaña de despacho seleccionada no válida para Programa
			 */
			// Obteniedo las campañas siguientes a la campaña incio y fin
			// del
			// programa
			String periodo = service.getPeriodosPrograma(criteria);
			log.debug("periodo: " + periodo);
			String[] periodos = periodo.split("__");
			String periodoInicio = periodos[0];
			String periodoFin = periodos[1];

			int pi = Integer.parseInt(periodoInicio);
			int pf = Integer.parseInt(periodoFin);
			log.debug("periodoInicio: " + pi);
			log.debug("periodoFin: " + pf);

			String periodoSeleccionado = despachoProductoForm
					.getCodigoPeriodo();
			int ps = Integer.parseInt(periodoSeleccionado);
			criteria.put("codigoPeriodo", periodoSeleccionado);

			/*
			 * criteria.put("codigoPeriodo", periodoInicio); int
			 * oidPeriodoInicio = reporteService.getOidPeriodo(criteria);
			 * criteria.put("codigoPeriodo", periodoFin); int oidPeriodoFin =
			 * reporteService.getOidPeriodo(criteria);
			 * criteria.put("codigoPeriodo", periodoSeleccionado); int
			 * oidPeriodoSeleccionado = reporteService.getOidPeriodo(criteria);
			 */

			// Valida campaña Despacho Final
			String parametroPrograma = service
					.getParametroPrograma(Constants.PARAMETRO_PROGRAMA);
			int paramProgMenos1 = Integer.parseInt(parametroPrograma) - 1;
			criteria.put("parametrosistema", paramProgMenos1);
			List cupones = (List) service.getProgramasDsctoByCriteria(criteria);
			ProgramaCupon cupon = (ProgramaCupon) cupones.get(0);
			criteria.put("campanhafinal", cupon.getCampanhaFinal());
			int validarPeriodoFin = service.validarPeriodoCampahnaFin(criteria);

			Map criteria2 = new HashMap();
			criteria2.put("codigoPais", pais.getCodigo());
			criteria2.put("codigoPeriodo", periodoFin);
			criteria2.put("nperiodo", paramProgMenos1);
			String valor = service.getPeriodoSiguiente(criteria2);
			int vCondicional = Integer.parseInt(valor);

			if ((ps >= pi && ps <= pf) || (ps > pf && ps <= vCondicional)) {
				// continua...
			} else {
				// addError("", null);
				String mensaje = getResourceMessage("mantenimientoCUPDespachoRegaloPedidoForm.error.periodos");
				this.addError("Error: ", mensaje);
				this.mostrarBotonSave = false;
				return;
			}
			this.renderedCodigoVenta = "true";

			/* INI SA PER-SiCC-2012-0854 */
			criteria.put("codigoPeriodo",
					despachoProductoForm.getCodigoPeriodo());

			/*
			 * validaMostrarSegundoKit(service, despachoProductoForm, criteria);
			 */

			if (validationSuccessful(despachoProductoForm)) {
				this.renderedCodigoVenta = "true";
				if (StringUtils.equalsIgnoreCase(
						despachoProductoForm.getCodigoValida(),
						Constants.IND_VALIDA_PERIODO)) {
					despachoProductoForm
							.setCodigoValida(Constants.IND_VALIDA_COD_VENTA);

					/* INI SA PER-SiCC--2012-0467 */
					despachoProductoForm.setMostrarIndicadorKit(false);

					criteria.put("codigoPais", pais.getCodigo());
					criteria.put("codigoPrograma",
							despachoProductoForm.getCodigoPrograma());

					/* INI SA PER-SiCC-2012-0854 */
					criteria.put("codigoPeriodo",
							despachoProductoForm.getCodigoPeriodo());
					/* FIN SA PER-SiCC-2012-0854 */

					if (despachoProductoForm.getCodigoNivel().equals("01")) {
						if (!dsctoService
								.existenDespachosConIndicadorKit(criteria))
							despachoProductoForm.setMostrarIndicadorKit(true);
					}
					this.renderedCodigoVenta = "true";
					/* FIN SA PER-SiCC--2012-0467 */

					/*
					 * validaMostrarSegundoKit(service, despachoProductoForm,
					 * criteria);
					 */

				} else {
					this.renderedCodigoVenta = "false";
					this.mostrarBotonSave = true;
					Map map = BeanUtils.describe(despachoProductoForm);
					BigDecimal oidOferta = facturacionService
							.getOfertaDetalleByPeriodoCodigoVenta(map);
					map.put("idDetalleOferta", oidOferta);
					DespachoProducto despachoProducto = dsctoService
							.getOfertaDetalleById(map);
					BeanUtils.copyProperties(despachoProductoForm,
							despachoProducto);
					despachoProductoForm
							.setCodigoValida(Constants.IND_NO_VALIDA);

					/**********************************************/
					List lstPrgDscto1 = service
							.getProgramasDsctoByCriteria(criteria);
					String lsProgOblig1 = ((ProgramaCupon) lstPrgDscto1.get(0))
							.getIndicadorPremioWeb();
					String lsNumPedidos = ((ProgramaCupon) lstPrgDscto1.get(0))
							.getNumeroPedidos();

					if (StringUtils.equals(lsProgOblig1, Constants.NUMERO_UNO)
							&& Integer.parseInt(despachoProducto
									.getCodigoNivel()) >= Integer
									.parseInt(lsNumPedidos)) {
						despachoProductoForm
								.setIndicadorPremioWeb(Constants.NUMERO_CERO);
						despachoProductoForm.setMostrarIndicadorPremioWeb(true);
					} else {
						despachoProductoForm
								.setIndicadorPremioWeb(Constants.NUMERO_CERO);
						despachoProductoForm
								.setMostrarIndicadorPremioWeb(false);
					}
					/**********************************************/

					/* INI SA PER-SiCC--2012-0467 */
					despachoProductoForm.setMostrarIndicadorKit(false);

					criteria.put("codigoPais", pais.getCodigo());
					criteria.put("codigoPrograma",
							despachoProductoForm.getCodigoPrograma());

					/* INI SA PER-SiCC-2012-0854 */
					criteria.put("codigoPeriodo",
							despachoProductoForm.getCodigoPeriodo());

					if (StringUtils.equalsIgnoreCase(
							despachoProductoForm.getCodigoNivel(), "01")) {
						/* FIN SA PER-SiCC-2012-0854 */
						if (!dsctoService
								.existenDespachosConIndicadorKit(criteria))
							despachoProductoForm.setMostrarIndicadorKit(true);

					}
					this.mostrarLuegoCodigoVenta = true;
					/*
					 * validaMostrarSegundoKit(service, despachoProductoForm,
					 * criteria);
					 */

					/* FIN SA PER-SiCC--2012-0467 */
				}
			}
			return;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
			return;
		}

	}

	/**
	 * @param form
	 * @return
	 * @throws Exception
	 */
	private boolean validationSuccessful(
			MantenimientoCUPDespachoProductoForm form) throws Exception {
		boolean isOk = true;
		String mensaje = null;
		Map map = BeanUtils.describe(form);
		MantenimientoOCRPedidoControlFacturacionService facturacionService = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		MantenimientoCUPProgDsctoService dsctoService = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");

		if (form.getCodigoValida().equals(Constants.IND_VALIDA_PERIODO)) {
			BigDecimal oidMatriz = facturacionService
					.getMatrizFacturacionByPeriodo(map);
			if (oidMatriz.intValue() <= 0) {
				mensaje = this.getResourceMessage("errors.campanha.matriz");
				addError("Error : ", mensaje);
				return false;
			}
		} else if (form.getCodigoValida()
				.equals(Constants.IND_VALIDA_COD_VENTA)) {

			this.setRenderedCodigoVenta("true");
			BigDecimal existeCodigo = dsctoService
					.getCodVentaDespachoProductoById(map);
			if (existeCodigo.intValue() > 0) {
				mensaje = this.getResourceMessage("errors.codigo.venta.tabla");
				addError("Error : ", mensaje);
				return false;
			} else {
				BigDecimal oidOferta = facturacionService
						.getOfertaDetalleByPeriodoCodigoVenta(map);
				if (oidOferta.intValue() <= 0) {
					mensaje = this
							.getResourceMessage("errors.codigo.venta.matriz");
					addError("Error : ", mensaje);
					return false;
				} else {
					map.put("idDetalleOferta", oidOferta);
					BigDecimal indicaDigit = facturacionService
							.getDetOfertaIndicaDigitableById(map);
					// BigDecimal precioCatal =
					// facturacionService.getDetOfertaPrecioCatalogoById(map);
					BigDecimal estrategia = dsctoService
							.getDetOfertaEstrategiaCompuestaFijaById(map);
					if (indicaDigit.intValue() <= 0) {
						mensaje = this
								.getResourceMessage("errors.indica.digitable.matriz");
						addError("Error : ", mensaje);
						return false;
					}
					/*
					 * if (precioCatal.intValue() <= 0) {
					 * errors.add(ActionMessages.GLOBAL_MESSAGE, new
					 * ActionMessage("errors.precio.matriz")); }
					 */
					if (estrategia.intValue() <= 0) {
						mensaje = this
								.getResourceMessage("errors.estrategia.despacho.matriz");
						addError("Error : ", mensaje);
						return false;
					}
				}
			}
		}
		if (mensaje != null) {
			// saveErrors(request, errors);
			isOk = false;
		}

		return isOk;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}
	
		MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
		MantenimientoCUPDespachoProductoForm despachoProductoForm = (MantenimientoCUPDespachoProductoForm) this.formMantenimiento;

		// MantenimientoCUPProductoPrimerPedidoForm f =
		// (MantenimientoCUPProductoPrimerPedidoForm)form;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		if (validationSuccessful(despachoProductoForm)) {
			DespachoProducto despachoProducto = new DespachoProducto();
			BeanUtils.copyProperties(despachoProducto, despachoProductoForm);

			if (this.kit != null) {
				if (this.kit) {
					despachoProducto.setIndicadorKit("1");
					despachoProductoForm.setIndicadorKit("1");
					despachoProductoForm.setIndicadorKitSegundoPedido("1");
				} else {
					despachoProducto.setIndicadorKit("0");
					despachoProductoForm.setIndicadorKit("0");
					despachoProductoForm.setIndicadorKitSegundoPedido("0");
				}
			}
			if (this.web != null) {
				if (this.web) {
					despachoProducto.setIndicadorPremioWeb("1");
					despachoProductoForm.setIndicadorPremioWeb("1");
				} else {
					despachoProducto.setIndicadorPremioWeb("0");
					despachoProductoForm.setIndicadorPremioWeb("0");
				}
			}

			// Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			// Validar si el periódo no se encuentra en la BD, registrar el
			// periodo.
			Map map = BeanUtils.describe(despachoProductoForm);
			map.put("codigoPais", pais.getCodigo());
			map.put("codigoUsuario", usuario.getLogin());
			map.put("codigoPeriodo", despachoProductoForm.getCodigoPeriodo());
			map.put("codigoPrograma", despachoProductoForm.getCodigoPrograma());

			String periodo = service.getPeriodo(map);
			if (StringUtils.isBlank(periodo)) {
				service.insertPeriodo(map);
				log.debug("Inserte un nuevo periodo.");
			}

			/* INI SA PER-SiCC--2012-0467 */
			if (despachoProductoForm.isMostrarIndicadorKit()) {
				despachoProducto.setIndicadorKit(despachoProductoForm
						.getIndicadorKit());
			}
			/* INI PER-SiCC-2012-1055 */

			if (!StringUtils.equals(
					despachoProductoForm.getIndicadorKitSegundoPedido(),
					Constants.NUMERO_CERO)) {
				despachoProducto.setIndicadorKit(despachoProductoForm
						.getIndicadorKitSegundoPedido());
			}/*
			 * else { despachoProducto.setIndicadorKit(despachoProductoForm.
			 * getIndicadorKitSegundoPedido()); }
			 */
			/* FIN PER-SiCC-2012-1055 */

			/* FIN SA PER-SiCC--2012-0467 */

			if (isUpdate(despachoProductoForm)) {
				/* INI SA PER-SiCC--2012-0467 */
				despachoProducto.setActualizarIndicadorKit(Constants.SI);
				/* FIN SA PER-SiCC--2012-0467 */

				/*************************************************************************/
				if (StringUtils.equals(
						despachoProducto.getIndicadorPremioWeb(),
						Constants.NUMERO_UNO)) {
					despachoProducto.setTipoDespacho("04");
				} else {
					despachoProducto.setTipoDespacho("");
				}
				/*************************************************************************/

				service.desactivarDespachoProducto(despachoProducto);
				// messages.add(ActionMessages.GLOBAL_MESSAGE, new
				// ActionMessage("codigo.venta.updated"));
			} else {
				// Map params = BeanUtils.describe(despachoProductoForm);
				despachoProducto.setEstadoRegistro(Constants.ESTADO_ACTIVO);

				if (StringUtils.equals(
						despachoProducto.getIndicadorPremioWeb(),
						Constants.NUMERO_UNO)) {
					despachoProducto.setIndicadorPremioWeb("04");
				} else {
					despachoProducto.setIndicadorPremioWeb("");
				}

				service.insertDespachoProductos(despachoProducto, usuario);
				// messages.add(ActionMessages.GLOBAL_MESSAGE, new
				// ActionMessage("codigo.venta.added"));
				// saveMessages(request, messages);
				// log.debug("insert bean "+despachoProducto);
			}
			this.listaBusqueda = this.setFindAttributes();
			this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
			return true;
		} else {
			return false;
		}
	}

	private boolean isUpdate(
			MantenimientoCUPDespachoProductoForm despachoProductoForm) {
		boolean updateFlag = true;
		// String id = request.getParameter("id");
		String id = despachoProductoForm.getId();
		if (StringUtils.isBlank(id)) {
			updateFlag = false;
		}
		return updateFlag;
	}

	/**
	 * @param actionEvent
	 */
	public void addDespachoAutomatico(ActionEvent actionEvent) {
		if (log.isWarnEnabled()) {
			log.warn("Entering 'add' method");
		}
		
		this.mostrarBotonDespachoAutomatico = true;
		this.mostrarMatriz = false;
		this.mostrarCuv = false;
		this.accion = this.ACCION_NUEVO;
		this.accionFuncional = this.ACCION_NUEVO;

		/* Redireccionando a la pagina respectiva */
		try {
			this.variableTitulo = null;
	
			this.editableMantenimiento = true;
			this.formMantenimiento = this.setObtenerRegistroAttributes();
			this.setAddAttributes();

			this.redireccionarPagina(this.getPaginaMantenimiento());

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			return;
		}
	}

	/**
	 * @param actionEvent
	 */
	public void addDespachoPedido(ActionEvent actionEvent) {
		if (log.isWarnEnabled()) {
			log.warn("Entering 'add' method");
		}
		this.mostrarBotonDespachoAutomatico = false;
		this.mostrarMatriz = true;
		this.mostrarCuv = false;
		this.renderedCodigoVenta = "false";
		this.mostrarBotonSave = false;
		
		this.accion = this.ACCION_NUEVO;
		this.accionFuncional = this.ACCION_NUEVO;

		/* Redireccionando a la pagina respectiva */
		try {
			this.variableTitulo = "3";
	
			this.editableMantenimiento = true;
			this.formMantenimiento = this.setObtenerRegistroAttributes();
			this.setAddAttributes();

			this.redireccionarPagina(this.getPaginaMantenimiento());
	    } catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			return;
		}
	}

	/**
	 * @return the variableTitulo
	 */
	public String getVariableTitulo() {
		return variableTitulo;
	}

	/**
	 * @param variableTitulo
	 *            the variableTitulo to set
	 */
	public void setVariableTitulo(String variableTitulo) {
		this.variableTitulo = variableTitulo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'edit' method");
		}
		this.mostrarMatriz = false;
		this.mostrarCuv = false;
		this.web = false;
		this.kit = false;
		
		if(this.variableTitulo != null){
			this.mostrarMatriz = true;
			this.mostrarBotonDespachoAutomatico = false;
		}

		DespachoProducto sistemabusqueda = (DespachoProducto) this.beanRegistroSeleccionado;
		this.mostrarBotonSave = false;
		if (sistemabusqueda != null) {
			

			this.renderedCodigoVenta = "true";
			if (!StringUtils.isBlank(sistemabusqueda.getIndicadorKit())) {
				this.variableTitulo = null;
				if (sistemabusqueda.getIndicadorKit().equalsIgnoreCase("1")) {
					this.kit = true;
				} else {
					this.kit = false;
				}
			}

			if (!StringUtils.isBlank(sistemabusqueda.getIndicadorPremioWeb())) {
				this.variableTitulo = null;
				if (sistemabusqueda.getIndicadorPremioWeb().equalsIgnoreCase(
						"1")) {
					this.web = true;
				} else {
					this.web = false;
				}
			}
		}

		this.mostrarLuegoCodigoVenta = false;
		this.renderedCodigoVenta = "false";
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
		MantenimientoCUPDespachoProductoForm despachoProductoForm = new MantenimientoCUPDespachoProductoForm();
		despachoProductoForm.setMostrarIndicadorKit(false);
		despachoProductoForm.setCodigoPais(pais.getCodigo());
		// setViewAttributes(request, form);

		/* INI SA PER-SiCC--2012-0467 */
		Map criteria = new HashMap();

		// obteniendo el numero de niveles permitidos
		String niveles = service.getNumeroNiveles(criteria);

		if (niveles.length() == 1) {
			niveles = '0' + niveles;
		}
		criteria.put("niveles", niveles);
		
		//
		
		
		this.cupNivelList = service.getNivelesPermitidos(criteria);
		
		
		

		despachoProductoForm.setMostrarIndicadorKit(false);
		/* FIN SA PER-SiCC--2012-0467 */
		despachoProductoForm.setNewRecord(false);
		if (!this.accion.equals(this.ACCION_NUEVO)) {
			this.mostrarBotonSave = true;
			despachoProductoForm.setNewRecord(true);
			// this.mostrarBotonModificar = true;
			String id = sistemabusqueda.getId();
			DespachoProducto despachoProducto = service
					.getDespachoProductosById(getCriteria(id));
			BeanUtils.copyProperties(despachoProductoForm, despachoProducto);
			despachoProductoForm.setCodigoValida(Constants.IND_NO_VALIDA);

			/* INI SA PER-SiCC--2012-0467 */
			despachoProductoForm.setMostrarIndicadorKit(false);

			/*************************************************************************/
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("codigoPrograma",
					despachoProductoForm.getCodigoPrograma());

			/* INI SA PER-SiCC-2012-0854 */
			criteria.put("codigoPeriodo",
					despachoProductoForm.getCodigoPeriodo());

			List lstPrgDscto1 = service.getProgramasDsctoByCriteria(criteria);
			String lsIndPremioWeb = ((ProgramaCupon) lstPrgDscto1.get(0))
					.getIndicadorPremioWeb();
			String lsNumPedidos = ((ProgramaCupon) lstPrgDscto1.get(0))
					.getNumeroPedidos();

			if (StringUtils.equals(lsIndPremioWeb, Constants.NUMERO_UNO)
					&& Integer.parseInt(despachoProducto.getCodigoNivel()) >= Integer
							.parseInt(lsNumPedidos)) {
				despachoProductoForm.setMostrarIndicadorPremioWeb(true);
				this.variableTitulo = null;
				this.web = false;

				if (StringUtils.equals(despachoProductoForm.getTipoDespacho(),
						"04")) {
					despachoProductoForm
							.setIndicadorPremioWeb(Constants.NUMERO_UNO);
					this.web = true;
				} else {
					despachoProductoForm
							.setIndicadorPremioWeb(Constants.NUMERO_CERO);
					this.web = false;
				}
			} else {
				despachoProductoForm.setMostrarIndicadorPremioWeb(false);
				despachoProductoForm
						.setIndicadorPremioWeb(Constants.NUMERO_CERO);
				this.web = false;
			}
			/*************************************************************************/

			if (Constants.NUMERO_UNO.equals(despachoProductoForm
					.getIndicadorKit())) {
				despachoProductoForm.setMostrarIndicadorKit(true);
				this.variableTitulo = null;
				this.kit = true;

			} else {

				criteria.put("codigoPais", pais.getCodigo());
				criteria.put("codigoPrograma",
						despachoProductoForm.getCodigoPrograma());

				/* INI SA PER-SiCC-2012-0854 */
				criteria.put("codigoPeriodo",
						despachoProductoForm.getCodigoPeriodo());
				/* FIN SA PER-SiCC-2012-0854 */

				if (despachoProductoForm.getCodigoNivel().equals("01")) {
					if (!service.existenDespachosConIndicadorKit(criteria))
						despachoProductoForm.setMostrarIndicadorKit(true);

				}
				/*
				 * validaMostrarSegundoKit(service, despachoProductoForm,
				 * criteria);
				 */
			}
			/* FIN SA PER-SiCC--2012-0467 */
			// despachoProductoForm.setNewRecord(false);
		} else {
			this.web = false;
			this.kit = false;
			despachoProductoForm.setCodigoValida(Constants.IND_VALIDA_PERIODO);
			this.mostrarBotonSave = false;
		}

		return despachoProductoForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarBotonConsultar = false;
		this.mostrarBotonSave = false;
		this.mostrarBotonNuevo = false;
		MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
		Map criteria = new HashMap();
		criteria.put("codigoPais", getCodigoPais());
		this.cupProgramasList = service.getProgramasDescuentosbyPais(criteria);
		this.renderedCodigoVenta = null;
		this.variableTitulo = null;
		this.cupNivelList = service.getNivelbyPais(criteria);
		this.cupDespachoProductosList = new ArrayList();
		this.mostrarLuegoCodigoVenta = false;
		MantenimientoCUPDespachoProductoForm f = new MantenimientoCUPDespachoProductoForm();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		f.setNewRecord(false);
		this.renderedCodigoVenta = "false";
		this.mostrarMatriz = false;
		this.mostrarCuv = false;
		this.mostrarBotonDespachoAutomatico = false;

	}

	/**
	 * @return the cupDespachoProductosList
	 */
	public List getCupDespachoProductosList() {
		return cupDespachoProductosList;
	}

	/**
	 * @param cupDespachoProductosList
	 *            the cupDespachoProductosList to set
	 */
	public void setCupDespachoProductosList(List cupDespachoProductosList) {
		this.cupDespachoProductosList = cupDespachoProductosList;
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
	 * @return the cupNivelList
	 */
	public List getCupNivelList() {
		return cupNivelList;
	}

	/**
	 * @param cupNivelList
	 *            the cupNivelList to set
	 */
	public void setCupNivelList(List cupNivelList) {
		this.cupNivelList = cupNivelList;
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
	 * @return the popupCodigoVenta
	 */
	public static String getPopupCodigoVenta() {
		return POPUP_CODIGO_VENTA;
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
	 * @return the renderedCodigoVenta
	 */
	public String getRenderedCodigoVenta() {
		return renderedCodigoVenta;
	}

	/**
	 * @param renderedCodigoVenta
	 *            the renderedCodigoVenta to set
	 */
	public void setRenderedCodigoVenta(String renderedCodigoVenta) {
		this.renderedCodigoVenta = renderedCodigoVenta;
	}

	/**
	 * @return the mostrarLuegoCodigoVenta
	 */
	public Boolean getMostrarLuegoCodigoVenta() {
		return mostrarLuegoCodigoVenta;
	}

	/**
	 * @param mostrarLuegoCodigoVenta
	 *            the mostrarLuegoCodigoVenta to set
	 */
	public void setMostrarLuegoCodigoVenta(Boolean mostrarLuegoCodigoVenta) {
		this.mostrarLuegoCodigoVenta = mostrarLuegoCodigoVenta;
	}

	/**
	 * @return the kit
	 */
	public Boolean getKit() {
		return kit;
	}

	/**
	 * @param kit
	 *            the kit to set
	 */
	public void setKit(Boolean kit) {
		this.kit = kit;
	}

	/**
	 * @return the web
	 */
	public Boolean getWeb() {
		return web;
	}

	/**
	 * @param web
	 *            the web to set
	 */
	public void setWeb(Boolean web) {
		this.web = web;
	}

	/**
	 * @return the mostrarMatriz
	 */
	public Boolean getMostrarMatriz() {
		return mostrarMatriz;
	}

	/**
	 * @param mostrarMatriz the mostrarMatriz to set
	 */
	public void setMostrarMatriz(Boolean mostrarMatriz) {
		this.mostrarMatriz = mostrarMatriz;
	}

	/**
	 * @return the mostrarCuv
	 */
	public Boolean getMostrarCuv() {
		return mostrarCuv;
	}

	/**
	 * @param mostrarCuv the mostrarCuv to set
	 */
	public void setMostrarCuv(Boolean mostrarCuv) {
		this.mostrarCuv = mostrarCuv;
	}

	/**
	 * @return the mostrarBotonDespachoAutomatico
	 */
	public Boolean getMostrarBotonDespachoAutomatico() {
		return mostrarBotonDespachoAutomatico;
	}

	/**
	 * @param mostrarBotonDespachoAutomatico the mostrarBotonDespachoAutomatico to set
	 */
	public void setMostrarBotonDespachoAutomatico(
			Boolean mostrarBotonDespachoAutomatico) {
		this.mostrarBotonDespachoAutomatico = mostrarBotonDespachoAutomatico;
	}
	
	
	
}