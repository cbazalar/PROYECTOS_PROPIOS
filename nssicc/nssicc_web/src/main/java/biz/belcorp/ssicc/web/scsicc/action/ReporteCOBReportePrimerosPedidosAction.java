package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCOBReportePrimerosPedidosForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
public class ReporteCOBReportePrimerosPedidosAction extends
		BaseReporteAbstractAction implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6354031738581530682L;
	private String formatoReporte;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccSeccionList = {};
	private LabelValue[] siccZonaList = {};

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOBReportePrimerosPedidosForm form = new ReporteCOBReportePrimerosPedidosForm();
		return form;
	}

	protected String devuelveBeanReporteService() {
		return "reportes.reporteCOBReportePrimerosPedidosService";
	}  
	
	@Override
	protected void setViewAtributes() throws Exception {

		this.mostrarReporteXLS = true;

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteriaOperacion = new HashMap();

		criteriaOperacion.put("codigoPais", pais.getCodigo());
		List listaRegiones = new ArrayList();
		listaRegiones = reporteService.getListaGenerico("getRegionesByPais",
				criteriaOperacion);
		this.siccRegionList = new LabelValue[listaRegiones.size()];
		int z = 0;
		for (Object object : listaRegiones) {
			LabelValue labelValue = new LabelValue();
			labelValue.setLabel(((Base) object).getDescripcion());
			labelValue.setValue(((Base) object).getCodigo());
			this.getSiccRegionList()[z] = labelValue;
			z++;
		}

	}
	
	public String setValidarReporte() {
		ReporteCOBReportePrimerosPedidosForm form = (ReporteCOBReportePrimerosPedidosForm)this.formReporte;
		int codperini = Integer.parseInt(form.getCodigoPeriodoInicio());
		int codperfin = Integer.parseInt(form.getCodigoPeriodoFin());
		if(codperfin<codperini){
			String mensaje =  this.getResourceMessage("reporteCOBReportePrimerosPedidosForm.msg.validacionCampanha");
			return mensaje;
		}

	    					
	    return null;
	}

	public void loadzonas(ValueChangeEvent val) {
		log.debug("loadzonas");

		String[] valores = (String[]) val.getNewValue();
		if (!val.equals(null) && valores.length > 0) {
			String[] regiones = (String[]) val.getNewValue();

			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			this.siccZonaList = aSvc
					.getZonasMultipleByPaisMarcaCanalRegion(
							this.mPantallaPrincipalBean.getCurrentCountry()
									.getCodigo(),
							Constants.CODIGO_MARCA_DEFAULT,
							Constants.CODIGO_CANAL_DEFAULT, regiones,
							Constants.FORMATO_TOTAL);

		} else {
			setSiccZonaList(null);
			setSiccSeccionList(null);

		}
	}

	//
	public void loadseccion(ValueChangeEvent val) {
		log.debug("loadseccion");
		ReporteCOBReportePrimerosPedidosForm form = (ReporteCOBReportePrimerosPedidosForm) this.formReporte;
		String[] regiones = (String[]) form.getCodigoRegion();
		String[] zonas = (String[]) val.getNewValue();
		if (regiones.length > 0 && zonas.length > 0) {
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			setSiccSeccionList(aSvc
					.getSeccionMultipleByPaisMarcaCanalRegionZona(
							this.mPantallaPrincipalBean.getCurrentCountry()
									.getCodigo(),
							Constants.CODIGO_MARCA_DEFAULT,
							Constants.CODIGO_CANAL_DEFAULT, regiones, zonas,
							Constants.FORMATO_TOTAL));

		} else {
			setSiccSeccionList(null);

		}

	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))
			return "reporteCOBReportePrimerosPedidosXLS";
		else
			return "reporteMaestroHorizontal";

	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		if ("PDF".equals(formatoReporte))
			 return "reporteCOBReportePrimerosPedidosPDF";
		else
			return "";

	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {

				
		ReporteCOBReportePrimerosPedidosForm reporteCOBForm = (ReporteCOBReportePrimerosPedidosForm) this.formReporte;
		formatoReporte = reporteCOBForm.getFormatoExportacion();
		
		String condicionRegion = "";
		String condicionZona = "";
		String condicionSeccion = "";

		condicionRegion = this.obtieneCondicion(reporteCOBForm.getCodigoRegion(), "zr.COD_REGI", "'");
		condicionZona = this.obtieneCondicion(reporteCOBForm.getCodigoZona(), "zz.COD_ZONA", "'");
		condicionSeccion = this.obtieneCondicion(reporteCOBForm.getCodigoSeccion(), "zs.COD_SECC", "'");
		 
		params.put("titulo", getReportResourceMessage("reporteCOBReportePrimerosPedidosForm.titulo"));			
		
		
		params.put("codigoPais",reporteCOBForm.getCodigoPais());
		params.put("codigoPeriodoInicio",reporteCOBForm.getCodigoPeriodoInicio());
		params.put("codigoPeriodoFin",reporteCOBForm.getCodigoPeriodoFin());
		params.put("condicionRegion", condicionRegion!=null?condicionRegion:"");
		params.put("condicionZona", condicionZona!=null?condicionZona:"");
		params.put("condicionSeccion", condicionSeccion!=null?condicionSeccion:"");
				
		reporteCOBForm.setFormatoExportacion(formatoReporte);
		return params;
	}

	/**
	 * @return the formatoReporte
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte
	 *            the formatoReporte to set
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	/**
	 * @return the siccRegionList
	 */
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 *            the siccRegionList to set
	 */
	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccSeccionList
	 */
	public LabelValue[] getSiccSeccionList() {
		return siccSeccionList;
	}

	/**
	 * @param siccSeccionList
	 *            the siccSeccionList to set
	 */
	public void setSiccSeccionList(LabelValue[] siccSeccionList) {
		this.siccSeccionList = siccSeccionList;
	}

	/**
	 * @return the siccZonaList
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 *            the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}
}
