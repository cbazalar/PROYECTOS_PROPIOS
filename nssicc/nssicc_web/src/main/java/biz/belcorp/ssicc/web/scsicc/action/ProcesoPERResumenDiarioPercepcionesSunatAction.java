package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.percepciones.ResumenDiarioPercepcionesSunatService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ProcesoPERResumenDiarioPercepcionesSunatForm;


@ManagedBean
@SessionScoped
public class ProcesoPERResumenDiarioPercepcionesSunatAction extends BaseProcesoAbstractAction  {

	private static final long serialVersionUID = 3152404367735292363L;
	private String formatoExportacion;
	
	private boolean mostrarReporteOTXT = true;
	private boolean mostrarReporteOCSV = true;
	
	@ManagedProperty(value = "#{reportePERResumenDiarioPercepcionesSunatAction}")
	private ReportePERResumenDiarioPercepcionesSunatAction reportePERResumenDiarioPercepcionesSunatAction;
	
	
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoPERResumenDiarioPercepcionesSunatForm f = new ProcesoPERResumenDiarioPercepcionesSunatForm();
		return f;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)throws Exception {	
		ProcesoPERResumenDiarioPercepcionesSunatForm f = (ProcesoPERResumenDiarioPercepcionesSunatForm)this.formProceso;
		ResumenDiarioPercepcionesSunatService service = (ResumenDiarioPercepcionesSunatService)getBean("spusicc.resumenDiarioPercepcionesSunatService");		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
	    Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
	    String  fecha = DateUtil.convertDateToString(f.getFechaGeneracionDate());
	    f.setFechaGeneracion(fecha);
	    
		params.put("usuario", usuario.getLogin());
		params.put("codigoPais", pais.getCodigo());
		params.put("fechaGenerar",f.getFechaGeneracion());
		
		service.executeGenerarResumenDiarioPercepcionesSunat(params);
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
	
	}
	
	public void verificarEjecucionReporteNoJASPER(ActionEvent event) {
		try {
			String lsMensajeError = this.setValidarProceso();
			if (StringUtils.isNotBlank(lsMensajeError)) {
				this.setMensajeAlertaDefault(lsMensajeError);
				this.getRequestContext().execute("PF('principalFormAlert_alertDialog').show()");
				return ;
			}
			this.getRequestContext().execute("PF('confirmationDialogGenerarReporteNoJASPER').show()");
			return;
		}
		catch(Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	
	/**
	 * @param actionEvent
	 */
	public String executeReporteNoJASPER() {
		try {
			ProcesoPERResumenDiarioPercepcionesSunatForm f = (ProcesoPERResumenDiarioPercepcionesSunatForm)this.formProceso;
			this.reportePERResumenDiarioPercepcionesSunatAction.setFormatoExportacion(this.formatoExportacion);
			this.reportePERResumenDiarioPercepcionesSunatAction.setFechaGeneraDate(f.getFechaGeneracionDate());			
			this.reportePERResumenDiarioPercepcionesSunatAction.setearValores();
			this.reportePERResumenDiarioPercepcionesSunatAction.executeReporteNoJASPER();			
		}
		catch(Exception e) {
			String error = this.obtieneMensajeErrorException(e);
			this.mPantallaPrincipalBean.setMensajeErrorSistema(error);
			return "_reporteConError";
		}
		return null;
	}


	/**
	 * @return the mostrarReporteOTXT
	 */
	public boolean isMostrarReporteOTXT() {
		return mostrarReporteOTXT;
	}

	/**
	 * @param mostrarReporteOTXT the mostrarReporteOTXT to set
	 */
	public void setMostrarReporteOTXT(boolean mostrarReporteOTXT) {
		this.mostrarReporteOTXT = mostrarReporteOTXT;
	}

	/**
	 * @return the mostrarReporteOCSV
	 */
	public boolean isMostrarReporteOCSV() {
		return mostrarReporteOCSV;
	}

	/**
	 * @param mostrarReporteOCSV the mostrarReporteOCSV to set
	 */
	public void setMostrarReporteOCSV(boolean mostrarReporteOCSV) {
		this.mostrarReporteOCSV = mostrarReporteOCSV;
	}

	/**
	 * @return the formatoExportacion
	 */
	public String getFormatoExportacion() {
		return formatoExportacion;
	}

	/**
	 * @param formatoExportacion the formatoExportacion to set
	 */
	public void setFormatoExportacion(String formatoExportacion) {
		this.formatoExportacion = formatoExportacion;
	}

	/**
	 * @return the reportePERResumenDiarioPercepcionesSunatAction
	 */
	public ReportePERResumenDiarioPercepcionesSunatAction getReportePERResumenDiarioPercepcionesSunatAction() {
		return reportePERResumenDiarioPercepcionesSunatAction;
	}

	/**
	 * @param reportePERResumenDiarioPercepcionesSunatAction the reportePERResumenDiarioPercepcionesSunatAction to set
	 */
	public void setReportePERResumenDiarioPercepcionesSunatAction(
			ReportePERResumenDiarioPercepcionesSunatAction reportePERResumenDiarioPercepcionesSunatAction) {
		this.reportePERResumenDiarioPercepcionesSunatAction = reportePERResumenDiarioPercepcionesSunatAction;
	}	

}
