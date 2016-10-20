package biz.belcorp.ssicc.service.spusicc.sto;


import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spncd.model.SecuenciaValidacion;
import biz.belcorp.ssicc.service.framework.Service;


/**
 * Service con metodos para las consultas invocados por la pantalla de Secuencia Validacion
 * 
 * <p>
 * <a href="MantenimientoSTOSecuenciaValidacionService.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Diego Torres Loyola</a>
 * 
 */
public interface MantenimientoSTOSecuenciaValidacionService extends Service {
	
	/**
	 * Devuelve la lista de secuencia de validacion
	 * @param params
	 * @return
	 */
	List getValidacionesByCriteria(Map params);
	
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