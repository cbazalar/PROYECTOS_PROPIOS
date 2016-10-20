/*
 * Created on 08/11/2005 17:24:42 AM biz.belcorp.ssicc.service.MenuService
 */
package biz.belcorp.ssicc.service;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Menu;
import biz.belcorp.ssicc.dao.model.MenuOpciones;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MenuService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public interface MenuService extends Service {

    
    /**
     * Obtiene todos los menues
     */
    public List getMenues(Menu menu);

    /**
     * Obtiene todos los menues tomando como criterios de busqueda los valores
     * enviados a traves de un Map
     */
    public List getMenuesByCriteria(Map criteria);

    /**
     * Obtiene todos los menues a los cuales tiene acceso el usuario
     * 
     * @param usuario
     *            El objeto Usuario de la sesin.
     */
    public List getMenuesByUsuario(final String codigoPais, final String username, final String codigoIdioma, int nroSession);

    /**
     * Obtiene la informacin del menu en base al cdigo. Adicionalmente el
     * valor del objeto Locale permite obtener el valor de la descripcion a
     * mostrar por defecto ya que se trata de una entidad multilenguaje
     * 
     * @param codigo
     *            el codigo del menu
     * @param codigoIdioma
     *            el codigo del idioma en el que va a ser mostrada la
     *            informacion
     * @return menu objeto menu poblado
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
     *            el usuario que registra la informacin
     */
    public void insertMenu(Menu menu, Usuario usuario);

    /**
     * Actualiza la informacion de un menu
     * 
     * @param menu
     *            el objeto a ser actualizado
     * @param usuario
     *            el usuario que actualiza la informacin
     */
    public void updateMenu(Menu menu, Usuario usuario);

    /**
     * Elimina un menu de la base de datos en base a su codigo
     * 
     * @param codigo
     *            el codigo del menu
     * @param usuario
     *            el usuario que elimina la informacin
     */
    public void removeMenu(final String codigo, Usuario usuario);

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
