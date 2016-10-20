package biz.belcorp.ssicc.web.scsicc.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteRECPedidosRobadosForm;


/**
 * The Class ReporteCOBDetalladoCobranza31DiasAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 18/03/2014
 */
@ManagedBean
@SessionScoped
public class ReporteRECPedidosRobadosAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 1L;

	List recCodigoConsultoraArchivoList = new ArrayList();
	List siccRegionList = new ArrayList();
	List siccZonaList = new ArrayList();
	private String attachment;	

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteRECPedidosRobadosForm form = new ReporteRECPedidosRobadosForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("devuelveNombreReporte");
		}
		ReporteRECPedidosRobadosForm form = (ReporteRECPedidosRobadosForm)this.formReporte;
		String formatoExportacion = form.getFormatoExportacion()+ form.getTipoReporte();
		if ("XLS0".equals(formatoExportacion))
			return "reporteRECCabeceraFacturasXLS";
		else if ("XLS1".equals(formatoExportacion))
			return "reporteRECDetalleFacturasXLS";
		else if ("XLS2".equals(formatoExportacion))
			return "reporteRECCabeceraNotasCreditoXLS";
		else if ("XLS3".equals(formatoExportacion))
			return "reporteRECDetalleNotasCreditoXLS";
		else
			return "reporteMaestroHorizontal";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("prepareParameterMap");
		}
		
		ReporteRECPedidosRobadosForm form = (ReporteRECPedidosRobadosForm) this.formReporte;
		String condicionRegiones = obtieneCondicion(form.getRegionList(), "zr.cod_regi", "'");
		String condicionZonas = obtieneCondicion(form.getZonaList(), "zz.cod_zona", "'");
		
		if(StringUtils.equals(form.getTipoReporte(), Constants.NUMERO_UNO) || 
				StringUtils.equals(form.getTipoReporte(), Constants.NUMERO_TRES)){
			condicionRegiones = obtieneCondicionIN(form.getRegionList(), "zr.cod_regi", "'");
			condicionRegiones = "AND GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(m.cod_clie,'COD_REGI') " + condicionRegiones; 
		}
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		if(form.getFechaAnulacionDate()!=null){
			form.setFechaAnulacion(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, form.getFechaAnulacionDate()));
		}
		
		Map criteria = params;
		criteria.put("codigoPeriodo", form.getCodigoPeriodo());		
		
		String oidPeriodo = reporteService.getOidString("getOidPeriodoByCodigoPeriodo", criteria);
		params.put("oidPeriodo", oidPeriodo);
		params.put("codigoPais", form.getCodigoPais());
		params.put("fechaAnulacion", form.getFechaAnulacion());
		params.put("condicionRegiones", condicionRegiones);
		params.put("condicionZonas", condicionZonas);

		if(this.getRecCodigoConsultoraArchivoList() != null && this.getRecCodigoConsultoraArchivoList().size() > 0){
			String[] listaCodigoCliente = new String[this.getRecCodigoConsultoraArchivoList().size()];
			
			for (int i = 0; i < this.getRecCodigoConsultoraArchivoList().size(); i++) {
				listaCodigoCliente[i] = this.getRecCodigoConsultoraArchivoList().get(i).toString();
			}			
			params.put("codigoConsultora", obtieneCondicion(listaCodigoCliente, "m.cod_clie", "'"));
		}else{
			params.put("codigoConsultora", "AND m.cod_clie like '%"+ form.getConsultora()+"%'");
		}
		
		this.setRecCodigoConsultoraArchivoList(new ArrayList());
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAtributes' method");
		}
		
		this.mostrarReportePDF = false;
		this.mostrarReporteXLS = true;
				
		ReporteRECPedidosRobadosForm form = (ReporteRECPedidosRobadosForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteria = new HashMap();
		criteria.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		form.setCodigoPais(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		this.setSiccRegionList(reporteService.getListaGenerico("getRegionesByPais",criteria));
	}
	
	
	/**
	 * Show zonasx region.
	 *
	 * @param val the val
	 */
	public void showZonasxRegion(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		log.debug(val.getNewValue().toString());
		if(StringUtils.isNotEmpty(val.getNewValue().toString()) 
				|| StringUtils.isNotBlank(val.getNewValue().toString())){
			String[] regionListado = (String[])val.getNewValue();
			log.debug(regionListado.length);
			
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			if(regionListado.length>0){
				siccZonaList = Arrays.asList(ajax.getZonasMultipleByPaisMarcaCanalRegion(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), "T", "VD", regionListado,"T"));			
			}else{
				siccZonaList = new ArrayList();
			}
		}
	}
	
	/**
	 * Metodo que valida el archivo cargado en el servidor.
	 *
	 * @param e the e
	 */
	public void validar(){
		if(log.isDebugEnabled()){
			log.debug("validar");
		}
		ReporteRECPedidosRobadosForm f = (ReporteRECPedidosRobadosForm) this.formReporte;
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		
		String tipoReporte = f.getTipoReporte();
		String codigoPeriodo = f.getCodigoPeriodo();
		String fechaAnulacion = f.getFechaAnulacion();
		String consultora = f.getConsultora();
		String regiones[] = f.getRegionList();
		String zonas[] = f.getZonaList();
		BufferedReader br = null;
		try{
			
				br =  new BufferedReader(new InputStreamReader(f.getArchivo().getInputstream()));
				String linea;
				int cont = 0;
				while(true) {
					linea = br.readLine();
					if (linea == null)
						break;
					
					if(StringUtils.isNotBlank(linea.trim()) && StringUtils.isNumeric(linea.trim())){
						this.recCodigoConsultoraArchivoList.add(linea.trim());
					}else{
						cont++;
					}
				}
				
				if(cont > 0){
					String valores[] = {"" + cont};					
					this.addError("Error: ", this.getResourceMessage("reporteRECPedidosRobadosForm.errors.datos.no.numericos",valores));
				}
				
				
				//Cargamos las zonas si es que se han seleccionado		
				if(regiones != null && regiones.length > 0){
					this.siccZonaList = Arrays.asList(ajaxService.getZonasMultipleByPaisMarcaCanalRegion(
										this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), 
										Constants.CODIGO_MARCA_DEFAULT, 
										Constants.CODIGO_CANAL_DEFAULT, 
										regiones, 
										Constants.FORMATEAR_TODOS));			
				}

//				session.setAttribute("ReporteRECPedidosRobadosForm_tipoReporte", tipoReporte);		
//				session.setAttribute("ReporteRECPedidosRobadosForm_codigoPeriodo", codigoPeriodo);
//				session.setAttribute("ReporteRECPedidosRobadosForm_fechaAnulacion", fechaAnulacion);
//				session.setAttribute("ReporteRECPedidosRobadosForm_consultora", consultora);
//				session.setAttribute("ReporteRECPedidosRobadosForm_regiones", regiones);
//				session.setAttribute("ReporteRECPedidosRobadosForm_zonas", zonas);
//				session.setAttribute("ReporteRECPedidosRobadosForm_archivo", "Archivo: " + archivo.getFileName());
//				
			
			br.close();
		}catch(Exception ex) {
			log.error("Mensaje de excepcion: " + ex.getMessage());
			ex.printStackTrace();
		}finally{
			log.debug("fin");
		}
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		if(log.isDebugEnabled()){
			log.debug("handleFileUpload");
		}
		ReporteRECPedidosRobadosForm f = (ReporteRECPedidosRobadosForm) this.formReporte;
		InputStreamReader  is = null;
		if(event != null){
			f.setArchivo(event.getFile());
			this.setAttachment(event.getFile().getFileName());
			this.validar();
		}
	}	
	
	
	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public List getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(List siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	public List getRecCodigoConsultoraArchivoList() {
		return recCodigoConsultoraArchivoList;
	}

	public void setRecCodigoConsultoraArchivoList(
			List recCodigoConsultoraArchivoList) {
		this.recCodigoConsultoraArchivoList = recCodigoConsultoraArchivoList;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	
}