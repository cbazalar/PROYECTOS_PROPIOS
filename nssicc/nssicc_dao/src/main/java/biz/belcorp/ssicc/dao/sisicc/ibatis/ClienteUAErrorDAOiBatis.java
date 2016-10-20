/*
 * Created on 03-ene-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.ClienteUAErrorDAO;
import biz.belcorp.ssicc.dao.sisicc.model.ClienteUAError;
import biz.belcorp.ssicc.dao.sisicc.model.ClienteUAErrorPK;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ClienteUAErrorDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
@Repository("sisicc.clienteUAErrorDAO")
public class ClienteUAErrorDAOiBatis extends BaseDAOiBatis implements
		ClienteUAErrorDAO {

	/* 
	 * @see biz.belcorp.ssicc.dao.ClienteUAErrorDAO#insertClienteUAError(biz.belcorp.ssicc.model.ClienteUAError, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertClienteUAError(ClienteUAError cliente, Usuario usuario) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("sisicc.ProcesosGEOSQL.insertClienteUAError", cliente);
	}
    /* 
     * @see biz.belcorp.ssicc.dao.ClienteUAErrorDAO#updateClienteUAError(biz.belcorp.ssicc.model.ClienteUAError, biz.belcorp.ssicc.model.Usuario)
     */
    public void updateClienteUAError(ClienteUAError cliente, Usuario usuario) {
        // TODO Auto-generated method stub
        getSqlMapClientTemplate().update("sisicc.ProcesosGEOSQL.updateClienteUAError", cliente);
    }
    /* 
     * @see biz.belcorp.ssicc.dao.ClienteUAErrorDAO#selectClienteUAError(biz.belcorp.ssicc.model.ClienteUAError, biz.belcorp.ssicc.model.Usuario)
   */
   public int selectClienteUAError(ClienteUAError clienteOriginal) {
        ClienteUAErrorPK pk = new ClienteUAErrorPK();
        pk.setCodigo(clienteOriginal.getCodigoCliente());
        pk.setCodigoPais(clienteOriginal.getCodigoPais());
        
        ClienteUAError cliente = (ClienteUAError) getSqlMapClientTemplate().queryForObject("sisicc.ProcesosGEOSQL.getClienteUAError", pk);
        if (cliente == null) return 0;
                        else return 1;
    }
	/* 
	 * @see biz.belcorp.ssicc.dao.ClienteUAErrorDAO#getClienteUAError(biz.belcorp.ssicc.model.ClienteUAErrorPK)
	 */
	public ClienteUAError getClienteUAError(ClienteUAErrorPK pk) {
		// TODO Auto-generated method stub
		ClienteUAError cliente = (ClienteUAError) getSqlMapClientTemplate().queryForObject("sisicc.ProcesosGEOSQL.getClienteUAError", pk);
        if (cliente == null) {
            throw new ObjectRetrievalFailureException(ClienteUAError.class, pk);
        }
        return cliente;
	}
	/* 
	 * @see biz.belcorp.ssicc.dao.ClienteUAErrorDAO#getClientesUAErrorByCriteria(java.util.Map)
	 */
	public List getClientesUAErrorByCriteria(Map criteria) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getClientesUAErrorByCriteria", criteria);
	}
    /* 
     * @see biz.belcorp.ssicc.dao.ClienteUAErrorDAO#getDireccionConsultorasByCriteria(java.util.Map)
     */
    public List getDireccionConsultorasByCriteria(Map criteria) {
        // TODO Auto-generated method stub
        return getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getReporteGISReporteDireccionConsultoras", criteria);
        
    }
    /* 
     * @see biz.belcorp.ssicc.dao.ClienteUAErrorDAO#getReporteClientesErroneos(java.util.Map)
     */
    public List getReporteClientesErroneos(Map criteria) {
        // TODO Auto-generated method stub
        return getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getReporteGEOClientesErroneosXLS", criteria);
    }
    /* 
     * @see biz.belcorp.ssicc.dao.ClienteUAErrorDAO#getPagoLideresByCriteria(java.util.Map)
     */
    public List getPagoLideresByCriteria(Map criteria) {
        // TODO Auto-generated method stub
        return getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getReporteCOMPagoLideres", criteria);
    }
    /* 
     * @see biz.belcorp.ssicc.dao.ClienteUAErrorDAO#getLideresNuevasByCriteria(java.util.Map)
     */
    public List getLideresNuevasByCriteria(Map criteria) {
        // TODO Auto-generated method stub
        return getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getReporteCOMLideresNuevas", criteria);
    }
}
