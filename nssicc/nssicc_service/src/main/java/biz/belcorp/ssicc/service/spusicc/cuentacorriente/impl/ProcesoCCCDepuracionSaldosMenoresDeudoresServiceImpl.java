package biz.belcorp.ssicc.service.spusicc.cuentacorriente.impl;


import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCDepuracionSaldosMenoresDeudoresDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCDepuracionSaldosMenoresDeudoresService;



/**
 * Service que controla la Depuracion de Saldos Menores Deudores en Cuenta Corriente
 *  
 * <p>
 * <a href="ProcesoCCCDepuracionSaldosMenoresDeudoresServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias</a>
 * 
 */
@Service("spusicc.procesoCCCDepuracionSaldosMenoresDeudoresService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCCCDepuracionSaldosMenoresDeudoresServiceImpl extends BaseService implements ProcesoCCCDepuracionSaldosMenoresDeudoresService {

	@Resource(name = "spusicc.procesoCCCDepuracionSaldosMenoresDeudoresDAO")		
	private ProcesoCCCDepuracionSaldosMenoresDeudoresDAO procesoCCCDepuracionSaldosMenoresDeudoresDAO;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ProcesoCCCDepuracionSaldosMenoresDeudoresService#executeDepuracionSaldosMenoresDeudores(java.util.Map)
	 */
	public void executeDepuracionSaldosMenoresDeudores (Map criteria) {
		this.procesoCCCDepuracionSaldosMenoresDeudoresDAO.executeDepuracionSaldosMenoresDeudores(criteria);
	}

}
