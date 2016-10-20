package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import biz.belcorp.ssicc.dao.spusicc.sto.model.ConsultaPedidosGP1;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitado;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitadoPK;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.MantenimientoPRECambioCodigoVentaService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECIngresoAtencionesService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.sto.form.ProcesoSTORevertirPedidosGP1Form;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProcesoSTORevertirPedidosGP1Action extends
		BaseProcesoAbstractAction {

	private static final long serialVersionUID = 1L;
	// private String oid = "";

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
	private Object[] beanProcesoSTORevertirPedidosGP1;
	private List stoResumenClientesList;
	private List listaCliente;
	private Boolean estadoEjecutar;

	/**
	 * Guarda los filtros seleccionados
	 * 
	 * @param f
	 * @param request
	 */
	private void setFilterSearch() {
		ProcesoSTORevertirPedidosGP1Form f = (ProcesoSTORevertirPedidosGP1Form) this.formProceso;

		f.setSelectedItemsSearch(f.getSelectedItems());
		f.setTipoDocumentoSearch(f.getTipoDocumento());
		f.setCodigoPeriodoSearch(f.getCodigoPeriodo());
		f.setFechaInicioProgramadaFacturacionSearch(f
				.getFechaInicioProgramadaFacturacion());
		f.setFechaFinProgramadaFacturacionSearch(f
				.getFechaFinProgramadaFacturacion());
		f.setCodigoClienteSearch(f.getCodigoCliente());
		f.setAccionSearch(f.getAccion());

		this.regionListSearch = f.getRegionList();
		this.zonaListSearch = f.getZonaList();
	}

	/**
	 * Metodo Para cargar archivo
	 * 
	 * @param event
	 * @throws Exception
	 */
	public void handleFileUpload(FileUploadEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("handleFileUpload");
		}
		try {
			ProcesoSTORevertirPedidosGP1Form f = (ProcesoSTORevertirPedidosGP1Form) this.formProceso;
			if (event != null) {
				// f.setArchivo(event.getFile());
				f.setClienteFile(event.getFile());
				this.setAttachment(event.getFile().getFileName());
				this.loadfile();
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * Mostrar zonas respecto a la region
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
		this.inicializar();
		Map params = null;
		ProcesoSTORevertirPedidosGP1Form f = (ProcesoSTORevertirPedidosGP1Form) this.formProceso;
		f.setFechaInicioProgramadaFacturacion(DateUtil.convertDateToString(f
				.getFechaInicioProgramadaFacturacionD()));
		f.setFechaFinProgramadaFacturacion(DateUtil.convertDateToString(f
				.getFechaFinProgramadaFacturacionD()));

		String codigoPais = f.getCodigoPais();

		ProcesoSTOService procesoSTOService = (ProcesoSTOService) getBean("spusicc.procesoSTOService");
		TipoDocumentoDigitadoPK tipoDocumentoDigitadoPK = new TipoDocumentoDigitadoPK(
				codigoPais, Constants.STO_TIPO_DOCUMENTO_OCC);
		TipoDocumentoDigitado tipoDocumentoDigitado = procesoSTOService
				.getTipoDocumentoDigitado(tipoDocumentoDigitadoPK);

		f.setDescripcionDocumento(tipoDocumentoDigitado.getDesTipoDocu());

		params = getCriteria();

		List consultaPedidosList = procesoSTOService
				.getConsultaPedidosGP1List(params);
		this.stoListaPedidos = consultaPedidosList;
		int tamanio = this.stoListaPedidos.size();
		if (tamanio > 0) {
			this.mostrarDatatable = true;
			this.mostrarPanelAdicionalProceso = false;
			///this.mostrarBotonExecute = true;
			this.setEstadoEjecutar(true);
			this.mostrarBotonBuscar = true;
			int sumaCorrectasT = 0;
			int sumaGP2T = 0;
			int sumaGP3T = 0;

			// ConsultaPolizas obj
			for (int i = 0; i < consultaPedidosList.size(); i++) {
				ConsultaPedidosGP1 obj = (ConsultaPedidosGP1) consultaPedidosList
						.get(i);
				sumaCorrectasT = sumaCorrectasT + obj.getCorrectas();
				sumaGP2T = sumaGP2T + obj.getNumGP2();
				sumaGP3T = sumaGP3T + obj.getNumGP3();
			}

			int valorPagina = 1;
			int filasMuestra = new Integer(this.cantidadRowsDefaultDatatable).intValue();
			int valorFinal = 0;
			valorFinal = valorPagina * filasMuestra;
			int valorInicial = 0;
			int sumaSubtotalCorrectas = 0;
			int sumaSubtotalGP2 = 0;
			int sumaSubtotalGP3 = 0;

			// validando si se recibe menos de lo paginado
			if (this.stoListaPedidos.size() <= 9) {
				for (int i = valorInicial; i < this.stoListaPedidos.size(); i++) {
					ConsultaPedidosGP1 obj = (ConsultaPedidosGP1) this.stoListaPedidos
							.get(i);
					sumaSubtotalCorrectas = sumaSubtotalCorrectas
							+ obj.getCorrectas();
					sumaSubtotalGP2 = sumaSubtotalGP2 + obj.getNumGP2();
					sumaSubtotalGP3 = sumaSubtotalGP3 + obj.getNumGP3();
				}
			} else {
				for (int i = valorInicial; i <= valorFinal - 1; i++) {
					if (tamanio <= i) {
						break;
					}
					ConsultaPedidosGP1 obj = (ConsultaPedidosGP1) this.stoListaPedidos
							.get(i);
					sumaSubtotalCorrectas = sumaSubtotalCorrectas
							+ obj.getCorrectas();
					sumaSubtotalGP2 = sumaSubtotalGP2 + obj.getNumGP2();
					sumaSubtotalGP3 = sumaSubtotalGP3 + obj.getNumGP3();
				}
			}

			this.sumaSubtotalCorrectasT = sumaSubtotalCorrectas;
			this.sumaSubtotalGP2T = sumaSubtotalGP2;
			this.sumaSubtotalGP3T = sumaSubtotalGP3;

			this.sumaCorrectasT = sumaCorrectasT;
			this.sumaGP2T = sumaGP2T;
			this.sumaGP3T = sumaGP3T;

			setFilterSearch();
			f.setCargaCombo(Constants.NO);
			syncZona();
		}

		return consultaPedidosList;
	}

	/**
	 * Calcula los subtotales
	 */
	public void calcularSubtotales() {
		int valorPagina = this.pagina + 1;
		int filasMuestra = new Integer(this.cantidadRowsDefaultDatatable).intValue();
		int valorFinal = 0;
		valorFinal = valorPagina * filasMuestra;

		int valorInicial = 0;
		valorInicial = valorFinal - filasMuestra;
		int sumaSubtotalCorrectas = 0;
		int sumaSubtotalGP2 = 0;
		int sumaSubtotalGP3 = 0;
		int tamanio = this.stoListaPedidos.size();
		int ultimaPagina = (tamanio / filasMuestra) + 1;
		int paginaFinal = tamanio % filasMuestra;

		int residuo = 0;
		if (paginaFinal > 0) {
			residuo = valorInicial + paginaFinal;
		}
		int valorPaginaFinal = valorPagina;

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

		if (this.stoListaPedidos.size() <= 9) {

			for (int i = 0; i <= this.stoListaPedidos.size(); i++) {

				ConsultaPedidosGP1 obj = (ConsultaPedidosGP1) this.stoListaPedidos
						.get(i);
				sumaSubtotalCorrectas = sumaSubtotalCorrectas
						+ obj.getCorrectas();
				sumaSubtotalGP2 = sumaSubtotalGP2 + obj.getNumGP2();
				sumaSubtotalGP3 = sumaSubtotalGP3 + obj.getNumGP3();

			}
		}
		// validando que sea la ultima pagina, y que tenga residuo
		else if (ultimaP && residuoUltimo != 0) {
			for (int i = valorInicial; i <= residuoUltimo - 1; i++) {
				ConsultaPedidosGP1 obj = (ConsultaPedidosGP1) this.stoListaPedidos
						.get(i);
				sumaSubtotalCorrectas = sumaSubtotalCorrectas
						+ obj.getCorrectas();
				sumaSubtotalGP2 = sumaSubtotalGP2 + obj.getNumGP2();
				sumaSubtotalGP3 = sumaSubtotalGP3 + obj.getNumGP3();
			}
		}

		// si es una lista normal con 10 registro entrara aca.
		else {
			for (int i = valorInicial; i <= valorFinal - 1; i++) {
				ConsultaPedidosGP1 obj = (ConsultaPedidosGP1) this.stoListaPedidos
						.get(i);
				sumaSubtotalCorrectas = sumaSubtotalCorrectas
						+ obj.getCorrectas();
				sumaSubtotalGP2 = sumaSubtotalGP2 + obj.getNumGP2();
				sumaSubtotalGP3 = sumaSubtotalGP3 + obj.getNumGP3();
			}
		}

		this.sumaSubtotalCorrectasT = sumaSubtotalCorrectas;
		this.sumaSubtotalGP2T = sumaSubtotalGP2;
		this.sumaSubtotalGP3T = sumaSubtotalGP3;

	}

	/**
	 * Metodo que captura la pagina seleccionada
	 * 
	 * @param e
	 */
	public void onPage(PageEvent e) {
		int paginas = e.getPage();
		this.pagina = paginas;
		calcularSubtotales();
	}

	/**
	 * Obtiene la Zona
	 * 
	 * @param request
	 * @param cabecera
	 * 
	 */
	private void syncZona() {
		ProcesoSTORevertirPedidosGP1Form cabecera = (ProcesoSTORevertirPedidosGP1Form) this.formProceso;
		String[] codigoRegion = cabecera.getRegionList();
		if (cabecera.getRegionList() != null) {
			LabelValue[] resultZona = this.getZonaByRegion(
					cabecera.getCodigoPais(), codigoRegion);
			this.siccZonaList = resultZona;
		}
	}

	/**
	 * Obtiene la Zona por Region
	 * 
	 * @param codigoPais
	 * @param codigoEmpresa
	 * @param codigoRegion
	 * @return
	 * 
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
	 * Metodo para devolver la posicion del registro en el display table de la
	 * pantalla
	 * 
	 * @param posicion
	 * @return
	 * 
	 */
	public int devuelvePosicion(String posicion) {
		int posi = 0;
		int aux = posicion.indexOf('|');
		String id2 = posicion.substring(0, aux);
		posi = Integer.parseInt(id2);

		return posi;
	}

	/**
	 * Devuelve el filtro con los criterios de busqueda
	 * 
	 * @param f
	 * @param request
	 * @return
	 * 
	 */
	protected Map getCriteria() {

		ProcesoSTORevertirPedidosGP1Form f = (ProcesoSTORevertirPedidosGP1Form) this.formProceso;
		Map params = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		String codigoPeriodo = f.getCodigoPeriodo();
		String fechaInicioProgramadaFacturacion = f
				.getFechaInicioProgramadaFacturacion();
		String fechaFinProgramadaFacturacion = f
				.getFechaFinProgramadaFacturacion();
		String codigoCliente = f.getCodigoCliente();

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

		String grupoProceso = f.getGrupoProceso();
		String estadoGP1 = "";
		String estadoGP2 = "";
		String estadoGP3 = "";

		if (grupoProceso.equals(Constants.NUMERO_UNO))
			estadoGP1 = Constants.SI;
		if (grupoProceso.equals(Constants.NUMERO_DOS))
			estadoGP2 = Constants.SI;
		if (grupoProceso.equals("3"))
			estadoGP3 = Constants.SI;

		try {
			params = BeanUtils.describe(f);
			params.put("codigoPais", pais.getCodigo());
			params.put("tipoDocumento", f.getTipoDocumento());
			params.put("codigoPeriodo", codigoPeriodo);
			params.put("fechaInicioProgramadaFacturacion",
					fechaInicioProgramadaFacturacion);
			params.put("fechaFinProgramadaFacturacion",
					fechaFinProgramadaFacturacion);
			params.put("codigoCliente", codigoCliente);
			params.put("estadoGP1", estadoGP1);
			params.put("estadoGP2", estadoGP2);
			params.put("estadoGP3", estadoGP3);
			params.put(
					"regionList",
					(f.getRegionList() == null) ? new ArrayList() : Arrays
							.asList(f.getRegionList()));
			params.put("zonaList", (f.getZonaList() == null) ? new ArrayList()
					: Arrays.asList(f.getZonaList()));

			// ----------------------------------------------
			String[] arrlistaClientes = new String[0];
			List clienteList = new ArrayList();
			Long longitudPais = pais.getLongitudCodigoCliente();
			String codigoCliente2 = f.getCodigoCliente();
			String codigoCliente3 = f.getCodigoCliente();
			List listaClientes = this.stoEliminarPedidosClientesList;

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

	/**
	 * Carga el archivo de Clientes
	 * 
	 * @param mapping
	 * @param form
	 * @param
	 * @param response
	 * @return
	 * @throws Exception
	 * 
	 */
	public void loadfile() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'load Clientes from file' method");
		}

		ProcesoSTORevertirPedidosGP1Form f = (ProcesoSTORevertirPedidosGP1Form) this.formProceso;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
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

		this.stoEliminarPedidosClientesList = list;
		this.stoResumenClientesList = list2;
		if (this.stoResumenClientesList != null
				&& stoEliminarPedidosClientesList != null)
			this.mostrarGrilla = true;
	}

	/**
	 * Limpia el archivo de Clientes
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * 
	 */
	public void clearFile() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'clearFile' method");
		}
		ProcesoSTORevertirPedidosGP1Form f = (ProcesoSTORevertirPedidosGP1Form) this.formProceso;
		f.setTipoDocumento(Constants.STO_TIPO_DOCUMENTO_OCC);
		this.stoEliminarPedidosClientesList = new ArrayList();
		this.stoResumenClientesList = new ArrayList();
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
		ProcesoSTORevertirPedidosGP1Form form = new ProcesoSTORevertirPedidosGP1Form();
		return form;
	}
	public void validarAccion(ActionEvent evt) {
		ProcesoSTORevertirPedidosGP1Form f = (ProcesoSTORevertirPedidosGP1Form) this.formProceso;
		if(StringUtils.isBlank(f.getAccion()))
		{
			addInfo("Mensaje", getResourceMessage("procesoSTORevertirPedidosGP1Form.seleccionarAccion"));
			return;
		}
		executeProceso();
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
		ProcesoSTORevertirPedidosGP1Form f = (ProcesoSTORevertirPedidosGP1Form) this.formProceso;
		this.listaCliente = new ArrayList();
		params.put("codigoSistema", Constants.STO_CODIGO_SISTEMA);
		params.put("codigoProcesoBatch", Constants.STO_PROC_BATC_OCC);
		int tamanio = beanProcesoSTORevertirPedidosGP1.length;
		if (!f.getAccion().equals("T")) {
			for (int i = 0; i < tamanio; i++) {
				ConsultaPedidosGP1 form = (ConsultaPedidosGP1) beanProcesoSTORevertirPedidosGP1[i];
				this.listaCliente.add(form);
			}
		} else {
			this.listaCliente = this.stoListaPedidos;
		}
		params.put("codigoAccionEjecutada", Constants.STO_INDICADOR_ELIMINACION
				+ f.getAccion());
		params.put("codTipoDocu", f.getTipoDocumento());
		ProcesoSTOService procesoSTOService = (ProcesoSTOService) getBean("spusicc.procesoSTOService");

		procesoSTOService.reversarPedidosGP1(this.listaCliente);
		this.setFindAttributes();
		return params;
	}

	/**
	 * Inicializando valores
	 */
	public void inicializar() {
		this.mostrarGrilla = false;
		this.sumaCorrectasT = 0;
		this.sumaGP2T = 0;
		this.sumaGP3T = 0;
		this.sumaSubtotalCorrectasT = 0;
		this.sumaSubtotalGP2T = 0;
		this.sumaSubtotalGP3T = 0;
		this.mostrarBotonExecute = false;
		this.mostrarBotonBuscar = true;
		this.mostrarPaginacionDatatableSpinner = false;
		this.nroPaginacionDatatable = 25;
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
		ProcesoSTORevertirPedidosGP1Form f = (ProcesoSTORevertirPedidosGP1Form) this.formProceso;

		if (f.getFechaInicioProgramadaFacturacionD() == null
				&& f.getFechaFinProgramadaFacturacionD() == null) {
			return null;
		}
		if (f.getFechaInicioProgramadaFacturacionD() == null) {
			return null;
		}

		if (f.getFechaFinProgramadaFacturacionD() == null) {
			return null;
		}

		else {
			if (!validarFechas(f.getFechaInicioProgramadaFacturacionD(),
					f.getFechaFinProgramadaFacturacionD())) {
				return this.getResourceMessage("errors.compare.dates");
			}
		}
		return null;
	}

	/**
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("Executing action : setViewAttributes.");
		inicializar();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String codigoPais = pais.getCodigo();
		this.mostrarPaginacionDatatableSpinner = false;
		this.nroPaginacionDatatable = 25;
		this.mostrarPanelAdicionalProceso = false;
		this.limpiarFindDatatable = true;
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoPRECambioCodigoVentaService service = (MantenimientoPRECambioCodigoVentaService) getBean("spusicc.mantenimientoPRECambioCodigoVentaService");

		// Seteando el Tipo de documento
		ProcesoSTOService procesoSTOService = (ProcesoSTOService) getBean("spusicc.procesoSTOService");
		TipoDocumentoDigitadoPK tipoDocumentoDigitadoPK = new TipoDocumentoDigitadoPK(
				codigoPais, Constants.STO_TIPO_DOCUMENTO_OCC);
		TipoDocumentoDigitado tipoDocumentoDigitado = procesoSTOService
				.getTipoDocumentoDigitado(tipoDocumentoDigitadoPK);

		ProcesoSTORevertirPedidosGP1Form f = (ProcesoSTORevertirPedidosGP1Form) this.formProceso;

		f.setTipoDocumento(Constants.STO_TIPO_DOCUMENTO_OCC);
		f.setDescripcionDocumento(tipoDocumentoDigitado.getDesTipoDocu());

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoUsuario", usuario.getLogin());

		siccRegionList = reporteService.getListaGenerico("getRegionesByPais",
				criteria);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		f.setFechaInicioProgramadaFacturacionD(new Date());
		f.setFechaFinProgramadaFacturacionD(new Date());
		f.setAccion("");
		f.setRegionList(new String[1]);
		f.setZonaList(new String[1]);
		f.setRegionListSearch(new String[1]);
		f.setZonaListSearch(new String[1]);
		f.setCargaCombo(Constants.SI);
		f.setCodigoPeriodo(service.getPeriodoActivo(criteria));

		ClienteUAGenerarService clienteService = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");
		this.longitudCampoClientes = clienteService
				.getTamanhoNumeroCliente(codigoPais);

		// topes
		String lineaDefecto = ajaxService.getNumeroRegistrosSTO(codigoPais,
				Constants.STO_TIPO_DOCUMENTO_OCC, "1");
		String lineaMaxima = ajaxService.getNumeroRegistrosSTO(codigoPais,
				Constants.STO_TIPO_DOCUMENTO_OCC, "2");
		f.setLineaDefecto(lineaDefecto);
		f.setLineaMaxima(lineaMaxima);
		f.setCodigoPais(pais.getCodigo());
		this.mostrarBotonExecute=false;
		this.setEstadoEjecutar(false);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setLimpiarFind()
	 */
	@Override
	public void setLimpiarFind() {
		this.stoListaPedidos = new ArrayList();
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
	 * @return the sumaCorrectasT
	 */
	public Integer getsumaCorrectasT() {
		return sumaCorrectasT;
	}

	/**
	 * @param sumaCorrectasT
	 *            the sumaCorrectasT to set
	 */
	public void setsumaCorrectasT(Integer sumaCorrectasT) {
		this.sumaCorrectasT = sumaCorrectasT;
	}

	/**
	 * @return the sumaGP2T
	 */
	public Integer getsumaGP2T() {
		return sumaGP2T;
	}

	/**
	 * @param sumaGP2T
	 *            the sumaGP2T to set
	 */
	public void setsumaGP2T(Integer sumaGP2T) {
		this.sumaGP2T = sumaGP2T;
	}

	/**
	 * @return the sumaGP3T
	 */
	public Integer getsumaGP3T() {
		return sumaGP3T;
	}

	/**
	 * @param sumaGP3T
	 *            the sumaGP3T to set
	 */
	public void setsumaGP3T(Integer sumaGP3T) {
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
	public Integer getsumaSubtotalCorrectasT() {
		return sumaSubtotalCorrectasT;
	}

	/**
	 * @param sumaSubtotalCorrectasT
	 *            the sumaSubtotalCorrectasT to set
	 */
	public void setsumaSubtotalCorrectasT(Integer sumaSubtotalCorrectasT) {
		this.sumaSubtotalCorrectasT = sumaSubtotalCorrectasT;
	}

	/**
	 * @return the sumaSubtotalGP2T
	 */
	public Integer getsumaSubtotalGP2T() {
		return sumaSubtotalGP2T;
	}

	/**
	 * @param sumaSubtotalGP2T
	 *            the sumaSubtotalGP2T to set
	 */
	public void setsumaSubtotalGP2T(Integer sumaSubtotalGP2T) {
		this.sumaSubtotalGP2T = sumaSubtotalGP2T;
	}

	/**
	 * @return the sumaSubtotalGP3T
	 */
	public Integer getsumaSubtotalGP3T() {
		return sumaSubtotalGP3T;
	}

	/**
	 * @param sumaSubtotalGP3T
	 *            the sumaSubtotalGP3T to set
	 */
	public void setsumaSubtotalGP3T(Integer sumaSubtotalGP3T) {
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
	 * @return the beanProcesoSTORevertirPedidosGP1
	 */
	public Object[] getBeanProcesoSTORevertirPedidosGP1() {
		return beanProcesoSTORevertirPedidosGP1;
	}

	/**
	 * @param beanProcesoSTORevertirPedidosGP1
	 *            the beanProcesoSTORevertirPedidosGP1 to set
	 */
	public void setBeanProcesoSTORevertirPedidosGP1(
			Object[] beanProcesoSTORevertirPedidosGP1) {
		this.beanProcesoSTORevertirPedidosGP1 = beanProcesoSTORevertirPedidosGP1;
	}

	/**
	 * @return the estadoEjecutar
	 */
	public Boolean getEstadoEjecutar() {
		return estadoEjecutar;
	}

	/**
	 * @param estadoEjecutar
	 *            the estadoEjecutar to set
	 */
	public void setEstadoEjecutar(Boolean estadoEjecutar) {
		this.estadoEjecutar = estadoEjecutar;
	}

	
}