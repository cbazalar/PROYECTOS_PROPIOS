package biz.belcorp.ssicc.web.spusicc.lec.action;

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
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.lec.ProcesoLECCargaDatosExcelService;
import biz.belcorp.ssicc.service.spusicc.pej.MantenimientoPEJProgramaEjecutivasService;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasService;
import biz.belcorp.ssicc.service.zon.ws.ProcesoZONWebService;
import biz.belcorp.ssicc.service.zon.ws.beans.ResponsableZONWebServiceResultado;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spisicc.action.ReporteLECCargaDatosFormatosAction;
import biz.belcorp.ssicc.web.spisicc.action.ReporteLECCargaDatosFormatosErrorAction;
import biz.belcorp.ssicc.web.spusicc.lec.form.ProcesoLECCargaDatosExcelForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProcesoLECCargaDatosExcelAction extends BaseProcesoAbstractAction {

	private static final long serialVersionUID = 1L;
	private List lecTipoCargaList;
	private List lecProgramaList;
	private boolean viewValida = false;
	private String msjDialog;
	private String attachment = "";
	private List lecProgramaArchivoList = new ArrayList();
	private Boolean mostrarZonaUpload;
	private String codigoPrograma;

	@ManagedProperty(value = "#{reporteLECCargaDatosFormatosAction}")
	private ReporteLECCargaDatosFormatosAction reporteLECCargaDatosFormatosAction;
	
	@ManagedProperty(value = "#{reporteLECCargaDatosFormatosErrorAction}")
	private ReporteLECCargaDatosFormatosErrorAction reporteLECCargaDatosFormatosErrorAction;

	/**
	 * Ejecutar boton de reporte
	 * 
	 * @param evt
	 */
	public void ejecutarReporteP(ActionEvent evt) {
		try {
			ProcesoLECCargaDatosExcelForm f = (ProcesoLECCargaDatosExcelForm) this.formProceso;
			this.reporteLECCargaDatosFormatosAction.setFormatoReporte("XLS");
			this.reporteLECCargaDatosFormatosAction.setFormatoExportacion("XLS");
			this.reporteLECCargaDatosFormatosAction.setTipoCarga(f.getTipoCarga());
			this.reporteLECCargaDatosFormatosAction.getFormReporte().setFormatoExportacion("XLS");
			this.reporteLECCargaDatosFormatosAction.executeReporte();				

			// this.redireccionarPagina("reporteLECCargaDatosFormatosForm");
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}
	
	/**
	 * Ejecuta el reporte de error
	 * 
	 * @param evt
	 */
	public void executeReporteLECCargaDatosFormatosError(ActionEvent evt) {
		try {
			ProcesoLECCargaDatosExcelForm f = (ProcesoLECCargaDatosExcelForm) this.formProceso;
			this.reporteLECCargaDatosFormatosErrorAction.setFormatoReporte("XLS");
			this.reporteLECCargaDatosFormatosErrorAction.setFormatoExportacion("XLS");
			this.reporteLECCargaDatosFormatosErrorAction.setTipoCarga(f.getTipoCarga());
			this.reporteLECCargaDatosFormatosErrorAction.setNumeroCarga(f.getNumeroCarga());
			this.reporteLECCargaDatosFormatosErrorAction.getFormReporte().setFormatoExportacion("XLS");
			this.reporteLECCargaDatosFormatosErrorAction.executeReporte();

			// this.redireccionarPagina("reporteLECCargaDatosFormatosForm");
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

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
		this.mostrarZonaUpload = true;
		try {
			ProcesoLECCargaDatosExcelForm f = (ProcesoLECCargaDatosExcelForm) this.formProceso;
			//f.setCodigoPrograma(this.codigoPrograma);
			if(StringUtils.isBlank(f.getCodigoPrograma())){
				this.addError("Error: ", this.getResourceMessage("procesoLECCargaDatosExcelForm.programa.requerido"));
				return;				
			}			
			
			if (event != null) {
				f.setArchivo(event.getFile());
				this.setAttachment(event.getFile().getFileName());
				this.cargar();
				
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}
	
	public void grabarPrograma(ValueChangeEvent event){
		if(event.getNewValue() == null)
			return;		
		try {			
			String valor = (String) event.getNewValue();			
			ProcesoLECCargaDatosExcelForm f = (ProcesoLECCargaDatosExcelForm) this.formProceso;
			f.setCodigoPrograma(valor);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}		
	}
	

	/**
	 * Carga el archivo al servidor
	 */
	public void cargar() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'cargar' method");
		}

		String mensaje = "";
		try {
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			ProcesoLECCargaDatosExcelForm f = (ProcesoLECCargaDatosExcelForm) this.formProceso;

			ProcesoLECCargaDatosExcelService service = (ProcesoLECCargaDatosExcelService) getBean("spusicc.procesoLECCargaDatosExcelService");

			/* INI PER-SiCC-2015-0190 */
			if (f.getTipoCarga().equals("10")) {
				MantenimientoPEJProgramaEjecutivasService serviceE = (MantenimientoPEJProgramaEjecutivasService) getBean("spusicc.mantenimientoPEJProgramaEjecutivasService");
				ProcesoLECCargaDatosExcelService servicePEJ = (ProcesoLECCargaDatosExcelService) getBean("spusicc.procesoLECCargaDatosExcelService");
				Map result = serviceE.getPeriodoDefault();
				String codigoPeriodo = (String) result.get("codigoPeriodo");
				Map params = new HashMap();
				params.put("codigoPais", pais.getCodigo());
				params.put("campana", codigoPeriodo);
				params.put("codigoPrograma", f.getCodigoPrograma());
				String campanhaInicialPrograma = servicePEJ
						.getCampanhaInicialPrograma(params);

				if (StringUtils.isNotBlank(campanhaInicialPrograma)) {
					if (!campanhaInicialPrograma.equals(codigoPeriodo)) {
						this.addError("Error: ", getResourceMessage("procesoLETCargaDatosExcelForm.error.campanhaInicio"));
						return;
					}
				} else {
					this.addError("Error: ", getResourceMessage("procesoLETCargaDatosExcelForm.error.campanhaInicio"));
					return;
				}

				if (!servicePEJ.getNumeroRegistrosResultadosLet(params)) {
					addError("Error: ", getResourceMessage("procesoLETCargaDatosExcelForm.error.registros"));
					return;
				}

			}
			/* FIN PER-SiCC-2015-0190 */

			// Cargamos el archivo de la maquina del cliente al servidor
			uploadArchivo();
			// Obtenemos la extension del archivo
			String extensionArchivo = obtenerExtensionArchivo(f.getNombreArchivo());
			f.setExtensionArchivo(extensionArchivo);

			Map criteria = new HashMap();
			criteria.put("directorioTemporal", f.getDirectorioTemporal());
			criteria.put("nombreArchivo", f.getNombreArchivo());
			criteria.put("usuario", usuario);
			criteria.put("tipoCarga", f.getTipoCarga());
			criteria.put("codigoPeriodo", f.getCodigoPeriodo());
			criteria.put("codigoPais", f.getCodigoPais());
			criteria.put("codigoPrograma", f.getCodigoPrograma());
			// validamos el archivo excel y en criteria mandamos que estructura
			// es sin period o con periodo
			Map resultados = service.cargarArchivoExcel(criteria);
			int totalRegistros = Integer.parseInt((String) resultados.get("totalRegistros"));
			if (totalRegistros == 0) {
				this.mostrarPanelAdicionalProceso= false; 
				this.viewValida = false;
				mensaje = this.getResourceMessage("procesoLETCargaDatosExcelForm.existe.registros");
				this.addError("Error : ", mensaje);
				return;
			} else {
				this.mostrarPanelAdicionalProceso= true; 
				this.viewValida = true;// flag para mostrar el resultado de la validacion
			}
			f.setNumeroCarga((String) resultados.get("numeroCarga"));
			f.setNumRegistros((String) resultados.get("totalRegistros"));
			f.setNumRegistrosInsertados((String) resultados.get("totalRegistrosInsertados"));
			f.setNumRegistrosError(Constants.NUMERO_CERO);
			f.setNumRegistrosValido(Constants.NUMERO_CERO);
			Integer numRegNoInsertados = Integer.parseInt(f.getNumRegistros()) - Integer.parseInt(f.getNumRegistrosInsertados());
			f.setNumRegistrosNoInsertados(numRegNoInsertados.toString());

			borrarFichero(f.getDirectorioTemporal(), f.getNombreArchivo());

			f.setFlagBotonValidar(true);
			f.setFlagBotonActualizar(false);

		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**Valida el archivo cargado
	 * 
	 * @param evt
	 */
	public void validar(ActionEvent evt) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'validar' method");
		}
		try {
			ProcesoLECCargaDatosExcelForm f = (ProcesoLECCargaDatosExcelForm) this.formProceso;

			Map params = BeanUtils.describe(f);

			ProcesoLECCargaDatosExcelService service = (ProcesoLECCargaDatosExcelService) getBean("spusicc.procesoLECCargaDatosExcelService");

			int totalErrores = 0;
			params.put("indicadorCarga", null);
			// validamos los datos cargados del archivo excel
			List resultados = service.executeValidarCargaDatos(params);
			for (int i = 0; i < resultados.size(); i++) {
				Map errores = (Map) resultados.get(i);
				if (errores.get("codigoMotivo") != null) {
					totalErrores++;
				}
			}

			int totalValidos = Integer.parseInt(f.getNumRegistros()) - totalErrores;

			f.setNumRegistrosError(String.valueOf(totalErrores));
			f.setNumRegistrosValido(String.valueOf(totalValidos));
			this.lecProgramaArchivoList = resultados;

			if (totalErrores > 0 || totalValidos > 0) {
				f.setFlagBotonValidar(false);
				f.setFlagBotonActualizar(true);
			} else {
				f.setFlagBotonValidar(true);
				f.setFlagBotonActualizar(false);
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * Escribe el archivo
	 */
	private void uploadArchivo() {
		try {
			ProcesoLECCargaDatosExcelForm f = (ProcesoLECCargaDatosExcelForm) this.formProceso;
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
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * @param nombreArchivo
	 * @return
	 * @throws Exception
	 */
	private String obtenerExtensionArchivo(String nombreArchivo)
			throws Exception {
		return nombreArchivo.substring(nombreArchivo.length() - 3);
	}

	/**
	 * @param path
	 * @param nombreArchivo
	 */
	private void borrarFichero(String path, String nombreArchivo) {
		try {
			File file = new File(path, nombreArchivo);
			file.delete();
			log.debug("Se elimin� el archivo");
		} catch (Exception ex) {
			log.debug("No se pudo eliminar el archivo " + ex.getMessage());
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
		ProcesoLECCargaDatosExcelForm f = (ProcesoLECCargaDatosExcelForm) form;
		f.setFlagBotonValidar(false);
		f.setFlagBotonActualizar(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction
	 * #setErrorLogicaNegocio(java.util.Map)
	 */
	protected String setErrorLogicaNegocio(Map params) {
		String mensaje = (String) params.get("mensajeError");
		if (StringUtils.isBlank(mensaje))
			return null;
		return mensaje;
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
		ProcesoLECCargaDatosExcelForm form = new ProcesoLECCargaDatosExcelForm();
		return form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {

		this.mostrarBotonExecute = false;
		this.viewValida = false;
        this.mostrarPanelAdicionalProceso= false; 
        this.lecProgramaArchivoList= new ArrayList(); 
        this.codigoPrograma="";
        
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteria = new HashMap();
		ProcesoLECCargaDatosExcelForm f = (ProcesoLECCargaDatosExcelForm) this.formProceso;
		f.setCodigoPrograma("");
		this.mostrarZonaUpload = false;
		MantenimientoPEJProgramaEjecutivasService service = (MantenimientoPEJProgramaEjecutivasService) getBean("spusicc.mantenimientoPEJProgramaEjecutivasService");

		ProcesoLECCargaDatosExcelService servicePEJ = (ProcesoLECCargaDatosExcelService) getBean("spusicc.procesoLECCargaDatosExcelService");
		criteria.put("indGrup", "D");
		this.lecTipoCargaList = servicePEJ.getTipoCarga(criteria);

		Map result = service.getPeriodoDefault();

		String codigoPeriodo = (String) result.get("codigoPeriodo");
		f.setCodigoPeriodo(codigoPeriodo);
		// seteamos la ruta temporal dodne guardar el excel
		ProcesoZONActualizarUnidadesGeograficasService serviceUnidad = (ProcesoZONActualizarUnidadesGeograficasService) getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");
		f.setDirectorioTemporal(serviceUnidad.obtenerPathUpload(pais
				.getCodigo()));

		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estado", Constants.ESTADO_ACTIVO);
		criteria.put("campana", codigoPeriodo);
		this.lecProgramaList = new ArrayList();
		this.lecProgramaList = servicePEJ.getPrograma(criteria);

		f.setCodigoPais(pais.getCodigo());
		f.setFlagBotonValidar(false);
		f.setFlagBotonActualizar(false);
		Base base = (Base) this.lecTipoCargaList.get(0);
		Base base1 = (Base) this.lecProgramaList.get(0);

		f.setTipoCarga(base.getCodigo());
		this.codigoPrograma = base1.getCodigo();
		f.setCodigoPrograma(this.codigoPrograma);
	}	

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'cargar' method");
		}
		
		try {	
			ProcesoLECCargaDatosExcelForm f = (ProcesoLECCargaDatosExcelForm) this.formProceso;
			
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			ProcesoLECCargaDatosExcelService service = (ProcesoLECCargaDatosExcelService) getBean("spusicc.procesoLECCargaDatosExcelService");
			params.put("indicadorProceso",null);
			params.put("usuario", usuario.getLogin());
			params.put("codigoPeriodo", f.getCodigoPeriodo());
			String a="";
			String tip=(String)params.get("tipoCarga");
			if(tip.compareTo("03")==0){
				ProcesoZONWebService servicews = (ProcesoZONWebService) getBean("ssicc.procesoZONWebService02");
				List lista=service.getListaIntermedia(params);
				for(int i=0; i<lista.size(); i++){
					Map elem =(Map)lista.get(i);	
		
				      ResponsableZONWebServiceResultado result=servicews.asignarResponsable((String)params.get("usuario"), (String)elem.get("codigoPais"),
								(String)elem.get("datoUno"), (String)elem.get("datoDos"), 
								(String)elem.get("datoTres"), (String)elem.get("datoCuatro")
								, (String)elem.get("datoCinco"), (String)elem.get("datoSeis"), (String)elem.get("datoSiete"));
				      
				
					 if(result.getCodigoResultado()==null || result.getCodigoResultado().compareTo("-1")==0){
						 params.put("mensajeError",result.getMensajeResultado());
						 setMensajeError(params);
					  }
				}
			
			}else{
				service.executeActualizarCargaDatos(params);
				
			}
			f.setFlagBotonValidar(true);
	        f.setFlagBotonActualizar(false);
	        
	   
		}	
      catch(Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
			this.addError("Error: ", this.getResourceMessage("errors.detail", new Object[]{error}));			
		}
		return params;
	}
	
	private void setMensajeError(Map params) {
		String keyMensaje = (String) params.get("mensajeError");
		log.debug("KeyMessage " + keyMensaje);
		params.put("mensajeError","Error no se proceso Correctamente "+keyMensaje);

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#afterExecuteProcessSuccess()
	 */
	@Override
	protected void afterExecuteProcessSuccess() {		

		//limpiando el flag de validacion de archivo
		ProcesoLECCargaDatosExcelForm f = (ProcesoLECCargaDatosExcelForm) this.formProceso;
		f.setFlagBotonValidar(false);
		f.setFlagBotonActualizar(false);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {

		String msj = null;
		
		ProcesoLECCargaDatosExcelForm f = (ProcesoLECCargaDatosExcelForm)this.formProceso;
		if(Integer.parseInt(f.getNumRegistrosError())>0){
			this.msjDialog = this.getResourceMessage("procesoLECCargaDatosExcelForm.process.valido.errores");
			
		}
		else{
			this.msjDialog = this.getResourceMessage("procesoLECCargaDatosExcelForm.process.valido");
		}
		
		return msj;
	
	}
	
	/**
	 * Carga el combo de programa
	 * 
	 * @param valor
	 */
	public void loadProgramas(String valor){
		try {
			log.debug("Enter method - loadProgramas_ " + valor);
			ProcesoLECCargaDatosExcelForm f = (ProcesoLECCargaDatosExcelForm) this.formProceso;		
			if(StringUtils.isNotBlank(valor)){
				Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
				f.setCodigoPeriodo(valor);
				this.lecProgramaList = new ArrayList();
				AjaxService ajax = (AjaxService)getBean("ajaxService");
				LabelValue[] data = ajax.getProgramasPorCampanaProceso(pais.getCodigo(), valor);				
				for (int i = 0; i < data.length; i++) {
					Base datos = new Base();
					String codigo = data[i].getValue();
					String descripcion = data[i].getLabel();
					datos.setCodigo(codigo);
					datos.setDescripcion(descripcion);						
					this.lecProgramaList.add(datos);					
				}	
				f.setCodigoPrograma(((Base) (this.lecProgramaList.get(0))).getCodigo());				
			}	
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));			
		}
		
	}

	/**
	 * @return the lecProgramaArchivoList
	 */
	public List getLecProgramaArchivoList() {
		return lecProgramaArchivoList;
	}

	/**
	 * @param lecProgramaArchivoList
	 *            the lecProgramaArchivoList to set
	 */
	public void setLecProgramaArchivoList(List lecProgramaArchivoList) {
		this.lecProgramaArchivoList = lecProgramaArchivoList;
	}

	/**
	 * @return the reporteLECCargaDatosFormatosAction
	 */
	public ReporteLECCargaDatosFormatosAction getReporteLECCargaDatosFormatosAction() {
		return reporteLECCargaDatosFormatosAction;
	}

	/**
	 * @param reporteLECCargaDatosFormatosAction
	 *            the reporteLECCargaDatosFormatosAction to set
	 */
	public void setReporteLECCargaDatosFormatosAction(
			ReporteLECCargaDatosFormatosAction reporteLECCargaDatosFormatosAction) {
		this.reporteLECCargaDatosFormatosAction = reporteLECCargaDatosFormatosAction;
	}

	/**
	 * @return the mostrarZonaUpload
	 */
	public Boolean getMostrarZonaUpload() {
		return mostrarZonaUpload;
	}

	/**
	 * @param mostrarZonaUpload
	 *            the mostrarZonaUpload to set
	 */
	public void setMostrarZonaUpload(Boolean mostrarZonaUpload) {
		this.mostrarZonaUpload = mostrarZonaUpload;
	}

	/**
	 * @return the codigoPrograma
	 */
	public String getCodigoPrograma() {
		return codigoPrograma;
	}

	/**
	 * @param codigoPrograma
	 *            the codigoPrograma to set
	 */
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
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
	
	/**
	 * @return the lecTipoCargaList
	 */
	public List getLecTipoCargaList() {
		return lecTipoCargaList;
	}

	/**
	 * @param lecTipoCargaList
	 *            the lecTipoCargaList to set
	 */
	public void setLecTipoCargaList(List lecTipoCargaList) {
		this.lecTipoCargaList = lecTipoCargaList;
	}

	/**
	 * @return the lecProgramaList
	 */
	public List getLecProgramaList() {
		return lecProgramaList;
	}

	/**
	 * @param lecProgramaList
	 *            the lecProgramaList to set
	 */
	public void setLecProgramaList(List lecProgramaList) {
		this.lecProgramaList = lecProgramaList;
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
	 * @return the reporteLECCargaDatosFormatosErrorAction
	 */
	public ReporteLECCargaDatosFormatosErrorAction getReporteLECCargaDatosFormatosErrorAction() {
		return reporteLECCargaDatosFormatosErrorAction;
	}

	/**
	 * @param reporteLECCargaDatosFormatosErrorAction the reporteLECCargaDatosFormatosErrorAction to set
	 */
	public void setReporteLECCargaDatosFormatosErrorAction(
			ReporteLECCargaDatosFormatosErrorAction reporteLECCargaDatosFormatosErrorAction) {
		this.reporteLECCargaDatosFormatosErrorAction = reporteLECCargaDatosFormatosErrorAction;
	}
}