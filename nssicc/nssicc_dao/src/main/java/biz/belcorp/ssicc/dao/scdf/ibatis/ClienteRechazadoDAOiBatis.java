/*
 * Created on 12-ago-08 12:09:08
 * biz.belcorp.ssicc.scdf.dao.ibatis.ClienteRechazadoDAOiBatis
 */
package biz.belcorp.ssicc.dao.scdf.ibatis;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.ClienteRechazadoDAO;
import biz.belcorp.ssicc.dao.scdf.model.ClienteRechazado;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ClienteRechazadoDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Repository("scdf.clienteRechazadoDAO")
public class ClienteRechazadoDAOiBatis extends BaseDAOiBatis implements
        ClienteRechazadoDAO {

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.ClienteRechazadoDAO#insertClienteRechazado(biz.belcorp.ssicc.scdf.model.ClienteRechazado,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void insertClienteRechazado(ClienteRechazado clienteRechazado,
            Usuario usuario) {
        getSqlMapClientTemplate().insert(
                "scdf.ClienteRechazadoSQL.insertClienteRechazado",
                clienteRechazado);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.ClienteRechazadoDAO#deleteClientesRechazados()
     */
    public void deleteClientesRechazados() {
        getSqlMapClientTemplate().delete(
                "scdf.ClienteRechazadoSQL.deleteClientesRechazados", null);
    }

}
