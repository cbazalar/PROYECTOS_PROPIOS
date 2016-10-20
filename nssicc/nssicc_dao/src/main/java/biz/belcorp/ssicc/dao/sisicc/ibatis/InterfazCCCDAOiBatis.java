package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.InterfazCCCDAO;

/**
 * Implementacion iBatis del DAO de la Interfaz de Cuenta Corriente.
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio</a>
 */
@Repository("sisicc.interfazCCCDAO")
public class InterfazCCCDAOiBatis extends BaseDAOiBatis implements
		InterfazCCCDAO {
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCCCDAO#deleteInterfazCCCRecepcionarMovimientosBancarios(java.util.Map)
	 */
	public void deleteInterfazCCCRecepcionarMovimientosBancarios(Map map) {
		getSqlMapClientTemplate().delete(
				"sisicc.InterfazCCCSQL.deleteInterfazCCCRecepcionarMovimientosBancarios", map);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCCCDAO#insertInterfazCCCRecepcionarMovimientosBancarios(java.util.Map)
	 */
	public void insertInterfazCCCRecepcionarMovimientosBancarios(Map map) {
		getSqlMapClientTemplate().insert(
				"sisicc.InterfazCCCSQL.insertInterfazCCCRecepcionarMovimientosBancarios", map);		
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCCCDAO#executeInterfazCCCRecepcionarMovimientosBancarios(java.util.Map)
	 */
	public void executeInterfazCCCRecepcionarMovimientosBancarios(Map map) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazCCCSQL.executeInterfazCCCRecepcionarMovimientosBancarios",map);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCCCDAO#executeInterfazCCCEnviarSaldosConsultoras(java.util.Map)
	 */
	public void executeInterfazCCCEnviarSaldosConsultoras(Map map) {
				
		getSqlMapClientTemplate().update(
				"sisicc.InterfazCCCSQL.executeInterfazCCCEnviarSaldosConsultoras",map);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCCCDAO#executeInterfazCCCEnviarSaldosCastigadas(java.util.Map)
	 */
	public void executeInterfazCCCEnviarSaldosCastigadas(Map map) {
				
		getSqlMapClientTemplate().update(
				"sisicc.InterfazCCCSQL.executeInterfazCCCEnviarSaldosCastigadas",map);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCCCDAO#executeInterfazCCCEnviarMaestroConsultoras(java.util.Map)
	 */
	public void executeInterfazCCCEnviarMaestroConsultoras(Map map) {
		
		getSqlMapClientTemplate().update(
				"sisicc.InterfazCCCSQL.executeInterfazCCCEnviarMaestroConsultoras",map);
	}
	
   /* (non-Javadoc)
   * @see biz.belcorp.ssicc.sisicc.dao.InterfazCCCDAO#executeInterfazCCCEnviarNovedadesConsultoras(java.util.Map)
   */
   public void executeInterfazCCCEnviarNovedadesConsultoras(Map map) {
		
		getSqlMapClientTemplate().update(
				"sisicc.InterfazCCCSQL.executeInterfazCCCEnviarNovedadesConsultoras",map);
	}
	
   
   public void executeInterfazCCCEnviarSAPFICobranzas(Map map) {
		
		getSqlMapClientTemplate().update(
				"sisicc.InterfazCCCSQL.executeInterfazCCCEnviarSAPFICobranzas",map);
	}
   
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCCCDAO#getNumeroLote(java.util.Map)
	 */
	public void getNumeroLote(Map criteria){
		getSqlMapClientTemplate().update(
                "spusicc.cuentacorriente.procesosCCCSQL.executeObtenerNumeroLote", criteria); 
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCCCDAO#getValParaCCCParamGener(java.lang.String)
	 */
	public String getValParaCCCParamGener(String codigoParametro) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.cuentacorriente.procesosCCCSQL.getValParaCCCParamGener", codigoParametro);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCCCDAO#getCantidadRegistroMovimBancaRuteo
	 */
	public Integer getCantidadRegistroMovimBancaRuteo() {
		
		return (Integer) getSqlMapClientTemplate().
				queryForObject("spusicc.cuentacorriente.procesosCCCSQL.getCantidadRegistroMovimBancaRuteo", null);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCCCDAO#insertCCCMovimBancaRuteo(java.lang.String)
	 */
	public void insertCCCMovimBancaRuteo(String numeroLote) {
		getSqlMapClientTemplate().insert("spusicc.cuentacorriente.procesosCCCSQL.insertCCCMovimBancaRuteo", numeroLote);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCCCDAO#executeCCCRegistrarLoteBancario(java.util.Map)
	 */
	public void executeCCCRegistrarLoteBancario(Map params) {
		getSqlMapClientTemplate().update(
                "spusicc.cuentacorriente.procesosCCCSQL.executeCCCRegistrarLoteBancario", params); 
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCCCDAO#executeGenerarCargoFamiliaSeguraCCPPGP3(java.util.Map)
	 */
	public void executeGenerarCargoFamiliaSeguraCCPPGP3(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazCCCSQL.executeGenerarCargoFamiliaSeguraCCPPGP3", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCCCDAO#executeGenerarCargoGastoAdministrativoCCPPGP3(java.util.Map)
	 */
	public void executeGenerarCargoGastoAdministrativoCCPPGP3(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazCCCSQL.executeGenerarCargoGastoAdministrativoCCPPGP3", params);
	}
	
	public void executeInterfazCCCCargasDeudasWeb(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazCCCSQL.executeInterfazCCCCargasDeudasWeb", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCCCDAO#executeGenerarInteresesMoraCCPPGP3(java.util.Map)
	 */
	public void executeGenerarInteresesMoraCCPPGP3(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazCCCSQL.executeGenerarInteresesMoraCCPPGP3", params);
	}
}