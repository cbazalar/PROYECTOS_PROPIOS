package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCOBComisionAbogadosForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes" })
public class ReporteCOBComisionAbogadosAction extends BaseReporteAbstractAction
		implements Serializable {

	private static final long serialVersionUID = 4316936254701637296L;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("JFA: Entering 'view' method");
		}
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		ReporteCOBComisionAbogadosForm form = (ReporteCOBComisionAbogadosForm) this.formReporte;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		form.setFechaInicial(sdf.format(new Date(System.currentTimeMillis())));
		form.setFechaInicialD(new Date(System.currentTimeMillis()));
		form.setFechaFinal(sdf.format(new Date(System.currentTimeMillis())));
		form.setFechaFinalD(new Date(System.currentTimeMillis()));

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOBComisionAbogadosForm form = new ReporteCOBComisionAbogadosForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteCOBComisionAbogadosXLS";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanReporteService()
	 */
	protected String devuelveBeanReporteService() {
		return "reportes.reporteCOBComisionAbogadosService";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'prepareParameterMap' method");
		}
		ReporteCOBComisionAbogadosForm form = (ReporteCOBComisionAbogadosForm) this.formReporte;
		String fecha3, fecha4;
		fecha3 = DateUtil.getDate(form.getFechaInicialD());
		fecha4 = DateUtil.getDate(form.getFechaFinalD());
		form.setFechaInicial(fecha3);
		form.setFechaFinal(fecha4);
		return params;
	}
}