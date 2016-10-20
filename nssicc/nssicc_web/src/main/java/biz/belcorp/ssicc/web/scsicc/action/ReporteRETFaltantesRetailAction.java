// TODO Migrar al framework de reportes
package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteRETFaltantesRetailForm;

/**
 * @author César Estrada
 * @company Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteRETFaltantesRetailAction extends
		BaseReporteAbstractAction implements Serializable {

	private String formatoReporte;
	private List siccRegionList;

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	

	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
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
		ReporteRETFaltantesRetailForm reporteForm = new ReporteRETFaltantesRetailForm();
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

		if (StringUtils.equals(formatoReporte,"XLS")) {
			return "reporteRETFaltantesRetailXLS";	
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

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		// se carga el combo de regiones
		siccRegionList=reporteService.getListaGenerico(
				"getRegionesByPaisActivasNoActivas",
				criteriaOperacion);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap()
	 */
	protected Map prepareParameterMap(Map params) throws Exception {

		ReporteRETFaltantesRetailForm reporteform = (ReporteRETFaltantesRetailForm) this.formReporte;
		//se obtiene el formato de exportacion, para este caso solo sera xls
		formatoReporte = reporteform.getFormatoExportacion();
		

		String codPeriodo = StringUtils.EMPTY;
		String codRegion = StringUtils.EMPTY;
		//se obtiene el codigo de periodo
		codPeriodo = reporteform.getCodigoPeriodo();
		//se obtiene el codigo de region
		codRegion = this.obtieneCondicion(reporteform.getCodigoRegion(), "REG.COD_REGI", "'");//reporteform.getCodigoRegion();

		params.put("codigoPeriodo", codPeriodo);
		params.put("codigoRegion", codRegion);
		//params.put("titulo", getMessageReporte("reporteCOMDetalleComisionRecuperacionForm.titulo", request));
		params.put("titulo", "");

		return params;

	}
}