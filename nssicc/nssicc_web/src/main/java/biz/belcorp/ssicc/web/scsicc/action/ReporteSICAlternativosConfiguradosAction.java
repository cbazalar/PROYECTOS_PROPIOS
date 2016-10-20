package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSICAlternativosConfiguradosForm;


/**
 * The Class ReportePREConfZonasConLimiteVentasAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 21/07/2014
 */
@ManagedBean
@SessionScoped
public class ReporteSICAlternativosConfiguradosAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The sufijo reporte. */
	private String sufijoReporte;
	
	private List siccTipoOfertaList = new ArrayList();
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSICAlternativosConfiguradosForm form = new ReporteSICAlternativosConfiguradosForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteSICAlternativosConfiguradosForm form = (ReporteSICAlternativosConfiguradosForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(form.getFormatoExportacion())){
			return "reporteSICAlternativosConfiguradosXLS";
		}else{
			return "reporteMaestroHorizontal";
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteSICAlternativosConfiguradosForm form = (ReporteSICAlternativosConfiguradosForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("PDF".equals(form.getFormatoExportacion())){
			return "reporteSICAlternativosConfiguradosPDF";
		}
		return "";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("prepareParameterMap");
		}
		ReporteSICAlternativosConfiguradosForm reporte = (ReporteSICAlternativosConfiguradosForm)this.formReporte;
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteria = new HashMap();
		criteria.put("codigoPais",reporte.getCodigoPais());
		String numero=reporteService.getMaximoValorOferta(criteria);

		params.put("maximoValor", numero);
		params.put("codigoPeriodo", reporte.getCodigoPeriodo());
		params.put("NroReporte", "");	
		
		params.put("titulo",this.getResourceMessage("reporteSICAlternativosConfiguradosForm.title"));
				
		return params;
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		this.mostrarReporteXLS = true;
		ReporteSICAlternativosConfiguradosForm reporteSICForm = (ReporteSICAlternativosConfiguradosForm) formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		criteriaOperacion.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteriaOperacion.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		String periodoActual = reporteService.getStringGenerico(
				"getPeriodoByFechaActual", criteriaOperacion);
		reporteSICForm.setCodigoPeriodo(periodoActual);
	}
	
	public String getSufijoReporte() {
		return sufijoReporte;
	}

	public void setSufijoReporte(String sufijoReporte) {
		this.sufijoReporte = sufijoReporte;
	}

	public List getSiccTipoOfertaList() {
		return siccTipoOfertaList;
	}

	public void setSiccTipoOfertaList(List siccTipoOfertaList) {
		this.siccTipoOfertaList = siccTipoOfertaList;
	}

}
