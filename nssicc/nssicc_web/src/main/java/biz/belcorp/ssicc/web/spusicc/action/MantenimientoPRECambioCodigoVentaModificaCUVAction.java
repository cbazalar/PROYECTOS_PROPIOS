package biz.belcorp.ssicc.web.spusicc.action;

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
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.sisicc.model.BaseOID;
import biz.belcorp.ssicc.dao.spusicc.model.CodigoVentaMod;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.MantenimientoPRECambioCodigoVentaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaProductoSearchAction;
import biz.belcorp.ssicc.web.spusicc.form.MantenimientoPRECambioCodigoVentaModificaCUVForm;
import biz.belcorp.ssicc.web.spusicc.form.MantenimientoPRECambioCodigoVentaSearchForm;
import biz.belcorp.ssicc.web.spusicc.form.MantenimientoPREModificaProductoAsociadoForm;
import biz.belcorp.ssicc.web.spusicc.form.MantenimientoPREModificaProductoGrupoForm;
import biz.belcorp.ssicc.web.spusicc.form.MantenimientoPREModificaProductoPrincipalForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.action.MantenimientoPEDProductoAsociadoSearchAction;

@SessionScoped
@ManagedBean
public class MantenimientoPRECambioCodigoVentaModificaCUVAction extends
		BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3723198159592036636L;
	private Map data;
	private int cantidadRegistros;
	private List preIndicadorCuadreList;
	private List preTipoCuadreList;
	private List preCriteriosList;
	private List preCatalogosList;
	private List preComponentesList;
	private String oidOferta;
	private String oidDetaOferta;
	private String oidCatalogo;
	private String periodoActivo;
	private String oidGrupoOferta;
	private String numeroGrupo;
	private String tipoGrupo;
	private String oidTipoEstrategia;
	private String oidProm;
	private String codigoPeriodo;
	private String numSecUsuario;
	private String indicadorCuadreGrupoAnterior;
	private String factorCuadreGrupoAnterior;
	private String tipoCuadreCondicionAnterior;
	private String factorCuadreCondicionAnterior;
	private boolean indicadorCodigoVenta;
	private boolean indicadorNumeroPagina;
	private boolean indicadorPrecioCatalogo;
	private boolean indicadorPrecioContable;
	private boolean indicadorFactorRepeticion;
	private boolean idCuv;
	private boolean idCatalogo;
	private boolean idFactor;
	private boolean indicadorExclusion;
	private MantenimientoPRECambioCodigoVentaSearchForm formCodigoVenta;
	private DataTableModel dataTableCriteriosList;
	private DataTableModel dataTableComponenteList;
	private Object beanRegistroCriterio;

	private static final String POPUP_SACPRODUCTO = "SACPRODUCTO";
	@ManagedProperty(value = "#{busquedaProductoSearchAction}")
	private BusquedaProductoSearchAction busquedaProductoSearchAction;
	private boolean mostrarPopupProducto;
	
	private List siccCatalagoList = new ArrayList();
	private List siccTipoOfertaList = new ArrayList();
	private String codTipoOferta;
	private String codSap;

	private boolean indicadorValTextoBreve;
    private boolean indicadorIndDigitable;
    private boolean indicadorIndImprimible;
    private boolean indicadorImpCosteEsta;
    private boolean indicadorNumUnidEstim;
    private boolean indicadorImpVenNetaEstim;
    private boolean indicadorTipoOferta;
    private boolean idValTextoBreve;
	private boolean idIndDigitable;
	private boolean idIndImprimible;
	private boolean idImpCosteEsta;
	private boolean idNumUnidEstim;
	private boolean idImpVenNetaEstim;
	private boolean idTipoOferta;
	
	private List listPanelGrupos = new ArrayList();
	private Map columnasSeleccionadas = new HashMap();
	private Map preOfertaGruposMap = new HashMap();
	private String numeroGrupoGlobal;
	private MantenimientoPREModificaProductoGrupoForm formModificaProductoGrupo;
	private MantenimientoPREModificaProductoPrincipalForm formModificaProductoPrincipal;
	private MantenimientoPREModificaProductoAsociadoForm formModificaProductoAsociado;
	
	private List listPrincipal = new ArrayList();
	private DataTableModel dataTableListPrincipal;
	private Map columnasSeleccionadasPrincipal = new HashMap();
	
	private List listAsociado = new ArrayList();
	private List listAsociadoGlobal = new ArrayList();
	private DataTableModel dataTableListAsociado;
	private Map columnasSeleccionadasAsociado = new HashMap();
	
	private boolean noPasaValidacionTipoOfertaIndividual = false;
	private boolean noPasaValidacionTipoOfertaGrupo = false;
	private boolean noPasaValidacionTipoOfertaPrincipal = false;
	private boolean noPasaValidacionTipoOfertaAsociado = false;
	
	private String valorModificarOfertaCerrada = "0";
	private boolean modificaOfertaCerrada = false;
	private boolean muestraIdModificaOfertaCerrada = false;
	private Integer indicadorModificaOfertaCerrada;
	
	@ManagedProperty(value="#{mantenimientoPEDProductoAsociadoSearchAction}")	
	private MantenimientoPEDProductoAsociadoSearchAction mantenimientoPEDProductoAsociadoSearchAction;
	
	private boolean booleanCuvFacturado = false;
	private boolean idPrecioContable;
	private boolean idPagina;
	private boolean booleanIndDigAsociado = false;
	private boolean booleanIndImpAsociado = false;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setInvocarPopup(java.lang.String)
	 */
	@Override
	protected void setInvocarPopup(String accion) {

		this.mostrarProcesoBatch = false;
		this.mostrarPopupProducto = true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setAceptarPopup(javax.faces.event.ActionEvent, java.lang.String)
	 */
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopup' method");
		}
		this.mostrarProcesoBatch = true;
		MantenimientoPRECambioCodigoVentaModificaCUVForm form = (MantenimientoPRECambioCodigoVentaModificaCUVForm) this.formBusqueda;

		if (accion.equals(POPUP_SACPRODUCTO)) {
			this.mostrarPopupProducto = false;

			this.busquedaProductoSearchAction.verificarRegistro(event);
			if (this.busquedaProductoSearchAction.isSeleccionoRegistro()) {

				Map prodMap = (Map) this.busquedaProductoSearchAction
						.getBeanRegistroSeleccionado();

				form.setCodigoProducto(MapUtils.getString(prodMap, "codigoSap"));
				form.setDescripcionProducto(MapUtils.getString(prodMap,
						"descripcionCorta"));
				this.busquedaProductoSearchAction
						.setBeanRegistroSeleccionado(null);
			}
			this.formBusqueda = form;
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
		this.mostrarPopupProducto = false;
		this.busquedaProductoSearchAction.setBeanRegistroSeleccionado(null);
	}

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

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoPRECambioCodigoVentaModificaCUVForm form = new MantenimientoPRECambioCodigoVentaModificaCUVForm();
		return form;
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
	
	/**
	 * Inicializand valores
	 */
	public void inicializandoValores(){
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoPRECambioCodigoVentaModificaCUVForm f = (MantenimientoPRECambioCodigoVentaModificaCUVForm) this.formBusqueda;
		MantenimientoPRECambioCodigoVentaService service = (MantenimientoPRECambioCodigoVentaService) getBean("spusicc.mantenimientoPRECambioCodigoVentaService");
		GenericoService genericoService = (GenericoService) getBean("genericoService");
		
		MantenimientoPREModificaProductoGrupoForm formProdGrupo = new MantenimientoPREModificaProductoGrupoForm();
		MantenimientoPREModificaProductoPrincipalForm formProdPrin = new MantenimientoPREModificaProductoPrincipalForm();
		MantenimientoPREModificaProductoAsociadoForm formProdAsoc = new MantenimientoPREModificaProductoAsociadoForm();
		
		this.mostrarBotonEliminar = true;
		
		this.formModificaProductoGrupo = formProdGrupo;
		this.formModificaProductoPrincipal = formProdPrin;
		this.formModificaProductoAsociado = formProdAsoc;
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		this.siccCatalagoList = reporteService.getListaGenerico("getOidCatalogoProductos", null);
		this.siccTipoOfertaList =  service.getTipoOfertaList(null);

		if (this.data != null) {
			Map map = new HashMap();
			map.put("codigoIdioma", usuario.getIdioma().getCodigoISO());
			this.oidDetaOferta = (String) this.data.get("oidDetaOferta");
			this.oidOferta = (String) this.data.get("oidOferta");
			this.oidCatalogo = (String) this.data.get("oidCatalogo");
			this.oidGrupoOferta = (String) this.data.get("oidGrupoOferta");
			if (this.data.get("numGrupo") == null)
				numeroGrupo = "";
			else
				this.numeroGrupo = this.data.get("numGrupo").toString();
			this.tipoGrupo = (String) this.data.get("tipGrupo");
			this.oidTipoEstrategia = (String) data.get("oidTipoEstrategia");
			this.codigoPeriodo = (String) this.data.get("codigoPeriodo");
			
			this.codTipoOferta = (String) this.data.get("codTipoOferta");
			this.codSap = (String) this.data.get("codSap");
			
			//INICIO Cargar Panel de Grupos PRE_GRUPO_OFERT
			if (!StringUtils.equals(this.oidTipoEstrategia, Constants.NUMERO_DOS)) {
				this.listPanelGrupos = service.getPanelGrupos(data);
				
				if(this.listPanelGrupos.size() > 0 && this.listPanelGrupos != null){
					for (int i = 0; i < this.listPanelGrupos.size(); i++) {
						Map mapGrupo = (Map) this.listPanelGrupos.get(i);
						mapGrupo.put("listaProductos", service.getCodigoVentaList(mapGrupo));
						
						this.preOfertaGruposMap.put(i + 1 + "", mapGrupo);
	//					this.preOfertaGruposMap = (Map) this.listPanelGrupos.get(i);
	//					this.preOfertaGruposMap.put("listaProductos", service.getCodigoVentaList(this.preOfertaGruposMap));
					}
					
					this.numeroGrupoGlobal = this.numeroGrupo;
					this.mPantallaPrincipalBean.setPreProductoAsociadoGrupoOfertaList(service.getCodigoVentaList(MapUtils.getMap(this.preOfertaGruposMap, this.numeroGrupo)));
					this.mPantallaPrincipalBean.setListaModelPreProductoAsociadoGrupoOfertaList(new DataTableModel(this.mPantallaPrincipalBean.getPreProductoAsociadoGrupoOfertaList()));
				}
			}
			//FIN Cargar Panel de Grupos PRE_GRUPO_OFERT

			// seccion grupos
			if (StringUtils.isNotBlank(this.oidGrupoOferta)) {
				f.setIndicadorSeccionGrupos(Constants.NUMERO_UNO);
				f.setNumGrupo(this.numeroGrupo);
				f.setTipoGrupo(this.tipoGrupo);

				map.put("oidGrupoOferta", this.oidGrupoOferta);
				Map grupo = new HashMap();
				grupo = (Map) service.getListGrupo(map);
				this.preIndicadorCuadreList = new ArrayList();
	
				f.setFactorCuadre(MapUtils.getString(grupo, "factorCuadre"));
				f.setIndCuadre(this.oidTipoEstrategia);
				String oid = MapUtils.getString(grupo, "oid");
				f.setIndicadorCuadre(oid);
				f.setOid(oid);
				f.setDescripcion(MapUtils.getString(grupo, "descripcion"));
				
				if (StringUtils.equalsIgnoreCase(this.oidTipoEstrategia, Constants.NUMERO_TRES) || 
					StringUtils.equalsIgnoreCase(this.oidTipoEstrategia, Constants.NUMERO_SIETE)) {
					if (grupo != null && grupo.size() > 0) {
						if (StringUtils.isNotBlank(oid)) {
							if (StringUtils.equalsIgnoreCase(oid, Constants.NUMERO_TRES) ||
								StringUtils.equalsIgnoreCase(oid, Constants.NUMERO_OCHO)) {
								f.setOid(oid);
								f.setDescripcion(MapUtils.getString(grupo, "descripcion"));
							} else {
								map.put("oidTipoEstrategia", this.oidTipoEstrategia);
								this.preIndicadorCuadreList = service.getIndicadorCuadreList(map);
							}
						}
						else {
							f.setOid("");
							f.setDescripcion("");
						}
					}
				}
				
				if (StringUtils.equalsIgnoreCase(this.oidTipoEstrategia, Constants.NUMERO_CUATRO)) {
					map.put("oidTipoEstrategia", this.oidTipoEstrategia);
					this.preIndicadorCuadreList = service.getIndicadorCuadreList(map);
				}
				
				if (StringUtils.equalsIgnoreCase(this.oidTipoEstrategia, Constants.NUMERO_SEIS)) {
					f.setOid("");
					f.setDescripcion("");
					
				}

				this.indicadorCuadreGrupoAnterior = (f.getIndicadorCuadre() != null && StringUtils
						.isNotBlank(f.getIndicadorCuadre())) ? f
						.getIndicadorCuadre() : "";
				this.factorCuadreGrupoAnterior = (f.getFactorCuadre() != null && StringUtils
						.isNotBlank(f.getFactorCuadre())) ? f.getFactorCuadre()
						: "";

			} else {
				f.setIndicadorSeccionGrupos(Constants.NUMERO_CERO);
			}

			// seccion condiciones
			if (StringUtils.isNotBlank(this.oidTipoEstrategia) 	&& 
			   (StringUtils.equalsIgnoreCase(this.oidTipoEstrategia, Constants.NUMERO_CINCO) || 
			    StringUtils.equalsIgnoreCase(this.oidTipoEstrategia, Constants.NUMERO_SEIS) || 
			    StringUtils.equalsIgnoreCase(this.oidTipoEstrategia, Constants.NUMERO_SIETE))) {
				f.setIndicadorSeccionCondiciones(Constants.NUMERO_UNO);
				map.put("oidOferta", this.oidOferta);
				Map secciones = (Map) service.getSeccionesList(map);

				f.setTipoCuadre(MapUtils.getString(secciones, "idCuadrePromocion"));
				f.setFactorCuadreSecc(MapUtils.getString(secciones, "factorCuadre"));
				this.tipoCuadreCondicionAnterior = f.getTipoCuadre();
				this.factorCuadreCondicionAnterior = f.getFactorCuadreSecc();
				this.oidProm = MapUtils.getString(secciones, "oid");
				
				// lista para el tipo de cuadre
				this.preTipoCuadreList = service.getTipoCuadreList(map);
				map.put("oidProm", MapUtils.getString(secciones, "oid"));
				map.put("codigoPais", f.getCodigoPais());
				// lista para la lista de criterios
				this.preCriteriosList = service.getCriteriosList(map);
				this.dataTableCriteriosList = new DataTableModel(
						this.preCriteriosList);
				this.cantidadRegistros = CollectionUtils.size(service
						.getCriteriosList(map));
				// lista de catalogos
				this.preCatalogosList = service.getCatalogoList(map);
				map.put("codigoPeriodo", this.codigoPeriodo);
				// lista de componentes
				this.preComponentesList = service.getComponentesList(map);
				this.dataTableComponenteList=new DataTableModel(this.preComponentesList);

			} else {
				f.setIndicadorSeccionCondiciones(Constants.NUMERO_CERO);
			}
			
			//INICIO SECCION COMPUESTA FIJA
			if (StringUtils.equals(this.oidTipoEstrategia, Constants.NUMERO_DOS)) {
				Map mapCF = new HashMap();
				mapCF.put("oidOferta", this.oidOferta);
				mapCF.put("oidPeriodo", this.data.get("oidPeriodo"));
				
				mapCF.put("indProdPrin", Constants.NUMERO_UNO);
				this.listPrincipal = service.getCodigoVentaList(mapCF);
				this.dataTableListPrincipal = new DataTableModel(this.listPrincipal);
				
				mapCF.put("indProdPrin", Constants.NUMERO_CERO);
//				this.listAsociado = service.getCodigoVentaList(mapCF);
//				this.listAsociadoGlobal = service.getCodigoVentaList(mapCF);
//				this.dataTableListAsociado = new DataTableModel(this.listAsociado);
				
				this.mPantallaPrincipalBean.setPreProductoAsociadoCompuestaFijaGlobalList(service.getCodigoVentaList(mapCF));
				this.mPantallaPrincipalBean.setPreProductoAsociadoCompuestaFijaList(service.getCodigoVentaList(mapCF));
				this.mPantallaPrincipalBean.setListaModelPreProductoAsociadoCompuestaFijaList(new DataTableModel(this.mPantallaPrincipalBean.getPreProductoAsociadoCompuestaFijaList()));
			}
			//FIN INICIO SECCION COMPUESTA FIJA

			map.put("codigoPais", pais.getCodigo());
			map.put("oidDetaOferta", this.oidDetaOferta);
			map.put("oidOferta", this.oidOferta);
			map.put("oidCatalogo", this.oidCatalogo);
			map.put("numSecUsuario", numSecUsuario);

//			if (!StringUtils.equals(this.oidTipoEstrategia, Constants.NUMERO_DOS)) {
				CodigoVentaMod codigoVentaMod = service.getCodigoVentaObject(map);

				/* Seteamos el Formulario */
				f.setCodigoPais(pais.getCodigo());
				f.setCodigoVenta(codigoVentaMod.getCodigoVenta());
				f.setNumPagina(codigoVentaMod.getNumPagina());
				f.setPreCatalogo(codigoVentaMod.getPreCatalogo());
				f.setPreContable(codigoVentaMod.getPreContable());
				f.setFacRepeticion(codigoVentaMod.getFacRepeticion());
				
				f.setValTextoBreve(codigoVentaMod.getValTextoBreve());
				f.setIndDigitable(StringUtils.equals(codigoVentaMod.getIndDigitable(), Constants.NUMERO_UNO) ? "true" : "false");
				f.setIndImprimible(StringUtils.equals(codigoVentaMod.getIndImprimible(), Constants.NUMERO_UNO) ? "true" : "false");
				f.setImpCosteEsta(codigoVentaMod.getImpCosteEsta());
				f.setNumUnidEstim(codigoVentaMod.getNumUnidEstim());
				f.setImpVenNetaEstim(codigoVentaMod.getImpVenNetaEstim());
				f.setTipoOferta(codigoVentaMod.getTipoOferta());
				
				this.indicadorCodigoVenta = false;
				this.indicadorNumeroPagina = false;
				this.indicadorPrecioCatalogo = false;
				this.indicadorPrecioContable = false;
				this.indicadorFactorRepeticion = false;
				
				this.indicadorValTextoBreve = false;
				this.indicadorIndDigitable = false;
				this.indicadorIndImprimible = false;
				this.indicadorImpCosteEsta = false;
				this.indicadorNumUnidEstim = false;
				this.indicadorImpVenNetaEstim = false;
				this.indicadorTipoOferta = false;
//			}
			
			f.setTipoRango("");
			f.setIndicadorExclusion("");
			f.setDescripcionProducto("");
			f.setPaginaFinal("");
			f.setPaginaInicial("");
			f.setCodigoProducto("");
			
			f.setGrupoAnterior(this.numeroGrupo);

			this.periodoActivo = periodoActivo;
			Integer cuvFact = service.getExisteCUVFacturado(map);
			f.setIndicadorCUVFacturado(cuvFact);
			this.indicadorModificaOfertaCerrada = cuvFact;
			habilitarComponentes(f);
			
			if(cuvFact > 0){
				//this.mostrarBotonSave = false;
				
				//Inicio Verifica si se puede modificar oferta teniendo la matriz facturando
				ParametroPais parametroPais = new ParametroPais();	
				parametroPais.setCodigoPais(pais.getCodigo());
				parametroPais.setCodigoSistema(Constants.CODIGO_SISTEMA_PRE);
				parametroPais.setCodigoParametro(Constants.COD_PARAM_MODIFICA_OFERTA_CERRADA);
				parametroPais.setNombreParametro(Constants.NOM_PARAM_MODIFICA_OFERTA_CERRADA);
				parametroPais.setIndicadorActivo(Constants.ESTADO_ACTIVO);
				
				List parametros = genericoService.getParametrosPais(parametroPais);
		        
				if(parametros != null && parametros.size() > 0) {
					ParametroPais p = (ParametroPais)parametros.get(0);
					this.valorModificarOfertaCerrada = p.getValorParametro();
				}
				
				if(StringUtils.equals(this.valorModificarOfertaCerrada, Constants.NUMERO_UNO)){
					this.modificaOfertaCerrada = false;
					this.muestraIdModificaOfertaCerrada = false;
				}else if (StringUtils.equals(this.valorModificarOfertaCerrada, Constants.NUMERO_CERO)){
					this.modificaOfertaCerrada = false;
					this.muestraIdModificaOfertaCerrada = true;
				}
				//Fin Verifica si se puede modificar oferta teniendo la matriz facturando
			}
		}
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method");
		}
		MantenimientoPRECambioCodigoVentaModificaCUVForm f = (MantenimientoPRECambioCodigoVentaModificaCUVForm) this.formBusqueda;
		MantenimientoPRECambioCodigoVentaService service = (MantenimientoPRECambioCodigoVentaService) getBean("spusicc.mantenimientoPRECambioCodigoVentaService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		boolean bGrabar = true;
		
		try {
			if (!StringUtils.equals(this.oidTipoEstrategia, Constants.NUMERO_DOS)) {
				if(this.noPasaValidacionTipoOfertaIndividual){
					throw new Exception("Elegir un Tipo Oferta que supere las validaciones");
				}
				
				Map criteria = new HashMap();
				criteria.put("codigoPais", pais.getCodigo());
				criteria.put("codigoUsuario", usuario.getLogin());
				criteria.put("oidOferta", this.oidOferta);
				criteria.put("oidDetaOferta", this.oidDetaOferta);
				criteria.put("oidCatalogo", this.oidCatalogo);

				// Setea los checkBox de booleano a String
				setearCheckBox(f);

				String indCUV = f.getIndicadorCodigoVenta();
				String indPagina = f.getIndicadorNumeroPagina();
				String indPreCatalogo = f.getIndicadorPrecioCatalogo();
				String indPreContable = f.getIndicadorPrecioContable();
				String indFactor = f.getIndicadorFactorRepeticion();
				
				String indValTextoBreve = f.getIndicadorValTextoBreve();
			    String indIndDigitable = f.getIndicadorIndDigitable();
			    String indIndImprimible = f.getIndicadorIndImprimible();
			    String indImpCosteEsta = f.getIndicadorImpCosteEsta();
			    String indNumUnidEstim = f.getIndicadorNumUnidEstim();
			    String indImpVenNetaEstim = f.getIndicadorImpVenNetaEstim();
			    String indTipoOferta = f.getIndicadorTipoOferta();

				if (indCUV.equals(Constants.SI)) {
					criteria.put("indicadorCUV", "S");
					criteria.put("valorCUV", f.getCodigoVenta());
				} else {
					criteria.put("indicadorCUV", "N");
					criteria.put("valorCUV", "");
				}

				if (indPagina.equals(Constants.SI)) {
					criteria.put("indicadorPagina", "S");
					criteria.put("valorPagina", f.getNumPagina());
				} else {
					criteria.put("indicadorPagina", "N");
					criteria.put("valorPagina", "");
				}

				if (indPreCatalogo.equals(Constants.SI)) {
					criteria.put("indicadorCatalogo", "S");
					criteria.put("valorPreCatalogo", f.getPreCatalogo());
				} else {
					criteria.put("indicadorCatalogo", "N");
					criteria.put("valorPreCatalogo", "");
				}

				if (indPreContable.equals(Constants.SI)) {
					criteria.put("indicadorContable", "S");
					criteria.put("valorPreContable", f.getPreContable());
				} else {
					criteria.put("indicadorContable", "N");
					criteria.put("valorPreContable", "");
				}

				if (indFactor.equals(Constants.SI)) {
					criteria.put("indicadorFactor", "S");
					criteria.put("valorFactor", f.getFacRepeticion());
				} else {
					criteria.put("indicadorFactor", "N");
					criteria.put("valorFactor", "");
				}
				
				
				
				if (indValTextoBreve.equals(Constants.SI)) {
					criteria.put("indicadorValTextoBreve", "S");
					criteria.put("valorValTextoBreve", f.getValTextoBreve());
				} else {
					criteria.put("indicadorValTextoBreve", "N");
					criteria.put("valorValTextoBreve", "");
				}
				
				if (indIndDigitable.equals(Constants.SI)) {
					criteria.put("indicadorIndDigitable", "S");
					
					String valIndDig = "";
					if(StringUtils.equals(f.getIndDigitable(), "true"))
						valIndDig = Constants.NUMERO_UNO;
					else
						valIndDig = Constants.NUMERO_CERO;
					
					criteria.put("valorIndDigitable", valIndDig);
				} else {
					criteria.put("indicadorIndDigitable", "N");
					criteria.put("valorIndDigitable", "");
				}
				
				if (indIndImprimible.equals(Constants.SI)) {
					criteria.put("indicadorIndImprimible", "S");
					
					String valIndImp = "";
					if(StringUtils.equals(f.getIndImprimible(), "true"))
						valIndImp = Constants.NUMERO_UNO;
					else
						valIndImp = Constants.NUMERO_CERO;
					
					criteria.put("valorIndImprimible", valIndImp);
				} else {
					criteria.put("indicadorIndImprimible", "N");
					criteria.put("valorIndImprimible", "");
				}
				
				if (indImpCosteEsta.equals(Constants.SI)) {
					criteria.put("indicadorImpCosteEsta", "S");
					criteria.put("valorImpCosteEsta", f.getImpCosteEsta());
				} else {
					criteria.put("indicadorImpCosteEsta", "N");
					criteria.put("valorImpCosteEsta", "");
				}
				
				if (indNumUnidEstim.equals(Constants.SI)) {
					criteria.put("indicadorNumUnidEstim", "S");
					criteria.put("valorNumUnidEstim", f.getNumUnidEstim());
				} else {
					criteria.put("indicadorNumUnidEstim", "N");
					criteria.put("valorNumUnidEstim", "");
				}
				
				if (indImpVenNetaEstim.equals(Constants.SI)) {
					criteria.put("indicadorImpVenNetaEstim", "S");
					criteria.put("valorImpVenNetaEstim", f.getImpVenNetaEstim());
				} else {
					criteria.put("indicadorImpVenNetaEstim", "N");
					criteria.put("valorImpVenNetaEstim", "");
				}
				
				if (indTipoOferta.equals(Constants.SI)) {
					criteria.put("indicadorTipoOferta", "S");
					criteria.put("valorTipoOferta", f.getTipoOferta());
				} else {
					criteria.put("indicadorTipoOferta", "N");
					criteria.put("valorTipoOferta", "");
				}

				criteria.put("numSecUsuario", numSecUsuario);

				service.executeModificarDatosCUV(criteria);
				MantenimientoPRECambioCodigoVentaSearchAction action = findManageBean("mantenimientoPRECambioCodigoVentaSearchAction");
				action.setModificaCUV(Constants.NUMERO_UNO);
				action.buscar(getFormCodigoVenta());
				salirGrabarPantallaPadre = true;
			}else{
				List listPrincipal = this.listPrincipal;
				List listAsociado = this.mPantallaPrincipalBean.getPreProductoAsociadoCompuestaFijaGlobalList();
				
				for (int i = 0; i < listPrincipal.size(); i++) {
					Map prodPrincipal = (Map) listPrincipal.get(i);
					prodPrincipal.put("indProdPrin", Constants.NUMERO_UNO);
					
					if(StringUtils.equals(MapUtils.getString(prodPrincipal, "estado"), "M")){
						service.updateProductoPrincipalAsociadoOferta(prodPrincipal);
					}
				}
				
				for (int i = 0; i < listAsociado.size(); i++) {
					Map prodAsociado = (Map) listAsociado.get(i);
					prodAsociado.put("indProdPrin", Constants.NUMERO_CERO);
					
					if(StringUtils.equals(MapUtils.getString(prodAsociado, "estado"), "A")){
						prodAsociado.put("oidOferta", this.oidOferta);
						service.insertProductoPrincipalAsociadoOferta(prodAsociado);
					}
					
					if(StringUtils.equals(MapUtils.getString(prodAsociado, "estado"), "M")){
						service.updateProductoPrincipalAsociadoOferta(prodAsociado);
					}
					
					if(StringUtils.equals(MapUtils.getString(prodAsociado, "estado"), "E")){
						service.deleteProductoGrupoOfertaDetalle(prodAsociado);
					}
				}
			}
		} catch (Exception e) {
			bGrabar = false;
			throw new Exception(e.getMessage());
		}
		return bGrabar;
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
		return "mantenimientoPRECambioCodigoVentaModificaCUVForm.updated";
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		
	}

	public void habilitarComponentes(
			MantenimientoPRECambioCodigoVentaModificaCUVForm f) {
		int indicadorCUV = f.getIndicadorCUVFacturado();

		if (indicadorCUV > 0) {
			idCuv = true;
			idCatalogo = true;
			idFactor = true;
			idValTextoBreve = true;
			idIndDigitable = true;
			idIndImprimible = true;
			idImpCosteEsta = true;
			idNumUnidEstim = true;
			idImpVenNetaEstim = true;
			idTipoOferta = true;
		} else {
			idCuv = false;
			idCatalogo = false;
			idFactor = false;
			idValTextoBreve = false;
			idIndDigitable = false;
			idIndImprimible = false;
			idImpCosteEsta = false;
			idNumUnidEstim = false;
			idImpVenNetaEstim = false;
			idTipoOferta = false;
			idPrecioContable = false;
			idPagina = false;
		}
	}

	public void setearCheckBox(
			MantenimientoPRECambioCodigoVentaModificaCUVForm f) {
		if (indicadorCodigoVenta)
			f.setIndicadorCodigoVenta(Constants.SI);
		else
			f.setIndicadorCodigoVenta(Constants.NO);

		if (indicadorNumeroPagina)
			f.setIndicadorNumeroPagina(Constants.SI);
		else
			f.setIndicadorNumeroPagina(Constants.NO);

		if (indicadorPrecioCatalogo)
			f.setIndicadorPrecioCatalogo(Constants.SI);
		else
			f.setIndicadorPrecioCatalogo(Constants.NO);

		if (indicadorPrecioContable)
			f.setIndicadorPrecioContable(Constants.SI);
		else
			f.setIndicadorPrecioContable(Constants.NO);

		if (indicadorFactorRepeticion)
			f.setIndicadorFactorRepeticion(Constants.SI);
		else
			f.setIndicadorFactorRepeticion(Constants.NO);
		
		
		
		if (indicadorValTextoBreve)
			f.setIndicadorValTextoBreve(Constants.SI);
		else
			f.setIndicadorValTextoBreve(Constants.NO);

		if (indicadorIndDigitable)
			f.setIndicadorIndDigitable(Constants.SI);
		else
			f.setIndicadorIndDigitable(Constants.NO);
		
		if (indicadorIndImprimible)
			f.setIndicadorIndImprimible(Constants.SI);
		else
			f.setIndicadorIndImprimible(Constants.NO);
		
		if (indicadorImpCosteEsta)
			f.setIndicadorImpCosteEsta(Constants.SI);
		else
			f.setIndicadorImpCosteEsta(Constants.NO);
		
		if (indicadorNumUnidEstim)
			f.setIndicadorNumUnidEstim(Constants.SI);
		else
			f.setIndicadorNumUnidEstim(Constants.NO);
		
		if (indicadorImpVenNetaEstim)
			f.setIndicadorImpVenNetaEstim(Constants.SI);
		else
			f.setIndicadorImpVenNetaEstim(Constants.NO);
		
		if (indicadorTipoOferta)
			f.setIndicadorTipoOferta(Constants.SI);
		else
			f.setIndicadorTipoOferta(Constants.NO);

	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void actualizagrupo(ActionEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'procesar' method");
		}

		MantenimientoPRECambioCodigoVentaModificaCUVForm f = (MantenimientoPRECambioCodigoVentaModificaCUVForm) this.formBusqueda;
		MantenimientoPRECambioCodigoVentaService service = (MantenimientoPRECambioCodigoVentaService) getBean("spusicc.mantenimientoPRECambioCodigoVentaService");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		try {

			if(!StringUtils.equals(this.oidTipoEstrategia, Constants.NUMERO_SEIS)){
				if (StringUtils.isBlank(f.getFactorCuadre())) {
					throw new Exception("Ingrese Indicador de Cuadre o Factor de Cuadre");
			    }
				if (StringUtils.isBlank(f.getIndicadorCuadre())) {
					throw new Exception("Valor Indicador Cuadre se encuentra vacío");
			    }
			}
			
			Map criteria = new HashMap();
			criteria.put("oidGrupoOferta", this.oidGrupoOferta);
			criteria.put("factorCuadre", f.getFactorCuadre());
			criteria.put("codigoUsuario", usuario.getLogin());

			criteria.put("oidOferta", this.oidOferta);
			criteria.put("oidDetaOferta", this.oidDetaOferta);

			if (StringUtils.equalsIgnoreCase(this.oidTipoEstrategia, Constants.NUMERO_SEIS)) {
				criteria.put("indicadorEstrategia", f.getIndicadorCuadre());
				service.updateFactorIndicadorCuadre(criteria);
				// actualizamos la tabla de auditoria primero para el factor
				criteria.put("nombreCampoModificado", Constants.PRE_CODIGO_FACTOR_CUADRE_GRUPO);
				criteria.put("valorAnterior", this.factorCuadreGrupoAnterior);
				criteria.put("valorNuevo", f.getFactorCuadre());
				
				// llamamos al service
				service.insertHistoricoCUV(criteria);
				// actualizamos la tabla de auditoria ahora para el indicador de
				// cuadre
				criteria.put("nombreCampoModificado", Constants.PRE_CODIGO_INDICADOR_CUADRE_GRUPO);
				criteria.put("valorAnterior", this.indicadorCuadreGrupoAnterior);
				criteria.put("valorNuevo", f.getIndicadorCuadre());
				// llamamos al service
				service.insertHistoricoCUV(criteria);
			} else {
				service.updateFactorCuadre(criteria);
				// actualizamos la tabla de auditoria
				criteria.put("nombreCampoModificado",
						Constants.PRE_CODIGO_FACTOR_CUADRE_GRUPO);
				criteria.put("valorAnterior", this.factorCuadreGrupoAnterior);
				criteria.put("valorNuevo", f.getFactorCuadre());
				// llamamos al service
				service.insertHistoricoCUV(criteria);
			}
			
			//INICIO Agregar A, Modificar M, Eliminar E los productos de cada grupo
			Map gruposAGuardar = this.preOfertaGruposMap;
			for (int i = 0; i < this.listPanelGrupos.size(); i++) {
				Map grupo = MapUtils.getMap(gruposAGuardar, i + 1 + "");
				List productos = (List) MapUtils.getObject(grupo, "listaProductos");
				
				for (int j = 0; j < productos.size(); j++) {
					Map mapProdEliminar = (Map) productos.get(j);
					
					if(StringUtils.equals("A", MapUtils.getString(mapProdEliminar, "estado"))){
						if(StringUtils.equals(Constants.NUMERO_UNO, MapUtils.getString(mapProdEliminar, "oidTipoEstrategia"))){
							mapProdEliminar.put("flagPrincipal", Constants.NUMERO_UNO);
						}else{
							mapProdEliminar.put("flagPrincipal", Constants.NUMERO_CERO);
						}
						mapProdEliminar.put("oidOferta", MapUtils.getString(grupo, "oidOferta"));
						mapProdEliminar.put("oidGrupoOferta", MapUtils.getString(grupo, "oidGrupoOferta"));
						service.insertProductoGrupoOfertaDetalle(mapProdEliminar);
					}
					
					if(StringUtils.equals("M", MapUtils.getString(mapProdEliminar, "estado"))){
						service.updateProductoGrupoOfertaDetalle(mapProdEliminar);
					}
					
					if(StringUtils.equals("E", MapUtils.getString(mapProdEliminar, "estado"))){
						service.deleteProductoGrupoOfertaDetalle(mapProdEliminar);
					}
				}
			}
			//FIN Agregar, Modificar, Eliminar los productos de cada grupo
			
			addInfo("Ok", this.getResourceMessage("mantenimientoPRECambioCodigoVentaModificaCUVForm.message.procesoOk"));
			
			this.inicializandoValores();
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		}

		this.indicadorCuadreGrupoAnterior = "";
		this.factorCuadreGrupoAnterior = "";
	}

	public void actualizacondicion(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'procesar' method");
		}

		MantenimientoPRECambioCodigoVentaModificaCUVForm f = (MantenimientoPRECambioCodigoVentaModificaCUVForm) this.formBusqueda;
		MantenimientoPRECambioCodigoVentaService service = (MantenimientoPRECambioCodigoVentaService) getBean("spusicc.mantenimientoPRECambioCodigoVentaService");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		try {

			if (StringUtils.isBlank(f.getFactorCuadreSecc()))
				throw new Exception("Ingrese Factor de Cuadre");

			Map criteria = new HashMap();
			criteria.put("oidGrupoOferta", this.oidGrupoOferta);
			criteria.put("factorCuadre", f.getFactorCuadre());
			criteria.put("codigoUsuario", usuario.getLogin());
			criteria.put("oidOferta", this.oidOferta);
			criteria.put("oidDetaOferta", this.oidDetaOferta);
			criteria.put("tipoCuadreSecc", f.getTipoCuadre());
			criteria.put("factorCuadreSecc", f.getFactorCuadreSecc());
			service.updateFactorCuadreCondicion(criteria);
			// actualizamos la tabla de auditoria
			criteria.put("nombreCampoModificado",
					Constants.PRE_CODIGO_FACTOR_CUADRE_GRUPO);
			criteria.put("valorAnterior", this.factorCuadreCondicionAnterior);
			criteria.put("valorNuevo", f.getFactorCuadreSecc());
			// llamamos al service
			service.insertHistoricoCUV(criteria);

			// actualizamos la tabla de auditoria
			criteria.put("nombreCampoModificado",
					Constants.PRE_CODIGO_FACTOR_CUADRE_CONDICION);
			criteria.put("valorAnterior", this.tipoCuadreCondicionAnterior);
			criteria.put("valorNuevo", f.getTipoCuadre());
			// llamamos al service
			service.insertHistoricoCUV(criteria);
			addInfo("Ok",
					this.getResourceMessage("mantenimientoPRECambioCodigoVentaModificaCUVForm.message.procesoCondicionOk"));
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		}

		this.factorCuadreCondicionAnterior = "";
	}

	public void cambiarTipoRango(ValueChangeEvent val) {
		if (val.getNewValue() == null) return;
		String valor = val.getNewValue().toString();
		MantenimientoPRECambioCodigoVentaModificaCUVForm f = (MantenimientoPRECambioCodigoVentaModificaCUVForm) this.formBusqueda;

		if (valor.equals("R")) {
			f.setTipoRango("R");
			indicadorExclusion = false;
		} else if (valor.equals("P")) {
			f.setTipoRango("P");
			indicadorExclusion = true;
		} else {
			f.setTipoRango(null);
			indicadorExclusion = false;
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
	public void ingresarcriterio(ActionEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'procesar' method");
		}

		MantenimientoPRECambioCodigoVentaModificaCUVForm f = (MantenimientoPRECambioCodigoVentaModificaCUVForm) this.formBusqueda;
		MantenimientoPRECambioCodigoVentaService service = (MantenimientoPRECambioCodigoVentaService) getBean("spusicc.mantenimientoPRECambioCodigoVentaService");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		try {	
			if (StringUtils.isBlank(f.getTipoRango())) 
				throw new Exception("Debe seleccionar un rango");
			
			if (f.getTipoRango().equals("R")) {
				if (StringUtils.isBlank(f.getPaginaInicial()) && StringUtils.isBlank(f.getPaginaFinal()))
					throw new Exception("Debe ingresar Pagina Inicial y Pagina Final");
			}
	
			if (f.getTipoRango().equals("P")) {
				if (StringUtils.isBlank(f.getCodigoProducto()))
					throw new Exception("Debe ingresar Producto");
			}
			
	
			if (indicadorExclusion)
				f.setIndicadorExclusion("S");
			else
				f.setIndicadorExclusion("N");
	
			Map criteria = new HashMap();
			criteria.put("oidCata", f.getCodigoCatalogo());
			criteria.put("oidProm", this.oidProm);
			criteria.put("tipoRango", f.getTipoRango());
			String valorDesde = null;
			String valorHasta = null;
			if (StringUtils.equalsIgnoreCase(f.getTipoRango(), Constants.PRE_TIPO_RANGO_R)) {
				valorDesde = f.getPaginaInicial();
				valorHasta = f.getPaginaFinal();
			} else {
				if (StringUtils.equalsIgnoreCase(f.getTipoRango(), 	Constants.PRE_TIPO_RANGO_P)) {
					criteria.put("codigoPais", pais.getCodigo());
					criteria.put("codigoSAP", f.getCodigoProducto());
					valorDesde = service.getProductoBySAP(criteria);
				}
			}
			criteria.put("valorDesde", valorDesde);
			criteria.put("valorHasta", valorHasta);
			criteria.put("exclusion",
					(StringUtils.equalsIgnoreCase(f.getIndicadorExclusion(),
							Constants.SI) ? Constants.NUMERO_UNO
							: Constants.NUMERO_CERO));
	
			service.insertaCriterio(criteria);
	
			criteria.put("oidProm", this.oidProm);
			criteria.put("codigoPais", f.getCodigoPais());
			this.preCriteriosList = service.getCriteriosList(criteria);
			dataTableCriteriosList = new DataTableModel(this.preCriteriosList);
	
			this.calcularcriterio(f);
			this.addInfo("Ok", this.getResourceMessage("mantenimientoPRECambioCodigoVentaModificaCUVForm.message.criterioIngresadoOk"));
			
			f.setCodigoProducto(null);
			f.setPaginaFinal(null);
			f.setPaginaInicial(null);
			f.setDescripcionProducto(null);
			f.setTipoRango(null);
			f.setIndicadorExclusion(null);
			f.setDescripcionProducto(null);
		}
		catch(Exception e) {
			this.addError("Error",this.obtieneMensajeErrorException(e));
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
	public void calcularcriterio(
			MantenimientoPRECambioCodigoVentaModificaCUVForm f)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'procesar' method");
		}

		MantenimientoPRECambioCodigoVentaService service = (MantenimientoPRECambioCodigoVentaService) getBean("spusicc.mantenimientoPRECambioCodigoVentaService");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		try {
			Map criteria = new HashMap();
			// limpiamos la lista
			this.preComponentesList = new ArrayList();
			// volvemos a llenar la lista
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("codigoPeriodo", this.codigoPeriodo);
			criteria.put("oidOferta", this.oidOferta);
			List listaComponentes = service.getComponentesList(criteria);
			this.preComponentesList = listaComponentes;

			if (listaComponentes != null && listaComponentes.size() > 0) {
				criteria.put("oidPromo", this.oidOferta);
				service.deletePromo(criteria);
				for (int i = 0; i < listaComponentes.size(); i++) {
					Map item = (Map) listaComponentes.get(i);
					criteria.put("oidDetalle", MapUtils.getString(item, "oid"));
					service.insertaPromo(criteria);
				}
			}
		} catch (Exception ex) {
			addError("Error", obtieneMensajeErrorException(ex));
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
	public void eliminarcriterio(ActionEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'procesar' method");
		}

		MantenimientoPRECambioCodigoVentaModificaCUVForm f = (MantenimientoPRECambioCodigoVentaModificaCUVForm) this.formBusqueda;
		MantenimientoPRECambioCodigoVentaService service = (MantenimientoPRECambioCodigoVentaService) getBean("spusicc.mantenimientoPRECambioCodigoVentaService");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		try {			
			
			Map criteria = (Map) beanRegistroCriterio;
			criteria.put("oidRango", criteria.get("oid").toString());
			Map criterio = service.getCriterioById(criteria);
			service.deleteCriterio(criteria);
			String tipoRango = (String) criteria.get("tipoRango");
			
			// actualizamos el historico
			if (StringUtils.equalsIgnoreCase(tipoRango, Constants.PRE_NOMBRE_MODIFICADO_RANGO)) {
				criteria.put("oidOferta", this.oidOferta);
				criteria.put("oidDetaOferta", this.oidDetaOferta);
				criteria.put("nombreCampoModificado",
						Constants.PRE_NOMBRE_MODIFICADO_RANGO);
				criteria.put(
						"valorAnterior",
						MapUtils.getString(criterio, "tipoRango") + "-"
								+ MapUtils.getString(criterio, "paginaInicial")
								+ "-" + MapUtils.getString(criterio, "paginaFinal")
								+ "-" + MapUtils.getString(criterio, "producto"));
				criteria.put("valorNuevo", null);
				criteria.put("codigoUsuario", usuario.getLogin());
				service.insertHistoricoCUV(criteria);
			}

			criteria.put("oidProm", this.oidProm);
			criteria.put("codigoPais", f.getCodigoPais());
			this.preCriteriosList = service.getCriteriosList(criteria);
			dataTableCriteriosList = new DataTableModel(this.preCriteriosList);

			this.calcularcriterio(f);
			this.addInfo("Ok",	this.getResourceMessage("mantenimientoPRECambioCodigoVentaModificaCUVForm.message.criterioEliminadoOk"));
		} catch (Exception ex) {
			addError("Error", obtieneMensajeErrorException(ex));
		}

		f.setCodigoProducto(null);
		f.setPaginaFinal(null);
		f.setPaginaInicial(null);
		f.setDescripcionProducto(null);
		f.setTipoRango(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction
	 * #setValidarConfirmar(java.lang.String)
	 */
	public String setValidarConfirmar(String accion) {
		if (accion.equals("ELIMINAR")){
			if(preCriteriosList.size()==0)
				return this.getResourceMessage("errors.sin.registros");
			if(beanRegistroCriterio==null)
				return this.getResourceMessage("errors.select.item");			
		}

		return null;
	}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void calcularcriterio(ActionEvent event) throws Exception {
		if (log.isDebugEnabled()){
			log.debug("Entering 'procesar' method");
		}
		
		MantenimientoPRECambioCodigoVentaModificaCUVForm f = (MantenimientoPRECambioCodigoVentaModificaCUVForm)this.formBusqueda;
		MantenimientoPRECambioCodigoVentaService service = 
			(MantenimientoPRECambioCodigoVentaService)getBean("spusicc.mantenimientoPRECambioCodigoVentaService");
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		try
		{
			Map criteria =  new HashMap();
			//limpiamos la lista
			this.preComponentesList=new ArrayList();
			//volvemos a llenar la lista
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("codigoPeriodo", this.codigoPeriodo);
			criteria.put("oidOferta", this.oidOferta);
			List listaComponentes = service.getComponentesList(criteria);
			this.preComponentesList=listaComponentes;
			dataTableComponenteList=new DataTableModel(this.preComponentesList);
			
			if(listaComponentes!=null && listaComponentes.size()>0){
				criteria.put("oidPromo", this.oidOferta);
				service.deletePromo(criteria);
				for(int i=0;i<listaComponentes.size();i++){
					Map item = (Map)listaComponentes.get(i);
					criteria.put("oidDetalle", MapUtils.getString(item, "oid"));
					service.insertaPromo(criteria);
				}
			}
			//messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("mantenimientoPRECambioCodigoVentaModificaCUVForm.message.procesoOk", null));
			//saveMessages(request.getSession(), messages);
		}
		catch(Exception ex)
		{			
			addError("Error", obtieneMensajeErrorException(ex));			
		}	
			
	}
	
	/**
	 * @param event
	 */
	public void cargargrupo(ValueChangeEvent event){
		if(log.isDebugEnabled())
			log.debug("Cargando datos de grupo...");
		
		MantenimientoPRECambioCodigoVentaModificaCUVForm editForm = (MantenimientoPRECambioCodigoVentaModificaCUVForm) this.formBusqueda;
		String numeroGrupo = (String) event.getNewValue();
		this.numeroGrupoGlobal = numeroGrupo;
		
		Map grupos = (Map) this.preOfertaGruposMap;
		
		if(grupos == null)
			grupos = new HashMap();
		
		//Guardamos la data del grupo que se esta viendo en pantalla
		guardarGrupoAnterior(editForm, grupos);
		
		//Cargamos la data del nuevo grupo					
		Map nuevoGrupoObj = MapUtils.getMap(grupos, numeroGrupo);
		editForm.setNumGrupo(numeroGrupo);
		editForm.setTipoGrupo(MapUtils.getString(nuevoGrupoObj, "tipoGrupo"));
//		editForm.setOid(MapUtils.getString(nuevoGrupoObj, "indCuadre"));
		editForm.setFactorCuadre(MapUtils.getString(nuevoGrupoObj, "factorCuadre"));
		editForm.setGrupoAnterior(numeroGrupo);
		
		this.mPantallaPrincipalBean.setPreProductoAsociadoGrupoOfertaList((List)MapUtils.getObject(nuevoGrupoObj, "listaProductos"));
		this.mPantallaPrincipalBean.setListaModelPreProductoAsociadoGrupoOfertaList(new DataTableModel(this.mPantallaPrincipalBean.getPreProductoAsociadoGrupoOfertaList()));
		
		this.preOfertaGruposMap = grupos;
		
		//this.flagMostrarGrupos = Constants.ESTADO_ACTIVO;
		//this.obtenerOidTipoEstrategia();
	}
	
	public void cargargrupo(){
		if(log.isDebugEnabled())
			log.debug("Cargando datos de grupo...");
		
		MantenimientoPRECambioCodigoVentaModificaCUVForm editForm = (MantenimientoPRECambioCodigoVentaModificaCUVForm) this.formBusqueda;
		String numeroGrupo = this.numeroGrupoGlobal;
		
		Map grupos = (Map) this.preOfertaGruposMap;
		
		if(grupos == null)
			grupos = new HashMap();
		
		//Guardamos la data del grupo que se esta viendo en pantalla
		guardarGrupoAnterior(editForm, grupos);
		
		//Cargamos la data del nuevo grupo					
		Map nuevoGrupoObj = MapUtils.getMap(grupos, numeroGrupo);
		editForm.setNumGrupo(numeroGrupo);
		editForm.setTipoGrupo(MapUtils.getString(nuevoGrupoObj, "tipoGrupo"));
//		editForm.setOid(MapUtils.getString(nuevoGrupoObj, "indCuadre"));
		editForm.setFactorCuadre(MapUtils.getString(nuevoGrupoObj, "factorCuadre"));
		editForm.setGrupoAnterior(numeroGrupo);
		
		this.mPantallaPrincipalBean.setPreProductoAsociadoGrupoOfertaList((List)MapUtils.getObject(nuevoGrupoObj, "listaProductos"));
		this.mPantallaPrincipalBean.setListaModelPreProductoAsociadoGrupoOfertaList(new DataTableModel(this.mPantallaPrincipalBean.getPreProductoAsociadoGrupoOfertaList()));
		
		this.preOfertaGruposMap = grupos;
		
		//this.flagMostrarGrupos = Constants.ESTADO_ACTIVO;
		//this.obtenerOidTipoEstrategia();
	}
	
	/**
	 * @param editForm
	 * @param grupos
	 */
	private void guardarGrupoAnterior(MantenimientoPRECambioCodigoVentaModificaCUVForm editForm, Map grupos) {
		Map grupoAnterior = new HashMap();
		String id = editForm.getGrupoAnterior();
		
		if(StringUtils.isBlank(id))
			id = Constants.NUMERO_UNO;
			
		//grupoAnterior.put("grupoActual", id);
		//grupoAnterior.put("grupoTipo", editForm.getTipoGrupo());
		//grupoAnterior.put("grupoOidIndicador", editForm.getIndicadorCuadre());
		//grupoAnterior.put("grupoFactorCuadre", editForm.getFactorCuadre());
		grupoAnterior.put("factorCuadre", editForm.getFactorCuadre());
		grupoAnterior.put("tipoGrupo", editForm.getTipoGrupo());
		grupoAnterior.put("indCuadre", editForm.getIndicadorCuadre());
		grupoAnterior.put("oidOferta", MapUtils.getString(grupos, "oidOferta"));
		grupoAnterior.put("listaProductos", this.mPantallaPrincipalBean.getPreProductoAsociadoGrupoOfertaList());
		grupoAnterior.put("numGrupo", id);
		grupoAnterior.put("oidGrupoOferta", MapUtils.getString(grupos, "oidGrupoOferta"));
		
		grupos.put(id, grupoAnterior);
	}
	
	/**
	 * Metodo que elimina una oferta seleccionada de la lista
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void eliminarProductoGrupo(ActionEvent event) {

		try {
			MantenimientoPRECambioCodigoVentaModificaCUVForm f = (MantenimientoPRECambioCodigoVentaModificaCUVForm) this.formBusqueda;
			MantenimientoPRECambioCodigoVentaService service = (MantenimientoPRECambioCodigoVentaService) getBean("spusicc.mantenimientoPRECambioCodigoVentaService");

			Map seleccionado = this.columnasSeleccionadas;
			List productosGrupo = this.mPantallaPrincipalBean.getPreProductoAsociadoGrupoOfertaList();
			boolean eliminado = false;
			
			String oidDetaOfertaSel = MapUtils.getString(seleccionado, "oidDetaOferta");

			if(seleccionado == null){
				throw new Exception("Debe seleccionar un producto de la lista");
			}else{
				if(productosGrupo.size() <= 1){
					throw new Exception("No se puede eliminar, debe haber al menos un producto en el grupo");
				}else{
					for (int i = 0; i < productosGrupo.size(); i++) {
						Map criteriaProdGrup = (Map) productosGrupo.get(i);
						
						if(StringUtils.equals(oidDetaOfertaSel, MapUtils.getString(criteriaProdGrup, "oidDetaOferta"))){
							productosGrupo.remove(i);
							eliminado = true;
							
							Map grupo = MapUtils.getMap(this.preOfertaGruposMap, this.numeroGrupoGlobal);
							List productos = (List) MapUtils.getObject(grupo, "listaProductos");
							for (int j = 0; j < productos.size(); j++) {
								Map prod = (Map) productos.get(j);
								if(StringUtils.isBlank(oidDetaOfertaSel)){
									break;
								}
								
								if(StringUtils.equals(oidDetaOfertaSel, MapUtils.getString(prod, "oidDetaOferta"))){
									prod.put("estado", "E");
									break;
								}
							}
							
							break;
						}
					}
					
					if(eliminado){
						this.mPantallaPrincipalBean.setPreProductoAsociadoGrupoOfertaList(productosGrupo);
						this.mPantallaPrincipalBean.setListaModelPreProductoAsociadoGrupoOfertaList(new DataTableModel(this.mPantallaPrincipalBean.getPreProductoAsociadoGrupoOfertaList()));
						
						throw new Exception(this.getResourceMessage("mantenimientoPRECambioCodigoVentaSearchForm.deleted.producto"));
					}
				}
			}
			
			//buscar(f);
		} catch (Exception e) {
			this.addError("Error", this.obtieneMensajeErrorException(e));
		}
	}
	
	/**
	 * @param event
	 */
	public void openPopupBuscarProductos(ActionEvent event){
		log.debug("Enter method - openPopupBuscarProductos");
		
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			String accion = externalContext.getRequestParameterMap().get("parametroAccion");
			
			MantenimientoPRECambioCodigoVentaModificaCUVForm form = (MantenimientoPRECambioCodigoVentaModificaCUVForm) this.formBusqueda;			
			
			if(StringUtils.equals(accion, "NUMERO_UNO")){
				this.mantenimientoPEDProductoAsociadoSearchAction.setPedOfertaTipoBusquedaSeleccionada(Constants.NUMERO_UNO);			
			}
			
			if(StringUtils.equals(accion, "NUMERO_DOS")){
				this.mantenimientoPEDProductoAsociadoSearchAction.setPedOfertaTipoBusquedaSeleccionada(Constants.NUMERO_DOS);				
			}
			
			if(StringUtils.equals(accion, "BUSCAR_PRODUCTOS_ASOCIADO")){
				this.mantenimientoPEDProductoAsociadoSearchAction.setOidCatalogoSeleccionado(this.oidCatalogo);
				this.mantenimientoPEDProductoAsociadoSearchAction.setSalirCambioCodigoVentaModificaCUV(false);
				this.mantenimientoPEDProductoAsociadoSearchAction.setSalirCambioCodigoVentaModificaCUVCompuestaFija(true);
				this.mantenimientoPEDProductoAsociadoSearchAction.setCodigoPeriodoCambioCodigoVentaModificaCUVCompuestaFija(this.codigoPeriodo);
			}else{
				this.mantenimientoPEDProductoAsociadoSearchAction.setOidCatalogoSeleccionado(this.oidCatalogo);
				this.mantenimientoPEDProductoAsociadoSearchAction.setSalirCambioCodigoVentaModificaCUV(true);
				this.mantenimientoPEDProductoAsociadoSearchAction.setSalirCambioCodigoVentaModificaCUVCompuestaFija(false);
				this.mantenimientoPEDProductoAsociadoSearchAction.setCodigoPeriodoCambioCodigoVentaModificaCUV(this.codigoPeriodo);
			}
			
//			this.mantenimientoPEDProductoAsociadoSearchAction.setPedOfertaCatalogoSeleccionado(form.getOidCatalogo());
//			this.mantenimientoPEDProductoAsociadoSearchAction.setPedOfertaEstrategiaSeleccionada(form.getOidEstrategia());
			this.mantenimientoPEDProductoAsociadoSearchAction.setPedOfertaTipoEstrategiaSeleccionada(this.oidTipoEstrategia);
//			this.mantenimientoPEDProductoAsociadoSearchAction.setPedEstrategiaList(this.pedEstrategiaList);
			
			this.mantenimientoPEDProductoAsociadoSearchAction.inicializarValoresSearch();
			this.redireccionarPagina("mantenimientoPRECambioCodigoVentaModificaCUVPopUpForm");
			
		} catch (Exception e) {
			
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	// ---------------- MODIFICAR PRODUCTO GRUPO -------------------
	public void abrirPopupModificarProductoGrupo(ActionEvent event) throws Exception {
		MantenimientoPREModificaProductoGrupoForm f = new MantenimientoPREModificaProductoGrupoForm();
		MantenimientoPRECambioCodigoVentaModificaCUVForm form = (MantenimientoPRECambioCodigoVentaModificaCUVForm) this.formBusqueda;
		MantenimientoPRECambioCodigoVentaService service = (MantenimientoPRECambioCodigoVentaService) getBean("spusicc.mantenimientoPRECambioCodigoVentaService");

		//resetModificaProductoGrupo(f);

		try {
			// Validamos el ingreso al popup
			if (this.columnasSeleccionadas == null)
				throw new Exception("Debe seleccionar un producto de la lista");
			else{
				Map mapProducto = this.columnasSeleccionadas;
				BeanUtils.copyProperties(f, mapProducto);
				
				if(StringUtils.equals(f.getIndDigitable(), Constants.NUMERO_UNO)){
					f.setIndDigitable("true");
				}else{
					f.setIndDigitable("false");
				}
				
				if(StringUtils.equals(f.getIndImprimible(), Constants.NUMERO_UNO)){
					f.setIndImprimible("true");
				}else{
					f.setIndImprimible("false");
				}
				
				this.formModificaProductoGrupo = f;
				
				//INICIO Validamos CUV Facturado
				Map mapCuvFact = new HashMap();
				Integer cuvFact = service.getExisteCUVFacturado(mapProducto);
				
				if(cuvFact > 0){
					this.booleanCuvFacturado = true;
				}else{
					this.booleanCuvFacturado = false;
				}
				//FIN Validamos CUV Facturado
				
				this.getRequestContext().execute("PF('viewModificaProductoGrupo').show()");
			}
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		}
	}
	
	// ---------------- GUARDAR PRODUCTO GRUPO -------------------
	public void saveModificarProductoGrupo(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'saveModificarProductoGrupo' method");
		}
		
		try {
			MantenimientoPREModificaProductoGrupoForm f = (MantenimientoPREModificaProductoGrupoForm) this.formModificaProductoGrupo;
			MantenimientoPRECambioCodigoVentaService service = (MantenimientoPRECambioCodigoVentaService) getBean("spusicc.mantenimientoPRECambioCodigoVentaService");
			
			Map preOfertaGruposMap = this.preOfertaGruposMap;
			Map seleccionado = this.columnasSeleccionadas;
			List productosGrupo = this.mPantallaPrincipalBean.getPreProductoAsociadoGrupoOfertaList();
			
			String oidDetaOfertaSel = MapUtils.getString(seleccionado, "oidDetaOferta");
			String codSapSel = MapUtils.getString(seleccionado, "codSap");
			
			if(StringUtils.isBlank(f.getFacRepeticion())){
				throw new Exception("Campo 'Factor Repetición' es requerido");
			}
			
			if(StringUtils.isBlank(f.getCodTipoOferta())){
				throw new Exception("Campo 'Tipo Oferta' es requerido");
			}
			
			if(this.noPasaValidacionTipoOfertaGrupo){
				throw new Exception("Elegir un Tipo Oferta que supere las validaciones");
			}
			
			//Obtener OID de Tipo Oferta
			Map mapOidTipoOferta = new HashMap();
			mapOidTipoOferta.put("codTipoOferta", f.getCodTipoOferta());
			BaseOID baseOid = (BaseOID) service.getOidTipoOferta(mapOidTipoOferta).get(0);
			f.setOidTipoOferta(baseOid.getOid().toString());
			f.setDesTipoOferta(baseOid.getDescripcion());
			
			for (int i = 0; i < productosGrupo.size(); i++) {
				Map criteriaProdGrup = (Map) productosGrupo.get(i);
				
				if(StringUtils.isNotBlank(oidDetaOfertaSel)){
					if(StringUtils.equals(oidDetaOfertaSel, MapUtils.getString(criteriaProdGrup, "oidDetaOferta"))){
						this.copiaDatosForm(criteriaProdGrup, f);
						
//							BeanUtils.copyProperties(criteriaProdGrup, f);
//							criteriaProdGrup = BeanUtils.describe(f);
						if(StringUtils.equals(f.getIndDigitable(), "true")){
							criteriaProdGrup.put("indDigitable", Constants.NUMERO_UNO);
						}else{
							criteriaProdGrup.put("indDigitable", Constants.NUMERO_CERO);
						}
						
						if(StringUtils.equals(f.getIndImprimible(), "true")){
							criteriaProdGrup.put("indImprimible", Constants.NUMERO_UNO);
						}else{
							criteriaProdGrup.put("indImprimible", Constants.NUMERO_CERO);
						}
						criteriaProdGrup.put("estado", "M");
						productosGrupo.set(i, criteriaProdGrup);
						
						Map grupo = MapUtils.getMap(preOfertaGruposMap, this.numeroGrupoGlobal);
						List productos = (List) MapUtils.getObject(grupo, "listaProductos");
						for (int j = 0; j < productos.size(); j++) {
							Map prod = (Map) productos.get(j);
							
							if(StringUtils.equals(oidDetaOfertaSel, MapUtils.getString(prod, "oidDetaOferta"))){
								this.copiaDatosForm(prod, f);
								
//									BeanUtils.copyProperties(prod, f);
//									prod = BeanUtils.describe(f);
								if(StringUtils.equals(f.getIndDigitable(), "true")){
									prod.put("indDigitable", Constants.NUMERO_UNO);
								}else{
									prod.put("indDigitable", Constants.NUMERO_CERO);
								}
								
								if(StringUtils.equals(f.getIndImprimible(), "true")){
									prod.put("indImprimible", Constants.NUMERO_UNO);
								}else{
									prod.put("indImprimible", Constants.NUMERO_CERO);
								}
								prod.put("estado", "M");
								break;
							}							
						}
					}
				}else{
					if(StringUtils.equals(codSapSel, MapUtils.getString(criteriaProdGrup, "codSap"))){
						this.copiaDatosForm(criteriaProdGrup, f);
						
//							criteriaProdGrup = BeanUtils.describe(f);
						if(StringUtils.equals(f.getIndDigitable(), "true")){
							criteriaProdGrup.put("indDigitable", Constants.NUMERO_UNO);
						}else{
							criteriaProdGrup.put("indDigitable", Constants.NUMERO_CERO);
						}
						
						if(StringUtils.equals(f.getIndImprimible(), "true")){
							criteriaProdGrup.put("indImprimible", Constants.NUMERO_UNO);
						}else{
							criteriaProdGrup.put("indImprimible", Constants.NUMERO_CERO);
						}
						criteriaProdGrup.put("estado", "M");
						productosGrupo.set(i, criteriaProdGrup);
						
						Map grupo = MapUtils.getMap(preOfertaGruposMap, this.numeroGrupoGlobal);
						List productos = (List) MapUtils.getObject(grupo, "listaProductos");
						for (int j = 0; j < productos.size(); j++) {
							Map prod = (Map) productos.get(j);
							
							if(StringUtils.equals(codSapSel, MapUtils.getString(prod, "codSap"))){
								this.copiaDatosForm(prod, f);
								
//									prod = BeanUtils.describe(f);
								if(StringUtils.equals(f.getIndDigitable(), "true")){
									prod.put("indDigitable", Constants.NUMERO_UNO);
								}else{
									prod.put("indDigitable", Constants.NUMERO_CERO);
								}
								
								if(StringUtils.equals(f.getIndImprimible(), "true")){
									prod.put("indImprimible", Constants.NUMERO_UNO);
								}else{
									prod.put("indImprimible", Constants.NUMERO_CERO);
								}
								prod.put("estado", "A");
								break;
							}							
						}
					}
				}	
			}
				
			this.mPantallaPrincipalBean.setPreProductoAsociadoGrupoOfertaList(productosGrupo);
			this.mPantallaPrincipalBean.setListaModelPreProductoAsociadoGrupoOfertaList(new DataTableModel(this.mPantallaPrincipalBean.getPreProductoAsociadoGrupoOfertaList()));
			this.preOfertaGruposMap = preOfertaGruposMap;
			
			this.addInfo("Ok",	this.getResourceMessage("producto.updated"));
			this.getRequestContext().execute("PF('viewModificaProductoGrupo').hide()");
			
//			if (StringUtils.isNotEmpty(f.getPrecio()))
//				concursoArticuloLote.setPrecioPublico(new BigDecimal(f.getPrecio()));
//			else
//				concursoArticuloLote.setPrecioPublico(new BigDecimal("0.00"));
		} catch (Exception e) {
			this.addError("", this.obtieneMensajeErrorException(e));
		}
	}
	
	public void resetModificaProductoGrupo(MantenimientoPREModificaProductoGrupoForm f) {
		log.debug("Entra a resetModificaProductoGrupo...");
		
		f.setCodigoVenta(null);
		f.setNumPagina(null);
		f.setPreCatalogo(null);
		f.setPreContable(null);
		f.setFacRepeticion(null);
		f.setCodSAP(null);
		f.setValTextoBreve(null);
		f.setIndDigitable(Constants.NUMERO_CERO);
		f.setIndImprimible(Constants.NUMERO_CERO);
		f.setImpCosteEsta(null);
		f.setNumUnidEstim(null);
		f.setImpVenNetaEstim(null);
		f.setTipoOferta(null);
		f.setModificar(false);
	}
	
	private void copiaDatosForm(Map map, MantenimientoPREModificaProductoGrupoForm f){
		log.debug("Entra a copiaDatosForm...");
		
		map.put("codigoVenta", f.getCodigoVenta());
		map.put("valTextoBreve", f.getValTextoBreve());
		map.put("facRepeticion", f.getFacRepeticion());
		map.put("indDigitable", f.getIndDigitable());
		map.put("indImprimible", f.getIndImprimible());
		map.put("preCatalogo", f.getPreCatalogo());
		map.put("preContable", f.getPreContable());
		map.put("impCosteEsta", f.getImpCosteEsta());
		map.put("numUnidEstim", f.getNumUnidEstim());
		map.put("impVenNetaEstim", f.getImpVenNetaEstim());
		map.put("numPagina", f.getNumPagina());
		map.put("oidTipoOferta", f.getOidTipoOferta());
		map.put("desTipoOferta", f.getDesTipoOferta());
		map.put("tipoOferta", f.getTipoOferta());
	}
	
	private void copiaDatosPrincipalForm(Map map, MantenimientoPREModificaProductoPrincipalForm f){
		log.debug("Entra a copiaDatosPrincipalForm...");
		
		map.put("codigoVenta", f.getCodigoVenta());
		map.put("valTextoBreve", f.getValTextoBreve());
		map.put("facRepeticion", f.getFacRepeticion());
		map.put("indDigitable", f.getIndDigitable());
		map.put("indImprimible", f.getIndImprimible());
		map.put("preCatalogo", f.getPreCatalogo());
		map.put("preContable", f.getPreContable());
		map.put("impCosteEsta", f.getImpCosteEsta());
		map.put("numUnidEstim", f.getNumUnidEstim());
		map.put("impVenNetaEstim", f.getImpVenNetaEstim());
		map.put("numPagina", f.getNumPagina());
		map.put("oidTipoOferta", f.getOidTipoOferta());
		map.put("desTipoOferta", f.getDesTipoOferta());
		map.put("tipoOferta", f.getTipoOferta());
	}
	
	private void copiaDatosAsociadoForm(Map map, MantenimientoPREModificaProductoAsociadoForm f){
		log.debug("Entra a copiaDatosAsociadoForm...");
		
		map.put("codigoVenta", f.getCodigoVenta());
		map.put("valTextoBreve", f.getValTextoBreve());
		map.put("facRepeticion", f.getFacRepeticion());
		map.put("indDigitable", f.getIndDigitable());
		map.put("indImprimible", f.getIndImprimible());
		map.put("preCatalogo", f.getPreCatalogo());
		map.put("preContable", f.getPreContable());
		map.put("impCosteEsta", f.getImpCosteEsta());
		map.put("numUnidEstim", f.getNumUnidEstim());
		map.put("impVenNetaEstim", f.getImpVenNetaEstim());
		map.put("numPagina", f.getNumPagina());
		map.put("oidTipoOferta", f.getOidTipoOferta());
		map.put("desTipoOferta", f.getDesTipoOferta());
		map.put("tipoOferta", f.getTipoOferta());
	}
	
	// ---------------- MODIFICAR PRODUCTO PRINCIPAL -------------------
	public void abrirPopupModificarProductoPrincipal(ActionEvent event) throws Exception {
		MantenimientoPREModificaProductoPrincipalForm f = new MantenimientoPREModificaProductoPrincipalForm();
		MantenimientoPRECambioCodigoVentaModificaCUVForm form = (MantenimientoPRECambioCodigoVentaModificaCUVForm) this.formBusqueda;
		MantenimientoPRECambioCodigoVentaService service = (MantenimientoPRECambioCodigoVentaService) getBean("spusicc.mantenimientoPRECambioCodigoVentaService");

		try {
			// Validamos el ingreso al popup
			if (this.columnasSeleccionadasPrincipal == null)
				throw new Exception("Debe seleccionar un producto de la lista");
			else{
				Map mapProducto = this.columnasSeleccionadasPrincipal;
				BeanUtils.copyProperties(f, mapProducto);
				if(StringUtils.equals(f.getIndDigitable(), Constants.NUMERO_UNO)){
					f.setIndDigitable("true");
				}else{
					f.setIndDigitable("false");
				}
				
				if(StringUtils.equals(f.getIndImprimible(), Constants.NUMERO_UNO)){
					f.setIndImprimible("true");
				}else{
					f.setIndImprimible("false");
				}
				
				this.formModificaProductoPrincipal = f;
				
				//INICIO Validamos CUV Facturado
				Map mapCuvFact = new HashMap();
				Integer cuvFact = service.getExisteCUVFacturado(mapProducto);
				
				if(cuvFact > 0){
					this.booleanCuvFacturado = true;
				}else{
					this.booleanCuvFacturado = false;
				}
				//FIN Validamos CUV Facturado
				
				this.getRequestContext().execute("PF('viewModificaProductoPrincipal').show()");
			}
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		}
	}
	
	// ---------------- GUARDAR PRODUCTO PRINCIPAL -------------------
	public void saveModificarProductoPrincipal(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'saveModificarProductoPrincipal' method");
		}
		
		try {
			MantenimientoPREModificaProductoPrincipalForm f = (MantenimientoPREModificaProductoPrincipalForm) this.formModificaProductoPrincipal;
			MantenimientoPRECambioCodigoVentaService service = (MantenimientoPRECambioCodigoVentaService) getBean("spusicc.mantenimientoPRECambioCodigoVentaService");
			
			if(StringUtils.isBlank(f.getFacRepeticion())){
				throw new Exception("Campo 'Factor Repetición' es requerido");
			}
			
			if(StringUtils.isBlank(f.getCodTipoOferta())){
				throw new Exception("Campo 'Tipo Oferta' es requerido");
			}
			
			if(this.noPasaValidacionTipoOfertaPrincipal){
				throw new Exception("Elegir un Tipo Oferta que supere las validaciones");
			}
			
			//Obtener OID de Tipo Oferta
			Map mapOidTipoOferta = new HashMap();
			mapOidTipoOferta.put("codTipoOferta", f.getCodTipoOferta());
			BaseOID baseOid = (BaseOID) service.getOidTipoOferta(mapOidTipoOferta).get(0);
			f.setOidTipoOferta(baseOid.getOid().toString());
			f.setDesTipoOferta(baseOid.getDescripcion());
			
			Map seleccionadoPrincipal = this.columnasSeleccionadasPrincipal;
			List productosPrincipal = this.listPrincipal;
			String oidDetaOfertaSelPrin = MapUtils.getString(seleccionadoPrincipal, "oidDetaOferta");
			
			for (int i = 0; i < productosPrincipal.size(); i++) {
				Map criteriaProdPrin = (Map) productosPrincipal.get(i);
				
				if(StringUtils.equals(oidDetaOfertaSelPrin, MapUtils.getString(criteriaProdPrin, "oidDetaOferta"))){
					this.copiaDatosPrincipalForm(criteriaProdPrin, f);
					
					if(StringUtils.equals(f.getIndDigitable(), "true")){
						criteriaProdPrin.put("indDigitable", Constants.NUMERO_UNO);
					}else{
						criteriaProdPrin.put("indDigitable", Constants.NUMERO_CERO);
					}
					
					if(StringUtils.equals(f.getIndImprimible(), "true")){
						criteriaProdPrin.put("indImprimible", Constants.NUMERO_UNO);
					}else{
						criteriaProdPrin.put("indImprimible", Constants.NUMERO_CERO);
					}
					criteriaProdPrin.put("estado", "M");
					productosPrincipal.set(i, criteriaProdPrin);						
				}
			}
						
			this.addInfo("Ok",	this.getResourceMessage("producto.updated"));
			this.getRequestContext().execute("PF('viewModificaProductoPrincipal').hide()");
			
		} catch (Exception e) {
			this.addError("", this.obtieneMensajeErrorException(e));
		}
	}
	
	// ---------------- MODIFICAR PRODUCTO ASOCIADO -------------------
	public void abrirPopupModificarProductoAsociado(ActionEvent event) throws Exception {
		MantenimientoPREModificaProductoAsociadoForm f = new MantenimientoPREModificaProductoAsociadoForm();
		MantenimientoPRECambioCodigoVentaModificaCUVForm form = (MantenimientoPRECambioCodigoVentaModificaCUVForm) this.formBusqueda;
		MantenimientoPRECambioCodigoVentaService service = (MantenimientoPRECambioCodigoVentaService) getBean("spusicc.mantenimientoPRECambioCodigoVentaService");

		try {
			// Validamos el ingreso al popup
			if (this.columnasSeleccionadasAsociado == null)
				throw new Exception("Debe seleccionar un producto de la lista");
			else{
				Map mapProducto = this.columnasSeleccionadasAsociado;
				BeanUtils.copyProperties(f, mapProducto);
				if(StringUtils.equals(f.getIndDigitable(), Constants.NUMERO_UNO)){
					f.setIndDigitable("true");
				}else{
					f.setIndDigitable("false");
				}
				
				if(StringUtils.equals(f.getIndImprimible(), Constants.NUMERO_UNO)){
					f.setIndImprimible("true");
				}else{
					f.setIndImprimible("false");
				}
				
				this.formModificaProductoAsociado = f;
				
				//INICIO Validamos CUV Facturado
				Map mapCuvFact = new HashMap();
				Integer cuvFact = 0;
				if(StringUtils.isNotBlank(MapUtils.getString(mapProducto, "codigoVenta"))){
					cuvFact = service.getExisteCUVFacturado(mapProducto);
				}
				
				if(cuvFact > 0){
					this.booleanCuvFacturado = true;
					this.booleanIndDigAsociado = true;
					this.booleanIndImpAsociado = true;
				}else{
					this.booleanCuvFacturado = false;
					this.booleanIndDigAsociado = true;
					this.booleanIndImpAsociado = true;
				}
				//FIN Validamos CUV Facturado
				
				this.getRequestContext().execute("PF('viewModificaProductoAsociado').show()");
			}
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		}
	}
	
	// ---------------- GUARDAR PRODUCTO ASOCIADO -------------------
	public void saveModificarProductoAsociado(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'saveModificarProductoAsociado' method");
		}
		
		try {
			MantenimientoPREModificaProductoAsociadoForm f = (MantenimientoPREModificaProductoAsociadoForm) this.formModificaProductoAsociado;
			MantenimientoPRECambioCodigoVentaService service = (MantenimientoPRECambioCodigoVentaService) getBean("spusicc.mantenimientoPRECambioCodigoVentaService");
			
			if(StringUtils.isBlank(f.getFacRepeticion())){
				throw new Exception("Campo 'Factor Repetición' es requerido");
			}
			
			if(StringUtils.isBlank(f.getCodTipoOferta())){
				throw new Exception("Campo 'Tipo Oferta' es requerido");
			}
			
			if(this.noPasaValidacionTipoOfertaAsociado){
				throw new Exception("Elegir un Tipo Oferta que supere las validaciones");
			}
			
			//Obtener OID de Tipo Oferta
			Map mapOidTipoOferta = new HashMap();
			mapOidTipoOferta.put("codTipoOferta", f.getCodTipoOferta());
			BaseOID baseOid = (BaseOID) service.getOidTipoOferta(mapOidTipoOferta).get(0);
			f.setOidTipoOferta(baseOid.getOid().toString());
			f.setDesTipoOferta(baseOid.getDescripcion());
			
			Map seleccionadoAsociado = this.columnasSeleccionadasAsociado;
//			List productosAsociado = this.listAsociado;
			List productosAsociado = this.mPantallaPrincipalBean.getPreProductoAsociadoCompuestaFijaList();
			String oidDetaOfertaSelAsoc = MapUtils.getString(seleccionadoAsociado, "oidDetaOferta");
			String codSapSelAsoc = MapUtils.getString(seleccionadoAsociado, "codSap");
			
			for (int i = 0; i < productosAsociado.size(); i++) {
				Map criteriaProdAsoc = (Map) productosAsociado.get(i);
				
				if(StringUtils.isNotBlank(oidDetaOfertaSelAsoc)){
					if(StringUtils.equals(oidDetaOfertaSelAsoc, MapUtils.getString(criteriaProdAsoc, "oidDetaOferta"))){
						this.copiaDatosAsociadoForm(criteriaProdAsoc, f);
						
						if(StringUtils.equals(f.getIndDigitable(), "true")){
							criteriaProdAsoc.put("indDigitable", Constants.NUMERO_UNO);
						}else{
							criteriaProdAsoc.put("indDigitable", Constants.NUMERO_CERO);
						}
						
						if(StringUtils.equals(f.getIndImprimible(), "true")){
							criteriaProdAsoc.put("indImprimible", Constants.NUMERO_UNO);
						}else{
							criteriaProdAsoc.put("indImprimible", Constants.NUMERO_CERO);
						}
						criteriaProdAsoc.put("estado", "M");
						productosAsociado.set(i, criteriaProdAsoc);
						
						List prodAsociadoGlobal = this.mPantallaPrincipalBean.getPreProductoAsociadoCompuestaFijaGlobalList();
						for (int j = 0; j < prodAsociadoGlobal.size(); j++) {
							Map prod = (Map) prodAsociadoGlobal.get(j);
							
							if(StringUtils.equals(oidDetaOfertaSelAsoc, MapUtils.getString(prod, "oidDetaOferta"))){
								this.copiaDatosAsociadoForm(prod, f);
								
								if(StringUtils.equals(f.getIndDigitable(), "true")){
									prod.put("indDigitable", Constants.NUMERO_UNO);
								}else{
									prod.put("indDigitable", Constants.NUMERO_CERO);
								}
								
								if(StringUtils.equals(f.getIndImprimible(), "true")){
									prod.put("indImprimible", Constants.NUMERO_UNO);
								}else{
									prod.put("indImprimible", Constants.NUMERO_CERO);
								}
								prod.put("estado", "M");
								break;
							}							
						}
					}
				}else{
					if(StringUtils.equals(codSapSelAsoc, MapUtils.getString(criteriaProdAsoc, "codSap"))){
						this.copiaDatosAsociadoForm(criteriaProdAsoc, f);
						
						if(StringUtils.equals(f.getIndDigitable(), "true")){
							criteriaProdAsoc.put("indDigitable", Constants.NUMERO_UNO);
						}else{
							criteriaProdAsoc.put("indDigitable", Constants.NUMERO_CERO);
						}
						
						if(StringUtils.equals(f.getIndImprimible(), "true")){
							criteriaProdAsoc.put("indImprimible", Constants.NUMERO_UNO);
						}else{
							criteriaProdAsoc.put("indImprimible", Constants.NUMERO_CERO);
						}
						criteriaProdAsoc.put("estado", "M");
						productosAsociado.set(i, criteriaProdAsoc);
						
						List prodAsociadoGlobal = this.mPantallaPrincipalBean.getPreProductoAsociadoCompuestaFijaGlobalList();
						for (int j = 0; j < prodAsociadoGlobal.size(); j++) {
							Map prod = (Map) prodAsociadoGlobal.get(j);
							
							if(StringUtils.equals(codSapSelAsoc, MapUtils.getString(prod, "codSap"))){
								this.copiaDatosAsociadoForm(prod, f);
								
								if(StringUtils.equals(f.getIndDigitable(), "true")){
									prod.put("indDigitable", Constants.NUMERO_UNO);
								}else{
									prod.put("indDigitable", Constants.NUMERO_CERO);
								}
								
								if(StringUtils.equals(f.getIndImprimible(), "true")){
									prod.put("indImprimible", Constants.NUMERO_UNO);
								}else{
									prod.put("indImprimible", Constants.NUMERO_CERO);
								}
								prod.put("estado", "A");
								break;
							}							
						}
					}
				}
			}
						
			this.addInfo("Ok",	this.getResourceMessage("producto.updated"));
			this.getRequestContext().execute("PF('viewModificaProductoAsociado').hide()");
			
		} catch (Exception e) {
			this.addError("", this.obtieneMensajeErrorException(e));
		}
	}
	
	// ---------------- ELIMINAR PRODUCTO ASOCIADO -------------------
	public void eliminarProductoAsociado(ActionEvent event) {

		try {
			MantenimientoPRECambioCodigoVentaModificaCUVForm f = (MantenimientoPRECambioCodigoVentaModificaCUVForm) this.formBusqueda;
			MantenimientoPRECambioCodigoVentaService service = (MantenimientoPRECambioCodigoVentaService) getBean("spusicc.mantenimientoPRECambioCodigoVentaService");

			Map seleccionadoAsoc = this.columnasSeleccionadasAsociado;
//			List productosAsoc = this.listAsociado;
			List productosAsoc = this.mPantallaPrincipalBean.getPreProductoAsociadoCompuestaFijaList();
			boolean eliminado = false;
			
			String oidDetaOfertaSel = MapUtils.getString(seleccionadoAsoc, "oidDetaOferta");

			if(seleccionadoAsoc == null){
				throw new Exception("Debe seleccionar un producto de la lista");
			}else{
				if(productosAsoc.size() <= 1){
					throw new Exception("No se puede eliminar, debe haber al menos un producto en la lista");
				}else{
					for (int i = 0; i < productosAsoc.size(); i++) {
						Map criteriaProdAsoc = (Map) productosAsoc.get(i);
						
						if(StringUtils.equals(oidDetaOfertaSel, MapUtils.getString(criteriaProdAsoc, "oidDetaOferta"))){
							productosAsoc.remove(i);
							eliminado = true;
							
//							List productosAsocGlobal = this.listAsociadoGlobal;
							List productosAsocGlobal = this.mPantallaPrincipalBean.getPreProductoAsociadoCompuestaFijaGlobalList();
							for (int j = 0; j < productosAsocGlobal.size(); j++) {
								Map prod = (Map) productosAsocGlobal.get(j);
								if(StringUtils.isBlank(oidDetaOfertaSel)){
									break;
								}
								
								if(StringUtils.equals(oidDetaOfertaSel, MapUtils.getString(prod, "oidDetaOferta"))){
									prod.put("estado", "E");
									break;
								}
							}
							
							break;
						}
					}
				}
			}
			
			if(eliminado){
//				this.listAsociado = productosAsoc;
//				this.dataTableListAsociado = new DataTableModel(this.listAsociado);
				
				this.mPantallaPrincipalBean.setPreProductoAsociadoCompuestaFijaList(productosAsoc);
				this.mPantallaPrincipalBean.setListaModelPreProductoAsociadoCompuestaFijaList(new DataTableModel(this.mPantallaPrincipalBean.getPreProductoAsociadoCompuestaFijaList()));
								
				throw new Exception(this.getResourceMessage("mantenimientoPRECambioCodigoVentaSearchForm.deleted.producto"));
			}
		} catch (Exception e) {
			this.addError("Error", this.obtieneMensajeErrorException(e));
		}
	}
	
	/**
	 * @param event
	 */
	public void validarTipoOfertaIndividual(ValueChangeEvent val){
		log.debug("Enter method - validarTipoOfertaIndividual");
		
		try {
			MantenimientoPRECambioCodigoVentaModificaCUVForm editForm = (MantenimientoPRECambioCodigoVentaModificaCUVForm) this.formBusqueda;
			MantenimientoPRECambioCodigoVentaService service = (MantenimientoPRECambioCodigoVentaService) getBean("spusicc.mantenimientoPRECambioCodigoVentaService");
			AjaxService ajax = (AjaxService)getBean("ajaxService");
			
			editForm.setTipoOferta(val.getNewValue().toString());
			
			//Obtener OID de Tipo Oferta
			Map mapOidTipoOferta = new HashMap();
			mapOidTipoOferta.put("codTipoOferta", editForm.getTipoOferta());
			BaseOID baseOid = (BaseOID) service.getOidTipoOferta(mapOidTipoOferta).get(0);
			
			List listEstrategias = service.getEstrategiaList(mapOidTipoOferta);
			
			String oidEstrategia = "";
			for (int i = 0; i < listEstrategias.size(); i++) {
				Base base = (Base) listEstrategias.get(i);
				
				if(StringUtils.equals((String) this.data.get("desEstrategia"), base.getDescripcion())){
					oidEstrategia = base.getCodigo();
					break;
				}
			}
			
			String oidTipoOferta = baseOid.getOid().toString();
			String codigoSap = this.codSap;
			String precioCatalogo = editForm.getPreCatalogo();
			String precioPosicionamiento = editForm.getPreContable();
			
			String data = ajax.validarTipoOferta(oidTipoOferta, codigoSap, oidEstrategia, precioCatalogo, precioPosicionamiento);	
			
			if(data != ""){
				this.noPasaValidacionTipoOfertaIndividual = true;
				throw new Exception(data);
			}else{
				this.noPasaValidacionTipoOfertaIndividual = false;
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}		
	}
	
	/**
	 * @param event
	 */
	public void validarTipoOfertaGrupo(ValueChangeEvent val){
		log.debug("Enter method - validarTipoOfertaGrupo");
		
		try {
			MantenimientoPREModificaProductoGrupoForm editForm = (MantenimientoPREModificaProductoGrupoForm) this.formModificaProductoGrupo;
			MantenimientoPRECambioCodigoVentaService service = (MantenimientoPRECambioCodigoVentaService) getBean("spusicc.mantenimientoPRECambioCodigoVentaService");
			AjaxService ajax = (AjaxService)getBean("ajaxService");
			
			editForm.setTipoOferta(val.getNewValue().toString());
			
			//Obtener OID de Tipo Oferta
			Map mapOidTipoOferta = new HashMap();
			mapOidTipoOferta.put("codTipoOferta", editForm.getTipoOferta());
			BaseOID baseOid = (BaseOID) service.getOidTipoOferta(mapOidTipoOferta).get(0);
			
			List listEstrategias = service.getEstrategiaList(mapOidTipoOferta);
			
			String oidEstrategia = "";
			for (int i = 0; i < listEstrategias.size(); i++) {
				Base base = (Base) listEstrategias.get(i);
				
				if(StringUtils.equals((String) this.data.get("desEstrategia"), base.getDescripcion())){
					oidEstrategia = base.getCodigo();
					break;
				}
			}
			
			String oidTipoOferta = baseOid.getOid().toString();
			String codigoSap = editForm.getCodSap();
			String precioCatalogo = editForm.getPreCatalogo();
			String precioPosicionamiento = editForm.getPreContable();
			
			String data = ajax.validarTipoOferta(oidTipoOferta, codigoSap, oidEstrategia, precioCatalogo, precioPosicionamiento);	
			
			if(data != ""){
				this.noPasaValidacionTipoOfertaGrupo = true;
				throw new Exception(data);
			}else{
				this.noPasaValidacionTipoOfertaGrupo = false;
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}		
	}
	
	/**
	 * @param event
	 */
	public void validarTipoOfertaPrincipal(ValueChangeEvent val){
		log.debug("Enter method - validarTipoOfertaPrincipal");
		
		try {
			MantenimientoPREModificaProductoPrincipalForm editForm = (MantenimientoPREModificaProductoPrincipalForm) this.formModificaProductoPrincipal;
			MantenimientoPRECambioCodigoVentaService service = (MantenimientoPRECambioCodigoVentaService) getBean("spusicc.mantenimientoPRECambioCodigoVentaService");
			AjaxService ajax = (AjaxService)getBean("ajaxService");
			
			editForm.setTipoOferta(val.getNewValue().toString());
			
			//Obtener OID de Tipo Oferta
			Map mapOidTipoOferta = new HashMap();
			mapOidTipoOferta.put("codTipoOferta", editForm.getTipoOferta());
			BaseOID baseOid = (BaseOID) service.getOidTipoOferta(mapOidTipoOferta).get(0);
			
			List listEstrategias = service.getEstrategiaList(mapOidTipoOferta);
			
			String oidEstrategia = "";
			for (int i = 0; i < listEstrategias.size(); i++) {
				Base base = (Base) listEstrategias.get(i);
				
				if(StringUtils.equals((String) this.data.get("desEstrategia"), base.getDescripcion())){
					oidEstrategia = base.getCodigo();
					break;
				}
			}
			
			String oidTipoOferta = baseOid.getOid().toString();
			String codigoSap = editForm.getCodSap();
			String precioCatalogo = editForm.getPreCatalogo();
			String precioPosicionamiento = editForm.getPreContable();
			
			String data = ajax.validarTipoOferta(oidTipoOferta, codigoSap, oidEstrategia, precioCatalogo, precioPosicionamiento);	
			
			if(data != ""){
				this.noPasaValidacionTipoOfertaPrincipal = true;
				throw new Exception(data);
			}else{
				this.noPasaValidacionTipoOfertaPrincipal = false;
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}		
	}
	
	/**
	 * @param event
	 */
	public void validarTipoOfertaAsociado(ValueChangeEvent val){
		log.debug("Enter method - validarTipoOfertaAsociado");
		
		try {
			MantenimientoPREModificaProductoAsociadoForm editForm = (MantenimientoPREModificaProductoAsociadoForm) this.formModificaProductoAsociado;
			MantenimientoPRECambioCodigoVentaService service = (MantenimientoPRECambioCodigoVentaService) getBean("spusicc.mantenimientoPRECambioCodigoVentaService");
			AjaxService ajax = (AjaxService)getBean("ajaxService");
			
			editForm.setTipoOferta(val.getNewValue().toString());
			
			//Obtener OID de Tipo Oferta
			Map mapOidTipoOferta = new HashMap();
			mapOidTipoOferta.put("codTipoOferta", editForm.getTipoOferta());
			BaseOID baseOid = (BaseOID) service.getOidTipoOferta(mapOidTipoOferta).get(0);
			
			List listEstrategias = service.getEstrategiaList(mapOidTipoOferta);
			
			String oidEstrategia = "";
			for (int i = 0; i < listEstrategias.size(); i++) {
				Base base = (Base) listEstrategias.get(i);
				
				if(StringUtils.equals((String) this.data.get("desEstrategia"), base.getDescripcion())){
					oidEstrategia = base.getCodigo();
					break;
				}
			}
			
			String oidTipoOferta = baseOid.getOid().toString();
			String codigoSap = editForm.getCodSap();
			String precioCatalogo = editForm.getPreCatalogo();
			String precioPosicionamiento = editForm.getPreContable();
			
			String data = ajax.validarTipoOferta(oidTipoOferta, codigoSap, oidEstrategia, precioCatalogo, precioPosicionamiento);	
			
			if(data != ""){
				this.noPasaValidacionTipoOfertaAsociado = true;
				throw new Exception(data);
			}else{
				this.noPasaValidacionTipoOfertaAsociado = false;
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}		
	}
	
	/**
	 * Metodo que elimina un grupo seleccionado
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void eliminarGrupo(ActionEvent event) {
		try {
			MantenimientoPRECambioCodigoVentaModificaCUVForm f = (MantenimientoPRECambioCodigoVentaModificaCUVForm) this.formBusqueda;
			MantenimientoPRECambioCodigoVentaService service = (MantenimientoPRECambioCodigoVentaService) getBean("spusicc.mantenimientoPRECambioCodigoVentaService");

			Map seleccionado = this.columnasSeleccionadas;
			List productosGrupo = this.mPantallaPrincipalBean.getPreProductoAsociadoGrupoOfertaList();
			boolean eliminado = false;
			
			String oidDetaOfertaSel = MapUtils.getString(seleccionado, "oidDetaOferta");

			if(seleccionado == null){
				throw new Exception("Debe seleccionar un producto de la lista");
			}else{
				if(productosGrupo.size() <= 1){
					throw new Exception("No se puede eliminar, debe haber al menos un producto en el grupo");
				}else{
					for (int i = 0; i < productosGrupo.size(); i++) {
						Map criteriaProdGrup = (Map) productosGrupo.get(i);
						
						if(StringUtils.equals(oidDetaOfertaSel, MapUtils.getString(criteriaProdGrup, "oidDetaOferta"))){
							productosGrupo.remove(i);
							eliminado = true;
							
							Map grupo = MapUtils.getMap(this.preOfertaGruposMap, this.numeroGrupoGlobal);
							List productos = (List) MapUtils.getObject(grupo, "listaProductos");
							for (int j = 0; j < productos.size(); j++) {
								Map prod = (Map) productos.get(j);
								if(StringUtils.isBlank(oidDetaOfertaSel)){
									break;
								}
								
								if(StringUtils.equals(oidDetaOfertaSel, MapUtils.getString(prod, "oidDetaOferta"))){
									prod.put("estado", "E");
									break;
								}
							}
							
							break;
						}
					}
					
					if(eliminado){
						this.mPantallaPrincipalBean.setPreProductoAsociadoGrupoOfertaList(productosGrupo);
						this.mPantallaPrincipalBean.setListaModelPreProductoAsociadoGrupoOfertaList(new DataTableModel(this.mPantallaPrincipalBean.getPreProductoAsociadoGrupoOfertaList()));
						
						throw new Exception(this.getResourceMessage("mantenimientoPRECambioCodigoVentaSearchForm.deleted.producto"));
					}
				}
			}
			
			//buscar(f);
		} catch (Exception e) {
			this.addError("Error", this.obtieneMensajeErrorException(e));
		}
	}
	
	/**
	 * @param val
	 */
	public void modificarOfertaCerrada(ValueChangeEvent val){
		if(log.isDebugEnabled())
			log.debug("modificarOfertaCerrada");
		
		MantenimientoPRECambioCodigoVentaModificaCUVForm editForm = (MantenimientoPRECambioCodigoVentaModificaCUVForm) this.formBusqueda;
		
		this.modificaOfertaCerrada = ((Boolean)val.getNewValue()).booleanValue();
		
		if(this.modificaOfertaCerrada){
			this.indicadorModificaOfertaCerrada = 0;
			this.mostrarBotonSave = true;
		}else{
			this.indicadorModificaOfertaCerrada = 1;
			this.mostrarBotonSave = false;
		}
	}
	
	public void eliminarOfertaIndividual(ActionEvent event){
		if(log.isDebugEnabled())
			log.debug("eliminarOfertaIndividual");
		
		try {
			MantenimientoPRECambioCodigoVentaService service = (MantenimientoPRECambioCodigoVentaService) getBean("spusicc.mantenimientoPRECambioCodigoVentaService");
			MantenimientoPRECambioCodigoVentaSearchAction action = findManageBean("mantenimientoPRECambioCodigoVentaSearchAction");
			
			Map criteria = new HashMap();
			criteria.put("oidDetaOferta", this.oidDetaOferta);
			criteria.put("oidOferta", this.oidOferta);
			
			service.deleteProductoGrupoOfertaDetalle(criteria);
			service.deleteOfertaIndividual(criteria);
			
			action.find();
			this.redireccionarPagina("mantenimientoPRECambioCodigoVentaList");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the data
	 */
	public Map getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(Map data) {
		this.data = data;
	}

	/**
	 * @return the oidOferta
	 */
	public String getOidOferta() {
		return oidOferta;
	}

	/**
	 * @param oidOferta
	 *            the oidOferta to set
	 */
	public void setOidOferta(String oidOferta) {
		this.oidOferta = oidOferta;
	}

	/**
	 * @return the oidDetaOferta
	 */
	public String getOidDetaOferta() {
		return oidDetaOferta;
	}

	/**
	 * @param oidDetaOferta
	 *            the oidDetaOferta to set
	 */
	public void setOidDetaOferta(String oidDetaOferta) {
		this.oidDetaOferta = oidDetaOferta;
	}

	/**
	 * @return the oidCatalogo
	 */
	public String getOidCatalogo() {
		return oidCatalogo;
	}

	/**
	 * @param oidCatalogo
	 *            the oidCatalogo to set
	 */
	public void setOidCatalogo(String oidCatalogo) {
		this.oidCatalogo = oidCatalogo;
	}

	/**
	 * @return the periodoActivo
	 */
	public String getPeriodoActivo() {
		return periodoActivo;
	}

	/**
	 * @param periodoActivo
	 *            the periodoActivo to set
	 */
	public void setPeriodoActivo(String periodoActivo) {
		this.periodoActivo = periodoActivo;
	}

	/**
	 * @return the oidGrupoOferta
	 */
	public String getOidGrupoOferta() {
		return oidGrupoOferta;
	}

	/**
	 * @param oidGrupoOferta
	 *            the oidGrupoOferta to set
	 */
	public void setOidGrupoOferta(String oidGrupoOferta) {
		this.oidGrupoOferta = oidGrupoOferta;
	}

	/**
	 * @return the numeroGrupo
	 */
	public String getNumeroGrupo() {
		return numeroGrupo;
	}

	/**
	 * @param numeroGrupo
	 *            the numeroGrupo to set
	 */
	public void setNumeroGrupo(String numeroGrupo) {
		this.numeroGrupo = numeroGrupo;
	}

	/**
	 * @return the tipoGrupo
	 */
	public String getTipoGrupo() {
		return tipoGrupo;
	}

	/**
	 * @param tipoGrupo
	 *            the tipoGrupo to set
	 */
	public void setTipoGrupo(String tipoGrupo) {
		this.tipoGrupo = tipoGrupo;
	}

	/**
	 * @return the oidTipoEstrategia
	 */
	public String getOidTipoEstrategia() {
		return oidTipoEstrategia;
	}

	/**
	 * @param oidTipoEstrategia
	 *            the oidTipoEstrategia to set
	 */
	public void setOidTipoEstrategia(String oidTipoEstrategia) {
		this.oidTipoEstrategia = oidTipoEstrategia;
	}

	/**
	 * @return the oidProm
	 */
	public String getOidProm() {
		return oidProm;
	}

	/**
	 * @param oidProm
	 *            the oidProm to set
	 */
	public void setOidProm(String oidProm) {
		this.oidProm = oidProm;
	}

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo
	 *            the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the indicadorCuadreGrupoAnterior
	 */
	public String getIndicadorCuadreGrupoAnterior() {
		return indicadorCuadreGrupoAnterior;
	}

	/**
	 * @param indicadorCuadreGrupoAnterior
	 *            the indicadorCuadreGrupoAnterior to set
	 */
	public void setIndicadorCuadreGrupoAnterior(
			String indicadorCuadreGrupoAnterior) {
		this.indicadorCuadreGrupoAnterior = indicadorCuadreGrupoAnterior;
	}

	/**
	 * @return the factorCuadreGrupoAnterior
	 */
	public String getFactorCuadreGrupoAnterior() {
		return factorCuadreGrupoAnterior;
	}

	/**
	 * @param factorCuadreGrupoAnterior
	 *            the factorCuadreGrupoAnterior to set
	 */
	public void setFactorCuadreGrupoAnterior(String factorCuadreGrupoAnterior) {
		this.factorCuadreGrupoAnterior = factorCuadreGrupoAnterior;
	}

	/**
	 * @return the tipoCuadreCondicionAnterior
	 */
	public String getTipoCuadreCondicionAnterior() {
		return tipoCuadreCondicionAnterior;
	}

	/**
	 * @param tipoCuadreCondicionAnterior
	 *            the tipoCuadreCondicionAnterior to set
	 */
	public void setTipoCuadreCondicionAnterior(
			String tipoCuadreCondicionAnterior) {
		this.tipoCuadreCondicionAnterior = tipoCuadreCondicionAnterior;
	}

	/**
	 * @return the factorCuadreCondicionAnterior
	 */
	public String getFactorCuadreCondicionAnterior() {
		return factorCuadreCondicionAnterior;
	}

	/**
	 * @param factorCuadreCondicionAnterior
	 *            the factorCuadreCondicionAnterior to set
	 */
	public void setFactorCuadreCondicionAnterior(
			String factorCuadreCondicionAnterior) {
		this.factorCuadreCondicionAnterior = factorCuadreCondicionAnterior;
	}

	/**
	 * @return the preIndicadorCuadreList
	 */
	public List getPreIndicadorCuadreList() {
		return preIndicadorCuadreList;
	}

	/**
	 * @param preIndicadorCuadreList
	 *            the preIndicadorCuadreList to set
	 */
	public void setPreIndicadorCuadreList(List preIndicadorCuadreList) {
		this.preIndicadorCuadreList = preIndicadorCuadreList;
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
	 * @return the cantidadRegistros
	 */
	public int getCantidadRegistros() {
		return cantidadRegistros;
	}

	/**
	 * @param cantidadRegistros
	 *            the cantidadRegistros to set
	 */
	public void setCantidadRegistros(int cantidadRegistros) {
		this.cantidadRegistros = cantidadRegistros;
	}

	/**
	 * @return the preTipoCuadreList
	 */
	public List getPreTipoCuadreList() {
		return preTipoCuadreList;
	}

	/**
	 * @param preTipoCuadreList
	 *            the preTipoCuadreList to set
	 */
	public void setPreTipoCuadreList(List preTipoCuadreList) {
		this.preTipoCuadreList = preTipoCuadreList;
	}

	/**
	 * @return the preCriteriosList
	 */
	public List getPreCriteriosList() {
		return preCriteriosList;
	}

	/**
	 * @param preCriteriosList
	 *            the preCriteriosList to set
	 */
	public void setPreCriteriosList(List preCriteriosList) {
		this.preCriteriosList = preCriteriosList;
	}

	/**
	 * @return the preCatalogosList
	 */
	public List getPreCatalogosList() {
		return preCatalogosList;
	}

	/**
	 * @param preCatalogosList
	 *            the preCatalogosList to set
	 */
	public void setPreCatalogosList(List preCatalogosList) {
		this.preCatalogosList = preCatalogosList;
	}

	/**
	 * @return the preComponentesList
	 */
	public List getPreComponentesList() {
		return preComponentesList;
	}

	/**
	 * @param preComponentesList
	 *            the preComponentesList to set
	 */
	public void setPreComponentesList(List preComponentesList) {
		this.preComponentesList = preComponentesList;
	}

	/**
	 * @return the indicadorCodigoVenta
	 */
	public boolean isIndicadorCodigoVenta() {
		return indicadorCodigoVenta;
	}

	/**
	 * @param indicadorCodigoVenta
	 *            the indicadorCodigoVenta to set
	 */
	public void setIndicadorCodigoVenta(boolean indicadorCodigoVenta) {
		this.indicadorCodigoVenta = indicadorCodigoVenta;
	}

	/**
	 * @return the indicadorNumeroPagina
	 */
	public boolean isIndicadorNumeroPagina() {
		return indicadorNumeroPagina;
	}

	/**
	 * @param indicadorNumeroPagina
	 *            the indicadorNumeroPagina to set
	 */
	public void setIndicadorNumeroPagina(boolean indicadorNumeroPagina) {
		this.indicadorNumeroPagina = indicadorNumeroPagina;
	}

	/**
	 * @return the indicadorPrecioCatalogo
	 */
	public boolean isIndicadorPrecioCatalogo() {
		return indicadorPrecioCatalogo;
	}

	/**
	 * @param indicadorPrecioCatalogo
	 *            the indicadorPrecioCatalogo to set
	 */
	public void setIndicadorPrecioCatalogo(boolean indicadorPrecioCatalogo) {
		this.indicadorPrecioCatalogo = indicadorPrecioCatalogo;
	}

	/**
	 * @return the indicadorPrecioContable
	 */
	public boolean isIndicadorPrecioContable() {
		return indicadorPrecioContable;
	}

	/**
	 * @param indicadorPrecioContable
	 *            the indicadorPrecioContable to set
	 */
	public void setIndicadorPrecioContable(boolean indicadorPrecioContable) {
		this.indicadorPrecioContable = indicadorPrecioContable;
	}

	/**
	 * @return the indicadorFactorRepeticion
	 */
	public boolean isIndicadorFactorRepeticion() {
		return indicadorFactorRepeticion;
	}

	/**
	 * @param indicadorFactorRepeticion
	 *            the indicadorFactorRepeticion to set
	 */
	public void setIndicadorFactorRepeticion(boolean indicadorFactorRepeticion) {
		this.indicadorFactorRepeticion = indicadorFactorRepeticion;
	}

	/**
	 * @return the idCuv
	 */
	public boolean isIdCuv() {
		return idCuv;
	}

	/**
	 * @param idCuv
	 *            the idCuv to set
	 */
	public void setIdCuv(boolean idCuv) {
		this.idCuv = idCuv;
	}

	/**
	 * @return the idCatalogo
	 */
	public boolean isIdCatalogo() {
		return idCatalogo;
	}

	/**
	 * @param idCatalogo
	 *            the idCatalogo to set
	 */
	public void setIdCatalogo(boolean idCatalogo) {
		this.idCatalogo = idCatalogo;
	}

	/**
	 * @return the idFactor
	 */
	public boolean isIdFactor() {
		return idFactor;
	}

	/**
	 * @param idFactor
	 *            the idFactor to set
	 */
	public void setIdFactor(boolean idFactor) {
		this.idFactor = idFactor;
	}

	/**
	 * @return the formCodigoVenta
	 */
	public MantenimientoPRECambioCodigoVentaSearchForm getFormCodigoVenta() {
		return formCodigoVenta;
	}

	/**
	 * @param formCodigoVenta
	 *            the formCodigoVenta to set
	 */
	public void setFormCodigoVenta(
			MantenimientoPRECambioCodigoVentaSearchForm formCodigoVenta) {
		this.formCodigoVenta = formCodigoVenta;
	}

	/**
	 * @return the dataTableCriteriosList
	 */
	public DataTableModel getDataTableCriteriosList() {
		return dataTableCriteriosList;
	}

	/**
	 * @param dataTableCriteriosList
	 *            the dataTableCriteriosList to set
	 */
	public void setDataTableCriteriosList(DataTableModel dataTableCriteriosList) {
		this.dataTableCriteriosList = dataTableCriteriosList;
	}

	/**
	 * @return the busquedaProductoSearchAction
	 */
	public BusquedaProductoSearchAction getBusquedaProductoSearchAction() {
		return busquedaProductoSearchAction;
	}

	/**
	 * @param busquedaProductoSearchAction
	 *            the busquedaProductoSearchAction to set
	 */
	public void setBusquedaProductoSearchAction(
			BusquedaProductoSearchAction busquedaProductoSearchAction) {
		this.busquedaProductoSearchAction = busquedaProductoSearchAction;
	}

	/**
	 * @return the mostrarPopupProducto
	 */
	public boolean isMostrarPopupProducto() {
		return mostrarPopupProducto;
	}

	/**
	 * @param mostrarPopupProducto
	 *            the mostrarPopupProducto to set
	 */
	public void setMostrarPopupProducto(boolean mostrarPopupProducto) {
		this.mostrarPopupProducto = mostrarPopupProducto;
	}

	/**
	 * @return the popupSacproducto
	 */
	public static String getPopupSacproducto() {
		return POPUP_SACPRODUCTO;
	}

	/**
	 * @return the indicadorExclusion
	 */
	public boolean isIndicadorExclusion() {
		return indicadorExclusion;
	}

	/**
	 * @param indicadorExclusion
	 *            the indicadorExclusion to set
	 */
	public void setIndicadorExclusion(boolean indicadorExclusion) {
		this.indicadorExclusion = indicadorExclusion;
	}

	/**
	 * @return the beanRegistroCriterio
	 */
	public Object getBeanRegistroCriterio() {
		return beanRegistroCriterio;
	}

	/**
	 * @param beanRegistroCriterio
	 *            the beanRegistroCriterio to set
	 */
	public void setBeanRegistroCriterio(Object beanRegistroCriterio) {
		this.beanRegistroCriterio = beanRegistroCriterio;
	}

	/**
	 * @return the dataTableComponenteList
	 */
	public DataTableModel getDataTableComponenteList() {
		return dataTableComponenteList;
	}

	/**
	 * @param dataTableComponenteList the dataTableComponenteList to set
	 */
	public void setDataTableComponenteList(DataTableModel dataTableComponenteList) {
		this.dataTableComponenteList = dataTableComponenteList;
	}

	/**
	 * @return the siccCatalagoList
	 */
	public List getSiccCatalagoList() {
		return siccCatalagoList;
	}

	/**
	 * @param siccCatalagoList the siccCatalagoList to set
	 */
	public void setSiccCatalagoList(List siccCatalagoList) {
		this.siccCatalagoList = siccCatalagoList;
	}

	/**
	 * @return the siccTipoOfertaList
	 */
	public List getSiccTipoOfertaList() {
		return siccTipoOfertaList;
	}

	/**
	 * @param siccTipoOfertaList the siccTipoOfertaList to set
	 */
	public void setSiccTipoOfertaList(List siccTipoOfertaList) {
		this.siccTipoOfertaList = siccTipoOfertaList;
	}

	/**
	 * @return the codTipoOferta
	 */
	public String getCodTipoOferta() {
		return codTipoOferta;
	}

	/**
	 * @param codTipoOferta the codTipoOferta to set
	 */
	public void setCodTipoOferta(String codTipoOferta) {
		this.codTipoOferta = codTipoOferta;
	}

	/**
	 * @return the indicadorValTextoBreve
	 */
	public boolean isIndicadorValTextoBreve() {
		return indicadorValTextoBreve;
	}

	/**
	 * @param indicadorValTextoBreve the indicadorValTextoBreve to set
	 */
	public void setIndicadorValTextoBreve(boolean indicadorValTextoBreve) {
		this.indicadorValTextoBreve = indicadorValTextoBreve;
	}

	/**
	 * @return the indicadorIndDigitable
	 */
	public boolean isIndicadorIndDigitable() {
		return indicadorIndDigitable;
	}

	/**
	 * @param indicadorIndDigitable the indicadorIndDigitable to set
	 */
	public void setIndicadorIndDigitable(boolean indicadorIndDigitable) {
		this.indicadorIndDigitable = indicadorIndDigitable;
	}

	/**
	 * @return the indicadorIndImprimible
	 */
	public boolean isIndicadorIndImprimible() {
		return indicadorIndImprimible;
	}

	/**
	 * @param indicadorIndImprimible the indicadorIndImprimible to set
	 */
	public void setIndicadorIndImprimible(boolean indicadorIndImprimible) {
		this.indicadorIndImprimible = indicadorIndImprimible;
	}

	/**
	 * @return the indicadorImpCosteEsta
	 */
	public boolean isIndicadorImpCosteEsta() {
		return indicadorImpCosteEsta;
	}

	/**
	 * @param indicadorImpCosteEsta the indicadorImpCosteEsta to set
	 */
	public void setIndicadorImpCosteEsta(boolean indicadorImpCosteEsta) {
		this.indicadorImpCosteEsta = indicadorImpCosteEsta;
	}

	/**
	 * @return the indicadorNumUnidEstim
	 */
	public boolean isIndicadorNumUnidEstim() {
		return indicadorNumUnidEstim;
	}

	/**
	 * @param indicadorNumUnidEstim the indicadorNumUnidEstim to set
	 */
	public void setIndicadorNumUnidEstim(boolean indicadorNumUnidEstim) {
		this.indicadorNumUnidEstim = indicadorNumUnidEstim;
	}

	/**
	 * @return the indicadorImpVenNetaEstim
	 */
	public boolean isIndicadorImpVenNetaEstim() {
		return indicadorImpVenNetaEstim;
	}

	/**
	 * @param indicadorImpVenNetaEstim the indicadorImpVenNetaEstim to set
	 */
	public void setIndicadorImpVenNetaEstim(boolean indicadorImpVenNetaEstim) {
		this.indicadorImpVenNetaEstim = indicadorImpVenNetaEstim;
	}

	/**
	 * @return the idValTextoBreve
	 */
	public boolean isIdValTextoBreve() {
		return idValTextoBreve;
	}

	/**
	 * @param idValTextoBreve the idValTextoBreve to set
	 */
	public void setIdValTextoBreve(boolean idValTextoBreve) {
		this.idValTextoBreve = idValTextoBreve;
	}

	/**
	 * @return the idIndDigitable
	 */
	public boolean isIdIndDigitable() {
		return idIndDigitable;
	}

	/**
	 * @param idIndDigitable the idIndDigitable to set
	 */
	public void setIdIndDigitable(boolean idIndDigitable) {
		this.idIndDigitable = idIndDigitable;
	}

	/**
	 * @return the idIndImprimible
	 */
	public boolean isIdIndImprimible() {
		return idIndImprimible;
	}

	/**
	 * @param idIndImprimible the idIndImprimible to set
	 */
	public void setIdIndImprimible(boolean idIndImprimible) {
		this.idIndImprimible = idIndImprimible;
	}

	/**
	 * @return the idImpCosteEsta
	 */
	public boolean isIdImpCosteEsta() {
		return idImpCosteEsta;
	}

	/**
	 * @param idImpCosteEsta the idImpCosteEsta to set
	 */
	public void setIdImpCosteEsta(boolean idImpCosteEsta) {
		this.idImpCosteEsta = idImpCosteEsta;
	}

	/**
	 * @return the idNumUnidEstim
	 */
	public boolean isIdNumUnidEstim() {
		return idNumUnidEstim;
	}

	/**
	 * @param idNumUnidEstim the idNumUnidEstim to set
	 */
	public void setIdNumUnidEstim(boolean idNumUnidEstim) {
		this.idNumUnidEstim = idNumUnidEstim;
	}

	/**
	 * @return the idImpVenNetaEstim
	 */
	public boolean isIdImpVenNetaEstim() {
		return idImpVenNetaEstim;
	}

	/**
	 * @param idImpVenNetaEstim the idImpVenNetaEstim to set
	 */
	public void setIdImpVenNetaEstim(boolean idImpVenNetaEstim) {
		this.idImpVenNetaEstim = idImpVenNetaEstim;
	}

	/**
	 * @return the codSap
	 */
	public String getCodSap() {
		return codSap;
	}

	/**
	 * @param codSap the codSap to set
	 */
	public void setCodSap(String codSap) {
		this.codSap = codSap;
	}

	/**
	 * @return the indicadorTipoOferta
	 */
	public boolean isIndicadorTipoOferta() {
		return indicadorTipoOferta;
	}

	/**
	 * @param indicadorTipoOferta the indicadorTipoOferta to set
	 */
	public void setIndicadorTipoOferta(boolean indicadorTipoOferta) {
		this.indicadorTipoOferta = indicadorTipoOferta;
	}

	/**
	 * @return the idTipoOferta
	 */
	public boolean isIdTipoOferta() {
		return idTipoOferta;
	}

	/**
	 * @param idTipoOferta the idTipoOferta to set
	 */
	public void setIdTipoOferta(boolean idTipoOferta) {
		this.idTipoOferta = idTipoOferta;
	}

	/**
	 * @return the listPanelGrupos
	 */
	public List getListPanelGrupos() {
		return listPanelGrupos;
	}

	/**
	 * @param listPanelGrupos the listPanelGrupos to set
	 */
	public void setListPanelGrupos(List listPanelGrupos) {
		this.listPanelGrupos = listPanelGrupos;
	}

	/**
	 * @return the columnasSeleccionadas
	 */
	public Map getColumnasSeleccionadas() {
		return columnasSeleccionadas;
	}

	/**
	 * @param columnasSeleccionadas the columnasSeleccionadas to set
	 */
	public void setColumnasSeleccionadas(Map columnasSeleccionadas) {
		this.columnasSeleccionadas = columnasSeleccionadas;
	}

	/**
	 * @return the preOfertaGruposMap
	 */
	public Map getPreOfertaGruposMap() {
		return preOfertaGruposMap;
	}

	/**
	 * @param preOfertaGruposMap the preOfertaGruposMap to set
	 */
	public void setPreOfertaGruposMap(Map preOfertaGruposMap) {
		this.preOfertaGruposMap = preOfertaGruposMap;
	}

	/**
	 * @return the mantenimientoPEDProductoAsociadoSearchAction
	 */
	public MantenimientoPEDProductoAsociadoSearchAction getMantenimientoPEDProductoAsociadoSearchAction() {
		return mantenimientoPEDProductoAsociadoSearchAction;
	}

	/**
	 * @param mantenimientoPEDProductoAsociadoSearchAction the mantenimientoPEDProductoAsociadoSearchAction to set
	 */
	public void setMantenimientoPEDProductoAsociadoSearchAction(MantenimientoPEDProductoAsociadoSearchAction mantenimientoPEDProductoAsociadoSearchAction) {
		this.mantenimientoPEDProductoAsociadoSearchAction = mantenimientoPEDProductoAsociadoSearchAction;
	}

	/**
	 * @return the numeroGrupoGlobal
	 */
	public String getNumeroGrupoGlobal() {
		return numeroGrupoGlobal;
	}

	/**
	 * @param numeroGrupoGlobal the numeroGrupoGlobal to set
	 */
	public void setNumeroGrupoGlobal(String numeroGrupoGlobal) {
		this.numeroGrupoGlobal = numeroGrupoGlobal;
	}

	/**
	 * @return the formModificaProductoGrupo
	 */
	public MantenimientoPREModificaProductoGrupoForm getFormModificaProductoGrupo() {
		return formModificaProductoGrupo;
	}

	/**
	 * @param formModificaProductoGrupo the formModificaProductoGrupo to set
	 */
	public void setFormModificaProductoGrupo(MantenimientoPREModificaProductoGrupoForm formModificaProductoGrupo) {
		this.formModificaProductoGrupo = formModificaProductoGrupo;
	}

	/**
	 * @return the listPrincipal
	 */
	public List getListPrincipal() {
		return listPrincipal;
	}

	/**
	 * @param listPrincipal the listPrincipal to set
	 */
	public void setListPrincipal(List listPrincipal) {
		this.listPrincipal = listPrincipal;
	}

	/**
	 * @return the dataTableListPrincipal
	 */
	public DataTableModel getDataTableListPrincipal() {
		return dataTableListPrincipal;
	}

	/**
	 * @param dataTableListPrincipal the dataTableListPrincipal to set
	 */
	public void setDataTableListPrincipal(DataTableModel dataTableListPrincipal) {
		this.dataTableListPrincipal = dataTableListPrincipal;
	}

	/**
	 * @return the listAsociado
	 */
	public List getListAsociado() {
		return listAsociado;
	}

	/**
	 * @param listAsociado the listAsociado to set
	 */
	public void setListAsociado(List listAsociado) {
		this.listAsociado = listAsociado;
	}

	/**
	 * @return the dataTableListAsociado
	 */
	public DataTableModel getDataTableListAsociado() {
		return dataTableListAsociado;
	}

	/**
	 * @param dataTableListAsociado the dataTableListAsociado to set
	 */
	public void setDataTableListAsociado(DataTableModel dataTableListAsociado) {
		this.dataTableListAsociado = dataTableListAsociado;
	}

	/**
	 * @return the columnasSeleccionadasPrincipal
	 */
	public Map getColumnasSeleccionadasPrincipal() {
		return columnasSeleccionadasPrincipal;
	}

	/**
	 * @param columnasSeleccionadasPrincipal the columnasSeleccionadasPrincipal to set
	 */
	public void setColumnasSeleccionadasPrincipal(Map columnasSeleccionadasPrincipal) {
		this.columnasSeleccionadasPrincipal = columnasSeleccionadasPrincipal;
	}

	/**
	 * @return the columnasSeleccionadasAsociado
	 */
	public Map getColumnasSeleccionadasAsociado() {
		return columnasSeleccionadasAsociado;
	}

	/**
	 * @param columnasSeleccionadasAsociado the columnasSeleccionadasAsociado to set
	 */
	public void setColumnasSeleccionadasAsociado(Map columnasSeleccionadasAsociado) {
		this.columnasSeleccionadasAsociado = columnasSeleccionadasAsociado;
	}

	/**
	 * @return the listAsociadoGlobal
	 */
	public List getListAsociadoGlobal() {
		return listAsociadoGlobal;
	}

	/**
	 * @param listAsociadoGlobal the listAsociadoGlobal to set
	 */
	public void setListAsociadoGlobal(List listAsociadoGlobal) {
		this.listAsociadoGlobal = listAsociadoGlobal;
	}

	/**
	 * @return the formModificaProductoPrincipal
	 */
	public MantenimientoPREModificaProductoPrincipalForm getFormModificaProductoPrincipal() {
		return formModificaProductoPrincipal;
	}

	/**
	 * @param formModificaProductoPrincipal the formModificaProductoPrincipal to set
	 */
	public void setFormModificaProductoPrincipal(MantenimientoPREModificaProductoPrincipalForm formModificaProductoPrincipal) {
		this.formModificaProductoPrincipal = formModificaProductoPrincipal;
	}

	/**
	 * @return the formModificaProductoAsociado
	 */
	public MantenimientoPREModificaProductoAsociadoForm getFormModificaProductoAsociado() {
		return formModificaProductoAsociado;
	}

	/**
	 * @param formModificaProductoAsociado the formModificaProductoAsociado to set
	 */
	public void setFormModificaProductoAsociado(MantenimientoPREModificaProductoAsociadoForm formModificaProductoAsociado) {
		this.formModificaProductoAsociado = formModificaProductoAsociado;
	}

	/**
	 * @return the noPasaValidacionTipoOfertaIndividual
	 */
	public boolean isNoPasaValidacionTipoOfertaIndividual() {
		return noPasaValidacionTipoOfertaIndividual;
	}

	/**
	 * @param noPasaValidacionTipoOfertaIndividual the noPasaValidacionTipoOfertaIndividual to set
	 */
	public void setNoPasaValidacionTipoOfertaIndividual(boolean noPasaValidacionTipoOfertaIndividual) {
		this.noPasaValidacionTipoOfertaIndividual = noPasaValidacionTipoOfertaIndividual;
	}

	/**
	 * @return the noPasaValidacionTipoOfertaGrupo
	 */
	public boolean isNoPasaValidacionTipoOfertaGrupo() {
		return noPasaValidacionTipoOfertaGrupo;
	}

	/**
	 * @param noPasaValidacionTipoOfertaGrupo the noPasaValidacionTipoOfertaGrupo to set
	 */
	public void setNoPasaValidacionTipoOfertaGrupo(boolean noPasaValidacionTipoOfertaGrupo) {
		this.noPasaValidacionTipoOfertaGrupo = noPasaValidacionTipoOfertaGrupo;
	}

	/**
	 * @return the noPasaValidacionTipoOfertaPrincipal
	 */
	public boolean isNoPasaValidacionTipoOfertaPrincipal() {
		return noPasaValidacionTipoOfertaPrincipal;
	}

	/**
	 * @param noPasaValidacionTipoOfertaPrincipal the noPasaValidacionTipoOfertaPrincipal to set
	 */
	public void setNoPasaValidacionTipoOfertaPrincipal(boolean noPasaValidacionTipoOfertaPrincipal) {
		this.noPasaValidacionTipoOfertaPrincipal = noPasaValidacionTipoOfertaPrincipal;
	}

	/**
	 * @return the noPasaValidacionTipoOfertaAsociado
	 */
	public boolean isNoPasaValidacionTipoOfertaAsociado() {
		return noPasaValidacionTipoOfertaAsociado;
	}

	/**
	 * @param noPasaValidacionTipoOfertaAsociado the noPasaValidacionTipoOfertaAsociado to set
	 */
	public void setNoPasaValidacionTipoOfertaAsociado(boolean noPasaValidacionTipoOfertaAsociado) {
		this.noPasaValidacionTipoOfertaAsociado = noPasaValidacionTipoOfertaAsociado;
	}

	/**
	 * @return the valorModificarOfertaCerrada
	 */
	public String getValorModificarOfertaCerrada() {
		return valorModificarOfertaCerrada;
	}

	/**
	 * @param valorModificarOfertaCerrada the valorModificarOfertaCerrada to set
	 */
	public void setValorModificarOfertaCerrada(String valorModificarOfertaCerrada) {
		this.valorModificarOfertaCerrada = valorModificarOfertaCerrada;
	}

	/**
	 * @return the modificaOfertaCerrada
	 */
	public boolean isModificaOfertaCerrada() {
		return modificaOfertaCerrada;
	}

	/**
	 * @param modificaOfertaCerrada the modificaOfertaCerrada to set
	 */
	public void setModificaOfertaCerrada(boolean modificaOfertaCerrada) {
		this.modificaOfertaCerrada = modificaOfertaCerrada;
	}

	/**
	 * @return the muestraIdModificaOfertaCerrada
	 */
	public boolean isMuestraIdModificaOfertaCerrada() {
		return muestraIdModificaOfertaCerrada;
	}

	/**
	 * @param muestraIdModificaOfertaCerrada the muestraIdModificaOfertaCerrada to set
	 */
	public void setMuestraIdModificaOfertaCerrada(boolean muestraIdModificaOfertaCerrada) {
		this.muestraIdModificaOfertaCerrada = muestraIdModificaOfertaCerrada;
	}

	/**
	 * @return the indicadorModificaOfertaCerrada
	 */
	public Integer getIndicadorModificaOfertaCerrada() {
		return indicadorModificaOfertaCerrada;
	}

	/**
	 * @param indicadorModificaOfertaCerrada the indicadorModificaOfertaCerrada to set
	 */
	public void setIndicadorModificaOfertaCerrada(Integer indicadorModificaOfertaCerrada) {
		this.indicadorModificaOfertaCerrada = indicadorModificaOfertaCerrada;
	}

	/**
	 * @return the booleanCuvFacturado
	 */
	public boolean isBooleanCuvFacturado() {
		return booleanCuvFacturado;
	}

	/**
	 * @param booleanCuvFacturado the booleanCuvFacturado to set
	 */
	public void setBooleanCuvFacturado(boolean booleanCuvFacturado) {
		this.booleanCuvFacturado = booleanCuvFacturado;
	}

	/**
	 * @return the idPrecioContable
	 */
	public boolean isIdPrecioContable() {
		return idPrecioContable;
	}

	/**
	 * @param idPrecioContable the idPrecioContable to set
	 */
	public void setIdPrecioContable(boolean idPrecioContable) {
		this.idPrecioContable = idPrecioContable;
	}

	/**
	 * @return the idPagina
	 */
	public boolean isIdPagina() {
		return idPagina;
	}

	/**
	 * @param idPagina the idPagina to set
	 */
	public void setIdPagina(boolean idPagina) {
		this.idPagina = idPagina;
	}

	/**
	 * @return the booleanIndDigAsociado
	 */
	public boolean isBooleanIndDigAsociado() {
		return booleanIndDigAsociado;
	}

	/**
	 * @param booleanIndDigAsociado the booleanIndDigAsociado to set
	 */
	public void setBooleanIndDigAsociado(boolean booleanIndDigAsociado) {
		this.booleanIndDigAsociado = booleanIndDigAsociado;
	}

	/**
	 * @return the booleanIndImpAsociado
	 */
	public boolean isBooleanIndImpAsociado() {
		return booleanIndImpAsociado;
	}

	/**
	 * @param booleanIndImpAsociado the booleanIndImpAsociado to set
	 */
	public void setBooleanIndImpAsociado(boolean booleanIndImpAsociado) {
		this.booleanIndImpAsociado = booleanIndImpAsociado;
	}

}
