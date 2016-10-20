package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.data.PageEvent;
import org.springframework.dao.DataAccessException;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.spusicc.sto.model.AccionTipoDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.ConsultaPedidos;
import biz.belcorp.ssicc.dao.spusicc.sto.model.ConsultaValidaciones;
import biz.belcorp.ssicc.dao.spusicc.sto.model.DocumentoDigitadoPK;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOLevantamientoErroresValidacionService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.ProcesoSTOExecutionService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.beans.DocumentoSTOResult;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.sto.form.ProcesoSTOEliminarCDRForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProcesoSTOEliminarCDRAction extends BaseProcesoAbstractAction {

	private static final long serialVersionUID = 1455430738257858774L;
	private String[] regionListSearch;
	private String[] zonaListSearch;
	private LabelValue[] siccZonaList;
	private List stoListaPedidos;
	private List stoEliminarPedidosClientesList;
	private List siccRegionList;
	private LabelValue[] stoEstadosGestionList;
	private List stoHorasProcesoList;
	private List stoHorasCargaList;
	private Integer longitudCampoClientes;
	private Boolean mostrarGrilla;
	private String attachment = "";
	private Integer sumaCorrectasT;
	private Integer sumaGP2T;
	private Integer sumaGP3T;
	private Integer sumaRechazadas;
	private Integer sumaSubtotalCorrectasT;
	private Integer sumaSubtotalGP2T;
	private Integer sumaSubtotalGP3T;
	private Integer sumaSubtotalRechazadasT;
	private Boolean mostrarDatatable;
	private Integer pagina;
	private Object[] beanProcesoSTOEliminarCDR;
	private List stoResumenClientesList;
	private List stoListaValidaciones;
	private String stoLongitudNumeroDocumento;
	private List stoListaReclamosEliminados;
	private Integer sumaSubtotalCargasT;
	private Integer sumaSubtotalEnviadasT;
	private Integer sumaSubtotalErradasT;
	private Integer sumaSubtotalFacturadasT;
	private Integer sumaSubtotalDetallesOkT;
	private Integer sumaCargasT;
	private Integer sumaEnviadasT;
	private Integer sumaErradasT;
	private Integer sumaFacturadasT;
	private Integer sumaDetallesOkT;
	private Integer sumaRechazadasT;
	private Boolean mostrarBotonEliminar;
	private List listaSeleccionados;
	private ArrayList stoList;
	private Boolean mostrarSegundoDatatable;

	/**
	 * Guarda los filtros seleccionados
	 */
	private void setFilterSearch() {

		ProcesoSTOEliminarCDRForm f = (ProcesoSTOEliminarCDRForm) this.formProceso;

		f.setTipoDocumentoSearch(f.getTipoDocumento());
		f.setSelectedItemsSearch(f.getSelectedItems());
		f.setFechaInicioSearch(f.getFechaInicio());
		f.setFechaFinSearch(f.getFechaFin());
		f.setIndicadorDocumentoSearch(f.getIndicadorDocumento());
		f.setNumeroLoteSearch(f.getNumeroLote());
		f.setCodigoClienteSearch(f.getCodigoCliente());
		f.setAgrupacionSearch(f.getAgrupacion());
		f.setFechaInicioProcesoSearch(f.getFechaInicioProceso());
		f.setFechaFinProcesoSearch(f.getFechaFinProceso());
		f.setHoraInicioCargaSearch(f.getHoraInicioCarga());
		f.setHoraFinCargaSearch(f.getHoraFinCarga());
		f.setHoraInicioProcesoSearch(f.getHoraInicioProceso());
		f.setHoraFinProcesoSearch(f.getHoraFinProceso());
		f.setCodigoPeriodoSearch(f.getCodigoPeriodo());
		f.setEstadoSearch(f.getEstado());
		f.setAccionSearch(f.getAccion());
		f.setCodigoOrigenSearch(f.getCodigoOrigen());
		f.setNumeroDocumentoSearch(f.getNumeroDocumento());
		this.regionListSearch = f.getRegionList();
		this.zonaListSearch = f.getZonaList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction
	 * #setValidarFind()
	 */
	@Override
	public String setValidarFind() {
		ProcesoSTOEliminarCDRForm f = (ProcesoSTOEliminarCDRForm) this.formProceso;
		if (f.getFechaInicioD() == null || f.getFechaFinD() == null) {
			return null;
		} else {
			if (!validarFechas(f.getFechaInicioD(), f.getFechaFinD())) {
				return this.getResourceMessage("errors.compare.dates");
			}
		}

		if (f.getFechaInicioProcesoD() == null
				|| f.getFechaFinProceso() == null) {
			return null;
		} else {
			if (!validarFechas(f.getFechaInicioProcesoD(),
					f.getFechaFinProcesoD())) {
				return this.getResourceMessage("errors.compare.dates");
			}
		}
		return null;
	}

	/**
	 * Validando fechas
	 * 
	 * @param fechaInicioDate
	 * @param fechaFinDate
	 * @return
	 */
	public boolean validarFechas(Date fechaInicioDate, Date fechaFinDate) {

		if (fechaInicioDate.compareTo(fechaFinDate) > 0) {
			return false;
		}
		return true;
	}

	/**
	 * Evento de capturar la pagina del datatable
	 * 
	 * @param e
	 */
	public void onPage(PageEvent e) {
		int paginas = e.getPage();
		this.pagina = paginas;
		calcularSubtotales();
	}

	/**
	 * Cacular los subtotales de la lista por paginacion.
	 */
	public void calcularSubtotales() {

		int valorPagina = this.pagina + 1;
		int filasMuestra = 10;
		int valorFinal = 0;
		valorFinal = valorPagina * filasMuestra;
		int valorInicial = 0;
		valorInicial = valorFinal - filasMuestra;
		int sumaSubtotalCargas = 0;
		int sumaSubtotalEnviadas = 0;
		int sumaSubtotalFacturadas = 0;
		int sumaSubtotalErradas = 0;
		int sumaSubtotalRechazadas = 0;
		int sumaSubtotalDetallesOk = 0;
		// capturando la ultima pagina
		int ultimapagina = 0;
		boolean ultimaP = false;
		int ultimo = (this.stoListaValidaciones.size() / 10) + 1;
		int residuoUltimo = (this.stoListaValidaciones.size() % 10) + valorInicial;
		if (valorPagina == ultimo) {
			ultimaP = true;
		}
		if (residuoUltimo == 0) {

		} else {
			ultimapagina = residuoUltimo;
		}

		if (this.stoListaValidaciones.size() <= 9) {

			for (int i = 0; i < this.stoListaValidaciones.size(); i++) {

				ConsultaValidaciones obj = (ConsultaValidaciones) this.stoListaValidaciones
						.get(i);
				sumaSubtotalCargas = sumaSubtotalCargas + obj.getTotalCarga();
				sumaSubtotalEnviadas = sumaSubtotalEnviadas
						+ obj.getCantidadGP1();
				sumaSubtotalFacturadas = sumaSubtotalFacturadas
						+ obj.getCantidadFacturadas();
				sumaSubtotalErradas = sumaSubtotalErradas + obj.getErradas();
				sumaSubtotalRechazadas = sumaSubtotalRechazadas
						+ obj.getRechazadas();
				sumaSubtotalDetallesOk = sumaSubtotalDetallesOk
						+ obj.getDetallesOK();
			}
		}

		// validando que sea la ultima pagina, y que tenga residuo
		else if (ultimaP && residuoUltimo != 0) {
			for (int i = valorInicial; i <= residuoUltimo - 1; i++) {
				ConsultaValidaciones obj = (ConsultaValidaciones) this.stoListaValidaciones
						.get(i);
				sumaSubtotalCargas = sumaSubtotalCargas + obj.getTotalCarga();
				sumaSubtotalEnviadas = sumaSubtotalEnviadas
						+ obj.getCantidadGP1();
				sumaSubtotalFacturadas = sumaSubtotalFacturadas
						+ obj.getCantidadFacturadas();
				sumaSubtotalErradas = sumaSubtotalErradas + obj.getErradas();
				sumaSubtotalRechazadas = sumaSubtotalRechazadas
						+ obj.getRechazadas();
				sumaSubtotalDetallesOk = sumaSubtotalDetallesOk
						+ obj.getDetallesOK();
			}
		}

		// si es igual al pagino, calcular subtotal
		else {

			for (int i = valorInicial; i <= valorFinal - 1; i++) {

				ConsultaValidaciones obj = (ConsultaValidaciones) this.stoListaValidaciones
						.get(i);
				sumaSubtotalCargas = sumaSubtotalCargas + obj.getTotalCarga();
				sumaSubtotalEnviadas = sumaSubtotalEnviadas
						+ obj.getCantidadGP1();
				sumaSubtotalFacturadas = sumaSubtotalFacturadas
						+ obj.getCantidadFacturadas();
				sumaSubtotalErradas = sumaSubtotalErradas + obj.getErradas();
				sumaSubtotalRechazadas = sumaSubtotalRechazadas
						+ obj.getRechazadas();
				sumaSubtotalDetallesOk = sumaSubtotalDetallesOk
						+ obj.getDetallesOK();
			}

		}

		this.sumaSubtotalCargasT = sumaSubtotalCargas;
		this.sumaSubtotalEnviadasT = sumaSubtotalEnviadas;
		this.sumaSubtotalErradasT = sumaSubtotalErradas;
		this.sumaSubtotalFacturadasT = sumaSubtotalFacturadas;
		this.sumaSubtotalDetallesOkT = sumaSubtotalDetallesOk;
		this.sumaSubtotalRechazadasT = sumaSubtotalRechazadas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction
	 * #setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		log.debug("setFindAttributes.");
		this.inicializando();
		this.mostrarDatatable = true;
		Map params = new HashMap();
		ProcesoSTOEliminarCDRForm f = (ProcesoSTOEliminarCDRForm) this.formProceso;
		String fechaInicial = DateUtil.convertDateToString(f.getFechaInicioD());
		String fechaFin = DateUtil.convertDateToString(f.getFechaFinD());
		String fechaFinProceso = DateUtil.convertDateToString(f
				.getFechaFinProcesoD());
		String fechaInicioProceso = DateUtil.convertDateToString(f
				.getFechaInicioProcesoD());
		f.setFechaFin(fechaFin);
		f.setFechaInicio(fechaInicial);
		f.setFechaFinProceso(fechaFinProceso);
		f.setFechaInicioProceso(fechaInicioProceso);
		ProcesoSTOLevantamientoErroresValidacionService service = (ProcesoSTOLevantamientoErroresValidacionService) getBean("spusicc.procesoSTOLevantamientoErroresValidacionService");
		params = getCriteria();
		this.stoListaValidaciones = new ArrayList();
		List consultaValidacionList = service
				.getConsultaCDRTemporalList(params);
		this.stoListaValidaciones = consultaValidacionList;
		int tamanio = this.stoListaValidaciones.size();
		if (tamanio > 0) {
			this.mostrarBotonEliminar = true;
			this.mostrarDatatable = true;
			this.mostrarPanelAdicionalProceso = false;
			this.mostrarBotonExecute = false;
			this.mostrarBotonBuscar = true;
			int sumaCargas = 0;
			int sumaEnviadas = 0;
			int sumaErradas = 0;
			int sumaFacturadas = 0;
			int sumaDetallesOk = 0;
			int sumaRechazadas = 0;

			for (int i = 0; i < consultaValidacionList.size(); i++) {
				ConsultaValidaciones obj = (ConsultaValidaciones) this.stoListaValidaciones
						.get(i);
				sumaCargas = sumaCargas + obj.getTotalCarga();
				sumaEnviadas = sumaEnviadas + obj.getCantidadGP1();
				sumaFacturadas = sumaFacturadas + obj.getCantidadFacturadas();
				sumaErradas = sumaErradas + obj.getErradas();
				sumaRechazadas = sumaRechazadas + obj.getRechazadas();
				sumaDetallesOk = sumaDetallesOk + obj.getDetallesOK();
			}

			int valorPagina = 1;
			int filasMuestra = 10;
			int valorFinal = 0;
			valorFinal = valorPagina * filasMuestra;
			int valorInicial = 0;
			int sumaSubtotalCargas = 0;
			int sumaSubtotalEnviadas = 0;
			int sumaSubtotalFacturadas = 0;
			int sumaSubtotalErradas = 0;
			int sumaSubtotalRechazadas = 0;
			int sumaSubtotalDetallesOk = 0;

			// subtotales cuando es menor a la paginacion

			if (tamanio < valorFinal) {
				for (int i = 0; i < consultaValidacionList.size(); i++) {
					ConsultaValidaciones obj = (ConsultaValidaciones) this.stoListaValidaciones
							.get(i);
					sumaSubtotalCargas = sumaSubtotalCargas
							+ obj.getTotalCarga();
					sumaSubtotalEnviadas = sumaSubtotalEnviadas
							+ obj.getCantidadGP1();
					sumaSubtotalFacturadas = sumaSubtotalFacturadas
							+ obj.getCantidadFacturadas();
					sumaSubtotalErradas = sumaSubtotalErradas
							+ obj.getErradas();
					sumaSubtotalRechazadas = sumaSubtotalRechazadas
							+ obj.getRechazadas();
					sumaSubtotalDetallesOk = sumaSubtotalDetallesOk
							+ obj.getDetallesOK();
				}
			}

			// subtotales cuando es mayor a la paginacion
			if (tamanio > valorFinal) {

				for (int i = valorInicial; i <= valorFinal - 1; i++) {
					ConsultaValidaciones obj = (ConsultaValidaciones) this.stoListaValidaciones
							.get(i);
					sumaSubtotalCargas = sumaSubtotalCargas
							+ obj.getTotalCarga();
					sumaSubtotalEnviadas = sumaSubtotalEnviadas
							+ obj.getCantidadGP1();
					sumaSubtotalFacturadas = sumaSubtotalFacturadas
							+ obj.getCantidadFacturadas();
					sumaSubtotalErradas = sumaSubtotalErradas
							+ obj.getErradas();
					sumaSubtotalRechazadas = sumaSubtotalRechazadas
							+ obj.getRechazadas();
					sumaSubtotalDetallesOk = sumaSubtotalDetallesOk
							+ obj.getDetallesOK();
				}
			}

			this.sumaSubtotalCargasT = sumaSubtotalCargas;
			this.sumaSubtotalEnviadasT = sumaSubtotalEnviadas;
			this.sumaSubtotalErradasT = sumaSubtotalErradas;
			this.sumaSubtotalFacturadasT = sumaSubtotalFacturadas;
			this.sumaSubtotalDetallesOkT = sumaSubtotalDetallesOk;
			this.sumaSubtotalRechazadasT = sumaSubtotalRechazadas;

			this.sumaCargasT = sumaCargas;
			this.sumaEnviadasT = sumaEnviadas;
			this.sumaErradasT = sumaErradas;
			this.sumaFacturadasT = sumaFacturadas;
			this.sumaDetallesOkT = sumaDetallesOk;
			this.sumaRechazadasT = sumaRechazadas;

			setFilterSearch();
			f.setCargaCombo(Constants.NO);
			syncZona();
			String ventana = "PF('dataTableWidgetVar').clearFilters()";
			this.getRequestContext().execute(ventana);
		}else{
			this.mostrarDatatable = false;
		}
		return consultaValidacionList;
	}

	/**
	 * @param request
	 * @param cabecera
	 */
	private void syncZona() {
		try {
			ProcesoSTOEliminarCDRForm cabecera = (ProcesoSTOEliminarCDRForm) this.formProceso;
			String[] codigoRegion = cabecera.getRegionList();
			if (cabecera.getRegionList() != null) {
				LabelValue[] resultZona = this.getZonaByRegion(
						cabecera.getCodigoPais(), codigoRegion);
				this.siccZonaList = resultZona;
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * @param codigoPais
	 * @param codigoEmpresa
	 * @param codigoRegion
	 * @return
	 */
	public LabelValue[] getZonaByRegion(final String codigoPais,
			final String[] codigoRegion) {

		LabelValue[] result = null;
		String condicionTodos = Constants.NO;
		InterfazSiCCService interfazSiCC = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		if (StringUtils.isNotBlank(codigoPais)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
			criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
			criteria.put("codigoRegion", codigoRegion);
			try {
				result = new LabelValue[1];
				result[0] = new LabelValue("Todos", "");

				List zonasList = interfazSiCC.getLista(
						"getZonasMultipleByPaisMarcaCanalRegion", criteria);
				if (zonasList != null && zonasList.size() > 0) {

					if (StringUtils.equals("T", condicionTodos)) {
						result = new LabelValue[zonasList.size() + 1];
						result[0] = new LabelValue("Todos", "");
						for (int i = 0; i < zonasList.size(); i++) {
							Base periodo = (Base) zonasList.get(i);
							// Construimos la descripcion
							LabelValue lv = new LabelValue(
									periodo.getDescripcion(),
									periodo.getCodigo());
							result[i + 1] = lv;
						}
					} else {
						result = new LabelValue[zonasList.size()];
						for (int i = 0; i < zonasList.size(); i++) {
							Base concurso = (Base) zonasList.get(i);
							LabelValue lv = new LabelValue(
									concurso.getDescripcion(),
									concurso.getCodigo());
							result[i] = lv;
						}
					}
				}
			} catch (DataAccessException ignore) {
				this.addError("Error : ",
						this.obtieneMensajeErrorException(ignore));
			}
		}
		return result;
	}

	/**
	 * @param f
	 * @param request
	 * @return Devuelve el filtro con los criterios de busqueda
	 */
	protected Map getCriteria() {
		ProcesoSTOEliminarCDRForm f = (ProcesoSTOEliminarCDRForm) this.formProceso;
		Map params = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String numLote = f.getNumeroLote();

		if (StringUtils.isBlank(f.getHoraInicioCarga())) {
			f.setHoraInicioCarga("00:00");
		}
		if (StringUtils.isBlank(f.getHoraFinCarga())) {
			f.setHoraFinCarga("00:00");
		}

		String indDocu = new String();
		indDocu = "1";
		// f.setFechaInicio(DateUtil.convertDateToString(f.getFechaInicioD()));
		// f.setFechaFin(DateUtil.convertDateToString(f.getFechaFinD()));
		// f.setFechaInicioProceso(DateUtil.convertDateToString(f
		// .getFechaInicioD()));
		// f.setFechaFinProceso(DateUtil.convertDateToString(f
		// .getFechaFinProcesoD()));

		String fechaInicio = f.getFechaInicio().trim();
		if (!StringUtils.isEmpty(fechaInicio))
			fechaInicio += " " + f.getHoraInicioCarga();

		String fechaFin = f.getFechaFin();
		if (!StringUtils.isEmpty(fechaFin))
			fechaFin += " " + f.getHoraFinCarga();

		String fechaInicioProceso = f.getFechaInicioProceso();
		if (!StringUtils.isEmpty(fechaInicioProceso))
			fechaInicioProceso += " " + f.getHoraInicioProceso();

		String fechaFinProceso = f.getFechaFinProceso();
		if (!StringUtils.isEmpty(fechaFinProceso))
			fechaFinProceso += " " + f.getHoraFinProceso();

		String codigoCliente = f.getCodigoCliente();
		String codigoPeriodo = f.getCodigoPeriodo();

		if (f.getRegionList() != null) {
			if (f.getRegionList().length == 1) {
				if (f.getRegionList()[0] == null
						|| f.getRegionList()[0].compareToIgnoreCase("") == 0) {
					f.setRegionList(null);
				}
			}
		}
		if (f.getZonaList() != null) {
			if (f.getZonaList().length == 1) {
				if (f.getZonaList()[0] == null
						|| f.getZonaList()[0].compareToIgnoreCase("") == 0) {
					f.setZonaList(null);
				}
			}
		}

		String codigoEstado = f.getEstado();
		String estadoGP1 = "";
		String estadoFacturadas = "";
		String estadoError = "";
		String estadoRechazadas = "";
		String estadoSinValidar = "";

		if (codigoEstado.equals(Constants.CODIGO_ESTADO_GP1))
			estadoGP1 = Constants.SI;
		if (codigoEstado.equals(Constants.CODIGO_ESTADO_FACTURADAS))
			estadoFacturadas = Constants.SI;
		if (codigoEstado.equals(Constants.CODIGO_ESTADO_ERROR))
			estadoError = Constants.SI;
		if (codigoEstado.equals(Constants.CODIGO_ESTADO_RECHAZADAS))
			estadoRechazadas = Constants.SI;
		if (codigoEstado.equals(Constants.CODIGO_ESTADO_SIN_VALIDAR))
			estadoSinValidar = Constants.SI;

		try {
			params = BeanUtils.describe(f);
			params.put("codigoPais", pais.getCodigo());
			params.put("tipoDocumento", f.getTipoDocumento());
			params.put("numLote", numLote);
			params.put("indDocu", indDocu);
			params.put("fechaInicio", fechaInicio.trim());
			params.put("fechaFin", fechaFin.trim());
			params.put("fechaInicioProceso", fechaInicioProceso.trim());
			params.put("fechaFinProceso", fechaFinProceso.trim());
			params.put("codigoCliente", codigoCliente);
			params.put("codigoPeriodo", codigoPeriodo);
			params.put(
					"regionList",
					(f.getRegionList() == null) ? new ArrayList() : Arrays
							.asList(f.getRegionList()));
			params.put("zonaList", (f.getZonaList() == null) ? new ArrayList()
					: Arrays.asList(f.getZonaList()));
			params.put("estadoGP1", estadoGP1);
			params.put("estadoFacturadas", estadoFacturadas);
			params.put("estadoError", estadoError);
			params.put("estadoRechazadas", estadoRechazadas);
			params.put("estadoSinValidar", estadoSinValidar);
			String numDocumento = f.getNumeroDocumento();
			String[] arrayNumDocumento = new String[0];
			f.setHoraInicioCarga("");
			f.setHoraFinCarga("");

			if (numDocumento.length() > 0)
				arrayNumDocumento = numDocumento.split(",");
			params.put(
					"numeroDocumentoList",
					(numDocumento.length() == 0) ? new ArrayList() : Arrays
							.asList(arrayNumDocumento));

		} catch (IllegalAccessException e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		} catch (InvocationTargetException e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		} catch (NoSuchMethodException e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
		return params;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction
	 * #devuelveFormProceso()
	 */
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoSTOEliminarCDRForm form = new ProcesoSTOEliminarCDRForm();
		return form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction
	 * #validarExecuteProceso(javax.faces.event.ActionEvent)
	 */
	@Override
	public void validarExecuteProceso(ActionEvent actionEvent) {
		super.validarExecuteProceso(actionEvent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction
	 * #executeProcess(java.util.Map)
	 */
	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		log.debug("executeProcess");

		ProcesoSTOEliminarCDRForm f = (ProcesoSTOEliminarCDRForm) this.formProceso;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		this.stoList = new ArrayList();
		int tamanio = this.beanProcesoSTOEliminarCDR.length;
		for (int i = 0; i < tamanio; i++) {
			ConsultaValidaciones form = (ConsultaValidaciones) this.beanProcesoSTOEliminarCDR[i];
			DocumentoDigitadoPK documento = new DocumentoDigitadoPK();
			documento.setCodPais(f.getCodigoPais());
			documento.setCodTipoDocu(Constants.STO_TIPO_DOCUMENTO_SPVC);
			String numeroLote = "";
			if (!StringUtils.isBlank(form.getLote())) {
				numeroLote = form.getLote();
			}
			documento.setNumLote(numeroLote);
			String secNumeDocu = "";
			if (!StringUtils.isBlank(form.getSecNumeDocu())) {
				secNumeDocu = form.getSecNumeDocu();
			}
			documento.setSecNumeDocu(secNumeDocu);
			this.stoList.add(documento);
		}

		ProcesoSTOExecutionService service = (ProcesoSTOExecutionService) getBean("spusicc.procesoSTOExecutionService");
		params.put("codTipoDocu", f.getTipoDocumento());

		AccionTipoDocumento accionTipoDocumento = new AccionTipoDocumento(
				pais.getCodigo(), Constants.TIPO_DOCUMENTO_SPVC,
				Constants.STO_ACCI_ELIM_SELE);
		DocumentoSTOResult result = service.execute(accionTipoDocumento,
				params, this.stoList);

		params.put("numeroProceso", result.getNumeroProceso());
		ProcesoSTOLevantamientoErroresValidacionService serviceSTO = (ProcesoSTOLevantamientoErroresValidacionService) getBean("spusicc.procesoSTOLevantamientoErroresValidacionService");
		List stoReclamosEliminadosList = serviceSTO
				.getReclamosEliminados(params);
		this.stoListaReclamosEliminados = stoReclamosEliminadosList;
		if(this.stoListaReclamosEliminados.size() > 0){
			this.mostrarSegundoDatatable = true;
			this.mostrarDatatable = false;
			this.stoListaValidaciones = new  ArrayList();
		}
		return params;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction
	 * #setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {
		if (accion.equals("CERRAR")) {
			ProcesoSTOEliminarCDRForm f = (ProcesoSTOEliminarCDRForm) this.formProceso;

			Integer lineaDefecto = Integer.parseInt(f.getLineaDefecto());
			Integer lineaMaxima = Integer.parseInt(f.getLineaMaxima());

			if (StringUtils.isBlank(f.getLineaDefecto())) {
				return this
						.getResourceMessage("procesoSTOEliminarCDRForm.numeroFilaValida");
			}
			if (lineaDefecto > lineaMaxima) {
				return this
						.getResourceMessage("procesoSTOEliminarCDRForm.numeroFilaMaxima");
			}

		}
		return "";
	}

	/**
	 * Inicializando valores
	 */
	public void inicializando() {
		this.mostrarBotonBuscar = true;
		this.mostrarBotonExecute = false;
		this.sumaSubtotalCargasT = 0;
		this.sumaSubtotalEnviadasT = 0;
		this.sumaSubtotalErradasT = 0;
		this.sumaSubtotalFacturadasT = 0;
		this.sumaSubtotalDetallesOkT = 0;
		this.sumaSubtotalRechazadasT = 0;
		this.sumaCargasT = 0;
		this.sumaEnviadasT = 0;
		this.sumaErradasT = 0;
		this.sumaFacturadasT = 0;
		this.sumaDetallesOkT = 0;
		this.sumaRechazadasT = 0;
		this.mostrarBotonEliminar = false;
		this.mostrarSegundoDatatable = false;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("Executing action : setViewAttributes.");
		inicializando();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		Map criteria = new HashMap();

		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoUsuario", usuario.getLogin());

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ProcesoSTOLevantamientoErroresValidacionService procesoSTOLevantamientoErroresValidacionService = (ProcesoSTOLevantamientoErroresValidacionService) getBean("spusicc.procesoSTOLevantamientoErroresValidacionService");

		ProcesoSTOEliminarCDRForm f = (ProcesoSTOEliminarCDRForm) this.formProceso;
		criteria.put("indicadorPantalla",
				Constants.STO_INDICADOR_PANTALLA_ELIMINACION);

		f.setTipoDocumento(Constants.TIPO_DOCUMENTO_SPVC);

		this.siccRegionList = reporteService.getListaGenerico(
				"getRegionesByPais", criteriaOperacion);

		criteria.put("codigoParametro", Constants.STO_INTERVALO_CARGA_STO);
		List listaHorasCarga = procesoSTOLevantamientoErroresValidacionService
				.getListaHoras(criteria);
		this.stoHorasCargaList = listaHorasCarga;

		criteria.put("codigoParametro", Constants.STO_INTERVALO_PROCESO_STO);
		List listaHorasProceso = procesoSTOLevantamientoErroresValidacionService
				.getListaHoras(criteria);
		this.stoHorasProcesoList = listaHorasProceso;
		// Cargo por default la campa�a actual de ssicc

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
		PedidoControlFacturacion controlFacturacion = serviceFact
				.getControlFacturacionById(criteria);
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		// Setea la longitud del codigo de cliente segun el pais
		ClienteUAGenerarService clienteService = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");
		this.longitudCampoClientes = clienteService
				.getTamanhoNumeroCliente(pais.getCodigo());
		// Setea la longitud del numero de documento
		this.stoLongitudNumeroDocumento = Constants.VAL_STO_LONGITUD_NUMERO_DOCUMENTO;

		String codigoPais = pais.getCodigo();

		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		String lineaDefecto = ajaxService.getNumeroRegistrosSTO(codigoPais,
				Constants.STO_TIPO_DOCUMENTO_OCC, "1");
		String lineaMaxima = ajaxService.getNumeroRegistrosSTO(codigoPais,
				Constants.STO_TIPO_DOCUMENTO_OCC, "2");
		f.setLineaDefecto(lineaDefecto);
		f.setLineaMaxima(lineaMaxima);
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		this.stoEstadosGestionList = ajax.getEstadosSTOByTipoDocumento(
				pais.getCodigo(), f.getTipoDocumento());

		f.setCodigoPais(pais.getCodigo());
		Date fechaFin = new Date(System.currentTimeMillis());

		f.setFechaInicioProcesoD(new Date());
		fechaFin = DateUtil.addToDate(fechaFin, Calendar.DATE, 1);
		f.setFechaFinProcesoD(fechaFin);
		f.setHoraInicioProceso("00:00");
		f.setHoraFinProceso("00:00");
		f.setEstado("01");
		f.setAccion("");
		// f.setCodigoPais(codigoPais);
		log.debug("------------entra al view ------------");
	}

	/**
	 * Carga Zonas
	 * 
	 * @param val
	 */
	public void loadZonas(ValueChangeEvent val) {
		log.debug(">>loadZonas");
		try {
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

			String[] valor = (String[]) val.getNewValue();
			// String [] regiones={valor};
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			if (valor.length > 0) {
				this.siccZonaList = ajax
						.getZonasMultipleByPaisMarcaCanalRegion(
								pais.getCodigo(), "T", "VD", valor, "T");
			} else {
				this.siccZonaList = null;
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * @return the regionListSearch
	 */
	public String[] getRegionListSearch() {
		return regionListSearch;
	}

	/**
	 * @param regionListSearch
	 *            the regionListSearch to set
	 */
	public void setRegionListSearch(String[] regionListSearch) {
		this.regionListSearch = regionListSearch;
	}

	/**
	 * @return the zonaListSearch
	 */
	public String[] getZonaListSearch() {
		return zonaListSearch;
	}

	/**
	 * @param zonaListSearch
	 *            the zonaListSearch to set
	 */
	public void setZonaListSearch(String[] zonaListSearch) {
		this.zonaListSearch = zonaListSearch;
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
	 * @return the stoListaPedidos
	 */
	public List getStoListaPedidos() {
		return stoListaPedidos;
	}

	/**
	 * @param stoListaPedidos
	 *            the stoListaPedidos to set
	 */
	public void setStoListaPedidos(List stoListaPedidos) {
		this.stoListaPedidos = stoListaPedidos;
	}

	/**
	 * @return the stoEliminarPedidosClientesList
	 */
	public List getStoEliminarPedidosClientesList() {
		return stoEliminarPedidosClientesList;
	}

	/**
	 * @param stoEliminarPedidosClientesList
	 *            the stoEliminarPedidosClientesList to set
	 */
	public void setStoEliminarPedidosClientesList(
			List stoEliminarPedidosClientesList) {
		this.stoEliminarPedidosClientesList = stoEliminarPedidosClientesList;
	}

	/**
	 * @return the siccRegionList
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 *            the siccRegionList to set
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the stoEstadosGestionList
	 */
	public LabelValue[] getStoEstadosGestionList() {
		return stoEstadosGestionList;
	}

	/**
	 * @param stoEstadosGestionList
	 *            the stoEstadosGestionList to set
	 */
	public void setStoEstadosGestionList(LabelValue[] stoEstadosGestionList) {
		this.stoEstadosGestionList = stoEstadosGestionList;
	}

	/**
	 * @return the stoHorasProcesoList
	 */
	public List getStoHorasProcesoList() {
		return stoHorasProcesoList;
	}

	/**
	 * @param stoHorasProcesoList
	 *            the stoHorasProcesoList to set
	 */
	public void setStoHorasProcesoList(List stoHorasProcesoList) {
		this.stoHorasProcesoList = stoHorasProcesoList;
	}

	/**
	 * @return the stoHorasCargaList
	 */
	public List getStoHorasCargaList() {
		return stoHorasCargaList;
	}

	/**
	 * @param stoHorasCargaList
	 *            the stoHorasCargaList to set
	 */
	public void setStoHorasCargaList(List stoHorasCargaList) {
		this.stoHorasCargaList = stoHorasCargaList;
	}

	/**
	 * @return the longitudCampoClientes
	 */
	public Integer getLongitudCampoClientes() {
		return longitudCampoClientes;
	}

	/**
	 * @param longitudCampoClientes
	 *            the longitudCampoClientes to set
	 */
	public void setLongitudCampoClientes(Integer longitudCampoClientes) {
		this.longitudCampoClientes = longitudCampoClientes;
	}

	/**
	 * @return the mostrarGrilla
	 */
	public Boolean getMostrarGrilla() {
		return mostrarGrilla;
	}

	/**
	 * @param mostrarGrilla
	 *            the mostrarGrilla to set
	 */
	public void setMostrarGrilla(Boolean mostrarGrilla) {
		this.mostrarGrilla = mostrarGrilla;
	}

	/**
	 * @return the attachment
	 */
	public String getAttachment() {
		return attachment;
	}

	/**
	 * @param attachment
	 *            the attachment to set
	 */
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	/**
	 * @return the sumaCorrectasT
	 */
	public Integer getSumaCorrectasT() {
		return sumaCorrectasT;
	}

	/**
	 * @param sumaCorrectasT
	 *            the sumaCorrectasT to set
	 */
	public void setSumaCorrectasT(Integer sumaCorrectasT) {
		this.sumaCorrectasT = sumaCorrectasT;
	}

	/**
	 * @return the sumaGP2T
	 */
	public Integer getSumaGP2T() {
		return sumaGP2T;
	}

	/**
	 * @param sumaGP2T
	 *            the sumaGP2T to set
	 */
	public void setSumaGP2T(Integer sumaGP2T) {
		this.sumaGP2T = sumaGP2T;
	}

	/**
	 * @return the sumaGP3T
	 */
	public Integer getSumaGP3T() {
		return sumaGP3T;
	}

	/**
	 * @param sumaGP3T
	 *            the sumaGP3T to set
	 */
	public void setSumaGP3T(Integer sumaGP3T) {
		this.sumaGP3T = sumaGP3T;
	}

	/**
	 * @return the sumaRechazadas
	 */
	public Integer getSumaRechazadas() {
		return sumaRechazadas;
	}

	/**
	 * @param sumaRechazadas
	 *            the sumaRechazadas to set
	 */
	public void setSumaRechazadas(Integer sumaRechazadas) {
		this.sumaRechazadas = sumaRechazadas;
	}

	/**
	 * @return the sumaSubtotalCorrectasT
	 */
	public Integer getSumaSubtotalCorrectasT() {
		return sumaSubtotalCorrectasT;
	}

	/**
	 * @param sumaSubtotalCorrectasT
	 *            the sumaSubtotalCorrectasT to set
	 */
	public void setSumaSubtotalCorrectasT(Integer sumaSubtotalCorrectasT) {
		this.sumaSubtotalCorrectasT = sumaSubtotalCorrectasT;
	}

	/**
	 * @return the sumaSubtotalGP2T
	 */
	public Integer getSumaSubtotalGP2T() {
		return sumaSubtotalGP2T;
	}

	/**
	 * @param sumaSubtotalGP2T
	 *            the sumaSubtotalGP2T to set
	 */
	public void setSumaSubtotalGP2T(Integer sumaSubtotalGP2T) {
		this.sumaSubtotalGP2T = sumaSubtotalGP2T;
	}

	/**
	 * @return the sumaSubtotalGP3T
	 */
	public Integer getSumaSubtotalGP3T() {
		return sumaSubtotalGP3T;
	}

	/**
	 * @param sumaSubtotalGP3T
	 *            the sumaSubtotalGP3T to set
	 */
	public void setSumaSubtotalGP3T(Integer sumaSubtotalGP3T) {
		this.sumaSubtotalGP3T = sumaSubtotalGP3T;
	}

	/**
	 * @return the sumaSubtotalRechazadasT
	 */
	public Integer getSumaSubtotalRechazadasT() {
		return sumaSubtotalRechazadasT;
	}

	/**
	 * @param sumaSubtotalRechazadasT
	 *            the sumaSubtotalRechazadasT to set
	 */
	public void setSumaSubtotalRechazadasT(Integer sumaSubtotalRechazadasT) {
		this.sumaSubtotalRechazadasT = sumaSubtotalRechazadasT;
	}

	/**
	 * @return the mostrarDatatable
	 */
	public Boolean getMostrarDatatable() {
		return mostrarDatatable;
	}

	/**
	 * @param mostrarDatatable
	 *            the mostrarDatatable to set
	 */
	public void setMostrarDatatable(Boolean mostrarDatatable) {
		this.mostrarDatatable = mostrarDatatable;
	}

	/**
	 * @return the pagina
	 */
	public Integer getPagina() {
		return pagina;
	}

	/**
	 * @param pagina
	 *            the pagina to set
	 */
	public void setPagina(Integer pagina) {
		this.pagina = pagina;
	}

	/**
	 * @return the beanProcesoSTOEliminarCDR
	 */
	public Object[] getBeanProcesoSTOEliminarCDR() {
		return beanProcesoSTOEliminarCDR;
	}

	/**
	 * @param beanProcesoSTOEliminarCDR
	 *            the beanProcesoSTOEliminarCDR to set
	 */
	public void setBeanProcesoSTOEliminarCDR(Object[] beanProcesoSTOEliminarCDR) {
		this.beanProcesoSTOEliminarCDR = beanProcesoSTOEliminarCDR;
	}

	/**
	 * @return the stoResumenClientesList
	 */
	public List getStoResumenClientesList() {
		return stoResumenClientesList;
	}

	/**
	 * @param stoResumenClientesList
	 *            the stoResumenClientesList to set
	 */
	public void setStoResumenClientesList(List stoResumenClientesList) {
		this.stoResumenClientesList = stoResumenClientesList;
	}

	/**
	 * @return the stoListaValidaciones
	 */
	public List getStoListaValidaciones() {
		return stoListaValidaciones;
	}

	/**
	 * @param stoListaValidaciones
	 *            the stoListaValidaciones to set
	 */
	public void setStoListaValidaciones(List stoListaValidaciones) {
		this.stoListaValidaciones = stoListaValidaciones;
	}

	/**
	 * @return the stoLongitudNumeroDocumento
	 */
	public String getStoLongitudNumeroDocumento() {
		return stoLongitudNumeroDocumento;
	}

	/**
	 * @param stoLongitudNumeroDocumento
	 *            the stoLongitudNumeroDocumento to set
	 */
	public void setStoLongitudNumeroDocumento(String stoLongitudNumeroDocumento) {
		this.stoLongitudNumeroDocumento = stoLongitudNumeroDocumento;
	}

	/**
	 * @return the stoListaReclamosEliminados
	 */
	public List getStoListaReclamosEliminados() {
		return stoListaReclamosEliminados;
	}

	/**
	 * @param stoListaReclamosEliminados
	 *            the stoListaReclamosEliminados to set
	 */
	public void setStoListaReclamosEliminados(List stoListaReclamosEliminados) {
		this.stoListaReclamosEliminados = stoListaReclamosEliminados;
	}

	/**
	 * @return the sumaSubtotalCargasT
	 */
	public Integer getSumaSubtotalCargasT() {
		return sumaSubtotalCargasT;
	}

	/**
	 * @param sumaSubtotalCargasT
	 *            the sumaSubtotalCargasT to set
	 */
	public void setSumaSubtotalCargasT(Integer sumaSubtotalCargasT) {
		this.sumaSubtotalCargasT = sumaSubtotalCargasT;
	}

	/**
	 * @return the sumaSubtotalEnviadasT
	 */
	public Integer getSumaSubtotalEnviadasT() {
		return sumaSubtotalEnviadasT;
	}

	/**
	 * @param sumaSubtotalEnviadasT
	 *            the sumaSubtotalEnviadasT to set
	 */
	public void setSumaSubtotalEnviadasT(Integer sumaSubtotalEnviadasT) {
		this.sumaSubtotalEnviadasT = sumaSubtotalEnviadasT;
	}

	/**
	 * @return the sumaSubtotalErradasT
	 */
	public Integer getSumaSubtotalErradasT() {
		return sumaSubtotalErradasT;
	}

	/**
	 * @param sumaSubtotalErradasT
	 *            the sumaSubtotalErradasT to set
	 */
	public void setSumaSubtotalErradasT(Integer sumaSubtotalErradasT) {
		this.sumaSubtotalErradasT = sumaSubtotalErradasT;
	}

	/**
	 * @return the sumaSubtotalFacturadasT
	 */
	public Integer getSumaSubtotalFacturadasT() {
		return sumaSubtotalFacturadasT;
	}

	/**
	 * @param sumaSubtotalFacturadasT
	 *            the sumaSubtotalFacturadasT to set
	 */
	public void setSumaSubtotalFacturadasT(Integer sumaSubtotalFacturadasT) {
		this.sumaSubtotalFacturadasT = sumaSubtotalFacturadasT;
	}

	/**
	 * @return the sumaSubtotalDetallesOkT
	 */
	public Integer getSumaSubtotalDetallesOkT() {
		return sumaSubtotalDetallesOkT;
	}

	/**
	 * @param sumaSubtotalDetallesOkT
	 *            the sumaSubtotalDetallesOkT to set
	 */
	public void setSumaSubtotalDetallesOkT(Integer sumaSubtotalDetallesOkT) {
		this.sumaSubtotalDetallesOkT = sumaSubtotalDetallesOkT;
	}

	/**
	 * @return the sumaCargasT
	 */
	public Integer getSumaCargasT() {
		return sumaCargasT;
	}

	/**
	 * @param sumaCargasT
	 *            the sumaCargasT to set
	 */
	public void setSumaCargasT(Integer sumaCargasT) {
		this.sumaCargasT = sumaCargasT;
	}

	/**
	 * @return the sumaEnviadasT
	 */
	public Integer getSumaEnviadasT() {
		return sumaEnviadasT;
	}

	/**
	 * @param sumaEnviadasT
	 *            the sumaEnviadasT to set
	 */
	public void setSumaEnviadasT(Integer sumaEnviadasT) {
		this.sumaEnviadasT = sumaEnviadasT;
	}

	/**
	 * @return the sumaErradasT
	 */
	public Integer getSumaErradasT() {
		return sumaErradasT;
	}

	/**
	 * @param sumaErradasT
	 *            the sumaErradasT to set
	 */
	public void setSumaErradasT(Integer sumaErradasT) {
		this.sumaErradasT = sumaErradasT;
	}

	/**
	 * @return the sumaFacturadasT
	 */
	public Integer getSumaFacturadasT() {
		return sumaFacturadasT;
	}

	/**
	 * @param sumaFacturadasT
	 *            the sumaFacturadasT to set
	 */
	public void setSumaFacturadasT(Integer sumaFacturadasT) {
		this.sumaFacturadasT = sumaFacturadasT;
	}

	/**
	 * @return the sumaDetallesOkT
	 */
	public Integer getSumaDetallesOkT() {
		return sumaDetallesOkT;
	}

	/**
	 * @param sumaDetallesOkT
	 *            the sumaDetallesOkT to set
	 */
	public void setSumaDetallesOkT(Integer sumaDetallesOkT) {
		this.sumaDetallesOkT = sumaDetallesOkT;
	}

	/**
	 * @return the sumaRechazadasT
	 */
	public Integer getSumaRechazadasT() {
		return sumaRechazadasT;
	}

	/**
	 * @param sumaRechazadasT
	 *            the sumaRechazadasT to set
	 */
	public void setSumaRechazadasT(Integer sumaRechazadasT) {
		this.sumaRechazadasT = sumaRechazadasT;
	}

	/**
	 * @return the mostrarBotonEliminar
	 */
	public Boolean getMostrarBotonEliminar() {
		return mostrarBotonEliminar;
	}

	/**
	 * @param mostrarBotonEliminar
	 *            the mostrarBotonEliminar to set
	 */
	public void setMostrarBotonEliminar(Boolean mostrarBotonEliminar) {
		this.mostrarBotonEliminar = mostrarBotonEliminar;
	}

	/**
	 * @return the listaSeleccionados
	 */
	public List getListaSeleccionados() {
		return listaSeleccionados;
	}

	/**
	 * @param listaSeleccionados
	 *            the listaSeleccionados to set
	 */
	public void setListaSeleccionados(List listaSeleccionados) {
		this.listaSeleccionados = listaSeleccionados;
	}

	/**
	 * @return the stoList
	 */
	public ArrayList getStoList() {
		return stoList;
	}

	/**
	 * @param stoList
	 *            the stoList to set
	 */
	public void setStoList(ArrayList stoList) {
		this.stoList = stoList;
	}

	/**
	 * @return the mostrarSegundoDatatable
	 */
	public Boolean getMostrarSegundoDatatable() {
		return mostrarSegundoDatatable;
	}

	/**
	 * @param mostrarSegundoDatatable the mostrarSegundoDatatable to set
	 */
	public void setMostrarSegundoDatatable(Boolean mostrarSegundoDatatable) {
		this.mostrarSegundoDatatable = mostrarSegundoDatatable;
	}
	
	
}