package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSICRecuperacionesConfiguradosForm;


/**
 * The Class ReportePREConfZonasConLimiteVentasAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 21/07/2014
 */
@ManagedBean
@SessionScoped
public class ReporteSICRecuperacionesConfiguradosAction extends BaseReporteAbstractAction {

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
		ReporteSICRecuperacionesConfiguradosForm form = new ReporteSICRecuperacionesConfiguradosForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {		
		ReporteSICRecuperacionesConfiguradosForm form = (ReporteSICRecuperacionesConfiguradosForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(form.getFormatoExportacion())){
			return "reporteSICRecuperacionesConfiguradosXLS";
		}else{
			return "reporteMaestroHorizontal";
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteSICRecuperacionesConfiguradosForm form = (ReporteSICRecuperacionesConfiguradosForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("PDF".equals(form.getFormatoExportacion())){
			return "reporteSICRecuperacionesConfiguradosPDF";
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
		ReporteSICRecuperacionesConfiguradosForm reporte = (ReporteSICRecuperacionesConfiguradosForm)this.formReporte;
		
		params.put("codigoPeriodo",reporte.getCodigoPeriodo());
		params.put("NroReporte", "");		
		
		params.put("titulo",this.getResourceMessage("reporteSICRecuperacionesConfiguradosForm.title"));
				
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
