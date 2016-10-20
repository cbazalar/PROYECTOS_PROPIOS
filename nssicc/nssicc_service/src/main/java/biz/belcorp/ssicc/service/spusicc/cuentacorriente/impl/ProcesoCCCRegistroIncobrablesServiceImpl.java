package biz.belcorp.ssicc.service.spusicc.cuentacorriente.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCRegistroIncobrablesDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCRegistroIncobrablesService;

/**
 * <p>
 * <a href="ProcesoCCCRegistroIncobrablesServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 * 
 */
@Service("spusicc.procesoCCCRegistroIncobrablesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCCCRegistroIncobrablesServiceImpl extends BaseService implements ProcesoCCCRegistroIncobrablesService {

	@Resource(name = "spusicc.procesoCCCRegistroIncobrablesDAO")
	private ProcesoCCCRegistroIncobrablesDAO procesoCCCRegistroIncobrablesDAO;



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ProcesoCCCRegistroIncobrablesService#executeRegistroIncobrables(java.util.Map)
	 */
	public void executeRegistroIncobrables(Map criteria) {
		procesoCCCRegistroIncobrablesDAO.executeRegistroIncobrables(criteria);
	}
}