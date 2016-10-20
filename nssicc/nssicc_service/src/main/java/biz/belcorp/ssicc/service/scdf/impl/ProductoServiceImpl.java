package biz.belcorp.ssicc.service.scdf.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.ProductoDAO;
import biz.belcorp.ssicc.dao.scdf.model.Producto;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.scdf.ProductoService;

@Service("scdf.productoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProductoServiceImpl extends BaseService implements ProductoService {

	@Resource(name="scdf.productoDAO")
    ProductoDAO productoDAO;
    
    

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.scdf.service.ProductoService#executeActualizaStickers(biz.belcorp.ssicc.scdf.model.Producto, biz.belcorp.ssicc.model.Usuario)
     */
    public void executeActualizaStickers(Producto producto, Usuario usuario) {
        productoDAO.updateProductoIndicadorGeneracionStickersByPais(producto, usuario);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.scdf.service.ProductoService#getProductosByCriteria(java.util.Map)
     */
    public List getProductosByCriteria(Map criteria) {
        List productos = productoDAO.getProductosByCriteria(criteria);
        return productos;
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.scdf.service.ProductoService#getProducto(biz.belcorp.ssicc.scdf.model.Producto)
     */
    public Producto getProducto(Producto producto) {
        return productoDAO.getProducto(producto);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scdf.service.ProductoService#updateProducto(biz.belcorp.ssicc.scdf.model.Producto, biz.belcorp.ssicc.model.Usuario)
     */
    public void updateProducto(Producto producto, Usuario usuario) {
        productoDAO.updateProducto(producto, usuario);
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scdf.service.ProductoService#getProductoStockList(java.util.Map)
	 */
	public List getProductoStockList(Map criteria) {
		// TODO Auto-generated method stub
		return productoDAO.getProductoStockList(criteria);
	}

}
