/*
 * Created on 29-dic-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.ClienteUAGenerarDAO;
import biz.belcorp.ssicc.dao.sisicc.model.ClienteUAGenerar;
import biz.belcorp.ssicc.dao.sisicc.model.ClienteUAGenerarPK;
import biz.belcorp.ssicc.dao.sisicc.model.LibretaAhorro;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ClienteUAGenerarDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
@Repository("sisicc.clienteUAGenerarDAO")
public class ClienteUAGenerarDAOiBatis extends BaseDAOiBatis implements
        ClienteUAGenerarDAO {

    /*
     * @see biz.belcorp.ssicc.dao.ClienteUAGenerarDAO#insertClienteUAGenerar(biz.belcorp.ssicc.model.ClienteUAGenerar,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void insertClienteUAGenerar(ClienteUAGenerar cliente, Usuario usuario) {
        getSqlMapClientTemplate().insert(
                "sisicc.ProcesosGEOSQL.insertClienteUAGenerar", cliente);
    }

    /*
     * @see biz.belcorp.ssicc.dao.ClienteUAGenerarDAO#updateClienteUAGenerar(biz.belcorp.ssicc.model.ClienteUAGenerar,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void updateClienteUAGenerar(ClienteUAGenerar cliente, Usuario usuario) {
        getSqlMapClientTemplate().update(
                "sisicc.ProcesosGEOSQL.updateClienteUAGenerar", cliente);
    }

    /*
     * @see biz.belcorp.ssicc.dao.ClienteUAGenerarDAO#selectClienteUAGenerar(biz.belcorp.ssicc.model.ClienteUAGenerar,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public int selectClienteUAGenerar(ClienteUAGenerar clienteOriginal) {
        ClienteUAGenerarPK pk = new ClienteUAGenerarPK();
        pk.setCodigo(clienteOriginal.getCodigoCliente());
        pk.setCodigoPais(clienteOriginal.getCodigoPais());

        ClienteUAGenerar cliente = (ClienteUAGenerar) getSqlMapClientTemplate()
                .queryForObject("sisicc.ProcesosGEOSQL.getClienteUAGenerar", pk);
        if (cliente == null)
            return 0;
        else
            return 1;
    }

    /*
     * @see biz.belcorp.ssicc.dao.ClienteUAGenerarDAO#getClienteUAGenerar(biz.belcorp.ssicc.model.ClienteUAGenerarPK)
     */
    public ClienteUAGenerar getClienteUAGenerar(ClienteUAGenerarPK pk) {
        ClienteUAGenerar cliente = (ClienteUAGenerar) getSqlMapClientTemplate()
                .queryForObject("sisicc.ProcesosGEOSQL.getClienteUAGenerar", pk);
        if (cliente == null) {
            throw new ObjectRetrievalFailureException(ClienteUAGenerar.class,
                    pk);
        }
        return cliente;
    }

    /*
     * @see biz.belcorp.ssicc.dao.ClienteUAGenerarDAO#getNumeroClientesUAGenerar()
     */
    public Long getNumeroClientesUAGenerar() {
        return (Long) getSqlMapClientTemplate().queryForObject(
                "sisicc.ProcesosGEOSQL.getNumeroClientesUAGenerar", null);
    }

    public Integer getTamanhoNumeroClientesXPais(String codigoPais)
            throws Exception {
        return (Integer) getSqlMapClientTemplate().queryForObject(
                "sisicc.ProcesosGEOSQL.getTamanhoNumeroClientesXPais",
                codigoPais);
    }

    /*
     * @see biz.belcorp.ssicc.dao.ClienteUAGenerarDAO#getClientesUAGenerar()
     */
    public List getClientesUAGenerar() {
        return getSqlMapClientTemplate().queryForList(
                "sisicc.InterfazSQL.getClientesUAGenerar", null);
    }

    /*
     * @see biz.belcorp.ssicc.dao.ClienteUAGenerarDAO#removeClientesUAGenerar()
     */
    public void removeClientesUAGenerar() {
        getSqlMapClientTemplate().update(
                "sisicc.ProcesosGEOSQL.removeClientesUAGenerar", null);
    }

    /*
     * @see biz.belcorp.ssicc.dao.SistemaDAO#getLibretaAhorroByCriteria(biz.belcorp.ssicc.model.Sistema)
     */
    public List getLibretaAhorroByCriteria(Map criteria) {
        List sistemas = getSqlMapClientTemplate().queryForList(
                "sisicc.ProcesosCOMSQL.getLibretaAhorroByCriteria", criteria);
        return sistemas;
    }

    /*
     * @see biz.belcorp.ssicc.dao.SistemaDAO#getLibretaAhorro(biz.belcorp.ssicc.model.Sistema)
     */
    public LibretaAhorro getLibretaAhorro(Map criteria) {
        LibretaAhorro libretaAhorro = (LibretaAhorro) getSqlMapClientTemplate()
                .queryForObject("sisicc.ProcesosCOMSQL.getLibretaAhorro",
                        criteria);
        if (libretaAhorro == null) {
            throw new ObjectRetrievalFailureException(ClienteUAGenerar.class,
                    criteria.get("codigoLider"));
        }
        return libretaAhorro;
    }

    /*
     * @see biz.belcorp.ssicc.dao.SistemaDAO#updateLibretaAhorro(biz.belcorp.ssicc.model.Sistema)
     */
    public void updateLibretaAhorro(Map criteria, Usuario usuario) {
        getSqlMapClientTemplate().update(
                "sisicc.ProcesosCOMSQL.updateLibretaAhorro", criteria);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.sisicc.dao.ClienteUAGenerarDAO#getNivelSocioeconomico(java.lang.String,
     *      java.lang.Long)
     */
    public String getNivelSocioeconomico(String codigoPais,
            Long codigoTerritorio) {
        Map criteria = new HashMap();
        criteria.put("codigoPais", codigoPais);
        criteria.put("codigoTerritorio", codigoTerritorio);
        String nivelSocioeconomico = (String) getSqlMapClientTemplate()
                .queryForObject("sisicc.ProcesosGEOSQL.getNivelSocioeconomico", criteria);
        return nivelSocioeconomico;
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.ClienteUAGenerarDAO#getTiposRegimen()
	 */
	public List getTiposRegimen() {
		return getSqlMapClientTemplate().queryForList(
                "sisicc.ProcesosGEOSQL.getTiposRegimen", null);	}

}