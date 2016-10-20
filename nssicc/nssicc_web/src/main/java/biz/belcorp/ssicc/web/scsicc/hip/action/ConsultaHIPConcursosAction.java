package biz.belcorp.ssicc.web.scsicc.hip.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.scsicc.model.ConsultaHIPDatosCliente;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.ConsultaHIPDatosClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.form.ConsultaHIPConcursosForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ConsultaHIPConcursosAction"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza </a>
 * 
 */

@ManagedBean  
@SessionScoped
public class ConsultaHIPConcursosAction extends BasePopupAbstractAction {
		
	private static final long serialVersionUID = -6485026127485659942L;
	
	@ManagedProperty(value="#{consultaHIPDatosClienteAction}")
	private ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction;
	
	private List hipConcursosList = new ArrayList();
	private List hipConcursosRecomendacionesList = new ArrayList();
	private List hipConcursosPuntajeList = new ArrayList();
	private List hipConcursosBolsaFaltantesList = new ArrayList();
	private List hipConcursosPremiosList = new ArrayList();
	private List hipConcursosPremiosAtendidosList = new ArrayList();
	private List hipConcursosPremiosElegidosList = new ArrayList();
	private List tipoPremioList = new ArrayList();
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaHIPConcursosForm consultaHIPConcursosForm = new ConsultaHIPConcursosForm();
		return consultaHIPConcursosForm;
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
		
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");
		ConsultaHIPConcursosForm f = (ConsultaHIPConcursosForm) this.formBusqueda;
		
		ConsultaHIPDatosCliente dtoDatosCliente = this.consultaHIPDatosClienteAction.getHipDtoDatosCliente();
		
		f.setCodConsultora(dtoDatosCliente.getCodigoCliente());
		f.setNomConsultora(dtoDatosCliente.getNombreCompleto());
		f.setDesRegZonTerri(dtoDatosCliente.getDescripcionRegion() + " / " + dtoDatosCliente.getDescripcionZona() 
							+ " / " + dtoDatosCliente.getCodigoTerritorio());
		f.setClasificacionLove(dtoDatosCliente.getClasificacionLove());
		if(f.getClasificacionLove().length()>0)
			f.setMostrarClasificacionLove(true);
		else
			f.setMostrarClasificacionLove(false);
		
		Map criteriaIndicador = new HashMap();
		criteriaIndicador.put("codigoPais", dtoDatosCliente.getCodigoPais());
        
        //verificamos el indicador de BasParamPais
        f.setIndicadorBasparampais(service.getIndicadorBasparampais(criteriaIndicador));
		
		//obtenemos los Concursos de la consultora
		Map criteria = new HashMap();
		criteria.put("oidCliente", dtoDatosCliente.getOidCliente());
		criteria.put("oidIdioma", dtoDatosCliente.getOidIdioma());
		
		List listConcursos = service.getConcursos(criteria);
		this.setHipConcursosList(listConcursos);
		
		//Obtenes valor del parametro para la pantalla de Digitacion Simplificada
		GenericoService genericoService1 = (GenericoService) getBean("genericoService");
		
		ParametroPais parametroPais1 = new ParametroPais();
		parametroPais1.setCodigoPais(dtoDatosCliente.getCodigoPais());
		parametroPais1.setCodigoSistema(Constants.HIP_CODIGO_SISTEMA);
		parametroPais1.setNombreParametro(Constants.HIP_NOMBRE_PARAMETRO_CONCURSO_CONSOLIDADO);
		parametroPais1.setIndicadorActivo(Constants.NUMERO_UNO);
		
		List lstParametros1 = genericoService1.getParametrosPais(parametroPais1);
		
		f.setMostrarConsolidado(false);
		
		if(lstParametros1 != null && lstParametros1.size() > 0){			
			ParametroPais ps = (ParametroPais)lstParametros1.get(0);
			f.setMostrarConsolidado(true);
		}
		
		f.setSelectedItem("");
		
		this.seleccionable = true;		
	}
	
	@Override
	protected List setFindAttributes() throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ConsultaHIPConcursosAction - setFindAttributes' method");
		}
		
		return this.getHipConcursosList();
	}
	
	/**
	 * 
	 * @param actionEvent
	 */
	public void searchRecomendaciones(ActionEvent actionEvent) {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'searchRecomendaciones' method");
		}
		
