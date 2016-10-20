package biz.belcorp.ssicc.dao.spusicc.let.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.let.MantenimientoLETProgramaCorporativoDAO;
import biz.belcorp.ssicc.dao.spusicc.let.model.Premios;
import biz.belcorp.ssicc.dao.spusicc.let.model.ProgramaCorporativo;
import biz.belcorp.ssicc.dao.spusicc.let.model.RangoNivel;
import biz.belcorp.ssicc.dao.spusicc.let.model.RangoRetencion;
import biz.belcorp.ssicc.dao.spusicc.let.model.Tramos;

/**
 * <p>
 * <a href="MantenimientoLETProgramaCorporativoDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="aoviedo@sigcomt.com">Aurelio Oviedo</a>
 *         
 */
@Repository("spusicc.mantenimientoLETProgramaCorporativoDAO")
public class MantenimientoLETProgramaCorporativoDAOIbatis extends BaseDAOiBatis implements MantenimientoLETProgramaCorporativoDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETProgramaCorporativoDAO#getProgramaCorporativoList(java.util.Map)
	 */
	public List getProgramaCorporativoList(Map criteria) {
		List lista = getSqlMapClientTemplate().queryForList("spusicc.let.MantenimientoLETSQL.getProgramaCorporativoList", criteria);
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETProgramaCorporativoDAO#getNextCodigoProgramaCorporativo()
	 */
	public String getNextCodigoProgramaCorporativo() {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getNextCodigoProgramaCorporativo", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETProgramaCorporativoDAO#getTipoNivelExito()
	 */
	public List getTipoNivelExito() {
		return getSqlMapClientTemplate().queryForList("spusicc.let.MantenimientoLETSQL.getTipoNivelExito", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETProgramaCorporativoDAO#insertProgramaCorporativo(java.util.Map)
	 */
	public void insertProgramaCorporativo(ProgramaCorporativo programaCorporativo, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.let.MantenimientoLETSQL.insertProgramaCorporativo", programaCorporativo);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETProgramaCorporativoDAO#insertRangoNivel(biz.belcorp.ssicc.spusicc.let.model.RangoNivel, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertRangoNivel(RangoNivel rangoNivel, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.let.MantenimientoLETSQL.insertRangoNivel", rangoNivel);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETProgramaCorporativoDAO#insertRangoRetencion(biz.belcorp.ssicc.spusicc.let.model.RangoRetencion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertRangoRetencion(RangoRetencion rangoRetencion, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.let.MantenimientoLETSQL.insertRangoRetencion", rangoRetencion);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETProgramaCorporativoDAO#insertTramos(biz.belcorp.ssicc.spusicc.let.model.Tramos, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertTramos(Tramos tramos, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.let.MantenimientoLETSQL.insertTramos", tramos);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETProgramaCorporativoDAO#insertPremios(biz.belcorp.ssicc.spusicc.let.model.Premios, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertPremios(Premios premios, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.let.MantenimientoLETSQL.insertPremios", premios);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETProgramaCorporativoDAO#updateProgramaCorporativo(java.util.Map)
	 */
	public void updateProgramaCorporativo(ProgramaCorporativo programaCorporativo, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.let.MantenimientoLETSQL.updateProgramaCorporativo", programaCorporativo);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETProgramaCorporativoDAO#updateRangoNivel(biz.belcorp.ssicc.spusicc.let.model.RangoNivel, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateRangoNivel(RangoNivel rangoNivel, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.let.MantenimientoLETSQL.updateRangoNivel", rangoNivel);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETProgramaCorporativoDAO#updateRangoRetencion(biz.belcorp.ssicc.spusicc.let.model.RangoRetencion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateRangoRetencion(RangoRetencion rangoRetencion, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.let.MantenimientoLETSQL.updateRangoRetencion", rangoRetencion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETProgramaCorporativoDAO#updateTramos(biz.belcorp.ssicc.spusicc.let.model.Tramos, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateTramos(Tramos tramos, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.let.MantenimientoLETSQL.updateTramos", tramos);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETProgramaCorporativoDAO#updatePremios(biz.belcorp.ssicc.spusicc.let.model.Premios, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updatePremios(Premios premios, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.let.MantenimientoLETSQL.updatePremios", premios);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETProgramaCorporativoDAO#deleteProgramaCorporativo(java.util.Map)
	 */
	public void deleteProgramaCorporativo(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.let.MantenimientoLETSQL.deleteRangoNivel", criteria);
		getSqlMapClientTemplate().delete("spusicc.let.MantenimientoLETSQL.deleteRangoRetencion", criteria);
		getSqlMapClientTemplate().delete("spusicc.let.MantenimientoLETSQL.deleteTramos", criteria);
		getSqlMapClientTemplate().delete("spusicc.let.MantenimientoLETSQL.deletePremios", criteria);
		
		getSqlMapClientTemplate().delete("spusicc.let.MantenimientoLETSQL.deleteProgramaCorporativo", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETProgramaCorporativoDAO#deleteRangoNivel(java.util.Map)
	 */
	public void deleteRangoNivel(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.let.MantenimientoLETSQL.deleteRangoNivel", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETProgramaCorporativoDAO#deleteRangoRetencion(java.util.Map)
	 */
	public void deleteRangoRetencion(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.let.MantenimientoLETSQL.deleteRangoRetencion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETProgramaCorporativoDAO#deleteTramos(java.util.Map)
	 */
	public void deleteTramos(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.let.MantenimientoLETSQL.deleteTramos", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETProgramaCorporativoDAO#deletePremios(java.util.Map)
	 */
	public void deletePremios(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.let.MantenimientoLETSQL.deletePremios", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETProgramaCorporativoDAO#getRangoNivelLET(java.util.Map)
	 */
	public List getRangoNivelLET(Map parametros) {
		List result = this.getSqlMapClientTemplate().queryForList("spusicc.let.MantenimientoLETSQL.getRangoNivelLET", parametros);
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETProgramaCorporativoDAO#getRangoRetencionLET(java.util.Map)
	 */
	public List getRangoRetencionLET(Map parametros) {
		List result = this.getSqlMapClientTemplate().queryForList("spusicc.let.MantenimientoLETSQL.getRangoRetencionLET", parametros);
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETProgramaCorporativoDAO#getTramosLET(java.util.Map)
	 */
	public List getTramosLET(Map parametros) {
		List result = this.getSqlMapClientTemplate().queryForList("spusicc.let.MantenimientoLETSQL.getTramosLET", parametros);
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETProgramaCorporativoDAO#getPremiosLET(java.util.Map)
	 */
	public List getPremiosLET(Map parametros) {
		List result = this.getSqlMapClientTemplate().queryForList("spusicc.let.MantenimientoLETSQL.getPremiosLET", parametros);
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETProgramaCorporativoDAO#getBuscarCUV(java.util.Map)
	 */
	public String getBuscarCUV(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getBuscarCUV", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETProgramaCorporativoDAO#getExisteObjetivosPedidosPorPrograma(java.util.Map)
	 */
	public String getExisteObjetivosPedidosPorPrograma(Map parametros) {
		return  (String)getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getExisteObjetivosPedidosPorPrograma", parametros);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETProgramaCorporativoDAO#getExisteProgramaCampanaFinNulo(java.util.Map)
	 */
	public String getExisteProgramaCampanaFinNulo(Map parametros) {
		return  (String)getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getExisteProgramaCampanaFinNulo", parametros);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETProgramaCorporativoDAO#getMaximaCampanaFinPrograma()
	 */
	public String getMaximaCampanaFinPrograma() {
		return  (String)getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getMaximaCampanaFinPrograma", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETProgramaCorporativoDAO#getPremiosPorCampanaList(java.util.Map)
	 */
	public List getPremiosPorCampanaList(Map criteria) {
		List lista = getSqlMapClientTemplate().queryForList("spusicc.let.MantenimientoLETSQL.getPremiosPorCampanaList", criteria);
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETProgramaCorporativoDAO#getNivelesCalculadosByCampanha(java.util.Map)
	 */
	public String getNivelesCalculadosByCampanha(Map criteria) {
		return  (String)getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getNivelesCalculadosByCampanha", criteria);
	}
}