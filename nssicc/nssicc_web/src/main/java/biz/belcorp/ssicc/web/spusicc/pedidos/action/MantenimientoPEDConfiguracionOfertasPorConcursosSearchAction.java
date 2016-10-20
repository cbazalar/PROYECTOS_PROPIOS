package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.lang.reflect.InvocationTargetException;
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
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDConfiguracionOfertasPorConcursosService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDConfiguracionOfertasPorConcursosForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDConfiguracionOfertasPorConcursosGratisForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDConfiguracionOfertasPorConcursosSearchForm;

@SessionScoped
@ManagedBean
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoPEDConfiguracionOfertasPorConcursosSearchAction
		extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 4141286895794280982L;
	private static final String TIPO_NIVEL_PRECIO = "Niveles de Precios";
	
	private List pedOfertaConcursosCatalogoList;
	private List pedOfertaConcursosList;
	private List siccCatalogoList;
	private List pedOfertasPorConcursosRangosList;
	private List pedOfertasPorConcursosCriteriosList;
	private String codigoPais;
	private String codigoLogin;
	private String PED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_PRECIOS = Constants.PED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_PRECIOS;
	private String PED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_CONCURSOS = Constants.PED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_CONCURSOS;
	private String PED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_UNIDADES = Constants.PED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_UNIDADES;
	private String PED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_MONTO = Constants.PED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_MONTO;
	private String PED_OFERTAS_POR_CONCURSO_TIPO_RANGO_RANGO = Constants.PED_OFERTAS_POR_CONCURSO_TIPO_RANGO_RANGO;
	private String PED_OFERTAS_POR_CONCURSO_TIPO_RANGO_PRODUCTO = Constants.PED_OFERTAS_POR_CONCURSO_TIPO_RANGO_PRODUCTO;
	private DataTableModel comDetalleTableModel;
	private Object beanRegistroDetalle;
	private DataTableModel comDetalleTableModelCriterio;
	private Object beanRegistroDetalleCriterio;
	private DataTableModel comDetalleTableModelComponente;
	private List beanRegistroDetalleComponente;
	private DataTableModel comDetalleTableModelGratis;
	private Object beanRegistroDetalleGratis;
	private boolean rangoboolean;
	private boolean indicadorExclusionRangoBoolean;
	private boolean esRango;
	private List pedOfertasPorConcursosCriteriosProductosList;
	private String codigoTipoProgramaAnterior;
	private MantenimientoPEDConfiguracionOfertasPorConcursosGratisForm formGratis;
	private List pedOfertasPorConcursosRegalosList;
	private MantenimientoPEDConfiguracionOfertasPorConcursosService service;
	private boolean activarDecimalRangos = false;
	private boolean activarModificarPrecio = false;
	private boolean activarEjecutarCriterio = true;

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoPEDConfiguracionOfertasPorConcursosList";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoPEDConfiguracionOfertasPorConcursosForm";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoPEDConfiguracionOfertasPorConcursosSearchForm form = new MantenimientoPEDConfiguracionOfertasPorConcursosSearchForm();
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
		MantenimientoPEDConfiguracionOfertasPorConcursosSearchForm searchForm = (MantenimientoPEDConfiguracionOfertasPorConcursosSearchForm) this.formBusqueda;
		searchForm.setCodigoPais(this.codigoPais);
		List lista = buscar(searchForm);
		this.pedOfertaConcursosList = lista;
		return lista;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		Map data = (Map) this.beanRegistroSeleccionado;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String oidOferta = (String) data.get("oidOferta");
		String codigoUsuario = usuario.getLogin();
		service.removeOfertaConcursos(oidOferta, codigoUsuario);
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
		MantenimientoPEDConfiguracionOfertasPorConcursosForm editForm = (MantenimientoPEDConfiguracionOfertasPorConcursosForm) this.formMantenimiento;
		Map params = BeanUtils.describe(editForm);
		params.put("codigoUsuario", this.codigoLogin);
		this.codigoTipoProgramaAnterior = editForm.getCodigoTipoPrograma();

		if (editForm.isNewRecord()) {
			this.service.insertOfertaConcursos(params);
			editForm.setOidOferta(MapUtils.getString(params, "oidOferta"));
			editForm.setNewRecord(false);
		} else {
			if (this.activarModificarPrecio)
				modificarRango();
			this.service.updateOfertaConcursos(params);
			return true;
		}

		editForm.setMostrarDetalles(true);
		this.indicadorExclusionRangoBoolean = true;
		this.salirGrabarPantallaPadre=false;
		this.listaBusqueda = new ArrayList();
		this.listaBusqueda = this.setFindAttributes();
		this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		MantenimientoPEDConfiguracionOfertasPorConcursosForm form = new MantenimientoPEDConfiguracionOfertasPorConcursosForm();
		MantenimientoPEDConfiguracionOfertasPorConcursosGratisForm gratisForm = new MantenimientoPEDConfiguracionOfertasPorConcursosGratisForm();
		form.setCodigoPais(this.codigoPais);
		this.pedOfertasPorConcursosRangosList = new ArrayList();
		this.pedOfertasPorConcursosCriteriosList = new ArrayList();
		this.pedOfertasPorConcursosRangosList = new ArrayList();
		this.pedOfertasPorConcursosRegalosList = new ArrayList();
		this.comDetalleTableModel = new DataTableModel(pedOfertasPorConcursosRangosList);
		this.comDetalleTableModelCriterio = new DataTableModel(	this.pedOfertasPorConcursosCriteriosList);
		this.comDetalleTableModelComponente = new DataTableModel(this.pedOfertasPorConcursosCriteriosProductosList);
		this.comDetalleTableModelGratis = new DataTableModel(this.pedOfertasPorConcursosRegalosList);
		this.activarModificarPrecio = false;
		this.activarEjecutarCriterio = true;
		
		if (this.accion.equals(this.ACCION_NUEVO)) {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'setAddAttributes' method");
			}

			Map params = BeanUtils.describe(form);

			// Moneda
			Map moneda = this.service.getMoneda(params);
			form.setNumeroDecimales(MapUtils.getString(moneda,
					"numeroDecimales"));

			ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
			this.siccCatalogoList = reporteService.getListaGenerico(
					"getOidCatalogoProductos", null);
			form.setCodigoPeriodo("");
			form.setNewRecord(true);
			setIndicadorExclusionRangoBoolean(true);
			form.setCodigoTipoRango(Constants.PED_OFERTAS_POR_CONCURSO_TIPO_RANGO_RANGO);
			this.pedOfertasPorConcursosRangosList = null;

		} else {
			form.setNewRecord(false);
			Map data = (Map) this.beanRegistroSeleccionado;
			String oidOferta = data.get("oidOferta").toString();
			String numeroLotePlan = (String)data.get("numeroLotePlan");
			if (StringUtils.isNotBlank(numeroLotePlan))
				this.activarEjecutarCriterio = false;
			Map params = new HashMap();
			params.put("oidOferta", oidOferta);
			params.put("codigoPais", this.codigoPais);
			
			form.setCodigoTipoRango(this.PED_OFERTAS_POR_CONCURSO_TIPO_RANGO_RANGO);
			
			this.esRango = true;

			List ofertas = this.service.getOfertaConcursosList(params);

			if (ofertas != null && ofertas.size() == 1) {
				Map oferta = (Map) ofertas.get(0);
				String tipoNivel = (String)data.get("tipoNivel");
				if (StringUtils.equalsIgnoreCase(tipoNivel, TIPO_NIVEL_PRECIO)) {
					this.activarModificarPrecio = true;
				}

				BeanUtils.copyProperties(form, oferta);

				form.setCodigoPais(this.codigoPais);
				form.setCodigoPeriodo(MapUtils.getString(oferta, "periodo", ""));
				form.setNumeroPagina(MapUtils.getString(oferta, "pagina", ""));

				// Moneda
				Map moneda = this.service.getMoneda(params);
				form.setNumeroDecimales(MapUtils.getString(moneda,
						"numeroDecimales"));

				ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
				this.siccCatalogoList = reporteService.getListaGenerico(
						"getOidCatalogoProductos", null);

				this.pedOfertasPorConcursosRangosList = this.service.getRangoOfertaConcursosList(params);
				
				this.comDetalleTableModel = new DataTableModel(
						this.pedOfertasPorConcursosRangosList);

				this.pedOfertasPorConcursosCriteriosList = this.service
						.getCriterioOfertaConcursosList(oidOferta);
				this.comDetalleTableModelCriterio = new DataTableModel(
						this.pedOfertasPorConcursosCriteriosList);

				params.put("codigoPeriodo", form.getCodigoPeriodo());
				this.pedOfertasPorConcursosCriteriosProductosList = this.service
						.getProductoOfertaConcursosList1(params);
				this.comDetalleTableModelComponente = new DataTableModel(
						this.pedOfertasPorConcursosCriteriosProductosList);

				form.setMostrarDetalles(true);
				String codigoTipoPrograma = form.getCodigoTipoPrograma();
				if (codigoTipoPrograma.equals("1")) {
					this.rangoboolean = false;
				} else {
					this.rangoboolean = true;
				}
			}
		}
		this.formGratis = gratisForm;

		return form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.activarModificarPrecio = false;
		MantenimientoPEDConfiguracionOfertasPorConcursosSearchForm searchForm = (MantenimientoPEDConfiguracionOfertasPorConcursosSearchForm) this.formBusqueda;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		this.pedOfertaConcursosCatalogoList = reporteService.getListaGenerico(
				"getCatalogoProductos", null);
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		this.codigoPais = pais.getCodigo();
		this.codigoLogin = usuario.getLogin();
		this.service = (MantenimientoPEDConfiguracionOfertasPorConcursosService) getBean("spusicc.mantenimientoPEDConfiguracionOfertasPorConcursosService");

		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", this.codigoPais);

		// Indica Campanha Activa
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO);
		// Indica Campanha activa q se procesa actualmente
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO);

		MantenimientoOCRPedidoControlFacturacionService controlFactService = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = controlFactService
				.getControlFacturacionById(criteriaPeriodo);

		searchForm.setCodigoPais(this.codigoPais);
		searchForm.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		searchForm.setNumeroPagina(null);
		searchForm.setCodigoCatalogo(null);

		List lista = buscar(searchForm);
		this.pedOfertaConcursosList = lista;
		this.listaBusqueda = lista;
		this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
		this.mostrarBotonConsultar = false;
		this.esRango = true;
		this.activarDecimalRangos = false;
	}

	/**
	 * @param form
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private List buscar(
			MantenimientoPEDConfiguracionOfertasPorConcursosSearchForm form)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		Map params = BeanUtils.describe(form);

		List lista = (List) this.service.getOfertaConcursosList(params);

		return lista;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction
	 * #devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoPEDConfiguracionOfertasPorConcursosForm form = (MantenimientoPEDConfiguracionOfertasPorConcursosForm) this.formMantenimiento;
		boolean isNew = form.isNewRecord();
		if (isNew) {
			return "mantenimientoPEDConfiguracionOfertasPorConcursosForm.oferta.grabada";
		} else {
			return "mantenimientoPEDConfiguracionOfertasPorConcursosForm.oferta.actualizada";
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setValidarConfirmar(java.lang.String)
	 */
	public String setValidarConfirmar(String accion) {
		MantenimientoPEDConfiguracionOfertasPorConcursosForm form = (MantenimientoPEDConfiguracionOfertasPorConcursosForm) this.formMantenimiento;
		MantenimientoPEDConfiguracionOfertasPorConcursosGratisForm f = (MantenimientoPEDConfiguracionOfertasPorConcursosGratisForm) this.formGratis;
		if (accion.equals("INSERTAR_DETALLE")) {
			if (StringUtils.isBlank(form.getRangoInferior()))
				return "'Rango Inferior' es un campo requerido";
			if (StringUtils.isBlank(form.getRangoSuperior()))
				return "'Rango Superior' es un campo requerido";
			if (form.getCodigoTipoPrograma()
					.equals(Constants.PED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_PRECIOS)) {
				if (StringUtils.isBlank(form.getPrecioUnitario()))
					return "'Precio Unitario' es un campo requerido";
			}
			double rangoInferior = Double.parseDouble(form.getRangoInferior());
			double rangoSuperior = Double.parseDouble(form.getRangoSuperior());	
			if (rangoInferior > rangoSuperior)
				return "Rango Superior debe ser mayor o igual que el Rango Inferior";
		} else if (accion.equals("INSERTAR_GRATIS")) {
			if (StringUtils.isBlank(f.getCuv()))
				return "'CUV' es un campo requerido";
			if (StringUtils.isBlank(f.getUnidades()))
				return "'Unidades a Entregar' es un campo requerido";

		} else if (accion.equals("ELIMINAR_GRATIS")) {

		} else if (accion.equals("ELIMINAR_DETALLE_CRITERIO")) {
			if (this.beanRegistroDetalleCriterio == null)
				return this.getResourceMessage("errors.select.item");
		} else if (accion.equals("ELIMINAR_DETALLE")) {
			if (this.beanRegistroDetalle == null)
				return this.getResourceMessage("errors.select.item");
		} else if (accion.equals("INSERTAR_DETALLE_CRITERIO")){
			if (StringUtils.isBlank(form.getCodigoTipoRango()))
				return "'Tipo de Rango' es un campo requerido";
			if (StringUtils.isBlank(form.getOidCatalogoCriterio()))
				return "Catálogo' es un campo requerido";
			
			if(StringUtils.equals(form.getCodigoTipoRango(), PED_OFERTAS_POR_CONCURSO_TIPO_RANGO_PRODUCTO)){
				if (this.beanRegistroDetalleComponente.size() <= 0)
					return this.getResourceMessage("errors.select.item");
			}
		}
		return null;
	}

	/**
	 * @param actionEvent
	 */
	public void agregarrango(ActionEvent actionEvent) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'agregarrango' method");
		}

		MantenimientoPEDConfiguracionOfertasPorConcursosForm editForm = (MantenimientoPEDConfiguracionOfertasPorConcursosForm) this.formMantenimiento;
		List rangoList = this.pedOfertasPorConcursosRangosList;

		if (rangoList == null)
			rangoList = new ArrayList();

		Map rango = new HashMap();

		rango.put("oidOferta", editForm.getOidOferta());
		rango.put("rangoInferior", editForm.getRangoInferior());
		rango.put("rangoSuperior", editForm.getRangoSuperior());
		rango.put("precioUnitario", editForm.getPrecioUnitario());
		rango.put("codigoUsuario", codigoLogin);

		// Insertamos en BD
		this.service.insertRangoOfertaConcursos(rango);
		//

		rangoList.add(rango);

		this.pedOfertasPorConcursosRangosList = rangoList;
		this.comDetalleTableModel = new DataTableModel(
				this.pedOfertasPorConcursosRangosList);
		editForm.setRangoInferior("");
		editForm.setRangoSuperior("");
		editForm.setPrecioUnitario("");
		editForm.setMostrarDetalles(true);

	}
	
	/**
	 * @param evt
	 */
	public void modificarRango() throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'modificarRango' method");
		}
		MantenimientoPEDConfiguracionOfertasPorConcursosForm editForm = (MantenimientoPEDConfiguracionOfertasPorConcursosForm) this.formMantenimiento;
		
		List rangoList = this.pedOfertasPorConcursosRangosList;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		if (rangoList == null) {
			return;
		}
		
		String retorno = this.service.updateRangoOfertaConcursos(rangoList, usuario.getLogin());
		if (StringUtils.isNotBlank(retorno)) 
			throw new Exception(retorno);
		return;
	}
	

	/**
	 * @param actionEvent
	 */
	public void eliminarrango(ActionEvent actionEvent) {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'eliminarrango' method");
		}

		Map data = (Map) this.beanRegistroDetalle;

		String oidRango = (String) data.get("oidRango");
		String codigoUsuario = this.mPantallaPrincipalBean.getCurrentUser().getLogin();
		if (oidRango != null) {

			// Eliminados de BD
			this.service.removeRangoOfertaConcursos(oidRango, codigoUsuario);

		}
		List rangoList = this.pedOfertasPorConcursosRangosList;
		rangoList.remove(data);

	}

	/**
	 * Habilita / deshabilta el campo Precio
	 * @param val
	 */
	public void habilitarPrecio(ValueChangeEvent val) {
		boolean activar = false;
		MantenimientoPEDConfiguracionOfertasPorConcursosForm editForm = (MantenimientoPEDConfiguracionOfertasPorConcursosForm) this.formMantenimiento;
		if (this.pedOfertasPorConcursosRangosList != null
				&& this.pedOfertasPorConcursosRangosList.size() > 0) {
			String valor = (String) val.getOldValue();
			editForm.setCodigoTipoPrograma(valor);
			this.addError(
					"Error",
					this.getResourceMessage("mantenimientoPEDConfiguracionOfertasPorConcursosForm.codigoTipoPrograma.error"));
			if (StringUtils.isNotBlank(this.codigoTipoProgramaAnterior)) {
				editForm.setCodigoTipoPrograma(this.codigoTipoProgramaAnterior);
			}

		} else {
			String valor2 = (String) val.getNewValue();
			if (valor2.equals("1")) {
				this.rangoboolean = false;
				activar = false;
			} else {
				this.rangoboolean = true;
				String codigoTipoCuadre = editForm.getCodigoTipoCuadre();
				if (StringUtils.equals(codigoTipoCuadre, this.PED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_MONTO)) {
					activar = true;
				}
			}
		}
		
		if (activar != this.activarDecimalRangos) {
			editForm.setRangoInferior("");
			editForm.setRangoSuperior("");
		}
		this.activarDecimalRangos = activar;
	}
	
	/**
	 * De acuerdo a la logica permite o no ingresar decimales en los campos Rangos
	 * @param val
	 */
	public void habilitarDecimalRango(ValueChangeEvent val) {
		boolean activar = false;
		MantenimientoPEDConfiguracionOfertasPorConcursosForm editForm = (MantenimientoPEDConfiguracionOfertasPorConcursosForm) this.formMantenimiento;
	
		String valor2 = (String) val.getNewValue();
		if (valor2.equals(this.PED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_MONTO)) {
			
			String codigoTipoPrograma = editForm.getCodigoTipoPrograma();
			if (StringUtils.equals(codigoTipoPrograma, this.PED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_CONCURSOS)) {
				activar = true;
			}
		}
		
		if (activar != this.activarDecimalRangos) {
			editForm.setRangoInferior("");
			editForm.setRangoSuperior("");
		}
		this.activarDecimalRangos = activar;
	}
	

	/**
	 * 
	 * @param actionEvent
	 * @throws
	 * @throws IllegalAccessException
	 */
	public void abrirPopup(ActionEvent event) throws Exception {
		log.debug("Executing action : setViewAttributes. ");

		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		String datos = externalContext.getRequestParameterMap().get("DATOS");
		MantenimientoPEDConfiguracionOfertasPorConcursosForm f = (MantenimientoPEDConfiguracionOfertasPorConcursosForm) this.formMantenimiento;
		String oidOferta = f.getOidOferta();
		String oidRango = datos.split(";")[0];
		String codigoPeriodo = f.getCodigoPeriodo();

		MantenimientoPEDConfiguracionOfertasPorConcursosGratisForm gratisForm = new MantenimientoPEDConfiguracionOfertasPorConcursosGratisForm();

		if (log.isDebugEnabled())
			log.debug("oidOferta: " + oidOferta);

		Map params = new HashMap();
		params.put("oidOferta", oidOferta);
		params.put("oidRango", oidRango);
		List rangos = this.service.getRangoOfertaConcursosList(params);

		if (rangos != null && rangos.size() > 0) {
			Map rango = (Map) rangos.get(0);

			BeanUtils.copyProperties(gratisForm, rango);
		}

		// Lista
		List gratis = this.service.getRangoGratisOfertaConcursosList(params);
		this.pedOfertasPorConcursosRegalosList = gratis;
		this.comDetalleTableModelGratis = new DataTableModel(
				this.pedOfertasPorConcursosRegalosList);
		gratisForm.setCodigoPeriodo(codigoPeriodo);
		gratisForm.setOidRango(oidRango);
		gratisForm.setCuv(null);
		gratisForm.setUnidades("");

		this.formGratis = gratisForm;
		this.getRequestContext().execute("PF('viewOfertasPopup').show()");
	}

	/**
	 * @param val
	 */
	public void loadTipoRango(ValueChangeEvent val) {
		if(val.getNewValue() == null){
			return;
		}
		try {
			String valor = (String) val.getNewValue();
			MantenimientoPEDConfiguracionOfertasPorConcursosForm editForm = (MantenimientoPEDConfiguracionOfertasPorConcursosForm) this.formMantenimiento;

			editForm.setNumeroPaginaInicial("");
			editForm.setNumeroPaginaFinal("");
			editForm.setCodigoProducto("");

			if (!StringUtils.isBlank(valor)) {
				if (valor.equals("R")) {
					
					this.esRango = true;
					
				} else{
					this.esRango = false;
					this.indicadorExclusionRangoBoolean = true;	
				}
					
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	

	}

	/**
	 * @param val
	 */
	public void agregarcriterio(ActionEvent val) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'agregarcriterio' method");
			}

			MantenimientoPEDConfiguracionOfertasPorConcursosForm editForm = (MantenimientoPEDConfiguracionOfertasPorConcursosForm) this.formMantenimiento;
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			List criterioList = this.pedOfertasPorConcursosCriteriosList;
			
			List productosSeleccionados = this.beanRegistroDetalleComponente;

			if (criterioList == null)
				criterioList = new ArrayList();

			if (editForm.getCodigoTipoRango().equals(Constants.PED_OFERTAS_POR_CONCURSO_TIPO_RANGO_RANGO)) {
				editForm.setIndicadorExclusionRango(Constants.NUMERO_CERO);
			} else {
				if (this.indicadorExclusionRangoBoolean == true)
					editForm.setIndicadorExclusionRango(Constants.NUMERO_UNO);
				else
					editForm.setIndicadorExclusionRango(Constants.NUMERO_CERO);
			}

			Map criterio = new HashMap();
			criterio.put("oidOferta", editForm.getOidOferta());
			criterio.put("oidCatalogoCriterio", editForm.getOidCatalogoCriterio());
			criterio.put("codigoTipoRango", editForm.getCodigoTipoRango());
			criterio.put("numeroPaginaInicial", editForm.getNumeroPaginaInicial());
			criterio.put("numeroPaginaFinal", editForm.getNumeroPaginaFinal());
			criterio.put("indicadorExclusionRango", StringUtils.isBlank(editForm.getIndicadorExclusionRango()) ? Constants.NUMERO_CERO
					: editForm.getIndicadorExclusionRango());
			criterio.put("codigoUsuario", codigoLogin);

			String oidProducto = null;
//			if (StringUtils.isNotBlank(editForm.getCodigoProducto())) {
//				// validamos si el producto ingresado existe
//				oidProducto = ajaxService.validarCodigoSAP(codigoPais, editForm.getCodigoProducto());
//
//				if (StringUtils.isBlank(oidProducto)) {
//					this.addError("Error", "El producto no existe");
//					return;
//				}
//			}
			
			if(editForm.getCodigoTipoRango().equals(Constants.PED_OFERTAS_POR_CONCURSO_TIPO_RANGO_PRODUCTO)){
				for (int i = 0; i < productosSeleccionados.size(); i++) {
					Map criterioSeleccionado = (Map) productosSeleccionados.get(i);
					
					oidProducto = ajaxService.validarCodigoSAP(codigoPais, MapUtils.getString(criterioSeleccionado, "codigoSap"));
					criterio.put("oidProducto", oidProducto);

					// Insertamos en BD
					this.service.insertCriterioOfertaConcursos(criterio);
					//
				}
			}else{
				criterio.put("oidProducto", oidProducto);
				this.service.insertCriterioOfertaConcursos(criterio);
			}

			criterioList = this.service.getCriterioOfertaConcursosList(editForm.getOidOferta());

			this.pedOfertasPorConcursosCriteriosList = criterioList;
			this.comDetalleTableModelCriterio = new DataTableModel(this.pedOfertasPorConcursosCriteriosList);

			// Invocamos al proceso de calculo de productos
			executeCalcularProductos(editForm, this.service);
			//

			editForm.setNumeroPaginaInicial("");
			editForm.setNumeroPaginaFinal("");
			editForm.setCodigoProducto("");
			// editForm.setIndicadorExclusionRango(Constants.NUMERO_CERO);
			editForm.setOidCatalogoCriterio("");
			editForm.setCodigoTipoRango("");
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	
	}

	public void eliminarcriterio(ActionEvent val) {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'eliminarcriterio' method");
		}

		MantenimientoPEDConfiguracionOfertasPorConcursosForm editForm = (MantenimientoPEDConfiguracionOfertasPorConcursosForm) this.formMantenimiento;
		Map data = (Map) this.beanRegistroDetalleCriterio;
		String oidCriterio = (String) data.get("oidCriterio");
		if (oidCriterio != null) {
			
			String codigoUsuario = this.mPantallaPrincipalBean.getCurrentUser().getLogin();
			// Eliminados de BD
			this.service.removeCriterioOfertaConcursos(oidCriterio, codigoUsuario);
			//

		}

		List criterioList = this.pedOfertasPorConcursosCriteriosList;
		criterioList.remove(data);

		// Invocamos al proceso de calculo de productos
		executeCalcularProductos(editForm, this.service);
		//

	}

	public void calcularproductos(ActionEvent val) {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'calcularproductos' method");
		}

		MantenimientoPEDConfiguracionOfertasPorConcursosForm editForm = (MantenimientoPEDConfiguracionOfertasPorConcursosForm) this.formMantenimiento;

		try {

			executeCalcularProductos(editForm, this.service);

			this.getResourceMessage("mantenimientoPEDConfiguracionOfertasPorConcursosForm.criterio.productos.calculados");
		} catch (Exception ex) {
			this.addError("Error : ", this.obtieneMensajeErrorException(ex));
		}
		editForm.setMostrarDetalles(true);

	}

	/**
	 * @param editForm
	 * @param service
	 */
	public void executeCalcularProductos(
			MantenimientoPEDConfiguracionOfertasPorConcursosForm editForm,
			MantenimientoPEDConfiguracionOfertasPorConcursosService service) {
		try {
			Map params = new HashMap();
			params.put("oidOferta", editForm.getOidOferta());
			params.put("codigoPeriodo", editForm.getCodigoPeriodo());
			params.put("codigoUsuario", this.codigoLogin);

			List productoList = service.executeCalcularProductos(params);
			this.pedOfertasPorConcursosCriteriosProductosList = productoList;
			this.comDetalleTableModelComponente = new DataTableModel(
					this.pedOfertasPorConcursosCriteriosProductosList);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
		

	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void agregargratis(ActionEvent event) throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'agregargratis' method");
		}

		MantenimientoPEDConfiguracionOfertasPorConcursosGratisForm gratisForm = (MantenimientoPEDConfiguracionOfertasPorConcursosGratisForm) this.formGratis;
		try {
			List regaloList = this.pedOfertasPorConcursosRegalosList;

			if (regaloList == null)
				regaloList = new ArrayList();

			Map regalo = new HashMap();
			regalo.put("oidRango", gratisForm.getOidRango());
			regalo.put("unidades", gratisForm.getUnidades());
			regalo.put("correlativo", regaloList.size() + 1);
			regalo.put("codigoUsuario", this.codigoLogin);

			// Buscamos el CUV
			Map params = new HashMap();
			params.put("codigoPeriodo", gratisForm.getCodigoPeriodo());
			params.put("cuv", gratisForm.getCuv());

			List cuvList = this.service.getCuvs(params);

			if (cuvList != null && cuvList.size() > 0) {
				Map cuv = (Map) cuvList.get(0);
				regalo.put("oidDetalleOferta",
						MapUtils.getString(cuv, "oidDetalleOferta"));
			} else {
				throw new Exception(
						this.getResourceMessage("mantenimientoPEDConfiguracionOfertasPorConcursosGratisForm.error.cuv.noexiste"));

			}
			//

			// Insertamos en BD
			this.service.insertRangoGratisOfertaConcursos(regalo);
			//

			regaloList = this.service.getRangoGratisOfertaConcursosList(regalo);

			this.pedOfertasPorConcursosRegalosList = regaloList;

			gratisForm.setCuv("");
			gratisForm.setUnidades("");
			this.comDetalleTableModelGratis = new DataTableModel(
					this.pedOfertasPorConcursosRegalosList);
			gratisForm.setUnidades("");
			gratisForm.setCuv("");
			this.addInfo(
					"Ok",
					this.getResourceMessage("mantenimientoPEDConfiguracionOfertasPorConcursosGratisForm.regalo.agregado"));
		} catch (Exception e) {
			this.addError("Error", obtieneMensajeErrorException(e));
		}

	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void eliminargratis(ActionEvent event) throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'eliminargratis' method");
		}

		if (this.beanRegistroDetalleGratis == null) {
			this.addWarn("Error : ",
					this.getResourceMessage("errors.select.item"));
			return;
		}

		MantenimientoPEDConfiguracionOfertasPorConcursosGratisForm gratisForm = (MantenimientoPEDConfiguracionOfertasPorConcursosGratisForm) this.formGratis;
		Map data = (Map) this.beanRegistroDetalleGratis;
		String oidGratis = data.get("oidGratis").toString();
		if (StringUtils.isNotBlank(oidGratis)) {
			if (log.isDebugEnabled())
				log.debug("oidGratis: " + oidGratis);
			Map params = BeanUtils.describe(gratisForm);
			String codigoUsuario = this.mPantallaPrincipalBean.getCurrentUser().getLogin();
			
			this.service.removeRangoGratisOfertaConcursos(oidGratis, codigoUsuario);
			// Lista
			List regalos = this.service.getRangoGratisOfertaConcursosList(params);
			this.pedOfertasPorConcursosRegalosList = regalos;
		}
		this.comDetalleTableModelGratis = new DataTableModel(this.pedOfertasPorConcursosRegalosList);
		this.addInfo("Ok",	this.getResourceMessage("mantenimientoPEDConfiguracionOfertasPorConcursosGratisForm.regalo.eliminado"));
	}

	/**
	 * @return the pedOfertaConcursosCatalogoList
	 */
	public List getPedOfertaConcursosCatalogoList() {
		return pedOfertaConcursosCatalogoList;
	}

	/**
	 * @param pedOfertaConcursosCatalogoList
	 *            the pedOfertaConcursosCatalogoList to set
	 */
	public void setPedOfertaConcursosCatalogoList(
			List pedOfertaConcursosCatalogoList) {
		this.pedOfertaConcursosCatalogoList = pedOfertaConcursosCatalogoList;
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the pedOfertaConcursosList
	 */
	public List getPedOfertaConcursosList() {
		return pedOfertaConcursosList;
	}

	/**
	 * @param pedOfertaConcursosList
	 *            the pedOfertaConcursosList to set
	 */
	public void setPedOfertaConcursosList(List pedOfertaConcursosList) {
		this.pedOfertaConcursosList = pedOfertaConcursosList;
	}

	/**
	 * @return the siccCatalogoList
	 */
	public List getSiccCatalogoList() {
		return siccCatalogoList;
	}

	/**
	 * @param siccCatalogoList
	 *            the siccCatalogoList to set
	 */
	public void setSiccCatalogoList(List siccCatalogoList) {
		this.siccCatalogoList = siccCatalogoList;
	}

	/**
	 * @return the pED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_PRECIOS
	 */
	public String getPED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_PRECIOS() {
		return PED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_PRECIOS;
	}

	/**
	 * @param pED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_PRECIOS
	 *            the pED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_PRECIOS to
	 *            set
	 */
	public void setPED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_PRECIOS(
			String pED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_PRECIOS) {
		PED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_PRECIOS = pED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_PRECIOS;
	}

	/**
	 * @return the pED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_CONCURSOS
	 */
	public String getPED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_CONCURSOS() {
		return PED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_CONCURSOS;
	}

	/**
	 * @param pED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_CONCURSOS
	 *            the pED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_CONCURSOS
	 *            to set
	 */
	public void setPED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_CONCURSOS(
			String pED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_CONCURSOS) {
		PED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_CONCURSOS = pED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_CONCURSOS;
	}

	/**
	 * @return the pED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_UNIDADES
	 */
	public String getPED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_UNIDADES() {
		return PED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_UNIDADES;
	}

	/**
	 * @param pED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_UNIDADES
	 *            the pED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_UNIDADES to set
	 */
	public void setPED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_UNIDADES(
			String pED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_UNIDADES) {
		PED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_UNIDADES = pED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_UNIDADES;
	}

	/**
	 * @return the pED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_MONTO
	 */
	public String getPED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_MONTO() {
		return PED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_MONTO;
	}

	/**
	 * @param pED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_MONTO
	 *            the pED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_MONTO to set
	 */
	public void setPED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_MONTO(
			String pED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_MONTO) {
		PED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_MONTO = pED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_MONTO;
	}

	/**
	 * @return the comDetalleTableModel
	 */
	public DataTableModel getComDetalleTableModel() {
		return comDetalleTableModel;
	}

	/**
	 * @param comDetalleTableModel
	 *            the comDetalleTableModel to set
	 */
	public void setComDetalleTableModel(DataTableModel comDetalleTableModel) {
		this.comDetalleTableModel = comDetalleTableModel;
	}

	/**
	 * @return the beanRegistroDetalle
	 */
	public Object getBeanRegistroDetalle() {
		return beanRegistroDetalle;
	}

	/**
	 * @param beanRegistroDetalle
	 *            the beanRegistroDetalle to set
	 */
	public void setBeanRegistroDetalle(Object beanRegistroDetalle) {
		this.beanRegistroDetalle = beanRegistroDetalle;
	}

	/**
	 * @return the pedOfertasPorConcursosRangosList
	 */
	public List getPedOfertasPorConcursosRangosList() {
		return pedOfertasPorConcursosRangosList;
	}

	/**
	 * @param pedOfertasPorConcursosRangosList
	 *            the pedOfertasPorConcursosRangosList to set
	 */
	public void setPedOfertasPorConcursosRangosList(
			List pedOfertasPorConcursosRangosList) {
		this.pedOfertasPorConcursosRangosList = pedOfertasPorConcursosRangosList;
	}

	/**
	 * @return the rangoboolean
	 */
	public boolean isRangoboolean() {
		return rangoboolean;
	}

	/**
	 * @param rangoboolean
	 *            the rangoboolean to set
	 */
	public void setRangoboolean(boolean rangoboolean) {
		this.rangoboolean = rangoboolean;
	}

	/**
	 * @return the pED_OFERTAS_POR_CONCURSO_TIPO_RANGO_RANGO
	 */
	public String getPED_OFERTAS_POR_CONCURSO_TIPO_RANGO_RANGO() {
		return PED_OFERTAS_POR_CONCURSO_TIPO_RANGO_RANGO;
	}

	/**
	 * @param pED_OFERTAS_POR_CONCURSO_TIPO_RANGO_RANGO
	 *            the pED_OFERTAS_POR_CONCURSO_TIPO_RANGO_RANGO to set
	 */
	public void setPED_OFERTAS_POR_CONCURSO_TIPO_RANGO_RANGO(
			String pED_OFERTAS_POR_CONCURSO_TIPO_RANGO_RANGO) {
		PED_OFERTAS_POR_CONCURSO_TIPO_RANGO_RANGO = pED_OFERTAS_POR_CONCURSO_TIPO_RANGO_RANGO;
	}

	/**
	 * @return the pED_OFERTAS_POR_CONCURSO_TIPO_RANGO_PRODUCTO
	 */
	public String getPED_OFERTAS_POR_CONCURSO_TIPO_RANGO_PRODUCTO() {
		return PED_OFERTAS_POR_CONCURSO_TIPO_RANGO_PRODUCTO;
	}

	/**
	 * @param pED_OFERTAS_POR_CONCURSO_TIPO_RANGO_PRODUCTO
	 *            the pED_OFERTAS_POR_CONCURSO_TIPO_RANGO_PRODUCTO to set
	 */
	public void setPED_OFERTAS_POR_CONCURSO_TIPO_RANGO_PRODUCTO(
			String pED_OFERTAS_POR_CONCURSO_TIPO_RANGO_PRODUCTO) {
		PED_OFERTAS_POR_CONCURSO_TIPO_RANGO_PRODUCTO = pED_OFERTAS_POR_CONCURSO_TIPO_RANGO_PRODUCTO;
	}

	/**
	 * @return the indicadorExclusionRangoBoolean
	 */
	public boolean isIndicadorExclusionRangoBoolean() {
		return indicadorExclusionRangoBoolean;
	}

	/**
	 * @param indicadorExclusionRangoBoolean
	 *            the indicadorExclusionRangoBoolean to set
	 */
	public void setIndicadorExclusionRangoBoolean(
			boolean indicadorExclusionRangoBoolean) {
		this.indicadorExclusionRangoBoolean = indicadorExclusionRangoBoolean;
	}

	/**
	 * @return the esRango
	 */
	public boolean isEsRango() {
		return esRango;
	}

	/**
	 * @param esRango
	 *            the esRango to set
	 */
	public void setEsRango(boolean esRango) {
		this.esRango = esRango;
	}

	/**
	 * @return the pedOfertasPorConcursosCriteriosList
	 */
	public List getPedOfertasPorConcursosCriteriosList() {
		return pedOfertasPorConcursosCriteriosList;
	}

	/**
	 * @param pedOfertasPorConcursosCriteriosList
	 *            the pedOfertasPorConcursosCriteriosList to set
	 */
	public void setPedOfertasPorConcursosCriteriosList(
			List pedOfertasPorConcursosCriteriosList) {
		this.pedOfertasPorConcursosCriteriosList = pedOfertasPorConcursosCriteriosList;
	}

	/**
	 * @return the codigoLogin
	 */
	public String getCodigoLogin() {
		return codigoLogin;
	}

	/**
	 * @param codigoLogin
	 *            the codigoLogin to set
	 */
	public void setCodigoLogin(String codigoLogin) {
		this.codigoLogin = codigoLogin;
	}

	/**
	 * @return the comDetalleTableModelCriterio
	 */
	public DataTableModel getComDetalleTableModelCriterio() {
		return comDetalleTableModelCriterio;
	}

	/**
	 * @param comDetalleTableModelCriterio
	 *            the comDetalleTableModelCriterio to set
	 */
	public void setComDetalleTableModelCriterio(
			DataTableModel comDetalleTableModelCriterio) {
		this.comDetalleTableModelCriterio = comDetalleTableModelCriterio;
	}

	/**
	 * @return the beanRegistroDetalleCriterio
	 */
	public Object getBeanRegistroDetalleCriterio() {
		return beanRegistroDetalleCriterio;
	}

	/**
	 * @param beanRegistroDetalleCriterio
	 *            the beanRegistroDetalleCriterio to set
	 */
	public void setBeanRegistroDetalleCriterio(
			Object beanRegistroDetalleCriterio) {
		this.beanRegistroDetalleCriterio = beanRegistroDetalleCriterio;
	}

	/**
	 * @return the pedOfertasPorConcursosCriteriosProductosList
	 */
	public List getPedOfertasPorConcursosCriteriosProductosList() {
		return pedOfertasPorConcursosCriteriosProductosList;
	}

	/**
	 * @param pedOfertasPorConcursosCriteriosProductosList
	 *            the pedOfertasPorConcursosCriteriosProductosList to set
	 */
	public void setPedOfertasPorConcursosCriteriosProductosList(
			List pedOfertasPorConcursosCriteriosProductosList) {
		this.pedOfertasPorConcursosCriteriosProductosList = pedOfertasPorConcursosCriteriosProductosList;
	}

	/**
	 * @return the comDetalleTableModelComponente
	 */
	public DataTableModel getComDetalleTableModelComponente() {
		return comDetalleTableModelComponente;
	}

	/**
	 * @param comDetalleTableModelComponente
	 *            the comDetalleTableModelComponente to set
	 */
	public void setComDetalleTableModelComponente(
			DataTableModel comDetalleTableModelComponente) {
		this.comDetalleTableModelComponente = comDetalleTableModelComponente;
	}

	/**
	 * @return the codigoTipoProgramaAnterior
	 */
	public String getCodigoTipoProgramaAnterior() {
		return codigoTipoProgramaAnterior;
	}

	/**
	 * @param codigoTipoProgramaAnterior
	 *            the codigoTipoProgramaAnterior to set
	 */
	public void setCodigoTipoProgramaAnterior(String codigoTipoProgramaAnterior) {
		this.codigoTipoProgramaAnterior = codigoTipoProgramaAnterior;
	}

	/**
	 * @return the formGratis
	 */
	public MantenimientoPEDConfiguracionOfertasPorConcursosGratisForm getFormGratis() {
		return formGratis;
	}

	/**
	 * @param formGratis
	 *            the formGratis to set
	 */
	public void setFormGratis(
			MantenimientoPEDConfiguracionOfertasPorConcursosGratisForm formGratis) {
		this.formGratis = formGratis;
	}

	/**
	 * @return the pedOfertasPorConcursosRegalosList
	 */
	public List getPedOfertasPorConcursosRegalosList() {
		return pedOfertasPorConcursosRegalosList;
	}

	/**
	 * @param pedOfertasPorConcursosRegalosList
	 *            the pedOfertasPorConcursosRegalosList to set
	 */
	public void setPedOfertasPorConcursosRegalosList(
			List pedOfertasPorConcursosRegalosList) {
		this.pedOfertasPorConcursosRegalosList = pedOfertasPorConcursosRegalosList;
	}

	/**
	 * @return the service
	 */
	public MantenimientoPEDConfiguracionOfertasPorConcursosService getService() {
		return service;
	}

	/**
	 * @param service
	 *            the service to set
	 */
	public void setService(
			MantenimientoPEDConfiguracionOfertasPorConcursosService service) {
		this.service = service;
	}

	/**
	 * @return the comDetalleTableModelGratis
	 */
	public DataTableModel getComDetalleTableModelGratis() {
		return comDetalleTableModelGratis;
	}

	/**
	 * @param comDetalleTableModelGratis
	 *            the comDetalleTableModelGratis to set
	 */
	public void setComDetalleTableModelGratis(
			DataTableModel comDetalleTableModelGratis) {
		this.comDetalleTableModelGratis = comDetalleTableModelGratis;
	}

	/**
	 * @return the beanRegistroDetalleGratis
	 */
	public Object getBeanRegistroDetalleGratis() {
		return beanRegistroDetalleGratis;
	}

	/**
	 * @param beanRegistroDetalleGratis
	 *            the beanRegistroDetalleGratis to set
	 */
	public void setBeanRegistroDetalleGratis(Object beanRegistroDetalleGratis) {
		this.beanRegistroDetalleGratis = beanRegistroDetalleGratis;
	}

	/**
	 * @return the beanRegistroDetalleComponente
	 */
	public List getBeanRegistroDetalleComponente() {
		return beanRegistroDetalleComponente;
	}

	/**
	 * @param beanRegistroDetalleComponente the beanRegistroDetalleComponente to set
	 */
	public void setBeanRegistroDetalleComponente(List beanRegistroDetalleComponente) {
		this.beanRegistroDetalleComponente = beanRegistroDetalleComponente;
	}

	/**
	 * @return the activarDecimalRangos
	 */
	public boolean isActivarDecimalRangos() {
		return activarDecimalRangos;
	}

	/**
	 * @param activarDecimalRangos the activarDecimalRangos to set
	 */
	public void setActivarDecimalRangos(boolean activarDecimalRangos) {
		this.activarDecimalRangos = activarDecimalRangos;
	}

	/**
	 * @return the activarModificarPrecio
	 */
	public boolean isActivarModificarPrecio() {
		return activarModificarPrecio;
	}

	/**
	 * @param activarModificarPrecio the activarModificarPrecio to set
	 */
	public void setActivarModificarPrecio(boolean activarModificarPrecio) {
		this.activarModificarPrecio = activarModificarPrecio;
	}

	/**
	 * @return the activarEjecutarCriterio
	 */
	public boolean isActivarEjecutarCriterio() {
		return activarEjecutarCriterio;
	}

	/**
	 * @param activarEjecutarCriterio the activarEjecutarCriterio to set
	 */
	public void setActivarEjecutarCriterio(boolean activarEjecutarCriterio) {
		this.activarEjecutarCriterio = activarEjecutarCriterio;
	}
	
	
	
}
