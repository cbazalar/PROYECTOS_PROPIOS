/*
 * 
 */
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
import org.apache.struts.action.ActionMessages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOAprobacionMasivaBuroCreditoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.sto.form.ProcesoSTOAprobacionMasivaBuroCreditoForm;

/**
 * The Class ProcesoSTOAprobacionMasivaBuroCreditoAction.
 *
 * @author Belcorp
 * @version 1.0
 * 11/02/2015
 */
@ManagedBean
@SessionScoped
public class ProcesoSTOAprobacionMasivaBuroCreditoAction extends BaseProcesoAbstractAction {

	private static final long serialVersionUID = 1L;
	private List stoNumeroDocumentoArchivoList = new ArrayList();
	private List stoNumeroDocumentoInvalidoArchivoList = new ArrayList();
	private String attachment = "";

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("devuelveFormProceso");
		}
		ProcesoSTOAprobacionMasivaBuroCreditoForm f = new ProcesoSTOAprobacionMasivaBuroCreditoForm();
		return f;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		if(log.isDebugEnabled()){
			log.debug("executeProcess");
		}
		try {
			
			
			ProcesoSTOAprobacionMasivaBuroCreditoService service = (ProcesoSTOAprobacionMasivaBuroCreditoService) getBean("spusicc.procesoSTOAprobacionMasivaBuroCreditoService");
			ProcesoSTOAprobacionMasivaBuroCreditoForm f = (ProcesoSTOAprobacionMasivaBuroCreditoForm)formProceso;
			
	        List listaNumeroDocumento = this.getStoNumeroDocumentoArchivoList();
	        for(int i =0; i<listaNumeroDocumento.size(); i++){
	        	String numeroDocIdentidad = (String)listaNumeroDocumento.get(i);
	        	Map criteria = new HashMap();
	        	criteria.put("numeroDocIdentidad", numeroDocIdentidad);
				service.updateNumeroDocumento(criteria);
	        }
	        
	        f.setFlagProcesar(false);
		}
		catch (Exception e) {
			ActionMessages messages = new ActionMessages();
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) 
				error = e.getLocalizedMessage();
			this.addError("Error:", this.getResourceMessage("errors.detail",new Object[]{error}));
		}
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		ProcesoSTOAprobacionMasivaBuroCreditoForm f = (ProcesoSTOAprobacionMasivaBuroCreditoForm)formProceso;
		f.setFlagProcesar(false);
	}
	
	public void carga(FileUploadEvent ev){
		if(log.isDebugEnabled()){
			log.debug("carga");
		}
		ProcesoSTOAprobacionMasivaBuroCreditoForm f = (ProcesoSTOAprobacionMasivaBuroCreditoForm)formProceso;
		
			try {
				
				f.setNumeroDocIdentidad(ev.getFile());
				this.setAttachment(ev.getFile().getFileName());
				ProcesoSTOAprobacionMasivaBuroCreditoService service = (ProcesoSTOAprobacionMasivaBuroCreditoService) getBean("spusicc.procesoSTOAprobacionMasivaBuroCreditoService");
				
				List listaNumeroDocumento = new ArrayList();
				List listaNumeroDocumentoInvalido = new ArrayList();
				
				UploadedFile archivo = f.getNumeroDocIdentidad();
				
				InputStream is = archivo.getInputstream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String linea;
				int cont = 0;
				while(true) {
					linea = br.readLine();
					if (linea == null)
						break;
					
					if(StringUtils.isNotBlank(linea.trim()) && StringUtils.isNumeric(linea.trim())){
						Integer num = service.getValidaNumeroDocumento(linea.trim());
						if(num.intValue() > 0)
							listaNumeroDocumento.add(linea.trim());
						else{
							this.addError("Error:",this.getResourceMessage("procesoSTOAprobacionMasivaBuroCreditoForm.errors.datos.numeroDocumento.no.valido",new Object[]{linea.trim()}));
							listaNumeroDocumentoInvalido.add(linea.trim());
						}
					}
					else
					{
						cont++;
					}
				}
				
				if(cont > 0){
					this.addError("Error:",this.getResourceMessage("procesoSTOAprobacionMasivaBuroCreditoForm.errors.datos.no.numericos",new Object[]{Integer.toString(cont)}));
				}
				
				f.setFlagProcesar(true);
				
				this.stoNumeroDocumentoArchivoList = listaNumeroDocumento;
				this.stoNumeroDocumentoInvalidoArchivoList = listaNumeroDocumentoInvalido;
			}
			catch (Exception e) {
				String error = e.getMessage();
				if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
				
				this.addError("Error:", this.getResourceMessage("errors.detail",new Object[]{error}));
				
			}
	}

	public List getStoNumeroDocumentoArchivoList() {
		return stoNumeroDocumentoArchivoList;
	}

	public void setStoNumeroDocumentoArchivoList(List stoNumeroDocumentoArchivoList) {
		this.stoNumeroDocumentoArchivoList = stoNumeroDocumentoArchivoList;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public List getStoNumeroDocumentoInvalidoArchivoList() {
		return stoNumeroDocumentoInvalidoArchivoList;
	}

	public void setStoNumeroDocumentoInvalidoArchivoList(
			List stoNumeroDocumentoInvalidoArchivoList) {
		this.stoNumeroDocumentoInvalidoArchivoList = stoNumeroDocumentoInvalidoArchivoList;
	}
	
 }