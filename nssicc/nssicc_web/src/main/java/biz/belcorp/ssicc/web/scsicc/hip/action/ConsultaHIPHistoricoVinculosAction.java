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
import biz.belcorp.ssicc.web.scsicc.hip.form.ConsultaHIPHistoricoVinculosForm;
import biz.belcorp.ssicc.web.util.StringUtil;


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
public class ConsultaHIPHistoricoVinculosAction extends BasePopupAbstractAction{
	
	private List hipTiposVinculosList;
	private List hipHistoricoVinculosList;
	
	@ManagedProperty(value="#{consultaHIPDatosClienteAction}")
	private ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction;
	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaHIPHistoricoVinculosForm consultaHIPHistoricoVinculosForm = new ConsultaHIPHistoricoVinculosForm();
		return consultaHIPHistoricoVinculosForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
            log.debug("Entering 'ConsultaHIPHistoricoVinculosAction - setFindAttributes' method");
        }
			
		return getHipHistoricoVinculosList();
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
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService) getBeanService("scsicc.consultaHIPDatosClienteService");
		ConsultaHIPHistoricoVinculosForm f = (ConsultaHIPHistoricoVinculosForm) this.formBusqueda;
		
		ConsultaHIPDatosCliente dtoDatosCliente = consultaHIPDatosClienteAction.getHipDtoDatosCliente();
		Map criteria = new HashMap();
		criteria.put("oidIdioma" , dtoDatosCliente.getOidIdioma());
		criteria.put("oidCliente" , dtoDatosCliente.getOidCliente());
		criteria.put("desRegion", dtoDatosCliente.getDescripcionRegion());
		criteria.put("desZona", dtoDatosCliente.getDescripcionZona());
		criteria.put("codTerritorio", dtoDatosCliente.getCodigoTerritorio());
		
		f.setCodConsultora(dtoDatosCliente.getCodigoCliente());
		f.setNomConsultora(dtoDatosCliente.getNombreCompleto());
		f.setDesRegZonTerri(criteria.get("desRegion") + " / " +  criteria.get("desZona") + " / " + criteria.get("codTerritorio"));
		f.setOidTiposVinculos(null);

		//recuperamos los tipos vinculos
		
		List listTiposVinculos = service.getTiposVinculos(criteria);
		
		setHipTiposVinculosList(listTiposVinculos);
		//setHipHistoricoVinculosList(new ArrayList());
		
		//request.getSession().setAttribute(Constants.HIP_TIPOS_VINCULOS_LIST, listTiposVinculos);
		//request.getSession().setAttribute(Constants.HIP_HISTORICO_VINCULOS_LIST, new ArrayList());
		
	}
	
	public void search(ActionEvent actionEvent) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search AJAX' method");
		}
		
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService) getBeanService("scsicc.consultaHIPDatosClienteService");
		ConsultaHIPDatosCliente dtoDatosCliente = consultaHIPDatosClienteAction.getHipDtoDatosCliente();
		ConsultaHIPHistoricoVinculosForm f = (ConsultaHIPHistoricoVinculosForm) this.formBusqueda;
		
		Map criteria = new HashMap();
		criteria.put("oidIdioma" , dtoDatosCliente.getOidIdioma());
		criteria.put("oidCliente" , dtoDatosCliente.getOidCliente());
		
		
		if(f.getOidTiposVinculos()!=null) {
			String condicion = StringUtil.obtieneListaCodigos(f.getOidTiposVinculos(), "x", "'");
			
			if(!condicion.equals(""))
				criteria.put("oidTiposVinculos" , f.getOidTiposVinculos());
		}	
			
		List listHistoricoVinculos = service.getHistoricoVinculos(criteria);
		setHipHistoricoVinculosList(listHistoricoVinculos);
		
		this.find();
		
	}	

	public List getHipTiposVinculosList() {
		return hipTiposVinculosList;
	}

	public void setHipTiposVinculosList(List hipTiposVinculosList) {
		this.hipTiposVinculosList = hipTiposVinculosList;
	}

	public List getHipHistoricoVinculosList() {
		return hipHistoricoVinculosList;
	}

	public void setHipHistoricoVinculosList(List hipHistoricoVinculosList) {
		this.hipHistoricoVinculosList = hipHistoricoVinculosList;
	}

	public ConsultaHIPDatosClienteAction getConsultaHIPDatosClienteAction() {
		return consultaHIPDatosClienteAction;
	}

	public void setConsultaHIPDatosClienteAction(
			ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction) {
		this.consultaHIPDatosClienteAction = consultaHIPDatosClienteAction;
	}

	
	
}
