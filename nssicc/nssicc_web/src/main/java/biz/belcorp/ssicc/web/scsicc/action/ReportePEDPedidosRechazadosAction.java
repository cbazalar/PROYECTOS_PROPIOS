package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReportePEDPedidosRechazadosForm;

@ManagedBean
@SessionScoped
public class ReportePEDPedidosRechazadosAction extends BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = 5452137025798023586L;
	
	/*
	 * (non-Javadoc)
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReportePEDPedidosRechazadosForm reporteForm = new ReportePEDPedidosRechazadosForm();
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
		return "reportePEDPedidosRechazadosXLS";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreSubReporte()
	 */
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("Entró ReportePEDPedidosRechazadosAction - setViewAtributes()");
		}
		
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		
		ReportePEDPedidosRechazadosForm f = (ReportePEDPedidosRechazadosForm) this.formReporte;
		
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
		if(log.isDebugEnabled()){
			log.debug("Entró ReportePEDPedidosRechazadosAction - prepareParameterMap");
		}

		ReportePEDPedidosRechazadosForm f = (ReportePEDPedidosRechazadosForm) this.formReporte;
		
		return params;
	}
}