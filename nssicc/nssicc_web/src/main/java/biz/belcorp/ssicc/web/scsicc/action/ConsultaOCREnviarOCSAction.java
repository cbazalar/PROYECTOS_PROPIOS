package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.primefaces.event.data.PageEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ConsultaOCREnviarOCSForm;

/**
 * The Class ReporteOCRPedidosDigitadosAction.
 * 
 * @autor: Belcorp
 * @version: 1.0 22/08/2014
 */
@ManagedBean
@SessionScoped
public class ConsultaOCREnviarOCSAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Integer subTotal;
	private Integer total;
	private Integer pagina;
	private List listaTotales;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ConsultaOCREnviarOCSForm form = new ConsultaOCREnviarOCSForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("setFindAttributes");
		}
		ConsultaOCREnviarOCSForm form = (ConsultaOCREnviarOCSForm) this.formReporte;
		Map criteria = BeanUtils.describe(form);
		if (form.getResumen().equalsIgnoreCase("0")) {
			criteria.put("zona", null);
		} else if (form.getResumen().equalsIgnoreCase("1")) {
			criteria.put("zona", "SI");
		}

		// La busqueda solo la realizaremos en los sistemas activos
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");

		List lista = service.getOCREnviaOCSList(criteria);

		int tamanio = lista.size();
		if (tamanio > 0) {
			int sumaTotal = 0;

			// ConsultaPolizas obj
			for (int i = 0; i < tamanio; i++) {
				HashMap objAContar = (HashMap) lista.get(i);
				sumaTotal = sumaTotal
						+ Integer
								.parseInt(objAContar.get("pedidos").toString());

			}

			int valorPagina = 1;
			int filasMuestra = 10;
			int valorFinal = 0;
			valorFinal = valorPagina * filasMuestra;
			int valorInicial = 0;
			int sumaSubtotal = 0;

			for (int i = valorInicial; i <= valorFinal - 1; i++) {
				HashMap objAContar = (HashMap) lista.get(i);
				sumaSubtotal = sumaSubtotal
						+ Integer
								.parseInt(objAContar.get("pedidos").toString());
			}

			this.total = sumaTotal;
			this.subTotal = sumaSubtotal;
		}
		this.listaTotales = lista;
		return this.listaTotales;
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
		try {
			int valorPagina = this.pagina + 1;
			int filasMuestra = 10;
			int valorFinal = 0;
			valorFinal = valorPagina * filasMuestra;
			int valorInicial = 0;
			valorInicial = valorFinal - filasMuestra;
			// int sumaTotal = 0;
			int sumaSubtotal = 0;
			for (int i = valorInicial; i <= valorFinal - 1; i++) {
				HashMap objAContar = (HashMap) this.listaTotales.get(i);
				sumaSubtotal = sumaSubtotal
						+ Integer
								.parseInt(objAContar.get("pedidos").toString());
			}

			// this.total = sumaTotal;
			this.subTotal = sumaSubtotal;
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
		if (log.isDebugEnabled()) {
			log.debug("setViewAtributes");
		}
		this.mostrarListaBusqueda = false;
		this.mostrarBotonBuscar = true;
		this.mostrarReportePDF = false;
		this.total = 0;
		this.subTotal = 0;
		Map criteria = new HashMap();
		criteria.put("codigoPais", this.getmPantallaPrincipalBean()
				.getCurrentCountry().getCodigo());
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																// Campanha
																// Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																	// Campanha
																	// activa q
																	// se
																	// procesa
																	// actualmente

		ConsultaOCREnviarOCSForm form = (ConsultaOCREnviarOCSForm) this.formReporte;
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service
				.getControlFacturacionById(criteria);

		// Carga Fecha y Periodo
		form.setFechaFact(controlFacturacion.getFechaProceso());
		form.setPeriodo(controlFacturacion.getCodigoPeriodo());
		form.setCodigoPais(this.getmPantallaPrincipalBean().getCurrentCountry()
				.getCodigo());
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ConsultaOCREnviarOCSForm form = (ConsultaOCREnviarOCSForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		String valor = "";
		if ("XLS".equals(form.getFormatoExportacion())) {
			valor = "reporteMaestroHorizontal";
		}
		return valor;
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		return params;
	}

	/**
	 * @return the subTotal
	 */
	public Integer getSubTotal() {
		return subTotal;
	}

	/**
	 * @param subTotal
	 *            the subTotal to set
	 */
	public void setSubTotal(Integer subTotal) {
		this.subTotal = subTotal;
	}

	/**
	 * @return the total
	 */
	public Integer getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(Integer total) {
		this.total = total;
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
	 * @return the listaTotales
	 */
	public List getListaTotales() {
		return listaTotales;
	}

	/**
	 * @param listaTotales
	 *            the listaTotales to set
	 */
	public void setListaTotales(List listaTotales) {
		this.listaTotales = listaTotales;
	}

}
