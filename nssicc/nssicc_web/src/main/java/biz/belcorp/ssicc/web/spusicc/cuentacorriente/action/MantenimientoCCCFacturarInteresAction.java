package biz.belcorp.ssicc.web.spusicc.cuentacorriente.action;

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

import org.apache.commons.beanutils.BeanUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.MantenimientoCCCFacturarInteresService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.MantenimientoCCCFacturarInteresForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoCCCFacturarInteresAction extends
		BaseProcesoAbstractAction {

	private static final long serialVersionUID = 1L;
	private String mantenimientoCCCFacturarInteresViewValida;
	private List mantenimientoCCCFacturarInteresErrorList;
	private String indicadorGrabo;
	private String attachment = "";
	private UploadedFile archivoPadre;
	private Boolean mostrarGrilla;
	

	/**
	 * Sustituye al setview
	 */
	public void inicializarPantalla() {
		try {
			this.mostrarGrilla = false;
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			MantenimientoCCCFacturarInteresForm f = (MantenimientoCCCFacturarInteresForm) this.formProceso;

			MantenimientoCCCFacturarInteresService service = (MantenimientoCCCFacturarInteresService) getBean("spusicc.mantenimientoCCCFacturarInteresService");
			f.setCodigoPais(pais.getCodigo());
			f.setDirectorioTemporal(service.obtenerPathUpload(pais.getCodigo()));
			f.setFlagBotonValidar(false);
			f.setFlagBotonProcesar(false);
			this.mantenimientoCCCFacturarInteresViewValida = "";
			this.mantenimientoCCCFacturarInteresErrorList = new ArrayList();
			f.setArchivo(null);
			f.setNombreArchivo("");
			f.setNumRegistros("");
			f.setNumRegistrosError("");
			f.setNumRegistrosValidos("");
			this.indicadorGrabo = Constants.NUMERO_CERO;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}
	
	public void handleFileUpload(FileUploadEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("handleFileUpload");
		}
		MantenimientoCCCFacturarInteresForm f = (MantenimientoCCCFacturarInteresForm) this.formProceso;
		if (event != null) {
			f.setArchivo(event.getFile());
			this.setAttachment(event.getFile().getFileName());
			this.uploadArchivo();
			this.cargar();
		}
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void cargar() {
		try {

			if (log.isDebugEnabled()) {
				log.debug("Entering 'cargar' method");
			}
			this.mostrarGrilla = true;
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			MantenimientoCCCFacturarInteresForm f = (MantenimientoCCCFacturarInteresForm) this.formProceso;
			f.setArchivo(this.archivoPadre);
			MantenimientoCCCFacturarInteresService service = (MantenimientoCCCFacturarInteresService) getBean("spusicc.mantenimientoCCCFacturarInteresService");
			uploadArchivo();
			String extensionArchivo = obtenerExtensionArchivo(f
					.getNombreArchivo());
			f.setExtensionArchivo(extensionArchivo);
			Map criteria = new HashMap();
			criteria.put("directorioTemporal", f.getDirectorioTemporal());
			criteria.put("nombreArchivo", f.getNombreArchivo());
			criteria.put("usuario", usuario);

			int totalRegistros = service.cargarArchivoExcel(criteria);

			f.setNumRegistros(Integer.toString(totalRegistros));
			f.setNumRegistrosError("N");
			f.setNumRegistrosValidos("N");

			borrarFichero(f.getDirectorioTemporal(), f.getNombreArchivo());
			this.mantenimientoCCCFacturarInteresErrorList = new ArrayList();
			this.mantenimientoCCCFacturarInteresViewValida = Constants.NUMERO_UNO;

			f.setFlagBotonValidar(true);
			f.setFlagBotonProcesar(false);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	/**
	 * 
	 */
	public void validar() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'validar' method");
		}
		try {
			
		

		MantenimientoCCCFacturarInteresForm f = (MantenimientoCCCFacturarInteresForm) this.formProceso;
		MantenimientoCCCFacturarInteresService service = (MantenimientoCCCFacturarInteresService) getBean("spusicc.mantenimientoCCCFacturarInteresService");

		String codigoUsuario =  this.mPantallaPrincipalBean.getCurrentUser().getLogin();

		List resultados = service
				.executeValidarConsolidadoInterMora(codigoUsuario);
		int totalErrores = resultados.size();
		int totalValidos = Integer.parseInt(f.getNumRegistros()) - totalErrores;
		f.setNumRegistrosError(String.valueOf(totalErrores));
		f.setNumRegistrosValidos(String.valueOf(totalValidos));
		this.mantenimientoCCCFacturarInteresErrorList = resultados;
		f.setFlagBotonValidar(false);
		f.setFlagBotonProcesar(true);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}
	
	/**
	 * 
	 */
	public void procesarArchivo(){
		try {
			Map<String, Object> params = null;
			MantenimientoCCCFacturarInteresForm f = (MantenimientoCCCFacturarInteresForm) this.formProceso;
			params = BeanUtils.describe(f);
			this.executeProcess(params);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * 
	 * @param form
	 * @throws Exception
	 */
	private void uploadArchivo() {
		try {
			this.mostrarGrilla = true;
			MantenimientoCCCFacturarInteresForm f = (MantenimientoCCCFacturarInteresForm) this.formProceso;
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
	private String obtenerExtensionArchivo(String nombreArchivo)
			throws Exception {
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

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#devuelveFormProceso()
	 */
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		return new MantenimientoCCCFacturarInteresForm();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#executeProcess(java.util.Map)
	 */
	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		MantenimientoCCCFacturarInteresForm f = (MantenimientoCCCFacturarInteresForm) this.formProceso;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoCCCFacturarInteresService service = (MantenimientoCCCFacturarInteresService) getBean("spusicc.mantenimientoCCCFacturarInteresService");
		service.executeProcesarCargaConsolidadoInterMora(usuario.getLogin());
		f.setFlagBotonValidar(true);
		f.setFlagBotonProcesar(false);
		this.indicadorGrabo = Constants.NUMERO_UNO;
		
		String ventana = "PF('viewMantenimientoCCCFacturarInteresPopup').hide()";
		this.getRequestContext().execute(ventana);

		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarGrilla = false;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoCCCFacturarInteresForm f = (MantenimientoCCCFacturarInteresForm) this.formProceso;

		MantenimientoCCCFacturarInteresService service = (MantenimientoCCCFacturarInteresService) getBean("spusicc.mantenimientoCCCFacturarInteresService");
		f.setCodigoPais(pais.getCodigo());
		f.setDirectorioTemporal(service.obtenerPathUpload(pais.getCodigo()));
		f.setFlagBotonValidar(false);
		f.setFlagBotonProcesar(false);
		this.mantenimientoCCCFacturarInteresViewValida = "";
		this.mantenimientoCCCFacturarInteresErrorList = new ArrayList();
		f.setArchivo(null);
		f.setNombreArchivo("");
		f.setNumRegistros("");
		f.setNumRegistrosError("");
		f.setNumRegistrosValidos("");
		this.indicadorGrabo = Constants.NUMERO_CERO;
	}

	/**
	 * @return the mantenimientoCCCFacturarInteresViewValida
	 */
	public String getMantenimientoCCCFacturarInteresViewValida() {
		return mantenimientoCCCFacturarInteresViewValida;
	}

	/**
	 * @param mantenimientoCCCFacturarInteresViewValida the mantenimientoCCCFacturarInteresViewValida to set
	 */
	public void setMantenimientoCCCFacturarInteresViewValida(
			String mantenimientoCCCFacturarInteresViewValida) {
		this.mantenimientoCCCFacturarInteresViewValida = mantenimientoCCCFacturarInteresViewValida;
	}

	/**
	 * @return the mantenimientoCCCFacturarInteresErrorList
	 */
	public List getMantenimientoCCCFacturarInteresErrorList() {
		return mantenimientoCCCFacturarInteresErrorList;
	}

	/**
	 * @param mantenimientoCCCFacturarInteresErrorList the mantenimientoCCCFacturarInteresErrorList to set
	 */
	public void setMantenimientoCCCFacturarInteresErrorList(
			List mantenimientoCCCFacturarInteresErrorList) {
		this.mantenimientoCCCFacturarInteresErrorList = mantenimientoCCCFacturarInteresErrorList;
	}

	/**
	 * @return the indicadorGrabo
	 */
	public String getIndicadorGrabo() {
		return indicadorGrabo;
	}

	/**
	 * @param indicadorGrabo the indicadorGrabo to set
	 */
	public void setIndicadorGrabo(String indicadorGrabo) {
		this.indicadorGrabo = indicadorGrabo;
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
	 * @return the archivoPadre
	 */
	public UploadedFile getArchivoPadre() {
		return archivoPadre;
	}

	/**
	 * @param archivoPadre the archivoPadre to set
	 */
	public void setArchivoPadre(UploadedFile archivoPadre) {
		this.archivoPadre = archivoPadre;
	}

	/**
	 * @return the mostrarGrilla
	 */
	public Boolean getMostrarGrilla() {
		return mostrarGrilla;
	}

	/**
	 * @param mostrarGrilla the mostrarGrilla to set
	 */
	public void setMostrarGrilla(Boolean mostrarGrilla) {
		this.mostrarGrilla = mostrarGrilla;
	}	
}