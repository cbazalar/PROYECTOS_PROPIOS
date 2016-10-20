package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.inc.MantenimientoINCConfiguracionFaltanteDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCConfiguracionFaltanteService;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Service("spusicc.mantenimientoINCConfiguracionFaltanteService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoINCConfiguracionFaltanteServiceImpl extends BaseService implements
	MantenimientoINCConfiguracionFaltanteService {

	@Resource(name="spusicc.mantenimientoINCConfiguracionFaltanteDAO")
	MantenimientoINCConfiguracionFaltanteDAO mantenimientoINCConfiguracionFaltanteDAO;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionFaltanteService#getConcursosFaltante(java.util.Map)
	 */
	public List getConcursosFaltante(Map criteria) {
		return mantenimientoINCConfiguracionFaltanteDAO.getConcursosFaltante(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionFaltanteService#getRegionesFaltante(java.util.Map)
	 */
	public List getRegionesFaltante(Map criteria) {
		return mantenimientoINCConfiguracionFaltanteDAO.getRegionesFaltante(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionFaltanteService#getFaltantes(java.util.Map)
	 */
	public List getFaltantes(Map criteria) {
		return mantenimientoINCConfiguracionFaltanteDAO.getFaltantes(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionFaltanteService#updateFaltantes(java.util.List)
	 */
	public void updateFaltantes(List list) throws Exception {
		mantenimientoINCConfiguracionFaltanteDAO.updateFaltantes(list);
	}

}