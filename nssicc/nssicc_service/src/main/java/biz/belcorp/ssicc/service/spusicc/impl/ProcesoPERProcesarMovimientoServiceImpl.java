/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazDAO;
import biz.belcorp.ssicc.dao.spusicc.ProcesoPERProcesarMovimientoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ProcesoPERProcesarMovimientoService;

/**
 * @author cbazalar
 *
 */
@Service("spusicc.procesoPERProcesarMovimientoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoPERProcesarMovimientoServiceImpl extends BaseService implements ProcesoPERProcesarMovimientoService {

	@Resource(name="spusicc.procesoPERProcesarMovimientoDAO")
	private ProcesoPERProcesarMovimientoDAO procesoPERProcesarMovimientoDAO;
	
	@Resource(name="sisicc.interfazDAO")
	private InterfazDAO interfazDAO;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.ProcesoPERProcesarMovimientoService#executeCargoAbonoDirecto(java.util.Map)
	 */
	public void executeProcesarMovimiento(Map criteria) {
		procesoPERProcesarMovimientoDAO.executeProcesarMovimiento(criteria);
	}
}
