/*
 * Created on 25/02/2005 12:28:50 PM biz.belcorp.ssicc.dao.RolDAO
 */
package biz.belcorp.ssicc.dao;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.OpcionMenuRol;
import biz.belcorp.ssicc.dao.model.Rol;
import biz.belcorp.ssicc.dao.model.RolPK;
import biz.belcorp.ssicc.dao.model.Usuario;

/**
 * TODO Include class description here.
 * <p>
 * <a href="RolDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramírez </a>
 */
public interface RolDAO extends DAO {

    /**
     * Obtiene un listado de todos los roles
     */
    public List getRoles(Rol rol);

    /**
     * Obtiene un listado de todos los roles en base a ciertos criterios los
     * cuales son enviados a través de un Map.
     * 
     * @param criteria
     *            objeto Map cuyos atributos son usados como criterios de
     *            búsqueda
     * @return Lista de objetos Rol poblados
     */
    public List getRolesByCriteria(Map criteria);

    /**
     * Obtiene la informacion del rol en base a su codigo. Debido a que es una
     * entidad cuya informacin es multilenguaje, recibe como parámetro
     * adicional el valor del objeto Locale el cual contiene el lenguaje
     * seleccionado por el usuario. La excepcion ObjectRetrievalFailureException
     * Runtime Exception es lanzada si no es encontrada.
     * 
     * @param primaryKey
     *            la llave primaria del rol
     * @param codigoIdioma
     *            el valor del lenguaje usado para mostrar la descripcion
     * @return objeto rol poblado
     */
    public Rol getRol(final RolPK primaryKey, final String codigoIdioma);

    /**
     * Registra la informacin de un nuevo rol
     * 
     * @param rol
     *            el objeto a ser insertado
     * @param usuario
     *            objeto conteniendo información del usuario invocador
     */
    public void insertRol(Rol rol, Usuario usuario);

    /**
     * Actualiza la informacion de un rol
     * 
     * @param rol
     *            el objeto a ser acualizado
     * @param usuario
     *            objeto conteniendo información del usuario invocador
     */
    public void updateRol(Rol rol, Usuario usuario);

    /**
     * Elimina un rol de la base de datos en base a su codigo
     * 
     * @param primaryKey
     *            la llave primaria del rol
     */
    public void removeRol(final RolPK primaryKey);

    /**
     * Obtiene el valor del siguiente PK para el rol que se va a insertar en la
     * base de datos, los parametros enviados son usados para calcular el valor
     * de la llave primaria.
     * 
     * @param params
     * @return El nuevo RolPK a asignar
     */
    public RolPK getNextPK(Map params);
             
     
    
    /**
     * @param opcionMenuRol
     * @return
     */
    public List getOpcionMenuRolesByCriteria(OpcionMenuRol  opcionMenuRol);
    
    /**
     * @param opcionMenuRol
     */
    public void insertOpcionMenuRoles(OpcionMenuRol  opcionMenuRol);
    
    /**
     * @param opcionMenuRol
     */
    public void removeOpcionMenuRoles(OpcionMenuRol  opcionMenuRol);
    
    /**
     * @param rol
     * @return
     */
    public List getRolesActivos(Rol rol);
    
}