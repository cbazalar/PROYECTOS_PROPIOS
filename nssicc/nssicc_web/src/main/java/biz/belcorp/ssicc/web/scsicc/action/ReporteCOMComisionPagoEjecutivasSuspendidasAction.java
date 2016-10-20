package biz.belcorp.ssicc.web.scsicc.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCOMComisionPagoEjecutivasSuspendidasForm;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteCOMComisionPagoEjecutivasSuspendidasAction extends BaseReporteAbstractAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3851524405375205814L;
	private String formatoReporte;
	private List comcodComisionList;
	private List siccMarcaList;
	private List siccCanalList;
	private String siccPeriodoInicialList;
	private List siccComisionList;
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOMComisionPagoEjecutivasSuspendidasForm reporteForm = new ReporteCOMComisionPagoEjecutivasSuspendidasForm();
		return reporteForm;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteMaestroHorizontal";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteCOMComisionPagoEjecutivasSuspendidas";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCOMComisionPagoEjecutivasAction.prepareParameterMap' method");
		}
		ReporteCOMComisionPagoEjecutivasSuspendidasForm reportePagoEjecSus = (ReporteCOMComisionPagoEjecutivasSuspendidasForm) this.formReporte;
		formatoReporte = reportePagoEjecSus.getFormatoExportacion();
		reportePagoEjecSus.setTitulo(this.getReportResourceMessage("reporteCOMComisionPagoEjecutivasSuspendidasForm.titulo"));
		reportePagoEjecSus.setBeforeExecuteReporte(true);
		
		params.put("CodigoPais", reportePagoEjecSus.getCodigoPais());
		params.put("CodigoComision", reportePagoEjecSus.getCodigoComision());
		params.put("CodigoPeriodo", reportePagoEjecSus.getCodigoPeriodo());
		params.put("ComisionIngresos", reportePagoEjecSus.getComisionIngresos());
		params.put("titulo", this.getReportResourceMessage("reporteCOMComisionPagoEjecutivasSuspendidasForm.titulo"));
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'ReporteCOMComisionPagoEjecutivasSuspendidasAction.setViewAtributes' method");
		}
		
		ReporteCOMComisionPagoEjecutivasSuspendidasForm form = (ReporteCOMComisionPagoEjecutivasSuspendidasForm) this.formReporte;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String codigoPeriodo = service.getPeriodoDefaultByPaisCanal(pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT);
		
		form.setCodigoPais(pais.getDescripcion());
		form.setCodigoPeriodo(codigoPeriodo);
		
		comcodComisionList = service.getListCodComision(pais.getCodigo());
		siccMarcaList = service.getMarcas();
		siccCanalList = service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		siccComisionList = service.getComision();
		siccPeriodoInicialList = service.getPeriodoDefaultByPaisCanal(pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT);
		
		log.debug("Todo Ok: Redireccionando");
		
	}

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
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
