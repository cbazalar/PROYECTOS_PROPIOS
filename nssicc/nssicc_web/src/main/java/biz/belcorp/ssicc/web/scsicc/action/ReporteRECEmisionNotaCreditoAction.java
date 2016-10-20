package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteRECEmisionNotaCreditoForm;


@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteRECEmisionNotaCreditoAction extends  BaseReporteAbstractAction implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5284998876340668589L;

	private String formatoReporte;	
	private List siccRegionList;
	private LabelValue[] siccZonaList;
	private List siccTipoCdrReclamosList;
	private String codigoIdiomaISO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();	
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		this.siccRegionList =reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);
		this.codigoIdiomaISO = this.mPantallaPrincipalBean.getOidIdiomaIso();
		this.siccTipoCdrReclamosList =  reporteService.getTiposSolicitudReclamos();
		ReporteRECEmisionNotaCreditoForm reporteRETForm = (ReporteRECEmisionNotaCreditoForm) this.formReporte;
		reporteRETForm.setCodigoPais(pais.getCodigo());
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteRECEmisionNotaCreditoXLS";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		String subReporte="";
		return subReporte;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteRECEmisionNotaCreditoForm reporteRETForm = (ReporteRECEmisionNotaCreditoForm) this.formReporte;
		this.formatoReporte =reporteRETForm.getFormatoExportacion()
				+ reporteRETForm.getTipoReporteAMostrar();
		String condicionRegion = this.obtieneCondicion(reporteRETForm.getRegionList(), "zr.COD_REGI", "'");
		String condicionZona = this.obtieneCondicion(reporteRETForm.getZonaList(), "zz.COD_ZONA", "'");
		
		String fecha1,fecha2;
		fecha1 = DateUtil.getDate(reporteRETForm.getFechaInicioD());
		fecha2 = DateUtil.getDate(reporteRETForm.getFechaFinalD());
		
		reporteRETForm.setFechaInicio(fecha1);
		reporteRETForm.setFechaFinal(fecha2);
		
		params.put("codigoPais",reporteRETForm.getCodigoPais());
		params.put("condicionRegion", condicionRegion);
		params.put("condicionZona", condicionZona);
		
				
		String condicionFecha =  "";
		if(StringUtils.isNotEmpty(reporteRETForm.getFechaInicio()) &&
				StringUtils.isNotEmpty(reporteRETForm.getFechaFinal())){ 		   		
			condicionFecha =  "and f.fec_fact >= to_date('"+  reporteRETForm.getFechaInicio() +"','dd/mm/yyyy')"+
								 "and f.fec_fact <= to_date('"+  reporteRETForm.getFechaFinal() +"','dd/mm/yyyy')";
		}
		params.put("condicionFecha", condicionFecha);
		String condicionCampanha ="";
		if(StringUtils.isNotEmpty(reporteRETForm.getCodigoPeriodo())){
			Map criteria = new HashMap();			
			criteria.put("codigoPeriodo", reporteRETForm.getCodigoPeriodo());
			ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
			Long oidPeriodo= new Long(reporteService.getOidString("getDesPerioByOidPerio", criteria));
			condicionCampanha ="and cra2.oid_peri = "+oidPeriodo;
		}
		params.put("condicionCampanha", condicionCampanha);
		String condicionTipo ="";
		if(StringUtils.isNotEmpty(reporteRETForm.getOidTipoSolicitudPais())){
			condicionTipo ="and c.tspa_oid_tipo_soli_pais = "+reporteRETForm.getOidTipoSolicitudPais();
		}
		params.put("condicionTipo", condicionTipo);
		
		params.put("titulo", this.getReportResourceMessage("reporteRECEmisionNotaCreditoForm.titulo"));
		reporteRETForm.setFormatoExportacion(formatoReporte);
		
		return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteRECEmisionNotaCreditoForm formReporte = new ReporteRECEmisionNotaCreditoForm();
		return formReporte;
	}
	
	/**
	 * Obtener Lista de Zonas
	 * @param val
	 */
	public void showZonasxRegion(ValueChangeEvent val){
		log.debug(">>showZonasxRegion ");
		try {
			ReporteRECEmisionNotaCreditoForm form = (ReporteRECEmisionNotaCreditoForm) this.formReporte;
			String[] regiones = (String [])val.getNewValue();
			if(!val.equals(null) && regiones.length > 0 ){		
//			List<String> strings = 
//				     new ArrayList<String>(Arrays.asList(regiones));
			
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			this.setSiccZonaList(aSvc.getZonasMultipleByPaisMarcaCanalRegion(form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, 
					Constants.CODIGO_CANAL_DEFAULT, regiones, Constants.FORMATO_TOTAL));
			form.setZonaList(null);
			}else {
				this.siccZonaList= null;
				form.setZonaList(null);	
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 * Validar fechas de inicio y fin
	 */
	public String setValidarReporte() {
		ReporteRECEmisionNotaCreditoForm form = (ReporteRECEmisionNotaCreditoForm)this.formReporte;
		if(form.getFechaFinalD()!=null){
			if (form.getFechaFinalD().compareTo(form.getFechaInicioD()) < 0){
		    	String mensaje =  this.getResourceMessage("errors.compare.dates");
				return mensaje;
		    }
		}
	
	    return null;
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
	 * @return the siccRegionList
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList the siccRegionList to set
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccZonaList
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the siccTipoCdrReclamosList
	 */
	public List getSiccTipoCdrReclamosList() {
		return siccTipoCdrReclamosList;
	}

	/**
	 * @param siccTipoCdrReclamosList the siccTipoCdrReclamosList to set
	 */
	public void setSiccTipoCdrReclamosList(List siccTipoCdrReclamosList) {
		this.siccTipoCdrReclamosList = siccTipoCdrReclamosList;
	}

	/**
	 * @return the codigoIdiomaISO
	 */
	public String getCodigoIdiomaISO() {
		return codigoIdiomaISO;
	}

	/**
	 * @param codigoIdiomaISO the codigoIdiomaISO to set
	 */
	public void setCodigoIdiomaISO(String codigoIdiomaISO) {
		this.codigoIdiomaISO = codigoIdiomaISO;
	}
}