package biz.belcorp.ssicc.web.spusicc.zon.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.zon.model.UnidadGeografica;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasService;
import biz.belcorp.ssicc.service.util.DBFUtil;
import biz.belcorp.ssicc.service.util.ExcelUtil;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.zon.form.ProcesoZONActualizarUnidadesGeograficasForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"unchecked","rawtypes"})
public class ProcesoZONActualizarUnidadesGeograficasAction extends BaseProcesoAbstractAction{

	@ManagedProperty(value = "#{reporteZONErroresCargaDatosUniGeoAction}")
	private ReporteZONErroresCargaDatosUniGeoAction reporteZONErroresCargaDatosUniGeoAction;
	
	@ManagedProperty(value = "#{reporteZONListadoDatosUniGeoAction}")
	private ReporteZONListadoDatosUniGeoAction reporteZONListadoDatosUniGeoAction;
	
	/**
	 * JPPS
	 */
	private static final long serialVersionUID = -3246874225578097629L;
	private String attachment;
	private List unidades;
	private List zonCampoList;
	
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		
		ProcesoZONActualizarUnidadesGeograficasForm z = new ProcesoZONActualizarUnidadesGeograficasForm();
		return z;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {

		ProcesoZONActualizarUnidadesGeograficasForm theForm = (ProcesoZONActualizarUnidadesGeograficasForm) this.formProceso;

		// obtenemos el servicio de Zon Unidades Geograficas
		ProcesoZONActualizarUnidadesGeograficasService service = (ProcesoZONActualizarUnidadesGeograficasService) getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");

		service.executeActualizarUnidadesGeograficas(params);
		theForm.setFlagProcesar(false);

		return params;
	
	}

	@Override
	protected void setViewAtributes() throws Exception {
		
		this.mostrarBotonExecute = false;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		ProcesoZONActualizarUnidadesGeograficasForm f = (ProcesoZONActualizarUnidadesGeograficasForm) this.formProceso;
		f.setFlagCargarDatos(false);
		f.setFlagMostrarPDFCargaDatos(false);
		f.setFlagMostrarPDFValidarDatos(false);
		f.setFlagValidarDatos(false);
		f.setFlagAprobar(false);
		f.setFlagProcesar(false);
		f.setIndicadorGeoreferenciaColArchivo(null);

		// obtenemos el servicio de Zon Unidades Geograficas
		ProcesoZONActualizarUnidadesGeograficasService service = (ProcesoZONActualizarUnidadesGeograficasService) getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");
		f.setDirectorioTemporal(service.obtenerPathUpload(pais.getCodigo()));
		
	}
	
