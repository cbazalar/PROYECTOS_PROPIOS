package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteSTOConsolidadoEntregaForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOEstadoEntregaOrdenTransporteService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
public class ReporteSTOConsolidadoEntregaAction extends BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1435748424609950127L;
	private String formatoReporte;
	private List stoTipoOrdenTransporteList;
	private List stoCompaniaTransporteList;
	private List stoCentroAcopioList;
	private String tipoReporte;
	private String tipoFiltro;
	private List siccRegionList;
	private LabelValue[] siccZonaList = {};
	private Boolean cambioTipoReporte;
	private Boolean cambioRegion;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSTOConsolidadoEntregaForm reporteForm = new ReporteSTOConsolidadoEntregaForm();

		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReporteSTOConsolidadoEntregaAction - setViewAtributes");
		}

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF=false;
		Map criteria = new HashMap();
		
		//Carga las regiones
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoUsuario", usuario.getLogin());
		this.siccRegionList=reporteService.getListaGenerico("getRegionesByPais", criteria);
					  
        //Carga los tipos de orden
        MantenimientoSTOEstadoEntregaOrdenTransporteService service = (MantenimientoSTOEstadoEntregaOrdenTransporteService)getBean("spusicc.mantenimientoSTOEstadoEntregaOrdenTransporteService");
        this.stoTipoOrdenTransporteList = service.getTiposOrdenTransporte();
	   
        //Carga las companias de transporte
        ProcesoSTOEjecucionValidacionesService procesoSTOservice = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");        
        this.stoCompaniaTransporteList = procesoSTOservice.getCompaniasTransporte();
	
		this.setCambioRegion(false);
		log.debug("Todo OK: Redireccionando");

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanReporteService()
	 */
	protected String devuelveBeanReporteService() {
		return "reportes.reporteSTOConsolidadoEntregaService";
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))
			return "reporteSTOConsoEntr" + this.tipoReporte + this.tipoFiltro +"XLS";
		else
			return "reporteMaestroVertical";
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
		ReporteSTOConsolidadoEntregaForm reporteForm = (ReporteSTOConsolidadoEntregaForm) this.formReporte;
		formatoReporte = reporteForm.getFormatoExportacion();				
				
		if(reporteForm.getTipoReporte().equals("R")){
			this.tipoReporte = "Region";			
			if(reporteForm.getCodigoRegion()!=null && reporteForm.getCodigoRegion().length()>0){
				params.put("condicionRegion", " AND xx.cod_regi = '"+reporteForm.getCodigoRegion()+"'");
			}else{
				params.put("condicionRegion", "");
			}
		}
				
		if(reporteForm.getTipoReporte().equals("Z")){
			this.tipoReporte = "Zonas";	
			params.put("condicionesZonas",this.obtieneCondicion(reporteForm.getZonaList(), "xx.COD_ZONA", "'"));
		}
		if(reporteForm.getTipoReporte().equals("T")){
			this.tipoReporte = "CiaTra";							
		}		
		if(reporteForm.getTipoReporte().equals("A")){
			this.tipoReporte = "CenAco";							
		}
				
		if(reporteForm.getCodigoTipoOrden().equals("BER"))
			this.tipoFiltro = "PV";
		else
			this.tipoFiltro = "Ped";
			
		params.put("codigoRegion"       , reporteForm.getCodigoRegion());
		params.put("tipoOrden"          , reporteForm.getCodigoTipoOrden());
		params.put("codigoCiaTransporte", reporteForm.getCodigoCompaniaTransporte());
		
		if(reporteForm.getCodigoTipoOrden().equals("BEP")){
			params.put("titulo", this.getResourceMessage("reporteSTOConsolidadoEntrega.titulo.bep"));			
		}else if(reporteForm.getCodigoTipoOrden().equals("BER")){
			params.put("titulo", this.getResourceMessage("reporteSTOConsolidadoEntrega.titulo.ber"));
		}else{
			params.put("titulo", this.getResourceMessage("reporteSTOConsolidadoEntrega.titulo.br"));
		}
			
		return params;
	}

	/**
	 * Metodo para obtener Lista de Zonas
	 * 
	 * @param val
	 */
	public void loadZonas(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadZonas");
		}
		String valor = (String) val.getNewValue();
		String[] valores = new String[1];
		valores[0] = valor;
		if (valores.length > 0) {
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			this.setSiccZonaList(ajaxService
					.getZonasMultipleByPaisMarcaCanalRegion(this
							.getmPantallaPrincipalBean().getCurrentCountry()
							.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
							Constants.CODIGO_CANAL_DEFAULT, valores,
							Constants.OPCION_TODOS));

		}
	}

	/**
	 * Metodo para Cambiar Tipo de Reporte
	 * 
	 * @param val
	 */
	public void loadTipoReporte(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadTipoReporte");
		}
		String valor = (String) val.getNewValue();
		if (valor.equals("Z")) {
			this.setCambioTipoReporte(true);
			this.setCambioRegion(false);
		} else if (valor.equals("R")) {
			this.setCambioRegion(false);
			this.setCambioTipoReporte(false);
			
		} else {
			this.setCambioTipoReporte(false);
			this.setCambioRegion(true);
			this.setSiccZonaList(null);
		}
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
	 * @return the stoTipoOrdenTransporteList
	 */
	public List getStoTipoOrdenTransporteList() {
		return stoTipoOrdenTransporteList;
	}

	/**
	 * @param stoTipoOrdenTransporteList
	 *            the stoTipoOrdenTransporteList to set
	 */
	public void setStoTipoOrdenTransporteList(List stoTipoOrdenTransporteList) {
		this.stoTipoOrdenTransporteList = stoTipoOrdenTransporteList;
	}

	/**
	 * @return the stoCentroAcopioList
	 */
	public List getStoCentroAcopioList() {
		return stoCentroAcopioList;
	}

	/**
	 * @param stoCentroAcopioList
	 *            the stoCentroAcopioList to set
	 */
	public void setStoCentroAcopioList(List stoCentroAcopioList) {
		this.stoCentroAcopioList = stoCentroAcopioList;
	}

	/**
	 * @return the stoCompaniaTransporteList
	 */
	public List getStoCompaniaTransporteList() {
		return stoCompaniaTransporteList;
	}

	/**
	 * @param stoCompaniaTransporteList
	 *            the stoCompaniaTransporteList to set
	 */
	public void setStoCompaniaTransporteList(List stoCompaniaTransporteList) {
		this.stoCompaniaTransporteList = stoCompaniaTransporteList;
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
	 * @return the tipoFiltro
	 */
	public String getTipoFiltro() {
		return tipoFiltro;
	}

	/**
	 * @param tipoFiltro
	 *            the tipoFiltro to set
	 */
	public void setTipoFiltro(String tipoFiltro) {
		this.tipoFiltro = tipoFiltro;
	}

	/**
	 * @return the cambioTipoReporte
	 */
	public Boolean getCambioTipoReporte() {
		return cambioTipoReporte;
	}

	/**
	 * @param cambioTipoReporte
	 *            the cambioTipoReporte to set
	 */
	public void setCambioTipoReporte(Boolean cambioTipoReporte) {
		this.cambioTipoReporte = cambioTipoReporte;
	}

	/**
	 * @return the cambioRegion
	 */
	public Boolean getCambioRegion() {
		return cambioRegion;
	}

	/**
	 * @param cambioRegion
	 *            the cambioRegion to set
	 */
	public void setCambioRegion(Boolean cambioRegion) {
		this.cambioRegion = cambioRegion;
	}
}
