package biz.belcorp.ssicc.dao.spusicc.cobranzas.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.ConsultaCOBTelecobroDAO;

/**
 * Implementacion del DAO que ejecutara la Consulta de Telecobro
 * <p>
 * <a href="ConsultarCOBTelecobroDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:doliva@belcorp.biz">Dennys Oliva Iriarte</a>
 */
@Repository("spusicc.consultaCOBTelecobroDAO")
public class ConsultaCOBTelecobroDAOiBatis extends BaseDAOiBatis implements ConsultaCOBTelecobroDAO {
    		
	
	
	public List getConsultorasByFilter(Map criteria){		
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getConsultorasByFilter",criteria);
	}
	
	public void saveLlamadaConsultora(Map criteria){
		getSqlMapClientTemplate().update("sisicc.ProcesosCOBSQL.saveLlamadaConsultora",criteria);		
	}
	
	public void saveCompromisoPago(Map criteria){
		getSqlMapClientTemplate().insert("sisicc.ProcesosCOBSQL.saveCompromisoPago",criteria);
	}
	
	public List getListaLlamadas(Map criteria){		
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getListaLlamadas",criteria);
	}
	
	public List getReferencias(Map criteria){		
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getReferencias",criteria);
	}
	
	public List getDetalleConsultora(Map criteria){		
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getCuentaCorrienteConsultora",criteria);
	}
	
	
	public String getSaldoInicial(Map criteria){
		List list = getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getSaldoInicial",criteria);		
		return list.get(0).toString();
	}
	
	public List getDetalleMovimiento(Map criteria){
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getDetalleMovimiento",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBTelecobroDAO#getCabeceraMovimientoBanco(java.util.Map)
	 */
	public List getCabeceraMovimientoBanco(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getCabeceraMovimientoBanco",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBTelecobroDAO#getDetalleMovimientoBanco(java.util.Map)
	 */
	public List getDetalleMovimientoBanco(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getDetalleMovimientoBanco",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBTelecobroDAO#getDetalleMovimientoCuenta(java.util.Map)
	 */
	public List getDetalleMovimientoCuenta(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getDetalleMovimientoCuenta",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBTelecobroDAO#executeCtaCteUltimasCampanhas(java.util.Map)
	 */
	public void executeCtaCteUltimasCampanhas(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ConsultaHIPSQL.executeCtaCteUltimasCampanhas",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBTelecobroDAO#getCtaCteUltimasNCampanhas(java.util.Map)
	 */
	public List getCtaCteUltimasNCampanhas(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getCtaCteUltimasNCampanhas",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBTelecobroDAO#getCtaCteUltimasNCampanhasCabecera(java.util.Map)
	 */
	public List getCtaCteUltimasNCampanhasCabecera(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getCtaCteUltimasNCampanhasCabecera", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBTelecobroDAO#insertReporteHIPCuentaCorrientesCampanha(java.util.Map)
	 */
	public void insertReporteHIPCuentaCorrientesCampanha(Map registro) {
		getSqlMapClientTemplate().insert("sisicc.ConsultaHIPSQL.insertReporteHIPCuentaCorrientesCampanha",registro);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBTelecobroDAO#deleteReporteHIPCuentaCorrientesCampanha(java.lang.String)
	 */
	public void deleteReporteHIPCuentaCorrientesCampanha(String codigoUsuario) {
		getSqlMapClientTemplate().update("sisicc.ConsultaHIPSQL.deleteReporteHIPCuentaCorrientesCampanha",codigoUsuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBTelecobroDAO#getPedidoCampanhaProceso(java.util.Map)
	 */
	public Integer getPedidoCampanhaProceso(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("sisicc.ConsultaHIPSQL.getPedidoCampanhaProceso", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBTelecobroDAO#getCierreRegionCampanhaProceso(java.util.Map)
	 */
	public Integer getCierreRegionCampanhaProceso(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("sisicc.ConsultaHIPSQL.getCierreRegionCampanhaProceso", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBTelecobroDAO#getValidarConsultoraIncobrablesMigradas(java.util.Map)
	 */
	public Integer getValidarConsultoraIncobrablesMigradas(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("sisicc.ProcesosCOBSQL.getValidarConsultoraIncobrablesMigradas", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ConsultaCOBTelecobroDAO#getCuentaCorrienteConsultoraIncobrablesMigradas(java.util.Map)
	 */
	public List getCuentaCorrienteConsultoraIncobrablesMigradas(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getCuentaCorrienteConsultoraIncobrablesMigradas", criteria);
	}

	@Override
	public List getAbonoProcesoCDR(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getAbonoProcesoCDR", criteria);
	}
	
}
