/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.flx.ibatis;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.flx.MantenimientoFLXConsultoraDAO;
import biz.belcorp.ssicc.dao.spusicc.flx.model.ConsultoraFlexipago;
import biz.belcorp.ssicc.dao.spusicc.flx.model.ConsultoraFlexipagoAuditoria;
import biz.belcorp.ssicc.dao.spusicc.flx.model.ConsultoraFlexipagoObjetada;
import biz.belcorp.ssicc.dao.spusicc.flx.model.ConsultoraFlexipagoPK;
import biz.belcorp.ssicc.dao.spusicc.flx.model.InteresFlexipago;

/**
 * @author Ivan Tocto Jaimes 
 *
 */
@Repository("spusicc.mantenimientoFLXConsultoraDAO")
public class MantenimientoFLXConsultoraDAOiBatis extends BaseDAOiBatis implements MantenimientoFLXConsultoraDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flx.dao.MantenimientoFLXConsultoraDAO#getConsultorasByCriteria(java.util.Map)
	 */
	public List getConsultorasByCriteria(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.InterfazFLXSQL.getConsultorasByCriteria", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flx.dao.MantenimientoFLXConsultoraDAO#getConsultora(biz.belcorp.ssicc.spusicc.flx.dao.model.ConsultoraFlexipagoPK)
	 */
	public ConsultoraFlexipago getConsultora(ConsultoraFlexipagoPK pk) {
		ConsultoraFlexipago c = (ConsultoraFlexipago) getSqlMapClientTemplate().queryForObject("sisicc.InterfazFLXSQL.getConsultora", pk);
        return c;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flx.dao.MantenimientoFLXConsultoraDAO#updateConsultora(biz.belcorp.ssicc.spusicc.flx.dao.model.ConsultoraFlexipago, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateConsultora(ConsultoraFlexipago consultora, Usuario usuario) {
		getSqlMapClientTemplate().update("sisicc.InterfazFLXSQL.updateConsultora", consultora);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flx.dao.MantenimientoFLXConsultoraDAO#insertAuditoriaConsultora(biz.belcorp.ssicc.spusicc.flx.dao.model.ConsultoraFlexipagoAuditoria)
	 */
	public void insertAuditoriaConsultora(ConsultoraFlexipagoAuditoria auditoria) {
		getSqlMapClientTemplate().insert("sisicc.InterfazFLXSQL.insertAuditoriaConsultora", auditoria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flx.dao.MantenimientoFLXConsultoraDAO#getCalificacionesComportamiento()
	 */
	public List getCalificacionesComportamiento() {
		return getSqlMapClientTemplate().queryForList("sisicc.InterfazFLXSQL.getCalificacionesComportamiento", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flx.dao.MantenimientoFLXConsultoraDAO#getCalificacionesExperiencia()
	 */
	public List getCalificacionesExperiencia() {
		return getSqlMapClientTemplate().queryForList("sisicc.InterfazFLXSQL.getCalificacionesExperiencia", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flx.dao.MantenimientoFLXConsultoraDAO#getConsultoraAuditoriaByCodigosAccion(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public ConsultoraFlexipagoAuditoria getConsultoraAuditoriaByCodigosAccion(
			String codigoPais, String codigoCliente, String codigoAccion1,
			String codigoAccion2) {
		
		Map params = new HashMap();
		params.put("codigoPais", codigoPais);
		params.put("codigoCliente", codigoCliente);
		params.put("codigoAccion1", codigoAccion1);
		params.put("codigoAccion2", codigoAccion2);
		
		ConsultoraFlexipagoAuditoria c = (ConsultoraFlexipagoAuditoria) getSqlMapClientTemplate().queryForObject("sisicc.InterfazFLXSQL.getConsultoraAuditoriaByCodigosAccion", params);
        if (c == null) {
            c = new ConsultoraFlexipagoAuditoria();
        }
        
        return c;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flx.dao.MantenimientoFLXConsultoraDAO#getCodigoConsultora(java.lang.String)
	 */
	public String getCodigoConsultora(String codigoConsultora) {
		return (String) getSqlMapClientTemplate().queryForObject("sisicc.InterfazFLXSQL.getCodigoConsultora", codigoConsultora);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flx.dao.MantenimientoFLXConsultoraDAO#getConsultorasObjetadaByCriteria(java.util.Map)
	 */
	public List getConsultorasObjetadaByCriteria(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.InterfazFLXSQL.getConsultorasObjetadaByCriteria", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flx.dao.MantenimientoFLXConsultoraDAO#insertConsultoraObjetada(biz.belcorp.ssicc.spusicc.flx.dao.model.ConsultoraFlexipagoObjetada, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertConsultoraObjetada(
			ConsultoraFlexipagoObjetada consultora, Usuario usuario) {
		getSqlMapClientTemplate().insert("sisicc.InterfazFLXSQL.insertConsultoraObjetada", consultora);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flx.dao.MantenimientoFLXConsultoraDAO#getConsultoraObjetada(biz.belcorp.ssicc.spusicc.flx.dao.model.ConsultoraFlexipagoPK)
	 */
	public ConsultoraFlexipagoObjetada getConsultoraObjetada(
			ConsultoraFlexipagoPK pk) {		
		ConsultoraFlexipagoObjetada c = (ConsultoraFlexipagoObjetada) getSqlMapClientTemplate().queryForObject("sisicc.InterfazFLXSQL.getConsultoraObjetada", pk);
        return c;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flx.dao.MantenimientoFLXConsultoraDAO#updateConsultoraObjetada(biz.belcorp.ssicc.spusicc.flx.dao.model.ConsultoraFlexipagoObjetada, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateConsultoraObjetada(
			ConsultoraFlexipagoObjetada consultora, Usuario usuario) {		
		getSqlMapClientTemplate().update("sisicc.InterfazFLXSQL.updateConsultoraObjetada", consultora);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flx.dao.MantenimientoFLXConsultoraDAO#deleteConsultoraObjetada(biz.belcorp.ssicc.spusicc.flx.dao.model.ConsultoraFlexipagoPK, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteConsultoraObjetada(ConsultoraFlexipagoPK pk,
			Usuario usuario) {
		getSqlMapClientTemplate().update("sisicc.InterfazFLXSQL.deleteConsultoraObjetada", pk);
		
	}
	
	public List getDatosConsultora(Map criteria){
		return getSqlMapClientTemplate().queryForList("sisicc.InterfazFLXSQL.getDatosConsultora", criteria);
	}
	
	public List getDatosConsultoraDetalle(Map criteria){
		return getSqlMapClientTemplate().queryForList("sisicc.InterfazFLXSQL.getDatosConsultoraDetalle", criteria);
	}

	public List getDatosUsosFlexipago(Map criteria){
		return getSqlMapClientTemplate().queryForList("sisicc.InterfazFLXSQL.getDatosUsosFlexipago", criteria);
	}
	
        @Override
	public void executeReporteDetalleSaldo(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.InterfazFLXSQL.executeReporteDetalleSaldo", criteria);		
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flx.dao.MantenimientoFLXConsultoraDAO#getMotivos()
	 */
	public List getMotivos() {
		return getSqlMapClientTemplate().queryForList("sisicc.InterfazFLXSQL.getMotivos", null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flx.dao.MantenimientoFLXConsultoraDAO#getPasoPedido(java.util.Map)
	 */
	public String getPasoPedido(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.InterfazFLXSQL.getPasoPedido",criteria);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flx.dao.MantenimientoFLXConsultoraDAO#getCerroRegion(java.util.Map)
	 */
	public String getCerroRegion(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.InterfazFLXSQL.getCerroRegion",criteria);		
	}
	
	public String getCodigoConsultoraHabil(String codigoConsultora) {
		return (String) getSqlMapClientTemplate().queryForObject("sisicc.InterfazFLXSQL.getCodigoConsultoraHabil", codigoConsultora);
	}
	
	public String getContratoConsultoraHabil(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.InterfazFLXSQL.getContratoConsultoraHabil",criteria);
	}
	
	public void updateEnvioContrato(ConsultoraFlexipago c) {
		getSqlMapClientTemplate().update("sisicc.InterfazFLXSQL.updateEnvioContrato", c);
	}
	
	public String getConsultoraActiva(ConsultoraFlexipago c) {
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.InterfazFLXSQL.getConsultoraActiva",c);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.flx.MantenimientoFLXConsultoraDAO#getInteresFlexipago()
	 */
	public List getInteresFlexipagoList() {
		return getSqlMapClientTemplate().queryForList("sisicc.InterfazFLXSQL.getInteresFlexipagoList", null);
	}
	
	public InteresFlexipago getInteresFlexipago(InteresFlexipago bean) {
		return (InteresFlexipago) this.getSqlMapClientTemplate().queryForObject(
				"sisicc.InterfazFLXSQL.getInteresFlexipago",bean);
	}
	
	public void insertInteresFlexipago(InteresFlexipago bean, Usuario usuario) {
		this.getSqlMapClientTemplate().insert(
				"sisicc.InterfazFLXSQL.insertInteresFlexipago", bean);		
	}
	
	public void deleteInteresesFlexipago(InteresFlexipago bean, Usuario usuario) {
		this.getSqlMapClientTemplate().update(
				"sisicc.InterfazFLXSQL.deleteInteresesFlexipago", bean);
	}
	
	public void deleteTotalInteresesFlexipago() {
		this.getSqlMapClientTemplate().update(
				"sisicc.InterfazFLXSQL.deleteTotalInteresesFlexipago", null);
	}
	
	public String getRangoMaxHasta() {
		return (String) getSqlMapClientTemplate().queryForObject("sisicc.InterfazFLXSQL.getRangoMaxHasta", null);
	}
	
	public int getCantDecimalesxPais(Map criteria) {
		return (Integer) getSqlMapClientTemplate().queryForObject("sisicc.InterfazFLXSQL.getCantDecimalesxPais", criteria);
	}
	
}
