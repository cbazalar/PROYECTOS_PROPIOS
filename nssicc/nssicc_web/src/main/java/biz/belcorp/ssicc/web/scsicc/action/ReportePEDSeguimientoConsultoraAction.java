package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.springframework.core.io.ClassPathResource;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReportePEDSeguimientoConsultoraForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDEnviarReporteSeguimientoConsultoraService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;



@SuppressWarnings({"unchecked","rawtypes"})
@SessionScoped
@ManagedBean
public class ReportePEDSeguimientoConsultoraAction extends BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5960463211630952974L;
	private List siccRegionList;
	private List siccEstatusList;
	private LabelValue[] stoZonaList;
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {

		ReportePEDSeguimientoConsultoraForm r = new ReportePEDSeguimientoConsultoraForm();
		return r;
	}
	
	@Override
	protected String devuelveBeanReporteService() {
		// TODO Auto-generated method stub
		return "reportes.reportePEDSeguimientoConsultoraServiceImpl";
	}
	
	@Override
	protected String devuelveNombreReporte() throws Exception {
		
		String reporte=  "reportePEDSeguimientoConsultoraDXLS";
		return reporte;
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		String reporte="";
		return reporte;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		
		ReportePEDSeguimientoConsultoraForm reportePEDForm = (ReportePEDSeguimientoConsultoraForm) this.formReporte;
		
		String condicionZonas = obtieneCondicion(reportePEDForm.getZonaList(), "f.cod_zona", "'");
		String[] region = new String[]{reportePEDForm.getRegionList()};
		String condicionRegion = obtieneCondicion(region, "g.cod_regi", "'");
		
		String condicionEstatus = obtieneCondicion(reportePEDForm.getEstatusList(), "mcda.esta_oid_esta_clie", "'");
		
		String condicion = condicionZonas + condicionRegion + condicionEstatus;
								  		        						
		params.put("condicion", condicion);
		this.setVisualizarReporte(true);				
		return params;
	
	}

	@Override
	protected void setViewAtributes() throws Exception {
		
		this.mostrarReporteXLS = true;
		this.mostrarReporteOCSV = true;
		this.mostrarReportePDF = false;
		
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		Map criteriaOperacion = new HashMap();
		
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		
		this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);
		
		this.siccEstatusList = reporteService.getListaGenerico("getEstadoSaldoConsultora",criteriaOperacion);

		ReportePEDSeguimientoConsultoraForm f =  (ReportePEDSeguimientoConsultoraForm) this.formReporte;
		f.setDescripcionEstatusList(null);
		f.setDescripcionRegionList(null);
		f.setDescripcionZonaList(null);
		f.setRegionList(null);
		f.setZonaList(null);
		f.setEstatusList(null);
		
	}

	/**
	 * 
	 * @param actionEvent
	 * @throws Exception
	 */
	public void enviar(ActionEvent actionEvent) {
		
		try
		{
			Map params = new HashMap();
			this.prepareParameterMap(params);
			ReportePEDSeguimientoConsultoraForm reportePEDForm = (ReportePEDSeguimientoConsultoraForm) this.formReporte;
			ProcesoPEDEnviarReporteSeguimientoConsultoraService service = (ProcesoPEDEnviarReporteSeguimientoConsultoraService)getBean("spusicc.procesoPEDEnviarReporteSeguimientoConsultoraService");
			ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
			
			setParametros(params, reportePEDForm);
			params.put("regiones", new String[]{reportePEDForm.getRegionList()});
			params.put("zonas", reportePEDForm.getZonaList());
			
			reporteService.insertReportePEDSeguimientoConsultora(params);
			
			service.executeEnviarReporteSeguimientoConsultora(params);
							
			this.addInfo("Mensaje: ", getResourceMessage("reportePEDSeguimientoConsultoraForm.sended"));
		}
		catch(Exception ex){
			this.addError("Error: ", ex.getMessage());
		}				
	}
	
	
	private void setParametros(Map params, ReportePEDSeguimientoConsultoraForm reporteForm) throws Exception {
		
		ClassPathResource resource = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteLECProyeccion" + JASPER_EXTENSION);
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		
		params.put("SUBREPORT_DIR1",  (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource.getPath() )) );
		
		params.put("codigoUsuario", usuario.getLogin());
		params.put("usuarioLogin", usuario.getLogin());
		params.put("codigoPais", reporteForm.getCodigoPais());
		params.put("pais", pais);
		params.put("rutaPath", externalContext.getRealPath("/"));
		
		params.put("NroReporte", "");
		params.put("titulo", getResourceMessage("reportePEDSeguimientoConsultoraForm.titulo.detallado"));
		
		//El reporte en pantalla muestra todas las zonas y regiones seleccionadas
		params.put("codigoRegion", null);
		params.put("codigoZona", null);
	}
	
	/**
	 * Metodo para cargar las zonas
	 * @param val
	 */
	public void loadZonas(ValueChangeEvent val){
		log.debug(">>loadZonas");
		log.debug("val: " + (String)val.getNewValue());
			
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		String valor = (String)val.getNewValue();
		String [] regiones={valor};
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		
			if(regiones.length > 0){
				
				this.stoZonaList = ajax.getZonasMultipleByPaisMarcaCanalRegion(pais.getCodigo(), "T", "VD",  regiones,"");
			}						
			
	}

	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public List getSiccEstatusList() {
		return siccEstatusList;
	}

	public void setSiccEstatusList(List siccEstatusList) {
		this.siccEstatusList = siccEstatusList;
	}

	public LabelValue[] getStoZonaList() {
		return stoZonaList;
	}

	public void setStoZonaList(LabelValue[] stoZonaList) {
		this.stoZonaList = stoZonaList;
	}
}
