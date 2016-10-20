package biz.belcorp.ssicc.web.spusicc.inc.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.sisicc.model.BaseOID;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoAmbitoGeografico;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoArticuloLote;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoArticuloLoteDescuento;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoBonificacionPeriodo;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoClasificacionParticipante;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoDespachoPremios;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoEstatusVenta;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoLotePremioArticulo;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoMontoVentas;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoNivelPremiacion;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoObtencionPuntos;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoParametrosConsultoras;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoParametrosGenerales;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoParametrosPremiacion;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoPeriodoDespacho;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoPremioArticulo;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoProductos;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoProductosBonificados;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoProductosExcluidos;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoProductosExigidos;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoProductosValidos;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoPuntajeExigido;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoRecomendadaPeriodo;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoRequisitoPremiacion;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoVersion;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteINCForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCConfiguracionConcursoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECProductosFFNNEEService;
import biz.belcorp.ssicc.service.util.ConvertUtil;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.scsicc.action.ReporteINCConfiguracionConcursoAction;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaProductoSearchAction;
import biz.belcorp.ssicc.web.spusicc.inc.form.MantenimientoINCBonificacionPeriodoForm;
import biz.belcorp.ssicc.web.spusicc.inc.form.MantenimientoINCClasificacionParticipanteForm;
import biz.belcorp.ssicc.web.spusicc.inc.form.MantenimientoINCConfiguracionConcursoForm;
import biz.belcorp.ssicc.web.spusicc.inc.form.MantenimientoINCConfiguracionConcursoReplicarForm;
import biz.belcorp.ssicc.web.spusicc.inc.form.MantenimientoINCConfiguracionConcursoSearchForm;
import biz.belcorp.ssicc.web.spusicc.inc.form.MantenimientoINCDefinirPremioDescuentoForm;
import biz.belcorp.ssicc.web.spusicc.inc.form.MantenimientoINCDefinirPremioForm;
import biz.belcorp.ssicc.web.spusicc.inc.form.MantenimientoINCEstatusVentaForm;
import biz.belcorp.ssicc.web.spusicc.inc.form.MantenimientoINCPeriodoDespachoForm;
import biz.belcorp.ssicc.web.spusicc.inc.form.MantenimientoINCPuntajeExigidoForm;
import biz.belcorp.ssicc.web.spusicc.inc.form.MantenimientoINCRecomendadaPeriodoForm;
import biz.belcorp.ssicc.web.spusicc.inc.form.MantenimientoINCUnidadAdministrativaForm;

@SessionScoped
@ManagedBean
public class MantenimientoINCConfiguracionConcursoSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7919345902588106647L;
	private static String INC_PLANTILLA_CONSTANCIA_RECOMENDACION = "9036";
	public static Long INC_TIPO_PROGRAMA_GENERAR_RECOMENDADAS = new Long(2000);
	private MantenimientoINCConfiguracionConcursoService service;
	private Usuario usuario;
	private String codigoPais;
	private String indRedefinirNivelPremiacion;
	private String accionDinamico;
	private String mensajeGuardar;
	private String tabIndex = "0";
	private List incClasificacionConcursoList;
	private List incEstadoList;
	private List incVigenciaList;
	private List incConcursoList;
	private List incReemplazosPendientesList;
	private List incBaseCalculoList;
	private List siccSubGerenciaList;
	private List incTipoProgramaList;
	private List incDirigidosList;
	private List incTipoVentaList;
	private List incTipoExigenciaList;
	private List incConcursoRecomendadasList;
	private List incEstatusClienteList;
	private List incClasificacionesParticipantesList;
	private List incTiposPremiacionList;
	private List incTiposEleccionList;
	private List incCentrosServicioList;
	private List incIndicadorTipoRecomendacionList;
	private List incGenericoList;
	private List incSuperGenericoList;
	private List incProductosValidosList;
	private List incProductosBonificadosList;
	private List incProductosExcluidosList;
	private List incProductosExigidosList;
	private List incBloqueProductoList;
	private List incTipoProductoList;
	private List incTipoOfertaList;
	private List incTipoAgrupacionList;
	private List incUnidadNegocioList;
	private List incNegocioList;
	private List incCicloVidaList;
	private List incMarcaProductoList;
	private List incAmbitoList;
	private List incConcursoEstatusList;
	private List incConcursoClasificacionesList;
	private List incArticulosLoteList;
	private List incNivelesPremiacionList;
	private List incRecomendacionPeriodoList;
	private List incBonificacionPeriodoList;
	private List incPeriodoDespachoList;
	private List incPuntajeExigidoList;
	private List incPuntajeExigidoListTempo;
	private List incTipoLoteList;
	private MantenimientoINCBonificacionPeriodoForm formBonificacion;
	private MantenimientoINCRecomendadaPeriodoForm formRecomendada;
	private MantenimientoINCEstatusVentaForm formEstatus;
	private MantenimientoINCUnidadAdministrativaForm formUnidad;
	private MantenimientoINCClasificacionParticipanteForm formClasificacion;
	private MantenimientoINCPeriodoDespachoForm formPeriodoDespacho;
	private MantenimientoINCPuntajeExigidoForm formPuntajeExigido;
	private MantenimientoINCDefinirPremioForm formDefinirPremio;
	private ConcursoParametrosGenerales incDtoConcurso;
	private LabelValue[] incConcursoProgramaPuntosTipoDespachoList = {};
	private LabelValue[] siccZonaList = {};
	private LabelValue[] siccRegionList = {};
	private boolean codigoPeriodoDisabled;
	private boolean puntosUnidadDisabled;
	private boolean unidadesExigidasDisabled;
	private boolean montoExigidoDisabled;
	private boolean puntosExigidosDisabled;
	private boolean factorMultiplicadorDisabled;
	private boolean indicadorMultiMarcaBoolean;
	private boolean faltantesNoAnunciadosBoolean;
	private boolean indicadorPuntajeAcumulativoBoolean;
	private boolean indicadorDevolucionesBoolean;
	private boolean indicadorAnulacionesBoolean;
	private boolean indicadorActividadBoolean;
	private boolean indicadorConstanciaBoolean;
	private boolean indicadorObtencionPuntosBoolean;
	private boolean indicadorComunicacionBoolean;
	private boolean indicadorCPPBoolean;
	private boolean indicadorExigePasarPedidoCampanyaAnteriorCPPBoolean;
	private boolean indicadorPriorizaWebBoolean;
	private boolean indicadorPedidoEnPeriodoBoolean;
	private boolean premiosAcumulativosNivelesBoolean;
	private boolean accesoNivelSuperiorBoolean;
	private boolean indicadorNivelesRotativosBoolean;
	private boolean indicadorRangoPedidosBoolean;
	private boolean indicadorPorPedidoBoolean;
	private boolean indicadorPremioCampEfectBoolean;
	private boolean generarPuntajeARecomendadasBoolean;
	private boolean habilitarMontoVenta;
	private boolean indicadorRangoPedidosDisabled;
	private boolean indicadorPorPedidoDisabled;
	private boolean numeroMinimoPedidosRecomendadaDisabled;
	private boolean indicadorPremioCampEfectDisabled;
	private boolean generarPuntajeARecomendadasDisabled;
	private boolean multiMarcaDisabled;
	private boolean oidBaseCalculoDisabled;
	private boolean codigoMensajePuntosDisabled;
	private boolean puntosAbonarRecomendacionEfectivaCPPDisabled;
	private boolean campanyasSinPedidoParaCancelacionPuntosCPPDisabled;
	private boolean codigoCPPDisabled;
	private boolean cuotaIngresoDisabled;
	private boolean montoMinimoPedidoPremiacionDisabled;
	private boolean hastaNivelDisabled;
	private boolean numeroRotacionesDisabled;
	private DataTableModel dataTableModelPremiacion;
	private Object beanRegistroPremiacion;
	private boolean cantidadInicialPuntosDisabled;
	private boolean cantidadFinalPuntosDisabled;
	private boolean puntosProductosExigidosDisabled;
	private boolean cantidadFijaPuntosDisabled;
	private boolean plazoEntregaDisabled;
	private boolean consultar;
	private boolean oidTipoAgrupacionRendered;
	private boolean oidUnidadNegocioRendered;
	private boolean oidNegocioRendered;
	private boolean oidTipoOfertaRendered;
	private boolean codigoSAPRendered;
	private boolean oidMarcaProductoRendered;
	private boolean codigoPeriodoCUVRendered;
	private boolean CUVRendered;
	private boolean codigoPeriodoRendered;
	private boolean indicadorCopiarProductoBoolean;
	private boolean indicadorCopiarParticipantesBoolean;
	private boolean excluirClasificacion;
	private boolean indicadorCentroServicioBoolean;
	private boolean indicadorPremiosWebBoolean;
	private Object[] beanRegistroBonificacion;
	private Object[] beanRegistroRecomendada;
	private Object[] beanRegistroEstatus;
	private Object[] beanRegistroUnidad;
	private Object[] beanRegistroClasificacion;
	private Object[] beanRegistroProductosValidos;
	private Object[] beanRegistroProductosBonificacion;
	private Object[] beanRegistroProductosExcluidos;
	private Object[] beanRegistroProductosExigidos;
	private Object[] beanRegistroPeriodoDespacho;
	private Object[] beanRegistroPuntajeExigido;
	private Object beanRegistroDefinirPremio;
	private DataTableModel dataTableBonificacion;
	private DataTableModel dataTableRecomendada;
	private DataTableModel dataTableEstatus;
	private DataTableModel dataTableUnidad;
	private DataTableModel dataTableClasificacion;
	private DataTableModel dataTableProductosValidos;
	private DataTableModel dataTableProductosBonificacion;
	private DataTableModel dataTableProductosExcluidos;
	private DataTableModel dataTableProductosExigidos;
	private DataTableModel dataTablePeriodoDespacho;
	private DataTableModel dataTablePuntajeExigido;
	private DataTableModel dataTableDefinirPremio;

	@ManagedProperty(value = "#{mantenimientoINCReemplazosSearchAction}")
	private MantenimientoINCReemplazosSearchAction mantenerReemplazosAction;

	@ManagedProperty(value = "#{reporteINCConfiguracionConcursoAction}")
	private ReporteINCConfiguracionConcursoAction reporteConcurso;

	private static final String POPUP_SACPRODUCTO = "SACPRODUCTO";
	private static final String POPUP_SACPRODUCTOPREMIO = "SACPRODUCTOPREMIO";
	@ManagedProperty(value = "#{busquedaProductoSearchAction}")
	private BusquedaProductoSearchAction busquedaProductoSearchAction;
	private boolean mostrarPopupProducto;

	// campos utilizados para Definir Premio Descuento
	private MantenimientoINCDefinirPremioDescuentoForm definirPremioDescuentoForm;
	private List listaPremioDescuento;
	private DataTableModel premioDescuentoDataModel;
	private ConcursoArticuloLoteDescuento beanRegistroDefinirPremioDescuento;
	private Boolean mostrarBotones; // Se utiliza tambien en definir Premio
	private ConcursoArticuloLoteDescuento idPremioDescuento;

	// definir Premio
	private ConcursoArticuloLote idPremio;
	String oidTipoAgrupacionBloqueRendered;
	private Boolean indicadorGrabarParametrosGenerales;
	private String descripcionConcurso;
	private Boolean deshabilitarNumeroMinimoPedidos;

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setInvocarPopup(java.lang.String)
	 */
	@Override
	protected void setInvocarPopup(String accion) {

		accionDinamico = accion;
		this.mostrarProcesoBatch = false;
		this.mostrarPopupProducto = true;

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
		MantenimientoINCConfiguracionConcursoForm form = (MantenimientoINCConfiguracionConcursoForm) this.formMantenimiento;

		if (accionDinamico.equals(POPUP_SACPRODUCTO)) {
			this.mostrarPopupProducto = false;

			this.busquedaProductoSearchAction.verificarRegistro(event);
			if (this.busquedaProductoSearchAction.isSeleccionoRegistro()) {

				Map prodMap = (Map) this.busquedaProductoSearchAction
						.getBeanRegistroSeleccionado();

				form.setCodigoSAP(MapUtils.getString(prodMap, "codigoSap"));
				this.busquedaProductoSearchAction
						.setBeanRegistroSeleccionado(null);
			}
			this.formMantenimiento = form;
		} else if (accionDinamico.equals(POPUP_SACPRODUCTOPREMIO)) {
			MantenimientoINCDefinirPremioForm f = (MantenimientoINCDefinirPremioForm) this.formDefinirPremio;
			this.mostrarPopupProducto = false;

			this.busquedaProductoSearchAction.verificarRegistro(event);
			if (this.busquedaProductoSearchAction.isSeleccionoRegistro()) {

				Map prodMap = (Map) this.busquedaProductoSearchAction
						.getBeanRegistroSeleccionado();

				f.setCodigoSAP(MapUtils.getString(prodMap, "codigoSap"));
				f.setDescripcionSAP(MapUtils.getString(prodMap,
						"descripcionCorta"));
				this.busquedaProductoSearchAction
						.setBeanRegistroSeleccionado(null);
			}
			this.formDefinirPremio = f;
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
		this.mostrarProcesoBatch = true;
		this.mostrarPopupProducto = false;
		this.busquedaProductoSearchAction.setBeanRegistroSeleccionado(null);
	}

	@Override
	protected String getSalirForward() {
		return "mantenimientoINCConfiguracionConcursoList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoINCConfiguracionConcursoForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoINCConfiguracionConcursoSearchForm form = new MantenimientoINCConfiguracionConcursoSearchForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		MantenimientoINCConfiguracionConcursoSearchForm f = (MantenimientoINCConfiguracionConcursoSearchForm) this.formBusqueda;
		// f.setCodigoPais(codigoPais);
		Map criteria = BeanUtils.describe(f);
		criteria.put("nombreConcurso", "%" + f.getNombreConcurso() + "%");
		List list = service.getConcursosByCriteria(criteria);
		this.incConcursoList = list;
		return list;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public String setValidarConfirmar(String accion) {
		// ############### VALIDAR CAMPOS REQUERIDOS #######################
		if (datatableBusqueda == null)
			return this.getResourceMessage("errors.sin.registros");
		if (beanRegistroSeleccionado == null)
			return this.getResourceMessage("errors.select.item");
		if (accion.equals("ANULAR_CERRAR")) {
		}
		if (accion.equals("CONSULTAR_FALTANTES")) {

		}
		if (accion.equals("REPLICA")) {
			MantenimientoINCConfiguracionConcursoReplicarForm f = (MantenimientoINCConfiguracionConcursoReplicarForm) this.formMantenimiento;
			if (StringUtils.isBlank(f.getCodigoPeriodoInicio()))
				return "Campaña de Inicio es un campo requerido";
			else if (StringUtils.isBlank(f.getNombreConcurso()))
				return "Nombre Concurso es un campo requerido";
		}

		return null;
	}

	public void anularCerrar(ActionEvent event) {
		Map data = (Map) this.beanRegistroSeleccionado;

		if (data != null) {

			String oidConcurso = data.get("oidConcurso").toString();
			boolean validacionEliminar = service
					.validarEliminacionConcurso(oidConcurso);

			if (validacionEliminar) {
				data.put("codigoUsuario", usuario.getLogin());
				data.put("oidVigencia", "6"); // cerrado

				service.deleteConfiguracionConcurso(data);
				this.addInfo(
						"",
						this.getResourceMessage("mantenimientoINCConfiguracionConcursoSearchForm.cerrar"));

			} else {
				data.put("codigoUsuario", usuario.getLogin());
				data.put("oidVigencia", "4"); // anulado

				service.deleteConfiguracionConcurso(data);
				this.addInfo(
						"",
						this.getResourceMessage("mantenimientoINCConfiguracionConcursoSearchForm.anular"));

			}
			this.listaBusqueda = null;
			this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
		}
	}

	public void consultarFaltantesPendientes(ActionEvent event) {
		try {

			MantenimientoINCConfiguracionConcursoSearchForm f = (MantenimientoINCConfiguracionConcursoSearchForm) this.formBusqueda;
			if (datatableBusqueda == null) {
				this.getRequestContext().execute(
						"PF('dialogSinRegistros_alertDialog').show()");
			} else {
				if (this.beanRegistroSeleccionado == null) {
					this.getRequestContext().execute(
							"PF('dialogSinItem_alertDialog').show()");
				} else {
					Map data = (Map) this.beanRegistroSeleccionado;

					if (data != null) {
						//this.mostrarBotonSalir = false;
						this.mostrarBotonSave = false;
						String oidConcurso = data.get("oidConcurso").toString();
						Map criteria = BeanUtils.describe(f);
						criteria.put("oidConcurso", oidConcurso);

						f.setConcurso(data.get("numeroConcurso") + " "
								+ data.get("nombreConcurso"));

						List list = service
								.getFaltantesPendientesConcurso(criteria);
						this.incReemplazosPendientesList = list;
						this.redireccionarPagina("mantenimientoINCFaltantesPendientes");
					}
				}
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	public void replicarConcurso(ActionEvent event) throws Exception {
		MantenimientoINCConfiguracionConcursoReplicarForm f = new MantenimientoINCConfiguracionConcursoReplicarForm();
		Map paramsConcurso = (Map) this.beanRegistroSeleccionado;
		if (datatableBusqueda == null)
			this.getRequestContext().execute(
					"PF('dialogSinRegistros_alertDialog').show()");
		else if (beanRegistroSeleccionado == null)
			this.getRequestContext().execute(
					"PF('dialogSinItem_alertDialog').show()");
		else {

			f.setOidConcursoOrigen(MapUtils.getString(paramsConcurso,
					"oidConcurso"));
			f.setCodigoConcursoOrigen(MapUtils.getString(paramsConcurso,
					"numeroConcurso"));
			f.setNombreConcursoOrigen(MapUtils.getString(paramsConcurso,
					"nombreConcurso"));
			f.setNombreConcurso("");
			f.setIndicadorCopiarParticipantes(Constants.NUMERO_UNO);
			f.setIndicadorCopiarProducto(Constants.NUMERO_UNO);

			// cargar el periodo de proceso
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																	// Campanha
																	// Activa
			criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																		// Campanha
																		// activa
																		// q se
																		// procesa
																		// actualmente

			MantenimientoOCRPedidoControlFacturacionService serviceAux = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");

			PedidoControlFacturacion controlFacturacion = serviceAux
					.getControlFacturacionById(criteria);
			f.setCodigoPeriodoProceso(controlFacturacion.getCodigoPeriodo());
			f.setCodigoPeriodoInicio(ajaxService.getPeriodoNSiguiente(
					codigoPais, controlFacturacion.getCodigoPeriodo(),
					Constants.NUMERO_UNO));

			this.formMantenimiento = f;
			indicadorCopiarProductoBoolean = true;
			indicadorCopiarParticipantesBoolean = true;
			mostrarBotonSave = false;
			//this.mostrarBotonSalir = true;
			this.redireccionarPagina("mantenimientoINCConfiguracionConcursoReplicarForm");
		}
	}

	public void savereplicar(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'savereplicar' method");
		}

		try {

			MantenimientoINCConfiguracionConcursoReplicarForm f = (MantenimientoINCConfiguracionConcursoReplicarForm) this.formMantenimiento;
			MantenimientoINCConfiguracionConcursoService service = (MantenimientoINCConfiguracionConcursoService) getBean("spusicc.mantenimientoINCConfiguracionConcursoService");

			Map params = new HashMap();
			params.put("codigoPais", codigoPais);
			params.put("oidConcursoOrigen", f.getOidConcursoOrigen());
			params.put("nombreConcurso", f.getNombreConcurso());
			params.put("codigoPeriodoInicio", f.getCodigoPeriodoInicio());
			if (indicadorCopiarProductoBoolean)
				f.setIndicadorCopiarProducto("1");
			else
				f.setIndicadorCopiarProducto("0");

			if (indicadorCopiarParticipantesBoolean)
				f.setIndicadorCopiarParticipantes("1");
			else
				f.setIndicadorCopiarParticipantes("0");

			params.put("indicadorCopiarProducto",
					f.getIndicadorCopiarProducto());
			params.put("indicadorCopiarParticipantes",
					f.getIndicadorCopiarParticipantes());
			params.put("codigoUsuario", usuario.getLogin());

			service.executeReplicarConfiguracionConcurso(params);

			String codigoConcurso = (String) params.get("numeroConcurso");

			addInfo("",
					"Se realizo la copia del Concurso N° satisfactoriamente"
							+ codigoConcurso);
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		}
	}

	public void mantenerReemplazo(ActionEvent event) throws Exception {
		Map data = (Map) this.beanRegistroSeleccionado;
		if (datatableBusqueda == null)
			this.getRequestContext().execute(
					"PF('dialogSinRegistros_alertDialog').show()");
		else if (beanRegistroSeleccionado == null)
			this.getRequestContext().execute(
					"PF('dialogSinItem_alertDialog').show()");
		else {
			mantenerReemplazosAction.setData(data);
			mantenerReemplazosAction.setViewAtributes();
		}
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		boolean bGrabar = true;
		MantenimientoINCConfiguracionConcursoForm f = (MantenimientoINCConfiguracionConcursoForm) this.formMantenimiento;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		
		//EN CASO DE CAMPAÑA INICIO= CAMPAÑA FIN, SE DESACTIVA LOS CAMPOS DE LA SECCION: EVALUAR POR
		if(StringUtils.isNotEmpty(f.getCodigoPeriodoInicio()) && f.getCodigoPeriodoInicio().equals(f.getCodigoPeriodoFin())) {
			this.indicadorActividadBoolean = false;
			this.indicadorConstanciaBoolean = false;
			f.setNumeroPeriodosSinPedido("");
		}
		
		setearCheckBoxString(f);
		validaciones();
		ConcursoParametrosGenerales concurso = obtenerDTOConcurso(f);
		concurso.setCodigoPais(pais.getCodigo());
		concurso.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		concurso.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		concurso.setCodigoAcceso(Constants.CODIGO_ACCESO_DEFAULT);

		// Validamos el codigo de Mensaje de Puntos
		if (StringUtils.isNotEmpty(f.getCodigoMensajePuntos())) {
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			String descripcionMensaje = ajaxService.getDescripcionMensaje(
					codigoPais, f.getCodigoMensajePuntos());

			if (StringUtils.isEmpty(descripcionMensaje)) {
				tabIndex = "3";
				bGrabar = false;
				throw new Exception(
						this.getResourceMessage("mantenimientoINCConfiguracionConcursoForm.msg.mensajeNoExiste"));

			} else {
				f.setDescripcionMensajePuntos(descripcionMensaje);
			}
		}

		if (!this.validarMultiMarca(concurso)) {
			addError(
					"Error",
					this.getResourceMessage("mantenimientoINCConfiguracionConcursoForm.validacionMultiMarca"));
			bGrabar = false;
		}
		
		//VALIDAMOS QUE LOS NUMEROS DE PEDIDO RECOMENDADA y RECOMENDANTE >= 0
		if(f.getOidBaseCalculo().equals(Constants.INC_BASE_CALCULO_RECOMENDADAS.toString())) {
			if(StringUtils.isEmpty(f.getNumeroMinimoPedidos()) || StringUtils.isEmpty(f.getNumeroMinimoPedidosRecomendada())) {
				addError("Error", this.getResourceMessage("mantenimientoINCConfiguracionConcursoForm.validacionNumeroPedidosRecomendacion"));
				return false;
			}
		}
		
		String validarPremiacion = this.validarPremiacion(concurso, f);
		if (StringUtils.isNotBlank(validarPremiacion)) {
			throw new Exception(this.getResourceMessage(validarPremiacion));
		}

		// Validamos que el concurso multipunto exista
		if (StringUtils.isNotBlank(f.getCodigoCPP())) {
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			LabelValue programa = ajaxService.getConcursoProgramaPuntos(
					codigoPais, f.getCodigoCPP());

			if (programa == null) {
				addError(
						"Error",
						this.getResourceMessage("mantenimientoINCConfiguracionConcursoForm.msg.concursoNoExiste"));
				bGrabar = false;
			} else {
				if (StringUtils.equals(programa.getValue(),
						Constants.NUMERO_CERO)) {
					addError(
							"Error",
							this.getResourceMessage("mantenimientoINCConfiguracionConcursoForm.msg.noEsProgramaPuntos"));
					bGrabar = false;
				}
			}
		}
		//
		//
		// PER-SiCC-2015-0202 - ini
		// validamos cada uno de los periodos en caso no tengan oid se devuelve
		// un mensaje de error

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		Map criteria = new HashMap();
		criteria.put("codigoPeriodo", f.getCodigoPeriodoInicio());
		String oidPeriodo = null;

		try {
			oidPeriodo = reporteService.getOidString(
					"getOidPeriodoByCodigoPeriodo", criteria);
		} catch (Exception e) {
			addError(
					"Error",
					this.getResourceMessage("mantenimientoINCConfiguracionConcursoForm.msg.validaOidPeriodoInicio"));
			return false;
		}

		criteria.put("codigoPeriodo", f.getCodigoPeriodoFin());

		try {
			oidPeriodo = reporteService.getOidString(
					"getOidPeriodoByCodigoPeriodo", criteria);
		} catch (Exception e) {
			addError(
					"Error",
					this.getResourceMessage("mantenimientoINCConfiguracionConcursoForm.msg.validaOidPeriodoFin"));
			return false;
		}

		if (f.getCodigoPeriodoDespachoInicio() != null
				&& StringUtils.isNotBlank(f.getCodigoPeriodoDespachoInicio())) {
			criteria.put("codigoPeriodo", f.getCodigoPeriodoDespachoInicio());

			try {
				oidPeriodo = reporteService.getOidString(
						"getOidPeriodoByCodigoPeriodo", criteria);
			} catch (Exception e) {
				addError(
						"Error",
						this.getResourceMessage("mantenimientoINCConfiguracionConcursoForm.msg.validaOidPeriodoInicioPremiacion"));
				return false;
			}
		}

		if (f.getCodigoPeriodoDespacho() != null
				&& StringUtils.isNotBlank(f.getCodigoPeriodoDespacho())) {
			criteria.put("codigoPeriodo", f.getCodigoPeriodoDespacho());
			try {
				oidPeriodo = reporteService.getOidString(
						"getOidPeriodoByCodigoPeriodo", criteria);
			} catch (Exception e) {
				addError(
						"Error",
						this.getResourceMessage("mantenimientoINCConfiguracionConcursoForm.msg.validaOidPeriodoFinPremiacion"));
				return false;
			}
		}
		// PER-SiCC-2015-0202 - fin

		try {
			// VERIFICAMOS SI SE VA A REGISTRAR EL CONCURSO DE RECOMENDACION
			// (PARA LA RECOMENDADA)
			boolean registrarConcursoRecomendacion = false;
			if (concurso.getConcursoParametrosConsultoras()
					.getOidConcursoPuntajeRecomendada() == null
					&& concurso.getConcursoParametrosConsultoras()
							.getConcursoRecomendada() != null) {
				registrarConcursoRecomendacion = true;
			}

			if (f.isNewRecord()) {
				this.invocarFindLuegoGrabar = false;
				
				service.insertConfiguracionConcurso(concurso, usuario);// inserta

				// enviamos el mensaje de satisfaccion
				if (registrarConcursoRecomendacion) {
					String numeroConcursoReco = concurso
							.getConcursoParametrosConsultoras()
							.getConcursoRecomendada().getNumeroConcurso();
					this.mensajeGuardar = "El Concurso ha sido creado satisfactoriamente, favor de revisar el concurso "
							+ numeroConcursoReco + " de la recomendada";
				} else
					this.mensajeGuardar = this
							.getResourceMessage("mantenimientoINCConfiguracionConcursoForm.insert");
			} else {
				this.invocarFindLuegoGrabar = true;
				
				service.updateConfiguracionConcurso(concurso, usuario);// update

				// enviamos el mensaje de satisfaccion
				if (registrarConcursoRecomendacion) {
					String numeroConcursoReco = concurso
							.getConcursoParametrosConsultoras()
							.getConcursoRecomendada().getNumeroConcurso();
					this.mensajeGuardar = "El Concurso ha sido actualizado satisfactoriamente, favor de revisar el concurso "
							+ numeroConcursoReco + " de la recomendada";
				} else
					this.mensajeGuardar = this
							.getResourceMessage("mantenimientoINCConfiguracionConcursoForm.update");

			}

		} catch (Exception e) {
			log.debug("error " + e.getMessage());
			addError("", obtieneMensajeErrorException(e));
			bGrabar = false;
		}

		return bGrabar;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction
	 * #devuelveMensajeAlertaDefaultAction()
	 */
	public String devuelveMensajeAlertaDefaultAction() throws Exception {
		return mensajeGuardar;
	}

	public void validaciones() throws Exception {
		MantenimientoINCConfiguracionConcursoForm f = (MantenimientoINCConfiguracionConcursoForm) this.formMantenimiento;
		if (f.getIndRestriccionMantenimiento().equals("0")) {
			if (this.accion.equals(this.ACCION_NUEVO)) {
				int codperini = Integer.parseInt(f.getCodigoPeriodoInicio());
				int codperpro = Integer.parseInt(f.getCodigoPeriodoProceso());
				if (codperini < codperpro) {
					// Campaña de inicio debe ser mayor o igual a campaña actual
					throw new Exception(
							this.getResourceMessage("mantenimientoINCConfiguracionConcursoForm.msg.validarPeriodoInicio3"));
				}
			}

			// if(indicadorGrabarSoloPremiacion.value=="false") {
			// if((editableCampannaInicio.value=="true") &&
			// (codigoPeriodoInicio.value < codigoPeriodoProceso.value)) {
			// //Campaña de inicio del concurso es menor a la campaña de proceso
			// alert("<fmt:message key='mantenimientoINCConfiguracionConcursoForm.msg.validarPeriodoInicio'/>");
			// codigoPeriodoInicio.focus();
			// return false;
			// }
			// }

		} else {
			if (f.getIndRestriccionMantenimiento().equals("1")) {
				if (this.accion.equals(this.ACCION_NUEVO)) {
					int codperini = Integer
							.parseInt(f.getCodigoPeriodoInicio());
					int codperpro = Integer.parseInt(f
							.getCodigoPeriodoProceso());
					if (codperini <= codperpro) {
						// Campaña de inicio debe ser mayor a campaña actual
						throw new Exception(
								this.getResourceMessage("mantenimientoINCConfiguracionConcursoForm.msg.validarPeriodoInicio2"));
					}
				}
			}
			if (f.getIndRestriccionMantenimiento().equals("2")) {
				if (this.accion.equals(this.ACCION_NUEVO)) {
					int codperini = Integer
							.parseInt(f.getCodigoPeriodoInicio());
					int codperpro = Integer.parseInt(f
							.getCodigoPeriodoProceso());
					if (codperini < codperpro) {
						// Campaña de inicio debe ser mayor o igual a campaña
						// actual
						throw new Exception(
								this.getResourceMessage("mantenimientoINCConfiguracionConcursoForm.msg.validarPeriodoInicio3"));
					}
				}
			}
		}
	}

	/**
	 * Metodo que realiza la Validacion de MultiMarca
	 * 
	 * @param session
	 * @param concursoParametrosGenerales
	 * @return
	 */
	protected boolean validarMultiMarca(
			ConcursoParametrosGenerales concursoParametrosGenerales) {
		boolean validacionOK = true;

		/* Validando MultiMarca */
		List listConcursoProductosExigidos = this.incProductosExigidosList;
		Integer valorMultiMarca = concursoParametrosGenerales
				.getIndicadorMultiMarca();
		Long puntosAbonar = concursoParametrosGenerales.getPuntosAbonar();
		if (valorMultiMarca != null && valorMultiMarca > 0) {
			if (puntosAbonar != null && puntosAbonar > 0) {
				int size = listConcursoProductosExigidos.size();
				if (listConcursoProductosExigidos == null)
					return false;
				if (size <= 0)
					return false;
				List listaMarca = new ArrayList();
				for (int i = 0; i < size; i++) {
					ConcursoProductosExigidos concursoProductosExigidos = (ConcursoProductosExigidos) listConcursoProductosExigidos
							.get(i);
					Long oidMarca = concursoProductosExigidos
							.getOidMarcaProducto();
					if (oidMarca != null && oidMarca.longValue() != 0) {
						listaMarca.add(oidMarca);
					}
				}

				ConvertUtil.removeDuplicates(listaMarca);
				if (listaMarca == null)
					return false;
				if (listaMarca.size() < 2)
					return false;

			}
		}
		return validacionOK;
	}

	/**
	 * @param session
	 * @param concursoParametrosGenerales
	 * @return
	 */
	public String validarPremiacion(
			ConcursoParametrosGenerales concursoParametrosGenerales,
			MantenimientoINCConfiguracionConcursoForm f) {
		String validar = new String();
		ConcursoParametrosPremiacion concursoParametrosPremiacion = concursoParametrosGenerales
				.getConcursoParametrosPremiacion();

		/* Si Base Calculo es Recomendadas */
		if (concursoParametrosGenerales.getOidBaseCalculo().intValue() == 4) {

			if (concursoParametrosGenerales.getConcursoRequisitoPremiacion()
					.getMontoMinimoConcurso().intValue() != 0) {
				return "mantenimientoINCConfiguracionConcursoForm.validarMontoMinimoConcurso";
			}

			Integer numerosPedidos = concursoParametrosGenerales
					.getConcursoRequisitoPremiacion().getNumeroPedidos();
			if (numerosPedidos == null)
				concursoParametrosGenerales.getConcursoRequisitoPremiacion()
						.setNumeroPedidos(new Integer(0));
			if (concursoParametrosGenerales.getConcursoRequisitoPremiacion()
					.getNumeroPedidos().intValue() != 0) {
				return "mantenimientoINCConfiguracionConcursoForm.validarNumeroPedidos";
			}
			Integer cuotaIngreso = concursoParametrosGenerales
					.getConcursoRequisitoPremiacion().getCuotaIngreso();
			if (cuotaIngreso == null)
				concursoParametrosGenerales.getConcursoRequisitoPremiacion()
						.setCuotaIngreso(new Integer(0));
			if (concursoParametrosGenerales.getConcursoRequisitoPremiacion()
					.getCuotaIngreso().intValue() != 0) {
				return "mantenimientoINCConfiguracionConcursoForm.validarCuotaIngreso";
			}
		}
		if (concursoParametrosGenerales.getConcursoRequisitoPremiacion()
				.getIndicadorPedidoEnPeriodo().intValue() == 1) {
			BigDecimal montoMinimoPedido = concursoParametrosGenerales
					.getConcursoRequisitoPremiacion().getMontoMinimoPedido();
			if (montoMinimoPedido.longValue() < 1)
				return "mantenimientoINCConfiguracionConcursoForm.validarMontoMinimoPedido";
		}
		Integer numeroNiveles = concursoParametrosPremiacion.getNumeroNiveles();
		if (numeroNiveles == null || numeroNiveles.intValue() <= 0) {
			return "mantenimientoINCConfiguracionConcursoForm.validarNumeroNiveles";
		}
		String codigoPeriodoDespachoInicio = f.getCodigoPeriodoDespachoInicio();
		String codigoPeriodoDespachoFinal = f.getCodigoPeriodoDespacho();
		if (StringUtils.isBlank(codigoPeriodoDespachoInicio)
				&& StringUtils.isNotBlank(codigoPeriodoDespachoFinal)) {
			return "mantenimientoINCConfiguracionConcursoForm.validarCodigoPeriodoDespachoExistente";
		}
		if (StringUtils.isBlank(codigoPeriodoDespachoFinal)
				&& StringUtils.isNotBlank(codigoPeriodoDespachoInicio)) {
			return "mantenimientoINCConfiguracionConcursoForm.validarCodigoPeriodoDespachoExistente";
		}
		if (StringUtils.isNotBlank(codigoPeriodoDespachoFinal)
				&& StringUtils.isNotBlank(codigoPeriodoDespachoInicio)) {
			String codigoPeriodoInicio = f.getCodigoPeriodoInicio();
			String codigoPeriodoFin = f.getCodigoPeriodoFin();
			if (new Long(codigoPeriodoDespachoFinal) < new Long(
					codigoPeriodoDespachoInicio)) {
				return "mantenimientoINCConfiguracionConcursoForm.validarCodigoPeriodoDespachoFinal";
			}
			if (new Long(codigoPeriodoDespachoInicio) < new Long(
					codigoPeriodoInicio)) {
				return "mantenimientoINCConfiguracionConcursoForm.validarCodigoPeriodoDespachoInicial";
			}
			if (new Long(codigoPeriodoDespachoFinal) < new Long(
					codigoPeriodoFin)) {
				return "mantenimientoINCConfiguracionConcursoForm.validarCodigoPeriodoDespachoRango";
			}
		}
		if (concursoParametrosPremiacion.getHastaNivel() != null) {
			if (concursoParametrosPremiacion.getHastaNivel() > concursoParametrosPremiacion
					.getNumeroNiveles()) {
				return "mantenimientoINCConfiguracionConcursoForm.validarHastaNivel";
			}
		}
		if (concursoParametrosPremiacion.getIndicadorNivelesRotativos()
				.intValue() == 1) {
			if (concursoParametrosPremiacion.getNumeroRotaciones().intValue() < 1
					|| concursoParametrosPremiacion.getNumeroRotaciones()
							.intValue() > 99)
				return "mantenimientoINCConfiguracionConcursoForm.validarNumeroRotaciones";
		}

		/* Validamos los Niveles de Premiacion */
		Long oidTipoPremiacion = concursoParametrosPremiacion
				.getOidTipoPremiacion();
		List listConcursoNivelPremiacion = concursoParametrosPremiacion
				.getListConcursoNivelPremiacion();
		if (oidTipoPremiacion.longValue() == 1) {
			for (int i = 0; i < listConcursoNivelPremiacion.size(); i++) {
				ConcursoNivelPremiacion concursoNivelPremiacion = (ConcursoNivelPremiacion) listConcursoNivelPremiacion
						.get(i);
				if (concursoNivelPremiacion.getCantidadFijaPuntos() != null
						&& concursoNivelPremiacion.getCantidadFijaPuntos()
								.intValue() < 0) {
					tabIndex = "2";
					return "mantenimientoINCConfiguracionConcursoForm.validarCantidadFijaPuntos.oidTipoPremiacion01";
				}

			}
		}
		if (oidTipoPremiacion.longValue() == 2) {
			ConcursoNivelPremiacion concursoNivelPremiacionAnterior = null;
			for (int i = 0; i < listConcursoNivelPremiacion.size(); i++) {
				ConcursoNivelPremiacion concursoNivelPremiacion = (ConcursoNivelPremiacion) listConcursoNivelPremiacion
						.get(i);

				if (concursoNivelPremiacion.getCantidadInicialPuntos() == null
						|| concursoNivelPremiacion.getCantidadInicialPuntos()
								.intValue() < 0) {
					tabIndex = "2";
					return "mantenimientoINCConfiguracionConcursoForm.validarCantidadInicialPuntos.oidTipoPremiacion02";
				}
				if (concursoNivelPremiacion.getCantidadFinalPuntos() == null
						|| concursoNivelPremiacion.getCantidadFinalPuntos()
								.intValue() < 0) {
					tabIndex = "2";
					return "mantenimientoINCConfiguracionConcursoForm.validarCantidadFinalPuntos.oidTipoPremiacion02";
				}
				if (concursoNivelPremiacion.getCantidadInicialPuntos()
						.intValue() < concursoNivelPremiacion
						.getCantidadInicialPuntos().intValue()) {
					tabIndex = "2";
					return "mantenimientoINCConfiguracionConcursoForm.validarCantidadFinalPuntosMayor.oidTipoPremiacion02";
				}
				if (concursoNivelPremiacionAnterior != null) {
					int cantidadInicialRegistro = concursoNivelPremiacion
							.getCantidadInicialPuntos().intValue();
					int cantidadFinalAnterior = concursoNivelPremiacionAnterior
							.getCantidadFinalPuntos().intValue();

					if (cantidadInicialRegistro != cantidadFinalAnterior + 1) {
						tabIndex = "2";
						return "mantenimientoINCConfiguracionConcursoForm.validarCantidadInicialNivel.oidTipoPremiacion02";
					}
				}
				concursoNivelPremiacionAnterior = concursoNivelPremiacion;
			}

		}
		return validar;
	}

	/**
	 * @param session
	 * @param f
	 * @return
	 */
	private ConcursoParametrosGenerales obtenerDTOConcurso(
			MantenimientoINCConfiguracionConcursoForm f) {
		ConcursoParametrosGenerales concursoParametrosGenerales = this.incDtoConcurso;

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		if (concursoParametrosGenerales.getConcursoObtencionPuntos() == null)
			concursoParametrosGenerales
					.setConcursoObtencionPuntos(new ConcursoObtencionPuntos());

		if (concursoParametrosGenerales.getConcursoDespachoPremios() == null)
			concursoParametrosGenerales
					.setConcursoDespachoPremios(new ConcursoDespachoPremios());

		if (concursoParametrosGenerales.getConcursoParametrosConsultoras() == null)
			concursoParametrosGenerales
					.setConcursoParametrosConsultoras(new ConcursoParametrosConsultoras());

		if (concursoParametrosGenerales.getConcursoRequisitoPremiacion() == null) {
			concursoParametrosGenerales
					.setConcursoRequisitoPremiacion(new ConcursoRequisitoPremiacion());
			concursoParametrosGenerales.getConcursoRequisitoPremiacion()
					.setIndicadorPagoATiempo(new Integer(0));
		}

		if (concursoParametrosGenerales.getConcursoParametrosPremiacion() == null) {
			concursoParametrosGenerales
					.setConcursoParametrosPremiacion(new ConcursoParametrosPremiacion());
			concursoParametrosGenerales.getConcursoParametrosPremiacion()
					.setIndicadorComunicacion(new Integer(0));
		}

		ConcursoObtencionPuntos concursoObtencionPuntos = concursoParametrosGenerales
				.getConcursoObtencionPuntos();
		ConcursoDespachoPremios concursoDespachoPremios = concursoParametrosGenerales
				.getConcursoDespachoPremios();
		ConcursoParametrosConsultoras concursoParametrosConsultoras = concursoParametrosGenerales
				.getConcursoParametrosConsultoras();
		ConcursoRequisitoPremiacion concursoRequisitoPremiacion = concursoParametrosGenerales
				.getConcursoRequisitoPremiacion();
		ConcursoParametrosPremiacion concursoParametrosPremiacion = concursoParametrosGenerales
				.getConcursoParametrosPremiacion();
		ConcursoProductos concursoProductos = concursoParametrosGenerales
				.getConcursoProductos();

		// Parametros Generales
		concursoParametrosGenerales.setOidClasificacionConcurso(new Long(f
				.getOidTipoConcurso()));
		concursoParametrosGenerales.setNombreConcurso(f.getNombreConcurso());

		if (StringUtils.isNotBlank(f.getOidTipoPrograma()))
			concursoParametrosGenerales.setOidTipoPrograma(new Long(f
					.getOidTipoPrograma()));
		else
			concursoParametrosGenerales.setOidTipoPrograma(null);

		concursoParametrosGenerales.setOidBaseCalculo(new Long(f
				.getOidBaseCalculo()));
		// concursoParametrosGenerales.setIndicadorActivo(new
		// Integer(f.getIndicadorActivarConcurso()));
		concursoParametrosGenerales.setCodigoPeriodoDesde(f
				.getCodigoPeriodoInicio());
		concursoParametrosGenerales.setCodigoPeriodoHasta(f
				.getCodigoPeriodoFin());
		concursoParametrosGenerales.setIndicadorNoGeneraPuntaje(new Integer(f
				.getIndicadorNoGeneraPuntaje()));
		concursoParametrosGenerales.setIndicadorDevoluciones(new Integer(f
				.getIndicadorDevoluciones()));
		concursoParametrosGenerales.setIndicadorAnulaciones(new Integer(f
				.getIndicadorAnulaciones()));
		concursoParametrosGenerales.setFaltantesNoAnunciados(new Integer(f
				.getFaltantesNoAnunciados()));
		concursoParametrosGenerales.setObservaciones(f.getObservaciones());
		concursoParametrosGenerales.setCodigoUsuario(usuario.getLogin());

		if (!StringUtils.isEmpty(f.getOidPlantilla()))
			concursoParametrosGenerales.setOidPlantilla(new Long(f
					.getOidPlantilla()));

		if (!StringUtils.isEmpty(f.getOidTipoConcursoIVR())) {
			concursoParametrosGenerales.setOidTipoConcursoIVR(new Long(f
					.getOidTipoConcursoIVR()));
		} else
			concursoParametrosGenerales.setOidTipoConcursoIVR(null);

		concursoParametrosGenerales.setIndicadorMultiMarca(new Integer(f
				.getIndicadorMultiMarca()));
		if (!StringUtils.isEmpty(f.getPuntosAbonar()))
			concursoParametrosGenerales.setPuntosAbonar(new Long(f
					.getPuntosAbonar()));
		else
			concursoParametrosGenerales.setPuntosAbonar(null);

		// Obtencion Puntos
		concursoObtencionPuntos.setFactorConversion(new BigDecimal(f
				.getFactorConversion()));
		concursoObtencionPuntos.setNumeroPuntosAsignar(new Long(f
				.getNumeroPuntosAsignar()));
		concursoObtencionPuntos.setIndicadorPuntajeAcumulativo(new Integer(f
				.getIndicadorPuntajeAcumulativo()));
		concursoObtencionPuntos.setIndicadorActividad(new Integer(f
				.getIndicadorActividad()));
		concursoObtencionPuntos.setIndicadorConstancia(new Integer(f
				.getIndicadorConstancia()));
		concursoObtencionPuntos.setCodigoMensaje(f.getCodigoMensajePuntos());
		concursoObtencionPuntos.setIndicadorComunicacion(new Integer(f
				.getIndicadorObtencionPuntos()));

		if (!StringUtils.isEmpty(f.getNumeroPeriodosSinPedido())) {
			concursoObtencionPuntos.setNumeroPeriodosSinPedido(new Integer(f
					.getNumeroPeriodosSinPedido()));
		} else
			concursoObtencionPuntos.setNumeroPeriodosSinPedido(null);
		
		concursoObtencionPuntos.setTipoCuadre(new Integer(f.getTipoCuadre()));

		// Despacho Premios
		concursoDespachoPremios.setIndicadorComunicacionAutomatico(new Integer(
				0));
		concursoDespachoPremios.setIndicadorComunicacionManual(new Integer(0));
		concursoDespachoPremios.setCodigoMensajeAutomatico("");
		concursoDespachoPremios.setCodigoMensajeManual("");
		/*
		 * if(f.getIndicadorAutomaticoManual().equals("A")) {
		 * concursoDespachoPremios.setIndicadorComunicacionAutomatico(new
		 * Integer(1));
		 * concursoDespachoPremios.setIndicadorComunicacionManual(new
		 * Integer(0)); concursoDespachoPremios.setCodigoMensajeAutomatico(f.
		 * getCodigoMensajeDespacho());
		 * concursoDespachoPremios.setCodigoMensajeManual(""); }
		 * if(f.getIndicadorAutomaticoManual().equals("M")) {
		 * concursoDespachoPremios.setIndicadorComunicacionAutomatico(new
		 * Integer(0));
		 * concursoDespachoPremios.setIndicadorComunicacionManual(new
		 * Integer(1)); concursoDespachoPremios.setCodigoMensajeAutomatico("");
		 * concursoDespachoPremios
		 * .setCodigoMensajeManual(f.getCodigoMensajeDespacho()); }
		 */

		// Parametros de Consultoras
		if (!StringUtils.isEmpty(f.getMontoMinimoPedido()))
			concursoParametrosConsultoras.setMontoMinimoPedido(new BigDecimal(f
					.getMontoMinimoPedido()));
		else
			concursoParametrosConsultoras.setMontoMinimoPedido(null);
		if (!StringUtils.isEmpty(f.getUnidadesMinimasPedido()))
			concursoParametrosConsultoras.setUnidadesMinimasPedido(new Integer(
					f.getUnidadesMinimasPedido()));
		else
			concursoParametrosConsultoras.setUnidadesMinimasPedido(null);
		if (!StringUtils.isEmpty(f.getNumeroMinimoPedidos()))
			concursoParametrosConsultoras.setNumeroMinimoPedidos(new Integer(f
					.getNumeroMinimoPedidos()));
		else
			concursoParametrosConsultoras.setNumeroMinimoPedidos(null);

		if (!StringUtils.isEmpty(f.getOidTipoVenta()))
			concursoParametrosConsultoras.setOidTipoVenta(new Long(f
					.getOidTipoVenta()));
		else
			concursoParametrosConsultoras.setOidTipoVenta(null);

		concursoParametrosConsultoras.setOidTipoVenta(null);
		if (concursoParametrosGenerales.getOidBaseCalculo().longValue() == 1) {
			concursoParametrosConsultoras.setOidTipoVenta(new Long(3));
		}

		if(StringUtils.isBlank(f.getIndicadorReingresoPierdePuntaje()))
			f.setIndicadorReingresoPierdePuntaje("0");
		concursoParametrosConsultoras.setIndicadorReingresoPierdePuntaje(new Integer(f.getIndicadorReingresoPierdePuntaje()));

		concursoParametrosConsultoras
				.setIndicadorRecomendacionEfectiva(new Integer(0));
		if (concursoParametrosGenerales.getOidBaseCalculo().longValue() == 4) {
			concursoParametrosConsultoras
					.setIndicadorRecomendacionEfectiva(new Integer(1));
		}

		if (!StringUtils.isEmpty(f.getOidTipoExigencia()))
			concursoParametrosGenerales.setOidTipoExigencia(new Long(f
					.getOidTipoExigencia()));
		else
			concursoParametrosGenerales.setOidTipoExigencia(null);

		concursoParametrosConsultoras.setCodigoPeriodoEvaluacion(f
				.getCodigoPeriodoEvaluacion());
		if (!StringUtils.isEmpty(f.getPeriodosEvaluacion()))
			concursoParametrosConsultoras.setPeriodosEvaluacion(new Integer(f
					.getPeriodosEvaluacion()));
		else
			concursoParametrosConsultoras.setPeriodosEvaluacion(null);
		if (!StringUtils.isEmpty(f.getNumeroMinimoPedidosRecomendada()))
			concursoParametrosConsultoras
					.setNumeroMinimoPedidosRecomendada(new Integer(f
							.getNumeroMinimoPedidosRecomendada()));
		else
			concursoParametrosConsultoras
					.setNumeroMinimoPedidosRecomendada(null);

		concursoParametrosConsultoras.setIndicadorPremioCampEfect(new Integer(f
				.getIndicadorPremioCampEfect()));
		if (!StringUtils.isEmpty(f.getGenerarPuntajeARecomendadas()))
			concursoParametrosConsultoras
					.setGenerarPuntajeARecomendadas(new Integer(f
							.getGenerarPuntajeARecomendadas()));
		else
			concursoParametrosConsultoras.setGenerarPuntajeARecomendadas(null);

		/*
		 * if(!StringUtils.isEmpty(f.getTipoEvaluacion()))
		 * concursoParametrosConsultoras.setTipoEvaluacion(new
		 * Integer(f.getTipoEvaluacion())); else
		 * concursoParametrosConsultoras.setTipoEvaluacion(null);
		 */

		if (StringUtils.equals(f.getIndicadorRangoPedidos(),
				Constants.NUMERO_CERO)
				&& StringUtils.equals(f.getIndicadorPorPedido(),
						Constants.NUMERO_CERO)) {
			concursoParametrosConsultoras.setTipoEvaluacion(null);
		} else if (StringUtils.equals(f.getIndicadorRangoPedidos(),
				Constants.NUMERO_UNO)) {
			concursoParametrosConsultoras.setTipoEvaluacion(new Integer(f
					.getIndicadorRangoPedidos()));
		} else if (StringUtils.equals(f.getIndicadorPorPedido(),
				Constants.NUMERO_DOS)) {
			concursoParametrosConsultoras.setTipoEvaluacion(new Integer(f
					.getIndicadorPorPedido()));
		}

		if (!StringUtils.isEmpty(f.getOidConcursoPuntajeRecomendada()))
			concursoParametrosConsultoras
					.setOidConcursoPuntajeRecomendada(new Long(f
							.getOidConcursoPuntajeRecomendada()));
		else
			concursoParametrosConsultoras
					.setOidConcursoPuntajeRecomendada(null);

		// Monto Ventas
		if (concursoParametrosGenerales.getOidClasificacionConcurso()
				.toString().equals(f.getOidTipoConcursoRecomendacion())) {
			List listConcursoMontoVentas = concursoParametrosGenerales
					.getListConcursoMontoVentas();

			if (listConcursoMontoVentas == null)
				listConcursoMontoVentas = new ArrayList();

			if (listConcursoMontoVentas.size() > 0) {
				for (int i = 0; i < listConcursoMontoVentas.size(); i++) {
					ConcursoMontoVentas concursoMontoVentas = (ConcursoMontoVentas) listConcursoMontoVentas
							.get(i);

					if (concursoMontoVentas.getOidTipoMontoVenta().equals(
							Constants.INC_MONTOVENTA_MONTO_MINIMO)) {
						if (!StringUtils.isEmpty(f.getMontoVentaMinimo()))
							concursoMontoVentas
									.setCantidadMontoVenta(new BigDecimal(f
											.getMontoVentaMinimo()));
						else
							concursoMontoVentas.setCantidadMontoVenta(null);
					}
					if (concursoMontoVentas.getOidTipoMontoVenta().equals(
							Constants.INC_MONTOVENTA_UNIDAD_MINIMA)) {
						concursoMontoVentas
								.setCantidadMontoVenta(new BigDecimal(f
										.getMontoVentaUnidad()));
					}
					if (concursoMontoVentas.getOidTipoMontoVenta().equals(
							Constants.INC_MONTOVENTA_MONTO_TOTAL)) {
						concursoMontoVentas
								.setCantidadMontoVenta(new BigDecimal(f
										.getMontoVentaTotal()));
					}
					if (concursoMontoVentas.getOidTipoMontoVenta().equals(
							Constants.INC_MONTOVENTA_VENTA_PROMEDIO)) {
						concursoMontoVentas
								.setCantidadMontoVenta(new BigDecimal(f
										.getMontoVentaPromedio()));
					}
				}

			} else {
				ConcursoMontoVentas concursoMontoVentas = null;

				if (!StringUtils.isEmpty(f.getMontoVentaMinimo())) {
					concursoMontoVentas = new ConcursoMontoVentas();
					concursoMontoVentas.setCantidadMontoVenta(new BigDecimal(f
							.getMontoVentaMinimo()));
					concursoMontoVentas
							.setOidTipoMontoVenta(Constants.INC_MONTOVENTA_MONTO_MINIMO);
					listConcursoMontoVentas.add(concursoMontoVentas);
				}

				if (!StringUtils.isEmpty(f.getMontoVentaUnidad())) {
					concursoMontoVentas = new ConcursoMontoVentas();
					concursoMontoVentas.setCantidadMontoVenta(new BigDecimal(f
							.getMontoVentaUnidad()));
					concursoMontoVentas
							.setOidTipoMontoVenta(Constants.INC_MONTOVENTA_UNIDAD_MINIMA);
					listConcursoMontoVentas.add(concursoMontoVentas);
				}

				if (!StringUtils.isEmpty(f.getMontoVentaTotal())) {
					concursoMontoVentas = new ConcursoMontoVentas();
					concursoMontoVentas.setCantidadMontoVenta(new BigDecimal(f
							.getMontoVentaTotal()));
					concursoMontoVentas
							.setOidTipoMontoVenta(Constants.INC_MONTOVENTA_MONTO_TOTAL);
					listConcursoMontoVentas.add(concursoMontoVentas);
				}

				if (!StringUtils.isEmpty(f.getMontoVentaPromedio())) {
					concursoMontoVentas = new ConcursoMontoVentas();
					concursoMontoVentas.setCantidadMontoVenta(new BigDecimal(f
							.getMontoVentaPromedio()));
					concursoMontoVentas
							.setOidTipoMontoVenta(Constants.INC_MONTOVENTA_VENTA_PROMEDIO);
					listConcursoMontoVentas.add(concursoMontoVentas);
				}
			}

			concursoParametrosGenerales
					.setListConcursoMontoVentas(listConcursoMontoVentas);
		}

		// Requisitos de Premiacion
		if (!StringUtils.isEmpty(f.getMontoMinimoConcurso()))
			concursoRequisitoPremiacion.setMontoMinimoConcurso(new BigDecimal(f
					.getMontoMinimoConcurso()));
		else
			concursoRequisitoPremiacion.setMontoMinimoConcurso(new BigDecimal(
					"0"));
		if (!StringUtils.isEmpty(f.getNumeroPedidos()))
			concursoRequisitoPremiacion.setNumeroPedidos(new Integer(f
					.getNumeroPedidos()));
		else
			concursoRequisitoPremiacion.setNumeroPedidos(null);
		if (!StringUtils.isEmpty(f.getCuotaIngreso()))
			concursoRequisitoPremiacion.setCuotaIngreso(new Integer(f
					.getCuotaIngreso()));
		else
			concursoRequisitoPremiacion.setCuotaIngreso(null);
		concursoRequisitoPremiacion.setIndicadorPedidoEnPeriodo(new Integer(f
				.getIndicadorPedidoEnPeriodo()));
		if (!StringUtils.isEmpty(f.getMontoMinimoPedidoPremiacion()))
			concursoRequisitoPremiacion.setMontoMinimoPedido(new BigDecimal(f
					.getMontoMinimoPedidoPremiacion()));
		else
			concursoRequisitoPremiacion
					.setMontoMinimoPedido(new BigDecimal("0"));
		concursoRequisitoPremiacion
				.setIndicadorAsistenciaCurso(new Integer("0"));

		// si es tipoConcurso=RECONOCIMIENTO, indicadorDespacho = 2
		if (f.getOidTipoConcursoReconocimiento().equals(f.getOidTipoConcurso())) {
			concursoRequisitoPremiacion.setIndicadorDespacho(new Integer(2));
		} else
			concursoRequisitoPremiacion.setIndicadorDespacho(null);

		// Parametros de Premiacion
		if (!StringUtils.isEmpty(f.getOidTipoPremiacion()))
			concursoParametrosPremiacion.setOidTipoPremiacion(new Long(f
					.getOidTipoPremiacion()));
		if (!StringUtils.isEmpty(f.getNumeroNiveles()))
			concursoParametrosPremiacion.setNumeroNiveles(new Integer(f
					.getNumeroNiveles()));
		if (!StringUtils.isEmpty(f.getCodigoPeriodoDespacho())) {
			concursoParametrosPremiacion.setCodigoPeriodoDespacho(f
					.getCodigoPeriodoDespacho());
			concursoParametrosPremiacion.setCodigoPeriodoDespachoInicio(f
					.getCodigoPeriodoDespachoInicio());
			concursoParametrosPremiacion.setPeriodoDespachoExigido(new Integer(
					1));
		} else {
			concursoParametrosPremiacion.setCodigoPeriodoDespacho("");
			concursoParametrosPremiacion.setCodigoPeriodoDespachoInicio("");
			concursoParametrosPremiacion.setPeriodoDespachoExigido(new Integer(
					0));
		}

		// concursoParametrosPremiacion.setIndicadorPremiosElectivos(new
		// Integer(f.getIndicadorPremiosElectivos()));
		if (!StringUtils.isEmpty(f.getOidTipoEleccion()))
			concursoParametrosPremiacion.setOidTipoEleccion(new Long(f
					.getOidTipoEleccion()));
		else
			concursoParametrosPremiacion.setOidTipoEleccion(null);
		concursoParametrosPremiacion.setPremiosAcumulativosNiveles(new Integer(
				f.getPremiosAcumulativosNiveles()));
		if (!StringUtils.isEmpty(f.getHastaNivel()))
			concursoParametrosPremiacion.setHastaNivel(new Integer(f
					.getHastaNivel()));
		else
			concursoParametrosPremiacion.setHastaNivel(null);
		concursoParametrosPremiacion.setIndicadorNivelesRotativos(new Integer(f
				.getIndicadorNivelesRotativos()));
		if (!StringUtils.isEmpty(f.getNumeroRotaciones()))
			concursoParametrosPremiacion.setNumeroRotaciones(new Integer(f
					.getNumeroRotaciones()));
		else {
			if (f.getOidTipoPremiacion().equals(
					Constants.INC_TIPO_PREMIACION_POR_NIVELES))
				concursoParametrosPremiacion
						.setNumeroRotaciones(new Integer(1));
			else
				concursoParametrosPremiacion.setNumeroRotaciones(null);
		}

		concursoParametrosPremiacion.setAccesoNivelSuperior(new Integer(f
				.getAccesoNivelSuperior()));
		if (indRedefinirNivelPremiacion.equals("S"))
			concursoParametrosPremiacion.setIndRedefinirNivelPremiacion(true);

		concursoParametrosPremiacion.setListConcursoNivelPremiacion(f
				.getListNivelesPremiacion());

		concursoParametrosPremiacion
				.setIndicadorPremiosElectivos(new Integer(0));
		for (int i = 0; i < f.getListNivelesPremiacion().size(); i++) {
			ConcursoNivelPremiacion concursoNivelPremiacion = (ConcursoNivelPremiacion) f
					.getListNivelesPremiacion().get(i);

			if (concursoNivelPremiacion.getIndicadorNivelSelectivo().intValue() == 1) {
				concursoParametrosPremiacion
						.setIndicadorPremiosElectivos(new Integer(1));
				break;
			}
		}

		// VALIDAMOS SI EL CONCURSO HA CUMPLIDO NIVELES DE PREMIACION PARA
		// ACTIVAR EL CONCURSO
		if (!StringUtils.isEmpty(f.getNumeroNiveles())) {
			boolean activarConcurso = false;
			Integer numeroNiveles = new Integer(f.getNumeroNiveles());

			if (concursoParametrosPremiacion.getListConcursoNivelPremiacion()
					.size() == numeroNiveles) {
				activarConcurso = true;

				for (int i = 0; i < concursoParametrosPremiacion
						.getListConcursoNivelPremiacion().size(); i++) {
					ConcursoNivelPremiacion nivel = (ConcursoNivelPremiacion) concursoParametrosPremiacion
							.getListConcursoNivelPremiacion().get(i);

					if (f.getOidTipoPremiacion().equals(
							Constants.INC_TIPO_PREMIACION_POR_NIVELES)) {
						if ((nivel.getCantidadInicialPuntos() == null || nivel
								.getCantidadFinalPuntos() == null)
								|| (nivel.getListConcursoLotePremioArticulo()
										.size() == 0)) {
							activarConcurso = false;
							break;
						}
					} else {
						if ((nivel.getCantidadFijaPuntos() == null)
								|| (nivel.getListConcursoLotePremioArticulo()
										.size() == 0)) {
							activarConcurso = false;
							break;
						}
					}
				}
			}

			if (activarConcurso) {
				concursoParametrosGenerales.setIndicadorActivo(new Integer(1));
				if (concursoParametrosGenerales.getConcursoVersion() != null)
					concursoParametrosGenerales.getConcursoVersion()
							.setOidEstadoConcurso(new Integer(2));
			} else
				concursoParametrosGenerales.setIndicadorActivo(new Integer(0));
		}

		// CREACION DE CONCURSO DE RECOMENDACION
		if (concursoParametrosConsultoras.getOidConcursoPuntajeRecomendada() == null) {
			if ((concursoParametrosConsultoras.getGenerarPuntajeARecomendadas() != null)
					&& (concursoParametrosConsultoras
							.getGenerarPuntajeARecomendadas().intValue() == 1)) {

				MantenimientoINCConfiguracionConcursoService service = (MantenimientoINCConfiguracionConcursoService) getBean("spusicc.mantenimientoINCConfiguracionConcursoService");

				// CREAMOS UN CONCURSO DE CONSTANCIA
				ConcursoParametrosGenerales concursoRecomendada = crearConcursoDefault(codigoPais);

				concursoRecomendada.setOidClasificacionConcurso(new Long(f
						.getOidTipoConcursoConstancia()));
				concursoRecomendada.setOidBaseCalculo(new Long(
						Constants.INC_BASE_CALCULO_UNIDADES));
				concursoRecomendada.setOidPlantilla(new Long(
						INC_PLANTILLA_CONSTANCIA_RECOMENDACION));
				concursoRecomendada.setIndicadorNoGeneraPuntaje(new Integer(1));
				concursoRecomendada.setCodigoPeriodoDesde(f
						.getCodigoPeriodoInicio());
				concursoRecomendada.setCodigoPeriodoHasta(f
						.getCodigoPeriodoFin());
				concursoRecomendada.setNombreConcurso(f.getNombreConcurso()
						+ "(recomendada)");
				concursoRecomendada
						.setOidTipoPrograma(concursoParametrosGenerales
								.getOidTipoPrograma());

				ConcursoVersion concursoVersionRec = new ConcursoVersion();
				concursoVersionRec
						.setOidVigenciaConcurso(Constants.INC_OID_VIG_CONCU_NO_VIGENTE);
				concursoVersionRec
						.setOidEstadoConcurso(Constants.INC_OID_ESTADO_EN_CREACION);
				concursoVersionRec.setCodigoPeriodo(f.getCodigoPeriodoInicio());
				concursoVersionRec.setVersionNueva(new Integer(1));
				concursoVersionRec.setCodigoUsuario(usuario.getLogin());
				concursoRecomendada.setConcursoVersion(concursoVersionRec);

				concursoRecomendada.getConcursoObtencionPuntos()
						.setIndicadorComunicacion(new Integer(0));

				concursoRecomendada.getConcursoDespachoPremios()
						.setIndicadorComunicacionAutomatico(new Integer(1));
				concursoRecomendada.getConcursoDespachoPremios()
						.setIndicadorComunicacionManual(new Integer(0));
				concursoRecomendada.getConcursoDespachoPremios()
						.setCodigoMensajeAutomatico(
								Constants.INC_MENSAJE_DESPACHO_NODUPLA_DEFAULT);
				concursoRecomendada.getConcursoDespachoPremios()
						.setCodigoMensajeManual("");

				concursoRecomendada.getConcursoParametrosPremiacion()
						.setCodigoPeriodoDespacho(f.getCodigoPeriodoDespacho());
				concursoRecomendada.getConcursoParametrosPremiacion()
						.setCodigoPeriodoDespachoInicio(
								f.getCodigoPeriodoDespachoInicio());
				concursoRecomendada.getConcursoParametrosPremiacion()
						.setNumeroRotaciones(new Integer(1));
				concursoRecomendada.getConcursoParametrosPremiacion()
						.setAccesoNivelSuperior(new Integer(0));

				if (!StringUtils.isEmpty(f.getCodigoPeriodoDespacho())) {
					concursoRecomendada.getConcursoParametrosPremiacion()
							.setPeriodoDespachoExigido(new Integer(1));
				} else {
					concursoRecomendada.getConcursoParametrosPremiacion()
							.setPeriodoDespachoExigido(new Integer(0));
				}

				ConcursoParametrosPremiacion concursoParametrosPremiacionRec = concursoRecomendada
						.getConcursoParametrosPremiacion();
				concursoParametrosPremiacionRec
						.setIndRedefinirNivelPremiacion(true);

				concursoRecomendada
						.setIndicadorActivo(concursoParametrosGenerales
								.getIndicadorActivo());

				// seteamos la lista de Niveles
				if (concursoRecomendada.getIndicadorActivo().intValue() == 1) {
					List listConcursoNivelPremiacion = concursoParametrosPremiacion
							.getListConcursoNivelPremiacion();
					ConcursoNivelPremiacion concursoNivelPremiacion = (ConcursoNivelPremiacion) listConcursoNivelPremiacion
							.get(0);

					ConcursoNivelPremiacion concursoNivelPremiacionRec = new ConcursoNivelPremiacion();
					concursoNivelPremiacionRec.setNumeroNivel(new Integer(1));
					concursoNivelPremiacionRec
							.setOidTipoPremio(concursoNivelPremiacion
									.getOidTipoPremio());
					concursoNivelPremiacionRec
							.setIndicadorNivelSelectivo(concursoNivelPremiacion
									.getIndicadorNivelSelectivo());
					concursoNivelPremiacionRec
							.setCantidadInicialPuntos(concursoNivelPremiacion
									.getCantidadInicialPuntos());
					concursoNivelPremiacionRec
							.setCantidadFinalPuntos(concursoNivelPremiacion
									.getCantidadFinalPuntos());
					concursoNivelPremiacionRec
							.setCantidadFijaPuntos(concursoNivelPremiacion
									.getCantidadFijaPuntos());

					ConcursoPremioArticulo concursoPremioArticuloRec = new ConcursoPremioArticulo();
					concursoPremioArticuloRec.setNumeroUnidades(new Integer(1));
					concursoNivelPremiacionRec
							.setConcursoPremioArticulo(concursoPremioArticuloRec);

					concursoNivelPremiacionRec
							.setListConcursoLotePremioArticulo(new ArrayList());
					concursoParametrosPremiacionRec
							.setListConcursoNivelPremiacion(new ArrayList());
					concursoParametrosPremiacionRec
							.getListConcursoNivelPremiacion().add(
									concursoNivelPremiacionRec);

					List listConcursoLotePremioArticulo = concursoNivelPremiacion
							.getListConcursoLotePremioArticulo();
					for (int k = 0; k < listConcursoLotePremioArticulo.size(); k++) {
						ConcursoLotePremioArticulo concursoLotePremioArticulo = (ConcursoLotePremioArticulo) listConcursoLotePremioArticulo
								.get(k);

						ConcursoLotePremioArticulo concursoLotePremioArticuloRec = new ConcursoLotePremioArticulo();
						concursoLotePremioArticuloRec
								.setDescripcionLote(concursoLotePremioArticulo
										.getDescripcionLote());
						concursoLotePremioArticuloRec
								.setNumeroLote(concursoLotePremioArticulo
										.getNumeroLote());
						concursoLotePremioArticuloRec
								.setNumeroPremio(concursoLotePremioArticulo
										.getNumeroPremio());
						concursoLotePremioArticuloRec
								.setListConcursoArticuloLote(new ArrayList());
						concursoNivelPremiacionRec
								.getListConcursoLotePremioArticulo().add(
										concursoLotePremioArticuloRec);

						List listConcursoArticuloLote = concursoLotePremioArticulo
								.getListConcursoArticuloLote();
						for (int m = 0; m < listConcursoArticuloLote.size(); m++) {
							ConcursoArticuloLote concursoArticuloLote = (ConcursoArticuloLote) listConcursoArticuloLote
									.get(m);

							ConcursoArticuloLote concursoArticuloLoteRec = new ConcursoArticuloLote();
							concursoArticuloLoteRec
									.setNumeroNivel(concursoArticuloLote
											.getNumeroNivel());
							concursoArticuloLoteRec
									.setDescripcionLote(concursoArticuloLote
											.getDescripcionLote());
							concursoArticuloLoteRec
									.setNumeroPremio(concursoArticuloLote
											.getNumeroPremio());
							concursoArticuloLoteRec
									.setCodigoSAP(concursoArticuloLote
											.getCodigoSAP());
							concursoArticuloLoteRec
									.setNumeroUnidades(concursoArticuloLote
											.getNumeroUnidades());
							concursoArticuloLoteRec
									.setPrecioPublico(concursoArticuloLote
											.getPrecioPublico());
							concursoArticuloLoteRec
									.setObservaciones(concursoArticuloLote
											.getObservaciones());
							concursoArticuloLoteRec
									.setIndicadorDespacho(concursoArticuloLote
											.getIndicadorDespacho());
							concursoArticuloLoteRec
									.setCodigoVentaFicticio(concursoArticuloLote
											.getCodigoVentaFicticio());
							concursoArticuloLoteRec
									.setIndicadorCentroServicio(concursoArticuloLote
											.getIndicadorCentroServicio());
							concursoArticuloLoteRec
									.setIndicadorTipoEntrega(concursoArticuloLote
											.getIndicadorTipoEntrega());
							concursoLotePremioArticuloRec
									.getListConcursoArticuloLote().add(
											concursoArticuloLoteRec);
						}

					}

				} else {
					ConcursoNivelPremiacion concursoNivelPremiacionRec = new ConcursoNivelPremiacion();
					concursoNivelPremiacionRec.setNumeroNivel(new Integer(1));
					concursoNivelPremiacionRec
							.setOidTipoPremio(Constants.INC_TIPO_PREMIO_ARTICULO);
					concursoNivelPremiacionRec
							.setIndicadorNivelSelectivo(new Integer(
									Constants.NUMERO_CERO));
					concursoNivelPremiacionRec
							.setCantidadInicialPuntos(new Integer(1));
					concursoNivelPremiacionRec
							.setCantidadFinalPuntos(new Integer(9999999));

					ConcursoPremioArticulo concursoPremioArticuloRec = new ConcursoPremioArticulo();
					concursoPremioArticuloRec.setNumeroUnidades(new Integer(1));
					concursoNivelPremiacionRec
							.setConcursoPremioArticulo(concursoPremioArticuloRec);

					concursoNivelPremiacionRec
							.setListConcursoLotePremioArticulo(new ArrayList());
					concursoParametrosPremiacionRec
							.setListConcursoNivelPremiacion(new ArrayList());
					concursoParametrosPremiacionRec
							.getListConcursoNivelPremiacion().add(
									concursoNivelPremiacionRec);
				}

				concursoRecomendada
						.setConcursoParametrosPremiacion(concursoParametrosPremiacionRec);

				concursoRecomendada.getConcursoRequisitoPremiacion()
						.setMontoMinimoConcurso(new BigDecimal("0"));
				concursoRecomendada.getConcursoRequisitoPremiacion()
						.setMontoMinimoPedido(new BigDecimal("1"));
				concursoRecomendada.getConcursoRequisitoPremiacion()
						.setIndicadorPedidoEnPeriodo(
								concursoRequisitoPremiacion
										.getIndicadorPedidoEnPeriodo());

				concursoParametrosConsultoras
						.setConcursoRecomendada(concursoRecomendada);
			}
		} else {
			concursoParametrosConsultoras.setConcursoRecomendada(null);
		}

		// Productos
		List listConcursoProductosValidos = this.incProductosValidosList;
		List listConcursoProductosBonificados = this.incProductosBonificadosList;
		List listConcursoProductosExcluidos = this.incProductosExcluidosList;
		List listConcursoProductosExigidos = this.incProductosExigidosList;

		if (concursoProductos == null) {
			if ((listConcursoProductosValidos.size() > 0)
					|| (listConcursoProductosBonificados.size() > 0)
					|| (listConcursoProductosExcluidos.size() > 0)
					|| (listConcursoProductosExigidos.size() > 0)) {

				concursoProductos = new ConcursoProductos();
				concursoProductos.setComunicacionValidos(new Integer(0));
				concursoProductos.setComunicacionBonificados(new Integer(0));
				concursoProductos.setComunicacionExigidos(new Integer(0));
				concursoProductos.setComunicacionExcluidos(new Integer(0));

				concursoProductos.setIndRedefinirProductosValidos(true);
				concursoProductos.setIndRedefinirProductosBonificados(true);
				concursoProductos.setIndRedefinirProductosExcluidos(true);
				concursoProductos.setIndRedefinirProductosExigidos(true);

				concursoProductos
						.setListConcursoProductosValidos(listConcursoProductosValidos);
				concursoProductos
						.setListConcursoProductosBonificados(listConcursoProductosBonificados);
				concursoProductos
						.setListConcursoProductosExcluidos(listConcursoProductosExcluidos);
				concursoProductos
						.setListConcursoProductosExigidos(listConcursoProductosExigidos);

				concursoParametrosGenerales
						.setConcursoProductos(concursoProductos);
			}
		} else {
			concursoProductos.setIndRedefinirProductosValidos(f
					.isIndRedefinirProductosValidos());
			concursoProductos.setIndRedefinirProductosBonificados(f
					.isIndRedefinirProductosBonificados());
			concursoProductos.setIndRedefinirProductosExcluidos(f
					.isIndRedefinirProductosExcluidos());
			concursoProductos.setIndRedefinirProductosExigidos(f
					.isIndRedefinirProductosExigidos());

			concursoProductos
					.setListConcursoProductosValidos(listConcursoProductosValidos);
			concursoProductos
					.setListConcursoProductosBonificados(listConcursoProductosBonificados);
			concursoProductos
					.setListConcursoProductosExcluidos(listConcursoProductosExcluidos);
			concursoProductos
					.setListConcursoProductosExigidos(listConcursoProductosExigidos);
		}

		if (f.isNewRecord()) {
			ConcursoVersion concursoVersion = new ConcursoVersion();
			concursoVersion
					.setOidVigenciaConcurso(Constants.INC_OID_VIG_CONCU_NO_VIGENTE);
			concursoVersion
					.setOidEstadoConcurso(Constants.INC_OID_ESTADO_EN_CREACION);
			concursoVersion.setCodigoPeriodo(f.getCodigoPeriodoInicio());
			concursoVersion.setVersionNueva(new Integer(1));
			concursoVersion.setCodigoUsuario(usuario.getLogin());
			concursoParametrosGenerales.setConcursoVersion(concursoVersion);
		}

		// si es tipoConcurso=FICTICIO, no se activa el concurso y no genera
		// puntaje
		if (f.getOidTipoConcursoFicticio() != null
				&& f.getOidTipoConcursoFicticio()
						.equals(f.getOidTipoConcurso())) {
			concursoParametrosGenerales.setIndicadorActivo(new Integer(0));
			concursoParametrosGenerales
					.setIndicadorNoGeneraPuntaje(new Integer(1));
		}

		// Se debe validar si la campaña de proceso del sistema se encuentra
		// entre la campaña de inicio
		// y fin del concurso entonces poner como vigente al concurso
		Integer codigoPeriodoActual = new Integer(f.getCodigoPeriodoProceso());
		Integer codigoPeriodoInicio = new Integer(f.getCodigoPeriodoInicio());
		Integer codigoPeriodoFin = new Integer(f.getCodigoPeriodoFin());

		if (codigoPeriodoInicio.intValue() <= codigoPeriodoActual.intValue()
				&& codigoPeriodoFin.intValue() >= codigoPeriodoActual
						.intValue()) {

			if (concursoParametrosGenerales.getIndicadorActivo().intValue() == 1
					&& concursoParametrosGenerales.getConcursoVersion() != null
					&& concursoParametrosGenerales.getConcursoVersion()
							.getOidVigenciaConcurso().intValue() == 2) {

				concursoParametrosGenerales.getConcursoVersion()
						.setOidVigenciaConcurso(new Integer(1));
				concursoParametrosGenerales.getConcursoVersion()
						.setOidEstadoConcurso(new Integer(2));

			}

		}

		if (StringUtils.isBlank(f.getIndicadorComunicacion()))
			concursoParametrosGenerales.setIndicadorComunicacion(Integer
					.valueOf(Constants.NUMERO_CERO));
		else
			concursoParametrosGenerales.setIndicadorComunicacion(Integer
					.valueOf(f.getIndicadorComunicacion()));

		concursoParametrosGenerales.setIndicadorCPP(f.getIndicadorCPP());
		concursoParametrosGenerales.setOidCPP(f.getOidCPP());

		if (StringUtils.isBlank(f.getCodigoTipoDespachoCPP()))
			concursoRequisitoPremiacion.setIndicadorDespacho(null);
		else
			concursoRequisitoPremiacion.setIndicadorDespacho(Integer.parseInt(f
					.getCodigoTipoDespachoCPP()));

		concursoObtencionPuntos
				.setIndicadorExigePasarPedidoCampanyaAnteriorCPP(f
						.getIndicadorExigePasarPedidoCampanyaAnteriorCPP());
		concursoObtencionPuntos.setPuntosAbonarRecomendacionEfectivaCPP(f
				.getPuntosAbonarRecomendacionEfectivaCPP());
		concursoObtencionPuntos.setCampanyasSinPedidoParaCancelacionPuntosCPP(f
				.getCampanyasSinPedidoParaCancelacionPuntosCPP());
		concursoRequisitoPremiacion.setIndicadorPriorizaWeb(f.getIndicadorPriorizaWeb());

		// PER-SiCC-2015-0340 fecha: 30/06/2015
		 concursoParametrosPremiacion.setPorcentajeMaximoDescuentoCPP(f.getPorcentajeMaximoDescuentoCPP());

		return concursoParametrosGenerales;
	}

	/**
	 * @param codigoPais
	 * @return
	 */
	private ConcursoParametrosGenerales crearConcursoDefault(String codigoPais) {
		ConcursoParametrosGenerales concursoParametrosGenerales = new ConcursoParametrosGenerales();
		concursoParametrosGenerales.setVersion(new Integer(1));
		concursoParametrosGenerales.setIndicadorRanking(new Integer(0));
		concursoParametrosGenerales.setIndicadorDuplaCyzone(new Integer(0));
		concursoParametrosGenerales.setCodigoPais(codigoPais);
		concursoParametrosGenerales
				.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		concursoParametrosGenerales
				.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		concursoParametrosGenerales
				.setCodigoAcceso(Constants.CODIGO_ACCESO_DEFAULT);
		concursoParametrosGenerales.setExpresionPuntaje("puntos");
		concursoParametrosGenerales.setDirigidoA(new Long(1));
		concursoParametrosGenerales.setIndicadorNoGeneraPuntaje(new Integer(0));

		concursoParametrosGenerales.setIndicadorDevoluciones(new Integer(1));
		concursoParametrosGenerales.setIndicadorAnulaciones(new Integer(1));
		concursoParametrosGenerales.setFaltantesNoAnunciados(new Integer(1));
		concursoParametrosGenerales.setOidTipoExigencia(new Long(1));

		if (concursoParametrosGenerales.getConcursoObtencionPuntos() == null)
			concursoParametrosGenerales
					.setConcursoObtencionPuntos(new ConcursoObtencionPuntos());

		if (concursoParametrosGenerales.getConcursoDespachoPremios() == null)
			concursoParametrosGenerales
					.setConcursoDespachoPremios(new ConcursoDespachoPremios());

		if (concursoParametrosGenerales.getConcursoParametrosConsultoras() == null)
			concursoParametrosGenerales
					.setConcursoParametrosConsultoras(new ConcursoParametrosConsultoras());

		if (concursoParametrosGenerales.getConcursoRequisitoPremiacion() == null) {
			concursoParametrosGenerales
					.setConcursoRequisitoPremiacion(new ConcursoRequisitoPremiacion());
			concursoParametrosGenerales.getConcursoRequisitoPremiacion()
					.setIndicadorPagoATiempo(new Integer(0));
		}

		if (concursoParametrosGenerales.getConcursoParametrosPremiacion() == null) {
			concursoParametrosGenerales
					.setConcursoParametrosPremiacion(new ConcursoParametrosPremiacion());
			concursoParametrosGenerales.getConcursoParametrosPremiacion()
					.setIndicadorComunicacion(new Integer(0));
		}

		ConcursoObtencionPuntos concursoObtencionPuntos = concursoParametrosGenerales
				.getConcursoObtencionPuntos();
		ConcursoParametrosConsultoras concursoParametrosConsultoras = concursoParametrosGenerales
				.getConcursoParametrosConsultoras();
		ConcursoParametrosPremiacion concursoParametrosPremiacion = concursoParametrosGenerales
				.getConcursoParametrosPremiacion();

		concursoObtencionPuntos.setFactorConversion(new BigDecimal("1"));
		concursoObtencionPuntos.setNumeroPuntosAsignar(new Long(1));
		concursoObtencionPuntos.setIndicadorPuntajeAcumulativo(new Integer(1));
		concursoObtencionPuntos.setIndicadorActividad(new Integer(0));
		concursoObtencionPuntos.setIndicadorConstancia(new Integer(0));

		concursoParametrosPremiacion.setOidTipoPremiacion(new Long(
				Constants.INC_TIPO_PREMIACION_POR_NIVELES));
		concursoParametrosPremiacion.setNumeroNiveles(new Integer(1));

		concursoParametrosConsultoras.setMontoMinimoPedido(new BigDecimal("1"));

		// PER-SiCC-2015-0340 fecha: 30/06/2015
		concursoParametrosPremiacion.setPorcentajeMaximoDescuentoCPP(String.valueOf(Constants.NUMERO_CIEN));

		return concursoParametrosGenerales;
	}

	public void setearCheckBoxString(MantenimientoINCConfiguracionConcursoForm f) {
		if (indicadorMultiMarcaBoolean)
			f.setIndicadorMultiMarca("1");
		else
			f.setIndicadorMultiMarca("0");
		if (faltantesNoAnunciadosBoolean)
			f.setFaltantesNoAnunciados("1");
		else
			f.setFaltantesNoAnunciados("0");
		if (indicadorPuntajeAcumulativoBoolean)
			f.setIndicadorPuntajeAcumulativo("1");
		else
			f.setIndicadorPuntajeAcumulativo("0");
		if (indicadorDevolucionesBoolean)
			f.setIndicadorDevoluciones("1");
		else
			f.setIndicadorDevoluciones("0");
		if (indicadorAnulacionesBoolean)
			f.setIndicadorAnulaciones("1");
		else
			f.setIndicadorAnulaciones("0");
		if (indicadorActividadBoolean)
			f.setIndicadorActividad("1");
		else
			f.setIndicadorActividad("0");
		if (indicadorConstanciaBoolean)
			f.setIndicadorConstancia("1");
		else
			f.setIndicadorConstancia("0");
		if (indicadorComunicacionBoolean)
			f.setIndicadorComunicacion("1");
		else
			f.setIndicadorComunicacion("0");
		if (indicadorObtencionPuntosBoolean)
			f.setIndicadorObtencionPuntos("1");
		else
			f.setIndicadorObtencionPuntos("0");
		if (indicadorPremioCampEfectBoolean)
			f.setIndicadorPremioCampEfect("1");
		else
			f.setIndicadorPremioCampEfect("0");
		if (premiosAcumulativosNivelesBoolean)
			f.setPremiosAcumulativosNiveles("1");
		else
			f.setPremiosAcumulativosNiveles("0");
		if (indicadorNivelesRotativosBoolean)
			f.setIndicadorNivelesRotativos("1");
		else
			f.setIndicadorNivelesRotativos("0");
		if (accesoNivelSuperiorBoolean)
			f.setAccesoNivelSuperior("1");
		else
			f.setAccesoNivelSuperior("0");
		if (indicadorPorPedidoBoolean)
			f.setIndicadorPorPedido("2");
		else
			f.setIndicadorPorPedido("0");
		if (indicadorRangoPedidosBoolean)
			f.setIndicadorRangoPedidos("1");
		else
			f.setIndicadorRangoPedidos("0");
		if (indicadorExigePasarPedidoCampanyaAnteriorCPPBoolean)
			f.setIndicadorExigePasarPedidoCampanyaAnteriorCPP("1");
		else
			f.setIndicadorExigePasarPedidoCampanyaAnteriorCPP("0");
		if (indicadorCPPBoolean)
			f.setIndicadorCPP("1");
		else
			f.setIndicadorCPP("0");

		if (this.generarPuntajeARecomendadasBoolean)
			f.setGenerarPuntajeARecomendadas("1");
		else
			f.setGenerarPuntajeARecomendadas("0");
		
		if(indicadorPedidoEnPeriodoBoolean)
			f.setIndicadorPedidoEnPeriodo("1");
		else
			f.setIndicadorPedidoEnPeriodo("0");
		
		if (indicadorPriorizaWebBoolean)
			f.setIndicadorPriorizaWeb("1");
		else
			f.setIndicadorPriorizaWeb("0");
	}

	public void setearCheckBoxBoolean(
			MantenimientoINCConfiguracionConcursoForm f) {
		if (StringUtils.isNotBlank(f.getIndicadorMultiMarca())
				&& f.getIndicadorMultiMarca().equals("1"))
			indicadorMultiMarcaBoolean = true;
		else
			indicadorMultiMarcaBoolean = false;
		if (StringUtils.isNotBlank(f.getFaltantesNoAnunciados())
				&& f.getFaltantesNoAnunciados().equals("1"))
			faltantesNoAnunciadosBoolean = true;
		else
			faltantesNoAnunciadosBoolean = false;
		if (StringUtils.isNotBlank(f.getIndicadorPuntajeAcumulativo())
				&& f.getIndicadorPuntajeAcumulativo().equals("1"))
			indicadorPuntajeAcumulativoBoolean = true;
		else
			indicadorPuntajeAcumulativoBoolean = false;
		if (StringUtils.isNotBlank(f.getIndicadorDevoluciones())
				&& f.getIndicadorDevoluciones().equals("1"))
			indicadorDevolucionesBoolean = true;
		else
			indicadorDevolucionesBoolean = false;
		if (StringUtils.isNotBlank(f.getIndicadorAnulaciones())
				&& f.getIndicadorAnulaciones().equals("1"))
			indicadorAnulacionesBoolean = true;
		else
			indicadorAnulacionesBoolean = false;
		if (StringUtils.isNotBlank(f.getIndicadorActividad())
				&& f.getIndicadorActividad().equals("1"))
			indicadorActividadBoolean = true;
		else
			indicadorActividadBoolean = false;
		if (StringUtils.isNotBlank(f.getIndicadorConstancia())
				&& f.getIndicadorConstancia().equals("1"))
			indicadorConstanciaBoolean = true;
		else
			indicadorConstanciaBoolean = false;
		if (StringUtils.isNotBlank(f.getIndicadorComunicacion())
				&& f.getIndicadorComunicacion().equals("1"))
			indicadorComunicacionBoolean = true;
		else
			indicadorComunicacionBoolean = false;
		if (StringUtils.isNotBlank(f.getIndicadorObtencionPuntos())
				&& f.getIndicadorObtencionPuntos().equals("1"))
			indicadorObtencionPuntosBoolean = true;
		else {
			indicadorObtencionPuntosBoolean = false;
			codigoMensajePuntosDisabled = true;
		}
		if (StringUtils.isNotBlank(f.getIndicadorPremioCampEfect())
				&& f.getIndicadorPremioCampEfect().equals("1"))
			indicadorPremioCampEfectBoolean = true;
		else
			indicadorPremioCampEfectBoolean = false;
		if (StringUtils.isNotBlank(f.getPremiosAcumulativosNiveles())
				&& f.getPremiosAcumulativosNiveles().equals("1"))
			premiosAcumulativosNivelesBoolean = true;
		else
			premiosAcumulativosNivelesBoolean = false;
		if (StringUtils.isNotBlank(f.getIndicadorNivelesRotativos())
				&& f.getIndicadorNivelesRotativos().equals("1"))
			indicadorNivelesRotativosBoolean = true;
		else
			indicadorNivelesRotativosBoolean = false;
		if (StringUtils.isNotBlank(f.getAccesoNivelSuperior())
				&& f.getAccesoNivelSuperior().equals("1"))
			accesoNivelSuperiorBoolean = true;
		else
			accesoNivelSuperiorBoolean = false;

		if (StringUtils.isNotBlank(f.getIndicadorRangoPedidos())
				&& f.getIndicadorRangoPedidos() == null)
			indicadorRangoPedidosBoolean = false;
		else if (StringUtils.isNotBlank(f.getIndicadorRangoPedidos())
				&& f.getIndicadorRangoPedidos().equals("1"))
			indicadorRangoPedidosBoolean = true;
		else
			indicadorRangoPedidosBoolean = false;

		if (StringUtils.isNotBlank(f.getIndicadorPorPedido())
				&& f.getIndicadorPorPedido().equals("2"))
			indicadorPorPedidoBoolean = true;
		else
			indicadorPorPedidoBoolean = false;

		if (StringUtils.isNotBlank(f
				.getIndicadorExigePasarPedidoCampanyaAnteriorCPP())
				&& f.getIndicadorExigePasarPedidoCampanyaAnteriorCPP().equals(
						"1"))
			indicadorExigePasarPedidoCampanyaAnteriorCPPBoolean = true;
		else
			indicadorExigePasarPedidoCampanyaAnteriorCPPBoolean = false;
		if (StringUtils.isNotBlank(f.getIndicadorCPP())
				&& f.getIndicadorCPP().equals("1")) {
			indicadorCPPBoolean = true;
			codigoCPPDisabled = true;
			//indicadorExigePasarPedidoCampanyaAnteriorCPPBoolean = true;
			campanyasSinPedidoParaCancelacionPuntosCPPDisabled = false;
			puntosAbonarRecomendacionEfectivaCPPDisabled = true;
			f.setCodigoCPP("");
			f.setDescripcionCPP("");
		} else {
			indicadorCPPBoolean = false;
			codigoCPPDisabled = false;
			//indicadorExigePasarPedidoCampanyaAnteriorCPPBoolean = false;
			campanyasSinPedidoParaCancelacionPuntosCPPDisabled = true;
		}
		
		if (StringUtils.isNotBlank(f.getIndicadorPriorizaWeb())
				&& f.getIndicadorPriorizaWeb().equals("1"))
			indicadorPriorizaWebBoolean = true;
		else
			indicadorPriorizaWebBoolean = false;

		if (StringUtils.isNotBlank(f.getGenerarPuntajeARecomendadas())
				&& f.getGenerarPuntajeARecomendadas().equals(
						Constants.NUMERO_UNO))
			this.generarPuntajeARecomendadasBoolean = true;
		else
			this.generarPuntajeARecomendadasBoolean = false;
		
		if (StringUtils.isNotBlank(f.getIndicadorPedidoEnPeriodo())
				&& f.getIndicadorPedidoEnPeriodo().equals("1"))
			indicadorPedidoEnPeriodoBoolean = true;
		else
			indicadorPedidoEnPeriodoBoolean = false;
	}
	
	@Override
	protected void setConsultarAttributes() throws Exception {
		MantenimientoINCConfiguracionConcursoForm f = (MantenimientoINCConfiguracionConcursoForm) this.formMantenimiento;

		f.setEditable(false);
		f.setEditableCampannaInicio(false);
		f.setIndicadorGrabarSoloPremiacion(false);
		f.setEditableParametrosGenerales(false);
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// formMantenimiento
		MantenimientoINCConfiguracionConcursoForm f = new MantenimientoINCConfiguracionConcursoForm();

		// Inicializacion de Form's
		MantenimientoINCBonificacionPeriodoForm fBonificacion = new MantenimientoINCBonificacionPeriodoForm();
		MantenimientoINCRecomendadaPeriodoForm fRecomendada = new MantenimientoINCRecomendadaPeriodoForm();
		MantenimientoINCEstatusVentaForm fEstatus = new MantenimientoINCEstatusVentaForm();
		MantenimientoINCUnidadAdministrativaForm fUnidad = new MantenimientoINCUnidadAdministrativaForm();
		MantenimientoINCClasificacionParticipanteForm fClasificacion = new MantenimientoINCClasificacionParticipanteForm();
		MantenimientoINCPeriodoDespachoForm fPeriodoDespacho = new MantenimientoINCPeriodoDespachoForm();
		MantenimientoINCPuntajeExigidoForm fPuntajeExigido = new MantenimientoINCPuntajeExigidoForm();
		MantenimientoINCDefinirPremioForm fDefinirPremio = new MantenimientoINCDefinirPremioForm();
		MantenimientoINCDefinirPremioDescuentoForm fDefinirPremioDescuento = new MantenimientoINCDefinirPremioDescuentoForm();

		this.formBonificacion = fBonificacion;
		this.formRecomendada = fRecomendada;
		this.formEstatus = fEstatus;
		this.formUnidad = fUnidad;
		this.formClasificacion = fClasificacion;
		this.formPeriodoDespacho = fPeriodoDespacho;
		this.formPuntajeExigido = fPuntajeExigido;
		this.formDefinirPremio = fDefinirPremio;
		this.definirPremioDescuentoForm = fDefinirPremioDescuento;

		// Inicializacion de listas
		this.incConcursoEstatusList = new ArrayList();
		this.incAmbitoList = new ArrayList();
		this.incConcursoClasificacionesList = new ArrayList();
		this.incProductosValidosList = new ArrayList();
		this.incProductosBonificadosList = new ArrayList();
		this.incProductosExcluidosList = new ArrayList();
		this.incProductosExigidosList = new ArrayList();

		f.reset();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		this.siccRegionList = ajax.getRegionesByOidSubGerencia(
				f.getOidSubgerencia(), "X");
		this.consultar = false;
		this.codigoPeriodoDisabled = false;
		this.mostrarBotonSave = true;
		this.deshabilitarNumeroMinimoPedidos = false;
		f.setTipoCuadre(Constants.NUMERO_UNO);
		
		if (this.accion.equals(this.ACCION_NUEVO)) {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'setAddAttributes ' method");
			}

			// Inicializamos datatable Clasificacion TAB PARTICIPANTES
			this.dataTableEstatus = new DataTableModel(
					this.incConcursoEstatusList);
			this.dataTableUnidad = new DataTableModel(incAmbitoList);
			this.dataTableClasificacion = new DataTableModel(
					this.incConcursoClasificacionesList);

			// Inicializamos datatable TAB PRODUCTOS
			this.dataTableProductosValidos = new DataTableModel(
					this.incProductosValidosList);
			this.dataTableProductosBonificacion = new DataTableModel(
					this.incProductosBonificadosList);
			this.dataTableProductosExcluidos = new DataTableModel(
					this.incProductosExcluidosList);
			this.dataTableProductosExigidos = new DataTableModel(
					this.incProductosExigidosList);

			cargarListas(f);
			f.setCodigoPais(codigoPais);
			setHabilitarMontoVenta(true);
			setIndicadorRangoPedidosDisabled(true);
			setIndicadorPorPedidoDisabled(true);
			this.indicadorDevolucionesBoolean = true;
			this.indicadorMultiMarcaBoolean = false;
			this.indicadorCPPBoolean = false;
			this.indicadorAnulacionesBoolean = true;
			this.faltantesNoAnunciadosBoolean = true;
			this.numeroMinimoPedidosRecomendadaDisabled = true;
			this.indicadorPremioCampEfectDisabled = true;
			f.setIndicadorNoGeneraPuntaje(Constants.NRO_CERO);
			this.indicadorPuntajeAcumulativoBoolean = true;
			this.oidBaseCalculoDisabled = false;
			this.indicadorActividadBoolean = false;
			this.indicadorConstanciaBoolean = false;
			this.generarPuntajeARecomendadasBoolean = false;
			this.indicadorGrabarParametrosGenerales = false;

			// PREMIACION
			f.setMontoMinimoPedidoPremiacion("1");
			this.indicadorPedidoEnPeriodoBoolean = true;
			this.cuotaIngresoDisabled = true;
			this.hastaNivelDisabled = true;
			this.numeroRotacionesDisabled = true;
			// ---ListaPremiacion
			this.cantidadFijaPuntosDisabled = true;
			this.plazoEntregaDisabled = true;

			// PRODUCTOS
			this.codigoPeriodoDisabled = false; // cambie
			this.puntosUnidadDisabled = true;
			this.factorMultiplicadorDisabled = true;
			this.unidadesExigidasDisabled = true;
			this.montoExigidoDisabled = true;
			this.puntosExigidosDisabled = true;
			this.codigoPeriodoRendered = true;
			this.oidMarcaProductoRendered = true;// cambie

			// TAB MENSAJES
			this.indicadorComunicacionBoolean = true;
			this.codigoMensajePuntosDisabled = true;

			// TAB PROGRAMA PUNTOS
			this.puntosAbonarRecomendacionEfectivaCPPDisabled = true;
			this.campanyasSinPedidoParaCancelacionPuntosCPPDisabled = true;
			f.setCodigoCPP("");
			f.setDescripcionCPP("");
			f.setOidCPP("");
			f.setCodigoTipoDespachoCPP("");
			f.setIndicadorExigePasarPedidoCampanyaAnteriorCPP(Constants.NUMERO_CERO);
			f.setPuntosAbonarRecomendacionEfectivaCPP(Constants.NUMERO_UNO);
			f.setCampanyasSinPedidoParaCancelacionPuntosCPP("");
			f.setIndicadorPriorizaWeb(Constants.NUMERO_CERO);

			// PER-SiCC-2015-0340 fecha: 30/06/2015
			f.setPorcentajeMaximoDescuentoCPP(String.valueOf(Constants.NUMERO_CIEN));

			f.setFactorConversion(Constants.NRO_UNO);
			f.setNumeroPuntosAsignar(Constants.NRO_UNO);
			f.setOidTipoExigencia(Constants.NRO_UNO);
			f.setMontoMinimoPedido(Constants.NRO_UNO);
			f.setNumeroNiveles(Constants.NRO_UNO);
			f.setIndicadorAutomaticoManual("A");
			f.setCodigoMensajeDespacho(Constants.INC_MENSAJE_DESPACHO_NODUPLA_DEFAULT);
			f.setOidTipoPremiacion(Constants.INC_TIPO_PREMIACION_POR_NIVELES);
			f.setOidTipoVenta(Constants.INC_TIPO_VENTA_VENTA_CATALOGO);
			f.setTipoEvaluacion(null);
			this.indicadorPremioCampEfectBoolean = true;
			f.setIndicadorPedidoEnPeriodo(Constants.NRO_UNO);
			f.setDescripcionPrograma(null);

			// seteamos la lista de Niveles
			ConcursoNivelPremiacion concursoNivelPremiacion = new ConcursoNivelPremiacion();
			concursoNivelPremiacion.setNumeroNivel(new Integer(1));
			concursoNivelPremiacion
					.setOidTipoPremio(Constants.INC_TIPO_PREMIO_ARTICULO);
			concursoNivelPremiacion.setIndicadorNivelSelectivo(new Integer(
					Constants.NUMERO_CERO));
			// indicadorNivelSelectivoBoolean

			ConcursoPremioArticulo concursoPremioArticulo = new ConcursoPremioArticulo();
			concursoPremioArticulo.setNumeroUnidades(new Integer(1));
			concursoNivelPremiacion
					.setConcursoPremioArticulo(concursoPremioArticulo);

			concursoNivelPremiacion
					.setListConcursoLotePremioArticulo(new ArrayList());

			List detalList = new ArrayList();
			detalList.add(concursoNivelPremiacion);
			f.setListNivelesPremiacion(detalList);
			this.incNivelesPremiacionList = detalList;
			this.indRedefinirNivelPremiacion = "S";

			ConcursoParametrosGenerales concursoParametrosGenerales = crearConcursoDefault();
			this.incDtoConcurso = concursoParametrosGenerales;
			this.incBaseCalculoList = actualizarBaseCalculo(false, f);
			// f.setTabSeleccion(Constants.INC_TAB_PARTICIPANTES);
			f.setEditableCampannaInicio(true);
			f.setIndicadorGrabarSoloPremiacion(false);
			this.dataTableModelPremiacion = new DataTableModel(
					incNivelesPremiacionList);
			this.tabIndex = "0";

			cambiarTipoProducto(f.getOidTipoProducto(), f);
			
			// setearCheckBoxBoolean(f);

		} else if (!this.accion.equals(this.ACCION_NUEVO)) {
			f.setNewRecord(false);
			Map map = (Map) this.beanRegistroSeleccionado;
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");

			if (this.accion.equals(this.ACCION_MODIFICAR)) {
				if (((String) map.get("estadoCerrado")).equals("S")) {
					throw new Exception(
							this.getResourceMessage("mantenimientoINCConfiguracionConcursoSearchForm.msg.concursoCerrado"));
				}
				if (((String) map.get("estadoCerrado")).equals("A")) {
					throw new Exception(
							this.getResourceMessage("mantenimientoINCConfiguracionConcursoSearchForm.msg.concursoAnulado"));
				}
			}

			String oidConcurso = String.valueOf(map.get("oidConcurso"));

			ConcursoParametrosGenerales concursoParametrosGenerales = service
					.getConfiguracionConcurso(oidConcurso);

			ConcursoVersion concursoVersion = concursoParametrosGenerales
					.getConcursoVersion();
			ConcursoObtencionPuntos concursoObtencionPuntos = concursoParametrosGenerales
					.getConcursoObtencionPuntos();
			ConcursoDespachoPremios concursoDespachoPremios = concursoParametrosGenerales
					.getConcursoDespachoPremios();
			ConcursoParametrosConsultoras concursoParametrosConsultoras = concursoParametrosGenerales
					.getConcursoParametrosConsultoras();
			ConcursoRequisitoPremiacion concursoRequisitoPremiacion = concursoParametrosGenerales
					.getConcursoRequisitoPremiacion();
			ConcursoParametrosPremiacion concursoParametrosPremiacion = concursoParametrosGenerales
					.getConcursoParametrosPremiacion();
			ConcursoProductos concursoProductos = concursoParametrosGenerales
					.getConcursoProductos();

			f.setOidTipoConcurso(reemplazarNulo(concursoParametrosGenerales
					.getOidClasificacionConcurso()));
			f.setNumeroConcurso(concursoParametrosGenerales.getNumeroConcurso());
			f.setNombreConcurso(concursoParametrosGenerales.getNombreConcurso());
			f.setOidTipoPrograma(reemplazarNulo(concursoParametrosGenerales
					.getOidTipoPrograma()));
			f.setOidBaseCalculo(reemplazarNulo(concursoParametrosGenerales
					.getOidBaseCalculo()));
			f.setIndicadorActivarConcurso(reemplazarNulo(concursoParametrosGenerales
					.getIndicadorActivo()));
			f.setOidPlantilla(reemplazarNulo(concursoParametrosGenerales
					.getOidPlantilla()));

			f.setCodigoPeriodoInicio(concursoParametrosGenerales
					.getCodigoPeriodoDesde());
			f.setCodigoPeriodoFin(concursoParametrosGenerales
					.getCodigoPeriodoHasta());
			f.setEstadoConcurso(concursoParametrosGenerales.getEstadoConcurso());

			f.setDescripcionPrograma(concursoParametrosGenerales
					.getDescripcionPrograma());

			f.setIndicadorMultiMarca(reemplazarNulo(concursoParametrosGenerales
					.getIndicadorMultiMarca()));
			f.setPuntosAbonar(reemplazarNulo(concursoParametrosGenerales
					.getPuntosAbonar()));

			f.setVigenciaConcurso(concursoVersion.getVigenciaConcurso());
			f.setIndicadorNoGeneraPuntaje(reemplazarNulo(concursoParametrosGenerales
					.getIndicadorNoGeneraPuntaje()));
			f.setOidTipoConcursoIVR(reemplazarNulo(concursoParametrosGenerales
					.getOidTipoConcursoIVR()));

			f.setIndicadorDevoluciones(reemplazarNulo(concursoParametrosGenerales
					.getIndicadorDevoluciones()));
			f.setIndicadorAnulaciones(reemplazarNulo(concursoParametrosGenerales
					.getIndicadorAnulaciones()));
			f.setFaltantesNoAnunciados(reemplazarNulo(concursoParametrosGenerales
					.getFaltantesNoAnunciados()));
			f.setObservaciones(concursoParametrosGenerales.getObservaciones());
			f.setIndicadorCPP(concursoParametrosGenerales.getIndicadorCPP());
			f.setOidCPP(reemplazarNulo(concursoParametrosGenerales.getOidCPP()));

			f.setCodigoCPP(concursoParametrosGenerales.getCodigoCPP());
			f.setDescripcionCPP(concursoParametrosGenerales.getDescripcionCPP());
			

			// PER-SiCC-2015-0340 fecha:30/06/2015
			f.setPorcentajeMaximoDescuentoCPP(concursoParametrosPremiacion.getPorcentajeMaximoDescuentoCPP());

			if (concursoObtencionPuntos != null) {
				f.setFactorConversion(reemplazarNulo(concursoObtencionPuntos
						.getFactorConversion()));
				f.setNumeroPuntosAsignar(reemplazarNulo(concursoObtencionPuntos
						.getNumeroPuntosAsignar()));
				f.setIndicadorPuntajeAcumulativo(concursoObtencionPuntos
						.getIndicadorPuntajeAcumulativo().toString());
				f.setIndicadorActividad(concursoObtencionPuntos
						.getIndicadorActividad().toString());
				f.setIndicadorConstancia(concursoObtencionPuntos
						.getIndicadorConstancia().toString());
				f.setIndicadorObtencionPuntos(concursoObtencionPuntos
						.getIndicadorComunicacion().toString());
				f.setCodigoMensajePuntos(concursoObtencionPuntos
						.getCodigoMensaje());
				f.setDescripcionMensajePuntos(concursoObtencionPuntos
						.getDescripcionMensaje());
				f.setNumeroPeriodosSinPedido(reemplazarNulo(concursoObtencionPuntos
						.getNumeroPeriodosSinPedido()));
				f.setIndicadorExigePasarPedidoCampanyaAnteriorCPP(StringUtils.isBlank(concursoObtencionPuntos
						.getIndicadorExigePasarPedidoCampanyaAnteriorCPP()) ? Constants.NUMERO_CERO
						: concursoObtencionPuntos
								.getIndicadorExigePasarPedidoCampanyaAnteriorCPP());
				f.setPuntosAbonarRecomendacionEfectivaCPP(StringUtils
						.isBlank(concursoObtencionPuntos
								.getPuntosAbonarRecomendacionEfectivaCPP()) ? Constants.NUMERO_CERO
						: concursoObtencionPuntos
								.getPuntosAbonarRecomendacionEfectivaCPP());
				f.setCampanyasSinPedidoParaCancelacionPuntosCPP(StringUtils.isBlank(concursoObtencionPuntos
						.getCampanyasSinPedidoParaCancelacionPuntosCPP()) ? Constants.NUMERO_CERO
						: concursoObtencionPuntos
								.getCampanyasSinPedidoParaCancelacionPuntosCPP());
				
				f.setTipoCuadre(concursoObtencionPuntos.getTipoCuadre().toString());
			}

			if (concursoDespachoPremios != null) {
				if (concursoDespachoPremios
						.getIndicadorComunicacionAutomatico().longValue() == 1) {
					f.setIndicadorAutomaticoManual(Constants.INC_INDICADOR_DESPACHO_AUTOMATICO);
					f.setCodigoMensajeDespacho(concursoDespachoPremios
							.getCodigoMensajeAutomatico());
				} else {
					f.setIndicadorAutomaticoManual(Constants.INC_INDICADOR_DESPACHO_MANUAL);
					f.setCodigoMensajeDespacho(concursoDespachoPremios
							.getCodigoMensajeManual());
				}
			}

			cargarListas(f);

			// RECUPERAMOS LA LISTA DE AMBITOS GEOGRAFICOS DEL CONCURSO
			List listConcursoAmbitoGeografico = concursoParametrosGenerales
					.getListConcursoAmbitoGeografico();
			this.incAmbitoList = listConcursoAmbitoGeografico;
			this.dataTableUnidad = new DataTableModel(this.incAmbitoList);

			// RECUPERAMOS DATOS DE PARAMETROS DE CONSULTORAS
			if (concursoParametrosConsultoras != null) {
				f.setMontoMinimoPedido(reemplazarNulo(concursoParametrosConsultoras
						.getMontoMinimoPedido()));
				f.setUnidadesMinimasPedido(reemplazarNulo(concursoParametrosConsultoras
						.getUnidadesMinimasPedido()));
				f.setNumeroMinimoPedidos(reemplazarNulo(concursoParametrosConsultoras
						.getNumeroMinimoPedidos()));
				f.setOidTipoVenta(reemplazarNulo(concursoParametrosConsultoras
						.getOidTipoVenta()));
				
				f.setIndicadorReingresoPierdePuntaje(reemplazarNulo(concursoParametrosConsultoras.getIndicadorReingresoPierdePuntaje()));

				f.setIndicadorRecomendacionEfectiva(reemplazarNulo(concursoParametrosConsultoras
						.getIndicadorRecomendacionEfectiva()));
				f.setOidTipoExigencia(reemplazarNulo(concursoParametrosGenerales
						.getOidTipoExigencia()));
				f.setCodigoPeriodoEvaluacion(reemplazarNulo(concursoParametrosConsultoras
						.getCodigoPeriodoEvaluacion()));
				f.setPeriodosEvaluacion(reemplazarNulo(concursoParametrosConsultoras
						.getPeriodosEvaluacion()));
				f.setNumeroMinimoPedidosRecomendada(reemplazarNulo(concursoParametrosConsultoras
						.getNumeroMinimoPedidosRecomendada()));
				f.setIndicadorPremioCampEfect(reemplazarNulo(concursoParametrosConsultoras
						.getIndicadorPremioCampEfect()));
				f.setGenerarPuntajeARecomendadas(reemplazarNulo(concursoParametrosConsultoras
						.getGenerarPuntajeARecomendadas()));
				f.setOidConcursoPuntajeRecomendada(reemplazarNulo(concursoParametrosConsultoras
						.getOidConcursoPuntajeRecomendada()));
				f.setNumeroConcursoPuntajeRecomendada(reemplazarNulo(concursoParametrosConsultoras
						.getNumeroConcursoPuntajeRecomendada()));
				// f.setTipoEvaluacion(reemplazarNulo(concursoParametrosConsultoras.getTipoEvaluacion()));

				if (StringUtils.equals(String
						.valueOf(concursoParametrosConsultoras
								.getTipoEvaluacion()), "")) {
					f.setIndicadorRangoPedidos(null);
					f.setIndicadorPorPedido(null);
				} else if (StringUtils.equals(String
						.valueOf(concursoParametrosConsultoras
								.getTipoEvaluacion()), "1")) {
					f.setIndicadorRangoPedidos(Constants.NUMERO_UNO);
					f.setIndicadorPorPedido(null);
				} else if (StringUtils.equals(String
						.valueOf(concursoParametrosConsultoras
								.getTipoEvaluacion()), "2")) {
					f.setIndicadorRangoPedidos(null);
					f.setIndicadorPorPedido(Constants.NUMERO_DOS);
				}
			}

			// RECUPERAMOS DATOS DE MONTO DE VENTAS DEL CONCURSO
			List listConcursoMontoVentas = concursoParametrosGenerales
					.getListConcursoMontoVentas();
			if (listConcursoMontoVentas != null
					&& listConcursoMontoVentas.size() > 0) {
				for (int i = 0; i < listConcursoMontoVentas.size(); i++) {
					ConcursoMontoVentas concursoMontoVentas = (ConcursoMontoVentas) listConcursoMontoVentas
							.get(i);

					if (concursoMontoVentas.getOidTipoMontoVenta().equals(
							Constants.INC_MONTOVENTA_MONTO_MINIMO)) {
						f.setMontoVentaMinimo(concursoMontoVentas
								.getCantidadMontoVenta().toString());
					}

					if (concursoMontoVentas.getOidTipoMontoVenta().equals(
							Constants.INC_MONTOVENTA_UNIDAD_MINIMA)) {
						f.setMontoVentaUnidad(concursoMontoVentas
								.getCantidadMontoVenta().toString());
					}
					if (concursoMontoVentas.getOidTipoMontoVenta().equals(
							Constants.INC_MONTOVENTA_MONTO_TOTAL)) {
						f.setMontoVentaTotal(concursoMontoVentas
								.getCantidadMontoVenta().toString());
					}
					if (concursoMontoVentas.getOidTipoMontoVenta().equals(
							Constants.INC_MONTOVENTA_VENTA_PROMEDIO)) {
						f.setMontoVentaPromedio(concursoMontoVentas
								.getCantidadMontoVenta().toString());
					}
				}
			}

			// RECUPERAMOS DATOS DE ESTATUS DE VENTA DEL CONCURSO
			List listConcursoEstatusVenta = concursoParametrosGenerales
					.getListConcursoEstatusVenta();
			incConcursoEstatusList = listConcursoEstatusVenta;
			dataTableEstatus = new DataTableModel(this.incConcursoEstatusList);

			// RECUPERAMOS DATOS DE CLASIFICACIONES DE PARTICIPANTES DEL
			// CONCURSO
			List listConcursoClasificacionParticipante = concursoParametrosGenerales
					.getListConcursoClasificacionParticipante();
			incConcursoClasificacionesList = listConcursoClasificacionParticipante;
			dataTableClasificacion = new DataTableModel(
					this.incConcursoClasificacionesList);

			// RECUPERAMOS DATOS DE RECOMENDADAS x PERIODO DEL CONCURSO
			List listConcursoRecomendadaPeriodo = concursoParametrosGenerales
					.getListConcursoRecomendadaPeriodo();
			incRecomendacionPeriodoList = listConcursoRecomendadaPeriodo;
			dataTableRecomendada = new DataTableModel(
					this.incRecomendacionPeriodoList);

			// RECUPERAMOS DATOS DE BONIFICACION x PERIODO DEL CONCURSO
			List listConcursoBonificacionPeriodo = concursoParametrosGenerales
					.getListConcursoBonificacionPeriodo();
			incBonificacionPeriodoList = listConcursoBonificacionPeriodo;
			dataTableBonificacion = new DataTableModel(
					this.incBonificacionPeriodoList);

			// RECUPERAMOS DATOS DE PERIODOS DE DESPACHO DEL CONCURSO
			List listConcursoPeriodoDespacho = concursoParametrosGenerales
					.getListConcursoPeriodoDespacho();
			incPeriodoDespachoList = listConcursoPeriodoDespacho;
			dataTablePeriodoDespacho = new DataTableModel(
					this.incPeriodoDespachoList);

			// RECUPERAMOS DATOS DE PUNTAJES EXIGIDOS
			List listConcursoPuntajeExigido = concursoParametrosGenerales
					.getListConcursoPuntajeExigido();
			incPuntajeExigidoList = listConcursoPuntajeExigido;
			dataTablePuntajeExigido = new DataTableModel(
					this.incPuntajeExigidoList);

			// RECUPERAMOS DATOS DE REQUISITOS DE PREMIACION
			if (concursoRequisitoPremiacion != null) {
				f.setMontoMinimoConcurso(reemplazarNulo(concursoRequisitoPremiacion
						.getMontoMinimoConcurso()));
				f.setNumeroPedidos(reemplazarNulo(concursoRequisitoPremiacion
						.getNumeroPedidos()));
				f.setCuotaIngreso(reemplazarNulo(concursoRequisitoPremiacion
						.getCuotaIngreso()));
				f.setMontoMinimoPedidoPremiacion(reemplazarNulo(concursoRequisitoPremiacion
						.getMontoMinimoPedido()));
				f.setIndicadorPedidoEnPeriodo(reemplazarNulo(concursoRequisitoPremiacion
						.getIndicadorPedidoEnPeriodo()));
				f.setCodigoTipoDespachoCPP(concursoRequisitoPremiacion
						.getIndicadorDespacho() == null ? Constants.NUMERO_CERO
						: concursoRequisitoPremiacion.getIndicadorDespacho()
								.toString());
				f.setIndicadorPriorizaWeb(StringUtils.isBlank(concursoRequisitoPremiacion.getIndicadorPriorizaWeb()) ? Constants.NUMERO_CERO
						: concursoRequisitoPremiacion.getIndicadorPriorizaWeb());
			}

			// RECUPERAMOS DATOS DE PARAMETROS DE PREMIACION
			if (concursoParametrosPremiacion != null) {
				f.setOidTipoPremiacion(reemplazarNulo(concursoParametrosPremiacion
						.getOidTipoPremiacion()));
				f.setNumeroNiveles(reemplazarNulo(concursoParametrosPremiacion
						.getNumeroNiveles()));
				f.setCodigoPeriodoDespacho(reemplazarNulo(concursoParametrosPremiacion
						.getCodigoPeriodoDespacho()));
				f.setCodigoPeriodoDespachoInicio(reemplazarNulo(concursoParametrosPremiacion
						.getCodigoPeriodoDespachoInicio()));
				f.setIndicadorPremiosElectivos(reemplazarNulo(concursoParametrosPremiacion
						.getIndicadorPremiosElectivos()));
				f.setOidTipoEleccion(reemplazarNulo(concursoParametrosPremiacion
						.getOidTipoEleccion()));

				f.setPremiosAcumulativosNiveles(reemplazarNulo(concursoParametrosPremiacion
						.getPremiosAcumulativosNiveles()));
				f.setHastaNivel(reemplazarNulo(concursoParametrosPremiacion
						.getHastaNivel()));
				f.setIndicadorNivelesRotativos(reemplazarNulo(concursoParametrosPremiacion
						.getIndicadorNivelesRotativos()));
				f.setNumeroRotaciones(reemplazarNulo(concursoParametrosPremiacion
						.getNumeroRotaciones()));
				f.setAccesoNivelSuperior(reemplazarNulo(concursoParametrosPremiacion
						.getAccesoNivelSuperior()));

				if (concursoParametrosPremiacion
						.getListConcursoNivelPremiacion() != null) {
					if (concursoParametrosPremiacion
							.getListConcursoNivelPremiacion().size() > 0) {
						f.setListNivelesPremiacion(concursoParametrosPremiacion
								.getListConcursoNivelPremiacion());
						incNivelesPremiacionList = f.getListNivelesPremiacion();
					}
				}
			}

			// RECUPERAMOS DATOS DE PARAMETROS DE PRODUCTOS
			if (concursoProductos != null) {
				if (concursoProductos.getListConcursoProductosValidos() != null)
					incProductosValidosList = concursoProductos
							.getListConcursoProductosValidos();
				if (concursoProductos.getListConcursoProductosBonificados() != null)
					incProductosBonificadosList = concursoProductos
							.getListConcursoProductosBonificados();
				if (concursoProductos.getListConcursoProductosExcluidos() != null)
					incProductosExcluidosList = concursoProductos
							.getListConcursoProductosExcluidos();
				if (concursoProductos.getListConcursoProductosExigidos() != null) {
					incProductosExigidosList = concursoProductos
							.getListConcursoProductosExigidos();

					if (concursoProductos.getListConcursoProductosExigidos()
							.size() > 0) {
						f.setIndTieneProductosExigidos(true);
					}
				}
			}

			setTabIndex("0");
			incDtoConcurso = concursoParametrosGenerales;

			/*
			 * Validando codigo de Periodo Actual con el codigo de Periodo
			 * Inicio
			 */
			f.setEditableCampannaInicio(true);
			Integer codigoPeriodoActual = new Integer(
					f.getCodigoPeriodoProceso());
			Integer codigoPeriodoInicio = new Integer(
					f.getCodigoPeriodoInicio());
			if (codigoPeriodoInicio.intValue() < codigoPeriodoActual.intValue()) {
				f.setEditableCampannaInicio(false);
			}

			/* Validando que si es que solo grabe Pestaña de Premiacion */
			f.setIndicadorGrabarSoloPremiacion(false);
			Integer codigoPeriodoFin = new Integer(f.getCodigoPeriodoFin());
			Integer oidBaseCalculo = new Integer(f.getOidBaseCalculo());

			if ("1".equals(f.getIndRestriccionMantenimiento())) {
				if (codigoPeriodoInicio.intValue() > codigoPeriodoActual
						.intValue()) {
					f.setEditable(true);
					f.setIndicadorGrabarSoloPremiacion(false);
				} else {
					f.setEditable(false);
					f.setIndicadorGrabarSoloPremiacion(true);
				}

			} else if ("2".equals(f.getIndRestriccionMantenimiento())) {
				if (codigoPeriodoInicio.intValue() >= codigoPeriodoActual
						.intValue()) {
					f.setEditable(true);
					f.setIndicadorGrabarSoloPremiacion(false);
				} else {
					f.setEditable(false);
					f.setIndicadorGrabarSoloPremiacion(true);
				}

			} else {

				// Concursos de Ventas
				if (oidBaseCalculo.intValue() != 4) {
					if (codigoPeriodoFin.intValue() >= codigoPeriodoActual
							.intValue()) {
						f.setEditable(true);
						f.setIndicadorGrabarSoloPremiacion(false);
					} else {
						f.setEditable(false);
						f.setIndicadorGrabarSoloPremiacion(true);
					}

				}

				// Concursos de Recomendacion
				if (oidBaseCalculo.intValue() == 4) {
					int maximo = 0;
					Integer numeroMinimoPedidos = new Integer(0);
					Integer numeroMinimoPedidosRecomendada = new Integer(0);
					if (StringUtils.isNotBlank(f.getNumeroMinimoPedidos())) {
						numeroMinimoPedidos = new Integer(
								f.getNumeroMinimoPedidos());
					}
					if (StringUtils.isNotBlank(f
							.getNumeroMinimoPedidosRecomendada())) {
						numeroMinimoPedidosRecomendada = new Integer(
								f.getNumeroMinimoPedidosRecomendada());
					}
					if (numeroMinimoPedidos.intValue() <= numeroMinimoPedidosRecomendada
							.intValue()) {
						maximo = numeroMinimoPedidosRecomendada.intValue();
					} else {
						maximo = numeroMinimoPedidos.intValue();
					}

					String maximoCodigoPeriodo = ajaxService
							.getCampanhaSiguientes(f.getCodigoPeriodoFin(),
									String.valueOf(maximo - 1));

					if (codigoPeriodoActual.intValue() <= Integer
							.parseInt(maximoCodigoPeriodo)) {
						f.setEditable(true);
						f.setIndicadorGrabarSoloPremiacion(false);
					} else {
						f.setEditable(false);
						f.setIndicadorGrabarSoloPremiacion(true);
					}
				}

				if ("3".equals(f.getIndRestriccionMantenimiento())) {
					if (codigoPeriodoInicio.intValue() <= codigoPeriodoActual
							.intValue()) {
						f.setIndicadorGrabarParametrosGenerales(false);
					}
				}
			}
			
			//validacion que setea tab premiacion por default
			if(f.isIndicadorGrabarSoloPremiacion()) 
				setTabIndex("2");


			f.setEditableParametrosGenerales(false);
			if (!"0".equals(f.getIndRestriccionMantenimiento()))
				f.setEditableParametrosGenerales(true);

			if (concursoParametrosGenerales.getIndicadorComunicacion() == null)
				f.setIndicadorComunicacion(String.valueOf(Constants.NUMERO_UNO));
			else
				f.setIndicadorComunicacion(String
						.valueOf(concursoParametrosGenerales
								.getIndicadorComunicacion()));

			String tipoLista = Constants.NUMERO_DOS;
			if (StringUtils.equals(f.getOidBaseCalculo(),
					Constants.NUMERO_CUATRO)
					|| StringUtils.equals(f.getOidTipoConcurso(),
							Constants.NUMERO_DOS)) {
				tipoLista = Constants.NUMERO_UNO;
			}
			LabelValue[] listaTipoDespacho = ajaxService
					.getTiposDespachoCPP(tipoLista);
			incConcursoProgramaPuntosTipoDespachoList = listaTipoDespacho;
			dataTableModelPremiacion = new DataTableModel(
					incNivelesPremiacionList);
			log.debug("enviando para editar");
			setearCheckBoxBoolean(f);
			concursoCambiarEditar(f.getOidTipoConcurso(), f);
			cambiarTipoProducto(f.getOidTipoProducto(), f);
			cambiarBloqueProducto(f.getCodigoBloqueProducto(), f);

			// SI DESCRIPCION PROGRAMA ES NULL APLICA ESTE METODO
			if (StringUtils.isBlank(f.getDescripcionPrograma()))
				f.setDescripcionPrograma(ajax.getDescripcionProgramaAdicionar(f
						.getOidTipoConcurso()));

			dataTableProductosValidos = new DataTableModel(
					this.incProductosValidosList);
			dataTableProductosBonificacion = new DataTableModel(
					this.incProductosBonificadosList);
			dataTableProductosExcluidos = new DataTableModel(
					this.incProductosExcluidosList);
			dataTableProductosExigidos = new DataTableModel(
					this.incProductosExigidosList);

			if (this.accion.equals(ACCION_CONSULTAR)) {
				mostrarBotonSave = false;
				consultar = true;
				codigoPeriodoDisabled = true;
			}

			 Map obj=(Map)this.beanRegistroSeleccionado;
			 String estado=obj.get("estado")==null?"":obj.get("estado").toString();
			 
			if (this.accion.equals(ACCION_MODIFICAR) && estado.equalsIgnoreCase("Inactivo") ) 
				this.indicadorGrabarParametrosGenerales = true;
				else
					this.indicadorGrabarParametrosGenerales = false;
		}

		return f;
	}

	private String reemplazarNulo(Object obj) {
		if (obj == null)
			return "";
		else
			return String.valueOf(obj);
	}

	private ConcursoParametrosGenerales crearConcursoDefault() {
		ConcursoParametrosGenerales concursoParametrosGenerales = new ConcursoParametrosGenerales();
		concursoParametrosGenerales.setVersion(new Integer(1));
		concursoParametrosGenerales.setIndicadorRanking(new Integer(0));
		concursoParametrosGenerales.setIndicadorDuplaCyzone(new Integer(0));
		concursoParametrosGenerales.setCodigoPais(codigoPais);
		concursoParametrosGenerales
				.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		concursoParametrosGenerales
				.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		concursoParametrosGenerales
				.setCodigoAcceso(Constants.CODIGO_ACCESO_DEFAULT);
		concursoParametrosGenerales.setExpresionPuntaje("puntos");
		concursoParametrosGenerales.setDirigidoA(new Long(1));
		concursoParametrosGenerales.setIndicadorNoGeneraPuntaje(new Integer(0));

		concursoParametrosGenerales.setIndicadorDevoluciones(new Integer(1));
		concursoParametrosGenerales.setIndicadorAnulaciones(new Integer(1));
		concursoParametrosGenerales.setFaltantesNoAnunciados(new Integer(1));
		concursoParametrosGenerales.setOidTipoExigencia(new Long(1));

		if (concursoParametrosGenerales.getConcursoObtencionPuntos() == null)
			concursoParametrosGenerales
					.setConcursoObtencionPuntos(new ConcursoObtencionPuntos());
		if (concursoParametrosGenerales.getConcursoDespachoPremios() == null)
			concursoParametrosGenerales
					.setConcursoDespachoPremios(new ConcursoDespachoPremios());
		if (concursoParametrosGenerales.getConcursoParametrosConsultoras() == null)
			concursoParametrosGenerales
					.setConcursoParametrosConsultoras(new ConcursoParametrosConsultoras());
		if (concursoParametrosGenerales.getConcursoRequisitoPremiacion() == null) {
			concursoParametrosGenerales
					.setConcursoRequisitoPremiacion(new ConcursoRequisitoPremiacion());
			concursoParametrosGenerales.getConcursoRequisitoPremiacion()
					.setIndicadorPagoATiempo(new Integer(0));
		}
		if (concursoParametrosGenerales.getConcursoParametrosPremiacion() == null) {
			concursoParametrosGenerales
					.setConcursoParametrosPremiacion(new ConcursoParametrosPremiacion());
			concursoParametrosGenerales.getConcursoParametrosPremiacion()
					.setIndicadorComunicacion(new Integer(0));
		}

		ConcursoObtencionPuntos concursoObtencionPuntos = concursoParametrosGenerales
				.getConcursoObtencionPuntos();
		ConcursoParametrosConsultoras concursoParametrosConsultoras = concursoParametrosGenerales
				.getConcursoParametrosConsultoras();
		ConcursoParametrosPremiacion concursoParametrosPremiacion = concursoParametrosGenerales
				.getConcursoParametrosPremiacion();

		concursoObtencionPuntos.setFactorConversion(new BigDecimal("1"));
		concursoObtencionPuntos.setNumeroPuntosAsignar(new Long(1));
		concursoObtencionPuntos.setIndicadorPuntajeAcumulativo(new Integer(1));
		concursoObtencionPuntos.setIndicadorActividad(new Integer(0));
		concursoObtencionPuntos.setIndicadorConstancia(new Integer(0));

		concursoParametrosPremiacion.setOidTipoPremiacion(new Long(
				Constants.INC_TIPO_PREMIACION_POR_NIVELES));
		concursoParametrosPremiacion.setNumeroNiveles(new Integer(1));

		concursoParametrosConsultoras.setMontoMinimoPedido(new BigDecimal("1"));

		// PER-SiCC-2015-0340 fecha 30/06/015
		concursoParametrosPremiacion.setPorcentajeMaximoDescuentoCPP(String.valueOf(Constants.NUMERO_CIEN));

		return concursoParametrosGenerales;
	}

	private void cargarListas(MantenimientoINCConfiguracionConcursoForm f) {
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);

		this.siccSubGerenciaList = reporteService.getListaGenerico(
				"getSubGerenciasByPaisMarcaCanal", criteria);

		List listClasificacionesConcurso = service.getClasificacionesConcurso();
		this.incClasificacionConcursoList = listClasificacionesConcurso;

		// descripcion concurso
		if (!this.accion.equals(this.ACCION_NUEVO)) {
			for (Object obj : incClasificacionConcursoList) {
				Map aux = (Map) obj;
				String codigo = aux.get("oid") == null ? "" : aux.get("oid")
						.toString();
				if (f.getOidTipoConcurso().equals(codigo)) {
					this.descripcionConcurso = aux.get("descripcion") == null ? ""
							: aux.get("descripcion").toString();
					break;
				}
			}
		}

		List listTiposPrograma = service.getTiposPrograma();
		this.incTipoProgramaList = listTiposPrograma;

		this.incDirigidosList = service.getDirigidos();

		this.incTipoVentaList = service.getTipoVenta();
		this.incTipoExigenciaList = service.getTipoExigencia();
		this.incConcursoRecomendadasList = service.getConcursosRecomendadas();

		this.incEstatusClienteList = service.getEstatusCliente();
		this.incClasificacionesParticipantesList = service
				.getClasificacionesParticipante();
		this.incTiposPremiacionList = service.getTiposPremiacion();
		this.incTiposEleccionList = service.getTiposEleccion();
		this.incCentrosServicioList = service.getCentrosServicio();
		this.incIndicadorTipoRecomendacionList = service
				.getIndicadoresTipoRecomendada();

		List listBloqueProductos = new ArrayList();
		LabelValue baseNegocio = new LabelValue("NEGOCIO",
				Constants.INC_BLOQUE_NEGOCIO);
		LabelValue baseOferta = new LabelValue("OFERTA",
				Constants.INC_BLOQUE_OFERTA);
		LabelValue baseProducto = new LabelValue("PRODUCTO",
				Constants.INC_BLOQUE_PRODUCTO);
		LabelValue baseCUV = new LabelValue("CUV", Constants.INC_BLOQUE_CUV);
		listBloqueProductos.add(baseNegocio);
		listBloqueProductos.add(baseOferta);
		listBloqueProductos.add(baseProducto);
		listBloqueProductos.add(baseCUV);
		this.incBloqueProductoList = listBloqueProductos;

		this.incTipoProductoList = service.getTiposProducto();
		this.incTipoOfertaList = service.getTiposOferta();
		this.incTipoAgrupacionList = service.getTiposAgrupacion();
		this.incUnidadNegocioList = service.getUnidadesNegocio();
		this.incNegocioList = service.getNegocios();
		this.incCicloVidaList = service.getCiclosVida();
		this.incMarcaProductoList = service.getMarcaProductos();

		this.incGenericoList = new ArrayList();
		this.incSuperGenericoList = new ArrayList();
		this.incProductosValidosList = new ArrayList();
		this.incProductosBonificadosList = new ArrayList();
		this.incProductosExcluidosList = new ArrayList();
		this.incProductosExigidosList = new ArrayList();

		for (int i = 0; i < listClasificacionesConcurso.size(); i++) {
			Map mapClasificacion = (Map) listClasificacionesConcurso.get(i);
			String codigo = (String) mapClasificacion.get("codigo");
			String oid = (String) mapClasificacion.get("oid");

			if (codigo.equals(Constants.INC_TIPO_CONCURSO_DUPLA_CYZONE)) {
				f.setOidTipoConcursoDuplaCyzone(oid);
			}
			if (codigo.equals(Constants.INC_TIPO_CONCURSO_RECOMENDACION)) {
				f.setOidTipoConcursoRecomendacion(oid);
			}
			if (codigo.equals(Constants.INC_TIPO_CONCURSO_PARATI_PARAMI)) {
				f.setOidTipoConcursoParatiParaMi(oid);
			}
			if (codigo.equals(Constants.INC_TIPO_CONCURSO_CONSTANCIA)) {
				f.setOidTipoConcursoConstancia(oid);
			}
			if (codigo.equals(Constants.INC_TIPO_CONCURSO_BOLSA_PREMIO)) {
				f.setOidTipoConcursoBolsaPremio(oid);
			}
			if (codigo.equals(Constants.INC_TIPO_CONCURSO_RECONOCIMIENTO)) {
				f.setOidTipoConcursoReconocimiento(oid);
			}
			if (codigo.equals(Constants.INC_TIPO_CONCURSO_FICTICIO)) {
				f.setOidTipoConcursoFicticio(oid);
			}
		}

		for (int i = 0; i < listTiposPrograma.size(); i++) {
			Map mapTipoPrograma = (Map) listTiposPrograma.get(i);
			String codigo = (String) mapTipoPrograma.get("codigo");
			String oid = (String) mapTipoPrograma.get("oid");

			if (codigo.equals(Constants.INC_TIPO_PROGRAMA_BONIFICACION)) {
				f.setOidTipoProgramaBonificacion(oid);
			}
			if (codigo.equals(Constants.INC_TIPO_PROGRAMA_RETIRO_BIENES)) {
				f.setOidTipoProgramaRetiro(oid);
			}
			if (codigo.equals(Constants.INC_TIPO_PROGRAMA_CONCURSO)) {
				f.setOidTipoProgramaConcurso(oid);
			}
		}

		f.setOidTipoPremiacionBolsaPremios(Constants.INC_TIPO_PREMIACION_BOLSA_PREMIOS);

		f.setOidBaseCalculoRecomedadas(Constants.INC_BASE_CALCULO_RECOMENDADAS
				.toString());

		f.setCodigoMensajeDespachoDuplaCyzone(Constants.INC_MENSAJE_DESPACHO_DUPLA_DEFAULT);
		f.setCodigoMensajeDespachoNoDuplaCyzone(Constants.INC_MENSAJE_DESPACHO_NODUPLA_DEFAULT);
		f.setCodigoMensajePuntosBonificacion(Constants.INC_MENSAJE_PUNTOS_BONIFICACION_DEFAULT);
		f.setCodigoMensajePuntosRecomendacion(Constants.INC_MENSAJE_PUNTOS_RECOMENDACION_DEFAULT);
		f.setCodigoMensajePuntosDuplaCyzone(Constants.INC_MENSAJE_PUNTOS_DUPLA_DEFAULT);
		f.setCodigoMensajePuntosConcurso(Constants.INC_MENSAJE_PUNTOS_CONCURSO_DEFAULT);

		this.incAmbitoList = new ArrayList();
		this.incConcursoEstatusList = new ArrayList();
		this.incConcursoClasificacionesList = new ArrayList();
		f.setListNivelesPremiacion(new ArrayList());
		this.incArticulosLoteList = new ArrayList();
		this.incNivelesPremiacionList = new ArrayList();
		this.incRecomendacionPeriodoList = new ArrayList();
		this.incBonificacionPeriodoList = new ArrayList();
		this.incPeriodoDespachoList = new ArrayList();
		this.incPuntajeExigidoList = new ArrayList();
		this.incPuntajeExigidoListTempo = new ArrayList();

		indRedefinirNivelPremiacion = "N";
		f.setIndActualizarAmbitoGeografico(false);
		f.setIndRedefinirProductosValidos(false);
		f.setIndRedefinirProductosBonificados(false);
		f.setIndRedefinirProductosExcluidos(false);
		f.setIndRedefinirProductosExigidos(false);

		f.setOidTipoProducto(Constants.INC_OID_PRODUCTOS_VALIDOS);
		f.setCodigoBloqueProducto(Constants.INC_BLOQUE_NEGOCIO);

		// cargar el periodo de proceso
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																// Campanha
																// Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																	// Campanha
																	// activa q
																	// se
																	// procesa
																	// actualmente

		MantenimientoOCRPedidoControlFacturacionService serviceAux = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceAux
				.getControlFacturacionById(criteria);
		f.setCodigoPeriodoProceso(controlFacturacion.getCodigoPeriodo());

		// Validar Restriccion Mantenimiento
		GenericoService genericoService = (GenericoService) getBean("genericoService");
		ParametroPais paramPais = new ParametroPais();

		paramPais.setCodigoPais(codigoPais);
		paramPais.setCodigoSistema("INC");
		paramPais.setNombreParametro("indRestriccionMantenimiento");
		paramPais.setIndicadorActivo(Constants.NUMERO_UNO);

		List lstParametros = genericoService.getParametrosPais(paramPais);

		if (lstParametros != null && lstParametros.size() > 0) {
			paramPais = (ParametroPais) lstParametros.get(0);

			if ("1".equals(paramPais.getValorParametro()))
				f.setIndRestriccionMantenimiento("1");
			else if ("2".equals(paramPais.getValorParametro()))
				f.setIndRestriccionMantenimiento("2");
			else if ("3".equals(paramPais.getValorParametro()))
				f.setIndRestriccionMantenimiento("3");
			else
				f.setIndRestriccionMantenimiento("0");

		} else {

			f.setIndRestriccionMantenimiento("0");
		}
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		codigoPais = pais.getCodigo();
		usuario = this.mPantallaPrincipalBean.getCurrentUser();
		service = (MantenimientoINCConfiguracionConcursoService) getBean("spusicc.mantenimientoINCConfiguracionConcursoService");

		// seteamos el codigo del pais
		MantenimientoINCConfiguracionConcursoSearchForm f = (MantenimientoINCConfiguracionConcursoSearchForm) this.formBusqueda;
		f.setCodigoPais(pais.getCodigo());

		// combo concursos
		List listClasificacionesConcurso = service.getClasificacionesConcurso();

		// combo tipo proceso
		this.incClasificacionConcursoList = listClasificacionesConcurso;
		this.incEstadoList = service.getEstados();
		this.incVigenciaList = service.getVigencias();
		this.mostrarBotonEliminar = false;
		salirGrabarPantallaPadre = true;
		
		this.invocarFindLuegoGrabar = true;
		this.activarHotkeyEstandar = false;
	}

	public void cambiarTipoConcurso(ValueChangeEvent val) {
		MantenimientoINCConfiguracionConcursoForm f = (MantenimientoINCConfiguracionConcursoForm) this.formMantenimiento;
		String oidTipoConcurso = (String) val.getNewValue();
		cambiarConcurso(oidTipoConcurso, f);
	}

	public void cambiarConcurso(String oidTipoConcurso,
			MantenimientoINCConfiguracionConcursoForm f) {
		AjaxService ajax = (AjaxService) getBean("ajaxService");

		// Cambia la Lista de Despacho

		// Cambia la descripcion del programa
		String descripcionPrograma = ajax
				.getDescripcionProgramaAdicionar(oidTipoConcurso);
		f.setDescripcionPrograma(descripcionPrograma);
		// Cambiar Datos Tipo Concurso
		String[] listaTipoConcurso;
		String datos = ajax.getDatosTipoConcurso(oidTipoConcurso) != null ? ajax
				.getDatosTipoConcurso(oidTipoConcurso) : "";
		listaTipoConcurso = datos != null ? datos.split("__") : null;
		if (listaTipoConcurso != null && listaTipoConcurso.length > 0) {
			if (listaTipoConcurso.length<=3)
			{
				 f.setOidBaseCalculo(listaTipoConcurso[0]);
					f.setOidTipoPrograma(listaTipoConcurso[1]);
					f.setOidPlantilla(listaTipoConcurso[2]);
				
			}else
			{
				f.setOidBaseCalculo(listaTipoConcurso[0]);
				f.setOidTipoPrograma(listaTipoConcurso[1]);
				f.setOidPlantilla(listaTipoConcurso[2]);
				f.setOidTipoConcursoIVR(listaTipoConcurso[3]);
			}
           
			
		}

		// habilitar/deshabilitar campos
		if (oidTipoConcurso.equals(f.getOidTipoConcursoRecomendacion())) {
			// Setea campo codigo codigoPeriodoEvaluacion
			cambiarPeriodoEvaluacion(oidTipoConcurso);

			indicadorPuntajeAcumulativoBoolean = false;
			habilitarMontoVenta = false;
			f.setMontoVentaMinimo("1");
			f.setMontoVentaUnidad("1");
			f.setMontoVentaTotal("1");
			f.setMontoVentaPromedio("1");
			f.setNumeroMinimoPedidosRecomendada("2");
			f.setNumeroMinimoPedidos("2");
			f.setPeriodosEvaluacion("2");
			setIndicadorRangoPedidosDisabled(false);
			indicadorPorPedidoDisabled = false;
			numeroMinimoPedidosRecomendadaDisabled = false;
			indicadorPremioCampEfectDisabled = false;
			generarPuntajeARecomendadasDisabled = false;
			multiMarcaDisabled = true;
			indicadorMultiMarcaBoolean = false;
			f.setPuntosAbonar(null);
			oidBaseCalculoDisabled = true;
			this.incBaseCalculoList = actualizarBaseCalculo(true, f);
		} else {
			if (oidTipoConcurso.equals(f.getOidTipoConcursoBolsaPremio())) {
				indicadorPuntajeAcumulativoBoolean = true;
				habilitarMontoVenta = true;
				// f.setMontoVentaMinimo(null);
				// f.setMontoVentaUnidad(null);
				// f.setMontoVentaTotal(null);
				// f.setMontoVentaPromedio(null);
				f.setNumeroMinimoPedidos(null);
				setIndicadorRangoPedidosDisabled(true);
				indicadorPorPedidoDisabled = true;
				numeroMinimoPedidosRecomendadaDisabled = true;
				indicadorPremioCampEfectDisabled = true;
				generarPuntajeARecomendadasDisabled = true;
				multiMarcaDisabled = false;
				f.setOidBaseCalculo(null);
				oidBaseCalculoDisabled = false;
				this.incBaseCalculoList = actualizarBaseCalculo(false, f);

				ConcursoNivelPremiacion objNivelPremiacion = f
						.getListNivelesPremiacion() != null
						&& f.getListNivelesPremiacion().size() > 0 ? (ConcursoNivelPremiacion) f
						.getListNivelesPremiacion().get(0) : null;
				Integer indicadorNivelSelectivo = objNivelPremiacion != null ? objNivelPremiacion
						.getIndicadorNivelSelectivo() : -1;
				if (indicadorNivelSelectivo == 1) {
					f.setIndicadorPremiosElectivos("1");
					f.setOidTipoEleccion("1");
				} else {

					f.setIndicadorPremiosElectivos("0");
					f.setOidTipoEleccion("");
				}

			} else {

				indicadorPuntajeAcumulativoBoolean = true;
				habilitarMontoVenta = true;
				// f.setMontoVentaMinimo(null);
				// f.setMontoVentaUnidad(null);
				// f.setMontoVentaTotal(null);
				// f.setMontoVentaPromedio(null);
				f.setNumeroMinimoPedidos(null);
				setIndicadorRangoPedidosDisabled(true);
				indicadorPorPedidoDisabled = true;
				numeroMinimoPedidosRecomendadaDisabled = true;
				indicadorPremioCampEfectDisabled = true;
				generarPuntajeARecomendadasDisabled = true;
				multiMarcaDisabled = false;
				f.setOidBaseCalculo(null);
				oidBaseCalculoDisabled = false;
				f.setIndicadorPremiosElectivos("0");

				f.setOidTipoEleccion("");
				this.incBaseCalculoList = actualizarBaseCalculo(false, f);
			}

		}

		cargarTiposDespachoCPP(oidTipoConcurso, f);
	}

	public void cambiarPeriodoEvaluacion(String oidTipoConcurso) {
		MantenimientoINCConfiguracionConcursoForm f = (MantenimientoINCConfiguracionConcursoForm) this.formMantenimiento;
		AjaxService ajax = (AjaxService) getBean("ajaxService");

		if (oidTipoConcurso.equals(f.getOidTipoConcursoRecomendacion())
				&& StringUtils.isNotBlank(f.getCodigoPeriodoInicio())) {
			if (StringUtils.isBlank(f.getNumeroMinimoPedidosRecomendada()))
				f.setNumeroMinimoPedidosRecomendada("0");
			if (StringUtils.isBlank(f.getNumeroMinimoPedidos()))
				f.setNumeroMinimoPedidos("0");

			int maximo = Integer
					.parseInt(f.getNumeroMinimoPedidosRecomendada());

			if (maximo < Integer.parseInt(f.getNumeroMinimoPedidos())) {
				maximo = Integer.parseInt(f.getNumeroMinimoPedidos());
			}

			String resultado = ajax.getCampanhaSiguientes(
					f.getCodigoPeriodoInicio(), String.valueOf(maximo - 1));
			f.setCodigoPeriodoEvaluacion(resultado);

			cambiarNroEvaluaciones(oidTipoConcurso);
		} else {
			f.setCodigoPeriodoEvaluacion("");
			f.setPeriodosEvaluacion("");
		}
	}

	public void cambiarPeriodoEvaluacionPedidos(String oidTipoConcurso) {
		MantenimientoINCConfiguracionConcursoForm f = (MantenimientoINCConfiguracionConcursoForm) this.formMantenimiento;
		AjaxService ajax = (AjaxService) getBean("ajaxService");

		if (StringUtils.isNotBlank(f.getOidTipoConcurso())
				&& StringUtils.isNotBlank(f.getOidTipoConcursoRecomendacion())
				&& f.getOidTipoConcurso().equals(
						f.getOidTipoConcursoRecomendacion())
				&& StringUtils.isNotBlank(f.getCodigoPeriodoInicio())) {
			if (StringUtils.isBlank(f.getNumeroMinimoPedidosRecomendada()))
				f.setNumeroMinimoPedidosRecomendada("0");
			if (StringUtils.isBlank(f.getNumeroMinimoPedidos()))
				f.setNumeroMinimoPedidos("0");

			int maximo = Integer
					.parseInt(f.getNumeroMinimoPedidosRecomendada());

			if (maximo < Integer.parseInt(f.getNumeroMinimoPedidos())) {
				maximo = Integer.parseInt(f.getNumeroMinimoPedidos());
			}

			String resultado = ajax.getCampanhaSiguientes(
					f.getCodigoPeriodoInicio(), String.valueOf(maximo - 1));
			f.setCodigoPeriodoEvaluacion(resultado);

			cambiarNroEvaluaciones(f.getOidTipoConcurso());
		} else {
			f.setCodigoPeriodoEvaluacion("");
			f.setPeriodosEvaluacion("");
		}
	}

	private void cambiarNroEvaluaciones(String oidTipoConcurso) {
		MantenimientoINCConfiguracionConcursoForm f = (MantenimientoINCConfiguracionConcursoForm) this.formMantenimiento;
		AjaxService ajax = (AjaxService) getBean("ajaxService");

		if (oidTipoConcurso.equals(f.getOidTipoConcursoRecomendacion())
				&& StringUtils.isNotBlank(f.getCodigoPeriodoInicio())
				&& StringUtils.isNotBlank(f.getCodigoPeriodoFin())) {
			String resultado = ajax.getDiferenciaPeriodos(f.getCodigoPais(),
					f.getCodigoPeriodoInicio(), f.getCodigoPeriodoFin());
			f.setPeriodosEvaluacion(resultado);
		} else {
			f.setPeriodosEvaluacion("");
		}
	}

	public void concursoCambiarEditar(String oidTipoConcurso,
			MantenimientoINCConfiguracionConcursoForm f) {
		AjaxService ajax = (AjaxService) getBean("ajaxService");

		// habilitar/deshabilitar campos
		if (oidTipoConcurso.equals(f.getOidTipoConcursoRecomendacion())) {
			indicadorPuntajeAcumulativoBoolean = false;
			habilitarMontoVenta = false;
			setIndicadorRangoPedidosDisabled(false);
			indicadorPorPedidoDisabled = false;
			numeroMinimoPedidosRecomendadaDisabled = false;
			indicadorPremioCampEfectDisabled = false;
			generarPuntajeARecomendadasDisabled = false;
			multiMarcaDisabled = true;
			indicadorMultiMarcaBoolean = false;
			f.setPuntosAbonar(null);
			oidBaseCalculoDisabled = true;
			this.incBaseCalculoList = actualizarBaseCalculo(true, f);

		} else {
			indicadorPuntajeAcumulativoBoolean = true;
			habilitarMontoVenta = true;
			setIndicadorRangoPedidosDisabled(true);
			indicadorPorPedidoDisabled = true;
			numeroMinimoPedidosRecomendadaDisabled = true;
			indicadorPremioCampEfectDisabled = true;
			generarPuntajeARecomendadasDisabled = true;
			multiMarcaDisabled = false;
			oidBaseCalculoDisabled = false;
			this.incBaseCalculoList = actualizarBaseCalculo(false, f);
		}

		// TAB PREMIACION - PARAMETROS
		if (premiosAcumulativosNivelesBoolean)
			hastaNivelDisabled = false;
		if (indicadorNivelesRotativosBoolean)
			numeroRotacionesDisabled = false;
	}

	public List actualizarBaseCalculo(boolean esRecomendacion,
			MantenimientoINCConfiguracionConcursoForm f) {
		this.incBaseCalculoList = service.getBaseCalculo();
		List lista = new ArrayList();
		if (!esRecomendacion) {
			for (int i = 0; i < incBaseCalculoList.size(); i++) {
				Base base = (Base) incBaseCalculoList.get(i);
				if (base.getCodigo().equals("1")
						|| base.getCodigo().equals("2"))
					lista.add(base);
			}
		} else {
			for (int i = 0; i < incBaseCalculoList.size(); i++) {
				Base base = (Base) incBaseCalculoList.get(i);
				if (base.getCodigo().equals("4"))
					lista.add(base);
			}
			f.setOidBaseCalculo("4");
		}
		return lista;

	}

	public void habilitarPuntosAbonar(ValueChangeEvent val) {
		MantenimientoINCConfiguracionConcursoForm f = (MantenimientoINCConfiguracionConcursoForm) this.formMantenimiento;
		String valor = val.getNewValue().toString();
		if (valor.equals("false"))
			f.setPuntosAbonar(null);

	}

	public void cargarTiposDespachoCPP(String oidTipoConcurso,
			MantenimientoINCConfiguracionConcursoForm f) {
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		String oidBaseCalculo = f.getOidBaseCalculo();
		String oidTipoPremiacion = f.getOidTipoPremiacion();
		String tipoLista = Constants.NUMERO_DOS;
		String oidTipoConcursoReconocimiento = Constants.NUMERO_SEIS;

		if (oidBaseCalculo == null)
			oidBaseCalculo = "";
		if (oidBaseCalculo.equals("4") || oidTipoPremiacion.equals("1"))
			tipoLista = Constants.NUMERO_UNO;

		this.incConcursoProgramaPuntosTipoDespachoList = ajax
				.getTiposDespachoCPP(tipoLista);

		if (oidTipoConcurso.equals(oidTipoConcursoReconocimiento)) {
			f.setCodigoTipoDespachoCPP(Constants.INC_TIPO_DESPACHO_CPP_SIN_DESPACHO_PREMIO_CODIGO);
		} else {
			f.setCodigoTipoDespachoCPP(Constants.INC_TIPO_DESPACHO_CPP_PREMIACION_UNICA_CODIGO);
		}

	}

	public void habilitarCodigoMensaje(ValueChangeEvent val) {
		MantenimientoINCConfiguracionConcursoForm f = (MantenimientoINCConfiguracionConcursoForm) this.formMantenimiento;
		String valor = val.getNewValue().toString();
		if (valor.equals("true"))
			codigoMensajePuntosDisabled = false;
		else {
			codigoMensajePuntosDisabled = true;
			f.setCodigoMensajePuntos(null);
			f.setDescripcionMensajePuntos(null);
		}
	}

	public void habilitarCamposProgramaPuntos(ValueChangeEvent val) {
		String valor = val.getNewValue().toString();
		MantenimientoINCConfiguracionConcursoForm f = (MantenimientoINCConfiguracionConcursoForm) this.formMantenimiento;

		if (valor.equals("true")) {
			codigoCPPDisabled = true;
			//indicadorExigePasarPedidoCampanyaAnteriorCPPBoolean = true;
			indicadorPriorizaWebBoolean = true;
			campanyasSinPedidoParaCancelacionPuntosCPPDisabled = false;
			puntosAbonarRecomendacionEfectivaCPPDisabled = true;
			f.setCodigoCPP("");
			f.setDescripcionCPP("");

		} else {
			codigoCPPDisabled = false;
			//indicadorExigePasarPedidoCampanyaAnteriorCPPBoolean = false;
			indicadorPriorizaWebBoolean = false;
			campanyasSinPedidoParaCancelacionPuntosCPPDisabled = true;
		}
	}

	public void habilitarCamposPorPedido(ValueChangeEvent val) {
		String valor = val.getNewValue().toString();
		if (valor.equals("true")) {
			indicadorRangoPedidosBoolean = true;
			indicadorPorPedidoBoolean = false;
		} else
			indicadorRangoPedidosBoolean = false;
	}

	public void cambiarMontoMinimo(ValueChangeEvent val) {
		MantenimientoINCConfiguracionConcursoForm f = (MantenimientoINCConfiguracionConcursoForm) this.formMantenimiento;
		String valor = val.getNewValue().toString();
		if (valor.equals("true")) {
			montoMinimoPedidoPremiacionDisabled = false;
			f.setMontoMinimoPedidoPremiacion("1");
		} else {
			montoMinimoPedidoPremiacionDisabled = true;
			f.setMontoMinimoPedidoPremiacion("0");
		}
	}

	public void cambiarHastaNivel(ValueChangeEvent val) {
		MantenimientoINCConfiguracionConcursoForm f = (MantenimientoINCConfiguracionConcursoForm) this.formMantenimiento;
		String valor = val.getNewValue().toString();
		if (valor.equals("true")) {
			hastaNivelDisabled = false;
			f.setHastaNivel(f.getNumeroNiveles());
		} else {
			hastaNivelDisabled = true;
			f.setHastaNivel(null);
		}
	}

	public void cambiarNumeroRotaciones(ValueChangeEvent val) {
		MantenimientoINCConfiguracionConcursoForm f = (MantenimientoINCConfiguracionConcursoForm) this.formMantenimiento;
		String valor = val.getNewValue().toString();
		if (valor.equals("true")) {
			numeroRotacionesDisabled = false;
			if (f.getOidTipoPremiacion().equals("2"))
				f.setNumeroRotaciones("1");
			else
				f.setNumeroRotaciones(null);
		} else {
			numeroRotacionesDisabled = true;
			f.setNumeroRotaciones(null);
		}
	}

	public void definirNiveles(ActionEvent event) throws Exception {

		try {

			MantenimientoINCConfiguracionConcursoForm f = (MantenimientoINCConfiguracionConcursoForm) this.formMantenimiento;

			List detalList = this.incNivelesPremiacionList;
			if (detalList == null)
				detalList = new ArrayList();

			int numeroNiveles = Integer.parseInt(f.getNumeroNiveles());
			int totalNiveles = detalList.size();

			if (totalNiveles <= numeroNiveles) {
				for (int i = totalNiveles; i < numeroNiveles; i++) {
					ConcursoNivelPremiacion concursoNivelPremiacion = new ConcursoNivelPremiacion();
					concursoNivelPremiacion.setNumeroNivel(i + 1);
					concursoNivelPremiacion
							.setOidTipoPremio(Constants.INC_TIPO_PREMIO_ARTICULO);
					if (StringUtils.equals(f.getOidTipoConcurso(),
							f.getOidTipoConcursoBolsaPremio()))
						concursoNivelPremiacion
								.setIndicadorNivelSelectivo(new Integer(
										Constants.NUMERO_UNO));
					else
						concursoNivelPremiacion
								.setIndicadorNivelSelectivo(new Integer(
										Constants.NUMERO_CERO));

					ConcursoPremioArticulo concursoPremioArticulo = new ConcursoPremioArticulo();
					concursoPremioArticulo.setNumeroUnidades(new Integer(1));
					concursoNivelPremiacion
							.setConcursoPremioArticulo(concursoPremioArticulo);

					concursoNivelPremiacion
							.setListConcursoLotePremioArticulo(new ArrayList());

					detalList.add(concursoNivelPremiacion);
				}
			} else {

				List detalList1 = new ArrayList();

				log.debug("detalList.size() = " + detalList.size());

				for (int i = 0; i < detalList.size(); i++) {
					log.debug("i = " + i);

					if ((i + 1) <= numeroNiveles) {
						ConcursoNivelPremiacion concursoNivelPremiacion = (ConcursoNivelPremiacion) detalList
								.get(i);
						detalList1.add(concursoNivelPremiacion);
					} else
						break;
				}

				log.debug("detalList1.size() = " + detalList1.size());

				detalList = detalList1;

			}

			/* Colocando el check de Indicador Selectivo */
			for (int i = 0; i < detalList.size(); i++) {
				ConcursoNivelPremiacion concursoNivelPremiacion = (ConcursoNivelPremiacion) detalList
						.get(i);
				if (StringUtils.equals(f.getOidTipoPremiacion(),
						f.getOidTipoPremiacionBolsaPremios()))
					concursoNivelPremiacion
							.setIndicadorNivelSelectivo(new Integer(
									Constants.NUMERO_UNO));
				else
					concursoNivelPremiacion
							.setIndicadorNivelSelectivo(new Integer(
									Constants.NUMERO_CERO));
				detalList.set(i, concursoNivelPremiacion);
			}

			f.setListNivelesPremiacion(detalList);
			incNivelesPremiacionList = detalList;
			dataTableModelPremiacion = new DataTableModel(
					incNivelesPremiacionList);
			indRedefinirNivelPremiacion = "S";

		} catch (Exception ex) {
			log.error("ERRORR!!!");

			log.error(ex.getMessage(), ex);
		}

	}

	public void habilitarCamposListaPremiacion(ValueChangeEvent val) {
		String valor = val.getNewValue().toString();
		if (valor.equals("2")) {
			cantidadInicialPuntosDisabled = false;
			cantidadFinalPuntosDisabled = false;
			puntosProductosExigidosDisabled = false;
			cantidadFijaPuntosDisabled = true;

		} else {
			cantidadInicialPuntosDisabled = true;
			cantidadFinalPuntosDisabled = true;
			puntosProductosExigidosDisabled = true;
			cantidadFijaPuntosDisabled = false;
		}

	}

	public void cambiarTipoProducto(ValueChangeEvent val) {
		String valor = val.getNewValue().toString();
		MantenimientoINCConfiguracionConcursoForm f = (MantenimientoINCConfiguracionConcursoForm) this.formMantenimiento;
		cambiarTipoProducto(valor, f);
	}

	public void cambiarTipoProducto(String valor,
			MantenimientoINCConfiguracionConcursoForm f) {
		if (valor.equals("1")) {
			codigoPeriodoDisabled = true;
			puntosUnidadDisabled = true;
			factorMultiplicadorDisabled = true;
			unidadesExigidasDisabled = true;
			montoExigidoDisabled = true;
			puntosExigidosDisabled = true;
			f.setCodigoPeriodoDesde(null);
			f.setCodigoPeriodoHasta(null);
			f.setPuntosUnidad(null);
			f.setFactorMultiplicador(null);
			f.setUnidadesExigidas(null);
			f.setMontoExigido(null);
			f.setPuntosExigidos(null);
			oidTipoAgrupacionRendered = false;
			f.setOidTipoAgrupacion(null);
		} else if (valor.equals("2")) {
			codigoPeriodoDisabled = false;
			puntosUnidadDisabled = true;
			factorMultiplicadorDisabled = true;
			unidadesExigidasDisabled = true;
			montoExigidoDisabled = true;
			puntosExigidosDisabled = true;
			f.setPuntosUnidad(null);
			f.setFactorMultiplicador(null);
			f.setUnidadesExigidas(null);
			f.setMontoExigido(null);
			f.setPuntosExigidos(null);
			oidTipoAgrupacionRendered = false;
			f.setOidTipoAgrupacion(null);
		} else if (valor.equals("3")) {
			codigoPeriodoDisabled = false;
			puntosUnidadDisabled = false;
			factorMultiplicadorDisabled = false;
			unidadesExigidasDisabled = true;
			montoExigidoDisabled = true;
			puntosExigidosDisabled = true;
			f.setUnidadesExigidas(null);
			f.setMontoExigido(null);
			f.setPuntosExigidos(null);
			oidTipoAgrupacionRendered = false;
			f.setOidTipoAgrupacion(null);
		} else if (valor.equals("4")) {
			codigoPeriodoDisabled = false;
			puntosUnidadDisabled = true;
			factorMultiplicadorDisabled = true;
			unidadesExigidasDisabled = false;
			montoExigidoDisabled = false;
			puntosExigidosDisabled = false;
			f.setPuntosUnidad(null);
			oidTipoAgrupacionRendered = true;
			if (f.getCodigoBloqueProducto().equals("N"))
				oidTipoAgrupacionBloqueRendered = "N";

			if (f.getCodigoBloqueProducto().equals("O"))
				oidTipoAgrupacionBloqueRendered = "O";

			if (f.getCodigoBloqueProducto().equals("P"))
				oidTipoAgrupacionBloqueRendered = "P";

			if (f.getCodigoBloqueProducto().equals("C"))
				oidTipoAgrupacionBloqueRendered = "C";
		}
	}

	public void cambiarBloqueProducto(ValueChangeEvent val) {
		String valor = val.getNewValue().toString();
		MantenimientoINCConfiguracionConcursoForm f = (MantenimientoINCConfiguracionConcursoForm) this.formMantenimiento;
		cambiarBloqueProducto(valor, f);
	}

	public void cambiarBloqueProducto(String valor,
			MantenimientoINCConfiguracionConcursoForm f) {

		if (valor.equals("O")) {
			oidUnidadNegocioRendered = true;
			oidNegocioRendered = true;
			oidTipoOfertaRendered = true;
			f.setOidUnidadNegocio(null);
			f.setOidNegocio(null);
			f.setOidTipoOferta(null);
			codigoSAPRendered = false;
			CUVRendered = false;
			oidMarcaProductoRendered = false;
			codigoPeriodoRendered = true;
			codigoPeriodoCUVRendered = false;
			if (f.getOidTipoProducto().equals("4"))
				oidTipoAgrupacionBloqueRendered = "O";
		} else if (valor.equals("P")) {
			codigoSAPRendered = true;
			f.setCodigoSAP(null);
			oidUnidadNegocioRendered = true;
			oidNegocioRendered = true;
			oidTipoOfertaRendered = true;
			oidMarcaProductoRendered = false;
			f.setOidMarcaProducto(null);
			codigoPeriodoCUVRendered = false;
			CUVRendered = false;
			codigoPeriodoRendered = true;
			if (f.getOidTipoProducto().equals("4"))
				oidTipoAgrupacionBloqueRendered = "P";
		} else if (valor.equals("C")) {
			codigoPeriodoCUVRendered = true;
			f.setCodigoPeriodoCUV(null);
			CUVRendered = true;
			f.setCUV(null);
			oidUnidadNegocioRendered = true;
			oidNegocioRendered = true;
			codigoSAPRendered = false;
			codigoPeriodoRendered = false;
			oidTipoOfertaRendered = false;
			oidMarcaProductoRendered = false;
			if (f.getOidTipoProducto().equals("4"))
				oidTipoAgrupacionBloqueRendered = "C";
		} else if (valor.equals("N")) {
			codigoPeriodoRendered = true;
			oidUnidadNegocioRendered = false;
			oidNegocioRendered = false;
			oidTipoOfertaRendered = false;
			oidMarcaProductoRendered = true;
			codigoPeriodoCUVRendered = false;
			CUVRendered = false;
			codigoSAPRendered = false;
			if (f.getOidTipoProducto().equals("4"))
				oidTipoAgrupacionBloqueRendered = "N";
		}
	}

	public void abrirPopupBonificacion(ActionEvent event) {
		MantenimientoINCBonificacionPeriodoForm f = new MantenimientoINCBonificacionPeriodoForm();
		formBonificacion = f;
		this.getRequestContext().execute("PF('viewBonificacion').show()");
	}

	public void insertBonificacion(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'insert' method");
		}
		MantenimientoINCBonificacionPeriodoForm f = (MantenimientoINCBonificacionPeriodoForm) this.formBonificacion;

		try {
			if (StringUtils.isBlank(f.getCodigoPeriodo()))
				throw new Exception("Campaña es un campo requerido");
			if (StringUtils.isBlank(f.getPuntos()))
				throw new Exception("Factor Bonificación es un campo requerido");

			List detalList = this.incBonificacionPeriodoList;
			ConcursoParametrosGenerales concursoParametrosGenerales = this.incDtoConcurso;

			boolean encontrado = false;
			for (int i = 0; i < detalList.size(); i++) {
				ConcursoBonificacionPeriodo concursoBonificacionPeriodo = (ConcursoBonificacionPeriodo) detalList
						.get(i);
				String codigoPeriodo = concursoBonificacionPeriodo
						.getCodigoPeriodo();

				if (codigoPeriodo.equals(f.getCodigoPeriodo())) {
					encontrado = true;
					addError(
							"Error",
							this.getResourceMessage("mantenimientoINCBonificacionPeriodoForm.msg.duplicado"));
				}
			}

			if (!encontrado) {
				ConcursoBonificacionPeriodo concursoBonificacionPeriodo = new ConcursoBonificacionPeriodo();
				concursoBonificacionPeriodo.setCodigoPais(codigoPais);
				concursoBonificacionPeriodo.setNumeroConcurso(f
						.getNumeroConcurso());
				concursoBonificacionPeriodo.setCodigoPeriodo(f
						.getCodigoPeriodo());
				concursoBonificacionPeriodo
						.setPuntos(new Integer(f.getPuntos()));

				detalList.add(concursoBonificacionPeriodo);
				concursoParametrosGenerales
						.setIndActualizarBonificacionPeriodo(true);
				concursoParametrosGenerales
						.setListConcursoBonificacionPeriodo(detalList);
			}
			this.incBonificacionPeriodoList = detalList;
			dataTableBonificacion = new DataTableModel(
					incBonificacionPeriodoList);
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		}

	}

	public void deleteBonificacion(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'delete' method");
		}

		int tamanio = beanRegistroBonificacion.length;
		ConcursoParametrosGenerales concursoParametrosGenerales = this.incDtoConcurso;
		for (int i = 0; i < tamanio; i++) {
			ConcursoBonificacionPeriodo data = (ConcursoBonificacionPeriodo) this.beanRegistroBonificacion[i];

			for (int j = 0; j < incBonificacionPeriodoList.size(); j++) {
				ConcursoBonificacionPeriodo list = (ConcursoBonificacionPeriodo) incBonificacionPeriodoList
						.get(j);
				if (data.getCodigoPeriodo().equals(list.getCodigoPeriodo())) {
					incBonificacionPeriodoList.remove(j);
				}
			}
		}

		dataTableBonificacion = new DataTableModel(incBonificacionPeriodoList);
		concursoParametrosGenerales.setIndActualizarBonificacionPeriodo(true);
		concursoParametrosGenerales
				.setListConcursoBonificacionPeriodo(incBonificacionPeriodoList);

		beanRegistroBonificacion = null;

	}

	public void abrirPopupRecomendada(ActionEvent event) {
		MantenimientoINCRecomendadaPeriodoForm f = new MantenimientoINCRecomendadaPeriodoForm();
		formRecomendada = f;
		this.getRequestContext().execute("PF('viewRecomendada').show()");
	}

	public void insertRecomendada(ActionEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'insert' method");
		}
		MantenimientoINCRecomendadaPeriodoForm f = (MantenimientoINCRecomendadaPeriodoForm) this.formRecomendada;
		MantenimientoINCConfiguracionConcursoForm form = (MantenimientoINCConfiguracionConcursoForm) this.formMantenimiento;

		try {
			if (StringUtils.isBlank(f.getIndicadorTipo()))
				throw new Exception("Tipo es un campo requerido");
			if (StringUtils.isBlank(f.getSecuencia()))
				throw new Exception("Secuencia es un campo requerido");
			if (StringUtils.isBlank(f.getMonto()))
				throw new Exception("Monto es un campo requerido");

			if (f.getIndicadorTipo().equals("1")) { // Si es Recomendante
				if (StringUtils.isBlank(form.getNumeroMinimoPedidos())) {
					throw new Exception(
							this.getResourceMessage("mantenimientoINCRecomendadaPeriodoForm.msg.numeroPedidosRecomendante"));

				} else {
					if (Integer.parseInt(f.getSecuencia()) > Integer
							.parseInt(form.getNumeroMinimoPedidos()))
						throw new Exception(
								this.getResourceMessage("mantenimientoINCRecomendadaPeriodoForm.msg.numeroPedidosRecomendante2"));

				}
			} else { // Si es Recomendada
				if (StringUtils.isBlank(form
						.getNumeroMinimoPedidosRecomendada())) {
					throw new Exception(
							this.getResourceMessage("mantenimientoINCRecomendadaPeriodoForm.msg.numeroPedidosRecomendada"));
				} else {
					if (Integer.parseInt(f.getSecuencia()) > Integer
							.parseInt(form.getNumeroMinimoPedidosRecomendada())) {
						throw new Exception(
								this.getResourceMessage("mantenimientoINCRecomendadaPeriodoForm.msg.numeroPedidosRecomendada2"));
					}
				}
			}

			List detalList = this.incRecomendacionPeriodoList;
			ConcursoParametrosGenerales concursoParametrosGenerales = this.incDtoConcurso;

			boolean encontrado = false;
			for (int i = 0; i < detalList.size(); i++) {
				ConcursoRecomendadaPeriodo concursoRecomendadaPeriodo = (ConcursoRecomendadaPeriodo) detalList
						.get(i);
				String indicadorTipo = concursoRecomendadaPeriodo
						.getIndicadorTipo();
				String secuencia = concursoRecomendadaPeriodo.getSecuencia();

				if (indicadorTipo.equals(f.getIndicadorTipo())
						&& secuencia.equals(f.getSecuencia())) {
					encontrado = true;
					addError(
							"Error",
							this.getResourceMessage("mantenimientoINCRecomendadaPeriodoForm.msg.duplicado"));
				}
			}

			if (!encontrado) {
				ConcursoRecomendadaPeriodo concursoRecomendadaPeriodo = new ConcursoRecomendadaPeriodo();
				concursoRecomendadaPeriodo.setCodigoPais(codigoPais);
				concursoRecomendadaPeriodo.setNumeroConcurso(f
						.getNumeroConcurso());
				concursoRecomendadaPeriodo.setIndicadorTipo(f
						.getIndicadorTipo());
				concursoRecomendadaPeriodo.setSecuencia(f.getSecuencia());
				concursoRecomendadaPeriodo
						.setMonto(new BigDecimal(f.getMonto()));

				List detalListAux = this.incIndicadorTipoRecomendacionList;
				for (int i = 0; i < detalListAux.size(); i++) {
					Base base = (Base) detalListAux.get(i);

					if (base.getCodigo().equals(f.getIndicadorTipo())) {
						concursoRecomendadaPeriodo
								.setDescripcionIndicadorTipo(base
										.getDescripcion());
						break;
					}
				}

				detalList.add(concursoRecomendadaPeriodo);
				concursoParametrosGenerales
						.setIndActualizarRecomendadaPeriodo(true);
				concursoParametrosGenerales
						.setListConcursoRecomendadaPeriodo(detalList);
			}

			this.incRecomendacionPeriodoList = detalList;
			dataTableRecomendada = new DataTableModel(
					incRecomendacionPeriodoList);
		} catch (Exception e) {
			// TODO: handle exception
			addError("Error", obtieneMensajeErrorException(e));
		}

	}

	public void deleteRecomendada(ActionEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'delete' method");
		}

		int tamanio = beanRegistroRecomendada.length;

		ConcursoParametrosGenerales concursoParametrosGenerales = this.incDtoConcurso;
		for (int i = 0; i < tamanio; i++) {
			ConcursoRecomendadaPeriodo data = (ConcursoRecomendadaPeriodo) this.beanRegistroRecomendada[i];
			for (int j = 0; j < incRecomendacionPeriodoList.size(); j++) {
				ConcursoRecomendadaPeriodo list = (ConcursoRecomendadaPeriodo) incRecomendacionPeriodoList
						.get(j);
				if (data.getIndicadorTipo().equals(list.getIndicadorTipo())
						&& data.getSecuencia().equals(list.getSecuencia()))
					incRecomendacionPeriodoList.remove(j);

			}
		}

		dataTableRecomendada = new DataTableModel(incRecomendacionPeriodoList);
		concursoParametrosGenerales.setIndActualizarRecomendadaPeriodo(true);
		concursoParametrosGenerales
				.setListConcursoRecomendadaPeriodo(incRecomendacionPeriodoList);
		beanRegistroRecomendada = null;

	}

	public void cambiarBotonRecomendada(ValueChangeEvent val) {
		String valor = val.getNewValue().toString();
		if (valor.equals("true")) {
			indicadorPorPedidoBoolean = true;
			indicadorRangoPedidosBoolean = false;
		} else {
			indicadorPorPedidoBoolean = false;
		}

	}

	public void abrirPopupEstatus(ActionEvent event) {
		MantenimientoINCEstatusVentaForm f = new MantenimientoINCEstatusVentaForm();
		formEstatus = f;
		this.getRequestContext().execute("PF('viewEstatus').show()");
	}

	public void insertEstatus(ActionEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'insert' method");
		}
		MantenimientoINCEstatusVentaForm f = (MantenimientoINCEstatusVentaForm) this.formEstatus;
		MantenimientoINCConfiguracionConcursoForm form = (MantenimientoINCConfiguracionConcursoForm) this.formMantenimiento;

		try {
			if (StringUtils.isBlank(f.getOidEstatus()))
				throw new Exception("Estatus es un campo requerido");
			if (StringUtils.isBlank(f.getCodigoPeriodoInicial()))
				throw new Exception("Camapaña Inicial es un campo requerido");
			if (StringUtils.isBlank(f.getCodigoPeriodoFinal()))
				throw new Exception("Camapaña Final es un campo requerido");

			if (StringUtils.isBlank(form.getCodigoPeriodoFin())) {
				throw new Exception(
						this.getResourceMessage("mantenimientoINCConfiguracionConcursoForm.msg.estatus.PeriodoFinVigencia"));
			}

			if (StringUtils.isNotBlank(f.getCodigoPeriodoFinal())
					&& (Integer.parseInt(f.getCodigoPeriodoFinal()) > Integer
							.parseInt(form.getCodigoPeriodoFin()))) {
				throw new Exception(
						this.getResourceMessage("mantenimientoINCEstatusVentaForm.msg.validaPeriodoFinal"));
			}

			List detalList = this.incConcursoEstatusList;
			ConcursoParametrosGenerales concursoParametrosGenerales = this.incDtoConcurso;

			boolean encontrado = false;
			for (int i = 0; i < detalList.size(); i++) {
				ConcursoEstatusVenta concursoEstatusVenta = (ConcursoEstatusVenta) detalList
						.get(i);
				String oidEstatus = concursoEstatusVenta.getOidEstatusCliente()
						.toString();

				if (oidEstatus.equals(f.getOidEstatus())) {
					encontrado = true;
					addError(
							"Error",
							this.getResourceMessage("mantenimientoINCConfiguracionConcursoForm.msg.estatusDuplicado"));
				}
			}

			if (!encontrado) {
				ConcursoEstatusVenta concursoEstatusVenta = new ConcursoEstatusVenta();
				concursoEstatusVenta.setOidEstatusCliente(new Long(f
						.getOidEstatus()));
				concursoEstatusVenta.setCodigoPeriodoDesde(f
						.getCodigoPeriodoInicial());
				concursoEstatusVenta.setCodigoPeriodoHasta(f
						.getCodigoPeriodoFinal());

				List detalListAux = this.incEstatusClienteList;
				for (int i = 0; i < detalListAux.size(); i++) {
					Base base = (Base) detalListAux.get(i);

					if (base.getCodigo().equals(f.getOidEstatus())) {
						concursoEstatusVenta.setDescripcionEstatusCliente(base
								.getDescripcion());
						break;
					}
				}

				detalList.add(concursoEstatusVenta);
				concursoParametrosGenerales.setIndActualizarEstatusVenta(true);
				concursoParametrosGenerales
						.setListConcursoEstatusVenta(detalList);
			}

			this.incConcursoEstatusList = detalList;
			dataTableEstatus = new DataTableModel(incConcursoEstatusList);

		} catch (Exception e) {
			// TODO: handle exception
			addError("Error", obtieneMensajeErrorException(e));
		}

	}

	public void deleteEstatus(ActionEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'delete' method");
		}

		int tamanio = beanRegistroEstatus.length;
		ConcursoParametrosGenerales concursoParametrosGenerales = this.incDtoConcurso;
		for (int i = 0; i < tamanio; i++) {
			ConcursoEstatusVenta data = (ConcursoEstatusVenta) this.beanRegistroEstatus[i];
			for (int j = 0; j < incConcursoEstatusList.size(); j++) {
				ConcursoEstatusVenta list = (ConcursoEstatusVenta) incConcursoEstatusList
						.get(j);
				if (data.getOidEstatusCliente().equals(
						new Long(list.getOidEstatusCliente())))
					incConcursoEstatusList.remove(j);
			}
		}

		dataTableEstatus = new DataTableModel(incConcursoEstatusList);
		concursoParametrosGenerales.setIndActualizarBonificacionPeriodo(true);
		concursoParametrosGenerales
				.setListConcursoBonificacionPeriodo(incConcursoEstatusList);

		beanRegistroEstatus = null;
	}

	public void abrirPopupUnidad(ActionEvent event) {
		MantenimientoINCUnidadAdministrativaForm f = new MantenimientoINCUnidadAdministrativaForm();
		formUnidad = f;
		List listSubGerencia = this.siccSubGerenciaList;
		BaseOID baseSubGerencia = (BaseOID) listSubGerencia.get(0);
		f.setOidSubgerencia(baseSubGerencia.getOid().toString());
		f.setDescripcionSubgerencia(baseSubGerencia.getDescripcion());
		this.getRequestContext().execute("PF('viewUnidad').show()");
	}

	public void loadZonas(ValueChangeEvent val) {
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		String[] valores = (String[]) val.getNewValue();
		if (valores.length > 0) {
			String[] valoresN = new String[valores.length];
			int j = 0;
			for (int i = 0; i < valores.length; i++) {
				if (valores[i] != null) {
					valoresN[j] = valores[i].split("__")[0];
					j++;
				}
			}

			this.siccZonaList = ajax.getZonasByMultipleOidRegiones(
					new ArrayList(Arrays.asList(valoresN)), "X");
		} else
			this.siccZonaList = null;
	}

	public void insertUnidad(ActionEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'insert' method");
		}
		MantenimientoINCUnidadAdministrativaForm f = (MantenimientoINCUnidadAdministrativaForm) this.formUnidad;

		List detalList = this.incAmbitoList;
		ConcursoParametrosGenerales concursoParametrosGenerales = this.incDtoConcurso;

		log.debug("formulario  :  " + f);

		// obtenemos las regiones ingresadas
		Map mapRegiones = new HashMap();
		if (f.getRegiones() != null) {
			for (int i = 0; i < f.getRegiones().length; i++) {
				String region = f.getRegiones()[i];

				String[] datosRegion = region.split("__");
				mapRegiones.put(datosRegion[0], datosRegion[1]);
			}
		}

		// obtenemos las zonas ingresadas y verificamos si ya han sido
		// ingresadas anteriormente
		if (f.getZonas() != null) {
			for (int i = 0; i < f.getZonas().length; i++) {
				String zona = f.getZonas()[i];

				String[] datosZona = zona.split("__");
				Long oidZona = new Long(datosZona[1]);
				Long oidRegion = new Long(datosZona[0]);
				boolean encontrado = false;

				for (int j = 0; j < detalList.size(); j++) {
					ConcursoAmbitoGeografico ambito = (ConcursoAmbitoGeografico) detalList
							.get(j);

					if ((ambito.getOidZona() != null)
							&& (ambito.getOidZona().longValue() == oidZona
									.longValue())) {
						// ya se encuentre en la lista de ambitos actual
						encontrado = true;
						break;
					}
				}

				if (!encontrado) {
					ConcursoAmbitoGeografico ambito = new ConcursoAmbitoGeografico();
					ambito.setOidRegion(oidRegion);
					ambito.setOidZona(oidZona);
					ambito.setOidSubgerencia(new Long(f.getOidSubgerencia()
							.split(";")[0]));

					ambito.setDescripcionZona(datosZona[2]);
					String descripcionRegion = (String) mapRegiones
							.get(oidRegion.toString());
					ambito.setDescripcionRegion(descripcionRegion);
					ambito.setDescripcionSubGerencia(f
							.getDescripcionSubgerencia());

					// si existe la region sin zona registrada, lo eliminamos
					for (int j = 0; j < detalList.size(); j++) {
						ConcursoAmbitoGeografico ambitoAux = (ConcursoAmbitoGeografico) detalList
								.get(j);

						if ((ambito.getOidRegion().longValue() == oidRegion
								.longValue()) && (ambito.getOidZona() == null)) {
							detalList.remove(j);
							break;
						}
					}

					detalList.add(ambito);
					concursoParametrosGenerales
							.setIndActualizarAmbitoGeografico(true);
					concursoParametrosGenerales
							.setListConcursoAmbitoGeografico(detalList);
				}
			}

		}

		// obtenemos las regiones ingresadas y verificamos si ya han sido
		// ingresadas anteriormente
		if (f.getRegiones() != null) {
			for (int i = 0; i < f.getRegiones().length; i++) {
				String region = f.getRegiones()[i];

				String[] datosRegion = region.split("__");
				Long oidRegion = new Long(datosRegion[0]);
				String descripcionRegion = datosRegion[1];

				boolean encontrado = false;

				for (int j = 0; j < detalList.size(); j++) {
					ConcursoAmbitoGeografico ambito = (ConcursoAmbitoGeografico) detalList
							.get(j);

					if (ambito.getOidRegion().longValue() == oidRegion
							.longValue()) {
						// ya se encuentre en la lista de ambitos actual
						encontrado = true;
						break;
					}
				}

				if (!encontrado) {
					ConcursoAmbitoGeografico ambito = new ConcursoAmbitoGeografico();
					ambito.setOidRegion(oidRegion);
					ambito.setOidZona(null);
					ambito.setOidSubgerencia(new Long(f.getOidSubgerencia()));

					ambito.setDescripcionZona("");
					ambito.setDescripcionRegion(descripcionRegion);
					ambito.setDescripcionSubGerencia(f
							.getDescripcionSubgerencia());

					detalList.add(ambito);
					concursoParametrosGenerales
							.setIndActualizarAmbitoGeografico(true);
					concursoParametrosGenerales
							.setListConcursoAmbitoGeografico(detalList);
				}
			}
		}
		siccZonaList = null;
		f.setRegiones(null);
		this.incAmbitoList = detalList;
		dataTableUnidad = new DataTableModel(incAmbitoList);
	}

	public void deleteUnidad(ActionEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'delete' method");
		}

		int tamanio = beanRegistroUnidad.length;
		ConcursoParametrosGenerales concursoParametrosGenerales = this.incDtoConcurso;
		for (int i = 0; i < tamanio; i++) {
			ConcursoAmbitoGeografico data = (ConcursoAmbitoGeografico) this.beanRegistroUnidad[i];

			for (int j = 0; j < incAmbitoList.size(); j++) {
				ConcursoAmbitoGeografico list = (ConcursoAmbitoGeografico) incAmbitoList
						.get(j);
				if (list.getOidZona() != null) {
					if ((data.getOidRegion().equals(list.getOidRegion()) && data
							.getOidZona().equals(list.getOidZona())))
						incAmbitoList.remove(j);
				} else {
					if (data.getOidRegion().equals(list.getOidRegion()))
						incAmbitoList.remove(j);
				}
			}
		}

		dataTableUnidad = new DataTableModel(incAmbitoList);
		
		concursoParametrosGenerales.setIndActualizarAmbitoGeografico(true);
		concursoParametrosGenerales.setListConcursoAmbitoGeografico(incAmbitoList);

		beanRegistroUnidad = null;

	}

	public void abrirPopupClasificacion(ActionEvent event) {
		MantenimientoINCClasificacionParticipanteForm f = new MantenimientoINCClasificacionParticipanteForm();
		formClasificacion = f;
		this.getRequestContext().execute("PF('viewClasificacion').show()");
	}

	public void insertClasificacion(ActionEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'insert' method");
		}
		MantenimientoINCClasificacionParticipanteForm f = (MantenimientoINCClasificacionParticipanteForm) this.formClasificacion;
		if (excluirClasificacion)
			f.setExcluirClasificacion("1");
		else
			f.setExcluirClasificacion("");
		try {
			List detalList = this.incConcursoClasificacionesList;
			ConcursoParametrosGenerales concursoParametrosGenerales = this.incDtoConcurso;

			boolean encontrado = false;
			for (int i = 0; i < detalList.size(); i++) {
				ConcursoClasificacionParticipante concursoClasificacionParticipante = (ConcursoClasificacionParticipante) detalList
						.get(i);
				String oidEstatus = concursoClasificacionParticipante
						.getOidClasificacion().toString();

				if (oidEstatus.equals(f.getOidClasificacion())) {
					encontrado = true;
					addError(
							"Error",
							this.getResourceMessage("mantenimientoINCConfiguracionConcursoForm.msg.clasificacionDuplicado"));
				}
			}

			if (!encontrado) {
				ConcursoClasificacionParticipante concursoClasificacionParticipante = new ConcursoClasificacionParticipante();
				concursoClasificacionParticipante.setOidClasificacion(new Long(
						f.getOidClasificacion()));

				List detalListAux = this.incClasificacionesParticipantesList;
				for (int i = 0; i < detalListAux.size(); i++) {
					Base base = (Base) detalListAux.get(i);

					if (base.getCodigo().equals(f.getOidClasificacion())) {
						concursoClasificacionParticipante
								.setDescripcionClasificacion(base
										.getDescripcion());
						break;
					}
					concursoClasificacionParticipante.setExcluirClasificacion(f
							.getExcluirClasificacion());
				}

				detalList.add(concursoClasificacionParticipante);

				concursoParametrosGenerales
						.setIndActualizarClasificacionParticipantes(true);
				concursoParametrosGenerales
						.setListConcursoClasificacionParticipante(detalList);
			}

			this.incConcursoClasificacionesList = detalList;
			dataTableClasificacion = new DataTableModel(
					incConcursoClasificacionesList);

		} catch (Exception e) {
			// TODO: handle exception
			addError("Error", obtieneMensajeErrorException(e));
		}

	}

	public void deleteClasificacion(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'delete' method");
		}
		int tamanio = beanRegistroClasificacion.length;
		ConcursoParametrosGenerales concursoParametrosGenerales = this.incDtoConcurso;
		for (int i = 0; i < tamanio; i++) {
			ConcursoClasificacionParticipante data = (ConcursoClasificacionParticipante) this.beanRegistroClasificacion[i];

			for (int j = 0; j < incConcursoClasificacionesList.size(); j++) {
				ConcursoClasificacionParticipante list = (ConcursoClasificacionParticipante) incConcursoClasificacionesList
						.get(j);
				if (data.getOidClasificacion().equals(
						list.getOidClasificacion())) {
					incConcursoClasificacionesList.remove(j);
				}
			}
		}
		dataTableClasificacion = new DataTableModel(
				incConcursoClasificacionesList);
		concursoParametrosGenerales
				.setIndActualizarClasificacionParticipantes(true);
		concursoParametrosGenerales
				.setListConcursoClasificacionParticipante(incConcursoClasificacionesList);
		beanRegistroClasificacion = null;

	}

	public void abrirPopupPeriodoDespacho(ActionEvent event) throws Exception {
		MantenimientoINCPeriodoDespachoForm f = new MantenimientoINCPeriodoDespachoForm();
		MantenimientoINCConfiguracionConcursoForm form = (MantenimientoINCConfiguracionConcursoForm) this.formMantenimiento;
		try {
			if (StringUtils.isBlank(form.getCodigoPeriodoDespachoInicio()))
				throw new Exception("Ingrese Campaña Premiación Inicio");

			if (StringUtils.isBlank(form.getCodigoPeriodoDespacho()))
				throw new Exception("Ingrese Campaña Premiación Fin");

			formPeriodoDespacho = f;
			this.getRequestContext()
					.execute("PF('viewPeriodoDespacho').show()");
		} catch (Exception e) {
			// TODO: handle exception
			addError("Error", obtieneMensajeErrorException(e));
		}

	}

	public void insertPeriodoDespacho(ActionEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'insert' method");
		}
		MantenimientoINCPeriodoDespachoForm f = (MantenimientoINCPeriodoDespachoForm) this.formPeriodoDespacho;
		MantenimientoINCConfiguracionConcursoForm form = (MantenimientoINCConfiguracionConcursoForm) this.formMantenimiento;

		try {
			String periodoInicio = form.getCodigoPeriodoDespachoInicio();
			String periodoFin = form.getCodigoPeriodoDespacho();

			List detalList = this.incPeriodoDespachoList;
			ConcursoParametrosGenerales concursoParametrosGenerales = this.incDtoConcurso;

			boolean encontrado = false;
			Integer valorPeriodoInicio = new Integer(periodoInicio);
			Integer valorPeriodoFinal = new Integer(periodoFin);
			Integer valorPeriodo = new Integer(f.getCodigoPeriodo());
			if (valorPeriodo < valorPeriodoInicio
					|| valorPeriodo > valorPeriodoFinal) {
				encontrado = true;
				throw new Exception(
						this.getResourceMessage("mantenimientoINCPeriodoDespachoForm.msg.codigoPeriodo.noCumpleRango"));

			}
			if (!encontrado) {
				for (int i = 0; i < detalList.size(); i++) {
					ConcursoPeriodoDespacho concursoPeriodoDespacho = (ConcursoPeriodoDespacho) detalList
							.get(i);
					String codigoPeriodoLista = concursoPeriodoDespacho
							.getCodigoPeriodo();
					if (f.getCodigoPeriodo().equals(codigoPeriodoLista)) {
						encontrado = true;
						throw new Exception(
								this.getResourceMessage("mantenimientoINCPeriodoDespachoForm.msg.codigoPeriodo.existe"));

					}
				}
			}

			if (!encontrado) {
				ConcursoPeriodoDespacho concursoPeriodoDespacho = new ConcursoPeriodoDespacho();
				concursoPeriodoDespacho.setCodigoPais(codigoPais);
				concursoPeriodoDespacho
						.setNumeroConcurso(f.getNumeroConcurso());
				concursoPeriodoDespacho.setCodigoPeriodo(f.getCodigoPeriodo());
				concursoPeriodoDespacho.setCodigoPeriodoFin(null);

				detalList.add(concursoPeriodoDespacho);
				concursoParametrosGenerales
						.setIndActualizarPeriodoDespacho(true);
				concursoParametrosGenerales
						.setListConcursoPeriodoDespacho(detalList);
			}

			this.incPeriodoDespachoList = detalList;
			dataTablePeriodoDespacho = new DataTableModel(
					incPeriodoDespachoList);

		} catch (Exception e) {
			// TODO: handle exception
			addError("Error", obtieneMensajeErrorException(e));
		}

	}

	public void deletePeriodoDespacho(ActionEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'delete' method");
		}
		int tamanio = beanRegistroPeriodoDespacho.length;
		ConcursoParametrosGenerales concursoParametrosGenerales = this.incDtoConcurso;
		for (int i = 0; i < tamanio; i++) {
			ConcursoPeriodoDespacho data = (ConcursoPeriodoDespacho) beanRegistroPeriodoDespacho[i];
			for (int j = 0; j < incPeriodoDespachoList.size(); j++) {
				ConcursoPeriodoDespacho list = (ConcursoPeriodoDespacho) incPeriodoDespachoList
						.get(j);
				if (data.getCodigoPeriodo().equals(list.getCodigoPeriodo()))
					incPeriodoDespachoList.remove(j);

			}

		}
		dataTablePeriodoDespacho = new DataTableModel(incPeriodoDespachoList);
		concursoParametrosGenerales.setIndActualizarPeriodoDespacho(true);
		concursoParametrosGenerales
				.setListConcursoPeriodoDespacho(incPeriodoDespachoList);
		beanRegistroPeriodoDespacho = null;

	}

	public void abrirPopupPuntajeExigido(ActionEvent event) throws Exception {
		MantenimientoINCPuntajeExigidoForm f = new MantenimientoINCPuntajeExigidoForm();
		MantenimientoINCConfiguracionConcursoForm form = (MantenimientoINCConfiguracionConcursoForm) this.formMantenimiento;
		try {
			if (beanRegistroPremiacion == null)
				throw new Exception("Debe Seleccionar un Nivel");

			if (StringUtils.isBlank(form.getCodigoPeriodoInicio()))
				throw new Exception("Ingrese Campaña Concurso Inicio");

			if (StringUtils.isBlank(form.getCodigoPeriodoFin()))
				throw new Exception("Ingrese Campaña Concurso Fin");

			ConcursoNivelPremiacion data = (ConcursoNivelPremiacion) beanRegistroPremiacion;
			f.setNivel(data.getNumeroNivel().toString());

			formPuntajeExigido = f;
			/* Cargando la lista temporal en base al nivel */
			List detalList = this.incPuntajeExigidoList;
			List listaTemporal = new ArrayList();
			if (detalList == null) {
				this.incPuntajeExigidoListTempo = listaTemporal;

			} else {
				for (int i = 0; i < detalList.size(); i++) {
					ConcursoPuntajeExigido concursoPuntajeExigido = (ConcursoPuntajeExigido) detalList
							.get(i);
					String nivelLista = concursoPuntajeExigido.getNivel();
					if (StringUtils.equals(nivelLista, data.getNumeroNivel()
							.toString())) {
						listaTemporal.add(concursoPuntajeExigido);
					}
				}
				this.incPuntajeExigidoListTempo = listaTemporal;

			}
			dataTablePuntajeExigido = new DataTableModel(
					incPuntajeExigidoListTempo);
			this.getRequestContext().execute("PF('viewPuntajeExigido').show()");
		} catch (Exception e) {
			// TODO: handle exception
			addError("Error", obtieneMensajeErrorException(e));
		}
	}

	public void insertPuntajeExigido(ActionEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'insert' method");
		}
		MantenimientoINCPuntajeExigidoForm f = (MantenimientoINCPuntajeExigidoForm) this.formPuntajeExigido;
		MantenimientoINCConfiguracionConcursoForm form = (MantenimientoINCConfiguracionConcursoForm) this.formMantenimiento;

		try {

			if (StringUtils.isBlank(f.getCodigoPeriodo()))
				throw new Exception("Campaña es un campo requerido");
			if (StringUtils.isBlank(f.getPuntajeExigido()))
				throw new Exception("Puntaje Exigido es un campo requerido");

			String periodoInicio = form.getCodigoPeriodoInicio();
			String periodoFin = form.getCodigoPeriodoFin();

			List detalListTotal = this.incPuntajeExigidoList;
			List detalList = this.incPuntajeExigidoListTempo;
			ConcursoParametrosGenerales concursoParametrosGenerales = this.incDtoConcurso;

			boolean encontrado = false;
			Integer valorPeriodoInicio = new Integer(periodoInicio);
			Integer valorPeriodoFinal = new Integer(periodoFin);
			Integer valorPeriodo = new Integer(f.getCodigoPeriodo());
			if (valorPeriodo < valorPeriodoInicio
					|| valorPeriodo > valorPeriodoFinal) {
				encontrado = true;
				throw new Exception(
						this.getResourceMessage("mantenimientoINCPuntajeExigidoForm.msg.codigoPeriodo.noCumpleRango"));

			}
			if (!encontrado) {
				for (int i = 0; i < detalList.size(); i++) {
					ConcursoPuntajeExigido concursoPuntajeExigido = (ConcursoPuntajeExigido) detalList
							.get(i);
					String codigoPeriodoLista = concursoPuntajeExigido
							.getCodigoPeriodo();
					if (f.getCodigoPeriodo().equals(codigoPeriodoLista)) {
						encontrado = true;
						throw new Exception(
								this.getResourceMessage("mantenimientoINCPuntajeExigidoForm.msg.codigoPeriodo.existe"));
					}
				}
			}

			if (!encontrado) {
				ConcursoPuntajeExigido concursoPuntajeExigido = new ConcursoPuntajeExigido();
				concursoPuntajeExigido.setCodigoPais(codigoPais);
				concursoPuntajeExigido.setNivel(f.getNivel());
				concursoPuntajeExigido.setCodigoPeriodo(f.getCodigoPeriodo());
				concursoPuntajeExigido.setPuntajeExigido(f.getPuntajeExigido());

				detalList.add(concursoPuntajeExigido);
				detalListTotal.add(concursoPuntajeExigido);
				concursoParametrosGenerales
						.setIndActualizarPuntajeExigido(true);
				concursoParametrosGenerales
						.setListConcursoPuntajeExigido(detalListTotal);
			}

			this.incPuntajeExigidoListTempo = detalList;
			dataTablePuntajeExigido = new DataTableModel(
					incPuntajeExigidoListTempo);
		} catch (Exception e) {
			// TODO: handle exception
			addError("Error", obtieneMensajeErrorException(e));
		}

	}

	public void deletePuntajeExigido(ActionEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'delete' method");
		}
		MantenimientoINCPuntajeExigidoForm f = (MantenimientoINCPuntajeExigidoForm) this.formPuntajeExigido;
		ConcursoParametrosGenerales concursoParametrosGenerales = this.incDtoConcurso;

		List detalListTotal = this.incPuntajeExigidoList;
		List detalList = this.incPuntajeExigidoListTempo;
		List detalListNuevo = new ArrayList();
		int tamanio = beanRegistroPuntajeExigido.length;
		String nivel = f.getNivel();

		for (int i = 0; i < tamanio; i++) {
			ConcursoPuntajeExigido data = (ConcursoPuntajeExigido) this.beanRegistroPuntajeExigido[i];
			if (data != null) {
				for (int j = 0; j < detalList.size(); j++) {
					ConcursoPuntajeExigido list = (ConcursoPuntajeExigido) detalList
							.get(j);
					if (data.getCodigoPeriodo().equals(list.getCodigoPeriodo()))
						incPuntajeExigidoListTempo.remove(j);
				}
			}
		}

		detalList = incPuntajeExigidoListTempo;
		concursoParametrosGenerales.setIndActualizarPuntajeExigido(true);

		/* Reconstruyendo la lista general */
		for (int i = 0; i < detalListTotal.size(); i++) {
			ConcursoPuntajeExigido concursoPuntajeExigido = (ConcursoPuntajeExigido) detalListTotal
					.get(i);
			String nivelLista = concursoPuntajeExigido.getNivel();
			if (!StringUtils.equals(nivelLista, nivel)) {
				detalListNuevo.add(concursoPuntajeExigido);
			}
		}
		if (detalList != null) {
			for (int i = 0; i < detalList.size(); i++) {
				ConcursoPuntajeExigido concursoPuntajeExigido = (ConcursoPuntajeExigido) detalList
						.get(i);
				detalListNuevo.add(concursoPuntajeExigido);
			}
		}
		detalListTotal = detalListNuevo;
		concursoParametrosGenerales
				.setListConcursoPuntajeExigido(detalListTotal);

		this.incPuntajeExigidoList = detalListTotal;
		dataTablePuntajeExigido = new DataTableModel(incPuntajeExigidoList);
		beanRegistroPuntajeExigido = null;

	}

	// ---------------- DEFINIR PREMIO ------------------------------------------
	public void abrirPopupDefinirPremio(ActionEvent event) throws Exception {
		MantenimientoINCDefinirPremioForm f = new MantenimientoINCDefinirPremioForm();
		MantenimientoINCConfiguracionConcursoForm form = (MantenimientoINCConfiguracionConcursoForm) this.formMantenimiento;

		resetDefinirPremio(f);

		try {
			if (beanRegistroPremiacion == null)
				throw new Exception("Debe Seleccionar un Nivel");

			ConcursoNivelPremiacion data = (ConcursoNivelPremiacion) beanRegistroPremiacion;

			// Validamos el ingreso al popup
			
			int existeData = 0;
			if (data.getListConcursoLotePremioArticulo() != null && data.getListConcursoLotePremioArticulo().size() > 0) 
			{
				for (int i = 0; i < data.getListConcursoLotePremioArticulo().size(); i++) 
				{
					ConcursoLotePremioArticulo aux = (ConcursoLotePremioArticulo) data.getListConcursoLotePremioArticulo().get(i);
					Map criteria = new HashMap();
					criteria.put("oidPremio", aux.getOid());
					criteria.put("valueIndTipoPrem", "D");

					existeData = service.getExistePremiosDescuento(criteria);
					if (existeData > 0)
						break;
				}
			}

			if (existeData > 0) {
				this.addWarn("Advertencia", this.getResourceMessage("mantenimientoINCDefinirPremioForm.existePremiosDescuento"));
				return;
			}
			 
			Map criteria = new HashMap();

			List detalList = this.incNivelesPremiacionList;
			String nivelSeleccionado = data.getNumeroNivel().toString();
			String indicadorNivelSelectivo = data.getIndicadorNivelSelectivo().toString();

			f.setIndicadorNivelSelectivo(indicadorNivelSelectivo);
			f.setNumeroNivel(nivelSeleccionado);

			List detalListArticulos = new ArrayList();
			// se verifica si existen premios descuento
			List premioDescuento = new ArrayList();

			for (int i = 0; i < detalList.size(); i++) {
				ConcursoNivelPremiacion concursoNivelPremiacion = (ConcursoNivelPremiacion) detalList.get(i);
				int numeroNivel = concursoNivelPremiacion.getNumeroNivel().intValue();

				if (numeroNivel == Integer.parseInt(f.getNumeroNivel())) {
					f.setDescripcionLote("Nivel " + f.getNumeroNivel());

					if (concursoNivelPremiacion.getListConcursoLotePremioArticulo() == null) {
						concursoNivelPremiacion.setListConcursoLotePremioArticulo(new ArrayList());
					}

					// numeroLote es un correlativo por nivel, si el nivel es
					// elegible
					if (Constants.NRO_UNO.equals(indicadorNivelSelectivo)) {
						f.setNumeroLote(String.valueOf(concursoNivelPremiacion.getListConcursoLotePremioArticulo().size() + 1));
						f.setDescripcionLote(f.getDescripcionLote() + " - Opc " + f.getNumeroLote());
					} else {
						if (concursoNivelPremiacion
								.getListConcursoLotePremioArticulo().size() > 0) {
							ConcursoLotePremioArticulo concursoLotePremioArticulo = (ConcursoLotePremioArticulo) concursoNivelPremiacion
									.getListConcursoLotePremioArticulo().get(0);
							f.setDescripcionLote(concursoLotePremioArticulo
									.getDescripcionLote());
						}
						f.setNumeroLote(Constants.NRO_UNO);
					}

					List detalListAux = concursoNivelPremiacion.getListConcursoLotePremioArticulo();

					for (int j = 0; j < detalListAux.size(); j++) {
						ConcursoLotePremioArticulo concursoLotePremioArticulo = (ConcursoLotePremioArticulo) detalListAux.get(j);
						detalListArticulos.addAll(concursoLotePremioArticulo.getListConcursoArticuloLote());
						
						// se verifica si existen premios descuento
						if(concursoLotePremioArticulo.getListConcursoArticuloLoteDescuento()!= null &&
								concursoLotePremioArticulo.getListConcursoArticuloLoteDescuento().size() > 0)
							premioDescuento.addAll(concursoLotePremioArticulo.getListConcursoArticuloLoteDescuento());

						ConcursoArticuloLote concArtLote = (ConcursoArticuloLote)detalListArticulos.get(0);

						
						if(concArtLote.getIndicadorPremiosWeb()==null)
							concArtLote.setIndicadorPremiosWeb(0);						
						
						if(concArtLote.getIndicadorPremiosWeb()==1)
							indicadorPremiosWebBoolean = true;
						else
							indicadorPremiosWebBoolean = false;
					}


				}
			}

			if(premioDescuento.size() > 0){
				this.addWarn("Advertencia", this.getResourceMessage("mantenimientoINCDefinirPremioForm.existePremiosDescuento"));
				return;	
			}
			
			this.mostrarBotones = false;
			this.incArticulosLoteList = detalListArticulos;		
			dataTableDefinirPremio = new DataTableModel(incArticulosLoteList);
			f.setTipoLote("");
			f.setIndicadorPremiosWeb("0");
			actualizarLote(f);
			formDefinirPremio = f;
			actualizarLotePremio(f.getTipoLote());
			
			Base valInd = (Base)incTipoLoteList.get(0);
			if(StringUtils.isBlank(valInd.getCodigo()))
				indicadorPremiosWebBoolean=false;
			
			this.getRequestContext().execute("PF('viewDefinirPremio').show()");
		} catch (Exception e) {
			// TODO: handle exception
			addError("Error", obtieneMensajeErrorException(e));
		}

	}

	public void insertDefinirPremio(ActionEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'insert' method");
		}
		MantenimientoINCDefinirPremioForm f = (MantenimientoINCDefinirPremioForm) this.formDefinirPremio;
		String descripcionProducto = "";
		try {
			if (indicadorPremiosWebBoolean)
				f.setIndicadorPremiosWeb("1");
			else
				f.setIndicadorPremiosWeb("0");
				
			if (indicadorCentroServicioBoolean)
				f.setIndicadorCentroServicio("1");
			else
				f.setIndicadorCentroServicio("0");

			// Validamos el codigo SAP ingresado
			Map criteriaBusqueda = new HashMap();
			criteriaBusqueda.put("codigoSAP", f.getCodigoSAP());
			criteriaBusqueda.put("codigoPais", codigoPais);

			MantenimientoRECProductosFFNNEEService service = (MantenimientoRECProductosFFNNEEService) getBean("spusicc.mantenimientoRECProductosFFNNEEService");
			String oidProducto = service.getOidProducto(criteriaBusqueda);
			criteriaBusqueda.put("oidProducto", oidProducto);

			if (oidProducto == null) {
				throw new Exception(
						this.getResourceMessage("mantenimientoINCDefinirPremioForm.msg.codigoProductoNoExiste"));

			} else {
				criteriaBusqueda.put("oidProducto", oidProducto);
				descripcionProducto = service
						.getDescripcionProducto(criteriaBusqueda);

				List detalList = this.incArticulosLoteList;
				ConcursoParametrosGenerales concursoParametrosGenerales = this.incDtoConcurso;

				ConcursoLotePremioArticulo concursoLotePremioArticulo = null;
				List detalListAux = this.incNivelesPremiacionList;
				ConcursoNivelPremiacion concursoNivelPremiacionAct = null;

				for (int i = 0; i < detalListAux.size(); i++) {
					ConcursoNivelPremiacion concursoNivelPremiacion = (ConcursoNivelPremiacion) detalListAux.get(i);
					String numeroNivel = concursoNivelPremiacion.getNumeroNivel().toString();

					if (numeroNivel.equals(f.getNumeroNivel()))
						concursoNivelPremiacionAct = concursoNivelPremiacion;
				}

				if (Constants.NRO_UNO.equals(f.getIndicadorNivelSelectivo())) {
					List detalListLote = concursoNivelPremiacionAct.getListConcursoLotePremioArticulo();
					boolean encontrado = false;

					for (int j = 0; j < detalListLote.size(); j++) {
						concursoLotePremioArticulo = (ConcursoLotePremioArticulo) detalListLote.get(j);

						if (concursoLotePremioArticulo.getNumeroLote().toString().equals(f.getNumeroLote())) 
                                                {
							f.setDescripcionLote(concursoLotePremioArticulo.getDescripcionLote());
							f.setNumeroPremio(concursoLotePremioArticulo.getNumeroPremio().toString());
							encontrado = true;
							break;
						}
					}

					if (!encontrado) {
						concursoLotePremioArticulo = new ConcursoLotePremioArticulo();
						concursoLotePremioArticulo.setDescripcionLote(f.getDescripcionLote());
						concursoLotePremioArticulo.setNumeroLote(new Integer(f.getNumeroLote()));
						concursoLotePremioArticulo.setNumeroPremio(new Integer(f.getNumeroPremio()));
						concursoLotePremioArticulo.setListConcursoArticuloLote(new ArrayList());
						concursoNivelPremiacionAct.getListConcursoLotePremioArticulo().add(concursoLotePremioArticulo);
						concursoLotePremioArticulo.setIndicadorPremiosWeb(new Integer(f.getIndicadorPremiosWeb()));
					}

				} else {
					if (concursoNivelPremiacionAct.getListConcursoLotePremioArticulo() == null
							|| concursoNivelPremiacionAct.getListConcursoLotePremioArticulo().size() == 0) 
                                        {
						concursoLotePremioArticulo = new ConcursoLotePremioArticulo();
						concursoLotePremioArticulo.setDescripcionLote(f.getDescripcionLote());
						concursoLotePremioArticulo.setNumeroLote(new Integer(f.getNumeroLote()));
						concursoLotePremioArticulo.setNumeroPremio(new Integer(f.getNumeroPremio()));
						concursoLotePremioArticulo.setListConcursoArticuloLote(new ArrayList());
						concursoNivelPremiacionAct.getListConcursoLotePremioArticulo().add(concursoLotePremioArticulo);
						concursoLotePremioArticulo.setIndicadorPremiosWeb(new Integer(f.getIndicadorPremiosWeb()));

					} else {
						concursoLotePremioArticulo = (ConcursoLotePremioArticulo) concursoNivelPremiacionAct.getListConcursoLotePremioArticulo().get(0);
						f.setDescripcionLote(concursoLotePremioArticulo.getDescripcionLote());
						f.setNumeroPremio(concursoLotePremioArticulo.getNumeroPremio().toString());
						if(concursoLotePremioArticulo.getIndicadorPremiosWeb()== null)
							concursoLotePremioArticulo.setIndicadorPremiosWeb(0);
						f.setIndicadorPremiosWeb(concursoLotePremioArticulo.getIndicadorPremiosWeb().toString());
					}
				}

				ConcursoArticuloLote concursoArticuloLote = new ConcursoArticuloLote();
				concursoArticuloLote.setNumeroNivel(f.getNumeroNivel());
				concursoArticuloLote.setDescripcionLote(f.getDescripcionLote());
				concursoArticuloLote.setNumeroPremio(f.getNumeroPremio());
				concursoArticuloLote.setCodigoSAP(f.getCodigoSAP());
				concursoArticuloLote.setNumeroUnidades(Integer.parseInt(f.getNumeroUnidades()));

				if (StringUtils.isNotEmpty(f.getPrecio()))
					concursoArticuloLote.setPrecioPublico(new BigDecimal(f.getPrecio()));
				else
					concursoArticuloLote.setPrecioPublico(new BigDecimal("0.00"));

				concursoArticuloLote.setIndicadorCentroServicio(new Integer(f.getIndicadorCentroServicio()));
				concursoArticuloLote.setIndicadorPremiosWeb(new Integer(f.getIndicadorPremiosWeb()));
				concursoArticuloLote.setIndicadorTipoEntrega(f.getIndicadorTipoEntrega());
				concursoArticuloLote.setObservaciones(f.getObservaciones());
				concursoArticuloLote.setIndicadorDespacho(new Integer(1));
				concursoArticuloLote.setCodigoVentaFicticio("");
				concursoArticuloLote.setDescripcionProducto(descripcionProducto);

				if (!StringUtils.isEmpty(f.getOidCentroServGarantia())) {
					concursoArticuloLote.setOidCentroServGarantia(new Long(f.getOidCentroServGarantia()));
				}

				if (!StringUtils.isEmpty(f.getOidCentroServEntrega())) {
					concursoArticuloLote.setOidCentroServEntrega(new Long(f.getOidCentroServEntrega()));
				}

				if (!StringUtils.isEmpty(f.getNumeroMesesGarantia())) {
					concursoArticuloLote.setNumeroMesesGarantia(new Integer(f.getNumeroMesesGarantia()));
				}

				for (int k = 0; k < concursoLotePremioArticulo.getListConcursoArticuloLote().size(); k++) {
					ConcursoArticuloLote aux = (ConcursoArticuloLote) concursoLotePremioArticulo.getListConcursoArticuloLote().get(k);

					if (aux.getCodigoSAP().equalsIgnoreCase(concursoArticuloLote.getCodigoSAP())) 
                                        {
						throw new Exception(this.getResourceMessage("mantenimientoINCDefinirPremioForm.msg.productoExistente"));
					}
				}

				int pos = -1;
				for (int k = 0; k < concursoLotePremioArticulo
						.getListConcursoArticuloLote().size(); k++) {
					ConcursoArticuloLote aux = (ConcursoArticuloLote) concursoLotePremioArticulo
							.getListConcursoArticuloLote().get(k);

					if ("".equals(aux.getCodigoVentaFicticio())) {
						if (concursoArticuloLote.getCodigoSAP().compareTo(
								aux.getCodigoSAP()) < 0) {
							pos = k;
							break;
						}
					}
				}

				if (pos >= 0)
					concursoLotePremioArticulo.getListConcursoArticuloLote()
							.add(pos, concursoArticuloLote);
				else
					concursoLotePremioArticulo.getListConcursoArticuloLote()
							.add(concursoArticuloLote);

				List detalListArticulos = new ArrayList();
				List detalListLote = concursoNivelPremiacionAct
						.getListConcursoLotePremioArticulo();
				for (int j = 0; j < detalListLote.size(); j++) {
					concursoLotePremioArticulo = (ConcursoLotePremioArticulo) detalListLote
							.get(j);
					detalListArticulos.addAll(concursoLotePremioArticulo
							.getListConcursoArticuloLote());
				}
				this.incArticulosLoteList = detalListArticulos;
				dataTableDefinirPremio = new DataTableModel(incArticulosLoteList);

				resetDefinirPremio(f);
				actualizarLote(f);
				f.setNumeroUnidades("1");
				this.indRedefinirNivelPremiacion = "S";
				
				if (Constants.NRO_UNO.equals(f.getIndicadorNivelSelectivo())) {
					String codLote = "";
					if(incTipoLoteList.size()>0){
						Base dataLote = (Base)incTipoLoteList.get(0);
						codLote=dataLote.getCodigo();
						if(StringUtils.isBlank(codLote))
								indicadorPremiosWebBoolean=false;	
						
						if(!StringUtils.isBlank(f.getTipoLote())){
							String nivOpc = f.getTipoLote().split("\\__")[1];
							
							for (int i = 0; i < incArticulosLoteList.size(); i++) {
								ConcursoArticuloLote dataArt = (ConcursoArticuloLote)incArticulosLoteList.get(i);
								if(dataArt.getDescripcionLote().equals(nivOpc)){
									if(dataArt.getIndicadorPremiosWeb()==1)
										indicadorPremiosWebBoolean=true;
									else
										indicadorPremiosWebBoolean=false;	
									break;
								}					
							}
						}else
							indicadorPremiosWebBoolean=false;
					}
				}				
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			addError("Error", obtieneMensajeErrorException(e));
		}

	}

	public void deleteDefinirPremio(ActionEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'delete' method");
		}
		MantenimientoINCDefinirPremioForm f = (MantenimientoINCDefinirPremioForm) this.formDefinirPremio;
		ConcursoArticuloLote data = (ConcursoArticuloLote) this.beanRegistroDefinirPremio;
		MantenimientoINCConfiguracionConcursoService service = (MantenimientoINCConfiguracionConcursoService) getBean("spusicc.mantenimientoINCConfiguracionConcursoService");
		ConcursoParametrosGenerales concursoParametrosGenerales = this.incDtoConcurso;

		List detalList = this.incArticulosLoteList;
        try {
		if (data != null) {
			// VALIDAMOS SI EL PREMIO HA SIDO ATENDIDO, SI ES ASI, NO SE PUEDE
			// ELIMINAR
			String oidConcurso = "";
			if (concursoParametrosGenerales.getOid() != null)
				oidConcurso = concursoParametrosGenerales.getOid().toString();
			String codigoVenta = data.getCodigoVentaFicticio();
			if (StringUtils.isNotEmpty(codigoVenta)) {
				Map criteria = new HashMap();
				criteria.put("oidConcurso", oidConcurso);
				criteria.put("codigoVenta", codigoVenta);
				boolean tieneAtenciones = service
						.validaPremioAtendido(criteria);

				if (tieneAtenciones) {
					throw new Exception(
							this.getResourceMessage("mantenimientoINCDefinirPremioForm.msg.premioTieneAtenciones"));
				}
			}

			// MARCAMOS LOS REGISTROS SELECCIONADOS COMO ELIMINADOS
			// for(int i=0; i<incArticulosLoteList.length; i++) {
			// concursoArticuloLote =
			// (ConcursoArticuloLote)detalList.get(Integer.parseInt(arrSeleccionados[i]));
			// concursoArticuloLote.setEliminado(true);
			// }
			data.setEliminado(true);

			// RECORREMOS LOS LOTES DE ARTICULOS Y BORRAMOS LOS ARTICULOS QUE
			// ESTAN MARCADOS COMO ELIMINADOS
			List detalListAux = this.incNivelesPremiacionList;
			ConcursoNivelPremiacion concursoNivelPremiacionAct = null;

			for (int i = 0; i < detalListAux.size(); i++) {
				ConcursoNivelPremiacion concursoNivelPremiacion = (ConcursoNivelPremiacion) detalListAux
						.get(i);
				String numeroNivel = concursoNivelPremiacion.getNumeroNivel()
						.toString();

				if (numeroNivel.equals(f.getNumeroNivel()))
					concursoNivelPremiacionAct = concursoNivelPremiacion;
			}

			List detalListLote = concursoNivelPremiacionAct
					.getListConcursoLotePremioArticulo();
			for (int j = 0; j < detalListLote.size(); j++) {
				ConcursoLotePremioArticulo concursoLotePremioArticulo = (ConcursoLotePremioArticulo) detalListLote
						.get(j);
				List detalListArticulo = concursoLotePremioArticulo
						.getListConcursoArticuloLote();

				for (int k = 0; k < detalListArticulo.size(); k++) {
					data = (ConcursoArticuloLote) detalListArticulo.get(k);
					if (data.isEliminado())
						detalListArticulo.remove(k);
				}
			}

			// RECORREMOS LOS LOTES QUE NO TENGAN ARTICULOS Y LO BORRAMOS
			for (int j = 0; j < detalListLote.size(); j++) {
				ConcursoLotePremioArticulo concursoLotePremioArticulo = (ConcursoLotePremioArticulo) detalListLote
						.get(j);
				List detalListArticulo = concursoLotePremioArticulo
						.getListConcursoArticuloLote();

				if (detalListArticulo == null || detalListArticulo.size() == 0) {
					detalListLote.remove(j);
				}
			}

			// ACTUALIZAMOS LA LISTA DE ARTICULOS PERTENECIENTA AL NIVEL DE
			// PREMIACION QUE SE ESTA EDITANDO
			List detalListArticulos = new ArrayList();
			for (int j = 0; j < detalListLote.size(); j++) {
				ConcursoLotePremioArticulo concursoLotePremioArticulo = (ConcursoLotePremioArticulo) detalListLote
						.get(j);
				detalListArticulos.addAll(concursoLotePremioArticulo
						.getListConcursoArticuloLote());
			}

			f.setSelectedItems(null);
			this.incArticulosLoteList = detalListArticulos;
			dataTableDefinirPremio = new DataTableModel(incArticulosLoteList);
			this.indRedefinirNivelPremiacion = "S";
		}

		} catch (Exception e) {
			// TODO: handle exception
			addError("Error", obtieneMensajeErrorException(e));
	}

	}

	public void editDefinirPremio(ActionEvent event) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'edit' method");
			}

			MantenimientoINCDefinirPremioForm f = (MantenimientoINCDefinirPremioForm) this.formDefinirPremio;

			MantenimientoINCConfiguracionConcursoService service = (MantenimientoINCConfiguracionConcursoService) getBean("spusicc.mantenimientoINCConfiguracionConcursoService");
			ConcursoParametrosGenerales concursoParametrosGenerales = this.incDtoConcurso;

			ConcursoArticuloLote data = (ConcursoArticuloLote) this.beanRegistroDefinirPremio;

			if (data != null) 
                        {
				List list = this.incArticulosLoteList;
				ConcursoArticuloLote concursoArticuloLote = data;

				// VALIDAMOS SI EL PREMIO HA SIDO ATENDIDO, SI ES ASI, NO SE
				// PUEDE ELIMINAR
				String oidConcurso = concursoParametrosGenerales.getOid().toString();
				String codigoVenta = concursoArticuloLote.getCodigoVentaFicticio();

				if (StringUtils.isNotEmpty(codigoVenta)) 
                                {
					Map criteria = new HashMap();
					criteria.put("oidConcurso", oidConcurso);
					criteria.put("codigoVenta", codigoVenta);
					boolean tieneAtenciones = service.validaPremioAtendido(criteria);

					if (tieneAtenciones) {
						this.addError("", this.getResourceMessage("mantenimientoINCDefinirPremioForm.msg.premioTieneAtenciones2"));
						return;
					}
				}

				f.setModificar(true);

				f.setNumeroPremio(concursoArticuloLote.getNumeroPremio());
				f.setDescripcionLote(concursoArticuloLote.getDescripcionLote());

				f.setCodigoSAP(concursoArticuloLote.getCodigoSAP());
				f.setDescripcionSAP(concursoArticuloLote.getDescripcionProducto());
				f.setNumeroUnidades(reemplazarNulo(concursoArticuloLote.getNumeroUnidades()));

				f.setIndicadorCentroServicio(reemplazarNulo(concursoArticuloLote.getIndicadorCentroServicio()));
				f.setIndicadorPremiosWeb(reemplazarNulo(concursoArticuloLote.getIndicadorPremiosWeb()));
				if(f.getIndicadorPremiosWeb().equals("1"))
					indicadorPremiosWebBoolean = true;
				else
					indicadorPremiosWebBoolean = false;
				f.setNumeroMesesGarantia(reemplazarNulo(concursoArticuloLote.getNumeroMesesGarantia()));
				f.setIndicadorTipoEntrega(concursoArticuloLote.getIndicadorTipoEntrega());

				f.setOidCentroServGarantia(reemplazarNulo(f.getOidCentroServGarantia()));
				f.setObservaciones(concursoArticuloLote.getObservaciones());
				f.setOidCentroServEntrega(reemplazarNulo(f.getOidCentroServEntrega()));
				f.setPrecio(reemplazarNulo(concursoArticuloLote.getPrecioPublico()));

				List detalListAux = this.incNivelesPremiacionList;

				for (int i = 0; i < detalListAux.size(); i++) 
                                {
					ConcursoNivelPremiacion concursoNivelPremiacion = (ConcursoNivelPremiacion) detalListAux.get(i);
					String numeroNivel = concursoNivelPremiacion.getNumeroNivel().toString();

					if (numeroNivel.equals(f.getNumeroNivel())) {
						List detalListLote = concursoNivelPremiacion.getListConcursoLotePremioArticulo();
						boolean encontrado = false;

						for (int j = 0; j < detalListLote.size(); j++) 
                                                {
							ConcursoLotePremioArticulo concursoLotePremioArticulo = (ConcursoLotePremioArticulo) detalListLote.get(j);

							if (concursoLotePremioArticulo.getNumeroPremio().toString().equals(f.getNumeroPremio())) 
                                                        {
								f.setNumeroLote(reemplazarNulo(concursoLotePremioArticulo.getNumeroLote()));
								f.setTipoLote(f.getNumeroLote() + "__" + f.getDescripcionLote() + "__"
										+ f.getNumeroPremio());
								break;
							}
						}
						break;
					}
				}

				f.setSelectedItems(null);
				this.idPremio = data;
				this.mostrarBotones = true;
			} else
				this.addWarn("Advertencia: ", this.getResourceMessage("errors.select.item"));
		} catch (Exception e) {
			this.addError("", this.obtieneMensajeErrorException(e));
		}
	}

	public void saveDefinirPremio(ActionEvent event) {
		try {

			if (log.isDebugEnabled()) {
				log.debug("Entering 'saveDefinirPremio' method");
			}
			
			Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
			ConcursoArticuloLote data = this.idPremio;
			MantenimientoINCDefinirPremioForm f = (MantenimientoINCDefinirPremioForm) this.formDefinirPremio;
			
			if (indicadorPremiosWebBoolean)
				f.setIndicadorPremiosWeb("1");
			else
				f.setIndicadorPremiosWeb("0");
			
			String descripcionProducto = "";

			// Validamos el codigo SAP ingresado
			Map criteriaBusqueda = new HashMap();
			criteriaBusqueda.put("codigoSAP", f.getCodigoSAP());
			criteriaBusqueda.put("codigoPais", pais.getCodigo());

			MantenimientoRECProductosFFNNEEService service = (MantenimientoRECProductosFFNNEEService) getBean("spusicc.mantenimientoRECProductosFFNNEEService");
			String oidProducto = service.getOidProducto(criteriaBusqueda);
			criteriaBusqueda.put("oidProducto", oidProducto);

			if (oidProducto == null) {
				this.addError(
						"Error: ",
						this.getResourceMessage("mantenimientoINCDefinirPremioForm.msg.codigoProductoNoExiste"));
				return;
			} else {
				criteriaBusqueda.put("oidProducto", oidProducto);
				descripcionProducto = service
						.getDescripcionProducto(criteriaBusqueda);

				List list = this.incArticulosLoteList;
				ConcursoArticuloLote concursoArticuloLote = data;

				concursoArticuloLote.setCodigoSAP(f.getCodigoSAP());
				concursoArticuloLote
						.setDescripcionProducto(descripcionProducto);
				concursoArticuloLote.setNumeroUnidades(Integer.parseInt(f
						.getNumeroUnidades()));
				concursoArticuloLote.setIndicadorPremiosWeb(Integer.parseInt(f.getIndicadorPremiosWeb()));

				if (StringUtils.isNotEmpty(f.getPrecio()))
					concursoArticuloLote.setPrecioPublico(new BigDecimal(f
							.getPrecio()));
				else
					concursoArticuloLote
							.setPrecioPublico(new BigDecimal("0.00"));

				resetDefinirPremio(f);
				f.setTipoLote("");
				actualizarLote(f);
				this.mostrarBotones = false;

				this.indRedefinirNivelPremiacion = "S";
				
				for (int i = 0; i < list.size(); i++) {
					ConcursoArticuloLote conArt = (ConcursoArticuloLote)list.get(i);
					if(conArt.getDescripcionLote().equals(data.getDescripcionLote()))
						conArt.setIndicadorPremiosWeb(Integer.parseInt((f.getIndicadorPremiosWeb())));
				}
			}
		} catch (Exception e) {
			this.addError("", this.obtieneMensajeErrorException(e));
		}
	}

	public void cancelarDefinirPremio(ActionEvent event) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'cancelar' method");
			}
			MantenimientoINCDefinirPremioForm f = (MantenimientoINCDefinirPremioForm) this.formDefinirPremio;

			resetDefinirPremio(f);
			f.setTipoLote("");
			actualizarLote(f);
			this.mostrarBotones = false;

		} catch (Exception e) {
			this.addError("", this.obtieneMensajeErrorException(e));
		}
	}

	public void resetDefinirPremio(MantenimientoINCDefinirPremioForm f) {
		f.setCodigoSAP(null);
		f.setDescripcionSAP(null);
		f.setOidCentroServGarantia(null);
		f.setNumeroMesesGarantia(null);
		f.setObservaciones(null);
		f.setOidCentroServEntrega(null);

		f.setIndicadorCentroServicio(Constants.NUMERO_CERO);
		f.setIndicadorTipoEntrega(Constants.INC_TIPO_ENTREGA_BELCORP);

		f.setNumeroLoteUltimo(null);
		f.setNumeroPremioUltimo(null);
		f.setDescripcionLoteUltimo(null);

		f.setModificar(false);
		f.setPrecio("0.00");
	}

	public void actualizarLotePremio(ValueChangeEvent event) {
		String valor = (String) event.getNewValue();
		actualizarLotePremio(valor);
		
		if(!StringUtils.isBlank(valor)){
			String nivOpc = valor.split("\\__")[1];
			
			for (int i = 0; i < incArticulosLoteList.size(); i++) {
				ConcursoArticuloLote dataArt = (ConcursoArticuloLote)incArticulosLoteList.get(i);
				if(dataArt.getDescripcionLote().equals(nivOpc)){
					if(dataArt.getIndicadorPremiosWeb()==1)
						indicadorPremiosWebBoolean=true;
					else
						indicadorPremiosWebBoolean=false;	
					break;
				}					
			}
		}else
			indicadorPremiosWebBoolean=false;
	}

	private void actualizarLotePremio(String valor) {
		MantenimientoINCDefinirPremioForm f = (MantenimientoINCDefinirPremioForm) this.formDefinirPremio;

		if (StringUtils.isBlank(valor)) {
			f.setNumeroLote(f.getNumeroLoteUltimo());
			f.setDescripcionLote(f.getDescripcionLoteUltimo());
			f.setNumeroPremio(f.getNumeroPremioUltimo());
		} else {
			String elementos[] = valor.split("\\__");
			f.setNumeroLote(elementos[0]);
			f.setDescripcionLote(elementos[1]);
			f.setNumeroPremio(elementos[2]);
		}
	}

	private void actualizarLote(MantenimientoINCDefinirPremioForm f) {
		List detalList = this.incNivelesPremiacionList;
		List listTipoLote = new ArrayList();

		ConcursoNivelPremiacion concursoNivelPremiacionAct = null;
		int ultimoNumeroPremio = 0;

		for (int i = 0; i < detalList.size(); i++) {
			ConcursoNivelPremiacion concursoNivelPremiacion = (ConcursoNivelPremiacion) detalList
					.get(i);
			int numeroNivel = concursoNivelPremiacion.getNumeroNivel()
					.intValue();

			if (numeroNivel == Integer.parseInt(f.getNumeroNivel())) {
				concursoNivelPremiacionAct = concursoNivelPremiacion;
				f.setDescripcionLote("Nivel " + f.getNumeroNivel());

				if (concursoNivelPremiacion.getListConcursoLotePremioArticulo() == null) {
					concursoNivelPremiacion
							.setListConcursoLotePremioArticulo(new ArrayList());
				}

				// numeroLote es un correlativo por nivel, si el nivel es
				// elegible
				if (f.getIndicadorNivelSelectivo().equals(Constants.NRO_UNO)) {
					List listAux = concursoNivelPremiacion
							.getListConcursoLotePremioArticulo();
					if (listAux.size() > 0)
						f.setNumeroLote(String
								.valueOf(((ConcursoLotePremioArticulo) listAux
										.get(listAux.size() - 1))
										.getNumeroLote() + 1));
					else
						f.setNumeroLote(String.valueOf(1));

					f.setDescripcionLote(f.getDescripcionLote() + " - Opc "
							+ f.getNumeroLote());
				} else {
					if (concursoNivelPremiacion
							.getListConcursoLotePremioArticulo().size() > 0) {
						ConcursoLotePremioArticulo concursoLotePremioArticulo = (ConcursoLotePremioArticulo) concursoNivelPremiacion
								.getListConcursoLotePremioArticulo().get(0);
						f.setDescripcionLote(concursoLotePremioArticulo
								.getDescripcionLote());
					}
					f.setNumeroLote(Constants.NRO_UNO);
				}

				List detalListAux = concursoNivelPremiacion
						.getListConcursoLotePremioArticulo();

				for (int j = 0; j < detalListAux.size(); j++) {
					ConcursoLotePremioArticulo concursoLotePremioArticulo = (ConcursoLotePremioArticulo) detalListAux
							.get(j);

					Base base = new Base();
					String numeroLote = concursoLotePremioArticulo
							.getNumeroLote().toString();
					String descripcionLote = concursoLotePremioArticulo
							.getDescripcionLote();
					String numeroPremio = concursoLotePremioArticulo
							.getNumeroPremio().toString();

					base.setCodigo(numeroLote + "__" + descripcionLote + "__"
							+ numeroPremio);
					base.setDescripcion(concursoLotePremioArticulo
							.getDescripcionLote());
					listTipoLote.add(base);
				}

				if ((Constants.NRO_UNO.equals(f.getIndicadorNivelSelectivo()))
						|| (!Constants.NRO_UNO.equals(f
								.getIndicadorNivelSelectivo()) && detalListAux
								.size() == 0)) {
					Base baseVacio = new Base();
					baseVacio.setCodigo("");
					baseVacio.setDescripcion("");

					listTipoLote.add(0, baseVacio);
				}
			}

			List detalListAux = concursoNivelPremiacion
					.getListConcursoLotePremioArticulo();

			for (int j = 0; j < detalListAux.size(); j++) {
				ConcursoLotePremioArticulo concursoLotePremioArticulo = (ConcursoLotePremioArticulo) detalListAux
						.get(j);

				if ((i == 0) && (j == 0)) {
					ultimoNumeroPremio = concursoLotePremioArticulo
							.getNumeroPremio().intValue();
				} else {
					// el numero de premio debe ser calculado en base al ultimo
					// premio
					if (concursoLotePremioArticulo.getNumeroPremio().intValue() > ultimoNumeroPremio) {
						ultimoNumeroPremio = concursoLotePremioArticulo
								.getNumeroPremio().intValue();
					}
				}

			}

		}

		// numeroLote es un correlativo por nivel, si el nivel es elegible
		if (Constants.NRO_UNO.equals(f.getIndicadorNivelSelectivo())) {
			ultimoNumeroPremio++;
			f.setNumeroPremio(String.valueOf(ultimoNumeroPremio));
		} else {
			if (concursoNivelPremiacionAct.getListConcursoLotePremioArticulo()
					.size() == 0) {
				ultimoNumeroPremio++;
				f.setNumeroPremio(String.valueOf(ultimoNumeroPremio));
			} else {
				ConcursoLotePremioArticulo concursoLotePremioArticulo = (ConcursoLotePremioArticulo) concursoNivelPremiacionAct
						.getListConcursoLotePremioArticulo().get(0);
				f.setNumeroPremio(String.valueOf(concursoLotePremioArticulo
						.getNumeroPremio()));
			}

			if (ultimoNumeroPremio == 0) {
				ultimoNumeroPremio++;
				f.setNumeroPremio(String.valueOf(ultimoNumeroPremio));
			}
		}

		f.setNumeroLoteUltimo(f.getNumeroLote());
		f.setDescripcionLoteUltimo(f.getDescripcionLote());
		f.setNumeroPremioUltimo(f.getNumeroPremio());

		// carga el metodo actualizarLotePremio
		actualizarLotePremio(f.getTipoLote());
		this.incTipoLoteList = listTipoLote;
	}

	// ---------------- FIN DEFINIR PREMIO ------------------------------------------

	public void insertProducto(ActionEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'insertProducto' method");
		}
		MantenimientoINCConfiguracionConcursoForm f = (MantenimientoINCConfiguracionConcursoForm) this.formMantenimiento;

		MantenimientoRECProductosFFNNEEService service = (MantenimientoRECProductosFFNNEEService) getBean("spusicc.mantenimientoRECProductosFFNNEEService");
		MantenimientoINCConfiguracionConcursoService serviceInc = (MantenimientoINCConfiguracionConcursoService) getBean("spusicc.mantenimientoINCConfiguracionConcursoService");

		try {
			validarInsertProductos();
			String oidTipoProducto = f.getOidTipoProducto();
			String codigoBloqueProducto = f.getCodigoBloqueProducto();

			List listTiposProducto = this.incTipoProductoList;
			List listTiposOferta = this.incTipoOfertaList;
			List listTiposAgrupacion = this.incTipoAgrupacionList;
			List listUnidadesNegocio = this.incUnidadNegocioList;
			List listNegocios = this.incNegocioList;
			List listCiclosVida = this.incCicloVidaList;
			List listMarcaProductos = this.incMarcaProductoList;

			List detalList = null;
			ConcursoProductosValidos concursoProductosValidos = null;
			ConcursoProductosBonificados concursoProductosBonificados = null;
			ConcursoProductosExcluidos concursoProductosExcluidos = null;
			ConcursoProductosExigidos concursoProductosExigidos = null;

			if (oidTipoProducto.equals(Constants.INC_OID_PRODUCTOS_VALIDOS)) {
				detalList = this.incProductosValidosList;
				concursoProductosValidos = new ConcursoProductosValidos();

				if (codigoBloqueProducto.equals(Constants.INC_BLOQUE_NEGOCIO)) {
					concursoProductosValidos.setOidMarcaProducto(new Long(f
							.getOidMarcaProducto()));

					if (!StringUtils.isEmpty(f.getOidUnidadNegocio()))
						concursoProductosValidos.setOidUnidadNegocio(new Long(f
								.getOidUnidadNegocio()));

					if (!StringUtils.isEmpty(f.getOidNegocio()))
						concursoProductosValidos.setOidNegocio(new Long(f
								.getOidNegocio()));

					if (!StringUtils.isEmpty(f.getOidSuperGenerico()))
						concursoProductosValidos.setOidSuperGenerico(new Long(f
								.getOidSuperGenerico()));

					if (!StringUtils.isEmpty(f.getOidGenerico()))
						concursoProductosValidos.setOidGenerico(new Long(f
								.getOidGenerico()));

					concursoProductosValidos.setDescripcionSuperGenerico(f
							.getDescripcionSuperGenerico());
					concursoProductosValidos.setDescripcionGenerico(f
							.getDescripcionGenerico());
					concursoProductosValidos
							.setDescripcionMarcaProducto(obtenerDescripcionLista(
									f.getOidMarcaProducto(), listMarcaProductos));
					concursoProductosValidos
							.setDescripcionNegocio(obtenerDescripcionLista(
									f.getOidNegocio(), listNegocios));
					concursoProductosValidos
							.setDescripcionUnidadNegocio(obtenerDescripcionLista(
									f.getOidUnidadNegocio(),
									listUnidadesNegocio));

					concursoProductosValidos.setCodigoSuperGenerico(f
							.getCodigoSuperGenerico());
					concursoProductosValidos.setCodigoGenerico(f
							.getCodigoGenerico());
				}
				if (codigoBloqueProducto.equals(Constants.INC_BLOQUE_OFERTA)) {
					concursoProductosValidos.setOidTipoOferta(new Long(f
							.getOidTipoOferta()));

					if (!StringUtils.isEmpty(f.getOidCicloVida()))
						concursoProductosValidos.setOidCicloVida(new Long(f
								.getOidCicloVida()));

					concursoProductosValidos
							.setDescripcionTipoOferta(obtenerDescripcionLista(
									f.getOidTipoOferta(), listTiposOferta));
					concursoProductosValidos
							.setDescripcionCicloVida(obtenerDescripcionLista(
									f.getOidCicloVida(), listCiclosVida));
				}
				if (codigoBloqueProducto.equals(Constants.INC_BLOQUE_PRODUCTO)) {
					// Validamos el codigo SAP ingresado
					Map criteriaBusqueda = new HashMap();
					criteriaBusqueda.put("codigoSAP", f.getCodigoSAP());
					criteriaBusqueda.put("codigoPais", f.getCodigoPais());

					String oidProducto = service
							.getOidProducto(criteriaBusqueda);
					criteriaBusqueda.put("oidProducto", oidProducto);

					if (oidProducto == null) {
						throw new Exception(
								this.getResourceMessage("mantenimientoINCDefinirPremioForm.msg.codigoProductoNoExiste"));

					} else {
						criteriaBusqueda.put("oidProducto", oidProducto);
						concursoProductosValidos.setDescripcionProducto(service
								.getDescripcionProducto(criteriaBusqueda));
					}

					concursoProductosValidos.setCodigoSAP(f.getCodigoSAP());

					if (!StringUtils.isEmpty(f.getOidTipoOferta()))
						concursoProductosValidos.setOidTipoOferta(new Long(f
								.getOidTipoOferta()));

					if (!StringUtils.isEmpty(f.getOidCicloVida()))
						concursoProductosValidos.setOidCicloVida(new Long(f
								.getOidCicloVida()));

					concursoProductosValidos
							.setDescripcionTipoOferta(obtenerDescripcionLista(
									f.getOidTipoOferta(), listTiposOferta));
					concursoProductosValidos
							.setDescripcionCicloVida(obtenerDescripcionLista(
									f.getOidCicloVida(), listCiclosVida));
				}
				if (codigoBloqueProducto.equals(Constants.INC_BLOQUE_CUV)) {
					// Validamos el CUV ingresado
					Map criteriaBusqueda = new HashMap();
					criteriaBusqueda.put("codigoPeriodo",
							f.getCodigoPeriodoCUV());
					criteriaBusqueda.put("CUV", f.getCUV());

					Map mapDetalleOferta = serviceInc
							.getDetalleOfertaCUV(criteriaBusqueda);

					if (mapDetalleOferta == null) {
						throw new Exception(
								this.getResourceMessage("mantenimientoINCDefinirPremioForm.msg.codigoCUVNoExiste"));

					}

					String oidDetalleOferta = mapDetalleOferta.get(
							"oidDetalleOferta").toString();

					concursoProductosValidos
							.setOidDetalleOferta(oidDetalleOferta);
					concursoProductosValidos.setDescripcionCUV(f
							.getCodigoPeriodoCUV() + " - " + f.getCUV());
					concursoProductosValidos
							.setCodigoSAP((String) mapDetalleOferta
									.get("codigoSAP"));
					concursoProductosValidos
							.setDescripcionTipoOferta((String) mapDetalleOferta
									.get("descripcionTipoOferta"));
					concursoProductosValidos
							.setDescripcionProducto((String) mapDetalleOferta
									.get("descripcionProducto"));
				}

				detalList.add(concursoProductosValidos);
				this.incProductosValidosList = detalList;
				// GENERAR INDEX
				generarIndexValidos(incProductosValidosList);

				dataTableProductosValidos = new DataTableModel(
						this.incProductosValidosList);
				f.setIndRedefinirProductosValidos(true);
			}

			if (oidTipoProducto.equals(Constants.INC_OID_PRODUCTOS_BONIFICADOS)) {
				detalList = this.incProductosBonificadosList;
				concursoProductosBonificados = new ConcursoProductosBonificados();

				if (!StringUtils.isEmpty(f.getPuntosUnidad()))
					concursoProductosBonificados.setPuntosUnidad(new Integer(f
							.getPuntosUnidad()));
				if (!StringUtils.isEmpty(f.getFactorMultiplicador()))
					concursoProductosBonificados
							.setFactorMultiplicador(new Integer(f
									.getFactorMultiplicador()));

				concursoProductosBonificados.setCodigoPeriodoDesde(f
						.getCodigoPeriodoDesde());
				concursoProductosBonificados.setCodigoPeriodoHasta(f
						.getCodigoPeriodoHasta());

				if (codigoBloqueProducto.equals(Constants.INC_BLOQUE_NEGOCIO)) {
					concursoProductosBonificados.setOidMarcaProducto(new Long(f
							.getOidMarcaProducto()));

					if (!StringUtils.isEmpty(f.getOidUnidadNegocio()))
						concursoProductosBonificados
								.setOidUnidadNegocio(new Long(f
										.getOidUnidadNegocio()));

					if (!StringUtils.isEmpty(f.getOidNegocio()))
						concursoProductosBonificados.setOidNegocio(new Long(f
								.getOidNegocio()));

					if (!StringUtils.isEmpty(f.getOidSuperGenerico()))
						concursoProductosBonificados
								.setOidSuperGenerico(new Long(f
										.getOidSuperGenerico()));

					if (!StringUtils.isEmpty(f.getOidGenerico()))
						concursoProductosBonificados.setOidGenerico(new Long(f
								.getOidGenerico()));

					concursoProductosBonificados.setDescripcionSuperGenerico(f
							.getDescripcionSuperGenerico());
					concursoProductosBonificados.setDescripcionGenerico(f
							.getDescripcionGenerico());
					concursoProductosBonificados
							.setDescripcionMarcaProducto(obtenerDescripcionLista(
									f.getOidMarcaProducto(), listMarcaProductos));
					concursoProductosBonificados
							.setDescripcionNegocio(obtenerDescripcionLista(
									f.getOidNegocio(), listNegocios));
					concursoProductosBonificados
							.setDescripcionUnidadNegocio(obtenerDescripcionLista(
									f.getOidUnidadNegocio(),
									listUnidadesNegocio));

					concursoProductosBonificados.setCodigoSuperGenerico(f
							.getCodigoSuperGenerico());
					concursoProductosBonificados.setCodigoGenerico(f
							.getCodigoGenerico());
				}
				if (codigoBloqueProducto.equals(Constants.INC_BLOQUE_OFERTA)) {
					concursoProductosBonificados.setOidTipoOferta(new Long(f
							.getOidTipoOferta()));

					if (!StringUtils.isEmpty(f.getOidCicloVida()))
						concursoProductosBonificados.setOidCicloVida(new Long(f
								.getOidCicloVida()));

					concursoProductosBonificados
							.setDescripcionTipoOferta(obtenerDescripcionLista(
									f.getOidTipoOferta(), listTiposOferta));
					concursoProductosBonificados
							.setDescripcionCicloVida(obtenerDescripcionLista(
									f.getOidCicloVida(), listCiclosVida));
				}
				if (codigoBloqueProducto.equals(Constants.INC_BLOQUE_PRODUCTO)) {
					// Validamos el codigo SAP ingresado
					Map criteriaBusqueda = new HashMap();
					criteriaBusqueda.put("codigoSAP", f.getCodigoSAP());
					criteriaBusqueda.put("codigoPais", f.getCodigoPais());

					String oidProducto = service
							.getOidProducto(criteriaBusqueda);
					criteriaBusqueda.put("oidProducto", oidProducto);

					if (oidProducto == null) {
						throw new Exception(
								this.getResourceMessage("mantenimientoINCDefinirPremioForm.msg.codigoProductoNoExiste"));

					} else {
						criteriaBusqueda.put("oidProducto", oidProducto);
						concursoProductosBonificados
								.setDescripcionProducto(service
										.getDescripcionProducto(criteriaBusqueda));
					}

					concursoProductosBonificados.setCodigoSAP(f.getCodigoSAP());

					if (!StringUtils.isEmpty(f.getOidTipoOferta()))
						concursoProductosBonificados.setOidTipoOferta(new Long(
								f.getOidTipoOferta()));

					if (!StringUtils.isEmpty(f.getOidCicloVida()))
						concursoProductosBonificados.setOidCicloVida(new Long(f
								.getOidCicloVida()));

					concursoProductosBonificados
							.setDescripcionTipoOferta(obtenerDescripcionLista(
									f.getOidTipoOferta(), listTiposOferta));
					concursoProductosBonificados
							.setDescripcionCicloVida(obtenerDescripcionLista(
									f.getOidCicloVida(), listCiclosVida));
				}
				if (codigoBloqueProducto.equals(Constants.INC_BLOQUE_CUV)) {
					// Validamos el CUV ingresado
					Map criteriaBusqueda = new HashMap();
					criteriaBusqueda.put("codigoPeriodo",
							f.getCodigoPeriodoCUV());
					criteriaBusqueda.put("CUV", f.getCUV());

					Map mapDetalleOferta = serviceInc
							.getDetalleOfertaCUV(criteriaBusqueda);

					if (mapDetalleOferta == null) {
						throw new Exception(
								this.getResourceMessage("mantenimientoINCDefinirPremioForm.msg.codigoCUVNoExiste"));

					}

					String oidDetalleOferta = mapDetalleOferta.get(
							"oidDetalleOferta").toString();
					concursoProductosBonificados
							.setOidDetalleOferta(oidDetalleOferta);
					concursoProductosBonificados.setCodigoPeriodoDesde(f
							.getCodigoPeriodoCUV());
					concursoProductosBonificados.setCodigoPeriodoHasta(f
							.getCodigoPeriodoCUV());
					concursoProductosBonificados.setDescripcionCUV(f
							.getCodigoPeriodoCUV() + " - " + f.getCUV());
					concursoProductosBonificados
							.setCodigoSAP((String) mapDetalleOferta
									.get("codigoSAP"));
					concursoProductosBonificados
							.setDescripcionTipoOferta((String) mapDetalleOferta
									.get("descripcionTipoOferta"));
					concursoProductosBonificados
							.setDescripcionProducto((String) mapDetalleOferta
									.get("descripcionProducto"));
				}

				detalList.add(concursoProductosBonificados);
				this.incProductosBonificadosList = detalList;
				// GENERAR INDEX
				generarIndexBonificacion(incProductosBonificadosList);

				dataTableProductosBonificacion = new DataTableModel(
						this.incProductosBonificadosList);
				f.setIndRedefinirProductosBonificados(true);
			}

			if (oidTipoProducto.equals(Constants.INC_OID_PRODUCTOS_EXCLUIDOS)) {
				detalList = this.incProductosExcluidosList;
				concursoProductosExcluidos = new ConcursoProductosExcluidos();

				concursoProductosExcluidos.setCodigoPeriodoDesde(f
						.getCodigoPeriodoDesde());
				concursoProductosExcluidos.setCodigoPeriodoHasta(f
						.getCodigoPeriodoHasta());

				if (codigoBloqueProducto.equals(Constants.INC_BLOQUE_NEGOCIO)) {
					concursoProductosExcluidos.setOidMarcaProducto(new Long(f
							.getOidMarcaProducto()));

					if (!StringUtils.isEmpty(f.getOidUnidadNegocio()))
						concursoProductosExcluidos
								.setOidUnidadNegocio(new Long(f
										.getOidUnidadNegocio()));

					if (!StringUtils.isEmpty(f.getOidNegocio()))
						concursoProductosExcluidos.setOidNegocio(new Long(f
								.getOidNegocio()));

					if (!StringUtils.isEmpty(f.getOidSuperGenerico()))
						concursoProductosExcluidos
								.setOidSuperGenerico(new Long(f
										.getOidSuperGenerico()));

					if (!StringUtils.isEmpty(f.getOidGenerico()))
						concursoProductosExcluidos.setOidGenerico(new Long(f
								.getOidGenerico()));

					concursoProductosExcluidos.setDescripcionSuperGenerico(f
							.getDescripcionSuperGenerico());
					concursoProductosExcluidos.setDescripcionGenerico(f
							.getDescripcionGenerico());
					concursoProductosExcluidos
							.setDescripcionMarcaProducto(obtenerDescripcionLista(
									f.getOidMarcaProducto(), listMarcaProductos));
					concursoProductosExcluidos
							.setDescripcionNegocio(obtenerDescripcionLista(
									f.getOidNegocio(), listNegocios));
					concursoProductosExcluidos
							.setDescripcionUnidadNegocio(obtenerDescripcionLista(
									f.getOidUnidadNegocio(),
									listUnidadesNegocio));

					concursoProductosExcluidos.setCodigoSuperGenerico(f
							.getCodigoSuperGenerico());
					concursoProductosExcluidos.setCodigoGenerico(f
							.getCodigoGenerico());
				}
				if (codigoBloqueProducto.equals(Constants.INC_BLOQUE_OFERTA)) {
					concursoProductosExcluidos.setOidTipoOferta(new Long(f
							.getOidTipoOferta()));

					if (!StringUtils.isEmpty(f.getOidCicloVida()))
						concursoProductosExcluidos.setOidCicloVida(new Long(f
								.getOidCicloVida()));

					concursoProductosExcluidos
							.setDescripcionTipoOferta(obtenerDescripcionLista(
									f.getOidTipoOferta(), listTiposOferta));
					concursoProductosExcluidos
							.setDescripcionCicloVida(obtenerDescripcionLista(
									f.getOidCicloVida(), listCiclosVida));
				}
				if (codigoBloqueProducto.equals(Constants.INC_BLOQUE_PRODUCTO)) {
					// Validamos el codigo SAP ingresado
					Map criteriaBusqueda = new HashMap();
					criteriaBusqueda.put("codigoSAP", f.getCodigoSAP());
					criteriaBusqueda.put("codigoPais", f.getCodigoPais());

					String oidProducto = service
							.getOidProducto(criteriaBusqueda);
					criteriaBusqueda.put("oidProducto", oidProducto);

					if (oidProducto == null) {
						throw new Exception(
								this.getResourceMessage("mantenimientoINCDefinirPremioForm.msg.codigoProductoNoExiste"));

					} else {
						criteriaBusqueda.put("oidProducto", oidProducto);
						concursoProductosExcluidos
								.setDescripcionProducto(service
										.getDescripcionProducto(criteriaBusqueda));
					}

					concursoProductosExcluidos.setCodigoSAP(f.getCodigoSAP());

					if (!StringUtils.isEmpty(f.getOidTipoOferta()))
						concursoProductosExcluidos.setOidTipoOferta(new Long(f
								.getOidTipoOferta()));

					if (!StringUtils.isEmpty(f.getOidCicloVida()))
						concursoProductosExcluidos.setOidCicloVida(new Long(f
								.getOidCicloVida()));

					concursoProductosExcluidos
							.setDescripcionTipoOferta(obtenerDescripcionLista(
									f.getOidTipoOferta(), listTiposOferta));
					concursoProductosExcluidos
							.setDescripcionCicloVida(obtenerDescripcionLista(
									f.getOidCicloVida(), listCiclosVida));
				}
				if (codigoBloqueProducto.equals(Constants.INC_BLOQUE_CUV)) {
					// Validamos el CUV ingresado
					Map criteriaBusqueda = new HashMap();
					criteriaBusqueda.put("codigoPeriodo",
							f.getCodigoPeriodoCUV());
					criteriaBusqueda.put("CUV", f.getCUV());

					Map mapDetalleOferta = serviceInc
							.getDetalleOfertaCUV(criteriaBusqueda);

					if (mapDetalleOferta == null) {
						throw new Exception(
								this.getResourceMessage("mantenimientoINCDefinirPremioForm.msg.codigoCUVNoExiste"));

					}

					String oidDetalleOferta = mapDetalleOferta.get(
							"oidDetalleOferta").toString();
					concursoProductosExcluidos
							.setOidDetalleOferta(oidDetalleOferta);
					concursoProductosExcluidos.setCodigoPeriodoDesde(f
							.getCodigoPeriodoCUV());
					concursoProductosExcluidos.setCodigoPeriodoHasta(f
							.getCodigoPeriodoCUV());
					concursoProductosExcluidos.setDescripcionCUV(f
							.getCodigoPeriodoCUV() + " - " + f.getCUV());
					concursoProductosExcluidos
							.setCodigoSAP((String) mapDetalleOferta
									.get("codigoSAP"));
					concursoProductosExcluidos
							.setDescripcionTipoOferta((String) mapDetalleOferta
									.get("descripcionTipoOferta"));
					concursoProductosExcluidos
							.setDescripcionProducto((String) mapDetalleOferta
									.get("descripcionProducto"));
				}

				detalList.add(concursoProductosExcluidos);
				this.incProductosExcluidosList = detalList;
				// GENERAR INDEX
				generarIndexExcluidos(incProductosExcluidosList);

				dataTableProductosExcluidos = new DataTableModel(
						this.incProductosExcluidosList);
				f.setIndRedefinirProductosExcluidos(true);
			}
			if (oidTipoProducto.equals(Constants.INC_OID_PRODUCTOS_EXIGIDOS)) {
				detalList = this.incProductosExigidosList;
				concursoProductosExigidos = new ConcursoProductosExigidos();

				if (!StringUtils.isEmpty(f.getUnidadesExigidas()))
					concursoProductosExigidos.setUnidadesExigidas(new Integer(f
							.getUnidadesExigidas()));
				if (!StringUtils.isEmpty(f.getMontoExigido()))
					concursoProductosExigidos.setMontoExigido(new BigDecimal(f
							.getMontoExigido()));
				if (!StringUtils.isEmpty(f.getPuntosExigidos()))
					concursoProductosExigidos.setPuntosExigidos(new Integer(f
							.getPuntosExigidos()));

				concursoProductosExigidos.setCodigoPeriodoDesde(f
						.getCodigoPeriodoDesde());
				concursoProductosExigidos.setCodigoPeriodoHasta(f
						.getCodigoPeriodoHasta());

				if (codigoBloqueProducto.equals(Constants.INC_BLOQUE_NEGOCIO)) {
					concursoProductosExigidos.setOidMarcaProducto(new Long(f
							.getOidMarcaProducto()));

					if (!StringUtils.isEmpty(f.getOidUnidadNegocio()))
						concursoProductosExigidos.setOidUnidadNegocio(new Long(
								f.getOidUnidadNegocio()));

					if (!StringUtils.isEmpty(f.getOidNegocio()))
						concursoProductosExigidos.setOidNegocio(new Long(f
								.getOidNegocio()));

					if (!StringUtils.isEmpty(f.getOidSuperGenerico()))
						concursoProductosExigidos.setOidSuperGenerico(new Long(
								f.getOidSuperGenerico()));

					if (!StringUtils.isEmpty(f.getOidGenerico()))
						concursoProductosExigidos.setOidGenerico(new Long(f
								.getOidGenerico()));

					concursoProductosExigidos.setDescripcionSuperGenerico(f
							.getDescripcionSuperGenerico());
					concursoProductosExigidos.setDescripcionGenerico(f
							.getDescripcionGenerico());
					concursoProductosExigidos
							.setDescripcionMarcaProducto(obtenerDescripcionLista(
									f.getOidMarcaProducto(), listMarcaProductos));
					concursoProductosExigidos
							.setDescripcionNegocio(obtenerDescripcionLista(
									f.getOidNegocio(), listNegocios));
					concursoProductosExigidos
							.setDescripcionUnidadNegocio(obtenerDescripcionLista(
									f.getOidUnidadNegocio(),
									listUnidadesNegocio));

					concursoProductosExigidos.setCodigoSuperGenerico(f
							.getCodigoSuperGenerico());
					concursoProductosExigidos.setCodigoGenerico(f
							.getCodigoGenerico());

					if (!StringUtils.isEmpty(f.getOidTipoAgrupacionNegocio())) {
						concursoProductosExigidos.setIndicadorAgrupacion(f
								.getOidTipoAgrupacionNegocio());
						concursoProductosExigidos
								.setDescripcionAgrupacion(obtenerDescripcionLista(
										f.getOidTipoAgrupacionNegocio(),
										listTiposAgrupacion));
					}
				}
				if (codigoBloqueProducto.equals(Constants.INC_BLOQUE_OFERTA)) {
					concursoProductosExigidos.setOidTipoOferta(new Long(f
							.getOidTipoOferta()));

					if (!StringUtils.isEmpty(f.getOidCicloVida()))
						concursoProductosExigidos.setOidCicloVida(new Long(f
								.getOidCicloVida()));

					concursoProductosExigidos
							.setDescripcionTipoOferta(obtenerDescripcionLista(
									f.getOidTipoOferta(), listTiposOferta));
					concursoProductosExigidos
							.setDescripcionCicloVida(obtenerDescripcionLista(
									f.getOidCicloVida(), listCiclosVida));

					if (!StringUtils.isEmpty(f.getOidTipoAgrupacionOferta())) {
						concursoProductosExigidos.setIndicadorAgrupacion(f
								.getOidTipoAgrupacionOferta());
						concursoProductosExigidos
								.setDescripcionAgrupacion(obtenerDescripcionLista(
										f.getOidTipoAgrupacionOferta(),
										listTiposAgrupacion));
					}

				}
				if (codigoBloqueProducto.equals(Constants.INC_BLOQUE_PRODUCTO)) {
					// Validamos el codigo SAP ingresado
					Map criteriaBusqueda = new HashMap();
					criteriaBusqueda.put("codigoSAP", f.getCodigoSAP());
					criteriaBusqueda.put("codigoPais", f.getCodigoPais());

					String oidProducto = service
							.getOidProducto(criteriaBusqueda);
					criteriaBusqueda.put("oidProducto", oidProducto);

					if (oidProducto == null) {
						throw new Exception(
								this.getResourceMessage("mantenimientoINCDefinirPremioForm.msg.codigoProductoNoExiste"));
					} else {
						criteriaBusqueda.put("oidProducto", oidProducto);
						concursoProductosExigidos
								.setDescripcionProducto(service
										.getDescripcionProducto(criteriaBusqueda));
					}

					concursoProductosExigidos.setCodigoSAP(f.getCodigoSAP());

					if (!StringUtils.isEmpty(f.getOidTipoOferta()))
						concursoProductosExigidos.setOidTipoOferta(new Long(f
								.getOidTipoOferta()));

					if (!StringUtils.isEmpty(f.getOidCicloVida()))
						concursoProductosExigidos.setOidCicloVida(new Long(f
								.getOidCicloVida()));

					if (!StringUtils.isEmpty(f.getOidTipoAgrupacion()))
						concursoProductosExigidos.setIndicadorAgrupacion(f
								.getOidTipoAgrupacion());

					concursoProductosExigidos
							.setDescripcionTipoOferta(obtenerDescripcionLista(
									f.getOidTipoOferta(), listTiposOferta));
					concursoProductosExigidos
							.setDescripcionCicloVida(obtenerDescripcionLista(
									f.getOidCicloVida(), listCiclosVida));
					concursoProductosExigidos
							.setDescripcionAgrupacion(obtenerDescripcionLista(
									f.getOidTipoAgrupacion(),
									listTiposAgrupacion));
				}
				if (codigoBloqueProducto.equals(Constants.INC_BLOQUE_CUV)) {
					// Validamos el CUV ingresado
					Map criteriaBusqueda = new HashMap();
					criteriaBusqueda.put("codigoPeriodo",
							f.getCodigoPeriodoCUV());
					criteriaBusqueda.put("CUV", f.getCUV());

					Map mapDetalleOferta = serviceInc
							.getDetalleOfertaCUV(criteriaBusqueda);

					if (mapDetalleOferta == null) {
						throw new Exception(
								this.getResourceMessage("mantenimientoINCDefinirPremioForm.msg.codigoCUVNoExiste"));

					}

					String oidDetalleOferta = mapDetalleOferta.get(
							"oidDetalleOferta").toString();
					concursoProductosExigidos
							.setOidDetalleOferta(oidDetalleOferta);
					concursoProductosExigidos.setCodigoPeriodoDesde(f
							.getCodigoPeriodoCUV());
					concursoProductosExigidos.setCodigoPeriodoHasta(f
							.getCodigoPeriodoCUV());
					concursoProductosExigidos.setDescripcionCUV(f
							.getCodigoPeriodoCUV() + " - " + f.getCUV());
					concursoProductosExigidos
							.setCodigoSAP((String) mapDetalleOferta
									.get("codigoSAP"));
					concursoProductosExigidos
							.setDescripcionTipoOferta((String) mapDetalleOferta
									.get("descripcionTipoOferta"));
					concursoProductosExigidos
							.setDescripcionProducto((String) mapDetalleOferta
									.get("descripcionProducto"));

					if (!StringUtils.isEmpty(f.getOidTipoAgrupacionCUV())) {
						concursoProductosExigidos.setIndicadorAgrupacion(f
								.getOidTipoAgrupacionCUV());
						concursoProductosExigidos
								.setDescripcionAgrupacion(obtenerDescripcionLista(
										f.getOidTipoAgrupacionCUV(),
										listTiposAgrupacion));
					}
				}

				detalList.add(concursoProductosExigidos);
				this.incProductosExigidosList = detalList;
				// GENERAR INDEX
				generarIndexExigidos(incProductosExigidosList);

				dataTableProductosExigidos = new DataTableModel(
						this.incProductosExigidosList);
				f.setIndRedefinirProductosExigidos(true);
				f.setIndTieneProductosExigidos(true);
			}
		} catch (Exception e) {
			// TODO: handle exception
			addError("Error", obtieneMensajeErrorException(e));
		}

	}

	public void deleteProducto(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'deleteProducto' method");
		}

		MantenimientoINCConfiguracionConcursoForm f = (MantenimientoINCConfiguracionConcursoForm) this.formMantenimiento;

		List detalList = null;
		String oidTipoProducto = f.getOidTipoProducto();

		if (oidTipoProducto.equals(Constants.INC_OID_PRODUCTOS_VALIDOS)
				&& beanRegistroProductosValidos != null) {
			int tamanio = beanRegistroProductosValidos.length;
			for (int i = 0; i < tamanio; i++) {
				ConcursoProductosValidos data = (ConcursoProductosValidos) beanRegistroProductosValidos[i];
				for (int j = 0; j < incProductosValidosList.size(); j++) {
					ConcursoProductosValidos data2 = (ConcursoProductosValidos) incProductosValidosList
							.get(j);
					if (data.getIndex().equals(data2.getIndex()))
						incProductosValidosList.remove(j);
				}
			}

			detalList = this.incProductosValidosList;
			this.incProductosValidosList = detalList;
			generarIndexValidos(incProductosValidosList);
			dataTableProductosValidos = new DataTableModel(
					this.incProductosValidosList);
			beanRegistroProductosValidos = null;
			f.setIndRedefinirProductosValidos(true);
		}
		if (oidTipoProducto.equals(Constants.INC_OID_PRODUCTOS_BONIFICADOS)
				&& beanRegistroProductosBonificacion != null) {
			int tamanio = beanRegistroProductosBonificacion.length;
			for (int i = 0; i < tamanio; i++) {
				ConcursoProductosBonificados data = (ConcursoProductosBonificados) beanRegistroProductosBonificacion[i];
				for (int j = 0; j < incProductosBonificadosList.size(); j++) {
					ConcursoProductosBonificados data2 = (ConcursoProductosBonificados) incProductosBonificadosList
							.get(j);
					if (data.getIndex().equals(data2.getIndex()))
						incProductosBonificadosList.remove(j);
				}
			}
			detalList = this.incProductosBonificadosList;
			this.incProductosBonificadosList = detalList;
			generarIndexBonificacion(incProductosBonificadosList);
			dataTableProductosBonificacion = new DataTableModel(
					this.incProductosBonificadosList);
			beanRegistroProductosBonificacion = null;
			f.setIndRedefinirProductosBonificados(true);
		}
		if (oidTipoProducto.equals(Constants.INC_OID_PRODUCTOS_EXCLUIDOS)
				&& beanRegistroProductosExcluidos != null) {
			int tamanio = beanRegistroProductosExcluidos.length;
			for (int i = 0; i < tamanio; i++) {
				ConcursoProductosExcluidos data = (ConcursoProductosExcluidos) beanRegistroProductosExcluidos[i];
				for (int j = 0; j < incProductosExcluidosList.size(); j++) {
					ConcursoProductosExcluidos data2 = (ConcursoProductosExcluidos) incProductosExcluidosList
							.get(j);
					if (data.getIndex().equals(data2.getIndex()))
						incProductosExcluidosList.remove(j);
				}
			}

			detalList = this.incProductosExcluidosList;
			this.incProductosExcluidosList = detalList;
			generarIndexExcluidos(incProductosExcluidosList);
			dataTableProductosExcluidos = new DataTableModel(
					this.incProductosExcluidosList);
			beanRegistroProductosExcluidos = null;
			f.setIndRedefinirProductosExcluidos(true);
		}
		if (oidTipoProducto.equals(Constants.INC_OID_PRODUCTOS_EXIGIDOS)
				&& beanRegistroProductosExigidos != null) {
			int tamanio = beanRegistroProductosExigidos.length;
			for (int i = 0; i < tamanio; i++) {
				ConcursoProductosExigidos data = (ConcursoProductosExigidos) beanRegistroProductosExigidos[i];
				for (int j = 0; j < incProductosExigidosList.size(); j++) {
					ConcursoProductosExigidos data2 = (ConcursoProductosExigidos) incProductosExigidosList
							.get(j);
					if (data.getIndex().equals(data2.getIndex()))
						incProductosExigidosList.remove(j);
				}
			}

			detalList = this.incProductosExigidosList;
			this.incProductosExigidosList = detalList;
			generarIndexExigidos(incProductosExigidosList);
			dataTableProductosExigidos = new DataTableModel(
					this.incProductosExigidosList);
			beanRegistroProductosExigidos = null;
			f.setIndRedefinirProductosExigidos(true);
		}

		if (oidTipoProducto.equals(Constants.INC_OID_PRODUCTOS_EXIGIDOS)) {
			if (detalList.size() == 0) {
				f.setIndTieneProductosExigidos(false);
			}
		}

	}

	private String obtenerDescripcionLista(String oid, List listValores) {
		String descripcion = "";

		for (int i = 0; i < listValores.size(); i++) {
			Base base = (Base) listValores.get(i);

			if (base.getCodigo().equals(oid)) {
				descripcion = base.getDescripcion();
				break;
			}
		}

		return descripcion;
	}

	public void validarInsertProductos() throws Exception {
		// realizamos validaciones de valores x defecto
		MantenimientoINCConfiguracionConcursoForm f = (MantenimientoINCConfiguracionConcursoForm) this.formMantenimiento;

		if (f.getCodigoBloqueProducto().equals("N")) {// Negocio
			if (StringUtils.isBlank(f.getOidMarcaProducto())) {
				throw new Exception(
						this.getResourceMessage("mantenimientoINCConfiguracionConcursoForm.msg.validaMarcaProducto"));

			}
		}
		if (f.getCodigoBloqueProducto().equals("O")) {// Oferta
			if (StringUtils.isBlank(f.getOidTipoOferta())) {
				throw new Exception(
						this.getResourceMessage("mantenimientoINCConfiguracionConcursoForm.msg.validaTipoOferta"));
			}
		}
		if (f.getCodigoBloqueProducto().equals("P")) {// Producto
			// var oidTipoOferta = document.getElementById('oidTipoOferta');
			if (StringUtils.isBlank(f.getCodigoSAP())) {
				throw new Exception(
						this.getResourceMessage("mantenimientoINCConfiguracionConcursoForm.msg.validaCodigoProducto"));
			}

		}
		if (f.getCodigoBloqueProducto().equals("C")) {// CUV
			// var codigoPeriodoCUV =
			// document.getElementById('codigoPeriodoCUV');
			// var CUV = document.getElementById('CUV');

			if (StringUtils.isBlank(f.getCodigoPeriodoCUV())) {
				throw new Exception(
						this.getResourceMessage("mantenimientoINCConfiguracionConcursoForm.msg.validaCodigoPeriodoCUV"));
			}

			if ((Integer.parseInt(f.getCodigoPeriodoCUV()) < Integer.parseInt(f
					.getCodigoPeriodoInicio()))
					|| (Integer.parseInt(f.getCodigoPeriodoCUV()) > Integer
							.parseInt(f.getCodigoPeriodoFin()))) {
				throw new Exception(
						this.getResourceMessage("mantenimientoINCConfiguracionConcursoForm.msg.validaRangoPeriodoCUV"));
			}
			if (StringUtils.isBlank(f.getCUV())) {
				throw new Exception(
						this.getResourceMessage("mantenimientoINCConfiguracionConcursoForm.msg.validaCUVProducto"));
			}

		}

		if (!f.getOidTipoProducto().equals("1")) {// Es diferente a
													// Productos
													// Validos

			if (!f.getCodigoBloqueProducto().equals("C")) { // Es diferente
															// a
															// CUV
				if (StringUtils.isBlank(f.getCodigoPeriodoDesde())) {
					throw new Exception(
							this.getResourceMessage("mantenimientoINCConfiguracionConcursoForm.msg.validaCodigoPeriodoDesde"));
				}
				if (StringUtils.isBlank(f.getCodigoPeriodoHasta())) {
					throw new Exception(
							this.getResourceMessage("mantenimientoINCConfiguracionConcursoForm.msg.validaCodigoPeriodoHasta"));
				}
				if (f.getCodigoPeriodoInicio() == null)
					f.setCodigoPeriodoInicio("0");
				if (f.getCodigoPeriodoFin() == null)
					f.setCodigoPeriodoFin("0");

				if ((Integer.parseInt(f.getCodigoPeriodoDesde()) < Integer
						.parseInt(f.getCodigoPeriodoInicio()))
						|| (Integer.parseInt(f.getCodigoPeriodoHasta()) > Integer
								.parseInt(f.getCodigoPeriodoFin()))) {
					throw new Exception(
							this.getResourceMessage("mantenimientoINCConfiguracionConcursoForm.msg.validaVigenciaPeriodoProductos"));
				}
			}
		}

		if (f.getOidTipoProducto().equals("3")) {// Es Productos Bonificados

			if (StringUtils.isBlank(f.getPuntosUnidad())
					&& StringUtils.isBlank(f.getFactorMultiplicador())) {
				throw new Exception(
						this.getResourceMessage("mantenimientoINCConfiguracionConcursoForm.msg.validaTipoBonificacion"));
			}
		}

		if (f.getOidTipoProducto().equals("4")) {// Es Productos Exigidos
			if (f.getCodigoBloqueProducto().equals("P")
					&& StringUtils.isBlank(f.getOidTipoAgrupacion())) {
				f.setOidTipoAgrupacion("U");
			}
		}

	}

	public void generarReporte(ActionEvent event) throws Exception {

		Map paramsConcurso = (Map) this.beanRegistroSeleccionado;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		if (datatableBusqueda == null)
			this.getRequestContext().execute(
					"PF('dialogSinRegistros_alertDialog').show()");
		else if (beanRegistroSeleccionado == null)
			this.getRequestContext().execute(
					"PF('dialogSinItem_alertDialog').show()");
		else {
			reporteConcurso.setTipoReporte("TO");
			ReporteINCForm report = new ReporteINCForm();
			report.setCodigoPais(codigoPais);
			report.setPais(pais);
			report.setUsuario(usuario);
			// report.setCodigoPeriodo(codigoPeriodo);
			report.setCodigoConcurso(paramsConcurso.get("numeroConcurso")
					.toString());
			report.setTipoReporte("1");

			reporteConcurso.setFormatoReporte("PDF");
			reporteConcurso.setFormatoExportacion("PDF");
			reporteConcurso.setFormReporte(report);
			reporteConcurso.getFormReporte().setFormatoExportacion("PDF");
			reporteConcurso.executeReporte();
			// reporteINCConfiguracionConcursoForm.xhtml
			this.redireccionarPagina("mantenimientoReporteINCConfiguracionConcursoForm");
		}
	}

	public void validaCodigo() {
		MantenimientoINCConfiguracionConcursoForm f = (MantenimientoINCConfiguracionConcursoForm) this.formMantenimiento;
		// Validamos que el concurso multipunto exista
		try {
			if (f.getCodigoCPP().length() == 6) {
				AjaxService ajaxService = (AjaxService) getBean("ajaxService");
				LabelValue programa = ajaxService.getConcursoProgramaPuntos(
						codigoPais, f.getCodigoCPP());

				if (programa == null) {
					throw new Exception(
							this.getResourceMessage("mantenimientoINCConfiguracionConcursoForm.msg.concursoNoExiste"));

				} else {
					if (StringUtils.equals(programa.getValue(),
							Constants.NUMERO_CERO))
						throw new Exception(
								this.getResourceMessage("mantenimientoINCConfiguracionConcursoForm.msg.noEsProgramaPuntos"));

				}

				f.setDescripcionCPP(programa.getLabel());
				indicadorExigePasarPedidoCampanyaAnteriorCPPBoolean = true;
				puntosAbonarRecomendacionEfectivaCPPDisabled = false;
			} else {
				f.setDescripcionCPP("");
				indicadorExigePasarPedidoCampanyaAnteriorCPPBoolean = false;
				puntosAbonarRecomendacionEfectivaCPPDisabled = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			addError("Error", obtieneMensajeErrorException(e));
		}

	}

	public void validaMensaje() {
		try {
			MantenimientoINCConfiguracionConcursoForm f = (MantenimientoINCConfiguracionConcursoForm) this.formMantenimiento;
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			String data = ajax.getDescripcionMensaje(codigoPais,
					f.getCodigoMensajePuntos());
			if (data == null) {
				f.setDescripcionMensajePuntos("");
				throw new Exception(
						this.getResourceMessage("mantenimientoINCConfiguracionConcursoForm.msg.mensajeNoExiste"));
			}

			f.setDescripcionMensajePuntos(data);
		} catch (Exception e) {
			// TODO: handle exception
			addError("Error", obtieneMensajeErrorException(e));
		}
	}

	public void salirReporte(ActionEvent event) throws IOException {
		this.redireccionarPagina("mantenimientoINCConfiguracionConcursoList");
	}

	public void mostrarDescripcionProducto() {
		System.out.println("entrooooooooooooooo");
		MantenimientoINCDefinirPremioForm f = (MantenimientoINCDefinirPremioForm) this.formDefinirPremio;
		AjaxService ajax = (AjaxService) getBean("ajaxService");

		if (StringUtils.isNotBlank(f.getCodigoSAP())) {
			String resultado = ajax.getDescripcionInternacionalizableProducto(f
					.getCodigoSAP());
			if (StringUtils.isNotBlank(resultado))
				f.setDescripcionSAP(resultado);
			else {
				f.setCodigoSAP("");
				f.setDescripcionSAP("");
				this.addWarn(
						"",
						this.getResourceMessage("mantenimientoMAVConfiguracionSearchForm.producto.no.existe"));
			}
		}
	}

	public void verBaseCalculo(ValueChangeEvent event) {
		String valor = (String) event.getNewValue();
		MantenimientoINCConfiguracionConcursoForm f = (MantenimientoINCConfiguracionConcursoForm) this.formMantenimiento;

		if (valor.equals("1") || valor.equals("2")) {
			f.setNumeroMinimoPedidos("");
			this.deshabilitarNumeroMinimoPedidos = true;
		} else {
			this.deshabilitarNumeroMinimoPedidos = false;
		}

	}

	// ---------------- DEFINIR PREMIO DESCUENTO
	// PER-SiCC-2015-0340 fecha: 30/06/2015
	public void abrirPopupDefinirPremioDescuento(ActionEvent event) 
	{
		try {
			MantenimientoINCDefinirPremioDescuentoForm f = new MantenimientoINCDefinirPremioDescuentoForm();
			MantenimientoINCConfiguracionConcursoForm formPrincipal = (MantenimientoINCConfiguracionConcursoForm) this.formMantenimiento;
			MantenimientoINCConfiguracionConcursoService service = (MantenimientoINCConfiguracionConcursoService) getBean("spusicc.mantenimientoINCConfiguracionConcursoService");
			
			if (beanRegistroPremiacion == null)
			{
				this.addWarn("Advertencia: ", "Debe Seleccionar un Nivel");
				return;
			}
			
			ConcursoNivelPremiacion data = (ConcursoNivelPremiacion) beanRegistroPremiacion;
			
			// Validacion que pide en el word para premios Descuento			
			int  existeData  = 0;
			if(data.getListConcursoLotePremioArticulo() != null && data.getListConcursoLotePremioArticulo().size() > 0)
			{
				for (int i = 0; i < data.getListConcursoLotePremioArticulo().size(); i++) {
					ConcursoLotePremioArticulo aux = (ConcursoLotePremioArticulo)data.getListConcursoLotePremioArticulo().get(i);
					Map criteria = new HashMap();
					criteria.put("oidPremio", aux.getOid());
					criteria.put("valueIndTipoPrem", "A");
					
					existeData = service.getExistePremiosArticulo(criteria);
					if(existeData > 0)
						break;
				}
			}
			
			if(existeData > 0)
			{
				this.addWarn("Advertencia", this.getResourceMessage("mantenimientoINCDefinirPremioDescuentoForm.existePremiosArticulos"));
				return;				
			}
			
			if(!formPrincipal.getOidTipoPremiacion().equals("1") || data.getIndicadorNivelSelectivo() != 1){
				this.addWarn("Advertencia", this.getResourceMessage("mantenimientoINCDefinirPremioDescuentoForm.premioDescuentoNoDisponible"));
				return;
			}
			
			List detalList = this.incNivelesPremiacionList;
			String nivelSeleccionado = data.getNumeroNivel().toString();
			String indicadorNivelSelectivo = data.getIndicadorNivelSelectivo().toString();

			f.setIndicadorNivelSelectivo(indicadorNivelSelectivo);
			f.setNumeroNivel(nivelSeleccionado);

			List detalListArticulos = new ArrayList();
			// se verifica si existen premios articulos
			List premioArticuo = new ArrayList();

			for (int i = 0; i < detalList.size(); i++) 
			{
				ConcursoNivelPremiacion concursoNivelPremiacion = (ConcursoNivelPremiacion) detalList.get(i);
				int numeroNivel = concursoNivelPremiacion.getNumeroNivel().intValue();

				if (numeroNivel == Integer.parseInt(f.getNumeroNivel())) 
				{
					f.setDescripcionLote("Nivel " + f.getNumeroNivel());

					if (concursoNivelPremiacion.getListConcursoLotePremioArticulo() == null) {
						concursoNivelPremiacion.setListConcursoLotePremioArticulo(new ArrayList());
					}

					// numeroLote es un correlativo por nivel, si el nivel es elegible
					if (Constants.NRO_UNO.equals(indicadorNivelSelectivo)) 
					{
						f.setNumeroLote(String.valueOf(concursoNivelPremiacion.getListConcursoLotePremioArticulo().size() + 1));
						f.setDescripcionLote(f.getDescripcionLote() + " - Opc " + f.getNumeroLote());
					} else 
					{
						if (concursoNivelPremiacion.getListConcursoLotePremioArticulo().size() > 0) 
						{
							ConcursoLotePremioArticulo concursoLotePremioArticulo = (ConcursoLotePremioArticulo) concursoNivelPremiacion
									.getListConcursoLotePremioArticulo().get(0);
							f.setDescripcionLote(concursoLotePremioArticulo.getDescripcionLote());
						}
						
						f.setNumeroLote(Constants.NRO_UNO);
					}

					List detalListAux = concursoNivelPremiacion.getListConcursoLotePremioArticulo();

					for (int j = 0; j < detalListAux.size(); j++) 
					{
						ConcursoLotePremioArticulo concursoLotePremioArticulo = (ConcursoLotePremioArticulo) detalListAux.get(j);
						if(concursoLotePremioArticulo.getListConcursoArticuloLoteDescuento() != null)
							detalListArticulos.addAll(concursoLotePremioArticulo.getListConcursoArticuloLoteDescuento());
						else
							concursoLotePremioArticulo.setListConcursoArticuloLoteDescuento(new ArrayList());
						
						if(concursoLotePremioArticulo.getListConcursoArticuloLote() != null &&
								concursoLotePremioArticulo.getListConcursoArticuloLote().size() > 0)
							premioArticuo.addAll(concursoLotePremioArticulo.getListConcursoArticuloLote());
					}
				}

			}
			
			if(premioArticuo.size() > 0)
			{
				this.addWarn("Advertencia", this.getResourceMessage("mantenimientoINCDefinirPremioDescuentoForm.existePremiosArticulos"));
				return;		
			}
			
			this.listaPremioDescuento = detalListArticulos;
			premioDescuentoDataModel = new DataTableModel(listaPremioDescuento);
			f.setTipoLote("");
			actualizarLotePremioDescuento(f);
			this.definirPremioDescuentoForm = f;
			this.mostrarBotones = false;
			
			this.getRequestContext().execute("PF('viewDefinirPremioDescuento').show()");

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	public void insertDefinirPremioDescuento(ActionEvent event)
	{
		try {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'insert' method");
			}
			MantenimientoINCDefinirPremioDescuentoForm f = (MantenimientoINCDefinirPremioDescuentoForm) this.definirPremioDescuentoForm;
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			String descripcionProducto = "";
			
			//  insertamos el codigo del producto
			String codigoProducto = service.getcodigoProductoDescuento();
			f.setCodigoSAP(codigoProducto);
			
			//Validamos el codigo SAP ingresado
			Map criteriaBusqueda = new HashMap();
			criteriaBusqueda.put("codigoSAP",f.getCodigoSAP());
			criteriaBusqueda.put("codigoPais",pais.getCodigo());
			
			MantenimientoRECProductosFFNNEEService service = (MantenimientoRECProductosFFNNEEService)  	
															getBean("spusicc.mantenimientoRECProductosFFNNEEService");
			String oidProducto=service.getOidProducto(criteriaBusqueda);
			criteriaBusqueda.put("oidProducto", oidProducto);
			
				criteriaBusqueda.put("oidProducto",oidProducto);
				descripcionProducto=service.getDescripcionProducto(criteriaBusqueda);
					
				List detalList = this.listaPremioDescuento;//this.incArticulosLoteList;
				ConcursoParametrosGenerales concursoParametrosGenerales = this.incDtoConcurso;
				
				ConcursoLotePremioArticulo concursoLotePremioArticulo = null;
				List detalListAux = this.incNivelesPremiacionList;
				ConcursoNivelPremiacion concursoNivelPremiacionAct = null;
				
				for(int i=0; i< detalListAux.size(); i++) {
					ConcursoNivelPremiacion concursoNivelPremiacion = (ConcursoNivelPremiacion)detalListAux.get(i);
					String numeroNivel = concursoNivelPremiacion.getNumeroNivel().toString();
					
					if(numeroNivel.equals(f.getNumeroNivel())) 
						concursoNivelPremiacionAct = concursoNivelPremiacion;
				}

				if(Constants.NRO_UNO.equals(f.getIndicadorNivelSelectivo())) {
					List detalListLote = concursoNivelPremiacionAct.getListConcursoLotePremioArticulo();
					boolean encontrado = false;
					
					for(int j=0; j< detalListLote.size(); j++) {
						concursoLotePremioArticulo = (ConcursoLotePremioArticulo)detalListLote.get(j);
						
						if(concursoLotePremioArticulo.getNumeroLote().toString().equals(f.getNumeroLote())) {
							f.setDescripcionLote(concursoLotePremioArticulo.getDescripcionLote());
							f.setNumeroPremio(concursoLotePremioArticulo.getNumeroPremio().toString());
							encontrado = true;
							break;
						}
					}

					if(!encontrado) {
						concursoLotePremioArticulo = new ConcursoLotePremioArticulo();
						concursoLotePremioArticulo.setDescripcionLote(f.getDescripcionLote());
						concursoLotePremioArticulo.setNumeroLote(new Integer(f.getNumeroLote()));
						concursoLotePremioArticulo.setNumeroPremio(new Integer(f.getNumeroPremio()));
						concursoLotePremioArticulo.setListConcursoArticuloLote(new ArrayList());
						concursoLotePremioArticulo.setListConcursoArticuloLoteDescuento(new ArrayList());
						concursoNivelPremiacionAct.getListConcursoLotePremioArticulo().add(concursoLotePremioArticulo);
					}	
					
				} else {
					if(concursoNivelPremiacionAct.getListConcursoLotePremioArticulo()== null || 
							concursoNivelPremiacionAct.getListConcursoLotePremioArticulo().size() == 0) {
						
						concursoLotePremioArticulo = new ConcursoLotePremioArticulo();
						concursoLotePremioArticulo.setDescripcionLote(f.getDescripcionLote());
						concursoLotePremioArticulo.setNumeroLote(new Integer(f.getNumeroLote()));
						concursoLotePremioArticulo.setNumeroPremio(new Integer(f.getNumeroPremio()));
						concursoLotePremioArticulo.setListConcursoArticuloLote(new ArrayList());
						concursoLotePremioArticulo.setListConcursoArticuloLoteDescuento(new ArrayList());
						concursoNivelPremiacionAct.getListConcursoLotePremioArticulo().add(concursoLotePremioArticulo);
						
					} else {
						concursoLotePremioArticulo = (ConcursoLotePremioArticulo)concursoNivelPremiacionAct.
															getListConcursoLotePremioArticulo().get(0);
						f.setDescripcionLote(concursoLotePremioArticulo.getDescripcionLote());
						f.setNumeroPremio(concursoLotePremioArticulo.getNumeroPremio().toString());
					}	
				}
				
				// cambiamos concursoArticuloLote x concursoArticuloLoteDescuento
				ConcursoArticuloLoteDescuento concursoArticuloLoteDescuento = new ConcursoArticuloLoteDescuento();
				concursoArticuloLoteDescuento.setNumeroNivel(f.getNumeroNivel());
				concursoArticuloLoteDescuento.setDescripcionLote(f.getDescripcionLote());
				concursoArticuloLoteDescuento.setNumeroPremio(f.getNumeroPremio());
				concursoArticuloLoteDescuento.setCodigoSAP(f.getCodigoSAP()); // codigo producto
				concursoArticuloLoteDescuento.setMontoDescuento(f.getMontoDescuento());
				concursoArticuloLoteDescuento.setIndicadorDespacho(new Integer(1));
				concursoArticuloLoteDescuento.setCodigoVentaFicticio("");
				concursoArticuloLoteDescuento.setNumeroUnidades(new Integer(1));
				concursoArticuloLoteDescuento.setPrecioPublico(new BigDecimal("0.00"));
				concursoArticuloLoteDescuento.setDescripcionProducto(descripcionProducto);
							
				for(int k=0; k<concursoLotePremioArticulo.getListConcursoArticuloLoteDescuento().size(); k++) {
					ConcursoArticuloLoteDescuento aux = (ConcursoArticuloLoteDescuento)
							concursoLotePremioArticulo.getListConcursoArticuloLoteDescuento().get(k);
					if(aux.getCodigoSAP().equalsIgnoreCase(concursoArticuloLoteDescuento.getCodigoSAP())) {
						this.addError("Error: ", this.getResourceMessage("mantenimientoINCDefinirPremioForm.msg.productoExistente"));
						return;
					}
				}
				
				int pos = -1;
				for(int k=0; k<concursoLotePremioArticulo.getListConcursoArticuloLoteDescuento().size(); k++) {
					ConcursoArticuloLoteDescuento aux = (ConcursoArticuloLoteDescuento)
							concursoLotePremioArticulo.getListConcursoArticuloLoteDescuento().get(k);
					
					if("".equals(aux.getCodigoVentaFicticio())) {
						if(concursoArticuloLoteDescuento.getCodigoSAP().compareTo(aux.getCodigoSAP())<0) {
							pos = k;
							break;
						}
					} 
				}	
				
				if(pos>=0)
					concursoLotePremioArticulo.getListConcursoArticuloLoteDescuento().add(pos, concursoArticuloLoteDescuento);
				else
					concursoLotePremioArticulo.getListConcursoArticuloLoteDescuento().add(concursoArticuloLoteDescuento);
				
				List detalListArticulos = new ArrayList();
				List detalListLote = concursoNivelPremiacionAct.getListConcursoLotePremioArticulo();
				for(int j=0; j< detalListLote.size(); j++) {
					concursoLotePremioArticulo = (ConcursoLotePremioArticulo)detalListLote.get(j);
					detalListArticulos.addAll(concursoLotePremioArticulo.getListConcursoArticuloLoteDescuento());
				}

				this.listaPremioDescuento = detalListArticulos;
				premioDescuentoDataModel = new DataTableModel(listaPremioDescuento);
				
				actualizarLotePremioDescuento(f);
				f.setTipoLote(f.getDescripcionLote());
				f.setMontoDescuento("");
				
				this.indRedefinirNivelPremiacion = "S";			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	public void editDefinirPremioDescuento(ActionEvent event) 
	{
		try {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'editDefinirPremioDescuento' method");
			}

			MantenimientoINCDefinirPremioDescuentoForm f = (MantenimientoINCDefinirPremioDescuentoForm) this.definirPremioDescuentoForm;
			
			ConcursoArticuloLoteDescuento data = this.beanRegistroDefinirPremioDescuento;

			MantenimientoINCConfiguracionConcursoService service = (MantenimientoINCConfiguracionConcursoService) getBean("spusicc.mantenimientoINCConfiguracionConcursoService");
			ConcursoParametrosGenerales concursoParametrosGenerales = this.incDtoConcurso;

			if (data != null) 
			{
				List list = this.incArticulosLoteList;
				ConcursoArticuloLoteDescuento concursoArticuloLoteDescuento = data;

				// VALIDAMOS SI EL PREMIO HA SIDO ATENDIDO, SI ES ASI, NO SE PUEDE ELIMINAR
				String oidConcurso = concursoParametrosGenerales.getOid()==null?null:concursoParametrosGenerales.getOid().toString();
				String codigoVenta = concursoArticuloLoteDescuento.getCodigoVentaFicticio();
				if (StringUtils.isNotEmpty(codigoVenta)) 
				{
					Map criteria = new HashMap();
					criteria.put("oidConcurso", oidConcurso);
					criteria.put("codigoVenta", codigoVenta);
					boolean tieneAtenciones = service.validaPremioAtendido(criteria);

					if (tieneAtenciones) {
						this.addError("Error: ", this.getResourceMessage("mantenimientoINCDefinirPremioForm.msg.premioTieneAtenciones2"));
						return;
					}
				}

				f.setModificar(true);

				f.setNumeroPremio(concursoArticuloLoteDescuento.getNumeroPremio());
				f.setDescripcionLote(concursoArticuloLoteDescuento.getDescripcionLote());

				f.setCodigoSAP(concursoArticuloLoteDescuento.getCodigoSAP());
				f.setDescripcionSAP(concursoArticuloLoteDescuento.getDescripcionProducto());
				f.setNumeroUnidades(reemplazarNulo(concursoArticuloLoteDescuento.getNumeroUnidades()));

				f.setIndicadorCentroServicio(reemplazarNulo(concursoArticuloLoteDescuento.getIndicadorCentroServicio()));
				f.setNumeroMesesGarantia(reemplazarNulo(concursoArticuloLoteDescuento.getNumeroMesesGarantia()));
				f.setIndicadorTipoEntrega(concursoArticuloLoteDescuento.getIndicadorTipoEntrega());

				f.setOidCentroServGarantia(reemplazarNulo(f.getOidCentroServGarantia()));
				f.setObservaciones(concursoArticuloLoteDescuento.getObservaciones());
				f.setOidCentroServEntrega(reemplazarNulo(f.getOidCentroServEntrega()));
				f.setPrecio(reemplazarNulo(concursoArticuloLoteDescuento.getPrecioPublico()));
				f.setMontoDescuento(concursoArticuloLoteDescuento.getMontoDescuento());

				List detalListAux = this.incNivelesPremiacionList;

				for (int i = 0; i < detalListAux.size(); i++) 
				{
					ConcursoNivelPremiacion concursoNivelPremiacion = (ConcursoNivelPremiacion) detalListAux.get(i);
					String numeroNivel = concursoNivelPremiacion.getNumeroNivel().toString();

					if (numeroNivel.equals(f.getNumeroNivel())) 
					{
						List detalListLote = concursoNivelPremiacion.getListConcursoLotePremioArticulo();
						boolean encontrado = false;

						for (int j = 0; j < detalListLote.size(); j++) {
							ConcursoLotePremioArticulo concursoLotePremioArticulo = (ConcursoLotePremioArticulo) detalListLote.get(j);

							if (concursoLotePremioArticulo.getNumeroPremio().toString().equals(f.getNumeroPremio())) 
							{
								f.setNumeroLote(reemplazarNulo(concursoLotePremioArticulo.getNumeroLote()));
								f.setTipoLote(f.getNumeroLote() + "__" + f.getDescripcionLote() + "__" + f.getNumeroPremio());
								break;
							}
						}
						break;
					}
				}

				f.setSelectedItems(null);
				this.idPremioDescuento = data;
				this.mostrarBotones = true;
			}else
				this.addWarn("Advertencia: ", this.getResourceMessage("errors.select.item"));
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	public void deleteDefinirPremioDescuento(ActionEvent event)
	{
		try {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'deleteDefinirPremioDescuento' method");
			}
			
			MantenimientoINCDefinirPremioDescuentoForm f = (MantenimientoINCDefinirPremioDescuentoForm) this.definirPremioDescuentoForm;
			MantenimientoINCConfiguracionConcursoService service = (MantenimientoINCConfiguracionConcursoService) 
					getBean("spusicc.mantenimientoINCConfiguracionConcursoService");
			ConcursoParametrosGenerales concursoParametrosGenerales = this.incDtoConcurso;
						
			//Capturamos el objeto a eliminar
			ConcursoArticuloLoteDescuento data = this.beanRegistroDefinirPremioDescuento;
			
			if (data != null) 
			{
				//VALIDAMOS SI EL PREMIO HA SIDO ATENDIDO, SI ES ASI, NO SE PUEDE ELIMINAR
				ConcursoArticuloLoteDescuento concursoArticuloLoteDescuento = data;
				String oidConcurso = concursoParametrosGenerales.getOid() == null?null:concursoParametrosGenerales.getOid().toString();
				String codigoVenta = concursoArticuloLoteDescuento.getCodigoVentaFicticio();
				if(StringUtils.isNotEmpty(codigoVenta)) {
					Map criteria = new HashMap();
					criteria.put("oidConcurso", oidConcurso);
					criteria.put("codigoVenta", codigoVenta);
					boolean tieneAtenciones = service.validaPremioAtendido(criteria);
					
					if(tieneAtenciones) {
						this.addError("Error: ", this.getResourceMessage("mantenimientoINCDefinirPremioForm.msg.premioTieneAtenciones"));
						return;
					}	
				}
				
				data.setEliminado(true);
			
				//RECORREMOS LOS LOTES DE ARTICULOS Y BORRAMOS LOS PREMIOS DESCUENTO QUE ESTAN MARCADOS COMO ELIMINADOS
				List detalListAux = this.incNivelesPremiacionList;
				ConcursoNivelPremiacion concursoNivelPremiacionAct = null;
				
				for(int i=0; i< detalListAux.size(); i++) {
					ConcursoNivelPremiacion concursoNivelPremiacion = (ConcursoNivelPremiacion)detalListAux.get(i);
					String numeroNivel = concursoNivelPremiacion.getNumeroNivel().toString();
					
					if(numeroNivel.equals(f.getNumeroNivel())) 
						concursoNivelPremiacionAct = concursoNivelPremiacion;
				}
				
				List detalListLote = concursoNivelPremiacionAct.getListConcursoLotePremioArticulo();
				for(int j=0; j< detalListLote.size(); j++) {
					ConcursoLotePremioArticulo concursoLotePremioArticulo = (ConcursoLotePremioArticulo)detalListLote.get(j);
					List detalListArticulo = concursoLotePremioArticulo.getListConcursoArticuloLoteDescuento();
					
					for(int k=0; k<detalListArticulo.size(); k++) {
						concursoArticuloLoteDescuento = (ConcursoArticuloLoteDescuento)detalListArticulo.get(k);
						if(concursoArticuloLoteDescuento.isEliminado())
							detalListArticulo.remove(k);
					}
				}
				
				//RECORREMOS LOS LOTES QUE NO TENGAN PREMIOS DESCUENTO Y LO BORRAMOS
				for(int j=0; j< detalListLote.size(); j++) {
					ConcursoLotePremioArticulo concursoLotePremioArticulo = (ConcursoLotePremioArticulo)detalListLote.get(j);
					List detalListArticulo = concursoLotePremioArticulo.getListConcursoArticuloLoteDescuento();
					
					if(detalListArticulo == null || detalListArticulo.size() == 0) {
						detalListLote.remove(j);
					}
				}

				//ACTUALIZAMOS LA LISTA DE PREMIOS DESCUENTO PERTENECIENTA AL NIVEL DE PREMIACION QUE SE ESTA EDITANDO
				List detalListArticulos = new ArrayList();
				for(int j=0; j< detalListLote.size(); j++) {
					ConcursoLotePremioArticulo concursoLotePremioArticulo = (ConcursoLotePremioArticulo)detalListLote.get(j);
					detalListArticulos.addAll(concursoLotePremioArticulo.getListConcursoArticuloLoteDescuento());
				}

				f.setSelectedItems(null);
				this.listaPremioDescuento = detalListArticulos;
				premioDescuentoDataModel = new DataTableModel(listaPremioDescuento);
				this.indRedefinirNivelPremiacion = "S";
			}
						
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	public void saveDefinirPremioDescuento(ActionEvent event)
	{
		try {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'save' method");
			}
			
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			ConcursoArticuloLoteDescuento data = this.idPremioDescuento;
			MantenimientoINCDefinirPremioDescuentoForm f = (MantenimientoINCDefinirPremioDescuentoForm) this.definirPremioDescuentoForm;		
			
			String descripcionProducto = "";
			
			//Validamos el codigo SAP ingresado
			Map criteriaBusqueda = new HashMap();
			criteriaBusqueda.put("codigoSAP",f.getCodigoSAP());
			criteriaBusqueda.put("codigoPais",pais.getCodigo());
			
			MantenimientoRECProductosFFNNEEService service = (MantenimientoRECProductosFFNNEEService)  	
															getBean("spusicc.mantenimientoRECProductosFFNNEEService");
			String oidProducto=service.getOidProducto(criteriaBusqueda);
			criteriaBusqueda.put("oidProducto", oidProducto);
			
				criteriaBusqueda.put("oidProducto",oidProducto);
				descripcionProducto=service.getDescripcionProducto(criteriaBusqueda);
				
				List list = this.incArticulosLoteList;
				ConcursoArticuloLoteDescuento concursoArticuloLoteDescuento = data;
				
				concursoArticuloLoteDescuento.setCodigoSAP(f.getCodigoSAP());
				concursoArticuloLoteDescuento.setDescripcionProducto(descripcionProducto);
				//concursoArticuloLoteDescuento.setNumeroUnidades(Integer.parseInt(f.getNumeroUnidades()));
				concursoArticuloLoteDescuento.setMontoDescuento(f.getMontoDescuento());
				
				if(StringUtils.isNotEmpty(f.getPrecio()))
					concursoArticuloLoteDescuento.setPrecioPublico(new BigDecimal(f.getPrecio()));
				else
					concursoArticuloLoteDescuento.setPrecioPublico(new BigDecimal("0.00"));

				actualizarLotePremioDescuento(f);
				f.setTipoLote(f.getDescripcionLote());
				
				this.indRedefinirNivelPremiacion = "S";
				
				//limpiamos el campo
				f.setMontoDescuento("");
				this.mostrarBotones = false;		
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	public void cancelarDefinirPremioDescuento(ActionEvent event)
	{
		try {
			
			if (log.isDebugEnabled()) {
				log.debug("Entering 'cancelar' method");
			}
			MantenimientoINCDefinirPremioDescuentoForm f = (MantenimientoINCDefinirPremioDescuentoForm) this.definirPremioDescuentoForm;		
			
			f.setTipoLote("");
			actualizarLotePremioDescuento(f);	
			this.mostrarBotones = false;
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	private void actualizarLotePremioDescuento(MantenimientoINCDefinirPremioDescuentoForm f) 
	{
		List detalList = this.incNivelesPremiacionList;
		List listTipoLote = new ArrayList();

		ConcursoNivelPremiacion concursoNivelPremiacionAct = null;
		int ultimoNumeroPremio = 0;

		for (int i = 0; i < detalList.size(); i++) 
		{
			ConcursoNivelPremiacion concursoNivelPremiacion = (ConcursoNivelPremiacion) detalList.get(i);
			int numeroNivel = concursoNivelPremiacion.getNumeroNivel().intValue();

			if (numeroNivel == Integer.parseInt(f.getNumeroNivel())) 
			{
				concursoNivelPremiacionAct = concursoNivelPremiacion;
				f.setDescripcionLote("Nivel " + f.getNumeroNivel());

				if (concursoNivelPremiacion.getListConcursoLotePremioArticulo() == null) 
				{
					concursoNivelPremiacion.setListConcursoLotePremioArticulo(new ArrayList());
				}

				// numeroLote es un correlativo por nivel, si el nivel es elegible
				if (Constants.NRO_UNO.equals(f.getIndicadorNivelSelectivo())) 
				{
					List listAux = concursoNivelPremiacion.getListConcursoLotePremioArticulo();
					if (listAux.size() > 0)
						f.setNumeroLote(String.valueOf(((ConcursoLotePremioArticulo) listAux.
								get(listAux.size() - 1)).getNumeroLote() + 1));
					else
						f.setNumeroLote(String.valueOf(1));

					f.setDescripcionLote(f.getDescripcionLote() + " - Opc " + f.getNumeroLote());
				} else 
				{
					if (concursoNivelPremiacion.getListConcursoLotePremioArticulo().size() > 0) 
					{
						ConcursoLotePremioArticulo concursoLotePremioArticulo = (ConcursoLotePremioArticulo) concursoNivelPremiacion
								.getListConcursoLotePremioArticulo().get(0);
						f.setDescripcionLote(concursoLotePremioArticulo.getDescripcionLote());
					}
					
					f.setNumeroLote(Constants.NRO_UNO);
				}

				List detalListAux = concursoNivelPremiacion.getListConcursoLotePremioArticulo();
			}

			List detalListAux = concursoNivelPremiacion.getListConcursoLotePremioArticulo();

			for (int j = 0; j < detalListAux.size(); j++) 
			{
				ConcursoLotePremioArticulo concursoLotePremioArticulo = (ConcursoLotePremioArticulo) detalListAux.get(j);

				if ((i == 0) && (j == 0)) 
				{
					ultimoNumeroPremio = concursoLotePremioArticulo.getNumeroPremio().intValue();
				} else 
				{
					// el numero de premio debe ser calculado en base al ultimo premio
					if (concursoLotePremioArticulo.getNumeroPremio().intValue() > ultimoNumeroPremio) 
					{
						ultimoNumeroPremio = concursoLotePremioArticulo.getNumeroPremio().intValue();
					}
				}
			}
		}

		// numeroLote es un correlativo por nivel, si el nivel es elegible
		if (Constants.NRO_UNO.equals(f.getIndicadorNivelSelectivo())) 
		{
			ultimoNumeroPremio++;
			f.setNumeroPremio(String.valueOf(ultimoNumeroPremio));
		} else 
		{
			if (concursoNivelPremiacionAct.getListConcursoLotePremioArticulo().size() == 0) 
			{
				ultimoNumeroPremio++;
				f.setNumeroPremio(String.valueOf(ultimoNumeroPremio));
			} else 
			{
				ConcursoLotePremioArticulo concursoLotePremioArticulo = (ConcursoLotePremioArticulo) concursoNivelPremiacionAct
						.getListConcursoLotePremioArticulo().get(0);
				f.setNumeroPremio(String.valueOf(concursoLotePremioArticulo.getNumeroPremio()));
			}

			if (ultimoNumeroPremio == 0) 
			{
				ultimoNumeroPremio++;
				f.setNumeroPremio(String.valueOf(ultimoNumeroPremio));
			}
		}

		f.setNumeroLoteUltimo(f.getNumeroLote());
		f.setDescripcionLoteUltimo(f.getDescripcionLote());
		f.setNumeroPremioUltimo(f.getNumeroPremio());
	}

	@Override
	public String setValidarMantenimiento() {
		MantenimientoINCConfiguracionConcursoForm f = (MantenimientoINCConfiguracionConcursoForm) this.formMantenimiento;

		if (this.indicadorObtencionPuntosBoolean) {
			if (StringUtils.isBlank(f.getCodigoMensajePuntos())) {
				return this
						.getResourceMessage("mantenimientoINCConfiguracionConcursoForm.desmarcarPuntosObtenidos");
			}
		}

		if (StringUtils.isBlank(f.getOidTipoConcurso())) {
			return "'Tipo Concurso' es un campo requerido.";
		}

		if (StringUtils.isBlank(f.getNombreConcurso())) {
			return "'Nombre Concurso' es un campo requerido.";
		}

		if (StringUtils.isBlank(f.getOidBaseCalculo())) {
			return "'Base Calculo' es un campo requerido.";
		}

		if (StringUtils.isBlank(f.getCodigoPeriodoInicio())) {
			return "'Campaña Inicio' es un campo requerido.";
		}

		if (StringUtils.isBlank(f.getCodigoPeriodoFin())) {
			return "'Campaña Fin' es un campo requerido.";
		}

		return null;
	}

	// ------------------------FIN DEFINIR PREMIO DESCUENTO

	public void validarConstancia(ValueChangeEvent event) {
		indicadorActividadBoolean = false;
	}

	public void validarActividad(ValueChangeEvent event) {
		indicadorConstanciaBoolean = false;
	}

	public void generarIndexBonificacion(List lista) {
		// GENERAR INDEX
		for (int i = 0; i < lista.size(); i++) {
			ConcursoProductosBonificados data = (ConcursoProductosBonificados) lista
					.get(i);
			data.setIndex("" + i + "");
		}
	}

	public void generarIndexExcluidos(List lista) {
		// GENERAR INDEX
		for (int i = 0; i < lista.size(); i++) {
			ConcursoProductosExcluidos data = (ConcursoProductosExcluidos) lista
					.get(i);
			data.setIndex("" + i + "");
		}
	}

	public void generarIndexExigidos(List lista) {
		// GENERAR INDEX
		for (int i = 0; i < lista.size(); i++) {
			ConcursoProductosExigidos data = (ConcursoProductosExigidos) lista
					.get(i);
			data.setIndex("" + i + "");
		}
	}

	public void generarIndexValidos(List lista) {
		// GENERAR INDEX
		for (int i = 0; i < lista.size(); i++) {
			ConcursoProductosValidos data = (ConcursoProductosValidos) lista
					.get(i);
			data.setIndex("" + i + "");
		}
	}

	public void validarCampaniaInicio(String valor) {
		MantenimientoINCConfiguracionConcursoForm f = (MantenimientoINCConfiguracionConcursoForm) this.formMantenimiento;
		String oidTipoConcurso = f.getOidTipoConcurso();
		f.setCodigoPeriodoInicio(valor);
		if (oidTipoConcurso.equals(f.getOidTipoConcursoRecomendacion()))
			cambiarPeriodoEvaluacion(oidTipoConcurso);
		else
			f.setCodigoPeriodoEvaluacion(null);

	}

	public void validarCampaniaFin(String valor) {
		MantenimientoINCConfiguracionConcursoForm f = (MantenimientoINCConfiguracionConcursoForm) this.formMantenimiento;
		String oidTipoConcurso = f.getOidTipoConcurso();
		f.setCodigoPeriodoFin(valor);
		if (oidTipoConcurso.equals(f.getOidTipoConcursoRecomendacion()))
			cambiarPeriodoEvaluacion(oidTipoConcurso);
		else
			f.setCodigoPeriodoEvaluacion(null);

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
	 * @return the incClasificacionConcursoList
	 */
	public List getIncClasificacionConcursoList() {
		return incClasificacionConcursoList;
	}

	/**
	 * @param incClasificacionConcursoList
	 *            the incClasificacionConcursoList to set
	 */
	public void setIncClasificacionConcursoList(
			List incClasificacionConcursoList) {
		this.incClasificacionConcursoList = incClasificacionConcursoList;
	}

	/**
	 * @return the incEstadoList
	 */
	public List getIncEstadoList() {
		return incEstadoList;
	}

	/**
	 * @param incEstadoList
	 *            the incEstadoList to set
	 */
	public void setIncEstadoList(List incEstadoList) {
		this.incEstadoList = incEstadoList;
	}

	/**
	 * @return the incVigenciaList
	 */
	public List getIncVigenciaList() {
		return incVigenciaList;
	}

	/**
	 * @param incVigenciaList
	 *            the incVigenciaList to set
	 */
	public void setIncVigenciaList(List incVigenciaList) {
		this.incVigenciaList = incVigenciaList;
	}

	/**
	 * @return the incConcursoList
	 */
	public List getIncConcursoList() {
		return incConcursoList;
	}

	/**
	 * @param incConcursoList
	 *            the incConcursoList to set
	 */
	public void setIncConcursoList(List incConcursoList) {
		this.incConcursoList = incConcursoList;
	}

	/**
	 * @return the service
	 */
	public MantenimientoINCConfiguracionConcursoService getService() {
		return service;
	}

	/**
	 * @param service
	 *            the service to set
	 */
	public void setService(MantenimientoINCConfiguracionConcursoService service) {
		this.service = service;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the incReemplazosPendientesList
	 */
	public List getIncReemplazosPendientesList() {
		return incReemplazosPendientesList;
	}

	/**
	 * @param incReemplazosPendientesList
	 *            the incReemplazosPendientesList to set
	 */
	public void setIncReemplazosPendientesList(List incReemplazosPendientesList) {
		this.incReemplazosPendientesList = incReemplazosPendientesList;
	}

	/**
	 * @return the mantenerReemplazosAction
	 */
	public MantenimientoINCReemplazosSearchAction getMantenerReemplazosAction() {
		return mantenerReemplazosAction;
	}

	/**
	 * @param mantenerReemplazosAction
	 *            the mantenerReemplazosAction to set
	 */
	public void setMantenerReemplazosAction(
			MantenimientoINCReemplazosSearchAction mantenerReemplazosAction) {
		this.mantenerReemplazosAction = mantenerReemplazosAction;
	}

	/**
	 * @return the incBaseCalculoList
	 */
	public List getIncBaseCalculoList() {
		return incBaseCalculoList;
	}

	/**
	 * @param incBaseCalculoList
	 *            the incBaseCalculoList to set
	 */
	public void setIncBaseCalculoList(List incBaseCalculoList) {
		this.incBaseCalculoList = incBaseCalculoList;
	}

	/**
	 * @return the siccSubGerenciaList
	 */
	public List getSiccSubGerenciaList() {
		return siccSubGerenciaList;
	}

	/**
	 * @param siccSubGerenciaList
	 *            the siccSubGerenciaList to set
	 */
	public void setSiccSubGerenciaList(List siccSubGerenciaList) {
		this.siccSubGerenciaList = siccSubGerenciaList;
	}

	/**
	 * @return the incTipoProgramaList
	 */
	public List getIncTipoProgramaList() {
		return incTipoProgramaList;
	}

	/**
	 * @param incTipoProgramaList
	 *            the incTipoProgramaList to set
	 */
	public void setIncTipoProgramaList(List incTipoProgramaList) {
		this.incTipoProgramaList = incTipoProgramaList;
	}

	/**
	 * @return the incDirigidosList
	 */
	public List getIncDirigidosList() {
		return incDirigidosList;
	}

	/**
	 * @param incDirigidosList
	 *            the incDirigidosList to set
	 */
	public void setIncDirigidosList(List incDirigidosList) {
		this.incDirigidosList = incDirigidosList;
	}

	/**
	 * @return the incTipoVentaList
	 */
	public List getIncTipoVentaList() {
		return incTipoVentaList;
	}

	/**
	 * @param incTipoVentaList
	 *            the incTipoVentaList to set
	 */
	public void setIncTipoVentaList(List incTipoVentaList) {
		this.incTipoVentaList = incTipoVentaList;
	}

	/**
	 * @return the incTipoExigenciaList
	 */
	public List getIncTipoExigenciaList() {
		return incTipoExigenciaList;
	}

	/**
	 * @param incTipoExigenciaList
	 *            the incTipoExigenciaList to set
	 */
	public void setIncTipoExigenciaList(List incTipoExigenciaList) {
		this.incTipoExigenciaList = incTipoExigenciaList;
	}

	/**
	 * @return the incConcursoRecomendadasList
	 */
	public List getIncConcursoRecomendadasList() {
		return incConcursoRecomendadasList;
	}

	/**
	 * @param incConcursoRecomendadasList
	 *            the incConcursoRecomendadasList to set
	 */
	public void setIncConcursoRecomendadasList(List incConcursoRecomendadasList) {
		this.incConcursoRecomendadasList = incConcursoRecomendadasList;
	}

	/**
	 * @return the incEstatusClienteList
	 */
	public List getIncEstatusClienteList() {
		return incEstatusClienteList;
	}

	/**
	 * @param incEstatusClienteList
	 *            the incEstatusClienteList to set
	 */
	public void setIncEstatusClienteList(List incEstatusClienteList) {
		this.incEstatusClienteList = incEstatusClienteList;
	}

	/**
	 * @return the incClasificacionesParticipantesList
	 */
	public List getIncClasificacionesParticipantesList() {
		return incClasificacionesParticipantesList;
	}

	/**
	 * @param incClasificacionesParticipantesList
	 *            the incClasificacionesParticipantesList to set
	 */
	public void setIncClasificacionesParticipantesList(
			List incClasificacionesParticipantesList) {
		this.incClasificacionesParticipantesList = incClasificacionesParticipantesList;
	}

	/**
	 * @return the incTiposPremiacionList
	 */
	public List getIncTiposPremiacionList() {
		return incTiposPremiacionList;
	}

	/**
	 * @param incTiposPremiacionList
	 *            the incTiposPremiacionList to set
	 */
	public void setIncTiposPremiacionList(List incTiposPremiacionList) {
		this.incTiposPremiacionList = incTiposPremiacionList;
	}

	/**
	 * @return the incTiposEleccionList
	 */
	public List getIncTiposEleccionList() {
		return incTiposEleccionList;
	}

	/**
	 * @param incTiposEleccionList
	 *            the incTiposEleccionList to set
	 */
	public void setIncTiposEleccionList(List incTiposEleccionList) {
		this.incTiposEleccionList = incTiposEleccionList;
	}

	/**
	 * @return the incCentrosServicioList
	 */
	public List getIncCentrosServicioList() {
		return incCentrosServicioList;
	}

	/**
	 * @param incCentrosServicioList
	 *            the incCentrosServicioList to set
	 */
	public void setIncCentrosServicioList(List incCentrosServicioList) {
		this.incCentrosServicioList = incCentrosServicioList;
	}

	/**
	 * @return the incIndicadorTipoRecomendacionList
	 */
	public List getIncIndicadorTipoRecomendacionList() {
		return incIndicadorTipoRecomendacionList;
	}

	/**
	 * @param incIndicadorTipoRecomendacionList
	 *            the incIndicadorTipoRecomendacionList to set
	 */
	public void setIncIndicadorTipoRecomendacionList(
			List incIndicadorTipoRecomendacionList) {
		this.incIndicadorTipoRecomendacionList = incIndicadorTipoRecomendacionList;
	}

	/**
	 * @return the incGenericoList
	 */
	public List getIncGenericoList() {
		return incGenericoList;
	}

	/**
	 * @param incGenericoList
	 *            the incGenericoList to set
	 */
	public void setIncGenericoList(List incGenericoList) {
		this.incGenericoList = incGenericoList;
	}

	/**
	 * @return the incSuperGenericoList
	 */
	public List getIncSuperGenericoList() {
		return incSuperGenericoList;
	}

	/**
	 * @param incSuperGenericoList
	 *            the incSuperGenericoList to set
	 */
	public void setIncSuperGenericoList(List incSuperGenericoList) {
		this.incSuperGenericoList = incSuperGenericoList;
	}

	/**
	 * @return the incProductosValidosList
	 */
	public List getIncProductosValidosList() {
		return incProductosValidosList;
	}

	/**
	 * @param incProductosValidosList
	 *            the incProductosValidosList to set
	 */
	public void setIncProductosValidosList(List incProductosValidosList) {
		this.incProductosValidosList = incProductosValidosList;
	}

	/**
	 * @return the incProductosBonificadosList
	 */
	public List getIncProductosBonificadosList() {
		return incProductosBonificadosList;
	}

	/**
	 * @param incProductosBonificadosList
	 *            the incProductosBonificadosList to set
	 */
	public void setIncProductosBonificadosList(List incProductosBonificadosList) {
		this.incProductosBonificadosList = incProductosBonificadosList;
	}

	/**
	 * @return the incProductosExcluidosList
	 */
	public List getIncProductosExcluidosList() {
		return incProductosExcluidosList;
	}

	/**
	 * @param incProductosExcluidosList
	 *            the incProductosExcluidosList to set
	 */
	public void setIncProductosExcluidosList(List incProductosExcluidosList) {
		this.incProductosExcluidosList = incProductosExcluidosList;
	}

	/**
	 * @return the incProductosExigidosList
	 */
	public List getIncProductosExigidosList() {
		return incProductosExigidosList;
	}

	/**
	 * @param incProductosExigidosList
	 *            the incProductosExigidosList to set
	 */
	public void setIncProductosExigidosList(List incProductosExigidosList) {
		this.incProductosExigidosList = incProductosExigidosList;
	}

	/**
	 * @return the incBloqueProductoList
	 */
	public List getIncBloqueProductoList() {
		return incBloqueProductoList;
	}

	/**
	 * @param incBloqueProductoList
	 *            the incBloqueProductoList to set
	 */
	public void setIncBloqueProductoList(List incBloqueProductoList) {
		this.incBloqueProductoList = incBloqueProductoList;
	}

	/**
	 * @return the incTipoProductoList
	 */
	public List getIncTipoProductoList() {
		return incTipoProductoList;
	}

	/**
	 * @param incTipoProductoList
	 *            the incTipoProductoList to set
	 */
	public void setIncTipoProductoList(List incTipoProductoList) {
		this.incTipoProductoList = incTipoProductoList;
	}

	/**
	 * @return the incTipoOfertaList
	 */
	public List getIncTipoOfertaList() {
		return incTipoOfertaList;
	}

	/**
	 * @param incTipoOfertaList
	 *            the incTipoOfertaList to set
	 */
	public void setIncTipoOfertaList(List incTipoOfertaList) {
		this.incTipoOfertaList = incTipoOfertaList;
	}

	/**
	 * @return the incTipoAgrupacionList
	 */
	public List getIncTipoAgrupacionList() {
		return incTipoAgrupacionList;
	}

	/**
	 * @param incTipoAgrupacionList
	 *            the incTipoAgrupacionList to set
	 */
	public void setIncTipoAgrupacionList(List incTipoAgrupacionList) {
		this.incTipoAgrupacionList = incTipoAgrupacionList;
	}

	/**
	 * @return the incUnidadNegocioList
	 */
	public List getIncUnidadNegocioList() {
		return incUnidadNegocioList;
	}

	/**
	 * @param incUnidadNegocioList
	 *            the incUnidadNegocioList to set
	 */
	public void setIncUnidadNegocioList(List incUnidadNegocioList) {
		this.incUnidadNegocioList = incUnidadNegocioList;
	}

	/**
	 * @return the incNegocioList
	 */
	public List getIncNegocioList() {
		return incNegocioList;
	}

	/**
	 * @param incNegocioList
	 *            the incNegocioList to set
	 */
	public void setIncNegocioList(List incNegocioList) {
		this.incNegocioList = incNegocioList;
	}

	/**
	 * @return the incCicloVidaList
	 */
	public List getIncCicloVidaList() {
		return incCicloVidaList;
	}

	/**
	 * @param incCicloVidaList
	 *            the incCicloVidaList to set
	 */
	public void setIncCicloVidaList(List incCicloVidaList) {
		this.incCicloVidaList = incCicloVidaList;
	}

	/**
	 * @return the incMarcaProductoList
	 */
	public List getIncMarcaProductoList() {
		return incMarcaProductoList;
	}

	/**
	 * @param incMarcaProductoList
	 *            the incMarcaProductoList to set
	 */
	public void setIncMarcaProductoList(List incMarcaProductoList) {
		this.incMarcaProductoList = incMarcaProductoList;
	}

	/**
	 * @return the incAmbitoList
	 */
	public List getIncAmbitoList() {
		return incAmbitoList;
	}

	/**
	 * @param incAmbitoList
	 *            the incAmbitoList to set
	 */
	public void setIncAmbitoList(List incAmbitoList) {
		this.incAmbitoList = incAmbitoList;
	}

	/**
	 * @return the incConcursoEstatusList
	 */
	public List getIncConcursoEstatusList() {
		return incConcursoEstatusList;
	}

	/**
	 * @param incConcursoEstatusList
	 *            the incConcursoEstatusList to set
	 */
	public void setIncConcursoEstatusList(List incConcursoEstatusList) {
		this.incConcursoEstatusList = incConcursoEstatusList;
	}

	/**
	 * @return the incConcursoClasificacionesList
	 */
	public List getIncConcursoClasificacionesList() {
		return incConcursoClasificacionesList;
	}

	/**
	 * @param incConcursoClasificacionesList
	 *            the incConcursoClasificacionesList to set
	 */
	public void setIncConcursoClasificacionesList(
			List incConcursoClasificacionesList) {
		this.incConcursoClasificacionesList = incConcursoClasificacionesList;
	}

	/**
	 * @return the incArticulosLoteList
	 */
	public List getIncArticulosLoteList() {
		return incArticulosLoteList;
	}

	/**
	 * @param incArticulosLoteList
	 *            the incArticulosLoteList to set
	 */
	public void setIncArticulosLoteList(List incArticulosLoteList) {
		this.incArticulosLoteList = incArticulosLoteList;
	}

	/**
	 * @return the incNivelesPremiacionList
	 */
	public List getIncNivelesPremiacionList() {
		return incNivelesPremiacionList;
	}

	/**
	 * @param incNivelesPremiacionList
	 *            the incNivelesPremiacionList to set
	 */
	public void setIncNivelesPremiacionList(List incNivelesPremiacionList) {
		this.incNivelesPremiacionList = incNivelesPremiacionList;
	}

	/**
	 * @return the incRecomendacionPeriodoList
	 */
	public List getIncRecomendacionPeriodoList() {
		return incRecomendacionPeriodoList;
	}

	/**
	 * @param incRecomendacionPeriodoList
	 *            the incRecomendacionPeriodoList to set
	 */
	public void setIncRecomendacionPeriodoList(List incRecomendacionPeriodoList) {
		this.incRecomendacionPeriodoList = incRecomendacionPeriodoList;
	}

	/**
	 * @return the incBonificacionPeriodoList
	 */
	public List getIncBonificacionPeriodoList() {
		return incBonificacionPeriodoList;
	}

	/**
	 * @param incBonificacionPeriodoList
	 *            the incBonificacionPeriodoList to set
	 */
	public void setIncBonificacionPeriodoList(List incBonificacionPeriodoList) {
		this.incBonificacionPeriodoList = incBonificacionPeriodoList;
	}

	/**
	 * @return the incPeriodoDespachoList
	 */
	public List getIncPeriodoDespachoList() {
		return incPeriodoDespachoList;
	}

	/**
	 * @param incPeriodoDespachoList
	 *            the incPeriodoDespachoList to set
	 */
	public void setIncPeriodoDespachoList(List incPeriodoDespachoList) {
		this.incPeriodoDespachoList = incPeriodoDespachoList;
	}

	/**
	 * @return the incPuntajeExigidoList
	 */
	public List getIncPuntajeExigidoList() {
		return incPuntajeExigidoList;
	}

	/**
	 * @param incPuntajeExigidoList
	 *            the incPuntajeExigidoList to set
	 */
	public void setIncPuntajeExigidoList(List incPuntajeExigidoList) {
		this.incPuntajeExigidoList = incPuntajeExigidoList;
	}

	/**
	 * @return the incPuntajeExigidoListTempo
	 */
	public List getIncPuntajeExigidoListTempo() {
		return incPuntajeExigidoListTempo;
	}

	/**
	 * @param incPuntajeExigidoListTempo
	 *            the incPuntajeExigidoListTempo to set
	 */
	public void setIncPuntajeExigidoListTempo(List incPuntajeExigidoListTempo) {
		this.incPuntajeExigidoListTempo = incPuntajeExigidoListTempo;
	}

	/**
	 * @return the incDtoConcurso
	 */
	public ConcursoParametrosGenerales getIncDtoConcurso() {
		return incDtoConcurso;
	}

	/**
	 * @param incDtoConcurso
	 *            the incDtoConcurso to set
	 */
	public void setIncDtoConcurso(ConcursoParametrosGenerales incDtoConcurso) {
		this.incDtoConcurso = incDtoConcurso;
	}

	/**
	 * @return the indicadorMultiMarcaBoolean
	 */
	public boolean isIndicadorMultiMarcaBoolean() {
		return indicadorMultiMarcaBoolean;
	}

	/**
	 * @param indicadorMultiMarcaBoolean
	 *            the indicadorMultiMarcaBoolean to set
	 */
	public void setIndicadorMultiMarcaBoolean(boolean indicadorMultiMarcaBoolean) {
		this.indicadorMultiMarcaBoolean = indicadorMultiMarcaBoolean;
	}

	/**
	 * @return the faltantesNoAnunciadosBoolean
	 */
	public boolean isFaltantesNoAnunciadosBoolean() {
		return faltantesNoAnunciadosBoolean;
	}

	/**
	 * @param faltantesNoAnunciadosBoolean
	 *            the faltantesNoAnunciadosBoolean to set
	 */
	public void setFaltantesNoAnunciadosBoolean(
			boolean faltantesNoAnunciadosBoolean) {
		this.faltantesNoAnunciadosBoolean = faltantesNoAnunciadosBoolean;
	}

	/**
	 * @return the indicadorPuntajeAcumulativoBoolean
	 */
	public boolean isIndicadorPuntajeAcumulativoBoolean() {
		return indicadorPuntajeAcumulativoBoolean;
	}

	/**
	 * @param indicadorPuntajeAcumulativoBoolean
	 *            the indicadorPuntajeAcumulativoBoolean to set
	 */
	public void setIndicadorPuntajeAcumulativoBoolean(
			boolean indicadorPuntajeAcumulativoBoolean) {
		this.indicadorPuntajeAcumulativoBoolean = indicadorPuntajeAcumulativoBoolean;
	}

	/**
	 * @return the indicadorDevolucionesBoolean
	 */
	public boolean isIndicadorDevolucionesBoolean() {
		return indicadorDevolucionesBoolean;
	}

	/**
	 * @param indicadorDevolucionesBoolean
	 *            the indicadorDevolucionesBoolean to set
	 */
	public void setIndicadorDevolucionesBoolean(
			boolean indicadorDevolucionesBoolean) {
		this.indicadorDevolucionesBoolean = indicadorDevolucionesBoolean;
	}

	/**
	 * @return the indicadorAnulacionesBoolean
	 */
	public boolean isIndicadorAnulacionesBoolean() {
		return indicadorAnulacionesBoolean;
	}

	/**
	 * @param indicadorAnulacionesBoolean
	 *            the indicadorAnulacionesBoolean to set
	 */
	public void setIndicadorAnulacionesBoolean(
			boolean indicadorAnulacionesBoolean) {
		this.indicadorAnulacionesBoolean = indicadorAnulacionesBoolean;
	}

	/**
	 * @return the indicadorActividadBoolean
	 */
	public boolean isIndicadorActividadBoolean() {
		return indicadorActividadBoolean;
	}

	/**
	 * @param indicadorActividadBoolean
	 *            the indicadorActividadBoolean to set
	 */
	public void setIndicadorActividadBoolean(boolean indicadorActividadBoolean) {
		this.indicadorActividadBoolean = indicadorActividadBoolean;
	}

	/**
	 * @return the indicadorConstanciaBoolean
	 */
	public boolean isIndicadorConstanciaBoolean() {
		return indicadorConstanciaBoolean;
	}

	/**
	 * @param indicadorConstanciaBoolean
	 *            the indicadorConstanciaBoolean to set
	 */
	public void setIndicadorConstanciaBoolean(boolean indicadorConstanciaBoolean) {
		this.indicadorConstanciaBoolean = indicadorConstanciaBoolean;
	}

	/**
	 * @return the indicadorObtencionPuntosBoolean
	 */
	public boolean isIndicadorObtencionPuntosBoolean() {
		return indicadorObtencionPuntosBoolean;
	}

	/**
	 * @param indicadorObtencionPuntosBoolean
	 *            the indicadorObtencionPuntosBoolean to set
	 */
	public void setIndicadorObtencionPuntosBoolean(
			boolean indicadorObtencionPuntosBoolean) {
		this.indicadorObtencionPuntosBoolean = indicadorObtencionPuntosBoolean;
	}

	/**
	 * @return the indicadorComunicacionBoolean
	 */
	public boolean isIndicadorComunicacionBoolean() {
		return indicadorComunicacionBoolean;
	}

	/**
	 * @param indicadorComunicacionBoolean
	 *            the indicadorComunicacionBoolean to set
	 */
	public void setIndicadorComunicacionBoolean(
			boolean indicadorComunicacionBoolean) {
		this.indicadorComunicacionBoolean = indicadorComunicacionBoolean;
	}

	/**
	 * @return the indicadorCPPBoolean
	 */
	public boolean isIndicadorCPPBoolean() {
		return indicadorCPPBoolean;
	}

	/**
	 * @param indicadorCPPBoolean
	 *            the indicadorCPPBoolean to set
	 */
	public void setIndicadorCPPBoolean(boolean indicadorCPPBoolean) {
		this.indicadorCPPBoolean = indicadorCPPBoolean;
	}

	/**
	 * @return the indicadorExigePasarPedidoCampanyaAnteriorCPPBoolean
	 */
	public boolean isIndicadorExigePasarPedidoCampanyaAnteriorCPPBoolean() {
		return indicadorExigePasarPedidoCampanyaAnteriorCPPBoolean;
	}

	/**
	 * @param indicadorExigePasarPedidoCampanyaAnteriorCPPBoolean
	 *            the indicadorExigePasarPedidoCampanyaAnteriorCPPBoolean to set
	 */
	public void setIndicadorExigePasarPedidoCampanyaAnteriorCPPBoolean(
			boolean indicadorExigePasarPedidoCampanyaAnteriorCPPBoolean) {
		this.indicadorExigePasarPedidoCampanyaAnteriorCPPBoolean = indicadorExigePasarPedidoCampanyaAnteriorCPPBoolean;
	}

	/**
	 * @return the incConcursoProgramaPuntosTipoDespachoList
	 */
	public LabelValue[] getIncConcursoProgramaPuntosTipoDespachoList() {
		return incConcursoProgramaPuntosTipoDespachoList;
	}

	/**
	 * @param incConcursoProgramaPuntosTipoDespachoList
	 *            the incConcursoProgramaPuntosTipoDespachoList to set
	 */
	public void setIncConcursoProgramaPuntosTipoDespachoList(
			LabelValue[] incConcursoProgramaPuntosTipoDespachoList) {
		this.incConcursoProgramaPuntosTipoDespachoList = incConcursoProgramaPuntosTipoDespachoList;
	}

	/**
	 * @return the indicadorPedidoEnPeriodoBoolean
	 */
	public boolean isIndicadorPedidoEnPeriodoBoolean() {
		return indicadorPedidoEnPeriodoBoolean;
	}

	/**
	 * @param indicadorPedidoEnPeriodoBoolean
	 *            the indicadorPedidoEnPeriodoBoolean to set
	 */
	public void setIndicadorPedidoEnPeriodoBoolean(
			boolean indicadorPedidoEnPeriodoBoolean) {
		this.indicadorPedidoEnPeriodoBoolean = indicadorPedidoEnPeriodoBoolean;
	}

	/**
	 * @return the premiosAcumulativosNivelesBoolean
	 */
	public boolean isPremiosAcumulativosNivelesBoolean() {
		return premiosAcumulativosNivelesBoolean;
	}

	/**
	 * @param premiosAcumulativosNivelesBoolean
	 *            the premiosAcumulativosNivelesBoolean to set
	 */
	public void setPremiosAcumulativosNivelesBoolean(
			boolean premiosAcumulativosNivelesBoolean) {
		this.premiosAcumulativosNivelesBoolean = premiosAcumulativosNivelesBoolean;
	}

	/**
	 * @return the accesoNivelSuperiorBoolean
	 */
	public boolean isAccesoNivelSuperiorBoolean() {
		return accesoNivelSuperiorBoolean;
	}

	/**
	 * @param accesoNivelSuperiorBoolean
	 *            the accesoNivelSuperiorBoolean to set
	 */
	public void setAccesoNivelSuperiorBoolean(boolean accesoNivelSuperiorBoolean) {
		this.accesoNivelSuperiorBoolean = accesoNivelSuperiorBoolean;
	}

	/**
	 * @return the indicadorNivelesRotativosBoolean
	 */
	public boolean isIndicadorNivelesRotativosBoolean() {
		return indicadorNivelesRotativosBoolean;
	}

	/**
	 * @param indicadorNivelesRotativosBoolean
	 *            the indicadorNivelesRotativosBoolean to set
	 */
	public void setIndicadorNivelesRotativosBoolean(
			boolean indicadorNivelesRotativosBoolean) {
		this.indicadorNivelesRotativosBoolean = indicadorNivelesRotativosBoolean;
	}

	/**
	 * @return the indicadorRangoPedidosBoolean
	 */
	public boolean isIndicadorRangoPedidosBoolean() {
		return indicadorRangoPedidosBoolean;
	}

	/**
	 * @param indicadorRangoPedidosBoolean
	 *            the indicadorRangoPedidosBoolean to set
	 */
	public void setIndicadorRangoPedidosBoolean(
			boolean indicadorRangoPedidosBoolean) {
		this.indicadorRangoPedidosBoolean = indicadorRangoPedidosBoolean;
	}

	/**
	 * @return the indicadorPorPedidoBoolean
	 */
	public boolean isIndicadorPorPedidoBoolean() {
		return indicadorPorPedidoBoolean;
	}

	/**
	 * @param indicadorPorPedidoBoolean
	 *            the indicadorPorPedidoBoolean to set
	 */
	public void setIndicadorPorPedidoBoolean(boolean indicadorPorPedidoBoolean) {
		this.indicadorPorPedidoBoolean = indicadorPorPedidoBoolean;
	}

	/**
	 * @return the indicadorPremioCampEfectBoolean
	 */
	public boolean isIndicadorPremioCampEfectBoolean() {
		return indicadorPremioCampEfectBoolean;
	}

	/**
	 * @param indicadorPremioCampEfectBoolean
	 *            the indicadorPremioCampEfectBoolean to set
	 */
	public void setIndicadorPremioCampEfectBoolean(
			boolean indicadorPremioCampEfectBoolean) {
		this.indicadorPremioCampEfectBoolean = indicadorPremioCampEfectBoolean;
	}

	/**
	 * @return the generarPuntajeARecomendadasBoolean
	 */
	public boolean isGenerarPuntajeARecomendadasBoolean() {
		return generarPuntajeARecomendadasBoolean;
	}

	/**
	 * @param generarPuntajeARecomendadasBoolean
	 *            the generarPuntajeARecomendadasBoolean to set
	 */
	public void setGenerarPuntajeARecomendadasBoolean(
			boolean generarPuntajeARecomendadasBoolean) {
		this.generarPuntajeARecomendadasBoolean = generarPuntajeARecomendadasBoolean;
	}

	/**
	 * @return the habilitarMontoVenta
	 */
	public boolean isHabilitarMontoVenta() {
		return habilitarMontoVenta;
	}

	/**
	 * @param habilitarMontoVenta
	 *            the habilitarMontoVenta to set
	 */
	public void setHabilitarMontoVenta(boolean habilitarMontoVenta) {
		this.habilitarMontoVenta = habilitarMontoVenta;
	}

	/**
	 * @return the indicadorRangoPedidosDisabled
	 */
	public boolean isIndicadorRangoPedidosDisabled() {
		return indicadorRangoPedidosDisabled;
	}

	/**
	 * @param indicadorRangoPedidosDisabled
	 *            the indicadorRangoPedidosDisabled to set
	 */
	public void setIndicadorRangoPedidosDisabled(
			boolean indicadorRangoPedidosDisabled) {
		this.indicadorRangoPedidosDisabled = indicadorRangoPedidosDisabled;
	}

	/**
	 * @return the indicadorPorPedidoDisabled
	 */
	public boolean isIndicadorPorPedidoDisabled() {
		return indicadorPorPedidoDisabled;
	}

	/**
	 * @param indicadorPorPedidoDisabled
	 *            the indicadorPorPedidoDisabled to set
	 */
	public void setIndicadorPorPedidoDisabled(boolean indicadorPorPedidoDisabled) {
		this.indicadorPorPedidoDisabled = indicadorPorPedidoDisabled;
	}

	/**
	 * @return the numeroMinimoPedidosRecomendadaDisabled
	 */
	public boolean isNumeroMinimoPedidosRecomendadaDisabled() {
		return numeroMinimoPedidosRecomendadaDisabled;
	}

	/**
	 * @param numeroMinimoPedidosRecomendadaDisabled
	 *            the numeroMinimoPedidosRecomendadaDisabled to set
	 */
	public void setNumeroMinimoPedidosRecomendadaDisabled(
			boolean numeroMinimoPedidosRecomendadaDisabled) {
		this.numeroMinimoPedidosRecomendadaDisabled = numeroMinimoPedidosRecomendadaDisabled;
	}

	/**
	 * @return the indicadorPremioCampEfectDisabled
	 */
	public boolean isIndicadorPremioCampEfectDisabled() {
		return indicadorPremioCampEfectDisabled;
	}

	/**
	 * @param indicadorPremioCampEfectDisabled
	 *            the indicadorPremioCampEfectDisabled to set
	 */
	public void setIndicadorPremioCampEfectDisabled(
			boolean indicadorPremioCampEfectDisabled) {
		this.indicadorPremioCampEfectDisabled = indicadorPremioCampEfectDisabled;
	}

	/**
	 * @return the generarPuntajeARecomendadasDisabled
	 */
	public boolean isGenerarPuntajeARecomendadasDisabled() {
		return generarPuntajeARecomendadasDisabled;
	}

	/**
	 * @param generarPuntajeARecomendadasDisabled
	 *            the generarPuntajeARecomendadasDisabled to set
	 */
	public void setGenerarPuntajeARecomendadasDisabled(
			boolean generarPuntajeARecomendadasDisabled) {
		this.generarPuntajeARecomendadasDisabled = generarPuntajeARecomendadasDisabled;
	}

	/**
	 * @return the multiMarcaDisabled
	 */
	public boolean isMultiMarcaDisabled() {
		return multiMarcaDisabled;
	}

	/**
	 * @param multiMarcaDisabled
	 *            the multiMarcaDisabled to set
	 */
	public void setMultiMarcaDisabled(boolean multiMarcaDisabled) {
		this.multiMarcaDisabled = multiMarcaDisabled;
	}

	/**
	 * @return the oidBaseCalculoDisabled
	 */
	public boolean isOidBaseCalculoDisabled() {
		return oidBaseCalculoDisabled;
	}

	/**
	 * @param oidBaseCalculoDisabled
	 *            the oidBaseCalculoDisabled to set
	 */
	public void setOidBaseCalculoDisabled(boolean oidBaseCalculoDisabled) {
		this.oidBaseCalculoDisabled = oidBaseCalculoDisabled;
	}

	/**
	 * @return the codigoMensajePuntosDisabled
	 */
	public boolean isCodigoMensajePuntosDisabled() {
		return codigoMensajePuntosDisabled;
	}

	/**
	 * @param codigoMensajePuntosDisabled
	 *            the codigoMensajePuntosDisabled to set
	 */
	public void setCodigoMensajePuntosDisabled(
			boolean codigoMensajePuntosDisabled) {
		this.codigoMensajePuntosDisabled = codigoMensajePuntosDisabled;
	}

	/**
	 * @return the puntosAbonarRecomendacionEfectivaCPPDisabled
	 */
	public boolean isPuntosAbonarRecomendacionEfectivaCPPDisabled() {
		return puntosAbonarRecomendacionEfectivaCPPDisabled;
	}

	/**
	 * @param puntosAbonarRecomendacionEfectivaCPPDisabled
	 *            the puntosAbonarRecomendacionEfectivaCPPDisabled to set
	 */
	public void setPuntosAbonarRecomendacionEfectivaCPPDisabled(
			boolean puntosAbonarRecomendacionEfectivaCPPDisabled) {
		this.puntosAbonarRecomendacionEfectivaCPPDisabled = puntosAbonarRecomendacionEfectivaCPPDisabled;
	}

	/**
	 * @return the campanyasSinPedidoParaCancelacionPuntosCPPDisabled
	 */
	public boolean isCampanyasSinPedidoParaCancelacionPuntosCPPDisabled() {
		return campanyasSinPedidoParaCancelacionPuntosCPPDisabled;
	}

	/**
	 * @param campanyasSinPedidoParaCancelacionPuntosCPPDisabled
	 *            the campanyasSinPedidoParaCancelacionPuntosCPPDisabled to set
	 */
	public void setCampanyasSinPedidoParaCancelacionPuntosCPPDisabled(
			boolean campanyasSinPedidoParaCancelacionPuntosCPPDisabled) {
		this.campanyasSinPedidoParaCancelacionPuntosCPPDisabled = campanyasSinPedidoParaCancelacionPuntosCPPDisabled;
	}

	/**
	 * @return the codigoCPPDisabled
	 */
	public boolean isCodigoCPPDisabled() {
		return codigoCPPDisabled;
	}

	/**
	 * @param codigoCPPDisabled
	 *            the codigoCPPDisabled to set
	 */
	public void setCodigoCPPDisabled(boolean codigoCPPDisabled) {
		this.codigoCPPDisabled = codigoCPPDisabled;
	}

	/**
	 * @return the cuotaIngresoDisabled
	 */
	public boolean isCuotaIngresoDisabled() {
		return cuotaIngresoDisabled;
	}

	/**
	 * @param cuotaIngresoDisabled
	 *            the cuotaIngresoDisabled to set
	 */
	public void setCuotaIngresoDisabled(boolean cuotaIngresoDisabled) {
		this.cuotaIngresoDisabled = cuotaIngresoDisabled;
	}

	/**
	 * @return the montoMinimoPedidoPremiacionDisabled
	 */
	public boolean isMontoMinimoPedidoPremiacionDisabled() {
		return montoMinimoPedidoPremiacionDisabled;
	}

	/**
	 * @param montoMinimoPedidoPremiacionDisabled
	 *            the montoMinimoPedidoPremiacionDisabled to set
	 */
	public void setMontoMinimoPedidoPremiacionDisabled(
			boolean montoMinimoPedidoPremiacionDisabled) {
		this.montoMinimoPedidoPremiacionDisabled = montoMinimoPedidoPremiacionDisabled;
	}

	/**
	 * @return the hastaNivelDisabled
	 */
	public boolean isHastaNivelDisabled() {
		return hastaNivelDisabled;
	}

	/**
	 * @param hastaNivelDisabled
	 *            the hastaNivelDisabled to set
	 */
	public void setHastaNivelDisabled(boolean hastaNivelDisabled) {
		this.hastaNivelDisabled = hastaNivelDisabled;
	}

	/**
	 * @return the numeroRotacionesDisabled
	 */
	public boolean isNumeroRotacionesDisabled() {
		return numeroRotacionesDisabled;
	}

	/**
	 * @param numeroRotacionesDisabled
	 *            the numeroRotacionesDisabled to set
	 */
	public void setNumeroRotacionesDisabled(boolean numeroRotacionesDisabled) {
		this.numeroRotacionesDisabled = numeroRotacionesDisabled;
	}

	/**
	 * @return the dataTableModelPremiacion
	 */
	public DataTableModel getDataTableModelPremiacion() {
		return dataTableModelPremiacion;
	}

	/**
	 * @param dataTableModelPremiacion
	 *            the dataTableModelPremiacion to set
	 */
	public void setDataTableModelPremiacion(
			DataTableModel dataTableModelPremiacion) {
		this.dataTableModelPremiacion = dataTableModelPremiacion;
	}

	/**
	 * @return the indRedefinirNivelPremiacion
	 */
	public String getIndRedefinirNivelPremiacion() {
		return indRedefinirNivelPremiacion;
	}

	/**
	 * @param indRedefinirNivelPremiacion
	 *            the indRedefinirNivelPremiacion to set
	 */
	public void setIndRedefinirNivelPremiacion(
			String indRedefinirNivelPremiacion) {
		this.indRedefinirNivelPremiacion = indRedefinirNivelPremiacion;
	}

	/**
	 * @return the iNC_PLANTILLA_CONSTANCIA_RECOMENDACION
	 */
	public static String getINC_PLANTILLA_CONSTANCIA_RECOMENDACION() {
		return INC_PLANTILLA_CONSTANCIA_RECOMENDACION;
	}

	/**
	 * @param iNC_PLANTILLA_CONSTANCIA_RECOMENDACION
	 *            the iNC_PLANTILLA_CONSTANCIA_RECOMENDACION to set
	 */
	public static void setINC_PLANTILLA_CONSTANCIA_RECOMENDACION(
			String iNC_PLANTILLA_CONSTANCIA_RECOMENDACION) {
		INC_PLANTILLA_CONSTANCIA_RECOMENDACION = iNC_PLANTILLA_CONSTANCIA_RECOMENDACION;
	}

	/**
	 * @return the iNC_TIPO_PROGRAMA_GENERAR_RECOMENDADAS
	 */
	public static Long getINC_TIPO_PROGRAMA_GENERAR_RECOMENDADAS() {
		return INC_TIPO_PROGRAMA_GENERAR_RECOMENDADAS;
	}

	/**
	 * @param iNC_TIPO_PROGRAMA_GENERAR_RECOMENDADAS
	 *            the iNC_TIPO_PROGRAMA_GENERAR_RECOMENDADAS to set
	 */
	public static void setINC_TIPO_PROGRAMA_GENERAR_RECOMENDADAS(
			Long iNC_TIPO_PROGRAMA_GENERAR_RECOMENDADAS) {
		INC_TIPO_PROGRAMA_GENERAR_RECOMENDADAS = iNC_TIPO_PROGRAMA_GENERAR_RECOMENDADAS;
	}

	/**
	 * @return the cantidadInicialPuntosDisabled
	 */
	public boolean isCantidadInicialPuntosDisabled() {
		return cantidadInicialPuntosDisabled;
	}

	/**
	 * @param cantidadInicialPuntosDisabled
	 *            the cantidadInicialPuntosDisabled to set
	 */
	public void setCantidadInicialPuntosDisabled(
			boolean cantidadInicialPuntosDisabled) {
		this.cantidadInicialPuntosDisabled = cantidadInicialPuntosDisabled;
	}

	/**
	 * @return the cantidadFinalPuntosDisabled
	 */
	public boolean isCantidadFinalPuntosDisabled() {
		return cantidadFinalPuntosDisabled;
	}

	/**
	 * @param cantidadFinalPuntosDisabled
	 *            the cantidadFinalPuntosDisabled to set
	 */
	public void setCantidadFinalPuntosDisabled(
			boolean cantidadFinalPuntosDisabled) {
		this.cantidadFinalPuntosDisabled = cantidadFinalPuntosDisabled;
	}

	/**
	 * @return the puntosProductosExigidosDisabled
	 */
	public boolean isPuntosProductosExigidosDisabled() {
		return puntosProductosExigidosDisabled;
	}

	/**
	 * @param puntosProductosExigidosDisabled
	 *            the puntosProductosExigidosDisabled to set
	 */
	public void setPuntosProductosExigidosDisabled(
			boolean puntosProductosExigidosDisabled) {
		this.puntosProductosExigidosDisabled = puntosProductosExigidosDisabled;
	}

	/**
	 * @return the cantidadFijaPuntosDisabled
	 */
	public boolean isCantidadFijaPuntosDisabled() {
		return cantidadFijaPuntosDisabled;
	}

	/**
	 * @param cantidadFijaPuntosDisabled
	 *            the cantidadFijaPuntosDisabled to set
	 */
	public void setCantidadFijaPuntosDisabled(boolean cantidadFijaPuntosDisabled) {
		this.cantidadFijaPuntosDisabled = cantidadFijaPuntosDisabled;
	}

	/**
	 * @return the plazoEntregaDisabled
	 */
	public boolean isPlazoEntregaDisabled() {
		return plazoEntregaDisabled;
	}

	/**
	 * @param plazoEntregaDisabled
	 *            the plazoEntregaDisabled to set
	 */
	public void setPlazoEntregaDisabled(boolean plazoEntregaDisabled) {
		this.plazoEntregaDisabled = plazoEntregaDisabled;
	}

	/**
	 * @return the consultar
	 */
	public boolean isConsultar() {
		return consultar;
	}

	/**
	 * @param consultar
	 *            the consultar to set
	 */
	public void setConsultar(boolean consultar) {
		this.consultar = consultar;
	}

	/**
	 * @return the codigoPeriodoDisabled
	 */
	public boolean isCodigoPeriodoDisabled() {
		return codigoPeriodoDisabled;
	}

	/**
	 * @param codigoPeriodoDisabled
	 *            the codigoPeriodoDisabled to set
	 */
	public void setCodigoPeriodoDisabled(boolean codigoPeriodoDisabled) {
		this.codigoPeriodoDisabled = codigoPeriodoDisabled;
	}

	/**
	 * @return the puntosUnidadDisabled
	 */
	public boolean isPuntosUnidadDisabled() {
		return puntosUnidadDisabled;
	}

	/**
	 * @param puntosUnidadDisabled
	 *            the puntosUnidadDisabled to set
	 */
	public void setPuntosUnidadDisabled(boolean puntosUnidadDisabled) {
		this.puntosUnidadDisabled = puntosUnidadDisabled;
	}

	/**
	 * @return the factorMultiplicadorDisabled
	 */
	public boolean isFactorMultiplicadorDisabled() {
		return factorMultiplicadorDisabled;
	}

	/**
	 * @param factorMultiplicadorDisabled
	 *            the factorMultiplicadorDisabled to set
	 */
	public void setFactorMultiplicadorDisabled(
			boolean factorMultiplicadorDisabled) {
		this.factorMultiplicadorDisabled = factorMultiplicadorDisabled;
	}

	/**
	 * @return the unidadesExigidasDisabled
	 */
	public boolean isUnidadesExigidasDisabled() {
		return unidadesExigidasDisabled;
	}

	/**
	 * @param unidadesExigidasDisabled
	 *            the unidadesExigidasDisabled to set
	 */
	public void setUnidadesExigidasDisabled(boolean unidadesExigidasDisabled) {
		this.unidadesExigidasDisabled = unidadesExigidasDisabled;
	}

	/**
	 * @return the montoExigidoDisabled
	 */
	public boolean isMontoExigidoDisabled() {
		return montoExigidoDisabled;
	}

	/**
	 * @param montoExigidoDisabled
	 *            the montoExigidoDisabled to set
	 */
	public void setMontoExigidoDisabled(boolean montoExigidoDisabled) {
		this.montoExigidoDisabled = montoExigidoDisabled;
	}

	/**
	 * @return the puntosExigidosDisabled
	 */
	public boolean isPuntosExigidosDisabled() {
		return puntosExigidosDisabled;
	}

	/**
	 * @param puntosExigidosDisabled
	 *            the puntosExigidosDisabled to set
	 */
	public void setPuntosExigidosDisabled(boolean puntosExigidosDisabled) {
		this.puntosExigidosDisabled = puntosExigidosDisabled;
	}

	/**
	 * @return the oidTipoAgrupacionRendered
	 */
	public boolean isOidTipoAgrupacionRendered() {
		return oidTipoAgrupacionRendered;
	}

	/**
	 * @param oidTipoAgrupacionRendered
	 *            the oidTipoAgrupacionRendered to set
	 */
	public void setOidTipoAgrupacionRendered(boolean oidTipoAgrupacionRendered) {
		this.oidTipoAgrupacionRendered = oidTipoAgrupacionRendered;
	}

	/**
	 * @return the oidUnidadNegocioRendered
	 */
	public boolean isOidUnidadNegocioRendered() {
		return oidUnidadNegocioRendered;
	}

	/**
	 * @param oidUnidadNegocioRendered
	 *            the oidUnidadNegocioRendered to set
	 */
	public void setOidUnidadNegocioRendered(boolean oidUnidadNegocioRendered) {
		this.oidUnidadNegocioRendered = oidUnidadNegocioRendered;
	}

	/**
	 * @return the oidNegocioRendered
	 */
	public boolean isOidNegocioRendered() {
		return oidNegocioRendered;
	}

	/**
	 * @param oidNegocioRendered
	 *            the oidNegocioRendered to set
	 */
	public void setOidNegocioRendered(boolean oidNegocioRendered) {
		this.oidNegocioRendered = oidNegocioRendered;
	}

	/**
	 * @return the oidTipoOfertaRendered
	 */
	public boolean isOidTipoOfertaRendered() {
		return oidTipoOfertaRendered;
	}

	/**
	 * @param oidTipoOfertaRendered
	 *            the oidTipoOfertaRendered to set
	 */
	public void setOidTipoOfertaRendered(boolean oidTipoOfertaRendered) {
		this.oidTipoOfertaRendered = oidTipoOfertaRendered;
	}

	/**
	 * @return the busquedaProductoSearchAction
	 */
	public BusquedaProductoSearchAction getBusquedaProductoSearchAction() {
		return busquedaProductoSearchAction;
	}

	/**
	 * @param busquedaProductoSearchAction
	 *            the busquedaProductoSearchAction to set
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
	 * @param mostrarPopupProducto
	 *            the mostrarPopupProducto to set
	 */
	public void setMostrarPopupProducto(boolean mostrarPopupProducto) {
		this.mostrarPopupProducto = mostrarPopupProducto;
	}

	/**
	 * @return the popupSacproducto
	 */
	public static String getPopupSacproducto() {
		return POPUP_SACPRODUCTO;
	}

	/**
	 * @return the codigoSAPRendered
	 */
	public boolean isCodigoSAPRendered() {
		return codigoSAPRendered;
	}

	/**
	 * @param codigoSAPRendered
	 *            the codigoSAPRendered to set
	 */
	public void setCodigoSAPRendered(boolean codigoSAPRendered) {
		this.codigoSAPRendered = codigoSAPRendered;
	}

	/**
	 * @return the oidMarcaProductoRendered
	 */
	public boolean isOidMarcaProductoRendered() {
		return oidMarcaProductoRendered;
	}

	/**
	 * @param oidMarcaProductoRendered
	 *            the oidMarcaProductoRendered to set
	 */
	public void setOidMarcaProductoRendered(boolean oidMarcaProductoRendered) {
		this.oidMarcaProductoRendered = oidMarcaProductoRendered;
	}

	/**
	 * @return the codigoPeriodoCUVRendered
	 */
	public boolean isCodigoPeriodoCUVRendered() {
		return codigoPeriodoCUVRendered;
	}

	/**
	 * @param codigoPeriodoCUVRendered
	 *            the codigoPeriodoCUVRendered to set
	 */
	public void setCodigoPeriodoCUVRendered(boolean codigoPeriodoCUVRendered) {
		this.codigoPeriodoCUVRendered = codigoPeriodoCUVRendered;
	}

	/**
	 * @return the cUVRendered
	 */
	public boolean isCUVRendered() {
		return CUVRendered;
	}

	/**
	 * @param cUVRendered
	 *            the cUVRendered to set
	 */
	public void setCUVRendered(boolean cUVRendered) {
		CUVRendered = cUVRendered;
	}

	/**
	 * @return the codigoPeriodoRendered
	 */
	public boolean isCodigoPeriodoRendered() {
		return codigoPeriodoRendered;
	}

	/**
	 * @param codigoPeriodoRendered
	 *            the codigoPeriodoRendered to set
	 */
	public void setCodigoPeriodoRendered(boolean codigoPeriodoRendered) {
		this.codigoPeriodoRendered = codigoPeriodoRendered;
	}

	/**
	 * @return the indicadorCopiarProductoBoolean
	 */
	public boolean isIndicadorCopiarProductoBoolean() {
		return indicadorCopiarProductoBoolean;
	}

	/**
	 * @param indicadorCopiarProductoBoolean
	 *            the indicadorCopiarProductoBoolean to set
	 */
	public void setIndicadorCopiarProductoBoolean(
			boolean indicadorCopiarProductoBoolean) {
		this.indicadorCopiarProductoBoolean = indicadorCopiarProductoBoolean;
	}

	/**
	 * @return the indicadorCopiarParticipantesBoolean
	 */
	public boolean isIndicadorCopiarParticipantesBoolean() {
		return indicadorCopiarParticipantesBoolean;
	}

	/**
	 * @param indicadorCopiarParticipantesBoolean
	 *            the indicadorCopiarParticipantesBoolean to set
	 */
	public void setIndicadorCopiarParticipantesBoolean(
			boolean indicadorCopiarParticipantesBoolean) {
		this.indicadorCopiarParticipantesBoolean = indicadorCopiarParticipantesBoolean;
	}

	/**
	 * @return the formBonificacion
	 */
	public MantenimientoINCBonificacionPeriodoForm getFormBonificacion() {
		return formBonificacion;
	}

	/**
	 * @param formBonificacion
	 *            the formBonificacion to set
	 */
	public void setFormBonificacion(
			MantenimientoINCBonificacionPeriodoForm formBonificacion) {
		this.formBonificacion = formBonificacion;
	}

	/**
	 * @return the beanRegistroBonificacion
	 */
	public Object[] getBeanRegistroBonificacion() {
		return beanRegistroBonificacion;
	}

	/**
	 * @param beanRegistroBonificacion
	 *            the beanRegistroBonificacion to set
	 */
	public void setBeanRegistroBonificacion(Object[] beanRegistroBonificacion) {
		this.beanRegistroBonificacion = beanRegistroBonificacion;
	}

	/**
	 * @return the formRecomendada
	 */
	public MantenimientoINCRecomendadaPeriodoForm getFormRecomendada() {
		return formRecomendada;
	}

	/**
	 * @param formRecomendada
	 *            the formRecomendada to set
	 */
	public void setFormRecomendada(
			MantenimientoINCRecomendadaPeriodoForm formRecomendada) {
		this.formRecomendada = formRecomendada;
	}

	/**
	 * @return the dataTableBonificacion
	 */
	public DataTableModel getDataTableBonificacion() {
		return dataTableBonificacion;
	}

	/**
	 * @param dataTableBonificacion
	 *            the dataTableBonificacion to set
	 */
	public void setDataTableBonificacion(DataTableModel dataTableBonificacion) {
		this.dataTableBonificacion = dataTableBonificacion;
	}

	/**
	 * @return the beanRegistroRecomendada
	 */
	public Object[] getBeanRegistroRecomendada() {
		return beanRegistroRecomendada;
	}

	/**
	 * @param beanRegistroRecomendada
	 *            the beanRegistroRecomendada to set
	 */
	public void setBeanRegistroRecomendada(Object[] beanRegistroRecomendada) {
		this.beanRegistroRecomendada = beanRegistroRecomendada;
	}

	/**
	 * @return the dataTableRecomendada
	 */
	public DataTableModel getDataTableRecomendada() {
		return dataTableRecomendada;
	}

	/**
	 * @param dataTableRecomendada
	 *            the dataTableRecomendada to set
	 */
	public void setDataTableRecomendada(DataTableModel dataTableRecomendada) {
		this.dataTableRecomendada = dataTableRecomendada;
	}

	/**
	 * @return the formEstatus
	 */
	public MantenimientoINCEstatusVentaForm getFormEstatus() {
		return formEstatus;
	}

	/**
	 * @param formEstatus
	 *            the formEstatus to set
	 */
	public void setFormEstatus(MantenimientoINCEstatusVentaForm formEstatus) {
		this.formEstatus = formEstatus;
	}

	/**
	 * @return the beanRegistroEstatus
	 */
	public Object[] getBeanRegistroEstatus() {
		return beanRegistroEstatus;
	}

	/**
	 * @param beanRegistroEstatus
	 *            the beanRegistroEstatus to set
	 */
	public void setBeanRegistroEstatus(Object[] beanRegistroEstatus) {
		this.beanRegistroEstatus = beanRegistroEstatus;
	}

	/**
	 * @return the dataTableEstatus
	 */
	public DataTableModel getDataTableEstatus() {
		return dataTableEstatus;
	}

	/**
	 * @param dataTableEstatus
	 *            the dataTableEstatus to set
	 */
	public void setDataTableEstatus(DataTableModel dataTableEstatus) {
		this.dataTableEstatus = dataTableEstatus;
	}

	/**
	 * @return the beanRegistroUnidad
	 */
	public Object[] getBeanRegistroUnidad() {
		return beanRegistroUnidad;
	}

	/**
	 * @param beanRegistroUnidad
	 *            the beanRegistroUnidad to set
	 */
	public void setBeanRegistroUnidad(Object[] beanRegistroUnidad) {
		this.beanRegistroUnidad = beanRegistroUnidad;
	}

	/**
	 * @return the dataTableUnidad
	 */
	public DataTableModel getDataTableUnidad() {
		return dataTableUnidad;
	}

	/**
	 * @param dataTableUnidad
	 *            the dataTableUnidad to set
	 */
	public void setDataTableUnidad(DataTableModel dataTableUnidad) {
		this.dataTableUnidad = dataTableUnidad;
	}

	/**
	 * @return the formUnidad
	 */
	public MantenimientoINCUnidadAdministrativaForm getFormUnidad() {
		return formUnidad;
	}

	/**
	 * @param formUnidad
	 *            the formUnidad to set
	 */
	public void setFormUnidad(
			MantenimientoINCUnidadAdministrativaForm formUnidad) {
		this.formUnidad = formUnidad;
	}

	/**
	 * @return the siccZonaList
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 *            the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the siccRegionList
	 */
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 *            the siccRegionList to set
	 */
	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the formClasificacion
	 */
	public MantenimientoINCClasificacionParticipanteForm getFormClasificacion() {
		return formClasificacion;
	}

	/**
	 * @param formClasificacion
	 *            the formClasificacion to set
	 */
	public void setFormClasificacion(
			MantenimientoINCClasificacionParticipanteForm formClasificacion) {
		this.formClasificacion = formClasificacion;
	}

	/**
	 * @return the beanRegistroClasificacion
	 */
	public Object[] getBeanRegistroClasificacion() {
		return beanRegistroClasificacion;
	}

	/**
	 * @param beanRegistroClasificacion
	 *            the beanRegistroClasificacion to set
	 */
	public void setBeanRegistroClasificacion(Object[] beanRegistroClasificacion) {
		this.beanRegistroClasificacion = beanRegistroClasificacion;
	}

	/**
	 * @return the dataTableClasificacion
	 */
	public DataTableModel getDataTableClasificacion() {
		return dataTableClasificacion;
	}

	/**
	 * @param dataTableClasificacion
	 *            the dataTableClasificacion to set
	 */
	public void setDataTableClasificacion(DataTableModel dataTableClasificacion) {
		this.dataTableClasificacion = dataTableClasificacion;
	}

	/**
	 * @return the excluirClasificacion
	 */
	public boolean isExcluirClasificacion() {
		return excluirClasificacion;
	}

	/**
	 * @param excluirClasificacion
	 *            the excluirClasificacion to set
	 */
	public void setExcluirClasificacion(boolean excluirClasificacion) {
		this.excluirClasificacion = excluirClasificacion;
	}

	/**
	 * @return the beanRegistroProductosBonificacion
	 */
	public Object[] getBeanRegistroProductosBonificacion() {
		return beanRegistroProductosBonificacion;
	}

	/**
	 * @param beanRegistroProductosBonificacion
	 *            the beanRegistroProductosBonificacion to set
	 */
	public void setBeanRegistroProductosBonificacion(
			Object[] beanRegistroProductosBonificacion) {
		this.beanRegistroProductosBonificacion = beanRegistroProductosBonificacion;
	}

	/**
	 * @return the dataTableProductosBonificacion
	 */
	public DataTableModel getDataTableProductosBonificacion() {
		return dataTableProductosBonificacion;
	}

	/**
	 * @param dataTableProductosBonificacion
	 *            the dataTableProductosBonificacion to set
	 */
	public void setDataTableProductosBonificacion(
			DataTableModel dataTableProductosBonificacion) {
		this.dataTableProductosBonificacion = dataTableProductosBonificacion;
	}

	/**
	 * @return the beanRegistroProductosValidos
	 */
	public Object[] getBeanRegistroProductosValidos() {
		return beanRegistroProductosValidos;
	}

	/**
	 * @param beanRegistroProductosValidos
	 *            the beanRegistroProductosValidos to set
	 */
	public void setBeanRegistroProductosValidos(
			Object[] beanRegistroProductosValidos) {
		this.beanRegistroProductosValidos = beanRegistroProductosValidos;
	}

	/**
	 * @return the beanRegistroProductosExcluidos
	 */
	public Object[] getBeanRegistroProductosExcluidos() {
		return beanRegistroProductosExcluidos;
	}

	/**
	 * @param beanRegistroProductosExcluidos
	 *            the beanRegistroProductosExcluidos to set
	 */
	public void setBeanRegistroProductosExcluidos(
			Object[] beanRegistroProductosExcluidos) {
		this.beanRegistroProductosExcluidos = beanRegistroProductosExcluidos;
	}

	/**
	 * @return the beanRegistroProductosExigidos
	 */
	public Object[] getBeanRegistroProductosExigidos() {
		return beanRegistroProductosExigidos;
	}

	/**
	 * @param beanRegistroProductosExigidos
	 *            the beanRegistroProductosExigidos to set
	 */
	public void setBeanRegistroProductosExigidos(
			Object[] beanRegistroProductosExigidos) {
		this.beanRegistroProductosExigidos = beanRegistroProductosExigidos;
	}

	/**
	 * @return the dataTableProductosValidos
	 */
	public DataTableModel getDataTableProductosValidos() {
		return dataTableProductosValidos;
	}

	/**
	 * @param dataTableProductosValidos
	 *            the dataTableProductosValidos to set
	 */
	public void setDataTableProductosValidos(
			DataTableModel dataTableProductosValidos) {
		this.dataTableProductosValidos = dataTableProductosValidos;
	}

	/**
	 * @return the dataTableProductosExcluidos
	 */
	public DataTableModel getDataTableProductosExcluidos() {
		return dataTableProductosExcluidos;
	}

	/**
	 * @param dataTableProductosExcluidos
	 *            the dataTableProductosExcluidos to set
	 */
	public void setDataTableProductosExcluidos(
			DataTableModel dataTableProductosExcluidos) {
		this.dataTableProductosExcluidos = dataTableProductosExcluidos;
	}

	/**
	 * @return the dataTableProductosExigidos
	 */
	public DataTableModel getDataTableProductosExigidos() {
		return dataTableProductosExigidos;
	}

	/**
	 * @param dataTableProductosExigidos
	 *            the dataTableProductosExigidos to set
	 */
	public void setDataTableProductosExigidos(
			DataTableModel dataTableProductosExigidos) {
		this.dataTableProductosExigidos = dataTableProductosExigidos;
	}

	/**
	 * @return the formPeriodoDespacho
	 */
	public MantenimientoINCPeriodoDespachoForm getFormPeriodoDespacho() {
		return formPeriodoDespacho;
	}

	/**
	 * @param formPeriodoDespacho
	 *            the formPeriodoDespacho to set
	 */
	public void setFormPeriodoDespacho(
			MantenimientoINCPeriodoDespachoForm formPeriodoDespacho) {
		this.formPeriodoDespacho = formPeriodoDespacho;
	}

	/**
	 * @return the beanRegistroPeriodoDespacho
	 */
	public Object[] getBeanRegistroPeriodoDespacho() {
		return beanRegistroPeriodoDespacho;
	}

	/**
	 * @param beanRegistroPeriodoDespacho
	 *            the beanRegistroPeriodoDespacho to set
	 */
	public void setBeanRegistroPeriodoDespacho(
			Object[] beanRegistroPeriodoDespacho) {
		this.beanRegistroPeriodoDespacho = beanRegistroPeriodoDespacho;
	}

	/**
	 * @return the dataTablePeriodoDespacho
	 */
	public DataTableModel getDataTablePeriodoDespacho() {
		return dataTablePeriodoDespacho;
	}

	/**
	 * @param dataTablePeriodoDespacho
	 *            the dataTablePeriodoDespacho to set
	 */
	public void setDataTablePeriodoDespacho(
			DataTableModel dataTablePeriodoDespacho) {
		this.dataTablePeriodoDespacho = dataTablePeriodoDespacho;
	}

	/**
	 * @return the formPuntajeExigido
	 */
	public MantenimientoINCPuntajeExigidoForm getFormPuntajeExigido() {
		return formPuntajeExigido;
	}

	/**
	 * @param formPuntajeExigido
	 *            the formPuntajeExigido to set
	 */
	public void setFormPuntajeExigido(
			MantenimientoINCPuntajeExigidoForm formPuntajeExigido) {
		this.formPuntajeExigido = formPuntajeExigido;
	}

	/**
	 * @return the beanRegistroPremiacion
	 */
	public Object getBeanRegistroPremiacion() {
		return beanRegistroPremiacion;
	}

	/**
	 * @param beanRegistroPremiacion
	 *            the beanRegistroPremiacion to set
	 */
	public void setBeanRegistroPremiacion(Object beanRegistroPremiacion) {
		this.beanRegistroPremiacion = beanRegistroPremiacion;
	}

	/**
	 * @return the beanRegistroPuntajeExigido
	 */
	public Object[] getBeanRegistroPuntajeExigido() {
		return beanRegistroPuntajeExigido;
	}

	/**
	 * @param beanRegistroPuntajeExigido
	 *            the beanRegistroPuntajeExigido to set
	 */
	public void setBeanRegistroPuntajeExigido(
			Object[] beanRegistroPuntajeExigido) {
		this.beanRegistroPuntajeExigido = beanRegistroPuntajeExigido;
	}

	/**
	 * @return the dataTablePuntajeExigido
	 */
	public DataTableModel getDataTablePuntajeExigido() {
		return dataTablePuntajeExigido;
	}

	/**
	 * @param dataTablePuntajeExigido
	 *            the dataTablePuntajeExigido to set
	 */
	public void setDataTablePuntajeExigido(
			DataTableModel dataTablePuntajeExigido) {
		this.dataTablePuntajeExigido = dataTablePuntajeExigido;
	}

	/**
	 * @return the formDefinirPremio
	 */
	public MantenimientoINCDefinirPremioForm getFormDefinirPremio() {
		return formDefinirPremio;
	}

	/**
	 * @param formDefinirPremio
	 *            the formDefinirPremio to set
	 */
	public void setFormDefinirPremio(
			MantenimientoINCDefinirPremioForm formDefinirPremio) {
		this.formDefinirPremio = formDefinirPremio;
	}

	/**
	 * @return the beanRegistroDefinirPremio
	 */
	public Object getBeanRegistroDefinirPremio() {
		return beanRegistroDefinirPremio;
	}

	/**
	 * @param beanRegistroDefinirPremio
	 *            the beanRegistroDefinirPremio to set
	 */
	public void setBeanRegistroDefinirPremio(Object beanRegistroDefinirPremio) {
		this.beanRegistroDefinirPremio = beanRegistroDefinirPremio;
	}

	/**
	 * @return the dataTableDefinirPremio
	 */
	public DataTableModel getDataTableDefinirPremio() {
		return dataTableDefinirPremio;
	}

	/**
	 * @param dataTableDefinirPremio
	 *            the dataTableDefinirPremio to set
	 */
	public void setDataTableDefinirPremio(DataTableModel dataTableDefinirPremio) {
		this.dataTableDefinirPremio = dataTableDefinirPremio;
	}

	/**
	 * @return the incTipoLoteList
	 */
	public List getIncTipoLoteList() {
		return incTipoLoteList;
	}

	/**
	 * @param incTipoLoteList
	 *            the incTipoLoteList to set
	 */
	public void setIncTipoLoteList(List incTipoLoteList) {
		this.incTipoLoteList = incTipoLoteList;
	}

	/**
	 * @return the indicadorCentroServicioBoolean
	 */
	public boolean isIndicadorCentroServicioBoolean() {
		return indicadorCentroServicioBoolean;
	}

	/**
	 * @param indicadorCentroServicioBoolean
	 *            the indicadorCentroServicioBoolean to set
	 */
	public void setIndicadorCentroServicioBoolean(
			boolean indicadorCentroServicioBoolean) {
		this.indicadorCentroServicioBoolean = indicadorCentroServicioBoolean;
	}

	/**
	 * @return the popupSacproductopremio
	 */
	public static String getPopupSacproductopremio() {
		return POPUP_SACPRODUCTOPREMIO;
	}

	/**
	 * @return the accionDinamico
	 */
	public String getAccionDinamico() {
		return accionDinamico;
	}

	/**
	 * @param accionDinamico
	 *            the accionDinamico to set
	 */
	public void setAccionDinamico(String accionDinamico) {
		this.accionDinamico = accionDinamico;
	}

	/**
	 * @return the reporteConcurso
	 */
	public ReporteINCConfiguracionConcursoAction getReporteConcurso() {
		return reporteConcurso;
	}

	/**
	 * @param reporteConcurso
	 *            the reporteConcurso to set
	 */
	public void setReporteConcurso(
			ReporteINCConfiguracionConcursoAction reporteConcurso) {
		this.reporteConcurso = reporteConcurso;
	}

	/**
	 * @return the mensajeGuardar
	 */
	public String getMensajeGuardar() {
		return mensajeGuardar;
	}

	/**
	 * @param mensajeGuardar
	 *            the mensajeGuardar to set
	 */
	public void setMensajeGuardar(String mensajeGuardar) {
		this.mensajeGuardar = mensajeGuardar;
	}

	/**
	 * @return the tabIndex
	 */
	public String getTabIndex() {
		return tabIndex;
	}

	/**
	 * @param tabIndex
	 *            the tabIndex to set
	 */
	public void setTabIndex(String tabIndex) {
		this.tabIndex = tabIndex;
	}

	public MantenimientoINCDefinirPremioDescuentoForm getDefinirPremioDescuentoForm() {
		return definirPremioDescuentoForm;
	}

	public void setDefinirPremioDescuentoForm(
			MantenimientoINCDefinirPremioDescuentoForm definirPremioDescuentoForm) {
		this.definirPremioDescuentoForm = definirPremioDescuentoForm;
	}

	public List getListaPremioDescuento() {
		return listaPremioDescuento;
	}

	public void setListaPremioDescuento(List listaPremioDescuento) {
		this.listaPremioDescuento = listaPremioDescuento;
	}

	public DataTableModel getPremioDescuentoDataModel() {
		return premioDescuentoDataModel;
	}

	public void setPremioDescuentoDataModel(
			DataTableModel premioDescuentoDataModel) {
		this.premioDescuentoDataModel = premioDescuentoDataModel;
	}

	public ConcursoArticuloLoteDescuento getBeanRegistroDefinirPremioDescuento() {
		return beanRegistroDefinirPremioDescuento;
	}

	public void setBeanRegistroDefinirPremioDescuento(
			ConcursoArticuloLoteDescuento beanRegistroDefinirPremioDescuento) {
		this.beanRegistroDefinirPremioDescuento = beanRegistroDefinirPremioDescuento;
	}

	public Boolean getMostrarBotones() {
		return mostrarBotones;
	}

	public void setMostrarBotones(Boolean mostrarBotones) {
		this.mostrarBotones = mostrarBotones;
	}

	public ConcursoArticuloLoteDescuento getIdPremioDescuento() {
		return idPremioDescuento;
	}

	public void setIdPremioDescuento(
			ConcursoArticuloLoteDescuento idPremioDescuento) {
		this.idPremioDescuento = idPremioDescuento;
	}

	public ConcursoArticuloLote getIdPremio() {
		return idPremio;
	}

	public void setIdPremio(ConcursoArticuloLote idPremio) {
		this.idPremio = idPremio;
	}

	/**
	 * @return the oidTipoAgrupacionBloqueRendered
	 */
	public String getOidTipoAgrupacionBloqueRendered() {
		return oidTipoAgrupacionBloqueRendered;
	}

	/**
	 * @param oidTipoAgrupacionBloqueRendered
	 *            the oidTipoAgrupacionBloqueRendered to set
	 */
	public void setOidTipoAgrupacionBloqueRendered(
			String oidTipoAgrupacionBloqueRendered) {
		this.oidTipoAgrupacionBloqueRendered = oidTipoAgrupacionBloqueRendered;
	}

	/**
	 * @return the indicadorGrabarParametrosGenerales
	 */
	public Boolean getIndicadorGrabarParametrosGenerales() {
		return indicadorGrabarParametrosGenerales;
	}

	/**
	 * @param indicadorGrabarParametrosGenerales
	 *            the indicadorGrabarParametrosGenerales to set
	 */
	public void setIndicadorGrabarParametrosGenerales(
			Boolean indicadorGrabarParametrosGenerales) {
		this.indicadorGrabarParametrosGenerales = indicadorGrabarParametrosGenerales;
	}

	/**
	 * @return the descripcionConcurso
	 */
	public String getDescripcionConcurso() {
		return descripcionConcurso;
	}

	/**
	 * @param descripcionConcurso
	 *            the descripcionConcurso to set
	 */
	public void setDescripcionConcurso(String descripcionConcurso) {
		this.descripcionConcurso = descripcionConcurso;
	}

	/**
	 * @return the deshabilitarNumeroMinimoPedidos
	 */
	public Boolean getDeshabilitarNumeroMinimoPedidos() {
		return deshabilitarNumeroMinimoPedidos;
	}

	/**
	 * @param deshabilitarNumeroMinimoPedidos
	 *            the deshabilitarNumeroMinimoPedidos to set
	 */
	public void setDeshabilitarNumeroMinimoPedidos(
			Boolean deshabilitarNumeroMinimoPedidos) {
		this.deshabilitarNumeroMinimoPedidos = deshabilitarNumeroMinimoPedidos;
	}

	/**
	 * @return the indicadorPriorizaWebBoolean
	 */
	public boolean isIndicadorPriorizaWebBoolean() {
		return indicadorPriorizaWebBoolean;
	}

	/**
	 * @param indicadorPriorizaWebBoolean the indicadorPriorizaWebBoolean to set
	 */
	public void setIndicadorPriorizaWebBoolean(boolean indicadorPriorizaWebBoolean) {
		this.indicadorPriorizaWebBoolean = indicadorPriorizaWebBoolean;
	}

	public boolean isIndicadorPremiosWebBoolean() {
		return indicadorPremiosWebBoolean;
	}

	public void setIndicadorPremiosWebBoolean(boolean indicadorPremiosWebBoolean) {
		this.indicadorPremiosWebBoolean = indicadorPremiosWebBoolean;
	}
	
	

}