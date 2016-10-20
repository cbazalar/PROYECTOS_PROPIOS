package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCCCDetalladoCuponesPagoForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCOMComparativoRetailSiccForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteRECPremiosReclamosForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteRECCDRsCategoriaProductosForm;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSICFletesForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaProductoSearchAction;



@SuppressWarnings({"unchecked","rawtypes"})
@SessionScoped
@ManagedBean
public class ReporteRECPremiosReclamosAction extends BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5960463211630952974L;
	
	private Boolean mostrarCodigoProducto;
	private List siccSociedadList;
	
	/*Agregar estos atributos para el popup Producto*/
	private static final String POPUP_SACPRODUCTO = "SACPRODUCTO";
	@ManagedProperty(value="#{busquedaProductoSearchAction}")
	private BusquedaProductoSearchAction busquedaProductoSearchAction;
	private boolean mostrarPopupProducto;
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {

		ReporteRECPremiosReclamosForm r = new ReporteRECPremiosReclamosForm();
		return r;
	}

	
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteRECPremiosReclamosForm form = (ReporteRECPremiosReclamosForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		String reporte= "";
		
		if ("XLS".equals(form.getFormatoExportacion())){
			reporte = "reporteRECPremiosReclamos"+form.getTipoReporte()+"XLS";
			
		}
			
		return reporte;
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteRECPremiosReclamosForm form = (ReporteRECPremiosReclamosForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		
		ReporteRECPremiosReclamosForm reportePEDForm = (ReporteRECPremiosReclamosForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		params.put("titulo",getResourceMessage("reporteRECPremiosReclamosForm.titulo"));
		
		params.put("periodoInicio", reportePEDForm.getCodigoPeriodoInicio());
		params.put("periodoFin", reportePEDForm.getCodigoPeriodoFin());
		params.put("codigoSociedad", reportePEDForm.getCodigoSociedad());
		
		Map criteriaPais = new HashMap();
		criteriaPais.put("codigoPais", reportePEDForm.getCodigoPais());
		
		params.put("oidPais", reporteService.getOidString("getOidPaisByCodigoPais", criteriaPais));
		
		if(this.mostrarCodigoProducto){
			params.put("codigoProducto", reportePEDForm.getCodigoSap());
		}
		
		this.setVisualizarReporte(true);
		
		return params;
	
	}

	@Override
	protected void setViewAtributes() throws Exception {
		
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		String codpais = pais.getCodigo();
		siccSociedadList = service.getSociedadesByCodigoPais(codpais);
		
		ReporteRECPremiosReclamosForm f =  (ReporteRECPremiosReclamosForm) this.formReporte;
		
		f.setCodigoPeriodoInicio(null);
		f.setCodigoPeriodoFin(null);
		f.setCodigoSap(null);
		
		this.mostrarCodigoProducto = false;
	}
	
	/**
	 * Metodo para Cambiar Filtro
	 * 
	 * @param val
	 */
	public void loadFiltro(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadFiltro");
		}
		ReporteRECPremiosReclamosForm form = (ReporteRECPremiosReclamosForm) this.formReporte;
		String valor = (String) val.getNewValue();
		if (StringUtils.equalsIgnoreCase(valor, "PA")) {
			this.mostrarCodigoProducto = true;
		} else{ 
			this.mostrarCodigoProducto = false;
			form.setCodigoSap(null);
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	@Override
	public String setValidarReporte() {
		if(log.isDebugEnabled()){
			log.debug("setValidarReporte");
		}
		ReporteRECPremiosReclamosForm form = (ReporteRECPremiosReclamosForm) this.formReporte;
		if(StringUtils.isBlank(form.getTipoReporte())){
			this.setMensajeAlertaDefault(this.getResourceMessage("reporteRECPremiosReclamosForm.msg.tipoReporte"));
			return this.getMensajeAlertaDefault();
		}else
		{
			Integer fecha1, fecha2;
			fecha1 = Integer.parseInt(form.getCodigoPeriodoInicio());
			fecha2 = Integer.parseInt(form.getCodigoPeriodoFin());
			if (fecha1 > fecha2) {
				String mensaje = this
						.getResourceMessage("reporteRECPremiosReclamosForm.errorInicioMayor");
				return mensaje;
			}
		}
		return null;
	}


	/**
	 * @return the siccSociedadList
	 */
	public List getSiccSociedadList() {
		return siccSociedadList;
	}


	/**
	 * @param siccSociedadList the siccSociedadList to set
	 */
	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}


	/**
	 * @return the mostrarCodigoProducto
	 */
	public Boolean getMostrarCodigoProducto() {
		return mostrarCodigoProducto;
	}


	/**
	 * @param mostrarCodigoProducto the mostrarCodigoProducto to set
	 */
	public void setMostrarCodigoProducto(Boolean mostrarCodigoProducto) {
		this.mostrarCodigoProducto = mostrarCodigoProducto;
	}
	
	@Override
	protected void setInvocarPopup(String accion) {

		this.mostrarProcesoBatch = false;
		this.mostrarPopupProducto = true;
		
	}
	
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopup' method");
		}
		this.mostrarProcesoBatch = true;
		ReporteRECPremiosReclamosForm busquedaForm = (ReporteRECPremiosReclamosForm)this.formReporte;
		
		if (accion.equals(POPUP_SACPRODUCTO)) {
			this.mostrarPopupProducto = false;
			
			this.busquedaProductoSearchAction.verificarRegistro(event);
			if (this.busquedaProductoSearchAction.isSeleccionoRegistro()) {
				
				Map prodMap = (Map) this.busquedaProductoSearchAction.getBeanRegistroSeleccionado(); 

				busquedaForm.setCodigoSap(MapUtils.getString(prodMap, "codigoSap"));
				this.busquedaProductoSearchAction.setBeanRegistroSeleccionado(null);
			}
		}	
		
		this.formReporte =  busquedaForm;

	}
	
	@Override
	protected void setSalirPopup() {
		this.mostrarProcesoBatch = true;
		this.mostrarPopupProducto = false;
		this.busquedaProductoSearchAction.setBeanRegistroSeleccionado(null);
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
