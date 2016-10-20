package biz.belcorp.ssicc.service.edu.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.ProcesoEDUGenerarPlanillaProgramacionDAO;
import biz.belcorp.ssicc.dao.edu.model.ParametroProceso;
import biz.belcorp.ssicc.dao.edu.model.RegionCursoCapacitacion;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUCursoCapacitacionService;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUGenericoService;
import biz.belcorp.ssicc.service.edu.ProcesoEDUActualizacionStatusConsultoraService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * @author <a href="mailto:sbuchelli@belcorp.biz">Segio Buchelli Silva</a>
 *
 */
@Service("edu.procesoEDUActualizacionStatusConsultoraService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoEDUActualizacionStatusConsultoraServiceImpl extends BaseService  
		implements ProcesoEDUActualizacionStatusConsultoraService	{

	@Resource(name="edu.procesoEDUGenerarPlanillaProgramacionDAO")
	ProcesoEDUGenerarPlanillaProgramacionDAO procesoEDUGenerarPlanillaProgramacionDAO;
	
	@Resource(name="edu.mantenimientoEDUCursoCapacitacionService")
	MantenimientoEDUCursoCapacitacionService mantenimientoEDUCursoCapacitacionService;
	
	@Resource(name="edu.mantenimientoEDUGenericoService")
	MantenimientoEDUGenericoService mantenimientoEDUGenericoService;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUActualizarPlanillaProgramacionService#executeActualizarPlanillaProgramacion(java.lang.String, java.util.Map)
	 */
	public void executeActualizacionStatusConsultora(Map params) throws Exception {
		
		this.obtenerListaRegiones(params);
		List regionList = (List) params.get("regionList");
		
			String codigoProceso = Constants.EDU_PARAM_PROC_REGION;
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
			params.put("codigoParam",Constants.EDU_PARAM_PROC_REGION);
			//actualizacion status consultora
			procesoEDUGenerarPlanillaProgramacionDAO.executeActualizacionStatusConsultora(params);
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
