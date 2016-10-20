package biz.belcorp.ssicc.web.spusicc.action;

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

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.LookupService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.MantenimientoPRECambioCodigoVentaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.form.InterfazPRECambioCodigoVentaModificaOfertaForm;

@SessionScoped
@ManagedBean
public class InterfazPRECambioCodigoVentaModificaOfertaAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -7275306634664972600L;
	private String codigoInterfaz;
	private String numSecUsuario;
	private String accionDinamica;
	private List ssiccCatalogoList;
	private List preFormaPagoList;
	private List preTipoClienteList;
	private List preEstatusList;
	private List allPaises;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	private LabelValue[] subTipoCliente = {};
	private LabelValue[] tipoClasificacion = {};
	private LabelValue[] clasificacion = {};
	private boolean indicadorCatalogo;
	private boolean indicadorFormaPago;
	private boolean indicadorResecuenciar;
	
	private String oidOferta;
	private Map columnaSeleccionada = new HashMap();
	private List preVentaExclusivaList;
	private List preVentaExclusivaGlobalList;
	private DataTableModel dataTablePreVentaExclusiva;
	
	private String indexVentaExclusiva;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoPRECambioCodigoVentaList";
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
		InterfazPRECambioCodigoVentaModificaOfertaForm form = new InterfazPRECambioCodigoVentaModificaOfertaForm();
		return form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction
	 * #setValidarConfirmar(java.lang.String)
	 */
	public String setValidarConfirmar(String accion) {
		InterfazPRECambioCodigoVentaModificaOfertaForm f = (InterfazPRECambioCodigoVentaModificaOfertaForm) this.formBusqueda;
		accionDinamica = accion;
		if (accion.equals("GUARDAR")) {

		} else if (accion.equals("ASIGNAR")) {

//			if (StringUtils.isBlank(f.getOidTipoCliente())
//					&& StringUtils.isBlank(f.getOidEstatus())
//					&& StringUtils.isBlank(f.getCodigoRegion())) {
//				return "Debe de ingresar: el Tipo de Cliente ó el Estatus ó la Región";
//			}
		} else if (accion.equals("LIMPIAR")) {
			
		} else if (accion.equals("AGREGAR_VENT_EXCLU")) {
			
			if (StringUtils.isBlank(f.getOidTipoCliente())
					&& StringUtils.isBlank(f.getOidEstatus())
					&& StringUtils.isBlank(f.getCodigoRegion())) {
				return "Debe de ingresar: el Tipo de Cliente ó el Estatus ó la Región";
			}
			
		} else if (accion.split(";")[0].equals("ELIMINAR_VENT_EXCLU")) {
//			if(this.columnaSeleccionada == null){
//				return "Debe seleccionar una Venta Exclusiva de la lista";
//			}
			
			indexVentaExclusiva = accion.split(";")[1];
		} else if (accion.equals("COPIAR")) {
			if (StringUtils.isBlank(f.getCodigoPeriodo()))
				return "Campaña es un campo requerido";
		} else if (accion.equals("EXECUTE")) {

		}
		return null;
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
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method");
		}

		InterfazPRECambioCodigoVentaModificaOfertaForm f = (InterfazPRECambioCodigoVentaModificaOfertaForm) this.formBusqueda;
		if (this.accionDinamica.equals("GUARDAR"))
			grabar(f);
		else if (this.accionDinamica.equals("ASIGNAR"))
			asignar(f);
		else if (this.accionDinamica.equals("LIMPIAR"))
			limpiara();
		else if (this.accionDinamica.equals("COPIAR")) {
			if (this.indicadorResecuenciar)
				f.setIndicadorResecuenciar("S");
			else
				f.setIndicadorResecuenciar("N");
			copiar(f);
		} else if (this.accionDinamica.equals("EXECUTE")) {
			execute(f);
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction
	 * #devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		if (this.accionDinamica.equals("GUARDAR"))
			return "interfazPRECambioCodigoVentaModificaOfertaForm.updated";
		else if (this.accionDinamica.equals("ASIGNAR"))
			return "interfazPRECambioCodigoVentaModificaOfertaForm.asignar";
		else if (this.accionDinamica.equals("LIMPIAR"))
			return "interfazPRECambioCodigoVentaModificaOfertaForm.limpiar";
		else if (this.accionDinamica.equals("COPIAR"))
			return "interfazPRECambioCodigoVentaModificaOfertaForm.copiar";
		else
			return "Se ejecutó satisfactoriamente";

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		return null;
	}
	
	/**
	 * Inicializando valores cada vez que se hace clic en el boton
	 */
	public void inicializando(){
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		InterfazPRECambioCodigoVentaModificaOfertaForm f = (InterfazPRECambioCodigoVentaModificaOfertaForm) this.formBusqueda;
		MantenimientoPRECambioCodigoVentaService service = (MantenimientoPRECambioCodigoVentaService) getBean("spusicc.mantenimientoPRECambioCodigoVentaService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		AjaxService ajax = (AjaxService) getBean("ajaxService");

		Map params = new HashMap();
		params.put("codigoPais", pais.getCodigo());

		this.ssiccCatalogoList = reporteService.getListaGenerico("getCatalogoProductos", null);
		this.preFormaPagoList = service.getFormaPagoList(params);
		this.preTipoClienteList = service.getTipoClienteList(params);
		this.preEstatusList = service.getEstatusClienteList(params);
		this.siccRegionList = ajax.getRegionesByPais(pais.getCodigo());
		this.siccZonaList = null;
		LookupService service2 = (LookupService) getBean("lookupService");
		this.allPaises = service2.getAllPaises();

		f.setCodigoCatalogo(null);
		f.setCodigoFormaPago(null);
		f.setIndicadorCatalogo(Constants.NO);
		f.setIndicadorFormaPago(Constants.NO);
		f.setIndicadorResecuenciar(Constants.NO);
		f.setCodigoPeriodo(null);
		f.setCodigoRegion(null);
		f.setCodigoZona(null);
		f.setOidTipoCliente(null);
		f.setOidSubTipoCliente(null);
		f.setOidTipoClasificacion(null);
		f.setOidClasificacion(null);
		f.setOidEstatus(null);
		
		params.put("oidOferta", this.oidOferta);
		this.preVentaExclusivaList = service.getVentaExclusivaOferta(params);
		this.preVentaExclusivaGlobalList = service.getVentaExclusivaOferta(params);
		this.dataTablePreVentaExclusiva = new DataTableModel(this.preVentaExclusivaList);
		
		this.salirGrabarPantallaPadre = true;
		this.mostrarBotonSave = false;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		
	}

	/**
	 * Metodo que modifica los datos catalogo y forma de pago de una oferta
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void grabar(InterfazPRECambioCodigoVentaModificaOfertaForm f) {

		MantenimientoPRECambioCodigoVentaService service = (MantenimientoPRECambioCodigoVentaService) getBean("spusicc.mantenimientoPRECambioCodigoVentaService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		try {
			Map criteria = new HashMap();
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("codigoUsuario", usuario.getLogin());

			// validar CheckBox
			if (this.indicadorCatalogo)
				f.setIndicadorCatalogo("S");
			else
				f.setIndicadorCatalogo("N");

			if (this.indicadorFormaPago)
				f.setIndicadorFormaPago("S");
			else
				f.setIndicadorFormaPago("N");

			String indCatalogo = f.getIndicadorCatalogo();
			String indFormaPago = f.getIndicadorFormaPago();

			if (indCatalogo.equals(Constants.SI)) {
				criteria.put("indicadorCatalogo", "S");
				criteria.put("codigoCatalogo", f.getCodigoCatalogo());
			} else {
				criteria.put("indicadorCatalogo", "N");
				criteria.put("codigoCatalogo", "");
			}

			if (indFormaPago.equals(Constants.SI)) {
				criteria.put("indicadorFormaPago", "S");
				criteria.put("codigoFormaPago", f.getCodigoFormaPago());
			} else {
				criteria.put("indicadorFormaPago", "N");
				criteria.put("codigoFormaPago", "");
			}

			criteria.put("numSecUsuario", this.numSecUsuario);

			service.executeModificarDatosOferta(criteria);

			MantenimientoPRECambioCodigoVentaSearchAction action = findManageBean("mantenimientoPRECambioCodigoVentaSearchAction");

			action.setModificaCUV(Constants.NUMERO_UNO);
			action.cargarPagina();
			action.iniciarAgregarCUV();
		} catch (Exception e) {
			this.addError("Error", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * Metodo que asigna datos a una Oferta
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void asignar(InterfazPRECambioCodigoVentaModificaOfertaForm f){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'asignar' method");
		}
		MantenimientoPRECambioCodigoVentaService service = (MantenimientoPRECambioCodigoVentaService) getBean("spusicc.mantenimientoPRECambioCodigoVentaService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		try {
			List listVentasExclu = this.preVentaExclusivaList;
			List listVentasExcluGlobal = this.preVentaExclusivaGlobalList;
			
			for (int i = 0; i < listVentasExclu.size(); i++) {
				Map mapVE = (Map) listVentasExclu.get(i);
				BeanUtils.copyProperties(f, listVentasExclu.get(i));
				
				Map criteria = new HashMap();
				criteria.put("codigoPais", pais.getCodigo());
				criteria.put("oidTipoCliente", f.getOidTipoCliente());
				criteria.put("oidSubTipoCliente", f.getOidSubTipoCliente());
				criteria.put("oidTipoClasificacion", f.getOidTipoClasificacion());
				criteria.put("oidClasificacion", f.getOidClasificacion());
				criteria.put("oidEstatus", f.getOidEstatus());
				criteria.put("codigoRegion", MapUtils.getString(mapVE, "codigoRegion"));
				criteria.put("codigoZona", MapUtils.getString(mapVE, "codigoZona"));
				criteria.put("numSecUsuario", numSecUsuario);

				if(StringUtils.equals(MapUtils.getString(mapVE, "estado"), "A")){
					service.executeInsertVentaExclusiva(criteria);
				}
			}
			
			for (int j = 0; j < listVentasExcluGlobal.size(); j++) {
				Map mapVEAEliminar = (Map) listVentasExcluGlobal.get(j);
				
				if(StringUtils.equals(MapUtils.getString(mapVEAEliminar, "estado"), "E")){
					service.deleteVentaExclusivaPorOidVentaExclusiva(mapVEAEliminar);
				}
			}
		} catch (Exception e) {
			this.addError("Error", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * Metodo que elimina los datos de una oferta
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void limpiara(){

		if (log.isDebugEnabled()) {
			log.debug("Entering 'limpiar' method");
		}
		try {
			MantenimientoPRECambioCodigoVentaService service = (MantenimientoPRECambioCodigoVentaService) getBean("spusicc.mantenimientoPRECambioCodigoVentaService");

			Map criteria = new HashMap();
			criteria.put("numSecUsuario", numSecUsuario);

			service.deleteVentaExclusiva(criteria);
		} catch (Exception e) {
			this.addError("Error", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * Metodo que copia una oferta
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void copiar(InterfazPRECambioCodigoVentaModificaOfertaForm f)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'copiar' method");
		}
		try {
			MantenimientoPRECambioCodigoVentaService service = (MantenimientoPRECambioCodigoVentaService) getBean("spusicc.mantenimientoPRECambioCodigoVentaService");

			Map criteria = new HashMap();
			criteria.put("codigoPeriodo", f.getCodigoPeriodo());

			String indResecuenciar = f.getIndicadorResecuenciar();

			if (indResecuenciar.equals(Constants.SI)) {
				criteria.put("indicadorResecuenciar", "S");
			} else {
				criteria.put("indicadorResecuenciar", "N");
			}

			criteria.put("numSecUsuario", numSecUsuario);
			service.executeCopiarOferta(criteria);
		} catch (Exception e) {
			this.addError("Error", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * @param f
	 */
	public void execute(InterfazPRECambioCodigoVentaModificaOfertaForm f) {
		try {
			InterfazPRECambioCodigoVentaModificaOfertaExecute action = findManageBean("interfazPRECambioCodigoVentaModificaOfertaExecute");
			action.setCodigoPaisExportar(f.getCodigoPaisExportar());
			action.executeInterfaz();
		} catch (Exception e) {
			this.addError("Error", this.obtieneMensajeErrorException(e));
		}
	}

	/**Carga la zona
	 * @param val
	 */
	public void loadZona(ValueChangeEvent val) {
		try {
			InterfazPRECambioCodigoVentaModificaOfertaForm f = (InterfazPRECambioCodigoVentaModificaOfertaForm) this.formBusqueda;
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			String valor = (String) val.getNewValue();
			String[] valor0 = new String[1];
			valor0[0] = valor;
			f.setCodigoZona(null);
			this.siccZonaList = ajax.getZonasMultipleByPaisMarcaCanalRegion(
					pais.getCodigo(), "T", "VD", valor0, "T");
		} catch (Exception e) {
			this.addError("Error", this.obtieneMensajeErrorException(e));
		}		
	}

	/**
	 * carga el subcliente tipo de clasificacion
	 * @param val
	 */
	public void loadSubClienteTipoClasificacionClasificacion(
			ValueChangeEvent val) {
		try {
			InterfazPRECambioCodigoVentaModificaOfertaForm f = (InterfazPRECambioCodigoVentaModificaOfertaForm) this.formBusqueda;
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			String valor = (String) val.getNewValue();

			this.subTipoCliente = ajax.getSubTipoClienteByOidTipoCliente(valor);
			this.subTipoCliente = actualizarLista(subTipoCliente);
			this.tipoClasificacion = ajax.getTipoClasificacionByOidSubTipoCliente(null);
			this.tipoClasificacion = actualizarLista(tipoClasificacion);
			this.clasificacion = ajax.getClasificacionByOidTipoClasificacion(null);
			this.clasificacion = actualizarLista(clasificacion);

			f.setOidSubTipoCliente(null);
			f.setOidTipoClasificacion(null);
			f.setOidClasificacion(null);
		} catch (Exception e) {
			this.addError("Error", this.obtieneMensajeErrorException(e));
		}
	}

	/**Carga el tipo de clasificacion de acuerdo a la clasificacion
	 * @param val
	 */
	public void loadTipoClasificacionClasificacion(ValueChangeEvent val) {
		try {
			InterfazPRECambioCodigoVentaModificaOfertaForm f = (InterfazPRECambioCodigoVentaModificaOfertaForm) this.formBusqueda;
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			String valor = (String) val.getNewValue();
			tipoClasificacion = ajax.getTipoClasificacionByOidSubTipoCliente(valor);
			tipoClasificacion = actualizarLista(tipoClasificacion);
			clasificacion = ajax.getClasificacionByOidTipoClasificacion(null);
			clasificacion = actualizarLista(clasificacion);

			f.setOidTipoClasificacion(null);
			f.setOidClasificacion(null);
		} catch (Exception e) {
			this.addError("Error", this.obtieneMensajeErrorException(e));
		}
	}

	/**Carga clasificaciones
	 * @param val
	 */
	public void loadClasificacion(ValueChangeEvent val) {
		try {
			InterfazPRECambioCodigoVentaModificaOfertaForm f = (InterfazPRECambioCodigoVentaModificaOfertaForm) this.formBusqueda;
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			String valor = (String) val.getNewValue();
			clasificacion = ajax.getClasificacionByOidTipoClasificacion(valor);
			clasificacion = actualizarLista(clasificacion);
			f.setOidClasificacion(null);
		} catch (Exception e) {
			this.addError("Error", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * @param lista
	 * @return
	 */
	public LabelValue[] actualizarLista(LabelValue[] lista) {
		LabelValue[] resultadoList = null;
		if (lista == null)
			resultadoList = new LabelValue[1];
		else
			resultadoList = new LabelValue[lista.length + 1];

		LabelValue option = new LabelValue();
		option.setLabel("Todos");
		option.setValue("");
		resultadoList[0] = option;
		if (lista != null)
			for (int i = 1; i < lista.length + 1; i++) {
				resultadoList[i] = lista[i - 1];
			}
		return resultadoList;
	}
	
	/**
	 * @param event
	 */
	public void agregar(ActionEvent event){
		log.debug("Enter method - agregar");
		
		try {
			InterfazPRECambioCodigoVentaModificaOfertaForm f = (InterfazPRECambioCodigoVentaModificaOfertaForm) this.formBusqueda;
			
			if (StringUtils.isBlank(f.getOidTipoCliente())
					&& StringUtils.isBlank(f.getOidEstatus())
					&& StringUtils.isBlank(f.getCodigoRegion())) {
				throw new Exception("Debe de ingresar: el Tipo de Cliente ó el Estatus ó la Región");
			}
			
			Map criteria = new HashMap(); 		
			criteria.put("oidVentaExclusiva", "");
			criteria.put("oidTipoCliente", f.getOidTipoCliente());
			criteria.put("oidSubTipoCliente", f.getOidSubTipoCliente());
			criteria.put("oidTipoClasificacion", f.getOidTipoClasificacion());
			criteria.put("oidClasificacion", f.getOidClasificacion());
			criteria.put("oidEstatus", f.getOidEstatus());
			criteria.put("codigoRegion", f.getCodigoRegion());
			criteria.put("codigoZona", f.getCodigoZona());
			
			String desTipoCliente = this.descripcionSimpleLista(f.getOidTipoCliente(), this.preTipoClienteList);
			String desSubTipoCliente = this.descripcionSimpleLista((f.getOidSubTipoCliente() == null ? "" : f.getOidSubTipoCliente()), this.subTipoCliente);
			String desTipoClasificacion = this.descripcionSimpleLista((f.getOidTipoClasificacion() == null ? "" : f.getOidTipoClasificacion()), this.tipoClasificacion);
			String desClasificacion = this.descripcionSimpleLista((f.getOidClasificacion() == null ? "" : f.getOidClasificacion()), this.clasificacion);
			String desEstatus = this.descripcionSimpleLista(f.getOidEstatus(), this.preEstatusList);
			String desRegion = this.descripcionSimpleLista(f.getCodigoRegion(), this.siccRegionList);
			String desZona = this.descripcionSimpleLista((f.getCodigoZona() == null ? "" : f.getCodigoZona()), this.siccZonaList);
					
			criteria.put("desTipoCliente", desTipoCliente);
			criteria.put("desSubTipoCliente", (StringUtils.equals(desSubTipoCliente, "Todos") ? "" : desSubTipoCliente));
			criteria.put("desTipoClasificacion", StringUtils.equals(desTipoClasificacion, "Todos") ? "" : desTipoClasificacion);
			criteria.put("desClasificacion", StringUtils.equals(desClasificacion, "Todos") ? "" : desClasificacion);
			criteria.put("desEstatus", desEstatus);
			criteria.put("desRegion", desRegion);
			criteria.put("desZona", StringUtils.equals(desZona, "Todos") ? "" : desZona);
			criteria.put("estado", "A");
						
			this.preVentaExclusivaList.add(criteria);
//			this.preVentaExclusivaGlobalList.add(criteria);
			this.dataTablePreVentaExclusiva = new DataTableModel(this.preVentaExclusivaList);
			
			f.setOidTipoCliente(null);
			f.setOidSubTipoCliente(null);
			f.setOidTipoClasificacion(null);
			f.setOidClasificacion(null);
			f.setOidEstatus(null);
			f.setCodigoRegion(null);
			f.setCodigoZona(null);
			
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		}
	}
	
	/**
	 * Metodo que elimina una venta exclusiva seleccionada de la lista
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void eliminarVentaExclusiva(ActionEvent event) {
		log.debug("Enter method - eliminarVentaExclusiva");
		
		try {
			InterfazPRECambioCodigoVentaModificaOfertaForm f = (InterfazPRECambioCodigoVentaModificaOfertaForm) this.formBusqueda;

//			Map seleccionado = this.columnaSeleccionada;
			Map seleccionado = (Map) this.preVentaExclusivaList.get(Integer.parseInt(this.indexVentaExclusiva));
			List listVentaExclusiva = this.preVentaExclusivaList;
			List listVentaExclusivaGlobal = this.preVentaExclusivaGlobalList;
			boolean eliminado = false;
			
			String oidVentaExclusivaSel = MapUtils.getString(seleccionado, "oidVentaExclusiva");

			if(StringUtils.isBlank(oidVentaExclusivaSel)){
				listVentaExclusiva.remove(Integer.parseInt(this.indexVentaExclusiva));
				
				eliminado = true;
			}else{
				for (int i = 0; i < listVentaExclusiva.size(); i++) {
					Map criteriaVenExc = (Map) listVentaExclusiva.get(i);
					
					if(StringUtils.equals(oidVentaExclusivaSel, MapUtils.getString(criteriaVenExc, "oidVentaExclusiva"))){
						listVentaExclusiva.remove(i);
						eliminado = true;
						
						for (int j = 0; j < listVentaExclusivaGlobal.size(); j++) {
							Map venExc = (Map) listVentaExclusivaGlobal.get(j);

							if(StringUtils.equals(oidVentaExclusivaSel, MapUtils.getString(venExc, "oidVentaExclusiva"))){
								venExc.put("estado", "E");
								break;
							}
						}
						
						break;
					}
				}
			}
			
			if(eliminado){
				this.preVentaExclusivaList = listVentaExclusiva;
				this.dataTablePreVentaExclusiva = new DataTableModel(this.preVentaExclusivaList);
				
				throw new Exception(this.getResourceMessage("mantenimientoPRECambioCodigoVentaSearchForm.deleted.venta.exclusiva"));
			}
		} catch (Exception e) {
			this.addError("Error", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * @return the codigoInterfaz
	 */
	public String getCodigoInterfaz() {
		return codigoInterfaz;
	}

	/**
	 * @param codigoInterfaz
	 *            the codigoInterfaz to set
	 */
	public void setCodigoInterfaz(String codigoInterfaz) {
		this.codigoInterfaz = codigoInterfaz;
	}

	/**
	 * @return the ssiccCatalogoList
	 */
	public List getSsiccCatalogoList() {
		return ssiccCatalogoList;
	}

	/**
	 * @param ssiccCatalogoList
	 *            the ssiccCatalogoList to set
	 */
	public void setSsiccCatalogoList(List ssiccCatalogoList) {
		this.ssiccCatalogoList = ssiccCatalogoList;
	}

	/**
	 * @return the preFormaPagoList
	 */
	public List getPreFormaPagoList() {
		return preFormaPagoList;
	}

	/**
	 * @param preFormaPagoList
	 *            the preFormaPagoList to set
	 */
	public void setPreFormaPagoList(List preFormaPagoList) {
		this.preFormaPagoList = preFormaPagoList;
	}

	/**
	 * @return the preTipoClienteList
	 */
	public List getPreTipoClienteList() {
		return preTipoClienteList;
	}

	/**
	 * @param preTipoClienteList
	 *            the preTipoClienteList to set
	 */
	public void setPreTipoClienteList(List preTipoClienteList) {
		this.preTipoClienteList = preTipoClienteList;
	}

	/**
	 * @return the preEstatusList
	 */
	public List getPreEstatusList() {
		return preEstatusList;
	}

	/**
	 * @param preEstatusList
	 *            the preEstatusList to set
	 */
	public void setPreEstatusList(List preEstatusList) {
		this.preEstatusList = preEstatusList;
	}

	/**
	 * @return the indicadorCatalogo
	 */
	public boolean isIndicadorCatalogo() {
		return indicadorCatalogo;
	}

	/**
	 * @param indicadorCatalogo
	 *            the indicadorCatalogo to set
	 */
	public void setIndicadorCatalogo(boolean indicadorCatalogo) {
		this.indicadorCatalogo = indicadorCatalogo;
	}

	/**
	 * @return the indicadorFormaPago
	 */
	public boolean isIndicadorFormaPago() {
		return indicadorFormaPago;
	}

	/**
	 * @param indicadorFormaPago
	 *            the indicadorFormaPago to set
	 */
	public void setIndicadorFormaPago(boolean indicadorFormaPago) {
		this.indicadorFormaPago = indicadorFormaPago;
	}

	/**
	 * @return the numSecUsuario
	 */
	public String getNumSecUsuario() {
		return numSecUsuario;
	}

	/**
	 * @param numSecUsuario
	 *            the numSecUsuario to set
	 */
	public void setNumSecUsuario(String numSecUsuario) {
		this.numSecUsuario = numSecUsuario;
	}

	/**
	 * @return the accionDinamica
	 */
	public String getAccionDinamica() {
		return accionDinamica;
	}

	/**
	 * @param accion
	 *            the accionDinamica to set
	 */
	public void setAccionDinamica(String accionDinamica) {
		this.accionDinamica = accionDinamica;
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
	 * @return the subTipoCliente
	 */
	public LabelValue[] getSubTipoCliente() {
		return subTipoCliente;
	}

	/**
	 * @param subTipoCliente
	 *            the subTipoCliente to set
	 */
	public void setSubTipoCliente(LabelValue[] subTipoCliente) {
		this.subTipoCliente = subTipoCliente;
	}

	/**
	 * @return the tipoClasificacion
	 */
	public LabelValue[] getTipoClasificacion() {
		return tipoClasificacion;
	}

	/**
	 * @param tipoClasificacion
	 *            the tipoClasificacion to set
	 */
	public void setTipoClasificacion(LabelValue[] tipoClasificacion) {
		this.tipoClasificacion = tipoClasificacion;
	}

	/**
	 * @return the clasificacion
	 */
	public LabelValue[] getClasificacion() {
		return clasificacion;
	}

	/**
	 * @param clasificacion
	 *            the clasificacion to set
	 */
	public void setClasificacion(LabelValue[] clasificacion) {
		this.clasificacion = clasificacion;
	}

	/**
	 * @return the indicadorResecuenciar
	 */
	public boolean isIndicadorResecuenciar() {
		return indicadorResecuenciar;
	}

	/**
	 * @param indicadorResecuenciar
	 *            the indicadorResecuenciar to set
	 */
	public void setIndicadorResecuenciar(boolean indicadorResecuenciar) {
		this.indicadorResecuenciar = indicadorResecuenciar;
	}

	/**
	 * @return the allPaises
	 */
	public List getAllPaises() {
		return allPaises;
	}

	/**
	 * @param allPaises
	 *            the allPaises to set
	 */
	public void setAllPaises(List allPaises) {
		this.allPaises = allPaises;
	}

	/**
	 * @return the preVentaExclusivaList
	 */
	public List getPreVentaExclusivaList() {
		return preVentaExclusivaList;
	}

	/**
	 * @param preVentaExclusivaList the preVentaExclusivaList to set
	 */
	public void setPreVentaExclusivaList(List preVentaExclusivaList) {
		this.preVentaExclusivaList = preVentaExclusivaList;
	}

	/**
	 * @return the dataTablePreVentaExclusiva
	 */
	public DataTableModel getDataTablePreVentaExclusiva() {
		return dataTablePreVentaExclusiva;
	}

	/**
	 * @param dataTablePreVentaExclusiva the dataTablePreVentaExclusiva to set
	 */
	public void setDataTablePreVentaExclusiva(DataTableModel dataTablePreVentaExclusiva) {
		this.dataTablePreVentaExclusiva = dataTablePreVentaExclusiva;
	}

	/**
	 * @return the columnaSeleccionada
	 */
	public Map getColumnaSeleccionada() {
		return columnaSeleccionada;
	}

	/**
	 * @param columnaSeleccionada the columnaSeleccionada to set
	 */
	public void setColumnaSeleccionada(Map columnaSeleccionada) {
		this.columnaSeleccionada = columnaSeleccionada;
	}

	/**
	 * @return the oidOferta
	 */
	public String getOidOferta() {
		return oidOferta;
	}

	/**
	 * @param oidOferta the oidOferta to set
	 */
	public void setOidOferta(String oidOferta) {
		this.oidOferta = oidOferta;
	}

	/**
	 * @return the preVentaExclusivaGlobalList
	 */
	public List getPreVentaExclusivaGlobalList() {
		return preVentaExclusivaGlobalList;
	}

	/**
	 * @param preVentaExclusivaGlobalList the preVentaExclusivaGlobalList to set
	 */
	public void setPreVentaExclusivaGlobalList(List preVentaExclusivaGlobalList) {
		this.preVentaExclusivaGlobalList = preVentaExclusivaGlobalList;
	}

	/**
	 * @return the indexVentaExclusiva
	 */
	public String getIndexVentaExclusiva() {
		return indexVentaExclusiva;
	}

	/**
	 * @param indexVentaExclusiva the indexVentaExclusiva to set
	 */
	public void setIndexVentaExclusiva(String indexVentaExclusiva) {
		this.indexVentaExclusiva = indexVentaExclusiva;
	}

}
