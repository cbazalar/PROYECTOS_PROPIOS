package biz.belcorp.ssicc.web.spusicc.cuentacorriente.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.LabelDatosConsultoraValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.DatosConsultoraBasico;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.RegularizacionPagoBancario;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ConsultaCCCGenericoService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.MantenimientoCCCRegularizacionPagosBancariosService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.MantenimientoCCCRegularizacionPagosBancariosForm;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.MantenimientoCCCRegularizacionPagosBancariosSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "unchecked", "rawtypes" })
public class MantenimientoCCCRegularizacionPagosBancariosSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 1L;
	private Integer longitudCampoClientes;
	private String cccLongitudNumeroCedula;
	private List siccCuentaCorrienteList;
	private List cccTipoErroresPagosBancariosList;
	private List cccPagosBancariosPorRegularizarList;
	private Object[] columnasSeleccionadas;
	private DataTableModel datatableCCCD;
	private Boolean mostrarBotonesAdic;
	private String oidMovBancario;

	@ManagedProperty(value = "#{mantenimientoCCCRegularizacionPagosBancariosPopupDividirPagoAction}")
	private MantenimientoCCCRegularizacionPagosBancariosPopupDividirPagoAction popupDividirPagoAction;

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void ejecutar(ActionEvent evt) {
		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'ejecutar' method");
		}
		String mensaje = "";
		try {
			MantenimientoCCCRegularizacionPagosBancariosSearchForm f = (MantenimientoCCCRegularizacionPagosBancariosSearchForm) this.formBusqueda;

			List params = new ArrayList();

			MantenimientoCCCRegularizacionPagosBancariosService service = (MantenimientoCCCRegularizacionPagosBancariosService) getBean("spusicc.mantenimientoCCCRegularizacionPagosBancariosService");

			Map datos = new HashMap();

			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

			datos.put("codigoPais", f.getCodigoPais());
			datos.put("codigoCuentaCorrienteBancaria",
					f.getCodigoCuentaCorrienteBancariaSearch());
			datos.put("codigoUsuario", usuario.getLogin());
			datos.put("codigoTipoOrigen", "REG");

			service.getNumeroLote(datos);
			String numeroLote = datos.get("numeroLote").toString();

			if (log.isDebugEnabled()) {
				log.debug("JFA Obteniendo Numero de Lote " + numeroLote);
			}
			

			List lista = this.cccPagosBancariosPorRegularizarList;
			List listaDetalle = new ArrayList();

			for (int i = 0; i < this.columnasSeleccionadas.length; i++) {
				
				Map criteria = new HashMap();
				HashMap mapa = (HashMap) this.columnasSeleccionadas[i];
				String codigoBancario = mapa.get("oidMovimientoBancario").toString();
				String codigoConsultora = mapa.get("codigoConsultora").toString();
				String importePagoDetalle = mapa.get("importePago").toString();
				if(lista!=null && lista.size()>0){
					for(int j = 0; j<lista.size() ; j++){
						Map index = (Map)lista.get(j);
						if(StringUtils.equalsIgnoreCase(MapUtils.getString(index, "oidMovimientoBancario"),codigoBancario)){
							listaDetalle = (List)index.get("listaDetalle");
						}
					}
				}
				criteria.put("numeroLoteBancario", numeroLote);
				criteria.put("oidMovimientoBancario", codigoBancario);
				criteria.put("codigoConsultora",codigoConsultora );
				criteria.put("numeroConsecutivoTransaccion",String.valueOf(i));
				criteria.put("importePagoDetalle",importePagoDetalle );
				criteria.put("listaDetalle", listaDetalle);
				params.add(criteria);
			}
//			if (objects.length == 0) {
//				mensaje = this
//						.getResourceMessage("mantenimientoRECDigitacionBoletasRecojoForm.error.notRegistros");
//				this.addError("Error : ", mensaje);
//				return;
//			}
			

			service.updateListPagoBancarioPorRegularizar(params);

			if (log.isDebugEnabled()) {
				log.debug("Registrar el Lote Bancario " + numeroLote);
				log.debug(datos.toString());
			}

			service.executeRegistrarLoteBancario(datos);
			this.setFindAttributes();
			buscar();
			String[] object = new String[1];
			object[0] = numeroLote;
			mensaje = this.getResourceMessage("mantenimientoCCCRegularizacionPagosBancariosForm.msj.registrar", object);
			this.addInfo("", mensaje);

		} catch (Exception e) {
			String error = this.obtieneMensajeErrorException(e);
			this.addError("Error", error);
		}
	}

	/**
	 * @param indice
	 */
	public void validaConsultoraOnEnter() {
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			int  i = Integer.parseInt(externalContext.getRequestParameterMap().get("index"));
			if (i < 0)
				return;
			HashMap bean = (HashMap) this.cccPagosBancariosPorRegularizarList.get(i);
			String codigoConsultora = (String) bean.get("codigoConsultora");			

			if (StringUtils.isBlank(codigoConsultora)) {
				return;
			} else {
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				String codigoPais = this.mPantallaPrincipalBean.getCurrentCountry().getCodigo();
				String codigoClienteCompletado = completarCaracteres(codigoConsultora, this.longitudCampoClientes, "0");				
				LabelDatosConsultoraValue[] obtenerDatos = null;
				obtenerDatos = aSvc.getCabeceraConsultoraSimple(codigoPais,codigoClienteCompletado);
				if(obtenerDatos!=null){
					String nombreConsultora = obtenerDatos[0].getNombreConsultora();
					((HashMap) this.cccPagosBancariosPorRegularizarList.get(i)).put("codigoConsultora", codigoClienteCompletado);	
					((HashMap) this.cccPagosBancariosPorRegularizarList.get(i)).put("nombreConsultora", nombreConsultora);				
				}else{
					((HashMap) this.cccPagosBancariosPorRegularizarList.get(i)).put("codigoConsultora", codigoClienteCompletado);
					this.addError("Error:", this.getResourceMessage("mensaje.noExisteConsultora"));
					return;
				}
			}
			
		} catch (Exception e) {
			this.addError("Error:", this.obtieneMensajeErrorException(e));
		}
		
	}

	/**
	 * @param indice
	 */
	public void validaDocIdentidadOnEnter() {
		try {
			
		
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		int  i = Integer.parseInt(externalContext.getRequestParameterMap().get("index"));
		MantenimientoCCCRegularizacionPagosBancariosSearchForm f = (MantenimientoCCCRegularizacionPagosBancariosSearchForm) this.formBusqueda;

		String mensaje = "";
		if (i < 0)
			return;
		HashMap bean = (HashMap) this.cccPagosBancariosPorRegularizarList
				.get(i);
		String codigoConsultora = "";
		String numeroDocumento = (String) bean.get("cedulaConsultora");
		String tipoDocumento = (String) bean.get("tipoDocumento");

		if (StringUtils.isBlank(numeroDocumento)) {
			return;
		} else {
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			String codigoPais = this.mPantallaPrincipalBean.getCurrentCountry()
					.getCodigo();
			DatosConsultoraBasico[] obtenerDatos = null;
			obtenerDatos = aSvc.getCabeceraConsultoraByDocIdentidad(codigoPais,
					numeroDocumento, tipoDocumento, codigoConsultora);
			
			if(obtenerDatos!=null){
				String nombreConsultora = obtenerDatos[0].getNombreConsultora();
				String codigoConsultoraList = obtenerDatos[0].getCodigoConsultora();
				((HashMap) this.cccPagosBancariosPorRegularizarList.get(i)).put("nombreConsultora", nombreConsultora);
				((HashMap) this.cccPagosBancariosPorRegularizarList.get(i)).put("codigoConsultora", codigoConsultoraList);	
			}else{
				this.addError("Error:", this.getResourceMessage("mensaje.documentoIdentidadNoExiste"));
				return;
			}
		}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	/**
	 * @param evt
	 */
	public void archivar(ActionEvent evt) {

		if (log.isDebugEnabled()) {
			log.debug("JFA : Entering 'archivar' method");
		}
		try {
			MantenimientoCCCRegularizacionPagosBancariosSearchForm f = (MantenimientoCCCRegularizacionPagosBancariosSearchForm) this.formBusqueda;
			String[] seleccionados = f.getSelectedItems();
			List params = new ArrayList();

			MantenimientoCCCRegularizacionPagosBancariosService service = (MantenimientoCCCRegularizacionPagosBancariosService) getBean("spusicc.mantenimientoCCCRegularizacionPagosBancariosService");

			Map datos = new HashMap();

			for (int i = 0; i < seleccionados.length; i++) {
				String[] split = StringUtils.split(seleccionados[i], "-");
				datos.put("oidMovimientoBancario", split[0]);
				params.add(datos);
			}

			if (log.isDebugEnabled()) {
				log.debug("Los OID Seleccionados son :" + datos.toString());
			}

			service.archivarListPagoBancarioPorRegularizar(params);

			this.setFindAttributes();

		} catch (Exception e) {
			this.obtieneMensajeErrorException(e);
		}
	}
	
	public String completarCaracteres(String valor, Integer longitud, String caracter){
		String valorAux ="";
		
		if (valor.length() != 0) {
			int faltante = longitud - valor.length();	
			
			if (faltante >= 0) {
				for (int i = 0; i < faltante; i++) {
					valorAux = valorAux + caracter;
				}
				valorAux = valorAux + valor;
			}
			else {			
				faltante = valor.length() - longitud;
				valorAux = valor.substring(faltante, longitud);
				
			}
		}
		
		return valorAux;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoCCCRegularizacionPagosBancariosList";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoCCCRegularizacionPagosBancariosForm";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoCCCRegularizacionPagosBancariosSearchForm form = new MantenimientoCCCRegularizacionPagosBancariosSearchForm();
		return form;
	}

	/**Inicializa el Popup
	 * @param event
	 */
	public void abrirPopup(ActionEvent event) {
		try {

			ExternalContext externalContext = FacesContext.getCurrentInstance()
					.getExternalContext();
			this.oidMovBancario = externalContext.getRequestParameterMap()
					.get("oidMovimientoBancario").toString();
			this.popupDividirPagoAction.setCccPagosBancariosPorRegularizarList(this.cccPagosBancariosPorRegularizarList);
			this.popupDividirPagoAction.setOidMovBancario(this.oidMovBancario);
			this.popupDividirPagoAction.inicializarValores();

			return;
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * 
	 */
	public void buscar() {
		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'find' method");
		}

		MantenimientoCCCRegularizacionPagosBancariosSearchForm f = (MantenimientoCCCRegularizacionPagosBancariosSearchForm) this.formBusqueda;
		
		if(StringUtils.isBlank(f.getCodigoError())){
			f.setCodigoError("");
		}

		f.setFechaPagoDesde(DateUtil.convertDateToString(f.getFechaPagoDesdeD()));
		f.setFechaPagoHasta(DateUtil.convertDateToString(f.getFechaPagoHastaD()));
		f.setFechaProcesoDesde(DateUtil.convertDateToString(f.getFechaProcesoDesdeD()));
		f.setFechaProcesoHasta(DateUtil.convertDateToString(f.getFechaProcesoHastaD()));
		
		if(StringUtils.isBlank(f.getImportePagoDesde())){
			f.setImportePagoDesde("");
		}
		if(StringUtils.isBlank(f.getImportePagoHasta())){
			f.setImportePagoHasta("");
		}

		this.mostrarListaBusqueda = false;
		MantenimientoCCCRegularizacionPagosBancariosService service = (MantenimientoCCCRegularizacionPagosBancariosService) getBean("spusicc.mantenimientoCCCRegularizacionPagosBancariosService");

		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoCuentaCorrienteBancaria",
				f.getCodigoCuentaCorrienteBancaria());
		criteria.put("codigoError", f.getCodigoError());
		criteria.put("importeProcesoDesde", f.getFechaProcesoDesde());
		criteria.put("importeProcesoHasta", f.getFechaProcesoHasta());
		criteria.put("fechaPagoDesde", f.getFechaPagoDesde());
		criteria.put("fechaPagoHasta", f.getFechaPagoHasta());
		criteria.put("importePagoDesde", f.getImportePagoDesde());
		criteria.put("importePagoHasta", f.getImportePagoHasta());

		f.setCodigoCuentaCorrienteBancariaSearch(f
				.getCodigoCuentaCorrienteBancaria());

		List resultado = service.getPagosBancariosPorRegularizarList(criteria);

		String cantidadRegistrosDevueltos = String.valueOf(resultado.size());
		this.cccPagosBancariosPorRegularizarList = new ArrayList();
		this.cccPagosBancariosPorRegularizarList = resultado;
		int tamanio = this.cccPagosBancariosPorRegularizarList.size();
		if (tamanio > 0)
			this.mostrarBotonesAdic = true;
		for (int i = 0; i < tamanio; i++) {
			((HashMap) this.cccPagosBancariosPorRegularizarList.get(i)).put(
					"nombreConsultora", "");
		}

		if (log.isDebugEnabled()) {
			log.debug("JFA Return resultado");
			log.debug(cantidadRegistrosDevueltos);
		}
		
		this.datatableCCCD = new DataTableModel(this.cccPagosBancariosPorRegularizarList);
		
		if(this.cccPagosBancariosPorRegularizarList.size()==0 || this.cccPagosBancariosPorRegularizarList==null){
			this.addWarn("Warning: ", this.getResourceMessage("errors.datos.fuentes.busqueda"));			
		}

	}

	/**
	 * @param evt
	 */
	public void buscar(ActionEvent evt) {
		buscar();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		return null;
	}

	/**
	 * @param evt
	 */
	public void eliminar(ActionEvent evt) {
		if (log.isDebugEnabled()) {
			log.debug("JFA : Entering 'eliminar' method");
		}

		List params = new ArrayList();
		String mensaje = "";

		// HashMap<String, String> objSeleccionado = this.columnasSeleccionadas;
		Map datos = new HashMap();
		Object[] objects = this.columnasSeleccionadas;
		if (objects.length == 0) {
			mensaje = this
					.getResourceMessage("mantenimientoRECDigitacionBoletasRecojoForm.error.notRegistros");
			this.addError("Error : ", mensaje);
			return;
		}
		for (int i = 0; i < objects.length; i++) {
			Object object = objects[i];
			if (object instanceof Map) {
				HashMap map = (HashMap) object;
				String oidMovimientoBancario = map.get("oidMovimientoBancario")
						.toString();
				datos.put("oidMovimientoBancario", oidMovimientoBancario);
				params.add(datos);

			}
		}

		MantenimientoCCCRegularizacionPagosBancariosService service = (MantenimientoCCCRegularizacionPagosBancariosService) getBean("spusicc.mantenimientoCCCRegularizacionPagosBancariosService");

		if (log.isDebugEnabled()) {
			log.debug("Los OID Seleccionados son :" + datos.toString());
		}

		service.deleteListPagoBancarioPorRegularizar(params);
		this.buscar();
		this.datatableCCCD = new DataTableModel(
				this.cccPagosBancariosPorRegularizarList);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'setSaveAttributes' method");
		}

		String mensaje = "";

		MantenimientoCCCRegularizacionPagosBancariosForm f = (MantenimientoCCCRegularizacionPagosBancariosForm) this.formMantenimiento;

		MantenimientoCCCRegularizacionPagosBancariosService service = (MantenimientoCCCRegularizacionPagosBancariosService) getBean("spusicc.mantenimientoCCCRegularizacionPagosBancariosService");

		RegularizacionPagoBancario pagoBancario = new RegularizacionPagoBancario();

		BeanUtils.copyProperties(pagoBancario, f);
		if (!f.isNewRecord()) {
			if (log.isDebugEnabled()) {
				log.debug("EN EL CASO QUE SEA MODIFICACION");
			}

			Map params = BeanUtils.describe(pagoBancario);

			if (log.isDebugEnabled()) {
				log.debug("params " + params.toString());
				log.debug("Llamando al Service");
			}

			service.updatePagoBancarioPorRegularizar(params);

			service.executeRegistrarLoteBancario(params);

			mensaje = "mantenimientoCCCRegularizacionPagosBancariosList.updated";
			this.addError("Error : ", mensaje);
		}

		return true;
	}

	/**
	 * Obtiene key del registro seleccionado en el JSP de Busqueda
	 * 
	 * @param id
	 * @param f
	 * @throws Exception
	 */
	private void obtenerKeyMensaje(String id, RegularizacionPagoBancario f)
			throws Exception {

		// obteniendo Pais
		f.setOidMovimientoBancario(StringUtils.split(id, "-")[0]);
		f.setCodigoPais(StringUtils.split(id, "-")[1]);
		f.setCodigoSociedad(StringUtils.split(id, "-")[2]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		String id = "";
		MantenimientoCCCRegularizacionPagosBancariosForm f = (MantenimientoCCCRegularizacionPagosBancariosForm) this.formMantenimiento;

		if (id != null) {
			if (log.isDebugEnabled()) {
				log.debug("Id seleccionado de la lista: " + id);
			}

			MantenimientoCCCRegularizacionPagosBancariosService service = (MantenimientoCCCRegularizacionPagosBancariosService) getBean("spusicc.mantenimientoCCCRegularizacionPagosBancariosService");

			RegularizacionPagoBancario pagoBancario = new RegularizacionPagoBancario();

			log.debug("JFA - Obtener Key");
			obtenerKeyMensaje(id, pagoBancario);

			log.debug(pagoBancario.getOidMovimientoBancario());

			pagoBancario = (RegularizacionPagoBancario) service
					.getRegularizacionPagoBancarioById(pagoBancario);

			log.debug("JFA - BeanUtils");
			BeanUtils.copyProperties(f, pagoBancario);

			log.debug("JFA - Todo OK");

		}
		return f;
	}

	/**
	 * 
	 */
	public void inicializar() {
		this.mostrarBotonBuscar = false;
		this.mostrarBotonesAdic = false;
		this.mostrarListaBusqueda = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonModificar = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'setViewAttributes' method");
		}

		inicializar();

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		// Map para almacenar los parametros
		Map criteria = new HashMap();

		// Instanciando el Service
		ConsultaCCCGenericoService serviceGenericoCCC = (ConsultaCCCGenericoService) getBean("spusicc.consultaCCCGenericoService");

		// Seteando el tama�o del Campo Consultora
		ClienteUAGenerarService clienteService = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");
		this.longitudCampoClientes = clienteService
				.getTamanhoNumeroCliente(pais.getCodigo());
		if (log.isDebugEnabled()) {
			log.debug("------------------ "
					+ clienteService.getTamanhoNumeroCliente(pais.getCodigo()));
		}

		this.cccLongitudNumeroCedula = serviceGenericoCCC
				.getParametroPaisbyCodigo("LongitudDocumentoIdentidad");
		if (log.isDebugEnabled()) {
			log.debug("------------------ Longitud Documento de Identidad Consultora "
					+ serviceGenericoCCC
							.getParametroPaisbyCodigo("LongitudDocumentoIdentidad"));
		}

		// Obteniedo el listado de las Cuentas Corrientes Bancarias
		criteria.put("codigoPais", pais.getCodigo());

		// Obteniedo el listado de los Errores de los Pagos Bancarios
		this.cccTipoErroresPagosBancariosList = serviceGenericoCCC
				.getTiposErrorPagoBancarioList();

		this.siccCuentaCorrienteList = serviceGenericoCCC
				.getCuentasCorrientesBancariasList(criteria);

		if (log.isDebugEnabled()) {
			log.debug("JFA Todo Ok: Redireccionando");
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {
		if(log.isDebugEnabled()){
			log.debug("setValidarConfirmar()...");
		}
		
		String mensaje = "";
		
		if(StringUtils.equals(accion, "EJECUTAR")){
			
		}
		
		if(StringUtils.equals(accion, "ELIMINAR")){
			
		}
		
		return mensaje;
	}

	/**
	 * @return the longitudCampoClientes
	 */
	public Integer getLongitudCampoClientes() {
		return longitudCampoClientes;
	}

	/**
	 * @param longitudCampoClientes
	 *            the longitudCampoClientes to set
	 */
	public void setLongitudCampoClientes(Integer longitudCampoClientes) {
		this.longitudCampoClientes = longitudCampoClientes;
	}

	/**
	 * @return the cccLongitudNumeroCedula
	 */
	public String getCccLongitudNumeroCedula() {
		return cccLongitudNumeroCedula;
	}

	/**
	 * @param cccLongitudNumeroCedula
	 *            the cccLongitudNumeroCedula to set
	 */
	public void setCccLongitudNumeroCedula(String cccLongitudNumeroCedula) {
		this.cccLongitudNumeroCedula = cccLongitudNumeroCedula;
	}

	/**
	 * @return the siccCuentaCorrienteList
	 */
	public List getSiccCuentaCorrienteList() {
		return siccCuentaCorrienteList;
	}

	/**
	 * @param siccCuentaCorrienteList
	 *            the siccCuentaCorrienteList to set
	 */
	public void setSiccCuentaCorrienteList(List siccCuentaCorrienteList) {
		this.siccCuentaCorrienteList = siccCuentaCorrienteList;
	}

	/**
	 * @return the cccTipoErroresPagosBancariosList
	 */
	public List getCccTipoErroresPagosBancariosList() {
		return cccTipoErroresPagosBancariosList;
	}

	/**
	 * @param cccTipoErroresPagosBancariosList
	 *            the cccTipoErroresPagosBancariosList to set
	 */
	public void setCccTipoErroresPagosBancariosList(
			List cccTipoErroresPagosBancariosList) {
		this.cccTipoErroresPagosBancariosList = cccTipoErroresPagosBancariosList;
	}

	/**
	 * @return the cccPagosBancariosPorRegularizarList
	 */
	public List getCccPagosBancariosPorRegularizarList() {
		return cccPagosBancariosPorRegularizarList;
	}

	/**
	 * @param cccPagosBancariosPorRegularizarList
	 *            the cccPagosBancariosPorRegularizarList to set
	 */
	public void setCccPagosBancariosPorRegularizarList(
			List cccPagosBancariosPorRegularizarList) {
		this.cccPagosBancariosPorRegularizarList = cccPagosBancariosPorRegularizarList;
	}

	/**
	 * @return the columnasSeleccionadas
	 */
	public Object[] getColumnasSeleccionadas() {
		return columnasSeleccionadas;
	}

	/**
	 * @param columnasSeleccionadas
	 *            the columnasSeleccionadas to set
	 */
	public void setColumnasSeleccionadas(Object[] columnasSeleccionadas) {
		this.columnasSeleccionadas = columnasSeleccionadas;
	}

	/**
	 * @return the datatableCCCD
	 */
	public DataTableModel getDatatableCCCD() {
		return datatableCCCD;
	}

	/**
	 * @param datatableCCCD
	 *            the datatableCCCD to set
	 */
	public void setDatatableCCCD(DataTableModel datatableCCCD) {
		this.datatableCCCD = datatableCCCD;
	}

	/**
	 * @return the mostrarBotonesAdic
	 */
	public Boolean getMostrarBotonesAdic() {
		return mostrarBotonesAdic;
	}

	/**
	 * @param mostrarBotonesAdic
	 *            the mostrarBotonesAdic to set
	 */
	public void setMostrarBotonesAdic(Boolean mostrarBotonesAdic) {
		this.mostrarBotonesAdic = mostrarBotonesAdic;
	}

	/**
	 * @return the popupDividirPagoAction
	 */
	public MantenimientoCCCRegularizacionPagosBancariosPopupDividirPagoAction getPopupDividirPagoAction() {
		return popupDividirPagoAction;
	}

	/**
	 * @param popupDividirPagoAction
	 *            the popupDividirPagoAction to set
	 */
	public void setPopupDividirPagoAction(
			MantenimientoCCCRegularizacionPagosBancariosPopupDividirPagoAction popupDividirPagoAction) {
		this.popupDividirPagoAction = popupDividirPagoAction;
	}

	/**
	 * @return the oidMovBancario
	 */
	public String getOidMovBancario() {
		return oidMovBancario;
	}

	/**
	 * @param oidMovBancario
	 *            the oidMovBancario to set
	 */
	public void setOidMovBancario(String oidMovBancario) {
		this.oidMovBancario = oidMovBancario;
	}
}