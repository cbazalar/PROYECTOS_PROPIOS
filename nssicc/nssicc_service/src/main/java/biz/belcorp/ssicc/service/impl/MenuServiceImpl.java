/*
 * Created on 20/04/2005 04:07:20 PM
 * biz.belcorp.ssicc.service.impl.MenuServiceImpl
 */
package biz.belcorp.ssicc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.MenuDAO;
import biz.belcorp.ssicc.dao.model.Menu;
import biz.belcorp.ssicc.dao.model.MenuOpciones;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.MenuService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MenuServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Service("menuService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MenuServiceImpl extends BaseService implements MenuService {

	@Resource(name="menuDAO")
    private MenuDAO menuDAO;

   

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.service.MenuService#getMenues(biz.belcorp.ssicc.model.Menu)
     */
    public List getMenues(Menu menu) {
        return menuDAO.getMenues(menu);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.service.MenuService#getMenuesByCriteria(java.util.Map)
     */
    public List getMenuesByCriteria(Map criteria) {
        return menuDAO.getMenuesByCriteria(criteria);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.service.MenuService#getMenuesByUsuario(java.lang.String,java.lang.String,java.lang.String)
     */
    public List getMenuesByUsuario(String codigoPais, String username, String codigoIdioma, int nroSession) {
        return menuDAO.getMenuesByUsuario(codigoPais, username, codigoIdioma , nroSession);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.service.MenuService#getMenu(java.lang.String,
     *      java.lang.String)
     */
    public Menu getMenu(String codigo, String codigoIdioma) {
        return menuDAO.getMenu(codigo, codigoIdioma);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.service.MenuService#insertMenu(biz.belcorp.ssicc.model.Menu,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void insertMenu(Menu menu, Usuario usuario) {
        // Obtenemos el nivel del menu en base al valor de su padre
        int nivel = 0;

        if (StringUtils.isNotBlank(menu.getCodigoPadre())) {
            Menu menuPadre = menuDAO.getMenu(menu.getCodigoPadre(), usuario.getIdioma().getCodigo());
            nivel = Integer.parseInt(menuPadre.getNivel()) + 1;
        }
        // Asignamos el valor del nivel
        menu.setNivel(Integer.toString(nivel));

        // Seteamos los valores por defecto
        menu.setEstado(Constants.ESTADO_ACTIVO);

        // Finalmente invocamos al dao
        menuDAO.insertMenu(menu, usuario);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.service.MenuService#updateMenu(biz.belcorp.ssicc.model.Menu,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void updateMenu(Menu menu, Usuario usuario) {
        menuDAO.updateMenu(menu, usuario);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.service.MenuService#removeMenu(java.lang.String,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void removeMenu(String codigo, Usuario usuario) {
        // Actualizamos el estado del Menu
        try {
            Menu Menu = menuDAO.getMenu(codigo, usuario.getIdioma().getCodigo());
            Menu.setEstado(Constants.ESTADO_INACTIVO);

            // Actualizamos el Menu
            menuDAO.updateMenu(Menu, usuario);
        }
        catch (ObjectRetrievalFailureException orfe) {
            log.warn(orfe.getMessage());
        }
    }
    public List getMenuesByCriteriaRol(Map criteria){
    	return menuDAO.getMenuesByCriteriaRol(criteria);
    	
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.service.MenuService#getInformacionMenu(java.util.Map)
     */
    public String getInformacionMenu(Map params) {
    	return menuDAO.getInformacionMenu(params);
    }
    
    
    public List getMenuOpciones(Map params){
    	return menuDAO.getMenuOpciones(params);
    }
    
    public void insertMenuOpciones(MenuOpciones menuOpciones){
    	menuDAO.insertMenuOpciones(menuOpciones);
    }
    
    public void updateMenuOpciones(MenuOpciones menuOpciones){
    	menuDAO.updateMenuOpciones(menuOpciones);
    }
    
    public void deleteMenuOpciones(String codigoMenu){
    	menuDAO.deleteMenuOpciones(codigoMenu);
    }
    
    
    public List getMenuOpcionesAsignadas(String codigoMenu){
    	return menuDAO.getMenuOpcionesAsignadas(codigoMenu);
    }
    
    public List getMenuOpcionesFaltantes(String codigoMenu){
    	return menuDAO.getMenuOpcionesFaltantes(codigoMenu);    	
    }
    
    /* NSSiCC */ 

     /* (non-Javadoc)
     * @see biz.belcorp.ssicc.service.seguridad.MenuService#getMenuJSF(java.lang.String, java.lang.String)
     */
    public Menu getMenuJSF(String codigo, String codigoIdioma) {
    	return menuDAO.getMenuJSF(codigo, codigoIdioma);
    }


    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.service.MenuService#getMenuesByCriteriaRolNSSiCC(java.util.Map)
     */
    public List getMenuesByCriteriaRolNSSiCC(Map criteria) {
    	return menuDAO.getMenuesByCriteriaRolNSSiCC(criteria);
    }

}
