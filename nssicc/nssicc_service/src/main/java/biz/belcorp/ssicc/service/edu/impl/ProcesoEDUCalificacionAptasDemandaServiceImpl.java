package biz.belcorp.ssicc.service.edu.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.ProcesoEDUCalificacionAptasDemandaDAO;
import biz.belcorp.ssicc.dao.edu.ProcesoEDUGenerarPlanillaProgramacionDAO;
import biz.belcorp.ssicc.dao.edu.model.CalificacionAptaDemandaCampanha;
import biz.belcorp.ssicc.dao.edu.model.CalificacionAptaDemandaCliente;
import biz.belcorp.ssicc.dao.edu.model.CalificacionAptaDemandaRegion;
import biz.belcorp.ssicc.dao.edu.model.CalificacionAptaDemandaZona;
import biz.belcorp.ssicc.dao.edu.model.CalificacionAptasDemandaCurso;
import biz.belcorp.ssicc.dao.edu.model.ParametroProceso;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.edu.ProcesoEDUCalificacionAptasDemandaService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * @author peextrvela
 *
 */
@Service("edu.procesoEDUCalificacionAptasDemandaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoEDUCalificacionAptasDemandaServiceImpl extends 	BaseService implements ProcesoEDUCalificacionAptasDemandaService{
	
	@Resource(name="edu.procesoEDUCalificacionAptasDemandaDAO")
	ProcesoEDUCalificacionAptasDemandaDAO procesoEDUCalificacionAptasDemandaDAO;
	
	@Resource(name="edu.procesoEDUGenerarPlanillaProgramacionDAO")
	ProcesoEDUGenerarPlanillaProgramacionDAO procesoEDUGenerarPlanillaProgramacionDAO;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUCalificacionAptasDemandaService#getCalificacionAptaDemandaCampanha(biz.belcorp.ssicc.edu.dao.model.CalificacionAptaDemandaCampanha)
	 */
	public List getCalificacionAptaDemandaCampanha(CalificacionAptaDemandaCampanha calificacionAptaDemandaCampanha) {
		// TODO Auto-generated method stub
		return procesoEDUCalificacionAptasDemandaDAO.getCalificacionAptaDemandaCampanha(calificacionAptaDemandaCampanha);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUCalificacionAptasDemandaService#getCalificacionAptaDemandaCliente(biz.belcorp.ssicc.edu.dao.model.CalificacionAptaDemandaCliente)
	 */
	public List getCalificacionAptaDemandaCliente(CalificacionAptaDemandaCliente calificacionAptaDemandaCliente) {
		// TODO Auto-generated method stub
		return procesoEDUCalificacionAptasDemandaDAO.getCalificacionAptaDemandaCliente(calificacionAptaDemandaCliente);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUCalificacionAptasDemandaService#getCalificacionAptaDemandaRegion(biz.belcorp.ssicc.edu.dao.model.CalificacionAptaDemandaRegion)
	 */
	public List getCalificacionAptaDemandaRegion(CalificacionAptaDemandaRegion calificacionAptaDemandaRegion) {
		// TODO Auto-generated method stub
		return procesoEDUCalificacionAptasDemandaDAO.getCalificacionAptaDemandaRegion(calificacionAptaDemandaRegion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUCalificacionAptasDemandaService#getCalificacionAptaDemandaZona(biz.belcorp.ssicc.edu.dao.model.CalificacionAptaDemandaZona)
	 */
	public List getCalificacionAptaDemandaZona(CalificacionAptaDemandaZona calificacionAptaDemandaZona) {
		// TODO Auto-generated method stub
		return procesoEDUCalificacionAptasDemandaDAO.getCalificacionAptaDemandaZona(calificacionAptaDemandaZona);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUCalificacionAptasDemandaService#getCalificacionAptasDemandaCurso(biz.belcorp.ssicc.edu.dao.model.CalificacionAptasDemandaCurso)
	 */
	public List getCalificacionAptasDemandaCurso(CalificacionAptasDemandaCurso calificacionAptasCurso) {
		// TODO Auto-generated method stub
		return procesoEDUCalificacionAptasDemandaDAO.getCalificacionAptasDemandaCurso(calificacionAptasCurso);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUCalificacionAptasDemandaService#insertCalificacionAptaDemandaCampanha(biz.belcorp.ssicc.edu.dao.model.CalificacionAptaDemandaCampanha)
	 */
	public void insertCalificacionAptaDemandaCampanha(CalificacionAptaDemandaCampanha calificacionAptaDemandaCampanha,Usuario usuario) {
		// TODO Auto-generated method stub
		procesoEDUCalificacionAptasDemandaDAO.insertCalificacionAptaDemandaCampanha(calificacionAptaDemandaCampanha,usuario);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUCalificacionAptasDemandaService#insertCalificacionAptaDemandaCliente(biz.belcorp.ssicc.edu.dao.model.CalificacionAptaDemandaCliente)
	 */
	public void insertCalificacionAptaDemandaCliente(CalificacionAptaDemandaCliente calificacionAptaDemandaCliente,Usuario usuario) {
		// TODO Auto-generated method stub
		procesoEDUCalificacionAptasDemandaDAO.insertCalificacionAptaDemandaCliente(calificacionAptaDemandaCliente,usuario);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUCalificacionAptasDemandaService#insertCalificacionAptaDemandaRegion(biz.belcorp.ssicc.edu.dao.model.CalificacionAptaDemandaRegion)
	 */
	public void insertCalificacionAptaDemandaRegion(CalificacionAptaDemandaRegion calificacionAptaDemandaRegion,Usuario usuario) {
		// TODO Auto-generated method stub
		procesoEDUCalificacionAptasDemandaDAO.insertCalificacionAptaDemandaRegion(calificacionAptaDemandaRegion,usuario);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUCalificacionAptasDemandaService#insertCalificacionAptaDemandaZona(biz.belcorp.ssicc.edu.dao.model.CalificacionAptaDemandaZona)
	 */
	public void insertCalificacionAptaDemandaZona(CalificacionAptaDemandaZona calificacionAptaDemandaZona,Usuario usuario) {
		// TODO Auto-generated method stub
		procesoEDUCalificacionAptasDemandaDAO.insertCalificacionAptaDemandaZona(calificacionAptaDemandaZona,usuario);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUCalificacionAptasDemandaService#removeCalificacionAptaDemandaCampanha(biz.belcorp.ssicc.edu.dao.model.CalificacionAptaDemandaCampanha)
	 */
	public void removeCalificacionAptaDemandaCampanha(CalificacionAptaDemandaCampanha calificacionAptaDemandaCampanha) {
		// TODO Auto-generated method stub
		procesoEDUCalificacionAptasDemandaDAO.removeCalificacionAptaDemandaCampanha(calificacionAptaDemandaCampanha);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUCalificacionAptasDemandaService#removeCalificacionAptaDemandaCliente(biz.belcorp.ssicc.edu.dao.model.CalificacionAptaDemandaCliente)
	 */
	public void removeCalificacionAptaDemandaCliente(CalificacionAptaDemandaCliente calificacionAptaDemandaCliente) {
		// TODO Auto-generated method stub
		procesoEDUCalificacionAptasDemandaDAO.removeCalificacionAptaDemandaCliente(calificacionAptaDemandaCliente);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUCalificacionAptasDemandaService#removeCalificacionAptaDemandaRegion(biz.belcorp.ssicc.edu.dao.model.CalificacionAptaDemandaRegion)
	 */
	public void removeCalificacionAptaDemandaRegion(CalificacionAptaDemandaRegion calificacionAptaDemandaRegion) {
		// TODO Auto-generated method stub
		procesoEDUCalificacionAptasDemandaDAO.removeCalificacionAptaDemandaRegion(calificacionAptaDemandaRegion);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUCalificacionAptasDemandaService#removeCalificacionAptaDemandaZona(biz.belcorp.ssicc.edu.dao.model.CalificacionAptaDemandaZona)
	 */
	public void removeCalificacionAptaDemandaZona(CalificacionAptaDemandaZona calificacionAptaDemandaZona) {
		// TODO Auto-generated method stub
		procesoEDUCalificacionAptasDemandaDAO.removeCalificacionAptaDemandaZona(calificacionAptaDemandaZona);		
	}

	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUCalificacionAptasDemandaService#updateCalificacionAptaDemanda(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.List, java.util.List, java.util.List, java.util.List)
	 */
	public void updateCalificacionAptaDemanda(CalificacionAptasDemandaCurso calificacionAptasDemandaCurso,Usuario usuario, 
			List listaClientes, List listaCampanhas, List listaRegiones, List listaZonas) {
		// TODO Auto-generated method stub
		/*Empezamos por los Clientes*/
		CalificacionAptaDemandaCliente calificacionAptaDemandaCliente = new CalificacionAptaDemandaCliente();
		calificacionAptaDemandaCliente.setCodigoPais(calificacionAptasDemandaCurso.getCodigoPais());
		calificacionAptaDemandaCliente.setCodigoEmpresa(calificacionAptasDemandaCurso.getCodigoEmpresa());
		calificacionAptaDemandaCliente.setCodigoCurso(calificacionAptasDemandaCurso.getCodigoCurso());
		calificacionAptaDemandaCliente.setCampanhaCalificacion(calificacionAptasDemandaCurso.getCampanhaCalificacion());
		procesoEDUCalificacionAptasDemandaDAO.removeCalificacionAptaDemandaCliente(calificacionAptaDemandaCliente);
		for (int i=0;i<listaClientes.size();i++){
			calificacionAptaDemandaCliente = new CalificacionAptaDemandaCliente();
			calificacionAptaDemandaCliente = (CalificacionAptaDemandaCliente) listaClientes.get(i);
			procesoEDUCalificacionAptasDemandaDAO.insertCalificacionAptaDemandaCliente(calificacionAptaDemandaCliente,usuario);			
		}
		/*Campaas*/
		CalificacionAptaDemandaCampanha calificacionAptaDemandaCampanha = new CalificacionAptaDemandaCampanha();
		calificacionAptaDemandaCampanha.setCodigoPais(calificacionAptasDemandaCurso.getCodigoPais());
		calificacionAptaDemandaCampanha.setCodigoEmpresa(calificacionAptasDemandaCurso.getCodigoEmpresa());
		calificacionAptaDemandaCampanha.setCodigoCurso(calificacionAptasDemandaCurso.getCodigoCurso());
		calificacionAptaDemandaCampanha.setCampanhaCalificacion(calificacionAptasDemandaCurso.getCampanhaCalificacion());
		procesoEDUCalificacionAptasDemandaDAO.removeCalificacionAptaDemandaCampanha(calificacionAptaDemandaCampanha);
		for(int i=0;i<listaCampanhas.size();i++){
			calificacionAptaDemandaCampanha = new CalificacionAptaDemandaCampanha();
			calificacionAptaDemandaCampanha = (CalificacionAptaDemandaCampanha) listaCampanhas.get(i);
			procesoEDUCalificacionAptasDemandaDAO.insertCalificacionAptaDemandaCampanha(calificacionAptaDemandaCampanha,usuario);
		}
		/*Regiones*/
		CalificacionAptaDemandaRegion calificacionAptaDemandaRegion = new CalificacionAptaDemandaRegion();
		calificacionAptaDemandaRegion.setCodigoPais(calificacionAptasDemandaCurso.getCodigoPais());
		calificacionAptaDemandaRegion.setCodigoEmpresa(calificacionAptasDemandaCurso.getCodigoEmpresa());
		calificacionAptaDemandaRegion.setCodigoCurso(calificacionAptasDemandaCurso.getCodigoCurso());
		calificacionAptaDemandaRegion.setCampanhaCalificacion(calificacionAptasDemandaCurso.getCampanhaCalificacion());
		procesoEDUCalificacionAptasDemandaDAO.removeCalificacionAptaDemandaRegion(calificacionAptaDemandaRegion);
		for(int i=0;i<listaRegiones.size();i++){
			calificacionAptaDemandaRegion = new CalificacionAptaDemandaRegion();
			calificacionAptaDemandaRegion = (CalificacionAptaDemandaRegion) listaRegiones.get(i);
			procesoEDUCalificacionAptasDemandaDAO.insertCalificacionAptaDemandaRegion(calificacionAptaDemandaRegion,usuario);
		}
		/*Zonas*/
		CalificacionAptaDemandaZona calificacionAptaDemandaZona = new CalificacionAptaDemandaZona();
		calificacionAptaDemandaZona.setCodigoPais(calificacionAptasDemandaCurso.getCodigoPais());
		calificacionAptaDemandaZona.setCodigoEmpresa(calificacionAptasDemandaCurso.getCodigoEmpresa());
		calificacionAptaDemandaZona.setCodigoCurso(calificacionAptasDemandaCurso.getCodigoCurso());
		calificacionAptaDemandaZona.setCampanhaCalificacion(calificacionAptasDemandaCurso.getCampanhaCalificacion());
		procesoEDUCalificacionAptasDemandaDAO.removeCalificacionAptaDemandaZona(calificacionAptaDemandaZona);
		for(int i=0;i<listaZonas.size();i++){
			calificacionAptaDemandaZona = new CalificacionAptaDemandaZona();
			calificacionAptaDemandaZona = (CalificacionAptaDemandaZona) listaZonas.get(i);
			procesoEDUCalificacionAptasDemandaDAO.insertCalificacionAptaDemandaZona(calificacionAptaDemandaZona,usuario);
		}
		
	}
	
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.edu.service.ProcesoEDUCalificacionAptasDemandaService#executeCalificacionAptasDemanda(java.lang.String, java.util.Map)
     */
    public void executeCalificacionAptasDemanda(String codigoPais, Map params) throws Exception{
        /* Invocando a la calificacin Aptas a Demanda */
		params.put("valorEnvio","N");
		String cursos[] = (String[])params.get("listaCursos");
		if (cursos != null && cursos.length > 0) {
			
			/* Proceso de Calificacion de Aptas a Demanda*/
			for(int i=0; i < cursos.length; i++ ) {
				cursos[i] = cursos[i].trim();
				params.put("codigoPais", codigoPais);
				params.put("codigoCurso", cursos[i]);
				
				/* Invocando Procedimiento Oracle que filtre calificadoras aptas */
				procesoEDUCalificacionAptasDemandaDAO.executeCalificacionAptasDemanda(params);
			}
		}
	}
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.edu.service.ProcesoEDUCalificacionAptasDemandaService#executePrevioCalificacionAptasDemanda(java.lang.String, java.util.Map)
     */
    public List executePrevioCalificacionAptasDemanda(String codigoPais, Map params) throws Exception{
    	/* Insertamos los parametro de Cursos seleccionadas */
    	String cursosList[] = (String[])params.get("listaCursos");
		String codigoProceso = "01";
		
		if (cursosList != null && cursosList.length > 0) {
			ParametroProceso parametroProceso = new ParametroProceso();
			params.put("codigoProceso", codigoProceso);
			params.put("codigoPrograma", Constants.EDU_PARAM_PROC_CURSO);
			for (int i=0;i<cursosList.length;i++){
				parametroProceso = new ParametroProceso();
				String curso = cursosList[i];
				parametroProceso.setCodigoProceso(codigoProceso);
				parametroProceso.setCodigoParametro(Constants.EDU_PARAM_PROC_CURSO);
				parametroProceso.setValorCadenaParametro(curso);
				procesoEDUGenerarPlanillaProgramacionDAO.insertParametroProceso(parametroProceso);
			}
		}
    	
		/* Invocando al proceso previo de calificacin Aptas a Demanda */
		procesoEDUCalificacionAptasDemandaDAO.executePrevioCalificacionAptasDemanda(params);
		
		/* Obteniendo lista de consultoras obtenidas previamente por el proceso Previo de Calificacion a Demanda */
		List resultado = procesoEDUCalificacionAptasDemandaDAO.getPrevioCalificacionAptasDemanda(null);
		return resultado;
	}
    
    	
	
	
}
