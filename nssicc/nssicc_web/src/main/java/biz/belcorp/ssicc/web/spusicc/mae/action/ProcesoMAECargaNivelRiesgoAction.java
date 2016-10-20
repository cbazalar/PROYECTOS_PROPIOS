package biz.belcorp.ssicc.web.spusicc.mae.action;

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
import biz.belcorp.ssicc.service.spusicc.mae.ProcesoMAECargaNivelRiesgoService;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.mae.form.ProcesoMAECargaNivelRiesgoForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ProcesoMAECargaNivelRiesgoAction extends BaseProcesoAbstractAction {

	private static final long serialVersionUID = -1172260015473609961L;

	private List maeNivelRiesgoArchivoList;
	private String viewValida;
	private String attachment = "";
	private Boolean mostrarPrimeraGrilla;
	private Boolean mostrarSegundaGrilla;
	private String mensaje;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#devuelveFormProceso()
	 */
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoMAECargaNivelRiesgoForm f = new ProcesoMAECargaNivelRiesgoForm();
		return f;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#executeProcess(java.util.Map)
	 */
	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {

		ProcesoMAECargaNivelRiesgoService service = (ProcesoMAECargaNivelRiesgoService) getBean("spusicc.procesoMAECargaNivelRiesgoService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		params.put("codigoUsuario", usuario.getLogin());
		service.executeActualizarCargaNivelRiesgo(params);
		clearForm();
		
		return params;

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#afterExecuteProcessSuccess()
	 */
	@Override
	protected void afterExecuteProcessSuccess() {
		ProcesoMAECargaNivelRiesgoForm f = (ProcesoMAECargaNivelRiesgoForm) this.formProceso;
		f.setFlagBotonValidar(false);
		f.setFlagBotonActualizar(false);
		this.addInfo("Mensaje",getResourceMessage("procesoMAECargaNivelRiesgoForm.proceso.ok"));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		ProcesoMAECargaNivelRiesgoForm f = (ProcesoMAECargaNivelRiesgoForm) this.formProceso;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();		
		// seteamos la ruta temporal dodne guardar el excel
		ProcesoZONActualizarUnidadesGeograficasService serviceUnidad = (ProcesoZONActualizarUnidadesGeograficasService) getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");
		f.setDirectorioTemporal(serviceUnidad.obtenerPathUpload(pais.getCodigo()));
		f.setCodigoPais(pais.getCodigo());
		clearForm();
	}

	/**
	 * Carga el archivo excel que viene del request e inserta su informacion en
	 * tabla
	 * 
	 * 
	 */
	public void cargar(FileUploadEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'cargar' method");
		}
		try {
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

			ProcesoMAECargaNivelRiesgoForm f = (ProcesoMAECargaNivelRiesgoForm) this.formProceso;

			// obtenemos el service
			ProcesoMAECargaNivelRiesgoService service = (ProcesoMAECargaNivelRiesgoService) getBean("spusicc.procesoMAECargaNivelRiesgoService");

			this.setAttachment(event.getFile().getFileName());
			// Cargamos el archivo de la maquina del cliente al servidor
			UploadedFile archi = event.getFile();
			uploadArchivo(archi);

			// Obtenemos la extension del archivo
			String extensionArchivo = obtenerExtensionArchivo(f
					.getNombreArchivo());
			f.setExtensionArchivo(extensionArchivo);

			Map criteria = new HashMap();
			criteria.put("directorioTemporal", f.getDirectorioTemporal());
			criteria.put("nombreArchivo", f.getNombreArchivo());
			criteria.put("usuario", usuario);

			// validamos el archivo excel y en criteria mandamos que estructura
			// es sin period o con periodo
			Map resultados = service.cargarArchivoExcel(criteria);
			f.setNumeroCarga((String) resultados.get("numeroCarga"));
			f.setNumRegistros((String) resultados.get("totalRegistros"));

			f.setNumRegistrosError(Constants.NUMERO_CERO);
			f.setNumRegistrosValido(Constants.NUMERO_CERO);

			if (f.getNumeroCarga() != null)
				this.mostrarPrimeraGrilla = true;

			this.maeNivelRiesgoArchivoList = new ArrayList();

			borrarFichero(f.getDirectorioTemporal(), f.getNombreArchivo());

			this.viewValida = Constants.NUMERO_UNO; // flag para mostrar el
													// resultado de la
													// validacion

			f.setFlagBotonValidar(true);
			f.setFlagBotonActualizar(false);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * carga el archivo
	 * 
	 */
	private void uploadArchivo(UploadedFile archivo) {
		try {
			ProcesoMAECargaNivelRiesgoForm f = (ProcesoMAECargaNivelRiesgoForm) this.formProceso;

			f.setNombreArchivo(archivo.getFileName());
			log.debug("Nombre Archivo Upload: " + f.getNombreArchivo());
			// leyemos el stream de entrada
			InputStream is = archivo.getInputstream();
			// abrimos el stream de escritura, ubicacion al cual se grabara el
			// archivo del cliente
			FileOutputStream os = new FileOutputStream(new File(
					f.getDirectorioTemporal(), f.getNombreArchivo()));
			// grabamos cada 1024 bytes
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while ((bytesRead = is.read(buffer, 0, 1024)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();
			f.setArchivo(null);
			String mensaje = "Se cargo archivo al Servidor con éxito";
			this.addInfo("Mensaje", mensaje);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * obtiene la extension del archivo
	 * 
	 */
	private String obtenerExtensionArchivo(String nombreArchivo)
			throws Exception {
		return nombreArchivo.substring(nombreArchivo.length() - 3);
	}

	/**
	 * elimina el fichero del temporal
	 * 
	 */
	private void borrarFichero(String path, String nombreArchivo) {
		try {
			File file = new File(path, nombreArchivo);
			file.delete();
			log.debug("Se elimino el archivo");
		} catch (Exception ex) {
			log.debug("No se pudo eliminar el archivo " + ex.getMessage());
		}
	}

	/**
	 * Carga el archivo excel que viene del request e inserta su informacion en
	 * tabla
	 * 
	 * 
	 */
	public void validar() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'validar' method");
		}
		try {

			ProcesoMAECargaNivelRiesgoForm f = (ProcesoMAECargaNivelRiesgoForm) this.formProceso;
			Map params = BeanUtils.describe(f);

			// obtenemos el service
			ProcesoMAECargaNivelRiesgoService service = (ProcesoMAECargaNivelRiesgoService) getBean("spusicc.procesoMAECargaNivelRiesgoService");

			// validamos los datos cargados del archivo excel
			List resultados = service.executeValidarCargaNivelRiesgo(params);
			int totalErrores = resultados.size();
			int totalValidos = Integer.parseInt(f.getNumRegistros())
					- totalErrores;

			f.setNumRegistrosError(String.valueOf(totalErrores));
			f.setNumRegistrosValido(String.valueOf(totalValidos));

			this.maeNivelRiesgoArchivoList = resultados;

			if (this.maeNivelRiesgoArchivoList != null)
				this.mostrarSegundaGrilla = true;

			f.setFlagBotonValidar(false);
			f.setFlagBotonActualizar(true);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}
	
	public void clearForm() {
		ProcesoMAECargaNivelRiesgoForm f = (ProcesoMAECargaNivelRiesgoForm) this.formProceso;
		this.maeNivelRiesgoArchivoList = null;
		this.attachment = "";
		this.mostrarPrimeraGrilla = false;
		this.mostrarSegundaGrilla = false;
		this.mostrarBotonExecute = false;		
		this.mensaje="";
		f.setFlagBotonValidar(false);
		f.setFlagBotonActualizar(false);
	}
	
	//Metodo para Abrir el mensjae de confirmacion- abre el dialog del xhtml
	public void showMensaje(ActionEvent event){
		ProcesoMAECargaNivelRiesgoForm f = (ProcesoMAECargaNivelRiesgoForm) this.formProceso;
		String ventana = "PF('confirmDialogGrabar_confirmationDialogConfirmar').show()";
		if (f.isFlagBotonActualizar()) {
			if (Integer.parseInt(f.getNumRegistrosError()) > 0){				
				this.getRequestContext().execute(ventana);
				mensaje = this.getResourceMessage("procesoMAECargaNivelRiesgoForm.process.valido.errores");
			}else{
				this.getRequestContext().execute(ventana);
				mensaje = this.getResourceMessage("procesoMAECargaNivelRiesgoForm.process.valido");
			}
		}
		
	}	

	public List getMaeNivelRiesgoArchivoList() {
		return maeNivelRiesgoArchivoList;
	}

	public void setMaeNivelRiesgoArchivoList(List maeNivelRiesgoArchivoList) {
		this.maeNivelRiesgoArchivoList = maeNivelRiesgoArchivoList;
	}

	public String getViewValida() {
		return viewValida;
	}

	public void setViewValida(String viewValida) {
		this.viewValida = viewValida;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public Boolean getMostrarPrimeraGrilla() {
		return mostrarPrimeraGrilla;
	}

	public void setMostrarPrimeraGrilla(Boolean mostrarPrimeraGrilla) {
		this.mostrarPrimeraGrilla = mostrarPrimeraGrilla;
	}

	public Boolean getMostrarSegundaGrilla() {
		return mostrarSegundaGrilla;
	}

	public void setMostrarSegundaGrilla(Boolean mostrarSegundaGrilla) {
		this.mostrarSegundaGrilla = mostrarSegundaGrilla;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	

}
