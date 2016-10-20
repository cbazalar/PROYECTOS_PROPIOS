// TODO Migrar al framework de reportes
package biz.belcorp.ssicc.web.scsicc.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCOMPagoLideresForm;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteCOMPagoLideresAction extends
BaseReporteAbstractAction {

	private List siccComisionList;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOMPagoLideresForm reporteForm = new ReporteCOMPagoLideresForm();
		return reporteForm;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteMaestroVertical";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteCOMPagoLideres";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCOMPagoLideresAction.prepareParameterMap' method");
		}
		
		ReporteCOMPagoLideresForm reporteForm = (ReporteCOMPagoLideresForm) this.formReporte;
		reporteForm.setTitulo(this.getReportResourceMessage("reporteCOMPagoLideresForm.titulo"));
		reporteForm.setBeforeExecuteReporte(true);		
		
		params.put("titulo", this.getReportResourceMessage("reporteCOMPagoLideresForm.titulo"));
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'ReporteCOMPagoLideresAction.setViewAtributes' method");
		}
		
		ReporteCOMPagoLideresForm form = (ReporteCOMPagoLideresForm) this.formReporte;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		siccComisionList = service.getComision();		
		String codigoPeriodo = service.getPeriodoDefaultByPaisCanal(pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT);
		form.setCodigoPais(pais.getDescripcion());
		form.setCodigoPeriodo(codigoPeriodo);
		log.debug("Todo Ok: Redireccionando");
		
	}

	public List getSiccComisionList() {
		return siccComisionList;
	}

	public void setSiccComisionList(List siccComisionList) {
		this.siccComisionList = siccComisionList;
	}

	
}
