package biz.belcorp.ssicc.service.spusicc.cuentacorriente.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCGenerarInformacionSAPFIDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCGenerarInformacionSAPFIService;


/**
 * Service que controla la Generacion de la Informacion para SAPFI
 *  
 * <p>
 * <a href="ProcesoCCCGenerarInformacionSAPFIServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">JFA</a>
 * 
 */
@Service("spusicc.procesoCCCGenerarInformacionSAPFIService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCCCGenerarInformacionSAPFIServiceImpl extends BaseService implements ProcesoCCCGenerarInformacionSAPFIService {

	@Resource(name = "spusicc.procesoCCCGenerarInformacionSAPFIDAO")	
	private ProcesoCCCGenerarInformacionSAPFIDAO procesoCCCGenerarInformacionSAPFIDAO;
	
	   	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ProcesoCCCGenerarInformacionSAPFIService#executeGenerarInformacionSAPFI(java.util.Map)
	 */
	public void executeGenerarInformacionSAPFI (Map criteria) {
		this.procesoCCCGenerarInformacionSAPFIDAO.executeGenerarInformacionSAPFI(criteria);
	}
	
}
