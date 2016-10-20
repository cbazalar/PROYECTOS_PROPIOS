package biz.belcorp.ssicc.web.spusicc.inc.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.inc.ProcesoINCCargaPremiosExcelService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.inc.form.ProcesoINCCargaPremiosExcelForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProcesoINCCargaPremiosExcelAction extends
		BaseProcesoAbstractAction {

	private static final long serialVersionUID = 1L;
	String oidSecuencia = "";
	private List incErroresPremiosList;
	private List incConcuCreadosVigentesList;	
	private List incArchivoList;
	private String attachment = "";
	private Boolean primerSegmento;
	private Boolean segundoSegmento;
	
	private boolean viewValida = false;
	private String msjDialog;

	/**
	 * Evento donde se carga el archivo
	 * 
	 * @param event
	 * @throws Exception
	 */
	public void handleFileUpload(FileUploadEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("handleFileUpload");
		}
		String mensaje = "";
		try {
			ProcesoINCCargaPremiosExcelForm f = (ProcesoINCCargaPremiosExcelForm) this.formProceso;
			if (StringUtils.isBlank(f.getOidConcurso())) {
				mensaje = "Se requiere seleccionar un concurso";
				this.addError("Error : ", mensaje);
				return;
			}
			if (event != null) {
				// f.setArchivo(event.getFile());
				f.setArchivo(event.getFile());
				this.setAttachment(event.getFile().getFileName());
				this.uploadArchivo();
				this.validar();
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * Mostrar el campo de Carga de Archivo
	 * 
	 * @param e
	 */
	public void mostrarPrimerSegmento(ValueChangeEvent e) {
		try {
			String valorOid = (String) e.getNewValue();
			AjaxService ajax = (AjaxService)getBean("ajaxService");
			if(StringUtils.isNotBlank(valorOid)){
				String data = ajax.getValidaEntregaPremios(valorOid);
				
				if (data!=null){
		  		 	if (!StringUtils.equals(data,Constants.NUMERO_CERO)){		 				
		 		    	this.addWarn("Error: ", this.getResourceMessage("procesoINCCargaPremiosExcelForm.msg.concurso"));
		 		    	this.primerSegmento = false;
		  	 		 	return;
		  		 	}else{
		  		 		this.primerSegmento = true;
		  		 	} 
		  	   }
			}					
		} catch (Exception e2) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e2));
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
		String mensaje = "";
		try {
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			List listaArchivo = null;

			ProcesoINCCargaPremiosExcelForm f = (ProcesoINCCargaPremiosExcelForm) this.formProceso;
			MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");

			// obtenemos el servicio
			ProcesoINCCargaPremiosExcelService service = (ProcesoINCCargaPremiosExcelService) getBean("spusicc.procesoINCCargaPremiosExcelService");

			// Cargamos el archivo de la maquina del cliente al servidor
			// uploadArchivo();

			log.debug("Se realiz� la carga");
			// Leemos la primera linea del archivo, para recuperar los campos
			// filtro
			// que nos servira para determinar que campo corresponde a que nivel
			// de

			String extensionArchivo = obtenerExtensionArchivo(f
					.getNombreArchivo());
			f.setExtensionArchivo(extensionArchivo);

			Map criteria = new HashMap();
			Map mapFila = null;
			criteria.put("directorioTemporal", f.getDirectorioTemporal());
			criteria.put("nombreArchivo", f.getNombreArchivo());
			criteria.put("numeroConcurso",
					getNumeroConcurso(f.getOidConcurso()));
			criteria.put("oidConcurso", f.getOidConcurso().trim());
			criteria.put("usuario", usuario);
			criteria.put("codigoPais", pais.getCodigo());
			String oidPais = clienteService.getOidPais(criteria);
			criteria.put("oidPais", oidPais);

			// validamos el archivo excel y en criteria mandamos que estructura
			// es
			// sin period o con periodo
			boolean isValido = service.validarArchivoExcel(criteria);

			// cargamos todos los registros del excel

			if (isValido) {
				oidSecuencia = service.getObtenerSecTempCargaPremios();
				criteria.put("oidSecuencia", oidSecuencia);
				criteria.put("numErrores", "");
				criteria.put("numNivFalta", "");
				criteria.put("numRegTotal", "");

				listaArchivo = service.cargarArchivoExcel(criteria);

				// Map map = null;
				String numRegError = (String) criteria.get("numErrores");
				String numNivelFaltan = (String) criteria.get("numNivFalta");
				String numRegTotal = (String) criteria.get("numRegTotal");
				String mensajeError = (String) criteria.get("mensajeError");
				f.setNumRegistros(String.valueOf(numRegTotal));

				if (listaArchivo.size() > 0) {
					// map = (Map) listaArchivo.get(listaArchivo.size() - 1);
					f.setNumRegistrosError(numRegError);
					log.debug("numRegTotal 00:" + numRegTotal);
					log.debug("numRegError 00:" + numRegError);
					f.setNumRegistrosValido(String.valueOf(Integer
							.parseInt(numRegTotal)
							- Integer.parseInt(numRegError)));
					f.setNumNivelesFaltante(numNivelFaltan);
				} else {
					f.setNumRegistrosError("0");
					f.setNumRegistrosValido(numRegTotal);
					f.setNumNivelesFaltante(numNivelFaltan);
				}

				// es valido si por lo menos hay un registro por cargar , es
				// decir
				// numero errors < num registros
				if (Integer.parseInt(f.getNumRegistrosError()) > 0) {
					f.setIndicadorValido(Constants.NUMERO_CERO);

					if (Integer.parseInt(f.getNumRegistrosError()) == 1) {
						listaArchivo = new ArrayList();
						// mandamos infomacion del archivo
						mapFila = new HashMap();
						mapFila.put("numeroFila", Constants.NUMERO_UNO);
						mapFila.put("codigoConcurso", "");
						mapFila.put("numNivel", "");
						mapFila.put("numPremio", "");
						mapFila.put("codigoProducto", "");
						mapFila.put("codigoProducto", "");
						mapFila.put("mensajeError", mensajeError);
						listaArchivo.add(mapFila);
					}

				} else {
					f.setIndicadorValido(Constants.NUMERO_UNO);
				}
				borrarFichero(f.getDirectorioTemporal(), f.getNombreArchivo());
				// Flag para mostrar el resultado de la validacion
				this.viewValida =true; 
				this.mostrarPanelAdicionalProceso= true;
				this.incErroresPremiosList = listaArchivo;

			} else {
				listaArchivo = new ArrayList();

				f.setIndicadorValido(Constants.NUMERO_CERO);
				f.setNumRegistrosError(Constants.NUMERO_UNO);
				f.setNumRegistros(Constants.NUMERO_UNO);
				f.setNumRegistrosValido(Constants.NUMERO_CERO);
				// Flag para mostrar el resultado de la validacion
				this.viewValida = true;
				this.mostrarPanelAdicionalProceso= true;
				// mandamos infomacion del archivo
				mapFila = new HashMap();
				mapFila.put("numeroFila", Constants.NUMERO_UNO);
				mapFila.put("codigoConcurso", "");
				mapFila.put("numNivel", "");
				mapFila.put("numPremio", "");
				mapFila.put("codigoProducto", "");
				mapFila.put("codigoProducto", "");

				mensaje = this
						.getResourceMessage("procesoINCCargaPremiosExcelForm.error.noFormatoExcel");

				mapFila.put("mensajeError", mensaje);
				listaArchivo.add(mapFila);
				this.incErroresPremiosList = listaArchivo;
			}
//			int tamanio = this.incErroresPremiosList.size();
//			if (tamanio > 0) {
//				this.segundoSegmento = true;
//				this.mostrarBotonExecute = true;
//			}
			f.setFlagVisualiza("1");

		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * obtiene el numero del concurso
	 * 
	 * @param sesion
	 * @param oidConcurso
	 * @return
	 */
	private String getNumeroConcurso(String oidConcurso) {
		String numeroConcurso = "";
		List listConcursos = this.incConcuCreadosVigentesList;
		Iterator it = listConcursos.iterator();
		while (it.hasNext()) {
			Base concurso = (Base) it.next();
			if (oidConcurso.equals(concurso.getCodigo())) {
				numeroConcurso = (StringUtils.split(concurso.getDescripcion(),
						"-")[0]).trim();
				break;
			}
		}
		return numeroConcurso;
	}

	/**
	 * carga el archivo
	 * 
	 * @param form
	 * @throws Exception
	 */
	private void uploadArchivo() throws Exception {
		ProcesoINCCargaPremiosExcelForm f = (ProcesoINCCargaPremiosExcelForm) this.formProceso;

		// recuperamos el fichero
		UploadedFile archivo = f.getArchivo();
		f.setNombreArchivo(archivo.getFileName());
		log.debug("Nombre Archivo Upload: " + f.getNombreArchivo());
		// leemos el stream de entrada
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
			this.addError("Error : ", this.obtieneMensajeErrorException(ex));
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
//	@Override
//	public void afterExecuteProcess(BaseProcesoForm form) throws Exception {
//		// limpiando el flag de validacion de archivo
//		this.viewValida = "";
//		this.incArchivoList = new ArrayList();
//		String mensaje = this
//				.getResourceMessage("procesoINCCargaPremiosExcelForm.proceso.ok");
//		this.addInfo("Info : ", mensaje);
//
//		ProcesoINCCargaPremiosExcelForm f = (ProcesoINCCargaPremiosExcelForm) this.formProceso;
//
//		f.setOidConcurso("");
//		oidSecuencia = "";
//		f.setIndicadorValido(Constants.NUMERO_CERO);
//		f.setFlagVisualiza("0");
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction
	 * #setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		log.debug("Entering 'find' method");

		// -- Variables
		ProcesoINCCargaPremiosExcelForm f = (ProcesoINCCargaPremiosExcelForm) this.formProceso;
		f.setOidConcurso(f.getOidConcurso());
		List listaErroresPremios = this.incErroresPremiosList;
		f.setFlagVisualiza(f.getFlagVisualiza());

		return listaErroresPremios;
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
		ProcesoINCCargaPremiosExcelForm form = new ProcesoINCCargaPremiosExcelForm();
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
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		ProcesoINCCargaPremiosExcelService service = (ProcesoINCCargaPremiosExcelService) getBean("spusicc.procesoINCCargaPremiosExcelService");
		params.put("codigoPais", pais.getCodigo());
		log.debug("oid Secuencia Process : " + oidSecuencia);
		params.put("oidSecuencia", oidSecuencia);

		service.executeProcesoCargaPremiosExcel(params);

		return params;
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#afterExecuteProcessSuccess()
	 */
	@Override
	protected void afterExecuteProcessSuccess() {
		//limpiando el flag de validacion de archivo
		this.viewValida = false;
		this.mostrarPanelAdicionalProceso= false;
		this.incArchivoList = new ArrayList();
		
//		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("procesoINCCargaPremiosExcelForm.proceso.ok"));			
		
		ProcesoINCCargaPremiosExcelForm f = (ProcesoINCCargaPremiosExcelForm) this.formProceso;
		f.setOidConcurso("");
		this.oidSecuencia = "";
		f.setIndicadorValido(Constants.NUMERO_CERO);
		f.setFlagVisualiza("0");
		this.attachment = "";
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

		ProcesoINCCargaPremiosExcelForm f = (ProcesoINCCargaPremiosExcelForm) this.formProceso;

		// seteamos la ruta temporal dodne guardar el excel
		ProcesoZONActualizarUnidadesGeograficasService serviceUnidad = (ProcesoZONActualizarUnidadesGeograficasService) getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");
		ProcesoINCCargaPremiosExcelService service = (ProcesoINCCargaPremiosExcelService) getBean("spusicc.procesoINCCargaPremiosExcelService");

		f.setDirectorioTemporal(serviceUnidad.obtenerPathUpload(pais
				.getCodigo()));
		f.setCodigoPais(pais.getCodigo());

		// cargando en session la lista de concursos creados/vigentes
		this.incConcuCreadosVigentesList = service
				.getListConcursoCreadosVigentes();

		// limpiando el flag de validacion de archivo
		this.viewValida = false;
		this.mostrarPanelAdicionalProceso= false;
		this.incErroresPremiosList = new ArrayList();
		f.setFlagVisualiza("0");
		this.primerSegmento = false;
		this.segundoSegmento = false;
		this.mostrarBotonExecute = false;
		this.attachment="";

	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {

		String mensaje = null;
				
		if(StringUtils.equals(accion, "MENSAJE")){			
			AjaxService ajax = (AjaxService)getBean("ajaxService");
			ProcesoINCCargaPremiosExcelForm f = (ProcesoINCCargaPremiosExcelForm) this.formProceso;
			String oidConcurso = f.getOidConcurso();
			String valor = ajax.getValidaExistePremios(oidConcurso);
			String valorVal = "";			

			if (valor!=null){
				int val = Integer.parseInt(valor);
				if (val>0){
			   		valorVal ="S";
				}else{
					valorVal ="N";
				}

		   		if (StringUtils.equals(valorVal, "S")){	   			
		   			this.msjDialog = this.getResourceMessage("procesoINCCargaPremiosExcelForm.msg.existePremios");  			
		   		}else{
		   			this.msjDialog = this.getResourceMessage("confirm.execute.process");	   			
		   		}				
			}
			
		}
		
		return mensaje;
	}
	
	

	/**
	 * @return the oidSecuencia
	 */
	public String getOidSecuencia() {
		return oidSecuencia;
	}

	/**
	 * @param oidSecuencia
	 *            the oidSecuencia to set
	 */
	public void setOidSecuencia(String oidSecuencia) {
		this.oidSecuencia = oidSecuencia;
	}

	/**
	 * @return the incErroresPremiosList
	 */
	public List getIncErroresPremiosList() {
		return incErroresPremiosList;
	}

	/**
	 * @param incErroresPremiosList
	 *            the incErroresPremiosList to set
	 */
	public void setIncErroresPremiosList(List incErroresPremiosList) {
		this.incErroresPremiosList = incErroresPremiosList;
	}

	/**
	 * @return the incConcuCreadosVigentesList
	 */
	public List getIncConcuCreadosVigentesList() {
		return incConcuCreadosVigentesList;
	}

	/**
	 * @param incConcuCreadosVigentesList
	 *            the incConcuCreadosVigentesList to set
	 */
	public void setIncConcuCreadosVigentesList(List incConcuCreadosVigentesList) {
		this.incConcuCreadosVigentesList = incConcuCreadosVigentesList;
	}

	/**
	 * @return the incArchivoList
	 */
	public List getIncArchivoList() {
		return incArchivoList;
	}

	/**
	 * @param incArchivoList
	 *            the incArchivoList to set
	 */
	public void setIncArchivoList(List incArchivoList) {
		this.incArchivoList = incArchivoList;
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
	 * @return the primerSegmento
	 */
	public Boolean getPrimerSegmento() {
		return primerSegmento;
	}

	/**
	 * @param primerSegmento
	 *            the primerSegmento to set
	 */
	public void setPrimerSegmento(Boolean primerSegmento) {
		this.primerSegmento = primerSegmento;
	}

	/**
	 * @return the segundoSegmento
	 */
	public Boolean getSegundoSegmento() {
		return segundoSegmento;
	}

	/**
	 * @param segundoSegmento
	 *            the segundoSegmento to set
	 */
	public void setSegundoSegmento(Boolean segundoSegmento) {
		this.segundoSegmento = segundoSegmento;
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