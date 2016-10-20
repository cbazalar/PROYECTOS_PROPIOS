package biz.belcorp.ssicc.dao.spusicc.app.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.app.ProcesoAPPCargarHomolYobelDAO;

/**
 * <p>
 * <a href="ProcesoAPPCargarHomolYobelDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:doliva@belcorp.biz">Dennys Oliva Iriarte</a>
 */
@Repository("spusicc.procesoAPPCargarHomolYobelDAO")
public class ProcesoAPPCargarHomolYobelDAOiBatis extends BaseDAOiBatis implements ProcesoAPPCargarHomolYobelDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.dao.ProcesoAPPCargarHomolYobelDAO#insertaHomologadoYobel(java.util.Map)
	 */
	public void insertaHomologadoYobel(Map criteria) {		
		getSqlMapClientTemplate().update("spusicc.app.ProcesosAPPSQL.insertaHomologadoYobel",criteria);
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.dao.ProcesoAPPCargarHomolYobelDAO#insertaTemporalHomologadoYobel(java.util.Map)
	 */
	public void insertaTemporalHomologadoYobel(Map params) {
		getSqlMapClientTemplate().update("spusicc.app.ProcesosAPPSQL.insertaTemporalHomologadoYobel", params);
	}
	
	public void deleteTemporalHomologadoYobel() {
		getSqlMapClientTemplate().delete("spusicc.app.ProcesosAPPSQL.deleteTemporalHomologadoYobel", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.dao.ProcesoAPPCargarHomolYobelDAO#getSecuenciasZonasList()
	 */
	public List getSecuenciasZonasList(){
		return getSqlMapClientTemplate().queryForList("spusicc.app.ProcesosAPPSQL.getSecuenciasZonasList", null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.dao.ProcesoAPPCargarHomolYobelDAO#getSecuenciasZonasTempoHomologadasList()
	 */
	public List getSecuenciasZonasTempoHomologadasList(){
		return getSqlMapClientTemplate().queryForList("spusicc.app.ProcesosAPPSQL.getSecuenciasZonasTempoHomologadasList", null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.dao.ProcesoAPPCargarHomolYobelDAO#getSecuenciasZonasHomologadasList()
	 */
	public List getSecuenciasZonasHomologadasList(){
		return getSqlMapClientTemplate().queryForList("spusicc.app.ProcesosAPPSQL.getSecuenciasZonasHomologadasList", null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.dao.ProcesoAPPCargarHomolYobelDAO#executeSecuenciarZonasTerritorios()
	 */
	public void executeSecuenciarZonasTerritorios(){
		getSqlMapClientTemplate().update("spusicc.app.ProcesosAPPSQL.executeSecuenciarZonasTerritorios", null);
	}
	
	public void deleteTablaHomologacion(){
		getSqlMapClientTemplate().delete("spusicc.app.ProcesosAPPSQL.deleteTablaHomologacion",null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.dao.ProcesoAPPCargarHomolYobelDAO#executeSecuenciarZonasTerritorios(java.util.Map)
	 */
	public void executeSecuenciarZonasTerritorios(Map criteria){
		getSqlMapClientTemplate().update("spusicc.app.ProcesosAPPSQL.executeSecuenciarZonasTerritorios2", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.dao.ProcesoAPPCargarHomolYobelDAO#validarCargaHomologacion(java.util.Map)
	 */
	public void validarCargaHomologacion(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.app.ProcesosAPPSQL.validarCargaHomologacion", criteria);
	}

}
