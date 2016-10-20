
package biz.belcorp.ssicc.web.scsicc.hip.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.scsicc.model.ConsultaHIPDatosCliente;
import biz.belcorp.ssicc.dao.scsicc.model.CronogramaActividades;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.scsicc.ConsultaHIPDatosClienteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.form.ConsultaHIPCronogramaActividadesForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ConsultaHIPCronogramaActividadesAction"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */

@ManagedBean
@SessionScoped
public class ConsultaHIPCronogramaActividadesAction extends BasePopupAbstractAction {

	private static final long serialVersionUID = 4705375821905094995L;
	
	private List hipCronogramaActividadesList = new ArrayList();
	
	@ManagedProperty(value="#{consultaHIPDatosClienteAction}")
	private ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction;

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaHIPCronogramaActividadesForm consultaHIPCronogramaActividadesForm = new ConsultaHIPCronogramaActividadesForm();
		return consultaHIPCronogramaActividadesForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
            log.debug("Entering 'ConsultaHIPCronogramaActividadesAction - setFindAttributes' method");
        }
		
		return this.getHipCronogramaActividadesList();
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

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
            log.debug("Entering 'ConsultaHIPCronogramaActividadesAction - setViewAtributes' method");
        }

    	ConsultaHIPDatosClienteService consultaHIPDatosClienteService = (ConsultaHIPDatosClienteService)this.getBeanService("scsicc.consultaHIPDatosClienteService");
	    ConsultaHIPCronogramaActividadesForm f = (ConsultaHIPCronogramaActividadesForm)this.formBusqueda;
	    ConsultaHIPDatosCliente dtoDatosCliente = this.consultaHIPDatosClienteAction.getHipDtoDatosCliente();
	    
		Map criteria = new HashMap();
		criteria.put("codConsultora", dtoDatosCliente.getCodigoCliente());
		criteria.put("nomConsultora", dtoDatosCliente.getNombreCompleto());
		criteria.put("pais", dtoDatosCliente.getCodigoPais());
		criteria.put("marca", dtoDatosCliente.getCodigoMarca());
		criteria.put("canal", dtoDatosCliente.getCodigoCanal());
		criteria.put("codRegion", dtoDatosCliente.getCodigoRegion());
		criteria.put("desRegion",dtoDatosCliente.getDescripcionRegion());		
		criteria.put("codZona", dtoDatosCliente.getCodigoZona());
		criteria.put("desZona", dtoDatosCliente.getDescripcionZona());
		criteria.put("codSeccion", dtoDatosCliente.getCodigoSeccion());
		criteria.put("desSeccion", dtoDatosCliente.getDescripcionSeccion());		
		criteria.put("codTerritorio", dtoDatosCliente.getCodigoTerritorio());
		
		//Obtiene la campaña de proceso actual
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
		criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
		MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");        
		PedidoControlFacturacion controlFacturacion = serviceFact.getControlFacturacionById(criteria);
		String codigoPeriodo = controlFacturacion.getCodigoPeriodo();      			       	
		
		criteria.put("codigoPeriodo",codigoPeriodo );
		
		f.setCodConsultora((String) criteria.get("codConsultora"));
		f.setNomConsultora((String) criteria.get("nomConsultora"));
		f.setDesRegZonTerri((String) criteria.get("desRegion")+ "/" +criteria.get("desZona")+ "/" +criteria.get("codTerritorio"));

		List ListaCronogramaActividades= consultaHIPDatosClienteService.executaCronogramaActividadesList(criteria);

		CronogramaActividades cronogramaActividades = new CronogramaActividades();
		
		if (ListaCronogramaActividades.size()>0 ){
				int i = 0;
				cronogramaActividades = (CronogramaActividades)ListaCronogramaActividades.get(i);
				f.setDesActividad(cronogramaActividades.getDesActividad());
				f.setFecPeriodoAnterior(cronogramaActividades.getFecPeriodoAnterior());
				f.setFecPeriodoActual(cronogramaActividades.getFecPeriodoActual());
				f.setFecPeriodoDespues(cronogramaActividades.getFecPeriodoDespues());
				f.setFecPeriodoDespues2(cronogramaActividades.getFecPeriodoDespues2());
				
				ListaCronogramaActividades.remove(i);
		}
		
		this.setHipCronogramaActividadesList(ListaCronogramaActividades);
		
		List ListaGerenteZona= consultaHIPDatosClienteService.getGerenteZonaList(criteria);
		if (ListaGerenteZona.size()>0 ){
			int i = 0;
			Map cambioMap  = new HashMap();
			cambioMap  = (HashMap) ListaGerenteZona.get(i);

			f.setApellido1((String) cambioMap.get("apellido1"));
			f.setApellido2((String) cambioMap.get("apellido2"));
			f.setNombre1((String) cambioMap.get("nombre1"));
			f.setNombre2((String) cambioMap.get("nombre2"));
			f.setTelefonoCasa((String) cambioMap.get("telefonoCasa"));
			f.setEmail((String) cambioMap.get("email"));
			f.setCelular((String) cambioMap.get("celular"));
		}

		List ListaGerenteRegion= consultaHIPDatosClienteService.getGerenteRegionList(criteria);
		if (ListaGerenteRegion.size()>0 ){
			int i = 0;
			Map cambioMap  = new HashMap();
			cambioMap  = (HashMap) ListaGerenteRegion.get(i);

			f.setApellido1Reg((String) cambioMap.get("apellido1"));
			f.setApellido2Reg((String) cambioMap.get("apellido2"));
			f.setNombre1Reg((String) cambioMap.get("nombre1"));
			f.setNombre2Reg((String) cambioMap.get("nombre2"));
			f.setTelefonoCasaReg((String) cambioMap.get("telefonoCasa"));
			f.setEmailReg((String) cambioMap.get("email"));
			f.setCelularReg((String) cambioMap.get("celular"));
		}

		List ListaLiderSeccion = consultaHIPDatosClienteService.getLiderSeccionList(criteria);
		if (ListaLiderSeccion.size()>0 ){
			int i = 0;
			Map cambioMap  = new HashMap();
			cambioMap  = (HashMap) ListaLiderSeccion.get(i);

			f.setApellido1Lid((String) cambioMap.get("apellido1"));
			f.setApellido2Lid((String) cambioMap.get("apellido2"));
			f.setNombre1Lid((String) cambioMap.get("nombre1"));
			f.setNombre2Lid((String) cambioMap.get("nombre2"));
			f.setTelefonoCasaLid((String) cambioMap.get("telefonoCasa"));
			f.setEmailLid((String) cambioMap.get("email"));
			f.setCelularLid((String) cambioMap.get("celular"));
		}
		
	}

	public List getHipCronogramaActividadesList() {
		return hipCronogramaActividadesList;
	}

	public void setHipCronogramaActividadesList(List hipCronogramaActividadesList) {
		this.hipCronogramaActividadesList = hipCronogramaActividadesList;
	}

	public ConsultaHIPDatosClienteAction getConsultaHIPDatosClienteAction() {
		return consultaHIPDatosClienteAction;
	}

	public void setConsultaHIPDatosClienteAction(
			ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction) {
		this.consultaHIPDatosClienteAction = consultaHIPDatosClienteAction;
	}
	
	

	

}


