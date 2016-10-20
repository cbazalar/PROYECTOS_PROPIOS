/*
 * Created on 10/11/2005 05:03:14 PM
 *
 * biz.belcorp.ssicc.dao.ibatis.ClienteDAOiBatis
 */
package biz.belcorp.ssicc.dao.scdf.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.ClienteDAO;
import biz.belcorp.ssicc.dao.scdf.model.Cliente;
import biz.belcorp.ssicc.dao.scdf.model.ClientePK;
import biz.belcorp.ssicc.dao.scdf.model.EmailCliente;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ClienteDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Repository("scdf.clienteDAO")
public class ClienteDAOiBatis extends BaseDAOiBatis implements ClienteDAO {

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.ClienteDAO#getClientes(biz.belcorp.ssicc.scdf.model.Cliente)
     */
    public List getClientes(Cliente cliente) {
        List clientes = getSqlMapClientTemplate().queryForList(
                "scdf.ClienteSQL.getClientes", cliente);
        return clientes;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.ClienteDAO#getClientesMap(biz.belcorp.ssicc.scdf.model.Cliente)
     */
    public List getClientesMap(Cliente cliente) {
        List clientes = getSqlMapClientTemplate().queryForList(
                "scdf.ClienteSQL.getClientesMap", cliente);
        return clientes;
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scdf.dao.ClienteDAO#getClientesMapByPais(java.lang.String)
     */
    public List getClientesMapByPais(String codigoPais) {
        List clientes = getSqlMapClientTemplate().queryForList(
                "scdf.ClienteSQL.getClientesMapByPais", codigoPais);
        return clientes;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.ClienteDAO#getClientesByCriteria(java.util.Map)
     */
    public List getClientesByCriteria(Map criteria) {
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scdf.dao.ClienteDAO#getCliente(biz.belcorp.ssicc.scdf.model.ClientePK)
     */
    public Cliente getCliente(ClientePK primaryKey) {
        Cliente cliente = (Cliente) getSqlMapClientTemplate().queryForObject("scdf.ClienteSQL.getCliente", primaryKey);
        if (cliente == null) {
            throw new ObjectRetrievalFailureException(Cliente.class, primaryKey);
        }

        return cliente;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.ClienteDAO#insertCliente(biz.belcorp.ssicc.scdf.model.Cliente,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void insertCliente(Cliente cliente, Usuario usuario) {
        getSqlMapClientTemplate().insert("scdf.ClienteSQL.insertCliente",
                cliente);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.ClienteDAO#updateCliente(biz.belcorp.ssicc.scdf.model.Cliente,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void updateCliente(Cliente cliente, Usuario usuario) {
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scdf.dao.ClienteDAO#updatePeriodoClienteByPais(java.lang.String)
     */
    public void updatePeriodoClienteByPais(String codigoPais) {
        getSqlMapClientTemplate().update(
                "scdf.ClienteSQL.updatePeriodoClienteByPais", codigoPais);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.ClienteDAO#updateClienteStatusByPais(java.lang.String)
     */
    public void updateClienteStatusByPais(String codigoPais) {
        getSqlMapClientTemplate().update(
                "scdf.ClienteSQL.updateClienteStatusByPais", codigoPais);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.ClienteDAO#removeCliente(biz.belcorp.ssicc.scdf.model.Cliente)
     */
    public void removeCliente(Cliente cliente) {
        getSqlMapClientTemplate().update("scdf.ClienteSQL.removeClienteByPais",
                cliente);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.ClienteDAO#removeClienteByPais(java.lang.String)
     */
    public void removeClienteByPais(String codigoPais) {
        getSqlMapClientTemplate().update("scdf.ClienteSQL.removeClienteByPais",
                codigoPais);
    }

  	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scdf.dao.ClienteDAO#executeEliminarBuzonMsg(java.lang.String)
	 */
	public void executeEliminarBuzonMsg(String codigoPais) {
		if (log.isDebugEnabled()) {
			log.debug("Iniciando executeEliminarBuzonMsg, params="
					+ codigoPais);
		}
		getSqlMapClientTemplate().update(
				"scdf.ClienteSQL.executeEliminarBuzonMsg",
				codigoPais);
	}    

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scdf.dao.ClienteDAO#deleteEmailClientes()
	 * Elimina los registros de la tabla temporal de clientes
	 */
	public void deleteEmailClientes() {
		getSqlMapClientTemplate().delete("scdf.ClienteSQL.deleteEmailClientes", null);
		
	}

	/* Inserta los Email de los clientes en la tabla temporal de Emails de CLientes
	 * Privilege (non-Javadoc)
	 * @see biz.belcorp.ssicc.scdf.dao.ClienteDAO#insertEmailCliente(biz.belcorp.ssicc.scdf.model.EmailConsultora, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertEmailCliente(EmailCliente emailConsultora, Usuario usuario) {
		
		 getSqlMapClientTemplate().insert("scdf.ClienteSQL.insertEmailCliente",emailConsultora);
	}

	
	/* Procesa la informacion recepcionada por la Interfaz de Recepcion de Emails de Clientes
	 * (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.DAO#executeInterfazPRIRecepcionarEmailClientes()
	 */
	public void executeInterfazPRIRecepcionarEmailClientes() {

		getSqlMapClientTemplate().update("scdf.ClienteSQL.executeInterfazPRIRecepcionarEmailClientes", null);
	}    

}