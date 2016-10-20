package biz.belcorp.ssicc.dao.scdf.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.ProductoDAO;
import biz.belcorp.ssicc.dao.scdf.model.Producto;

@Repository("scdf.productoDAO")
public class ProductoDAOiBatis extends BaseDAOiBatis implements ProductoDAO {

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.ProductoDAO#getProducto(biz.belcorp.ssicc.scdf.model.Producto)
     */
    public Producto getProducto(Producto producto) {
        Producto resultado = (Producto) getSqlMapClientTemplate()
                .queryForObject("scdf.ProductoSQL.getProducto", producto);
        if (resultado == null) {
            throw new ObjectRetrievalFailureException(Usuario.class, resultado);
        }
        return resultado;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.ProductoDAO#getProductosByPais(java.lang.String)
     */
    public List getProductosByPais(String codigoPais) {
        List productos = getSqlMapClientTemplate().queryForList(
                "scdf.ProductoSQL.getProductosByPais", codigoPais);
        return productos;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.ProductoDAO#getProductosMapByPais(java.lang.String)
     */
    public List getProductosMapByPais(String codigoPais) {
        List productos = getSqlMapClientTemplate().queryForList(
                "scdf.ProductoSQL.getProductosMapByPais", codigoPais);
        return productos;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.ProductoDAO#getProductosByCriteria(java.util.Map)
     */
    public List getProductosByCriteria(Map criteria) {
        List productos = getSqlMapClientTemplate().queryForList(
                "scdf.ProductoSQL.getProductosByCriteria", criteria);
        return productos;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.ProductoDAO#insertProducto(biz.belcorp.ssicc.scdf.model.Producto,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void insertProducto(Producto producto, Usuario usuario) {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.ProductoDAO#updateProducto(biz.belcorp.ssicc.scdf.model.Producto,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void updateProducto(Producto producto, Usuario usuario) {
        getSqlMapClientTemplate().update("scdf.ProductoSQL.updateProducto",
                producto);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.ProductoDAO#removeProducto(biz.belcorp.ssicc.scdf.model.Producto,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void removeProducto(Producto producto, Usuario usuario) {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.ProductoDAO#updateProductoIndicadorGeneracionStickersByPais(biz.belcorp.ssicc.scdf.model.Producto,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void updateProductoIndicadorGeneracionStickersByPais(
            Producto producto, Usuario usuario) {
        getSqlMapClientTemplate()
                .update(
                        "scdf.ProductoSQL.updateProductoIndicadorGeneracionStickersByPais",
                        producto);
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scdf.dao.ProductoDAO#getProductoStockList(java.util.Map)
	 */
	public List getProductoStockList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("scdf.ProductoSQL.getProductoStockList",criteria);
	}

}
