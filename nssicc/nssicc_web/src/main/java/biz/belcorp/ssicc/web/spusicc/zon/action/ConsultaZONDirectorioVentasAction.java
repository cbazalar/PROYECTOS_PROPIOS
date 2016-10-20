package biz.belcorp.ssicc.web.spusicc.zon.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.LabelValueCDR;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.IdiomaService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.zon.MantenimientoZONDirectorioService;
import biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.form.ConsultaZONDirectorioVentasForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaHIPClienteSearchAction;
import biz.belcorp.ssicc.web.scsicc.hip.form.BusquedaHIPClienteSearchForm;

@ManagedBean(name="consultaZONDirectorioVentasAction1")
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ConsultaZONDirectorioVentasAction extends
		BaseConsultaAbstractAction {
	
	private static final long serialVersionUID = 6712020878314851740L;
	private LabelValue[] siccRegionList;
	private LabelValueCDR[] siccZonaList;
	private String idCargo;
	private String zonIndiResuDeta;
	private String validarBarrio;
	private int longitudCampoClientes;
	private List siccNovedadList;
	private List siccZonManCargList;
	private List siccManCargList;
	private List siccRolList;
	private List siccPerfilList;
	private List siccConsultaList;
	private LabelValue[] siccZonEstadosList;
	private boolean mostrarPopUpCliente = false;
	private static final String POPUP_CLIENTES = "SCLIENTES";

	@ManagedProperty(value="#{busquedaHIPClienteSearchAction}")
	private BusquedaHIPClienteSearchAction busquedaHIPClienteSearchAction;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setAceptarPopup(javax.faces.event.ActionEvent, java.lang.String)
	 */
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) 
	{
		if (log.isDebugEnabled()) {
			log.debug("Finish 'PopupHipCliente' method");
		}
		if (accion.equals(this.POPUP_CLIENTES)) 
		{
			this.busquedaHIPClienteSearchAction.verificarRegistro(event);
			if (this.busquedaHIPClienteSearchAction.isSeleccionoRegistro()) 
			{
				Map clienteHipMap = (Map) this.busquedaHIPClienteSearchAction.getBeanRegistroSeleccionado();
				ConsultaZONDirectorioVentasForm f = (ConsultaZONDirectorioVentasForm) this.formBusqueda;
				f.setCodigoClienteBuscar(((String) clienteHipMap.get("codigoCliente")));
				
				String apellido1 = clienteHipMap.get("apellido1") == null ? ""
						: (String) clienteHipMap.get("apellido1");
				
				String apellido2 = clienteHipMap.get("apellido2") == null ? ""
						: (String) clienteHipMap.get("apellido2");
				
				String nombre1 = clienteHipMap.get("nombre1") == null ? ""
						: (String) clienteHipMap.get("nombre1");
				
				String nombre2 = clienteHipMap.get("nombre2") == null ? ""
						: (String) clienteHipMap.get("nombre2");
				
				String numeroDocumento = (String) clienteHipMap.get("numeroDocumento");
				
				f.setNombreCliente(nombre1 +" "+nombre2+" "+apellido1+" "+ apellido2);
				f.setNumeroDocIdentidadBuscar(numeroDocumento);
				
				this.busquedaHIPClienteSearchAction.setBeanRegistroSeleccionado(null);
			}
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
		this.mostrarPopUpCliente = false;
		this.busquedaHIPClienteSearchAction.setBeanRegistroSeleccionado(null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setInvocarPopup(java.lang.String)
	 */
	@Override
	protected void setInvocarPopup(String accion) {
		this.mostrarProcesoBatch = false;

		if (accion.equals(this.POPUP_CLIENTES)) {
			this.mostrarPopUpCliente = true;
			
			BusquedaHIPClienteSearchForm searchForm = (BusquedaHIPClienteSearchForm)this.busquedaHIPClienteSearchAction.getFormBusqueda();
			ConsultaZONDirectorioVentasForm f = (ConsultaZONDirectorioVentasForm) this.formBusqueda;
			searchForm.setOidIdioma(f.getOidIdioma());
			this.busquedaHIPClienteSearchAction.setFormBusqueda(searchForm);
			
			try {
				this.busquedaHIPClienteSearchAction.cargarVista();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaZONDirectorioVentasForm form = new ConsultaZONDirectorioVentasForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {

		ConsultaZONDirectorioVentasForm f = (ConsultaZONDirectorioVentasForm) this.formBusqueda;
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		f.setCodigoPais(pais.getCodigo());
		f.setResumenDetalle("RES");
		
		/* obteniendo valores */
		AjaxService ajaxService = (AjaxService) this.getBean("ajaxService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoZONDirectorioService service = (MantenimientoZONDirectorioService) this.getBean("spusicc.mantenimientoZonDirectorioService");

//		this.siccZonManCargList = service.getTipoCargoList();
		LabelValue[] resultado = ajax.getCargosByFlagFuturas("0");
		
		if(resultado.length > 0){
			this.siccZonManCargList = new ArrayList();
			for (LabelValue obj : resultado) {
				Base aux = new Base();
				aux.setCodigo(obj.getValue());
				aux.setDescripcion(obj.getLabel());
				this.siccZonManCargList.add(aux);
			}
		}
		
		this.siccRegionList = ajaxService.getRegionesAllDirectorioMantenimientoZON(
				pais.getCodigo(), pais.getCodigoConexionExterna(), Constants.NUMERO_CERO, "ASD");
		this.siccZonaList = null;

		// session.removeAttribute("datosZONConsultora");
		// session.removeAttribute("datosZONLicencia");
		// session.removeAttribute("consultaList");

		LabelValue[] estadoList = null;
		estadoList = new LabelValue[4];
		estadoList[0] = new LabelValue("ACTIVA", "A");
		estadoList[1] = new LabelValue("INACTIVA", "I");
		estadoList[2] = new LabelValue("INACTIVA TEMPORAL", "IT");
		estadoList[3] = new LabelValue("NO ASIGNADA", "NA");

		this.siccZonEstadosList = estadoList;

		// Recuperamos el idioma
		IdiomaService idiomaService = (IdiomaService) getBean("idiomaService");
		String s = mPantallaPrincipalBean.getCurrentIdioma().getCodigoISO();
		Idioma idioma = idiomaService.getIdiomaByCodigoISO(s);

		// Longitud del campo consultora
		ClienteUAGenerarService clienteService2 = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");
		this.longitudCampoClientes = clienteService2.getTamanhoNumeroCliente(pais.getCodigo());

		Map params = new HashMap();
		params.put("indicadorActivo", Constants.UNO);
		params.put("codigoIdiomaIso", idioma.getCodigoSiCC());
		params.put("codigoIdioma", idioma.getCodigoISO());

		List perfiles = service.getPerfilesByCriteria(params);
		List roles = service.getRolesByCriteria(params);

		this.siccPerfilList = perfiles;
		this.siccRolList = roles;

		String oidIdiomaIso = reporteService.getOidString("getOidIdiomaByCodigoIdiomaIso", params);
		f.setOidIdioma(oidIdiomaIso);

		this.log.debug("Todo Oki");

		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");

		String validarBarrio = clienteService.getValorModuloxPaisTipoValidacion(pais.getCodigo(), Constants.MAE_VALID_VAL_BARRIO);

		if (StringUtils.isNotBlank(validarBarrio))
			validarBarrio = Constants.ESTADO_ACTIVO;

		this.validarBarrio = validarBarrio;
		
		this.mostrarListaBusqueda = false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setFindAttributes' method");
		}
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		// datosZONConsultora
		MantenimientoZONDirectorioService service = (MantenimientoZONDirectorioService) this
				.getBean("spusicc.mantenimientoZonDirectorioService");
		

		AjaxService ajaxService = (AjaxService) this.getBean("ajaxService");
		ConsultaZONDirectorioVentasForm f = (ConsultaZONDirectorioVentasForm) this.formBusqueda;

		Map criteria = new HashMap();
		criteria.put("codigoRegion", f.getCodigoRegion());
		criteria.put("codigoZona", f.getCodigoZona());
		criteria.put("cargo", f.getCodigoCargo());

		if (f.getTipoCargo().equals("CV")) { // para CargosVigentes
			criteria.put("tipoCargoVigente", '1');
		} else if (f.getTipoCargo().equals("CF")) { // para cargos futuros
			criteria.put("tipoCargoFuturo", '1');
		}
		// criteria.put("estado", f.getEstado());
		// criteria.put("estadoList", (f.getEstadoList() == null ||
		// StringUtils.isEmpty(f.getEstadoList()[0])) ? new ArrayList() : Arrays
		// .asList(f.getEstadoList()));

		criteria.put("email", f.getEmail());
		criteria.put("barrio", f.getBarrio());
		criteria.put("cub", f.getCub());
		criteria.put("codigoRol", f.getCodigoRol());
		criteria.put("codigoPerfil", f.getCodigoPerfil());
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoCliente", f.getCodigoClienteBuscar());

		if (f.getEstadoList().length <= 0 || StringUtils.isEmpty(f.getEstadoList()[0])) {
			criteria.put("estadoList", new ArrayList());
		} else 
		{
			String[] sa = f.getEstadoList();
			ArrayList coleccion = new ArrayList();
			System.out.println("tamaño: " + sa.length);
			for (int i = 0; i < sa.length; i++) 
			{
				if (sa[i].equals("A")) {
					coleccion.add("ACTIVA");
				} else if (sa[i].equals("I")) {
					coleccion.add("INACTIVA");
				} else if (sa[i].equals("IT")) {
					coleccion.add("INACTIVA TEMPORAL");
				} else if (sa[i].equals("NA")) {
					coleccion.add("NO ASIGNADA");
				}
			}

			criteria.put("estadoList", coleccion);
		}

		criteria.put("codigoConexionExterna", pais.getCodigoConexionExterna());

		List listDirectorioVentas = service.getConsultarDirectorioVentas(criteria);

		if (listDirectorioVentas != null && listDirectorioVentas.size() > 0) 
			this.siccConsultaList = listDirectorioVentas;
		else 
			this.siccConsultaList = null;

		LabelValueCDR[] listaZonas = ajaxService.getZonasAllDirectorioActivas(pais.getCodigo(), pais.getCodigoConexionExterna(),
				new String[] { f.getCodigoRegion() }, Constants.NUMERO_CERO);
		LabelValueCDR[] listaZonasFinal = null;

		if (listaZonas != null) 
		{
			listaZonasFinal = new LabelValueCDR[listaZonas.length + 1];
			listaZonasFinal[0] = new LabelValueCDR();
			System.arraycopy(listaZonas, 0, listaZonasFinal, 1, listaZonas.length);
		}
		
		int flag = 0;

		for(int i = 0; i < listaZonasFinal.length; i++)
		{
			LabelValueCDR aux = listaZonasFinal[i];
			if(StringUtils.isBlank(aux.getLabel()))
			{
				flag = 1;
			}
		}
		
		if(flag == 0)
			this.siccZonaList = listaZonasFinal;
		
		this.siccRegionList = ajaxService.getRegionesAllDirectorioMantenimientoZON(
				pais.getCodigo(), pais.getCodigoConexionExterna(),
				Constants.NUMERO_CERO, "ASD");
		this.idCargo = f.getCodigoCargo();
		this.zonIndiResuDeta = f.getResumenDetalle();
		
		return listDirectorioVentas;
	}

	/**
	 * Obtiene lista de Zonas por Region
	 * 
	 * @param val
	 */

	private List getListDirectorioVentas(List listDirectorioVentas) {
		List listResult = new ArrayList();
		Iterator it = listDirectorioVentas.iterator();
		while (it.hasNext()) {
			Map map = (Map) it.next();
			if (Constants.NUMERO_UNO.equals((String) map.get("verDirectorio")))
				listResult.add(map);
		}
		return listResult;
	}

	/**
	 * @param val
	 */
	public void cambiaZonasByRegion(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("cambiaZonas...");
		}
		String valor = (String) val.getNewValue();
		if (StringUtils.isNotBlank(valor) || StringUtils.isNotEmpty(valor)) {
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			ConsultaZONDirectorioVentasForm form = (ConsultaZONDirectorioVentasForm) this.formBusqueda;
			form.setCodigoRegion(valor);
			String[] valores = new String[1];
			valores[0] = valor;

			this.setSiccZonaList(ajaxService.getZonasAllDirectorioActivas(this
					.getmPantallaPrincipalBean().getCurrentCountry()
					.getCodigo(), form.getCodigoPeriodo(), valores, "0"));

		}else
			this.setSiccZonaList(null);
	}
	
	/**
	 * 
	 */
	public void searchConsultoraOnEnter()
	{
		ConsultaZONDirectorioVentasForm f = (ConsultaZONDirectorioVentasForm) this.formBusqueda;
		if(StringUtils.isNotBlank(f.getCodigoClienteBuscar()))
		{
			f.setCodigoClienteBuscar(StringUtils.leftPad(f.getCodigoClienteBuscar(), this.longitudCampoClientes, "0"));
			seteaCodigoConsultora(f.getCodigoClienteBuscar());
		}		
	}
	
	/**
	 * 
	 */
	public void seteaConsultora()
	{
		ConsultaZONDirectorioVentasForm f = (ConsultaZONDirectorioVentasForm) this.formBusqueda;
		if(StringUtils.isNotBlank(f.getCodigoClienteBuscar()))
		{
			if(f.getCodigoClienteBuscar().length() == this.longitudCampoClientes)
				seteaCodigoConsultora(f.getCodigoClienteBuscar());
			else
			{
				f.setNombreCliente("");
				f.setNumeroDocIdentidadBuscar("");
			}
		}
	}
	
	/**
	 * @param valor
	 */
	public void seteaCodigoConsultora(String valor)
	{
		AjaxService ajax =(AjaxService)getBean("ajaxService");
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		ConsultaZONDirectorioVentasForm f = (ConsultaZONDirectorioVentasForm) this.formBusqueda;
		String resultado = ajax.obtenerDatosConsultora(pais.getCodigo(), pais.getCodigoConexionExterna(), valor);
		
		String[] datoOperacion = resultado.split("_");

		if(resultado.equals("1"))
		{
			f.setNombreCliente("No se encuentra");
			f.setNumeroDocIdentidadBuscar("No se encuentra");
		}else{
			f.setNombreCliente(datoOperacion[0]);
			f.setNumeroDocIdentidadBuscar(datoOperacion[1]);
		}
		
	}
	
	public void loadCargosByFlagFuturas(ValueChangeEvent event)
	{
		String valor = (String) event.getNewValue();
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		String flagFuturas = "";
		
		if(StringUtils.isNotBlank(valor)){
			
			if(valor.equals("TC")){
				flagFuturas = "2";
			}else if(valor.equals("CF")){
				flagFuturas = "1";
			}else if (valor.equals("CV")){
				flagFuturas = "0";
			}

			LabelValue[] resultado = ajax.getCargosByFlagFuturas(flagFuturas);
			
			if(resultado.length > 0){
				this.siccZonManCargList = new ArrayList();
				for (LabelValue obj : resultado) {
					Base aux = new Base();
					aux.setCodigo(obj.getValue());
					aux.setDescripcion(obj.getLabel());
					this.siccZonManCargList.add(aux);
				}
			}
		}
	}
	
	/**
	 * @return
	 */
	public boolean isMostrarPopUpCliente() {
		return mostrarPopUpCliente;
	}

	/**
	 * @param mostrarPopUpCliente
	 */
	public void setMostrarPopUpCliente(boolean mostrarPopUpCliente) {
		this.mostrarPopUpCliente = mostrarPopUpCliente;
	}


	/**
	 * @return
	 */
	public static String getPopupClientes() {
		return POPUP_CLIENTES;
	}

	/**
	 * @return
	 */
	public int getLongitudCampoClientes() {
		return longitudCampoClientes;
	}

	/**
	 * @param longitudCampoClientes
	 */
	public void setLongitudCampoClientes(int longitudCampoClientes) {
		this.longitudCampoClientes = longitudCampoClientes;
	}

	/**
	 * @return
	 */
	public String getValidarBarrio() {
		return validarBarrio;
	}

	/**
	 * @param validarBarrio
	 */
	public void setValidarBarrio(String validarBarrio) {
		this.validarBarrio = validarBarrio;
	}

	/**
	 * @return
	 */
	public List getSiccZonManCargList() {
		return siccZonManCargList;
	}

	/**
	 * @param siccZonManCargList
	 */
	public void setSiccZonManCargList(List siccZonManCargList) {
		this.siccZonManCargList = siccZonManCargList;
	}

	/**
	 * @return
	 */
	public String getIdCargo() {
		return idCargo;
	}

	/**
	 * @param idCargo
	 */
	public void setIdCargo(String idCargo) {
		this.idCargo = idCargo;
	}

	/**
	 * @return
	 */
	public List getSiccManCargList() {
		return siccManCargList;
	}

	/**
	 * @param siccManCargList
	 */
	public void setSiccManCargList(List siccManCargList) {
		this.siccManCargList = siccManCargList;
	}

	/**
	 * @return
	 */
	public String getZonIndiResuDeta() {
		return zonIndiResuDeta;
	}

	/**
	 * @param zonIndiResuDeta
	 */
	public void setZonIndiResuDeta(String zonIndiResuDeta) {
		this.zonIndiResuDeta = zonIndiResuDeta;
	}

	/**
	 * @return
	 */
	public List getSiccConsultaList() {
		return siccConsultaList;
	}

	/**
	 * @param siccConsultaList
	 */
	public void setSiccConsultaList(List siccConsultaList) {
		this.siccConsultaList = siccConsultaList;
	}

	/**
	 * @return
	 */
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 */
	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return
	 */
	public LabelValueCDR[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 */
	public void setSiccZonaList(LabelValueCDR[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return
	 */
	public List getSiccNovedadList() {
		return siccNovedadList;
	}

	/**
	 * @param siccNovedadList
	 */
	public void setSiccNovedadList(List siccNovedadList) {
		this.siccNovedadList = siccNovedadList;
	}

	/**
	 * @return
	 */
	public List getSiccRolList() {
		return siccRolList;
	}

	/**
	 * @param siccRolList
	 */
	public void setSiccRolList(List siccRolList) {
		this.siccRolList = siccRolList;
	}

	/**
	 * @return
	 */
	public List getSiccPerfilList() {
		return siccPerfilList;
	}

	/**
	 * @param siccPerfilList
	 */
	public void setSiccPerfilList(List siccPerfilList) {
		this.siccPerfilList = siccPerfilList;
	}
	
	/**
	 * @return
	 */
	public LabelValue[] getSiccZonEstadosList() {
		return siccZonEstadosList;
	}

	/**
	 * @param siccZonEstadosList
	 */
	public void setSiccZonEstadosList(LabelValue[] siccZonEstadosList) {
		this.siccZonEstadosList = siccZonEstadosList;
	}

	/**
	 * @return the busquedaHIPClienteSearchAction
	 */
	public BusquedaHIPClienteSearchAction getBusquedaHIPClienteSearchAction() {
		return busquedaHIPClienteSearchAction;
	}

	/**
	 * @param busquedaHIPClienteSearchAction the busquedaHIPClienteSearchAction to set
	 */
	public void setBusquedaHIPClienteSearchAction(
			BusquedaHIPClienteSearchAction busquedaHIPClienteSearchAction) {
		this.busquedaHIPClienteSearchAction = busquedaHIPClienteSearchAction;
	}

}