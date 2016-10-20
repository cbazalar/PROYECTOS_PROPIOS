package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang3.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSACAtendidoxFechaForm;

/**
 * @author Carlos Bazalar
 * @company Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteSACAtendidoxFechaAction extends	BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = 6086571940584435828L;
	private static final String REPORTE_CONSOLIDADO = "C";
	private static final String REPORTE_DETALLADO = "D";
	
	private List siccSociedadList = new ArrayList();

	@Override
	protected void setViewAtributes() throws Exception {
		log.info("Entro a ReporteSACAtendidoxFechaAction - setViewAttributes");
		this.mostrarReportePDF = false;
		this.mostrarReporteXLS = true;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		this.siccSociedadList = service.getSociedadesByCodigoPais(pais.getCodigo());
		ReporteSACAtendidoxFechaForm form = (ReporteSACAtendidoxFechaForm) this.formReporte;
		form.setCodigoPais(pais.getCodigo());

		log.info("Salio a ReporteSACAtendidoxFechaAction - setViewAttributes");
	}

	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteSACAtendidoxFechaForm f = (ReporteSACAtendidoxFechaForm) this.formReporte;
		String tipoReporte = f.getTipoReporte();
		
		if (StringUtils.equals(tipoReporte, REPORTE_CONSOLIDADO)) 
			return "reporteSACAtendidoxFechaConsoXLS";
		else
			return "reporteSACAtendidoxFechaDetalXLS";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSACAtendidoxFechaForm form = new ReporteSACAtendidoxFechaForm();
		return form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteSACAtendidoxFechaForm f = (ReporteSACAtendidoxFechaForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		String tipoReporte = f.getTipoReporte();
		if (StringUtils.equals(tipoReporte, REPORTE_CONSOLIDADO)) 
			params.put("titulo", getReportResourceMessage("reporteSACAtendidoxFechaConso.titulo"));
		else
			params.put("titulo", getReportResourceMessage("reporteSACAtendidoxFechaDetal.titulo"));
		
		if (f.getFechaFacturacionD() != null) {
			String fechaFacturacion = DateUtil.convertDateToString(f.getFechaFacturacionD());
			params.put("fechaFacturacion", fechaFacturacion);
		}
	
		return params;
	}
	
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map<String, Object> params, BaseForm form) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("prepareParamsBeforeExecute");
		}
		ReporteSACAtendidoxFechaForm f = (ReporteSACAtendidoxFechaForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		String tipoReporte = f.getTipoReporte();
		
		if (StringUtils.equals(tipoReporte, REPORTE_CONSOLIDADO)) {
			reporteService.insertarReporteSACAtendidoxFechaConsolidado(params);
		}
		else {
			reporteService.insertarReporteSACAtendidoxFechaDetallado(params);
		}
		
		return params;
	}
	
	
	
	
	/* GET - SET */

	/**
	 * @return the siccSociedadList
	 */
	public List getSiccSociedadList() {
		return siccSociedadList;
	}


	/**
	 * @param siccSociedadList the siccSociedadList to set
	 */
	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}
	
	
	

	
}