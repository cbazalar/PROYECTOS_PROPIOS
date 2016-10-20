/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.percepciones.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ConsultaCCCGenericoService;
import biz.belcorp.ssicc.service.spusicc.percepciones.ProcesoPERCargarPagosBancariosMasivosService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.percepciones.form.ProcesoPERCargarPagosBancariosMasivosForm;

/**
 * @author Sigcomt
 *
 */
@ManagedBean
@SessionScoped
public class ProcesoPERCargarPagosBancariosMasivosAction extends BaseProcesoAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2324694035912730845L;
	
	private List siccSociedadList = new ArrayList();
	private List siccCuentaCorrienteList = new ArrayList();
	private List cccCargaMasivaErroresList = new ArrayList();
	private String attachment = null;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoPERCargarPagosBancariosMasivosForm form = new ProcesoPERCargarPagosBancariosMasivosForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("JFA: Entering 'view' method");
		}
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();			
		this.mostrarBotonExecute = false;
		this.mostrarPanelAdicionalProceso = false;
		//Map para almacenar los parametros
		Map criteria = new HashMap();
		
		//Obteniendo el listado de Sociedades por Pais
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		this.siccSociedadList = service.getSociedadesByCodigoPais(pais.getCodigo());
		//Obteniedo el listado de las Cuentas Corrientes Bancarias	        		
		criteria.put("codigoPais", pais.getCodigo());
        ConsultaCCCGenericoService serviceCCC = (ConsultaCCCGenericoService) getBean("spusicc.consultaCCCGenericoService");	   
        this.siccCuentaCorrienteList = serviceCCC.getCuentasCorrientesBancariasList(criteria);
		 
				
		ProcesoPERCargarPagosBancariosMasivosForm f = (ProcesoPERCargarPagosBancariosMasivosForm)this.formProceso;		
		f.setFlagUpload(true);
		f.setFlagValidar(false);		
		f.setFlagProcesar(false);
		f.setFlagMostrarErrores(false);
								
		if (log.isDebugEnabled()) {
			log.debug("JFA: Finalizando 'view' method");
		}	
		
		//return mapping.findForward("view");
	}

	public void upload(FileUploadEvent event){
		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'upload' method");
		}
		
		try {
			
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();					
			ProcesoPERCargarPagosBancariosMasivosForm f = (ProcesoPERCargarPagosBancariosMasivosForm) this.formProceso; 
			ProcesoPERCargarPagosBancariosMasivosService service = (ProcesoPERCargarPagosBancariosMasivosService) getBean("spusicc.procesoPERCargarPagosBancariosMasivosService");
			
			Map criteria = new HashMap();
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("codigoSociedad",f.getCodigoSociedad());
			
			f.setDirectorioTemporal(service.obtenerPathUpload(criteria));
			//Obtenemos archivo y nombre de Archivo
			f.setArchivo(event.getFile());
			f.setNombreArchivo(event.getFile().getFileName());
			//Cargamos el archivo de la maquina del cliente al servidor
			uploadArchivo();
			
			String extensionArchivo = obtenerExtensionArchivo(f.getNombreArchivo());
			f.setExtensionArchivo(extensionArchivo);
					
			//Borramos las tablas Temporales de Cargos y Abonos Masivos
		   //	service.deleteTablasPagosBancariosMasivos();
			this.mostrarPanelAdicionalProceso = false;
			this.cccCargaMasivaErroresList = null;
			f.setFlagValidar(true);
			f.setFlagUpload(false);		
			f.setFlagProcesar(false);
			
			if (log.isDebugEnabled()) {
				log.debug("JFA Finalizando 'upload' method");
			}
			
			//return mapping.findForward("view");
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	private void uploadArchivo() {
		
		if (log.isDebugEnabled()) {
			log.debug("JFA Cargando Archivo");
		}
		
		try {
			ProcesoPERCargarPagosBancariosMasivosForm f = (ProcesoPERCargarPagosBancariosMasivosForm) this.formProceso;

			// recuperamos el fichero
			UploadedFile archivo = f.getArchivo();

			f.setNombreArchivo(archivo.getFileName());
			this.attachment = f.getNombreArchivo();
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
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
				
		
	}
	
	private String obtenerExtensionArchivo(String nombreArchivo)throws Exception {
		return nombreArchivo.substring(nombreArchivo.length() - 3);
	}
	
	public void validar(ActionEvent event){
		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'validar' method");
		}
		ProcesoPERCargarPagosBancariosMasivosForm f = (ProcesoPERCargarPagosBancariosMasivosForm) this.formProceso;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String mensaje = null;			
		try {
			File file = new File(f.getDirectorioTemporal(), f.getNombreArchivo());
			if (file.exists()){
				
				Map datos = new HashMap();
				datos.put("directorioTemporal", f.getDirectorioTemporal());
				datos.put("nombreArchivo", f.getNombreArchivo());
				datos.put("extensionArchivo", f.getExtensionArchivo());
				datos.put("codigoPais", pais.getCodigo());
											
				log.debug("datos__"+datos.toString());
		
				ProcesoPERCargarPagosBancariosMasivosService service = (ProcesoPERCargarPagosBancariosMasivosService) getBean("spusicc.procesoPERCargarPagosBancariosMasivosService");
				
				if (log.isDebugEnabled()) {
					log.debug("JFA Llamando deleteTablasCargaPagosBancariosMasivos");
				}
				service.deleteTablasCargaPagosBancariosMasivos();
								
				if (log.isDebugEnabled()) {
					log.debug("JFA Llamando executeValidarPagosBancariosMasivos");
				}
				service.executeValidarPagosBancariosMasivos(datos);
												
				borrarFichero(f.getDirectorioTemporal(), f.getNombreArchivo());
				
				String error = (String) datos.get("error");				
				
				if (log.isDebugEnabled()) {
					log.debug("JFA Validando Errores");
				}
				
				if (error.equals("0")){
					mensaje = "procesoPERCargarPagosBancariosMasivosUpload.msg.CargaDatos.ok";
					f.setFlagValidar(false);
					f.setFlagProcesar(true);
					this.mostrarPanelAdicionalProceso = false;
					this.cccCargaMasivaErroresList = null;	
									
				}
				else{		
					f.setFlagMostrarErrores(true);
					List erroresList = service.getErroresCargaPagosBancariosMasivos();
					this.cccCargaMasivaErroresList = erroresList;	
					this.mostrarPanelAdicionalProceso = true;
					mensaje = "procesoPERCargarPagosBancariosMasivosUpload.msg.CargaDatos.error";
				}	
				this.addInfo("Info: ", this.getResourceMessage(mensaje));
		}	
			
		} catch (Exception e) {
			mensaje = e.getMessage();
			if (StringUtils.isBlank(mensaje))
				mensaje = e.getLocalizedMessage();
			this.addError("", this.getResourceMessage("errors.detail", new Object[]{mensaje}));
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
	
	public void procesar(ActionEvent event){
		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'procesar' method");
		}
		try {
			ProcesoPERCargarPagosBancariosMasivosService service = (ProcesoPERCargarPagosBancariosMasivosService) getBean("spusicc.procesoPERCargarPagosBancariosMasivosService");
			ProcesoPERCargarPagosBancariosMasivosForm f = (ProcesoPERCargarPagosBancariosMasivosForm) this.formProceso;	       
	        
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			log.debug("usuario"+usuario.getLogin());
			
			Map datos = new HashMap();
	        datos.put("codigoUsuario",usuario.getLogin());
	        datos.put("codigoPais",f.getCodigoPais());
	        datos.put("codigoSociedad",f.getCodigoSociedad());        
	        datos.put("codigoCuentaCorrienteBancaria",f.getCodigoCuentaCorrienteBancaria());        
	        
	        if (log.isDebugEnabled()) {
				log.debug("JFA Parameter Map : " + datos.toString());
			}
	        
			service.executeProcesarPagosBancariosMasivos(datos);
			String mensaje = null;
			mensaje = "procesoPERCargarPagosBancariosMasivosUpload.msg.procesa.ok";
			this.addInfo("Info: ", this.getResourceMessage(mensaje));
			
	        if (log.isDebugEnabled()) {
				log.debug("JFA Finalizando 'procesar' method");
			}
	        
			//return mapping.findForward("view");
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
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
	 * @return the siccCuentaCorrienteList
	 */
	public List getSiccCuentaCorrienteList() {
		return siccCuentaCorrienteList;
	}

	/**
	 * @param siccCuentaCorrienteList the siccCuentaCorrienteList to set
	 */
	public void setSiccCuentaCorrienteList(List siccCuentaCorrienteList) {
		this.siccCuentaCorrienteList = siccCuentaCorrienteList;
	}

	/**
	 * @return the cccCargaMasivaErroresList
	 */
	public List getCccCargaMasivaErroresList() {
		return cccCargaMasivaErroresList;
	}

	/**
	 * @param cccCargaMasivaErroresList the cccCargaMasivaErroresList to set
	 */
	public void setCccCargaMasivaErroresList(List cccCargaMasivaErroresList) {
		this.cccCargaMasivaErroresList = cccCargaMasivaErroresList;
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
