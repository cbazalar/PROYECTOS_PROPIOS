package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ibatis;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.MantenimientoCCCGestionarErroresPagosBancariosDAO;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.ErrorPagoBancario;
/**
  * <p>
 * <a href="MantenimientoCCCGestionarErroresPagosBancariosDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:jflorencio@belcorp.biz"></a>
 */
@Repository("spusicc.mantenimientoCCCGestionarErroresPagosBancariosDAO")
public class MantenimientoCCCGestionarErroresPagosBancariosDAOiBatis extends BaseDAOiBatis implements MantenimientoCCCGestionarErroresPagosBancariosDAO {

			
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeAsignacionCartera(java.util.Map)
	 */
	public List  getPagosBancariosPorGestionarList(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.cuentacorriente.procesosCCCSQL.getPagosBancariosPorGestionarByFilter", criteria);
	}
			
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.MantenimientoCCCGestionarErroresPagosBancariosDAO#getRegularizacionPagoBancarioById(biz.belcorp.ssicc.spusicc.cuentacorriente.model.RegularizacionPagoBancario)
	 */
	public ErrorPagoBancario getErrorPagoBancarioById(String oidMovimientoBancario) {
		ErrorPagoBancario resultado = (ErrorPagoBancario) getSqlMapClientTemplate().queryForObject(
				"spusicc.cuentacorriente.procesosCCCSQL.getErrorPagoBancarioById",oidMovimientoBancario);
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBCronogramaCarteraDAO#updateCronogramaCartera(java.util.Map)
	 */
	public void gestionarErrorPagoBancario(Map criteria) {
        getSqlMapClientTemplate().update(
                "spusicc.cuentacorriente.procesosCCCSQL.executeGestionarErrorPagoBancario", criteria);
	}
	
	public void aprobarErrorPagoBancario(Map criteria){
		getSqlMapClientTemplate().insert("spusicc.cuentacorriente.procesosCCCSQL.aprobarErrorPagoBancario", criteria);
	}
	
	public void deleteErrorPagoBancario(Map criteria){
		getSqlMapClientTemplate().insert("spusicc.cuentacorriente.procesosCCCSQL.executeEliminarErrorPagoBancario", criteria);
	}
	
}
