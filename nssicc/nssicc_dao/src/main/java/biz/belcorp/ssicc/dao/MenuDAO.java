/*
 * Created on 19/04/2005 04:43:53 PM biz.belcorp.ssicc.dao.MenuDAO
 */
package biz.belcorp.ssicc.dao;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Menu;
import biz.belcorp.ssicc.dao.model.MenuOpciones;
import biz.belcorp.ssicc.dao.model.Usuario;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MenuDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramírez </a>
 */
public interface MenuDAO extends DAO {
    /**
     * Obtiene un listado de todos los menues
     * 
     * @param menu
     *            objeto Menu cuyos atributos son usados como criterios de
     *            búsqueda
     * @return Lista de objetos Menu poblados
     */
    public List getMenues(Menu menu);

    /**
     * Obtiene un listado de todos los menues en base a ciertos criterios los
     * cuales son enviados a través de un Map.
     * 
     * @param criteria
     *            objeto Map cuyos atributos son usados como criterios de
     *            bsqueda
     * @return Lista de objetos Menu poblados
     */
    public List getMenuesByCriteria(Map criteria);

    /**
     * Obtiene un listado de todos los menues a los que tiene acceso el usuario
     * enviado como parametro.
     * 
     * @param usuario
     *            objeto Usuario que ha iniciado una sesin en la aplicación.
     * @return Lista de objetos Menu poblados
     */
    public List getMenuesByUsuario(final String codigoPais, final String username, final String codigoIdioma, int nroSession);

    /**
     * Obtiene la informacion del menu en base a su codigo. Debido a que es una
     * entidad cuya información es multilenguaje, recibe como parámetro
     * adicional el valor del objeto Locale el cual contiene el lenguaje
     * seleccionado por el usuario. La excepcion ObjectRetrievalFailureException
     * Runtime Exception es lanzada si no es encontrada.
     * 
     * @param codigo
     *            el codigo del menu
     * @param codigoIdioma
     *            el codigo del idioma usado para mostrar la descripcion
     * @return objeto menu poblado
     */
    public Menu getMenu(final String codigo, final String codigoIdioma);
    
    /**
     * Obtiene la informacion del menu JSF en base al codigo. Adicionalmente el
     * valor del objeto Locale permite obtener el valor de la descripcion a
     * mostrar por defecto ya que se trata de una entidad multilenguaje
     * @param codigo
     * @param codigoIdioma
     * @return
     */
    public Menu getMenuJSF(String codigo, String codigoIdioma);

    /**
     * Registra la informacin de un nuevo menu
     * 
     * @param menu
     *            el objeto a ser insertado
     * @param usuario
     *            objeto conteniendo información del usuario invocador
     */
    public void insertMenu(Menu menu, Usuario usuario);

    /**
     * Actualiza la informacion de un menu
     * 
     * @param menu
     *            el objeto a ser acualizado
     * @param usuario
     *            objeto conteniendo información del usuario invocador
     */
    public void updateMenu(Menu menu, Usuario usuario);

    /**
     * Elimina un menu de la base de datos en base a su codigo
     * 
     * @param codigo
     *            el codigo del menu
     */
    public void removeMenu(final String codigo);
    
    public List getMenuesByCriteriaRol(Map criteria);
    
    /**
     * Devuelve informacion correspondiente a la opcion del menu en la que se encuentra el sistema 
     * @param params
     * @return
     */
    public String getInformacionMenu(Map params);
    
    public List getMenuOpciones(Map params);
    
    public void insertMenuOpciones(MenuOpciones menuOpciones);
    
    public void updateMenuOpciones(MenuOpciones menuOpciones);
    
    public void deleteMenuOpciones(String codigoMenu);
    
    public List getMenuOpcionesAsignadas(String codigoMenu);
    
    public List getMenuOpcionesFaltantes(String codigoMenu);
    
    /* NSSICC */
    /**
     * Lista obtenida de Opciones de Menu por Rol 
     * @param criteria
     * @return
     */
    public List getMenuesByCriteriaRolNSSiCC(Map criteria);
    
    
}
