package biz.belcorp.ssicc.dao.spusicc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.ProcesoPERActualizarNumeroConsolidadoDAO;

/**
 * Implementacion del DAO de ejecucion del SP de Actualizacion de Numero Consolidado
 * 
 * <p>
 * <a href="ProcesoPERActualizarNumeroConsolidadoDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:rdelosreyes@belcorp.biz">Richard De los Reyes Prncipe</a>
 * 
 */
@Repository("spusicc.procesoPERActualizarNumeroConsolidadoDAO")
public class ProcesoPERActualizarNumeroConsolidadoDAOiBatis extends BaseDAOiBatis
		implements ProcesoPERActualizarNumeroConsolidadoDAO {

	public void updateNumeroConsolidadoPercepciones(Map criteria, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.ProcesosPERSQL.updateNumeroConsolidadoPercepciones", criteria);
	}

}
