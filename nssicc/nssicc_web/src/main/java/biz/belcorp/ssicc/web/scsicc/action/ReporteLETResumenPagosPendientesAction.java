package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.spusicc.lec.MantenimientoLECProgramaCorporativoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteLETResumenPagosPendientesForm;

@ManagedBean
@SessionScoped
public class ReporteLETResumenPagosPendientesAction extends
		BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7026421572291202428L;
	private List lecProgramaCorporativoList;
	


	

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteLETResumenPagosPendientesForm reporteForm = new ReporteLETResumenPagosPendientesForm();
		return reporteForm;
	}



	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReporteLETResumenPagosPendientesAction - setViewAtributes");
		}
		ReporteLETResumenPagosPendientesForm f = (ReporteLETResumenPagosPendientesForm) this.formReporte;	
		
		MantenimientoLECProgramaCorporativoService service = (MantenimientoLECProgramaCorporativoService) getBean("spusicc.mantenimientoLECProgramaCorporativoService");		
		
		Map criteria = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		criteria.put("codigoPais", pais.getCodigo());
		
	 	List programaCorporativoList = service.getProgramaCorporativoList(criteria);
	 	this.lecProgramaCorporativoList = programaCorporativoList;
	 	this.mostrarReportePDF = false;
	 	this.mostrarReporteXLS = true;
		log.debug("Todo OK: Redireccionando");

	}


	@Override
	protected String devuelveNombreReporte() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("prepareParameterMap...");
		}
		if ("XLS".equals(this.formReporte.getFormatoExportacion())){
			return "reporteLETResumenPagosPendientesXLS";
		}
		else
			return "reporteMaestroHorizontal";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("prepareParameterMap...");
		}
		ReporteLETResumenPagosPendientesForm f = (ReporteLETResumenPagosPendientesForm) this.formReporte;
		
		
		MantenimientoLECProgramaCorporativoService service = (MantenimientoLECProgramaCorporativoService) getBean("spusicc.mantenimientoLECProgramaCorporativoService");		
		
		Map criteria = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoPrograma", f.getCodigoPrograma());
		return params;
	}



	/**
	 * @return the lecProgramaCorporativoList
	 */
	public List getLecProgramaCorporativoList() {
		return lecProgramaCorporativoList;
	}



	/**
	 * @param lecProgramaCorporativoList the lecProgramaCorporativoList to set
	 */
	public void setLecProgramaCorporativoList(List lecProgramaCorporativoList) {
		this.lecProgramaCorporativoList = lecProgramaCorporativoList;
	}
}

