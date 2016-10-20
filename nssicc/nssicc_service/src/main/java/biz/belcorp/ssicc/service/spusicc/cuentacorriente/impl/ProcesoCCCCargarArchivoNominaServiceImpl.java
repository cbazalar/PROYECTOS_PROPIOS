/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.cuentacorriente.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCCargarArchivoNominaDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCCargarArchivoNominaService;

/**
 * @author ghuertasa
 *
 */
@Service("spusicc.procesoCCCCargarArchivoNominaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCCCCargarArchivoNominaServiceImpl extends BaseService implements ProcesoCCCCargarArchivoNominaService {

	@Resource(name = "spusicc.procesoCCCCargarArchivoNominaDAO")
	ProcesoCCCCargarArchivoNominaDAO procesoCCCCargarArchivoNominaDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ProcesoCCCCargarArchivoNominaService#executeCargarArchivoNomina(java.util.Map)
	 */
	public void executeCargarArchivoNomina(Map criteria) {
		procesoCCCCargarArchivoNominaDAO.executeProcesoCCCCargarArchivoNominaFTP();
		procesoCCCCargarArchivoNominaDAO.executeProcesoCCCCargarArchivoNominaMAIL();
	}

}
