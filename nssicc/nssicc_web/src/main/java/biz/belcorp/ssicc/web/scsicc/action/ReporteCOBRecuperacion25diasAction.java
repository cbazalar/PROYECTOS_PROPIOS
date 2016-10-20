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
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCOBRecuperacion25diasForm;

@ManagedBean
@SessionScoped
public class ReporteCOBRecuperacion25diasAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 5190289864800765080L;
	private static final String CODIGO_REGION = "R";
	private static final String CODIGO_ZONA = "Z";
	private static final String CODIGO_SECCION = "S";
	
	private String tipoVista;
	private List siccRegionList;
	private List siccSociedadList;
	private LabelValue[] siccZonaList = {};
	private LabelValue[] siccSeccionList = {};
	private boolean mostrarZona;
	private boolean mostrarSeccion;	

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOBRecuperacion25diasForm reporteForm = new ReporteCOBRecuperacion25diasForm();
		return reporteForm;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if(StringUtils.equals("XLS", this.formatoExportacion)) {
		    if (this.tipoVista.equals(CODIGO_REGION))
		    	return "reporteCOBRecuperacion25diasRXLS";
		    if  (this.tipoVista.equals(CODIGO_ZONA))
		    	return "reporteCOBRecuperacion25diasZXLS";
		    if  (this.tipoVista.equals(CODIGO_SECCION))
		    	return "reporteCOBRecuperacion25diasSXLS";
		}    
		else 
			return "reporteMaestroHorizontal";	
		return null;
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteCOBRecuperacion25diasForm f= (ReporteCOBRecuperacion25diasForm)this.formReporte;
		this.tipoVista = f.getTipoVista();
		String condicionRegion = this.obtieneCondicion(f.getRegionList(), "aa.cod_regi", "'");
		String condicionZonas = new String("");
		String condicionSeccion = new String("");
		String titulo01 = new String("");
		if (this.tipoVista.equals(CODIGO_REGION)) 
			titulo01 = this.getResourceMessage("reporteCOBRecuperacion25diasForm.tituloReporteRegion");
		else if (this.tipoVista.equals(CODIGO_ZONA)) {
			titulo01 = this.getResourceMessage("reporteCOBRecuperacion25diasForm.tituloReporteZona");
			condicionZonas = this.obtieneCondicion(f.getZonaList(),"aa.cod_zona", "'");
		}
		else if (this.tipoVista.equals(CODIGO_SECCION)) {
			titulo01 = this.getResourceMessage("reporteCOBRecuperacion25diasForm.tituloReporteSeccion");
			condicionZonas = this.obtieneCondicion(f.getZonaList(),"aa.cod_zona", "'");
			condicionSeccion = this.obtieneCondicion(f.getSeccionList(),"aa.cod_secc", "'");
		}
		
		String titulo = this.getResourceMessage("reporteCOBRecuperacion25diasForm.tituloReporte", new Object[ ] {titulo01, f.getCodPeriodoInicial(), f.getCodPeriodoFinal()});
		params.put("titulo", titulo);
		String condicion = condicionSeccion + condicionZonas + condicionRegion;
		params.put("codperInicio", f.getCodPeriodoInicial());
		params.put("codperFin", f.getCodPeriodoFinal());
		params.put("condicion", condicion);
		return params;

	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;	
		this.mostrarZona = false;
		this.mostrarSeccion = false;
		
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ReporteCOBRecuperacion25diasForm f= (ReporteCOBRecuperacion25diasForm)this.formReporte;
		f.setCodigoPais(pais.getCodigo());
		
		Map criteria = new HashMap();		
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		criteria.put("codigoPais", pais.getCodigo());		
		this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais",criteria);
		this.siccSociedadList = service.getSociedadesByCodigoPais(pais.getCodigo());
		
		
		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", pais.getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO);
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO);

		MantenimientoOCRPedidoControlFacturacionService controlFactService = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = controlFactService.getControlFacturacionById(criteriaPeriodo);

		f.setCodPeriodoInicial(controlFacturacion.getCodigoPeriodo());
		f.setCodPeriodoFinal(controlFacturacion.getCodigoPeriodo());
		
	}
	
	/**
	 * @param val
	 */
	public void loadReportes(ValueChangeEvent val) {
		try {
			String codigo = (String) val.getNewValue();	
			if (codigo.equals(CODIGO_REGION)) {
				this.mostrarZona = false;
				this.mostrarSeccion = false;
			}
			else if (codigo.equals(CODIGO_ZONA)) {
				this.mostrarZona = true;
				this.mostrarSeccion = false;
			}
			else if (codigo.equals(CODIGO_SECCION)) {
				this.mostrarZona = true;
				this.mostrarSeccion = true;
			}
		} catch (Exception e) {
			this.addError("Error:", this.obtieneMensajeErrorException(e));
		}
		
	}
	
	//Muestras las Zonas por las Regiones Seleccionadas
	public void loadZonas(ValueChangeEvent val) {
		try {
			String[] regiones = (String []) val.getNewValue();	
			ReporteCOBRecuperacion25diasForm f= (ReporteCOBRecuperacion25diasForm)this.formReporte;
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			this.siccZonaList = ajax.getZonasMultipleByPaisMarcaCanalRegion(f.getCodigoPais(), "T", "VD", regiones, "T");
			this.siccSeccionList=null;
		} catch (Exception e) {
			this.addError("Error:", this.obtieneMensajeErrorException(e));
		}
		
	}
	
	//Muestras las Zonas por las Secciones seleccionadas
	public void loadSecciones(ValueChangeEvent val) {
		try {
			String[] zonas = (String []) val.getNewValue();	
			ReporteCOBRecuperacion25diasForm f= (ReporteCOBRecuperacion25diasForm)this.formReporte;			
			AjaxService ajax = (AjaxService) getBean("ajaxService");		
			this.siccSeccionList=ajax.getSeccionMultipleByPaisMarcaCanalRegionZona(
									f.getCodigoPais(), "T", "VD", f.getRegionList() , zonas, "T");
		} catch (Exception e) {
			this.addError("Error:", this.obtieneMensajeErrorException(e));
		}
		
	}
	@Override
	public String setValidarReporte(){
		ReporteCOBRecuperacion25diasForm f= (ReporteCOBRecuperacion25diasForm)this.formReporte;	
		Integer fecha1, fecha2;		
		fecha1 = Integer.parseInt(f.getCodPeriodoInicial());
		fecha2 = Integer.parseInt(f.getCodPeriodoFinal());
		if (fecha1 > fecha2) {
			String mensaje = "Campaña Hasta debe ser mayor o igual a Campaña Desde";
			return mensaje;
		}
		return "";
	}

	
	
	/* GET -SET */
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
	 * @return the siccSeccionList
	 */
	public LabelValue[] getSiccSeccionList() {
		return siccSeccionList;
	}

	/**
	 * @param siccSeccionList the siccSeccionList to set
	 */
	public void setSiccSeccionList(LabelValue[] siccSeccionList) {
		this.siccSeccionList = siccSeccionList;
	}

	/**
	 * @return the tipoVista
	 */
	public String getTipoVista() {
		return tipoVista;
	}

	/**
	 * @param tipoVista the tipoVista to set
	 */
	public void setTipoVista(String tipoVista) {
		this.tipoVista = tipoVista;
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
	 * @return the mostrarZona
	 */
	public boolean isMostrarZona() {
		return mostrarZona;
	}

	/**
	 * @param mostrarZona the mostrarZona to set
	 */
	public void setMostrarZona(boolean mostrarZona) {
		this.mostrarZona = mostrarZona;
	}

	/**
	 * @return the mostrarSeccion
	 */
	public boolean isMostrarSeccion() {
		return mostrarSeccion;
	}

	/**
	 * @param mostrarSeccion the mostrarSeccion to set
	 */
	public void setMostrarSeccion(boolean mostrarSeccion) {
		this.mostrarSeccion = mostrarSeccion;
	}
	
	

}
