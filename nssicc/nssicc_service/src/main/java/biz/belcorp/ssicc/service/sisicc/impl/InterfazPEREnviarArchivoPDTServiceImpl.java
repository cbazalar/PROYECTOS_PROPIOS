/**
 * 
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazSiCCDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * Service encargado de procesar el Archivo PDT/
 * 
 * @author peextrdelosreyes
 *
 */
@Service("sisicc.interfazPEREnviarArchivoPDTService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazPEREnviarArchivoPDTServiceImpl extends BaseInterfazSalidaStoredProcedureAbstractService {

	@Resource(name="sisicc.interfazSiCCDAO")
	private InterfazSiCCDAO interfazSiCCDAO;
		
	@Override
	protected void executeStoreProcedure(Map params) {
		interfazSiCCDAO.executeInterfazPEREnviarArchivoPDT(params);
	}
}