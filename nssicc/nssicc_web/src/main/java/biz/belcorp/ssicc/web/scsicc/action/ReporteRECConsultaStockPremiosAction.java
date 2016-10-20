package biz.belcorp.ssicc.web.scsicc.action;


import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.collections.MapUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteRECConsultaStockPremiosForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaProductoSearchAction;


/**
 * 
 * @author <a href="">Marco Agurto</a>
 */
@ManagedBean
@SessionScoped
public class ReporteRECConsultaStockPremiosAction extends BaseReporteAbstractAction implements Serializable {
			
	private static final long serialVersionUID = 7496906172990857522L;

    private LabelValue[] siccConcursoList = {};
    private List siccMarcaList;
    private List siccCanalList;
	
 	/*Agregar estos atributos para el popup Producto*/
	private static final String POPUP_SACPRODUCTO = "SACPRODUCTO";
	@ManagedProperty(value="#{busquedaProductoSearchAction}")
	private BusquedaProductoSearchAction busquedaProductoSearchAction;
	private boolean mostrarPopupProducto;
    
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setInvocarPopup(java.lang.String)
	 */
	@Override
	protected void setInvocarPopup(String accion) {

		this.mostrarProcesoBatch = false;
		this.mostrarPopupProducto = true;
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setAceptarPopup(javax.faces.event.ActionEvent, java.lang.String)
	 */
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopup' method");
		}
		this.mostrarProcesoBatch = true;
		ReporteRECConsultaStockPremiosForm busquedaForm = (ReporteRECConsultaStockPremiosForm)this.formReporte;
		
		if (accion.equals(POPUP_SACPRODUCTO)) {
			this.mostrarPopupProducto = false;
			
			this.busquedaProductoSearchAction.verificarRegistro(event);
			if (this.busquedaProductoSearchAction.isSeleccionoRegistro()) {
				
				Map prodMap = (Map) this.busquedaProductoSearchAction.getBeanRegistroSeleccionado(); 

				busquedaForm.setCodigoSap(MapUtils.getString(prodMap, "codigoSap"));
				busquedaForm.setDescripcionCorta(MapUtils.getString(prodMap, "descripcionCorta"));
				this.busquedaProductoSearchAction.setBeanRegistroSeleccionado(null);
			}
		}	
		
		this.formReporte =  busquedaForm;

	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setSalirPopup()
	 */
	@Override
	protected void setSalirPopup() {
		this.mostrarProcesoBatch = true;
		this.mostrarPopupProducto = false;
		this.busquedaProductoSearchAction.setBeanRegistroSeleccionado(null);
	}
	
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
	    return new ReporteRECConsultaStockPremiosForm();
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteRECConsultaStockPremiosForm form = (ReporteRECConsultaStockPremiosForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(form.getFormatoExportacion()))
			return "reporteRECConsultaStockPremiosXLS";
		else
		   return "reporteMaestroHorizontalCustom43";
	   
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteRECConsultaStockPremiosForm form = (ReporteRECConsultaStockPremiosForm)	this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(form.getFormatoExportacion()))
			return null;
		else
		   return "reporteRECConsultaStockPremiosPDF";
	}
	
	public void showConcursos(ValueChangeEvent val){
		log.debug(">>showConcursos ");
		log.debug("val: "+ val.getNewValue());
		ReporteRECConsultaStockPremiosForm form = (ReporteRECConsultaStockPremiosForm) this.formReporte;
		String codigo = (String) val.getNewValue();		
		String clienteId = val.getComponent().getClientId();
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		
		if ("codigoCanal:select".equals(clienteId)) {
			setSiccConcursoList(aSvc.getConcursosByPaisMarcaCanal( form.getCodigoPais(), form.getCodigoMarca(), codigo, ""));
		}else{
			setSiccConcursoList(aSvc.getConcursosByPaisMarcaCanal( form.getCodigoPais(),codigo,form.getCodigoCanal(), ""));
		}

	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		
		ReporteRECConsultaStockPremiosForm reporteRECForm = (ReporteRECConsultaStockPremiosForm) this.formReporte;

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteria = new HashMap();
		//obtenemos la concion concurso
		String condicionConcurso = this.obtieneCondicion(reporteRECForm.getCodigoConcurso(), "num_conc", "'");
		criteria.put("numeroConcurso", reporteRECForm.getCodigoConcurso());

		String oidPais = reporteService.getOidString("getOidPaisByCodigoPais",params);
		params.put("oidPais",oidPais);		
		params.put("NroReporte", getReportResourceMessage("reporteRECConsultaStockPremiosForm.numero.reporte"));
		params.put("titulo", getReportResourceMessage("reporteRECConsultaStockPremiosForm.titulo"));
		params.put("condicionConcurso", condicionConcurso);	
		log.debug("condicion concurso "+condicionConcurso);
		log.debug("params "+params);
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		log.info("Entro a setViewAttributes");

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = true;
		
		ReporteRECConsultaStockPremiosForm f = (ReporteRECConsultaStockPremiosForm) this.formReporte;
		
		// parametros generales
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
		
		f.setCodigoPais(pais.getCodigo());
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		
		// Carga de los combos Marca, Canal
		siccMarcaList = reporteService.getMarcas();
		siccCanalList = reporteService.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		siccConcursoList = aSvc.getConcursosByPaisMarcaCanal(pais.getCodigo(),
															 Constants.CODIGO_MARCA_DEFAULT,
															 Constants.CODIGO_CANAL_DEFAULT, 
															 "");
		
		
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
	}

	/**
	 * @return the siccConcursoList
	 */
	public LabelValue[] getSiccConcursoList() {
		return siccConcursoList;
	}

	/**
	 * @param siccConcursoList the siccConcursoList to set
	 */
	public void setSiccConcursoList(LabelValue[] siccConcursoList) {
		this.siccConcursoList = siccConcursoList;
	}

	/**
	 * @return the siccMarcaList
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList the siccMarcaList to set
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * @return the siccCanalList
	 */
	public List getSiccCanalList() {
		return siccCanalList;
	}

	/**
	 * @param siccCanalList the siccCanalList to set
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	/**
	 * @return the busquedaProductoSearchAction
	 */
	public BusquedaProductoSearchAction getBusquedaProductoSearchAction() {
		return busquedaProductoSearchAction;
	}

	/**
	 * @param busquedaProductoSearchAction the busquedaProductoSearchAction to set
	 */
	public void setBusquedaProductoSearchAction(
			BusquedaProductoSearchAction busquedaProductoSearchAction) {
		this.busquedaProductoSearchAction = busquedaProductoSearchAction;
	}

	/**
	 * @return the mostrarPopupProducto
	 */
	public boolean isMostrarPopupProducto() {
		return mostrarPopupProducto;
	}

	/**
	 * @param mostrarPopupProducto the mostrarPopupProducto to set
	 */
	public void setMostrarPopupProducto(boolean mostrarPopupProducto) {
		this.mostrarPopupProducto = mostrarPopupProducto;
	}

	

	
}