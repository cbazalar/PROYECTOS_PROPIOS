package biz.belcorp.ssicc.web.scsicc.action;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteAPEProductosPosventaForm;


@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteAPEProductosPosventaAction extends
		BaseReporteAbstractAction {

	private String formatoReporte;
	private LabelValue[] siccLineaList;
	private LabelValue[] siccSublineaList;
	private LabelValue[] siccCentrodList;

	/**
	 * @return
	 */
	public LabelValue[] getSiccLineaList() {
		return siccLineaList;
	}

	/**
	 * @param siccLineaList
	 */
	public void setSiccLineaList(LabelValue[] siccLineaList) {
		this.siccLineaList = siccLineaList;
	}

	/**
	 * @return
	 */
	public LabelValue[] getSiccSublineaList() {
		return siccSublineaList;
	}

	/**
	 * @param siccSublineaList
	 */
	public void setSiccSublineaList(LabelValue[] siccSublineaList) {
		this.siccSublineaList = siccSublineaList;
	}

	/**
	 * @return
	 */
	public LabelValue[] getSiccCentrodList() {
		return siccCentrodList;
	}

	/**
	 * @param siccCentrodList
	 */
	public void setSiccCentrodList(LabelValue[] siccCentrodList) {
		this.siccCentrodList = siccCentrodList;
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
		ReporteAPEProductosPosventaForm reporteForm = new ReporteAPEProductosPosventaForm();
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
		ReporteAPEProductosPosventaForm reporteForm = (ReporteAPEProductosPosventaForm) this.formReporte;
		String tipoReporte = reporteForm.getFormatoExportacion();
		if ("XLS".equals(tipoReporte))
			return "reporteAPEProductosPosventaXLS";
		else
			return "reporteMaestroVertical";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreSubReporte()
	 */
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteAPEProductosPosventaPDF";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()ReporteRECMercaderiaSiniestradaAction
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'SetView ReporteAPEProductosPosventaForm' method");
		}
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		ReporteAPEProductosPosventaForm form = (ReporteAPEProductosPosventaForm) this.formReporte;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		form.setCodigoPais(pais.getCodigo());
		Map criteria = new HashMap();
        criteria.put("codigoPais", pais.getCodigo());
        form.setFechaFacturacionDate( new Date(System.currentTimeMillis()));
		this.siccCentrodList=aSvc.getCentroDistribucionByPais(pais.getCodigo());
		this.siccLineaList=aSvc.getLinea(pais.getCodigo());
		this.siccSublineaList= aSvc.getSublineaByLinea(pais.getCodigo());
		this.log.debug("Centro Distribución"+ siccCentrodList);
		this.log.debug("siccLineaList"+ siccLineaList);
		this.log.debug("siccSublineaList"+ siccSublineaList);
	}

	/*
	 *  Cargar Lineas Por Centro de Distribución
	 * 
	 * 
	 */
	public void loadLineas(ValueChangeEvent val){
		log.debug(">>Load Lineas ");
		try {
			String codCentroDist = val.getNewValue().toString();
			
			ReporteAPEProductosPosventaForm form = (ReporteAPEProductosPosventaForm) this.formReporte;
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			setSiccLineaList(aSvc.getLinea(codCentroDist));	
			form.setCodsublinea("");
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	
	}
	
	public void loadTipos(ValueChangeEvent val){
		log.debug(">>Load Tipos ");
		try {
			String codLinea = val.getNewValue().toString();
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			setSiccSublineaList(aSvc.getSublineaByLinea(codLinea));	
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
		
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
			log.debug("Entering 'ReporteAPEProductosPosventaForm.prepareParameterMap' method");
		}
		//ReporteRECMercaderiaSiniestradaForm mercaderiaSiniestradaForm = (ReporteRECMercaderiaSiniestradaForm) form;

		ReporteAPEProductosPosventaForm reporteForm = (ReporteAPEProductosPosventaForm) this.formReporte;
		reporteForm
				.setTitulo(this
						.getResourceMessage("reporteAPEProductosPosventaForm.titulo"));
		reporteForm.setNroReporte("");
		this.formatoReporte = reporteForm.getFormatoExportacion();

		params.put("NroReporte", " "); // ReporteCOMComisionIngresoAction
		params.put(
				"titulo",
				this.getReportResourceMessage("reporteAPEProductosPosventaForm.titulo"));
		

		String codigoPais =  reporteForm.getCodigoPais();
        String fechaFact = reporteForm.getFechaFacturacion();
        String CodCentroDist = reporteForm.getCodigoCentro();
        String Sublinea = reporteForm.getCodsublinea();

		params.put("pais", codigoPais);	
		params.put("fechaFacturacion", fechaFact);
		params.put("centro", CodCentroDist);
		params.put("codsublinea", Sublinea);
		return params;

	}

	/**
	 * @return the formatoReporte
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte the formatoReporte to set
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}
}