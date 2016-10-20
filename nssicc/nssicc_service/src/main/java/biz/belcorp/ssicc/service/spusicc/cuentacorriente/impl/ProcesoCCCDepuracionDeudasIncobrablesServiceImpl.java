package biz.belcorp.ssicc.service.spusicc.cuentacorriente.impl;


import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCDepuracionDeudasIncobrablesDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCDepuracionDeudasIncobrablesService;



/**
 * Service que controla la Depuracion de Deudas Incobrables
 *  
 * <p>
 * <a href="ProcesoCCCDepuracionDeudasIncobrablesServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias</a>
 * 
 */
@Service("spusicc.procesoCCCDepuracionDeudasIncobrablesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCCCDepuracionDeudasIncobrablesServiceImpl extends BaseService implements ProcesoCCCDepuracionDeudasIncobrablesService {

	@Resource(name = "spusicc.procesoCCCDepuracionDeudasIncobrablesDAO")		
	private ProcesoCCCDepuracionDeudasIncobrablesDAO procesoCCCDepuracionDeudasIncobrablesDAO;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ProcesoCCCDepuracionDeudasIncobrablesService#executeDepuracionDeudasIncobrables(java.util.Map)
	 */
	public void executeDepuracionDeudasIncobrables (Map criteria) {
		this.procesoCCCDepuracionDeudasIncobrablesDAO.executeDepuracionDeudasIncobrables(criteria);
	}

}
