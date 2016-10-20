/*
 * Created on 22/05/2006 06:48:48 PM
 * biz.belcorp.ssicc.scdf.dao.ibatis.ControlImpresionStickersDAOiBatis
 */
package biz.belcorp.ssicc.dao.scdf.ibatis;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.ControlImpresionStickersDAO;
import biz.belcorp.ssicc.dao.scdf.model.ControlImpresionStickers;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ControlImpresionStickersDAOiBatis.java.html"> <i>View Source </i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Repository("scdf.controlImpresionStickersDAO")
public class ControlImpresionStickersDAOiBatis extends BaseDAOiBatis implements
        ControlImpresionStickersDAO {

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.ControlImpresionStickersDAO#getControlImpresionStickers(java.lang.String)
     */
    public ControlImpresionStickers getControlImpresionStickers(
            String codigoPais) {
        ControlImpresionStickers control = (ControlImpresionStickers) getSqlMapClientTemplate()
                .queryForObject(
                        "scdf.ControlImpresionStickersSQL.getControlImpresionStickers",
                        codigoPais);
        if (control == null)
            throw new ObjectRetrievalFailureException(
                    ControlImpresionStickers.class, control);
        return control;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.ControlImpresionStickersDAO#insertControlImpresionStickers(biz.belcorp.ssicc.scdf.model.ControlImpresionStickers,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void insertControlImpresionStickers(
            ControlImpresionStickers control, Usuario usuario) {
        getSqlMapClientTemplate()
                .insert(
                        "scdf.ControlImpresionStickersSQL.insertControlImpresionStickers",
                        control);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.ControlImpresionStickersDAO#updateControlImpresionStickers(biz.belcorp.ssicc.scdf.model.ControlImpresionStickers,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void updateControlImpresionStickers(
            ControlImpresionStickers control, Usuario usuario) {
        getSqlMapClientTemplate()
                .update(
                        "scdf.ControlImpresionStickersSQL.updateControlImpresionStickers",
                        control);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.ControlImpresionStickersDAO#removeControlImpresionStickers(biz.belcorp.ssicc.scdf.model.ControlImpresionStickers,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void removeControlImpresionStickers(
            ControlImpresionStickers control, Usuario usuario) {
        getSqlMapClientTemplate()
                .delete(
                        "scdf.ControlImpresionStickersSQL.deleteControlImpresionStickers",
                        control);
    }

}
