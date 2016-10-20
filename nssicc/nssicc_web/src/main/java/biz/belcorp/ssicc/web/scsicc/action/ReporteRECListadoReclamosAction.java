package biz.belcorp.ssicc.web.scsicc.action;

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

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Cliente;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteRECListadoReclamosForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaConsultoraPOPUPSearchAction;

@ManagedBean
@SessionScoped
public class ReporteRECListadoReclamosAction extends
		BaseReporteAbstractAction {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8749203088373453740L;
	private String formatoReporte;
	private String tipoMovimiento;
	private List siccMarcaList;
	private List siccUnidadNegocioList;
	private List siccEstadoOperacionList;
	private List siccEstadoReclamosList;
	private List siccNegocioList;
	private List siccRegionList;
	private List siccOperacionesList;
	private LabelValue[] siccTipoOperacionList={};
	private LabelValue[] siccZonaList={};
	private LabelValue[] siccTerritorioList={};
	private static final String POPUP_CONSULTORA = "CONSULTORA";
	private boolean mostrarPopupConsultora;
	
	@ManagedProperty(value="#{busquedaConsultoraPOPUPSearchAction}")
	private BusquedaConsultoraPOPUPSearchAction busquedaConsultoraPOPUPSearchAction;

	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopupHipCliente' method");
		}
		if (accion.equals(this.POPUP_CONSULTORA)) {
			this.busquedaConsultoraPOPUPSearchAction.verificarRegistro(event);
			if (this.busquedaConsultoraPOPUPSearchAction.isSeleccionoRegistro()) {
				Cliente cliente= (Cliente)this.busquedaConsultoraPOPUPSearchAction.getBeanRegistroSeleccionado(); 
				ReporteRECListadoReclamosForm f = (ReporteRECListadoReclamosForm) this.formReporte;
				f.setCodigoConsultora(cliente.getCodigo());
				this.busquedaConsultoraPOPUPSearchAction.setBeanRegistroSeleccionado(null);
			}
		}
	}

	
	@Override
	protected void setSalirPopup() {
		this.mostrarPopupConsultora = false;
		this.busquedaConsultoraPOPUPSearchAction.setBeanRegistroSeleccionado(null);
	}
	
	@Override
	protected void setInvocarPopup(String accion) {
		this.mostrarProcesoBatch = false;
		if (accion.equals(this.POPUP_CONSULTORA)){ 
			this.mostrarPopupConsultora = true;
		}
	}
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteRECListadoReclamosForm reporteForm = new ReporteRECListadoReclamosForm();

		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReporteRECEstadisticaRecDetalladoAction - setViewAtributes");
		}

		this.mostrarReporteXLS = true;
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteRECListadoReclamosForm f = (ReporteRECListadoReclamosForm) this.formReporte;
		Map criteriaOperacion = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		criteriaOperacion.put("codigoPais", pais.getCodigo());

		this.siccMarcaList = reporteService.getListaGenerico("getMarcaProdu",
				criteriaOperacion);
		this.siccUnidadNegocioList = reporteService.getListaGenerico(
				"getListaUnidadNegocio", criteriaOperacion);
		this.siccEstadoOperacionList = reporteService.getListaGenerico(
				"getListaEstadoOperacion", criteriaOperacion);
		this.siccEstadoReclamosList = reporteService.getListaGenerico(
				"getListaEstadoReclamo", criteriaOperacion);
		this.siccNegocioList = reporteService.getListaGenerico(
				"getListaNegocio", criteriaOperacion);
		this.siccRegionList = reporteService.getListaGenerico(
				"getRegionesByPais", criteriaOperacion);
		this.siccOperacionesList = interfazSiCCService
				.getOperacionesByCodigoPais(criteriaOperacion);

		f.setOidIdiomaIso(usuario.getIdioma().getCodigoISO());

		log.debug("Todo OK: Redireccionando");

	}

	protected String devuelveBeanReporteService() {
		
		return "reportes.reporteRECListadoReclamosService";
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteRECListadoReclamosForm form = (ReporteRECListadoReclamosForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(formReporte.getFormatoExportacion()))
			return "reporteRECListadoReclamosXLS";
		else
			return "reporteMaestroHorizontal";
	
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteRECListadoReclamosForm form = (ReporteRECListadoReclamosForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("PDF".equals(formReporte.getFormatoExportacion()))
			return "reporteRECListadoReclamosPDF";
		else
			return "";

	}

	public String setValidarReporte() {
		ReporteRECListadoReclamosForm form = (ReporteRECListadoReclamosForm) this.formReporte;
		int codperini = Integer.parseInt(form.getCodigoPeriodoInicial());
		int codperfin = Integer.parseInt(form.getCodigoPeriodoFinal());
		if (codperfin < codperini) {
			String mensaje = "El Periodo Inicial debe ser mayor o igual al Periodo Final";
			return mensaje;
		} 
			return null;

	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("prepareParameterMap...");
		}
		ReporteRECListadoReclamosForm reporteRECForm = (ReporteRECListadoReclamosForm) this.formReporte;
		formatoReporte = reporteRECForm.getFormatoExportacion();
		tipoMovimiento = reporteRECForm.getTipoMovimiento();
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteria = params;
		if (StringUtils.equals(reporteRECForm.getTipoPeriodo(), "0")) {
			params.put("periodoReferenciaInicial", reporteRECForm
					.getCodigoPeriodoInicial());
			params.put("periodoReferenciaFinal", reporteRECForm
					.getCodigoPeriodoFinal());

			criteria.put("codigoPeriodo", reporteRECForm
					.getCodigoPeriodoInicial());
			params.put("oidPeriodoReferenciaInicial", reporteService
					.getOidString("getOidPeriodoByCodigoPeriodo", criteria));
			criteria.put("codigoPeriodo", reporteRECForm
					.getCodigoPeriodoFinal());
			params.put("oidPeriodoReferenciaFinal", reporteService
					.getOidString("getOidPeriodoByCodigoPeriodo", criteria));

			params.put("periodoRegistroInicial", null);
			params.put("periodoRegistroFinal", null);
		} else {
			params.put("periodoRegistroInicial", reporteRECForm
					.getCodigoPeriodoInicial());
			params.put("periodoRegistroFinal", reporteRECForm
					.getCodigoPeriodoFinal());

			criteria.put("codigoPeriodo", reporteRECForm
					.getCodigoPeriodoInicial());
			params.put("oidPeriodoRegistroInicial", reporteService
					.getOidString("getOidPeriodoByCodigoPeriodo", criteria));
			criteria.put("codigoPeriodo", reporteRECForm
					.getCodigoPeriodoFinal());
			params.put("oidPeriodoRegistroFinal", reporteService.getOidString(
					"getOidPeriodoByCodigoPeriodo", criteria));

			params.put("periodoReferenciaInicial", null);
			params.put("periodoReferenciaFinal", null);
		}
		params.put("codigoConsultora", reporteRECForm.getCodigoConsultora());
		Map criteria2 = new HashMap();
		criteria2.put("codigoCliente", reporteRECForm.getCodigoConsultora());
		String oidConsultora = null;
		if (StringUtils.isNotEmpty(reporteRECForm.getCodigoConsultora())) {
			try {
				oidConsultora = reporteService.getOidString(
						"getOidClienteByCodigoCliente", criteria2);
			} catch (Exception e) {
				ActionMessages errors = new ActionMessages();
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"reporteRECListadoReclamosForm.notfound"));
			}
		}
		params.put("oidConsultora", oidConsultora);
		params.put("codigoConsultora", reporteRECForm.getCodigoConsultora());
		params.put("codigoOperacion",
				(reporteRECForm.getCodigoOperacion() == null) ? new ArrayList()
						: Arrays.asList(reporteRECForm.getCodigoOperacion()));
		params.put("negocioList",
				(reporteRECForm.getNegocioList() == null) ? new ArrayList()
						: Arrays.asList(reporteRECForm.getNegocioList()));
		params
				.put("unidadNegocioList", (reporteRECForm
						.getUnidadNegocioList() == null) ? new ArrayList()
						: Arrays.asList(reporteRECForm.getUnidadNegocioList()));
		params.put("territorioList",
				(reporteRECForm.getTerritorioList() == null) ? new ArrayList()
						: Arrays.asList(reporteRECForm.getTerritorioList()));
		params
				.put("tipoOperacionList", (reporteRECForm
						.getTipoOperacionList() == null) ? new ArrayList()
						: Arrays.asList(reporteRECForm.getTipoOperacionList()));
		params.put("marcaList",
				(reporteRECForm.getMarcaList() == null) ? new ArrayList()
						: Arrays.asList(reporteRECForm.getMarcaList()));
		params.put("zonaList",
				(reporteRECForm.getZonaList() == null) ? new ArrayList()
						: Arrays.asList(reporteRECForm.getZonaList()));
		params.put("regionList",
				(reporteRECForm.getRegionList() == null) ? new ArrayList()
						: Arrays.asList(reporteRECForm.getRegionList()));		
		params.put("NroReporte", getResourceMessage(
				"reporteRECListadoReclamosForm.numero.reporte"));
		params.put("titulo", getResourceMessage(
				"reporteRECListadoReclamosForm.titulo"));
		params.put("listaOperaciones", obtieneLista(reporteRECForm.getTipoOperacionList()));
		params.put("tipoMovimiento", tipoMovimiento);
	
		return params;
	}
	
	private String obtieneLista(String[] tipoOperacionList) {
		if (tipoOperacionList == null) return "";
		String lista = "";
		for (int i = 0; i < tipoOperacionList.length; i++) {
			lista+=" "+ tipoOperacionList[i];
		}
		return lista;
	}

	/**
	 * Metodo para obtener Lista de Zonas
	 * 
	 * @param val
	 */
	public void loadzonas(ValueChangeEvent val) {
		log.debug("loadzonas");
		
		String[] valores = (String[]) val.getNewValue();
		if (valores.length > 0) {
			String[] regiones = (String[]) val.getNewValue();

			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			this.siccZonaList = aSvc
					.getZonasMultipleByPaisMarcaCanalRegion(
							this.mPantallaPrincipalBean.getCurrentCountry()
									.getCodigo(),
							Constants.CODIGO_MARCA_DEFAULT,
							Constants.CODIGO_CANAL_DEFAULT, regiones,
							Constants.FORMATO_TOTAL);

		} else {
			setSiccZonaList(null);
			setSiccTerritorioList(null);

		}

	}

	/**
	 * Metodo para obtener Lista de Territorios
	 * 
	 * @param val
	 */
	public void loadterritorio(ValueChangeEvent val) {
		log.debug("loadterritorio");
		
		if (val == null) return;
		ReporteRECListadoReclamosForm form = (ReporteRECListadoReclamosForm) this.formReporte;
		String[] regiones = (String[]) form.getRegionList();
		String[] zonas = (String[]) val.getNewValue();
		List<String> listaRegiones = 
			     new ArrayList<String>(Arrays.asList(regiones));
		List<String> listaZonas = 
			     new ArrayList<String>(Arrays.asList(zonas));
		if (regiones.length > 0 && zonas.length > 0) {
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			setSiccTerritorioList(aSvc
					.getTerritoriosMultipleByPaisMarcaCanalRegionZona(
							this.mPantallaPrincipalBean.getCurrentCountry()
									.getCodigo(),
							Constants.CODIGO_MARCA_DEFAULT,
							Constants.CODIGO_CANAL_DEFAULT, listaRegiones, listaZonas,
							Constants.FORMATO_TOTAL));

		} else {
			setSiccTerritorioList(null);

		}

	}



	public void loadTipoOperacion(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadTipoOperacion");
		}
		String[] valores = (String[]) val.getNewValue();
	
		
		if (valores.length > 0) {
			ArrayList<String> listaValores = 
				    new ArrayList<String>(Arrays.asList(valores));
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			setSiccTipoOperacionList(ajax
					.getTiposOperaMultipleByOpera(this.mPantallaPrincipalBean
							.getCurrentCountry().getCodigo(), listaValores, Constants.FORMATEAR_TODOS));
			
		}


	}

	/**
	 * @return
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	/**
	 * @return the siccMarcaList
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList the siccMarcaList to set
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * @return the siccUnidadNegocioList
	 */
	public List getSiccUnidadNegocioList() {
		return siccUnidadNegocioList;
	}

	/**
	 * @param siccUnidadNegocioList the siccUnidadNegocioList to set
	 */
	public void setSiccUnidadNegocioList(List siccUnidadNegocioList) {
		this.siccUnidadNegocioList = siccUnidadNegocioList;
	}

	/**
	 * @return the siccEstadoOperacionList
	 */
	public List getSiccEstadoOperacionList() {
		return siccEstadoOperacionList;
	}

	/**
	 * @param siccEstadoOperacionList the siccEstadoOperacionList to set
	 */
	public void setSiccEstadoOperacionList(List siccEstadoOperacionList) {
		this.siccEstadoOperacionList = siccEstadoOperacionList;
	}

	/**
	 * @return the siccEstadoReclamosList
	 */
	public List getSiccEstadoReclamosList() {
		return siccEstadoReclamosList;
	}

	/**
	 * @param siccEstadoReclamosList the siccEstadoReclamosList to set
	 */
	public void setSiccEstadoReclamosList(List siccEstadoReclamosList) {
		this.siccEstadoReclamosList = siccEstadoReclamosList;
	}

	/**
	 * @return the siccNegocioList
	 */
	public List getSiccNegocioList() {
		return siccNegocioList;
	}

	/**
	 * @param siccNegocioList the siccNegocioList to set
	 */
	public void setSiccNegocioList(List siccNegocioList) {
		this.siccNegocioList = siccNegocioList;
	}

	/**
	 * @return the siccRegionList
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList the siccRegionList to set
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccOperacionesList
	 */
	public List getSiccOperacionesList() {
		return siccOperacionesList;
	}

	/**
	 * @param siccOperacionesList the siccOperacionesList to set
	 */
	public void setSiccOperacionesList(List siccOperacionesList) {
		this.siccOperacionesList = siccOperacionesList;
	}

	/**
	 * @return the siccTipoOperacionList
	 */
	public LabelValue[] getSiccTipoOperacionList() {
		return siccTipoOperacionList;
	}

	/**
	 * @param siccTipoOperacionList the siccTipoOperacionList to set
	 */
	public void setSiccTipoOperacionList(LabelValue[] siccTipoOperacionList) {
		this.siccTipoOperacionList = siccTipoOperacionList;
	}

	/**
	 * @return the siccZonaList
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the siccTerritorioList
	 */
	public LabelValue[] getSiccTerritorioList() {
		return siccTerritorioList;
	}

	/**
	 * @param siccTerritorioList the siccTerritorioList to set
	 */
	public void setSiccTerritorioList(LabelValue[] siccTerritorioList) {
		this.siccTerritorioList = siccTerritorioList;
	}

	/**
	 * @return the tipoMovimiento
	 */
	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	/**
	 * @param tipoMovimiento the tipoMovimiento to set
	 */
	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	/**
	 * @return the mostrarPopupConsultora
	 */
	public boolean isMostrarPopupConsultora() {
		return mostrarPopupConsultora;
	}

	/**
	 * @param mostrarPopupConsultora the mostrarPopupConsultora to set
	 */
	public void setMostrarPopupConsultora(boolean mostrarPopupConsultora) {
		this.mostrarPopupConsultora = mostrarPopupConsultora;
	}

	/**
	 * @return the busquedaConsultoraPOPUPSearchAction
	 */
	public BusquedaConsultoraPOPUPSearchAction getBusquedaConsultoraPOPUPSearchAction() {
		return busquedaConsultoraPOPUPSearchAction;
	}

	/**
	 * @param busquedaConsultoraPOPUPSearchAction the busquedaConsultoraPOPUPSearchAction to set
	 */
	public void setBusquedaConsultoraPOPUPSearchAction(
			BusquedaConsultoraPOPUPSearchAction busquedaConsultoraPOPUPSearchAction) {
		this.busquedaConsultoraPOPUPSearchAction = busquedaConsultoraPOPUPSearchAction;
	}

	


	
	
	

	
	
	

}

