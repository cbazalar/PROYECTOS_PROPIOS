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

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.scsicc.model.ConsultaHIPDatosCliente;
import biz.belcorp.ssicc.service.scsicc.ConsultaHIPDatosClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.form.ConsultaHIPCdrRechazadosForm;

/**
 * <p>
 * <a href="consultaHIPCdrRechazadosAction"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */

@ManagedBean  
@SessionScoped
public class ConsultaHIPCdrRechazadosAction extends BasePopupAbstractAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3913105027523362990L;
	
	private static final String ACCION_DETALLE_CDRRECHAZADOS = "DETALLECDRRECHAZADOS";
	
	@ManagedProperty(value="#{consultaHIPDatosClienteAction}")
	private ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction;
	
	private List consultaCdrRechazadosList = new ArrayList();
	private List consultaDetalleCdrRechazadosList = new ArrayList();

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaHIPCdrRechazadosForm consultaHIPCdrRechazadosForm = new ConsultaHIPCdrRechazadosForm();
		return consultaHIPCdrRechazadosForm;
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
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ConsultaHIPCdrRechazadosAction - setViewAtributes' method");
		}
		
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService)this.getBeanService("scsicc.consultaHIPDatosClienteService");				
		ConsultaHIPCdrRechazadosForm f = (ConsultaHIPCdrRechazadosForm) this.formBusqueda;
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteria = new HashMap();
		
		ConsultaHIPDatosCliente dtoDatosCliente = this.consultaHIPDatosClienteAction.getHipDtoDatosCliente();
		
		//Seteo la informacion que se trae de la pgina anterior
		f.setCodConsultora(dtoDatosCliente.getCodigoCliente());
		f.setNomConsultora(dtoDatosCliente.getNombreCompleto());
		f.setDesRegZonTerri(dtoDatosCliente.getDescripcionRegion() + " / " + dtoDatosCliente.getDescripcionZona() 
							+ " / " + dtoDatosCliente.getCodigoTerritorio());
		//Seteo los parametros de la consulta
		criteria.put("codigoCliente", dtoDatosCliente.getCodigoCliente());
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("tipoDocumento", Constants.HIP_TIPO_DOCUMENTO);
		//Obtengo las listas a mostrar
		List consultaCDRRechazados = service.getConsultaCdrRechazados(criteria);
		
		// Cargo a session las listar para ser mostradas por pantalla
		this.setConsultaCdrRechazadosList(consultaCDRRechazados);
				
	}

	
	@Override
	protected List setFindAttributes() throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ConsultaHIPCdrRechazadosAction - setFindAttributes' method");
		}
		
		List lista = this.getConsultaCdrRechazadosList();
				
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String accion = externalContext.getRequestParameterMap().get("accion");
		
		log.debug("accion:" + accion);
		
		if(accion!=null){
			
			if(accion.equals(this.ACCION_DETALLE_CDRRECHAZADOS)){
				
				ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService)this.getBeanService("scsicc.consultaHIPDatosClienteService");
				//ConsultaHIPDatosCliente dtoDatosCliente = (ConsultaHIPDatosCliente)session.getAttribute(Constants.HIP_DTO_DATOS_CLIENTE);
				// ver si trae valor				
				String parametro = externalContext.getRequestParameterMap().get("parametro");
				log.debug("id" + parametro);
				
				List listCdrRechazados = this.getConsultaCdrRechazadosList();						
				List detalleCDRList=new ArrayList();
				
				if(parametro!=null){
					  Map map = (Map)listCdrRechazados.get(Integer.parseInt(parametro));
					  //map.put("codigoCliente" , codigoCliente);
					  //PedidoControlFacturacion controlFacturacion=getCodigoPeriodo(session);
					  map.put("tipoDocumentoDetalle", Constants.HIP_TIPO_DOCUMENTO_DETALLE);
					  detalleCDRList = service.getDetalleCdrRechazados(map);
				}
				
				this.setConsultaDetalleCdrRechazadosList(detalleCDRList);
				lista = detalleCDRList;
				
			}
			
		}
				
		return lista;
	}

	/**
	 * @return the consultaHIPDatosClienteAction
	 */
	public ConsultaHIPDatosClienteAction getConsultaHIPDatosClienteAction() {
		return consultaHIPDatosClienteAction;
	}

	/**
	 * @param consultaHIPDatosClienteAction the consultaHIPDatosClienteAction to set
	 */
	public void setConsultaHIPDatosClienteAction(
			ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction) {
		this.consultaHIPDatosClienteAction = consultaHIPDatosClienteAction;
	}

	/**
	 * @return the consultaCdrRechazadosList
	 */
	public List getConsultaCdrRechazadosList() {
		return consultaCdrRechazadosList;
	}

	/**
	 * @param consultaCdrRechazadosList the consultaCdrRechazadosList to set
	 */
	public void setConsultaCdrRechazadosList(List consultaCdrRechazadosList) {
		this.consultaCdrRechazadosList = consultaCdrRechazadosList;
	}

	/**
	 * @return the consultaDetalleCdrRechazadosList
	 */
	public List getConsultaDetalleCdrRechazadosList() {
		return consultaDetalleCdrRechazadosList;
	}

	/**
	 * @param consultaDetalleCdrRechazadosList the consultaDetalleCdrRechazadosList to set
	 */
	public void setConsultaDetalleCdrRechazadosList(
			List consultaDetalleCdrRechazadosList) {
		this.consultaDetalleCdrRechazadosList = consultaDetalleCdrRechazadosList;
	}
	
}
