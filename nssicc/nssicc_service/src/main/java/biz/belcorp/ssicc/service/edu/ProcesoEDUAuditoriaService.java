package biz.belcorp.ssicc.service.edu;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.edu.model.Auditoria;

/**
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva</a>
 *
 */
public interface ProcesoEDUAuditoriaService {
	
	/**
	 * Realiza el proceso de Auditoria
	 * @param params
	 * @throws Exception
	 */
	public void executeAuditoria(Map params) ;

	public List getListAuditoria(Auditoria auditoria);

	public List getListDetalleAuditoria(Auditoria auditoria);
	
	
}
