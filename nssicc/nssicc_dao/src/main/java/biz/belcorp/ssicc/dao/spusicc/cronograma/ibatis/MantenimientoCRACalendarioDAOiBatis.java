package biz.belcorp.ssicc.dao.spusicc.cronograma.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cronograma.MantenimientoCRACalendarioDAO;

/**  
 * <p>
 * <a href="MantenimientoCRACalendarioDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:rramirez@belcorp.biz"> Rosalvina Ram+irez Guardia </a>
 */
@Repository("spusicc.mantenimientoCRACalendarioDAO")
public class MantenimientoCRACalendarioDAOiBatis  extends BaseDAOiBatis implements MantenimientoCRACalendarioDAO {
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.dao.MantenimientoCRACalendarioDAO#insertDiaNoLaborable(java.util.Map)
	 */
	public void insertDiaNoLaborable(Map criteria){
		getSqlMapClientTemplate().update("spusicc.cronograma.mantenimientoCRASQL.executeInsertDiaNoLaborable", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.dao.MantenimientoCRACalendarioDAO#insertFeriados(java.util.Map)
	 */
	public void insertFeriados(Map criteria){
		getSqlMapClientTemplate().insert("spusicc.cronograma.mantenimientoCRASQL.executeInsertDiaFeriado", criteria);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.dao.MantenimientoCRACalendarioDAO#insertAnhio(java.util.Map)
	 */
	public void insertAnhio(Map criteria){
		getSqlMapClientTemplate().insert("spusicc.cronograma.mantenimientoCRASQL.insertAnhio", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.dao.MantenimientoCRACalendarioDAO#getCalendarioFeriados(java.util.Map)
	 */
	public List getCalendarioFeriados(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.cronograma.mantenimientoCRASQL.getCalendarioFeriados", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.dao.MantenimientoCRACalendarioDAO#existeAnhio(java.util.Map)
	 */
	public Integer existeAnhio(Map criteria){
		Integer contador = new Integer(0);
			 
		contador = (Integer) getSqlMapClientTemplate().queryForObject("spusicc.cronograma.mantenimientoCRASQL.existeAnhio",criteria);
		if (contador == null) contador = new Integer(0);
		
		return contador;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.dao.MantenimientoCRACalendarioDAO#existeFecha(java.util.Map)
	 */
	public Integer existeFecha(Map criteria){
		Integer contador = new Integer(0);
		 
		contador = (Integer) getSqlMapClientTemplate().queryForObject("spusicc.cronograma.mantenimientoCRASQL.existeFecha",criteria);
		if (contador == null) contador = new Integer(0);
		
		return contador;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.dao.MantenimientoCRACalendarioDAO#deleteCalendario(java.util.Map)
	 */
	public void deleteFeriado(Map criteria){
		getSqlMapClientTemplate().update("spusicc.cronograma.mantenimientoCRASQL.deleteFeriado", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.dao.MantenimientoCRACalendarioDAO#deleteDiaNoLaborable(java.util.Map)
	 */
	public void deleteDiaNoLaborable(Map criteria){
		getSqlMapClientTemplate().update("spusicc.cronograma.mantenimientoCRASQL.executeDeleteDiaNoLaborable", criteria);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.dao.MantenimientoCRACalendarioDAO#updateIndCalendario(java.util.Map)
	 */
	public void updateIndCalendario(Map criteria){
		getSqlMapClientTemplate().update("spusicc.cronograma.mantenimientoCRASQL.updateIndCalendario", criteria);
	}
	
	public int getFeriadoValidoAnhio(Map criteria){
		return ((Integer)getSqlMapClientTemplate().queryForObject("spusicc.cronograma.mantenimientoCRASQL.getFeriadoValidoAnhio",criteria)).intValue();
	}
	
	public void copiarCalendarioPorActividad(Map criteria){
		getSqlMapClientTemplate().update("spusicc.cronograma.mantenimientoCRASQL.executeCopiarCalendarioPorActividad",criteria);
	}	

}
