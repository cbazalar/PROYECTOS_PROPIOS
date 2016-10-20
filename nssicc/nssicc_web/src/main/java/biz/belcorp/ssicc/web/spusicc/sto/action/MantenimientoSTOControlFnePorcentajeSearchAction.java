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

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelDatosConsultoraValue;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECIngresoAtencionesService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOBloqueoControlService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOControlFnePorcentajeForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOControlDevolucionesSearchForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOControlFnePorcentajeForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOControlFnePorcentajeSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes","unchecked"})
public class MantenimientoSTOControlFnePorcentajeSearchAction extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -7766588187041148487L;
	
	private List stoTipoClienteList = new ArrayList();
	private LabelValue[] stoSubtipoClienteList = {};
	private LabelValue[] stoTipoClasificacionList = {};
	private LabelValue[] stoClasificacionList = {};
	private List stoRegionList = new ArrayList();
	private LabelValue[] stoZonaList = {};
	private List stoBloqueoControlList = new ArrayList();
	private List stoLevantamientoErroresClientesList = new ArrayList();
	private List stoTipoDocumentoSpvd = new ArrayList();
	private List stoControlFNEPorcentajeList = new ArrayList();
	private String attachment = "";
	private String attachmentForm = "";
	private Object[] columnasSeleccionadas;
	
	private List seleccionado = new ArrayList();
	private boolean isArchivo = false;
	private int cantInsertado = 0;
	
	
	private List stoTipoClienteFormList = new ArrayList();
	private LabelValue[] stoSubtipoClienteFormList = {};
	private LabelValue[] stoTipoClasificacionFormList = {};
	private LabelValue[] stoClasificacionFormList = {};
	private List stoRegionFormList = new ArrayList();
	private LabelValue[] stoZonaFormList = {};
	private int longitudCampoClientesSize = 0;
	private int longitudCampoClientesMax = 0;
	private int longitud = 0;
	
	private boolean mostrarFilas = true;

	/**
	 * @param event
	 * @throws Exception
	 */
	public void handleFileUploadSearch(FileUploadEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("handleFileUpload");
		}
		MantenimientoSTOControlFnePorcentajeSearchForm f = (MantenimientoSTOControlFnePorcentajeSearchForm) this.formBusqueda;
		if (event != null) {
			f.setClienteFile(event.getFile());
			this.setAttachment(event.getFile().getFileName());
			this.uploadArchivoSearch();
		}
	}
	
	/**
	 * @throws Exception
	 */
	public void uploadArchivoSearch() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'load Clientes from file' method");
		}
		MantenimientoSTOControlFnePorcentajeSearchForm f = (MantenimientoSTOControlFnePorcentajeSearchForm) this.formBusqueda;
		List listaClientes = new ArrayList();
		UploadedFile archivo = f.getClienteFile();
		InputStream is = archivo.getInputstream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String linea = "";
		while (true) {
			linea = br.readLine();
			if (linea == null)
				break;
			LabelValue bean = new LabelValue(linea.trim(), linea.trim());
			listaClientes.add(bean);
		}
		this.stoLevantamientoErroresClientesList.clear();
		this.stoLevantamientoErroresClientesList = listaClientes;
	}

	/**
	 * @param event
	 * @throws Exception
	 */
	public void handleFileUploadForm(FileUploadEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("handleFileUpload");
		}
		MantenimientoSTOControlFnePorcentajeForm f = (MantenimientoSTOControlFnePorcentajeForm) this.formMantenimiento;
		if (event != null) {
			f.setClienteFile(event.getFile());
			this.setAttachmentForm(event.getFile().getFileName());
			this.uploadArchivoForm();
		}
	}

	/**
	 * @throws Exception
	 */
	public void uploadArchivoForm() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'load Clientes from file' method");
		}

		MantenimientoSTOControlFnePorcentajeForm f = (MantenimientoSTOControlFnePorcentajeForm) this.formMantenimiento;
		MantenimientoRECIngresoAtencionesService service = (MantenimientoRECIngresoAtencionesService) getBean("spusicc.mantenimientoRECIngresoAtencionesService");
		List listaClientes = new ArrayList();

		UploadedFile archivo = f.getClienteFile();
		Map criteria = new HashMap();
		InputStream is = archivo.getInputstream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String linea = "";
		int cont = 0;
		while (true) {
			linea = br.readLine();
			if (linea == null)
				break;

			criteria.put("codigoConsultora", linea.trim());
			LabelValue bean = new LabelValue(linea.trim(),
					service.getCodigoConsultora(criteria));
			listaClientes.add(bean);
			if (bean.getValue() == null)
				cont++;
		}
		f.setCodigosErradosFile("");
		if (cont != 0)
			f.setCodigosErradosFile("Existe(n) " + cont
					+ " codigo(s) errado(s)");

		this.stoLevantamientoErroresClientesList.clear();
		this.stoLevantamientoErroresClientesList = listaClientes;

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setValidarMantenimiento()
	 */
	@Override
	public String setValidarMantenimiento() {
		MantenimientoSTOControlFnePorcentajeForm f = (MantenimientoSTOControlFnePorcentajeForm) this.formMantenimiento;
		String mensaje = null;
		boolean flag = false;
		
		if(StringUtils.equals(f.getTipoBloqueo(), Constants.NUMERO_UNO)){
			if(StringUtils.isBlank(f.getCodigoPeriodo())){
				mensaje = this.getResourceMessage("mantenimientoSTOControlFnePorcentajeForm.periodo.requerido");
				return mensaje;
			}
		}
		
		if(this.stoLevantamientoErroresClientesList != null && this.stoLevantamientoErroresClientesList.size()>0){
			flag = true;
		}

		if (StringUtils.isBlank(f.getCodigoCliente()) && StringUtils.isBlank(f.getCodigoRegion()) &&StringUtils.isBlank(f.getTipoCliente()) && flag == false) {
			mensaje = this.getResourceMessage("mantenimientoSTOControlFnePorcentajeForm.tipocliente.region.codigocliente.requerido");
			return mensaje;
		}
		
		if(StringUtils.isNotBlank(f.getCodigosErradosFile())){
			mensaje =this.getResourceMessage("MantenimientoSTOControlFnePorcentajeForm.codigocliente.corregir");
			return mensaje;
			
		}
		
		if(StringUtils.isNotBlank(f.getCodigosErrados())){
			mensaje =this.getResourceMessage("mantenimientoSTOControlFnePorcentajeForm.codigocliente.corregir");
			return mensaje;
			
		}	

		return mensaje;
	}	
	
	/**
	 * Limpia la data cargada del archivo
	 * 
	 * @param event
	 */
	public void limpiarFile(ActionEvent event){
		
		try {
			
			if (log.isDebugEnabled()) {
				log.debug("Entering 'limpiar' method");
			}				
			
			MantenimientoSTOControlFnePorcentajeForm f = (MantenimientoSTOControlFnePorcentajeForm)this.formMantenimiento;
			f.setCodigoCliente("");
			f.setCodigosErrados("");
			f.setCodigosErradosFile("");
			this.attachmentForm =""; 
			this.stoLevantamientoErroresClientesList= new ArrayList(); 
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		
	}
	
	/**
	 * Limpia la data cargada del archivo de busqueda
	 * 
	 * @param event
	 */
	public void limpiarFileSearch(ActionEvent event){
		
		try {
			
			if (log.isDebugEnabled()) {
				log.debug("Entering 'limpiar' method");
			}				
			
			MantenimientoSTOControlFnePorcentajeSearchForm f = (MantenimientoSTOControlFnePorcentajeSearchForm)this.formBusqueda;
			f.setCodigoCliente("");
			this.attachment =""; 
			this.stoLevantamientoErroresClientesList= new ArrayList(); 
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setSalirFindBeforeAttributes()
	 */
	@Override
	public void setSalirFindBeforeAttributes() throws Exception {
		MantenimientoSTOControlFnePorcentajeForm f = (MantenimientoSTOControlFnePorcentajeForm)this.formMantenimiento;
		f.setCodigoCliente("");
		f.setCodigosErrados("");
		f.setCodigosErradosFile("");
		this.attachmentForm =""; 
		this.attachment = "";
		this.stoLevantamientoErroresClientesList= new ArrayList(); 
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoSTOControlFnePorcentajeList";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoSTOControlFnePorcentajeForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoSTOControlFnePorcentajeSearchForm searchForm = new MantenimientoSTOControlFnePorcentajeSearchForm();
		return searchForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {

		log.debug("MantenimientoSTOControlFnePorcentajeSearchAction - setFindAttributes");

		MantenimientoSTOControlFnePorcentajeSearchForm f = (MantenimientoSTOControlFnePorcentajeSearchForm) this.formBusqueda;

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		// MantenimientoSTOControlDevolucionesService service =
		// (MantenimientoSTOControlDevolucionesService)getBean("spusicc.mantenimientoSTOControlDevolucionesService");
		MantenimientoSTOBloqueoControlService service = (MantenimientoSTOBloqueoControlService) getBean("spusicc.mantenimientoSTOBloqueoControlService");

		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();

		Map criteria = new HashMap();

		criteria.put("codigoPais", mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		criteria.put("codigoCliente", f.getCodigoCliente());
		criteria.put("codigoIso", usuario.getIdioma().getCodigoISO());
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);

		if (StringUtils.isNotBlank(f.getCodigoPeriodo()))
			criteria.put("oidPeriodo", reporteService.getOidString("getOidPeriodoByCodigoPeriodo", criteria));
		else
			criteria.put("oidPeriodo", null);

		if (StringUtils.isNotBlank(f.getTipoCliente()))
			criteria.put("oidTipoCliente", f.getTipoCliente());
		else
			criteria.put("oidTipoCliente", null);

		if (StringUtils.isNotBlank(f.getSubTipoCliente())) {
			criteria.put("oidSubTipoCliente", f.getSubTipoCliente());
		} else {
			criteria.put("oidSubTipoCliente", null);
		}

		if (StringUtils.isNotBlank(f.getTipoClasificacion())) {
			criteria.put("oidSubTipoClasificacion", f.getTipoClasificacion());
		} else {
			criteria.put("oidSubTipoClasificacion", null);
		}

		if (StringUtils.isNotBlank(f.getClasificacion())) {
			criteria.put("oidClasificacion", f.getClasificacion());
		} else {
			criteria.put("oidClasificacion", null);
		}

		if (StringUtils.isNotBlank(f.getCodigoRegion()))
			criteria.put("codigoRegion", f.getCodigoRegion());
		else
			criteria.put("codigoRegion", null);

		if (StringUtils.isNotBlank(f.getCodigoZona())) {
			criteria.put("codigoZona", f.getCodigoZona());
		} else {
			criteria.put("codigoZona", null);
		}

		/*-------------------------*/
		if (StringUtils.isNotBlank(f.getTipoBloqueo())) {
			criteria.put("tipoBloqueo", f.getTipoBloqueo());
		} else {
			criteria.put("tipoBloqueo", null);
		}
		/*-------------------------*/
		String[] arrlistaClientes = new String[0];
		List clienteList = new ArrayList(); // result
		Long longitudPais = mPantallaPrincipalBean.getCurrentCountry().getLongitudCodigoCliente();
		String codigoCliente = f.getCodigoCliente();
		if(StringUtils.isBlank(f.getCodigoCliente())){
			codigoCliente = null;
			f.setCodigoCliente(null);
		}
		List listaClientes = this.stoLevantamientoErroresClientesList; // grilla del
																	// archivo

		Map param = new HashMap();

		// SI cargo consultoras por el archivo
		if (listaClientes != null) {
			for (int i = 0; i < listaClientes.size(); i++) {
				LabelValue bean = (LabelValue) listaClientes.get(i);
				param.put("codigoCliente", StringUtils.leftPad(bean.getLabel(), longitudPais.intValue(), '0'));
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
					this.addError("Error  : ", obtieneMensajeErrorException(e));
				}
			}
		}
		if (!clienteList.isEmpty())
			criteria.put("clienteList", clienteList);

		/*-------------------------*/

		List list = new ArrayList();
		criteria.put("codigoTipoDocumento", f.getCodigoTipoDocumento());
		criteria.put("motivoBloqueo", Constants.MOTIVO_BLOQUEO_FNE);
		list = service.getBloqueoControlList(criteria);

		return list;
	
	}

	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setValidarFind()
	 */
	@Override
	public String setValidarFind() {

		MantenimientoSTOControlFnePorcentajeSearchForm f = (MantenimientoSTOControlFnePorcentajeSearchForm) this.formBusqueda;
		String mensaje = null;
		
		if(StringUtils.isNotBlank(f.getCodigoCliente()))
		{
			mensaje = this.validadorBotonFind();
		}
			
		
		return mensaje;
		
	}
	
	/**
	 * Da formato al codigo cliente y verfica si es valido
	 * 
	 * 
	 */
	public String validadorBotonFind(){
		log.debug("Enter method - validador");
		String mensaje = null;
		try {
			
			MantenimientoSTOControlFnePorcentajeSearchForm f = (MantenimientoSTOControlFnePorcentajeSearchForm) this.formBusqueda;
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			AjaxService ajax = (AjaxService)getBean("ajaxService");
					 
			if(StringUtils.isNotBlank(f.getCodigoCliente())){
				LabelDatosConsultoraValue[] data = ajax.getCabeceraConsultoraSimple( pais.getCodigo(), f.getCodigoCliente());
				
				if(data == null)
					mensaje = this.getResourceMessage("mantenimientoSTOControlFnePorcentajeSearchForm.cliente.no.existe");
				
			}			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}		
		return mensaje;		
	}
		
	/**
	 * Da formato al codigo cliente y verfica si es valido
	 * 
	 * 
	 */
	public void validador(){
		log.debug("Enter method - validador");
		try {			
			
			MantenimientoSTOControlFnePorcentajeSearchForm f = (MantenimientoSTOControlFnePorcentajeSearchForm) this.formBusqueda;
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			AjaxService ajax = (AjaxService)getBean("ajaxService");
			
			if(StringUtils.isNotBlank(f.getCodigoCliente())){
				int tam = f.getCodigoCliente().length();
				if(tam != 9){
					String cad = rellenar(f.getCodigoCliente(), "0", 9);				
					f.setCodigoCliente(cad);
				}
							 		
				LabelDatosConsultoraValue[] data = ajax.getCabeceraConsultoraSimple( pais.getCodigo(), f.getCodigoCliente());
				
				if(data == null)
					this.addError("Error: ", this.getResourceMessage("mantenimientoSTOControlFnePorcentajeSearchForm.cliente.no.existe"));	
				
			}			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {

		log.debug("MantenimientoSTOControlFnePorcentajeSearchAction - setDeleteAttributes");
		
		MantenimientoSTOControlFnePorcentajeSearchForm f = (MantenimientoSTOControlFnePorcentajeSearchForm) this.formBusqueda;
		
		MantenimientoSTOBloqueoControlService service = (MantenimientoSTOBloqueoControlService)getBean("spusicc.mantenimientoSTOBloqueoControlService");
		
		String[] items = new String[this.seleccionado.size()] ;
		
		for (int i = 0; i < this.seleccionado.size(); i++) {
			Map seleccionados = (Map)this.seleccionado.get(i);			
			items[i] = (String) seleccionados.get("codigoBloqueo").toString();
		}
		
		Map parametros = new HashMap();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		parametros.put("usuario", usuario.getLogin());
		parametros.put("idSeleccionados",items);
		
		service.deleteBloqueoControl(parametros);
		
		return true;	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#verificarRegistroSeleccionado()
	 */
	@Override
	protected boolean verificarRegistroSeleccionado() {
		
		boolean flag = true;
		if(!StringUtils.equals(this.accion, "ELIMINAR"))
			flag = false;
		
		return flag;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		log.debug("MantenimientoSTOControlFnePorcentajeSearchAction - setAddAttributes");

		MantenimientoSTOControlFnePorcentajeForm f = (MantenimientoSTOControlFnePorcentajeForm) this.formMantenimiento;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoSTOBloqueoControlService service = (MantenimientoSTOBloqueoControlService)getBean("spusicc.mantenimientoSTOBloqueoControlService");
		this.isArchivo = false;
		
		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
		Map criteria = new HashMap();

		criteria.put("codigoPais", mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo());
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		criteria.put("codigoCliente", f.getCodigoCliente());
		criteria.put("codigoIso", usuario.getIdioma().getCodigoISO());
		criteria.put("usuario", usuario.getLogin());

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
			// request.setAttribute("oidSubTipoCliente",
			// criteria.get("oidSubTipoCliente"));
		} else {
			criteria.put("oidSubTipoCliente", null);
			// request.setAttribute("oidSubTipoCliente", null);
		}

		if (StringUtils.isNotBlank(f.getTipoClasificacion())) {
			criteria.put("oidSubTipoClasificacion", f.getTipoClasificacion());
			// request.setAttribute("oidSubTipoClasificacion",
			// criteria.get("oidSubTipoClasificacion"));
		} else {
			criteria.put("oidSubTipoClasificacion", null);
			// request.setAttribute("oidSubTipoClasificacion",null);
		}

		if (StringUtils.isNotBlank(f.getClasificacion())) {
			criteria.put("oidClasificacion", f.getClasificacion());
			// request.setAttribute("oidClasificacion",
			// criteria.get("oidClasificacion"));
		} else {
			criteria.put("oidClasificacion", null);
			// request.setAttribute("oidClasificacion", null);
		}

		if (StringUtils.isNotBlank(f.getCodigoRegion()))
			criteria.put("codigoRegion", f.getCodigoRegion());
		else
			criteria.put("codigoRegion", Constants.STO_VALOR_OID_NULL);

		if (StringUtils.isNotBlank(f.getCodigoZona())) {
			criteria.put("codigoZona", f.getCodigoZona());
			// request.setAttribute("codigoZona", criteria.get("codigoZona"));
		} else {
			criteria.put("codigoZona", null);
			// request.setAttribute("codigoZona", null);
		}

		criteria.put("fechaCreacion", Calendar.getInstance().getTime());
		criteria.put("observaciones", f.getObservaciones());

		criteria.put("tipoBloqueo", f.getTipoBloqueo());

		/*-------------------------*/
		String[] arrlistaClientes = new String[0];
		List clienteList = new ArrayList(); // result
		Long longitudPais = this.mPantallaPrincipalBean.getCurrentCountry().getLongitudCodigoCliente();
		String codigoCliente = f.getCodigoCliente();
		List listaClientes = this.stoLevantamientoErroresClientesList; // grilla del
																	// archivo

		Map param = new HashMap();

		// SI cargo consultoras por el archivo
		if (listaClientes != null && listaClientes.size() > 0) {
			this.isArchivo = true;
			for (int i = 0; i < listaClientes.size(); i++) {
				LabelValue bean = (LabelValue) listaClientes.get(i);
				param.put("codigoCliente", StringUtils.leftPad(bean.getLabel(), longitudPais.intValue(), '0'));
				try {
					clienteList.add(reporteService.getOidString("getOidClienteByCodigoCliente", param));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		} else {
			// Si es cargado por la caja de texto
			if (codigoCliente.length() > 0)
				arrlistaClientes = codigoCliente.split(",+");
			for (int i = 0; i < arrlistaClientes.length; i++) {
				param.put("codigoCliente", StringUtils.leftPad(arrlistaClientes[i], longitudPais.intValue(), '0'));
				try {
					clienteList.add(reporteService.getOidString("getOidClienteByCodigoCliente", param));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		criteria.put("clienteList", (clienteList == null) ? new ArrayList(): clienteList);
		/*-------------------------*/

		criteria.put("codigoTipoDocumento", f.getCodigoTipoDocumento());
		criteria.put("porcentaje", f.getPorcentaje());

		// insertando motivo de bloqueo F
		criteria.put("motivoBloqueo", Constants.MOTIVO_BLOQUEO_FNE);

		int cantidadInsertados = service.insertSTOBloqueoControl(criteria);
		
		if(this.isArchivo)
			this.cantInsertado = cantidadInsertados;
		
		//Se inicializa la variable para la busqueda
		this.stoLevantamientoErroresClientesList = new ArrayList();
		this.attachmentForm ="";
		
		
		return true;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#devuelveMensajeAlertaDefaultAction()
	 */
	@Override
	public String devuelveMensajeAlertaDefaultAction() throws Exception {
		
		if (this.isArchivo) 			
			return getResourceMessage("mensaje.mantenimientoSTOControlFnePorcentajeForm.satisfactorio.archivo", new Object[]{this.cantInsertado});					   
		else 
			return getResourceMessage("mensaje.mantenimientoSTOControlFnePorcentajeForm.satisfactorio");
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		log.debug("MantenimientoSTOControlFnePorcentajeSearchAction - setAddAttributes");

		this.mostrarFilas = true;
		MantenimientoSTOControlFnePorcentajeForm f = new MantenimientoSTOControlFnePorcentajeForm();
		ClienteUAGenerarService clienteService = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente		
        f.setTipoBloqueo(Constants.NUMERO_CERO);
        
        //Carga el combo Tipo cliente
        InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
        this.stoTipoClienteFormList = interfazSiCCService.getTiposClientesByCodigoISOOID(usuario.getIdioma().getCodigoISO());
        this.stoSubtipoClienteFormList = new LabelValue[0];
        this.stoTipoClasificacionFormList = new LabelValue[0];
        this.stoClasificacionFormList = new LabelValue[0]; 
        
        ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
        this.stoRegionFormList = reporteService.getListaGenerico("getRegionesByPais",criteria);
        this.stoZonaFormList = new LabelValue[0];
//      session.setAttribute("codigoIdiomaISO", usuario.getIdioma().getCodigoISO());        
        this.longitud = clienteService.getTamanhoNumeroCliente(pais.getCodigo());
        this.longitudCampoClientesSize = 3 * longitud;
        this.longitudCampoClientesMax = 30 * longitud;
        this.stoLevantamientoErroresClientesList = new ArrayList();
        f.setCodigosErradosFile("");
        f.setCodigosErrados("");
		f.setCodigoTipoDocumento(Constants.STO_TIPO_DOCUMENTO_SPVD);

		return f;

	}

	/**
	 * @param f
	 */
	private void cleanForm(MantenimientoSTOControlFnePorcentajeSearchForm f) {
		f.setCodigoCliente("");
		f.setCodigoPeriodo("");
		f.setTipoCliente("");// this.tipoCliente = null;
		f.setSubTipoCliente(""); // this.subTipoCliente = null;
		f.setTipoClasificacion(""); // this.tipoClasificacion = null;
		f.setClasificacion(""); // this.clasificacion = null;
		f.setCodigoRegion(""); // this.codigoRegion = null;
		f.setCodigoZona(""); // this.codigoZona = null;
		f.setTipoBloqueo("");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("MantenimientoSTOControlFnePorcentajeSearchAction - setViewAttributes");
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar=false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonBuscar = false;
//		this.nroPaginacionDatatable = 25; 
		this.mostrarListaBusqueda = false;
		this.salirGrabarPantallaPadre = true;
		
		MantenimientoSTOControlFnePorcentajeSearchForm f = (MantenimientoSTOControlFnePorcentajeSearchForm) this.formBusqueda;
		cleanForm(f);
//		this.mostrarPaginacionDatatableSpinner = false;
//		this.nroPaginacionDatatable = 25;
		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
		Map criteria = new HashMap();
		criteria.put("codigoPais", mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo());
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente

		// Carga el combo Tipo cliente
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		this.stoTipoClienteList = interfazSiCCService.getTiposClientesByCodigoISOOID(usuario.getIdioma().getCodigoISO());
		this.stoSubtipoClienteList = new LabelValue[0];
		this.stoTipoClasificacionList =  new LabelValue[0];
		this.stoClasificacionList = new LabelValue[0];
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		this.stoRegionList = reporteService.getListaGenerico("getRegionesByPais",criteria);
		this.stoZonaList = new LabelValue[0];
//		session.setAttribute("codigoIdiomaISO", usuario.getIdioma().getCodigoISO());
		this.stoBloqueoControlList.clear();
		this.stoLevantamientoErroresClientesList.clear();
		f.setCodigoTipoDocumento(Constants.STO_TIPO_DOCUMENTO_SPVD);
	}


	/**
	 * Cargar sub tipo clientes
	 * @param val
	 */
	public void buscarSubTipoClientes(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		try {
			if (StringUtils.isNotEmpty(val.getNewValue().toString())
					|| StringUtils.isNotBlank(val.getNewValue().toString())) {
				ArrayList x = new ArrayList();

				String regionListado = (String) val.getNewValue();
				x.add(regionListado);

				AjaxService ajax = (AjaxService) getBean("ajaxService");
				this.stoSubtipoClienteList = ajax
						.getSubTiposClientesPorPaisTipoClientesOID(this
								.getmPantallaPrincipalBean().getCurrentUser()
								.getIdioma().getCodigoISO(), x);
				this.stoTipoClasificacionList = null;
				this.stoClasificacionList = null;
			} else {
				this.stoSubtipoClienteList = null;
			}
		} catch (Exception e) {
			this.stoSubtipoClienteList = null;
			this.stoTipoClasificacionList = null;
			this.stoClasificacionList = null;
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}		
	}

	/**
	 * Cargar tipo de clasificacion
	 * @param val
	 */
	public void buscarTipoClasificacion(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		try {			
			if (StringUtils.isNotEmpty(val.getNewValue().toString())
					|| StringUtils.isNotBlank(val.getNewValue().toString())) {
				ArrayList x = new ArrayList();
				MantenimientoSTOControlFnePorcentajeSearchForm f = (MantenimientoSTOControlFnePorcentajeSearchForm) this.formBusqueda;

				String regionListado = (String) val.getNewValue();
				x.add(regionListado);

				AjaxService ajax = (AjaxService) getBean("ajaxService");
				this.stoTipoClasificacionList = ajax
						.getTiposClasificacionesByCriteriaMultipleOID(this
								.getmPantallaPrincipalBean().getCurrentUser()
								.getIdioma().getCodigoISO(), f.getTipoCliente(), x);
				this.stoClasificacionList = null;
			} else {
				this.stoTipoClasificacionList = null;
				this.stoClasificacionList = null;
			}
		} catch (Exception e) {
			this.stoTipoClasificacionList = null;
			this.stoClasificacionList = null;
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}		
	}

	/**
	 * Cargar clasificacion
	 * @param val
	 */
	public void buscarClasificacion(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		try {
			if (StringUtils.isNotEmpty(val.getNewValue().toString())
					|| StringUtils.isNotBlank(val.getNewValue().toString())) {
				ArrayList x = new ArrayList();
				MantenimientoSTOControlFnePorcentajeSearchForm f = (MantenimientoSTOControlFnePorcentajeSearchForm) this.formBusqueda;
				String regionListado = (String) val.getNewValue();
				x.add(regionListado);
				ArrayList subTiposCliente = new ArrayList();
				subTiposCliente.add(f.getSubTipoCliente());

				AjaxService ajax = (AjaxService) getBean("ajaxService");
				this.stoClasificacionList = ajax
						.getClasificacionesByCriteriaMultipleOID(this
								.getmPantallaPrincipalBean().getCurrentUser()
								.getIdioma().getCodigoISO(), f.getTipoCliente(),
								subTiposCliente, x);
			} else {
				this.stoClasificacionList = null;
			}
		} catch (Exception e) {
			this.stoClasificacionList = null;
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}		
	}

	/**
	 * Cargar Zonas
	 * @param val
	 */
	public void buscarZonasRegion(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		try {
			if (StringUtils.isNotEmpty(val.getNewValue().toString())
					|| StringUtils.isNotBlank(val.getNewValue().toString())) {
				String regionListado = (String) val.getNewValue();
				String regiones[] = new String[1];
				regiones[0] = regionListado;
				AjaxService ajax = (AjaxService) getBean("ajaxService");
				this.stoZonaList = ajax.getZonasMultipleByPaisMarcaCanalRegion(this
						.getmPantallaPrincipalBean().getCurrentCountry()
						.getCodigo(), "T", "VD", regiones, "");

			} else {
				this.stoZonaList = null;
			}
		} catch (Exception e) {
			this.stoZonaList = null;
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}		
	}

	/* BaseEdit */
	/**
	 * @param val
	 */
	public void buscarSubTipoClientesForm(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		try {
			if (StringUtils.isNotEmpty(val.getNewValue().toString())
					|| StringUtils.isNotBlank(val.getNewValue().toString())) {
				ArrayList x = new ArrayList();
				String regionListado = (String) val.getNewValue();
				x.add(regionListado);

				AjaxService ajax = (AjaxService) getBean("ajaxService");
				this.stoSubtipoClienteFormList = ajax.getSubTiposClientesPorPaisTipoClientesOID(this.getmPantallaPrincipalBean().getCurrentUser().getIdioma().getCodigoISO(), x);
				this.stoTipoClasificacionFormList = null;
				this.stoClasificacionFormList = null;
			} else {
				this.stoSubtipoClienteFormList = null;
				this.stoTipoClasificacionFormList = null;
				this.stoClasificacionFormList = null;
			}
		} catch (Exception e) {
			this.stoSubtipoClienteFormList = null;
			this.stoTipoClasificacionFormList = null;
			this.stoClasificacionFormList = null;
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}		
	}

	/**
	 * @param val
	 */
	public void buscarTipoClasificacionForm(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		try {
			if (StringUtils.isNotEmpty(val.getNewValue().toString())
					|| StringUtils.isNotBlank(val.getNewValue().toString())) {
				ArrayList x = new ArrayList();
				MantenimientoSTOControlFnePorcentajeForm f = (MantenimientoSTOControlFnePorcentajeForm) this.formMantenimiento;
				String regionListado = (String) val.getNewValue();
				x.add(regionListado);

				AjaxService ajax = (AjaxService) getBean("ajaxService");
				this.stoTipoClasificacionFormList = ajax
						.getTiposClasificacionesByCriteriaMultipleOID(this
								.getmPantallaPrincipalBean().getCurrentUser()
								.getIdioma().getCodigoISO(), f.getTipoCliente(), x);
				this.stoClasificacionFormList = null;
			} else {
				this.stoClasificacionFormList = null;
			}
		} catch (Exception e) {
			this.stoTipoClasificacionFormList = null;
			this.stoClasificacionFormList = null;
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}		
	}

	/**
	 * @param val
	 */
	public void buscarClasificacionForm(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		try {
			if (StringUtils.isNotEmpty(val.getNewValue().toString())
					|| StringUtils.isNotBlank(val.getNewValue().toString())) {
				ArrayList x = new ArrayList();
				MantenimientoSTOControlFnePorcentajeForm f = (MantenimientoSTOControlFnePorcentajeForm) this.formMantenimiento;
				String regionListado = (String) val.getNewValue();
				x.add(regionListado);
				ArrayList subTiposCliente = new ArrayList();
				subTiposCliente.add(f.getSubTipoCliente());

				AjaxService ajax = (AjaxService) getBean("ajaxService");
				this.stoClasificacionFormList = ajax.getClasificacionesByCriteriaMultipleOID(this.getmPantallaPrincipalBean().getCurrentUser().getIdioma().getCodigoISO(), f.getTipoCliente(),subTiposCliente, x);

			} else {
				this.stoClasificacionFormList = null;
			}
		} catch (Exception e) {
			this.stoClasificacionFormList = null;
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}		
	}

	/**
	 * @param val
	 */
	public void buscarZonasRegionForm(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		try {
			log.debug(val.getNewValue().toString());
			if (StringUtils.isNotEmpty(val.getNewValue().toString())
					|| StringUtils.isNotBlank(val.getNewValue().toString())) {
				String regionListado = (String) val.getNewValue();
				String regiones[] = new String[1];
				regiones[0] = regionListado;
				AjaxService ajax = (AjaxService) getBean("ajaxService");
				this.stoZonaFormList = ajax.getZonasMultipleByPaisMarcaCanalRegion(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), "T", "VD", regiones, "");
			} else {
				this.stoZonaFormList = null;
			}
		} catch (Exception e) {
			this.stoZonaFormList = null;
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {
		String mensaje = null;
		
		if(StringUtils.equals(accion, "ELIMINAR")){
			if(this.seleccionado == null || this.seleccionado.size() == 0 ){				
				mensaje = this.getResourceMessage("errors.select.item");
				return mensaje;
			}
			
		}				
		return mensaje;
	}
	
	
	public void formatClientes(){
		
		MantenimientoSTOControlFnePorcentajeForm f = (MantenimientoSTOControlFnePorcentajeForm) this.formMantenimiento;
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
	
	/**
	 * Oculta Archivo cliente,
	 * periodo,
	 * codigo cliente,
	 * y fila de errores
	 * 
	 * @param event
	 */
	public void ocultarMostrar(ValueChangeEvent event){
		String tipoBloqueo = (String) event.getNewValue();
				
		if(StringUtils.equals(tipoBloqueo, Constants.NUMERO_CERO)){
			this.mostrarFilas = false;
		}else{
			this.mostrarFilas = true;
		}
		
		
	}

	/**
	 * @return the seleccionado
	 */
	public List getSeleccionado() {
		return seleccionado;
	}

	/**
	 * @param seleccionado the seleccionado to set
	 */
	public void setSeleccionado(List seleccionado) {
		this.seleccionado = seleccionado;
	}

	/**
	 * @return the isArchivo
	 */
	public boolean isArchivo() {
		return isArchivo;
	}

	/**
	 * @param isArchivo the isArchivo to set
	 */
	public void setArchivo(boolean isArchivo) {
		this.isArchivo = isArchivo;
	}

	/**
	 * @return the cantInsertado
	 */
	public int getCantInsertado() {
		return cantInsertado;
	}

	/**
	 * @param cantInsertado the cantInsertado to set
	 */
	public void setCantInsertado(int cantInsertado) {
		this.cantInsertado = cantInsertado;
	}

	/**
	 * @return the stoTipoClienteFormList
	 */
	public List getStoTipoClienteFormList() {
		return stoTipoClienteFormList;
	}

	/**
	 * @param stoTipoClienteFormList the stoTipoClienteFormList to set
	 */
	public void setStoTipoClienteFormList(List stoTipoClienteFormList) {
		this.stoTipoClienteFormList = stoTipoClienteFormList;
	}

	/**
	 * @return the stoSubtipoClienteFormList
	 */
	public LabelValue[] getStoSubtipoClienteFormList() {
		return stoSubtipoClienteFormList;
	}

	/**
	 * @param stoSubtipoClienteFormList the stoSubtipoClienteFormList to set
	 */
	public void setStoSubtipoClienteFormList(LabelValue[] stoSubtipoClienteFormList) {
		this.stoSubtipoClienteFormList = stoSubtipoClienteFormList;
	}

	/**
	 * @return the stoTipoClasificacionFormList
	 */
	public LabelValue[] getStoTipoClasificacionFormList() {
		return stoTipoClasificacionFormList;
	}

	/**
	 * @param stoTipoClasificacionFormList the stoTipoClasificacionFormList to set
	 */
	public void setStoTipoClasificacionFormList(
			LabelValue[] stoTipoClasificacionFormList) {
		this.stoTipoClasificacionFormList = stoTipoClasificacionFormList;
	}

	/**
	 * @return the stoClasificacionFormList
	 */
	public LabelValue[] getStoClasificacionFormList() {
		return stoClasificacionFormList;
	}

	/**
	 * @param stoClasificacionFormList the stoClasificacionFormList to set
	 */
	public void setStoClasificacionFormList(LabelValue[] stoClasificacionFormList) {
		this.stoClasificacionFormList = stoClasificacionFormList;
	}

	/**
	 * @return the stoRegionFormList
	 */
	public List getStoRegionFormList() {
		return stoRegionFormList;
	}

	/**
	 * @param stoRegionFormList the stoRegionFormList to set
	 */
	public void setStoRegionFormList(List stoRegionFormList) {
		this.stoRegionFormList = stoRegionFormList;
	}

	/**
	 * @return the stoZonaFormList
	 */
	public LabelValue[] getStoZonaFormList() {
		return stoZonaFormList;
	}

	/**
	 * @param stoZonaFormList the stoZonaFormList to set
	 */
	public void setStoZonaFormList(LabelValue[] stoZonaFormList) {
		this.stoZonaFormList = stoZonaFormList;
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

	/**
	 * @return the mostrarFilas
	 */
	public boolean isMostrarFilas() {
		return mostrarFilas;
	}

	/**
	 * @param mostrarFilas the mostrarFilas to set
	 */
	public void setMostrarFilas(boolean mostrarFilas) {
		this.mostrarFilas = mostrarFilas;
	}
	
	/**
	 * @return
	 */
	public Object[] getColumnasSeleccionadas() {
		return columnasSeleccionadas;
	}

	/**
	 * @param columnasSeleccionadas
	 */
	public void setColumnasSeleccionadas(Object[] columnasSeleccionadas) {
		this.columnasSeleccionadas = columnasSeleccionadas;
	}

	/**
	 * @return
	 */
	public String getAttachmentForm() {
		return attachmentForm;
	}

	/**
	 * @param attachmentForm
	 */
	public void setAttachmentForm(String attachmentForm) {
		this.attachmentForm = attachmentForm;
	}

	/**
	 * @return
	 */
	public List getStoControlFNEPorcentajeList() {
		return stoControlFNEPorcentajeList;
	}

	/**
	 * @param stoControlFNEPorcentajeList
	 */
	public void setStoControlFNEPorcentajeList(List stoControlFNEPorcentajeList) {
		this.stoControlFNEPorcentajeList = stoControlFNEPorcentajeList;
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
	public List getStoTipoDocumentoSpvd() {
		return stoTipoDocumentoSpvd;
	}

	public void setStoTipoDocumentoSpvd(List stoTipoDocumentoSpvd) {
		this.stoTipoDocumentoSpvd = stoTipoDocumentoSpvd;
	}

	/**
	 * @return
	 */
	public List getStoTipoClienteList() {
		return stoTipoClienteList;
	}

	/**
	 * @param stoTipoClienteList
	 */
	public void setStoTipoClienteList(List stoTipoClienteList) {
		this.stoTipoClienteList = stoTipoClienteList;
	}

	/**
	 * @return
	 */
	public LabelValue[] getStoSubtipoClienteList() {
		return stoSubtipoClienteList;
	}

	/**
	 * @param stoSubtipoClienteList
	 */
	public void setStoSubtipoClienteList(LabelValue[] stoSubtipoClienteList) {
		this.stoSubtipoClienteList = stoSubtipoClienteList;
	}

	/**
	 * @return
	 */
	public LabelValue[] getStoTipoClasificacionList() {
		return stoTipoClasificacionList;
	}

	/**
	 * @param stoTipoClasificacionList
	 */
	public void setStoTipoClasificacionList(
			LabelValue[] stoTipoClasificacionList) {
		this.stoTipoClasificacionList = stoTipoClasificacionList;
	}

	/**
	 * @return
	 */
	public LabelValue[] getStoClasificacionList() {
		return stoClasificacionList;
	}

	/**
	 * @param stoClasificacionList
	 */
	public void setStoClasificacionList(LabelValue[] stoClasificacionList) {
		this.stoClasificacionList = stoClasificacionList;
	}

	/**
	 * @return
	 */
	public List getStoRegionList() {
		return stoRegionList;
	}

	/**
	 * @param stoRegionList
	 */
	public void setStoRegionList(List stoRegionList) {
		this.stoRegionList = stoRegionList;
	}

	/**
	 * @return
	 */
	public LabelValue[] getStoZonaList() {
		return stoZonaList;
	}

	/**
	 * @param stoZonaList
	 */
	public void setStoZonaList(LabelValue[] stoZonaList) {
		this.stoZonaList = stoZonaList;
	}

	/**
	 * @return
	 */
	public List getStoBloqueoControlList() {
		return stoBloqueoControlList;
	}

	/**
	 * @param stoBloqueoControlList
	 */
	public void setStoBloqueoControlList(List stoBloqueoControlList) {
		this.stoBloqueoControlList = stoBloqueoControlList;
	}

	/**
	 * @return
	 */
	public List getStoLevantamientoErroresClientesList() {
		return stoLevantamientoErroresClientesList;
	}

	/**
	 * @param stoLevantamientoErroresClientesList
	 */
	public void setStoLevantamientoErroresClientesList(
			List stoLevantamientoErroresClientesList) {
		this.stoLevantamientoErroresClientesList = stoLevantamientoErroresClientesList;
	}
	
	/**
	 * @return
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}