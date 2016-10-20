package biz.belcorp.ssicc.web.scsicc.hip.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.scsicc.model.ConsultaHIPDatosCliente;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ConsultaHIPDatosClienteService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.form.ConsultaHIPPedidosForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ConsultaHIPPedidosAction"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */

@ManagedBean
@SessionScoped
public class ConsultaHIPPedidosAction extends BasePopupAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2225737262140519807L;

	private static final String ACCION_ULTIMOS_PEDIDOS = "ULTIMOSPEDIDOS";

	private List hipPedidosConsultoraList = new ArrayList();
	private String STO_IND_BOLELEC;
	private boolean indBoletaElect;

	@ManagedProperty(value = "#{consultaHIPDatosClienteAction}")
	private ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction;

	private List hipDetallePedidosConsultoraList;
	private List stoListaDetallePedidoGP1_GP2;
	
	private String oidSoliCabecera;
	private String lineaDefecto;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction#
	 * devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaHIPPedidosForm consultaHIPPedidosForm = new ConsultaHIPPedidosForm();
		return consultaHIPPedidosForm;
	}
	
	/**
	 * Metodo que se ejecuta luego que se ejecuta el Constructor de la clase
	 */
	@PostConstruct
	public void view() {
		if (log.isDebugEnabled()) {
            log.debug("Entering 'ConsultaHIPCuentaCorrientesAction - view' method");
        }
		Map<String, String> parametros = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();;
		this.parametrosPantalla = new HashMap<String, String>();
		this.parametrosPantalla.putAll(parametros);
		try {
			this.formBusqueda = this.devuelveFormBusqueda();
		}
		catch (Exception e) {
			
		}
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
			log.debug("Entering 'ConsultaHIPPedidosAction - setViewAtributes' method");
		}

		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService) this.getBeanService("scsicc.consultaHIPDatosClienteService");
		ConsultaHIPPedidosForm f = (ConsultaHIPPedidosForm) this.formBusqueda;
		ConsultaHIPDatosCliente dtoDatosCliente = this.consultaHIPDatosClienteAction.getHipDtoDatosCliente();

		Map criteria = new HashMap();
		
		//Seteo la informacion que se trae de la página anterior
		f.setCodConsultora(dtoDatosCliente.getCodigoCliente());
		f.setNomConsultora(dtoDatosCliente.getNombreCompleto());
		f.setDesRegZonTerri(dtoDatosCliente.getDescripcionRegion() + " / " + dtoDatosCliente.getDescripcionZona() 
							+ " / " + dtoDatosCliente.getCodigoTerritorio());
		f.setEstatus(dtoDatosCliente.getStatus());
		f.setCampanaIngreso(dtoDatosCliente.getCampanaIngreso());
		
		f.setCodigoPais(dtoDatosCliente.getCodigoPais());
		f.setCodigoMarca(dtoDatosCliente.getCodigoMarca());
		f.setCodigoCanal(dtoDatosCliente.getCodigoCanal());
		f.setCodigoRegion(dtoDatosCliente.getCodigoRegion());
		
		//Seteo los parametros de la consulta
		criteria.put("codigoCliente", dtoDatosCliente.getCodigoCliente());
		
		//Obtengo las listas a mostrar
		List pedidosConsultora = service.getPedidosConsultora(criteria);
		
		//Si viene de exportacion, la columna tipomovimiento sera visualizado como texto normal
		/*String mostrarLinks = request.getParameter("mostrarLinks");
		if (mostrarLinks != null) 
			f.setMostrarLinks(false);
		else
			f.setMostrarLinks(true);*/
		
		// Cargo a session las listar para ser mostradas por pantalla
		this.setHipPedidosConsultoraList(pedidosConsultora);
		
		f.setPantallaBusquedaActiva(Constants.ESTADO_INACTIVO);
		f.setCodigoVenta(null);
		
		f.setLineaDefecto(Constants.HIP_PEDIDO_FACTURADO_SOLICITADO_LINEA_DEFECTO);
		f.setLineaMaxima(Constants.HIP_PEDIDO_FACTURADO_SOLICITADO_LINEA_MAXIMA);
		f.setLineaDefectoPS(Constants.HIP_PEDIDO_FACTURADO_SOLICITADO_LINEA_DEFECTO_PS);
		f.setLineaMaximaPS(Constants.HIP_PEDIDO_FACTURADO_SOLICITADO_LINEA_MAXIMA_PS);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction#
	 * setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ConsultaHIPPedidosAction - setFindAttributes' method");
		}
		String montoSuperacion = "";
		ConsultaHIPPedidosForm f = (ConsultaHIPPedidosForm) this.formBusqueda;
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService) this.getBeanService("scsicc.consultaHIPDatosClienteService");
		ConsultaHIPDatosCliente dtoDatosCliente = this.consultaHIPDatosClienteAction.getHipDtoDatosCliente();

		// ver si trae valor
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String oidSoliCabecera = externalContext.getRequestParameterMap().get("parametro");
		String accion = externalContext.getRequestParameterMap().get("accion");

		
		//Seteo la informacion que se trae de la página anterior
		f.setCodConsultora(dtoDatosCliente.getCodigoCliente());
		f.setNomConsultora(dtoDatosCliente.getNombreCompleto());
		f.setDesRegZonTerri(dtoDatosCliente.getDescripcionRegion() + " / " + dtoDatosCliente.getDescripcionZona() 
							+ " / " + dtoDatosCliente.getCodigoTerritorio());
		f.setEstatus(dtoDatosCliente.getStatus());
		f.setCampanaIngreso(dtoDatosCliente.getCampanaIngreso());
		
		// ver si trae valor
		if(StringUtils.isNotEmpty(oidSoliCabecera)){
			this.setOidSoliCabecera(oidSoliCabecera);
		}else{
			oidSoliCabecera = this.getOidSoliCabecera();
		}
		
		Map criteria = new HashMap();
		criteria.put("oidSoliCabecera" , oidSoliCabecera);
		criteria.put("codigoVentaLog" , f.getCodigoVentaLog());
		
		montoSuperacion = service.getObtenerMontoPlanSuperacion(criteria);
		
		if (StringUtils.isNotBlank(montoSuperacion)){
			f.setMontoPlansupera(montoSuperacion);	
		}else{
			f.setMontoPlansupera("");
		}
		
		List detallePedidoList = service.getDetallePedido(criteria);
		
		//pintamos informacion del reclamo
		List pedidosList = this.getHipPedidosConsultoraList();

		for(int i=0; i<pedidosList.size(); i++) {
			Map mapPedido = (Map)pedidosList.get(i);
			String oidSoliCabeceraAux = (String)mapPedido.get("oidSoliCabecera");
			
			if (oidSoliCabeceraAux.equals(oidSoliCabecera)) {
				f.setOidSolicCabecera(oidSoliCabecera);
				
				/* INI SA PER-SiCC-2012-0765 */
				f.setGastosAdministrativos(reemplazarNulo(mapPedido.get("gastoAdministrativo")));
				f.setFlete(reemplazarNulo(mapPedido.get("flete")));
				f.setFlexipago(reemplazarNulo(mapPedido.get("flexipago")));
				f.setTotalPedido(reemplazarNulo(mapPedido.get("monto")));
				/* FIN SA PER-SiCC-2012-0765 */

				break;
			}
			
		}	
				
		this.setHipDetallePedidosConsultoraList(detallePedidoList);

		//Obtenemos el valor del parametro STO_IND_BOLELEC
		ProcesoSTOEjecucionValidacionesService serviceSTO = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		criteria.put("codigoPais", this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		criteria.put("codigoParametro", Constants.STO_IND_BOLELEC);
		String valorParametro = serviceSTO.getParametroSTO(criteria);
		
		this.setSTO_IND_BOLELEC(valorParametro);
		
		f.setTipo("GP1");
		criteria.put("codigoCliente",f.getCodConsultora());
		criteria.put("oidConsolidado" , f.getOidSolicCabecera());
		List listaDetallePedidoGP1_GP2 = service.getConsultaDetallePedidoSolicitado(criteria);
		this.setStoListaDetallePedidoGP1_GP2(listaDetallePedidoGP1_GP2);
		
		f.setPantallaBusquedaActiva(Constants.ESTADO_INACTIVO);
		f.setCodigoVenta(null);
		f.setCodigoVentaLog(null);
		
		this.setLineaDefecto(f.getLineaDefecto());
		
		return null;
	}

	/**
	 * @param actionEvent
	 */
	public void calcularPromedioVenta(ActionEvent actionEvent) {
		
		log.debug("calcularPromedioVenta");
		
		ConsultaHIPPedidosForm f = (ConsultaHIPPedidosForm) this.formBusqueda;
		
		f.setValorPromedio("");
		
		if(StringUtils.isBlank(f.getNumeroPedidos()))
		{
			this.addError("Error ", "Ingrese un número mayor a cero");
		}
		else
		{
			double numeroPedidos = Double.parseDouble(f.getNumeroPedidos());
			
			if(numeroPedidos <= 0)
			{
				this.addError("Error ", "Ingrese un número mayor a cero");
			}
			else
			{
				AjaxService ajaxService = (AjaxService)this.getBeanService("ajaxService");
				
				String promedio = ajaxService.getPromedioVenta(f.getCodigoPais(), f.getCodigoMarca(), f.getCodigoCanal(), f.getCodigoRegion(), f.getCodConsultora(), f.getNumeroPedidos());
				f.setValorPromedio(promedio);
			}
		}
		
	}
			
	/**
	 * @param obj
	 * @return
	 */
	private String reemplazarNulo(Object obj) {
		if (obj == null)
			return "";
		else
			return obj.toString();
	}

	/**
	 * @return the hipPedidosConsultoraList
	 */
	public List getHipPedidosConsultoraList() {
		return hipPedidosConsultoraList;
	}

	/**
	 * @param hipPedidosConsultoraList
	 *            the hipPedidosConsultoraList to set
	 */
	public void setHipPedidosConsultoraList(List hipPedidosConsultoraList) {
		this.hipPedidosConsultoraList = hipPedidosConsultoraList;
	}

	/**
	 * @return the sTO_IND_BOLELEC
	 */
	public String getSTO_IND_BOLELEC() {
		return STO_IND_BOLELEC;
	}

	/**
	 * @param sTO_IND_BOLELEC
	 *            the sTO_IND_BOLELEC to set
	 */
	public void setSTO_IND_BOLELEC(String sTO_IND_BOLELEC) {
		STO_IND_BOLELEC = sTO_IND_BOLELEC;
	}

	/**
	 * @return the consultaHIPDatosClienteAction
	 */
	public ConsultaHIPDatosClienteAction getConsultaHIPDatosClienteAction() {
		return consultaHIPDatosClienteAction;
	}

	/**
	 * @param consultaHIPDatosClienteAction
	 *            the consultaHIPDatosClienteAction to set
	 */
	public void setConsultaHIPDatosClienteAction(
			ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction) {
		this.consultaHIPDatosClienteAction = consultaHIPDatosClienteAction;
	}

	/**
	 * @return the indBoletaElect
	 */
	public boolean isIndBoletaElect() {
		return indBoletaElect;
	}

	/**
	 * @param indBoletaElect
	 *            the indBoletaElect to set
	 */
	public void setIndBoletaElect(boolean indBoletaElect) {
		this.indBoletaElect = indBoletaElect;
	}

	/**
	 * @return the hipDetallePedidosConsultoraList
	 */
	public List getHipDetallePedidosConsultoraList() {
		return hipDetallePedidosConsultoraList;
	}

	/**
	 * @param hipDetallePedidosConsultoraList the hipDetallePedidosConsultoraList to set
	 */
	public void setHipDetallePedidosConsultoraList(
			List hipDetallePedidosConsultoraList) {
		this.hipDetallePedidosConsultoraList = hipDetallePedidosConsultoraList;
	}

	/**
	 * @return the oidSoliCabecera
	 */
	public String getOidSoliCabecera() {
		return oidSoliCabecera;
	}

	/**
	 * @param oidSoliCabecera the oidSoliCabecera to set
	 */
	public void setOidSoliCabecera(String oidSoliCabecera) {
		this.oidSoliCabecera = oidSoliCabecera;
	}

	/**
	 * @return the stoListaDetallePedidoGP1_GP2
	 */
	public List getStoListaDetallePedidoGP1_GP2() {
		return stoListaDetallePedidoGP1_GP2;
	}

	/**
	 * @param stoListaDetallePedidoGP1_GP2 the stoListaDetallePedidoGP1_GP2 to set
	 */
	public void setStoListaDetallePedidoGP1_GP2(List stoListaDetallePedidoGP1_GP2) {
		this.stoListaDetallePedidoGP1_GP2 = stoListaDetallePedidoGP1_GP2;
	}

	/**
	 * @return the lineaDefecto
	 */
	public String getLineaDefecto() {
		return lineaDefecto;
	}

	/**
	 * @param lineaDefecto the lineaDefecto to set
	 */
	public void setLineaDefecto(String lineaDefecto) {
		this.lineaDefecto = lineaDefecto;
	}

}
