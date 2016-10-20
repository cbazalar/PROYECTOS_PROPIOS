package biz.belcorp.ssicc.web.scsicc.hip.action;

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
import biz.belcorp.ssicc.web.scsicc.hip.form.ConsultaHIPEnviosPreferencialesForm;


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
public class ConsultaHIPEnviosPreferencialesAction extends BasePopupAbstractAction{

	private List envioPreferencialesList;
	
	@ManagedProperty(value="#{consultaHIPDatosClienteAction}")
	private ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction;
	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaHIPEnviosPreferencialesForm consultaHIPEnviosPreferencialesForm = new ConsultaHIPEnviosPreferencialesForm();
		return consultaHIPEnviosPreferencialesForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
            log.debug("Entering 'ConsultaHIPEnviosPreferencialesAction - setFindAttributes' method");
        }
		
		this.seleccionable = true;

		
		 Pais pais= this.mPantallaPrincipalBean.getCurrentCountry();
		 ConsultaHIPDatosClienteService consultaHIPDatosClienteService = (ConsultaHIPDatosClienteService) this.getBeanService("scsicc.consultaHIPDatosClienteService");
		 ConsultaHIPEnviosPreferencialesForm f = (ConsultaHIPEnviosPreferencialesForm)this.formBusqueda;
		 ConsultaHIPDatosCliente dtoDatosCliente = consultaHIPDatosClienteAction.getHipDtoDatosCliente();

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
		f.setCodigoPais((String) criteria.get("pais"));
		f.setCodigoMarca((String) criteria.get("marca"));
		f.setCodigoCanal((String) criteria.get("canal"));
		f.setSelectedItem("");

		List ListaEnvioPreferenciales= consultaHIPDatosClienteService.executaEnviosPreferencialesList(criteria);

		setEnvioPreferencialesList(ListaEnvioPreferenciales);
		
		this.beanRegistroSeleccionado = null;
		
		return this.getEnvioPreferencialesList();
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
            log.debug("Entering 'ConsultaHIPEnviosPreferencialesAction - setViewAtributes' method");
        }			
	}
	
	
	public void suscribir() throws Exception {
		
		if (log.isDebugEnabled()) log.debug("Entering 'suscribir()' method");
	

		ConsultaHIPDatosClienteService consultaHIPDatosClienteService = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");		
		ConsultaHIPEnviosPreferencialesForm f = (ConsultaHIPEnviosPreferencialesForm) this.formBusqueda;
		
		List PreferencialesList = this.getEnvioPreferencialesList();
		log.debug("envioPreferencialesList.size :"+ PreferencialesList.size());

		try
		{
			if(this.verificarRegistroSeleccionado()){
				
				Map ingresoPreferentesMap  = new HashMap();
				ingresoPreferentesMap  = (HashMap) this.beanRegistroSeleccionado;
				ingresoPreferentesMap.put("codigoPais", f.getCodigoPais() );
				ingresoPreferentesMap.put("codigoMarca",f.getCodigoMarca());
				ingresoPreferentesMap.put("codigoCanal",f.getCodigoCanal());

				log.debug("ingresoPreferentesMap :"+ ingresoPreferentesMap);
				
				if(ingresoPreferentesMap.get("flagSuscripcion").equals("0")){
					consultaHIPDatosClienteService.executaSuscribirEnviosPreferenciales(ingresoPreferentesMap);
				}
			}
		}
		catch(Exception ex)
		{
			this.addError("Error: ", ex.getMessage());
			
			log.error(ex.getMessage(), ex);
		}
		
		this.find();
	}

	
	public void finSuscribir() throws Exception {
		
		if (log.isDebugEnabled()) log.debug("Entering 'finSuscribir()' method");
	
		ConsultaHIPDatosClienteService consultaHIPDatosClienteService = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");		
		ConsultaHIPEnviosPreferencialesForm f = (ConsultaHIPEnviosPreferencialesForm) this.formBusqueda;
		
		List preferencialesList = this.getEnvioPreferencialesList();
		log.debug("preferencialesList.size :"+ preferencialesList.size());		
		
		try
		{
			if(this.verificarRegistroSeleccionado()){
				
				Map preferentesMap  = new HashMap();
				preferentesMap  = (HashMap) this.beanRegistroSeleccionado;
				log.debug("preferentesMap :"+ preferentesMap);
				if(preferentesMap.get("flagSuscripcion").equals("1")){
					consultaHIPDatosClienteService.executaFinSuscribirEnviosPreferenciales(preferentesMap);						
				}			
			}
		}
		catch(Exception ex)
		{
			this.addError("Error: ", ex.getMessage());
			
			log.error(ex.getMessage(), ex);
		}
		
		this.find();
	}	
	
	
	//GETTERS && SETTERS

	public List getEnvioPreferencialesList() {
		return envioPreferencialesList;
	}

	public void setEnvioPreferencialesList(List envioPreferencialesList) {
		this.envioPreferencialesList = envioPreferencialesList;
	}

	public ConsultaHIPDatosClienteAction getConsultaHIPDatosClienteAction() {
		return consultaHIPDatosClienteAction;
	}

	public void setConsultaHIPDatosClienteAction(
			ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction) {
		this.consultaHIPDatosClienteAction = consultaHIPDatosClienteAction;
	}

}
