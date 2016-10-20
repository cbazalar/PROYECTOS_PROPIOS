package biz.belcorp.ssicc.web.spusicc.pre.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.spusicc.pre.model.MatrizRecuperacion;
import biz.belcorp.ssicc.dao.spusicc.pre.model.ProductoMatriz;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.pre.MantenimientoPREMatrizRecuperacionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.pre.form.MantenimientoPREMatrizRecuperacionesForm;
import biz.belcorp.ssicc.web.spusicc.pre.form.MantenimientoPREMatrizRecuperacionesSearchForm;

/**
 * @author Sigcomt
 *
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoPREMatrizRecuperacionesSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -8075897084656307184L;

	private String ambos = Constants.TODAS;
	private String numeroUno = Constants.NUMERO_UNO;
	private String numeroCero = Constants.NUMERO_CERO;
	private List preTipoClienteList;
	private LabelValue[] preSubTipoClienteList;
	private LabelValue[] preTipoClasificacionList;
	private LabelValue[] preClasificacionList;
	private List preRegionList;
	private LabelValue[] preZonaList;

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoPREMatrizRecuperacionesList";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoPREMatrizRecuperacionesForm";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoPREMatrizRecuperacionesSearchForm f = new MantenimientoPREMatrizRecuperacionesSearchForm();
		return f;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		MantenimientoPREMatrizRecuperacionesSearchForm f = (MantenimientoPREMatrizRecuperacionesSearchForm) this.formBusqueda;
		MantenimientoPREMatrizRecuperacionesService service = (MantenimientoPREMatrizRecuperacionesService) getBean("spusicc.mantenimientoPREMatrizRecuperacionesService");

		Map params = BeanUtils.describe(f);

		if (StringUtils.equals(f.getIndicadorActivo(), Constants.TODAS))
			params.put("indicadorActivo", null);

		List lista = service.getRecuperaciones(params);

		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		log.debug("MantenimientoPREMatrizRecuperacionesAction - setSaveAttributes");
		MantenimientoPREMatrizRecuperacionesForm f = (MantenimientoPREMatrizRecuperacionesForm) this.formMantenimiento;
		f.setIndicadorMensaje(f.getIndicadorMensajeBool().equals(true) ? Constants.NUMERO_UNO
				: Constants.NUMERO_CERO);
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		MantenimientoPREMatrizRecuperacionesService service = (MantenimientoPREMatrizRecuperacionesService) getBean("spusicc.mantenimientoPREMatrizRecuperacionesService");

		MatrizRecuperacion matrizRecuperacion = new MatrizRecuperacion();

		if (StringUtils.isBlank(f.getIndicadorMensaje()))
			f.setIndicadorMensaje(Constants.NUMERO_CERO);

		BeanUtils.copyProperties(matrizRecuperacion, f);

		try {
			if (f.isNewRecord()) {
				service.insertRecuperacion(matrizRecuperacion, usuario);
				f.setNewRecord(false);
				this.salirGrabarPantallaPadre  = true;
			}
		} catch (InvalidIdentifierException iie) {
			this.addError(
					"Error: ",
					this.getResourceMessage("mantenimientoPREMatrizRecuperacionesForm.save.error.unique"));
			return false;
		}

		return true;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																// Campanha
																// Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																	// Campanha
																	// activa q
																	// se
																	// procesa
																	// actualmente

		MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		PedidoControlFacturacion controlFacturacion = serviceFact
				.getControlFacturacionById(criteria);

		MantenimientoPREMatrizRecuperacionesForm f = new MantenimientoPREMatrizRecuperacionesForm();
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		f.setCodigoPeriodoFaltante(reporteService
				.getCodigoPeriodoAnterior(controlFacturacion.getCodigoPeriodo()));

		// Carga el combo Tipo cliente
		this.preTipoClienteList = interfazSiCCService
				.getTiposClientesByCodigoISOOID(usuario.getIdioma()
						.getCodigoISO());

		// Carga el combo de Regiones
		this.preRegionList = reporteService.getListaGenerico(
				"getRegionesByPais", criteria);

		return f;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		MantenimientoPREMatrizRecuperacionesSearchForm f = (MantenimientoPREMatrizRecuperacionesSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		limpiarForm(f);

		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", pais.getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO);
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO);

		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service
				.getControlFacturacionById(criteriaPeriodo);

		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());

		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonModificar = false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {
		String mensaje = null;
		if (this.beanRegistroSeleccionado == null) {
			mensaje = this.getResourceMessage("errors.select.item");
		}

		return mensaje;
	}

	/**
	 * @param event
	 */
	public void actualizarEstado(ActionEvent event) {
		try {
			MantenimientoPREMatrizRecuperacionesService service = (MantenimientoPREMatrizRecuperacionesService) getBean("spusicc.mantenimientoPREMatrizRecuperacionesService");
			ExternalContext externalContext = FacesContext.getCurrentInstance()
					.getExternalContext();
			String estado = externalContext.getRequestParameterMap().get(
					"parametroAccion");
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			Map bean = (Map) this.beanRegistroSeleccionado;
			MatrizRecuperacion mr = new MatrizRecuperacion();
			String id = bean.get("oid").toString();

			mr.setOid(id);
			mr.setIndicadorActivo(estado);
			service.updateRecuperacion(mr, usuario);

			this.listaBusqueda = this.setFindAttributes();
			this.datatableBusqueda = new DataTableModel(this.listaBusqueda);

			if (StringUtils.equals(estado, Constants.NUMERO_CERO)) {
				this.addInfo(
						"",
						this.getResourceMessage("mantenimientoPREMatrizRecuperacionesSearchForm.desactivado"));
			} else if (StringUtils.equals(estado, Constants.NUMERO_UNO)) {
				this.addInfo(
						"",
						this.getResourceMessage("mantenimientoPREMatrizRecuperacionesSearchForm.activado"));
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * 
	 */
	public void seteaCodigoVentaPrincipal() {
		try {
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			MantenimientoPREMatrizRecuperacionesForm f = (MantenimientoPREMatrizRecuperacionesForm) this.formMantenimiento;

			if (StringUtils.isNotBlank(f.getCodigoPeriodo())
					&& StringUtils.isNotBlank(f.getCodigoVentaPrincipal())) {
				f.setCodigoVentaPrincipal(StringUtils.leftPad(
						f.getCodigoVentaPrincipal(), 5, "0"));
				ProductoMatriz resultado = ajax.getProductoPREMatrizRecuperaciones(
						f.getCodigoPeriodo(), f.getCodigoVentaPrincipal(), "N");

				if (resultado != null) {
					f.setCodigoSAPPrincipal(resultado.getCodigoSAP());
					f.setDescripcionPrincipal(resultado.getDescripcion());
					f.setOidMatrizRecuperacion(resultado.getOidMatriz());
				} else {
					this.addError("Error: ",
							this.getResourceMessage("mensaje.CUV.noExiste"));
					f.setCodigoVentaPrincipal(null);
					f.setCodigoSAPPrincipal(null);
					f.setDescripcionPrincipal(null);
					f.setOidMatrizRecuperacion(null);
				}
			} else {
				f.setCodigoVentaPrincipal(null);
				f.setCodigoSAPPrincipal(null);
				f.setDescripcionPrincipal(null);
				f.setOidMatrizRecuperacion(null);
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * @param event
	 */
	public void loadSubTiposClientes(ValueChangeEvent event) {
		try {
			String valor = (String) event.getNewValue();
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			ArrayList tipoClientes = new ArrayList();

			if (valor != null) {
				tipoClientes.add(valor);
				this.preSubTipoClienteList = ajax
						.getSubTiposClientesPorPaisTipoClientesOID(
								pais.getCodigoIdiomaIso(), tipoClientes);
				this.preTipoClasificacionList = null;
				this.preClasificacionList = null;
			} else {
				this.preSubTipoClienteList = null;
				this.preTipoClasificacionList = null;
				this.preClasificacionList = null;
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
		
	}

	/**
	 * @param event
	 */
	public void loadTiposClasificaciones(ValueChangeEvent event) {
		try {
			String valor = (String) event.getNewValue();
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			MantenimientoPREMatrizRecuperacionesForm f = (MantenimientoPREMatrizRecuperacionesForm) this.formMantenimiento;
			ArrayList subTipoClientes = new ArrayList();

			if (valor != null) {
				subTipoClientes.add(valor);
				this.preTipoClasificacionList = ajax
						.getTiposClasificacionesByCriteriaMultipleOID(
								pais.getCodigoIdiomaIso(), f.getOidTipoCliente(),
								subTipoClientes);
				this.preClasificacionList = null;
			} else {
				this.preTipoClasificacionList = null;
				this.preClasificacionList = null;
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
		
	}

	/**
	 * @param event
	 */
	public void loadClasificaciones(ValueChangeEvent event) {
		try {
			String valor = (String) event.getNewValue();
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			MantenimientoPREMatrizRecuperacionesForm f = (MantenimientoPREMatrizRecuperacionesForm) this.formMantenimiento;
			ArrayList TipoClasificacion = new ArrayList();
			ArrayList subTipoCliente = new ArrayList();

			if (valor != null) {
				TipoClasificacion.add(valor);
				subTipoCliente.add(f.getOidSubTipoCliente());
				this.preClasificacionList = ajax
						.getClasificacionesByCriteriaMultipleOID(
								pais.getCodigoIdiomaIso(),
								f.getOidTipoCliente(), subTipoCliente,
								TipoClasificacion);
			} else {
				this.preClasificacionList = null;
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * @param event
	 */
	public void loadZonas(ValueChangeEvent event) {
		try {
			String valor = (String) event.getNewValue();
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			String[] codigoRegiones = new String[1];

			if (valor != null) {
				codigoRegiones[0] = valor;
				this.preZonaList = ajax.getZonasMultipleByPaisMarcaCanalRegion(
						pais.getCodigo(), "T", "VD", codigoRegiones, "");
			} else {
				this.preZonaList = null;
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * 
	 */
	public void seteaCodigoVentaRecuperar() {
		try {
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			MantenimientoPREMatrizRecuperacionesForm f = (MantenimientoPREMatrizRecuperacionesForm) this.formMantenimiento;

			if (StringUtils.isNotBlank(f.getCodigoPeriodo())
					&& StringUtils.isNotBlank(f.getCodigoVentaRecuperar())) {
				f.setCodigoVentaRecuperar(StringUtils.leftPad(
						f.getCodigoVentaRecuperar(), 5, "0"));
				ProductoMatriz resultado = ajax
						.getProductoPREMatrizRecuperaciones(
								f.getCodigoPeriodoFaltante(),
								f.getCodigoVentaRecuperar(), "S");

				if (resultado != null) {
					f.setCodigoSAPRecuperar(resultado.getCodigoSAP());
					f.setDescripcionRecuperar(resultado.getDescripcion());
					f.setOidMatrizReemplazo(resultado.getOidMatriz());

					if (resultado.getFlagRecuperableExiste() != null
							&& resultado.getFlagRecuperableExiste().equals("S")) {
						String ventanaConfirmar = "PF('confirmDialogValidar_confirmationDialogConfirmarSalir').show()";
						this.getRequestContext().execute(ventanaConfirmar);
					}
				} else {
					this.addError("Error: ",
							this.getResourceMessage("mensaje.CUV.noExiste"));
					f.setCodigoVentaRecuperar(null);
					f.setCodigoSAPRecuperar(null);
					f.setDescripcionRecuperar(null);
					f.setOidMatrizReemplazo(null);
				}
			} else {
				f.setCodigoVentaRecuperar(null);
				f.setCodigoSAPRecuperar(null);
				f.setDescripcionRecuperar(null);
				f.setOidMatrizReemplazo(null);
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	public void aceptarCodigoVentaPrincipal() {
	}

	/**
	 * 
	 */
	public void cancelarCodigoVentaPrincipal() {
		MantenimientoPREMatrizRecuperacionesForm f = (MantenimientoPREMatrizRecuperacionesForm) this.formMantenimiento;
		f.setCodigoVentaRecuperar(null);
		f.setCodigoSAPRecuperar(null);
		f.setDescripcionRecuperar(null);
		f.setOidMatrizReemplazo(null);
	}

	/**
	 * @param f
	 */
	private void limpiarForm(MantenimientoPREMatrizRecuperacionesSearchForm f) {
		f.setCodigoPeriodo("");
		f.setCodigoVentaPrincipal("");
		f.setCodigoVentaRecuperar("");
		f.setIndicadorActivo(Constants.TODAS);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setValidarMantenimiento()
	 */
	@Override
	public String setValidarMantenimiento() {
		String mensaje = null;
		MantenimientoPREMatrizRecuperacionesForm f = (MantenimientoPREMatrizRecuperacionesForm) this.formMantenimiento;

		if (StringUtils.isBlank(f.getCodigoVentaPrincipal())) {
			mensaje = "'Codigo de Venta Principal' es un campo requerido.";
			return mensaje;
		}

		if (StringUtils.isBlank(f.getCodigoVentaRecuperar())) {
			mensaje = "Codigo de Venta a Recuperar' es un campo requerido.";
			return mensaje;
		}

		return mensaje;
	}

	/**
	 * @return
	 */
	public String getAmbos() {
		return ambos;
	}

	/**
	 * @param ambos
	 */
	public void setAmbos(String ambos) {
		this.ambos = ambos;
	}

	/**
	 * @return
	 */
	public String getNumeroUno() {
		return numeroUno;
	}

	/**
	 * @param numeroUno
	 */
	public void setNumeroUno(String numeroUno) {
		this.numeroUno = numeroUno;
	}

	/**
	 * @return
	 */
	public String getNumeroCero() {
		return numeroCero;
	}

	/**
	 * @param numeroCero
	 */
	public void setNumeroCero(String numeroCero) {
		this.numeroCero = numeroCero;
	}

	/**
	 * @return
	 */
	public List getPreTipoClienteList() {
		return preTipoClienteList;
	}

	/**
	 * @param preTipoClienteList
	 */
	public void setPreTipoClienteList(List preTipoClienteList) {
		this.preTipoClienteList = preTipoClienteList;
	}

	/**
	 * @return
	 */
	public List getPreRegionList() {
		return preRegionList;
	}

	/**
	 * @param preRegionList
	 */
	public void setPreRegionList(List preRegionList) {
		this.preRegionList = preRegionList;
	}

	/**
	 * @return
	 */
	public LabelValue[] getPreSubTipoClienteList() {
		return preSubTipoClienteList;
	}

	/**
	 * @param preSubTipoClienteList
	 */
	public void setPreSubTipoClienteList(LabelValue[] preSubTipoClienteList) {
		this.preSubTipoClienteList = preSubTipoClienteList;
	}

	/**
	 * @return
	 */
	public LabelValue[] getPreTipoClasificacionList() {
		return preTipoClasificacionList;
	}

	/**
	 * @param preTipoClasificacionList
	 */
	public void setPreTipoClasificacionList(
			LabelValue[] preTipoClasificacionList) {
		this.preTipoClasificacionList = preTipoClasificacionList;
	}

	/**
	 * @return
	 */
	public LabelValue[] getPreZonaList() {
		return preZonaList;
	}

	/**
	 * @param preZonaList
	 */
	public void setPreZonaList(LabelValue[] preZonaList) {
		this.preZonaList = preZonaList;
	}

	/**
	 * @return
	 */
	public LabelValue[] getPreClasificacionList() {
		return preClasificacionList;
	}

	/**
	 * @param preClasificacionList
	 */
	public void setPreClasificacionList(LabelValue[] preClasificacionList) {
		this.preClasificacionList = preClasificacionList;
	}
}