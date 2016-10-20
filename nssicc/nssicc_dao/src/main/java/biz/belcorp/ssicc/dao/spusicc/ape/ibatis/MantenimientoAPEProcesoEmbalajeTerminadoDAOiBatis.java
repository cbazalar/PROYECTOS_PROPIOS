package biz.belcorp.ssicc.dao.spusicc.ape.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEProcesoEmbalajeTerminadoDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.EtiquetayListadoPicado;

/**
 * @author David Ramos
 */
@Repository("spusicc.mantenimientoAPEProcesoEmbalajeTerminadoDAO")
public class MantenimientoAPEProcesoEmbalajeTerminadoDAOiBatis extends BaseDAOiBatis implements MantenimientoAPEProcesoEmbalajeTerminadoDAO{
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEProcesoEmbalajeTerminadoDAO#updateEtiqueta(java.util.Map)
	 */
	public void updateEtiqueta(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.updateEtiqueta", criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEProcesoEmbalajeTerminadoDAO#updateListadoPicado(java.util.Map)
	 */
	public void updateListadoPicado(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.updateListadoPicado", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEProcesoEmbalajeTerminadoDAO#getEtiquetayListadoPicadoByCodBarrayOidPaisObject(java.util.Map)
	 */
	public EtiquetayListadoPicado getEtiquetayListadoPicadoByCodBarrayOidPaisObject(Map criteria){
		return (EtiquetayListadoPicado)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getEtiquetayListadoPicadoByCodBarrayOidPaisObject", criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEProcesoEmbalajeTerminadoDAO#actualizarSeguimientoPedidos(java.util.Map)
	 */
	public void actualizarSeguimientoPedidos(Map criteria) {
		if(validarCajasDelPedidoRecibidas(criteria)){
			getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.executeActualizarSeguimientoPedidos", criteria);
		}
	}


	private boolean validarCajasDelPedidoRecibidas(Map criteria) {
		
		/**
		 *  Obtiene el nro de pedido que no tienen el estado 2 (EMBALADO)
		 *  (Se asume que si existen pedidos, por la validacion anterior que captura los datos iniciales del criteria que es getEtiquetayListadoPicadoByCodBarrayOidPaisObject)
		 */
		Integer nroCajasDelPedidoNoRecibidas = (Integer) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getNroCajasDelPedidoNoRecibidas", criteria);
		
		// EMBALADOS si el nro es 0
		return nroCajasDelPedidoNoRecibidas == 0 ;
	}

}