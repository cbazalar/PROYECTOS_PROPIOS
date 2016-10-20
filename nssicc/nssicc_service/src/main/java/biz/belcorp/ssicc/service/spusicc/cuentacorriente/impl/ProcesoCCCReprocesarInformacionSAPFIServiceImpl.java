package biz.belcorp.ssicc.service.spusicc.cuentacorriente.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCReprocesarInformacionSAPFIDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCReprocesarInformacionSAPFIService;


/**
 * Service que controla el Reproceso de la Informacion para SAPFI
 *  
 * <p>
 * <a href="ProcesoCCCReprocesarInformacionSAPFIServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">JFA</a>
 * 
 */
@Service("spusicc.procesoCCCReprocesarInformacionSAPFIService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCCCReprocesarInformacionSAPFIServiceImpl extends BaseService implements ProcesoCCCReprocesarInformacionSAPFIService {

	@Resource(name = "spusicc.procesoCCCReprocesarInformacionSAPFIDAO")	
	private ProcesoCCCReprocesarInformacionSAPFIDAO procesoCCCReprocesarInformacionSAPFIDAO;

	   	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ProcesoCCCReprocesarInformacionSAPFIService#executeReprocesarInformacionSAPFI(java.util.Map)
	 */
	public void executeReprocesarInformacionSAPFI (Map criteria) {
		this.procesoCCCReprocesarInformacionSAPFIDAO.executeReprocesarInformacionSAPFI(criteria);
	}
	
	public void executeCierreFacturacion (Map criteria) {
		
	}
	
}
