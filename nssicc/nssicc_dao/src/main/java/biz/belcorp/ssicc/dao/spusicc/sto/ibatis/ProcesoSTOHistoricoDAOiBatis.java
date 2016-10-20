/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.sto.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTOHistoricoDAO;
import biz.belcorp.ssicc.dao.spusicc.sto.model.AccionTipoDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.HistoricoTipoDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.HistoricoValidacion;

/**
 * Implementacion del DAO que ejecutara Historicos
 * <p>
 * <a href="ProcesoSTOHistoricoDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 */
@Repository("spusicc.procesoSTOHistoricoDAO")
public class ProcesoSTOHistoricoDAOiBatis extends BaseDAOiBatis implements ProcesoSTOHistoricoDAO {
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOHistoricoDAO#getNumeroProceso(biz.belcorp.ssicc.spusicc.sto.model.TipoDocumentoDigitadoPK)
	 */
	public String getNumeroProceso(AccionTipoDocumento accionTipoDocumento) {
		
		Map params = new HashMap();
		String numeroProceso ="";
		params.put("codPais", accionTipoDocumento.getCodPais());
		params.put("codTipoDocu", accionTipoDocumento.getCodTipoDocu());
		params.put("numeroProceso", numeroProceso);	
		
		getSqlMapClientTemplate().update("spusicc.ProcesoSTOHistorico.updateNumeroProceso", params);
        
        return (String)params.get("numeroProceso");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOHistoricoDAO#insertHistoricoProceso(biz.belcorp.ssicc.spusicc.sto.model.HistoricoTipoDocumento, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertHistoricoProceso(HistoricoTipoDocumento historico,Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.ProcesoSTOHistorico.insertHistoricoProceso", historico);
		
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOHistoricoDAO#updateHistoricoProceso(biz.belcorp.ssicc.spusicc.sto.model.HistoricoTipoDocumento, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateFinProceso(HistoricoTipoDocumento historico,Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.ProcesoSTOHistorico.updateFinProceso", historico);
		
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOHistoricoDAO#getProcesoValidacionEjecucionByDocumento(java.util.Map)
	 */
	public List getProcesoValidacionEjecucionByDocumento(Map params) {
		return (List)getSqlMapClientTemplate().queryForList("spusicc.ProcesoSTOHistorico.getProcesoValidacionEjecucionByDocumento", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOHistoricoDAO#getProcesoEjecucionByDocumento(java.util.Map)
	 */
	public List getProcesoEjecucionByDocumento(Map params) {
		return (List)getSqlMapClientTemplate().queryForList("spusicc.ProcesoSTOHistorico.getProcesoEjecucionByDocumento", params);
	}
	/**
	 * @param params
	 * @return
	 */
	public List getProcesoValidacionByDocumento(Map params) {
		return (List)getSqlMapClientTemplate().queryForList("spusicc.ProcesoSTOHistorico.getProcesoValidacionByDocumento", params);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOHistoricoDAO#getListaProcesosHijos(biz.belcorp.ssicc.spusicc.sto.model.HistoricoTipoDocumento)
	 */
	public List getListaProcesosHijos(HistoricoTipoDocumento historico) {
		return (List)getSqlMapClientTemplate().queryForList("spusicc.ProcesoSTOHistorico.getListaProcesosHijos", historico);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOHistoricoDAO#insertHistoricoValidaciones(biz.belcorp.ssicc.spusicc.sto.model.HistoricoTipoDocumento)
	 */
	public void UpdateInicioProceso(HistoricoTipoDocumento historico) {
		getSqlMapClientTemplate().update("spusicc.ProcesoSTOHistorico.updateInicioHistoricoProceso", historico);
	}


	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOHistoricoDAO#updateHistoricoValidacion(biz.belcorp.ssicc.spusicc.sto.model.HistoricoValidacion)
	 */
	public void updateHistoricoValidacion(HistoricoValidacion historico) {
		getSqlMapClientTemplate().update("spusicc.ProcesoSTOHistorico.updateHistoricoValidacion", historico);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOHistoricoDAO#insertHistoricoValidaciones(biz.belcorp.ssicc.spusicc.sto.model.HistoricoTipoDocumento)
	 */
	public void insertHistoricoValidaciones(HistoricoTipoDocumento historico) {
		getSqlMapClientTemplate().update("spusicc.ProcesoSTOHistorico.insertHistoricoValidaciones", historico);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOHistoricoDAO#insertHistoricoValidacionesOnline(biz.belcorp.ssicc.spusicc.sto.model.HistoricoTipoDocumento)
	 */
	public void insertHistoricoValidacionesOnline(HistoricoTipoDocumento historico) {
		getSqlMapClientTemplate().update("spusicc.ProcesoSTOHistorico.insertHistoricoValidacionesOnline", historico);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOHistoricoDAO#updateInicioValidacion(biz.belcorp.ssicc.spusicc.sto.model.HistoricoValidacion)
	 */
	public void updateInicioValidacion(HistoricoValidacion historicoValidacion) {
		getSqlMapClientTemplate().update("spusicc.ProcesoSTOHistorico.updateInicioValidacion", historicoValidacion);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOHistoricoDAO#updateFinValidacion(biz.belcorp.ssicc.spusicc.sto.model.HistoricoValidacion)
	 */
	public void updateFinValidacion(HistoricoValidacion historicoValidacion) {
		getSqlMapClientTemplate().update("spusicc.ProcesoSTOHistorico.updateFinValidacion", historicoValidacion);
		
	}



	
}
