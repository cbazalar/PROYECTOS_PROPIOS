package biz.belcorp.ssicc.dao.spusicc.ape.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEUnidadesAdministrativasDAO;

/**
 * 
 * <p>
 * <a href="MantenimientoAPEUnidadesAdministrativasDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:nlopez@csigcomt.com">Nicolas Lopez</a>
 */
@Repository("spusicc.mantenimientoAPEUnidadesAdministrativasDAO")
public class MantenimientoAPEUnidadesAdministrativasDAOiBatis extends
		BaseDAOiBatis implements MantenimientoAPEUnidadesAdministrativasDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEUnidadesAdministrativasDAO#getCodigoCentroDistribucionList(java.util.Map)
	 */
	
	public List getCodigoCentroDistribucionList(Map criteria) {
		return this.getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getCentroDistList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEUnidadesAdministrativasDAO#getOidCentroDistribucionPais(java.util.Map)
	 */
	public String getOidCentroDistribucionPais( Map criteria){
		return (String) this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidCentroDistribucionPais", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEUnidadesAdministrativasDAO#getCodCDDefecto(java.util.Map)
	 */
	public String getCodigoCDDefecto(Map criteria){
		return (String) this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getCodCDDefecto", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEUnidadesAdministrativasDAO#getLineaArmadoListar(java.util.Map)
	 */
	public List getLineaArmadoListar(Map criteria) {
		return this.getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getLineaArmadoListar", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEUnidadesAdministrativasDAO#getCodLineaArmadaDefecto(java.util.Map)
	 */
	public String getCodLineaArmadaDefecto(Map criteria){
		return (String) this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getCodLineaArmadaDefecto", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEUnidadesAdministrativasDAO#insertUnidadAdministrativaLinea(java.util.Map)
	 */
	public void insertUnidadAdministrativaLinea(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.executeInsertaUnidadAdmLinea",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEUnidadesAdministrativasDAO#getOidLineaArmado(java.util.Map)
	 */
	public String getOidLineaArmado(Map criteria){
		return (String) this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidLineaArmado", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEUnidadesAdministrativasDAO#getValidaExisteUadmLineaAPE(java.util.Map)
	 */
	public String getValidaExisteUadmLineaAPE(Map criteria){
		return (String) this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getValidaExisteUadmLineaAPE", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEUnidadesAdministrativasDAO#getUnidadAdministrativaLineaList(java.util.Map)
	 */
	public List getUnidadAdministrativaLineaList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getUnidadAdmLineaList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEUnidadesAdministrativasDAO#deleteUnidadAdministrativa(java.util.Map)
	 */
	public void deleteUnidadAdministrativa(Map criteria) {
		this.getSqlMapClientTemplate().delete("spusicc.ape.MantenimientoAPESQL.deleteUnidadAdministrativa", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEUnidadesAdministrativasDAO#getOidNivelAgrupacionOlas(java.util.Map)
	 */
	public String getOidNivelAgrupacionOlas(Map criteria){
		return (String) this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidNivelAgrupacionOlas", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEUnidadesAdministrativasDAO#getObtenerNivelOlas(java.util.Map)
	 */
	public List getObtenerNivelOlas(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getObtenerNivelOlas", criteria);
	}

}
