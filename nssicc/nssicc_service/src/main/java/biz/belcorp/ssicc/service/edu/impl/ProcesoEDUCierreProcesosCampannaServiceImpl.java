package biz.belcorp.ssicc.service.edu.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.MantenimientoEDUGenericoDAO;
import biz.belcorp.ssicc.dao.edu.ProcesoEDUCierreCampannaDAO;
import biz.belcorp.ssicc.dao.edu.model.ParametroCursoCapacitacion;
import biz.belcorp.ssicc.service.edu.ProcesoEDUActualizarParametrosCursoService;
import biz.belcorp.ssicc.service.edu.ProcesoEDUCierreProcesosCampannaService;
import biz.belcorp.ssicc.service.edu.ProcesoEDUComercialService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 *
 */
@Service("edu.procesoEDUCierreProcesosCampannaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoEDUCierreProcesosCampannaServiceImpl extends BaseService  
		implements ProcesoEDUCierreProcesosCampannaService	{
	
	@Resource(name="edu.procesoEDUActualizarParametrosCursoService")
	ProcesoEDUActualizarParametrosCursoService 	procesoEDUActualizarParametrosCursoService;
	
	@Resource(name="edu.procesoEDUComercialService")
	ProcesoEDUComercialService	procesoEDUComercialService;
	
	@Resource(name="edu.procesoEDUCierreCampannaDAO")
	ProcesoEDUCierreCampannaDAO procesoEDUCierreCampannaDAO;
	
	@Resource(name="edu.mantenimientoEDUGenericoDAO")
	MantenimientoEDUGenericoDAO mantenimientoEDUGenericoDAO;
	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUCierreProcesosCampannaService#executeCierreProcesosCampanna(java.util.Map)
	 */
	public void executeCierreProcesosCampanna(String codigoPais, Map params) throws Exception {
		String codigoEmpresa = (String)params.get("codigoEmpresa");
		ParametroCursoCapacitacion parametro = new ParametroCursoCapacitacion();
		parametro.setCodigoPais(codigoPais);
		parametro.setCodigoEmpresa(codigoEmpresa);
		parametro.setCodigoPrograma(Constants.EDU_PARAMETROS_PROGRAMA_LBEL);
		parametro =	mantenimientoEDUGenericoDAO.getParametroCurso(parametro);
		String indicadorBloqueo = parametro.getIndicadorBloqueo();
		String indicadorPlanillasNoProcesadas = parametro.getIndicadorRegistroPlanillasNoProcesadas();
		params.put("indicadorBloqueo", indicadorBloqueo);
		
		//Insertando en Planilla Consultoras con Pedidos Rezagados
		String indicadorConsultoraRezagada = parametro.getIndicadorConsultoraRezagada();
		if(Constants.NUMERO_UNO.equals(indicadorConsultoraRezagada))
			procesoEDUCierreCampannaDAO.executeProcesoConsultoraRezagadas(params);
		// Actualizando Proceso de Parmetros Cursos	
		procesoEDUActualizarParametrosCursoService.executeActualizarParametrosCurso(params);
		
        // Invocando al Proceso Validar Cursos no Comprados (Compra de Curso con Costo)   
		procesoEDUComercialService.executeProcesoEDURecepcionarCursosNoFacturados(codigoPais, params);
		
		/* Proceso de Bloqueo de consultora */
		if (Constants.EDU_INDICADOR_BLOQUEO_SI.equals(indicadorBloqueo)) {
			procesoEDUCierreCampannaDAO.executeBloqueoConsultoraCampanna(params);		
			List listaHistoricoBloqueo = procesoEDUComercialService.getHistoricoBloqueoConsultoraTemporal(params);
			procesoEDUComercialService.insertBloqueoConsultora(codigoPais, listaHistoricoBloqueo, params);
		}	
		
		if (Constants.NUMERO_UNO.equals(indicadorPlanillasNoProcesadas)) {
			String [] regionList = (String [])params.get("regionList");
			for(int i=0;i<regionList.length;i++){
			 params.put("codigoRegion", regionList[i]);
			 procesoEDUCierreCampannaDAO.executeRegistrarPlanillasNoProcesadas(params);
			}
		}
	}



}
