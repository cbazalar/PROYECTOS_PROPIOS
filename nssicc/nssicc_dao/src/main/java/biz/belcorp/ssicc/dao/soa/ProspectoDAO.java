package biz.belcorp.ssicc.dao.soa;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoEDURegistroCalificacionEjecutivaDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 */


public interface ProspectoDAO extends DAO {

	  /**
	 * @param criteria
	 * @return
	 */
	List getValidacionCrediticiaUsuario(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	int getLengthDocumento(Map criteria);

	/**
	 * Obtiene los motivos de Bloqueo de una consultora
	 * @param criteria
	 * @return
	 */
	List getMotivosBloqueoXConsultora(Map criteria);

}
