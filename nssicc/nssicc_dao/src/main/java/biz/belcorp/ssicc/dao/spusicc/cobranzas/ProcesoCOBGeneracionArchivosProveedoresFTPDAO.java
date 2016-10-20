package biz.belcorp.ssicc.dao.spusicc.cobranzas;

import java.util.List;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * <p>
 * <a href="ProcesoCOBGeneracionArchivosProveedoresFTPDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 */
public interface ProcesoCOBGeneracionArchivosProveedoresFTPDAO extends DAO {
	
	/**
	 * Devuelve los proveedores y las etapas a reportar
	 * @return
	 */
	public List obtenerProveedoresEtapas();
	
}