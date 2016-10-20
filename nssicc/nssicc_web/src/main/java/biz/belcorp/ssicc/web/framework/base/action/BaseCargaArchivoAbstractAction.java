package biz.belcorp.ssicc.web.framework.base.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.util.FileUtil;
import biz.belcorp.ssicc.web.framework.base.form.BaseCargaArchivoForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;


public abstract class BaseCargaArchivoAbstractAction extends MBaseSistemaAbstractJSF{

	private static final long serialVersionUID = 1L;
	
	protected BaseCargaArchivoForm formCargaArchivo;
	
	
	protected boolean mostrarArchivoCargaExcel = true;
	protected boolean mostrarArchivoCargaTXT = false;
	protected boolean mostrarArchivoCargaCSV = false;
	
	protected boolean mostrarBotonArchivoCarga = true;
	protected boolean mostrarBotonValidar = false;
	protected boolean mostrarBotonProcesar = false;
	protected boolean mostrarListaCarga = true;
	protected boolean errorValidar = false;
	
	protected UploadedFile file;	//objeto que se utilizara para el upload del Archivo
	protected String attachment;
	
	
	protected List listaCarga; 
	protected DataTableModel datatableCarga; //Atributo DataTableModel usado en el Datatable la cual contiene la lista guardada en listaCarga
	
	
	/**
	 * Aqui se debe asociar la clase FORM al Manage Beans, la cual internamente se asociara al
	 * atributo formBusqueda
	 * @return
	 * @throws Exception
	 */
	protected abstract BaseCargaArchivoForm devuelveFormCarga() throws Exception;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setBeforeInitAtributes()
	 */
	@Override
	protected final void setBeforeViewAtributes() throws Exception {
		
		super.setBeforeViewAtributes();
		log.debug("Entering 'setBeforeViewAtributes' ");
		this.accion = this.ACCION_BUSCAR;
		this.mPantallaPrincipalBean.setCurrentMenu(this.parametrosPantalla.get("codigoMenu"));
		this.formCargaArchivo = this.devuelveFormCarga();
		if (this.formCargaArchivo != null)
			this.formCargaArchivo.setAnyoPeriodo(this.mPantallaPrincipalBean.getAnyoActualperiodo());
		this.formCargaArchivo.setFlagUpload(false);	
		this.formCargaArchivo.setFlagValidar(false);
		this.formCargaArchivo.setFlagProcesar(false);
		
		this.mostrarListaCarga = false;
		this.mostrarBotonValidar = false;
		this.mostrarBotonProcesar = false;
		this.listaCarga = new ArrayList();
		this.datatableCarga = new DataTableModel(this.listaCarga);
	}
	
	
	
	
	/**
	 *  Metodo que carga el archivo
	 */
	public void upload(FileUploadEvent event) {
		if (log.isDebugEnabled()) {
			log.debug(" metodo upload");
		}
		this.file = event.getFile();
        if(this.file == null) {
            this.addError("Error", "Debe seleccionar primero el archivo a Cargar");
            return;
        }
        ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
        this.mostrarListaCarga = false;
		this.mostrarBotonValidar = false;
		this.mostrarBotonProcesar = false;
		this.listaCarga = new ArrayList();
		this.datatableCarga = new DataTableModel(this.listaCarga);
        try {
	        BaseCargaArchivoForm f = (BaseCargaArchivoForm) this.formCargaArchivo;
	        f.setNombreArchivo("");
	        
			Map<String, Object> criteria = new HashMap<String, Object>();
			criteria.put("codigoPais", this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());		
			
			f = this.setUploadAttibuttes(criteria);
			String directorio = f.getDirectorioTemporal();
			if (StringUtils.isBlank(directorio)) {
				Map paramReporteGeneral = reporteService.getParametrosReporteGeneral(criteria);
				String directorioRepositorio = (String) paramReporteGeneral.get("directorioRepositorio"); 
				f.setDirectorioTemporal(directorioRepositorio);
			}
			
			//Cargamos el archivo de la maquina del cliente al servidor
			this.uploadArchivo(this.file.getFileName(), this.file.getInputstream());
			String extensionArchivo = obtenerExtensionArchivo(f.getNombreArchivo());
			f.setExtensionArchivo(extensionArchivo);
	    	f.setFlagValidar(true);
			f.setFlagUpload(false);	
			f.setFlagProcesar(false);
			this.mostrarBotonValidar = true;
			this.addInfo("Información: ", "Archivo cargado correctamente en el Servidor");
        }
        catch (Exception e) {
        	this.addError("Error: ", this.obtieneMensajeErrorException(e));
        }
		if (log.isDebugEnabled()) {
			log.debug("Fin metodo upload");
		}
        
        
    }
	
	
	/**
	 * @param event
	 */
	public abstract BaseCargaArchivoForm setUploadAttibuttes(Map<String, Object> criteria) throws Exception;
	
	
	/**
	 * Metodo que guarda el archivo en el servidor
	 * @param fileName
	 * @param in
	 * @throws Exception
	 */
	protected final void uploadArchivo(String fileName, InputStream in) throws Exception {
		BaseCargaArchivoForm f = (BaseCargaArchivoForm) this.formCargaArchivo;
		f.setNombreArchivo(this.file.getFileName());
		
	    // Escribe el contenido de un archivo de entrada a un FileOutputStream de salida
	    OutputStream out = new FileOutputStream(new File(FileUtil.formatDirectory(f.getDirectorioTemporal()) + fileName));
	  
	    int read = 0;
	    byte[] bytes = new byte[1024];
	  
	    while ((read = in.read(bytes)) != -1) {
	    	out.write(bytes, 0, read);
	    }
	  
	    in.close();
	    out.flush();
	    out.close();   
	}
	
	
	
