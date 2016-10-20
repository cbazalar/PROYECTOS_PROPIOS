package biz.belcorp.ssicc.dao.spusicc.ape;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.EtiquetayListadoPicado;

/**
 * @author David Ramos
 */

public interface MantenimientoAPEProcesoEmbalajeTerminadoDAO extends DAO{
	

	/**
	 * Actuliza los datos de Etiqueta y Listado Picado
	 * @param criteria
	 */

	public void updateEtiqueta(Map criteria) ;

	/**
	 * Actuliza los datos de Etiqueta y Listado Picado
	 * @param criteria
	 */

	public void updateListadoPicado(Map criteria);
	
	/**
	 * Obtiene la lista de Linea de Armado
	 * @param criteria
	 * @return
	 */
	public EtiquetayListadoPicado getEtiquetayListadoPicadoByCodBarrayOidPaisObject(Map criteria);

	/**
	 * Actualiza Seguimiento de Pedidos
	 * @param criteria
	 */
	public void actualizarSeguimientoPedidos(Map criteria);
}