	public void upload(FileUploadEvent event)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'upload' method");
		}
		
		ProcesoZONActualizarUnidadesGeograficasForm f = (ProcesoZONActualizarUnidadesGeograficasForm) this.formProceso;
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		// obtenemos el servicio de Zon Unidades Geograficas
		ProcesoZONActualizarUnidadesGeograficasService service = (ProcesoZONActualizarUnidadesGeograficasService) getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");
		
		f.setArchivo(event.getFile());
		f.setNombreArchivo(event.getFile().getFileName());
		
		this.setAttachment(event.getFile().getFileName());
		// Cargamos el archivo de la maquina del cliente al servidor
		UploadedFile archi = event.getFile();
		uploadArchivo(archi);

		// Leemos la primera linea del archivo, para recuperar los campos filtro
		// que nos
		// servira para determinar que campo corresponde a que nivel de
		// estructura geopolitica
		String extensionArchivo = obtenerExtensionArchivo(f.getNombreArchivo());
		f.setExtensionArchivo(extensionArchivo);
		List listCamposArchivo = null;
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		f.setDirectorioTemporal(service.obtenerPathUpload(pais.getCodigo()));

		if (extensionArchivo.equalsIgnoreCase(Constants.EXTENSION_ARCHIVO_EXCEL))
			listCamposArchivo = leerCabeceraArchivoExcel(f.getDirectorioTemporal(), f.getNombreArchivo());
		
		if (extensionArchivo.equalsIgnoreCase(Constants.EXTENSION_ARCHIVO_DBF))
			listCamposArchivo = leerCabeceraArchivoDBF(f.getDirectorioTemporal(), f.getNombreArchivo());

		// Borramos las tablas respecto a Unidades Geograficas
		service.deleteTablasUnidadesGeograficas();

		// Obtenemos la estructura geopolitica del pais
		List listEstructurasGeopoliticas = service.getEstructurasGeopoliticas(pais.getCodigo());

		// Colocamos en sesion la lista de estructura geopolitica del pais
		this.setUnidades(listEstructurasGeopoliticas);
		f.setUnidades(listEstructurasGeopoliticas);

		// Colocamos en sesion los campos recuperados al leer la primera linea
		// del archivo
		this.setZonCampoList(listCamposArchivo);
		

		// Habilitamos el boton [Cargar]
		f.setFlagCargarDatos(true);
		
		if(archi != null)
		this.redireccionarPagina("procesoZONActualizarUnidadesGeograficasForm");
	}
	
	private void uploadArchivo(UploadedFile archivo) throws Exception {
		ProcesoZONActualizarUnidadesGeograficasForm f = (ProcesoZONActualizarUnidadesGeograficasForm) this.formProceso;

		
		f.setNombreArchivo(archivo.getFileName());
		log.debug("Nombre Archivo Upload: " + f.getNombreArchivo());

		// leyemos el stream de entrada
		InputStream is = archivo.getInputstream();

		// abrimos el stream de escritura, ubicacion al cual se grabara el
		// archivo del cliente
		FileOutputStream os = new FileOutputStream(new File(f.getDirectorioTemporal(), 
								f.getNombreArchivo()));

		// grabamos cada 1024 bytes
		int bytesRead = 0;
		byte[] buffer = new byte[1024];
		while ((bytesRead = is.read(buffer, 0, 1024)) != -1) {
			os.write(buffer, 0, bytesRead);
		}
		os.close();

		f.setArchivo(null);
	}
	
	private String obtenerExtensionArchivo(String nombreArchivo)
			throws Exception {
		return nombreArchivo.substring(nombreArchivo.length() - 3);
	}
	
	private List leerCabeceraArchivoExcel(String directorioTemp,
			String nombreArchivo) throws Exception {
		ExcelUtil excelUtil = new ExcelUtil(directorioTemp, nombreArchivo);
		List listCamposArchivo = excelUtil.getFilaConColumnaNoNulos(0, 0);
		excelUtil.cerrar();
		
		return listCamposArchivo;
	}
	
	private List leerCabeceraArchivoDBF(String directorioTemp,
			String nombreArchivo) throws Exception {
		DBFUtil dbfUtil = new DBFUtil(directorioTemp, nombreArchivo);
		List listCamposArchivo = dbfUtil.obtenerBaseCampos();
		dbfUtil.cerrar();

		return listCamposArchivo;
	}
	
	public void cargar()
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'cargar' method");
		}

		ProcesoZONActualizarUnidadesGeograficasForm f = (ProcesoZONActualizarUnidadesGeograficasForm) this.formProceso;
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		ArrayList listEstructurasGeopoliticas = new ArrayList();
		
		listEstructurasGeopoliticas = (ArrayList) f.getUnidades();
		
		ArrayList lista = (ArrayList) listEstructurasGeopoliticas.clone();
		
		//Agregamos a la lista de unidades el Indicador de Georeferencia
		UnidadGeografica ug = new UnidadGeografica();
		ug.setCodigoOrdenEstruGeopo(String.valueOf(Integer.MAX_VALUE));
		ug.setIndicadorGeoreferenciaColArchivo(f.getIndicadorGeoreferenciaColArchivo());
		lista.add(ug);
		//
		
		Map datos = new HashMap();
		datos.put("listEstructuraGeopoliticas", lista);
		datos.put("directorioTemporal", f.getDirectorioTemporal());
		datos.put("nombreArchivo", f.getNombreArchivo());
		datos.put("extensionArchivo", f.getExtensionArchivo());
		datos.put("codigoPais", pais.getCodigo());

		// obtenemos el servicio de Zon Unidades Geograficas
		ProcesoZONActualizarUnidadesGeograficasService service = (ProcesoZONActualizarUnidadesGeograficasService) getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");
		service.executeCargarUnidadesGeograficas(datos);

		String error = (String) datos.get("error");

		if (error.equalsIgnoreCase(Constants.ERROR)) {
			f.setFlagMostrarPDFCargaDatos(true);
		} else { //Sin Errores
			f.setFlagCargarDatos(false);			
			f.setFlagMostrarPDFCargaDatos(false);
			f.setFlagValidarDatos(true);
			borrarFichero(f.getDirectorioTemporal(), f.getNombreArchivo());
		}

