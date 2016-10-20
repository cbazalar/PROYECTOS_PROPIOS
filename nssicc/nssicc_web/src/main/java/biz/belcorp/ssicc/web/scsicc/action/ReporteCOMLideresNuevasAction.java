package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.ClienteUAErrorService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCOMLideresNuevasForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteCOMLideresNuevasAction extends BaseReporteAbstractAction{
	
	private static final long serialVersionUID = 1L;
	
	private List siccPeriodoList;
	private LabelValue[] siccComisionList;
	private String formatoReporte;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOMLideresNuevasForm reporteForm = new ReporteCOMLideresNuevasForm();
		return reporteForm;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("PDF".equals(this.formatoExportacion)) {
			return "lideresNuevas";			
		} 
		else {
			return "reporteMaestroHorizontal";
		} 
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {		
		return null;
	}
	
	
	/**
	 * Metodo para ejecutar el PDF
	 * @param evt
	 */
	public void executePDF(ActionEvent evt) {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'dc' method");
		}
		try {
			HttpServletResponse response = this.getResponse();
			log.debug(" *********** Inicio de la generación del Reporte Pago Lideres ********");
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			ClienteUAErrorService service = (ClienteUAErrorService) getBean("sisicc.clienteUAErrorService");
			ReporteCOMLideresNuevasForm reportForm = (ReporteCOMLideresNuevasForm) this.formReporte;
			
			if (log.isDebugEnabled()) {
				log.debug("Generando reporte...");
			}
			Map params = null;
			params = BeanUtils.describe(reportForm);
			

			byte[] bytes = service.getBytesReporteCOMLideresNuevas(params, usuario,
					pais);
			this.viewPDFMedia = true;
			if (bytes.length > 0) {
				if (log.isDebugEnabled()) {
					log.debug("Imprimiendo reporte...");
				}
				this.mPantallaPrincipalBean.setViewPDFMedia(true);
				response.reset();
				response.setContentType("application/pdf");
				response.addHeader("Content-Disposition", "inline");
				response.setContentLength(bytes.length);
				response.getOutputStream().write(bytes);
				response.getOutputStream().flush();
				response.getOutputStream().close();
				this.responseComplete();
				this.viewReporteMedia = true;
			} else
				log.debug("No se encontraron Datos para generar el reporte...");
			
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		
		}
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering ReporteCOMLideresNuevasAction - 'setFindAttributes()' - method");
		}

		ClienteUAErrorService service = (ClienteUAErrorService) getBean("sisicc.clienteUAErrorService");
		ReporteCOMLideresNuevasForm form = (ReporteCOMLideresNuevasForm)this.formReporte;
		
		Map params = new HashMap();
		params.put("codigoPais", this.mPantallaPrincipalBean.getCurrentUser().getPais().getCodigo());
		params.put("codigoPeriodo", form.getCodigoPeriodo());

		return service.getLideresNuevasByCriteria(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception{
		
		return null;
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering ReporteCOMLideresNuevasAction - 'setViewAtributes()' - method");
		}		
		this.mostrarReportePDF = false;
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteCOMLideresNuevasForm f = (ReporteCOMLideresNuevasForm) this.formReporte;		
		
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		//String periodoDefecto = svc.getPeriodoDefaultByPaisCanal(pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT);
		
		f.setCodigoPais(pais.getCodigo());
		f.setDescPais(pais.getDescripcion());
		//f.setCodigoPeriodo(periodoDefecto);
		
	}

	//GETTERS && SETTERS

	/**
	 * @return
	 */
	public List getSiccPeriodoList() {
		return siccPeriodoList;
	}

	/**
	 * @param siccPeriodoList
	 */
	public void setSiccPeriodoList(List siccPeriodoList) {
		this.siccPeriodoList = siccPeriodoList;
	}

	/**
	 * @return
	 */
	public LabelValue[] getSiccComisionList() {
		return siccComisionList;
	}

	/**
	 * @param siccComisionList
	 */
	public void setSiccComisionList(LabelValue[] siccComisionList) {
		this.siccComisionList = siccComisionList;
	}

	/**
	 * @return
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}
}
