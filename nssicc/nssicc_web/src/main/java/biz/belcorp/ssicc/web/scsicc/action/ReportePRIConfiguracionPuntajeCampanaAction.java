package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReportePRIConfiguracionPuntajeCampanaForm;

@ManagedBean
@SessionScoped
public class ReportePRIConfiguracionPuntajeCampanaAction extends BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8128898934380399793L;
	private String formatoReporte;


	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReportePRIConfiguracionPuntajeCampanaForm reporteForm = new ReportePRIConfiguracionPuntajeCampanaForm();
		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReportePRIConfiguracionPuntajeCampanaAction - setViewAtributes");
		}

		this.mostrarReporteXLS = true;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReportePRIConfiguracionPuntajeCampanaForm f = ( ReportePRIConfiguracionPuntajeCampanaForm ) this.formReporte ;
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
	
		f.setPeriodo(service.getPeriodoDefaultByPaisCanal(pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT));
		f.setCodigoPais(pais.getCodigo());
		
		log.debug("Todo Ok: Redireccionando");


	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))
			return "reportePRIConfiguracionPuntajeCampanaXLS";
		else
			return "reporteMaestroHorizontal"; 
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		
		return "reportePRIConfiguracionPuntajeCampanaPDF";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("prepareParameterMap...");
		}
		ReportePRIConfiguracionPuntajeCampanaForm reporte = (ReportePRIConfiguracionPuntajeCampanaForm) this.formReporte;	
		formatoReporte = reporte.getFormatoExportacion();			
		
			
		params.put("NroReporte", " ");		
		params.put("periodo", reporte.getPeriodo());
		params.put("codigoPais", reporte.getCodigoPais());
		
		params.put("titulo",getResourceMessage("reportePRIConfiguracionPuntajeCampanaForm.title"));
		
			return params;
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

