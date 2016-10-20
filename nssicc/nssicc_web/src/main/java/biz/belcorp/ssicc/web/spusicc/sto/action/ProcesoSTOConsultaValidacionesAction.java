package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.primefaces.event.data.PageEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sto.model.ConsultaValidaciones;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOLevantamientoErroresValidacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.ProcesoSTOConsultaValidacionesForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProcesoSTOConsultaValidacionesAction extends BaseConsultaAbstractAction {

	private static final long serialVersionUID = 1L;
	private String[] regionListSearch;
	private String[] zonaListSearch;
	private LabelValue[] siccZonaList;
	private List stoListaPedidos;
	private List stoEliminarPedidosClientesList;
	private List siccRegionList;
	private LabelValue[] stoEstadosGestionList;
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
	private Object[] beanProcesoSTOConsultaValidaciones;
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
	private List stoTipoDocumentoList;
	private String stoLineaDefecto;
	private String stoLineaMaxima;
	private Boolean agrupacionNumeroPreimpreso;
	private Boolean agrupacionPeriodo;
	private Boolean agrupacionFechaCarga;
	private Boolean agrupacionFechaProceso;
	private Boolean agrupacionLote;
	private Boolean agrupacionRegion;
	private Boolean agrupacionZona;
	private Boolean agrupacionCliente;
	
	private String stoTipoDocumentoCabecera ="1";
	private String stoTIpoDocumentoDetalle ="0";
	private String stoTipoDocumentoTodos = "";

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
		
		Map params = null;
		ProcesoSTOConsultaValidacionesForm f = (ProcesoSTOConsultaValidacionesForm) this.formBusqueda;
		
		this.sumaCargasT = 0;
		this.sumaCorrectasT = 0;
		this.sumaDetallesOkT = 0;
		this.sumaErradasT = 0;
		this.sumaRechazadasT = 0;

		this.sumaSubtotalCargasT = 0;
		this.sumaSubtotalCorrectasT = 0;
		this.sumaSubtotalDetallesOkT = 0;
		this.sumaSubtotalErradasT = 0;
		this.sumaSubtotalRechazadasT = 0;
		this.cantidadRowsDefaultDatatable = "100";
		
		ProcesoSTOLevantamientoErroresValidacionService service = (ProcesoSTOLevantamientoErroresValidacionService) getBean("spusicc.procesoSTOLevantamientoErroresValidacionService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		String fechaInicio,fechaFin,fechaInicioProceso,fechaFinProceso=null;
		
		String aMask = "dd/MM/yyyy HH:mm";
		fechaInicio=DateUtil.convertDateToString(aMask,f.getFechaInicioD());
		log.debug("fechaInicio " + fechaInicio);
		
		fechaFin=DateUtil.convertDateToString(aMask,f.getFechaFinD());
		log.debug("fechaFin " + fechaFin);
		
		fechaInicioProceso = DateUtil.convertDateToString(aMask ,f.getFechaInicioProcesoD());
		log.debug("fechaInicioProceso " + fechaInicioProceso);
		
		fechaFinProceso=DateUtil.convertDateToString(aMask ,f.getFechaFinProcesoD());
		log.debug("fechaFinProceso " + fechaFinProceso);

		
		String agruCliente = "";
		if (this.agrupacionCliente) agruCliente = "S";

		String agruLote = "";
		if (this.agrupacionLote) agruLote = "S";

		String agruRegion = "";
		if (this.agrupacionRegion)	agruRegion = "S";

		String agruZona =  "";
		if (this.agrupacionZona) agruZona ="S";

		String agruFecProce = "";
		if (this.agrupacionFechaProceso) agruFecProce = "S";

		String agruFecDigi = "";
		if (this.agrupacionFechaCarga)	agruFecDigi = "S";

		String agruPeriodo = "";
		if (this.agrupacionPeriodo)		agruPeriodo = "S";

		String agruNumeroPreimpreso = "";
		if (this.agrupacionNumeroPreimpreso) agruNumeroPreimpreso = "S";

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

		params = BeanUtils.describe(f);
		params.put("codigoPais", pais.getCodigo());
		params.put("tipoDocumento", f.getTipoDocumento());
		params.put("numLote", f.getNumeroLote());
		params.put("indDocu", f.getIndicadorDocumento());
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
		params.put("agruNumeroPreimpreso", agruNumeroPreimpreso);
		
		params.put("codigoCliente", f.getCodigoCliente());
		params.put("codigoPeriodo", f.getCodigoPeriodo());
		params.put("regionList", (f.getRegionList() == null) ? new ArrayList()
				: Arrays.asList(f.getRegionList()));
		params.put("zonaList", (f.getZonaList() == null) ? new ArrayList()
				: Arrays.asList(f.getZonaList()));
		params.put("numPreimpreso", f.getNumPreimpreso());

		params.put("isActualizacionDatos", null);
		f.setIsActualizacionDatos(Constants.NUMERO_CERO);
		if (params.get("tipoDocumento")
				.equals(Constants.STO_TIPO_DOCUMENTO_SAD)
				&& !agruCliente.equals("")) {
			params.put("isActualizacionDatos", Constants.NUMERO_UNO);
			f.setIsActualizacionDatos(Constants.NUMERO_UNO);
		}

		/*******************************************************************************************************/
		String agruTodos = "";
		if (!agruCliente.equals("") && !agruLote.equals("")
				&& !agruRegion.equals("") && !agruZona.equals("")
				&& !agruFecProce.equals("") && !agruFecDigi.equals("")
				&& !agruPeriodo.equals("") && !agruNumeroPreimpreso.equals(""))
			agruTodos = Constants.SI;

		params.put("isCuponPago", null);
		f.setIsCuponPago(Constants.NUMERO_CERO);
		if (params.get("tipoDocumento")
				.equals(Constants.STO_TIPO_DOCUMENTO_CUP)
				&& !agruTodos.equals("")) {
			params.put("isCuponPago", Constants.NUMERO_UNO);
			f.setIsCuponPago(Constants.NUMERO_UNO);
		}
		/*******************************************************************************************************/

		List consultaValidacionList = service.getConsultaValidacionesList(params);
		this.stoListaValidaciones = new ArrayList();
		this.stoListaValidaciones = consultaValidacionList;
		int tamanio = this.stoListaValidaciones.size();
		if (tamanio > 0) {
			int sumaCorrectas = 0;
			int sumaCargas = 0;
			int sumaErradas = 0;
			int sumaRechazadas = 0;
			int sumaDetallesOK = 0;

			// ConsultaValidaciones obj
			for (int i = 0; i < consultaValidacionList.size(); i++) {
				ConsultaValidaciones obj = (ConsultaValidaciones) this.stoListaValidaciones
						.get(i);
				sumaCorrectas = sumaCorrectas + obj.getCorrectas();
				sumaCargas = sumaCargas + obj.getTotalCarga();
				sumaErradas = sumaErradas + obj.getErradas();
				sumaRechazadas = sumaRechazadas + obj.getRechazadas();
				sumaDetallesOK = sumaDetallesOK + obj.getDetallesOK();
			}

			int valorPagina = 1;
			int filasMuestra = nroPaginacionDatatable;
			int valorFinal = 0;
			valorFinal = valorPagina * filasMuestra;
			int valorInicial = 0;
			int sumaSubtotalCorrectas = 0;
			int sumaSubtotalCargas = 0;
			int sumaSubtotalErradas = 0;
			int sumaSubtotalRechazadas = 0;
			int sumaSubtotalDetallesOK = 0;

			// validando si se recibe menos de lo paginado
			if (this.stoListaValidaciones.size() <= nroPaginacionDatatable-1) {

				for (int i = 0; i < this.stoListaValidaciones.size() ; i++) {

					ConsultaValidaciones obj = (ConsultaValidaciones) this.stoListaValidaciones
							.get(i);
					sumaSubtotalCorrectas = sumaSubtotalCorrectas
							+ obj.getCorrectas();
					sumaSubtotalCargas = sumaSubtotalCargas
							+ obj.getTotalCarga();
					sumaSubtotalErradas = sumaSubtotalErradas
							+ obj.getErradas();
					sumaSubtotalRechazadas = sumaSubtotalRechazadas
							+ obj.getRechazadas();
					sumaSubtotalDetallesOK = sumaSubtotalDetallesOK
							+ obj.getDetallesOK();
				}
			} else {
				for (int i = valorInicial; i <= valorFinal - 1; i++) {
					if (tamanio <= i) {
						break;
					}
					ConsultaValidaciones obj = (ConsultaValidaciones) this.stoListaValidaciones
							.get(i);
					sumaSubtotalCorrectas = sumaSubtotalCorrectas
							+ obj.getCorrectas();
					sumaSubtotalCargas = sumaSubtotalCargas
							+ obj.getTotalCarga();
					sumaSubtotalErradas = sumaSubtotalErradas
							+ obj.getErradas();
					sumaSubtotalRechazadas = sumaSubtotalRechazadas
							+ obj.getRechazadas();
					sumaSubtotalDetallesOK = sumaSubtotalDetallesOK
							+ obj.getDetallesOK();
				}
			}

			this.sumaSubtotalCorrectasT = sumaSubtotalCorrectas;
			this.sumaSubtotalCargasT = sumaSubtotalCargas;
			this.sumaSubtotalDetallesOkT = sumaSubtotalDetallesOK;
			this.sumaSubtotalErradasT = sumaSubtotalErradas;
			this.sumaSubtotalRechazadasT = sumaSubtotalRechazadas;
			this.sumaCorrectasT = sumaCorrectas;
			this.sumaCargasT = sumaCargas;
			this.sumaErradasT = sumaErradas;
			this.sumaRechazadasT = sumaRechazadas;
			this.sumaDetallesOkT = sumaDetallesOK;
			
//			Conviertiendo a decimales

			

		}

		this.stoLineaDefecto = f.getLineaDefecto();
		this.stoLineaMaxima = f.getLineaMaxima();
		this.mostrarListaBusqueda = false;
		String ventana = "PF('dataTableWidgetConsultaValidaciones').clearFilters()";
		this.getRequestContext().execute(ventana);
		

		return consultaValidacionList;
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
		ProcesoSTOConsultaValidacionesForm f = (ProcesoSTOConsultaValidacionesForm) this.formBusqueda;
		
		
		if (f.getFechaInicioD() != null  &&  f.getFechaFinD() != null ) {
			if (!validarFechas(f.getFechaInicioD(),
					f.getFechaFinD())) {
				return this.getResourceMessage("errors.compare.dates");
			}
		}


		if (f.getFechaInicioProcesoD() != null  &&  f.getFechaFinProcesoD() != null ) {
			if (!validarFechas(f.getFechaInicioProcesoD(),
					f.getFechaFinProcesoD())) {
				return this.getResourceMessage("errors.compare.dates");
			}
		}

		return null;
	}

	/**
	 * valida la fechas
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
	 * INICIALIZANDO VALORES
	 * 
	 * @throws Exception
	 */
	public void inicializando() throws Exception {

		ProcesoSTOConsultaValidacionesForm f = (ProcesoSTOConsultaValidacionesForm) this.formBusqueda;
		f.setIndicadorDocumento(stoTipoDocumentoTodos);

		this.agrupacionCliente = false;
		this.agrupacionFechaCarga = false;
		this.agrupacionFechaProceso = false;
		this.agrupacionLote = false;
		this.agrupacionNumeroPreimpreso = false;
		this.agrupacionPeriodo = false;
		this.agrupacionRegion = false;
		this.agrupacionZona = false;


		this.sumaCargasT = 0;
		this.sumaCorrectasT = 0;
		this.sumaDetallesOkT = 0;
		this.sumaErradasT = 0;
		this.sumaRechazadasT = 0;

		this.sumaSubtotalCargasT = 0;
		this.sumaSubtotalCorrectasT = 0;
		this.sumaSubtotalDetallesOkT = 0;
		this.sumaSubtotalErradasT = 0;
		this.sumaSubtotalRechazadasT = 0;
		
		Calendar cal = Calendar.getInstance();
		cal.setTime( new Date());
		cal.set(Calendar.HOUR_OF_DAY,0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.MILLISECOND,0);

		Date fechaInicio = cal.getTime();
		f.setFechaInicioProcesoD(fechaInicio);
		f.setFechaFinProcesoD(DateUtil.addToDate(fechaInicio, Calendar.DATE, 1));
		

	}

	/**
	 * Realiza el evento de cambio de pagina
	 * 
	 * @param e
	 */
	public void onPage(PageEvent e) {
		int paginas = e.getPage();
		this.pagina = paginas;
		calcularSubtotales();
	}

	/**
	 * Calcula subtotales del datatable
	 */
	public void calcularSubtotales() {

		int valorPagina = this.pagina + 1;
		int filasMuestra = nroPaginacionDatatable;
		int valorFinal = 0;
		valorFinal = valorPagina * filasMuestra;

		int valorInicial = 0;
		valorInicial = valorFinal - filasMuestra;

		int sumaSubtotalCorrectas = 0;
		int sumaSubtotalCargas = 0;
		int sumaSubtotalErradas = 0;
		int sumaSubtotalRechazadas = 0;
		int sumaSubtotalDetallesOK = 0;

		// capturando la ultima pagina
		int ultimapagina = 0;
		boolean ultimaP = false;
		int ultimo = (this.stoListaValidaciones.size() / nroPaginacionDatatable) + 1;
		int residuoUltimo = (this.stoListaValidaciones.size() % nroPaginacionDatatable)
				+ valorInicial;
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
			if (this.stoListaValidaciones.size() <= nroPaginacionDatatable-1) {

				for (int i = 0; i < this.stoListaValidaciones.size(); i++) {

					ConsultaValidaciones obj = (ConsultaValidaciones) this.stoListaValidaciones
							.get(i);
					sumaSubtotalCorrectas = sumaSubtotalCorrectas
							+ obj.getCorrectas();
					sumaSubtotalCargas = sumaSubtotalCargas
							+ obj.getTotalCarga();
					sumaSubtotalErradas = sumaSubtotalErradas
							+ obj.getErradas();
					sumaSubtotalRechazadas = sumaSubtotalRechazadas
							+ obj.getRechazadas();
					sumaSubtotalDetallesOK = sumaSubtotalDetallesOK
							+ obj.getDetallesOK();
				}
			}
			// si es igual al pagino, calcular subtotal
			else {

				for (int i = 0; i <= nroPaginacionDatatable-1; i++) {

					ConsultaValidaciones obj = (ConsultaValidaciones) this.stoListaValidaciones
							.get(i);
					sumaSubtotalCorrectas = sumaSubtotalCorrectas
							+ obj.getCorrectas();
					sumaSubtotalCargas = sumaSubtotalCargas
							+ obj.getTotalCarga();
					sumaSubtotalErradas = sumaSubtotalErradas
							+ obj.getErradas();
					sumaSubtotalRechazadas = sumaSubtotalRechazadas
							+ obj.getRechazadas();
					sumaSubtotalDetallesOK = sumaSubtotalDetallesOK
							+ obj.getDetallesOK();
				}

			}

		}
		// validando que sea la ultima pagina, y que tenga residuo
		else if (ultimaP && residuoUltimo != 0) {
			for (int i = valorInicial; i <= residuoUltimo - 1; i++) {
				ConsultaValidaciones obj = (ConsultaValidaciones) this.stoListaValidaciones
						.get(i);
				sumaSubtotalCorrectas = sumaSubtotalCorrectas
						+ obj.getCorrectas();
				sumaSubtotalCargas = sumaSubtotalCargas + obj.getTotalCarga();
				sumaSubtotalErradas = sumaSubtotalErradas + obj.getErradas();
				sumaSubtotalRechazadas = sumaSubtotalRechazadas
						+ obj.getRechazadas();
				sumaSubtotalDetallesOK = sumaSubtotalDetallesOK
						+ obj.getDetallesOK();
			}
		}

		// si es una lista normal con 10 registro entrara aca.
		else {
			for (int i = valorInicial; i <= valorFinal - 1; i++) {
				ConsultaValidaciones obj = (ConsultaValidaciones) this.stoListaValidaciones
						.get(i);
				sumaSubtotalCorrectas = sumaSubtotalCorrectas
						+ obj.getCorrectas();
				sumaSubtotalCargas = sumaSubtotalCargas + obj.getTotalCarga();
				sumaSubtotalErradas = sumaSubtotalErradas + obj.getErradas();
				sumaSubtotalRechazadas = sumaSubtotalRechazadas
						+ obj.getRechazadas();
				sumaSubtotalDetallesOK = sumaSubtotalDetallesOK
						+ obj.getDetallesOK();
			}
		}

		this.sumaSubtotalCorrectasT = sumaSubtotalCorrectas;
		this.sumaSubtotalCargasT = sumaSubtotalCargas;
		this.sumaSubtotalErradasT = sumaSubtotalErradas;
		this.sumaSubtotalRechazadasT = sumaSubtotalRechazadas;
		this.sumaSubtotalDetallesOkT = sumaSubtotalDetallesOK;

	}

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("Executing action : setViewAttributes.");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String codigoPais = pais.getCodigo();
		inicializando();
		this.mostrarPaginacionDatatableSpinner = false;
		this.nroPaginacionDatatable = 100;
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoUsuario", usuario.getLogin());

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");

		criteria.put("indicadorPantalla",Constants.STO_INDICADOR_PANTALLA_CONSULTA);
		this.stoTipoDocumentoList = procesoSTOEjecucionValidacionesService.getTiposDocumentosSTO(criteria);
		this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais", criteria);
		
		this.cantidadRowsDefaultDatatable = String.valueOf(nroPaginacionDatatable);
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
	 * @return the beanProcesoSTOConsultaValidaciones
	 */
	public Object[] getBeanProcesoSTOConsultaValidaciones() {
		return beanProcesoSTOConsultaValidaciones;
	}

	/**
	 * @param beanProcesoSTOConsultaValidaciones
	 *            the beanProcesoSTOConsultaValidaciones to set
	 */
	public void setBeanProcesoSTOConsultaValidaciones(
			Object[] beanProcesoSTOConsultaValidaciones) {
		this.beanProcesoSTOConsultaValidaciones = beanProcesoSTOConsultaValidaciones;
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
	 * @return the stoTipoDocumentoList
	 */
	public List getStoTipoDocumentoList() {
		return stoTipoDocumentoList;
	}

	/**
	 * @param stoTipoDocumentoList
	 *            the stoTipoDocumentoList to set
	 */
	public void setStoTipoDocumentoList(List stoTipoDocumentoList) {
		this.stoTipoDocumentoList = stoTipoDocumentoList;
	}

	/**
	 * @return the stoLineaDefecto
	 */
	public String getStoLineaDefecto() {
		return stoLineaDefecto;
	}

	/**
	 * @param stoLineaDefecto
	 *            the stoLineaDefecto to set
	 */
	public void setStoLineaDefecto(String stoLineaDefecto) {
		this.stoLineaDefecto = stoLineaDefecto;
	}

	/**
	 * @return the stoLineaMaxima
	 */
	public String getStoLineaMaxima() {
		return stoLineaMaxima;
	}

	/**
	 * @param stoLineaMaxima
	 *            the stoLineaMaxima to set
	 */
	public void setStoLineaMaxima(String stoLineaMaxima) {
		this.stoLineaMaxima = stoLineaMaxima;
	}

	/**
	 * @return the agrupacionNumeroPreimpreso
	 */
	public Boolean getAgrupacionNumeroPreimpreso() {
		return agrupacionNumeroPreimpreso;
	}

	/**
	 * @param agrupacionNumeroPreimpreso
	 *            the agrupacionNumeroPreimpreso to set
	 */
	public void setAgrupacionNumeroPreimpreso(Boolean agrupacionNumeroPreimpreso) {
		this.agrupacionNumeroPreimpreso = agrupacionNumeroPreimpreso;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction
	 * #devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ProcesoSTOConsultaValidacionesForm form = new ProcesoSTOConsultaValidacionesForm();
		return form;
	}
	
	/**
	 * @return the stoTipoDocumentoCabecera
	 */
	public String getStoTipoDocumentoCabecera() {
		return stoTipoDocumentoCabecera;
	}

	/**
	 * @param stoTipoDocumentoCabecera the stoTipoDocumentoCabecera to set
	 */
	public void setStoTipoDocumentoCabecera(String stoTipoDocumentoCabecera) {
		this.stoTipoDocumentoCabecera = stoTipoDocumentoCabecera;
	}

	/**
	 * @return the stoTIpoDocumentoDetalle
	 */
	public String getStoTIpoDocumentoDetalle() {
		return stoTIpoDocumentoDetalle;
	}

	/**
	 * @param stoTIpoDocumentoDetalle the stoTIpoDocumentoDetalle to set
	 */
	public void setStoTIpoDocumentoDetalle(String stoTIpoDocumentoDetalle) {
		this.stoTIpoDocumentoDetalle = stoTIpoDocumentoDetalle;
	}

	/**
	 * @return the stoTipoDocumentoTodos
	 */
	public String getStoTipoDocumentoTodos() {
		return stoTipoDocumentoTodos;
	}

	/**
	 * @param stoTipoDocumentoTodos the stoTipoDocumentoTodos to set
	 */
	public void setStoTipoDocumentoTodos(String stoTipoDocumentoTodos) {
		this.stoTipoDocumentoTodos = stoTipoDocumentoTodos;
	}
}