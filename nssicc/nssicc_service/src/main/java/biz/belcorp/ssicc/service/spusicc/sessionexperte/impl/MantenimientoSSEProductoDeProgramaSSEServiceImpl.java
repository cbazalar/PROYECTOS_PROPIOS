/*
 * Created on 25-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.service.spusicc.sessionexperte.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.MantenimientoSSEProductoDeProgramaSSEDAO;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.model.LabelDatosProductoValue;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.model.ProductoDeProgramaSessionExperte;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.sessionexperte.MantenimientoSSEProductoDeProgramaSSEService;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="EstructuraArchivoServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:ivega@belcorp.biz">Isabel Vega Palomino</a>
 * 
 */
@Service("spusicc.mantenimientoSSEProductoDeProgramaSSEService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoSSEProductoDeProgramaSSEServiceImpl extends BaseService implements MantenimientoSSEProductoDeProgramaSSEService {

	@Resource(name="spusicc.sessionexperte.mantenimientoSSEProductoDeProgramaSSEDAO")
	private MantenimientoSSEProductoDeProgramaSSEDAO mantenimientoDAO;
	
	/**
	 * Obtiene un listado de los productos del Programa Session Experte indicado.
	 * @param producto Objeto de tipo Programa session experte.
	 * @return Lista de objetos de tipo ProductoDeProgramaSessionExperte.
	 * */
	public List getProductosDeProgramaSessionExperteByCriteria(ProductoDeProgramaSessionExperte producto){
		return this.mantenimientoDAO.getProductosDeProgramaSessionExperteByCriteria(producto);
	}
	
	/**
	 * Obtiene un listado de los productos del Programa Session Experte indicado.
	 * @param producto Objeto de tipo ProductoDeProgramaSessionExperte.
	 * @return Lista de objetos de tipo BaseGenerico (codigo y descripcion).
	 * */
	public List getBaseProductosDeProgramaSessionExperteByCriteria(ProductoDeProgramaSessionExperte producto){
		return this.mantenimientoDAO.getBaseProductosDeProgramaSessionExperteByCriteria(producto);
	}

	/**
	 * Obtiene un producto del Programa Session Experte indicado.
	 * @param producto Objeto de tipo Producto de Programa session experte.
	 * @return  ProductoDeProgramaSessionExperte.
	 * */
	public ProductoDeProgramaSessionExperte getFirstFromProductosDeProgramaSessionExperteByCriteria(ProductoDeProgramaSessionExperte producto){
		return (ProductoDeProgramaSessionExperte)(mantenimientoDAO.getProductosDeProgramaSessionExperteByCriteria(producto)).get(0);
	}

	/**
	 * Inserta un Producto de Programa SessionExperte
	 * @param producto  Producto de Programa SessionExperte.
	 * @param usuario Usuario que inserta.
	 */
	public void insertProductoDeProgramaSessionExperte(ProductoDeProgramaSessionExperte producto, Usuario usuario){
		producto.setEstadoRegistro(Constants.ESTADO_ACTIVO);
		this.mantenimientoDAO.insertProductoDeProgramaSessionExperte(producto, usuario);
	}

	/**
	 * Actualiza un Producto de Programa SessionExperte
	 * @param producto Producto de Programa SessionExperte.
	 * @param usuario Usuario que actualiza.
	 */
	public void updateProductoDeProgramaSessionExperte(ProductoDeProgramaSessionExperte producto, Usuario usuario){
		this.mantenimientoDAO.updateProductoDeProgramaSessionExperte(producto, usuario);
	}
	
	/**
	 * Cambia el estado a INACTIVO del Producto de Programa SessionExperte (No lo elimina).
	 * @param producto Producto de Programa SessionExperte.
	 * @param usuario Usuario que elimina.
	 */
	public void deleteProductoDeProgramaSessionExperte(ProductoDeProgramaSessionExperte producto, Usuario usuario){
		producto.setEstadoRegistro(Constants.ESTADO_INACTIVO);
		this.mantenimientoDAO.updateInactivarProductoDeProgramaSessionExperte(producto, usuario);
	}
	
	/**
	 * Obtiene los datos de un producto del Programa Session Experte indicado buscando en la tabla maestra de Productos.
	 * @param producto Objeto de tipo Producto de Programa session experte.
	 * @return LabelDatosProductoValue.
	 * */
	public LabelDatosProductoValue getDatosProductoByCriteria(ProductoDeProgramaSessionExperte producto){
		return this.mantenimientoDAO.getDatosProductoByCriteria(producto);
	}
	
	public void insertProductoDeProgramaCicloSSE(ProductoDeProgramaSessionExperte producto, Usuario usuario){
		this.mantenimientoDAO.insertProductoDeProgramaCicloSSE(producto, usuario);
	}
	
	public void updateProductoDeProgramaCicloSessionExperte(ProductoDeProgramaSessionExperte producto, Usuario usuario){
		this.mantenimientoDAO.updateProductoDeProgramaCicloSSE(producto, usuario);
	}
	
	public void deleteProductoDeProgramaCicloSessionExperte(ProductoDeProgramaSessionExperte productoCiclo, Usuario usuario){
		this.mantenimientoDAO.deleteProductoDeProgramaCicloSessionExperte(productoCiclo, usuario);
	}
	
	public List getCiclosDeProductoSessionExperteByCriteria(ProductoDeProgramaSessionExperte producto){
		return this.mantenimientoDAO.getCiclosDeProductoSessionExperteByCriteria(producto);
	}
	
	public ProductoDeProgramaSessionExperte getFirstFromCiclosDeProductoSSEByCriteria(ProductoDeProgramaSessionExperte producto){
		return this.mantenimientoDAO.getFirstFromCiclosDeProductoSSEByCriteria(producto);
	}
	
	public String getCodigoProductoPrograma(ProductoDeProgramaSessionExperte producto){
		return this.mantenimientoDAO.getCodigoProductoPrograma(producto);
	}
/*	
	public List getEstructuraArchivo(InterfazPK interfazPK) {
			return this.estructuraArchivoDAO.getEstructuraArchivo(interfazPK);
	}

	public void insertEstructuraArchivo(EstructuraArchivo estructuraArchivo, Usuario usuario) {
		try{
			// Verificamos que no exista un item con el mismo identificador
            // En caso no exista el objeto se lanzar una excepcion ObjectRetrievalFailureException			
			this.estructuraArchivoDAO.getItemEstructuraArchivo(new EstructuraArchivoPK(estructuraArchivo.getCodigoPais(), estructuraArchivo.getCodigoSistema(), estructuraArchivo.getCodigoInterfaz(), estructuraArchivo.getCodigo()));
			
            // Si ninguna excepcion es lanzada entonces el objeto ya existe
            // Por consiguiente lanzamos la excepcion correspondiente
			throw new InvalidIdentifierException(EstructuraArchivo.class, estructuraArchivo.getCodigo());
		}
		catch(ObjectRetrievalFailureException orfe){

            // Verificamos que no exista un item con la misma descripcin
            // Creamos el bean que nos servir como criterio de busqueda
			EstructuraArchivo criteria = new EstructuraArchivo();
			criteria.setCodigoPais(estructuraArchivo.getCodigoPais());
			criteria.setCodigoSistema(estructuraArchivo.getCodigoSistema());
			criteria.setCodigoInterfaz(estructuraArchivo.getCodigoInterfaz());
			criteria.setDescripcionCampo(estructuraArchivo.getDescripcionCampo());
			
			List items = this.estructuraArchivoDAO.getEstructuraArchivoByCriteria(criteria);
			
			if(items != null && items.size() > 0){
				throw new InvalidDescriptionException(EstructuraArchivo.class, estructuraArchivo.getDescripcionCampo());	
			}
			
			// Seteamos los valores por defecto
			InterfazPK pk = new InterfazPK(estructuraArchivo.getCodigoPais(), estructuraArchivo.getCodigoSistema(), estructuraArchivo.getCodigoInterfaz());
			estructuraArchivo.setEstado(Constants.ESTADO_ACTIVO);
        	estructuraArchivo.setCodigo(getSiguienteCodigo(pk));
        	estructuraArchivo.setPosicionCampo(getSiguientePosicion(pk));
			
			// Insertamos el nuevo sistema
			this.estructuraArchivoDAO.insertEstructuraArchivo(estructuraArchivo, usuario);
		}
	}

	public void updateEstructuraArchivo(EstructuraArchivo estructuraArchivo, Usuario usuario) {
        // Verificamos que no exista un item con la misma descripcin
        // Creamos el bean que nos servir como criterio de busqueda
		EstructuraArchivo criteria = new EstructuraArchivo();
		criteria.setCodigoPais(estructuraArchivo.getCodigoPais());
		criteria.setCodigoSistema(estructuraArchivo.getCodigoSistema());
		criteria.setCodigoInterfaz(estructuraArchivo.getCodigoInterfaz());
        criteria.setDescripcionCampo(estructuraArchivo.getDescripcionCampo());
        
        List items = this.estructuraArchivoDAO.getEstructuraArchivoByCriteria(criteria);
        
        if (items != null && items.size() > 0) {
            for (int i = 0; i < items.size(); i++) {
            	EstructuraArchivo o = (EstructuraArchivo) items.get(i);
                if (!(o.getCodigo().equals(estructuraArchivo.getCodigo()) && 
                		o.getCodigoPais().equals(estructuraArchivo.getCodigoPais()) && 
						o.getCodigoInterfaz().equals(estructuraArchivo.getCodigoInterfaz()) && 
						o.getCodigoSistema().equals(estructuraArchivo.getCodigoSistema()))) {
                    throw new InvalidDescriptionException(EstructuraArchivo.class, estructuraArchivo.getDescripcionCampo());
                }
            }
        }

        // Si no existe un item con la misma descripcin realizamos la actualizacin
        this.estructuraArchivoDAO.updateEstructuraArchivo(estructuraArchivo, usuario);		
	}

	public void removeEstructuraArchivo(EstructuraArchivoPK primaryKey, Usuario usuario) {
        // Actualizamos el estado del item
        try {
        	EstructuraArchivo item = this.estructuraArchivoDAO.getItemEstructuraArchivo(primaryKey);
            item.setEstado(Constants.ESTADO_INACTIVO);

            // Actualizamos el item
            this.estructuraArchivoDAO.updateEstructuraArchivo(item, usuario);
        }
        catch (ObjectRetrievalFailureException orfe) {
            log.warn(orfe.getMessage());
        }		
	}	

	public void removeEstructuraArchivo(EstructuraArchivoPK primaryKey) {
		// TODO Auto-generated method stub
		this.estructuraArchivoDAO.removeEstructuraArchivo(primaryKey);
	}

	public List getEstructuraArchivoByCriteria(EstructuraArchivo criteria) {
		// TODO Auto-generated method stub
		return this.estructuraArchivoDAO.getEstructuraArchivoByCriteria(criteria);
	}

	public EstructuraArchivo getItemEstructuraArchivo(EstructuraArchivoPK estructuraArchivoPK) {
		// TODO Auto-generated method stub
		return this.estructuraArchivoDAO.getItemEstructuraArchivo(estructuraArchivoPK);
	}

	public String getSiguienteCodigo(InterfazPK pk) {
		// TODO Auto-generated method stub
		return this.estructuraArchivoDAO.getSiguienteCodigo(pk);
	}

	public int getSiguientePosicion(InterfazPK pk) {
		// TODO Auto-generated method stub
		return this.estructuraArchivoDAO.getSiguientePosicion(pk);
	}

	public void updatePosicion(InterfazPK pk, Usuario usuario) {
		// TODO Auto-generated method stub
		EstructuraArchivo item;
		List estructura = this.estructuraArchivoDAO.getEstructuraArchivo(pk);
		int posicion = 0;
		for(int i=0; i<estructura.size(); i++){
			item = (EstructuraArchivo)estructura.get(i);
			posicion = i+1;			
			if(posicion != item.getPosicionCampo()){
				item.setPosicionCampo(posicion);
				this.estructuraArchivoDAO.updateEstructuraArchivo(item, usuario);
			}
		}
	}
*/

}
