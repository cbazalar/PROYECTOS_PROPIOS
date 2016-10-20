/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.cuentacorriente.impl;

  
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCGenerarInformacionDatamartDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCGenerarInformacionDatamartService;

/**
 * @author Jorge Florencio Arias
 *
 */
@Service("spusicc.procesoCCCGenerarInformacionDatamartService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCCCGenerarInformacionDatamartServiceImpl extends BaseService implements ProcesoCCCGenerarInformacionDatamartService {
		
	@Resource(name = "spusicc.procesoCCCGenerarInformacionDatamartDAO")
	ProcesoCCCGenerarInformacionDatamartDAO procesoCCCGenerarInformacionDatamartDAO;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ProcesoCCCGenerarInformacionDatamartService#executeGenerarInformacionDatamart(java.util.Map)
	 */
	public void executeGenerarInformacionDatamart(Map datos){		
		procesoCCCGenerarInformacionDatamartDAO.executeGenerarInformacionDatamart(datos);
	}
	

}
