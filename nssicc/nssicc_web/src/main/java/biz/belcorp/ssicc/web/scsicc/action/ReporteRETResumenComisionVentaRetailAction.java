//TODO Migrar al framework de reportes
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
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteRETResumenComisionVentaRetailForm;

@ManagedBean
@SessionScoped
public class ReporteRETResumenComisionVentaRetailAction extends
		BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6841250864214074144L;
	private String formatoReporte;
	private List siccRegionList;
	private LabelValue[] siccZonaList = {};

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteRETResumenComisionVentaRetailForm reporteForm = new ReporteRETResumenComisionVentaRetailForm();
		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'view' method");
		}
		this.mostrarReporteXLS = true;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		Map criteriaOperacion = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		this.siccRegionList=reporteService.getListaGenerico("getRegionesByPais",
				criteriaOperacion);

	}

	public String setValidarReporte() {
		ReporteRETResumenComisionVentaRetailForm form = (ReporteRETResumenComisionVentaRetailForm) this.formReporte;
			if (form.getFechaFinalD().compareTo(form.getFechaInicioD()) < 0) {
				String mensaje = this
						.getResourceMessage("reporteRETResumenComisionVentaRetailForm.validar.fechas");
				return mensaje;
			}
		

		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'prepareParameterMap' method");
		}
		ReporteRETResumenComisionVentaRetailForm reporteRETForm = (ReporteRETResumenComisionVentaRetailForm) this.formReporte;
		formatoReporte = reporteRETForm.getFormatoExportacion();
		String condicionRegion = this.obtieneCondicion(reporteRETForm.getRegionList(), "A.COD_REGI", "'");
		String condicionZona = this.obtieneCondicion(reporteRETForm.getZonaList(), "A.COD_ZONA", "'");
		String fechaini = DateUtil.convertDateToString(reporteRETForm.getFechaInicioD());
		String fechafin = DateUtil.convertDateToString(reporteRETForm.getFechaFinalD());
		params.put("codigoPais",reporteRETForm.getCodigoPais());
		params.put("fechaInicio",fechaini);
		params.put("fechaFinal", fechafin);
		params.put("condicionRegion", condicionRegion);
		params.put("condicionZona", condicionZona);
		
		params.put("titulo", getResourceMessage(
				"reporteRETResumenComisionVentaRetailForm.titulo"));
		reporteRETForm.setFormatoExportacion(formatoReporte);
		
		return params;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))
			return "reporteRETResumenComisionVentaRetailXLS";
		else
			return "reporteMaestroHorizontal";

	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		String subReporte="";
		 if("PDF".equals(formatoReporte))
			 subReporte="reporteRETResumenComisionVentaRetailPDF";
		return subReporte;
	}

	public void loadZonas(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadZonas");
		}
		String[] valor = (String[]) val.getNewValue();
		if (valor.length > 0) {
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			this.siccZonaList = ajax
					.getZonasMultipleByPaisMarcaCanalRegion(
							this.mPantallaPrincipalBean.getCurrentCountry()
									.getCodigo(),
							Constants.CODIGO_MARCA_DEFAULT,
							Constants.CODIGO_CANAL_DEFAULT, valor,
							Constants.FORMATEAR_TODOS);

		} else {

			setSiccZonaList(null);
		}

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
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}
	
	

	

}

