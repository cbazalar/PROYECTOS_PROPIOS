/*
 * 
 */
package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.util.ArrayList;
import java.util.Arrays;
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

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.BoletaRecojoCabecera;
import biz.belcorp.ssicc.dao.sisicc.model.Cliente;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECLiquidacionBoletaRecojoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaConsultoraPOPUPSearchAction;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.MantenimientoRECLiquidacionBoletaRecojoForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes","unchecked"})
public class MantenimientoRECLiquidacionBoletaRecojoAction extends BaseMantenimientoSearchAbstractAction 
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The mantenimiento rec liquidacion boleta recojo cabecera list. */
	private List mantenimientoRECLiquidacionBoletaRecojoCabeceraList = new ArrayList();
	
	/** The sicc marca list. */
	private List siccMarcaList = new ArrayList();
	
	/** The sicc canal list. */
	private List siccCanalList = new ArrayList();
	
	/** The sicc region list. */
	private List siccRegionList = new ArrayList();
	
	/** The sicc zona list. */
	private LabelValue[] siccZonaList = new LabelValue[]{};
	
	/** The estados list. */
	private List estadosList = new ArrayList();
	
	/** The longitud campo clientes. */
	private Integer longitudCampoClientes = 0;
	
	/** The mostrar popup ocr cliente. */
	private boolean mostrarPopupOCRCliente = false;
	
	/** The Constant POPUP_OCRCLIENTE. */
	private static final String POPUP_OCRCLIENTE = "OCRCLIENTE";
	
	/** The busqueda hip cliente popup search action. */
	@ManagedProperty(value="#{busquedaConsultoraPOPUPSearchAction}")
	private BusquedaConsultoraPOPUPSearchAction busquedaConsultoraPOPUPSearchAction;
	
	/** flag mostrar/ocultar botones */
	private Boolean mostrarBotones = false;
	
	private List registrosSeleccionados;
	
	/** */
	@ManagedProperty(value="#{consultaRECBoletaRecojoPopupAction1}")
	private ConsultaRECBoletaRecojoPopupAction1 consultaRECBoletaRecojoPopupAction1;
	
	// utiliza para mostrar/ocultar popup
	private Boolean flag = false;

	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopupHipCliente' method");
		}		
		
		if (accion.equals(this.POPUP_OCRCLIENTE)) {
			this.busquedaConsultoraPOPUPSearchAction.verificarRegistro(event);
			if (this.busquedaConsultoraPOPUPSearchAction.isSeleccionoRegistro()) {								
				Cliente cliente = (Cliente)this.busquedaConsultoraPOPUPSearchAction.getBeanRegistroSeleccionado();		
				MantenimientoRECLiquidacionBoletaRecojoForm form = (MantenimientoRECLiquidacionBoletaRecojoForm)this.formBusqueda;
				form.setCodigoConsultora(cliente.getCodigo());
				this.busquedaConsultoraPOPUPSearchAction.setBeanRegistroSeleccionado(null);
			}
		}		
		if (log.isDebugEnabled()) {
			log.debug("Finish 'PopupHipCliente' method");
		}
	}
	
	@Override
	protected void setSalirPopup() 
	{
		this.mostrarPopupOCRCliente = false;
		this.busquedaConsultoraPOPUPSearchAction.setBeanRegistroSeleccionado(null);
	}

	@Override
	protected void setInvocarPopup(String accion) 
	{
		this.mostrarProcesoBatch = false;
		if (accion.equals(this.POPUP_OCRCLIENTE)){ 
			this.mostrarPopupOCRCliente = true;
		}
	}

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
		MantenimientoRECLiquidacionBoletaRecojoForm form = new MantenimientoRECLiquidacionBoletaRecojoForm(); 
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("setFindAttributes");
		}
		MantenimientoRECLiquidacionBoletaRecojoForm f = (MantenimientoRECLiquidacionBoletaRecojoForm) this.formBusqueda;
		Map criteria = getCriteria(f);

		List regionList = (List) MapUtils.getObject(criteria, "regionList");
		List zonaList = (List) MapUtils.getObject(criteria, "zonaList");

		if (regionList == null || regionList.size() == 0 || (regionList.size() == 1 && StringUtils
						.isBlank(((String) regionList.get(0)))))
			criteria.put("regionList", null);

		if (zonaList == null || zonaList.size() == 0 || (zonaList.size() == 1 && StringUtils
						.isBlank(((String) zonaList.get(0)))))
			criteria.put("zonaList", null);

		List l = new ArrayList();
		MantenimientoRECLiquidacionBoletaRecojoService service = (MantenimientoRECLiquidacionBoletaRecojoService) getBean("spusicc.mantenimientoRECLiquidacionBoletaRecojoService");
		l = service.getCabecerasBoletasReclamo(criteria);
		this.mantenimientoRECLiquidacionBoletaRecojoCabeceraList = l;
		setEstadisticas(f, criteria, service);

		AjaxService ajax = (AjaxService) getBean("ajaxService");

		LabelValue[] lista = ajax.getZonasMultipleByPaisMarcaCanalRegion(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),
				Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, f.getCodigoRegion(), "");

		if (regionList == null) {
			lista = new LabelValue[] {};
		}

		this.setSiccZonaList(lista);
		
		if(l.size() > 0)
			this.mostrarBotones = true;
		else
			this.mostrarBotones = false;

		return l;
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
	protected void setViewAtributes() throws Exception 
	{
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		MantenimientoRECLiquidacionBoletaRecojoForm form = (MantenimientoRECLiquidacionBoletaRecojoForm)this.formBusqueda;
		form.setCodigoEstado("");		
		form.setCodigoMarca("T");
		form.setCodigoCanal("VD");
		this.mostrarBotonBuscar = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonNuevo = false;		
		this.mostrarListaBusqueda = false;
		
		this.loadCombos();
	}
		
	public void ejecutar(ActionEvent event)
	{
		try {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ejecutar' method");
			}		
			MantenimientoRECLiquidacionBoletaRecojoForm f = (MantenimientoRECLiquidacionBoletaRecojoForm)this.formBusqueda;
			MantenimientoRECLiquidacionBoletaRecojoService service = (MantenimientoRECLiquidacionBoletaRecojoService)getBean("spusicc.mantenimientoRECLiquidacionBoletaRecojoService");
					
			String[] ids = null;
			if(this.registrosSeleccionados != null && this.registrosSeleccionados.size() >0)
			{
				ids = new String[this.registrosSeleccionados.size()];
				for (int i = 0; i < this.registrosSeleccionados.size(); i++) 
				{
					BoletaRecojoCabecera aux = (BoletaRecojoCabecera)this.registrosSeleccionados.get(i);
					ids[i] = aux.getBoletaRecojo();										
				}
			}
			
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			
			Map params = new HashMap();
			params.put("codigoPais", f.getCodigoPais());
			
			List l = new ArrayList();
			List nuevaLista = new ArrayList();
			
			log.debug("___IndicadorEjecucion = "+f.getIndicadorEjecucion());
//			request.getSession().removeAttribute(Constants.REC_CABECERAS_RECLAMOS_LIST);				
			
			// Opcion APROBAR
			if(f.getIndicadorEjecucion().equals(Constants.REC_INDICADOR_APRUEBA))
			{
				params.put("codigoUsuario", usuario.getLogin());
				service.aprobarReclamoDigitados(params,ids);			
				this.listaBusqueda = service.getCabecerasBoletasReclamo(getCriteria(f));
				this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
				setEstadisticas(f,getCriteria(f),service);
				this.addInfo("", this.getResourceMessage("mantenimientoRECLiquidacionBoletaRecojo.mensaje.aprobada.ok"));
			}
			
			// Opcion RECHAZAR
			if(f.getIndicadorEjecucion().equals(Constants.REC_INDICADOR_RECHAZA))
			{
				Map criteria = new HashMap();
				criteria.put("ids",ids);
				
				criteria.put("codigoUsuario", usuario.getLogin());
				service.updateRechazarReclamoDigitados(criteria);			
				this.listaBusqueda = service.getCabecerasBoletasReclamo(getCriteria(f));
				this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
				setEstadisticas(f,getCriteria(f),service);
				this.addInfo("", this.getResourceMessage("mantenimientoRECLiquidacionBoletaRecojo.mensaje.rechazada.ok"));
			}
			
			// Opcion ELIMINAR
			if(f.getIndicadorEjecucion().equals(Constants.REC_INDICADOR_ELIMINA))
			{			
				l = this.listaBusqueda;
				
				for (int i = 0; i < l.size(); i++) 
				{
					boolean flag = true;
					BoletaRecojoCabecera bc = new BoletaRecojoCabecera();
					bc = (BoletaRecojoCabecera)l.get(i);
					for (int j = 0; j < ids.length; j++) {
						if(ids[j].equals(bc.getBoletaRecojo())){
							flag = false;
							break;
						}	
					}
					
					if(flag) nuevaLista.add(bc);
				}
				this.listaBusqueda = nuevaLista;
				this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
			}
			
			//-- Opcion Recalcular
			if(f.getIndicadorEjecucion().equals(Constants.REC_INDICADOR_RECALCULO))
			{	
				//-- Actualizar Recalculo
				service.updateRecalcularBoletaRecojo(params,ids);
				
				//-- Actualizar listado
				List lista = service.getCabecerasBoletasReclamo(getCriteria(f)); 	
				
				//-- Peticiones retorno
				this.listaBusqueda = lista;
				this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
			}			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}		
	}
	
	/**
	 * Load combos.
	 */
	private void loadCombos() 
	{
		if(log.isDebugEnabled()){
			log.debug("loadCombos");
		}
		
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ClienteUAGenerarService clienteService = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");
		MantenimientoRECLiquidacionBoletaRecojoService serviceRec = (MantenimientoRECLiquidacionBoletaRecojoService)getBean("spusicc.mantenimientoRECLiquidacionBoletaRecojoService");		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		this.setSiccMarcaList(service.getMarcas());
		this.setSiccCanalList(service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO()));
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		
		this.setSiccRegionList(reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion));
		
		//cargamos primer valor de zona
		LabelValue todos = new LabelValue();
		todos.setLabel("Todos");
		todos.setValue("");
		this.siccZonaList = new LabelValue[]{todos};
		
		this.setLongitudCampoClientes(clienteService.getTamanhoNumeroCliente(pais.getCodigo()));		
		this.setEstadosList(serviceRec.getEstados());
	}
	
	/**
	 * Gets the criteria.
	 *
	 * @param f the f
	 * @return the criteria
	 */
	private Map getCriteria(MantenimientoRECLiquidacionBoletaRecojoForm f) {
		Map criteria = new HashMap();
		criteria.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		criteria.put("codigoPeriodo", f.getPeriodo());
		criteria.put("codigoPeriodoFinal", f.getPeriodoFinal());
		criteria.put("numeroBoleta", f.getNumeroBoleta());
		criteria.put("codigoConsultora", f.getCodigoConsultora());
		if(StringUtils.isNotBlank(f.getCodigoEstado())&&StringUtils.isNotEmpty(f.getCodigoEstado())){
			criteria.put("codigoEstado", f.getCodigoEstado());	
		}
		if(f.getCodigoRegion().length > 0){
			if(!f.getCodigoRegion()[0].equals("")){
				criteria.put("regionList",Arrays.asList(f.getCodigoRegion()));
				if(f.getCodigoZona() != null){
					criteria.put("zonaList",Arrays.asList(f.getCodigoZona()));
				}
			}
		}
		return criteria;
	}
	
	/**
	 * Sets the estadisticas.
	 *
	 * @param f the f
	 * @param criteria the criteria
	 * @param service the service
	 */
	private void setEstadisticas(MantenimientoRECLiquidacionBoletaRecojoForm f,
			Map criteria, MantenimientoRECLiquidacionBoletaRecojoService service) {
		f.setTotalBoletasRecojo(service.getTotalBR(criteria));
		f.setBoletasRecojoPendientes(service.getPendientesBR(criteria));
		f.setBoletasRecojoAprobadas(service.getAprobadasBR(criteria));
		f.setBoletasRecojoRechazadas(service.getRechazadasBR(criteria));
		f.setTotalCargos(service.getTotalCargosBR(criteria));
		f.setTotalAbonos(service.getTotalAbonosBR(criteria));
	}
	
	/**
	 * Show zonas x region.
	 *
	 * @param val the val
	 */
	public void showZonasxRegion(ValueChangeEvent val) 
	{
		String[] regionListado = (String[]) val.getNewValue();

		AjaxService ajax = (AjaxService) getBean("ajaxService");
		if (regionListado.length > 0) 
		{
			siccZonaList = ajax.getZonasMultipleByPaisMarcaCanalRegion(this.getmPantallaPrincipalBean().getCurrentCountry()
							.getCodigo(), "T", "VD", regionListado, "T");
		} else {
			siccZonaList = new LabelValue[]{};
		}
	}
	
	@Override
	public String setValidarConfirmar(String accion) 
	{
		MantenimientoRECLiquidacionBoletaRecojoForm f = (MantenimientoRECLiquidacionBoletaRecojoForm) this.formBusqueda;
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String valor = externalContext.getRequestParameterMap().get("parametroOtros");
		
		if(accion.equals("BOTON_BUSCAR"))
		{
			Long periodoIni = Long.parseLong(StringUtils.isBlank(f.getPeriodo())?"0":f.getPeriodo());
			Long periodoFin = Long.parseLong(StringUtils.isBlank(f.getPeriodoFinal())?"0":f.getPeriodoFinal());
			
			if(StringUtils.isBlank(f.getPeriodo()))
				return "'Periodo' es un campo requerido.";
			
			if(periodoIni.compareTo(periodoFin) > 0 && periodoFin.intValue() != 0)
				return this.getResourceMessage("reporteRECBoletaRecojoStatusForm.validacionFechas");			
		}
		
		if(accion.equals("BOTON_APROBAR"))
		{
			f.setIndicadorEjecucion(valor);
			if(this.registrosSeleccionados == null || this.registrosSeleccionados.size()== 0)
				return this.getResourceMessage("errors.select.item");
		}
		
		if(accion.equals("BOTON_RECHAZAR"))
		{
			f.setIndicadorEjecucion(valor);
			if(this.registrosSeleccionados == null || this.registrosSeleccionados.size()== 0)
				return this.getResourceMessage("errors.select.item");
		}
		
		if(accion.equals("BOTON_RECALCULAR"))
		{
			f.setIndicadorEjecucion(valor);
			if(this.registrosSeleccionados == null || this.registrosSeleccionados.size()== 0)
				return this.getResourceMessage("errors.select.item");
		}			
		
		return null;
	}
	
	public void viewAjax(ActionEvent event)
	{
		if(this.registrosSeleccionados == null || this.registrosSeleccionados.size() == 0)
		{
			this.flag = false;
			this.setMensajeAlertaDefault(this.getResourceMessage("errors.select.item"));
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return;
		}
		
		if(this.registrosSeleccionados.size() > 1)
		{
			this.flag = false;
			this.setMensajeAlertaDefault(this.getResourceMessage("errors.select.unique.item"));
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return;			
		}
		
		if(this.registrosSeleccionados.size() == 1)
		{
			this.flag = true;
			consultaRECBoletaRecojoPopupAction1.setBeanRegistroSeleccionado(this.registrosSeleccionados.get(0));
			consultaRECBoletaRecojoPopupAction1.search(event);
		}		
	}

	public List getMantenimientoRECLiquidacionBoletaRecojoCabeceraList() {
		return mantenimientoRECLiquidacionBoletaRecojoCabeceraList;
	}

	public void setMantenimientoRECLiquidacionBoletaRecojoCabeceraList(
			List mantenimientoRECLiquidacionBoletaRecojoCabeceraList) {
		this.mantenimientoRECLiquidacionBoletaRecojoCabeceraList = mantenimientoRECLiquidacionBoletaRecojoCabeceraList;
	}

	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	public List getSiccCanalList() {
		return siccCanalList;
	}

	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public Integer getLongitudCampoClientes() {
		return longitudCampoClientes;
	}

	public void setLongitudCampoClientes(Integer longitudCampoClientes) {
		this.longitudCampoClientes = longitudCampoClientes;
	}

	public List getEstadosList() {
		return estadosList;
	}

	public void setEstadosList(List estadosList) {
		this.estadosList = estadosList;
	}

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	public BusquedaConsultoraPOPUPSearchAction getBusquedaConsultoraPOPUPSearchAction() {
		return busquedaConsultoraPOPUPSearchAction;
	}

	public void setBusquedaConsultoraPOPUPSearchAction(
			BusquedaConsultoraPOPUPSearchAction busquedaConsultoraPOPUPSearchAction) {
		this.busquedaConsultoraPOPUPSearchAction = busquedaConsultoraPOPUPSearchAction;
	}

	public boolean isMostrarPopupOCRCliente() {
		return mostrarPopupOCRCliente;
	}

	public void setMostrarPopupOCRCliente(boolean mostrarPopupOCRCliente) {
		this.mostrarPopupOCRCliente = mostrarPopupOCRCliente;
	}

	public Boolean getMostrarBotones() {
		return mostrarBotones;
	}

	public void setMostrarBotones(Boolean mostrarBotones) {
		this.mostrarBotones = mostrarBotones;
	}

	public static String getPopupOcrcliente() {
		return POPUP_OCRCLIENTE;
	}

	public List getRegistrosSeleccionados() {
		return registrosSeleccionados;
	}

	public void setRegistrosSeleccionados(List registrosSeleccionados) {
		this.registrosSeleccionados = registrosSeleccionados;
	}

	public ConsultaRECBoletaRecojoPopupAction1 getConsultaRECBoletaRecojoPopupAction1() {
		return consultaRECBoletaRecojoPopupAction1;
	}

	public void setConsultaRECBoletaRecojoPopupAction1(
			ConsultaRECBoletaRecojoPopupAction1 consultaRECBoletaRecojoPopupAction1) {
		this.consultaRECBoletaRecojoPopupAction1 = consultaRECBoletaRecojoPopupAction1;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	
}


