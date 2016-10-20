package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCCargaPuntajeBonificadoDAO;
import biz.belcorp.ssicc.dao.spusicc.inc.model.CargaPuntajeConsultora;
/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
@Repository("spusicc.procesoINCCargaPuntajeBonificadoDAO")
public class ProcesoINCCargaPuntajeBonificadoDAOIbatis extends BaseDAOiBatis implements
					ProcesoINCCargaPuntajeBonificadoDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCCargaPuntajeBonificadoDAO#executeInsercionCuentaCorrientePuntaje(java.util.Map)
	 */
	public void executeInsercionCuentaCorrientePuntaje(Map params) {
		
	
		getSqlMapClientTemplate()
		.update(
				"spusicc.incentivos.ProcesoINCSQL.executeProcesoCuentaCorrientePuntaje",
				params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCCargaPuntajeBonificadoDAO#insertCargaPuntajeConsultora(biz.belcorp.ssicc.spusicc.inc.dao.model.CargaPuntajeConsultora)
	 */
	public void insertCargaPuntajeConsultora(
			CargaPuntajeConsultora cargaPuntajeConsultora) {
		getSqlMapClientTemplate()
		.insert(
				"spusicc.incentivos.ProcesoINCSQL.insertCargaPuntajeConsultora",
				cargaPuntajeConsultora);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCCargaPuntajeBonificadoDAO#updateIndAplicacionCargaPuntajeConsultora(biz.belcorp.ssicc.spusicc.inc.dao.model.CargaPuntajeConsultora)
	 */
	public void updateIndAplicacionCargaPuntajeConsultora(
			CargaPuntajeConsultora cargaPuntajeConsultora) {
		getSqlMapClientTemplate()
		.update(
				"spusicc.incentivos.ProcesoINCSQL.updateIndAplicacionCargaPuntajeConsultora",
				cargaPuntajeConsultora);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCCargaPuntajeBonificadoDAO#getNumeroLote()
	 */
	public String getNumeroLote() {
		return (String)getSqlMapClientTemplate().
						queryForObject("spusicc.incentivos.ProcesoINCSQL.getNumeroLote");

	}

	
}
