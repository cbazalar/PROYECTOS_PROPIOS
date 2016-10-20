package biz.belcorp.ssicc.dao.spusicc.app.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.app.ProcesoAPPCargarSecuenciaZonaDAO;

/**
 * <p>
 * <a href="ProcesoAPPCargarSecuenciaZonaDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="">Jose Luis Rodriguez</a>
 */
@Repository("spusicc.procesoAPPCargarSecuenciaZonaDAO")
public class ProcesoAPPCargarSecuenciaZonaDAOiBatis extends BaseDAOiBatis implements ProcesoAPPCargarSecuenciaZonaDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.dao.ProcesoAPPCargarSecuenciaZonaDAO#insertaSecuenciaZona(java.util.Map)
	 */
	public void insertaSecuenciaZona(Map criteria) {		
		getSqlMapClientTemplate().insert("spusicc.app.ProcesosAPPSQL.insertaSecuenciaZona",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.dao.ProcesoAPPCargarSecuenciaZonaDAO#deleteTablaSecuenciaZona()
	 */
	public void deleteTablaSecuenciaZona(){
		getSqlMapClientTemplate().delete("spusicc.app.ProcesosAPPSQL.deleteTablaSecuenciaZona",null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.dao.ProcesoAPPCargarSecuenciaZonaDAO#executeCargaSecuenciaZona(java.util.Map)
	 */
	public void executeCargaSecuenciaZona(Map criteria){
		getSqlMapClientTemplate().delete("spusicc.app.ProcesosAPPSQL.executeCargaSecuenciaZona",criteria);
	}
}
