package biz.belcorp.ssicc.web.spusicc.inc.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.inc.ProcesoINCMigracionPuntosConsultoraService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaClientesPOPUPSearchAction;
import biz.belcorp.ssicc.web.spusicc.inc.form.ProcesoINCMigracionPuntosConsultoraForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProcesoINCMigracionPuntosConsultoraAction extends
		BaseProcesoAbstractAction {

	private static final long serialVersionUID = 1L;
	private LabelValue[] siccRegionList;
	private LabelValue[] siccZonaList;
	private List incAmbitoList;
	private List incConcursosMigracionPuntosList;
	private List incMigracionPuntosArchivoList1 = new ArrayList();
	private String viewValida;
	private Integer totalAmbito;
	private boolean mostrarPopupCliente;
	private static final String POPUP_CLIENTE = "POPUP_CLIENTE";
	private String attachment = "";
	private Boolean mostrarGrilla;
	private Object beanProcesoINCMigracionPuntosConsultora;
	private DataTableModel dataTableRegionZona;
	private DataTableModel dataArchivoList;
	public Boolean mostrarPanel1;
	public Boolean mostrarPanel2;
	public Boolean mostrarPanel3;
	private String valorRadio;
	private Boolean mostrarNombre;
	
	@ManagedProperty(value = "#{reporteINCMigracionPuntosConcursoAction}")
	private ReporteINCMigracionPuntosConcursoAction reporteINCMigracionPuntosConcurso;

	/**
	 * @return the valorRadio
	 */
	public String getValorRadio() {
		return valorRadio;
	}

	/**
	 * @param valorRadio
	 *            the valorRadio to set
	 */
	public void setValorRadio(String valorRadio) {
		this.valorRadio = valorRadio;
	}

	@ManagedProperty(value = "#{busquedaClientesPOPUPSearchAction}")
	private BusquedaClientesPOPUPSearchAction busquedaClientesPOPUPSearchAction;

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setInvocarPopup(java.lang.String)
	 */
	@SuppressWarnings("static-access")
	@Override
	protected void setInvocarPopup(String accion) {
		if (accion.equals(this.POPUP_CLIENTE)) {
			this.mostrarPopupCliente = true;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setAceptarPopup(javax.faces.event.ActionEvent, java.lang.String)
	 */
	@SuppressWarnings("static-access")
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopupHipCliente' method");
		}
		this.mostrarProcesoBatch = true;
		this.mostrarPopupCliente = false;
		if (accion.equals(this.POPUP_CLIENTE)) {
			this.busquedaClientesPOPUPSearchAction.verificarRegistro(event);
			if (this.busquedaClientesPOPUPSearchAction.isSeleccionoRegistro()) {
				Map cliente = (Map) this.busquedaClientesPOPUPSearchAction
						.getBeanRegistroSeleccionado();
				ProcesoINCMigracionPuntosConsultoraForm f = (ProcesoINCMigracionPuntosConsultoraForm) this.formProceso;
				String codigoCliente = null;
				codigoCliente = cliente.get("codigo").toString();
				f.setCodigoCliente(codigoCliente);
				String nombre = cliente.get("nombre1").toString() + " "
						+ cliente.get("apellido1").toString() + " "
						+ cliente.get("apellido2").toString();
				f.setNombreCliente(nombre);
				this.busquedaClientesPOPUPSearchAction
						.setBeanRegistroSeleccionado(null);
				this.mostrarNombre = true;
			}
		}
		if (log.isDebugEnabled()) {
			log.debug("Finish 'PopupHipCliente' method");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setSalirPopup()
	 */
	@Override
	protected void setSalirPopup() {
		this.mostrarProcesoBatch = true;
		this.mostrarPopupCliente = false;
		this.busquedaClientesPOPUPSearchAction
				.setBeanRegistroSeleccionado(null);
	}

	/**
	 * Carga el archivo excel que viene del request e inserta su informacion en
	 * tabla
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void cargar() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'cargar' method");
		}
		try {
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

			ProcesoINCMigracionPuntosConsultoraForm f = (ProcesoINCMigracionPuntosConsultoraForm) this.formProceso;
			// recargarTipologiaClientes(request, f);

			// obtenemos el service
			ProcesoINCMigracionPuntosConsultoraService service = (ProcesoINCMigracionPuntosConsultoraService) getBean("spusicc.procesoINCMigracionPuntosConsultoraService");
			// Cargamos el archivo de la maquina del cliente al servidor
			// uploadArchivo();

			// Obtenemos la extension del archivo
			String extensionArchivo = obtenerExtensionArchivo(f
					.getNombreArchivo());
			f.setExtensionArchivo(extensionArchivo);

			Map criteria = new HashMap();
			criteria.put("directorioTemporal", f.getDirectorioTemporal());
			criteria.put("nombreArchivo", f.getNombreArchivo());
			criteria.put("usuario", usuario);

			// validamos el archivo excel y en criteria mandamos que estructura
			// es
			// sin period o con periodo
			Map resultados = service.cargarArchivoExcel(criteria);
			f.setNumeroCarga((String) resultados.get("numeroCarga"));
			f.setNumRegistros((String) resultados.get("totalRegistros"));

			f.setNumRegistrosError(Constants.NUMERO_CERO);
			f.setNumRegistrosValido(Constants.NUMERO_CERO);

			borrarFichero(f.getDirectorioTemporal(), f.getNombreArchivo());
			this.viewValida = Constants.NUMERO_UNO;// flag para mostrar el
													// resultado de
													// la validacion

			f.setFlagBotonValidar(true);
			f.setFlagBotonActualizar(false);
			this.validar();

		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

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

	/**
	 * Cargar zonas
	 * 
	 * @param val
	 */
	public void loadZonas(ValueChangeEvent val) {
		try {
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			String[] valores = (String[]) val.getNewValue();
			if (valores.length > 0) {
				String[] valoresN = new String[valores.length];
				int j = 0;
				for (int i = 0; i < valores.length; i++) {
					if (valores[i] != null) {
						valoresN[j] = valores[i].split("__")[0];
						j++;
					}
				}

				this.siccZonaList = ajax.getZonasByMultipleOidRegiones(
						new ArrayList(Arrays.asList(valoresN)), "X");
			} else
				this.siccZonaList = null;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * Carga el archivo excel que viene del request e inserta su informacion en
	 * tabla
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

		try {
			ProcesoINCMigracionPuntosConsultoraForm f = (ProcesoINCMigracionPuntosConsultoraForm) this.formProceso;
			Map params = BeanUtils.describe(f);

			// obtenemos el service
			ProcesoINCMigracionPuntosConsultoraService service = (ProcesoINCMigracionPuntosConsultoraService) getBean("spusicc.procesoINCMigracionPuntosConsultoraService");

			// validamos los datos cargados del archivo excel
			List resultados = service
					.executeValidarMigracionPuntosConsultora(params);
			int totalErrores = resultados.size();
			int totalValidos = Integer.parseInt(f.getNumRegistros())
					- totalErrores;

			f.setNumRegistrosError(String.valueOf(totalErrores));
			f.setNumRegistrosValido(String.valueOf(totalValidos));
			this.incMigracionPuntosArchivoList1 = new ArrayList();
			this.incMigracionPuntosArchivoList1 = resultados;
			if (this.incMigracionPuntosArchivoList1 != null) {
				this.mostrarGrilla = true;
			}

			f.setFlagBotonValidar(false);
			f.setFlagBotonActualizar(true);
			this.dataArchivoList = new DataTableModel(
					incMigracionPuntosArchivoList1);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * Metodo para subir archivo al servidor
	 * 
	 * @param event
	 */
	public void handleFileUpload(FileUploadEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("handleFileUpload");
		}
		try {
			ProcesoINCMigracionPuntosConsultoraForm f = (ProcesoINCMigracionPuntosConsultoraForm) this.formProceso;
			if (event != null) {
				f.setArchivo(event.getFile());
				this.setAttachment(event.getFile().getFileName());
				this.uploadArchivo();
				this.cargar();
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * carga el archivo
	 * 
	 * @param form
	 * @throws Exception
	 */
	private void uploadArchivo() {
		ProcesoINCMigracionPuntosConsultoraForm f = (ProcesoINCMigracionPuntosConsultoraForm) this.formProceso;
		try {
			// recuperamos el fichero
			UploadedFile archivo = f.getArchivo();
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
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

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
	 * @param evt
	 */
	public void ejecutarReportePDF(ActionEvent evt) {
		try {
			ProcesoINCMigracionPuntosConsultoraForm f = (ProcesoINCMigracionPuntosConsultoraForm) this.formProceso;
			String numeroCarga = f.getNumeroCarga();
			this.reporteINCMigracionPuntosConcurso
					.setFormatoExportacion("PDF");
			this.reporteINCMigracionPuntosConcurso
					.setNumeroCarga(numeroCarga);

			this.reporteINCMigracionPuntosConcurso.getFormReporte()
					.setFormatoExportacion("PDF");
			this.reporteINCMigracionPuntosConcurso.executeReporte();

			this.redireccionarPagina("reporteINCMigracionPuntosConcursoForm");
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * Inicializando
	 */
	public void valoresIniciales() {
		this.mostrarPanel1 = true;
		this.valorRadio = "1";
		this.mostrarPanel2 = false;
		this.mostrarPanel3 = false;
	}

	/**
	 * @param e
	 */
	public void habilitarPaneles(AjaxBehaviorEvent e) {
		try {
			String valor = this.valorRadio;
			if (valor.equals("1")) {
				this.mostrarPanel1 = true;
				this.mostrarPanel2 = false;
				this.mostrarPanel3 = false;
			} else if (valor.equals("2")) {
				this.mostrarPanel1 = false;
				this.mostrarPanel2 = true;
				this.mostrarPanel3 = false;
				// form.setCodigoPeriodoFin(null);
			} else if (valor.equals("3")) {
				this.mostrarPanel1 = false;
				this.mostrarPanel2 = false;
				this.mostrarPanel3 = true;
			}
			ProcesoINCMigracionPuntosConsultoraForm f = (ProcesoINCMigracionPuntosConsultoraForm) this.formProceso;
			f.setIndicadorMigracion(valor);
		} catch (Exception e2) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e2));
		}
	}

	public void validarCodigoConsultora() {
		try {
			ProcesoINCMigracionPuntosConsultoraForm f = (ProcesoINCMigracionPuntosConsultoraForm) this.formProceso;
			String codigoCliente = f.getCodigoCliente();
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			String nombreCliente = aSvc.getExisteCodigoCliente(
					f.getOidPais(), codigoCliente);

			String mensaje = null;
			if (StringUtils.isNotBlank(nombreCliente)) {
				
				String[] lista = StringUtils.split(nombreCliente, "|");
				nombreCliente = lista[1];
				f.setNombreCliente(nombreCliente);
				this.mostrarNombre = true;
				return;
			} else {
				mensaje = "Codigo de Cliente no existe, por favor ingrese un codigo valido para hacer la busqueda";
				this.addError("Error : ", mensaje);
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	
	@Override
	public String setValidarProceso() {
		ProcesoINCMigracionPuntosConsultoraForm f = (ProcesoINCMigracionPuntosConsultoraForm) this.formProceso;
		String codigoOrigen = f.getNumeroConcursoOrigen();
		String codigoFinal = f.getNumeroConcursoDestino();
		String mensaje = null;
		if (StringUtils.equalsIgnoreCase(codigoOrigen, codigoFinal)) {
			mensaje = this.getResourceMessage("procesoINCMigracionPuntosConsultoraForm.msg.validacionConcurso");
			return mensaje;
		}

		return "";
	}
	
	/**
	 * Insertando ambito
	 */
	public void insertAmbito() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'insertAmbitoGeografico' method");
		}
		try {
			ProcesoINCMigracionPuntosConsultoraForm f = (ProcesoINCMigracionPuntosConsultoraForm) this.formProceso;

			List detalList = this.incAmbitoList;
			log.debug("formulario  :  " + f);

			// obtenemos las regiones ingresadas
			Map mapRegiones = new HashMap();
			if (f.getRegiones() != null) {
				for (int i = 0; i < f.getRegiones().length; i++) {
					String region = f.getRegiones()[i];

					String[] datosRegion = region.split("__");
					mapRegiones.put(datosRegion[0], datosRegion[1]);
				}
			}

			// obtenemos las zonas ingresadas y verificamos si ya han sido
			// ingresadas anteriormente
			if (f.getZonas() != null) {
				for (int i = 0; i < f.getZonas().length; i++) {
					String zona = f.getZonas()[i];

					String[] datosZona = zona.split("__");
					String oidZona = datosZona[1];
					String oidRegion = datosZona[0];
					boolean encontrado = false;

					for (int j = 0; j < detalList.size(); j++) {
						Map ambitoAux = (Map) detalList.get(j);
						String oidZonaAux = (String) ambitoAux.get("oidZona");

						if ((oidZonaAux != null)
								&& (oidZonaAux.equals(oidZona))) {
							// ya se encuentre en la lista de ambitos actual
							encontrado = true;
							break;
						}
					}

					if (!encontrado) {
						Map ambito = new HashMap();
						ambito.put("oidRegion", oidRegion); // oidRegion
						ambito.put("oidZona", oidZona);
						ambito.put("descripcionZona", datosZona[2]);
						String descripcionRegion = (String) mapRegiones
								.get(oidRegion.toString());
						ambito.put("descripcionRegion", descripcionRegion);

						// si existe la region sin zona registrada, lo
						// eliminamos
						for (int j = 0; j < detalList.size(); j++) {
							Map ambitoAux = (Map) detalList.get(j);
							String oidRegionAux = (String) ambitoAux
									.get("oidRegion");
							String oidZonaAux = (String) ambitoAux
									.get("oidZona");

							if (oidRegionAux.equals(oidRegion)
									&& oidZonaAux == null) {
								detalList.remove(j);
								break;
							}
						}

						detalList.add(ambito);
					}
				}

			}

			// obtenemos las regiones ingresadas y verificamos si ya han sido
			// ingresadas anteriormente
			if (f.getRegiones() != null) {
				for (int i = 0; i < f.getRegiones().length; i++) {
					String region = f.getRegiones()[i];

					String[] datosRegion = region.split("__");
					String oidRegion = datosRegion[0];
					String descripcionRegion = datosRegion[1];

					boolean encontrado = false;

					for (int j = 0; j < detalList.size(); j++) {
						Map ambitoAux = (Map) detalList.get(j);
						String oidRegionAux = (String) ambitoAux
								.get("oidRegion");

						if (oidRegionAux.equals(oidRegion)) {
							// ya se encuentre en la lista de ambitos actual
							encontrado = true;
							break;
						}
					}

					if (!encontrado) {
						Map ambito = new HashMap();
						ambito.put("oidRegion", oidRegion); // oidRegion
						ambito.put("oidZona", null);
						ambito.put("descripcionZona", "");
						ambito.put("descripcionRegion", descripcionRegion);

						detalList.add(ambito);
					}
				}
			}
			this.incAmbitoList = detalList;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * Eliminar ambito
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void deleteAmbito() {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'deleteAmbito' method");
		}
		try {
			Map data = (Map) this.beanProcesoINCMigracionPuntosConsultora;

			List detalList = new ArrayList();
			if (data != null) {
				String oidRegion = data.get("oidRegion").toString();
				String oidZona = (String) data.get("oidZona");

				for (int j = 0; j < incAmbitoList.size(); j++) {
					Map mapAux = (Map) incAmbitoList.get(j);
					String region = mapAux.get("oidRegion").toString();
					String zona = (String) mapAux.get("oidZona");

					if (StringUtils.isBlank(oidZona) && StringUtils.isBlank(zona)) {
						if (!oidRegion.equals(region))
							detalList.add(mapAux);
					} else if (oidRegion.equals(region) && oidZona.equals(zona)) {

					} else
						detalList.add(mapAux);
				}

				this.incAmbitoList = detalList;
			}
			this.dataTableRegionZona = new DataTableModel(incAmbitoList);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * @throws Exception
	 */
	public void ejecutaProcesoAction(ActionEvent evt){

		try {
			ProcesoINCMigracionPuntosConsultoraForm f = (ProcesoINCMigracionPuntosConsultoraForm) this.formProceso;
			String codigoOrigen = f.getNumeroConcursoOrigen();
			String codigoFinal = f.getNumeroConcursoDestino();
			String mensaje = null;
			if (codigoOrigen.equalsIgnoreCase(codigoFinal)) {
				mensaje = this
						.getResourceMessage("procesoINCMigracionPuntosConsultoraForm.msg.validacionConcurso");
				// this.addError("Error : ", mensaje);
				this.addError("Error : ", mensaje);
				return;
			}

			Map<String, Object> params = null;
			params = BeanUtils.describe(f);
			this.executeProcess(params);
			this.afterExecuteProcess(f);
//			this.addInfo("Info : ", this.getResourceMessage("procesoINCCargaPuntajeBonificadoForm.proceso.ok"));
			return;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
		
	}
	
	
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoINCMigracionPuntosConsultoraForm form = new ProcesoINCMigracionPuntosConsultoraForm();
		return form;
	}
	
	@Override
	public void afterExecuteProcess(BaseProcesoForm form) throws Exception {
		this.viewValida = null;
		this.incMigracionPuntosArchivoList1 = null;
		
		ProcesoINCMigracionPuntosConsultoraForm f = (ProcesoINCMigracionPuntosConsultoraForm) form;
		f.setFlagBotonValidar(false);
		f.setFlagBotonActualizar(false);
		this.incAmbitoList = new ArrayList();
		this.totalAmbito = 0;
		this.dataTableRegionZona = new DataTableModel(this.incAmbitoList);
		this.addInfo("Info : ", this.getResourceMessage("procesoINCMigracionPuntosConsultoraForm.proceso.ok"));
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		ProcesoINCMigracionPuntosConsultoraForm f = (ProcesoINCMigracionPuntosConsultoraForm) this.formProceso;

		ProcesoINCMigracionPuntosConsultoraService service = (ProcesoINCMigracionPuntosConsultoraService) getBean("spusicc.procesoINCMigracionPuntosConsultoraService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		List listAmbitos = this.incAmbitoList;
		params.put("listAmbitos", listAmbitos);
		params.put("codigoUsuario", usuario.getLogin());
		service.executeActualizarMigracionPuntosConsultora(params);
		f.setNumeroCarga((String) params.get("numeroCarga"));			
		return params;
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

		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		this.mostrarBotonExecute = false;

		ProcesoINCMigracionPuntosConsultoraForm f = (ProcesoINCMigracionPuntosConsultoraForm) this.formProceso;
		// seteamos la ruta temporal dodne guardar el excel
		ProcesoZONActualizarUnidadesGeograficasService serviceUnidad = (ProcesoZONActualizarUnidadesGeograficasService) getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");
		f.setDirectorioTemporal(serviceUnidad.obtenerPathUpload(pais
				.getCodigo()));

		ProcesoINCMigracionPuntosConsultoraService service = (ProcesoINCMigracionPuntosConsultoraService) getBean("spusicc.procesoINCMigracionPuntosConsultoraService");
		valoresIniciales();
		// Obtenemos los concursos para Migracion de Puntos
		List listConcursos = service.getListConcursosMigracionPuntos();
		this.incConcursosMigracionPuntosList = listConcursos;

		this.siccRegionList = aSvc.getRegionesByOidSubGerencia("", "X");
		this.incAmbitoList = new ArrayList();
		this.dataTableRegionZona = new DataTableModel(this.incAmbitoList);
		this.mostrarPanel1 = true;
		this.mostrarNombre = false;
		this.mostrarGrilla = false;

		this.totalAmbito = 0;

		f.setCodigoPais(pais.getCodigo());
		f.setCodigoCliente("");
		f.setNombreCliente("");
		f.setIndicadorMigracion("1");
		f.setRegiones(null);
		f.setZonas(null);
		f.setNumeroCarga("0");

		// Obtenemos oid Pais
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		String oidPais = clienteService.getOidPais(criteria);
		f.setOidPais(oidPais);

		f.setFlagBotonValidar(false);
		f.setFlagBotonActualizar(false);

		// limpiando el flag de validacion de archivo
	}


	/**
	 * @return the siccRegionList
	 */
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 *            the siccRegionList to set
	 */
	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccZonaList
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 *            the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the incAmbitoList
	 */
	public List getIncAmbitoList() {
		return incAmbitoList;
	}

	/**
	 * @param incAmbitoList
	 *            the incAmbitoList to set
	 */
	public void setIncAmbitoList(List incAmbitoList) {
		this.incAmbitoList = incAmbitoList;
	}

	/**
	 * @return the viewValida
	 */
	public String getViewValida() {
		return viewValida;
	}

	/**
	 * @param viewValida
	 *            the viewValida to set
	 */
	public void setViewValida(String viewValida) {
		this.viewValida = viewValida;
	}

	/**
	 * @return the totalAmbito
	 */
	public Integer getTotalAmbito() {
		return totalAmbito;
	}

	/**
	 * @param totalAmbito
	 *            the totalAmbito to set
	 */
	public void setTotalAmbito(Integer totalAmbito) {
		this.totalAmbito = totalAmbito;
	}

	/**
	 * @return the mostrarPopupCliente
	 */
	public boolean isMostrarPopupCliente() {
		return mostrarPopupCliente;
	}

	/**
	 * @param mostrarPopupCliente
	 *            the mostrarPopupCliente to set
	 */
	public void setMostrarPopupCliente(boolean mostrarPopupCliente) {
		this.mostrarPopupCliente = mostrarPopupCliente;
	}

	/**
	 * @return the busquedaClientesPOPUPSearchAction
	 */
	public BusquedaClientesPOPUPSearchAction getBusquedaClientesPOPUPSearchAction() {
		return busquedaClientesPOPUPSearchAction;
	}

	/**
	 * @param busquedaClientesPOPUPSearchAction
	 *            the busquedaClientesPOPUPSearchAction to set
	 */
	public void setBusquedaClientesPOPUPSearchAction(
			BusquedaClientesPOPUPSearchAction busquedaClientesPOPUPSearchAction) {
		this.busquedaClientesPOPUPSearchAction = busquedaClientesPOPUPSearchAction;
	}

	/**
	 * @return the popupCliente
	 */
	public static String getPopupCliente() {
		return POPUP_CLIENTE;
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
	 * @return the mostrarGrilla
	 */
	public Boolean getMostrarGrilla() {
		return mostrarGrilla;
	}

	/**
	 * @param mostrarGrilla
	 *            the mostrarGrilla to set
	 */
	public void setMostrarGrilla(Boolean mostrarGrilla) {
		this.mostrarGrilla = mostrarGrilla;
	}

	/**
	 * @return the beanProcesoINCMigracionPuntosConsultora
	 */
	public Object getBeanProcesoINCMigracionPuntosConsultora() {
		return beanProcesoINCMigracionPuntosConsultora;
	}

	/**
	 * @param beanProcesoINCMigracionPuntosConsultora
	 *            the beanProcesoINCMigracionPuntosConsultora to set
	 */
	public void setBeanProcesoINCMigracionPuntosConsultora(
			Object beanProcesoINCMigracionPuntosConsultora) {
		this.beanProcesoINCMigracionPuntosConsultora = beanProcesoINCMigracionPuntosConsultora;
	}

	/**
	 * @return the dataTableRegionZona
	 */
	public DataTableModel getDataTableRegionZona() {
		return dataTableRegionZona;
	}

	/**
	 * @param dataTableRegionZona
	 *            the dataTableRegionZona to set
	 */
	public void setDataTableRegionZona(DataTableModel dataTableRegionZona) {
		this.dataTableRegionZona = dataTableRegionZona;
	}

	/**
	 * @return the mostrarPanel1
	 */
	public Boolean getMostrarPanel1() {
		return mostrarPanel1;
	}

	/**
	 * @param mostrarPanel1
	 *            the mostrarPanel1 to set
	 */
	public void setMostrarPanel1(Boolean mostrarPanel1) {
		this.mostrarPanel1 = mostrarPanel1;
	}

	/**
	 * @return the mostrarPanel2
	 */
	public Boolean getMostrarPanel2() {
		return mostrarPanel2;
	}

	/**
	 * @param mostrarPanel2
	 *            the mostrarPanel2 to set
	 */
	public void setMostrarPanel2(Boolean mostrarPanel2) {
		this.mostrarPanel2 = mostrarPanel2;
	}

	/**
	 * @return the mostrarPanel3
	 */
	public Boolean getMostrarPanel3() {
		return mostrarPanel3;
	}

	/**
	 * @param mostrarPanel3
	 *            the mostrarPanel3 to set
	 */
	public void setMostrarPanel3(Boolean mostrarPanel3) {
		this.mostrarPanel3 = mostrarPanel3;
	}

	/**
	 * @return the mostrarNombre
	 */
	public Boolean getMostrarNombre() {
		return mostrarNombre;
	}

	/**
	 * @param mostrarNombre
	 *            the mostrarNombre to set
	 */
	public void setMostrarNombre(Boolean mostrarNombre) {
		this.mostrarNombre = mostrarNombre;
	}

	/**
	 * @return the incConcursosMigracionPuntosList
	 */
	public List getIncConcursosMigracionPuntosList() {
		return incConcursosMigracionPuntosList;
	}

	/**
	 * @param incConcursosMigracionPuntosList
	 *            the incConcursosMigracionPuntosList to set
	 */
	public void setIncConcursosMigracionPuntosList(
			List incConcursosMigracionPuntosList) {
		this.incConcursosMigracionPuntosList = incConcursosMigracionPuntosList;
	}

	/**
	 * @return the incMigracionPuntosArchivoList1
	 */
	public List getIncMigracionPuntosArchivoList1() {
		return incMigracionPuntosArchivoList1;
	}

	/**
	 * @param incMigracionPuntosArchivoList1
	 *            the incMigracionPuntosArchivoList1 to set
	 */
	public void setIncMigracionPuntosArchivoList1(
			List incMigracionPuntosArchivoList1) {
		this.incMigracionPuntosArchivoList1 = incMigracionPuntosArchivoList1;
	}

	/**
	 * @return the dataArchivoList
	 */
	public DataTableModel getDataArchivoList() {
		return dataArchivoList;
	}

	/**
	 * @param dataArchivoList
	 *            the dataArchivoList to set
	 */
	public void setDataArchivoList(DataTableModel dataArchivoList) {
		this.dataArchivoList = dataArchivoList;
	}

	/**
	 * @return the reporteINCMigracionPuntosConcurso
	 */
	public ReporteINCMigracionPuntosConcursoAction getReporteINCMigracionPuntosConcurso() {
		return reporteINCMigracionPuntosConcurso;
	}

	/**
	 * @param reporteINCMigracionPuntosConcurso the reporteINCMigracionPuntosConcurso to set
	 */
	public void setReporteINCMigracionPuntosConcurso(
			ReporteINCMigracionPuntosConcursoAction reporteINCMigracionPuntosConcurso) {
		this.reporteINCMigracionPuntosConcurso = reporteINCMigracionPuntosConcurso;
	}
	
	

}