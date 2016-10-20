package biz.belcorp.ssicc.dao.spusicc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ProcesoPERCargaVentaDirectaDAO;

/**
 * Implementacion del DAO de ejecucion del SP de Carga Venta Directa
 * 
 * <p>
 * <a href="ProcesoPERCargaVentaDirectaDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:rdelosreyes@belcorp.biz">Richard De los Reyes Prncipe</a>
 * 
 */
@Repository("spusicc.procesoPERCargaVentaDirectaDAO")
public class ProcesoPERCargaVentaDirectaDAOiBatis extends BaseDAOiBatis
		implements ProcesoPERCargaVentaDirectaDAO {

	public String executeCargaVentaDirecta(Map criteria) {
        getSqlMapClientTemplate().update("spusicc.ProcesosPERSQL.procesoPERCargaVentaDirecta", criteria);
        return ((String) criteria.get("totalRegistros"));
	}

	public String getVerificaBloqueoControlAnual(Map criteria) {
		String resultado = (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.ProcesosPERSQL.getVerificaBloqueoControlAnual", criteria);
		
        return resultado;
	}
	
	public void updateBloqueoControlAnual(Map criteria) {
        getSqlMapClientTemplate().update("spusicc.ProcesosPERSQL.updateBloqueoControlAnual", criteria);
	}
	
}
