/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.model.BaseOID;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDDefinirOfertaForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDProductoAsociadoDetalleForm;

/**
 * @author Sigcomt
 *
 */
@ManagedBean
@SessionScoped
public class MantenimientoPEDProductoAsociadoDetalleAction extends BaseMantenimientoSearchAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 415682758053575191L;
	
	private int indiceProductoActual = 0;
	private List siccCatalagoList = new ArrayList();
	private List pedProductoAsociadoTipoOfertaList = new ArrayList();
	
	private MantenimientoPEDDefinirOfertaForm formMantenimientoPEDDefinirOferta;
	
	private List pedProductoAsociadoSeleccionadoSearchList;
	private String pedOfertaCatalogoSeleccionado;
	private String pedOfertaEstrategiaSeleccionada;	

	private List pedEstrategiaList;
	
	private boolean boolFlagDigitable = false;
	private boolean boolFlagImprimible = false;
	
	private String pedOfertaTipoBusquedaSeleccionada;
	
	private boolean noPasaValidacionTipoOferta = false;

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
		// TODO Auto-generated method stub
		return null;
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

		MantenimientoPEDProductoAsociadoDetalleForm editForm = (MantenimientoPEDProductoAsociadoDetalleForm) this.formMantenimiento;
		String tipoBusqueda = this.pedOfertaTipoBusquedaSeleccionada;
		List productos = null;
		
		if(this.noPasaValidacionTipoOferta){
			throw new Exception("Elegir un Tipo Oferta que supere las validaciones");
		}
		
		if(this.boolFlagDigitable)
			editForm.setFlagDigitable(Constants.NUMERO_UNO);
		else
			editForm.setFlagDigitable(Constants.NUMERO_CERO);
		
		if(this.boolFlagImprimible)
			editForm.setFlagImprimible(Constants.NUMERO_UNO);
		else
			editForm.setFlagImprimible(Constants.NUMERO_CERO);
		
		if(StringUtils.equals(tipoBusqueda, Constants.PED_TIPO_BUSQUEDA_PRODUCTO_ASOCIADO))
			productos = this.mPantallaPrincipalBean.getPedProductoAsociadoOfertaList();
		else if(StringUtils.equals(tipoBusqueda, Constants.PED_TIPO_BUSQUEDA_PRODUCTO_ASOCIADO_GRUPO))
			productos = this.mPantallaPrincipalBean.getPedProductoAsociadoOfertaGrupoList();

		if(StringUtils.equals(this.pedOfertaEstrategiaSeleccionada, "2001")){
			productos = null;
			this.mPantallaPrincipalBean.setPedProductoAsociadoOfertaList(null);
		}
		
		List listaSeleccionados = this.pedProductoAsociadoSeleccionadoSearchList;						
		
		if(productos == null)
			productos = new ArrayList();
		
		Map producto = (Map)listaSeleccionados.get(indiceProductoActual);
		//Agregamos/Actualizamos los valores que el usuario ha seleccionado
		producto.put("textoBreve", editForm.getTextoBreve());
		producto.put("factorRepeticion", editForm.getFactorRepeticion());
		producto.put("flagDigitable", editForm.getFlagDigitable());
		producto.put("flagImprimible", editForm.getFlagImprimible());
		producto.put("precioCatalogo", editForm.getPrecioCatalogo());
		producto.put("precioPosicionamiento", editForm.getPrecioPosicionamiento());
		producto.put("costoEstandar", editForm.getCostoEstandar());
		producto.put("unidadesEstimadas", editForm.getUnidadesEstimadas());
		producto.put("ventaNetaEstimada", editForm.getVentaNetaEstimada());
		producto.put("numeroPaginaCatalogo", editForm.getNumeroPaginaCatalogo());
		producto.put("oidTipoOferta", editForm.getOidTipoOferta());
		producto.put("tipoOferta", getCodigoTipoOferta(editForm.getOidTipoOferta()));
		
		productos.add(producto);
		
		if(StringUtils.equals(tipoBusqueda, Constants.PED_TIPO_BUSQUEDA_PRODUCTO_ASOCIADO))
			this.mPantallaPrincipalBean.setPedProductoAsociadoOfertaList(productos);
		else if(StringUtils.equals(tipoBusqueda, Constants.PED_TIPO_BUSQUEDA_PRODUCTO_ASOCIADO_GRUPO)){
			this.mPantallaPrincipalBean.setPedProductoAsociadoOfertaGrupoList(productos);
			this.mPantallaPrincipalBean.setListaModelPedProductoAsociadoOfertaGrupoList(new DataTableModel(this.mPantallaPrincipalBean.getPedProductoAsociadoOfertaGrupoList()));
		}
			
		
		if(productos.size() < listaSeleccionados.size())
		{
			indiceProductoActual++;		
			cargarDatosProducto(editForm);
		}
		else
		{
			editForm.setFlagCerrarVentana(true);
			this.mostrarBotonSave = false;
			this.redireccionarPagina("mantenimientoPEDDefinirOfertaForm");
		}				
		return true;
	}
	
	/**
	 * 
	 * @param oidTipoOferta
	 * @return
	 */
	private String getCodigoTipoOferta(String oidTipoOferta) {
		
		String ret = "";
		
		List tipos = this.pedProductoAsociadoTipoOfertaList;
		
		if(tipos != null && tipos.size() > 0)
		{
			for(int i=0; i<tipos.size(); i++)
			{
				BaseOID tipo = (BaseOID)tipos.get(i);
				
				if(StringUtils.equals(tipo.getOid().toString(), oidTipoOferta))
				{
					ret = tipo.getDescripcion();
					break;
				}
			}
		}
		
		return ret;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {

		String mensaje = "mantenimientoPEDProductoAsociadoDetalleForm.actualizado";		
		return mensaje;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		
//		HttpSession session = request.getSession();
//		MantenimientoPEDProductoAsociadoDetalleForm editForm = (MantenimientoPEDProductoAsociadoDetalleForm)form;
//		
//		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
//		session.setAttribute(Constants.SICC_CATALOGO_LIST, reporteService.getListaGenerico("getOidCatalogoProductos", null));
//		session.setAttribute(Constants.PED_PRODUCTO_ASOCIADO_TIPO_OFERTA_LIST, reporteService.getListaReporte("getOidTipoOfertasList", null));
//				
//		indiceProductoActual = 0;
//		cargarDatosProducto(session, editForm);
		
		//
	}
	
	/**
	 * 
	 */
	public void inicializarValoresDetalle(){
		MantenimientoPEDProductoAsociadoDetalleForm editForm = new MantenimientoPEDProductoAsociadoDetalleForm();
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		this.siccCatalagoList = reporteService.getListaGenerico("getOidCatalogoProductos", null);
		this.pedProductoAsociadoTipoOfertaList =  reporteService.getListaReporte("getOidTipoOfertasList", null);				
		this.indiceProductoActual = 0;
		cargarDatosProducto( editForm);
		this.mostrarBotonSalir = false;
		this.mostrarBotonSave = true;
	}
	
	/**
	 * @param editForm
	 */
	public void cargarDatosProducto(MantenimientoPEDProductoAsociadoDetalleForm editForm){
			
		limpiarForm(editForm);
		
		// Lista de productos seleccionados en la pantalla de busqueda de productos
		List listaSeleccionados = this.pedProductoAsociadoSeleccionadoSearchList;
		
		/* Viene desde la pantalla de DefinirOferta */
		String oidCatalogoSeleccionado = this.pedOfertaCatalogoSeleccionado;		
		String oidEstrategiaSeleccionada = this.pedOfertaEstrategiaSeleccionada;
		List estrategias =  this.pedEstrategiaList;
		String oidTipoEstrategia = getTipoEstrategia(estrategias, oidEstrategiaSeleccionada);
		/**/
		
		//Tomamos el producto con el indice actual
		Map productoActual = (Map)listaSeleccionados.get(this.indiceProductoActual);		
		editForm.setMensajeProductoActual(String.format("Producto %s de %s", Integer.valueOf(this.indiceProductoActual + 1).toString(), Integer.valueOf(listaSeleccionados.size())));
		
		editForm.setCodigoSap(MapUtils.getString(productoActual, "codigoSap", ""));
		editForm.setDescripcion(MapUtils.getString(productoActual, "descripcion", ""));
		editForm.setOidCatalogo(oidCatalogoSeleccionado);	
		
		editForm.setFlagImprimible(Constants.NUMERO_CERO);
		this.boolFlagImprimible = false;
		editForm.setPrecioCatalogo(MapUtils.getString(productoActual, "precioCatalogo", ""));
		editForm.setPrecioPosicionamiento(MapUtils.getString(productoActual, "precioPosicionamiento", ""));
		editForm.setCostoEstandar(MapUtils.getString(productoActual, "costoEstandar", ""));
		editForm.setUnidadesEstimadas(MapUtils.getString(productoActual, "unidadesEstimadas", ""));
		editForm.setVentaNetaEstimada(MapUtils.getString(productoActual, "ventaNetaEstimada", ""));
		editForm.setNumeroPaginaCatalogo(MapUtils.getString(productoActual, "numeroPaginaCatalogo", ""));
		editForm.setOidEstrategia(oidEstrategiaSeleccionada);
		
		editForm.setOidTipoOferta(MapUtils.getString(productoActual, "oidTipoOferta", ""));
		
		//Digitable
		if(StringUtils.equals(oidTipoEstrategia, Constants.PED_OID_TIPO_ESTRATEGIA_UNO) || 
				StringUtils.equals(oidTipoEstrategia, Constants.PED_OID_TIPO_ESTRATEGIA_TRES) ||
				StringUtils.equals(oidTipoEstrategia, Constants.PED_OID_TIPO_ESTRATEGIA_SIETE))
		{
			editForm.setFlagDigitable(Constants.NUMERO_UNO);
			editForm.setFlagImprimible(Constants.NUMERO_UNO);
			this.boolFlagDigitable = true;
			this.boolFlagImprimible = true;
			editForm.setFlagDigitableSoloLectura(false);
			editForm.setFlagImprimibleSoloLectura(false);
			
			if(StringUtils.equals(oidTipoEstrategia, Constants.PED_OID_TIPO_ESTRATEGIA_UNO)){
				editForm.setFlagDigitable(Constants.NUMERO_CERO);
				editForm.setFlagImprimible(Constants.NUMERO_CERO);
				this.boolFlagDigitable = false;
				this.boolFlagImprimible = false;
				editForm.setFlagDigitableSoloLectura(true);
				editForm.setFlagImprimibleSoloLectura(true);
			}
		}
		else if(StringUtils.equals(oidTipoEstrategia, Constants.PED_OID_TIPO_ESTRATEGIA_CINCO) ||
				StringUtils.equals(oidTipoEstrategia, Constants.PED_OID_TIPO_ESTRATEGIA_SEIS))
		{
			editForm.setFlagDigitable(Constants.NUMERO_CERO);
			this.boolFlagDigitable = false;
			editForm.setFlagDigitableSoloLectura(false);

		}
		else if(StringUtils.equals(oidTipoEstrategia, Constants.PED_OID_TIPO_ESTRATEGIA_DOS))
		{
			//Verificamos en la lista si ya hay un producto marcado
			if(validarExisteDigitable(this.mPantallaPrincipalBean.getPedProductoAsociadoOfertaList()))
			{
				editForm.setFlagDigitable(Constants.NUMERO_CERO);
				this.boolFlagDigitable = false;
				editForm.setFlagDigitableSoloLectura(true);
			}
			else
			{
				//Si es el ultimo y no hay ningun marcado, se setea como marcado y se inhabilita
				if(listaSeleccionados.size() == (this.indiceProductoActual + 1))
				{
					editForm.setFlagDigitable(Constants.NUMERO_UNO);
					this.boolFlagDigitable = true;
					editForm.setFlagDigitableSoloLectura(false);				
				}
			}
			/*editForm.setFlagDigitable(Constants.NUMERO_CERO);
			editForm.setFlagImprimible(Constants.NUMERO_CERO);
			this.boolFlagDigitable = false;
			this.boolFlagImprimible = false;
			editForm.setFlagDigitableSoloLectura(true);
			editForm.setFlagImprimibleSoloLectura(true);*/
		}
		
//		editForm.setFlagDigitable(Constants.NUMERO_UNO);
//		editForm.setFlagImprimible(Constants.NUMERO_UNO);
//		this.boolFlagDigitable = true;
//		this.boolFlagImprimible = true;
//		editForm.setFlagDigitableSoloLectura(false);
//		editForm.setFlagImprimibleSoloLectura(false);
		
		this.formMantenimiento = editForm;
	}
	
	/**
	 * @param lista
	 * @return
	 */
	private boolean validarExisteDigitable(List lista) {
			
			boolean ret = false;
			List productos = lista;
			
			if(productos != null && productos.size() > 0)
			{
				for(int i=0; i<productos.size(); i++)
				{
					Map producto = (Map)productos.get(i);
					
					if(StringUtils.equals(MapUtils.getString(producto, "flagDigitable"), Constants.NUMERO_UNO))
					{
						ret = true;
						break;
					}
				}
				
			}
			
			return ret;
		}
	
	/**
	 * @param estrategiaList
	 * @param oidEstrategiaSeleccionada
	 * @return
	 */
	private String getTipoEstrategia(List  estrategiaList, String oidEstrategiaSeleccionada) {
		String oidTipo = null;

		List estrategias = estrategiaList;
		
		if(estrategias != null && estrategias.size() > 0)
		{
			for(int i=0; i<estrategias.size(); i++)
			{
				Map est = (Map)estrategias.get(i);
				if(StringUtils.equals(MapUtils.getString(est, "oid"), oidEstrategiaSeleccionada))
				{
					oidTipo = MapUtils.getString(est, "oidTipo");
					break;
				}
			}
		}
		
		return oidTipo;
	}
	
	/**
	 * @param editForm
	 */
	private void limpiarForm(MantenimientoPEDProductoAsociadoDetalleForm editForm)
	{
		editForm.setCodigoSap("");
		editForm.setTextoBreve("");
		editForm.setOidCatalogo("");
		editForm.setFactorRepeticion("1");
		this.boolFlagDigitable = false;
		editForm.setFlagDigitable(Constants.NUMERO_CERO);
		this.boolFlagImprimible = false;
		editForm.setFlagImprimible(Constants.NUMERO_CERO);
		editForm.setPrecioCatalogo("");
		editForm.setPrecioPosicionamiento("");
		editForm.setCostoEstandar("");
		editForm.setUnidadesEstimadas("");
		editForm.setVentaNetaEstimada("");
		editForm.setNumeroPaginaCatalogo("");
		editForm.setOidTipoOferta("");
		
		editForm.setMensajeProductoActual("");
		editForm.setFlagDigitableSoloLectura(false);
		editForm.setFlagCerrarVentana(false);
	}

	/**
	 * @param event
	 */
	public void validarTipoOferta(ValueChangeEvent val){
		
		log.debug("Enter method - validarTipoOferta");
		try {
			
			MantenimientoPEDProductoAsociadoDetalleForm editForm = (MantenimientoPEDProductoAsociadoDetalleForm) this.formMantenimiento;
			AjaxService ajax = (AjaxService)getBean("ajaxService");
//			String oidTipoOferta = editForm.getOidTipoOferta();
			
			editForm.setOidTipoOferta(val.getNewValue().toString());
			String oidTipoOferta = editForm.getOidTipoOferta();
			
			String codigoSap = editForm.getCodigoSap();
			String oidEstrategia = editForm.getOidEstrategia();
			String precioCatalogo = editForm.getPrecioCatalogo();
			String precioPosicionamiento = editForm.getPrecioPosicionamiento();
			
			String data = ajax.validarTipoOferta(oidTipoOferta, codigoSap, oidEstrategia, precioCatalogo, precioPosicionamiento);	
			
			if(data != ""){
				this.noPasaValidacionTipoOferta = true;
				this.addInfo("Info: ", data);
			}else{
				this.noPasaValidacionTipoOferta = false;
			}
		} catch (Exception e) {

			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setValidarMantenimiento()
	 */
	@Override
	public String setValidarMantenimiento() {

		MantenimientoPEDProductoAsociadoDetalleForm editForm = (MantenimientoPEDProductoAsociadoDetalleForm) this.formMantenimiento;
		String mensaje = null;
		
		/*if(this.boolFlagDigitable = false){
			editForm.setFlagDigitableSoloLectura(false);
			editForm.setFlagDigitable(Constants.NUMERO_CERO);
			
		}*/
		
		if(StringUtils.isBlank(editForm.getFactorRepeticion()))
			return this.getResourceMessage("mantenimientoPEDProductoAsociadoDetalleForm.factorRepeticion.requerido");
		
		if(StringUtils.isBlank(editForm.getOidTipoOferta()))
			return this.getResourceMessage("mantenimientoPEDProductoAsociadoDetalleForm.oidTipoOferta.requerido"); 

		return mensaje;
	}

	/**
	 * @param event
	 */
	public void salirPers(ActionEvent event){
		
		log.debug("Enter method - salirPers");
		
		try {
			MantenimientoPEDProductoAsociadoDetalleForm editForm = (MantenimientoPEDProductoAsociadoDetalleForm) this.formMantenimiento;
			
			if(editForm.isFlagCerrarVentana()){				
				MantenimientoPEDDefinirOfertaAction action =  findManageBean("mantenimientoPEDDefinirOfertaAction");
				action.recargar(this.pedOfertaTipoBusquedaSeleccionada);
				this.redireccionarPagina("mantenimientoPEDDefinirOfertaForm");				
			}else{
				this.mPantallaPrincipalBean.setPedProductoAsociadoOfertaGrupoList(null);
				this.mPantallaPrincipalBean.setListaModelPedProductoAsociadoOfertaGrupoList(null);
				this.mPantallaPrincipalBean.setPedProductoAsociadoOfertaList(null);				
				this.redireccionarPagina("mantenimientoPEDDefinirOfertaForm");
			}
			
		} catch (Exception e) {

			this.addError("Info: ", this.obtieneMensajeErrorException(e));
		}
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setSaveFindBeforeAttributes()
	 */
	@Override
	public void setSaveFindBeforeAttributes() throws Exception {
		this.mostrarErrorNoExistenRegistroBusqueda = false;
		
		super.setSaveFindBeforeAttributes();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setSalirFindAfterAttributes()
	 */
	@Override
	public void setSalirFindAfterAttributes() throws Exception {
		this.mostrarErrorNoExistenRegistroBusqueda = true;
		
		super.setSalirFindAfterAttributes();
	}

	/**
	 * @return the indiceProductoActual
	 */
	public int getIndiceProductoActual() {
		return indiceProductoActual;
	}

	/**
	 * @param indiceProductoActual the indiceProductoActual to set
	 */
	public void setIndiceProductoActual(int indiceProductoActual) {
		this.indiceProductoActual = indiceProductoActual;
	}

	/**
	 * @return the formMantenimientoPEDDefinirOferta
	 */
	public MantenimientoPEDDefinirOfertaForm getFormMantenimientoPEDDefinirOferta() {
		return formMantenimientoPEDDefinirOferta;
	}

	/**
	 * @param formMantenimientoPEDDefinirOferta the formMantenimientoPEDDefinirOferta to set
	 */
	public void setFormMantenimientoPEDDefinirOferta(
			MantenimientoPEDDefinirOfertaForm formMantenimientoPEDDefinirOferta) {
		this.formMantenimientoPEDDefinirOferta = formMantenimientoPEDDefinirOferta;
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
	 * @return the pedProductoAsociadoTipoOfertaList
	 */
	public List getPedProductoAsociadoTipoOfertaList() {
		return pedProductoAsociadoTipoOfertaList;
	}

	/**
	 * @param pedProductoAsociadoTipoOfertaList the pedProductoAsociadoTipoOfertaList to set
	 */
	public void setPedProductoAsociadoTipoOfertaList(
			List pedProductoAsociadoTipoOfertaList) {
		this.pedProductoAsociadoTipoOfertaList = pedProductoAsociadoTipoOfertaList;
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
	 * @return the boolFlagDigitable
	 */
	public boolean isBoolFlagDigitable() {
		return boolFlagDigitable;
	}

	/**
	 * @param boolFlagDigitable the boolFlagDigitable to set
	 */
	public void setBoolFlagDigitable(boolean boolFlagDigitable) {
		this.boolFlagDigitable = boolFlagDigitable;
	}

	/**
	 * @return the boolFlagImprimible
	 */
	public boolean isBoolFlagImprimible() {
		return boolFlagImprimible;
	}

	/**
	 * @param boolFlagImprimible the boolFlagImprimible to set
	 */
	public void setBoolFlagImprimible(boolean boolFlagImprimible) {
		this.boolFlagImprimible = boolFlagImprimible;
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
	 * @return the noPasaValidacionTipoOferta
	 */
	public boolean isNoPasaValidacionTipoOferta() {
		return noPasaValidacionTipoOferta;
	}

	/**
	 * @param noPasaValidacionTipoOferta the noPasaValidacionTipoOferta to set
	 */
	public void setNoPasaValidacionTipoOferta(boolean noPasaValidacionTipoOferta) {
		this.noPasaValidacionTipoOferta = noPasaValidacionTipoOferta;
	}
	
}
