package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.ProductoAgregacion;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECDigitacionCDRService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaProductoSearchAction;
import biz.belcorp.ssicc.web.spusicc.emprendedoras.form.MantenimientoEMPEmprendedoraForm;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.MantenimientoRECRecepcionCDRForm;

@SessionScoped
@ManagedBean
@SuppressWarnings({ "unchecked", "rawtypes" })
public class MantenimientoRECRecepcionCDRAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 7446674821680736907L;

	private List recBusquedaIngresoAtencionesList;
	private List recProcesarIngresoAtencionesList;
	private List recClientesIngresoAtencionesList;
	private List listResultConsultoras;
	private List recListaAtencionesMasivasList;
	private List recRecepcionCDRList;

	private LabelValue[] recTipoOperacionList;
	private String numeroLote = "";

	private String attachment;
	private String botonAdicional = "";
	private String mostrarTabla = "";
	
	@ManagedProperty(value="#{busquedaProductoSearchAction}")
	private BusquedaProductoSearchAction busquedaProductoSearchAction;
	private boolean mostrarPopupBuscar= false;
	private static final String POPUP_BUSCAR_PRODUTOS = "RECEPCIONCDR";
	
	private String oidCabeReclamo = null;
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoRECRecepcionCDRForm z = new MantenimientoRECRecepcionCDRForm();
		return z;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'find' method");
		}

		try {
			this.mostrarErrorNoExistenRegistroBusqueda= false;
			MantenimientoRECDigitacionCDRService service = (MantenimientoRECDigitacionCDRService) getBean("spusicc.mantenimientoRECDigitacionCDRService");
			MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
			MantenimientoRECRecepcionCDRForm f = (MantenimientoRECRecepcionCDRForm) this.formBusqueda;

			f.setFlagMostrarInsertar(false);
			f.setFlagMostrarLista(false);

			Map criteria = new HashMap();
			criteria.put("codigoPais", f.getCodigoPais());
			criteria.put("numeroCDR", f.getNumeroCDR());

			Map resultado = (Map) service
					.getValidacionInicialRecepcionCDR(criteria);
			BigDecimal contador = (BigDecimal) resultado.get("varcontrcr");
			if (contador.longValue() == 0) {
				this.addError("Error: ", this.getResourceMessage("mantenimientoRECRecepcionCDRForm.error.numeroCDRNoExiste"));
				return null;

			}
			if (contador.longValue() > 1) {
				this.addError("Error: ", this.getResourceMessage("mantenimientoRECRecepcionCDRForm.error.numeroCDRDuplicado"));
				return null;

			}

			Map resultado2 = (HashMap) service
					.getValidacionRecepcionCDR(resultado);
			contador = (BigDecimal) resultado2.get("varcontrcd");
			String varindorig = (String) resultado2.get("varindorig");
			String indicadorEstado = (String) resultado2.get("varindesta");
			String usuIngreec = (String) resultado2.get("varusuingrrec");

			if (contador.longValue() > 0 && indicadorEstado.equals("1")) {
				this.addError("Error: ", this.getResourceMessage("mantenimientoRECRecepcionCDRForm.error.CDREnviadoSAP"));
				return null;
			}

			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			if (contador.longValue() > 0 && indicadorEstado.equals("0")
					&& !usuIngreec.equals(usuario.getLogin())) {
				this.addError( "Error: ", this.getResourceMessage("mantenimientoRECRecepcionCDRForm.error.CDRUsuarioIncorrecto"));
				return null;

			}

			/* Cargando información */
			BigDecimal oidCliente = (BigDecimal) resultado.get("varoidclie");
			BigDecimal valNumeSoli = (BigDecimal) resultado
					.get("varvalnumesoli");
			BigDecimal oidPeriodoPedido = (BigDecimal) resultado
					.get("varoidperiped");
			BigDecimal oidPeriodoRecepcion = (BigDecimal) resultado
					.get("varoidperirecl");
			BigDecimal oidPeriodoReclamo = (BigDecimal) resultado
					.get("varoidperirecl");
			BigDecimal oidCabeReclamo = (BigDecimal) resultado
					.get("varoidcaberecl");
			BigDecimal numeroReclamo = (BigDecimal) resultado.get("varnumrecl");

			String codigoPeriodoPedido = clienteService
					.getCodigoPeriodoByOidPeriodo(oidPeriodoPedido.toString());
			String codigoPeriodoRecepcion = clienteService
					.getCodigoPeriodoByOidPeriodo(oidPeriodoRecepcion
							.toString());

			Map criteriaCliente = new HashMap();
			criteriaCliente.put("oidCliente", oidCliente.longValue());
			Map resultadoCliente = (HashMap) service
					.getOidDatosCliente(criteriaCliente);
			String codigoConsultora = (String) resultadoCliente
					.get("codigoCliente");
			String mombreCliente = (String) resultadoCliente
					.get("nombreCliente");
			String descripcionRegion = (String) resultadoCliente
					.get("descripcionRegion");
			String descripcionZona = (String) resultadoCliente
					.get("descripcionZona");

			f.setNumeroPedido(valNumeSoli.toString());
			f.setCodigoConsultora(codigoConsultora);
			f.setNombreConsultora(mombreCliente);
			f.setDescripcionRegion(descripcionRegion);
			f.setDescripcionZona(descripcionZona);
			f.setCodigoPeriodoPedido(codigoPeriodoPedido);
			f.setCodigoPeriodoRecepcion(codigoPeriodoRecepcion);
			f.setCodigoPeriodoAtencion(codigoPeriodoRecepcion);
			f.setOidCabeReclamo(oidCabeReclamo.toString());
			//Se obtiene el oid de la cabecera, el cual se utilizara al insertar una fila en la grilla
			this.oidCabeReclamo = f.getOidCabeReclamo();
			f.setNumeroReclamo(numeroReclamo.toString());
			f.setContrcd(contador.toString());
			f.setIndicadorEstado("0");
			f.setOidPeriodoPedido(oidPeriodoPedido.toString());
			f.setOidPeriodoReclamo(oidPeriodoReclamo.toString());
			f.setIndicadorOrigen(varindorig);

			List listaResultado = new ArrayList();
			if (contador.longValue() == 1) {
				listaResultado = service.getListaRecepcionCDRDetalle(resultado);
			} else {
				Integer varanul = (Integer) service
						.getValidacionRecepcionCDRAnuladoDetalle(resultado);
				Integer varcanlin = (Integer) service
						.getValidacionRecepcionCDRDetalle(resultado);

				String indicadorOrigen = "C";
				if (varanul.intValue() == 1) {
					indicadorOrigen = "A";
				}
				f.setIndicadorOrigen(indicadorOrigen);

				if (varcanlin.intValue() == 0) {
					this.addError("Error: ", this.getResourceMessage("mantenimientoRECRecepcionCDRForm.error.CDRnotieneProductos"));
					return null;
				}
				listaResultado = service.getListaRecepcionCDRReclamos(resultado);
			}
			this.recRecepcionCDRList = listaResultado;
			this.mostrarErrorNoExistenRegistroBusqueda= true;
			if (listaResultado != null && listaResultado.size() > 0){
				f.setFlagMostrarLista(true);
			}
				

			if (f.getIndicadorOrigen().equals("C"))
				f.setFlagMostrarInsertar(true);
			f.setContadorRegistros(listaResultado.size());
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));

		}

		return this.recRecepcionCDRList;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		MantenimientoRECRecepcionCDRForm f = (MantenimientoRECRecepcionCDRForm) this.formBusqueda;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		MantenimientoRECDigitacionCDRService service = (MantenimientoRECDigitacionCDRService) getBean("spusicc.mantenimientoRECDigitacionCDRService");
		 
