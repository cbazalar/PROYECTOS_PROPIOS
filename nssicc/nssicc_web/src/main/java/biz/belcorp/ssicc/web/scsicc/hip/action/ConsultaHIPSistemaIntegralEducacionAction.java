package biz.belcorp.ssicc.web.scsicc.hip.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.model.EmpresaComercializadora;
import biz.belcorp.ssicc.dao.edu.model.HistoricoAptas;
import biz.belcorp.ssicc.dao.scsicc.model.ConsultaHIPDatosCliente;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUGenericoService;
import biz.belcorp.ssicc.service.edu.ProcesoEDURegistroAsistenciaService;
import biz.belcorp.ssicc.service.scsicc.ConsultaHIPDatosClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.form.ConsultaHIPSistemaIntegralEducacionForm;

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
public class ConsultaHIPSistemaIntegralEducacionAction extends BasePopupAbstractAction{
	
	private static final long serialVersionUID = -6738111572485250591L;
	
	private List hipEduCursosList;
	
	@ManagedProperty(value="#{consultaHIPDatosClienteAction}")
	private ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction;

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaHIPSistemaIntegralEducacionForm consultaHIPSistemaIntegralEducacionForm = new ConsultaHIPSistemaIntegralEducacionForm();
		return consultaHIPSistemaIntegralEducacionForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
            log.debug("Entering 'ConsultaHIPSistemaIntegralEducacionAction - setFindAttributes' method");
        }
		
		return this.getHipEduCursosList();
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
            log.debug("Entering 'ConsultaHIPSistemaIntegralEducacionAction - setViewAtributes' method");
        }

		 ConsultaHIPDatosClienteService consultaHIPDatosClienteService = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");
		 ConsultaHIPSistemaIntegralEducacionForm f = (ConsultaHIPSistemaIntegralEducacionForm)this.formBusqueda;
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
		
		List listaEduConsultoras= consultaHIPDatosClienteService.getEduConsultorasList(criteria);
		Map eduConsultorasMap=null;
		
		if (listaEduConsultoras.size() >0  ){
			log.debug("PreferencialesList.get(posicion-1) " + listaEduConsultoras.get(0));
			eduConsultorasMap  = (HashMap) listaEduConsultoras.get(0);
			log.debug("eduConsultorasMap :"+ eduConsultorasMap);

			f.setNivAlcanzado((String) eduConsultorasMap.get("nivAlcanzado"));
			f.setEstado((String) eduConsultorasMap.get("estado"));
			f.setNivPorAlcanzar((String) eduConsultorasMap.get("nivPorAlcanzar"));

			//setamos parametros para la busqueda de cronograma 
			criteria.put("codCurso", String.valueOf(eduConsultorasMap.get("codCurso")));
			
		    String campanaCronograma=getCampanaCronograma(criteria);
		  
		}
		List listaEduCursos= consultaHIPDatosClienteService.getEduCursosList(criteria);
		
		setHipEduCursosList(listaEduCursos);
		
	}
	
	/**
	 * Retorna la campanha que deberia tener de cronograma
	 * @param criteria
	 * @return
	 */
	private String getCampanaCronograma(Map criteria) {
		String campana=null;
		ProcesoEDURegistroAsistenciaService service = (ProcesoEDURegistroAsistenciaService) getBean("edu.procesoEDURegistroAsistenciaService");
		MantenimientoEDUGenericoService serviceEduGener = (MantenimientoEDUGenericoService) getBean("edu.mantenimientoEDUGenericoService");
		HistoricoAptas historicoAptas = new HistoricoAptas();
		historicoAptas.setCodigoPais(String.valueOf(criteria.get("pais")));
		historicoAptas.setCodigoEmpresa(getEmpresa(criteria));
		historicoAptas.setCodigoCliente(String.valueOf(criteria.get("codCliente")));
		historicoAptas.setCodigoCurso(String.valueOf(criteria.get("codCurso")));
		
		 List listHistoricoAptas=service.getSituacionActualHistoricoAptas(historicoAptas);
		  if(listHistoricoAptas.size()>0){
				  historicoAptas=(HistoricoAptas)listHistoricoAptas.get(0);
				  log.debug("ultima calificada como apta  " + historicoAptas.getCampanhaUltimaCalificacionApta());
				if(historicoAptas.getEstadoCapacitacion().equals(Constants.EDU_CURSO_SITUACION_PROGRAMADA)){
				  campana = historicoAptas.getCampanhaUltimaCalificacionApta();				  
				  Map map= new HashMap();
				  map.put("campannaProceso", historicoAptas.getCampanhaUltimaCalificacionApta());
				  map.put("numeroCampanna", new Integer("1"));
				  campana =serviceEduGener.getDevuelveCampanna(map);
				}  
		 }		
		
		return campana;
	}
	
	/**
	 * Retorna la empresa del pais
	 * @param criteria
	 * @return
	 */
	private String getEmpresa(Map criteria) {
		EmpresaComercializadora parametroEmpresa = new EmpresaComercializadora();
		parametroEmpresa.setCodigoPais(String.valueOf(criteria.get("pais")));
		parametroEmpresa.setEstadoRegistro(Constants.ESTADO_ACTIVO);
		
		MantenimientoEDUGenericoService siccService = (MantenimientoEDUGenericoService) 
				getBean("edu.mantenimientoEDUGenericoService");
				
		List listEmpresa =siccService.getEmpresasComercializadorasByPais(parametroEmpresa);
		if(listEmpresa.size()>0)
			parametroEmpresa = (EmpresaComercializadora) listEmpresa.get(0);
		
		return parametroEmpresa.getCodigoEmpresa();
	}
	
	//GETTERS && SETTERS

	public List getHipEduCursosList() {
		return hipEduCursosList;
	}

	public void setHipEduCursosList(List hipEduCursosList) {
		this.hipEduCursosList = hipEduCursosList;
	}

	public ConsultaHIPDatosClienteAction getConsultaHIPDatosClienteAction() {
		return consultaHIPDatosClienteAction;
	}

	public void setConsultaHIPDatosClienteAction(
			ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction) {
		this.consultaHIPDatosClienteAction = consultaHIPDatosClienteAction;
	}
	
	
	

}
