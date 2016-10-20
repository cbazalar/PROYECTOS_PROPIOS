package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCMoverInformacionHistoricaDAO;

/**
 * Service para el proceso que Mueve Informacion Historica de Incentivos
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 */
@Service("spusicc.procesoINCMoverInformacionHistoricaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCMoverInformacionHistoricaServiceImpl extends
	BaseInterfazProcesoAbstractService {
	
	@Resource(name="spusicc.procesoINCMoverInformacionHistoricaDAO")
	private ProcesoINCMoverInformacionHistoricaDAO procesoINCMoverInformacionHistoricaDAO;

	protected void executeStoreProcedure(Map params) {
		procesoINCMoverInformacionHistoricaDAO.executeMoverInformacionHistorica(params);
	}
	
	
}