package biz.belcorp.ssicc.service.spusicc.let.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.let.MantenimientoLETBonosConcursoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.let.MantenimientoLETBonosConcursoService;

/**
 * @author Jesse James Rios Franco
 *
 */
@Service("spusicc.mantenimientoLETBonosConcursoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoLETBonosConcursoServiceImpl extends BaseService implements MantenimientoLETBonosConcursoService{
	
	@Resource(name="spusicc.mantenimientoLETBonosConcursoDAO")
	private MantenimientoLETBonosConcursoDAO mantenimientoLETBonosConcursoDAO;



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETBonosConcursoService#getBonoConcursoList(java.util.Map)
	 */
	public List getBonoConcursoList(Map criteria) {
		return mantenimientoLETBonosConcursoDAO.getBonoConcursoList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETBonosConcursoService#getValidaBonoConcursoExiste(java.util.Map)
	 */
	public int getValidaBonoConcursoExiste(Map criteria) {
		return mantenimientoLETBonosConcursoDAO.getValidaBonoConcursoExiste(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETBonosConcursoService#deleteBonoConcurso(java.util.Map)
	 */
	public void deleteBonoConcurso(Map criteria) {
		mantenimientoLETBonosConcursoDAO.deleteBonoConcurso(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETBonosConcursoService#insertBonoConcurso(java.util.Map)
	 */
	public void insertBonoConcurso(Map criteria) {
		mantenimientoLETBonosConcursoDAO.insertBonoConcurso(criteria);
	}
}