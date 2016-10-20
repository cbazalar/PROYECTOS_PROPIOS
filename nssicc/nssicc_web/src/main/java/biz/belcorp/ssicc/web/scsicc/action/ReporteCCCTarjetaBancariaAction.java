package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ConsultaCCCGenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCCCTarjetaBancariaForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes" })
public class ReporteCCCTarjetaBancariaAction extends BaseReporteAbstractAction
		implements Serializable {

	private static final long serialVersionUID = 8623889324055769121L;
	private String tipoReporte;
	private List siccBancoList = new ArrayList();

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;

		ReporteCCCTarjetaBancariaForm form = (ReporteCCCTarjetaBancariaForm) this.formReporte;
		ConsultaCCCGenericoService serviceCCC = (ConsultaCCCGenericoService) getBean("spusicc.consultaCCCGenericoService");
		this.siccBancoList = serviceCCC.getBancosCheques();

		form.setFechaCobroDesdeD(new Date(System.currentTimeMillis()));
		form.setFechaCobroHastaD(new Date(System.currentTimeMillis()));
		form.setFechaProcDesdeD(new Date(System.currentTimeMillis()));
		form.setFechaProcHastaD(new Date(System.currentTimeMillis()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteCCCTarjetaBancariaForm form = (ReporteCCCTarjetaBancariaForm) this.formReporte;
		Date fecha1D, fecha2D, fecha3D, fecha4D;

		fecha1D = form.getFechaCobroDesdeD();
		fecha2D = form.getFechaCobroHastaD();
		fecha3D = form.getFechaProcDesdeD();
		fecha4D = form.getFechaProcHastaD();

		if (fecha2D.compareTo(fecha1D) < 0) {
			String mensaje = this
					.getResourceMessage("reporteCCCTarjetaBancariaForm.errors.compare.fechaCobro");
			// reporteRECEmisionNotaCreditoForm.validar.fechas
			return mensaje;
		}
		if (fecha4D.compareTo(fecha3D) < 0) {
			String mensaje = this
					.getResourceMessage("reporteCCCTarjetaBancariaForm.errors.compare.fechaProc");
			// reporteRECEmisionNotaCreditoForm.validar.fechas
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
		log.info("ENTRANDO AL PREPARA PARMATER");
		ReporteCCCTarjetaBancariaForm reporteCCCTarjetaBancariaForm = (ReporteCCCTarjetaBancariaForm) this.formReporte;
		log.info(reporteCCCTarjetaBancariaForm.toString());
		this.tipoReporte = reporteCCCTarjetaBancariaForm.getTipoReporte();

		String fecha1, fecha2, fecha3, fecha4;
		fecha1 = DateUtil.getDate(reporteCCCTarjetaBancariaForm
				.getFechaCobroDesdeD());
		fecha2 = DateUtil.getDate(reporteCCCTarjetaBancariaForm
				.getFechaCobroHastaD());
		fecha3 = DateUtil.getDate(reporteCCCTarjetaBancariaForm
				.getFechaProcDesdeD());
		fecha4 = DateUtil.getDate(reporteCCCTarjetaBancariaForm
				.getFechaProcHastaD());

		reporteCCCTarjetaBancariaForm.setFechaCobroDesde(fecha1);
		reporteCCCTarjetaBancariaForm.setFechaCobroHasta(fecha2);
		reporteCCCTarjetaBancariaForm.setFechaProcDesde(fecha3);
		reporteCCCTarjetaBancariaForm.setFechaProcHasta(fecha4);
		
		params.put("fechaCobroDesde", reporteCCCTarjetaBancariaForm.getFechaCobroDesde());
		params.put("fechaCobroHasta", reporteCCCTarjetaBancariaForm.getFechaCobroHasta());
		params.put("fechaProcDesde", reporteCCCTarjetaBancariaForm.getFechaProcDesde());
		params.put("fechaProcHasta", reporteCCCTarjetaBancariaForm.getFechaProcHasta());

		log.debug(" Imprimiendo parmetros");
		log.debug(params);
		log.debug("Fin parmetros");
		return params;
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
		return "reporteCCCTarjetaBancaria" + this.tipoReporte + "XLS";
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
		ReporteCCCTarjetaBancariaForm form = new ReporteCCCTarjetaBancariaForm();
		return form;
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

	/**
	 * @return the tipoReporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte
	 *            the tipoReporte to set
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
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
}