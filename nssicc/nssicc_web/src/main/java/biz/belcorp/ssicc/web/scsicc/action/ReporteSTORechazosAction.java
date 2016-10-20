package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteSTORechazosForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;


@ManagedBean
@SessionScoped
public class ReporteSTORechazosAction extends BaseReporteAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6763649095982296374L;
	private String formatoReporte;
	private List stoTipoDocumentoList;
	private Date fechaini;
	private Date fechafin;
	
	
	
	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	public List getStoTipoDocumentoList() {
		return stoTipoDocumentoList;
	}

	public void setStoTipoDocumentoList(List stoTipoDocumentoList) {
		this.stoTipoDocumentoList = stoTipoDocumentoList;
	}

	public Date getFechaini() {
		return fechaini;
	}

	public void setFechaini(Date fechaini) {
		this.fechaini = fechaini;
	}

	public Date getFechafin() {
		return fechafin;
	}

	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		// TODO Auto-generated method stub
		ReporteSTORechazosForm reporteForm = new ReporteSTORechazosForm();
		return reporteForm;
	}

	protected String devuelveBeanReporteService(){
		return "reportes.reporteSTORechazosService";
	}
	
	
	
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteSTORechazosForm form=(ReporteSTORechazosForm) this.formReporte;
		// TODO Auto-generated method stub
		if ("XLS".equals(this.formReporte.getFormatoExportacion())) {
			return "reporteSTORechazosXLS";
		} else {
			return "reporteMaestroHorizontal";
		}	
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'prepareParameterMap' method");
		}
		ReporteSTORechazosForm reporteForm= (ReporteSTORechazosForm) this.formReporte;
		this.formatoReporte = reporteForm.getFormatoExportacion();
		
		//this.setVirtualizador(true);
		
		String fechaDesde="";
		String fechaHasta="";
		fechaDesde=DateUtil.convertDateToString(reporteForm.getFechaDesdeD());
    	fechaHasta=DateUtil.convertDateToString(reporteForm.getFechaHastaD());
		params.put("codigoTipoDocumento", reporteForm.getTipoDocumento());
		params.put("codigoPeriodo", reporteForm.getCodigoPeriodo());
		params.put("fechaInicio",fechaDesde );
		params.put("fechaFin",fechaHasta );
		
		params.put("titulo", getResourceMessage("reporteSTORechazosForm.titulo"));
		
		return params;
				
		
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering REPORTE STO RECHAZOS method");
		}

		this.mostrarReportePDF=false;
		this.mostrarReporteXLS=true;
		
		Map criteria = new HashMap();
		Pais pais= this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoUsuario", usuario.getLogin());
		ReporteSTORechazosForm reporteForm= (ReporteSTORechazosForm) this.formReporte;
		//Carga Periodo
		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", pais.getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
		     
		MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService) this.getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceFact.getControlFacturacionById(criteriaPeriodo);
		
		reporteForm.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		String fechaDesde = ajaxService
				.getFechaInicioPeriodoByPaisMarcaCanal(this
						.getmPantallaPrincipalBean().getCurrentCountry()
						.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
						Constants.CODIGO_CANAL_DEFAULT, controlFacturacion.getCodigoPeriodo());
		String fechaHasta = ajaxService
				.getFechaFinalPeriodoByPaisMarcaCanal(this
						.getmPantallaPrincipalBean().getCurrentCountry()
						.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
						Constants.CODIGO_CANAL_DEFAULT, controlFacturacion.getCodigoPeriodo());
		reporteForm.setFechaDesdeD(DateUtil.convertStringToDate(fechaDesde));
		reporteForm.setFechaHastaD(DateUtil.convertStringToDate(fechaHasta));
				
		 //Carga lista de documentos
        ProcesoSTOEjecucionValidacionesService procesoSTOService = (ProcesoSTOEjecucionValidacionesService) this.getBean("spusicc.procesoSTOEjecucionValidacionesService");
        stoTipoDocumentoList=procesoSTOService.getTiposDocumentosSTO(criteria);
        log.debug("TIPO DOCUMETOOOOOOOOOOO"+stoTipoDocumentoList);
        log.debug("CARGA PERIODO+++codigopais+++++"+criteriaPeriodo.get("codigoPais"));
        log.debug("CARGA PERIODO+++estadocampanha+++++"+criteriaPeriodo.get("estadoCampanha"));
        log.debug("CARGA PERIODO+++indicadoractiva+++++"+criteriaPeriodo.get("indicadorActiva"));	
        this.loadFechasPeriodo(controlFacturacion.getCodigoPeriodo());
		
	}
	
	/**
	 * @param valor
	 */
	public void loadFechasPeriodo(String valor) {
		if (log.isDebugEnabled()) {
			log.debug("loadFechasPeriodo");
		}
		ReporteSTORechazosForm reporteForm = (ReporteSTORechazosForm) this.formReporte;
		try {
			if (StringUtils.isNotBlank(valor) && valor.length() == 6) {
				AjaxService ajaxService = (AjaxService) getBean("ajaxService");
				String fechaDesde = ajaxService
						.getFechaInicioPeriodoByPaisMarcaCanal(this
								.getmPantallaPrincipalBean().getCurrentCountry()
								.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
								Constants.CODIGO_CANAL_DEFAULT, valor);
				
				String fechaHasta = ajaxService.getFechaFinalPeriodoByPaisMarcaCanal(this
								.getmPantallaPrincipalBean().getCurrentCountry()
								.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
								Constants.CODIGO_CANAL_DEFAULT, valor);
				
				reporteForm.setFechaDesdeD(DateUtil.convertStringToDate(fechaDesde));
				reporteForm.setFechaHastaD(DateUtil.convertStringToDate(fechaHasta));
				
				this.fechaini=DateUtil.convertStringToDate(fechaDesde);
				this.fechafin=DateUtil.convertStringToDate(fechaHasta);
			}
		}
		catch (Exception e) {
			
		}
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteSTORechazosForm form = (ReporteSTORechazosForm) this.formReporte;
		String fechaDesde = DateUtil.convertDateToString(form.getFechaDesdeD());
		String fechaHasta = DateUtil.convertDateToString(form.getFechaHastaD());
		String mensaje = "";
		
		if(StringUtils.isBlank(form.getTipoDocumento()))
			return "'Tipo de Documento' es un campo requerido.";
			
		if(StringUtils.isBlank(form.getCodigoPeriodo()))
			return "'Campaña' es un campo requerido.";
			
		if(StringUtils.isBlank(fechaDesde))
			return "'Fecha desde' es un campo requerido.";
			
		if(StringUtils.isBlank(fechaHasta))
			return "'Fecha hasta' es un campo requerido.";
			
		
//		String fechaIni =DateUtil.convertDateToString(fechaini);
//		String fechaFin =DateUtil.convertDateToString(fechafin);
//		if (fechaini.compareTo(form.getFechaDesdeD()) ==1 || fechafin.compareTo(form.getFechaDesdeD())<0) {
//			mensaje = this.getResourceMessage("errors.compare.campaigns.periodo.fechaInicio")+fechaIni+" - "+fechaFin;
//			return mensaje;
//		} else if (fechafin.compareTo(form.getFechaHastaD())<0 || fechaini.compareTo(form.getFechaHastaD()) ==1) {
//			mensaje = this.getResourceMessage("errors.compare.campaigns.periodo.fechaFin")+fechaIni+" - "+fechaFin;
//			return mensaje;
//		}

		return mensaje;
	}
}
