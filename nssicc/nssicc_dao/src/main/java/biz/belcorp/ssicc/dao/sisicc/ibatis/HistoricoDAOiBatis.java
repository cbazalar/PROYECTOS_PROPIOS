/*
 * Created on 22-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.HistoricoDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="HistoricoDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
@Repository("sisicc.historicoDAO")
public class HistoricoDAOiBatis extends BaseDAOiBatis implements HistoricoDAO {

	/* 
	 * @see biz.belcorp.ssicc.dao.HistoricoDAO#insertHistorico(biz.belcorp.ssicc.model.Historico, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertHistorico(Historico historico, Usuario usuario) {
		getSqlMapClientTemplate().update("sisicc.HistoricoLoteSQL.insertHistorico", historico);
	}
	
	/* 
	 * @see biz.belcorp.ssicc.dao.HistoricoDAO#updateHistorico(biz.belcorp.ssicc.model.Historico, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateHistorico(Historico historico, Usuario usuario) {
		getSqlMapClientTemplate().update("sisicc.HistoricoLoteSQL.updateHistorico", historico);
	}
	/* 
	 * @see biz.belcorp.ssicc.dao.HistoricoDAO#getHistoricosByCriteria(java.util.Map)
	 */
	public List getHistoricosByCriteria(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.HistoricoLoteSQL.getHistoricosByCriteria", criteria);
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.HistoricoDAO#getHistoricosLotesMultiHilo(java.util.Map)
	 */
	public List<Historico> getHistoricosLotesMultiHilo(Map criteria) {
		List queryForList = getSqlMapClientTemplate().queryForList("sisicc.HistoricoLoteSQL.getHistoricosLotesMultiHilo", criteria);
		List<Historico> lista = queryForList;	
		return lista;
	}
	
    /* 
     * @see biz.belcorp.ssicc.dao.HistoricoDAO#getHistoricosByCriteria(java.util.Map)
     */
    public List getUltimoHistoricoByCodInterfaz(Map criteria) {
        return getSqlMapClientTemplate().queryForList("sisicc.HistoricoLoteSQL.getUltimoHistoricoByCodInterfaz", criteria);
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.dao.HistoricoDAO#getDevuelveDescripcionErrorInterfaz(java.util.Map)
     */
    public String getDevuelveDescripcionErrorInterfaz(Map criteria) {
    	return (String)getSqlMapClientTemplate().queryForObject("sisicc.HistoricoLoteSQL.getDevuelveDescripcionErrorInterfaz", criteria);
    }
    
    /* INI FRAMEWORK NSSICC */
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.dao.sisicc.HistoricoDAO#updateHistoricoIniProcesoAnteriorInterfaz(biz.belcorp.ssicc.dao.sisicc.model.Historico, biz.belcorp.ssicc.dao.model.Usuario)
     */
    public void updateHistoricoIniProcesoAnteriorInterfaz(Historico historico, Usuario usuario) {
		getSqlMapClientTemplate().update("sisicc.HistoricoLoteSQL.updateHistoricoIniProcesoAnteriorInterfaz", historico);
	}
     
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.dao.sisicc.HistoricoDAO#updateHistoricoFinProcesoAnteriorInterfaz(biz.belcorp.ssicc.dao.sisicc.model.Historico, biz.belcorp.ssicc.dao.model.Usuario)
     */
    public void updateHistoricoFinProcesoAnteriorInterfaz(Historico historico, Usuario usuario) {
		getSqlMapClientTemplate().update("sisicc.HistoricoLoteSQL.updateHistoricoFinProcesoAnteriorInterfaz", historico);
	}
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.dao.sisicc.HistoricoDAO#updateHistoricoIniInterfaz(biz.belcorp.ssicc.dao.sisicc.model.Historico, biz.belcorp.ssicc.dao.model.Usuario)
     */
    public void updateHistoricoIniInterfaz(Historico historico, Usuario usuario) {
		getSqlMapClientTemplate().update("sisicc.HistoricoLoteSQL.updateHistoricoIniInterfaz", historico);
	}
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.dao.sisicc.HistoricoDAO#updateHistoricoFinInterfaz(biz.belcorp.ssicc.dao.sisicc.model.Historico, biz.belcorp.ssicc.dao.model.Usuario)
     */
    public void updateHistoricoFinInterfaz(Historico historico, Usuario usuario) {
		getSqlMapClientTemplate().update("sisicc.HistoricoLoteSQL.updateHistoricoFinInterfaz", historico);
	}
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.dao.sisicc.HistoricoDAO#updateHistoricoIniProcesoPosteriorInterfaz(biz.belcorp.ssicc.dao.sisicc.model.Historico, biz.belcorp.ssicc.dao.model.Usuario)
     */
    public void updateHistoricoIniProcesoPosteriorInterfaz(Historico historico, Usuario usuario) {
		getSqlMapClientTemplate().update("sisicc.HistoricoLoteSQL.updateHistoricoIniProcesoPosteriorInterfaz", historico);
	}
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.dao.sisicc.HistoricoDAO#updateHistoricoFinProcesoPosteriorInterfaz(biz.belcorp.ssicc.dao.sisicc.model.Historico, biz.belcorp.ssicc.dao.model.Usuario)
     */
    public void updateHistoricoFinProcesoPosteriorInterfaz(Historico historico, Usuario usuario) {
		getSqlMapClientTemplate().update("sisicc.HistoricoLoteSQL.updateHistoricoFinProcesoPosteriorInterfaz", historico);
	}
    
    /* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.HistoricoDAO#getHistoricosLotesMultiHiloLote(java.util.Map)
	 */
	public List<Historico> getHistoricosLotesMultiHiloLote(Map criteria) {
		List queryForList = getSqlMapClientTemplate().queryForList("sisicc.HistoricoLoteSQL.getHistoricosLotesMultiHilo02", criteria);
		List<Historico> lista = queryForList;	
		return lista;
	}
    
    /* FIN FRAMEWORK NSSICC */
}
