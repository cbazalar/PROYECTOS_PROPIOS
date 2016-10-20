package biz.belcorp.ssicc.web.spusicc.mav.action;

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
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.mav.ProcesoMAVCargaMasivaService;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.mae.form.ProcesoMAECargaNivelRiesgoForm;
import biz.belcorp.ssicc.web.spusicc.mav.form.ProcesoMAVCargaMasivaForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"unchecked","rawtypes"})
public class ProcesoMAVCargaMasivaAction extends BaseProcesoAbstractAction{
	
	private static final long serialVersionUID = -8878889544222763706L;
	private List mavCargaMasivalist;
	private List mavCargaMasivaResumenlist;
	private String viewValida;
	private String  attachment = "";
	
	private Boolean mostrarBotonValidar;
	private Boolean mostrarPrimeraGrilla;
	private Boolean mostrarSegundaGrilla;
	
	private String paginaPadre;
	
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoMAVCargaMasivaForm f = new ProcesoMAVCargaMasivaForm();
		return f;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {

		ProcesoMAVCargaMasivaService service = (ProcesoMAVCargaMasivaService) getBean("spusicc.procesoMAVCargaMasivaService");
		service.executeActualizarCargaMasiva(params);
		
		return params;
	
	}
	
	@Override
	protected void afterExecuteProcessSuccess() {
		limpiar();
	}
	
	@Override
	protected void setViewAtributes() throws Exception {

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		limpiara();
		
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		
		ProcesoMAVCargaMasivaForm f = (ProcesoMAVCargaMasivaForm) this.formProceso;
			//seteamos la ruta temporal dodne guardar el excel
		ProcesoZONActualizarUnidadesGeograficasService serviceUnidad = (ProcesoZONActualizarUnidadesGeograficasService) getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");
		f.setDirectorioTemporal(serviceUnidad.obtenerPathUpload(pais.getCodigo()));
		
		f.setCodigoPais(pais.getCodigo());		
	}

