package biz.belcorp.ssicc.web.spusicc.let.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.context.MessageSource;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.let.MantenimientoLETLideresService;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasService;
import biz.belcorp.ssicc.service.util.StringUtil;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.let.form.ProcesoLETCargaObjetivosExcelForm;

@ManagedBean
@SessionScoped
public class ProcesoLETCargaObjetivosExcelAction extends BaseProcesoAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5319969483906071621L;
	
	
	private List siccMarcaList;
	private List siccCanalList;
	private List siccRegionList;
	private List letObjetivosList;
	private List letObjetivosErroresList;
	private String attachment="";  
	private String tipoCarga;
	
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoLETCargaObjetivosExcelForm pro = new ProcesoLETCargaObjetivosExcelForm();
		return pro;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {

		if(log.isDebugEnabled()){
			log.debug("ProcesoLETCargaObjetivosExcelAction - executeProcess");
		}
		
		ProcesoLETCargaObjetivosExcelForm f = (ProcesoLETCargaObjetivosExcelForm) this.formProceso;
		MantenimientoLETLideresService serviceLET = (MantenimientoLETLideresService) getBean("spusicc.mantenimientoLETLideresService");
		
		//List objetivosList = (List)request.getSession().getAttribute(Constants.LET_OBJETIVOS_LIST);
		List objetivosList = letObjetivosList;
		if(f.getTipoCarga().equals("CO1")){
			serviceLET.executeCargaObjetivos(objetivosList);			
		}else if(f.getTipoCarga().equals("CO2")){
			serviceLET.executeCargaObjetivosRetencion22(objetivosList);
		}else if(f.getTipoCarga().equals("CO3") || f.getTipoCarga().equals("CO4")){
			serviceLET.executeCargaObjetivosRetencion3344(objetivosList,f.getTipoCarga());
		}
		
		f.setFlagMostrarErrores(false);
		f.setFlagUpload(true);
		f.setFlagUploadOk(false);
		
		return params;
	
	}
	
	/**
	 * Realiza la carga del archivo excel
	 * @param form
	 */
	public void loadfile(FileUploadEvent event) throws Exception{
		if(log.isDebugEnabled()){
			log.debug("ProcesoLETCargaObjetivosExcelAction - loadfile");
		}
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();		
		
		ProcesoLETCargaObjetivosExcelForm f = (ProcesoLETCargaObjetivosExcelForm) this.formProceso;
		MantenimientoLETLideresService serviceLET = (MantenimientoLETLideresService) getBean("spusicc.mantenimientoLETLideresService");
		
		UploadedFile archi =event.getFile();
		
		uploadArchivo(archi);
		f.setArchivo(archi);
		f.setNombreArchivo(archi.getFileName());
		this.tipoCarga = f.getTipoCarga();
		
		ProcesoZONActualizarUnidadesGeograficasService serviceUnidad = (ProcesoZONActualizarUnidadesGeograficasService) getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");		
		f.setDirectorioTemporal(serviceUnidad.obtenerPathUpload(pais.getCodigo()));
		
		String extensionArchivo = obtenerExtensionArchivo(f.getNombreArchivo());
		f.setExtensionArchivo(extensionArchivo);
		
		this.setAttachment(event.getFile().getFileName());
		
		Map criteria = new HashMap();
		log.debug("directorioTemporal " + f.getDirectorioTemporal());
		
		criteria.put("directorioTemporal", f.getDirectorioTemporal());
		criteria.put("nombreArchivo", f.getNombreArchivo());
		criteria.put("login", usuario.getLogin());
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoMarca", f.getCodigoMarca());
		criteria.put("codigoCanal", f.getCodigoCanal());
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		criteria.put("regionList", f.getCodigoRegion());
		criteria.put("tipoCarga", f.getTipoCarga());
		//criteria.put("indicadorValidaLiderSeccion", f.getIndicadorValidaLiderSeccion());
		
		List objetivosList = new ArrayList();
		List errorObjetivosListList = new ArrayList();
		
		Boolean flagErrorGlobal = Boolean.FALSE;
		boolean flagErrorRepetidos = false;
		boolean isEncontroConcursoTramo = true;
		boolean flagError = false;
		//Map mapConcursoTramo = new HashMap();
		
		Map params = new HashMap();
		params.put("codigoPeriodo", f.getCodigoPeriodo());
		
		if(f.getTipoCarga().equals("CO1") || f.getTipoCarga().equals("CO2") || f.getTipoCarga().equals("CO3") || f.getTipoCarga().equals("CO4"))
		//{ mapConcursoTramo = serviceLET.getConcursoTramoPrograma(params);
			//if(mapConcursoTramo != null){
				/*criteria.put("codigoConcurso", MapUtils.getString(mapConcursoTramo, "codigoPrograma"));
				criteria.put("campanaInicioConcurso", MapUtils.getString(mapConcursoTramo, "campanaInicioProgr"));
				criteria.put("campanaFinConcurso", MapUtils.getString(mapConcursoTramo, "campanaFinProgr"));
				criteria.put("codigoTramo", MapUtils.getString(mapConcursoTramo, "codigoTramo"));
				criteria.put("campanaInicioTramo", MapUtils.getString(mapConcursoTramo, "campanaInicioTramo"));
				criteria.put("campanaFinTramo", MapUtils.getString(mapConcursoTramo, "campanaFinTramo"));*/
				flagError = serviceLET.loadfileExcel(criteria, objetivosList, errorObjetivosListList, flagErrorGlobal);
			//}else
				//isEncontroConcursoTramo = false;
		/*}else{
			flagError = serviceLET.loadfileExcel(criteria, objetivosList, errorObjetivosListList, flagErrorGlobal);
		}*/
		
		if(flagErrorGlobal.booleanValue())
			addError("Error:", getResourceMessage("proceso.error"));
		
		if(!isEncontroConcursoTramo)
			addError("Error:", getResourceMessage("procesoLETCargaObjetivosExcelForm.mensajeErrorNoEncontroConcurso"));
			
		flagErrorRepetidos = validaRegistrosRepetidos(objetivosList, flagErrorRepetidos, errorObjetivosListList, f.getTipoCarga());
					
		
		f.setFlagMostrarErrores(flagError | flagErrorRepetidos);
		
		if(!flagError && !flagErrorRepetidos && !flagErrorGlobal.booleanValue() && isEncontroConcursoTramo){
			f.setFlagUploadOk(true);
		}
		
		this.setLetObjetivosErroresList(errorObjetivosListList);
		this.letObjetivosList = objetivosList;
				
		borrarFichero(f.getDirectorioTemporal(), f.getNombreArchivo());
		
	}
	
	/**
	 * Realiza la subida del archivo a un directorio temporal
	 * @param form
	 */
	private void uploadArchivo(UploadedFile archivo) throws IOException{
		
		ProcesoLETCargaObjetivosExcelForm f = (ProcesoLETCargaObjetivosExcelForm) this.formProceso;
		// recuperamos el fichero
		//UploadedFile archivo = f.getArchivo();
		f.setNombreArchivo(archivo.getFileName());
		log.debug("Nombre Archivo Upload: " + f.getNombreArchivo());
		
		// leemos el stream de entrada
		InputStream is = archivo.getInputstream();
		
		// abrimos el stream de escritura, ubicacion al cual se grabara el archivo
		FileOutputStream os = new FileOutputStream(new File(f.getDirectorioTemporal(),f.getNombreArchivo()));
		
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
	 * @param nombreArchivo
	 * @return
	 * @throws Exception
	 */
	private String obtenerExtensionArchivo(String nombreArchivo) throws Exception {
		return nombreArchivo.substring(nombreArchivo.length() - 3);
	}
	
	public boolean validaRegistrosRepetidos(List objetivosList, boolean flagErrorRepetidos, List errorObjetivosListList, String tipoCarga) {
		Map temp;
		Map objetivos;
		
		boolean flagBreak = false;
		MessageSource message = (MessageSource)getBean("messageSource");
		objetivosList.addAll(errorObjetivosListList);
		
		for(int i=0;i<objetivosList.size();i++){
			temp = (Map)objetivosList.get(i);
			for(int j = i + 1; j < objetivosList.size(); j ++){
				objetivos = (Map)objetivosList.get(j);
				
				if(!tipoCarga.equals("CO2")){
					if(((String)temp.get("codigoPais")).equals((String)objetivos.get("codigoPais")) &&
					   ((Integer)temp.get("codigoPeriodo")).equals((Integer)objetivos.get("codigoPeriodo")) &&
					   ((String)temp.get("codigoRegion")).equals((String)objetivos.get("codigoRegion")) &&
					   ((String)temp.get("codigoZona")).equals((String)objetivos.get("codigoZona")) &&
					   ((String)temp.get("codigoSeccion")).equals((String)objetivos.get("codigoSeccion"))
					  ){
						temp.put("descripcionError", message.getMessage("procesoLETCargaObjetivosExcelForm.mensajeErrorRegistrosDupiblicados", null, new Locale(Constants.EDU_IDIOMA_DEFAULT_ES)));
						errorObjetivosListList.add(temp);
						
						flagErrorRepetidos = true;
						flagBreak = true;
						break;
					}
				}else{
					if(((String)temp.get("codigoPais")).equals((String)objetivos.get("codigoPais")) &&
					   ((Integer)temp.get("codigoPeriodo")).equals((Integer)objetivos.get("codigoPeriodo")) &&
					   ((String)temp.get("codigoLider")).equals((String)objetivos.get("codigoLider"))
					  ){
						temp.put("descripcionError", message.getMessage("procesoLETCargaObjetivosExcelForm.mensajeErrorRegistrosDupiblicados", null, new Locale(Constants.EDU_IDIOMA_DEFAULT_ES)));
						errorObjetivosListList.add(temp);
						
						flagErrorRepetidos = true;
						flagBreak = true;
						break;
					}
				}
			}
			
			if(flagBreak)
				break;
		}
		return flagErrorRepetidos;
	}
	
	/**
	 * elimina el fichero del temporal
	 * @param path
	 * @param nombreArchivo
	 */
	private void borrarFichero(String path, String nombreArchivo) {
		try {
			File file = new File(path, nombreArchivo);
			file.delete();
			log.debug("Se eliminó el archivo");
		}	
		catch(Exception ex) {
			log.debug("No se pudo eliminar el archivo " + ex.getMessage());
		}
	}
	
	public void paginacion() throws Exception {	
		if (log.isDebugEnabled()) {
			log.debug("Entering 'paginacion' method");
		}
		
		ProcesoLETCargaObjetivosExcelForm f = (ProcesoLETCargaObjetivosExcelForm) this.formProceso;
		
		f.setFlagUploadOk(false);
		
	}
	
	public void validaProgramaTramoCampanaCerrada(ActionEvent actionEvent)throws Exception{
		
		ProcesoLETCargaObjetivosExcelForm f = (ProcesoLETCargaObjetivosExcelForm) this.formProceso;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String tipoCarga =  this.tipoCarga;
		String valorCarga = "";
		String valorValidacion= "";

		if(f.getCodigoPeriodo() != ""){
			if(tipoCarga.equals("CO1")){
				valorValidacion = "1";
			}else{
				valorValidacion = "2";
			}
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			String data = ajax.validaProgramaTramoCampanaCerrada(f.getCodigoPeriodo(), valorValidacion, pais.getCodigo());
			
			String mensaje = data;
			
			if(!StringUtils.isBlank(mensaje)){
				this.addInfo("Info : ", mensaje);
			}	
			
		}else{
			addInfo("Alerta: ", getResourceMessage("procesoLETCargaObjetivosExcelForm.mensajeAlertaCampanha"));
			//alert("<fmt:message key='procesoLETCargaObjetivosExcelForm.mensajeAlertaCampanha' />");
		}
	}
	
	public void loadRegiones(ActionEvent actionEvent)throws Exception {
		ProcesoLETCargaObjetivosExcelForm f = (ProcesoLETCargaObjetivosExcelForm) this.formProceso;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		ajax.getRegionesByPaisMarcaCanal(pais.getCodigo(), f.getCodigoMarca(), f.getCodigoCanal());
	}
	
	@Override
	protected void setViewAtributes() throws Exception {

		if(log.isDebugEnabled()){
			log.debug("ProcesoLETCargaObjetivosExcelAction - setViewAttributes");
		}
		this.mostrarBotonExecute = false;
		
		this.attachment = "";
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		
		
		ProcesoLETCargaObjetivosExcelForm f = new ProcesoLETCargaObjetivosExcelForm();
		
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		MantenimientoLETLideresService service = (MantenimientoLETLideresService) getBean("spusicc.mantenimientoLETLideresService");
		
			
//		String codigoSistema = request.getParameter("codigoSistema");
//		String codigoParametro = request.getParameter("codigoParametro");
		
//		Map criteria = new HashMap();
//		criteria.put("codigoPais", pais.getCodigo());
//		criteria.put("codigoSistema", codigoSistema);
//		criteria.put("codigoParametro", codigoParametro);
		
		//String indicadorValidaLiderSeccion = service.getIndicadorAsignarLider(criteria);
		
		this.siccMarcaList = interfazSiCCService.getMarcas();
		this.siccCanalList = interfazSiCCService.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		this.siccRegionList = new ArrayList();
	
		
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);

		f.setCodigoPais(pais.getCodigo());
		f.setFlagMostrarErrores(false);
		f.setFlagUpload(true);
		f.setFlagUploadOk(false);
		//f.setIndicadorValidaLiderSeccion(indicadorValidaLiderSeccion);
		
		ProcesoZONActualizarUnidadesGeograficasService serviceUnidad = (ProcesoZONActualizarUnidadesGeograficasService) getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");		
		f.setDirectorioTemporal(serviceUnidad.obtenerPathUpload(pais.getCodigo()));
		
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
	public List getSiccRegionList() {
		return siccRegionList;
	}
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public List getLetObjetivosErroresList() {
		return letObjetivosErroresList;
	}

	public void setLetObjetivosErroresList(List letObjetivosErroresList) {
		this.letObjetivosErroresList = letObjetivosErroresList;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	/**
	 * @return the letObjetivosList
	 */
	public List getLetObjetivosList() {
		return letObjetivosList;
	}

	/**
	 * @param letObjetivosList the letObjetivosList to set
	 */
	public void setLetObjetivosList(List letObjetivosList) {
		this.letObjetivosList = letObjetivosList;
	}

	/**
	 * @return the tipoCarga
	 */
	public String getTipoCarga() {
		return tipoCarga;
	}

	/**
	 * @param tipoCarga the tipoCarga to set
	 */
	public void setTipoCarga(String tipoCarga) {
		this.tipoCarga = tipoCarga;
	}
}