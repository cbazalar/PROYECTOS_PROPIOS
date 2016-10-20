package biz.belcorp.ssicc.dao.spusicc.cobranzas.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.ConsultaCOBEjecutivoDAO;

/**
 * Implementacion del DAO que ejecutara la Consulta de Ejecutivo
 * <p>
 * <a href="ConsultarCOBEjecutivoDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias</a>
 */
@Repository("spusicc.consultaCOBEjecutivoDAO")
public class ConsultaCOBEjecutivoDAOiBatis extends BaseDAOiBatis implements ConsultaCOBEjecutivoDAO {
    	
	
	
	/* (non-Javadoc)
	 * Obtiene la Cartera del Ejecutivo de Cobranzas
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBEjecutivoDAO#getCarteraEjecutivoByFilter(java.util.Map)
	 */
	public List getCarteraEjecutivoByFilter(Map criteria){		
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getCarteraEjecutivoByFilter",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBEjecutivoDAO#getCarteraLlamadaEntranteByFilter(java.util.Map)
	 */
	public List getCarteraLlamadaEntranteByFilter(Map criteria){		
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getCarteraLlamadaEntranteByFilter",criteria);
	}
	
	/* (non-Javadoc)
	 *  Graba la gestion realizada
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBEjecutivoDAO#saveLlamadaConsultora(java.util.Map)
	 */
	public void saveGestionCartera(Map criteria){
		getSqlMapClientTemplate().update("sisicc.ProcesosCOBSQL.saveGestionCartera",criteria);		
	}
	
	/* (non-Javadoc)
	 * Graba el compromiso de pago
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBEjecutivoDAO#saveCompromisoPago(java.util.Map)
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
	 * Obtiene el listado de referencias de la deudora
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBEjecutivoDAO#getReferencias(java.util.Map)
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
	 * Obtiene el detalle de la cuenta corriente de la deudora
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBEjecutivoDAO#getDetalleConsultora(java.util.Map)
	 */
	public List getDetalleConsultora(Map criteria){		
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getCuentaCorrienteConsultora",criteria);
	}
	
	/* (non-Javadoc)
	 * Obtiene el saldo actual de la deudora
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBEjecutivoDAO#getSaldoInicial(java.util.Map)
	 */
	public String getSaldoInicial(Map criteria){
		List list = getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getSaldoInicial",criteria);		
		return list.get(0).toString();
	}
	
	/* (non-Javadoc)
	 * Obtiene el detalle de un cargo de la deudora
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBEjecutivoDAO#getDetalleMovimiento(java.util.Map)
	 */
	public List getDetalleMovimiento(Map criteria){
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getDetalleMovimiento",criteria);
	}
	
	/* (non-Javadoc)
	 *  Actualiza los telefonos de la deudora
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBEjecutivoDAO#actualizarTelefonoDeudora(java.util.Map)
	 */
	public void actualizarTelefonoDeudora(Map criteria){
		getSqlMapClientTemplate().insert("sisicc.ProcesosCOBSQL.actualizarTelefonoDeudora",criteria);
	}
	
}
