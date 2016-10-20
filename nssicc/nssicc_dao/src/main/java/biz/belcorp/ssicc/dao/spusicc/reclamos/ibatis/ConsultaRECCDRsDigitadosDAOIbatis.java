/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.reclamos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.reclamos.ConsultaRECCDRsDigitadosDAO;


/**
 * @author peexcroman
 *
 */
@Repository("spusicc.consultaRECCDRsDigitadosDAO")
public class ConsultaRECCDRsDigitadosDAOIbatis extends		BaseDAOiBatis implements ConsultaRECCDRsDigitadosDAO {

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.ConsultaRECCDRsDigitadosDAO#getListaCabeceras(java.util.Map)
	 */
	public List getListaCabeceras(Map params){
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getListaCabeceras",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.ConsultaRECCDRsDigitadosDAO#getListaDetalles(java.util.Map)
	 */
	public List getListaDetalles(Map params){
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getListaDetalles",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.ConsultaRECCDRsDigitadosDAO#deleteDetallesDigitados(java.util.Map)
	 */
	public void deleteDetallesDigitados(Map params){
		getSqlMapClientTemplate().delete("spusicc.reclamos.ReclamosSQL.deleteDetallesDigitados",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.ConsultaRECCDRsDigitadosDAO#deleteCabeceraDigitada(java.util.Map)
	 */
	public void deleteCabeceraDigitada(Map params){
		getSqlMapClientTemplate().delete("spusicc.reclamos.ReclamosSQL.deleteCabeceraDigitada",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.ConsultaRECCDRsDigitadosDAO#getSecuenciaZonaDiaria()
	 */
	public List getSecuenciaZonaDiaria(){
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getSecuenciaZonaDiaria");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.ConsultaRECCDRsDigitadosDAO#getListaCabecerasHistorico(java.util.Map)
	 */
	public List getListaCabecerasHistorico(Map params){
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getListaCabecerasHistorico",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.ConsultaRECCDRsDigitadosDAO#getListaDetallesHistoricos(java.util.Map)
	 */
	public List getListaDetallesHistoricos(Map params){
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getListaDetallesHistoricos",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.ConsultaRECCDRsDigitadosDAO#deleteDetallesDigitadosHistoricos(java.util.Map)
	 */
	public void deleteDetallesDigitadosHistoricos(Map params){
		getSqlMapClientTemplate().delete("spusicc.reclamos.ReclamosSQL.deleteDetallesDigitadosHistoricos",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.ConsultaRECCDRsDigitadosDAO#deleteCabeceraDigitadaHistoricos(java.util.Map)
	 */
	public void deleteCabeceraDigitadaHistoricos(Map params){
		getSqlMapClientTemplate().delete("spusicc.reclamos.ReclamosSQL.deleteCabeceraDigitadaHistoricos",params);
	}
}
