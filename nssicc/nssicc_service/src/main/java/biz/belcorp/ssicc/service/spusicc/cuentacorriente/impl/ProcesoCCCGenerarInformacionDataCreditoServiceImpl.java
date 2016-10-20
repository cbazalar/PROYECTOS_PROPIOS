package biz.belcorp.ssicc.service.spusicc.cuentacorriente.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCGenerarInformacionDataCreditoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCGenerarInformacionDataCreditoService;


/**
 * Service que controla la Generacion de la Informacion para Data Credito
 *  
 * <p>
 * <a href="ProcesoCCCGenerarInformacionDataCreditoServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">JFA</a>
 * 
 */
@Service("spusicc.procesoCCCGenerarInformacionDataCreditoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCCCGenerarInformacionDataCreditoServiceImpl extends BaseService implements ProcesoCCCGenerarInformacionDataCreditoService {

	@Resource(name = "spusicc.procesoCCCGenerarInformacionDataCreditoDAO")	
	private ProcesoCCCGenerarInformacionDataCreditoDAO procesoCCCGenerarInformacionDataCreditoDAO;
	
   
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ProcesoCCCGenerarInformacionDataCreditoService#executeAsignacionCartera(java.util.Map)
	 */
	public void executeGenerarInformacionDataCredito (Map criteria) {
		this.procesoCCCGenerarInformacionDataCreditoDAO.executeGenerarInformacionDataCredito(criteria);
	}

	
}
