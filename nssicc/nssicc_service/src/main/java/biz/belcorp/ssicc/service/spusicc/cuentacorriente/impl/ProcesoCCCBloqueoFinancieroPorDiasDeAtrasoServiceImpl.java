package biz.belcorp.ssicc.service.spusicc.cuentacorriente.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCBloqueoFinancieroPorDiasDeAtrasoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCBloqueoFinancieroPorDiasDeAtrasoService;


/**
 * Service que controla la Generacion de Consultora Cupon
 *  
 * <p>
 * <a href="ProcesoCCCBloqueoFinancieroPorDiasDeAtrasoServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">JFA</a>
 * 
 */
@Service("spusicc.procesoCCCBloqueoFinancieroPorDiasDeAtrasoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCCCBloqueoFinancieroPorDiasDeAtrasoServiceImpl extends BaseService implements ProcesoCCCBloqueoFinancieroPorDiasDeAtrasoService {

	@Resource(name = "spusicc.procesoCCCBloqueoFinancieroPorDiasDeAtrasoDAO")	
	private ProcesoCCCBloqueoFinancieroPorDiasDeAtrasoDAO procesoCCCBloqueoFinancieroPorDiasDeAtrasoDAO;

   
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ProcesoCCCBloqueoFinancieroPorDiasDeAtrasoService#executeAsignacionCartera(java.util.Map)
	 */
	public void executeBloqueoFinancieroPorDiasDeAtraso (Map criteria) {
		this.procesoCCCBloqueoFinancieroPorDiasDeAtrasoDAO.executeBloqueoFinancieroPorDiasDeAtraso(criteria);
	}

	
}
