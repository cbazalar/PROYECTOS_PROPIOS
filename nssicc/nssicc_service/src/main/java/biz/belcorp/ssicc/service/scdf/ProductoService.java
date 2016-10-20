package biz.belcorp.ssicc.service.scdf;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.model.Producto;
import biz.belcorp.ssicc.service.framework.Service;

public interface ProductoService extends Service {

    /**
     * Actualiza la informacion de un producto.
     * 
     * @param producto
     * @param usuario
     */
    public void updateProducto(Producto producto, Usuario usuario);

    /**
     * Realiza la actualizacion de Stickers.
     * 
     * @param producto
     *            Objeto Producto que se utilizar para almacenar criterios
     * @param usuario
     *            Usuario logueado en el sistema
     */
    public void executeActualizaStickers(Producto producto, Usuario usuario);

    /**
     * Busca los productos por criterios que se almacenan en un Map
     * 
     * @param criteria
     *            Objeto que sirve para almacenar los criterios.
     * @return Lista de Objetos Productos poblados.
     */
    public List getProductosByCriteria(Map criteria);

    /**
     * Obtiene la informacion de un producto en base a un criterio de Primary
     * Key.
     * 
     * @param producto
     *            Objeto que sirve para almacenar los criterios de Primary Key.
     * @return Lista de Objetos Productos poblados.
     */
    public Producto getProducto(Producto producto);
    
    
    /**
     * Obtiene la informacion de los productos en Stock
     * 
     * @param criteria
     *            Objeto que sirve para almacenar los criterios.
     * @return Lista de Objetos Productos poblados.
     */
    public List getProductoStockList(Map criteria);
}
