/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.cobranzas.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ProcesoCOBCargaMasivaGestionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.ProcesoCOBCargaMasivaGestionesForm;

/**
 * @author Sigcomt
 *
 */
@ManagedBean
@SessionScoped
public class ProcesoCOBCargaMasivaGestionesAction extends BaseProcesoAbstractAction{
	
	private static final long serialVersionUID = 2247080320879993677L;
	private String attachment = null;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoCOBCargaMasivaGestionesForm form = new ProcesoCOBCargaMasivaGestionesForm();
		return form;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("JFA: Entering 'view' method");
		}	
			
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();	
		ProcesoCOBCargaMasivaGestionesService service = (ProcesoCOBCargaMasivaGestionesService) getBean("spusicc.procesoCOBCargaMasivaGestionesService");
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("indicadorCobranzaExterna", Constants.SI);
				
		ProcesoCOBCargaMasivaGestionesForm f = (ProcesoCOBCargaMasivaGestionesForm)this.formProceso;
		f.setFlagUpload(true);
		f.setFlagValidar(false);		
		f.setFlagProcesar(true);//*********************** es para el boton procesar, estoy viendo para trabajar con el boton de la plantilla o adicional
		f.setFlagMostrarErrores(false);
		this.mostrarPanelAdicionalProceso = false;
		this.mostrarBotonExecute = false;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'procesar' method");
		}		
		ProcesoCOBCargaMasivaGestionesService service = (ProcesoCOBCargaMasivaGestionesService) getBean("spusicc.procesoCOBCargaMasivaGestionesService");
		ProcesoCOBCargaMasivaGestionesForm f = (ProcesoCOBCargaMasivaGestionesForm) this.formProceso;       
        
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		log.debug("usuario"+usuario.getLogin());
		
		Map datos = new HashMap();        
        datos.put("codigoPais",f.getCodigoPais());         
        datos.put("codigoUsuario",usuario.getLogin());        
        datos.put("usuario", usuario); 
        service.executeProcesarCargaMasivaGestiones(datos);		
		
		return params;
	}

	
	public void upload(FileUploadEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'upload' method");
		}
		
		try {			

			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			ProcesoCOBCargaMasivaGestionesForm f = (ProcesoCOBCargaMasivaGestionesForm) this.formProceso; 
			
			
			Map criteria = new HashMap();
			criteria.put("codigoPais", pais.getCodigo());		
			
			ProcesoCOBCargaMasivaGestionesService service = (ProcesoCOBCargaMasivaGestionesService) getBean("spusicc.procesoCOBCargaMasivaGestionesService");
			f.setDirectorioTemporal(service.obtenerPathUpload(criteria));
			//Obtenemos el archivo y nombre
			f.setArchivo(event.getFile());
			f.setNombreArchivo(event.getFile().getFileName());
			this.attachment = f.getNombreArchivo();
			//Cargamos el archivo de la maquina del cliente al servidor
			if (log.isDebugEnabled()) {
				log.debug("JFA Cargando Archivo");
			}
			this.uploadArchivo();
			
			String extensionArchivo = obtenerExtensionArchivo(f.getNombreArchivo());
			f.setExtensionArchivo(extensionArchivo);
	    	f.setFlagValidar(true);
			f.setFlagUpload(false);				
							
			if (log.isDebugEnabled()) {
				log.debug("JFA find Forward view");
			}
			
			//return mapping.findForward("view");
			this.mostrarBotonExecute = false;
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		
	}
	
	private void uploadArchivo() throws Exception {
		

			ProcesoCOBCargaMasivaGestionesForm f = (ProcesoCOBCargaMasivaGestionesForm) this.formProceso;

			// recuperamos el fichero
			UploadedFile archivo = f.getArchivo();

			f.setNombreArchivo(archivo.getFileName());

			// leyemos el stream de entrada
			InputStream is = archivo.getInputstream();

			// abrimos el stream de escritura, ubicacion al cual se grabara el
			// archivo del cliente		
			OutputStream os = new FileOutputStream(new File(f.getDirectorioTemporal(), f.getNombreArchivo()));

			// grabamos cada 1024 bytes
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while ((bytesRead = is.read(buffer, 0, 1024)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();

			f.setArchivo(null);

	}
	
	private String obtenerExtensionArchivo(String nombreArchivo) throws Exception {
		return nombreArchivo.substring(nombreArchivo.length() - 3);
	}
	
	public void validar(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'validar' method");
		}			
		
		ProcesoCOBCargaMasivaGestionesForm f = (ProcesoCOBCargaMasivaGestionesForm) this.formProceso; 
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String mensaje = null;	
		
		try {


			File file = new File(f.getDirectorioTemporal(), f.getNombreArchivo());
			
			if (file.exists()){
				Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();	
				
				Map datos = new HashMap();
				datos.put("directorioTemporal", f.getDirectorioTemporal());
				datos.put("nombreArchivo", f.getNombreArchivo());
				datos.put("extensionArchivo", f.getExtensionArchivo());
				datos.put("codigoPais", pais.getCodigo());		
				datos.put("usuario", usuario);	
				datos.put("codigoUsuario", usuario.getLogin());			
				
			    ProcesoCOBCargaMasivaGestionesService service = (ProcesoCOBCargaMasivaGestionesService) getBean("spusicc.procesoCOBCargaMasivaGestionesService");
				service.executeValidarCargaMasivaGestiones(datos);
								
				borrarFichero(f.getDirectorioTemporal(), f.getNombreArchivo());
				String registroOK = (String) datos.get("registroOK");	
				
				//Devuelve numero de registros cargados a procesar
				Integer registrosCargados=(Integer) datos.get("cantidadRegistrosCargados");
				f.setNumRegistros(registrosCargados.toString());				
				
				f.setFlagValidar(false);
				f.setFlagProcesar(false);
				this.mostrarBotonExecute = false;
				this.mostrarPanelAdicionalProceso = false;
				
				if (Constants.SI.equals(registroOK)){
					f.setFlagProcesar(true);
					this.mostrarBotonExecute = true;
					this.mostrarPanelAdicionalProceso = true;
				}
				else
				{
					mensaje = MapUtils.getString(datos, "mensajeError", "");
					this.addError("Error: ", this.getResourceMessage("errors.detail", new Object[]{mensaje}));
				}
			}
							
		} catch (Exception e) {

			mensaje = e.getMessage();
			if (StringUtils.isBlank(mensaje))
				mensaje = e.getLocalizedMessage();
			this.addError("Error: ", this.getResourceMessage("errors.detail", new Object [] {mensaje}));
		}
		
		//return mapping.findForward("view");
	}
	
	
	private void borrarFichero(String path, String nombreArchivo) {
		try {
			File file = new File(path, nombreArchivo);
			file.delete();
			log.debug("Se elimino el archivo");
		}	
		catch(Exception ex) {
			log.debug("No se pudo eliminar el archivo");
		}
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
