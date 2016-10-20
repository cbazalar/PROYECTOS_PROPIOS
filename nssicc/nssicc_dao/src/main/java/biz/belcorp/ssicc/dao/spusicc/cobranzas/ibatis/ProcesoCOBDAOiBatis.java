package biz.belcorp.ssicc.dao.spusicc.cobranzas.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.ProcesoCOBDAO;

/**
 * Implementacion del DAO que ejecutara la Generacion de Cuenta Corriente por Documento Legal
 * <p>
 * <a href="ProcesoCOBDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:jflorencio@belcorp.biz">JFA</a>
 */
@Repository("spusicc.procesoCOBDAO")
public class ProcesoCOBDAOiBatis extends BaseDAOiBatis implements ProcesoCOBDAO {


	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeActualizarCartera(java.util.Map)
	 */
	public List getLista(String tipoLista, Map params){		
		return getSqlMapClientTemplate().queryForList(
				"sisicc.ProcesosCOBSQL."+tipoLista, params);
		
	}
}
