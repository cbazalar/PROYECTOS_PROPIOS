/*
 * Created on 21/03/2005 03:01:28 PM
 * biz.belcorp.ssicc.dao.ibatis.RolDAOiBatis
 */
package biz.belcorp.ssicc.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.RolDAO;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.OpcionMenuRol;
import biz.belcorp.ssicc.dao.model.Rol;
import biz.belcorp.ssicc.dao.model.RolPK;
import biz.belcorp.ssicc.dao.model.Usuario;

/**
 * TODO Include class description here.
 * <p>
 * <a href="RolDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Repository("rolDAO")
public class RolDAOiBatis extends BaseDAOiBatis implements RolDAO {

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.RolDAO#getRoles(biz.belcorp.ssicc.model.Rol)
     */
    public List getRoles(Rol rol) {
        List roles = getSqlMapClientTemplate().queryForList("RolSQL.getRoles", rol);
        return roles;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.RolDAO#getRolesByCriteria(java.util.Map)
     */
    public List getRolesByCriteria(Map criteria) {
        List roles = getSqlMapClientTemplate().queryForList("RolSQL.getRolesByCriteria", criteria);
        return roles;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.RolDAO#getRol(biz.belcorp.ssicc.model.RolPK, java.lang.String)
     */
    public Rol getRol(RolPK primaryKey, String codigoIdioma) {
        Rol rol = (Rol) getSqlMapClientTemplate().queryForObject("RolSQL.getRol", primaryKey);
        if (rol == null) {
            throw new ObjectRetrievalFailureException(Rol.class, primaryKey);
        }

        return rol;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.RolDAO#insertRol(biz.belcorp.ssicc.model.Rol,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void insertRol(Rol rol, Usuario usuario) {
        // Obtenemos el valor del nuevo PK del rol
        Map params = new HashMap();
        params.put("codigoPais", rol.getCodigoPais());
        RolPK rolPK = getNextPK(params);
        
        // Asigmanos el valor del codigo al rol
        rol.setCodigo(rolPK.getCodigo());
        getSqlMapClientTemplate().update("RolSQL.insertRol", rol);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.RolDAO#updateRol(biz.belcorp.ssicc.model.Rol,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void updateRol(Rol rol, Usuario usuario) {
        getSqlMapClientTemplate().update("RolSQL.updateRol", rol);

        /* Actualizamos la descripcion del rol 
        DescripcionObjeto d = new DescripcionObjeto();
        d.setCodigo(rol.getCodigo());
        d.setCodigoLenguaje(usuario.getLocale().getLanguage());
        d.setDescripcion(rol.getDescripcion());

        // Actualizamos la descripcion del rol
        int rows = getSqlMapClientTemplate().update("RolSQL.updateDescripcionRol", d);
        
        // Si el metodo devuelve cero filas modificadas es porque el
        // rol aun no tiene descripcion, por tanto la insertamos
        if(rows == 0) {
            getSqlMapClientTemplate().update("RolSQL.insertDescripcionRol", d);
        }
        */
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.RolDAO#removeRol(biz.belcorp.ssicc.model.RolPK)
     */
    public void removeRol(RolPK primaryKey) {
        getSqlMapClientTemplate().update("RolSQL.removeRol", primaryKey);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.dao.RolDAO#getNextPK(java.util.Map)
     */
    public RolPK getNextPK(Map params) {
        // Creamos el objeto a devolver
        RolPK rolPK = (RolPK) getSqlMapClientTemplate().queryForObject("RolSQL.getNextPK", params);
        return rolPK;
    }

    
    public List getOpcionMenuRolesByCriteria(OpcionMenuRol  opcionMenuRol){
        List opcionesMenuRoles = getSqlMapClientTemplate().queryForList("RolSQL.getOpcionMenuRolesByCriteria", opcionMenuRol);
        return opcionesMenuRoles;
    
    	
    }
    
    public void insertOpcionMenuRoles(OpcionMenuRol  opcionMenuRol){
    	
    	getSqlMapClientTemplate().update("RolSQL.insertOpcionMenuRoles",opcionMenuRol);
    }
    
    public void removeOpcionMenuRoles(OpcionMenuRol  opcionMenuRol){
    	getSqlMapClientTemplate().update("RolSQL.removeOpcionMenuRoles",opcionMenuRol);
    }

    public List getRolesActivos(Rol rol) {
        List roles = getSqlMapClientTemplate().queryForList("RolSQL.getRolesActivos", rol);
        return roles;
    }
}