package biz.belcorp.ssicc.service.edu.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.edu.ProcesoEDUAuditoriaDAO;
import biz.belcorp.ssicc.dao.edu.model.Auditoria;
import biz.belcorp.ssicc.service.edu.ProcesoEDUAuditoriaService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva</a>
 *
 */
@Service("edu.procesoEDUAuditoriaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoEDUAuditoriaServiceImpl extends BaseService  
		implements ProcesoEDUAuditoriaService	{
	
	
	@Resource(name="edu.procesoEDUAuditoriaDAO")
	ProcesoEDUAuditoriaDAO dao;

	public void executeAuditoria(Map params) {
		dao.executeAuditoria(params);
	}

	
	public List getListAuditoria(Auditoria auditoria) {
		return dao.getListAuditoria(auditoria);
	}


	public List getListDetalleAuditoria(Auditoria auditoria) {
		return dao.getListDetalleAuditoria(auditoria);
	}
		
}
