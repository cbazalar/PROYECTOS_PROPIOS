package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCConfiguracionConcursoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCOBPosiblesReingresosForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteCOBPosiblesReingresosAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 7265893401869891004L;
	private String formatoReporte;
	
	private List siccEstatusList;
	private List siccRegionList;
	private List siccZonaList;
	private List siccSeccionList;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOBPosiblesReingresosForm form = new ReporteCOBPosiblesReingresosForm();
		
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte)) {
			return "reporteCOBPosiblesReingresosXLS";
		} else
			return "reporteMaestroVertical";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entro setViewAttributes - ReporteCOBPosiblesReingresosAction");
		}
		
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoINCConfiguracionConcursoService service = (MantenimientoINCConfiguracionConcursoService) getBean("spusicc.mantenimientoINCConfiguracionConcursoService");
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		
		this.siccEstatusList = service.getEstatusCliente();
		this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais", criteriaOperacion);
		this.siccZonaList = new ArrayList();
		this.siccSeccionList = new ArrayList();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteCOBPosiblesReingresosForm form = (ReporteCOBPosiblesReingresosForm) this.formReporte;
		Integer cPPD1, cPPH2;
		Integer cUPD1, cUPH2;
		double importeDesde, importeHasta;
		
		cPPD1 = Integer.parseInt(form.getCampanaPrimerPedidoDesde());
		cPPH2 = Integer.parseInt(form.getCampanaPrimerPedidoHasta());
		
		if (cPPD1 > cPPH2) {
			String mensaje = "Campaña Primer Pedido Hasta debe ser mayor o igual a Campaña Primer Pedido Desde";
			return mensaje;
		}
		
		cUPD1 = Integer.parseInt(form.getCampanaUltimoPedidoDesde());
		cUPH2 = Integer.parseInt(form.getCampanaUltimoPedidoHasta());
		
		if (cUPD1 > cUPH2) {
			String mensaje = "Campaña Último Pedido Hasta debe ser mayor o igual a Campaña Último Pedido Desde";
			return mensaje;
		}
		
		if(StringUtils.isNotBlank(form.getImporteDeudaDesde()) && StringUtils.isNotBlank(form.getImporteDeudaHasta())){
			importeDesde = Double.parseDouble(form.getImporteDeudaDesde());
			importeHasta = Double.parseDouble(form.getImporteDeudaHasta());
			
			if(importeDesde > importeHasta){
				String mensaje = "Importe Deuda Hasta debe ser mayor o igual a Importe Deuda Desde";
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
		ReporteCOBPosiblesReingresosForm f = (ReporteCOBPosiblesReingresosForm) this.formReporte;
		
		this.formatoReporte = f.getFormatoExportacion();
		
		if(StringUtils.isNotBlank(f.getImporteDeudaDesde())){
			params.put("importeDeudaDesde", " AND mc.sal_deud_ante >= " + f.getImporteDeudaDesde());
		}else{
			params.put("importeDeudaDesde", "");
		}
		
		if(StringUtils.isNotBlank(f.getImporteDeudaHasta())){
			params.put("importeDeudaHasta", " AND mc.sal_deud_ante <= " + f.getImporteDeudaHasta());
		}else{
			params.put("importeDeudaHasta", "");
		}
		
		String condicionEstatus = obtieneCondicion(f.getEstatus(), "mcda.esta_oid_esta_clie", "'");
		String condicionRegion = obtieneCondicion(f.getCodigoRegion(), "zr.cod_regi", "'");
		String condicionZona = obtieneCondicion(f.getCodigoZona(), "zz.cod_zona", "'");
		String condicionSeccion = obtieneCondicion(f.getCodigoSeccion(), "zs.cod_secc", "'");
		
		params.put("condicionEstatus", condicionEstatus != null ? condicionEstatus : "");
		params.put("condicionRegion", condicionRegion != null ? condicionRegion : "");
		params.put("condicionZona", condicionZona != null ? condicionZona : "");
		params.put("condicionSeccion", condicionSeccion != null ? condicionSeccion : "");
		
		params.put("NroReporte", "");
		
		return params;
	}
	
	/**
	 * Carga zonas dependiendo las regiones seleccionadas
	 * @param val
	 */
	public void showZonasxRegion(ValueChangeEvent val){
		log.debug(">>showZonasxRegion...");
		
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		String[] regiones = (String[]) val.getNewValue();
		
		if(regiones != null && regiones.length > 0) {
			LabelValue zonas[] = ajax.getZonasMultipleByPaisMarcaCanalRegion(pais.getCodigo(), "T", "VD", regiones, "T");
			
			if(zonas != null && zonas.length > 0) {
				this.siccZonaList = Arrays.asList(zonas);
			} else {
				this.siccZonaList = new ArrayList();
			}		
		} else{
			this.siccZonaList = new ArrayList();
		}
	}
	
	/**
	 * Carga secciones dependiendo las zonas seleccionadas
	 * @param val
	 */
	public void showSeccionesxZonas(ValueChangeEvent val){
		log.debug(">>showSeccionesxZonas...");
		
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		ReporteCOBPosiblesReingresosForm f = (ReporteCOBPosiblesReingresosForm) this.formReporte;
		
		String[] zonas = (String[]) val.getNewValue();
				
		if(zonas != null && zonas.length > 0){		
			LabelValue secciones[] = ajax.getSeccionMultipleByPaisMarcaCanalRegionZona(pais.getCodigo(), "T", "VD", f.getCodigoRegion(), zonas, "T");

			if(secciones != null && secciones.length > 0) {
				this.siccSeccionList = Arrays.asList(secciones);
			} else {
				this.siccSeccionList = new ArrayList();
			}		
		} else{
			this.siccSeccionList = new ArrayList();
		}
	}

	/**
	 * @return the siccEstatusList
	 */
	public List getSiccEstatusList() {
		return siccEstatusList;
	}

	/**
	 * @param siccEstatusList the siccEstatusList to set
	 */
	public void setSiccEstatusList(List siccEstatusList) {
		this.siccEstatusList = siccEstatusList;
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
	public List getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList the siccZonaList to set
	 */
	public void setSiccZonaList(List siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the siccSeccionList
	 */
	public List getSiccSeccionList() {
		return siccSeccionList;
	}

	/**
	 * @param siccSeccionList the siccSeccionList to set
	 */
	public void setSiccSeccionList(List siccSeccionList) {
		this.siccSeccionList = siccSeccionList;
	}
}