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
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ConsultaCCCGenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCCCDetalladoPagosRegularizadosForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteCCCDetalladoPagosRegularizadosAction extends
		BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = 3847694719429935218L;
	private String tipoNombreReporte;

	private List siccBancoList = new ArrayList();

	public static long getSerialversionuid() {
		return serialVersionUID;
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
			log.debug("Entering 'view' method");
		}

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;

		// Obtenemos el Pais
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		// Carga de lista para cargar los combos en el JSP
		ConsultaCCCGenericoService serviceCCC = (ConsultaCCCGenericoService) getBean("spusicc.consultaCCCGenericoService");

		// Map para almacenar los parametros
		Map criteria = new HashMap();

		criteria.put("codigoPais", pais.getCodigo());
		this.siccBancoList = serviceCCC
				.getCuentasCorrientesBancariasList(criteria);

		// Lista de Banco para el Pais

		this.siccBancoList = serviceCCC.getCuentasCorrientesBancariasList(criteria);

		ReporteCCCDetalladoPagosRegularizadosForm form = (ReporteCCCDetalladoPagosRegularizadosForm) this.formReporte;
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
		ReporteCCCDetalladoPagosRegularizadosForm form = new ReporteCCCDetalladoPagosRegularizadosForm();
		return form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteCCCDetalladoPagosRegularizadosXLS";
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
		ReporteCCCDetalladoPagosRegularizadosForm form = (ReporteCCCDetalladoPagosRegularizadosForm) this.formReporte;
		String fecha1D, fecha2D, fecha3D, fecha4D;

		fecha1D = DateUtil.getDate(form.getFechaPagoDesdeD());
		fecha2D = DateUtil.getDate(form.getFechaPagoHastaD());
		fecha3D = DateUtil.getDate(form.getFechaProcDesdeD());
		fecha4D = DateUtil.getDate(form.getFechaProcHastaD());

		if (!fecha1D.isEmpty() && !fecha2D.isEmpty()) {
			if (fecha2D.compareTo(fecha1D) < 0) {
				String mensaje = this
						.getResourceMessage("reporteCCCLiquidacionCobranzasForm.errors.compare.fechaPago");
				// reporteRECEmisionNotaCreditoForm.validar.fechas
				return mensaje;
			}
		}
		if (!fecha3D.isEmpty() && !fecha4D.isEmpty()) {
			if (fecha4D.compareTo(fecha3D) < 0) {
				String mensaje = this
						.getResourceMessage("reporteCCCLiquidacionCobranzasForm.errors.compare.fechaProc");
				// reporteRECEmisionNotaCreditoForm.validar.fechas
				return mensaje;
			}
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

		ReporteCCCDetalladoPagosRegularizadosForm ReporteCCCDetalladoPagosRegularizadosForm = (ReporteCCCDetalladoPagosRegularizadosForm) this.formReporte;

		String fecha1, fecha2, fecha3, fecha4;
		fecha1 = DateUtil.getDate(ReporteCCCDetalladoPagosRegularizadosForm
				.getFechaPagoDesdeD());
		fecha2 = DateUtil.getDate(ReporteCCCDetalladoPagosRegularizadosForm
				.getFechaPagoHastaD());
		fecha3 = DateUtil.getDate(ReporteCCCDetalladoPagosRegularizadosForm
				.getFechaProcDesdeD());
		fecha4 = DateUtil.getDate(ReporteCCCDetalladoPagosRegularizadosForm
				.getFechaProcHastaD());

		ReporteCCCDetalladoPagosRegularizadosForm.setFechaPagoDesde(fecha1);
		ReporteCCCDetalladoPagosRegularizadosForm.setFechaPagoHasta(fecha2);
		ReporteCCCDetalladoPagosRegularizadosForm.setFechaProcDesde(fecha3);
		ReporteCCCDetalladoPagosRegularizadosForm.setFechaProcHasta(fecha4);
		params.put("fechaPagoDesde", ReporteCCCDetalladoPagosRegularizadosForm.getFechaPagoDesde());
		params.put("fechaPagoHasta", ReporteCCCDetalladoPagosRegularizadosForm.getFechaPagoHasta());
		params.put("fechaProcDesde", ReporteCCCDetalladoPagosRegularizadosForm.getFechaProcDesde());
		params.put("fechaProcHasta", ReporteCCCDetalladoPagosRegularizadosForm.getFechaProcHasta());
	
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
	 * @return the tipoNombreReporte
	 */
	public String getTipoNombreReporte() {
		return tipoNombreReporte;
	}
}