	/**
	 * @param actionEvent
	 * @throws Exception
	 */
	public void validar(ActionEvent actionEvent)  {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'validar' method");
		}			
		
		
		BaseCargaArchivoForm f = (BaseCargaArchivoForm) this.formCargaArchivo;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		this.mostrarListaCarga = false;
		this.mostrarBotonValidar = true;
		this.mostrarBotonProcesar = false;
		this.errorValidar = false;
		this.listaCarga = new ArrayList();
		this.datatableCarga = new DataTableModel(this.listaCarga);
		
		try {

			File file = new File(f.getDirectorioTemporal(), f.getNombreArchivo());
			
			if (file.exists()){
								
				Map<String, Object> datos = new HashMap<String, Object>();
				datos.put("directorioTemporal", f.getDirectorioTemporal());
				datos.put("nombreArchivo", f.getNombreArchivo());
				datos.put("extensionArchivo", f.getExtensionArchivo());
				datos.put("codigoPais", pais.getCodigo());		
				datos.put("usuario", usuario);	
				datos.put("codigoUsuario", usuario.getLogin());			
				datos.put("registroOK", Constants.NO);
				
				this.listaCarga = this.setValidarAttributes(datos);
				this.datatableCarga = new DataTableModel(this.listaCarga);
			    				
				borrarFichero(f.getDirectorioTemporal(), f.getNombreArchivo());
				this.file = null;
				
				//String registroOK = (String) datos.get("registroOK");				
				
				f.setFlagValidar(false);
				f.setFlagProcesar(false);
				f.setFlagProcesar(true);
				
				if (this.errorValidar) {
					this.mostrarBotonProcesar = false;
					this.mostrarBotonValidar = true;
				}
				else {
					this.mostrarBotonProcesar = true;
					this.mostrarBotonValidar = false;
				}
				this.setAfterValidarAttributes(datos);	
			}
			else {
				this.addError("Error: ", "Archivo no se encuentra en el Servidor");
				this.errorValidar = true;
				this.mostrarListaCarga = false;
				this.mostrarBotonValidar = true;
				this.mostrarBotonProcesar = false;
				return;
			}
		
		}
		catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			this.errorValidar = true;
			this.mostrarListaCarga = false;
			this.mostrarBotonValidar = true;
			this.mostrarBotonProcesar = false;
		}
		return;
	}
	
	
	/**
	 * @param event
	 */
	protected abstract List setValidarAttributes(Map<String, Object> datos) throws Exception;
	
	/**
	 * @param datos
	 * @throws Exception
	 */
	protected void setAfterValidarAttributes(Map<String, Object> datos) throws Exception {
		return;
	}
	
	/**
	 * Metodo que borra el archivo
	 * @param path
	 * @param nombreArchivo
	 */
	protected void borrarFichero(String path, String nombreArchivo) throws Exception {
		File file = new File(path, nombreArchivo);
		file.delete();
		log.debug("Se elimino el archivo");
	}
	
	/**
	 * Metodo que obtiene la extension del archivo
	 * @param nombreArchivo
	 * @return
	 * @throws Exception
	 */
	protected String obtenerExtensionArchivo(String nombreArchivo) throws Exception {
		String resultado[] = nombreArchivo.split("\\.(?=[^\\.]+$)");
		return resultado[1];
	}
	
	/**
	 * @return
	 */
	public String obtenerPathPersonalizado(){
		return FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources");
	}
	
	
	/**
	 * @param actionEvent
	 * @throws Exception
	 */
	public void procesar(ActionEvent actionEvent)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'procesar' method");
		}			
		
		
		BaseCargaArchivoForm f = (BaseCargaArchivoForm) this.formCargaArchivo;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		log.debug("usuario"+usuario.getLogin());
		this.mostrarBotonValidar = false;
		this.mostrarBotonProcesar = true;	
		
		try {

			Map<String, Object> datos = new HashMap<String, Object>();	
			datos.put("codigoPais", pais.getCodigo());         
	        datos.put("codigoUsuario",usuario.getLogin());        
	        datos.put("usuario", usuario);
	        
			this.setProcesarAttributes(datos);
			String mensaje = this.getMensajeProcesarOK();
			if (StringUtils.isNotBlank(mensaje)) {
				this.addInfo("Información: ", mensaje);
			}
			else 
				this.addInfo("Información: ", "Proceso Concluido");
			this.mostrarBotonValidar = false;
			this.mostrarBotonProcesar = false;	
		
		}
		catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			this.mostrarBotonValidar = false;
			this.mostrarBotonProcesar = true;	
		}
		return;
	}
	
	
	protected abstract String getMensajeProcesarOK();
	
	
	/**
	 * Devuelve mensaje de confirmacion del Proceso si termino OK
	 * @param event
	 */
	protected abstract void setProcesarAttributes(Map<String, Object> datos) throws Exception;
	
	
	
	
	
	/* GET - SET ATRIBUTOS */	
	/**
	 * Gets the form carga archivo.
	 *
	 * @return the form carga archivo
	 */
	public BaseCargaArchivoForm getFormCargaArchivo() {
		return formCargaArchivo;
	}

	/**
	 * Sets the form carga archivo.
	 *
	 * @param formCargaArchivo the new form carga archivo
	 */
	public void setFormCargaArchivo(BaseCargaArchivoForm formCargaArchivo) {
		this.formCargaArchivo = formCargaArchivo;
	}


	/**
	 * @return the mostrarBotonValidar
	 */
	public boolean isMostrarBotonValidar() {
		return mostrarBotonValidar;
	}


	/**
	 * @param mostrarBotonValidar the mostrarBotonValidar to set
	 */
	public void setMostrarBotonValidar(boolean mostrarBotonValidar) {
		this.mostrarBotonValidar = mostrarBotonValidar;
	}


	/**
	 * @return the mostrarBotonProcesar
	 */
	public boolean isMostrarBotonProcesar() {
		return mostrarBotonProcesar;
	}


	/**
	 * @param mostrarBotonProcesar the mostrarBotonProcesar to set
	 */
	public void setMostrarBotonProcesar(boolean mostrarBotonProcesar) {
		this.mostrarBotonProcesar = mostrarBotonProcesar;
	}


	/**
	 * @return the listaCarga
	 */
	public List getListaCarga() {
		return listaCarga;
	}


	/**
	 * @param listaCarga the listaCarga to set
	 */
	public void setListaCarga(List listaCarga) {
		this.listaCarga = listaCarga;
	}


	/**
	 * @return the datatableCarga
	 */
	public DataTableModel getDatatableCarga() {
		return datatableCarga;
	}


	/**
	 * @param datatableCarga the datatableCarga to set
	 */
	public void setDatatableCarga(DataTableModel datatableCarga) {
		this.datatableCarga = datatableCarga;
	}


	/**
	 * @return the mostrarArchivoCargaExcel
	 */
	public boolean isMostrarArchivoCargaExcel() {
		return mostrarArchivoCargaExcel;
	}



	/**
	 * @param mostrarArchivoCargaExcel the mostrarArchivoCargaExcel to set
	 */
	public void setMostrarArchivoCargaExcel(boolean mostrarArchivoCargaExcel) {
		this.mostrarArchivoCargaExcel = mostrarArchivoCargaExcel;
	}



	/**
	 * @return the mostrarArchivoCargaTXT
	 */
	public boolean isMostrarArchivoCargaTXT() {
		return mostrarArchivoCargaTXT;
	}



	/**
	 * @param mostrarArchivoCargaTXT the mostrarArchivoCargaTXT to set
	 */
	public void setMostrarArchivoCargaTXT(boolean mostrarArchivoCargaTXT) {
		this.mostrarArchivoCargaTXT = mostrarArchivoCargaTXT;
	}



	/**
	 * @return the mostrarArchivoCargaCSV
	 */
	public boolean isMostrarArchivoCargaCSV() {
		return mostrarArchivoCargaCSV;
	}



	/**
	 * @param mostrarArchivoCargaCSV the mostrarArchivoCargaCSV to set
	 */
	public void setMostrarArchivoCargaCSV(boolean mostrarArchivoCargaCSV) {
		this.mostrarArchivoCargaCSV = mostrarArchivoCargaCSV;
	}



	/**
	 * @return the file
	 */
	public UploadedFile getFile() {
		return file;
	}



	/**
	 * @param file the file to set
	 */
	public void setFile(UploadedFile file) {
		this.file = file;
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


	/**
	 * @return the mostrarListaCarga
	 */
	public boolean isMostrarListaCarga() {
		return mostrarListaCarga;
	}


	/**
	 * @param mostrarListaCarga the mostrarListaCarga to set
	 */
	public void setMostrarListaCarga(boolean mostrarListaCarga) {
		this.mostrarListaCarga = mostrarListaCarga;
	}


	/**
	 * @return the mostrarBotonArchivoCarga
	 */
	public boolean isMostrarBotonArchivoCarga() {
		return mostrarBotonArchivoCarga;
	}


	/**
	 * @param mostrarBotonArchivoCarga the mostrarBotonArchivoCarga to set
	 */
	public void setMostrarBotonArchivoCarga(boolean mostrarBotonArchivoCarga) {
		this.mostrarBotonArchivoCarga = mostrarBotonArchivoCarga;
	}


	/**
	 * @return the errorValidar
	 */
	public boolean isErrorValidar() {
		return errorValidar;
	}


	/**
	 * @param errorValidar the errorValidar to set
	 */
	public void setErrorValidar(boolean errorValidar) {
		this.errorValidar = errorValidar;
	}
	
	
	
	
}
