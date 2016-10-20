package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.let.MantenimientoLETProgramaCorporativoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteLETAlertaBajaForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteLETAlertaBajaAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 1L;
	private List listProgramas;
	private List listRegion;
	private LabelValue[] listTramoCampania;
	private String formatoReporte;

	/**
	 * @return
	 */
	public List getListProgramas() {
		return listProgramas;
	}

	/**
	 * @param listProgramas
	 */
	public void setListProgramas(List listProgramas) {
		this.listProgramas = listProgramas;
	}

	/**
	 * @return
	 */
	public List getListRegion() {
		return listRegion;
	}

	/**
	 * @param listRegion
	 */
	public void setListRegion(List listRegion) {
		this.listRegion = listRegion;
	}
	

	/**
	 * @return
	 */
	public LabelValue[] getListTramoCampania() {
		return listTramoCampania;
	}

	/**
	 * @param listTramoCampania
	 */
	public void setListTramoCampania(LabelValue[] listTramoCampania) {
		this.listTramoCampania = listTramoCampania;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteLETAlertaBajaForm reporteForm = new ReporteLETAlertaBajaForm();
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
			return "reporteLETAlertaBajaXLS";
		}
		return null;
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
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();

		MantenimientoLETProgramaCorporativoService service = (MantenimientoLETProgramaCorporativoService) getBean("spusicc.mantenimientoLETProgramaCorporativoService");

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());

		this.listProgramas = service.getProgramaCorporativoList(criteria);
		//listTramoCampania = new LabelValue[];

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		this.listRegion = reporteService.getListaGenerico("getRegionesByPais",
				criteria);
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

		ReporteLETAlertaBajaForm reporteLETForm = (ReporteLETAlertaBajaForm) this.formReporte;

		this.formatoReporte = reporteLETForm.getFormatoExportacion();

		params.put("codigoPeriodoInicio",
				reporteLETForm.getCodigoPeriodoInicio());
		params.put("codigoPeriodoFin", reporteLETForm.getCodigoPeriodoFinal());

		String cInicio = StringUtils.substring(reporteLETForm
				.getCodigoPeriodoInicio(), reporteLETForm
				.getCodigoPeriodoInicio().length() - 2, reporteLETForm
				.getCodigoPeriodoInicio().length());
		String cFin = StringUtils.substring(reporteLETForm
				.getCodigoPeriodoFinal(), reporteLETForm
				.getCodigoPeriodoFinal().length() - 2, reporteLETForm
				.getCodigoPeriodoFinal().length());

		params.put("campaniaTitulo",
				"C".concat(cInicio).concat("-C").concat(cFin));

		String codigoRegion = reporteLETForm.getCodigoRegion();
		if (!"Todos".equals(codigoRegion))
			params.put("condicionRegion", "AND sr.cod_regi = '" + codigoRegion
					+ "'");
		else
			params.put("condicionRegion", "");

		

		return params;

	}
	
	/**
	 * Carga Tramos
	 */
	public void loadTramos(ValueChangeEvent evt){
		try {
			if(evt == null){
				return;
			}
			String valor  = evt.getNewValue().toString();
			
			log.debug("loadTramosporPrograma...");
			ReporteLETAlertaBajaForm form = (ReporteLETAlertaBajaForm) this.formReporte;
			Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
			AjaxService ajaxService = (AjaxService)this.getBean("ajaxService");
			this.listTramoCampania= ajaxService.getTramos(pais.getCodigo(),valor);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	
	}

}
