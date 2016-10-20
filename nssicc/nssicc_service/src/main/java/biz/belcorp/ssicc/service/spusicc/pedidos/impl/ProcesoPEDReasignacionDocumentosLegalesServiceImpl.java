package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDReasignacionDocumentosLegalesDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDReasignacionDocumentosLegalesService;

@Service("spusicc.procesoPEDReasignacionDocumentosLegalesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoPEDReasignacionDocumentosLegalesServiceImpl extends BaseService implements ProcesoPEDReasignacionDocumentosLegalesService{
	
	@Resource(name="spusicc.procesoPEDReasignacionDocumentosLegalesDAO")
	private ProcesoPEDReasignacionDocumentosLegalesDAO procesoPEDReasignacionDocumentosLegalesDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDReasignacionDocumentosLegalesService#getTipoDocumentoContableAllList()
	 */
	public List getTipoDocumentoContableAllList() {
		return procesoPEDReasignacionDocumentosLegalesDAO.getTipoDocumentoContableAllList();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDReasignacionDocumentosLegalesService#executeReasignacionDocumentosLegales(java.util.Map)
	 */
	public void executeReasignacionDocumentosLegales(Map params) {
		procesoPEDReasignacionDocumentosLegalesDAO.executeReasignacionDocumentosLegales(params);
	}
}