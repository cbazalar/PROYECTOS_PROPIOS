package biz.belcorp.ssicc.dao.spusicc.cobranzas.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.ProcesoCOBActualizarIncobrableDAO;

/**
 * Implementacion del DAO que ejecutara la Generacion de Cuenta Corriente por Documento Legal
 * <p>
 * <a href="ProcesoCOBActualizarIncobrableDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:jflorencio@belcorp.biz">JFA</a>
 */
@Repository("spusicc.procesoCOBActualizarIncobrableDAO")
public class ProcesoCOBActualizarIncobrableDAOiBatis extends BaseDAOiBatis implements ProcesoCOBActualizarIncobrableDAO {


	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeActualizarIncobrable(java.util.Map)
	 */
	public void executeActualizarIncobrable(Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ProcesosCOBSQL.executeActualizarIncobrable", criteria);
	}

	
	
}
