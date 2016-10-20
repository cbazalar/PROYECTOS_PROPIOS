package biz.belcorp.ssicc.web.scsicc.action;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteAPEUnidadesPicadasDiaForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteAPEUnidadesPicadasDiaAction extends
		BaseReporteAbstractAction {

	private String formatoReporte;
	private List siccCentrodList;
	private List siccLineaEstList;


	/**
	 * @return
	 */
	public List getSiccLineaEstList() {
		return siccLineaEstList;
	}

	/**
	 * @param siccLineaEstList
	 */
	public void setSiccLineaEstList(List siccLineaEstList) {
		this.siccLineaEstList = siccLineaEstList;
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
	 * @return
	 */
	public List getSiccCentrodList() {
		return siccCentrodList;
	}

	/**
	 * @param siccCentrodList
	 */
	public void setSiccCentrodList(List siccCentrodList) {
		this.siccCentrodList = siccCentrodList;
	}

	private static final long serialVersionUID = 5452137025798023588L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteAPEUnidadesPicadasDiaForm reporteForm = new ReporteAPEUnidadesPicadasDiaForm();
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
		if ("XLS".equals(this.formatoReporte))	
			return  "reporteAPEUnidadesPicadasDiaXLS";
				
		else return "reporteMaestroVertical";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreSubReporte()
	 */
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteAPEUnidadesPicadasDiaPDF";
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
			this.log.debug("Entering 'ReporteAPETotalArticulosAFPForm.setViewAtributes' method");
		}
		ReporteAPEUnidadesPicadasDiaForm reporteAPEForm = (ReporteAPEUnidadesPicadasDiaForm) this.formReporte;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = true;
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		reporteAPEForm.setFechaFacturacionDt( new Date(System.currentTimeMillis()));
		this.siccCentrodList = service.getCentroDistribucionByPais(criteria);
		this.siccLineaEstList = service.getLinea(criteria);		
		log.debug("Todo Ok: Redireccionando");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap()
	 */
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteAPEListaPickingSublineaAction.prepareParameterMap' method");	}
		ReporteAPEUnidadesPicadasDiaForm reporteAPEForm = (ReporteAPEUnidadesPicadasDiaForm) this.formReporte;
		this.formatoReporte = reporteAPEForm.getFormatoExportacion();		
		params.put("NroReporte", " ");
		params.put("titulo", getReportResourceMessage(
				"reporteAPEUnidadesPicadasDiaForm.titulo"));
		super.prepareParamsBeforeExecute(params, reporteAPEForm);
		String codigoPais =  this.mPantallaPrincipalBean.getCurrentCountry().getCodigo();
		params.put("codigoPais",codigoPais);	
		params.put("fechaFacturacion", reporteAPEForm.getFechaFacturacion());		
		return params;
	}
}