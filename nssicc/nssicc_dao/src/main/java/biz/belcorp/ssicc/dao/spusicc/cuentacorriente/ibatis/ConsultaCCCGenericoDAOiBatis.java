package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ConsultaCCCGenericoDAO;

/**
 * Implementacion del DAO que ejecutara para las consultas Genericas
 * <p>
 * <a href="ConsultarCCCGenericoDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias</a>
 */
@Repository("spusicc.consultaCCCGenericoDAO")
public class ConsultaCCCGenericoDAOiBatis extends BaseDAOiBatis implements ConsultaCCCGenericoDAO {
    	
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ConsultaCCCGenericoDAO#getParametroPais(java.util.Map)
	 */
	public String getParametroPais(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.cuentacorriente.consultaCCCSQL.getParametroPais", criteria);
	}
	
	
	public String getParametroPaisbyCodigo(String codigoParametro) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.cuentacorriente.consultaCCCSQL.getParametroPaisbyCodigo", codigoParametro);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ConsultaCCCGenericoDAO#getTiposBloqueo(java.util.Map)
	 */
	public List getTiposBloqueo(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.cuentacorriente.procesosCCCSQL.getTiposBloqueo",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ConsultaCCCGenericoDAO#getTiposLoteBancarioList()
	 */
	public List getTiposLoteBancarioList() {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.cuentacorriente.procesosCCCSQL.getTiposLoteBancarioList");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ConsultaCCCGenericoDAO#getEstadosLoteBancarioList()
	 */
	public List getEstadosLoteBancarioList() {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.cuentacorriente.procesosCCCSQL.getEstadosLoteBancarioList");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ConsultaCCCGenericoDAO#getTiposLoteBancarioList()
	 */
	public List getEstadosPagoBancarioList() {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.cuentacorriente.procesosCCCSQL.getEstadosPagoBancarioList");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ConsultaCCCGenericoDAO#getTiposErrorPagoBancarioList()
	 */
	public List getTiposErrorPagoBancarioList() {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.cuentacorriente.procesosCCCSQL.getTiposErrorPagoBancarioList");
	}
	
	/**
	 * Obtiene la Lista de los Pagos del Lote Bancario
	 * @return
	 */
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ConsultaCCCGenericoDAO#getDetalleCargaLoteBancario(java.util.Map)
	 */
	public List getDetalleCargaLoteBancario(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.cuentacorriente.procesosCCCSQL.getDetalleCargaLoteBancario",criteria);
	}
			
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ConsultaCCCGenericoDAO#getNumeroLote(java.util.Map)
	 */
	public void getNumeroLote(Map criteria){
		getSqlMapClientTemplate().update(
                "spusicc.cuentacorriente.procesosCCCSQL.executeObtenerNumeroLote", criteria); 
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ConsultaCCCGenericoDAO#getSaldoUnico(java.util.Map)
	 */
	public String getSaldoUnico(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.cuentacorriente.procesosCCCSQL.getSaldoUnico", criteria);
	}
				
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ConsultaCCCGenericoDAO#getSaldoVencido(java.util.Map)
	 */
	public String getSaldoVencido(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.cuentacorriente.procesosCCCSQL.getSaldoVencido", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ConsultaCCCGenericoDAO#getSaldoHistoricoTotal(java.util.Map)
	 */
	public String getSaldoHistoricoTotal(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.cuentacorriente.procesosCCCSQL.getSaldoHistoricoTotal", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ConsultaCCCGenericoDAO#getTipoCADDocumentoLegalList(java.util.Map)
	 */
	public List getTipoCADDocumentoLegalList(Map criteria) {
		return getSqlMapClientTemplate().queryForList(				
				"spusicc.cuentacorriente.procesosCCCSQL.getTipoCADDocumentoLegalList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ConsultaCCCGenericoDAO#getCuentasCorrientesBancariasList(java.util.Map)
	 */
	public List getCuentasCorrientesBancariasList(Map criteria) {
		return getSqlMapClientTemplate().queryForList(				
				"spusicc.cuentacorriente.procesosCCCSQL.getCuentasCorrientesBancariasList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ConsultaCCCGenericoDAO#getBancosDigitablesList(java.util.Map)
	 */
	public List getBancosDigitablesList(Map criteria) {
		return getSqlMapClientTemplate().queryForList(				
				"spusicc.cuentacorriente.procesosCCCSQL.getBancosDigitablesList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ConsultaCCCGenericoDAO#getCuentasCorrientesBancariasExternasList(java.util.Map)
	 */
	public List getCuentasCorrientesBancariasExternasList(Map criteria) {
		return getSqlMapClientTemplate().queryForList(				
				"spusicc.cuentacorriente.procesosCCCSQL.getCuentasCorrientesBancariasExternasList", criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ConsultaCCCGenericoDAO#getBancosCheques()
	 */
	public List getBancosCheques() {
		return getSqlMapClientTemplate().queryForList(				
				"spusicc.cuentacorriente.procesosCCCSQL.getBancosCheques");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ConsultaCCCGenericoDAO#getCuentaCorrienteHistoricaConsultoraList(java.util.Map)
	 */
	public List getCuentaCorrienteHistoricaConsultoraList(Map criteria) {
		return getSqlMapClientTemplate().queryForList(				
				"spusicc.cuentacorriente.procesosCCCSQL.getCuentaCorrienteHistoricaConsultoraList", criteria);
	}
			
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ConsultaCCCGenericoDAO#getExisteCupon(java.util.Map)
	 */
	public Integer getExisteCupon(Map criteria) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"spusicc.cuentacorriente.procesosCCCSQL.getExisteCupon", criteria);
	}
	
	public List getDetalleCuponTramiteDepur(Map criteria){
		return getSqlMapClientTemplate().queryForList(				
				"spusicc.cuentacorriente.procesosCCCSQL.getDetalleCuponTramiteDepur", criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ConsultaCCCGenericoDAO#getTipoCargosDirectos()
	 */
	public List getTipoCargosDirectos(){
		return getSqlMapClientTemplate().queryForList(
				"spusicc.cuentacorriente.procesosCCCSQL.getTiposCargosDirectos");
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ConsultaCCCGenericoDAO#getTipoAbonosDirectos()
	 */
	public List getTipoAbonosDirectos(){
		return getSqlMapClientTemplate().queryForList(
				"spusicc.cuentacorriente.procesosCCCSQL.getTiposAbonosDirectos");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ConsultaCCCGenericoDAO#getTipoCargosDirectosDigitables()
	 */
	public List getTipoCargosDirectosDigitables(){
		return getSqlMapClientTemplate().queryForList(
				"spusicc.cuentacorriente.procesosCCCSQL.getTiposCargosDirectosDigitables");
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ConsultaCCCGenericoDAO#getTipoAbonosDirectosDigitables()
	 */
	public List getTipoAbonosDirectosDigitables(){
		return getSqlMapClientTemplate().queryForList(
				"spusicc.cuentacorriente.procesosCCCSQL.getTipoAbonosDirectosDigitables");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ConsultaCCCGenericoDAO#getTipoOrigenLotesBancarios()
	 */
	public List getTipoOrigenLotesBancarios(){
		return getSqlMapClientTemplate().queryForList(
				"spusicc.cuentacorriente.procesosCCCSQL.getTipoOrigenLotesBancarios");
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ConsultaCCCGenericoDAO#generarCabeceraLoteBancario(java.util.Map)
	 */
	public void generarCabeceraLoteBancario(Map criteria){
		getSqlMapClientTemplate().update(
                "spusicc.cuentacorriente.procesosCCCSQL.generarCabeceraLoteBancario", criteria); 
	}
	
	public List getTipoOrigenBanco(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.cuentacorriente.procesosCCCSQL.getTipoOrigenBanco", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ConsultaCCCGenericoDAO#generarContSaldosCampanias(java.util.Map)
	 */
	public void generarContSaldosCampanias(Map criteria){
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.executeRepoContaSaldosCampanias", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ConsultaCCCGenericoDAO#getTotalFacturaActiva(java.util.Map)
	 */
	@Override
	public String getTotalFacturaActiva(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.cuentacorriente.procesosCCCSQL.getTotalFacturaActiva", criteria);
	}
}
