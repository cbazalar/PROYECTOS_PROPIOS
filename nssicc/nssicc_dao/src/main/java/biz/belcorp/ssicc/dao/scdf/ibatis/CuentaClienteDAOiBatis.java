package biz.belcorp.ssicc.dao.scdf.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.CuentaClienteDAO;
import biz.belcorp.ssicc.dao.scdf.model.CuentaCliente;

@Repository("scdf.cuentaClienteDAO")
public class CuentaClienteDAOiBatis extends BaseDAOiBatis implements
        CuentaClienteDAO {

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.CuentaClienteDAO#getCuentaClientes(biz.belcorp.ssicc.scdf.model.CuentaCliente)
     */
    public List getCuentaClientes(CuentaCliente cuentaCliente) {
        return getSqlMapClientTemplate().queryForList(
                "scdf.CuentaClienteSQL.getCuentaClientes", cuentaCliente);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.CuentaClienteDAO#insertCuentaCliente(biz.belcorp.ssicc.scdf.model.CuentaCliente,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void insertCuentaCliente(CuentaCliente cuenta, Usuario usuario) {
        getSqlMapClientTemplate().insert(
                "scdf.CuentaClienteSQL.insertCuentaCliente", cuenta);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.CuentaClienteDAO#updateCuentaCliente(biz.belcorp.ssicc.scdf.model.CuentaCliente,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void updateCuentaCliente(CuentaCliente cuenta, Usuario usuario) {

    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.CuentaClienteDAO#removeCuentaCliente(biz.belcorp.ssicc.scdf.model.CuentaCliente)
     */
    public void removeCuentaCliente(CuentaCliente cuentaCliente) {

    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.CuentaClienteDAO#removeCuentaClienteByPais(java.lang.String)
     */
    public void removeCuentaClienteByPais(String codigoPais) {
        getSqlMapClientTemplate().delete(
                "scdf.CuentaClienteSQL.removeCuentaClienteByPais", codigoPais);
    }

}