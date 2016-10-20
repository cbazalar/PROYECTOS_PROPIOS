package biz.belcorp.ssicc.dao.spusicc.let.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETEntregaPremioCampaniaCierreCampaniaDAO;

/**
 * Clase de la implementacin de la capa DAO (Data Access Object)
 * 
 * <p>
 * <a href="ProcesoLETEntregaPremioCampaniaCierreCampaniaDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 *         
 */
@Repository("spusicc.procesoLETEntregaPremioCampaniaCierreCampaniaDAO")
public class ProcesoLETEntregaPremioCampaniaCierreCampaniaDAOIbatis extends BaseDAOiBatis implements ProcesoLETEntregaPremioCampaniaCierreCampaniaDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLETEntregaPremioCampaniaCierreCampaniaDAO#executeProcesoLETEntregaPremioCampaniaCierreCampania(java.util.Map)
	 */
	public void executeProcesoLETEntregaPremioCampaniaCierreCampania(Map params) {
		log.info("Entro a ProcesoLETEntregaPremioCampaniaCierreCampaniaDAOIbatis - executeProcesoLETEntregaPremioCampaniaCierreCampania(java.util.Map)");
		getSqlMapClientTemplate().update("spusicc.let.ProcesosLETSQL.executeProcesoLETEntregaPremioCampaniaCierreCampania", params);
		log.info("Salio a ProcesoLETEntregaPremioCampaniaCierreCampaniaDAOIbatis - executeProcesoLETEntregaPremioCampaniaCierreCampania(java.util.Map)");
	}
	
}
