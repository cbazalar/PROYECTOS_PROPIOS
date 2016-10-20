package biz.belcorp.ssicc.web.spusicc.lec.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.spusicc.lec.form.ReporteLECResultadosForm;


@SuppressWarnings({ "rawtypes", "unchecked" })
@ManagedBean
@SessionScoped
public class ReporteLECResultadosAction extends BaseReporteAbstractAction 
{
	private static final long serialVersionUID = -4698880017395058272L;

	private List siccRegionList;

	
	/**
	 * @return
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanReporteService()
	 */
	protected String devuelveBeanReporteService() {
		return "reportes.reporteLECResultadosService";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception 
	{
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		this.mostrarReporteOCSV = true;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais", criteria); 
	//	this.siccTramoList = reporteService.get
		
		log.debug("Todo Ok: Redireccionando");		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteLECResultadosForm reporteForm = new ReporteLECResultadosForm();
		return reporteForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteLECResultadosXLS";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("prepareParameterMap");
		}		
		ReporteLECResultadosForm reporteForm = (ReporteLECResultadosForm) this.formReporte;
		String codigoRegion = obtieneCondicion(reporteForm.getCodigoRegion(), "r.cod_regi", "'");
		params.put("region", codigoRegion);
		params.put("regionList", reporteForm.getCodigoRegion());
		params.put("campana", reporteForm.getCodigoPeriodo());
		params.put("NroReporte", "");
		params.put("titulo", "");
		params.put("tramo", reporteForm.getCodigoTramo());
		return params;
	}

	

	
	
}