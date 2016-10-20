package biz.belcorp.ssicc.dao.spusicc.cronograma.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cronograma.MantenimientoCRACronogramaFase1DAO;

/**
 * @author <a href="mailto:dooliva@belcorp.biz">Dennys Oliva Iriarte</a>
 */
@Repository("spusicc.cronograma.mantenimientoCRACronogramaFase1DAO")
public class MantenimientoCRACronogramaFase1DAOiBatis  extends	BaseDAOiBatis implements MantenimientoCRACronogramaFase1DAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.dao.MantenimientoCRAMatrizDiasDAO#getCargaMatrizDias(java.util.Map)
	 */
	
	public void getGenerarCronogramaFase1(Map criteria){		
		getSqlMapClientTemplate().update("spusicc.cronograma.mantenimientoCRASQL.executeGenerarCronogramaFase1",criteria);						
	}
	
	public void getCargaCronogramaFase1(Map criteria){		
		getSqlMapClientTemplate().update("spusicc.cronograma.mantenimientoCRASQL.executeCargaCronogramaFase1",criteria);						
	}
	
	public List getPintaCronogramaFase1(){
		return getSqlMapClientTemplate().queryForList("spusicc.cronograma.mantenimientoCRASQL.getPintaCronogramaFase1");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.dao.MantenimientoCRACronogramaFase1DAO#updateCronogramaFase1(java.util.Map)
	 */
	public void updateCronogramaFase1(Map criteria){		
		getSqlMapClientTemplate().update("spusicc.cronograma.mantenimientoCRASQL.updateCronogramaFase1",criteria);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.dao.MantenimientoCRACronogramaFase1DAO#getDatosCronoFase1(java.util.Map)
	 */
	public List getDatosCronoFase1(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.cronograma.mantenimientoCRASQL.getDatosCronoFase1",criteria);
	}
	
	public List getZonasCronograma(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.cronograma.mantenimientoCRASQL.getZonasCronograma",criteria);
	}

	public List getDatosCronoFase2(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.cronograma.mantenimientoCRASQL.getDatosCronoFase2",criteria);
	}

	public void updateCronogramaFase2(Map criteria){
		getSqlMapClientTemplate().update("spusicc.cronograma.mantenimientoCRASQL.updateCronogramaFase2",criteria);
	}
	
	public void getCargaCronogramaFase2(Map criteria){
		//Lee de cronograma fase2
		getSqlMapClientTemplate().update("spusicc.cronograma.mantenimientoCRASQL.executeCargaCronogramaFase2",criteria);
	}

	public void generarCronogramaFase2(Map criteria){
		//Lee de cronograma fase1
		getSqlMapClientTemplate().update("spusicc.cronograma.mantenimientoCRASQL.executeGenerarCronogramaFase2",criteria);
	}
	
	public void deleteTemporalFase2(){
		getSqlMapClientTemplate().update("spusicc.cronograma.mantenimientoCRASQL.deleteTemporalFase2");
	}

	public List getPintaCronogramaFase2(){
		return getSqlMapClientTemplate().queryForList("spusicc.cronograma.mantenimientoCRASQL.getPintaCronogramaFase2");
	}
	
	public void deleteCronogramaFase2(Map criteria){
		getSqlMapClientTemplate().update("spusicc.cronograma.mantenimientoCRASQL.executeDeleteCronogramaFase2",criteria);
	}
	
	public void copiaCronogramaPorZonaFase2(Map criteria){
		getSqlMapClientTemplate().update("spusicc.cronograma.mantenimientoCRASQL.executeCopiaCronogramaPorZonaFase2",criteria);
	}
	
	public String getActuaFechaFase1(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.cronograma.mantenimientoCRASQL.getActuaFechaFase1",criteria);
	}
		
	public String getActuaFechaFase2(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.cronograma.mantenimientoCRASQL.getActuaFechaFase2",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.dao.MantenimientoCRACronogramaFase1DAO#getCronogramaFase1Existente(java.util.Map)
	 */
	public int getCronogramaFase1Existente(Map criteria){
		return ((Integer)getSqlMapClientTemplate().queryForObject("spusicc.cronograma.mantenimientoCRASQL.getCronogramaFase1Existente",criteria)).intValue();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.dao.MantenimientoCRACronogramaFase1DAO#getFechaCronoFase2(java.util.Map)
	 */
	public String getFechaCronoFase2(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.cronograma.mantenimientoCRASQL.getFechaCronoFase2",criteria);
	}
	
}
