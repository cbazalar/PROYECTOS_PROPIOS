package biz.belcorp.ssicc.service.spusicc.let.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.let.MantenimientoLETPremioConcursoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.let.MantenimientoLETPremioConcursoService;

/**
 * Clase de la implementacin de la capa BO (Bussiness Object)
 * 
 * <p>
 * <a href="MantenimientoLETPremioConcursoServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 *         
 */
@Service("spusicc.mantenimientoLETPremioConcursoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoLETPremioConcursoServiceImpl extends BaseService 
	implements MantenimientoLETPremioConcursoService
{

	@Resource(name="spusicc.mantenimientoLETPremioConcursoDAO")
	private MantenimientoLETPremioConcursoDAO mantenimientoLETPremioConcursoDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.MantenimientoLIDPremioCampaniaService#getPremioCampaniaList(java.util.Map)
	 */
	public List getPremioConcursoList(Map criteria) {
		log.info("Entro a MantenimientoLETPremioConcursoServiceImpl - getPremioConcursoList(java.util.Map)");
		List lista = mantenimientoLETPremioConcursoDAO.getPremioConcursoList(criteria);
		log.info("Salio a MantenimientoLETPremioConcursoServiceImpl - getPremioConcursoList(java.util.Map) - Resultado:"+lista.size());
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.MantenimientoLIDPremioCampaniaService#deletePremioCampania(java.util.Map)
	 */
	public void deletePremioConcurso(Map criteria) {
		log.info("Entro a MantenimientoLETPremioConcursoServiceImpl - deletePremioConcurso(java.util.Map)");
		mantenimientoLETPremioConcursoDAO.deletePremioConcurso(criteria);
		log.info("Salio a MantenimientoLETPremioConcursoServiceImpl - deletePremioConcurso(java.util.Map)");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.MantenimientoLIDPremioCampaniaService#insertPremioCampania(java.util.Map)
	 */
	public void insertPremioConcurso(Map criteria) {
		log.info("Entro a MantenimientoLETPremioConcursoServiceImpl - insertPremioConcurso(java.util.Map)");
		mantenimientoLETPremioConcursoDAO.insertPremioConcurso(criteria);
		log.info("Salio a MantenimientoLETPremioConcursoServiceImpl - insertPremioConcurso(java.util.Map)");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.MantenimientoLIDPremioConcursoService#getNivelConcursoRangoList(java.util.Map)
	 */
	public List getNivelConcursoRangoList(Map criteria) {
		log.info("Entro a MantenimientoLETPremioConcursoServiceImpl - getNivelConcursoRangoList(java.util.Map)");
		List lista = mantenimientoLETPremioConcursoDAO.getNivelConcursoRangoList(criteria);
		log.info("Salio a MantenimientoLETPremioConcursoServiceImpl - getNivelConcursoRangoList(java.util.Map) - Resultado:"+lista.size());
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETPremioConcursoService#getValidaPremioConcursoExiste(java.util.Map)
	 */
	public String getValidaPremioConcursoExiste(Map criteria) {
		log.info("Entro a MantenimientoLETPremioConcursoServiceImpl - getValidaPremioConcursoExiste(java.util.Map)");
		String resultado = mantenimientoLETPremioConcursoDAO.getValidaPremioConcursoExiste(criteria);
		log.info("Salio a MantenimientoLETPremioConcursoServiceImpl - getValidaPremioConcursoExiste(java.util.Map) - Resultado:"+resultado);
		return resultado;
	}


	
}
