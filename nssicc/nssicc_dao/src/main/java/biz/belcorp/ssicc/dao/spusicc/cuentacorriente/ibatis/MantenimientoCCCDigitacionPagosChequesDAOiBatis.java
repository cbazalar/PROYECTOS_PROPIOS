package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.MantenimientoCCCDigitacionPagosChequesDAO;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.EstructuraChequesDigitados;

/**
 * <p>
 * <a href="MantenimientoCCCDigitacionPagosChequesDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias</a>
 */
@Repository("spusicc.mantenimientoCCCDigitacionPagosChequesDAO")
public class MantenimientoCCCDigitacionPagosChequesDAOiBatis extends BaseDAOiBatis implements MantenimientoCCCDigitacionPagosChequesDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.MantenimientoCCCDigitacionPagosChequesDAO#getBancosCheques(java.util.Map)
	 */
	public List getBancosCheques(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.cuentacorriente.procesosCCCSQL.getBancosCheques", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.MantenimientoCCCDigitacionPagosChequesDAO#getSucursalesCheques(java.util.Map)
	 */
	public List getSucursalesCheques(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.cuentacorriente.procesosCCCSQL.getSucursalesCheques", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.MantenimientoCCCDigitacionPagosChequesDAO#generarPagoCheque(java.util.Map)
	 */
	public void generarPagoCheque(EstructuraChequesDigitados estructura){
		getSqlMapClientTemplate().update(
				"spusicc.cuentacorriente.procesosCCCSQL.insertEstructuraChequesDigitados", estructura);
	}
			

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.MantenimientoCCCDigitacionPagosChequesDAO#registrarLoteBancario(java.util.Map)
	 */
	public void procesarPagoCheque(Map criteria){
		getSqlMapClientTemplate().update(
				"spusicc.cuentacorriente.procesosCCCSQL.procesarPagoCheque", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.MantenimientoCCCDigitacionPagosChequesDAO#getCabeceraConsultoraByDocIdentidad(java.util.Map)
	 */
	public List getCabeceraConsultoraByDocIdentidad(Map criteria){
		List resultado= getSqlMapClientTemplate().queryForList("spusicc.cuentacorriente.procesosCCCSQL.getCabeceraConsultoraByDocIdentidad", criteria);
		return resultado;
	}

	public void generarLoteBancarioCheque(Map criteria) {
		getSqlMapClientTemplate().update(
				"spusicc.cuentacorriente.procesosCCCSQL.generarLoteBancarioCheque", criteria);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.MantenimientoCCCDigitacionPagosChequesDAO#getClasificacionConsultoraByDocIdentidad(java.util.Map)
	 */
	public Integer getClasificacionConsultoraByDocIdentidad(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.cuentacorriente.procesosCCCSQL.getClasificacionConsultoraByDocIdentidad", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.MantenimientoCCCDigitacionPagosChequesDAO#getConsultoraByDocIdentidad(java.util.Map)
	 */
	public String getConsultoraByDocIdentidad(Map criteriaConsultora) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.cuentacorriente.procesosCCCSQL.getConsultoraByDocIdentidad", criteriaConsultora);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.MantenimientoCCCDigitacionPagosChequesDAO#getActivaConsultoraByDocIdentidad(java.util.Map)
	 */
	public Integer getActivaConsultoraByDocIdentidad(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.cuentacorriente.procesosCCCSQL.getActivaConsultoraByDocIdentidad", criteria);
	}	

}
