package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.gen;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.ocr.model.ConexionOCRWrapper;

/**
 * 
 * DAO correspondiente al CCC Comercial 
 * @author <a href="mailto:jflorencio@belcorp.biz">JFC</a>
 * 
 */
public interface GenericoCCCComercialDAO extends DAO {
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.ocr.dao.gen.GenericoOCRComercialDAO#getListProcesoCarga(java.lang.String, java.util.Map)
	 */
	public List getListPagosWeb(ConexionOCRWrapper conexionOCRWrapper, Map params) throws Exception;
	
	public void insertDeudaWeb(ConexionOCRWrapper conexionOCRWrapper, Map params) throws Exception;
	
	public void updateDeudaWeb(ConexionOCRWrapper conexionOCRWrapper, Map params) throws Exception;

	public void deleteDeudasWeb(ConexionOCRWrapper conexionOCRWrapper, Map params) throws Exception;
	
}
