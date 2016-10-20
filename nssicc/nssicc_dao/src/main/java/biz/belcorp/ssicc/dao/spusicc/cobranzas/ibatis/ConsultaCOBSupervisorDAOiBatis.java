package biz.belcorp.ssicc.dao.spusicc.cobranzas.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.ConsultaCOBSupervisorDAO;

/**
 * Implementacion del DAO que ejecutara la Consulta de Telecobro
 * <p>
 * <a href="ConsultarCOBSupervisorDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias</a>
 */
@Repository("spusicc.consultaCOBSupervisorDAO")
public class ConsultaCOBSupervisorDAOiBatis extends BaseDAOiBatis implements ConsultaCOBSupervisorDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBSupervisorDAO#getCarteraSupervisorByFilter(java.util.Map)
	 */
	public List getCarteraSupervisorByFilter(Map criteria){		
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getCarteraSupervisorByFilter",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBSupervisorDAO#getConsultoraByFilter(java.util.Map)
	 */
	public List getConsultoraSupervisorByFilter(Map criteria){		
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getConsultoraSupervisorByFilter",criteria);
	}
	
	/* (non-Javadoc)
	 *  Graba la gestion realizada
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBEjecutivoDAO#saveLlamadaConsultora(java.util.Map)
	 */
	public void saveGestionCartera(Map criteria){
		getSqlMapClientTemplate().update("sisicc.ProcesosCOBSQL.saveGestionCartera",criteria);		
	}
	
	/* (non-Javadoc)
	 * Graba la gestion realizada
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBSupervisorDAO#saveCompromisoPago(java.util.Map)
	 */
	public void saveCompromisoPago(Map criteria){
		getSqlMapClientTemplate().insert("sisicc.ProcesosCOBSQL.saveCompromisoPago",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBEjecutivoDAO#getListaGestionesCartera(java.util.Map)
	 */
	public List getListaGestionesCartera(Map criteria){		
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getListaGestionesCartera",criteria);
	}
	
	/* (non-Javadoc)
	 * Obtiene el detalle de las gestiones realizadas sobre una cartera especifica
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBSupervisorDAO#getReferencias(java.util.Map)
	 */
	public List getReferencias(Map criteria){		
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getReferencias",criteria);
	}
	
/* (non-Javadoc)
	 * Obtiene el listado de referencias de la deudora
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBEjecutivoDAO#getReferencias(java.util.Map)
	 */
	public List getReferenciasDeudora(Map criteria){		
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getReferenciasDeudora",criteria);
	}
	
	/* (non-Javadoc)
	 * Obtiene el listado de referencias de la deudora
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBEjecutivoDAO#getReferencias(java.util.Map)
	 */
	public List getAvalesDeudora(Map criteria){		
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getAvalesDeudora",criteria);
	}

	/* (non-Javadoc)
	 * Obtiene las referencias para un cartera especifica
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBSupervisorDAO#getDetalleConsultora(java.util.Map)
	 */
	public List getDetalleConsultora(Map criteria){		
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getCuentaCorrienteConsultora",criteria);
	}
	
	/* (non-Javadoc)
	 * Obtiene el saldo inicial de la consultora
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBSupervisorDAO#getSaldoInicial(java.util.Map)
	 */
	public String getSaldoInicial(Map criteria){
		List list = getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getSaldoInicial",criteria);		
		return list.get(0).toString();
	}
	
	/* (non-Javadoc)
	 * Obtiene el detalle de la cuenta corriente de la consultora
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBSupervisorDAO#getDetalleMovimiento(java.util.Map)
	 */
	public List getDetalleMovimiento(Map criteria){
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getDetalleMovimiento",criteria);
	}
	
	/* (non-Javadoc)
	 * Actualiza el telefono de la deudora
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBSupervisorDAO#actualizarTelefonoDeudora(java.util.Map)
	 */
	public void actualizarTelefonoDeudora(Map criteria){
		getSqlMapClientTemplate().insert("sisicc.ProcesosCOBSQL.actualizarTelefonoDeudora",criteria);
	}
	
	/* (non-Javadoc)
	 * Rebaja la cartera de la deudora
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBSupervisorDAO#rebajarCarteraDeudora(java.util.Map)
	 */
	public void rebajarTemporalCarteraDeudora(Map criteria){
		getSqlMapClientTemplate().insert("sisicc.ProcesosCOBSQL.rebajarTemporalCarteraDeudora",criteria);
	}
	
	/* (non-Javadoc)
	 * Rebaja la cartera de la deudora
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBSupervisorDAO#rebajarCarteraDeudora(java.util.Map)
	 */
	public void rebajarDefinitivoCarteraDeudora(Map criteria){
		getSqlMapClientTemplate().insert("sisicc.ProcesosCOBSQL.rebajarDefinitivoCarteraDeudora",criteria);
	}
	
	/* (non-Javadoc)
	 * Bloquea financieramente una consultora.
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBSupervisorDAO#bloquearFinancieroDeudora(java.util.Map)
	 */
	public void bloquearFinancieroDeudora(Map criteria){
		getSqlMapClientTemplate().insert("sisicc.ProcesosCOBSQL.bloquearFinancieroDeudora",criteria);
	}
}
