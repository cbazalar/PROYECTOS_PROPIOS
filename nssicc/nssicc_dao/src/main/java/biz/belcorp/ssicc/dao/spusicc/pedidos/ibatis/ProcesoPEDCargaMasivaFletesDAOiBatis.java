/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.pedidos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDCargaMasivaFletesDAO;

/**
 * @author <a href="mailto:sguerra@sigcomt.com">Sebastian Guerra</a>
 */
@Repository("spusicc.procesoPEDCargaMasivaFletesDAO")
public class ProcesoPEDCargaMasivaFletesDAOiBatis extends BaseDAOiBatis implements ProcesoPEDCargaMasivaFletesDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDCargaMasivaFletesDAO#deleteTemporalCargaMasivaFletes(java.lang.String)
	 */
	public void deleteTemporalCargaMasivaFletes(String codUsuario) {
		getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteTemporalCargaMasivaFletes", codUsuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDCargaMasivaFletesDAO#insertaTemporalCargaMasivaFletes(java.util.Map)
	 */
	public void insertaTemporalCargaMasivaFletes(Map params) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.insertaTemporalCargaMasivaFletes", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDCargaMasivaFletesDAO#executeValidarCargaMasivaFlete(java.util.Map)
	 */
	public void executeValidarCargaMasivaFlete(Map params) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.executeValidarCargaMasivaFlete", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDCargaMasivaFletesDAO#getCargaMasivaFleteList(java.util.Map)
	 */
	public List getCargaMasivaFleteList(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getCargaMasivaFleteList", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDCargaMasivaFletesDAO#deleteDetalleConfiguracionFlete()
	 */
	public void deleteDetalleConfiguracionFlete() {
		getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteDetalleConfiguracionFlete", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDCargaMasivaFletesDAO#deleteCabeceraConfiguracionFlete()
	 */
	public void deleteCabeceraConfiguracionFlete() {
		getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteCabeceraConfiguracionFlete", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDCargaMasivaFletesDAO#executeActualizarConfiguracionFlete(java.util.Map)
	 */
	public void executeActualizarConfiguracionFlete(Map params) {
		getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.executeActualizarConfiguracionFlete", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDCargaMasivaFletesDAO#deleteTemporalCargaMasivaMontoMinimo(java.lang.String)
	 */
	public void deleteTemporalCargaMasivaMontoMinimo(String codUsuario) {
		getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteTemporalCargaMasivaMontoMinimo", codUsuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDCargaMasivaFletesDAO#insertaTemporalCargaMasivaMontoMinimo(java.util.Map)
	 */
	public void insertaTemporalCargaMasivaMontoMinimo(Map params) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.insertaTemporalCargaMasivaMontoMinimo", params);
	}
	
	public void executeValidarCargaMasivaMontoMinimo(Map params) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.executeValidarCargaMasivaMontoMinimo", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDCargaMasivaFletesDAO#getCargaMasivaMontoMinimoList(java.util.Map)
	 */
	public List getCargaMasivaMontoMinimoList(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getCargaMasivaMontoMinimoList", params);
	}
	
	public void executeActualizarCargaMasivaMontoMinimo(Map params) {
		getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.executeActualizarCargaMasivaMontoMinimo", params);
	}

}
