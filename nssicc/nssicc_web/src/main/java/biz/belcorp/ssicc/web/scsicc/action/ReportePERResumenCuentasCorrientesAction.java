package biz.belcorp.ssicc.web.scsicc.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.springframework.core.io.ClassPathResource;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReportePERResumenCuentasCorrientesForm;


/**
 * The Class ReportePERResumenCuentasCorrientesAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 26/11/2014
 */
@SuppressWarnings("rawtypes")
@ManagedBean
@SessionScoped
public class ReportePERResumenCuentasCorrientesAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 8237862379254804727L;
	private String secuencial1 = "0";
	private String secuencial2 = "0";
	private List siccSociedadList;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReportePERResumenCuentasCorrientesForm form = new ReportePERResumenCuentasCorrientesForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReportePERResumenCuentasCorrientesAction.setViewAtributes' method");
		}		
		this.mostrarReporteOCSV = false;
		this.mostrarReporteXLSX = false;
		this.mostrarReporteCSV = false;
		this.mostrarReportePDF = true;
		this.mostrarReporteXLS = false;
		this.mostrarReporteOJXLSX = false;
		this.mostrarReporteOOXLSX = false;
		
		// Cargamos los combos a utilizar
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		
		ReportePERResumenCuentasCorrientesForm reportePERForm = (ReportePERResumenCuentasCorrientesForm) this.formReporte;
		reportePERForm.setFechaDesdeD(new Date(System.currentTimeMillis()));
		reportePERForm.setFechaHastaD(new Date(System.currentTimeMillis()));
		
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		this.siccSociedadList = svc.getSociedadesByCodigoPais(pais.getCodigo());
	}

	@Override
	protected String devuelveBeanReporteService(){
		return "reportes.reportePERResumenCuentasCorrientesService";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reportePERResumenCuentasCorrientes";
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteMaestroVertical";
	}
	
	public String setValidarReporte() {
		ReportePERResumenCuentasCorrientesForm r = (ReportePERResumenCuentasCorrientesForm) this.formReporte;
	    if (r.getFechaDesdeD().compareTo(r.getFechaHastaD()) > 0) {
			return getResourceMessage("reporteRETDetalleComisionVentaRetailForm.validar.fechas");
	    }	
	    return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		
		ReportePERResumenCuentasCorrientesForm reportePERForm = (ReportePERResumenCuentasCorrientesForm) this.formReporte;
		String fecha1 = DateUtil.getDate(reportePERForm.getFechaDesdeD());
 		String fecha2 = DateUtil.getDate(reportePERForm.getFechaHastaD());
 		reportePERForm.setFechaDesde(fecha1);
 		reportePERForm.setFechaHasta(fecha2);

		ClassPathResource resource = new ClassPathResource(
				Constants.JASPER_DIRECTORIO + "subReportePERCargosFacturacion" + JASPER_EXTENSION);
		ClassPathResource resource1 = new ClassPathResource(
				Constants.JASPER_DIRECTORIO + "subReportePERCargosDirectos" + JASPER_EXTENSION);
		ClassPathResource resource2 = new ClassPathResource(
				Constants.JASPER_DIRECTORIO + "subReportePERAbonosNotaCredito" + JASPER_EXTENSION);
		ClassPathResource resource3 = new ClassPathResource(
				Constants.JASPER_DIRECTORIO + "subReportePERAbonosDirectos" + JASPER_EXTENSION);
		ClassPathResource resource4 = new ClassPathResource(
				Constants.JASPER_DIRECTORIO + "subReportePERAbonosDirectosPercepciones" + JASPER_EXTENSION);
		ClassPathResource resource5 = new ClassPathResource(
				Constants.JASPER_DIRECTORIO + "subReportePERAbonosCobranza" + JASPER_EXTENSION);

		
		params.put("SUBREPORT_DIR1", (JasperReport) JRLoader
				.loadObject(this.getClass().getClassLoader().getResource(resource.getPath() )));
		params.put("SUBREPORT_DIR2", (JasperReport) JRLoader
				.loadObject(this.getClass().getClassLoader().getResource(resource1.getPath() )));
		params.put("SUBREPORT_DIR3", (JasperReport) JRLoader
				.loadObject(this.getClass().getClassLoader().getResource(resource2.getPath() )));
		params.put("SUBREPORT_DIR4", (JasperReport) JRLoader
				.loadObject(this.getClass().getClassLoader().getResource(resource3.getPath() )));
		params.put("SUBREPORT_DIR5", (JasperReport) JRLoader
				.loadObject(this.getClass().getClassLoader().getResource(resource4.getPath() )));
		params.put("SUBREPORT_DIR6", (JasperReport) JRLoader
				.loadObject(this.getClass().getClassLoader().getResource(resource5.getPath() )));
		
		params.put("secuencial1", this.secuencial1);
		params.put("secuencial2", this.secuencial2);
		params.put("NroReporte", " ");
		params.put("titulo", getReportResourceMessage("reportePERResumenCuentasCorrientesForm.titulo"));
		
		log.debug("Imprimiendo parámetros");
		log.debug(params);
		log.debug("Fin parámetros");
		return params;
	}

	/**
	 * @return the secuencial1
	 */
	public String getSecuencial1() {
		return secuencial1;
	}

	/**
	 * @param secuencial1 the secuencial1 to set
	 */
	public void setSecuencial1(String secuencial1) {
		this.secuencial1 = secuencial1;
	}

	/**
	 * @return the secuencial2
	 */
	public String getSecuencial2() {
		return secuencial2;
	}

	/**
	 * @param secuencial2 the secuencial2 to set
	 */
	public void setSecuencial2(String secuencial2) {
		this.secuencial2 = secuencial2;
	}

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