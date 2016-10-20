package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCCCPagosPorRegularizarForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteCCCPagosPorRegularizarAction extends
		BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = 3847694719429935218L;
	private String tipoNombreReporte;

	private List siccSociedadList = new ArrayList();
	private List siccBancoList = new ArrayList();
	private List siccCuentaCorrienteList = new ArrayList();

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'view' method");
		}

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;

		// Obtenemos el Pais
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		// Carga de lista para cargar los combos en el JSP
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		// La constante SICC_SOCIEDAD_LIST almacena la lista de Sociedades por
		// Paiss

		this.siccSociedadList = service.getSociedadesByCodigoPais(pais
				.getCodigo());

		// Map para almacenar los parametros
		Map criteria = new HashMap();

		criteria.put("codigoPais", pais.getCodigo());

		// Lista de Banco para el Pais

		this.siccBancoList = service.getBancosByPais(criteria);

		ReporteCCCPagosPorRegularizarForm form = (ReporteCCCPagosPorRegularizarForm) this.formReporte;
		form.setCodigoPais(pais.getCodigo());

		// SETEANDO VALORES POR DEFAULT A FECHAS
		form.setFechaPagoDesdeD(new Date(System.currentTimeMillis()));

		form.setFechaPagoHastaD(new Date(System.currentTimeMillis()));

		form.setFechaProcDesdeD(new Date(System.currentTimeMillis()));

		form.setFechaProcHastaD(new Date(System.currentTimeMillis()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCCCPagosPorRegularizarForm form = new ReporteCCCPagosPorRegularizarForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteCCCPagosPorRegularizar" + tipoNombreReporte + "XLS";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteCCCPagosPorRegularizarForm form = (ReporteCCCPagosPorRegularizarForm) this.formReporte;
		Date fecha1D, fecha2D, fecha3D, fecha4D;

		fecha1D = form.getFechaPagoDesdeD();
		fecha2D = form.getFechaPagoHastaD();
		fecha3D = form.getFechaProcDesdeD();
		fecha4D = form.getFechaProcHastaD();

		if (fecha2D.compareTo(fecha1D) < 0) {
			String mensaje = this
					.getResourceMessage("reporteCCCLiquidacionCobranzasForm.errors.compare.fechaPago");
			return mensaje;
		}
		if (fecha4D.compareTo(fecha3D) < 0) {
			String mensaje = this
					.getResourceMessage("reporteCCCLiquidacionCobranzasForm.errors.compare.fechaProc");
			return mensaje;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'prepareParameterMap' method");
		}

		// Preparando los parametros
		ReporteCCCPagosPorRegularizarForm reporteCCCPagosPorRegularizarForm = (ReporteCCCPagosPorRegularizarForm) this.formReporte;

		String fecha1, fecha2, fecha3, fecha4;
		fecha1 = DateUtil.getDate(reporteCCCPagosPorRegularizarForm
				.getFechaPagoDesdeD());
		fecha2 = DateUtil.getDate(reporteCCCPagosPorRegularizarForm
				.getFechaPagoHastaD());
		fecha3 = DateUtil.getDate(reporteCCCPagosPorRegularizarForm
				.getFechaProcDesdeD());
		fecha4 = DateUtil.getDate(reporteCCCPagosPorRegularizarForm
				.getFechaProcHastaD());

		reporteCCCPagosPorRegularizarForm.setFechaPagoDesde(fecha1);
		reporteCCCPagosPorRegularizarForm.setFechaPagoHasta(fecha2);
		reporteCCCPagosPorRegularizarForm.setFechaProcDesde(fecha3);
		reporteCCCPagosPorRegularizarForm.setFechaProcHasta(fecha4);

		String codigoProceso = (Constants.RECAUDO_BANCARIO_PROCESO);
		String codigoSubproceso = "";

		if (reporteCCCPagosPorRegularizarForm.getTipoAbono().equalsIgnoreCase(
				Constants.TIPO_RECAUDO_BANCARIO_TODOS)) {
			codigoSubproceso = null;
		} else if (reporteCCCPagosPorRegularizarForm.getTipoAbono()
				.equalsIgnoreCase(Constants.TIPO_RECAUDO_BANCARIO_AUTOMATICO)) {
			codigoSubproceso = (Constants.RECAUDO_BANCARIO_AUTOMATICO_SUBPROCESO);
		} else {
			codigoSubproceso = (Constants.RECAUDO_BANCARIO_MANUAL_SUBPROCESO);
		}

		log.debug("Tipo Abono");
		log.debug(codigoProceso);
		log.debug(codigoSubproceso);

		this.tipoNombreReporte = reporteCCCPagosPorRegularizarForm
				.getTipoReporte()
				+ reporteCCCPagosPorRegularizarForm.getTipoVista();

		params.put("codigoProceso", codigoProceso);
		params.put("codigoSubproceso", codigoSubproceso);
		return params;
	}

	/**
	 * @param tipoNombreReporte
	 *            the tipoNombreReporte to set
	 */
	public void setTipoNombreReporte(String tipoNombreReporte) {
		this.tipoNombreReporte = tipoNombreReporte;
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
	 * @return the siccCuentaCorrienteList
	 */
	public List getSiccCuentaCorrienteList() {
		return siccCuentaCorrienteList;
	}

	/**
	 * @param siccCuentaCorrienteList
	 *            the siccCuentaCorrienteList to set
	 */
	public void setSiccCuentaCorrienteList(List siccCuentaCorrienteList) {
		this.siccCuentaCorrienteList = siccCuentaCorrienteList;
	}

	/**
	 * @return the tipoNombreReporte
	 */
	public String getTipoNombreReporte() {
		return tipoNombreReporte;
	}
}