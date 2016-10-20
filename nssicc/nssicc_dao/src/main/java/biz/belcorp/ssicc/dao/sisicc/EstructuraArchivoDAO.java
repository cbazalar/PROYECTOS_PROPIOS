/*
 * Created on 25-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc;

import java.util.List;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.EstructuraArchivo;
import biz.belcorp.ssicc.dao.sisicc.model.EstructuraArchivoPK;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;

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

public interface EstructuraArchivoDAO extends DAO {
    /**
     * Obtiene un listado de toda la estructura del archvo de la interfaz
     * en base a la llave primaria de la interfaz.
     */
    public List getEstructuraArchivo(InterfazPK interfazPK);
    
    /**
     * Obtiene una lista de la estructura de un archivo de interfaz en base 
     * a un conjunto de parametros que son pasados en objeto EstructuraArchivo.
     *  
     * @param criteria
     * Parametros de criterio de busqueda.
     * 
     * @return
     * Lista de objetos de tipo EstructuraArchivo, poblados.
     */
    public List getEstructuraArchivoByCriteria(EstructuraArchivo criteria);
    
    /**
     * Obtiene un item de la estructura del archivo de interfaz, en base a su llave primaria.
     * 
     * @param estructuraArchivoPK
     * Llave primaria.
     * 
     * @return
     * Objeto Tipo EstructuraArchivo, poblado.
     */
    public EstructuraArchivo getItemEstructuraArchivo(EstructuraArchivoPK estructuraArchivoPK);

    /**
     * Registra la informacin de un nuevo Item de la estructura del archivo de la Interfaz.
     * 
     * @param estructuraArchivo
     * Objeto de tipo EstructuraArchivo, donde va toda la informacion 
     * de un item de la estructura del archivo de la interfaz.
     * 
     * @param usuario
     * Objeto de tipo Usuario, quien hace la invocacin. 
     */       
    public void insertEstructuraArchivo(EstructuraArchivo estructuraArchivo, Usuario usuario);

    /**
     * Actualiza la informacin un Item de la estructura del archivo de la Interfaz.
     * 
     * @param estructuraArchivo
     * Objeto de tipo EstructuraArchivo, donde va toda la informacion 
     * de un item de la estructura del archivo de la interfaz.
     * 
     * @param usuario
     * Objeto de tipo Usuario, quien hace la invocacin. 
     */
    public void updateEstructuraArchivo(EstructuraArchivo estructuraArchivo, Usuario usuario);
    
    /**
     * Elimina un item de la Estructura del archivo de la Interfaz en base a su llave primaria.
     * 
     * @param primaryKey
     * Llave primaria del item.
     */
    public void removeEstructuraArchivo(final EstructuraArchivoPK primaryKey);
    
    /**
     * Obtiene el siguiente codigo de una entrada en la estructura del archivo.
     * 
     * @param pk
     * Llave primaria de la interfaz.
     * 
     * @return
     * siguiente codigo.
     */
    public String getSiguienteCodigo(InterfazPK pk);
    
    /**
     * Obtiene la siguiente posicion del campo, de la estructura del archivo de interfaz.
     * 
     * @param pk
     * Llave primaria de la interfaz.
     * 
     * @return
     * Siguiente posicion.
     */
    public int getSiguientePosicion(InterfazPK pk);
}
