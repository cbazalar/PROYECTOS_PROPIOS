package biz.belcorp.ssicc.dao.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.CopiarAsignarProductos;

/**
 * @author Nicols Lpez
 *
 */

public interface MantenimientoAPECopiarAsignacionProductosDAO extends DAO{

	/**
	 * Retorna la lista de Asignacin de Anaqueles y Versiones
	 * @return
	 */
	public List getCopiarAsignacionProductosList(Map criteria);
	
	/**
	 * @param criteria
	 * @return 1 Si existe Versin por Centro de Distribucin y Periodo, 0 en caso no Existe
	 */
	public String getValidaVersionByCDyPeriodo(Map criteria);
	
	/**
	 * @param criteria
	 * Permite Copiar la asignacin de productos anaqueles asignando una nueva versin
	 */
	public void generaCopiaAsignacionProductosAnaqueles(Map criteria);
	
	/**
	 * @param criteria
	 * @return El Objeto Asignar Productos Anaqueles 
	 */
	public CopiarAsignarProductos getCopiarAsigProdAnaquelObject(Map criteria);
	
}
