package biz.belcorp.ssicc.web.scsicc.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReportePERRegistroControlClienteForm;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano
 *         Huamán</a>
 * 
 */
/**
 * @author Sigcomt
 *
 */
@ManagedBean
@SessionScoped
public class ReportePERRegistroControlClienteAction extends
		BaseReporteAbstractAction {

	private String tipoReporte;
	private List listTipoDocumento;

	public List getListTipoDocumento() {
		return listTipoDocumento;
	}

	public void setListTipoDocumento(List listTipoDocumento) {
		this.listTipoDocumento = listTipoDocumento;
	}

	public String getTipoReporte() {
		return tipoReporte;
	}

	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	private static final long serialVersionUID = 5452137025798023586L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReportePERRegistroControlClienteForm reporteForm = new ReportePERRegistroControlClienteForm();
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
		return "reporteMaestroHorizontalLibroPercepciones";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreSubReporte()
	 */
	protected String devuelveNombreSubReporte() throws Exception {

		if (("PDF".equals(tipoReporte)) || ("VPDF".equals(tipoReporte)))
			return "reportePERRegistroControlClienteEsquemas";
		return "reportePERRegistroControlClienteEsquemas";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()ReporteRECMercaderiaSiniestradaAction
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {

		ReportePERRegistroControlClienteForm form = (ReportePERRegistroControlClienteForm) this.formReporte;
		this.tipoReporte = form.getFormatoExportacion();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		InterfazSiCCService siccService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Map criteria = new HashMap();
		criteria.put("codigoIdioma", usuario.getIdioma().getCodigoISO());
		criteria.put("codigoPais", pais.getCodigo());  
		this.listTipoDocumento= siccService.getTipoDocumentosByCodigoISO(criteria);     
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		form.setFechaDesde(sdf.format(new Date(System.currentTimeMillis())));
		form.setFechaDesdeDt(new Date(System.currentTimeMillis()));

		form.setFechaHasta(sdf.format(new Date(System.currentTimeMillis())));
		form.setFechaHastaDt(new Date(System.currentTimeMillis()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap()
	 */
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReportePERRegistroControlClienteForm.prepareParameterMap' method");
		}

		try {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ReportePERRegistroControlClienteForm.prepareParameterMap' method");
			}

			try {

				InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
				ReportePERRegistroControlClienteForm reporteForm = (ReportePERRegistroControlClienteForm) this.formReporte;
				super.prepareParamsBeforeExecute(params, reporteForm);
				String fecha1,fecha2;
				fecha1 = DateUtil.getDate(reporteForm.getFechaDesdeDt());
				fecha2 = DateUtil.getDate(reporteForm.getFechaHastaDt());
				reporteForm.setFechaDesde(fecha1);
				reporteForm.setFechaHasta(fecha2);
				params.put("NroReporte",getReportResourceMessage("reportePERRegistroControlClienteForm.regimen"));
				params.put("superiorIzquierdaPer", getReportResourceMessage("reporte.maestro.cetco.ruc"));
				params.put("condicionFechaHora", "NO");
				params.put("condicionUsuario", "NO");
				params.put("titulo",getReportResourceMessage("reportePERRegistroControlClienteForm.titulo")
								+ reporteForm.getFechaDesde()
								+ " "
								+ getReportResourceMessage("reportePERRegistroControlClienteForm.al")
								+ " " + reporteForm.getFechaHasta());
				params.put("codigoPaisLbel", reporteForm.getCodigoPais()
						.substring(0, 2) + Constants.FIN_CODIGO_PAIS_LBEL);
				reporteForm.setBeforeExecuteReporte(true);
				return params;

			} catch (Exception e) {

				e.printStackTrace();
			}
			return params;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;

	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanReporteService()
	 */
	protected String devuelveBeanReporteService(){
		return "reportes.reportePERRegistroControlClienteService";
	}
	
	public String setValidarReporte() {
		ReportePERRegistroControlClienteForm reporteRETForm = (ReportePERRegistroControlClienteForm) this.formReporte;
	    if (reporteRETForm.getFechaDesdeDt().compareTo(reporteRETForm.getFechaHastaDt()) > 0) 
				return "La fecha inicial no puede ser mayor a la fecha final";
			
	    return null;
	}
	


	/**
	 * @param val
	 */

}