package biz.belcorp.ssicc.dao.edu;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.edu.model.Auditoria;
import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoEDUAuditoriaDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva</a>
 */


public interface ProcesoEDUAuditoriaDAO extends DAO {

	/**
	 * Realiza el proceso de Cerrar Cursos Vigentes
	 * @param criteria
	 */
	public void executeAuditoria(Map criteria);

	public List getListAuditoria(Auditoria auditoria);

	public List getListDetalleAuditoria(Auditoria auditoria);
	
	
	
}
