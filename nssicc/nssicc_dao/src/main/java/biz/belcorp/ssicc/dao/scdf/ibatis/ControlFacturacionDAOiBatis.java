/*
 * Created on 11/11/2005 05:43:14 PM
 *
 * biz.belcorp.ssicc.dao.ibatis.ControlFacturacionDAOiBatis
 */
package biz.belcorp.ssicc.dao.scdf.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.ControlFacturacionDAO;
import biz.belcorp.ssicc.dao.scdf.model.ControlFacturacion;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ControlFacturacionDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Repository("scdf.controlFacturacionDAO")
public class ControlFacturacionDAOiBatis extends BaseDAOiBatis implements
        ControlFacturacionDAO {

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.ControlFacturacionDAO#getControlesFacturacionByCriteria(java.util.Map)
     */
    public List getControlesFacturacionByCriteria(Map criteria) {
        List resultados = getSqlMapClientTemplate().queryForList(
                "scdf.ControlFacturacionSQL.getControlesFacturacionByCriteria",
                criteria);
        return resultados;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.ControlFacturacionDAO#getControlFacturacion(java.lang.String)
     */
    public ControlFacturacion getControlFacturacion(String codigoPais) {
        ControlFacturacion control = (ControlFacturacion) getSqlMapClientTemplate()
                .queryForObject(
                        "scdf.ControlFacturacionSQL.getControlFacturacion",
                        codigoPais);
        if (control == null)
            throw new ObjectRetrievalFailureException(ControlFacturacion.class,
                    control);
        return control;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.ControlFacturacionDAO#getControlFacturacionMap(java.lang.String)
     */
    public List getControlFacturacionMap(String codigoPais) {
        List resultados = getSqlMapClientTemplate().queryForList(
                "scdf.ControlFacturacionSQL.getControlFacturacionMap",
                codigoPais);
        return resultados;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.ControlFacturacionDAO#insertControlFacturacion(biz.belcorp.ssicc.scdf.model.ControlFacturacion,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void insertControlFacturacion(ControlFacturacion control,
            Usuario usuario) {
        getSqlMapClientTemplate().insert(
                "scdf.ControlFacturacionSQL.insertControlFacturacion", control);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.ControlFacturacionDAO#updateControlFacturacion(biz.belcorp.ssicc.scdf.model.ControlFacturacion,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void updateControlFacturacion(ControlFacturacion control,
            Usuario usuario) {
        getSqlMapClientTemplate().update(
                "scdf.ControlFacturacionSQL.updateControlFacturacion", control);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.ControlFacturacionDAO#removeControlFacturacion(biz.belcorp.ssicc.scdf.model.ControlFacturacion,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void removeControlFacturacion(ControlFacturacion control,
            Usuario usuario) {
        getSqlMapClientTemplate().delete(
                "scdf.ControlFacturacionSQL.deleteControlFacturacion", control);
    }

}