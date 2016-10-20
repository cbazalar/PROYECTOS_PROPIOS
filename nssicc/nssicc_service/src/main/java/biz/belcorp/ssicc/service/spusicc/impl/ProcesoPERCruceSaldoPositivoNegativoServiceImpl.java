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
import biz.belcorp.ssicc.dao.spusicc.ProcesoPERCruceSaldoPositivoNegativoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ProcesoPERCruceSaldoPositivoNegativoService;

/**
 * @author cbazalar
 *
 */
@Service("spusicc.procesoPERCruceSaldoPositivoNegativoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoPERCruceSaldoPositivoNegativoServiceImpl extends BaseService implements ProcesoPERCruceSaldoPositivoNegativoService {

	@Resource(name="spusicc.procesoPERCruceSaldoPositivoNegativoDAO")
	private ProcesoPERCruceSaldoPositivoNegativoDAO procesoPERCruceSaldoPositivoNegativoDAO;
	
	@Resource(name="sisicc.interfazDAO")
	private InterfazDAO interfazDAO;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.ProcesoPERCruceSaldoPositivoNegativoService#executeCruceSaldoPositivoNegativo(java.util.Map)
	 */
	public void executeCruceSaldoPositivoNegativo(Map criteria) {
		procesoPERCruceSaldoPositivoNegativoDAO.executeCruceSaldoPositivoNegativo(criteria);
		criteria.put("numeroLoteSolicitud", criteria.get("numeroLote"));
	}

}
