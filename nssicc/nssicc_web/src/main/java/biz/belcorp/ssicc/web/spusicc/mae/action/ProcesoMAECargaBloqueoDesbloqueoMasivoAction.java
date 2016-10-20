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
import javax.faces.event.ValueChangeEvent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.mae.ProcesoMAECargaBloqueoDesbloqueoMasivoService;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.mae.form.ProcesoMAECargaBloqueoDesbloqueoMasivoForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProcesoMAECargaBloqueoDesbloqueoMasivoAction extends
		BaseProcesoAbstractAction {

	private static final long serialVersionUID = 1L;
	private List maeTipoBloqueoList;
	private List maeTipoDesbloqueoList;
	private List mantenimientoMaeBloqueoDesbloqueoTipoAreaList;
	private List maeAccionBloqueoList;
	private String viewValida;
	private List listRegistros;
	private String tipoBloqueoElegido;
	private List maeArchivoList;
	private Boolean mostraIndicadorBloqueo;
	private Boolean mostraDesbloqueoBloqueo;
	private String attachment = "";
	private Boolean mostrarPrimeraGrilla;
	private Boolean mostrarSegundaGrilla;
	private String mensaje;
	private Boolean botonExecute;
	private boolean indicadorDesbloqueo;

	/**
	 * Inicializa algunas variables
	 */
	public void limpiara() {
		this.mostraIndicadorBloqueo = false;
		this.mostraDesbloqueoBloqueo = false;
		this.maeTipoBloqueoList = null;
		this.mostrarPrimeraGrilla = false;
		this.mostrarSegundaGrilla = false;

	}

	/**
	 * validar parametros, y concatena un string
	 * 
	 * @return
	 */
	public Boolean validarParametros() {
		try {
			ProcesoMAECargaBloqueoDesbloqueoMasivoForm f = (ProcesoMAECargaBloqueoDesbloqueoMasivoForm) this.formProceso;
			String accionBloqueo = f.getAccionBloqueo();
			String tipoBloqueo = f.getTipoBloqueo();
			String observaciones = f.getObservacionesBloqueo();
			String mensaje = null;
			if (accionBloqueo == null) {
				mensaje = "Accion es un campo requerido,\n";
			}

			if (tipoBloqueo == null) {
				mensaje += "\nTipo Bloqueo es un campo requerido,\n";
			}

			if (observaciones == null) {
				mensaje += "\nObservaciones  es un campo requerido\n";
			}

			if (mensaje != null) {
				this.addError("Error :", mensaje);
				return false;
			} else {
				return true;

			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
			return false;
		}

	}

	/**
	 * Metodo que sirve para la carga de archivo
	 * 
	 * @param event
	 * @throws Exception
	 */
	public void handleFileUpload(FileUploadEvent event) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("handleFileUpload");
			}
			Boolean validar = validarParametros();
			if (validar == false) {
				return;
			}
			ProcesoMAECargaBloqueoDesbloqueoMasivoForm f = (ProcesoMAECargaBloqueoDesbloqueoMasivoForm) this.formProceso;
			if (event != null) {
				// f.setArchivo(event.getFile());
				f.setArchivo(event.getFile());
				this.setAttachment(event.getFile().getFileName());
				this.uploadArchivo();
				this.cargar();
				botonExecute = true;
			}

		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	// metodo que sale del popup
	public void salirPopup(ActionEvent event) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("Entering my method 'salirUA'");
			}
			
			ProcesoMAECargaBloqueoDesbloqueoMasivoForm f = (ProcesoMAECargaBloqueoDesbloqueoMasivoForm) this.formProceso;
			
			f.setAccionBloqueo("");
			f.setTipoBloqueo("");
			f.setCodigoArea("");
			f.setMotivoBloqueo("");
			f.setObservacionesBloqueo("");
			
			this.redireccionarPagina("mantenimientoMAEBloqueoDesbloqueoClienteList");
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * @param val
	 */
	public void loadBloqueosOdesbloqueos(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadBloqueosOdesbloqueos");
		}
		try {
			String codigo = val.getNewValue().toString();
			MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			// Lista de tipos de bloqueo
			if (codigo.equals("B")) {
				List listaBloqueos = clienteService
						.getAccesosTiposBloqueoByUsuario(usuario.getCodigo());
				this.mostraIndicadorBloqueo = true;
				this.mostraDesbloqueoBloqueo = false;
				this.maeTipoBloqueoList = listaBloqueos;
			}
			if (codigo.equals("D")) {
				List listaDesbloqueos = clienteService
						.getAccesosTiposDesbloqueoByUsuario(usuario.getCodigo());
				this.mostraDesbloqueoBloqueo = true;
				this.mostraIndicadorBloqueo = false;
				this.maeTipoBloqueoList = listaDesbloqueos;
			}
			if (codigo.equals("")) {
				limpiara();

			}

		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/*
	 * Carga el archivo excel que viene del request e inserta su informacion en
	 * tabla
	 */
	public void cargar() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'cargar' method");
		}
		try {

			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

			ProcesoMAECargaBloqueoDesbloqueoMasivoForm f = (ProcesoMAECargaBloqueoDesbloqueoMasivoForm) this.formProceso;

			// obtenemos el service
			ProcesoMAECargaBloqueoDesbloqueoMasivoService service = (ProcesoMAECargaBloqueoDesbloqueoMasivoService) getBean("spusicc.procesoMAECargaBloqueoDesbloqueoMasivoService");
			// Cargamos el archivo de la maquina del cliente al servidor
			// uploadArchivo();

			// Obtenemos la extension del archivo
			String extensionArchivo = obtenerExtensionArchivo(f
					.getNombreArchivo());
			f.setExtensionArchivo(extensionArchivo);

			Map criteria = new HashMap();
			criteria.put("directorioTemporal", f.getDirectorioTemporal());
			criteria.put("nombreArchivo", f.getNombreArchivo());
			criteria.put("usuario", usuario);

			// validamos el archivo excel y en criteria mandamos que estructura
			// es
			// sin period o con periodo
			Map resultados = service.cargarArchivoExcel(criteria);
			List listRegistros = (List) resultados.get("listRegistros");
			f.setNumRegistros((String) resultados.get("totalRegistros"));
			if (listRegistros != null) {
				this.mostrarPrimeraGrilla = true;
			}

			f.setNumRegistrosError(Constants.NUMERO_CERO);
			f.setNumRegistrosValido(Constants.NUMERO_CERO);

			borrarFichero(f.getDirectorioTemporal(), f.getNombreArchivo());
			this.viewValida = Constants.NUMERO_UNO;// flag para mostrar el
													// resultado
													// de la validacion
			this.listRegistros = listRegistros;

			f.setFlagBotonValidar(true);
			f.setFlagBotonActualizar(false);

			this.tipoBloqueoElegido = f.getTipoBloqueo();

		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * Valida el excel que viene del request
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void validar() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'validar' method");
		}

		try {

			ProcesoMAECargaBloqueoDesbloqueoMasivoForm f = (ProcesoMAECargaBloqueoDesbloqueoMasivoForm) this.formProceso;

			// validar indicador
			String indicadorDesbloqueo = f.getIndicadorDesbloqueo();
			if (indicadorDesbloqueo != null) {
				f.setIndicadorDesbloqueo(indicadorDesbloqueo);
			} else {
				f.setIndicadorDesbloqueo("1");
			}

			// obtenemos el service
			ProcesoMAECargaBloqueoDesbloqueoMasivoService service = (ProcesoMAECargaBloqueoDesbloqueoMasivoService) getBean("spusicc.procesoMAECargaBloqueoDesbloqueoMasivoService");

			// recuperamos los registros cargados del Excel
			List listRegistros = this.listRegistros;

			Map params = new HashMap();
			params.put("accionBloqueo", f.getAccionBloqueo());
			params.put("tipoBloqueo", f.getTipoBloqueo());
			params.put("codigoPais", f.getCodigoPais());
			params.put("listRegistros", listRegistros);

			// validamos los datos cargados del archivo excel
			List resultados = service
					.executeValidarCargaBloqueoDesbloqueoMasivos(params);

			int totalErrores = 0;
			for (int i = 0; i < resultados.size(); i++) {
				Map mapRegistro = (Map) resultados.get(i);
				if (mapRegistro.get("codigoMotivo") != null) {
					totalErrores++;
				}
			}

			int totalValidos = Integer.parseInt(f.getNumRegistros())
					- totalErrores;

			f.setNumRegistrosError(String.valueOf(totalErrores));
			f.setNumRegistrosValido(String.valueOf(totalValidos));

			this.maeArchivoList = resultados;
			if (this.maeArchivoList != null) {
				this.mostrarSegundaGrilla = true;
			}

			f.setFlagBotonValidar(false);
			f.setFlagBotonActualizar(true);

			this.tipoBloqueoElegido = f.getTipoBloqueo();
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * carga el archivo
	 * 
	 * @param form
	 * @throws Exception
	 */

	private void uploadArchivo() {
		try {
			ProcesoMAECargaBloqueoDesbloqueoMasivoForm f = (ProcesoMAECargaBloqueoDesbloqueoMasivoForm) this.formProceso;

			// recuperamos el fichero
			UploadedFile archivo = f.getArchivo();
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
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * obtiene la extension del archivo
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
	 * elimina el fichero del temporal
	 * 
	 * @param path
	 * @param nombreArchivo
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction
	 * #prepareParamsBeforeExecute(java.util.Map)
	 */
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Exectuting action : prepareParamsBeforeExecute.");
		}
		ProcesoMAECargaBloqueoDesbloqueoMasivoForm f = (ProcesoMAECargaBloqueoDesbloqueoMasivoForm) this.formProceso;
		f.setIndicadorDesbloqueo(null);
		if(indicadorDesbloqueo)
			f.setIndicadorDesbloqueo("1");
		f.setCodigoArea("");
		params = super.prepareParamsBeforeExecute(params, form);
		String accionBloqueo = String.valueOf(params.get("accionBloqueo"));
		String indicadorDesbloqueo = String.valueOf(params
				.get("indicadorDesbloqueo"));

		if (indicadorDesbloqueo.equals("null"))
			indicadorDesbloqueo = "";
		if (accionBloqueo.equals("B")) // Bloquear
			indicadorDesbloqueo = "";
		params.put("indicadorDesbloqueo", indicadorDesbloqueo);

		List listRegistros = this.maeArchivoList;
		params.put("listRegistros", listRegistros);
		return params;
	}
	
	//Metodo para Abrir el mensjae de confirmacion- abre el dialog del xhtml
	public void abrirMensaje(ActionEvent event) {
		ProcesoMAECargaBloqueoDesbloqueoMasivoForm f = (ProcesoMAECargaBloqueoDesbloqueoMasivoForm) this.formProceso;
		String ventana = "PF('confirmDialogGrabar_confirmationDialogConfirmar').show()";
		if (f.isFlagBotonActualizar()) {
			if (Integer.parseInt(f.getNumRegistrosError()) > 0){				
				this.getRequestContext().execute(ventana);
				mensaje = this.getResourceMessage("procesoMAECargaBloqueoDesbloqueoMasivoForm.process.valido.errores");
			}else{
				this.getRequestContext().execute(ventana);
				mensaje = this.getResourceMessage("procesoMAECargaBloqueoDesbloqueoMasivoForm.process.valido");
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction
	 * #afterExecuteProcess
	 * (biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm)
	 */
	@Override
	public void afterExecuteProcess(BaseProcesoForm form) throws Exception {
		String mensaje = null;
		mensaje = this
				.getResourceMessage("procesoMAECargaBloqueoDesbloqueoMasivoForm.proceso.ok");
		this.view();
		this.addInfo("Info : ", mensaje);
		clearPantalla();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction
	 * #devuelveFormProceso()
	 */
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoMAECargaBloqueoDesbloqueoMasivoForm form = new ProcesoMAECargaBloqueoDesbloqueoMasivoForm();
		return form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction
	 * #executeProcess(java.util.Map)
	 */
	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		ProcesoMAECargaBloqueoDesbloqueoMasivoService service = (ProcesoMAECargaBloqueoDesbloqueoMasivoService) getBean("spusicc.procesoMAECargaBloqueoDesbloqueoMasivoService");
		List listRegistros = this.maeArchivoList;
		params.put("listRegistros", listRegistros);
		this.mostrarSegundaGrilla=false;		
		service.executeActualizarBloqueoDesbloqueoMasivos(params);
		return params;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		limpiara();
		ProcesoMAECargaBloqueoDesbloqueoMasivoForm f = (ProcesoMAECargaBloqueoDesbloqueoMasivoForm) this.formProceso;
		// seteamos la ruta temporal dodne guardar el excel
		ProcesoZONActualizarUnidadesGeograficasService serviceUnidad = (ProcesoZONActualizarUnidadesGeograficasService) getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		f.setCodigoPais(f.getCodigoPais());
		f.setDirectorioTemporal(serviceUnidad.obtenerPathUpload(pais
				.getCodigo()));

		this.mantenimientoMaeBloqueoDesbloqueoTipoAreaList = clienteService
				.getAccesosTiposAreaByPais(pais.getCodigo());

		// Lista de Accion de Bloqueo
		List listaAcciones = new ArrayList();
		Base base = new Base();
		base.setCodigo("B");
		base.setDescripcion("Bloquear");
		listaAcciones.add(base);
		base = new Base();
		base.setCodigo("D");
		base.setDescripcion("Desbloquear");
		listaAcciones.add(base);
		this.maeAccionBloqueoList = listaAcciones;

		f.setFlagBotonValidar(false);
		f.setFlagBotonActualizar(false);
		f.setCodigoPais(pais.getCodigo());
		mostrarBotonExecute = false;
		botonExecute = false;
	}
	
	public void clearPantalla(){
		ProcesoMAECargaBloqueoDesbloqueoMasivoForm f = (ProcesoMAECargaBloqueoDesbloqueoMasivoForm) this.formProceso;
		f.setAccionBloqueo("");
		f.setTipoBloqueo("");
		f.setCodigoArea("");
		f.setMotivoBloqueo("");
		f.setObservacionesBloqueo("");
		this.attachment="";
		this.mostrarPrimeraGrilla=false;
		this.mostrarSegundaGrilla=false;
		this.maeArchivoList=new ArrayList();	
	}

	/**
	 * @return the maeTipoBloqueoList
	 */
	public List getMaeTipoBloqueoList() {
		return maeTipoBloqueoList;
	}

	/**
	 * @param maeTipoBloqueoList
	 *            the maeTipoBloqueoList to set
	 */
	public void setMaeTipoBloqueoList(List maeTipoBloqueoList) {
		this.maeTipoBloqueoList = maeTipoBloqueoList;
	}

	/**
	 * @return the mantenimientoMaeBloqueoDesbloqueoTipoAreaList
	 */
	public List getMantenimientoMaeBloqueoDesbloqueoTipoAreaList() {
		return mantenimientoMaeBloqueoDesbloqueoTipoAreaList;
	}

	/**
	 * @param mantenimientoMaeBloqueoDesbloqueoTipoAreaList
	 *            the mantenimientoMaeBloqueoDesbloqueoTipoAreaList to set
	 */
	public void setMantenimientoMaeBloqueoDesbloqueoTipoAreaList(
			List mantenimientoMaeBloqueoDesbloqueoTipoAreaList) {
		this.mantenimientoMaeBloqueoDesbloqueoTipoAreaList = mantenimientoMaeBloqueoDesbloqueoTipoAreaList;
	}

	/**
	 * @return the maeAccionBloqueoList
	 */
	public List getMaeAccionBloqueoList() {
		return maeAccionBloqueoList;
	}

	/**
	 * @param maeAccionBloqueoList
	 *            the maeAccionBloqueoList to set
	 */
	public void setMaeAccionBloqueoList(List maeAccionBloqueoList) {
		this.maeAccionBloqueoList = maeAccionBloqueoList;
	}

	/**
	 * @return the viewValida
	 */
	public String getViewValida() {
		return viewValida;
	}

	/**
	 * @param viewValida
	 *            the viewValida to set
	 */
	public void setViewValida(String viewValida) {
		this.viewValida = viewValida;
	}

	/**
	 * @return the listRegistros
	 */
	public List getListRegistros() {
		return listRegistros;
	}

	/**
	 * @param listRegistros
	 *            the listRegistros to set
	 */
	public void setListRegistros(List listRegistros) {
		this.listRegistros = listRegistros;
	}

	/**
	 * @return the tipoBloqueoElegido
	 */
	public String getTipoBloqueoElegido() {
		return tipoBloqueoElegido;
	}

	/**
	 * @param tipoBloqueoElegido
	 *            the tipoBloqueoElegido to set
	 */
	public void setTipoBloqueoElegido(String tipoBloqueoElegido) {
		this.tipoBloqueoElegido = tipoBloqueoElegido;
	}

	/**
	 * @return the maeArchivoList
	 */
	public List getMaeArchivoList() {
		return maeArchivoList;
	}

	/**
	 * @param maeArchivoList
	 *            the maeArchivoList to set
	 */
	public void setMaeArchivoList(List maeArchivoList) {
		this.maeArchivoList = maeArchivoList;
	}

	/**
	 * @return the maeTipoDesbloqueoList
	 */
	public List getMaeTipoDesbloqueoList() {
		return maeTipoDesbloqueoList;
	}

	/**
	 * @param maeTipoDesbloqueoList
	 *            the maeTipoDesbloqueoList to set
	 */
	public void setMaeTipoDesbloqueoList(List maeTipoDesbloqueoList) {
		this.maeTipoDesbloqueoList = maeTipoDesbloqueoList;
	}

	/**
	 * @return the mostraIndicadorBloqueo
	 */
	public Boolean getMostraIndicadorBloqueo() {
		return mostraIndicadorBloqueo;
	}

	/**
	 * @param mostraIndicadorBloqueo
	 *            the mostraIndicadorBloqueo to set
	 */
	public void setMostraIndicadorBloqueo(Boolean mostraIndicadorBloqueo) {
		this.mostraIndicadorBloqueo = mostraIndicadorBloqueo;
	}

	/**
	 * @return the mostraDesbloqueoBloqueo
	 */
	public Boolean getMostraDesbloqueoBloqueo() {
		return mostraDesbloqueoBloqueo;
	}

	/**
	 * @param mostraDesbloqueoBloqueo
	 *            the mostraDesbloqueoBloqueo to set
	 */
	public void setMostraDesbloqueoBloqueo(Boolean mostraDesbloqueoBloqueo) {
		this.mostraDesbloqueoBloqueo = mostraDesbloqueoBloqueo;
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
	 * @return the mostrarPrimeraGrilla
	 */
	public Boolean getMostrarPrimeraGrilla() {
		return mostrarPrimeraGrilla;
	}

	/**
	 * @param mostrarPrimeraGrilla
	 *            the mostrarPrimeraGrilla to set
	 */
	public void setMostrarPrimeraGrilla(Boolean mostrarPrimeraGrilla) {
		this.mostrarPrimeraGrilla = mostrarPrimeraGrilla;
	}

	/**
	 * @return the mostrarSegundaGrilla
	 */
	public Boolean getMostrarSegundaGrilla() {
		return mostrarSegundaGrilla;
	}

	/**
	 * @param mostrarSegundaGrilla
	 *            the mostrarSegundaGrilla to set
	 */
	public void setMostrarSegundaGrilla(Boolean mostrarSegundaGrilla) {
		this.mostrarSegundaGrilla = mostrarSegundaGrilla;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Boolean getBotonExecute() {
		return botonExecute;
	}

	public void setBotonExecute(Boolean botonExecute) {
		this.botonExecute = botonExecute;
	}

	public boolean isIndicadorDesbloqueo() {
		return indicadorDesbloqueo;
	}

	public void setIndicadorDesbloqueo(boolean indicadorDesbloqueo) {
		this.indicadorDesbloqueo = indicadorDesbloqueo;
	}
	
	

}