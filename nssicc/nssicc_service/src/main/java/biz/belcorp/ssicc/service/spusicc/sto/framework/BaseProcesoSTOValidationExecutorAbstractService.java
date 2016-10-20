package biz.belcorp.ssicc.service.spusicc.sto.framework;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.GenericoDAO;
import biz.belcorp.ssicc.dao.sisicc.InterfazOCRDAO;
import biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTODAO;
import biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTOHistoricoDAO;
import biz.belcorp.ssicc.dao.spusicc.sto.model.ValidacionDocumento;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
/**
 * Implementacion de ProcesoSTOValidationExecutorService que utiliza un Map con las
 * implementaciones especificas de los Documentos STO SiCC inyectados mediante
 * Spring. Nuevos documentos requeriran que se agregue la referencia al Map de
 * implementaciones en el 'applicationContext-service-spusicc-impl.xml'.
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 */
@Service("spusicc.baseProcesoSTOValidationExecutorAbstractService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class BaseProcesoSTOValidationExecutorAbstractService extends BaseService implements ProcesoSTOValidationExecutorService {
	protected final Log log = LogFactory.getLog(getClass());
	
	@Resource(name="spusicc.procesoSTODAO")
	protected ProcesoSTODAO procesoSTODAO;
	
	@Resource(name="genericoDAO")
	protected GenericoDAO genericoDAO;
	
	@Resource(name="spusicc.procesoSTOHistoricoDAO")
	protected ProcesoSTOHistoricoDAO procesoSTOHistoricoDAO;
	
	@Resource(name="sisicc.interfazOCRDAO")
	protected InterfazOCRDAO interfazOCRDAO; 
	


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.framework.ProcesoSTOExecutionService#approveValidacionDocumentoSTO(java.util.Map, java.util.List)
	 */
	public void executeValidation(ValidacionDocumento validacionDocumento , String namespaceSTO, Map queryParams) throws Exception {
		
		beforeExecuteValidation(validacionDocumento,namespaceSTO,queryParams);
		procesoSTODAO.executeBeforeValidation(queryParams);
		List list = procesoSTODAO.getListForValidate(namespaceSTO,queryParams);
		
		for (int i = 0; i < list.size(); i++) {
			Map row = (Map)list.get(i);
			boolean isValid = execute(validacionDocumento,namespaceSTO,row);
			if (isValid) {
				row.put("validacionDocumento", validacionDocumento);
				procesoSTODAO.updateValidRecord(row);
			}
		} 
		procesoSTODAO.executeAfterValidation(queryParams);
		afterExecuteValidation(validacionDocumento,namespaceSTO,queryParams);
	}
	
	protected void beforeExecuteValidation(ValidacionDocumento validacionDocumento , String namespaceSTO, Map queryParams) throws Exception {
	}
	
	protected void afterExecuteValidation(ValidacionDocumento validacionDocumento , String namespaceSTO, Map queryParams) throws Exception {
	}
	
	/**
	 * @param documentoSTOParams
	 * @return
	 */
	protected boolean execute(ValidacionDocumento validacionDocumento , String namespaceSTO, Map row) throws Exception {
		return false;
	}
	
}
