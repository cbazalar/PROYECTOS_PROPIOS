package biz.belcorp.ssicc.web.spusicc.zon.action;

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

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesAdministrativasService;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasService;
import biz.belcorp.ssicc.service.util.StringUtil;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.zon.form.ProcesoZONModificarTerritorioConsultorasForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"unchecked","rawtypes"})
public class ProcesoZONModificarTerritorioConsultorasAction extends BaseProcesoAbstractAction {

	private static final long serialVersionUID = -2032341246469689711L;
	
	private List siccMarcaList;
	private List siccCanalList;
	private List siccPerioodoCorporativoList;
	private List siccRegionList;
	private List zonUnidadAdminConsuList;
	
	private Boolean mostrarPrimeraGrilla;
	private Boolean mostrarSegundaGrilla;
	
	private String attachment = "";
	
	
	@ManagedProperty(value = "#{reporteZONTerritorioConsultorasAction}")
	private ReporteZONTerritorioConsultorasAction reporteZONTerritorioConsultorasAction;
	
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoZONModificarTerritorioConsultorasForm p = new ProcesoZONModificarTerritorioConsultorasForm();
		return p;
	}
	
	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'executeProcess' method"); 
		}

		ProcesoZONModificarTerritorioConsultorasForm f = (ProcesoZONModificarTerritorioConsultorasForm)this.formProceso;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		// obtenemos el servicio de Zon Unidades Administrativas
		ProcesoZONActualizarUnidadesAdministrativasService service = 
				(ProcesoZONActualizarUnidadesAdministrativasService)getBean("spusicc.procesoZONUniAdmService");
		params.put("codigoPais", pais.getCodigo());
		params.put("codigoMarca", f.getCodigoMarca());
		params.put("codigoCanal", f.getCodigoCanal());
		params.put("codigoPeriodo", f.getPeriodoCorporativo());
		params.put("codigoUsuario", f.getCodigoUsuario());  
		
		//Se Actualiza las consultoras y territorios recibidos del archivo excel
		service.execActualizarModTerritorioConsultoras(params);
		f.setEjecutaReporte("s");
	
		return params;
	
	}
	public void ejecutarReporteP(ActionEvent evt) {
		try {
			ProcesoZONModificarTerritorioConsultorasForm f=(ProcesoZONModificarTerritorioConsultorasForm)this.formProceso;
			//this.reporteZONTerritorioConsultorasAction.devuelveNombreReporte();
			this.reporteZONTerritorioConsultorasAction.setFormatoReporte("XLS");
			this.reporteZONTerritorioConsultorasAction.setFormatoExportacion("XLS");
			this.reporteZONTerritorioConsultorasAction.getFormReporte().setFormatoExportacion("XLS");
			this.reporteZONTerritorioConsultorasAction.executeReporte();

			// this.redireccionarPagina("reporteLECCargaDatosFormatosForm");s
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'view' method");
		}
		ProcesoZONModificarTerritorioConsultorasForm f = (ProcesoZONModificarTerritorioConsultorasForm) this.formProceso;
		f.setEjecutaReporte("n");
		this.mostrarBotonExecute = false;
		this.limpiara();
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		if(f.getCodigoCanal()==null) 
			f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		if(f.getCodigoMarca()==null) 
			f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		if(f.getPeriodoCorporativo()==null) 
			f.setPeriodoCorporativo(service.getPeriodoDefaultByPaisCanal(pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT));

		ProcesoZONActualizarUnidadesGeograficasService service2 = (ProcesoZONActualizarUnidadesGeograficasService) getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");
		f.setDirectorioTemporal(service2.obtenerPathUpload(pais.getCodigo()));
		
		this.siccMarcaList = reporteService.getMarcas();
		this.siccCanalList = reporteService.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		this.siccPerioodoCorporativoList = reporteService.getPeriodosCorporativosPorTipo(Constants.TIPO_PERIOODO_CORPORATIVO_CAMPANIA);

		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		criteriaOperacion.put("codigoMarca", f.getCodigoMarca());
		criteriaOperacion.put("codigoCanal", f.getCodigoCanal());
		
		this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais", criteriaOperacion);
		
		f.setTotalError(new Integer(0));
		f.setTotalRegistros(new Integer(0));
		f.setTotalOK(new Integer(0));
		
		
	}
	

	/**
	 * Accion que valida las regiones seleccionadas no hayan cerrado en el periodo indicado y
     * que las regiones no hayan iniciado su facturacion
	 * 
	 * 
	 */
	public void validarRegion()
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'validarRegion' method");
		}
		
		ProcesoZONActualizarUnidadesAdministrativasService service = (ProcesoZONActualizarUnidadesAdministrativasService)getBean("spusicc.procesoZONUniAdmService");
		ProcesoZONModificarTerritorioConsultorasForm f = (ProcesoZONModificarTerritorioConsultorasForm) this.formProceso;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		if(f.getCodigosRegion()== null)
		{	
			String mensaje = "Indique al menos una región";
			addInfo("Mensaje: ", mensaje);
			return;
		}
		
		try{
		Map params = new HashMap();
		params.put("codigoPais", pais.getCodigo());
		params.put("codigoMarca", f.getCodigoMarca());
		params.put("codigoCanal", f.getCodigoCanal());
		params.put("codigoPeriodo", f.getPeriodoCorporativo());
		params.put("codigosRegion", f.getCodigosRegion());
		params.put("validarRegionCerrada", "1");
		params.put("codigoUsuario",usuario.getLogin());
		f.setCodigoUsuario(usuario.getLogin());
		
		// Invocar service
		service.validarRegiones(params);
		String codRetorno = (String)params.get("codRetorno");
		
		if(codRetorno!=null && !"".equals(codRetorno)){
			String msgRetorno = (String) params.get("msgRetorno");
			
			addError("Error: ", getResourceMessage("procesoZONModificarTerritorioConsultorasForm.errorValidarRegiones"+msgRetorno));
		}
		
		f.setTotalError(new Integer(0));
		f.setTotalRegistros(new Integer(0));
		f.setTotalOK(new Integer(0));
		f.setEjecutaReporte("n");
		
		this.redireccionarPagina("procesoZONCargarConsultorasUploadForm");
		}
		catch(Exception e)
		{
			 this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		
		return;
	}
	
	/**
	 * Ejecuta Proceso de Validacion y Carga de Datos del Excel
	 * 
	 */
	public void load(FileUploadEvent event)
				throws Exception {	
		if (log.isDebugEnabled()) {
			log.debug("Entering 'executeProcess' method"); 
		}

		ProcesoZONModificarTerritorioConsultorasForm f = (ProcesoZONModificarTerritorioConsultorasForm)this.formProceso;
		Map params = new HashMap();
		UploadedFile archivo = event.getFile();
		f.setArchivo(archivo);
		f.setNombreArchivo(archivo.getFileName());
		this.zonUnidadAdminConsuList = new ArrayList();
		//Obtenemos la extension del archivo
		String extensionArchivo = obtenerExtensionArchivo(f.getNombreArchivo());
		f.setExtensionArchivo(extensionArchivo);
		
		try
		{
		/* cargamos archivo a la BD */
		params.put("codigoPais", f.getCodigoPais());
		params.put("codigoPeriodo", f.getPeriodoCorporativo());
		params.put("codigoMarca", f.getCodigoMarca());
		params.put("codigoCanal", f.getCodigoCanal());
		params.put("directorioTemporal", f.getDirectorioTemporal());
		params.put("nombreArchivo", f.getNombreArchivo());
		params.put("extensionArchivo", f.getExtensionArchivo());
		params.put("codigoRegiones", StringUtil.obtieneListaCodigos(f.getCodigosRegion(), "", ""));
		params.put("codigoUsuario",f.getCodigoUsuario());
		
		
		log.debug("codigoRegiones: " + StringUtil.obtieneListaCodigos(f.getCodigosRegion(), "", ""));
		this.uploadArchivo(f);
		
		this.setAttachment(event.getFile().getFileName());
		
		if(f.getArchivo().getFileName()!=null)
			this.mostrarPrimeraGrilla = true;
		
		this.validarDatosExcel(params, f);
		validar();
		}
		catch(Exception e)
		{
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * Cargar archivo del cliente al servidor. 
	 * 
	 * @param form
	 * @throws Exception
	 */
	private void uploadArchivo(ProcesoZONModificarTerritorioConsultorasForm f) throws Exception {
		

		// recuperamos el fichero
		UploadedFile archivo = f.getArchivo();

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

		
		// obtenemos el servicio de Zon Unidades Administrativas
		ProcesoZONActualizarUnidadesAdministrativasService service = 
				(ProcesoZONActualizarUnidadesAdministrativasService)getBean("spusicc.procesoZONUniAdmService");

		// Borramos las tablas respecto a Consultoras y Territorios
		service.deleteTerritorioConsultoraTmp();
		Map params = new HashMap();		
		params.put("codigoPais", f.getCodigoPais());
		params.put("codigoPeriodo", f.getPeriodoCorporativo());
		params.put("codigoMarca", f.getCodigoMarca());
		params.put("codigoCanal", f.getCodigoCanal());
		params.put("directorioTemporal", f.getDirectorioTemporal());
		params.put("nombreArchivo", f.getNombreArchivo());
		params.put("extensionArchivo", f.getExtensionArchivo());
		params.put("codigoRegiones", StringUtil.obtieneListaCodigos(f.getCodigosRegion(), "", ""));
		params.put("codigoUsuario",f.getCodigoUsuario());  
				
		// Cargamos los datos del archivo a tabla de BD
		service.insertTerritorioConsultora(params);
	
		
	}
	
	/**
	 * Realiza la validacion del archivo cargado del Excel
	 * 
	 */
	private Map validarDatosExcel( Map params, ProcesoZONModificarTerritorioConsultorasForm f) {
		// obtenemos el servicio de Zon Unidades Administrativas
		ProcesoZONActualizarUnidadesAdministrativasService service = 
					(ProcesoZONActualizarUnidadesAdministrativasService)getBean("spusicc.procesoZONUniAdmService");
		log.debug("Entering 'validarDatosExcel' method");	
		log.debug("codigoRegiones: " + StringUtil.obtieneListaCodigos(f.getCodigosRegion(), "", ""));
		//Se realiza la validaciones de las consultoras y territorios recibidos del archivo excel
		service.execValidacionesModTerritorioConsultoras(params);

		String codRetorno = (String) params.get("codRetorno");
		params.put("error", "");
		
		if(codRetorno!=null && !"".equals(codRetorno.trim()) && !Constants.NUMERO_CERO.equals(codRetorno.trim())){
			String keyError = "";
			String msgRetorno = (String) params.get("msgRetorno");
			
			if(codRetorno.equals("1")){
				keyError = "procesoZONModificarTerritorioConsultorasForm.consultoras.msg.clienteNoExiste";
			} else if(codRetorno.equals("2")){
				keyError = "procesoZONModificarTerritorioConsultorasForm.consultoras.msg.territorioNoExiste";
			} else if(codRetorno.equals("3")){
				keyError = "procesoZONModificarTerritorioConsultorasForm.consultoras.msg.territorioInactivo";
			} else if(codRetorno.equals("4")){
				keyError = "procesoZONModificarTerritorioConsultorasForm.consultoras.msg.seccionActiva";
			} else if(codRetorno.equals("5")){
				keyError = "procesoZONModificarTerritorioConsultorasForm.consultoras.msg.regionDestino";
			} else if(codRetorno.equals("6")){
				keyError = "procesoZONModificarTerritorioConsultorasForm.consultoras.msg.regionConsultora";
			} else if(codRetorno.equals("7")){
				keyError = "procesoZONModificarTerritorioConsultorasForm.consultoras.msg.consultoraFacturo";
			} else if(codRetorno.equals("8")){
				keyError = "procesoZONModificarTerritorioConsultorasForm.consultoras.msg.consultoraSinUAActiva";
			} 
			getResourceMessage(keyError);
			
//			addInfo("Error: ", getReportResourceMessage(msgRetorno+" && "+keyError));
		}
		
		this.zonUnidadAdminConsuList = service.getUnidadAdministrativaConsultora(params);
		Integer totalRegistros = service.getUnidadAdministrativaConsultoraTotal(params);
		Integer totalError = service.getUnidadAdministrativaConsultoraTotalError(params);
		Integer totalOK = service.getUnidadAdministrativaConsultoraTotalOK(params);
		f.setTotalRegistros(totalRegistros);
		f.setTotalError(totalError);
		f.setTotalOK(totalOK);
		f.setEjecutaReporte("n");
		return params;
	}
	
	/**
	 * Action que realiza la validacion del archivo cargado del Excel
	 * 
	 */
	public void validar() throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'validarExcel' method"); 
		}
		try{
		ProcesoZONModificarTerritorioConsultorasForm f = (ProcesoZONModificarTerritorioConsultorasForm) this.formProceso;
		log.debug("codigoRegiones: " + StringUtil.obtieneListaCodigos(f.getCodigosRegion(), "", ""));
		
		Map params = new HashMap();
		params.put("codigoPais", f.getCodigoPais());
		params.put("codigoMarca", f.getCodigoMarca());
		params.put("codigoCanal", f.getCodigoCanal());
		params.put("codigoPeriodo", f.getPeriodoCorporativo());
		params.put("directorioTemporal", f.getDirectorioTemporal());
		params.put("nombreArchivo", f.getNombreArchivo());
		params.put("extensionArchivo", f.getExtensionArchivo());
		params.put("codigoRegiones", StringUtil.obtieneListaCodigos(f.getCodigosRegion(), "", ""));
		params.put("codigoUsuario",f.getCodigoUsuario());
		params = this.validarDatosExcel(params, f);
		
		ProcesoZONActualizarUnidadesAdministrativasService service = 
				(ProcesoZONActualizarUnidadesAdministrativasService)getBean("spusicc.procesoZONUniAdmService");
		this.zonUnidadAdminConsuList = service.getUnidadAdministrativaConsultora(params);
		
		if(zonUnidadAdminConsuList!= null)
			this.mostrarSegundaGrilla = true;
		
		f.setEjecutaReporte("n");
		}
		catch(Exception e)
		{
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		
		}
		
	}
	
	public void regresar()
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'regresarConsultoras' method");
		}
		ProcesoZONModificarTerritorioConsultorasForm f = (ProcesoZONModificarTerritorioConsultorasForm)this.formProceso;
		f.setTotalError(new Integer(0));
		f.setTotalRegistros(new Integer(0));
		f.setTotalOK(new Integer(0));
		
	}
	
	public void limpiara() {
		
		this.zonUnidadAdminConsuList =null;
		this.attachment = "";
		this.mostrarPrimeraGrilla = false;
		this.mostrarSegundaGrilla = false;
		
	}
	
	/**
	 * obtiene la extension del archivo
	 * 
	 */
	private String obtenerExtensionArchivo(String nombreArchivo)
			throws Exception {
		return nombreArchivo.substring(nombreArchivo.length() - 3);
	}
	

	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	public List getSiccCanalList() {
		return siccCanalList;
	}

	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	public List getSiccPerioodoCorporativoList() {
		return siccPerioodoCorporativoList;
	}

	public void setSiccPerioodoCorporativoList(List siccPerioodoCorporativoList) {
		this.siccPerioodoCorporativoList = siccPerioodoCorporativoList;
	}

	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public List getZonUnidadAdminConsuList() {
		return zonUnidadAdminConsuList;
	}

	public void setZonUnidadAdminConsuList(List zonUnidadAdminConsuList) {
		this.zonUnidadAdminConsuList = zonUnidadAdminConsuList;
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

	/**
	 * @return the reporteZONTerritorioConsultorasAction
	 */
	public ReporteZONTerritorioConsultorasAction getReporteZONTerritorioConsultorasAction() {
		return reporteZONTerritorioConsultorasAction;
	}

	/**
	 * @param reporteZONTerritorioConsultorasAction the reporteZONTerritorioConsultorasAction to set
	 */
	public void setReporteZONTerritorioConsultorasAction(
			ReporteZONTerritorioConsultorasAction reporteZONTerritorioConsultorasAction) {
		this.reporteZONTerritorioConsultorasAction = reporteZONTerritorioConsultorasAction;
	}


	
}
