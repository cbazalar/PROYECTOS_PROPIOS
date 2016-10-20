package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ConsultaCCCGenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReportePERConsolidadoPagosBancariosPorFechaProcesoForm;

@ManagedBean
@SessionScoped
public class ReportePERConsolidadoPagosBancariosPorFechaProcesoAction extends
		BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6841250864214074144L;
	private String formatoReporte;
	private List siccSociedadList;
	private List cccTiposLoteBancarioList;
	private List siccBancoList;
	private List siccRegionList;
	private LabelValue[] siccZonaList = {};
	private String tipoVista;
	private LabelValue[] siccCuentaCorrienteList;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReportePERConsolidadoPagosBancariosPorFechaProcesoForm reporteForm = new ReportePERConsolidadoPagosBancariosPorFechaProcesoForm();
		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'view' method");
		}
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;

		// Obtenemos el Pais
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		// Carga de lista para cargar los combos en el JSP
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		// La constante SICC_SOCIEDAD_LIST almacena la lista de Sociedades por
		// Paiss
		this.siccSociedadList = service.getSociedadesByCodigoPais(pais
				.getCodigo());

		// Map para almacenar los parametros
		Map criteria = new HashMap();

		criteria.put("codigoPais", pais.getCodigo());

		ConsultaCCCGenericoService serviceGenericoCCC = (ConsultaCCCGenericoService) getBean("spusicc.consultaCCCGenericoService");

		// Obtener los Estados de Los Lotes
		this.cccTiposLoteBancarioList = serviceGenericoCCC
				.getTiposLoteBancarioList();

		// Lista de Banco para el Pais
		this.siccBancoList = service.getBancosByPais(criteria);

		this.siccRegionList = reporteService.getListaGenerico(
				"getRegionesByPais", criteria);

		if (log.isDebugEnabled()) {
			log.debug("JFA Finalizando 'view' method");
		}

	}

	public String setValidarReporte() {
		ReportePERConsolidadoPagosBancariosPorFechaProcesoForm form = (ReportePERConsolidadoPagosBancariosPorFechaProcesoForm) this.formReporte;
		if (form.getFechaProcHastaD() != null
				&& form.getFechaProcDesdeD() != null) {
			if (form.getFechaProcHastaD().compareTo(form.getFechaProcDesdeD()) < 0) {
				String mensaje = this
						.getResourceMessage("reportePERConsolidadoPagosBancariosPorFechaProcesoForm.errors.compare.fechaProc");
				return mensaje;
			}
		}

		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'prepareParameterMap' method");
		}
		ReportePERConsolidadoPagosBancariosPorFechaProcesoForm reportePERForm = (ReportePERConsolidadoPagosBancariosPorFechaProcesoForm) this.formReporte;

		this.tipoVista = reportePERForm.getTipoVista();

		String fechaIni = DateUtil.convertDateToString(reportePERForm
				.getFechaProcDesdeD());
		String fechaFin = DateUtil.convertDateToString(reportePERForm
				.getFechaProcHastaD());

		params.put("fechaProcDesde", fechaIni);
		params.put("fechaProcHasta", fechaFin);
		String condicionZonas = obtieneCondicion(reportePERForm.getZonaList(),
				"COD_ZONA", "'");
		String condicionRegion = obtieneCondicion(
				reportePERForm.getRegionList(), "COD_REGI", "'");

		String condicion = condicionZonas + condicionRegion;

		params.put("condicion", condicion);

		log.debug(" Imprimiendo parámetros");
		log.debug(params);
		log.debug("Fin parámetros");

		return params;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reportePERConsolidadoPagosBancariosPorFechaProceso" + tipoVista
				+ "XLS";

	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}

	public void loadZonas(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadZonas");
		}
		String[] valor = (String[]) val.getNewValue();
		if (valor.length > 0) {
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			this.siccZonaList = ajax
					.getZonasMultipleByPaisMarcaCanalRegion(
							this.mPantallaPrincipalBean.getCurrentCountry()
									.getCodigo(),
							Constants.CODIGO_MARCA_DEFAULT,
							Constants.CODIGO_CANAL_DEFAULT, valor,
							Constants.FORMATEAR_TODOS);

		} else {

			setSiccZonaList(null);
		}

	}

	public void loadCuentaCorriente(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadCuentaCorriente");
		}
		ReportePERConsolidadoPagosBancariosPorFechaProcesoForm reportePERForm = (ReportePERConsolidadoPagosBancariosPorFechaProcesoForm) this.formReporte;

		String valor = (String) val.getNewValue();
		if (valor.length() > 0) {
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			setSiccCuentaCorrienteList(ajax
					.getCuentasCorrientesPorPaisSociedadBanco(
							this.mPantallaPrincipalBean.getCurrentCountry()
									.getCodigo(), reportePERForm
									.getCodigoSociedad(),valor));

		} else {

			setSiccCuentaCorrienteList(null);
		}

	}

	/**
	 * @return the formatoReporte
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte
	 *            the formatoReporte to set
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	/**
	 * @return the siccSociedadList
	 */
	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	/**
	 * @param siccSociedadList
	 *            the siccSociedadList to set
	 */
	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}

	/**
	 * @return the cccTiposLoteBancarioList
	 */
	public List getCccTiposLoteBancarioList() {
		return cccTiposLoteBancarioList;
	}

	/**
	 * @param cccTiposLoteBancarioList
	 *            the cccTiposLoteBancarioList to set
	 */
	public void setCccTiposLoteBancarioList(List cccTiposLoteBancarioList) {
		this.cccTiposLoteBancarioList = cccTiposLoteBancarioList;
	}

	/**
	 * @return the siccBancoList
	 */
	public List getSiccBancoList() {
		return siccBancoList;
	}

	/**
	 * @param siccBancoList
	 *            the siccBancoList to set
	 */
	public void setSiccBancoList(List siccBancoList) {
		this.siccBancoList = siccBancoList;
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
	 * @return the tipoVista
	 */
	public String getTipoVista() {
		return tipoVista;
	}

	/**
	 * @param tipoVista
	 *            the tipoVista to set
	 */
	public void setTipoVista(String tipoVista) {
		this.tipoVista = tipoVista;
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
	 * @return the siccCuentaCorrienteList
	 */
	public LabelValue[] getSiccCuentaCorrienteList() {
		return siccCuentaCorrienteList;
	}

	/**
	 * @param siccCuentaCorrienteList the siccCuentaCorrienteList to set
	 */
	public void setSiccCuentaCorrienteList(LabelValue[] siccCuentaCorrienteList) {
		this.siccCuentaCorrienteList = siccCuentaCorrienteList;
	}
	
	

}
