/*
 * Created on 25-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.spusicc.sessionexperte;

import java.util.List;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.model.LabelDatosProductoValue;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.model.ProductoDeProgramaSessionExperte;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="EstructuraArchivoDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public interface MantenimientoSSEProductoDeProgramaSSEDAO extends DAO {

	/**
	 * Obtiene un listado de los productos del Programa Session Experte indicado.
	 * @param producto Objeto de tipo Programa session experte.
	 * */
	public List getProductosDeProgramaSessionExperteByCriteria(ProductoDeProgramaSessionExperte producto);

	/**
	 * Obtiene un listado de los productos del Programa Session Experte indicado.
	 * @param producto Objeto de tipo ProductoDeProgramaSessionExperte.
	 * @return Lista de objetos de tipo BaseGenerico (codigo y descripcion).
	 * */
	public List getBaseProductosDeProgramaSessionExperteByCriteria(ProductoDeProgramaSessionExperte producto);
	
	/**
	 * Inserta un Producto de Programa SessionExperte
	 * @param producto  Producto de Programa SessionExperte.
	 * @param usuario Usuario que inserta.
	 */
	public void insertProductoDeProgramaSessionExperte(ProductoDeProgramaSessionExperte producto, Usuario usuario);

	/**
	 * Actualiza un Producto de Programa SessionExperte
	 * @param producto Producto de Programa SessionExperte.
	 * @param usuario Usuario que actualiza.
	 */
	public void updateProductoDeProgramaSessionExperte(ProductoDeProgramaSessionExperte producto, Usuario usuario);

	/**
	 * Cambia el estado a INACTIVO del Producto de Programa SessionExperte (No lo elimina).
	 * @param producto Producto de Programa SessionExperte.
	 * @param usuario Usuario que elimina.
	 */
	public void updateInactivarProductoDeProgramaSessionExperte(ProductoDeProgramaSessionExperte producto, Usuario usuario);
	
	/**
	 * Obtiene los datos de un producto del Programa Session Experte indicado buscando en la tabla maestra de Productos.
	 * @param producto Objeto de tipo Producto de Programa session experte.
	 * @return LabelDatosProductoValue.
	 * */
	public LabelDatosProductoValue getDatosProductoByCriteria(ProductoDeProgramaSessionExperte producto);

	public void insertProductoDeProgramaCicloSSE(ProductoDeProgramaSessionExperte cabecera, Usuario usuario);

	public List getCiclosDeProductoSessionExperteByCriteria(ProductoDeProgramaSessionExperte producto);
	
	public void updateProductoDeProgramaCicloSSE(ProductoDeProgramaSessionExperte producto, Usuario usuario);

	public ProductoDeProgramaSessionExperte getFirstFromCiclosDeProductoSSEByCriteria(ProductoDeProgramaSessionExperte producto);
	
	public String getCodigoProductoPrograma(ProductoDeProgramaSessionExperte producto);

	public void deleteProductoDeProgramaCicloSessionExperte(ProductoDeProgramaSessionExperte productoCiclo, Usuario usuario);
	
    /*
    public List getEstructuraArchivo(InterfazPK interfazPK);
    public List getEstructuraArchivoByCriteria(EstructuraArchivo criteria);
    public EstructuraArchivo getItemEstructuraArchivo(EstructuraArchivoPK estructuraArchivoPK);
    public void insertEstructuraArchivo(EstructuraArchivo estructuraArchivo, Usuario usuario);
    public void updateEstructuraArchivo(EstructuraArchivo estructuraArchivo, Usuario usuario);
    public void removeEstructuraArchivo(final EstructuraArchivoPK primaryKey);
    public String getSiguienteCodigo(InterfazPK pk);
    public int getSiguientePosicion(InterfazPK pk);
    */
}
