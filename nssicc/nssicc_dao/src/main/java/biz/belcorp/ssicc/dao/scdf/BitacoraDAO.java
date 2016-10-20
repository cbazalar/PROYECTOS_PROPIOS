/*
 * Created on 19/04/2005 04:43:53 PM biz.belcorp.ssicc.dao.BitacoraDAO
 */
package biz.belcorp.ssicc.dao.scdf;

import java.util.List;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.scdf.model.Bitacora;

/**
 * TODO Include class description here.
 * <p>
 * <a href="BitacoraDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramírez </a>
 */
public interface BitacoraDAO extends DAO {
    
	/**
	 * Obtiene un listado de todas las bitacoras
	 * 
	 * @param bitacora
	 *            objeto Bitacora cuyos atributos son usados como criterios de
	 *            búsqueda
	 * @return Lista de objetos Bitacora poblados
	 */
	public List getBitacoras(Bitacora bitacora);

	/**
	 * Registra la información de una nueva bitacora
	 * 
	 * @param bitacora
	 *            el objeto a ser insertado
	 * @param usuario
	 *            objeto conteniendo información del usuario invocador
	 */
	public void insertBitacora(Bitacora bitacora);

}