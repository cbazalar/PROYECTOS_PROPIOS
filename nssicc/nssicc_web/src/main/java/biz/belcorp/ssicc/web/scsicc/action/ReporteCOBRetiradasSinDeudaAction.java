package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCOBRetiradasSinDeudaForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteCOBRetiradasSinDeudaAction extends
		BaseReporteAbstractAction {

	private String formatoReporte;
	private String codigoIdiomaISO;

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
	 * @return
	 */
	public String getCodigoIdiomaISO() {
		return codigoIdiomaISO;
	}

	/**
	 * @param codigoIdiomaISO
	 */
	public void setCodigoIdiomaISO(String codigoIdiomaISO) {
		this.codigoIdiomaISO = codigoIdiomaISO;
	}

	private static final long serialVersionUID = 5452137025798023586L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOBRetiradasSinDeudaForm reporteForm = new ReporteCOBRetiradasSinDeudaForm();
		return reporteForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreReporte()
	 */
	protected String devuelveNombreReporte() throws Exception {

		if ("XLS".equals(formatoReporte)) {
			return "reporteCOBRetiradasSinDeudaXLS";
		} else {
			return "reporteMaestroHorizontal";
		}
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreSubReporte()
	 */
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()ReporteRECMercaderiaSiniestradaAction
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'ReporteCOBRetiradasSinDeudaForm.setViewAtributes' method");
		}
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;		
		ReporteCOBRetiradasSinDeudaForm f = (ReporteCOBRetiradasSinDeudaForm) this.formReporte;
		Map criteriaOperacion = new HashMap();
		Pais pais = mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		f.setImporteDeuda("");
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		f.getIdioma().setCodigoISO(f.getIdioma().getCodigoISO());
		this.codigoIdiomaISO = f.getIdioma().getCodigoISO();
	}

	protected String devuelveBeanReporteService() {
		return "reportes.reporteCOBEgresadasSinDeudaService";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap()
	 */
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		reporteService.executeReporteCOBRetiradasSinDeuda(params);
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCOBEgresadasSinDeudaAction.prepareParameterMap' method");
		}
		ReporteCOBRetiradasSinDeudaForm f = (ReporteCOBRetiradasSinDeudaForm) this.formReporte;
		this.formatoReporte = f.getFormatoExportacion();
		return params;
	}
}
