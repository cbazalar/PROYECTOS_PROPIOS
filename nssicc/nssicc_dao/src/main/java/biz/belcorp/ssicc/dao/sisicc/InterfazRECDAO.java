/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.ocr.model.ConexionOCRWrapper;

/**
  * <p>
 * <a href="InterfazRECDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:croman@csigcomt.com">Cristhian Roman</a>
 * 
 */
public interface InterfazRECDAO extends DAO {

	/**Metodo que modifica la informacion de la tabla REC_LINEA_OPERA_RECLA 
	 * @param criteria
	 */
	public void updateInterfazEnvioAlmacenVirtual(Map criteria);
		
	/**
	 * Metodo que modifica la informacion luego de ejecutar la interfAz de la tabla  REC_LINEA_OPERA_RECLA
	 * @param criteria
	 */
	public void updateInterfazEnviarTransferenciaBoletasRecojo(Map criteria);
	
	/**
	 * Metodo que inserta registro en la tabla historica INT_HISTO_TRANS_BOREC
	 * @param criteria
	 */
	public void insertTablaHistoricaTransferenciaBoletasRecojo(Map criteria);
	
	public ConexionOCRWrapper getDevuelveConexionOCR(Map params)throws Exception;
}
