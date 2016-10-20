
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

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.scsicc.model.ConsultaHIPDatosCliente;
import biz.belcorp.ssicc.service.scsicc.ConsultaHIPDatosClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.form.ConsultaHIPHistoriaBloqueoForm;

/**
 * <p>
 * <a href="ConsultaHIPHistoriaBloqueoAction.java.html"> <i>View Source </i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:">efernandezo</a>
 * 
 */

@ManagedBean  
@SessionScoped
public class ConsultaHIPHistoriaBloqueoAction extends BasePopupAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5508026776450051186L;
	
	@ManagedProperty(value="#{consultaHIPDatosClienteAction}")
	private ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction;
	
	private List historiaBloqueoList = new ArrayList();

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaHIPHistoriaBloqueoForm consultaHIPHistoriaBloqueoForm = new ConsultaHIPHistoriaBloqueoForm();
		return consultaHIPHistoriaBloqueoForm;
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
			log.debug("Entering 'ConsultaHIPHistoriaBloqueoAction - setViewAtributes' method");
		}
				
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		ConsultaHIPDatosClienteService consultaHIPDatosClienteService = (ConsultaHIPDatosClienteService) 
				this.getBeanService("scsicc.consultaHIPDatosClienteService");
						
		ConsultaHIPHistoriaBloqueoForm f = (ConsultaHIPHistoriaBloqueoForm)this.formBusqueda;
		
		ConsultaHIPDatosCliente dtoDatosCliente = this.consultaHIPDatosClienteAction.getHipDtoDatosCliente();
		
		Map criteria = new HashMap();
		criteria.put("codCliente", dtoDatosCliente.getCodigoCliente());
		criteria.put("nomCliente", dtoDatosCliente.getNombreCompleto());
		criteria.put("pais", dtoDatosCliente.getCodigoPais());
		criteria.put("marca", dtoDatosCliente.getCodigoMarca());
		criteria.put("canal", dtoDatosCliente.getCodigoCanal());
		criteria.put("codRegion", dtoDatosCliente.getCodigoRegion());
		criteria.put("desRegion", dtoDatosCliente.getDescripcionRegion());		
		criteria.put("codZona", dtoDatosCliente.getCodigoZona());
		criteria.put("desZona", dtoDatosCliente.getDescripcionZona());
		criteria.put("codSeccion", dtoDatosCliente.getCodigoSeccion());
		criteria.put("desSeccion", dtoDatosCliente.getDescripcionSeccion());
		criteria.put("codTerritorio", dtoDatosCliente.getCodigoTerritorio());
		criteria.put("oidCliente", dtoDatosCliente.getOidCliente());
		criteria.put("oidIdioma", dtoDatosCliente.getOidIdioma());
		
		f.setCodConsultora((String) criteria.get("codCliente"));
		f.setNomConsultora((String) criteria.get("nomCliente"));
		f.setDesRegZonTerri((String) criteria.get("desRegion")+ "/" +criteria.get("desZona")+ "/" +criteria.get("codTerritorio"));
		
		List ListaHistoriaBloqueo= consultaHIPDatosClienteService.getHistoriaBloqueoList(criteria);
		
		this.setHistoriaBloqueoList(ListaHistoriaBloqueo);
				
		f.setSelectedItems(f.getSelectedItemsVacio());
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {		
		return this.getHistoriaBloqueoList();
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
	 * @return the historiaBloqueoList
	 */
	public List getHistoriaBloqueoList() {
		return historiaBloqueoList;
	}

	/**
	 * @param historiaBloqueoList the historiaBloqueoList to set
	 */
	public void setHistoriaBloqueoList(List historiaBloqueoList) {
		this.historiaBloqueoList = historiaBloqueoList;
	}
			
}


