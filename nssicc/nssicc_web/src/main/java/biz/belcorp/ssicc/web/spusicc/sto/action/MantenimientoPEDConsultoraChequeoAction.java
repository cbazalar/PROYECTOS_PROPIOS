package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.sisicc.model.Cliente;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDConsultoraChequeoService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECIngresoAtencionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaConsultoraPOPUPSearchAction;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoPEDConsultoraChequeoForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoPEDConsultoraChequeoAction extends
		BaseConsultaAbstractAction {

	private static final long serialVersionUID = 3323859950750853269L;

	private List pedTipoChequeoList;
	private List stoLevantamientoErorresClientesList;
	private List stoResumenClientesList;
	private List pedResultadoConsultoraChequearList;
	private Boolean mostrarGrillas;
	private String stoLineaDefecto;
	private Integer longitudCampoClientes;
	private static final String POPUP_CONSULTORA = "CONSULTORA";
	private boolean mostrarPopupConsultora;
	private Object[] beanMantenimientoPEDConsultoraChequeo;
	private Boolean mostrarDatatable;
	private String oidPais;
	private DataTableModel dmPEDConsultoraChequeo;

	@ManagedProperty(value = "#{busquedaConsultoraPOPUPSearchAction}")
	private BusquedaConsultoraPOPUPSearchAction busquedaConsultoraPOPUPSearchAction;

	/*
	 * (non-Javadoc) Metodo para almacenar el codigo que se selecciono
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setAceptarPopup(javax.faces.event.ActionEvent, java.lang.String)
	 */
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopupHipCliente' method");
		}
		if (accion.equals(this.POPUP_CONSULTORA)) {
			this.busquedaConsultoraPOPUPSearchAction.verificarRegistro(event);
			if (this.busquedaConsultoraPOPUPSearchAction.isSeleccionoRegistro()) {
				Cliente cliente = (Cliente) this.busquedaConsultoraPOPUPSearchAction
						.getBeanRegistroSeleccionado();
				MantenimientoPEDConsultoraChequeoForm f = (MantenimientoPEDConsultoraChequeoForm) this.formBusqueda;
				f.setCodigoConsultora(cliente.getCodigo());
				String consu = cliente.getApellidoPaterno() + " "
						+ cliente.getApellidoMaterno() + " "
						+ cliente.getNombre();
				f.setDescripcionConsultora(consu);
				this.busquedaConsultoraPOPUPSearchAction
						.setBeanRegistroSeleccionado(null);
			}
		}
	}

	/*
	 * (non-Javadoc) metodo para salir del popup
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setSalirPopup()
	 */
	@Override
	protected void setSalirPopup() {
		this.mostrarPopupConsultora = false;
		this.busquedaConsultoraPOPUPSearchAction
				.setBeanRegistroSeleccionado(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setInvocarPopup(java.lang.String)
	 */
	@Override
	protected void setInvocarPopup(String accion) {
		this.mostrarProcesoBatch = false;
		if (accion.equals(this.POPUP_CONSULTORA)) {
			this.mostrarPopupConsultora = true;
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
		MantenimientoPEDConsultoraChequeoForm f = (MantenimientoPEDConsultoraChequeoForm) this.formBusqueda;
		try {
			f.setClienteFile(event.getFile());
			this.loadfile();
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * Metodo Ajax, para validar la consultora
	 */
	public void validarConsultora() {
		String mensaje = "";
		try {
			MantenimientoPEDConsultoraChequeoForm f = (MantenimientoPEDConsultoraChequeoForm) this.formBusqueda;
			String codigoCliente = "";
			f.setCodigoConsultora(StringUtils.leftPad(f.getCodigoConsultora(),
					this.longitudCampoClientes, "0"));
			codigoCliente = f.getCodigoConsultora();
			if (StringUtils.isEmpty(codigoCliente)) {
				mensaje = "Ingrese su documento de Identidad";
				this.addError("Error : ", mensaje);
				f.setCodigoConsultora(null);
			} else {
				AjaxService ajax = (AjaxService) getBean("ajaxService");
				String codCliente = ajax.getExisteCodigoCliente(this.oidPais,
						f.getCodigoConsultora());
				if (StringUtils.isBlank(codCliente)) {
					f.setCodigoConsultora(null);
					f.setDescripcionConsultora("");
					this.addWarn(
							"Error : ",
							"Codigo de Cliente no existe, por favor ingrese un codigo valido para hacer la busqueda");
				}
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * Se inserta una consultora a chequear
	 * 
	 * @param evt
	 * @return
	 */
	public void insertar(ActionEvent evt) {
		String mensaje = "";
		try {

			MantenimientoPEDConsultoraChequeoForm f = (MantenimientoPEDConsultoraChequeoForm) this.formBusqueda;
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			MantenimientoPEDConsultoraChequeoService service = (MantenimientoPEDConsultoraChequeoService) getBean("spusicc.pedidos.mantenimientoPEDConsultoraChequeoService");
			Map criteria = new HashMap();
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("codigoTipoChequeo", f.getCodigoTipoChequeo());
			criteria.put("codigoPeriodo", f.getCodigoPeriodo());
			/*-------------------------*/
			String[] arrlistaClientes = new String[0];
			List clienteList = new ArrayList(); // result
			Long longitudPais = pais.getLongitudCodigoCliente();
			String codigoCliente = f.getCodigoConsultora();
			List listaClientes = this.stoLevantamientoErorresClientesList; // grilla del archivo
			Map param = new HashMap();
			Map map = new HashMap();
			// SI cargo consultoras por el archivo
			if (listaClientes != null) {
				// isArchivo = true;
				for (int i = 0; i < listaClientes.size(); i++) {
					map = (Map) listaClientes.get(i);

					if ((String) map.get("indicadorValido") != null) {
						if (((String) map.get("indicadorValido")).equals("1")) {
							criteria.put("codigoConsultora",
									(String) map.get("codigoCliente"));
							if(service.getExisteConsultoraChequear(criteria)==0){
								service.insertConsultoraChequear(criteria);
							}else{
								throw new Exception(this.getResourceMessage("mantenimientoPEDConsultoraChequeoForm.existeCodigo"));
							}
						}
					}
				}
			}
			if (codigoCliente.length() > 0) {
				criteria.put("codigoConsultora", codigoCliente);
				if(service.getExisteConsultoraChequear(criteria)==0){
					service.insertConsultoraChequear(criteria);
				}else{
					throw new Exception(this.getResourceMessage("mantenimientoPEDConsultoraChequeoForm.existeCodigo"));
				}
			}

			criteria.put("codigoConsultora", null);
			List resultado = service.getConsultoraChequear(criteria);
			this.pedResultadoConsultoraChequearList = resultado;
			this.dmPEDConsultoraChequeo = new DataTableModel(this.pedResultadoConsultoraChequearList);
			
			// limpiamos la grilla del archivo
			inicializar();
			
			mensaje = this.getResourceMessage("mantenimientoPEDConsultoraChequeoForm.insert");
			this.addInfo("Info : ", mensaje);

		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}
	
	/**
	 * Metodo para eliminar del datatable
	 */
	public void eliminar(ActionEvent evt) {
		try {
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			String mensaje = "";
			MantenimientoPEDConsultoraChequeoForm f = (MantenimientoPEDConsultoraChequeoForm) this.formBusqueda;
			MantenimientoPEDConsultoraChequeoService service = (MantenimientoPEDConsultoraChequeoService) getBean("spusicc.pedidos.mantenimientoPEDConsultoraChequeoService");
			
				if (StringUtils.equals(f.getEliminarTodo(), Constants.NUMERO_UNO)) {
					List list = this.pedResultadoConsultoraChequearList;
					for (int i = 0; i < list.size(); i++) {
						Map criteria = (Map) list.get(i);
						criteria.put("codigoPais", pais.getCodigo());
						service.deleteConsultoraChequear(criteria);
					}
					mensaje = this
							.getResourceMessage("mantenimientoPEDConsultoraChequeoForm.deleted");
					this.addInfo("Info : ", mensaje);
					return;
				} else {
					int tamanio = this.beanMantenimientoPEDConsultoraChequeo.length;
					//String[] items = new String[tamanio];
					for (int i = 0; i < tamanio; i++) {
						HashMap obj = (HashMap) this.beanMantenimientoPEDConsultoraChequeo[i];
						// listaDelete.add(obj);
	//					items[i] = obj.get("codigoTipoChequeo").toString() + "-"
	//							+ obj.get("codigoPeriodo").toString() + "-"
	//							+ obj.get("codigoConsultora").toString();
				
						Map criteria = new HashMap();
						criteria.put("codigoPais", pais.getCodigo());
						criteria.put("codigoTipoChequeo", obj.get("codigoTipoChequeo").toString());
						criteria.put("codigoPeriodo",obj.get("codigoPeriodo").toString());
						criteria.put("codigoConsultora",obj.get("codigoConsultora").toString());
						service.deleteConsultoraChequear(criteria);
					}
	
					this.pedResultadoConsultoraChequearList = new ArrayList();
					this.pedResultadoConsultoraChequearList = this
							.buscarPrincipal();
					this.dmPEDConsultoraChequeo = new DataTableModel(
							this.pedResultadoConsultoraChequearList);
					mensaje = this.getResourceMessage("mantenimientoPEDConsultoraChequeoForm.deleted");
					this.addInfo("Info : ", mensaje);
					return;
				}
				
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * Carga el archivo.
	 * @return
	 * @throws Exception
	 */
	public void loadfile() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'load Clientes from file' method");
		}
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoPEDConsultoraChequeoForm f = (MantenimientoPEDConsultoraChequeoForm) this.formBusqueda;
		MantenimientoRECIngresoAtencionesService service = (MantenimientoRECIngresoAtencionesService) getBean("spusicc.mantenimientoRECIngresoAtencionesService");
		GenericoService genericoService = (GenericoService) getBean("genericoService");
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
					this.longitudCampoClientes, '0');
			criteria.put("codigoConsultora", codigoConsultora);
			LabelValue bean = new LabelValue(codigoConsultora,
					service.getCodigoConsultora(criteria));

			if (bean.getValue() == null
					&& StringUtils.equals(indValidaCodConsultoraDocIdentidad,
							Constants.SI)) {
				criteria.put("documentoIdentidad", codigoConsultora);
				String codigoConsultoraPorDocIden = service
						.getCodigoConsultoraPorDocumentoIdentidad(criteria);

				if (codigoConsultoraPorDocIden == null) {
					bean = new LabelValue(
							codigoConsultora,
							service.getCodigoConsultoraPorDocumentoIdentidad(criteria));
				} else {
					bean = new LabelValue(
							codigoConsultoraPorDocIden,
							service.getCodigoConsultoraPorDocumentoIdentidad(criteria));
				}
			}
			
			boolean indicadorRepetido = validarRepetido(listaClientes, bean);
			if(!indicadorRepetido){
				listaClientes.add(bean);
			}
			if (bean.getValue() == null)
				cont++;
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
		if (cont != 0)
			f.setCodigosErradosFile("Existe(n) " + cont
					+ " codigo(s) errado(s)");
		criteria.put("numRegistros", numRegistros);
		List list2 = new ArrayList();
		list2 = service.getResumenCargaClienteList(criteria);
		this.stoLevantamientoErorresClientesList = list;
		this.stoResumenClientesList = list2;
		f.setCodigoPeriodo(f.getCodigoPeriodoActual());
		int tamanio1 = this.stoLevantamientoErorresClientesList.size();
		int tamanio2 = this.stoResumenClientesList.size();
		if (tamanio2 > 0 && tamanio1 > 0) {
			this.mostrarGrillas = true;
		}
	}
	
	public boolean validarRepetido(List listaClientes, LabelValue bean){
		boolean flag = false;
		
		if(listaClientes!=null && listaClientes.size()>0){
			for(int i=0;i<listaClientes.size();i++){
				if(StringUtils.equalsIgnoreCase(((LabelValue)listaClientes.get(i)).getLabel(), bean.getLabel())){
					flag = true;
					break;
				}	
			}
		}
		
		return flag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction
	 * #devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoPEDConsultoraChequeoForm form = new MantenimientoPEDConsultoraChequeoForm();
		return form;
	}

	public List buscarPrincipal() {
		try {

			MantenimientoPEDConsultoraChequeoForm f = (MantenimientoPEDConsultoraChequeoForm) this.formBusqueda;
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			MantenimientoPEDConsultoraChequeoService service = (MantenimientoPEDConsultoraChequeoService) getBean("spusicc.pedidos.mantenimientoPEDConsultoraChequeoService");
			List resultado = new ArrayList();
			Map criteria = new HashMap();
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("codigoTipoChequeo", f.getCodigoTipoChequeo());
			criteria.put("codigoPeriodo", f.getCodigoPeriodo());
			criteria.put("codigoConsultora", f.getCodigoConsultora());
			resultado = service.getConsultoraChequear(criteria);
			this.pedResultadoConsultoraChequearList = resultado;
			this.stoLineaDefecto = f.getLineaDefecto();
			this.dmPEDConsultoraChequeo = new DataTableModel(pedResultadoConsultoraChequearList);
			return this.pedResultadoConsultoraChequearList;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
			return null;
		}
	}

	/**
	 * Busca
	 * 
	 * @param evt
	 */
	public void buscar(ActionEvent evt) {
		try {
			this.pedResultadoConsultoraChequearList = new ArrayList();

			this.pedResultadoConsultoraChequearList = this.buscarPrincipal();
			this.dmPEDConsultoraChequeo = new DataTableModel(this.pedResultadoConsultoraChequearList);
			
			if(this.pedResultadoConsultoraChequearList == null || this.pedResultadoConsultoraChequearList.size()== 0)
				this.addWarn("Warning: ", this.getResourceMessage("errors.datos.fuentes.busqueda"));
			
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction
	 * #setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		return null;
	}

	/**
	 * Inicializando valores del form
	 */
	public void inicializar() {
		MantenimientoPEDConsultoraChequeoForm f = (MantenimientoPEDConsultoraChequeoForm) this.formBusqueda;
		this.stoLevantamientoErorresClientesList = new ArrayList();
		this.stoResumenClientesList = new ArrayList();
		f.setEliminarTodo(Constants.NUMERO_CERO);
		this.mostrarGrillas = false;
		this.mostrarDatatable = true;
		this.mostrarListaBusqueda = false;
		this.mostrarBotonBuscar = false;
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
		inicializar();
		MantenimientoPEDConsultoraChequeoForm f = (MantenimientoPEDConsultoraChequeoForm) this.formBusqueda;
		MantenimientoPEDConsultoraChequeoService service = (MantenimientoPEDConsultoraChequeoService) getBean("spusicc.pedidos.mantenimientoPEDConsultoraChequeoService");
		this.pedResultadoConsultoraChequearList = new ArrayList();
		f.setCodigoPais(pais.getCodigo());
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		this.pedTipoChequeoList = service.getTipoChequeoPais(criteria);
		/* obteniendo codigo de periodo actual */
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
		MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceFact
				.getControlFacturacionById(criteria);
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		f.setCodigoPeriodoActual(controlFacturacion.getCodigoPeriodo());
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		this.oidPais = reporteService.getOidString("getOidPaisByCodigoPais",
				criteria);
		f.setOidPais(String.valueOf(oidPais));
		// Seteo la longitud del codigo de consultora de acuerdo al pais
		ClienteUAGenerarService clienteService = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");
		this.longitudCampoClientes = clienteService
				.getTamanhoNumeroCliente(pais.getCodigo());
		f.setLongitudCodigoConsultora(String.valueOf(clienteService
				.getTamanhoNumeroCliente(pais.getCodigo())));
		f.setCodigosErradosFile("");
		f.setCodigoConsultora("");
		f.setLineaDefecto(Constants.PED_CONSULTORAS_CHEQUEAR_LINEA_DEFECTO);
		f.setLineaMaxima(Constants.PED_CONSULTORAS_CHEQUEAR_LINEA_MAXIMA);
		this.dmPEDConsultoraChequeo = new DataTableModel(this.pedResultadoConsultoraChequearList);
	}
	
	@Override
	public String setValidarConfirmar(String accion) {
		String mensaje = null;
		
		if(accion.equals("BotonGuardar"))
		{
			MantenimientoPEDConsultoraChequeoForm f = (MantenimientoPEDConsultoraChequeoForm) this.formBusqueda;
			if (this.stoLevantamientoErorresClientesList == null
					|| this.stoLevantamientoErorresClientesList.size() <= 0) {
				if (StringUtils.isBlank(f.getCodigoConsultora())) {
					mensaje = "Debe ingresar Codigo de Cliente";
					return mensaje;
				}
			}

			if (StringUtils.isNotBlank(f.getCodigosErradosFile())) {
				mensaje = "Antes de Guardar por favor corrija el/los Codigo(s) del Cliente";
				return mensaje;
			}
		}
		
		if(accion.equals("botonBorrar"))
		{
			if(this.beanMantenimientoPEDConsultoraChequeo == null || this.beanMantenimientoPEDConsultoraChequeo.length == 0)
			{
				mensaje = this.getResourceMessage("mensaje.error.seleccioneUnRegistro");
				return mensaje;
			}
		}
		
		return mensaje;
	}

	/**
	 * @return the pedTipoChequeoList
	 */
	public List getPedTipoChequeoList() {
		return pedTipoChequeoList;
	}

	/**
	 * @param pedTipoChequeoList
	 *            the pedTipoChequeoList to set
	 */
	public void setPedTipoChequeoList(List pedTipoChequeoList) {
		this.pedTipoChequeoList = pedTipoChequeoList;
	}

	/**
	 * @return the stoLevantamientoErorresClientesList
	 */
	public List getStoLevantamientoErorresClientesList() {
		return stoLevantamientoErorresClientesList;
	}

	/**
	 * @param stoLevantamientoErorresClientesList
	 *            the stoLevantamientoErorresClientesList to set
	 */
	public void setStoLevantamientoErorresClientesList(
			List stoLevantamientoErorresClientesList) {
		this.stoLevantamientoErorresClientesList = stoLevantamientoErorresClientesList;
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
	 * @return the pedResultadoConsultoraChequearList
	 */
	public List getPedResultadoConsultoraChequearList() {
		return pedResultadoConsultoraChequearList;
	}

	/**
	 * @param pedResultadoConsultoraChequearList
	 *            the pedResultadoConsultoraChequearList to set
	 */
	public void setPedResultadoConsultoraChequearList(
			List pedResultadoConsultoraChequearList) {
		this.pedResultadoConsultoraChequearList = pedResultadoConsultoraChequearList;
	}

	/**
	 * @return the mostrarGrillas
	 */
	public Boolean getMostrarGrillas() {
		return mostrarGrillas;
	}

	/**
	 * @param mostrarGrillas
	 *            the mostrarGrillas to set
	 */
	public void setMostrarGrillas(Boolean mostrarGrillas) {
		this.mostrarGrillas = mostrarGrillas;
	}

	/**
	 * @return the stoLineaDefecto
	 */
	public String getStoLineaDefecto() {
		return stoLineaDefecto;
	}

	/**
	 * @param stoLineaDefecto
	 *            the stoLineaDefecto to set
	 */
	public void setStoLineaDefecto(String stoLineaDefecto) {
		this.stoLineaDefecto = stoLineaDefecto;
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
	 * @return the mostrarPopupConsultora
	 */
	public boolean isMostrarPopupConsultora() {
		return mostrarPopupConsultora;
	}

	/**
	 * @param mostrarPopupConsultora
	 *            the mostrarPopupConsultora to set
	 */
	public void setMostrarPopupConsultora(boolean mostrarPopupConsultora) {
		this.mostrarPopupConsultora = mostrarPopupConsultora;
	}

	/**
	 * @return the busquedaConsultoraPOPUPSearchAction
	 */
	public BusquedaConsultoraPOPUPSearchAction getBusquedaConsultoraPOPUPSearchAction() {
		return busquedaConsultoraPOPUPSearchAction;
	}

	/**
	 * @param busquedaConsultoraPOPUPSearchAction
	 *            the busquedaConsultoraPOPUPSearchAction to set
	 */
	public void setBusquedaConsultoraPOPUPSearchAction(
			BusquedaConsultoraPOPUPSearchAction busquedaConsultoraPOPUPSearchAction) {
		this.busquedaConsultoraPOPUPSearchAction = busquedaConsultoraPOPUPSearchAction;
	}

	/**
	 * @return the popupConsultora
	 */
	public static String getPopupConsultora() {
		return POPUP_CONSULTORA;
	}

	/**
	 * @return the beanMantenimientoPEDConsultoraChequeo
	 */
	public Object[] getBeanMantenimientoPEDConsultoraChequeo() {
		return beanMantenimientoPEDConsultoraChequeo;
	}

	/**
	 * @param beanMantenimientoPEDConsultoraChequeo
	 *            the beanMantenimientoPEDConsultoraChequeo to set
	 */
	public void setBeanMantenimientoPEDConsultoraChequeo(
			Object[] beanMantenimientoPEDConsultoraChequeo) {
		this.beanMantenimientoPEDConsultoraChequeo = beanMantenimientoPEDConsultoraChequeo;
	}

	/**
	 * @return the mostrarDatatable
	 */
	public Boolean getMostrarDatatable() {
		return mostrarDatatable;
	}

	/**
	 * @param mostrarDatatable
	 *            the mostrarDatatable to set
	 */
	public void setMostrarDatatable(Boolean mostrarDatatable) {
		this.mostrarDatatable = mostrarDatatable;
	}

	/**
	 * @return the oidPais
	 */
	public String getOidPais() {
		return oidPais;
	}

	/**
	 * @param oidPais
	 *            the oidPais to set
	 */
	public void setOidPais(String oidPais) {
		this.oidPais = oidPais;
	}

	/**
	 * @return the dmPEDConsultoraChequeo
	 */
	public DataTableModel getDmPEDConsultoraChequeo() {
		return dmPEDConsultoraChequeo;
	}

	/**
	 * @param dmPEDConsultoraChequeo
	 *            the dmPEDConsultoraChequeo to set
	 */
	public void setDmPEDConsultoraChequeo(DataTableModel dmPEDConsultoraChequeo) {
		this.dmPEDConsultoraChequeo = dmPEDConsultoraChequeo;
	}

}