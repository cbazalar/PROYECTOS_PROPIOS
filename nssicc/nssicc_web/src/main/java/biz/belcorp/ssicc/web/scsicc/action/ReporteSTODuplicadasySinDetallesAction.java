package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.jfree.data.time.DateRange;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOLevantamientoErroresValidacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSTODuplicadasySinDetalleForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteSTODuplicadasySinDetallesAction extends
		BaseReporteAbstractAction {

	private static final long serialVersionUID = -3315942703992267929L;

	private String formatoReporte;
	private String tipoReporte;
	private List stoTipoDocumentoList;
	private LabelValue[] stoReporteTipoDocumentoList;
	private List stpIntervaloCargaSto;
	private List stoHorasCargaList;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSTODuplicadasySinDetalleForm reporteForm = new ReporteSTODuplicadasySinDetalleForm();
		return reporteForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Executing action : setViewAttributes.");
		}

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;

		Map criteriaOperacion = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		String codpais = pais.getCodigo();
		criteriaOperacion.put("codigoPais", codpais);
		Map criteria = new HashMap();
		Usuario user = this.mPantallaPrincipalBean.getCurrentUser();
		criteria.put("codigoPais", codpais);
		criteria.put("codigoUsuario", user.getLogin());

		ProcesoSTOLevantamientoErroresValidacionService procesoSTOLevantamientoErroresValidacionService = (ProcesoSTOLevantamientoErroresValidacionService) getBean("spusicc.procesoSTOLevantamientoErroresValidacionService");
		this.stoTipoDocumentoList = procesoSTOLevantamientoErroresValidacionService
				.getReporteTiposDocumentosSTO(criteria);
		criteria.put("codigoParametro", Constants.STO_INTERVALO_CARGA_STO);
		List listaHorasCarga = procesoSTOLevantamientoErroresValidacionService
				.getListaHoras(criteria);
		this.stoHorasCargaList = listaHorasCarga;
			

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteSTODuplicadasySinDetalleForm form = (ReporteSTODuplicadasySinDetalleForm) this.formReporte;
		
		if(form.getFechaInicioProcesoD()!=null && form.getFechaFinProcesoD()!=null &&
				form.getHoraInicioCarga()!=null && 	form.getHoraFinCarga()!=null){
			if ((form.getFechaInicioProcesoD() != null && form.getHoraInicioCarga() ==null) || 
					(form.getFechaInicioProcesoD()==null && form.getHoraInicioCarga() !=null) ){
				String mensaje = this.getResourceMessage("errors.fechaInicioCarga");
				return mensaje;
			}else if ((form.getFechaFinProcesoD() != null && form.getHoraFinCarga() ==null) || 
					(form.getFechaFinProcesoD()==null && form.getHoraFinCarga() !=null) ){
				String mensaje = this.getResourceMessage("errors.fechaFinCarga");
				return mensaje;
			}
		}
	
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'prepareParameterMap' method");
		}
		ReporteSTODuplicadasySinDetalleForm reporteSTOForm = (ReporteSTODuplicadasySinDetalleForm) this.formReporte;
		this.formatoReporte = reporteSTOForm.getFormatoExportacion();
		this.tipoReporte = reporteSTOForm.getTipoConsulta();

		reporteSTOForm.setFechaFinProceso(DateUtil.convertDateToString(reporteSTOForm.getFechaFinProcesoD()));
		reporteSTOForm.setFechaInicioProceso(DateUtil.convertDateToString(reporteSTOForm.getFechaInicioProcesoD()));
		
		
		Map criteria = params;

		criteria.put("codigoPeriodo", reporteSTOForm.getCodigoPeriodo());

		String fechaInicio = reporteSTOForm.getFechaInicioProceso() + " "
				+ reporteSTOForm.getHoraInicioCarga();
		String fechaFin = reporteSTOForm.getFechaFinProceso() + " "
				+ reporteSTOForm.getHoraFinCarga();

		if (fechaInicio.compareToIgnoreCase("") == 0)
			fechaInicio = null;
		else
			fechaInicio = fechaInicio.trim();

		if (fechaFin.compareToIgnoreCase("") == 0)
			fechaFin = null;
		else
			fechaFin = fechaFin.trim();

		String codigoPeriodo = reporteSTOForm.getCodigoPeriodo();
		if (codigoPeriodo.compareToIgnoreCase("") == 0)
			codigoPeriodo = null;
		String codigoCliente = reporteSTOForm.getCodigoCliente();
		if (codigoCliente.compareToIgnoreCase("") == 0)
			codigoCliente = null;

		String numLote = reporteSTOForm.getNumeroLote();
		if (numLote.compareToIgnoreCase("") == 0)
			numLote = null;
		
		params.put("fechaInicioProceso",fechaInicio);
		params.put("fechaInicioProceso",fechaFin);
		params.put("codigoPeriodo", codigoPeriodo);
		params.put("numLote", numLote);
		params.put("codigoCliente", codigoCliente);
		params.put("fechaInicio", fechaInicio);
		params.put("fechaFin", fechaFin);
		params.put("NroReporte", "");
		params.put("titulo",
				getResourceMessage("reporteSTODuplicadosySinDetalle.title"));

		log.info("Salio a ReporteSTODuplicadasySinDetalleAction - prepareParameterMap");
		return params;

	}
	
	/**
	 * Cargando reportes
	 * @param val
	 */
	public void loadReportes(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadReportes");
		}
		try {
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

			String codpais = pais.getCodigo();
			String valor = (String) val.getNewValue();
			this.setStoReporteTipoDocumentoList(null);
			if (valor.length()>0) {
				AjaxService ajaxService = (AjaxService) getBean("ajaxService");
				this.setStoReporteTipoDocumentoList(ajaxService
						.getReportesByDocumento(codpais, valor));
				

			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteSTODuplicadasySinDetalleForm form = (ReporteSTODuplicadasySinDetalleForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(formReporte.getFormatoExportacion()))
			return tipoReporte;
		else
			return "reporteMaestroHorizontal";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return " ";
	}

	/**
	 * @return the formatoReporte
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte
	 *            the formatoReporte to set
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	/**
	 * @return the tipoReporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte
	 *            the tipoReporte to set
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	/**
	 * @return the stoTipoDocumentoList
	 */
	public List getStoTipoDocumentoList() {
		return stoTipoDocumentoList;
	}

	/**
	 * @param stoTipoDocumentoList
	 *            the stoTipoDocumentoList to set
	 */
	public void setStoTipoDocumentoList(List stoTipoDocumentoList) {
		this.stoTipoDocumentoList = stoTipoDocumentoList;
	}

	

	/**
	 * @return the stoReporteTipoDocumentoList
	 */
	public LabelValue[] getStoReporteTipoDocumentoList() {
		return stoReporteTipoDocumentoList;
	}

	/**
	 * @param stoReporteTipoDocumentoList the stoReporteTipoDocumentoList to set
	 */
	public void setStoReporteTipoDocumentoList(
			LabelValue[] stoReporteTipoDocumentoList) {
		this.stoReporteTipoDocumentoList = stoReporteTipoDocumentoList;
	}

	/**
	 * @return the stpIntervaloCargaSto
	 */
	public List getStpIntervaloCargaSto() {
		return stpIntervaloCargaSto;
	}

	/**
	 * @param stpIntervaloCargaSto
	 *            the stpIntervaloCargaSto to set
	 */
	public void setStpIntervaloCargaSto(List stpIntervaloCargaSto) {
		this.stpIntervaloCargaSto = stpIntervaloCargaSto;
	}

	/**
	 * @return the stoHorasCargaList
	 */
	public List getStoHorasCargaList() {
		return stoHorasCargaList;
	}

	/**
	 * @param stoHorasCargaList
	 *            the stoHorasCargaList to set
	 */
	public void setStoHorasCargaList(List stoHorasCargaList) {
		this.stoHorasCargaList = stoHorasCargaList;
	}

}
