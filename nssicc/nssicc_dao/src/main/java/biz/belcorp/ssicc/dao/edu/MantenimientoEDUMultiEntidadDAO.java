package biz.belcorp.ssicc.dao.edu;

import java.util.List;

import biz.belcorp.ssicc.dao.edu.model.EntidadGenerico;
import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoEDUMultiEntidadDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva</a>
 */

public interface MantenimientoEDUMultiEntidadDAO extends DAO{

	
	/**
	 * Devuelve los registros de una Entidad selecciona ,segun filtro 
	 * @author sbuchelli 
	 * @param 
	 */
	public List getMultiEntidadByCriteria(EntidadGenerico entidadGenerico);
	/**
	 * Actualiza el estado ala entidad Generica 1:activo 9 :eliminado 
	 * @author sbuchelli
	 *  
	 * @param  entidadGenerico
	 * 			objeto entidad Generica
	 * @param usuario
	 *			objeto Usuario para el registro de Auditoría 
	 * 
	 */
	public void updateEstadoMultiEntidad(EntidadGenerico entidadGenerico, Usuario usuario);

	

	public String updateMultiEntidad(EntidadGenerico entidadGenerico, Usuario usuario);

	public String insertMultiEntidad(EntidadGenerico entidadGenerico, Usuario usuario);


}
