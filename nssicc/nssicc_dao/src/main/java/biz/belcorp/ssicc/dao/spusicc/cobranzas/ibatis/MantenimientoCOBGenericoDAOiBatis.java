package biz.belcorp.ssicc.dao.spusicc.cobranzas.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.MantenimientoCOBGenericoDAO;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.AsignacionCarteraCobrador;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.AsignacionCarteraSupervisor;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.CobradorPais;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.CobradorUnidadAdministrativa;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.EtapaDeuda;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.ExcepcionAsignacionCartera;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.ExcepcionClasificacionCliente;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.ParametrosGeneralesCOB;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.SeccionNoCritica;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.UnidadAdministrativaCronogramaCartera;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.ValidacionAsignacionCartera;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.ZonaNoCritica;


/**
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias</a>
 *
 */
@Repository("spusicc.mantenimientoCOBGenericoDAO")
public class MantenimientoCOBGenericoDAOiBatis extends BaseDAOiBatis 
    implements MantenimientoCOBGenericoDAO {
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#getListaEtapaDeuda(biz.belcorp.ssicc.spusicc.cobranzas.model.EtapaDeuda)
	 */
	public List getListaEtapaDeuda(EtapaDeuda bean) {
		return this.getSqlMapClientTemplate().queryForList(
				"spusicc.cobranzas.mantenimientoCOBSQL.getEtapaDeudaList",bean);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#getEtapaDeuda(biz.belcorp.ssicc.spusicc.cobranzas.model.EtapaDeuda)
	 */
	public EtapaDeuda getEtapaDeuda(EtapaDeuda bean) {
		return (EtapaDeuda) this.getSqlMapClientTemplate().queryForObject(
				"spusicc.cobranzas.mantenimientoCOBSQL.getEtapaDeuda",bean);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#insertEtapaDeuda(biz.belcorp.ssicc.spusicc.cobranzas.model.EtapaDeuda, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertEtapaDeuda(EtapaDeuda bean,
			Usuario usuario) {
		this.getSqlMapClientTemplate().insert(
				"spusicc.cobranzas.mantenimientoCOBSQL.insertEtapaDeuda", bean);		
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#updateEtapaDeuda(biz.belcorp.ssicc.spusicc.cobranzas.model.EtapaDeuda, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateEtapaDeuda(EtapaDeuda bean, Usuario usuario) {
		this.getSqlMapClientTemplate().update(
				"spusicc.cobranzas.mantenimientoCOBSQL.updateEtapaDeuda", bean);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#deleteEtapaDeuda(biz.belcorp.ssicc.spusicc.cobranzas.model.EtapaDeuda, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteEtapaDeuda(EtapaDeuda bean, Usuario usuario) {
		this.getSqlMapClientTemplate().update(
				"spusicc.cobranzas.mantenimientoCOBSQL.deleteEtapaDeuda", bean);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#getListaCobradorPais(biz.belcorp.ssicc.spusicc.cobranzas.model.CobradorPais)
	 */
	public List getListaCobradorPais(CobradorPais bean) {
		return this.getSqlMapClientTemplate().queryForList(
				"spusicc.cobranzas.mantenimientoCOBSQL.getCobradorPaisList",bean);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#getCobradorPais(biz.belcorp.ssicc.spusicc.cobranzas.model.CobradorPais)
	 */
	public CobradorPais getCobradorPais(CobradorPais bean) {
		return (CobradorPais) this.getSqlMapClientTemplate().queryForObject(
				"spusicc.cobranzas.mantenimientoCOBSQL.getCobradorPais",bean);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#insertCobradorPais(biz.belcorp.ssicc.spusicc.cobranzas.model.CobradorPais, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertCobradorPais(CobradorPais bean,
			Usuario usuario) {
		this.getSqlMapClientTemplate().insert(
				"spusicc.cobranzas.mantenimientoCOBSQL.insertCobradorPais", bean);		
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#updateCobradorPais(biz.belcorp.ssicc.spusicc.cobranzas.model.CobradorPais, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateCobradorPais(CobradorPais bean, Usuario usuario) {
		this.getSqlMapClientTemplate().update(
				"spusicc.cobranzas.mantenimientoCOBSQL.updateCobradorPais", bean);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#deleteCobradorPais(biz.belcorp.ssicc.spusicc.cobranzas.model.CobradorPais, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteCobradorPais(CobradorPais bean, Usuario usuario) {
		this.getSqlMapClientTemplate().update(
				"spusicc.cobranzas.mantenimientoCOBSQL.deleteCobradorPais", bean);
	}
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#getListaCobradorUnidadAdministrativa(biz.belcorp.ssicc.spusicc.cobranzas.model.CobradorUnidadAdministrativa)
	 */
	public List getListaCobradorUnidadAdministrativa(CobradorUnidadAdministrativa bean) {
		return this.getSqlMapClientTemplate().queryForList(
				"spusicc.cobranzas.mantenimientoCOBSQL.getCobradorUnidadAdministrativaList",bean);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#getCobradorUnidadAdministrativa(biz.belcorp.ssicc.spusicc.cobranzas.model.CobradorUnidadAdministrativa)
	 */
	public CobradorUnidadAdministrativa getCobradorUnidadAdministrativa(CobradorUnidadAdministrativa bean) {
		return (CobradorUnidadAdministrativa) this.getSqlMapClientTemplate().queryForObject(
				"spusicc.cobranzas.mantenimientoCOBSQL.getCobradorUnidadAdministrativa",bean);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#insertCobradorUnidadAdministrativa(biz.belcorp.ssicc.spusicc.cobranzas.model.CobradorUnidadAdministrativa, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertCobradorUnidadAdministrativa(CobradorUnidadAdministrativa bean,
			Usuario usuario) {
		this.getSqlMapClientTemplate().insert(
				"spusicc.cobranzas.mantenimientoCOBSQL.insertCobradorUnidadAdministrativa", bean);		
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#updateCobradorUnidadAdministrativa(biz.belcorp.ssicc.spusicc.cobranzas.model.CobradorUnidadAdministrativa, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateCobradorUnidadAdministrativa(Map params) {
		this.getSqlMapClientTemplate().update(
				"spusicc.cobranzas.mantenimientoCOBSQL.updateCobradorUnidadAdministrativa", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#deleteCobradorUnidadAdministrativa(biz.belcorp.ssicc.spusicc.cobranzas.model.CobradorUnidadAdministrativa, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteCobradorUnidadAdministrativa(CobradorUnidadAdministrativa bean, Usuario usuario) {
		this.getSqlMapClientTemplate().update(
				"spusicc.cobranzas.mantenimientoCOBSQL.deleteCobradorUnidadAdministrativa", bean);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#getListaZonaNoCritica(biz.belcorp.ssicc.spusicc.cobranzas.model.ZonaNoCritica)
	 */
	public List getListaZonaNoCritica(ZonaNoCritica bean) {
		return this.getSqlMapClientTemplate().queryForList(
				"spusicc.cobranzas.mantenimientoCOBSQL.getZonaNoCriticaList",bean);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#getZonaNoCritica(biz.belcorp.ssicc.spusicc.cobranzas.model.ZonaNoCritica)
	 */
	public ZonaNoCritica getZonaNoCritica(ZonaNoCritica bean) {
		return (ZonaNoCritica) this.getSqlMapClientTemplate().queryForObject(
				"spusicc.cobranzas.mantenimientoCOBSQL.getZonaNoCritica",bean);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#insertZonaNoCritica(biz.belcorp.ssicc.spusicc.cobranzas.model.ZonaNoCritica, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertZonaNoCritica(ZonaNoCritica bean,
			Usuario usuario) {
		this.getSqlMapClientTemplate().insert(
				"spusicc.cobranzas.mantenimientoCOBSQL.insertZonaNoCritica", bean);		
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#updateZonaNoCritica(biz.belcorp.ssicc.spusicc.cobranzas.model.ZonaNoCritica, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateZonaNoCritica(ZonaNoCritica bean, Usuario usuario) {
		this.getSqlMapClientTemplate().update(
				"spusicc.cobranzas.mantenimientoCOBSQL.updateZonaNoCritica", bean);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#deleteZonaNoCritica(biz.belcorp.ssicc.spusicc.cobranzas.model.ZonaNoCritica, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteZonaNoCritica(ZonaNoCritica bean, Usuario usuario) {
		this.getSqlMapClientTemplate().update(
				"spusicc.cobranzas.mantenimientoCOBSQL.deleteZonaNoCritica", bean);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#getListaAsignacionCarteraCobrador(biz.belcorp.ssicc.spusicc.cobranzas.model.AsignacionCarteraCobrador)
	 */
	public List getListaAsignacionCarteraCobrador(AsignacionCarteraCobrador bean) {
		return this.getSqlMapClientTemplate().queryForList(
				"spusicc.cobranzas.mantenimientoCOBSQL.getAsignacionCarteraCobradorList",bean);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#getAsignacionCarteraCobrador(biz.belcorp.ssicc.spusicc.cobranzas.model.AsignacionCarteraCobrador)
	 */
	public AsignacionCarteraCobrador getAsignacionCarteraCobrador(AsignacionCarteraCobrador bean) {
		return (AsignacionCarteraCobrador) this.getSqlMapClientTemplate().queryForObject(
				"spusicc.cobranzas.mantenimientoCOBSQL.getAsignacionCarteraCobrador",bean);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#insertAsignacionCarteraCobrador(biz.belcorp.ssicc.spusicc.cobranzas.model.AsignacionCarteraCobrador, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertAsignacionCarteraCobrador(AsignacionCarteraCobrador bean,
			Usuario usuario) {
		this.getSqlMapClientTemplate().insert(
				"spusicc.cobranzas.mantenimientoCOBSQL.insertAsignacionCarteraCobrador", bean);		
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#updateAsignacionCarteraCobrador(biz.belcorp.ssicc.spusicc.cobranzas.model.AsignacionCarteraCobrador, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateAsignacionCarteraCobrador(Map params) {
		this.getSqlMapClientTemplate().update(
				"spusicc.cobranzas.mantenimientoCOBSQL.updateAsignacionCarteraCobrador", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#deleteAsignacionCarteraCobrador(biz.belcorp.ssicc.spusicc.cobranzas.model.AsignacionCarteraCobrador, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteAsignacionCarteraCobrador(AsignacionCarteraCobrador bean, Usuario usuario) {
		this.getSqlMapClientTemplate().update(
				"spusicc.cobranzas.mantenimientoCOBSQL.deleteAsignacionCarteraCobrador", bean);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#getListaValidacionAsignacionCartera(biz.belcorp.ssicc.spusicc.cobranzas.model.ValidacionAsignacionCartera)
	 */
	public List getListaValidacionAsignacionCartera(ValidacionAsignacionCartera bean) {
		return this.getSqlMapClientTemplate().queryForList(
				"spusicc.cobranzas.mantenimientoCOBSQL.getValidacionAsignacionCarteraList",bean);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#getValidacionAsignacionCartera(biz.belcorp.ssicc.spusicc.cobranzas.model.ValidacionAsignacionCartera)
	 */
	public ValidacionAsignacionCartera getValidacionAsignacionCartera(ValidacionAsignacionCartera bean) {
		return (ValidacionAsignacionCartera) this.getSqlMapClientTemplate().queryForObject(
				"spusicc.cobranzas.mantenimientoCOBSQL.getValidacionAsignacionCartera",bean);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#insertValidacionAsignacionCartera(biz.belcorp.ssicc.spusicc.cobranzas.model.ValidacionAsignacionCartera, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertValidacionAsignacionCartera(ValidacionAsignacionCartera bean,
			Usuario usuario) {
		this.getSqlMapClientTemplate().insert(
				"spusicc.cobranzas.mantenimientoCOBSQL.insertValidacionAsignacionCartera", bean);		
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#updateValidacionAsignacionCartera(biz.belcorp.ssicc.spusicc.cobranzas.model.ValidacionAsignacionCartera, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateValidacionAsignacionCartera(ValidacionAsignacionCartera bean, Usuario usuario) {
		this.getSqlMapClientTemplate().update(
				"spusicc.cobranzas.mantenimientoCOBSQL.updateValidacionAsignacionCartera", bean);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#deleteValidacionAsignacionCartera(biz.belcorp.ssicc.spusicc.cobranzas.model.ValidacionAsignacionCartera, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteValidacionAsignacionCartera(ValidacionAsignacionCartera bean, Usuario usuario) {
		this.getSqlMapClientTemplate().update(
				"spusicc.cobranzas.mantenimientoCOBSQL.deleteValidacionAsignacionCartera", bean);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#getListaExcepcionAsignacionCartera(biz.belcorp.ssicc.spusicc.cobranzas.model.ExcepcionAsignacionCartera)
	 */
	public List getListaExcepcionAsignacionCartera(ExcepcionAsignacionCartera bean) {
		return this.getSqlMapClientTemplate().queryForList(
				"spusicc.cobranzas.mantenimientoCOBSQL.getExcepcionAsignacionCarteraList",bean);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#getExcepcionAsignacionCartera(biz.belcorp.ssicc.spusicc.cobranzas.model.ExcepcionAsignacionCartera)
	 */
	public ExcepcionAsignacionCartera getExcepcionAsignacionCartera(ExcepcionAsignacionCartera bean) {
		return (ExcepcionAsignacionCartera) this.getSqlMapClientTemplate().queryForObject(
				"spusicc.cobranzas.mantenimientoCOBSQL.getExcepcionAsignacionCartera",bean);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#insertExcepcionAsignacionCartera(biz.belcorp.ssicc.spusicc.cobranzas.model.ExcepcionAsignacionCartera, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertExcepcionAsignacionCartera(ExcepcionAsignacionCartera bean,
			Usuario usuario) {
		this.getSqlMapClientTemplate().insert(
				"spusicc.cobranzas.mantenimientoCOBSQL.insertExcepcionAsignacionCartera", bean);		
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#updateExcepcionAsignacionCartera(biz.belcorp.ssicc.spusicc.cobranzas.model.ExcepcionAsignacionCartera, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateExcepcionAsignacionCartera(ExcepcionAsignacionCartera bean, Usuario usuario) {
		this.getSqlMapClientTemplate().update(
				"spusicc.cobranzas.mantenimientoCOBSQL.updateExcepcionAsignacionCartera", bean);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#deleteExcepcionAsignacionCartera(biz.belcorp.ssicc.spusicc.cobranzas.model.ExcepcionAsignacionCartera, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteExcepcionAsignacionCartera(ExcepcionAsignacionCartera bean, Usuario usuario) {
		this.getSqlMapClientTemplate().update(
				"spusicc.cobranzas.mantenimientoCOBSQL.deleteExcepcionAsignacionCartera", bean);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#getListaCarteraAsignada(java.util.Map)
	 */
	public List getListaCarteraAsignada(Map datos) {
		return this.getSqlMapClientTemplate().queryForList(
				"spusicc.cobranzas.mantenimientoCOBSQL.getCarteraAsignadaList",datos);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#executeDepurarCarteraAsignada(java.util.Map)
	 */
	public void executeDepurarCarteraAsignada(Map datos) {
		this.getSqlMapClientTemplate().update(
				"spusicc.cobranzas.mantenimientoCOBSQL.executeDepurarCarteraAsignada", datos);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#executeEliminarCarteraAsignada(java.util.Map)
	 */
	public void executeEliminarCarteraAsignada(Map datos) {
		this.getSqlMapClientTemplate().update(
				"spusicc.cobranzas.mantenimientoCOBSQL.executeEliminarCarteraAsignada", datos);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#getListaExcepcionClasificacionCliente(biz.belcorp.ssicc.spusicc.cobranzas.model.ExcepcionClasificacionCliente)
	 */
	public List getListaExcepcionClasificacionCliente(ExcepcionClasificacionCliente bean) {
		return this.getSqlMapClientTemplate().queryForList(
				"spusicc.cobranzas.mantenimientoCOBSQL.getExcepcionClasificacionClienteList",bean);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#getExcepcionClasificacionCliente(biz.belcorp.ssicc.spusicc.cobranzas.model.ExcepcionClasificacionCliente)
	 */
	public ExcepcionClasificacionCliente getExcepcionClasificacionCliente(ExcepcionClasificacionCliente bean) {
		return (ExcepcionClasificacionCliente) this.getSqlMapClientTemplate().queryForObject(
				"spusicc.cobranzas.mantenimientoCOBSQL.getExcepcionClasificacionCliente",bean);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#insertExcepcionClasificacionCliente(biz.belcorp.ssicc.spusicc.cobranzas.model.ExcepcionClasificacionCliente, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertExcepcionClasificacionCliente(ExcepcionClasificacionCliente bean,
			Usuario usuario) {
		this.getSqlMapClientTemplate().insert(
				"spusicc.cobranzas.mantenimientoCOBSQL.insertExcepcionClasificacionCliente", bean);		
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#updateExcepcionClasificacionCliente(java.util.Map)
	 */
	public void updateExcepcionClasificacionCliente(Map params) {
		this.getSqlMapClientTemplate().update(
				"spusicc.cobranzas.mantenimientoCOBSQL.updateExcepcionClasificacionCliente", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#deleteExcepcionClasificacionCliente(biz.belcorp.ssicc.spusicc.cobranzas.model.ExcepcionClasificacionCliente, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteExcepcionClasificacionCliente(ExcepcionClasificacionCliente bean, Usuario usuario) {
		this.getSqlMapClientTemplate().update(
				"spusicc.cobranzas.mantenimientoCOBSQL.deleteExcepcionClasificacionCliente", bean);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#getListaUnidadAdministrativaCronogramaCartera(biz.belcorp.ssicc.spusicc.cobranzas.model.UnidadAdministrativaCronogramaCartera)
	 */
	public List getListaUnidadAdministrativaCronogramaCartera(UnidadAdministrativaCronogramaCartera bean) {
		return this.getSqlMapClientTemplate().queryForList(
				"spusicc.cobranzas.mantenimientoCOBSQL.getUnidadAdministrativaCronogramaCarteraList",bean);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#getUnidadAdministrativaCronogramaCartera(biz.belcorp.ssicc.spusicc.cobranzas.model.UnidadAdministrativaCronogramaCartera)
	 */
	public UnidadAdministrativaCronogramaCartera getUnidadAdministrativaCronogramaCartera(UnidadAdministrativaCronogramaCartera bean) {
		return (UnidadAdministrativaCronogramaCartera) this.getSqlMapClientTemplate().queryForObject(
				"spusicc.cobranzas.mantenimientoCOBSQL.getUnidadAdministrativaCronogramaCartera",bean);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#insertUnidadAdministrativaCronogramaCartera(biz.belcorp.ssicc.spusicc.cobranzas.model.UnidadAdministrativaCronogramaCartera, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertUnidadAdministrativaCronogramaCartera(UnidadAdministrativaCronogramaCartera bean,
			Usuario usuario) {
		this.getSqlMapClientTemplate().insert(
				"spusicc.cobranzas.mantenimientoCOBSQL.insertUnidadAdministrativaCronogramaCartera", bean);		
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#updateUnidadAdministrativaCronogramaCartera(biz.belcorp.ssicc.spusicc.cobranzas.model.UnidadAdministrativaCronogramaCartera, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateUnidadAdministrativaCronogramaCartera(UnidadAdministrativaCronogramaCartera bean, Usuario usuario) {
		this.getSqlMapClientTemplate().update(
				"spusicc.cobranzas.mantenimientoCOBSQL.updateUnidadAdministrativaCronogramaCartera", bean);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#deleteUnidadAdministrativaCronogramaCartera(biz.belcorp.ssicc.spusicc.cobranzas.model.UnidadAdministrativaCronogramaCartera, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteUnidadAdministrativaCronogramaCartera(UnidadAdministrativaCronogramaCartera bean, Usuario usuario) {
		this.getSqlMapClientTemplate().update(
				"spusicc.cobranzas.mantenimientoCOBSQL.deleteUnidadAdministrativaCronogramaCartera", bean);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#getListaAsignacionCarteraSupervisor(biz.belcorp.ssicc.spusicc.cobranzas.model.AsignacionCarteraSupervisor)
	 */
	public List getListaAsignacionCarteraSupervisor(AsignacionCarteraSupervisor bean) {
		return this.getSqlMapClientTemplate().queryForList(
				"spusicc.cobranzas.mantenimientoCOBSQL.getAsignacionCarteraSupervisorList",bean);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#getAsignacionCarteraSupervisor(biz.belcorp.ssicc.spusicc.cobranzas.model.AsignacionCarteraSupervisor)
	 */
	public AsignacionCarteraSupervisor getAsignacionCarteraSupervisor(AsignacionCarteraSupervisor bean) {
		return (AsignacionCarteraSupervisor) this.getSqlMapClientTemplate().queryForObject(
				"spusicc.cobranzas.mantenimientoCOBSQL.getAsignacionCarteraSupervisor",bean);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#insertAsignacionCarteraSupervisor(biz.belcorp.ssicc.spusicc.cobranzas.model.AsignacionCarteraSupervisor, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertAsignacionCarteraSupervisor(AsignacionCarteraSupervisor bean,
			Usuario usuario) {
		this.getSqlMapClientTemplate().insert(
				"spusicc.cobranzas.mantenimientoCOBSQL.insertAsignacionCarteraSupervisor", bean);		
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#updateAsignacionCarteraSupervisor(biz.belcorp.ssicc.spusicc.cobranzas.model.AsignacionCarteraSupervisor, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateAsignacionCarteraSupervisor(Map params) {
		this.getSqlMapClientTemplate().update(
				"spusicc.cobranzas.mantenimientoCOBSQL.updateAsignacionCarteraSupervisor", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBGenericoDAO#deleteAsignacionCarteraSupervisor(biz.belcorp.ssicc.spusicc.cobranzas.model.AsignacionCarteraSupervisor, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteAsignacionCarteraSupervisor(AsignacionCarteraSupervisor bean, Usuario usuario) {
		this.getSqlMapClientTemplate().update(
				"spusicc.cobranzas.mantenimientoCOBSQL.deleteAsignacionCarteraSupervisor", bean);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.cobranzas.MantenimientoCOBGenericoDAO#getParametrosGeneralesCobList(biz.belcorp.ssicc.dao.spusicc.cobranzas.model.ParametrosGeneralesCOB)
	 */
	public List getParametrosGeneralesCobList(ParametrosGeneralesCOB bean) {
		return this.getSqlMapClientTemplate().queryForList(
				"spusicc.cobranzas.mantenimientoCOBSQL.getParametrosGeneralesCobList",bean);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.cobranzas.MantenimientoCOBGenericoDAO#getParametrosGeneralesCob(biz.belcorp.ssicc.dao.spusicc.cobranzas.model.ParametrosGeneralesCOB)
	 */
	public ParametrosGeneralesCOB getParametrosGeneralesCob(ParametrosGeneralesCOB bean) {
		return (ParametrosGeneralesCOB) this.getSqlMapClientTemplate().queryForObject(
				"spusicc.cobranzas.mantenimientoCOBSQL.getParametrosGeneralesCob",bean);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.cobranzas.MantenimientoCOBGenericoDAO#insertParametrosGeneralesCob(biz.belcorp.ssicc.dao.spusicc.cobranzas.model.ParametrosGeneralesCOB, biz.belcorp.ssicc.dao.model.Usuario)
	 */
	public void insertParametrosGeneralesCob(ParametrosGeneralesCOB bean, Usuario usuario) {
		this.getSqlMapClientTemplate().insert(
				"spusicc.cobranzas.mantenimientoCOBSQL.insertParametrosGeneralesCob", bean);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.cobranzas.MantenimientoCOBGenericoDAO#updateParametrosGeneralesCob(biz.belcorp.ssicc.dao.spusicc.cobranzas.model.ParametrosGeneralesCOB, biz.belcorp.ssicc.dao.model.Usuario)
	 */
	public void updateParametrosGeneralesCob(ParametrosGeneralesCOB bean, Usuario usuario) {
		this.getSqlMapClientTemplate().update(
				"spusicc.cobranzas.mantenimientoCOBSQL.updateParametrosGeneralesCob", bean);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.cobranzas.MantenimientoCOBGenericoDAO#deleteParametrosGeneralesCob(biz.belcorp.ssicc.dao.spusicc.cobranzas.model.ParametrosGeneralesCOB, biz.belcorp.ssicc.dao.model.Usuario)
	 */
	public void deleteParametrosGeneralesCob(ParametrosGeneralesCOB bean, Usuario usuario) {
		this.getSqlMapClientTemplate().update(
				"spusicc.cobranzas.mantenimientoCOBSQL.deleteParametrosGeneralesCob", bean);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.cobranzas.MantenimientoCOBGenericoDAO#getSeccionNoCriticaList(biz.belcorp.ssicc.dao.spusicc.cobranzas.model.SeccionNoCritica)
	 */
	@Override
	public List getSeccionNoCriticaList(SeccionNoCritica bean) {
		return this.getSqlMapClientTemplate().queryForList(
				"spusicc.cobranzas.mantenimientoCOBSQL.getSeccionNoCriticaList",bean);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.cobranzas.MantenimientoCOBGenericoDAO#getSeccionNoCritica(biz.belcorp.ssicc.dao.spusicc.cobranzas.model.SeccionNoCritica)
	 */
	@Override
	public SeccionNoCritica getSeccionNoCritica(SeccionNoCritica bean) {
		return (SeccionNoCritica) this.getSqlMapClientTemplate().queryForObject(
				"spusicc.cobranzas.mantenimientoCOBSQL.getSeccionNoCritica",bean);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.cobranzas.MantenimientoCOBGenericoDAO#insertSeccionNoCritica(biz.belcorp.ssicc.dao.spusicc.cobranzas.model.SeccionNoCritica, biz.belcorp.ssicc.dao.model.Usuario)
	 */
	@Override
	public void insertSeccionNoCritica(SeccionNoCritica bean, Usuario usuario) {
		this.getSqlMapClientTemplate().insert(
				"spusicc.cobranzas.mantenimientoCOBSQL.insertSeccionNoCritica", bean);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.cobranzas.MantenimientoCOBGenericoDAO#updateSeccionNoCritica(biz.belcorp.ssicc.dao.spusicc.cobranzas.model.SeccionNoCritica, biz.belcorp.ssicc.dao.model.Usuario)
	 */
	@Override
	public void updateSeccionNoCritica(SeccionNoCritica bean, Usuario usuario) {
		this.getSqlMapClientTemplate().update(
				"spusicc.cobranzas.mantenimientoCOBSQL.updateSeccionNoCritica", bean);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.cobranzas.MantenimientoCOBGenericoDAO#deleteSeccionNoCritica(biz.belcorp.ssicc.dao.spusicc.cobranzas.model.SeccionNoCritica, biz.belcorp.ssicc.dao.model.Usuario)
	 */
	@Override
	public void deleteSeccionNoCritica(SeccionNoCritica bean, Usuario usuario) {
		this.getSqlMapClientTemplate().update(
				"spusicc.cobranzas.mantenimientoCOBSQL.deleteSeccionNoCritica", bean);		
	}
	
	
	
}
