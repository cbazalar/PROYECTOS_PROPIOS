/*
 * Created on 04-ene-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.ClienteUAHistoricoDAO;
import biz.belcorp.ssicc.dao.sisicc.model.ClienteUAHistorico;
import biz.belcorp.ssicc.dao.sisicc.model.ClienteUAHistoricoPK;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ClienteUAHistoricoDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
@Repository("sisicc.clienteUAHistoricoDAO")
public class ClienteUAHistoricoDAOiBatis extends BaseDAOiBatis implements
		ClienteUAHistoricoDAO {

	/* 
	 * @see biz.belcorp.ssicc.dao.ClienteUAHistoricoDAO#insertClienteUAHistorico(biz.belcorp.ssicc.model.ClienteUAHistorico, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertClienteUAHistorico(ClienteUAHistorico cliente, Usuario usuario) {
		getSqlMapClientTemplate().insert("sisicc.ProcesosGEOSQL.insertClienteUAHistorico", cliente);
	}
    /* 
     * @see biz.belcorp.ssicc.dao.ClienteUAHistoricoDAO#updateClienteUAHistorico(biz.belcorp.ssicc.model.ClienteUAHistorico, biz.belcorp.ssicc.model.Usuario)
     */
    public void updateClienteUAHistorico(ClienteUAHistorico cliente, Usuario usuario) {
        getSqlMapClientTemplate().insert("sisicc.ProcesosGEOSQL.updateClienteUAHistorico", cliente);
    }
    /* 
     * @see biz.belcorp.ssicc.dao.ClienteUAHistoricoDAO#selectClienteUAHistorico(biz.belcorp.ssicc.model.ClienteUAHistorico, biz.belcorp.ssicc.model.Usuario)
    */
    public int selectClienteUAHistorico(ClienteUAHistorico clienteOriginal) {
        ClienteUAHistoricoPK pk = new ClienteUAHistoricoPK();
        pk.setCodigo(clienteOriginal.getCodigoCliente());
        pk.setCodigoPais(clienteOriginal.getCodigoPais());
        pk.setNumeroLote(clienteOriginal.getNumeroLote());
        
        ClienteUAHistorico cliente = (ClienteUAHistorico) getSqlMapClientTemplate().queryForObject("sisicc.ProcesosGEOSQL.getClienteUAHistorico", pk);
        if (cliente == null) return 0;
                        else return 1;
    }
	/* 
	 * @see biz.belcorp.ssicc.dao.ClienteUAHistoricoDAO#getClienteUAHistorico(biz.belcorp.ssicc.model.ClienteUAHistoricoPK)
	 */
	public ClienteUAHistorico getClienteUAHistorico(ClienteUAHistoricoPK pk) {
		ClienteUAHistorico cliente = (ClienteUAHistorico) getSqlMapClientTemplate().queryForObject("sisicc.ProcesosGEOSQL.getClienteUAHistorico", pk);
        if (cliente == null) {
            throw new ObjectRetrievalFailureException(ClienteUAHistorico.class, pk);
        }
        return cliente;
	}
	/* 
	 * @see biz.belcorp.ssicc.dao.ClienteUAHistoricoDAO#getClientesUAHistorico()
	 */
	public List getClientesUAHistorico() {
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosGEOSQL.getClientesUAHistorico", null);
	}
}
