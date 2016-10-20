package biz.belcorp.ssicc.web.spusicc.zon.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONCargarTerritorioUnidadGeograficaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.zon.form.ProcesoZONCargarTerritorioUnidadGeograficaForm;

@ManagedBean
@SessionScoped
public class ProcesoZONCargarTerritorioUnidadGeograficaAction extends
		BaseProcesoAbstractAction {

	private static final long serialVersionUID = 711823845708091338L;
	private List procesoZonCargarTugErrorList;
	private boolean procesoZonCargarTugViewValida;
	private String attachment = "";
	private Boolean mostrarBotonValidar;
	private Boolean mostrarBotonGuardar;
	private String msjDialog="";

	/**
	 * Carga el archivo
	 * 
	 * @param event
	 */
	public void handleFileUpload(FileUploadEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("handleFileUpload");
		}
	
		String mensaje = "";
		try {
			ProcesoZONCargarTerritorioUnidadGeograficaForm f = (ProcesoZONCargarTerritorioUnidadGeograficaForm) formProceso;
			if (event != null) {
				f.setArchivo(event.getFile());
				setAttachment(event.getFile().getFileName());
			}
			this.cargar();
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * Carga el archivo al servidor
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void cargar() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'cargar' method");
		}

		try {
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			ProcesoZONCargarTerritorioUnidadGeograficaForm f = (ProcesoZONCargarTerritorioUnidadGeograficaForm) this.formProceso;
			ProcesoZONCargarTerritorioUnidadGeograficaService service = (ProcesoZONCargarTerritorioUnidadGeograficaService) getBean("spusicc.procesoZONCargarTerritorioUnidadGeograficaService");
			uploadArchivo();
			String extensionArchivo = obtenerExtensionArchivo(f
					.getNombreArchivo());
			f.setExtensionArchivo(extensionArchivo);
			Map criteria = new HashMap();
			criteria.put("directorioTemporal", f.getDirectorioTemporal());
			criteria.put("nombreArchivo", f.getNombreArchivo());
			criteria.put("usuario", usuario);

			int totalRegistros = service.cargarArchivoCSV(criteria);

			f.setNumRegistros(Integer.toString(totalRegistros));
			f.setNumRegistrosError("N");
			f.setNumRegistrosValidos("N");

			borrarFichero(f.getDirectorioTemporal(), f.getNombreArchivo());

			this.procesoZonCargarTugViewValida = true;
			this.mostrarPanelAdicionalProceso= true; 
			f.setFlagBotonValidar(true);
			f.setFlagBotonProcesar(false);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * Valida el archivo que se cargo
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void validar(ActionEvent evt) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'validar' method");
		}
		try {
			ProcesoZONCargarTerritorioUnidadGeograficaForm f = (ProcesoZONCargarTerritorioUnidadGeograficaForm) this.formProceso;
			ProcesoZONCargarTerritorioUnidadGeograficaService service = (ProcesoZONCargarTerritorioUnidadGeograficaService) getBean("spusicc.procesoZONCargarTerritorioUnidadGeograficaService");
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			String codigoUsuario = usuario.getLogin();

			List resultados = service
					.executeValidarCargaTerritorioUnidadGeografica(codigoUsuario);
			int totalErrores = resultados.size();
			int totalValidos = Integer.parseInt(f.getNumRegistros())- totalErrores;
			f.setNumRegistrosError(String.valueOf(totalErrores));
			f.setNumRegistrosValidos(String.valueOf(totalValidos));
			this.procesoZonCargarTugErrorList = resultados;
			f.setFlagBotonValidar(false);
			f.setFlagBotonProcesar(true);
			this.mostrarPanelAdicionalProceso= true; 
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * Hace la escritura del archivo que se carga al servidor
	 * 
	 * @param form
	 * @throws Exception
	 */
	private void uploadArchivo() {
		try {
			ProcesoZONCargarTerritorioUnidadGeograficaForm f = (ProcesoZONCargarTerritorioUnidadGeograficaForm) this.formProceso;
			UploadedFile archivo = f.getArchivo();
			f.setNombreArchivo(archivo.getFileName());
			log.debug((new StringBuilder()).append("Nombre Archivo Upload: ")
					.append(f.getNombreArchivo()).toString());
			InputStream is = archivo.getInputstream();
			FileOutputStream os = new FileOutputStream(new File(
					f.getDirectorioTemporal(), f.getNombreArchivo()));
			int bytesRead = 0;
			byte buffer[] = new byte[1024];
			while ((bytesRead = is.read(buffer, 0, 1024)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();
			f.setArchivo(null);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * 
	 * @param nombreArchivo
	 * @return
	 * @throws Exception
	 */
	private String obtenerExtensionArchivo(String nombreArchivo) throws Exception {
		return nombreArchivo.substring(nombreArchivo.length() - 3);
	}

	/**
	 * 
	 * @param path
	 * @param nombreArchivo
	 */
	private void borrarFichero(String path, String nombreArchivo) {
		try {
			File file = new File(path, nombreArchivo);
			file.delete();
			log.debug("Se elimin� el archivo");
		} catch (Exception ex) {
			log.debug((new StringBuilder())
					.append("No se pudo eliminar el archivo ")
					.append(ex.getMessage()).toString());
		}
	}

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoZONCargarTerritorioUnidadGeograficaForm form = new ProcesoZONCargarTerritorioUnidadGeograficaForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		ProcesoZONCargarTerritorioUnidadGeograficaForm f = (ProcesoZONCargarTerritorioUnidadGeograficaForm) this.formProceso;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		ProcesoZONCargarTerritorioUnidadGeograficaService service = (ProcesoZONCargarTerritorioUnidadGeograficaService) getBean("spusicc.procesoZONCargarTerritorioUnidadGeograficaService");
		service.executeProcesarCargaTerritorioUnidadGeografica(usuario
				.getLogin());
		f.setFlagBotonValidar(true);
		f.setFlagBotonProcesar(false);
		this.attachment = "";
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ProcesoZONCargarTerritorioUnidadGeograficaForm f = (ProcesoZONCargarTerritorioUnidadGeograficaForm) this.formProceso;
		ProcesoZONCargarTerritorioUnidadGeograficaService service = (ProcesoZONCargarTerritorioUnidadGeograficaService) getBean("spusicc.procesoZONCargarTerritorioUnidadGeograficaService");
		f.setDirectorioTemporal(service.obtenerPathUpload(pais.getCodigo()));
		f.setFlagBotonValidar(false);
		f.setFlagBotonProcesar(false);	
		this.procesoZonCargarTugViewValida = false;
		this.procesoZonCargarTugErrorList = new ArrayList();
		this.mostrarBotonExecute = false;
		this.mostrarPanelAdicionalProceso= false; 
		this.attachment = "";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {
		String msj = null;
		
		ProcesoZONCargarTerritorioUnidadGeograficaForm f = (ProcesoZONCargarTerritorioUnidadGeograficaForm) this.formProceso;
		if(Integer.parseInt(f.getNumRegistrosError())>0){
			this.msjDialog = this.getResourceMessage("procesoZONCargarTerritorioUnidadGeograficaForm.process.errores");
			
		}
		else{
			this.msjDialog = this.getResourceMessage("procesoZONCargarTerritorioUnidadGeograficaForm.process.valido");
		}
		
		return msj;
	}

	/**
	 * @return the procesoZonCargarTugErrorList
	 */
	public List getProcesoZonCargarTugErrorList() {
		return procesoZonCargarTugErrorList;
	}

	/**
	 * @param procesoZonCargarTugErrorList
	 *            the procesoZonCargarTugErrorList to set
	 */
	public void setProcesoZonCargarTugErrorList(
			List procesoZonCargarTugErrorList) {
		this.procesoZonCargarTugErrorList = procesoZonCargarTugErrorList;
	}

	/**
	 * @return the attachment
	 */
	public String getAttachment() {
		return attachment;
	}

	/**
	 * @param attachment
	 *            the attachment to set
	 */
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	/**
	 * @return the mostrarBotonValidar
	 */
	public Boolean getMostrarBotonValidar() {
		return mostrarBotonValidar;
	}

	/**
	 * @param mostrarBotonValidar
	 *            the mostrarBotonValidar to set
	 */
	public void setMostrarBotonValidar(Boolean mostrarBotonValidar) {
		this.mostrarBotonValidar = mostrarBotonValidar;
	}

	/**
	 * @return the mostrarBotonGuardar
	 */
	public Boolean getMostrarBotonGuardar() {
		return mostrarBotonGuardar;
	}

	/**
	 * @param mostrarBotonGuardar
	 *            the mostrarBotonGuardar to set
	 */
	public void setMostrarBotonGuardar(Boolean mostrarBotonGuardar) {
		this.mostrarBotonGuardar = mostrarBotonGuardar;
	}

	/**
	 * @return the msjDialog
	 */
	public String getMsjDialog() {
		return msjDialog;
	}

	/**
	 * @param msjDialog the msjDialog to set
	 */
	public void setMsjDialog(String msjDialog) {
		this.msjDialog = msjDialog;
	}

	/**
	 * @return the procesoZonCargarTugViewValida
	 */
	public boolean isProcesoZonCargarTugViewValida() {
		return procesoZonCargarTugViewValida;
	}

	/**
	 * @param procesoZonCargarTugViewValida the procesoZonCargarTugViewValida to set
	 */
	public void setProcesoZonCargarTugViewValida(
			boolean procesoZonCargarTugViewValida) {
		this.procesoZonCargarTugViewValida = procesoZonCargarTugViewValida;
	}
	
}