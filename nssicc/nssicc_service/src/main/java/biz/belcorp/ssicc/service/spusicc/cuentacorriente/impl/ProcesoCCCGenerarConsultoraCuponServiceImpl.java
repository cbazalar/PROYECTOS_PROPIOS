package biz.belcorp.ssicc.service.spusicc.cuentacorriente.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCGenerarConsultoraCuponDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCGenerarConsultoraCuponService;


/**
 * Service que controla la Generacion de Consultora Cupon
 *  
 * <p>
 * <a href="ProcesoCCCGenerarConsultoraCuponServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">JFA</a>
 * 
 */
@Service("spusicc.procesoCCCGenerarConsultoraCuponService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCCCGenerarConsultoraCuponServiceImpl extends BaseService implements ProcesoCCCGenerarConsultoraCuponService {

	@Resource(name = "spusicc.procesoCCCGenerarConsultoraCuponDAO")	
	private ProcesoCCCGenerarConsultoraCuponDAO procesoCCCGenerarConsultoraCuponDAO;

   
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ProcesoCCCGenerarConsultoraCuponService#executeAsignacionCartera(java.util.Map)
	 */
	public void executeGenerarConsultoraCupon (Map criteria) {
		this.procesoCCCGenerarConsultoraCuponDAO.executeGenerarConsultoraCupon(criteria);
	}

	
}
