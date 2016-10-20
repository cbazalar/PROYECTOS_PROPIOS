package biz.belcorp.ssicc.dao.spusicc.let.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETCalculoObjetivoIngresoEfectivoLiderDAO;

/**
 * Proceso que realiza el calculo Objetivo Ingresos Efectivos Lder
 * 
 * <p>
 * <a href="ProcesoLETCalculoPedidosObjetivosSeccionCampaniaDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Danny Amaro
 *         
 */
@Repository("spusicc.procesoLETCalculoObjetivoIngresoEfectivoLiderDAO")
public class ProcesoLETCalculoObjetivoIngresoEfectivoLiderDAOiBatis extends BaseDAOiBatis implements ProcesoLETCalculoObjetivoIngresoEfectivoLiderDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLETCalculoObjetivoIngresoEfectivoLiderDAO#executeProcesoLETCalculoObjetivoIngresoEfectivoLider(java.util.Map)
	 */
	public void executeProcesoLETCalculoObjetivoIngresoEfectivoLider(Map params) {
		log.info("Entro a ProcesoLETCalculoObjetivoIngresoEfectivoLiderDAOiBatis - executeProcesoLETCalculoObjetivoIngresoEfectivoLider(java.util.Map)");
		getSqlMapClientTemplate().update("spusicc.let.ProcesosLETSQL.executeProcesoLETCalculoObjetivoIngresoEfectivoLider", params);
		log.info("Salio a ProcesoLETCalculoObjetivoIngresoEfectivoLiderDAOiBatis - executeProcesoLETCalculoObjetivoIngresoEfectivoLider(java.util.Map)");		
	}

}
