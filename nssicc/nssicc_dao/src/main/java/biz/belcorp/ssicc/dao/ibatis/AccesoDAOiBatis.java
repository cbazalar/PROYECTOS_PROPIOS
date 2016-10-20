/*
 * Created on 02/08/2005 06:36:02 PM
 * biz.belcorp.privilege.dao.ibatis.AccesoDAOiBatis
 */
package biz.belcorp.ssicc.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.AccesoDAO;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Acceso;
import biz.belcorp.ssicc.dao.model.AccesoPK;
import biz.belcorp.ssicc.dao.model.Usuario;

/**
 * TODO Include class description here.
 * <p>
 * <a href="AccesoDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Repository("accesoDAO")
public class AccesoDAOiBatis extends BaseDAOiBatis implements AccesoDAO {

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.AccesoDAO#getAcceso(biz.belcorp.ssicc.model.AccesoPK)
     */
    public Acceso getAcceso(AccesoPK primaryKey) {
        Acceso acceso = (Acceso) getSqlMapClientTemplate().queryForObject("AccesoSQL.getAcceso", primaryKey);
        if (acceso == null) {
            throw new ObjectRetrievalFailureException(Acceso.class, primaryKey);
        }
        return acceso;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.AccesoDAO#insertAcceso(biz.belcorp.ssicc.model.Acceso,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void insertAcceso(Acceso acceso, Usuario usuario) {
        getSqlMapClientTemplate().update("AccesoSQL.insertAcceso", acceso);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.AccesoDAO#updateAcceso(biz.belcorp.ssicc.model.Acceso,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void updateAcceso(Acceso acceso, Usuario usuario) {
        getSqlMapClientTemplate().update("AccesoSQL.updateAcceso", acceso);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.AccesoDAO#removeAcceso(biz.belcorp.ssicc.model.AccesoPK)
     */
    public void removeAcceso(AccesoPK primaryKey) {
        getSqlMapClientTemplate().update("AccesoSQL.removeAcceso", primaryKey);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.dao.RolDAO#getAccesosByRol(java.lang.String, java.lang.String, java.lang.String)
     */
    public List getAccesosByRol(String codigoPais, String codigoRol, String codigoIdioma) {
        // Ya que necesitamos enviar el valor del idioma, ademas del pk
        // es que usamos un map con dichos objetos
        Map map = new HashMap();
        map.put("codigoPais", codigoPais);
        map.put("codigoRol", codigoRol);
        map.put("codigoIdioma", codigoIdioma);

        List accesos = getSqlMapClientTemplate().queryForList("AccesoSQL.getAccesosByRol", map);

        return accesos;
    }
    
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.dao.AccesoDAO#getAccesosMap(java.lang.String, java.lang.String)
     */
    public List getAccesosMap(String codigoRol, String codigoLenguaje) {
        Map map = new HashMap();
        map.put("codigoRol", codigoRol);
        map.put("codigoLenguaje", codigoLenguaje);
        
        return getSqlMapClientTemplate().queryForList("AccesoSQL.getAccesosMap", map);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.AccesoDAO#removeAcceso(biz.belcorp.ssicc.model.AccesoPK)
     */
    public void removeOpcioMenuRol(AccesoPK primaryKey) {
        getSqlMapClientTemplate().update("AccesoSQL.removeOpcioMenuRol", primaryKey);
    }
    
    
    public void insertOpcioMenuRol(Acceso acceso, Usuario usuario) {
        getSqlMapClientTemplate().update("AccesoSQL.insertOpcioMenuRol", acceso);
    }
    

}
