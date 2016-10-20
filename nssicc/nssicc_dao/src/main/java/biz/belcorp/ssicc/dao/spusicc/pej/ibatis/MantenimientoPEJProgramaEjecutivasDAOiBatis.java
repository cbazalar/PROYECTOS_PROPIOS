package biz.belcorp.ssicc.dao.spusicc.pej.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pej.MantenimientoPEJProgramaEjecutivasDAO;
import biz.belcorp.ssicc.dao.spusicc.pej.model.CUP;
import biz.belcorp.ssicc.dao.spusicc.pej.model.DiferenciaPedidos;
import biz.belcorp.ssicc.dao.spusicc.pej.model.Etapa;
import biz.belcorp.ssicc.dao.spusicc.pej.model.Fase;
import biz.belcorp.ssicc.dao.spusicc.pej.model.Grupo;
import biz.belcorp.ssicc.dao.spusicc.pej.model.Nivel;
import biz.belcorp.ssicc.dao.spusicc.pej.model.Porcentaje;
import biz.belcorp.ssicc.dao.spusicc.pej.model.ProgramaEjecutiva;
import biz.belcorp.ssicc.dao.spusicc.pej.model.Rango;
import biz.belcorp.ssicc.dao.spusicc.pej.model.TipoAbono;
import biz.belcorp.ssicc.dao.spusicc.pej.model.TipoPremio;

/**
 * @author Sebastian Guerra
 *
 */
