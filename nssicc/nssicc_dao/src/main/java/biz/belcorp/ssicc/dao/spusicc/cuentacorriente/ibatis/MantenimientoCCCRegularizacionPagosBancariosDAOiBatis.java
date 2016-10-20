package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ibatis;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.MantenimientoCCCRegularizacionPagosBancariosDAO;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.RegularizacionPagoBancario;
/**
  * <p>
 * <a href="MantenimientoCCCRegularizacionPagosBancariosDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:jflorencio@belcorp.biz"></a>
 */
@Repository("spusicc.mantenimientoCCCRegularizacionPagosBancariosDAO")
public class MantenimientoCCCRegularizacionPagosBancariosDAOiBatis extends BaseDAOiBatis implements MantenimientoCCCRegularizacionPagosBancariosDAO {

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.MantenimientoCCCRegularizacionPagosBancariosDAO#getNumeroLote(java.util.Map)
	 */
	public void getNumeroLote(Map criteria){
		getSqlMapClientTemplate().update(
                "spusicc.cuentacorriente.procesosCCCSQL.executeObtenerNumeroLote", criteria); 
	};
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeAsignacionCartera(java.util.Map)
	 */
	public List  getPagosBancariosPorRegularizarList(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.cuentacorriente.procesosCCCSQL.getPagosBancariosPorRegularizarByFilter", criteria);
	}
			
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.MantenimientoCCCRegularizacionPagosBancariosDAO#getRegularizacionPagoBancarioById(biz.belcorp.ssicc.spusicc.cuentacorriente.model.RegularizacionPagoBancario)
	 */
	public RegularizacionPagoBancario getRegularizacionPagoBancarioById(RegularizacionPagoBancario regularizacionPagoBancario) {
		RegularizacionPagoBancario resultado = (RegularizacionPagoBancario) getSqlMapClientTemplate().queryForObject(
				"spusicc.cuentacorriente.procesosCCCSQL.getRegularizacionPagoBancarioById",regularizacionPagoBancario);
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBCronogramaCarteraDAO#updateCronogramaCartera(java.util.Map)
	 */
	public void updatePagoBancarioPorRegularizar(Map criteria) {
        getSqlMapClientTemplate().update(
                "spusicc.cuentacorriente.procesosCCCSQL.executeRegularizacionPagoBancario", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.MantenimientoCCCRegularizacionPagosBancariosDAO#deletePagoBancarioPorRegularizar(java.util.Map)
	 */
	public void deletePagoBancarioPorRegularizar(Map criteria) {
        getSqlMapClientTemplate().update(
                "spusicc.cuentacorriente.procesosCCCSQL.executeEliminarPagoBancarioPorRegularizar", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.MantenimientoCCCRegularizacionPagosBancariosDAO#archivarPagoBancarioPorRegularizar(java.util.Map)
	 */
	public void archivarPagoBancarioPorRegularizar(Map criteria) {
        getSqlMapClientTemplate().update(
                "spusicc.cuentacorriente.procesosCCCSQL.executeArchivarPagoBancarioPorRegularizar", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.MantenimientoCCCRegularizacionPagosBancariosDAO#executeRegistrarLoteBancario(java.util.Map)
	 */
	public void executeRegistrarLoteBancario (Map criteria){
		getSqlMapClientTemplate().update(
                "spusicc.cuentacorriente.procesosCCCSQL.executeRegistrarLoteBancario", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.MantenimientoCCCRegularizacionPagosBancariosDAO#updateListCuponesTramite(java.util.Map)
	 */
	public void updateListCuponesTramite(Map criteria) {
        getSqlMapClientTemplate().update(
                "spusicc.cuentacorriente.procesosCCCSQL.updateCuponTramiDepur", criteria);
	}

	@Override
	public Map getPagosBancariosPorRegularizarDividirPagoByFilter(Map criteria) {
		return (HashMap) getSqlMapClientTemplate().queryForObject("spusicc.cuentacorriente.procesosCCCSQL.getPagosBancariosPorRegularizarDividirPagoByFilter", criteria);
	}
	
		
}
