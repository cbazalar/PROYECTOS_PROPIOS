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

import biz.belcorp.ssicc.dao.scsicc.model.ConsultaHIPDatosCliente;
import biz.belcorp.ssicc.service.scsicc.ConsultaHIPDatosClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.form.ConsultaHIPResponsablesSeccionForm;


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
public class ConsultaHIPResponsablesSeccionAction extends BasePopupAbstractAction {

	private static final long serialVersionUID = 4170093197763808477L;
	
	private List hipResponsableSeleccionList = new ArrayList();
	
	@ManagedProperty(value="#{consultaHIPDatosClienteAction}")
	private ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction;

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaHIPResponsablesSeccionForm consultaHIPResponsablesSeccionForm = new ConsultaHIPResponsablesSeccionForm();
		return consultaHIPResponsablesSeccionForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
            log.debug("Entering 'ConsultaHIPResponsablesSeccionAction - setFindAttributes' method");
        }
		
		return this.getHipResponsableSeleccionList();
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
            log.debug("Entering 'ConsultaHIPResponsablesSeccionAction - setViewAtributes' method");
        }

		 ConsultaHIPDatosClienteService consultaHIPDatosClienteService = (ConsultaHIPDatosClienteService) this.getBeanService("scsicc.consultaHIPDatosClienteService");
		 ConsultaHIPResponsablesSeccionForm f = (ConsultaHIPResponsablesSeccionForm)this.formBusqueda;
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

		f.setCodConsultora((String) criteria.get("codCliente"));
		f.setNomConsultora((String) criteria.get("nomCliente"));
		f.setDesRegZonTerri((String) criteria.get("desRegion")+ "/" +criteria.get("desZona")+ "/" +criteria.get("codTerritorio"));
		f.setSelectedItems(f.getSelectedItemsVacio());
		
		List ListaResponsableSeleccion = consultaHIPDatosClienteService.getResponsableSeccionList(criteria);
		setHipResponsableSeleccionList(ListaResponsableSeleccion);

	}

	public List getHipResponsableSeleccionList() {
		return hipResponsableSeleccionList;
	}

	public void setHipResponsableSeleccionList(List hipResponsableSeleccionList) {
		this.hipResponsableSeleccionList = hipResponsableSeleccionList;
	}

	public ConsultaHIPDatosClienteAction getConsultaHIPDatosClienteAction() {
		return consultaHIPDatosClienteAction;
	}

	public void setConsultaHIPDatosClienteAction(
			ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction) {
		this.consultaHIPDatosClienteAction = consultaHIPDatosClienteAction;
	}
	
	
}
