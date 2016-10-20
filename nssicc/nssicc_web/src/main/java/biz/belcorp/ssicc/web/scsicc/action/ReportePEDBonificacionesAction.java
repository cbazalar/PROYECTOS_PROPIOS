package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReportePEDBonificacionesForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
public class ReportePEDBonificacionesAction extends BaseReporteAbstractAction {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2107101536920323458L;
	private String formatoReporte;
	private List pedTipoDocList;


	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReportePEDBonificacionesForm reporteForm = new ReportePEDBonificacionesForm();

		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReportePEDBonificacionesAction - setViewAtributes");
		}

		this.mostrarReporteOCSV = true;
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ReportePEDBonificacionesForm f = (ReportePEDBonificacionesForm) this.formReporte;
		List lista = reporteService.getTipoDocumentoList();
		this.pedTipoDocList = lista;
	
		
		log.debug("Todo OK: Redireccionando");

	}

	protected String devuelveBeanReporteService() {
		return "reportes.reportePEDBonificacionesService";
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("PDF".equals(formatoReporte))
			return "reporteMaestroHorizontal";
		return null;
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reportePEDBonificacionesPDF";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("prepareParameterMap...");
		}
		ReportePEDBonificacionesForm reporteForm = (ReportePEDBonificacionesForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		formatoReporte = reporteForm.getFormatoExportacion();
		Map criteria = new HashMap();
		
		criteria.put("codigoTipoDocu", reporteForm.getTipoDocumento());
		
		String oidTipoDocu = reporteService.getOidTipoDocumento(criteria);

		log.debug("Fecha Fact: "+reporteForm.getCodigoAnhoMes());
		 
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		criteria.put("codigoPais", pais.getCodigo());
		params.put("oidPais", reporteService.getOidString("getOidPaisByCodigoPais", criteria));

		params.put("titulo", getResourceMessage(
				"reportePEDBonificacionesForm.titulo"));
		params.put("oidTipoDocumento", oidTipoDocu);
		params.put("fechaFactura",reporteForm.getCodigoAnhoMes());
		this.setGenerateTabsXLS(true);
	
		return params;
	}

	/**
	 * @return the formatoReporte
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte the formatoReporte to set
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	/**
	 * @return the pedTipoDocList
	 */
	public List getPedTipoDocList() {
		return pedTipoDocList;
	}

	/**
	 * @param pedTipoDocList the pedTipoDocList to set
	 */
	public void setPedTipoDocList(List pedTipoDocList) {
		this.pedTipoDocList = pedTipoDocList;
	}

	


}
