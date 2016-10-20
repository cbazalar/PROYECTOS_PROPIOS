package biz.belcorp.ssicc.dao.scdf.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.TarjetaDAO;
import biz.belcorp.ssicc.dao.scdf.model.Tarjeta;
import biz.belcorp.ssicc.dao.scdf.model.TarjetaPK;

@Repository("scdf.tarjetaDAO")
public class TarjetaDAOiBatis extends BaseDAOiBatis implements TarjetaDAO {

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.TarjetaDAO#getTarjetas(biz.belcorp.ssicc.scdf.model.Tarjeta)
     */
    public List getTarjetas(Tarjeta tarjeta) {
        List tarjetas = getSqlMapClientTemplate().queryForList(
                "scdf.TarjetaSQL.getTarjetas", tarjeta);
        return tarjetas;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.TarjetaDAO#getTarjetasByPais(java.lang.String)
     */
    public List getTarjetasByPais(String codigoPais) {
        List tarjetas = getSqlMapClientTemplate().queryForList(
                "scdf.TarjetaSQL.getTarjetasByPais", codigoPais);
        return tarjetas;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.TarjetaDAO#getTarjetasMapByPais(java.lang.String)
     */
    public List getTarjetasMapByPais(String codigoPais) {
        List tarjetas = getSqlMapClientTemplate().queryForList(
                "scdf.TarjetaSQL.getTarjetasMapByPais", codigoPais);
        return tarjetas;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.TarjetaDAO#getTarjetasByCriteria(java.util.Map)
     */
    public List getTarjetasByCriteria(Map criteria) {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.TarjetaDAO#getTarjeta(biz.belcorp.ssicc.scdf.model.TarjetaPK)
     */
    public Tarjeta getTarjeta(TarjetaPK primaryKey) {
        Tarjeta tarjeta = (Tarjeta) getSqlMapClientTemplate().queryForObject("scdf.TarjetaSQL.getTarjeta", primaryKey);
        if (tarjeta == null) {
            throw new ObjectRetrievalFailureException(Tarjeta.class, primaryKey);
        }

        return tarjeta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.TarjetaDAO#insertTarjeta(biz.belcorp.ssicc.scdf.model.Tarjeta,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void insertTarjeta(Tarjeta tarjeta, Usuario usuario) {
        getSqlMapClientTemplate().insert("scdf.TarjetaSQL.insertTarjeta",
                tarjeta);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.TarjetaDAO#updateTarjeta(biz.belcorp.ssicc.scdf.model.Tarjeta,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void updateTarjeta(Tarjeta tarjeta, Usuario usuario) {
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scdf.dao.TarjetaDAO#updatePeriodoTarjetaByPais(java.lang.String)
     */
    public void updatePeriodoTarjetaByPais(String codigoPais) {
        getSqlMapClientTemplate().update(
                "scdf.TarjetaSQL.updatePeriodoTarjetaByPais", codigoPais);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.TarjetaDAO#updateTarjetaStatusByPais(java.lang.String)
     */
    public void updateTarjetaStatusByPais(String codigoPais) {
        getSqlMapClientTemplate().update(
                "scdf.TarjetaSQL.updateTarjetaStatusByPais", codigoPais);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.TarjetaDAO#removeTarjeta(biz.belcorp.ssicc.scdf.model.Tarjeta)
     */
    public void removeTarjeta(Tarjeta tarjeta) {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.TarjetaDAO#removeTarjetaByPais(java.lang.String)
     */
    public void removeTarjetaByPais(String codigoPais) {
        getSqlMapClientTemplate().delete("scdf.TarjetaSQL.removeTarjetaByPais",
                codigoPais);
    }

}