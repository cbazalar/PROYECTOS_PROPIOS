/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.MatrizFacturacion;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDConfiguracionOfertaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.action.MantenimientoPRECambioCodigoVentaModificaCUVAction;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDProductoAsociadoSearchForm;

/**
 * @author Sigcomt
 *
 */
@ManagedBean
@SessionScoped
public class MantenimientoPEDProductoAsociadoSearchAction extends BaseMantenimientoSearchAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2958742586579437641L;

	private String pedOfertaCatalogoSeleccionado;
	private String pedOfertaEstrategiaSeleccionada;
	private String pedOfertaTipoEstrategiaSeleccionada;
	private String pedOfertaTipoBusquedaSeleccionada;
	private List siccCatalogoList = new ArrayList();
	
	private List pedProductoAsociadoSearchList = new ArrayList();
	private DataTableModel listaModelPedProductoAsociadoSearchList = new DataTableModel();
	private List columnasSeleccionadasProductoAsociado = new ArrayList();
	
	private List pedProductoAsociadoSeleccionadoSearchList = new ArrayList();
	private DataTableModel listaModelPedProductoAsociadoSeleccionadoSearchList = new DataTableModel();
	private List columnasSeleccionadasProductoAsociadoSeleccionado = new ArrayList();
	
	private List pedEstrategiaList =  new ArrayList();
	
	private boolean salirCambioCodigoVentaModificaCUV = false;
	private String codigoPeriodoCambioCodigoVentaModificaCUV = "";
	
	private List preProductoGrupoSearchList = new ArrayList();
	private DataTableModel listaModelPreProductoGrupoSearchList = new DataTableModel();
	private Map columnasSeleccionadasProductoGrupo = new HashMap();
	
	private boolean salirCambioCodigoVentaModificaCUVCompuestaFija = false;
	private String codigoPeriodoCambioCodigoVentaModificaCUVCompuestaFija = "";
	
	private List preProductoAsociadoCFSearchList = new ArrayList();
	private DataTableModel listaModelPreProductoAsociadoCFSearchList = new DataTableModel();
	private Map columnasSeleccionadasProductoAsociadoCF = new HashMap();
	
	private String oidCatalogoSeleccionado;
	
	@ManagedProperty(value="#{mantenimientoPEDProductoAsociadoDetalleAction}")	
	private MantenimientoPEDProductoAsociadoDetalleAction mantenimientoPEDProductoAsociadoDetalleAction;
	
	@Override
	protected String getSalirForward() {
		if(this.salirCambioCodigoVentaModificaCUV){
			this.salirCambioCodigoVentaModificaCUV = false;
			return "mantenimientoPRECambioCodigoVentaModificaCUVForm";
		}else if(this.salirCambioCodigoVentaModificaCUVCompuestaFija){
			this.salirCambioCodigoVentaModificaCUVCompuestaFija = false;
			return "mantenimientoPRECambioCodigoVentaModificaCUVCompuestaFijaForm";
		}else{
			this.salirCambioCodigoVentaModificaCUV = false;
			this.salirCambioCodigoVentaModificaCUVCompuestaFija = false;
			return "mantenimientoPEDDefinirOfertaForm";
		}
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoPEDProductoAsociadoSearchForm form = new MantenimientoPEDProductoAsociadoSearchForm();
		return form;
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
	protected boolean setSaveAttributes() throws Exception {
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

//		MantenimientoPEDProductoAsociadoSearchForm searchForm = (MantenimientoPEDProductoAsociadoSearchForm) this.formBusqueda;
//		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
//		
//		this.siccCatalogoList = reporteService.getListaGenerico("getOidCatalogoProductos", null);
//		
//		MatrizFacturacion matriz =	this.mPantallaPrincipalBean.getPedMatrizSeleccionada();
//		if(matriz != null)
//			searchForm.setCodigoPeriodo(matriz.getCodigoPeriodo());
		
//		session.removeAttribute(Constants.PED_PRODUCTO_ASOCIADO_SEARCH_LIST);
//		session.removeAttribute(Constants.PED_PRODUCTO_ASOCIADO_SELECCIONADO_SEARCH_LIST);
		
//		searchForm.setSelectedItems(new String[0]);
//		searchForm.setAsociadosSelectedItems(new String[0]);	
		
//		this.mostrarBotonSave = false;		
	
		
	}
	
	/**
	 * @throws Exception
	 */
	public void inicializarValoresSearch() throws Exception{
		
		
		MantenimientoPEDProductoAsociadoSearchForm searchForm = new MantenimientoPEDProductoAsociadoSearchForm(); 
		
		/* Se usa en la pantalla de Detalle de productos */
		/**/
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		this.siccCatalogoList = reporteService.getListaGenerico("getOidCatalogoProductos", null);
		
		if(this.salirCambioCodigoVentaModificaCUV){
			searchForm.setCodigoPeriodo(this.codigoPeriodoCambioCodigoVentaModificaCUV);
			searchForm.setOidCatalogo(this.oidCatalogoSeleccionado);
			
			this.preProductoGrupoSearchList = null;
			this.listaModelPreProductoGrupoSearchList = new DataTableModel(this.preProductoGrupoSearchList);
			this.columnasSeleccionadasProductoGrupo = null;
		}else if (this.salirCambioCodigoVentaModificaCUVCompuestaFija){
			searchForm.setCodigoPeriodo(this.codigoPeriodoCambioCodigoVentaModificaCUVCompuestaFija);
			searchForm.setOidCatalogo(this.oidCatalogoSeleccionado);
			
			this.preProductoAsociadoCFSearchList = null;
			this.listaModelPreProductoAsociadoCFSearchList = new DataTableModel(this.preProductoAsociadoCFSearchList);
			this.columnasSeleccionadasProductoAsociadoCF = null;
		}else{
			searchForm.setOidCatalogo(this.oidCatalogoSeleccionado);
			
			MatrizFacturacion matriz =	this.mPantallaPrincipalBean.getPedMatrizSeleccionada();
			if(matriz != null)
				searchForm.setCodigoPeriodo(matriz.getCodigoPeriodo());
			
			this.pedProductoAsociadoSearchList = null;
			this.listaModelPedProductoAsociadoSearchList = new DataTableModel(this.pedProductoAsociadoSearchList);
			this.columnasSeleccionadasProductoAsociado = null;
			
			this.pedProductoAsociadoSeleccionadoSearchList = null;
			this.listaModelPedProductoAsociadoSeleccionadoSearchList = new DataTableModel(this.pedProductoAsociadoSeleccionadoSearchList);
			this.columnasSeleccionadasProductoAsociadoSeleccionado = null;
		}
	
		this.formBusqueda = searchForm;
		this.mostrarBotonSave = false;
	
		
	}
	
	/**
	 * @param event
	 */
	public void buscar (ActionEvent event){
		
		log.debug("Enter method - buscar");
		
		try {
			
			MantenimientoPEDProductoAsociadoSearchForm searchForm = (MantenimientoPEDProductoAsociadoSearchForm)this.formBusqueda;		
			MantenimientoPEDConfiguracionOfertaService service = (MantenimientoPEDConfiguracionOfertaService)getBean("spusicc.mantenimientoPEDConfiguracionOfertaService");
			String mensaje = null;
			Map params = BeanUtils.describe(searchForm);
			params.put("salirCambioCodigoVentaModificaCUV", this.salirCambioCodigoVentaModificaCUV);
			params.put("salirCambioCodigoVentaModificaCUVCompuestaFija", this.salirCambioCodigoVentaModificaCUVCompuestaFija);
			
			this.columnasSeleccionadasProductoAsociado = null;
			this.columnasSeleccionadasProductoAsociadoSeleccionado = null;
			this.columnasSeleccionadasProductoGrupo = null;
			this.columnasSeleccionadasProductoAsociadoCF = null;
			
			List lista = (List)service.getProductosAsociadosByCriteria(params);
			
			if(this.salirCambioCodigoVentaModificaCUV){
				this.preProductoGrupoSearchList = lista;
				this.listaModelPreProductoGrupoSearchList = new DataTableModel(this.preProductoGrupoSearchList);
			}else if(this.salirCambioCodigoVentaModificaCUVCompuestaFija){
				this.preProductoAsociadoCFSearchList = lista;
				this.listaModelPreProductoAsociadoCFSearchList = new DataTableModel(this.preProductoAsociadoCFSearchList);
			}else{
				this.pedProductoAsociadoSearchList = lista;
				this.listaModelPedProductoAsociadoSearchList = new DataTableModel(this.pedProductoAsociadoSearchList);
			}
			
			if(lista == null || lista.size() == 0){
				mensaje = "errors.datos.fuentes.busqueda";
				this.addError("Error: ", this.getResourceMessage(mensaje));
			}
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		
	}
	
	/**
	 * @param event
	 */
	public void agregar(ActionEvent event){
		
		log.debug("Enter method - agregar");
		
		try {
			MantenimientoPRECambioCodigoVentaModificaCUVAction actionModCUV = findManageBean("mantenimientoPRECambioCodigoVentaModificaCUVAction");
			
			MantenimientoPEDProductoAsociadoSearchForm searchForm = (MantenimientoPEDProductoAsociadoSearchForm)this.formBusqueda;
			List listaBusqueda = this.pedProductoAsociadoSearchList;				
			List listaSeleccionados = this.pedProductoAsociadoSeleccionadoSearchList;

			//Modifica CUV
			List listaProductoModificaCUV = this.preProductoGrupoSearchList;
			List listaProductosGrupo = this.mPantallaPrincipalBean.getPreProductoAsociadoGrupoOfertaList();
			
			//Producto Asociado CF
			List listaProductoModificaCUVCF = this.preProductoAsociadoCFSearchList;
			List listaProductosAsociadoCF = this.mPantallaPrincipalBean.getPreProductoAsociadoCompuestaFijaList();
			
			String oidTipoEstrategia = this.pedOfertaTipoEstrategiaSeleccionada;
			String mensaje = null;
			
			if(listaSeleccionados == null)
				listaSeleccionados = new ArrayList();
			
			if(listaBusqueda == null)
				listaBusqueda = new ArrayList();
			
			//Modifica CUV
			if(listaProductoModificaCUV == null)
				listaProductoModificaCUV = new ArrayList();
			if(listaProductosGrupo == null)
				listaProductosGrupo = new ArrayList();
			
			//Producto Asociado CF
			if(listaProductoModificaCUVCF == null)
				listaProductoModificaCUVCF = new ArrayList();
			if(listaProductosAsociadoCF == null)
				listaProductosAsociadoCF = new ArrayList();
				
			if(this.salirCambioCodigoVentaModificaCUV){
				if(this.columnasSeleccionadasProductoGrupo == null){
					mensaje = "errors.select.item";
					this.addError("Error: ", this.getResourceMessage(mensaje));
					return;
				}
				
				if(this.columnasSeleccionadasProductoGrupo != null){					
					//for(int i=0; i<this.columnasSeleccionadasProductoGrupo.size(); i++){
						//Map columnaSeleccionada = (Map) this.columnasSeleccionadasProductoGrupo.get(i);
						String oidProductoSeleccionado = MapUtils.getString(this.columnasSeleccionadasProductoGrupo, "oidProducto"); 
						
						for(int j=0; j<listaProductoModificaCUV.size(); j++){
							Map seleccionado = (Map)listaProductoModificaCUV.get(j);
							String oidProducto = MapUtils.getString(seleccionado, "oidProducto"); 
									
							if(StringUtils.equals(oidProductoSeleccionado, oidProducto)){												
								boolean existe = false;
								for(int k=0; k<listaProductosGrupo.size(); k++){
									Map existente = (Map)listaProductosGrupo.get(k);
									String oidProductoExistente = MapUtils.getString(existente, "oidProducto");
									
									if(StringUtils.equals(oidProducto, oidProductoExistente)){
										existe = true;
										break;
									}
								}
							
								if(!existe)
									seleccionado.put("estado", "A");
									listaProductosGrupo.add(seleccionado);
									
									Map mapGrupos = actionModCUV.getPreOfertaGruposMap();
									Map grupo = MapUtils.getMap(mapGrupos, actionModCUV.getNumeroGrupoGlobal());
									List productos = (List) MapUtils.getObject(grupo, "listaProductos");
									
									//Digitable
									if(StringUtils.equals(oidTipoEstrategia, Constants.PED_OID_TIPO_ESTRATEGIA_UNO) || 
											StringUtils.equals(oidTipoEstrategia, Constants.PED_OID_TIPO_ESTRATEGIA_TRES) ||
											StringUtils.equals(oidTipoEstrategia, Constants.PED_OID_TIPO_ESTRATEGIA_SIETE)) {
										seleccionado.put("indDigitable", Constants.NUMERO_UNO);
										//editForm.setFlagDigitable(Constants.NUMERO_UNO);
										//this.boolFlagDigitable = true;
										//editForm.setFlagDigitableSoloLectura(true);
									} else if(StringUtils.equals(oidTipoEstrategia, Constants.PED_OID_TIPO_ESTRATEGIA_CINCO) ||
											StringUtils.equals(oidTipoEstrategia, Constants.PED_OID_TIPO_ESTRATEGIA_SEIS)) {
										seleccionado.put("indDigitable", Constants.NUMERO_CERO);
										//editForm.setFlagDigitable(Constants.NUMERO_CERO);
										//this.boolFlagDigitable = false;
										//editForm.setFlagDigitableSoloLectura(false);
									} /*else if(StringUtils.equals(oidTipoEstrategia, Constants.PED_OID_TIPO_ESTRATEGIA_DOS)) {
										//Verificamos en la lista si ya hay un producto marcado
										if(validarExisteDigitable(this.mPantallaPrincipalBean.getPedProductoAsociadoOfertaList())) {
											editForm.setFlagDigitable(Constants.NUMERO_CERO);
											this.boolFlagDigitable = false;
											editForm.setFlagDigitableSoloLectura(true);
										} else {
											//Si es el ultimo y no hay ningun marcado, se setea como marcado y se inhabilita
											if(listaSeleccionados.size() == (this.indiceProductoActual + 1)) {
												editForm.setFlagDigitable(Constants.NUMERO_UNO);
												this.boolFlagDigitable = true;
												editForm.setFlagDigitableSoloLectura(true);				
											}
										}
									}*/
									
									for (int k = productos.size(); k == productos.size(); k--) {
										productos.add(k, seleccionado);
									}
								
								break;
							}
						}
					//}
				}
				
				this.mPantallaPrincipalBean.setPreProductoAsociadoGrupoOfertaList(listaProductosGrupo);
				this.mPantallaPrincipalBean.setListaModelPreProductoAsociadoGrupoOfertaList(new DataTableModel(this.mPantallaPrincipalBean.getPreProductoAsociadoGrupoOfertaList()));
				
				mensaje = "mantenimientoPEDProductoAsociadoSearchForm.agregado";
				this.addInfo("Info:", this.getResourceMessage(mensaje));
				
				this.columnasSeleccionadasProductoGrupo = null;
				
				this.redireccionarPagina("mantenimientoPRECambioCodigoVentaModificaCUVForm");
			}else if(this.salirCambioCodigoVentaModificaCUVCompuestaFija){
				if(this.columnasSeleccionadasProductoAsociadoCF == null){
					mensaje = "errors.select.item";
					this.addError("Error: ", this.getResourceMessage(mensaje));
					return;
				}
				
				if(this.columnasSeleccionadasProductoAsociadoCF != null){					
					//for(int i=0; i<this.columnasSeleccionadasProductoGrupo.size(); i++){
						//Map columnaSeleccionada = (Map) this.columnasSeleccionadasProductoGrupo.get(i);
						String oidProductoSeleccionado = MapUtils.getString(this.columnasSeleccionadasProductoAsociadoCF, "oidProducto"); 
						
						for(int j=0; j<listaProductoModificaCUVCF.size(); j++){
							Map seleccionado = (Map)listaProductoModificaCUVCF.get(j);
							String oidProducto = MapUtils.getString(seleccionado, "oidProducto"); 
									
							if(StringUtils.equals(oidProductoSeleccionado, oidProducto)){												
								boolean existe = false;
								for(int k=0; k<listaProductosAsociadoCF.size(); k++){
									Map existente = (Map)listaProductosAsociadoCF.get(k);
									String oidProductoExistente = MapUtils.getString(existente, "oidProducto");
									
									if(StringUtils.equals(oidProducto, oidProductoExistente)){
										existe = true;
										break;
									}
								}
							
								if(!existe)
									seleccionado.put("estado", "A");
									listaProductosAsociadoCF.add(seleccionado);
									
									List productos = this.mPantallaPrincipalBean.getPreProductoAsociadoCompuestaFijaGlobalList();
									
									//Digitable
									if(StringUtils.equals(oidTipoEstrategia, Constants.PED_OID_TIPO_ESTRATEGIA_UNO) || 
											StringUtils.equals(oidTipoEstrategia, Constants.PED_OID_TIPO_ESTRATEGIA_TRES) ||
											StringUtils.equals(oidTipoEstrategia, Constants.PED_OID_TIPO_ESTRATEGIA_SIETE)) {
										seleccionado.put("indDigitable", Constants.NUMERO_UNO);
										//editForm.setFlagDigitable(Constants.NUMERO_UNO);
										//this.boolFlagDigitable = true;
										//editForm.setFlagDigitableSoloLectura(true);
									} else if(StringUtils.equals(oidTipoEstrategia, Constants.PED_OID_TIPO_ESTRATEGIA_CINCO) ||
											StringUtils.equals(oidTipoEstrategia, Constants.PED_OID_TIPO_ESTRATEGIA_SEIS)) {
										seleccionado.put("indDigitable", Constants.NUMERO_CERO);
										//editForm.setFlagDigitable(Constants.NUMERO_CERO);
										//this.boolFlagDigitable = false;
										//editForm.setFlagDigitableSoloLectura(false);
									}
									
									for (int k = productos.size(); k == productos.size(); k--) {
										productos.add(k, seleccionado);
									}
								
								break;
							}
						}
					//}
				}
				
				this.mPantallaPrincipalBean.setPreProductoAsociadoCompuestaFijaList(listaProductosAsociadoCF);
				this.mPantallaPrincipalBean.setListaModelPreProductoAsociadoCompuestaFijaList(new DataTableModel(this.mPantallaPrincipalBean.getPreProductoAsociadoCompuestaFijaList()));
				
				mensaje = "mantenimientoPEDProductoAsociadoSearchForm.agregado";
				this.addInfo("Info:", this.getResourceMessage(mensaje));
				
				this.columnasSeleccionadasProductoAsociadoCF = null;
				
				this.redireccionarPagina("mantenimientoPRECambioCodigoVentaModificaCUVCompuestaFijaForm");
			}else{
				if(listaBusqueda.size() == 0 )
					return;
				
				if(this.columnasSeleccionadasProductoAsociado == null || this.columnasSeleccionadasProductoAsociado.size()== 0){
					mensaje = "errors.select.items";
					this.addError("Error: ", this.getResourceMessage(mensaje));
					return;
				}
				
				if(this.columnasSeleccionadasProductoAsociado != null && this.columnasSeleccionadasProductoAsociado.size()>0){
					
					// Validamos que pata los tipos 1 y 5 solo pueda agregar un elemento
					if(StringUtils.equals(oidTipoEstrategia, Constants.PED_OID_TIPO_ESTRATEGIA_UNO) || 
					   StringUtils.equals(oidTipoEstrategia, Constants.PED_OID_TIPO_ESTRATEGIA_CINCO))
					{
						if(listaSeleccionados.size() == 1 ||  this.columnasSeleccionadasProductoAsociado.size() > 1 )
						{
							mensaje = "mantenimientoPEDProductoAsociadoSearchForm.error.cantidad.productos.1";
							this.addError("Error: ", this.getResourceMessage(mensaje));
							return;			
							
						}
					}
					//
					
					for(int i=0; i<this.columnasSeleccionadasProductoAsociado.size(); i++)
					{
						Map columnaSeleccionada = (Map) this.columnasSeleccionadasProductoAsociado.get(i);
						String oidProductoSeleccionado = MapUtils.getString(columnaSeleccionada, "oidProducto"); 
						for(int j=0; j<listaBusqueda.size(); j++)
						{
							Map seleccionado = (Map)listaBusqueda.get(j);
							String oidProducto = MapUtils.getString(seleccionado, "oidProducto"); 
									
							if(StringUtils.equals(oidProductoSeleccionado, oidProducto))
							{												
								boolean existe = false;
								for(int k=0; k<listaSeleccionados.size(); k++)
								{
									Map existente = (Map)listaSeleccionados.get(k);
									String oidProductoExistente = MapUtils.getString(existente, "oidProducto");
									
									if(StringUtils.equals(oidProducto, oidProductoExistente))
									{
										existe = true;
										break;
									}
									
								}
							
								if(!existe)
									//Digitable
									if(StringUtils.equals(oidTipoEstrategia, Constants.PED_OID_TIPO_ESTRATEGIA_UNO) || 
											StringUtils.equals(oidTipoEstrategia, Constants.PED_OID_TIPO_ESTRATEGIA_TRES) ||
											StringUtils.equals(oidTipoEstrategia, Constants.PED_OID_TIPO_ESTRATEGIA_SIETE)) {
										seleccionado.put("indDigitable", Constants.NUMERO_UNO);
										//editForm.setFlagDigitable(Constants.NUMERO_UNO);
										//this.boolFlagDigitable = true;
										//editForm.setFlagDigitableSoloLectura(true);
									} else if(StringUtils.equals(oidTipoEstrategia, Constants.PED_OID_TIPO_ESTRATEGIA_CINCO) ||
											StringUtils.equals(oidTipoEstrategia, Constants.PED_OID_TIPO_ESTRATEGIA_SEIS)) {
										seleccionado.put("indDigitable", Constants.NUMERO_CERO);
										//editForm.setFlagDigitable(Constants.NUMERO_CERO);
										//this.boolFlagDigitable = false;
										//editForm.setFlagDigitableSoloLectura(false);
									}
									
									listaSeleccionados.add(seleccionado);
								
								break;
							}
						}
					}
					
				}
				
				this.pedProductoAsociadoSeleccionadoSearchList = listaSeleccionados;
				this.listaModelPedProductoAsociadoSeleccionadoSearchList = new DataTableModel(this.pedProductoAsociadoSeleccionadoSearchList);
				mensaje = "mantenimientoPEDProductoAsociadoSearchForm.agregado";
				this.addInfo("Info:", this.getResourceMessage(mensaje));
				
				this.columnasSeleccionadasProductoAsociado = null;
				this.columnasSeleccionadasProductoAsociadoSeleccionado = null;
			}
			
		} catch (Exception e) {
			
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * @param event
	 */
	public void quitar(ActionEvent event){
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'quitar' method");
		}
		
		try {
			
			MantenimientoPEDProductoAsociadoSearchForm searchForm = (MantenimientoPEDProductoAsociadoSearchForm)this.formBusqueda;		
			String mensaje = null;
			List listaSeleccionados = this.pedProductoAsociadoSeleccionadoSearchList;
			
			if(this.columnasSeleccionadasProductoAsociadoSeleccionado == null || this.columnasSeleccionadasProductoAsociadoSeleccionado.size()==0){
				mensaje = "errors.select.items";
				this.addError("Error: ", this.getResourceMessage(mensaje));
				return;
				
			}
			
			if(columnasSeleccionadasProductoAsociadoSeleccionado != null && columnasSeleccionadasProductoAsociadoSeleccionado.size() > 0)
			{
				for(int i=0; i<columnasSeleccionadasProductoAsociadoSeleccionado.size(); i++)
				{
					Map columnaSeleccionada = (Map) this.columnasSeleccionadasProductoAsociadoSeleccionado.get(i);
					String oidProductoSeleccionado = MapUtils.getString(columnaSeleccionada, "oidProducto"); 
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
			
			this.pedProductoAsociadoSeleccionadoSearchList = listaSeleccionados;
			this.listaModelPedProductoAsociadoSeleccionadoSearchList = new DataTableModel(this.pedProductoAsociadoSeleccionadoSearchList);
			
			mensaje = "mantenimientoPEDProductoAsociadoSearchForm.eliminado";
			this.addInfo("Info:", this.getResourceMessage(mensaje));
			
			this.columnasSeleccionadasProductoAsociado = null;
			this.columnasSeleccionadasProductoAsociadoSeleccionado = null; 
			
		} catch (Exception e) {
			
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	/**
	 * @param event
	 */
	public void guardar (ActionEvent event){
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'guardar' method");
		}
		
		try {
			String mensaje = null;
			
			if(this.pedProductoAsociadoSeleccionadoSearchList == null || this.pedProductoAsociadoSeleccionadoSearchList.size()==0){
				mensaje = "mantenimientoPEDProductoAsociadoSearchForm.productos.seleccionados.error";
				this.addError("Error: ", this.getResourceMessage(mensaje));
				return;
			}
			
			this.mantenimientoPEDProductoAsociadoDetalleAction.setPedProductoAsociadoSeleccionadoSearchList(this.pedProductoAsociadoSeleccionadoSearchList);
			this.mantenimientoPEDProductoAsociadoDetalleAction.setPedOfertaCatalogoSeleccionado(this.pedOfertaCatalogoSeleccionado);
			this.mantenimientoPEDProductoAsociadoDetalleAction.setPedOfertaEstrategiaSeleccionada(this.pedOfertaEstrategiaSeleccionada);
			this.mantenimientoPEDProductoAsociadoDetalleAction.setPedEstrategiaList(this.pedEstrategiaList);
			this.mantenimientoPEDProductoAsociadoDetalleAction.setPedOfertaTipoBusquedaSeleccionada(this.pedOfertaTipoBusquedaSeleccionada);
			this.mantenimientoPEDProductoAsociadoDetalleAction.inicializarValoresDetalle();
			this.redireccionarPagina("mantenimientoPEDProductoAsociadoDetalleForm");
				
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		
	}
	
	
	/**
	 * @return the pedOfertaCatalogoSeleccionado
	 */
	public String getPedOfertaCatalogoSeleccionado() {
		return pedOfertaCatalogoSeleccionado;
	}

	/**
	 * @param pedOfertaCatalogoSeleccionado the pedOfertaCatalogoSeleccionado to set
	 */
	public void setPedOfertaCatalogoSeleccionado(
			String pedOfertaCatalogoSeleccionado) {
		this.pedOfertaCatalogoSeleccionado = pedOfertaCatalogoSeleccionado;
	}

	/**
	 * @return the pedOfertaEstrategiaSeleccionada
	 */
	public String getPedOfertaEstrategiaSeleccionada() {
		return pedOfertaEstrategiaSeleccionada;
	}

	/**
	 * @param pedOfertaEstrategiaSeleccionada the pedOfertaEstrategiaSeleccionada to set
	 */
	public void setPedOfertaEstrategiaSeleccionada(
			String pedOfertaEstrategiaSeleccionada) {
		this.pedOfertaEstrategiaSeleccionada = pedOfertaEstrategiaSeleccionada;
	}

	/**
	 * @return the pedOfertaTipoEstrategiaSeleccionada
	 */
	public String getPedOfertaTipoEstrategiaSeleccionada() {
		return pedOfertaTipoEstrategiaSeleccionada;
	}

	/**
	 * @param pedOfertaTipoEstrategiaSeleccionada the pedOfertaTipoEstrategiaSeleccionada to set
	 */
	public void setPedOfertaTipoEstrategiaSeleccionada(
			String pedOfertaTipoEstrategiaSeleccionada) {
		this.pedOfertaTipoEstrategiaSeleccionada = pedOfertaTipoEstrategiaSeleccionada;
	}

	/**
	 * @return the pedOfertaTipoBusquedaSeleccionada
	 */
	public String getPedOfertaTipoBusquedaSeleccionada() {
		return pedOfertaTipoBusquedaSeleccionada;
	}

	/**
	 * @param pedOfertaTipoBusquedaSeleccionada the pedOfertaTipoBusquedaSeleccionada to set
	 */
	public void setPedOfertaTipoBusquedaSeleccionada(
			String pedOfertaTipoBusquedaSeleccionada) {
		this.pedOfertaTipoBusquedaSeleccionada = pedOfertaTipoBusquedaSeleccionada;
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
	 * @return the pedProductoAsociadoSearchList
	 */
	public List getPedProductoAsociadoSearchList() {
		return pedProductoAsociadoSearchList;
	}

	/**
	 * @param pedProductoAsociadoSearchList the pedProductoAsociadoSearchList to set
	 */
	public void setPedProductoAsociadoSearchList(List pedProductoAsociadoSearchList) {
		this.pedProductoAsociadoSearchList = pedProductoAsociadoSearchList;
	}

	/**
	 * @return the listaModelPedProductoAsociadoSearchList
	 */
	public DataTableModel getListaModelPedProductoAsociadoSearchList() {
		return listaModelPedProductoAsociadoSearchList;
	}

	/**
	 * @param listaModelPedProductoAsociadoSearchList the listaModelPedProductoAsociadoSearchList to set
	 */
	public void setListaModelPedProductoAsociadoSearchList(
			DataTableModel listaModelPedProductoAsociadoSearchList) {
		this.listaModelPedProductoAsociadoSearchList = listaModelPedProductoAsociadoSearchList;
	}

	/**
	 * @return the columnasSeleccionadasProductoAsociado
	 */
	public List getColumnasSeleccionadasProductoAsociado() {
		return columnasSeleccionadasProductoAsociado;
	}

	/**
	 * @param columnasSeleccionadasProductoAsociado the columnasSeleccionadasProductoAsociado to set
	 */
	public void setColumnasSeleccionadasProductoAsociado(
			List columnasSeleccionadasProductoAsociado) {
		this.columnasSeleccionadasProductoAsociado = columnasSeleccionadasProductoAsociado;
	}

	/**
	 * @return the pedProductoAsociadoSeleccionadoSearchList
	 */
	public List getPedProductoAsociadoSeleccionadoSearchList() {
		return pedProductoAsociadoSeleccionadoSearchList;
	}

	/**
	 * @param pedProductoAsociadoSeleccionadoSearchList the pedProductoAsociadoSeleccionadoSearchList to set
	 */
	public void setPedProductoAsociadoSeleccionadoSearchList(
			List pedProductoAsociadoSeleccionadoSearchList) {
		this.pedProductoAsociadoSeleccionadoSearchList = pedProductoAsociadoSeleccionadoSearchList;
	}

	/**
	 * @return the listaModelPedProductoAsociadoSeleccionadoSearchList
	 */
	public DataTableModel getListaModelPedProductoAsociadoSeleccionadoSearchList() {
		return listaModelPedProductoAsociadoSeleccionadoSearchList;
	}

	/**
	 * @param listaModelPedProductoAsociadoSeleccionadoSearchList the listaModelPedProductoAsociadoSeleccionadoSearchList to set
	 */
	public void setListaModelPedProductoAsociadoSeleccionadoSearchList(
			DataTableModel listaModelPedProductoAsociadoSeleccionadoSearchList) {
		this.listaModelPedProductoAsociadoSeleccionadoSearchList = listaModelPedProductoAsociadoSeleccionadoSearchList;
	}

	/**
	 * @return the columnasSeleccionadasProductoAsociadoSeleccionado
	 */
	public List getColumnasSeleccionadasProductoAsociadoSeleccionado() {
		return columnasSeleccionadasProductoAsociadoSeleccionado;
	}

	/**
	 * @param columnasSeleccionadasProductoAsociadoSeleccionado the columnasSeleccionadasProductoAsociadoSeleccionado to set
	 */
	public void setColumnasSeleccionadasProductoAsociadoSeleccionado(
			List columnasSeleccionadasProductoAsociadoSeleccionado) {
		this.columnasSeleccionadasProductoAsociadoSeleccionado = columnasSeleccionadasProductoAsociadoSeleccionado;
	}

	/**
	 * @return the mantenimientoPEDProductoAsociadoDetalleAction
	 */
	public MantenimientoPEDProductoAsociadoDetalleAction getMantenimientoPEDProductoAsociadoDetalleAction() {
		return mantenimientoPEDProductoAsociadoDetalleAction;
	}

	/**
	 * @param mantenimientoPEDProductoAsociadoDetalleAction the mantenimientoPEDProductoAsociadoDetalleAction to set
	 */
	public void setMantenimientoPEDProductoAsociadoDetalleAction(
			MantenimientoPEDProductoAsociadoDetalleAction mantenimientoPEDProductoAsociadoDetalleAction) {
		this.mantenimientoPEDProductoAsociadoDetalleAction = mantenimientoPEDProductoAsociadoDetalleAction;
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
	 * @return the salirCambioCodigoVentaModificaCUV
	 */
	public boolean isSalirCambioCodigoVentaModificaCUV() {
		return salirCambioCodigoVentaModificaCUV;
	}

	/**
	 * @param salirCambioCodigoVentaModificaCUV the salirCambioCodigoVentaModificaCUV to set
	 */
	public void setSalirCambioCodigoVentaModificaCUV(boolean salirCambioCodigoVentaModificaCUV) {
		this.salirCambioCodigoVentaModificaCUV = salirCambioCodigoVentaModificaCUV;
	}

	/**
	 * @return the codigoPeriodoCambioCodigoVentaModificaCUV
	 */
	public String getCodigoPeriodoCambioCodigoVentaModificaCUV() {
		return codigoPeriodoCambioCodigoVentaModificaCUV;
	}

	/**
	 * @param codigoPeriodoCambioCodigoVentaModificaCUV the codigoPeriodoCambioCodigoVentaModificaCUV to set
	 */
	public void setCodigoPeriodoCambioCodigoVentaModificaCUV(String codigoPeriodoCambioCodigoVentaModificaCUV) {
		this.codigoPeriodoCambioCodigoVentaModificaCUV = codigoPeriodoCambioCodigoVentaModificaCUV;
	}

	/**
	 * @return the preProductoGrupoSearchList
	 */
	public List getPreProductoGrupoSearchList() {
		return preProductoGrupoSearchList;
	}

	/**
	 * @param preProductoGrupoSearchList the preProductoGrupoSearchList to set
	 */
	public void setPreProductoGrupoSearchList(List preProductoGrupoSearchList) {
		this.preProductoGrupoSearchList = preProductoGrupoSearchList;
	}

	/**
	 * @return the listaModelPreProductoGrupoSearchList
	 */
	public DataTableModel getListaModelPreProductoGrupoSearchList() {
		return listaModelPreProductoGrupoSearchList;
	}

	/**
	 * @param listaModelPreProductoGrupoSearchList the listaModelPreProductoGrupoSearchList to set
	 */
	public void setListaModelPreProductoGrupoSearchList(DataTableModel listaModelPreProductoGrupoSearchList) {
		this.listaModelPreProductoGrupoSearchList = listaModelPreProductoGrupoSearchList;
	}

	/**
	 * @return the columnasSeleccionadasProductoGrupo
	 */
	public Map getColumnasSeleccionadasProductoGrupo() {
		return columnasSeleccionadasProductoGrupo;
	}

	/**
	 * @param columnasSeleccionadasProductoGrupo the columnasSeleccionadasProductoGrupo to set
	 */
	public void setColumnasSeleccionadasProductoGrupo(Map columnasSeleccionadasProductoGrupo) {
		this.columnasSeleccionadasProductoGrupo = columnasSeleccionadasProductoGrupo;
	}

	/**
	 * @return the salirCambioCodigoVentaModificaCUVCompuestaFija
	 */
	public boolean isSalirCambioCodigoVentaModificaCUVCompuestaFija() {
		return salirCambioCodigoVentaModificaCUVCompuestaFija;
	}

	/**
	 * @param salirCambioCodigoVentaModificaCUVCompuestaFija the salirCambioCodigoVentaModificaCUVCompuestaFija to set
	 */
	public void setSalirCambioCodigoVentaModificaCUVCompuestaFija(boolean salirCambioCodigoVentaModificaCUVCompuestaFija) {
		this.salirCambioCodigoVentaModificaCUVCompuestaFija = salirCambioCodigoVentaModificaCUVCompuestaFija;
	}

	/**
	 * @return the codigoPeriodoCambioCodigoVentaModificaCUVCompuestaFija
	 */
	public String getCodigoPeriodoCambioCodigoVentaModificaCUVCompuestaFija() {
		return codigoPeriodoCambioCodigoVentaModificaCUVCompuestaFija;
	}

	/**
	 * @param codigoPeriodoCambioCodigoVentaModificaCUVCompuestaFija the codigoPeriodoCambioCodigoVentaModificaCUVCompuestaFija to set
	 */
	public void setCodigoPeriodoCambioCodigoVentaModificaCUVCompuestaFija(String codigoPeriodoCambioCodigoVentaModificaCUVCompuestaFija) {
		this.codigoPeriodoCambioCodigoVentaModificaCUVCompuestaFija = codigoPeriodoCambioCodigoVentaModificaCUVCompuestaFija;
	}

	/**
	 * @return the preProductoAsociadoCFSearchList
	 */
	public List getPreProductoAsociadoCFSearchList() {
		return preProductoAsociadoCFSearchList;
	}

	/**
	 * @param preProductoAsociadoCFSearchList the preProductoAsociadoCFSearchList to set
	 */
	public void setPreProductoAsociadoCFSearchList(List preProductoAsociadoCFSearchList) {
		this.preProductoAsociadoCFSearchList = preProductoAsociadoCFSearchList;
	}

	/**
	 * @return the listaModelPreProductoAsociadoCFSearchList
	 */
	public DataTableModel getListaModelPreProductoAsociadoCFSearchList() {
		return listaModelPreProductoAsociadoCFSearchList;
	}

	/**
	 * @param listaModelPreProductoAsociadoCFSearchList the listaModelPreProductoAsociadoCFSearchList to set
	 */
	public void setListaModelPreProductoAsociadoCFSearchList(DataTableModel listaModelPreProductoAsociadoCFSearchList) {
		this.listaModelPreProductoAsociadoCFSearchList = listaModelPreProductoAsociadoCFSearchList;
	}

	/**
	 * @return the columnasSeleccionadasProductoAsociadoCF
	 */
	public Map getColumnasSeleccionadasProductoAsociadoCF() {
		return columnasSeleccionadasProductoAsociadoCF;
	}

	/**
	 * @param columnasSeleccionadasProductoAsociadoCF the columnasSeleccionadasProductoAsociadoCF to set
	 */
	public void setColumnasSeleccionadasProductoAsociadoCF(Map columnasSeleccionadasProductoAsociadoCF) {
		this.columnasSeleccionadasProductoAsociadoCF = columnasSeleccionadasProductoAsociadoCF;
	}

	/**
	 * @return the oidCatalogoSeleccionado
	 */
	public String getOidCatalogoSeleccionado() {
		return oidCatalogoSeleccionado;
	}

	/**
	 * @param oidCatalogoSeleccionado the oidCatalogoSeleccionado to set
	 */
	public void setOidCatalogoSeleccionado(String oidCatalogoSeleccionado) {
		this.oidCatalogoSeleccionado = oidCatalogoSeleccionado;
	}
	
}
