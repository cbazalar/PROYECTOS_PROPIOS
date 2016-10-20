package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.inc.MantenimientoINCBloqueoPremiosDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCBloqueoPremiosService;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Service("spusicc.mantenimientoINCBloqueoPremiosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoINCBloqueoPremiosServiceImpl extends BaseService implements
	MantenimientoINCBloqueoPremiosService {

	@Resource(name="spusicc.mantenimientoINCBloqueoPremiosDAO")
	MantenimientoINCBloqueoPremiosDAO mantenimientoINCBloqueoPremiosDAO;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCBloqueoPremiosService#getConcursosBloqueoPremios(java.util.Map)
	 */
	public List getConcursosBloqueoPremios(Map criteria) {
		return mantenimientoINCBloqueoPremiosDAO.getConcursosBloqueoPremios(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCBloqueoPremiosService#getPremiosxConcurso(java.util.Map)
	 */
	public List getPremiosxConcurso(Map criteria) {
		return mantenimientoINCBloqueoPremiosDAO.getPremiosxConcurso(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCBloqueoPremiosService#getRegionesBloqueoPremios()
	 */
	public List getRegionesBloqueoPremios() {
		return mantenimientoINCBloqueoPremiosDAO.getRegionesBloqueoPremios();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCBloqueoPremiosService#getBloqueoPremios(java.util.Map)
	 */
	public List getBloqueoPremios(Map criteria) {
		return mantenimientoINCBloqueoPremiosDAO.getBloqueoPremios(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCBloqueoPremiosService#insertBloqueoPremios(java.util.Map)
	 */
	public void insertBloqueoPremios(List listaBloqueoPremios) {
		for(int i=0; i<listaBloqueoPremios.size(); i++) {
			Map mapBloqueoPremio = (Map)listaBloqueoPremios.get(i);
			
			mantenimientoINCBloqueoPremiosDAO.insertBloqueoPremios(mapBloqueoPremio);
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCBloqueoPremiosService#existeBloqueoPremio(java.util.Map)
	 */
	public boolean existeBloqueoPremio(Map criteria) {
		return mantenimientoINCBloqueoPremiosDAO.existeBloqueoPremio(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCBloqueoPremiosService#updateBloqueoPremios(java.util.Map)
	 */
	public void updateBloqueoPremios(Map params) {
		mantenimientoINCBloqueoPremiosDAO.updateBloqueoPremios(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCBloqueoPremiosService#deleteBloqueoPremios(java.util.Map)
	 */
	public void deleteBloqueoPremios(Map params) {
		mantenimientoINCBloqueoPremiosDAO.deleteBloqueoPremios(params);
	}

	
}
