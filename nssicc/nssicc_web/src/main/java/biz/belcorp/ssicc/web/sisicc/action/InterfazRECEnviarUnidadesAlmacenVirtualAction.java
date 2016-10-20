package biz.belcorp.ssicc.web.sisicc.action;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.primefaces.event.data.PageEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.sisicc.InterfazRECService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.scsicc.action.ReporteRECEnviarUnidadesAlmacenVirtualCabeceraAction;
import biz.belcorp.ssicc.web.scsicc.action.ReporteRECEnviarUnidadesAlmacenVirtualZonaAction;
import biz.belcorp.ssicc.web.sisicc.form.InterfazRECEnviarUnidadesAlmacenVirtualForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class InterfazRECEnviarUnidadesAlmacenVirtualAction extends
		BaseInterfazAbstractAction {

	private static final long serialVersionUID = 4244216716479325587L;

	private Integer totalGuias = 0;
	private Integer totalunidadesReclamadas = 0;
	private Integer subTotalGuias = 0;
	private Integer subTotalunidadesReclamadas = 0;
	private boolean mostrarToolbarAdicional;
	private Integer pagina;
	private List listRecEnviarUnidadesAlmacen;
	private Boolean mostrarDatatable;
	private DataTableModel dmRecEnviarUnidades;

	@ManagedProperty(value = "#{reporteRECEnviarUnidadesAlmacenVirtualCabeceraAction}")
	private ReporteRECEnviarUnidadesAlmacenVirtualCabeceraAction reporteRECEnviarUnidadesAlmacenVirtualCabecera;

	@ManagedProperty(value = "#{reporteRECEnviarUnidadesAlmacenVirtualZonaAction}")
	private ReporteRECEnviarUnidadesAlmacenVirtualZonaAction reporteRECEnviarUnidadesAlmacenVirtualZona;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction
	 * #devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		return new InterfazRECEnviarUnidadesAlmacenVirtualForm();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		this.mostrarBotonBuscar = true;
		this.mostrarListaBusqueda = false;
		setMostrarToolbarAdicional(true);
//		this.mostrar
		this.mostrarDatatable = false;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

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

		InterfazRECEnviarUnidadesAlmacenVirtualForm f = (InterfazRECEnviarUnidadesAlmacenVirtualForm) this.formInterfaz;
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());

	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseInterfazAbstractAction#afterExecuteInterfaz(java.util.Map,
	 *      biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazExecutionResult)
	 */
	protected void afterExecuteInterfaz(Map params,InterfazExecutionResult interfazExecutionResult) throws Exception {
		super.afterExecuteInterfaz(params, interfazExecutionResult);
		if (log.isDebugEnabled()) {
			log.debug("Dentro del metodo 'afterExecuteInterfaz'");
		}
		// Validamos el resultado de la ejecucion
		
		InterfazRECService svc = (InterfazRECService) getBean("sisicc.interfazRECService");
		svc.updateInterfazRECEnviarUnidadesAlmacenVirtualExitosa(params);
		
	}

	
	/**
	 * Recargando valores totales y subtotales
	 */
	public void recargarValoresTotales(){
		this.subTotalGuias = 0;
		this.subTotalunidadesReclamadas = 0;
		this.totalGuias = 0;
		this.totalunidadesReclamadas = 0;
		this.mostrarDatatable = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction
	 * #setFindAttributes()
	 */
	protected List setFindAttributes() throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}
		this.recargarValoresTotales();

		InterfazRECEnviarUnidadesAlmacenVirtualForm f = (InterfazRECEnviarUnidadesAlmacenVirtualForm) this.formInterfaz;
		Map criteria = BeanUtils.describe(f);

		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		List listUnidadesAlmacenVirtual = interfazSiCCService
				.getRECConsolidadoUnidadesAlmacenVirtual(criteria);
		this.listRecEnviarUnidadesAlmacen = listUnidadesAlmacenVirtual;

		if (CollectionUtils.isEmpty(listUnidadesAlmacenVirtual)) {
			setMostrarToolbarAdicional(false);
		} else {
			setMostrarToolbarAdicional(true);
		}
		int tamanioLista = listUnidadesAlmacenVirtual.size();
		
		if(tamanioLista > 0){
			
			this.mostrarDatatable = true;
			
			//CALCULANDO EL TOTAL
			
			for (Object obj : listUnidadesAlmacenVirtual) {
				Map map = (Map) obj;
				BigDecimal totalGuia = (BigDecimal) map.get("totalGuias");
				BigDecimal unidadesReclamadas = (BigDecimal) map
						.get("unidadesReclamadas");
				this.totalGuias += totalGuia.intValue();
				this.totalunidadesReclamadas += unidadesReclamadas.intValue();
			}
			
//			CALCULANDO EL SUBTOTAL
			
			int pagina = 0;
			int valorInicial = 0;
			int valorFinal = 0;
			int numeroFilas = 10;
			valorFinal = valorInicial + numeroFilas;
			
			if(tamanioLista <= 9){				
				for (int i = 0; i < tamanioLista; i++) {
					Map map = (Map) listUnidadesAlmacenVirtual.get(i);
					BigDecimal totalGuia = (BigDecimal) map.get("totalGuias");
					BigDecimal unidadesReclamadas = (BigDecimal) map
							.get("unidadesReclamadas");
					this.subTotalGuias += totalGuia.intValue();
					this.subTotalunidadesReclamadas += unidadesReclamadas.intValue();
				}
					
				
			}else{
				for (int i = 0; i < valorFinal - 1; i++) {
					Map map = (Map) this.listRecEnviarUnidadesAlmacen.get(i);
					BigDecimal totalGuia = (BigDecimal) map.get("totalGuias");
					BigDecimal unidadesReclamadas = (BigDecimal) map
							.get("unidadesReclamadas");
					this.subTotalGuias += totalGuia.intValue();
					this.subTotalunidadesReclamadas += unidadesReclamadas.intValue();
				}
			}
			
			// Seteamos los valores para pasarlos a los reportes
			this.reporteRECEnviarUnidadesAlmacenVirtualCabecera
					.setMapProperties(criteria);
			this.reporteRECEnviarUnidadesAlmacenVirtualZona
					.setMapProperties(criteria);
			
		}
		this.dmRecEnviarUnidades = new DataTableModel(this.listRecEnviarUnidadesAlmacen);
		return listUnidadesAlmacenVirtual;
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
		
		// capturando la ultima pagina
		int ultimapagina = 0;
		boolean ultimaP = false;
		int ultimo = (this.listRecEnviarUnidadesAlmacen.size() / 10) + 1;
		int residuoUltimo = (this.listRecEnviarUnidadesAlmacen.size() % 10) + valorInicial;
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
			if (this.listRecEnviarUnidadesAlmacen.size() <= 9) {

				for (int i = 0; i < this.listRecEnviarUnidadesAlmacen.size(); i++) {

					Map map = (Map) this.listRecEnviarUnidadesAlmacen.get(i);
					BigDecimal totalGuia = (BigDecimal) map.get("totalGuias");
					BigDecimal unidadesReclamadas = (BigDecimal) map
							.get("unidadesReclamadas");
					this.subTotalGuias += totalGuia.intValue();
					this.subTotalunidadesReclamadas += unidadesReclamadas.intValue();
				}
			}
			// si es igual al pagino, calcular subtotal
			else {

				for (int i = 0; i <= 9; i++) {

					Map map = (Map) this.listRecEnviarUnidadesAlmacen.get(i);
					BigDecimal totalGuia = (BigDecimal) map.get("totalGuias");
					BigDecimal unidadesReclamadas = (BigDecimal) map
							.get("unidadesReclamadas");
					this.subTotalGuias += totalGuia.intValue();
					this.subTotalunidadesReclamadas += unidadesReclamadas.intValue();
				}

			}

		} 
		//validando que sea la ultima pagina, y que tenga residuo
		else if (ultimaP && residuoUltimo != 0) {
			for (int i = valorInicial; i <= residuoUltimo - 1; i++) {
				Map map = (Map) this.listRecEnviarUnidadesAlmacen.get(i);
				BigDecimal totalGuia = (BigDecimal) map.get("totalGuias");
				BigDecimal unidadesReclamadas = (BigDecimal) map
						.get("unidadesReclamadas");
				this.subTotalGuias += totalGuia.intValue();
				this.subTotalunidadesReclamadas += unidadesReclamadas.intValue();
			}
		}

		// si es una lista normal con 10 registro entrara aca.
		else {
			for (int i = valorInicial; i <= valorFinal - 1; i++) {
				Map map = (Map) this.listRecEnviarUnidadesAlmacen.get(i);
				BigDecimal totalGuia = (BigDecimal) map.get("totalGuias");
				BigDecimal unidadesReclamadas = (BigDecimal) map
						.get("unidadesReclamadas");
				this.subTotalGuias += totalGuia.intValue();
				this.subTotalunidadesReclamadas += unidadesReclamadas.intValue();
			}
		}
	}

	/**
	 * @param actionEvent
	 * @throws Exception
	 */
	public void generarReporteCabecera(ActionEvent actionEvent)
			throws Exception {
		this.reporteRECEnviarUnidadesAlmacenVirtualCabecera
				.setFormatoExportacion("XLS");
		this.reporteRECEnviarUnidadesAlmacenVirtualCabecera.executeReporte();
	}

	/**
	 * @param actionEvent
	 * @throws Exception
	 */
	public void generarReporteZona(ActionEvent actionEvent) throws Exception {
		this.reporteRECEnviarUnidadesAlmacenVirtualCabecera
				.setFormatoExportacion("PDF");
		this.reporteRECEnviarUnidadesAlmacenVirtualZona.executeReporte();
		this.redireccionarPagina("reporteRECEnviarUnidadesAlmacenVirtualZonaForm");
	}

	/**
	 * @return the totalGuias
	 */
	public Integer getTotalGuias() {
		return totalGuias;
	}

	/**
	 * @param totalGuias
	 *            the totalGuias to set
	 */
	public void setTotalGuias(Integer totalGuias) {
		this.totalGuias = totalGuias;
	}

	/**
	 * @return the totalunidadesReclamadas
	 */
	public Integer getTotalunidadesReclamadas() {
		return totalunidadesReclamadas;
	}

	/**
	 * @param totalunidadesReclamadas
	 *            the totalunidadesReclamadas to set
	 */
	public void setTotalunidadesReclamadas(Integer totalunidadesReclamadas) {
		this.totalunidadesReclamadas = totalunidadesReclamadas;
	}

	/**
	 * @return the reporteRECEnviarUnidadesAlmacenVirtualCabecera
	 */
	public ReporteRECEnviarUnidadesAlmacenVirtualCabeceraAction getReporteRECEnviarUnidadesAlmacenVirtualCabecera() {
		return reporteRECEnviarUnidadesAlmacenVirtualCabecera;
	}

	/**
	 * @param reporteRECEnviarUnidadesAlmacenVirtualCabecera
	 *            the reporteRECEnviarUnidadesAlmacenVirtualCabecera to set
	 */
	public void setReporteRECEnviarUnidadesAlmacenVirtualCabecera(
			ReporteRECEnviarUnidadesAlmacenVirtualCabeceraAction reporteRECEnviarUnidadesAlmacenVirtualCabecera) {
		this.reporteRECEnviarUnidadesAlmacenVirtualCabecera = reporteRECEnviarUnidadesAlmacenVirtualCabecera;
	}

	/**
	 * @return the reporteRECEnviarUnidadesAlmacenVirtualZona
	 */
	public ReporteRECEnviarUnidadesAlmacenVirtualZonaAction getReporteRECEnviarUnidadesAlmacenVirtualZona() {
		return reporteRECEnviarUnidadesAlmacenVirtualZona;
	}

	/**
	 * @param reporteRECEnviarUnidadesAlmacenVirtualZona
	 *            the reporteRECEnviarUnidadesAlmacenVirtualZona to set
	 */
	public void setReporteRECEnviarUnidadesAlmacenVirtualZona(
			ReporteRECEnviarUnidadesAlmacenVirtualZonaAction reporteRECEnviarUnidadesAlmacenVirtualZona) {
		this.reporteRECEnviarUnidadesAlmacenVirtualZona = reporteRECEnviarUnidadesAlmacenVirtualZona;
	}

	/**
	 * @return the mostrarToolbarAdicional
	 */
	public boolean isMostrarToolbarAdicional() {
		return mostrarToolbarAdicional;
	}

	/**
	 * @param mostrarToolbarAdicional
	 *            the mostrarToolbarAdicional to set
	 */
	public void setMostrarToolbarAdicional(boolean mostrarToolbarAdicional) {
		this.mostrarToolbarAdicional = mostrarToolbarAdicional;
	}

	/**
	 * @return the subTotalGuias
	 */
	public Integer getSubTotalGuias() {
		return subTotalGuias;
	}

	/**
	 * @param subTotalGuias the subTotalGuias to set
	 */
	public void setSubTotalGuias(Integer subTotalGuias) {
		this.subTotalGuias = subTotalGuias;
	}

	/**
	 * @return the subTotalunidadesReclamadas
	 */
	public Integer getSubTotalunidadesReclamadas() {
		return subTotalunidadesReclamadas;
	}

	/**
	 * @param subTotalunidadesReclamadas the subTotalunidadesReclamadas to set
	 */
	public void setSubTotalunidadesReclamadas(Integer subTotalunidadesReclamadas) {
		this.subTotalunidadesReclamadas = subTotalunidadesReclamadas;
	}

	/**
	 * @return the pagina
	 */
	public Integer getPagina() {
		return pagina;
	}

	/**
	 * @param pagina the pagina to set
	 */
	public void setPagina(Integer pagina) {
		this.pagina = pagina;
	}

	/**
	 * @return the listRecEnviarUnidadesAlmacen
	 */
	public List getListRecEnviarUnidadesAlmacen() {
		return listRecEnviarUnidadesAlmacen;
	}

	/**
	 * @param listRecEnviarUnidadesAlmacen the listRecEnviarUnidadesAlmacen to set
	 */
	public void setListRecEnviarUnidadesAlmacen(List listRecEnviarUnidadesAlmacen) {
		this.listRecEnviarUnidadesAlmacen = listRecEnviarUnidadesAlmacen;
	}

	/**
	 * @return the mostrarDatatable
	 */
	public Boolean getMostrarDatatable() {
		return mostrarDatatable;
	}

	/**
	 * @param mostrarDatatable the mostrarDatatable to set
	 */
	public void setMostrarDatatable(Boolean mostrarDatatable) {
		this.mostrarDatatable = mostrarDatatable;
	}

	/**
	 * @return the dmRecEnviarUnidades
	 */
	public DataTableModel getDmRecEnviarUnidades() {
		return dmRecEnviarUnidades;
	}

	/**
	 * @param dmRecEnviarUnidades the dmRecEnviarUnidades to set
	 */
	public void setDmRecEnviarUnidades(DataTableModel dmRecEnviarUnidades) {
		this.dmRecEnviarUnidades = dmRecEnviarUnidades;
	}
	
	
}