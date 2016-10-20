package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECIngresoAtencionesService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOBloqueoControlService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOBloqueoControlForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOBloqueoControlSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoSTOBloqueoControlSearchAction extends	BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -7802492884762041370L;

	private List stoTipoClienteList;
	private List stoRegionList;
	private LabelValue[] stoSubtipoClienteList;
	private LabelValue[] stoTipoClasificacionList;
	private LabelValue[] stoClasificacionList;
	private LabelValue[] stoZonaList;
	private Object[] objetoSeleccionados;
	
	
	private List stoLevantamientoErroresClientesList;
	private List stoResumenClientesList;
	
	private List nuevostoLevantamientoErroresClientesList;
	private List nuevostoResumenClientesList;	
	private LabelValue[] nuevostoSubtipoClienteList;
	private LabelValue[] nuevostoTipoClasificacionList;
	private LabelValue[] nuevostoClasificacionList;	
	private LabelValue[] nuevostoZonaList;
	private String attachmentNuevo;
	private Boolean mostrarGrillaNuevo;	
	
	private String codigoIdiomaISO;
	private String oidSubTipoCliente;
	private String oidSubTipoClasificacion;
	private String oidClasificacion;
	private String codigoZona;
	private List stoBloqueoControlList;
	
	private String indicadorGrabo;
	private double cantidadInsertados = -1;
	private int longitudCampoClientes = 0;
	
	private Boolean mostrarGrilla;
	private String attachment = "";
	private String motivoBloqueo = "C";
	private String msjeSave;
	private boolean mantenimiento = false;

	private String stoMotivoBloqueo = Constants.STO_MOTIVO_ONLINE;
	
	private int longitud = 0;
	private int longitudCampoClientesSize = 0;
	private int longitudCampoClientesMax = 0;
	
	/**
	 * Metodo para realizar el upload	
	 */
	public void handleFileUpload(FileUploadEvent event) {		
		String mensaje = "";
		try {
			MantenimientoSTOBloqueoControlSearchForm f = (MantenimientoSTOBloqueoControlSearchForm) this.formBusqueda;
			if (event != null) {
				f.setClienteFile(event.getFile());
				this.setAttachment(event.getFile().getFileName());		
				this.uploadArchivo();
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}
	
	//Subir archivos de la Pantalla- Nueva 
	public void handleFileUploadNuevo(FileUploadEvent event) {		
		String mensaje = "";
		try {
			MantenimientoSTOBloqueoControlForm f = (MantenimientoSTOBloqueoControlForm) this.formMantenimiento;
			if (event != null) {
				f.setClienteFile(event.getFile());
				this.attachmentNuevo=event.getFile().getFileName();					
				this.uploadArchivoNuevo();
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		this.mantenimiento=false;
		return "mantenimientoSTOBloqueoControlList";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoSTOBloqueoControlForm";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoSTOBloqueoControlSearchForm f = new MantenimientoSTOBloqueoControlSearchForm();
		return f;
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setValidarFind()
	 */
	@Override
	public String setValidarFind() {
		MantenimientoSTOBloqueoControlSearchForm search = (MantenimientoSTOBloqueoControlSearchForm) this.formBusqueda;

		String codigoCliente = "";
		codigoCliente = search.getCodigoCliente();
		
		if (StringUtils.isNotBlank(codigoCliente)) {
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			String codigocliente = ajax.getNombreCliente(codigoCliente);

			if (StringUtils.isBlank(codigocliente)) 
				return "Codigo de Cliente no existe, por favor ingrese un codigo valido para hacer la busqueda";
		}				
	
		return "";
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {

		log.debug("MantenimientoSTOBloqueoControlSearchAction - setFindAttributes");

		MantenimientoSTOBloqueoControlSearchForm f = (MantenimientoSTOBloqueoControlSearchForm) this.formBusqueda;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoSTOBloqueoControlService service = (MantenimientoSTOBloqueoControlService) getBean("spusicc.mantenimientoSTOBloqueoControlService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

//		int aux = validarCodigoConsultora();
//		if (aux == 1) {
//			String mensaje = "Codigo de Cliente no existe, por favor ingrese un codigo valido para hacer la busqueda";
//			this.addError("Error : ", mensaje);
//			return null;
//		}

		Map criteria = new HashMap();

		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		criteria.put("codigoCliente", f.getCodigoCliente());
		criteria.put("codigoIso", usuario.getIdioma().getCodigoISO());
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);

		String motivoBloqueo = f.getMotivoBloqueo();
		if (motivoBloqueo.equals(Constants.STO_MOTIVO_ONLINE)) {
			criteria.put("motivoBloqueo", Constants.STO_MOTIVO_ONLINE);
		} else {
			criteria.put("motivoBloqueo", Constants.STO_MOTIVO_CONTROL);
		}

		if (StringUtils.isNotBlank(f.getCodigoPeriodo()))
			criteria.put("oidPeriodo", reporteService.getOidString(
					"getOidPeriodoByCodigoPeriodo", criteria));
		else
			criteria.put("oidPeriodo", null);

		/*
		 * if(StringUtils.isNotBlank(f.getCodigoCliente()))
		 * criteria.put("oidCliente",
		 * reporteService.getOidString("getOidClienteByCodigoCliente",
		 * criteria)); else criteria.put("oidCliente", null);
		 */

		if (StringUtils.isNotBlank(f.getTipoCliente()))
			criteria.put("oidTipoCliente", f.getTipoCliente());
		else
			criteria.put("oidTipoCliente", null);

		if (StringUtils.isNotBlank(f.getSubTipoCliente())) {
			criteria.put("oidSubTipoCliente", f.getSubTipoCliente());
			this.oidSubTipoCliente = criteria.get("oidSubTipoCliente")
					.toString();
		} else {
			criteria.put("oidSubTipoCliente", null);
			this.oidSubTipoCliente = null;
		}

		if (StringUtils.isNotBlank(f.getTipoClasificacion())) {
			criteria.put("oidSubTipoClasificacion", f.getTipoClasificacion());
			this.oidSubTipoClasificacion = criteria.get("oidSubTipoClasificacion").toString();
		} else {
			criteria.put("oidSubTipoClasificacion", null);
			this.oidSubTipoClasificacion = null;
		}

		if (StringUtils.isNotBlank(f.getClasificacion())) {
			criteria.put("oidClasificacion", f.getClasificacion());
			this.oidClasificacion = criteria.get("oidClasificacion").toString();
		} else {
			criteria.put("oidClasificacion", null);
			this.oidClasificacion = null;
		}

		if (StringUtils.isNotBlank(f.getCodigoRegion()))
			criteria.put("codigoRegion", f.getCodigoRegion());
		else
			criteria.put("codigoRegion", null);

		if (StringUtils.isNotBlank(f.getCodigoZona())) {
			criteria.put("codigoZona", f.getCodigoZona());
			this.codigoZona = criteria.get("codigoZona").toString();
		} else {
			criteria.put("codigoZona", null);
			this.codigoZona = null;
		}

		/*-------------------------*/
		if (StringUtils.isNotBlank(f.getTipoBloqueo())) {
			criteria.put("tipoBloqueo", f.getTipoBloqueo());
		} else {
			criteria.put("tipoBloqueo", null);
		}
		/*-------------------------*/
		String[] arrlistaClientes = new String[0];
		List clienteList = new ArrayList();
		Long longitudPais = pais.getLongitudCodigoCliente();
		String codigoCliente = f.getCodigoCliente();

		List listaClientes = this.stoLevantamientoErroresClientesList; // grilla
																		// del
																		// archivo

		Map param = new HashMap();
		Map map = new HashMap();
		// SI cargo consultoras por el archivo
		if (listaClientes != null) {
			
			for (int i = 0; i < listaClientes.size(); i++) {
				/*
				 * LabelValue bean = (LabelValue) listaClientes.get(i);
				 * param.put("codigoCliente",
				 * StringUtils.leftPad(bean.getLabel(), longitudPais.intValue(),
				 * '0'));
				 */
				map = (Map) listaClientes.get(i);
				codigoCliente = (String) map.get("codigoCliente");
				param.put("codigoCliente", codigoCliente);

				try {
					clienteList.add(reporteService.getOidString("getOidClienteByCodigoCliente", param));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		} else {
			// Si es cargado por la caja de texto
			if (codigoCliente != null && codigoCliente.length() > 0)
				arrlistaClientes = codigoCliente.split(",+");
			else
				clienteList = null;
			for (int i = 0; i < arrlistaClientes.length; i++) {
				param.put("codigoCliente", StringUtils.leftPad(arrlistaClientes[i], longitudPais.intValue(), '0'));
				
				try {
					clienteList.add(reporteService.getOidString("getOidClienteByCodigoCliente", param));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	
		if (clienteList != null)
			criteria.put("clienteList", clienteList);
		/*-------------------------*/

		List list = new ArrayList();
		criteria.put("codigoTipoDocumento", f.getCodigoTipoDocumento());
		list = service.getBloqueoControlList(criteria);
		this.stoBloqueoControlList = list;
		this.datatableBusqueda = new DataTableModel(this.stoBloqueoControlList);
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {		
		return false;
	}

	/**
	 * Metodo para cargar el archivo
	 * 
	 * @throws Exception
	 */
	protected void uploadArchivo() throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'load Clientes from file' method");
		}

		MantenimientoSTOBloqueoControlSearchForm f = (MantenimientoSTOBloqueoControlSearchForm) this.formBusqueda;
		MantenimientoRECIngresoAtencionesService service = (MantenimientoRECIngresoAtencionesService) getBean("spusicc.mantenimientoRECIngresoAtencionesService");
		GenericoService genericoService = (GenericoService) getBean("genericoService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		List listaClientes = new ArrayList();
		String indValidaCodConsultoraDocIdentidad = null;

		ParametroPais parametroPais = new ParametroPais();
		parametroPais.setCodigoPais(pais.getCodigo());
		parametroPais.setCodigoSistema("STO");
		parametroPais.setNombreParametro("indValidaCodConsultoraDocIdentidad");

		List lstParametros = genericoService.getParametrosPais(parametroPais);

		if (lstParametros != null && lstParametros.size() > 0) {
			ParametroPais ps = (ParametroPais) lstParametros.get(0);
			indValidaCodConsultoraDocIdentidad = ps.getValorParametro();
		}

		UploadedFile archivo = f.getClienteFile();
		Map criteria = new HashMap();
		InputStream is = archivo.getInputstream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		Long longitudPais = pais.getLongitudCodigoCliente();
		String linea = "";
		String codigoConsultora = "";
		int cont = 0;
		int numRegistros = 0;

		while (true) {
			linea = br.readLine();
			if (linea == null)
				break;

			codigoConsultora = StringUtils.leftPad(linea.trim(), longitudPais.intValue(), '0');
			criteria.put("codigoConsultora", codigoConsultora);
			LabelValue bean = new LabelValue(codigoConsultora, service.getCodigoConsultora(criteria));

			if (bean.getValue() == null && StringUtils.equals(indValidaCodConsultoraDocIdentidad,Constants.SI)) {
				criteria.put("documentoIdentidad", codigoConsultora);
				String codigoConsultoraPorDocIden = service.getCodigoConsultoraPorDocumentoIdentidad(criteria);

				if (codigoConsultoraPorDocIden == null) {
					bean = new LabelValue(codigoConsultora,	service.getCodigoConsultoraPorDocumentoIdentidad(criteria));
				} else {
					bean = new LabelValue(codigoConsultoraPorDocIden,service.getCodigoConsultoraPorDocumentoIdentidad(criteria));
				}
			}

			listaClientes.add(bean);

			if (bean.getValue() == null) {
				cont++;
			}

			numRegistros++;
		}

		// Se inserta en la tabla temporal
		String oidCarga = service.getOidCargaCliente();
		criteria.put("oid", oidCarga);
		service.insertaClienteFile(listaClientes, criteria);

		// Se obtiene la lista de la tabla temporal
		List list = new ArrayList();
		list = service.getCargaClienteList(criteria);

		f.setCodigosErradosFile("");
		f.setCodigoRegion(f.getCodigoRegion());
		f.setCodigoZona(f.getCodigoZona());

		if (cont != 0)
			f.setCodigosErradosFile("Existe(n) " + cont	+ " codigo(s) errado(s)");

		criteria.put("numRegistros", numRegistros);
		List list2 = new ArrayList();
		list2 = service.getResumenCargaClienteList(criteria);

		this.stoLevantamientoErroresClientesList = new ArrayList();
		this.stoLevantamientoErroresClientesList = list;
		if (stoLevantamientoErroresClientesList != null) {
			this.mostrarGrilla = true;
		}

		this.stoResumenClientesList =  new ArrayList();
		this.stoResumenClientesList = list2;

	}
	//Metodo para subir los archivos -TXT
	protected void uploadArchivoNuevo() throws Exception {
		MantenimientoSTOBloqueoControlForm f = (MantenimientoSTOBloqueoControlForm) this.formMantenimiento;
		MantenimientoRECIngresoAtencionesService service = (MantenimientoRECIngresoAtencionesService) getBean("spusicc.mantenimientoRECIngresoAtencionesService");
		GenericoService genericoService = (GenericoService) getBean("genericoService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		List listaClientes = new ArrayList();
		String indValidaCodConsultoraDocIdentidad = null;

		ParametroPais parametroPais = new ParametroPais();
		parametroPais.setCodigoPais(pais.getCodigo());
		parametroPais.setCodigoSistema("STO");
		parametroPais.setNombreParametro("indValidaCodConsultoraDocIdentidad");

		List lstParametros = genericoService.getParametrosPais(parametroPais);

		if (lstParametros != null && lstParametros.size() > 0) {
			ParametroPais ps = (ParametroPais) lstParametros.get(0);
			indValidaCodConsultoraDocIdentidad = ps.getValorParametro();
		}

		UploadedFile archivo = f.getClienteFile();
		Map criteria = new HashMap();
		InputStream is = archivo.getInputstream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		Long longitudPais = pais.getLongitudCodigoCliente();
		String linea = "";
		String codigoConsultora = "";
		int cont = 0;
		int numRegistros = 0;

		while (true) {
			linea = br.readLine();
			if (linea == null)
				break;

			codigoConsultora = StringUtils.leftPad(linea.trim(),
					longitudPais.intValue(), '0');
			criteria.put("codigoConsultora", codigoConsultora);
			LabelValue bean = new LabelValue(codigoConsultora,service.getCodigoConsultora(criteria));

			if (bean.getValue() == null
					&& StringUtils.equals(indValidaCodConsultoraDocIdentidad,Constants.SI)) {
				criteria.put("documentoIdentidad", codigoConsultora);
				String codigoConsultoraPorDocIden = service.getCodigoConsultoraPorDocumentoIdentidad(criteria);

				if (codigoConsultoraPorDocIden == null) {
					bean = new LabelValue(codigoConsultora,service.getCodigoConsultoraPorDocumentoIdentidad(criteria));
				} else {
					bean = new LabelValue(codigoConsultoraPorDocIden,service.getCodigoConsultoraPorDocumentoIdentidad(criteria));
				}
			}

			listaClientes.add(bean);

			if (bean.getValue() == null) {
				cont++;
			}

			numRegistros++;
		}

		// Se inserta en la tabla temporal
		String oidCarga = service.getOidCargaCliente();
		criteria.put("oid", oidCarga);
		service.insertaClienteFile(listaClientes, criteria);

		// Se obtiene la lista de la tabla temporal
		List list = new ArrayList();
		list = service.getCargaClienteList(criteria);

		f.setCodigosErradosFile("");
		f.setCodigoRegion(f.getCodigoRegion());
		f.setCodigoZona(f.getCodigoZona());

		if (cont != 0)
			f.setCodigosErradosFile("Existe(n) " + cont	+ " codigo(s) errado(s)");

		criteria.put("numRegistros", numRegistros);
		List list2 = new ArrayList();
		list2 = service.getResumenCargaClienteList(criteria);

		this.nuevostoLevantamientoErroresClientesList = list;
		if (this.nuevostoLevantamientoErroresClientesList != null) {
			this.mostrarGrillaNuevo = true;
		}
		this.nuevostoResumenClientesList = list2;

	}

	/**
	 * Metodo para limpiar el form	
	 */
	public void limpiarNuevo(MantenimientoSTOBloqueoControlForm f) throws Exception {		
		f.setCodigoCliente("");	
		f.setCodigoPeriodo("");
		f.setTipoCliente("");// this.tipoCliente = null;
		f.setSubTipoCliente(""); // this.subTipoCliente = null;
		f.setTipoClasificacion(""); // this.tipoClasificacion = null;
		f.setClasificacion(""); // this.clasificacion = null;
		f.setCodigoRegion(""); // this.codigoRegion = null;
		f.setCodigoZona(""); // this.codigoZona = null;
		f.setTipoBloqueo("");
		f.setCodigosErradosFile("");
		this.nuevostoResumenClientesList = null;
		this.nuevostoLevantamientoErroresClientesList = null;
		this.mostrarGrillaNuevo = false;
		this.attachmentNuevo = null;
		this.msjeSave="";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {

		MantenimientoSTOBloqueoControlForm f = (MantenimientoSTOBloqueoControlForm) this.formMantenimiento;

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoSTOBloqueoControlService service = (MantenimientoSTOBloqueoControlService) getBean("spusicc.mantenimientoSTOBloqueoControlService");

		boolean isArchivo = false;

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Map criteria = new HashMap();

		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		criteria.put("codigoCliente", f.getCodigoCliente());
		criteria.put("codigoIso", usuario.getIdioma().getCodigoISO());
		criteria.put("usuario", usuario.getLogin());

		if (StringUtils.isNotBlank(f.getCodigoPeriodo()))
			criteria.put("oidPeriodo", reporteService.getOidString(
					"getOidPeriodoByCodigoPeriodo", criteria));
		else
			criteria.put("oidPeriodo", null);
		if (StringUtils.isNotBlank(f.getTipoCliente()))
			criteria.put("oidTipoCliente", f.getTipoCliente());
		else
			criteria.put("oidTipoCliente", null);

		if (StringUtils.isNotBlank(f.getSubTipoCliente())) {
			criteria.put("oidSubTipoCliente", f.getSubTipoCliente());
			this.oidSubTipoCliente = criteria.get("oidSubTipoCliente")
					.toString();
		} else {
			criteria.put("oidSubTipoCliente", null);
			this.oidSubTipoCliente = null;
		}

		if (StringUtils.isNotBlank(f.getTipoClasificacion())) {
			criteria.put("oidSubTipoClasificacion", f.getTipoClasificacion());
			this.oidSubTipoClasificacion = criteria.get("oidSubTipoClasificacion").toString();
		} else {
			criteria.put("oidSubTipoClasificacion", null);
			this.oidSubTipoClasificacion = null;
		}

		if (StringUtils.isNotBlank(f.getClasificacion())) {
			criteria.put("oidClasificacion", f.getClasificacion());
			this.oidClasificacion = criteria.get("oidClasificacion").toString();
		} else {
			criteria.put("oidClasificacion", null);
			this.oidClasificacion = null;
		}

		if (StringUtils.isNotBlank(f.getCodigoRegion())
				&& !Constants.FORMATEAR_TODOS.equals(f.getCodigoRegion())) {// no
																			// es
																			// vacio
																			// y
																			// diferente
																			// de
																			// todos
			criteria.put("codigoRegion", f.getCodigoRegion());
		} else {

			List listaRegiones = stoRegionList; // List listaRegiones =
												// (List)session.getAttribute(Constants.STO_REGION_FORM_LIST);
			criteria.put("codigoRegion", StringUtils.isEmpty(f
					.getCodigoRegion()) ? Constants.STO_VALOR_OID_NULL: Constants.FORMATEAR_TODOS);
			criteria.put("listaRegionesSTO", listaRegiones);

		}

		if (StringUtils.isNotBlank(f.getCodigoZona())) {
			criteria.put("codigoZona", f.getCodigoZona());
			this.codigoZona = criteria.get("codigoZona").toString();
		} else {
			criteria.put("codigoZona", null);
			this.codigoZona = null;
		}

		criteria.put("fechaCreacion", Calendar.getInstance().getTime());
		criteria.put("observaciones", f.getObservaciones());

		criteria.put("tipoBloqueo", f.getTipoBloqueo());

		/*-------------------------*/
		String[] arrlistaClientes = new String[0];
		List clienteList = new ArrayList(); // result
		Long longitudPais = pais.getLongitudCodigoCliente();
		String codigoCliente = f.getCodigoCliente();
		// List listaClientes =
		// (List)request.getSession().getAttribute(Constants.STO_LEVANTAMIENTO_ERRORES_CLIENTES_LIST);
		// // grilla del archivo
		List listaClientes = nuevostoLevantamientoErroresClientesList;

		Map param = new HashMap();
		Map map = new HashMap();
		// SI cargo consultoras por el archivo
		if (listaClientes != null) {
			isArchivo = true;
			for (int i = 0; i < listaClientes.size(); i++) {
				map = (Map) listaClientes.get(i);
				codigoCliente = (String) map.get("codigoCliente");
				param.put("codigoCliente", codigoCliente);

				try {
					clienteList.add(reporteService.getOidString(
							"getOidClienteByCodigoCliente", param));
				} catch (Exception e) {
				}
			}
		} else {
			// Si es cargado por la caja de texto
			if (codigoCliente.length() > 0)
				arrlistaClientes = codigoCliente.split(",+");
			for (int i = 0; i < arrlistaClientes.length; i++) {
				param.put("codigoCliente",StringUtils.leftPad(arrlistaClientes[i],
								longitudPais.intValue(), '0'));
				try {
					clienteList.add(reporteService.getOidString("getOidClienteByCodigoCliente", param));
				} catch (Exception e) {

				}
			}
		}
		criteria.put("clienteList", (clienteList == null) ? new ArrayList()
				: clienteList);
		/*-------------------------*/

		criteria.put("codigoTipoDocumento", f.getCodigoTipoDocumento());

		if (motivoBloqueo.equals(Constants.STO_MOTIVO_ONLINE)) {
			criteria.put("motivoBloqueo", Constants.STO_MOTIVO_ONLINE);
		} else {
			criteria.put("motivoBloqueo", Constants.STO_MOTIVO_CONTROL);
		}
		stoLevantamientoErroresClientesList = listaClientes;
		int cantidadInsertados = service.insertSTOBloqueoControl(criteria);

		this.indicadorGrabo = Constants.NUMERO_UNO;
		if (isArchivo)
			this.cantidadInsertados = cantidadInsertados;
		else
			this.cantidadInsertados = -1;
		
		if(this.cantidadInsertados==-1){
			if(!StringUtils.equals(f.getMotivoBloqueo(), "O"))
				this.msjeSave=this.getResourceMessage("mensaje.mantenimientoSTOBloqueoControlForm.satisfactorio");
			else
				this.msjeSave=this.getResourceMessage("mensaje.mantenimientoSTOBloqueoControlForm.satisfactorio.online");
		}else{
			if(!StringUtils.equals(f.getMotivoBloqueo(), "O"))
				this.msjeSave=this.getResourceMessage("mensaje.mantenimientoSTOBloqueoControlForm.satisfactorio")+" "+
							"Se procesaron "+cantidadInsertados + " codigo(s) de consultora";
			else
				this.msjeSave=this.getResourceMessage("mensaje.mantenimientoSTOBloqueoControlForm.satisfactorio.online")+ " "+
							"Se procesaron "+cantidadInsertados + " codigo(s) de consultora";			
		}
		
		this.mantenimiento=false;
		
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		this.mantenimiento = true;
	
		MantenimientoSTOBloqueoControlForm f = new MantenimientoSTOBloqueoControlForm();
		ClienteUAGenerarService clienteService = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		limpiarNuevo(f);
		String motivoBloqueo = this.getParametrosPantalla()
				.get("motivoBloqueo"); // agregar el
										// getParametrosPantalla().get("...parametro de la visa...")
		f.setMotivoBloqueo(motivoBloqueo);
		
		Map criteria = new HashMap();		
		criteria.put("codigoPais", pais.getCodigo());
		//Indica campaña activa
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); 
		//Indica que campaña activa se procesa actualmente													
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); 

		
		MantenimientoSTOBloqueoControlService service = (MantenimientoSTOBloqueoControlService) getBean("spusicc.mantenimientoSTOBloqueoControlService");
		f.setTipoBloqueo(Constants.NUMERO_CERO);

		// Carga el combo Tipo cliente
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		this.stoTipoClienteList = interfazSiCCService.getTiposClientesByCodigoISOOID(usuario.getIdioma().getCodigoISO());	
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		this.stoRegionList = reporteService.getListaGenerico("getRegionesByPais", criteria);
	
		this.codigoIdiomaISO = usuario.getIdioma().getCodigoISO();
		this.indicadorGrabo = Constants.NUMERO_CERO;
		this.cantidadInsertados = -1;
//		this.longitudCampoClientes = clienteService.getTamanhoNumeroCliente(pais.getCodigo());
		this.longitud = clienteService.getTamanhoNumeroCliente(pais.getCodigo());
		this.longitudCampoClientesSize = 3 * longitud;
        this.longitudCampoClientesMax = 30 * longitud;
        
		f.setCodigosErradosFile("");
		f.setCodigoTipoDocumento(Constants.STO_TIPO_DOCUMENTO_OCC);

		criteria.put("codigoSistema", "STO");
		criteria.put("nombreParametro", "indicadorPeriodo");
		// criteria.put("valorParametro", value);
		String indicadorPeriodo = service.getParametroGenericoSistema(criteria);
		log.debug("indicadorPeriodo " + indicadorPeriodo);
		f.setIndicadorPeriodo(StringUtils.isEmpty(indicadorPeriodo) ? Constants.NUMERO_CERO: indicadorPeriodo);// 1:habilitado, 0:desahabilitado
		f.setMotivoBloqueo(motivoBloqueo);

		return f;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.mantenimiento = false;
		this.mostrarBotonEliminar=false;
		this.mostrarListaBusqueda=false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonModificar = false;
		
		MantenimientoSTOBloqueoControlSearchForm f = (MantenimientoSTOBloqueoControlSearchForm) this.formBusqueda;
		cleanForm(f);
		this.motivoBloqueo = "C";
		MantenimientoSTOBloqueoControlService service = (MantenimientoSTOBloqueoControlService) getBean("spusicc.mantenimientoSTOBloqueoControlService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		f.setCodigoPais(pais.getCodigo());
		this.mostrarPaginacionDatatableSpinner = false;
		this.nroPaginacionDatatable = 25;
	
		Map criteria = new HashMap();
		String codigoPais = pais.getCodigo();
		criteria.put("codigoPais", codigoPais);
		//Indica campaña activa
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); 
		//Indica campaña activa que se procesa actualmente
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); 
		
		// Carga el combo Tipo cliente
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		this.stoTipoClienteList = interfazSiCCService
				.getTiposClientesByCodigoISOOID(usuario.getIdioma()
						.getCodigoISO());

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		this.stoRegionList = reporteService.getListaGenerico(
				"getRegionesByPais", criteria);

		this.codigoIdiomaISO = usuario.getIdioma().getCodigoISO();

		f.setCodigoTipoDocumento(Constants.STO_TIPO_DOCUMENTO_OCC);

		criteria.put("codigoSistema", "STO");
		criteria.put("nombreParametro", "indicadorPeriodo");
		// criteria.put("valorParametro", value);
		String indicadorPeriodo = service.getParametroGenericoSistema(criteria);
		log.debug("indicadorPeriodo " + indicadorPeriodo);
		f.setIndicadorPeriodo(StringUtils.isEmpty(indicadorPeriodo) ? Constants.NUMERO_CERO
				: indicadorPeriodo);// 1:habilitado, 0:desahabilitado

		String motivoBloqueo = this.getParametrosPantalla()
				.get("motivoBloqueo"); // agregar el
										// getParametrosPantalla().get("...parametro de la visa...")
		f.setMotivoBloqueo(motivoBloqueo);

		// Inicio PER-SiCC-2012-0819
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		String lineaDefecto = ajaxService.getNumeroRegistrosSTO(codigoPais,
				Constants.STO_TIPO_DOCUMENTO_OCC, "1");
		String lineaMaxima = ajaxService.getNumeroRegistrosSTO(codigoPais,
				Constants.STO_TIPO_DOCUMENTO_OCC, "2");
		f.setLineaDefecto(lineaDefecto);
		f.setLineaMaxima(lineaMaxima);
		// Fin PER-SiCC-2012-0819

	}

	/**
	 * @param f
	 */
	private void cleanForm(MantenimientoSTOBloqueoControlSearchForm f) {
		f.setCodigoCliente("");		
		f.setCodigoCliente("");
		f.setCodigoPeriodo("");
		f.setTipoCliente("");// this.tipoCliente = null;
		f.setSubTipoCliente(""); // this.subTipoCliente = null;
		f.setTipoClasificacion(""); // this.tipoClasificacion = null;
		f.setClasificacion(""); // this.clasificacion = null;
		f.setCodigoRegion(""); // this.codigoRegion = null;
		f.setCodigoZona(""); // this.codigoZona = null;
		f.setTipoBloqueo("");
		this.stoResumenClientesList = null;
		this.stoLevantamientoErroresClientesList = null;
		this.mostrarGrilla = false;
		this.attachment = "";
	}

	/**
	 * Metodo para cargar los sub tipos clientes	
	 */
	public void loadSubTiposClientes(ValueChangeEvent val) {
		try {
			String stipos = (String) val.getNewValue();
			ArrayList tipos = new ArrayList();
			if (StringUtils.isNotBlank(stipos)) {
				tipos.add(stipos);
				AjaxService ajax = (AjaxService) getBean("ajaxService");
				if(this.mantenimiento){
					this.nuevostoSubtipoClienteList = ajax.getSubTiposClientesPorPaisTipoClientesOID(this.codigoIdiomaISO, tipos);
					this.nuevostoTipoClasificacionList=null;
					this.nuevostoClasificacionList=null;	
				}else{
					this.stoSubtipoClienteList = ajax.getSubTiposClientesPorPaisTipoClientesOID(this.codigoIdiomaISO, tipos);
					this.stoTipoClasificacionList=null;
					this.stoClasificacionList=null;
				}			
			} else {
				this.stoSubtipoClienteList = null;
				this.nuevostoSubtipoClienteList=null;
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));

		}
	
	}

	/**
	 * Metodo para cargar los tipos de clasificaciones	
	 */
	public void loadTiposClasificaciones(ValueChangeEvent val) {
		if(val.getNewValue() == null) return;
		try {
			MantenimientoSTOBloqueoControlSearchForm search = (MantenimientoSTOBloqueoControlSearchForm) this.formBusqueda;		
			String stipos = (String) val.getNewValue();
			ArrayList listSubtipo = new ArrayList();

			if (StringUtils.isNotBlank(stipos)) {
				listSubtipo.add(stipos);
				AjaxService ajax = (AjaxService) getBean("ajaxService");
				if (this.mantenimiento) {
					MantenimientoSTOBloqueoControlForm f = (MantenimientoSTOBloqueoControlForm) this.formMantenimiento;
					this.nuevostoTipoClasificacionList= ajax.getTiposClasificacionesByCriteriaMultipleOID(
												this.codigoIdiomaISO, f.getTipoCliente(),listSubtipo);
					this.nuevostoClasificacionList=null;
				} else {
					this.stoTipoClasificacionList = ajax.getTiposClasificacionesByCriteriaMultipleOID(
												this.codigoIdiomaISO, search.getTipoCliente(),listSubtipo);
					this.stoClasificacionList=null;
				}
			} else {
				this.stoTipoClasificacionList = null;
				this.nuevostoTipoClasificacionList=null;
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));

		}
		

	}

	/**
	 * Metodo para cargar las clasificaciones	
	 */
	public void loadClasificaciones(ValueChangeEvent val) {		
		try {
			MantenimientoSTOBloqueoControlSearchForm search = (MantenimientoSTOBloqueoControlSearchForm) this.formBusqueda;
			String tipoClasificacion = (String) val.getNewValue();
			ArrayList listTipoClasificacion = new ArrayList();
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			
			if (StringUtils.isNotBlank(tipoClasificacion)) {
				listTipoClasificacion.add(tipoClasificacion);					
				if (this.mantenimiento) {
					MantenimientoSTOBloqueoControlForm f = (MantenimientoSTOBloqueoControlForm) this.formMantenimiento;	
					ArrayList listSubtipoForm = new ArrayList();
					listSubtipoForm.add(f.getSubTipoCliente());
					this.nuevostoClasificacionList = ajax.getClasificacionesByCriteriaMultipleOID(this.codigoIdiomaISO, f.getTipoCliente(),listSubtipoForm, listTipoClasificacion);
				} else {
					ArrayList listSubtipo = new ArrayList();
					listSubtipo.add(search.getSubTipoCliente());
					this.stoClasificacionList = ajax.getClasificacionesByCriteriaMultipleOID(this.codigoIdiomaISO, search.getTipoCliente(),listSubtipo, listTipoClasificacion);
				}
			} else {
				this.stoClasificacionList = null;
				this.nuevostoClasificacionList=null;
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));

		}
		
	}

	/**
	 * Metodo para cargar las zonas
	 * 
	 * @param val
	 */
	public void loadZonas(ValueChangeEvent val) {
		try {
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			String valor = (String) val.getNewValue();
			
			if(StringUtils.isNotBlank(valor)){
				String[] regiones = { valor };
				AjaxService ajax = (AjaxService) getBean("ajaxService");

				if (regiones.length > 0) {
					if (this.mantenimiento) {
						this.nuevostoZonaList = ajax.getZonasMultipleByPaisMarcaCanalRegion(
								pais.getCodigo(), "T", "VD", regiones, "");
					} else {
						this.stoZonaList = ajax.getZonasMultipleByPaisMarcaCanalRegion(
								pais.getCodigo(), "T", "VD", regiones, "");
					}
				} else {
					this.stoZonaList = null;
				}
			}						
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));

		}
		
	}

	/**
	 * Metodo para validar el codigo consultora
	 * 
	 * @return
	 */
	public void validarCodigoConsultora() {
		try {
			MantenimientoSTOBloqueoControlSearchForm search = (MantenimientoSTOBloqueoControlSearchForm) this.formBusqueda;

			String codigoCliente = "";
			codigoCliente = search.getCodigoCliente();
			
			if (StringUtils.isNotBlank(codigoCliente)) {
				AjaxService ajax = (AjaxService) getBean("ajaxService");
				String codigocliente = ajax.getNombreCliente(codigoCliente);

				if (StringUtils.isBlank(codigocliente)) 
					this.addError("Error: ", "Codigo de Cliente no existe, por favor ingrese un codigo valido para hacer la busqueda");
			}				
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
		
	}

	public void validarCodigoConsultoraForm() {
		
		MantenimientoSTOBloqueoControlForm f = (MantenimientoSTOBloqueoControlForm) this.formMantenimiento;
		f.setCodigosErrados("");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		
		try {
			if(StringUtils.isNotBlank(f.getCodigoCliente())){
				String [] fragmentoTexto = f.getCodigoCliente().split(",");
				String newStringClientes = "";
				for (int i=0;i<fragmentoTexto.length;i++){
					String codigo = rellenar(fragmentoTexto[i],"0",this.longitud);
					if (i>0)			
					  newStringClientes = newStringClientes + ',' +codigo;				
					else newStringClientes = newStringClientes +codigo;
				}
				f.setCodigoCliente(newStringClientes);
				String data = ajax.getCodigosErrados( newStringClientes, pais.getCodigo());  
				if(data != null) {
					if(StringUtils.isNotBlank(data))
							f.setCodigosErrados("Los codigo(s): "+data+" no son válidos");
					else
						f.setCodigosErrados("");	
				}				
			}
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}		
	}
	
	/**
	 * Rellena a la izquierda
	 * de una cadena con el tipo indicado
	 * 
	 * @param cadena
	 * @param tipo
	 * @param tam
	 * @return
	 */
	private String rellenar(String cadena,String tipo, int longitud){

		int longitudCadena = cadena.length();		
		String relleno = "";
		int n = 0;
		n = longitud - longitudCadena;
		
		for(int i= 0; i < n; i++) {
		      relleno = relleno +tipo;
		  } 
		cadena = relleno + cadena;		
		return cadena;
	}
	
	//Elimina multiple de registros en la grilla principal
	public void eliminarPrincipal(ActionEvent action){
		try {
			if(this.objetoSeleccionados==null || this.objetoSeleccionados.length<1){
				this.setMensajeAlertaDefault(this.getResourceMessage("errors.select.item"));
				String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
				this.getRequestContext().execute(ventanaConfirmar);
				return;	
			}			
			
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			MantenimientoSTOBloqueoControlService service = (MantenimientoSTOBloqueoControlService) getBean("spusicc.mantenimientoSTOBloqueoControlService");
			int tam=this.objetoSeleccionados.length;
			String []object=new String[tam];
			for(int i=0;i<this.objetoSeleccionados.length;i++){
				Map seleccionados=(Map)this.objetoSeleccionados[i];
				String id=seleccionados.get("codigoBloqueo").toString();
				object[i]=id;
			}

			Map parametros = new HashMap();
			parametros.put("usuario", usuario.getCodigo());
			parametros.put("idSeleccionados", object);

			service.deleteBloqueoControl(parametros);
			setFindAttributes();
			this.addInfo("Info:", this.getResourceMessage("datos.delete.ok"));
			this.objetoSeleccionados=null;
			
		} catch (Exception e) {
			this.addError("Error:", this.obtieneMensajeErrorException(e));
		}
	}
	
	
	public String setValidarMantenimiento(){
		MantenimientoSTOBloqueoControlForm f = (MantenimientoSTOBloqueoControlForm) this.formMantenimiento;
		boolean flag = false;
		if(StringUtils.isBlank(f.getCodigoPeriodo())){
			return "Periodo es un campo requerido";			
		}
		
		if(StringUtils.isBlank(f.getObservaciones())){
			return "Observacion es un campo requerido";			
		}
		
		if(this.nuevostoLevantamientoErroresClientesList != null && this.nuevostoLevantamientoErroresClientesList.size()>0){
			flag = true;
		}
		
		if(StringUtils.isBlank(f.getCodigoRegion())&& StringUtils.isBlank(f.getCodigoCliente()) && flag == false)
			return "Por lo menos Región  o Codigo de Cliente deben estar ingresadas";					
				
		if(StringUtils.isNotBlank(f.getCodigosErradosFile()))
			return "Antes de Guardar por favor corrija el/los Codigo(s) del Cliente";
		
		if(StringUtils.isNotBlank(f.getCodigosErrados())){
			return "Antes de Guardar por favor corrija el/los Codigo(s) del Cliente";
			
		}	
		
		return "";    
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#devuelveMensajeAlertaDefaultAction()
	 */
	@Override
	public String devuelveMensajeAlertaDefaultAction() throws Exception  {		
		return this.msjeSave;	
	}
	
	
	/**
	 * Limpia la carga de archivo del searchForm
	 * 
	 * @param event
	 */
	public void limpiarFile (ActionEvent event){

		if (log.isDebugEnabled()) {
			log.debug("Entering 'limpiar' method");
		}

		MantenimientoSTOBloqueoControlSearchForm f = (MantenimientoSTOBloqueoControlSearchForm)this.formBusqueda;
		f.setCodigoCliente("");
		this.stoLevantamientoErroresClientesList = new ArrayList();
		this.stoResumenClientesList = new ArrayList();
		//this.oid = "";
		this.mostrarGrilla = false;
	}
	
	/**
	 * Limpia la carga de archivo del Form
	 * 
	 * @param event
	 */
	public void limpiarFileForm (ActionEvent event){

		if (log.isDebugEnabled()) {
			log.debug("Entering 'limpiar' method");
		}

		MantenimientoSTOBloqueoControlForm f = (MantenimientoSTOBloqueoControlForm)this.formMantenimiento;
		f.setCodigoCliente("");
		this.nuevostoLevantamientoErroresClientesList = new ArrayList();
		this.nuevostoResumenClientesList = new ArrayList();
		//this.oid = "";
		this.mostrarGrillaNuevo = false;
	}

	/**
	 * @return the stoSubtipoClienteList
	 */
	public LabelValue[] getStoSubtipoClienteList() {
		return stoSubtipoClienteList;
	}

	/**
	 * @param stoSubtipoClienteList
	 *            the stoSubtipoClienteList to set
	 */
	public void setStoSubtipoClienteList(LabelValue[] stoSubtipoClienteList) {
		this.stoSubtipoClienteList = stoSubtipoClienteList;
	}

	/**
	 * @return the codigoIdiomaISO
	 */
	public String getCodigoIdiomaISO() {
		return codigoIdiomaISO;
	}

	/**
	 * @param codigoIdiomaISO
	 *            the codigoIdiomaISO to set
	 */
	public void setCodigoIdiomaISO(String codigoIdiomaISO) {
		this.codigoIdiomaISO = codigoIdiomaISO;
	}

	/**
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona
	 *            the codigoZona to set
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	/**
	 * @return the indicadorGrabo
	 */
	public String getIndicadorGrabo() {
		return indicadorGrabo;
	}

	/**
	 * @param indicadorGrabo
	 *            the indicadorGrabo to set
	 */
	public void setIndicadorGrabo(String indicadorGrabo) {
		this.indicadorGrabo = indicadorGrabo;
	}

	/**
	 * @return the cantidadInsertados
	 */
	public double getCantidadInsertados() {
		return cantidadInsertados;
	}

	/**
	 * @param cantidadInsertados
	 *            the cantidadInsertados to set
	 */
	public void setCantidadInsertados(double cantidadInsertados) {
		this.cantidadInsertados = cantidadInsertados;
	}

	/**
	 * @return the longitudCampoClientes
	 */
	public int getLongitudCampoClientes() {
		return longitudCampoClientes;
	}

	/**
	 * @param longitudCampoClientes
	 *            the longitudCampoClientes to set
	 */
	public void setLongitudCampoClientes(int longitudCampoClientes) {
		this.longitudCampoClientes = longitudCampoClientes;
	}

	/**
	 * @return the motivoBloqueo
	 */
	public String getMotivoBloqueo() {
		return motivoBloqueo;
	}

	/**
	 * @param motivoBloqueo
	 *            the motivoBloqueo to set
	 */
	public void setMotivoBloqueo(String motivoBloqueo) {
		this.motivoBloqueo = motivoBloqueo;
	}

	/**
	 * @return the mantenimiento
	 */
	public boolean isMantenimiento() {
		return mantenimiento;
	}

	/**
	 * @param mantenimiento
	 *            the mantenimiento to set
	 */
	public void setMantenimiento(boolean mantenimiento) {
		this.mantenimiento = mantenimiento;
	}

	/**
	 * @return the stoTipoClasificacionList
	 */
	public LabelValue[] getStoTipoClasificacionList() {
		return stoTipoClasificacionList;
	}

	/**
	 * @param stoTipoClasificacionList
	 *            the stoTipoClasificacionList to set
	 */
	public void setStoTipoClasificacionList(
			LabelValue[] stoTipoClasificacionList) {
		this.stoTipoClasificacionList = stoTipoClasificacionList;
	}

	/**
	 * @return the stoTipoClienteList
	 */
	public List getStoTipoClienteList() {
		return stoTipoClienteList;
	}

	/**
	 * @param stoTipoClienteList
	 *            the stoTipoClienteList to set
	 */
	public void setStoTipoClienteList(List stoTipoClienteList) {
		this.stoTipoClienteList = stoTipoClienteList;
	}

	/**
	 * @return the stoClasificacionList
	 */
	public LabelValue[] getStoClasificacionList() {
		return stoClasificacionList;
	}

	/**
	 * @param stoClasificacionList
	 *            the stoClasificacionList to set
	 */
	public void setStoClasificacionList(LabelValue[] stoClasificacionList) {
		this.stoClasificacionList = stoClasificacionList;
	}

	/**
	 * @return the stoZonaList
	 */
	public LabelValue[] getStoZonaList() {
		return stoZonaList;
	}

	/**
	 * @param stoZonaList
	 *            the stoZonaList to set
	 */
	public void setStoZonaList(LabelValue[] stoZonaList) {
		this.stoZonaList = stoZonaList;
	}

	/**
	 * @return the oidSubTipoCliente
	 */
	public String getOidSubTipoCliente() {
		return oidSubTipoCliente;
	}

	/**
	 * @param oidSubTipoCliente
	 *            the oidSubTipoCliente to set
	 */
	public void setOidSubTipoCliente(String oidSubTipoCliente) {
		this.oidSubTipoCliente = oidSubTipoCliente;
	}

	/**
	 * @return the oidSubTipoClasificacion
	 */
	public String getOidSubTipoClasificacion() {
		return oidSubTipoClasificacion;
	}

	/**
	 * @param oidSubTipoClasificacion
	 *            the oidSubTipoClasificacion to set
	 */
	public void setOidSubTipoClasificacion(String oidSubTipoClasificacion) {
		this.oidSubTipoClasificacion = oidSubTipoClasificacion;
	}

	/**
	 * @return the oidClasificacion
	 */
	public String getOidClasificacion() {
		return oidClasificacion;
	}

	/**
	 * @param oidClasificacion
	 *            the oidClasificacion to set
	 */
	public void setOidClasificacion(String oidClasificacion) {
		this.oidClasificacion = oidClasificacion;
	}

	/**
	 * @return the stoBloqueoControlList
	 */
	public List getStoBloqueoControlList() {
		return stoBloqueoControlList;
	}

	/**
	 * @param stoBloqueoControlList
	 *            the stoBloqueoControlList to set
	 */
	public void setStoBloqueoControlList(List stoBloqueoControlList) {
		this.stoBloqueoControlList = stoBloqueoControlList;
	}

	/**
	 * @return the stoResumenClientesList
	 */
	public List getStoResumenClientesList() {
		return stoResumenClientesList;
	}

	/**
	 * @param stoResumenClientesList
	 *            the stoResumenClientesList to set
	 */
	public void setStoResumenClientesList(List stoResumenClientesList) {
		this.stoResumenClientesList = stoResumenClientesList;
	}

	/**
	 * @return the stoLevantamientoErroresClientesList
	 */
	public List getStoLevantamientoErroresClientesList() {
		return stoLevantamientoErroresClientesList;
	}

	/**
	 * @param stoLevantamientoErroresClientesList
	 *            the stoLevantamientoErroresClientesList to set
	 */
	public void setStoLevantamientoErroresClientesList(
			List stoLevantamientoErroresClientesList) {
		this.stoLevantamientoErroresClientesList = stoLevantamientoErroresClientesList;
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
	 * @return the stoMotivoBloqueo
	 */
	public String getStoMotivoBloqueo() {
		return stoMotivoBloqueo;
	}

	/**
	 * @param stoMotivoBloqueo
	 *            the stoMotivoBloqueo to set
	 */
	public void setStoMotivoBloqueo(String stoMotivoBloqueo) {
		this.stoMotivoBloqueo = stoMotivoBloqueo;
	}

	/**
	 * @return the stoRegionList
	 */
	public List getStoRegionList() {
		return stoRegionList;
	}

	/**
	 * @param stoRegionList
	 *            the stoRegionList to set
	 */
	public void setStoRegionList(List stoRegionList) {
		this.stoRegionList = stoRegionList;
	}

	public Object[] getObjetoSeleccionados() {
		return objetoSeleccionados;
	}

	public void setObjetoSeleccionados(Object[] objetoSeleccionados) {
		this.objetoSeleccionados = objetoSeleccionados;
	}

	public List getNuevostoLevantamientoErroresClientesList() {
		return nuevostoLevantamientoErroresClientesList;
	}

	public void setNuevostoLevantamientoErroresClientesList(
			List nuevostoLevantamientoErroresClientesList) {
		this.nuevostoLevantamientoErroresClientesList = nuevostoLevantamientoErroresClientesList;
	}

	public List getNuevostoResumenClientesList() {
		return nuevostoResumenClientesList;
	}

	public void setNuevostoResumenClientesList(List nuevostoResumenClientesList) {
		this.nuevostoResumenClientesList = nuevostoResumenClientesList;
	}

	public LabelValue[] getNuevostoSubtipoClienteList() {
		return nuevostoSubtipoClienteList;
	}

	public void setNuevostoSubtipoClienteList(
			LabelValue[] nuevostoSubtipoClienteList) {
		this.nuevostoSubtipoClienteList = nuevostoSubtipoClienteList;
	}

	public LabelValue[] getNuevostoTipoClasificacionList() {
		return nuevostoTipoClasificacionList;
	}

	public void setNuevostoTipoClasificacionList(
			LabelValue[] nuevostoTipoClasificacionList) {
		this.nuevostoTipoClasificacionList = nuevostoTipoClasificacionList;
	}

	public LabelValue[] getNuevostoClasificacionList() {
		return nuevostoClasificacionList;
	}

	public void setNuevostoClasificacionList(LabelValue[] nuevostoClasificacionList) {
		this.nuevostoClasificacionList = nuevostoClasificacionList;
	}

	public LabelValue[] getNuevostoZonaList() {
		return nuevostoZonaList;
	}

	public void setNuevostoZonaList(LabelValue[] nuevostoZonaList) {
		this.nuevostoZonaList = nuevostoZonaList;
	}

	public String getAttachmentNuevo() {
		return attachmentNuevo;
	}

	public void setAttachmentNuevo(String attachmentNuevo) {
		this.attachmentNuevo = attachmentNuevo;
	}

	public Boolean getMostrarGrillaNuevo() {
		return mostrarGrillaNuevo;
	}

	public void setMostrarGrillaNuevo(Boolean mostrarGrillaNuevo) {
		this.mostrarGrillaNuevo = mostrarGrillaNuevo;
	}

	public String getMsjeSave() {
		return msjeSave;
	}

	public void setMsjeSave(String msjeSave) {
		this.msjeSave = msjeSave;
	}

	/**
	 * @return the longitud
	 */
	public int getLongitud() {
		return longitud;
	}

	/**
	 * @param longitud the longitud to set
	 */
	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}

	/**
	 * @return the longitudCampoClientesSize
	 */
	public int getLongitudCampoClientesSize() {
		return longitudCampoClientesSize;
	}

	/**
	 * @param longitudCampoClientesSize the longitudCampoClientesSize to set
	 */
	public void setLongitudCampoClientesSize(int longitudCampoClientesSize) {
		this.longitudCampoClientesSize = longitudCampoClientesSize;
	}

	/**
	 * @return the longitudCampoClientesMax
	 */
	public int getLongitudCampoClientesMax() {
		return longitudCampoClientesMax;
	}

	/**
	 * @param longitudCampoClientesMax the longitudCampoClientesMax to set
	 */
	public void setLongitudCampoClientesMax(int longitudCampoClientesMax) {
		this.longitudCampoClientesMax = longitudCampoClientesMax;
	}		
}