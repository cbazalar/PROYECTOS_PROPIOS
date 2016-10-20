package biz.belcorp.ssicc.web.spusicc.inc.action;

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
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.inc.ProcesoINCRecepcionarTransaccionesPagoConcursoService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.inc.form.ProcesoINCRecepcionarTransaccionesPagoConcursoForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"unchecked","rawtypes"})
public class ProcesoINCRecepcionarTransaccionesPagoConcursoAction extends BaseProcesoAbstractAction{
	
	private static final long serialVersionUID = -8878889544222763706L;
	private List incTransaccionPagoConcursoList;
	private List incPagoConcursoList;
	
	private boolean viewValida = false;
	private String  attachment = "";
	
	private Boolean mostrarBotonValidar;
	private Boolean mostrarPrimeraGrilla;
	private Boolean mostrarSegundaGrilla;

	private String msjDialog;
	
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoINCRecepcionarTransaccionesPagoConcursoForm f = new ProcesoINCRecepcionarTransaccionesPagoConcursoForm();
		return f;
	}

	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {

		ProcesoINCRecepcionarTransaccionesPagoConcursoForm f = (ProcesoINCRecepcionarTransaccionesPagoConcursoForm) this.formProceso;
		f.setFechaFacturacion(DateUtil.convertDateToString(f.getFechaFacturacionDate()));
		
		params = super.prepareParamsBeforeExecute(params, form);

		return params;
	}
	
	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		ProcesoINCRecepcionarTransaccionesPagoConcursoForm f = (ProcesoINCRecepcionarTransaccionesPagoConcursoForm) this.formProceso;
		ProcesoINCRecepcionarTransaccionesPagoConcursoService service = 
				(ProcesoINCRecepcionarTransaccionesPagoConcursoService) getBean("spusicc.procesoINCRecepcionarTransaccionesPagoConcursoService");
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		params.put("codigoUsuario",usuario.getLogin());
		
		service.executeActualizarTransaccionesPagoConcurso(params);
		
		f.setNumeroCarga((String)params.get("numeroCarga"));
		
		return params;
	
	}
	
	@Override
	protected void afterExecuteProcessSuccess() {
		limpiara();
		
		ProcesoINCRecepcionarTransaccionesPagoConcursoForm f = (ProcesoINCRecepcionarTransaccionesPagoConcursoForm) this.formProceso;
		f.setFlagBotonValidar(false);
		f.setFlagBotonActualizar(false);
	}
	
	@Override
	protected void setViewAtributes() throws Exception {

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		limpiara();
		
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		
		ProcesoINCRecepcionarTransaccionesPagoConcursoForm f = (ProcesoINCRecepcionarTransaccionesPagoConcursoForm) this.formProceso;
			//seteamos la ruta temporal dodne guardar el excel
		ProcesoZONActualizarUnidadesGeograficasService serviceUnidad = (ProcesoZONActualizarUnidadesGeograficasService) getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");
		f.setDirectorioTemporal(serviceUnidad.obtenerPathUpload(pais.getCodigo()));
		
		ProcesoINCRecepcionarTransaccionesPagoConcursoService service = (ProcesoINCRecepcionarTransaccionesPagoConcursoService) 
				getBean("spusicc.procesoINCRecepcionarTransaccionesPagoConcursoService");

		//Obtenemos los concursos para Migracion de Puntos
		List listPagos = service.getListPagoConcurso();
		this.incPagoConcursoList = listPagos;
		
		f.setCodigoPais(pais.getCodigo());
		f.setNumeroCarga("0");
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
		MantenimientoOCRPedidoControlFacturacionService serviceOCR = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceOCR.getControlFacturacionById(criteria);

		// Carga el periodo actual y fecha de proceso
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		f.setFechaFacturacion(controlFacturacion.getFechaProceso());
		f.setFechaFacturacionDate(DateUtil.convertStringToDate(f.getFechaFacturacion()));
		
		//Obtenemos oid Pais
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) 
				getBean("spusicc.mantenimientoMAEClienteService");
		criteria.put("codigoPais", f.getCodigoPais());
		String oidPais = clienteService.getOidPais(criteria);
		f.setOidPais(oidPais);
				
		f.setFlagBotonValidar(false);
		f.setFlagBotonActualizar(false);
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

		ProcesoINCRecepcionarTransaccionesPagoConcursoForm f = (ProcesoINCRecepcionarTransaccionesPagoConcursoForm) this.formProceso;
		
		//obtenemos el service
		ProcesoINCRecepcionarTransaccionesPagoConcursoService service = (ProcesoINCRecepcionarTransaccionesPagoConcursoService) 
														getBean("spusicc.procesoINCRecepcionarTransaccionesPagoConcursoService");
		
		this.setAttachment(event.getFile().getFileName());
		
		// Cargamos el archivo de la maquina del cliente al servidor
		UploadedFile archi = event.getFile();
		uploadArchivo(archi);
		
		// Obtenemos la extension del archivo
		String extensionArchivo = obtenerExtensionArchivo(f.getNombreArchivo());
		f.setExtensionArchivo(extensionArchivo);
		
		Map criteria = new HashMap();
		criteria.put("directorioTemporal", f.getDirectorioTemporal());
		criteria.put("nombreArchivo", f.getNombreArchivo());
		criteria.put("usuario", usuario);

		//validamos el archivo excel y en criteria mandamos que estructura es sin period o con periodo
		Map resultados = service.cargarArchivoExcel(criteria);
		f.setNumeroCarga((String)resultados.get("numeroCarga"));
		f.setNumRegistros((String)resultados.get("totalRegistros"));
		
		f.setNumRegistrosError(Constants.NUMERO_CERO);
		f.setNumRegistrosValido(Constants.NUMERO_CERO);

		this.incTransaccionPagoConcursoList = new ArrayList();
		
		borrarFichero(f.getDirectorioTemporal(), f.getNombreArchivo());
		this.viewValida = true;// flag para mostrar el resultado de la validacion
		
		f.setFlagBotonValidar(true);
		f.setFlagBotonActualizar(false);
		
		this.mostrarBotonValidar = true;
		
		validar(null);
	}
	
	public void validar(ActionEvent actionEvent) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'validar' method");
		}
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario= this.mPantallaPrincipalBean.getCurrentUser();
		ProcesoINCRecepcionarTransaccionesPagoConcursoForm f = (ProcesoINCRecepcionarTransaccionesPagoConcursoForm) this.formProceso;
				
		Map params = BeanUtils.describe(f);
		
		//obtenemos el service
		ProcesoINCRecepcionarTransaccionesPagoConcursoService service = (ProcesoINCRecepcionarTransaccionesPagoConcursoService) 
												getBean("spusicc.procesoINCRecepcionarTransaccionesPagoConcursoService");
		
		//validamos los datos cargados del archivo excel
		List resultados = new ArrayList(); //service.executeValidarRecepcionarTransaccionesPagoConcurso(params);
		int totalErrores = resultados.size();
		int totalValidos = Integer.parseInt(f.getNumRegistros()) - totalErrores;
		
		f.setNumRegistrosError(String.valueOf(totalErrores));
		f.setNumRegistrosValido(String.valueOf(totalValidos));

		this.incTransaccionPagoConcursoList = resultados;
		
		f.setFlagBotonValidar(false);
		f.setFlagBotonActualizar(true);
		
	}
	
	/**
	 * carga el archivo
	 * 
	 */
	private void uploadArchivo(UploadedFile archivo) throws Exception {
		ProcesoINCRecepcionarTransaccionesPagoConcursoForm f = (ProcesoINCRecepcionarTransaccionesPagoConcursoForm) this.formProceso;
		
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
		
		this.incTransaccionPagoConcursoList =null;
		this.attachment = "";		
		this.mostrarPrimeraGrilla = false;
		this.mostrarSegundaGrilla = false;
		this.mostrarBotonExecute = false;
		this.mostrarBotonValidar = false;
		this.viewValida = false;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {

		String msj = null;
		
		ProcesoINCRecepcionarTransaccionesPagoConcursoForm f = (ProcesoINCRecepcionarTransaccionesPagoConcursoForm) this.formProceso;
		
		if(Integer.parseInt(f.getNumRegistrosError())>0){
			this.msjDialog = this.getResourceMessage("procesoINCRecepcionarTransaccionesPagoConcursoForm.process.valido.errores");
			
		}
		else{
			this.msjDialog = this.getResourceMessage("procesoINCRecepcionarTransaccionesPagoConcursoForm.process.validoo");
		}
		
		return msj;
		
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
	 * @return the incTransaccionPagoConcursoList
	 */
	public List getIncTransaccionPagoConcursoList() {
		return incTransaccionPagoConcursoList;
	}

	/**
	 * @param incTransaccionPagoConcursoList the incTransaccionPagoConcursoList to set
	 */
	public void setIncTransaccionPagoConcursoList(
			List incTransaccionPagoConcursoList) {
		this.incTransaccionPagoConcursoList = incTransaccionPagoConcursoList;
	}

	/**
	 * @return the incPagoConcursoList
	 */
	public List getIncPagoConcursoList() {
		return incPagoConcursoList;
	}

	/**
	 * @param incPagoConcursoList the incPagoConcursoList to set
	 */
	public void setIncPagoConcursoList(List incPagoConcursoList) {
		this.incPagoConcursoList = incPagoConcursoList;
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
	
}

