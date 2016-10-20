/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.CondicionOferta;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.DetalleOferta;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.GrupoOferta;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.MatrizFacturacion;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.Oferta;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.RangoPromocion;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.MantenimientoPRECambioCodigoVentaService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDConfiguracionOfertaService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDConfiguracionOfertasPorConcursosService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDDefinirOfertaForm;

/**
 * @author Sigcomt
 *
 */
@ManagedBean
@SessionScoped
public class MantenimientoPEDDefinirOfertaAction extends BaseMantenimientoSearchAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1930887751956261257L;
	
	private List pedEstrategiaList = new ArrayList();
	private List pedAccesoList = new ArrayList();
	private List siccCatalogoList = new ArrayList();
	private List pedCondicionPromocionTipoCuadreList = new ArrayList();
	private List siccFormaPagoList = new ArrayList();
	private String flagMostrarGrupos;
	private LabelValue[] pedSubAccesoList={};
	private LabelValue[] pedIndicadorCuadreList={};
	private boolean boolFlagDespachoCompleto;
	private boolean boolFlagDespachoAutomatico;
	private List pedOfertaGrupoActualList = new ArrayList();
	private LabelValue[] pedIndicadorCuadreGrupoList = {};
	private List columnasSeleccionadas = new ArrayList();
	private List pedOfertaCriteriosList = new ArrayList();
	private DataTableModel listaModelPedOfertaCriteriosList = new DataTableModel();
	private Map columnasSeleccionadasCriterios = new HashMap();
	
	private String grupoNumeroTotalGruposAnteriorJs ;
	private String numeroGruposAnteriorJs ;
	private String numeroPaquetesAnteriorJs ;
	private String numeroGruposAnterior;
	private String numeroPaquetesAnterior;
	private String numeroGruposCondicionantesAnterior;
	private String numeroGruposCondicionadosAnterior;
	
	
	
	private boolean flagGridProductos;
	private boolean flagGridProductoPrincipal;
	private boolean flagGridDatosGrupos;
	private boolean flagCondicionesPromocion;
	
	private String rango = Constants.PED_OFERTAS_POR_CONCURSO_TIPO_RANGO_RANGO;
	private String producto = Constants.PED_OFERTAS_POR_CONCURSO_TIPO_RANGO_PRODUCTO;
	private String num_uno = Constants.NUMERO_UNO;
	private String num_cero = Constants.NUMERO_CERO;
	
	private String oidTipoEstrategia1 = Constants.PED_OID_TIPO_ESTRATEGIA_UNO;
	private String oidTipoEstrategia2 = Constants.PED_OID_TIPO_ESTRATEGIA_DOS;
	private String oidTipoEstrategia3 = Constants.PED_OID_TIPO_ESTRATEGIA_TRES;
	private String oidTipoEstrategia4 = Constants.PED_OID_TIPO_ESTRATEGIA_CUATRO;
	private String oidTipoEstrategia5 = Constants.PED_OID_TIPO_ESTRATEGIA_CINCO;
	private String oidTipoEstrategia6 = Constants.PED_OID_TIPO_ESTRATEGIA_SEIS;
	private String oidTipoEstrategia7 = Constants.PED_OID_TIPO_ESTRATEGIA_SIETE;
	private String oidTipoEstrategia22 = Constants.PED_OID_TIPO_ESTRATEGIA_VEINTIDOS;
	
	private boolean flagCondicionesPromocionOidTipoCuadreObligatorio = false;
	private boolean flagCondicionesPromocionFactorCuadreObligatorio = false;
	
	private boolean flagReload = false;
	
	private boolean flagNumeroGruposObligatorio = false;
	private boolean flagNumeroPaquetesObligatorio = false;	
	private boolean flagIndicadorCuadreObligatorio = false;
	private boolean flagNumeroGruposCondicionantesObligatorio = false;
	private boolean flagCondicionantesObligatorio = false;
	private boolean flagNumeroGruposCondicionadosObligatorio = false;
	private boolean flagCondicionadosObligatorio = false;	
		
	private boolean flagNumeroGrupos;
	private boolean flagNumeroPaquetes;
	private boolean flagOidIndicadorCuadre;
	private boolean flagNumeroGruposCondicionantes;
	private boolean flagCondicionantesUno;
	private boolean flagCondicionantesCero;	
	private boolean flagCondicionadosUno;
	private boolean flagCondicionadosCero;
	private boolean flagNumeroGruposCondicionados;
	private boolean flagDespachoCompletoDis;
	private boolean flagDespachoAutomaticoDis;
	private boolean flagGrupoOidIndicadorCuadre;
	private boolean flagCriteriosPaginaInicial;
	private boolean flagCriteriosPaginaFinal;
	private boolean flagCriteriosCodigoProducto;
	private boolean flagCriteriosIndicadorExclusion;
	private boolean boolCriteriosIndicadorExclusion;
	
	private Map pedOfertaGruposMap = new HashMap ();
	
	private String indicadorRecarga;
	
	@ManagedProperty(value="#{mantenimientoPEDProductoAsociadoSearchAction}")	
	private MantenimientoPEDProductoAsociadoSearchAction mantenimientoPEDProductoAsociadoSearchAction;
	
	private List preComponentesList = new ArrayList();
	private List preComponentesGlobalList = new ArrayList();
	private DataTableModel dataTableComponenteList;
	
	@Override
	protected String getSalirForward() { 
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoPEDDefinirOfertaForm form = new MantenimientoPEDDefinirOfertaForm();
		return form;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		
		log.debug("Enter method - setViewAtributes");
		
		MantenimientoPEDConfiguracionOfertaService service = (MantenimientoPEDConfiguracionOfertaService)getBean("spusicc.mantenimientoPEDConfiguracionOfertaService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoPRECambioCodigoVentaService service2 = (MantenimientoPRECambioCodigoVentaService)getBean("spusicc.mantenimientoPRECambioCodigoVentaService");

		MantenimientoPEDDefinirOfertaForm editForm = (MantenimientoPEDDefinirOfertaForm)this.formBusqueda;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		//Obtenemos la matriz seleccionada desde la session de usuario
		MatrizFacturacion matriz = this.mPantallaPrincipalBean.getPedMatrizSeleccionada();
		
		if(matriz != null)
		{
			editForm.setOidPeriodo(matriz.getOidPeriodo());
			editForm.setCodigoPeriodo(matriz.getCodigoPeriodo());
			editForm.setOidMatriz(matriz.getOidMatriz());
			
			List estrategias = service.getEstrategias(null);
			this.pedEstrategiaList = estrategias;			
					
			List accesos = service.getOidAccesosByCodigoISO(usuario.getIdioma().getCodigoISO());
			this.pedAccesoList = accesos;
			this.pedSubAccesoList = null;
			
			this.siccCatalogoList = reporteService.getListaGenerico("getOidCatalogoProductos",null);
			this.pedCondicionPromocionTipoCuadreList =  service.getCondicionPromocionTiposCuadre();				
		}
		else
		{
			if(StringUtils.equals(this.mPantallaPrincipalBean.getCurrentMenu(),Constants.CODIGO_MENU_PEDDEFINIROFERTA)){
				
				log.debug("Redireccionando a la pantalla de Seleccion de Matriz...");
				this.mPantallaPrincipalBean.setCodigoAccionRetorno(Constants.PED_CODIGO_PANTALLA_DEFINIR_OFERTA);
				this.redireccionarPagina("mantenimientoPEDSeleccionMatrizFacturacionList");
			}
		}
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		criteriaOperacion.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteriaOperacion.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);		
		criteriaOperacion.put("oidPais", reporteService.getOidString("getOidPaisByCodigoPais", criteriaOperacion));
		
		this.pedIndicadorCuadreList = null;
		this.pedIndicadorCuadreGrupoList = null;
		this.siccFormaPagoList = reporteService.getListaGenerico("getListaFormaPago", criteriaOperacion);		
		this.pedOfertaGrupoActualList = null;
		
		this.mPantallaPrincipalBean.setPedProductoAsociadoOfertaList(null);
		this.mPantallaPrincipalBean.setPedProductoAsociadoOfertaGrupoList(null);
		this.mPantallaPrincipalBean.setListaModelPedProductoAsociadoOfertaGrupoList(null);
		
		this.pedOfertaGruposMap = null;
		this.pedOfertaCriteriosList = null;
		this.listaModelPedOfertaCriteriosList = new DataTableModel(null);
		
//		
		this.flagMostrarGrupos = Constants.ESTADO_ACTIVO;		
		
		limpiarForm(editForm);
		
		this.mostrarBotonBuscar = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonNuevo = false;
		
		this.boolFlagDespachoAutomatico = false;
		this.boolFlagDespachoCompleto = false;
		
		Map map = new HashMap();
		service2.deletePromoProduCompoTemporal(map);
	}
	
	/**
	 * @param seccion
	 */
	public void recargar(String seccion){
		
		log.debug("Enter method - inicializarValores*******");		
		if(log.isDebugEnabled())
			log.debug("Recargando pagina ...");
		
		MantenimientoPEDDefinirOfertaForm editForm = (MantenimientoPEDDefinirOfertaForm)this.formBusqueda;
		this.recargar(seccion, editForm);
		this.flagMostrarGrupos = Constants.ESTADO_ACTIVO;
	}
	
	/**
	 * @throws Exception
	 */
	public void inicializarValores() throws Exception{
		
		log.debug("Enter method - inicializarValores");
		
		MantenimientoPEDConfiguracionOfertaService service = (MantenimientoPEDConfiguracionOfertaService)getBean("spusicc.mantenimientoPEDConfiguracionOfertaService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoPRECambioCodigoVentaService service2 = (MantenimientoPRECambioCodigoVentaService)getBean("spusicc.mantenimientoPRECambioCodigoVentaService");

		MantenimientoPEDDefinirOfertaForm editForm = (MantenimientoPEDDefinirOfertaForm)this.formBusqueda;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		//Obtenemos la matriz seleccionada desde la session de usuario
		MatrizFacturacion matriz = this.mPantallaPrincipalBean.getPedMatrizSeleccionada();
		
		if(matriz != null)
		{
			editForm.setOidPeriodo(matriz.getOidPeriodo());
			editForm.setCodigoPeriodo(matriz.getCodigoPeriodo());
			editForm.setOidMatriz(matriz.getOidMatriz());
			
			List estrategias = service.getEstrategias(null);
			this.pedEstrategiaList = estrategias;			
					
			List accesos = service.getOidAccesosByCodigoISO(usuario.getIdioma().getCodigoISO());
			this.pedAccesoList = accesos;
			this.pedSubAccesoList = null;
			
			this.siccCatalogoList = reporteService.getListaGenerico("getOidCatalogoProductos",null);
			this.pedCondicionPromocionTipoCuadreList =  service.getCondicionPromocionTiposCuadre();		
		}
		else
		{
			log.debug("Redireccionando a la pantalla de Seleccion de Matriz...");
			this.mPantallaPrincipalBean.setCodigoAccionRetorno(Constants.PED_CODIGO_PANTALLA_DEFINIR_OFERTA);
			this.redireccionarPagina("mantenimientoPEDSeleccionMatrizFacturacionList");
		}
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		criteriaOperacion.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteriaOperacion.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);		
		criteriaOperacion.put("oidPais", reporteService.getOidString("getOidPaisByCodigoPais", criteriaOperacion));
		
		this.pedIndicadorCuadreList = null;
		this.pedIndicadorCuadreGrupoList = null;
		this.siccFormaPagoList = reporteService.getListaGenerico("getListaFormaPago", criteriaOperacion);		
		this.pedOfertaGrupoActualList = null;	
		
		this.mPantallaPrincipalBean.setPedProductoAsociadoOfertaList(null);
		this.mPantallaPrincipalBean.setPedProductoAsociadoOfertaGrupoList(null);
		this.mPantallaPrincipalBean.setListaModelPedProductoAsociadoOfertaGrupoList(null);
		this.pedOfertaGruposMap = null;
		this.pedOfertaCriteriosList = null;
		this.listaModelPedOfertaCriteriosList = new DataTableModel(null);
//		
		this.flagMostrarGrupos = Constants.ESTADO_ACTIVO;		
		
		limpiarForm(editForm);
		
		this.mostrarBotonBuscar = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonNuevo = false;
		
		this.boolFlagDespachoAutomatico = false;
		this.boolFlagDespachoCompleto = false;		
	
		Map map = new HashMap();
		service2.deletePromoProduCompoTemporal(map);
	}
	
	/**
	 * @param editForm
	 */
	private void limpiarForm(MantenimientoPEDDefinirOfertaForm editForm) {
		editForm.setFlagCabeceraSoloLectura(false);
		editForm.setFlagDatosOFertaSoloLectura(true);
		this.flagNumeroGrupos = true;
		this.flagNumeroPaquetes = true;
		this.flagOidIndicadorCuadre = true;
		this.flagNumeroGruposCondicionantes = true;
		this.flagCondicionantesUno = true;
		this.flagCondicionantesCero = true;
		this.flagCondicionadosUno = true;
		this.flagCondicionadosCero = true;
		this.flagNumeroGruposCondicionados = true;
		this.flagDespachoCompletoDis = true; 
		this.flagDespachoAutomaticoDis= true; 
		this.flagGrupoOidIndicadorCuadre = true;
		this.flagGridProductos = false;
		this.flagGridProductoPrincipal = false;
		this.flagGridDatosGrupos = false;
		this.flagCondicionesPromocion = false;
		editForm.setFlagDatosGrupoSoloLectura(false);			
		editForm.setOidEstrategia("");
		editForm.setOidAcceso("");
		editForm.setOidSubacceso("");
		editForm.setOidCatalogo("");
		
		editForm.setNumeroGrupos("");
		editForm.setNumeroPaquetes("");
		editForm.setOidIndicadorCuadre("");
		editForm.setNumeroGruposCondicionantes("");
		editForm.setCondicionantes("");
		editForm.setNumeroGruposCondicionados("");
		editForm.setCondicionados("");
		editForm.setFlagDespachoCompleto("");
		editForm.setFlagDespachoAutomatico("");
		editForm.setOidFormaPago("");
		
		editForm.setOidTipoEstrategia("");
		
		editForm.setGrupoNumeroTotalGrupos("");
		editForm.setGrupoTipo("");
		editForm.setGrupoOidIndicadorCuadre("");
		editForm.setGrupoFactorCuadre("");
		editForm.setGrupoActual("");
		editForm.setGrupoAnterior(Constants.NUMERO_UNO);
		editForm.setGrupoNumeroMensaje("");
		editForm.setGrupoProductosSelectedItems(new String[0]);
		
		editForm.setCondicionesPromocionFactorCuadre("");
		editForm.setCondicionesPromocionOidTipoCuadre("");
		
		editForm.setCriteriosOidCatalogo("");
		editForm.setCriteriosCodigoTipoRango("");
		editForm.setCriteriosPaginaInicial("");
		editForm.setCriteriosPaginaFinal("");
		editForm.setCriteriosCodigoProducto("");
		editForm.setCriteriosIndicadorExclusion(Constants.NUMERO_CERO);		
		this.boolCriteriosIndicadorExclusion = false;
	}

	/**
	 * @param event
	 */
	public void loadSubaccesos(ValueChangeEvent event){
		
		log.debug("Enter method - loadSubaccesos");
		
		String oidAcceso = (String)event.getNewValue();
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		this.pedSubAccesoList = ajax.getOidSubaccesosByOidAcceso(oidAcceso);
		
	}
	
	/**
	 * @param event
	 */
	public void obtenerOidTipoEstrategia(ValueChangeEvent event){
		
		log.debug("Enter method - obtenerOidTipoEstrategia");
		String oidEstrategia = (String) event.getNewValue();
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		this.flagReload = false;
		String oidTipoEstrategiaSeleccionada = ajax.getOidTipoEstrategia(oidEstrategia);
				
		MantenimientoPEDDefinirOfertaForm form = (MantenimientoPEDDefinirOfertaForm) this.formBusqueda;
		form.setOidTipoEstrategia(oidTipoEstrategiaSeleccionada);
        this.getOidTipoEstrategia(oidTipoEstrategiaSeleccionada, form, oidEstrategia);
	}
	
	
    /**
     * 
     */
    public void obtenerOidTipoEstrategia(){
    	MantenimientoPEDDefinirOfertaForm editForm = (MantenimientoPEDDefinirOfertaForm)this.formBusqueda;
		log.debug("Enter method - obtenerOidTipoEstrategia");
		String oidEstrategia = editForm.getOidEstrategia();
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		this.flagReload = true;
		String oidTipoEstrategiaSeleccionada = ajax.getOidTipoEstrategia(oidEstrategia);
				
		MantenimientoPEDDefinirOfertaForm form = (MantenimientoPEDDefinirOfertaForm) this.formBusqueda;
		form.setOidTipoEstrategia(oidTipoEstrategiaSeleccionada);
        this.getOidTipoEstrategia(oidTipoEstrategiaSeleccionada, form, oidEstrategia);
	}

	/**
	 * @param oidTipoEstrategiaSeleccionada
	 * @param form
	 */
	private void getOidTipoEstrategia(String oidTipoEstrategiaSeleccionada,
			MantenimientoPEDDefinirOfertaForm form, String oidEstrategia) {
				
				//Grid de productos			
				if(oidTipoEstrategiaSeleccionada.equals(oidTipoEstrategia1) || oidTipoEstrategiaSeleccionada.equals(oidTipoEstrategia2) || oidTipoEstrategiaSeleccionada.equals(oidTipoEstrategia5))
				{
					this.flagGridProductos = true;
					this.flagGridProductoPrincipal = false;
					
					if(oidTipoEstrategiaSeleccionada.equals(oidTipoEstrategia2)){
						this.flagGridProductoPrincipal = true;
					}
				}
				else
				{
					this.flagGridProductos = false;
					this.flagGridProductoPrincipal = false;
				}
				//
				
				//Grid de grupos
				if(flagMostrarGrupos == Constants.ESTADO_ACTIVO)
					mostrarGrupos();
				//
				
				//condicionesPromocion
				if(oidTipoEstrategiaSeleccionada.equals(oidTipoEstrategia5) || oidTipoEstrategiaSeleccionada.equals(oidTipoEstrategia6) || oidTipoEstrategiaSeleccionada.equals(oidTipoEstrategia7))
				{
					this.flagCondicionesPromocion = true;
					this.flagCondicionesPromocionOidTipoCuadreObligatorio = true;
					this.flagCondicionesPromocionFactorCuadreObligatorio = true;
				}
				else
				{
					this.flagCondicionesPromocion = false;
					this.flagCondicionesPromocionOidTipoCuadreObligatorio = false;
					this.flagCondicionesPromocionFactorCuadreObligatorio = false;
				}
				//
				
				//numeroGrupos
				if(oidTipoEstrategiaSeleccionada.equals(oidTipoEstrategia3) || oidTipoEstrategiaSeleccionada.equals(oidTipoEstrategia7))
				{
					this.flagNumeroGruposObligatorio = true;
					if(!flagReload)
						this.flagNumeroGrupos = false;			
				}
				else
				{
					this.flagNumeroGruposObligatorio = false;			
					this.flagNumeroGrupos = true;			
				}
				//
				
				//numeroPaquetes
				if(oidTipoEstrategiaSeleccionada.equals(oidTipoEstrategia6))
				{
					this.flagNumeroPaquetesObligatorio = true;
					if(!flagReload)
						this.flagNumeroPaquetes = false;
				}
				else
				{
					this.flagNumeroPaquetesObligatorio = false;
					this.flagNumeroPaquetes = true;
				}
				//
				
				//oidIndicadorCuadre
				if(oidTipoEstrategiaSeleccionada.equals(oidTipoEstrategia3) || oidTipoEstrategiaSeleccionada.equals(oidTipoEstrategia7))
				{
					this.flagIndicadorCuadreObligatorio = true;
					if(!flagReload)
						this.flagOidIndicadorCuadre = false;
				}
				else
				{
					this.flagIndicadorCuadreObligatorio = false;
					this.flagOidIndicadorCuadre = true;
				}
				//
				
				//numeroGruposCondicionantes
				if(oidTipoEstrategiaSeleccionada.equals(oidTipoEstrategia4))
				{
					flagNumeroGruposCondicionantesObligatorio = true;
					if(!flagReload)
						this.flagNumeroGruposCondicionantes = false;
				}
				else
				{
					flagNumeroGruposCondicionantesObligatorio = false;
					this.flagNumeroGruposCondicionantes = true;
				}
				//
				
				//condicionantes
				if(oidTipoEstrategiaSeleccionada.equals(oidTipoEstrategia4)  || oidTipoEstrategiaSeleccionada.equals(oidTipoEstrategia5) || oidTipoEstrategiaSeleccionada.equals(oidTipoEstrategia6) || StringUtils.equals(oidEstrategia, "2022"))
				{
					this.flagCondicionantesObligatorio = true;
					if(!flagReload)
					{
						this.flagCondicionantesUno = false;
						form.setCondicionantes(Constants.NUMERO_UNO);
						this.flagCondicionantesCero = false;			
					}
				}
				else
				{
					this.flagCondicionantesObligatorio = false;
					this.flagCondicionantesUno = true;
					this.flagCondicionantesCero = true;
					form.setCondicionantes(null);
				}
				//
				
				//numeroGruposCondicionados
				if(oidTipoEstrategiaSeleccionada.equals(oidTipoEstrategia4))
				{
					this.flagNumeroGruposCondicionadosObligatorio = true;
					if(!flagReload)
						this.flagNumeroGruposCondicionados = false;
				}
				else
				{
					this.flagNumeroGruposCondicionadosObligatorio = false;
					this.flagNumeroGruposCondicionados = true;
				}
				//
				
				//condicionados
				if(oidTipoEstrategiaSeleccionada.equals(oidTipoEstrategia4))
				{
					flagCondicionadosObligatorio = true;
					if(!flagReload)
					{
						this.flagCondicionadosUno = false;
						this.flagCondicionadosCero = false;	
						form.setCondicionados(Constants.NUMERO_UNO);
					}
				}
				else
				{
					flagCondicionadosObligatorio = false;
					this.flagCondicionadosUno = true;
					this.flagCondicionadosCero = true;
					form.setCondicionados(null);
				}
				//
				
				//flagDespachoCompleto
				if(oidTipoEstrategiaSeleccionada.equals(oidTipoEstrategia2) || oidTipoEstrategiaSeleccionada.equals(oidTipoEstrategia6))
				{
					if(!flagReload)
						this.flagDespachoCompletoDis = false;
				}
				else
				{
					this.flagDespachoCompletoDis = true;
				}
				//
				
				//flagDespachoAutomatico
				if(oidTipoEstrategiaSeleccionada.equals(oidTipoEstrategia5) || oidTipoEstrategiaSeleccionada.equals(oidTipoEstrategia6) || oidTipoEstrategiaSeleccionada.equals(oidTipoEstrategia7))
				{
					if(!flagReload)
						this.flagDespachoAutomaticoDis = false;
				}
				else
				{
					this.flagDespachoAutomaticoDis = true;
				}
				//		
				
				if(oidTipoEstrategiaSeleccionada.equals(oidTipoEstrategia3) || oidTipoEstrategiaSeleccionada.equals(oidTipoEstrategia7))
				{
					form.setGrupoTipo(Constants.PED_TIPO_GRUPO_GRUPO);
		//			Aca creo esta seteando el valor del grupoOidIndicadorCuadre CON EL VALOR oidIndicadorCuadre
		//			document.getElementById("grupoOidIndicadorCuadre").value = document.getElementById("oidIndicadorCuadre").value;		
					form.setGrupoOidIndicadorCuadre(form.getOidIndicadorCuadre());
					this.flagGrupoOidIndicadorCuadre = true;
				}
				else if(oidTipoEstrategiaSeleccionada.equals(oidTipoEstrategia6))
				{
					form.setGrupoTipo(Constants.PED_TIPO_GRUPO_PAQUETE);
					this.flagGrupoOidIndicadorCuadre = true;					
				}
				else if(oidTipoEstrategiaSeleccionada.equals(oidTipoEstrategia4))
				{					
					//document.getElementById("grupoTipo").value = '': //'<c:out value="${PED_TIPO_GRUPO_CONDICIONANTE}"/>';
					
					if(!flagReload)
						getTiposCuadreGrupo();			
				}
				
				if(!flagReload)
				{					
					form.setNumeroGrupos("");
					form.setNumeroPaquetes("");
					form.setOidIndicadorCuadre("");
					form.setNumeroGruposCondicionantes("");
					form.setNumeroGruposCondicionados("");
					this.boolFlagDespachoCompleto = false;
					form.setFlagDespachoCompleto("");
					this.boolFlagDespachoAutomatico = false;
					form.setFlagDespachoAutomatico("");
					form.setOidFormaPago("");
					form.setGrupoNumeroTotalGrupos("");
					form.setGrupoNumeroMensaje("");
				}		
				else{
					
				}
				calcularNumeroGrupos();
	}
	
	/**
	 * @param event
	 */
	public void mostrarGrupos(ValueChangeEvent event){
		
		String oidIndicadorCuadre = (String) event.getNewValue();
		MantenimientoPEDDefinirOfertaForm form = (MantenimientoPEDDefinirOfertaForm) this.formBusqueda;
		form.setGrupoOidIndicadorCuadre(oidIndicadorCuadre);
		this.mostrarGrupos();
		
		
	}
	
	/**
	 * 
	 */
	private void mostrarGrupos(){
		
		log.debug("Enter method - mostrarGrupos");
		MantenimientoPEDDefinirOfertaForm form = (MantenimientoPEDDefinirOfertaForm) this.formBusqueda;
		String oidTipoEstrategiaSeleccionada = form.getOidTipoEstrategia();
		
		
		String numeroGrupos = form.getNumeroGrupos();
		String numeroPaquetes = form.getNumeroPaquetes();
		String numeroGruposCondicionantes = form.getNumeroGruposCondicionantes();
		String numeroGruposCondicionados = form.getNumeroGruposCondicionados();
		
		if(
				(numeroGrupos != null && numeroGrupos != "") || (numeroPaquetes != null && numeroPaquetes!= "") ||
				((numeroGruposCondicionantes != null && numeroGruposCondicionantes != "") && 
				 (numeroGruposCondicionados != null && numeroGruposCondicionados != ""))
			)
		{
			if(oidTipoEstrategiaSeleccionada.equals(oidTipoEstrategia3) || oidTipoEstrategiaSeleccionada.equals(oidTipoEstrategia4) || oidTipoEstrategiaSeleccionada.equals(oidTipoEstrategia6) || oidTipoEstrategiaSeleccionada.equals(oidTipoEstrategia7))
			{
				this.flagGridDatosGrupos = true;
			}
			else
			{
				this.flagGridDatosGrupos = false;
			}		
		}
		
	}
	
	/**
	 * 
	 */
	private void getTiposCuadreGrupo(){
		
		log.debug("Enter method - getTiposCuadreGrupo");
		
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		LabelValue[] data = ajax.getTiposCuadre(Constants.CODIGO_NEGATIVO_UNO);
		
		this.pedIndicadorCuadreGrupoList = data;
		
	}
	
	/**
	 * 
	 */
	private void calcularNumeroGrupos(){
		
		log.debug("Enter method - calcularNumeroGrupos");
		MantenimientoPEDDefinirOfertaForm form = (MantenimientoPEDDefinirOfertaForm)this.formBusqueda;
		
		String numeroGruposFinal = Constants.NUMERO_CERO;
		int numeroGruposFinalInt = 0;
		String numeroGrupos = form.getNumeroGrupos();
		String numeroPaquetes = form.getNumeroPaquetes();
		String numeroGruposCondicionantes = form.getNumeroGruposCondicionantes();
		String numeroGruposCondicionados = form.getNumeroGruposCondicionados();
		
		if(StringUtils.isBlank(numeroGrupos))
			numeroGrupos = Constants.NUMERO_CERO;
		
		if(StringUtils.isBlank(numeroPaquetes))
			numeroPaquetes = Constants.NUMERO_CERO;
		
		if(StringUtils.isBlank(numeroGruposCondicionantes))
			numeroGruposCondicionantes = Constants.NUMERO_CERO;
		
		if(StringUtils.isBlank(numeroGruposCondicionados))
			numeroGruposCondicionados = Constants.NUMERO_CERO;
		
		numeroGruposFinal = numeroGrupos;
		
		if(StringUtils.equals(numeroGruposFinal, Constants.NUMERO_CERO))
			numeroGruposFinal = numeroPaquetes;
		
		if(StringUtils.equals(numeroGruposFinal, Constants.NUMERO_CERO))
			{
				numeroGruposFinalInt = Integer.parseInt(numeroGruposCondicionantes) + Integer.parseInt(numeroGruposCondicionados);
				numeroGruposFinal = Integer.toString(numeroGruposFinalInt);
			}
		
		
		form.setGrupoNumeroTotalGrupos(numeroGruposFinal);	
	}
	
	
	/**
	 * @param event
	 */
	public void cargargrupo(ValueChangeEvent event){
		
		if(log.isDebugEnabled())
			log.debug("Cargando datos de grupo...");
		
		log.debug("Enter method - cargargrupo");
		
		MantenimientoPEDDefinirOfertaForm editForm = (MantenimientoPEDDefinirOfertaForm) this.formBusqueda;
		String nuevoGrupo = (String) event.getNewValue();
		
		if(StringUtils.isNotBlank(nuevoGrupo))
		{
			Map grupos = this.pedOfertaGruposMap; 
			
			if(grupos == null)
				grupos = new HashMap();
			
			//Guardamos la data del grupo que se esta viendo en pantalla
			guardarGrupoAnterior(editForm, grupos);
			//
			
			//Cargamos la data del nuevo grupo					
			Map nuevoGrupoObj = (Map)MapUtils.getObject(grupos, nuevoGrupo);
			editForm.setGrupoActual(nuevoGrupo);
			editForm.setGrupoTipo(obtenerTipoGrupo(editForm));
			editForm.setGrupoOidIndicadorCuadre(MapUtils.getString(nuevoGrupoObj, "grupoOidIndicador"));
			editForm.setGrupoFactorCuadre(MapUtils.getString(nuevoGrupoObj, "grupoFactorCuadre"));
			editForm.setGrupoAnterior(nuevoGrupo);
			
			this.mPantallaPrincipalBean.setPedProductoAsociadoOfertaGrupoList((List)MapUtils.getObject(nuevoGrupoObj, "listaProductos"));
			this.mPantallaPrincipalBean.setListaModelPedProductoAsociadoOfertaGrupoList(new DataTableModel(this.mPantallaPrincipalBean.getPedProductoAsociadoOfertaGrupoList()));
			//
			
			editForm.setGrupoNumeroMensaje(String.format("%s de %s", nuevoGrupo, editForm.getGrupoNumeroTotalGrupos()));	
			this.pedOfertaGruposMap = grupos;			
		}
		
		this.flagMostrarGrupos = Constants.ESTADO_ACTIVO;
		
		this.obtenerOidTipoEstrategia();		
	}
	
	/**
	 * @param editForm
	 * @param grupos
	 */
	private void guardarGrupoAnterior(MantenimientoPEDDefinirOfertaForm editForm, Map grupos) {
		Map grupoAnterior = new HashMap();
		String id = editForm.getGrupoAnterior();
		
		if(StringUtils.isBlank(id))
			id = Constants.NUMERO_UNO;
			
		grupoAnterior.put("grupoActual", id);
		grupoAnterior.put("grupoTipo", editForm.getGrupoTipo());
		grupoAnterior.put("grupoOidIndicador", editForm.getGrupoOidIndicadorCuadre());
		grupoAnterior.put("grupoFactorCuadre", editForm.getGrupoFactorCuadre());
		grupoAnterior.put("listaProductos", this.mPantallaPrincipalBean.getPedProductoAsociadoOfertaGrupoList());	
		
		grupos.put(id, grupoAnterior);
	}	
	
	/**
	 * @param editForm
	 * @return
	 */
	private String obtenerTipoGrupo(MantenimientoPEDDefinirOfertaForm editForm) {
		int gruposCondicionantes = 0;
		int grupoActual = 0;
		
		if(StringUtils.isNotBlank(editForm.getNumeroGruposCondicionantes()))
			gruposCondicionantes = Integer.parseInt(editForm.getNumeroGruposCondicionantes());

		if(StringUtils.isNotBlank(editForm.getGrupoActual()))
			grupoActual = Integer.parseInt(editForm.getGrupoActual());
		
		if(grupoActual <= gruposCondicionantes)
			return Constants.PED_TIPO_GRUPO_CONDICIONANTE;
		else
			return Constants.PED_TIPO_GRUPO_CONDICIONADO;		
	}
	
	/**
	 * @param event
	 */
	public void actualizarExclusion(ValueChangeEvent event){
		
		String criteriosCodigoTipoRango = (String) event.getNewValue();
		actualizarExclusion(criteriosCodigoTipoRango);
	}
	
	private void actualizarExclusion(String criteriosCodigoTipoRango){
	
		MantenimientoPEDDefinirOfertaForm form = (MantenimientoPEDDefinirOfertaForm) this.formBusqueda;
		
		if(StringUtils.equals(criteriosCodigoTipoRango,Constants.PED_OFERTAS_POR_CONCURSO_TIPO_RANGO_PRODUCTO)){
			this.flagCriteriosPaginaInicial = true;
			this.flagCriteriosPaginaFinal = true;
			this.flagCriteriosCodigoProducto = false;
			
			this.boolCriteriosIndicadorExclusion = true;
			form.setCriteriosIndicadorExclusion(Constants.NUMERO_UNO);
			this.flagCriteriosIndicadorExclusion = true;
//			f.criteriosCodigoProducto.focus();
		}
		else 
			if (StringUtils.equals(criteriosCodigoTipoRango,Constants.PED_OFERTAS_POR_CONCURSO_TIPO_RANGO_RANGO)) {
				this.flagCriteriosPaginaInicial = false;
				this.flagCriteriosPaginaFinal = false;
				this.flagCriteriosCodigoProducto = true;				
				this.boolCriteriosIndicadorExclusion = false;
				form.setCriteriosIndicadorExclusion(Constants.NUMERO_CERO);
				this.flagCriteriosIndicadorExclusion = true;
				
			}else {
				this.flagCriteriosPaginaInicial = false;
				this.flagCriteriosPaginaFinal = false;
				this.flagCriteriosCodigoProducto = false;
				
				this.boolCriteriosIndicadorExclusion = false;
				form.setCriteriosIndicadorExclusion(Constants.NUMERO_CERO);
				this.flagCriteriosIndicadorExclusion = false;
			}
	}
	
	/**
	 * @param event
	 */
	public void agregarcriterio (ActionEvent event){
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'agregarcriterio' method");
		}
		MantenimientoPEDDefinirOfertaForm editForm = (MantenimientoPEDDefinirOfertaForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String mensaje = null;
		
		String catalogo = editForm.getCriteriosOidCatalogo();
		String tipoRango = editForm.getCriteriosCodigoTipoRango();
		String paginaInicial = editForm.getCriteriosPaginaInicial();
		String paginaFinal = editForm.getCriteriosPaginaFinal();
		String producto = editForm.getCriteriosCodigoProducto();
		
		if(StringUtils.isBlank(catalogo)){
			mensaje = "mantenimientoPEDDefinirOfertaForm.criteriosOidCatalogo.requerido";
			this.addError("Error: ", this.getResourceMessage(mensaje));				
			return;
			
		}
		
		if(StringUtils.isBlank(tipoRango)){
			mensaje = "mantenimientoPEDDefinirOfertaForm.criteriosCodigoTipoRango.requerido";
			this.addError("Error: ", this.getResourceMessage(mensaje));				
			return;
			
		}
		
		if(StringUtils.isBlank(paginaInicial) && StringUtils.equals(tipoRango, Constants.PED_OFERTAS_POR_CONCURSO_TIPO_RANGO_RANGO)){
			mensaje = "mantenimientoPEDDefinirOfertaForm.criteriosPaginaInicial.requerido";
			this.addError("Error: ", this.getResourceMessage(mensaje));				
			return;
			
		}
		
		if(StringUtils.isBlank(paginaFinal) && StringUtils.equals(tipoRango, Constants.PED_OFERTAS_POR_CONCURSO_TIPO_RANGO_RANGO)){
			mensaje = "mantenimientoPEDDefinirOfertaForm.criteriosPaginaFinal.requerido";
			this.addError("Error: ", this.getResourceMessage(mensaje));				
			return;
			
		}
		
		if(StringUtils.isBlank(producto) && StringUtils.equals(tipoRango, Constants.PED_OFERTAS_POR_CONCURSO_TIPO_RANGO_PRODUCTO)){
			mensaje = "mantenimientoPEDDefinirOfertaForm.criteriosCodigoProducto.requerido";
			this.addError("Error: ", this.getResourceMessage(mensaje));				
			return;
			
		}

				
		try {
					
		MantenimientoPEDConfiguracionOfertasPorConcursosService service = (MantenimientoPEDConfiguracionOfertasPorConcursosService)getBean("spusicc.mantenimientoPEDConfiguracionOfertasPorConcursosService");
		MantenimientoPRECambioCodigoVentaService service2 = (MantenimientoPRECambioCodigoVentaService)getBean("spusicc.mantenimientoPRECambioCodigoVentaService");
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		String msj = null;
		
		if(this.isBoolCriteriosIndicadorExclusion())
			editForm.setCriteriosIndicadorExclusion(Constants.NUMERO_UNO);
		else
			editForm.setCriteriosIndicadorExclusion(null);
		
		List criterioList = this.pedOfertaCriteriosList;
		if(criterioList == null)
			criterioList = new ArrayList();	
		
		Map criterio = new HashMap();
		criterio.put("oidCriterio",RandomUtils.nextLong());
		criterio.put("oidCatalogo", editForm.getCriteriosOidCatalogo());
		criterio.put("codigoTipoRango", editForm.getCriteriosCodigoTipoRango());
		criterio.put("paginaInicial", editForm.getCriteriosPaginaInicial());		
		criterio.put("paginaFinal", editForm.getCriteriosPaginaFinal());
		criterio.put("codigoProducto", editForm.getCriteriosCodigoProducto());
		criterio.put("indicadorExclusion", StringUtils.isBlank(editForm.getCriteriosIndicadorExclusion()) ? Constants.NUMERO_CERO : editForm.getCriteriosIndicadorExclusion());
		
		criterio.put("catalogo", getDescripcionCatalogo(editForm.getCriteriosOidCatalogo()));
		criterio.put("producto", editForm.getCriteriosCodigoProducto());
		
		String oidProducto = null;
		
		if(StringUtils.equals(editForm.getCriteriosCodigoTipoRango(), Constants.PED_OFERTAS_POR_CONCURSO_TIPO_RANGO_PRODUCTO)
				&& StringUtils.isNotBlank(editForm.getCriteriosCodigoProducto()))
		{
			
			//validamos si el producto ingresado existe
			oidProducto = ajaxService.validarCodigoSAP(pais.getCodigo(), editForm.getCriteriosCodigoProducto());
			
			if(StringUtils.isBlank(oidProducto))
			{
				msj = "mantenimientoPEDDefinirOfertaForm.error.criterio.producto.noexiste";
				this.addError("Error: ", this.getResourceMessage(msj, new Object[]{editForm.getCriteriosCodigoProducto()}));
				return;
			}
		}
		
		criterio.put("oidProducto", oidProducto);
		
		criterioList.add(criterio);
		
		this.pedOfertaCriteriosList = criterioList;
		this.listaModelPedOfertaCriteriosList = new DataTableModel(this.pedOfertaCriteriosList);
		
		//Inicio Lista los Componentes 
		if(StringUtils.equals(editForm.getCriteriosCodigoTipoRango(), Constants.PED_OFERTAS_POR_CONCURSO_TIPO_RANGO_PRODUCTO)){
			Map mapSearch = new HashMap();
			mapSearch.put("codSapCompo", editForm.getCriteriosCodigoProducto());
			service2.deletePromoProduCompoTemporal(mapSearch);
			
			/*List listaComponentes = service2.getPromoProduCompoTemporalList(mapSearch);
			
			if(listaComponentes.size() > 0 && listaComponentes != null){
				for (int i = 0; i < listaComponentes.size(); i++) {
					Map mapProd = (Map) listaComponentes.get(i);
					service2.deletePromoProduCompoTemporal(mapProd);
				}
			}*/
		}else{
			Map criteriaCompo = new HashMap();
			criteriaCompo.put("oidCatalogoCompo", editForm.getCriteriosOidCatalogo());
			criteriaCompo.put("paginaInicialCompo", editForm.getCriteriosPaginaInicial());		
			criteriaCompo.put("paginaFinalCompo", editForm.getCriteriosPaginaFinal());
			criteriaCompo.put("codigoPais", pais.getCodigo());
			criteriaCompo.put("codigoPeriodo", editForm.getCodigoPeriodo());
			
			service2.insertPromoProduCompoTemporal(criteriaCompo);
			
			/*for (int j = 0; j < listaComponentes.size(); j++) {
				Map c = (Map) listaComponentes.get(j);
				this.preComponentesList.add(c);
				this.preComponentesGlobalList.add(c);
			}*/
		}
		
		Map mapSearch = new HashMap();
		List listaComponentes = service2.getPromoProduCompoTemporalList(mapSearch);
		this.preComponentesList = listaComponentes;
		this.preComponentesGlobalList = listaComponentes;
		this.dataTableComponenteList = new DataTableModel(this.preComponentesGlobalList);
		//Fin Lista los Componentes 
		
		msj = "mantenimientoPEDDefinirOfertaForm.criterio.agregado";
		this.addInfo("Info: ", this.getResourceMessage(msj));
		
		editForm.setCriteriosOidCatalogo("");
		editForm.setCriteriosCodigoTipoRango("");
		editForm.setCriteriosPaginaInicial("");
		editForm.setCriteriosPaginaFinal("");
		editForm.setCriteriosCodigoProducto("");
		this.boolCriteriosIndicadorExclusion = false;
		editForm.setCriteriosIndicadorExclusion(Constants.NUMERO_CERO);	
		
		} catch (Exception ex) {

			String error = ex.getMessage();
			if (StringUtils.isBlank(error)) error = ex.getLocalizedMessage();
			this.addError("Error: ", this.getResourceMessage("errors.detail", new Object[]{error}));						
		}
			
		
		this.flagMostrarGrupos = Constants.ESTADO_ACTIVO;
		obtenerOidTipoEstrategia();
		actualizarExclusion(editForm.getCriteriosCodigoTipoRango());
		setearTiposCuadre();
	
	}
	
	/**
	 * @param oidCatalogo
	 * @return
	 */
	private String getDescripcionCatalogo(String oidCatalogo) {
		String des = "";
		
		List catalogos = this.siccCatalogoList;
		
		for(int i=0; i<catalogos.size(); i++)
		{
			Base cat = (Base)catalogos.get(i);
			if(StringUtils.equals(cat.getCodigo(), oidCatalogo))
			{
				des = cat.getDescripcion();
				break;
			}
		}
		
		return des;
	}
	
	/**
	 * @param event
	 */
	public void quitarcriterio(ActionEvent event){
		
		log.debug("Enter method - quitarcriterio");
		
		MantenimientoPEDDefinirOfertaForm editForm = (MantenimientoPEDDefinirOfertaForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoPRECambioCodigoVentaService service2 = (MantenimientoPRECambioCodigoVentaService)getBean("spusicc.mantenimientoPRECambioCodigoVentaService");
		String mensaje = null;
		
		if(this.pedOfertaCriteriosList.size() == 0)
			return;
		
		if(this.columnasSeleccionadasCriterios!=null && this.columnasSeleccionadasCriterios.size() > 0){
			
			try {
				
				if (log.isDebugEnabled()) {
					log.debug("Entering 'quitarcriterio' method");
				}
				
				String oidCriterioSeleccionable = MapUtils.getString(this.columnasSeleccionadasCriterios, "oidCriterio");
				Map pedOferta = new HashMap();
				for (int i = 0; i < this.pedOfertaCriteriosList.size(); i++) {
					
					pedOferta = (Map) this.pedOfertaCriteriosList.get(i);
					String oidCriterioLista = MapUtils.getString(pedOferta, "oidCriterio");
										
					if(StringUtils.equals(oidCriterioLista, oidCriterioSeleccionable)){
						this.pedOfertaCriteriosList.remove(i);
						break;
					}
					
				}
				
				//Inicio Lista los Componentes 
				Map criteriaCompo = new HashMap();
				criteriaCompo.put("oidCatalogoCompo", MapUtils.getString(this.columnasSeleccionadasCriterios, "oidCatalogo"));
				criteriaCompo.put("paginaInicialCompo", MapUtils.getString(this.columnasSeleccionadasCriterios, "paginaInicial"));		
				criteriaCompo.put("paginaFinalCompo", MapUtils.getString(this.columnasSeleccionadasCriterios, "paginaFinal"));
				criteriaCompo.put("codigoPais", pais.getCodigo());
				criteriaCompo.put("codigoPeriodo", editForm.getCodigoPeriodo());
				List listaComponentes = service2.getComponentesList(criteriaCompo);
				
				for (int i = 0; i < this.preComponentesGlobalList.size(); i++) {
					Map compo = (Map) this.preComponentesGlobalList.get(i);
					
					for (int j = 0; j < listaComponentes.size(); j++) {
						Map c = (Map) listaComponentes.get(j);
						
						if(StringUtils.equals(MapUtils.getString(compo, "oid"), MapUtils.getString(c, "oid"))){
							service2.deletePromoProduCompoTemporal(c);
							break;
						}
					}
				}
				
				Map mapSearch = new HashMap();
				List listaComponentes2 = service2.getPromoProduCompoTemporalList(mapSearch);
				
				this.preComponentesGlobalList = listaComponentes2;
				this.dataTableComponenteList = new DataTableModel(this.preComponentesGlobalList);
				//Fin Lista los Componentes 
				
				this.listaModelPedOfertaCriteriosList= new DataTableModel(this.pedOfertaCriteriosList);
				mensaje = "mantenimientoPEDDefinirOfertaForm.criterio.eliminado";
				this.addInfo("Info: ", this.getResourceMessage(mensaje));
				
			} catch (Exception ex) {

				String error = ex.getMessage();
				if (StringUtils.isBlank(error)) error = ex.getLocalizedMessage();
				mensaje = "errors.detail";
				this.addError("Error: ", this.getResourceMessage(mensaje, new Object[]{error}));
			}
			
		}
		else{
			mensaje ="errors.select.item";
			this.addError("Error:", this.getResourceMessage(mensaje));
			return;
		}
		
		
		this.flagMostrarGrupos = Constants.ESTADO_ACTIVO; 
		
	}
	
	/**
	 * 
	 */
	public void reiniciarGruposJs(){
		
		calcularNumeroGrupos();
		MantenimientoPEDDefinirOfertaForm editForm = (MantenimientoPEDDefinirOfertaForm) this.formBusqueda;
		String grupoNumeroTotalGruposAnterior = null;
		String grupoNumeroTotalGrupos;
		String numeroGrupos;
		String numeroPaquetes;
		
		if(StringUtils.isBlank(this.grupoNumeroTotalGruposAnteriorJs)){
			this.grupoNumeroTotalGruposAnteriorJs= editForm.getGrupoNumeroTotalGrupos(); 
			grupoNumeroTotalGrupos= editForm.getGrupoNumeroTotalGrupos(); 
		}			
		else{
			grupoNumeroTotalGruposAnterior = this.grupoNumeroTotalGruposAnteriorJs;
			grupoNumeroTotalGrupos= editForm.getGrupoNumeroTotalGrupos(); 
			this.grupoNumeroTotalGruposAnteriorJs = editForm.getGrupoNumeroTotalGrupos();
		}
		
		if(StringUtils.isBlank(this.numeroGruposAnteriorJs)){
			this.numeroGruposAnteriorJs= editForm.getNumeroGrupos(); 
			numeroGrupos= editForm.getNumeroGrupos(); 
		}			
		else{
			this.numeroGruposAnterior = this.numeroGruposAnteriorJs;
			numeroGrupos= editForm.getNumeroGrupos(); 
			this.numeroGruposAnteriorJs = editForm.getNumeroGrupos();
		}
		
		if(StringUtils.isBlank(this.numeroPaquetesAnteriorJs)){
			this.numeroPaquetesAnteriorJs= editForm.getNumeroPaquetes(); 
			numeroPaquetes= editForm.getNumeroPaquetes(); 
		}			
		else{
			this.numeroPaquetesAnterior = this.numeroPaquetesAnteriorJs;
			numeroPaquetes= editForm.getNumeroPaquetes(); 
			this.numeroPaquetesAnteriorJs = editForm.getNumeroPaquetes();
		}
		

		this.numeroGruposCondicionantesAnterior = editForm.getNumeroGruposCondicionantes();
		this.numeroGruposCondicionadosAnterior = editForm.getNumeroGruposCondicionados();
		
		if(StringUtils.isBlank(grupoNumeroTotalGruposAnterior)|| StringUtils.equals(grupoNumeroTotalGruposAnterior, Constants.NUMERO_CERO))
		{
			reiniciargrupos();
			obtenerOidTipoEstrategia();
			setearTiposCuadre();
			actualizarExclusion(editForm.getCriteriosCodigoTipoRango());
			return;							
		}else 
		{
			if(!StringUtils.equals(grupoNumeroTotalGruposAnterior, grupoNumeroTotalGrupos))
			{
				String ventana = "confirmDialogReiniciarGrupo";
				String ventanaConfirmar = "PF('" + ventana + "_confirmationDialogConfirmarSalir').show()";
				this.getRequestContext().execute(ventanaConfirmar);
				return;
			}
		}
	}
	
	/**
	 * @param event
	 */
	public void reiniciargrupos(ActionEvent event){
		this.reiniciargrupos();
		obtenerOidTipoEstrategia();
		setearTiposCuadre();
		MantenimientoPEDDefinirOfertaForm editForm = (MantenimientoPEDDefinirOfertaForm) this.formBusqueda;
		actualizarExclusion(editForm.getCriteriosCodigoTipoRango());
	}
	
	/**
	 * 
	 */
	private void reiniciargrupos(){
		
		try {
			if(log.isDebugEnabled())
				log.debug("Reiniciando grupos...");
			
			MantenimientoPEDDefinirOfertaForm editForm = (MantenimientoPEDDefinirOfertaForm) this.formBusqueda;
			this.pedOfertaGruposMap = null;
			this.mPantallaPrincipalBean.setPedProductoAsociadoOfertaGrupoList(new ArrayList());	
			this.mPantallaPrincipalBean.setListaModelPedProductoAsociadoOfertaGrupoList(new DataTableModel(this.mPantallaPrincipalBean.getPedProductoAsociadoOfertaGrupoList()));
			
			//Creamos los enlaces para los grupos
			this.recargar("", editForm);
			//
			
			editForm.setOidIndicadorCuadre("");
			editForm.setGrupoFactorCuadre("");
			
			this.flagMostrarGrupos = Constants.ESTADO_INACTIVO;
			if(StringUtils.equals(editForm.getNumeroGrupos(), Constants.NUMERO_UNO) || 
			   StringUtils.isNotEmpty(editForm.getNumeroPaquetes()) || 
			   (StringUtils.isNotEmpty(editForm.getNumeroGruposCondicionantes()) && StringUtils.isNotEmpty(editForm.getNumeroGruposCondicionados())))
				this.flagMostrarGrupos = Constants.ESTADO_ACTIVO;
			
		} catch (Exception e) {

			this.addError("Error: ", this.obtieneMensajeErrorException(e));			
		}	
	}
	
	/**
	 * @param event
	 */
	public void setearValores(ActionEvent event){
		
		try {
			if(log.isDebugEnabled())
				log.debug("setearValores");
			
			MantenimientoPEDDefinirOfertaForm editForm = (MantenimientoPEDDefinirOfertaForm) this.formBusqueda;
			editForm.setNumeroGrupos(this.numeroGruposAnterior);
			editForm.setNumeroPaquetes(this.numeroPaquetesAnterior);
			editForm.setNumeroGruposCondicionantes(this.numeroGruposCondicionantesAnterior);
			editForm.setNumeroGruposCondicionados(this.numeroGruposCondicionadosAnterior);
			
		} catch (Exception e) {

			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		
	}
	
	/**
	 * @param seccion
	 * @param editForm
	 */
	private void recargar(String seccion, MantenimientoPEDDefinirOfertaForm editForm) {
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
				
		if(StringUtils.isNotBlank(editForm.getOidAcceso()))
		{
			//Cargamos la lista de subaccesos			
			LabelValue lista[] = ajaxService.getOidSubaccesosByOidAcceso(editForm.getOidAcceso());
			this.pedSubAccesoList = lista;
		}
		
		if(StringUtils.isNotBlank(editForm.getNumeroGrupos()))
		{
			//Cargamos el combo
			LabelValue lista[] = ajaxService.getTiposCuadre(editForm.getNumeroGrupos());
			this.pedIndicadorCuadreList = lista;
			this.pedIndicadorCuadreGrupoList = lista;
		}
		
		//Estrategia Condicionada
		if(StringUtils.equals(editForm.getOidTipoEstrategia(), Constants.PED_OID_TIPO_ESTRATEGIA_CUATRO))
		{
			LabelValue lista[] = ajaxService.getTiposCuadre("-1");
			this.pedIndicadorCuadreGrupoList = lista;
			
			//Determinamos el tipo de grupo, en el orden condicionante/ condiconado
			editForm.setGrupoTipo(obtenerTipoGrupo(editForm));
			//
		}
		
		if(StringUtils.equals(seccion, Constants.NUMERO_UNO) || StringUtils.equals(seccion, Constants.NUMERO_DOS))
		{
			editForm.setFlagCabeceraSoloLectura(true);
		}

		//Creamos los enlaces para los grupos
		generarEnlacesGrupos(editForm);
	}
	
	/**
	 * @param editForm
	 */
	private void generarEnlacesGrupos(MantenimientoPEDDefinirOfertaForm editForm) {
		if(StringUtils.isNotBlank(editForm.getGrupoNumeroTotalGrupos()))
		{
			int total = Integer.parseInt(editForm.getGrupoNumeroTotalGrupos());
					
			List grupoList = new ArrayList();
			for(int i=0; i<total; i++)
			{
				String numeroGrupo = Integer.valueOf(i + 1).toString();
				LabelValue grupo = new LabelValue(String.format("%s - %s", "GRUPO", numeroGrupo), numeroGrupo);
				grupoList.add(grupo);
			}
			
			this.pedOfertaGrupoActualList = grupoList;
			editForm.setGrupoNumeroMensaje(String.format("%s de %s", StringUtils.isNotBlank(editForm.getGrupoActual()) ? editForm.getGrupoActual() : Constants.NUMERO_UNO, editForm.getGrupoNumeroTotalGrupos()));
		}
	}

	/**
	 * 
	 */
	private void setearTiposCuadre(){
		MantenimientoPEDDefinirOfertaForm form = (MantenimientoPEDDefinirOfertaForm) this.formBusqueda;
		String numeroGrupos = form.getNumeroGrupos();
		
		if(StringUtils.isNotBlank(numeroGrupos))
		{
			if(StringUtils.equals(numeroGrupos, Constants.NUMERO_UNO))
			{
//				
//				document.forms[0].oidIndicadorCuadre.options[1].selected = true;
//				document.getElementById("oidIndicadorCuadre").disabled = true;
//				document.forms[0].grupoOidIndicadorCuadre.value = document.forms[0].oidIndicadorCuadre.value; //Cuadre al factor multiplo
				
				LabelValue[] lista = this.pedIndicadorCuadreList;				
				form.setOidIndicadorCuadre(lista[0].getValue());
				this.flagOidIndicadorCuadre = true;
				form.setGrupoOidIndicadorCuadre(form.getOidIndicadorCuadre());
			}
			else
			{
				this.flagOidIndicadorCuadre = false;
//				document.getElementById("oidIndicadorCuadre").disabled = false;
			}			
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
			
			MantenimientoPEDDefinirOfertaForm form = (MantenimientoPEDDefinirOfertaForm) this.formBusqueda;			
			
			if(StringUtils.isNotBlank(validacionesIniciales())){
				String msj = validacionesIniciales();
				this.addError("Error: ", this.getResourceMessage(msj));
				return;
			}
			
			this.mantenimientoPEDProductoAsociadoSearchAction.setOidCatalogoSeleccionado(form.getOidCatalogo());
			
			if(StringUtils.equals(accion, "NUMERO_UNO")){
				this.mantenimientoPEDProductoAsociadoSearchAction.setPedOfertaTipoBusquedaSeleccionada(Constants.NUMERO_UNO);			
			}
			
			if(StringUtils.equals(accion, "NUMERO_DOS")){
				if(!validacionesPopupTipoDos())
					return;
				else 
					this.mantenimientoPEDProductoAsociadoSearchAction.setPedOfertaTipoBusquedaSeleccionada(Constants.NUMERO_DOS);
				
			}
			
			this.mantenimientoPEDProductoAsociadoSearchAction.setPedOfertaCatalogoSeleccionado(form.getOidCatalogo());
			this.mantenimientoPEDProductoAsociadoSearchAction.setPedOfertaEstrategiaSeleccionada(form.getOidEstrategia());
			this.mantenimientoPEDProductoAsociadoSearchAction.setPedOfertaTipoEstrategiaSeleccionada(form.getOidTipoEstrategia());
			this.mantenimientoPEDProductoAsociadoSearchAction.setPedEstrategiaList(this.pedEstrategiaList);
			this.mantenimientoPEDProductoAsociadoSearchAction.setSalirCambioCodigoVentaModificaCUV(false);
			this.mantenimientoPEDProductoAsociadoSearchAction.setSalirCambioCodigoVentaModificaCUVCompuestaFija(false);
			this.mantenimientoPEDProductoAsociadoSearchAction.inicializarValoresSearch();			
			this.redireccionarPagina("mantenimientoPEDProductoAsociadoList");	
			
		} catch (Exception e) {
			
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	/**
	 * @return
	 */
	private boolean validacionesPopupTipoDos(){
		
		MantenimientoPEDDefinirOfertaForm form = (MantenimientoPEDDefinirOfertaForm) this.formBusqueda;
		String mensaje = null;
		boolean flag = true;
		if(this.flagNumeroGruposObligatorio){			
			if(StringUtils.isBlank(form.getNumeroGrupos())){
				mensaje = "mantenimientoPEDDefinirOfertaForm.numeroGrupos.requerido";
				this.addError("Error: ", this.getResourceMessage(mensaje));
				flag = false;
			}			
		}
		
		if(this.flagNumeroPaquetesObligatorio)
		{			
			if(StringUtils.isBlank(form.getNumeroPaquetes())){
				mensaje = "mantenimientoPEDDefinirOfertaForm.numeroPaquetes.requerido";
				this.addError("Error: ", this.getResourceMessage(mensaje));
				flag = false;
			}			
		}
		
		if(this.flagIndicadorCuadreObligatorio)
		{			
			if(StringUtils.isBlank(form.getOidIndicadorCuadre())){
				mensaje = "mantenimientoPEDDefinirOfertaForm.oidIndicadorCuadre.requerido";
				this.addError("Error: ", this.getResourceMessage(mensaje));
				flag = false;
			}			
		}
		
		if(this.flagNumeroGruposCondicionantesObligatorio)
		{			
			if(StringUtils.isBlank(form.getNumeroGruposCondicionantes())){
				mensaje = "mantenimientoPEDDefinirOfertaForm.numeroGruposCondicionantes.requerido";
				this.addError("Error: ", this.getResourceMessage(mensaje));
				flag = false;
			}			
		}
		
		if(this.flagCondicionantesObligatorio)
		{			
			if(StringUtils.isBlank(form.getCondicionantes())){
				mensaje = "mantenimientoPEDDefinirOfertaForm.condicionantes.requerido";
				this.addError("Error: ", this.getResourceMessage(mensaje));
				flag = false;
			}			
		}
		
		if(this.flagNumeroGruposCondicionadosObligatorio)
		{			
			if(StringUtils.isBlank(form.getNumeroGruposCondicionados())){
				mensaje = "mantenimientoPEDDefinirOfertaForm.numeroGruposCondicionados.requerido";
				this.addError("Error: ", this.getResourceMessage(mensaje));
				flag = false;
			}			
		}
		
		if(this.flagCondicionadosObligatorio)
		{			
			if(StringUtils.isBlank(form.getCondicionados())){
				mensaje = "mantenimientoPEDDefinirOfertaForm.condicionados.requerido";
				this.addError("Error: ", this.getResourceMessage(mensaje));
				flag = false;
			}			
		}
		
		if(StringUtils.isBlank(form.getGrupoFactorCuadre())){
			mensaje = "mantenimientoPEDDefinirOfertaForm.grupoFactorCuadre.requerido";
			this.addError("Error: ", this.getResourceMessage(mensaje));
			flag = false;
		}	
		

		
		return flag;
	}
	
	/**
	 * @return
	 */
	private String validacionesIniciales(){
		
		MantenimientoPEDDefinirOfertaForm form = (MantenimientoPEDDefinirOfertaForm) this.formBusqueda;
		String mensaje = null;
		if(StringUtils.isBlank(form.getOidEstrategia())){
			mensaje = "mantenimientoPEDDefinirOfertaForm.oidEstrategia.requerido";
			return mensaje;
		}
		
		if(StringUtils.isBlank(form.getOidCatalogo())){
			mensaje = "mantenimientoPEDDefinirOfertaForm.oidCatalogo.requerido";
			return mensaje;
		}
		
		return mensaje;
	}
	
	/**
	 * @param event
	 */
	public void quitarproductosgrupo(ActionEvent event){
		
		log.debug("Enter method - quitarproductosgrupo");
		try {
			
			MantenimientoPEDDefinirOfertaForm editForm = (MantenimientoPEDDefinirOfertaForm) this.formBusqueda;
			String mensaje = null;
			if(this.mPantallaPrincipalBean.getPedProductoAsociadoOfertaGrupoList().size() == 0)
				return;
			
			if(this.columnasSeleccionadas!=null && this.columnasSeleccionadas.size() > 0){
		
					if(log.isDebugEnabled())
						log.debug("Quitando productos del grupo...");
					
					List listaSeleccionados  = this.mPantallaPrincipalBean.getPedProductoAsociadoOfertaGrupoList();
					
					if(this.columnasSeleccionadas != null && this.columnasSeleccionadas.size() > 0)
					{
						for(int i=0; i<columnasSeleccionadas.size(); i++)
						{
							Map mapListaSeleccionados = (Map) columnasSeleccionadas.get(i);
							String oidProductoSeleccionado =  MapUtils.getString(mapListaSeleccionados, "oidProducto");;
							Iterator litr = listaSeleccionados.iterator();
						      while(litr.hasNext()) {
						         Map seleccionado = (Map)litr.next();
						         String oidProducto = MapUtils.getString(seleccionado, "oidProducto"); 
						         
						         if(StringUtils.equals(oidProductoSeleccionado, oidProducto))
						         {
						        	 litr.remove();
						        	 break;
						         }			         
						      }
						}
					}
					
					this.mPantallaPrincipalBean.setPedProductoAsociadoOfertaGrupoList(listaSeleccionados);
					this.mPantallaPrincipalBean.setListaModelPedProductoAsociadoOfertaGrupoList(new DataTableModel(this.mPantallaPrincipalBean.getPedProductoAsociadoOfertaGrupoList()));
					
					//Actualizamos la data en el map de grupos
					Map grupos = this.pedOfertaGruposMap;
					if(grupos != null)
					{
						Map grupoModificado = (Map)MapUtils.getObject(grupos, editForm.getGrupoActual());
						grupoModificado.put("listaProductos", listaSeleccionados);		
						grupos.put(editForm.getGrupoActual(), grupoModificado);
						
						this.pedOfertaGruposMap = grupos;
					}
				
			}
			else{
				mensaje ="errors.select.items";
				this.addError("Error:", this.getResourceMessage(mensaje));
				return;
			}
						
			this.flagMostrarGrupos = Constants.ESTADO_ACTIVO; 
			 
		} catch (Exception e) {
			
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		
	}
	

	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {
		
		String mensaje = null;
		MantenimientoPEDDefinirOfertaForm editForm = (MantenimientoPEDDefinirOfertaForm) this.formBusqueda;
		
		if (accion.equals("GUARDAR")) {
			
			if(StringUtils.isNotBlank(validacionesIniciales())){
				mensaje = validacionesIniciales();				
				return this.getResourceMessage(mensaje);
			}
			
			
			//Validamos los campos obligatorios	    	
	    	if(this.flagCondicionesPromocionOidTipoCuadreObligatorio)
	    	{
				if(StringUtils.isBlank(editForm.getCondicionesPromocionOidTipoCuadre()))
				{
					mensaje = "mantenimientoPEDDefinirOfertaForm.condicionesPromocionOidTipoCuadre.requerido";
					return this.getResourceMessage(mensaje);
				}
	    	}

			if(this.flagCondicionesPromocionFactorCuadreObligatorio)
	    	{
				if(StringUtils.isBlank(editForm.getCondicionesPromocionFactorCuadre()))
				{
					mensaje = "mantenimientoPEDDefinirOfertaForm.condicionesPromocionFactorCuadre.requerido";
					return this.getResourceMessage(mensaje);
				}
	    	}	 
			
			String oidEstrategia = editForm.getOidTipoEstrategia();
			boolean pedProductoAsociadoOfertaListVacio = true;
			boolean pedProductoAsociadoOfertaGrupoListVacio = true;
			boolean pedOfertaCriteriosListVacio = true;
			
			//validar grid productosasociados
			if( this.mPantallaPrincipalBean.getPedProductoAsociadoOfertaList() != null)
				if(this.mPantallaPrincipalBean.getPedProductoAsociadoOfertaList().size() >0)
					pedProductoAsociadoOfertaListVacio = false;
				
			//validar grupos
			if(this.mPantallaPrincipalBean.getPedProductoAsociadoOfertaGrupoList() != null)
				if(this.mPantallaPrincipalBean.getPedProductoAsociadoOfertaGrupoList().size() >0)
					pedProductoAsociadoOfertaGrupoListVacio = false;
			
			//validar condicionantes
			if(this.pedOfertaCriteriosList != null)
				if(this.pedOfertaCriteriosList.size() >0)
					pedOfertaCriteriosListVacio = false;
			
			if(pedProductoAsociadoOfertaListVacio && (StringUtils.equals(oidEstrategia, this.oidTipoEstrategia1) || StringUtils.equals(oidEstrategia, this.oidTipoEstrategia2) || StringUtils.equals(oidEstrategia, this.oidTipoEstrategia5)))
			{
				mensaje = "mantenimientoPEDDefinirOfertaForm.error.productos.asociados";
				return this.getResourceMessage(mensaje);
			}
			
			if(pedProductoAsociadoOfertaGrupoListVacio && (StringUtils.equals(oidEstrategia, this.oidTipoEstrategia3) || StringUtils.equals(oidEstrategia, this.oidTipoEstrategia4) || StringUtils.equals(oidEstrategia, this.oidTipoEstrategia6) || StringUtils.equals(oidEstrategia, this.oidTipoEstrategia7)))			
			{
				mensaje = "mantenimientoPEDDefinirOfertaForm.error.grupo.sin.productos";
				return this.getResourceMessage(mensaje);
			}
			
			if(pedOfertaCriteriosListVacio && (StringUtils.equals(oidEstrategia, this.oidTipoEstrategia5) || StringUtils.equals(oidEstrategia, this.oidTipoEstrategia6) || StringUtils.equals(oidEstrategia, this.oidTipoEstrategia7)))
			{
				mensaje = "mantenimientoPEDDefinirOfertaForm.error.criterios";
				return this.getResourceMessage(mensaje);
			}
			


			
		}		
		return mensaje;		
	}
	
	public void guardar (ActionEvent event){
		
		log.debug("Enter method - guardar");
		
		try {
			
			MantenimientoPEDConfiguracionOfertaService service = (MantenimientoPEDConfiguracionOfertaService)getBean("spusicc.mantenimientoPEDConfiguracionOfertaService");
			MantenimientoPEDDefinirOfertaForm editForm = (MantenimientoPEDDefinirOfertaForm)this.formBusqueda;
			String mensaje = null;
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			
			String oi = editForm.getGrupoOidIndicadorCuadre();
			if(StringUtils.isNotBlank(oi))
				this.flagGrupoOidIndicadorCuadre = false;
			
			
			if(this.isBoolFlagDespachoCompleto())
				editForm.setFlagDespachoCompleto(Constants.NUMERO_UNO);
			else
				editForm.setFlagDespachoCompleto(null);
			
			if(this.isBoolFlagDespachoAutomatico())
				editForm.setFlagDespachoAutomatico(Constants.NUMERO_UNO);
			else
				editForm.setFlagDespachoAutomatico(null);
			
			
			if(editForm.isNewRecord())
			{
				Oferta oferta = new Oferta();

				oferta.setOidEstrategia(StringUtils.isBlank(editForm.getOidEstrategia()) ? null : editForm.getOidEstrategia());
				oferta.setNumeroGrupos(StringUtils.isBlank(editForm.getNumeroGrupos()) ? null : editForm.getNumeroGrupos());
				oferta.setNumeroGruposCondicionados(StringUtils.isBlank(editForm.getNumeroGruposCondicionados()) ? null : editForm.getNumeroGruposCondicionados());
				oferta.setNumeroGruposCondicionantes(StringUtils.isBlank(editForm.getNumeroGruposCondicionantes()) ? null : editForm.getNumeroGruposCondicionantes());
				oferta.setNumeroPaquetes(StringUtils.isBlank(editForm.getNumeroPaquetes()) ? null : editForm.getNumeroPaquetes());
				oferta.setCondicionantes(StringUtils.isBlank(editForm.getCondicionantes()) ? null : editForm.getCondicionantes());
				oferta.setCondicionados(StringUtils.isBlank(editForm.getCondicionados()) ? null : editForm.getCondicionados());
				oferta.setOidFormaPago(StringUtils.isBlank(editForm.getOidFormaPago()) ? null : editForm.getOidFormaPago());
				oferta.setOidSubacceso(StringUtils.isBlank(editForm.getOidSubacceso()) ? null : editForm.getOidSubacceso());
				oferta.setOidTipoEstrategia(StringUtils.isBlank(editForm.getOidTipoEstrategia()) ? null : editForm.getOidTipoEstrategia());
				oferta.setOidAcceso(StringUtils.isBlank(editForm.getOidAcceso()) ? null : editForm.getOidAcceso());
				oferta.setOidMatriz(StringUtils.isBlank(editForm.getOidMatriz()) ? null : editForm.getOidMatriz());
				oferta.setFlagDespachoCompleto(StringUtils.isBlank(editForm.getFlagDespachoCompleto()) ? null : editForm.getFlagDespachoCompleto());
				oferta.setFlagDespachoAutomatico(StringUtils.isBlank(editForm.getFlagDespachoAutomatico()) ? null : editForm.getFlagDespachoAutomatico());
				oferta.setOidCatalogo(StringUtils.isBlank(editForm.getOidCatalogo()) ? null : editForm.getOidCatalogo());
				
				//Guardamos la data del grupo que se esta viendo en pantalla
				Map grupos = this.pedOfertaGruposMap;
				
				if(grupos == null)
					grupos = new HashMap();
				
				guardarGrupoAnterior(editForm, grupos);
				//
				
				//Grupos
				if(StringUtils.isNotBlank(editForm.getGrupoNumeroTotalGrupos()))
				{
					int totalGrupos = Integer.parseInt(editForm.getGrupoNumeroTotalGrupos());
					List listaGrupos = new ArrayList();
					for(int i=1; i<=totalGrupos; i++)
					{
						Map grupo = (Map)MapUtils.getObject(grupos, Integer.toString(i));
						GrupoOferta grupoOferta = new GrupoOferta();
						grupoOferta.setNumero(Integer.toString(i));
						grupoOferta.setFactorCuadre(MapUtils.getString(grupo, "grupoFactorCuadre"));
						grupoOferta.setOidIndicadorCuadreTipoEstrategia(MapUtils.getString(grupo, "grupoOidIndicador"));
						
						grupoOferta.setFlagCondicionante(Constants.NUMERO_CERO);
						grupoOferta.setFlagCondicionado(Constants.NUMERO_CERO);
						grupoOferta.setFlagGrupo(Constants.NUMERO_CERO);
						
						String grupoTipo = MapUtils.getString(grupo, "grupoTipo");
						
						if(StringUtils.equals(grupoTipo, Constants.PED_TIPO_GRUPO_CONDICIONANTE))
						{
							grupoOferta.setFlagCondicionante(Constants.NUMERO_UNO);						
						}
						else if(StringUtils.equals(grupoTipo, Constants.PED_TIPO_GRUPO_CONDICIONADO))
						{
							grupoOferta.setFlagCondicionado(Constants.NUMERO_UNO);						
						}
						else if(StringUtils.equals(grupoTipo, Constants.PED_TIPO_GRUPO_GRUPO)) {
							grupoOferta.setFlagGrupo(Constants.NUMERO_UNO);
						}
						
						//De cada uno de los grupos sacamos los productos, y enviamos como detalles de oferta
						List listaProductos = (List)MapUtils.getObject(grupo, "listaProductos");
						
						if(StringUtils.isBlank(grupoOferta.getFactorCuadre()) || listaProductos == null || listaProductos.size() == 0)
						{
							mensaje = "mantenimientoPEDDefinirOfertaForm.error.grupo.sin.productos";
							this.addError("Error: ", this.getResourceMessage(mensaje));
							return;
						}
						
						List detalles = getDetallesOferta(editForm, listaProductos);
						grupoOferta.setDetalles(detalles);
						//
						
						listaGrupos.add(grupoOferta);
						oferta.setGrupos(listaGrupos);
					}
				}
				
				//Lista de Producto asociados
				List listaProductos = this.mPantallaPrincipalBean.getPedProductoAsociadoOfertaList();
				List detalles = getDetallesOferta(editForm, listaProductos);
				oferta.setDetalles(detalles);
				//
				
				//Condiciones de promocion
				if(StringUtils.isNotBlank(editForm.getCondicionesPromocionOidTipoCuadre()) && StringUtils.isNotBlank(editForm.getCondicionesPromocionFactorCuadre()))
				{
					CondicionOferta condicion = new CondicionOferta();
					condicion.setFactorCuadre(editForm.getCondicionesPromocionFactorCuadre());
					condicion.setOidTipoCuadre(editForm.getCondicionesPromocionOidTipoCuadre());
					
					oferta.setCondicion(condicion);
				}
				//
				
				//Criterios
				List criterioList = this.pedOfertaCriteriosList;
				if(criterioList != null && criterioList.size() > 0)
				{
					List listaRangos = new ArrayList();
					for(int i=0; i<criterioList.size(); i++)
					{
						Map criterio = (Map)criterioList.get(i);
						RangoPromocion rangoPromocion = new RangoPromocion();
						
						rangoPromocion.setOidCatalogo(MapUtils.getString(criterio, "oidCatalogo"));
						rangoPromocion.setCodigoTipoRango(MapUtils.getString(criterio, "codigoTipoRango"));
						rangoPromocion.setNumeroRangoInterno(Integer.toString(i + 1));
						
						if(StringUtils.equals(rangoPromocion.getCodigoTipoRango(), Constants.PED_OFERTAS_POR_CONCURSO_TIPO_RANGO_RANGO))
						{
							rangoPromocion.setValorInicial(MapUtils.getString(criterio, "paginaInicial"));
							rangoPromocion.setValorFinal(MapUtils.getString(criterio, "paginaFinal"));
						}
						else if(StringUtils.equals(rangoPromocion.getCodigoTipoRango(), Constants.PED_OFERTAS_POR_CONCURSO_TIPO_RANGO_PRODUCTO))
						{
							rangoPromocion.setValorInicial(MapUtils.getString(criterio, "oidProducto"));
							rangoPromocion.setValorFinal(null);
						}
						
						rangoPromocion.setIndicadorExclusion(MapUtils.getString(criterio, "indicadorExclusion"));
						
						listaRangos.add(rangoPromocion);
					}
					
					oferta.setRangosPromocion(listaRangos);
				}
				//
				
				service.insertOferta(oferta, usuario);
				this.setViewAtributes();
				
				mensaje = "mantenimientoPEDDefinirOfertaForm.grabada";
				this.addInfo("Info: ", this.getResourceMessage(mensaje));
				
				obtenerOidTipoEstrategia();
				actualizarExclusion("");
				setearTiposCuadre();
			}	
			
		} catch (Exception e) {

			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		
		this.flagMostrarGrupos = 	Constants.ESTADO_ACTIVO;		
	}
	
	/**
	 * @param editForm
	 * @param listaProductos
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private List getDetallesOferta(MantenimientoPEDDefinirOfertaForm editForm, List listaProductos) throws IllegalAccessException, InvocationTargetException {
		
		List detalles = new ArrayList();
		
		if(listaProductos != null && listaProductos.size() > 0)
		{						
			for(int j=0; j<listaProductos.size(); j++)
			{
				Map producto = (Map)listaProductos.get(j);
				DetalleOferta detalleOferta = new DetalleOferta();
				
				BigDecimal precioUnitario = new BigDecimal(MapUtils.getDoubleValue(producto, "precioCatalogo", 0) / MapUtils.getDoubleValue(producto, "factorRepeticion", 0));
				//DecimalFormat f = new DecimalFormat("##,00");
				BigDecimal round = precioUnitario.setScale(2, BigDecimal.ROUND_HALF_EVEN);
				producto.put("precioUnitario", String.valueOf(round));
				
				BeanUtils.copyProperties(detalleOferta, producto);
			
				//<Si el tipo de estrategia es 1 entonces 1, sino si es 2 entonces 1 si digitable está marcado y 0 si esta desmarcado, sino 0>, 
				if(StringUtils.equals(editForm.getOidTipoEstrategia(), Constants.PED_OID_TIPO_ESTRATEGIA_UNO))
				{
					detalleOferta.setFlagPrincipal(Constants.NUMERO_UNO);
				}
				else if(StringUtils.equals(editForm.getOidTipoEstrategia(), Constants.PED_OID_TIPO_ESTRATEGIA_DOS))
				{
					if(StringUtils.equals(detalleOferta.getFlagDigitable(), Constants.NUMERO_UNO))
					{
						detalleOferta.setFlagPrincipal(Constants.NUMERO_UNO);
					}
					else
					{
						detalleOferta.setFlagPrincipal(Constants.NUMERO_CERO);
					}
				}
				else
				{
					detalleOferta.setFlagPrincipal(Constants.NUMERO_CERO);
				}
				//

				detalleOferta.setOidCatalogo(editForm.getOidCatalogo());
				detalleOferta.setOidFormaPago(editForm.getOidFormaPago());	
				
				detalles.add(detalleOferta);
			}												
		}
		
		return detalles;
	}
	

	/**
	 * @return the pedEstrategiaList
	 */
	public List getPedEstrategiaList() {
		return pedEstrategiaList;
	}

	/**
	 * @param pedEstrategiaList the pedEstrategiaList to set
	 */
	public void setPedEstrategiaList(List pedEstrategiaList) {
		this.pedEstrategiaList = pedEstrategiaList;
	}

	/**
	 * @return the pedAccesoList
	 */
	public List getPedAccesoList() {
		return pedAccesoList;
	}

	/**
	 * @param pedAccesoList the pedAccesoList to set
	 */
	public void setPedAccesoList(List pedAccesoList) {
		this.pedAccesoList = pedAccesoList;
	}

	/**
	 * @return the pedCondicionPromocionTipoCuadreList
	 */
	public List getPedCondicionPromocionTipoCuadreList() {
		return pedCondicionPromocionTipoCuadreList;
	}

	/**
	 * @param pedCondicionPromocionTipoCuadreList the pedCondicionPromocionTipoCuadreList to set
	 */
	public void setPedCondicionPromocionTipoCuadreList(
			List pedCondicionPromocionTipoCuadreList) {
		this.pedCondicionPromocionTipoCuadreList = pedCondicionPromocionTipoCuadreList;
	}

	/**
	 * @return the siccFormaPagoList
	 */
	public List getSiccFormaPagoList() {
		return siccFormaPagoList;
	}

	/**
	 * @param siccFormaPagoList the siccFormaPagoList to set
	 */
	public void setSiccFormaPagoList(List siccFormaPagoList) {
		this.siccFormaPagoList = siccFormaPagoList;
	}

	/**
	 * @return the flagMostrarGrupos
	 */
	public String getFlagMostrarGrupos() {
		return flagMostrarGrupos;
	}

	/**
	 * @param flagMostrarGrupos the flagMostrarGrupos to set
	 */
	public void setFlagMostrarGrupos(String flagMostrarGrupos) {
		this.flagMostrarGrupos = flagMostrarGrupos;
	}

	/**
	 * @return the pedSubAccesoList
	 */
	public LabelValue[] getPedSubAccesoList() {
		return pedSubAccesoList;
	}

	/**
	 * @param pedSubAccesoList the pedSubAccesoList to set
	 */
	public void setPedSubAccesoList(LabelValue[] pedSubAccesoList) {
		this.pedSubAccesoList = pedSubAccesoList;
	}

	/**
	 * @return the pedIndicadorCuadreList
	 */
	public LabelValue[] getPedIndicadorCuadreList() {
		return pedIndicadorCuadreList;
	}

	/**
	 * @param pedIndicadorCuadreList the pedIndicadorCuadreList to set
	 */
	public void setPedIndicadorCuadreList(LabelValue[] pedIndicadorCuadreList) {
		this.pedIndicadorCuadreList = pedIndicadorCuadreList;
	}

	/**
	 * @return the boolFlagDespachoCompleto
	 */
	public boolean isBoolFlagDespachoCompleto() {
		return boolFlagDespachoCompleto;
	}

	/**
	 * @param boolFlagDespachoCompleto the boolFlagDespachoCompleto to set
	 */
	public void setBoolFlagDespachoCompleto(boolean boolFlagDespachoCompleto) {
		this.boolFlagDespachoCompleto = boolFlagDespachoCompleto;
	}

	/**
	 * @return the boolFlagDespachoAutomatico
	 */
	public boolean isBoolFlagDespachoAutomatico() {
		return boolFlagDespachoAutomatico;
	}

	/**
	 * @param boolFlagDespachoAutomatico the boolFlagDespachoAutomatico to set
	 */
	public void setBoolFlagDespachoAutomatico(boolean boolFlagDespachoAutomatico) {
		this.boolFlagDespachoAutomatico = boolFlagDespachoAutomatico;
	}

	/**
	 * @return the pedOfertaGrupoActualList
	 */
	public List getPedOfertaGrupoActualList() {
		return pedOfertaGrupoActualList;
	}

	/**
	 * @param pedOfertaGrupoActualList the pedOfertaGrupoActualList to set
	 */
	public void setPedOfertaGrupoActualList(List pedOfertaGrupoActualList) {
		this.pedOfertaGrupoActualList = pedOfertaGrupoActualList;
	}

	/**
	 * @return the pedIndicadorCuadreGrupoList
	 */
	public LabelValue[] getPedIndicadorCuadreGrupoList() {
		return pedIndicadorCuadreGrupoList;
	}

	/**
	 * @param pedIndicadorCuadreGrupoList the pedIndicadorCuadreGrupoList to set
	 */
	public void setPedIndicadorCuadreGrupoList(
			LabelValue[] pedIndicadorCuadreGrupoList) {
		this.pedIndicadorCuadreGrupoList = pedIndicadorCuadreGrupoList;
	}
	
	/**
	 * @return the columnasSeleccionadas
	 */
	public List getColumnasSeleccionadas() {
		return columnasSeleccionadas;
	}

	/**
	 * @param columnasSeleccionadas the columnasSeleccionadas to set
	 */
	public void setColumnasSeleccionadas(List columnasSeleccionadas) {
		this.columnasSeleccionadas = columnasSeleccionadas;
	}

	/**
	 * @return the rango
	 */
	public String getRango() {
		return rango;
	}

	/**
	 * @param rango the rango to set
	 */
	public void setRango(String rango) {
		this.rango = rango;
	}

	/**
	 * @return the producto
	 */
	public String getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(String producto) {
		this.producto = producto;
	}

	/**
	 * @return the num_uno
	 */
	public String getNum_uno() {
		return num_uno;
	}

	/**
	 * @param num_uno the num_uno to set
	 */
	public void setNum_uno(String num_uno) {
		this.num_uno = num_uno;
	}

	/**
	 * @return the pedOfertaCriteriosList
	 */
	public List getPedOfertaCriteriosList() {
		return pedOfertaCriteriosList;
	}

	/**
	 * @param pedOfertaCriteriosList the pedOfertaCriteriosList to set
	 */
	public void setPedOfertaCriteriosList(List pedOfertaCriteriosList) {
		this.pedOfertaCriteriosList = pedOfertaCriteriosList;
	}

	/**
	 * @return the listaModelPedOfertaCriteriosList
	 */
	public DataTableModel getListaModelPedOfertaCriteriosList() {
		return listaModelPedOfertaCriteriosList;
	}

	/**
	 * @param listaModelPedOfertaCriteriosList the listaModelPedOfertaCriteriosList to set
	 */
	public void setListaModelPedOfertaCriteriosList(
			DataTableModel listaModelPedOfertaCriteriosList) {
		this.listaModelPedOfertaCriteriosList = listaModelPedOfertaCriteriosList;
	}

	/**
	 * @return the flagGridProductos
	 */
	public boolean isFlagGridProductos() {
		return flagGridProductos;
	}

	/**
	 * @param flagGridProductos the flagGridProductos to set
	 */
	public void setFlagGridProductos(boolean flagGridProductos) {
		this.flagGridProductos = flagGridProductos;
	}

	/**
	 * @return the flagGridDatosGrupos
	 */
	public boolean isFlagGridDatosGrupos() {
		return flagGridDatosGrupos;
	}

	/**
	 * @param flagGridDatosGrupos the flagGridDatosGrupos to set
	 */
	public void setFlagGridDatosGrupos(boolean flagGridDatosGrupos) {
		this.flagGridDatosGrupos = flagGridDatosGrupos;
	}

	/**
	 * @return the flagCondicionesPromocion
	 */
	public boolean isFlagCondicionesPromocion() {
		return flagCondicionesPromocion;
	}

	/**
	 * @param flagCondicionesPromocion the flagCondicionesPromocion to set
	 */
	public void setFlagCondicionesPromocion(boolean flagCondicionesPromocion) {
		this.flagCondicionesPromocion = flagCondicionesPromocion;
	}

	/**
	 * @return the num_cero
	 */
	public String getNum_cero() {
		return num_cero;
	}

	/**
	 * @param num_cero the num_cero to set
	 */
	public void setNum_cero(String num_cero) {
		this.num_cero = num_cero;
	}

	/**
	 * @return the oidTipoEstrategia1
	 */
	public String getOidTipoEstrategia1() {
		return oidTipoEstrategia1;
	}

	/**
	 * @param oidTipoEstrategia1 the oidTipoEstrategia1 to set
	 */
	public void setOidTipoEstrategia1(String oidTipoEstrategia1) {
		this.oidTipoEstrategia1 = oidTipoEstrategia1;
	}

	/**
	 * @return the oidTipoEstrategia2
	 */
	public String getOidTipoEstrategia2() {
		return oidTipoEstrategia2;
	}

	/**
	 * @param oidTipoEstrategia2 the oidTipoEstrategia2 to set
	 */
	public void setOidTipoEstrategia2(String oidTipoEstrategia2) {
		this.oidTipoEstrategia2 = oidTipoEstrategia2;
	}

	/**
	 * @return the oidTipoEstrategia3
	 */
	public String getOidTipoEstrategia3() {
		return oidTipoEstrategia3;
	}

	/**
	 * @param oidTipoEstrategia3 the oidTipoEstrategia3 to set
	 */
	public void setOidTipoEstrategia3(String oidTipoEstrategia3) {
		this.oidTipoEstrategia3 = oidTipoEstrategia3;
	}

	/**
	 * @return the oidTipoEstrategia4
	 */
	public String getOidTipoEstrategia4() {
		return oidTipoEstrategia4;
	}

	/**
	 * @param oidTipoEstrategia4 the oidTipoEstrategia4 to set
	 */
	public void setOidTipoEstrategia4(String oidTipoEstrategia4) {
		this.oidTipoEstrategia4 = oidTipoEstrategia4;
	}

	/**
	 * @return the oidTipoEstrategia5
	 */
	public String getOidTipoEstrategia5() {
		return oidTipoEstrategia5;
	}

	/**
	 * @param oidTipoEstrategia5 the oidTipoEstrategia5 to set
	 */
	public void setOidTipoEstrategia5(String oidTipoEstrategia5) {
		this.oidTipoEstrategia5 = oidTipoEstrategia5;
	}

	/**
	 * @return the oidTipoEstrategia6
	 */
	public String getOidTipoEstrategia6() {
		return oidTipoEstrategia6;
	}

	/**
	 * @param oidTipoEstrategia6 the oidTipoEstrategia6 to set
	 */
	public void setOidTipoEstrategia6(String oidTipoEstrategia6) {
		this.oidTipoEstrategia6 = oidTipoEstrategia6;
	}

	/**
	 * @return the oidTipoEstrategia7
	 */
	public String getOidTipoEstrategia7() {
		return oidTipoEstrategia7;
	}

	/**
	 * @param oidTipoEstrategia7 the oidTipoEstrategia7 to set
	 */
	public void setOidTipoEstrategia7(String oidTipoEstrategia7) {
		this.oidTipoEstrategia7 = oidTipoEstrategia7;
	}

	/**
	 * @return the flagCondicionesPromocionOidTipoCuadreObligatorio
	 */
	public boolean isFlagCondicionesPromocionOidTipoCuadreObligatorio() {
		return flagCondicionesPromocionOidTipoCuadreObligatorio;
	}

	/**
	 * @param flagCondicionesPromocionOidTipoCuadreObligatorio the flagCondicionesPromocionOidTipoCuadreObligatorio to set
	 */
	public void setFlagCondicionesPromocionOidTipoCuadreObligatorio(
			boolean flagCondicionesPromocionOidTipoCuadreObligatorio) {
		this.flagCondicionesPromocionOidTipoCuadreObligatorio = flagCondicionesPromocionOidTipoCuadreObligatorio;
	}

	/**
	 * @return the flagCondicionesPromocionFactorCuadreObligatorio
	 */
	public boolean isFlagCondicionesPromocionFactorCuadreObligatorio() {
		return flagCondicionesPromocionFactorCuadreObligatorio;
	}

	/**
	 * @param flagCondicionesPromocionFactorCuadreObligatorio the flagCondicionesPromocionFactorCuadreObligatorio to set
	 */
	public void setFlagCondicionesPromocionFactorCuadreObligatorio(
			boolean flagCondicionesPromocionFactorCuadreObligatorio) {
		this.flagCondicionesPromocionFactorCuadreObligatorio = flagCondicionesPromocionFactorCuadreObligatorio;
	}

	/**
	 * @return the flagReload
	 */
	public boolean isFlagReload() {
		return flagReload;
	}

	/**
	 * @param flagReload the flagReload to set
	 */
	public void setFlagReload(boolean flagReload) {
		this.flagReload = flagReload;
	}

	/**
	 * @return the flagNumeroGruposObligatorio
	 */
	public boolean isFlagNumeroGruposObligatorio() {
		return flagNumeroGruposObligatorio;
	}

	/**
	 * @param flagNumeroGruposObligatorio the flagNumeroGruposObligatorio to set
	 */
	public void setFlagNumeroGruposObligatorio(boolean flagNumeroGruposObligatorio) {
		this.flagNumeroGruposObligatorio = flagNumeroGruposObligatorio;
	}

	/**
	 * @return the flagNumeroPaquetesObligatorio
	 */
	public boolean isFlagNumeroPaquetesObligatorio() {
		return flagNumeroPaquetesObligatorio;
	}

	/**
	 * @param flagNumeroPaquetesObligatorio the flagNumeroPaquetesObligatorio to set
	 */
	public void setFlagNumeroPaquetesObligatorio(
			boolean flagNumeroPaquetesObligatorio) {
		this.flagNumeroPaquetesObligatorio = flagNumeroPaquetesObligatorio;
	}

	/**
	 * @return the flagIndicadorCuadreObligatorio
	 */
	public boolean isFlagIndicadorCuadreObligatorio() {
		return flagIndicadorCuadreObligatorio;
	}

	/**
	 * @param flagIndicadorCuadreObligatorio the flagIndicadorCuadreObligatorio to set
	 */
	public void setFlagIndicadorCuadreObligatorio(
			boolean flagIndicadorCuadreObligatorio) {
		this.flagIndicadorCuadreObligatorio = flagIndicadorCuadreObligatorio;
	}

	/**
	 * @return the flagNumeroGruposCondicionantesObligatorio
	 */
	public boolean isFlagNumeroGruposCondicionantesObligatorio() {
		return flagNumeroGruposCondicionantesObligatorio;
	}

	/**
	 * @param flagNumeroGruposCondicionantesObligatorio the flagNumeroGruposCondicionantesObligatorio to set
	 */
	public void setFlagNumeroGruposCondicionantesObligatorio(
			boolean flagNumeroGruposCondicionantesObligatorio) {
		this.flagNumeroGruposCondicionantesObligatorio = flagNumeroGruposCondicionantesObligatorio;
	}

	/**
	 * @return the flagCondicionantesObligatorio
	 */
	public boolean isFlagCondicionantesObligatorio() {
		return flagCondicionantesObligatorio;
	}

	/**
	 * @param flagCondicionantesObligatorio the flagCondicionantesObligatorio to set
	 */
	public void setFlagCondicionantesObligatorio(
			boolean flagCondicionantesObligatorio) {
		this.flagCondicionantesObligatorio = flagCondicionantesObligatorio;
	}

	/**
	 * @return the flagNumeroGruposCondicionadosObligatorio
	 */
	public boolean isFlagNumeroGruposCondicionadosObligatorio() {
		return flagNumeroGruposCondicionadosObligatorio;
	}

	/**
	 * @param flagNumeroGruposCondicionadosObligatorio the flagNumeroGruposCondicionadosObligatorio to set
	 */
	public void setFlagNumeroGruposCondicionadosObligatorio(
			boolean flagNumeroGruposCondicionadosObligatorio) {
		this.flagNumeroGruposCondicionadosObligatorio = flagNumeroGruposCondicionadosObligatorio;
	}

	/**
	 * @return the flagCondicionadosObligatorio
	 */
	public boolean isFlagCondicionadosObligatorio() {
		return flagCondicionadosObligatorio;
	}

	/**
	 * @param flagCondicionadosObligatorio the flagCondicionadosObligatorio to set
	 */
	public void setFlagCondicionadosObligatorio(boolean flagCondicionadosObligatorio) {
		this.flagCondicionadosObligatorio = flagCondicionadosObligatorio;
	}

	/**
	 * @return the flagNumeroGrupos
	 */
	public boolean isFlagNumeroGrupos() {
		return flagNumeroGrupos;
	}

	/**
	 * @param flagNumeroGrupos the flagNumeroGrupos to set
	 */
	public void setFlagNumeroGrupos(boolean flagNumeroGrupos) {
		this.flagNumeroGrupos = flagNumeroGrupos;
	}

	/**
	 * @return the flagNumeroPaquetes
	 */
	public boolean isFlagNumeroPaquetes() {
		return flagNumeroPaquetes;
	}

	/**
	 * @param flagNumeroPaquetes the flagNumeroPaquetes to set
	 */
	public void setFlagNumeroPaquetes(boolean flagNumeroPaquetes) {
		this.flagNumeroPaquetes = flagNumeroPaquetes;
	}

	/**
	 * @return the flagOidIndicadorCuadre
	 */
	public boolean isFlagOidIndicadorCuadre() {
		return flagOidIndicadorCuadre;
	}

	/**
	 * @param flagOidIndicadorCuadre the flagOidIndicadorCuadre to set
	 */
	public void setFlagOidIndicadorCuadre(boolean flagOidIndicadorCuadre) {
		this.flagOidIndicadorCuadre = flagOidIndicadorCuadre;
	}

	/**
	 * @return the flagNumeroGruposCondicionantes
	 */
	public boolean isFlagNumeroGruposCondicionantes() {
		return flagNumeroGruposCondicionantes;
	}

	/**
	 * @param flagNumeroGruposCondicionantes the flagNumeroGruposCondicionantes to set
	 */
	public void setFlagNumeroGruposCondicionantes(
			boolean flagNumeroGruposCondicionantes) {
		this.flagNumeroGruposCondicionantes = flagNumeroGruposCondicionantes;
	}

	/**
	 * @return the flagCondicionantesUno
	 */
	public boolean isFlagCondicionantesUno() {
		return flagCondicionantesUno;
	}

	/**
	 * @param flagCondicionantesUno the flagCondicionantesUno to set
	 */
	public void setFlagCondicionantesUno(boolean flagCondicionantesUno) {
		this.flagCondicionantesUno = flagCondicionantesUno;
	}

	/**
	 * @return the flagCondicionantesCero
	 */
	public boolean isFlagCondicionantesCero() {
		return flagCondicionantesCero;
	}

	/**
	 * @param flagCondicionantesCero the flagCondicionantesCero to set
	 */
	public void setFlagCondicionantesCero(boolean flagCondicionantesCero) {
		this.flagCondicionantesCero = flagCondicionantesCero;
	}

	/**
	 * @return the flagCondicionadosUno
	 */
	public boolean isFlagCondicionadosUno() {
		return flagCondicionadosUno;
	}

	/**
	 * @param flagCondicionadosUno the flagCondicionadosUno to set
	 */
	public void setFlagCondicionadosUno(boolean flagCondicionadosUno) {
		this.flagCondicionadosUno = flagCondicionadosUno;
	}

	/**
	 * @return the flagCondicionadosCero
	 */
	public boolean isFlagCondicionadosCero() {
		return flagCondicionadosCero;
	}

	/**
	 * @param flagCondicionadosCero the flagCondicionadosCero to set
	 */
	public void setFlagCondicionadosCero(boolean flagCondicionadosCero) {
		this.flagCondicionadosCero = flagCondicionadosCero;
	}

	/**
	 * @return the flagNumeroGruposCondicionados
	 */
	public boolean isFlagNumeroGruposCondicionados() {
		return flagNumeroGruposCondicionados;
	}

	/**
	 * @param flagNumeroGruposCondicionados the flagNumeroGruposCondicionados to set
	 */
	public void setFlagNumeroGruposCondicionados(
			boolean flagNumeroGruposCondicionados) {
		this.flagNumeroGruposCondicionados = flagNumeroGruposCondicionados;
	}

	/**
	 * @return the flagDespachoCompletoDis
	 */
	public boolean isFlagDespachoCompletoDis() {
		return flagDespachoCompletoDis;
	}

	/**
	 * @param flagDespachoCompletoDis the flagDespachoCompletoDis to set
	 */
	public void setFlagDespachoCompletoDis(boolean flagDespachoCompletoDis) {
		this.flagDespachoCompletoDis = flagDespachoCompletoDis;
	}

	/**
	 * @return the flagDespachoAutomaticoDis
	 */
	public boolean isFlagDespachoAutomaticoDis() {
		return flagDespachoAutomaticoDis;
	}

	/**
	 * @param flagDespachoAutomaticoDis the flagDespachoAutomaticoDis to set
	 */
	public void setFlagDespachoAutomaticoDis(boolean flagDespachoAutomaticoDis) {
		this.flagDespachoAutomaticoDis = flagDespachoAutomaticoDis;
	}

	/**
	 * @return the flagGrupoOidIndicadorCuadre
	 */
	public boolean isFlagGrupoOidIndicadorCuadre() {
		return flagGrupoOidIndicadorCuadre;
	}

	/**
	 * @param flagGrupoOidIndicadorCuadre the flagGrupoOidIndicadorCuadre to set
	 */
	public void setFlagGrupoOidIndicadorCuadre(boolean flagGrupoOidIndicadorCuadre) {
		this.flagGrupoOidIndicadorCuadre = flagGrupoOidIndicadorCuadre;
	}

	/**
	 * @return the flagCriteriosPaginaInicial
	 */
	public boolean isFlagCriteriosPaginaInicial() {
		return flagCriteriosPaginaInicial;
	}

	/**
	 * @param flagCriteriosPaginaInicial the flagCriteriosPaginaInicial to set
	 */
	public void setFlagCriteriosPaginaInicial(boolean flagCriteriosPaginaInicial) {
		this.flagCriteriosPaginaInicial = flagCriteriosPaginaInicial;
	}

	/**
	 * @return the flagCriteriosPaginaFinal
	 */
	public boolean isFlagCriteriosPaginaFinal() {
		return flagCriteriosPaginaFinal;
	}

	/**
	 * @param flagCriteriosPaginaFinal the flagCriteriosPaginaFinal to set
	 */
	public void setFlagCriteriosPaginaFinal(boolean flagCriteriosPaginaFinal) {
		this.flagCriteriosPaginaFinal = flagCriteriosPaginaFinal;
	}

	/**
	 * @return the flagCriteriosCodigoProducto
	 */
	public boolean isFlagCriteriosCodigoProducto() {
		return flagCriteriosCodigoProducto;
	}

	/**
	 * @param flagCriteriosCodigoProducto the flagCriteriosCodigoProducto to set
	 */
	public void setFlagCriteriosCodigoProducto(boolean flagCriteriosCodigoProducto) {
		this.flagCriteriosCodigoProducto = flagCriteriosCodigoProducto;
	}

	/**
	 * @return the flagCriteriosIndicadorExclusion
	 */
	public boolean isFlagCriteriosIndicadorExclusion() {
		return flagCriteriosIndicadorExclusion;
	}

	/**
	 * @param flagCriteriosIndicadorExclusion the flagCriteriosIndicadorExclusion to set
	 */
	public void setFlagCriteriosIndicadorExclusion(
			boolean flagCriteriosIndicadorExclusion) {
		this.flagCriteriosIndicadorExclusion = flagCriteriosIndicadorExclusion;
	}

	/**
	 * @return the boolCriteriosIndicadorExclusion
	 */
	public boolean isBoolCriteriosIndicadorExclusion() {
		return boolCriteriosIndicadorExclusion;
	}

	/**
	 * @param boolCriteriosIndicadorExclusion the boolCriteriosIndicadorExclusion to set
	 */
	public void setBoolCriteriosIndicadorExclusion(
			boolean boolCriteriosIndicadorExclusion) {
		this.boolCriteriosIndicadorExclusion = boolCriteriosIndicadorExclusion;
	}

	/**
	 * @return the pedOfertaGruposMap
	 */
	public Map getPedOfertaGruposMap() {
		return pedOfertaGruposMap;
	}

	/**
	 * @param pedOfertaGruposMap the pedOfertaGruposMap to set
	 */
	public void setPedOfertaGruposMap(Map pedOfertaGruposMap) {
		this.pedOfertaGruposMap = pedOfertaGruposMap;
	}

	/**
	 * @return the siccCatalogoList
	 */
	public List getSiccCatalogoList() {
		return siccCatalogoList;
	}

	/**
	 * @param siccCatalogoList the siccCatalogoList to set
	 */
	public void setSiccCatalogoList(List siccCatalogoList) {
		this.siccCatalogoList = siccCatalogoList;
	}

	/**
	 * @return the columnasSeleccionadasCriterios
	 */
	public Map getColumnasSeleccionadasCriterios() {
		return columnasSeleccionadasCriterios;
	}

	/**
	 * @param columnasSeleccionadasCriterios the columnasSeleccionadasCriterios to set
	 */
	public void setColumnasSeleccionadasCriterios(Map columnasSeleccionadasCriterios) {
		this.columnasSeleccionadasCriterios = columnasSeleccionadasCriterios;
	}

	/**
	 * @return the grupoNumeroTotalGruposAnteriorJs
	 */
	public String getGrupoNumeroTotalGruposAnteriorJs() {
		return grupoNumeroTotalGruposAnteriorJs;
	}

	/**
	 * @param grupoNumeroTotalGruposAnteriorJs the grupoNumeroTotalGruposAnteriorJs to set
	 */
	public void setGrupoNumeroTotalGruposAnteriorJs(
			String grupoNumeroTotalGruposAnteriorJs) {
		this.grupoNumeroTotalGruposAnteriorJs = grupoNumeroTotalGruposAnteriorJs;
	}

	/**
	 * @return the numeroGruposAnteriorJs
	 */
	public String getNumeroGruposAnteriorJs() {
		return numeroGruposAnteriorJs;
	}

	/**
	 * @param numeroGruposAnteriorJs the numeroGruposAnteriorJs to set
	 */
	public void setNumeroGruposAnteriorJs(String numeroGruposAnteriorJs) {
		this.numeroGruposAnteriorJs = numeroGruposAnteriorJs;
	}

	/**
	 * @return the numeroPaquetesAnteriorJs
	 */
	public String getNumeroPaquetesAnteriorJs() {
		return numeroPaquetesAnteriorJs;
	}

	/**
	 * @param numeroPaquetesAnteriorJs the numeroPaquetesAnteriorJs to set
	 */
	public void setNumeroPaquetesAnteriorJs(String numeroPaquetesAnteriorJs) {
		this.numeroPaquetesAnteriorJs = numeroPaquetesAnteriorJs;
	}

	/**
	 * @return the numeroGruposAnterior
	 */
	public String getNumeroGruposAnterior() {
		return numeroGruposAnterior;
	}

	/**
	 * @param numeroGruposAnterior the numeroGruposAnterior to set
	 */
	public void setNumeroGruposAnterior(String numeroGruposAnterior) {
		this.numeroGruposAnterior = numeroGruposAnterior;
	}

	/**
	 * @return the numeroPaquetesAnterior
	 */
	public String getNumeroPaquetesAnterior() {
		return numeroPaquetesAnterior;
	}

	/**
	 * @param numeroPaquetesAnterior the numeroPaquetesAnterior to set
	 */
	public void setNumeroPaquetesAnterior(String numeroPaquetesAnterior) {
		this.numeroPaquetesAnterior = numeroPaquetesAnterior;
	}

	/**
	 * @return the numeroGruposCondicionantesAnterior
	 */
	public String getNumeroGruposCondicionantesAnterior() {
		return numeroGruposCondicionantesAnterior;
	}

	/**
	 * @param numeroGruposCondicionantesAnterior the numeroGruposCondicionantesAnterior to set
	 */
	public void setNumeroGruposCondicionantesAnterior(
			String numeroGruposCondicionantesAnterior) {
		this.numeroGruposCondicionantesAnterior = numeroGruposCondicionantesAnterior;
	}

	/**
	 * @return the numeroGruposCondicionadosAnterior
	 */
	public String getNumeroGruposCondicionadosAnterior() {
		return numeroGruposCondicionadosAnterior;
	}

	/**
	 * @param numeroGruposCondicionadosAnterior the numeroGruposCondicionadosAnterior to set
	 */
	public void setNumeroGruposCondicionadosAnterior(
			String numeroGruposCondicionadosAnterior) {
		this.numeroGruposCondicionadosAnterior = numeroGruposCondicionadosAnterior;
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
	public void setMantenimientoPEDProductoAsociadoSearchAction(
			MantenimientoPEDProductoAsociadoSearchAction mantenimientoPEDProductoAsociadoSearchAction) {
		this.mantenimientoPEDProductoAsociadoSearchAction = mantenimientoPEDProductoAsociadoSearchAction;
	}

	/**
	 * @return the indicadorRecarga
	 */
	public String getIndicadorRecarga() {
		return indicadorRecarga;
	}

	/**
	 * @param indicadorRecarga the indicadorRecarga to set
	 */
	public void setIndicadorRecarga(String indicadorRecarga) {
		this.indicadorRecarga = indicadorRecarga;
	}

	/**
	 * @return the flagGridProductoPrincipal
	 */
	public boolean isFlagGridProductoPrincipal() {
		return flagGridProductoPrincipal;
	}

	/**
	 * @param flagGridProductoPrincipal the flagGridProductoPrincipal to set
	 */
	public void setFlagGridProductoPrincipal(boolean flagGridProductoPrincipal) {
		this.flagGridProductoPrincipal = flagGridProductoPrincipal;
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
	 * @return the preComponentesList
	 */
	public List getPreComponentesList() {
		return preComponentesList;
	}

	/**
	 * @param preComponentesList the preComponentesList to set
	 */
	public void setPreComponentesList(List preComponentesList) {
		this.preComponentesList = preComponentesList;
	}

	/**
	 * @return the preComponentesGlobalList
	 */
	public List getPreComponentesGlobalList() {
		return preComponentesGlobalList;
	}

	/**
	 * @param preComponentesGlobalList the preComponentesGlobalList to set
	 */
	public void setPreComponentesGlobalList(List preComponentesGlobalList) {
		this.preComponentesGlobalList = preComponentesGlobalList;
	}
	
}
