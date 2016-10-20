package biz.belcorp.ssicc.dao.spusicc.cuentacorriente;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;


/**
 * <p>
 * <a href="MantenimientoCCCFacturarInteresDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:dtorres@sigcomt.com">Diego Torres Loyola</a>
 */
public interface MantenimientoCCCFacturarInteresDAO extends DAO {
	
	

	public List getConsoCalcuInteMoralList(Map criteria);
	
	
	/**
	 * Actualiza la Consolidado
	 * 
	 * @param criteria
	 * @param usuario
	 */
	
	String obtenerPathUpload(String codigoPais);
	
	void deleteConsolidadoInterMoraCCC(String codigoUsuario);
	
	void insertConsolidadoInterMoraCCC(Map params);
	
	void executeValidarCargaConsolidadoInterMoraCCC(String codigoUsuario);
	
	List getCargarConsolidadoInterMoraCCC(String codigoUsuario);
	
	void executeProcesarCargaFactuInterMora(String codigoUsuario);
	
}
