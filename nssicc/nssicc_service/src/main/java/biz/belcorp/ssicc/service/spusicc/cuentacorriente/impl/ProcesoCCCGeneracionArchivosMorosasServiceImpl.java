/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.cuentacorriente.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCGeneracionArchivosMorosasDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCGeneracionArchivosMorosasService;

/**
 * @author sguerra
 *
 */
@Service("spusicc.procesoCCCGeneracionArchivosMorosasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCCCGeneracionArchivosMorosasServiceImpl extends BaseService implements ProcesoCCCGeneracionArchivosMorosasService {

	@Resource(name = "spusicc.procesoCCCGeneracionArchivosMorosasDAO")
	ProcesoCCCGeneracionArchivosMorosasDAO procesoCCCGeneracionArchivosMorosasDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ProcesoCCCGeneracionArchivosMorosasService#executeGeneracionArchivosMorosas(java.util.Map)
	 */
	public void executeGeneracionArchivosMorosas(Map criteria) {
		procesoCCCGeneracionArchivosMorosasDAO.executeGeneracionArchivosMorosas(criteria);
	}

}
