package biz.belcorp.ssicc.dao.spusicc.sto;


import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spncd.model.SecuenciaValidacion;


/**
 * Service con metodos para las consultas invocados por la pantalla de Secuencia Validacion
 * 
 * <p>
 * <a href="MantenimientoSTOSecuenciaValidacionDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Diego Torres Loyola</a>
 * 
 */
public interface MantenimientoSTOSecuenciaValidacionDAO extends DAO {
	
	/**
	 * Devuelve la lista de secuencias de validacion
	 * @param params
	 * @return
	 */
	public List getValidacionesByCriteria(Map params);
	
	/**
	 * Devuelve la lista de tipos de documento
	 * @param params
	 * @return
	 */
	public List getTipoDocumentoList(Map params);
	

	
	/**
	 * Inserta la SecuenciaValidacion
	 * 
	 * @param bean
	 * @param usuario
	 */
	public boolean insertPametroSecuenciaValidacionSTO(SecuenciaValidacion bean, Usuario usuario);
	
	/**
	 * Actualiza la SecuenciaValidacion
	 * 
	 * @param bean
	 * @param usuario
	 */
	public boolean updatePametroSecuenciaValidacionSTO(Map criteria, Usuario usuario);
	
	/**
	 * Elimina un registro la base de datos 
	 * @param bean
	 */
	public void deletePametroSecuenciaValidacionSTO(SecuenciaValidacion bean);
	

}