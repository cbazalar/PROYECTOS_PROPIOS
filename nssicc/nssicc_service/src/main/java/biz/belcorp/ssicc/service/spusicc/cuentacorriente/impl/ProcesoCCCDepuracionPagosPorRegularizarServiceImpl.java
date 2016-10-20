package biz.belcorp.ssicc.service.spusicc.cuentacorriente.impl;


import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCDepuracionPagosPorRegularizarDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCDepuracionPagosPorRegularizarService;



/**
 * Service que controla la Depuracion de Pagos por Regularizar
 *  
 * <p>
 * <a href="ProcesoCCCDepuracionPagosPorRegularizarServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias</a>
 * 
 */
@Service("spusicc.procesoCCCDepuracionPagosPorRegularizarService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCCCDepuracionPagosPorRegularizarServiceImpl extends BaseService implements ProcesoCCCDepuracionPagosPorRegularizarService {

	@Resource(name = "spusicc.procesoCCCDepuracionPagosPorRegularizarDAO")		
	private ProcesoCCCDepuracionPagosPorRegularizarDAO procesoCCCDepuracionPagosPorRegularizarDAO;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ProcesoCCCDepuracionPagosPorRegularizarService#executeDepuracionPagosPorRegularizar(java.util.Map)
	 */
	public void executeDepuracionPagosPorRegularizar (Map criteria) {
		this.procesoCCCDepuracionPagosPorRegularizarDAO.executeDepuracionPagosPorRegularizar(criteria);
	}

    /* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ProcesoCCCDepuracionPagosPorRegularizarService#executeInsertarPago(java.util.Map)
	 */
	public void executeInsertarPago(Map criteria) {
		this.procesoCCCDepuracionPagosPorRegularizarDAO.executeInsertarPago(criteria);
   }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ProcesoCCCDepuracionPagosPorRegularizarService#executeRevertirPago(java.util.Map)
	 */
	public void executeRevertirPago(Map criteria) {
		this.procesoCCCDepuracionPagosPorRegularizarDAO.executeRevertirPago(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ProcesoCCCDepuracionPagosPorRegularizarService#executeConsultarPago(java.util.Map)
	 */
	public void executeConsultarPago(Map criteria) {
		this.procesoCCCDepuracionPagosPorRegularizarDAO.executeConsultarPago(criteria);
	}

}
