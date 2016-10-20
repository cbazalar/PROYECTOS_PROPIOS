package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang3.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCOBNumeroPagosCampannaForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;


/**
 * The Class ReporteCOBEstadisticoRecuperacionCarteraAction.
 *
 * @autor: Carlos Bazalar
 * @version: 1.0
 * 04/11/2015
 */
@ManagedBean
@SessionScoped
public class ReporteCOBNumeroPagosCampannaAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 9084823810216826503L;
	private static final String TIPO_REPORTE_CONSULTORA = "C";
	private String tipoReporte;	
	private List siccSociedadList;
	private List siccRegionList;
	private List siccZonaList;
	private boolean bzona;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOBNumeroPagosCampannaForm form = new ReporteCOBNumeroPagosCampannaForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCOBNumeroPagosCampannaAction.setViewAtributes' method");
		}		
		this.mostrarReporteOCSV = true;
		this.mostrarReportePDF = false;
		this.mostrarReporteXLS = true;
				
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		ReporteCOBNumeroPagosCampannaForm f = (ReporteCOBNumeroPagosCampannaForm) this.formReporte;
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", pais.getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO);
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO);

		MantenimientoOCRPedidoControlFacturacionService controlFactService = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = controlFactService.getControlFacturacionById(criteriaPeriodo);

		f.setCodigoPeriodoInicial(controlFacturacion.getCodigoPeriodo());
		f.setCodigoPeriodoFinal(controlFacturacion.getCodigoPeriodo());
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());			
		this.siccSociedadList = service.getSociedadesByCodigoPais(pais.getCodigo());
		this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais", criteriaOperacion);
		this.siccZonaList = new ArrayList();
		this.bzona = true;
		f.setNumeroPagos(Constants.NUMERO_CERO);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if (this.formatoExportacion.equals("XLS"))
			return  "reporteCOBNumeroPagosCampanna" + tipoReporte + "XLS";
		return "";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {			
		return null;
	}
	
	
	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reporteCOBNumeroPagosCampannaService";
	}
	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		
		ReporteCOBNumeroPagosCampannaForm f = (ReporteCOBNumeroPagosCampannaForm) this.formReporte;
								
		this.tipoReporte = f.getTipoReporte();						
		Integer numeroPagosMayores = new Integer(f.getNumeroPagos());
		params.put("numeroPagosMayores", numeroPagosMayores);  
		
		String titulo = this.getResourceMessage("reporteCOBNumeroPagosCampannaForm.titulo.consultoraBanco");
		if (tipoReporte.equals(TIPO_REPORTE_CONSULTORA)) {
			titulo = this.getResourceMessage("reporteCOBNumeroPagosCampannaForm.titulo.consultora");
		}
		params.put("titulo", titulo);  
		
		params.put("zonaList", (f.getZonaList() == null) ? new ArrayList() : Arrays.asList(f.getZonaList()));
		params.put("regionList", (f.getRegionList() == null) ? new ArrayList() : Arrays.asList(f.getRegionList()));
		
		List zonaList = (List) params.get("zonaList");
		if (zonaList != null && zonaList.size() == 1) {
			String valor = (String)zonaList.get(0);
			if (StringUtils.isBlank(valor)) 
				params.put("zonaList", new ArrayList());
		}
		
		List regionList = (List) params.get("regionList");
		if (regionList != null && regionList.size() == 1) {
			String valor = (String)regionList.get(0);
			if (StringUtils.isBlank(valor)) 
				params.put("regionList", new ArrayList());
		}
		log.debug("Imprimiendo parámetros");
		log.debug(params);
		return params;
	}
	
	/**
	 * loadZonas.
	 *
	 * @param val the val
	 */
	public void loadZonas(ValueChangeEvent val) {     
		log.debug(">>loadZonas ");
		if (val == null) return;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		LabelValue zonas[] = ajax.getZonasMultipleByPaisMarcaCanalRegion(pais.getCodigo(), "T", "VD", (String[]) val.getNewValue(), "T");
		if(zonas != null && zonas.length > 0) {
			this.siccZonaList = Arrays.asList(zonas);
			this.bzona = false;
		} else {
			this.siccZonaList = new ArrayList();
			this.bzona = true;
		}		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	@Override
	public String setValidarReporte() {
		ReporteCOBNumeroPagosCampannaForm f = (ReporteCOBNumeroPagosCampannaForm) this.formReporte;
		String codigoPeriodoIni = f.getCodigoPeriodoInicial();
		String codigoPeriodoFin = f.getCodigoPeriodoFinal();
		
		int icodigoPeriodoIni = new Integer(codigoPeriodoIni).intValue();
		int icodigoPeriodoFin = new Integer(codigoPeriodoFin).intValue();
		
		if (icodigoPeriodoFin < icodigoPeriodoIni) {
			String mensaje = this.getResourceMessage("reporteCOBNumeroPagosCampannaForm.codigoPeriodo.error");
			return mensaje;
		}
		return null;
	}
	
	

	/* GET - SET */

	

	/**
	 * @return the siccSociedadList
	 */
	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	/**
	 * @param siccSociedadList the siccSociedadList to set
	 */
	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
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
	 * @return the siccZonaList
	 */
	public List getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList the siccZonaList to set
	 */
	public void setSiccZonaList(List siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the bzona
	 */
	public boolean isBzona() {
		return bzona;
	}

	/**
	 * @param bzona the bzona to set
	 */
	public void setBzona(boolean bzona) {
		this.bzona = bzona;
	}

	/**
	 * @return the tipoReporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte the tipoReporte to set
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}
	
	

}