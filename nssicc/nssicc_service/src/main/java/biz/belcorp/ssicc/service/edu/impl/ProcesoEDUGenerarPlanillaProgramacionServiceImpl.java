package biz.belcorp.ssicc.service.edu.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.ProcesoEDUCierreProcesosRegionDAO;
import biz.belcorp.ssicc.dao.edu.ProcesoEDUGenerarPlanillaProgramacionDAO;
import biz.belcorp.ssicc.dao.edu.model.ParametroCursoCapacitacion;
import biz.belcorp.ssicc.dao.edu.model.ParametroProceso;
import biz.belcorp.ssicc.dao.edu.model.RegionCursoCapacitacion;
import biz.belcorp.ssicc.service.AjaxService2;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUCursoCapacitacionService;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUGenericoService;
import biz.belcorp.ssicc.service.edu.ProcesoEDUComercialService;
import biz.belcorp.ssicc.service.edu.ProcesoEDUGenerarPlanillaProgramacionService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * @author <a href="mailto:rvela@belcorp.biz">Robinson Vela Bardales</a>
 *
 */
@Service("edu.procesoEDUGenerarPlanillaProgramacionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoEDUGenerarPlanillaProgramacionServiceImpl extends BaseService  
		implements ProcesoEDUGenerarPlanillaProgramacionService	{

	@Resource(name="edu.procesoEDUGenerarPlanillaProgramacionDAO")
	ProcesoEDUGenerarPlanillaProgramacionDAO procesoEDUGenerarPlanillaProgramacionDAO;
	
	@Resource(name="edu.procesoEDUCierreProcesosRegionDAO")
	ProcesoEDUCierreProcesosRegionDAO procesoEDUCierreProcesosRegionDAO;
	
	@Resource(name="edu.mantenimientoEDUCursoCapacitacionService")
	MantenimientoEDUCursoCapacitacionService mantenimientoEDUCursoCapacitacionService;
	
	@Resource(name="edu.procesoEDUComercialService")
	ProcesoEDUComercialService	procesoEDUComercialService;
	
	@Resource(name="edu.mantenimientoEDUGenericoService")
	MantenimientoEDUGenericoService mantenimientoEDUGenericoService;
	
	@Resource(name="ajaxService2")
	AjaxService2 ajaxService;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUActualizarPlanillaProgramacionService#executeActualizarPlanillaProgramacion(java.lang.String, java.util.Map)
	 */
	public void executeGenerarPlanillaProgramacion(Map params) throws Exception {
		
        //SI EL PARAMETRO ES REENVIO Sergio Buchelli 08/11/2007		
		String tipoProceso= (String)params.get("tipoProceso");
		log.debug("tipoProceso " +tipoProceso);
		this.obtenerListaRegiones(params);
		List regionList = (List) params.get("regionList");
		
		String indicadorConsultoraRezagada = (String)params.get("indicadorConsultoraRezagada");
		if(Constants.NUMERO_UNO.equals(indicadorConsultoraRezagada)){
		//Insertando en planilla consulora con peididos rezagados
			executeProcesoConsultoraRezagadas(regionList,params);
		}
		
		/* En caso que NO sea REENVIO */
		if(!tipoProceso.equals(Constants.EDU_TIPO_PROCESO_REENVIO)) {
			//por metodo del padre enviara a grabarReporteGeneracion.do , este se encargara de armar los pdf :D 
			//reeenviar los correos ;)
			/* Obteniendo valores */
			String codigoProceso = (String) params.get("codigoProceso");
			
			/*Insertamos los parametro de Regiones seleccionadas*/
			if ((regionList!=null) && (regionList.size()>0)){
				ParametroProceso parametroProceso = new ParametroProceso();
				for (int i=0;i<regionList.size();i++){
					parametroProceso = new ParametroProceso();
					String region = (String) regionList.get(i);
					parametroProceso.setCodigoProceso(codigoProceso);
					parametroProceso.setCodigoParametro(Constants.EDU_PARAM_PROC_REGION);
					parametroProceso.setValorCadenaParametro(region);
					procesoEDUGenerarPlanillaProgramacionDAO.insertParametroProceso(parametroProceso);
				}
			}
			params.put("codigoParametro", Constants.EDU_PARAM_PROC_REGION);
			procesoEDUGenerarPlanillaProgramacionDAO.executeGenerarPlanillaProgramacion(params);
			
			params.put("codigoParam",Constants.EDU_PARAM_PROC_REGION);
			procesoEDUCierreProcesosRegionDAO.executePasarConsultorasProgramadaAPendiente(params);
			
			//actualizacion status consultora
			procesoEDUGenerarPlanillaProgramacionDAO.executeActualizacionStatusConsultora(params);
		}	
		
		
		/* Generando Lista para enviarlo al Sistema Comercial */
		String mensaje = (String) params.get("mensajeError");
		if (StringUtils.isBlank(mensaje)) { 
			String codigoPais  = (String) params.get("codigoPais");
			String codigoEmpresa  = (String) params.get("codigoEmpresa");
			if (verificaConexionExternaSistemaComercial(codigoPais, codigoEmpresa)) {
				String codigoPeriodoSgte = ajaxService.getCampannaSgteEDUByPaisEmpresa(codigoPais, codigoEmpresa);
				params.put("codigoPeriodoSgte",codigoPeriodoSgte);
				procesoEDUComercialService.executeProcesoEDUEnviarGenerarPlanillaProgramacion(codigoPais, params);
			}	
		}	
		
	}

	private void executeProcesoConsultoraRezagadas(List regionList, Map params)throws Exception {
		//no le interesa la region a procesar inserta las de pedido extemporaneo a planillas
				procesoEDUGenerarPlanillaProgramacionDAO.executeProcesoConsultoraRezagadas(params);
			}

	/**
	 * Obteniendo  Lista de Regiones 
	 * @param params
	 */
	private void obtenerListaRegiones(Map params) {
		/* Regenerando lista de Regiones */
		boolean encontrotodos = false;
		List listaRegiones = (List) params.get("regionList");
		String codigoPais  = (String) params.get("codigoPais");
		String codigoEmpresa = (String) params.get("codigoEmpresa");
		for (int i=0; i < listaRegiones.size(); i++ ) {
			String tempo = (String) listaRegiones.get(i);
			if ("Todos".equalsIgnoreCase(tempo)) {
				encontrotodos = true;
				break;
			}
		}
		/* En caso haya seleccionado Todos */
		if (encontrotodos) {
			listaRegiones = new ArrayList();
			RegionCursoCapacitacion region = new RegionCursoCapacitacion();
			region.setCodigoPais(codigoPais);
			region.setCodigoEmpresa(codigoEmpresa);
			List resultado = mantenimientoEDUCursoCapacitacionService.getRegion(region);
			for (int i=0; i < resultado.size(); i++ ) {
				RegionCursoCapacitacion regTemp = (RegionCursoCapacitacion) resultado.get(i);
				String temp = regTemp.getCodigoRegion();
				listaRegiones.add(temp);
			}	
		}
		params.remove("regionList");
		params.put("regionList", listaRegiones);
	}

	/**
	 * Metodo que verifica si esta activado la Conexion Externa al Sistema Comercial mediante la 
	 * Parametrizacion en la tabla respectiva
	 * @param codigoPais
	 * @param codigoEmpresa
	 * @return
	 */
	private boolean verificaConexionExternaSistemaComercial(String codigoPais, String codigoEmpresa) {
		ParametroCursoCapacitacion parametro = new ParametroCursoCapacitacion();
		parametro.setCodigoPais(codigoPais);
		parametro.setCodigoEmpresa(codigoEmpresa);
		parametro.setCodigoPrograma(Constants.EDU_PARAMETROS_PROGRAMA_LBEL);
		parametro = mantenimientoEDUGenericoService.getParametroCurso(parametro);
		
		if (parametro != null) {
			String conexionExterna = parametro.getIndicadorConexionExterna();
			String envioProgramadas= parametro.getIndicadorEnvioProgramadas();
			if (StringUtils.isNotBlank(conexionExterna) && (Constants.EDU_CONEXION_EXTERNA_ACTIVADO.equals(conexionExterna))
					&& (Constants.EDU_INDICADOR_ENVIO_PROGRAMADAS_SI.equals(envioProgramadas))) {
				return true;
			}
		}
		return false;
	}


	public Integer getCantidadPlanillasRegion(Map criteria) {
		return this.procesoEDUGenerarPlanillaProgramacionDAO.getCantidadPlanillasRegion(criteria);
	}
		
}
