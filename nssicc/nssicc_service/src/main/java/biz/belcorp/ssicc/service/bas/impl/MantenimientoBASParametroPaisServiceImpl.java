package biz.belcorp.ssicc.service.bas.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.bas.MantenimientoBASParametroPaisDAO;
import biz.belcorp.ssicc.dao.bas.model.BASParametroPais;
import biz.belcorp.ssicc.service.bas.MantenimientoBASParametroPaisService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * @author yrivas
 *
 */
@Service("bas.mantenimientoBASParametroPaisService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoBASParametroPaisServiceImpl extends BaseService implements MantenimientoBASParametroPaisService {

	@Resource(name="bas.mantenimientoBASParametroPaisDAO")
	MantenimientoBASParametroPaisDAO mantenimientoBASParametroPaisDAO;
	

	
	
	/**
	 * @return the mantenimientoBASParametroPaisDAO
	 */
	public MantenimientoBASParametroPaisDAO getMantenimientoBASParametroPaisDAO() {
		return mantenimientoBASParametroPaisDAO;
	}

	/**
	 * @param mantenimientoBASParametroPaisDAO the mantenimientoBASParametroPaisDAO to set
	 */
	public void setMantenimientoBASParametroPaisDAO(
			MantenimientoBASParametroPaisDAO mantenimientoBASParametroPaisDAO) {
		this.mantenimientoBASParametroPaisDAO = mantenimientoBASParametroPaisDAO;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService##getParametroPais(biz.belcorp.ssicc.bas.model.BASParametroPais)
	 */
	public List getParametroPais(BASParametroPais basParametroPais) {
		return mantenimientoBASParametroPaisDAO.getParametroPais(basParametroPais);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.bas.dao.MantenimientoBASParametroPaisDAO#insertParametroPais(biz.belcorp.ssicc.bas.model.BASParametroPais)
	 */
	public void insertParametroPais(BASParametroPais basParametroPais) {
		mantenimientoBASParametroPaisDAO.insertParametroPais(basParametroPais);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.bas.dao.MantenimientoBASParametroPaisDAO#deleteParametroPais(biz.belcorp.ssicc.bas.model.BASParametroPais)
	 */
	public void deleteParametroPais(BASParametroPais basParametroPais) {
		mantenimientoBASParametroPaisDAO.insertHistoricoParametroPais(basParametroPais);
		mantenimientoBASParametroPaisDAO.deleteParametroPais(basParametroPais);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.bas.dao.MantenimientoBASParametroPaisDAO#updateParametroPais(biz.belcorp.ssicc.bas.model.BASParametroPais)
	 */
	public void updateParametroPais(BASParametroPais basParametroPais) {
		mantenimientoBASParametroPaisDAO.updateParametroPais(basParametroPais);
	}

	
	
   

}
