/*
 * Created on 19/04/2005 04:43:53 PM biz.belcorp.ssicc.dao.BitacoraDAOiBatis
 */
package biz.belcorp.ssicc.dao.scdf.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.scdf.BitacoraDAO;
import biz.belcorp.ssicc.dao.scdf.model.Bitacora;

/**
 * TODO Include class description here.
 * <p>
 * <a href="BitacoraDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Repository("scdf.bitacoraDAO")
public class BitacoraDAOiBatis extends BaseDAOiBatis implements BitacoraDAO {

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.BitacoraDAO#getBitacoras(biz.belcorp.ssicc.scdf.model.Bitacora)
     */
    public List getBitacoras(Bitacora bitacora) {
        List bitacoras = getSqlMapClientTemplate().queryForList(
                "scdf.BitacoraSQL.getBitacoras", bitacora);
        return bitacoras;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.BitacoraDAO#insertBitacora(biz.belcorp.ssicc.scdf.model.Bitacora)
     */
    public void insertBitacora(Bitacora bitacora) {
        bitacora.setNumero(getNextPK(null));
        getSqlMapClientTemplate().insert("scdf.BitacoraSQL.insertBitacora",
                bitacora);
    }

    /**
     * Este metodo es para generar el siguiente elemento.
     * 
     * @param params
     *            En este caso en null
     * @return cadena conteniendo el valor del sgte. correlativo.
     */
    public synchronized String getNextPK(Map params) {
        return (String) getSqlMapClientTemplate().queryForObject(
                "scdf.BitacoraSQL.getNextPK", params);
    }
}