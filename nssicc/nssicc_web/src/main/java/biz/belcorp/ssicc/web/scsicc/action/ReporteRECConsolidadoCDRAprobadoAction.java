package biz.belcorp.ssicc.web.scsicc.action;

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
import biz.belcorp.ssicc.web.scsicc.form.ReporteRECConsolidadoCDRAprobadoForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteRECConsolidadoCDRAprobadoAction extends BaseReporteAbstractAction{
	
	private static final long serialVersionUID = -7090164560993170123L;
	
	private String formatoReporte;
	private List siccRegionList;
	private LabelValue[] siccZonaList={};
	private List siccTipoCdrList;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteRECConsolidadoCDRAprobadoForm reporteForm = new ReporteRECConsolidadoCDRAprobadoForm();
		return reporteForm;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {				
		
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF=false;
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ReporteRECConsolidadoCDRAprobadoForm f = (ReporteRECConsolidadoCDRAprobadoForm) this.formReporte;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codpais = pais.getCodigo();
		f.setCodigoPais(codpais);
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", codpais);
		
		this.siccRegionList=reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);
		this.siccTipoCdrList=reporteService.getTiposCDR();			
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))
			return "reporteRECConsolidadoCDRAprobadoXLS";
		else
			return "reporteMaestroHorizontal";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteRECConsolidadoCDRAprobadoForm f = (ReporteRECConsolidadoCDRAprobadoForm) this.formReporte;		
		formatoReporte = f.getFormatoExportacion();	
		
		//Carga los filtros
		params.put("codigoPais", f.getCodigoPais());
		//Regiones		
		String region = "";
		if (!(f.getRegionList() == null || 
	        StringUtils.equals(StringUtils.substring(f.getDescripcionRegionList(),0, 5),
	        					Constants.OPCION_TODOS)))
			region = this.obtieneCondicion(f.getRegionList(), "zr.COD_REGI", "'");
		//Zonas
		String zona = "";
		if (!(f.getZonaList() == null || 
	        StringUtils.equals(StringUtils.substring(f.getDescripcionZonaList(),0, 5),
	        					Constants.OPCION_TODOS)))
			zona = this.obtieneCondicion(f.getZonaList(), "zz.COD_ZONA", "'");
		//Tipo CDR
		String tipoCDR = "";
		if (!(f.getTipoCDRList() == null || 
	        StringUtils.equals(StringUtils.substring(f.getDescripcionTipoCDRList(),0, 5),
	        					Constants.OPCION_TODOS)))
			tipoCDR = this.obtieneCondicionSinT(f.getTipoCDRList(), "RR.COD_OPER_HOMOL", "'");
		
		String condicion = region + " " + zona + " " + tipoCDR;
		params.put("condicion", condicion);
		
		String nInicial=DateUtil.convertDateToString(f.getFechaProcesoInicialD());
		String nFinal=DateUtil.convertDateToString(f.getFechaProcesoFinalD());
		f.setFechaProcesoInicial(nInicial);
		f.setFechaProcesoFinal(nFinal);
		
		//Periodos
		params.put("codigoPeriodoInicial", f.getCodigoPeriodoInicial());
		params.put("codigoPeriodoFinal", f.getCodigoPeriodoFinal());
		//Fechas
		if (!StringUtils.equals(f.getFechaProcesoInicial(), ""))
			params.put("fechaProcesoInicial", f.getFechaProcesoInicial());
		if (!StringUtils.equals(f.getFechaProcesoFinal(), ""))
			params.put("fechaProcesoFinal", f.getFechaProcesoFinal());
		//OID Pais
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
        params.put("oidPais",new Integer(reporteService.getOidString("getOidPaisByCodigoPais", params))); 
		
		params.put("NroReporte","");
		params.put("titulo", getResourceMessage("reporteRECConsolidadoCDRAprobadoForm.title"));
		return params;
				
	}
	
	/**
	 * @param val
	 */
	public void showZonasxRegion(ValueChangeEvent val) {
		log.debug(">>showZonasxRegion ");
		log.debug("val: " + val.getNewValue().toString());		
		
		ReporteRECConsolidadoCDRAprobadoForm f = (ReporteRECConsolidadoCDRAprobadoForm) this.formReporte;
		String[] regiones = (String[]) val.getNewValue();
		if (!val.equals(null) && regiones.length > 0) {
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			this.setSiccZonaList(aSvc.getZonasMultipleByPaisMarcaCanalRegion(
					f.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT,Constants.CODIGO_CANAL_DEFAULT, regiones,
					Constants.FORMATO_TOTAL));			
			f.setZonaList(null);
		} else {
			this.siccZonaList = null;
			f.setZonaList(null);
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteRECConsolidadoCDRAprobadoForm f = (ReporteRECConsolidadoCDRAprobadoForm) this.formReporte;
		int codperini = Integer.parseInt(f.getCodigoPeriodoInicial());
		int codperfin = Integer.parseInt(f.getCodigoPeriodoFinal());
		if (codperfin < codperini) {
			String mensaje = "El Periodo Inicial debe ser mayor o igual al Periodo Final";
			return mensaje;
		}
		if (f.getFechaProcesoFinalD().compareTo(f.getFechaProcesoInicialD()) < 0 ){
	    	String mensaje =  this.getResourceMessage("errors.compare.dates");
			return mensaje;
	    }
		return null;	

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

	/**
	 * @return
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return
	 */
	public List getSiccTipoCdrList() {
		return siccTipoCdrList;
	}

	/**
	 * @param siccTipoCdrList
	 */
	public void setSiccTipoCdrList(List siccTipoCdrList) {
		this.siccTipoCdrList = siccTipoCdrList;
	}
	
	
}
