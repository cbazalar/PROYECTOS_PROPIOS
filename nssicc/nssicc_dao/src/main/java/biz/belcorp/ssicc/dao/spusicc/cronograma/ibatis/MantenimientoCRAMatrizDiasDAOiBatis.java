package biz.belcorp.ssicc.dao.spusicc.cronograma.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cronograma.MantenimientoCRAMatrizDiasDAO;

/**
 * @author <a href="mailto:dooliva@belcorp.biz">Dennys Oliva Iriarte</a>
 */
@Repository("spusicc.cronograma.mantenimientoCRAMatrizDiasDAO")
public class MantenimientoCRAMatrizDiasDAOiBatis  extends	BaseDAOiBatis implements MantenimientoCRAMatrizDiasDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.dao.MantenimientoCRAMatrizDiasDAO#getCargaMatrizDias(java.util.Map)
	 */
	public List getCargaMatrizDias(Map criteria){
		
		// LLena la tabla temporal
		getSqlMapClientTemplate().update("spusicc.cronograma.mantenimientoCRASQL.executeCargaMatrizDias",criteria);
		
		// Obtiene la lista de la tabla cargada
		return getSqlMapClientTemplate().queryForList("spusicc.cronograma.mantenimientoCRASQL.getPintaMatrizDias");
				
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.dao.MantenimientoCRAMatrizDiasDAO#getMatrizDias(java.util.Map)
	 */
	public List getMatrizDias(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.cronograma.mantenimientoCRASQL.getMatrizDias",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.dao.MantenimientoCRAMatrizDiasDAO#updateMatrizDias(java.util.Map)
	 */
	public void updateMatrizDias(Map criteria){
		getSqlMapClientTemplate().update("spusicc.cronograma.mantenimientoCRASQL.updateMatrizDias", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.dao.MantenimientoCRAMatrizDiasDAO#getMatrizDiasFueraPeriodo(java.util.Map)
	 */
	public List getMatrizDiasFueraPeriodo(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.cronograma.mantenimientoCRASQL.getMatrizDiasFueraPeriodo",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.dao.MantenimientoCRAMatrizDiasDAO#insertActividadMatrizDias(java.util.Map)
	 */
	public void insertActividadMatrizDias(Map criteria){
		getSqlMapClientTemplate().insert("spusicc.cronograma.mantenimientoCRASQL.insertActividadMatrizDias", criteria);
	}
	
	public void updateMatrizUpdateActividad(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.cronograma.mantenimientoCRASQL.updateMatrizUpdateActividad", criteria);		
	}

	public void updateMatrizDeleteActividad(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.cronograma.mantenimientoCRASQL.updateMatrizDeleteActividad", criteria);		
	}
	
}
