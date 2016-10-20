package biz.belcorp.ssicc.web.scsicc.action;


import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteRECExcepcionValidacionesForm;


/**
 * 
 * @author <a href="aoviedo@sigcomt.com">Aurelio Oviedo</a>
 */
@ManagedBean
@SessionScoped
public class ReporteRECExcepcionValidacionesAction extends BaseReporteAbstractAction implements Serializable {
			
	private static final long serialVersionUID = 7496906172990857522L;

	 private List siccRegionList;
     private LabelValue[] siccZonaList = {};
     private List stoTipoDocumentoExcepcionList;
	 private LabelValue[] stoValidacionesExcepcionByDocumento;
	 
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
	    return new ReporteRECExcepcionValidacionesForm();
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
	   return "reporteRECExcepcionValidacionesXLS";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		
	   return null;
	}
	
	public void showZonasxRegion(ValueChangeEvent val){
		log.debug(">>showZonasxRegion ");
		log.debug("val: "+ ArrayUtils.toString(val.getNewValue()));
		ReporteRECExcepcionValidacionesForm form = (ReporteRECExcepcionValidacionesForm) this.formReporte;
		String[] regiones = (String []) val.getNewValue();		
	      
		if(!ArrayUtils.isEmpty(regiones)){
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			setSiccZonaList(aSvc.getZonasMultipleByPaisMarcaCanalRegion(form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, 
					Constants.CODIGO_CANAL_DEFAULT, regiones, Constants.FORMATO_TOTAL));
		}else{
			setSiccZonaList(null);
		}
		
		form.setZonaList(null);
	}

	
	public void showTipoDocXVald(ValueChangeEvent val){
		log.debug(">>showTipoDocXVald ");
		log.debug("val: "+ val.getNewValue());
		ReporteRECExcepcionValidacionesForm form = (ReporteRECExcepcionValidacionesForm) this.formReporte;
		String tipoDocumento = (String) val.getNewValue();		
	      
		if(StringUtils.isNotBlank(tipoDocumento)){
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			
			setStoValidacionesExcepcionByDocumento(aSvc.getValidacionesExcepcionByDocumento(form.getCodigoPais(), tipoDocumento));
		}else{
			setStoValidacionesExcepcionByDocumento(null);
		}
		
		form.setValidacion(null);
		
	}
	
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {

		ReporteRECExcepcionValidacionesForm f = (ReporteRECExcepcionValidacionesForm) this.formReporte;
		

		String condicionRegiones = obtieneCondicion(f.getRegionList(), "cod_regi", "'");
		String condicionZonas = obtieneCondicion(f.getZonaList(), "cod_zona", "'");

		params.put("codigoPais", f.getCodigoPais());
		params.put("tipoDocumento", f.getTipoDocumento());
		
		if(StringUtils.isBlank(f.getValidacion())){
			params.put("validacion", "");
		}else{
			params.put("validacion", " AND cod_vali = '" + f.getValidacion() + "'");
		}
		
		params.put("condicionRegiones", condicionRegiones);
		params.put("condicionZonas", condicionZonas);
		
		if(StringUtils.isBlank(f.getCodigoPeriodo())){
			params.put("codigoPeriodo", "");
		}else{
			params.put("codigoPeriodo", " AND cod_peri = '" + f.getCodigoPeriodo() + "'");	
		}
		
		if(StringUtils.isBlank(f.getConsultora())){
			params.put("codigoConsultora", "");
		}else{
			params.put("codigoConsultora", " AND cod_clie = '" + f.getConsultora() + "'");	
		}
		
		Date fecha = f.getFecha();
		if(fecha == null){
			params.put("fecha", "");
		}else{
			String sFecha = DateFormatUtils.format(fecha, "dd/MM/yyyy");
			params.put("fecha", " AND to_char(fec_proc, 'dd/mm/yyyy') = '" + sFecha + "'");	
		}
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		log.info("Entro a setViewAttributes");

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		
		// parametros generales
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
		
		ProcesoSTOEjecucionValidacionesService procesoSTOService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		ReporteRECExcepcionValidacionesForm f = (ReporteRECExcepcionValidacionesForm) this.formReporte;
		f.setCodigoPais(pais.getCodigo());
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoUsuario", usuario.getLogin());
		
		stoTipoDocumentoExcepcionList =  procesoSTOService.getTiposDocumentosExcepcionSTO(criteria);
		siccRegionList = reporteService.getListaGenerico("getRegionesByPais",criteria);
		
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
	 * @return the stoTipoDocumentoExcepcionList
	 */
	public List getStoTipoDocumentoExcepcionList() {
		return stoTipoDocumentoExcepcionList;
	}

	/**
	 * @param stoTipoDocumentoExcepcionList the stoTipoDocumentoExcepcionList to set
	 */
	public void setStoTipoDocumentoExcepcionList(List stoTipoDocumentoExcepcionList) {
		this.stoTipoDocumentoExcepcionList = stoTipoDocumentoExcepcionList;
	}

	/**
	 * @return the stoValidacionesExcepcionByDocumento
	 */
	public LabelValue[] getStoValidacionesExcepcionByDocumento() {
		return stoValidacionesExcepcionByDocumento;
	}

	/**
	 * @param stoValidacionesExcepcionByDocumento the stoValidacionesExcepcionByDocumento to set
	 */
	public void setStoValidacionesExcepcionByDocumento(
			LabelValue[] stoValidacionesExcepcionByDocumento) {
		this.stoValidacionesExcepcionByDocumento = stoValidacionesExcepcionByDocumento;
	}

	
	

	
}