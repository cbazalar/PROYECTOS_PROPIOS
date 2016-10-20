package biz.belcorp.ssicc.dao.spusicc.cronograma.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.model.BaseOID;
import biz.belcorp.ssicc.dao.spusicc.cronograma.MantenimientoCRAPeriodoDAO;
import biz.belcorp.ssicc.dao.spusicc.cronograma.model.PeriodoCronograma;

/**
 * Implementacion del DAO que ejecutara el periodo del cronograma
 * <p>
 * <a href="MantenimientoCRAPeriodoDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:rramirez@belcorp.biz">Rosalvina Ramirez</a>
 */
@Repository("spusicc.cronograma.mantenimientoCRAPeriodoDAO")
public class MantenimientoCRAPeriodoDAOiBatis extends BaseDAOiBatis implements MantenimientoCRAPeriodoDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.dao.MantenimientoCRAPeriodoDAO#getPeriodoCorporativoList(java.lang.String)
	 */
	public List<BaseOID> getPeriodoCorporativoList(String anhio){
		return getSqlMapClientTemplate().queryForList("spusicc.cronograma.mantenimientoCRASQL.getPeriodoCorporativo",anhio);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.dao.MantenimientoCRAPeriodoDAO#getPeriodoCronogramaList(java.lang.String)
	 */
	public List getPeriodoCronogramaList(String anhio){
		return getSqlMapClientTemplate().queryForList("spusicc.cronograma.mantenimientoCRASQL.getPeriodoCronograma",anhio);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.dao.MantenimientoCRAPeriodoDAO#insertPeriodoCronograma(biz.belcorp.ssicc.spusicc.cronograma.model.PeriodoCronograma)
	 */
	public void insertPeriodoCronograma(PeriodoCronograma periodo) throws Exception{
		getSqlMapClientTemplate().insert("spusicc.cronograma.mantenimientoCRASQL.insertPeriodoCronograma",periodo);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.dao.MantenimientoCRAPeriodoDAO#modificaPeriodoCronograma(biz.belcorp.ssicc.spusicc.cronograma.model.PeriodoCronograma)
	 */
	public void modificaPeriodoCronograma(PeriodoCronograma periodo) throws Exception{
		getSqlMapClientTemplate().update("spusicc.cronograma.mantenimientoCRASQL.modificaPeriodoCronograma",periodo);
	}

	public void insertPeriodoCorporativo(Map criteria){
		getSqlMapClientTemplate().update("spusicc.cronograma.mantenimientoCRASQL.executeInsertPeriodoCorporativo",criteria);
	}

	public List getPeriodoNuevoCronogramaList(){
		return getSqlMapClientTemplate().queryForList("spusicc.cronograma.mantenimientoCRASQL.getPeriodoNuevoCronogramaList");
	}
}
