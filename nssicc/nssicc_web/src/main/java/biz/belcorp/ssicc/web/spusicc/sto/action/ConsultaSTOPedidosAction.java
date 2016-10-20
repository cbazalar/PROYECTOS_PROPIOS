package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.data.PageEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.dao.DataAccessException;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.spusicc.sto.model.ConsultaPedidos;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitado;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitadoPK;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECIngresoAtencionesService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOLevantamientoErroresValidacionService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOService;
import biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.sto.form.ConsultaSTOPedidosForm;

@SuppressWarnings({ "rawtypes", "unchecked" })
@ManagedBean
@SessionScoped
public class ConsultaSTOPedidosAction extends BaseConsultaAbstractAction {

	private static final long serialVersionUID = 1L;
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
	private Boolean agrupacionPeriodo;
	private Boolean agrupacionFechaCarga;
	private Boolean agrupacionFechaProceso;
	private Boolean agrupacionLote;
	private Boolean agrupacionRegion;
	private Boolean agrupacionZona;
	private Boolean agrupacionCliente;
	private Boolean agrupacionOrigen;
	private Boolean agrupacionFechaProgFacturacion;
	private Integer sumaCargas;
	private Integer sumaEnviadas;
	private Integer sumaErradas;
	private Integer sumaRechazadas;
	private Integer sumaSubtotalCargasT;
	private Integer sumaSubtotalEnviadasT;
	private Integer sumaSubtotalErradasT;
	private Integer sumaSubtotalRechazadasT;
	private Boolean mostrarDatatable;
	private Integer pagina;
	private Object[] beanConsultaSTOPedidos;
	private List stoResumenList;
	private LabelValue[] stoOrigenesList;
	private Integer sumaNumGP2T;
	private Integer sumaSubtotalNoValidadasT;
	private Integer sumaSubtotalNumGP5T;
	private Integer sumaSubtotalNumGP4T;
	private Integer sumaSubtotalNumGP3T;
	private Integer sumaSubtotalNumGP2T;
	private Integer sumaSubtotalCorrectasT;
	private Integer sumaNumGP3T;
	private Integer sumaNumGP4T;
	private Integer sumaNumGP5T;
	private Integer sumaCorrectasT;
	private Integer sumaCargasT;
	private Integer sumaErradasT;
	private Integer sumaRechazadasT;
	private Integer sumaNoValidadasT;
	private Boolean mostrarComboAccion;
	private List listaCliente;
	private String numeroSecuenciaConsulta;
	private List stoConsultarPedidosClientesList;
	private List stoResumenClientesList;
	private DataTableModel dmConsultaSTOPedidos;

	@ManagedProperty(value = "#{consultaSTODetallePedidosAction}")
	private ConsultaSTODetallePedidosAction popupConsultaSTODetallePedidos;

