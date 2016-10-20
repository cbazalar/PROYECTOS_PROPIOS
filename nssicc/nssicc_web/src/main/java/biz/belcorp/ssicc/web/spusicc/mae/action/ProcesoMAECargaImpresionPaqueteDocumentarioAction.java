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

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.mae.ProcesoMAECargaImpresionPaqueteDocumentarioService;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.mae.form.ProcesoMAECargaImpresionPaqueteDocumentarioForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"unchecked","rawtypes"})
public class ProcesoMAECargaImpresionPaqueteDocumentarioAction extends BaseProcesoAbstractAction {

	private static final long serialVersionUID = -2459957524245036374L;
	private List siccTipoClienteList;
	private LabelValue[] siccRegionList;
	private LabelValue[] siccSubTipoClienteList;
	private LabelValue[] siccTipoClasificacionList;
	private LabelValue[] siccClasificacionList;
	private LabelValue[] siccZonaList;
	
	private String codigoIdiomaISO;
	private List maeImpresionPaqDocArchivoList;
	private String viewValida;
	private String  attachment = "";
	
	private Boolean mostrarPrimeraGrilla;
	private Boolean mostrarSegundaGrilla;
	private boolean indicadorImprimirPaqDoc;
	private Boolean mostrarPrimero;
	private Boolean mostrarSegundo;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#devuelveFormProceso()
	 */
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoMAECargaImpresionPaqueteDocumentarioForm K = new ProcesoMAECargaImpresionPaqueteDocumentarioForm();
		return K;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#executeProcess(java.util.Map)
	 */
	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {

		ProcesoMAECargaImpresionPaqueteDocumentarioService service = (ProcesoMAECargaImpresionPaqueteDocumentarioService) 
															getBean("spusicc.procesoMAECargaImpresionPaqueteDocumentarioService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		params.put("codigoUsuario", usuario.getLogin());
		service.executeActualizarCargaImpresionPaqueteDocumentario(params);
		
		//---
		this.mostrarPanelAdicionalProceso = false;
		this.mostrarSegundaGrilla = false;
				
		return params;	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#afterExecuteProcess(biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm)
	 */
	@Override
	public void afterExecuteProcess(BaseProcesoForm form) throws Exception {
		super.afterExecuteProcess(form);

		//limpiando el flag de validacion de archivo
		this.viewValida = "";
//		this.maeImpresionPaqDocArchivoList = null;
		
		ProcesoMAECargaImpresionPaqueteDocumentarioForm f = (ProcesoMAECargaImpresionPaqueteDocumentarioForm) this.formProceso;
		f.setFlagBotonValidar(false);
		f.setFlagBotonActualizar(false);
		
		recargarTipologiaClientes(f);
		
//		addInfo("Mensaje", getReportResourceMessage("procesoMAECargaImpresionPaqueteDocumentarioForm.proceso.ok"));
	
	}
	
	public void cambiandoDevista(ValueChangeEvent evt){
		
		String valor = evt.getNewValue().toString();
		
		if(valor.equals("1")){
			this.mostrarPrimero = true;
			this.mostrarSegundo = false;			
		}else{
			this.mostrarPrimero= false;
			this.mostrarSegundo = true;
		}		
	}
	
	public void inicializar(){
		this.mostrarPrimero = true;
		this.mostrarSegundo = false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception 
	{	
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
				
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		limpiara();
		
		this.mostrarBotonExecute = false;
		
		ProcesoMAECargaImpresionPaqueteDocumentarioForm f = (ProcesoMAECargaImpresionPaqueteDocumentarioForm) this.formProceso;
			//seteamos la ruta temporal dodne guardar el excel
		ProcesoZONActualizarUnidadesGeograficasService serviceUnidad = (ProcesoZONActualizarUnidadesGeograficasService) getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");
		f.setDirectorioTemporal(serviceUnidad.obtenerPathUpload(pais.getCodigo()));
		this.inicializar();
		//cargando en session la lista de tipos de clientes
		
		this.siccTipoClienteList = interfazSiCCService.getTiposClientesByCodigoISOOID(usuario.getIdioma().getCodigoISO());
		
		f.setOidSubTipoCliente("TODOS");
		f.setOidTipoCliente("TODOS");
		f.setOidTipoClasificacion("TODOS");
		f.setOidClasificacion("TODOS");
		
		//cargando en session la lista de regiones
		LabelValue[] listRegiones = aSvc.getRegionesByPaisMarcaCanalDetalle(pais.getCodigo(), "T", "VD", "Region");
		
		this.siccRegionList = listRegiones;

		f.setCodigoRegion("TODOS");
		f.setCodigoZona("TODOS");

		this.siccSubTipoClienteList = new LabelValue[]{};
			
		f.setCodigoPais(pais.getCodigo());
		recargarTipologiaClientes(f);
		
		this.codigoIdiomaISO =  usuario.getIdioma().getCodigoISO();
				
		f.setFlagBotonValidar(false);
		f.setFlagBotonActualizar(false);
		f.setTipoCarga("1");
	}
	
	private void recargarTipologiaClientes ( ProcesoMAECargaImpresionPaqueteDocumentarioForm f) 
	{
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		ArrayList temp = new ArrayList();
		if(!f.getOidTipoCliente().equals("TODOS")) {
			temp.add(f.getOidTipoCliente());
			this.siccSubTipoClienteList = aSvc.getSubTiposClientesPorPaisTipoClientesOID(usuario.getIdioma().getCodigoISO(), temp);
		}	

		if(!f.getOidSubTipoCliente().equals("TODOS")) {
			temp = new ArrayList();
			temp.add(f.getOidSubTipoCliente());
			LabelValue[] listTiposClasificiones = aSvc.getTiposClasificacionesByCriteriaMultipleOID(usuario.getIdioma().getCodigoISO(), 
					Constants.OID_TIPO_CLIENTE_DEFAULT, temp);
			this.siccTipoClasificacionList = listTiposClasificiones;
		}
		
		if(!f.getOidTipoClasificacion().equals("TODOS")) {
			ArrayList temp2 = new ArrayList();
			temp2 = new ArrayList();
			temp2.add(f.getOidTipoClasificacion());
			this.siccClasificacionList = aSvc.getClasificacionesByCriteriaMultipleOID(usuario.getIdioma().getCodigoISO(), Constants.OID_TIPO_CLIENTE_DEFAULT, temp, temp2);
		}	
		
		if(!f.getCodigoRegion().equals("TODOS")) {
			ArrayList temp3 = new ArrayList();
			temp3 = new ArrayList();
			temp3.add(f.getCodigoRegion());
			this.siccZonaList = aSvc.getZonasMultipleByPaisMarcaCanalRegionDetalle(f.getCodigoPais(), "T", "VD", temp3, "", "Zona");
				
		}	
	}
	
	/**
	 * Carga el archivo excel que viene del request e inserta su información en tabla
	 */
	public void cargar(FileUploadEvent event) throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'cargar' method");
		}

		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();

		ProcesoMAECargaImpresionPaqueteDocumentarioForm f = (ProcesoMAECargaImpresionPaqueteDocumentarioForm) this.formProceso;
		recargarTipologiaClientes(f);
		
		//obtenemos el service
		ProcesoMAECargaImpresionPaqueteDocumentarioService service = (ProcesoMAECargaImpresionPaqueteDocumentarioService) 
												getBean("spusicc.procesoMAECargaImpresionPaqueteDocumentarioService");
		
		this.setAttachment(event.getFile().getFileName());
		UploadedFile archi =event.getFile();
		// Cargamos el archivo de la maquina del cliente al servidor
		uploadArchivo(archi);
		f.setArchivo(archi);
				
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
				
		if(f.getNumeroCarga()!= null)
			this.mostrarPrimeraGrilla = true;
		
		this.maeImpresionPaqDocArchivoList = new ArrayList();
				
		borrarFichero(f.getDirectorioTemporal(), f.getNombreArchivo());
			
		this.viewValida = Constants.NUMERO_UNO; //flag para mostrar el resultado de la validacion
		
		f.setFlagBotonValidar(true);
		f.setFlagBotonActualizar(false);
		
		// validamos la carga
		validar();		
	}
	
	/**
	 * carga el archivo
	 */
	public void uploadArchivo(UploadedFile form) throws Exception 
	{
		ProcesoMAECargaImpresionPaqueteDocumentarioForm f = (ProcesoMAECargaImpresionPaqueteDocumentarioForm) this.formProceso;
		
		recargarTipologiaClientes(f);
		// recuperamos el fichero
		f.setNombreArchivo(form.getFileName());
		log.debug("Nombre Archivo Upload: " + f.getNombreArchivo());
		// leyemos el stream de entrada
		InputStream is = form.getInputstream();
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
	 * obtiene la extension del archiv
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
		}	
		catch(Exception ex) {
			log.debug("No se pudo eliminar el archivo "+ex.getMessage());
		}
	}