	/**
	 * Carga el archivo excel que viene del request e inserta su informacion en tabla
	 * 
	 * 
	 */
	public void cargar(FileUploadEvent event)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'cargar' method");
		}

		
		Usuario usuario= this.mPantallaPrincipalBean.getCurrentUser();

		ProcesoMAVCargaMasivaForm f = (ProcesoMAVCargaMasivaForm) this.formProceso;
		
		this.setAttachment(event.getFile().getFileName());
		
		// Cargamos el archivo de la maquina del cliente al servidor
		UploadedFile archi = event.getFile();
		uploadArchivo(archi);
		
		// Obtenemos la extension del archivo
		String extensionArchivo = obtenerExtensionArchivo(f.getNombreArchivo());
		f.setExtensionArchivo(extensionArchivo);
		
		limpiara();
		this.mostrarBotonValidar = true;
	}
	
	/**
	 * Carga el archivo excel que viene del request e inserta su informacion en tabla
	 * 
	 * 
	 */
	public void validar()
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'validar' method");
		}
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario= this.mPantallaPrincipalBean.getCurrentUser();
		ProcesoMAVCargaMasivaForm f = (ProcesoMAVCargaMasivaForm) this.formProceso;
				
		//obtenemos el periodo desde la fecha de proceso 
		InterfazSiCCService serviceSiCC = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
				
		// obtenemos el servicio 
		ProcesoMAVCargaMasivaService service = (ProcesoMAVCargaMasivaService)  
													getBean("spusicc.procesoMAVCargaMasivaService");
		Map criteria = new HashMap();
		criteria.put("directorioTemporal",f.getDirectorioTemporal());
		criteria.put("nombreArchivo",f.getNombreArchivo());
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoUsuario", usuario.getLogin());

		//validamos el archivo excel y en criteria mandamos que estructura es sin period o con periodo
		boolean isValido = true; //service.validarCargaMasiva(criteria);
		//cargamos toda los registros del excel con la sgte informacion y simultaneamente cargara la 
		//informcion en la taba de puntaje
		//num fila codconsultora mensaje error num errores , este campo sera llenado y acualizado en el ultimo registro
		List listaArchivo =null;
				
		if(isValido){
			List listaResultado = service.executeValidarCargaMasiva(criteria);
			List listResumen = (List)listaResultado.get(0);
			listaArchivo = (List)listaResultado.get(1);
						
			f.setNumRegistros(String.valueOf(listaResultado.get(2)));
			f.setNumRegistrosError(String.valueOf(listaArchivo.size()));
			f.setNumRegistrosValido(String.valueOf(Integer.parseInt(f.getNumRegistros()) - Integer.parseInt(f.getNumRegistrosError())));
			
			//es valido si por lo menos hay un registro por cargar , es decir numero errors < num registros
			if(Integer.parseInt(f.getNumRegistrosError())< Integer.parseInt(f.getNumRegistros())) {
				f.setIndicadorValido(Constants.NUMERO_UNO);
				this.mostrarBotonExecute = true;
			} else
				f.setIndicadorValido(Constants.NUMERO_CERO);		
		
			borrarFichero(f.getDirectorioTemporal(), f.getNombreArchivo());
			this.viewValida = Constants.NUMERO_UNO;//flag para mostrar el resultado de la validacion
			this.mavCargaMasivalist = listaArchivo;
			this.mavCargaMasivaResumenlist = listResumen;
			
			this.mostrarPrimeraGrilla = true;
			this.mostrarSegundaGrilla = true;
			
		}else{
			listaArchivo = new ArrayList();
			f.setIndicadorValido(Constants.NUMERO_CERO);
			f.setNumRegistrosError(Constants.NUMERO_UNO);
			f.setNumRegistros(Constants.NUMERO_UNO);
			f.setNumRegistrosValido(Constants.NUMERO_CERO);
			
			this.viewValida = Constants.NUMERO_UNO;//flag para mostrar el resultado de la validacion
			//mandamos infomacion del archivo
			Map mapFila = new HashMap();
			mapFila.put("numeroFila", Constants.NUMERO_UNO);
			mapFila.put("mensajeError", this.getResourceMessage("procesoMAVCargaMasivaForm.error.noFormatoExcel"));				
			listaArchivo.add(mapFila);		
			this.mavCargaMasivalist = listaArchivo;
			this.mavCargaMasivaResumenlist = new ArrayList();

		}
		
	}
	
	/**
	 * carga el archivo
	 * 
	 */
	private void uploadArchivo(UploadedFile archivo) throws Exception {
		ProcesoMAVCargaMasivaForm f = (ProcesoMAVCargaMasivaForm) this.formProceso;
		
		f.setNombreArchivo(archivo.getFileName());
		log.debug("Nombre Archivo Upload: " + f.getNombreArchivo());
		// leyemos el stream de entrada
		InputStream is = archivo.getInputstream();
		// abrimos el stream de escritura, ubicacion al cual se grabara el
		// archivo del cliente
		FileOutputStream os = new FileOutputStream(new File(f.getDirectorioTemporal(), f.getNombreArchivo()));
		// grabamos cada 1024 bytes
		int bytesRead = 0;
		byte[] buffer = new byte[1024];
		while ((bytesRead = is.read(buffer, 0, 1024)) != -1) {
			os.write(buffer, 0, bytesRead);
		}
		os.close();
		f.setArchivo(null);
		String mensaje = "Se cargo archivo al Servidor con éxito";
		addInfo("Mensaje", mensaje);
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
		}	
		catch(Exception ex) {
			log.debug("No se pudo eliminar el archivo "+ex.getMessage());
		}
	}
	
	public void limpiara() {
		
		this.mavCargaMasivalist =null;
		this.mavCargaMasivaResumenlist =null;
		this.attachment = "";		
		this.mostrarPrimeraGrilla = false;
		this.mostrarSegundaGrilla = false;
		this.mostrarBotonExecute = false;
		this.mostrarBotonValidar = false;
	}

	// metodo que sale del popup	
	public void salirAPantallaPadre(ActionEvent actionEvent) {
		try {			
			this.redireccionarPagina(this.paginaPadre);			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}	
			
	}
	
	/**
	 * @return the mavCargaMasivalist
	 */
	public List getMavCargaMasivalist() {
		return mavCargaMasivalist;
	}

	/**
	 * @param mavCargaMasivalist the mavCargaMasivalist to set
	 */
	public void setMavCargaMasivalist(List mavCargaMasivalist) {
		this.mavCargaMasivalist = mavCargaMasivalist;
	}

	/**
	 * @return the mavCargaMasivaResumenlist
	 */
	public List getMavCargaMasivaResumenlist() {
		return mavCargaMasivaResumenlist;
	}

	/**
	 * @param mavCargaMasivaResumenlist the mavCargaMasivaResumenlist to set
	 */
	public void setMavCargaMasivaResumenlist(List mavCargaMasivaResumenlist) {
		this.mavCargaMasivaResumenlist = mavCargaMasivaResumenlist;
	}

	/**
	 * @return the viewValida
	 */
	public String getViewValida() {
		return viewValida;
	}

	/**
	 * @param viewValida the viewValida to set
	 */
	public void setViewValida(String viewValida) {
		this.viewValida = viewValida;
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
	 * @return the mostrarPrimeraGrilla
	 */
	public Boolean getMostrarPrimeraGrilla() {
		return mostrarPrimeraGrilla;
	}

	/**
	 * @param mostrarPrimeraGrilla the mostrarPrimeraGrilla to set
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
	 * @param mostrarSegundaGrilla the mostrarSegundaGrilla to set
	 */
	public void setMostrarSegundaGrilla(Boolean mostrarSegundaGrilla) {
		this.mostrarSegundaGrilla = mostrarSegundaGrilla;
	}

	/**
	 * @return the mostrarBotonValidar
	 */
	public Boolean getMostrarBotonValidar() {
		return mostrarBotonValidar;
	}

	/**
	 * @param mostrarBotonValidar the mostrarBotonValidar to set
	 */
	public void setMostrarBotonValidar(Boolean mostrarBotonValidar) {
		this.mostrarBotonValidar = mostrarBotonValidar;
	}

	/**
	 * @return the paginaPadre
	 */
	public String getPaginaPadre() {
		return paginaPadre;
	}

	/**
	 * @param paginaPadre the paginaPadre to set
	 */
	public void setPaginaPadre(String paginaPadre) {
		this.paginaPadre = paginaPadre;
	}
	
}

