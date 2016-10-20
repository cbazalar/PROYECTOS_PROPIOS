package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
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
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.sisicc.model.ProcesoBatchActu;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.spusicc.sto.model.AccionTipoDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.ConsultaPolizas;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitado;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitadoPK;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.PaisService;
import biz.belcorp.ssicc.service.scsicc.ProcesoBatchService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOLevantamientoErroresValidacionService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.ProcesoSTOExecutionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.sto.form.ProcesoSTOEliminarPolizasFamiliaSeguraForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProcesoSTOEliminarPolizasFamiliaSeguraAction extends
		BaseProcesoAbstractAction {

	private static final long serialVersionUID = 5128348614978506692L;
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
	private Object[] beanProcesoSTOEliminarPolizasFamiliaSegura;
	private Integer valorOriginal;
	private DataTableModel dmSTOEliminarPolizas;

	/**
	 * Evento donde se carga el archivo
	 * 
	 * @param event
	 * @throws Exception
	 */
	public void handleFileUpload(FileUploadEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("handleFileUpload");
		}
		String mensaje = "";
		this.attachment = "";
		try {
			ProcesoSTOEliminarPolizasFamiliaSeguraForm f = (ProcesoSTOEliminarPolizasFamiliaSeguraForm) this.formProceso;
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
	 * @param f
	 *            Setea los checkbox de agrupacion marcados por defecto
	 */
	public void setAgrupacionDefault() {
		ProcesoSTOEliminarPolizasFamiliaSeguraForm f = (ProcesoSTOEliminarPolizasFamiliaSeguraForm) this.formProceso;
		String valor = Constants.SI;
		if (valor == "S") {
			this.setAgrupacionPeriodo(true);
			this.setAgrupacionFechaCarga(true);
			this.setAgrupacionFechaProceso(true);
			this.setAgrupacionLote(true);
			this.setAgrupacionRegion(true);
			this.setAgrupacionZona(true);
			this.setAgrupacionCliente(true);
			this.sumaCargas = 0;

		}
		f.setAgrupacionPeriodo(valor);
		f.setAgrupacionFechaCarga(valor);
		f.setAgrupacionFechaProceso(valor);
		f.setAgrupacionLote(valor);
		f.setAgrupacionRegion(valor);
		f.setAgrupacionZona(valor);
		f.setAgrupacionCliente(valor);

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#setValidarProceso()
	 */
	@Override
	public String setValidarProceso() {
		ProcesoSTOEliminarPolizasFamiliaSeguraForm f = (ProcesoSTOEliminarPolizasFamiliaSeguraForm) this.formProceso;
		String accion = f.getAccion();
		String mensaje = null;
		if (StringUtils.isEmpty(accion)) {
			mensaje = "Debe de escoger una accion";
			return mensaje;

		}
		return super.setValidarProceso();
	}

	/**
	 * Valida Combos
	 */
	public void validarCambiosCheckBos() {
		ProcesoSTOEliminarPolizasFamiliaSeguraForm f = (ProcesoSTOEliminarPolizasFamiliaSeguraForm) this.formProceso;

		if (this.getAgrupacionPeriodo() == false) {
			f.setAgrupacionPeriodo("N");
			f.setAgrupacionPeriodoSearch("N");
		} else {
			f.setAgrupacionPeriodo("S");
		}
		if (this.getAgrupacionFechaCarga() == false) {
			f.setAgrupacionFechaCarga("N");
			f.setAgrupacionFechaCargaSearch("N");
		} else {
			f.setAgrupacionFechaCarga("S");
		}
		if (this.getAgrupacionFechaProceso() == false) {
			f.setAgrupacionFechaProceso("N");
			f.setAgrupacionFechaProcesoSearch("N");

		} else {
			f.setAgrupacionFechaProceso("S");
		}
		if (this.getAgrupacionLote() == false) {
			f.setAgrupacionLote("N");
			f.setAgrupacionLoteSearch("N");

		} else {
			f.setAgrupacionLote("S");
		}

		if (this.getAgrupacionRegion() == false) {
			f.setAgrupacionRegion("N");
			f.setAgrupacionRegionSearch("N");

		} else {
			f.setAgrupacionRegion("S");
		}

		if (this.getAgrupacionZona() == false) {
			f.setAgrupacionZona("N");
			f.setAgrupacionZonaSearch("N");

		} else {
			f.setAgrupacionZona("S");
		}

		if (this.getAgrupacionCliente() == false) {
			f.setAgrupacionCliente("N");
			f.setAgrupacionClienteSearch("N");

		} else {
			f.setAgrupacionCliente("S");
		}
	}

	/**
	 * Valida Codigo Consultora
	 */
	public void validarCodigoConsultora() {
		try {
			ProcesoSTOEliminarPolizasFamiliaSeguraForm f = (ProcesoSTOEliminarPolizasFamiliaSeguraForm) this.formProceso;
			String codigoCliente = f.getCodigoCliente();
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			String codigocliente = aSvc.getNombreCliente(codigoCliente);

			String mensaje = null;
			if (codigocliente != null) {
				return;
			} else {
				mensaje = "Codigo de Cliente no existe, por favor ingrese un codigo valido para hacer la busqueda";
				this.addError("Error : ", mensaje);
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * Carga Zonas
	 * @param val
	 */
	public void loadZonas(ValueChangeEvent val) {
		try {
			log.debug(">>loadZonas");
			log.debug("val: " + (String[]) val.getNewValue());

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
	 * Guarda los filtros seleccionados
	 */
	private void setFilterSearch(ProcesoSTOEliminarPolizasFamiliaSeguraForm f) {
		f.setTipoDocumentoSearch(f.getTipoDocumento());
		f.setIndicadorDocumentoSearch(f.getIndicadorDocumento());
		f.setSelectedItemsSearch(f.getSelectedItems());
		f.setCodigoPeriodoSearch(f.getCodigoPeriodo());
		f.setFechaInicioCargaSearch(f.getFechaInicioCarga());
		f.setFechaFinCargaSearch(f.getFechaFinCarga());
		f.setHoraInicioCargaSearch(f.getHoraInicioCarga());
		f.setHoraFinCargaSearch(f.getHoraFinCarga());
		f.setFechaInicioProcesoSearch(f.getFechaInicioProceso());
		f.setFechaFinProcesoSearch(f.getFechaFinProceso());
		f.setHoraInicioProcesoSearch(f.getHoraInicioProceso());
		f.setHoraFinProcesoSearch(f.getHoraFinProceso());
		f.setNumeroLoteSearch(f.getNumeroLote());
		f.setCodigoClienteSearch(f.getCodigoCliente());
		f.setEstadoSearch(f.getEstado());
		f.setAccionSearch(f.getAccion());
		f.setSecuencia(f.getSecuencia());
		f.setAgrupacionPeriodoSearch(f.getAgrupacionPeriodo());
		f.setAgrupacionFechaProcesoSearch(f.getAgrupacionFechaProceso());
		f.setAgrupacionFechaCargaSearch(f.getAgrupacionFechaCarga());
		f.setAgrupacionLoteSearch(f.getAgrupacionLote());
		f.setAgrupacionRegionSearch(f.getAgrupacionRegion());
		f.setAgrupacionZonaSearch(f.getAgrupacionZona());
		f.setAgrupacionClienteSearch(f.getAgrupacionCliente());

		regionListSearch = f.getRegionList();
		zonaListSearch = f.getZonaList();

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		log.debug("setFindAttributes.");
		ProcesoSTOEliminarPolizasFamiliaSeguraForm f = (ProcesoSTOEliminarPolizasFamiliaSeguraForm) this.formProceso;
		this.sumaSubtotalCargasT = 0;
		this.sumaSubtotalEnviadasT = 0;
		this.sumaSubtotalErradasT = 0;
		this.sumaSubtotalRechazadasT = 0;
		this.sumaCargas = 0;
		this.sumaEnviadas = 0;
		this.sumaErradas = 0;
		this.sumaRechazadas = 0;
		validarCambiosCheckBos();
		Map params = null;
		String codigoPais = f.getCodigoPais();
		ProcesoSTOService procesoSTOService = (ProcesoSTOService) getBean("spusicc.procesoSTOService");
		TipoDocumentoDigitadoPK tipoDocumentoDigitadoPK = new TipoDocumentoDigitadoPK(
				codigoPais, Constants.STO_TIPO_DOCUMENTO_FAS);
		TipoDocumentoDigitado tipoDocumentoDigitado = procesoSTOService
				.getTipoDocumentoDigitado(tipoDocumentoDigitadoPK);

		f.setDescripcionDocumento(tipoDocumentoDigitado.getDesTipoDocu());
		String secuencia = procesoSTOService.getSecuenciaConsultaDocumento();
		f.setSecuencia(secuencia);

		params = this.getCriteria();
		List consultaValidacionList = procesoSTOService
				.getConsultaPolizasFamiliaSeguraList(params);
		this.stoListaPedidos = new ArrayList();
		this.stoListaPedidos = consultaValidacionList;
		int valor1 = this.stoListaPedidos.size();
		this.valorOriginal = this.stoListaPedidos.size();
		int tamanio = this.stoListaPedidos.size();
		if (tamanio > 0) {
			
			this.mostrarDatatable = true;
			this.mostrarPanelAdicionalProceso = false;
			this.mostrarBotonExecute = true;
			this.mostrarListaBusqueda = false;
		} else {
			this.mostrarDatatable=false;
			this.dmSTOEliminarPolizas = new DataTableModel(this.stoListaPedidos);
			return this.stoListaPedidos;
		}

		int sumaCargasT = 0;
		int sumaEnviadasT = 0;
		int sumaErradasT = 0;
		int sumaRechazadasT = 0;
		

		// ConsultaPolizas obj
		for (int i = 0; i < consultaValidacionList.size(); i++) {
			ConsultaPolizas obj = (ConsultaPolizas) consultaValidacionList
					.get(i);
			sumaCargasT = sumaCargasT + obj.getCargadas();
			sumaEnviadasT = sumaEnviadasT + obj.getEnviadas();
			sumaErradasT = sumaErradasT + obj.getErradas();
			sumaRechazadasT = sumaRechazadasT + obj.getRechazadas();
		}

		int valorPagina = 0;
		int filasMuestra = 10;
		int valorFinal = 0;
		
		int valorInicial = 0;
		int sumaSubtotalCargas = 0;
		int sumaSubtotalEnviadas = 0;
		int sumaSubtotalErradas = 0;
		int sumaSubtotalRechazadas = 0;
			
		valorInicial = valorPagina;
		valorFinal = filasMuestra + valorInicial;		

			// validando si se recibe menos de lo paginado
			if (this.stoListaPedidos.size() <= 9) {

				for (int i = 0; i < this.stoListaPedidos.size(); i++) {

					ConsultaPolizas obj = (ConsultaPolizas) this.stoListaPedidos
							.get(i);
					sumaSubtotalCargas = sumaSubtotalCargas + obj.getCargadas();
					sumaSubtotalEnviadas = sumaSubtotalEnviadas + obj.getEnviadas();
					sumaSubtotalErradas = sumaSubtotalErradas + obj.getErradas();
					sumaSubtotalRechazadas = sumaSubtotalRechazadas
							+ obj.getRechazadas();
				}
			}
			// si es igual al paginado, calcular subtotal
			else {

				for (int i = 0; i <= 9; i++) {

					ConsultaPolizas obj = (ConsultaPolizas) this.stoListaPedidos
							.get(i);
					sumaSubtotalCargas = sumaSubtotalCargas + obj.getCargadas();
					sumaSubtotalEnviadas = sumaSubtotalEnviadas + obj.getEnviadas();
					sumaSubtotalErradas = sumaSubtotalErradas + obj.getErradas();
					sumaSubtotalRechazadas = sumaSubtotalRechazadas
							+ obj.getRechazadas();
				}

			}

	
	
		this.sumaSubtotalCargasT = sumaSubtotalCargas;
		this.sumaSubtotalEnviadasT = sumaSubtotalEnviadas;
		this.sumaSubtotalErradasT = sumaSubtotalErradas;
		this.sumaSubtotalRechazadasT = sumaSubtotalRechazadas;

		this.sumaCargas = sumaCargasT;
		this.sumaEnviadas = sumaEnviadasT;
		this.sumaErradas = sumaErradasT;
		this.sumaRechazadas = sumaRechazadasT;

		setFilterSearch(f);
		f.setCargaCombo(Constants.NO);
		//syncZona(f);

		f.setHoraFinCarga(null);
		f.setHoraInicioCarga(null);
		f.setHoraFinProceso(null);
		f.setHoraInicioProceso(null);

		String ventana = "PF('dataTableWidgetVar1').clearFilters()";
		this.getRequestContext().execute(ventana);
		return consultaValidacionList;

	}

	/**
	 * calcular los subtotales
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
		int sumaSubtotalErradas = 0;
		int sumaSubtotalRechazadas = 0;
		
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
		if (this.pagina == 0) {
			// validando si se recibe menos de lo paginado
			if (this.stoListaPedidos.size() <= 9) {

				for (int i = 0; i < this.stoListaPedidos.size(); i++) {

					ConsultaPolizas obj = (ConsultaPolizas) this.stoListaPedidos
							.get(i);
					sumaSubtotalCargas = sumaSubtotalCargas + obj.getCargadas();
					sumaSubtotalEnviadas = sumaSubtotalEnviadas + obj.getEnviadas();
					sumaSubtotalErradas = sumaSubtotalErradas + obj.getErradas();
					sumaSubtotalRechazadas = sumaSubtotalRechazadas
							+ obj.getRechazadas();
				}
			}
			// si es igual al pagino, calcular subtotal
			else {

				for (int i = 0; i <= 9; i++) {

					ConsultaPolizas obj = (ConsultaPolizas) this.stoListaPedidos
							.get(i);
					sumaSubtotalCargas = sumaSubtotalCargas + obj.getCargadas();
					sumaSubtotalEnviadas = sumaSubtotalEnviadas + obj.getEnviadas();
					sumaSubtotalErradas = sumaSubtotalErradas + obj.getErradas();
					sumaSubtotalRechazadas = sumaSubtotalRechazadas
							+ obj.getRechazadas();
				}

			}

		} 
		//validando que sea la ultima pagina, y que tenga residuo
		else if (ultimaP && residuoUltimo != 0) {
			for (int i = valorInicial; i <= residuoUltimo - 1; i++) {
				ConsultaPolizas obj = (ConsultaPolizas) this.stoListaPedidos
						.get(i);
				sumaSubtotalCargas = sumaSubtotalCargas + obj.getCargadas();
				sumaSubtotalEnviadas = sumaSubtotalEnviadas + obj.getEnviadas();
				sumaSubtotalErradas = sumaSubtotalErradas + obj.getErradas();
				sumaSubtotalRechazadas = sumaSubtotalRechazadas
						+ obj.getRechazadas();
			}
		}

		// si es una lista normal con 10 registro entrara aca.
		else {
			for (int i = valorInicial; i <= valorFinal - 1; i++) {
				ConsultaPolizas obj = (ConsultaPolizas) this.stoListaPedidos
						.get(i);
				sumaSubtotalCargas = sumaSubtotalCargas + obj.getCargadas();
				sumaSubtotalEnviadas = sumaSubtotalEnviadas + obj.getEnviadas();
				sumaSubtotalErradas = sumaSubtotalErradas + obj.getErradas();
				sumaSubtotalRechazadas = sumaSubtotalRechazadas
						+ obj.getRechazadas();
			}
		}

		this.sumaSubtotalCargasT = sumaSubtotalCargas;
		this.sumaSubtotalEnviadasT = sumaSubtotalEnviadas;
		this.sumaSubtotalErradasT = sumaSubtotalErradas;
		this.sumaSubtotalRechazadasT = sumaSubtotalRechazadas;

	}

	/**
	 * @param request
	 * @param cabecera
	 */
	private void syncZona(ProcesoSTOEliminarPolizasFamiliaSeguraForm cabecera) {
		try {
			String[] codigoRegion = cabecera.getRegionList();

			if (cabecera.getRegionList() != null) {
				LabelValue[] resultZona = this.getZonaByRegion(
						cabecera.getCodigoPais(), codigoRegion);
				siccZonaList = resultZona;
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
	 */
	public int devuelvePosicion(String posicion) {
		int posi = 0;
		int aux = posicion.indexOf('|');
		String id2 = posicion.substring(0, aux);
		posi = Integer.parseInt(id2);

		return posi;
	}

	/**
	 * @param f
	 * @param request
	 * @return Devuelve el filtro con los criterios de busqueda
	 */
	protected Map getCriteria() {

		ProcesoSTOEliminarPolizasFamiliaSeguraForm f = (ProcesoSTOEliminarPolizasFamiliaSeguraForm) this.formProceso;
		Map params = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String numLote = f.getNumeroLote();

		f.setFechaInicioCarga(DateUtil.convertDateToString(f
				.getFechaInicioCargaD()));
		f.setFechaFinCarga(DateUtil.convertDateToString(f.getFechaFinCargaD()));
		f.setFechaInicioProceso(DateUtil.convertDateToString(f
				.getFechaInicioProcesoD()));
		f.setFechaFinProceso(DateUtil.convertDateToString(f
				.getFechaFinProcesoD()));

		String indDocu = new String();
		indDocu = "1";

		String fechaInicioCarga = null;
		if (f.getHoraFinCarga() == null) {
			f.setHoraFinCarga("00:00");
		}
		if (f.getHoraInicioCarga() == null) {
			f.setHoraInicioCarga("00:00");
		}
		if (f.getHoraFinProceso() == null) {
			f.setHoraFinProceso("00:00");
		}
		if (f.getHoraInicioProceso() == null) {
			f.setHoraInicioProceso("00:00");

		}

		if (!f.getFechaInicioCarga().equals(""))
			fechaInicioCarga = (f.getFechaInicioCarga() + " " + f
					.getHoraInicioCarga()).trim();

		String fechaFinCarga = null;
		if (!f.getFechaFinCarga().equals(""))
			fechaFinCarga = (f.getFechaFinCarga() + " " + f.getHoraFinCarga())
					.trim();

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

		String agruPeriodo = f.getAgrupacionPeriodo();
		String agruFecCarga = f.getAgrupacionFechaCarga();
		String agruFecProce = f.getAgrupacionFechaProceso();
		String agruLote = f.getAgrupacionLote();
		String agruRegion = f.getAgrupacionRegion();
		String agruZona = f.getAgrupacionZona();
		String agruCliente = f.getAgrupacionCliente();

		if (agruPeriodo.compareToIgnoreCase("N") == 0)
			agruPeriodo = "";
		if (agruFecCarga.compareToIgnoreCase("N") == 0)
			agruFecCarga = "";
		if (agruFecProce.compareToIgnoreCase("N") == 0)
			agruFecProce = "";
		if (agruLote.compareToIgnoreCase("N") == 0)
			agruLote = "";
		if (agruRegion.compareToIgnoreCase("N") == 0)
			agruRegion = "";
		if (agruZona.compareToIgnoreCase("N") == 0)
			agruZona = "";
		if (agruCliente.compareToIgnoreCase("N") == 0)
			agruCliente = "";

		String codigoEstado = f.getEstado();
		String estadoError = "";
		String estadoRechazadas = "";
		String estadoEnviadas = "";

		if (codigoEstado.equals(Constants.CODIGO_ESTADO_FAS_ERROR))
			estadoError = Constants.SI;
		if (codigoEstado.equals(Constants.CODIGO_ESTADO_FAS_RECHAZADAS))
			estadoRechazadas = Constants.SI;
		if (codigoEstado.equals(Constants.CODIGO_ESTADO_FAS_ENVIADAS))
			estadoEnviadas = Constants.SI;

		try {
			params = BeanUtils.describe(f);
			params.put("codigoPais", pais.getCodigo());
			params.put("tipoDocumento", f.getTipoDocumento());
			params.put("numLote", numLote);
			params.put("indDocu", indDocu);
			params.put("fechaInicioCarga", fechaInicioCarga);
			params.put("fechaFinCarga", fechaFinCarga);
			params.put("fechaInicioProceso", fechaInicioProceso);
			params.put("fechaFinProceso", fechaFinProceso);
			params.put("agruPeriodo", agruPeriodo);
			params.put("agruFecCarga", agruFecCarga);
			params.put("agruFecProce", agruFecProce);
			params.put("agruLote", agruLote);
			params.put("agruRegion", agruRegion);
			params.put("agruZona", agruZona);
			params.put("agrupacionClinte", agruCliente);
			params.put("codigoCliente", codigoCliente);
			params.put("codigoPeriodo", codigoPeriodo);
			params.put(
					"regionList",
					(f.getRegionList() == null) ? new ArrayList() : Arrays
							.asList(f.getRegionList()));
			params.put("zonaList", (f.getZonaList() == null) ? new ArrayList()
					: Arrays.asList(f.getZonaList()));
			params.put("estadoError", estadoError);
			params.put("estadoRechazadas", estadoRechazadas);
			params.put("estadoEnviadas", estadoEnviadas);
			params.put("secuencia", f.getSecuencia());

			// ----------------------------------------------
			List listaClientes = this.stoEliminarPedidosClientesList;

			String[] listaClientes2 = new String[0];
			if (codigoCliente.length() > 0)
				listaClientes2 = f.getCodigoCliente().split(",+");
			f.setClienteList(null);
			Long longitudPais = pais.getLongitudCodigoCliente();

			String[] clienteList;
			int size = 0;

			if (listaClientes != null) {
				clienteList = new String[listaClientes.size()
						+ listaClientes2.length];
				size = listaClientes.size();

				for (int i = 0; i < listaClientes.size(); i++) {
					LabelValue bean = (LabelValue) listaClientes.get(i);
					clienteList[i] = StringUtils.leftPad(bean.getLabel(),
							longitudPais.intValue(), '0');
				}
			} else {
				clienteList = new String[listaClientes2.length];
			}

			for (int i = 0; i < listaClientes2.length; i++) {
				clienteList[i + size] = StringUtils.leftPad(listaClientes2[i],
						longitudPais.intValue(), '0');
			}

			f.setClienteList(clienteList);
			params.put(
					"clienteList",
					(f.getClienteList() == null) ? new ArrayList() : Arrays
							.asList(f.getClienteList()));
			// ----------------------------------------------

		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

		return params;
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
		Pais paisD = this.mPantallaPrincipalBean.getCurrentCountry();
		ProcesoSTOEliminarPolizasFamiliaSeguraForm f = (ProcesoSTOEliminarPolizasFamiliaSeguraForm) this.formProceso;
		f.setTipoDocumento(Constants.STO_TIPO_DOCUMENTO_FAS);
		String codigoPais = paisD.getCodigo();
		ProcesoSTOService procesoSTOService = (ProcesoSTOService) getBean("spusicc.procesoSTOService");
		TipoDocumentoDigitadoPK tipoDocumentoDigitadoPK = new TipoDocumentoDigitadoPK(
				codigoPais, Constants.STO_TIPO_DOCUMENTO_FAS);
		TipoDocumentoDigitado tipoDocumentoDigitado = procesoSTOService
				.getTipoDocumentoDigitado(tipoDocumentoDigitadoPK);

		f.setTipoDocumento(Constants.STO_TIPO_DOCUMENTO_FAS);
		f.setDescripcionDocumento(tipoDocumentoDigitado.getDesTipoDocu());

		List listaClientes = new ArrayList();
		UploadedFile archivo = f.getClienteFile();

		InputStream is = archivo.getInputstream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String linea = "";

		// validando max numer de regitrs a cargar
		PaisService paisService = (PaisService) getBean("paisService");
		Pais pais = paisService.getPais(f.getCodigoPais());

		int contFilas = 0;
		int numMaximoRegistros = StringUtils.isNotEmpty(pais
				.getMaximoNumeroRegistrosFile()) ? Integer.parseInt(pais
				.getMaximoNumeroRegistrosFile()) : 0;

		while (true) {
			linea = br.readLine();
			if (linea == null)
				break;

			if (StringUtils.isNotEmpty(linea)) {
				contFilas++;

				if (contFilas > numMaximoRegistros) {

					String mensaje = null;
					mensaje = this.getResourceMessage("errors.maximo.registro");
					this.addError("Error : ", mensaje);
					listaClientes = new ArrayList();
					break;

				}

				LabelValue bean = new LabelValue(linea.trim(), linea.trim());
				listaClientes.add(bean);
			}
		}

		setAgrupacionDefault();
		this.stoEliminarPedidosClientesList = listaClientes;
		if (stoEliminarPedidosClientesList != null) {
			this.mostrarGrilla = true;
		}
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

		ProcesoSTOEliminarPolizasFamiliaSeguraForm f = (ProcesoSTOEliminarPolizasFamiliaSeguraForm) this.formProceso;
		f.setTipoDocumento(Constants.STO_TIPO_DOCUMENTO_FAS);
		setAgrupacionDefault();
		this.siccRegionList = new ArrayList();
		this.stoEliminarPedidosClientesList = new ArrayList();
		this.siccZonaList = null;
		this.regionListSearch = null;
		this.zonaListSearch = null;
		this.stoListaPedidos = new ArrayList();
		this.mostrarGrilla = false;
		this.mostrarDatatable = false;
		this.mostrarPanelAdicionalProceso = false;

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
		ProcesoSTOEliminarPolizasFamiliaSeguraForm form = new ProcesoSTOEliminarPolizasFamiliaSeguraForm();
		return form;
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

		ProcesoSTOEliminarPolizasFamiliaSeguraForm f = (ProcesoSTOEliminarPolizasFamiliaSeguraForm) this.formProceso;

		params.put("codigoSistema", Constants.STO_CODIGO_SISTEMA);
		params.put("codigoProcesoBatch", Constants.STO_PROC_BATC_FAS);

		ProcesoBatchService procesoBatchService = (ProcesoBatchService) getBean("scsicc.procesoBatchService");
		String mensaje = null;
		List listaProcesoBatchDependientes = procesoBatchService
				.getProcesoBatchActuDependientesByCriteria(params);

		List listaDelete = new ArrayList();
		int tamanio = beanProcesoSTOEliminarPolizasFamiliaSegura.length;
		for (int i = 0; i < tamanio; i++) {
			ConsultaPolizas form = (ConsultaPolizas) beanProcesoSTOEliminarPolizasFamiliaSegura[i];
			listaDelete.add(form);
		}
		// String codigo = objSeleccionado.getCodigoTipoDocumento();

		if (listaProcesoBatchDependientes.size() > 0) {

			mensaje = this
					.getResourceMessage("procesoBatch.error.procesoDependienteEnEjecucion");

			for (int i = 0; i < listaProcesoBatchDependientes.size(); i++) {

				ProcesoBatchActu procesoBatchActu = (ProcesoBatchActu) listaProcesoBatchDependientes
						.get(i);

				mensaje += "\n"
						+ procesoBatchActu.getCodigoSistema()
						+ "-"
						+ procesoBatchActu.getProcesoBatch()
								.getCodigoProcesoBatch();
				mensaje += " "
						+ procesoBatchActu.getProcesoBatch()
								.getDescripcionProcesoBatch();
				mensaje += "; ";
			}

			mensaje += "\n"
					+ this.getResourceMessage("procesoBatch.error.espereProcesoDependienteEnEjecucion");
			throw new Exception(mensaje);
		}

		params.put("codTipoDocu", f.getTipoDocumento());
		params.put("secuencia", f.getSecuencia());

		ProcesoSTOService stoService = (ProcesoSTOService) getBean("spusicc.procesoSTOService");
		params.put("codigoPais", f.getCodigoPais());
		List newObjetcs = new ArrayList();
		newObjetcs = stoService.getSTOListByPolizaList(listaDelete, params);

		ProcesoSTOExecutionService service = (ProcesoSTOExecutionService) getBean("spusicc.procesoSTOExecutionService");
		AccionTipoDocumento accionTipoDocumento = new AccionTipoDocumento(
				f.getCodigoPais(), f.getTipoDocumento(), f.getAccion());
		service.execute(accionTipoDocumento, params, newObjetcs);

		this.stoListaPedidos = this.setFindAttributes();

		return params;
	}

	/**
	 * Metodo que captura la pagina del datatable
	 * 
	 * @param e
	 */
	public void onPage(PageEvent e) {
		int paginas = e.getPage();
		this.pagina = paginas;
		
		calcularSubtotales();
		
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
		this.mostrarBotonExecute = false;
		this.mostrarBotonBuscar = true;
		// this.mostrarListaBusqueda = true;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String codigoPais = pais.getCodigo();
		this.mostrarListaBusqueda = false;
		this.mostrarPanelAdicionalProceso = false;
		this.mostrarPanelAdicionalProceso = false;
		// Seteando el Tipo de documento
		ProcesoSTOService procesoSTOService = (ProcesoSTOService) getBean("spusicc.procesoSTOService");
		TipoDocumentoDigitadoPK tipoDocumentoDigitadoPK = new TipoDocumentoDigitadoPK(
				codigoPais, Constants.STO_TIPO_DOCUMENTO_FAS);
		TipoDocumentoDigitado tipoDocumentoDigitado = procesoSTOService
				.getTipoDocumentoDigitado(tipoDocumentoDigitadoPK);

		ProcesoSTOEliminarPolizasFamiliaSeguraForm f = (ProcesoSTOEliminarPolizasFamiliaSeguraForm) this.formProceso;
		f.setTipoDocumento(Constants.STO_TIPO_DOCUMENTO_FAS);
		f.setDescripcionDocumento(tipoDocumentoDigitado.getDesTipoDocu());

		clearFile();

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
		this.stoEstadosGestionList = ajaxService.getEstadosSTOByTipoDocumento(
				codigoPais, Constants.STO_TIPO_DOCUMENTO_FAS);
		this.pagina = 0;

		f.setEstado("01");
		f.setAccion("");
//		f.setRegionList(new String[1]);
//		f.setZonaList(new String[1]);
//		f.setRegionListSearch(new String[1]);
//		f.setZonaListSearch(new String[1]);
		f.setIndicadorDocumento("TODOS");
		f.setCargaCombo(Constants.SI);

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
		f.setCodigoPais(pais.getCodigo());

		ClienteUAGenerarService clienteService = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");
		this.longitudCampoClientes = clienteService
				.getTamanhoNumeroCliente(codigoPais);
		this.mostrarPanelAdicionalProceso = false;

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
	 * @return the beanProcesoSTOEliminarPolizasFamiliaSegura
	 */
	public Object[] getBeanProcesoSTOEliminarPolizasFamiliaSegura() {
		return beanProcesoSTOEliminarPolizasFamiliaSegura;
	}

	/**
	 * @param beanProcesoSTOEliminarPolizasFamiliaSegura
	 *            the beanProcesoSTOEliminarPolizasFamiliaSegura to set
	 */
	public void setBeanProcesoSTOEliminarPolizasFamiliaSegura(
			Object[] beanProcesoSTOEliminarPolizasFamiliaSegura) {
		this.beanProcesoSTOEliminarPolizasFamiliaSegura = beanProcesoSTOEliminarPolizasFamiliaSegura;
	}

	/**
	 * @return the valorOriginal
	 */
	public Integer getValorOriginal() {
		return valorOriginal;
	}

	/**
	 * @param valorOriginal the valorOriginal to set
	 */
	public void setValorOriginal(Integer valorOriginal) {
		this.valorOriginal = valorOriginal;
	}

	/**
	 * @return the dmSTOEliminarPolizas
	 */
	public DataTableModel getDmSTOEliminarPolizas() {
		return dmSTOEliminarPolizas;
	}

	/**
	 * @param dmSTOEliminarPolizas the dmSTOEliminarPolizas to set
	 */
	public void setDmSTOEliminarPolizas(DataTableModel dmSTOEliminarPolizas) {
		this.dmSTOEliminarPolizas = dmSTOEliminarPolizas;
	}
}