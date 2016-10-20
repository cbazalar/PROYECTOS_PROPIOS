/*
 * Created on 20/04/2005 10:15:02 AM biz.belcorp.ssicc.service.RolService
 */
package biz.belcorp.ssicc.service;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.OpcionMenuRol;
import biz.belcorp.ssicc.dao.model.Rol;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="RolService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public interface RolService extends Service {

	   /**
     * Obtiene todos los roles
     */
    public List getRoles(Rol rol);

    /**
     * Obtiene todos los roles tomando como criterios de busqueda los valores
     * enviados a traves de un Map
     */
    public List getRolesByCriteria(Map criteria);

    /**
     * Obtiene la información del rol en base al código. Adicionalmente el valor
     * del objeto Locale permite obtener el valor de la descripcion a mostrar
     * por defecto ya que se trata de una entidad multilenguaje
     * 
     * @param codigoPais
     *            el codigo del pais al que pertenece el rol
     * @param codigo
     *            el codigo del rol
     * @return rol objeto rol poblado
     */
    public Rol getRol(final String codigoPais, final String codigo, String codigoIdioma);

    /**
     * Registra la información de un nuevo rol
     * 
     * @param rol
     *            el objeto a ser insertado
     * @param usuario
     *            el usuario que registra la información
     */
    public void insertRol(Rol rol, Usuario usuario);

    /**
     * Actualiza la informacion de un rol
     * 
     * @param rol
     *            el objeto a ser actualizado
     * @param usuario
     *            el usuario que actualiza la información
     */
    public void updateRol(Rol rol, Usuario usuario);

    /**
     * Elimina un rol de la base de datos en base a su codigo
     * 
     * @param codigoPais
     *            el codigo del pais al que pertenece el rol
     * @param codigo
     *            el codigo del rol
     * @param usuario
     *            el usuario que elimina la información
     */
    public void removeRol(final String codigoPais, final String codigo, Usuario usuario);
    
    
    public List getOpcionMenuRolesByCriteria(OpcionMenuRol  opcionMenuRol);
    
    public void insertOpcionMenuRoles(OpcionMenuRol  opcionMenuRol);
    
    public void removeOpcionMenuRoles(OpcionMenuRol  opcionMenuRol);
    
    /**
     * Obtiene una lista de los accesos asignados a un determinado rol, cuyo
     * codigo es pasado como parámetro. La relacion de accesos es obtenida a
     * través de una lista de objetos del tipo map, cada contiene tanto
     * información del menu al cual el rol tiene acceso como de los accesos
     * asignados a cada subopcion de menu.
     * 
     * @param codigoRol
     *            codigo del rol
     * @param codigoLenguaje
     *            codigo del lenguaje en el que se mostrarán las descripciones
     * @return lista de objetos map poblados
     */
    public List getAccesosAsignados(final String codigoRol, final String codigoLenguaje);

    /**
     * Actualiza los accesos asignados a un rol
     * 
     * @param accesos lista conteniendo los objetos Acceso
     * @param usuario el usuario que actualiza la informacion
     */
    public void updateAccesosAsignados(List accesos, Usuario usuario);
    
    /**
     * @param rol
     * @return
     */
    public List getRolesActivos(Rol rol);
    
}