	/**
	 * Carga el archivo excel que viene del request e inserta su informacion en tabla
	 */
	public void validar() throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'validar' method");
		}

		ProcesoMAECargaImpresionPaqueteDocumentarioForm f = (ProcesoMAECargaImpresionPaqueteDocumentarioForm) this.formProceso;
		recargarTipologiaClientes(f);
		
		if (this.indicadorImprimirPaqDoc)
			f.setIndicadorImprimirPaqDoc("1");
		else
			f.setIndicadorImprimirPaqDoc("0");
		
		Map params = BeanUtils.describe(f);
		
		//obtenemos el service
		ProcesoMAECargaImpresionPaqueteDocumentarioService service = (ProcesoMAECargaImpresionPaqueteDocumentarioService) 
												getBean("spusicc.procesoMAECargaImpresionPaqueteDocumentarioService");

		if(f.getTipoCarga().equals("2")) { //CONDICIONAL
			f.setNombreArchivo("");
			service.executeInsertarCargaImpresionPaqueteDocumentario(params);

			f.setNumeroCarga((String)params.get("numeroCarga"));
			f.setNumRegistros(params.get("numeroRegistros").toString());
			f.setNumRegistrosError(Constants.NUMERO_CERO);
			f.setNumRegistrosValido(Constants.NUMERO_CERO);
			this.viewValida = Constants.NUMERO_UNO; //flag para mostrar el resultado de la validacion
			this.mostrarPrimeraGrilla = true;
			this.mostrarSegundaGrilla = true;
		}
		
		//validamos los datos cargados del archivo excel
		List resultados = service.executeValidarCargaImpresionPaqueteDocumentario(params);
		int totalErrores = resultados.size();
		int totalValidos = Integer.parseInt(f.getNumRegistros()) - totalErrores;
		
		f.setNumRegistrosError(String.valueOf(totalErrores));
		f.setNumRegistrosValido(String.valueOf(totalValidos));
		
		this.maeImpresionPaqDocArchivoList = resultados;
		if(this.maeImpresionPaqDocArchivoList != null)
			this.mostrarSegundaGrilla = true;
		
		f.setFlagBotonValidar(false);
		f.setFlagBotonActualizar(true);	
		this.mostrarPanelAdicionalProceso = true;
	}

	/**
	 * Carga el la lista SubTipoClientes
	 */
	public void loadSubTiposClientes(ValueChangeEvent val) {
    	try {
    		ArrayList values=new ArrayList(); 
    		String valor = (String)val.getNewValue();
        	values.add(valor);
       	
        	if(valor=="TODOS") {
        		
        		this.siccSubTipoClienteList = null;
                this.siccTipoClasificacionList = null;
                this.siccClasificacionList = null;
        	} else {
        		AjaxService ajax = (AjaxService) getBean("ajaxService");
        		this.siccSubTipoClienteList = ajax.getSubTiposClientesPorPaisTipoClientesOID( codigoIdiomaISO, values);
        	}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}			
	}
	
	public void loadTiposClasificaciones(ValueChangeEvent val) {    	
		try {
		 	ProcesoMAECargaImpresionPaqueteDocumentarioForm p = (ProcesoMAECargaImpresionPaqueteDocumentarioForm) this.formProceso;
			ArrayList values=new ArrayList();
	    	values.add((String)val.getNewValue());
	    	
	    	AjaxService ajax = (AjaxService) getBean("ajaxService");
	    	this.siccTipoClasificacionList = ajax.getTiposClasificacionesByCriteriaMultipleOID(codigoIdiomaISO, p.getOidTipoCliente(), values);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}   
	}  
	
	public void loadClasificaciones(ValueChangeEvent val) {
		try {
			ProcesoMAECargaImpresionPaqueteDocumentarioForm p = (ProcesoMAECargaImpresionPaqueteDocumentarioForm) this.formProceso;
			ArrayList valuesSubTipoCliente=new ArrayList();    	
			valuesSubTipoCliente.add(p.getOidSubTipoCliente());
			
			ArrayList valuesClasificacion=new ArrayList();
			valuesClasificacion.add((String)val.getNewValue());
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			this.siccClasificacionList = ajax.getClasificacionesByCriteriaMultipleOID(codigoIdiomaISO, p.getOidTipoCliente(), valuesSubTipoCliente, valuesClasificacion);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}
		
	public void loadZonas(ValueChangeEvent val) {
		try {
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			
//			var regions = document.getElementById("codigoRegion");
	    	String regions = val.getNewValue().toString();
	    	ArrayList values=new ArrayList();     	
	    	values.add(regions);
	    	
	    	AjaxService ajax = (AjaxService) getBean("ajaxService");				
	      	this.siccZonaList = ajax.getZonasMultipleByPaisMarcaCanalRegionDetalle(pais.getCodigo(), "T", "VD", values, "", "Zona");
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}
		
	public void previoGuardar(ActionEvent event)
	{
		try {
			ProcesoMAECargaImpresionPaqueteDocumentarioForm p = (ProcesoMAECargaImpresionPaqueteDocumentarioForm) this.formProceso;
			int registrosError = StringUtils.isNotBlank(p.getNumRegistrosError())? Integer.parseInt(p.getNumRegistrosError()): 0;
			
			RequestContext context = RequestContext.getCurrentInstance(); 
			String ventana = "confirmDialogProceso";
			String retornoMensaje = "";
			
			if(registrosError > 0 )
			{
				retornoMensaje = this.getResourceMessage("procesoMAECargaImpresionPaqueteDocumentarioForm.process.valido.errores");
					
				context.addCallbackParam("retornoMensaje", retornoMensaje);
				
				String ventanaConfirmar = "PF('" + ventana + "_confirmationDialogConfirmar').show()";
				this.getRequestContext().execute(ventanaConfirmar);
			}else
			{
				retornoMensaje = this.getResourceMessage("procesoMAECargaImpresionPaqueteDocumentarioForm.process.valido");
				context.addCallbackParam("retornoMensaje", retornoMensaje);
				
				String ventanaConfirmar = "PF('" + ventana + "_confirmationDialogConfirmar').show()";
				this.getRequestContext().execute(ventanaConfirmar);
			}			 
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	/**
	 * 
	 */
	public void limpiara() 
	{
		this.maeImpresionPaqDocArchivoList =null;
		this.attachment = "";
		this.mostrarPrimeraGrilla = false;
		this.mostrarSegundaGrilla = false;		
	}
	
	/**
	 * @return
	 */
	public List getSiccTipoClienteList() {
		return siccTipoClienteList;
	}

	/**
	 * @param siccTipoClienteList
	 */
	public void setSiccTipoClienteList(List siccTipoClienteList) {
		this.siccTipoClienteList = siccTipoClienteList;
	}

	/**
	 * @return
	 */
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 */
	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return
	 */
	public LabelValue[] getSiccSubTipoClienteList() {
		return siccSubTipoClienteList;
	}

	/**
	 * @param siccSubTipoClienteList
	 */
	public void setSiccSubTipoClienteList(LabelValue[] siccSubTipoClienteList) {
		this.siccSubTipoClienteList = siccSubTipoClienteList;
	}

	/**
	 * @return
	 */
	public LabelValue[] getSiccTipoClasificacionList() {
		return siccTipoClasificacionList;
	}

	/**
	 * @param siccTipoClasificacionList
	 */
	public void setSiccTipoClasificacionList(LabelValue[] siccTipoClasificacionList) {
		this.siccTipoClasificacionList = siccTipoClasificacionList;
	}

	/**
	 * @return
	 */
	public LabelValue[] getSiccClasificacionList() {
		return siccClasificacionList;
	}

	/**
	 * @param siccClasificacionList
	 */
	public void setSiccClasificacionList(LabelValue[] siccClasificacionList) {
		this.siccClasificacionList = siccClasificacionList;
	}

	/**
	 * @return
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
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
	public List getMaeImpresionPaqDocArchivoList() {
		return maeImpresionPaqDocArchivoList;
	}

	/**
	 * @param maeImpresionPaqDocArchivoList
	 */
	public void setMaeImpresionPaqDocArchivoList(List maeImpresionPaqDocArchivoList) {
		this.maeImpresionPaqDocArchivoList = maeImpresionPaqDocArchivoList;
	}

	/**
	 * @return
	 */
	public String getViewValida() {
		return viewValida;
	}

	/**
	 * @param viewValida
	 */
	public void setViewValida(String viewValida) {
		this.viewValida = viewValida;
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
	 * @return
	 */
	public boolean isIndicadorImprimirPaqDoc() {
		return indicadorImprimirPaqDoc;
	}

	/**
	 * @param indicadorImprimirPaqDoc
	 */
	public void setIndicadorImprimirPaqDoc(boolean indicadorImprimirPaqDoc) {
		this.indicadorImprimirPaqDoc = indicadorImprimirPaqDoc;
	}

	/**
	 * @return
	 */
	public Boolean getMostrarPrimero() {
		return mostrarPrimero;
	}

	/**
	 * @param mostrarPrimero
	 */
	public void setMostrarPrimero(Boolean mostrarPrimero) {
		this.mostrarPrimero = mostrarPrimero;
	}

	/**
	 * @return
	 */
	public Boolean getMostrarSegundo() {
		return mostrarSegundo;
	}

	/**
	 * @param mostrarSegundo
	 */
	public void setMostrarSegundo(Boolean mostrarSegundo) {
		this.mostrarSegundo = mostrarSegundo;
	}
}
