package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOAprobacionMasivaBuroCreditoService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOAprobacionMasivaDataCrediticiaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.sto.form.ProcesoSTOAprobacionMasivaBuroCreditoForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.ProcesoSTOAprobacionMasivaDataCrediticiaForm;

@ManagedBean
@SessionScoped
public class ProcesoSTOAprobacionMasivaDataCrediticiaAction extends BaseProcesoAbstractAction{

	private static final long serialVersionUID = -3799927183853411340L;
	
	private List stoCredArchivoList;
	private List stoCredArchivoInvalidosList;
	private String attachment;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoSTOAprobacionMasivaDataCrediticiaForm f = new ProcesoSTOAprobacionMasivaDataCrediticiaForm();
		return f;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		ProcesoSTOAprobacionMasivaDataCrediticiaForm f = (ProcesoSTOAprobacionMasivaDataCrediticiaForm)formProceso;
		ProcesoSTOAprobacionMasivaDataCrediticiaService service = (ProcesoSTOAprobacionMasivaDataCrediticiaService) getBean("spusicc.procesoSTOAprobacionMasivaDataCrediticiaService");
		 List listacodigoConsultora= this.stoCredArchivoList;
	        for(int i =0; i<listacodigoConsultora.size(); i++){
	        	String codConsultora = (String)listacodigoConsultora.get(i);
	        	Map criteria = new HashMap();
	        	criteria.put("codigoConsultora", codConsultora);
	        	service.updateSolicCodigoConsultora(criteria);				
	        }	        
	        f.setFlagProcesar(false);
	        return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		ProcesoSTOAprobacionMasivaDataCrediticiaForm f = (ProcesoSTOAprobacionMasivaDataCrediticiaForm)formProceso;
		f.setFlagProcesar(false);
		this.stoCredArchivoInvalidosList=new ArrayList();
		this.stoCredArchivoList=new ArrayList();
		this.attachment="";
		this.mostrarBotonExecute=false;
		
	}
	
	//Carga los archivos del txt
	public void cargaData(FileUploadEvent ev){
		try {
			ProcesoSTOAprobacionMasivaDataCrediticiaForm f = (ProcesoSTOAprobacionMasivaDataCrediticiaForm)formProceso;
			ProcesoSTOAprobacionMasivaDataCrediticiaService service = (ProcesoSTOAprobacionMasivaDataCrediticiaService) getBean("spusicc.procesoSTOAprobacionMasivaDataCrediticiaService");
			f.setFileCliente(ev.getFile());
			this.attachment=ev.getFile().getFileName();	
			
			List listacodConsultora = new ArrayList();
			List listacodConsultoraInvalido = new ArrayList();
			boolean errorData=false;
			
			UploadedFile archivo = f.getFileCliente();
			InputStream is = archivo.getInputstream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String linea;
			int cont = 0;
			while(true) {
				linea = br.readLine();
				if (linea == null)
					break;
				
				if(StringUtils.isNotBlank(linea.trim()) && StringUtils.isNumeric(linea.trim())){
					Integer num = service.getValidaSolicCodigoConsultora(linea.trim());					
					if(num.intValue() > 0)
						listacodConsultora.add(linea.trim());
					else{
						this.addError("Error:",this.getResourceMessage("procesoSTOAprobacionMasivaDataCrediticiaForm.errors.codconsultora.novalido",new Object[]{linea.trim()}));
						listacodConsultoraInvalido.add(linea.trim());
						errorData=true;
					}
				}else				
					cont++;				
			}
			
			if(cont > 0){
				this.addError("Error:",this.getResourceMessage("procesoSTOAprobacionMasivaDataCrediticiaForm.errors.codconsultora.nonumericos",new Object[]{Integer.toString(cont)}));
				errorData=true;
			}
			
			if(errorData)
				this.mostrarBotonExecute=false;			
			else
				this.mostrarBotonExecute=true;
			this.stoCredArchivoList= listacodConsultora;
			this.stoCredArchivoInvalidosList = listacodConsultoraInvalido;			
			
		} catch (Exception e) {
			this.addError("Error:", this.obtieneMensajeErrorException(e));
		}	
	}

	/**
	 * @return the stoCredArchivoList
	 */
	public List getStoCredArchivoList() {
		return stoCredArchivoList;
	}

	/**
	 * @param stoCredArchivoList the stoCredArchivoList to set
	 */
	public void setStoCredArchivoList(List stoCredArchivoList) {
		this.stoCredArchivoList = stoCredArchivoList;
	}

	/**
	 * @return the stoCredArchivoInvalidosList
	 */
	public List getStoCredArchivoInvalidosList() {
		return stoCredArchivoInvalidosList;
	}

	/**
	 * @param stoCredArchivoInvalidosList the stoCredArchivoInvalidosList to set
	 */
	public void setStoCredArchivoInvalidosList(List stoCredArchivoInvalidosList) {
		this.stoCredArchivoInvalidosList = stoCredArchivoInvalidosList;
	}

	/**
	 * @return the attachment
	 */
	public String getAttachment() {
		return attachment;
	}

	/**
	 * @param attachment the attachment to set
	 */
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	
	

}