@Repository("spusicc.mantenimientoPEJProgramaEjecutivasDAO")
public class MantenimientoPEJProgramaEjecutivasDAOiBatis extends BaseDAOiBatis implements MantenimientoPEJProgramaEjecutivasDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJDAO#getProgramasByCriteria(java.util.Map)
	 */
	public List getProgramasByCriteria(Map criteria) {
		List programas =this.getSqlMapClientTemplate().queryForList("spusicc.ProcesosPEJSQL.getProgramasByCriteria", criteria);
		return programas;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJProgramaEjecutivasDAO#getNextCodigoPrograma()
	 */
	public String getNextCodigoPrograma() {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ProcesosPEJSQL.getNextCodigoPrograma", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJProgramaEjecutivasDAO#insertProgramaEjecutiva(biz.belcorp.ssicc.spusicc.pej.model.ProgramaEjecutiva, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertProgramaEjecutiva(ProgramaEjecutiva programaEjecutiva, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.ProcesosPEJSQL.insertProgramaEjecutiva", programaEjecutiva);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJProgramaEjecutivasDAO#insertFase(biz.belcorp.ssicc.spusicc.pej.model.Fase, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertFase(Fase fase, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.ProcesosPEJSQL.insertFase", fase);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJProgramaEjecutivasDAO#insertNivel(biz.belcorp.ssicc.spusicc.pej.dao.Nivel, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertNivel(Nivel nivel, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.ProcesosPEJSQL.insertNivel", nivel);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJProgramaEjecutivasDAO#insertRango(biz.belcorp.ssicc.spusicc.pej.model.Rango, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertRango(Rango rango, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.ProcesosPEJSQL.insertRango", rango);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJProgramaEjecutivasDAO#insertGrupo(biz.belcorp.ssicc.spusicc.pej.dao.Grupo, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertGrupo(Grupo grupo, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.ProcesosPEJSQL.insertGrupo", grupo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJProgramaEjecutivasDAO#insertPorcentaje(biz.belcorp.ssicc.spusicc.pej.model.Porcentaje, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertPorcentaje(Porcentaje porcentaje, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.ProcesosPEJSQL.insertPorcentaje", porcentaje);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJProgramaEjecutivasDAO#insertTipoAbono(biz.belcorp.ssicc.spusicc.pej.model.TipoAbono, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertTipoAbono(TipoAbono tipoAbono, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.ProcesosPEJSQL.insertTipoAbono", tipoAbono);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJProgramaEjecutivasDAO#getFasePEJ(java.util.Map)
	 */
	public List getFasePEJ(Map parametros) {
		List result = this.getSqlMapClientTemplate().queryForList("spusicc.ProcesosPEJSQL.getFasePEJ", parametros);
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJProgramaEjecutivasDAO#getNivelPEJ(java.util.Map)
	 */
	public List getNivelPEJ(Map parametros) {
		List result = this.getSqlMapClientTemplate().queryForList("spusicc.ProcesosPEJSQL.getNivelPEJ", parametros);
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJProgramaEjecutivasDAO#getRangoPEJ(java.util.Map)
	 */
	public List getRangoPEJ(Map parametros) {
		List result = this.getSqlMapClientTemplate().queryForList("spusicc.ProcesosPEJSQL.getRangoPEJ", parametros);
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJProgramaEjecutivasDAO#getGrupoPEJ(java.util.Map)
	 */
	public List getGrupoPEJ(Map parametros) {
		List result = this.getSqlMapClientTemplate().queryForList("spusicc.ProcesosPEJSQL.getGrupoPEJ", parametros);
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJProgramaEjecutivasDAO#getPorcentajePEJ(java.util.Map)
	 */
	public List getPorcentajePEJ(Map parametros) {
		List result = this.getSqlMapClientTemplate().queryForList("spusicc.ProcesosPEJSQL.getPorcentajePEJ", parametros);
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJProgramaEjecutivasDAO#getTipoAbonoPEJ(java.util.Map)
	 */
	public List getTipoAbonoPEJ(Map parametros) {
		List result = this.getSqlMapClientTemplate().queryForList("spusicc.ProcesosPEJSQL.getTipoAbonoPEJ", parametros);
		return result;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJProgramaEjecutivasDAO#updateProgramaEjecutiva(biz.belcorp.ssicc.spusicc.pej.model.ProgramaEjecutiva, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateProgramaEjecutiva(ProgramaEjecutiva programaEjecutiva, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.ProcesosPEJSQL.updateProgramaEjecutiva", programaEjecutiva);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJProgramaEjecutivasDAO#updateFase(biz.belcorp.ssicc.spusicc.pej.model.Fase, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateFase(Fase fase, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.ProcesosPEJSQL.updateFase", fase);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJProgramaEjecutivasDAO#updateNivel(biz.belcorp.ssicc.spusicc.pej.dao.Nivel, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateNivel(Nivel nivel, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.ProcesosPEJSQL.updateNivel", nivel);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJProgramaEjecutivasDAO#updateRango(biz.belcorp.ssicc.spusicc.pej.model.Rango, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateRango(Rango rango, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.ProcesosPEJSQL.updateRango", rango);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJProgramaEjecutivasDAO#updateGrupo(biz.belcorp.ssicc.spusicc.pej.dao.Grupo, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateGrupo(Grupo grupo, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.ProcesosPEJSQL.updateGrupo", grupo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJProgramaEjecutivasDAO#updatePorcentaje(biz.belcorp.ssicc.spusicc.pej.model.Porcentaje, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updatePorcentaje(Porcentaje porcentaje, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.ProcesosPEJSQL.updatePorcentaje", porcentaje);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJProgramaEjecutivasDAO#updateTipoAbono(biz.belcorp.ssicc.spusicc.pej.model.TipoAbono, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateTipoAbono(TipoAbono tipoAbono, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.ProcesosPEJSQL.updateTipoAbono", tipoAbono);
	}
	
	public String getExisteLiquidacionCampanya(Map parametros) {
		return  (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosPEJSQL.getExisteLiquidacionCampanya", parametros);
	}
	
	public void deleteProgramaEjecutiva(Map parametros) throws Exception {
		getSqlMapClientTemplate().update("spusicc.ProcesosPEJSQL.deleteProgramaEjecutiva", parametros);

		getSqlMapClientTemplate().update("spusicc.ProcesosPEJSQL.deleteFase", parametros);
		getSqlMapClientTemplate().update("spusicc.ProcesosPEJSQL.deleteNivel", parametros);
		getSqlMapClientTemplate().update("spusicc.ProcesosPEJSQL.deleteRango", parametros);
		getSqlMapClientTemplate().update("spusicc.ProcesosPEJSQL.deleteGrupo", parametros);
		getSqlMapClientTemplate().update("spusicc.ProcesosPEJSQL.deletePorcentaje", parametros);
		getSqlMapClientTemplate().update("spusicc.ProcesosPEJSQL.deleteTipoAbono", parametros);
		
		getSqlMapClientTemplate().update("spusicc.ProcesosPEJSQL.deleteClienteExcluido", parametros);
		getSqlMapClientTemplate().update("spusicc.ProcesosPEJSQL.deleteMetasSeccion", parametros);
		getSqlMapClientTemplate().update("spusicc.ProcesosPEJSQL.deleteNivelCampanya", parametros);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJProgramaEjecutivasDAO#getPeriodoDefault()
	 */
	public Map getPeriodoDefault() {
		return (Map) getSqlMapClientTemplate().queryForObject("spusicc.ProcesosPEJSQL.getPeriodoDefault", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJProgramaEjecutivasDAO#getExisteDependenciaFase(java.util.Map)
	 */
	public String getExisteDependenciaFase(Map parametros) {
		return  (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosPEJSQL.getExisteDependenciaFase", parametros);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJProgramaEjecutivasDAO#getExisteDependenciaNivel(java.util.Map)
	 */
	public String getExisteDependenciaNivel(Map parametros) {
		return  (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosPEJSQL.getExisteDependenciaNivel", parametros);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJProgramaEjecutivasDAO#getExisteDependenciaRango(java.util.Map)
	 */
	public String getExisteDependenciaRango(Map parametros) {
		return  (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosPEJSQL.getExisteDependenciaRango", parametros);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJProgramaEjecutivasDAO#getExisteDependenciaGrupo(java.util.Map)
	 */
	public String getExisteDependenciaGrupo(Map parametros) {
		return  (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosPEJSQL.getExisteDependenciaGrupo", parametros);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJProgramaEjecutivasDAO#getExisteDependenciaTipoAbono(java.util.Map)
	 */
	public String getExisteDependenciaTipoAbono(Map parametros) {
		return  (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosPEJSQL.getExisteDependenciaTipoAbono", parametros);
	}
	
}