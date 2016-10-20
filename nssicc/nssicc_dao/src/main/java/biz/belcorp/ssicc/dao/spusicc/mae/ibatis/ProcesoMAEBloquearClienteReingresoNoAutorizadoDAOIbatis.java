package biz.belcorp.ssicc.dao.spusicc.mae.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.mae.ProcesoMAEBloquearClienteReingresoNoAutorizadoDAO;

/**
 * @author peextsapaza
 *
 */
@Repository("spusicc.procesoMAEBloquearClienteReingresoNoAutorizadoDAO")
public class ProcesoMAEBloquearClienteReingresoNoAutorizadoDAOIbatis extends BaseDAOiBatis implements 
				ProcesoMAEBloquearClienteReingresoNoAutorizadoDAO  {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoMAEBloquearClienteReingresoNoAutorizadoDAO#executeGenerarSolicitudBolsaFaltantes(java.util.Map)
	 */
	public void executeBloquearClienteReingresoNoAutorizado(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.executeBloquearClienteReingresoNoAutorizado",params);
		
	}

}

