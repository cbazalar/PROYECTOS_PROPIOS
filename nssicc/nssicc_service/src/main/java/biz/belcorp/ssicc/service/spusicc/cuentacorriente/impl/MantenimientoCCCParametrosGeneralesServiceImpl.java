package biz.belcorp.ssicc.service.spusicc.cuentacorriente.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.MantenimientoCCCParametrosGeneralesDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.MantenimientoCCCParametrosGeneralesService;

/**
 * Service que controla el Mantenimiento de la Liquidacion de Lote Bancario
 *  
 * <p>
 * <a href="MantenimientoCCCParametrosGeneralesServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:aoviedo@sigcomt.com"></a>
 */
@Service("spusicc.mantenimientoCCCParametrosGeneralesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoCCCParametrosGeneralesServiceImpl extends BaseService implements MantenimientoCCCParametrosGeneralesService {

	@Resource(name = "spusicc.mantenimientoCCCParametrosGeneralesDAO")
	private MantenimientoCCCParametrosGeneralesDAO mantenimientoCCCParametrosGeneralesDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.cuentacorriente.MantenimientoCCCParametrosGeneralesService#getParametroGeneralByCriteria(java.util.Map)
	 */
	@Override
	public List getParametroGeneralByCriteria(Map criteria) {
		return mantenimientoCCCParametrosGeneralesDAO.getParametroGeneralByCriteria(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.cuentacorriente.MantenimientoCCCParametrosGeneralesService#insertParametroGeneral(java.util.Map)
	 */
	@Override
	public void insertParametroGeneral(Map criteria) {
		mantenimientoCCCParametrosGeneralesDAO.insertParametroGeneral(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.cuentacorriente.MantenimientoCCCParametrosGeneralesService#updateParametroGeneral(java.util.Map)
	 */
	@Override
	public void updateParametroGeneral(Map criteria) {
		mantenimientoCCCParametrosGeneralesDAO.updateParametroGeneral(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.cuentacorriente.MantenimientoCCCParametrosGeneralesService#deleteParametroGeneral(java.util.Map)
	 */
	@Override
	public void deleteParametroGeneral(Map criteria) {
		mantenimientoCCCParametrosGeneralesDAO.deleteParametroGeneral(criteria);
	}

	/**
	 * @return the mantenimientoCCCParametrosGeneralesDAO
	 */
	public MantenimientoCCCParametrosGeneralesDAO getMantenimientoCCCParametrosGeneralesDAO() {
		return mantenimientoCCCParametrosGeneralesDAO;
	}

	/**
	 * @param mantenimientoCCCParametrosGeneralesDAO the mantenimientoCCCParametrosGeneralesDAO to set
	 */
	public void setMantenimientoCCCParametrosGeneralesDAO(MantenimientoCCCParametrosGeneralesDAO mantenimientoCCCParametrosGeneralesDAO) {
		this.mantenimientoCCCParametrosGeneralesDAO = mantenimientoCCCParametrosGeneralesDAO;
	}
}