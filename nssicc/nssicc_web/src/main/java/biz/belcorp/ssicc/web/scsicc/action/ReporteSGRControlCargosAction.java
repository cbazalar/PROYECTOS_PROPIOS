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
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.sgr.MantenimientoSGRGenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSGRControlCargosForm;

@ManagedBean
@SessionScoped
public class ReporteSGRControlCargosAction extends BaseReporteAbstractAction {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3622081087487602063L;
	private String formatoReporte;
	private String tipoFormato;
	private List siccRegionList;
	private List sgrAseguradoraList;
	private LabelValue[] siccZonaList={};
	private LabelValue[] siccSeccionList={};

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSGRControlCargosForm reporteForm = new ReporteSGRControlCargosForm();
		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReporteSGRControlCargosAction - setViewAtributes");
		}

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ReporteSGRControlCargosForm reporteCOBForm = (ReporteSGRControlCargosForm) this.formReporte;
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		MantenimientoSGRGenericoService polizaService = (MantenimientoSGRGenericoService)this.getBean("spusicc.mantenimientoSGRGenericoService");								
		
		Map criteriaOperacion = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		this.siccRegionList = reporteService.getListaGenerico(
				"getRegionesByPais", criteriaOperacion);
		

		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", pais.getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO);
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteriaPeriodo);

		reporteCOBForm.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		this.sgrAseguradoraList = polizaService.getPoliza(null);
		
		log.debug("Todo OK: Redireccionando");

	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))
			return "reporteSGRControlCargosXLS";
		else
			return "reporteMaestroHorizontal";
		}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("prepareParameterMap...");
		}
		ReporteSGRControlCargosForm reporteCOBForm = (ReporteSGRControlCargosForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		formatoReporte = reporteCOBForm.getFormatoExportacion();


		Map criteria = params;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codigoPais = pais.getCodigo();
		criteria.put("codigoPais", codigoPais);
		String oidPais =reporteService.getOidString("getOidPaisByCodigoPais",criteria);

		String fechaIni="";
		String fechaFin="";
		if(reporteCOBForm.getFechaInicioD()!=null){
			fechaIni=DateUtil.convertDateToString(reporteCOBForm.getFechaInicioD());
		}
		if(reporteCOBForm.getFechaFinalD()!=null){
			fechaFin=DateUtil.convertDateToString(reporteCOBForm.getFechaFinalD());
		}
		
		String codigoRegion = "";
		if(reporteCOBForm.getCodigoRegion()!=null)
		codigoRegion=reporteCOBForm.getCodigoRegion();
		if(codigoRegion.compareToIgnoreCase("")==0)
			codigoRegion = null;
		
		String codigoZona = "";
		if(reporteCOBForm.getCodigoZona()!=null)
			codigoZona = reporteCOBForm.getCodigoZona();
		if(codigoZona.compareToIgnoreCase("")==0)
			codigoZona = null;
		
		String codigoSeccion = "";
		if(reporteCOBForm.getCodigoSeccion()!=null)
			codigoSeccion=reporteCOBForm.getCodigoSeccion();
		if(codigoSeccion.compareToIgnoreCase("")==0)
			codigoSeccion = null;
		
		String codigoPeriodo = "";
		if(reporteCOBForm.getCodigoPeriodo()!=null)
		codigoPeriodo=reporteCOBForm.getCodigoPeriodo();
		if(codigoPeriodo.compareToIgnoreCase("")==0)
			codigoPeriodo = null;

		params.put("oidPais", oidPais);
		params.put("codigoPais", codigoPais);
		params.put("codigoRegion", codigoRegion);
		params.put("codigoZona", codigoZona);
		params.put("codigoSeccion", codigoSeccion);
		params.put("codigoPeriodo", codigoPeriodo);
		params.put("fechaInicio", fechaIni);
		params.put("fechaFin", fechaFin);
		params.put("NroReporte", "");
	
		params.put("titulo", getResourceMessage("reporteSGRControlCargosForm.title"));
		return params;
	}

	/**
	 * Carga combo zonas
	 * 
	 * @param val
	 */
	public void loadzonas(ValueChangeEvent val) {
		log.debug("loadzonas");

		String valor = (String) val.getNewValue();
		if (valor.trim().length() > 0) {

			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			this.siccZonaList = aSvc
					.getDesZonasByPaisMarcaCanalRegion(
							this.mPantallaPrincipalBean.getCurrentCountry()
									.getCodigo(),
							Constants.CODIGO_MARCA_DEFAULT,
							Constants.CODIGO_CANAL_DEFAULT, valor);

		} else {
			setSiccZonaList(null);
			setSiccSeccionList(null);

		}

	}

	
	/**
	 * Carga combo seccion
	 * 
	 * @param val
	 */
	public void loadseccion(ValueChangeEvent val) {
		log.debug("loadseccion");
		ReporteSGRControlCargosForm form = (ReporteSGRControlCargosForm) this.formReporte;
		String region = (String) form.getCodigoRegion();
		String zona = (String) val.getNewValue();
		if (region.length() > 0 && zona.length() > 0) {
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			setSiccSeccionList(aSvc
					.getSeccionesByPaisMarcaCanalRegionZona(
							this.mPantallaPrincipalBean.getCurrentCountry()
									.getCodigo(),
							Constants.CODIGO_MARCA_DEFAULT,
							Constants.CODIGO_CANAL_DEFAULT, region, zona));

		} else {
			setSiccSeccionList(null);

		}

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
	 * @return the tipoFormato
	 */
	public String getTipoFormato() {
		return tipoFormato;
	}

	/**
	 * @param tipoFormato the tipoFormato to set
	 */
	public void setTipoFormato(String tipoFormato) {
		this.tipoFormato = tipoFormato;
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
	 * @return the sgrAseguradoraList
	 */
	public List getSgrAseguradoraList() {
		return sgrAseguradoraList;
	}

	/**
	 * @param sgrAseguradoraList the sgrAseguradoraList to set
	 */
	public void setSgrAseguradoraList(List sgrAseguradoraList) {
		this.sgrAseguradoraList = sgrAseguradoraList;
	}

	/**
	 * @return the siccZonaList
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the siccSeccionList
	 */
	public LabelValue[] getSiccSeccionList() {
		return siccSeccionList;
	}

	/**
	 * @param siccSeccionList the siccSeccionList to set
	 */
	public void setSiccSeccionList(LabelValue[] siccSeccionList) {
		this.siccSeccionList = siccSeccionList;
	}
	
	

}

