package biz.belcorp.ssicc.web.scsicc.hip.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import biz.belcorp.ssicc.dao.scsicc.model.ConsultaHIPDatosCliente;
import biz.belcorp.ssicc.service.scsicc.ConsultaHIPDatosClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.form.ConsultaHIPMetasForm;

/**
 * The Class ConsultaHIPMetasAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 03/02/2014
 */
@ManagedBean  
@SessionScoped
public class ConsultaHIPMetasAction extends BasePopupAbstractAction {
	
	private static final long serialVersionUID = 1L;
	@ManagedProperty(value="#{consultaHIPDatosClienteAction}")
	private ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction;
	

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaHIPMetasForm form = new ConsultaHIPMetasForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("Entro a ConsultaHIPEjecutivasAction - setFindAttributes");
		}
		return null;
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
			log.debug("Entro a ConsultaHIPEjecutivasAction - setViewAttributes");
		}
		
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");
		ConsultaHIPMetasForm f = (ConsultaHIPMetasForm) this.formBusqueda;
		
		ConsultaHIPDatosCliente dtoDatosCliente = this.consultaHIPDatosClienteAction.getHipDtoDatosCliente();
		
		f.setCodConsultora(dtoDatosCliente.getCodigoCliente());
		f.setNomConsultora(dtoDatosCliente.getNombreCompleto());
		f.setDesRegZonTerri(dtoDatosCliente.getDescripcionRegion() + " / " + dtoDatosCliente.getDescripcionZona() 
				+ " / " + dtoDatosCliente.getDescripcionSeccion()+ " / " + dtoDatosCliente.getCodigoTerritorio());
		
		//recuperamos el saldo a pagar para el proximo pedido
		Map criteria = new HashMap();
		criteria.put("codigoPais", dtoDatosCliente.getCodigoPais());
		criteria.put("codigoMarca", dtoDatosCliente.getCodigoMarca());
		criteria.put("codigoCanal", dtoDatosCliente.getCodigoCanal());
		criteria.put("codigoRegion", dtoDatosCliente.getCodigoRegion());
		criteria.put("codigoZona", dtoDatosCliente.getCodigoZona());
		criteria.put("codigoCliente", dtoDatosCliente.getCodigoCliente());
		criteria.put("oidCliente", dtoDatosCliente.getOidCliente());

		Map mapDatosMetas = service.getDatosMetas(criteria);
		
		f.setIndicadorBasparampais(service.getIndicadorBasparampais(criteria));
		
		//recuperamos los datos de la Poliza
		if(mapDatosMetas!=null) {
			f.setTipoMeta((String)mapDatosMetas.get("tipoMeta"));
			f.setOrigen((String)mapDatosMetas.get("origen"));
			f.setMontoMeta(Double.parseDouble(mapDatosMetas.get("montoMeta").toString()) );
			f.setPeriodoInicio((String)mapDatosMetas.get("periodoInicio"));
			f.setPeriodoFin((String)mapDatosMetas.get("periodoFin"));
			f.setOportunidadAhorro((String)mapDatosMetas.get("oportunidadAhorro"));
			f.setMontoFaltante(Double.parseDouble(mapDatosMetas.get("montoFaltante").toString()));
			f.setEstadoMeta((String)mapDatosMetas.get("estadoMeta"));
		} else {
			f.setTipoMeta("");
			f.setOrigen("");
			f.setMontoMeta(0.0);
			f.setPeriodoInicio("");
			f.setPeriodoFin("");
			f.setOportunidadAhorro("");
			f.setMontoFaltante(0.0);
			f.setEstadoMeta("");
		}
	}
	
	public void ejecutarReporte(ActionEvent e){
		if(log.isDebugEnabled()){
			log.debug("ejecutarReporte");
		}
	}

	public ConsultaHIPDatosClienteAction getConsultaHIPDatosClienteAction() {
		return consultaHIPDatosClienteAction;
	}

	public void setConsultaHIPDatosClienteAction(
			ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction) {
		this.consultaHIPDatosClienteAction = consultaHIPDatosClienteAction;
	}
}
