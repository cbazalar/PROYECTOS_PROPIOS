package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.inc.MantenimientoINCCampanaDespachoDiferidaDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCCampanaDespachoDiferidaService;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Service("spusicc.mantenimientoINCCampanaDespachoDiferidaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoINCCampanaDespachoDiferidaServiceImpl extends BaseService implements
	MantenimientoINCCampanaDespachoDiferidaService {

	@Resource(name="spusicc.mantenimientoINCCampanaDespachoDiferidaDAO")
	MantenimientoINCCampanaDespachoDiferidaDAO mantenimientoINCCampanaDespachoDiferidaDAO;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoCampanaDespachoDiferidaService#getListConcursoDespachoDiferido()
	 */
	public List getListConcursoDespachoDiferido() {
		return mantenimientoINCCampanaDespachoDiferidaDAO.getListConcursoDespachoDiferido();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoCampanaDespachoDiferidaService#updateNivelDespachoDiferido(java.util.Map)
	 */
	public void updateNivelDespachoDiferido(Map criteria) {
		boolean existe = mantenimientoINCCampanaDespachoDiferidaDAO.getExistePeriodoNivelDespachoDiferido(criteria);
		
		if(existe)
			mantenimientoINCCampanaDespachoDiferidaDAO.updateNivelDespachoDiferido(criteria);
		else
			mantenimientoINCCampanaDespachoDiferidaDAO.insertNivelDespachoDiferido(criteria);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCCampanaDespachoDiferidaService#deleteNivelDespachoDiferido(java.util.Map)
	 */
	public void deleteNivelDespachoDiferido(Map criteria) {
		mantenimientoINCCampanaDespachoDiferidaDAO.deleteNivelDespachoDiferido(criteria);
	}
	
}
