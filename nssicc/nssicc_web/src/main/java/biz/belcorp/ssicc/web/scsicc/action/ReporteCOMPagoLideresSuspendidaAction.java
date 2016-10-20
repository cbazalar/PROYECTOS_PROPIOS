package biz.belcorp.ssicc.web.scsicc.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCOMPagoLideresSuspendidaForm;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;


/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteCOMPagoLideresSuspendidaAction extends BaseReporteAbstractAction{
	
	private static final long serialVersionUID = 2380267022004768969L;
	private List siccComisionList;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOMPagoLideresSuspendidaForm reporteForm = new ReporteCOMPagoLideresSuspendidaForm();
		return reporteForm;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteMaestroVertical";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteCOMPagoLideresSuspendida";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'ReporteCOMPagoLideresSuspendidaAction.prepareParameterMap' method");
		}
		
		ReporteCOMPagoLideresSuspendidaForm form = (ReporteCOMPagoLideresSuspendidaForm) this.formReporte;
		form.setTitulo(this.getReportResourceMessage("reporteCOMPagoLideresSuspendidaForm.titulo"));
		form.setBeforeExecuteReporte(true);		
		
		params.put("titulo", this.getReportResourceMessage("reporteCOMPagoLideresSuspendidaForm.titulo"));
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'ReporteCOMPagoLideresSuspendidaAction.setViewAtributes' method");
		}
		
		// Seteo de valores por default de nuevos registros
		ReporteCOMPagoLideresSuspendidaForm form = (ReporteCOMPagoLideresSuspendidaForm) this.formReporte;
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
