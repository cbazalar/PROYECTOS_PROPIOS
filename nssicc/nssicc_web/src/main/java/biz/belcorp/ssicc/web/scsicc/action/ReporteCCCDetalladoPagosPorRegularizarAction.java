package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ConsultaCCCGenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCCCDetalladoPagosPorRegularizarForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteCCCDetalladoPagosPorRegularizarAction extends
		BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = 1443456315075828406L;
	private List siccBancoList = new ArrayList();
	private List cccTipoErroresPagosBancariosList = new ArrayList();

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'view' method");
		}

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;

		// Obtenemos el Pais
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		// Map para almacenar los parametros
		Map criteria = new HashMap();

		criteria.put("codigoPais", pais.getCodigo());

		// Lista de Cuentas Corrientes Bancarias
		ConsultaCCCGenericoService serviceCCC = (ConsultaCCCGenericoService) getBean("spusicc.consultaCCCGenericoService");
		this.siccBancoList = serviceCCC
				.getCuentasCorrientesBancariasList(criteria);

		// Obteniedo el listado de los Errores de los Pagos Bancarios
		this.cccTipoErroresPagosBancariosList = serviceCCC
				.getTiposErrorPagoBancarioList();

		ReporteCCCDetalladoPagosPorRegularizarForm f = (ReporteCCCDetalladoPagosPorRegularizarForm) this.formReporte;

		f.setFechaProcDesdeD(new Date(System.currentTimeMillis()));

		f.setFechaProcHastaD(new Date(System.currentTimeMillis()));

		f.setFechaPagoDesdeD(new Date(System.currentTimeMillis()));

		f.setFechaPagoHastaD(new Date(System.currentTimeMillis()));
	}

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCCCDetalladoPagosPorRegularizarForm form = new ReporteCCCDetalladoPagosPorRegularizarForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteCCCDetalladoPagosPorRegularizarXLS";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'prepareParameterMap' method");
		}
		// Preparando los parametros
		ReporteCCCDetalladoPagosPorRegularizarForm reporteCCCPagosPorRegularizarForm = (ReporteCCCDetalladoPagosPorRegularizarForm) this.formReporte;
		String fecha1, fecha2, fecha3, fecha4;
		fecha1 = DateUtil.getDate(reporteCCCPagosPorRegularizarForm
				.getFechaProcDesdeD());
		fecha2 = DateUtil.getDate(reporteCCCPagosPorRegularizarForm
				.getFechaProcHastaD());
		fecha3 = DateUtil.getDate(reporteCCCPagosPorRegularizarForm
				.getFechaPagoDesdeD());
		fecha4 = DateUtil.getDate(reporteCCCPagosPorRegularizarForm
				.getFechaPagoHastaD());

		reporteCCCPagosPorRegularizarForm.setFechaProcDesde(fecha1);
		reporteCCCPagosPorRegularizarForm.setFechaProcHasta(fecha2);
		reporteCCCPagosPorRegularizarForm.setFechaPagoDesde(fecha3);
		reporteCCCPagosPorRegularizarForm.setFechaPagoHasta(fecha4);
		params.put("codigoBanco", reporteCCCPagosPorRegularizarForm.getCodigoBanco());
		params.put("fechaProcDesde", fecha1);
		params.put("fechaProcHasta",fecha2);
		params.put("fechaPagoDesde",fecha3);
		params.put("fechaPagoHasta",fecha4 );
		

		/*
		 * String codigoProceso = (Constants.RECAUDO_BANCARIO_PROCESO); String
		 * codigoSubproceso = "";
		 * 
		 * if
		 * (reporteCCCPagosPorRegularizarForm.getTipoAbono().equalsIgnoreCase(
		 * Constants.TIPO_RECAUDO_BANCARIO_TODOS)) { codigoSubproceso = null; }
		 * else if
		 * (reporteCCCPagosPorRegularizarForm.getTipoAbono().equalsIgnoreCase
		 * (Constants.TIPO_RECAUDO_BANCARIO_AUTOMATICO)) {
		 * codigoSubproceso=(Constants.RECAUDO_BANCARIO_AUTOMATICO_SUBPROCESO);
		 * } else {
		 * codigoSubproceso=(Constants.RECAUDO_BANCARIO_MANUAL_SUBPROCESO); };
		 * 
		 * log.debug("Tipo Abono"); log.debug(codigoProceso);
		 * log.debug(codigoSubproceso);
		 * 
		 * tipoNombreReporte =
		 * reporteCCCPagosPorRegularizarForm.getTipoReporte() +
		 * reporteCCCPagosPorRegularizarForm.getTipoVista();
		 * 
		 * super.prepareParameterMap(params, form, request);
		 * 
		 * Map criteria = params;
		 * 
		 * params.put("codigoProceso",codigoProceso);
		 * params.put("codigoSubproceso",codigoSubproceso);
		 */

		log.debug(" Imprimiendo parmetros");
		log.debug(params);
		log.debug("Fin parmetros");
		return params;
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
	 * @return the cccTipoErroresPagosBancariosList
	 */
	public List getCccTipoErroresPagosBancariosList() {
		return cccTipoErroresPagosBancariosList;
	}

	/**
	 * @param cccTipoErroresPagosBancariosList
	 *            the cccTipoErroresPagosBancariosList to set
	 */
	public void setCccTipoErroresPagosBancariosList(
			List cccTipoErroresPagosBancariosList) {
		this.cccTipoErroresPagosBancariosList = cccTipoErroresPagosBancariosList;
	}
}