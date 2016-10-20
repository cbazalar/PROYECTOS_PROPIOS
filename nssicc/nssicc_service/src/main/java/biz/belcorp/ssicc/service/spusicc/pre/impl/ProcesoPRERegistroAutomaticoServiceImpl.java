package biz.belcorp.ssicc.service.spusicc.pre.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.pre.ProcesoPRERegistroAutomaticoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pre.ProcesoPRERegistroAutomaticoService;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Service("spusicc.procesoPRERegistroAutomaticoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoPRERegistroAutomaticoServiceImpl extends BaseService implements
	ProcesoPRERegistroAutomaticoService {
		
	@Resource(name="spusicc.procesoPRERegistroAutomaticoDAO")
	private ProcesoPRERegistroAutomaticoDAO procesoPRERegistroAutomaticoDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.pre.ProcesoPRERegistroAutomaticoService#executeRegistroAutomatico(java.util.Map)
	 */
	public void executeRegistroAutomatico(Map params) {
		procesoPRERegistroAutomaticoDAO.executeRegistroAutomatico(params);
	}
	
}
