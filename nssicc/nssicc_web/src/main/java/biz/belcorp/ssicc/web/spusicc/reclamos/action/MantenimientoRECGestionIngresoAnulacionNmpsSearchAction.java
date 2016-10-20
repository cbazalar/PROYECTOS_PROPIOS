package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECGestionIngresoAnulacionNmpsSearchService;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasService;
import biz.belcorp.ssicc.service.util.ExcelUtil;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.MantenimientoRECGestionIngresoAnulacionNmpsExecuteForm;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.MantenimientoRECGestionIngresoAnulacionNmpsSearchForm;

@SessionScoped
@ManagedBean
public class MantenimientoRECGestionIngresoAnulacionNmpsSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 7091462473417168959L;
	
	
	private List siccMarcaList;
	private List siccCanalList;
	private String codigoPais;
	private List siccRegionList;
	private List recMotivoAnulacionList;
	private List recTipoOperacionList;
	private LabelValue[] recTipoOperacionLabelValue;
	private Object[] grillaSeleccionadaObject;
	private String indicadorNotaMercaderiaPerdida;
	private List mantenimientoRECGestionIngresoAnulacionNmpsConsultoraPedidoList;
	private boolean flagNotaMercaderiaPerdida;
	private boolean flagGenerarEnvia;	
	private String codigoProcesoActual;
	private DataTableModel dataTableRec;
	private String mensajeExecute;
	private String totalRegistrosRecuperados;
	private String totalRegistrosOk;
	private String totalRegistrosError;
	private String nombreArchivo;
	
	@ManagedProperty(value = "#{mantenimientoRECGestionIngresoAnulacionNmpsExecuteAction}")
	private MantenimientoRECGestionIngresoAnulacionNmpsExecuteAction executeAction;

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoRECGestionIngresoAnulacionNmpsSearchForm form = new MantenimientoRECGestionIngresoAnulacionNmpsSearchForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		try {
			log.debug("Seting Attributes1.");
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			codigoPais = pais.getCodigo();
			Map criteriaOperacion = new HashMap();
			criteriaOperacion.put("codigoPais", codigoPais);
			MantenimientoRECGestionIngresoAnulacionNmpsSearchForm f = (MantenimientoRECGestionIngresoAnulacionNmpsSearchForm)this.formBusqueda;
			MantenimientoRECGestionIngresoAnulacionNmpsSearchService service = (MantenimientoRECGestionIngresoAnulacionNmpsSearchService) getBean("spusicc.mantenimientoRECGestionIngresoAnulacionNmpsSearchService");

			// seteamos la ruta temporal donde guardar el excel
			ProcesoZONActualizarUnidadesGeograficasService serviceUnidad = (ProcesoZONActualizarUnidadesGeograficasService) getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");
			f.setDirectorioTemporal(serviceUnidad.obtenerPathUpload(codigoPais));

			f.setTipoSeleccion(Constants.CHECK_TIPO_01);
			loadCombos();
			this.totalRegistrosRecuperados="";
			this.totalRegistrosOk="";
			this.totalRegistrosError="";

			String codigoDevolucion = Constants.STO_CODIGO_ANULACION;
			criteriaOperacion.put("codigoDevolucion", codigoDevolucion);

			ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

			ArrayList datosList = new ArrayList();

			datosList = (ArrayList) reporteService
					.getListaPeriodosByBasCtrlFact(codigoPais, "0");
			Base base = new Base();

			if (datosList.size() > 0) {
				for (int i = 0; i < datosList.size(); i++) {
					base = (Base) datosList.get(i);
					f.setCodigoProceso(base.getCodigo());
					codigoProcesoActual = base.getCodigo();
				}
			}

			List result = service.getMotivosDevolucion(criteriaOperacion);
			this.recMotivoAnulacionList = service.getMotivosDevolucion(criteriaOperacion);
			Map aux = (Map) result.get(0);
			String codigoOperacionAnulacionAcopuesto = MapUtils.getString(aux,"codigo")
					+ "|"+ MapUtils.getString(aux, "codigoOperacionAnulacion");
			f.setMotivoAnulacion(codigoOperacionAnulacionAcopuesto);

			criteriaOperacion.put("varOperAnul",MapUtils.getString(aux, "codigoOperacionAnulacion"));
			// Combo de Tipo Operacion
			// session.removeAttribute(Constants.REC_TIPO_OPERACION_LIST);

			List tiposAnulacion = service.getTiposOperacionIngresoAnulaciones(criteriaOperacion);
			this.recTipoOperacionList = tiposAnulacion;

			if (tiposAnulacion != null && tiposAnulacion.size() > 0) {
				Base tipo = (Base) tiposAnulacion.get(0);
				f.setTipoAnulacion(tipo.getCodigo());
			}
			
			//Mostrar Tipo de Anulacion
			AjaxService ajax = (AjaxService) getBean("ajaxService");	
			String valor=MapUtils.getString(aux, "codigoOperacionAnulacion");
			this.recTipoOperacionLabelValue=ajax.getTiposAnulacionByMotivo(valor);

			criteriaOperacion.put("codigoSistema", Constants.CODIGO_SISTEMA_REC);
			criteriaOperacion.put("codigoParametro",Constants.REC_VALOR_PARAMETRO_NOTA_MERCADERIA_PERDIDA);
			this.indicadorNotaMercaderiaPerdida = service.getIndicadorNotaMercaderiaPerdida(criteriaOperacion);

			f.setTipoSeleccionActual("01");
			f.setCodigoConsultora("");
			f.setNumeroPedido("");
			f.setNumeroNMP("");
			f.setObservaciones("");
			
			this.flagNotaMercaderiaPerdida = false;
			this.flagGenerarEnvia = false;
			this.mostrarBotonSalir = false;
			this.mostrarBotonSave = false;
			this.nombreArchivo="";

			// session.removeAttribute("numeroLote");

		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		}
	}

	/**
	 * Seteando Combos
	 * 
	 * @param request
	 * @throws Exception
	 */
	private void loadCombos() throws Exception {
		log.debug("Loading Combos.");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		this.siccMarcaList = service.getMarcas();
		this.siccCanalList = service.getCanalesByCodigoISO(usuario.getIdioma()
				.getCodigoISO());

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", codigoPais);

		this.siccRegionList = reporteService.getListaGenerico(
				"getRegionesByPais", criteriaOperacion);

	}

	public String setValidarConfirmar(String accion) {
		MantenimientoRECGestionIngresoAnulacionNmpsSearchForm f = (MantenimientoRECGestionIngresoAnulacionNmpsSearchForm)this.formBusqueda;
		if (accion.equals("ELIMINAR")) {
			if (datatableBusqueda == null)
				return this.getResourceMessage("errors.sin.registros");
			else if (this.grillaSeleccionadaObject == null)
				return this.getResourceMessage("errors.select.item");
		} else if (accion.equals("EXECUTE")) {
			if (datatableBusqueda == null)
				return this.getResourceMessage("errors.sin.registros");			
			if (flagNotaMercaderiaPerdida)
				mensajeExecute = this.getResourceMessage("confirm.execute.processFlagS");
			else
				mensajeExecute = this.getResourceMessage("confirm.execute.processFlagN");
		}
		return null;
	}

	protected List setFindAttributes() throws Exception {

		log.debug("Seting find Attributes2.");

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoRECGestionIngresoAnulacionNmpsSearchForm f = (MantenimientoRECGestionIngresoAnulacionNmpsSearchForm)this.formBusqueda;
		if (flagNotaMercaderiaPerdida)
			f.setFlagNotaMercaderiaPerdida("S");
		else
			f.setFlagNotaMercaderiaPerdida("N");

		if (flagGenerarEnvia)
			f.setFlagGenerarEnvia("S");
		else
			f.setFlagGenerarEnvia("N");

		// ################ VALIDACIONES ######################
		int periodoActual = Integer.parseInt(codigoProcesoActual);
		int codigoProceso = Integer.parseInt(f.getCodigoProceso());
		if (periodoActual > codigoProceso)
			throw new Exception(
					"LA CAMPAÑA DE PROCESO DEBE DE SER MAYOR O IGUAL A LA CAMPAÑA ACTUAL");

		if (f.getTipoSeleccion().equals("01")) {
			if (StringUtils.isBlank(f.getNombreArchivo()))
				throw new Exception("Seleccione un Archivo");
		}

		if (f.getTipoSeleccion().equals("02")) {
			if (StringUtils.isBlank(f.getCodigoConsultora()))
				throw new Exception("Falta codigo Consultora");
			if (StringUtils.isBlank(f.getNumeroPedido()))
				throw new Exception("Falta Numero Pedido");
		}

		GenericoService genericoService = (GenericoService) getBean("genericoService");

		Map params = new HashMap();
		params.put("codigoConsultora", f.getCodigoConsultora());
		params.put("numeroPedido", f.getNumeroPedido());

		params.put("codigoPeriodo", f.getCodigoPeriodo());
		params.put("fechaFacturacion", f.getFechaFacturacion());
		params.put(
				"regionList",
				(f.getRegionList() == null || f.getRegionList()[0].equals("")) ? new ArrayList()
						: Arrays.asList(f.getRegionList()));
		params.put("zonaList", (f.getZonaList() == null || f.getZonaList()[0]
				.equals("")) ? new ArrayList() : Arrays.asList(f.getZonaList()));
		params.put(
				"seccionList",
				(f.getSeccionList() == null || f.getSeccionList()[0].equals("")) ? new ArrayList()
						: Arrays.asList(f.getSeccionList()));
		params.put(
				"territorioList",
				(f.getTerritorioList() == null || f.getTerritorioList()[0]
						.equals("")) ? new ArrayList() : Arrays.asList(f
						.getTerritorioList()));
		log.debug("usuariox " + usuario.getLogin());
		params.put("usuario", usuario.getLogin());
		log.debug("f.getCodigoPais():  " + codigoPais);
		params.put("numNmp", f.getNumeroNMP());
		String sTipoSeleccionActual = "";

		MantenimientoRECGestionIngresoAnulacionNmpsSearchService service = (MantenimientoRECGestionIngresoAnulacionNmpsSearchService) getBean("spusicc.mantenimientoRECGestionIngresoAnulacionNmpsSearchService");

		// List datosList = null;
		ArrayList datosList = new ArrayList();

		// sTipoSeleccionActual = f.getTipoSeleccionActual();
		sTipoSeleccionActual = f.getTipoSeleccion();
		log.debug("TipoSeleccionActual " + sTipoSeleccionActual);

		// recuperamos el fichero
		// FormFile archivo = f.getArchivo();
		// f.setNombreArchivo(archivo.getFileName());

		if (sTipoSeleccionActual.equals("01")) {

			String extensionArchivo = obtenerExtensionArchivo(f
					.getNombreArchivo());

			log.debug("aqui validamos ");
			if (extensionArchivo
					.equalsIgnoreCase(Constants.EXTENSION_ARCHIVO_TXT)) {
				log.debug("=====> Formato Texto TXT");
				datosList = (ArrayList) uploadArchivo2(f);
			} else if (extensionArchivo
					.equalsIgnoreCase(Constants.EXTENSION_ARCHIVO_EXCEL)) {
				log.debug("=====> Formato Excel XLS");
				datosList = (ArrayList) cargarExcel(f);
				log.debug("Tamaño de Lista ===>" + datosList.size());

				ParametroPais parametroPais = new ParametroPais();
				parametroPais.setCodigoPais(codigoPais);
				parametroPais.setCodigoSistema("REC");
				parametroPais.setNombreParametro("cantRegPermiExcel");
				parametroPais.setIndicadorActivo(Constants.NUMERO_UNO);

				String cantidadRegistrosPermitidos = null;
				List lstParametros = genericoService
						.getParametrosPais(parametroPais);

				if (lstParametros != null && lstParametros.size() > 0) {
					ParametroPais ps = (ParametroPais) lstParametros.get(0);
					cantidadRegistrosPermitidos = ps.getValorParametro();

					if (StringUtils.isNotBlank(cantidadRegistrosPermitidos)) {
						if (datosList.size() > Integer
								.parseInt(cantidadRegistrosPermitidos)) {
							throw new Exception(
									this.getResourceMessage("mantenimientoRECGestionIngresoAnulacionNmpsSearch.excede.numeroRegPermitidos"));

						}
					} else {
						throw new Exception(
								this.getResourceMessage("mantenimientoRECGestionIngresoAnulacionNmpsSearch.excede.numeroRegPermitidos"));

					}
				}
			} else {
				datosList = new ArrayList();
			}
		} else if (f.getTipoSeleccion().equals("02")) {
			datosList = (ArrayList) service.getConsultoraPedidoList(params);
		} else if (f.getTipoSeleccion().equals("03")) {
			datosList = (ArrayList) service.getCampanaList(params);
		}

		if (datosList.size() > 0) {
			for (int i = 0; i < datosList.size(); i++) {
				int posicion = i;
				log.debug("posicion: " + posicion);
				Map cambioMap = new HashMap();

				log.debug("ingresoAnulacionNMPSList.get(posicion-1) "
						+ datosList.get(posicion));
				cambioMap = (HashMap) datosList.get(posicion);
				cambioMap.put("cod_pais", codigoPais);

				if (!f.getTipoSeleccion().equals("01")) {
					cambioMap.put("num_npm", f.getNumeroNMP());
				}
			}
			// f.setSelectedItems(f.getSelectedItemsVacio());
			// session.removeAttribute(Constants.REC_CONSULTORA_TOTAL_OK);
			// session.removeAttribute(Constants.REC_CONSULTORA_TOTAL_ERROR);
			// session.removeAttribute(Constants.REC_CONSULTORA_TOTAL_NMPS_LIST);
			this.totalRegistrosRecuperados=String.valueOf(datosList.size());
			
		}

		// session.removeAttribute(Constants.REC_CONSULTORA_PEDIDO_NMPS_LIST);
		this.mantenimientoRECGestionIngresoAnulacionNmpsConsultoraPedidoList = datosList;

		// ------------------------
		// Map criteriaOperacion = new HashMap();
		// criteriaOperacion
		// .put("varOperAnul",
		// StringUtils.splitPreserveAllTokens(
		// f.getMotivoAnulacion(), '|')[1]);
		// List tiposAnulacion = service
		// .getTiposOperacionIngresoAnulaciones(criteriaOperacion);
		// this. = tiposAnulacion;
		//
		// if (tiposAnulacion == null || tiposAnulacion.size() == 0)
		// f.setTipoAnulacion("");

		// ---------------------------
		datatableBusqueda = new DataTableModel(
				this.mantenimientoRECGestionIngresoAnulacionNmpsConsultoraPedidoList);
		return datosList;
	}

	/**
	 * carga el archivo al temporal
	 * 
	 * @param form
	 * @throws Exception
	 */
	private void uploadArchivo(MantenimientoRECGestionIngresoAnulacionNmpsSearchForm f)	throws Exception {
		if(f.getArchivo()!=null){
			// recuperamos el fichero
			f.setNombreArchivo(f.getArchivo().getFileName());
			log.debug("Archivo Upload: " + f.getArchivo());
			log.debug("Nombre Archivo Upload: " + f.getNombreArchivo());
			// leyemos el stream de entrada
			InputStream is = f.getArchivo().getInputstream();
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
	}

	private List uploadArchivo2(MantenimientoRECGestionIngresoAnulacionNmpsSearchForm form)	throws Exception {
		MantenimientoRECGestionIngresoAnulacionNmpsSearchForm f = (MantenimientoRECGestionIngresoAnulacionNmpsSearchForm) form;

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		ArrayList listaArchivo = new ArrayList();
		// recuperamos el fichero
		if(f.getArchivo()!=null){
		f.setNombreArchivo(f.getArchivo().getFileName());
		log.debug("Nombre Archivo Upload: " + f.getNombreArchivo());

		// leyemos el stream de entrada
		InputStream is = f.getArchivo().getInputstream();

		BufferedReader di = new BufferedReader(new InputStreamReader(is));

		String linea;
		String valCampo;
		linea = di.readLine();
		log.debug("linea 1 : " + linea);

		while (linea != null) {
			Map ingresoAnulacionNMPSMap = new HashMap();
			int i = 0;
			String str = "";
			int lnValor = linea.length();

			valCampo = linea.substring(0, 30).trim();
			while (valCampo.charAt(i++) == 48) {
				str = valCampo.substring(i, valCampo.length());
			}
			ingresoAnulacionNMPSMap.put("num_recl", str);

			valCampo = linea.substring(30, 40).trim();
			i = 0;
			while (valCampo.charAt(i++) == 48) {
				str = valCampo.substring(i, valCampo.length());
			}
			ingresoAnulacionNMPSMap.put("num_doc", str);

			ingresoAnulacionNMPSMap.put("cod_clie", linea.substring(40, 55)
					.trim());
			ingresoAnulacionNMPSMap.put("cod_usua", usuario.getLogin()); // linea.substring(55,75).trim());
			ingresoAnulacionNMPSMap.put("fec_ingr", linea.substring(75, 83)
					.trim());
			ingresoAnulacionNMPSMap.put("cod_pais", linea
					.substring(83, lnValor).trim());
			ingresoAnulacionNMPSMap.put("cod_est_proc", "0");
			ingresoAnulacionNMPSMap.put("cod_error", "-");

			listaArchivo.add(ingresoAnulacionNMPSMap);
			linea = di.readLine();
			log.debug("linea 2 : " + linea);
		} }
		return listaArchivo;
	}

	private String obtenerExtensionArchivo(String nombreArchivo)
			throws Exception {
		log.debug("EXTENSION"
				+ nombreArchivo.substring(nombreArchivo.length() - 3));
		return nombreArchivo.substring(nombreArchivo.length() - 3);
	}

	private List cargarExcel(
			MantenimientoRECGestionIngresoAnulacionNmpsSearchForm f)
			throws Exception {

		MantenimientoRECGestionIngresoAnulacionNmpsSearchService service = (MantenimientoRECGestionIngresoAnulacionNmpsSearchService) getBean("spusicc.mantenimientoRECGestionIngresoAnulacionNmpsSearchService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		List listaExcel = null;

		uploadArchivo(f);
		String extensionArchivo = obtenerExtensionArchivo(f.getNombreArchivo());
		f.setExtensionArchivo(extensionArchivo);

		Map criteria = new HashMap();
		criteria.put("directorioTemporal", f.getDirectorioTemporal());
		criteria.put("nombreArchivo", f.getNombreArchivo());
		criteria.put("cod_usua", usuario.getLogin()); // linea.substring(55,75).trim());
		criteria.put("fec_ingr", "");
		criteria.put("cod_pais", codigoPais);
		criteria.put("cod_est_proc", "0");
		criteria.put("cod_error", "-");
		criteria.put("flagNotaMercaderiaPerdida",
				f.getFlagNotaMercaderiaPerdida());
		// validamos el archivo excel
		boolean isValido = validarArchivoExcel(criteria);
		List listaError = null;

		if (isValido) {

			listaExcel = service.executeCargaArchivoExcel(criteria);

		} else {

			listaExcel = new ArrayList();
		}

		borrarFichero(f.getDirectorioTemporal(), f.getNombreArchivo());

		return listaExcel;
	}

	private void borrarFichero(String path, String nombreArchivo) {
		try {
			File file = new File(path, nombreArchivo);
			file.delete();
			log.debug("Se elimino el archivo");
		} catch (Exception ex) {
			log.debug("No se pudo eliminar el archivo " + ex.getMessage());
		}
	}

	private boolean validarArchivoExcel(Map criteria) throws Exception {
		String directorioTemporal = (String) criteria.get("directorioTemporal");
		String nombreArchivo = (String) criteria.get("nombreArchivo");
		boolean valor = true;

		// Abrimos el archivo Excel para su procesamiento
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);
		// nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);
		while (excelUtil.hasNext()) {
			Map mapRow = excelUtil.next();
			if (mapRow.size() > 10) {// las columnas recogidas + el numero de
										// fila de fila procesda
				valor = false;
				break;
			}
		}

		excelUtil.cerrar();
		return valor;
	}

	public void upload(FileUploadEvent event) throws IOException, Exception {
		if (log.isDebugEnabled()) {
			log.debug(" metodo uploadSeccionFile");
		}
		MantenimientoRECGestionIngresoAnulacionNmpsSearchForm f = (MantenimientoRECGestionIngresoAnulacionNmpsSearchForm)this.formBusqueda;
		f.setArchivo(event.getFile());
		f.setNombreArchivo(f.getArchivo().getFileName());
		setNombreArchivo(f.getArchivo().getFileName());
	
	}
	
	//Metodo que carga el Tipo de Anulacion
	public void loadTipos(ValueChangeEvent val){
		if(val.getNewValue()!=null){
			String tipo=val.getNewValue().toString();
			if(StringUtils.isNotBlank(tipo)){				
				AjaxService ajax = (AjaxService) getBean("ajaxService");				
				String [] cad= new String[2];
				cad=StringUtils.split(tipo, "|");
				this.recTipoOperacionLabelValue=ajax.getTiposAnulacionByMotivo(cad[1]);
			}
		}else
			this.recTipoOperacionLabelValue=null;
		
	}
	
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoRECGestionIngresoAnulacionNmpsSearch";
	}
	
	//Elimina los datos de la Grilla
	public void deleteRec(ActionEvent event) {
		try {
			MantenimientoRECGestionIngresoAnulacionNmpsSearchForm f = (MantenimientoRECGestionIngresoAnulacionNmpsSearchForm)this.formBusqueda;
			ArrayList ingresoAnulacionNMPSList = (ArrayList) this.mantenimientoRECGestionIngresoAnulacionNmpsConsultoraPedidoList;			
				for(int j=0;j<this.grillaSeleccionadaObject.length;j++){
					Map data = (Map)this.grillaSeleccionadaObject[j];	
					ingresoAnulacionNMPSList.remove(data);				
				}		
			
			this.mantenimientoRECGestionIngresoAnulacionNmpsConsultoraPedidoList = ingresoAnulacionNMPSList;
			listaBusqueda = ingresoAnulacionNMPSList;
			datatableBusqueda = new DataTableModel(ingresoAnulacionNMPSList);
			this.totalRegistrosRecuperados = String.valueOf(ingresoAnulacionNMPSList.size());
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * Metodo que invoca al Ejecutar Proceso
	 * @param event
	 * @throws Exception
	 */
	public void executeRec(ActionEvent event) throws Exception {
		try {			
			MantenimientoRECGestionIngresoAnulacionNmpsSearchForm f = (MantenimientoRECGestionIngresoAnulacionNmpsSearchForm)this.formBusqueda;
			if (this.flagNotaMercaderiaPerdida)
				f.setFlagNotaMercaderiaPerdida("S");
			else
				f.setFlagNotaMercaderiaPerdida("N");

			if (this.flagGenerarEnvia)
				f.setFlagGenerarEnvia("S");
			else
				f.setFlagGenerarEnvia("N");
			MantenimientoRECGestionIngresoAnulacionNmpsSearchService service = (MantenimientoRECGestionIngresoAnulacionNmpsSearchService) getBean("spusicc.mantenimientoRECGestionIngresoAnulacionNmpsSearchService");
			String numeroLote = service.getNumeroLote();	
			this.executeAction.setNumeroLote(numeroLote);
			this.executeAction.setCodigoConsultora(f.getCodigoConsultora());
			this.executeAction.setMotivoAnulacion(f.getMotivoAnulacion());
			this.executeAction.setTipoAnulacion(f.getTipoAnulacion());
			this.executeAction.setObservaciones(f.getObservaciones());
			this.executeAction.setTipoSeleccion(f.getTipoSeleccion());
			this.executeAction.setCodigoProceso(f.getCodigoProceso());
			
			MantenimientoRECGestionIngresoAnulacionNmpsExecuteForm fExecute = (MantenimientoRECGestionIngresoAnulacionNmpsExecuteForm) this.executeAction.getFormProceso();
			fExecute.setCodigoConsultora(f.getCodigoConsultora());
			fExecute.setMotivoAnulacion(f.getMotivoAnulacion());
			fExecute.setTipoAnulacion(f.getTipoAnulacion());
			fExecute.setObservaciones(f.getObservaciones());
			fExecute.setTipoSeleccion(f.getTipoSeleccion());
			fExecute.setCodigoProceso(f.getCodigoProceso());
			fExecute.setFlagGenerarEnvia(f.getFlagGenerarEnvia());
			fExecute.setFlagNotaMercaderia(f.getFlagNotaMercaderiaPerdida());
			this.executeAction.setFormProceso(fExecute);
			
			this.executeAction.setIngresoAnulacionNMPSList(this.mantenimientoRECGestionIngresoAnulacionNmpsConsultoraPedidoList);
			this.executeAction.setEsProcesoBatch(true);
			this.executeAction.setEnEjecucion(true);
			this.executeAction.setActivarSalirPadre(true);
			this.executeAction.setPantallaPadre("mantenimientoRECGestionIngresoAnulacionNmpsSearch.xhtml");
			
			this.executeAction.executeProceso();
			this.redireccionarPagina("mantenimientoRECGestionIngresoAnulacionNmpsExecute");
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub

		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	/**
	 * @return the siccMarcaList
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList
	 *            the siccMarcaList to set
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * @return the siccCanalList
	 */
	public List getSiccCanalList() {
		return siccCanalList;
	}

	/**
	 * @param siccCanalList
	 *            the siccCanalList to set
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the siccRegionList
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 *            the siccRegionList to set
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the recMotivoAnulacionList
	 */
	public List getRecMotivoAnulacionList() {
		return recMotivoAnulacionList;
	}

	/**
	 * @param recMotivoAnulacionList
	 *            the recMotivoAnulacionList to set
	 */
	public void setRecMotivoAnulacionList(List recMotivoAnulacionList) {
		this.recMotivoAnulacionList = recMotivoAnulacionList;
	}

	
	public String getIndicadorNotaMercaderiaPerdida() {
		return indicadorNotaMercaderiaPerdida;
	}

	/**
	 * @param indicadorNotaMercaderiaPerdida
	 *            the indicadorNotaMercaderiaPerdida to set
	 */
	public void setIndicadorNotaMercaderiaPerdida(
			String indicadorNotaMercaderiaPerdida) {
		this.indicadorNotaMercaderiaPerdida = indicadorNotaMercaderiaPerdida;
	}

	/**
	 * @return the
	 *         mantenimientoRECGestionIngresoAnulacionNmpsConsultoraPedidoList
	 */
	public List getMantenimientoRECGestionIngresoAnulacionNmpsConsultoraPedidoList() {
		return mantenimientoRECGestionIngresoAnulacionNmpsConsultoraPedidoList;
	}

	public void setMantenimientoRECGestionIngresoAnulacionNmpsConsultoraPedidoList(
			List mantenimientoRECGestionIngresoAnulacionNmpsConsultoraPedidoList) {
		this.mantenimientoRECGestionIngresoAnulacionNmpsConsultoraPedidoList = mantenimientoRECGestionIngresoAnulacionNmpsConsultoraPedidoList;
	}

	public boolean isFlagNotaMercaderiaPerdida() {
		return flagNotaMercaderiaPerdida;
	}

	public void setFlagNotaMercaderiaPerdida(boolean flagNotaMercaderiaPerdida) {
		this.flagNotaMercaderiaPerdida = flagNotaMercaderiaPerdida;
	}
	
	public boolean isFlagGenerarEnvia() {
		return flagGenerarEnvia;
	}

	/**
	 * @param flagGenerarEnvia
	 *            the flagGenerarEnvia to set
	 */
	public void setFlagGenerarEnvia(boolean flagGenerarEnvia) {
		this.flagGenerarEnvia = flagGenerarEnvia;
	}

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return null;
	}

	

	/**
	 * @return the totalRegistrosRecuperados
	 */
	public String getTotalRegistrosRecuperados() {
		return totalRegistrosRecuperados;
	}

	/**
	 * @param totalRegistrosRecuperados
	 *            the totalRegistrosRecuperados to set
	 */
	public void setTotalRegistrosRecuperados(String totalRegistrosRecuperados) {
		this.totalRegistrosRecuperados = totalRegistrosRecuperados;
	}

	/**
	 * @return the codigoProcesoActual
	 */
	public String getCodigoProcesoActual() {
		return codigoProcesoActual;
	}

	/**
	 * @param codigoProcesoActual
	 *            the codigoProcesoActual to set
	 */
	public void setCodigoProcesoActual(String codigoProcesoActual) {
		this.codigoProcesoActual = codigoProcesoActual;
	}

	/**
	 * @return the dataTableRec
	 */
	public DataTableModel getDataTableRec() {
		return dataTableRec;
	}

	/**
	 * @param dataTableRec
	 *            the dataTableRec to set
	 */
	public void setDataTableRec(DataTableModel dataTableRec) {
		this.dataTableRec = dataTableRec;
	}

	/**
	 * @return the mensajeExecute
	 */
	public String getMensajeExecute() {
		return mensajeExecute;
	}

	/**
	 * @param mensajeExecute
	 *            the mensajeExecute to set
	 */
	public void setMensajeExecute(String mensajeExecute) {
		this.mensajeExecute = mensajeExecute;
	}

	/**
	 * @return the executeAction
	 */
	public MantenimientoRECGestionIngresoAnulacionNmpsExecuteAction getExecuteAction() {
		return executeAction;
	}

	/**
	 * @param executeAction
	 *            the executeAction to set
	 */
	public void setExecuteAction(
			MantenimientoRECGestionIngresoAnulacionNmpsExecuteAction executeAction) {
		this.executeAction = executeAction;
	}

	public LabelValue[] getRecTipoOperacionLabelValue() {
		return recTipoOperacionLabelValue;
	}

	public void setRecTipoOperacionLabelValue(
			LabelValue[] recTipoOperacionLabelValue) {
		this.recTipoOperacionLabelValue = recTipoOperacionLabelValue;
	}

	public Object[] getGrillaSeleccionadaObject() {
		return grillaSeleccionadaObject;
	}

	public void setGrillaSeleccionadaObject(Object[] grillaSeleccionadaObject) {
		this.grillaSeleccionadaObject = grillaSeleccionadaObject;
	}

	public String getTotalRegistrosOk() {
		return totalRegistrosOk;
	}

	public void setTotalRegistrosOk(String totalRegistrosOk) {
		this.totalRegistrosOk = totalRegistrosOk;
	}

	public String getTotalRegistrosError() {
		return totalRegistrosError;
	}

	public void setTotalRegistrosError(String totalRegistrosError) {
		this.totalRegistrosError = totalRegistrosError;
	}

	/**
	 * @return the nombreArchivo
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	/**
	 * @param nombreArchivo the nombreArchivo to set
	 */
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	

}
