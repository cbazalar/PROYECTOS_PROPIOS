package biz.belcorp.ssicc.web.scsicc.hip.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import biz.belcorp.ssicc.dao.scsicc.model.ConsultaHIPDatosCliente;
import biz.belcorp.ssicc.service.scsicc.ConsultaHIPDatosClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.form.ConsultaHIPSegurosForm;

/**
 * The Class ConsultaHIPMetasAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 03/02/2014
 */
@ManagedBean  
@SessionScoped
public class ConsultaHIPSegurosAction extends BasePopupAbstractAction {
	
	private static final long serialVersionUID = 1L;
	@ManagedProperty(value="#{consultaHIPDatosClienteAction}")
	private ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction;
	

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaHIPSegurosForm form = new ConsultaHIPSegurosForm();
		return form;
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
		if(log.isDebugEnabled()){
			log.debug("Entro a setViewAttributes");
		}
		
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");
		ConsultaHIPSegurosForm f = (ConsultaHIPSegurosForm) this.formBusqueda;
		
		ConsultaHIPDatosCliente dtoDatosCliente = this.consultaHIPDatosClienteAction.getHipDtoDatosCliente();
		
		f.setCodConsultora(dtoDatosCliente.getCodigoCliente());
		f.setNomConsultora(dtoDatosCliente.getNombreCompleto());
		f.setDocumentoIdentidad(dtoDatosCliente.getNumeroDocIdentidad());
		f.setDesRegZonTerri(dtoDatosCliente.getDescripcionRegion() + " / " + dtoDatosCliente.getDescripcionZona() 
				+ " / " + dtoDatosCliente.getDescripcionSeccion()+ " / " + dtoDatosCliente.getCodigoTerritorio());
				
	}
	
	@Override
	protected List setFindAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("Entro a setFindAttributes");
		}

		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");
		ConsultaHIPSegurosForm f = (ConsultaHIPSegurosForm) this.formBusqueda;
		
		ConsultaHIPDatosCliente dtoDatosCliente = this.consultaHIPDatosClienteAction.getHipDtoDatosCliente();
		
		//recuperamos el saldo a pagar para el proximo pedido
		Map criteria = new HashMap();
		criteria.put("codigoPais", dtoDatosCliente.getCodigoPais());
		criteria.put("codigoMarca", dtoDatosCliente.getCodigoMarca());
		criteria.put("codigoCanal", dtoDatosCliente.getCodigoCanal());
		criteria.put("codigoRegion", dtoDatosCliente.getCodigoRegion());
		criteria.put("codigoZona", dtoDatosCliente.getCodigoZona());
		criteria.put("codigoCliente", dtoDatosCliente.getCodigoCliente());
		criteria.put("oidCliente", dtoDatosCliente.getOidCliente());

		Map mapDatosPoliza = service.getDatosPoliza(criteria);
		
        f.setIndicadorBasparampais(service.getIndicadorBasparampais(criteria));
	    	
		//recuperamos los datos de la Poliza
		if(mapDatosPoliza!=null) {
			f.setAseguradora((String)mapDatosPoliza.get("aseguradora"));
			f.setNumeroPoliza((String)mapDatosPoliza.get("numeroPoliza"));
			f.setIndicadorOrigen((String)mapDatosPoliza.get("indicadorOrigen"));
			f.setPeriodoActivacion((String)mapDatosPoliza.get("periodoActivacion"));
			f.setFechaActivacion((String)mapDatosPoliza.get("fechaActivacion"));
			f.setEstadoPoliza((String)mapDatosPoliza.get("estadoPoliza"));
			f.setPeriodoCancelacion((String)mapDatosPoliza.get("periodoCancelacion"));
			f.setFechaCancelacion((String)mapDatosPoliza.get("fechaCancelacion"));
			f.setMotivoCancelacion((String)mapDatosPoliza.get("motivoCancelacion"));
			f.setFechaInicioCobertura((String)mapDatosPoliza.get("fechaInicioCobertura"));
			f.setFechaFinCobertura((String)mapDatosPoliza.get("fechaFinCobertura"));
		} else {
			f.setAseguradora("");
			f.setNumeroPoliza("");
			f.setIndicadorOrigen("");
			f.setPeriodoActivacion("");
			f.setFechaActivacion("");
			f.setEstadoPoliza("");
			f.setPeriodoCancelacion("");
			f.setFechaCancelacion("");
			f.setMotivoCancelacion("");
			f.setFechaInicioCobertura("");
			f.setFechaFinCobertura("");
		}

		List listHistorialCobrosSeguro = service.getHistorialCobrosSeguro(criteria);
		
		return listHistorialCobrosSeguro;
	}
	
	public ConsultaHIPDatosClienteAction getConsultaHIPDatosClienteAction() {
		return consultaHIPDatosClienteAction;
	}

	public void setConsultaHIPDatosClienteAction(
			ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction) {
		this.consultaHIPDatosClienteAction = consultaHIPDatosClienteAction;
	}
}
