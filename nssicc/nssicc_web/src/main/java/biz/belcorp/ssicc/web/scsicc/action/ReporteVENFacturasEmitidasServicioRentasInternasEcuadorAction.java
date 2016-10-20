package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteVENFacturasEmitidasServicioRentasInternasEcuadorForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteVENFacturasEmitidasServicioRentasInternasEcuadorAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 1L;
	
	private List siccSociedadList;
	private List siccTipoDocumentoList;
	private List siccTipoReporteList;
	private String tipoDocumento;
	private String tipoReporte;
	private boolean mostrarTipoDocFactura;
	private boolean mostrarTipoDocNotaCredito;
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteVENFacturasEmitidasServicioRentasInternasEcuadorForm form = new ReporteVENFacturasEmitidasServicioRentasInternasEcuadorForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(this.formatoExportacion)){
			if ("1".equals(this.tipoDocumento)){ //Facturas
				if("1".equals(this.tipoReporte)) //Cabecera
					return "reporteVENFacturasEmitidasServicioRentasInternasEcuadorFCXLS";
				else //Detalle
					return "reporteVENFacturasEmitidasServicioRentasInternasEcuadorFDXLS";
			}else if("2".equals(this.tipoDocumento)){ //Notas de Crédito
				if("1".equals(this.tipoReporte)) //Cabecera
					return "reporteVENFacturasEmitidasServicioRentasInternasEcuadorNCCXLS";
				else //Detalle
					return "reporteVENFacturasEmitidasServicioRentasInternasEcuadorNCDXLS";
			}else
				return "";
		}else{
			return "reporteMaestroHorizontal";
		}
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.info("Entro a ReporteVENFacturasEmitidasServicioRentasInternasEcuadorAction - setViewAttributes");
		}
		
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		ReporteVENFacturasEmitidasServicioRentasInternasEcuadorForm f = (ReporteVENFacturasEmitidasServicioRentasInternasEcuadorForm) this.formReporte;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		f.setCodigoPais(pais.getCodigo());
		this.siccSociedadList = service.getSociedadesByCodigoPais(pais.getCodigo());
		
		//Tipo Documento
		List listTipoDocumento = new ArrayList();
		Base[] tipoDoc = new Base[2];
		String[] tipoDocumento = { "Facturas", "Notas de Crédito" };
		for (int i = 0; i < 2; i++) {
			tipoDoc[i] = new Base();
			tipoDoc[i].setCodigo("" + (i + 1));
			tipoDoc[i].setDescripcion(tipoDocumento[i]);
			listTipoDocumento.add(tipoDoc[i]);
		}
		this.siccTipoDocumentoList = listTipoDocumento;
		
		//Tipo Reporte
		List listTipoReporte = new ArrayList();
		Base[] tipoRep = new Base[2];
		String[] tipoReporte = { "Cabecera", "Detalle" };
		for (int i = 0; i < 2; i++) {
			tipoRep[i] = new Base();
			tipoRep[i].setCodigo("" + (i + 1));
			tipoRep[i].setDescripcion(tipoReporte[i]);
			listTipoReporte.add(tipoRep[i]);
		}
		this.siccTipoReporteList = listTipoReporte;
		
		f.setTipoDocumento("1");
		this.mostrarTipoDocFactura = true;
		this.mostrarTipoDocNotaCredito = false;

		log.info("Salio a ReporteVENFacturasEmitidasServicioRentasInternasEcuadorAction - setViewAttributes");
	}
	
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if(log.isDebugEnabled()){
			log.info("Entro a ReporteVENFacturasEmitidasServicioRentasInternasEcuadorAction - prepareParameterMap");
		}
		
		ReporteVENFacturasEmitidasServicioRentasInternasEcuadorForm f = (ReporteVENFacturasEmitidasServicioRentasInternasEcuadorForm) this.formReporte;
		
		this.tipoDocumento = f.getTipoDocumento();
		this.tipoReporte = f.getTipoReporte();
		
		if(f.getFechaFacturacionD() != null){
			f.setFechaFacturacion(DateUtil.convertDateToString(f.getFechaFacturacionD()));
		}
		
		if(f.getFechaEmisionD() != null){
			f.setFechaEmision(DateUtil.convertDateToString(f.getFechaEmisionD()));
		}

		params.put("fechaFacturacion", f.getFechaFacturacion());
		params.put("fechaEmision", f.getFechaEmision());

		log.info("Salio a ReporteVENFacturasEmitidasServicioRentasInternasEcuadorAction - prepareParameterMap");
		
		return params;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #setValidarReporte()
	 */
	public String setValidarReporte() {
		if(log.isDebugEnabled()){
			log.info("Entro a ReporteVENFacturasEmitidasServicioRentasInternasEcuadorAction - setValidarReporte");
		}
		
		ReporteVENFacturasEmitidasServicioRentasInternasEcuadorForm form = (ReporteVENFacturasEmitidasServicioRentasInternasEcuadorForm) this.formReporte;
		
//		Date fecha1D = form.getFechaInicioD();
//		Date fecha2D = form.getFechaFinD();
		
//		if (fecha2D.compareTo(fecha1D) < 0) {
//			String mensaje = this.getResourceMessage("reporteVENCabecerasFacturasAnuladasForm.validar.fechas");
//			
//			return mensaje;
//		}
		
		return null;
	}
	
	public void muestraCamposPorTipoDocumento(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug(">>>> muestraCamposPorTipoDocumento...");
			log.debug("val: "+ val.getNewValue());
		}
		
		ReporteVENFacturasEmitidasServicioRentasInternasEcuadorForm form = (ReporteVENFacturasEmitidasServicioRentasInternasEcuadorForm) this.formReporte;
		
	    String tipoDocumento = (String) val.getNewValue();
	    form.setTipoDocumento(tipoDocumento);
	    
	    if (StringUtils.equals(tipoDocumento, "1")) {
			this.mostrarTipoDocFactura = true;
			this.mostrarTipoDocNotaCredito = false;
		}else if(StringUtils.equals(tipoDocumento, "2")){
			this.mostrarTipoDocFactura = false;
			this.mostrarTipoDocNotaCredito = true;
		}
	    
	    form.setCodigoPeriodo("");
		form.setFechaFacturacionD(null);
		form.setFechaEmisionD(null);
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
	 * @return the tipoReporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte the tipoReporte to set
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	/**
	 * @return the siccTipoDocumentoList
	 */
	public List getSiccTipoDocumentoList() {
		return siccTipoDocumentoList;
	}

	/**
	 * @param siccTipoDocumentoList the siccTipoDocumentoList to set
	 */
	public void setSiccTipoDocumentoList(List siccTipoDocumentoList) {
		this.siccTipoDocumentoList = siccTipoDocumentoList;
	}

	/**
	 * @return the siccTipoReporteList
	 */
	public List getSiccTipoReporteList() {
		return siccTipoReporteList;
	}

	/**
	 * @param siccTipoReporteList the siccTipoReporteList to set
	 */
	public void setSiccTipoReporteList(List siccTipoReporteList) {
		this.siccTipoReporteList = siccTipoReporteList;
	}

	/**
	 * @return the mostrarTipoDocFactura
	 */
	public boolean isMostrarTipoDocFactura() {
		return mostrarTipoDocFactura;
	}

	/**
	 * @param mostrarTipoDocFactura the mostrarTipoDocFactura to set
	 */
	public void setMostrarTipoDocFactura(boolean mostrarTipoDocFactura) {
		this.mostrarTipoDocFactura = mostrarTipoDocFactura;
	}

	/**
	 * @return the mostrarTipoDocNotaCredito
	 */
	public boolean isMostrarTipoDocNotaCredito() {
		return mostrarTipoDocNotaCredito;
	}

	/**
	 * @param mostrarTipoDocNotaCredito the mostrarTipoDocNotaCredito to set
	 */
	public void setMostrarTipoDocNotaCredito(boolean mostrarTipoDocNotaCredito) {
		this.mostrarTipoDocNotaCredito = mostrarTipoDocNotaCredito;
	}

	/**
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
}