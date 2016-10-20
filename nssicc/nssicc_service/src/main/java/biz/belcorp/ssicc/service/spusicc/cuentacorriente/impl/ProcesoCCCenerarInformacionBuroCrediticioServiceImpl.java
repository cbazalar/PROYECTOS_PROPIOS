package biz.belcorp.ssicc.service.spusicc.cuentacorriente.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCenerarInformacionBuroCrediticioDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

@Service("sisicc.procesoCCCenerarInformacionBuroCrediticioService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCCCenerarInformacionBuroCrediticioServiceImpl extends BaseInterfazSalidaStoredProcedureAbstractService {

	@Resource(name = "spusicc.procesoCCCenerarInformacionBuroCrediticioDAO")
	private ProcesoCCCenerarInformacionBuroCrediticioDAO procesoCCCenerarInformacionBuroCrediticioDAO;


	@Override
	protected void executeStoreProcedure(Map params) {
		procesoCCCenerarInformacionBuroCrediticioDAO.executeGenerarInformacionBuroCrediticia(params);
		
		if(log.isDebugEnabled())
			log.debug(params);
	}
	
	
}
