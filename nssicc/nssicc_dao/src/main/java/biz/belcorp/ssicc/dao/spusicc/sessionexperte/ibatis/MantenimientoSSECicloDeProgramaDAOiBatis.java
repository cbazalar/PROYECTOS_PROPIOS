package biz.belcorp.ssicc.dao.spusicc.sessionexperte.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.MantenimientoSSECicloDeProgramaDAO;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.model.CicloDePrograma;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoSSECicloDeProgramaSSEDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author peextrramirez
 * 
 */
@Repository("spusicc.sessionexperte.mantenimientoSSECicloDeProgramaDAO")
public class MantenimientoSSECicloDeProgramaDAOiBatis extends BaseDAOiBatis 
	implements MantenimientoSSECicloDeProgramaDAO {

	/**
	 * Obtiene un listado de los ciclos del Programa Session Experte indicado.
	 * @param ciclo Objeto de tipo Programa session experte.
	 * */
	public List getCiclosDeProgramaSessionExperteByCriteria(CicloDePrograma ciclo) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.sessionexperte.SessionExperteSQL.getCiclosDeProgramaSessionExperteByCriteria", ciclo);

	}
	
	/**
	 * Obtiene un listado de los tipo de ciclos
	 * */
	public List getTipoCiclosSessionExperte(){
		return getSqlMapClientTemplate().queryForList(
				"spusicc.sessionexperte.SessionExperteSQL.getTipoCiclosSessionExperte");
	}
	
	/**
	 * Inserta un Ciclo de Programa SessionExperte
	 * @param ciclo Ciclo de Programa SessionExperte.
	 * @param usuario Usuario que inserta.
	 */
	public void insertCicloDeProgramaSessionExperte(CicloDePrograma ciclo, Usuario usuario){
		getSqlMapClientTemplate().insert("spusicc.sessionexperte.SessionExperteSQL.insertCicloDeProgramaSessionExperte",ciclo);
	}
	
	/**
	 * Actualiza un Ciclo de Programa SessionExperte
	 * @param ciclo Ciclo de Programa SessionExperte.
	 * @param usuario Usuario que inserta.
	 */
	public void updateCicloDeProgramaSessionExperte(CicloDePrograma ciclo, Usuario usuario){
		getSqlMapClientTemplate().insert("spusicc.sessionexperte.SessionExperteSQL.updateCicloDeProgramaSessionExperte",ciclo);
	}
	
	
	/**
	 * Delete un Ciclo de Programa SessionExperte
	 * @param ciclo Ciclo de Programa SessionExperte.
	 * @param usuario Usuario que inserta.
	 */
	public void deleteCicloDeProgramaSessionExperte(CicloDePrograma ciclo, Usuario usuario){
		getSqlMapClientTemplate().update("spusicc.sessionexperte.SessionExperteSQL.deleteCicloDeProgramaSessionExperte",ciclo);
	}
	
	/**
	 * Consigue un Ciclo de Programa SessionExperte
	 * @param ciclo Ciclo de Programa SessionExperte.
	 */
	public CicloDePrograma getFirstFromCiclosDeProgramaSessionExperteByCriteria(CicloDePrograma ciclo){
		return (CicloDePrograma)getSqlMapClientTemplate().queryForList("spusicc.sessionexperte.SessionExperteSQL.getCiclosDeProgramaSessionExperteByCriteria",ciclo).get(0);
	}

	public String getCodigoCicloByAnhoTipoCiclo(CicloDePrograma ciclo){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.sessionexperte.SessionExperteSQL.getCodigoCicloByAnhoTipoCiclo",ciclo);
	}
	
	public List getCodigoCiclosByPaisProgAnho(CicloDePrograma ciclo){
		return getSqlMapClientTemplate().queryForList("spusicc.sessionexperte.SessionExperteSQL.getCodigoCiclosByPaisProgAnho", ciclo);
	}

}
