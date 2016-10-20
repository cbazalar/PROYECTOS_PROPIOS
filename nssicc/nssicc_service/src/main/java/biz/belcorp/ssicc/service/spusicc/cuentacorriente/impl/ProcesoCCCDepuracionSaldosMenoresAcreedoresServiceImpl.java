package biz.belcorp.ssicc.service.spusicc.cuentacorriente.impl;


import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCDepuracionSaldosMenoresAcreedoresDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCDepuracionSaldosMenoresAcreedoresService;



/**
 * Service que controla la Depuracion de Saldos Menores Acreedores en Cuenta Corriente
 *  
 * <p>
 * <a href="ProcesoCCCDepuracionSaldosMenoresAcreedoresServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias</a>
 * 
 */
@Service("spusicc.procesoCCCDepuracionSaldosMenoresAcreedoresService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCCCDepuracionSaldosMenoresAcreedoresServiceImpl extends BaseService implements ProcesoCCCDepuracionSaldosMenoresAcreedoresService {

	@Resource(name = "spusicc.procesoCCCDepuracionSaldosMenoresAcreedoresDAO")		
	private ProcesoCCCDepuracionSaldosMenoresAcreedoresDAO procesoCCCDepuracionSaldosMenoresAcreedoresDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ProcesoCCCDepuracionSaldosMenoresAcreedoresService#executeDepuracionSaldosMenoresAcreedores(java.util.Map)
	 */
	public void executeDepuracionSaldosMenoresAcreedores (Map criteria) {
		this.procesoCCCDepuracionSaldosMenoresAcreedoresDAO.executeDepuracionSaldosMenoresAcreedores(criteria);
	}

}
