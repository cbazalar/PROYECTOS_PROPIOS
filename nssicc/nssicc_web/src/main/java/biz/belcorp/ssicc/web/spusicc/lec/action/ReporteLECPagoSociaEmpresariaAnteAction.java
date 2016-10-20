package biz.belcorp.ssicc.web.spusicc.lec.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.spusicc.lec.MantenimientoLECProgramaCorporativoService;
import biz.belcorp.ssicc.service.spusicc.pej.MantenimientoPEJProgramaEjecutivasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.spusicc.lec.form.ReporteLECPagoSociaEmpresariaForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteLECPagoSociaEmpresariaAnteAction extends
		BaseReporteAbstractAction {

	private static final long serialVersionUID = -8283811688652696544L;

	private String formatoReporte;
	private List siccRegionList;
	private LabelValue[] siccZonaList;
	private List siccSeccionList; 

	private String[] listaTotal;
	
	/**
	 * @return
	 */
	public String[] getListaTotal() {
		return listaTotal;
	}

	/**
	 * @param listaTotal
	 */
	public void setListaTotal(String[] listaTotal) {
		this.listaTotal = listaTotal;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return
	 */
	public List getSiccSeccionList() {
		return siccSeccionList;
	}

	/**
	 * @param siccSeccionList
	 */
	public void setSiccSeccionList(List siccSeccionList) {
		this.siccSeccionList = siccSeccionList;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteLECPagoSociaEmpresariaForm reporteForm = new ReporteLECPagoSociaEmpresariaForm();
		return reporteForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		String reporte="reporteLECPagoSociaEmpreAnteXLS";
		return reporte;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		String reporte="";
		return reporte;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#getNroReportesAGenerar()
	 */
	protected int getNroReportesAGenerar() {
		ReporteLECPagoSociaEmpresariaForm f = (ReporteLECPagoSociaEmpresariaForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");	
		AjaxService ajaxService = (AjaxService) this.getBean("ajaxService");
		List lista = new ArrayList();
		
		String codigoRegion = f.getCodigoRegion();
		String codigoZona = f.getCodigoZona();
//		String codigoSeccion = f.getCodigoSeccion();
						
			if (StringUtils.equals(codigoZona, "Todos") || StringUtils.isBlank(codigoZona)) {
				String codigoRegionAux = codigoRegion;
				if (StringUtils.equals(codigoRegion, "Todos") || StringUtils.isBlank(codigoRegion)) {
					codigoRegionAux = "";
				}
				
				LabelValue[] result = ajaxService.getZonasRegionPEJTodos(codigoRegionAux, f.getCampanyaProceso());
				if (result != null) {
					for(int i=0; i < result.length; i++) {
						LabelValue zonas = result[i];
						lista.add(zonas.getValue());
					}
				}
			}
			else {
				lista.add(codigoZona);
			}
			
			if (StringUtils.equals(codigoRegion, "Todos") || StringUtils.isBlank(codigoRegion)) {
				List listaRegiones = reporteService.getListaGenerico("getRegionesPEJ",null);
				
				for(int i=0;i<listaRegiones.size();i++) {
					Base base =(Base)listaRegiones.get(i);
					lista.add("codigoRegion__"+base.getCodigo());
				}
			} else
				lista.add("codigoRegion__"+codigoRegion);
					
			
			int tamanno = lista.size();
			this.listaTotal = new String[tamanno];
			for (int i=0; i < tamanno; i++) {
				this.listaTotal[i] = (String)lista.get(i); 
			}		
			return this.listaTotal.length;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#getValorFiltroGrabarReporte(biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams)
	 */
	protected String getValorFiltroGrabarReporte(ReporteParams reporteParams) {
		String filtro = new String();
			if(this.listaTotal[this.getNroReporteProcesando() - 1 ].indexOf("codigoRegion__")<0) {
				filtro = "Zona: ";		
				return filtro + this.listaTotal[this.getNroReporteProcesando() - 1 ];
			} else {
				filtro = "Region: ";		
				return filtro + this.listaTotal[this.getNroReporteProcesando() - 1 ].substring(14);
			}	
	}
	
	/**
	 * @param reporteParams
	 * @return
	 */
	protected boolean continueExecuteReporte(ReporteParams reporteParams) {		
		return true;
	}
	
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteLECPagoSociaEmpresariaForm reporteForm = (ReporteLECPagoSociaEmpresariaForm) formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoLECProgramaCorporativoService lecService = (MantenimientoLECProgramaCorporativoService) getBean("spusicc.mantenimientoLECProgramaCorporativoService");
		String regionList[] ={reporteForm.getCodigoRegion()};
		String zonaList[] ={reporteForm.getCodigoZona()};
		this.ignorarCeldaBorder = true;
		
		
		//Obtener cod programa x Campaña Bono
		Map mapBono = lecService.getEncontrarProgramaLecCorporativo(reporteForm.getCampanyaProceso());
		params.put("codigoPrograma", mapBono.get("codigoPrograma"));
		
		//Obtener cod programa x Campaña Recaudo 
		Map map1 = lecService.getEncontrarProgramaLecCorporativo(reporteForm.getCodigoPeriodoRecaudo());
		params.put("codigoPrograma2", map1.get("codigoPrograma"));
		
		//params.put("codigoPrograma", reporteForm.getCodigoPrograma());
 		
		params.put("codigoPeriodo", reporteForm.getCampanyaProceso());
		params.put("oidCampanaBono",reporteService.getOidString("getDesPerioByOidPerio", params));		
		params.put("titulo", this.getResourceMessage("reporteLECPagoSociaEmpresariaForm.titulo"));		
		params.put("condicionZonaCorreo", " ");
		params.put("condicionRegionCorreo", " ");
		params.put("condicion", " ");		
		
		if (!this.isVisualizarReporte()) {		
				if(this.listaTotal[this.getNroReporteProcesando() - 1 ].indexOf("codigoRegion__")<0) {
					params.put("codigoRegion", null);
					params.put("codigoZona", this.listaTotal[this.getNroReporteProcesando() - 1 ]);
					params.put("condicionZonaCorreo", " AND nvl(lide.zonaBono,lide.zonaRecaudo)='"+ this.listaTotal[this.getNroReporteProcesando() - 1 ] +"' ");
				} else {
					params.put("codigoRegion", this.listaTotal[this.getNroReporteProcesando() - 1 ].substring(14));
					params.put("condicionRegionCorreo", " AND NVL(lide.regionBono,lide.regionRecaudo) ='"+ this.listaTotal[this.getNroReporteProcesando() - 1 ].substring(14) +"' ");
				}
		}
		else {
			String condicionRegion = this.obtieneCondicion(regionList, "NVL(lide.regionBono,lide.regionRecaudo)", "'");
			String condicionZonas = this.obtieneCondicion(zonaList, "nvl(lide.zonaBono,lide.zonaRecaudo)", "'");
			String condicion = condicionRegion + condicionZonas;
			params.put("condicion", condicion);
		}
		return params;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF=false;
		this.mostrarReporteMailXLS=true;

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoPEJProgramaEjecutivasService service = (MantenimientoPEJProgramaEjecutivasService)getBean("spusicc.mantenimientoPEJProgramaEjecutivasService");
		MantenimientoLECProgramaCorporativoService lecService = (MantenimientoLECProgramaCorporativoService) getBean("spusicc.mantenimientoLECProgramaCorporativoService");
	       
		ReporteLECPagoSociaEmpresariaForm f = (ReporteLECPagoSociaEmpresariaForm) this.formReporte;
		
		Map result = service.getPeriodoDefault();
		
		String codigoPeriodo = (String) result.get("codigoPeriodo");
		String fechaProceso = (String) result.get("fechaProceso");
		f.setCampanyaProceso(codigoPeriodo);
		f.setFechaFacturacion(fechaProceso);
		f.setCodigoPeriodoRecaudo("");
		
		Map map1 = lecService.getEncontrarProgramaLecCorporativo(f.getCampanyaProceso());
		
		f.setCodigoPrograma(map1.get("codigoPrograma").toString());
		f.setDescPrograma(map1.get("descPrograma").toString());
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		this.siccRegionList= reporteService.getListaGenerico("getRegionesPEJ",criteria);

		this.siccSeccionList=new ArrayList();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanMailService()
	 */
	protected String devuelveBeanMailService(){
		return "lec.mailReporteLECPagoSociaEmpresaria";
	}	
	
	/**
	 * @param val
	 */
	public void buscarZonaPorRegion(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		try {
			log.debug(val.getNewValue().toString());
			if (StringUtils.isNotEmpty(val.getNewValue().toString())
					|| StringUtils.isNotBlank(val.getNewValue().toString())) {
				String valor = (String) val.getNewValue();
				AjaxService ajax = (AjaxService) getBean("ajaxService");

				this.siccZonaList = ajax.getZonasRegionPEJ(valor);
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
		
	}
}