//		enviarMensajeRespuesta(  error, "procesoZONActualizarUnidadesGeograficasForm.msg.CargaDatos.ok",
//				"procesoZONActualizarUnidadesGeograficasForm.msg.CargaDatos.error");
		
		String msgOk = getResourceMessage("procesoZONActualizarUnidadesGeograficasForm.msg.CargaDatos.ok");
		String msgError = getResourceMessage("procesoZONActualizarUnidadesGeograficasForm.msg.CargaDatos.error");
		
		if (error.equalsIgnoreCase(Constants.ERROR)) {
			
			addError("Error", msgError);
			
		} else {
			addInfo("Mensaje", msgOk);
			
		}
		
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
	
	private void enviarMensajeRespuesta(String error, String msgOk,
			String msgError) {
		if (error.equalsIgnoreCase(Constants.ERROR)) {
					
			addError("Error", msgError);
			
		} else {
			addInfo("Mensaje", msgOk);
			
		}
	}
	
	public void validar()
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'validar' method");
		}

		ProcesoZONActualizarUnidadesGeograficasForm f = (ProcesoZONActualizarUnidadesGeograficasForm) this.formProceso;
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		Map datos = new HashMap();
		datos.put("codigoPais", pais.getCodigo());

		// obtenemos el servicio de Zon Unidades Geograficas
		ProcesoZONActualizarUnidadesGeograficasService service = (ProcesoZONActualizarUnidadesGeograficasService) getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");
		service.executeValidarDatosGeograficos(datos);

		f.setFlagMostrarPDFCargaDatos(false);
		f.setFlagValidarDatos(false);
		f.setFlagMostrarPDFValidarDatos(true);
		f.setFlagAprobar(true);
		
		String msjOk = getResourceMessage("procesoZONActualizarUnidadesGeograficasForm.msg.ValidarDatos.ok");
		enviarMensajeRespuesta( Constants.OK, msjOk , null);

	}

	public void aprobar()
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'aprobar' method");
		}

		ProcesoZONActualizarUnidadesGeograficasForm f = (ProcesoZONActualizarUnidadesGeograficasForm) this.formProceso;
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		Map datos = new HashMap();
		datos.put("codigoPais", pais.getCodigo());

		f.setFlagAprobar(false);
		f.setFlagMostrarPDFValidarDatos(false);
		f.setFlagProcesar(true);
		
		String msjOk= getResourceMessage("procesoZONActualizarUnidadesGeograficasForm.msg.Aprobar.ok"); 
		enviarMensajeRespuesta(Constants.OK, msjOk, null);

	}

	public void validarCargaDatos(){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'validarCargaDatos' method");
		}
		
		this.addWarn("", this.getResourceMessage("procesoZONActualizarUnidadesGeograficasForm.msg.CargarDatos.duplicado"));
	}
	
	public void muestraPDFCargaDatos(){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'muestraPDFCargaDatos' method");
		}
		
		try {
			this.reporteZONErroresCargaDatosUniGeoAction.executeReporte();
			this.redireccionarPagina("reporteZONErroresCargaDatosUniGeoForm");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void muestraPDFValidacion(){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'muestraPDFValidacion' method");
		}
		
		try {
			this.reporteZONListadoDatosUniGeoAction.executeReporte();
			this.redireccionarPagina("reporteZONListadoDatosUniGeoForm");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public List getUnidades() {
		return unidades;
	}

	public void setUnidades(List unidades) {
		this.unidades = unidades;
	}

	public List getZonCampoList() {
		return zonCampoList;
	}

	public void setZonCampoList(List zonCampoList) {
		this.zonCampoList = zonCampoList;
	}

	/**
	 * @return the reporteZONErroresCargaDatosUniGeoAction
	 */
	public ReporteZONErroresCargaDatosUniGeoAction getReporteZONErroresCargaDatosUniGeoAction() {
		return reporteZONErroresCargaDatosUniGeoAction;
	}

	/**
	 * @param reporteZONErroresCargaDatosUniGeoAction the reporteZONErroresCargaDatosUniGeoAction to set
	 */
	public void setReporteZONErroresCargaDatosUniGeoAction(ReporteZONErroresCargaDatosUniGeoAction reporteZONErroresCargaDatosUniGeoAction) {
		this.reporteZONErroresCargaDatosUniGeoAction = reporteZONErroresCargaDatosUniGeoAction;
	}

	/**
	 * @return the reporteZONListadoDatosUniGeoAction
	 */
	public ReporteZONListadoDatosUniGeoAction getReporteZONListadoDatosUniGeoAction() {
		return reporteZONListadoDatosUniGeoAction;
	}

	/**
	 * @param reporteZONListadoDatosUniGeoAction the reporteZONListadoDatosUniGeoAction to set
	 */
	public void setReporteZONListadoDatosUniGeoAction(ReporteZONListadoDatosUniGeoAction reporteZONListadoDatosUniGeoAction) {
		this.reporteZONListadoDatosUniGeoAction = reporteZONListadoDatosUniGeoAction;
	}	
}