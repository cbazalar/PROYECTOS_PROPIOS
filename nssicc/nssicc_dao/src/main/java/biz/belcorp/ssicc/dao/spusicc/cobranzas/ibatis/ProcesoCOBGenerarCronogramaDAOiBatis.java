package biz.belcorp.ssicc.dao.spusicc.cobranzas.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.ProcesoCOBGenerarCronogramaDAO;

/**
 * Implementacion del DAO que ejecutara la Actualizacin de Cartera
 * <p>
 * <a href="ProcesoCOBGenerarCronogramaDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:jflorencio@belcorp.biz">JFA</a>
 */
@Repository("spusicc.procesoCOBGenerarCronogramaDAO")
public class ProcesoCOBGenerarCronogramaDAOiBatis extends BaseDAOiBatis implements ProcesoCOBGenerarCronogramaDAO {


	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeAsignacionCartera(java.util.Map)
	 */
	public void executeGenerarCronograma(Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ProcesosCOBSQL.executeGenerarCronograma", criteria);
	}
	
	public List getCronogramaByPaisSociedadEtapaPeriodo(Map criteria) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"sisicc.ProcesosCOBSQL.getCronogramaByPaisSociedadEtapaPeriodo",criteria);
		return resultado;
	}
	
}
