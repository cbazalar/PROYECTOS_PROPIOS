/*
 * Created on 02/08/2005 06:34:26 PM biz.belcorp.privilege.dao.AccesoDAO
 */
package biz.belcorp.ssicc.dao;

import java.util.List;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Acceso;
import biz.belcorp.ssicc.dao.model.AccesoPK;
import biz.belcorp.ssicc.dao.model.Usuario;

/**
 * TODO Include class description here.
 * <p>
 * <a href="AccesoDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramírez </a>
 */
public interface AccesoDAO extends DAO {

    /**
     * Obtiene la informacion del acceso en base a su primaryKey. La excepcion
     * ObjectRetrievalFailureException Runtime Exception es lanzada si no es
     * encontrada.
     * 
     * @param primaryKey
     *            el primaryKey del acceso
     * @return acceso objeto acceso poblado
     */
    public Acceso getAcceso(final AccesoPK primaryKey);

    /**
     * Registra la informacin de un nuevo acceso
     * 
     * @param acceso
     *            el objeto a ser insertado
     * @param usuario
     *            objeto conteniendo información del usuario invocador
     */
    public void insertAcceso(Acceso acceso, Usuario usuario);

    /**
     * Actualiza la informacion de un acceso
     * 
     * @param acceso
     *            el objeto a ser acualizado
     * @param usuario
     *            objeto conteniendo informacióndel usuario invocador
     */
    public void updateAcceso(Acceso acceso, Usuario usuario);

    /**
     * Elimina un acceso de la base de datos en base a su primaryKey
     * 
     * @param primaryKey
     *            el primaryKey del acceso
     */
    public void removeAcceso(final AccesoPK primaryKey);

    /**
     * Obtiene una lista con los objetos Acceso con los cuales está relacionado
     * un rol determinado
     * 
     * @return
     */
    public List getAccesosByRol(final String codigoPais, final String codigoRol, final String codigoIdioma);
    
    
    
    /**
     * Obtiene una lista de objetos map conteniendo la información de las
     * opciones a las cuales tiene acceso un determinado rol
     * 
     * @param codigoRol
     *            codigo del rol
     * @param codigoLenguaje
     *            codigo del lenguaje usado para mostrar la descripcion del menu
     * @return lista con los objetos map poblados
     */
    public List getAccesosMap(final String codigoRol, final String codigoLenguaje);

    /**
     * Elimina las opciones del menu de la base de datos en base a su primaryKey
     * 
     * @param primaryKey
     *            el primaryKey del acceso
     */
    
    public void removeOpcioMenuRol(AccesoPK primaryKey);
    
    public void insertOpcioMenuRol(Acceso acceso, Usuario usuario);
    
}
