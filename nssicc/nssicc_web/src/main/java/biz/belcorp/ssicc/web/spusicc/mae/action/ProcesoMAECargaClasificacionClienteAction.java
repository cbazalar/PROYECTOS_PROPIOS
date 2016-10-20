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
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.mae.ProcesoMAECargaClasificacionClienteService;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.mae.form.ProcesoMAECargaClasificacionClienteForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ProcesoPREMatrizCargaEstimadosForm;

/**
 * @author jpulido
 * 
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ProcesoMAECargaClasificacionClienteAction extends
		BaseProcesoAbstractAction {

	private static final long serialVersionUID = 1494141550408572632L;

	private List siccTipoClienteList;
	private LabelValue[] siccSubTipoClienteList;
	private LabelValue[] siccTipoClasificacionList;
	private LabelValue[] siccClasificacionList;
	private String codigoIdiomaISO;
	private List maeClasificacionesArchivoList;
	private boolean viewValida = false;
	private String attachment = "";
	private Boolean mostrarPrimeraGrilla;
	private Boolean mostrarSegundaGrilla;
	private Boolean mostrarTerceraGrilla;
	
	private String msjDialog;

	@ManagedProperty(value = "#{reporteMAECargaClasificacionClientesAction}")
	private ReporteMAECargaClasificacionClientesAction reporteMAECargaClasificacionClientes;

	/**
	 * Ejecutar boton de reporte
	 * 
	 * @param evt
	 */
	public void ejecutarReporteExcel(ActionEvent evt) {
		try {
			ProcesoMAECargaClasificacionClienteForm f = (ProcesoMAECargaClasificacionClienteForm) this.formProceso;
			String numeroCarga = f.getNumeroCarga();
			this.reporteMAECargaClasificacionClientes
					.setFormatoExportacion("XLS");
			this.reporteMAECargaClasificacionClientes
					.setNumeroCarga(numeroCarga);
			this.reporteMAECargaClasificacionClientes.getFormReporte()
					.setFormatoExportacion("XLS");
			this.reporteMAECargaClasificacionClientes.executeReporte();

			// this.redireccionarPagina("reporteLECCargaDatosFormatosForm");
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * @param evt
	 */
	public void ejecutarReportePDF(ActionEvent evt) {
		try {
			ProcesoMAECargaClasificacionClienteForm f = (ProcesoMAECargaClasificacionClienteForm) this.formProceso;
			String numeroCarga = f.getNumeroCarga();
			this.reporteMAECargaClasificacionClientes
					.setFormatoExportacion("PDF");
			this.reporteMAECargaClasificacionClientes
					.setNumeroCarga(numeroCarga);

			this.reporteMAECargaClasificacionClientes.getFormReporte()
					.setFormatoExportacion("PDF");
			this.reporteMAECargaClasificacionClientes.executeReporte();

			this.redireccionarPagina("reporteMAECargaClasificacionClientesForm");
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
		ProcesoMAECargaClasificacionClienteForm J = new ProcesoMAECargaClasificacionClienteForm();
		return J;
	}

	/**
	 * @param evt
	 */
	public void validarProcesoGuardar(ActionEvent evt) {
		try {

			ProcesoMAECargaClasificacionClienteForm f = (ProcesoMAECargaClasificacionClienteForm) this.formProceso;
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

		ProcesoMAECargaClasificacionClienteForm f = (ProcesoMAECargaClasificacionClienteForm) this.formProceso;
		ProcesoMAECargaClasificacionClienteService service = (ProcesoMAECargaClasificacionClienteService) getBean("spusicc.procesoMAECargaClasificacionClienteService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		params = BeanUtils.describe(f);
		params.put("codigoUsuario", usuario.getLogin());
		service.executeActualizarCargaClasificacionClientes(params);
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
		ProcesoMAECargaClasificacionClienteForm f = (ProcesoMAECargaClasificacionClienteForm) this.formProceso;
		f.setFlagBotonValidar(false);
		f.setFlagBotonActualizar(false);
		f.setFlagBotonReporte(true);

		recargarTipologiaClientes(f);

//		this.addInfo(
//				"Mensaje",
//				getResourceMessage("procesoMAECargaClasificacionClienteForm.proceso.ok"));
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
		ProcesoMAECargaClasificacionClienteForm f = (ProcesoMAECargaClasificacionClienteForm) this.formProceso;
		f.reset();
		this.mostrarBotonExecute = false;
		this.mostrarTerceraGrilla = false;

		// Obtener parametria de tipoClasificacion
		GenericoService genericoService = (GenericoService) getBean("genericoService");
		ParametroPais parametroPais = new ParametroPais();
		parametroPais.setCodigoPais(pais.getCodigo());
		parametroPais.setCodigoSistema(Constants.MAE_CODIGO_SISTEMA);
		// parametroPais.setCodigoParametro(Constants.MAE_CODIGO_PARAMETRO_IND_TIPO_CLASIFICACION_PAIS);
		parametroPais
				.setNombreParametro(Constants.MAE_NOMBRE_PARAMETRO_IND_TIPO_CLASIFICACION_PAIS);
		parametroPais.setValorParametro(Constants.NUMERO_UNO);
		parametroPais.setIndicadorActivo(Constants.ESTADO_ACTIVO);

		List lstParametros = genericoService.getParametrosPais(parametroPais);

		if (lstParametros != null && lstParametros.size() > 0)
			f.setIndicadorTipoClasificacionPais(Constants.ESTADO_ACTIVO);

		// seteamos la ruta temporal dodne guardar el excel
		ProcesoZONActualizarUnidadesGeograficasService serviceUnidad = (ProcesoZONActualizarUnidadesGeograficasService) getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");
		f.setDirectorioTemporal(serviceUnidad.obtenerPathUpload(pais
				.getCodigo()));

		// cargando en session la lista de concursos habilitados

		this.siccTipoClienteList = interfazSiCCService
				.getTiposClientesByCodigoISOOID(usuario.getIdioma()
						.getCodigoISO());

		f.setOidSubTipoCliente(Constants.OID_SUBTIPO_CLIENTE_DEFAULT);
		f.setOidTipoCliente(Constants.OID_TIPO_CLIENTE_DEFAULT);
		f.setOidTipoClasificacion(null);
		f.setOidClasificacion(null);

		recargarTipologiaClientes(f);

		this.codigoIdiomaISO = usuario.getIdioma().getCodigoISO();
		f.setFlagBotonValidar(false);
		f.setFlagBotonActualizar(false);
		f.setFlagBotonReporte(false);
		f.setNumeroLote("");
		
		//limpiando el flag de validacion de archivo
		this.viewValida = false;
		this.mostrarPanelAdicionalProceso= false; 
		this.maeClasificacionesArchivoList = new ArrayList();
			

	}

	/**
	 * @param f
	 */
	private void recargarTipologiaClientes(
			ProcesoMAECargaClasificacionClienteForm f) {
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		// ProcesoMAECargaClasificacionClienteForm f =
		// (ProcesoMAECargaClasificacionClienteForm) this.formProceso;

		ArrayList temp = new ArrayList();
		temp = new ArrayList();
		temp.add(f.getOidTipoCliente());

		this.siccSubTipoClienteList = aSvc
				.getSubTiposClientesPorPaisTipoClientesOID(usuario.getIdioma()
						.getCodigoISO(), temp);

		temp = new ArrayList();
		temp.add(f.getOidSubTipoCliente());
		LabelValue[] listTiposClasificiones = aSvc
				.getTiposClasificacionesByCriteriaMultipleOIDParamPais(
						pais.getCodigo(), usuario.getIdioma().getCodigoISO(),
						Constants.OID_TIPO_CLIENTE_DEFAULT, temp,
						f.getIndicadorTipoClasificacionPais());

		this.siccTipoClasificacionList = listTiposClasificiones;

		if (f.getOidTipoClasificacion() == null)
			f.setOidTipoClasificacion(listTiposClasificiones[0].getValue());

		ArrayList temp2 = new ArrayList();
		temp2 = new ArrayList();
		temp2.add(f.getOidTipoClasificacion());

		this.siccClasificacionList = aSvc
				.getClasificacionesByCriteriaMultipleOID(usuario.getIdioma()
						.getCodigoISO(), Constants.OID_TIPO_CLIENTE_DEFAULT,
						temp, temp2);
		if(this.siccClasificacionList != null && this.siccClasificacionList.length > 0)
			if (f.getOidClasificacion() == null)
				f.setOidClasificacion(this.siccClasificacionList[0].getValue());


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

		ProcesoMAECargaClasificacionClienteForm f = (ProcesoMAECargaClasificacionClienteForm) this.formProceso;
		recargarTipologiaClientes(f);

		// obtenemos el service
		ProcesoMAECargaClasificacionClienteService service = (ProcesoMAECargaClasificacionClienteService) getBean("spusicc.procesoMAECargaClasificacionClienteService");
		// Cargamos el archivo de la maquina del cliente al servidor

		UploadedFile archi = event.getFile();
		uploadArchivo(archi);
		f.setArchivo(archi);

		// Obtenemos la extension del archivo
		String extensionArchivo = obtenerExtensionArchivo(f.getNombreArchivo());
		f.setExtensionArchivo(extensionArchivo);

		Map criteria = new HashMap();
		criteria.put("directorioTemporal", f.getDirectorioTemporal());
		criteria.put("nombreArchivo", f.getNombreArchivo());
		criteria.put("usuario", usuario);
		criteria.put("oidTipoCliente", f.getOidTipoCliente());
		criteria.put("oidSubTipoCliente", f.getOidSubTipoCliente());
		criteria.put("oidTipoClasificacion", f.getOidTipoClasificacion());
		criteria.put("oidClasificacion", f.getOidClasificacion());

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
		
		this.maeClasificacionesArchivoList = new ArrayList();
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

		ProcesoMAECargaClasificacionClienteForm f = (ProcesoMAECargaClasificacionClienteForm) this.formProceso;
		
		if(StringUtils.isBlank(f.getOidTipoCliente())){
			this.addError("Error: ", this.getResourceMessage("procesoMAECargaClasificacionClienteForm.oidTipoCliente.requerido")); 
			return;
		}
		
		if(StringUtils.isBlank(f.getOidSubTipoCliente())){
			this.addError("Error: ",  this.getResourceMessage("procesoMAECargaClasificacionClienteForm.oidSubTipoCliente.requerido"));
			return;
		}
		
		if(StringUtils.isBlank(f.getOidTipoClasificacion())){
			this.addError("Error: ",  this.getResourceMessage("procesoMAECargaClasificacionClienteForm.oidTipoClasificacion.requerido"));
			return;
		}
		
		if(StringUtils.isBlank(f.getOidClasificacion())){
			this.addError("Error: ", this.getResourceMessage("procesoMAECargaClasificacionClienteForm.oidClasificacion.requerido"));
			return;
		}
		
		
		recargarTipologiaClientes(f);

		Map params = BeanUtils.describe(f);

		// obtenemos el service
		ProcesoMAECargaClasificacionClienteService service = (ProcesoMAECargaClasificacionClienteService) getBean("spusicc.procesoMAECargaClasificacionClienteService");

		// validamos los datos cargados del archivo excel
		List resultados = service
				.executeValidarCargaClasificacionClientes(params);
		int totalErrores = resultados.size();
		int totalValidos = Integer.parseInt(f.getNumRegistros()) - totalErrores;

		f.setNumRegistrosError(String.valueOf(totalErrores));
		f.setNumRegistrosValido(String.valueOf(totalValidos));

		this.maeClasificacionesArchivoList = resultados;
		if (this.maeClasificacionesArchivoList != null)
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
		ProcesoMAECargaClasificacionClienteForm f = (ProcesoMAECargaClasificacionClienteForm) this.formProceso;

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
	 * Carga subtipos de clientes
	 * 
	 * @param val
	 */
	public void loadSubTiposClientes(ValueChangeEvent val) {
		try {
			ProcesoMAECargaClasificacionClienteForm p = (ProcesoMAECargaClasificacionClienteForm) this.formProceso;
			ArrayList values = new ArrayList();
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			String valor = (String) val.getNewValue();
			
			if(StringUtils.isNotBlank(valor)){
				values.add(valor);
				this.siccSubTipoClienteList = ajax.getSubTiposClientesPorPaisTipoClientesOID(codigoIdiomaISO, values);				
				
				if(this.siccSubTipoClienteList != null && this.siccSubTipoClienteList.length > 0){
					p.setOidSubTipoCliente(this.siccSubTipoClienteList[0].getValue());
				}
								
				this.siccTipoClasificacionList = new LabelValue[]{};
				this.siccClasificacionList = new LabelValue[]{};
				
			}
			
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * CArga tipo de clasificaciones
	 * 
	 * @param val
	 */
	public void loadTiposClasificaciones(ValueChangeEvent val) {
		try {
			ProcesoMAECargaClasificacionClienteForm p = (ProcesoMAECargaClasificacionClienteForm) this.formProceso;
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			ArrayList values = new ArrayList();
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			String valor = (String) val.getNewValue();
			
			if(StringUtils.isNotBlank(valor)){
				values.add(valor);				
				this.siccTipoClasificacionList = ajax.getTiposClasificacionesByCriteriaMultipleOIDParamPais(pais.getCodigo(), codigoIdiomaISO, p.getOidTipoCliente(), values, p.getIndicadorTipoClasificacionPais());
				
				if(this.siccTipoClasificacionList != null && this.siccTipoClasificacionList.length > 0){
					p.setOidTipoClasificacion(this.siccTipoClasificacionList[0].getValue());
				}
				
				this.siccClasificacionList = new LabelValue[]{};
			}
			
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * Carga clasificaciones
	 * 
	 * @param val
	 */
	public void loadClasificaciones(ValueChangeEvent val) {
		try {
			ProcesoMAECargaClasificacionClienteForm p = (ProcesoMAECargaClasificacionClienteForm) this.formProceso;
			ArrayList valuesSubTipoCliente = new ArrayList();
			ArrayList valuesClasificacion = new ArrayList();
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			String valor = (String) val.getNewValue();
			
			valuesSubTipoCliente.add(p.getOidSubTipoCliente());
			
			if(StringUtils.isNotBlank(valor)){
				valuesClasificacion.add(valor);
				this.siccClasificacionList = ajax.getClasificacionesByCriteriaMultipleOID(codigoIdiomaISO, p.getOidTipoCliente(), valuesSubTipoCliente, valuesClasificacion);
				
				if(this.siccClasificacionList != null && this.siccClasificacionList.length > 0)
					p.setOidClasificacion(this.siccClasificacionList[0].getValue());
			}			
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * Limpia la pantalla
	 */
	public void limpiara() {

		this.maeClasificacionesArchivoList = null;
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
		
		ProcesoMAECargaClasificacionClienteForm f = (ProcesoMAECargaClasificacionClienteForm)this.formProceso;
		
		if(Integer.parseInt(f.getNumRegistrosError())>0){
			this.msjDialog = this.getResourceMessage("procesoMAECargaClasificacionClienteForm.process.valido.errores");
			
		}
		else{
			this.msjDialog = this.getResourceMessage("procesoMAECargaClasificacionClienteForm.process.valido");
		}
		
		return msj;
	
		
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
	public void setSiccTipoClasificacionList(
			LabelValue[] siccTipoClasificacionList) {
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
	public List getMaeClasificacionesArchivoList() {
		return maeClasificacionesArchivoList;
	}

	/**
	 * @param maeClasificacionesArchivoList
	 */
	public void setMaeClasificacionesArchivoList(
			List maeClasificacionesArchivoList) {
		this.maeClasificacionesArchivoList = maeClasificacionesArchivoList;
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
	 * @return the reporteMAECargaClasificacionClientes
	 */
	public ReporteMAECargaClasificacionClientesAction getReporteMAECargaClasificacionClientes() {
		return reporteMAECargaClasificacionClientes;
	}

	/**
	 * @param reporteMAECargaClasificacionClientes
	 *            the reporteMAECargaClasificacionClientes to set
	 */
	public void setReporteMAECargaClasificacionClientes(
			ReporteMAECargaClasificacionClientesAction reporteMAECargaClasificacionClientes) {
		this.reporteMAECargaClasificacionClientes = reporteMAECargaClasificacionClientes;
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
}