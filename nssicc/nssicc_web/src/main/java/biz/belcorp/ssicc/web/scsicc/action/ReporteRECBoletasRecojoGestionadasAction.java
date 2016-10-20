/**
 * 
 */
package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteRECBoletasRecojoGestionadasForm;

/**
 * The Class ReporteRECBoletasRecojoGestionadasAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 07/11/2014
 */
@SuppressWarnings("rawtypes")
@ManagedBean
@SessionScoped
public class ReporteRECBoletasRecojoGestionadasAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = -4966095525371387998L;
	private String formatoReporte;
	private List siccRegionList;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteRECBoletasRecojoGestionadasForm form = new ReporteRECBoletasRecojoGestionadasForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@SuppressWarnings({ "unchecked" })
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteRECBoletasRecojoGestionadasAction.setViewAtributes' method");
		}		
		this.mostrarReporteOCSV = false;
		this.mostrarReporteXLSX = false;
		this.mostrarReporteCSV = false;
		this.mostrarReportePDF = false;
		this.mostrarReporteXLS = true;
		this.mostrarReporteOJXLSX = false;
		this.mostrarReporteOOXLSX = false;		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		setSiccRegionList(reporteService.getListaGenerico("getRegionesByPais", criteriaOperacion));	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		this.setFormatoReporte(((ReporteRECBoletasRecojoGestionadasForm)this.formReporte).getFormatoExportacion()); 
		if ("XLS".equals(getFormatoReporte())) {
			return "reporteRECBoletasRecojoGestionadasXLS";
		} else {
			return "reporteMaestroHorizontal";
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {	
		return "reporteRECBoletasRecojoGestionadasPDF";		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteRECBoletasRecojoGestionadasForm reporteRECForm = (ReporteRECBoletasRecojoGestionadasForm) this.formReporte;
		this.setFormatoExportacion(reporteRECForm.getFormatoExportacion());
		params.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		params.put("codigoPeriodoInicio", reporteRECForm.getCodigoPeriodoInicio());
		params.put("codigoPeriodoFin", reporteRECForm.getCodigoPeriodoFin());
		params.put("region", this.obtieneCondicion(reporteRECForm.getRegionList(), "c.cod_regi", "'"));
		params.put("titulo", this.getReportResourceMessage("reporteRECBoletasRecojoGestionadasForm.title"));
		log.debug("Imprimiendo parámetros");
		log.debug(params);
		log.debug("Fin parámetros");
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



}
