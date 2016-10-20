package biz.belcorp.ssicc.service.spusicc.let.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.let.MantenimientoLETBonosCampaniaDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.let.MantenimientoLETBonosCampaniaService;

/**
 * @author Jesse James Rios Franco
 *
 */
@Service("spusicc.mantenimientoLETBonosCampaniaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoLETBonosCampaniaServiceImpl extends BaseService implements MantenimientoLETBonosCampaniaService{
	
	@Resource(name="spusicc.mantenimientoLETBonosCampaniaDAO")
	private MantenimientoLETBonosCampaniaDAO mantenimientoLETBonosCampaniaDAO;



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETBonosCampaniaService#getBonoCampaniaList(java.util.Map)
	 */
	public List getBonoCampaniaList(Map criteria) {
		return mantenimientoLETBonosCampaniaDAO.getBonoCampaniaList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETBonosCampaniaService#getValidaBonoCampaniaExiste(java.util.Map)
	 */
	public int getValidaBonoCampaniaExiste(Map criteria) {
		return mantenimientoLETBonosCampaniaDAO.getValidaBonoCampaniaExiste(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETBonosCampaniaService#deleteBonoCampania(java.util.Map)
	 */
	public void deleteBonoCampania(Map criteria) {
		mantenimientoLETBonosCampaniaDAO.deleteBonoCampania(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETBonosCampaniaService#insertBonoCampania(java.util.Map)
	 */
	public void insertBonoCampania(Map criteria) {
		mantenimientoLETBonosCampaniaDAO.insertBonoCampania(criteria);
	}
}