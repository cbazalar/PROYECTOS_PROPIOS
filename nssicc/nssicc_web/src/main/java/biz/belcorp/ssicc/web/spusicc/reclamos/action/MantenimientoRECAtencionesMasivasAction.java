package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ProcesoRECAtencionesMasivas;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECAtencionesMasivasService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECIngresoAtencionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.MantenimientoRECAtencionesMasivasForm;

@SessionScoped
@ManagedBean
@SuppressWarnings({ "unchecked", "rawtypes" })
public class MantenimientoRECAtencionesMasivasAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 7446674821680736907L;

	private List recBusquedaIngresoAtencionesList;
	private List recProcesarIngresoAtencionesList;
	private List recClientesIngresoAtencionesList;
	private List listResultConsultoras;
	private List recListaAtencionesMasivasList;

	private LabelValue[] recTipoOperacionList;
	private String numeroLote = "";
	private String attachment;
	private boolean flagValidar;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {

		MantenimientoRECAtencionesMasivasForm z = new MantenimientoRECAtencionesMasivasForm();
		return z;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {

		this.mostrarBotonBuscar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonSave = false;
		this.mostrarBotonSalir = false;
		this.mostrarBotonConsultar = false;

		log.debug("Inicio view ");
		try {

			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			MantenimientoRECAtencionesMasivasForm f = (MantenimientoRECAtencionesMasivasForm) this.formBusqueda;
			MantenimientoRECIngresoAtencionesService service = (MantenimientoRECIngresoAtencionesService) getBean("spusicc.mantenimientoRECIngresoAtencionesService");
			f.setMostrarEjecutar(Constants.NO);
			Map criteria = new HashMap();
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("estadoCampanha", Constants.NUMERO_CERO);
			String campahniaActual = service.getObtenerCampahniaActual(criteria);

			if (campahniaActual == null) {
				campahniaActual = "";
			}

			f.setCodTipoProducto("");
			f.setCodTipoAtencion("");
			f.setCodTipoOperDefault("");

			initForm(f);
			f.setCodigoPeriodoProceso(campahniaActual);
			f.setsFlagTipoOper("N");
			this.numeroLote = "";
			
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			this.recTipoOperacionList = ajax.getTipoOperacionList("matriz","normal");

		} catch (Exception e) {

			String error = e.getMessage();
			if (StringUtils.isBlank(error))
				error = e.getLocalizedMessage();

			this.addError("Error: ", getResourceMessage("errors.detail" + error));

		}

	}

	/**
	 * @throws Exception
	 */
	public void refresh() throws Exception {
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoRECAtencionesMasivasForm f = (MantenimientoRECAtencionesMasivasForm) this.formBusqueda;
		MantenimientoRECIngresoAtencionesService service = (MantenimientoRECIngresoAtencionesService) getBean("spusicc.mantenimientoRECIngresoAtencionesService");
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha", Constants.NUMERO_CERO);
		String campahniaActual = service.getObtenerCampahniaActual(criteria);

		if (campahniaActual == null) {
			campahniaActual = "";
		}

		f.setCodTipoProducto("");
		f.setCodTipoAtencion("");
		f.setCodTipoOperDefault("");

		initForm(f);
		f.setCodigoPeriodoProceso(campahniaActual);
		f.setsFlagTipoOper("N");
	}

	/**
	 * @param request
	 * @param f
	 *            Inicializa campos del formulario
	 */
	private void initForm(MantenimientoRECAtencionesMasivasForm f) {
		MantenimientoRECIngresoAtencionesService service = (MantenimientoRECIngresoAtencionesService) getBean("spusicc.mantenimientoRECIngresoAtencionesService");

		// this.recBusquedaIngresoAtencionesList = null;
		// this.recProcesarIngresoAtencionesList = null;
		// this.recClientesIngresoAtencionesList = null;
		// this.listResultConsultoras = null;

		f.setTipoProducto("matriz");
		f.setTipoAtencion("normal");

		f.setMostrarPanel(Constants.NO);
		f.setMostrarEjecutar(Constants.NO);
		f.setCodigoPeriodoProceso("");
		f.setCodigoPeriodoReferencia("");
		f.setCodigoVenta("");
		f.setFlagVacio("");
		f.setNumeroLote("");

		Map criteria = new HashMap();
		List parametrosTipoOperacion = service
				.getCodigosParamIngreAtenc(criteria);

		if (parametrosTipoOperacion.size() > 0) {
			Map valores = (Map) parametrosTipoOperacion.get(0);

			String codOperEnvMatNrmConPed = (String) valores
					.get("codOperEnvMatNrmConPed");
			String codOperEnvMatExpConPed = (String) valores
					.get("codOperEnvMatExpConPed");

			if (StringUtils.isNotBlank(codOperEnvMatNrmConPed)
					&& StringUtils.isNotBlank(codOperEnvMatExpConPed)) {
				f.setActivarMatrizConPedido(Constants.SI);
			} else {
				f.setActivarMatrizConPedido(Constants.NO);
			}
		}

	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * 
	 *             Valida datos del cliente
	 * 
	 */
	private void validar(File file) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'validar' method");
		}
		log.debug("inicio validar");

		MantenimientoRECAtencionesMasivasService service = (MantenimientoRECAtencionesMasivasService) getBean("spusicc.mantenimientoRECAtencionesMasivasService");
		MantenimientoRECAtencionesMasivasForm formREC = (MantenimientoRECAtencionesMasivasForm) this.formBusqueda;

		ProcesoRECAtencionesMasivas procesoRECAtencionesMasivas = new ProcesoRECAtencionesMasivas();
		BeanUtils.copyProperties(procesoRECAtencionesMasivas, formREC);

		procesoRECAtencionesMasivas.setArchivo(file);
		Map resultado = service.getMantenimientoRECAtencionesMasivas(procesoRECAtencionesMasivas);

		List lista = (List) MapUtils.getObject(resultado, "list");
		// obtenemos las opciones de consulta que podra realizar el usuario
		// logueado
		String numfila = (String) MapUtils.getString(resultado, "numfila");
		String cantExis = (String) MapUtils.getString(resultado, "cantExis");
		String cantNoExis = (String) MapUtils
				.getString(resultado, "cantNoExis");

		this.recListaAtencionesMasivasList = lista;

		formREC.setMostrarPanel(Constants.SI);
		formREC.setMostrarEjecutar(Constants.SI);
		formREC.setNumfila(numfila);
		formREC.setCantExis(cantExis);
		formREC.setCantNoExis(cantNoExis);
		formREC.setsFlagTipoOper(Constants.SI);

		formREC.setCodTipoProducto(formREC.getTipoProducto());
		formREC.setCodTipoAtencion(formREC.getTipoAtencion());
		formREC.setCodTipoOperDefault(formREC.getTipoOperacion());

	}

	/**
	 * Metodo para procesar los registros de las atenciones masivas correctas
	 * 
	 * @param event
	 * @throws Exception
	 */
	public void procesar(ActionEvent event) throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'procesar' method");
		}

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		MantenimientoRECAtencionesMasivasService service = (MantenimientoRECAtencionesMasivasService) getBean("spusicc.mantenimientoRECAtencionesMasivasService");
		MantenimientoRECAtencionesMasivasForm formREC = (MantenimientoRECAtencionesMasivasForm) this.formBusqueda;
		
		
		if(formREC.getCantNoExis()!=null && Integer.parseInt(formREC.getCantNoExis())>0){
			addWarn("Mensaje: ",
					getResourceMessage("mantenimientoRECAtencionesMasivasForm.registroConError"));
			return;
		}

		List listaMasivas = recListaAtencionesMasivasList;
		log.debug("lista Masiva "
				+ String.valueOf((listaMasivas != null ? listaMasivas.size()
						: 0)));

		String cantNoExis = formREC.getCantNoExis();

		Map criteria = new HashMap();

		criteria.put("tipoProducto", formREC.getTipoProducto());
		criteria.put("tipoAtencion", formREC.getTipoAtencion());
		criteria.put("codigoCliente", null);
		criteria.put("campanhaProceso", formREC.getCodigoPeriodoProceso());
		criteria.put("campanhaReferencia", formREC.getCodigoPeriodoReferencia());
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoUsuario", usuario.getLogin());
		criteria.put("mensajeError", "");
		criteria.put("tipoOperacion", formREC.getTipoOperacion());
		criteria.put("listaMasivas", listaMasivas);

		// Ejecuta el proceso en el Service
		Map resultado = service.executeProcesarRECAtencionesMasivas(criteria);

		String numeroLote = (String) MapUtils.getString(resultado, "numeroLote");

		formREC.setNumeroLote(numeroLote);
		formREC.setMostrarPanel(Constants.SI);
		formREC.setMostrarEjecutar(Constants.NO);
		formREC.setsFlagTipoOper(Constants.SI);

		this.numeroLote = numeroLote;

		if (log.isDebugEnabled()) {
			log.debug("numeroLote = " + numeroLote);
		}

		this.addInfo("Mensaje: ",this.getResourceMessage("mantenimientoRECAtencionesMasivasForm.procesado"));

		List lista2 = this.recListaAtencionesMasivasList;

		List lista = (List) criteria.get("lista");

		this.recListaAtencionesMasivasList = new ArrayList();
		this.recListaAtencionesMasivasList = lista;
		

	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * 
	 *             Pagina en base a los valores que se encuentran en memoria
	 * 
	 */
	public void paginado() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'paginado' method");
		}

		List busquedaList = this.recListaAtencionesMasivasList;
		this.recListaAtencionesMasivasList = busquedaList;

	}

	public void loadTipoOperacion(ValueChangeEvent event) throws Exception {

		String valor = (String) event.getNewValue();
		MantenimientoRECAtencionesMasivasForm f = (MantenimientoRECAtencionesMasivasForm) this.formBusqueda;

		// String tipoProducto = f.getTipoProducto();
		String tipoAtencion = f.getTipoAtencion();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		this.recTipoOperacionList = ajax.getTipoOperacionList(valor,
				tipoAtencion);
	}

	public void loadTipoOperacion2(ValueChangeEvent event) throws Exception {

		String valor = (String) event.getNewValue();
		MantenimientoRECAtencionesMasivasForm f = (MantenimientoRECAtencionesMasivasForm) this.formBusqueda;

		String tipoProducto = f.getTipoProducto();
		// String tipoAtencion = f.getTipoAtencion();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		this.recTipoOperacionList = ajax.getTipoOperacionList(tipoProducto,
				valor);
	}

	public void validarCodigoPeriodoReferencia(String event) {

		MantenimientoRECAtencionesMasivasForm f = (MantenimientoRECAtencionesMasivasForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		// String codigoPeriodoReferencia = (String)event.getNewValue();
		boolean flag = false;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		String data = ajax.getValidarCampanhiaActiva(pais.getCodigo(), event);

		if (data == "1") {
			String mensajeX = getResourceMessage("mantenimientoRECAtencionesMasivasForm.msg.errorCodPeriodoReferencia");
			addError("Error: ", mensajeX);
			flag = true;
		}

		if (flag == true) {
			f.setCodigoPeriodoReferencia("");
			event = "";
		} else
			f.setCodigoPeriodoReferencia(event);

	}

	public void validarDatos(FileUploadEvent event) throws Exception {
		try {
			MantenimientoRECAtencionesMasivasForm f = (MantenimientoRECAtencionesMasivasForm) this.formBusqueda;
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			this.flagValidar=true;

			if (StringUtils.isBlank(f.getCodigoPeriodoReferencia())) {
				this.flagValidar=false;
				throw new Exception(
						this.getResourceMessage("mantenimientoRECAtencionesMasivasForm.msg.errorCampanhaReferencia"));

			}

			
			String data = ajax.getValidarCampanhiaActiva(pais.getCodigo(),
					f.getCodigoPeriodoReferencia());

			if (data == "1") {
				this.flagValidar=false;
				throw new Exception(
						this.getResourceMessage("mantenimientoRECAtencionesMasivasForm.msg.errorCodPeriodoReferencia"));
			}
			f.setArchivo(event.getFile());
			//UploadedFile arch = f.getArchivo();
			String directorio = "/ssicc/temp/";
			this.uploadArchivo(directorio, f.getArchivo().getFileName(), f
					.getArchivo().getInputstream());
			File file = new File(directorio, f.getArchivo().getFileName());
			this.setAttachment(event.getFile().getFileName());
			validar(file);

//			if (arch != null) {
//				// var extension =
//				// (arch.substring(arch.lastIndexOf("."))).toLowerCase();
//				String nombreArchivo = f.getArchivo().getFileName();
//				String extensionArchivo = obtenerExtensionArchivo(nombreArchivo);
//				f.setExtensionArchivo(extensionArchivo);
//				// alert(extension);
//				if (extensionArchivo.equals(".xls")
//						|| extensionArchivo.equals("xlsx")) {
//					// f.action='validarMantenimientoRECAtencionesMasivas.do';
//					validar(file);
//				}
//			}
		} catch (Exception e) {
			this.addError("Mensaje", obtieneMensajeErrorException(e));
		}

	}

	/**
	 * Metodo que guarda el archivo en el servidor
	 * 
	 * @param fileName
	 * @param in
	 * @throws Exception
	 */
	protected final void uploadArchivo(String directorio, String fileName,
			InputStream in) throws Exception {

		// Escribe el contenido de un archivo de entrada a un FileOutputStream
		// de salida
		OutputStream out = new FileOutputStream(new File(directorio + fileName));

		int read = 0;
		byte[] bytes = new byte[1024];

		while ((read = in.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}

		in.close();
		out.flush();
		out.close();
	}

	private String obtenerExtensionArchivo(String nombreArchivo)
			throws Exception {
		return nombreArchivo.substring(nombreArchivo.length() - 4);
	}

	public List getRecBusquedaIngresoAtencionesList() {
		return recBusquedaIngresoAtencionesList;
	}

	public void setRecBusquedaIngresoAtencionesList(
			List recBusquedaIngresoAtencionesList) {
		this.recBusquedaIngresoAtencionesList = recBusquedaIngresoAtencionesList;
	}

	public List getRecProcesarIngresoAtencionesList() {
		return recProcesarIngresoAtencionesList;
	}

	public void setRecProcesarIngresoAtencionesList(
			List recProcesarIngresoAtencionesList) {
		this.recProcesarIngresoAtencionesList = recProcesarIngresoAtencionesList;
	}

	public List getRecClientesIngresoAtencionesList() {
		return recClientesIngresoAtencionesList;
	}

	public void setRecClientesIngresoAtencionesList(
			List recClientesIngresoAtencionesList) {
		this.recClientesIngresoAtencionesList = recClientesIngresoAtencionesList;
	}

	public List getListResultConsultoras() {
		return listResultConsultoras;
	}

	public void setListResultConsultoras(List listResultConsultoras) {
		this.listResultConsultoras = listResultConsultoras;
	}

	public List getRecListaAtencionesMasivasList() {
		return recListaAtencionesMasivasList;
	}

	public void setRecListaAtencionesMasivasList(
			List recListaAtencionesMasivasList) {
		this.recListaAtencionesMasivasList = recListaAtencionesMasivasList;
	}

	public LabelValue[] getRecTipoOperacionList() {
		return recTipoOperacionList;
	}

	public void setRecTipoOperacionList(LabelValue[] recTipoOperacionList) {
		this.recTipoOperacionList = recTipoOperacionList;
	}

	public String getNumeroLote() {
		return numeroLote;
	}

	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public boolean isFlagValidar() {
		return flagValidar;
	}

	public void setFlagValidar(boolean flagValidar) {
		this.flagValidar = flagValidar;
	}
	
	
}
