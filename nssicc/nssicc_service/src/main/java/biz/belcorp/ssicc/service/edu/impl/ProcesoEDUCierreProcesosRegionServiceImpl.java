package biz.belcorp.ssicc.service.edu.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.ProcesoEDUCierreProcesosRegionDAO;
import biz.belcorp.ssicc.dao.edu.model.ParametroCursoCapacitacion;
import biz.belcorp.ssicc.dao.edu.model.RegionCursoCapacitacion;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUCursoCapacitacionService;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUGenericoService;
import biz.belcorp.ssicc.service.edu.ProcesoEDUCerrarCursosVigentesService;
import biz.belcorp.ssicc.service.edu.ProcesoEDUCierreProcesosRegionService;
import biz.belcorp.ssicc.service.edu.ProcesoEDUComercialService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 *
 */
@Service("edu.procesoEDUCierreProcesosRegionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoEDUCierreProcesosRegionServiceImpl extends BaseService  
		implements ProcesoEDUCierreProcesosRegionService	{
	
	@Resource(name="edu.procesoEDUCerrarCursosVigentesService")
	ProcesoEDUCerrarCursosVigentesService    procesoEDUCerrarCursosVigentesService;
	
	@Resource(name="edu.procesoEDUCierreProcesosRegionDAO")
	ProcesoEDUCierreProcesosRegionDAO        procesoEDUCierreProcesosRegionDAO;
	
	@Resource(name="edu.procesoEDUComercialService")
	ProcesoEDUComercialService				 procesoEDUComercialService;
	
	@Resource(name="edu.mantenimientoEDUGenericoService")
	MantenimientoEDUGenericoService          mantenimientoEDUGenericoService;
	
	@Resource(name="edu.mantenimientoEDUCursoCapacitacionService")
	MantenimientoEDUCursoCapacitacionService mantenimientoEDUCursoCapacitacionService;
		
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUCierreProcesosRegionService#executeCierreProcesosRegion(java.util.Map)
	 */
	public void executeCierreProcesosRegion(Map params) throws Exception {

		/* Obteniendo parametros del Pais y Empresa */
		String codigoPais = (String) params.get("codigoPais");
		String codigoEmpresa = (String) params.get("codigoEmpresa");
		this.obtenerListaRegiones(params);
		ParametroCursoCapacitacion parametro = new ParametroCursoCapacitacion();
		parametro.setCodigoPais(codigoPais);
		parametro.setCodigoEmpresa(codigoEmpresa);
		parametro.setCodigoPrograma(Constants.EDU_PARAMETROS_PROGRAMA_LBEL);
		parametro =	mantenimientoEDUGenericoService.getParametroCurso(parametro);
		String indicadorStatusConsultora = parametro.getIndicadorStatusConsultora();
		params.put("indicadorStatusConsultora", indicadorStatusConsultora);
		
		
		procesoEDUCerrarCursosVigentesService.executeCerrarCursosVigentes(params);
		procesoEDUCierreProcesosRegionDAO.executeCerrarCronogramaDictado(params);
		procesoEDUCierreProcesosRegionDAO.executePasarConsultorasProgramadaAPendiente(params);
		
		/* Verificando Conexion Externa */
		if (mantenimientoEDUGenericoService.verificaConexionExternaSistemaComercial(codigoPais, codigoEmpresa)) {
			
			/* Envio de Status Consultora */
			if (Constants.EDU_INDICADOR_STATUS_CONSULTORA_SI.equals(indicadorStatusConsultora)) {
				procesoEDUCierreProcesosRegionDAO.executeListaStatusConsultora(params);			
				List lista  = procesoEDUCierreProcesosRegionDAO.getListaStatusConsultora(params);
				procesoEDUComercialService.insertEnvioStatusConsultora(codigoPais, params, lista);
			}	
			/* Limpiando Cronograma Dictado */
			procesoEDUComercialService.limpiarCronogramaDictado(codigoPais, params);
		}
		
//		if(Constants.NUMERO_UNO.equals(indicadorPlanillaNoProcesada))
//			procesoEDUCierreProcesosRegionDAO.registrarPlanillaNoProcesadas(params);
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
			if ("Todos".equals(tempo)) {
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
	
	
}