	/**
	 * MOSTRAR ZONAS RESPECTO A REGIONES
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
								pais.getCodigo(), "T", "VD", valor, "");
			} else {
				this.siccZonaList = null;
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * Metodo Invocador de metodos para Cargar Archivo
	 * 
	 * @param event
	 * @throws Exception
	 */
	public void handleFileUpload(FileUploadEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("handleFileUpload");
		}
		ConsultaSTOPedidosForm f = (ConsultaSTOPedidosForm) this.formBusqueda;
		if (event != null) {
			// f.setArchivo(event.getFile());
			f.setClienteFile(event.getFile());
			this.setAttachment(event.getFile().getFileName());
			this.loadfile();
		}
	}

	/**
	 * @param f
	 *            Setea los checkbox de agrupacion marcados por defecto
	 */
	private void setAgrupacionDefault(ConsultaSTOPedidosForm f,
			String codigoPais) {
		ProcesoSTOService procesoSTOService = (ProcesoSTOService) getBean("spusicc.procesoSTOService");
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		String desmarcarAgrupacion = procesoSTOService
				.getindicadorDesmarcarAgrupacion(criteria);

		if (desmarcarAgrupacion.equals(Constants.SI)) {
			f.setAgrupacion(Constants.NO);
			f.setAgrupacionFechaProceso(Constants.NO);
			f.setAgrupacionPeriodo(Constants.NO);
			f.setAgrupacionLote(Constants.NO);
			f.setAgrupacionZona(Constants.NO);
			f.setAgrupacionRegion(Constants.NO);
			f.setAgrupacionCliente(Constants.NO);
			f.setAgrupacionOrigen(Constants.NO);
			f.setAgrupacionFechaProgFacturacion(Constants.NO);
		} else {
			f.setAgrupacion(Constants.SI);
			f.setAgrupacionFechaProceso(Constants.SI);
			f.setAgrupacionPeriodo(Constants.SI);
			f.setAgrupacionLote(Constants.SI);
			f.setAgrupacionZona(Constants.SI);
			f.setAgrupacionRegion(Constants.SI);
			f.setAgrupacionCliente(Constants.SI);
			f.setAgrupacionOrigen(Constants.SI);
			f.setAgrupacionFechaProgFacturacion(Constants.SI);
		}
	}

	/**
	 * Metodo que captura la pagina del datatable
	 * 
	 * @param e
	 */
	public void onPage(PageEvent e) {
		String hola;
		int paginas = e.getPage();
		this.pagina = paginas;
		calcularSubtotales();
	}

	/**
	 * Guarda los filtros seleccionados
	 */
	private void setFilterSearch() {

		ConsultaSTOPedidosForm f = (ConsultaSTOPedidosForm) this.formBusqueda;
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
		f.setAgrupacionFechaProcesoSearch(f.getAgrupacionFechaProceso());
		f.setAgrupacionLoteSearch(f.getAgrupacionLote());
		f.setAgrupacionRegionSearch(f.getAgrupacionRegion());
		f.setAgrupacionZonaSearch(f.getAgrupacionZona());
		f.setAgrupacionClienteSearch(f.getAgrupacionCliente());
		f.setHoraInicioCargaSearch(f.getHoraInicioCarga());
		f.setHoraFinCargaSearch(f.getHoraFinCarga());
		f.setHoraInicioProcesoSearch(f.getHoraInicioProceso());
		f.setHoraFinProcesoSearch(f.getHoraFinProceso());
		f.setCodigoPeriodoSearch(f.getCodigoPeriodo());
		f.setAgrupacionPeriodoSearch(f.getAgrupacionPeriodo());
		f.setEstadoSearch(f.getEstado());
		f.setAccionSearch(f.getAccion());
		f.setCodigoOrigenSearch(f.getCodigoOrigen());
		f.setAgrupacionOrigenSearch(f.getAgrupacionOrigen());

		this.regionListSearch = f.getRegionList();
		this.zonaListSearch = f.getZonaList();

	}

	/**
	 * @param request
	 * @param cabecera
	 */
	private void syncZona() {
		ConsultaSTOPedidosForm cabecera = (ConsultaSTOPedidosForm) this.formBusqueda;
		String[] codigoRegion = cabecera.getRegionList();
		if (cabecera.getRegionList() != null) {
			LabelValue[] resultZona = this.getZonaByRegion(
					cabecera.getCodigoPais(), codigoRegion);
			this.siccZonaList = resultZona;
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
				log.warn(ignore.getMessage());
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
		ConsultaSTOPedidosForm f = (ConsultaSTOPedidosForm) this.formBusqueda;
		Map params = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String numLote = f.getNumeroLote();

		String indDocu = new String();
		indDocu = "1";

		String fechaInicio = null;
		if (!f.getFechaInicio().equals(""))
			fechaInicio = (f.getFechaInicio() + " " + f.getHoraInicioCarga())
					.trim();

		String fechaFin = null;
		if (!f.getFechaFin().equals(""))
			fechaFin = (f.getFechaFin() + " " + f.getHoraFinCarga()).trim();

		String fechaInicioProceso = null;
		if (!f.getFechaInicioProceso().equals(""))
			fechaInicioProceso = (f.getFechaInicioProceso() + " " + f
					.getHoraInicioProceso()).trim();

		String fechaFinProceso = null;
		if (!f.getFechaFinProceso().equals(""))
			fechaFinProceso = (f.getFechaFinProceso() + " " + f
					.getHoraFinProceso()).trim();

		String codigoCliente = f.getCodigoCliente();
		String codigoPeriodo = f.getCodigoPeriodo();
		String fechaInicioProgramadaFacturacion = f
				.getFechaInicioProgramadaFacturacion();
		String fechaFinProgramadaFacturacion = f
				.getFechaFinProgramadaFacturacion();

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

		String agruCliente = f.getAgrupacionCliente();
		String agruLote = f.getAgrupacionLote();
		String agruRegion = f.getAgrupacionRegion();
		String agruZona = f.getAgrupacionZona();
		String agruFecProce = f.getAgrupacionFechaProceso();
		String agruFecDigi = f.getAgrupacion();
		String agruPeriodo = f.getAgrupacionPeriodo();
		String agruOrigen = f.getAgrupacionOrigen();
		String agruFecFactu = f.getAgrupacionFechaProgFacturacion();

		if (agruCliente.compareToIgnoreCase("N") == 0)
			agruCliente = "";
		if (agruLote.compareToIgnoreCase("N") == 0)
			agruLote = "";
		if (agruRegion.compareToIgnoreCase("N") == 0)
			agruRegion = "";
		if (agruZona.compareToIgnoreCase("N") == 0)
			agruZona = "";
		if (agruFecProce.compareToIgnoreCase("N") == 0)
			agruFecProce = "";
		if (agruFecDigi.compareToIgnoreCase("N") == 0)
			agruFecDigi = "";
		if (agruPeriodo.compareToIgnoreCase("N") == 0)
			agruPeriodo = "";
		if (agruOrigen.compareToIgnoreCase("N") == 0)
			agruOrigen = "";
		if (agruFecFactu.compareToIgnoreCase("N") == 0)
			agruFecFactu = "";

		String codigoEstado = f.getEstado();
		String estadoGP1 = "";
		String estadoGP2 = "";
		String estadoGP3 = "";
		String estadoGP4 = "";
		String estadoGP5 = "";
		String estadoError = "";
		String estadoRechazadas = "";
		String estadoSinValidar = "";

		if (codigoEstado.equals(Constants.CODIGO_ESTADO_GP1))
			estadoGP1 = Constants.SI;
		if (codigoEstado.equals(Constants.CODIGO_ESTADO_GP2))
			estadoGP2 = Constants.SI;
		if (codigoEstado.equals(Constants.CODIGO_ESTADO_GP3))
			estadoGP3 = Constants.SI;
		if (codigoEstado.equals(Constants.CODIGO_ESTADO_GP4))
			estadoGP4 = Constants.SI;
		if (codigoEstado.equals(Constants.CODIGO_ESTADO_GP5))
			estadoGP5 = Constants.SI;
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
			params.put("fechaInicio", fechaInicio);
			params.put("fechaFin", fechaFin);
			params.put("fechaInicioProceso", fechaInicioProceso);
			params.put("fechaFinProceso", fechaFinProceso);
			params.put("agrupacionClinte", agruCliente);
			params.put("agruLote", agruLote);
			params.put("agruRegion", agruRegion);
			params.put("agruZona", agruZona);
			params.put("agruFecProce", agruFecProce);
			params.put("agruFecDigi", agruFecDigi);
			params.put("agruPeriodo", agruPeriodo);
			params.put("agruOrigen", agruOrigen);
			params.put("agruFecFactu", agruFecFactu);
			params.put("codigoCliente", codigoCliente);
			params.put("codigoPeriodo", codigoPeriodo);
			params.put(
					"regionList",
					(f.getRegionList() == null) ? new ArrayList() : Arrays
							.asList(f.getRegionList()));
			params.put("zonaList", (f.getZonaList() == null) ? new ArrayList()
					: Arrays.asList(f.getZonaList()));
			params.put("estadoGP1", estadoGP1);
			params.put("estadoGP2", estadoGP2);
			params.put("estadoGP3", estadoGP3);
			params.put("estadoGP4", estadoGP4);
			params.put("estadoGP5", estadoGP5);
			params.put("estadoError", estadoError);
			params.put("estadoRechazadas", estadoRechazadas);
			params.put("estadoSinValidar", estadoSinValidar);
			params.put("fechaInicioProgramadaFacturacion",
					fechaInicioProgramadaFacturacion);
			params.put("fechaFinProgramadaFacturacion",
					fechaFinProgramadaFacturacion);
			params.put("secuencia", f.getSecuencia());

			// ----------------------------------------------
			String[] arrlistaClientes = new String[0];
			List clienteList = new ArrayList();
			Long longitudPais = pais.getLongitudCodigoCliente();
			String codigoCliente2 = f.getCodigoCliente();
			String codigoCliente3 = f.getCodigoCliente();
			List listaClientes = new ArrayList();
			listaClientes = this.stoConsultarPedidosClientesList;

			Map map = new HashMap();

			if (listaClientes != null) {
				if (listaClientes.size() > 0) {
					for (int i = 0; i < listaClientes.size(); i++) {
						/*
						 * LabelValue bean = (LabelValue) listaClientes.get(i);
						 * param.put("codigoCliente",
						 * StringUtils.leftPad(bean.getLabel(),
						 * longitudPais.intValue(), '0'));
						 */
						map = (Map) listaClientes.get(i);
						codigoCliente3 = (String) map.get("codigoCliente");

						clienteList.add(codigoCliente3);
					}
				}
			}

			// Si es cargado por la caja de texto
			if (codigoCliente2 != null && codigoCliente2.length() > 0)
				arrlistaClientes = codigoCliente2.split(",+");
			/*
			 * else clienteList = null;
			 */
			for (int i = 0; i < arrlistaClientes.length; i++) {
				codigoCliente2 = StringUtils.leftPad(arrlistaClientes[i],
						longitudPais.intValue(), '0');

				clienteList.add(codigoCliente2);
			}

			params.put("clienteList", (clienteList == null) ? new ArrayList()
					: clienteList);
			// ----------------------------------------------

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
	 * biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction
	 * #setValidarConsulta()
	 */
	@Override
	public String setValidarConsulta() {
		ConsultaSTOPedidosForm f = (ConsultaSTOPedidosForm) this.formBusqueda;
		if (StringUtils.isBlank(f.getFechaInicioProgramadaFacturacion())
				|| StringUtils.isBlank(f.getFechaFinProgramadaFacturacion())) {

		} else {
			if (!validarFechas(f.getFechaInicioProgramadaFacturacionD(),
					f.getFechaFinProgramadaFacturacionD())) {
				return this.getResourceMessage("errors.compare.dates");
			}
		}

		if (StringUtils.isBlank(f.getFechaInicioProgramadaFacturacion())
				|| StringUtils.isBlank(f.getFechaFinProgramadaFacturacion())) {

		} else {
			if (!validarFechas(f.getFechaInicioD(), f.getFechaFinD())) {
				return this.getResourceMessage("errors.compare.dates");
			}
		}

		if (StringUtils.isBlank(f.getFechaInicioProceso())
				|| StringUtils.isBlank(f.getFechaFinProceso())) {

		} else {
			if (!validarFechas(f.getFechaInicioProcesoD(),
					f.getFechaFinProcesoD())) {
				return this.getResourceMessage("errors.compare.dates");
			}
		}
		return null;
	}

	public boolean validarFechas(Date fechaInicioDate, Date fechaFinDate) {

		if (fechaInicioDate.compareTo(fechaFinDate) > 0) {
			return false;
		}
		return true;
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * 
	 *             Carga el archivo de Clientes
	 * 
	 */
	public void loadfile() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'load Clientes from file' method");
		}

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ConsultaSTOPedidosForm f = (ConsultaSTOPedidosForm) this.formBusqueda;
		String codigoPais = pais.getCodigo();

		f.setTipoDocumento(Constants.STO_TIPO_DOCUMENTO_OCC);
		ProcesoSTOService procesoSTOService = (ProcesoSTOService) getBean("spusicc.procesoSTOService");
		MantenimientoRECIngresoAtencionesService service = (MantenimientoRECIngresoAtencionesService) getBean("spusicc.mantenimientoRECIngresoAtencionesService");
		GenericoService genericoService = (GenericoService) getBean("genericoService");

		TipoDocumentoDigitadoPK tipoDocumentoDigitadoPK = new TipoDocumentoDigitadoPK(
				codigoPais, Constants.STO_TIPO_DOCUMENTO_OCC);
		TipoDocumentoDigitado tipoDocumentoDigitado = procesoSTOService
				.getTipoDocumentoDigitado(tipoDocumentoDigitadoPK);

		f.setTipoDocumento(Constants.STO_TIPO_DOCUMENTO_OCC);
		f.setDescripcionDocumento(tipoDocumentoDigitado.getDesTipoDocu());

		List listaClientes = new ArrayList();

		String indValidaCodConsultoraDocIdentidad = null;

		ParametroPais parametroPais = new ParametroPais();
		parametroPais.setCodigoPais(pais.getCodigo());
		parametroPais.setCodigoSistema("STO");
		parametroPais.setNombreParametro("indValidaCodConsultoraDocIdentidad");

		List lstParametros = genericoService.getParametrosPais(parametroPais);

		if (lstParametros != null && lstParametros.size() > 0) {
			ParametroPais ps = (ParametroPais) lstParametros.get(0);
			indValidaCodConsultoraDocIdentidad = ps.getValorParametro();
		}

		UploadedFile archivo = f.getClienteFile();
		Map criteria = new HashMap();
		InputStream is = archivo.getInputstream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		Long longitudPais = pais.getLongitudCodigoCliente();
		String linea = "";
		String codigoConsultora = "";
		int cont = 0;
		int numRegistros = 0;

		while (true) {
			linea = br.readLine();
			if (linea == null)
				break;

			codigoConsultora = StringUtils.leftPad(linea.trim(),
					longitudPais.intValue(), '0');
			criteria.put("codigoConsultora", codigoConsultora);
			LabelValue bean = new LabelValue(codigoConsultora,
					service.getCodigoConsultora(criteria));

			if (bean.getValue() == null
					&& StringUtils.equals(indValidaCodConsultoraDocIdentidad,
							Constants.SI)) {
				criteria.put("documentoIdentidad", codigoConsultora);
				String codigoConsultoraPorDocIden = service
						.getCodigoConsultoraPorDocumentoIdentidad(criteria);

				if (codigoConsultoraPorDocIden == null) {
					bean = new LabelValue(
							codigoConsultora,
							service.getCodigoConsultoraPorDocumentoIdentidad(criteria));
				} else {
					bean = new LabelValue(
							codigoConsultoraPorDocIden,
							service.getCodigoConsultoraPorDocumentoIdentidad(criteria));
				}
			}

			listaClientes.add(bean);

			if (bean.getValue() == null)
				cont++;

			numRegistros++;
		}

		// Se inserta en la tabla temporal
		String oidCarga = service.getOidCargaCliente();
		criteria.put("oid", oidCarga);
		service.insertaClienteFile(listaClientes, criteria);

		// Se obtiene la lista de la tabla temporal
		List list = new ArrayList();
		list = service.getCargaClienteList(criteria);

		f.setCodigosErradosFile("");

		if (cont != 0)
			f.setCodigosErradosFile("Existe(n) " + cont
					+ " codigo(s) errado(s)");

		criteria.put("numRegistros", numRegistros);
		List list2 = new ArrayList();
		list2 = service.getResumenCargaClienteList(criteria);

		setAgrupacionDefault(f, codigoPais);
		this.stoConsultarPedidosClientesList = list;
		this.stoResumenClientesList = list2;
		int tamanio1 = this.stoConsultarPedidosClientesList.size();
		int tamanio2 = this.stoResumenClientesList.size();
		if (tamanio1 > 0 && tamanio2 > 0) {
			this.mostrarGrilla = true;
		}
	}

	/**
	 * Setea al form los verdaderos valores
	 */
	public void validarCambiosCheckBos() {
		ConsultaSTOPedidosForm f = (ConsultaSTOPedidosForm) this.formBusqueda;
		f.setFechaFin(DateUtil.convertDateToString(f.getFechaFinD()));
		f.setFechaInicio(DateUtil.convertDateToString(f.getFechaInicioD()));
		f.setFechaInicioProceso(DateUtil.convertDateToString(f
				.getFechaInicioProcesoD()));
		f.setFechaFinProceso(DateUtil.convertDateToString(f
				.getFechaFinProcesoD()));
		f.setFechaInicioProgramadaFacturacion(DateUtil.convertDateToString(f
				.getFechaInicioProgramadaFacturacionD()));
		f.setFechaFinProgramadaFacturacion(DateUtil.convertDateToString(f
				.getFechaFinProgramadaFacturacionD()));
		
		if(StringUtils.isBlank(f.getHoraInicioProceso()))			
			f.setHoraInicioProceso("");		
			
		if(StringUtils.isBlank(f.getHoraFinProceso()))			
			f.setHoraFinProceso("");		
			
		if(StringUtils.isBlank(f.getHoraInicioCarga()))
			f.setHoraInicioCarga("");		
			
		if(StringUtils.isBlank(f.getHoraFinCarga()))			
			f.setHoraFinCarga("");
		
		// f.getIndicadorDocumento();

		if (this.getAgrupacionPeriodo().equals(false)) {
			f.setAgrupacionPeriodo("N");
		} else {
			f.setAgrupacionPeriodo("S");
		}
		if (this.getAgrupacionFechaCarga().equals(false)) {
			f.setAgrupacion("N");
		} else {
			f.setAgrupacion("S");
		}
		if (this.getAgrupacionFechaProceso().equals(false)) {
			f.setAgrupacionFechaProceso("N");

		} else {
			f.setAgrupacionFechaProceso("S");
		}
		if (this.getAgrupacionLote().equals(false)) {
			f.setAgrupacionLote("N");
		} else {
			f.setAgrupacionLote("S");
		}

		if (this.getAgrupacionRegion().equals(false)) {
			f.setAgrupacionRegion("N");

		} else {
			f.setAgrupacionRegion("S");
		}

		if (this.getAgrupacionZona().equals(false)) {
			f.setAgrupacionZona("N");
		} else {
			f.setAgrupacionZona("S");
		}

		if (this.getAgrupacionCliente().equals(false)) {
			f.setAgrupacionCliente("N");
		} else {
			f.setAgrupacionCliente("S");
		}
		if (this.getAgrupacionOrigen().equals(false)) {
			f.setAgrupacionOrigen("N");
		} else {
			f.setAgrupacionOrigen("S");
		}
		if (this.agrupacionFechaProgFacturacion.equals(false)) {
			f.setAgrupacionFechaProgFacturacion("N");
		} else {
			f.setAgrupacionFechaProgFacturacion("S");
		}
	}

	/**
	 * INICIALIZANDO LOS DATOS QUE SE EJECUTARAN AL COMIENZO
	 */
	public void guardandoDatosForm() {
		ConsultaSTOPedidosForm f = (ConsultaSTOPedidosForm) this.formBusqueda;

		String valor = Constants.SI;
		boolean inicio = true;
		this.agrupacionCliente = inicio;
		this.agrupacionFechaCarga = inicio;
		this.agrupacionFechaProceso = inicio;
		this.agrupacionLote = inicio;
		this.agrupacionOrigen = inicio;
		this.agrupacionPeriodo = inicio;
		this.agrupacionRegion = inicio;
		this.agrupacionZona = inicio;
		this.agrupacionFechaProgFacturacion = inicio;

		f.setAgrupacionPeriodo(valor);
		f.setAgrupacion(valor);
		f.setAgrupacionFechaProceso(valor);
		f.setAgrupacionLote(valor);
		f.setAgrupacionRegion(valor);
		f.setAgrupacionZona(valor);
		f.setAgrupacionCliente(valor);
		f.setAgrupacionOrigen(valor);
		f.setAgrupacionFechaProgFacturacion(valor);

	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * 
	 *             Limpia el archivo de Clientes
	 * 
	 */
	public void clearFile() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'clearFile' method");
		}
		ConsultaSTOPedidosForm f = (ConsultaSTOPedidosForm) this.formBusqueda;
		f.setTipoDocumento(Constants.STO_TIPO_DOCUMENTO_OCC);
		this.stoConsultarPedidosClientesList = new ArrayList();
		this.stoResumenClientesList = new ArrayList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction
	 * #devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaSTOPedidosForm f = new ConsultaSTOPedidosForm();
		return f;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction
	 * #setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		log.debug("setFindAttributes.");
		validarCambiosCheckBos();

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map params;

		String codigoPais = pais.getCodigo();

		ProcesoSTOService procesoSTOService = (ProcesoSTOService) getBean("spusicc.procesoSTOService");
		TipoDocumentoDigitadoPK tipoDocumentoDigitadoPK = new TipoDocumentoDigitadoPK(
				codigoPais, Constants.STO_TIPO_DOCUMENTO_OCC);
		TipoDocumentoDigitado tipoDocumentoDigitado = procesoSTOService
				.getTipoDocumentoDigitado(tipoDocumentoDigitadoPK);
		ConsultaSTOPedidosForm f = (ConsultaSTOPedidosForm) this.formBusqueda;

		f.setDescripcionDocumento(tipoDocumentoDigitado.getDesTipoDocu());
		String secuencia = procesoSTOService.getSecuenciaConsultaDocumento();
		this.numeroSecuenciaConsulta = secuencia;

		f.setSecuencia(secuencia);

		params = getCriteria();

		List consultaValidacionList = procesoSTOService
				.getConsultaPedidosList(params);
		this.stoListaPedidos = new ArrayList();
		this.stoListaPedidos = consultaValidacionList;
		// for (int i = 0; i < this.stoListaPedidos.size(); i++) {
		// ConsultaPedidos mapa = (ConsultaPedidos) this.stoListaPedidos.get(i);
		//
		// Integer numGP3 = mapa.getNumGP3();
		// Integer numGP4 =mapa.getNumGP4();
		// Integer numGP5 = mapa.getNumGP5();
		// if(numGP3 > 0 || numGP4 > 0 || numGP5 > 0){
		// this.stoListaPedidos.add(i,"ola");
		// }else{
		// this.stoListaPedidos.add(i,"no");
		// }
		//
		// }

		int tamanio = this.stoListaPedidos.size();
		if (tamanio > 0) {
			this.mostrarComboAccion = true;

			int sumaCargasT = 0;
			int sumaCorrectasT = 0;
			int sumaNumGP2T = 0;
			int sumaNumGP3T = 0;
			int sumaNumGP4T = 0;
			int sumaNumGP5T = 0;
			int sumaErradasT = 0;
			int sumaRechazadasT = 0;
			int sumaNoValidadasT = 0;

			int valorPagina = 1;
			int filasMuestra = 10;
			int valorFinal = 0;
			valorFinal = valorPagina * filasMuestra;
			int valorInicial = 0;

			int sumaSubtotalCargasT = 0;
			int sumaSubtotalCorrectasT = 0;
			int sumaSubtotalNumGP2T = 0;
			int sumaSubtotalNumGP3T = 0;
			int sumaSubtotalNumGP4T = 0;
			int sumaSubtotalNumGP5T = 0;
			int sumaSubtotalErradasT = 0;
			int sumaSubtotalRechazadasT = 0;
			int sumaSubtotalNoValidadasT = 0;
			// capturando la ultima pagina
			valorFinal = valorInicial + filasMuestra;

			for (int i = 0; i < this.stoListaPedidos.size(); i++) {
				ConsultaPedidos obj = (ConsultaPedidos) this.stoListaPedidos
						.get(i);
				sumaCargasT = sumaCargasT + obj.getCargadas();
				sumaNumGP2T = sumaNumGP2T + obj.getNumGP2();
				sumaNumGP3T = sumaNumGP3T + obj.getNumGP3();
				sumaNumGP4T = sumaNumGP4T + obj.getNumGP4();
				sumaNumGP5T = sumaNumGP5T + obj.getNumGP5();
				sumaCorrectasT = sumaCorrectasT + obj.getCorrectas();
				sumaErradasT = sumaErradasT + obj.getErradas();
				sumaRechazadasT = sumaRechazadasT + obj.getRechazadas();
				sumaNoValidadasT = sumaNoValidadasT + obj.getNoValidadas();
			}

			// validando si se recibe menos de lo paginado
			if (this.stoListaPedidos.size() <= 9) {

				for (int i = 0; i < this.stoListaPedidos.size(); i++) {

					ConsultaPedidos obj = (ConsultaPedidos) this.stoListaPedidos
							.get(i);

					sumaSubtotalCargasT = sumaSubtotalCargasT
							+ obj.getCargadas();
					sumaSubtotalNumGP2T = sumaSubtotalNumGP2T + obj.getNumGP2();
					sumaSubtotalNumGP3T = sumaSubtotalNumGP3T + obj.getNumGP3();
					sumaSubtotalNumGP4T = sumaSubtotalNumGP4T + obj.getNumGP4();
					sumaSubtotalNumGP5T = sumaSubtotalNumGP5T + obj.getNumGP5();
					sumaSubtotalCorrectasT = sumaSubtotalCorrectasT
							+ obj.getCorrectas();
					sumaSubtotalErradasT = sumaSubtotalErradasT
							+ obj.getErradas();
					sumaSubtotalRechazadasT = sumaSubtotalRechazadasT
							+ obj.getRechazadas();
					sumaSubtotalNoValidadasT = sumaSubtotalNoValidadasT
							+ obj.getNoValidadas();
				}
			}
			// si es igual al paginado, calcular subtotal
			else {

				for (int i = valorInicial; i <= valorFinal - 1; i++) {

					ConsultaPedidos obj = (ConsultaPedidos) this.stoListaPedidos
							.get(i);

					sumaSubtotalCargasT = sumaSubtotalCargasT
							+ obj.getCargadas();
					sumaSubtotalNumGP2T = sumaSubtotalNumGP2T + obj.getNumGP2();
					sumaSubtotalNumGP3T = sumaSubtotalNumGP3T + obj.getNumGP3();
					sumaSubtotalNumGP4T = sumaSubtotalNumGP4T + obj.getNumGP4();
					sumaSubtotalNumGP5T = sumaSubtotalNumGP5T + obj.getNumGP5();
					sumaSubtotalCorrectasT = sumaSubtotalCorrectasT
							+ obj.getCorrectas();
					sumaSubtotalErradasT = sumaSubtotalErradasT
							+ obj.getErradas();
					sumaSubtotalRechazadasT = sumaSubtotalRechazadasT
							+ obj.getRechazadas();
					sumaSubtotalNoValidadasT = sumaSubtotalNoValidadasT
							+ obj.getNoValidadas();
				}

			}

			this.sumaSubtotalNumGP2T = sumaSubtotalNumGP2T;
			this.sumaSubtotalNumGP3T = sumaSubtotalNumGP3T;
			this.sumaSubtotalNumGP4T = sumaSubtotalNumGP4T;
			this.sumaSubtotalNumGP5T = sumaSubtotalNumGP5T;
			this.sumaSubtotalCorrectasT = sumaSubtotalCorrectasT;
			this.sumaSubtotalCargasT = sumaSubtotalCargasT;
			this.sumaSubtotalErradasT = sumaSubtotalErradasT;
			this.sumaSubtotalRechazadasT = sumaSubtotalRechazadasT;
			this.sumaSubtotalNoValidadasT = sumaSubtotalNoValidadasT;
			this.sumaNumGP2T = sumaNumGP2T;
			this.sumaNumGP3T = sumaNumGP3T;
			this.sumaNumGP4T = sumaNumGP4T;
			this.sumaNumGP5T = sumaNumGP5T;
			this.sumaCorrectasT = sumaCorrectasT;
			this.sumaCargasT = sumaCargasT;
			this.sumaErradasT = sumaErradasT;
			this.sumaRechazadasT = sumaRechazadasT;
			this.sumaNoValidadasT = sumaNoValidadasT;
		}
		setFilterSearch();
		syncZona();
		this.dmConsultaSTOPedidos = new DataTableModel(consultaValidacionList);
		String ventana = "PF('widgetConsultaSTO').clearFilters()";
		this.getRequestContext().execute(ventana);
		return consultaValidacionList;

	}

	/**
	 * Obteniendo los subtotales.
	 */
	public void calcularSubtotales() {
		int valorPagina = this.pagina + 1;
		int filasMuestra = 10;
		int valorFinal = 0;
		valorFinal = valorPagina * filasMuestra;
		int valorInicial = 0;
		valorInicial = valorFinal - filasMuestra;
		int sumaSubtotalCargasT = 0;
		int sumaSubtotalCorrectasT = 0;
		int sumaSubtotalNumGP2T = 0;
		int sumaSubtotalNumGP3T = 0;
		int sumaSubtotalNumGP4T = 0;
		int sumaSubtotalNumGP5T = 0;
		int sumaSubtotalErradasT = 0;
		int sumaSubtotalRechazadasT = 0;
		int sumaSubtotalNoValidadasT = 0;
		// capturando la ultima pagina
		int ultimapagina = 0;
		boolean ultimaP = false;
		int ultimo = (this.stoListaPedidos.size() / 10) + 1;
		int residuoUltimo = (this.stoListaPedidos.size() % 10) + valorInicial;
		if (valorPagina == ultimo) {
			ultimaP = true;
		}
		if (residuoUltimo == 0) {

		} else {
			ultimapagina = residuoUltimo;
		}

		// Validando que sea la primera pagina
		// validando si se recibe menos de lo paginado
		if (this.stoListaPedidos.size() <= 9) {

			for (int i = 0; i < this.stoListaPedidos.size(); i++) {

				ConsultaPedidos obj = (ConsultaPedidos) this.stoListaPedidos
						.get(i);

				sumaSubtotalCargasT = sumaSubtotalCargasT + obj.getCargadas();
				sumaSubtotalNumGP2T = sumaSubtotalNumGP2T + obj.getNumGP2();
				sumaSubtotalNumGP3T = sumaSubtotalNumGP3T + obj.getNumGP3();
				sumaSubtotalNumGP4T = sumaSubtotalNumGP4T + obj.getNumGP4();
				sumaSubtotalNumGP5T = sumaSubtotalNumGP5T + obj.getNumGP5();
				sumaSubtotalCorrectasT = sumaSubtotalCorrectasT
						+ obj.getCorrectas();
				sumaSubtotalErradasT = sumaSubtotalErradasT + obj.getErradas();
				sumaSubtotalRechazadasT = sumaSubtotalRechazadasT
						+ obj.getRechazadas();
				sumaSubtotalNoValidadasT = sumaSubtotalNoValidadasT
						+ obj.getNoValidadas();
			}
		}

		// validando que sea la ultima pagina, y que tenga residuo
		else if (ultimaP && residuoUltimo != 0) {
			for (int i = valorInicial; i <= residuoUltimo - 1; i++) {
				ConsultaPedidos obj = (ConsultaPedidos) this.stoListaPedidos
						.get(i);
				sumaSubtotalCargasT = sumaSubtotalCargasT + obj.getCargadas();
				sumaSubtotalNumGP2T = sumaSubtotalNumGP2T + obj.getNumGP2();
				sumaSubtotalNumGP3T = sumaSubtotalNumGP3T + obj.getNumGP3();
				sumaSubtotalNumGP4T = sumaSubtotalNumGP4T + obj.getNumGP4();
				sumaSubtotalNumGP5T = sumaSubtotalNumGP5T + obj.getNumGP5();
				sumaSubtotalCorrectasT = sumaSubtotalCorrectasT
						+ obj.getCorrectas();
				sumaSubtotalErradasT = sumaSubtotalErradasT + obj.getErradas();
				sumaSubtotalRechazadasT = sumaSubtotalRechazadasT
						+ obj.getRechazadas();
				sumaSubtotalNoValidadasT = sumaSubtotalNoValidadasT
						+ obj.getNoValidadas();
			}
		}

		// si es igual al pagino, calcular subtotal
		else {

			for (int i = valorInicial; i <= valorFinal - 1; i++) {

				ConsultaPedidos obj = (ConsultaPedidos) this.stoListaPedidos
						.get(i);

				sumaSubtotalCargasT = sumaSubtotalCargasT + obj.getCargadas();
				sumaSubtotalNumGP2T = sumaSubtotalNumGP2T + obj.getNumGP2();
				sumaSubtotalNumGP3T = sumaSubtotalNumGP3T + obj.getNumGP3();
				sumaSubtotalNumGP4T = sumaSubtotalNumGP4T + obj.getNumGP4();
				sumaSubtotalNumGP5T = sumaSubtotalNumGP5T + obj.getNumGP5();
				sumaSubtotalCorrectasT = sumaSubtotalCorrectasT
						+ obj.getCorrectas();
				sumaSubtotalErradasT = sumaSubtotalErradasT + obj.getErradas();
				sumaSubtotalRechazadasT = sumaSubtotalRechazadasT
						+ obj.getRechazadas();
				sumaSubtotalNoValidadasT = sumaSubtotalNoValidadasT
						+ obj.getNoValidadas();
			}

		}

		this.sumaSubtotalNumGP2T = sumaSubtotalNumGP2T;
		this.sumaSubtotalNumGP3T = sumaSubtotalNumGP3T;
		this.sumaSubtotalNumGP4T = sumaSubtotalNumGP4T;
		this.sumaSubtotalNumGP5T = sumaSubtotalNumGP5T;
		this.sumaSubtotalCorrectasT = sumaSubtotalCorrectasT;
		this.sumaSubtotalCargasT = sumaSubtotalCargasT;
		this.sumaSubtotalErradasT = sumaSubtotalErradasT;
		this.sumaSubtotalRechazadasT = sumaSubtotalRechazadasT;
		this.sumaSubtotalNoValidadasT = sumaSubtotalNoValidadasT;
	}

	/**
	 * 
	 */
	public void inicializando() {
		this.siccRegionList = new ArrayList();
		this.regionListSearch = null;
		this.zonaListSearch = null;
		this.siccZonaList = null;
		this.mostrarGrilla = false;
		this.sumaSubtotalNumGP2T = 0;
		this.sumaSubtotalNumGP3T = 0;
		this.sumaSubtotalNumGP4T = 0;
		this.sumaSubtotalNumGP5T = 0;
		this.sumaSubtotalCorrectasT = 0;
		this.sumaSubtotalCargasT = 0;
		this.sumaSubtotalErradasT = 0;
		this.sumaSubtotalRechazadasT = 0;
		this.sumaSubtotalNoValidadasT = 0;
		this.sumaNumGP2T = 0;
		this.sumaNumGP3T = 0;
		this.sumaNumGP4T = 0;
		this.sumaNumGP5T = 0;
		this.sumaCorrectasT = 0;
		this.sumaCargasT = 0;
		this.sumaErradasT = 0;
		this.sumaRechazadasT = 0;
		this.sumaNoValidadasT = 0;

	}

	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("Executing action : setViewAttributes.");
		this.mostrarListaBusqueda = false;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codigoPais = pais.getCodigo();
		inicializando();
		this.guardandoDatosForm();
		// Seteando el Tipo de documento
		ProcesoSTOService procesoSTOService = (ProcesoSTOService) getBean("spusicc.procesoSTOService");
		TipoDocumentoDigitadoPK tipoDocumentoDigitadoPK = new TipoDocumentoDigitadoPK(
				codigoPais, Constants.STO_TIPO_DOCUMENTO_OCC);
		TipoDocumentoDigitado tipoDocumentoDigitado = procesoSTOService
				.getTipoDocumentoDigitado(tipoDocumentoDigitadoPK);

		ConsultaSTOPedidosForm f = (ConsultaSTOPedidosForm) this.formBusqueda;
		f.setTipoDocumento(Constants.STO_TIPO_DOCUMENTO_OCC);
		f.setDescripcionDocumento(tipoDocumentoDigitado.getDesTipoDocu());
		f.setDocumentoIdentidad("");

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoUsuario", usuario.getLogin());
		criteria.put("indicadorPantalla",
				Constants.STO_INDICADOR_PANTALLA_ELIMINACION);

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ProcesoSTOLevantamientoErroresValidacionService procesoSTOLevantamientoErroresValidacionService = (ProcesoSTOLevantamientoErroresValidacionService) getBean("spusicc.procesoSTOLevantamientoErroresValidacionService");

		this.siccRegionList = reporteService.getListaGenerico(
				"getRegionesByPais", criteria);

		criteria.put("codigoParametro", Constants.STO_INTERVALO_CARGA_STO);
		List listaHorasCarga = procesoSTOLevantamientoErroresValidacionService
				.getListaHoras(criteria);
		this.stoHorasCargaList = listaHorasCarga;

		criteria.put("codigoParametro", Constants.STO_INTERVALO_PROCESO_STO);
		List listaHorasProceso = procesoSTOLevantamientoErroresValidacionService
				.getListaHoras(criteria);
		this.stoHorasProcesoList = listaHorasProceso;

		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		this.stoOrigenesList = ajaxService.getOrigenSTOByTipoDocumento(
				codigoPais, Constants.STO_TIPO_DOCUMENTO_OCC);

		this.stoEstadosGestionList = ajaxService.getEstadosSTOByTipoDocumento(
				codigoPais, Constants.STO_TIPO_DOCUMENTO_OCC);

		Date fechaInicio = new Date(System.currentTimeMillis());
		Date fechaFin = new Date(System.currentTimeMillis());
		f.setFechaInicioProcesoD(fechaInicio);

		fechaFin = DateUtil.addToDate(fechaFin, Calendar.DATE, 1);

		f.setFechaFinProcesoD(fechaFin);
		f.setHoraInicioProceso("00:00");
		f.setHoraFinProceso("00:00");
		f.setEstado("01");
		f.setAccion("");
		f.setIndicadorDocumento("TODOS");

		setAgrupacionDefault(f, codigoPais);

		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", pais.getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																		// Campanha
																		// Activa
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																			// Campanha
																			// activa
																			// q
																			// se
																			// procesa
																			// actualmente

		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service
				.getControlFacturacionById(criteriaPeriodo);

		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());

		ClienteUAGenerarService clienteService = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");
		this.longitudCampoClientes = clienteService
				.getTamanhoNumeroCliente(pais.getCodigo());

		this.stoConsultarPedidosClientesList = new ArrayList();
		this.stoResumenClientesList = new ArrayList();

		// topes
		String lineaDefecto = ajaxService.getNumeroRegistrosSTO(codigoPais,
				Constants.STO_TIPO_DOCUMENTO_OCC, "1");
		String lineaMaxima = ajaxService.getNumeroRegistrosSTO(codigoPais,
				Constants.STO_TIPO_DOCUMENTO_OCC, "2");
		f.setLineaDefecto(lineaDefecto);
		f.setLineaMaxima(lineaMaxima);
		this.pagina = 0;
		f.setCodigoCliente("");

	}

	/**
	 * Abre POPUP
	 * 
	 * @param actionEvent
	 */
	public void abrirPopup() {
		try {
			// popupConsultaSTODetallePedidos.setOcrRecepcionPedidosList(this.ocrRecepcionPedidosList);
			this.popupConsultaSTODetallePedidos.inicializarValores();

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
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
	 * @return the agrupacionPeriodo
	 */
	public Boolean getAgrupacionPeriodo() {
		return agrupacionPeriodo;
	}

	/**
	 * @param agrupacionPeriodo
	 *            the agrupacionPeriodo to set
	 */
	public void setAgrupacionPeriodo(Boolean agrupacionPeriodo) {
		this.agrupacionPeriodo = agrupacionPeriodo;
	}

	/**
	 * @return the agrupacionFechaCarga
	 */
	public Boolean getAgrupacionFechaCarga() {
		return agrupacionFechaCarga;
	}

	/**
	 * @param agrupacionFechaCarga
	 *            the agrupacionFechaCarga to set
	 */
	public void setAgrupacionFechaCarga(Boolean agrupacionFechaCarga) {
		this.agrupacionFechaCarga = agrupacionFechaCarga;
	}

	/**
	 * @return the agrupacionFechaProceso
	 */
	public Boolean getAgrupacionFechaProceso() {
		return agrupacionFechaProceso;
	}

	/**
	 * @param agrupacionFechaProceso
	 *            the agrupacionFechaProceso to set
	 */
	public void setAgrupacionFechaProceso(Boolean agrupacionFechaProceso) {
		this.agrupacionFechaProceso = agrupacionFechaProceso;
	}

	/**
	 * @return the agrupacionLote
	 */
	public Boolean getAgrupacionLote() {
		return agrupacionLote;
	}

	/**
	 * @param agrupacionLote
	 *            the agrupacionLote to set
	 */
	public void setAgrupacionLote(Boolean agrupacionLote) {
		this.agrupacionLote = agrupacionLote;
	}

	/**
	 * @return the agrupacionRegion
	 */
	public Boolean getAgrupacionRegion() {
		return agrupacionRegion;
	}

	/**
	 * @param agrupacionRegion
	 *            the agrupacionRegion to set
	 */
	public void setAgrupacionRegion(Boolean agrupacionRegion) {
		this.agrupacionRegion = agrupacionRegion;
	}

	/**
	 * @return the agrupacionZona
	 */
	public Boolean getAgrupacionZona() {
		return agrupacionZona;
	}

	/**
	 * @param agrupacionZona
	 *            the agrupacionZona to set
	 */
	public void setAgrupacionZona(Boolean agrupacionZona) {
		this.agrupacionZona = agrupacionZona;
	}

	/**
	 * @return the agrupacionCliente
	 */
	public Boolean getAgrupacionCliente() {
		return agrupacionCliente;
	}

	/**
	 * @param agrupacionCliente
	 *            the agrupacionCliente to set
	 */
	public void setAgrupacionCliente(Boolean agrupacionCliente) {
		this.agrupacionCliente = agrupacionCliente;
	}

	/**
	 * @return the agrupacionOrigen
	 */
	public Boolean getAgrupacionOrigen() {
		return agrupacionOrigen;
	}

	/**
	 * @param agrupacionOrigen
	 *            the agrupacionOrigen to set
	 */
	public void setAgrupacionOrigen(Boolean agrupacionOrigen) {
		this.agrupacionOrigen = agrupacionOrigen;
	}

	/**
	 * @return the agrupacionFechaProgFacturacion
	 */
	public Boolean getAgrupacionFechaProgFacturacion() {
		return agrupacionFechaProgFacturacion;
	}

	/**
	 * @param agrupacionFechaProgFacturacion
	 *            the agrupacionFechaProgFacturacion to set
	 */
	public void setAgrupacionFechaProgFacturacion(
			Boolean agrupacionFechaProgFacturacion) {
		this.agrupacionFechaProgFacturacion = agrupacionFechaProgFacturacion;
	}

	/**
	 * @return the sumaCargas
	 */
	public Integer getSumaCargas() {
		return sumaCargas;
	}

	/**
	 * @param sumaCargas
	 *            the sumaCargas to set
	 */
	public void setSumaCargas(Integer sumaCargas) {
		this.sumaCargas = sumaCargas;
	}

	/**
	 * @return the sumaEnviadas
	 */
	public Integer getSumaEnviadas() {
		return sumaEnviadas;
	}

	/**
	 * @param sumaEnviadas
	 *            the sumaEnviadas to set
	 */
	public void setSumaEnviadas(Integer sumaEnviadas) {
		this.sumaEnviadas = sumaEnviadas;
	}

	/**
	 * @return the sumaErradas
	 */
	public Integer getSumaErradas() {
		return sumaErradas;
	}

	/**
	 * @param sumaErradas
	 *            the sumaErradas to set
	 */
	public void setSumaErradas(Integer sumaErradas) {
		this.sumaErradas = sumaErradas;
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
	 * @return the beanConsultaSTOPedidos
	 */
	public Object[] getBeanConsultaSTOPedidos() {
		return beanConsultaSTOPedidos;
	}

	/**
	 * @param beanConsultaSTOPedidos
	 *            the beanConsultaSTOPedidos to set
	 */
	public void setBeanConsultaSTOPedidos(Object[] beanConsultaSTOPedidos) {
		this.beanConsultaSTOPedidos = beanConsultaSTOPedidos;
	}

	/**
	 * @return the stoResumenList
	 */
	public List getStoResumenList() {
		return stoResumenList;
	}

	/**
	 * @param stoResumenList
	 *            the stoResumenList to set
	 */
	public void setStoResumenList(List stoResumenList) {
		this.stoResumenList = stoResumenList;
	}

	/**
	 * @return the stoOrigenesList
	 */
	public LabelValue[] getStoOrigenesList() {
		return stoOrigenesList;
	}

	/**
	 * @param stoOrigenesList
	 *            the stoOrigenesList to set
	 */
	public void setStoOrigenesList(LabelValue[] stoOrigenesList) {
		this.stoOrigenesList = stoOrigenesList;
	}

	/**
	 * @return the sumaNumGP2T
	 */
	public Integer getSumaNumGP2T() {
		return sumaNumGP2T;
	}

	/**
	 * @param sumaNumGP2T
	 *            the sumaNumGP2T to set
	 */
	public void setSumaNumGP2T(Integer sumaNumGP2T) {
		this.sumaNumGP2T = sumaNumGP2T;
	}

	/**
	 * @return the sumaSubtotalNoValidadasT
	 */
	public Integer getSumaSubtotalNoValidadasT() {
		return sumaSubtotalNoValidadasT;
	}

	/**
	 * @param sumaSubtotalNoValidadasT
	 *            the sumaSubtotalNoValidadasT to set
	 */
	public void setSumaSubtotalNoValidadasT(Integer sumaSubtotalNoValidadasT) {
		this.sumaSubtotalNoValidadasT = sumaSubtotalNoValidadasT;
	}

	/**
	 * @return the sumaSubtotalNumGP5T
	 */
	public Integer getSumaSubtotalNumGP5T() {
		return sumaSubtotalNumGP5T;
	}

	/**
	 * @param sumaSubtotalNumGP5T
	 *            the sumaSubtotalNumGP5T to set
	 */
	public void setSumaSubtotalNumGP5T(Integer sumaSubtotalNumGP5T) {
		this.sumaSubtotalNumGP5T = sumaSubtotalNumGP5T;
	}

	/**
	 * @return the sumaSubtotalNumGP4T
	 */
	public Integer getSumaSubtotalNumGP4T() {
		return sumaSubtotalNumGP4T;
	}

	/**
	 * @param sumaSubtotalNumGP4T
	 *            the sumaSubtotalNumGP4T to set
	 */
	public void setSumaSubtotalNumGP4T(Integer sumaSubtotalNumGP4T) {
		this.sumaSubtotalNumGP4T = sumaSubtotalNumGP4T;
	}

	/**
	 * @return the sumaSubtotalNumGP3T
	 */
	public Integer getSumaSubtotalNumGP3T() {
		return sumaSubtotalNumGP3T;
	}

	/**
	 * @param sumaSubtotalNumGP3T
	 *            the sumaSubtotalNumGP3T to set
	 */
	public void setSumaSubtotalNumGP3T(Integer sumaSubtotalNumGP3T) {
		this.sumaSubtotalNumGP3T = sumaSubtotalNumGP3T;
	}

	/**
	 * @return the sumaSubtotalNumGP2T
	 */
	public Integer getSumaSubtotalNumGP2T() {
		return sumaSubtotalNumGP2T;
	}

	/**
	 * @param sumaSubtotalNumGP2T
	 *            the sumaSubtotalNumGP2T to set
	 */
	public void setSumaSubtotalNumGP2T(Integer sumaSubtotalNumGP2T) {
		this.sumaSubtotalNumGP2T = sumaSubtotalNumGP2T;
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
	 * @return the sumaNumGP3T
	 */
	public Integer getSumaNumGP3T() {
		return sumaNumGP3T;
	}

	/**
	 * @param sumaNumGP3T
	 *            the sumaNumGP3T to set
	 */
	public void setSumaNumGP3T(Integer sumaNumGP3T) {
		this.sumaNumGP3T = sumaNumGP3T;
	}

	/**
	 * @return the sumaNumGP4T
	 */
	public Integer getSumaNumGP4T() {
		return sumaNumGP4T;
	}

	/**
	 * @param sumaNumGP4T
	 *            the sumaNumGP4T to set
	 */
	public void setSumaNumGP4T(Integer sumaNumGP4T) {
		this.sumaNumGP4T = sumaNumGP4T;
	}

	/**
	 * @return the sumaNumGP5T
	 */
	public Integer getSumaNumGP5T() {
		return sumaNumGP5T;
	}

	/**
	 * @param sumaNumGP5T
	 *            the sumaNumGP5T to set
	 */
	public void setSumaNumGP5T(Integer sumaNumGP5T) {
		this.sumaNumGP5T = sumaNumGP5T;
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
	 * @return the sumaNoValidadasT
	 */
	public Integer getSumaNoValidadasT() {
		return sumaNoValidadasT;
	}

	/**
	 * @param sumaNoValidadasT
	 *            the sumaNoValidadasT to set
	 */
	public void setSumaNoValidadasT(Integer sumaNoValidadasT) {
		this.sumaNoValidadasT = sumaNoValidadasT;
	}

	/**
	 * @return the mostrarComboAccion
	 */
	public Boolean getMostrarComboAccion() {
		return mostrarComboAccion;
	}

	/**
	 * @param mostrarComboAccion
	 *            the mostrarComboAccion to set
	 */
	public void setMostrarComboAccion(Boolean mostrarComboAccion) {
		this.mostrarComboAccion = mostrarComboAccion;
	}

	/**
	 * @return the listaCliente
	 */
	public List getListaCliente() {
		return listaCliente;
	}

	/**
	 * @param listaCliente
	 *            the listaCliente to set
	 */
	public void setListaCliente(List listaCliente) {
		this.listaCliente = listaCliente;
	}

	/**
	 * @return the numeroSecuenciaConsulta
	 */
	public String getNumeroSecuenciaConsulta() {
		return numeroSecuenciaConsulta;
	}

	/**
	 * @param numeroSecuenciaConsulta
	 *            the numeroSecuenciaConsulta to set
	 */
	public void setNumeroSecuenciaConsulta(String numeroSecuenciaConsulta) {
		this.numeroSecuenciaConsulta = numeroSecuenciaConsulta;
	}

	/**
	 * @return the stoConsultarPedidosClientesList
	 */
	public List getStoConsultarPedidosClientesList() {
		return stoConsultarPedidosClientesList;
	}

	/**
	 * @param stoConsultarPedidosClientesList
	 *            the stoConsultarPedidosClientesList to set
	 */
	public void setStoConsultarPedidosClientesList(
			List stoConsultarPedidosClientesList) {
		this.stoConsultarPedidosClientesList = stoConsultarPedidosClientesList;
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
	 * @return the popupConsultaSTODetallePedidos
	 */
	public ConsultaSTODetallePedidosAction getPopupConsultaSTODetallePedidos() {
		return popupConsultaSTODetallePedidos;
	}

	/**
	 * @param popupConsultaSTODetallePedidos
	 *            the popupConsultaSTODetallePedidos to set
	 */
	public void setPopupConsultaSTODetallePedidos(
			ConsultaSTODetallePedidosAction popupConsultaSTODetallePedidos) {
		this.popupConsultaSTODetallePedidos = popupConsultaSTODetallePedidos;
	}

	/**
	 * @return the dmConsultaSTOPedidos
	 */
	public DataTableModel getDmConsultaSTOPedidos() {
		return dmConsultaSTOPedidos;
	}

	/**
	 * @param dmConsultaSTOPedidos
	 *            the dmConsultaSTOPedidos to set
	 */
	public void setDmConsultaSTOPedidos(DataTableModel dmConsultaSTOPedidos) {
		this.dmConsultaSTOPedidos = dmConsultaSTOPedidos;
	}

}