// TODO Migrar al framework de reportes
package biz.belcorp.ssicc.web.scsicc.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCOMComisionPagoEjecutivasForm;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteCOMComisionPagoEjecutivasAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = -1091798983329193025L;
	
	private String formatoReporte;
	private List comcodComisionList;
	private List siccMarcaList;
	private List siccCanalList;
	private String siccPeriodoInicialList;
	private List siccComisionList;
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOMComisionPagoEjecutivasForm reporteForm = new ReporteCOMComisionPagoEjecutivasForm();
		return reporteForm;
	}
	
	@Override
	protected String devuelveNombreReporte() throws Exception {
		this.formatoReporte = ((ReporteCOMComisionPagoEjecutivasForm)this.formReporte).getFormatoExportacion(); 
		if ("PDF".equals(this.formatoReporte)){
			return "reporteMaestroHorizontal";
		}else{
			return "reporteCOMComisionPagoEjecutivasXLS";
		}
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		String subReporte = "";
		if ("PDF".equals(this.formatoReporte)){
			subReporte = "reporteCOMComisionPagoEjecutivas";
		}
		return subReporte;
	}
	
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCOMComisionPagoEjecutivasAction.prepareParameterMap' method");
		}
		ReporteCOMComisionPagoEjecutivasForm reportePagoEjec = (ReporteCOMComisionPagoEjecutivasForm) this.formReporte;
		formatoReporte = reportePagoEjec.getFormatoExportacion();
		reportePagoEjec.setTitulo(this.getReportResourceMessage("reporteCOMComisionPagoEjecutivasForm.titulo"));
		reportePagoEjec.setBeforeExecuteReporte(true);
		
		params.put("CodigoPais", reportePagoEjec.getCodigoPais());
		params.put("CodigoComision", reportePagoEjec.getCodigoComision());
		params.put("CodigoPeriodo", reportePagoEjec.getCodigoPeriodo());
		params.put("ComisionIngresos",reportePagoEjec.getComisionIngresos());
		params.put("titulo", this.getReportResourceMessage("reporteCOMComisionPagoEjecutivasForm.titulo"));
		return params;
		
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'ReporteCOMComisionPagoEjecutivasAction.setViewAtributes' method");
		}
		this.mostrarReporteXLS = true;
		// Seteo de valores por default de nuevos registros
		ReporteCOMComisionPagoEjecutivasForm form = (ReporteCOMComisionPagoEjecutivasForm) this.formReporte;	
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String codigoPeriodo = service.getPeriodoDefaultByPaisCanal(pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT);
		
		form.setCodigoPais(pais.getCodigo());
		form.setCodigoPeriodo(codigoPeriodo);
		
		comcodComisionList = service.getListCodComision(pais.getCodigo());
		siccMarcaList = service.getMarcas();
		siccCanalList = service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		siccComisionList = service.getComision();
		siccPeriodoInicialList = service.getPeriodoDefaultByPaisCanal(pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT);
		
		log.debug("Todo Ok: Redireccionando");
	}

	
	public List getComcodComisionList() {
		return comcodComisionList;
	}

	public void setComcodComisionList(List comcodComisionList) {
		this.comcodComisionList = comcodComisionList;
	}

	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	public List getSiccCanalList() {
		return siccCanalList;
	}

	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	public String getSiccPeriodoInicialList() {
		return siccPeriodoInicialList;
	}

	public void setSiccPeriodoInicialList(String siccPeriodoInicialList) {
		this.siccPeriodoInicialList = siccPeriodoInicialList;
	}

	public List getSiccComisionList() {
		return siccComisionList;
	}

	public void setSiccComisionList(List siccComisionList) {
		this.siccComisionList = siccComisionList;
	}

	
	
	
	

}
