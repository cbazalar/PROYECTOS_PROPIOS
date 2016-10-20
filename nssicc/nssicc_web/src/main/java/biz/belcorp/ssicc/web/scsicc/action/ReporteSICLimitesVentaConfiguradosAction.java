package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSICLimitesVentaConfiguradosForm;


/**
 * The Class ReportePREConfZonasConLimiteVentasAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 21/07/2014
 */
@ManagedBean
@SessionScoped
public class ReporteSICLimitesVentaConfiguradosAction extends BaseReporteAbstractAction {

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
		ReporteSICLimitesVentaConfiguradosForm form = new ReporteSICLimitesVentaConfiguradosForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {		
		ReporteSICLimitesVentaConfiguradosForm form = (ReporteSICLimitesVentaConfiguradosForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(form.getFormatoExportacion())){
			return "reporteSICLimitesVentaConfiguradosXLS";
		}else{
			return "reporteMaestroHorizontal";
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteSICLimitesVentaConfiguradosForm form = (ReporteSICLimitesVentaConfiguradosForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("PDF".equals(form.getFormatoExportacion())){
			return "reporteSICLimitesVentaConfiguradosPDF";
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
		ReporteSICLimitesVentaConfiguradosForm reporte = (ReporteSICLimitesVentaConfiguradosForm)this.formReporte;
		
		params.put("codigoPeriodo",reporte.getCodigoPeriodo());
		params.put("NroReporte", "");		
		
		params.put("titulo",this.getResourceMessage("reporteSICLimitesVentaConfiguradosForm.title"));
				
		return params;
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("ReporteSICFacturacionAction - setViewAtributes");
		}
		this.mostrarReporteXLS = true;
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
