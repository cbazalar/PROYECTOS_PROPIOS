package biz.belcorp.ssicc.web.scsicc.action;

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
import biz.belcorp.ssicc.web.scsicc.form.ReporteAPEListaPickingSublineaForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteAPEListaPickingSublineaAction extends
		BaseReporteAbstractAction {

	private String formatoReporte;
	private List siccMarcaList;
	private List siccCentrodList;
	private List siccLineaList;
	private List siccSublineaList;

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
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
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

	/**
	 * @return
	 */
	public List getSiccLineaList() {
		return siccLineaList;
	}

	/**
	 * @param siccLineaList
	 */
	public void setSiccLineaList(List siccLineaList) {
		this.siccLineaList = siccLineaList;
	}

	/**
	 * @return
	 */
	public List getSiccSublineaList() {
		return siccSublineaList;
	}

	/**
	 * @param siccSublineaList
	 */
	public void setSiccSublineaList(List siccSublineaList) {
		this.siccSublineaList = siccSublineaList;
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
		ReporteAPEListaPickingSublineaForm reporteForm = new ReporteAPEListaPickingSublineaForm();
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
			return "reporteAPEListaPickingSublineaXLS";
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
		return "reporteAPEListaPickingSublineaPDF";
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

		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);

		this.siccMarcaList = service.getFacturacion(criteria);
		this.siccCentrodList = service.getCentroDistribucionByPais(criteria);
		this.siccLineaList = service.getLinea(criteria);
		this.siccSublineaList = service.getSublineaxLinea(criteria);
		
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
			log.debug("Entering 'ReporteAPEListaPickingSublineaAction.prepareParameterMap' method");
		}


		ReporteAPEListaPickingSublineaForm reporteAPEForm = (ReporteAPEListaPickingSublineaForm) this.formReporte;
		this.formatoReporte = reporteAPEForm.getFormatoExportacion();
		
		params.put("NroReporte", " ");
		params.put("titulo", getReportResourceMessage(
				"reporteAPEListaPickingSublineaForm.titulo"));

		String codigoPais =  reporteAPEForm.getCodigoPais();
        String fechaSaldo = reporteAPEForm.getFechaFacturacion();
        String marca = reporteAPEForm.getCodigoMarca();
        String canal = reporteAPEForm.getCodigoCanal();
        String linea = reporteAPEForm.getCodigoLinea();
        String campana = "%"+reporteAPEForm.getCodigoPeriodo();
        String fuente =reporteAPEForm.getCodigoLote();
        String sublinea = reporteAPEForm.getCodsublinea();
        String centro = reporteAPEForm.getCodigoCentro();
        
		params.put("codigoPais", codigoPais);	
		params.put("fechaFacturacion", fechaSaldo);
		params.put("marca", marca);
		params.put("canal", canal);
		params.put("codigoLinea", linea);
		params.put("campana", campana);
		params.put("codigoLote",fuente);
		params.put("codsublinea",sublinea);
		params.put("centro",centro);			
		return params;
	}
}