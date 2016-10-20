package biz.belcorp.ssicc.web.spusicc.cobranzas.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasService;
import biz.belcorp.ssicc.service.util.ExcelUtil;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.ProcesoCOBCargarConsultorasDeudaCampanaForm;

@ManagedBean
@SessionScoped
public class ProcesoCOBCargarConsultorasDeudaCampanaAction extends BaseProcesoAbstractAction {

	private static final long serialVersionUID = 1L;
	
	private String attachment = "";
	private boolean mostrarBotonReporte;
	
	@ManagedProperty(value = "#{reporteCOBCargarConsultorasDeudaCampanaAction}")
    private ReporteCOBCargarConsultorasDeudaCampanaAction reporteCOBCargarConsultorasDeudaCampanaAction;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoCOBCargarConsultorasDeudaCampanaForm form = new ProcesoCOBCargarConsultorasDeudaCampanaForm();
		
		return form;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		
		ProcesoCOBCargarConsultorasDeudaCampanaForm f = (ProcesoCOBCargarConsultorasDeudaCampanaForm)this.formProceso;
		ProcesoZONActualizarUnidadesGeograficasService serviceUnidad = (ProcesoZONActualizarUnidadesGeograficasService) getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");
		
		f.setDirectorioTemporal(serviceUnidad.obtenerPathUpload(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo()));
		f.setCodigoPais(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		
		this.mostrarBotonExecute = false;
		this.mostrarBotonReporte = false;
		this.attachment = "";
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		if(log.isDebugEnabled()){
			log.debug("handleFileUpload");
		}
		
		try {
			ProcesoCOBCargarConsultorasDeudaCampanaForm f = (ProcesoCOBCargarConsultorasDeudaCampanaForm)this.formProceso;
			
			if(event != null){
				f.setArchivo(event.getFile());
				f.setNombreArchivo(event.getFile().getFileName());
				this.setAttachment(event.getFile().getFileName());
			}
			
			this.mostrarBotonExecute = false;
			this.mostrarBotonReporte = true;
		
			this.uploadArchivo();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * carga el archivo al temporal
	 * @param form
	 * @throws Exception
	 */
	private void uploadArchivo() throws IOException {
		if(log.isDebugEnabled()){
			log.debug("uploadArchivo");			
		}
		ProcesoCOBCargarConsultorasDeudaCampanaForm f = (ProcesoCOBCargarConsultorasDeudaCampanaForm) this.formProceso;
		// leyemos el stream de entrada
		InputStream is = f.getArchivo().getInputstream();
		// archivo del cliente
		FileOutputStream os = new FileOutputStream(new File(f.getDirectorioTemporal(), 
								f.getNombreArchivo()));
		// grabamos cada 1024 bytes
		int bytesRead = 0;
		byte[] buffer = new byte[1024];
		while ((bytesRead = is.read(buffer, 0, 1024)) != -1) {
			os.write(buffer, 0, bytesRead);
		}
		os.close();
		f.setArchivo(null);		
	}
	
	/**
     * @param actionEvent
     * @throws Exception
     */
    public void generarReporte(ActionEvent actionEvent) throws Exception{
    	if(log.isDebugEnabled()){
			log.debug("generarReporte...");
		}
    	
    	ProcesoCOBCargarConsultorasDeudaCampanaForm f = (ProcesoCOBCargarConsultorasDeudaCampanaForm)this.formProceso;
    	
//    	this.uploadArchivo();
    	
	    //Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(f.getDirectorioTemporal(), f.getNombreArchivo());
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);
		
		int cont = 0;
		List lista = new ArrayList();
		
		while(excelUtil.hasNext()) {
			Map mapRow = excelUtil.next();
			
			String codigoCliente = (String)mapRow.get("0");
			if(StringUtils.isNotBlank(codigoCliente)){
				lista.add(cont, codigoCliente);
				cont ++;
			}
		}
		excelUtil.cerrar();
		
		String[] consultoras = new String[cont];
		
		for (int i = 0; i < lista.size(); i++) {
			String valor = String.valueOf(lista.get(i));
			
			if(StringUtils.isNotBlank(valor)){
				consultoras[i] = valor;
			}
		}
    	
    	this.reporteCOBCargarConsultorasDeudaCampanaAction.setFormatoExportacion("XLS");
    	this.reporteCOBCargarConsultorasDeudaCampanaAction.setConsultoras(consultoras);
    	this.reporteCOBCargarConsultorasDeudaCampanaAction.executeReporte();
    }
    
    @Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)throws Exception {
		if(log.isDebugEnabled()){
			log.debug("executeProcess");
		}
		
		return params;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	/**
	 * @return the mostrarBotonReporte
	 */
	public boolean isMostrarBotonReporte() {
		return mostrarBotonReporte;
	}

	/**
	 * @param mostrarBotonReporte the mostrarBotonReporte to set
	 */
	public void setMostrarBotonReporte(boolean mostrarBotonReporte) {
		this.mostrarBotonReporte = mostrarBotonReporte;
	}

	/**
	 * @return the reporteCOBCargarConsultorasDeudaCampanaAction
	 */
	public ReporteCOBCargarConsultorasDeudaCampanaAction getReporteCOBCargarConsultorasDeudaCampanaAction() {
		return reporteCOBCargarConsultorasDeudaCampanaAction;
	}

	/**
	 * @param reporteCOBCargarConsultorasDeudaCampanaAction the reporteCOBCargarConsultorasDeudaCampanaAction to set
	 */
	public void setReporteCOBCargarConsultorasDeudaCampanaAction(ReporteCOBCargarConsultorasDeudaCampanaAction reporteCOBCargarConsultorasDeudaCampanaAction) {
		this.reporteCOBCargarConsultorasDeudaCampanaAction = reporteCOBCargarConsultorasDeudaCampanaAction;
	}
}