//		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
//		String oidConcurso = externalContext.getRequestParameterMap().get("oidConcurso");
		
		ConsultaHIPConcursosForm f = (ConsultaHIPConcursosForm) this.formBusqueda;
		
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");
		ConsultaHIPDatosCliente dtoDatosCliente = this.consultaHIPDatosClienteAction.getHipDtoDatosCliente();
		
//		Se obtiene fila seleccionada
		Map seleccionado = new HashMap();
		seleccionado =  (Map) this.beanRegistroSeleccionado;
		String oidConcurso =MapUtils.getString(seleccionado, "oidConcurso");
		
		Map criteria = new HashMap();
		criteria.put("oidIdioma" , dtoDatosCliente.getOidIdioma());
		criteria.put("oidCliente" , dtoDatosCliente.getOidCliente());
		criteria.put("codPais" , dtoDatosCliente.getCodigoPais());
		
		if(oidConcurso !=null)
			criteria.put("oidConcurso" , oidConcurso);
		
		List listRecomendaciones = service.getRecomendaciones(criteria);
		
		//pintamos la grilla de los premios agrupados por nivel 
		this.setHipConcursosRecomendacionesList(listRecomendaciones);

		if(oidConcurso ==null) {
			f.setNroConcurso(Constants.OPCION_TODOS);
			f.setDescripcion(Constants.OPCION_TODOS);
		} else {
			//pintamos informacion del concurso
			List listConcursos = this.getHipConcursosList();
			for(int i=0; i<listConcursos.size(); i++) {
				Map mapConcurso = (Map)listConcursos.get(i);
				String oidConcursoAux = (String)mapConcurso.get("oidConcurso");
				
				if (oidConcursoAux.equals(oidConcurso)) {
					f.setNroConcurso(reemplazarNulo(mapConcurso.get("numeroConcurso")));
					f.setDescripcion(reemplazarNulo(mapConcurso.get("nombreConcurso")));
					break;
				}	
			}
		}
		
	}
	
	
	/**
	 * 
	 * @param actionEvent
	 */
	public void searchParticipacion(ActionEvent actionEvent) {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'searchParticipacion' method");
		}
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String oidConcurso = externalContext.getRequestParameterMap().get("oidConcurso");
				
		ConsultaHIPConcursosForm f = (ConsultaHIPConcursosForm) this.formBusqueda;
		
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");
		ConsultaHIPDatosCliente dtoDatosCliente = this.consultaHIPDatosClienteAction.getHipDtoDatosCliente();
		
		Map criteria = new HashMap();
		criteria.put("oidCliente" , dtoDatosCliente.getOidCliente());
		criteria.put("oidIdioma" , dtoDatosCliente.getOidIdioma());
		criteria.put("oidConcurso" , oidConcurso);
		criteria.put("codigoCliente" , dtoDatosCliente.getCodigoCliente());
			
		List listPuntajeDetalle = service.getPuntajeDetalleConcurso(criteria);
		List listPuntajeResumen = service.getPuntajeResumenConcurso(criteria);
		
		//pintamos la grilla de los puntajes obtenidos por la consultora 
		 this.setHipConcursosPuntajeList(listPuntajeDetalle);
		
		//pintamos informacion del concurso
		List listConcursos = this.getHipConcursosList();
		for(int i=0; i<listConcursos.size(); i++) {
			Map mapConcurso = (Map)listConcursos.get(i);
			String oidConcursoAux = (String)mapConcurso.get("oidConcurso");
			
			if (oidConcursoAux.equals(oidConcurso)) {
				f.setNroConcurso(reemplazarNulo(mapConcurso.get("numeroConcurso")));
				f.setDescripcion(reemplazarNulo(mapConcurso.get("nombreConcurso")));
				f.setPeriodoInicio(reemplazarNulo(mapConcurso.get("periodoInicio")));
				f.setPeriodoFin(reemplazarNulo(mapConcurso.get("periodoFin")));
				
				break;
			}	
		}
		
		if(listPuntajeResumen!=null && listPuntajeResumen.size()>0) {
			Map mapPuntaje = (Map)listPuntajeResumen.get(0);
			
			f.setNivelAlcanzado(reemplazarNulo(mapPuntaje.get("nivelAlcanzado")));
			f.setSituacion(reemplazarNulo(mapPuntaje.get("situacion")));
			f.setSaldoPuntaje(reemplazarNulo(mapPuntaje.get("saldoPuntaje")));
			f.setVentaBase(reemplazarNulo(mapPuntaje.get("ventaBase")));
			f.setCodigoDupla(reemplazarNulo(mapPuntaje.get("codigoDupla")));
			f.setNombreDupla(reemplazarNulo(mapPuntaje.get("nombreDupla")));
			
			f.setPuntajeTotal(BigDecimal.valueOf(Integer.parseInt(mapPuntaje.get("puntajeTotal").toString())) );
			f.setPuntajeUtilizado(BigDecimal.valueOf(Integer.parseInt(mapPuntaje.get("puntajeUtilizado").toString())));
			f.setSaldo(BigDecimal.valueOf(Integer.parseInt(mapPuntaje.get("saldo").toString())));
			
			f.setReservaCanjes(BigDecimal.valueOf(Integer.parseInt(mapPuntaje.get("reserva").toString())) );
			f.setSaldoReservas(f.getSaldo().subtract(f.getReservaCanjes()));
			if(f.getSaldoReservas().doubleValue()<0)
				f.setSaldoReservas(new BigDecimal("0"));
			
		} else {
			f.setNivelAlcanzado("");
			f.setSituacion("");
			f.setSaldoPuntaje("");
			f.setVentaBase("");
			f.setCodigoDupla("");
			f.setNombreDupla("");
		}
		
		//validar la lista de tipo premio
		if(StringUtils.isNotEmpty(f.getNroConcurso()))
			setValidarListaTipoPremio(f);
		
		
		//CONSULTA PREMIOS PENDIENTES DE DESPACHO
		List listBolsaFaltantes = service.getBolsaFaltantes(criteria);
		
		//pintamos la grilla de los premios agrupados por nivel 
		this.setHipConcursosBolsaFaltantesList(listBolsaFaltantes); 
		
		//CONSULTA PREMIOS POR NIVEL
		List listPremios = service.getPremiosByNivel(criteria);
		
		for(int i=0; i<listPremios.size(); i++) {
			Map mapPremio = (Map)listPremios.get(i);
			mapPremio.put("numeroPremio", "Premio " + mapPremio.get("numeroPremio"));
			
			String indicadorElectivo = (String)mapPremio.get("indicadorPremioElectivo");
			if(indicadorElectivo.equals("0"))
				indicadorElectivo = "No"; 
			else
				indicadorElectivo = "Si";		
			
			int nivel = Integer.parseInt((String)mapPremio.get("nivel"));
			if(nivel%2 != 0)
				indicadorElectivo = indicadorElectivo + " ";
			
			mapPremio.put("indicadorPremioElectivo", indicadorElectivo);
		}	
		
		//pintamos la grilla de los premios agrupados por nivel 
		this.setHipConcursosPremiosList(listPremios);

		//CONSULTA PREMIOS ATENDIDOS
		List listPremiosAtendidosFaltantes = service.getPremiosAtendidosFaltantes(criteria);
		
		//pintamos la grilla de los premios agrupados por nivel 
		this.setHipConcursosPremiosAtendidosList(listPremiosAtendidosFaltantes);

		//CONSULTA PREMIOS RECHAZADOS
		/*List listPremiosRechazados = service.getPremiosRechazados(criteria);
		
		//pintamos la grilla de los premios rechazados
		request.getSession().setAttribute(Constants.HIP_CONCURSOS_PREMIOS_RECHAZADOS_LIST, listPremiosRechazados);*/

		//CONSULTA PREMIOS ELEGIDOS
		List listPremiosElegidos = service.getPremiosElegidos(criteria);
		
		//pintamos la grilla de los premios eleegidos
		this.setHipConcursosPremiosElegidosList(listPremiosElegidos);
		
	}
		
	/**
	 * 
	 * @param actionEvent
	 */
	public void searchPuntaje(ActionEvent actionEvent) {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'searchPuntaje' method");
		}

		this.verificarRegistro(actionEvent);
		
		ConsultaHIPConcursosForm f = (ConsultaHIPConcursosForm) this.formBusqueda;
		
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");
		ConsultaHIPDatosCliente dtoDatosCliente = this.consultaHIPDatosClienteAction.getHipDtoDatosCliente();
		String oidConcurso = MapUtils.getString((Map)this.getBeanRegistroSeleccionado(), "oidConcurso");
		
		Map criteria = new HashMap();
		criteria.put("oidCliente" , dtoDatosCliente.getOidCliente());
		criteria.put("oidIdioma" , dtoDatosCliente.getOidIdioma());
		criteria.put("oidConcurso" , oidConcurso);
		criteria.put("codigoPais" , dtoDatosCliente.getCodigoPais());
					
		List listPuntajeDetalle = service.getPuntajeDetalleConcurso(criteria);
		List listPuntajeResumen = service.getPuntajeResumenConcurso(criteria);
				
		//pintamos la grilla de los puntajes obtenidos por la consultora 
		this.setHipConcursosPuntajeList(listPuntajeDetalle);
		
		//pintamos informacion del concurso
		List listConcursos = this.getHipConcursosList();
		for(int i=0; i<listConcursos.size(); i++) {
			Map mapConcurso = (Map)listConcursos.get(i);
			String oidConcursoAux = (String)mapConcurso.get("oidConcurso");
			
			if (oidConcursoAux.equals(oidConcurso)) {
				f.setNroConcurso(reemplazarNulo(mapConcurso.get("numeroConcurso")));
				f.setDescripcion(reemplazarNulo(mapConcurso.get("nombreConcurso")));
				f.setPeriodoInicio(reemplazarNulo(mapConcurso.get("periodoInicio")));
				f.setPeriodoFin(reemplazarNulo(mapConcurso.get("periodoFin")));
				
				break;
			}	
		}
		
		if(listPuntajeResumen!=null && listPuntajeResumen.size()>0) {
			Map mapPuntaje = (Map)listPuntajeResumen.get(0);
			
			f.setNivelAlcanzado(reemplazarNulo(mapPuntaje.get("nivelAlcanzado")));
			f.setSituacion(reemplazarNulo(mapPuntaje.get("situacion")));
			f.setSaldoPuntaje(reemplazarNulo(mapPuntaje.get("saldoPuntaje")));
			f.setVentaBase(reemplazarNulo(mapPuntaje.get("ventaBase")));
			f.setCodigoDupla(reemplazarNulo(mapPuntaje.get("codigoDupla")));
			f.setNombreDupla(reemplazarNulo(mapPuntaje.get("nombreDupla")));
			
			f.setPuntajeTotal(BigDecimal.valueOf(Integer.parseInt(mapPuntaje.get("puntajeTotal").toString())) );
			f.setPuntajeUtilizado(BigDecimal.valueOf(Integer.parseInt(mapPuntaje.get("puntajeUtilizado").toString())));
			f.setSaldo(BigDecimal.valueOf(Integer.parseInt(mapPuntaje.get("saldo").toString())));
			
		} else {
			f.setNivelAlcanzado("");
			f.setSituacion("");
			f.setSaldoPuntaje("");
			f.setVentaBase("");
			f.setCodigoDupla("");
			f.setNombreDupla("");
		}
		
		//validar la lista de tipo premio
		if(StringUtils.isNotEmpty(f.getNroConcurso()))
			setValidarListaTipoPremio(f);
			
	}
	
	/**
	 * 
	 * @param actionEvent
	 */
	public void searchBolsaFaltantes(ActionEvent actionEvent) {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'searchBolsaFaltantes' method");
		}
		
		this.verificarRegistro(actionEvent);
		
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");
		ConsultaHIPDatosCliente dtoDatosCliente = this.consultaHIPDatosClienteAction.getHipDtoDatosCliente();
		
		Map criteria = new HashMap();
		criteria.put("oidIdioma" , dtoDatosCliente.getOidIdioma());
		criteria.put("oidCliente" , dtoDatosCliente.getOidCliente());
			
		List listBolsaFaltantes = service.getBolsaFaltantes(criteria);
		
		//pintamos la grilla de los premios agrupados por nivel 
		this.setHipConcursosBolsaFaltantesList(listBolsaFaltantes);
		
	}
	
	/**
	 * 
	 * @param actionEvent
	 */
	public void searchPremiosByNivel(ActionEvent actionEvent) {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'searchPremiosByNivel' method");
		}
		
		this.verificarRegistro(actionEvent);
		
		ConsultaHIPConcursosForm f = (ConsultaHIPConcursosForm) this.formBusqueda;
	
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");
		ConsultaHIPDatosCliente dtoDatosCliente = this.consultaHIPDatosClienteAction.getHipDtoDatosCliente();
		String oidConcurso = MapUtils.getString((Map)this.getBeanRegistroSeleccionado(), "oidConcurso");
		
		Map criteria = new HashMap();
		criteria.put("oidIdioma", dtoDatosCliente.getOidIdioma());
		criteria.put("oidConcurso", oidConcurso);
			
		List listPremios = service.getPremiosByNivel(criteria);
		
		for(int i=0; i<listPremios.size(); i++) {
			Map mapPremio = (Map)listPremios.get(i);
			mapPremio.put("numeroPremio", "Premio " + mapPremio.get("numeroPremio"));
			
			String indicadorElectivo = (String)mapPremio.get("indicadorPremioElectivo");
			if(indicadorElectivo.equals("0"))
				indicadorElectivo = "No"; 
			else
				indicadorElectivo = "Si";		
			
			int nivel = Integer.parseInt((String)mapPremio.get("nivel"));
			if(nivel%2 != 0)
				indicadorElectivo = indicadorElectivo + " ";
			
			mapPremio.put("indicadorPremioElectivo", indicadorElectivo);
		}	
		
		//pintamos la grilla de los premios agrupados por nivel 
		this.setHipConcursosPremiosList(listPremios);
		
		//pintamos informacion del concurso
		List listConcursos = this.getHipConcursosList();
		for(int i=0; i<listConcursos.size(); i++) {
			Map mapConcurso = (Map)listConcursos.get(i);
			String oidConcursoAux = (String)mapConcurso.get("oidConcurso");
			
			if (oidConcursoAux.equals(oidConcurso)) {
				f.setNroConcurso(reemplazarNulo(mapConcurso.get("numeroConcurso")));
				f.setDescripcion(reemplazarNulo(mapConcurso.get("nombreConcurso")));
				break;
			}	
		}
		
	}
	
	/**
	 * 
	 * @param actionEvent
	 */
	public void searchPremiosAtendidosFaltantes(ActionEvent actionEvent) {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'searchPremiosAtendidosFaltantes' method");
		}
		
		this.verificarRegistro(actionEvent);
		
		ConsultaHIPConcursosForm f = (ConsultaHIPConcursosForm) this.formBusqueda;
		
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");
		ConsultaHIPDatosCliente dtoDatosCliente = this.consultaHIPDatosClienteAction.getHipDtoDatosCliente();
		String oidConcurso = MapUtils.getString((Map)this.getBeanRegistroSeleccionado(), "oidConcurso");
		
		Map criteria = new HashMap();
		criteria.put("oidIdioma", dtoDatosCliente.getOidIdioma());
		criteria.put("oidCliente", dtoDatosCliente.getOidCliente());
		criteria.put("oidConcurso", oidConcurso);
		
		List listPremiosAtendidosFaltantes = service.getPremiosAtendidosFaltantes(criteria);
		
		//pintamos la grilla de los premios agrupados por nivel 
		this.setHipConcursosPremiosAtendidosList(listPremiosAtendidosFaltantes);

		//pintamos informacion del concurso
		List listConcursos = this.getHipConcursosList();
		for(int i=0; i<listConcursos.size(); i++) {
			Map mapConcurso = (Map)listConcursos.get(i);
			String oidConcursoAux = (String)mapConcurso.get("oidConcurso");
			
			if (oidConcursoAux.equals(oidConcurso)) {
				f.setNroConcurso(reemplazarNulo(mapConcurso.get("numeroConcurso")));
				f.setDescripcion(reemplazarNulo(mapConcurso.get("nombreConcurso")));
				break;
			}	
		}
	
	}
	
	/**
	 * 
	 * @param actionEvent
	 */
	public void searchPremiosElectivos(ActionEvent actionEvent) {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'searchPremiosElectivos' method");
		}
		
		this.verificarRegistro(actionEvent);
		
		ConsultaHIPConcursosForm f = (ConsultaHIPConcursosForm) this.formBusqueda;
		
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");
		ConsultaHIPDatosCliente dtoDatosCliente = this.consultaHIPDatosClienteAction.getHipDtoDatosCliente();
		String oidConcurso = MapUtils.getString((Map)this.getBeanRegistroSeleccionado(), "oidConcurso");
		
		Map criteria = new HashMap();
		criteria.put("oidIdioma", dtoDatosCliente.getOidIdioma());
		criteria.put("oidConcurso", oidConcurso);
		criteria.put("codigoCliente", dtoDatosCliente.getCodigoCliente());
		
		List listPremiosElegidos = service.getPremiosElegidos(criteria);
		
		//pintamos la grilla de los premios eleegidos
		this.setHipConcursosPremiosElegidosList(listPremiosElegidos);
	
	}
	
	/**
	 * setea la lista de premio 
	 * @param f
	 * @param session 
	 */
	private void setValidarListaTipoPremio(ConsultaHIPConcursosForm f) {
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");
		Map criteria = new HashMap();
		criteria.put("nroConcurso", f.getNroConcurso());
		List listaTipo = service.getListaTipoPremios(criteria);
		Iterator it = listaTipo.iterator();
		boolean hayTipoPremioValido = true;
		//validamos de que no haya tipo de premio 1 
		while(it.hasNext()){
		  	Map map=(Map)it.next();
		  	String tipoPremio = String.valueOf(map.get("tipoPremio"));
		  	if(Constants.NUMERO_UNO.equals(tipoPremio)){
		  		hayTipoPremioValido=false;
		  		break;
		  	}	
		}
		if(hayTipoPremioValido)
			this.setTipoPremioList(listaTipo);
		else
			this.setTipoPremioList(null);
		
	}

	/**
	 * metodo auxiliar que me permite recuperar en cadena el valor de un objeto
	 * 
	 * @param obj
	 * @return
	 */		
	private String reemplazarNulo(Object obj) {
		if(obj == null)
			return "";
		else
			return (String)obj;
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
	 * @return the hipConcursosList
	 */
	public List getHipConcursosList() {
		return hipConcursosList;
	}

	/**
	 * @param hipConcursosList the hipConcursosList to set
	 */
	public void setHipConcursosList(List hipConcursosList) {
		this.hipConcursosList = hipConcursosList;
	}

	/**
	 * @return the hipConcursosRecomendacionesList
	 */
	public List getHipConcursosRecomendacionesList() {
		return hipConcursosRecomendacionesList;
	}

	/**
	 * @param hipConcursosRecomendacionesList the hipConcursosRecomendacionesList to set
	 */
	public void setHipConcursosRecomendacionesList(
			List hipConcursosRecomendacionesList) {
		this.hipConcursosRecomendacionesList = hipConcursosRecomendacionesList;
	}

	/**
	 * @return the hipConcursosPuntajeList
	 */
	public List getHipConcursosPuntajeList() {
		return hipConcursosPuntajeList;
	}

	/**
	 * @param hipConcursosPuntajeList the hipConcursosPuntajeList to set
	 */
	public void setHipConcursosPuntajeList(List hipConcursosPuntajeList) {
		this.hipConcursosPuntajeList = hipConcursosPuntajeList;
	}

	/**
	 * @return the hipConcursosBolsaFaltantesList
	 */
	public List getHipConcursosBolsaFaltantesList() {
		return hipConcursosBolsaFaltantesList;
	}

	/**
	 * @param hipConcursosBolsaFaltantesList the hipConcursosBolsaFaltantesList to set
	 */
	public void setHipConcursosBolsaFaltantesList(
			List hipConcursosBolsaFaltantesList) {
		this.hipConcursosBolsaFaltantesList = hipConcursosBolsaFaltantesList;
	}

	/**
	 * @return the hipConcursosPremiosAtendidosList
	 */
	public List getHipConcursosPremiosAtendidosList() {
		return hipConcursosPremiosAtendidosList;
	}

	/**
	 * @param hipConcursosPremiosAtendidosList the hipConcursosPremiosAtendidosList to set
	 */
	public void setHipConcursosPremiosAtendidosList(
			List hipConcursosPremiosAtendidosList) {
		this.hipConcursosPremiosAtendidosList = hipConcursosPremiosAtendidosList;
	}

	/**
	 * @return the hipConcursosPremiosList
	 */
	public List getHipConcursosPremiosList() {
		return hipConcursosPremiosList;
	}

	/**
	 * @param hipConcursosPremiosList the hipConcursosPremiosList to set
	 */
	public void setHipConcursosPremiosList(List hipConcursosPremiosList) {
		this.hipConcursosPremiosList = hipConcursosPremiosList;
	}

	/**
	 * @return the hipConcursosPremiosElegidosList
	 */
	public List getHipConcursosPremiosElegidosList() {
		return hipConcursosPremiosElegidosList;
	}

	/**
	 * @param hipConcursosPremiosElegidosList the hipConcursosPremiosElegidosList to set
	 */
	public void setHipConcursosPremiosElegidosList(
			List hipConcursosPremiosElegidosList) {
		this.hipConcursosPremiosElegidosList = hipConcursosPremiosElegidosList;
	}

	/**
	 * @return the tipoPremioList
	 */
	public List getTipoPremioList() {
		return tipoPremioList;
	}

	/**
	 * @param tipoPremioList the tipoPremioList to set
	 */
	public void setTipoPremioList(List tipoPremioList) {
		this.tipoPremioList = tipoPremioList;
	}
		
}
