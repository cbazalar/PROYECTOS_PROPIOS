/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.pedidos.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoPEDConfiguracionOfertaDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.CondicionOferta;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.DetalleOferta;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.GrupoOferta;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.MatrizFacturacion;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.Oferta;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.RangoPromocion;

/**
 * @author Ivan Tocto
 *
 */
@Repository("spusicc.mantenimientoPEDConfiguracionOfertaDAO")
public class MantenimientoPEDConfiguracionOfertaDAOiBatis extends
		BaseDAOiBatis implements
		MantenimientoPEDConfiguracionOfertaDAO {

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertaDAO#getPeriodosMatrizFacturacion()
	 */
	public List getPeriodosMatrizFacturacion() {
		return this.getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getPeriodosMatrizFacturacion");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertaDAO#getEstimadosMatrizFacturacion(java.util.Map)
	 */
	public List getEstimadosMatrizFacturacion(Map params) {
		return this.getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getEstimadosMatrizFacturacion", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertaDAO#insertMatrizFacturacion(biz.belcorp.ssicc.spusicc.pedidos.model.MatrizFacturacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertMatrizFacturacion(MatrizFacturacion matriz, Usuario usuario) {
		this.getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertMatrizFacturacion", matriz);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertaDAO#updateMatrizFacturacion(biz.belcorp.ssicc.spusicc.pedidos.model.MatrizFacturacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateMatrizFacturacion(MatrizFacturacion matriz, Usuario usuario) {
		this.getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.updateMatrizFacturacion", matriz);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertaDAO#executeAsignarCodigoVentaAction(java.util.Map)
	 */
	public void executeAsignarCodigoVentaAction(Map params) {
		this.getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.executeAsignarCodigoVentaAction", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertaDAO#getEstrategias(java.util.Map)
	 */
	public List getEstrategias(Map params) {
		return this.getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getEstrategias", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertaDAO#getOidAccesosByCodigoISO(java.lang.String)
	 */
	public List getOidAccesosByCodigoISO(String codigoIso) {
		return getSqlMapClientTemplate().queryForList("sisicc.GenericoSQL.getOidAccesosByCodigoISO", codigoIso);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertaDAO#getOidSubaccesosByOidAcceso(java.lang.String)
	 */
	public List getOidSubaccesosByOidAcceso(String oidAcceso) {
		return getSqlMapClientTemplate().queryForList("sisicc.GenericoSQL.getOidSubaccesosByOidAcceso", oidAcceso);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertaDAO#getProductosAsociadosByCriteria1(java.util.Map)
	 */
	public List getProductosAsociadosByCriteria1(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getProductosAsociadosByCriteria1", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertaDAO#getProductosAsociadosByCriteria2(java.util.Map)
	 */
	public List getProductosAsociadosByCriteria2(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getProductosAsociadosByCriteria2", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertaDAO#getValidarTipoOferta(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getValidarTipoOferta(String oidTipoOferta, String codigoProducto, String oidEstrategia, String precioCatalogo, String precioPosicionamiento) {
		Map params = new HashMap();
		params.put("oidTipoOferta", oidTipoOferta);
		params.put("codigoProducto", codigoProducto);
		params.put("oidEstrategia", oidEstrategia);
		params.put("precioCatalogo", precioCatalogo);
		params.put("precioPosicionamiento", precioPosicionamiento);
		
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getValidarTipoOferta", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertaDAO#getTiposCuadre(java.lang.String)
	 */
	public List getTiposCuadre(String numeroGrupos) {
		Map params = new HashMap();
		params.put("numeroGrupos", numeroGrupos);

		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getTiposCuadre", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertaDAO#getCondicionPromocionTiposCuadre()
	 */
	public List getCondicionPromocionTiposCuadre() {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getCondicionPromocionTiposCuadre");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertaDAO#insertOferta(biz.belcorp.ssicc.spusicc.pedidos.model.Oferta, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertOferta(Oferta oferta, Usuario usuario) {
		String oidOferta = ((Long)this.getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getOidOferta")).toString();
		oferta.setOid(oidOferta);
		
		this.getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertOferta", oferta);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertaDAO#insertGrupoOferta(biz.belcorp.ssicc.spusicc.pedidos.model.GrupoOferta, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertGrupoOferta(GrupoOferta grupoOferta, Usuario usuario) {
		String oidGrupoOferta = ((Long)this.getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getOidGrupoOferta")).toString();
		grupoOferta.setOid(oidGrupoOferta);
		
		this.getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertGrupoOferta", grupoOferta);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertaDAO#insertDetalleOferta(biz.belcorp.ssicc.spusicc.pedidos.model.DetalleOferta, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertDetalleOferta(DetalleOferta detalleOferta, Usuario usuario) {
		this.getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertDetalleOferta", detalleOferta);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertaDAO#insertCondicionOferta(biz.belcorp.ssicc.spusicc.pedidos.model.CondicionOferta, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertCondicionOferta(CondicionOferta condicion, Usuario usuario) {
		String oidCondicionOferta = ((Long)this.getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getOidCondicionOferta")).toString();
		condicion.setOid(oidCondicionOferta);
		
		this.getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertCondicionOferta", condicion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertaDAO#insertRangoPromocion(biz.belcorp.ssicc.spusicc.pedidos.model.RangoPromocion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertRangoPromocion(RangoPromocion rangoPromocion, Usuario usuario) {
		this.getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertRangoPromocion", rangoPromocion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoPEDConfiguracionOfertaDAO#getProductosAsociadosParaGrupo(java.util.Map)
	 */
	@Override
	public List getProductosAsociadosParaGrupo(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getProductosAsociadosParaGrupo", params);
	}
}