//		Setear en el form la lista
		String[] listaCodigoCUV = new String [listaBusqueda.size()];
		Long[] listaOidProducto = new Long [listaBusqueda.size()]; 
		String[] listaCodigoSAP = new String [listaBusqueda.size()]; 
		Long[] listaUnidRecibidas = new Long [listaBusqueda.size()]; 
		Long[] listaUnidDisponible = new Long [listaBusqueda.size()]; 
		Long[] listaUnidDestruccion = new Long [listaBusqueda.size()]; 
		Long[] listaUnidAprovec = new Long [listaBusqueda.size()]; 
		String[] listaIndicadorExistencia = new String [listaBusqueda.size()];
		Long[] listaOidOperReclamo = new Long [listaBusqueda.size()]; 
		Long[] listaOidLineaOperReclamo = new Long [listaBusqueda.size()]; 
		String[] listaIndicadorBorrado = new String [listaBusqueda.size()]; 
		
		for (int i = 0; i < listaBusqueda.size(); i++) {
			Map lista = (HashMap) this.listaBusqueda.get(i);
			if(lista.get("codigoVenta") == null)
				lista.put("codigoVenta", "");
			
			listaCodigoCUV[i] = lista.get("codigoVenta").toString();
			listaOidProducto[i] = Long.parseLong(lista.get("oidProducto").toString());
			listaCodigoSAP[i] = lista.get("codigoSAP").toString();
			listaUnidRecibidas[i] = Long.parseLong(lista.get("unidadesRecibidas").toString());
			listaUnidDisponible[i] = Long.parseLong(lista.get("unidadesDisponibles").toString());
			listaUnidDestruccion[i] = Long.parseLong(lista.get("unidadesDestruccion").toString());
			listaUnidAprovec[i] = Long.parseLong(lista.get("unidadesAprovec").toString());
			listaIndicadorExistencia[i] = lista.get("indicadorExistencia").toString();
			listaOidOperReclamo[i] = Long.parseLong(lista.get("oidOperReclamo").toString());
			listaOidLineaOperReclamo[i] = Long.parseLong(lista.get("oidLineaOperReclamo").toString());
			listaIndicadorBorrado[i] = lista.get("indicadorBorrado").toString();	
			}
		
		f.setListaCodigoCUV(listaCodigoCUV);
		f.setListaOidProducto(listaOidProducto);
		f.setListaCodigoSAP(listaCodigoSAP);
		f.setListaUnidRecibidas(listaUnidRecibidas);
		f.setListaUnidDisponible(listaUnidDisponible);
		f.setListaUnidDestruccion(listaUnidDestruccion);
		f.setListaUnidAprovec(listaUnidAprovec);
		f.setListaIndicadorExistencia(listaIndicadorExistencia);
		f.setListaOidOperReclamo(listaOidOperReclamo); 
		f.setListaOidLineaOperReclamo(listaOidLineaOperReclamo);
		f.setListaIndicadorBorrado(listaIndicadorBorrado);
		
		
		Map criteria = BeanUtils.describe(f);
		criteria.put("listaCodigoCUV", f.getListaCodigoCUV());
		criteria.put("listaOidProducto", f.getListaOidProducto());
		criteria.put("listaCodigoSAP", f.getListaCodigoSAP());
		criteria.put("listaUnidRecibidas", f.getListaUnidRecibidas());
		criteria.put("listaUnidDisponible", f.getListaUnidDisponible());
		criteria.put("listaUnidDestruccion", f.getListaUnidDestruccion());
		criteria.put("listaUnidAprovec", f.getListaUnidAprovec());
		criteria.put("listaIndicadorExistencia", f.getListaIndicadorExistencia());
		criteria.put("listaOidOperReclamo", f.getListaOidOperReclamo()); 
		criteria.put("listaOidLineaOperReclamo", f.getListaOidLineaOperReclamo());
		criteria.put("listaIndicadorBorrado", f.getListaIndicadorBorrado());

		criteria.put("usuario", usuario);
		criteria.put("codigoUsuario", usuario.getLogin());
		service.executeInsertUpdateRecepcionCDR(criteria);

		this.listaBusqueda = new ArrayList();
		this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
		f.setFlagMostrarInsertar(false);
		f.setFlagMostrarLista(false);
		f.setIndicadorOrigen("");
		f.setNumeroCDR("");
		f.setCodigoConsultora("");
		f.setNombreConsultora("");
		f.setCodigoRegion("");
		f.setDescripcionRegion("");
		f.setCodigoZona("");
		f.setDescripcionZona("");
		f.setCodigoPeriodoRecepcion("");
		f.setNumeroPedido("");
		f.setCodigoPeriodoPedido("");
		f.setCodigoPeriodoAtencion("");
		return true;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		// TODO Auto-generated method stub
		return "mantenimientoRECRecepcionCDRForm.msj.registrar";
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setValidarMantenimiento()
	 */
	@Override
	public String setValidarMantenimiento() {		
		String mensaje = null;
		
		for (int i = 0; i < this.listaBusqueda.size(); i++) {
			Map lista = (HashMap) this.listaBusqueda.get(i);
			
			if (StringUtils.isBlank(lista.get("unidadesDisponibles").toString())|| 
				StringUtils.isBlank(lista.get("unidadesDestruccion").toString())|| 
				StringUtils.isBlank(lista.get("unidadesAprovec").toString())) {				
				return this.getResourceMessage("mantenimientoRECRecepcionCDRForm.error.valores.vacios");				
			}
			
			String indicadorExistencia = lista.get("indicadorExistencia").toString();
			int unidDisponible = Integer.parseInt(lista.get("unidadesDisponibles").toString());
			int unidDestruccion = Integer.parseInt(lista.get("unidadesDestruccion").toString());
			int unidAprovec = Integer.parseInt(lista.get("unidadesAprovec").toString());
//			Evalua suma de los indicadorExistencia que son = 0, sean mayor a 0
			if(StringUtils.equals(indicadorExistencia, Constants.NUMERO_CERO)){
				int suma = unidDisponible+ unidDestruccion+ unidAprovec;
				int registro = i + 1;
				if(suma<=0){
					return this.getResourceMessage("mantenimientoRECRecepcionCDRForm.registro.error.sumaCantidades", new Object[]{registro});					
				}			
			}			
		}			
		return mensaje;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {

		this.mostrarBotonBuscar = true;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonSave = false;
		this.mostrarBotonSalir = false;
		this.mostrarBotonConsultar = false;
		this.mostrarListaBusqueda = false;
		this.activarHotkeyEstandar = false;
		this.invocarFindLuegoGrabar= false;
		
		this.listaBusqueda = new ArrayList();
		this.datatableBusqueda = new DataTableModel(this.listaBusqueda);

		log.debug("Inicio view ");
		try {
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			MantenimientoRECRecepcionCDRForm f = (MantenimientoRECRecepcionCDRForm) this.formBusqueda;
			f.setCodigoPais(pais.getCodigo());
			f.setFlagMostrarInsertar(false);
			f.setFlagMostrarLista(false);
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * @throws Exception
	 */
	public void paginado() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'paginado' method");
		}

		try {
			List busquedaList = this.recListaAtencionesMasivasList;
			this.recListaAtencionesMasivasList = busquedaList;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * @param nombreArchivo
	 * @return
	 * @throws Exception
	 */
	private String obtenerExtensionArchivo(String nombreArchivo) {
		try {
			return nombreArchivo.substring(nombreArchivo.length() - 4);

		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setInvocarPopup(java.lang.String)
	 */
	@Override
	protected void setInvocarPopup(String accion) {
		this.mostrarProcesoBatch = false;
		
		if (accion.equals(this.POPUP_BUSCAR_PRODUTOS)) {
			this.mostrarPopupBuscar = true;
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setAceptarPopup(javax.faces.event.ActionEvent, java.lang.String)
	 */
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Finish 'PopupHipCliente' method");
		}
		if (accion.equals(this.POPUP_BUSCAR_PRODUTOS)) {
			this.busquedaProductoSearchAction.verificarRegistro(event);
			Map seleccionado = (HashMap) this.busquedaProductoSearchAction.getBeanRegistroSeleccionado();
			MantenimientoRECRecepcionCDRForm f = (MantenimientoRECRecepcionCDRForm) this.formBusqueda;
			f.setCodigoSap(seleccionado.get("codigoSap").toString());
			
			this.mostrarPopupBuscar = false;
			this.busquedaProductoSearchAction.setListaBusqueda(null);
			this.busquedaProductoSearchAction.setDatatableBusqueda(null);
			this.setBeanRegistroSeleccionado(null);
			this.busquedaProductoSearchAction.limpiarSalir();
			
			this.getRequestContext().execute("PrimeFaces.focus('codigoSap')");
		}
	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setSalirPopup()
	 */
	@Override
	protected void setSalirPopup() {
		
		this.mostrarPopupBuscar = false;
		this.busquedaProductoSearchAction.setListaBusqueda(null);
		this.busquedaProductoSearchAction.setDatatableBusqueda(null);
		this.setBeanRegistroSeleccionado(null);
		this.busquedaProductoSearchAction.limpiarSalir();
//		this.busquedaConsultorasAction.setBeanRegistroSeleccionado(null);
	}

	
	
	/**
	 * Valida existencia de Codigos Sap, y que no
	 * sean repetidos. Al estar correctamente 
	 * lo agrega en una fila de la grilla 
	 * 
	 */
	public void validarProductoRepetido(){
		
		log.debug("Enter methos - validarProductoRepetido");
		
		MantenimientoRECRecepcionCDRForm f = (MantenimientoRECRecepcionCDRForm) this.formBusqueda;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		String valor = f.getCodigoSap();
		
		try {
			if(StringUtils.isNotBlank(valor)){
//				Valida si producto existe en la lista
				for(int i=0; i< this.listaBusqueda.size(); i++) {
					Map lista = (HashMap) this.listaBusqueda.get(i);
					String codigo = lista.get("codigoSAP").toString();
					
		  			if (StringUtils.equals(codigo, valor)) {
		  				this.addError("Error: ", this.getResourceMessage("mantenimientoRECRecepcionCDRForm.error.ProductoExistente"));
		 				return;
		 			}
		 		}
				
//				Valida existencia de producto
				ProductoAgregacion data = ajax.getDevuelveProductoRecepcionCDR(valor);
				if (StringUtils.equals(data.getCodigoProducto(), "-1")) {
					this.addError("Error: ", this.getResourceMessage("mantenimientoRECRecepcionCDRForm.error.ProductoNoExiste"));
					return;
				}
				
//				Inserta registro en la grilla
				this.insertarRegistro(data.getId(), data.getCodigoProducto(), data.getDescripcionProducto());		
			}
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}		
	}
	
	/**
	 * Inserta fila
	 * 
	 * @param id
	 * @param codigoProducto
	 * @param descripcionProducto
	 */
	private void insertarRegistro(String id, String codigoProducto, String descripcionProducto){
		
		try {
			
			Map registro = new HashMap();
			registro.put("codigoVenta", "");
			registro.put("codigoSAP", codigoProducto);
			registro.put("descripcionProducto", descripcionProducto);
			registro.put("oidCabeReclamo", this.oidCabeReclamo);
			registro.put("oidProducto", id);
			registro.put("unidadesRecibidas", "0");
			registro.put("unidadesDisponibles", "0");
			registro.put("unidadesDestruccion", "0");
			registro.put("unidadesAprovec", "0");
			registro.put("indicadorExistencia", "0");
			registro.put("oidCorrelativo", "0");
			registro.put("oidOperReclamo", "0");
			registro.put("oidLineaOperReclamo", "0");
			registro.put("indicadorBorrado", "N");
						
			this.listaBusqueda.add(registro);
			this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
			
		} catch (Exception e) {
			
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	
	
	/**
	 * Elimina fila al hacer click en checkbox
	 * 
	 */
	public void deletefila(){
		
		log.debug("Enter method - deletefila");
		
		try {
			
			 String indice = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("indice");			
			 this.listaBusqueda.remove(Integer.parseInt(indice));
			 this.datatableBusqueda = new DataTableModel(this.listaBusqueda);			
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		
	}
	
	/**
	 * @return
	 */
	public List getRecBusquedaIngresoAtencionesList() {
		return recBusquedaIngresoAtencionesList;
	}

	/**
	 * @param recBusquedaIngresoAtencionesList
	 */
	public void setRecBusquedaIngresoAtencionesList(
			List recBusquedaIngresoAtencionesList) {
		this.recBusquedaIngresoAtencionesList = recBusquedaIngresoAtencionesList;
	}

	/**
	 * @return
	 */
	public List getRecProcesarIngresoAtencionesList() {
		return recProcesarIngresoAtencionesList;
	}

	/**
	 * @param recProcesarIngresoAtencionesList
	 */
	public void setRecProcesarIngresoAtencionesList(
			List recProcesarIngresoAtencionesList) {
		this.recProcesarIngresoAtencionesList = recProcesarIngresoAtencionesList;
	}

	/**
	 * @return
	 */
	public List getRecClientesIngresoAtencionesList() {
		return recClientesIngresoAtencionesList;
	}

	/**
	 * @param recClientesIngresoAtencionesList
	 */
	/**
	 * @param recClientesIngresoAtencionesList
	 */
	public void setRecClientesIngresoAtencionesList(
			List recClientesIngresoAtencionesList) {
		this.recClientesIngresoAtencionesList = recClientesIngresoAtencionesList;
	}

	/**
	 * @return
	 */
	public List getListResultConsultoras() {
		return listResultConsultoras;
	}

	/**
	 * @param listResultConsultoras
	 */
	public void setListResultConsultoras(List listResultConsultoras) {
		this.listResultConsultoras = listResultConsultoras;
	}

	/**
	 * @return
	 */
	/**
	 * @return
	 */
	public List getRecListaAtencionesMasivasList() {
		return recListaAtencionesMasivasList;
	}

	/**
	 * @param recListaAtencionesMasivasList
	 */
	public void setRecListaAtencionesMasivasList(
			List recListaAtencionesMasivasList) {
		this.recListaAtencionesMasivasList = recListaAtencionesMasivasList;
	}

	/**
	 * @return
	 */
	public LabelValue[] getRecTipoOperacionList() {
		return recTipoOperacionList;
	}

	/**
	 * @param recTipoOperacionList
	 */
	public void setRecTipoOperacionList(LabelValue[] recTipoOperacionList) {
		this.recTipoOperacionList = recTipoOperacionList;
	}

	/**
	 * @return
	 */
	public String getNumeroLote() {
		return numeroLote;
	}

	/**
	 * @param numeroLote
	 */
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
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
	public String getBotonAdicional() {
		return botonAdicional;
	}

	/**
	 * @param botonAdicional
	 */
	public void setBotonAdicional(String botonAdicional) {
		this.botonAdicional = botonAdicional;
	}

	/**
	 * @return
	 */
	public String getMostrarTabla() {
		return mostrarTabla;
	}

	/**
	 * @param mostrarTabla
	 */
	public void setMostrarTabla(String mostrarTabla) {
		this.mostrarTabla = mostrarTabla;
	}

	/**
	 * @return
	 */
	private List getRecRecepcionCDRList() {
		return recRecepcionCDRList;
	}

	/**
	 * @param recRecepcionCDRList
	 */
	private void setRecRecepcionCDRList(List recRecepcionCDRList) {
		this.recRecepcionCDRList = recRecepcionCDRList;
	}

	/**
	 * @return the mostrarPopupBuscar
	 */
	public boolean isMostrarPopupBuscar() {
		return mostrarPopupBuscar;
	}

	/**
	 * @param mostrarPopupBuscar the mostrarPopupBuscar to set
	 */
	public void setMostrarPopupBuscar(boolean mostrarPopupBuscar) {
		this.mostrarPopupBuscar = mostrarPopupBuscar;
	}

	/**
	 * @return the busquedaProductoSearchAction
	 */
	public BusquedaProductoSearchAction getBusquedaProductoSearchAction() {
		return busquedaProductoSearchAction;
	}

	/**
	 * @param busquedaProductoSearchAction the busquedaProductoSearchAction to set
	 */
	public void setBusquedaProductoSearchAction(
			BusquedaProductoSearchAction busquedaProductoSearchAction) {
		this.busquedaProductoSearchAction = busquedaProductoSearchAction;
	}

	/**
	 * @return the oidCabeReclamo
	 */
	public String getOidCabeReclamo() {
		return oidCabeReclamo;
	}

	/**
	 * @param oidCabeReclamo the oidCabeReclamo to set
	 */
	public void setOidCabeReclamo(String oidCabeReclamo) {
		this.oidCabeReclamo = oidCabeReclamo;
	}

}