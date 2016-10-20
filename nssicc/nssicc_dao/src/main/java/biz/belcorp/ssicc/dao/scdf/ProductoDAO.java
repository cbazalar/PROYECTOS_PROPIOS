/*
 * Created on 02/08/2005 06:34:26 PM biz.belcorp.ssicc.dao.ProductoDAO
 */
package biz.belcorp.ssicc.dao.scdf;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.model.Producto;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProductoDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramírez </a>
 */
public interface ProductoDAO extends DAO {

    /**
     * Obtenemos un producto en base a la primary Key. La excepcion
     * ObjectRetrievalFailureException Runtime Exception es lanzada si no es
     * encontrada.
     * 
     * @param producto
     * 
     * @return Producto identificado por la primary Key.
     */
    public Producto getProducto(Producto producto);

    /**
     * Obtiene una relacion de Productos en base al Pais de procedencia
     * 
     * @param codigoPais
     *            Codigo del Pais del Usuario invocador
     * @return Lista de Objetos productos poblados
     */
    public List getProductosByPais(String codigoPais);

    /**
     * Obtiene una relacion de Maps con columnas de Producto en base al Pais de
     * procedencia
     * 
     * @param codigoPais
     *            Codigo del Pais del Usuario invocador
     * @return Lista de Maps poblados
     */
    public List getProductosMapByPais(String codigoPais);

    /**
     * Obtiene una lista de productos filtrados a traves de un Map de criterios.
     * 
     * @param criteria
     *            Map con ciertos parametros que serviran como filtro
     * @return Lista de Objetos productos poblados.
     */
    public List getProductosByCriteria(Map criteria);

    /**
     * Registra la información de un nuevo producto
     * 
     * @param producto
     *            el objeto a ser insertado
     * @param usuario
     *            objeto conteniendo información del usuario invocador
     */
    public void insertProducto(Producto producto, Usuario usuario);

    /**
     * Actualiza la informacion de un producto
     * 
     * @param producto
     *            el objeto a ser acualizado
     * @param usuario
     *            objeto conteniendo información del usuario invocador
     */
    public void updateProducto(Producto producto, Usuario usuario);

    /**
     * Actualiza el Indicador de Generacion de Stickers, el cual es Invocado
     * desde el Mantenimiento de Productos
     * 
     * @param codigoPais
     *            Codigo del Pais del usuario invocador.
     */
    public void updateProductoIndicadorGeneracionStickersByPais(
            Producto producto, Usuario usuario);

    /**
     * Elimina un producto de la base de datos en base a informacion que se
     * almacene en un bean producto.
     * 
     * @param producto
     *            Producto con algunos atributos poblados que serviran de filtro
     * @param usuario
     *            objeto conteniendo información del usuario invocador
     */
    public void removeProducto(Producto producto, Usuario usuario);
    
    /**
     * Obtiene la informacion de los productos en Stock
     * 
     * @param criteria
     *            Objeto que sirve para almacenar los criterios.
     * @return Lista de Objetos Productos poblados.
     */
    public List getProductoStockList(Map criteria);
}
