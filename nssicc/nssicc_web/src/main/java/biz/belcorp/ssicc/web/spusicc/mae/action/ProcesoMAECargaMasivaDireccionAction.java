package biz.belcorp.ssicc.web.spusicc.mae.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.mae.ProcesoMAECargaMasivaDireccionService;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.mae.form.ProcesoMAECargaMasivaDireccionForm;

/**
 * @author jpulido
 * 
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ProcesoMAECargaMasivaDireccionAction extends
		BaseProcesoAbstractAction {

	private static final long serialVersionUID = 1494141550408572632L;

	private String codigoIdiomaISO;
	private List maeDireccionesErroresArchivoList;
	private List maeDireccionesArchivoList;
	private boolean viewValida = false;
	private String attachment = "";
	private Boolean mostrarPrimeraGrilla;
	private Boolean mostrarSegundaGrilla;
	private Boolean mostrarTerceraGrilla;
	
	private String msjDialog;
	private String indAdicional;
	
	@ManagedProperty(value = "#{reporteMAECargaMasivaDireccionAction}")
	private ReporteMAECargaMasivaDireccionAction reporteMAECargaMasivaDireccion;

	/**
	 * Ejecutar boton de reporte
	 * 
	 * @param evt
	 */
	public void ejecutarReporteExcel(String tipo) {
		try {
			ProcesoMAECargaMasivaDireccionForm f = (ProcesoMAECargaMasivaDireccionForm) this.formProceso;
			String numeroCarga = f.getNumeroCarga();
			this.reporteMAECargaMasivaDireccion.setFormatoExportacion("XLS");
			this.reporteMAECargaMasivaDireccion.setNumeroCarga(numeroCarga);
			this.reporteMAECargaMasivaDireccion.getFormReporte().setFormatoExportacion("XLS");
			this.reporteMAECargaMasivaDireccion.setTipo(tipo);
			this.reporteMAECargaMasivaDireccion.executeReporte();

			// this.redireccionarPagina("reporteLECCargaDatosFormatosForm");
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

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
		ProcesoMAECargaMasivaDireccionForm J = new ProcesoMAECargaMasivaDireccionForm();
		return J;
	}
	
	/**
	 * @param evt
	 */
	public void validarProcesoGuardar(ActionEvent evt) {
		try {
			ProcesoMAECargaMasivaDireccionForm f = (ProcesoMAECargaMasivaDireccionForm) this.formProceso;
			Integer numError = Integer.valueOf(f.getNumRegistrosError());
			if (numError > 0) {
				String ventana = "confirmValoresErrados";
				String ventanaConfirmar = "PF('" + ventana
						+ "_confirmationDialogConfirmar').show()";
				this.getRequestContext().execute(ventanaConfirmar);

				return;
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
			this.mostrarTerceraGrilla = false;
			return;
		}
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

		ProcesoMAECargaMasivaDireccionForm f = (ProcesoMAECargaMasivaDireccionForm) this.formProceso;
		ProcesoMAECargaMasivaDireccionService service = (ProcesoMAECargaMasivaDireccionService) getBean("spusicc.procesoMAECargaMasivaDireccionService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		params = BeanUtils.describe(f);
		params.put("codigoUsuario", usuario.getLogin());
		params.put("indCamposAdicionales", indAdicional);
		service.executeActualizarCargaMasivaDireccion(params);
		f.setNumeroLote((String) params.get("numeroLote"));
		
		return params;

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
		super.afterExecuteProcess(form);
		ProcesoMAECargaMasivaDireccionForm f = (ProcesoMAECargaMasivaDireccionForm) this.formProceso;
		f.setFlagBotonValidar(false);
		f.setFlagBotonActualizar(false);
		f.setFlagBotonReporte(true);
		this.viewValida = true;
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
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		limpiara();
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ProcesoMAECargaMasivaDireccionForm f = (ProcesoMAECargaMasivaDireccionForm) this.formProceso;
		f.reset();
		this.mostrarBotonExecute = false;
		this.mostrarTerceraGrilla = false;

		// seteamos la ruta temporal dodne guardar el excel
		ProcesoZONActualizarUnidadesGeograficasService serviceUnidad = (ProcesoZONActualizarUnidadesGeograficasService) getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");
		f.setDirectorioTemporal(serviceUnidad.obtenerPathUpload(pais
				.getCodigo()));

		this.codigoIdiomaISO = usuario.getIdioma().getCodigoISO();
		f.setFlagBotonValidar(false);
		f.setFlagBotonActualizar(false);
		f.setFlagBotonReporte(false);
		f.setNumeroLote("");
		
		//limpiando el flag de validacion de archivo
		this.viewValida = false;
		this.mostrarPanelAdicionalProceso= false; 
		this.maeDireccionesErroresArchivoList = new ArrayList();
		this.indAdicional="";
			
	}


	/**
	 * Carga el archivo excel que viene del request e inserta su informacion en
	 * tabla
	 * 
	 */
	public void cargar(FileUploadEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'cargar' method");
		}
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		this.setAttachment(event.getFile().getFileName());
		ProcesoMAECargaMasivaDireccionForm f = (ProcesoMAECargaMasivaDireccionForm) this.formProceso;
		ProcesoMAECargaMasivaDireccionService service = (ProcesoMAECargaMasivaDireccionService) getBean("spusicc.procesoMAECargaMasivaDireccionService");
		GenericoService genericoService = (GenericoService) getBean("genericoService");
		// Cargamos el archivo de la maquina del cliente al servidor

		UploadedFile archi = event.getFile();
		uploadArchivo(archi);
		f.setArchivo(archi);

		// Obtenemos la extension del archivo
		String extensionArchivo = obtenerExtensionArchivo(f.getNombreArchivo());
		f.setExtensionArchivo(extensionArchivo);
		//Obtener el ind campos Adicionales
		ParametroPais paramPais = new ParametroPais();	
		paramPais.setCodigoSistema(Constants.MAE_CODIGO_SISTEMA);
		paramPais.setNombreParametro(Constants.MAE_PARAM_INDICADOR_CAMPOS_ADICIONALES);
		List lstParametros = genericoService.getParametrosPais(paramPais);		
		if(!lstParametros.isEmpty())
			indAdicional =((ParametroPais)lstParametros.get(0)).getValorParametro();
		if(!StringUtils.equals(indAdicional, "1"))
			indAdicional=null;

		Map criteria = new HashMap();
		criteria.put("directorioTemporal", f.getDirectorioTemporal());
		criteria.put("nombreArchivo", f.getNombreArchivo());
		criteria.put("usuario", usuario);
		criteria.put("indCamposAdicionales", indAdicional);
		

		// validamos el archivo excel y en criteria mandamos que estructura es
		// sin period o con periodo
		Map resultados = service.cargarArchivoExcel(criteria);
		f.setNumeroCarga((String) resultados.get("numeroCarga"));
		f.setNumRegistros((String) resultados.get("totalRegistros"));

		f.setNumRegistrosError(Constants.NUMERO_CERO);
		f.setNumRegistrosValido(Constants.NUMERO_CERO);

		if (f.getNumeroCarga() != null)
			this.mostrarPrimeraGrilla = true;		
		
		borrarFichero(f.getDirectorioTemporal(), f.getNombreArchivo());
		
		this.maeDireccionesArchivoList = new ArrayList();
		this.maeDireccionesErroresArchivoList = new ArrayList();
		this.mostrarPanelAdicionalProceso= true;  
		this.viewValida = true;// flag para mostrar el resultado de la validacion
		
		f.setFlagBotonValidar(true);
		f.setFlagBotonActualizar(false);
		f.setFlagBotonReporte(false);
		f.setNumeroLote("");

	}

	/**
	 * Carga el archivo excel que viene del request e inserta su informacion en
	 * tabla
	 * 
	 */
	public void validar(ActionEvent actionEvent) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'validar' method");
		}

		ProcesoMAECargaMasivaDireccionForm f = (ProcesoMAECargaMasivaDireccionForm) this.formProceso;

		Map params = BeanUtils.describe(f);

		// obtenemos el service
		ProcesoMAECargaMasivaDireccionService service = (ProcesoMAECargaMasivaDireccionService) getBean("spusicc.procesoMAECargaMasivaDireccionService");

		// validamos los datos cargados del archivo excel
		Map mapResultados = service.executeValidarCargaMasivaDireccion(params);
		List resultados = (List)mapResultados.get("resultados");
		List resultadosOK = (List)mapResultados.get("resultadosOK");
		int totalErrores = resultados.size();
		int totalValidos = Integer.parseInt(f.getNumRegistros()) - totalErrores;

		f.setNumRegistrosError(String.valueOf(totalErrores));
		f.setNumRegistrosValido(String.valueOf(totalValidos));

		this.maeDireccionesErroresArchivoList = resultados;
		this.maeDireccionesArchivoList = resultadosOK;
		
		if (this.maeDireccionesErroresArchivoList != null)
			this.mostrarSegundaGrilla = true;
		this.mostrarTerceraGrilla = false;

		f.setFlagBotonValidar(false);
		f.setFlagBotonActualizar(true);
		f.setFlagBotonReporte(false);
		this.mostrarPanelAdicionalProceso= true; 

	}

	/**
	 * carga el archivo
	 */
	private void uploadArchivo(UploadedFile archivo) throws Exception {
		ProcesoMAECargaMasivaDireccionForm f = (ProcesoMAECargaMasivaDireccionForm) this.formProceso;

		// recuperamos el fichero
		// UploadedFile archivo = f.getArchivo();
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
	}

	/**
	 * obtiene la extension del archivo
	 */
	private String obtenerExtensionArchivo(String nombreArchivo)
			throws Exception {
		return nombreArchivo.substring(nombreArchivo.length() - 3);
	}

	/**
	 * elimina el fichero del temporal
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
	 * Limpia la pantalla
	 */
	public void limpiara() {
		this.maeDireccionesErroresArchivoList = null;
		this.attachment = "";
		this.mostrarPrimeraGrilla = false;
		this.mostrarSegundaGrilla = false;
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {

		String msj = null;
		
		ProcesoMAECargaMasivaDireccionForm f = (ProcesoMAECargaMasivaDireccionForm)this.formProceso;
		
		if(Integer.parseInt(f.getNumRegistrosError())>0){
			this.msjDialog = this.getResourceMessage("procesoMAECargaMasivaDireccionForm.process.valido.errores");
			
		}
		else{
			this.msjDialog = this.getResourceMessage("procesoMAECargaMasivaDireccionForm.process.valido");
		}
		
		return msj;
		
	}

	/**
	 * @return
	 */
	public String getCodigoIdiomaISO() {
		return codigoIdiomaISO;
	}

	/**
	 * @param codigoIdiomaISO
	 */
	public void setCodigoIdiomaISO(String codigoIdiomaISO) {
		this.codigoIdiomaISO = codigoIdiomaISO;
	}

	/**
	 * @return
	 */
	public String getAttachment() {
		return attachment;
	}

	/**
	 * @param attachment
	 */
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	/**
	 * @return
	 */
	public Boolean getMostrarPrimeraGrilla() {
		return mostrarPrimeraGrilla;
	}

	/**
	 * @param mostrarPrimeraGrilla
	 */
	public void setMostrarPrimeraGrilla(Boolean mostrarPrimeraGrilla) {
		this.mostrarPrimeraGrilla = mostrarPrimeraGrilla;
	}

	/**
	 * @return
	 */
	public Boolean getMostrarSegundaGrilla() {
		return mostrarSegundaGrilla;
	}

	/**
	 * @param mostrarSegundaGrilla
	 */
	public void setMostrarSegundaGrilla(Boolean mostrarSegundaGrilla) {
		this.mostrarSegundaGrilla = mostrarSegundaGrilla;
	}

	/**
	 * @return the mostrarTerceraGrilla
	 */
	public Boolean getMostrarTerceraGrilla() {
		return mostrarTerceraGrilla;
	}

	/**
	 * @param mostrarTerceraGrilla
	 *            the mostrarTerceraGrilla to set
	 */
	public void setMostrarTerceraGrilla(Boolean mostrarTerceraGrilla) {
		this.mostrarTerceraGrilla = mostrarTerceraGrilla;
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
	 * @return the viewValida
	 */
	public boolean isViewValida() {
		return viewValida;
	}

	/**
	 * @param viewValida the viewValida to set
	 */
	public void setViewValida(boolean viewValida) {
		this.viewValida = viewValida;
	}

	/**
	 * @return the maeDireccionesErroresArchivoList
	 */
	public List getMaeDireccionesErroresArchivoList() {
		return maeDireccionesErroresArchivoList;
	}

	/**
	 * @param maeDireccionesErroresArchivoList the maeDireccionesErroresArchivoList to set
	 */
	public void setMaeDireccionesErroresArchivoList(
			List maeDireccionesErroresArchivoList) {
		this.maeDireccionesErroresArchivoList = maeDireccionesErroresArchivoList;
	}

	/**
	 * @return the reporteMAECargaMasivaDireccion
	 */
	public ReporteMAECargaMasivaDireccionAction getReporteMAECargaMasivaDireccion() {
		return reporteMAECargaMasivaDireccion;
	}

	/**
	 * @param reporteMAECargaMasivaDireccion the reporteMAECargaMasivaDireccion to set
	 */
	public void setReporteMAECargaMasivaDireccion(
			ReporteMAECargaMasivaDireccionAction reporteMAECargaMasivaDireccion) {
		this.reporteMAECargaMasivaDireccion = reporteMAECargaMasivaDireccion;
	}

	/**
	 * @return the maeDireccionesArchivoList
	 */
	public List getMaeDireccionesArchivoList() {
		return maeDireccionesArchivoList;
	}

	/**
	 * @param maeDireccionesArchivoList the maeDireccionesArchivoList to set
	 */
	public void setMaeDireccionesArchivoList(List maeDireccionesArchivoList) {
		this.maeDireccionesArchivoList = maeDireccionesArchivoList;
	}
}

